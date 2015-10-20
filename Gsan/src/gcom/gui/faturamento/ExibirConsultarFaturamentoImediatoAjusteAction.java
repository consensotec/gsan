package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarFaturamentoImediatoAjusteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarFaturamentoImediatoAjuste");
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera os par�metros da sess�o para ser efetuada a pesquisa
		
		FaturamentoImediatoAjusteHelper helper = (FaturamentoImediatoAjusteHelper)httpServletRequest.getAttribute("helper");
		
		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Integer qtdImoveis = fachada.contarFaturamentoImediatoAjuste(helper);
	
		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		retorno =  controlarPaginacao(httpServletRequest, retorno, qtdImoveis);
		int indice = (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa");

		// cria a collecao de Faturamento Imediato Ajuste
		Collection colecaoFaturamentoImediatoAjuste =  (Collection) fachada.pesquisarFaturamentoImediatoAjuste(helper, indice);
		
		//Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarEquipeAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoFaturamentoImediatoAjuste != null
				&& !colecaoFaturamentoImediatoAjuste.isEmpty()) {

			httpServletRequest.setAttribute(
					"colecaoFaturamentoImediatoAjuste", colecaoFaturamentoImediatoAjuste);

		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
		
	}

}
