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
package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class RelatorioManterMovimentoArrecadador extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterMovimentoArrecadador(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_MOVIMENTO_ARRECADADOR_MANTER);
	}

	@Deprecated
	public RelatorioManterMovimentoArrecadador() {
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

		Arrecadador arrecadadorParametros = (Arrecadador) getParametro("arrecadadorParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String codigoBanco = (String) getParametro("codigoBanco");
		String codigoRemessa = (String) getParametro("codigoRemessa");
		String descricaoIdentificacaoServico = (String) getParametro("descricaoIdentificacaoServico");
		String numeroSequencialArquivo = (String) getParametro("numeroSequencialArquivo");
		Date dataGeracaoInicio = null;
		if (getParametro("dataGeracaoInicio") != null){
			dataGeracaoInicio = (Date) getParametro("dataGeracaoInicio");
		}
		Date dataGeracaoFim = null;
		if (getParametro("dataGeracaoFim") != null){
			dataGeracaoFim = (Date) getParametro("dataGeracaoFim");
		}
		Date ultimaAlteracaoInicio = null;
		if (getParametro("ultimaAlteracaoInicio") != null ){
			ultimaAlteracaoInicio = (Date) getParametro("ultimaAlteracaoInicio");
		}
		Date ultimaAlteracaoFim = null;
		if (getParametro("ultimaAlteracaoFim") != null){
			ultimaAlteracaoFim = (Date) getParametro("ultimaAlteracaoFim");
		}
		String descricaoOcorrencia = (String) getParametro("descricaoOcorrencia");
		String indicadorAceitacao = (String) getParametro("indicadorAceitacao");
		String indicadorAbertoFechado = (String) getParametro("indicadorAbertoFechado");
			
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioManterMovimentoArrecadadorBean relatorioBean = null;

		Collection colecaoArrecadadorMovimento = fachada.filtrarMovimentoArrecadadorParaRelatorio(
				codigoBanco, codigoRemessa, descricaoIdentificacaoServico, numeroSequencialArquivo, 
				dataGeracaoInicio, dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim, 
				descricaoOcorrencia, indicadorAceitacao, indicadorAbertoFechado);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoArrecadadorMovimento != null
				&& !colecaoArrecadadorMovimento.isEmpty()) {
			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoArrecadadorMovimentoIterator = colecaoArrecadadorMovimento
					.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoArrecadadorMovimentoIterator.hasNext()) {

				ArrecadadorMovimento arrecadadorMovimento = (ArrecadadorMovimento) colecaoArrecadadorMovimentoIterator.next();

				// Obt�m a situa��o do movimento arrecadador
				String situacaoArrecadadorMovimento = fachada.obterSituacaoArrecadadorMovimento(arrecadadorMovimento);

				/*
				 * N�mero de registros em ocorr�ncia (n�mero de linhas da tabela
				 * ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID = ARMV_ID da tabela
				 * ARRECADADOR_MOVIMENTO e AMIT_DSOCORRENCIA diferente de "OK")
				 */
				Integer numeroRegistrosOcorrencia = fachada.obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(arrecadadorMovimento, ConstantesSistema.OK);

				/*
				 * N�mero de registros que n�o foram aceitos (n�mero de linhas
				 * da tabela ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID = ARMV_ID da
				 * tabela ARRECADADOR_MOVIMENTO e AMIT_ICACEITACAO igual a 2
				 * (N�O))
				 */
				Integer numeroRegistrosNaoAceitos = fachada.obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(arrecadadorMovimento,ConstantesSistema.REGISTROS_NAO_ACEITOS);

				// Pesquisa a cole��o de itens do movimento arrecadador
				Collection colecaoItensArrecadadorMovimento = fachada.consultarItensMovimentoArrecadador(arrecadadorMovimento,null,null,null);

				if (colecaoItensArrecadadorMovimento != null
						&& !colecaoItensArrecadadorMovimento.isEmpty()) {
					
					Collections.sort((List) colecaoItensArrecadadorMovimento,
							new Comparator() {
								public int compare(Object a, Object b) {
									String chave1 = "";
									if (((ArrecadadorMovimentoItemHelper) a)
											.getIdentificacao() != null) {
										chave1 = ((ArrecadadorMovimentoItemHelper) a)
												.getIdentificacao();
									}

									String chave2 = "";

									if (((ArrecadadorMovimentoItemHelper) b)
											.getIdentificacao() != null) {
										chave2 = ((ArrecadadorMovimentoItemHelper) b)
												.getIdentificacao();
									}

									return chave1.compareTo(chave2);

								}
							});

					Iterator colecaoItensArrecadadorMovimentoIterator = colecaoItensArrecadadorMovimento
							.iterator();

					while (colecaoItensArrecadadorMovimentoIterator.hasNext()) {

						ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper = (ArrecadadorMovimentoItemHelper) colecaoItensArrecadadorMovimentoIterator.next();

						String identificacaoClienteBanco = "";
						String agencia = "";
						String data = "";
						String anoMes = "";
						String valor = "";
						String codigoRetono = "";
						String codigoMovimento = "";

						// Verifica o Tipo do Movimento Arrecador e seta os valores de acordo com cada tipo
						if (arrecadadorMovimentoItemHelper.getRegistroHelperCodigoB() != null) {
							
							identificacaoClienteBanco = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoB().getIdClienteBanco();
							agencia = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoB().getAgenciaDebito();
							data = Util.formatarData(arrecadadorMovimentoItemHelper.getRegistroHelperCodigoB().getDataOpcaoExclusao());
							codigoMovimento = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoB().getCodigoMovimento();
						
						} else if (arrecadadorMovimentoItemHelper.getRegistroHelperCodigoC() != null) {
							
							identificacaoClienteBanco = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoC().getIdClienteBanco();
							agencia = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoC().getAgenciaDebito();
							codigoMovimento = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoC().getCodigoMovimento();
						
						} else if (arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE() != null) {
							
							identificacaoClienteBanco = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE().getIdClienteBanco();
							agencia = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE().getAgenciaDebito();
							data = Util	.formatarData(arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE().getDataDebito());
							anoMes = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE().getAnoMesReferenciaConta();
							valor = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE().getValorDebito();
							codigoMovimento = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoE().getCodigoMovimento();
							
						} else if (arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF() != null) {
							
							identificacaoClienteBanco = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getIdClienteBanco();
							agencia = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getAgenciaDebito();
							data = Util.formatarData(arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getDataDebito());
							anoMes = Util.formatarAnoMesParaMesAno(arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getAnoMesReferenciaConta());
							valor = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getValorDebito();
							codigoRetono = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getCodigoRetorno();
							codigoMovimento = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoF().getCodigoMovimento();
						
						} else if (arrecadadorMovimentoItemHelper.getRegistroHelperCodigoG() != null) {
							
							//identificacaoClienteBanco = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoG().getIdAgenciaContaDigito();
							valor = arrecadadorMovimentoItemHelper.getRegistroHelperCodigoG().getValorRecebido();
						}

						// Cria o Bean setando para nulo os campos que n�o s�o
						// referentes a pesquisa de reparcelamento para que o
						// agrupamento fique de maneira correta
						relatorioBean = new RelatorioManterMovimentoArrecadadorBean(

								// Dados do Movimento:
								// Id Movimento
								arrecadadorMovimento.getId().toString(),

								// C�digo Arrecadador
								arrecadadorMovimento.getCodigoBanco() == null ? ""
										: arrecadadorMovimento.getCodigoBanco()
												.toString(),

								// Nome Arrecadador
								arrecadadorMovimento.getNomeBanco(),

								// Remessa
								arrecadadorMovimento.getCodigoRemessa() == null ? ""
										: arrecadadorMovimento
												.getCodigoRemessa().toString(),

								// Servi�o
								arrecadadorMovimento
										.getDescricaoIdentificacaoServico(),

								// Sequencial
								arrecadadorMovimento
										.getNumeroSequencialArquivo() == null ? ""
										: arrecadadorMovimento
												.getNumeroSequencialArquivo()
												.toString(),

								// Data Gera��o
								arrecadadorMovimento.getDataGeracao() == null ? ""
										: Util
												.formatarData(arrecadadorMovimento
														.getDataGeracao()),

								// Data Processamento
								arrecadadorMovimento.getUltimaAlteracao() == null ? ""
										: Util
												.formatarDataComHora(arrecadadorMovimento
														.getUltimaAlteracao()),

								// Situa��o
								situacaoArrecadadorMovimento,

								// Valor Movimento
								arrecadadorMovimento.getValorTotalMovimento() == null ? ""
										: Util
												.formatarMoedaReal(arrecadadorMovimento
														.getValorTotalMovimento()),

								// Dados dos Itens
								// Registro
								arrecadadorMovimentoItemHelper
										.getCodigoRegistro(),

								// Identifica��o Im�vel/Cliente
								arrecadadorMovimentoItemHelper
										.getIdentificacao(),

								// Tipo Pagamento
								arrecadadorMovimentoItemHelper
										.getTipoPagamento(),

								// Identifica��o Cliente Banco
								identificacaoClienteBanco,

								// Data Pagamento
								arrecadadorMovimentoItemHelper
										.getRegistroHelperCodigoG() == null ? ""
										: Util
												.formatarData(arrecadadorMovimentoItemHelper
														.getRegistroHelperCodigoG()
														.getDataPagamento()),

								// Data Prevista
								arrecadadorMovimentoItemHelper
										.getRegistroHelperCodigoG() == null ? ""
										: Util
												.formatarData(arrecadadorMovimentoItemHelper
														.getRegistroHelperCodigoG()
														.getDataPrevistaCredito()),

								// C�digo Barras
								arrecadadorMovimentoItemHelper
										.getRegistroHelperCodigoG() == null ? ""
										: arrecadadorMovimentoItemHelper
												.getRegistroHelperCodigoG()
												.getCodigoBarras(),

								// Forma Arrecada��o
								arrecadadorMovimentoItemHelper
										.getRegistroHelperCodigoG() == null ? ""
										: arrecadadorMovimentoItemHelper
												.getRegistroHelperCodigoG()
												.getFormaArrecadacao(),

								// Ag�ncia
								agencia,

								// Data
								data,

								// Ano/M�s
								anoMes,

								// Valor
								valor,

								// C�digo de Retorno
								codigoRetono,

								// C�digo Movimento
								codigoMovimento,

								// Ocorr�ncia
								arrecadadorMovimentoItemHelper.getOcorrencia(),

								// Indicador Aceita��o
								arrecadadorMovimentoItemHelper
										.getDescricaoIndicadorAceitacao(),

								// Dados do Movimento Presentes no Rodap�
								// Total de Registros
								arrecadadorMovimento
										.getNumeroRegistrosMovimento() == null ? ""
										: arrecadadorMovimento
												.getNumeroRegistrosMovimento()
												.toString(),

								// N�mero de Movimentos em Ocorr�ncia
								numeroRegistrosOcorrencia == null ? ""
										: numeroRegistrosOcorrencia.toString(),

								// N�mero de Movimentos N�o Aceitos
								numeroRegistrosNaoAceitos == null ? ""
										: numeroRegistrosNaoAceitos.toString());

						// adiciona o bean a cole��o
						relatorioBeans.add(relatorioBean);

					}

				}
			}
		}
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Arrecadador
		if (arrecadadorParametros != null) {
			parametros.put("idArrecadador", arrecadadorParametros
					.getCodigoAgente().toString());
			parametros.put("nomeArrecadador", arrecadadorParametros
					.getCliente().getNome());
		} else {
			parametros.put("idArrecadador", "");
			parametros.put("nomeArrecadador", "");
		}

		// Remessa
		String remessa = "";

		if (codigoRemessa != null) {
			if (codigoRemessa.equals("" + ConstantesSistema.CODIGO_ENVIO)) {
				remessa = "ENVIO";
			} else if (codigoRemessa.equals("" + ConstantesSistema.CODIGO_RETORNO)) {
				remessa = "RETORNO";
			}
		}

		parametros.put("remessa", remessa);

		// Identifica��o Servi�o
		String identificaoServico = "";

		if (descricaoIdentificacaoServico != null
				&& !descricaoIdentificacaoServico.equals("")) {
			if (descricaoIdentificacaoServico.equals(
							ConstantesSistema.DEBITO_AUTOMATICO)) {
				identificaoServico = "D�BITO AUTOMATICO";
			} else if (descricaoIdentificacaoServico.equals(
							ConstantesSistema.CODIGO_DE_BARRAS)) {
				identificaoServico = "C�DIGO DE BARRAS";
			}
		}

		parametros.put("identificaoServico", identificaoServico);

		// Sequencial
		if (numeroSequencialArquivo != null) {
			parametros.put("sequencial", numeroSequencialArquivo);
		} else {
			parametros.put("sequencial", "");
		}

		// Per�odo Gera��o
		if (dataGeracaoInicio != null) {

			parametros.put("periodoGeracaoInicial", Util.formatarData(dataGeracaoInicio));

		} else {
			parametros.put("periodoGeracaoInicial", null);
		}

		if (dataGeracaoFim != null) {

			parametros.put("periodoGeracaoFinal", Util.formatarData(dataGeracaoFim));

		} else {
			parametros.put("periodoGeracaoFinal", null);
		}

		// Per�odo Processamento
		if (ultimaAlteracaoInicio != null) {

			parametros.put("periodoProcessamentoInicial", Util
					.formatarData(ultimaAlteracaoInicio));

		} else {
			parametros.put("periodoProcessamentoInicial", null);
		}

		if (ultimaAlteracaoFim != null) {

			parametros.put("periodoProcessamentoFinal", Util
					.formatarData(ultimaAlteracaoFim));

		} else {
			parametros.put("periodoProcessamentoFinal", null);
		}

		// �tens em Ocorr�ncia
		String itemEmOcorrenciaParametros = "";

		if (descricaoOcorrencia != null && !descricaoOcorrencia.equals("")) {
			if (descricaoOcorrencia.equals("" + ConstantesSistema.COM_ITENS)) {
				itemEmOcorrenciaParametros = "COM �TENS EM OCORR�NCIA";
			} else if (descricaoOcorrencia.equals("" + ConstantesSistema.SEM_ITENS)) {
				itemEmOcorrenciaParametros = "SEM �TENS EM OCORR�NCIA";
			}
		}

		parametros.put("itensOcorrencia", itemEmOcorrenciaParametros);

		// �tens N�o Aceitos
		String itemNaoAceitoParametros = "";

		if (indicadorAceitacao != null && !indicadorAceitacao.equals("")) {
			if (indicadorAceitacao.equals("" + ConstantesSistema.COM_ITENS)) {
				itemNaoAceitoParametros = "COM �TENS N�O ACEITOS";
			} else if (indicadorAceitacao
					.equals("" + ConstantesSistema.SEM_ITENS)) {
				itemNaoAceitoParametros = "SEM �TENS N�O ACEITOS";
			}
		}

		parametros.put("itensNaoAceitos", itemNaoAceitoParametros);

		// Movimentos Abertos/Fechados
		String movimentoAbertoFechadoParametros = "";

		if (indicadorAbertoFechado != null
				&& !indicadorAbertoFechado.equals("")) {
			if (indicadorAbertoFechado.equals(""
					+ ConstantesSistema.MOVIMENTO_ABERTO)) {
				movimentoAbertoFechadoParametros = "COM �TENS N�O ACEITOS";
			} else if (indicadorAbertoFechado.equals(""
					+ ConstantesSistema.MOVIMENTO_FECHADO)) {
				movimentoAbertoFechadoParametros = "SEM �TENS N�O ACEITOS";
			}
		}

		parametros.put("movimentosAbertosFechados",	movimentoAbertoFechadoParametros);

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_MOVIMENTO_ARRECADADOR_MANTER,
				parametros, ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.MANTER_MOVIMENTO_ARRECADADOR,
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
		int retorno = 0;
		String codigoBanco = (String) getParametro("codigoBanco");
		String codigoRemessa = (String) getParametro("codigoRemessa");
		String descricaoIdentificacaoServico = (String) getParametro("descricaoIdentificacaoServico");
		String numeroSequencialArquivo = (String) getParametro("numeroSequencialArquivo");
		Date dataGeracaoInicio = null;
		if (getParametro("dataGeracaoInicio") != null){
			dataGeracaoInicio = (Date) getParametro("dataGeracaoInicio");
		}
		Date dataGeracaoFim = null;
		if (getParametro("dataGeracaoFim") != null){
			dataGeracaoFim = (Date) getParametro("dataGeracaoFim");
		}
		Date ultimaAlteracaoInicio = null;
		if (getParametro("ultimaAlteracaoInicio") != null ){
			ultimaAlteracaoInicio = (Date) getParametro("ultimaAlteracaoInicio");
		}
		Date ultimaAlteracaoFim = null;
		if (getParametro("ultimaAlteracaoFim") != null){
			ultimaAlteracaoFim = (Date) getParametro("ultimaAlteracaoFim");
		}
		String descricaoOcorrencia = (String) getParametro("descricaoOcorrencia");
		String indicadorAceitacao = (String) getParametro("indicadorAceitacao");
		String indicadorAbertoFechado = (String) getParametro("indicadorAbertoFechado");
		
		retorno = Fachada.getInstancia().filtrarMovimentoArrecadadoresRelatorioCount(codigoBanco, codigoRemessa, descricaoIdentificacaoServico, numeroSequencialArquivo, dataGeracaoInicio, dataGeracaoFim, ultimaAlteracaoInicio, ultimaAlteracaoFim, descricaoOcorrencia, indicadorAceitacao, indicadorAbertoFechado);
		
//		retorno = Fachada
//				.getInstancia()
//				.totalRegistrosPesquisa(
//						(FiltroArrecadadorMovimento) getParametro("filtroArrecadadorMovimento"),
//						ArrecadadorMovimento.class.getName());

		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterMovimentoArrecadador",
				this);
	}

}