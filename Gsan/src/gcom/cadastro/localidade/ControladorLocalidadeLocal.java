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

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.bean.InserirQuadraHelper;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
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
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author not attributable
 * @created 6 de Setembro de 2005
 * @version 1.0
 */

public interface ControladorLocalidadeLocal extends javax.ejb.EJBLocalObject {
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param setorComercial
     *            Descri��o do par�metro
     */
    public void atualizarSetorComercial(SetorComercial setorComercial,Collection colecaoFonteCaptacao) 
    	throws ControladorException;

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param localidade
     *            Descri��o do par�metro
     */
    public void atualizarLocalidade(Localidade localidade) throws ControladorException;

    /**
     * < <Descri��o do m�todo>>
     * 
     * @param quadra
     *            Descri��o do par�metro
     */
    public void atualizarQuadra(Quadra quadra, Usuario usuarioLogado, 
    		Collection colecaoQuadraFace) throws ControladorException;

    /**
     * Pesquisa uma cole��o de setor comercial com uma query especifica
     * 
     * @param idLocalidade
     *            parametros para a consulta
     * @return Description of the Return Value
     */
    public Collection pesquisarSetorComercial(int idLocalidade) throws ControladorException;

    /**
     * Pesquisa uma cole��o de quadra com uma query especifica
     * 
     * @param idSetorComercial
     *            parametros para a consulta
     * @return Description of the Return Value
     */
    public Collection pesquisarQuadra(int idSetorComercial) throws ControladorException;
    
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception RemoteException
     *                Description of the Exception
     */
    // public Collection pesquisarQuadraRelatorio(Quadra quadraParametros)
    // throws ControladorException;
    /**
     * Pesquisa uma cole��o de localidades por ger�ncia regional
     * 
     * @param idGerenciaRegional
     *            C�digo da ger�ncia regional solicitada
     * @return Cole��o de Localidades da Ger�ncia Regional solicitada
     * @exception ControladorException
     *                Erro no hibernate
     */
    public Collection<Localidade> pesquisarLocalidadePorGerenciaRegional(int idGerenciaRegional) throws ControladorException;

    /**
     * Pesquisa uma cole��o de ger�ncias regionais
     * 
     * @return Cole��o de Ger�ncias Regionais
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public Collection<GerenciaRegional> pesquisarGerenciaRegional() throws ControladorException;

    /**
     * Pesquisa uma ger�ncia regional pelo id
     * 
     * @author Rafael Corr�a
     * @date 01/08/2006
     * 
     * @return Ger�ncia Regional
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public GerenciaRegional pesquisarObjetoGerenciaRegionalRelatorio(Integer idGerenciaRegional) throws ControladorException;

    /**
     * Inseri um objeto do tipo setor comercial no BD
     * 
     * @param setorComercial
     * @return ID gerado
     * @throws ControladorException
     */
    public Integer inserirSetorComercial(SetorComercial setorComercial,Collection colecaoFonteCaptacao) 
    	throws ControladorException;

    /**
     * Inseri uma cole��o de pagamentos no sistema
     * 
     * [UC0265] Inserir Pagamentos
     * 
     * Este fluxo secund�rio tem como objetivo pesquisar a localidade digitada
     * pelo usu�rio
     * 
     * [FS0007] - Verificar exist�ncia da localidade
     * 
     * @author Pedro Alexandre
     * @date 16/02/2006
     * 
     * @param idLocalidadeDigitada
     * @return
     * @throws ControladorException
     */
    public Localidade pesquisarLocalidadeDigitada(Integer idLocalidadeDigitada) throws ControladorException;

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

    public int pesquisarMaximoCodigoQuadra(Integer idSetorComercial) throws ControladorException;

    /**
     * M�todo que retorna o maior c�digo de setor comercial de uma localidade
     * 
     * @author Rafael Corr�a
     * @date 11/07/2006
     * 
     * @param idSetorComercial
     * @return
     * @throws ControladorException
     */

