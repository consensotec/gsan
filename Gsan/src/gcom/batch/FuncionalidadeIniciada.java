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
package gcom.batch;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.financeiro.ControladorFinanceiroLocal;
import gcom.financeiro.ControladorFinanceiroLocalHome;
import gcom.financeiro.ParametrosDevedoresDuvidosos;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.spcserasa.ControladorSpcSerasaLocal;
import gcom.spcserasa.ControladorSpcSerasaLocalHome;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.tarefa.TarefaBatch;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.IoUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.CreateException;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FuncionalidadeIniciada implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataHoraInicio;

	/** nullable persistent field */
	private Date dataHoraTermino;

	/** persistent field */
	private gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao;

	/** persistent field */
	private gcom.batch.ProcessoIniciado processoIniciado;

	/** persistent field */
	private gcom.batch.ProcessoFuncionalidade processoFuncionalidade;

	/** persistent field */
	private Set unidadesIniciadas;

	/** persistent field */
	private Set relatoriosGerados;

	private byte[] tarefaBatch;
	
	private String descricaoExcecao;

	public String getDescricaoExcecao() {
		return descricaoExcecao;
	}

	public void setDescricaoExcecao(String descricaoExcecao) {
		this.descricaoExcecao = descricaoExcecao;
	}

	/** full constructor */
	public FuncionalidadeIniciada(Date dataHoraInicio, Date dataHoraTermino,
			gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao,
			gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoFuncionalidade processoFuncionalidade,
			Set unidadesIniciadas, Set relatoriosGerados) {
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraTermino = dataHoraTermino;
		this.funcionalidadeSituacao = funcionalidadeSituacao;
		this.processoIniciado = processoIniciado;
		this.processoFuncionalidade = processoFuncionalidade;
		this.unidadesIniciadas = unidadesIniciadas;
		this.relatoriosGerados = relatoriosGerados;
	}

	/** default constructor */
	public FuncionalidadeIniciada() {
	}

	/** minimal constructor */
	public FuncionalidadeIniciada(
			gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao,
			gcom.batch.ProcessoIniciado processoIniciado,
			gcom.batch.ProcessoFuncionalidade processoFuncionalidade,
			Set unidadesIniciadas, Set relatoriosGerados) {
		this.funcionalidadeSituacao = funcionalidadeSituacao;
		this.processoIniciado = processoIniciado;
		this.processoFuncionalidade = processoFuncionalidade;
		this.unidadesIniciadas = unidadesIniciadas;
		this.relatoriosGerados = relatoriosGerados;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHoraInicio() {
		return this.dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return this.dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

	public gcom.batch.FuncionalidadeSituacao getFuncionalidadeSituacao() {
		return this.funcionalidadeSituacao;
	}

	public void setFuncionalidadeSituacao(
			gcom.batch.FuncionalidadeSituacao funcionalidadeSituacao) {
		this.funcionalidadeSituacao = funcionalidadeSituacao;
	}

	public gcom.batch.ProcessoIniciado getProcessoIniciado() {
		return this.processoIniciado;
	}

	public void setProcessoIniciado(gcom.batch.ProcessoIniciado processoIniciado) {
		this.processoIniciado = processoIniciado;
	}

	public gcom.batch.ProcessoFuncionalidade getProcessoFuncionalidade() {
		return this.processoFuncionalidade;
	}

	public void setProcessoFuncionalidade(
			gcom.batch.ProcessoFuncionalidade processoFuncionalidade) {
		this.processoFuncionalidade = processoFuncionalidade;
	}

	public Set getUnidadesIniciadas() {
		return this.unidadesIniciadas;
	}

	public void setUnidadesIniciadas(Set unidadesIniciadas) {
		this.unidadesIniciadas = unidadesIniciadas;
	}

	public Set getRelatoriosGerados() {
		return this.relatoriosGerados;
	}

	public void setRelatoriosGerados(Set relatoriosGerados) {
		this.relatoriosGerados = relatoriosGerados;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public byte[] getTarefaBatch() {
		return tarefaBatch;
	}

	public void setTarefaBatch(byte[] tarefaBatch) {
		this.tarefaBatch = tarefaBatch;
	}

	public String getDiferencaInicioTermino() {
		String retorno = "";
		if (this.dataHoraTermino != null) {
			long diferencaEmMilisegundos = this.dataHoraTermino.getTime()
					- this.dataHoraInicio.getTime();

			long diferencaEmHoras = diferencaEmMilisegundos / (60 * 60 * 1000);

			long diferencaEmMinutos = (diferencaEmMilisegundos / (60 * 1000)) % 60;

			long diferencaEmSegundos = (diferencaEmMilisegundos / (1000)) % 60;

			if (diferencaEmHoras < 10) {
				retorno = "0" + diferencaEmHoras;
			} else {
				retorno = "" + diferencaEmHoras;
			}

			if (diferencaEmMinutos < 10) {
				retorno = retorno + ":0" + diferencaEmMinutos;
			} else {
				retorno = retorno + ":" + diferencaEmMinutos;
			}

			if (diferencaEmSegundos < 10) {
				retorno = retorno + ":0" + diferencaEmSegundos;
			} else {
				retorno = retorno + ":" + diferencaEmSegundos;
			}

		}
		return retorno;
	}

	public void finalizador() {
		
          // 29/10/2008 - Yara T. Souza
		  // Estes prints foram colocados para verificar os casos em que os processos n�o finalizam.
		  System.out.println("Entrou no finalizador ");
		
		
		try {
			SistemaParametro sistemaParametros = getControladorUtil()
					.pesquisarParametrosDoSistema();

			TarefaBatch tarefaBatch = (TarefaBatch) IoUtil
					.transformarBytesParaObjeto(this.tarefaBatch);

			 // 29/10/2008 - Yara T. Souza
			System.out.println("Entrou na Funcionalidade: " + this.processoFuncionalidade.getFuncionalidade().getId() + "-" + this.processoFuncionalidade.getFuncionalidade().getDescricao());
			
			switch (this.processoFuncionalidade.getFuncionalidade().getId()) {

				case Funcionalidade.GERAR_DADOS_PARA_LEITURA :

					Integer anoMesFaturamentoGrupoLeitura = (Integer) tarefaBatch
							.getParametro("anoMesFaturamentoGrupo");

					Integer idFaturamentoGrupo = (Integer) tarefaBatch
							.getParametro("idFaturamentoGrupo");

					// atualiza a data e a hora da realiza��o da atividade com a
					// data e
					// a hora correntes

					getControladorFaturamento()
							.atualizarDataHoraRealizacaoAtividade(
									FaturamentoAtividade.GERAR_ARQUIVO_LEITURA,
									anoMesFaturamentoGrupoLeitura,
									idFaturamentoGrupo);

					break;

				case Funcionalidade.FATURAR_GRUPO_FATURAMENTO :

					FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) tarefaBatch
							.getParametro("faturamentoGrupo");
					Integer atividade = (Integer) tarefaBatch
							.getParametro("atividade");
					
					System.out
							.println("**********************ENTROU PARA ATUALIZAR ANOMES:  "
									+ faturamentoGrupo.getAnoMesReferencia()
									+ "  ********************");
					
					

					/*
					 * Caso a atividade que esteja sendo executada, corresponda
					 * a faturar grupo, atualiza o ano/m�s de refer�ncia do
					 * faturamento para o m�s seguinte.
					 */
					if (atividade == FaturamentoAtividade.FATURAR_GRUPO
							.intValue()) {
						getControladorFaturamento()
								.atualizarAnoMesReferenciaFaturamentoGrupo(
										faturamentoGrupo,
										faturamentoGrupo.getAnoMesReferencia(), atividade);
					}

					break;

				case Funcionalidade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS :

					FaturamentoGrupo faturamentoGrupoConsistirLeituras = (FaturamentoGrupo) tarefaBatch
							.getParametro("faturamentoGrupo");
					
	
					System.out
							.println("**********************ENTROU PARA ATUALIZAR DATAHORA REALIZA��O DA ATIVIDADE:  "
									+ faturamentoGrupoConsistirLeituras.getId()
											.toString()
									+ "********************");

					getControladorFaturamento()
							.atualizarDataHoraRealizacaoAtividade(
									FaturamentoAtividade.CONSISTIR_LEITURAS_E_CALCULAR_CONSUMOS,
									faturamentoGrupoConsistirLeituras.getAnoMesReferencia(),
									faturamentoGrupoConsistirLeituras.getId());

					break;

				case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES :

					/**
					 * Item 12 do batch de encerrar faturamento do m�s.
					 */
					// recupera o ano/m�s de refer�ncia do faturamento
					int anoMesFaturamentoSistemaParametro = sistemaParametros
							.getAnoMesFaturamento();

					getControladorFaturamento().atualizarAnoMesFaturamento(
							anoMesFaturamentoSistemaParametro);

					break;

				case Funcionalidade.GERAR_HISTORICO_PARA_ENCERRAR_ARRECADACAO_MES :

					/**
					 * Item 11 do batch de encerrar arrecada��o do m�s.
					 */
					// recupera o ano/m�s de refer�ncia da arrecada��o
					int anoMesArrecadacaoSistemaParametro = sistemaParametros
							.getAnoMesArrecadacao();

					getControladorArrecadacao().atualizarAnoMesArrecadacao(
							anoMesArrecadacaoSistemaParametro);

					break;
					
					
				case Funcionalidade.GERAR_RESUMO_DEVEDORES_DUVIDOSOS :

					Integer anoMesReferenciaContabil = (Integer) tarefaBatch.getParametro("anoMesReferenciaContabil");
					ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = getControladorFinanceiro().pesquisarParametrosDevedoresDuvidosos(anoMesReferenciaContabil);
					parametrosDevedoresDuvidosos.setDataProcessamento(new Date());
					this.getControladorUtil().atualizar(parametrosDevedoresDuvidosos);
					
					System.out.println("ENTROU PARA ATUALIZAR ANOMES:  " + anoMesReferenciaContabil);
					

					break;
					
				case Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO :

					sistemaParametros.setDataHoraDadosDiariosArrecadacao(new Date());

					System.out
							.println("**********************ENTROU PARA ATUALIZAR DATAHORA DA GERA��O DOS DADOS********************");

					getControladorUtil().atualizar(sistemaParametros);

					break;
					
				case Funcionalidade.INSERIR_RESUMO_ACOES_COBRANCA_CRONOGRAMA :

					sistemaParametros.setDataHoraResumoAcoesCobranca(new Date());

					System.out.println("**********************ENTROU PARA ATUALIZAR DATAHORA DO RESUMO DE A��ES DE COBRAN�A ********************");

					getControladorUtil().atualizar(sistemaParametros);

					break;
					
					
				case Funcionalidade.GERAR_RESUMO_DIARIO_NEGATIVACAO :					
					
//					sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
//			     	Integer numeroExecucao = sistemaParametro.getNumeroExecucaoResumoNegativacao() + 1;			     	
//					
//					 // [UC0688] Gerar Resumo Di�rio da Negativa��o.
//			        //-------------------------------------------------------------------------------------------
//					// Alterado por :  Yara Taciane  - data : 08/07/2008 
//					// Analista :  F�tima Sampaio
//			        //-------------------------------------------------------------------------------------------
//					
//					//O sistema atualiza o n�mero da execu��o do resumo da negativa��o na tabela SISTEMA_PARAMETROS mais um).
//					sistemaParametro.setNumeroExecucaoResumoNegativacao(numeroExecucao);
//					sistemaParametro.setUltimaAlteracao(new Date());
//					this.getControladorUtil().atualizarSistemaParametro(sistemaParametro);
//					System.out.println(" Fim --- > altualizado Sistema Parametro = "  + sistemaParametro.getNumeroExecucaoResumoNegativacao());
//					//-------------------------------------------------------------------------------------------
//						
					
//					System.out.println("**********************ENTROU  GERAR MOV. DE EXCLUS�O NEGATIVADORES********************");
//					Collection coll = getControladorSpcSerasa().consultarNegativadoresParaExclusaoMovimento();
//					Integer[] idNegat = new  Integer[coll.size()] ; 
//					Iterator it = coll.iterator();
//					int i = 0;
//					while(it.hasNext()){
//						Negativador negativador = (Negativador)it.next();
//						idNegat[i]= negativador.getId();
//						i++;
//					}
//				    Collection collRetorno = getControladorSpcSerasa().gerarMovimentoExclusaoNegativacao(idNegat);
//				    if(collRetorno != null){
//				    	System.out.println("---> retornou da Exclus�o <----");
//				    }							
				    
					System.out.println(" Fim do Resumo Di�rio! ");

					break;		
					
				case Funcionalidade.EXECUTAR_COMANDO_DE_NEGATIVACAO :
					NegativacaoComando nComando = (NegativacaoComando) tarefaBatch.getParametro("nComando");
					Negativador neg = (Negativador) tarefaBatch.getParametro("neg");
									
					Object[] obj = null;
					Integer qtd = null;
					
					//4.2 Caso o comando n�o seja uma simula��o
					if (nComando.getIndicadorSimulacao() == NegativacaoComando.NAO_SIMULACAO) { 
						// 4.2.1 [SB0012] - Gerar Arquivo TXT para envio ao negativador

						obj = getControladorSpcSerasa().pesquisarQuantidadeInclusaoNegativacao(nComando.getId());
						qtd = getControladorSpcSerasa().pesquisarQuantidadeInclusaoItemNegativacao(nComando.getId());
						
						//Soma o registro do trailler
						Integer qtdRegistro = (Integer)obj[2] + 1;
						this.getControladorSpcSerasa().gerarArquivoInclusao(nComando.getId(),(Integer)obj[3],neg.getId());

						FiltroNegativadorMovimento fnm = new FiltroNegativadorMovimento();
						fnm.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID,(Integer)obj[3]));
						Collection colecaoNegativadorMovimento  = getControladorUtil().pesquisar(fnm,NegativadorMovimento.class.getName());
						NegativadorMovimento nm = null;
						if (colecaoNegativadorMovimento != null && !colecaoNegativadorMovimento.isEmpty()) {
							nm = (NegativadorMovimento)colecaoNegativadorMovimento.iterator().next();
						}
						Negativador n = nComando.getNegativador();

						//4.2.2
						NegativadorContrato nContrato = getControladorSpcSerasa().consultarNegativadorContratoVigente(n.getId());
						nContrato.setNumeroSequencialEnvio(nm.getNumeroSequencialEnvio());
						if(nContrato.getNumeroInclusoesEnviadas() != null){
						  nContrato.setNumeroInclusoesEnviadas(nContrato.getNumeroInclusoesEnviadas() + (Integer)obj[0]);
						}else{
						  nContrato.setNumeroInclusoesEnviadas((Integer)obj[0]);	  
						}
						getControladorUtil().atualizar(nContrato);

						//4.2.3
						nm.setNumeroRegistrosEnvio(qtdRegistro);
						nm.setValorTotalEnvio((BigDecimal)obj[1]);
						
						getControladorUtil().atualizar(nm);

					}else{
						obj = getControladorSpcSerasa().pesquisarQuantidadeInclusaoNegativacaoSimulacao(nComando.getId());
						qtd = (Integer)obj[2];
					}
					
					//4.1 
					nComando.setDataHoraRealizacao(new Date());
					nComando.setQuantidadeInclusoes((Integer)obj[0]);
					nComando.setValorDebito((BigDecimal)obj[1]);
					nComando.setQuantidadeItensIncluidos(qtd);
					nComando.setUltimaAlteracao(new Date());

					getControladorUtil().atualizar(nComando);
					
					NegativacaoComando.resetQuantidadeImoveisJaIncluidos();
					NegativadorMovimentoReg.resetNumeroProximoRegistro();
					
					System.out.println("**********************SAIU  GERAR MOV. DE INCLUS�O NEGATIVADORES********************");

					break;	
					
				case Funcionalidade.GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA :

					Collection colecaoComandoEmpresaCobrancaContaExtensao = (Collection) tarefaBatch
						.getParametro("colecaoComandoEmpresaCobrancaContaExtensao");

						if (colecaoComandoEmpresaCobrancaContaExtensao != null
								&& !colecaoComandoEmpresaCobrancaContaExtensao
										.isEmpty()) {
		
							Iterator it = colecaoComandoEmpresaCobrancaContaExtensao
									.iterator();
		
							while (it.hasNext()) {
								
								ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao = 
									(ComandoEmpresaCobrancaContaExtensao) it.next();
		
								comandoEmpresaCobrancaContaExtensao
										.setDataExecucao(new Date());
								
								comandoEmpresaCobrancaContaExtensao
										.setUltimaAlteracao(new Date());
		
								getControladorUtil().atualizar(
										comandoEmpresaCobrancaContaExtensao);
							}
						}
					break;
					
				case Funcionalidade.GERAR_ATIVIDADE_ACAO_COBRANCA :

			
					FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) tarefaBatch.getParametro("faturamentoAtividadeCronograma");
					
					System.out.println("********************** ENTROU PARA ATUALIZAR faturamentoAtividadeCronograma ********************");
					
					if(faturamentoAtividadeCronograma != null 
							&& faturamentoAtividadeCronograma.getId() != null){
						getControladorFaturamento(). atualizarFaturamentoAtividadeCronograma(
							faturamentoAtividadeCronograma.getId(), new Date());
					}

					break;
			}

			 // 29/10/2008 - Yara T. Souza
			  System.out.println("Saiu do finalizador ");
			
			
			// Se ocorrer algum erro com o finalizador a funcionalidadeIniciada
			// � marcada como CONCLUIDA_COM_ERRO
		} catch (ControladorException ex) {
			ex.printStackTrace();
			this.funcionalidadeSituacao
					.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
		} catch (IOException ex) {
			ex.printStackTrace();
			this.funcionalidadeSituacao
					.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			this.funcionalidadeSituacao
					.setId(FuncionalidadeSituacao.CONCLUIDA_COM_ERRO);
		}

	}

	/**
	 * Retorna o valor de controladorArrecadacao
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	/**
	 * Retorna o valor de controladorFinanceiro
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFinanceiroLocal getControladorFinanceiro() {
		ControladorFinanceiroLocalHome localHome = null;
		ControladorFinanceiroLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFinanceiroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FINANCEIRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	
	private ControladorSpcSerasaLocal getControladorSpcSerasa() {
		ControladorSpcSerasaLocalHome localHome = null;
		ControladorSpcSerasaLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorSpcSerasaLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_SPC_SERASA_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	
	
	
	
}
