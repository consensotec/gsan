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
package gcom.gerencial.micromedicao;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.gerencial.micromedicao.FiltrarRelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoDistritoOperacionalHelper;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoZonaAbastecimentoHelper;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.List;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialMicromedicaoLocal extends
		javax.ejb.EJBLocalObject {

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeAgua(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadePoco(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public List consultarResumoAnormalidadeConsumo(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeLeitura(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * [UC0344] Consultar Resumo de Anormalidades
	 * 
	 * 
	 * @param informarDadosGeracaoRelatorioConsultaHelper
	 * @return
	 * @throws ControladorException
	 */
	public void gerarResumoAnormalidadeConsumo() throws ControladorException;

	/**
	 * Gera o resumo das liga��es de hidr�metro.
	 * 
	 * [UC0564 - Gerar Resumo das Intala��es de Hidr�metros]
	 * 
	 * @author Pedro Alexandre
	 * @date 24/04/2007
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometros(
			Integer anoMesReferenciaArrecadacao, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;

	/**
	 * M�todo que gera o resumo da Leituras Anormalidades
	 * 
	 * [UC0551] - Gerar Resumo da Leitura Anormalidade
	 * 
	 * @author Ivan S�rgio
	 * @date 26/04/2007
	 */
	public void gerarResumoLeituraAnormalidade(int idLocalidade,
			int anoMesLeitura, int idFuncionalidadeIniciada)
			throws ControladorException;

	/**
	 * [UC0564] Gerar Resumo das Instala��es de Hidr�metros
	 * 
	 * Pesquisa os ids dos setores comercias dos im�veis que tem hidrometro
	 * instalado no hist�rico
	 * 
	 * @author Pedro Alexandre
	 * @date 08/05/2007
	 * 
	 * @param anoMesFaturamento
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsSetorComercialParaGerarResumoInstalacaoHidrometro(
			Integer anoMesFaturamento) throws ControladorException;

	/**
	 * M�todo que gera o resumo de Hidrometros
	 * 
	 * [UC0586] - Gerar Resumo Hidrometro
	 * 
	 * @author Thiago Ten�rio
	 * @date 30/04/2007
	 * 
	 */
	public void gerarResumoHidrometros(Integer idHidrometroMarca,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * M�todo que gera resumo indicadores da micromedi��o
	 * 
	 * [UC0573] - Gerar Resumo Indicadores da Micromedi��o
	 * 
	 * @author Rafael Corr�a
	 * @date 11/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicao(int idFuncionalidadeIniciada)
			throws ControladorException;
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoDistritoOperacionalHelper> pesquisarRelatorioResumoDistritoOperacional(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException;
	
	/**
	 * 
	 * [UC0892]Consulta total dos registros do Relatorio Resumo Distrito Operacional
	 * 
	 * @author Hugo Amorim
	 * @date 15/04/2009
	 * 
	 * @return Innteger
	 * 
	 */
	public Integer pesquisarTotalRegistroRelatorioResumoDistritoOperacional(FiltrarRelatorioResumoDistritoOperacionalHelper helper) throws ControladorException;
	/**
	 * 
	 * [UC0892]Consulta os registros do Relatorio Resumo Zona Abastecimento
	 * 
	 * @author Hugo Amorim
	 * @date 23/04/2009
	 * 
	 * @return Collection<RelatorioResumoDistritoOperacionalHelper>
	 * 
	 */
	public Collection<RelatorioResumoZonaAbastecimentoHelper> pesquisarRelatorioResumoZonaAbastecimento(FiltrarRelatorioResumoDistritoOperacionalHelper filtro)
			throws ControladorException;
	
	/**
	 * M�todo que gera resumo indicadores da micromedi��o
	 * 
	 * @author Fernando Fontelles
	 * @date 17/05/2010
	 * 
	 */
	public void gerarResumoIndicadoresMicromedicaoPorAno(int idFuncionalidadeIniciada)
			throws ControladorException;
	
	/**
	 * Gera o resumo das liga��es de hidr�metro por ano.
	 * 
	 * Gerar Resumo das Instala��es de Hidr�metros Por Ano
	 * 
	 * @author Fernando Fontelles
	 * @date 17/06/2010
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idSetorComercial
	 * @param idFuncionalidadeIniciada
	 * @throws ControladorException
	 */
	public void gerarResumoInstalacoesHidrometrosPorAno(
			Integer anoMesReferenciaFaturamento, Integer idSetorComercial,
			int idFuncionalidadeIniciada) throws ControladorException;
}	
