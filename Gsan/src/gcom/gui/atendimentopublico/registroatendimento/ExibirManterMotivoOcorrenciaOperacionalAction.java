package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
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

public class ExibirManterMotivoOcorrenciaOperacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping
				.findForward("exibirSelecionarMotivoOcorrenciaOperacional");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa o atributo se o usu�rio voltou para o manter
		if (sessao.getAttribute("colecaoOcorrenciaOperacionalTela") != null) {
			sessao.removeAttribute("colecaoOcorrenciaOperacionalTela");
		}

		// Recupera o filtro passado pelo FiltrarOcorrenciaOperacionalAction para
		// ser efetuada pesquisa
		FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = (FiltroOcorrenciaOperacionalMotivo) sessao
				.getAttribute("filtroOcorrenciaOperacionalMotivo");
		

		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
			filtroOcorrenciaOperacionalMotivo, OcorrenciaOperacionalMotivo.class.getName());
		Collection colecaoOcorrenciaOperacionalMotivo = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		// Verifica se a cole��o retornada pela pesquisa � nula, em caso
		// afirmativo comunica ao usu�rio que n�o existe nenhuma fuuncionalidade
		// cadastrada
		// para a pesquisa efetuada e em caso negativo e se
		// atender a algumas condi��es seta o retorno para o
		// ExibirAtualizarOcorrenciaOperacionalAction, se n�o atender manda a
		// cole��o pelo request para ser recuperado e exibido pelo jsp.

		if (colecaoOcorrenciaOperacionalMotivo != null && !colecaoOcorrenciaOperacionalMotivo.isEmpty()) {

			// Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			// da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			// nova busca), evitando, assim, que caso haja 11 elementos no
			// retorno da pesquisa e o usu�rio selecione o link para ir para a
			// segunda p�gina ele n�o v� para tela de atualizar.

			if (colecaoOcorrenciaOperacionalMotivo.size() == 1) {

				// Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				// ocorrencia_operacional_filtrar. Caso todas as condi��es sejam
				// verdadeiras seta o retorno para o
				// ExibirAtualizarOcorrenciaOperacionalAction e em caso negativo
				// manda a cole��o pelo request.

				if (httpServletRequest.getAttribute("atualizar") != null) {
					String opcaoAtualizar = (String) sessao.getAttribute("opcaoAtualizar");
					if(opcaoAtualizar.equals("1")){
						retorno = actionMapping
								.findForward("exibirAtualizarManterMotivoOcorrenciaOperacional");
						OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivo = (OcorrenciaOperacionalMotivo) colecaoOcorrenciaOperacionalMotivo
								.iterator().next();
						sessao.setAttribute("objetoOcorrenciaOperacionalMotivo", ocorrenciaOperacionalMotivo);	
					}else if(opcaoAtualizar.equals("2")){
						retorno = actionMapping
								.findForward("exibirSelecionarMotivoOcorrenciaOperacional");
						httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalMotivo",colecaoOcorrenciaOperacionalMotivo);
					}
				} else {
					httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalMotivo",colecaoOcorrenciaOperacionalMotivo);
				}
			} else {
				httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalMotivo",
					colecaoOcorrenciaOperacionalMotivo);
			}
		} else {
			// Nenhuma ocorrencia operacional cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
