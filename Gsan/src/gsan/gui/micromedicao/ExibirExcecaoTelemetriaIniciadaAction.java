package gsan.gui.micromedicao;

import java.io.IOException;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroTelemetriaMovReg;
import gsan.micromedicao.TelemetriaMovReg;
import gsan.util.IoUtil;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Action responsável pela pre-exibição da pagina de exibição do 
 * erro ocorrido no consistir telemetria
 * 
 * @author Hugo Amorim
 * @created 07/10/2008
 */
public class ExibirExcecaoTelemetriaIniciadaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirExcecao");
		
		String idTelemetria = httpServletRequest.getParameter("idTelemetria");
		
		FiltroTelemetriaMovReg filtroTelemetriaMovReg = new FiltroTelemetriaMovReg();
		
		filtroTelemetriaMovReg.adicionarParametro(new ParametroSimples(FiltroTelemetriaMovReg.ID, idTelemetria));
		
		TelemetriaMovReg telemetriaMovReg = 
			(TelemetriaMovReg) Util.retonarObjetoDeColecao(
				Fachada.getInstancia().pesquisar(filtroTelemetriaMovReg, TelemetriaMovReg.class.getName()));
		
		try {
			httpServletRequest.setAttribute("excecao", 
					IoUtil.transformarBytesParaObjeto(telemetriaMovReg.getDescricaoErro()).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			throw new ActionServletException("erro.sistema");
		}
		
		return retorno;
	}
}
