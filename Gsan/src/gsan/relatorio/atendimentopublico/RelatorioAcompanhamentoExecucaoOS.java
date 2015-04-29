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
package gsan.relatorio.atendimentopublico;

import gsan.atendimentopublico.ordemservico.bean.OSRelatorioAcompanhamentoExecucaoHelper;
import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de regitro atendimento manter de
 * �gua
 * 
 * @author Rafael Corr�a
 * @created 11 de Julho de 2005
 */
public class RelatorioAcompanhamentoExecucaoOS extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioAcompanhamentoExecucaoOS(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS);
	}

	@Deprecated
	public RelatorioAcompanhamentoExecucaoOS() {
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

		String origemServico = (String) getParametro("origemServico");
		String situacaoOS = (String) getParametro("situacaoOS");
		String[] idsServicosTipos = (String[]) getParametro("idsServicosTipos");
		String idUnidadeAtendimento = (String) getParametro("idUnidadeAtendimento");
		String idUnidadeAtual = (String) getParametro("idUnidadeAtual");
		String idUnidadeEncerramento = (String) getParametro("idUnidadeEncerramento");
		Date periodoAtendimentoInicial = (Date) getParametro("periodoAtendimentoInicial");
		Date periodoAtendimentoFinal = (Date) getParametro("periodoAtendimentoFinal");
		Date periodoEncerramentoInicial = (Date) getParametro("periodoEncerramentoInicial");
		Date periodoEncerramentoFinal = (Date) getParametro("periodoEncerramentoFinal");
		String idEquipeProgramacao = (String) getParametro("idEquipeProgramacao");
		String idEquipeExecucao = (String) getParametro("idEquipeExecucao");
		String tipoOrdenacao = (String) getParametro("tipoOrdenacao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoExecucaoOSBean relatorioBean = null;

		Collection colecaoOSRelatorioAcompanhamentoExecucaoHelper = fachada
				.pesquisarOSGerarRelatorioAcompanhamentoExecucao(origemServico,
						situacaoOS, idsServicosTipos, idUnidadeAtendimento,
						idUnidadeAtual, idUnidadeEncerramento,
						periodoAtendimentoInicial, periodoAtendimentoFinal,
						periodoEncerramentoInicial, periodoEncerramentoFinal,
						idEquipeProgramacao, idEquipeExecucao, tipoOrdenacao);

		if (colecaoOSRelatorioAcompanhamentoExecucaoHelper != null
				&& !colecaoOSRelatorioAcompanhamentoExecucaoHelper.isEmpty()) {
			
			if (situacaoOS == null || situacaoOS.equals("")) {
				situacaoOS = "AMBOS";
			} else if (situacaoOS.equals("" + ConstantesSistema.SITUACAO_REFERENCIA_PENDENTE)) {
				situacaoOS = "PENDENTES";
			} else {
				situacaoOS = "ENCERRADOS";
			}

			Iterator colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator = colecaoOSRelatorioAcompanhamentoExecucaoHelper
					.iterator();

			OSRelatorioAcompanhamentoExecucaoHelper osRelatorioAcompanhamentoExecucaoHelper = null;

			String tipoServicoAnterior = null;

			int totalServico = 0;

			int totalGeral = 0;

			while (colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator
					.hasNext()) {

				osRelatorioAcompanhamentoExecucaoHelper = (OSRelatorioAcompanhamentoExecucaoHelper) colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator
						.next();

				totalServico = totalServico + 1;

				totalGeral = totalGeral + 1;

				// Prepara os campos na formata��o correta ou passando-os para
				// String
				// para ser colocado no Bean do relat�rio

				// Tipo de Servi�o
				String tipoServico = "";
				if (osRelatorioAcompanhamentoExecucaoHelper.getIdServicoTipo() != null) {
					tipoServico = osRelatorioAcompanhamentoExecucaoHelper
							.getIdServicoTipo().toString()
							+ " - "
							+ osRelatorioAcompanhamentoExecucaoHelper
									.getDescricaoServicoTipo();
				}

				// Id do RA
				String idRA = "";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getIdRegistroAtendimento() != null) {
					idRA = osRelatorioAcompanhamentoExecucaoHelper
							.getIdRegistroAtendimento().toString();
				}

				// Origem
				String origem = "";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getIdUnidadeAtendimento() != null) {
					origem = osRelatorioAcompanhamentoExecucaoHelper
							.getIdUnidadeAtendimento().toString()
							+ " - "
							+ osRelatorioAcompanhamentoExecucaoHelper
									.getNomeUnidadeAtendimento();
				}

				// Data Solicita��o
				String dataSolicitacao = "";

				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataSolicitacao() != null) {
					dataSolicitacao = Util
							.formatarData(osRelatorioAcompanhamentoExecucaoHelper
									.getDataSolicitacao());
				}

				// Data da Programa��o
				String dataProgramacao = "";

				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataProgramacao() != null) {
					dataProgramacao = Util
							.formatarData(osRelatorioAcompanhamentoExecucaoHelper
									.getDataProgramacao());
				}

				// Data de Encerramento
				String dataEncerramento = "";

				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataEncerramento() != null) {
					dataEncerramento = Util
							.formatarData(osRelatorioAcompanhamentoExecucaoHelper
									.getDataEncerramento());
				}

				// Dias de Atraso
				String diasAtraso = "";

				// Verifica se o RA foi encerrado
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getDataEncerramentoRA() == null) {

					// Verifica se a data do RA � diferente de nulo
					if (osRelatorioAcompanhamentoExecucaoHelper
							.getDataSolicitacao() != null) {

						// Verifica se a quantidade de dias de prazo � diferente
						// de nulo
						if (osRelatorioAcompanhamentoExecucaoHelper
								.getDiasPrazo() != null) {

							Date dataAtual = new Date();
							int diasPrazo = osRelatorioAcompanhamentoExecucaoHelper
									.getDiasPrazo().intValue();
							Date dataPrevista = Util
									.adicionarNumeroDiasDeUmaData(
											osRelatorioAcompanhamentoExecucaoHelper
													.getDataSolicitacao(),
											diasPrazo);

							// Verifica se a data atual � posterior a data
							// prevista
							if (Util.compararData(dataAtual, dataPrevista) == 1) {
								diasAtraso = ""
										+ Util
												.obterQuantidadeDiasEntreDuasDatas(
														dataPrevista, dataAtual);
							}
						}
					}
				}

				if (tipoServicoAnterior == null) {
					tipoServicoAnterior = tipoServico;
				}

				if (!tipoServicoAnterior.equalsIgnoreCase(tipoServico)) {
					tipoServicoAnterior = tipoServico;
					totalServico = 1;
				}
				
				// Id Imovel
				String idImovel = "*";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getIdImovel() != null) {
					idImovel = osRelatorioAcompanhamentoExecucaoHelper
							.getIdImovel().toString();
				}
				
				// Nome Cliente Usu�rio
				String nomeClienteUsuario = "*";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getNomeClienteUsuario() != null) {
					nomeClienteUsuario = osRelatorioAcompanhamentoExecucaoHelper
							.getNomeClienteUsuario();
				}
				
				// Cliente Fone Padr�o 
				String clienteFonePadrao = "*";
				if (osRelatorioAcompanhamentoExecucaoHelper
						.getClienteFonePadrao() != null) {
					clienteFonePadrao = osRelatorioAcompanhamentoExecucaoHelper
							.getClienteFonePadrao();
				}

				if (colecaoOSRelatorioAcompanhamentoExecucaoHelperIterator
						.hasNext()) {

					relatorioBean = new RelatorioAcompanhamentoExecucaoOSBean(

					// N�mero OS
							osRelatorioAcompanhamentoExecucaoHelper
									.getIdOrdemServico().toString(),

							// Unidade Atual
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeUnidadeAtual(),

							// Situa��o da OS
							situacaoOS,

							// Tipo de Servi�o
							tipoServico,

							// N�mero do RA
							idRA,

							// Endere�o
							osRelatorioAcompanhamentoExecucaoHelper
									.getEndereco(),

							// Origem
							origem,

							// Data Solicita��o
							dataSolicitacao,

							// Data Programa��o
							dataProgramacao,

							// Data Encerramento
							dataEncerramento,

							// Equipe
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeEquipe(),

							// Dias de Atraso
							diasAtraso,

							// Quantidade de OS por Tipo de Servi�o (TOTAL)
							"" + totalServico,

							// Quantidade de OS no relat�rio (TOTAL GERAL)
							"",
							
							//Id Imovel
							idImovel,
							
							//Nome Cliente Usu�rio
							nomeClienteUsuario,
							
							//Cleiente Telefone Pad�o 
							clienteFonePadrao);

				} else {
					
					relatorioBean = new RelatorioAcompanhamentoExecucaoOSBean(

					// N�mero OS
							osRelatorioAcompanhamentoExecucaoHelper
									.getIdOrdemServico().toString(),

							// Unidade Atual
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeUnidadeAtual(),

							// Situa��o da OS
							osRelatorioAcompanhamentoExecucaoHelper
									.getSituacaoOS(),

							// Tipo de Servi�o
							tipoServico,

							// N�mero do RA
							idRA,

							// Endere�o
							osRelatorioAcompanhamentoExecucaoHelper
									.getEndereco(),

							// Origem
							origem,

							// Data Solicita��o
							dataSolicitacao,

							// Data Programa��o
							dataProgramacao,

							// Data Encerramento
							dataEncerramento,

							// Equipe
							osRelatorioAcompanhamentoExecucaoHelper
									.getNomeEquipe(),

							// Dias de Atraso
							diasAtraso,

							// Quantidade de OS por Tipo de Servi�o (TOTAL)
							"" + totalServico,

							// Quantidade de OS no relat�rio (TOTAL GERAL)
							"" + totalGeral,
							//Id Imovel
							idImovel,
							
							//Nome Cliente Usu�rio
							nomeClienteUsuario,
							
							//Cleiente Telefone Pad�o 
							clienteFonePadrao);
				}

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);

			}
		}

		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// Per�odo de Atendimento
		String periodoAtendimento = "";

		if (periodoAtendimentoInicial != null) {
			String periodoAtendimentoInicialFormatado = Util
					.formatarData(periodoAtendimentoInicial);
			String periodoAtendimentoFinalFormatado = Util
					.formatarData(periodoAtendimentoFinal);
			periodoAtendimento = periodoAtendimentoInicialFormatado + " a "
					+ periodoAtendimentoFinalFormatado;
			parametros.put("periodoAtendimento", periodoAtendimento);
		} else {
			parametros.put("periodoAtendimento", periodoAtendimento);
		}

		// Per�odo de Encerramento
		String periodoEncerramento = "";

		if (periodoEncerramentoInicial != null) {
			String periodoEncerramentoInicialFormatado = Util
					.formatarData(periodoEncerramentoInicial);
			String periodoEncerramentoFinalFormatado = Util
					.formatarData(periodoEncerramentoFinal);
			periodoEncerramento = periodoEncerramentoInicialFormatado + " a "
					+ periodoEncerramentoFinalFormatado;
			parametros.put("periodoEncerramento", periodoEncerramento);
		} else {
			parametros.put("periodoEncerramento", periodoEncerramento);
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ACOMPANHAMENTO_EXECUCAO_OS,
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

		int retorno = 0;

		String origemServico = (String) getParametro("origemServico");
		String situacaoOS = (String) getParametro("situacaoOS");
		String[] idsServicosTipos = (String[]) getParametro("idsServicosTipos");
		String idUnidadeAtendimento = (String) getParametro("idUnidadeAtendimento");
		String idUnidadeAtual = (String) getParametro("idUnidadeAtual");
		String idUnidadeEncerramento = (String) getParametro("idUnidadeEncerramento");
		Date periodoAtendimentoInicial = (Date) getParametro("periodoAtendimentoInicial");
		Date periodoAtendimentoFinal = (Date) getParametro("periodoAtendimentoFinal");
		Date periodoEncerramentoInicial = (Date) getParametro("periodoEncerramentoInicial");
		Date periodoEncerramentoFinal = (Date) getParametro("periodoEncerramentoFinal");
		String idEquipeProgramacao = (String) getParametro("idEquipeProgramacao");
		String idEquipeExecucao = (String) getParametro("idEquipeExecucao");

		retorno = fachada.pesquisarOSGerarRelatorioAcompanhamentoExecucaoCount(
				origemServico, situacaoOS, idsServicosTipos,
				idUnidadeAtendimento, idUnidadeAtual, idUnidadeEncerramento,
				periodoAtendimentoInicial, periodoAtendimentoFinal,
				periodoEncerramentoInicial, periodoEncerramentoFinal,
				idEquipeProgramacao, idEquipeExecucao);

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoExecucaoOS",
				this);
	}
}