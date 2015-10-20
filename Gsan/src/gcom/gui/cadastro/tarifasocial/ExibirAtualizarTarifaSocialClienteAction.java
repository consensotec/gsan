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
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corr�a
 * @since 16/01/2007
 */
public class ExibirAtualizarTarifaSocialClienteAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirCliente");

		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarDadosTarifaSocialClienteActionForm atualizarDadosTarifaSocialClienteActionForm = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;

		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
				ClienteRelacaoTipo.PROPRIETARIO, ConectorOr.CONECTOR_OR, 2));
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
				ClienteRelacaoTipo.USUARIO.intValue()));
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialCartaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoTipoRelacao = fachada.pesquisar(
				filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());

		sessao.setAttribute("colecaoTipoRelacao", colecaoTipoRelacao);

		Date dataAtual = new Date();

		if (httpServletRequest.getAttribute("telaLimpa") != null
				|| sessao.getAttribute("telaLimpa") != null) {
			sessao.removeAttribute("telaLimpa");
			atualizarDadosTarifaSocialClienteActionForm.setIdCliente("");
			atualizarDadosTarifaSocialClienteActionForm.setNomeCliente("");
			atualizarDadosTarifaSocialClienteActionForm
					.setClienteRelacaoTipo(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			atualizarDadosTarifaSocialClienteActionForm
					.setDataInicioRelacao(Util.formatarData(dataAtual));
		}

		sessao.removeAttribute("colecaoClienteImovelRemover");
		sessao.removeAttribute("colecaoClienteImovelEconomiaRemover");

		String idCliente = atualizarDadosTarifaSocialClienteActionForm
				.getIdCliente();

		Cliente cliente = null;

		if (idCliente != null && !idCliente.trim().equals("")) {
			cliente = fachada
					.pesquisarClienteComClienteTipoTarifaSocial(new Integer(
							idCliente));

			if (cliente != null) {
				atualizarDadosTarifaSocialClienteActionForm
						.setNomeCliente(cliente.getNome());
			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrada", true);
				atualizarDadosTarifaSocialClienteActionForm.setIdCliente("");
				atualizarDadosTarifaSocialClienteActionForm
						.setNomeCliente("Cliente Inexistente");
			}
		}

		TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) sessao
				.getAttribute("tarifaSocialHelper");

		Collection colecaoClienteImovel = null;
		Collection colecaoClienteImovelEconomia = null;

		if (sessao.getAttribute("colecaoClienteImovel") == null
				&& sessao.getAttribute("colecaoClienteImovelEconomia") == null) {

			if (tarifaSocialHelper.getColecaoClientesImoveis() == null
					&& tarifaSocialHelper.getColecaoClientesImoveisEconomias() == null) {

				// Uma Economia
				if (tarifaSocialHelper.getClienteImovel() != null) {

					Integer idImovel = tarifaSocialHelper.getClienteImovel()
							.getImovel().getId();

					colecaoClienteImovel = fachada
							.pesquisarClientesImovelTarifaSocial(idImovel);
					sessao.setAttribute("colecaoClienteImovel",
							colecaoClienteImovel);

				} else {

					Integer idImovelEconomia = tarifaSocialHelper
							.getClienteImovelEconomia().getImovelEconomia()
							.getId();

					colecaoClienteImovelEconomia = fachada
							.pesquisarClientesImovelEconomiaTarifaSocial(idImovelEconomia);
					sessao.setAttribute("colecaoClienteImovelEconomia",
							colecaoClienteImovelEconomia);

				}

			} else {

				if (tarifaSocialHelper.getColecaoClientesImoveis() != null) {

					colecaoClienteImovel = tarifaSocialHelper
							.getColecaoClientesImoveis();
					sessao.setAttribute("colecaoClienteImovel",
							colecaoClienteImovel);

				} else {

					colecaoClienteImovelEconomia = tarifaSocialHelper
							.getColecaoClientesImoveisEconomias();
					sessao.setAttribute("colecaoClienteImovelEconomia",
							colecaoClienteImovelEconomia);

				}
			}

		}

		// Adiciona um cliente im�vel a cole��o
		if (httpServletRequest.getParameter("adicionar") != null) {

			TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao
					.getAttribute("tarifaSocialHelperAtualizar");

			if (cliente != null) {

				String idTipoRelacao = atualizarDadosTarifaSocialClienteActionForm
						.getClienteRelacaoTipo();
				String dataInicioRelacao = atualizarDadosTarifaSocialClienteActionForm
						.getDataInicioRelacao();

				if (idTipoRelacao != null
						&& !idTipoRelacao.equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
					clienteRelacaoTipo.setId(new Integer(idTipoRelacao));

					if (idTipoRelacao.equals(ClienteRelacaoTipo.PROPRIETARIO
							.toString())) {
						if (cliente.getClienteTipo()
								.getIndicadorPessoaFisicaJuridica().equals(
										ClienteTipo.INDICADOR_PESSOA_FISICA)) {

							if ((cliente.getCpf() == null || cliente.getCpf()
									.equals(""))
									&& (cliente.getRg() == null || cliente
											.getRg().equals(""))) {
								throw new ActionServletException(
										"atencao.cliente.imovel.proprietario.pessoa_fisica.sem.documento");
							}

						} else {

							if (cliente.getCnpj() == null
									|| cliente.getCnpj().equals("")) {
								throw new ActionServletException(
										"atencao.cliente.imovel.proprietario.pessoa_juridica.sem.documento");
							}

						}

						clienteRelacaoTipo.setDescricao("PROPRIETARIO");

					} else if (idTipoRelacao.equals(ClienteRelacaoTipo.USUARIO
							.toString())) {

						if ((cliente.getCpf() == null || cliente.getCpf()
								.equals(""))
								&& (cliente.getRg() == null || cliente.getRg()
										.equals(""))) {
							throw new ActionServletException(
									"atencao.cliente.imovel.usuario.pessoa_fisica.sem.documento");
						}

						Collection colecaoImovel = null;
						
						if (tarifaSocialHelperAtualizar.getClienteImovel() != null) {

							colecaoImovel = fachada
									.verificarClienteCadastradoManterTarifaSocialUmaEconomia(
											new Integer(idCliente),
											tarifaSocialHelperAtualizar
													.getClienteImovel()
													.getImovel().getId());

						} else if (tarifaSocialHelperAtualizar
								.getClienteImovelEconomia() != null) {
							colecaoImovel = fachada
									.verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(
											new Integer(idCliente),
											tarifaSocialHelperAtualizar
													.getClienteImovelEconomia()
													.getImovelEconomia()
													.getId());
						}

						if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

							Imovel imovel = (Imovel) colecaoImovel.iterator()
									.next();

							throw new ActionServletException(
									"atencao.usuario.ja_cadastrado_tarifasocial.sem.encerramento",
									null, imovel.getId().toString());

						}

						clienteRelacaoTipo.setDescricao("USUARIO");
					}

					Date dataInicioRelacaoFormatada = null;

					if (dataInicioRelacao != null
							&& !dataInicioRelacao.trim().equals("")) {
						dataInicioRelacaoFormatada = Util
								.converteStringParaDate(dataInicioRelacao);

						// [FS0018] - Verificar Data de In�cio da Rela��o
						if (dataInicioRelacaoFormatada.compareTo(dataAtual) > 0) {
							throw new ActionServletException(
									"atencao.data_inicio_relacao_cliente_imovel");
						}

						if (cliente.getDataNascimento() != null) {
							if (dataInicioRelacaoFormatada.compareTo(cliente
									.getDataNascimento()) < 0) {
								throw new ActionServletException(
										"atencao.data_inicio_relacao_cliente_imovel.data_nascimento");
							}
						}

					} else {
						dataInicioRelacaoFormatada = dataAtual;
					}

					boolean mudouRetorno = false;

					// Verifica se o usu�rio a ser inserido est� na tarifa
					// social em outro im�vel com motivo de revis�o que permita
					// recadastramento. Em caso afirmativo muda o retorno para
					// um popup solicitando que ele selecione o motivo da
					// exclus�o da tarifa social do im�vel anterior
					if (idTipoRelacao.equals(ClienteRelacaoTipo.USUARIO
							.toString())) {

						// Verifica se j� existe um usu�rio
						
						// Uma Economia
						if (sessao.getAttribute("colecaoClienteImovel") != null) {
							
							colecaoClienteImovel = (Collection) sessao
									.getAttribute("colecaoClienteImovel");

							boolean existeUsuario = false;

							Iterator colecaoClienteImovelIterator = colecaoClienteImovel
									.iterator();

							while (colecaoClienteImovelIterator.hasNext()) {
								ClienteImovel clienteImovelValidar = (ClienteImovel) colecaoClienteImovelIterator
										.next();

								if (clienteImovelValidar
										.getClienteRelacaoTipo()
										.getId()
										.toString()
										.equals("" + ClienteRelacaoTipo.USUARIO)) {
									existeUsuario = true;
									break;
								}
							}

							if (existeUsuario) {
								throw new ActionServletException(
										"atencao.ja_cadastradado.cliente_imovel_usuario");
							}

						}
						
						// M�ltiplas Economias
						else {
							colecaoClienteImovelEconomia = (Collection) sessao
									.getAttribute("colecaoClienteImovelEconomia");

							Iterator colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
									.iterator();

							boolean existeUsuario = false;

							while (colecaoClienteImovelEconomiaIterator
									.hasNext()) {
								ClienteImovelEconomia clienteImovelEconomiaValidar = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
										.next();

								if (clienteImovelEconomiaValidar
										.getClienteRelacaoTipo()
										.getId()
										.toString()
										.equals("" + ClienteRelacaoTipo.USUARIO)) {
									existeUsuario = true;
									break;
								}
							}

							if (existeUsuario) {
								throw new ActionServletException(
										"atencao.ja_cadastradado.cliente_imovel_usuario");
							}
						}

						Collection colecaoClientesUsuarioExistenteTarifaSocial = fachada
								.pesquisarClientesUsuarioExistenteTarifaSocial(cliente
										.getId());

						if (colecaoClientesUsuarioExistenteTarifaSocial != null
								&& !colecaoClientesUsuarioExistenteTarifaSocial
										.isEmpty()) {

							mudouRetorno = true;

							retorno = actionMapping
									.findForward("exibirRemoverImovelAnteriorTarifaSocial");

							TarifaSocialDadoEconomia tarifaSocialDadoEconomiaImovelAnterior = (TarifaSocialDadoEconomia) Util
									.retonarObjetoDeColecao(colecaoClientesUsuarioExistenteTarifaSocial);
							sessao.setAttribute(
									"tarifaSocialDadoEconomiaImovelAnterior",
									tarifaSocialDadoEconomiaImovelAnterior);
							sessao.setAttribute(
									"clienteTarifaSocialImovelAnterior",
									cliente);
							sessao.setAttribute("dataInicioRelacao",
									dataInicioRelacaoFormatada);
						}
					}

					if (!mudouRetorno) {

						// Uma Economia
						if (sessao.getAttribute("colecaoClienteImovel") != null) {

							ClienteImovel clienteImovel = new ClienteImovel();

							clienteImovel.setCliente(cliente);

							clienteImovel
									.setClienteRelacaoTipo(clienteRelacaoTipo);

							Imovel imovel = tarifaSocialHelper
									.getClienteImovel().getImovel();

							clienteImovel
									.setDataInicioRelacao(dataInicioRelacaoFormatada);
							clienteImovel.setImovel(imovel);

							colecaoClienteImovel = (Collection) sessao
									.getAttribute("colecaoClienteImovel");

							if (idTipoRelacao.equals(ClienteRelacaoTipo.USUARIO
									.toString())) {

								tarifaSocialHelperAtualizar
										.setClienteImovel(clienteImovel);
								sessao.setAttribute(
										"tarifaSocialHelperAtualizar",
										tarifaSocialHelperAtualizar);
							} else if (idTipoRelacao
									.equals(ClienteRelacaoTipo.PROPRIETARIO
											.toString())) {

								Iterator colecaoClienteImovelIterator = colecaoClienteImovel
										.iterator();

								boolean existeProprietario = false;

								while (colecaoClienteImovelIterator.hasNext()) {
									ClienteImovel clienteImovelValidar = (ClienteImovel) colecaoClienteImovelIterator
											.next();

									if (clienteImovelValidar
											.getClienteRelacaoTipo()
											.getId()
											.equals(
													ClienteRelacaoTipo.PROPRIETARIO)
											&& clienteImovelValidar
													.getCliente().getId()
													.equals(cliente.getId())) {
										existeProprietario = true;
										break;
									}
								}

								if (existeProprietario) {
									throw new ActionServletException(
											"atencao.tarifasocial.cliente_proprietario_duplicidade");
								}
							}

							colecaoClienteImovel.add(clienteImovel);
							sessao.setAttribute("colecaoClienteImovel",
									colecaoClienteImovel);

							Collection colecaoClientesInseridos = null;

							if (tarifaSocialHelperAtualizar
									.getColecaoClientesInseridos() != null) {
								colecaoClientesInseridos = tarifaSocialHelperAtualizar
										.getColecaoClientesInseridos();
							} else {
								colecaoClientesInseridos = new ArrayList();
							}

							colecaoClientesInseridos.add(clienteImovel);
							tarifaSocialHelperAtualizar
									.setColecaoClientesInseridos(colecaoClientesInseridos);
							sessao.setAttribute("tarifaSocialHelperAtualizar",
									tarifaSocialHelperAtualizar);

						}

						// M�ltiplas Economias
						else {

							ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();

							clienteImovelEconomia.setCliente(cliente);

							clienteImovelEconomia
									.setClienteRelacaoTipo(clienteRelacaoTipo);

							ImovelEconomia imovelEconomia = tarifaSocialHelper
									.getClienteImovelEconomia()
									.getImovelEconomia();

							clienteImovelEconomia
									.setDataInicioRelacao(dataInicioRelacaoFormatada);
							clienteImovelEconomia
									.setImovelEconomia(imovelEconomia);

							colecaoClienteImovelEconomia = (Collection) sessao
									.getAttribute("colecaoClienteImovelEconomia");

							if (idTipoRelacao.equals(ClienteRelacaoTipo.USUARIO
									.toString())) {

								tarifaSocialHelperAtualizar
										.setClienteImovelEconomia(clienteImovelEconomia);
								sessao.setAttribute(
										"tarifaSocialHelperAtualizar",
										tarifaSocialHelperAtualizar);

							} else if (idTipoRelacao
									.equals(ClienteRelacaoTipo.PROPRIETARIO
											.toString())) {

								Iterator colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
										.iterator();

								boolean existeProprietario = false;

								while (colecaoClienteImovelEconomiaIterator
										.hasNext()) {
									ClienteImovelEconomia clienteImovelEconomiaValidar = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
											.next();

									if (clienteImovelEconomiaValidar
											.getCliente().getId().equals(
													cliente.getId())
											&& clienteImovelEconomiaValidar
													.getClienteRelacaoTipo()
													.getId()
													.toString()
													.equals(
															ClienteRelacaoTipo.PROPRIETARIO
																	.toString())) {
										existeProprietario = true;
										break;
									}
								}

								if (existeProprietario) {
									throw new ActionServletException(
											"atencao.tarifasocial.cliente_proprietario_duplicidade");
								}
							}

							colecaoClienteImovelEconomia
									.add(clienteImovelEconomia);
							sessao.setAttribute("colecaoClienteImovelEconomia",
									colecaoClienteImovelEconomia);

							Collection colecaoClientesInseridos = null;

							if (tarifaSocialHelperAtualizar
									.getColecaoClientesInseridos() != null) {
								colecaoClientesInseridos = tarifaSocialHelperAtualizar
										.getColecaoClientesEconomiaInseridos();
							} else {
								colecaoClientesInseridos = new ArrayList();
							}

							colecaoClientesInseridos.add(clienteImovelEconomia);
							tarifaSocialHelperAtualizar
									.setColecaoClientesEconomiaInseridos(colecaoClientesInseridos);

							sessao.setAttribute("tarifaSocialHelperAtualizar",
									tarifaSocialHelperAtualizar);
						}

					}

					atualizarDadosTarifaSocialClienteActionForm
							.setIdCliente("");
					atualizarDadosTarifaSocialClienteActionForm
							.setNomeCliente("");
					atualizarDadosTarifaSocialClienteActionForm
							.setDataInicioRelacao(Util.formatarData(dataAtual));
					atualizarDadosTarifaSocialClienteActionForm
							.setClienteRelacaoTipo(""
									+ ConstantesSistema.NUMERO_NAO_INFORMADO);

				}

			}

		}

		return retorno;

	}

}
