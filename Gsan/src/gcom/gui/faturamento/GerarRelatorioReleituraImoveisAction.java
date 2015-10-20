package gcom.gui.faturamento;

import gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioReleituraImoveis;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioReleituraImoveisAction extends
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

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection<AnalisarImoveisReleituraHelper> colAnalisarImoveisReleituraHelper = null;
		FiltrarAnalisarReleituraImoveisActionForm form = null;

		if (sessao.getAttribute("colAnalisarImoveisReleituraHelper") != null) {
			colAnalisarImoveisReleituraHelper = (Collection<AnalisarImoveisReleituraHelper>) sessao
					.getAttribute("colAnalisarImoveisReleituraHelper");
		}

		if (sessao.getAttribute("formularioFiltrarReleituraImoveis") != null) {
			form = (FiltrarAnalisarReleituraImoveisActionForm) sessao
					.getAttribute("formularioFiltrarReleituraImoveis");
		}

		RelatorioReleituraImoveis relatorio = new RelatorioReleituraImoveis(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";

		}
		relatorio
				.addParametro("tipoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("colAnalisarImoveisReleituraHelper",
				colAnalisarImoveisReleituraHelper);
		relatorio.addParametro("formularioFiltrarReleituraImoveis", form);

		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
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
