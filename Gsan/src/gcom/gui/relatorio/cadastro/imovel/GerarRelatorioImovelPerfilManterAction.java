package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroImovelPerfilHelper;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gui.cadastro.imovel.FiltrarImovelPerfilActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.RelatorioManterImovelPerfil;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioImovelPerfilManterAction extends ExibidorProcessamentoTarefaRelatorio{
	
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

		FiltrarImovelPerfilActionForm filtrarImovelPerfilActionForm = (FiltrarImovelPerfilActionForm) actionForm;

		FiltroImovelPerfilHelper filtroImovelPerfilHelper = (FiltroImovelPerfilHelper) sessao
				.getAttribute("filtroImovelPerfilHelper");

		// Inicio da parte que vai mandar os parametros para o relat�rio

		ImovelPerfil imovelPerfilParametros = new ImovelPerfil();
		String id = null;

		String idImovelPerfilPesquisar = (String) filtrarImovelPerfilActionForm.getId();

		if (idImovelPerfilPesquisar != null && !idImovelPerfilPesquisar.equals("")) {
			id = idImovelPerfilPesquisar;
		}				
		// seta os parametros que ser�o mostrados no relat�rio

		imovelPerfilParametros.setId(id == null ? null : new Integer(id));
		imovelPerfilParametros.setDescricao(""
				+filtrarImovelPerfilActionForm.getDescricao());	

		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
		RelatorioManterImovelPerfil relatorioManterImovelPerfil = new RelatorioManterImovelPerfil(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterImovelPerfil.addParametro("filtroImovelPerfilHelper",
				filtroImovelPerfilHelper);
		relatorioManterImovelPerfil.addParametro("imovelPerfilParametros",
				imovelPerfilParametros);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterImovelPerfil.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterImovelPerfil,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

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

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
