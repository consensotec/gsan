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

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.arrecadacao.pagamento.GuiaPagamentoItemCategoria;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;

/**
 * Interface para o reposit�rio batch
 * 
 * @author Rodrigo Silveira
 * @created 15/08/2006
 */
public interface IRepositorioBatch {
	public Collection pesquisarRotasProcessamentoBatchFaturamentoComandado(
			Integer idFaturamentoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * Encerra os Processos Iniciados no sistema quando todas as funcionalidades
	 * do mesmo finalizarem a execu��o
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosProntosParaEncerramento()
			throws ErroRepositorioException;

	/**
	 * Encerra as Funcionalidades Iniciadas no sistema quando todas as unidades
	 * de processamento do mesmo finalizarem a execu��o
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasProntasParaEncerramento()
			throws ErroRepositorioException;

	/**
	 * Busca as Funcionalidades Iniciadas no sistema que falharam para marcar o
	 * Processo Iniciado como falho
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/08/2006
	 * 
	 */
	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosExecucaoFalha()
			throws ErroRepositorioException;

	/**
	 * Busca as Unidades Iniciadas no sistema que falharam para marcar o
	 * Funcionalidade Iniciada como falha
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/08/2006
	 * 
	 */
	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasExecucaoFalha()
			throws ErroRepositorioException;

	/**
	 * Busca as Funcionalidades Iniciadas no sistema que est�o prontas para
	 * execu��o
	 * 
	 * @author Rodrigo Silveira
	 * @date 29/08/2006
	 * 
	 */
	public Collection<Object[]> pesquisarFuncionaldadesIniciadasProntasExecucao()
			throws ErroRepositorioException;

	/**
	 * Verifica se a FuncionalidadeIniciada foi concluida com erro para evitar a
	 * execu��o da UnidadeIniciada relacionada
	 * 
	 * @author Rodrigo Silveira
	 * @date 01/09/2006
	 * 
	 */
	public int pesquisarFuncionaldadesIniciadasConcluidasErro(
			int idFuncionalidadeIniciada) throws ErroRepositorioException;

	/**
	 * Inseri uma cole��o de objetos gen�ricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos)
			throws ErroRepositorioException;
	
	/**
	 * Inseri uma cole��o de objetos gen�ricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Bruno Barros
	 * @date 27/04/2007
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchGerencial(Collection<? extends Object> colecaoObjetos)
			throws ErroRepositorioException;	

	/**
	 * Atualiza uma cole��o de objetos gen�ricos na base com um flush para cada
	 * 50 registros inseridos.
	 * 
	 * @author Leonardo Vieira
	 * @date 12/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void atualizarColecaoObjetoParaBatch(
			Collection<? extends Object> colecaoObjetos) throws ErroRepositorioException;

	/**
	 * Verifica se a Funcionalidade Iniciada no sistema que est� na ordem
	 * correta de execu��o dentro do processoFuncionalidade, as funcionalidades
	 * s� podem iniciar se estiverem na ordem correta do sequencial de execu��o
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/09/2006
	 * 
	 */
	public Integer pesquisarQuantidadeFuncionaldadesIniciadasForaOrdemExecucao(
			int idSequencialExecucao, int idProcessoIniciado)
			throws ErroRepositorioException;

	/**
	 * Inicia uma funcionalidade iniciada de um relat�rio
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada) throws ErroRepositorioException;

	/**
	 * Inicia uma processo iniciado de um relat�rio
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada)
			throws ErroRepositorioException;

	/**
	 * Encerra uma funcionalidade iniciada de um relat�rio
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void encerrarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada, int situacaoConclusaoFuncionalidade)
			throws ErroRepositorioException;

	/**
	 * Inicia uma processo iniciado de um relat�rio
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void encerrarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada,
			int situacaoConclusaoFuncionalidade)
			throws ErroRepositorioException;

	/**
	 * Inicia todos os relat�rios agendados
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public Collection<byte[]> iniciarRelatoriosAgendados()
			throws ErroRepositorioException;

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relat�rios
	 * batch do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 09/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchSistema()
			throws ErroRepositorioException;

	/**
	 * Remove uma cole��o de objetos gen�ricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos)
			throws ErroRepositorioException;

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relat�rios
	 * batch do sistema por Usu�rio
	 * 
	 * @author Rodrigo Silveira
	 * @date 25/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(
			int idProcesso) throws ErroRepositorioException;

	/**
	 * Remove do sistema todos os relat�rios batch que est�o na data de
	 * expira��o
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/10/2006
	 * 
	 */
	public void deletarRelatoriosBatchDataExpiracao(Date dataDeExpiracao)
			throws ErroRepositorioException;
	
	public Collection<Rota> pesquisarRotasProcessamentoBatchCobrancaGrupoNaoInformado(
			Integer idCobrancaAcaoAtividadeComando)
			throws ErroRepositorioException; 

	
	/**
	 * Inseri uma objeto gen�rico na base 
	 * 
	 * @author Marcio Roberto
	 * @date 18/05/2007
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatchGerencial(Object objeto) throws ErroRepositorioException; 
	
	/**
	 * Remove do sistema as unidades iniciadas de uma funcionalidade
	 * 
	 * @author Rafael Corr�a
	 * @date 06/11/2006
	 * 
	 */
	public void removerUnidadesIniciadas(Integer idFuncionalidadeIniciada)
			throws ErroRepositorioException;

	/**
	 * Remove uma cole��o de GuiaPagamentoCategoria
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamentoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoCategoriaParaBatch(Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de ClienteGuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoClienteGuiaPagamentoParaBatch(Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de GuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoParaBatch(Collection<GuiaPagamento> colecaoGuiaPagamento) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de DebitoACobrar
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoACobrarParaBatch(Collection<DebitoACobrar> colecaoDebitoACobrar) throws ErroRepositorioException ;

	/**
	 * Remove uma cole��o de DebitoACobrarCategoria
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrarCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoACobrarCategoriaParaBatch(Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de Pagamento
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoPagamentoParaBatch(Collection<Pagamento> colecaoPagamento) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de Devolu��o
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDevolucao
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDevolucaoParaBatch(Collection<Devolucao> colecaoDevolucao) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de Conta
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoConta
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta) throws ErroRepositorioException ;

	/**
	 * Remove uma cole��o de ContaCategoria
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaCategoriaParaBatch(Collection<ContaCategoria> colecaoContaCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de CreditoRealizado
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizado
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoRealizadoParaBatch(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ErroRepositorioException ;

	/**
	 * Remove uma cole��o de DebitoCobrado
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobrado
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoCobradoParaBatch(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de ContaImpostosDeduzidos
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaImpostosDeduzidos
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaImpostosDeduzidosParaBatch(Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de ClienteConta
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteConta
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoClienteContaParaBatch(Collection<ClienteConta> colecaoClienteConta) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de ContaCategoriaConsumoFaixa
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa) throws ErroRepositorioException ;

	/**
	 * Remove uma cole��o de DebitoCobradoCategoria
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobradoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoCobradoCategoriaParaBatch(Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma cole��o de CreditoRealizadoCategoria
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizadoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoRealizadoCategoriaParaBatch(Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria) throws ErroRepositorioException ;
	
	/**
	 * Pesquisa e registra as queries demoradas do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 27/02/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public void pesquisarQueriesDemoradasSistema()
			throws ErroRepositorioException; 
	
	/**
	 * Remove uma cole��o de CreditoARealizar
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoARealizar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoARealizarParaBatch(Collection<CreditoARealizar> colecaoCreditoARealizar) throws ErroRepositorioException ;

	/**
	 * Remove uma cole��o de CreditoARealizarCategoria
	 * 
	 * [UC0276] Encerrar Arrecada��o do M�s
	 * 
	 * @author Pedro Alexandre
	 * @date 09/04/2008
	 * 
	 * @param colecaoCreditoARealizarCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoARealizarCategoriaParaBatch(Collection<Integer> colecaoIdsCreditoARealizar) throws ErroRepositorioException ;

	public void inserirLogExcecaoFuncionalidadeIniciada(UnidadeIniciada unidadeIniciada, Throwable excecao) 
		throws ErroRepositorioException;
	
	/**
	 * Inseri uma cole��o de objetos gen�ricos na base
	 * 
	 * @author Rafael Pinto
	 * @date 20/05/2008
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchTransacao(Collection<Object> colecaoObjetos)
			throws ErroRepositorioException ;

	public void atualizarSituacaoFuncionalidadeIniciadaConcluida(FuncionalidadeIniciada funcionalidadeIniciada) throws ErroRepositorioException;

	/**
	 * Verifica se o processo est� em execu��o
	 * 
	 * @author Ana Maria
	 * @date 18/12/2008
	 * 
	 */
	public boolean verificarProcessoEmExecucao(Integer idProcesso) throws ErroRepositorioException;

	public boolean validarAutorizacaoInserirRelatorioBatch(Usuario usuario, int idProcesso) throws ErroRepositorioException;
	
	/**
	 * Autoriza Processo Iniciado
	 * 
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */
	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado, Integer processoSituacao) throws ErroRepositorioException;
	
