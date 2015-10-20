/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 26 de Maio de 2006
**/
public class ExibirConsultarDadosDiariosEloAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarDadosDiariosElo");
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		String idUnidadeNegocio = httpServletRequest.getParameter("idUnidadeNegocio");
		
		String idGerencia = httpServletRequest.getParameter("idGerencia");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
		
		BigDecimal valorTotalGerencia = new BigDecimal(httpServletRequest.getParameter("valorTotalGerencia"));
		BigDecimal valorTotalUNEG = new BigDecimal(httpServletRequest.getParameter("valorTotalUNEG"));
		sessao.setAttribute("valorTotalGerencia", valorTotalGerencia);
		sessao.setAttribute("valorTotalUnidadeNegocio", valorTotalUNEG);		
		sessao.setAttribute("idGerencia", idGerencia);
		sessao.setAttribute("idUnidadeNegocio", idUnidadeNegocio);
		
		if (idGerencia != null && !idGerencia.equals("") && !idGerencia.equals("-1")){
			
			// pesquisar na base a gerencia Regional
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional ();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,
					idGerencia));
			
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());
			
			GerenciaRegional gerenciaRegional = (GerenciaRegional) 
				Util.retonarObjetoDeColecao(colecaoGerenciaRegional); 
			sessao.setAttribute("nomeGerencia", gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome());
		} else if (idGerencia.equals("-1")){
			sessao.setAttribute("nomeGerencia", "TODAS");
		} else {
			sessao.removeAttribute("nomeGerencia");
		}
		
		if (idUnidadeNegocio != null && !idUnidadeNegocio.equals("") && 
			!idUnidadeNegocio.equals("-1")){
			
			//pesquisar na base a gerencia
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,
					idUnidadeNegocio));
			
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());
			
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) 
				Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			sessao.setAttribute("nomeUnidadeNegocio",unidadeNegocio.getNome());									
		} else if (idUnidadeNegocio.equals("-1")){
			sessao.setAttribute("nomeUnidadeNegocio","TODAS");
		} else {
			sessao.removeAttribute("nomeUnidadeNegocio");
		}
		
		sessao.setAttribute("referencia", referencia);
		
		Integer anoMesAnterior = Util.subtrairMesDoAnoMes(new Integer(referencia).intValue(), 1);
		BigDecimal faturamentoCobradoEmConta = fachada.pesquisarFaturamentoCobradoEmConta(anoMesAnterior);
		sessao.setAttribute("faturamentoCobradoEmConta", Util.formatarMoedaReal(faturamentoCobradoEmConta));
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.ELO);
			filtro.setIdGerenciaRegional(idGerencia);
			filtro.setIdUnidadeNegocio(idUnidadeNegocio);
			filtro.setAnoMesArrecadacao(referencia);
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
			
	    	BigDecimal valorTotal = new BigDecimal(0.0);

	    	// variaveis para gerar a linha de TODOS para os ELOs
       		BigDecimal totalDebitos = new BigDecimal(0.0);
    		BigDecimal totalDescontos = new BigDecimal(0.0);
    		BigDecimal totalArrecadacao = new BigDecimal(0.0);
    		BigDecimal totalDevolucoes = new BigDecimal(0.0);
    		BigDecimal totalArrecadacaoLiquida = new BigDecimal(0.0);   	    	
	    	
	    	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
	    		mapDadosDiariosAnoMes.get(new Integer(referencia));
	    	
	    	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
	    		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
	    		
        		totalDebitos = totalDebitos.add(helper.getValorDebitos());
        		totalDescontos = totalDescontos.add(helper.getValorDescontos());
        		totalArrecadacao = totalArrecadacao.add(helper.getValorArrecadacao());
        		totalDevolucoes = totalDevolucoes.add(helper.getValorDevolucoes());
        		totalArrecadacaoLiquida = totalArrecadacaoLiquida.add(helper.getValorArrecadacaoLiquida());            		
	    		
	    	}

	    	// criando helper para ser incluido como um ultimo item da lista 
	    	Localidade elo = new Localidade();
    		elo.setId(-1);
    		elo.setDescricao("TODOS");
    		
	    	Localidade localidade = new Localidade();
	    	localidade.setLocalidade(elo);
    		
    		FiltrarDadosDiariosArrecadacaoHelper helperTodos = new FiltrarDadosDiariosArrecadacaoHelper();
    		helperTodos.setItemAgrupado(localidade);
    		helperTodos.setValorDebitos(totalDebitos);
    		helperTodos.setValorDescontos(totalDescontos);
    		helperTodos.setValorArrecadacao(totalArrecadacao);
    		helperTodos.setValorDevolucoes(totalDevolucoes);
    		helperTodos.setValorArrecadacaoLiquida(totalArrecadacaoLiquida);
    		helperTodos.setPercentual(new BigDecimal(100.00));
    		
    		colecaoDadosDiarios.add(helperTodos);
    		
    		sessao.setAttribute("colecaoDadosDiarios", colecaoDadosDiarios);	        
    		sessao.setAttribute("valorTotal", valorTotal);	   
    		
    		Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(new Integer(referencia));
     		Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());

     		
     		if(dataMesInformado!=null){ 			
     			sessao
 					.setAttribute("dadosMesInformado", 
 						Util.formatarDataComHora(dataMesInformado));
     		} else {
     			sessao.removeAttribute("dadosMesInformado");
     		}
     		
     		if(dataAtual!=null){   			
     			sessao
 					.setAttribute("dadosAtual", 
 						Util.formatarDataComHora(dataAtual));
     		} else {
     			sessao.removeAttribute("dadosAtual");
     		}
			
		} else {
 			sessao.removeAttribute("colecaoDadosDiarios");
 			sessao.removeAttribute("valorTotal");
 			sessao.removeAttribute("dadosMesInformado");
 			sessao.removeAttribute("dadosAtual");
		}
		
		
