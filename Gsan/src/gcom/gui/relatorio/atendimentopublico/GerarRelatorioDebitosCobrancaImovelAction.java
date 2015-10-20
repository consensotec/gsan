package gcom.gui.relatorio.atendimentopublico;

import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioDebitosCobradosImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
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
public class GerarRelatorioDebitosCobrancaImovelAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
        
		GerarRelatorioDebitosCobrancaImovelActionForm form = (GerarRelatorioDebitosCobrancaImovelActionForm) actionForm;
        String nomeRelatorio = ConstantesRelatorios.RELATORIO_DEBITOS_COBRADOS_IMOVEL;
        RelatorioDebitosCobradosImovel relatorio = new RelatorioDebitosCobradosImovel(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);
        
        FiltrarRelatorioDebitosCobrancaImovelHelper filtro = new FiltrarRelatorioDebitosCobrancaImovelHelper(
					form.getIdImovel(), form.getInscricaoImovel(), form.getTipoServico(), form.getNomeTipoServico(),
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
		relatorio.addParametro("matriculaImovel", form.getIdImovel());
			
		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}
}