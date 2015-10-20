package gcom.gui.cobranca;

import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.FachadaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe respons�vel por remover os motivos de aceita��o
 * selecionados da tela motivo_nao_aceitacao_manter.jsp
 * 
 * @author Diogo Peixoto
 * @since 24/05/2011
 *
 */
public class RemoverMotivoNaoAceitacaoEncerramentoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		MotivoNaoAceitacaoEncerramentoOSFiltrarActionForm form = (MotivoNaoAceitacaoEncerramentoOSFiltrarActionForm) actionForm;
		String[] selecaoMotivos = form.getMotivoNaoAceitacaoEncerramentoOSSelectID();

		if (!Util.isVazioOrNulo(selecaoMotivos)) {
			Fachada fachada = Fachada.getInstancia();

			MotivoNaoAceitacaoEncerramentoOS motivo =  null;
			for (String id : selecaoMotivos) {
				motivo =  new MotivoNaoAceitacaoEncerramentoOS();
				motivo.setId(Integer.valueOf(id));
				try{
					fachada.remover(motivo);
				}catch (FachadaException e) {
					throw new ActionServletException("atencao.dependencias.existentes", null, "Favor remover as deped�ncias"); 
				}
			}
		}else{
			throw new ActionServletException("atencao.registros.nao_selecionados");
		}

		montarPaginaSucesso(httpServletRequest,
				selecaoMotivos.length +
				" Motivo(s) de n�o aceita��o removido(s) com sucesso.",
				"Realizar outra manuten��o de Motivos de n�o aceita��o",
		"exibirManterMotivoNaoAceitacaoEncerramentoOSAction.do?menu=sim");

		return retorno;
	}
}