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
package gcom.cadastro.imovel;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

/**
 * < <Descri��o da Interface>>
 * 
 * @author Administrador
 * @created 7 de Junho de 2004
 */
public interface ControladorImovelRemote extends javax.ejb.EJBObject {

	/**
	 * Insere um imovel na base
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void inserirImovel(Imovel imovel) throws RemoteException;

	/**
	 * Retorna a quantidade de economias de um im�vel
	 * 
	 * @param imovel
	 *            Im�vel que ser� consultado
	 * @return Quantidade de economias
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public int obterQuantidadeEconomias(Imovel imovel) throws RemoteException;

	/**
	 * Retorna a cole��o de economias de um im�vel
	 * 
	 * @param imovel
	 *            Im�vel que ser� consultado
	 * @return Quantidade de economias
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public Collection obterColecaoImovelSubcategorias(Imovel imovel)
			throws RemoteException;

	/**
	 * Retorna a quantidade de categorias de um im�vel
	 * 
	 * @param imovel
	 *            Im�vel que ser� consultado
	 * @return uma cole��o de categorias
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel)
			throws RemoteException;

	/**
	 * 
	 * @return Quantidade de categorias cadastradas no BD
	 * @throws RemoteException
	 */
	public Object pesquisarObterQuantidadeCategoria() throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param subCategorias
	 *            Description of the Parameter
	 * @param enderecoImoveis
	 *            Description of the Parameter
	 * @param clientes
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public Integer inserirImovelRetorno(Imovel imovel,
			Collection subCategorias, Collection enderecoImoveis,
			Collection clientes, Collection colecaoClientesImoveisRemovidos,
			Collection colecaoImovelSubcategoriasRemovidas)
			throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovelSubcategoria
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void inserirImovelSubCategoria(ImovelSubcategoria imovelSubcategoria)
			throws RemoteException;

	/**
	 * inseri o im�vel economia e o cliente imovel economia do im�vel
	 * subcategoria
	 * 
	 * @param imoveisEconomias
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void informarImovelEconomias(Collection imoveisEconomias)
			throws RemoteException;

	/**
	 * remove o im�vel economia e o cliente imovel economia do im�vel
	 * subcategoria
	 * 
	 * @param imovelEconomia
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void removerImovelEconomia(ImovelEconomia imovelEconomia)
			throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovel(Imovel imovel) throws RemoteException,
			ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param subcategorias
	 *            Descri��o do par�metro
	 * @param enderecoImovel
	 *            Descri��o do par�metro
	 * @param clientes
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovel(Imovel imovel, Collection subcategorias,
			Collection enderecoImovel, Collection clientes)
			throws RemoteException;

	/**
	 * verifica se existe algum iptu no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Descri��o do par�metro
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param numeroIptu
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Description of the Exception
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void verificarExistenciaIPTU(Collection imoveisEconomia,
			Imovel imovel, String numeroIptu, Date dataUltimaAlteracao)
			throws RemoteException, ErroRepositorioException;

	/**
	 * verifica se existe algum numero da celpe no imovel ou imovelEconomia
	 * 
	 * @param imoveisEconomia
	 *            Descri��o do par�metro
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param numeroCelpe
	 *            Description of the Parameter
	 * @param dataUltimaAlteracao
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Description of the Exception
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void verificarExistenciaCelpe(Collection imoveisEconomia,
			Imovel imovel, String numeroCelpe, Date dataUltimaAlteracao)
			throws RemoteException, ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param ids
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void removerImovel(String[] ids) throws RemoteException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovelSubcategoria
	 *            Descri��o do par�metro
	 * @exception RemoteException
	 *                Descri��o da exce��o
	 */
	public void atualizarImovelSubCategoria(
			ImovelSubcategoria imovelSubcategoria) throws RemoteException;

	// ----- Metodo Para Carregar o Objeto ImovelMicromedicao
	// ----- Fl�vio Leonardo
	public Collection carregarImovelMicromedicao(Collection imoveisMicromedicao)
			throws RemoteException;
	
	/**
	 * Permite pesquisar entidades beneficentes
	 * [UC0389] Inserir Autoriza��o para Doa��o Mensal
	 * @author  C�sar Ara�jo
	 * @date    30/08/2006
	 * @param   idEntidadeBeneficente - C�digo da entidade beneficente
	 * @return  Collection<EntidadeBeneficente> - Cole��o de entidades beneficentes
	 * @throws  ControladorException
	 */
	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(Integer idEntidadeBeneficente) throws RemoteException;
	
	/**
	 * Permite pesquisar im�veis doa��o 
	 * [UC0389] Inserir Autoriza��o para Doa��o Mensal
	 * @author  C�sar Ara�jo
	 * @date    30/08/2006
	 * @param   idImovelDoacao - C�digo do im�vel doa��o
	 * @return  Collection<ImovelDoacao> - Cole��o de im�veis doa��o
	 * @throws  ControladorException
	 */
	public Collection<ImovelDoacao> pesquisarImovelDoacao(FiltroImovelDoacao filtroImovelDoacao) throws RemoteException;

	/**
	 * Permite verificar se existe um determinado im�vel doa��o 
	 * [UC0389] Inserir Autoriza��o para Doa��o Mensal
	 * @author  C�sar Ara�jo
	 * @date    30/08/2006
	 * @param   idImovel - C�digo do im�vel
	 * @param   idEntidadeBeneficente - C�digo da entidade beneficente
	 * @return  ImovelDoacao - Retorna um im�vel doa��o caso a combina��o de im�vel e entidade beneficente exista.  
	 * @throws  ControladorException
	 */
	public ImovelDoacao verificarExistenciaImovelDoacao(Integer idImovel, Integer idEntidadeBeneficente) throws RemoteException;
		
	/**
	 * Permite atualizar as informa��es do im�vel doa��o 
	 * [UC0390] Manter Autoriza��o para Doa��o Mensal
	 * @author  C�sar Ara�jo
	 * @date    30/08/2006
	 * @param   imovelDoacao - C�digo do im�veo doa��o
	 * @throws  ControladorException
	 */
	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao) throws RemoteException;


}