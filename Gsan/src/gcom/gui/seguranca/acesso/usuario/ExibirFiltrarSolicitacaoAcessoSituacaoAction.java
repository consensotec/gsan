package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Exibe o Filtrar Solicita��o Acesso Situa��o >>
 * 
 * @author: Wallace Thierre
 * @Data: 05/11/2010
 * 
 */
public class ExibirFiltrarSolicitacaoAcessoSituacaoAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarSolicitacaoAcessoSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSolicitacaoAcessoSituacaoActionForm form = (FiltrarSolicitacaoAcessoSituacaoActionForm) actionForm;

		// Quando for acessado pela primeira vez
		String primeiraVez = httpServletRequest.getParameter("menu");

		if (primeiraVez != null && !primeiraVez.trim().equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
					.toString());
		}

		if (form.getIndicadorAtualizar() == null) {
			form.setIndicadorAtualizar("1");
		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			form.setId("");
			form.setDescricao("");
			form.setIndicadorUso("");
			form.setCodigoSituacao("");

		}
		return retorno;
	}
}
