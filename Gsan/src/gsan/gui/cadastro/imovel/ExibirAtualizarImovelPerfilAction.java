package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.fachada.Fachada;
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
 *  
 * @author Wallace Thierre
 * @date 04/10/2010
 * 
 */
public class ExibirAtualizarImovelPerfilAction extends GcomAction{
	
	/**
	 * M�todo responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
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
		
		if(id != null && !id.trim().equals("")){
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro
					(new ParametroSimples(FiltroImovelPerfil.ID, id));
			Collection colecaoImovelPerfil = fachada.pesquisar
				(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if (colecaoImovelPerfil != null && 
						!colecaoImovelPerfil.isEmpty()){
				
				ImovelPerfil imovelPerfil = (ImovelPerfil) Util
						.retonarObjetoDeColecao(colecaoImovelPerfil);		
				
				atualizarImovelPerfilActionForm.setId(imovelPerfil.getId().toString());
				
				atualizarImovelPerfilActionForm.setDescricao(imovelPerfil.getDescricao());
				
				atualizarImovelPerfilActionForm.setIndicadorUso(""
						+ imovelPerfil.getIndicadorUso());
				
				atualizarImovelPerfilActionForm.setIndicadorGeracaoAutomatica("" 
						+ imovelPerfil.getIndicadorGeracaoAutomatica());
				
				atualizarImovelPerfilActionForm.setIndicadorInserirManterPerfil(""
						+ imovelPerfil.getIndicadorInserirManterPerfil());
				
				atualizarImovelPerfilActionForm.setIndicadorGerarDadosLeitura(""
						+ imovelPerfil.getIndicadorGerarDadosLeitura());
				
				atualizarImovelPerfilActionForm.setIndicadorBloquearRetificacao(""
						+ imovelPerfil.getIndicadorBloquearRetificacao());
				
				atualizarImovelPerfilActionForm.setIndicadorGrandeConsumidor(""
						+ imovelPerfil.getIndicadorGrandeConsumidor());
				
				atualizarImovelPerfilActionForm.setIndicadorBloquearDadosSocial(""
						+ imovelPerfil.getIndicadorBloqueaDadosSocial());
				
				atualizarImovelPerfilActionForm.setIndicadorGeraDebitoSegundaViaConta(""
						+ imovelPerfil.getIndicadorGeraDebitoSegundaViaConta());
				
				atualizarImovelPerfilActionForm.setIndicadorAcrescimosImpontualidade(""
						+ imovelPerfil.getIndicadorAcrescimoImpontualidade());
				
				atualizarImovelPerfilActionForm.setIndicadorNegativacaoDoCliente(""
						+ imovelPerfil.getIndicadorNegativacaoDoCliente());
				
				atualizarImovelPerfilActionForm.setIndicadorCorporativo(""
						+ imovelPerfil.getIndicadorCorporativo());

				sessao.setAttribute("atualizarImovelPerfil", imovelPerfil);
			}			
			
			if(sessao.getAttribute("colecaoImovelPerfil") != null){
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/filtrarImovelPerfilAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarImovelPerfilAction.do");
			}			
			
		}
		
		return retorno;
	}
		
	


}
