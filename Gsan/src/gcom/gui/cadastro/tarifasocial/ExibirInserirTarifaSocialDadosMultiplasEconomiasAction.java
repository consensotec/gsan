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
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

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
public class ExibirInserirTarifaSocialDadosMultiplasEconomiasAction extends
		GcomAction {

	// Instancia da Fachada
	Fachada fachada = Fachada.getInstancia();

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
				.findForward("inserirTarifaSocialMultiplasEconomia");

		String reload = httpServletRequest.getParameter("reload");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Pega uma instancia do actionform
		DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

		String valorConfirmacao = httpServletRequest.getParameter("confirmado");

		if (valorConfirmacao == null || valorConfirmacao.equals("")) {

			// Pega o id do imovel selecionado
			String idImovel = null; 
				
			if (inserirTarifaSocialActionForm
					.get("idImovel") != null && !inserirTarifaSocialActionForm
					.get("idImovel").equals("")) {
			
				idImovel = (String) inserirTarifaSocialActionForm
					.get("idImovel");
				
			} else {
				idImovel = (String) httpServletRequest.getAttribute("idImovelRA");
			}

			// [FS0010] Verificar exist�ncia dos dados por economia
			Integer qtdEconomiasImovel = (Integer) inserirTarifaSocialActionForm
					.get("qtdEconomia");
			Collection imovelEconomias = null;

			if (qtdEconomiasImovel != null) {

//				FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
//				// Objetos que ser�o retornados pelo hibernate
//				// filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel");
//				filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialDadoEconomias");
//				filtroImovelEconomia.adicionarParametro(new ParametroSimples(FiltroImovelEconomia.IMOVEL_ID, idImovel));
//				imovelEconomias = fachada.pesquisar(filtroImovelEconomia,ImovelEconomia.class.getName());
				
				imovelEconomias = fachada.pesquisarImovelEconomia(new Integer(idImovel));
				
				if (imovelEconomias.size() < qtdEconomiasImovel.intValue()) {
					throw new ActionServletException(
							"atencao.imovel.economia_quantidade_invalida");
				}

				// Carregando os objetos que est�o localizados dentro do Set
				carregarObjetosImovelEconomia(imovelEconomias);

			} else {
				throw new ActionServletException(
						"atencao.imovel.economia_quantidade_invalida");
			}
			// FIM - [FS0010] Verificar exist�ncia dos dados por economia
			// ======================================================================

			// [FS0011] - [FS0012] Verificar propriet�rio e o usu�rio de cada
			// economia do imovel (CPF e RG)

			// Interator para percorrer a colecao de ImovelEconomia
			Iterator imovelEconomiaIterator = imovelEconomias.iterator();
			ImovelEconomia imovelEconomiaObj = null;

			FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
			Collection colecaoClienteImovelEconomia = null;
			Iterator clienteImovelEconomiaIterator = null;
			ClienteImovelEconomia clienteImovelEconomiaObj = null;

			Collection colecaoClienteImovel = null;

			FiltroClienteImovelEconomia filtroClienteImovelEconomiaVerificacao = new FiltroClienteImovelEconomia();
			Collection colecaoClienteImovelEconomiaVerificacao = null;

			Collection colecaoClienteImovelEconomiaJSP = new Vector();

			// Flag para indiciar se existe proprietario cadastrado para o
			// imovel
			boolean tipoProprietario = true;

			// Cole��o que ir� auxiliar na identifica��o de usu�rios repetidos
			Collection<Cliente> colecaoClienteUsuario = new ArrayList();

			while (imovelEconomiaIterator.hasNext()) {

				if (!tipoProprietario) {
					throw new ActionServletException(
							"atencao.proprietario.nao_cadastrado_economia");
				}

				filtroClienteImovelEconomia.limparListaParametros();

				imovelEconomiaObj = (ImovelEconomia) imovelEconomiaIterator
						.next();

				// Objetos que ser�o retornados pelo hibernate
				filtroClienteImovelEconomia
						.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");
				filtroClienteImovelEconomia
						.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
				filtroClienteImovelEconomia
						.adicionarCaminhoParaCarregamentoEntidade("imovelEconomia.imovelSubcategoria.comp_id.imovel");
				filtroClienteImovelEconomia
					.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

				filtroClienteImovelEconomia
						.adicionarParametro(new ParametroSimples(
								FiltroClienteImovelEconomia.IMOVEL_ECONOMIA_ID,
								imovelEconomiaObj.getId()));
				filtroClienteImovelEconomia
						.adicionarParametro(new ParametroSimples(
								FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
								ClienteRelacaoTipo.PROPRIETARIO,
								FiltroParametro.CONECTOR_OR, 2));
				filtroClienteImovelEconomia
						.adicionarParametro(new ParametroSimples(
								FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
								ClienteRelacaoTipo.USUARIO));
				filtroClienteImovelEconomia
						.adicionarParametro(new ParametroNulo(
								FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO));

				colecaoClienteImovelEconomia = fachada.pesquisar(
						filtroClienteImovelEconomia,
						ClienteImovelEconomia.class.getName());

				clienteImovelEconomiaIterator = colecaoClienteImovelEconomia
						.iterator();

				// Flag para indiciar se existe proprietario cadastrado para o
				// imovel
				tipoProprietario = false;

				while (clienteImovelEconomiaIterator.hasNext()) {

					clienteImovelEconomiaObj = (ClienteImovelEconomia) clienteImovelEconomiaIterator
							.next();

					if (clienteImovelEconomiaObj.getClienteRelacaoTipo()
							.getId().shortValue() == ClienteRelacaoTipo.PROPRIETARIO
							.shortValue()) {

						tipoProprietario = true;

						if (clienteImovelEconomiaObj.getCliente()
								.getClienteTipo()
								.getIndicadorPessoaFisicaJuridica().equals(
										ClienteTipo.INDICADOR_PESSOA_FISICA)) {

							if ((clienteImovelEconomiaObj.getCliente().getRg() == null || clienteImovelEconomiaObj
									.getCliente().getRg().equals("")) 
									&& clienteImovelEconomiaObj.getCliente()
											.getCpf() == null) {
								throw new ActionServletException(
										"atencao.proprietario.rg_cpf_nao_cadastrado_economia");
							}

						} else {
							if (clienteImovelEconomiaObj.getCliente().getCnpj() == null) {
								throw new ActionServletException(
										"atencao.proprietario.cnpj_nao_cadastrado_economia");
							}
						}

					}
					// [FS0012] - Verificar o cliente usu�rio de cada economia
					// do im�vel
					else if (clienteImovelEconomiaObj.getClienteRelacaoTipo()
							.getId().shortValue() == ClienteRelacaoTipo.USUARIO
							.shortValue()) {

						// Verifica se o cliente usu�rio tem RG ou CPF
						if ((clienteImovelEconomiaObj.getCliente().getRg() == null || clienteImovelEconomiaObj
								.getCliente().getRg().equals(""))
								&& clienteImovelEconomiaObj.getCliente()
										.getCpf() == null) {
							throw new ActionServletException(
									"atencao.usuario.rg_cpf_nao_cadastrado_economia");
						} else {

							// Verifica se o mesmo cliente est� vinculado a mais
							// de uma economia do im�vel como usu�rio
							int qtde = fachada
									.pesquisarClienteImovelEconomiaCount(
											new Integer(idImovel),
											clienteImovelEconomiaObj
													.getCliente().getId());

							if (qtde >= 2) {
								throw new ActionServletException(
										"atencao.usuarios.nao_distintos");
							}
							
//							fachada.verificarClienteUsuarioEmOutroEconomia(null, clienteImovelEconomiaObj.getImovelEconomia().getId(), clienteImovelEconomiaObj.getCliente().getId());
							
							// Verifica se o cliente usu�rio j� foi exclu�do por um motivo que permita recadastramento
							fachada.verificarClienteMotivoExclusaoRecadastramento(clienteImovelEconomiaObj
									.getCliente().getId());
							
							// Carregando os objetos que ser�o visualizados no
							// JSP
							clienteImovelEconomiaObj
									.setImovelEconomia(imovelEconomiaObj);
							colecaoClienteImovelEconomiaJSP
									.add(clienteImovelEconomiaObj);

							// Verificar se o cliente usu�rio est� relacionado a
							// algum outro im�vel na tarifa social

							Collection colecaoImovel = fachada
									.verificarClienteCadastradoTarifaSocial(clienteImovelEconomiaObj
											.getCliente().getId());

							if (colecaoImovel != null
									&& !colecaoImovel.isEmpty()) {

								Imovel imovel = (Imovel) colecaoImovel
										.iterator().next();

								sessao.setAttribute("prerequisito", "14");

								httpServletRequest
										.setAttribute(
												"caminhoActionConclusao",
												"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");

								return montarPaginaConfirmacaoWizard(
										"atencao.usuario.ja_cadastrado_tarifasocial",
										httpServletRequest, actionMapping,
										imovel.getId()
												.toString());
							}
							
							if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
								colecaoClienteImovel.clear();
							}

							// Verificar se o cliente usu�rio est� relacionado a
							// algum outro im�vel na tarifa social
							// (CLIENTE_IMOVEL_ECONOMIA)
							filtroClienteImovelEconomiaVerificacao
									.adicionarParametro(new ParametroSimples(
											FiltroClienteImovelEconomia.IMOVEL_PERFIL_INDICADOR_USO,
											ConstantesSistema.INDICADOR_USO_ATIVO));
							filtroClienteImovelEconomiaVerificacao
									.adicionarParametro(new ParametroSimples(
											FiltroClienteImovelEconomia.IMOVEL_PERFIL,
											ImovelPerfil.TARIFA_SOCIAL));
							filtroClienteImovelEconomiaVerificacao
									.adicionarParametro(new ParametroSimples(
											FiltroClienteImovelEconomia.CLIENTE_ID,
											clienteImovelEconomiaObj
													.getCliente().getId()));
							filtroClienteImovelEconomiaVerificacao
									.adicionarParametro(new ParametroSimples(
											FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO,
											ClienteRelacaoTipo.USUARIO));
							filtroClienteImovelEconomiaVerificacao
									.adicionarParametro(new ParametroNulo(
											FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO));

							colecaoClienteImovelEconomiaVerificacao = fachada
									.pesquisar(
											filtroClienteImovelEconomiaVerificacao,
											ClienteImovelEconomia.class
													.getName());

							if (colecaoClienteImovelEconomiaVerificacao != null
									&& !colecaoClienteImovelEconomiaVerificacao
											.isEmpty()) {

								/*
								 * clienteImovelEconomiaVerificacaoObj =
								 * (ClienteImovelEconomia)
								 */colecaoClienteImovelEconomiaVerificacao
										.iterator().next();

								throw new ActionServletException(
										"atencao.usuario.ja_cadastrado_tarifasocial",
										null, clienteImovelEconomiaObj
												.getImovelEconomia()
												.getImovelSubcategoria()
												.getComp_id().getImovel()
												.getId().toString());
							}

							/*
							 * Verifica a exit�ncia de clientes do tipo usu�rios
							 * repetidos no processo de inclus�o da tarifa
							 */
							if (colecaoClienteUsuario
									.contains(clienteImovelEconomiaObj
											.getCliente())) {
								throw new ActionServletException(
										"atencao.usuario.ja_cadastrado_tarifasocial_mesmo_imovel",
										null, idImovel.toString());
							} else {
								colecaoClienteUsuario
										.add(clienteImovelEconomiaObj
												.getCliente());
							}

							colecaoClienteImovelEconomiaVerificacao.clear();

						}

					}
				}

			}

			// FIM [FS0011] - [FS0012] Verificar propriet�rio e o usu�rio de
			// cada
			// economia do imovel (CPF e RG)
			if (!tipoProprietario) {
				throw new ActionServletException(
						"atencao.proprietario.nao_cadastrado_economia");
			}
			// ==================================================================================================

			if (reload == null) {
				sessao.setAttribute("colecaoClienteImovelEconomia",
						colecaoClienteImovelEconomiaJSP);
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
						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
					
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel_residencial",
							httpServletRequest, actionMapping);
					
				//SUBCATEGORIA CASA DE VERANEIO - E	
				case 2:
					sessao.setAttribute("prerequisito","2");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel.com.subcategorias",
						httpServletRequest, actionMapping, parametroMensagem, "Encerramento");
					
					
				//SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T
				case 3:
					sessao.setAttribute("prerequisito","3");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.nao_imovel.com.subcategorias",
						httpServletRequest, actionMapping, parametroMensagem, "Tramita��o");
					
					
				//PERIL GRANDE CONSUMIDOR	
				case 4:
					sessao.setAttribute("prerequisito","4");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.imovel.perfil.grande_consumidor",
						httpServletRequest, actionMapping);

					
				//LIGACAO DIFERENTE DE CORTADO OU SUPRIMIDO - T	
				case 5:
					sessao.setAttribute("prerequisito","5");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifasocial.agua_nao_ligada",
						httpServletRequest, actionMapping);
					
					
				//ANORMAIDADE DE LEITURA - T	
				case 6:
					sessao.setAttribute("prerequisito","6");
					
					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.anormalidade.leitura.tarifasocial",
						httpServletRequest, actionMapping, parametroMensagem);
					
					
				//EXISTENCIA DE DEBITOS DO CLIENTE	
				case 7:
					sessao.setAttribute("prerequisito","7");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
					return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.apenas.imovel.sem_debitos",
						httpServletRequest, actionMapping);
					
					
				//CONSUMO MEDIO MAIOR QUE 10M3 - E	
				case 8:
					sessao.setAttribute("prerequisito","8");

					httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
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
	        
