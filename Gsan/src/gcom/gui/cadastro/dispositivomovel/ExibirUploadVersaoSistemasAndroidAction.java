package gcom.gui.cadastro.dispositivomovel;

import gcom.cadastro.FiltroSistemaAndroid;
import gcom.cadastro.SistemaAndroid;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1015] Upload nova versão GSAN Dispositivo Móvel
 * 
 *   Este caso de uso permite realizar o envio dos arquivos referentes à 
 *   nova versão do GSAN Dispositivo Móvel.
 * 
 * @author Fernanda Almeida
 * @since 02/04/2012
 *
 */
public class ExibirUploadVersaoSistemasAndroidAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirUploadVersaoSistemasAndroidAction");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroSistemaAndroid filtroSistema = new FiltroSistemaAndroid();
		filtroSistema.adicionarParametro(new ParametroSimples(FiltroSistemaAndroid.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<SistemaAndroid> colSistemasAndroid = fachada.pesquisar(filtroSistema, SistemaAndroid.class.getName());

		if(colSistemasAndroid.size() != 0){
			sessao.setAttribute("colecaoSistemas", colSistemasAndroid);
		}
		
		return retorno;
	}
}	
