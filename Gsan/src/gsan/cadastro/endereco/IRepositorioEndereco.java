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
package gsan.cadastro.endereco;

import gsan.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Interface para o reposit�rio de cliente
 * 
 * @author S�vio Luiz
 * @created 22 de Abril de 2005
 */

public interface IRepositorioEndereco {

	/**
	 * pesquisa uma cole��o de cep(s) de acordo com o c�digo
	 * 
	 * @param codigoCep
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCep() throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void inserirImportacaoCep(Collection cepsImportados)
			throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void atualizarImportacaoCep(Collection cepsImportados)
			throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarEndereco(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarEnderecoFormatado(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroCep j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idCep,
	 *            idLogradouro
	 * @return LogradouroCep
	 */
	public LogradouroCep pesquisarAssociacaoLogradouroCep(Integer idCep,
			Integer idLogradouro) throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroCep apenas por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idLogradouro
	 * @return LogradouroCep
	 */
	public Collection<LogradouroCep> pesquisarAssociacaoLogradouroCepPorLogradouro(
			Integer idLogradouro) throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Atualiza a situa��o de uma associa��o de LogradouroCep (Motivo: CEP
	 * Inicial de Munic�pio)
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idCep,
	 *            idLogradouro, indicadorUso
	 * @return void
	 */
	public void atualizarIndicadorUsoLogradouroCep(Integer idCep,
			Integer idLogradouro, Short indicadorUso)
			throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroBairro j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 24/05/2006
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return LogradouroBairro
	 */
	public LogradouroBairro pesquisarAssociacaoLogradouroBairro(
			Integer idBairro, Integer idLogradouro)
			throws ErroRepositorioException;

	public Collection<Logradouro> pesquisarLogradouro(
			FiltroLogradouro filtroLogradouro, Integer numeroPaginas)
			throws ErroRepositorioException;

	public Integer pesquisarLogradouroCount(FiltroLogradouro filtroLogradouro)
			throws ErroRepositorioException;

	public Collection pesquisarEnderecoClienteAbreviado(Integer idCliente)
			throws ErroRepositorioException;

	// metodo que serve para fazer a pesquisa do logradouro
	// apartir dos parametros informados
	public Collection pesquisarLogradouroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ErroRepositorioException;

	
	
	public Integer pesquisarLogradouroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ErroRepositorioException;
	
	
	public Collection pesquisarLogradouroBairroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ErroRepositorioException;

	
	
	public Integer pesquisarLogradouroBairroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ErroRepositorioException;
	
	public Collection pesquisarLogradouroCompletoRelatorio(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo,
			String cep, String codigoLogradouro, String indicadorUso,
			String tipoPesquisa, String tipoPesquisaPopular)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0085] Obter Endere�o
	 * 
	 * 
	 * @author Ana Maria
	 * @data 07/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * 
	 * @return Collection
	 */
	public Collection pesquisarEnderecoRegistroAtendimentoFormatado(Integer idRegistroAtendimento)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC0085] Obter Endere�o
	 * 
	 * 
	 * @author Ana Maria
	 * @data 07/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * 
	 * @return Collection
	 */
	public Collection pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException ;

	/**
	 * Obter dados do Logradouro cep para o endere�o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @data 05/09/2006
	 * 
	 * @param idLogradouroCep
	 * 
	 * @return Collection
	 */
	public Collection obterDadosLogradouroCepEndereco(Integer idLogradouroCep)
			throws ErroRepositorioException;

	/**
	 * Obter dados do Logradouro bairro para o endere�o
	 * 
	 * 
	 * @author S�vio Luiz
	 * @data 05/09/2006
	 * 
	 * @param idLogradouroCep
	 * 
	 * @return Collection
	 */
	public Collection obterDadosLogradouroBairroEndereco(
			Integer idLogradouroBairro) throws ErroRepositorioException;
	/**
	 * 
	 * Pesquisar os Endere�os do Cliente
	 *
	 * [UC0474] Consultar Im�vel
	 *
	 * @author Rafael Santos
	 * @date 19/09/2006
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClientesEnderecosAbreviado(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar o endere�o abreviado a partir do id do im�vel
	 * 
	 * [UC0483] - Obter Endere�o Abreviado
	 * 
	 * @author Rafael Corr�a
	 * @date 18/10/2006
	 * 
	 * @param idImovel
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterEnderecoAbreviadoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar o endere�o abreviado a partir do id do ra
	 * 
	 * [UC0483] - Obter Endere�o Abreviado
	 * 
	 * @author Rafael Corr�a
	 * @date 18/10/2006
	 * 
	 * @param idRA
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterEnderecoAbreviadoRA(Integer idRA)
			throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC0348] Emitir Contas por cliente responsavel CAERN
	 * 
	 * Pesquisar endereco formatado para cliente
	 * 
	 * @author Raphael Rossiter
	 * @data 22/05/2007
	 * 
	 * @param idCliente,
	 * @return Collection
	 */
	public Collection pesquisarEnderecoFormatadoCliente(Integer idCliente)
	throws ErroRepositorioException ;
	
	public Collection pesquisarEnderecoAbreviadoCAER(Integer idImovel)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar o cep pelo codigo do cep
	 * 
	 * @author S�vio Luiz
	 * @date 05/11/2007
	 * 
	 */

	public Cep pesquisarCep(Integer codigoCep) throws ErroRepositorioException;
	
	/**
	 * Verifica a exist�ncia do endere�o de correspond�ncia do cliente pelo seu id 
	 * 
	 */
	public boolean verificarExistenciaClienteEndereco(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Retorna a cole��o de Logradouro Tipos presentes na tabela CEP 
	 * 
	 * @author: Vinicius Medeiros 
	 */
	public Collection retornaTipoLogradouroPeloCep()
			throws ErroRepositorioException;
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(
			Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Obter Logradouro(Tipo + Nome Logradouro + T�tulo)
	 */	
	public Collection pesquisarLogradouro(Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * Obter Logradouro(Tipo + Nome Logradouro + T�tulo)
	 */
	public Collection pesquisarLogradouroCliente(Integer idCliente)
	throws ErroRepositorioException;
	
	/**
	 * [UC0085] Obter Endere�o
	 * 
	 * @author Vivianne Sousa
	 * @data 17/05/2011
	 * 
	 * @param idraReiteracao
	 * 
	 * @return Collection
	 */
	public Collection pesquisarEnderecoSolicitanteRAReiteracaoFormatado(
			Integer idraReiteracao)throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 20/05/2011
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(
			Integer idCliente) throws ErroRepositorioException;

	public Object[] obterEnderecoAbreviadoCliente(Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * Recuperar Nome do Bairro do Cliente
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 10/07/2014
	 */
	public String pesquisarBairroCliente(Integer idCliente)
			throws ErroRepositorioException;
}
