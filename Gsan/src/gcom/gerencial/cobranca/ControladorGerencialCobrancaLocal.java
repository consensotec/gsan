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

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper;
import gcom.gerencial.cobranca.bean.ResumoPendenciaAcumuladoHelper;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.List;



/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialCobrancaLocal extends javax.ejb.EJBLocalObject {
	
	/**
	 * M�todo que gera o resumo Resumo Situacao Especial Faturamento  
	 *
	 * [UC0341] 
	 *
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 *
	 */
	public void gerarResumoSituacaoEspecialCobranca(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * M�todo que gera o resumo da pendencia
	 * 
	 * [UC0335] - Gerar Resumo da Pendencia
	 * 
	 * @author Bruno Barros
	 * @date 19/07/2007
	 * 
	 */	
	public void gerarResumoPendencia( int idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;		
	/**
	 * Este caso de uso permite consultar o resumo da pend�ncia, com a op��o de impress�o da  consulta.
	 * Dependendo da op��o de totaliza��o sempre � gerado o relat�rio, sem a fera��o da consulta.
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
	 * @throws ControladorException
	 */
	public List consultarResumoPendencia(InformarDadosGeracaoRelatorioConsultaHelper 
			informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	public Collection<ResumoCobrancaSituacaoEspecialConsultaGerenciaRegHelper> recuperaResumoSituacaoEspecialCobranca(ConsultarResumoSituacaoEspecialHelper helper) throws ControladorException;
	
	/**
	 * Este caso de uso permite consultar o resumo da pend�ncia, com a op��o de impress�o da  consulta.
	 * Dependendo da op��o de totaliza��o sempre � gerado o relat�rio, sem a fera��o da consulta.
	 *
	 * [UC0338] Consultar Resumo da Pend�ncia
	 *
	 * Retorna os registro de resumo pend�ncia dividindo em cole��es de categoria RESIDENCIAL, COMERCIAL,
	 * INDUSTRIAL e PUBLICA
	 *
	 * retornaConsultaResumoPendencia
	 *
	 * @author Roberta Costa
	 * @date 31/05/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public Collection<ResumoPendenciaAcumuladoHelper> retornaConsultaResumoPendencia( InformarDadosGeracaoRelatorioConsultaHelper
			informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return CobrancaAcaoHelper
	 * @throws ErroRepositorioException
	 */	
	public Collection consultarResumoCobrancaAcao(InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper) 
		throws ControladorException;
	
	/**
	 * [UCXXXX] - Consultar Resumo das A��es de Cobran�a por per�odo
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return CobrancaAcaoHelper
	 * @throws ErroRepositorioException
	 */	
	public Collection consultarResumoCobrancaAcaoPeriodo(InformarDadosGeracaoResumoAcaoConsultaPeriodoHelper informarDadosGeracaoResumoAcaoConsultaPeriodoHelper) 
		throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * @author Ana Maria
	 * @date 06/11/2006
	 * 
	 * @return CobrancaAcaoHelper
	 * @throws ErroRepositorioException
	 */	
	public Collection consultarResumoCobrancaAcaoPerfil(int anoMesReferencia, Integer idCobrancaAcao, 
			Integer idCobrancaAcaoSituacao, Integer idCobrancaAcaoDebito, Short idIndicador,InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) 
		throws ControladorException;
	
	/**
	 * M�todo que gera resumo indicadores da cobran�a
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Cobran�a
	 * 
	 * @author Rafael Corr�a
	 * @date 25/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresCobranca(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * 
	 * Pesquisa as situa��es de d�bito da situa��o da a��o de acordo com o
	 * indicador antesApos
	 * 
	 * @author S�vio Luiz
	 * @date 06/11/2006
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCobrancaAcaoDebitoComIndicador(
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			Integer idCobrancaAcao, Integer idCobrancaAcaoSituacao,Integer idCobrancaAcaoDebito)throws ControladorException;
	
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
	public Collection consultarResumoCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
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
	public Collection consultarResumoCobrancaAcaoEventualPerfil(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			Integer idCobrancaAcaoDebito,
			Short idIndicador,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
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
			Integer idCobrancaAcaoDebito) throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 13/06/2008
	 * 
	 * @return Collection CobrancaAcaoMotivoEncerramentoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoMotivoEncerramento(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
			boolean ehExecucao)
			throws ControladorException;
	
	/**
	 * [UC0489] - Consultar Resumo das A��es de Cobran�a
	 * Popup de Retorno de fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 18/06/2008
	 * 
	 * @return Collection ResumoAcaoCobrancaSituacaoAcaoDetalheHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoRetornoFiscalizacao(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper)
			throws ControladorException;	

	/**
	 * [UC0617] - Consultar Resumo das A��es de Cobran�a Eventual
	 * Popup de Motivo de Encerramento 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection CobrancaAcaoMotivoEncerramentoHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoMotivoEncerramentoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper,
			boolean ehExecucao)
			throws ControladorException;
	
	/**
	 * [UC0617] - Consultar Resumo das A��es de Cobran�a Eventual
	 * Popup de Retorno de fiscalizacao 
	 * 
	 * @author Francisco do Nascimento
	 * @date 19/06/2008
	 * 
	 * @return Collection ResumoAcaoCobrancaSituacaoAcaoDetalheHelper
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoCobrancaAcaoRetornoFiscalizacaoEventual(
			Integer idCobrancaAcao,
			Integer idCobrancaAcaoSituacao,
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
	/**
     * M�todo que gera o resumo da pendencia Por Ano
     * 
     * @author Fernando Fontelles Filho
     * @date 23/03/2010
     */
    public void gerarResumoPendenciaPorAno(int idSetor, int idFuncionalidadeIniciada)
            throws ControladorException;
	
    /**
     * 
     * @author Arthur Carvalho
     * @date 30/09/2010
     * @param idLocalidade
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    public void gerarGuiaPagamentoPorClienteResumoPendencia( int idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;
    
    /**
     * [UC0489] - Consultar Resumo das A��es de Cobran�a Popup de Motivo de
     * Encerramento
     * 
     * @author Ivan Sergio
     * @date 23/12/2010
     * @return Collection CobrancaAcaoMotivoEncerramentoHelper
     * @throws ErroRepositorioException
     */
    public Collection consultarResumoCobrancaAcaoTipoCorte(
            Integer idCobrancaAcao,
            Integer idCobrancaAcaoSituacao,
            InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper,
            boolean ehExecucao) throws ControladorException;
    
    
    public void gerarResumoPendenciaCreditosARealizarGerencia(int idQuadra)
            throws ControladorException, ErroRepositorioException;
	
}