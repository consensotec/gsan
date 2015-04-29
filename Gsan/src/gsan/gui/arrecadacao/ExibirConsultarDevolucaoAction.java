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
package gsan.gui.arrecadacao;

import gsan.arrecadacao.Devolucao;
import gsan.arrecadacao.DevolucaoHistorico;
import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.FiltroClienteEndereco;
import gsan.cadastro.cliente.FiltroClienteFone;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.DocumentoTipo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtrar Devolu��es - Exibir
 * 
 * @author Rafael Corr�a - 31/01/2006
 */
public class ExibirConsultarDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarClienteDevolucoes");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarDevolucaoActionForm consultarDevolucaoActionForm = (ConsultarDevolucaoActionForm) actionForm;

		Collection colecaoDevolucao = null;
		Collection colecaoDevolucaoHistorico = null;
		Collection<Devolucao> colecaoDevolucaoClienteConta = new ArrayList<Devolucao>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoClienteConta = new ArrayList<DevolucaoHistorico>();
		Collection<Devolucao> colecaoDevolucaoClienteGuiaPagamento = new ArrayList<Devolucao>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoClienteGuiaPagamento = new ArrayList<DevolucaoHistorico>();
		Collection<Devolucao> colecaoDevolucaoClienteDebitoACobrar = new ArrayList<Devolucao>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoClienteDebitoACobrar = new ArrayList<DevolucaoHistorico>();
		Collection<Devolucao> colecaoDevolucaoClienteDevolucaoValor = new ArrayList<Devolucao>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoClienteDevolucaoValor = new ArrayList<DevolucaoHistorico>();
		Collection<Devolucao> colecaoDevolucaoImovelConta = new ArrayList<Devolucao>();
		Collection<Devolucao> colecaoDevolucaoImovelGuiaPagamento = new ArrayList<Devolucao>();
		Collection<Devolucao> colecaoDevolucaoImovelDebitoACobrar = new ArrayList<Devolucao>();
		Collection<Devolucao> colecaoDevolucaoImovelDevolucaoValor = new ArrayList<Devolucao>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelConta = new ArrayList<DevolucaoHistorico>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelGuiaPagamento = new ArrayList<DevolucaoHistorico>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelDebitoACobrar = new ArrayList<DevolucaoHistorico>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoImovelDevolucaoValor = new ArrayList<DevolucaoHistorico>();
		Collection<Devolucao> colecaoDevolucaoAvisoBancarioConta = new ArrayList<Devolucao>();
		Collection<Devolucao> colecaoDevolucaoAvisoBancarioGuiaPagamento = new ArrayList<Devolucao>();
		Collection<Devolucao> colecaoDevolucaoAvisoBancarioDebitoACobrar = new ArrayList<Devolucao>();
		Collection<Devolucao> colecaoDevolucaoAvisoBancarioDevolucaoValor = new ArrayList<Devolucao>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoAvisoBancarioConta = new ArrayList<DevolucaoHistorico>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento = new ArrayList<DevolucaoHistorico>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar = new ArrayList<DevolucaoHistorico>();
		Collection<DevolucaoHistorico> colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor = new ArrayList<DevolucaoHistorico>();

		
		//Vari�veis para definir tamanho das tabelas
		// se necess�rio ou naum scroll
		Integer qtdeDevContas = 0;
		Integer qtdeDevGuiaPagamento = 0;
		Integer qtdeDevDebitoACobrar = 0;
		Integer qtdeDevDevolucaoValores = 0;
		
		// Consultar Devolu��o do Cliente

		if (sessao.getAttribute("colecaoClientesDevolucoes") != null ||
				sessao.getAttribute("colecaoClientesDevolucoesHistorico") != null) {
			
			colecaoDevolucao = (Collection) sessao
					.getAttribute("colecaoClientesDevolucoes");
			sessao.removeAttribute("colecaoClientesDevolucoes");
			
			colecaoDevolucaoHistorico = (Collection) sessao
					.getAttribute("colecaoClientesDevolucoesHistorico");
			sessao.removeAttribute("colecaoClientesDevolucoesHistorico");

			Cliente cliente = (Cliente) sessao.getAttribute("cliente");

			ClienteEndereco clienteEndereco = null;

			ClienteFone clienteFone = null;

			FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();

			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.municipio.unidadeFederacao");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			filtroClienteEndereco.adicionarParametro(new ParametroSimples(
					FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));

			Collection clientesEnderecos = fachada.pesquisar(
					filtroClienteEndereco, ClienteEndereco.class.getName());

			if (clientesEnderecos != null && !clientesEnderecos.isEmpty()) {
				// O Endere�o foi encontrado
				Iterator clienteEnderecoIterator = clientesEnderecos.iterator();

				while (clienteEnderecoIterator.hasNext()) {
					clienteEndereco = (ClienteEndereco) clienteEnderecoIterator
							.next();

					if (clienteEndereco.getIndicadorEnderecoCorrespondencia()
							.equals(new Short("1"))) {
						sessao.setAttribute("clienteEndereco", clienteEndereco);
						break;
					}
				}
			}

			FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
			filtroClienteFone
					.adicionarCaminhoParaCarregamentoEntidade("foneTipo");

			filtroClienteFone.adicionarParametro(new ParametroSimples(
					FiltroClienteFone.CLIENTE_ID, cliente.getId()));

			Collection clientesFones = fachada.pesquisar(filtroClienteFone,
					ClienteFone.class.getName());

			if (clientesFones != null && !clientesFones.isEmpty()) {
				// O telefone foi encontrado
				Iterator clienteFoneIterator = clientesFones.iterator();

				while (clienteFoneIterator.hasNext()) {
					clienteFone = (ClienteFone) clienteFoneIterator.next();

					if (clienteFone.getIndicadorTelefonePadrao().equals(
							new Short("1"))) {
						consultarDevolucaoActionForm.setTelefone(clienteFone
								.getTelefone());
						break;
					}
				}
			}

			consultarDevolucaoActionForm.setNomeCliente(cliente.getNome());
			consultarDevolucaoActionForm.setIdCliente(cliente.getId().toString());

			if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica()
					.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				consultarDevolucaoActionForm.setCpfCnpj(cliente
						.getCpfFormatado());
				consultarDevolucaoActionForm.setProfissao(cliente
						.getProfissao() == null ? "" : cliente.getProfissao()
						.getDescricao());
			} else {
				consultarDevolucaoActionForm.setCpfCnpj(cliente
						.getCnpjFormatado());
				consultarDevolucaoActionForm.setRamoAtividade(cliente
						.getRamoAtividade() == null ? "" : cliente
						.getRamoAtividade().getDescricao());
			}

			if (colecaoDevolucao != null){
				Iterator colecaoDevolucaoIterator = colecaoDevolucao.iterator();
	
				while (colecaoDevolucaoIterator.hasNext()) {
	
					Devolucao devolucao = ((Devolucao) colecaoDevolucaoIterator
							.next());
	
					if (devolucao.getGuiaDevolucao() != null) {
	
						if (devolucao.getGuiaDevolucao().getDocumentoTipo().getId()
								.equals(DocumentoTipo.CONTA)) {
							colecaoDevolucaoClienteConta.add(devolucao);
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
							colecaoDevolucaoClienteDebitoACobrar.add(devolucao);
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
							colecaoDevolucaoClienteGuiaPagamento.add(devolucao);
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							colecaoDevolucaoClienteDevolucaoValor.add(devolucao);
						}
					} else {
						if (devolucao.getAnoMesReferenciaDevolucao() != null) {
							colecaoDevolucaoClienteConta.add(devolucao);
						} else {
							colecaoDevolucaoClienteDevolucaoValor.add(devolucao);
						}
					}
				}
			}	
			
			
			if (colecaoDevolucaoHistorico != null){
				Iterator colecaoDevolucaoHistoricoIterator = colecaoDevolucaoHistorico.iterator();
	
				while (colecaoDevolucaoHistoricoIterator.hasNext()) {
	
					DevolucaoHistorico devolucaoHistorico = ((DevolucaoHistorico) colecaoDevolucaoHistoricoIterator
							.next());
	
					if (devolucaoHistorico.getGuiaDevolucao() != null) {
	
						if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo().getId()
								.equals(DocumentoTipo.CONTA)) {
							colecaoDevolucaoHistoricoClienteConta.add(devolucaoHistorico);
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
							colecaoDevolucaoHistoricoClienteDebitoACobrar.add(devolucaoHistorico);
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
							colecaoDevolucaoHistoricoClienteGuiaPagamento.add(devolucaoHistorico);
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							colecaoDevolucaoHistoricoClienteDevolucaoValor.add(devolucaoHistorico);
						}
					} else {
						if (devolucaoHistorico.getAnoMesReferenciaDevolucao() != null) {
							colecaoDevolucaoHistoricoClienteConta.add(devolucaoHistorico);
						} else {
							colecaoDevolucaoHistoricoClienteDevolucaoValor.add(devolucaoHistorico);
						}
					}
				}
			}

			if (colecaoDevolucaoClienteConta != null
					&& !colecaoDevolucaoClienteConta.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, ano/m�s refer�ncia
				// da devolu��o e
				// pela data da devolu��o
				Comparator comparator = new Comparator() {
					public int compare(Object a, Object b) {

						// Cria as chaves usadas para fazer as
						// compara��es

						String chave1 = "";
						String chave2 = "";

						// Verifica se os campos s�o nulos, em caso
						// afirmativo seta na chave um espa�o em branco
						// para servir de diferencia��o entre as
						// strings, evitando assim que uma devolu��o sem
						// localidade venha no meio de outras com
						// localidade e em caso negativo coloca-o na
						// chave

						// Localidade
						if (((Devolucao) a).getLocalidade() != null) {
							chave1 = chave1
									+ ((Devolucao) a).getLocalidade()
											.getId().toString();
						} else {
							chave1 = chave1 + " ";
						}

						// Im�vel
						if (((Devolucao) a).getImovel() != null) {
							chave1 = chave1
									+ ((Devolucao) a).getImovel()
											.getId().toString();
						} else {
							chave1 = chave1 + " ";
						}

						// Ano/m�s refer�ncia da devolu��o
						if (((Devolucao) a)
								.getAnoMesReferenciaDevolucao() != null) {
							chave1 = chave1
									+ ((Devolucao) a)
											.getAnoMesReferenciaDevolucao()
											.toString();
						} else {
							chave1 = chave1 + " ";
						}

						// Data da devolu��o
						if (((Devolucao) a).getDataDevolucao() != null) {
							chave1 = chave1
									+ Util
											.recuperaDataInvertida(((Devolucao) a)
													.getDataDevolucao());
						} else {
							chave1 = chave1 + " ";
						}

						// Localidade
						if (((Devolucao) b).getLocalidade() != null) {
							chave2 = chave2
									+ ((Devolucao) b).getLocalidade()
											.getId().toString();
						} else {
							chave2 = chave2 + " ";
						}

						// Im�vel
						if (((Devolucao) b).getImovel() != null) {
							chave2 = chave2
									+ ((Devolucao) b).getImovel()
											.getId().toString();
						} else {
							chave2 = chave2 + " ";
						}

						// Ano/m�s refer�ncia da devolu��o
						if (((Devolucao) b)
								.getAnoMesReferenciaDevolucao() != null) {
							chave2 = chave2
									+ ((Devolucao) b)
											.getAnoMesReferenciaDevolucao()
											.toString();
						} else {
							chave2 = chave2 + " ";
						}

						// Data da devolu��o
						if (((Devolucao) b).getDataDevolucao() != null) {
							chave2 = chave2
									+ Util
											.recuperaDataInvertida(((Devolucao) a)
													.getDataDevolucao());
						} else {
							chave2 = chave2 + " ";
						}

						return chave1.compareTo(chave2);

					}
				};
				
				Collections.sort((List<Devolucao>) colecaoDevolucaoClienteConta,comparator);
				httpServletRequest.setAttribute("colecaoDevolucaoClienteConta",
						colecaoDevolucaoClienteConta);
				
				qtdeDevContas =  colecaoDevolucaoClienteConta.size();
			}
			
			if (colecaoDevolucaoHistoricoClienteConta != null
					&& !colecaoDevolucaoHistoricoClienteConta.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, ano/m�s refer�ncia
				// da devolu��o e
				// pela data da devolu��o
				Collections.sort((List<DevolucaoHistorico>) colecaoDevolucaoHistoricoClienteConta,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Ano/m�s refer�ncia da devolu��oHistorico
								if (((DevolucaoHistorico) a)
										.getAnoMesReferenciaDevolucao() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((DevolucaoHistorico) b)
										.getAnoMesReferenciaDevolucao() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute("colecaoDevolucaoHistoricoClienteConta",
						colecaoDevolucaoHistoricoClienteConta);
				qtdeDevContas = qtdeDevContas + colecaoDevolucaoHistoricoClienteConta.size();
			}

			
			
			if (colecaoDevolucaoClienteGuiaPagamento != null
					&& !colecaoDevolucaoClienteGuiaPagamento.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoClienteGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoClienteGuiaPagamento",
						colecaoDevolucaoClienteGuiaPagamento);
				qtdeDevGuiaPagamento = colecaoDevolucaoClienteGuiaPagamento.size();
			}
			
			
			if (colecaoDevolucaoHistoricoClienteGuiaPagamento != null
					&& !colecaoDevolucaoHistoricoClienteGuiaPagamento.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoClienteGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoClienteGuiaPagamento",
						colecaoDevolucaoHistoricoClienteGuiaPagamento);
				
				qtdeDevGuiaPagamento = qtdeDevGuiaPagamento + colecaoDevolucaoHistoricoClienteGuiaPagamento.size();
			}
			
			
			if (colecaoDevolucaoClienteDebitoACobrar != null
					&& !colecaoDevolucaoClienteDebitoACobrar.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoClienteDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da Devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});

				httpServletRequest.setAttribute(
						"colecaoDevolucaoClienteDebitoACobrar",
						colecaoDevolucaoClienteDebitoACobrar);
				qtdeDevDebitoACobrar = colecaoDevolucaoClienteDebitoACobrar.size();
			}
			
			if (colecaoDevolucaoHistoricoClienteDebitoACobrar != null
					&& !colecaoDevolucaoHistoricoClienteDebitoACobrar.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoClienteDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da Devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});

				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoClienteDebitoACobrar",
						colecaoDevolucaoHistoricoClienteDebitoACobrar);
				
				qtdeDevDebitoACobrar = qtdeDevDebitoACobrar + colecaoDevolucaoHistoricoClienteDebitoACobrar.size();
			}

			if (colecaoDevolucaoClienteDevolucaoValor != null
					&& !colecaoDevolucaoClienteDevolucaoValor.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoClienteDevolucaoValor,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoClienteDevolucaoValor",
						colecaoDevolucaoClienteDevolucaoValor);
				qtdeDevDevolucaoValores = colecaoDevolucaoClienteDevolucaoValor.size();
			}

		}

		if (colecaoDevolucaoHistoricoClienteDevolucaoValor != null
				&& !colecaoDevolucaoHistoricoClienteDevolucaoValor.isEmpty()) {
			// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
			// Documento
			// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
			// pela data da devolu��o
			Collections.sort((List) colecaoDevolucaoHistoricoClienteDevolucaoValor,
					new Comparator() {
						public int compare(Object a, Object b) {

							// Cria as chaves usadas para fazer as
							// compara��es

							String chave1 = "";
							String chave2 = "";

							// Verifica se os campos s�o nulos, em caso
							// afirmativo seta na chave um espa�o em branco
							// para servir de diferencia��o entre as
							// strings, evitando assim que uma devolu��o sem
							// localidade venha no meio de outras com
							// localidade e em caso negativo coloca-o na
							// chave

							// Localidade
							if (((DevolucaoHistorico) a).getLocalidade() != null) {
								chave1 = chave1
										+ ((DevolucaoHistorico) a).getLocalidade()
												.getId().toString();
							} else {
								chave1 = chave1 + " ";
							}

							// Im�vel
							if (((DevolucaoHistorico) a).getImovel() != null) {
								chave1 = chave1
										+ ((DevolucaoHistorico) a).getImovel()
												.getId().toString();
							} else {
								chave1 = chave1 + " ";
							}

							// Tipo de D�bito
							if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
								chave1 = chave1
										+ ((DevolucaoHistorico) a).getDebitoTipo()
												.getId();
							} else {
								chave1 = chave1 + " ";
							}

							// Data da devolu��o
							if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
								chave1 = chave1
										+ Util
												.recuperaDataInvertida(((DevolucaoHistorico) a)
														.getDataDevolucao());
							} else {
								chave1 = chave1 + " ";
							}

							// Localidade
							if (((DevolucaoHistorico) b).getLocalidade() != null) {
								chave2 = chave2
										+ ((DevolucaoHistorico) b).getLocalidade()
												.getId().toString();
							} else {
								chave2 = chave2 + " ";
							}

							// Im�vel
							if (((DevolucaoHistorico) b).getImovel() != null) {
								chave2 = chave2
										+ ((DevolucaoHistorico) b).getImovel()
												.getId().toString();
							} else {
								chave2 = chave2 + " ";
							}

							// Tipo de D�bito
							if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
								chave2 = chave2
										+ ((DevolucaoHistorico) b).getDebitoTipo()
												.getId();
							} else {
								chave2 = chave2 + " ";
							}

							// Data da devolu��o
							if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
								chave2 = chave2
										+ Util
												.recuperaDataInvertida(((DevolucaoHistorico) a)
														.getDataDevolucao());
							} else {
								chave2 = chave2 + " ";
							}

							return chave1.compareTo(chave2);

						}
					});
			httpServletRequest.setAttribute(
					"colecaoDevolucaoHistoricoClienteDevolucaoValor",
					colecaoDevolucaoHistoricoClienteDevolucaoValor);
			qtdeDevDevolucaoValores = qtdeDevDevolucaoValores + colecaoDevolucaoHistoricoClienteDevolucaoValor.size();
		}

	
		
		// Consultar Devolu��o do Im�vel

		if (sessao.getAttribute("colecaoImoveisDevolucoes") != null ||
				sessao.getAttribute("colecaoImoveisDevolucoesHistorico") != null) {

			retorno = actionMapping.findForward("consultarImovelDevolucoes");

			colecaoDevolucao = (Collection) sessao
					.getAttribute("colecaoImoveisDevolucoes");
			sessao.removeAttribute("colecaoImoveisDevolucoes");
			
			colecaoDevolucaoHistorico = (Collection) sessao
			.getAttribute("colecaoImoveisDevolucoesHistorico");
			sessao.removeAttribute("colecaoImoveisDevolucoesHistorico");

			Imovel imovel = (Imovel) sessao.getAttribute("imovel");

			sessao.setAttribute("imovel", imovel);

			consultarDevolucaoActionForm.setInscricao(imovel
					.getInscricaoFormatada());

			consultarDevolucaoActionForm.setIdImovel(imovel.getId().toString());

			if (imovel.getLigacaoAguaSituacao() != null) {
				consultarDevolucaoActionForm.setSituacaoLigacaoAgua(imovel
						.getLigacaoAguaSituacao().getDescricao());
			}
			if (imovel.getLigacaoEsgotoSituacao() != null) {
				consultarDevolucaoActionForm.setSituacaoLigacaoEsgoto(imovel
						.getLigacaoEsgotoSituacao().getDescricao());
			}

			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(
					FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection colecaoClientes = fachada.pesquisar(filtroClienteImovel,
					ClienteImovel.class.getName());

			if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
				httpServletRequest.setAttribute("colecaoClientes",
						colecaoClientes);
			}

			if (colecaoDevolucao != null){
				Iterator colecaoDevolucaoIterator = colecaoDevolucao.iterator();
	
				while (colecaoDevolucaoIterator.hasNext()) {
	
					Devolucao devolucao = ((Devolucao) colecaoDevolucaoIterator
							.next());
	
					if (devolucao.getGuiaDevolucao() != null) {
	
						if (devolucao.getGuiaDevolucao().getDocumentoTipo().getId()
								.equals(DocumentoTipo.CONTA)) {
	
							colecaoDevolucaoImovelConta.add(devolucao);
	
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
	
							colecaoDevolucaoImovelDebitoACobrar.add(devolucao);
	
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
	
							colecaoDevolucaoImovelGuiaPagamento.add(devolucao);
	
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							colecaoDevolucaoImovelDevolucaoValor.add(devolucao);
						}
					} else {
						if (devolucao.getAnoMesReferenciaDevolucao() != null) {
							colecaoDevolucaoImovelConta.add(devolucao);
						} else {
							colecaoDevolucaoImovelDevolucaoValor.add(devolucao);
						}
					}
				}	
	
				}

			if (colecaoDevolucaoHistorico!= null){
			
				Iterator colecaoDevolucaoHistoricoIterator = colecaoDevolucaoHistorico.iterator();
	
				while (colecaoDevolucaoHistoricoIterator.hasNext()) {
	
					DevolucaoHistorico devolucaoHistorico = ((DevolucaoHistorico) colecaoDevolucaoHistoricoIterator
							.next());
	
					if (devolucaoHistorico.getGuiaDevolucao() != null) {
	
						if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo().getId()
								.equals(DocumentoTipo.CONTA)) {
	
							colecaoDevolucaoHistoricoImovelConta.add(devolucaoHistorico);
	
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
	
							colecaoDevolucaoHistoricoImovelDebitoACobrar.add(devolucaoHistorico);
	
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
	
							colecaoDevolucaoHistoricoImovelGuiaPagamento.add(devolucaoHistorico);
	
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							colecaoDevolucaoHistoricoImovelDevolucaoValor.add(devolucaoHistorico);
						}
					} else {
						if (devolucaoHistorico.getAnoMesReferenciaDevolucao() != null) {
							colecaoDevolucaoHistoricoImovelConta.add(devolucaoHistorico);
						} else {
							colecaoDevolucaoHistoricoImovelDevolucaoValor.add(devolucaoHistorico);
						}
					}
				}
			}

			
			if (colecaoDevolucaoImovelConta != null
					&& !colecaoDevolucaoImovelConta.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, ano/m�s refer�ncia
				// da devolu��o e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoImovelConta,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((Devolucao) a)
										.getAnoMesReferenciaDevolucao() != null) {
									chave1 = chave1
											+ ((Devolucao) a)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((Devolucao) b)
										.getAnoMesReferenciaDevolucao() != null) {
									chave2 = chave2
											+ ((Devolucao) b)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}

						});

				httpServletRequest.setAttribute("colecaoDevolucaoImovelConta",
						colecaoDevolucaoImovelConta);
				qtdeDevContas = colecaoDevolucaoImovelConta.size();
				
			}
			
			if (colecaoDevolucaoHistoricoImovelConta != null
					&& !colecaoDevolucaoHistoricoImovelConta.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, ano/m�s refer�ncia
				// da devolu��o e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoImovelConta,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((DevolucaoHistorico) a)
										.getAnoMesReferenciaDevolucao() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((DevolucaoHistorico) b)
										.getAnoMesReferenciaDevolucao() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}

						});

				httpServletRequest.setAttribute("colecaoDevolucaoHistoricoImovelConta",
						colecaoDevolucaoHistoricoImovelConta);
				qtdeDevContas = qtdeDevContas + colecaoDevolucaoHistoricoImovelConta.size();
			}
			
			
			if (colecaoDevolucaoImovelGuiaPagamento != null
					&& !colecaoDevolucaoImovelGuiaPagamento.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoImovelGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoImovelGuiaPagamento",
						colecaoDevolucaoImovelGuiaPagamento);
				qtdeDevGuiaPagamento = colecaoDevolucaoImovelGuiaPagamento.size();

			}
			
			if (colecaoDevolucaoHistoricoImovelGuiaPagamento != null
					&& !colecaoDevolucaoHistoricoImovelGuiaPagamento.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoImovelGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoImovelGuiaPagamento",
						colecaoDevolucaoHistoricoImovelGuiaPagamento);
				
				qtdeDevGuiaPagamento = qtdeDevGuiaPagamento + colecaoDevolucaoHistoricoImovelGuiaPagamento.size();
			}
			
			if (colecaoDevolucaoImovelDebitoACobrar != null
					&& !colecaoDevolucaoImovelDebitoACobrar.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoImovelDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da Devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoImovelDebitoACobrar",
						colecaoDevolucaoImovelDebitoACobrar);
				
				qtdeDevDebitoACobrar = colecaoDevolucaoImovelDebitoACobrar.size();
			}
			
			
			if (colecaoDevolucaoHistoricoImovelDebitoACobrar != null
					&& !colecaoDevolucaoHistoricoImovelDebitoACobrar.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoImovelDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da Devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoImovelDebitoACobrar",
						colecaoDevolucaoHistoricoImovelDebitoACobrar);
				
				qtdeDevDebitoACobrar = qtdeDevDebitoACobrar + colecaoDevolucaoHistoricoImovelDebitoACobrar.size();
			}

			if (colecaoDevolucaoImovelDevolucaoValor != null
					&& !colecaoDevolucaoImovelDevolucaoValor.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoImovelDevolucaoValor,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoImovelDevolucaoValor",
						colecaoDevolucaoImovelDevolucaoValor);
				qtdeDevDevolucaoValores = colecaoDevolucaoImovelDevolucaoValor.size();
			}

			if (colecaoDevolucaoHistoricoImovelDevolucaoValor != null
					&& !colecaoDevolucaoHistoricoImovelDevolucaoValor.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoImovelDevolucaoValor,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoImovelDevolucaoValor",
						colecaoDevolucaoHistoricoImovelDevolucaoValor);
				
				qtdeDevDevolucaoValores = qtdeDevDevolucaoValores + colecaoDevolucaoHistoricoImovelDevolucaoValor.size();
			}

			
		}

		// Consultar Devolu��o do Aviso Banc�rio

		if (sessao.getAttribute("colecaoAvisosBancariosDevolucoes") != null ||
				sessao.getAttribute("colecaoAvisosBancariosDevolucoesHistorico") != null) {

			retorno = actionMapping
					.findForward("consultarAvisoBancarioDevolucoes");

			colecaoDevolucao = (Collection) sessao
					.getAttribute("colecaoAvisosBancariosDevolucoes");
			sessao.removeAttribute("colecaoAvisosBancariosDevolucoes");
			
			colecaoDevolucaoHistorico = (Collection) sessao
					.getAttribute("colecaoAvisosBancariosDevolucoesHistorico");
			sessao.removeAttribute("colecaoAvisosBancariosDevolucoesHistorico");

			AvisoBancario avisoBancario = (AvisoBancario) sessao.getAttribute("avisoBancario");

			consultarDevolucaoActionForm.setSequencialAviso(""
					+ avisoBancario.getNumeroSequencial());

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String dataLancamento = "";
			if (avisoBancario.getDataLancamento() != null
					&& !avisoBancario.getDataLancamento().equals("")) {
				dataLancamento = format.format(avisoBancario
						.getDataLancamento());
			}

			consultarDevolucaoActionForm.setDataLancamento(dataLancamento);

			if (avisoBancario.getArrecadador() != null) {
				consultarDevolucaoActionForm.setIdArrecadador(""
						+ avisoBancario.getArrecadador().getCodigoAgente());
				consultarDevolucaoActionForm
						.setDescricaoArrecadador(avisoBancario.getArrecadador()
								.getCliente().getNome());
			}
			if (avisoBancario.getIndicadorCreditoDebito() == AvisoBancario.INDICADOR_CREDITO
					.shortValue()) {
				consultarDevolucaoActionForm.setTipoAviso("CR�DITO");
			} else {
				consultarDevolucaoActionForm.setTipoAviso("D�BITO");
			}

			if (colecaoDevolucao != null){
				Iterator colecaoDevolucaoIterator = colecaoDevolucao.iterator();
	
				while (colecaoDevolucaoIterator.hasNext()) {
	
					Devolucao devolucao = ((Devolucao) colecaoDevolucaoIterator
							.next());
	
					if (devolucao.getGuiaDevolucao() != null) {
	
						if (devolucao.getGuiaDevolucao().getDocumentoTipo().getId()
								.equals(DocumentoTipo.CONTA)) {
	
							colecaoDevolucaoAvisoBancarioConta.add(devolucao);
	
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
	
							colecaoDevolucaoAvisoBancarioDebitoACobrar
									.add(devolucao);
	
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
	
							colecaoDevolucaoAvisoBancarioGuiaPagamento
									.add(devolucao);
	
						} else if (devolucao.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							colecaoDevolucaoAvisoBancarioDevolucaoValor
									.add(devolucao);
						}
	
					} else {
						if (devolucao.getAnoMesReferenciaDevolucao() != null) {
							colecaoDevolucaoAvisoBancarioConta.add(devolucao);
						} else {
							colecaoDevolucaoAvisoBancarioDevolucaoValor
									.add(devolucao);
						}
					}
	
				}
			}

			if (colecaoDevolucaoHistorico != null){
			
				Iterator colecaoDevolucaoHistoricoIterator = colecaoDevolucaoHistorico.iterator();
	
				while (colecaoDevolucaoHistoricoIterator.hasNext()) {
	
					DevolucaoHistorico devolucaoHistorico = ((DevolucaoHistorico) colecaoDevolucaoHistoricoIterator
							.next());
	
					if (devolucaoHistorico.getGuiaDevolucao() != null) {
	
						if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo().getId()
								.equals(DocumentoTipo.CONTA)) {
	
							colecaoDevolucaoHistoricoAvisoBancarioConta.add(devolucaoHistorico);
	
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEBITO_A_COBRAR)) {
	
							colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar
									.add(devolucaoHistorico);
	
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.GUIA_PAGAMENTO)) {
	
							colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento
									.add(devolucaoHistorico);
	
						} else if (devolucaoHistorico.getGuiaDevolucao().getDocumentoTipo()
								.getId().equals(DocumentoTipo.DEVOLUCAO_VALOR)) {
							colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor
									.add(devolucaoHistorico);
						}
	
					} else {
						if (devolucaoHistorico.getAnoMesReferenciaDevolucao() != null) {
							colecaoDevolucaoHistoricoAvisoBancarioConta.add(devolucaoHistorico);
						} else {
							colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor
									.add(devolucaoHistorico);
						}
					}
	
				}
			}
			
			if (colecaoDevolucaoAvisoBancarioConta != null
					&& !colecaoDevolucaoAvisoBancarioConta.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, ano/m�s refer�ncia
				// da devolu��o e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoAvisoBancarioConta,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((Devolucao) a)
										.getAnoMesReferenciaDevolucao() != null) {
									chave1 = chave1
											+ ((Devolucao) a)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((Devolucao) b)
										.getAnoMesReferenciaDevolucao() != null) {
									chave2 = chave2
											+ ((Devolucao) b)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoAvisoBancarioConta",
						colecaoDevolucaoAvisoBancarioConta);
				
				qtdeDevContas = colecaoDevolucaoAvisoBancarioConta.size();
			}
			
			if (colecaoDevolucaoHistoricoAvisoBancarioConta != null
					&& !colecaoDevolucaoHistoricoAvisoBancarioConta.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, ano/m�s refer�ncia
				// da devolu��o e
				// pela data da devolu��o
				Collections.sort((List) colecaoDevolucaoHistoricoAvisoBancarioConta,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((DevolucaoHistorico) a)
										.getAnoMesReferenciaDevolucao() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Ano/m�s refer�ncia da devolu��o
								if (((DevolucaoHistorico) b)
										.getAnoMesReferenciaDevolucao() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b)
													.getAnoMesReferenciaDevolucao()
													.toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoAvisoBancarioConta",
						colecaoDevolucaoHistoricoAvisoBancarioConta);
				qtdeDevContas = qtdeDevContas + colecaoDevolucaoHistoricoAvisoBancarioConta.size();
			}
			
			if (colecaoDevolucaoAvisoBancarioGuiaPagamento != null
					&& !colecaoDevolucaoAvisoBancarioGuiaPagamento.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort(
						(List) colecaoDevolucaoAvisoBancarioGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoAvisoBancarioGuiaPagamento",
						colecaoDevolucaoAvisoBancarioGuiaPagamento);
				qtdeDevGuiaPagamento = colecaoDevolucaoAvisoBancarioGuiaPagamento.size();
			}
			
			if (colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento != null
					&& !colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort(
						(List) colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento",
						colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento);
				qtdeDevGuiaPagamento = qtdeDevGuiaPagamento + colecaoDevolucaoHistoricoAvisoBancarioGuiaPagamento.size();
				
			}
			
			if (colecaoDevolucaoAvisoBancarioDebitoACobrar != null
					&& !colecaoDevolucaoAvisoBancarioDebitoACobrar.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort(
						(List) colecaoDevolucaoAvisoBancarioDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da Devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoAvisoBancarioDebitoACobrar",
						colecaoDevolucaoAvisoBancarioDebitoACobrar);
				qtdeDevDebitoACobrar = colecaoDevolucaoAvisoBancarioDebitoACobrar.size();
			}

			
			if (colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar != null
					&& !colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar.isEmpty()) {
				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort(
						(List) colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da Devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar",
						colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar);
				qtdeDevDebitoACobrar = qtdeDevDebitoACobrar + colecaoDevolucaoHistoricoAvisoBancarioDebitoACobrar.size();
			}

			
			if (colecaoDevolucaoAvisoBancarioDevolucaoValor != null
					&& !colecaoDevolucaoAvisoBancarioDevolucaoValor.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort(
						(List) colecaoDevolucaoAvisoBancarioDevolucaoValor,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((Devolucao) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((Devolucao) a).getImovel() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((Devolucao) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((Devolucao) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((Devolucao) b).getImovel() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((Devolucao) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((Devolucao) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((Devolucao) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((Devolucao) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoAvisoBancarioDevolucaoValor",
						colecaoDevolucaoAvisoBancarioDevolucaoValor);
				qtdeDevDevolucaoValores = colecaoDevolucaoAvisoBancarioDevolucaoValor.size();
			}

			if (colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor != null
					&& !colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor.isEmpty()) {

				// [SB0005] - Ordenar Devolu��es Hist�rico de Acordo com o Tipo de
				// Documento
				// Ordena a cole��o pela localidade, imovel, tipo de d�bito e
				// pela data da devolu��o
				Collections.sort(
						(List) colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor,
						new Comparator() {
							public int compare(Object a, Object b) {

								// Cria as chaves usadas para fazer as
								// compara��es

								String chave1 = "";
								String chave2 = "";

								// Verifica se os campos s�o nulos, em caso
								// afirmativo seta na chave um espa�o em branco
								// para servir de diferencia��o entre as
								// strings, evitando assim que uma devolu��o sem
								// localidade venha no meio de outras com
								// localidade e em caso negativo coloca-o na
								// chave

								// Localidade
								if (((DevolucaoHistorico) a).getLocalidade() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getLocalidade()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) a).getImovel() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getImovel()
													.getId().toString();
								} else {
									chave1 = chave1 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) a).getDebitoTipo() != null) {
									chave1 = chave1
											+ ((DevolucaoHistorico) a).getDebitoTipo()
													.getId();
								} else {
									chave1 = chave1 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) a).getDataDevolucao() != null) {
									chave1 = chave1
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave1 = chave1 + " ";
								}

								// Localidade
								if (((DevolucaoHistorico) b).getLocalidade() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getLocalidade()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Im�vel
								if (((DevolucaoHistorico) b).getImovel() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getImovel()
													.getId().toString();
								} else {
									chave2 = chave2 + " ";
								}

								// Tipo de D�bito
								if (((DevolucaoHistorico) b).getDebitoTipo() != null) {
									chave2 = chave2
											+ ((DevolucaoHistorico) b).getDebitoTipo()
													.getId();
								} else {
									chave2 = chave2 + " ";
								}

								// Data da devolu��o
								if (((DevolucaoHistorico) b).getDataDevolucao() != null) {
									chave2 = chave2
											+ Util
													.recuperaDataInvertida(((DevolucaoHistorico) a)
															.getDataDevolucao());
								} else {
									chave2 = chave2 + " ";
								}

								return chave1.compareTo(chave2);

							}
						});
				httpServletRequest.setAttribute(
						"colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor",
						colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor);
				qtdeDevDevolucaoValores = qtdeDevDevolucaoValores + colecaoDevolucaoHistoricoAvisoBancarioDevolucaoValor.size();
				
			}

		}
		
		sessao.setAttribute("qtdeDevContas",qtdeDevContas);
		sessao.setAttribute("qtdeDevGuiaPagamento",qtdeDevGuiaPagamento);
		sessao.setAttribute("qtdeDevDebitoACobrar",qtdeDevDebitoACobrar);
		sessao.setAttribute("qtdeDevDevolucaoValores",qtdeDevDevolucaoValores);
		
		return retorno;
	}
}
