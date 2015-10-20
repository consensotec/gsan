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
package gcom.seguranca.acesso.usuario;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorCliente
 * 
 * @author S�vio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorUsuarioLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Inseri um usuario com seus grupos
	 * 
	 * [UC0230]Inserir Usuario
	 * 
	 * @author Thiago Toscano
	 * @date 19/05/2006
	 * 
	 * @param usuario
	 * @param idGrupo
	 *            grupos que o usuario faz parte
	 * @throws ControladorException
	 */
	public void inserirUsuario(Usuario usuario, Integer[] idGrupos, Usuario usuarioLogado, 
			String idSolicitacaoAcesso)
			throws ControladorException;

	/**
	 * Atualiza um usuario com seus grupos
	 * 
	 * [UC0231]Inserir Usuario
	 * 
	 * @author Thiago Toscano
	 * @date 19/05/2006
	 * 
	 * @param usuario
	 * @param idGrupo
	 *            grupos que o usuario faz parte
	 * @throws ControladorException
	 */
	 public void atualizarUsuario(Usuario usuario, Integer[] idGrupos, String processo, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * [UC0291] Bloquear/Desbloquear Acesso Usuario
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 09/06/2006
	 * 
	 * @param usuario
	 * @throws ControladorException
	 */

	public void bloquearDesbloquearUsuarioSituacao(Usuario usuario)
			throws ControladorException;

	/**
	 * M�todo que consulta os grupos do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuario(Integer idUsuario)
			throws ControladorException;

	/**
	 * M�todo que consulta as abrang�ncias dos usu�rio pelos os ids das
	 * abrang�ncias superiores e com o id da abrang�ncia diferente do id da
	 * abrang�ncia do usu�rio que est� inserindo(usu�rio logado)
	 * 
	 * @author S�vio Luiz
	 * @date 28/06/2006
	 */
	public Collection pesquisarUsuarioAbrangenciaPorSuperior(
			Collection colecaoUsuarioAbrangencia,
			Integer idUsuarioAbrangenciaLogado) throws ControladorException;

	/**
	 * Informa o n�mero total de registros de usuario grupo, auxiliando o
	 * esquema de pagina��o
	 * 
	 * @author S�vio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return N�mero de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public int totalRegistrosPesquisaUsuarioGrupo(
			FiltroUsuarioGrupo filtroUsuarioGrupo) throws ControladorException;

	/**
	 * Informa o n�mero total de registros de usuario grupo, auxiliando o
	 * esquema de pagina��o
	 * 
	 * @author S�vio Luiz
	 * @date 30/06/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return N�mero de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public Collection pesquisarUsuariosDosGruposUsuarios(
			FiltroUsuarioGrupo filtroUsuarioGrupo, Integer numeroPagina)
			throws ControladorException;

	/**
	 * Remove usuario(s)
	 * 
	 * [UC0231] Manter Usuario
	 * 
	 * @author S�vio Luiz
	 * @date 07/07/2006
	 * @param idsUsuario
	 * @param usuario
	 * @throws ControladorException
	 */
	public void removerUsuario(String[] idsUsuario, Usuario usuario, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * M�todo que consulta os grupos funcion�rios opera��es passando os ids dos
	 * grupos
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */

	public Collection pesquisarGruposFuncionalidadeOperacoes(Integer[] idsGrupos)
			throws ControladorException;

	/**
	 * M�todo que consulta os grupos funcion�rios opera��es passando os ids dos
	 * grupos e o id da funcionalidade
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
			Integer[] idsGrupos, Integer idFuncionalidade)
			throws ControladorException;

	/**
	 * M�todo que consulta os usu�rios restrin��o passando os ids dos grupos , o
	 * id da funcionalidade e o id do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarUsuarioRestrincao(Integer[] idsGrupos,
			Integer idFuncionalidade, Integer idUsuario)
			throws ControladorException;

	/**
	 * M�todo que consulta as funcionalidades da(s) funcionalidade(s)
	 * princpial(is)
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarFuncionanidadesDependencia(
			Collection idsFuncionalidades) throws ControladorException;

	/**
	 * M�todo que consulta as opera��es da(s) funcionalidade(s)
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarOperacoes(Collection idsFuncionalidades)
			throws ControladorException;

	/**
	 * M�todo que consulta as opera��es da(s) funcionalidade(s) e das
	 * funcionalidades dependencia
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection recuperarOperacoesFuncionalidadesEDependentes(
			Integer idFuncionalidade) throws ControladorException;

	/**
	 * Retorna 2 cole��es e um array ,com os valores que v�o retornar
	 * marcados,uma com as permiss�es do usu�rio que ele possa marcar/desmarcar
	 * e a outra o usu�rio logado n�o vai poder marcar/desmarcar
	 * 
	 * [UC0231] - Manter Usu�rio [SB0010] - Selecionar Permiss�es Especiais
	 * (n�2)
	 * 
	 * @author S�vio Luiz
	 * @date 13/07/2006
	 */
	public Object[] pesquisarPermissoesEspeciaisUsuarioEUsuarioLogado(
			Usuario usuarioAtualizar, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Retorna um array com os ids dos objetos da cole��o
	 * 
	 * @author S�vio Luiz
	 * @date 13/07/2006
	 */
	public String[] retornarPermissoesMarcadas(Collection permissoesEspeciais);

	/**
	 * M�todo que atualiza o controle de acesso do usu�rio
	 * 
	 * [UC0231] - Manter Usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 14/07/2006
	 * 
	 * @param String[]
	 * @param grupoFuncionalidadeOperacao
	 */
	public void atualizarControleAcessoUsuario(String[] permissoesEspeciais,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap,
			Usuario usuarioAtualizar, Integer[] idsGrupos,
			String permissoesCheckBoxVazias, Usuario usuarioLogado)
			throws ControladorException;

	/**
	 * Retorna um map com o indicador dizendo se vai aparecer
	 * marcado(1),desmarcado(2) ou desabilitado(3) para cada opera��o da
	 * funcionalidade escolhida
	 * 
	 * [UC0231] - Manter Usu�rio [SB0008] - Selecionar Restrin��es (n�2)
	 * 
	 * @author S�vio Luiz
	 * @date 17/07/2006
	 */
	public Map<Integer, Map<Integer, Collection<Operacao>>> organizarOperacoesComValor(
			Integer codigoFuncionalidade,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap, Integer[] idsGrupos,
			Usuario usuarioAtualizar) throws ControladorException;

	/**
	 * Retorna um map com o indicador dizendo se vai aparecer
	 * marcado(1),desmarcado(2) ou desabilitado(3) para cada opera��o da
	 * funcionalidade escolhida e a cole��o com as opera��es e funcionalidades
	 * que que foram desmarcados
	 * 
	 * [UC0231] - Manter Usu�rio [SB0008] - Selecionar Restrin��es (n�2)
	 * 
	 * @author S�vio Luiz
	 * @date 17/07/2006
	 */
	public Map<Integer, Map<Integer, Collection<Operacao>>> recuperaFuncionalidadeOperacaoRestrincao(
			Integer codigoFuncionalidade, String[] idsOperacoes,
			Map<Integer, Map<Integer, Collection<Operacao>>> funcionalidadesMap)
			throws ControladorException;
	
	/**
	 * M�todo que consulta os grupos do usu�rio da tabela grupoAcessos
	 * 
	 * @author S�vio Luiz
	 * @date 21/02/2007
	 */
	public Collection pesquisarGruposUsuarioAcesso(Collection colecaoUsuarioGrupos)throws ControladorException;
	

	/**
	* M�todo que consulta o nome do usu�rio de uma guia de devolu��o,
	* passando por par�metro o id da guia de devolucao
	*
	* @author Daniel Alves
	* @date 22/02/2010
	*/
	public String pesquisarUsuarioPorGuiaDevolucao(Integer idGuiaDevolucao)throws ControladorException;
	
	
	/**
	 * [UC0204] Consultar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/11/2010
	 */
	public Collection pesquisarUsuario(Integer idOperacao,
			Integer idImovel,String referenciaConta)throws ControladorException;
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public Collection pesquisarGrupoUsuario(Integer idUsuario)throws ControladorException;


	/**
	 * [UC0146] Manter Conta
	 * [SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public BigDecimal pesquisarMaiorCompetenciaRetificacaoGrupo()throws ControladorException;
	
	/**
	 * [UC0230] Inserir Usu�rio
	 * [FS0020] Verificar exist�ncia de usu�rio batch
	 * [FS0021] Verificar usu�rio batch
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioRotinaBatch() throws ControladorException;	
	
	/**
	 * [UC0230] Inserir Usu�rio
	 * [FS0022] Verificar exist�ncia de usu�rio internet
	 * [FS0023] Verificar usu�rio internet
	 * 
	 * @author Paulo Diniz
	 * @throws ControladorException 
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioInternet() throws ControladorException;
	

	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Usuarios por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteUsuario(String valor)throws ControladorException;
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param idUsuario
	 * @return UsuarioBanco
	 * @throws ErroRepositorioException
	 */
	public UsuarioBanco validarUsuarioBaseLogado(Integer idUsuario)throws ControladorException;
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * [FS-0001] - Validar usu�rio da base para usu�rio logado
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param loginUsuario
	 * @return String
	 * @throws ErroRepositorioException
	 */
	public String validarUsuarioBaseLogadoDicionarioDados(String loginUsuario, SistemaParametro sistemaParametro) throws ControladorException;

	
	/**
	 * [UC0279] Manter Grupo
	 * RM 3892.1 - Implantar Normas de Senhas no GSAN
	 * @author Am�lia Pessoa
	 * @date 28/12/2011
	 */
	public void atualizarGrupoPermissaoEspecial(
			String[] permissoesEspeciaisMarcadas, Integer grupoId) throws ControladorException;
	
	/**
	 * [UC1274] Registrar Acessos Realizados na Loja Virtual
	 * [FS0001] - Verificar exist�ncia do registro
	 * @author Arthur Carvalho
	 * @date 09/01/2012
	 */
	public boolean verificarExistenciaAcessoLojaVirtual(String ip, String codigoTipoAtendimento, Short indicadorServicoExecutado) throws ControladorException ;
}

