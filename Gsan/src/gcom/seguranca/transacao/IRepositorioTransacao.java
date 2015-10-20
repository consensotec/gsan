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

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public interface IRepositorioTransacao {


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
	 * @param id
	 * @return
	 * @throws ControladorException
	 * 
	 * @author thiago toscano
	 * @date 17/02/2006
	 */	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
			Integer idOperacao, Integer idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String, String> argumentos, 
			Integer id1, String unidadeNegocio) 
		throws ErroRepositorioException;
	
	// UC12880 - Consultar Cliente.
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, Integer numeroPagina, String unidadeNegocio, boolean indicadorConsultarImovel)
		throws ErroRepositorioException;
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao,
			 String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
		throws ErroRepositorioException;
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao,
			 String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio) 
		throws ErroRepositorioException;

	public Collection pesquisarOperacaoOrdemExibicao(int[] idTabelaColuna, int idOperacao) 
		throws ErroRepositorioException;
	
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
			Integer argumentoValor, Integer id2)throws ErroRepositorioException;

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
			Integer idTabelaColuna)throws ErroRepositorioException;
	
	/**
	 * Consultar Movimento Atualiza��o Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 02/05/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(String idArquivoTxt, 
			String idEmpresa, String idLeiturista, String exibirCampos, Collection colunaImoveisSelecionados,String matriculaImovel)throws ErroRepositorioException;
	
	/**
	 * @author Ivan Sergio
	 * @date 03/06/2009
	 *
	 * @param idRegistroAlterado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarDadosTabelaColunaAtualizacaoCadastral(
			Integer idRegistroAlterado,
			Integer idArquivo, Integer idImovel, Integer idCliente,Integer idTipoAlteracao) throws ErroRepositorioException;
	
	/**
	 * @author Ivan Sergio
	 * @date 12/06/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException;
	
	/**
	 * @author Ana Maria
	 * @date 16/06/2009
	 *
	 * @param idArgumento
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(
			Integer idArgumento, Short indicador) throws ErroRepositorioException;
	
	/**
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param idArquivo
	 * @param idLeiturista
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(
			String idEmpresa, String idArquivo, String idLeiturista) throws ErroRepositorioException;
	
	/**
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idTabelaAtualizacaoCadastral
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral(
			Integer idTabelaAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * @author Genival Barbosa
	 * @date 27/07/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException;
	
	/**
	 * CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter cliente pendente de atualizacao do imovel.
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idObjeto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOperacaoPendente(Integer idObjeto, Integer idUsuario) throws ErroRepositorioException;
	
	/**
	 * CRC2103 - [FS0026] - Realiza a alteracao em OperacaoEfetuada em cliente
	 * pendente de atualizacao do imovel.
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idOperacaoEfetuada
	 * @param idGrupoAtributo
	 * @throws ErroRepositorioException
	 */
	public void atualizarOperacaoEfetuadaPendente(Integer idOperacaoEfetuada, Integer idGrupoAtributo) throws ErroRepositorioException;
	
	/**
	 * CRC2103 - [FS0026] - Realiza a alteracao em TabelaLinhaAlteracao em cliente
	 * pendente de atualizacao do imovel. 
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idOperacaoEfetuada
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarTabelaLinhaAlteracaoPendente(Integer idOperacaoEfetuada, Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * @author Ana Maria
	 * @date 17/12/2009
	 *
	 * @param codigoImovel
	 * @param codigoCliente
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) 
		throws ErroRepositorioException;
	
	/**
	 * @author S�vio Luiz
	 * @date 19/04/2011
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral) throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClientePeloCPF(String cpf,Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClientePeloCNPJ(String cnpj,Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterValorAtualTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelSubcategoria recuperaImovelSubcategoriaAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * Verifica se existe na base algum rela��o de cliente im�vel que seja com outro im�vel
	 * 
	 * @author S�vio Luiz
	 * @date 06/05/2011
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovelDiferenteImovel(Integer idImovel,Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 11/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteRelacaoTipo recuperaTipoRelacaoClienteAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 16/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteFone recuperaClienteFoneAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterValorAnteriorTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException;
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public TabelaAtualizacaoCadastral obterIdTabelaAtualizacaoCadastralPorCliente(Integer idCliente, Integer idTabelaColuna)
			throws ErroRepositorioException;

	
	
	/**
	 * @author Diogo Luiz
	 * 
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarExistenciaIdentificadorArquivo(Integer idArquivo) throws ErroRepositorioException;

	
	
	/**
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarExistenciaDados(String idEmpresa,
			String periodoInicial, String periodoFinal, String idLeiturista)
	throws ErroRepositorioException;

	
	/**
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection gerarRelatorioClienteCpfCnpjValidados(Integer idIdentArquivo)
			throws ErroRepositorioException;
	
	

	/**
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection gerarRelatorioClienteCpfCnpjValidados(String idEmpresa, String periodoInicial, 
			String periodoFinal, String idLeiturista)
			throws ErroRepositorioException;
	
	

	
}
