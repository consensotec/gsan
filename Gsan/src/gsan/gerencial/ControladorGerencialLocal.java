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
package gsan.gerencial;

import gsan.gerencial.bean.FiltrarRelatorioOrcamentoSINPHelper;
import gsan.gerencial.bean.FiltrarRelatorioQuadroMetasAcumuladoHelper;
import gsan.gerencial.bean.FiltrarRelatorioQuadroMetasExercicioHelper;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaPeriodoHelper;
import gsan.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gsan.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gsan.gerencial.bean.OrcamentoSINPHelper;
import gsan.gerencial.bean.QuadroMetasAcumuladoHelper;
import gsan.gerencial.bean.QuadroMetasExercicioHelper;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;

import java.io.File;
import java.util.Collection;
import java.util.List;



/**
 * 
 * 
 * @author Raphael Rossiter
 * @created 20/05/2006
 */
public interface ControladorGerencialLocal extends javax.ejb.EJBLocalObject {
	
	/**
	 * Esta funcionalidade permite informar dados para gera��o de relat�rios ou consultas 
	 * 
	 * [UC0304] - Informar Dados para Gera��o de Relat�rio ou Consulta
	 * 
	 * @author Raphael Rossiter
	 * @date 22/05/2006
	 *
	 * @param mesAnoFaturamento
	 * @param opcaoTotalizacao
	 * @param idFauramentoGrupo
	 * @param idGerenciaRegional
	 * @param idEloPolo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param nmQuadra
	 * @param idsImovelPerfil
	 * @param idsLigacaoAguaSituacao
	 * @param idsLigacaoEsgotoSituacao
	 * @param idsCategoria
	 * @param idsEsferaPoder
	 * @param tipoAnaliseFaturamento
	 * @param tipoRelatorio
	 * @return InformarDadosGeracaoRelatorioConsultaHelper
	 * @throws ControladorException
	 */
	public InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsulta(String mesAnoFaturamento,
			Integer opcaoTotalizacao, Integer idFauramentoGrupo, Integer idCobrancaGrupo, Integer idGerenciaRegional, Integer idEloPolo,
			Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra, String[] idsImovelPerfil,
			String[] idsLigacaoAguaSituacao, String[] idsLigacaoEsgotoSituacao, String[] idsCategoria,
			String[] idsEsferaPoder, Integer tipoAnaliseFaturamento, Integer tipoRelatorio,
			Integer idUnidadeNegocio, Integer idMunicipio, Integer idRota) throws ControladorException ;
	
	
	/**
	 * M�todo para auxilio de Casos de Uso de resumos
	 */
	public Collection criarColecaoAgrupamentoResumos(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	public Collection criarColecaoAgrupamentoResumosCobrancaAcao(InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper) throws ControladorException;
	
	public List consultarComparativoResumosFaturamentoArrecadacaoPendencia(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
	public Collection criarColecaoAgrupamentoResumosPeriodo(InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodoHelper) throws ControladorException;
	
	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da faturamento
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecada��o e da Pend�ncia.
	 *
	 * @author Pedro Alexandre
	 * @date 09/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoFaturamento(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da arrecada��o
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecada��o e da Pend�ncia.
	 *
	 * @author Pedro Alexandre
	 * @date 10/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoArrecadacao(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;

	/**
	 * Pesquisa o valor e a quantidade de contas do resumo da pend�ncia.
	 *
	 * [UC0350] - Consultar Comparativo entre os Resumos do Faturamento, Arrecada��o e da Pend�ncia.
	 *
	 * @author Pedro Alexandre
	 * @date 10/06/2006
	 *
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoComparativoPendencia(InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper) throws ControladorException;
	
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
	public Collection criarColecaoAgrupamentoResumosCobrancaAcaoEventual(
			InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventualHelper)
			throws ControladorException;
	
	/**
	 * Pesquisa os valores necessarios na tabela un_resumo_faturamento
	 *
	 * [UC0722] - Gerar Relatorio para Or�amento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 20/11/2006
	 *
	 * @param FiltrarRelatorioOrcamentoSINPHelper
	 * @return Collection<OrcamentoSINPHelper>
	 * 
	 * @throws ControladorException
	 */
	public Collection<OrcamentoSINPHelper> pesquisarRelatorioOrcamentoSINP(
		FiltrarRelatorioOrcamentoSINPHelper filtrarRelatorioOrcamentoSINPHelper) 
		throws ControladorException ;
	
	/**
	 * 
	 * [UC0733] Gerar Quadro de metas Acumulado
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasAcumuladoHelper
	 * @return
	 */
	public Collection<QuadroMetasAcumuladoHelper> pesquisarRelatorioQuadroMetasAcumulado(
			FiltrarRelatorioQuadroMetasAcumuladoHelper filtrarRelatorioQuadroMetasAcumuladoHelper) 
		throws ControladorException;
	
	/**
	 * Verifica se existe dados nas tabelas de resumo
	 *
	 * [UC0722] - Gerar Relatorio para Or�amento e SINP
	 *
	 * @author Rafael Pinto
	 * @date 11/01/2007
	 *
	 * @param anoMesReferencia
	 * 
	 * @throws ControladorException
	 */
	public void validarDadosOrcamentoSINP(int anoMesReferencia) 
		throws ControladorException ;
	
	/**
	 * Pesquisa todas as tabelas de resumo para o Orcamento sem a tabela de resumo pendencia e arrecada��o
	 *
	 * [UC0750] - Gerar Arquivo Texto para Or�amento e SINP
	 *
	 * @author S�vio Luiz
	 * @date 12/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void existeDadosUnResumoParcialParaOrcamentoSINP(int anoMesReferencia) 
		throws ControladorException;
	
	/**
	 * Gera o Arquivo de Oracamento e SINP
	 *
	 * [UC0750] - Gerar Arquivo Texto para Or�amento e SINP
	 *
	 * @author Tiago Moreno
	 * @date 14/02/2008
	 *
	 * @return anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void gerarArquivoTextoOrcamentoSinp(int anoMesReferencia) 
		throws ControladorException;
	
	/**
	 * 
	 * [UC0752] Gerar Quadro de metas por Exercicio
	 * 
	 * @author Bruno Barros
	 * @param filtrarRelatorioQuadroMetasExercicioHelper
	 * @return
	 */
	public Collection<QuadroMetasExercicioHelper> pesquisarRelatorioQuadroMetasExercicio(
			FiltrarRelatorioQuadroMetasExercicioHelper filtrarRelatorioQuadroMetasExercicioHelper) throws ControladorException;
	
	/**
	 * 
	 * [RM 1078] Evolu��o de Faturamento por per�odo
	 * 
	 * @author Davi Menezes
	 * @return
	 */
	public InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodo(String mesAnoInicialFaturamento, String mesAnoFinalFaturamento,
			Integer opcaoTotalizacao, Integer idFauramentoGrupo, Integer idCobrancaGrupo, Integer idGerenciaRegional, Integer idEloPolo,
			Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra, String[] idsImovelPerfil,
			String[] idsLigacaoAguaSituacao, String[] idsLigacaoEsgotoSituacao, String[] idsCategoria,
			String[] idsEsferaPoder, Integer tipoAnaliseFaturamento, Integer tipoRelatorio,
			Integer idUnidadeNegocio, Integer idMunicipio, Integer idRota) throws ControladorException ;
	
	/**
	 * Gerar Arquivo Texto para o Planejamento
	 *
	 * [UC1262] - Gerar Arquivo Texto para o Planejamento
	 *
	 * @author Rafael Pinto
	 * @date 14/12/2011
	 * 
	 * @param Ano/Mes
	 * @return StringBuilder
	 * 
	 * @throws ErroRepositorioException
	 */
	public File gerarArquivoTextoPlanejamentoSinp(int anoMesReferencia) 
		throws ControladorException ;
	
	/**
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 28/06/2012
	 */
	public void gerarResumoPendenciaBD(int idFuncionalidadeIniciada) throws ControladorException;
}

