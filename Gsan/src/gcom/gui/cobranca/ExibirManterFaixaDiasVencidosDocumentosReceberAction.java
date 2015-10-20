package gcom.gui.cobranca;

import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterFaixaDiasVencidosDocumentosReceberAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterFaixaDiasVencidosDocumentosReceberAction");
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera os par�metros da sess�o para ser efetuada a pesquisa
		FiltroDocumentosReceberFaixaDiasVencidos filtroDocumentosReceberFaixaDiasVencidos = new FiltroDocumentosReceberFaixaDiasVencidos();
		filtroDocumentosReceberFaixaDiasVencidos.setCampoOrderBy(FiltroDocumentosReceberFaixaDiasVencidos.VALOR_MENOR_FAIXA);
		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroDocumentosReceberFaixaDiasVencidos, DocumentosReceberFaixaDiasVencidos.class.getName());
		
		Collection colecaoDocumentosReceberFaixaDiasVencidos = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarEquipeAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoDocumentosReceberFaixaDiasVencidos != null
				&& !colecaoDocumentosReceberFaixaDiasVencidos.isEmpty()) {

			httpServletRequest.setAttribute(
					"colecaoDocumentosReceberFaixaDiasVencidos",
					colecaoDocumentosReceberFaixaDiasVencidos);

		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
		
	}

}
