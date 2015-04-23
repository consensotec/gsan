package gsan.gui.relatorio.arrecadacao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

import gsan.arrecadacao.ArrecadacaoForma;import gsan.arrecadacao.Arrecadador;import gsan.arrecadacao.FiltroArrecadacaoForma;import gsan.arrecadacao.FiltroArrecadador;import gsan.fachada.Fachada;import gsan.gui.GcomAction;

/**
 * [UC0827] Gerar Relatório Análise dos Avisos Bancários
 * 
 * @see gsan.gui.relatorio.arrecadacao.GerarRelatorioAnaliseAvisosBancariosActionForm
 * @see gsan.gui.relatorio.arrecadacao.GerarRelatorioAnaliseAvisosBancariosAction
 * @see gsan.relatorio.arrecadacao.RelatorioAnaliseAvisosBancarios
 * 
 * @author Victor Cisneiros
 * @date 30/07/2008
 */
public class ExibirGerarRelatorioAnaliseAvisosBancariosAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioAnaliseAvisosBancariosAction");
		Fachada fachada = Fachada.getInstancia();
		
		// ------------------------------
		// -- Por Arrecadador
		// ------------------------------
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		Collection collectionArrecadador = fachada.pesquisar(filtroArrecadador, Arrecadador.class.getName());
		request.setAttribute("collectionArrecadador", collectionArrecadador);
		
		// ------------------------------
		// -- Por Formar de Arrecadação
		// ------------------------------
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
		Collection collectionArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		request.setAttribute("collectionArrecadacaoForma", collectionArrecadacaoForma);
		
		return retorno;
	}

}