    public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade) throws ControladorException;

    /**
     * M�todo que retorna o maior id da Localidade
     * 
     * @author Vivianne Sousa
     * @date 12/07/2006
     * 
     * @return
     * @throws ControladorException
     */

    public int pesquisarMaximoIdLocalidade() throws ControladorException;

    /**
     * Pesquisa uma localidade pelo id
     * 
     * @author Rafael Corr�a
     * @date 01/08/2006
     * 
     * @return Localidade
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public Localidade pesquisarObjetoLocalidadeRelatorio(Integer idLocalidade) throws ControladorException;

    /**
     * Pesquisa um setor comercial pelo c�digo e pelo id da localidade
     * 
     * @author Rafael Corr�a
     * @date 01/08/2006
     * 
     * @return SetorComercial
     * @exception ErroRepositorioException
     *                Erro no hibernate
     */
    public SetorComercial pesquisarObjetoSetorComercialRelatorio(Integer codigoSetorComercial, Integer idLocalidade) throws ControladorException;

    public Integer verificarExistenciaLocalidade(Integer idLocalidade) throws ControladorException;

    /**
	 * Met�do respons�vel por inserir uma quadra no sitema
	 * 
	 * [UC0000 - Inserir Quadra]
	 * 
	 * @author Vivianne Sousa, Pedro Alexandre
	 * @date 27/06/2006, 16/11/2006
	 * 
	 * @param quadra
	 * @param usuarioLogado
	 * @param usuarioLogado
	 * @return colecaoQuadraFace
	 * @throws ControladorException
	 */
	public Integer inserirQuadra(Quadra quadra, Collection colecaoQuadraFace, Usuario usuarioLogado)
			throws ControladorException ;

    /**
     * met�do respons�vel por verificar se o usu�rio que est� tentando remover as quadras tem abrang�ncia
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 18/11/2006
     *
     * @param ids
     * @param pacoteNomeObjeto
     * @param operacaoEfetuada
     * @param acaoUsuarioHelper
     * @param usuarioLogado
     * @throws ControladorException
     */
    public void removerQuadra(String[] ids, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper, Usuario usuarioLogado) throws ControladorException ;

    /**
     * Pesquisa o id da ger�ncia regional para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecada��o do M�s, [UC0155] Encerrar Faturamento do M�s
     * 
     * @author Pedro Alexandre
     * @date 05/01/2007
     *
     * @param idLocalidade
     * @return
     * @throws ControladorException
     */
    public Integer pesquisarIdGerenciaParaLocalidade(Integer idLocalidade) throws ControladorException ;
    
