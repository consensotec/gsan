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

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * < <Descri��o da Classe>>
 * 
 * @author S�vio Luiz
 * @created 27 de Julho de 2006
 */
public interface IRepositorioUsuario {

	/**
	 * M�todo que consulta os grupos do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuario(Integer idUsuario)
			throws ErroRepositorioException;

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
			Integer idUsuarioAbrangenciaLogado) throws ErroRepositorioException;

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
			FiltroUsuarioGrupo filtroUsuarioGrupo)
			throws ErroRepositorioException;

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
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta os grupos funcion�rios opera��es passando os ids dos
	 * grupos
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */

	public Collection pesquisarGruposFuncionalidadesOperacoes(Integer[] idsGrupos)
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta os grupos funcion�rios opera��es passando os ids dos
	 * grupos e o id da funcionalidade
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
			Integer[] idsGrupos, Integer idFuncionalidade)
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta os usu�rios restrin��o passando os ids dos grupos , o
	 * id da funcionalidade e o id do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarUsuarioRestrincao(Integer[] idsGrupos,
			Integer idFuncionalidade, Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta as funcionalidades da(s) funcionalidade(s)
	 * princpial(is)
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarFuncionanidadesDependencia(
			Collection idsFuncionalidades) throws ErroRepositorioException;

	/**
	 * M�todo que consulta as opera��es da(s) funcionalidade(s)
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarOperacoes(Collection idsFuncionalidades)
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta as permiss�es especiais do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuario(Integer idUsuario)
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta as permiss�es especiais do usu�rio com os parametros
	 * das permiss�es de outro usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuarioComPermissoes(
			Integer idUsuario, Collection permissoesEspeciais)
			throws ErroRepositorioException;

	/**
	 * M�todo que consulta as permiss�es especiais do usu�rio sem os parametros
	 * das permiss�es de outro usu�rio.Recupera todas as permiss�es do usuario
	 * que n�o tem a permiss�o de outro usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuarioSemPermissoes(
			Integer idUsuario, Collection permissoesEspeciais)
			throws ErroRepositorioException;

	/**
	 * Essa verifica��o � preciso para quando for, [SB0011]- Atualizar Controles
	 * de Acesso no [SB0230]-Manter Usu�rio ,saber que grupos daquela
	 * funcionalidade daquela opera��o ser�o inseridos na tabela
	 * UsuarioGrupoRestrincao
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarIdsGruposPelaFuncionalidadeGruposOperacao(
			Integer[] idsGrupos, Integer idFuncionalidade, Integer idOperacao)
			throws ErroRepositorioException;
	
	/**
	 * M�todo que consulta os grupos do usu�rio da tabela grupoAcessos
	 * 
	 * @author S�vio Luiz
	 * @date 21/02/2007
	 */
	public Collection pesquisarGruposUsuarioAcesso(Collection colecaoUsuarioGrupos)
			throws ErroRepositorioException; 
	
	
	/**
	* M�todo que consulta o nome do usu�rio de uma guia de devolu��o,
	* passando por par�metro o id da guia de devolucao
	*
	* @author Daniel Alves
	* @date 22/02/2010
	*/
	public String pesquisarUsuarioPorGuiaDevolucao(	Integer idGuiaDevolucao)
	throws ErroRepositorioException;
	
	
	/**
	 * M�todo para pesquisar os usu�rios de uma Unidade Organizacional
	 * 
	 * @author Daniel Alves
	 * @date 11/06/2010
	 */
	public Collection pesquisarUsuariosUnidadeOrganizacional(Integer idUnidadeOrganizacional) 
	      throws ErroRepositorioException;
	
	/**
	 * [UC0204] Consultar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/11/2010
	 */
	public Collection pesquisarUsuario(Integer idOperacao,
			Integer idImovel,String referenciaConta)
			throws ErroRepositorioException ;
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public Collection pesquisarGrupoUsuario(Integer idUsuario)throws ErroRepositorioException;	


	/**
	 * [UC0146] Manter Conta
	 * [SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public BigDecimal pesquisarMaiorCompetenciaRetificacaoGrupo()throws ErroRepositorioException;
	
	/**
	 * [UC0230] Inserir Usu�rio
	 * [FS0020] Verificar exist�ncia de usu�rio batch
	 * [FS0021] Verificar usu�rio batch
	 * 
	 * @author Paulo Diniz
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioRotinaBatch()throws ErroRepositorioException;
	
	/**
	 * [UC0230] Inserir Usu�rio
	 * [FS0022] Verificar exist�ncia de usu�rio internet
	 * [FS0023] Verificar usu�rio internet
	 * 
	 * @author Paulo Diniz
	 * @date 03/03/2011
	 */
	public Usuario pesquisarUsuarioInternet()throws ErroRepositorioException;
	
	
	/**
	 * 
	 * Filtra os Usuarios por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteUsuario(String valor)throws ErroRepositorioException;

	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param idUsuario
	 * @throws ErroRepositorioException
	 */
	public UsuarioBanco validarUsuarioBaseLogado(Integer idUsuario)throws ErroRepositorioException;
	
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
	 * @throws ErroRepositorioException
	 */
	public String validarUsuarioBaseLogadoDicionarioDados(String loginUsuario, SistemaParametro sistemaParametro) throws ErroRepositorioException;

	/**
	 * [UC0279] Manter Grupo
	 * RM 3892.1 - Implantar Normas de Senhas no GSAN
	 * @author Am�lia Pessoa
	 * @date 28/12/2011
	 */
	public void excluirPermissoesEspeciais(Integer grupoId)throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 18/01/2012
	 *
	 * @throws ErroRepositorioException
	 */
	public void inserirAcessoLojaVirtual(String codigoTipoAtendimento, String ipAcesso, Short indicadorServicoExecutado) throws ErroRepositorioException ;
	
	/**
	 * [UC1274] Registrar Acessos Realizados na Loja Virtual
	 * 
	 * @author Anderson Cabral
	 * @date 14/07/2013
	 */
	public AcessoLojaVirtual retornaAcessoLojaVirtual(String ip, String codigoTipoAtendimento) throws ErroRepositorioException ;
	
	/**
	 * [UC1274] Registrar Acessos Realizados na Loja Virtual
	 * [FS0001] - Verificar exist�ncia do registro
	 * @author Arthur Carvalho
	 * @date 09/01/2012
	 */
	public boolean verificarExistenciaAcessoLojaVirtual(String ip, String codigoTipoAtendimento) throws ErroRepositorioException ;

}
