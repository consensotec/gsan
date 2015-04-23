package gsan.gui.seguranca.acesso.usuario;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Solicitação Acesso Situação >>
 * 
 * @author: Wallace Thierre
 * @Data: 05/11/2010
 * 
 */
public class InserirSolicitacaoAcessoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirSolicitacaoAcessoSituacaoActionForm form = (InserirSolicitacaoAcessoSituacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = new SolicitacaoAcessoSituacao();

		String descricao = form.getDescricao();

		Collection colecaoPesquisa = null;

		// Descricao Solicitação Acesso Situação
		if (!"".equals(form.getDescricao())) {
			solicitacaoAcessoSituacao.setDescricao(form.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}

		// Indicador de Uso

		if (form.getIndicadorUso() != null) {
			solicitacaoAcessoSituacao.setIndicadorUso(form.getIndicadorUso());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicador de uso");
		}

		// Indicador Situacao

		if (form.getCodigoSituacao() != null) {
			solicitacaoAcessoSituacao.setCodigoSituacao(form
					.getCodigoSituacao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"indicador de situacao");
		}

		// Data Corrente
		solicitacaoAcessoSituacao.setUltimaAlteracao(new Date());

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();

		filtroSolicitacaoAcessoSituacao
				.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoAcessoSituacao.DESCRICAO,
						solicitacaoAcessoSituacao.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroSolicitacaoAcessoSituacao,
				SolicitacaoAcessoSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.descricao_existente",
					null, solicitacaoAcessoSituacao.getDescricao());
		} else {
			solicitacaoAcessoSituacao.setDescricao(descricao);

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR,
					solicitacaoAcessoSituacao.getId(),
					solicitacaoAcessoSituacao.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(solicitacaoAcessoSituacao);
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			Integer idSolicitacaoAcessoSituacao = (Integer) fachada
					.inserir(solicitacaoAcessoSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situação Solicitação Acesso  " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Situação Solicitação Acesso",
					"exibirInserirSolicitacaoAcessoSituacaoAction.do?menu=sim",
					"exibirAtualizarSolicitacaoAcessoSituacaoAction.do?idRegistroAtualizacao="
							+ idSolicitacaoAcessoSituacao,
					"Atualizar Situação Solicitação Acesso Inserida");

			sessao.removeAttribute("InserirSolicitacaoAcessoSituacaoActionForm");
		}
		return retorno;
	}

}
