package gsan.gui.relatorio.atendimentopublico;

import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.atendimentopublico.RelatorioTiposServico;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @since 28/11/2011
 */
public class GerarRelatorioTiposServicoAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
        
		GerarRelatorioTiposServicoActionForm form = (GerarRelatorioTiposServicoActionForm) actionForm;
        String nomeRelatorio = ConstantesRelatorios.RELATORIO_TIPOS_SERVICO;
        RelatorioTiposServico relatorio = new RelatorioTiposServico(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);
        
        FiltrarRelatorioTiposServicoHelper filtro = new FiltrarRelatorioTiposServicoHelper(
					form.getTipoMedicao(), form.getDataInicial(), form.getDataFinal());
			
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
			
		relatorio.addParametro("tipoRelatorio", Integer.parseInt(tipoRelatorio));
			
		relatorio.addParametro("filtro", filtro);
								
		relatorio.addParametro("dataInicial", form.getDataInicial());
		relatorio.addParametro("dataFinal", form.getDataFinal());
		relatorio.addParametro("tipoMedicao", form.getTipoMedicao());
			
		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}
}