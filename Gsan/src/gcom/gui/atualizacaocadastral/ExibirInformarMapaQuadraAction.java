package gcom.gui.atualizacaocadastral;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
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
 * [UC1646] Informar Mapa da Quadra
 * 
 * @author André Miranda
 * @since 04/12/2014
 */
public class ExibirInformarMapaQuadraAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		boolean validar;
		ActionForward retorno = actionMapping.findForward("exibirInformarMapaQuadra");
		ExibirInformarMapaQuadraActionForm form = (ExibirInformarMapaQuadraActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Validar localidade
		validar = form.getIdLocalidade() != null && !form.getIdLocalidade().isEmpty();
		if (validar) validarLocalidade(form, fachada);

		// Validar setor comercial, se localidade for validada
		if(validar) {
			validar = form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().isEmpty();
			if (validar) validarSetorComercial(form, fachada);
		}

		// Validar quadra, se localidade e setor comercial forem validados
		if(validar) {
			validar = form.getNumeroQuadra() != null && !form.getNumeroQuadra().isEmpty();
			if (validar) validarQuadra(form, fachada);
		}

		return retorno;
	}

	/**
	 * Validar Localidade
	 * 
	 * @author André Miranda
	 * @since 05/12/2014
	 * 
	 */
	private void validarLocalidade(ExibirInformarMapaQuadraActionForm form, Fachada fachada) {
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colLocalidade = fachada.pesquisar(filtro, Localidade.class.getName());
		if (Util.isVazioOrNulo(colLocalidade)) {
			form.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
		}else{
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			form.setDescricaoLocalidade(localidade.getDescricao());
		}

	}

	/**
	 * Validar Setor Comercial
	 * 
	 * @author André Miranda
	 * @since 05/12/2014
	 */
	private void validarSetorComercial(ExibirInformarMapaQuadraActionForm form, Fachada fachada) {
		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,	form.getCodigoSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colSetorComercial = fachada.pesquisar(filtro, SetorComercial.class.getName());
		if (Util.isVazioOrNulo(colSetorComercial)) {
			form.setDescricaoSetorComercial("SETOR COMERCIAL INEXISTENTE");
		}else{
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
			form.setDescricaoSetorComercial(setorComercial.getDescricao());
		}

	}

	/**
	 * Validar Quadra
	 * 
	 * @author André Miranda
	 * @since 24/02/2015
	 */
	private void validarQuadra(ExibirInformarMapaQuadraActionForm form, Fachada fachada) {
		FiltroQuadra filtro = new FiltroQuadra();
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA,	form.getNumeroQuadra()));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
		filtro.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colQuadra = fachada.pesquisar(filtro, Quadra.class.getName());
		if (Util.isVazioOrNulo(colQuadra)) {
			form.setDescricaoQuadra("QUADRA INEXISTENTE");
		}else{
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colQuadra);
			form.setDescricaoQuadra(quadra.getDescricao());
		}

	}
}
