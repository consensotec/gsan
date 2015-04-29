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
package gsan.faturamento;

import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaForma;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.faturamento.bean.EmitirContaHelper;
import gsan.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoACobrarGeral;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.faturamento.debito.DebitoTipo;
import gsan.faturamento.debito.DebitoTipoVigencia;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.micromedicao.Rota;
import gsan.micromedicao.consumo.ConsumoAnormalidade;
import gsan.micromedicao.consumo.ConsumoHistorico;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.SessionBean;

/**
 * Controlador Faturamento COMPESA
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class ControladorFaturamentoCOMPESASEJB extends ControladorFaturamento implements SessionBean {
	private static final long serialVersionUID = 1L;
	//==============================================================================================================
	// M�TODOS EXCLUSIVOS DA COMPESA
	//==============================================================================================================
	
	/**
	 * [UC0120] - Calcular Valores de �gua e/ou Esgoto
	 *
	 * Selecionar as tarifas vigentes para o im�vel
	 *
	 * @author Raphael Rossiter
	 * @date 28/05/2008
	 *
	 * @param tarifaImovel
	 * @param dataLeituraAnterior
	 * @param dataLeituraAtual
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection obterConsumoTarifaVigenciaCalcularAguaEsgoto(Integer tarifaImovel, Date dataLeituraAnterior,
			Date dataLeituraAtual, Integer anoMesReferencia) throws ControladorException{
		
		Collection colecaoConsumoTarifaVigenciaTodasDatas = new ArrayList();
		
		if (tarifaImovel != null && dataLeituraAnterior != null && dataLeituraAtual != null) {
			
			/*
			 * Seleciona todas as ocorr�ncias da tabela CONSUMO_TARIFA_VIGENCIA com
			 * CSTF_ID=Id da tarifa para o im�vel e com CSTV_DTVIGENCIA entre as
			 * datas de leitura anterior e atual
			 */
			Collection colecaoConsumoTarifaVigenciaEntreDatas = selecionaTarifasVigentesPeriodoLeituraImovel(
			tarifaImovel, dataLeituraAnterior, dataLeituraAtual, true);
			
			/*
			 * Caso n�o seja selecionada nenhuma ocorr�ncia ou caso nenhuma
			 * ocorr�ncia selecionada tenha CSTV_DTVIGENCIA = data de leitura
			 * anterior, selecionar tamb�m a ocorr�ncia da tabela
			 * CONSUMO_TARIFA_VIGENCIA com CSTF_ID= Id da tarifa para o im�vel e
			 * com a maior CSTV_DTVIGENCIA que seja menor que a data de leitura
			 * anterior.
			 */
			if (colecaoConsumoTarifaVigenciaEntreDatas == null || 
				colecaoConsumoTarifaVigenciaEntreDatas.isEmpty()) {

				Collection colecaoConsumoTarifaVigenciaMaiorData = selecionaTarifasVigentesPeriodoLeituraImovel(
				tarifaImovel, dataLeituraAnterior, dataLeituraAtual, false);

				colecaoConsumoTarifaVigenciaTodasDatas
				.addAll(colecaoConsumoTarifaVigenciaMaiorData);

			} 
			else {

				ConsumoTarifaVigencia consumoTarifaVigencia = null;
				
				Iterator itConsumoTarifaVigencia = colecaoConsumoTarifaVigenciaEntreDatas.iterator();

				boolean flagBuscarOcorrencia = true;

				while (itConsumoTarifaVigencia.hasNext()) {
					
					consumoTarifaVigencia = (ConsumoTarifaVigencia) itConsumoTarifaVigencia.next();

					/*
					 * Verifica se j� existe alguma ocorr�ncia onde a
					 * dataVigencia � igual a dataLeituraAnterior
					 */
					if (consumoTarifaVigencia.getDataVigencia() != null
						&& Util.datasIguais(consumoTarifaVigencia.getDataVigencia(), dataLeituraAnterior)) {

						flagBuscarOcorrencia = false;
						break;
					}
				}

				colecaoConsumoTarifaVigenciaTodasDatas.addAll(colecaoConsumoTarifaVigenciaEntreDatas);

				if (flagBuscarOcorrencia) {

					Collection colecaoConsumoTarifaVigenciaMaiorData = selecionaTarifasVigentesPeriodoLeituraImovel(
					tarifaImovel, dataLeituraAnterior, dataLeituraAtual, false);

					/*
					 * As tarifas vigentes para o per�odo de leitura ser�o as
					 * ocorr�ncias selecionadas nas cole��es parcial e final
					 */
					colecaoConsumoTarifaVigenciaTodasDatas.addAll(colecaoConsumoTarifaVigenciaMaiorData);

					// Organizar a cole��o
					Collections.sort((List) colecaoConsumoTarifaVigenciaTodasDatas,
						new Comparator() {
							public int compare(Object a, Object b) {
								Date dataVigencia1 = ((ConsumoTarifaVigencia) a)
										.getDataVigencia();
								Date dataVigencia2 = ((ConsumoTarifaVigencia) b)
										.getDataVigencia();

								return dataVigencia1.compareTo(dataVigencia2);

							}
						}
					);
				}
			}
		}
		
		return colecaoConsumoTarifaVigenciaTodasDatas;
	}
	
	
	
	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB00016] Obter Mensagem da Conta em 3 Partes
	 * 
	 * @author S�vio Luiz
	 * @date 24/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public String[] obterMensagemConta3Partes(
			EmitirContaHelper emitirContaHelper,
			SistemaParametro sistemaParametro) throws ControladorException {
		
		String[] linhasImpostosRetidos = new String[3];
		
		boolean achou = false;
		
		if (!achou) {
			// Caso tenha mensagem de quita��o de debitos
			Integer anoMensagem = null;
			
			if(emitirContaHelper.getAnoMesFaturamentoGrupo()!=null){
			
				try {
					anoMensagem = 
							repositorioFaturamento
								.pesquisarMesagemExtrato(emitirContaHelper.getAnoMesFaturamentoGrupo(),
										emitirContaHelper.getIdImovel());
				} catch (ErroRepositorioException e) {
					e.printStackTrace();
					throw new ControladorException("erro.sistema", e);
				}
			}
			if (anoMensagem != null) {
				
				linhasImpostosRetidos[0] = "IMOVEL NAO POSSUI DEBITOS DE " + anoMensagem+",EXCETO";
				linhasImpostosRetidos[1] = "SE EXISTIR FATURA EM COBRANCA JUDICIAL";
				linhasImpostosRetidos[2] = "";
				achou = true;
			}
		}
		
		if(!achou){
		
			// mensagem da conta para a anormalidade de consumo (Baixo Consumo,Auto Consumo e Estouro de Consumo)
			linhasImpostosRetidos = obterMensagemAnormalidadeConsumo(emitirContaHelper);
		
			if(linhasImpostosRetidos == null || linhasImpostosRetidos.equals("")){
				linhasImpostosRetidos = new String[3];
			
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
				dataVencimentoFinal.set(Calendar.MONTH, (mes - 1));
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
					linhasImpostosRetidos[0] = "EM "
							+ dataVencimentoFinalString
							+ ", REGISTRAMOS QUE V.SA.";
					//linhasImpostosRetidos[1] = "COMPARE�A A UM DOS NOSSOS POSTOS DE ATENDIMENTO PARA REGULARIZAR SUA SITUACAO.EVITE O CORTE.";
					linhasImpostosRetidos[1] = "ESTAVA EM D�BITO COM A COMPESA CASO";
					linhasImpostosRetidos[2] = "J� O TENHA PAGO, DESCONSIDERE ESTE AVISO.";
		//			linhasImpostosRetidos[2] = "REAJUSTE TARIF�RIO 7,31% PRO RATA A PARTIR DE 29/09/2008.";
		
				} else {
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
			}
		}
		
		//7.Caso imov_amrefexclusaotarifasocial = cnta_amreferenciaconta  

		Integer anoMesExclusaoTarifaSocialImovel  = getControladorImovel().pesquisarAnoMesExclusaoTarifaSocialImovel(emitirContaHelper.getIdImovel());
		
		if(anoMesExclusaoTarifaSocialImovel != null &&
			anoMesExclusaoTarifaSocialImovel.equals(emitirContaHelper.getAmReferencia())){
			linhasImpostosRetidos[0] = "PREZADO CLIENTE, SEU BENEF�CIO DA TARIFA SOCIAL FOI CANCELADO ";
			linhasImpostosRetidos[1] = "DEVIDO A SUA M�DIA DE CONSUMO NOS �LTIMOS 06(SEIS) MESES ";
			linhasImpostosRetidos[2] = "TER EXCEDIDO OS 10m�";
		}
		
		
		return linhasImpostosRetidos;
	}
	
	
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 *
	 * [SB0006] - Gerar Dados da Conta
	 *
	 * @author Raphael Rossiter
	 * @date 31/03/2008
	 *
	 * @param consumoHistoricoAgua
	 * @return ContaMotivoRevisao
	 * @throws ControladorException
	 */
	public ContaMotivoRevisao determinarContaMotivoRevisao(ConsumoHistorico consumoHistoricoAgua) 
		throws ControladorException{
		
		ContaMotivoRevisao contaMotivoRevisao = null;
		
		if (consumoHistoricoAgua != null) {

			if (consumoHistoricoAgua.getConsumoAnormalidade() != null && 
				(consumoHistoricoAgua.getConsumoAnormalidade().getId().equals(ConsumoAnormalidade.ESTOURO_CONSUMO) || 
				consumoHistoricoAgua.getConsumoAnormalidade().getId().equals(ConsumoAnormalidade.BAIXO_CONSUMO))) {
				
				if (consumoHistoricoAgua.getConsumoAnormalidade().getId()
					.equals(ConsumoAnormalidade.ESTOURO_CONSUMO)) {
					
					contaMotivoRevisao = new ContaMotivoRevisao();
					
					contaMotivoRevisao.setId(ContaMotivoRevisao.REVISAO_AUTOMATICA_ESTOURO_CONSUMO);
				}
				
				if (consumoHistoricoAgua.getConsumoAnormalidade().getId()
					.equals(ConsumoAnormalidade.BAIXO_CONSUMO)) {
					
					contaMotivoRevisao = new ContaMotivoRevisao();
					
					contaMotivoRevisao.setId(ContaMotivoRevisao.REVISAO_AUTOMATICA_BAIXO_CONSUMO);
				}	
			}
		}
		
		return contaMotivoRevisao;
	}
	
	
	/**
	 * [UC0113] Faturar Grupo de Faturamento
	 *
	 * � necess�rio colocar a query abaixo no processo de faturar grupo como uma funcionalidade 
	 * (Antes de rodar o faturar), para atender uma necessidade de uma localidade (Petrolina), onde existe uma 
	 * cobran�a diferenciada de esgoto. CRC771 - Socorro Oliveira 
	 *
	 *	UPDATE atendimentopublico.ligacao_esgoto
	 *	set lesg_pcalternativo = 50.00,
	 *	lesg_nnconsumopcalternativo = 20 
	 *	WHERE lesg_id in (
	 *	select im.imov_id from cadastro.imovel im 
	 *	INNER JOIN cadastro.quadra qdra on (qdra.qdra_id = im.qdra_id)
	 *	INNER JOIN micromedicao.rota rota on (rota.rota_id = qdra.rota_id)
	 *	INNER JOIN cadastro.imovel_subcategoria ims on (im.imov_id = ims.imov_id)
	 *	WHERE rota.rota_id = ?
	 *	AND   im.loca_id = 111
	 *	AND   im.last_id in (3,4,5)
	 *	AND   im.lest_id = 3
	 *	AND   ims.scat_id < 20)
	 *	AND   lesg_nnconsumopcalternativo is null;
	 *
	 * @author Raphael Rossiter
	 * @date 22/12/2008
	 *
	 * @param rota
	 * @param atividade
	 * @throws ControladorException
	 */
	protected void atualizarLigacaoEsgotoPorRota(Rota rota, int atividade) throws ControladorException {
    	
		/*
		 * As atualiza��es s� ser�o realmente efetivadas quando o processo que esteja rodando N�O
		 * seja para simula��o.
		 */
		if (atividade == FaturamentoAtividade.FATURAR_GRUPO.intValue()) {
			
			BigDecimal PC_ALTERNATIVO = new BigDecimal("50.00");
			Integer CONSUMO_PC_ALTERNATIVO = 20;
			
			try {
				
				repositorioFaturamento.atualizarLigacaoEsgotoPorRota(PC_ALTERNATIVO, 
				CONSUMO_PC_ALTERNATIVO, rota);
				
			} catch (ErroRepositorioException e) {
				throw new ControladorException("erro.sistema", e);
			}
		}
    }
	
	/**
	 * [UC0482] Emitir Segunda Via de Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 21/02/2007
	 * 
	 * @param
	 * @throws ControladorException
	 */
	public void gerarDebitoACobrarTaxaEmissaoConta(Integer idImovel,
			int anoMesReferencia) throws ControladorException {

		try {

			Imovel imovel = getControladorImovel().pesquisarImovel(idImovel);

			// Recupera os parametros do sistema
			SistemaParametro sistema = getControladorUtil()
					.pesquisarParametrosDoSistema();

			// Inst�ncia a forma de cobran�a para cobran�a em conta
			CobrancaForma cobrancaForma = new CobrancaForma();
			cobrancaForma.setId(CobrancaForma.COBRANCA_EM_CONTA);

			// Inst�ncia a situa��o do d�bito para normal
			DebitoCreditoSituacao debitoCreditoSituacao = new DebitoCreditoSituacao();
			debitoCreditoSituacao.setId(DebitoCreditoSituacao.NORMAL);
			
			/* 
			 * 4.1.	Atrav�s da constante 22 (TAXA EMISSAO 2� VIA)
			 *	identificada na coluna DBTP_NNCODIGOCONSTANTE na tabela 
			 *	DEBITO_TIPO obter o DBTP_ID. 
			 *
			 *  Desenvolvedor Hugo Amorim
			 *  Analista Nelson Carvalho
			 *  Data: 17/05/210
			 */
			DebitoTipo debitoTipo = null;
			
			FiltroDebitoTipo filtroDebitoTipo =
				new FiltroDebitoTipo();
			
			filtroDebitoTipo.adicionarParametro(
					new ParametroSimples(
							FiltroDebitoTipo.CODIGO_CONSTANTE, 
							DebitoTipo.CONSTANTE_TAXA_2_VIA_CONTA));
			
			debitoTipo = (DebitoTipo) 
				Util.retonarObjetoDeColecao(
					this.getControladorUtil().pesquisar(
							filtroDebitoTipo,
							DebitoTipo.class.getName()));		

			// Recupera a data atual
			Date dataAtual = new Date(System.currentTimeMillis());

			// Verifica se j� existe d�bito para este im�vel
			/*Object[] dadoDebitoACobrar = this.repositorioFaturamento
					.pesquisarDebitoACobrar(imovel.getId(), debitoTipo.getId(),
							sistema.getAnoMesFaturamento());*/
			
			/* 
			 *  4.1.1.	Atrav�s do DBTP_ID relacionar com o DBTP_ID da tabela DEBITO_TIPO_VIGENCIA.
			 *   Selecionar a �ltima vig�ncia (maior data DBTV_DTVIGENCIAFINAL) para o tipo de d�bito 
			 *   o valor DBTV_VLDEBITO correspondente. 
			 *	4.1.2.	Caso n�o seja encontrado para uma vig�ncia e tipo de d�bito na tabela 
			 *	DEBITO_TIPO_VIGENCIA, dever� selecionar o valor sugerido (DBTP_VLSUGERIDO<>0) 
			 *	correspondente a constante 22 na tabela DEBITO_TIPO e utilizar este valor. 
			 *
			 *  Desenvolvedor Hugo Amorim
			 *  Analista Nelson Carvalho
			 *  Data: 17/05/210
			 */

			// Cria a vari�vel que vai armazenar o valor do d�bito
			BigDecimal valor = new BigDecimal("0");
			
//			/*
//			 * Caso o perfil do im�vel seja tarifa social o valor vai ser o
//			 * valor da tarifa social Caso contr�rio o valor da tarifa vai ser o
//			 * normal.
//			 */
//			if (!imovel.getImovelPerfil().getId().equals(
//					ImovelPerfil.TARIFA_SOCIAL)) {
//				// caso o im�vel n�o seja enquadrado em tarifa social
//
//				// Obt�m o valor da Tarifa Normal
//				BigDecimal valorMinimaTarifaNormal = this.repositorioFaturamento
//						.obterValorTarifa(ConsumoTarifa.CONSUMO_NORMAL);
//
//				// Caso o valor da tarifa normal esteja nulo seta o valor para
//				// zero
//				if (valorMinimaTarifaNormal == null) {
//					valorMinimaTarifaNormal = new BigDecimal("0");
//				}
//
//				valor = valorMinimaTarifaNormal;
//
//			} else if (imovel.getImovelPerfil().getId().equals(
//					ImovelPerfil.TARIFA_SOCIAL)) {
//				// caso o im�vel seja enquadrado em tarifa social
//
//				// Obt�m o valor da Tarifa Social
//				BigDecimal valorMinimaTarifaSocial = this.repositorioFaturamento
//						.obterValorTarifa(ConsumoTarifa.CONSUMO_SOCIAL);
//
//				// Caso o valor da tarifa social esteja nulo seta o valor para
//				// zero
//				if (valorMinimaTarifaSocial == null) {
//					valorMinimaTarifaSocial = new BigDecimal("0");
//				}
//
//				valor = valorMinimaTarifaSocial;
//			}
			
			DebitoTipoVigencia debitoTipoVigencia
				= this.repositorioFaturamento
					.pesquisarDebitoTipoVigenciaPorDebitoTipo(debitoTipo.getId());
			
			if(debitoTipoVigencia!=null && debitoTipoVigencia.getValorDebito()!=null){
				valor = debitoTipoVigencia.getValorDebito();
			}else if(debitoTipo.getValorSugerido()!=null 
					&& debitoTipo.getValorSugerido().compareTo(BigDecimal.ZERO)!=0){
				valor = debitoTipo.getValorSugerido();
			}
			

			// inclui D�bito A Cobrar Geral
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral
					.setIndicadorHistorico(DebitoACobrarGeral.INDICADOR_NAO_POSSUI_HISTORICO);
			debitoACobrarGeral.setUltimaAlteracao(new Date());
			Integer idDebitoACobrarGeral = (Integer) this.getControladorUtil()
					.inserir(debitoACobrarGeral);
			debitoACobrarGeral.setId(idDebitoACobrarGeral);

			// Cria uma inst�ncia de d�bito a cobrar
			DebitoACobrar debitoACobrar = new DebitoACobrar();
			debitoACobrar.setId(debitoACobrarGeral.getId());
			debitoACobrar.setDebitoACobrarGeral(debitoACobrarGeral);

			// Seta o Im�vel
			debitoACobrar.setImovel(imovel);

			// Seta o D�bito Tipo
			debitoACobrar.setDebitoTipo(debitoTipo);

			// Seta Data e Hora Atual
			debitoACobrar.setGeracaoDebito(dataAtual);

			// Seta ano/m�s da conta emitida como 2 via
			debitoACobrar.setAnoMesReferenciaDebito(anoMesReferencia);

			// Seta Ano/M�s de Cobran�a
			debitoACobrar.setAnoMesCobrancaDebito(sistema
					.getAnoMesArrecadacao());

			// Seta Ano/M�s Refer�ncia do Faturamento
			//Alteracao CRC1389 Data:09/03/2009 
			//Author: R�mulo Aur�lio 
			//Analista: Rosana Carvalho

			int anoMesAtual =  Util.getAnoMesComoInt(new Date());
			
			if(sistema
					.getAnoMesFaturamento().compareTo(anoMesAtual) < 0){
			
				debitoACobrar.setAnoMesReferenciaContabil(anoMesAtual);
			
			}else{
				debitoACobrar.setAnoMesReferenciaContabil(sistema
						.getAnoMesFaturamento());
			}
			//Fim Alteracao CRC1389 Data:09/03/2009

			// Seta Valor do D�bito
			debitoACobrar.setValorDebito(valor);

			// Seta N�mero de Presta��es do D�bito
			debitoACobrar.setNumeroPrestacaoDebito(new Short("1"));

			// Seta N�mero de Presta��es Cobradas
			debitoACobrar.setNumeroPrestacaoCobradas(new Short("0"));

			// Seta Localidade
			debitoACobrar.setLocalidade(imovel.getLocalidade());

			// Seta Quadra
			debitoACobrar.setQuadra(imovel.getQuadra());

			// Seta C�digo do Setor Comercial
			debitoACobrar.setCodigoSetorComercial(imovel.getSetorComercial()
					.getCodigo());

			// Seta N�mero Quadra
			debitoACobrar.setNumeroQuadra(imovel.getQuadra().getNumeroQuadra());

			// Seta Lote
			debitoACobrar.setNumeroLote(imovel.getLote());

			// Seta SubLote
			debitoACobrar.setNumeroSubLote(imovel.getSubLote());

			// Seta Taxa de Juros do Financiamento
			debitoACobrar.setPercentualTaxaJurosFinanciamento(new BigDecimal(
					"0"));

			// Seta Financiamento Tipo
			debitoACobrar.setFinanciamentoTipo(debitoTipo
					.getFinanciamentoTipo());

			// Seta Lan�amento Item Cont�bil
			debitoACobrar.setLancamentoItemContabil(debitoTipo
					.getLancamentoItemContabil());

			// Seta D�bito Cr�dito Situa��o
			debitoACobrar.setDebitoCreditoSituacaoAtual(debitoCreditoSituacao);

			// Seta Cobran�a Forma
			debitoACobrar.setCobrancaForma(cobrancaForma);

			// Seta a data de ultima altera��o
			debitoACobrar.setUltimaAlteracao(new Date());

			Integer idDebitoACobrar = (Integer) this.getControladorUtil()
					.inserir(debitoACobrar);

			debitoACobrar.setId(idDebitoACobrar);

			// Recupera Categorias por Im�vel
			Collection<Categoria> colecaoCategoria = this
					.getControladorImovel().obterQuantidadeEconomiasCategoria(
							imovel);
			// Recupera Valores por Categorias
			Collection<BigDecimal> colecaoValoresCategorias = this
					.getControladorImovel().obterValorPorCategoria(
							colecaoCategoria, valor);
			// Insere d�bito a cobrar por categoria
			inserirDebitoACobrarCategoria(colecaoCategoria,
					colecaoValoresCategorias, debitoACobrar);

		} catch (Exception ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * M�todo que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento,o numero da prestacao e o numero total de
	 * presta��es
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author S�vio Luiz,Vivianne Sousa
	 * @date 19/05/2006,16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomatico(Integer idConta)
			throws ControladorException{
		try {
			return repositorioFaturamento.
				pesquisarParmsDebitoAutomaticoParcelasMaisJurosParcelamento(idConta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	/**
	 * M�todo que retorna uma array de object com a soma do valor dos debitos
	 * cobrados de parcelamento,o numero da prestacao e o numero total de
	 * presta��es
	 * 
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistorico(
			Integer idConta) throws ControladorException{
		try {
			return repositorioFaturamento.
			pesquisarParmsDebitoAutomaticoHistoricoParcelasMaisJurosParcelamento(idConta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	/**
	 * M�todo que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author S�vio Luiz, Vivianne Sousa
	 * @date 19/05/2006, 16/01/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoPorTipo(Integer idConta)
	 throws ControladorException{
		try {
			return repositorioFaturamento.
				pesquisarParmsDebitoCobradoPorTipoSemParcelasEJurosParcelamento(idConta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	} 
	
	/**
	 * M�todo que retorna uma array de object do debito cobrado ordenado pelo
	 * tipo de debito
	 * 
	 * 
	 * [UC0482]Emitir 2� Via de Conta
	 * 
	 * [SB0013] Gerar Linhas dos D�bitos Cobrados
	 * 
	 * @author Vivianne Sousa
	 * @date 16/05/2007
	 * 
	 * 
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List pesquisarParmsDebitoCobradoHistoricoPorTipo(Integer idConta)
	 throws ControladorException{
		try {
			return repositorioFaturamento.
			pesquisarParmsDebitoCobradoHistoricoPorTipoSemParcelasEJurosParcelamento(idConta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos servi�os de parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2008
	 * 
	 * @param conta
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDebitoCobradoDeParcelamento(Conta conta)
	 throws ControladorException{
		try {
			return repositorioFaturamento.pesquisarDebitoCobradoDeParcelamentoMaisJurosParcelamento(conta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	} 
	
	/**
	 * [UC0745] - Gerar Arquivo Texto para Faturamento
	 * 
	 * [SB0002] - Obter dados dos servi�os de parcelamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2008
	 * 
	 * @param conta
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDebitoCobradoNaoParcelamento(Conta conta)
	 throws ControladorException{
		try {
			return repositorioFaturamento.pesquisarDebitoCobradoNaoParcelamentoEJurosParcelamento(conta);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	} 
	
	
	/**
	 * Met�do respons�vel por emitir os txts das contas.
	 * 
	 * [UC0348] Emitir Contas
	 * 
	 * [SB0009] Obter Mensagem de Rateio de Consumo ou Consumo fixo de Esgoto
	 * 
	 * @author S�vio Luiz
	 * @date 19/05/2006
	 * 
	 * @param colecaoConta
	 * @throws ControladorException
	 */
	public StringBuilder obterMensagemRateioConsumo(
			EmitirContaHelper emitirContaHelper, String consumoRateio,
			Object[] parmsMedicaoHistorico, Integer tipoMedicao)
			throws ControladorException {
		StringBuilder mensagemConsumo = new StringBuilder();
		// caso o consumo de rateio seja diferente de vazio
		if (!consumoRateio.equals("") && !consumoRateio.equals("0")) {
			mensagemConsumo.append("CONSUMO DO RATEIO - ");
			mensagemConsumo.append(Util.completaStringComEspacoAEsquerda(
					consumoRateio, 6));
			mensagemConsumo.append("M3");
			// sen�o completa com espa�os em branco
			mensagemConsumo.append(Util.completaString("", 4));
		} else {
			// sen�o completa com espa�os em branco
			mensagemConsumo.append(Util.completaString("", 32));
		}

		return mensagemConsumo;
	}
	
	 /**
     * Met�do respons�vel por emitir os txts das contas.
     * 
     * [UC0348] Emitir Contas
     * 
     * [SB0009] Obter Mensagem de Rateio de Consumo ou Consumo fixo de Esgoto
     * 
     * @author Vivianne Sousa
     * @date 13/11/2007
     * 
     * @param colecaoConta
     * @throws ControladorException
     */
    public StringBuilder obterMensagemRateioConsumoFichaCompensacao(
            EmitirContaHelper emitirContaHelper, String consumoRateio,
            Object[] parmsMedicaoHistorico, Integer tipoMedicao)
            throws ControladorException {
        StringBuilder mensagemConsumo = new StringBuilder();
        // caso o consumo de rateio seja diferente de vazio
        if (!consumoRateio.equals("") && !consumoRateio.equals("0")) {
            mensagemConsumo.append("CONSUMO DO RATEIO - ");
            mensagemConsumo.append(Util.completaStringComEspacoAEsquerda(consumoRateio, 6));
            mensagemConsumo.append("M3");
            // sen�o completa com espa�os em branco
            mensagemConsumo.append(Util.completaString("", 3));
        } else {
                    // sen�o completa com espa�os em branco
                    mensagemConsumo.append(Util.completaString("", 31));
        }
        
        

        return mensagemConsumo;
    }
    
	/**
	 * [UC0811] Processar Requisi��es do Dispositivo M�vel Impressao Simultanea.
	 * 
	 * M�todo criado para evitar o if "compesa" ou if "caern". Para todas as 
	 * empresas, o pr�ximo arquivo do leiturista � disponibilizado assim que o 
	 * arquivo anterior � finalizado. Apenas na compesa, n�o permite.
	 * 
	 * @author Bruno Barros
	 * @date 05/10/2010
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean liberaProximoArquivoImpressaoSimultaneaOnLine() throws ControladorException{
		return false;
	}

}