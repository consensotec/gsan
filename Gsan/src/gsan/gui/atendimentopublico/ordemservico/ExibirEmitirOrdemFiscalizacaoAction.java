package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.FiltroOrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0983] Emitir Ordem de Fiscalização
 * 
 * Este Caso Uso permite realizar a emissão de Documentos de Ordem de Fiscalização
 * de forma individual para um determinado imóvel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class ExibirEmitirOrdemFiscalizacaoAction extends GcomAction {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirEmitirOrdemFiscalizacaoAction");

		EmitirOrdemFiscalizacaoForm emitirOrdemFiscalizacaoForm =
			(EmitirOrdemFiscalizacaoForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
				
		// Verifica se entrou apartir
		// do menu
		//
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			
			this.limpar(emitirOrdemFiscalizacaoForm, sessao);		
			
		}
		
		// Pesquisa Imovel
		// Enter ou Reload PopUp
		//
		if(httpServletRequest.getParameter("pesquisarImovel")!=null
				&& httpServletRequest.getParameter("pesquisarImovel").toString().equalsIgnoreCase("sim")){
			
			if(httpServletRequest.getParameter("idImovel")!=null
					&& !httpServletRequest.getParameter("idImovel").toString().equals("")){
				emitirOrdemFiscalizacaoForm.setMatriculaImovel(httpServletRequest.getParameter("idImovel"));	
			}
			
			if(emitirOrdemFiscalizacaoForm.getMatriculaImovel()!=null && 
				!emitirOrdemFiscalizacaoForm.getMatriculaImovel().equals("")){
								
				getImovel(emitirOrdemFiscalizacaoForm,httpServletRequest);
			}
			
			
			
		}
		return retorno;
	}
	
	private void getImovel(EmitirOrdemFiscalizacaoForm form,
			HttpServletRequest httpServletRequest) {
		
			Fachada fachada = Fachada.getInstancia();
		
			HttpSession sessao = httpServletRequest.getSession(false);

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
					form.getMatriculaImovel()));
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);


			Collection<?> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
					
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {		
				String enderecoFormatado = null;				
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);				
				enderecoFormatado = fachada.pesquisarEndereco(imovel.getId());
				sessao.setAttribute("enderecoFormatado",enderecoFormatado);
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
				form.setEnderecoImovel(enderecoFormatado);
				
				if(imovel.getLigacaoAguaSituacao()!=null){
					form.setSituacaoAguaDebitos(imovel.getLigacaoAguaSituacao().getDescricao());
				}
				if(imovel.getLigacaoEsgotoSituacao()!=null){
					form.setSituacaoEsgotoDebitos(imovel.getLigacaoEsgotoSituacao().getDescricao());
				}
				this.validarExistenciaOs(form,httpServletRequest,fachada);
				
			} else {			
				this.limpar(form, sessao);
				sessao.removeAttribute("inscricaoImovelEncontrada");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("Matrícula inexistente");
				
			}
	}
	
	private void validarExistenciaOs(EmitirOrdemFiscalizacaoForm form,
			HttpServletRequest httpServletRequest,Fachada fachada){
		
			HttpSession sessao = httpServletRequest.getSession(false);
			
			ServicoTipo servicoTipo = 
				fachada.recuperaServicoTipoPorConstante(
						ServicoTipo.TIPO_ORDEM_SERVICO_FISCALIZACAO);
			
			if(servicoTipo==null){
				throw new ActionServletException("atencao.servico_tipo_nao_existe");
			}
			
			FiltroOrdemServico filtro = new FiltroOrdemServico();
			
			filtro.adicionarParametro(
					new ParametroSimples(FiltroOrdemServico.ID_IMOVEL, form.getMatriculaImovel()));
			filtro.adicionarParametro(
					new ParametroSimples(FiltroOrdemServico.SITUACAO, OrdemServico.SITUACAO_PENDENTE));
			filtro.adicionarParametro(
					new ParametroSimples(FiltroOrdemServico.ID_SERVICO_TIPO,servicoTipo.getId()));
			
			Collection<?> colecaoOrdensServido 
				= fachada.pesquisar(filtro, OrdemServico.class.getName());
			
			OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdensServido);
			
			if(ordemServico!=null){				
				form.setOrdemServico(ordemServico.getId().toString());
				Integer[] idImovelOS = new Integer[2];
				
				idImovelOS[0] = new Integer(form.getMatriculaImovel());
				idImovelOS[1] = ordemServico.getId();
				sessao.setAttribute("idImovelOS", idImovelOS);
				
				Integer idLigacao = null;
				if(form.getTipo() != null && !form.getTipo().equals("")){
					idLigacao = new Integer(form.getTipo());
				}
				
				//[SB0001] Emissão de relatório Tipo 1 					
				if(fachada.verificarEmissaoOrdemFiscalizacao(ordemServico.getId(),form.getMatriculaImovel(), idLigacao ) )	{				
					sessao.removeAttribute("idImovelTipo2");
					sessao.setAttribute("idImovelTipo1", form.getMatriculaImovel());
					
				//[SB0002] Emissão de relatório Tipo 2 		
				}else{
					sessao.removeAttribute("idImovelTipo1");
					sessao.setAttribute("idImovelTipo2", form.getMatriculaImovel());
				}
				form.setIndicadorPermitirEmitir("sim");
				form.setIndicadorPermitirGerarOs("nao");
			}else{
				sessao.removeAttribute("idImovelTipo1");
				sessao.removeAttribute("idImovelTipo2");
				
				form.setIndicadorPermitirEmitir("nao");
				form.setIndicadorPermitirGerarOs("sim");
			}
			
	}
	
	private void limpar(EmitirOrdemFiscalizacaoForm form,HttpSession sessao){
		form.setTipo("1");
		form.setInscricaoImovel(null);
		form.setMatriculaImovel(null);
		form.setIndicadorPermitirEmitir("nao");
		form.setIndicadorPermitirGerarOs("nao");
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("inscricaoImovelEncontrada");
		sessao.removeAttribute("idImovelTipo1");
		sessao.removeAttribute("idImovelTipo2");
	}
}
