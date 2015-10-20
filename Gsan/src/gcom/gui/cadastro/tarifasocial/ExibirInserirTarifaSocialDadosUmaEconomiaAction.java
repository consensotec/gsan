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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialDadosUmaEconomiaAction extends GcomAction {
	
	
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("inserirTarifaSocialUmaEconomia");

        // Instancia da Fachada
        Fachada fachada = Fachada.getInstancia();

        //Pega uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Pega uma instancia do actionform
        DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

        String valorConfirmacao = httpServletRequest.getParameter("confirmado");
        
        if (valorConfirmacao == null || valorConfirmacao.equals("")) {
	        
	        //Pega o id do imovel selecionado
	        String idImovel = (String) inserirTarifaSocialActionForm
	                .get("idImovel");
	        
	        Collection colecaoTarifaSocialDadoEconomia = null;
	
	        //Faz a verifica��o de [FS00004]
	        fachada.verificarProprietarioImovel(new Integer(idImovel));
	
	        //Faz a verifica��o de [FS0005]
	        Cliente cliente = fachada.verificarUsuarioImovel(new Integer(idImovel));
	        
			// Realiza uma pesquisa pelos parametros fornecidos
			Collection clienteImoveis = fachada.pesquisarClienteImovelPeloClienteTarifaSocial(cliente.getId());
	        
			Collection colecaoIdImovel = new ArrayList();
			Collection colecaoIdImovelEconomia = new ArrayList();

			//colecao com os ids dos imoveis
	        if(clienteImoveis != null && !clienteImoveis.isEmpty()){
	        	Iterator iteratorClienteImoveis = clienteImoveis.iterator();
	        	
	        	while (iteratorClienteImoveis.hasNext()) {
					ClienteImovel clienteImovel = (ClienteImovel) iteratorClienteImoveis.next();
					
					sessao.setAttribute("clienteImovel", clienteImovel);
					
					colecaoIdImovel.add(clienteImovel.getImovel().getId());	
				}
	        }

	        
	        FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
	        
			filtroClienteImovelEconomia
				.adicionarCaminhoParaCarregamentoEntidade("imovelEconomia.imovelSubcategoria.comp_id.imovel");

			filtroClienteImovelEconomia
				.adicionarParametro(new ParametroSimples("imovelEconomia.imovelSubcategoria.comp_id.imovel.imovelPerfil.id",ImovelPerfil.TARIFA_SOCIAL));
			filtroClienteImovelEconomia
				.adicionarParametro(new ParametroSimples("cliente.id",cliente.getId()));

			
			filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(
					FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO));
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
							ClienteRelacaoTipo.USUARIO));
			
			// Realiza uma pesquisa pelos parametros fornecidos
			Collection clienteImoveisEconomias = fachada.pesquisar(
					filtroClienteImovelEconomia, ClienteImovelEconomia.class.getName());
	        
			//colecao com os ids dos imoveis
	        if(clienteImoveisEconomias != null && !clienteImoveisEconomias.isEmpty()){
	        	
	        	Iterator iteratorClienteImoveisEconomias = clienteImoveisEconomias.iterator();
	        	
	        	while (iteratorClienteImoveisEconomias.hasNext()) {
	        		ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) iteratorClienteImoveisEconomias.next();
					
					colecaoIdImovelEconomia.add(clienteImovelEconomia.getImovelEconomia().getId());	
				}
	        }
	        
	        colecaoTarifaSocialDadoEconomia = fachada.pesquisarTarifaSocialDadoEconomia(
					new Integer(idImovel));
	        
	        if(colecaoIdImovel != null && !colecaoIdImovel.isEmpty()){
	        	
	        	Iterator iteratorColecaoImovel = colecaoIdImovel.iterator();
	        	
	        	while (iteratorColecaoImovel.hasNext()) {
					Integer matriculaImovel = (Integer) iteratorColecaoImovel.next();
					
					// Realiza uma pesquisa pelos parametros fornecidos
					Collection colecaoTarifaSocialDadoEconomiaVerificar = null;
					
					colecaoTarifaSocialDadoEconomiaVerificar = fachada.pesquisarTarifaSocialDadoEconomia(
							matriculaImovel);
					
					if (colecaoTarifaSocialDadoEconomiaVerificar != null && !colecaoTarifaSocialDadoEconomiaVerificar.isEmpty()) {

					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialDadoEconomiaVerificar.iterator().next();

					fachada.verificarClienteMotivoExclusaoRecadastramento(cliente.getId());
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialExclusaoMotivo() != null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo()
										.getIndicadorPermiteRecadastramentoImovel() == 2) {
							throw new ActionServletException(
									"atencao.motivo_exclusao_imovel_nao_permite_recadastramento");
					}
					
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialRevisaoMotivo() == null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo() == null) {

						sessao.setAttribute("prerequisito","");
						
						httpServletRequest.setAttribute("atencao.cliente.usuario.tarifa.social",
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");

						sessao.setAttribute("prerequisito","");
						
						return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
								httpServletRequest, actionMapping,matriculaImovel.toString());
					}else{
						
						if (tarifaSocialDadoEconomia
									.getTarifaSocialExclusaoMotivo() == null
									&& tarifaSocialDadoEconomia
											.getTarifaSocialRevisaoMotivo()
											.getIndicadorPermiteRecadastramento()
											.intValue() == 2) {
							
							sessao.setAttribute("prerequisito","");
							
							httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
							
							sessao.setAttribute("prerequisito","");
							
							return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
									httpServletRequest, actionMapping,matriculaImovel.toString());
						}
					}
					}
				}
	        }
	        
	        if(colecaoIdImovelEconomia != null && !colecaoIdImovelEconomia.isEmpty()){
	        	
	        	Iterator iteratorColecaoIdImovelEconomia = colecaoIdImovelEconomia.iterator();
	        	
	        	while (iteratorColecaoIdImovelEconomia.hasNext()) {
					Integer idImovelEconomia = (Integer) iteratorColecaoIdImovelEconomia.next();
					
					// Realiza uma pesquisa pelos parametros fornecidos
					Collection colecaoTarifaSocialDadoEconomiaVerificar = null;
					
					colecaoTarifaSocialDadoEconomiaVerificar = fachada.pesquisarTarifaSocialDadoEconomiaImovelEconomia(
							idImovelEconomia);
					
					if (colecaoTarifaSocialDadoEconomiaVerificar != null && !colecaoTarifaSocialDadoEconomiaVerificar.isEmpty()) {

					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifaSocialDadoEconomiaVerificar.iterator().next();

					fachada.verificarClienteMotivoExclusaoRecadastramento(cliente.getId());
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialExclusaoMotivo() != null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo()
										.getIndicadorPermiteRecadastramentoImovel() == 2) {
							throw new ActionServletException(
									"atencao.motivo_exclusao_imovel_nao_permite_recadastramento");
					}
					
					
					if (tarifaSocialDadoEconomia
								.getTarifaSocialRevisaoMotivo() == null
								&& tarifaSocialDadoEconomia
										.getTarifaSocialExclusaoMotivo() == null) {

						sessao.setAttribute("prerequisito","");
						
						httpServletRequest.setAttribute("atencao.cliente.usuario.tarifa.social",
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");

						sessao.setAttribute("prerequisito","");
						
						return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
								httpServletRequest, actionMapping, tarifaSocialDadoEconomia.getImovel().getId().toString());
					}else{
						
						if (tarifaSocialDadoEconomia
									.getTarifaSocialExclusaoMotivo() == null
									&& tarifaSocialDadoEconomia
											.getTarifaSocialRevisaoMotivo()
											.getIndicadorPermiteRecadastramento()
											.intValue() == 2) {
							
							sessao.setAttribute("prerequisito","");
							
							httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
							
							sessao.setAttribute("prerequisito","");
							
							return montarPaginaConfirmacaoWizard("atencao.cliente.usuario.tarifa.social",
									httpServletRequest, actionMapping, tarifaSocialDadoEconomia.getImovel().getId().toString());
						}
					}
					}
				}
	        }
	        
	        //[FS0003] - Verificar pr�-requisitos para cadastramento na tarifa
	        // social
	        String[] dados = fachada.verificarPreRequisitosCadastramentoTarifaSocial(new Integer(idImovel));
	        
	        int prerequisito = new Integer(dados[0]).intValue();
	        
	        String parametroMensagem = dados[1];
	        
	        switch (prerequisito) {
	        
	        	//CATEGORIA RESIDENCIAL - E
				case 1:
					sessao.setAttribute("prerequisito","1");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
					
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel_residencial",
							httpServletRequest, actionMapping);
					
				//SUBCATEGORIA CASA DE VERANEIO - E	
				case 2:
					sessao.setAttribute("prerequisito","2");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel.com.subcategorias",
						httpServletRequest, actionMapping, parametroMensagem, "provoca o encerramento","Encerramento");
					
					
				//SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
				case 3:
					sessao.setAttribute("prerequisito","3");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel.com.subcategorias",
						httpServletRequest, actionMapping, parametroMensagem, "exige a tramita��o", "Tramita��o");
					
					
				//PERIL GRANDE CONSUMIDOR	
				case 4:
					sessao.setAttribute("prerequisito","4");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.imovel.perfil.grande_consumidor",
						httpServletRequest, actionMapping);

					
				//LIGACAO DIFERENTE DE CORTADO OU SUPRIMIDO - T	
				case 5:
					sessao.setAttribute("prerequisito","5");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.agua_nao_ligada",
						httpServletRequest, actionMapping);
					
					
				//ANORMAIDADE DE LEITURA - T	
				case 6:
					sessao.setAttribute("prerequisito","6");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.anormalidade.leitura.tarifasocial",
						httpServletRequest, actionMapping, parametroMensagem);
					
					
				//EXISTENCIA DE DEBITOS DO CLIENTE	
				case 7:
					sessao.setAttribute("prerequisito","7");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.apenas.imovel.sem_debitos",
						httpServletRequest, actionMapping);
					
					
				//CONSUMO MEDIO MAIOR QUE 10M3 - E	
				case 8:
					sessao.setAttribute("prerequisito","8");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.imoveis.consumo_medio",
						httpServletRequest, actionMapping, parametroMensagem);
					
				// CONSUMO M�NIMO FIXADO MAIOR QUE 10M3 - E	
				case 9:
					sessao.setAttribute("prerequisito","9");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.imoveis.consumo_minimo_fixado",
						httpServletRequest, actionMapping, parametroMensagem);
					

				default:
					break;
			}
	
	        //Pesquisa na fachada a cole��o
	        Collection colecaoClienteImovel = fachada.pesquisarClienteImovelPeloImovelTarifaSocial(new Integer(idImovel));
	        
	        //Carregando todos os objetos da cole��o de TarifaSocialDadoEconomia 
	        carregarObjetosClienteImovel(clienteImoveis);
	        
	        ClienteImovel clienteImovel = null;
	        
	        Iterator colecaoClienteImovelIterator = colecaoClienteImovel.iterator();
	        
	        while (colecaoClienteImovelIterator.hasNext()) {
	        	
	        	clienteImovel = (ClienteImovel) colecaoClienteImovelIterator.next();
	        	
	        	if (clienteImovel.getClienteRelacaoTipo().getId().toString()
						.equals(ClienteRelacaoTipo.USUARIO.toString())) {
					break;
				}
	        	
	        }
	        
	        //Manda pela sessao o clienteImovel para a p�gina
	        sessao.setAttribute("clienteImovel", clienteImovel);

	        if(colecaoTarifaSocialDadoEconomia != null && !colecaoTarifaSocialDadoEconomia.isEmpty()){
	        	sessao.setAttribute("colecaoTarifaSocialDadoEconomia", colecaoTarifaSocialDadoEconomia);
	        }
	        
	        sessao.setAttribute("prerequisito",null);
	        
        }else{
        	
        	if(sessao.getAttribute("prerequisito") != null ){
        	
        		String prerequisito = (String)sessao.getAttribute("prerequisito");
        		
        		httpServletRequest.setAttribute("numeroRA", inserirTarifaSocialActionForm.get("registroAtendimento"));

        		//[FS0005] Verificar o cliente usu�rio do im�vel
        		if(prerequisito.equals("")){
        			
        			retorno = actionMapping
        				.findForward("exibirTramitarRegistroAtendimentoAction");
        		}else if(prerequisito.equals("1")){
        			//CATEGORIA RESIDENCIAL - E
        			
        			retorno = actionMapping
    				.findForward("exibirEncerrarRegistroAtendimentoAction");
        			
        		}else if(prerequisito.equals("2")){
        			//CATEGORIA RESIDENCIAL - E
        			
        			retorno = actionMapping
    					.findForward("exibirEncerrarRegistroAtendimentoAction");

        			
        		}else if(prerequisito.equals("3")){
        			//SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
        			
        			retorno = actionMapping
    					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("4")){
        			//PERIFL GRANDE CONSUMIDOR
        			retorno = actionMapping
    				.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("5")){
        			//LIGACAO DIFERENTE DE CORTADO OU SUPRIMIDO - T
        			retorno = actionMapping
    					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("6")){
        			//ANORMALIDAE DE LEITURA - T
        			retorno = actionMapping
					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("7")){
        			//EXISTENCIA DE DEBITOS DO CLIENTE - T
        			
        			retorno = actionMapping
					.findForward("exibirTramitarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("8")){

        			//CONSUMO M�DIO MAIOR QUE 10M3 - E
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        		}else if(prerequisito.equals("9")){
				
        			// CONSUMO M�NIMO FIXADO MAIOR QUE 10M3 - E
					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");
        		}else if(prerequisito.equals("10")){
        			
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        		
        		}else if(prerequisito.equals("11")){
        			
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        			
        		}else if(prerequisito.equals("12")){
        			
        			retorno = actionMapping
					.findForward("exibirEncerrarRegistroAtendimentoAction");
        			
        		}else if(prerequisito.equals("13")){
        			
        			retorno = actionMapping
        			.findForward("exibirEncerrarRegistroAtendimentoAction");
    			
    		}
			
        	}
        }
        
        String codigo = httpServletRequest.getParameter("codigo");
        String valor = httpServletRequest.getParameter("valor");
        
        if(codigo != null && !codigo.equals("")){

        	if(codigo.equals("9")){
				sessao.setAttribute("prerequisito","9");
        		
        	}else if(codigo.equals("10")){
				sessao.setAttribute("prerequisito","10");
	
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.tarifa_social.renda_familiar.maior.salario_minimo",
					httpServletRequest, actionMapping,valor);
			
        	}else if(codigo.equals("11")){
				sessao.setAttribute("prerequisito","11");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.valor.consumo_energia",
					httpServletRequest, actionMapping,valor);

        	}else if(codigo.equals("12")){
				sessao.setAttribute("prerequisito","12");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.requisitos",
					httpServletRequest, actionMapping,valor);
        	}else if(codigo.equals("13")){
				sessao.setAttribute("prerequisito","13");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do&action=exibirInserirTarifaSocialDadosUmaEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.requisitos",
					httpServletRequest, actionMapping,valor);
        	}
        	
        	
        }
        
        return retorno;
    }
    
    
    private void carregarObjetosClienteImovel(Collection clienteImoveis){
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	Iterator clienteImoveisIt = clienteImoveis.iterator();
    	
    	while (clienteImoveisIt.hasNext()){
    		ClienteImovel clienteImovelObject =  (ClienteImovel) clienteImoveisIt.next();
    		
    			
   			FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
    			
   			filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples("imovel.id", clienteImovelObject.getImovel().getId()));
			filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialCartaoTipo");
			filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade("rendaTipo");
    	
   			
   			Collection tarifaSocialDadoEconomias = fachada.pesquisar(filtroTarifaSocialDadoEconomia,TarifaSocialDadoEconomia.class.getName());
    			
   			if(tarifaSocialDadoEconomias != null && !tarifaSocialDadoEconomias.isEmpty()){
    			
    			Iterator tarifaSocialDadoEconomiasIt = tarifaSocialDadoEconomias.iterator();
    		
    			while (tarifaSocialDadoEconomiasIt.hasNext()){
    				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaObject = 
    					(TarifaSocialDadoEconomia) tarifaSocialDadoEconomiasIt.next();
	    			
	    			//TarifaSocialCartaoTipo
	    			tarifaSocialDadoEconomiaObject.setTarifaSocialCartaoTipo(
	    					tarifaSocialDadoEconomiaObject.getTarifaSocialCartaoTipo());
	    			
	    			//RendaTipo
	    			tarifaSocialDadoEconomiaObject.setRendaTipo(tarifaSocialDadoEconomiaObject.getRendaTipo());
	    		}
    		
    		}
    	}
    }
}
