package gsan.gui.cadastro.imovel;

import gsan.atualizacaocadastral.MapaAtualizacaoCadastralDM;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Bruno Sá Barreto
 * @since 26/02/2015
 */
public class ExibirMapaKMLAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno  = actionMapping.findForward("exibirMapaKML");
		
		String paramsMapa = httpServletRequest.getParameter("paramsMapa");
		MapaAtualizacaoCadastralDM mapa = null;
		
		//quando o parametro paramsMapa é passado, os valores do imovel são ignorados
		//e a pesquisa do mapa e json será feita através das informações contidas neste parametro
		//(LOCA_ID,CD_SETOR,N_QUADRA). esta situação é gerada apenas quando é chamada 
		//a funcionalidade pelo inserir imovel.
		if(paramsMapa!=null && !paramsMapa.isEmpty()){
			httpServletRequest.setAttribute("atualizar", true);
			httpServletRequest.setAttribute("paramsMapa", paramsMapa);
			mapa = fachada.obterArquivoMapaQuadraPorParametros(paramsMapa);
			
		}else{
			Integer idImovel = Integer.parseInt(httpServletRequest.getParameter("idImovel"));
			Boolean atualizar = Boolean.valueOf(httpServletRequest.getParameter("atualizar"));
			Imovel imovel = fachada.pesquisarImovel(idImovel);
			mapa = fachada.obterArquivoMapaQuadraImovel(idImovel);
			httpServletRequest.setAttribute("idImovel", idImovel);
			if(imovel.getCoordenadaX()!=null&&imovel.getCoordenadaY()!=null){
				//Obs.: No GSAN, as coordenadas estão armazenadas de forma contrária (X = Latitude e Y = Longitude)
				httpServletRequest.setAttribute("latImov", imovel.getCoordenadaX().toString());
				httpServletRequest.setAttribute("lonImov", imovel.getCoordenadaY().toString());
			}
			
			if(atualizar)httpServletRequest.setAttribute("atualizar", true);
		}
		
		if(mapa==null){
			//caso não tenham sido enviadas nenhuma coordenada
			//Coordenadas do Rio Grande do Norte
			String lat = "-6.083033716807931";
			String lon = "-36.47186279297";
			httpServletRequest.setAttribute("latMapa", lat);
			httpServletRequest.setAttribute("lonMapa", lon);
			//afasta o nível de zoom para poder visualizar o RN completo.
			httpServletRequest.setAttribute("zoomMapa", 7);
		}else{
			httpServletRequest.setAttribute("latMapa", mapa.getCoordenadaY().toString());
			httpServletRequest.setAttribute("lonMapa", mapa.getCoordenadaX().toString());
			httpServletRequest.setAttribute("zoomMapa", 17);
		}
		
		//caso venham setados como parametro as coordenadas
		//devem ser utilizadas preferencialmente.
		String latParam = httpServletRequest.getParameter("latImov");
		String lonParam = httpServletRequest.getParameter("lonImov");
		if(latParam!=null && lonParam!=null
			&& !latParam.isEmpty() && !lonParam.isEmpty()){
			httpServletRequest.setAttribute("latImov", latParam.replace(",", "."));
			httpServletRequest.setAttribute("lonImov", lonParam.replace(",", "."));
		}
		
		httpServletRequest.setAttribute("urlServidor", getURLWithContextPath(httpServletRequest));
		
		return retorno;
	}

	public static String getURLWithContextPath(HttpServletRequest request) {
	   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
