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
package gcom.gerencial.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ResumoCobrancaSituacaoEspecial;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaContasGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaContasGerencialPorAnoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaCreditoARealizarGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaCreditoARealizarGerencialPorAnoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaDebitosACobrarGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaDebitosACobrarGerencialPorAnoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaGuiasPagamentoGerenciaHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface IRepositorioGerencialCobranca {

	/**
	 * 
	 * M�todo que consulta os ResumoSituacaoEspecialCobrancaHelper
	 * 
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getResumoSituacaoEspecialCobrancaHelper(int idLocalidade)
			throws ErroRepositorioException;

	/**
	 * M�todo que insere o ResumoSituacaoEspecialCobranca em batch
	 * 
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 * 
	 * @param listResumoFaturamentoSituacaoEspecialHelper
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoSituacaoEspecialCobranca(
			List<ResumoCobrancaSituacaoEspecial> list)
			throws ErroRepositorioException;

	/**
	 * M�todo que exclui todos os ResumoFaturamentoSituacaoEspecial
	 * 
	 * [CU0346] - Gerar Resumo de Situacao Especial de Cobran�a
	 * 
	 * @author Thiago Toscano
	 * @date 15/05/2006
	 * 
	 * @throws ErroRepositorioException
	 */
	public void excluirTodosResumoCobrancaSituacaoEspecial(int idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso gera o resumo da pend�ncia
	 * 
	 * [UC0335] Gerar Resumo da Pend�ncia
	 * 
	 * Gera a lista de conta da pend�ncia das Contas
	 * 
	 * gerarResumoPendenciaContas
	 * 
	 * @author Roberta Costa
	 * @date 15/05/2006
	 * 
	 * @param sistemaParametro
	 * @return retorno
	 * @throws ErroRepositorioException
	 */
	public List getResumoPendenciaContas(SistemaParametro sistemaParametro,
			Integer idLocalidade, Integer idSetorComercial)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso gera o resumo da pend�ncia
	 * 
	 * [UC0335] Gerar Resumo da Pend�ncia
	 * 
	 * Gera a lista de guias de pagamento da pend�ncia das Contas
	 * 
	 * getResumoPendenciaGuiasPagamento
	 * 
	 * @author Roberta Costa
	 * @date 17/05/2006
	 * 
	 * @param sistemaParametro
	 * @return retorno
	 * @throws ErroRepositorioException
	 */
	public List getResumoPendenciaGuiasPagamento(
			SistemaParametro sistemaParametro, Integer idLocalidade,
			Integer idSetorComercial) throws ErroRepositorioException;

	/**
	 * Este caso de uso permite consultar o resumo da pend�ncia, com a op��o de
	 * impress�o da consulta. Dependendo da op��o de totaliza��o sempre � gerado
	 * o relat�rio, sem a fera��o da consulta.
	 * 
	 * [UC0338] Consultar Resumo da Pend�ncia
	 * 
	 * Gera a lista de pend�ncias das Contas e Guias de Pagamento
	 * 
	 * consultarResumoPendencia
	 * 
	 * @author Roberta Costa
	 * @date 24/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarResumoPendencia(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso permite consultar o resumo da pend�ncia, com a op��o de
	 * impress�o da consulta. Dependendo da op��o de totaliza��o sempre � gerado
	 * o relat�rio, sem a fera��o da consulta.
	 * 
	 * [UC0338] Consultar Resumo da Pend�ncia
	 * 
	 * Verifica se existe registros para o ano/m�s refr�ncia
	 * 
	 * verificarExistenciaAnoMesReferenciaResumo
	 * 
	 * @author Roberta Costa
	 * @date 24/05/2006
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer  verificarExistenciaAnoMesReferenciaResumo(Integer anoMesReferencia,
			String resumo) throws ErroRepositorioException;
	
	public Integer  verificarExistenciaAnoMesReferenciaResumoPeriodo(Integer anoMesInicialReferencia,
			Integer anoMesFinalReferencia, String resumo) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaGerenciaRegionalHelper(
			ConsultarResumoSituacaoEspecialHelper helper)
			throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaUnidadeNegocioHelper(
			ConsultarResumoSituacaoEspecialHelper helper)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaLocalidadeHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException;
	
	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaSetorComercialHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaSitTipoHelper(
			ConsultarResumoSituacaoEspecialHelper helper) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarResumoCobrancaSituacaoEspecialConsultaMotivoHelper(
			ConsultarResumoSituacaoEspecialHelper helper)
			throws ErroRepositorioException;

	public Collection<BigDecimal> pesquisarResumoCobrancaSituacaoEspecialConsultaFatEstimadoHelper(ConsultarResumoSituacaoEspecialHelper helper, 
			int anoMesReferencia) throws ErroRepositorioException;

	public Integer pesquisarResumoCobrancaSituacaoEspecialConsultaQtLigacoesHelper(ConsultarResumoSituacaoEspecialHelper helper,
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcao(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException;
	
	public Collection consultarCobrancaAcaoPeriodo(
			InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper informarDadosGeracaoResumoAcaoConsultaPeriodoHelper)
			throws ErroRepositorioException;

	/**
	 * Pesquisa as situa��es das a��o de cobran�a
	 */
	public Collection consultarCobrancaAcaoSituacao(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException;
	
	public Collection consultarCobrancaAcaoSituacaoPeriodo(
			InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper informarDadosGeracaoResumoAcaoConsultaPeriodoHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException;

	/**
	 * Pesquisa as situa��es de d�bito da situa��o da a��o
	 */
	public Collection consultarCobrancaAcaoDebito(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao)
			throws ErroRepositorioException;
	
	public Collection consultarCobrancaAcaoDebitoPeriodo(
			InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper informarDadosGeracaoResumoAcaoConsultaPeriodoHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao)
			throws ErroRepositorioException;

	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoSituacaoPerfilImovel(
			int anoMesReferencia,
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException;

	public Collection consultarCobrancaAcaoDebitoPerfilImovel(
			int anoMesReferencia,
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException;

	public Collection consultarCobrancaAcaoSituacaoPerfilImovelIndicador(
			int anoMesReferencia, Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao, Integer idPerfil,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException;

	public Collection consultarCobrancaAcaoDebitoPerfilImovelIndicador(
			int anoMesReferencia, Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao, Integer idCobrancaAcaoDebito,
			Integer idPerfil, Short idIndicador,InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException;

	/**
	 * Exclui os dados Resumo de pend�ncia por ano/m�s e localidade
	 * 
	 * [UC0335] Gerar Resumo da Pend�ncia
	 * 
	 * @author Ana Maria
	 * @date 30/01/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoPendenciaPorAnoMesLocalidade(int anoMesReferencia,
			Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as situa��es de d�bito da situa��o da a��o de acordo com o indicador antesApos
	 * 
	 * @author S�vio Luiz 
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,short indicadorAntesApos,Integer idCobrancaAcaoDebito)
			throws ErroRepositorioException;
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 21/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCobrancaAcaoQuantidadeDocumentos(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException;
	
	public Integer consultarCobrancaAcaoQuantidadeDocumentosPeriodo(
			InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper informarDadosGeracaoResumoAcaoConsultaPeriodoHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 25/06/2007
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaResumoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 25/06/2007
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 21/05/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Integer consultarCobrancaAcaoEventualQuantidadeDocumentos(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoSituacaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao) throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualSituacaoPerfilImovel(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualSituacaoPerfilImovelIndicador(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idPerfil,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualDebitoPerfilImovel(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualDebitoPerfilImovelIndicador(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Integer idPerfil,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] Consultar Resumo das A��es de Cobran�a Eventuais
	 * 
	 * Pesquisa as a��es de cobran�a
	 * 
	 * @author S�vio Luiz
	 * @date 26/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoEventualDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,
			short indicadorAntesApos, Integer idCobrancaAcaoDebito)
			throws ErroRepositorioException;
	
    /**
	 * O sistema seleciona as contas pendentes ( a partir
	 * da tabela CONTA com CNTA_AMREFERENCIACONTA < 
	 * PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS
	 * e ( DCST_IDATUAL = 0 ou (DCST_IDATUAL = (1,2) e 
	 * CNTA_AMREFERENCIACONTABIL < PARM_AMREFENRECIAFATURAMENTO
	 * ou (DCST_IDATUAL = (3,4,5) e CNTA_AMREFERENCIACONTABIL >
	 * PARM_AMREFERENCIAFATURAMENTO 
	 * 
	 * @author Bruno Barrros
	 * @date 19/07/2007
	 * 
	 * @param idQuadra id do setor a ser pesquisado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasPendentes(int idQuadra)
			throws ErroRepositorioException;	
	
	/**
	 * Seleciona as faixas m�nima e m�xima para a pesquisa de contas pendentes 
	 * 
	 * @author Bruno Barros
	 * @date 03/09/2007
	 * 
	 * @param idLocalidade id da localidade a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] getFaixaContasPendentes( int idSetor, int anoMesReferenciaMenos1 ) throws ErroRepositorioException;
	
	/**
	 * Insere uma linha na tabela un_resumo_pendencia
	 * Metodo criado devido ao aumento de performace
	 * consideravel para o gerencial
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirPendenciaContasGerencia( 
			Integer anoMesReferencia, ResumoPendenciaContasGerenciaHelper helper )
		throws ErroRepositorioException;
	
	/**
	 * O sistema seleciona as contas pendentes (a partir
	 * da tabela CONTA com CNTA_AMREFERENCIACONTA < PARM_AMREFERENCIAFATURAMENTO
	 * da tabela SISTEMA_PARAMENTOS e DCST_IDATUAL com valor correspondente a normal
	 * ou incluida ou retificada 
	 * 
	 * @author Bruno Barrros
	 * @date 01/08/2007
	 * 
	 * @param idSetor id do Setor a ser pesquisado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasPendentesPorRegiao(int idSetor)
			throws ErroRepositorioException;
	
	/**
	 * @author Bruno Barrros
	 * @date 01/08/2007
	 * 
	 * @param idQuadra id da quadra a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getGuiasPagamentoGerencia(int idQuadra)
			throws ErroRepositorioException;
	
	public void inserirGuiasPagamentoGerencia( Integer anoMesReferencia, ResumoPendenciaGuiasPagamentoGerenciaHelper helper )
		throws ErroRepositorioException;
	
	/**
	 * @author Bruno Barrros
	 * @date 06/08/2007
	 * 
	 * @param idQuadra id da Quadra a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDebitosACobrarGerencia(int idQuadra)
			throws ErroRepositorioException;
	
	public void inserirPendendiciaDebitosACobrarGerencia( Integer anoMesReferencia, ResumoPendenciaDebitosACobrarGerenciaHelper helper )
		throws ErroRepositorioException;
	
	/**
	 * @author Bruno Barrros
	 * @date 07/08/2007
	 * 
	 * @param idQuadra id da Quadra a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getCreditoARealizarGerencia(int idQuadra)
			throws ErroRepositorioException;
	public void inserirPendendiciaCreditosARealizerGerencia( Integer anoMesReferencia, ResumoPendenciaCreditoARealizarGerenciaHelper helper )
	  		throws ErroRepositorioException;	
	
//	/**
//	 * @author Marcio Roberto
//	 * @date 07/08/2007
//	 * 
//	 * @return
//	 * @throws ErroRepositorioException
//	 */
//	public BigDecimal getPesquisaDebitoACobrar(int idParc) throws ErroRepositorioException;	
//	
//	
//	/**
//	 * @author Marcio Roberto
//	 * @date 08/08/2007
//	 * 
//	 * @return
//	 * @throws ErroRepositorioException
//	 */
//	public BigDecimal getPesquisaDebitoACobrarTipos(int idConta, int tipoLancamentoItemContabil) throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior m�s/ano de refer�ncia da tabela un_resumo_pendencia
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoPendencia()
			throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior m�s/ano de refer�ncia da tabela un_resumo_parcelamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoParcelamento()
			throws ErroRepositorioException;
	
	/**
	 * Seleciona o maior m�s/ano de refer�ncia da tabela un_resumo_indicadores_cobranca
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarMaiorAnoMesResumoIndicadoresCobranca()
			throws ErroRepositorioException;
	
	/**
	 * Atualiza os dados na tabela un_resumo_indicadores_cobranca
	 * 
 	 * [UC????] - Gerar Resumo Indicadores da Cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 25/03/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadoresCobranca(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas) throws ErroRepositorioException;
	

	/**
	 * Esse m�todo insere os dados gerados pelo resumo de pendencia 
	 * em uma outra tabela, a qual na leva em considera��o a quadra como quebra
	 * Essa outra tabela foi criada devido a pouca performace da tabela
	 * com as quadras no mondrian
	 * 
	 * @param idQuadra - C�digo da Quadra a ser inserido
	 * @param anoMesReferencia - Ano e mes de referencia ser inserido
	 * @throws ErroRepositorioException - Qualquer erro...
	 * 
	 * @author Bruno Barros 
	 */
	public void inserirResumoPendenciaSemQuadra( Integer idQuadra, Integer anoMesReferencia )
		throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 13/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoMotivoEncerramento(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			boolean ehExecucao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * Popup de Retorno de Fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoRetornoFiscalizacao(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ErroRepositorioException;
	
	/**
	 * [UC0617] - Consultar Resumo das A��es de Cobran�a Eventual
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoMotivoEncerramentoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			boolean ehExecucao)
			throws ErroRepositorioException;	
	
	/**
	 * [UC0617] - Consultar Resumo das A��es de Cobran�a Eventual
	 * Popup de Retorno de Fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoRetornoFiscalizacaoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ErroRepositorioException;
	
	/**
	 * O sistema seleciona as contas pendentes ( a partir
	 * da tabela CONTA com CNTA_AMREFERENCIACONTA < 
	 * PARM_AMREFERENCIAFATURAMENTO da tabela SISTEMA_PARAMETROS
	 * e ( DCST_IDATUAL = 0 ou (DCST_IDATUAL = (1,2) e 
	 * CNTA_AMREFERENCIACONTABIL < PARM_AMREFENRECIAFATURAMENTO
	 * ou (DCST_IDATUAL = (3,4,5) e CNTA_AMREFERENCIACONTABIL >
	 * PARM_AMREFERENCIAFATURAMENTO 
	 * 
	 * @author Fernando Fontelles
	 * @date 25/03/2010
	 * 
	 * @param idSetor id do setor a ser pesquisado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getContasPendentesPorAno(int idSetor/*, int quantidadeInicio, int quantidadeMaxima*/)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 25/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */	
	public void inserirPendenciaContasGerencialPorAno( Integer anoMesReferencia, ResumoPendenciaContasGerencialPorAnoHelper helper )
	throws ErroRepositorioException;
	
	/**
	 * @author Fernando Fontelles Filho
	 * @date 25/03/2010
	 * 
	 * @param idSetor id do SetorComercial a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getGuiasPagamentoGerenciaPorAno(int idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException; 
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirGuiasPagamentoGerenciaPorAno( Integer anoMesReferencia, 
			ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper helper )
	throws ErroRepositorioException;
	
	/**
	 * @author Fernando Fontelles
	 * @date 26/03/2010
	 * 
	 * @param idSetor id do Setor Comercial a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getDebitosACobrarGerenciaPorAno(int idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirPendendiciaDebitosACobrarGerenciaPorAno( Integer anoMesReferencia, 
			ResumoPendenciaDebitosACobrarGerencialPorAnoHelper helper )
	throws ErroRepositorioException;
	
	/**
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param idSetor id do Setor Comercial a ser pesquisada
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getCreditoARealizarGerenciaPorAno(int idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 26/03/2010
	 * 
	 * @param anoMesReferencia
	 * @param helper
	 * @throws ErroRepositorioException
	 */
	public void inserirPendendiciaCreditosARealizerGerenciaPorAno( Integer anoMesReferencia, 
			ResumoPendenciaCreditoARealizarGerencialPorAnoHelper helper )
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 06/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarPendenciaContasGerencialPorAno(ResumoPendenciaContasGerencialPorAnoHelper helper,
			Integer anoMesReferencia)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 07/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarGuiasPagamentoGerenciaPorAno
						(Integer anoMesReferencia,ResumoPendenciaGuiasPagamentoGerencialPorAnoHelper helper)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 07/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarPendendiciaDebitosACobrarGerenciaPorAno
						(Integer anoMesReferencia,ResumoPendenciaDebitosACobrarGerencialPorAnoHelper helper)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Fernando Fontelles Filho
	 * @date 07/05/2010
	 * 
	 * @param helper
	 * @param anoMesReferencia
	 * @throws ErroRepositorioException
	 * 
	 */
	public Integer atualizarPendendiciaCreditosARealizerGerenciaPorAno
						(Integer anoMesReferencia,ResumoPendenciaCreditoARealizarGerencialPorAnoHelper helper)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 30/09/2010
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List getGuiasPagamentoPorClienteGerencia( int idLocalidade ) throws ErroRepositorioException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Ivan Sergio
	 * @date 23/12/2010
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoTipoCorte(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			boolean ehExecucao)
			throws ErroRepositorioException;

}

