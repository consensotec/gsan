package gcom.gui.atualizacaocadastral;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 1327] - Liberar Localidade Atualização Cadastral
 * 
 * @author André Miranda
 * @date 26/08/2014
 *
 */
public class ExibirLiberarLocalidadeAtualizacaoCadastralAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("liberarLocalidadeAtualizacaoCadastral");

		LiberarLocalidadeAtualizacaoCadastralActionForm form = (LiberarLocalidadeAtualizacaoCadastralActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			form.setIdEmpresa("-1");
			form.setLocalidade("");
			form.setNomeLocalidade("");
			form.setSetorComercial("");
			form.setNomeSetorComercial("");
		}

		if (Util.verificarNaoVazio(form.getLocalidade())) {
			this.pesquisarLocalidade(httpServletRequest, form);

			if (Util.verificarNaoVazio(form.getSetorComercial())) {
				this.pesquisarSetorComercial(httpServletRequest, form);
			}
		}

		this.pesquisarEmpresa(httpServletRequest);

		return retorno;
	}

	private void pesquisarSetorComercial(HttpServletRequest httpServletRequest, LiberarLocalidadeAtualizacaoCadastralActionForm form) {
		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colSetor = Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName());

		if (Util.isVazioOrNulo(colSetor)) {
			form.setSetorComercial("");
			form.setNomeSetorComercial("Setor Comercial Inexistente");
		} else {
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetor);
			form.setNomeSetorComercial(setorComercial.getDescricao());

			httpServletRequest.setAttribute("setorComercialEncontrado", true);
		}
	}

	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, LiberarLocalidadeAtualizacaoCadastralActionForm form) {
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colLocalidade = Fachada.getInstancia().pesquisar(filtro, Localidade.class.getName());

		if (Util.isVazioOrNulo(colLocalidade)) {
			form.setLocalidade("");
			form.setNomeLocalidade("Localidade Inexistente");
		} else {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			form.setNomeLocalidade(localidade.getDescricao());

			httpServletRequest.setAttribute("localidadeEncontrada", true);
		}
	}

	private void pesquisarEmpresa(HttpServletRequest httpServletRequest) {
		FiltroEmpresa filtro = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colEmpresa = this.getFachada().pesquisar(filtro, Empresa.class.getName());

		if (Util.isVazioOrNulo(colEmpresa)) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", "EMPRESA");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colEmpresa);
	}
}
