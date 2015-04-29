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
package gsan.relatorio.arrecadacao;

import gsan.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gsan.arrecadacao.bean.PagamentoRelatorioHelper;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.arrecadacao.pagamento.PagamentoSituacao;
import gsan.batch.Relatorio;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.DocumentoTipo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de bairro manter de �gua
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioPagamento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAgua
	 */
	public RelatorioPagamento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_PAGAMENTO);
	}

	@Deprecated
	public RelatorioPagamento() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoPagamentos = new ArrayList();
		Collection colecaoPagamentosClienteHistorico = new ArrayList();
		Collection colecaoPagamentosHistoricoClienteGuiaPagamento = new ArrayList();
		boolean relatorioConsultarImovel = false;
		boolean relatorioConsultarAvisoBancario = false;
		if (getParametro("relatorioConsultarImovel") != null) {
			relatorioConsultarImovel = true;
		}

		if (getParametro("relatorioConsultarAvisoBancario") != null) {
			relatorioConsultarAvisoBancario = true;
		}

		Pagamento pagamentoParametrosInicial = (Pagamento) getParametro("pagamentoParametrosInicial");
		Pagamento pagamentoParametrosFinal = (Pagamento) getParametro("pagamentoParametrosFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// Par�metros de Pesquisa
		AvisoBancarioHelper avisoBancarioHelper = (AvisoBancarioHelper) getParametro("avisoBancarioHelper");
		String idImovel = (String) getParametro("idImovel");
		String idCliente = (String) getParametro("idCliente");
		String clienteRelacaoTipo = (String) getParametro("clienteRelacaoTipo");
		String idAvisoBancario = (String) getParametro("idAvisoBancario");
		String idMovimentoArrecadador = (String) getParametro("idMovimentoArrecadador");
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		String periodoArrecadacaoInicial = (String) getParametro("periodoArrecadacaoInicial");
		String periodoArrecadacaoFinal = (String) getParametro("periodoArrecadacaoFinal");
		String periodoPagamentoInicial = (String) getParametro("periodoPagamentoInicial");
		String periodoPagamentoFinal = (String) getParametro("periodoPagamentoFinal");
		Date dataPagamentoInicial = (Date) getParametro("dataPagamentoInicial");
		Date dataPagamentoFinal = (Date) getParametro("dataPagamentoFinal");
		String[] idsPagamentoSituacao = (String[]) getParametro("idsPagamentoSituacao");
		String[] idsArrecadacaoForma = (String[]) getParametro("idsArrecadacaoForma");
		String[] idsDocumentoTipo = (String[]) getParametro("idsDocumentoTipo");
		String[] idsDebitoTipo = (String[]) getParametro("idsDebitoTipo");
		String opcaoPagamento = (String) getParametro("opcaoPagamento");
        
        String valorPagamentoInicial = (String) getParametro("valorPagamentoInicial");
        String valorPagamentoFinal = (String) getParametro("valorPagamentoFinal");
        
        Cliente clie = null;
        if(Util.verificarIdNaoVazio(idImovel)){
        	clie = fachada.pesquisarClienteUsuarioImovel(Integer.parseInt(idImovel));
        }
        

		if (relatorioConsultarAvisoBancario) {
			colecaoPagamentos = avisoBancarioHelper.getColecaoPagamentos();
		} else {

			if (idImovel != null && !idImovel.equals("")) {
				if (opcaoPagamento != null && opcaoPagamento.equals("ambos")) {
					colecaoPagamentos = fachada
							.pesquisarPagamentoImovelAmbosRelatorio(idImovel);
				} else {
					colecaoPagamentos = fachada
							.pesquisarPagamentoImovelRelatorio(idImovel,
									idCliente, clienteRelacaoTipo,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idMovimentoArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicial,
									periodoPagamentoFinal,
									dataPagamentoInicial, dataPagamentoFinal,
									idsPagamentoSituacao, idsDebitoTipo,
									idsArrecadacaoForma, idsDocumentoTipo,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);
				}
			} else if (idCliente != null && !idCliente.equals("")) {
				
				if (opcaoPagamento != null && opcaoPagamento.equals("atual") 
						|| (opcaoPagamento.equals("ambos"))) {
					
					colecaoPagamentos = fachada.pesquisarPagamentoClienteRelatorio(
							idImovel, idCliente, clienteRelacaoTipo,
							localidadeInicial, localidadeFinal, idAvisoBancario,
							idMovimentoArrecadador, periodoArrecadacaoInicial,
							periodoArrecadacaoFinal, periodoPagamentoInicial,
							periodoPagamentoFinal, dataPagamentoInicial,
							dataPagamentoFinal, idsPagamentoSituacao,
							idsDebitoTipo, idsArrecadacaoForma, idsDocumentoTipo,
	                        valorPagamentoInicial,
	                        valorPagamentoFinal);
				} 
				
				if (opcaoPagamento != null && opcaoPagamento.equals("historico")
						|| (opcaoPagamento.equals("ambos"))){
					
					colecaoPagamentosClienteHistorico = fachada.pesquisarPagamentoHistoricoClienteRelatorio(
						idImovel, idCliente, clienteRelacaoTipo,
						localidadeInicial, localidadeFinal, idAvisoBancario,
						idMovimentoArrecadador, periodoArrecadacaoInicial,
						periodoArrecadacaoFinal, periodoPagamentoInicial,
						periodoPagamentoFinal, dataPagamentoInicial,
						dataPagamentoFinal, idsPagamentoSituacao,
						idsDebitoTipo, idsArrecadacaoForma, idsDocumentoTipo,
                        valorPagamentoInicial,
                        valorPagamentoFinal);
					
					colecaoPagamentos.addAll(colecaoPagamentosClienteHistorico);
					
					colecaoPagamentosHistoricoClienteGuiaPagamento = fachada
							.pesquisarPagamentoHistoricoClienteGuiaPagamentoRelatorio(
									idImovel, idCliente, clienteRelacaoTipo,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idMovimentoArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicial,
									periodoPagamentoFinal, dataPagamentoInicial,
									dataPagamentoFinal, idsPagamentoSituacao,
									idsDebitoTipo, idsArrecadacaoForma,
									idsDocumentoTipo);
					
					colecaoPagamentos.addAll(colecaoPagamentosHistoricoClienteGuiaPagamento);
					
				}
			} else if (idAvisoBancario != null && !idAvisoBancario.equals("")) {
				colecaoPagamentos = fachada
						.pesquisarPagamentoAvisoBancarioRelatorio(idImovel,
								idCliente, clienteRelacaoTipo,
								localidadeInicial, localidadeFinal,
								idAvisoBancario, idMovimentoArrecadador,
								periodoArrecadacaoInicial,
								periodoArrecadacaoFinal,
								periodoPagamentoInicial, periodoPagamentoFinal,
								dataPagamentoInicial, dataPagamentoFinal,
								idsPagamentoSituacao, idsDebitoTipo,
								idsArrecadacaoForma, idsDocumentoTipo,
                                valorPagamentoInicial,
                                valorPagamentoFinal);
			} else if (idMovimentoArrecadador != null
					&& !idMovimentoArrecadador.equals("")) {
				colecaoPagamentos = fachada
						.pesquisarPagamentoMovimentoArrecadadorRelatorio(
								idImovel, idCliente, clienteRelacaoTipo,
								localidadeInicial, localidadeFinal,
								idAvisoBancario, idMovimentoArrecadador,
								periodoArrecadacaoInicial,
								periodoArrecadacaoFinal,
								periodoPagamentoInicial, periodoPagamentoFinal,
								dataPagamentoInicial, dataPagamentoFinal,
								idsPagamentoSituacao, idsDebitoTipo,
								idsArrecadacaoForma, idsDocumentoTipo,
                                valorPagamentoInicial,
                                valorPagamentoFinal);
			} else {
				// verifica se � para pesquisar no atual, no historico ou em
				// ambos
				if (opcaoPagamento != null && opcaoPagamento.equals("atual")) {
					colecaoPagamentos = fachada
							.pesquisarPagamentoLocalidadeRelatorio(idImovel,
									idCliente, clienteRelacaoTipo,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idMovimentoArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicial,
									periodoPagamentoFinal,
									dataPagamentoInicial, dataPagamentoFinal,
									idsPagamentoSituacao, idsDebitoTipo,
									idsArrecadacaoForma, idsDocumentoTipo,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);
				} else if (opcaoPagamento != null
						&& opcaoPagamento.equals("historico")) {
					colecaoPagamentos = fachada
							.pesquisarPagamentoHistoricoLocalidadeRelatorio(
									idImovel, idCliente, clienteRelacaoTipo,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idMovimentoArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicial,
									periodoPagamentoFinal,
									dataPagamentoInicial, dataPagamentoFinal,
									idsPagamentoSituacao, idsDebitoTipo,
									idsArrecadacaoForma, idsDocumentoTipo,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);

				} else if (opcaoPagamento != null
						&& opcaoPagamento.equals("ambos")) {
					colecaoPagamentos = fachada
							.pesquisarPagamentoLocalidadeAmbosRelatorio(
									idImovel, idCliente, clienteRelacaoTipo,
									localidadeInicial, localidadeFinal,
									idAvisoBancario, idMovimentoArrecadador,
									periodoArrecadacaoInicial,
									periodoArrecadacaoFinal,
									periodoPagamentoInicial,
									periodoPagamentoFinal,
									dataPagamentoInicial, dataPagamentoFinal,
									idsPagamentoSituacao, idsDebitoTipo,
									idsArrecadacaoForma, idsDocumentoTipo,
                                    valorPagamentoInicial,
                                    valorPagamentoFinal);
				}

			}
		}

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioPagamentoBean relatorioBean = null;

		// Cria as vari�veis que ser�o usadas futuramente no somat�rio de alguns
		// valores e para contar o n�mero de devolu��es de cada tipo
		int qtdePagamentosClassificadoContas = 0;
		BigDecimal valorPagamentosClassificadoContas = new BigDecimal("0.00");
		int qtdePagamentosDuploExcessoContas = 0;
		BigDecimal valorPagamentosDuploExcessoContas = new BigDecimal("0.00");
		int qtdePagamentosDocumentoInexistenteContas = 0;
		BigDecimal valorPagamentosDocumentoInexistenteContas = new BigDecimal(
				"0.00");
		int qtdePagamentosBaixarValorExcedenteContas = 0;
		BigDecimal valorPagamentosBaixarValorExcedenteContas = new BigDecimal(
				"0.00");
		int qtdePagamentosValorNaoConfereContas = 0;
		BigDecimal valorPagamentosValorNaoConfereContas = new BigDecimal("0.00");
		int qtdePagamentosDuploExcessoDevolvidoContas = 0;
		BigDecimal valorPagamentosDuploExcessoDevolvidoContas = new BigDecimal(
				"0.00");

		int qtdePagamentosClassificadoGuiasPagamento = 0;
		BigDecimal valorPagamentosClassificadoGuiasPagamento = new BigDecimal(
				"0.00");
		int qtdePagamentosDuploExcessoGuiasPagamento = 0;
		BigDecimal valorPagamentosDuploExcessoGuiasPagamento = new BigDecimal(
				"0.00");
		int qtdePagamentosDocumentoInexistenteGuiasPagamento = 0;
		BigDecimal valorPagamentosDocumentoInexistenteGuiasPagamento = new BigDecimal(
				"0.00");
		int qtdePagamentosBaixarValorExcedenteGuiasPagamento = 0;
		BigDecimal valorPagamentosBaixarValorExcedenteGuiasPagamento = new BigDecimal(
				"0.00");
		int qtdePagamentosValorNaoConfereGuiasPagamento = 0;
		BigDecimal valorPagamentosValorNaoConfereGuiasPagamento = new BigDecimal(
				"0.00");
		int qtdePagamentosDuploExcessoDevolvidoGuiasPagamento = 0;
		BigDecimal valorPagamentosDuploExcessoDevolvidoGuiasPagamento = new BigDecimal(
				"0.00");

		int qtdePagamentosClassificadoDebitosACobrar = 0;
		BigDecimal valorPagamentosClassificadoDebitosACobrar = new BigDecimal(
				"0.00");
		int qtdePagamentosDuploExcessoDebitosACobrar = 0;
		BigDecimal valorPagamentosDuploExcessoDebitosACobrar = new BigDecimal(
				"0.00");
		int qtdePagamentosDocumentoInexistenteDebitosACobrar = 0;
		BigDecimal valorPagamentosDocumentoInexistenteDebitosACobrar = new BigDecimal(
				"0.00");
		int qtdePagamentosBaixarValorExcedenteDebitosACobrar = 0;
		BigDecimal valorPagamentosBaixarValorExcedenteDebitosACobrar = new BigDecimal(
				"0.00");
		int qtdePagamentosValorNaoConfereDebitosACobrar = 0;
		BigDecimal valorPagamentosValorNaoConfereDebitosACobrar = new BigDecimal(
				"0.00");
		int qtdePagamentosDuploExcessoDevolvidoDebitosACobrar = 0;
		BigDecimal valorPagamentosDuploExcessoDevolvidoDebitosACobrar = new BigDecimal(
				"0.00");

		int qtdePagamentosClassificado = 0;
		BigDecimal valorPagamentosClassificado = new BigDecimal("0.00");
		int qtdePagamentosDuploExcesso = 0;
		BigDecimal valorPagamentosDuploExcesso = new BigDecimal("0.00");
		int qtdePagamentosDocumentoInexistente = 0;
		BigDecimal valorPagamentosDocumentoInexistente = new BigDecimal("0.00");
		int qtdePagamentosBaixarValorExcedente = 0;
		BigDecimal valorPagamentosBaixarValorExcedente = new BigDecimal("0.00");
		int qtdePagamentosValorNaoConfere = 0;
		BigDecimal valorPagamentosValorNaoConfere = new BigDecimal("0.00");
		int qtdePagamentosDuploExcessoDevolvido = 0;
		BigDecimal valorPagamentosDuploExcessoDevolvido = new BigDecimal("0.00");

		if (relatorioConsultarAvisoBancario) {

			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// coloca a cole��o de par�metros da analise no iterator
				Iterator colecaoPagamentosIterator = colecaoPagamentos
						.iterator();

				// la�o para criar a cole��o de par�metros da analise
				while (colecaoPagamentosIterator.hasNext()) {

					Pagamento pagamento = (Pagamento) colecaoPagamentosIterator
							.next();

					String tipoDocumento = "";
					if (pagamento.getDocumentoTipo() != null
							&& pagamento.getDocumentoTipo()
									.getDescricaoAbreviado() != null) {
						tipoDocumento = pagamento.getDocumentoTipo()
								.getDescricaoAbreviado();
					}

					String dataPagamento = "";
					if (pagamento.getDataPagamento() != null) {
						dataPagamento = Util.formatarData(pagamento
								.getDataPagamento());
					}

					String localidade = "";
					if (pagamento.getLocalidade() != null
							&& pagamento.getLocalidade().getId() != null) {
						localidade = pagamento.getLocalidade().getId()
								.toString();
					}

					String matricula = "";
					if (pagamento.getImovel() != null
							&& pagamento.getImovel().getId() != null) {
						matricula = pagamento.getImovel().getId().toString();
					}

					String codigoCliente = "";
					if (pagamento.getCliente() != null
							&& pagamento.getCliente().getId() != null) {
						codigoCliente = pagamento.getCliente().getId()
								.toString();
					}

					String referencia = "";
					if (pagamento.getAnoMesReferenciaPagamento() != null) {
						referencia = pagamento
								.getFormatarAnoMesPagamentoParaMesAno();
					}

					String debito = "";
					if (pagamento.getDebitoTipo() != null
							&& pagamento.getDebitoTipo().getId() != null) {
						debito = pagamento.getDebitoTipo().getId().toString();
					}

					String valorPagamento = "";
					if (pagamento.getValorPagamento() != null) {
						valorPagamento = Util.formatarMoedaReal(pagamento
								.getValorPagamento());
					}

					String situacaoAtual = "";
					if (pagamento.getPagamentoSituacaoAtual() != null
							&& pagamento.getPagamentoSituacaoAtual()
									.getDescricaoAbreviada() != null) {
						situacaoAtual = pagamento.getPagamentoSituacaoAtual()
								.getDescricaoAbreviada();
					}
					
					boolean indicadorHistorico = false;
					if ( pagamento.getIndicadorPagamento() == ConstantesSistema.SIM ) {
						indicadorHistorico = true;
					}
					

					relatorioBean = new RelatorioPagamentoBean(

					// Tipo de Documento
							tipoDocumento,

							// Data do Pagamento
							dataPagamento,

							// Localidade
							localidade,

							// Matr�cula do Im�vel
							matricula,

							// C�digo do Cliente
							codigoCliente,

							// Refer�ncia do Pagamento
							referencia,

							// D�bito
							debito,

							// Valor Pagamento
							valorPagamento,

							// Situa��o Atual
							situacaoAtual,

							// Indicador Hist�rico
							indicadorHistorico);

					relatorioBeans.add(relatorioBean);

				}

			}

		} else {

			// se a cole��o de par�metros da analise n�o for vazia
			if (colecaoPagamentos != null && !colecaoPagamentos.isEmpty()) {
				// coloca a cole��o de par�metros da analise no iterator
				Iterator colecaoPagamentosIterator = colecaoPagamentos
						.iterator();

				// la�o para criar a cole��o de par�metros da analise
				while (colecaoPagamentosIterator.hasNext()) {

					PagamentoRelatorioHelper pagamentoRelatorioHelper = (PagamentoRelatorioHelper) colecaoPagamentosIterator
							.next();
					//
					// (pagamento.getGuiaPagamento() == null && pagamento
					// .getAnoMesReferenciaPagamento() != null)
					// ||

					// Verifica o tipo do documento do pagamento e calcula a
					// quantidade de pagamentos de cada tipo e o somat�rio do
					// valor
					// dos pagamentos

					// Conta
					if (pagamentoRelatorioHelper.getIdDocumentoTipo().equals(
							DocumentoTipo.CONTA)) {

						// Pagamento Classificado
						if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual() == null
								|| pagamentoRelatorioHelper
										.getIdSituacaoPagamentoAtual()
										.equals(
												PagamentoSituacao.PAGAMENTO_CLASSIFICADO)) {
							qtdePagamentosClassificadoContas = qtdePagamentosClassificadoContas + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosClassificadoContas = valorPagamentosClassificadoContas
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Pagamento em Duplicidade
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(
										PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE)) {
							qtdePagamentosDuploExcessoContas = qtdePagamentosDuploExcessoContas + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDuploExcessoContas = valorPagamentosDuploExcessoContas
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Documento Inexistente
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE)) {
							qtdePagamentosDocumentoInexistenteContas = qtdePagamentosDocumentoInexistenteContas + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDocumentoInexistenteContas = valorPagamentosDocumentoInexistenteContas
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Baixar Valor Excedente
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual().equals(
										PagamentoSituacao.VALOR_A_BAIXAR)) {
							qtdePagamentosBaixarValorExcedenteContas = qtdePagamentosBaixarValorExcedenteContas + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosBaixarValorExcedenteContas = valorPagamentosBaixarValorExcedenteContas
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Valor N�o Confere
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual().equals(
										PagamentoSituacao.VALOR_NAO_CONFERE)) {
							qtdePagamentosValorNaoConfereContas = qtdePagamentosValorNaoConfereContas + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosValorNaoConfereContas = valorPagamentosValorNaoConfereContas
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Pagamento Duplo em Excesso
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(
										PagamentoSituacao.DUPLICIDADE_EXCESSO_DEVOLVIDO)) {
							qtdePagamentosDuploExcessoDevolvidoContas = qtdePagamentosDuploExcessoDevolvidoContas + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDuploExcessoDevolvidoContas = valorPagamentosDuploExcessoDevolvidoContas
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}
					}

					// D�bito a Cobrar
					else if (pagamentoRelatorioHelper.getIdDocumentoTipo()
							.equals(DocumentoTipo.DEBITO_A_COBRAR)) {

						// Pagamento Classificado
						if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual() == null
								|| pagamentoRelatorioHelper
										.getIdSituacaoPagamentoAtual()
										.equals(
												PagamentoSituacao.PAGAMENTO_CLASSIFICADO)) {
							qtdePagamentosClassificadoDebitosACobrar = qtdePagamentosClassificadoDebitosACobrar + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosClassificadoDebitosACobrar = valorPagamentosClassificadoDebitosACobrar
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Pagamento em Duplicidade
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(
										PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE)) {
							qtdePagamentosDuploExcessoDebitosACobrar = qtdePagamentosDuploExcessoDebitosACobrar + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDuploExcessoDebitosACobrar = valorPagamentosDuploExcessoDebitosACobrar
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Documento Inexistente
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE)) {
							qtdePagamentosDocumentoInexistenteDebitosACobrar = qtdePagamentosDocumentoInexistenteDebitosACobrar + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDocumentoInexistenteDebitosACobrar = valorPagamentosDocumentoInexistenteDebitosACobrar
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Baixar Valor Excedente
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual().equals(
										PagamentoSituacao.VALOR_A_BAIXAR)) {
							qtdePagamentosBaixarValorExcedenteDebitosACobrar = qtdePagamentosBaixarValorExcedenteDebitosACobrar + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosBaixarValorExcedenteDebitosACobrar = valorPagamentosBaixarValorExcedenteDebitosACobrar
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Valor N�o Confere
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual().equals(
										PagamentoSituacao.VALOR_NAO_CONFERE)) {
							qtdePagamentosValorNaoConfereDebitosACobrar = qtdePagamentosValorNaoConfereDebitosACobrar + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosValorNaoConfereDebitosACobrar = valorPagamentosValorNaoConfereDebitosACobrar
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Pagamento Duplo em Excesso
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(
										PagamentoSituacao.DUPLICIDADE_EXCESSO_DEVOLVIDO)) {
							qtdePagamentosDuploExcessoDevolvidoDebitosACobrar = qtdePagamentosDuploExcessoDevolvidoDebitosACobrar + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDuploExcessoDevolvidoDebitosACobrar = valorPagamentosDuploExcessoDevolvidoDebitosACobrar
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}
					}

					// Guia Pagamento
					else if (pagamentoRelatorioHelper.getIdDocumentoTipo()
							.equals(DocumentoTipo.GUIA_PAGAMENTO)) {

						// Pagamento Classificado
						if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual() == null
								|| pagamentoRelatorioHelper
										.getIdSituacaoPagamentoAtual()
										.equals(
												PagamentoSituacao.PAGAMENTO_CLASSIFICADO)) {
							qtdePagamentosClassificadoGuiasPagamento = qtdePagamentosClassificadoGuiasPagamento + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosClassificadoGuiasPagamento = valorPagamentosClassificadoGuiasPagamento
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Pagamento em Duplicidade
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(
										PagamentoSituacao.PAGAMENTO_EM_DUPLICIDADE)) {
							qtdePagamentosDuploExcessoGuiasPagamento = qtdePagamentosDuploExcessoGuiasPagamento + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDuploExcessoGuiasPagamento = valorPagamentosDuploExcessoGuiasPagamento
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Documento Inexistente
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(PagamentoSituacao.DOCUMENTO_INEXISTENTE)) {
							qtdePagamentosDocumentoInexistenteGuiasPagamento = qtdePagamentosDocumentoInexistenteGuiasPagamento + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDocumentoInexistenteGuiasPagamento = valorPagamentosDocumentoInexistenteGuiasPagamento
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Baixar Valor Excedente
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual().equals(
										PagamentoSituacao.VALOR_A_BAIXAR)) {
							qtdePagamentosBaixarValorExcedenteGuiasPagamento = qtdePagamentosBaixarValorExcedenteGuiasPagamento + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosBaixarValorExcedenteGuiasPagamento = valorPagamentosBaixarValorExcedenteGuiasPagamento
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Valor N�o Confere
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual().equals(
										PagamentoSituacao.VALOR_NAO_CONFERE)) {
							qtdePagamentosValorNaoConfereGuiasPagamento = qtdePagamentosValorNaoConfereGuiasPagamento + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosValorNaoConfereGuiasPagamento = valorPagamentosValorNaoConfereGuiasPagamento
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}

						// Pagamento Duplo em Excesso
						else if (pagamentoRelatorioHelper
								.getIdSituacaoPagamentoAtual()
								.equals(
										PagamentoSituacao.DUPLICIDADE_EXCESSO_DEVOLVIDO)) {
							qtdePagamentosDuploExcessoDevolvidoGuiasPagamento = qtdePagamentosDuploExcessoDevolvidoGuiasPagamento + 1;
							if (pagamentoRelatorioHelper.getValorPagamento() != null) {
								valorPagamentosDuploExcessoDevolvidoGuiasPagamento = valorPagamentosDuploExcessoDevolvidoGuiasPagamento
										.add(pagamentoRelatorioHelper
												.getValorPagamento());
							}
						}
					}

					String valorDocumento = Util
							.formatarMoedaReal(pagamentoRelatorioHelper
									.getValorTotalDocumento());
					
					String valorAgua = "0,00";

					if(pagamentoRelatorioHelper.getValorAgua() != null){
						valorAgua = Util
							.formatarMoedaReal(pagamentoRelatorioHelper
									.getValorAgua());
					}
					
					String valorEsgoto = "0,00";
					
					if(pagamentoRelatorioHelper.getValorEsgoto() != null){
						valorEsgoto = Util
							.formatarMoedaReal(pagamentoRelatorioHelper
									.getValorEsgoto());
					}	
					
					String valorCredito = "0,00";

					if(pagamentoRelatorioHelper.getValorCreditos() != null){
						valorCredito = Util
							.formatarMoedaReal(pagamentoRelatorioHelper
									.getValorCreditos());
					}
					
					String valorDebito = "0,00";

					if (pagamentoRelatorioHelper.getDebitos() != null){
						
						valorDebito = Util.formatarMoedaReal(pagamentoRelatorioHelper.getDebitos());
					}
					
					

					// Cria o objeto que ser� impresso no relat�rio setando os
					// campos que ser�o mostrados e fazendo as verifica��es para
					// evitar NullPointerException

					if (relatorioConsultarImovel) {
						
								relatorioBean = new RelatorioPagamentoBean(

								// Matr�cula do Im�vel ou C�digo do Cliente
								pagamentoRelatorioHelper.getIdImovel()
										.toString(),

								// Inscricao do Im�vel ou Nome do Cliente
								fachada
										.pesquisarInscricaoImovelExcluidoOuNao(pagamentoRelatorioHelper
												.getIdImovel()),

								// Data Pagamento
								pagamentoRelatorioHelper.getDataPagamento() == null ? ""
										: Util
												.formatarData(pagamentoRelatorioHelper
														.getDataPagamento()),

								// M�s/Ano
								pagamentoRelatorioHelper
										.getAnoMesReferenciaPagamento() == null ? ""
										: Util
												.formatarAnoMesParaMesAno(pagamentoRelatorioHelper
														.getAnoMesReferenciaPagamento()),

								// Tipo de D�bito
								pagamentoRelatorioHelper
										.getDescricaoTipoDebito() == null ? ""
										: pagamentoRelatorioHelper
												.getDescricaoTipoDebito(),

								// Valor do Documento
								valorDocumento,
								
								//valor Agua
								valorAgua,
								
								//Valor Esgoto
								valorEsgoto,

								// Valor Pagamento
								pagamentoRelatorioHelper.getValorPagamento() == null ? ""
										: Util
												.formatarMoedaReal(pagamentoRelatorioHelper
														.getValorPagamento()),

								// Situa��o Atual
								pagamentoRelatorioHelper
										.getDescricaoSituacaoPagamentoAtual() == null ? ""
										: pagamentoRelatorioHelper
												.getDescricaoSituacaoPagamentoAtual(),

								// Situa��o Anterior
								pagamentoRelatorioHelper
										.getDescricaoSituacaoPagamentoAnterior() == null ? ""
										: pagamentoRelatorioHelper
												.getDescricaoSituacaoPagamentoAnterior(),

								// Tipo Documento
								pagamentoRelatorioHelper
										.getDescricaoDocumentoTipo() == null ? ""
										: pagamentoRelatorioHelper
												.getDescricaoDocumentoTipo(),
												
						        // Valor dos D�bitos
								valorDebito,
										
						        // Valor do Cr�dito
								valorCredito,
								
								clie == null ? "" : clie.getNome());
						
					} else {

						relatorioBean = new RelatorioPagamentoBean(

								// Ger�ncia Regional
								pagamentoRelatorioHelper
										.getIdGerenciaRegional() == null ? ""
										: pagamentoRelatorioHelper
												.getIdGerenciaRegional()
												+ " - "
												+ pagamentoRelatorioHelper
														.getNomeGerenciaRegional(),

								// Localidade
								pagamentoRelatorioHelper.getIdLocalidade()
										.toString()
										+ " - "
										+ pagamentoRelatorioHelper
												.getDescricaoLocalidade(),

								// Matr�cula do Im�vel ou C�digo do Cliente
								pagamentoRelatorioHelper.getIdImovel() == null ? pagamentoRelatorioHelper
										.getIdCliente().toString()
										: pagamentoRelatorioHelper
												.getIdImovel().toString(),

								// Inscricao do Im�vel ou Nome do Cliente
								pagamentoRelatorioHelper.getIdImovel() == null ? pagamentoRelatorioHelper
										.getNomeCliente()
										: fachada
												.pesquisarInscricaoImovel(pagamentoRelatorioHelper
														.getIdImovel()),

								// Arrecadador
								pagamentoRelatorioHelper.getNomeArrecadador() == null ? ""
										: pagamentoRelatorioHelper
												.getNomeArrecadador(),

								// Data Pagamento
								pagamentoRelatorioHelper.getDataPagamento() == null ? ""
										: Util
												.formatarData(pagamentoRelatorioHelper
														.getDataPagamento()),

								// M�s/Ano
								pagamentoRelatorioHelper
										.getAnoMesReferenciaPagamento() == null ? ""
										: Util
												.formatarAnoMesParaMesAno(pagamentoRelatorioHelper
														.getAnoMesReferenciaPagamento()),

								// Tipo de D�bito
								pagamentoRelatorioHelper
										.getDescricaoTipoDebito() == null ? ""
										: pagamentoRelatorioHelper
												.getDescricaoTipoDebito(),

								// Valor do Documento
								valorDocumento,
								
								//valor Agua
								valorAgua,
								
								//Valor Esgoto
								valorEsgoto,

								// Valor Pagamento
								pagamentoRelatorioHelper.getValorPagamento() == null ? ""
										: Util
												.formatarMoedaReal(pagamentoRelatorioHelper
														.getValorPagamento()),

								// Situa��o Atual
								pagamentoRelatorioHelper
										.getDescricaoSituacaoPagamentoAtual() == null ? ""
										: pagamentoRelatorioHelper
												.getDescricaoSituacaoPagamentoAtual());

					}

					// adiciona o bean a cole��o
					relatorioBeans.add(relatorioBean);
				}

			}

		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (relatorioConsultarAvisoBancario) {
			
			// Arrecadador
			if (avisoBancarioHelper.getCodigoNomeArrecadador() != null) {
				parametros.put("arrecadador", avisoBancarioHelper.getCodigoNomeArrecadador());
			} else {
				parametros.put("arrecadador", "");
			}
			
			// Data do Lan�amento
			if (avisoBancarioHelper.getAvisoBancario().getDataLancamento() != null) {
				parametros.put("dataLancamento", Util.formatarData(avisoBancarioHelper.getAvisoBancario().getDataLancamento()));
			} else {
				parametros.put("dataLancamento", "");
			}
			
			// Sequencial
			 if (avisoBancarioHelper.getAvisoBancario().getNumeroSequencial() != null) {
				 parametros.put("sequencial", avisoBancarioHelper
				        	.getAvisoBancario().getNumeroSequencial().toString()); 
			 } else {
				 parametros.put("sequencial", "");
			 }
			 
			 // N�mero do Documento
			 if (avisoBancarioHelper.getAvisoBancario().getNumeroDocumento() != 0){
				 parametros.put("numeroDocumento", String.valueOf(avisoBancarioHelper
				        	.getAvisoBancario().getNumeroDocumento()));
			 } else {
				 parametros.put("numeroDocumento", "");
			 }
			
		} else {
		
		// Seta os par�metros de acordo com o que o usu�rio digitou ou de acordo
		// com a qtde de devolu��es de cada tipo e seus respectivos valores
		if (pagamentoParametrosInicial.getAnoMesReferenciaPagamento() != null) {
			parametros.put("periodoAnoMes", Util
					.formatarAnoMesParaMesAno(pagamentoParametrosInicial
							.getAnoMesReferenciaPagamento())
					+ " a "
					+ Util.formatarAnoMesParaMesAno(pagamentoParametrosFinal
							.getAnoMesReferenciaPagamento()));

		} else {
			parametros.put("periodoAnoMes", "");
		}

		if (pagamentoParametrosInicial.getDataPagamento() != null) {
			parametros.put("periodoPagamento",
					Util.formatarData(pagamentoParametrosInicial
							.getDataPagamento())
							+ " a "
							+ Util.formatarData(pagamentoParametrosFinal
									.getDataPagamento()));
		} else {
			parametros.put("periodoPagamento", "");
		}

		// o valor total das devolu��es de cada tipo ser� o valor de todas as
		// devolu��es desse tipo presente em cada tipo de documento, assim,
		// adiciona-se os valores a ele para achar o resultado
		qtdePagamentosClassificado = qtdePagamentosClassificado
				+ qtdePagamentosClassificadoContas
				+ qtdePagamentosClassificadoDebitosACobrar
				+ qtdePagamentosClassificadoGuiasPagamento;

		valorPagamentosClassificado = valorPagamentosClassificado
				.add(valorPagamentosClassificadoContas
						.add(valorPagamentosClassificadoDebitosACobrar
								.add(valorPagamentosClassificadoGuiasPagamento)));

		qtdePagamentosDuploExcesso = qtdePagamentosDuploExcesso
				+ qtdePagamentosDuploExcessoContas
				+ qtdePagamentosDuploExcessoDebitosACobrar
				+ qtdePagamentosDuploExcessoGuiasPagamento;

		valorPagamentosDuploExcesso = valorPagamentosDuploExcesso
				.add(valorPagamentosDuploExcessoContas
						.add(valorPagamentosDuploExcessoDebitosACobrar
								.add(valorPagamentosDuploExcessoGuiasPagamento)));

		qtdePagamentosDocumentoInexistente = qtdePagamentosDocumentoInexistente
				+ qtdePagamentosDocumentoInexistenteContas
				+ qtdePagamentosDocumentoInexistenteDebitosACobrar
				+ qtdePagamentosDocumentoInexistenteGuiasPagamento;

		valorPagamentosDocumentoInexistente = valorPagamentosDocumentoInexistente
				.add(valorPagamentosDocumentoInexistenteContas
						.add(valorPagamentosDocumentoInexistenteDebitosACobrar
								.add(valorPagamentosDocumentoInexistenteGuiasPagamento)));

		qtdePagamentosBaixarValorExcedente = qtdePagamentosBaixarValorExcedente
				+ qtdePagamentosBaixarValorExcedenteContas
				+ qtdePagamentosBaixarValorExcedenteDebitosACobrar
				+ qtdePagamentosBaixarValorExcedenteGuiasPagamento;

		valorPagamentosBaixarValorExcedente = valorPagamentosBaixarValorExcedente
				.add(valorPagamentosBaixarValorExcedenteContas
						.add(valorPagamentosBaixarValorExcedenteDebitosACobrar
								.add(valorPagamentosBaixarValorExcedenteGuiasPagamento)));

		qtdePagamentosValorNaoConfere = qtdePagamentosValorNaoConfere
				+ qtdePagamentosValorNaoConfereContas
				+ qtdePagamentosValorNaoConfereDebitosACobrar
				+ qtdePagamentosValorNaoConfereGuiasPagamento;

		valorPagamentosValorNaoConfere = valorPagamentosValorNaoConfere
				.add(valorPagamentosValorNaoConfereContas
						.add(valorPagamentosValorNaoConfereDebitosACobrar
								.add(valorPagamentosValorNaoConfereGuiasPagamento)));

		qtdePagamentosDuploExcessoDevolvido = qtdePagamentosDuploExcessoDevolvido
				+ qtdePagamentosDuploExcessoDevolvidoContas
				+ qtdePagamentosDuploExcessoDevolvidoDebitosACobrar
				+ qtdePagamentosDuploExcessoDevolvidoGuiasPagamento;

		valorPagamentosDuploExcessoDevolvido = valorPagamentosDuploExcessoDevolvido
				.add(valorPagamentosDuploExcessoDevolvidoContas
						.add(valorPagamentosDuploExcessoDevolvidoDebitosACobrar
								.add(valorPagamentosDuploExcessoDevolvidoGuiasPagamento)));

		parametros.put("qtdePagamentosClassificadoContas", ""
				+ qtdePagamentosClassificadoContas);
		parametros.put("valorPagamentosClassificadoContas", Util
				.formatarMoedaReal(valorPagamentosClassificadoContas));
		parametros.put("qtdePagamentosDuploExcessoContas", ""
				+ qtdePagamentosDuploExcessoContas);
		parametros.put("valorPagamentosDuploExcessoContas", Util
				.formatarMoedaReal(valorPagamentosDuploExcessoContas));
		parametros.put("qtdePagamentosDocumentoInexistenteContas", ""
				+ qtdePagamentosDocumentoInexistenteContas);
		parametros.put("valorPagamentosDocumentoInexistenteContas", Util
				.formatarMoedaReal(valorPagamentosDocumentoInexistenteContas));
		parametros.put("qtdePagamentosBaixarValorExcedenteContas", ""
				+ qtdePagamentosBaixarValorExcedenteContas);
		parametros.put("valorPagamentosBaixarValorExcedenteContas", Util
				.formatarMoedaReal(valorPagamentosBaixarValorExcedenteContas));
		parametros.put("qtdePagamentosValorNaoConfereContas", ""
				+ qtdePagamentosValorNaoConfereContas);
		parametros.put("valorPagamentosValorNaoConfereContas", Util
				.formatarMoedaReal(valorPagamentosValorNaoConfereContas));
		parametros.put("qtdePagamentosDuploExcessoDevolvidoContas", ""
				+ qtdePagamentosDuploExcessoDevolvidoContas);
		parametros.put("valorPagamentosDuploExcessoDevolvidoContas", Util
				.formatarMoedaReal(valorPagamentosDuploExcessoDevolvidoContas));

		parametros.put("qtdePagamentosClassificadoGuiasPagamento", ""
				+ qtdePagamentosClassificadoGuiasPagamento);
		parametros.put("valorPagamentosClassificadoGuiasPagamento", Util
				.formatarMoedaReal(valorPagamentosClassificadoGuiasPagamento));
		parametros.put("qtdePagamentosDuploExcessoGuiasPagamento", ""
				+ qtdePagamentosDuploExcessoGuiasPagamento);
		parametros.put("valorPagamentosDuploExcessoGuiasPagamento", Util
				.formatarMoedaReal(valorPagamentosDuploExcessoGuiasPagamento));
		parametros.put("qtdePagamentosDocumentoInexistenteGuiasPagamento", ""
				+ qtdePagamentosDocumentoInexistenteGuiasPagamento);
		parametros
				.put(
						"valorPagamentosDocumentoInexistenteGuiasPagamento",
						Util
								.formatarMoedaReal(valorPagamentosDocumentoInexistenteGuiasPagamento));
		parametros.put("qtdePagamentosBaixarValorExcedenteGuiasPagamento", ""
				+ qtdePagamentosBaixarValorExcedenteGuiasPagamento);
		parametros
				.put(
						"valorPagamentosBaixarValorExcedenteGuiasPagamento",
						Util
								.formatarMoedaReal(valorPagamentosBaixarValorExcedenteGuiasPagamento));
		parametros.put("qtdePagamentosValorNaoConfereGuiasPagamento", ""
				+ qtdePagamentosValorNaoConfereGuiasPagamento);
		parametros
				.put(
						"valorPagamentosValorNaoConfereGuiasPagamento",
						Util
								.formatarMoedaReal(valorPagamentosValorNaoConfereGuiasPagamento));
		parametros.put("qtdePagamentosDuploExcessoDevolvidoGuiasPagamento", ""
				+ qtdePagamentosDuploExcessoDevolvidoGuiasPagamento);
		parametros
				.put(
						"valorPagamentosDuploExcessoDevolvidoGuiasPagamento",
						Util
								.formatarMoedaReal(valorPagamentosDuploExcessoDevolvidoGuiasPagamento));

		parametros.put("qtdePagamentosClassificadoDebitosACobrar", ""
				+ qtdePagamentosClassificadoDebitosACobrar);
		parametros.put("valorPagamentosClassificadoDebitosACobrar", Util
				.formatarMoedaReal(valorPagamentosClassificadoDebitosACobrar));
		parametros.put("qtdePagamentosDuploExcessoDebitosACobrar", ""
				+ qtdePagamentosDuploExcessoDebitosACobrar);
		parametros.put("valorPagamentosDuploExcessoDebitosACobrar", Util
				.formatarMoedaReal(valorPagamentosDuploExcessoDebitosACobrar));
		parametros.put("qtdePagamentosDocumentoInexistenteDebitosACobrar", ""
				+ qtdePagamentosDocumentoInexistenteDebitosACobrar);
		parametros
				.put(
						"valorPagamentosDocumentoInexistenteDebitosACobrar",
						Util
								.formatarMoedaReal(valorPagamentosDocumentoInexistenteDebitosACobrar));
		parametros.put("qtdePagamentosBaixarValorExcedenteDebitosACobrar", ""
				+ qtdePagamentosBaixarValorExcedenteDebitosACobrar);
		parametros
				.put(
						"valorPagamentosBaixarValorExcedenteDebitosACobrar",
						Util
								.formatarMoedaReal(valorPagamentosBaixarValorExcedenteDebitosACobrar));
		parametros.put("qtdePagamentosValorNaoConfereDebitosACobrar", ""
				+ qtdePagamentosValorNaoConfereDebitosACobrar);
		parametros
				.put(
						"valorPagamentosValorNaoConfereDebitosACobrar",
						Util
								.formatarMoedaReal(valorPagamentosValorNaoConfereDebitosACobrar));
		parametros.put("qtdePagamentosDuploExcessoDevolvidoDebitosACobrar", ""
				+ qtdePagamentosDuploExcessoDevolvidoDebitosACobrar);
		parametros
				.put(
						"valorPagamentosDuploExcessoDevolvidoDebitosACobrar",
						Util
								.formatarMoedaReal(valorPagamentosDuploExcessoDevolvidoDebitosACobrar));

		parametros.put("qtdePagamentosClassificado", ""
				+ qtdePagamentosClassificado);
		parametros.put("valorPagamentosClassificado", Util
				.formatarMoedaReal(valorPagamentosClassificado));
		parametros.put("qtdePagamentosDuploExcesso", ""
				+ qtdePagamentosDuploExcesso);
		parametros.put("valorPagamentosDuploExcesso", Util
				.formatarMoedaReal(valorPagamentosDuploExcesso));
		parametros.put("qtdePagamentosDocumentoInexistente", ""
				+ qtdePagamentosDocumentoInexistente);
		parametros.put("valorPagamentosDocumentoInexistente", Util
				.formatarMoedaReal(valorPagamentosDocumentoInexistente));
		parametros.put("qtdePagamentosBaixarValorExcedente", ""
				+ qtdePagamentosBaixarValorExcedente);
		parametros.put("valorPagamentosBaixarValorExcedente", Util
				.formatarMoedaReal(valorPagamentosBaixarValorExcedente));
		parametros.put("qtdePagamentosValorNaoConfere", ""
				+ qtdePagamentosValorNaoConfere);
		parametros.put("valorPagamentosValorNaoConfere", Util
				.formatarMoedaReal(valorPagamentosValorNaoConfere));
		parametros.put("qtdePagamentosDuploExcessoDevolvido", ""
				+ qtdePagamentosDuploExcessoDevolvido);
		parametros.put("valorPagamentosDuploExcessoDevolvido", Util
				.formatarMoedaReal(valorPagamentosDuploExcessoDevolvido));
		
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		if (relatorioConsultarImovel) {
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_PAGAMENTO_CONSULTAR_IMOVEL,
					parametros, ds, tipoFormatoRelatorio);
		} else if (relatorioConsultarAvisoBancario) { 
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_PAGAMENTO_CONSULTAR_AVISO_BANCARIO,
					parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_PAGAMENTO, parametros, ds,
					tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.PAGAMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {

		Fachada fachada = Fachada.getInstancia();

		int i = 0;

		// Par�metros de Pesquisa
		String idImovel = (String) getParametro("idImovel");
		String idCliente = (String) getParametro("idCliente");
		String clienteRelacaoTipo = (String) getParametro("clienteRelacaoTipo");
		String idAvisoBancario = (String) getParametro("idAvisoBancario");
		String idMovimentoArrecadador = (String) getParametro("idMovimentoArrecadador");
		String localidadeInicial = (String) getParametro("localidadeInicial");
		String localidadeFinal = (String) getParametro("localidadeFinal");
		String periodoArrecadacaoInicial = (String) getParametro("periodoArrecadacaoInicial");
		String periodoArrecadacaoFinal = (String) getParametro("periodoArrecadacaoFinal");
		String periodoPagamentoInicial = (String) getParametro("periodoPagamentoInicial");
		String periodoPagamentoFinal = (String) getParametro("periodoPagamentoFinal");
		Date dataPagamentoInicial = (Date) getParametro("dataPagamentoInicial");
		Date dataPagamentoFinal = (Date) getParametro("dataPagamentoFinal");
		String[] idsPagamentoSituacao = (String[]) getParametro("idsPagamentoSituacao");
		String[] idsArrecadacaoForma = (String[]) getParametro("idsArrecadacaoForma");
		String[] idsDocumentoTipo = (String[]) getParametro("idsDocumentoTipo");
		String[] idsDebitoTipo = (String[]) getParametro("idsDebitoTipo");
		String opcaoPagamento = (String) getParametro("opcaoPagamento");
        
        String valorPagamentoInicial = (String) getParametro("valorPagamentoInicial");
        String valorPagamentoFinal = (String) getParametro("valorPagamentoFinal");
        

		if (idImovel != null && !idImovel.equals("")) {
			
			if (opcaoPagamento != null && opcaoPagamento.equals("atual") || (opcaoPagamento
					.equals("ambos")) ) {
				
					i = fachada.pesquisarPagamentoImovelCount(idImovel, idCliente,
							clienteRelacaoTipo, localidadeInicial, localidadeFinal,
							idAvisoBancario, idMovimentoArrecadador,
							periodoArrecadacaoInicial, periodoArrecadacaoFinal,
							periodoPagamentoInicial, periodoPagamentoFinal,
							dataPagamentoInicial, dataPagamentoFinal,
							idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
							idsDocumentoTipo, valorPagamentoInicial, valorPagamentoFinal);
					
					if (opcaoPagamento != null && opcaoPagamento.equals("historico") || (opcaoPagamento
							.equals("ambos"))){
							i = i + fachada.pesquisarPagamentoHistoricoImovelCount(idImovel,
								idCliente, clienteRelacaoTipo, localidadeInicial,
								localidadeFinal, idAvisoBancario, idMovimentoArrecadador,
								periodoArrecadacaoInicial, periodoArrecadacaoFinal,
								periodoPagamentoInicial, periodoPagamentoFinal,
								dataPagamentoInicial, dataPagamentoFinal,
								idsPagamentoSituacao, idsDebitoTipo,
								idsArrecadacaoForma, idsDocumentoTipo);
					}
				
			}
		} else if (idCliente != null && !idCliente.equals("")) {
			
			if (opcaoPagamento != null && opcaoPagamento.equals("atual") || (opcaoPagamento
					.equals("ambos")) ) {
				
				i = i + fachada.pesquisarPagamentoImovelCount(idImovel, idCliente,
						clienteRelacaoTipo, localidadeInicial, localidadeFinal,
						idAvisoBancario, idMovimentoArrecadador,
						periodoArrecadacaoInicial, periodoArrecadacaoFinal,
						periodoPagamentoInicial, periodoPagamentoFinal,
						dataPagamentoInicial, dataPagamentoFinal,
						idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
						idsDocumentoTipo,
	                    valorPagamentoInicial,
	                    valorPagamentoFinal );
				
			} 
			
			if (opcaoPagamento != null && opcaoPagamento.equals("historico") || (opcaoPagamento
					.equals("ambos"))){
				i = i + fachada.pesquisarPagamentoHistoricoClienteCount(idImovel, idCliente,
					clienteRelacaoTipo, localidadeInicial, localidadeFinal,
					idAvisoBancario, idMovimentoArrecadador,
					periodoArrecadacaoInicial, periodoArrecadacaoFinal,
					periodoPagamentoInicial, periodoPagamentoFinal,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
					idsDocumentoTipo, valorPagamentoInicial, valorPagamentoFinal);
				
			}
			
			
		} else if (idAvisoBancario != null && !idAvisoBancario.equals("")) {
			i = fachada.pesquisarPagamentoAvisoBancarioCount(idImovel,
					idCliente, clienteRelacaoTipo, localidadeInicial,
					localidadeFinal, idAvisoBancario, idMovimentoArrecadador,
					periodoArrecadacaoInicial, periodoArrecadacaoFinal,
					periodoPagamentoInicial, periodoPagamentoFinal,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
					idsDocumentoTipo,
                    valorPagamentoInicial,
                    valorPagamentoFinal);
			
			if ( i == 0 ) {
				//Pesquisa em Historico caso nao tenha nenhum pagamento atual.
			 i = fachada.pesquisarPagamentoHistoricoAvisoBancarioCount(idImovel,
						idCliente, clienteRelacaoTipo, localidadeInicial,
						localidadeFinal, idAvisoBancario, idMovimentoArrecadador,
						periodoArrecadacaoInicial, periodoArrecadacaoFinal,
						periodoPagamentoInicial, periodoPagamentoFinal,
						dataPagamentoInicial, dataPagamentoFinal,
						idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
						idsDocumentoTipo,
	                    valorPagamentoInicial,
	                    valorPagamentoFinal);
			}
			
 		} else if (idMovimentoArrecadador != null
				&& !idMovimentoArrecadador.equals("")) {
			i = fachada.pesquisarPagamentoMovimentoArrecadadorCount(idImovel,
					idCliente, clienteRelacaoTipo, localidadeInicial,
					localidadeFinal, idAvisoBancario, idMovimentoArrecadador,
					periodoArrecadacaoInicial, periodoArrecadacaoFinal,
					periodoPagamentoInicial, periodoPagamentoFinal,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
					idsDocumentoTipo,
                    valorPagamentoInicial,
                    valorPagamentoFinal);
		} else {
			
			if (opcaoPagamento != null
					&& ((opcaoPagamento.equals("historico")) || (opcaoPagamento
							.equals("ambos")))) {
			i = i + fachada.pesquisarPagamentoHistoricoLocalidadeCount(idImovel, idCliente,
					clienteRelacaoTipo, localidadeInicial,
					localidadeFinal, idAvisoBancario,
					idMovimentoArrecadador, periodoArrecadacaoInicial,
					periodoArrecadacaoFinal,
					periodoPagamentoInicial, periodoPagamentoFinal,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentoSituacao, idsDebitoTipo,
					idsArrecadacaoForma, idsDocumentoTipo);
			} 
			if (opcaoPagamento != null
					&& ((opcaoPagamento.equals("atual")) || (opcaoPagamento
							.equals("ambos")))) {
			i = i + fachada.pesquisarPagamentoLocalidadeCount(idImovel, idCliente,
					clienteRelacaoTipo, localidadeInicial, localidadeFinal,
					idAvisoBancario, idMovimentoArrecadador,
					periodoArrecadacaoInicial, periodoArrecadacaoFinal,
					periodoPagamentoInicial, periodoPagamentoFinal,
					dataPagamentoInicial, dataPagamentoFinal,
					idsPagamentoSituacao, idsDebitoTipo, idsArrecadacaoForma,
					idsDocumentoTipo,
                    valorPagamentoInicial,
                    valorPagamentoFinal);
			}
		}

		if(i == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Relat�rio");
		}

		return i;

	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioPagamento", this);
	}

}
