package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterImovelProgramaEspecialAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterImovelProgramaEspecialAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera os par�metros da sess�o para ser efetuada a pesquisa
		FiltroImovelProgramaEspecial filtro = (FiltroImovelProgramaEspecial) sessao.getAttribute("filtroImovelProgramaEspecial");
		
		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtro, ImovelProgramaEspecial.class.getName());
		
		Collection colecaoImoveisProgramas = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma equipe
		// cadastrada para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarEquipeAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.
		if (colecaoImoveisProgramas != null && !colecaoImoveisProgramas.isEmpty()) {

			// Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			// da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usu�rio selecione o link para ir para a
			// segunda p�gina ele n�o v� para tela de atualizar.
			if (colecaoImoveisProgramas.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				// Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				// equipe_filtrar. Caso todas as condi��es sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarEquipeAction e em caso negativo
				// manda a cole��o pelo request.
				if (sessao.getAttribute("atualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarImovelProgramaEspecialAction");
					ImovelProgramaEspecial imovel = (ImovelProgramaEspecial) colecaoImoveisProgramas.iterator().next();
					sessao.setAttribute("imovelProgramaEspecial", imovel);
					sessao.setAttribute("idRegistroAtualizar", imovel.getId());
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarImovelProgramaEspecialAction.do");
				} else {
					sessao.removeAttribute("imovelProgramaEspecial");
					httpServletRequest.setAttribute("colecaoImoveisProgramasEspeciais",
							colecaoImoveisProgramas);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterImovelProgramaEspecialAction.do");
				}
			} else {
				sessao.removeAttribute("imovelProgramaEspecial");
				httpServletRequest.setAttribute("colecaoImoveisProgramasEspeciais",
						colecaoImoveisProgramas);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterImovelProgramaEspecialAction.do");
			}
		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
		
	}
}
