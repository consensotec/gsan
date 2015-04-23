package gsan.gui.atendimentopublico.registroatendimento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;

public class RemoverMotivoOcorrenciaOperacionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarMotivoOcorrenciaOperacionalActionForm manterMotivoOcorrenciaOperacionalActionForm = (AtualizarMotivoOcorrenciaOperacionalActionForm) actionForm;
		
		String[] idsOcorrenciasMotivosRemocao = manterMotivoOcorrenciaOperacionalActionForm
				.getIdMotivoOcorrenciaOperacional();

		fachada.remover(idsOcorrenciasMotivosRemocao, OcorrenciaOperacionalMotivo.class.getName(),
				null, null);
		
		montarPaginaSucesso(httpServletRequest, idsOcorrenciasMotivosRemocao.length
			+ " Motivo(s) Ocorrência(s) operacional(is) excluído(s) com sucesso.",
			"Manter outra Ocorrência Operacional",
			"exibirFiltrarMotivoOcorrenciaOperacionalAction.do?menu=sim");
		
		return retorno;
	}
}
