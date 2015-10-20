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
package gcom.cadastro.cliente;

import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public interface IRepositorioImovelTarifaSocial {


	/**
	 * Pesquisa os imoveis com o filtro passado que tenha tarifa social 
	 *
	 * @param filtroClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a quantidade de tarifa social
	 *  @author Rafael Santos
	 *  @since 05/09/2006 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public int pesquisarQuantidadeImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Im�vel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomia(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usu�rio do im�vel j� est� relacionado em outro im�vel na tarifa social 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 02/01/2007
	 */
	public Collection verificarClienteCadastradoTarifaSocial(Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente est� vinculado a mais de uma economia como
	 * usu�rio
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 03/01/2007
	 */
	public int pesquisarClienteImovelEconomiaCount(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os clientes usu�rios das economias do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 03/01/2007
	 */
	public Collection pesquisarClientesUsuariosImovelEconomia(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o cliente usu�rio est� vinculado na tarifa social a outro
	 * im�vel ou economia com motivo de revis�o que permita recadastramento
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 04/01/2007
	 */
	public Collection pesquisarClientesUsuarioExistenteTarifaSocial(Integer idCliente) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe uma OS de vistoria para um im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarOSVistoriaImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe um motivo de exclus�o para o cliente que n�o permite
	 * recadastramento na tarifa social
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarClienteMotivoExclusaoRecadastramento(Integer idCliente) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os cliente a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 08/01/2007
	 */
	public Integer pesquisarClienteImovelEconomia(Integer idClienteImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe uma OS de vistoria para uma economia do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarOSVistoriaImovelEconomia(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorno o hist�rico medi��o atual do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 08/01/2007
	 */
	public Collection pesquisarMedicaoHistoricoImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorno o hist�rico de consumo atual do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 08/01/2007
	 */
	public Collection pesquisarConsumoHistoricoImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usu�rio
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 15/01/2007
	 */
	public Collection pesquisarDadosClienteTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna a tarifa social a partir do seu id
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 16/01/2007
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocial(Integer idTarifaSocial) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se existe tarifa social para o im�vel que n�o tenha sido
	 * exclu�do
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 16/01/2007
	 */
	public Collection pesquisarTarifaSocialImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes das economias do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa o cliente pelo seu id carregando o seu tipo
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 22/01/2007
	 */
	public Collection pesquisarClienteComClienteTipoTarifaSocial(Integer idCliente) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usu�rio para cada economia
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 25/01/2007
	 */
	public Collection pesquisarDadosClienteEconomiaTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usu�rio do im�vel j� est� relacionado em outro im�vel na tarifa social 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialUmaEconomia(Integer idCliente, Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usu�rio da economia do im�vel j� est� relacionado em outro im�vel na tarifa social 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(Integer idCliente, Integer idImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa a economia do im�vel pelo seu id
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 01/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloId(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Seta o indicador do nome da conta para 2 nos clientes propriet�rio e
	 * usu�rios
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 01/02/2007
	 */
	public void atualizarNomeContaClienteImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id da Economia do Im�vel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomiaImovelEconomia(Integer idImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna o id cliente usu�rio da economias do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 19/01/2007
	 */
	public Integer pesquisarClienteUsuarioImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna a economia do im�vel a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 15/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloCliente(Integer idClienteImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0009] - Manter Cliente 
	 * 
	 * Verifica se o cliente usu�rio est� na tarifa social 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 16/02/2007
	 */
	public Collection verificarClienteUsuarioCadastradoTarifaSocial(Integer idCliente) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Autor: Vivianne Sousa
	 * 
	 * Data: 27/10/2008
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ErroRepositorioException;
}
