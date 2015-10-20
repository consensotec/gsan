package gcom.gui.cadastro.imovel;

import gcom.atualizacaocadastral.MapaAtualizacaoCadastralDM;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Bruno Sá Barreto
 * @since 25/02/2015 
 */
public class ExibirObterMapaJSONAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 
		Fachada fachada = Fachada.getInstancia();
		MapaAtualizacaoCadastralDM mapa;
		
		try {
			String paramsMapa = httpServletRequest.getParameter("paramsMapa");
			if(paramsMapa.isEmpty()){
				Integer idImovel = Integer.parseInt(httpServletRequest.getParameter("idImovel"));
				mapa = fachada.obterArquivoMapaQuadraImovel(idImovel);
			}else{
				mapa = fachada.obterArquivoMapaQuadraPorParametros(paramsMapa);
			}
			
			if(mapa!=null){
				httpServletResponse.getWriter().write(new String(mapa.getArquivoJson()));
			}
			
			httpServletResponse.setContentType("application/json");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
}
