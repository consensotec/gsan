package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1170] - Inserir Motivos de N�o Aceita��o de Encerramento de OS
 * Classe respons�vel por exibir os dados na tela de inserir motivo de
 * n�o aceita��o de encerramento de OS. A priori vai estar em branco, pois
 * n�o � necess�rio nenhuma consulta na base de dados. 
 * 
 * @author Diogo Peixoto
 * @since 20/05/2011
 * 
 */

public class ExibirInserirMotivoNaoAceitacaoEncerramentoOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("inserirMotivoNaoAceitacaoEncerramentoOS");
		InserirMotivoNaoAceitacaoEncerramentoOSActionForm form = (InserirMotivoNaoAceitacaoEncerramentoOSActionForm) actionForm;
		String limpar = (String)httpServletRequest.getParameter("limpar");
		if(Util.verificarNaoVazio(limpar) && limpar.equalsIgnoreCase("ok")){
			form.limparCampos();
		}
		return retorno;
	}
}