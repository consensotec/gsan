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
package gcom.faturamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadraFace;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.micromedicao.FiltroLeituraSituacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;

/**
 * Controlador Faturamento COSANPA
 *
 * @author Raphael Rossiter
 * @date 24/04/2009
 */
public class ControladorFaturamentoCOSANPASEJB extends ControladorFaturamento implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COSANPA
	//==============================================================================================================
	
	/**
	 * Metodo responsovel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * @author  Tiago Moreno
	 * @date 24/09/2009
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param faturamentoGrupo
	 * @throws ControladorException
	 */
	public void emitirContas(Integer anoMesReferenciaFaturamento,
			FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada,
			int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE,
						idEmpresa == null ? 0 : idEmpresa);
		
		try {
			SistemaParametro sistemaParametro = null;

			int quantidadeContas = 0;

			final int quantidadeRegistros = 1000;
			int numeroIndice = 0;
			int numeroIndiceAntecipado = 0;

			try {
				
				sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
				
				boolean ehFaturamentoAntecipado = false;
				
				Integer anoMesReferenciaFaturamentoAntecipado = null;
				//anoMesReferenciaFaturamento = 200911;
				if (Util.obterMes(anoMesReferenciaFaturamento) == 11) {
					if(sistemaParametro.getIndicadorFaturamentoAntecipado().equals(ConstantesSistema.SIM)){
						
						ehFaturamentoAntecipado = true;
						anoMesReferenciaFaturamentoAntecipado = 
							Util.somarData(anoMesReferenciaFaturamento);
					}
				}

				// recebe todos as contas da lista
				StringBuilder contasTxtLista = null;
				Map<Integer, Integer> mapAtualizaSequencial = null;

				try {

					boolean flagTerminou = false;
					numeroIndice = 0;
					numeroIndiceAntecipado = 0;
					Integer sequencialImpressao = 0;
					Collection colecaoConta = null;
					int cont = 1;

					contasTxtLista = new StringBuilder();
					// cartasTxtListaConta = new StringBuilder();

					while (!flagTerminou) {
						// map que armazena o sequencial e o numero da
						// conta para no final atualizar todos os
						// sequencias
						mapAtualizaSequencial = new HashMap();
						Collection colecaoContaParms = null;
						
						if(anoMesReferenciaFaturamentoAntecipado != null &&
								anoMesReferenciaFaturamento.intValue() == anoMesReferenciaFaturamentoAntecipado.intValue()){
								
								System.out.println("INDICE_ANTECIPADO_PESQUISA:"+numeroIndiceAntecipado);
								
								numeroIndice = numeroIndiceAntecipado;
						}

						colecaoContaParms = repositorioFaturamento
								.pesquisarContasEmitirCOSANPA(
										ContaTipo.CONTA_NORMAL, idEmpresa,
										numeroIndice,
										anoMesReferenciaFaturamento,
										faturamentoGrupo.getId());
						colecaoConta = formatarEmitirContasHelper(
								colecaoContaParms, ContaTipo.CONTA_NORMAL);

						if (colecaoConta != null && !colecaoConta.isEmpty()) {

							if (colecaoConta.size() < quantidadeRegistros) {
								flagTerminou = true;
							}

							EmitirContaHelper emitirContaHelper = null;
							Iterator iteratorConta = colecaoConta.iterator();

							// int count = 0;
							while (iteratorConta.hasNext()) {

								emitirContaHelper = null;

								emitirContaHelper = (EmitirContaHelper) iteratorConta
										.next();
								sequencialImpressao += 1;

								quantidadeContas++;
								// So para exibir no console a quantidade de
								// contas

								StringBuilder contaTxt = new StringBuilder();

								if (emitirContaHelper != null) {

									// Item 1 - Numero Fatura
									contaTxt
											.append(Util
													.completaString(
															emitirContaHelper
																	.getIdConta()
																	+ "/"
																	+ Util
																			.formatarAnoMesParaMesAnoSemBarra(emitirContaHelper
																					.getAmReferencia()),
															16));

									// Item 2 - Data de Emissao da Fatura
									contaTxt.append(Util.completaString(Util
											.formatarData(new Date()), 10));

									FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

									filtroLocalidade
											.adicionarParametro(new ParametroSimples(
													FiltroLocalidade.ID,
													emitirContaHelper
															.getIdLocalidade()));

									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
									filtroLocalidade
											.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
									
									filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("unidadeNegocio");

									Collection cLocalidade = (Collection) getControladorUtil()
											.pesquisar(filtroLocalidade,
													Localidade.class.getName());
									Localidade localidade = (Localidade) cLocalidade
											.iterator().next();

									// Item 3 - Endereco do Escritorio
									contaTxt
											.append(Util
													.completaString(
															localidade
																	.getEnderecoFormatadoTituloAbreviado(),
															120));

									// Item 4 - Telefone do Escritorio
									contaTxt.append(Util.completaString(
											localidade.getFone(), 9));

									contaTxt.append(Util.completaString(
											"06.274.757/0001-50", 18));

									contaTxt.append(Util.completaString(
											"12.050.537-1", 12));

									// Item 3
									/*FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

									filtroUnidadeNegocio
											.adicionarParametro(new ParametroSimples(
													FiltroUnidadeNegocio.ID_GERENCIA,
													emitirContaHelper
															.getIdGerenciaRegional()));

									Collection cUnidade = getControladorUtil()
											.pesquisar(
													filtroUnidadeNegocio,
													UnidadeNegocio.class
															.getName());*/
									if (localidade.getUnidadeNegocio().getId() != null && !localidade.getUnidadeNegocio().getId().equals("") ) {
										

										contaTxt.append(Util
												.adicionarZerosEsquedaNumero(2,localidade.getUnidadeNegocio().getId().toString()));
									} else {
										contaTxt.append(Util
												.adicionarZerosEsquedaNumero(2,
														"00"));
									}

									// Item 100
									contaTxt
											.append(Util
													.adicionarZerosEsquedaNumero(
															4,
															emitirContaHelper
																	.getCodigoSetorComercialConta()
																	.toString()));

									// Item 2
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(3,
													emitirContaHelper
															.getIdLocalidade()
															.toString()));

									Imovel imovelEmitido = getControladorImovel()
											.pesquisarImovel(
													emitirContaHelper
															.getIdImovel());

									// Item 5
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(4,
													new Integer(imovelEmitido
															.getQuadra()
															.getNumeroQuadra())
															.toString()));

									// Item 6
									contaTxt
											.append(Util
													.adicionarZerosEsquedaNumero(
															9,
															imovelEmitido
																	.getNumeroSequencialRota()
																	.toString()));

									// Item 7
									contaTxt
											.append(Util
													.adicionarZerosEsquedaNumero(
															1,
															imovelEmitido
																	.getIndicadorDebitoConta()
																	.toString()));

									// Item 9
									contaTxt.append(Util.completaString(
											emitirContaHelper.getIdImovel()
													.toString(), 9));

									// Item 11
									// Caso a colecao de contas seja de entrega
									// para o cliente responsavel
									if (tipoConta == 3 || tipoConta == 4) {
										String nomeCliente = null;
										if (emitirContaHelper.getNomeImovel() != null
												&& !emitirContaHelper
														.getNomeImovel()
														.equals("")) {

											nomeCliente = emitirContaHelper
													.getNomeImovel();

										} else {
											try {
												nomeCliente = repositorioFaturamento
														.pesquisarNomeClienteUsuarioConta(emitirContaHelper
																.getIdConta());

											} catch (ErroRepositorioException e) {
												throw new ControladorException(
														"erro.sistema", e);
											}
										}
										contaTxt.append(Util.completaString(
												nomeCliente, 40));
									} else {
										contaTxt.append(Util.completaString(
												emitirContaHelper
														.getNomeCliente(), 40));
									}

									// Item 12
									
									//String endereco = getControladorEndereco().pesquisarEnderecoFormatado(imovelEmitido.getId());
									
									Collection colecaoClienteImovel = repositorioClienteImovel.
										pesquisarClienteImovelResponsavelConta(emitirContaHelper.getIdImovel());
								
									String endereco = "";
									String municipioEntrega = "";
									String bairroEntrega = "";
									String cepEntrega = "";
									String ufEntrega = "";
									String logCepClie = "";
									String logBairroClie = "";
									
									if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()){
										ClienteImovel clienteImovelRespConta = (ClienteImovel) colecaoClienteImovel.iterator().next();
										
										if (clienteImovelRespConta != null){
											Cliente cliente = clienteImovelRespConta.getCliente();
										
											if (cliente != null){
												String[] enderecoCliente = getControladorEndereco().pesquisarEnderecoClienteAbreviadoDivididoCosanpa(cliente.getId());
												bairroEntrega = enderecoCliente[3];
												municipioEntrega = enderecoCliente[1];
												ufEntrega = enderecoCliente[2];
												cepEntrega = enderecoCliente[4];
												logCepClie = enderecoCliente[5];
												logBairroClie = enderecoCliente[6];
												
												endereco = enderecoCliente[0] +" - "+ bairroEntrega +" "+ municipioEntrega + " " + ufEntrega +" "+ cepEntrega;
												
											}
										}
									}
									
									String[] enderecoImovel2 = getControladorEndereco().pesquisarEnderecoFormatadoDivididoCosanpa(emitirContaHelper.getIdImovel());
									
									String municipioImovel = enderecoImovel2[1];
									String logCepImovel = "";
									String logBairroImovel = "";
									
									logCepImovel = enderecoImovel2[5];
									logBairroImovel = enderecoImovel2[6];
									
									if (municipioEntrega.equalsIgnoreCase("")){
										municipioEntrega = municipioImovel;
									}
									
									if (logCepClie.trim().equalsIgnoreCase("")){
										logCepClie = logCepImovel;
									}
									
									if (logBairroClie.trim().equalsIgnoreCase("")){
										logBairroClie = logBairroImovel;
									}

									if (endereco == null || endereco.trim().equalsIgnoreCase("") || endereco.trim().equalsIgnoreCase("-")){
										endereco = getControladorEndereco().pesquisarEnderecoFormatado(imovelEmitido.getId());
									}
									
									contaTxt.append(Util.completaString(endereco, 120));

									// Item 13
									Categoria categoriaImovel = (Categoria) getControladorImovel()
											.obterPrincipalCategoriaImovel(
													emitirContaHelper
															.getIdImovel());

									contaTxt
											.append(Util
													.completaString(
															categoriaImovel
																	.getDescricao(),
															30));

									Collection colecaoSubcategoria = getControladorImovel()
											.obterQuantidadeEconomiasSubCategoria(
													imovelEmitido.getId());

									String subCat = "";
									
									for (Iterator iter = colecaoSubcategoria
											.iterator(); iter.hasNext();) {
										Subcategoria subCategoria = (Subcategoria) iter.next();
										
										subCat = subCat + subCategoria.getDescricaoAbreviada() + " ";
									}
									
									
									/*Subcategoria subcategoria = (Subcategoria) colecaoSubcategoria
											.iterator().next();
									*/
									
									
									contaTxt.append(Util.completaString(
											subCat, 30));

									// Item 14
									Collection cIS = getControladorImovel()
											.pesquisarImovelSubcategoria(
													imovelEmitido);

									boolean residencial = false;
									boolean comercial = false;
									boolean industrial = false;
									boolean publico = false;

									int economiaResidencial = 0;
									int economiaComercial = 0;
									int economiaIndustrial = 0;
									int economiaPublica = 0;
									int economiaTotal = 0;

									// acumulando as economias por categoria
									for (Iterator iter = cIS.iterator(); iter
											.hasNext();) {
										ImovelSubcategoria iS = (ImovelSubcategoria) iter
												.next();

										if (iS.getComp_id().getSubcategoria()
												.getCategoria().getId().equals(
														1)) {
											residencial = true;
											economiaResidencial = economiaResidencial
													+ iS
															.getQuantidadeEconomias();
											economiaTotal = economiaTotal
													+ iS
															.getQuantidadeEconomias();
										}

										if (iS.getComp_id().getSubcategoria()
												.getCategoria().getId().equals(
														2)) {
											comercial = true;
											economiaComercial = economiaComercial
													+ iS
															.getQuantidadeEconomias();
											economiaTotal = economiaTotal
													+ iS
															.getQuantidadeEconomias();
										}

										if (iS.getComp_id().getSubcategoria()
												.getCategoria().getId().equals(
														3)) {
											comercial = true;
											economiaIndustrial = economiaIndustrial
													+ iS
															.getQuantidadeEconomias();
											economiaTotal = economiaTotal
													+ iS
															.getQuantidadeEconomias();
										}

										if (iS.getComp_id().getSubcategoria()
												.getCategoria().getId().equals(
														4)) {
											publico = true;
											economiaPublica = economiaPublica
													+ iS
															.getQuantidadeEconomias();
											economiaTotal = economiaTotal
													+ iS
															.getQuantidadeEconomias();
										}
									}

									String economiaPorCategoria = "";

									if (residencial) {
										economiaPorCategoria = "R"
												+ Util
														.adicionarZerosEsquedaNumero(
																3,
																economiaResidencial
																		+ "")
												+ " ";
									}

									if (comercial) {
										economiaPorCategoria = "C"
												+ Util
														.adicionarZerosEsquedaNumero(
																3,
																economiaComercial
																		+ "")
												+ " ";
									}

									if (industrial) {
										economiaPorCategoria = "I"
												+ Util
														.adicionarZerosEsquedaNumero(
																3,
																economiaIndustrial
																		+ "")
												+ " ";
									}

									if (publico) {
										economiaPorCategoria = "P"
												+ Util
														.adicionarZerosEsquedaNumero(
																3,
																economiaPublica
																		+ "")
												+ " ";
									}

									contaTxt.append(Util.completaString(
											economiaPorCategoria, 50));

									// Item 15
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(4,
													economiaTotal + ""));

									// Item 32
									String dataVencimento = Util
											.formatarData(emitirContaHelper
													.getDataVencimentoConta());
									contaTxt.append(Util.completaString(
											dataVencimento, 10));

									// Item 33
									if (imovelEmitido.getLigacaoAgua() != null) {

										if (imovelEmitido
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico() != null) {

											if (imovelEmitido
													.getLigacaoAgua()
													.getHidrometroInstalacaoHistorico()
													.getHidrometro() != null) {

												contaTxt
														.append(Util
																.completaString(
																		imovelEmitido
																				.getLigacaoAgua()
																				.getHidrometroInstalacaoHistorico()
																				.getHidrometro()
																				.getNumero(),
																		12));
											} else {
												contaTxt
														.append(Util
																.completaString(
																		" ", 12));
											}

										} else {
											contaTxt.append(Util
													.completaString(" ", 12));
										}

									} else {
										contaTxt.append(Util.completaString(
												" ", 12));
									}

									Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
									Integer tipoLigacao = parmSituacao[0];
									Integer tipoMedicao = parmSituacao[1];

									Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(
											emitirContaHelper, tipoMedicao);
									// Leitura Anterior
									String leituraAnterior = "0";
									// Leitura Atual
									String leituraAtual = "0";
									// Data Leitura Anterior
									String dataLeituraAnterior = "";
									// Leitura Anterior
									String dataLeituraAtual = "";
									
									String idSituacaoLeituraAtual = "0";
									
									//Date dtLeitura = new Date();

									if (parmsMedicaoHistorico != null) {

										if (parmsMedicaoHistorico[0] != null) {
											leituraAnterior = ""
													+ (Integer) parmsMedicaoHistorico[0];
										}

										if (parmsMedicaoHistorico[1] != null) {
											leituraAtual = ""
													+ (Integer) parmsMedicaoHistorico[1];
										}

										if (parmsMedicaoHistorico[3] != null) {
											dataLeituraAnterior = Util
													.formatarData((Date) parmsMedicaoHistorico[3]);
										}

										if (parmsMedicaoHistorico[2] != null) {
											dataLeituraAtual = Util
													.formatarData((Date) parmsMedicaoHistorico[2]);
										}
										if (parmsMedicaoHistorico[4] != null) {
											idSituacaoLeituraAtual = ""+ (Integer) parmsMedicaoHistorico[4];
										}
									}
									Object[] parmsConsumoHistorico = null;
									String consumoMedio = "0";
									String mensagemContaAnormalidade = "";
									if (tipoLigacao != null) {
										try {
											parmsConsumoHistorico = repositorioMicromedicao
													.obterDadosConsumoConta(
															emitirContaHelper
																	.getIdImovel(),
															emitirContaHelper
																	.getAmReferencia(),
															tipoLigacao);

										} catch (ErroRepositorioException e) {
											sessionContext.setRollbackOnly();
											throw new ControladorException(
													"erro.sistema", e);
										}

										if (parmsConsumoHistorico != null) {

											// Consumo medio
											if (parmsConsumoHistorico[2] != null) {
												consumoMedio = ""
														+ (Integer) parmsConsumoHistorico[2];
											}

											if (parmsConsumoHistorico[6] != null) {
												mensagemContaAnormalidade = (String) parmsConsumoHistorico[6];
											}
										} 
									}
									// Item 18
									contaTxt.append(Util.completaString(
											dataLeituraAnterior, 10));

									// Item 16
									contaTxt.append(Util.completaString(
											dataLeituraAtual, 10));

									// Item 25
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(6,
													leituraAnterior));

									// Item 22
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(6,
													leituraAtual));
									
									if (imovelEmitido.getId().equals(9679227)){
										System.out.println("");
									}

									// Item 23
									Integer consumoMedido = (new Integer(
											leituraAtual)).intValue()
											- (new Integer(leituraAnterior))
													.intValue();
									
									if (consumoMedido.intValue() < 0) {
										consumoMedido = 0;
									}

									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(7,
													consumoMedido.toString()));

									String diasConsumo = "30";

									if (!dataLeituraAnterior.equals("")
											&& !dataLeituraAtual.equals("")) {
										diasConsumo = ""
												+ Util
														.obterQuantidadeDiasEntreDuasDatas(
																(Date) parmsMedicaoHistorico[3],
																(Date) parmsMedicaoHistorico[2]);
									}

									// Item 78 a 82 - Correa

									Collection colecaoContaCategoriaConsumoFaixa = null;

									try {
										colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
												.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
														.getIdConta());

									} catch (ErroRepositorioException e) {
										throw new ControladorException(
												"erro.sistema", e);
									}

									// Item 77
									Collection colecaoSubCategoria = getControladorImovel()
											.obterQuantidadeEconomiasSubCategoria(
													imovelEmitido.getId());

									Integer consumoExcesso = 0;
									Integer consumoMinimo = 0;
									BigDecimal valorExcesso = new BigDecimal(
											"0.0");
									BigDecimal valorMinimo = new BigDecimal(
											"0.0");

									// Colocado por Rafael Correa em 14/11/2008
									StringBuilder dadosContaCategoria = null;
									
									Collection<ContaCategoria> cContaCategoria = repositorioFaturamento
									.pesquisarContaCategoria(emitirContaHelper
											.getIdConta());
									
									// Caso tenha mais de uma categoria (misto)
									if (cContaCategoria.size() > 1) {
										dadosContaCategoria = obterDadosContaCategoriaMisto(cContaCategoria);
									} else {

									if (colecaoContaCategoriaConsumoFaixa == null
											|| colecaoContaCategoriaConsumoFaixa
													.isEmpty()) {

											ContaCategoria contaCategoria = (ContaCategoria) cContaCategoria
													.iterator().next();

											consumoMinimo = contaCategoria
													.getConsumoMinimoAgua();
											
											if(consumoMinimo == null){
												consumoMinimo = contaCategoria
												.getConsumoMinimoEsgoto();
											}

											/*
											 * consumoMinimo = emitirContaHelper
											 * .getConsumoAgua();
											 */

											valorMinimo = emitirContaHelper
													.getValorAgua();

									} else {
										if (!emitirContaHelper.getConsumoAgua()
												.equals(0)) {
											for (Iterator iter = colecaoContaCategoriaConsumoFaixa
													.iterator(); iter.hasNext();) {

												ContaCategoriaConsumoFaixa contaCategoriaConsumoFaixa = (ContaCategoriaConsumoFaixa) iter
														.next();
												if (contaCategoriaConsumoFaixa
														.getConsumoAgua() != null) {
													for (Iterator iteration = colecaoSubCategoria
															.iterator(); iteration
															.hasNext();) {
														Subcategoria subCategoriaEmitir = (Subcategoria) iteration
																.next();
														
														Integer fatorEconomias = null;
														if(subCategoriaEmitir.getCategoria() != null){
															if(subCategoriaEmitir.getCategoria().getFatorEconomias() != null &&
																	!subCategoriaEmitir.getCategoria().getFatorEconomias().equals("")){
															
																fatorEconomias = subCategoriaEmitir.getCategoria().getFatorEconomias().intValue();
															}
														}

														if (contaCategoriaConsumoFaixa
																.getSubcategoria()
																.getId()
																.equals(
																		subCategoriaEmitir
																				.getId())) {
															if(fatorEconomias != null && !fatorEconomias.equals("")){
																consumoExcesso = consumoExcesso
																			+ contaCategoriaConsumoFaixa
																					.getConsumoAgua()
																			* fatorEconomias;

																	valorExcesso = valorExcesso
																			.add(contaCategoriaConsumoFaixa
																					.getValorAgua()
																					.multiply(
																							new BigDecimal(fatorEconomias)));	
															}else{
																consumoExcesso = consumoExcesso
																			+ contaCategoriaConsumoFaixa
																					.getConsumoAgua()
																			* subCategoriaEmitir
																					.getQuantidadeEconomias();

																	valorExcesso = valorExcesso
																			.add(contaCategoriaConsumoFaixa
																					.getValorAgua()
																					.multiply(
																							new BigDecimal(
																									subCategoriaEmitir
																											.getQuantidadeEconomias())));
															}
														}

													}
												}
											}
										}

										valorMinimo = emitirContaHelper
												.getValorAgua().subtract(
														valorExcesso);
										consumoMinimo = emitirContaHelper
												.getConsumoAgua()
												- consumoExcesso;

									}
									
									if (consumoMinimo != null
											&& consumoMinimo == 0) {
//										Collection cContaCategoria = repositorioFaturamento
//												.pesquisarContaCategoria(emitirContaHelper
//														.getIdConta());

										ContaCategoria contaCategoria = (ContaCategoria) cContaCategoria
												.iterator().next();

										consumoMinimo = contaCategoria
												.getConsumoMinimoAgua();
										
										if(consumoMinimo == null){
											consumoMinimo = contaCategoria
											.getConsumoMinimoEsgoto();
										}
									}
									
									}

									

									// Item 24
									String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
											emitirContaHelper, tipoMedicao,
											diasConsumo);

									String consumoFaturamento = parmsConsumo[0];
									
									String consumo = "";

									if (consumoFaturamento == null || consumoFaturamento.trim().equals("") || new Integer(consumoFaturamento).intValue() >= consumoMinimo.intValue()) {

										contaTxt.append(Util
												.adicionarZerosEsquedaNumero(6,
														consumoFaturamento));
										consumo = consumoFaturamento;
									} else {
										contaTxt.append(Util
												.adicionarZerosEsquedaNumero(6,
														consumoMinimo
																.toString()));
										consumo = consumoMinimo.toString();
									}

									// Item 17
									contaTxt.append(Util
											.adicionarZerosEsquedaNumero(6,
													consumoMedio));

									// Item 19
									contaTxt.append(Util.completaString(
											diasConsumo, 2));

									// Item 20
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(emitirContaHelper
																			.getAmReferencia()),
															7));

									if (dadosContaCategoria != null) {
										contaTxt.append(Util.completaString(dadosContaCategoria
												.toString(), 315));
									} else {

										if (emitirContaHelper.getValorAgua() != null
												&& !emitirContaHelper
														.getValorAgua()
														.equals(
																new BigDecimal(
																		"0.00"))) {
											contaTxt
													.append(Util
															.completaString(
																	"AGUA ", 31));
											contaTxt.append(Util
													.completaString(
															consumoMinimo + "",
															6));
											contaTxt
													.append(Util
															.completaString(
																	valorMinimo
																			.divide(new BigDecimal(consumoMinimo.toString()),2,	BigDecimal.ROUND_DOWN)
																			+ "",
																	13));
											contaTxt.append(Util
													.completaString(valorMinimo
															+ "", 13));
										} else {
											contaTxt.append(Util
													.completaString(" ", 63));
										}

										try {
											colecaoContaCategoriaConsumoFaixa = repositorioFaturamento
													.pesquisarContaCategoriaConsumoFaixa(emitirContaHelper
															.getIdConta());

										} catch (ErroRepositorioException e) {
											throw new ControladorException(
													"erro.sistema", e);
										}

										/*int quantidadesFaixas = colecaoContaCategoriaConsumoFaixa
												.size();*/
										int quantidadesFaixasRestantes = 4 - 0;
									/*  int contadorFaixas = 0;
										for (Iterator iter = colecaoContaCategoriaConsumoFaixa
												.iterator(); iter.hasNext();) {

											ContaCategoriaConsumoFaixa cccf = (ContaCategoriaConsumoFaixa) iter
													.next();
											
											Short fatorEconomias = null;
											if (cccf.getCategoria() != null){
												fatorEconomias = getControladorImovel().pesquisarFatorEconomiasCategoria(cccf.getCategoria().getId());
											}

											if (cccf.getConsumoAgua() != null
													&& !cccf.getConsumoAgua()
															.equals(0)) {
												contaTxt.append(Util
														.completaString(
																"AGUA ", 31));
												if(fatorEconomias != null && !fatorEconomias.equals("")){
													 contaTxt
														.append(Util
																.completaString(new BigDecimal(
																		cccf.getConsumoAgua()).multiply(
																			new BigDecimal(fatorEconomias))
																				+ "", 6));
												}else{
												     contaTxt
														.append(Util
																.completaString(new BigDecimal(
																		cccf.getConsumoAgua()).multiply(
																			new BigDecimal(cccf.getContaCategoria().getQuantidadeEconomia()))
																				+ "", 6));
												}
											} else {
												contaTxt
														.append(Util
																.completaString(
																		"", 37));
											}

											if (cccf.getValorTarifaFaixa() != null
													&& !cccf
															.getValorTarifaFaixa()
															.equals("0.00")) {
												contaTxt
														.append(Util
																.completaString(
																		cccf
																				.getValorTarifaFaixa()
																				+ "",
																		13));
											} else {
												contaTxt
														.append(Util
																.completaString(
																		"", 13));
											}

											if (cccf.getValorAgua() != null
													&& !cccf.getValorAgua()
															.equals("0.00")) {
												
												
												if(fatorEconomias != null && !fatorEconomias.equals("")){
													contaTxt.append(Util
															.completaString(cccf
																	.getValorAgua().multiply(
																			new BigDecimal(fatorEconomias))
																	+ "", 13));
												}else{
												   contaTxt.append(Util
														.completaString(cccf
																.getValorAgua().multiply(
																		new BigDecimal(cccf.getContaCategoria().getQuantidadeEconomia()))
																+ "", 13));
												}
											} else {
												contaTxt
														.append(Util
																.completaString(
																		"", 13));
											}

											contadorFaixas++;
											if (contadorFaixas >= 4) {
												break;
											}
										}
*/
										contaTxt
												.append(Util
														.completaString(
																"",
																63 * quantidadesFaixasRestantes));

									}

									// Item 26
									if (emitirContaHelper.getValorAgua() != null) {
										contaTxt.append(Util.completaString(
												"TOTAL �GUA ", 50));
										contaTxt.append(Util.completaString(
												emitirContaHelper
														.getValorAgua()
														+ "", 13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 63));
									}

									// Item 27
									if (emitirContaHelper.getValorEsgoto() != null
											&& !emitirContaHelper
													.getValorEsgoto().equals(
															new BigDecimal(
																	"0.00"))) {
										/*BigDecimal percentualEsgoto = emitirContaHelper
												.getPercentualEsgotoConta()
												.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_DOWN);
										
										BigDecimal valor100PorcentoEsgoto = new BigDecimal ("0.00");
										
										if (emitirContaHelper.getValorAgua() != null 
												&& emitirContaHelper.getConsumoAgua().equals(emitirContaHelper.getConsumoEsgoto())){
											valor100PorcentoEsgoto = emitirContaHelper.getValorAgua();
										} else {
											valor100PorcentoEsgoto = emitirContaHelper
												.getValorEsgoto().divide(percentualEsgoto,2, BigDecimal.ROUND_DOWN);
										}*/
										
										
										contaTxt
												.append(Util
														.completaString(
																"ESGOTO ",
																50));
										/*contaTxt.append(Util
												.completaString(
														valor100PorcentoEsgoto
																+ "", 13));*/
										
										contaTxt.append(Util
												.completaString(
														emitirContaHelper.getValorEsgoto()
																+ "", 13));
										contaTxt
												.append(Util
														.completaString(
																" ",
																63));
										contaTxt
												.append(Util
														.completaString(
																" ",
																50));
										contaTxt
												.append(Util.completaString(" ", 13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 189));
									}

									// Item 30
									if (emitirContaHelper.getValorCreditos() != null
											&& !emitirContaHelper
													.getValorCreditos().equals(
															new BigDecimal(
																	"0.00"))) {
										contaTxt.append(Util.completaString(
												"TOTAL DE CREDITOS ", 50));
										contaTxt.append(Util.completaString(
												emitirContaHelper
														.getValorCreditos()
														+ "", 13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 63));
										// contaTxt.append(Util.completaString(emitirContaHelper.getValorCreditos()+"",
										// 13));
									}
									

									// Item 57 ao Item 76
									Conta contaId = new Conta();
									contaId.setId(emitirContaHelper
											.getIdConta());

									Collection<DebitoCobradoAgrupadoHelper> cDebitoCobrado = this
											.obterDebitosCobradosContaCAERN(contaId);

									int quantidadeLinhasSobrando = 9;
									int i = 0;

									if (cDebitoCobrado != null
											&& !cDebitoCobrado.isEmpty()) {

										int quantidadeDebitos = cDebitoCobrado
												.size();

										if (quantidadeLinhasSobrando >= quantidadeDebitos) {

											for (Iterator iter = cDebitoCobrado
													.iterator(); iter.hasNext();) {
												DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter
														.next();

												contaTxt
														.append(Util
																.completaString(
																		debitoCobrado
																				.getDescricaoDebitoTipo(),
																		30)); // 30
												contaTxt
														.append(Util
																.completaString(
																		debitoCobrado
																				.getNumeroPrestacaoDebito()
																				+ "/"
																				+ debitoCobrado
																						.getNumeroPrestacao(),
																		20));
												contaTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(debitoCobrado
																						.getValorDebito()),
																		13));

												i++;
											}

										} else {
											Iterator iter = cDebitoCobrado
													.iterator();
											int contador = 1;
											BigDecimal valorAcumulado = new BigDecimal(
													"0.00");
											boolean temOutros = false;
											while (iter.hasNext()) {
												DebitoCobradoAgrupadoHelper debitoCobrado = (DebitoCobradoAgrupadoHelper) iter
														.next();

												if (quantidadeLinhasSobrando > contador) {
													contaTxt
															.append(Util
																	.completaString(
																			debitoCobrado
																					.getDescricaoDebitoTipo(),
																			30)); // 30
													contaTxt
															.append(Util
																	.completaString(
																			debitoCobrado
																					.getNumeroPrestacaoDebito()
																					+ "/"
																					+ debitoCobrado
																							.getNumeroPrestacao(),
																			20));
													contaTxt
															.append(Util
																	.completaStringComEspacoAEsquerda(
																			Util
																					.formatarMoedaReal(debitoCobrado
																							.getValorDebito()),
																			13));
													i++;
												} else {

													valorAcumulado = valorAcumulado
															.add(debitoCobrado
																	.getValorDebito());
													temOutros = true;
												}

												contador++;
											}
											if (temOutros) {
												contaTxt
														.append("OUTROS SERVICOS               "); // 30
												contaTxt
														.append(Util
																.completaString(
																		" ", 20));
												contaTxt
														.append(Util
																.completaStringComEspacoAEsquerda(
																		Util
																				.formatarMoedaReal(valorAcumulado),
																		13));
												i++;
											}
										}
									}
									
									if (emitirContaHelper
											.getValorImpostos()!= null && !emitirContaHelper.getValorImpostos()
											.equals(new BigDecimal("0.00"))) {
										contaTxt
												.append("TOTAL DE IMPOSTOS FEDERAIS "); // 27
										contaTxt.append(Util
												.completaString(
														" ", 23));
										contaTxt
												.append(Util
														.completaStringComEspacoAEsquerda(
																Util
																		.formatarMoedaReal(emitirContaHelper
																				.getValorImpostos()),
																13));
										
									} else {
										contaTxt
										.append(Util
												.completaString(" ",63));
									}

									int quantidadeLinhasServicosSobraram = 9 - i;
									contaTxt
											.append(Util
													.completaString(
															" ",
															quantidadeLinhasServicosSobraram * 63));

									// Item 28

									FiltroConta filtroConta = new FiltroConta();

									filtroConta
											.adicionarParametro(new ParametroSimples(
													FiltroConta.ID,
													emitirContaHelper
															.getIdConta()
															+ ""));

									Collection colecaoContaEmitida = (Collection) getControladorUtil()
											.pesquisar(filtroConta,
													Conta.class.getName());

									Conta contaEmitida = (Conta) colecaoContaEmitida
											.iterator().next();

									contaTxt.append(Util.completaString(
											contaEmitida.getValorTotalConta(),
											13));

									// Item 36
									int mes1 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													1);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes1),
															7));

									// Item 37
									int mes2 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													2);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes2),
															7));

									// Item 38
									int mes3 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													3);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes3),
															7));

									// Item 39
									int mes4 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													4);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes4),
															7));

									// Item 40
									int mes5 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													5);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes5),
															7));

									// Item 41
									int mes6 = Util
											.subtrairMesDoAnoMes(
													emitirContaHelper
															.getAmReferencia(),
													6);
									contaTxt
											.append(Util
													.completaString(
															Util
																	.formatarAnoMesParaMesAno(mes6),
															7));

									// Item 48
									StringBuilder consumo1 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													1, tipoLigacao, tipoMedicao);
									
									String consumoM1 = consumo1.toString();
									
									if (consumo.equalsIgnoreCase("")){
										consumo = consumo + "0";
									}

									if (consumoM1 != null && !consumoM1.trim().equalsIgnoreCase("")) {
										contaTxt.append(Util.completaString(
												consumo1 + "", 7));
									} else {
										contaTxt.append(Util.completaString(consumo,
												7));
										consumoM1 = consumo;
									}

									// Item 49
									StringBuilder consumo2 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													2, tipoLigacao, tipoMedicao);

									String consumoM2 = consumo2.toString();

									if (consumoM2 != null && !consumoM2.trim().equalsIgnoreCase("")) {
										contaTxt.append(Util.completaString(
												consumo2 + "", 7));
									} else {
										contaTxt.append(Util.completaString(consumo,
												7));
										consumoM2 = consumo;
									}

									// Item 50
									StringBuilder consumo3 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													3, tipoLigacao, tipoMedicao);

									String consumoM3 = consumo3.toString();

									if (consumoM3 != null && !consumoM3.trim().equalsIgnoreCase("")) {
										contaTxt.append(Util.completaString(
												consumo3 + "", 7));
									} else {
										contaTxt.append(Util.completaString(consumo,
												7));
										consumoM3 = consumo;
									}

									// Item 51
									StringBuilder consumo4 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													4, tipoLigacao, tipoMedicao);

									String consumoM4 = consumo4.toString();

									if (consumoM4 != null && !consumoM4.trim().equalsIgnoreCase("")) {
										contaTxt.append(Util.completaString(
												consumo4 + "", 7));
									} else {
										contaTxt.append(Util.completaString(consumo,
												7));
										consumoM4 = consumo;
									}

									// Item 52
									StringBuilder consumo5 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													5, tipoLigacao, tipoMedicao);

									String consumoM5 = consumo5.toString();

									if (consumoM5 != null && !consumoM5.trim().equalsIgnoreCase("")) {
										contaTxt.append(Util.completaString(
												consumo5 + "", 7));
									} else {
										contaTxt.append(Util.completaString(consumo,
												7));
										consumoM5 = consumo;
									}

									// Item 53
									StringBuilder consumo6 = this
											.obterConsumoAnterior(
													emitirContaHelper
															.getIdImovel(),
													emitirContaHelper
															.getAmReferencia(),
													6, tipoLigacao, tipoMedicao);

									String consumoM6 = consumo6.toString();

									if (consumoM6 != null && !consumoM6.trim().equalsIgnoreCase("")) {
										contaTxt.append(Util.completaString(
												consumo6 + "", 7));
									} else {
										contaTxt.append(Util.completaString(consumo,
												7));
										consumoM6 = consumo;
									}

									// Item 54
									String[] parmsPartesConta = obterMensagemDebitoConta3Partes(
											emitirContaHelper, sistemaParametro);

									contaTxt.append(Util.completaString(
											parmsPartesConta[0], 100));

									// Item 55
									contaTxt.append(Util.completaString(
											parmsPartesConta[1], 100));

									// Item 56
									contaTxt.append(Util.completaString(
											parmsPartesConta[2], 100));

									// Item 99
									if (mensagemContaAnormalidade != null
											&& !mensagemContaAnormalidade
													.equalsIgnoreCase("")) {
										contaTxt
												.append(Util
														.completaString(
																mensagemContaAnormalidade,
																100));
									} else {
										contaTxt.append(Util.completaString(
												" ", 100));
									}

									// qt de faturas abertas 6 posicoes - total
									// do debito 13 posicoes
									ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper = this
											.obterDebitoImovelOuClienteHelper(
													emitirContaHelper,
													sistemaParametro);

									Collection colecaoContasValores = obterDebitoImovelOuClienteHelper
											.getColecaoContasValores();

									if (colecaoContasValores != null
											&& !colecaoContasValores.isEmpty()) {
										Integer qtContas = colecaoContasValores
												.size();
										contaTxt.append(Util.completaString(
												qtContas + "", 6));

										BigDecimal valorDebitoTotal = new BigDecimal(
												"0.00");

										for (Iterator iter = colecaoContasValores
												.iterator(); iter.hasNext();) {
											ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter
													.next();

											valorDebitoTotal = valorDebitoTotal
													.add(contaValoresHelper
															.getValorTotalConta());
										}

										contaTxt
												.append(Util
														.completaString(
																Util
																		.formatarMoedaReal(valorDebitoTotal),
																13));
									} else {
										contaTxt.append(Util.completaString(
												" ", 19));
									}

									// Item 83 e 84

									Conta conta = new Conta();
									conta.setValorAgua(emitirContaHelper
											.getValorAgua());
									conta.setValorEsgoto(emitirContaHelper
											.getValorEsgoto());
									conta.setValorCreditos(emitirContaHelper
											.getValorCreditos());
									conta.setDebitos(emitirContaHelper
											.getDebitos());
									conta.setValorImposto(emitirContaHelper
											.getValorImpostos());

									BigDecimal valorConta = conta
											.getValorTotalContaBigDecimal();

									String anoMesString = ""
											+ emitirContaHelper
													.getAmReferencia();

									String mesAnoFormatado = anoMesString
											.substring(4, 6)
											+ anoMesString.substring(0, 4);

									Integer digitoVerificadorConta = new Integer(
											""
													+ emitirContaHelper
															.getDigitoVerificadorConta());

									String representacaoNumericaCodBarra = this
											.getControladorArrecadacao()
											.obterRepresentacaoNumericaCodigoBarra(
													3,
													valorConta,
													emitirContaHelper
															.getIdLocalidade(),
													emitirContaHelper
															.getIdImovel(),
													mesAnoFormatado,
													digitoVerificadorConta,
													null, null, null, null,
													null, null, null);

									contaTxt.append(Util.completaString(
											representacaoNumericaCodBarra, 50));

									// Item 129
									contaTxt.append(Util.completaString(cont
											+ "", 8));

									// #############################################
									// PROXIMA PAGINA
									// ################################################################
									// Qualidade Agua
									
									String [] qualidade = this.obterDadosQualidadeAguaCosanpa(
											localidade.getId(), emitirContaHelper.getIdSetorComercial(), anoMesReferenciaFaturamento, imovelEmitido.getQuadraFace().getId());
									
									contaTxt.append(Util.completaString(qualidade[0],10));
									
									contaTxt.append(Util.completaString(qualidade[1],10));
									
									contaTxt.append(Util.completaString(qualidade[2],10));
									
									contaTxt.append(Util.completaString(qualidade[3],10));
									
									contaTxt.append(Util.completaString(qualidade[4],10));
									
									contaTxt.append(Util.completaString(qualidade[5],10));
									
									
									contaTxt.append(Util.completaString(qualidade[6],10));
									
									contaTxt.append(Util.completaString(qualidade[7],10));
									
									contaTxt.append(Util.completaString(qualidade[8],10));
									
									contaTxt.append(Util.completaString(qualidade[9],10));
									
									contaTxt.append(Util.completaString(qualidade[10],10));
									
									contaTxt.append(Util.completaString(qualidade[11],10));
									
									
									contaTxt.append(Util.completaString(qualidade[12],10));
									
									contaTxt.append(Util.completaString(qualidade[13],10));
									
									contaTxt.append(Util.completaString(qualidade[14],10));
									
									contaTxt.append(Util.completaString(qualidade[15],10));
									
									contaTxt.append(Util.completaString(qualidade[16],10));
									
									contaTxt.append(Util.completaString(qualidade[17],10));
									
									
									contaTxt.append(Util.completaString(qualidade[18],10));
									
									contaTxt.append(Util.completaString(qualidade[19],10));
									
									contaTxt.append(Util.completaString(qualidade[20],10));
									
									contaTxt.append(Util.completaString(qualidade[21],10));
									
									contaTxt.append(Util.completaString(qualidade[22],10));
									
									contaTxt.append(Util.completaString(qualidade[23],10));
									

									// Item 87
									String[] enderecoImovel = getControladorEndereco()
											.pesquisarEnderecoFormatadoDividido(
													emitirContaHelper
															.getIdImovel());

									contaTxt.append(Util.completaString(
											enderecoImovel[1], 30));

									// Item 88 a Item 90
									contaTxt.append(Util.completaString(
											enderecoImovel[0], 78));

									// Item 92
									contaTxt.append(Util.completaString(
											enderecoImovel[3], 20));

									// Item 93
									contaTxt.append(Util.completaString(
											enderecoImovel[4], 9));

									// Item 94
									contaTxt.append(Util.completaString(
											enderecoImovel[2], 2));
									
									/*Cliente cliente = getControladorCliente().pesquisarCliente(emitirContaHelper.getIdCliente());*/
									
									String cnpjCpf = "";
									
									Collection colecaoClienteImovel2 = repositorioClienteImovel.
										pesquisarClienteImovelResponsavelConta(emitirContaHelper.getIdImovel());
									
									if (colecaoClienteImovel2 != null && !colecaoClienteImovel2.isEmpty()){
										ClienteImovel clienteImovelRespConta2 = (ClienteImovel) colecaoClienteImovel2.iterator().next();
										
										if (clienteImovelRespConta2 != null){
											Cliente cliente2 = clienteImovelRespConta2.getCliente();
										
											if (cliente2.getCnpjFormatado() != null && !cliente2.getCnpjFormatado().equalsIgnoreCase("")){
												cnpjCpf = cliente2.getCnpjFormatado();
											} else if (cliente2.getCpfFormatado() != null && !cliente2.getCpfFormatado().equalsIgnoreCase("")){
												cnpjCpf = cliente2.getCpfFormatado();
											}
											
										}
									}
																		
									contaTxt.append(Util.completaString(cnpjCpf, 20));
									
									FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
									
									Collection colecaoConsumoTarifa = getControladorUtil().pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName());
									
									ConsumoTarifa consumoTarifa = (ConsumoTarifa) colecaoConsumoTarifa.iterator().next();
									
									contaTxt.append(Util.completaString(consumoTarifa.getDescricao(), 25));
									
									Object[] dadosRota = getControladorMicromedicao().obterRotaESequencialRotaDoImovelSeparados(imovelEmitido.getId());
									
									contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(6, dadosRota[0].toString())+"",6));
									
									contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(3, faturamentoGrupo.getId().toString())+"",3));
									
									contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(4, imovelEmitido.getLote()+"")+"",4));
									
									contaTxt.append(Util.completaString(Util.adicionarZerosEsquedaNumero(3, imovelEmitido.getSubLote()+"")+"",3));
									
									if (imovelEmitido.getAreaConstruida() != null){
										int area = imovelEmitido.getAreaConstruida().intValue();
										contaTxt.append(Util.completaString(area+"",5));
									
									} else {
										contaTxt.append(Util.completaString("",5));
									}
									
									contaTxt.append(Util.completaString(emitirContaHelper.getIdLocalidade()+"", 10));
									
									contaTxt.append(Util.completaString(endereco, 120));
									
									contaTxt.append(Util.completaString(imovelEmitido.getQuadraFace().getNumeroQuadraFace()+"",2));
									
									contaTxt.append(Util.completaString(localidade.getUnidadeNegocio().getNomeAbreviado(),6));
									
									Integer amRefMaisUm = Util.somaMesAnoMesReferencia(emitirContaHelper.getAmReferencia(), 1);
									
									FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
									
									filtroFaturamentoAtividadeCronograma.adicionarParametro(
											new ParametroSimples(FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID, "2"));
									filtroFaturamentoAtividadeCronograma.adicionarParametro(
											new ParametroSimples(FiltroFaturamentoAtividadeCronograma.
													FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA, amRefMaisUm+""));
									filtroFaturamentoAtividadeCronograma.adicionarParametro(
											new ParametroSimples(FiltroFaturamentoAtividadeCronograma.
													FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID, faturamentoGrupo.getId()+""));
									
									filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
									filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal");
									filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");
									
									Collection<FaturamentoAtividadeCronograma> cFaturamentoAtividadeCronograma = getControladorUtil().pesquisar(
											filtroFaturamentoAtividadeCronograma, FaturamentoAtividadeCronograma.class.getName());
									String dataPrevista = "";
									if (cFaturamentoAtividadeCronograma != null && !cFaturamentoAtividadeCronograma.isEmpty()){
										FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util.retonarObjetoDeColecao(cFaturamentoAtividadeCronograma);
										if (faturamentoAtividadeCronograma.getDataPrevista() != null){
											dataPrevista = Util.formatarData(faturamentoAtividadeCronograma.getDataPrevista());
										} 
									}
									
									contaTxt.append(Util.completaString(dataPrevista,10));
									
									String leituraSituacaoDescricao = "";
									
									FiltroLeituraSituacao filtroLeituraSituacao = new FiltroLeituraSituacao();
									filtroLeituraSituacao.adicionarParametro(new ParametroSimples(
											FiltroLeituraSituacao.ID, idSituacaoLeituraAtual));
									
									
									Collection<LeituraSituacao> colecaoLeituraSituacao =  getControladorUtil().pesquisar(filtroLeituraSituacao, LeituraSituacao.class.getName());
									if (colecaoLeituraSituacao != null && !colecaoLeituraSituacao.isEmpty()){
										LeituraSituacao LeituraSituacao = (LeituraSituacao) Util.retonarObjetoDeColecao(colecaoLeituraSituacao);
										if (LeituraSituacao != null){
											leituraSituacaoDescricao = LeituraSituacao.getDescricao();
										}
									}
									
									contaTxt.append(Util.completaString(leituraSituacaoDescricao,20));
									
									// TipoCaptacao
									if(!qualidade[24].trim().equalsIgnoreCase("")){
										contaTxt.append(Util.completaString(qualidade[24].toString(),1));
									} else {
										contaTxt.append(Util.completaString("1",1));
									}
									
									
									String mensagemBonusSocial = "Para usufruir dos beneficios do Bonus Social e necessario ".toUpperCase() +
											"efetuar o pagamento desta conta ate a data de vencimento".toUpperCase();
									
									Integer idPerfilBonusSocial = new Integer(4);
									
									if (imovelEmitido.getImovelPerfil().getId().equals(idPerfilBonusSocial)){
										contaTxt.append(Util.completaString(mensagemBonusSocial,150));
									}else {
										contaTxt.append(Util.completaString("",150));
									}
									
									if (!logCepClie.equalsIgnoreCase(logCepImovel) || !logBairroClie.equalsIgnoreCase(logBairroImovel)){
										contaTxt.append(Util.completaString(municipioEntrega,40));
									} else {
										contaTxt.append(Util.completaString("",40));
									}
									
									contaTxt.append(Util.completaString(localidade.getUnidadeNegocio().getNome(),30));
									
									String[] parmsPartesContaMensagem = this.obterMensagemConta3Partes(
											emitirContaHelper, sistemaParametro);

									contaTxt.append(Util.completaString(
											parmsPartesContaMensagem[0], 100));

									contaTxt.append(Util.completaString(
											parmsPartesContaMensagem[1], 100));

									contaTxt.append(Util.completaString(
											parmsPartesContaMensagem[2], 100));
									
									// Hist�rico de dias de consumo
									String diasConsumo1 = "30";
									String diasConsumo2 = "30";
									String diasConsumo3 = "30";
									String diasConsumo4 = "30";
									String diasConsumo5 = "30";
									String diasConsumo6 = "30";
									String dataLeituraAnterior1 = "";
									String dataLeituraAnterior2 = "";
									String dataLeituraAnterior3 = "";
									String dataLeituraAnterior4 = "";
									String dataLeituraAnterior5 = "";
									String dataLeituraAnterior6 = "";
									String dataLeituraAtual1 = "";
									String dataLeituraAtual2 = "";
									String dataLeituraAtual3 = "";
									String dataLeituraAtual4 = "";
									String dataLeituraAtual5 = "";
									String dataLeituraAtual6 = "";
									
									Object[] parms1 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), mes1);
	
									if (parms1 != null) {

										if (parms1[0] != null) {
											dataLeituraAnterior1 = Util.formatarData((Date)  parms1[0]);
										}
										if (parms1[1] != null) {
											dataLeituraAtual1 = Util.formatarData((Date)  parms1[1]);
										}
										
										if (!dataLeituraAnterior1.equals("") && !dataLeituraAtual1.equals("")) {
											diasConsumo1 = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms1[0], (Date) parms1[1]);
											if (diasConsumo1.equalsIgnoreCase("0")){
												diasConsumo1 = "30";
											}
										}
									}
									
									Object[] parms2 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), mes2);
									if (parms2 != null) {

										if (parms2[0] != null) {
											dataLeituraAnterior2 = Util.formatarData((Date)  parms2[0]);
										}
										if (parms2[1] != null) {
											dataLeituraAtual2 = Util.formatarData((Date)  parms2[1]);
										}
										
										if (!dataLeituraAnterior2.equals("") && !dataLeituraAtual2.equals("")) {
											diasConsumo2 = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms2[0],(Date) parms2[1]);
											if (diasConsumo2.equalsIgnoreCase("0")){
												diasConsumo2 = "30";
											}
										}
									}
									
									Object[] parms3 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), mes3);
									if (parms3 != null) {

										if (parms3[0] != null) {
											dataLeituraAnterior3 = Util.formatarData((Date)  parms3[0]);
										}
										if (parms3[1] != null) {
											dataLeituraAtual3 = Util.formatarData((Date)  parms3[1]);
										}
										
										if (!dataLeituraAnterior3.equals("") && !dataLeituraAtual3.equals("")) {
											diasConsumo3 = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms3[0],(Date) parms3[1]);
											if (diasConsumo3.equalsIgnoreCase("0")){
												diasConsumo3 = "30";
											}
										}
									}
									
									Object[] parms4 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), mes4);
									if (parms4 != null) {

										if (parms4[0] != null) {
											dataLeituraAnterior4 = Util.formatarData((Date)  parms4[0]);
										}
										if (parms4[1] != null) {
											dataLeituraAtual4 = Util.formatarData((Date)  parms4[1]);
										}
										
										if (!dataLeituraAnterior4.equals("") && !dataLeituraAtual4.equals("")) {
											diasConsumo4 = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms4[0],(Date) parms4[1]);
											if (diasConsumo4.equalsIgnoreCase("0")){
												diasConsumo4 = "30";
											}
										}
									}
									
									Object[] parms5 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), mes5);
									if (parms5 != null) {

										if (parms5[0] != null) {
											dataLeituraAnterior5 = Util.formatarData((Date)  parms5[0]);
										}
										if (parms5[1] != null) {
											dataLeituraAtual5 = Util.formatarData((Date)  parms5[1]);
										}
										
										if (!dataLeituraAnterior5.equals("") && !dataLeituraAtual5.equals("")) {
											diasConsumo5 = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms5[0],(Date) parms5[1]);
											if (diasConsumo5.equalsIgnoreCase("0")){
												diasConsumo5 = "30";
											}
										}
									}
									
									Object[] parms6 = this.obterLeituraAnteriorEAtual(emitirContaHelper.getIdImovel(), mes6);
									if (parms6 != null) {

										if (parms6[0] != null) {
											dataLeituraAnterior6 = Util.formatarData((Date)  parms6[0]);
										}
										if (parms6[1] != null) {
											dataLeituraAtual6 = Util.formatarData((Date)  parms6[1]);
										}
										
										if (!dataLeituraAnterior6.equals("") && !dataLeituraAtual6.equals("")) {
											diasConsumo6 = "" + Util.obterQuantidadeDiasEntreDuasDatas((Date) parms6[0],(Date) parms6[1]);
											if (diasConsumo6.equalsIgnoreCase("0")){
												diasConsumo6 = "30";
											}
										}
									}
									
									contaTxt.append(Util.completaString(diasConsumo1, 2));
									contaTxt.append(Util.completaString(diasConsumo2, 2));
									contaTxt.append(Util.completaString(diasConsumo3, 2));
									contaTxt.append(Util.completaString(diasConsumo4, 2));
									contaTxt.append(Util.completaString(diasConsumo5, 2));
									contaTxt.append(Util.completaString(diasConsumo6, 2));
									
									//Crit�rio de apura��o - cosumoTipo - ultimos 6 meses - Apenas ligacao de agua
									String consumoTipo1 =  getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), mes1, 1);
									contaTxt.append(Util.completaString(consumoTipo1, 2));
									
									String consumoTipo2 =  getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), mes2, 1);
									contaTxt.append(Util.completaString(consumoTipo2, 2));
									
									String consumoTipo3 =  getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), mes3, 1);
									contaTxt.append(Util.completaString(consumoTipo3, 2));
									
									String consumoTipo4 =  getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), mes4, 1);
									contaTxt.append(Util.completaString(consumoTipo4, 2));
									
									String consumoTipo5 =  getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), mes5, 1);
									contaTxt.append(Util.completaString(consumoTipo5, 2));
									
									String consumoTipo6 =  getControladorMicromedicao().obterConsumoTipoImovel(imovelEmitido.getId(), mes6, 1);
									contaTxt.append(Util.completaString(consumoTipo6, 2));
									
									//Historico da M�dia de consumo por dia				
									BigDecimal consumoDB1 = Util.formatarMoedaRealparaBigDecimal(consumoM1);
									BigDecimal consumoDB2 = Util.formatarMoedaRealparaBigDecimal(consumoM2);
									BigDecimal consumoDB3 = Util.formatarMoedaRealparaBigDecimal(consumoM3);
									BigDecimal consumoDB4 = Util.formatarMoedaRealparaBigDecimal(consumoM4);
									BigDecimal consumoDB5 = Util.formatarMoedaRealparaBigDecimal(consumoM5);
									BigDecimal consumoDB6 = Util.formatarMoedaRealparaBigDecimal(consumoM6);
									
									BigDecimal diasDB1 = Util.formatarMoedaRealparaBigDecimal(diasConsumo1);
									BigDecimal diasDB2 = Util.formatarMoedaRealparaBigDecimal(diasConsumo2);
									BigDecimal diasDB3 = Util.formatarMoedaRealparaBigDecimal(diasConsumo3);
									BigDecimal diasDB4 = Util.formatarMoedaRealparaBigDecimal(diasConsumo4);
									BigDecimal diasDB5 = Util.formatarMoedaRealparaBigDecimal(diasConsumo5);
									BigDecimal diasDB6 = Util.formatarMoedaRealparaBigDecimal(diasConsumo6);
									
									String mediaConsumo1 = Util.formatarMoedaReal(consumoDB1.divide(diasDB1,2,BigDecimal.ROUND_DOWN));
									String mediaConsumo2 = Util.formatarMoedaReal(consumoDB2.divide(diasDB2,2,BigDecimal.ROUND_DOWN));
									String mediaConsumo3 = Util.formatarMoedaReal(consumoDB3.divide(diasDB3,2,BigDecimal.ROUND_DOWN));
									String mediaConsumo4 = Util.formatarMoedaReal(consumoDB4.divide(diasDB4,2,BigDecimal.ROUND_DOWN));
									String mediaConsumo5 = Util.formatarMoedaReal(consumoDB5.divide(diasDB5,2,BigDecimal.ROUND_DOWN));
									String mediaConsumo6 = Util.formatarMoedaReal(consumoDB6.divide(diasDB6,2,BigDecimal.ROUND_DOWN));
									
									contaTxt.append(Util.completaString(mediaConsumo1, 6));
									contaTxt.append(Util.completaString(mediaConsumo2, 6));
									contaTxt.append(Util.completaString(mediaConsumo3, 6));
									contaTxt.append(Util.completaString(mediaConsumo4, 6));
									contaTxt.append(Util.completaString(mediaConsumo5, 6));
									contaTxt.append(Util.completaString(mediaConsumo6, 6));
									
									//Adicionando a Linha ao StringBuilder Completo
									contasTxtLista.append(contaTxt.toString());
									conta = null;
									contaTxt = null;

									contasTxtLista.append(System
											.getProperty("line.separator"));

									// Adiciona o id da conta e o sequencial no para serem atualizados
									mapAtualizaSequencial.put(emitirContaHelper
											.getIdConta(), sequencialImpressao);
									cont++;
									
									System.out.println("ID_CONTA:"+emitirContaHelper.getIdConta()+" SEQUENCIAL:"+sequencialImpressao+" CONT:"+cont);
					                if(flagTerminou && ehFaturamentoAntecipado){
										if(anoMesReferenciaFaturamentoAntecipado != null && 
											anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()){
											
											anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
											flagTerminou = false;
											numeroIndiceAntecipado = 0;
										}
									}
								}
							}
							

						}else{
							flagTerminou = true;
							if(ehFaturamentoAntecipado){
								if(anoMesReferenciaFaturamentoAntecipado != null && 
									anoMesReferenciaFaturamento.intValue() != anoMesReferenciaFaturamentoAntecipado.intValue()){
									
									anoMesReferenciaFaturamento = anoMesReferenciaFaturamentoAntecipado;
									flagTerminou = false;
									numeroIndiceAntecipado = 0;
								}
							}
						}
						

						numeroIndice = numeroIndice + 1000;	
						
						if(mapAtualizaSequencial != null){
							
							System.out.println("NUMERO_INDICE_ANTECIPADO:"+numeroIndiceAntecipado);
							System.out.println("NUMERO_INDICE:"+numeroIndice);
							System.out.println("QTD_CONTAS:"+quantidadeContas);
							
							repositorioFaturamento
									.atualizarSequencialContaImpressao(mapAtualizaSequencial);
						}
						mapAtualizaSequencial = null;
						
						/*if (cont > 500){
							flagTerminou = true;
							break;
						}*/
						
					}// fim laco if da paginacao

					String idGrupoFaturamento = "_G" + faturamentoGrupo.getId();
					String mesReferencia = "_REF"
					+ Util.formatarAnoMesParaMesAnoSemBarra(anoMesReferenciaFaturamento);
					
					String nomeZip = null;

					nomeZip = "CONTA" + idGrupoFaturamento	+ mesReferencia;

					BufferedWriter out = null;
					ZipOutputStream zos = null;

					File compactadoTipo = new File(nomeZip + ".zip");
					File leituraTipo = new File(nomeZip + ".txt");

					if (contasTxtLista != null && contasTxtLista.length() != 0) {
						// fim de arquivo
						// ************ TIPO E *************
						zos = new ZipOutputStream(new FileOutputStream(
								compactadoTipo));
						out = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(leituraTipo
										.getAbsolutePath())));
						out.write(contasTxtLista.toString());
						out.flush();
						ZipUtil.adicionarArquivo(zos, leituraTipo);
						zos.close();
						leituraTipo.delete();
						out.close();
					}

					// limpa todos os campos
					nomeZip = null;
					out = null;
					zos = null;
					compactadoTipo = null;
					leituraTipo = null;
					contasTxtLista = null;

					tipoConta++;
				} catch (ErroRepositorioException e) {
					throw new ControladorException("erro.sistema", e);
				}

			} catch (IOException e) {
				String mensagem = e.getMessage();
				String[] inicioMensagem = mensagem.split("\\.");
				if (inicioMensagem != null
						&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
								.equals("atencao"))) {
					throw new ControladorException(mensagem);
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String mensagem = e.getMessage();
				if (mensagem != null) {
					String[] inicioMensagem = mensagem.split("\\.");
					if (inicioMensagem != null
							&& (inicioMensagem[0].equals("erro") || inicioMensagem[0]
									.equals("atencao"))) {
						throw new ControladorException(mensagem);
					} else {
						throw new ControladorException("erro.sistema", e);
					}
				} else {
					throw new ControladorException("erro.sistema", e);
				}
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execucao da Unidade de Processamento
			//
			// --------------------------------------------------------

			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer excecao que o processo
			// batch venha a lancar e garantir que a unidade de processamento do
			// batch sera atualizada com o erro ocorrido

			e.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,
					idUnidadeIniciada, true);

			throw new EJBException(e);
		}
		
		

	}
	
	/**
	 * Metodo utilizado para adicionar os dados das contas categorias no txt no caso do imovel ser misto 
	 *
	 * @author Rafael Correa
	 * @date 14/11/2008
	 *
	 * @param cContaCategoria
	 * @return
	 * @throws ControladorException
	 */
	private StringBuilder obterDadosContaCategoriaMisto(Collection<ContaCategoria> cContaCategoria) {
		
		StringBuilder retorno = new StringBuilder();
		
		for (ContaCategoria contaCategoria : cContaCategoria) {
			
			Integer consumoAgua = contaCategoria.getConsumoAgua();
			BigDecimal valorAgua = contaCategoria.getValorAgua();
			
			if (valorAgua != null && !valorAgua.equals(new BigDecimal("0.00"))) {
			
				String subcategoriaFormatada = Util.completaString(contaCategoria.getDescricao(), 15);
				String quantidadeEconomias = contaCategoria.getQuantidadeEconomia() + " UNIDADE(S)";
			
				retorno.append(Util.completaString(subcategoriaFormatada + " " + quantidadeEconomias, 31));
			
				if (consumoAgua != null && consumoAgua != 0) {
				retorno.append(Util.completaString(consumoAgua.toString(), 6));
				retorno.append(Util.completaString(valorAgua.divide(
						new BigDecimal(consumoAgua.toString()), 2,
						BigDecimal.ROUND_DOWN)
						+ "", 13));
				} else {
					retorno.append(Util.completaString("0", 6));
					retorno.append(Util.completaString(valorAgua.toString(), 13));
				}

				retorno.append(Util.completaString(valorAgua.toString(), 13));
				
			} else {
				retorno.append(Util.completaString("", 63));
//				Integer consumoEsgoto = contaCategoria.getConsumoEsgoto();
//				BigDecimal valorEsgoto = contaCategoria.getValorEsgoto();
//				
//				retorno.append(Util.completaString(consumoEsgoto.toString(), 6));
//				
//				retorno.append(Util.completaString(valorEsgoto.divide(
//						new BigDecimal(consumoEsgoto.toString()), 2,
//						BigDecimal.ROUND_DOWN)
//						+ "", 13));
//				
//				retorno.append(Util.completaString(valorEsgoto.toString(), 13));
			}
			
			
		}
		
		return retorno;
	}
	
	public StringBuilder obterConsumoAnterior(Integer idImovel, int anoMes,
			int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException {

		StringBuilder dadosConsumoAnterior = new StringBuilder();

		int anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMes, qtdMeses);

		// adiciona o mes/ano formatado com o traco

		// caso o tipo de ligacao e medicao seja diferente de nulo
		if (tipoLigacao != null && tipoMedicao != null) {
			//dadosConsumoAnterior
			//		.append(Util.completaString(mesAnoFormatado, 7));
			Object[] parmsConsumoHistorico = null;
			parmsConsumoHistorico = getControladorMicromedicao()
					.obterConsumoAnteriorAnormalidadeDoImovel(idImovel,
							anoMesSubtraido, tipoLigacao);
			Integer numeroConsumoFaturadoMes = null;
			if (parmsConsumoHistorico != null) {
				if (parmsConsumoHistorico[0] != null) {
					numeroConsumoFaturadoMes = (Integer) parmsConsumoHistorico[0];
				}
			}
			// caso o numero consumo faturado do mes for diferente de nulo
			if (numeroConsumoFaturadoMes != null) {
				dadosConsumoAnterior.append(Util.completaString(""
						+ numeroConsumoFaturadoMes, 7));
			} else {
				dadosConsumoAnterior.append(Util.completaString("", 7));
			}
			// caso o id dos dados do consumo anterior for diferente de nulo

		} else {
			// senao completa com espacos em branco
			dadosConsumoAnterior.append(Util.completaString("", 7));
		}

		return dadosConsumoAnterior;
	}
	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuClienteHelper(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {


		Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();
		int anoMesSubtraido = Util
				.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
		Integer dataVencimentoFinalInteger = sistemaParametro
				.getAnoMesArrecadacao();
		String anoMesSubtraidoString = ""
				+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
		int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
		int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

		// recupera o ultimo dia do anomes e passa a data como parametro
		Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
		dataVencimentoFinal.set(Calendar.YEAR, ano);
		dataVencimentoFinal.set(Calendar.MONTH, mes - 1);
		dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dataFinalDate = dataVencimentoFinal.getTime();

		// converte String em data
		Date dataVencimento = Util.converteStringParaDate("01/01/1900");

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(1,
						"" + emitirContaHelper.getIdImovel(), null, null,
						"190001", "" + anoMesSubtraido, dataVencimento,
						dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null,2);

		return debitoImovelClienteHelper;
	}
	
	public String[] obterDadosQualidadeAguaCosanpa (Integer idLocalidade, Integer idSetorComercial, Integer amReferencia, Integer idQuadraFace) 
		throws ControladorException{
	
		String [] retornoQualidade = new String [25];
		
		String padraoCor = "";
		String padraoTurbidez = "";
		String padraoFluor = "";
		String padraoCloro = "";
		String padraoColiformesTotais = "";
		String padraoColiformesTermotolerantes = "";
		
		String exigidaCor = "";
		String exigidaTurbidez = "";
		String exigidaFluor = "";
		String exigidaCloro = "";
		String exigidaColiformesTotais = "";
		String exigidaColiformesTermotolerantes = "";
		
		String analisadaCor = "";
		String analisadaTurbidez = "";
		String analisadaFluor = "";
		String analisadaCloro = "";
		String analisadaColiformesTotais = "";
		String analisadaColiformesTermotolerantes = "";
		
		String emConformidadeCor = "";
		String emConformidadeTurbidez = "";
		String emConformidadeFluor = "";
		String emConformidadeCloro = "";
		String emConformidadeColiformesTotais = "";
		String emConformidadeColiformesTermotolerantes = "";
		
		String tipoCaptacao = "";
		
		
		// Qualidade da Agua Padrao
		FiltroQualidadeAguaPadrao filtroQualidadeAguaPadrao = new FiltroQualidadeAguaPadrao();
		
		Collection colecaoQualidAguaPadrao = getControladorUtil().pesquisar(filtroQualidadeAguaPadrao, QualidadeAguaPadrao.class.getName());
		
		if (colecaoQualidAguaPadrao != null && !colecaoQualidAguaPadrao.isEmpty()){
	
			QualidadeAguaPadrao qualidadePadrao = (QualidadeAguaPadrao) colecaoQualidAguaPadrao.iterator().next();
		
			padraoCor = qualidadePadrao.getDescricaoPadraoCor();
			padraoTurbidez = qualidadePadrao.getDescricaoPadraoTurbidez();
			padraoFluor = qualidadePadrao.getDescricaoPadraoFluor();
			padraoCloro = qualidadePadrao.getDescricaoPadraoCloro();
			padraoColiformesTotais = qualidadePadrao.getDescricaoPadraoColiformesTotais();
			padraoColiformesTermotolerantes = qualidadePadrao.getDescricaoPadraoColiformesTermotolerantes();
		
		}
		
		// Qualidade da Agua em 4 Niveis
		
		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
		
		Collection colecaoQualidadeAgua = null;
	
		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();
		filtroQuadraFace.adicionarParametro(new ParametroSimples(FiltroQuadraFace.ID,idQuadraFace));
		
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento.fonteCaptacao");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional.setorAbastecimento.sistemaAbastecimento.fonteCaptacao.tipoCaptacao");
		
		Collection colecaoQudraFace = getControladorUtil().pesquisar(filtroQuadraFace, QuadraFace.class.getName());
				
		QuadraFace quadraFace = (QuadraFace) Util.retonarObjetoDeColecao(colecaoQudraFace);
		
		if (quadraFace.getDistritoOperacional() != null &&
			quadraFace.getDistritoOperacional().getSetorAbastecimento() != null &&
			quadraFace.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento() != null) {
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroSimples(
						FiltroQualidadeAgua.SISTEMA_ABASTECIMENTO,
						quadraFace.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento().getId()));
				
				filtroQualidadeAgua.adicionarParametro(
					new ParametroSimples(
						FiltroQualidadeAgua.ANO_MES_REFERENCIA,
						amReferencia));
				 
			if (quadraFace.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento().getFonteCaptacao() != null &&
			    quadraFace.getDistritoOperacional().getSetorAbastecimento().getSistemaAbastecimento().getFonteCaptacao().getTipoCaptacao() != null) {
				
				tipoCaptacao = quadraFace.getDistritoOperacional().getSetorAbastecimento().
								getSistemaAbastecimento().getFonteCaptacao().getTipoCaptacao().getId()+"";
			}
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");
			
			colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(
					filtroQualidadeAgua,
					QualidadeAgua.class.getName());
			
		}
		
		// Com Localidade e Setor
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			
			filtroQualidadeAgua.limparListaParametros();
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID,
					idLocalidade));
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.SETOR_COMERCIAL_ID,
					idSetorComercial));
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					amReferencia));
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");
			
			colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(
					filtroQualidadeAgua,
					QualidadeAgua.class.getName());
		}
		
		//Com Localidade
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			
			filtroQualidadeAgua.limparListaParametros();
			
			colecaoQualidadeAgua = null;
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.LOCALIDADE_ID,
					idLocalidade));
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroNulo(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					amReferencia));
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");
			
			colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(
					filtroQualidadeAgua,
					QualidadeAgua.class.getName());
		}
		
		// Sem Localidade e sem Setor
		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {
			
			filtroQualidadeAgua.limparListaParametros();
			
			filtroQualidadeAgua.adicionarParametro(
				new ParametroSimples(
					FiltroQualidadeAgua.ANO_MES_REFERENCIA,
					amReferencia));
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroNulo(
						FiltroQualidadeAgua.LOCALIDADE_ID));
			
			filtroQualidadeAgua.adicionarParametro(
					new ParametroNulo(
						FiltroQualidadeAgua.SETOR_COMERCIAL_ID));
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao.tipoCaptacao");
			
			colecaoQualidadeAgua = 
				getControladorUtil().pesquisar(
					filtroQualidadeAgua,
					QualidadeAgua.class.getName());
		}
		
		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()){
			
			QualidadeAgua qualidadeAgua = (QualidadeAgua) colecaoQualidadeAgua.iterator().next();
			
			// Exigidas
			if (qualidadeAgua.getQuantidadeCorExigidas() != null) {
				exigidaCor = qualidadeAgua.getQuantidadeCorExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeTurbidezExigidas() != null) {
				exigidaTurbidez = qualidadeAgua.getQuantidadeTurbidezExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeFluorExigidas() != null) {
				exigidaFluor = qualidadeAgua.getQuantidadeFluorExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeCloroExigidas() != null) {
				exigidaCloro = qualidadeAgua.getQuantidadeCloroExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTotaisExigidas() != null) {
				exigidaColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisExigidas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() != null) {
				exigidaColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas()+"";
			}
	
			// Analisadas
			if (qualidadeAgua.getQuantidadeCorAnalisadas() != null) {
				analisadaCor = qualidadeAgua.getQuantidadeCorAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeTurbidezAnalisadas() != null) {
				analisadaTurbidez = qualidadeAgua.getQuantidadeTurbidezAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeFluorAnalisadas() != null) {
				analisadaFluor = qualidadeAgua.getQuantidadeFluorAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeCloroAnalisadas() != null) {
				analisadaCloro = qualidadeAgua.getQuantidadeCloroAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() != null) {
				analisadaColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas() != null) {
				analisadaColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas()+"";
			}
			 
			// Em Conformidade
			if (qualidadeAgua.getQuantidadeCorConforme() != null) {
				emConformidadeCor = qualidadeAgua.getQuantidadeCorConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeTurbidezConforme() != null) {
				emConformidadeTurbidez = qualidadeAgua.getQuantidadeTurbidezConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeFluorConforme() != null) {
				emConformidadeFluor = qualidadeAgua.getQuantidadeFluorConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeCloroConforme() != null) {
				emConformidadeCloro = qualidadeAgua.getQuantidadeCloroConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTotaisConforme() != null) {
				emConformidadeColiformesTotais = qualidadeAgua.getQuantidadeColiformesTotaisConforme()+"";
			}
			
			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() != null) {
				emConformidadeColiformesTermotolerantes = qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme()+"";
			}
			
			if (qualidadeAgua.getFonteCaptacao() != null && tipoCaptacao.trim().equalsIgnoreCase("")) {
				if (qualidadeAgua.getFonteCaptacao().getTipoCaptacao() != null) {
					tipoCaptacao = qualidadeAgua.getFonteCaptacao().getTipoCaptacao().getId()+"";
				}
			}
		
		}
		
		retornoQualidade[0] = padraoCor;
		retornoQualidade[1] = padraoTurbidez;
		retornoQualidade[2] = padraoFluor;
		retornoQualidade[3] = padraoCloro;
		retornoQualidade[4] = padraoColiformesTotais;
		retornoQualidade[5] = padraoColiformesTermotolerantes;
		
		retornoQualidade[6] = exigidaCor;
		retornoQualidade[7] = exigidaTurbidez;
		retornoQualidade[8] = exigidaFluor;
		retornoQualidade[9] = exigidaCloro;
		retornoQualidade[10] = exigidaColiformesTotais;
		retornoQualidade[11] = exigidaColiformesTermotolerantes;
		
		retornoQualidade[12] = analisadaCor;
		retornoQualidade[13] = analisadaTurbidez;
		retornoQualidade[14] = analisadaFluor;
		retornoQualidade[15] = analisadaCloro;
		retornoQualidade[16] = analisadaColiformesTotais;
		retornoQualidade[17] = analisadaColiformesTermotolerantes;
		
		retornoQualidade[18] = emConformidadeCor;
		retornoQualidade[19] = emConformidadeTurbidez;
		retornoQualidade[20] = emConformidadeFluor;
		retornoQualidade[21] = emConformidadeCloro;
		retornoQualidade[22] = emConformidadeColiformesTotais;
		retornoQualidade[23] = emConformidadeColiformesTermotolerantes;
		
		retornoQualidade[24] = tipoCaptacao;
		
		
		return retornoQualidade;
	}
	
	
	/**
	 * Este caso de uso gera os d�bitos a cobrar referentes aos acr�scimos por
	 * impontualidade (multa, juros de mora e atualiza��o monet�ria)
	 * 
	 * [UC0302] - Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade Autor:
	 * 
	 * @author Fernanda Paiva, Pedro Alexandre, Pedro Aexandre
	 * @date 20/04/2006, 31/08/2006, 23/04/2008
	 * 
	 * @param rotas
	 * @param indicadorGeracaoMulta
	 * @param indicadorGeracaoJuros
	 * @param indicadorGeracaoAtualizacao
	 * @return
	 * @throws ControladorException
	 */
	public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(
			Collection rotas, 
			Short indicadorGeracaoMulta,
			Short indicadorGeracaoJuros, 
			Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, 
			boolean indicadorEncerrandoArrecadacao)
			throws ControladorException {

		int idUnidadeIniciada = 0;

		try {
			Rota rota = (Rota) Util.retonarObjetoDeColecao(rotas);
			
			// -------------------------
			//
			// Registrar o in�cio do processamento da Unidade de
			// Processamento
			// do Batch
			//
			// -------------------------
			idUnidadeIniciada = getControladorBatch()
					.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
							UnidadeProcessamento.ROTA,
							rota.getId());

			// cria uma cole��o de im�vel por rota
			Collection imoveisPorRota = null;
			Collection colecaoDebitoACobrarInserir = new ArrayList();
			Collection colecaoDebitoACobrarCategoriaInserir = new ArrayList();

			
			
			// recupera todos os im�veis da cole��o de rotas
			imoveisPorRota = this.pesquisarImovelGerarAcrescimosImpontualidade(rota);
			
			SistemaParametro sistemaParametros = getControladorUtil().pesquisarParametrosDoSistema();

			Integer anoMesReferenciaArrecadacao = sistemaParametros.getAnoMesArrecadacao();
			
			Integer anoMesReferenciaFaturamento = sistemaParametros.getAnoMesFaturamento();

			Short codigoEmpresaFebraban = sistemaParametros.getCodigoEmpresaFebraban();
			
			Iterator imovelPorRotaIterator = imoveisPorRota.iterator();
			
			//Item 5.1 [UC0306] - Obter Principal Categoria do Im�vel
			Map<Integer,Categoria> mapImovelPrincipalCategoria = 
			this.pesquisarPrincipalCategoriaImovelPorRota(codigoEmpresaFebraban, rota);
			
			/**
			 * Item 5.4 Caso o im�vel possua cliente respons�vel, recupera o
			 * indicador de cobran�a de acr�cimos do cliente respons�vel
			 * (CLIE_ICCOBRANCAACRESCIMOS)
			 */
			Map<Integer,Short> mapIndicadorAcrescimoCliente = 
			this.obterIndicadorGeracaoAcrescimosClienteImovel(rota);
			
			while (imovelPorRotaIterator.hasNext()) {
				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayImoveisPorRota = (Object[]) imovelPorRotaIterator.next();

				// instancia um im�vel
				Imovel imovel = new Imovel();
				if (arrayImoveisPorRota[0] != null) {
					// seta o id no imovel
					imovel.setId((Integer) arrayImoveisPorRota[0]);
				}
				
				if (arrayImoveisPorRota[4] != null) {
					// seta o lote no imovel
					imovel.setLote((Short) arrayImoveisPorRota[4]);
				}

				if (arrayImoveisPorRota[5] != null) {
					// seta o sublote no imovel
					imovel.setSubLote((Short) arrayImoveisPorRota[5]);
				}

				Localidade localidade = new Localidade();
				if (arrayImoveisPorRota[1] != null) {
					// instancia uma localidade para ser setado no im�vel
					localidade.setId((Integer) arrayImoveisPorRota[1]);
					imovel.setLocalidade(localidade);
				}

				Quadra quadra = new Quadra();
				if (arrayImoveisPorRota[3] != null) {
					// instancia uma quadra para ser setado no im�vel
					Integer numeroQuadra = (Integer) arrayImoveisPorRota[3];
					Integer idQuadra = (Integer) arrayImoveisPorRota[7];
					quadra.setId(idQuadra);
					quadra.setNumeroQuadra(numeroQuadra);
					imovel.setQuadra(quadra);
				}

				Integer setorComercial = null;
				if (arrayImoveisPorRota[2] != null) {
					// instancia um setor comercial para ser setado no im�vel
					setorComercial = (Integer) arrayImoveisPorRota[2];
				}
				
				/*
				 * Colocado por Raphael Rossiter em 31/05/2007
				 */
				if (arrayImoveisPorRota[8] != null) {
					imovel.setIndicadorDebitoConta((Short) arrayImoveisPorRota[8]);
				}

				
				// Item 5.1 [UC0306] - Obter Principal Categoria do Im�vel
				Categoria principalCategoria = mapImovelPrincipalCategoria.get(imovel.getId());
				
				boolean flagProximoImovel = false;
				
				/**
				 * Item 5.2 Caso a principal categoria do im�vel esteja
				 * indicando que somente deve ser gerado acr�scimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=NAO) da principal categoria do
				 * im�vel, passa para o pr�ximo im�vel.
				 */
				if (principalCategoria.getIndicadorCobrancaAcrescimos().equals(
						ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				/**
				 * Item 5.3 Caso a principal categoria do im�vel esteja
				 * indicando que n�o deve ser gerado acr�scimos por
				 * impontualidade para a categoria
				 * (catg_icgeracaoacrescimos=ENCERRAMENTO_ARRECADACAO) da
				 * principal categoria do im�vel e esteja indicando que n�o est�
				 * sendo encerrada a arrecada��o , passa para o pr�ximo im�vel.
				 */
				if ((principalCategoria!= null && principalCategoria.getIndicadorCobrancaAcrescimos().equals(ConstantesSistema.ENCERRAMENTO_ARRECADACAO)) && !indicadorEncerrandoArrecadacao) {
					flagProximoImovel = true;
				}

				/**
				 * Item 5.4 Caso o im�vel possua cliente respons�vel, recupera o
				 * indicador de cobran�a de acr�cimos do cliente respons�vel
				 * (CLIE_ICCOBRANCAACRESCIMOS)
				 */
				Short indicadorCobrancaAcrescimos = mapIndicadorAcrescimoCliente.get(imovel.getId());
				
				/**
				 * Item 5.4.1 Caso esteja indicado que n�o de ve ser gerado
				 * acr�cimos por impontualidade para o cliente
				 * (CLIE_ICCOBRANCAACRESCIMOS=NAO) , passar para o pr�ximo
				 * im�vel
				 */
				if (indicadorCobrancaAcrescimos != null	&& indicadorCobrancaAcrescimos.equals(ConstantesSistema.NAO)) {
					flagProximoImovel = true;
				}

				if (indicadorCobrancaAcrescimos != null	&& (indicadorCobrancaAcrescimos.equals(ConstantesSistema.NAO) && !indicadorEncerrandoArrecadacao)) {
					flagProximoImovel = true;
				}

				if (!flagProximoImovel) {

					Date dataAnoMesReferenciaUltimoDia = Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaArrecadacao);

					Collection<Integer> colecaoIdsContasAtualizarIndicadorMulta = new ArrayList();

					// cria uma cole��o de contas do imovel
					Collection colecaoContaImovel = null;

					/*
					 * Item 5.5 Caso esteja indicado que N�O esteja sendo encerrada 
					 * a arrecadac�o seleciona as contas do im�vel com ano/m�s da 
					 * data de vencimento menor ou igual ao ano/m�s de refer�ncia 
					 * da arrecada��o corrente e com situa��o atual correspondente a normal,
					 * retificada ou inclu�da e que n�o estejam em revis�o 
					 * e que ainda n�o tiveram cobran�a de multa.  
					 */
					if(!indicadorEncerrandoArrecadacao){
						// recupera todas as contas dos im�veis da cole��o
						// de rotas
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovel(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia);
					}else{
						// recupera todas as contas dos im�veis da cole��o
						// de rotas
						colecaoContaImovel = repositorioFaturamento
								.obterContasImovelComPagamento(imovel.getId(),
										DebitoCreditoSituacao.NORMAL,
										DebitoCreditoSituacao.INCLUIDA,
										DebitoCreditoSituacao.RETIFICADA,
										dataAnoMesReferenciaUltimoDia,
										anoMesReferenciaArrecadacao);
					}
					
					Map<Integer,Boolean> mapIndicadorExistePagamentoConta = this.pesquisarIndicadorPagamentoConta(colecaoContaImovel,anoMesReferenciaArrecadacao);
					
					Short numeroPrestacaoDebito = 1;
					Short numeroPrestacaoCobradas = 0;

					if ( !Util.isVazioOrNulo(colecaoContaImovel)) {

						Iterator contasIterator = colecaoContaImovel.iterator();

						while (contasIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosConta = (Object[]) contasIterator.next();

							Integer anoMes = Util.recuperaAnoMesDaData((Date) dadosConta[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								Integer idConta = (Integer) dadosConta[0];
								Conta conta = new Conta();
								if (dadosConta[0] != null) {
									// seta o id da conta
									conta.setId((Integer) dadosConta[0]);
								}
								if (dadosConta[1] != null) {
									// seta o ano/mes referencia da
									// conta
									conta.setReferencia((Integer) dadosConta[1]);
								}
								if (dadosConta[2] != null) {
									// seta a data de vencimento da
									// conta
									conta.setDataVencimentoConta((Date) dadosConta[2]);
								}
								if (dadosConta[3] != null) {
									// seta o valor da �gua
									conta.setValorAgua((BigDecimal) dadosConta[3]);
								}
								if (dadosConta[4] != null) {
									// seta o valor do esgoto
									conta.setValorEsgoto((BigDecimal) dadosConta[4]);
								}
								if (dadosConta[5] != null) {
									// seta o valor dos debitos
									conta.setDebitos((BigDecimal) dadosConta[5]);
								}
								if (dadosConta[6] != null) {
									// seta o valor dos creditos
									conta.setValorCreditos((BigDecimal) dadosConta[6]);
								}
								if (dadosConta[7] != null) {
									// seta o indicador de cobranca da
									// multa
									conta.setIndicadorCobrancaMulta((Short) dadosConta[7]);
								}

								// cria uma cole��o dos pagamentos da
								// conta com menor
								// data de pagamento
								Date pagamentoContasMenorData = null;
								Integer idArrecadacaoForma = null;

								// recupera todos os pagamentos da conta
								// com menor data de pagamento
								Object[] arrayPagamentoContasMenorData = repositorioFaturamento
								.obterArrecadacaoFormaPagamentoContasMenorData(idConta, imovel.getId(), conta
								.getReferencia());
								
								
								if (arrayPagamentoContasMenorData != null){
									idArrecadacaoForma = (Integer) arrayPagamentoContasMenorData[0];
									pagamentoContasMenorData = (Date) arrayPagamentoContasMenorData[1];
								}
								
								/*
								 * Colocado por Raphael Rossiter em 19/05/2008
								 * S� ir� calcular o acr�scimo caso o imovel e o pagamento n�o sejam d�bito
								 * autom�tico
								 */
								if (idArrecadacaoForma == null || 
									(idArrecadacaoForma != null && 
									!idArrecadacaoForma.equals(ArrecadacaoForma.DEBITO_AUTOMATICO))){
								
									
									boolean indicadorExistePagamentoClassificadoConta;
									//caso tenha o id da conta no map 
									//ent�o existe pagamento para a conta atual.
									if(mapIndicadorExistePagamentoConta.containsKey(idConta)){
										indicadorExistePagamentoClassificadoConta = true;
									}else{
										indicadorExistePagamentoClassificadoConta = false;
									}
								
									CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();
	
									BigDecimal valorConta = conta.getValorAgua().add(conta.getValorEsgoto()).add(conta.getDebitos()).subtract(conta.getValorCreditos());
	
									// Calcula o valor das multas cobradas
									// para
									// a conta
									BigDecimal valorMultasCobradas = repositorioFaturamento.pesquisarValorMultasCobradas(idConta);
	
									
									// Item 5.6.2 Calcular os acrescimos por impontualidade
									calcularAcrescimoPorImpontualidade = this.getControladorCobranca()
											.calcularAcrescimoPorImpontualidade(
													conta.getReferencia(),
													conta.getDataVencimentoConta(),
													pagamentoContasMenorData,
													valorConta,
													valorMultasCobradas,
													conta.getIndicadorCobrancaMulta(),
													"" + anoMesReferenciaArrecadacao,
													conta.getId(),
													ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO,
													null);
									
									DebitoTipo debitoTipo = null;

									/**
									 * Item 5.6.3 Caso o indicador de gera��o de
									 * multa corresponda a sim(1) e o valor da multa
									 * seja maior que que zero. Gera o d�bito a
									 * cobrar referente a multa.
									 */
									if (indicadorGeracaoMulta.equals(ConstantesSistema.SIM) && calcularAcrescimoPorImpontualidade.getValorMulta().compareTo(BigDecimal.ZERO) == 1 && indicadorExistePagamentoClassificadoConta) {
									
										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.MULTA_IMPONTUALIDADE);
										
										//[SB0001 - Gerar D�bito a Cobrar]
										DebitoACobrar debitoACobrar = this.gerarDebitoACobrarParaConta(
												anoMesReferenciaArrecadacao, 
												anoMesReferenciaFaturamento, 
												imovel, 
												localidade, 
												quadra, 
												setorComercial, 
												numeroPrestacaoDebito, 
												numeroPrestacaoCobradas, 
												conta, 
												calcularAcrescimoPorImpontualidade.getValorMulta(), 
												debitoTipo, Usuario.USUARIO_BATCH);
										
										colecaoIdsContasAtualizarIndicadorMulta.add(conta.getId());
										
										colecaoDebitoACobrarInserir.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir.addAll(this.inserirDebitoACobrarCategoriaBatch(debitoACobrar,debitoACobrar.getImovel()));
									}// if indicador de gera��o de multa

									/**
									 * Item 5.6.4 Caso o indicador de gera��o dos
									 * juros de mora corresponda a sim(1) e o valor
									 * dos juros de mora seja maior que zero Gera o
									 * d�bito a cobrar referente a juros de mora e
									 * exista pagamento para a conta com data de
									 * pagamento diferente de nulo e ano/m�s
									 * refer�ncia da arrecada��o do pagamento seja
									 * menor ou igual ao ano/m�s de arrecada��o
									 * corente.
									 */
									if (indicadorGeracaoJuros.equals(ConstantesSistema.SIM)	&& calcularAcrescimoPorImpontualidade.getValorJurosMora().compareTo(BigDecimal.ZERO) == 1 && indicadorExistePagamentoClassificadoConta) {		
										
										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.JUROS_MORA);
										
										//[SB0001 - Gerar D�bito a Cobrar]
										DebitoACobrar debitoACobrar = this.gerarDebitoACobrarParaConta(
												anoMesReferenciaArrecadacao, 
												anoMesReferenciaFaturamento, 
												imovel, 
												localidade, 
												quadra, 
												setorComercial, 
												numeroPrestacaoDebito, 
												numeroPrestacaoCobradas, 
												conta, 
												calcularAcrescimoPorImpontualidade.getValorJurosMora(), 
												debitoTipo, Usuario.USUARIO_BATCH);
										
										colecaoIdsContasAtualizarIndicadorMulta.add(conta.getId());
										
										colecaoDebitoACobrarInserir.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir.addAll(this.inserirDebitoACobrarCategoriaBatch(debitoACobrar,debitoACobrar.getImovel()));
									}

									/*
									 * 5.6.5 Caso o indicador de gera��o de
									 * atualiza��o monet�ria corresponda a sim(1) e
									 * o valor da atualiza��o monet�ria seja maior
									 * que zero Gera o d�bito a cobrar referente a
									 * atualiza��o monet�ria e
									 */
									if (indicadorGeracaoAtualizacao.equals(ConstantesSistema.SIM) && calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria().compareTo(BigDecimal.ZERO) == 1 && indicadorExistePagamentoClassificadoConta) {
	
										debitoTipo = new DebitoTipo();
										debitoTipo.setId(DebitoTipo.ATUALIZACAO_MONETARIA);
										
										//[SB0001 - Gerar D�bito a Cobrar]
										DebitoACobrar debitoACobrar = this.gerarDebitoACobrarParaConta(
												anoMesReferenciaArrecadacao, 
												anoMesReferenciaFaturamento, 
												imovel, 
												localidade, 
												quadra, 
												setorComercial, 
												numeroPrestacaoDebito, 
												numeroPrestacaoCobradas, 
												conta, 
												calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria(), 
												debitoTipo, Usuario.USUARIO_BATCH);
										
										colecaoIdsContasAtualizarIndicadorMulta.add(conta.getId());
										
										colecaoDebitoACobrarInserir.add(debitoACobrar);
										colecaoDebitoACobrarCategoriaInserir.addAll(this.inserirDebitoACobrarCategoriaBatch(debitoACobrar,debitoACobrar.getImovel()));
									}
								} // fim comparacao debito automatico
							} // fim if da compara��o da data de pagamento
						} // fim while contas iterator
					} // fim if cole��o conta

					/*
					 * Item 5.6.3.2 Atualiza o indicador de que j� cobrou multa
					 * da conta com o valor igual a SIM (CNTA_ICCOBRANCAMULTA=1)
					 */
					if (colecaoIdsContasAtualizarIndicadorMulta != null	&& !colecaoIdsContasAtualizarIndicadorMulta.isEmpty()) {
						repositorioFaturamento.atualizarIndicadorMultaDeConta(colecaoIdsContasAtualizarIndicadorMulta);
					}
					
					// cria uma cole��o de guias do im�vel
					Collection colecaoGuiasPagamentoImovel = null;

					Collection<Integer> colecaoIdsGuiasPagamentosAtualizarIndicadorMulta = new ArrayList();

					// recupera todas as guias dos im�veis da cole��o de rotas
					colecaoGuiasPagamentoImovel = repositorioFaturamento
							.obterGuiasPagamentoImovel(imovel.getId(),
									DebitoCreditoSituacao.NORMAL,
									DebitoCreditoSituacao.INCLUIDA,
									DebitoCreditoSituacao.RETIFICADA,
									anoMesReferenciaArrecadacao);
					
					if ( !Util.isVazioOrNulo(colecaoGuiasPagamentoImovel)) {

						Iterator guiasPagamentoIterator = colecaoGuiasPagamentoImovel.iterator();

						while (guiasPagamentoIterator.hasNext()) {
							// cria um array de objetos para pegar os
							// parametros de
							// retorno da pesquisa
							Object[] dadosGuiasPagamento = (Object[]) guiasPagamentoIterator.next();

							Integer anoMes = Util.recuperaAnoMesDaData((Date) dadosGuiasPagamento[2]);

							if (anoMes <= anoMesReferenciaArrecadacao) {

								GuiaPagamento guiaPagamento = new GuiaPagamento();
								if (dadosGuiasPagamento[0] != null) {
									// seta o id da guia
									guiaPagamento.setId((Integer) dadosGuiasPagamento[0]);
								}
								if (dadosGuiasPagamento[1] != null) {
									// seta o ano/mes referencia da guia
									guiaPagamento.setAnoMesReferenciaContabil((Integer) dadosGuiasPagamento[1]);
								}
								if (dadosGuiasPagamento[2] != null) {
									// seta a data de vencimento da
									// conta
									guiaPagamento.setDataVencimento((Date) dadosGuiasPagamento[2]);
								}
								if (dadosGuiasPagamento[3] != null) {
									// seta o valor dos debitos
									guiaPagamento.setValorDebito((BigDecimal) dadosGuiasPagamento[3]);
								}
								if (dadosGuiasPagamento[4] != null) {
									// seta o indicador de cobranca da
									// multa
									guiaPagamento.setIndicadoCobrancaMulta((Short) dadosGuiasPagamento[4]);
								}

								DebitoTipo debitoTipoGuiaPagamento = new DebitoTipo();
								if (dadosGuiasPagamento[5] != null) {
									debitoTipoGuiaPagamento.setId((Integer) dadosGuiasPagamento[5]);
									guiaPagamento.setDebitoTipo(debitoTipoGuiaPagamento);
								}

								Date menorDataPagamento = repositorioCobranca
										.pesquisarMenorDataPagamentoGuiaPagamento(
												guiaPagamento.getId(), 
												imovel.getId(), 
												guiaPagamento.getDebitoTipo().getId());

								boolean indicadorExistePagamentoClassificadoGuiaPagamento = repositorioFaturamento
										.obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
												guiaPagamento.getId(), 
												imovel.getId(), 
												guiaPagamento.getDebitoTipo().getId(),
												anoMesReferenciaArrecadacao);
								
								// [UC0216] Calcular Acr�scimos por Impontualidade
								CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade = new CalcularAcrescimoPorImpontualidadeHelper();
								calcularAcrescimoPorImpontualidade = this.getControladorCobranca()
										.calcularAcrescimoPorImpontualidade(
												guiaPagamento.getAnoMesReferenciaContabil(),
												guiaPagamento.getDataVencimento(),
												menorDataPagamento,
												guiaPagamento.getValorDebito(),
												BigDecimal.ZERO,
												guiaPagamento.getIndicadoCobrancaMulta(),
												"" + anoMesReferenciaArrecadacao,
												null,
												ConstantesSistema.INDICADOR_ARRECADACAO_ATIVO,
												null);

								DebitoTipo debitoTipo = null;

								/*
								 * Item 5.8.3 Caso o indicador de gera��o de
								 * multa corresponda a sim(1) e o valor da multa
								 * seja maior que que zero. Gera o d�bito a
								 * cobrar referente a multa.
								 */
								if (indicadorGeracaoMulta.equals(ConstantesSistema.SIM) && calcularAcrescimoPorImpontualidade.getValorMulta().compareTo(BigDecimal.ZERO) == 1 && indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.MULTA_IMPONTUALIDADE);

									//[SB0001 - Gerar D�bito a Cobrar] 
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao, 
											anoMesReferenciaFaturamento, 
											imovel, 
											localidade, 
											quadra, 
											setorComercial, 
											numeroPrestacaoDebito, 
											numeroPrestacaoCobradas, 
											guiaPagamento, 
											calcularAcrescimoPorImpontualidade.getValorMulta(), 
											debitoTipo, Usuario.USUARIO_BATCH);
									
									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.add(guiaPagamento.getId());
									
									colecaoDebitoACobrarInserir.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir.addAll(this.inserirDebitoACobrarCategoriaBatch(debitoACobrar,debitoACobrar.getImovel()));
								}

								/*
								 * Item 5.8.4 Caso o indicador de gera��o dos
								 * juros de mora corresponda a sim(1) e o valor
								 * dos juros de mora seja maior que zero e
								 * exista pagamento para a guia de pagamento com
								 * situa��o atual igual a classificado. Gera o
								 * d�bito a cobrar referente a juros de mora.
								 */
								if (indicadorGeracaoJuros.equals(ConstantesSistema.SIM)	&& calcularAcrescimoPorImpontualidade.getValorJurosMora().compareTo(BigDecimal.ZERO) == 1 && indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.JUROS_MORA);
									
									//[SB0001 - Gerar D�bito a Cobrar] 
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao, 
											anoMesReferenciaFaturamento, 
											imovel, 
											localidade, 
											quadra, 
											setorComercial, 
											numeroPrestacaoDebito, 
											numeroPrestacaoCobradas, 
											guiaPagamento, 
											calcularAcrescimoPorImpontualidade.getValorJurosMora(), 
											debitoTipo, Usuario.USUARIO_BATCH);
								
									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.add(guiaPagamento.getId());
									
									colecaoDebitoACobrarInserir.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir.addAll(this.inserirDebitoACobrarCategoriaBatch(debitoACobrar,debitoACobrar.getImovel()));
								}

								/*
								 * Item 5.8.5 Caso o indicador de gera��o de
								 * atualiza��o monet�ria corresponda a sim(1) e
								 * o valor da atualiza��o monet�ria seja maior
								 * que zero e exista pagamento para a guia de
								 * pagamento com situa��o atual igual a
								 * classificado. Gera o d�bito a cobrar
								 * referente a atualiza��o monet�ria.
								 */
								if (indicadorGeracaoAtualizacao.equals(ConstantesSistema.SIM) && calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria().compareTo(BigDecimal.ZERO) == 1 && indicadorExistePagamentoClassificadoGuiaPagamento) {

									debitoTipo = new DebitoTipo();
									debitoTipo.setId(DebitoTipo.ATUALIZACAO_MONETARIA);
									
									//[SB0001 - Gerar D�bito a Cobrar] 
									DebitoACobrar debitoACobrar = gerarDebitoACobrarParaGuiaPagamento(
											anoMesReferenciaArrecadacao, 
											anoMesReferenciaFaturamento, 
											imovel, 
											localidade, 
											quadra, 
											setorComercial, 
											numeroPrestacaoDebito, 
											numeroPrestacaoCobradas, 
											guiaPagamento, 
											calcularAcrescimoPorImpontualidade.getValorAtualizacaoMonetaria(), 
											debitoTipo, Usuario.USUARIO_BATCH);
				
									colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.add(guiaPagamento.getId());
									
									colecaoDebitoACobrarInserir.add(debitoACobrar);
									colecaoDebitoACobrarCategoriaInserir.addAll(this.inserirDebitoACobrarCategoriaBatch(debitoACobrar,debitoACobrar.getImovel()));
								}
							} // fim if da comparacao da data de
							// pagamento
						} // fim while contasiterator
					} // fim if colecaoguia

					if (colecaoIdsGuiasPagamentosAtualizarIndicadorMulta != null && !colecaoIdsGuiasPagamentosAtualizarIndicadorMulta.isEmpty()) {
						repositorioFaturamento.atualizarIndicadorMultaDeGuiaPagamento(colecaoIdsGuiasPagamentosAtualizarIndicadorMulta);
					}
				} //fim if flagProximoImovel
			}// fim while imovelporrotaiterator

			// Inserir os d�bitos a cobrar
			if (colecaoDebitoACobrarInserir != null	&& !colecaoDebitoACobrarInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(colecaoDebitoACobrarInserir);
			}

			// Inseri os d�bitos a cobrar por categoria
			if (colecaoDebitoACobrarCategoriaInserir != null && !colecaoDebitoACobrarCategoriaInserir.isEmpty()) {
				this.getControladorBatch().inserirColecaoObjetoParaBatch(colecaoDebitoACobrarCategoriaInserir);
			}

			// --------------------------------------------------------
			//
			// Registrar o fim da execu��o da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,idUnidadeIniciada, false);

			return null;

		} catch (Exception e) {
			// Este catch serve para interceptar qualquer exce��o que o processo
			// batch venha a lan�ar e garantir que a unidade de processamento do
			// batch ser� atualizada com o erro ocorrido
			e.printStackTrace();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(e,idUnidadeIniciada, true);

			throw new EJBException(e);
		}

	}

	/**
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * @author Tiago Moreno
	 * @date 12/11/2009
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public Collection<EmitirContaHelper> emitir2ViaContas(
			Collection idsContaEP, boolean cobrarTaxaEmissaoConta,
			Short contaSemCodigoBarras) throws ControladorException {

		Collection<EmitirContaHelper> colecaoEmitirContaHelper = new ArrayList();

		Iterator iter = idsContaEP.iterator();

		while (iter.hasNext()) {
			Integer idContaEP = (Integer) iter.next();

			Collection colectionConta;
			try {
				colectionConta = this.repositorioFaturamento.pesquisarConta(idContaEP);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			EmitirContaHelper emitirContaHelper = (EmitirContaHelper) colectionConta.iterator().next();

			SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

			String nomeCliente = "";

			if (emitirContaHelper.getNomeCliente() == null || emitirContaHelper.getNomeCliente().trim().equals("")) {
				
				if (emitirContaHelper.getNomeImovel() != null && !emitirContaHelper.getNomeImovel().trim().equals("")) {
					nomeCliente = emitirContaHelper.getNomeImovel();
				}
				
				emitirContaHelper.setNomeCliente(nomeCliente);
			}

			// Linha 5
			// --------------------------------------------------------------
			// recupera endereco do im�vel
			String enderecoImovel = "";
			try {
				enderecoImovel = getControladorEndereco().pesquisarEnderecoFormatado(emitirContaHelper.getIdImovel());
			} catch (ControladorException e1) {
				e1.printStackTrace();
			}
			emitirContaHelper.setEnderecoImovel(enderecoImovel);

			// Linha 6
			// --------------------------------------------------------------
			// inst�ncia um imovel com os dados da conta para recuperar a
			// inscri��o que est� no objeto imovel
			Imovel imovel = new Imovel();
			Localidade localidade = new Localidade();
			localidade.setId(emitirContaHelper.getIdLocalidade());
			imovel.setLocalidade(localidade);
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setCodigo(emitirContaHelper.getCodigoSetorComercialConta());
			imovel.setSetorComercial(setorComercial);
			Quadra quadra = new Quadra();
			quadra.setNumeroQuadra(emitirContaHelper.getIdQuadraConta());
			imovel.setQuadra(quadra);
			imovel.setLote(emitirContaHelper.getLoteConta());
			imovel.setSubLote(emitirContaHelper.getSubLoteConta());
			// Inscri��o do im�vel
			emitirContaHelper.setInscricaoImovel(imovel.getInscricaoFormatada());

			// Linha 7
			// --------------------------------------------------------------
			String idClienteResponsavel = "";
			String enderecoClienteResponsavel = "";
			Integer idImovelContaEnvio = emitirContaHelper.getIdImovelContaEnvio();
			// caso a cole��o de contas seja de entrega para o cliente respons�vel
			if (idImovelContaEnvio != null
				&& (idImovelContaEnvio.equals(ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL) 
					|| idImovelContaEnvio.equals(ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL))) {

				Integer idClienteResponsavelInteger = null;
				idClienteResponsavelInteger = pesquisarIdClienteResponsavelConta(
						emitirContaHelper.getIdConta(), false);

				if (idClienteResponsavelInteger != null && !idClienteResponsavelInteger.equals("")) {
					idClienteResponsavel = idClienteResponsavelInteger.toString();
					// [UC0085]Obter Endereco
					enderecoClienteResponsavel = getControladorEndereco()
						.pesquisarEnderecoClienteAbreviado(idClienteResponsavelInteger);
				}

			}
			emitirContaHelper.setIdClienteResponsavel(idClienteResponsavel);
			emitirContaHelper.setEnderecoClienteResponsavel(enderecoClienteResponsavel);

			// Linha 8
			// --------------------------------------------------------------

			// [SB0002] - Determinar tipo de liga��o e tipo de Medi��o
			Integer[] parmSituacao = determinarTipoLigacaoMedicao(emitirContaHelper);
			Integer tipoLigacao = parmSituacao[0];
			Integer tipoMedicao = parmSituacao[1];

			// Linha 9
			// --------------------------------------------------------------
			// cria uma stringBuilder para recuperar o resultado do [SB0003]
			// o tamanho da string que vem como resultado � de 20 posi��es
			StringBuilder obterDadosConsumoMedicaoAnterior = null;

			// chama o [SB0003] -Obter Dados do Consumo e Medi��o Anterior
			// passando a quantidade de Meses Igual a 1
			// e o tipo de liga��o e medi��o recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(emitirContaHelper.getIdImovel(), 
				emitirContaHelper.getAmReferencia(), 1, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes1(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medi��o Anterior
			// passando a quantidade de Meses Igual a 4
			// e o tipo de liga��o e medi��o recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 4, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes4(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 10
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medi��o Anterior
			// passando a quantidade de Meses Igual a 2
			// e o tipo de liga��o e medi��o recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 2, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes2(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medi��o Anterior
			// passando a quantidade de Meses Igual a 5
			// e o tipo de liga��o e medi��o recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
				emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia(), 5, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes5(obterDadosConsumoMedicaoAnterior.toString());
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros da medi��o historico do
			// [SB0004] - Obter Dados da Medi��o da Conta
			Object[] parmsMedicaoHistorico = obterDadosMedicaoConta(emitirContaHelper, tipoMedicao);
			// Leitura Anterior
			String leituraAnterior = "";
			// Leitura Atual
			String leituraAtual = "";
			// Data Leitura Anterior
			String dataLeituraAnterior = "";
			// Leitura Anterior
			String dataLeituraAtual = "";
			// Leitura Situa��o Atual
			// String leituraSituacaoAtual = "";
			// Leitura Anormalidade Faturamento
			String leituraAnormalidadeFaturamento = "";
			if (parmsMedicaoHistorico != null) {

				if (parmsMedicaoHistorico[0] != null) {
					leituraAnterior = "" + (Integer) parmsMedicaoHistorico[0];
				}

				if (parmsMedicaoHistorico[1] != null) {
					leituraAtual = "" + (Integer) parmsMedicaoHistorico[1];
				}

				if (parmsMedicaoHistorico[3] != null) {
					dataLeituraAnterior = Util.formatarData((Date) parmsMedicaoHistorico[3]);
				}

				if (parmsMedicaoHistorico[2] != null) {
					dataLeituraAtual = Util.formatarData((Date) parmsMedicaoHistorico[2]);
				}

				if (parmsMedicaoHistorico[4] != null) {
					// leituraSituacaoAtual = ""
					// + (Integer) parmsMedicaoHistorico[4];
				}

				if (parmsMedicaoHistorico[5] != null) {
					leituraAnormalidadeFaturamento = ""	+ (Integer) parmsMedicaoHistorico[5];
				}
			}
			emitirContaHelper.setDataLeituraAnterior(dataLeituraAnterior);
			emitirContaHelper.setDataLeituraAtual(dataLeituraAtual);
			String diasConsumo = "";
			if (!dataLeituraAnterior.equals("") && !dataLeituraAtual.equals("")) {
				// calcula a quantidade de dias de consumo que � a
				// quantidade de dias
				// entre a data de leitura
				// anterior(parmsMedicaoHistorico[2]) e a data de leitura
				// atual(parmsMedicaoHistorico[3])
				diasConsumo = "" + Util.obterQuantidadeDiasEntreDuasDatas(
						(Date) parmsMedicaoHistorico[3],(Date) parmsMedicaoHistorico[2]);
			}
			// recupera os parametros de consumo faturamento e consumo m�dio di�rio
			// [SB0005] - Obter Consumo Faturado e Consumo M�dio Di�rio
			String[] parmsConsumo = obterConsumoFaturadoConsumoMedioDiario(
					emitirContaHelper, tipoMedicao, diasConsumo);
			String consumoFaturamento = parmsConsumo[0];
			emitirContaHelper.setConsumoFaturamento(consumoFaturamento);

			String consumoMedioDiario = parmsConsumo[1];
			emitirContaHelper.setConsumoMedioDiario(consumoMedioDiario);
			// Fim Chamar Sub-Fluxo
			// Leitura Anterior
			leituraAnterior = Util.completaString(leituraAnterior, 7);
			emitirContaHelper.setLeituraAnterior(leituraAnterior);
			// Leitura Atual
			leituraAtual = Util.completaString(leituraAtual, 7);
			emitirContaHelper.setLeituraAtual(leituraAtual);
			// Dias de consumo
			diasConsumo = Util.completaString(diasConsumo, 2);
			emitirContaHelper.setDiasConsumo(diasConsumo);

			// Linha 11
			// --------------------------------------------------------------
			// chama o [SB0003] -Obter Dados do Consumo e Medi��o Anterior
			// passando a quantidade de Meses Igual a 3
			// e o tipo de liga��o e medi��o recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
					emitirContaHelper.getIdImovel(), emitirContaHelper
							.getAmReferencia(), 3, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes3(obterDadosConsumoMedicaoAnterior.toString());
			// chama o [SB0003] -Obter Dados do Consumo e Medi��o Anterior
			// passando a quantidade de Meses Igual a 6
			// e o tipo de liga��o e medi��o recuperados anteriormente
			obterDadosConsumoMedicaoAnterior = obterDadosConsumoAnterior(
					emitirContaHelper.getIdImovel(), emitirContaHelper
							.getAmReferencia(), 6, tipoLigacao, tipoMedicao);
			emitirContaHelper.setDadosConsumoMes6(obterDadosConsumoMedicaoAnterior.toString());

			// Linha 12
			// --------------------------------------------------------------
			// Inicio Chamar Sub-Fluxo
			// recupera os parametros do consumo historico da conta
			// [SB0006] - Obter Dados de Consumo da Conta
			Object[] parmsConsumoHistorico = null;
			String descricaoAbreviadaTipoConsumo = "";
			String descricaoTipoConsumo = "";
			String consumoMedio = "";
			String descricaoAbreviadaAnormalidadeConsumo = "";
			String descricaoAnormalidadeConsumo = "";
			String consumoRateio = "";
			// caso o tipo de ligacao for diferente de nulo
			if (tipoLigacao != null) {
				try {
					parmsConsumoHistorico = getControladorMicromedicao().obterDadosConsumoConta(
						emitirContaHelper.getIdImovel(),emitirContaHelper.getAmReferencia(),tipoLigacao);
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				if (parmsConsumoHistorico != null) {
					// descri��o abreviada tipo de consumo
					if (parmsConsumoHistorico[0] != null) {
						descricaoAbreviadaTipoConsumo = (String) parmsConsumoHistorico[0];
					}
					// descri��o tipo de consumo
					if (parmsConsumoHistorico[1] != null) {
						descricaoTipoConsumo = (String) parmsConsumoHistorico[1];
					}
					// Consumo m�dio
					if (parmsConsumoHistorico[2] != null) {
						consumoMedio = "" + (Integer) parmsConsumoHistorico[2];
					}
					// descri��o abreviada anormalidade de consumo
					if (parmsConsumoHistorico[3] != null) {
						descricaoAbreviadaAnormalidadeConsumo = (String) parmsConsumoHistorico[3];
					}
					// descri��o anormalidade de consumo
					if (parmsConsumoHistorico[4] != null) {
						descricaoAnormalidadeConsumo = (String) parmsConsumoHistorico[4];
					}
					// Consumo m�dio
					if (parmsConsumoHistorico[5] != null) {
						consumoRateio = "" + (Integer) parmsConsumoHistorico[5];
					}
				}
			}

			emitirContaHelper.setDescricaoTipoConsumo(descricaoTipoConsumo);
			emitirContaHelper.setDescricaoAnormalidadeConsumo(descricaoAnormalidadeConsumo);

			// Fim Chamar Sub-Fluxo

			// Linha 13
			// --------------------------------------------------------------

			// Inicio Chamar Sub-Fluxo
			// soma a quantidades de economias da tabela contaCategoria
			// [SB0007] - Obter Dados da Medi��o da Conta
			Short quantidadeEconomiaConta = 0;
			quantidadeEconomiaConta = obterQuantidadeEconomiasConta(
			emitirContaHelper.getIdConta(), false);
			emitirContaHelper.setQuantidadeEconomiaConta(""	+ quantidadeEconomiaConta);
			// Fim Chamar Sub-Fluxo

			// Consumo por Economia
			// transforma o consumoFaturamento para um bigDecimal
			BigDecimal consumoFaturadoBigDecimal = null;
			if (consumoFaturamento != null && !consumoFaturamento.equals("")) {
				consumoFaturadoBigDecimal = Util.formatarMoedaRealparaBigDecimal(consumoFaturamento);

			}
			// transforma a quantidade de economias da conta para um
			// bigDecimal
			BigDecimal qtdEconomiasBigDecimal = null;
			if (quantidadeEconomiaConta != null && !quantidadeEconomiaConta.equals("")) {
				qtdEconomiasBigDecimal = Util.formatarMoedaRealparaBigDecimal("" + quantidadeEconomiaConta);
			}
			String consumoEconomia = "";
			if (consumoFaturadoBigDecimal != null && qtdEconomiasBigDecimal != null) {
				BigDecimal consumoEconomiaBigDecimal = consumoFaturadoBigDecimal.divide(qtdEconomiasBigDecimal, 2, RoundingMode.UP);
				consumoEconomia = Util.formatarMoedaReal(consumoEconomiaBigDecimal);
				emitirContaHelper.setConsumoEconomia(consumoEconomia.substring(0, consumoEconomia.length() - 3));
			}

			// Inicio Chamar Sub-Fluxo
			// concatena os campos dos sub-fluxos anteriores
			// [SB0008] - Obter Dados da Medi��o da Conta
			StringBuilder codigoAuxiliar = new StringBuilder();
			// leitura situa��o atual
			// tipo de consumo
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaTipoConsumo, 1));
			// tipo de contrato
			codigoAuxiliar.append(Util.completaString("", 1));
			// anormalidade de leitura
			codigoAuxiliar.append(Util.completaString(leituraAnormalidadeFaturamento, 2));
			// anormalidade de consumo
			codigoAuxiliar.append(Util.completaString(descricaoAbreviadaAnormalidadeConsumo, 2));

			// perfil do im�vel
			if (emitirContaHelper.getIdImovelPerfil() != null) {
				codigoAuxiliar.append(Util.completaString("" + emitirContaHelper.getIdImovelPerfil(), 1));
			} else {
				codigoAuxiliar.append(Util.completaString("", 1));
			}
			// dias do consumo
			codigoAuxiliar.append(Util.completaString(diasConsumo, 2));
			// Consumo medio do im�vel
			codigoAuxiliar.append(Util.completaString(consumoMedio, 6));
			// Fim Chamar Sub-Fluxo
			emitirContaHelper.setCodigoAuxiliarString(codigoAuxiliar.toString());

			// chama o [SB0009] - Obter Mensagem de Rateio de Consumo Fixo
			// de Esgoto
			StringBuilder mesagemConsumo = obterMensagemRateioConsumo(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao);
			// mensagem de rateio de consumo ou consumo fixo de esgoto
			emitirContaHelper.setMensagemConsumoString(mesagemConsumo.toString());

			// Linha 16
			// --------------------------------------------------------------
			// chama o [SB0010] - Gerar Linhas da Descri��o dos Servi�os e Tarifas

			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper = 
				gerarLinhasDescricaoServicoTarifasRelatorio(
				emitirContaHelper, consumoRateio, parmsMedicaoHistorico,tipoMedicao, false);
			emitirContaHelper.setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(colecaoContaLinhasDescricaoServicosTarifasTotalHelper);

			// Linha 17
			// --------------------------------------------------------------
			// cria um objeto conta para calcular o valor da conta
			Conta conta = new Conta();
			conta.setValorAgua(emitirContaHelper.getValorAgua());
			conta.setValorEsgoto(emitirContaHelper.getValorEsgoto());
			conta.setValorCreditos(emitirContaHelper.getValorCreditos());
			conta.setDebitos(emitirContaHelper.getDebitos());
			conta.setValorImposto(emitirContaHelper.getValorImpostos());
			BigDecimal valorConta = conta.getValorTotalContaBigDecimal();

			emitirContaHelper.setValorContaString(Util.formatarMoedaReal(valorConta));
			emitirContaHelper.setValorConta(valorConta);

			if (contaSemCodigoBarras.equals(ConstantesSistema.SIM)
					|| valorConta.compareTo(new BigDecimal("0.00")) == 0) {
				emitirContaHelper.setContaSemCodigoBarras("1");
			} else {
				emitirContaHelper.setContaSemCodigoBarras("2");
			}

			// chama o [SB0016] - Obter Mensagem da Conta em 3 Partes
			String[] parmsPartesConta = obterMensagemConta3Partes(
					emitirContaHelper, sistemaParametro);

			// Linha 18
			// --------------------------------------------------------------
			emitirContaHelper.setPrimeiraParte(parmsPartesConta[0]);

			// Linha 19
			// --------------------------------------------------------------
			emitirContaHelper.setSegundaParte(parmsPartesConta[1]);

			// Linha 20
			// --------------------------------------------------------------
			emitirContaHelper.setTerceiraParte(parmsPartesConta[2]);

			// Linha 21
			// --------------------------------------------------------------
			int anoMesReferenciaSubtraido = Util.subtrairMesDoAnoMes(emitirContaHelper.getAmReferencia(), 1);
			emitirContaHelper.setMesAnoFormatado(Util.formatarAnoMesParaMesAno(anoMesReferenciaSubtraido));

			// Linha 22
			// --------------------------------------------------------------
			Object[] parmsQualidadeAgua = null;
			parmsQualidadeAgua = pesquisarParmsQualidadeAgua(emitirContaHelper);

			// numero indice turbidez da qualidade agua
			String numeroIndiceTurbidez = "";
			// numero cloro residual da qualidade agua
			String numeroCloroResidual = "";
			if (parmsQualidadeAgua != null) {
				if (parmsQualidadeAgua[0] != null) {
					numeroIndiceTurbidez = Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[0]);
				}

				if (parmsQualidadeAgua[1] != null) {
					numeroCloroResidual = Util.formatarMoedaReal((BigDecimal) parmsQualidadeAgua[1]);
				}
			}
			emitirContaHelper.setNumeroIndiceTurbidez(numeroIndiceTurbidez);
			emitirContaHelper.setNumeroCloroResidual(numeroCloroResidual);

			// Linha 23
			// --------------------------------------------------------------
			// Considerar as contas do tipo d�bito autom�tico como tipo de conta normal
			// [SB0018 - Gerar Linhas das DemaisContas]
			Integer digitoVerificadorConta = new Integer(""	+ emitirContaHelper.getDigitoVerificadorConta());
			// formata ano mes para mes ano
			String anoMes = "" + emitirContaHelper.getAmReferencia();
			String mesAno = anoMes.substring(4, 6) + anoMes.substring(0, 4);

			String representacaoNumericaCodBarra = "";
			
			// Linha28
			Date dataValidade = obterDataValidade2ViaConta(emitirContaHelper);
			emitirContaHelper.setDataValidade(Util.formatarData(dataValidade));
			
			if (contaSemCodigoBarras.equals(ConstantesSistema.NAO)
					|| valorConta.compareTo(new BigDecimal("0.00")) != 0) {

				representacaoNumericaCodBarra = this.getControladorArrecadacao()
						.obterRepresentacaoNumericaCodigoBarra(3, valorConta,
								emitirContaHelper.getIdLocalidade(),
								emitirContaHelper.getIdImovel(), mesAno,
								digitoVerificadorConta, null, null, null, null,
								null, null, null);

				// Linha 24
				// Formata a representa��o n�merica do c�digo de barras
				String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra.substring(0, 11)
						+ "-"
						+ representacaoNumericaCodBarra.substring(11, 12)
						+ " "
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ "-"
						+ representacaoNumericaCodBarra.substring(23, 24)
						+ " "
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ "-"
						+ representacaoNumericaCodBarra.substring(35, 36)
						+ " "
						+ representacaoNumericaCodBarra.substring(36, 47)
						+ "-" + representacaoNumericaCodBarra.substring(47, 48);
				emitirContaHelper.setRepresentacaoNumericaCodBarraFormatada(representacaoNumericaCodBarraFormatada);

				// Linha 25
				String representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarra
						.substring(0, 11)
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ representacaoNumericaCodBarra.substring(36, 47);
				emitirContaHelper.setRepresentacaoNumericaCodBarraSemDigito(representacaoNumericaCodBarraSemDigito);
				
			}
			
			
			colecaoEmitirContaHelper.add(emitirContaHelper);

			if (cobrarTaxaEmissaoConta) {
				this.gerarDebitoACobrarTaxaEmissaoConta(
					emitirContaHelper.getIdImovel(), emitirContaHelper.getAmReferencia());
			}

		}

		return colecaoEmitirContaHelper;
	}
	
	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Tiago Moreno
	 * @date 25/11/2009
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemDebitoConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {

		String[] linhasImpostosRetidos = new String[3];
		Integer anoMesReferenciaFinal = sistemaParametro.getAnoMesFaturamento();
		int anoMesSubtraido = Util
				.subtrairMesDoAnoMes(anoMesReferenciaFinal, 1);
		Integer dataVencimentoFinalInteger = sistemaParametro
				.getAnoMesArrecadacao();
		String anoMesSubtraidoString = ""
				+ Util.subtrairMesDoAnoMes(dataVencimentoFinalInteger, 1);
		int ano = Integer.parseInt(anoMesSubtraidoString.substring(0, 4));
		int mes = Integer.parseInt(anoMesSubtraidoString.substring(4, 6));

		// recupera o ultimo dia do anomes e passa a data como parametro
		Calendar dataVencimentoFinal = GregorianCalendar.getInstance();
		dataVencimentoFinal.set(Calendar.YEAR, ano);
		dataVencimentoFinal.set(Calendar.MONTH, mes - 1);
		dataVencimentoFinal.set(Calendar.DAY_OF_MONTH, dataVencimentoFinal
				.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date dataFinalDate = dataVencimentoFinal.getTime();

		// converte String em data
		Date dataVencimento = Util.converteStringParaDate("01/01/1900");

		ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper = getControladorCobranca()
				.obterDebitoImovelOuCliente(1,
						"" + emitirContaHelper.getIdImovel(), null, null,
						"190001", "" + anoMesSubtraido, dataVencimento,
						dataFinalDate, 1, 2, 2, 2, 2, 1, 2, null,2);
		// se o imovel possua d�bito(debitoImovelCobran�a for diferente de nulo)
		if (debitoImovelClienteHelper != null
				&& ((debitoImovelClienteHelper
						.getColecaoGuiasPagamentoValores() != null && !debitoImovelClienteHelper
						.getColecaoGuiasPagamentoValores().isEmpty()) || (debitoImovelClienteHelper
						.getColecaoContasValores() != null && !debitoImovelClienteHelper
						.getColecaoContasValores().isEmpty()))) {
			String dataVencimentoFinalString = Util.formatarData(dataFinalDate);
			linhasImpostosRetidos[0] = "SR. USU�RIO: EM  "
					+ dataVencimentoFinalString
					+ ",    REGISTRAMOS QUE V.SA. ESTAVA EM D�BITO COM A "
					+ sistemaParametro.getNomeAbreviadoEmpresa().toUpperCase() + ".";
			linhasImpostosRetidos[1] = "COMPARE�A A UM DOS NOSSOS POSTOS DE ATENDIMENTO PARA REGULARIZAR SUA SITUACAO.EVITE O CORTE.";
			linhasImpostosRetidos[2] = "CASO O SEU D�BITO TENHA SIDO PAGO AP�S A DATA INDICADA,DESCONSIDERE ESTE AVISO.";

		} else{
			linhasImpostosRetidos[0] = "A COSANPA AGRADECE SUA PONTUALIDADE.";
			linhasImpostosRetidos[1] = "MUITO OBRIGADO.";
			linhasImpostosRetidos[2] = "";
		}
		
		return linhasImpostosRetidos;
	}
	
	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author Tiago Moreno
	 * @date 25/11/2009
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {

		String[] linhasImpostosRetidos = new String[3];
		
		linhasImpostosRetidos = obterMensagemAnormalidadeConsumo(emitirContaHelper);
		
		if(linhasImpostosRetidos == null || linhasImpostosRetidos.equals("")){
			linhasImpostosRetidos = new String[3];
			Object[] mensagensConta = null;
			// recupera o id do grupo de faturamento da conta
			Integer idFaturamentoGrupo = emitirContaHelper
					.getIdFaturamentoGrupo();
			// recupera o id da gerencia regional da conta
			Integer idGerenciaRegional = emitirContaHelper
					.getIdGerenciaRegional();
			// recupera o id da localidade da conta
			Integer idLocalidade = emitirContaHelper.getIdLocalidade();
			// recupera o id do setor comercial da conta
			Integer idSetorComercial = emitirContaHelper.getIdSetorComercial();
			// caso entre em alguma condi��o ent�o n�o entra mais nas outras
			boolean achou = false;
			try {
				// o sistema obtem a mensagem para a conta
				// Caso seja a condi��o 1
				// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
				// Localidade=parmConta, SetorComercial=parmConta)
				mensagensConta = repositorioFaturamento
						.pesquisarParmsContaMensagem(emitirContaHelper, null,
								idGerenciaRegional, idLocalidade,
								idSetorComercial);
				if (mensagensConta != null) {
					// Conta Mensagem 1
					if (mensagensConta[0] != null) {
						linhasImpostosRetidos[0] = (String) mensagensConta[0];
					} else {
						linhasImpostosRetidos[0] = "";
					}
					// Conta Mensagem 2
					if (mensagensConta[1] != null) {
						linhasImpostosRetidos[1] = (String) mensagensConta[1];
					} else {
						linhasImpostosRetidos[1] = "";
					}
					// Conta Mensagem 3
					if (mensagensConta[2] != null) {
						linhasImpostosRetidos[2] = (String) mensagensConta[2];
					} else {
						linhasImpostosRetidos[2] = "";
					}
					achou = true;
				}
	
				if (!achou) {
	
					// Caso seja a condi��o 2
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, idGerenciaRegional, idLocalidade,
									null);
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							linhasImpostosRetidos[0] = (String) mensagensConta[0];
						} else {
							linhasImpostosRetidos[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							linhasImpostosRetidos[1] = (String) mensagensConta[1];
						} else {
							linhasImpostosRetidos[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							linhasImpostosRetidos[2] = (String) mensagensConta[2];
						} else {
							linhasImpostosRetidos[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condi��o 3
					// (FaturamentoGrupo =null, GerenciaRegional=parmConta,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, idGerenciaRegional, null, null);
	
					if (mensagensConta != null) {
	
						if (mensagensConta[0] != null) {
							linhasImpostosRetidos[0] = (String) mensagensConta[0];
						} else {
							linhasImpostosRetidos[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							linhasImpostosRetidos[1] = (String) mensagensConta[1];
						} else {
							linhasImpostosRetidos[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							linhasImpostosRetidos[2] = (String) mensagensConta[2];
						} else {
							linhasImpostosRetidos[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condi��o 4
					// (FaturamentoGrupo =parmConta, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									idFaturamentoGrupo, null, null, null);
	
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							linhasImpostosRetidos[0] = (String) mensagensConta[0];
						} else {
							linhasImpostosRetidos[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							linhasImpostosRetidos[1] = (String) mensagensConta[1];
						} else {
							linhasImpostosRetidos[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							linhasImpostosRetidos[2] = (String) mensagensConta[2];
						} else {
							linhasImpostosRetidos[2] = "";
						}
						achou = true;
					}
				}
				if (!achou) {
					// Caso seja a condi��o 5
					// (FaturamentoGrupo =null, GerenciaRegional=null,
					// Localidade=null, SetorComercial=null)
					// Conta Mensagem 1
					mensagensConta = repositorioFaturamento
							.pesquisarParmsContaMensagem(emitirContaHelper,
									null, null, null, null);
					if (mensagensConta != null) {
						if (mensagensConta[0] != null) {
							linhasImpostosRetidos[0] = (String) mensagensConta[0];
						} else {
							linhasImpostosRetidos[0] = "";
						}
						// Conta Mensagem 2
						if (mensagensConta[1] != null) {
							linhasImpostosRetidos[1] = (String) mensagensConta[1];
						} else {
							linhasImpostosRetidos[1] = "";
						}
						// Conta Mensagem 3
						if (mensagensConta[2] != null) {
							linhasImpostosRetidos[2] = (String) mensagensConta[2];
						} else {
							linhasImpostosRetidos[2] = "";
						}
						achou = true;
					}
				}
				// caso n�o tenha entrado em nenhuma das op��es acima
				// ent�o completa a string com espa��s em branco
				if (!achou) {
					linhasImpostosRetidos[0] = "";
					linhasImpostosRetidos[1] = "";
					linhasImpostosRetidos[2] = "";
				}
			} catch (ErroRepositorioException e) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", e);
			}
		}
		return linhasImpostosRetidos;
	}
	
	
}
