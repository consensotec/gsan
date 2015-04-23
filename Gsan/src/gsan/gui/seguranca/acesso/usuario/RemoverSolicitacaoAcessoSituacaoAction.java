package gsan.gui.seguranca.acesso.usuario;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.ManutencaoRegistroActionForm;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.usuario.FiltroSolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverSolicitacaoAcessoSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		FiltroSolicitacaoAcessoSituacao filtroSolicitacaoAcessoSituacao = new FiltroSolicitacaoAcessoSituacao();

		Collection idsSolicitacaoAcessoSituacao = new ArrayList(ids.length);

		for (int i = 0; i < ids.length; i++) {
			idsSolicitacaoAcessoSituacao.add(new Integer(ids[i]));
		}

		filtroSolicitacaoAcessoSituacao
				.adicionarParametro(new ParametroSimplesIn(
						FiltroSolicitacaoAcessoSituacao.ID,
						idsSolicitacaoAcessoSituacao));

		Collection coletionSolicitacaoAcessoSituacao = fachada.pesquisar(
				filtroSolicitacaoAcessoSituacao,
				SolicitacaoAcessoSituacao.class.getName());

		Iterator itera = coletionSolicitacaoAcessoSituacao.iterator();

		while (itera.hasNext()) {

			SolicitacaoAcessoSituacao solicitacaoAcessoSituacao = (SolicitacaoAcessoSituacao) itera
					.next();

			// ------------ REGISTRAR TRANSAÇÃO ----------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER,
					solicitacaoAcessoSituacao.getId(),
					solicitacaoAcessoSituacao.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			// ------------ REGISTRAR TRANSAÇÃO ----------------

			registradorOperacao.registrarOperacao(solicitacaoAcessoSituacao);

			fachada.remover(solicitacaoAcessoSituacao);

		}

		Integer idQt = ids.length;

		montarPaginaSucesso(httpServletRequest, idQt.toString()
				+ " Situacao Solicitacao Acesso removido(s) com sucesso.",
				"Realizar outra Manutenção Situacao Solicitacao Acesso",
				"exibirFiltrarSolicitacaoAcessoSituacaoAction.do?menu=sim");
		return retorno;

	}

}
