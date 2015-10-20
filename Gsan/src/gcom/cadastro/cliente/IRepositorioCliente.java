/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.cadastro.cliente;

import gcom.atualizacaocadastral.ClienteFoneAtualizacaoCadastralDM;
import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.seguranca.ConsultarReceitaFederal;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Interface para o repositório de cliente
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */
public interface IRepositorioCliente {

	/**
	 * pesquisa uma coleção de responsavel(is) de acordo com critérios
	 * existentes no filtro
	 * 
	 * @param filtroCliente
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarResponsavelSuperior(FiltroCliente filtroCliente)
			throws ErroRepositorioException;

	/**
	 * Remove todos os endereços de um determinado cliente
	 * 
	 * @param idCliente
	 *            Código do cliente que terá seus endereços apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosEnderecosPorCliente(Integer idCliente)
			throws ErroRepositorioException;

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(
			FiltroCliente filtroCliente, Integer numeroPagina) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(
			FiltroCliente filtroCliente) throws ErroRepositorioException;

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * Retrona a quantidade de endereços que existem para o Cliente
	 *
	 * pesquisarClienteDadosClienteEnderecoCount
	 *
	 * @author Roberta Costa
	 * @date 29/06/2006
	 * 
	 * @param filtroCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarClienteDadosClienteEnderecoCount(
			FiltroCliente filtroCliente) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteOutrosCriterios(
			FiltroCliente filtroCliente) throws ErroRepositorioException;

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores Author: Sávio Luiz Data:
	 * 01/02/2006
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param anoMesReferencia
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */

	public Integer verificarExistenciaCliente(Integer idCliente)
			throws ErroRepositorioException;

	/**
	 * Pesquisa um cliente carregando os dados do relacionamento com ClienteTipo
	 *
	 * [UC0321] Emitir Fatura de Cliente Responsável
	 *
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarCliente(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Pesquisa um cliente pelo id
	 * 
	 * @author Rafael Corrêa
	 * @date 01/08/2006
	 * 
	 * @return Cliente
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoClienteRelatorio(
			Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Pesquisa as quantidades de imóveis e as quantidades de economias
	 * associadas a um cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 23/08/2007
	 * 
	 * @return Object[]
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente)
			throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa todos os telefones de um cliente 
	 *  
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteFone(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados do Cliente 
	 *  
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarCliente(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * Pesquisa todos os endereços do cliente 
	 *  
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecosCliente(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * [UC0458] - Imprimir Ordem de Serviço
	 * 
	 * Pesquisa o telefone principal do Cliente para o
	 * Relatório de OS
	 * 
	 * @author Rafael Corrêa
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	
	public Object[] pesquisarClienteFonePrincipal(
			Integer idCliente) throws ErroRepositorioException;

	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra o Cliente usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder,
		    Integer numeroPagina) throws ErroRepositorioException; 
	

	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra a quantidade de Clientes usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object filtrarQuantidadeCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder
			) throws ErroRepositorioException; 
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Pesquisa os dados do cliente para a emissão do boletim
	 * 
	 * @author Rafael Corrêa
	 * @date 16/05/2007
	 * 
	 * @param idImovel, clienteRelacaoTipo
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarClienteEmitirBoletimCadastro(
			Integer idImovel, Short clienteRelacaoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2008
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 * @author Vivianne Sousa
	 * @date 27/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterIdENomeCliente(
			String cpf) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 * @author Vivianne Sousa
	 * @date 30/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCPFCliente(String cpf,Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosCliente(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ErroRepositorioException;
	
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 01/09/2008
	 * 
	 * @return Cliente Fone
	 * @throws ErroRepositorioException
	 */

