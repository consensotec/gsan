package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.InstalacaoSubstituicaoRetiradaLoteHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1695] - Instalar/Substituir/Retirar Hidrômetro em Lote
 * @author Rodrigo Cabral
 * @since 18/11/2015
 */
public class ExibirEfetuarInstalacaoSubstituicaoRetiradaLoteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirEfetuarInstalacaoSubstituicaoRetiradaLote");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm form = (EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		sessao.setAttribute("servicoTipo",false);
		
		OrdemServico ordemServico = null;
		
		Imovel imovel = null;
		
		if (httpServletRequest.getParameter("desfazer") != null &&
				httpServletRequest.getParameter("desfazer").equals("sim")){
		
			form.limparForm();
			sessao.removeAttribute("colecaoOsHidrometro");
			
		}
		
		// PESQUISAR A ORDEM DE SERVIÇO
		if (form.getIdOrdemServico() != null
				&& !form.getIdOrdemServico().equals("")) {
			
			//[FS0002] - Verificar existencia da ordem de serviço seletiva
			//[FS0003] - Verificar Ordem de Serviço Hidrometração
			ordemServico = fachada.pesquisarOrdemServicoHidrometro(new Integer(form.getIdOrdemServico()));
			
			
			if (ordemServico != null) {
				
				Imovel imovelComLocalidade = null;
				
				if (ordemServico.getServicoTipo() != null){
					
					form.setDescricaoOrdemServico(ordemServico.getServicoTipo().getDescricao());
					sessao.setAttribute("ordemServicoInexistente",Boolean.FALSE);
					
					// PREENCHER MATRICULA IMOVEL
					if (ordemServico.getImovel() != null){
						
						imovel = ordemServico.getImovel();
	                    
	                    FiltroImovel filtroImovel = new FiltroImovel();
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
						filtroImovel.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");
						filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
						
						Collection<Imovel> colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
						
						imovelComLocalidade = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
						
						String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId());
						form.setMatriculaImovel(inscricaoImovel);
					}
					
					if (ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().equals(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO)){
						
						sessao.setAttribute("servicoTipo",true);
						
						validarHidrometro(imovelComLocalidade, form);
						
						form.setOperacao("Instalação");
						
					} else if (ordemServico.getServicoTipo().getConstanteFuncionalidadeTipoServico().equals(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO)) {
						
						sessao.setAttribute("servicoTipo",true);
						
						validarHidrometro(imovelComLocalidade, form);
						
						MedicaoTipo medicaoTipo = new MedicaoTipo();
						medicaoTipo.setId(MedicaoTipo.LIGACAO_AGUA);
						
						FiltroHidrometroInstalacaoHistorico filtroHidrometroInstalacaoHistorico = new FiltroHidrometroInstalacaoHistorico();
						filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroSimples(FiltroHidrometroInstalacaoHistorico.LIGACAO_AGUA_ID, imovel.getId()));
						filtroHidrometroInstalacaoHistorico.adicionarParametro(new ParametroNulo(FiltroHidrometroInstalacaoHistorico.DATA_RETIRADA));
						filtroHidrometroInstalacaoHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroHidrometroInstalacaoHistorico.HIDROMETRO);
						
						Collection<HidrometroInstalacaoHistorico> colHidInstHist = fachada.pesquisar(filtroHidrometroInstalacaoHistorico, HidrometroInstalacaoHistorico.class.getName());
						
						HidrometroInstalacaoHistorico hidrometroAtualInstalado = (HidrometroInstalacaoHistorico) Util.retonarObjetoDeColecao(colHidInstHist);
						
						validarFinalidadeHidrometro(form, MedicaoTipo.LIGACAO_AGUA.toString());
						
						if (hidrometroAtualInstalado != null 
								&& hidrometroAtualInstalado.getHidrometro() != null){
							
							form.setNumeroHidrometroAtual(hidrometroAtualInstalado.getHidrometro().getNumero());
						} else {
							
							throw new ActionServletException("atencao.nao_hidrometro_agua");
						}
						
						
						form.setOperacao("Substituição");
						
					} else {
						
						sessao.setAttribute("servicoTipo",false);
					}
						
					
				} 			
				
			} else {
				form.setIdOrdemServico("");
				form.setDescricaoOrdemServico("Ordem de Serviço Inválida");
				sessao.setAttribute("ordemServicoInexistente",Boolean.TRUE);
			}
			
			
		}
		
		if (httpServletRequest.getParameter("adicionar") != null &&
				httpServletRequest.getParameter("adicionar").equals("sim")){
			
			Collection<InstalacaoSubstituicaoRetiradaLoteHelper> colecaoOsHidrometro = (Collection<InstalacaoSubstituicaoRetiradaLoteHelper>) 
																											sessao.getAttribute("colecaoOsHidrometro");
			
			if (sessao.getAttribute("colecaoOsHidrometro") == null) {
				colecaoOsHidrometro = new ArrayList<InstalacaoSubstituicaoRetiradaLoteHelper>();
			}
						
			InstalacaoSubstituicaoRetiradaLoteHelper helper = new InstalacaoSubstituicaoRetiradaLoteHelper();
			
			helper.setIdOS(form.getIdOrdemServico());
			helper.setOperacao(form.getOperacao());
			
			if (imovel != null){
				helper.setMatricula(imovel.getId());
			}
			
			helper.setHidrometroInstalado(form.getNumeroHidrometroInstalado());
			helper.setHidrometroRetirado(form.getNumeroHidrometroAtual());
			helper.setDataInstalacaoRetirada(form.getDataInstalSubstRet());
			helper.setLeituraInstalacao(form.getLeituraInstalacao());
			helper.setLeituraRetirada(form.getLeituraRetirada());
			
			if (colecaoOsHidrometro != null && !colecaoOsHidrometro.isEmpty()){
				
				Iterator<InstalacaoSubstituicaoRetiradaLoteHelper> iColOsHid = colecaoOsHidrometro.iterator();
				
				while (iColOsHid.hasNext()){
					
					InstalacaoSubstituicaoRetiradaLoteHelper helpAdicionar = iColOsHid.next();
					
					if (helpAdicionar.getIdOS().equals(helper.getIdOS())){
						
						throw new ActionServletException("atencao.os_selecionada");
						
					} else if (helpAdicionar.getHidrometroInstalado().equals(helper.getHidrometroInstalado())){
						
						throw new ActionServletException("atencao.hidrometro_uso");
					}
					
				}
				
			}
			
			colecaoOsHidrometro.add(helper);
			
			form.limparForm();
			
			sessao.setAttribute("colecaoOsHidrometro", colecaoOsHidrometro);
		
		}
		
		
		if (httpServletRequest.getParameter("remover") != null &&
				httpServletRequest.getParameter("remover").equals("sim")){
			
			Collection<InstalacaoSubstituicaoRetiradaLoteHelper> colecaoOsHidrometro = (Collection<InstalacaoSubstituicaoRetiradaLoteHelper>) 
					sessao.getAttribute("colecaoOsHidrometro");
			
			String[] ids = form.getIdRegistrosRemocao();
			if (ids != null && ids.length != 0) {
				for (int i = 0; i < ids.length; i++) {
					
					String idOs = ids[i];
					
					Iterator<InstalacaoSubstituicaoRetiradaLoteHelper> icolRemover = colecaoOsHidrometro.iterator();
					
					while (icolRemover.hasNext()){
						
						InstalacaoSubstituicaoRetiradaLoteHelper helpRemov = icolRemover.next();
						
						if (helpRemov.getIdOS().equals(idOs)){
							
							colecaoOsHidrometro.remove(helpRemov);
							
							sessao.setAttribute("colecaoOsHidrometro", colecaoOsHidrometro);
							break;
						}
					}
						
				}
		    }
			
		}
		
		
		return retorno;
	}
	
	/**
	 *  [FS0004 - Verificar Situação do Hidrômetro]
	 *  
	 * @param imovelComLocalidade
	 * @param form
	 */
	private void validarHidrometro(Imovel imovelComLocalidade, EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm form){
		
		// Verificar se o número do hidrômetro não está cadastrado
		if (form.getNumeroHidrometroInstalado() != null && !form.getNumeroHidrometroInstalado().trim().equals("")) {

			// Filtro para descobrir id do Hidrometro
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();

			filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.NUMERO_HIDROMETRO, form.getNumeroHidrometroInstalado()));
			
			filtroHidrometro.adicionarParametro(new ParametroSimples(
				FiltroHidrometro.HIDROMETRO_SITUACAO, HidrometroSituacao.DISPONIVEL));
			
			filtroHidrometro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");			

			Collection<Hidrometro> colecaoHidrometro = Fachada.getInstancia().pesquisar(filtroHidrometro,Hidrometro.class.getName());
	
			if (colecaoHidrometro == null || colecaoHidrometro.isEmpty()) {
				form.setNumeroHidrometroInstalado("");
				throw new ActionServletException("atencao.hidrometro_inexistente");
			}else{
				Hidrometro hidro = (Hidrometro) Util.retonarObjetoDeColecao(colecaoHidrometro);
				
				if (imovelComLocalidade != null && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null &&
						!hidro.getHidrometroLocalArmazenagem().getId().equals(imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
						throw new ActionServletException("atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}
				
				form.setNumeroHidrometroInstalado(hidro.getNumero());
				
				
			}
		}
		
	}
	
	
	/**
	 *  [FS0005] - Verificar a Existência de Hidrômetro no Imóvel/Ligação de água.
	 *  
	 * @param numeroHidrometro
	 * @param tipoMedicao
	 */
	private void validarFinalidadeHidrometro(EfetuarInstalacaoSubstituicaoRetiradaLoteActionForm form, String tipoMedicao) {
		
		String numeroHidrometro = form.getNumeroHidrometroInstalado();
		
		if (numeroHidrometro != null && !numeroHidrometro.trim().equals("") &&
			tipoMedicao != null && !tipoMedicao.trim().equals("")) {
			
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(
			FiltroHidrometro.NUMERO_HIDROMETRO, numeroHidrometro));
					
			Collection<Hidrometro> colecaoHidrometro = Fachada.getInstancia().pesquisar(filtroHidrometro, Hidrometro.class.getName());

			if (colecaoHidrometro != null && !colecaoHidrometro.isEmpty()) {
				Hidrometro hidrometro = (Hidrometro) colecaoHidrometro.iterator().next();
		
				if ((tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA.toString()) || tipoMedicao.equals(MedicaoTipo.POCO.toString())) &&
					hidrometro.getIndicadorFinalidade() == 2) {
					throw new ActionServletException("atencao.nao_e_possivel_adicionar_esgoto_para_medir_agua");
				} else if (tipoMedicao.equals(MedicaoTipo.LIGACAO_ESGOTO.toString()) && hidrometro.getIndicadorFinalidade() == 1) {
					throw new ActionServletException("atencao.nao_e_possivel_adicionar_agua_para_medir_esgoto");
				}
			}
		}
		
	}

}
