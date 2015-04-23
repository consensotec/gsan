package gsan.gui.relatorio.arrecadacao;

import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.banco.FiltroBanco;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
 * 
 * @author Victor Cisneiros
 * @date 21/08/2008
 */
public class ExibirGerarRelatorioAvisoBancarioPorContaCorrenteAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioAvisoBancarioPorContaCorrenteAction");
		Fachada fachada = Fachada.getInstancia();
		
		// ------------------------------
		// -- Por Banco
		// ------------------------------
		FiltroBanco filtroBanco = new FiltroBanco();
		Collection collectionBanco = fachada.pesquisar(filtroBanco, Banco.class.getName());
		request.setAttribute("collectionBanco", collectionBanco);
		
		return retorno;
	}

}
