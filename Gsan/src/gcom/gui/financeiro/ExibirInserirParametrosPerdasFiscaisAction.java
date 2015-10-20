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
 * [UC1070] Inserir Parametros Perdas Fiscais
 * 
 * @author Ricardo Germinio
 * @date 15/01/2013
 *
 */

public class ExibirInserirParametrosPerdasFiscaisAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
				findForward("exibirInserirParametrosPerdasFiscais");
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

	    
	    
	    
	    if(httpServletRequest.getParameter("adicionarParametroEncontrado") != null &&
				 httpServletRequest.getParameter("adicionarParametroEncontrado").equalsIgnoreCase("S")){
	    	
	    	ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper = null;
	    	if(sessao.getAttribute("parametrosPerdasFiscaisHelper") != null ){
	    		parametrosPerdasFiscaisHelper = (ParametrosPerdasFiscaisHelper)sessao.getAttribute("parametrosPerdasFiscaisHelper");
	    		if(form.getAnoMesReferenciaContabil() != null && !form.getAnoMesReferenciaContabil().equals("")){
	    			boolean referenciaInvalida = Util.validarAnoMesInvalido(form.getAnoMesReferenciaContabil(),"MM/yyyy");
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
	    			
	    		if(form.getValorABaixar() != null && !form.getValorABaixar().equals("")){
	    			BigDecimal valorABaixar = Util.formatarMoedaRealparaBigDecimal(form.getValorABaixar());
	    			if(!(valorABaixar.compareTo(parametrosPerdasFiscaisHelper.getValorABaixar()) == 0)){
	    				throw new ActionServletException("atencao.valor_perdas_fiscais_diferente");
	    			}
	    		}else{
	    			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o Valor das Perdas Fiscais");
	    		}
	    	}else{
	    		parametrosPerdasFiscaisHelper = new ParametrosPerdasFiscaisHelper();
	    		if(form.getAnoMesReferenciaContabil() != null  && !form.getAnoMesReferenciaContabil().equals("")){
	    			boolean referenciaInvalida = Util.validarAnoMesInvalido(form.getAnoMesReferenciaContabil(),"MM/yyyy");
	    			if(referenciaInvalida){
	    				throw new ActionServletException("atencao.referencia_invalida");
	    			}
	    			parametrosPerdasFiscaisHelper.setAnoMesReferenciaContabil(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferenciaContabil()));
	    		} else {
	    			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Vigência a partir da referência contábil");
	    		}
	    		if(form.getValorABaixar() != null && !form.getValorABaixar().equals("") && !form.getValorABaixar().equals("0")){
	    			parametrosPerdasFiscaisHelper.setValorABaixar(Util.formatarMoedaRealparaBigDecimal(form.getValorABaixar()));
	    		} else {
	    			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o Valor máximo das perdas fiscais");
	    		}
	    	}
	    	
	    	
	    	
					
			// --------------- botÃ£o adiciona SituaÃ§Ã£o Encontrada ----------
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

	    		
				// --------------- botÃ£o adiciona SituaÃ§Ã£o Encontrada ----------
				removerSituacaoSelecionada(form,sessao,
						httpServletRequest,parametrosPerdasFiscaisHelper);
	    	}
		}

		return retorno;
	} 

	private void adicionarParametroEncontrado(
			ParametrosPerdasFiscaisActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest,ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper) { 


		if(form.getValorLimite() != null && !form.getValorLimite().trim().equals("") && !form.getValorLimite().equals("0")){
			if(form.getNumeroMeses() != null && !form.getNumeroMeses().trim().equals("") && !form.getNumeroMeses().equals("0")){

				Collection<ParametrosPerdasFiscaisItensHelper> colecaoParametrosPerdasItensFiscais = parametrosPerdasFiscaisHelper.getCollParametrosPerdasFiscaisItensHelper();

				if (!Util.isVazioOrNulo(colecaoParametrosPerdasItensFiscais)){ 

					ParametrosPerdasFiscaisItensHelper helper = new ParametrosPerdasFiscaisItensHelper();
					helper.setValorLimite(Util.formatarMoedaRealparaBigDecimal(form.getValorLimite()));
					helper.setNumeroMeses(new Short(form.getNumeroMeses()));

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

					helper.setValorLimite(Util.formatarMoedaRealparaBigDecimal(form.getValorLimite()));
					helper.setNumeroMeses(new Short(form.getNumeroMeses()));

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
			//	if(httpServletRequest.getParameter("menu") != null){
					sessao.setAttribute("parametrosPerdasFiscaisHelper", parametrosPerdasFiscaisHelper);
		//		}

			}else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o número de meses");
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"o valor limite");
		}
	}

	private void removerSituacaoSelecionada(
			ParametrosPerdasFiscaisActionForm form, HttpSession sessao,
			HttpServletRequest httpServletRequest,ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper) {
			
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
}

 