	/**
	 * Autoriza Processo Iniciado
	 * 
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */
	public void autorizarFuncionalidadeIniciada(ProcessoIniciado processoIniciado,Integer funcionalidadeSituacao) throws ErroRepositorioException;
	
	/**
	 * Atualiza um objeto gen�rico na base 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param objetoParaAtualizar
	 * @throws ErroRepositorioException
	 */
	public void atualizarObjetoParaBatch(
			Object objetoParaAtualizar) throws ErroRepositorioException;
	
	/**
	 * Insere uma objeto gen�rico na base 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatch(Object objeto)
			throws ErroRepositorioException ;
	
	/**
	 * Remove uma cole��o de objetos gen�ricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author S�vio Luiz
	 * @date 31/03/2010
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerObjetoParaBatch(Object objeto)
			throws ErroRepositorioException;
	
	/**
	 * Retorna o(s) processo(s) que est� em execu��o
	 * 
	 * @author Arthur Carvalho
	 * @date 04/06/2010
	 * 
	 */
	public Collection retornaProcessoFuncionalidadeEmExecucao() throws ErroRepositorioException ;
	
	
	/**
	 * Retorna a data/hora em que um processo foi finalizado em um determinado m�s. 
	 * 
	 * @author Hugo Azevedo
	 * @date 17/10/2011
	 */
	
