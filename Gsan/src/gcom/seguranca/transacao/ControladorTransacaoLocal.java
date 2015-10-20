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
package gcom.seguranca.transacao;

import gcom.cadastro.DadosCadastraisTransacaoBatchHelper;
import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.atualizacaocadastral.bean.DadosTabelaAtualizacaoCadastralHelper;
import gcom.interceptor.ObjetoTransacao;
import gcom.relatorio.cadastro.GerarRelatorioClienteCpfCnpjValidadosHelper;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;


/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorTransacao
 * 
 * @author Thiago Toscano
 * @created 09 de Fevereiro de 2005
 */
public interface ControladorTransacaoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * M�todo que registra uma operacao ao sistema
	 * 
	 * @param operacaoEfetuada
	 * @param tabelaLinhaAlteracao
	 * @param tabelaLinhaColunaAlteracoes
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuada(Collection usuariosAcaoUsuarioHelp, OperacaoEfetuada operacaoEfetuada , TabelaLinhaAlteracao tabelaLinhaAlteracao, Collection<TabelaLinhaColunaAlteracao> tabelaLinhaColunaAlteracoes) throws ControladorException;


	/**
	 * M�todo que consulta os usuario alteracao de uma determinada operacao com as restricoes passadas
	 *  
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author thiago toscano
	 * @date 17/02/2006
	 */	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
			Integer idOperacao, Integer idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)throws ControladorException ;
	/**
	 * M�todo que consulta os usuario alteracao de uma determinada operacao com as restricoes passadas
	 *  
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * @param numeroPagina
	 * 
	 * @return
	 * @throws ControladorException
	 * 
	 * @author R�mulo Aur�lio / Rafael Correa
	 * @date 26/04/2007
	 */	
	// UC12880 - Consultar Cliente.
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, Integer numeroPagina, String unidadeNegocio, boolean indicadorConsultarImovel)
		throws ControladorException;
	
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
		throws ControladorException;
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
		throws ControladorException;

	
	public void registrarTransacao(ObjetoTransacao objetoTransacao) 
		throws ControladorException;
	
	public HashMap consultarResumoInformacoesOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada, int idItemAnalisado);
	
	public void ordenarTabelaLinhaColunaAlteracao(Collection linhas, int idOperacao)	
		throws ControladorException;
	
	
	
	/**
	 * Pesquisa a quantidade de registros na tabela de Opera��o Efetuada
	 * para os argumentos passados.
	 * 		
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idOperacao
	 * @param argumentoValor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOperacaoEfetuada(Integer idOperacao,
			Integer argumentoValor,Integer id2)throws ControladorException;
	

	
	/**
	 * 
	 * Pesquisa os registros na TabelaLinhaColunaAlteracao para o argumento passado.
	 * 
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idTabelaColuna
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado,
			Integer idTabelaColuna)throws ControladorException;
	
	/**
	 * 
	 * Registrar Transacao - Inserir operacao efetuada
	 * @author anamaria
	 * @date 12/05/2009
	 *
	 * @param usuariosAcaoUsuarioHelp
	 * @param operacaoEfetuada
	 * @param tabelaAtualizacaoCadastral
	 * @param colecaoTabelaColunaAtualizacaoCadastral
	 * @throws ControladorException
	 */
	public void inserirOperacaoEfetuadaAtualizacaoCadastral(Collection usuariosAcaoUsuarioHelp,
			OperacaoEfetuada operacaoEfetuada,
			TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral,
			Collection<TabelaColunaAtualizacaoCadastral> colecaoTabelaColunaAtualizacaoCadastral)
			throws ControladorException;
	
	/**
	 * Consultar Movimento Atualiza��o Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 02/05/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(String idArquivoTxt, String idEmpresa, String idLeiturista, String exibirCampos, Collection colunaImoveisSelecionados,String matriculaImovel)
		throws ControladorException;
	
	/**
	 * @author Ivan Sergio
	 * @date 03/06/2009
	 *
	 * @param idRegistroAlterado
	 * @param idArquivo
	 * @return
	 * @throws ControladorException
	 */
	public Collection<DadosTabelaAtualizacaoCadastralHelper> consultarDadosTabelaColunaAtualizacaoCadastral(
			Integer idRegistroAlterado,
			Integer idArquivo, Integer idImovel, Integer idCliente,Integer idTipoAlteracao)
		throws ControladorException;
	
	
	/**
	 * Metodo utilizado para efetuar o registro de transacao de um objeto helper
	 * @author Anderson Italo
	 * @date 08/06/2009
	 *
	 * @param usuario
	 * @param idTipoAlteracao
	 * @param objetoHelper
	 * @param operacaoEfetuada
	 * @param idTabela
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void processaRegistroOperacaoObjetohelper(UsuarioAcaoUsuarioHelper usuario, Integer idTipoAlteracao, 
												     ObjetoTransacao objetoHelper, OperacaoEfetuada operacaoEfetuada, Integer idTabela);
	
	/**
	 * @author Ivan Sergio
	 * @date 12/06/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ControladorException
	 */
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(
			String[] idsAtualizacaoCadastral, Short indicador,Usuario usuarioLogado, String idImovelAtualizacaoCadastral) throws ControladorException;
	
	/**
	 * @author Ana Maria
	 * @date 16/06/2009
	 *
	 * @param idArgumento
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(
			Integer idArgumento, Short indicador)throws ControladorException;
	
	
	/**
	 * @author Genival Barbosa
	 * @date 27/07/2009
	 *
	 * @param idArgumento
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
		Integer idArgumento, Short indicador)throws ControladorException;

	/**
	 * Registrar transacao para um conjunto de atributos especificos
	 * 
	 * @param objetoTransacao
	 * @throws ControladorException
	 * @author Francisco do Nascimento
	 * @date 11/08/09
	 */
	public void registrarTransacao(ObjetoTransacao objetoTransacao, String[] atributos) 
		throws ControladorException;
	/**
	 * CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter cliente pendente de atualizacao do imovel. 
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idImovel
	 * @param colecaoClientes
	 * @throws ControladorException
	 */
	public void verificarAtualizarOperacaoPendente(
			Integer idImovel, Collection colecaoClientes, Integer idUsuario) throws ControladorException;
	
	/**
	 * @author Ana Maria
	 * @date 17/12/2009
	 *
	 * @param codigoImovel
	 * @param codigoCliente
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) 
		throws ControladorException;	
	

	/**
	 * @author Diogo Luiz
	 * @date 18/08/2013
	 * 
	 * @param idArquivo
	 * @throws ControladorException
	 */

	public boolean pesquisarExistenciaIdentificadorArquivo(String idArquivo)
			throws ControladorException;

	
	/**
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @throws ControladorException
	 */
	
	public boolean pesquisarExistenciaDados(String idEmpresa,
			String periodoInicial, String periodoFinal, String idLeiturista)
	throws ControladorException;

	
	/**
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @throws ControladorException
	 */

	public Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> gerarRelatorioClienteCpfCnpjValidados(String idArquivo) 
			throws ControladorException;	
	
	
	/**
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @throws ControladorException
	 */
	
	public Collection<GerarRelatorioClienteCpfCnpjValidadosHelper> gerarRelatorioClienteCpfCnpjValidados(String idEmpresa, 
			String periodoInicial, String periodoFinal, String idLeiturista) 
			throws ControladorException;

	public void processaRegistroOperacaoDadosCadastrais(UsuarioAcaoUsuarioHelper usuario,
			DadosCadastraisTransacaoBatchHelper helper, OperacaoEfetuada operacaoEfetuada) throws ControladorException;
}
