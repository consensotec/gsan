package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfilHelper;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 *	MANTER IMOVEL PERFIL* 
 * @author Wallace Thierre
 * Date: 04/10/2010
 * 
 */
public class ExibirManterImovelPerfilAction extends GcomAction{
	
	/**
	 * < <Descrição do método>>
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
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse){
		
		// Seta o caminho do retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterImovelPerfil");		
		
		Collection<ImovelPerfil> colecaoImovelPerfil = new ArrayList();
		
		// Mudar isso quando for implementado o esquema de segurança
		HttpSession session = httpServletRequest.getSession(false);
		
		FiltroImovelPerfilHelper filtroImovelPerfilHelper = (FiltroImovelPerfilHelper) 
				session.getAttribute("filtroImovelPerfilHelper");
		
		int totalRegistros = getFachada().pesquisarImovelPerfilCount(filtroImovelPerfilHelper);
		
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);
		
		colecaoImovelPerfil = getFachada().pesquisarImovelPerfil(filtroImovelPerfilHelper, (Integer) httpServletRequest
 						.getAttribute("numeroPaginasPesquisa"),false);
		
		if(colecaoImovelPerfil!= null && !colecaoImovelPerfil.isEmpty()){
			
			if (colecaoImovelPerfil.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null 
							|| httpServletRequest.getParameter("page.offset").equals("1"))) {
				
				if(httpServletRequest.getParameter("indicadorAtualizar") != null){
					
					retorno = actionMapping.findForward("exibirAtualizarImovelPerfil");
					
					ImovelPerfil imovelPerfil = 
						(ImovelPerfil) colecaoImovelPerfil.iterator().next();
					
					session.setAttribute("imovelPerfil", imovelPerfil);
					session.setAttribute("idRegistroAtualizacao", imovelPerfil.getId().toString());
					
				} else {
					
					httpServletRequest.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
				}				
				
			} else {
				httpServletRequest.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
			}
		}else {
			// Caso não haja nenhum resultado da pesquisa
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		
		}		
		
		return retorno;
	}
}
