/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
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
 * Fabíola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Tenório Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corrêa Lima e Silva
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
 * Vinicius de Melo Medeiros
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
package gcom.gui.micromedicao;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.InformarItensContratoServicoHelper;
import gcom.micromedicao.ItemServico;
import gcom.micromedicao.ItemServicoContrato;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1055] Informar Valor de Item de Servico Por Contrato
 * 
 * @author Hugo Leonardo, Mariana Victor
 * @date 22/07/2010, 23/11/2010
 */

public class ExibirInformarItensContratoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInformarItensContratoServicoAction");

			try{
				
			
			ExibirInformarItensContratoServicoActionForm form = 
				(ExibirInformarItensContratoServicoActionForm) actionForm;
	
			// Obt�m a inst�ncia da fachada
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			ArrayList colecaoItemServicoContratoSelecionados =  new ArrayList();
			ArrayList colecaoAditivo =  new ArrayList();
			
			List colecaoHelper = null;
			
			Integer idContratoServico = null;
			
			int posicaoComponente = 0;
			
			if(httpServletRequest.getParameter("menu") != null &&
					httpServletRequest.getParameter("menu").equals("sim")){
				sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
				sessao.removeAttribute("colecaoAditivo");
			
			}
	
			/*sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
			sessao.removeAttribute("colecaoAditivo");
	
			if(sessao.getAttribute("colecaoItemServicoContratoSelecionados") == null ){
	    		
	    		colecaoItemServicoContratoSelecionados =  new ArrayList();
	    			
	    	}else{
	    		colecaoItemServicoContratoSelecionados = (ArrayList) 
	    			sessao.getAttribute("colecaoItemServicoContratoSelecionados");
	    	}
			
			if(sessao.getAttribute("colecaoAditivo") == null ){
	    		
				colecaoAditivo =  new ArrayList();
	    			
	    	}else{
	    		colecaoAditivo = (ArrayList) 
	    			sessao.getAttribute("colecaoAditivo");
	    	}*/
			
			if(sessao.getAttribute("colecaoEmpresa") == null){
				this.pesquisarEmpresa(sessao, form);
			}
			
			if(sessao.getAttribute("colecaoItemServico") == null){
				this.pesquisaItemServico(sessao, form);
			}
			
			if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")){
		
				if(sessao.getAttribute("colecaoHelper") == null){
					
					colecaoHelper = new ArrayList();
	
					colecaoHelper = fachada.obterDadosItensContratoServico( 
							new Integer(form.getIdEmpresa()));
	
					if(colecaoHelper != null && colecaoHelper.size() > 0){
						sessao.setAttribute("colecaoHelper", colecaoHelper);
					}
				}else{
					colecaoHelper = (List) sessao.getAttribute("colecaoHelper");
				}
			}else {
				sessao.removeAttribute("colecaoHelper");
				form.getReset();
			}
			
			
			// Verifica se o usu�rio adicionou um novo aditivo
			if(httpServletRequest.getParameter("acao") != null && 
		        	httpServletRequest.getParameter("acao").equals("adicionarAditivo") &&
		        	form.getIdEmpresa() != null &&
		        	!form.getIdEmpresa().equals("-1") && 
		        	(form.getIdNumeroContrato() != null || !form.getIdNumeroContrato().equals("") )){
				
				idContratoServico = new Integer(form.getIdNumeroContrato());
				
			}
			
			// bot�o desfazer
	        if ( httpServletRequest.getParameter("desfazer") != null &&
	        		httpServletRequest.getParameter("desfazer").equals("S") ) {
	
	        	//form.setIdNumeroContrato("");
	        	if(form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equalsIgnoreCase("")){
	    			
		        	posicaoComponente = new Integer(form.getIdNumeroContrato());
	        		
	        		InformarItensContratoServicoHelper helper = null;
	        		
	        		colecaoHelper = fachada.obterDadosItensContratoServico( 
							new Integer(form.getIdEmpresa()));
	        		
	        		if(colecaoHelper != null){
		    			
	        			helper = (InformarItensContratoServicoHelper) colecaoHelper.get(posicaoComponente-1);
		    			
		    			// N�mero do contrato
		    			form.setIdContrato(helper.getContratoEmpresaServico().getDescricaoContrato());
		    			
		    			// Data Inicio do Contrato
		    			String dataInicial = Util.formatarData(helper.getContratoEmpresaServico().getDataInicioContrato());
		    			form.setDtInicioContrato(dataInicial);
		    			
		    			// Data Fim do Contrato
		    			if(helper.getContratoEmpresaServico().getDataFimContrato() != null){
		    				
		    				String dataFinal = Util.formatarData(helper.getContratoEmpresaServico().getDataFimContrato());
		    				form.setDtFimContrato(dataFinal);
		    			}else{
		    				form.setDtFimContrato("");
		    			}
		    			
		    			// Observa��o
		    			if (helper.getContratoEmpresaServico().getObservacao() != null) {
		    				form.setObservacao(helper.getContratoEmpresaServico().getObservacao());
		    			} else {
		    				form.setObservacao("");
		    			}
		    			
		    			sessao.setAttribute("colecaoHelper", colecaoHelper);
	        		}else{
	        			
	        			form.setIdItemContrato("-1");
	        			form.setIdContrato("");
	    				form.setDtInicioContrato("");
	    				form.setDtFimContrato("");
	    				form.setValorItemContrato("");
	    				form.setObservacao("");
	    				sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
	    				sessao.removeAttribute("colecaoAditivo");
	    				sessao.removeAttribute("contratoSelecionado");
	    				sessao.removeAttribute("colecaoHelper");
	        		}
	    			
	        		form.setPercentualServicosNaoExecutados("");
		    		form.setPercentualMultaAplicar("");
		    		form.setQuantidadeOrcadaItemContrato("");
		    		form.setValorOrcadoItemContrato("");
	    			form.setValorItemContrato("");
	    			form.setIdItemContrato("-1");
	    			form.setIdNumeroContrato("");
					form.setObservacao("");
	    			//sessao.setAttribute("colecaoHelper", colecaoHelper);
	    			sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
					sessao.removeAttribute("colecaoAditivo");
	        	}else{
	        		
	        		if(form.getIdEmpresa() != null && !form.getIdEmpresa().equals("")){
	        			
	        			colecaoHelper = fachada.obterDadosItensContratoServico( 
	    						new Integer(form.getIdEmpresa()));
	        			
	        			if(colecaoHelper != null && colecaoHelper.size() > 0){
	    					sessao.setAttribute("colecaoHelper", colecaoHelper);
	    				}else{
	    					sessao.removeAttribute("colecaoHelper");
	    					sessao.removeAttribute("contratoSelecionado");
	    				}
	        			
	        		}else{
	        			colecaoHelper = null;
	        		}
	
					form.setIdEmpresa("");
	
	    			form.setIdItemContrato("-1");
	    			form.setIdContrato("");
					form.setDtInicioContrato("");
					form.setDtFimContrato("");
					form.setValorItemContrato("");
					form.setPercentualServicosNaoExecutados("");
		    		form.setPercentualMultaAplicar("");
		    		form.setQuantidadeOrcadaItemContrato("");
		    		form.setValorOrcadoItemContrato("");
					form.setObservacao("");
					sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
					sessao.removeAttribute("colecaoAditivo");
					//sessao.removeAttribute("contratoSelecionado");
	        	}
			}
			
			// caso selecione um contrato
			if(form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equalsIgnoreCase("")
					&& (httpServletRequest.getParameter("id") != null || idContratoServico != null)){
				
				sessao.setAttribute("contratoSelecionado", true);
				
				InformarItensContratoServicoHelper helper = null;
				
				if (idContratoServico != null)
					posicaoComponente = idContratoServico;
				else
					posicaoComponente = new Integer(form.getIdNumeroContrato());
					
				helper = (InformarItensContratoServicoHelper) colecaoHelper.get(posicaoComponente-1);
				
				// N�mero do contrato
				form.setIdContrato(helper.getContratoEmpresaServico().getDescricaoContrato());
				
				// Data Inicio do Contrato
				String dataInicial = Util.formatarData(helper.getContratoEmpresaServico().getDataInicioContrato());
				form.setDtInicioContrato(dataInicial);
				
				if (helper.getContratoEmpresaServico().getValorGlobalContrato() != null) {
					String valorGlobal = Util.formataBigDecimal(helper.getContratoEmpresaServico().getValorGlobalContrato(),2,true);
					form.setValorGlobalContrato(valorGlobal);
					sessao.setAttribute("valorGlobContrato", true);
				} else {
					form.setValorGlobalContrato(null);
					sessao.removeAttribute("valorGlobContrato");
				}
				
				if (helper.getContratoEmpresaServico().getPercentualTaxaSucesso() != null) {
					String percentualTaxaSucesso = Util.formataBigDecimal(helper.getContratoEmpresaServico().getPercentualTaxaSucesso(),2,true);
	
					
					form.setPercentualTaxaSucesso(percentualTaxaSucesso);
					sessao.setAttribute("percTxSucesso", true);
				} else {
					form.setPercentualTaxaSucesso(null);
					sessao.removeAttribute("percTxSucesso");
				}
				
				// Observa��o
				if (helper.getContratoEmpresaServico().getObservacao() != null
						&& !helper.getContratoEmpresaServico().getObservacao().equals("")) {
					form.setObservacao(helper.getContratoEmpresaServico().getObservacao());
				} else {
					form.setObservacao("");
				}
				
				// Data Fim do Contrato
				if(helper.getContratoEmpresaServico().getDataFimContrato() != null){
					
					String dataFinal = Util.formatarData(helper.getContratoEmpresaServico().getDataFimContrato());
					form.setDtFimContrato(dataFinal);
					sessao.setAttribute("dataFimContrato", true);
				}else{
					form.setDtFimContrato(null);
					sessao.removeAttribute("dataFimContrato");
				}
				
				// Exibe os itens de servi�o do contrato
				if(sessao.getAttribute("colecaoHelper") != null
						&& (httpServletRequest.getParameter("id") != null || idContratoServico != null)){
					
					if (colecaoHelper != null){
						
						if (colecaoHelper.size() >= posicaoComponente) {
							
							if(helper.getItemServicoContrato() != null && helper.getItemServicoContrato().size() > 0){
								
								Iterator iterator = helper.getItemServicoContrato().iterator();
								ItemServicoContrato itemServicoContrato = null;
								
								while (iterator.hasNext()) {
									
									itemServicoContrato = (ItemServicoContrato) iterator.next();
									colecaoItemServicoContratoSelecionados.add(itemServicoContrato);
								}
								
								// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
								Collections.sort((List) colecaoItemServicoContratoSelecionados,
										new Comparator() {
											public int compare(Object a, Object b) {
												String codigo1 = ((ItemServicoContrato) a)
														.getItemServico().getDescricao();
												String codigo2 = ((ItemServicoContrato) b)
														.getItemServico().getDescricao();
												if (codigo1 == null || codigo1.equals("")) {
													return -1;
												} else {
													return codigo1.compareTo(codigo2);
												}
											}
										});
	
								sessao.setAttribute("colecaoItemServicoContratoSelecionados", 
											colecaoItemServicoContratoSelecionados);
								
							}else{
								sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
							}
							
							//Exibe os Aditivos do contrato						
							if (helper.getContratoEmpresaAditivo() != null && helper.getContratoEmpresaAditivo().size() > 0) {
								Iterator iterator = helper.getContratoEmpresaAditivo().iterator();
								ContratoEmpresaAditivo contratoEmpresaAditivo = null;
								
								while (iterator.hasNext()) {
									
									contratoEmpresaAditivo = (ContratoEmpresaAditivo) iterator.next();
									colecaoAditivo.add(contratoEmpresaAditivo);
								}
								
								sessao.setAttribute("colecaoAditivo", 
										colecaoAditivo);
							} else {
								sessao.removeAttribute("colecaoAditivo");
							}
						}
					}
					
				}
				
			}else{
				sessao.removeAttribute("contratoSelecionado");
			}
			
			// Adicionar um novo Contrato
			if(httpServletRequest.getParameter("acao") != null && 
		        	httpServletRequest.getParameter("acao").equals("adicionar") &&
		        	form.getIdEmpresa() != null &&
		        	!form.getIdEmpresa().equals("-1") && 
		        	(form.getIdNumeroContrato() == null || form.getIdNumeroContrato().equals("") )&&
		        	!form.getIdContrato().equals("") && !form.getDtInicioContrato().equals("") && 
		        	!form.getIdItemContrato().equals("-1") && !form.getValorItemContrato().equals("") ){
	
				if(sessao.getAttribute("colecaoHelper") == null){
					
					colecaoHelper = new ArrayList();
					sessao.setAttribute("colecaoHelper", colecaoHelper);
				}
	
				if(sessao.getAttribute("colecaoHelper") != null){
					
					InformarItensContratoServicoHelper helper = new InformarItensContratoServicoHelper();
					
					colecaoItemServicoContratoSelecionados.clear();
					colecaoAditivo.clear();
					
					// Pesquisar Empresa
					Empresa empresa = this.pesquisarEmpresaSelecionada(Integer.parseInt(form.getIdEmpresa()));
					
					// Pesquisar Contrato Empresa Servico
					ContratoEmpresaServico contratoEmpresaServico = new ContratoEmpresaServico();
					
					// Verificar Data
					this.verificarData(form.getDtInicioContrato(), form.getDtFimContrato());
					
					// Verificar se pode adicionar itens
					this.verificarPodeAdicionarItem(form.getDtInicioContrato(), form.getDtFimContrato());
					
					contratoEmpresaServico.setEmpresa(empresa);
					contratoEmpresaServico.setDescricaoContrato(form.getIdContrato());
		    		
	    			// Observa��o
	    			if (form.getObservacao() != null) {
	    				contratoEmpresaServico.setObservacao(form.getObservacao());
	    			} else {
	    				contratoEmpresaServico.setObservacao(null);
	    			}
	    			
					Date DataInicio = Util.converteStringParaDate(form.getDtInicioContrato());
					contratoEmpresaServico.setDataInicioContrato(DataInicio);
					
					if(form.getDtFimContrato() != null && !form.getDtFimContrato().equals("")){
						
						Date DataFim = Util.converteStringParaDate(form.getDtFimContrato());
						contratoEmpresaServico.setDataFimContrato(DataFim);
					}
					
					contratoEmpresaServico.setUltimaAlteracao( new Date());
	
					// Valida��o do valor do Item do Contrato
		    		BigDecimal igualZero = new BigDecimal(0);
		    		
		    		if (form.getValorGlobalContrato() != null && !form.getValorGlobalContrato().equals("")) {
			    		BigDecimal valorGlobalContrato = Util.formatarMoedaRealparaBigDecimal(form.getValorGlobalContrato());
			    		if (valorGlobalContrato.compareTo(igualZero) <= -1){
			    			throw new ActionServletException("atencao.invalido_zero", null ,"O Valor Global do Contrato" );	
			    		}
			    		contratoEmpresaServico.setValorGlobalContrato(valorGlobalContrato);
		    		}
		    		if (form.getPercentualTaxaSucesso() != null
							&& !form.getPercentualTaxaSucesso()
									.equals("")) {
						
						BigDecimal percentualTaxaSucessoAtual = null;
	
						String percentualTaxaSucesso = form.getPercentualTaxaSucesso()
								.toString().replace(".", "");
	
						percentualTaxaSucesso = percentualTaxaSucesso.replace(",", ".");
						percentualTaxaSucessoAtual = new BigDecimal(percentualTaxaSucesso);
	
						contratoEmpresaServico
								.setPercentualTaxaSucesso(percentualTaxaSucessoAtual);
	
					}
		    		
					// Pesquisar Item Servico Contrato
					ItemServicoContrato itemServicoContrato = new ItemServicoContrato();
					
					ItemServico itemServico = this.pesquisaItemServicoSelecionado( 
							new Integer(form.getIdItemContrato()));
					
					BigDecimal valorItem = Util.formatarMoedaRealparaBigDecimal(form.getValorItemContrato());
					
	
		    		if (valorItem.compareTo(igualZero) <= -1){
		    			throw new ActionServletException("atencao.invalido_zero", null ,"O Valor do Item" );	
		    		}
		    		
		    		// Valida��o do percentual de servi�os n�o executados
		    		BigDecimal percentualServicosNaoExecutados = null;
		    		try{
			    		if(Util.verificarNaoVazio(form.getPercentualServicosNaoExecutados())){
			    			percentualServicosNaoExecutados = Util.formatarMoedaRealparaBigDecimal(form.getPercentualServicosNaoExecutados());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Percentual de servi�os n�o executados");
					}
		    		
		    		// Valida��o do percentual de servi�os n�o executados
		    		BigDecimal percentualMultaSerAplicada = null;
		    		try{
			    		if(Util.verificarNaoVazio(form.getPercentualMultaAplicar())){
			    			percentualMultaSerAplicada = Util.formatarMoedaRealparaBigDecimal(form.getPercentualMultaAplicar());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Percentual de multa a ser aplicada");
					}

		    		// Valida��o de quantidade or�ada para o item de contrato
		    		BigDecimal quantidadeOrcada = null;
		    		try{
			    		if(Util.verificarNaoVazio(form.getQuantidadeOrcadaItemContrato())){
			    			quantidadeOrcada = Util.formatarMoedaRealparaBigDecimal(form.getQuantidadeOrcadaItemContrato());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Quantidade or�ada para o item de contrato");
					}
		    		
		    		// Valida��o do valor or�ado para o item de contrato
		    		BigDecimal valorOrcado = null;
		    		try{
		    			if(Util.verificarNaoVazio(form.getValorOrcadoItemContrato())){
			    			valorOrcado = Util.formatarMoedaRealparaBigDecimal(form.getValorOrcadoItemContrato());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Valor or�ado para o item de contrato");
					}
		    		
		    		Collection colecaoTiposServico = (Collection)sessao.getAttribute("colecaoTiposServico");
		    		
		    		if(colecaoTiposServico != null && !colecaoTiposServico.isEmpty()){
		    			itemServicoContrato.setServicoTipos(colecaoTiposServico);
		    		}
		    			    		
		    		itemServicoContrato.setPercentualServicosNaoExecutados(percentualServicosNaoExecutados);
		    		itemServicoContrato.setPercentualMultaSerAplicada(percentualMultaSerAplicada);
		    		itemServicoContrato.setQuantidadeOrcadaItemContrato(quantidadeOrcada);
		    		itemServicoContrato.setValorOrcadoItemContrato(valorOrcado);
		    		
					itemServicoContrato.setItemServico(itemServico);
					itemServicoContrato.setValor(valorItem);
					itemServicoContrato.setContratoEmpresaServico(contratoEmpresaServico);
					itemServicoContrato.setUltimaAlteracao( new Date());
					
					helper.setContratoEmpresaServico(contratoEmpresaServico);
					
					colecaoItemServicoContratoSelecionados.add(itemServicoContrato);
					
					// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
					Collections.sort((List) colecaoItemServicoContratoSelecionados,
							new Comparator() {
								public int compare(Object a, Object b) {
									String codigo1 = ((ItemServicoContrato) a)
											.getItemServico().getDescricao();
									String codigo2 = ((ItemServicoContrato) b)
											.getItemServico().getDescricao();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
	    		
					helper.setItemServicoContrato((List<ItemServicoContrato>) colecaoItemServicoContratoSelecionados);
					colecaoHelper.add(helper);
					
					// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
					Collections.sort((List) colecaoHelper,
							new Comparator() {
								public int compare(Object a, Object b) {
									String codigo1 = ((InformarItensContratoServicoHelper) a)
											.getContratoEmpresaServico().getDescricaoContrato();
									String codigo2 = ((InformarItensContratoServicoHelper) b)
											.getContratoEmpresaServico().getDescricaoContrato();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
					
					sessao.setAttribute("colecaoHelper", colecaoHelper);
					
					sessao.removeAttribute("colecaoTiposServico");
					
					httpServletRequest.setAttribute("idContrato", form.getIdContrato());
					
					form.setIdContrato("");
					form.setDtInicioContrato("");
					form.setDtFimContrato("");
					form.setValorItemContrato("");
					form.setValorGlobalContrato("");
					form.setPercentualTaxaSucesso("");
	    			form.setIdItemContrato("-1");
					form.setObservacao("");
		    		form.setIdServicoTipo("");
		    		form.setDescricaoServicoTipo("");
		    		form.setPercentualServicosNaoExecutados("");
		    		form.setPercentualMultaAplicar("");
		    		form.setQuantidadeOrcadaItemContrato("");
		    		form.setValorOrcadoItemContrato("");
				}
			}
			
	        // Verifica se o usuario clicou no botao adicionar
	        if ( httpServletRequest.getParameter("acao") != null && 
	        		httpServletRequest.getParameter("acao").equals("adicionar") && form.getIdEmpresa() != null
	        		&& !form.getIdEmpresa().equals("-1") && !form.getIdContrato().equals("") &&
	        		form.getIdNumeroContrato()!= null && !form.getIdNumeroContrato().equals("") 
	        		&& !form.getDtInicioContrato().equals("") && !form.getIdItemContrato().equals("-1") 
	        		&& !form.getValorItemContrato().equals("")) {
	        	
	        	
		        posicaoComponente = new Integer(form.getIdNumeroContrato());
	        	
	        	// Verificar Data
	        	this.verificarData(form.getDtInicioContrato(), form.getDtFimContrato());
	        	
	        	// Verificar se pode adicionar itens
				this.verificarPodeAdicionarItem(form.getDtInicioContrato(), form.getDtFimContrato());
	        	
	        	colecaoItemServicoContratoSelecionados.clear();
	        	colecaoAditivo.clear();
	        	
				if (colecaoHelper.size() >= posicaoComponente) {
					InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper) 
						colecaoHelper.get(posicaoComponente-1);
					
					ItemServico itemServico = this.pesquisaItemServicoSelecionado( 
							new Integer(form.getIdItemContrato()));
		    		
		    		ContratoEmpresaServico contratoEmpresaServico = helper.getContratoEmpresaServico();
		    		colecaoAditivo = (ArrayList) helper.getContratoEmpresaAditivo();
		    		
		    		if(helper.getItemServicoContrato() != null && helper.getItemServicoContrato().size() > 0){
		    			
		    			Iterator iteratorItemServicoContrato = helper.getItemServicoContrato().iterator();
	
			    		// Valida se ja existe itens de contrato
			    		while (iteratorItemServicoContrato.hasNext()) {
			    			
			    			ItemServicoContrato itemServicoContrato = (ItemServicoContrato) iteratorItemServicoContrato.next();
			    			if ( itemServico.getId().intValue() == itemServicoContrato.getItemServico().getId().intValue() ) {
			    				
			    				colecaoItemServicoContratoSelecionados.clear();
			    				
			    				throw new ActionServletException("atencao.item_contrato_ja_informado", 
			    						null ,"Item Contrato" );
			    			} 
			    			colecaoItemServicoContratoSelecionados.add(itemServicoContrato);
			    		}
		    		}
	
		    		// Valida��o do valor do Item de Contrato
		    		BigDecimal valorItemContrato = Util.formatarMoedaRealparaBigDecimal
		    			(form.getValorItemContrato());
		    		
		    		// Valida��o do valor do Item do Contrato
		    		BigDecimal igualZero = new BigDecimal(0);
	
		    		if (valorItemContrato.compareTo(igualZero) <= -1){
		    			
		    			throw new ActionServletException("atencao.invalido_zero", null ,"O Valor do Item" );	
		    		}
		    		
		    		ItemServicoContrato itemServicoContrato = new ItemServicoContrato();
		    		itemServicoContrato.setItemServico(itemServico);
		    		itemServicoContrato.setValor(valorItemContrato);
		    		itemServicoContrato.setContratoEmpresaServico(contratoEmpresaServico);
		    		itemServicoContrato.setUltimaAlteracao(new Date());
		    		
		    		/*if (form.getIdServicoTipo() != null && !form.getIdServicoTipo().equals("")) {
		    			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
	
		    			filtroServicoTipo.adicionarParametro(new ParametroSimples(
		    					FiltroServicoTipo.ID, form.getIdServicoTipo()));
		    			filtroServicoTipo.adicionarParametro(new ParametroSimples(
		    					FiltroServicoTipo.INDICADOR_USO,
		    					ConstantesSistema.INDICADOR_USO_ATIVO));
	
		    			Collection servicoTipoEncontrado = fachada.pesquisar(
		    					filtroServicoTipo, ServicoTipo.class.getName());
	
		    			if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
		    				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
		    				itemServicoContrato.setServicoTipo(servicoTipo);
		    			} else {
		    				itemServicoContrato.setServicoTipo(null);
		    			}
		    		}*/
		    		
		    		Collection colecaoTiposServico = (Collection)sessao.getAttribute("colecaoTiposServico");
		    		
		    		if(colecaoTiposServico != null && !colecaoTiposServico.isEmpty()){
		    			itemServicoContrato.setServicoTipos(colecaoTiposServico);
		    		}
		    		
		    		// Valida��o do percentual de servi�os n�o executados
		    		BigDecimal percentualServicosNaoExecutados = null;
		    		try{
			    		if(Util.verificarNaoVazio(form.getPercentualServicosNaoExecutados())){
			    			percentualServicosNaoExecutados = Util.formatarMoedaRealparaBigDecimal(form.getPercentualServicosNaoExecutados());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Percentual de servi�os n�o executados");
					}
		    		
		    		// Valida��o do percentual de servi�os n�o executados
		    		BigDecimal percentualMultaSerAplicada = null;
		    		try{
			    		if(Util.verificarNaoVazio(form.getPercentualMultaAplicar())){
			    			percentualMultaSerAplicada = Util.formatarMoedaRealparaBigDecimal(form.getPercentualMultaAplicar());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Percentual de multa a ser aplicada");
					}

		    		// Valida��o de quantidade or�ada para o item de contrato
		    		BigDecimal quantidadeOrcada = null;
		    		try{
			    		if(Util.verificarNaoVazio(form.getQuantidadeOrcadaItemContrato())){
			    			quantidadeOrcada = Util.formatarMoedaRealparaBigDecimal(form.getQuantidadeOrcadaItemContrato());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Quantidade or�ada para o item de contrato");
					}
		    		
		    		// Valida��o do valor or�ado para o item de contrato
		    		BigDecimal valorOrcado = null;
		    		try{
		    			if(Util.verificarNaoVazio(form.getValorOrcadoItemContrato())){
			    			valorOrcado = Util.formatarMoedaRealparaBigDecimal(form.getValorOrcadoItemContrato());
			    		}
		    		}catch (NumberFormatException e) {
		    			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "Valor or�ado para o item de contrato");
					}
		    		
		    		itemServicoContrato.setPercentualServicosNaoExecutados(percentualServicosNaoExecutados);
		    		itemServicoContrato.setPercentualMultaSerAplicada(percentualMultaSerAplicada);
		    		itemServicoContrato.setQuantidadeOrcadaItemContrato(quantidadeOrcada);
		    		itemServicoContrato.setValorOrcadoItemContrato(valorOrcado);
		    		
		    		colecaoItemServicoContratoSelecionados.add(itemServicoContrato);
			
		    		// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
					Collections.sort((List) colecaoItemServicoContratoSelecionados,
							new Comparator() {
								public int compare(Object a, Object b) {
									String codigo1 = ((ItemServicoContrato) a)
											.getItemServico().getDescricao();
									String codigo2 = ((ItemServicoContrato) b)
											.getItemServico().getDescricao();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
	
		    		helper.setItemServicoContrato((List<ItemServicoContrato>) colecaoItemServicoContratoSelecionados);
		    		helper.setContratoEmpresaServico(contratoEmpresaServico);
		    		helper.setContratoEmpresaAditivo(colecaoAditivo);
		    		
		    		colecaoHelper.remove(posicaoComponente-1);
		    		colecaoHelper.add(helper);
		    		
		    		// o sistema classifica a lista de InformarItensContratoServicoHelper recebidas por Empresa
					Collections.sort((List) colecaoHelper,
							new Comparator() {
								public int compare(Object a, Object b) {
									String codigo1 = ((InformarItensContratoServicoHelper) a)
											.getContratoEmpresaServico().getDescricaoContrato();
									String codigo2 = ((InformarItensContratoServicoHelper) b)
											.getContratoEmpresaServico().getDescricaoContrato();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
		    		
		    		sessao.setAttribute("colecaoHelper", colecaoHelper);
		    		
	    			sessao.setAttribute("colecaoItemServicoContratoSelecionados", 
	    					colecaoItemServicoContratoSelecionados);
	    			sessao.setAttribute("colecaoAditivo", colecaoAditivo);
		    		
	    			sessao.removeAttribute("colecaoTiposServico");
	    			
		    		form.setIdItemContrato("-1");
		    		form.setValorItemContrato("");
		    		form.setIdServicoTipo("");
		    		form.setDescricaoServicoTipo("");
		    		form.setPercentualServicosNaoExecutados("");
		    		form.setPercentualMultaAplicar("");
		    		form.setQuantidadeOrcadaItemContrato("");
		    		form.setValorOrcadoItemContrato("");
		    		
				}
	        }
	    	
	        // Remover Itens do Contrato da Colecao
	        if ( httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("remover") ) {
	        	
	        	posicaoComponente = new Integer(form.getIdNumeroContrato());
	        	
	        	Integer indice = new Integer(httpServletRequest.getParameter("id")).intValue();
	        	
	        	if (colecaoHelper.size() >= posicaoComponente) {
	        		
					InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper) 
						colecaoHelper.get(posicaoComponente-1);
					
					ContratoEmpresaServico contratoEmpresaServico = helper.getContratoEmpresaServico();
					
					colecaoItemServicoContratoSelecionados = (ArrayList) helper.getItemServicoContrato();
					
					// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
					Collections.sort((List) colecaoItemServicoContratoSelecionados,
							new Comparator() {
								public int compare(Object a, Object b) {
									String codigo1 = ((ItemServicoContrato) a)
											.getItemServico().getDescricao();
									String codigo2 = ((ItemServicoContrato) b)
											.getItemServico().getDescricao();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
					
					if (colecaoItemServicoContratoSelecionados != null && 
		        			!colecaoItemServicoContratoSelecionados.isEmpty() && 
		        			colecaoItemServicoContratoSelecionados.size() > 0) {
		        		
		        		if (colecaoItemServicoContratoSelecionados.size() >= indice) {
		            		
		        			colecaoItemServicoContratoSelecionados.remove(indice-1);
		            	}
	
		        		sessao.setAttribute("colecaoItemServicoContratoSelecionados", colecaoItemServicoContratoSelecionados);
	
					} else if (colecaoItemServicoContratoSelecionados.size() <= 0){
						
						throw new ActionServletException("atencao.item_contrato_existente", null ,"Item Contrato" );
					}
					
					helper.setItemServicoContrato((List<ItemServicoContrato>) colecaoItemServicoContratoSelecionados);
					helper.setContratoEmpresaServico(contratoEmpresaServico);
					
					colecaoHelper.remove(posicaoComponente-1);
		    		//colecaoHelper.add(helper); 
		    		colecaoHelper.add(posicaoComponente-1, helper);
		    		
		    		sessao.setAttribute("colecaoHelper", colecaoHelper);
	        	}	
	        }
	        
	        // Remover Aditivos da Colecao
	        if ( httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("removerAditivo") ) {
	        	
	        	posicaoComponente = new Integer(form.getIdNumeroContrato());
	        	
	        	Integer indice = new Integer(httpServletRequest.getParameter("idAditivo")).intValue();
	        	
	        	if (colecaoHelper.size() >= posicaoComponente) {
	        		
					InformarItensContratoServicoHelper helper = (InformarItensContratoServicoHelper) 
						colecaoHelper.get(posicaoComponente-1);
					
					ContratoEmpresaServico contratoEmpresaServico = helper.getContratoEmpresaServico();
					
					colecaoAditivo = (ArrayList) helper.getContratoEmpresaAditivo();
					
					colecaoItemServicoContratoSelecionados = (ArrayList) helper.getItemServicoContrato();
					
					// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
					Collections.sort((List) colecaoItemServicoContratoSelecionados,
							new Comparator() {
								public int compare(Object a, Object b) {
									String codigo1 = ((ItemServicoContrato) a)
											.getItemServico().getDescricao();
									String codigo2 = ((ItemServicoContrato) b)
											.getItemServico().getDescricao();
									if (codigo1 == null || codigo1.equals("")) {
										return -1;
									} else {
										return codigo1.compareTo(codigo2);
									}
								}
							});
					
					sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
					sessao.setAttribute("colecaoItemServicoContratoSelecionados", colecaoItemServicoContratoSelecionados);
					
					if (colecaoAditivo != null && 
		        			!colecaoAditivo.isEmpty() && 
		        			colecaoAditivo.size() > 0) {
		        		
		        		if (colecaoAditivo.size() >= indice) {
		            		
		        			colecaoAditivo.remove(indice-1);
		            	}
	
		        		sessao.removeAttribute("colecaoAditivo");
		        		sessao.setAttribute("colecaoAditivo", colecaoAditivo);
	
						helper.setContratoEmpresaAditivo((List<ContratoEmpresaAditivo>) colecaoAditivo);
						helper.setContratoEmpresaServico(contratoEmpresaServico);
						
						colecaoHelper.remove(posicaoComponente-1);
			    		colecaoHelper.add(helper);
			    		
			    		Collections.sort((List) colecaoHelper,
								new Comparator() {
									public int compare(Object a, Object b) {
										String codigo1 = ((InformarItensContratoServicoHelper) a)
												.getContratoEmpresaServico().getDescricaoContrato();
										String codigo2 = ((InformarItensContratoServicoHelper) b)
												.getContratoEmpresaServico().getDescricaoContrato();
										if (codigo1 == null || codigo1.equals("")) {
											return -1;
										} else {
											return codigo1.compareTo(codigo2);
										}
									}
								});
	
						sessao.setAttribute("contratoSelecionado", true);
						sessao.setAttribute("contratoSelecionado", true);
			    		sessao.setAttribute("colecaoHelper", colecaoHelper);
					}
	        	}	
	        }
	
	        // Pesquisar Tipo de Servi�o
			String idServicoTipo = form.getIdServicoTipo();
			String descricaoServicoTipo = form.getDescricaoServicoTipo();
			ServicoTipo servicoTipo = null;
			if ((idServicoTipo != null && !idServicoTipo.equals(""))
					&& (descricaoServicoTipo == null || descricaoServicoTipo.equals(""))) {
	
				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
	
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, idServicoTipo));
				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
	
				Collection servicoTipoEncontrado = fachada.pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());
	
				if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
					servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
					// O servi�o tipo foi encontrado
					form.setIdServicoTipo(""	+ servicoTipo.getId());
					form.setDescricaoServicoTipo(servicoTipo.getDescricao());
					httpServletRequest.setAttribute("idServicoTipoNaoEncontrado", "true");
	
					httpServletRequest.setAttribute("nomeCampo", "ButtonAtividade");
					sessao.setAttribute("servicoTipo", servicoTipo);
					
				} else {
	
					form.setIdServicoTipo("");
					httpServletRequest.setAttribute("nomeCampo", "idServicoTipo");
					httpServletRequest.setAttribute("idServicoTipoNaoEncontrado", "exception");
					form.setDescricaoServicoTipo("Tipo Servi�o Inexistente");
					sessao.removeAttribute("servicoTipo");
					
				}
				
				// Exibe os itens de servi�o do contrato
				if(sessao.getAttribute("colecaoHelper") != null
						&& (httpServletRequest.getParameter("id") != null || idContratoServico != null)){
					
					sessao.setAttribute("contratoSelecionado", true);
					
					InformarItensContratoServicoHelper helper = null;
					
					if (idContratoServico != null)
						posicaoComponente = idContratoServico;
					else
						posicaoComponente = new Integer(form.getIdNumeroContrato());
						
					helper = (InformarItensContratoServicoHelper) colecaoHelper.get(posicaoComponente-1);
					
					if (colecaoHelper != null){
						
						if (colecaoHelper.size() >= posicaoComponente) {
							
							if(helper.getItemServicoContrato() != null && helper.getItemServicoContrato().size() > 0){
								
								Iterator iterator = helper.getItemServicoContrato().iterator();
								ItemServicoContrato itemServicoContrato = null;
								
								while (iterator.hasNext()) {
									
									itemServicoContrato = (ItemServicoContrato) iterator.next();
									colecaoItemServicoContratoSelecionados.add(itemServicoContrato);
								}
								
								// o sistema classifica a lista de ItemServicoContrato recebidas por contrato
								Collections.sort((List) colecaoItemServicoContratoSelecionados,
										new Comparator() {
											public int compare(Object a, Object b) {
												String codigo1 = ((ItemServicoContrato) a)
														.getItemServico().getDescricao();
												String codigo2 = ((ItemServicoContrato) b)
														.getItemServico().getDescricao();
												if (codigo1 == null || codigo1.equals("")) {
													return -1;
												} else {
													return codigo1.compareTo(codigo2);
												}
											}
										});
	
								sessao.setAttribute("colecaoItemServicoContratoSelecionados", 
											colecaoItemServicoContratoSelecionados);
								
							}else{
								sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
							}
							
							//Exibe os Aditivos do contrato						
							if (helper.getContratoEmpresaAditivo() != null && helper.getContratoEmpresaAditivo().size() > 0) {
								Iterator iterator = helper.getContratoEmpresaAditivo().iterator();
								ContratoEmpresaAditivo contratoEmpresaAditivo = null;
								
								while (iterator.hasNext()) {
									
									contratoEmpresaAditivo = (ContratoEmpresaAditivo) iterator.next();
									colecaoAditivo.add(contratoEmpresaAditivo);
								}
								
								sessao.setAttribute("colecaoAditivo", 
										colecaoAditivo);
							} else {
								sessao.removeAttribute("colecaoAditivo");
							}
						}
					}
					
				}
	
			}
			
			
			// Adicionar Tipo de Servico
	        if ( httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("adicionarTipoServico") ) {
	        
	        	Collection colecaoTiposServico = (Collection)sessao.getAttribute("colecaoTiposServico");
	        	servicoTipo = (ServicoTipo)sessao.getAttribute("servicoTipo");
				
	        	if(colecaoTiposServico == null)
	        		colecaoTiposServico = new ArrayList();
	        	
	        	if(servicoTipo == null){
	        		
	        		//Servico Tipo a partir da lupa
	        		if (idServicoTipo != null && !idServicoTipo.equals("")) {
	    	
	    				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
	    	
	    				filtroServicoTipo.adicionarParametro(new ParametroSimples(
	    						FiltroServicoTipo.ID, idServicoTipo));
	    				filtroServicoTipo.adicionarParametro(new ParametroSimples(
	    						FiltroServicoTipo.INDICADOR_USO,
	    						ConstantesSistema.INDICADOR_USO_ATIVO));
	    	
	    				Collection servicoTipoEncontrado = fachada.pesquisar(
	    						filtroServicoTipo, ServicoTipo.class.getName());
	    	
	    				if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
	    					servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
	    						
	    				}
	      			}
	        	}
	        	
	        	
	        	if(servicoTipo != null){
	        		
		        	ServicoTipo tipoServico = new ServicoTipo();
		        	
		        	tipoServico.setId(servicoTipo.getId());
		        	tipoServico.setDescricao(servicoTipo.getDescricao());
		        	
		        	Iterator it = colecaoTiposServico.iterator();
	        		while(it.hasNext()){
	        			
	        			ServicoTipo st = (ServicoTipo)it.next();
	        			
	        			if(st.getId().compareTo(servicoTipo.getId()) == 0){
	        				throw new ActionServletException("atencao.tipo_servico_ja_informado");
	        			}
	        		}
		        	
	        		colecaoTiposServico.add(tipoServico);
	        		form.setIdServicoTipo("");
					form.setDescricaoServicoTipo("");
	        		
		        	sessao.setAttribute("colecaoTiposServico", colecaoTiposServico);
	        	
	        	}
	        	
	        }
			
			
			 // Remover Tipo de Servico
	        if ( httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("removerTipoServico") ) {
	        	
	        	String id = httpServletRequest.getParameter("id");
	        	
	        	Collection colecaoTiposServico = (Collection)sessao.getAttribute("colecaoTiposServico");
	        	
	        	if(colecaoTiposServico != null && !colecaoTiposServico.isEmpty() 
	        			&& id != null && !id.equals("")){
	        		
	        		Iterator it = colecaoTiposServico.iterator();
	        		while(it.hasNext()){
	        			
	        			ServicoTipo st = (ServicoTipo)it.next();
	        			
	        			if(st.getId().compareTo(new Integer(id)) == 0){
	        				it.remove();
	        				break;
	        			}
	        		}
	        		
	        	}
	        
	        }
			
			
	        
	        this.verificarData(form.getDtInicioContrato(), form.getDtFimContrato());
		}catch (NumberFormatException e) {
			throw new ActionServletException("atencao.gsan.campo_formato_invalido", null, "teste");
		}
		return retorno;
	}

	/**
	 * @param form
	 */
	private void verificarData(String dataInicio, String dataFim) {
		
		// Verifica se a Data Final � maior que a Inicial
        if ( dataInicio != null && !dataInicio.equals("")
        		&& dataFim != null && !dataFim.equals("")){
        	
        	Date dtInicial = Util.converteStringParaDate( dataInicio);
        	Date dtFinal = Util.converteStringParaDate( dataFim);
        	
        	if (Util.compararData(dtFinal, dtInicial) == -1){
        		
        		throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Inv�lida" );
        	}
        }
	}
	
	private void verificarPodeAdicionarItem(String dataInicio, String dataFim) {
		
		// Verifica se a Data Final � maior que a Inicial
        if ( dataInicio != null && !dataInicio.equals("")
        		&& dataFim != null && !dataFim.equals("")){

        	Date dtFinal = Util.converteStringParaDate( dataFim);
        	
        	if (Util.compararData(dtFinal, new Date()) == -1){
        		throw new ActionServletException("atencao.item_contrato_nao_pode_ser_informado", null ,"" );
        	}
        }
	}
	
	private void pesquisarEmpresa(HttpSession sessao,
			ExibirInformarItensContratoServicoActionForm form){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro( new ParametroSimples( FiltroEmpresa.INDICADOR_EMPRESA_PRINCIPAL, 
				new Short("2") ));
		filtroEmpresa.adicionarParametro( new ParametroSimples(
				FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection colecaoEmpresa = 
			this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	private Empresa pesquisarEmpresaSelecionada(Integer idEmpresa){
		
		Empresa retorno = null;
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro( new ParametroSimples( FiltroEmpresa.ID, idEmpresa));		

		Collection colecaoEmpresa = 
			this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
			
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			retorno = empresa;
		}
		
		return retorno;
	}
	
	private void pesquisaItemServico(HttpSession sessao,
			ExibirInformarItensContratoServicoActionForm form){
		
		FiltroItemServico filtroItemServico = new FiltroItemServico();
		
		filtroItemServico.setConsultaSemLimites(true);
		filtroItemServico.adicionarParametro( new ParametroSimples( FiltroItemServico.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroItemServico.setCampoOrderBy(FiltroItemServico.DESCRICAO);		

		Collection colecaoItemServico = 
			this.getFachada().pesquisar(filtroItemServico, ItemServico.class.getName());

		if (colecaoItemServico == null || colecaoItemServico.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Item Servico");
		} else {
			sessao.setAttribute("colecaoItemServico", colecaoItemServico);
		}
	}
	
	private ItemServico pesquisaItemServicoSelecionado(Integer idItemServico){
		
		ItemServico retorno = null;
		
		FiltroItemServico filtroItemServico = new FiltroItemServico();
		filtroItemServico.adicionarParametro( new ParametroSimples( FiltroItemServico.ID, idItemServico));
		
		Collection colecaoItemServico = 
			this.getFachada().pesquisar(filtroItemServico, ItemServico.class.getName());

		if (colecaoItemServico != null && !colecaoItemServico.isEmpty()) {
			ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
			retorno = itemServico;
		} 
		
		return retorno;
	}
}
