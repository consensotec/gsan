package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.FiltroImovelPerfil;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

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
	 * < <Descri��o do m�todo>>
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
			HttpServletResponse httpServletResponse){
		
		// Seta o caminho do retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterImovelPerfil");		
		
		Collection colecaoImovelPerfil = new ArrayList();
		
		// Mudar isso quando for implementado o esquema de seguran�a
		HttpSession session = httpServletRequest.getSession(false);
		
		FiltroImovelPerfil filtroImovelPerfil = (FiltroImovelPerfil) 
				session.getAttribute("filtroImovelPerfil");
			
		if(filtroImovelPerfil != null && !filtroImovelPerfil.equals("")){
			
			filtroImovelPerfil.setCampoOrderBy
			(FiltroImovelPerfil.ID);
			
			//n�o esta passando daqui
			Map resultado = controlarPaginacao(httpServletRequest, retorno, 
					filtroImovelPerfil, ImovelPerfil.class.getName());
			colecaoImovelPerfil = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");		
					
		}
		
		if(colecaoImovelPerfil!= null && !colecaoImovelPerfil.isEmpty()){
			
			if (colecaoImovelPerfil.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null 
							|| httpServletRequest.getParameter("page.offset").equals("1"))) {
				
				if(httpServletRequest.getParameter("indicadorAtualizar") != null){
					
					retorno = actionMapping.findForward("exibirAtualizarImovelPerfil");
					
					ImovelPerfil imovelPerfil = 
						(ImovelPerfil) colecaoImovelPerfil.iterator().next();
					
					session.setAttribute("imovelPerfil", imovelPerfil);
					
				} else {
					
					httpServletRequest.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
				}				
				
			} else {
				httpServletRequest.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
			}
		}else {
			// Caso n�o haja nenhum resultado da pesquisa
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		
		}		
		
		return retorno;
	}		

}
