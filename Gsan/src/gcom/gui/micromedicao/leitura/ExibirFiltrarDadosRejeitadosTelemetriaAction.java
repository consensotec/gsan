/**
 * 
 */
package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroTelemetriaRetMot;
import gcom.micromedicao.TelemetriaRetMot;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1125] - Filtrar Dados Rejeitados Telemetria
 * @author Fábio Aguiar
 * @date 27/04/2015
 */ 
public class ExibirFiltrarDadosRejeitadosTelemetriaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
 		ActionForward retorno = actionMapping
				.findForward("filtrarDadosRejeitadosTelemetria"); 	
		
		FiltrarDadosRejeitadosTelemetriaActionForm form = (FiltrarDadosRejeitadosTelemetriaActionForm) actionForm;

		Fachada fachada = this.getFachada();
		
		FiltroTelemetriaRetMot filtro = new FiltroTelemetriaRetMot();
		filtro.adicionarParametro(new ParametroSimples(FiltroTelemetriaRetMot.INDICADOR_MOV_ACEITO, 2));
		filtro.adicionarParametro(new ParametroSimples(FiltroTelemetriaRetMot.INDICADOR_USO, 1));
		filtro.setCampoOrderBy(FiltroTelemetriaRetMot.DESCRICAO);
		Collection<TelemetriaRetMot> colecaoTelemetriaRetMot = fachada.pesquisar(filtro, TelemetriaRetMot.class.getName());
		httpServletRequest.setAttribute("colecaoMotivoRejeicao",colecaoTelemetriaRetMot);
		
		if(httpServletRequest.getParameter("menu") != null){
			form.setIndicadorEnvioDadosTotalmenteRejeitados("1");			
		}
		
		return retorno;
	}
	
}
