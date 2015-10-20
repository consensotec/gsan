package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.AreaAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.FiltroAreaAtualizacaoCadastralDM;
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
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1327] - Liberar Localidade Atualizacao Cadastral
 * 
 * @author André Miranda
 * @date 26/08/2014
 *
 */
public class LiberarLocalidadeAtualizacaoCadastralAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Localidade localidade = null;
		Fachada fachada = this.getFachada();
		FiltroAreaAtualizacaoCadastralDM filtroArea = null;
		AreaAtualizacaoCadastralDM areaAtualizacaoCadastral = new AreaAtualizacaoCadastralDM();
		LiberarLocalidadeAtualizacaoCadastralActionForm form = (LiberarLocalidadeAtualizacaoCadastralActionForm) actionForm;

		// FS002 - Verificar existência da localidade
		if (Util.verificarNaoVazio(form.getLocalidade())) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getLocalidade()));

			Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (Util.isVazioOrNulo(colLocalidade)) {
				throw new ActionServletException("atencao.localidade.inexistente");
			}

			localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			areaAtualizacaoCadastral.setLocalidade(localidade);

			// FS003 - Verificar existência do setor
			if (Util.verificarNaoVazio(form.getSetorComercial())) {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getLocalidade()));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getSetorComercial()));

				Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (Util.isVazioOrNulo(colSetorComercial)) {
					throw new ActionServletException("atencao.setor_comercial.inexistente");
				}

				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
				areaAtualizacaoCadastral.setSetorComercial(setorComercial);
			}
		}

		// Consultar empresa selecionada
		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));

			Collection<?> colEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

			if (!Util.isVazioOrNulo(colEmpresa)) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colEmpresa);
				areaAtualizacaoCadastral.setEmpresa(empresa);
			}
		}

		// FS0002 - Verificar se localidade se já liberada(Area com setorComercial = null)
		filtroArea = new FiltroAreaAtualizacaoCadastralDM();
		filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE_ID, form.getLocalidade()));
		filtroArea.adicionarParametro(new ParametroNulo(FiltroAreaAtualizacaoCadastralDM.SETOR_COMERCIAL));
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.EMPRESA);
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE);

		Collection<?> colecaoAreaAtualizacaoCadastral = fachada.pesquisar(filtroArea, AreaAtualizacaoCadastralDM.class.getName());

		if (!Util.isVazioOrNulo(colecaoAreaAtualizacaoCadastral)) {
			AreaAtualizacaoCadastralDM areaAtualizacao =
					(AreaAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colecaoAreaAtualizacaoCadastral);

			if (areaAtualizacao.getCodigoSituacao().equals(ConstantesSistema.INDICADOR_LIBERADO)) {
				String[] mensagem = new String[2];
				mensagem[0] = areaAtualizacao.getLocalidade().getDescricao();
				mensagem[1] = areaAtualizacao.getEmpresa().getDescricao();
				throw new ActionServletException("atencao.localidade_ja_liberada_empresa", null, mensagem);
			}
		}

		// Consultar Área Atualização Cadastral
		filtroArea = new FiltroAreaAtualizacaoCadastralDM();
		filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE_ID, form.getLocalidade()));

		if (!form.getSetorComercial().isEmpty()) {
			filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.SETOR_COMERCIAL_CODIGO,
				form.getSetorComercial()));
		}else{
			filtroArea.adicionarParametro(new ParametroNulo(FiltroAreaAtualizacaoCadastralDM.SETOR_COMERCIAL));
		}

		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.EMPRESA);
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE);
		filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.SETOR_COMERCIAL);

		Collection<?> colecaoArea = fachada.pesquisar(filtroArea, AreaAtualizacaoCadastralDM.class.getName());

		if (Util.isVazioOrNulo(colecaoArea)) {
			// SB001 - Atualizar Área liberada para atualização cadastral
			areaAtualizacaoCadastral.setCodigoSituacao(ConstantesSistema.INDICADOR_LIBERADO);
			areaAtualizacaoCadastral.setDataLiberacao(new Date());
			areaAtualizacaoCadastral.setUltimaAlteracao(new Date());

			this.suspenderSetoresComerciaisLiberadosNaLocalidade(form, fachada);
			
			fachada.inserir(areaAtualizacaoCadastral);
		} else {
			AreaAtualizacaoCadastralDM areaConsultada = (AreaAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colecaoArea);

			// FS0002 - Verificar se localidade/setor já liberado(a)
			if (areaConsultada.getCodigoSituacao().equals(ConstantesSistema.INDICADOR_LIBERADO)) {
				String[] mensagem = new String[2];
				mensagem[1] = areaConsultada.getEmpresa().getDescricao();
				if (Util.verificarNaoVazio(form.getSetorComercial())) {
					mensagem[0] = areaConsultada.getSetorComercial().getDescricao();
					throw new ActionServletException("atencao.setor_comercial_ja_liberado_empresa", null, mensagem);
				} else {
					mensagem[0] = areaConsultada.getLocalidade().getDescricao();
					throw new ActionServletException("atencao.localidade_ja_liberada_empresa", null, mensagem);
				}
			}

			// SB001 - Atualizar Área liberada para atualização cadastral
			areaConsultada.setEmpresa(areaAtualizacaoCadastral.getEmpresa());
			areaConsultada.setCodigoSituacao(ConstantesSistema.INDICADOR_LIBERADO);
			areaConsultada.setDataLiberacao(new Date());
			areaConsultada.setUltimaAlteracao(new Date());
			areaConsultada.setDataSuspensao(null);

			this.suspenderSetoresComerciaisLiberadosNaLocalidade(form, fachada);
			
			fachada.atualizar(areaConsultada);
		}

		montarPaginaSucesso(httpServletRequest, "Localidade liberada com sucesso.", "Liberar outra Localidade",
			"exibirLiberarLocalidadeAtualizacaoCadastralAction.do?menu=sim");

		return retorno;
	}

	/**
	 * Verifica os objetos do tipo AreaAtualizacaoCadastral que foram liberados com
	 * algum setor da localidade que está prestes a ser liberada. Visando não inserir
	 * registros "redundantes" na base, ex.: liberar um setor e depois liberar a localidade
	 * inteira.
	 * 	
	 * @author Bruno Sá Barreto
	 * @date 24/02/2015
	 *
	 * @param form - ActionForm da pagina
	 * @param fachada - fachada já inicializada
	 */
	@SuppressWarnings("unchecked")
	private void suspenderSetoresComerciaisLiberadosNaLocalidade(
			LiberarLocalidadeAtualizacaoCadastralActionForm form,
			Fachada fachada) {
		//caso o setor não tenha sido preenchido
		//verificar se existe alguma liberação por setor na localidade. Suspender todas.
		if(form.getSetorComercial().isEmpty()){
			FiltroAreaAtualizacaoCadastralDM 	filtroArea = new FiltroAreaAtualizacaoCadastralDM();
			filtroArea.adicionarParametro(new ParametroSimples(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE_ID, form.getLocalidade()));
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.EMPRESA);
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE);
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.EMPRESA);
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.LOCALIDADE);
			filtroArea.adicionarCaminhoParaCarregamentoEntidade(FiltroAreaAtualizacaoCadastralDM.SETOR_COMERCIAL);
	
			Collection<AreaAtualizacaoCadastralDM> colecaoArea = fachada.pesquisar(filtroArea, AreaAtualizacaoCadastralDM.class.getName());
			if(!colecaoArea.isEmpty()){
				for(AreaAtualizacaoCadastralDM area : colecaoArea){
					area.setCodigoSituacao(ConstantesSistema.INDICADOR_SUSPENSO);
					area.setUltimaAlteracao(new Date());
					area.setDataSuspensao(new Date());
					fachada.atualizar(area);
				}
			}
		}
	}
}