	public Date obterDataEncerramentoProcesso(Integer idProcesso, String anoMes) throws ErroRepositorioException;
	
	/**
	 * Gerar Declara��o de Quita��o Anual de D�bitos
	 * 
	 * @author Raphael Rossiter
	 * @date 02/03/2012
	 * 
	 * @param idFaturamentoGrupo
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeRotasAtivasFaturamentoGrupo(Integer idFaturamentoGrupo)
			throws ErroRepositorioException ;
	
	/**
	 * Verificar se a funcionalidade de gerar dados di�rios de arrecada��o esta executando
	 * 
	 * @author Rodrigo Cabral
	 * @date 07/12/2011
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarExecucaoFuncionalidade(Integer idFuncionalidade)
			throws ErroRepositorioException;
	
	/**
	 * Verificar se o processo esta executando
	 * 
	 * @author Rodrigo Cabral
	 * @date 30/03/2012
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */	
	public Integer verificarExecucaoProcesso(Integer idFuncionalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * Pesquisa uma colecaode GuiaPagamentoItem
	 * 
	 * @author Rafael Corr�a
	 * @date 29/10/2014
	 * 
	 * @param colecaoGuiaPagamentoItem
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoItemParaBatch(Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * Remove uma colecao de GuiaPagamentoItemCategoria
	 * 
	 * @author Rafael Corr�a
	 * @date 29/10/2014
	 * 
	 * @param colecaoGuiaPagamentoItemCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoItemCategoriaParaBatch(
			Collection<GuiaPagamentoItemCategoria> colecaoGuiaPagamentoItemCategoria) 
			throws ErroRepositorioException;
	
}
