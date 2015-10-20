package gcom.gui.relatorio.seguranca;

import gcom.gui.seguranca.acesso.usuario.FiltrarSolicitacaoAcessoSituacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.RelatorioManterSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioManterSolicitacaoAcessoSituacaoAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarSolicitacaoAcessoSituacaoActionForm filtrarSolicitacaoAcessoSituacaoActionForm = (FiltrarSolicitacaoAcessoSituacaoActionForm) actionForm;

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = (FiltroSolicitacaoAcessoSituacao) sessao
				.getAttribute("filtroSolicitacaoAcessoSituacao");

		SolicitacaoAcessoSituacao solicitacaoAcessoSituacaoParametros = new SolicitacaoAcessoSituacao();

		String id = null;

		String idSolicitacaoAcessoSituacaoPesquisar = (String) filtrarSolicitacaoAcessoSituacaoActionForm
				.getId();

		if (idSolicitacaoAcessoSituacaoPesquisar != null
				&& !idSolicitacaoAcessoSituacaoPesquisar.equals("")) {
			id = idSolicitacaoAcessoSituacaoPesquisar;
		}

		Short indicadorUso = null;

		if (filtrarSolicitacaoAcessoSituacaoActionForm.getIndicadorUso() != null
				&& !filtrarSolicitacaoAcessoSituacaoActionForm
						.getIndicadorUso().equals("")) {

			indicadorUso = new Short(
					""
							+ filtrarSolicitacaoAcessoSituacaoActionForm
									.getIndicadorUso());
		}

		solicitacaoAcessoSituacaoParametros.setId(id == null ? null
				: new Integer(id));
		solicitacaoAcessoSituacaoParametros.setDescricao(""
				+ filtrarSolicitacaoAcessoSituacaoActionForm.getDescricao());
		solicitacaoAcessoSituacaoParametros.setIndicadorUso(indicadorUso);

		// Fim da parte que vai mandar os parametros para o relat�rio

		RelatorioManterSolicitacaoAcessoSituacao relatorioManterSolicitacaoAcessoSituacao = new RelatorioManterSolicitacaoAcessoSituacao(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorioManterSolicitacaoAcessoSituacao.addParametro(
				"filtroSolicitacaoAcessoSituacao",
				filtroSolicitacaoAcessoSituacao);

		relatorioManterSolicitacaoAcessoSituacao.addParametro(
				"solicitacaoAcessoSituacaoParametros",
				solicitacaoAcessoSituacaoParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterSolicitacaoAcessoSituacao.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterSolicitacaoAcessoSituacao, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;

	}
}
