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
package gcom.gerencial.arrecadacao;

import gcom.util.ControladorException;

/**
 * 
 * 
 * @author Ivan S�rgio
 * @created 11/05/2007
 */
public interface ControladorGerencialArrecadacaoLocal
		extends
			javax.ejb.EJBLocalObject {
	
	
	/**
	 * M�todo que gera o resumo da Arrecadacao
	 *
	 * [UC0551] - Gerar Resumo da Arrecadacao Agua/Esgoto
	 * 			- Gerar Resumo da Arrecadacao Outros
	 * 			- Gerar Resumo da Arrecadacao Credito
	 *
	 * @author Ivan S�rgio
	 * @date 17/05/2007
	 */
	public void gerarResumoArrecadacao(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * M�todo que gera o resumo da Arrecadacao Por Ano
	 *
	 * @author Fernando Fontelles Filho
	 * @date 02/06/2010 
	 *
	 */
	public void gerarResumoArrecadacaoPorAno(
			int idLocalidade, 
			int anoMesReferenciaArrecadacao,
			int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * Carrega os dados do resumo da arrecada��o do gerencial
	 * 
	 * @author Erivan Sousa
	 * @date 19/04/2012
	 * 
	 * @throws ControladorException
	 */
	public void executarCargaResumoArrecadacaoGerencialPorAno(
			int idFuncionalidadeIniciada)	throws ControladorException;
	
	/**
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaLigacoesEconomiasPorAno(int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaIndicadorLigacaoEconomiaPorAno(int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaResumoParcelamentoPorAno(int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaResumoPendenciaPorAno(int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 24/04/2012
	 */
	public void executarCargaPendenciaSemQuadraVariosAnos(int idFuncionalidadeIniciada) throws ControladorException;
	
	/**
	 * 
	 * @author Rafael Corr�a
	 * @date 17/04/2013
	 *
	 * @throws ControladorException
	 */
	public void gerarResumoArrecadacao(int idFuncionalidadeIniciada) throws ControladorException;
	
}