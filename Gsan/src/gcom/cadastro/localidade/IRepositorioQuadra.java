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
package gcom.cadastro.localidade;

import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rossiter
 * @created 6 de Setembro de 2005
 * @version 1.0
 */

public interface IRepositorioQuadra {

    /**
     * Pesquisa uma cole��o de quadra com uma query especifica
     * 
     * @param idSetorComercial
     *            Description of the Parameter
     * @return Description of the Return Value
     * @exception ErroRepositorioException
     *                Description of the Exception
     */

    public Collection pesquisarQuadra(int idSetorComercial)
            throws ErroRepositorioException;
    
	/**
	 * M�todo que retorna o maior n�mero da quadra de um setor comercial
	 * 
	 * @author Rafael Corr�a
	 * @date 11/07/2006
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ControladorException
	 */
	
	public int pesquisarMaximoCodigoQuadra(Integer idSetorComercial)
			throws ErroRepositorioException;

    /**
     * Pesquisa uma cole��o de quadra com uma query especifica
     * 
     * @param idSetorComercial
     *            Description of the Parameter
     * @return Description of the Return Value
     * @exception ErroRepositorioException
     *                Description of the Exception
     */

    //    public Collection pesquisarQuadraRelatorio(Quadra quadraParametros)
    // throws ErroRepositorioException;
	
	public Collection pesquisarQuadrasPorSetorComercialFaturamentoGrupo(int localidade, int idSetoresComercial[], 
			Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC608] Efetuar Liga��o de Esgoto sem RA
	 * @author S�vio Luiz
	 * @date 10/09/2007
	 * 
	 * @return
	 */

	public Short pesquisarIndicadorRedeEsgotoQuadra(int idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisar quadras de um roteiro empresa 
	 * @param idRoteiroEmpresa
	 * @return cole��o de quadras
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarQuadrasPorRoteiroEmpresa(int idRoteiroEmpresa) throws ErroRepositorioException;
    
    /**
     * @author: Vivianne Sousa
     * @date: 16/05/2008 
     */
    public Collection pesquisarIdQuadraPorSetorComercial(Integer idSetorComercial)
        throws ErroRepositorioException ;
    
    /**
     * [????] Informar Subdivis�es de Rota
     * 
     * Atualiza o campo rota das quadras que pertencem ao intervalo de numero informado e ao setor comercial
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public void atualizarRotaDasQuadrasNoIntervaloNumero(
    		int idNovaRota, int idSetorComercial, int numeroInicial, int numeroFinal) throws ErroRepositorioException;
	
    /**
     * [????] Informar Subdivis�es de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public Collection<NumeroQuadraMinimoMaximoDaRotaHelper> pesquisarNumeroQuadraMininoMaximoDaRota(
    		Collection<Integer> idsRotas) throws ErroRepositorioException;
    
    /**
     * [UC0???] Informar Subdivis�es de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 27/10/2008
     */
    public Integer pesqisarQuantidadeQuadrasDaRota(
    		Integer idRota, Integer quadraInicial, Integer quadraFinal) throws ErroRepositorioException;
    
    /**
	 * Integra��o com o conceito de face da quadra
	 *
	 * @author Raphael Rossiter
	 * @date 21/05/2009
	 *
	 * @param idImovel
	 * @return Quadra
	 * @throws ErroRepositorioException
	 */
	public Quadra pesquisarQuadra(Integer idImovel)throws ErroRepositorioException ;
	
	/**
	 * Integra��o com o conceito de face da quadra
	 *
	 * @author Raphael Rossiter
	 * @date 21/05/2009
	 *
	 * @param idImovel
	 * @return QuadraFace
	 * @throws ErroRepositorioException
	 */
	public QuadraFace pesquisarQuadraFace(Integer idImovel)throws ErroRepositorioException ;

}
