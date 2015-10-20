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
package gcom.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * < <Descri��o da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorTarifaSocialLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Faz verifica��es da inser��o de dados de tarifa social de um im�vel
	 * 
	 * @param idImovel
	 *            C�digo do Im�vel
	 */
	public void verificarProprietarioImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Faz verifica��es da inser��o de dados de tarifa social de um im�vel
	 * 
	 * @param idImovel
	 *            C�digo do Im�vel
	 */
	public Cliente verificarUsuarioImovel(Integer idImovel)
			throws ControladorException;
	
	
	/**
	 * Verificar os pr�-requisitos para o cadastramento de um im�vel na tarifa
	 * social
	 * 
	 * @param idImovel
	 *            C�digo do imovel
	 */
	public String[] verificarPreRequisitosCadastramentoTarifaSocial(Integer idImovel)
			throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param tarifaSocialCartaoTipo
	 *            Descri��o do par�metro
	 */
	public void atualizarTarifaSocialCartaoTipo(
			TarifaSocialCartaoTipo tarifaSocialCartaoTipo)
			throws ControladorException;
	
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * [FS0014] - Verificar duplicidade do cart�o do programa social
	 * @param numeroCartao
	 * @throws ControladorException
	 */
	public void verificarDuplicidadeCartaoProgramaSocial(Long numeroCartao, 
			TarifaSocialCartaoTipo tipoCartao, Integer idImovel) throws ControladorException;

	
	/**
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @param clienteImovel
	 *            Descri��o do par�metro
	 * @param numeroCartaoSocial
	 *            Descri��o do par�metro
	 * @param dataValidadeCartaoSocial
	 *            Descri��o do par�metro
	 * @param numeroParcelasCartaoSocial
	 *            Descri��o do par�metro
	 * @param valorRendaFamiliar
	 *            Descri��o do par�metro
	 */
	public String[] verificarPreenchimentoInserirDadosTarifaSocial(Long numeroCelpe, 
			BigDecimal areaConstruida, BigDecimal numeroIPTU, Integer idImovel, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar, String tarifaSocialCartaoTipo,
			String tipoRenda) throws ControladorException;
	
	/**
	 * 
	 * @param clienteImovelEconomia
	 * @param numeroCartaoSocial
	 * @param dataValidadeCartaoSocial
	 * @param numeroParcelasCartaoSocial
	 * @param consumoMedio
	 * @param valorRendaFamiliar
	 * @param tarifaSocialCartaoTipo
	 * @param tipoRenda
	 * @param imovel
	 * @throws ControladorException
	 */
	public String[] verificarPreenchimentoInserirDadosTarifaSocialMultiplas(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovelEconomia, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException;

	/**
	 * Atualiza um tarifaSocialDadoEconomia
	 * 
	 * @param tarifaSocialDadoEconomia
	 *            Descri��o do par�metro
	 */
	public void atualizarTarifaSocialDadoEconomia(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException;

	/**
	 * Enquadra um imovel no regime de tarifa social
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param tarifaSocialDado
	 *            Descri��o do par�metro
	 * @param tarifaSocialDadoEconomia
	 *            Descri��o do par�metro
	 */
/*	public void inserirDadosTarifaSocialImovel(
			TarifaSocialDado tarifaSocialDado,
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException;
	*/
	
	
	/**
	 * Atualiza o perfil do im�vel para tarifa social
	 * @param imovel
	 * @throws ControladorException
	 */
	public void atualizarImovelPerfilTarifaSocial(Imovel imovel) throws ControladorException;
	

	/**
	 * Atualiza o enquadramento de um imovel no regime de tarifa social
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param tarifaSocialDado
	 *            Descri��o do par�metro
	 * @param tarifaSocialDadoEconomia
	 *            Descri��o do par�metro
	 */
	public void atualizarDadosTarifaSocialImovel(
			TarifaSocialDadoEconomia tarifaSocialDadoEconomia)
			throws ControladorException;
	
	
	/**
	 * 
	 * @param tarifaSocialDado
	 * @throws ControladorException
	 */
//	public void atualizarTarifaSocialDadoRecadastramento(TarifaSocialDado tarifaSocialDado) 
	//throws ControladorException;	

	/**
	 * Pesquisa uma cole��o de Tarifa Social Dado Economia.
	 * 
	 * @param filtroTarifaSocialDadoEconomia
	 *            Description of the Parameter
	 * @author Thiago
	 * @date 12/12/2005           
	 * @return Description of the Return Value
	 */

	public Collection pesquisarTarifaSocialDadoEconomia(
			FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia)
			throws ControladorException;
	
	
	/**
	 * M�todo que remover o imover da tarifa social 
	 * 
	 * @param idImovel
	 * @param idMotivoTarifaSocial
	 * @throws ControladorException
	 */
	public void removerImovelTarfiaSocial( Integer idImovel, Integer idMotivoTarifaSocial) throws ControladorException;

	/**
	 * Pesquisa todas as tarifas sociais com o filtro passado
	 * 	
	 * @param filtroClienteImovel
	 * @return
	 */
	public Collection pesquisarImovelTarfiaSocial( FiltroClienteImovel filtroClienteImovel, Integer numeroPagina ) throws ControladorException;
	
	/**
	 * M�todo que verifica se o usuario esta cadastrado em outro imovel que esteja na tarifa social e 
	 * verifica se ja esta cadastrado como usuario de algum imovel economia,
	 *
	 * Caso o idImovel seja diferente de nula ele verifa se o usuario esta cadastrado num imovel diferente 
	 * do id passado.
	 *
	 * Caso o idImovelEconomia seja diferente de nula ele verifaca se o usuario esta cadastrado num imovel economia 
	 * do idImovelEconomia passado.
	 *
	 * @param idImovel
	 * @param idImovelEconomia
	 * @param idEconomiaAtual
	 * @param idClienteUsuario
	 */
	public void verificarClienteUsuarioEmOutroEconomia(Integer idImovel, Integer idImovelEconomia, Integer idClienteUsuario) throws ControladorException;
	
	/**
	 * M�todo que pesquisa a quantidade de tarifa social
	 * 
	 *  @author Rafael Santos
	 *  @since 05/09/2006
	 * 
	 * @param filtroClienteImovel
	 * @return
	 * @throws ControladorException
	 */
	public int pesquisarQuantidadeImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException;
	
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
			throws ControladorException;
	
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
		throws ControladorException;
	
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
			Integer idCliente) throws ControladorException;
	
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
	public int verificarExistenciaDebitosCliente(Integer idCliente)
			throws ControladorException;
	
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
		throws ControladorException;
	
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
		throws ControladorException;
	
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
	public void verificarClienteMotivoExclusaoRecadastramento(Integer idCliente)
			throws ControladorException;
	
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
			throws ControladorException;
	
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
			throws ControladorException;
	
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
		throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * [FS0008] - Verificar Preenchimento dos Campos
	 * 
	 * Verificar o preenchimento dos campos para uma economia
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corr�a
	 * 
	 * @param clienteImovel
	 *            Descri��o do par�metro
	 * @param numeroCartaoSocial
	 *            Descri��o do par�metro
	 * @param dataValidadeCartaoSocial
	 *            Descri��o do par�metro
	 * @param numeroParcelasCartaoSocial
	 *            Descri��o do par�metro
	 * @param valorRendaFamiliar
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void verificarPreenchimentoManterDadosTarifaSocial(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovel, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do im�vel
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa o cliente pelo seu id carregando o seu tipo
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 22/01/2007
	 */
	public Cliente pesquisarClienteComClienteTipoTarifaSocial(Integer idCliente) 
			throws ControladorException;
	
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
			throws ControladorException;
	
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
			throws ControladorException;
	
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
		throws ControladorException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * [FS0008] - Verificar Preenchimento dos Campos
	 * 
	 * Verificar o preenchimento dos campos para m�ltiplas economias
	 * 
	 * @date 18/01/2007
	 * @author Rafael Corr�a
	 * @throws ControladorException
	 * 
	 * @param clienteImovel
	 *            Descri��o do par�metro
	 * @param numeroCartaoSocial
	 *            Descri��o do par�metro
	 * @param dataValidadeCartaoSocial
	 *            Descri��o do par�metro
	 * @param numeroParcelasCartaoSocial
	 *            Descri��o do par�metro
	 * @param valorRendaFamiliar
	 *            Descri��o do par�metro
	 * @throws ControladorException
	 */
	public void verificarPreenchimentoManterDadosTarifaSocialMultiplasEconomias(
			Long numeroCelpe, BigDecimal areaConstruida, BigDecimal numeroIPTU,
			Integer idImovelEconomia, String numeroCartaoSocial,
			String dataValidadeCartaoSocial, String numeroParcelasCartaoSocial,
			Integer consumoMedio, BigDecimal valorRendaFamiliar,
			String tarifaSocialCartaoTipo, String tipoRenda)
			throws ControladorException;
	
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
		throws ControladorException;
	
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
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Im�vel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomiaImovelEconomia(Integer idImovelEconomia)  
			throws ControladorException;

     /**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Recadastrar, atualiza ou remove a tarifa social
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 13/02/2007
	 */
	public AtendimentoMotivoEncerramento manterTarifaSocial(Imovel imovelSessao,
			Collection colecaoTarifaSocialHelperAtualizar,
			Collection colecaoImoveisExcluidosTarifaSocial,
			Collection colecaoTarifaSocialExcluida,
			Collection colecaoTarifasSociaisRecadastradas,  Usuario usuarioLogado)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Recadastrar ou insere a tarifa social
	 * 
	 * Autor: Rafael Corr�a
	 * 
	 * Data: 13/02/2007
	 */
	public void inserirTarifaSocial(Imovel imovelSessao,
			ClienteImovel clienteImovel,
			RegistroAtendimento registroAtendimento,
			RegistroAtendimentoUnidade registroAtendimentoUnidade,
			Usuario usuarioLogado, Integer idTarifaSocialDadoEconomiaExcluida,
			Collection colecaoTarifaSocialDadoEconomia,
			Collection colecaoClienteImovelEconomia,
			Collection colecaoTarifaSocialRecadastrar, Imovel imovelAtualizar,
			Collection colecaoImovelEconomiaAtualizar)
			throws ControladorException;
	
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
		throws ControladorException;
	
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
		throws ControladorException;

	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Autor: Vivianne Sousa
	 * 
	 * Data: 27/10/2008
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ControladorException;
	
}
