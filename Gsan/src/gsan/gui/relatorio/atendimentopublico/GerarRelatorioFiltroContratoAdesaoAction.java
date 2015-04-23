package gsan.gui.relatorio.atendimentopublico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.tarefa.TarefaRelatorio;

public class GerarRelatorioFiltroContratoAdesaoAction extends
			ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		RelatorioFiltroContratoAdesao relatorioFiltroContratoAdesao = new RelatorioFiltroContratoAdesao(
				getUsuarioLogado(httpServletRequest));
		
		relatorioFiltroContratoAdesao.addParametro("colecaoContratoAdesao", 
				httpServletRequest.getSession().getAttribute("colecaoContratoAdesao"));
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		
		relatorioFiltroContratoAdesao
			.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorioFiltroContratoAdesao,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		return retorno;
	}

}