//    /**
//     * < <Descri��o do m�todo>>
//     * 
//     * @param localidade
//     *            Descri��o do par�metro
//     */
//    public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional) throws ControladorException;
//    /**
//     * Met�do respons�vel por inserir uma ger�ncia Regional
//     *
//     * [UC0000 - Inserir Gerencia Regional
//     *
//     * @author Thiago Ten�rio
//     * @date 27/06/2006, 16/11/2006
//     * 
//     * @param gerencia regional
//     * @param usuarioLogado
//     * @return
//     * @throws ControladorException
//     */
//    public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional) throws ControladorException;
    
    
    /**
	 * Atualiza logradouroCep de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCepGerenciaRegional(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairroGerenciaRegional(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroCep de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	
	/**
     * Pesquisa o id da unidade negocio para a qual a localidade pertence.
     * 
     * [UC0267] Encerrar Arrecada��o do M�s, [UC0155] Encerrar Faturamento do M�s
     * 
     * @author Raphael Rossiter
     * @date 30/05/2007
     *
     * @param idLocalidade
     * @throws ControladorException
     */
    public Integer pesquisarIdUnidadeNegocioParaLocalidade(Integer idLocalidade) throws ControladorException ;
    
	/**
	 * Pesquisar os ids da Localidade
	 * 
	 * @author Thiago ten�rio
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTodosIdsLocalidade() throws ControladorException ;

    /**
     * Pesquisa uma cole��o de quadra com uma query especifica
     * 
     * @param idsSetorComercial
     * @param idFaturamentoGrupo
     *            parametros para a consulta
     * @return Description of the Return Value
     * @throws ControladorException
     */
    public Collection pesquisarQuadrasPorSetorComercialFaturamentoGrupo(int localidade, int []idsSetorComercial, Integer idFaturamentoGrupo) throws ControladorException;
    
    
    /**
	 * 
	 * [UC608] Efetuar Liga��o de Esgoto sem RA
	 * 
	 * @author S�vio Luiz
	 * @date 10/09/2007
	 * 
	 * @return
	 */

	public Short pesquisarIndicadorRedeEsgotoQuadra(int idImovel)
			throws ControladorException;
 
	/**
	 * Pesquisar quadras de um roteiro empresa 
	 * @param idRoteiroEmpresa
	 * @return cole��o de quadras
	 * @throws ControladorException
	 */
	public Collection pesquisarQuadrasPorRoteiroEmpresa(int idRoteiroEmpresa) throws ControladorException;
	
	/**
	 * Obt�m Elo P�lo
	 * @author Ana Maria
	 * @date 10/12/2007
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarEloPolo()throws ControladorException;
	
	/**
	 * pesquisar localidades do munic�pio
	 * @author S�vio Luiz
	 * @date 25/02/2008
	 *
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesMunicipio(Integer idMunicipio)throws ControladorException;
    
    /**
     * @author: Vivianne Sousa
     * @date: 16/05/2008 
     */
    public Collection pesquisarIdQuadraPorSetorComercial(Integer idSetorComercial)
        throws ControladorException;
	
	/**
	 * Pesquisa os ids das localidaes que possuem im�veis
	 *
	 * @author Pedro Alexandre
	 * @date 07/07/2008
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesImoveis() throws ControladorException ;
	
    /**
     * Atualiza o campo rota das quadras que pertencem ao intervalo de numero informado e ao setor comercial
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public void atualizarRotaDasQuadrasNoIntervaloNumero(
    		int idNovaRota, int idSetorComercial, int numeroInicial, int numeroFinal) throws ControladorException;
    
    /**
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public Collection<NumeroQuadraMinimoMaximoDaRotaHelper> pesquisarNumeroQuadraMininoMaximoDaRota(
    		Collection<Integer> idsRotas) throws ControladorException;
    
    /**
     * [UC0???] Informar Subdivis�es de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 27/10/2008
     */
    public Integer pesqisarQuantidadeQuadrasDaRota(
    		Integer idRota, Integer quadraInicial, Integer quadraFinal) throws ControladorException;
    
    /**
     * [UC0024] Inserir Quadra
     *
     * @author Raphael Rossiter
     * @date 06/04/2009
     *
     * @param helper
     * @return Quadra
     * @throws ControladorException
     */
    public Quadra validarQuadra(InserirQuadraHelper helper) throws ControladorException;
    
    /**
     * Integra��o com o conceito de face da quadra
     *
     * @author Raphael Rossiter
     * @date 21/05/2009
     *
     * @param idImovel
     * @return IntegracaoQuadraFaceHelper
     * @throws ControladorException
     */
    public IntegracaoQuadraFaceHelper integracaoQuadraFace(Integer idImovel) throws ControladorException;

	/**
	 * [UC0928] - Manter Situa��o Especial de Faturamento
	 * [FS0004] - Verificar Exist�ncia da Quadra
	 * 
	 * Pesquisa uma cole��o de quadra com filtrando tamb�m pelo setor comercial.
	 * 
	 */
	public Quadra obterQuadraSetorComercial(int idSetor, int numeroQuadra) throws ControladorException;
	
	/**
	 * Pesquisa os ids de Todas as localidaes.
	 *
	 * @author Hugo Leonardo
	 * @date 08/07/2010
	 *
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarIdsLocalidades() throws ControladorException;

	/**
	 * Pesquisa a localidade por id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */	
	public Localidade pesquisarLocalidade(Integer idLocalidade) throws ControladorException;
	
	/**
	 * Pesquisar os ids da Localidade
	 * 
	 * @author R�mulo Aur�lio
	 * @date 20/12/2011
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdLocalidadeCobrancaPorResultado() throws ControladorException;
	
}	
	
	

