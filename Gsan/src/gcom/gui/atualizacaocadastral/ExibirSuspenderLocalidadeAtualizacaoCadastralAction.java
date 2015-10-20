package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.AreaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.FiltroAreaAtualizacaoCadastralDM;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1328] - Suspender Localidade Atualização Cadastral
 * 
 * @author André Miranda
 * @date 27/08/2014
 */
public class ExibirSuspenderLocalidadeAtualizacaoCadastralAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("suspenderLocalidadeAtualizacaoCadastral");

		SuspenderLocalidadeAtualizacaoCadastralActionForm form =(SuspenderLocalidadeAtualizacaoCadastralActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			form.setIdEmpresa("-1");
			form.setIdsRegistro(null);
		}

		this.pesquisarEmpresa(httpServletRequest);

		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")) {
			this.pesquisarLocalidade(httpServletRequest, form);
		} else {
			httpServletRequest.removeAttribute("colecaoAreaAtualizacaoCadastral");
		}

		return retorno;
	}

	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, SuspenderLocalidadeAtualizacaoCadastralActionForm form) {
		FiltroAreaAtualizacaoCadastralDM filtro = new FiltroAreaAtualizacaoCadastralDM();
		filtro.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.EMPRESA_ID, form.getIdEmpresa()));
		filtro.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.CODIGO_SITUACAO, ConstantesSistema.SIM));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.SETOR_COMERCIAL);

		Collection<?> colecaoAreaAtualizacao = Fachada.getInstancia().pesquisar(filtro, AreaAtualizacaoCadastralDM.class.getName());

		if (Util.isVazioOrNulo(colecaoAreaAtualizacao)) {
			httpServletRequest.setAttribute("semLocalidades", true);
		} else {
			httpServletRequest.removeAttribute("semLocalidades");
			httpServletRequest.setAttribute("colecaoAreaAtualizacaoCadastral", colecaoAreaAtualizacao);
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
