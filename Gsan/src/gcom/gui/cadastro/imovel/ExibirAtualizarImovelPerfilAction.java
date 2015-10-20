package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelPerfilCapacidadeHidrometro;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfilCapacidadeHidrometro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.FiltroPermissaoEspecial;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 *  
 * @author Wallace Thierre
 * @date 04/10/2010
 * 
 */
public class ExibirAtualizarImovelPerfilAction extends GcomAction{
	
	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("ImovelPerfilAtualizar");
		
		AtualizarImovelPerfilActionForm atualizarImovelPerfilActionForm =
				(AtualizarImovelPerfilActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		FiltroImovelPerfilCapacidadeHidrometro filtroImovelPerfilCapacidadeHidrometro;
		Collection<ImovelPerfilCapacidadeHidrometro> colecaoImovelPerfilCapacidadeHidrometro;
		String[] hidrometroCapacidades;
		
		String pesquisarPermissaoEspecial = httpServletRequest.getParameter("pesquisarPermissaoEspecial");
		String desfazer = httpServletRequest.getParameter("desfazer");
		
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", true);			
		} else if (httpServletRequest.getParameter("filtrar") != null){
			sessao.removeAttribute("manter");
		}
		
		if (id == null ){
			if(httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
				"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		if(id != null && !id.trim().equals("") && pesquisarPermissaoEspecial==null){
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");
			filtroImovelPerfil.adicionarParametro
					(new ParametroSimples(FiltroImovelPerfil.ID, id));
			Collection<ImovelPerfil> colecaoImovelPerfil = fachada.pesquisar
				(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if (colecaoImovelPerfil != null && 
						!colecaoImovelPerfil.isEmpty()){
				
				ImovelPerfil imovelPerfil = (ImovelPerfil) Util
						.retonarObjetoDeColecao(colecaoImovelPerfil);		
				
				atualizarImovelPerfilActionForm.setId(imovelPerfil.getId().toString());
				atualizarImovelPerfilActionForm.setDescricao(imovelPerfil.getDescricao());
				atualizarImovelPerfilActionForm.setIndicadorUso(imovelPerfil.getIndicadorUso());
				atualizarImovelPerfilActionForm.setIndicadorGeracaoAutomatica(imovelPerfil.getIndicadorGeracaoAutomatica());
				atualizarImovelPerfilActionForm.setIndicadorInserirManterPerfil(imovelPerfil.getIndicadorInserirManterPerfil());
				atualizarImovelPerfilActionForm.setIndicadorGerarDadosLeitura(imovelPerfil.getIndicadorGerarDadosLeitura());
				atualizarImovelPerfilActionForm.setIndicadorBloquearRetificacao(imovelPerfil.getIndicadorBloquearRetificacao());
				atualizarImovelPerfilActionForm.setIndicadorGrandeConsumidor(imovelPerfil.getIndicadorGrandeConsumidor());
				atualizarImovelPerfilActionForm.setIndicadorBloquearDadosSocial(imovelPerfil.getIndicadorBloqueaDadosSocial());
				atualizarImovelPerfilActionForm.setIndicadorGeraDebitoSegundaViaConta(imovelPerfil.getIndicadorGeraDebitoSegundaViaConta());
				atualizarImovelPerfilActionForm.setIndicadorAcrescimosImpontualidade(imovelPerfil.getIndicadorAcrescimoImpontualidade());
				atualizarImovelPerfilActionForm.setIndicadorNegativacaoDoCliente(imovelPerfil.getIndicadorNegativacaoDoCliente());
				atualizarImovelPerfilActionForm.setIndicadorCorporativo(imovelPerfil.getIndicadorCorporativo());
				atualizarImovelPerfilActionForm.setIndicadorPerfilTelemedido(imovelPerfil.getIndicadorPerfilTelemedido());
				
				if(imovelPerfil.getConsumoMinimo()!=null)
				{
					atualizarImovelPerfilActionForm.setConsumoMinimo(
							String.valueOf(imovelPerfil.getConsumoMinimo()));
				}
				else
				{
					atualizarImovelPerfilActionForm.setConsumoMinimo(null);
				}
				
				atualizarImovelPerfilActionForm.setColecaoHidrometroCapacidade(pesquisarHidrometroCapacidade());
				
				filtroImovelPerfilCapacidadeHidrometro = new FiltroImovelPerfilCapacidadeHidrometro();
				filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(
						new ParametroSimples(FiltroImovelPerfilCapacidadeHidrometro.IMOVEL_PERFIL_ID, imovelPerfil.getId()));
				
				colecaoImovelPerfilCapacidadeHidrometro = getFachada().pesquisar(filtroImovelPerfilCapacidadeHidrometro, 
						ImovelPerfilCapacidadeHidrometro.class.getName());
				
				if(colecaoImovelPerfilCapacidadeHidrometro!=null && colecaoImovelPerfilCapacidadeHidrometro.size()!=0)
				{
					hidrometroCapacidades = new String[colecaoImovelPerfilCapacidadeHidrometro.size()];
					
					int contador = 0;
					for(ImovelPerfilCapacidadeHidrometro imovelPerfilCH : colecaoImovelPerfilCapacidadeHidrometro)
					{
						hidrometroCapacidades[contador] = imovelPerfilCH.getHidrometroCapacidade().getId().toString();
						contador++;
					}
					
					atualizarImovelPerfilActionForm.setHidrometroCapacidades(hidrometroCapacidades);
				}
				else
				{
					atualizarImovelPerfilActionForm.setHidrometroCapacidades(null);
				}
				
				if(imovelPerfil.getPermissaoEspecial()!=null)
				{
					atualizarImovelPerfilActionForm.setIdPermissaoEspecial(imovelPerfil.getPermissaoEspecial().getId().toString());
					atualizarImovelPerfilActionForm.setDescricaoPermissaoEspecial(imovelPerfil.getPermissaoEspecial().getDescricao());
				}
				else
				{
					atualizarImovelPerfilActionForm.setIdPermissaoEspecial(null);
					atualizarImovelPerfilActionForm.setDescricaoPermissaoEspecial(null);
				}
				
				sessao.setAttribute("atualizarImovelPerfil", imovelPerfil);
			}			
		}
		
		if(desfazer!=null)
		{
			desfazer(atualizarImovelPerfilActionForm);
		}
		
		if(pesquisarPermissaoEspecial!=null)
		{
			pesquisarPermissaoEspecial(atualizarImovelPerfilActionForm,httpServletRequest);
		}
		
		String indicadorClienteCorporativo = Fachada.getInstancia().obterValorParametro(ParametroSistema.INDICADOR_CONTROLE_GRANDE_CLIENTE);
        
        if(indicadorClienteCorporativo != null && indicadorClienteCorporativo.equals(ConstantesSistema.SIM.toString())){
			httpServletRequest.setAttribute("controleClienteCorporativo", true);	 				
        }
		
		return retorno;
	}
		
	@SuppressWarnings("unchecked")
	private Collection<HidrometroCapacidade> pesquisarHidrometroCapacidade(){
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<HidrometroCapacidade> colecaoHidrometroCapacidade = getFachada().
				pesquisar(filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
		
		return colecaoHidrometroCapacidade;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarPermissaoEspecial(AtualizarImovelPerfilActionForm atualizarImovelPerfilActionForm,HttpServletRequest httpServletRequest){
		FiltroPermissaoEspecial filtroPermissaoEspecial;
		
		if(Util.isInteger(atualizarImovelPerfilActionForm.getIdPermissaoEspecial()))
		{
			filtroPermissaoEspecial = new FiltroPermissaoEspecial();
			filtroPermissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPermissaoEspecial.ID, 
					Integer.valueOf(atualizarImovelPerfilActionForm.getIdPermissaoEspecial())));
			
			PermissaoEspecial permissaoEspecial = (PermissaoEspecial) Util.retonarObjetoDeColecao(getFachada().
					pesquisar(filtroPermissaoEspecial, PermissaoEspecial.class.getName()));
			
			if(permissaoEspecial!=null)
			{
				atualizarImovelPerfilActionForm.setDescricaoPermissaoEspecial(permissaoEspecial.getDescricao());
				httpServletRequest.removeAttribute("idPermissaoEspecialNaoEncontrado");
			}
			else
			{
				atualizarImovelPerfilActionForm.setDescricaoPermissaoEspecial("Permissão Especial não encontrada");
				httpServletRequest.setAttribute("idPermissaoEspecialNaoEncontrado", "true");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void desfazer(AtualizarImovelPerfilActionForm atualizarImovelPerfilActionForm){
		
		FiltroImovelPerfilCapacidadeHidrometro filtroImovelPerfilCapacidadeHidrometro;
		Collection<ImovelPerfilCapacidadeHidrometro> colecaoImovelPerfilCapacidadeHidrometro;
		String[] hidrometroCapacidades;
		
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");
		filtroImovelPerfil.adicionarParametro
				(new ParametroSimples(FiltroImovelPerfil.ID, atualizarImovelPerfilActionForm.getId()));
		Collection<ImovelPerfil> colecaoImovelPerfil = getFachada().pesquisar
			(filtroImovelPerfil, ImovelPerfil.class.getName());
		
		if (colecaoImovelPerfil != null && 
				!colecaoImovelPerfil.isEmpty()){
		
			ImovelPerfil imovelPerfil = (ImovelPerfil) Util
					.retonarObjetoDeColecao(colecaoImovelPerfil);
			
			atualizarImovelPerfilActionForm.setId(imovelPerfil.getId().toString());
			atualizarImovelPerfilActionForm.setDescricao(imovelPerfil.getDescricao());
			atualizarImovelPerfilActionForm.setIndicadorUso(imovelPerfil.getIndicadorUso());
			atualizarImovelPerfilActionForm.setIndicadorGeracaoAutomatica(imovelPerfil.getIndicadorGeracaoAutomatica());
			atualizarImovelPerfilActionForm.setIndicadorInserirManterPerfil(imovelPerfil.getIndicadorInserirManterPerfil());
			atualizarImovelPerfilActionForm.setIndicadorGerarDadosLeitura(imovelPerfil.getIndicadorGerarDadosLeitura());
			atualizarImovelPerfilActionForm.setIndicadorBloquearRetificacao(imovelPerfil.getIndicadorBloquearRetificacao());
			atualizarImovelPerfilActionForm.setIndicadorGrandeConsumidor(imovelPerfil.getIndicadorGrandeConsumidor());
			atualizarImovelPerfilActionForm.setIndicadorBloquearDadosSocial(imovelPerfil.getIndicadorBloqueaDadosSocial());
			atualizarImovelPerfilActionForm.setIndicadorGeraDebitoSegundaViaConta(imovelPerfil.getIndicadorGeraDebitoSegundaViaConta());
			atualizarImovelPerfilActionForm.setIndicadorAcrescimosImpontualidade(imovelPerfil.getIndicadorAcrescimoImpontualidade());
			atualizarImovelPerfilActionForm.setIndicadorNegativacaoDoCliente(imovelPerfil.getIndicadorNegativacaoDoCliente());
			atualizarImovelPerfilActionForm.setIndicadorCorporativo(imovelPerfil.getIndicadorCorporativo());
			atualizarImovelPerfilActionForm.setIndicadorPerfilTelemedido(imovelPerfil.getIndicadorPerfilTelemedido());
			
			if(imovelPerfil.getConsumoMinimo()!=null)
			{
				atualizarImovelPerfilActionForm.setConsumoMinimo(
						String.valueOf(imovelPerfil.getConsumoMinimo()));
			}
			
			atualizarImovelPerfilActionForm.setColecaoHidrometroCapacidade(pesquisarHidrometroCapacidade());
			
			filtroImovelPerfilCapacidadeHidrometro = new FiltroImovelPerfilCapacidadeHidrometro();
			filtroImovelPerfilCapacidadeHidrometro.adicionarParametro(
					new ParametroSimples(FiltroImovelPerfilCapacidadeHidrometro.IMOVEL_PERFIL_ID, imovelPerfil.getId()));
			
			colecaoImovelPerfilCapacidadeHidrometro = getFachada().pesquisar(filtroImovelPerfilCapacidadeHidrometro, 
					ImovelPerfilCapacidadeHidrometro.class.getName());
			
			if(colecaoImovelPerfilCapacidadeHidrometro!=null && colecaoImovelPerfilCapacidadeHidrometro.size()!=0)
			{
				hidrometroCapacidades = new String[colecaoImovelPerfilCapacidadeHidrometro.size()];
				
				int contador = 0;
				for(ImovelPerfilCapacidadeHidrometro imovelPerfilCH : colecaoImovelPerfilCapacidadeHidrometro)
				{
					hidrometroCapacidades[contador] = imovelPerfilCH.getHidrometroCapacidade().getId().toString();
					contador++;
				}
				
				atualizarImovelPerfilActionForm.setHidrometroCapacidades(hidrometroCapacidades);
			}
			
			if(imovelPerfil.getPermissaoEspecial()!=null)
			{
				atualizarImovelPerfilActionForm.setIdPermissaoEspecial(imovelPerfil.getPermissaoEspecial().getId().toString());
				atualizarImovelPerfilActionForm.setDescricaoPermissaoEspecial(imovelPerfil.getPermissaoEspecial().getDescricao());
			}
			else
			{
				atualizarImovelPerfilActionForm.setIdPermissaoEspecial(null);
				atualizarImovelPerfilActionForm.setDescricaoPermissaoEspecial(null);
			}
		}
	}
}
