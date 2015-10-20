package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.AreaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.FiltroAreaAtualizacaoCadastralDM;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1328] - Suspender Localidade Atualizacao Cadastral
 * 
 * @author André Miranda
 * @date 27/08/2014
 */
public class SuspenderLocalidadeAtualizacaoCadastralAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = this.getFachada();
		FiltroAreaAtualizacaoCadastralDM filtroArea = null;
		SuspenderLocalidadeAtualizacaoCadastralActionForm form =
				(SuspenderLocalidadeAtualizacaoCadastralActionForm) actionForm;

		if (Util.isVazioOrNulo(form.getIdsRegistro())) {
			throw new ActionServletException("atencao.id_localidade_nao_selecionado");
		}

		for (String id : form.getIdsRegistro()) {
			filtroArea = new FiltroAreaAtualizacaoCadastralDM();
			filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.ID, id));

			Collection<?> colAreaAtualizacaoCadastral =
					fachada.pesquisar(filtroArea, AreaAtualizacaoCadastralDM.class.getName());

			if (!Util.isVazioOrNulo(colAreaAtualizacaoCadastral)) {
				AreaAtualizacaoCadastralDM area =
						(AreaAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colAreaAtualizacaoCadastral);
				area.setCodigoSituacao(ConstantesSistema.INDICADOR_SUSPENSO);
				area.setDataSuspensao(new Date());
				area.setUltimaAlteracao(new Date());

				fachada.atualizar(area);
			}
		}

		montarPaginaSucesso(httpServletRequest, "Localidade(s) suspensa(s) com sucesso.",
			"Suspender outra Localidade", "exibirSuspenderLocalidadeAtualizacaoCadastralAction.do?menu=sim");

		return retorno;
	}
}
