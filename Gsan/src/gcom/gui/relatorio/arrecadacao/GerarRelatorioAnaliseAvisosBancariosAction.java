package gcom.gui.relatorio.arrecadacao;

import gcom.gui.ActionServletException;import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;import gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancarios;import gcom.seguranca.acesso.usuario.Usuario;import gcom.tarefa.TarefaRelatorio;import gcom.util.ConstantesSistema;import java.io.OutputStream;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * [UC0827] Gerar Relat�rio An�lise dos Avisos Banc�rios
 * 
 * @see gcom.gui.relatorio.arrecadacao.GerarRelatorioAnaliseAvisosBancariosActionForm
 * @see gcom.gui.relatorio.arrecadacao.ExibirGerarRelatorioAnaliseAvisosBancariosAction
 * @see gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancarios
 * 
 * @author Victor Cisneiros
 * @date 30/07/2008
 */
public class GerarRelatorioAnaliseAvisosBancariosAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("### GerarRelatorioAnaliseAvisosBancariosAction ###");
		
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		GerarRelatorioAnaliseAvisosBancariosActionForm form = (GerarRelatorioAnaliseAvisosBancariosActionForm) actionForm;
		
		// ------------------------------
		// -- Parametros
		// ------------------------------
		Integer mesAno = null;
		Boolean porEstado = false;
		Boolean porArrecadador = false;
		Boolean porFormaArrecadacao = false;
		Integer idArrecadador = null;
		Integer idFormaArrecadacao = null;
		
		// ------------------------------
		// -- Mes/Ano
		// ------------------------------
		if (form.getMesAno() != null && !form.getMesAno().trim().equals("")) {
			mesAno = Integer.parseInt(form.getMesAno().substring(3, 7) + form.getMesAno().substring(0, 2));
		} else {
			throw new ActionServletException("atencao.required", null, "M�s/Ano da Arrecada��o");
		}
		
		// ------------------------------
		// -- Form
		// ------------------------------
		if (form.getEstado() != null && !form.getEstado().trim().equals("")) {
			porEstado = true;
		}
		if (form.getPorArrecadador() != null && !form.getPorArrecadador().trim().equals("")) {
			porArrecadador = true;
		}
		if (form.getPorFormaArrecadacao() != null && !form.getPorFormaArrecadacao().trim().equals("")) {
			porFormaArrecadacao = true;
		}
		if (form.getIdArrecadador() != null && !form.getIdArrecadador().trim().equals("")) {
			int id = new Integer(form.getIdArrecadador());
			if (id != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				idArrecadador = id;
			}
		}
		if (form.getIdFormaArrecadacao() != null && !form.getIdFormaArrecadacao().trim().equals("")) {
			int id = new Integer(form.getIdFormaArrecadacao());
			if (id != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				idFormaArrecadacao = id;
			}
		}
		
		// ------------------------------
		// -- Combinacao
		// ------------------------------
		boolean correta = false;
		if (idArrecadador == null && idFormaArrecadacao == null) {
			if (porEstado) correta = true;
		}
		else if (idArrecadador != null && idFormaArrecadacao == null) {
			if (porArrecadador) correta = true;
		}
		else if (idArrecadador != null && idFormaArrecadacao != null) {
			if (porArrecadador && porFormaArrecadacao) correta = true;
		}
		else if (idArrecadador == null && idFormaArrecadacao != null) {
			if (!porArrecadador && porFormaArrecadacao) correta = true;
		}
		
		if (!correta) {
			throw new ActionServletException("atencao.selecao.invalida");
		}
		
		// ------------------------------
		// -- Tipo do Relatorio
		// ------------------------------
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		// ------------------------------
		// -- Gera��o do Relatorio
		// ------------------------------
		RelatorioAnaliseAvisosBancarios relatorio = new RelatorioAnaliseAvisosBancarios(usuario);
		relatorio.addParametro("mesAno", mesAno);
		relatorio.addParametro("porEstado", porEstado);
		relatorio.addParametro("porArrecadador", porArrecadador);
		relatorio.addParametro("porFormaArrecadacao", porFormaArrecadacao);
		relatorio.addParametro("idArrecadador", idArrecadador);
		relatorio.addParametro("idFormaArrecadacao", idFormaArrecadacao);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);

		byte[] bytes = (byte[]) relatorio.executar();

		if (tipoRelatorio == TarefaRelatorio.TIPO_PDF) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.pdf");
			response.setContentType("application/pdf");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_RTF) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.rtf");
			response.setContentType("application/rtf");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_XLS) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.xls");
			response.setContentType("application/vnd.ms-excel");
		} else if (tipoRelatorio == TarefaRelatorio.TIPO_HTML) {
			response.addHeader("Content-Disposition", 
					"attachment; filename=relatorio.zip");
			response.setContentType("application/zip");
		}
		
		OutputStream out = response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
		return null;
	}
	
}
