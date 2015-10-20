package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1524] Inserir Motivo da Ocorrência Operacional
 * 
 * @author Jonathan Marcos
 * @date 09/07/2013
 * 
 */
public class ExibirInserirMotivoOcorrenciaOperacionalAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("exibirMotivoOcorrenciaOperacional");
		
		Fachada fachada = Fachada.getInstancia();
			FiltroOcorrenciaOperacionalTipo filtroOcorrenciaOperacionalTipo = new FiltroOcorrenciaOperacionalTipo(FiltroOcorrenciaOperacionalTipo.DESCRICAO);
			Collection colecaoOcorrenciaOperacionalTipo = fachada.pesquisar(filtroOcorrenciaOperacionalTipo, OcorrenciaOperacionalTipo.class.getName()); 
	
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalTipo", colecaoOcorrenciaOperacionalTipo);
		
		return retorno;
	}
}
