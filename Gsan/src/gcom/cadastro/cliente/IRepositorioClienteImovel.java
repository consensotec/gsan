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

import gcom.cadastro.imovel.Imovel;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public interface IRepositorioClienteImovel {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param clienteImovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel,Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param clienteRelacaoTipo
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarClienteImovelResponsavelEsferaPoder(
			Imovel imovel, ClienteRelacaoTipo clienteRelacaoTipo)
			throws ErroRepositorioException;

	public String pesquisarNomeClientePorImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma a quantidade de cliente imovel com uma query especifica
	 * [UC0015] Filtrar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @author Rafael Santos
	 * @since 26/06/2006           
	 *            
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public int pesquisarQuantidadeClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException; 

	
	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovelRelatorio(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException; 
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * 
	 * @param idCliente, idImovel
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovel(Integer idCliente, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o nome, cnpj e id do cliente a partir do im�vel Autor: Rafael Corr�a Data:
	 * 25/09/2006
	 */
	public Object[] pesquisarDadosClienteRelatorioParcelamentoPorImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Autor: S�vio Luiz Data:
	 * 27/11/2006
	 */
	public Collection pesquisarParmsClienteImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Cliente, indicador de uso, motivo
	 * do fim da rela��o, pelo perfil do im�vel e pelo tipo da rela��o do cliente carregando o im�vel
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel carregando o im�vel, o cliente, o perfil do im�vel, 
	 * o org�o expedidor do RG e a unidade da federa��o
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel carregando os dados necess�rios para retornar o seu endere�o 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(
			Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * 
	 *Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Fl�vio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Retorna os clientes e suas rela��es tipos a partir do id do imovel
	 *
	 * @author S�vio Luiz
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection retornaClientesRelacao(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Retorna o cliente usu�rio
	 *
	 * @author S�vio Luiz
	 * @date 22/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	

	/**
	 * [UC0544] Gerar Arwuivo Texto do Faturamento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 4/04/2006
	 * 
	 * 
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelGerarArquivoFaturamento()
			throws ErroRepositorioException;
	

	/**
	 * 
	 * Retorna o cliente usu�rio
	 *
	 * @author S�vio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Retorna o tipo da rela��o do cliente com indicador nome conta
	 *
	 * @author Rafael Corr�a
	 * @date 17/05/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaTipoRelacaoClienteImovelNomeConta(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o rg do cliente do parcelamento a partir do idParcelamento
	 * Autor: Vivianne Sousa 
	 * Data: 20/06/2007
	 */
	public Object[] pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento)
			throws ErroRepositorioException ;
	

	/**
	 * 
	 * Retorna o cliente usu�rio
	 *
	 * @author S�vio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa o codigo da corta e o sequencia da rota 
	 * apartir do codigo do cliente
	 * 
	 * @date 19/09/2007
	 * @author Rafael Pinto
	 * @throws ErroRepositorioException
	 * @return String[2] onde String[0]=codigo e String[1]=sequencial
	 */
	public Object[] pesquisarCodigoSequencialRotaPorUsuario(Integer idCliente)
	 			throws ErroRepositorioException ;
	
	/**
	 * 
	 *Retorna o cliente proprietario a partir do id do imovel
	 *
	 * @author Vinicius Medeiros
	 * @date 29/08/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteProprietario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0014] Manter Im�vel
	 * [FS0017] Registra Fim de Rela��o do(s) Cliente(s) com Im�vel
	 *
	 * @author Ana Maria
	 * @date 13/10/2008
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(Integer idImovel)
		throws ErroRepositorioException;
	
	
	/**
	 * EMITIR CONTAS CAEMA
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel cujo cliente seja o responsavel pela conta
	 * 
	 * Autor: Tiago Moreno
	 * 
	 * Data: 29/10/2008
	 */
	public Collection pesquisarClienteImovelResponsavelConta(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar Cliente Imovel Atualiza��o Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarClienteImovelAtualizacaoCadastral(Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * 
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Integer pesquisarEsferaPoderClienteImovelResponsavel(Integer imovel)
			throws ErroRepositorioException;
	
	/**
	 * @author Daniel Alves
	 * @date 02/09/2010
	 * @param idClienteImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ErroRepositorioException;

	/**
	 * 
	 * Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Mariana Victor
	 * @date 17/01/2011
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public ClienteImovel pesquisarClienteImovelOSFiscalizada(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0003] - Substituir Cliente Usu�rio do Im�vel
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvinculaClienteUsuarioAtualDoImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0003] - Substituir Cliente Usu�rio do Im�vel
	 * 
	 * @author Mariana Victor
	 * @date 30/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndicadorNomeConta(Integer idCliente, Integer idImovel) 
			throws ErroRepositorioException;
	/**
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * [SB0005] - Gerar Negativa��o para o Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @date 06/12/2011
	 */
	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(
			Integer idImovel, Integer idClienteRelacaoTipo)
			throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0003] - Substituir Cliente Usu�rio do Im�vel
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvinculaClienteUsuarioAtualDoImovel(Integer idImovel, Date dataFim) 
			throws ErroRepositorioException;

	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0013] - Substituir Cliente Respons�vel do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvincularClienteResponsavelImovel(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException ;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0013] - Substituir Cliente Respons�vel do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void associarClienteResponsavelImovel(Integer idImovel,
			Integer idCliente, Short icNomeConta) throws ErroRepositorioException ;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0014] - Substituir Cliente Propriet�rio do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvincularClienteProprietarioImovel(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException ;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0014] - Substituir Cliente Propriet�rio do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void associarClienteProprietarioImovel(Integer idImovel,
			Integer idCliente, Short icNomeConta) throws ErroRepositorioException ;
	
	/**
	 * [UC0150] - Retifcar Conta
	 * 
	 * Pesquisar Cliente Imovel
	 * 
	 * @author Rafael Corr�a
	 * @date 16/04/2015
	 */
	public ClienteImovel pesquisarClienteImovel(String idImovel, int indicadoNomeConta) throws ErroRepositorioException;
	
	/**
	 * [UC 1366] - Alterar V�nculo de Clientes com o Im�vel e Contas
	 * 
	 * @author Rafael Corr�a
	 * @date 24/04/2015
	 */
	public Date pesquisarMenorDataClienteImovel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC 1366] - Alterar V�nculo de Clientes com o Im�vel e Contas
	 * 
	 * @author Rafael Corr�a
	 * @date 24/04/2015
	 */
	public Collection pesquisarClienteImovelPeriodo(Integer idImovel, Date periodo) throws ErroRepositorioException;
	
	/**
	 * [UC 1366] - Alterar V�nculo de Clientes com o Im�vel e Contas
	 * 
	 * @author Rafael Corr�a
	 * @date 24/04/2015
	 */
	public Collection pesquisarClienteImovelPeriodo(Integer idCliente, Integer idImovel, Integer idTipoRelacao, Date periodo) throws ErroRepositorioException;
	
//	Pesquisar Cliente Imovel
	public Collection<ClienteImovel> pesquisarClientesImovelDadosComplementares(Integer idImovel) 
			throws ErroRepositorioException;
}
