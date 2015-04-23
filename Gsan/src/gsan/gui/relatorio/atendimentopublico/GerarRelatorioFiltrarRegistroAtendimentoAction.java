package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.atendimentopublico.RelatorioFiltrarRegistroAtendimento;
import gsan.relatorio.atendimentopublico.RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioFiltrarRegistroAtendimentoAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		HttpSession session = request.getSession();
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		FiltrarRegistroAtendimentoHelper filtroRA = (FiltrarRegistroAtendimentoHelper) session.getAttribute("filtroRA");
		if (filtroRA == null) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		
		if (filtroRA.getRegistroAtendimento() != null && 
			filtroRA.getRegistroAtendimento().getIndicadorCoordenadaSemLogradouro() == ConstantesSistema.SIM){
				RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro relatorio = new RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro(usuario);
				relatorio.addParametro("filtroRA", filtroRA);
				relatorio.addParametro("tipoRelatorio", tipoRelatorio);
				return processarExibicaoRelatorio(
					relatorio, tipoRelatorio, request, response, mapping);
		} else {
				RelatorioFiltrarRegistroAtendimento relatorio = new RelatorioFiltrarRegistroAtendimento(usuario);
				relatorio.addParametro("filtroRA", filtroRA);
				relatorio.addParametro("tipoRelatorio", tipoRelatorio);
				return processarExibicaoRelatorio(
						relatorio, tipoRelatorio, request, response, mapping);
		}
		
		
	}

}
