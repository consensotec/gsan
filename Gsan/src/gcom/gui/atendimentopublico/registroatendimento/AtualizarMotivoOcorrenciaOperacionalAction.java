package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;
import java.util.Date;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacional;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarMotivoOcorrenciaOperacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = new FiltroOcorrenciaOperacionalMotivo();
		AtualizarMotivoOcorrenciaOperacionalActionForm atualizarMotivoOcorrenciaOperacionalActionForm = (AtualizarMotivoOcorrenciaOperacionalActionForm) actionForm;
				OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivo = new OcorrenciaOperacionalMotivo();
			OcorrenciaOperacionalTipo ocorrenciaOperacionalTipo = new OcorrenciaOperacionalTipo();
			
			ocorrenciaOperacionalMotivo.setId(Integer.parseInt(atualizarMotivoOcorrenciaOperacionalActionForm.getIdMotivoOcorrenciaOperacional()[0]));
			ocorrenciaOperacionalMotivo.setDescricao(atualizarMotivoOcorrenciaOperacionalActionForm.getDescricao().toUpperCase());
			ocorrenciaOperacionalMotivo.setDescricaoAbreviada(atualizarMotivoOcorrenciaOperacionalActionForm.getDescricaoAbreviada().toUpperCase());
			ocorrenciaOperacionalTipo.setId(atualizarMotivoOcorrenciaOperacionalActionForm.getTipoOcorrencia());
			ocorrenciaOperacionalMotivo.setOcorrenciaOperacionalTipo(ocorrenciaOperacionalTipo);
			ocorrenciaOperacionalMotivo.setIndicadorUso(atualizarMotivoOcorrenciaOperacionalActionForm.getIndicadorUso());
			ocorrenciaOperacionalMotivo.setUltimaAlteracao(new Date());
			
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.ID, ocorrenciaOperacionalMotivo.getId()));
			
			Collection colecaoOcorrenciaOperacionalMotivo = fachada.pesquisar(
				filtroOcorrenciaOperacionalMotivo, OcorrenciaOperacionalMotivo.class.getName());
			
			if (colecaoOcorrenciaOperacionalMotivo == null
					|| colecaoOcorrenciaOperacionalMotivo.isEmpty()) {
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}
			
			OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivoBase = (OcorrenciaOperacionalMotivo)colecaoOcorrenciaOperacionalMotivo.
					iterator().next();
			
			if (ocorrenciaOperacionalMotivoBase.getUltimaAlteracao().after(
				ocorrenciaOperacionalMotivo.getUltimaAlteracao())) {
				throw new ActionServletException("atencao.atualizacao.timestamp");
			}
			
			fachada.atualizar(ocorrenciaOperacionalMotivo);
			
			montarPaginaSucesso(httpServletRequest, "Motivo da Ocorrência Operacional de código "
					+ ocorrenciaOperacionalMotivo.getId()
					+ " atualizada com sucesso.", "Manter outra Ocorrência Operacional",
					"exibirFiltrarMotivoOcorrenciaOperacionalAction.do?menu=sim",
					null, null);
		return retorno;
	}
}