	public Collection obterDadosClienteFone(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 26/09/2008
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * Pesquisa a quantidade de clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ErroRepositorioException;
	
	/**
	 * Pesquisa os clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ErroRepositorioException;
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @param idCliente, idImovel
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Ana Maria
     * @date 15/05/2009
	 * @exception ErroRepositorioException
	 */
	public ClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar Cliente Fone Atualização Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, 
			Integer idTipoFone, Integer idClienteRelacaoTipo,String numeroFone)
		throws ErroRepositorioException;
	
	
	/**
	 * 
	 * Atualiza telefone padrão
	 *
	 * @author Daniel Alves
	 * @date 06/09/2010
	 *
	 * @param idCliente
	 * @param idClienteFonePadrao  (novo telefone padrão do cliente).
	 * @throws ErroRepositorioException 
	 */
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao)
		throws ErroRepositorioException;
	
	/**
	 * Remove todos os telefones de um determinado cliente
	 * 
	 * @param idCliente
	 *            Código do cliente que terá seus telefones apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosTelefonesPorCliente(Integer idCliente)
			throws ErroRepositorioException;
	

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0002]–Verifica Critério Recadastramento
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * 
	 * Filtra os Clientes por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteCliente(String valor)throws ErroRepositorioException;
	
	/**
	 * 
	 * Filtra os Clientes Responsavel por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ErroRepositorioException 
	 */
	public Collection filtrarAutocompleteClienteResponsavel(String valor, ClienteTipo tipo)throws ErroRepositorioException;
	
	/**
	 * Verifica Clientes Associados a um Cliente sem CNPJ ou ICPESSOAFISICAJURIDICA diferente de 2
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Retorna Lista de Imóveis associados ao cliente
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )throws ErroRepositorioException;
	

	
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar os clientes a partir do imóvel e o tipo de relação com o cliente
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos Através da Loja Virtual
	 * 
	 * Caso o CPF do cliente passado no parâmetro seja do cliente proprietário
	 * ou do cliente usuário do imóvel o método retorna o nome do cliente, caso
	 * contrário o método retorna null.
	 * 
	 * @author Diogo Peixoto
	 * @date 28/06/2011
	 * 
	 * @param CPFCliente
	 * 
	 * @throws ErroRepositorioException
	 * @return String
	 */
	public String validarCliente(String cpfCliente, Integer matricula) throws ErroRepositorioException;

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public Cliente pesquisarDadosCliente(Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * 4.1.1. Pesquisa um cliente com o novo CPF/CNPJ informado 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public Cliente pesquisarClienteArquivoTextoRetornoClienteVisitaCampo(
			String numeroCpfOuCnpj, Boolean numeroCpf, Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * [FS0006] - Verificar existência de negativação para o cliente-imóvel 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public Integer pesquisarExistenciaNegativacaoParaClienteImovel(
			Integer idCliente, Integer idImovel) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * [SB0001] - Atualizar CPF/CNPJ do Cliente Anterior
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizaClienteAnterior(Integer idCliente) 
			throws ErroRepositorioException; 
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public Cliente pesquisarDadosClientePessoaFisica(String cpf)throws ErroRepositorioException;
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public Cliente pesquisarDadosClientePessoaJuridica(String cnpj)throws ErroRepositorioException;
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ClienteFone pesquisarDadosClienteFonePadrao(Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ClienteEndereco pesquisarDadosClienteEnderecoCorrespondecia(Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ConsultarReceitaFederal pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(String cpf) 
		throws ErroRepositorioException;
		
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ConsultarReceitaFederal pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(String cnpj) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * [SB0007] - Verificar CPF/CNPJ válido 
	 * 
	 * @author Mariana Victor
	 * @data 26/10/2011
	 */
	public boolean verificaClienteNegativadoNaoRejeitado(
			String cpf, String cnpj) 
		throws ErroRepositorioException;


	/**
	 * 
	 * UC[0378] - Manter  Cliente
	 *
	 * @author Fernanda Almeida
	 * @date 15/12/2011
	 *
	 * @param idImovel
	 * @throws ErroRepositorioException 
	 */
	public Collection<?> obterImovelCorporativo(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * UC[0378] - Associar Tarifa de Consumo a Imóveis
	 *
	 * @author Fernanda Almeida
	 * @date 15/12/2011
	 *
	 * @param idImovel
	 * @throws ErroRepositorioException 
	 */
	public Collection<Object[]> obterClientePorImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	
	/**
	 * Metodo responsavel por pesquisar todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteVirtual> pesquisarClienteVirtual(Date periodoInicial , Date periodoFinal , int numeroPagina) throws ErroRepositorioException;
	
	/**
	 * Metodo responsavel por pesquisar quantidade todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeClienteVirtual(Date periodoInicial , Date periodoFinal ) throws ErroRepositorioException;

	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0005] - Gerar Negativação para o Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @date 07/12/2011
	 */
	public Cliente pesquisarDadosClienteParaNegativacao(Integer idCliente, String cnpjEmpresa)
		throws ErroRepositorioException;
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 15/12/2011
	 */
	public Integer pesquisarEnderecoClienteParaNegativacao(Integer idCliente)throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0352] - Emitir Contas e Cartas
	 *
	 * @author Rômulo Aurélio
	 * @date 28/05/2012
	 *
	 * @param idConta
	 * @throws ErroRepositorioException 
	 */
	public Integer obterClienteEntregaContaPorIdConta(Integer idConta)throws ErroRepositorioException;

	
	/**
	 * Método para pesquisa de árvore de clientes vinculados aos superiores - Postgrees
	 * @author Amelia Pessoa
	 * @param idClientePrincipal
	 * @return Coleção de Integer contendo ids dos clientes 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarArvoreClientesResponsaveis(Integer idClientePrincipal) throws ErroRepositorioException;
	
	/**
	 * Método para pesquisar o indicador de validar CPF/CNPJ
	 * 
	 * @author Davi Menezes
	 * @date 20/08/2013
	 * 
	 * @param idCliente
	 * @return Indicador de Validar CPF/CNPJ
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIndicadorValidarCpfCnpjCliente(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * UC1576-AssociarContasNovoClienteOuRemoverClienteConta
	 *  
	 *  @author Arthur Carvalho
	 *  @date 28/11/2013
	 *  
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterNomeClienteUsuarioConta(Integer idConta)throws ErroRepositorioException;
	
	
	/**
	 * UC1576-AssociarContasNovoClienteOuRemoverClienteConta
	 * [SB0006] - Associar Conta ao Cliente Responsável Informado
	 * 
	 * @author Arthur Carvalho
	 * @date 28/11/2013
	 * 
	 * @param idCliente
	 * @param idRelacaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteConta pesquisarClienteConta(Integer idConta, Integer idRelacaoTipo) throws ErroRepositorioException;
	
	
	/**
	 * UC1576-AssociarContasNovoClienteOuRemoverClienteConta
	 * [SB0005] - Associar Conta ao Cliente Responsável do Imóvel
	 * 
	 * @author Arthur Carvalho
	 * @date 28/11/2013
	 * 
	 * @param idCliente
	 * @param idRelacaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer obterIdClienteResponsavelImovel(Integer idContaSelecionada) throws ErroRepositorioException;

	
	public String pesquisarNomeClienteExcluido(Integer matricula)  throws ErroRepositorioException;
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public List<ClienteFoneAtualizacaoCadastralDM> obterDadosClienteFoneAtualizacaoCadastralDM
		(Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * 4.1.1. Pesquisa um cliente com o novo CPF/CNPJ informado 
	 * 
	 * @author Mariana Victor
	 * @data 27/09/2011
	 */
	public Cliente pesquisarClienteDispositivoMovel(
			String numeroCpfOuCnpj, Boolean numeroCpf, Integer idCliente) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosClienteFoneAtualizacaoCadastral
		(Integer idClienteAtualizacaoCadastral) throws ErroRepositorioException;

	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Movel
	 * 
	 * @author Bruno Sá Barreto
	 * @date 10/10/2014
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCpfCnpjCliente(String cpf, String cnpj, Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa CPF ou CNPJ do cliente 
	 * 
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0001] - Validar Atributo Cpf Cnpj
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2012
	 * 
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarCPFCNPJCliente(Integer idCliente)
			throws ErroRepositorioException;
}