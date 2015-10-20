package gcom.gui.financeiro;

import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.financeiro.bean.ParametrosPerdasFiscaisHelper;
import gcom.financeiro.bean.ParametrosPerdasFiscaisItensHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1070] Manter Parametros Perdas Fiscais
 * 
 * @author Ricardo Germinio
 * @date 15/01/2013
 *
 */

public class ExibirManterParametrosPerdasFiscaisAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
				findForward("exibirManterParametrosPerdasFiscais");
		Fachada fachada = Fachada.getInstancia();
		ParametrosPerdasFiscaisActionForm form = (ParametrosPerdasFiscaisActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
		Collection<CobrancaSituacao> collectionCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
		sessao.setAttribute("collectionCobrancaSituacao", collectionCobrancaSituacao);

		if(httpServletRequest.getParameter("menu") != null){
			sessao.removeAttribute("parametrosPerdasFiscaisHelper");
		}

		if(httpServletRequest.getParameter("pesquisarParametroInformado") != null &&
				!httpServletRequest.getParameter("pesquisarParametroInformado").equals("")){

			ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper = new ParametrosPerdasFiscaisHelper();
			if(sessao.getAttribute("parametrosPerdasFiscaisHelper") != null ){
				parametrosPerdasFiscaisHelper = (ParametrosPerdasFiscaisHelper)sessao.getAttribute("parametrosPerdasFiscaisHelper");			
			}

			// --------------- botão adiciona Situação Encontrada ----------
			pesquisarParametroInformado(form,sessao,
					httpServletRequest,parametrosPerdasFiscaisHelper);

		}


		// ----------------- botão pesquisar parametro informado ----------------


		if(httpServletRequest.getParameter("adicionarParametroEncontrado") != null &&
				!httpServletRequest.getParameter("adicionarParametroEncontrado").equalsIgnoreCase("")){

			ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper = null;
			boolean referenciaInvalida = Util.validarAnoMesInvalido(form.getAnoMesReferenciaContabil(),"MM/yyyy");
			if(referenciaInvalida){
				throw new ActionServletException("atencao.vigencia_obrigatoria");
			}
			if(sessao.getAttribute("parametrosPerdasFiscaisHelper") != null ){
				parametrosPerdasFiscaisHelper = (ParametrosPerdasFiscaisHelper)sessao.getAttribute("parametrosPerdasFiscaisHelper");
				if(form.getAnoMesReferenciaContabil() != null && !form.getAnoMesReferenciaContabil().equals("")){referenciaInvalida = Util.validarAnoMesInvalido(form.getAnoMesReferenciaContabil(),"MM/yyyy");
					if(referenciaInvalida){
						throw new ActionServletException("atencao.referencia_invalida");
					}
					Integer anoMesReferenciaContabil = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil());
					if(!anoMesReferenciaContabil.equals(parametrosPerdasFiscaisHelper.getAnoMesReferenciaContabil())){
						throw new ActionServletException("atencao.vigencia_referencia_contabil_diferente");
					}
				}else{
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Vigência a partir da referência contábil");
				}

			}else{
				parametrosPerdasFiscaisHelper = new ParametrosPerdasFiscaisHelper();
				if(form.getAnoMesReferenciaContabil() != null  && !form.getAnoMesReferenciaContabil().equals("")){
					parametrosPerdasFiscaisHelper.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
				}
			}

			// --------------- botão adiciona Situação Encontrada ----------
			adicionarParametroEncontrado(form,sessao,httpServletRequest,parametrosPerdasFiscaisHelper);

			form.setValorLimite(null);
			form.setNumeroMeses(null);
			form.setSituacaoCobranca(null);		
		}	

		if(httpServletRequest.getParameter("idParametroRemover") != null &&
				!httpServletRequest.getParameter("idParametroRemover").equals("")){

			ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper = null;
			if(sessao.getAttribute("parametrosPerdasFiscaisHelper") != null ){
				parametrosPerdasFiscaisHelper = (ParametrosPerdasFiscaisHelper)sessao.getAttribute("parametrosPerdasFiscaisHelper");


				// --------------- botão adiciona Situação Encontrada ----------
				removerSituacaoSelecionada(form,sessao,
						httpServletRequest,parametrosPerdasFiscaisHelper);
			}
		}


		return retorno;
	} 



	private void adicionarParametroEncontrado(
			ParametrosPerdasFiscaisActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest,ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper) { 

		
			if(form.getValorLimite() != null && !form.getValorLimite().trim().equals("")){
				if(form.getNumeroMeses() != null && !form.getNumeroMeses().trim().equals("")){

				Collection<ParametrosPerdasFiscaisItensHelper> colecaoParametrosPerdasItensFiscais = parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper();

				if (!Util.isVazioOrNulo(colecaoParametrosPerdasItensFiscais)){ 

					ParametrosPerdasFiscaisItensHelper helper = new ParametrosPerdasFiscaisItensHelper();

					if(form.getValorLimite() != null && !form.getValorLimite().equals("") && !form.getValorLimite().equals("0")) {
						helper.setValorLimite(Util.formatarMoedaRealparaBigDecimal(form.getValorLimite()));
					} else {
						throw new ActionServletException("atencao.valor_limite_invalido");
					}
					if(form.getNumeroMeses() != null && !form.getNumeroMeses().equals("") && !form.getNumeroMeses().equals("0")) {
						helper.setNumeroMeses(new Short(form.getNumeroMeses()));
					} else {
						throw new ActionServletException("atencao.numero_mes_invalido");
					}
					if(form.getSituacaoCobranca() != null && !form.getSituacaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){

						String idFiltroCobrancaSituacao = form.getSituacaoCobranca();
						Integer idCobrancaSituacao = Util.converterStringParaInteger(idFiltroCobrancaSituacao);
						helper.setSituacaoCobranca(idCobrancaSituacao);

						FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
						filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.ID,idCobrancaSituacao));
						Collection<CobrancaSituacao> colecaoCobrancaSituacao = this.getFachada().pesquisar(
								filtroCobrancaSituacao, CobrancaSituacao.class.getName());
						CobrancaSituacao cobrancaSituacao = (CobrancaSituacao)Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
						helper.setDescricaoSituacaoCobranca(cobrancaSituacao.getDescricao());

					}
					if(!colecaoParametrosPerdasItensFiscais.contains(helper)){
						colecaoParametrosPerdasItensFiscais.add(helper);
					}else{
						throw new ActionServletException("atencao.valor_ja_informado");
					}

				}else {
					colecaoParametrosPerdasItensFiscais = new ArrayList<ParametrosPerdasFiscaisItensHelper>();
					ParametrosPerdasFiscaisItensHelper helper = new ParametrosPerdasFiscaisItensHelper();
					if(form.getValorLimite() != null && !form.getValorLimite().equals("") && !form.getValorLimite().equals("0")) {
						helper.setValorLimite(Util.formatarMoedaRealparaBigDecimal(form.getValorLimite()));
					} else {
						throw new ActionServletException("atencao.valor_limite_invalido");
					}
					if(form.getNumeroMeses() != null && !form.getNumeroMeses().equals("") && !form.getNumeroMeses().equals("0")) {
						helper.setNumeroMeses(new Short(form.getNumeroMeses()));
					} else {
						throw new ActionServletException("atencao.numero_mes_invalido");
					}
					if(form.getSituacaoCobranca() != null && !form.getSituacaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
						String idFiltroCobrancaSituacao = form.getSituacaoCobranca();
						Integer idCobrancaSituacao = Util.converterStringParaInteger(idFiltroCobrancaSituacao);
						helper.setSituacaoCobranca(idCobrancaSituacao);

						FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
						filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.ID,idCobrancaSituacao));
						Collection<CobrancaSituacao> colecaoCobrancaSituacao = this.getFachada().pesquisar(
								filtroCobrancaSituacao, CobrancaSituacao.class.getName());
						CobrancaSituacao cobrancaSituacao = (CobrancaSituacao)Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
						helper.setDescricaoSituacaoCobranca(cobrancaSituacao.getDescricao());
					}
					colecaoParametrosPerdasItensFiscais.add(helper);
				}
				parametrosPerdasFiscaisHelper.setCollParametrosPerdasFiscaisItensHelper(colecaoParametrosPerdasItensFiscais);
				sessao.setAttribute("parametrosPerdasFiscaisHelper", parametrosPerdasFiscaisHelper);


			}else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o número de meses");
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o valor limite");
		}
		
	}

	private void removerSituacaoSelecionada(
			ParametrosPerdasFiscaisActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest, ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper) {

		Collection<ParametrosPerdasFiscaisItensHelper> colecaoParametrosPerdasItensFiscais = parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper();

		BigDecimal valorLimite = Util.formatarMoedaRealparaBigDecimal(httpServletRequest.getParameter("idParametroRemover"));

		if (!Util.isVazioOrNulo(colecaoParametrosPerdasItensFiscais)){ 
			for (ParametrosPerdasFiscaisItensHelper parametrosPerdasFiscaisItensHelper : colecaoParametrosPerdasItensFiscais){
				BigDecimal valorLimiteColl = parametrosPerdasFiscaisItensHelper.getValorLimite();
				if(valorLimite.compareTo(valorLimiteColl) == 0){
					colecaoParametrosPerdasItensFiscais.remove(parametrosPerdasFiscaisItensHelper);
					break;
				}
			}

			parametrosPerdasFiscaisHelper.setCollParametrosPerdasFiscaisItensHelper(colecaoParametrosPerdasItensFiscais);
			sessao.setAttribute("parametrosPerdasFiscaisHelper", parametrosPerdasFiscaisHelper);
		}
	}


	private ParametrosPerdasFiscaisHelper pesquisarParametroInformado(
			ParametrosPerdasFiscaisActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest, ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper) {

		Fachada fachada = Fachada.getInstancia();

		boolean referenciaInvalida = Util.validarAnoMesInvalido(form.getAnoMesReferenciaContabil(),"MM/yyyy");
		if(referenciaInvalida){
			throw new ActionServletException("atencao.referencia_invalida");
		}
		
		if(form.getAnoMesReferenciaContabil() != null && !form.getAnoMesReferenciaContabil().trim().equals("")){
			if(fachada.pesquisarAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil())) != null
					&& !fachada.pesquisarAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()))){

				parametrosPerdasFiscaisHelper.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
				parametrosPerdasFiscaisHelper = fachada.pesquisarParametrosPerdasFiscais(parametrosPerdasFiscaisHelper);
				form.setValorABaixar(Util.formatarBigDecimalParaStringComVirgula(parametrosPerdasFiscaisHelper.getValorABaixar()));
				
				//Adicionar o ponto decimal no valor a baixar
				if (!form.getValorABaixar().contains(",")) {
					form.setValorABaixar(form.getValorABaixar() + ",00");
				}
				
				else if (form.getValorABaixar().indexOf(",") == form.getValorABaixar().length()-2){
					form.setValorABaixar(form.getValorABaixar() + "0");
				}
				
				sessao.setAttribute("parametrosPerdasFiscaisHelper", parametrosPerdasFiscaisHelper);

			} else{
				throw new ActionServletException("atencao.vigencia_nao_existe");
			}
		} else {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Vigência a partir da referência contábil");
		}
		return parametrosPerdasFiscaisHelper;
	}
}

 