//	        Collection colecaoClientes = fachada.pesquisarClientesUsuariosImovelEconomia(new Integer(idImovel));
//	        
//	        if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
//	        	Iterator colecaoClientesIterator = colecaoClientes.iterator();
//	        	
//	        	while (colecaoClientesIterator.hasNext()) {
//	        		Cliente clienteUsuario = (Cliente) colecaoClientesIterator.next();
//	        		
//	        		prerequisito = fachada.verificarExistenciaDebitosCliente(clienteUsuario.getId());
//	        		
//	        		if (prerequisito == 7) {
//	        			sessao.setAttribute("prerequisito","7");
//
//						httpServletRequest.setAttribute("caminhoActionConclusao",
//						"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
//					
//						return montarPaginaConfirmacaoWizard("atencao.tarifa_social.permitida.apenas.imovel.sem_debitos",
//							httpServletRequest, actionMapping);
//	        		}
//	        	}
//	        }

		} else {
			if (sessao.getAttribute("prerequisito") != null) {

				String prerequisito = (String) sessao
						.getAttribute("prerequisito");

				httpServletRequest.setAttribute("numeroRA",
						inserirTarifaSocialActionForm
								.get("registroAtendimento"));

				// [FS0005] Verificar o cliente usu�rio do im�vel
				if (prerequisito.equals("")) {

					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");
				} else if (prerequisito.equals("1")) {
					// CATEGORIA RESIDENCIAL - E

					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");

				} else if (prerequisito.equals("2")) {
					// CATEGORIA RESIDENCIAL - E

					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");

				} else if (prerequisito.equals("3")) {
					// SUBCATEGORIA IGREJA, CHAFARIZ, TERRENO - T

					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");

				} else if (prerequisito.equals("4")) {
					// PERIFL GRANDE CONSUMIDOR
					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");

				} else if (prerequisito.equals("5")) {
					// LIGACAO DIFERENTE DE CORTADO OU SUPRIMIDO - T
					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");

				} else if (prerequisito.equals("6")) {
					// ANORMALIDAE DE LEITURA - T
					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");

				} else if (prerequisito.equals("7")) {
					// EXISTENCIA DE DEBITOS DO CLIENTE - T

					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");

				} else if (prerequisito.equals("8")) {

					// CONSUMO M�DIO MAIOR QUE 10M3 - E
					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");
				} else if (prerequisito.equals("9")) {

					// CONSUMO M�NIMO FIXADO MAIOR QUE 10M3 - E
					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");
				} else if (prerequisito.equals("10")) {

					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");

				} else if (prerequisito.equals("11")) {

					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");

				} else if (prerequisito.equals("12")) {

					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");

				} else if (prerequisito.equals("13")) {

					retorno = actionMapping
							.findForward("exibirEncerrarRegistroAtendimentoAction");

				} else if (prerequisito.equals("14")) {
					retorno = actionMapping
							.findForward("exibirTramitarRegistroAtendimentoAction");
				}
			}
		}
		
		String codigo = (String) sessao.getAttribute("codigo");
        String valor = (String) sessao.getAttribute("valor");
        
        if(codigo != null && !codigo.equals("")){

        	if(codigo.equals("9")){
				sessao.setAttribute("prerequisito","9");
				sessao.removeAttribute("codigo");
				sessao.removeAttribute("valor");
        		
        	}else if(codigo.equals("10")){
				sessao.setAttribute("prerequisito","10");
	
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
				
				sessao.removeAttribute("codigo");
				sessao.removeAttribute("valor");
			
				return montarPaginaConfirmacaoWizard("atencao.tarifa_social.renda_familiar.maior.salario_minimo",
					httpServletRequest, actionMapping,valor);
			
        	}else if(codigo.equals("11")){
				sessao.setAttribute("prerequisito","11");
				
				sessao.removeAttribute("codigo");
				sessao.removeAttribute("valor");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.valor.consumo_energia",
					httpServletRequest, actionMapping,valor);

        	}else if(codigo.equals("12")){
				sessao.setAttribute("prerequisito","12");
				
				sessao.removeAttribute("codigo");
				sessao.removeAttribute("valor");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.requisitos",
					httpServletRequest, actionMapping,valor);
        	}else if(codigo.equals("13")){
				sessao.setAttribute("prerequisito","13");
				
				httpServletRequest.setAttribute("caminhoActionConclusao",
				"/gsan/inserirTarifaSocialWizardAction.do?action=exibirInserirTarifaSocialDadosMultiplasEconomiaAction");
			
				return montarPaginaConfirmacaoWizard("atencao.preencher.tarifa_social.requisitos",
					httpServletRequest, actionMapping,valor);
        	}
        	
        	
        }

		return retorno;
	}

	private void carregarObjetosImovelEconomia(Collection imovelEconomias) {

		Iterator imovelEconomiasIt = imovelEconomias.iterator();

		while (imovelEconomiasIt.hasNext()) {
			ImovelEconomia imovelEconomiaObject = (ImovelEconomia) imovelEconomiasIt
					.next();

			Collection tarifaSocialDadoEconomias = imovelEconomiaObject
					.getTarifaSocialDadoEconomias();
			Iterator tarifaSocialDadoEconomiasIt = tarifaSocialDadoEconomias
					.iterator();

			while (tarifaSocialDadoEconomiasIt.hasNext()) {
				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaObject = (TarifaSocialDadoEconomia) tarifaSocialDadoEconomiasIt
						.next();

				FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();

				// Objetos que ser�o retornados pelo hibernate
				filtroTarifaSocialDadoEconomia
						.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialCartaoTipo");
				filtroTarifaSocialDadoEconomia
						.adicionarCaminhoParaCarregamentoEntidade("rendaTipo");
//				filtroTarifaSocialDadoEconomia
//						.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialDado");

				filtroTarifaSocialDadoEconomia
						.adicionarParametro(new ParametroSimples(
								FiltroTarifaSocialDadoEconomia.ID,
								tarifaSocialDadoEconomiaObject.getId()));

				Collection colecaoTarifaSocialDadoEconomia = fachada.pesquisar(
						filtroTarifaSocialDadoEconomia,
						TarifaSocialDadoEconomia.class.getName());

				TarifaSocialDadoEconomia tarifaSocialDadoEconomiaCompleto = (TarifaSocialDadoEconomia) Util
						.retonarObjetoDeColecao(colecaoTarifaSocialDadoEconomia);

				// TarifaSocialCartaoTipo
				tarifaSocialDadoEconomiaObject
						.setTarifaSocialCartaoTipo(tarifaSocialDadoEconomiaCompleto
								.getTarifaSocialCartaoTipo());

				// RendaTipo
				tarifaSocialDadoEconomiaObject
						.setRendaTipo(tarifaSocialDadoEconomiaCompleto
								.getRendaTipo());

				// TarifaSocialDado
				// tarifaSocialDadoEconomiaObject.setTarifaSocialDado(tarifaSocialDadoEconomiaCompleto.getTarifaSocialDado());
			}
		}
	}
}