//		Collection colecaoArrecadacaoDadosDiariosElo = new ArrayList();
//		
//		sessao.removeAttribute("colecaoDadosDiarios");
//		sessao.removeAttribute("valorTotal");
//		sessao.removeAttribute("valorTotalElo");
//		
//        Collection colecaoArrecadacaoDadosDiarios = (Collection) sessao
//		.getAttribute("colecaoArrecadacaoDadosDiarios");
//
//        Iterator iteratorColecaoDadosDiarios = colecaoArrecadacaoDadosDiarios.iterator();
//        
//        ArrecadacaoDadosDiarios arrecadacaoDadosDiarios = null;
//        
//        BigDecimal valorTotal = new BigDecimal("0.00");
//        
//        Collection colecaoIdLocalidade = new ArrayList();
//        
//        ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_ELO);
//        
//        colecaoArrecadacaoDadosDiariosElo = acumuladorHelper.aplicarFiltroEAcumularValores(        		
//        		colecaoArrecadacaoDadosDiarios, 
//        		referencia, null, null, idGerencia, idUnidadeNegocio, null, null,
//        		null, null, null, null, false, false, false, false, false);
//        
//		if(idUnidadeNegocio != null){
//			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
//
//			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
//					FiltroUnidadeNegocio.ID,
//					idUnidadeNegocio));
//			
//			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
//					UnidadeNegocio.class.getName());
//			
//			if (colecaoUnidadeNegocio != null
//					&& !colecaoUnidadeNegocio.isEmpty()){
//				
//				UnidadeNegocio unidadeNegocio = (UnidadeNegocio) ((List) colecaoUnidadeNegocio).get(0);
//				
//				if (unidadeNegocio.getNome() != null
//						&& !unidadeNegocio.getNome().equals("")) {
//					httpServletRequest.setAttribute("nomeUnidadeNegocio",unidadeNegocio.getNome());
//				}
//			}
//		}
//		
//		
//		if(colecaoArrecadacaoDadosDiariosElo != null && !colecaoArrecadacaoDadosDiariosElo.isEmpty()){
//			
//			ArrecadacaoDadosDiariosItemAcumuladorHelper arrecadacaoDadosDiariosGerencia 
//				= (ArrecadacaoDadosDiariosItemAcumuladorHelper) colecaoArrecadacaoDadosDiariosElo.iterator().next();
//			
//			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
//
//			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
//					FiltroGerenciaRegional.ID,
//					arrecadacaoDadosDiariosGerencia.getGerenciaRegional().getId()));
//			
//			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
//					GerenciaRegional.class.getName());
//			
//			if (colecaoGerenciaRegional != null
//					&& !colecaoGerenciaRegional.isEmpty()){
//				
//				GerenciaRegional gerenciaRegional = (GerenciaRegional) ((List) colecaoGerenciaRegional).get(0);
//				
//				if (gerenciaRegional.getNome() != null
//						&& !gerenciaRegional.getNome().equals("")) {
//					httpServletRequest.setAttribute("nomeGerencia",gerenciaRegional.getNome());
//					httpServletRequest.setAttribute("idGerencia",gerenciaRegional.getId().toString());
//				}
//			}
//		}
//		
//		
//        
//        valorTotal = acumuladorHelper.getValorLiquidoTotal(); 		
//		
//		if(colecaoArrecadacaoDadosDiariosElo != null){
//			sessao.setAttribute("colecaoDadosDiarios",colecaoArrecadacaoDadosDiariosElo);
//			sessao.setAttribute("valorTotalUnidadeNegocio",valorTotal);
//			sessao.setAttribute("valorTotalElo",valorTotal);
//			//sessao.setAttribute("valorTotalGerencia",valorTotalGerencia);
//			
//		}
		
		return retorno;
	}
}