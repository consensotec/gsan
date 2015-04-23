/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.cobranca;

import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ordemservico.FiltroOrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelSituacao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.GuiaPagamentoValoresHelper;
import gsan.cobranca.bean.IndicadoresParcelamentoHelper;
import gsan.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gsan.cobranca.bean.VerificarCriterioCobrancaParaImovelHelper;
import gsan.cobranca.parcelamento.ParcelamentoPerfil;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.Util;
import gsan.util.ZipUtil;
import gsan.util.filtro.MaiorQue;
import gsan.util.filtro.MenorQue;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import javax.ejb.SessionBean;

import br.com.danhil.BarCode.Interleaved2of5;

/**
 * Controlador Cobranca CAER
 * 
 * @author Raphael Rossiter
 * @date 26/07/2006
 */
public class ControladorCobrancaCAERSEJB extends ControladorCobranca implements
		SessionBean {
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAER
	// ==============================================================================================================

	private static final long serialVersionUID = 1L;
	
	/**
	 * [UC0251] Gerar Atividade de A??o de Cobran?a [SB0004] Verificar Crit?rio
	 * de Cobran?a para Im?vel
	 * 
	 * @author Pedro Alexandre
	 * @created 08/02/2006
	 * 
	 * @param imovel
	 *            Im?vel
	 * @param acaoCobranca
	 *            A??o de Cobran?a
	 * @param cobrancaCriterio
	 *            Crit?riode cobran?a para ser utilizado
	 * @param colecaoCobrancaCriterioLinha
	 *            Cole??o de linha de crit?rio de cobran?a
	 * @param anoMesReferenciaInicial
	 *            Ano/M?s de refer?ncia inicial
	 * @param anoMesReferenciaFinal
	 *            Ano/M?s de refer?ncia final
	 * @param dataVencimentoInicial
	 *            Data de vencimento inicial
	 * @param dataVencimentoFinal
	 *            Data de vencimento final
	 * @param colecaoDebitosNotificados
	 *            Cole??o de D?bitos Notificados
	 * @param sistemaParametros
	 *            Par?metros do Sistema
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 * 
	 * @return Retorna um objeto contendo todas as informa??es necess?rias prara
	 *         identificar se um im?vel satisfaz os crit?rios de cobran?a
	 */
	@SuppressWarnings("unused")
	public VerificarCriterioCobrancaParaImovelHelper verificarCriterioCobrancaParaImovel(
			Imovel imovel, CobrancaAcao acaoCobranca,
			CobrancaCriterio cobrancaCriterio,
			Collection<CobrancaCriterioLinha> colecaoCobrancaCriterioLinha,
			String anoMesReferenciaInicial, String anoMesReferenciaFinal,
			Date dataVencimentoInicial, Date dataVencimentoFinal,
			Collection<CobrancaDocumentoItem> colecaoDebitosNotificados,
			SistemaParametro sistemaParametros,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			ImovelNaoGerado imovelNaoGerado, Integer idDocumentoTipoCobrancaAcaoPrecedente,
			boolean validacaoPorItemCobrado)
			throws ControladorException {

		// cria o objeto de retorno do met?do
		VerificarCriterioCobrancaParaImovelHelper verificarCriterioCobrancaParaImovelHelper = new VerificarCriterioCobrancaParaImovelHelper();

		// seta a flag do crit?rio de cobran?a para false(o im?vel n?o satisfaz
		// o crit?rio de cobran?a)
		verificarCriterioCobrancaParaImovelHelper
				.setFlagCriterioCobrancaImovel(false);

	
		if (anoMesReferenciaInicial == null 
				|| anoMesReferenciaInicial.trim().equals("")) {
			anoMesReferenciaInicial = "000101"; 
			anoMesReferenciaFinal = sistemaParametros.getAnoMesArrecadacao().toString();
		}

		if (dataVencimentoInicial == null) {
			Calendar calendario = Calendar.getInstance();
	
			calendario.add(Calendar.MONTH, -1);
	
			String ultimoDiaMesAnterior = "";
			ultimoDiaMesAnterior = calendario.getActualMaximum(Calendar.DAY_OF_MONTH) + "";
	
			if (calendario.get(Calendar.MONTH) < 9) {
				ultimoDiaMesAnterior = ultimoDiaMesAnterior + "/0" + (calendario.get(Calendar.MONTH) + 1);
			} else {
				ultimoDiaMesAnterior = ultimoDiaMesAnterior + "/" + (calendario.get(Calendar.MONTH) + 1);
			}
			ultimoDiaMesAnterior = ultimoDiaMesAnterior + "/" + calendario.get(Calendar.YEAR);
	
			dataVencimentoInicial = Util.converteStringParaDate("01/01/0001");
			dataVencimentoFinal = Util.converteStringParaDate(ultimoDiaMesAnterior);
		}


		// item 1
		// se o indicador de emiss?o da a??o de cobran?a para im?vel,com
		// situa??o especial de cobran?a
		// e o im?vel esteja com algum tipo de situa??o especial de cobran?a
		if (cobrancaCriterio.getIndicadorEmissaoImovelParalisacao()
				.shortValue() == 2
				&& imovel.getCobrancaSituacaoTipo() != null) {

			MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
				new MotivoNaoGeracaoDocCobranca();
			motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.IMOVEL_EM_SITUACAO_ESPECIAL_COBRANCA);
			imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
			
			getControladorUtil().inserir(imovelNaoGerado);
			
			imovelNaoGerado = null;
			
			// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
			return verificarCriterioCobrancaParaImovelHelper;
		}
		// fim item 1

		// item 2
		Collection colecaoImovelCobrancaSituacao = null;
		try {
			colecaoImovelCobrancaSituacao = repositorioCobranca
					.pesquisarImovelCobrancaSituacao(imovel.getId());

			// erro nohibernate
		} catch (ErroRepositorioException ex) {
			// levanta a exce??o para a pr?xima camada
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoImovelCobrancaSituacao != null && !colecaoImovelCobrancaSituacao.isEmpty()){

			// se o indicador de emiss?o da a??o de cobran?a para im?vel,com
			// situa??o de cobran?a correspondente a " 2-N?O"
			// e o im?vel esteja com algum tipo de situa??o de cobran?a, 
			// passar o imovel, indicando q o imovel nao satisfaz o criterio de cobranca

			if (cobrancaCriterio.getIndicadorEmissaoImovelSituacaoCobranca()
					.shortValue() == 2) {

				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
					new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CRITERIO_NAO_PERMITE_SITUACAO_COBRANCA);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
				
				getControladorUtil().inserir(imovelNaoGerado);
				
				imovelNaoGerado = null;
				
					// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
					return verificarCriterioCobrancaParaImovelHelper;
			} else {
				// Item 4.2 - Caso o indicado de emissao da acao de cobranca para imovel com situacao 
				// de cobranca, corresponder a SIM (1) 
				// Verificar se a situacao de cobranca do imovel esta contida nas 
				// situacoes de cobranca contempladas no criterio de cobranca
				
				boolean situacaoCobrancaImovelContidaNasSelecionadas = false;
				

				// pesquisar a colecao de criterios para situacao ligacao agua, esgoto e cobranca
				FiltroCriterioSituacaoCobranca filtroCritSitCobranca= new FiltroCriterioSituacaoCobranca();
				filtroCritSitCobranca.adicionarParametro(new ParametroSimples(
						FiltroCriterioSituacaoCobranca.COBRANCA_CRITERIO_ID,
						cobrancaCriterio.getId()));
				filtroCritSitCobranca.adicionarCaminhoParaCarregamentoEntidade(FiltroCriterioSituacaoCobranca.COBRANCA_SITUACAO);
				Collection colecaoSituacoesCobrancaSelecionadas = getControladorUtil().pesquisar(filtroCritSitCobranca, CriterioSituacaoCobranca.class.getName());				
				
//				Collection colecaoSituacoesCobrancaSelecionadas = cobrancaCriterio
//					.getCriteriosSituacaoCobranca();
				
				if (colecaoSituacoesCobrancaSelecionadas != null && 
						!colecaoSituacoesCobrancaSelecionadas.isEmpty()){
				
					for (Iterator iterSitImov = colecaoImovelCobrancaSituacao.iterator(); 
							iterSitImov.hasNext();) {
						Integer idSitCobImov = (Integer) iterSitImov.next();
						
						for (Iterator iter = colecaoSituacoesCobrancaSelecionadas.iterator(); 
							iter.hasNext();) {
							CriterioSituacaoCobranca critSitCob = (CriterioSituacaoCobranca) iter.next();
							if (critSitCob.getComp_id().getCobrancaSituacao().getId().intValue() == 
								idSitCobImov.intValue()){
								situacaoCobrancaImovelContidaNasSelecionadas = true;
								break;
							}				
						}
					}
									
				}	

				if (!situacaoCobrancaImovelContidaNasSelecionadas){
					
					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.IMOVEL_EM_SITUACAO_COBRANCA_NAO_PERMITIDA);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

					getControladorUtil().inserir(imovelNaoGerado);
					
					imovelNaoGerado = null;
					
					return verificarCriterioCobrancaParaImovelHelper;		
				}
				
			}
		}
		else if (cobrancaCriterio.getIndicadorImovelComSituacaoCobranca().shortValue() == 1){
			
			MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
			new MotivoNaoGeracaoDocCobranca();
			motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.IMOVEL_EM_SITUACAO_COBRANCA_NAO_PERMITIDA);
			imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

			getControladorUtil().inserir(imovelNaoGerado);
				
			imovelNaoGerado = null;
				
			return verificarCriterioCobrancaParaImovelHelper;
		}
		// fim item 2

		// item 3
		// cria a flag que vai indicar se o perfil do im?vel exista na cole??o
		// de linhas de crit?rio de cobran?a
		boolean flagPerfilImovel = false;

		// la?o para verificar todas as linhas
		labelLoop: for (CobrancaCriterioLinha cobrancaCriterioLinha : colecaoCobrancaCriterioLinha) {
			// se o perfil for igual
			if (cobrancaCriterioLinha.getImovelPerfil().getId().intValue() == imovel
					.getImovelPerfil().getId().intValue()) {
				// seta a flag par true
				flagPerfilImovel = true;

				// encerrar o la?o
				break labelLoop;
			}
		}

		// se existe o perfil do im?vel em alguma linha de crit?rio de cobran?a
		if (!flagPerfilImovel) {
			
			MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
				new MotivoNaoGeracaoDocCobranca();
			motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.NAO_HA_CRITERIO_PARA_O_PERFIL);
			imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
			
			getControladorUtil().inserir(imovelNaoGerado);
			
			imovelNaoGerado = null;
			
			// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
			return verificarCriterioCobrancaParaImovelHelper;
		}
		// fim item 3

		// item 4
		// cria as vari?veis que v?o ser utilizadas para obter o d?bito do
		// im?vel
		final int indicadorDebitoImovel = 1;
		String matriculaImovel = imovel.getId().toString();
		String codigoCliente = null;
		Short clienteRelacaoTipo = null;
		int indicadorPagamento = 1;
		int indicadorConta = cobrancaCriterio.getIndicadorEmissaoContaRevisao();
		int indicadorDebitoACobrar = acaoCobranca.getIndicadorCobrancaDebACobrar();
		int indicadorCreditoARalizar = acaoCobranca.getIndicadorCreditosARealizar().intValue();
		int indicadorNotasPromissorias = acaoCobranca.getIndicadorNotasPromissoria().intValue();
		int indicadorGuiaPagamento = 2;
		int indicadorCalcularAcrescimoImpontualidade = acaoCobranca
				.getIndicadorAcrescimoImpontualidade().intValue();
		int indicadorCalcularAcrescimoImpontualidadeGuiaPagamentoExtratoDebito = ConstantesSistema.NAO.intValue();
		if(acaoCobranca!=null 
				&& acaoCobranca.getId().compareTo(CobrancaAcao.CARTA_FINAL_DE_ANO)==0){	
			indicadorGuiaPagamento = 1;
			indicadorConta = 2;
		}
		
		// obt?m o d?bito do im?vel
		ObterDebitoImovelOuClienteHelper debitoImovel = this
				.obterDebitoImovelOuCliente(indicadorDebitoImovel,
						matriculaImovel, codigoCliente, clienteRelacaoTipo,
						anoMesReferenciaInicial, anoMesReferenciaFinal,
						dataVencimentoInicial, dataVencimentoFinal,
						indicadorPagamento, indicadorConta,
						indicadorDebitoACobrar, indicadorCreditoARalizar,
						indicadorNotasPromissorias, indicadorGuiaPagamento,
						indicadorCalcularAcrescimoImpontualidade, null,
						indicadorCalcularAcrescimoImpontualidadeGuiaPagamentoExtratoDebito);
		// fim item 4

		// recupera as cole??es de conta, guia de pagamento e d?bito a cobrar do
		// im?vel
		Collection<ContaValoresHelper> colecaoContasValores = debitoImovel
				.getColecaoContasValores();
		Collection<DebitoACobrar> colecaoDebitoACobrar = debitoImovel
				.getColecaoDebitoACobrar();
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores = debitoImovel
				.getColecaoGuiasPagamentoValores();

		// item 5
		// cria as cole??es de contas, guia de pagamentoe d?bito a cobrar
		// que v?o ser exclu?das das cole??es anteriores
		Collection<ContaValoresHelper> colecaoContasValoresParaRemocao = new ArrayList();
		Collection<DebitoACobrar> colecaoDebitoACobrarParaRemocao = new ArrayList();
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValoresRemocao = new ArrayList();

		if (colecaoContasValores != null) {

			// la?o para verificar quais contas n?o constam nos d?bitos
			// notificados
			for (ContaValoresHelper contaValorHelper : colecaoContasValores) {
				// cria a flag que vai indicar se a conta consta nos d?bitos
				// notificados

				if (contaValorHelper.getValorPago() != null
						&& contaValorHelper.getValorPago().compareTo(
								new BigDecimal(0)) == 1) {
					// adiciona a conta na cole??o que vai ser removida
					colecaoContasValoresParaRemocao.add(contaValorHelper);
				} else {
					boolean flagAchouConta = false;
					// se a cole??o de d?bitos notificados n?o estiver vazia
					if (colecaoDebitosNotificados != null
							&& !colecaoDebitosNotificados.isEmpty()) {
						// la?o para verificar os items de documento de cobran?a
						labelConta: for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoDebitosNotificados) {
							// se o item ? uma conta
							if (cobrancaDocumentoItem.getContaGeral() != null) {
								// se a conta ? a mesma do item
								if (cobrancaDocumentoItem.getContaGeral().getId().intValue() 
										== contaValorHelper.getConta().getId().intValue()) {
									// seta que a conta consta nos d?bitos
									// notificados
									flagAchouConta = true;

									// para o la?o de item de documento de
									// cobran?a
									// e passa para a pr?xima conta
									break labelConta;
								}
							}
						}
					}
					// se a conta n?o consta nos items
					if (!flagAchouConta 
							&& acaoCobranca.getCobrancaAcaoPredecessora() != null) {
						// adiciona a conta na cole??o que vai ser removida
						colecaoContasValoresParaRemocao
								.add(contaValorHelper);
					}
					// Alterado por S?vio Luiz Analista:Adriano Brito
					// Data:11/10/2007
					else {
						if (contaValorHelper.getConta() != null) {
							if (contaValorHelper.getConta()
									.getDebitoCreditoSituacaoAtual() != null
									&& contaValorHelper
											.getConta()
											.getDebitoCreditoSituacaoAtual()
											.getId()
											.equals(
													DebitoCreditoSituacao.PARCELADA)) {
								// adiciona a conta na cole??o que vai ser
								// removida
								colecaoContasValoresParaRemocao
										.add(contaValorHelper);
							}

						}
					}

				}

			}
		}

		// la?o para verificar quais d?bitos a cobrar n?o constam nos
		// d?bitos notificados
		if (colecaoDebitoACobrar != null) {
			for (DebitoACobrar debitoACobrar : colecaoDebitoACobrar) {

				// cria a flag que vai indicar se o d?bito a cobrar consta
				// nos
				// d?bitos notificados
				boolean flagAchouDebitoACobrar = false;
				// se a cole??o de d?bitos notificados n?o estiver vazia
				if (colecaoDebitosNotificados != null
						&& !colecaoDebitosNotificados.isEmpty()) {

					// la?o para verificar os items de documento de cobran?a
					labelDebitoACobrar: for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoDebitosNotificados) {

						// se o item ? um d?bito a cobrar
						if (cobrancaDocumentoItem.getDebitoACobrarGeral() != null) {

							// se o d?bito a cobrar ? o mesmo do item
							if (cobrancaDocumentoItem.getDebitoACobrarGeral().getId().intValue() 
									== debitoACobrar.getId().intValue()) {
								// seta que o d?bito a cobrar consta nos d?bitos
								// notificados
								flagAchouDebitoACobrar = true;

								// para o la?o de item de documento de cobran?a
								// e passa para o pr?ximo d?bito a cobrar
								break labelDebitoACobrar;
							}
						}
					}
				} 
				// se o d?bito a cobrar n?o consta nos items
				if (!flagAchouDebitoACobrar) {
					// adiciona o d?bito a cobrar na cole??o que vai ser
					// removida
					colecaoDebitoACobrarParaRemocao.add(debitoACobrar);
				}

			}
		}

		// la?o para verificar quais guias de pagamento n?o constam nos
		// d?bitos notificados
		if (colecaoGuiasPagamentoValores != null) {
			for (GuiaPagamentoValoresHelper guiaPagamentoValoresHelper : colecaoGuiasPagamentoValores) {
				if (guiaPagamentoValoresHelper.getValorPago() != null
						&& guiaPagamentoValoresHelper.getValorPago().compareTo(
								new BigDecimal(0)) == 1) {
					// adiciona a conta na cole??o que vai ser removida
					colecaoGuiasPagamentoValoresRemocao
							.add(guiaPagamentoValoresHelper);
				} else {
					// cria a flag que vai indicar se a guia de pagamento
					// consta
					// nos
					// d?bitos notificados
					boolean flagAchouGuiaPagamento = false;

					// se a cole??o de d?bitos notificados n?o estiver vazia
					if (colecaoDebitosNotificados != null
							&& !colecaoDebitosNotificados.isEmpty()) {

						// la?o para verificar os items de documento de
						// cobran?a
						labelGuiaPagamento: for (CobrancaDocumentoItem cobrancaDocumentoItem : colecaoDebitosNotificados) {

							// se o item ? uma guia de pagamento
							if (cobrancaDocumentoItem.getGuiaPagamentoGeral() != null) {

								// se a guia de pagamento ? o mesmo do item
								if (cobrancaDocumentoItem.getGuiaPagamentoGeral().getId().intValue() 
									== guiaPagamentoValoresHelper.getGuiaPagamento().getId().intValue()) {
									// seta que a guia de pagamento consta
									// nos
									// d?bitos
									// notificados
									flagAchouGuiaPagamento = true;

									// para o la?o de item de documento de
									// cobran?a
									// e passa para a pr?xima guia de
									// pagamento
									break labelGuiaPagamento;
								}
							}
						}
						// se a guia de pagamento n?o consta nos items
						if (!flagAchouGuiaPagamento) {
							// adiciona a guia de pagamento na cole??o que vai
							// ser
							// removida
							colecaoGuiasPagamentoValoresRemocao
									.add(guiaPagamentoValoresHelper);
						}
					}

				}
			}
		}

		// remove as contas, d?bitos a cobrar e guias de pagamento
		// que n?o constam nos itens de documentode cobran?a
		if (colecaoContasValores != null) {
			colecaoContasValores.removeAll(colecaoContasValoresParaRemocao);
		}
		if (colecaoDebitoACobrar != null) {
			colecaoDebitoACobrar.removeAll(colecaoDebitoACobrarParaRemocao);
		}
		if (colecaoGuiasPagamentoValores != null) {
			colecaoGuiasPagamentoValores
					.removeAll(colecaoGuiasPagamentoValoresRemocao);
		}

		// fim item 5
		
		//RM93 - adicionado por Vivianne Sousa - 23/12/2010 - analista:Rosana Carvalho
		if(validacaoPorItemCobrado || acaoCobranca.getCobrancaAcaoPredecessoraAlternativa() != null){
			
			if (idDocumentoTipoCobrancaAcaoPrecedente == null) {
				if (acaoCobranca.getCobrancaAcaoPredecessoraAlternativa().getDocumentoTipo() != null &&
					acaoCobranca.getCobrancaAcaoPredecessoraAlternativa().getDocumentoTipo().getId() != null){
					
					idDocumentoTipoCobrancaAcaoPrecedente = 
						acaoCobranca.getCobrancaAcaoPredecessoraAlternativa().getDocumentoTipo().getId();
				}
			}
			
			
			if (idDocumentoTipoCobrancaAcaoPrecedente != null) {
				
				FiltroDocumentoCobranca filtroDocCobranca = new FiltroDocumentoCobranca();
				filtroDocCobranca.adicionarParametro(new ParametroSimples(FiltroDocumentoCobranca.ID, idDocumentoTipoCobrancaAcaoPrecedente));
				filtroDocCobranca.adicionarCaminhoParaCarregamentoEntidade("");
				
				boolean verificaValidacaoPorItemCobrado = verificaValidacaoPorItemCobrado(
						imovel.getId(),idDocumentoTipoCobrancaAcaoPrecedente,
						colecaoContasValores, colecaoDebitoACobrar, null);
				
				//e qualquer um dos itens cobrados n?o tenha sido cobrado nas ?ltimas
				//6(seis) a??es relativa ? a??o precedente passar para o pr?ximo im?vel
				if(!verificaValidacaoPorItemCobrado){
					
					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.NAO_EXISTE_DOCUMENTO_PRECEDENTE_VALIDO);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);
					
					getControladorUtil().inserir(imovelNaoGerado);
					
					imovelNaoGerado = null;
					
					return verificarCriterioCobrancaParaImovelHelper;
				}
				
			} 
			
		}
		
		// inicio item 6
		
		// Desenvolvedor:Hugo Amorim Analista:Ana Cristina Data:30/06/2010
		// Verifica Consumo e Tipo de Medi??o,
		// Caso consumo medio inicial, final e tipo de consumo
		// tenham sido informados no comando eventual.
		if (cobrancaAcaoAtividadeComando != null
			&& cobrancaAcaoAtividadeComando.getConsumoMedioInicial()!=null
			&& !cobrancaAcaoAtividadeComando.getConsumoMedioInicial().toString().equals("")
			&& cobrancaAcaoAtividadeComando.getConsumoMedioFinal()!=null
			&& !cobrancaAcaoAtividadeComando.getConsumoMedioFinal().toString().equals("")
			&& cobrancaAcaoAtividadeComando.getTipoConsumo()!=null
			&& !cobrancaAcaoAtividadeComando.getTipoConsumo().toString().equals("")){
			
			Integer consumoMes = null;
			// Pesquisa consumo do imovel nos ultimos 6 meses,
			// se houver verifica se atende ao intervalo informado
			// no comando.
			labelPesquisaConsumoUltimos6Meses : for (int i = 0; i <= 6; i++) {
				
				Integer anoMes = 
					Util.subtrairMesDoAnoMes(sistemaParametros.getAnoMesFaturamento(), i);
				
				consumoMes = 
					getControladorMicromedicao()
						.pesquisarNumeroConsumoMedioImovel(imovel.getId(),anoMes);
				
				if(consumoMes!=null){
					break labelPesquisaConsumoUltimos6Meses;
				}
			}
			
			boolean naoAtendeAoCriterioDeConsumo = true;
			
			// Caso possua consumo verifica se o mesm o atende
			// ao intervalo informado.
			if(consumoMes!=null){
				
				// Verifica se consumo n?o esta no intervalo informado para criterio.
				if(consumoMes.compareTo(cobrancaAcaoAtividadeComando.getConsumoMedioInicial())>=0
						&& consumoMes.compareTo(cobrancaAcaoAtividadeComando.getConsumoMedioFinal())<=0){
					
					boolean existeHidrometro = 
						getControladorAtendimentoPublico()
							.verificarExistenciaHidrometroEmLigacaoAgua(imovel.getId());
					// MEDIDO
					if(cobrancaAcaoAtividadeComando.getTipoConsumo().compareTo(new Short("1"))==0
							&& existeHidrometro){
						naoAtendeAoCriterioDeConsumo = false;
					}
					// NAO MEDIDO
					if(cobrancaAcaoAtividadeComando.getTipoConsumo().compareTo(new Short("2"))==0
							&& !existeHidrometro){
						naoAtendeAoCriterioDeConsumo = false;
					}
					// AMBOS
					if(cobrancaAcaoAtividadeComando.getTipoConsumo().compareTo(new Short("3"))==0){
						naoAtendeAoCriterioDeConsumo = false;
					}
					
				}

			}
			// Caso n?o atenda ao criterio indica que o im?vel n?o satisfaz o crit?rio de cobran?a.
			if(naoAtendeAoCriterioDeConsumo){
						
				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = new MotivoNaoGeracaoDocCobranca();
				
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CONSUMO_MEDIO_FORA_INTERVALO_DEFINIDO_CRITERIO);
				
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
				
				getControladorUtil().inserir(imovelNaoGerado);
				
				imovelNaoGerado = null;
				
				return verificarCriterioCobrancaParaImovelHelper;
			}
		}
		// fim item 6
		
		// inicio item Período de Fiscalização do Imóvel
		
		// Desenvolvedor:Hugo Amorim Analista:Fatima Data:15/07/2010
		// Verifica Consumo e Tipo de Medição,
		// Caso consumo medio inicial, final e tipo de consumo
		// tenham sido informados no comando eventual.
		
		boolean existeSituacaoFiscalizacaoFiltro = false;
		Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> colecaoCobrancaAcaoFisc = null;
		
		if (cobrancaAcaoAtividadeComando != null){
			
			FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao filtroCobrancaAcaoFisc
				= new FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao();
		
			filtroCobrancaAcaoFisc.adicionarParametro(
				new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID,
					cobrancaAcaoAtividadeComando.getId()));
			
			filtroCobrancaAcaoFisc.adicionarCaminhoParaCarregamentoEntidade(
					FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.FISCALIZACAO_SITUACAO);
				
			colecaoCobrancaAcaoFisc =
				getControladorUtil().pesquisar(filtroCobrancaAcaoFisc, 
					CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class.getName());
	
			if(!Util.isVazioOrNulo(colecaoCobrancaAcaoFisc)){
				existeSituacaoFiscalizacaoFiltro = true;
			}
				
		}
		
		if (cobrancaAcaoAtividadeComando != null &&
			((cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()!=null &&
			!cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao().equals("")) ||
			(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()!=null &&
			!cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao().equals("")) ||
			existeSituacaoFiscalizacaoFiltro)){
			
			Collection<OrdemServico> colecaoOs = null;
			
			//	Caso o par?metro Per?odo de Fiscaliza??o do Im?vel esteja informado.
			if((cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()!=null &&
				!cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao().equals("")) ||
				(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()!=null &&
				!cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao().equals(""))){
				
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				
				filtroOrdemServico.adicionarParametro(
						new ParametroSimples(FiltroOrdemServico.ID_IMOVEL, imovel.getId()));
				
				if((cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao()!=null &&
						!cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao().equals(""))){
					filtroOrdemServico.adicionarParametro(
							new MaiorQue(FiltroOrdemServico.DATA_FISCALIZACAO_SITUACAO, 
								Util.formatarDataInicial(cobrancaAcaoAtividadeComando.getPeriodoInicialFiscalizacao())));
				}
				if((cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao()!=null &&
						!cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao().equals(""))){
					filtroOrdemServico.adicionarParametro(
							new MenorQue(FiltroOrdemServico.DATA_FISCALIZACAO_SITUACAO, 
								Util.formatarDataFinal(cobrancaAcaoAtividadeComando.getPeriodoFinalFiscalizacao())));
				}
				
				colecaoOs = this.getControladorUtil()
					.pesquisar(filtroOrdemServico, OrdemServico.class.getName());
				
				// Caso n?o exista fiscaliza??o para o im?vel no per?odo indicado 
				if(Util.isVazioOrNulo(colecaoOs)){
					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.SITUACAO_FISCALIZACAO_NAO_ATENDE_CRITERIO);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
					
					getControladorUtil().inserir(imovelNaoGerado);
					
					imovelNaoGerado = null;

					// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
					return verificarCriterioCobrancaParaImovelHelper;
				}
				// Caso exista fiscaliza??o para o im?vel
				else{
					if(existeSituacaoFiscalizacaoFiltro){				
						//	Verifica se situa??es de fiscaliza??o do im?vel estejam 
						// contidas nas situa??es selecionadas para o comando.
						situacoesFiscalizacaoComandoAcao : for (OrdemServico ordemServico : colecaoOs) {
							boolean atendeCriteiro = false;
							for (CobrancaAcaoAtividadeComandoFiscalizacaoSituacao cobAcaoFisc : colecaoCobrancaAcaoFisc) {
								if(cobAcaoFisc.getFiscalizacaoSituacao().getId().compareTo(
									ordemServico.getFiscalizacaoSituacao().getId())==0){
									atendeCriteiro = true;
								}
								if(atendeCriteiro){
									continue situacoesFiscalizacaoComandoAcao;
								}
								//	Caso as situa??es de fiscaliza??o do im?vel n?o 
								// estejam contidas nas situa??es selecionadas para o comando.
								else{
									MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
										new MotivoNaoGeracaoDocCobranca();
									motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.SITUACAO_FISCALIZACAO_NAO_ATENDE_CRITERIO);
									imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
									
									getControladorUtil().inserir(imovelNaoGerado);
									
									imovelNaoGerado = null;

									// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
									return verificarCriterioCobrancaParaImovelHelper;
								}						
							}		
						}
					}
				}	
			}
			//	Caso contr?rio (par?metro Per?odo de Fiscaliza??o do Im?vel n?o informado) e
			// caso o par?metro Situa??o da Fiscaliza??o esteja informado
			else if(existeSituacaoFiscalizacaoFiltro){
				
				Collection<Integer> colecaoIdsSituacaoFiscalizacao = new ArrayList<Integer>();
				for (CobrancaAcaoAtividadeComandoFiscalizacaoSituacao helper : colecaoCobrancaAcaoFisc) {
					colecaoIdsSituacaoFiscalizacao.add(helper.getFiscalizacaoSituacao().getId());
				}
				
				FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
				
				filtroOrdemServico.adicionarParametro(
						new ParametroSimples(FiltroOrdemServico.ID_IMOVEL, imovel.getId()));
				
				filtroOrdemServico.adicionarParametro(
							new ParametroSimplesIn(FiltroOrdemServico.DATA_FISCALIZACAO_SITUACAO, 
									colecaoIdsSituacaoFiscalizacao));
				
				colecaoOs = this.getControladorUtil()
					.pesquisar(filtroOrdemServico, OrdemServico.class.getName());
				
				//	Caso n?o exista fiscaliza??o para o im?vel com situa??o correspondente a alguma 
				// das situa??es selecionadas para o comando 
				if(Util.isVazioOrNulo(colecaoOs)){
					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.SITUACAO_FISCALIZACAO_NAO_ATENDE_CRITERIO);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
					
					getControladorUtil().inserir(imovelNaoGerado);
					
					imovelNaoGerado = null;

					// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
					return verificarCriterioCobrancaParaImovelHelper;
				}
			}			
		}
		// fim item Período de Fiscalização do Imóvel

		// item 7
		// se as cole??es de contas, d?bitos a cobrar e guias de pagamento
		// estiverem vazia
		if ((colecaoContasValores == null || colecaoContasValores.isEmpty())
				&& (colecaoDebitoACobrar == null || colecaoDebitoACobrar
						.isEmpty())
				&& (colecaoGuiasPagamentoValores == null || colecaoGuiasPagamentoValores
						.isEmpty())) {

			// item 7.1
			// Caso seja comando eventual e o indicador de selecionar apenas
			// im?veis com d?bitos seja igual a sim(1) descarta o im?vel
			if (cobrancaAcaoAtividadeComando != null
					&& cobrancaAcaoAtividadeComando.getIndicadorBoletim() != null
					&& cobrancaAcaoAtividadeComando
							.getIndicadorDebito()
							.equals(
									CobrancaAcaoAtividadeComando.INDICADOR_DEBITO_SIM)) {

				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
					new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.IMOVEL_SEM_DEBITOS);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
				
				getControladorUtil().inserir(imovelNaoGerado);
				
				imovelNaoGerado = null;

				// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
				return verificarCriterioCobrancaParaImovelHelper;
			} else {
				// caso n?o seja cmando, seja cronograma ent?o descarta
				if (cobrancaAcaoAtividadeComando == null) {

					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.IMOVEL_SEM_DEBITOS);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

					getControladorUtil().inserir(imovelNaoGerado);
					
					imovelNaoGerado = null;
					
					// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
					return verificarCriterioCobrancaParaImovelHelper;
				}
			}

		}
		// fim item 6
		
		//11. Caso seja um comando eventual e não exista nenhuma conta (da lista de contas retornada pelo [UC0067]) 
		//    com a quantidade mínima de dias de vencimento, ou seja, com a data de vencimento menor ou igual a quantidade 
		//    de dias informada (Data Atual - Quantidade de Dias de Vencido)):		
		if (cobrancaAcaoAtividadeComando != null
				&& cobrancaAcaoAtividadeComando.getIndicadorBoletim() != null
				&& colecaoContasValores != null
				&& cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento() != null
				&& cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento().compareTo(new Integer("0")) > 0) {
	
			boolean achouConta = false;
			
			for (ContaValoresHelper contaValorHelper : colecaoContasValores) {
				
				Date qtdDiasInformada = Util.subtrairNumeroDiasDeUmaData(new Date(), cobrancaAcaoAtividadeComando.getQuantidadeDiasVencimento());
				
				if(Util.compararData(contaValorHelper.getConta().getDataVencimentoConta(), qtdDiasInformada) <= 0){
					achouConta = true;
					break;
				}
			}
			
			if(!achouConta){
				//11.1. Indicar que o imóvel não satisfaz o critério de cobrança
				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CONTAS_QTD_MIN_DIAS_VENC);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
				
				getControladorUtil().inserir(imovelNaoGerado);
				
				imovelNaoGerado = null;

				//e retornar para o passo que chamou este subfluxo
				return verificarCriterioCobrancaParaImovelHelper;
			}
			
		}
		
		
		// ********************* Inicio Parte a Analisar ***********************
		
		// 11.Caso o N?mero de dias ap?s o vencimento seja diferente de nulo ou diferente de zero 
		//   e a lista de contas retornadas pelo [UC0067] n?o esteja vazia
		if(cobrancaCriterio.getNumeroDiasAposVencimento() != null
				&& cobrancaCriterio.getNumeroDiasAposVencimento().compareTo(new Short("0")) != 0
				&& colecaoContasValores != null 
				&& !colecaoContasValores.isEmpty()) {

			Iterator iteratorContas = colecaoContasValores.iterator();
			
			boolean imovelValidoParaGeracao = false;
			
			
			while(iteratorContas.hasNext()) {
				Conta conta = ((ContaValoresHelper) iteratorContas.next())
						.getConta();
				
				// 11.1. Caso a lista de contas possua pelo menos uma conta cuja diferen?a entre 
				//   a data atual e a data de vencimento da conta (CNTA_DTVENCIMENTOCONTA) 
				//   seja menor que o N?mero de dias ap?s o vencimento 
				Integer diferencaQuantidadeDias = Util.obterQuantidadeDiasEntreDuasDatas(
					conta.getDataVencimentoConta(), new Date());
				
				if (diferencaQuantidadeDias.compareTo(
					new Integer(cobrancaCriterio.getNumeroDiasAposVencimento())) > 0) {
					
					imovelValidoParaGeracao = true;
					
					break;
					
				}
							
			}
			if (!imovelValidoParaGeracao){

				//O imovel possui conta(s) com N?mero de Dias Ap?s o Vencimento inv?lido
				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CONTAS_NAO_POSSUEM_NUMERO_DAS_APOS_VENCIMENTO_VALIDO);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
				
				getControladorUtil().inserir(imovelNaoGerado);
				
				imovelNaoGerado = null;
				
				// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
				return verificarCriterioCobrancaParaImovelHelper;
			}
			
			
		}

		//************************************ Fim parte analise ***********************
		
		// item 7

		// se o indicador de emiss?o de d?bito da conta do m?s for igual a
		// 2(dois) - N?O
		if (cobrancaCriterio.getIndicadorEmissaoDebitoContaMes().intValue() == 2) {

			if (colecaoContasValores != null) {
				// se existe somente uma conta
				if (colecaoContasValores.size() == 1) {

					// recupera a conta
					Conta conta = (colecaoContasValores.iterator().next())
							.getConta();

					// se a conta for a conta do m?s
					if (conta.getReferencia() == Util
							.subtrairData(sistemaParametros
									.getAnoMesFaturamento())) {

						MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
							new MotivoNaoGeracaoDocCobranca();
						motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CRITERIO_NAO_PERMITE_DEBITO_APENAS_CONTA_MES);
						imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

						getControladorUtil().inserir(imovelNaoGerado);
						
						imovelNaoGerado = null;
						
						// indica que o im?vel n?o satisfaz o crit?rio de
						// cobran?a
						return verificarCriterioCobrancaParaImovelHelper;
					}
				}
			}
		}
		// fim item 7

		// item 8
		// se o indicador de emiss?o de d?bito da conta antiga for igual a
		// 2(dois) - N?O
		if (cobrancaCriterio.getIndicadorEmissaoDebitoContaAntiga().intValue() == 2) {

			if (colecaoContasValores != null) {
				// se existe somente uma conta
				if (colecaoContasValores.size() == 1) {

					// recupera a conta
					Conta conta = (colecaoContasValores.iterator().next())
							.getConta();

					// se a conta for uma conta antiga
					if (conta.getReferencia() <= (sistemaParametros
							.getAnoMesFaturamento() - (cobrancaCriterio
							.getNumeroContaAntiga() * 100))) {
						
						MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
							new MotivoNaoGeracaoDocCobranca();
						motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CRITERIO_NAO_PERMITE_DEBITO_APENAS_CONTA_ANTIGA);
						imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
						
						getControladorUtil().inserir(imovelNaoGerado);
						
						imovelNaoGerado = null;
						
						// indica que o im?vel n?o satisfaz o crit?rio de
						// cobran?a
						return verificarCriterioCobrancaParaImovelHelper;
					}
				}
			}
		}
		// fim item 8

		// item 9
		// se o indicador de emiss?o de d?bito da conta do m?s for igual a
		// 2(dois) - N?O
		// e o indicador de emiss?o de d?bito da conta antiga for igual a
		// 2(dois) - N?O
		if (cobrancaCriterio.getIndicadorEmissaoDebitoContaMes().intValue() == 2
				&& cobrancaCriterio.getIndicadorEmissaoDebitoContaAntiga()
						.intValue() == 2) {

			if (colecaoContasValores != null) {
				// se existe s? duas contas
				if (colecaoContasValores.size() == 2) {

					// flag que indica que a conta ? do m?s
					boolean flagContaMes = false;

					// flag que indica que a conta ? uma conta antiga
					boolean flagContaAntiga = false;

					// la?o para verificar se as contas ? uma do m?s e outra
					// antiga
					for (ContaValoresHelper contaValorHelper : colecaoContasValores) {

						// recupera a conta
						Conta conta = contaValorHelper.getConta();

						// se a conta for a do m?s
						if (conta.getReferencia() == Util
								.subtrairData(sistemaParametros
										.getAnoMesFaturamento())) {
							flagContaMes = true;
						}

						// se a conta n?o for antiga
						if (conta.getReferencia() <= (sistemaParametros
								.getAnoMesFaturamento() - (cobrancaCriterio
								.getNumeroContaAntiga() * 100))) {
							flagContaAntiga = true;
						}
					}

					// se as contas for uma antiga e a outra a do m?s
					if (flagContaMes && flagContaAntiga) {

						MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
							new MotivoNaoGeracaoDocCobranca();
						motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.CRITERIO_NAO_PERMITE_DEBITO_APENAS_CONTA_MES_E_ANTIGA);
						imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

						getControladorUtil().inserir(imovelNaoGerado);
						
						imovelNaoGerado = null;

						// indica que o im?vel n?o satisfaz o crit?rio de
						// cobran?a
						return verificarCriterioCobrancaParaImovelHelper;
					}

				}

			}
		}
		// fim item 9

		// item 10
		// o sistema calcula a quantidade de contas com parcelamento do im?vel
		int quantidadeContasParcelamento = getControladorFaturamento()
				.pesquisarQuantidadeDebitosCobradosComParcelamento(
						colecaoContasValores);

		// item 11
		// cria a vari?vel que vai armazenar o d?bito do im?vel
		BigDecimal valorDebitoImovel = new BigDecimal("0.00");

		// cria a vari?vel que vai armazenar a quantidades de itens em d?bito
		Integer quantidadeItensEmDebito = new Integer("0");

		if (colecaoContasValores != null && !colecaoContasValores.isEmpty()) {

			// la?o para somar os valores das contas no valor do d?bito do
			// im?vel
			for (ContaValoresHelper contaValorHelp : colecaoContasValores) {
				// recupera a conta
				Conta conta = contaValorHelp.getConta();

				// adiciona os valores da conta ao valor do d?bito do im?vel
				valorDebitoImovel = valorDebitoImovel.add(conta.getValorAgua());
				valorDebitoImovel = valorDebitoImovel.add(conta.getValorEsgoto());
				valorDebitoImovel = valorDebitoImovel.add(conta.getDebitos());
				valorDebitoImovel = valorDebitoImovel.subtract(conta.getValorCreditos());
				valorDebitoImovel = valorDebitoImovel.subtract(conta.getValorImposto());
			}

			// item 12
			// calcula a quantidade de itens em d?bito do im?vel
			quantidadeItensEmDebito = quantidadeItensEmDebito
					+ colecaoContasValores.size();
		}
		
		
		if(acaoCobranca ==null 
				|| acaoCobranca!=null && acaoCobranca.getId().compareTo(CobrancaAcao.CARTA_FINAL_DE_ANO)!=0){	
		
			// la?o para somar os valores das guias de pagamento no valor do d?bito
			// do im?vel
			if (colecaoGuiasPagamentoValores != null
					&& !colecaoGuiasPagamentoValores.isEmpty()) {
	
				for (GuiaPagamentoValoresHelper guiaPagamentoValoresHelper : colecaoGuiasPagamentoValores) {
	
					// recupera a guia de pagamento
					GuiaPagamento guiaPagamento = guiaPagamentoValoresHelper
							.getGuiaPagamento();
	
					// adiciona o valor do d?bito da guia ao valor do d?bito do
					// im?vel
					valorDebitoImovel = valorDebitoImovel.add(guiaPagamento
							.getValorDebito());
				}
	
				// item 12
				// calcula a quantidade de itens em d?bito do im?vel
				quantidadeItensEmDebito = quantidadeItensEmDebito
						+ colecaoGuiasPagamentoValores.size();
	
			}
	
			// la?o para somar os valores dos d?bitos a cobrar no valor do d?bito do
			// im?vel
			if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
	
				for (DebitoACobrar debitoACobrar : colecaoDebitoACobrar) {
	
	                // adiciona o valor do d?bito ao valor do d?bito do im?vel
				    valorDebitoImovel = valorDebitoImovel.add(debitoACobrar.getValorTotalComBonus());
				}
	
				// item 12
				// calcula a quantidade de itens em d?bito do im?vel
				quantidadeItensEmDebito = quantidadeItensEmDebito
						+ colecaoDebitoACobrar.size();
			}
			// fim item 11
			
		}
		
		if(acaoCobranca!= null && acaoCobranca.getId().compareTo(CobrancaAcao.CARTA_FINAL_DE_ANO)==0){	
			
			ResolucaoDiretoria resolucaoDiretoria = new ResolucaoDiretoria();
			resolucaoDiretoria.setId(14);
			
			//4. caso de lista de conta n?o esteja vazia
			if(debitoImovel.getColecaoContasValores() != null && !debitoImovel.getColecaoContasValores().isEmpty()
					&& debitoImovel.getColecaoContasValores().size() >= 2){
				
				IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
					
				indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(indicadorDebitoACobrar);
				indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(indicadorCreditoARalizar);
				indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(indicadorGuiaPagamento);
				indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(indicadorCalcularAcrescimoImpontualidade);
				indicadoresParcelamentoHelper.setIndicadorContasRevisao(indicadorConta);
				indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));
				
				BigDecimal[] valorDebitoTotalEDebitoComDesconto = this.obterValorDebitoTotalEDebitoComDesconto(
					imovel, cobrancaAcaoAtividadeComando, indicadoresParcelamentoHelper, debitoImovel,
					resolucaoDiretoria, new Integer("2"));
				valorDebitoImovel = valorDebitoTotalEDebitoComDesconto[1];
			}else{
				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.QTD_CONTAS_INVALIDA_PARA_PARCELAMENTO);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

				getControladorUtil().inserir(imovelNaoGerado);
				
				imovelNaoGerado = null;

				// indica que o im?vel n?o satisfaz o crit?rio de cobran?a
				return verificarCriterioCobrancaParaImovelHelper;
			}
					
		}
		//RM5901 - PE2011085901 - adicionado por Vivianne Sousa - 10/10/2011
		//[SB0006] Obter Desconto do Documento de Cobran?a
		BigDecimal valorDesconto =  new BigDecimal("0.00");
		if(acaoCobranca!= null && acaoCobranca.getIndicadorAceitaRDCriterio() != null 
			&& acaoCobranca.getIndicadorAceitaRDCriterio().equals(ConstantesSistema.SIM)
			&& cobrancaCriterio != null && cobrancaCriterio.getResolucaoDiretoria() != null
			&& cobrancaCriterio.getResolucaoDiretoria().getId() != null){
			
			ResolucaoDiretoria resolucaoDiretoria = cobrancaCriterio.getResolucaoDiretoria();
			IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
			indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(indicadorDebitoACobrar);
			indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(indicadorCreditoARalizar);
			indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(indicadorGuiaPagamento);
			indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(indicadorCalcularAcrescimoImpontualidade);
			indicadoresParcelamentoHelper.setIndicadorContasRevisao(indicadorConta);
			indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));
			
			ImovelSituacao imovelSituacao = this.obterSituacaoImovel(imovel.getLigacaoAguaSituacao().getId(),
					imovel.getLigacaoEsgotoSituacao().getId());
			
			ParcelamentoPerfil parcelamentoPerfil = this.obterPerfilParcelamentoPorNivel(imovel, imovelSituacao, 
					resolucaoDiretoria.getId(), imovel.getImovelPerfil().getId());
			
			if (parcelamentoPerfil != null){
				
				BigDecimal[] valorDebitoTotalEDebitoComDesconto = this.obterValorDebitoTotalEDebitoComDesconto(
						imovel, cobrancaAcaoAtividadeComando, indicadoresParcelamentoHelper, debitoImovel, 
						resolucaoDiretoria,new Integer("1"));
				
				BigDecimal valorDebitoTotalSemDesconto = valorDebitoTotalEDebitoComDesconto[0];
				valorDebitoImovel = valorDebitoTotalEDebitoComDesconto[1];
				valorDesconto = valorDebitoTotalSemDesconto.subtract(valorDebitoImovel);
			}
		}
		
		// item 12
		// calcula a quantidade de itens em d?bito do im?vel
		/*
		 * comentado por pedro alexandre dia 01/04/2006 quantidadeItensEmDebito =
		 * colecaoContasValores.size(); quantidadeItensEmDebito =
		 * colecaoGuiasPagamentoValores.size(); quantidadeItensEmDebito =
		 * colecaoDebitoACobrar.size();
		 */
		// fim item 12
		// cria uma flag para o indicador de cobran?a
		boolean flagIndicadorCriterioCobranca = false;

		// item 13
		// la?o para verificar se alguma linha do crit?rio de cobran?a satisfaz
		// o crit?rio de cobran?a do im?vel
		labelLinha: for (CobrancaCriterioLinha cobrancaCriterioLinha : colecaoCobrancaCriterioLinha) {
			// item 13.1
			// se o perfil do im?vel for igual ao da linha
			if (!imovel.getImovelPerfil().getId().equals(
					(cobrancaCriterioLinha.getImovelPerfil().getId()))) {
				// passa para a pr?xima linha do crit?rio de cobran?a
				continue labelLinha;
			}
			// fim item 13.1

			Integer existenciaImovelSubCategoria = null;
			// item 13.2
			// cria o filtro de im?vel sub-categoria
			existenciaImovelSubCategoria = getControladorImovel()
					.pesquisarExistenciaImovelSubCategoria(imovel.getId(),
							cobrancaCriterioLinha.getCategoria().getId());

			// se n?o existir nenhuma subcategoria
			if (existenciaImovelSubCategoria == null) {
				// passa para a pr?xima linha do crit?rio de cobran?a
				continue labelLinha;
			}
			// fim item 13.2

			// item 13.3
			// se o valor do d?bito do im?vel for menor que o valor minimo do
			// d?bito da linha de crit?rio de cobran?a
			// ou se o valor do d?bito do im?vel for maior que o valor m?ximo do
			// d?bito da linha de crit?rio de cobran?a
			if (valorDebitoImovel.doubleValue() < cobrancaCriterioLinha
					.getValorMinimoDebito().doubleValue()
					|| valorDebitoImovel.doubleValue() > cobrancaCriterioLinha
							.getValorMaximoDebito().doubleValue()) {

				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
					new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.VALOR_DEBITO_FORA_INTERVALO_DEFINIDO_CRITERIO);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
								
				getControladorUtil().inserir(imovelNaoGerado);
								
				// passa para a pr?xima linha do crit?rio de cobran?a
				continue labelLinha;
			}
			// fim item 13.3

			// item 13.4
			// se a quantidade de itens em d?bito do im?vel for menor que a
			// quantidade minima de contas da linha de crit?rio de cobran?a
			// ou se a quantidade de itens em d?bito do im?vel for maior que a
			// quantidade m?xima de contas da linha de crit?rio de cobran?a
			if (quantidadeItensEmDebito.intValue() < cobrancaCriterioLinha
					.getQuantidadeMinimaContas().intValue()
					|| quantidadeItensEmDebito.intValue() > cobrancaCriterioLinha
							.getQuantidadeMaximaContas().intValue()) {

				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
					new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.QTD_ITENS_FORA_INTERVALO_DEFINIDO_CRITERIO);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
				
				getControladorUtil().inserir(imovelNaoGerado);
				
				// passa para a pr?xima linha do crit?rio de cobran?a
				continue labelLinha;
			}
			// fim item 13.4

			// item 13.5
			// caso o im?vel esteja cadastrado como d?bito autom?tico
			if (imovel.getIndicadorDebitoConta() != null
					&& imovel.getIndicadorDebitoConta().shortValue() == 1) {

				// item 13.5.1
				// se o valor do d?bito do im?vel seja menor que o valor minimo
				// para d?bito autom?tico
				if (valorDebitoImovel.doubleValue() < cobrancaCriterioLinha
						.getValorMinimoDebitoDebitoAutomatico().doubleValue()) {
					
					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.VALOR_DEBITO_AUTOMATICO_MENOR_QUE_MINIMO);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						
					
					getControladorUtil().inserir(imovelNaoGerado);

					// passa para a pr?xima linha do crit?rio de cobran?a
					continue labelLinha;
				}
				// fim item 13.5.1

				// item 13.5.2
				// se a quantidade de itens em d?bito do im?vel for menor que a
				// quantidade minima de itens para d?bito autom?tico
				if (quantidadeItensEmDebito.intValue() < cobrancaCriterioLinha
						.getQuantidadeMinimaContasDebitoAutomatico()) {
					
					MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
						new MotivoNaoGeracaoDocCobranca();
					motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.QTD_ITENS_MENOR_MINIMA_PARA_DEBITO_AUTOMATICO);
					imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

					getControladorUtil().inserir(imovelNaoGerado);

					// passa para a pr?xima linha do crit?rio de cobran?a
					continue labelLinha;
				}
				// fim item 13.5.2
			}
			// fim item 13.5

			// item 13.6
			// caso o im?vel possua d?bito somente da conta do m?s
			if (colecaoContasValores != null && !colecaoContasValores.isEmpty()) {

				// --ALTERADO POR LEONARDO VIEIRA

				boolean flagContaMes = false;
				Conta conta = null;

				for (ContaValoresHelper contaValorHelper : colecaoContasValores) {

					// recupera a conta
					conta = contaValorHelper.getConta();

					// se a conta for a do m?s
					if (conta.getReferencia() == Util
							.subtrairData(sistemaParametros
									.getAnoMesFaturamento())) {
						flagContaMes = true;
					}

				}

				if (colecaoContasValores.size() == 1 && flagContaMes) {
					if (cobrancaCriterio
							.getIndicadorEmissaoInquilinoDebitoContaMes() == 1) {
						boolean usuarioNaoIquilino = getControladorCliente()
								.verificaUsuarioinquilino(
										Util
												.converterStringParaInteger(matriculaImovel));
						if (usuarioNaoIquilino) {
							// se o valor do d?bito do im?vel for menor que a
							// valor
							// m?nimo
							// para a conta do m?s
							if (valorDebitoImovel.doubleValue() < cobrancaCriterioLinha
									.getValorMinimoContaMes().doubleValue()) {
								
								MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
									new MotivoNaoGeracaoDocCobranca();
								motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.VALOR_DEBITO_MENOR_QUE_MINIMO_CASO_INQUILINO_CONTA_MES);
								imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);						

								getControladorUtil().inserir(imovelNaoGerado);
								
								// passa para a pr?xima linha do crit?rio de
								// cobran?a
								continue labelLinha;
							}
						}
					} else {
						// se o valor do d?bito do im?vel for menor que a
						// valor
						// m?nimo
						// para a conta do m?s
						if (valorDebitoImovel.doubleValue() < cobrancaCriterioLinha
								.getValorMinimoContaMes().doubleValue()) {
							
							MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
								new MotivoNaoGeracaoDocCobranca();
							motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.VALOR_DEBITO_MENOR_QUE_MINIMO_CASO_INQUILINO);
							imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);				
							
							getControladorUtil().inserir(imovelNaoGerado);
							
							// passa para a pr?xima linha do crit?rio de
							// cobran?a
							continue labelLinha;
						}
					}

				}
			}
			// fim item 13.6

			// item 13.7
			// Caso a quantidade de contas do im?vel com parcelamento seja maior
			// que a quantidade minima de contas com parcelamento para emiss?o
			// de a??o de cobran?a
			if (quantidadeContasParcelamento < cobrancaCriterioLinha
					.getQuantidadeMinimaContasParcelamento().intValue()) {
				
				MotivoNaoGeracaoDocCobranca motivoNaoGeracao = 
					new MotivoNaoGeracaoDocCobranca();
				motivoNaoGeracao.setId(MotivoNaoGeracaoDocCobranca.QTD_CONTAS_PARCELADAS_MENOR_QUE_MINIMA);
				imovelNaoGerado.setMotivoNaoGeracaoDocCobranca(motivoNaoGeracao);				

				getControladorUtil().inserir(imovelNaoGerado);

				// passa para a pr?xima linha do crit?rio de
				// cobran?a
				continue labelLinha;
			}

			// caso o im?vel satisfazer todos os crit?rios anteriores
			// seta a flag pra true (indica que o im?vel satisfaz o crit?rio de
			// cobran?a)
			flagIndicadorCriterioCobranca = true;

			// termina o la?o das linhas de crit?rio de cobran?a
			break labelLinha;
		}
		// fim item 13

		// item 14
		// se o im?vel satisfaz o crit?rio de cobran?a
		if (flagIndicadorCriterioCobranca) {
			// seta os dados no objeto que vai ser retornado pelo met?do
			verificarCriterioCobrancaParaImovelHelper
					.setFlagCriterioCobrancaImovel(true);
			verificarCriterioCobrancaParaImovelHelper
					.setQuantidadeItensEmDebito(quantidadeItensEmDebito);
			verificarCriterioCobrancaParaImovelHelper
					.setValorDebitoImovel(valorDebitoImovel);
			verificarCriterioCobrancaParaImovelHelper
					.setColecaoContasValores(colecaoContasValores);
			verificarCriterioCobrancaParaImovelHelper
					.setColecaoDebitoACobrar(colecaoDebitoACobrar);
			verificarCriterioCobrancaParaImovelHelper
					.setColecaoGuiasPagamentoValores(colecaoGuiasPagamentoValores);
			verificarCriterioCobrancaParaImovelHelper.setValorDesconto(valorDesconto);
		}
		imovelNaoGerado = null;

		// retorna o objeto com todas as informa??es necess?rias
		// para identificar se o im?vel satisfaz ou n?o o crit?rio de cobran?a
		return verificarCriterioCobrancaParaImovelHelper;
		// fim item 14
	}
	
	/**
	 * 
	 * Este caso de uso gera os avisos de cobrança dos documentos de cobrança
	 * 
	 * [UC0575] Emitir Aviso de Cobrança
	 * 
	 * Criado um emitirAvisoCobrancaFormatado específico para a CAER
	 * colocando no final do arquivo o número sequencia com 9 posições
	 * 
	 * @author Sávio Luiz, Raphael Rossiter, Bruno Barros
	 * @data 02/04/2007, 03/01/2007, 13/08/2014
	 * 
	 * 
	 * @param
	 * @return void
	 */
	public void emitirAvisoCobrancaFormatado(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			Date dataAtualPesquisa, CobrancaAcao acaoCobranca,
			CobrancaGrupo grupoCobranca, CobrancaCriterio cobrancaCriterio)
			throws ControladorException {

		boolean flagFimPesquisa = false;
		final int quantidadeCobrancaDocumento = 1000;
		int quantidadeCobrancaDocumentoInicio = 0;

		StringBuilder cobrancaDocumentoTxt = new StringBuilder();
		int sequencialImpressao = 0;

		Collection colecaoCobrancaDocumento = null;

		Integer idCronogramaAtividadeAcaoCobranca = null;
		Integer idComandoAtividadeAcaoCobranca = null;
		Integer idAcaoCobranca = null;
		if (cobrancaAcaoAtividadeCronograma != null
				&& cobrancaAcaoAtividadeCronograma.getId() != null) {
			idCronogramaAtividadeAcaoCobranca = cobrancaAcaoAtividadeCronograma
					.getId();
		}
		if (cobrancaAcaoAtividadeComando != null
				&& cobrancaAcaoAtividadeComando.getId() != null) {
			idComandoAtividadeAcaoCobranca = cobrancaAcaoAtividadeComando
					.getId();
		}
		if (acaoCobranca != null && acaoCobranca.getId() != null) {
			idAcaoCobranca = acaoCobranca.getId();
		}

		while (!flagFimPesquisa) {

			try {

				System.out.println("***************************************");
				System.out.println("ENTROU NO AVISO DE CORTE");
				System.out.println("***************************************");
				colecaoCobrancaDocumento = repositorioCobranca
						.pesquisarCobrancaDocumentoParaEmitirCAER(
								idCronogramaAtividadeAcaoCobranca,
								idComandoAtividadeAcaoCobranca,
								dataAtualPesquisa, idAcaoCobranca,
								quantidadeCobrancaDocumentoInicio);
				System.out.println("***************************************");
				System.out.println("QTD DE COBRANCA DOCUMENTO:"
						+ colecaoCobrancaDocumento.size());
				System.out.println("***************************************");
			} catch (ErroRepositorioException ex) {
				ex.printStackTrace();
				throw new ControladorException("erro.sistema", ex);
			}

			if (colecaoCobrancaDocumento != null
					&& !colecaoCobrancaDocumento.isEmpty()) {

				System.out.println("***************************************");
				System.out.println("QUANTIDADE COBRANÇA:"
						+ colecaoCobrancaDocumento.size());
				System.out.println("***************************************");

				if (colecaoCobrancaDocumento.size() < quantidadeCobrancaDocumento) {
					flagFimPesquisa = true;
				} else {
					quantidadeCobrancaDocumentoInicio = quantidadeCobrancaDocumentoInicio + 1000;
				}
				// ***********************************************************************
				// ****PARTE COMENTADA DA DIVISÃO PARA IMPRESSÃO DE DOCUMENTO
				// COBRANÇA****
				// ***********************************************************************

				// int metadeColecao = 0;
				// if (colecaoCobrancaDocumento.size() % 2 == 0) {
				// metadeColecao = colecaoCobrancaDocumento.size() / 2;
				// } else {
				// metadeColecao = (colecaoCobrancaDocumento.size() / 2) + 1;
				// }

				// Map<Integer, Map<Object, Object>>
				// mapCobrancaDocumentoOrdenada =
				// dividirColecao(colecaoCobrancaDocumento);

				/*
				 * if (mapCobrancaDocumentoOrdenada != null) { int countOrdem =
				 * 0;
				 * 
				 * while (countOrdem < mapCobrancaDocumentoOrdenada.size()) {
				 * Map<Object, Object> mapCobrancaoDocumentoDivididas =
				 * mapCobrancaDocumentoOrdenada .get(countOrdem);
				 */

				/*
				 * Iterator iteratorCobrancaDocumento =
				 * mapCobrancaoDocumentoDivididas .keySet().iterator();
				 */
				Iterator iteratorCobrancaDocumento = colecaoCobrancaDocumento
						.iterator();
				while (iteratorCobrancaDocumento.hasNext()) {

					CobrancaDocumento cobrancaDocumento = null;
					/*
					 * if(quantidadeContas == 48){ System.out.println(""); }
					 */

					// int situacao = 0;
					cobrancaDocumento = (CobrancaDocumento) iteratorCobrancaDocumento
							.next();

					String nomeClienteUsuario = null;
					Collection colecaoCobrancaDocumentoItemConta = null;
					Integer idClienteResponsavel = null;
					// Collection colecaoCobrancaDocumentoItemGuiaPagamento =
					// null;
					/*
					 * Estes objetos auxiliarão na formatação da inscrição que
					 * será composta por informações do documento de cobrança e
					 * do imóvel a ele associado
					 */

					/*
					 * Objeto que será utilizado para armazenar as informações
					 * do documento de cobran?a de acordo com o layout definido
					 * no caso de uso
					 */

					/*
					 * while (situacao < 2) { if (situacao == 0) { situacao = 1;
					 * sequencialImpressao = atualizaSequencial(
					 * sequencialImpressao, situacao, metadeColecao); } else {
					 * cobrancaDocumento = (CobrancaDocumento)
					 * mapCobrancaoDocumentoDivididas .get(cobrancaDocumento);
					 * situacao = 2; sequencialImpressao = atualizaSequencial(
					 * sequencialImpressao, situacao, metadeColecao); }
					 */

					if (cobrancaDocumento != null) {
						sequencialImpressao++;

						try {

							nomeClienteUsuario = this.repositorioClienteImovel
									.pesquisarNomeClientePorImovel(cobrancaDocumento
											.getImovel().getId());
							idClienteResponsavel = this.repositorioClienteImovel
									.retornaIdClienteResponsavelIndicadorEnvioConta(cobrancaDocumento
											.getImovel().getId());

							colecaoCobrancaDocumentoItemConta = this.repositorioCobranca
									.selecionarCobrancaDocumentoItemReferenteConta(cobrancaDocumento);

						} catch (ErroRepositorioException ex) {
							ex.printStackTrace();
							throw new ControladorException("erro.sistema", ex);
						}

						if (colecaoCobrancaDocumentoItemConta != null
								&& !colecaoCobrancaDocumentoItemConta.isEmpty()) {

							// ITEM 1
							// sequencial do documento de cobranca
							cobrancaDocumentoTxt
									.append(Util
											.retornaSequencialFormatado( 0 ) );
							// ITEM 2
							// Formatar sequencial de documento gerado
							cobrancaDocumentoTxt
									.append(Util
											.retornaSequencialFormatado(sequencialImpressao));
							// ITEM 3
							// id do grupo
							if (idCronogramaAtividadeAcaoCobranca != null) {
								cobrancaDocumentoTxt.append(Util
										.adicionarZerosEsquedaNumero(2, ""
												+ grupoCobranca.getId()));
							} else {
								cobrancaDocumentoTxt.append(Util
										.adicionarZerosEsquedaNumero(2, ""
												+ cobrancaDocumento.getImovel()
														.getQuadra().getRota()
														.getCobrancaGrupo()
														.getId()));
							}
							// Codigo Rota
							cobrancaDocumentoTxt.append(Util
									.adicionarZerosEsquedaNumero(6, ""
											+ cobrancaDocumento.getImovel()
													.getQuadra().getRota()
													.getCodigo()));

							// ITEM 4
							// Codigo Rota
							cobrancaDocumentoTxt
									.append(Util
											.adicionarZerosEsquedaNumero(
													4,
													""
															+ cobrancaDocumento
																	.getImovel()
																	.getNumeroSequencialRota()));

							// ITEM 5
							// código da firma
							if (cobrancaDocumento.getEmpresa() != null) {
								cobrancaDocumentoTxt.append(Util
										.adicionarZerosEsquedaNumero(2,
												cobrancaDocumento.getEmpresa()
														.getId().toString()));
							}

							// ITEM 6
							if (cobrancaDocumento.getEmpresa() != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(cobrancaDocumento
												.getEmpresa()
												.getDescricaoAbreviada(), 10));
							}

							// ITEM 7
							// Matrícula do imóvel
							cobrancaDocumentoTxt
									.append(Util
											.adicionarZerosEsquedaNumero(
													9,
													Util
															.retornaMatriculaImovelFormatada(cobrancaDocumento
																	.getImovel()
																	.getId())));
							// ITEM 8
							// Inscrição
							String idLocalidade = Util
									.adicionarZerosEsquedaNumero(3, ""
											+ cobrancaDocumento.getLocalidade()
													.getId());
							String codigoSetorComercial = Util
									.adicionarZerosEsquedaNumero(3, ""
											+ cobrancaDocumento
													.getCodigoSetorComercial());
							String numeroQuadra = Util
									.adicionarZerosEsquedaNumero(3, ""
											+ cobrancaDocumento
													.getNumeroQuadra());
							String lote = Util.adicionarZerosEsquedaNumero(4,
									""
											+ cobrancaDocumento.getImovel()
													.getLote());
							String subLote = Util.adicionarZerosEsquedaNumero(
									3, ""
											+ cobrancaDocumento.getImovel()
													.getSubLote());

							cobrancaDocumentoTxt.append(Util.completaString(
									idLocalidade + "." + codigoSetorComercial
											+ "." + numeroQuadra + "." + lote
											+ "." + subLote, 20));

							// ITEM 9,10
							String enderecoImovel = "";
							String nomeBairro = "";
							String nomeMunicipio = "";
							String siglaUnidadeFederecao = "";
							String cepFormatado = "";

							String[] parmsEnderecoImovel = getControladorEndereco()
									.pesquisarEnderecoFormatadoDividido(
											cobrancaDocumento.getImovel()
													.getId());
							if (parmsEnderecoImovel != null) {
								// endereço sem municipio e unidade federação
								cobrancaDocumentoTxt.append(Util
										.completaString(parmsEnderecoImovel[0],
												100));
								enderecoImovel = parmsEnderecoImovel[0];
								// nome do bairro
								nomeBairro = "" + parmsEnderecoImovel[3];
								// nome do municipio
								nomeMunicipio = "" + parmsEnderecoImovel[1];
								// sigla da unidade federação
								siglaUnidadeFederecao = parmsEnderecoImovel[2];
								cepFormatado = parmsEnderecoImovel[4];
							}

							// nome Bairro
							cobrancaDocumentoTxt.append(Util.completaString(
									nomeBairro, 30));
							// nome municipio
							cobrancaDocumentoTxt.append(Util.completaString(
									nomeMunicipio, 30));
							// sigla unidade federacao
							cobrancaDocumentoTxt.append(Util.completaString(
									siglaUnidadeFederecao, 2));

							if (cepFormatado != null) {
								cepFormatado = Util
										.adicionarZerosEsquedaNumero(8,
												cepFormatado);

								cobrancaDocumentoTxt.append(cepFormatado
										.substring(0, 5)
										+ "-" + cepFormatado.substring(5, 8));
							}

							// ITEM 11,12
							// endereço do cliente com opção de recebimento via
							// correio
							// ITEM 9,10
							String nomeBairroResponsavel = "";
							String nomeMunicipioResponsavel = "";
							String siglaUnidadeFederecaoResponsavel = "";
							String cepFormatadoResponsavel = "";
							if (idClienteResponsavel != null) {
								String[] parmsEndereco = getControladorEndereco()
										.pesquisarEnderecoClienteAbreviadoDividido(
												idClienteResponsavel);
								// endereço sem municipio e unidade federação
								cobrancaDocumentoTxt.append(Util
										.completaString(parmsEndereco[0], 100));
								// nome do bairro
								nomeBairroResponsavel = "" + parmsEndereco[3];
								// nome do municipio
								nomeMunicipioResponsavel = ""
										+ parmsEndereco[1];
								// sigla da unidade federação
								siglaUnidadeFederecaoResponsavel = parmsEndereco[2];
								cepFormatadoResponsavel = parmsEndereco[4];

								// nome Bairro
								cobrancaDocumentoTxt.append(Util
										.completaString(nomeBairroResponsavel,
												30));
								// nome municipio
								cobrancaDocumentoTxt.append(Util
										.completaString(
												nomeMunicipioResponsavel, 30));
								// sigla unidade federacao
								cobrancaDocumentoTxt
										.append(Util
												.completaString(
														siglaUnidadeFederecaoResponsavel,
														2));

								if (cepFormatadoResponsavel != null) {
									cepFormatadoResponsavel = Util
											.adicionarZerosEsquedaNumero(8,
													cepFormatadoResponsavel);

									cobrancaDocumentoTxt
											.append(cepFormatadoResponsavel
													.substring(0, 5)
													+ "-"
													+ cepFormatado.substring(5,
															8));
								}

							} else {
								// endereço sem municipio e unidade federação
								cobrancaDocumentoTxt.append(Util
										.completaString(enderecoImovel, 100));

								// nome Bairro
								cobrancaDocumentoTxt.append(Util
										.completaString(nomeBairro, 30));
								// nome municipio
								cobrancaDocumentoTxt.append(Util
										.completaString(nomeMunicipio, 30));
								// sigla unidade federacao
								cobrancaDocumentoTxt.append(Util
										.completaString(siglaUnidadeFederecao,
												2));

								if (cepFormatado != null) {
									cepFormatado = Util
											.adicionarZerosEsquedaNumero(8,
													cepFormatado);

									cobrancaDocumentoTxt.append(cepFormatado
											.substring(0, 5)
											+ "-"
											+ cepFormatado.substring(5, 8));
								}

							}

							// ITEM 13
							// nome cliente
							cobrancaDocumentoTxt.append(Util.completaString(
									nomeClienteUsuario, 50));

							// ITEM 14
							// Quant. contas em debito
							cobrancaDocumentoTxt.append(Util
									.adicionarZerosEsquedaNumero(3, ""
											+ colecaoCobrancaDocumentoItemConta
													.size()));

							// ITEM 15,18
							// Indicador Estouro
							// cobrancaDocumentoTxt.append(Util.completaString(""
							// + indicadorEstouro, 1));
							// em caso de ser carta de tarifa social não
							// formatar o txt

							int quantidadesContas = 12;

							// retorna o indicador de estouro e formata o
							// cobrancaDocumentoTxt com os dados
							Object[] dadosValores = formatarCobrancaDocumentoItemParaContaComFormatacao(
									cobrancaDocumentoTxt,
									colecaoCobrancaDocumentoItemConta,
									quantidadesContas, idAcaoCobranca);

							BigDecimal valorItemCobrado = (BigDecimal) dadosValores[0];
							BigDecimal valorAcrescimos = (BigDecimal) dadosValores[1];
							BigDecimal valorItemAcrescimos = (BigDecimal) dadosValores[2];

							// somatorio do valor do item da conta
							cobrancaDocumentoTxt.append(Util.completaString(
									Util.formataBigDecimal(valorItemCobrado, 2,
											true), 14));
							// somatorio do valor dos encargos
							cobrancaDocumentoTxt.append(Util.completaString(
									Util.formataBigDecimal(valorAcrescimos, 2,
											true), 14));
							// somatorio do valor total das contas
							cobrancaDocumentoTxt.append(Util.completaString(
									Util.formataBigDecimal(valorItemAcrescimos,
											2, true), 14));

							// String
							// quantidadeItensDocumentoGuiaPagamentoString =
							// null;
							// // em caso de ser carta de tarifa social não
							// // formatar o txt
							// if (idAcaoCobranca != null
							// && (!idAcaoCobranca
							// .equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_LIGADO)
							// && !idAcaoCobranca
							// .equals(CobrancaAcao.CARTA_TARIFA_SOCIAL_CORTADO)))
							// {
							// // retorna o quantidade de documento item com
							// // guia
							// // pagamento e formata o cobran?aDocumentoTxt
							// // com os
							// // dados
							// int quantidadeItensDocumentoGuiaPagamento =
							// somatorioValoresAcrescimosDocumentoItem(
							// cobrancaDocumentoTxt,
							// colecaoCobrancaDocumentoItemGuiaPagamento);
							// quantidadeItensDocumentoGuiaPagamentoString = ""
							// + quantidadeItensDocumentoGuiaPagamento;
							// }

							// ITEM 19
							// em caso de ser carta de tarifa social n?o
							// formatar o txt
							// Sigla da regional
							cobrancaDocumentoTxt.append(Util.completaString(""
									+ cobrancaDocumento.getImovel()
											.getLocalidade()
											.getGerenciaRegional()
											.getNomeAbreviado(), 3));

							// ITEM 20
							// Nome da Localidade
							cobrancaDocumentoTxt.append(Util
									.completaString(""
											+ cobrancaDocumento.getImovel()
													.getLocalidade()
													.getDescricao(), 25));
							// em caso de ser carta de tarifa social n?o
							// formatar o txt

							// ITEM 21
							cobrancaDocumentoTxt.append(Util
									.formatarData(cobrancaDocumento
											.getEmissao()));

							// data de vencimento AAAAMMDD
							// Object[] dadosFaturamentoGrupo =
							// getControladorFaturamento()
							// .pesquisarAnoMesEDiaVencimentoFaturamentoGrupo(
							// cobrancaDocumento.getImovel()
							// .getId());
							// Integer anoMesFaturamento = null;
							// Integer diaVencimento = null;
							// if (dadosFaturamentoGrupo != null) {
							// if (dadosFaturamentoGrupo[0] != null) {
							// anoMesFaturamento = (Integer)
							// dadosFaturamentoGrupo[0];
							// }
							// if (dadosFaturamentoGrupo[1] != null) {
							// diaVencimento = ((Short)
							// dadosFaturamentoGrupo[1])
							// .intValue();
							// }
							// }

							// ITEM 22
							String dataVencimento = "";
							if (cobrancaDocumento.getEmissao() != null
									&& acaoCobranca.getNumeroDiasValidade() != null) {
								dataVencimento = Util
										.formatarData(Util
												.adicionarNumeroDiasDeUmaData(
														cobrancaDocumento
																.getEmissao(),
														acaoCobranca
																.getNumeroDiasVencimento()));
							}

							cobrancaDocumentoTxt.append(Util
									.completaStringComEspacoAEsquerda(
											dataVencimento, 10));

							// ITEM 23
							if (cobrancaDocumento.getImovel() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoAgua() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoAgua()
											.getHidrometroInstalacaoHistorico() != null) {
								// numero do hidometro
								if (cobrancaDocumento.getImovel()
										.getLigacaoAgua()
										.getHidrometroInstalacaoHistorico() != null) {
									cobrancaDocumentoTxt
											.append(Util
													.completaString(
															""
																	+ cobrancaDocumento
																			.getImovel()
																			.getLigacaoAgua()
																			.getHidrometroInstalacaoHistorico()
																			.getHidrometro()
																			.getNumero(),
															10));

									// Local de instala??o descricao abreviada
									cobrancaDocumentoTxt
											.append(Util
													.completaString(
															""
																	+ cobrancaDocumento
																			.getImovel()
																			.getLigacaoAgua()
																			.getHidrometroInstalacaoHistorico()
																			.getHidrometroLocalInstalacao()
																			.getDescricaoAbreviada(),
															5));
								} else {
									cobrancaDocumentoTxt.append(Util
											.completaString("", 10));
									cobrancaDocumentoTxt.append(Util
											.completaString("", 5));
								}

							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 10));
								cobrancaDocumentoTxt.append(Util
										.completaString("", 5));
							}

							// ITEM 24,25,26
							String representacaoNumericaCodBarra = "";

							// Obt?m a representa??o num?rica do
							// c?digode
							// barra
							representacaoNumericaCodBarra = this
									.getControladorArrecadacao()
									.obterRepresentacaoNumericaCodigoBarra(
											5,
											cobrancaDocumento
													.getValorDocumento(),
											cobrancaDocumento.getLocalidade()
													.getId(),
											cobrancaDocumento.getImovel()
													.getId(),
											null,
											null,
											null,
											null,
											String
													.valueOf(cobrancaDocumento
															.getNumeroSequenciaDocumento()),
											cobrancaDocumento
													.getDocumentoTipo().getId(),
											null, null, null);

							// Formata a representa??o n?merica do
							// c?digo de
							// barras
							String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
									.substring(0, 11)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											11, 12)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											12, 23)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											23, 24)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											24, 35)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											35, 36)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											36, 47)
									+ " "
									+ representacaoNumericaCodBarra.substring(
											47, 48);

							cobrancaDocumentoTxt
									.append(representacaoNumericaCodBarraFormatada);

							// Cria o objeto para gerar o c?digo de
							// barras
							// no
							// padr?o
							// intercalado 2 de 5
							Interleaved2of5 codigoBarraIntercalado2de5 = new Interleaved2of5();

							// Recupera a representa??o n?merica do
							// c?digo
							// de
							// barras
							// sem
							// os d?gitos verificadores
							String representacaoCodigoBarrasSemDigitoVerificador = representacaoNumericaCodBarra
									.substring(0, 11)
									+ representacaoNumericaCodBarra.substring(
											12, 23)
									+ representacaoNumericaCodBarra.substring(
											24, 35)
									+ representacaoNumericaCodBarra.substring(
											36, 47);

							cobrancaDocumentoTxt
									.append(codigoBarraIntercalado2de5
											.encodeValue(representacaoCodigoBarrasSemDigitoVerificador));

							Object[] dadosOS = pesquisarDadosOrdemServicoDocumentoCobranca(cobrancaDocumento
									.getId());
							if (dadosOS != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString("" + dadosOS[0], 9));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 9));
							}
							// situa??o liga??o de agua
							if (cobrancaDocumento.getImovel() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoAguaSituacao() != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(cobrancaDocumento
												.getImovel()
												.getLigacaoAguaSituacao()
												.getDescricao(), 20));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 20));
							}

							// situa??o liga??o de esgoto
							if (cobrancaDocumento.getImovel() != null
									&& cobrancaDocumento.getImovel()
											.getLigacaoEsgotoSituacao() != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(cobrancaDocumento
												.getImovel()
												.getLigacaoEsgotoSituacao()
												.getDescricao(), 20));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 20));
							}

							Categoria categoria = getControladorImovel()
									.obterPrincipalCategoriaImovel(
											cobrancaDocumento.getImovel()
													.getId());
							if (categoria != null) {
								cobrancaDocumentoTxt.append(Util
										.completaString(categoria
												.getDescricao(), 15));
							} else {
								cobrancaDocumentoTxt.append(Util
										.completaString("", 15));
							}
							
							
							/*
							 * COLOCADO POR RAPHAEL ROSSITER EM 03/01/2007 =============================================
							 * -----------------------------------------------------------------------------------------
							 */ 
							
							//ITEM 31 - Consumo M?dio
							Integer consumoMedio = getControladorMicromedicao().pesquisarConsumoMedioImovel(
							cobrancaDocumento.getImovel().getId());

							if (consumoMedio != null) {
								cobrancaDocumentoTxt.append(Util
								.completaString("" + consumoMedio, 10));
							} else {
								cobrancaDocumentoTxt.append(Util
								.completaString("", 10));
							}

							
							//ITEM 32 - Consumo Fixo
							Integer consumoMinimoEsgoto = getControladorLigacaoEsgoto().recuperarConsumoMinimoEsgoto(
							cobrancaDocumento.getImovel().getId());

							if (consumoMinimoEsgoto != null) {
								cobrancaDocumentoTxt.append(Util
								.completaString("" + consumoMinimoEsgoto, 10));
							} else {
								cobrancaDocumentoTxt.append(Util
								.completaString("", 10));
							}
							
							
							// Categoria(s) e Economia(s)
							Collection colecaoCategorias = getControladorImovel()
							.obterQuantidadeEconomiasCategoria(cobrancaDocumento.getImovel());
							
							String qtdResidencial = "";
							String qtdComercial = "";
							String qtdIndustrial = "";
							String qtdPublico = "";

							Integer totalCategoria = 0;

							if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
								
								Iterator iteratorColecaoCategorias = colecaoCategorias
								.iterator();
								
								while (iteratorColecaoCategorias.hasNext()) {
									
									categoria = (Categoria) iteratorColecaoCategorias.next();

									if (categoria.getId().equals(Categoria.RESIDENCIAL)) {
										
										qtdResidencial = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
										
									} else if (categoria.getId().equals(Categoria.COMERCIAL)) {
										
										qtdComercial = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
										
									} else if (categoria.getId().equals(Categoria.INDUSTRIAL)) {
										
										qtdIndustrial = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
									
									} else if (categoria.getId().equals(Categoria.PUBLICO)) {
										
										qtdPublico = "" + categoria
										.getQuantidadeEconomiasCategoria();
										
										totalCategoria = totalCategoria + categoria
										.getQuantidadeEconomiasCategoria();
									}
								}
							}
							
							//ITEM 33 - Resid?ncial
							if (!qtdResidencial.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdResidencial));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}

							
							//ITEM 34 - Comercial
							if (!qtdComercial.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdComercial));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}
							
							
							//ITEM 35 - Industrial
							if (!qtdIndustrial.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdIndustrial));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}
							
							
							//ITEM 36 - P?blico
							if (!qtdPublico.equals("")) {
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(3,
								qtdPublico));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 3));
							}
							
							
							//ITEM 37 - Soma Total das economias
							if (totalCategoria != null && !totalCategoria.equals("")) {
								
								cobrancaDocumentoTxt.append(Util.adicionarZerosEsquedaNumero(4, ""
								+ totalCategoria));
							} else {
								cobrancaDocumentoTxt.append(Util.completaStringComEspacoAEsquerda("", 4));
							}
							
							
							//ITEM 38 - Data da Posi??o do D?bito
							SistemaParametro sistemaParametro = this.getControladorUtil()
							.pesquisarParametrosDoSistema();
							
							String anoMesValidade = sistemaParametro.getAnoMesArrecadacao().toString();
					
							Calendar calendario = new GregorianCalendar();

							if (anoMesValidade != null && !anoMesValidade.equals("")) {
						
								calendario.set(Calendar.YEAR, new Integer(
								anoMesValidade.substring(0, 4)).intValue());
						
								calendario.set(Calendar.MONTH, new Integer(
								anoMesValidade.substring(4, 6)).intValue() - 1);
						
								calendario.set(Calendar.DAY_OF_MONTH,
								calendario.getActualMaximum(Calendar.DAY_OF_MONTH));

								cobrancaDocumentoTxt.append(Util
								.formatarData(calendario.getTime()));
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 10));
							}
							
							
							/*
							 * As datas de corte e supressão serão repassadas de acordo com
							 * a situação da ligação do im?vel.
							 * 
							 * ITEM 39 - Data do Corte
							 * ITEM 40 - Data da Supressão
							 */
							if (cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
								.equals(LigacaoAguaSituacao.CORTADO) ||
								cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
								.equals(LigacaoAguaSituacao.SUPRIMIDO)){
							
								/*
								 * Dados da Liga??o de ?gua(a partir da tabela LIGACAO_AGUA
								 * lagu_id=imov_id da tabela IMOVEL)
								 */
								Object[] dadosLigacaoAgua = getControladorAtendimentoPublico()
								.pesquisarDadosLigacaoAgua(cobrancaDocumento.getImovel().getId());
								
								if (dadosLigacaoAgua != null) {
									
									//Data do Corte
									if (cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
										.equals(LigacaoAguaSituacao.CORTADO)){
										
										if (dadosLigacaoAgua[3] != null) {
											
											cobrancaDocumentoTxt.append(Util.completaString(
											Util.formatarData((Date) dadosLigacaoAgua[3]),10));
											
											cobrancaDocumentoTxt.append(Util.completaString("", 10));
										} 
										else {
											cobrancaDocumentoTxt.append(Util.completaString("", 20));
										}
									}
									
									//Data da Supressão
									else if (cobrancaDocumento.getImovel().getLigacaoAguaSituacao().getId()
											.equals(LigacaoAguaSituacao.SUPRIMIDO)){
										

										if (dadosLigacaoAgua[4] != null) {
									
											cobrancaDocumentoTxt.append(Util.completaString("", 10));
											
											cobrancaDocumentoTxt.append(Util.completaString(
											Util.formatarData((Date) dadosLigacaoAgua[4]),10));
											
										} 
										else {
											cobrancaDocumentoTxt.append(Util.completaString("", 20));
										}
									}
									else{
										
										cobrancaDocumentoTxt.append(Util.completaString("", 20));
									}
									
								} else {
									cobrancaDocumentoTxt.append(Util.completaString("", 20));
								}
							}
							else{
								cobrancaDocumentoTxt.append(Util.completaString("", 20));
							}
							
							//ITEM 41 - Origem
							LeituraAnormalidade leituraAnormalidade = cobrancaDocumento.getImovel()
							.getLeituraAnormalidade();
							
							if (leituraAnormalidade == null) {
								cobrancaDocumentoTxt.append("AUTOMATICO");
								cobrancaDocumentoTxt.append(Util.completaString("", 5));
							} 
							else if (leituraAnormalidade.getId().equals(LeituraAnormalidade.INDICADOR_LIGADO_CLANDESTINO_AGUA) 
									|| leituraAnormalidade.getId().equals(LeituraAnormalidade.INDICADOR_LIGADO_CLANDESTINO_ESGOTO)
									|| leituraAnormalidade.getId().equals(LeituraAnormalidade.INDICADOR_LIGADO_CLANDESTINO_AGUA_ESGOTO)) {
								
								cobrancaDocumentoTxt.append("RECADASTRAMENTO");
							} 
							else {
								cobrancaDocumentoTxt.append("LEITURA");
								cobrancaDocumentoTxt.append(Util.completaString("", 8));
							}

							//ITEM 42 - Ocorr?ncia
							if (leituraAnormalidade != null){
								
								cobrancaDocumentoTxt.append(Util.completaString(
								leituraAnormalidade.getDescricao(), 34));
							}
							else{
								
								cobrancaDocumentoTxt.append(Util.completaString("", 34));
							}
							
							//ITEM 43 - Data ?ltima Altera??o
							if (cobrancaDocumento.getImovel().getUltimaAlteracao() != null) {
								
								cobrancaDocumentoTxt.append(Util.formatarData(
								cobrancaDocumento.getImovel().getUltimaAlteracao()));
								
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 10));
							}
							
							//ITEM 44 - Ordem de Servi?o
							Integer idOrdemServico = this.getControladorOrdemServico()
							.pesquisarOrdemServicoPorCobrancaDocumento(cobrancaDocumento.getId());
							
							if (idOrdemServico != null){
								
								cobrancaDocumentoTxt.append(Util.completaString(
								idOrdemServico.toString(), 15));
							}
							else{
								cobrancaDocumentoTxt.append(Util.completaString("", 15));
							}
							
							//ITEM 45 - Tipo de Consumidor (ImovelPerfil da tabela CobrancaDocumento)
							if (cobrancaDocumento.getImovelPerfil() != null) {
								
								cobrancaDocumentoTxt.append(Util.completaString(
								cobrancaDocumento.getImovelPerfil().getDescricao() , 20));
								
							} else {
								cobrancaDocumentoTxt.append(Util.completaString("", 20));
							}
							
							// ITEM 46
							cobrancaDocumentoTxt
							.append(Util
									.retornaSequencialFormatado(cobrancaDocumento
											.getNumeroSequenciaDocumento(), 9));
							
							//ITEM 46 - Hidr?metro
							/*Collection dadosHidrometro = null;
							
							try {

								dadosHidrometro = this.repositorioMicromedicao
								.pesquisarDadosHidrometroTipoLigacaoAgua(cobrancaDocumento.getImovel().getId());
								
							} catch (ErroRepositorioException ex) {
								ex.printStackTrace();
								throw new ControladorException("erro.sistema", ex);
							}
							
							if (dadosHidrometro != null && !dadosHidrometro.isEmpty()){
								
								Object[] objetoDados = (Object[]) Util.retonarObjetoDeColecao(dadosHidrometro);
								String numeroHidrometro = String.valueOf(objetoDados[1]);
								
								if (numeroHidrometro != null && !numeroHidrometro.equalsIgnoreCase("")){
									
									cobrancaDocumentoTxt.append(Util.completaString(numeroHidrometro, 10));
								}
								else{
									
									cobrancaDocumentoTxt.append(Util.completaString("", 10));
								}
							}
							else{
								
								cobrancaDocumentoTxt.append(Util.completaString("", 10));
							}*/
				
							//==========================================================================================

							
							cobrancaDocumentoTxt.append(System
									.getProperty("line.separator"));

						}

						colecaoCobrancaDocumentoItemConta = null;
					}

					// }// fim do la?o que verifica
					// as 2
					// contas

				}// fim la?o while do iterator do
				// objeto
				// helper
				// countOrdem++;
				// mapCobrancaoDocumentoDivididas = null;
				// // }
			} else {
				flagFimPesquisa = true;
			}
			// } else {
			// flagFimPesquisa = true;
			// }
			// colecaoCobrancaDocumento = null;
		}

		Date dataAtual = new Date();

		String nomeZip = null;

		System.out.println("ID AÇÃO COBRANÇA:" + idAcaoCobranca);

		if (idCronogramaAtividadeAcaoCobranca != null) {
			nomeZip = "EMITIR_" + acaoCobranca.getDescricaoCobrancaAcao()
					+ "_GRUPO_" + grupoCobranca.getId() + "_"
					+ Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);

			// Substitui qualquer caracter especial por "underline"
			nomeZip = nomeZip.replaceAll("\\W", "_");
		} else {
			String descricaoAbrevDocumentoTipo = "";
			if (acaoCobranca != null && acaoCobranca.getDocumentoTipo() != null) {
				descricaoAbrevDocumentoTipo = acaoCobranca.getDocumentoTipo()
						.getDescricaoAbreviado();
			}
			String tituloComandoEventual = cobrancaAcaoAtividadeComando
					.getDescricaoTitulo();

			nomeZip = descricaoAbrevDocumentoTipo + " " + tituloComandoEventual
					+ " " + Util.formatarData(dataAtual) + Util.formatarHoraSemDataSemDoisPontos(dataAtual);
		}
		// nomeZip = nomeZip.replace("/", "_");
		// nomeZip = nomeZip.replace(" ", "_");
		
		// Substitui qualquer caracter especial por "underline"
		nomeZip = nomeZip.replaceAll("\\W", "_");

		// pegar o arquivo, zipar pasta e arquivo e escrever no stream
		try {

			System.out.println("***************************************");
			System.out.println("INICO DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

			if (cobrancaDocumentoTxt != null
					&& cobrancaDocumentoTxt.length() != 0) {

				// criar o arquivo zip
				File compactado = new File(nomeZip + ".zip"); // nomeZip
				ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
						compactado));

				File leitura = new File(nomeZip + ".txt");
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(leitura.getAbsolutePath())));
				out.write(cobrancaDocumentoTxt.toString());
				out.close();
				ZipUtil.adicionarArquivo(zos, leitura);

				// close the stream
				zos.close();
				leitura.delete();
			}
			System.out.println("***************************************");
			System.out.println("FIM DA CRIACAO DO ARQUIVO");
			System.out.println("***************************************");

		} catch (IOException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		} catch (Exception e) {
			e.printStackTrace();

			throw new ControladorException("erro.sistema", e);
		}

	}

}
