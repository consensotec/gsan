package gcom.gui.cadastro.imovel;

import gcom.atualizacaocadastral.MapaAtualizacaoCadastralDM;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

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

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = actionMapping.findForward("exibirMapaKML");

		String paramsMapa = request.getParameter("paramsMapa");
		MapaAtualizacaoCadastralDM mapa = null;

		//quando o parametro paramsMapa é passado, os valores do imovel são ignorados
		//e a pesquisa do mapa e json será feita através das informações contidas neste parametro
		//(LOCA_ID,CD_SETOR,N_QUADRA). esta situação é gerada apenas quando é chamada 
		//a funcionalidade pelo inserir imovel.
		if (paramsMapa != null && !paramsMapa.isEmpty()) {
			request.setAttribute("atualizar", true);
			request.setAttribute("paramsMapa", paramsMapa);
			mapa = fachada.obterArquivoMapaQuadraPorParametros(paramsMapa);
		} else {
			Integer idImovel = Integer.parseInt(request.getParameter("idImovel"));
			Boolean atualizar = Boolean.valueOf(request.getParameter("atualizar"));
			Imovel imovel = fachada.pesquisarImovel(idImovel);
			request.setAttribute("idImovel", idImovel);

			if(imovel != null) {
				mapa = fachada.obterArquivoMapaQuadraImovel(idImovel);
				if(imovel.getCoordenadaX()!=null&&imovel.getCoordenadaY()!=null) {
					//Obs.: No GSAN, as coordenadas estão armazenadas de forma contrária (X = Latitude e Y = Longitude)
					request.setAttribute("latImov", imovel.getCoordenadaX().toString());
					request.setAttribute("lonImov", imovel.getCoordenadaY().toString());
				}
			}

			if (atualizar) request.setAttribute("atualizar", true);
		}

		if (mapa == null) {
			//caso não tenham sido enviadas nenhuma coordenada
			//Coordenadas do Rio Grande do Norte
			String lat = "-6.083033716807931";
			String lon = "-36.47186279297";
			request.setAttribute("latMapa", lat);
			request.setAttribute("lonMapa", lon);
			//afasta o nível de zoom para poder visualizar o RN completo.
			request.setAttribute("zoomMapa", 7);
		} else {
			request.setAttribute("latMapa", mapa.getCoordenadaY().toString());
			request.setAttribute("lonMapa", mapa.getCoordenadaX().toString());
			request.setAttribute("zoomMapa", 17);
		}

		//caso venham setados como parametro as coordenadas
		//devem ser utilizadas preferencialmente.
		String latParam = request.getParameter("latImov");
		String lonParam = request.getParameter("lonImov");
		if (latParam != null && lonParam != null && !latParam.isEmpty() && !lonParam.isEmpty()) {
			request.setAttribute("latImov", latParam.replace(",", "."));
			request.setAttribute("lonImov", lonParam.replace(",", "."));
		}

		request.setAttribute("urlServidor", getURLWithContextPath(request));

		String voltar = request.getParameter("voltar");
		if ("sim".equalsIgnoreCase(voltar)) {
			request.setAttribute("voltar", true);
			request.removeAttribute("atualizar");
		}

		return retorno;
	}

	public static String getURLWithContextPath(HttpServletRequest request) {
	   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
