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
package gsan.cadastro.cliente;

import gsan.arrecadacao.ControladorArrecadacaoLocal;
import gsan.arrecadacao.ControladorArrecadacaoLocalHome;
import gsan.atualizacaocadastral.AtributoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ClienteFoneAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.bean.AtualizarClienteAPartirDispositivoMovelHelper;
import gsan.atualizacaocadastral.bean.AtualizarClienteFoneAPartirDispositivoMovelHelper;
import gsan.cadastro.ControladorCadastroLocal;
import gsan.cadastro.ControladorCadastroLocalHome;
import gsan.cadastro.DadosCadastraisTransacaoBatchHelper;
import gsan.cadastro.ParametrosTransacaoBatchHelper;
import gsan.cadastro.TipoAlteracaoTransacaoBatchHelper;
import gsan.cadastro.cliente.bean.ClienteEmitirBoletimCadastroHelper;
import gsan.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gsan.cadastro.endereco.Cep;
import gsan.cadastro.endereco.ControladorEnderecoLocal;
import gsan.cadastro.endereco.ControladorEnderecoLocalHome;
import gsan.cadastro.endereco.EnderecoReferencia;
import gsan.cadastro.endereco.EnderecoTipo;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.endereco.LogradouroTipo;
import gsan.cadastro.endereco.LogradouroTitulo;
import gsan.cadastro.geografico.Bairro;
import gsan.cadastro.geografico.FiltroUnidadeFederacao;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.geografico.UnidadeFederacao;
import gsan.cadastro.imovel.ControladorImovelLocal;
import gsan.cadastro.imovel.ControladorImovelLocalHome;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gsan.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gsan.cobranca.NegativadorMovimentoReg;
import gsan.interceptor.RegistradorOperacao;
import gsan.seguranca.AtributoGrupo;
import gsan.seguranca.ConsultarReceitaFederal;
import gsan.seguranca.ControladorPermissaoEspecialLocal;
import gsan.seguranca.ControladorPermissaoEspecialLocalHome;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.seguranca.transacao.AlteracaoTipo;
import gsan.seguranca.transacao.ControladorTransacaoLocal;
import gsan.seguranca.transacao.ControladorTransacaoLocalHome;
import gsan.seguranca.transacao.Tabela;
import gsan.seguranca.transacao.TabelaColuna;
import gsan.spcserasa.FiltroNegativadorMovimentoReg;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ControladorUtilLocal;
import gsan.util.ControladorUtilLocalHome;
import gsan.util.ErroRepositorioException;
import gsan.util.ServiceLocator;
import gsan.util.ServiceLocatorException;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Definição da lógica de negócio do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorClienteSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;

	private IRepositorioCliente repositorioCliente = null;

	private IRepositorioClienteImovel repositorioClienteImovel = null;

	private IRepositorioClienteEndereco repositorioClienteEndereco = null;

	SessionContext sessionContext;

	/**
	 * < <Descrição do método>>
	 * 
	 * @exception CreateException
	 *                Descrição da exceção
	 */
	public void ejbCreate() throws CreateException {

		repositorioCliente = RepositorioClienteHBM.getInstancia();
		repositorioClienteImovel = RepositorioClienteImovelHBM.getInstancia();
		repositorioClienteEndereco = RepositorioClienteEnderecoHBM
				.getInstancia();

	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descrição do método>>
	 */
	public void ejbPassivate() {
	}

	/**
	 * Seta o valor de sessionContext
	 * 
	 * @param sessionContext
	 *            O novo valor de sessionContext
	 */
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	/**
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
<<<<<<< .working
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorPermissaoEspecialLocal getControladorPermissaoEspecial() {

		ControladorPermissaoEspecialLocalHome localHome = null;
		ControladorPermissaoEspecialLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorPermissaoEspecialLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_PERMISSAO_ESPECIAL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
=======
	 * Retorna o valor de controladorTarifaSocial
	 * 
	 * @return O valor de controladorTarifaSocial
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {
		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	
	/**
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	protected ControladorImovelLocal getControladorImovel() {

		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	
	/**
>>>>>>> .merge-right.r10255
	 * Retorna o valor de controladorEndereco
	 * 
	 * @return O valor de controladorEndereco
	 */
	private ControladorEnderecoLocal getControladorEndereco() {

		ControladorEnderecoLocalHome localHome = null;
		ControladorEnderecoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorEnderecoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_ENDERECO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Insere um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser inserido
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Integer inserirCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario, Integer idOperacao) throws ControladorException {
		FiltroCliente filtroCliente = new FiltroCliente();

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				idOperacao, cliente.getId(), cliente.getId(), 
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------


		// Validar CPF de cliente
		if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cliente.getCpf()));

			Collection clienteComCpfExistente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			if (!clienteComCpfExistente.isEmpty()) {
				Cliente clienteComCpf = (Cliente) clienteComCpfExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.cpf.cliente.ja_cadastrado", null, ""
								+ clienteComCpf.getId());

			}
		}

		// Validar CNPJ de cliente
		if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
			filtroCliente.limparListaParametros();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cliente.getCnpj()));

			Collection clienteComCnpjExistente = getControladorUtil()
					.pesquisar(filtroCliente, Cliente.class.getName());

			if (!clienteComCnpjExistente.isEmpty()) {
				Cliente clienteComCnpj = (Cliente) clienteComCnpjExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.cnpj.cliente.ja_cadastrado", null, ""
								+ clienteComCnpj.getId());
			}
		}

		// Validar RG de cliente
		if (cliente.getRg() != null && !cliente.getRg().equals("")) {
			filtroCliente.limparListaParametros();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.RG, cliente.getRg()));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ORGAO_EXPEDIDOR_RG, cliente
							.getOrgaoExpedidorRg()));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.UNIDADE_FEDERACAO, cliente
							.getUnidadeFederacao()));

			Collection clienteComRgExistente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			if (!clienteComRgExistente.isEmpty()) {
				Cliente clienteComRg = (Cliente) clienteComRgExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.rg.cliente.ja_cadastrado", null, ""
								+ clienteComRg.getId());
			}
		}

		// -------------Parte que insere um cliente na
		// base----------------------
		cliente.setIndicadorGeraArquivoTexto(new Short("2"));
		
		//*************************************************************************
		// Autor: Ivan Sergio
		// Data: 06/08/2009
		// CRC2103
		// Caso em que o Inserir Cliente eh chamdo como PopUp pelo Manter Imovel 
		//*************************************************************************
		AtributoGrupo atributoGrupo = null;
		if (cliente.getOperacaoEfetuada() != null) {
			if (cliente.getOperacaoEfetuada().getAtributoGrupo() != null) {
				atributoGrupo = cliente.getOperacaoEfetuada().getAtributoGrupo();
			}
		}

		registradorOperacao.registrarOperacao(cliente);
		
		if (atributoGrupo != null) {
			cliente.getOperacaoEfetuada().setAtributoGrupo(atributoGrupo);
		}
		//*************************************************************************
		
//		cliente.setClienteFones(new HashSet(telefones));
//		cliente.setClienteEnderecos(new HashSet(enderecos));	
		cliente.setIndicadorAcaoCobranca(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		Integer chaveClienteGerada = (Integer) getControladorUtil().inserir(
				cliente);

		cliente.setId(chaveClienteGerada);

		if (telefones != null) {

			// Inserir os fones do cliente
			Iterator iteratorTelefones = telefones.iterator();

			while (iteratorTelefones.hasNext()) {
				ClienteFone clienteFone = (ClienteFone) iteratorTelefones
						.next();

				clienteFone.setCliente(cliente);
				
				/*
				 * Erivan Sousa - 25/03/2013 Seta o indicador de telefone padrão
				 * para 2 caso esse indicador esteja nulo.
				 */
				if (clienteFone.getIndicadorTelefonePadrao() == null) {
					clienteFone.setIndicadorTelefonePadrao(new Short("2"));
				}
				
				registradorOperacao.registrarOperacao(clienteFone);
				getControladorUtil().inserir(clienteFone);

			}
		}

		// Inserir os endereços do cliente
		Iterator iteratorEnderecos = enderecos.iterator();

		while (iteratorEnderecos.hasNext()) {
			ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
					.next();

			clienteEndereco.setCliente(cliente);
			registradorOperacao.registrarOperacao(clienteEndereco);
			getControladorUtil().inserir(clienteEndereco);

		}

		return chaveClienteGerada;
		/*
		 * } catch (ErroRepositorioException ex) {
		 * sessionContext.setRollbackOnly(); throw new
		 * ControladorException("erro.sistema", ex); }
		 */
		// -------------Fim da parte que insere um cliente na
		// base---------------
	}

	/**
	 * Atualiza um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser atualizado
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @throws ControladorException
	 */
	public void atualizarCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario) throws ControladorException {

		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CLIENTE_ATUALIZAR, cliente.getId(), cliente.getId(), 
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		
		FiltroCliente filtroCliente = new FiltroCliente();

		try {
			// -------------Parte que atualiza um cliente na
			// base----------------------
			// Verifica se já existe o cnpj cadastrado na base
			if (cliente.getCnpj() != null) {
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CNPJ, cliente.getCnpj()));
				filtroCliente.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCliente.ID, cliente.getId()));
				
				Collection colecaoClientes = getControladorUtil().pesquisar(filtroCliente, Cliente.class.getName());
				
				if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
					Cliente clienteComCnpj = (Cliente) Util
							.retonarObjetoDeColecao(colecaoClientes);
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.cnpj.cliente.ja_cadastrado", null, ""
									+ clienteComCnpj.getId());
				}
			}
			
			
			// Parte de Validacao com Timestamp
			filtroCliente.limparListaParametros();

			// Seta o filtro para buscar o cliente na base
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, cliente.getId()));

			Collection colecaoCliente = getControladorUtil()
			.pesquisar(filtroCliente, Cliente.class.getName());

			//verifica se o cliente ainda existe na base, porque ele pode ter sido excluido com isso
			//não é possível analizar a data de ultima alteração
			if(colecaoCliente == null || colecaoCliente.isEmpty()){
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}
			
			// Procura o cliente na base
			Cliente clienteNaBase = (Cliente) ((List)colecaoCliente).get(0);

			// Verificar se o cliente já foi atualizado por outro usuário
			// durante
			// esta atualização
			if (clienteNaBase.getUltimaAlteracao().after(
					cliente.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			//Altualiza o indicadorGeraArquivotexto
			cliente.setIndicadorGeraArquivoTexto(clienteNaBase.getIndicadorGeraArquivoTexto());
			// Atualiza a data de última alteração
			cliente.setUltimaAlteracao(new Date());
			
			//*************************************************************************
			// Autor: Ivan Sergio
			// Data: 06/08/2009
			// CRC2103
			// Caso em que o Inserir Cliente eh chamdo como PopUp pelo Manter Imovel 
			//*************************************************************************
			AtributoGrupo atributoGrupo = null;
			if (cliente.getOperacaoEfetuada() != null) {
				if (cliente.getOperacaoEfetuada().getAtributoGrupo() != null) {
					atributoGrupo = cliente.getOperacaoEfetuada().getAtributoGrupo();
				}
			}

			registradorOperacao.registrarOperacao(cliente);
			
			if (atributoGrupo != null) {
				cliente.getOperacaoEfetuada().setAtributoGrupo(atributoGrupo);
			}
			//*************************************************************************
			
			cliente.setClienteFones(new HashSet(telefones));
			cliente.setClienteEnderecos(new HashSet(enderecos));
			
			// Atualiza o cliente
			getControladorUtil().atualizar(cliente);

			// Atualizar os fones do cliente
			Iterator iteratorTelefones = telefones.iterator();

			// Remover a lista dos telefones do cliente para adicionar a nova
			// lista depois
			repositorioCliente.removerTodosTelefonesPorCliente(cliente.getId());

			// Adicionar os telefones novos informados para o cliente
			while (iteratorTelefones.hasNext()) {
				ClienteFone clienteFone = (ClienteFone) iteratorTelefones
						.next();

				clienteFone.setUltimaAlteracao(new Date());
				clienteFone.setCliente(cliente);
				/*
				 * Erivan Sousa - 25/03/2013 - Seta o indicador de telefone padrão
				 * para 2 caso esse indicador esteja nulo.
				 */
				if (clienteFone.getIndicadorTelefonePadrao() == null) {
					clienteFone.setIndicadorTelefonePadrao(new Short("2"));
				}
				
				getControladorUtil().inserir(clienteFone);

			}
			
			List colecaoEnderecos = (List) enderecos;
			
			//ordenar lista pelo indicador de correspondencia
			Collections.sort(colecaoEnderecos,  
		        new Comparator() {  
		           public int compare(Object left, Object right) {  
		           ClienteEndereco a = (ClienteEndereco) left;  
		           ClienteEndereco b = (ClienteEndereco) right;  
	               return a.getIndicadorEnderecoCorrespondencia().compareTo(b.getIndicadorEnderecoCorrespondencia());  
	             }  
			});

			// Inserir os endereços do cliente
			Iterator iteratorEnderecos = colecaoEnderecos.iterator();

			// Remover a lista dos endereços do cliente para adicionar a nova
			// lista depois
			repositorioCliente.removerTodosEnderecosPorCliente(cliente.getId());

			// Adicionar os endereços novos informados para o cliente
			while (iteratorEnderecos.hasNext()) {
				ClienteEndereco clienteEndereco = new ClienteEndereco();
				clienteEndereco = (ClienteEndereco) iteratorEnderecos
						.next();

				clienteEndereco.setUltimaAlteracao(new Date());
				clienteEndereco.setCliente(cliente);				
				
				getControladorUtil().inserir(clienteEndereco);

			}

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		// -------------Fim da parte que atualiza um cliente na
		// base---------------
	}
	
	

	/**
	 * < <Descrição do método>>
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @throws ControladorException
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws ControladorException {

		clienteImovel.setDataInicioRelacao(new Date());

		getControladorUtil().inserir(clienteImovel);
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ControladorException {
		try {
			return repositorioClienteImovel.pesquisarClienteImovel(
					filtroClienteImovel, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

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
	 * @throws ControladorException
	 */
	public int pesquisarQuantidadeClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		try {
			return repositorioClienteImovel
					.pesquisarQuantidadeClienteImovel(filtroClienteImovel);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovelTarifaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ControladorException {
		try {
			return repositorioClienteImovel.pesquisarClienteImovel(
					filtroClienteImovel, numeroPagina);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Metodo que retorno todos os clinte do filtro passado
	 * 
	 * @param filtroCliente
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @autor thiago toscano
	 * @date 15/12/2005
	 * @throws ControladorException
	 */
	public Collection pesquisarCliente(FiltroCliente filtroCliente)
			throws ControladorException {
		Collection coll = getControladorUtil().pesquisar(filtroCliente,
				Cliente.class.getSimpleName());
		return coll;
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteEndereco
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteEndereco(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ControladorException {
		try {
			return repositorioClienteEndereco
					.pesquisarClienteEndereco(filtroClienteEndereco);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * atualiza um cliente imovel economia com a data final da relação e o
	 * motivo.
	 * 
	 * @param clientesImoveisEconomia
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarClienteImovelEconomia(
			Collection clientesImoveisEconomia) throws ControladorException {

		// try {
		// -------------Parte que atualiza um cliente imovel economia na
		// base---------------------

		Iterator clienteImovelEconomiaIterator = clientesImoveisEconomia
				.iterator();

		while (clienteImovelEconomiaIterator.hasNext()) {
			ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
					.next();

			// Parte de Validacao com Timestamp
			FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();

			// Seta o filtro para buscar o cliente imovel economia na base
			filtroClienteImovelEconomia
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovelEconomia.ID,
							clienteImovelEconomia.getId()));

			// Procura o cliente na base
			ClienteImovelEconomia clienteImovelEconomiaNaBase = (ClienteImovelEconomia) ((List) (getControladorUtil()
					.pesquisar(filtroClienteImovelEconomia,
							ClienteImovelEconomia.class.getName()))).get(0);

			// Verificar se o cliente já foi atualizado por outro usuário
			// durante
			// esta atualização
			if (clienteImovelEconomiaNaBase.getUltimaAlteracao().after(
					clienteImovelEconomia.getUltimaAlteracao())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("atencao.atualizacao.timestamp");
			}

			// Atualiza a data de última alteração
			clienteImovelEconomia.setUltimaAlteracao(new Date());

			// Atualiza o cliente
			getControladorUtil().atualizar(clienteImovelEconomia);
		}
		/*
		 * } catch (ErroRepositorioException ex) {
		 * sessionContext.setRollbackOnly(); throw new
		 * ControladorException("erro.sistema", ex); }
		 */

	}

	/**
	 * Pesquisa ClienteEndereco percorrendo o ClienteImovel
	 * 
	 * @param filtroClienteEndereco
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ControladorException {
		try {
			return repositorioClienteEndereco
					.pesquisarClienteEnderecoClienteImovel(filtroClienteEndereco);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(
			FiltroCliente filtroCliente, Integer numeroPagina)
			throws ControladorException {

		try {
			return repositorioCliente.pesquisarClienteDadosClienteEndereco(
					filtroCliente, numeroPagina);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(
			FiltroCliente filtroCliente) throws ControladorException {

		try {
			return repositorioCliente
					.pesquisarClienteDadosClienteEnderecoRelatorio(filtroCliente);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

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
	 * @throws ControladorException
	 */
	public Integer pesquisarClienteDadosClienteEnderecoCount(
			FiltroCliente filtroCliente) throws ControladorException {

		try {
			return repositorioCliente
					.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection<Cliente> pesquisarClienteOutrosCriterios(
			FiltroCliente filtroCliente) throws ControladorException {

		try {
			return repositorioCliente
					.pesquisarClienteOutrosCriterios(filtroCliente);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Inseri uma coleção de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Este fluxo secundário tem como objetivo pesquisar o cliente digitado pelo
	 * usuário
	 * 
	 * [FS0011] - Verificar existência do código do cliente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idClienteDigitado
	 * @return
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteDigitado(Integer idClienteDigitado)
			throws ControladorException {

		// Cria a variável que vai armazenar o cliente pesquisado
		Cliente clienteDigitado = null;

		// Pesquisa o cliente informado pelo usuário no sistema
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				idClienteDigitado));
		
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ESFERA_PODER);
			
		Collection<Cliente> colecaoCliente = getControladorUtil().pesquisar(
				filtroCliente, Cliente.class.getName());

		// Caso exista o cliente no sistema
		// Retorna para o usuário o cliente retornado pela pesquisa
		// Caso contrário retorna um objeto nulo
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
			clienteDigitado = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCliente);
		}

		// Retorna o cliente encontrado ou nulo se não existir
		return clienteDigitado;
	}

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
	 * @throws ControladorException
	 */
	public Cliente pesquisarCliente(Integer idCliente)
			throws ControladorException {

		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioCliente.pesquisarCliente(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioClienteImovel
					.pesquisarClienteImovel(filtroClienteImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovelRelatorio(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException {
		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioClienteImovel
					.pesquisarClienteImovelRelatorio(filtroClienteImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

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
	public Cliente pesquisarObjetoClienteRelatorio(Integer idCliente)
			throws ControladorException {

		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoCliente = repositorioCliente
					.pesquisarObjetoClienteRelatorio(idCliente);

			Cliente cliente = new Cliente();

			cliente.setId((Integer) objetoCliente[0]);
			cliente.setNome((String) objetoCliente[1]);

			return cliente;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa as quantidades de imóveis e as quantidades de economias
	 * associadas a um cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 23/08/2007
	 * 
	 * @return Object[]
	 * @exception ControladorException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente)
			throws ControladorException {

		try {
			// pesquisa as gerencias regionais existentes no sisitema
			return repositorioCliente
					.pesquisarQtdeImoveisEEconomiasCliente(idCliente);

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exceção para a próxima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * O método abaixo realiza uma pesquisa em cliente e verifica se ele existe
	 * 
	 */
	public Integer verificarExistenciaCliente(Integer codigoCliente)
			throws ControladorException {

		// Retorna o cliente encontrado ou vazio se não existir
		try {
			return repositorioCliente.verificarExistenciaCliente(codigoCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * 
	 * @param idCliente,
	 *            idImovel
	 * @return Colletion
	 * @throws ControladorException
	 */
	public ClienteImovel pesquisarClienteImovel(Integer idCliente,
			Integer idImovel) throws ControladorException {

		Collection colecaoClienteImovel = null;

		ClienteImovel retorno = null;

		try {
			colecaoClienteImovel = this.repositorioClienteImovel
					.pesquisarClienteImovel(idCliente, idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			retorno = new ClienteImovel();
			Cliente cliente = new Cliente();

			Iterator raIterator = colecaoClienteImovel.iterator();

			Object[] arrayClienteIMovel = (Object[]) raIterator.next();

			cliente.setId((Integer) arrayClienteIMovel[0]);

			cliente.setNome((String) arrayClienteIMovel[1]);

			retorno.setCliente(cliente);
		}

		return retorno;
	}

	/**
	 * Pesquisa todos os telefones de um cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteFone(Integer idCliente)
			throws ControladorException {

		//Collection colecaoFone = null;
		//Collection colecaoRetorno = null;

		try {
			return this.repositorioCliente
					.pesquisarClienteFone(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		/*if (colecaoFone != null && !colecaoFone.isEmpty()) {

			Iterator foneIterator = colecaoFone.iterator();
			ClienteFone clienteFone = null;
			FoneTipo foneTipo = null;

			colecaoRetorno = new ArrayList();

			while (foneIterator.hasNext()) {

				clienteFone = new ClienteFone();

				Object[] arrayFone = (Object[]) foneIterator.next();

				clienteFone.setId((Integer) arrayFone[0]);

				clienteFone.setUltimaAlteracao((Date) arrayFone[5]);

				if (arrayFone[1] != null) {
					clienteFone.setDdd((String) arrayFone[1]);
				}

				clienteFone.setTelefone((String) arrayFone[2]);

				if (arrayFone[3] != null) {
					clienteFone
							.setIndicadorTelefonePadrao((Short) arrayFone[3]);
				}

				foneTipo = new FoneTipo();
				foneTipo.setDescricao((String) arrayFone[4]);

				clienteFone.setFoneTipo(foneTipo);

				colecaoRetorno.add(clienteFone);
			}
		}*/

		//return colecaoRetorno;
	}

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecosClienteAbreviado(Integer idCliente)
			throws ControladorException {

		Collection colecaoEndereco = null;
		Collection colecaoRetorno = null;

		try {
			colecaoEndereco = this.repositorioClienteEndereco
					.pesquisarEnderecosClienteAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {

			Iterator enderecoIterator = colecaoEndereco.iterator();
			ClienteEndereco clienteEndereco = null;

			colecaoRetorno = new ArrayList();

			while (enderecoIterator.hasNext()) {

				clienteEndereco = new ClienteEndereco();

				// cria um array de objetos para pegar os parametros de
				// retorno da pesquisa
				Object[] arrayEndereco = (Object[]) enderecoIterator.next();

				LogradouroCep logradouroCep = null;
				if (arrayEndereco[20] != null) {

					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) arrayEndereco[20]);

					// id do Logradouro
					Logradouro logradouro = null;
					if (arrayEndereco[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) arrayEndereco[19]);
						logradouro.setNome("" + arrayEndereco[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descrição logradouro tipo
					if (arrayEndereco[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada(""
								+ arrayEndereco[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descrição logradouro titulo
					if (arrayEndereco[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada(""
								+ arrayEndereco[23]);
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep cep = null;
					if (arrayEndereco[10] != null) {
						cep = new Cep();
						cep.setCepId((Integer) arrayEndereco[10]);
						cep.setCodigo((Integer) arrayEndereco[16]);
					}

					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(cep);
				}

				clienteEndereco.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (arrayEndereco[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayEndereco[21]);

					Bairro bairro = null;
					// nome bairro
					if (arrayEndereco[3] != null) {
						bairro = new Bairro();
						bairro.setId((Integer) arrayEndereco[3]);
						bairro.setNome("" + arrayEndereco[4]);
					}

					Municipio municipio = null;
					// nome municipio
					if (arrayEndereco[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) arrayEndereco[5]);
						municipio.setNome("" + arrayEndereco[6]);

						// id da unidade federação
						if (arrayEndereco[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) arrayEndereco[7]);
							unidadeFederacao.setSigla("" + arrayEndereco[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}

						bairro.setMunicipio(municipio);
					}

					logradouroBairro.setBairro(bairro);
				}

				clienteEndereco.setLogradouroBairro(logradouroBairro);

				// descricao de endereço referência
				if (arrayEndereco[24] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[24]);
					clienteEndereco.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					clienteEndereco.setNumero("" + arrayEndereco[17]);
				}

				// complemento endereço
				if (arrayEndereco[18] != null) {
					clienteEndereco.setComplemento("" + arrayEndereco[18]);
				}

				// indicador endereço correspondencia
				if (arrayEndereco[25] != null) {
					clienteEndereco
							.setIndicadorEnderecoCorrespondencia((Short) arrayEndereco[25]);
				}

				clienteEndereco.setId((Integer) arrayEndereco[26]);

				clienteEndereco.setUltimaAlteracao((Date) arrayEndereco[27]);
				
				// Perímetro
				if (arrayEndereco[28] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[28]);
					
					if (arrayEndereco[29] != null) {
						perimetroInicial.setNome((String) arrayEndereco[29]);
					}
					
					if (arrayEndereco[30] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[30]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[31] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[31]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					clienteEndereco.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[32] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[32]);
					
					if (arrayEndereco[33] != null) {
						perimetroFinal.setNome((String) arrayEndereco[33]);
					}
					
					if (arrayEndereco[34] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[34]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[35] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[35]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					clienteEndereco.setPerimetroFinal(perimetroFinal);
				}

				colecaoRetorno.add(clienteEndereco);
			}
		}

		return colecaoRetorno;
	}

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
	public Cliente consultarCliente(Integer idCliente)
			throws ControladorException {

		Collection colecaoClientes = null;
		// Collection colecaoRetorno = null;
		Cliente cliente = null;

		try {
			colecaoClientes = this.repositorioCliente
					.consultarCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClientes != null && !colecaoClientes.isEmpty()) {

			Object[] objetoCliente = (Object[]) colecaoClientes.iterator()
					.next();

			cliente = new Cliente();

			// 0 - Nome do Cliente
			if (objetoCliente[0] != null) {
				cliente.setNome((String) objetoCliente[0]);
			}

			// 1 - Nome do Cliente Abreviado
			if (objetoCliente[1] != null) {
				cliente.setNomeAbreviado((String) objetoCliente[1]);
			}

			// 2 - Data Vencimento
			if (objetoCliente[2] != null) {
				cliente.setDataVencimento((Short) objetoCliente[2]);
			}

			// 3 - Cliente Tipo Descricao
			ClienteTipo clienteTipo = null;
			if (objetoCliente[3] != null) {
				clienteTipo = new ClienteTipo();
				clienteTipo.setDescricao((String) objetoCliente[3]);
				cliente.setClienteTipo(clienteTipo);
			}

			// 4 - Indicado Pessoa Fisica ou Juridica
			if (objetoCliente[4] != null) {
				if (clienteTipo == null) {
					clienteTipo = new ClienteTipo();
				}
				clienteTipo
						.setIndicadorPessoaFisicaJuridica((Short) objetoCliente[4]);
				cliente.setClienteTipo(clienteTipo);
			}

			// 5 - E-mail
			if (objetoCliente[5] != null) {
				cliente.setEmail((String) objetoCliente[5]);
			}

			// 6 - Indicador de Acao Cobranca
			if (objetoCliente[6] != null) {
				cliente.setIndicadorAcaoCobranca((Short) objetoCliente[6]);
			}

			// 7 - CPF
			if (objetoCliente[7] != null) {
				cliente.setCpf((String) objetoCliente[7]);
			}

			// 8 - RG
			if (objetoCliente[8] != null) {
				cliente.setRg((String) objetoCliente[8]);
			}

			// 9 - Data Emissao RG
			if (objetoCliente[9] != null) {
				cliente.setDataEmissaoRg((Date) objetoCliente[9]);
			}

			// 10 - Orgao Expedidor RG
			OrgaoExpedidorRg orgaoExpedidorRg = null;
			if (objetoCliente[10] != null) {
				orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg
						.setDescricaoAbreviada((String) objetoCliente[10]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}

			// 11 - Unidade Federacao
			UnidadeFederacao unidadeFederacao = null;
			if (objetoCliente[11] != null) {
				unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String) objetoCliente[11]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}

			// 12 - Data Nascimento
			if (objetoCliente[12] != null) {
				cliente.setDataNascimento((Date) objetoCliente[12]);
			}

			// 13 - Profissao
			Profissao profissao = null;
			if (objetoCliente[13] != null) {
				profissao = new Profissao();
				profissao.setDescricao((String) objetoCliente[13]);
				cliente.setProfissao(profissao);
			}

			// 14 - Pessoa Sexo
			PessoaSexo pessoaSexo = null;
			if (objetoCliente[14] != null) {
				pessoaSexo = new PessoaSexo();
				pessoaSexo.setDescricao((String) objetoCliente[14]);
				cliente.setPessoaSexo(pessoaSexo);
			}

			// 15 - CNPJ
			if (objetoCliente[15] != null) {
				cliente.setCnpj((String) objetoCliente[15]);
			}

			// 16 - Ramo Atividade
			RamoAtividade ramoAtividade = null;
			if (objetoCliente[16] != null) {
				ramoAtividade = new RamoAtividade();
				ramoAtividade.setDescricao((String) objetoCliente[16]);
				cliente.setRamoAtividade(ramoAtividade);
			}

			// 17 - Codigo Cliente Responsavel
			Cliente clienteResponsavel = null;
			if (objetoCliente[17] != null) {
				clienteResponsavel = new Cliente();
				clienteResponsavel.setId((Integer) objetoCliente[17]);
				cliente.setCliente(cliente);
			}

			// 18 - Nome Cliente Responsavel
			if (objetoCliente[17] != null) {
				if (clienteResponsavel == null) {
					clienteResponsavel = new Cliente();
				}
				clienteResponsavel.setNome((String) objetoCliente[18]);
				cliente.setCliente(cliente);
			}

		}

		return cliente;
	}

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
	public Collection pesquisarEnderecoCliente(Integer idCliente)
			throws ControladorException {

		Collection colecaoClienteEndereco = null;
		Collection colecaoRetorno = null;

		try {
			colecaoClienteEndereco = this.repositorioCliente
					.pesquisarEnderecosCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()) {

			Iterator clienteEnderecoIterator = colecaoClienteEndereco
					.iterator();
			ClienteEndereco clienteEndereco = null;
			colecaoRetorno = new ArrayList();

			while (clienteEnderecoIterator.hasNext()) {

				Object[] array = (Object[]) clienteEnderecoIterator.next();

				clienteEndereco = new ClienteEndereco();

				// 0- Endereco Tipo
				EnderecoTipo enderecoTipo = null;
				if (array[0] != null) {
					enderecoTipo = new EnderecoTipo();
					enderecoTipo.setDescricao((String) array[0]);
					clienteEndereco.setEnderecoTipo(enderecoTipo);
				}

				// 1- Indicador Endereco Correspondencia
				if (array[0] != null) {
					clienteEndereco
							.setIndicadorEnderecoCorrespondencia((Short) array[1]);
				}
				colecaoRetorno.add(clienteEndereco);
			}
		}

		return colecaoRetorno;

	}

	/**
	 * Pesquisa o nome do cliente a partir do imóvel Autor: Sávio Luiz Data:
	 * 21/12/2005
	 */
	public String pesquisarNomeClientePorImovel(Integer idImovel)
			throws ControladorException {

		String nomeCliente = "";

		try {
			nomeCliente = this.repositorioClienteImovel
					.pesquisarNomeClientePorImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		return nomeCliente;
	}

	/**
	 * Pesquisa o nome, cnpj e id do cliente a partir do imóvel Autor: Rafael Corrêa Data:
	 * 25/09/2006
	 */
	public Cliente pesquisarDadosClienteRelatorioParcelamentoPorImovel(Integer idImovel)
			throws ControladorException {

		Cliente cliente = null;
		Object[] dadosCliente = null;

		try {
			dadosCliente = this.repositorioClienteImovel
					.pesquisarDadosClienteRelatorioParcelamentoPorImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosCliente != null) {
			
			cliente = new Cliente();
			
			if (dadosCliente[0] != null) {
				//Id
				cliente.setId((Integer) dadosCliente[0]);
			}
			
			if (dadosCliente[1] != null) {
				// Nome
				cliente.setNome((String) dadosCliente[1]);
			} else {
				cliente.setNome("");
			}
			
			if (dadosCliente[2] != null) {
				// CPF
				cliente.setCpf((String) dadosCliente[2]);
			}
			
			if (dadosCliente[3] != null) {
				// CNPJ
				cliente.setCnpj((String) dadosCliente[3]);
			}
			
			if (dadosCliente[4] != null) {
				// orgão expedidor
				OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg.setDescricao((String)dadosCliente[4]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}
			
			if (dadosCliente[5] != null) {
				// unidade federativa
				UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String) dadosCliente[5]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}
			
			if (dadosCliente[6] != null) {
				// rg
				cliente.setRg((String)dadosCliente[6]);
			}
			
		}

		return cliente;
	}
	
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
	
	public String pesquisarClienteFonePrincipal(
			Integer idCliente) throws ControladorException {

		Object[] dadosClienteFone = null;
		String telefoneFormatado = "";

		try {

			dadosClienteFone = this.repositorioCliente
					.pesquisarClienteFonePrincipal(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosClienteFone != null) {
			
			// DDD
			if (dadosClienteFone[0] != null) {
				telefoneFormatado = telefoneFormatado + "(" + dadosClienteFone[0].toString() + ")";
			}
			
			// Número do Telefone
			if (dadosClienteFone[1] != null) {
				telefoneFormatado = telefoneFormatado + dadosClienteFone[1];
			}
			
			// Ramal
			if (dadosClienteFone[2] != null) {
				telefoneFormatado = telefoneFormatado + "/" + dadosClienteFone[2];
			}
			
		}

		return telefoneFormatado;

	}
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Pesquisa os dados do cliente para a emissão do boletim
	 * 
	 * @author Rafael Corrêa
	 * @date 16/05/2007
	 * 
	 * @param idImovel, clienteRelacaoTipo
	 * @throws ControladorException
	 */
	public ClienteEmitirBoletimCadastroHelper pesquisarClienteEmitirBoletimCadastro(
			Integer idImovel, Short clienteRelacaoTipo)
			throws ControladorException {

		Collection colecaoDadosCliente = null;
		ClienteEmitirBoletimCadastroHelper clienteEmitirBoletimCadastroHelper = null;

		try {

			colecaoDadosCliente = this.repositorioCliente
					.pesquisarClienteEmitirBoletimCadastro(idImovel,
							clienteRelacaoTipo);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoDadosCliente != null && !colecaoDadosCliente.isEmpty()) {

			Iterator colecaoDadosClienteIterator = colecaoDadosCliente
					.iterator();

			boolean primeiroRegistro = true;

			Collection clientesFone = new ArrayList();

			while (colecaoDadosClienteIterator.hasNext()) {

				Object[] dadosCliente = (Object[]) colecaoDadosClienteIterator
						.next();

				if (primeiroRegistro) {

					clienteEmitirBoletimCadastroHelper = new ClienteEmitirBoletimCadastroHelper();
					Cliente cliente = new Cliente();
					ClienteEndereco clienteEndereco = new ClienteEndereco();
					LogradouroCep logradouroCep = new LogradouroCep();
					LogradouroBairro logradouroBairro = new LogradouroBairro();

					// Dados do Cliente
					// Id do Cliente
					if (dadosCliente[0] != null) { // 0
						cliente.setId((Integer) dadosCliente[0]);
					}

					// Nome do Cliente
					if (dadosCliente[1] != null) { // 1
						cliente.setNome((String) dadosCliente[1]);
					}

					// Tipo do Cliente
					if (dadosCliente[2] != null) { // 2
						ClienteTipo clienteTipo = new ClienteTipo();
						clienteTipo.setId((Integer) dadosCliente[2]);
						cliente.setClienteTipo(clienteTipo);
					}

					// CPF do Cliente
					if (dadosCliente[3] != null) { // 3
						cliente.setCpf((String) dadosCliente[3]);
					}

					// CNPJ do Cliente
					if (dadosCliente[4] != null) { // 4
						cliente.setCnpj((String) dadosCliente[4]);
					}

					// RG do Cliente
					if (dadosCliente[5] != null) { // 5
						cliente.setRg((String) dadosCliente[5]);
					}

					// Data de Emissão RG
					if (dadosCliente[6] != null) { // 6
						cliente.setDataEmissaoRg((Date) dadosCliente[6]);
					}

					// Órgão Expedidor RG
					if (dadosCliente[7] != null) { // 7
						OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
						orgaoExpedidorRg.setDescricaoAbreviada((String) dadosCliente[7]);
						cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
					}

					// Unidade Federação
					if (dadosCliente[8] != null) { // 8
						UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
						unidadeFederacao.setSigla((String) dadosCliente[8]);
						cliente.setUnidadeFederacao(unidadeFederacao);
					}

					// Data de Nascimento
					if (dadosCliente[9] != null) { // 9
						cliente.setDataNascimento((Date) dadosCliente[9]);
					}

					// Profissão
					if (dadosCliente[10] != null) { // 10
						Profissao profissao = new Profissao();
						profissao.setDescricao((String) dadosCliente[10]);
						cliente.setProfissao(profissao);
					}

					// Sexo
					if (dadosCliente[11] != null) { // 11
						PessoaSexo pessoaSexo = new PessoaSexo();
						pessoaSexo.setId((Integer) dadosCliente[11]);
						cliente.setPessoaSexo(pessoaSexo);
					}

					// Nome da Mãe
					if (dadosCliente[12] != null) { // 12
						cliente.setNomeMae((String) dadosCliente[12]);
					}

					// Indicador de Uso
					if (dadosCliente[13] != null) { // 13
						cliente.setIndicadorUso((Short) dadosCliente[13]);
					}

					clienteEmitirBoletimCadastroHelper.setCliente(cliente);

					// Dados do Endereço do Cliente
					// Tipo de Endereço
					if (dadosCliente[14] != null) { // 14
						EnderecoTipo enderecoTipo = new EnderecoTipo();
						enderecoTipo.setId((Integer) dadosCliente[14]);
						clienteEndereco.setEnderecoTipo(enderecoTipo);
					}

					// Id do Logradouro
					if (dadosCliente[15] != null) { // 15
						Logradouro logradouro = new Logradouro();
						logradouro.setId((Integer) dadosCliente[15]);
						logradouroCep.setLogradouro(logradouro);
						logradouroBairro.setLogradouro(logradouro);
					}

					// Endereço do Cliente
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(
							FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(
							FiltroClienteEndereco.INDICADOR_CORRESPONDENCIA, ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA));
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
					filtroClienteEndereco
							.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					
					Collection colecaoEnderecos = getControladorUtil()
							.pesquisar(filtroClienteEndereco,
									ClienteEndereco.class.getName());
					
					if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
						ClienteEndereco clienteEnderecoCorrespondencia = (ClienteEndereco) Util
								.retonarObjetoDeColecao(colecaoEnderecos);

						String endereco = clienteEnderecoCorrespondencia
								.getEnderecoFormatado();

						if (endereco != null && !endereco.trim().equals("")) {
							clienteEmitirBoletimCadastroHelper
									.setEnderecoFormatado(endereco);
						}
					}
					
					// CEP
					if (dadosCliente[16] != null) { // 16
						Cep cep = new Cep();
						cep.setCodigo((Integer) dadosCliente[16]);
						logradouroCep.setCep(cep);
					}

					// Id do Bairro
					if (dadosCliente[17] != null) { // 17
						Bairro bairro = new Bairro();
						bairro.setCodigo((Integer) dadosCliente[17]);
						
						Municipio municipio = new Municipio();
						municipio.setId((Integer) dadosCliente[25]);
						
						bairro.setMunicipio(municipio);
						
						logradouroBairro.setBairro(bairro);
					}

					// Endereço de Referência
					if (dadosCliente[18] != null) { // 18
						EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
						enderecoReferencia.setId((Integer) dadosCliente[18]);
						clienteEndereco
								.setEnderecoReferencia(enderecoReferencia);
					}

					// Número do Imóvel
					if (dadosCliente[19] != null) { // 19
						clienteEndereco.setNumero((String) dadosCliente[19]);
					}

					// Complemento
					if (dadosCliente[20] != null) { // 20
						clienteEndereco
								.setComplemento((String) dadosCliente[20]);
					}

					clienteEndereco.setLogradouroCep(logradouroCep);
					clienteEndereco.setLogradouroBairro(logradouroBairro);
					clienteEmitirBoletimCadastroHelper
							.setClienteEndereco(clienteEndereco);

					primeiroRegistro = false;
					
					// Tarifa Social Dado Economia
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
					filtroClienteImovel.adicionarParametro(
							new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, cliente.getId()));
					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(
							FiltroClienteImovel.IMOVEL);
					
					Collection colecaoClienteImovel = getControladorUtil()
							.pesquisar(filtroClienteImovel,
									ClienteImovel.class.getName());
					
					if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
						ClienteImovel clienteImovel = (ClienteImovel) Util
						.retonarObjetoDeColecao(colecaoClienteImovel);
						
						FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = 
								new FiltroTarifaSocialDadoEconomia();
						filtroTarifaSocialDadoEconomia.adicionarParametro(
								new ParametroSimples(FiltroTarifaSocialDadoEconomia.IMOVEL_ID, clienteImovel.getImovel().getId()));
						filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(
								FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
						filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(
								FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
						
						Collection colecaoTarifaSocial = getControladorUtil()
								.pesquisar(filtroTarifaSocialDadoEconomia,
										TarifaSocialDadoEconomia.class.getName());

						if (colecaoTarifaSocial != null && !colecaoTarifaSocial.isEmpty()) {
							TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) Util
									.retonarObjetoDeColecao(colecaoTarifaSocial);
							clienteEmitirBoletimCadastroHelper.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
						}
						
					}
					

				}

				ClienteFone clienteFone = new ClienteFone();

				// Dados do Telefone do Cliente
				// Tipo do Telefone
				if (dadosCliente[21] != null) { // 21
					FoneTipo foneTipo = new FoneTipo();
					foneTipo.setId((Integer) dadosCliente[21]);
					clienteFone.setFoneTipo(foneTipo);
				}

				// DDD
				if (dadosCliente[22] != null) { // 22
					clienteFone.setDdd((String) dadosCliente[22]);
				}

				// Número Telefone
				if (dadosCliente[23] != null) { // 23
					clienteFone.setTelefone((String) dadosCliente[23]);
				}

				// Ramal
				if (dadosCliente[24] != null) { // 24
					clienteFone.setRamal((String) dadosCliente[24]);
				}

				clientesFone.add(clienteFone);
				
				
			}

			clienteEmitirBoletimCadastroHelper.setClienteFone(clientesFone);

		}

		return clienteEmitirBoletimCadastroHelper;

	}
	
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
		    Integer numeroPagina) throws ControladorException {
		
		
		Collection colecaoDadosCliente = null;
		Collection colecaoClientes = null;

		try {

			colecaoDadosCliente = this.repositorioCliente
					.filtrarCliente(codigo,
							cpf,
							rg,
							cnpj,
							nome,
							nomeMae,		
							cep,
							idMunicipio,
							idBairro,
							idLogradouro,
							indicadorUso,
							tipoPesquisa,
							tipoPesquisaNomeMae,
							clienteTipo, idEsferaPoder,
						    numeroPagina);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (colecaoDadosCliente != null && !colecaoDadosCliente.isEmpty()) {
			
			Iterator iteratorColecaoDadosCliente = colecaoDadosCliente.iterator();
			Cliente cliente =  null;
			colecaoClientes = new ArrayList();
			
			while(iteratorColecaoDadosCliente.hasNext()){
				
				cliente = new Cliente();
				
				Object[] array = (Object[]) iteratorColecaoDadosCliente.next();
				
				// codigo
				if (array[0] != null) {
					cliente.setId((Integer) array[0]);
				}
				
				// nome
				if (array[1] != null) {
					cliente.setNome((String) array[1]);
				}
				
				// rg
				if (array[2] != null) {
					cliente.setRg((String) array[2]);
				}

				// cpf
				if (array[3] != null) {
					cliente.setCpf((String) array[3]);
				}

				// cnpj
				if (array[4] != null) {
					cliente.setCnpj((String) array[4]);
				}
				
				ClienteTipo tipoCliente = null;
				// indicadorPessoaFisicaJuridica
				if (array[5] != null) {
					tipoCliente = new ClienteTipo();
					tipoCliente.setIndicadorPessoaFisicaJuridica((Short)array[5]);
					cliente.setClienteTipo(tipoCliente);
				}
				
				// descricao orgaoExpedidorRg
				if (array[6] != null) {
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg.setDescricao((String) array[6]);
					cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
				}
				
				// silga orgaoExpedidorRg
				if (array[7] != null) {
					UnidadeFederacao unidadeFederacao  = new UnidadeFederacao();
					
					unidadeFederacao.setSigla((String) array[7]);
					cliente.setUnidadeFederacao(unidadeFederacao);
				}
				// descricao tipo cliente
				if (array[8] != null) {
					if(tipoCliente == null){
						tipoCliente = new ClienteTipo();
					}
					tipoCliente.setDescricao((String)array[8]);
					cliente.setClienteTipo(tipoCliente);
				}
				
				// indicador uso
				if (array[9] != null) {
					cliente.setIndicadorUso((Short) array[9]);
				}
								
				
				colecaoClientes.add(cliente);
			}
			
		}

		return colecaoClientes;
	}
	
	
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
			String clienteTipo, String idEsferaPoder) throws ControladorException{ 	
		
		Object quantidade = null;
		Integer retorno = null;
		
		try {
			quantidade = repositorioCliente.filtrarQuantidadeCliente(
					 codigo,
					 cpf,
					 rg,
					 cnpj,
					 nome,
					 nomeMae,		
					 cep,
					 idMunicipio,
					 idBairro,
					 idLogradouro,
					 indicadorUso,
					 tipoPesquisa,
					 tipoPesquisaNomeMae,
					 clienteTipo, idEsferaPoder);  
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		if(quantidade != null){
			retorno = (Integer) quantidade;
			
		}

		return retorno;
	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Cliente, indicador de uso, motivo
	 * do fim da relação, pelo perfil do imóvel e pelo tipo da relação do cliente carregando o imóvel
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente)
			throws ControladorException {

		try {
			return repositorioClienteImovel.pesquisarClienteImovelPeloClienteTarifaSocial(idCliente);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel carregando o imóvel, o cliente, o perfil do imóvel, 
	 * o orgão expedidor do RG e a unidade da federação
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(Integer idImovel) 
			throws ControladorException {

		try {
			return repositorioClienteImovel.pesquisarClienteImovelPeloImovelTarifaSocial(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel carregando os dados necessários para retornar o seu endereço 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(Integer idImovel) 
			throws ControladorException {

		try {
			return repositorioClienteImovel.pesquisarClienteImovelPeloImovelParaEndereco(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}

	}
	
	/**
	 * 
	 * Verifica se é usuario iquilino ou não
	 *
	 * @author Sávio Luiz
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public boolean verificaUsuarioinquilino(Integer idImovel) throws ControladorException{
		Collection colecao = null;
		Integer idClienteUsuario = null;
		boolean naoInquilino = false;
		try {
			idClienteUsuario = repositorioClienteImovel.retornaIdClienteUsuario(idImovel);
			colecao = repositorioClienteImovel.retornaClientesRelacao(idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
		if(colecao != null && !colecao.isEmpty()){
			Iterator iteParmsCliente = colecao.iterator();
			while(iteParmsCliente.hasNext()){
				Integer idClienteBase = (Integer)iteParmsCliente.next();
				if(idClienteBase.equals(idClienteUsuario)){
					naoInquilino = true;
					break;
				}
				
			}
		}else{
			naoInquilino = true;
		}
		return naoInquilino;
	}
	
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ControladorException {
		
		try {
		
			this.repositorioClienteEndereco
					.atualizarLogradouroCep(logradouroCepAntigo, logradouroCepNovo);
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ControladorException {
		
		try {
		
			this.repositorioClienteEndereco
					.atualizarLogradouroBairro(logradouroBairroAntigo, logradouroBairroNovo);
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0544] Gerar Arwuivo Texto do Faturamento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Flávio Cordeiro
	 * @date 4/04/2006
	 * 
	 * 
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelGerarArquivoFaturamento()
		throws ControladorException {
		
		try {
		
			Collection clientes = new HashSet();
			
			Collection colecaoObjetos = this.repositorioClienteImovel
					.pesquisarClienteImovelGerarArquivoFaturamento();
			if(!colecaoObjetos.isEmpty()){
				Iterator iterator = colecaoObjetos.iterator();
				while(iterator.hasNext()){
					Object[] objeto = (Object[])iterator.next();
					
					Cliente cliente = new Cliente();
					if(objeto[0] != null){
						cliente.setId((Integer)objeto[0]);
					}
					if(objeto[1] != null){
						cliente.setNome((String)objeto[1]);
					}
					clientes.add(cliente);
				}
			}
			return clientes;
		
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0864] Gerar Certidão Negativa por Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 25/09/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente) throws ControladorException {
	
		try {

			return this.repositorioCliente.pesquisarClientesAssociadosResponsavel(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * Pesquisa o rg do cliente do parcelamento a partir do idParcelamento
	 * Autor: Vivianne Sousa 
	 * Data: 20/06/2007
	 */
	public Cliente pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento)
			throws ControladorException {

		Cliente cliente = null;
		Object[] dadosCliente = null;

		try {
			dadosCliente = this.repositorioClienteImovel
					.pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(idParcelamento);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosCliente != null) {
			
			cliente = new Cliente();
			
					
			if (dadosCliente[0] != null) {
				// orgão expedidor
				OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg.setDescricao((String)dadosCliente[0]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}
			
			if (dadosCliente[1] != null) {
				// unidade federativa
				UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String) dadosCliente[1]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}
			
			if (dadosCliente[2] != null) {
				// rg
				cliente.setRg((String)dadosCliente[2]);
			}
			
		}

		return cliente;
	}
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 * @author Vivianne Sousa
	 * @date 27/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterIdENomeCliente(String cpf) throws ControladorException{
		Object[] dadosCliente = null;
		Cliente cliente = null;

		try {

			dadosCliente = this.repositorioCliente.obterIdENomeCliente(cpf);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
		if (dadosCliente != null) {
			cliente = new Cliente();
			// id do Cliente
			if (dadosCliente[0] != null) {
				cliente.setId((Integer)dadosCliente[0]);
			}
			
			// nome de Cliente
			if (dadosCliente[1] != null) {
				cliente.setNome((String)dadosCliente[1]);
			}
			
		}

		return cliente;
	}
	
	
	/**
	 * [UC0214] Efetuar Parcelamento de Débitos
	 *
	 *Alterado para registrar a transação na atualização
	 *do CPF do cliente.
	 *
	 * @author Anderson Italo, Vivianne Sousa
	 * @date 11/08/2009, 30/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCPFCliente(String cpf,Integer idCliente, Usuario usuarioLogado) throws ControladorException{
	
		try {
			
			String zeros = "";
			for (int a = 0; a < (11 - cpf.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cpf = zeros.concat(cpf);
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			
			Collection colecaoClientes = this.getControladorUtil().pesquisar(filtroCliente, Cliente.class.getName());
			
			Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecaoClientes);
			
			cliente.setCpf(cpf);
			
			//------------ <REGISTRAR TRANSAÇÃO>----------------------------

			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_CLIENTE_ATUALIZAR, cliente.getId(),
					cliente.getId(),
					new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

			registradorOperacao.registrarOperacao(cliente);
			String[] atributos = new String[1];
			atributos[0] = "cpf";
			//Registra a transação apenas para os atributos que quer-se registrar
			getControladorTransacao().registrarTransacao(cliente, atributos);
			
			// ------------ </REGISTRAR TRANSAÇÃO>----------------------------

			this.repositorioCliente.atualizarCPFCliente(cpf,idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	private ControladorTransacaoLocal getControladorTransacao() {
		ControladorTransacaoLocalHome localHome = null;
		ControladorTransacaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorTransacaoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_TRANSACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
/////////////////////////////////////////////////////////////////////////	
 	
	/**
	 * 
	 *Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Flávio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteUsuario(Integer idImovel)throws ControladorException{
	
		try {

			return this.repositorioClienteImovel.retornaClienteUsuario(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
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
	public Cliente retornaClienteProprietario(Integer idImovel) throws ControladorException{
	
		try {

			return this.repositorioClienteImovel.retornaClienteProprietario(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public ClienteAtualizacaoCadastral obterClientetuAlizacaoCadastral(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ControladorException{
	
		try {
			ClienteAtualizacaoCadastral cliente = null;	
			
			Object[] element =  this.repositorioCliente.obterDadosCliente(idImovel, idClienteRelacaoTipo);
			
			if (element != null) {				

				cliente = new ClienteAtualizacaoCadastral();		
			
				cliente.setIdCliente((Integer) element[0]);					
				
				cliente.setIdImovel(idImovel);
					
				if (element[1] != null){
					cliente.setNomeCliente((String) element[1]);
				}
				
				if (element[2] != null){
					cliente.setIdClienteTipo((Integer) element[2]);
				}
				
				if (element[3] != null){
					cliente.setCpfCnpj((String) element[3]);
				}else if(element[4] != null){
					cliente.setCpfCnpj((String) element[4]);
				}
				
				if (element[5] != null){
					cliente.setRg((String) element[5]);
				}
				
				if (element[6] != null){
					cliente.setDataEmissaoRg((Date) element[6]);
				}
				
				if (element[7] != null){
					cliente.setDsAbreviadaOrgaoExpedidorRg((String)element[7]);
				}
				
				if (element[8] != null){
					cliente.setDsUFSiglaOrgaoExpedidorRg((String)element[8]);
				}
				
				if (element[9] != null){
					cliente.setDataNascimento((Date) element[9]);
				}
				
				//Profissão ou Ramo de Atividade
				if(element[25] != null){
					if(((Short)element[25]).equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
						if (element[10] != null){
							cliente.setIdProfissao((Integer)element[10]);
						}	
					}else{
						if(element[24] != null){
							cliente.setIdRamoAtividade((Integer)element[24]);
						}	
					}
				}
				
				if (element[11] != null){
					cliente.setIdPessoaSexo((Integer)element[11]);
				}
				
				if (element[12] != null){
					cliente.setNomeMae((String) element[12]);
				}
				
				if (element[13] != null){
					cliente.setIndicadorUso((Short) element[13]);
				}
				
				if (element[14] != null){
					cliente.setEmail((String) element[14]);
				}
				
				if (element[15] != null){
					cliente.setIdEnderecoTipo((Integer)element[15]);
				}
				
				if(element[16] != null){
					cliente.setIdLogradouro((Integer) element[16]);
				}else if(element[17] != null){
					cliente.setIdLogradouro((Integer) element[17]);
				}
				
				//Logradouro
				Collection colecaoEndereco = getControladorEndereco().pesquisarLogradouroCliente((Integer) element[0]);
				if(colecaoEndereco != null && !colecaoEndereco.isEmpty()){
					
					Iterator enderecoIterator = colecaoEndereco.iterator();
					
					Object[] arrayEndereco = (Object[]) enderecoIterator.next();
					
					String nome = (String)arrayEndereco[0];				
					cliente.setDescricaoLogradouro(nome);
										
					if(arrayEndereco[3] != null){
						Integer idTipo = (Integer)arrayEndereco[3];
						cliente.setIdLogradouroTipo(idTipo);
						String tipo = (String)arrayEndereco[1];
						cliente.setDsLogradouroTipo(tipo);
					}
					
					if(arrayEndereco[4] != null){
						Integer idTitulo = (Integer)arrayEndereco[4];
						cliente.setIdLogradouroTitulo(idTitulo);
						String titulo = (String)arrayEndereco[2];
						cliente.setDsLogradouroTitulo(titulo);
					}	
					
					if(arrayEndereco[5] != null){
						Integer idMunicipio = (Integer)arrayEndereco[5];
						cliente.setIdMunicipio(idMunicipio);
						String nomeMunicipio = (String)arrayEndereco[6];
						cliente.setNomeMunicipio(nomeMunicipio);
					}
					
					if(arrayEndereco[7] != null){
						Integer idUnidadeFederacao = (Integer)arrayEndereco[7];
						cliente.setIdUinidadeFederacao(idUnidadeFederacao);
						String dsUnidadeFederacao = (String)arrayEndereco[8];
						cliente.setDsUFSiglaMunicipio(dsUnidadeFederacao);
					}
				}
				
				//Cep
				if(element[18] != null){
					cliente.setCodigoCep((Integer) element[18]);
				}
				
				//Bairro
				if(element[19] != null){
					cliente.setIdBairro((Integer) element[19]);
				}
				
				//Descrição do bairro
				if(element[20] != null){
					cliente.setNomeBairro((String) element[20]);
				}
				
				//Código de referência
				if (element[21] != null){
					cliente.setIdEnderecoReferencia((Integer) element[21]);
				}
				
				//Número do imóvel
				String numeroImovel = (String)element[22];
				if(numeroImovel != null && !numeroImovel.trim().equals("")){
					cliente.setNumeroImovel(numeroImovel);
				}
				
				//Complemento do Imóvel
				String trunk = ((String) element[23]);
				
				if ( trunk != null && trunk.length() > 25 ){
					trunk = trunk.substring( 0, 24 );
				}				
								
				if(element[23] != null){
					cliente.setComplementoEndereco(trunk);
				}
				
				cliente.setIdClienteRelacaoTipo(new Integer(idClienteRelacaoTipo));
				
			}
			
			return cliente;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente Fone
	 * @throws ControladorException
	 */

	public Collection obterDadosClienteFone(Integer idCliente)
			throws ControladorException {
	
		try {

			return this.repositorioCliente.obterDadosClienteFone(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
    /**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/09/2008
	 */
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ControladorException{
		
		try {

			return this.repositorioCliente.verificaExistenciaClienteAtualizacaoCadastral(idCliente);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	/**
	 * [UC0014] Manter Imóvel
	 * [FS0017] Registra Fim de Relação do(s) Cliente(s) com Imóvel
	 *
	 * @author Ana Maria
	 * @date 13/10/2008
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(Integer idImovel)
		throws ControladorException{
		try {

			return repositorioClienteImovel.pesquisarClienteImovel(idImovel);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}	
	}
	
	/**
	 * Pesquisa a quantidade de clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ControladorException {
		try {
			return this.repositorioCliente.pesquisarClienteResponsavelSuperiorParaPaginacaoCount(helper);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Pesquisa os clientes responsáveis superiores com os condicionais informados
	 * 
	 * @author Rafael Corrêa
	 * @date 18/11/08
	 */
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ControladorException {
		
		try {
			return this.repositorioCliente.pesquisarClienteResponsavelSuperiorParaPaginacao(helper, numeroPagina);
		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
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
		throws ControladorException {
	    try {
	        return repositorioCliente.pesquisarClienteAtualizacaoCadastral(idCliente, idImovel, idClienteRelacaoTipo);
	    } catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}

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
		throws ControladorException {
	    try {
	        return repositorioCliente.pesquisarClienteFoneAtualizacaoCadastral(idCliente, idMatricula, idTipoFone, idClienteRelacaoTipo,numeroFone);
	    } catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * @author Daniel Alves
	 * @date 02/09/2010
	 * @param idClienteImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ControladorException{
		
		try{
			repositorioClienteImovel.atualizarIndicadorNomeContaClienteImovel(idClienteImovel);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	
	/**
	 * 
	 * Atualiza telefone padrão
	 *
	 * @author Daniel Alves
	 * @date 06/09/2010
	 *
	 * @param idCliente
	 * @param idClienteFonePadrao  (novo telefone padrão do cliente).
	 * @throws ControladorException 
	 */
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao) throws ControladorException{
		
		try{			
			repositorioCliente.atualizarTelefonePadrao(idCliente, idClienteFonePadrao);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	/**
	 * Remove todos os telefones de um determinado cliente
	 * 
	 * @param idCliente
	 *            Código do cliente que terá seus telefones apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosTelefonesPorCliente(Integer idCliente) throws ControladorException{			
		try{			
			repositorioCliente.removerTodosTelefonesPorCliente(idCliente);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
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
	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ControladorException {
		try{			
			return repositorioClienteImovel.retornaDadosClienteUsuario(idImovel);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	

	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 * 
	 * [SB0003] – Obter Nome do Cliente
	 * @author Mariana Victor
	 * @date 11/03/2011
	 * 
	 * */
	public String obterNomeCliente(Integer idImovel) throws ControladorException {
		
		try{
			
			SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema();
			
			Cliente cliente  = this.repositorioClienteImovel.retornaDadosClienteUsuario(idImovel);
			
			if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().equals(ConstantesSistema.SIM)
				&& cliente.getIndicadorUsoNomeFantasiaConta().equals(ConstantesSistema.SIM)
				&& cliente.getNomeAbreviado() != null) {
				
				return cliente.getNomeAbreviado();
					
			} else {
				return cliente.getNome();
			}
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
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
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel) throws ControladorException {
		try{			
			return repositorioCliente.pesquisarClienteUsuarioDoImovel(idImovel);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}

	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Clientes por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteCliente(String valor) throws ControladorException{
		try{			
			return repositorioCliente.filtrarAutocompleteCliente(valor);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Clientes Responsavel por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteClienteResponsavel(String valor) throws ControladorException{
		try{			
			return repositorioCliente.filtrarAutocompleteCliente(valor);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
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
			throws ControladorException{
		try{			
			return repositorioCliente.pesquisarQtdClientesAssociadosResponsavelNaoJuridica(idCliente);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Retorna Lista de Imóveis associados ao cliente
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )throws ControladorException{
		try{			
			return repositorioCliente.pesquisarImoveisAssociadosCliente(idCliente, relacaoTipo);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
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
	 * @param Matricula
	 * 
	 * @throws ControladorException
	 * @return String
	 */
	public String validarCliente(String cpfCliente, Integer matricula) throws ControladorException{
		try{			
			return repositorioCliente.validarCliente(cpfCliente, matricula);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	/**
	 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
	 * 
	 * Pesquisar os clientes a partir do imóvel e o tipo de relação com o cliente
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ControladorException{
		try{
			
			return repositorioCliente.obterClienteImovelporRelacaoTipo(idImovel,idRelacaoTipo);
				
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
		
	}
	
	/**
	 * 
	 * Retorna o cliente responsável
	 *
	 * @author Sávio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel)
			throws ControladorException{
		try{
					
		  return repositorioClienteImovel.retornaIdClienteResponsavelIndicadorEnvioConta(idImovel);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
				
	}

	/**
	 * [UC1139] Atualizar Contrato de Parcelamento por Cliente
	 * 
	 * @author Mariana Victor
	 * @data 05/08/2011
	 */
	public Cliente pesquisarDadosCliente(Integer idCliente)
			throws ControladorException {
		try{
					
		  return repositorioCliente.pesquisarDadosCliente(idCliente);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
				
	}
	
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
			Integer idImovel) throws ControladorException{
		try{
			
			  return repositorioClienteImovel.pesquisarClienteImovelOSFiscalizada(idImovel);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public Cliente pesquisarDadosClientePessoaFisica(String cpf)throws ControladorException{
			try{
			
			  return repositorioCliente.pesquisarDadosClientePessoaFisica(cpf);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ClienteFone pesquisarDadosClienteFonePadrao(Integer idCliente) 
		throws ControladorException{
			try{
			
			  return repositorioCliente.pesquisarDadosClienteFonePadrao(idCliente);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ClienteEndereco pesquisarDadosClienteEnderecoCorrespondecia(Integer idCliente) throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosClienteEnderecoCorrespondecia(idCliente);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public Cliente pesquisarDadosClientePessoaJuridica(String cnpj) throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosClientePessoaJuridica(cnpj);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ConsultarReceitaFederal pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(String cpf) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosReceitaFederalPessoaFisicaJahCadastrada(cpf);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1089] Consultar Dados do Registro SPC
	 * 
	 * @author Paulo Diniz
	 * @data 28/09/2011
	 */
	public ConsultarReceitaFederal pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(String cnpj) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.pesquisarDadosReceitaFederalPessoaJuridicaJahCadastrada(cnpj);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	

	/**
	 * UC[0009] manter Cliente
	 *
	 * @author Fernanda Almeida
	 * @date 15/12/2011
	 *
	 */
	public Collection<?> obterImovelCorporativo(Integer idCliente) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.obterImovelCorporativo(idCliente);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * 
	 * UC[0378] -Associar Tarifa de Consumo a Imóveis
	 *
	 * @author Fernanda Almeida
	 * @date 15/12/2011
	 *
	 */
	public Collection<Object[]> obterClientePorImovel(Integer idImovel) 
		throws ControladorException{
		try{
			
			  return repositorioCliente.obterClientePorImovel(idImovel);
						
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}

	/**
	 * Metodo responsavel por pesquisar todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteVirtual> pesquisarClienteVirtual(Date periodoInicial , Date periodoFinal , int numeroPagina) throws ControladorException {
		try{
			
		  return repositorioCliente.pesquisarClienteVirtual(periodoInicial, periodoFinal, numeroPagina);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * Metodo responsavel por pesquisar quantidade todos os clientes cadastrado no portal por um intervalo de tempo.
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/2011
	 * 
	 * @return Collection<ClienteVirtual>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeClienteVirtual(Date periodoInicial , Date periodoFinal ) throws  ControladorException {
		try{
		
		  return repositorioCliente.pesquisarQuantidadeClienteVirtual(periodoInicial, periodoFinal);
					
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	

	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0005] - Gerar Negativação para o Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @date 06/12/2011
	 */
	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(
			Integer idImovel, Integer idClienteRelacaoTipo)throws ControladorException{
		try{
		  return repositorioClienteImovel.
			  pesquisarIndicadorNegativacaoPeriodoClienteResponsavel
			  (idImovel,idClienteRelacaoTipo);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0005] - Gerar Negativação para o Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @date 07/12/2011
	 */
	public Cliente pesquisarDadosClienteParaNegativacao(Integer idCliente, String cnpjEmpresa)
			throws ControladorException{
		try{
		  return repositorioCliente.pesquisarDadosClienteParaNegativacao(idCliente, cnpjEmpresa);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclusão de Negativação
	 * [SB0006] Verificar criterio de negativacao para o imovel
	 * 
	 * @author Vivianne Sousa
	 * @date 15/12/2011
	 */
	public boolean existeEnderecoParaCliente(Integer idCliente)throws ControladorException{
		try{
			boolean retorno = false;
			Integer idEnderecoCliente = repositorioCliente.pesquisarEnderecoClienteParaNegativacao(idCliente);
			if(idEnderecoCliente != null){
				retorno = true;
			}
			return retorno;
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
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
	public Integer obterClienteEntregaContaPorIdConta(Integer idConta) throws ControladorException{
		
		try{
			
			return repositorioCliente.obterClienteEntregaContaPorIdConta(idConta);
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
	/**
	 * Método para pesquisa de árvore de clientes vinculados aos superiores - Postgrees
	 * @author Amelia Pessoa
	 * @param idClientePrincipal
	 * @return Coleção de Integer contendo ids dos clientes 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarArvoreClientesResponsaveis(Integer idClientePrincipal) 
			throws ControladorException{
		try{
		  return repositorioCliente.pesquisarArvoreClientesResponsaveis(idClientePrincipal);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
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
	public Integer pesquisarIndicadorValidarCpfCnpjCliente(Integer idCliente) throws ControladorException {
		try{
			return repositorioCliente.pesquisarIndicadorValidarCpfCnpjCliente(idCliente);
		}catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}

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
	public String obterNomeClienteUsuarioConta(Integer idConta)throws ControladorException {
		try{
			return repositorioCliente.obterNomeClienteUsuarioConta(idConta);
			
		}catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
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
	public ClienteConta pesquisarClienteConta(Integer idConta, Integer idRelacaoTipo) throws ControladorException {
		try{
			return repositorioCliente.pesquisarClienteConta(idConta, idRelacaoTipo);
			
		}catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
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
	public Integer obterIdClienteResponsavelImovel(Integer idContaSelecionada) throws ControladorException {
		try{
			return repositorioCliente.obterIdClienteResponsavelImovel(idContaSelecionada);
			
		}catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	public String pesquisarNomeClienteExcluido(Integer matricula) throws ControladorException{
		
		try {
			return repositorioCliente.pesquisarNomeClienteExcluido(matricula);
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
	/**
	 * @author Flávio Leonardo
	 * @date 19/08/2014
	 * 
	 * @param idCliente
	 * @return boolean
	 * @throws ControladorException
	 */	
	public boolean verificarSeClienteEstaNegativado(Integer idCliente, Usuario usuario) throws ControladorException{
		
			boolean negativado = false;
			
			boolean permissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(PermissaoEspecial.VINCULAR_DESVINCULAR_ALTERAR_CLIENTE_NEGATIVADO, usuario);
			
			SistemaParametro parametros = getControladorUtil().pesquisarParametrosDoSistema();
			
			if(!permissaoEspecial && parametros.getIndicadorPermissaoAlteracaoClienteNegativado().equals(ConstantesSistema.NAO)){
				FiltroNegativadorMovimentoReg filtro = new FiltroNegativadorMovimentoReg();
				filtro.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.CLIENTE_ID, idCliente));
				filtro.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.INDICADOR_ACEITO, ConstantesSistema.SIM));
				filtro.adicionarParametro(new ParametroNulo(FiltroNegativadorMovimentoReg.CODIGO_EXCLUSAO_TIPO));
				filtro.adicionarParametro(new ParametroNulo(FiltroNegativadorMovimentoReg.NEGATIVADOR_EXLUSAO_MOTIVO));
				Collection colecao = getControladorUtil().pesquisar(filtro, NegativadorMovimentoReg.class.getName());
				
				if(colecao != null && !colecao.isEmpty()){
					negativado = true;
				}
			}
			
			
			return negativado;
	}
	
	
	/**
	 * @author Diogo Luiz
	 * @date 10/10/2014
	 * 
	 * @param cliente
	 * @param usuario
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarPermissaoEspecialClienteAlterarNomeCPFCNPJValidado(Cliente cliente, Usuario usuario) throws ControladorException{
		
		SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
		boolean retorno = true;
		
		if(sistemaParametro.getIndicadorAlterarNomeClienteCpfCnpjValidado().equals(ConstantesSistema.SIM)){
			
			boolean permissaoEspecial = getControladorPermissaoEspecial().verificarPermissaoEspecial(
				PermissaoEspecial.PERMITR_ALTERAR_NOME_PARA_CPF_CNPJ_VALIDADO, usuario);
			
			if(cliente.getIndicadorValidaCpfCnpj().equals(new Integer(ConstantesSistema.SIM))){				
				if(!permissaoEspecial){
					retorno = false;
				}
			}			
		}
		return retorno;
	}	

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public ClienteAtualizacaoCadastralDM obterClientetuAlizacaoCadastralDM(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ControladorException{
	
		try {
			ClienteAtualizacaoCadastralDM cliente = null;	
			
			Object[] element =  this.repositorioCliente.obterDadosCliente(idImovel, idClienteRelacaoTipo);
			
			if (element != null) {				

				cliente = new ClienteAtualizacaoCadastralDM();		
			
				cliente.setIdCliente((Integer) element[0]);					
				
				if (element[1] != null){
					cliente.setNomeCliente((String) element[1]);
				}
				
				if (element[2] != null){
					ClienteTipo clienteTipo = new ClienteTipo();
					clienteTipo.setId((Integer) element[2]);
					cliente.setClienteTipo(clienteTipo);
				}
				
				if (element[3] != null){
					cliente.setCpfCnpj((String) element[3]);
				}else if(element[4] != null){
					cliente.setCpfCnpj((String) element[4]);
				}
				
				if (element[5] != null){
					cliente.setRg((String) element[5]);
				}
				
				if (element[6] != null){
					cliente.setDataEmissaoRg((Date) element[6]);
				}

				if (element[26] != null){
					OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
					orgaoExpedidorRg.setId((Integer)element[26]);
					if (element[7] != null){
						orgaoExpedidorRg.setDescricaoAbreviada((String) element[7]);
					}
					cliente.setOrgaoExpedidorRG(orgaoExpedidorRg);
				}
				
				if (element[27] != null){
					UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
					unidadeFederacao.setId((Integer)element[27]);
					if (element[8] != null){
						unidadeFederacao.setDescricao((String)element[8]);
					}
					cliente.setUnidadeFederacao(unidadeFederacao);
				}
				
				if (element[9] != null){
					cliente.setDataNascimento((Date) element[9]);
				}

				if (element[11] != null){
					PessoaSexo sexo = new PessoaSexo();
					sexo.setId((Integer)element[11]);
					cliente.setSexo(sexo);
				}
				
				if (element[12] != null){
					cliente.setNomeMae((String) element[12]);
				}
				
				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
				clienteRelacaoTipo.setId(new Integer(idClienteRelacaoTipo));
				cliente.setClienteRelacaoTipo(clienteRelacaoTipo);
			}
			
			return cliente;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public List<ClienteFoneAtualizacaoCadastralDM> obterDadosClienteFoneAtualizacaoCadastralDM
		(Integer idCliente) throws ControladorException {
			try{			
				return repositorioCliente.obterDadosClienteFoneAtualizacaoCadastralDM(idCliente);
				
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Movel
	 * 
	 * @author Bruno Sá Barreto
	 * @date 07/10/2014
	 * 
	 */
	public AtualizarClienteAPartirDispositivoMovelHelper atualizarClienteAPartirDoDispositivoMovel(
			AtualizarClienteAPartirDispositivoMovelHelper helper)
			throws ControladorException {

		//Classe responsavel por enviar todos os parametros para atualizacao cadastral
		DadosCadastraisTransacaoBatchHelper dadosCadastraisTransacaoBatchHelper = new DadosCadastraisTransacaoBatchHelper();
		dadosCadastraisTransacaoBatchHelper.setIdOperacao(helper.getTipoOperacao());
		
 		//Para cada tipo de alteracao (inclusao, remocao, atualizacao) os atributos são agrupados pelo tipo
 		Collection<TipoAlteracaoTransacaoBatchHelper> colecaoTipoAlteracaoTransacao = new ArrayList<TipoAlteracaoTransacaoBatchHelper>();
 		TipoAlteracaoTransacaoBatchHelper tipoAlteracaoTransacaoBatchHelper = new TipoAlteracaoTransacaoBatchHelper();
 		
		//Registrar Transação no Processo Batch - adiciona na colecao os atributos que vao ser exibidos no log da transação
		Collection<ParametrosTransacaoBatchHelper> colecaoTransacaoHelper = new ArrayList<ParametrosTransacaoBatchHelper>();
		
		helper.setClienteAtualizar(false);
		helper.setClienteValidar(true);
		Integer idClienteInseridoAtualizado = null;
		String nomeClienteArgumento = null;
		
		try {
			String nomeClienteNovo = null;
			Integer idClienteImovelNovo = null;
			// 4. O sistema compara os dados enviados com os dados recebidos de retorno
			// 4.1. Caso tenha havido alteração do cliente
			if (!helper.getNomeNovo().equals(helper.getNomeAnterior()) || 
					!Util.stringsIguais(helper.getCpfNovo(), helper.getCpfAnterior()) || 
					!Util.stringsIguais(helper.getCnpjNovo(), helper.getCnpjAnterior())) {

				String numeroCpfCnpj = null;
				Boolean cpf = false;
				if (helper.getCpfNovo() != null && !helper.getCpfNovo().trim().equals("")) {
					numeroCpfCnpj = helper.getCpfNovo();
					cpf = true;
				} else {
					numeroCpfCnpj = helper.getCnpjNovo();
				}

				// 4.1.1. Pesquisa um cliente com o novo CPF/CNPJ informado
				Cliente cliente = this.repositorioCliente.pesquisarClienteDispositivoMovel(numeroCpfCnpj, cpf, null);

				Cliente clienteAnterior = null;
				Integer idClienteAnterior = null;
				if (helper.getIdClienteAnterior() != null) {
					clienteAnterior = this.repositorioCliente.pesquisarClienteDispositivoMovel(null, null,Integer.valueOf(helper.getIdClienteAnterior()));
					idClienteAnterior = clienteAnterior.getId();
				}

				// 4.1.2. Caso encontre um cliente com o novo CPF/CNPJ informado
				// e com o mesmo nome do novo nome informado
				if (cliente != null && cliente.getId() != null && cliente.getNome().equals(helper.getNomeNovo())) {
					idClienteInseridoAtualizado = cliente.getId();
					// 4.1.2.1. Substitui o cliente usuário do imóvel passando a identificação do cliente pesquisado
					// (CLIE_ID) [SB0003 - Substituir Cliente Usuário do Imóvel];
					boolean imovelSubstituido = getControladorCadastro().substituirClienteUsuarioDoImovel(cliente.getId(),idClienteAnterior, helper, clienteAnterior);

					if (helper.getImovelAtualizacaoCadastral() != null && imovelSubstituido) {
						
						helper.setClienteAtualizar(true);

						// [SB0006] - Inserir Registro no retorno da Atualizacao Cadastral
						getControladorCadastro().inserirRetornoAtualizacaoCadastralDM(
								helper.getImovelAtualizacaoCadastral().getIdImovel(),
								cliente.getId(),
								AtributoAtualizacaoCadastralDM.CLIENTE,
								RetornoAtualizacaoCadastralDM.SITUACAO_ATUALIZADO,
								MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO,
								helper.getImovelAtualizacaoCadastral().getParametroTabelaAtualizacaoCadastralDM().getId(),
								helper.getImovelAtualizacaoCadastral(),
								null);
					}

				}//caso o cliente do CPF informado com nome diferente do nome informado em campo
				else if (cliente != null && cliente.getId() != null && !cliente.getNome().equals(helper.getNomeNovo())) {
					
					//1. Atualiza o cliente anterior apagando a informação de CPF/CNPJ [SB0001] - Atualizar CPF/CNPJ do Cliente Anterior;
					this.repositorioCliente.atualizarCpfCnpjCliente("", "", cliente.getId());
					
					//2. Insere um novo cliente com o CPF/CNPJ e o novo nome informado [SB0008] - Verificar Dados do RG do Cliente, [SB0002] - Inserir Novo Cliente;
					Integer idClienteInserido = this.inserirNovoCliente(helper, cliente, colecaoTipoAlteracaoTransacao);
					this.verificarDadosRGCliente(helper, idClienteInserido, colecaoTipoAlteracaoTransacao);

					//3. Substitui o cliente usuário do imóvel passando a identificação do cliente inserido (CLIE_ID) [SB0003] - Substituir Cliente Usuário do Imóvel;
					boolean imovelSubstituido = this.substituirClienteUsuarioDoImovel(idClienteInserido, idClienteAnterior, helper, clienteAnterior);

					idClienteInseridoAtualizado = idClienteInserido;

					if (helper.getImovelAtualizacaoCadastral() != null && imovelSubstituido) {
					
						// [SB0006] - Inserir Registro no retorno da Atualizacao Cadastral
						getControladorCadastro().inserirRetornoAtualizacaoCadastralDM(
							helper.getImovelAtualizacaoCadastral().getIdImovel(),
							cliente.getId(),
							AtributoAtualizacaoCadastralDM.CLIENTE,
							RetornoAtualizacaoCadastralDM.SITUACAO_ATUALIZADO,
							MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO,
							helper.getImovelAtualizacaoCadastral().getParametroTabelaAtualizacaoCadastralDM().getId(),
							helper.getImovelAtualizacaoCadastral(),
							null);
					}
					
					TipoAlteracaoTransacaoBatchHelper tipoAlteracaoClienteTransacaoBatchHelper = new TipoAlteracaoTransacaoBatchHelper();
					tipoAlteracaoClienteTransacaoBatchHelper.setIdTabela(Tabela.CLIENTE);
					tipoAlteracaoClienteTransacaoBatchHelper.setTipoAlteracao(AlteracaoTipo.ALTERACAO);
			 		Collection<ParametrosTransacaoBatchHelper> colecaoParametrosTransacaoBatchHelper = new ArrayList<ParametrosTransacaoBatchHelper>();
			 		
			 		
					ParametrosTransacaoBatchHelper idClienteTransacao = new ParametrosTransacaoBatchHelper(
			 				String.valueOf(cliente.getId()), String.valueOf(cliente.getId()), TabelaColuna.IDCLIENTE_TABELA_CLIENTE);
					colecaoParametrosTransacaoBatchHelper.add(idClienteTransacao);
					
					
					if ( !cliente.getNome().equals(helper.getNomeNovo()) ) {
						ParametrosTransacaoBatchHelper nomeClienteTransacao = new ParametrosTransacaoBatchHelper(
				 				cliente.getNome(), helper.getNomeNovo(), TabelaColuna.NOME_CLIENTE_TABELA_CLIENTE);
						colecaoParametrosTransacaoBatchHelper.add(nomeClienteTransacao);
						tipoAlteracaoClienteTransacaoBatchHelper.setColecaoParametrosTransacao(colecaoParametrosTransacaoBatchHelper);
						colecaoTipoAlteracaoTransacao.add(tipoAlteracaoClienteTransacaoBatchHelper);
					}
					
				} else if (cliente == null || cliente.getId() == null) {
					// 4.1.4. Caso não encontre um cliente com o novo CPF/CNPJ informado
					// 4.1.1. Pesquisa um cliente com o novo CPF/CNPJ informado
					// 4.1.4.1. Insere um novo cliente com o CPF/CNPJ e o novo nome informados
					
					// [SB0002 - Inserir Novo Cliente]
					Integer idCliente = this.inserirNovoCliente(helper, clienteAnterior, colecaoTipoAlteracaoTransacao);
					helper.setAtualizarMovimentoClienteFone(true);
					idClienteInseridoAtualizado = idCliente;
					
					// [SB0008 - Verificar Dados do RG do Cliente]
					this.verificarDadosRGCliente(helper, idClienteInseridoAtualizado, colecaoTipoAlteracaoTransacao);
					// 4.1.4.2. Substitui o cliente usuário do imóvel passando a identificação do cliente inserido
					// (CLIE_ID) [SB0003 - Substituir Cliente Usuário do Imóvel];
					this.substituirClienteUsuarioDoImovel(idCliente,idClienteAnterior, helper,clienteAnterior);

					if (helper.getImovelAtualizacaoCadastral() != null) {
						helper.setClienteAtualizar(true);

						// [SB0006] - Inserir Registro no retorno da Atualizacao Cadastral
						getControladorCadastro().inserirRetornoAtualizacaoCadastralDM(
							helper.getImovelAtualizacaoCadastral().getIdImovel(),
							idCliente,
							AtributoAtualizacaoCadastralDM.CLIENTE,
							RetornoAtualizacaoCadastralDM.SITUACAO_ATUALIZADO,
							MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO,
							helper.getImovelAtualizacaoCadastral().getParametroTabelaAtualizacaoCadastralDM().getId(),
							helper.getImovelAtualizacaoCadastral(),
							null);
					}
				}
			}

			// 4.2. O sistema verifica se houveram alterações nos demais
			// dados do cliente usuário atual do imóvel:

			Integer idImovel = Integer.valueOf(helper.getMatricula());

			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel)); 
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
			Collection<ClienteImovel> colecaoClienteImovel = this.getControladorUtil().pesquisar(filtroClienteImovel,ClienteImovel.class.getName());
			ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);

			Integer idCliente = null;
			if (clienteImovel == null || clienteImovel.getId() == null) {

				// 1.2. Vincula o novo cliente usuário atual do imóvel
				// 1.2.1. Insere nova linha na tabela de relacionamento entre cliente e imóvel, conforme a seguir;
				ClienteImovel inserirClienteImovel = new ClienteImovel();

				Imovel imovel = new Imovel();
				imovel.setId(idImovel);

				ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
				clienteRelacaoTipo.setId(new Integer(ClienteRelacaoTipo.USUARIO));

				// CLIE_ID do novo cliente pesquisado ou inserido,
				Cliente cliente = new Cliente();
				cliente.setId(idClienteImovelNovo);
				inserirClienteImovel.setCliente(cliente);
				
				// IMOV_ID da tabela ORDEM_SERVICO com ORSE_ID=ORSE_ID informado como parâmetro.
				inserirClienteImovel.setImovel(imovel);

				// Data corrente.
				inserirClienteImovel.setDataInicioRelacao(new Date());

				// CRTP_ID da tabela CLIENTE_RELACAO_TIPO correspondente a "USUARIO".
				inserirClienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

				inserirClienteImovel.setIndicadorNomeConta(ConstantesSistema.SIM);
				inserirClienteImovel.setUltimaAlteracao(new Date());

				Integer idClienteImovel = (Integer) this.getControladorUtil().inserir(inserirClienteImovel);
				inserirClienteImovel.setId(idClienteImovel);
				clienteImovel = inserirClienteImovel;
				idCliente = idClienteImovelNovo;
				idClienteInseridoAtualizado = idCliente;
				nomeClienteArgumento = nomeClienteNovo;

			} else {
				idCliente = clienteImovel.getCliente().getId();
				idClienteInseridoAtualizado = idCliente;
				nomeClienteArgumento = clienteImovel.getCliente().getNome();
			}

			// 4.2.1. Caso tenha havido alguma alteração nos dados do RG do cliente
			if (validaAlteracaoRGClienteDispositivoMovel(helper, idCliente)) {

				// [SB0008 - Verificar Dados do RG do Cliente]
				this.verificarDadosRGCliente(helper, idClienteInseridoAtualizado, colecaoTipoAlteracaoTransacao);

				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				// [SB0004 - Atualizar Dados do RG do Cliente Atual]
				this.atualizarDadosRGClienteAtual(helper, cliente, colecaoTipoAlteracaoTransacao);

			}
			
			if(helper.isRemoverTelefone()){
				// atualizar dados telefone cliente - atualizacao cadastral
				getControladorCadastro().atualizarDadosFoneClienteAtual(
					helper.getIdClienteAtualizacaoCadastral(), idCliente, helper.getImovelAtualizacaoCadastral());
			}else{
				// atualizar telefone do cliente
				AtualizarClienteFoneAPartirDispositivoMovelHelper clienteFoneNovo = (AtualizarClienteFoneAPartirDispositivoMovelHelper) Util
						.retonarObjetoDeColecao(helper.getClienteFoneHelper());

				AtualizarClienteFoneAPartirDispositivoMovelHelper clienteFoneAnterior = (AtualizarClienteFoneAPartirDispositivoMovelHelper) Util
						.retonarObjetoDeColecao(helper.getClienteFoneAnteriorHelper());

				// 4.2.2. Caso tenha havido alguma alteração nos dados do Fone do cliente
				if ((clienteFoneAnterior == null && clienteFoneNovo != null) 
					|| (clienteFoneAnterior != null && clienteFoneNovo != null && (!Util.stringsIguais(clienteFoneAnterior.getDddFone(),clienteFoneNovo.getDddFone())
						|| !Util.stringsIguais(clienteFoneAnterior.getNumeroFone(),clienteFoneNovo.getNumeroFone()) 
						|| !Util.stringsIguais(clienteFoneAnterior.getRamalFone(),clienteFoneNovo.getRamalFone())))) {

					// atualiza os dados do Fone do cliente atual [SB0005 -
					// Atualizar Dados do Fone do Cliente Atual]
					this.atualizarDadosFoneClienteAtual(clienteFoneNovo, idCliente, helper.getUsuario());
					helper.setAtualizarMovimentoClienteFone(true);
				}
			}
			
			Short indicadorNomeConta = clienteImovel != null
					? clienteImovel.getIndicadorNomeConta()
					: ConstantesSistema.NAO;
			
			if(helper.getIndicadorProprietario().equals(ConstantesSistema.SIM)){				
				// [SB0014] - Substituir Cliente Proprietário do Imóvel;
				this.substituirClienteProprietarioImovel(idImovel, idClienteInseridoAtualizado, indicadorNomeConta);			
			}
			
			if(helper.getIndicadorResponsavel().equals(ConstantesSistema.SIM)){
				// [SB0013] - Substituir Cliente Responsável do Imóvel;
				this.substituirClienteResponsavelImovel(idImovel, idClienteInseridoAtualizado, indicadorNomeConta);
			}
			
			// ------------ REGISTRAR TRANSACAO----------------------------
			if ( colecaoTipoAlteracaoTransacao != null && !colecaoTipoAlteracaoTransacao.isEmpty() ) {
				
				UsuarioAcao usuarioAcao = UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO;
				Usuario  usuario = Usuario.USUARIO_BATCH;
	
				UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(usuario,usuarioAcao);
				
				OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
				Operacao operacao = new Operacao();
				operacao.setId(dadosCadastraisTransacaoBatchHelper.getIdOperacao());
				operacaoEfetuada.setOperacao(operacao);
				operacaoEfetuada.setDadosAdicionais("Nome: "+ nomeClienteArgumento);
				dadosCadastraisTransacaoBatchHelper.setIdArgumentoValor(idClienteInseridoAtualizado);
				operacaoEfetuada.setArgumentoValor(dadosCadastraisTransacaoBatchHelper.getIdArgumentoValor());
				
				dadosCadastraisTransacaoBatchHelper.setColecaoTipoAlteracaoTransacao(colecaoTipoAlteracaoTransacao);
				getControladorTransacao().processaRegistroOperacaoDadosCadastrais(usuarioAcaoUsuarioHelper, dadosCadastraisTransacaoBatchHelper, operacaoEfetuada);
			}
			// ------------ REGISTRAR TRANSACAO----------------------------
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}
		return helper;
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * [SB0014] - Substituir Cliente Proprietário do Imóvel;
	 * 
	 * @author Bruno Sá Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void substituirClienteProprietarioImovel(Integer idImovel,
			Integer idCliente, Short icNomeConta) throws ControladorException{
		Cliente proprietarioGsan = null;
		boolean inserirClienteImovel = false;
		
		try {
			proprietarioGsan = this.repositorioClienteImovel.retornaClienteProprietario(idImovel);
			
			//verificar se o cliente proprietario do imovel é diferente do idcliente fornecido
			if(proprietarioGsan != null && proprietarioGsan.getId() != null && !proprietarioGsan.getId().equals(idCliente)){
				//caso seja diferente associar o novo cliente como proprietario
				this.repositorioClienteImovel.desvincularClienteProprietarioImovel(idImovel, idCliente);
				inserirClienteImovel = true;
			} else if (proprietarioGsan == null){
				inserirClienteImovel = true;
			}
			
			if(inserirClienteImovel){
				ClienteImovel clienteImovel = new ClienteImovel();
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				clienteImovel.setImovel(imovel);
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				clienteImovel.setCliente(cliente);
				clienteImovel.setDataInicioRelacao(new Date());
				ClienteRelacaoTipo relaTipo = new ClienteRelacaoTipo();
				relaTipo.setId(Integer.valueOf(ClienteRelacaoTipo.PROPRIETARIO));
				clienteImovel.setClienteRelacaoTipo(relaTipo);
				clienteImovel.setIndicadorNomeConta(icNomeConta);
				clienteImovel.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(clienteImovel);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException(e.getMessage());
		}
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * [SB0013] - Substituir Cliente Responsável do Imóvel;
	 * 
	 * @author Bruno Sá Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void substituirClienteResponsavelImovel(Integer idImovel,
			Integer idCliente, Short icNomeConta) throws ControladorException{
		
		Integer idResponsavelGsan = null;
		boolean inserirClienteImovel = false;
		
		try {
			idResponsavelGsan = this.repositorioClienteImovel.retornaIdClienteResponsavel(idImovel);
			//verificar se o cliente responsavel do imovel é diferente do idcliente fornecido
			if(idResponsavelGsan != null && idResponsavelGsan != idCliente){
				//caso seja diferente associar o novo cliente como responsavel
				this.repositorioClienteImovel.desvincularClienteResponsavelImovel(idImovel, idCliente);
				inserirClienteImovel = true;
			}else if(idResponsavelGsan == null){
				inserirClienteImovel = true;
			}
			
			if(inserirClienteImovel){
				ClienteImovel clienteImovel = new ClienteImovel();
				Imovel imovel = new Imovel();
				imovel.setId(idImovel);
				clienteImovel.setImovel(imovel);
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				clienteImovel.setCliente(cliente);
				clienteImovel.setDataInicioRelacao(new Date());
				ClienteRelacaoTipo relaTipo = new ClienteRelacaoTipo();
				relaTipo.setId(Integer.valueOf(ClienteRelacaoTipo.RESPONSAVEL));
				clienteImovel.setClienteRelacaoTipo(relaTipo);
				clienteImovel.setIndicadorNomeConta(icNomeConta);
				clienteImovel.setUltimaAlteracao(new Date());
				getControladorUtil().inserir(clienteImovel);
			}
			
		} catch (ErroRepositorioException e) {
			throw new ControladorException(e.getMessage());
		}

	}

	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Móvel
	 * 
	 * @author Bruno Sá Barreto
	 * @date 27/08/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarExistenciaNegativacaoParaClienteImovel(
			Integer idClienteAnterior, Integer idImovel) throws ControladorException{
		Integer result = null;
		try {
			result = this.repositorioCliente.pesquisarExistenciaNegativacaoParaClienteImovel(idClienteAnterior,idImovel);
		} catch (ErroRepositorioException e) {
			throw new ControladorException(e.getMessage());
		}
		return result;
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Movel
	 * 
	 * @author Bruno Sá Barreto
	 * @date 07/10/2014
	 * 
	 */
	public boolean validaAlteracaoRGClienteDispositivoMovel(
			AtualizarClienteAPartirDispositivoMovelHelper helper,
			Integer idCliente) throws ControladorException {

		boolean retorno = false;
		// 4.2.1. Caso tenha havido alguma alteração nos dados do RG do cliente
		if (!Util.stringsIguais(helper.getRgAnterior(), helper.getRgNovo())
				|| (((helper.getOrgaoExpedidorAnterior() == null || helper
						.getOrgaoExpedidorAnterior().equals(""))
						&& helper.getOrgaoExpedidorNovo() != null && !helper
						.getOrgaoExpedidorNovo().equals(""))
						|| (helper.getOrgaoExpedidorAnterior() != null
								&& !helper.getOrgaoExpedidorAnterior().equals(
										"") && helper.getOrgaoExpedidorNovo() == null) || (helper
						.getOrgaoExpedidorAnterior() != null
						&& helper.getOrgaoExpedidorNovo() != null && Util
							.stringsIguais(helper.getOrgaoExpedidorAnterior(),
									helper.getOrgaoExpedidorNovo())))
				|| ((helper.getUnidadeFederacaoAnterior() == null
						&& helper.getUnidadeFederacaoNovo() != null && !helper
						.getUnidadeFederacaoNovo().equals(""))
						|| (helper.getUnidadeFederacaoAnterior() != null
								&& !helper.getUnidadeFederacaoAnterior()
										.equals("") && helper
								.getUnidadeFederacaoNovo() == null) || (helper
						.getUnidadeFederacaoAnterior() != null
						&& helper.getUnidadeFederacaoNovo() != null && !Util
							.stringsIguais(
									helper.getUnidadeFederacaoAnterior(),
									helper.getUnidadeFederacaoNovo())))) {

			retorno = true;
			// atualiza os dados do RG do cliente atual

		}
		return retorno;
	}
	

	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Mï¿½vel
	 * 
	 * [SB0008] - Verificar Dados do RG do Cliente
	 * 
	 * @author Mariana Victor, Arthur Carvalho
	 * @date 17/10/2011 , 12/09/2013
	 * 
	 * @param Integer
	 * @return
	 * @throws ControladorException
	 */
	public void verificarDadosRGCliente(
			AtualizarClienteAPartirDispositivoMovelHelper helper, 
			Integer idClienteArgumento, Collection<TipoAlteracaoTransacaoBatchHelper> colecaoTipoAlteracaoTransacao)
			throws ControladorException {

		// 1. Caso exista outro cliente com os dados do RG, o sistema limpa os
		// dados encontrados:
		if (helper.getRgNovo() != null
				&& helper.getUnidadeFederacaoNovo() != null
				&& !helper.getUnidadeFederacaoNovo().equals("")
				&& helper.getOrgaoExpedidorNovo() != null
				&& !helper.getOrgaoExpedidorNovo().equals("")) {

			FiltroCliente filtroClienteRG = new FiltroCliente();
			filtroClienteRG.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
			filtroClienteRG.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);
			filtroClienteRG.adicionarParametro(new ParametroSimples(FiltroCliente.RG, helper.getRgNovo()));
			filtroClienteRG.adicionarParametro(new ParametroSimples(FiltroCliente.UNIDADE_FEDERACAO_ID, helper.getUnidadeFederacaoNovo()));
			filtroClienteRG.adicionarParametro(new ParametroSimples(FiltroCliente.ORGAO_EXPEDIDOR_RG_ID, helper.getOrgaoExpedidorNovo()));
			
			if ( idClienteArgumento!= null ) {
				filtroClienteRG.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroCliente.ID, idClienteArgumento));
			}

			Collection<Cliente> colecaoClienteRG = this.getControladorUtil()
					.pesquisar(filtroClienteRG, Cliente.class.getName());

			if (colecaoClienteRG != null && !colecaoClienteRG.isEmpty()) {

				Iterator<Cliente> iteratorCliente = colecaoClienteRG.iterator();
				while (iteratorCliente.hasNext()) {

					Cliente cliente = (Cliente) iteratorCliente.next();
					
					TipoAlteracaoTransacaoBatchHelper tipoAlteracaoInclusaoTransacaoBatchHelper = new TipoAlteracaoTransacaoBatchHelper();
					tipoAlteracaoInclusaoTransacaoBatchHelper.setIdTabela(Tabela.CLIENTE);
					tipoAlteracaoInclusaoTransacaoBatchHelper.setTipoAlteracao(AlteracaoTipo.ALTERACAO);
			 		Collection<ParametrosTransacaoBatchHelper> colecaoParametrosInclusaoTransacaoBatchHelper = new ArrayList<ParametrosTransacaoBatchHelper>();
			 		
			 		
					ParametrosTransacaoBatchHelper idClienteTransacao = new ParametrosTransacaoBatchHelper(
			 				String.valueOf(cliente.getId()), String.valueOf(cliente.getId()), TabelaColuna.IDCLIENTE_TABELA_CLIENTE);
					colecaoParametrosInclusaoTransacaoBatchHelper.add(idClienteTransacao);
			 		
			 		//transacao RG
			 		if ( cliente.getRg() != null && !cliente.getRg().equals("") ) {
						ParametrosTransacaoBatchHelper RGTransacao = new ParametrosTransacaoBatchHelper(
				 				cliente.getRg(), null, TabelaColuna.RG_TABELA_CLIENTE);
						colecaoParametrosInclusaoTransacaoBatchHelper.add(RGTransacao);
			 		}
			 		
			 		//transacao RG data emissao
					if ( cliente.getDataEmissaoRg()!= null && !cliente.getDataEmissaoRg().equals("") ) {
						ParametrosTransacaoBatchHelper RGTransacao = new ParametrosTransacaoBatchHelper(
				 				Util.formatarData(cliente.getDataEmissaoRg()), null, TabelaColuna.RG_DATAEMISSAO_TABELA_CLIENTE);
						colecaoParametrosInclusaoTransacaoBatchHelper.add(RGTransacao);
			 		}
					
					//transacao unidade federecao
					if ( cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getDescricao() != null && 
							!cliente.getUnidadeFederacao().getDescricao().equals("") ) {
						ParametrosTransacaoBatchHelper unidadeTransacao = new ParametrosTransacaoBatchHelper(
				 				Util.formatarData(cliente.getUnidadeFederacao().getDescricao()), null, TabelaColuna.UNIDADE_FEDERECAO_TABELA_CLIENTE);
						colecaoParametrosInclusaoTransacaoBatchHelper.add(unidadeTransacao);
			 		}
					
					//transacao orgao expedidor
					if ( cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getDescricao() != null && 
							!cliente.getOrgaoExpedidorRg().getDescricao().equals("") ) {
						ParametrosTransacaoBatchHelper unidadeTransacao = new ParametrosTransacaoBatchHelper(
				 				Util.formatarData(cliente.getOrgaoExpedidorRg().getDescricao()), null, TabelaColuna.ORGAO_EXPEDIDOR_TABELA_CLIENTE);
						colecaoParametrosInclusaoTransacaoBatchHelper.add(unidadeTransacao);
			 		}
					
					cliente.setRg(null);
					cliente.setDataEmissaoRg(null);
					cliente.setUnidadeFederacao(null);
					cliente.setOrgaoExpedidorRg(null);

					this.getControladorUtil().atualizar(cliente);

				}
			}
		}
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Mï¿½vel
	 * 
	 * [SB0004] - Atualizar Dados do RG do Cliente Atual
	 * 
	 * @author Mariana Victor
	 * @date 29/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarDadosRGClienteAtual(
			AtualizarClienteAPartirDispositivoMovelHelper helper,
			Cliente clientePesquisa, Collection<TipoAlteracaoTransacaoBatchHelper> colecaoTipoAlteracaoTransacao) 
			throws ControladorException, ErroRepositorioException {

		// 2. O sistema registra a transação de alteração do cliente
		// <<Inclui>> [UC0107 - Registrar Transação],
		// passando a identificação do cliente atualizado (CLIE_ID);
		FiltroCliente filtro = new FiltroCliente();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				clientePesquisa.getId()));
		Collection<Cliente> colecaoCLiente = this.getControladorUtil()
				.pesquisar(filtro, Cliente.class.getName());
		Cliente cliente = (Cliente) Util
				.retonarObjetoDeColecao(colecaoCLiente);
		
		TipoAlteracaoTransacaoBatchHelper tipoAlteracaoInclusaoTransacaoBatchHelper = new TipoAlteracaoTransacaoBatchHelper();
		tipoAlteracaoInclusaoTransacaoBatchHelper.setIdTabela(Tabela.CLIENTE);
		tipoAlteracaoInclusaoTransacaoBatchHelper.setTipoAlteracao(AlteracaoTipo.ALTERACAO);
 		Collection<ParametrosTransacaoBatchHelper> colecaoParametrosTransacaoBatchHelper = new ArrayList<ParametrosTransacaoBatchHelper>();
 		
 		//Armazena o id do cliente
		ParametrosTransacaoBatchHelper idClienteTransacao = new ParametrosTransacaoBatchHelper(
 				String.valueOf(cliente.getId()), String.valueOf(cliente.getId()), TabelaColuna.IDCLIENTE_TABELA_CLIENTE);
		colecaoParametrosTransacaoBatchHelper.add(idClienteTransacao);
		
		//armazena o RG
		if ( (cliente.getRg() == null && helper.getRgNovo() != null) ||
				(cliente.getRg() != null && helper.getRgNovo() == null) ||
				(cliente.getRg() != null && helper.getRgNovo() != null && !cliente.getRg().equals(helper.getRgNovo())
				)){
			ParametrosTransacaoBatchHelper RGTransacao = new ParametrosTransacaoBatchHelper(cliente.getRg() , helper.getRgNovo(), TabelaColuna.RG_TABELA_CLIENTE);
			colecaoParametrosTransacaoBatchHelper.add(RGTransacao);
		}
		
		cliente.setRg(helper.getRgNovo());
		
		//recupera a descricao do orgao expedidor
		String descricao = null;
		if ( cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getDescricao() != null ) {
			descricao = cliente.getOrgaoExpedidorRg().getDescricao();
		}
		//recupera a nova descricao do orgao expedidor
		String descricaoNovoOrgao = null;
		if (helper.getOrgaoExpedidorNovo() != null && !helper.getOrgaoExpedidorNovo().equals("")) {
			OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId(Integer.valueOf(helper.getOrgaoExpedidorNovo()));

			cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			
			FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
			filtroOrgaoExpedidorRg.adicionarParametro( new ParametroSimples(FiltroOrgaoExpedidorRg.ID, Integer.valueOf(helper.getOrgaoExpedidorNovo())));
			Collection colecaoOrgao = getControladorUtil().pesquisar(filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class.getName());
			if ( colecaoOrgao != null && !colecaoOrgao.isEmpty()) {
				OrgaoExpedidorRg expedidorRg = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(colecaoOrgao);
				descricaoNovoOrgao = expedidorRg.getDescricao();
			}
			
			if ( (descricao == null && descricaoNovoOrgao != null) ||
					(descricao != null && descricaoNovoOrgao == null) ||
					(descricao != null && descricaoNovoOrgao != null && !descricao.equals(descricaoNovoOrgao)
					)){
				
				//armazena o orgao expedidor
				ParametrosTransacaoBatchHelper orgaoExpedidorTransacao = new ParametrosTransacaoBatchHelper(
						descricao, descricaoNovoOrgao, TabelaColuna.ORGAO_EXPEDIDOR_TABELA_CLIENTE);
				colecaoParametrosTransacaoBatchHelper.add(orgaoExpedidorTransacao);
			}
				
		}
		
		
		//recupera a descricao da unidade federacao
		String descricaoUnidade = "";
		if ( cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getDescricao() != null ) {
			descricaoUnidade = cliente.getUnidadeFederacao().getDescricao();
		}

		//recupera a nova unidade federacao
		String descricaoNovoUnidade = "";
		if (helper.getUnidadeFederacaoNovo() != null
				&& !helper.getUnidadeFederacaoNovo().equals("")) {
			UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId(Integer.valueOf(helper
					.getUnidadeFederacaoNovo()));
			cliente.setUnidadeFederacao(unidadeFederacao);
			
			FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
			filtroUnidadeFederacao.adicionarParametro( new ParametroSimples(FiltroUnidadeFederacao.ID, Integer.valueOf(helper.getUnidadeFederacaoNovo())));
			Collection colecaoUnidade = getControladorUtil().pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
			if ( colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				UnidadeFederacao unidadeFederecao = (UnidadeFederacao) Util.retonarObjetoDeColecao(colecaoUnidade);
				descricaoNovoUnidade = unidadeFederecao.getDescricao();
			}
			
			if ( (descricaoUnidade == null && descricaoNovoUnidade != null) ||
					(descricaoUnidade != null && descricaoNovoUnidade == null) ||
					(descricaoUnidade != null && descricaoNovoUnidade != null && !descricaoUnidade.equals(descricaoNovoUnidade)
					)){
				
				//armazena a unidade federacao
				ParametrosTransacaoBatchHelper unidadeTransacao = new ParametrosTransacaoBatchHelper(
						descricaoUnidade, descricaoNovoUnidade, TabelaColuna.UNIDADE_FEDERECAO_TABELA_CLIENTE);
				colecaoParametrosTransacaoBatchHelper.add(unidadeTransacao);
			}
			
		}
		
		
		
		if (helper.getRgNovo() == null || helper.getRgNovo().equals("")) {
			cliente.setDataEmissaoRg(null);
		}

		if (helper.getDataEmissaoRG() != null
				&& !helper.getDataEmissaoRG().equals("")) {
			cliente.setDataEmissaoRg(Util.converteStringParaDate(helper
					.getDataEmissaoRG()));
			
			String data = "";
			if ( cliente.getDataEmissaoRg() != null ) {
				data = Util.formatarData(cliente.getDataEmissaoRg());
			}
			
			if ( !data.equals(helper.getDataEmissaoRG()) ) {
				ParametrosTransacaoBatchHelper rgDataTransacao = new ParametrosTransacaoBatchHelper(
						data, helper.getDataEmissaoRG(), TabelaColuna.RG_DATAEMISSAO_TABELA_CLIENTE);
				colecaoParametrosTransacaoBatchHelper.add(rgDataTransacao);
			}
			
		}

		cliente.setUltimaAlteracao(new Date());
		
		if ( colecaoParametrosTransacaoBatchHelper != null && !colecaoParametrosTransacaoBatchHelper.isEmpty() && colecaoParametrosTransacaoBatchHelper.size() > 1) {
			tipoAlteracaoInclusaoTransacaoBatchHelper.setColecaoParametrosTransacao(colecaoParametrosTransacaoBatchHelper);
			colecaoTipoAlteracaoTransacao.add(tipoAlteracaoInclusaoTransacaoBatchHelper);
		}

		// this.repositorioAtendimentoPublico.atualizarDadosRGCliente(idCliente,
		// arqTxtRetClieVisitaCampo.getRg(),
		// arqTxtRetClieVisitaCampo.getOrgaoExpedidorRg().getId(),
		// arqTxtRetClieVisitaCampo.getUnidadeFederacaoRg().getId());
		this.getControladorUtil().atualizar(cliente);

	}

	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Mï¿½vel
	 * 
	 * [SB0003] - Substituir Cliente Usuário do Imóvel
	 * 
	 * @author Mariana Victor
	 * @date 27/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public boolean substituirClienteUsuarioDoImovel(Integer idCliente,
			Integer idClienteAnterior,
			AtualizarClienteAPartirDispositivoMovelHelper helper, 
			Cliente clienteAnterior)
			throws ControladorException {

		boolean imovelSubstituido = true;
		try {

			Integer idImovel = Integer.valueOf(helper.getMatricula());

			// 1. O sistema efetua a substituiï¿½ï¿½o do cliente usuário do
			// imóvel

			// [FS0006] - Verificar existência de negativação para o
			// cliente-imóvel
			// Caso o cliente do imóvel esteja em processo de negativação
			if (idClienteAnterior != null) {
				Integer qtdOcorrenciaNegativacao = this.repositorioCliente
						.pesquisarExistenciaNegativacaoParaClienteImovel(
								idClienteAnterior, idImovel);
				if (qtdOcorrenciaNegativacao != null
						&& qtdOcorrenciaNegativacao > 0) {

					// caso registre transacao, funcionalidade online
					throw new ControladorException(
							"atencao.cliente_imovel.processo_negativacao");
				}
			}

			// CLIM_ICNOMECONTA do cliente usuário do imóvel desvinculado acima.
			Short icNomeContaAnterior = ConstantesSistema.SIM;
			if (idClienteAnterior != null) {
				icNomeContaAnterior = this.repositorioClienteImovel.pesquisarIndicadorNomeConta(
						idClienteAnterior, idImovel);
			}

			// 1.1. Desvincula o cliente usuário atual do imóvel
			// 1.1.1. Atualiza a tabela de relacionamento entre cliente e imóvel
			// de modo a encerrar este relacionamento
			Date dataFim = new Date();
			
			this.repositorioClienteImovel.desvinculaClienteUsuarioAtualDoImovel(idImovel, dataFim);
	 		
			// 1.2. Vincula o novo cliente usuário atual do imóvel
			// 1.2.1. Insere nova linha na tabela de relacionamento entre
			// cliente e imóvel, conforme a seguir;
			ClienteImovel clienteImovel = new ClienteImovel();

			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					idCliente));
			Collection<Cliente> colecaoCLiente = this.getControladorUtil()
					.pesquisar(filtro, Cliente.class.getName());
			Cliente cliente = (Cliente) Util
					.retonarObjetoDeColecao(colecaoCLiente);

			Imovel imovel = new Imovel();
			imovel.setId(idImovel);
			ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
			clienteRelacaoTipo.setId(new Integer(ClienteRelacaoTipo.USUARIO));

			// CLIE_ID do novo cliente pesquisado ou inserido,
			clienteImovel.setCliente(cliente);

			// IMOV_ID da tabela ORDEM_SERVICO com ORSE_ID=ORSE_ID informado
			// como parï¿½metro.
			clienteImovel.setImovel(imovel);
			Date dataInicio = new Date();
			clienteImovel.setDataInicioRelacao(dataInicio);

			// CRTP_ID da tabela CLIENTE_RELACAO_TIPO correspondente a
			// "USUARIO".
			clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

			clienteImovel.setIndicadorNomeConta(icNomeContaAnterior);

			clienteImovel.setUltimaAlteracao(new Date());
			
			this.getControladorUtil().inserir(clienteImovel);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
			throw new ControladorException("erro.sistema", e);
		}

		return imovelSubstituido;
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Mï¿½vel
	 * 
	 * [SB0002] - Inserir Novo Cliente
	 * 
	 * @author Mariana Victor
	 * @date 27/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Integer inserirNovoCliente(
			AtualizarClienteAPartirDispositivoMovelHelper helper,
			Cliente clienteAnterior, Collection<TipoAlteracaoTransacaoBatchHelper> colecaoTipoAlteracaoHelper) throws ControladorException {

		Integer codigoCliente = null;

		// 1. O sistema efetua a inserï¿½ï¿½o de um novo cliente <<Inclui>>
		// [UC0007 - Inserir Cliente]:
		// passando os dados oriundos do cliente anterior e mais as atualizaï¿½ï¿½es informadas a partir do dispositivo móvel

		// 1.1. Tabela CLIENTE:
		Cliente cliente = new Cliente();

		// ATCV_NMCLIENTE
		if ( helper.getNomeNovo().length() > 49 ) { 
			cliente.setNome(helper.getNomeNovo().substring(0, 49));
		} else {
			cliente.setNome(helper.getNomeNovo());
		}

		// ATCV_NNCPF
		cliente.setCpf(helper.getCpfNovo());

		// ATCV_NNRG
		cliente.setRg(helper.getRgNovo());

		// OERG_IDRG da tabela ARQ_TXT_RET_CLIE_VISCAMPO
		if (helper.getOrgaoExpedidorNovo() != null && !helper.getOrgaoExpedidorNovo().equals("")) {
			OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId(Integer.valueOf(helper.getOrgaoExpedidorNovo()));
			cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
		}

		// UNFE_IDRG da tabela ARQ_TXT_RET_CLIE_VISCAMPO
		if (helper.getUnidadeFederacaoNovo() != null && !helper.getUnidadeFederacaoNovo().equals("")) {
			UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId(Integer.valueOf(helper.getUnidadeFederacaoNovo()));
			cliente.setUnidadeFederacao(unidadeFederacao);
		}

		if (helper.getRgNovo() == null || helper.getRgNovo().trim().equals("")) {
			cliente.setDataEmissaoRg(null);
		}

		// ATCV_NNCNPJ
		cliente.setCnpj(helper.getCnpjNovo());

		// 1 correspondente a "Ativo"
		cliente.setIndicadorUso(ConstantesSistema.SIM);

		// Data Emissao
		if (helper.getDataEmissaoRG() != null && !helper.getDataEmissaoRG().equals("")) {
			cliente.setDataEmissaoRg(Util.converteStringParaDate(helper.getDataEmissaoRG()));

		}

		// Data Nascimento
		if (helper.getDataNascimento() != null && !helper.getDataNascimento().equals("")) {
			cliente.setDataNascimento(Util.converteStringParaDate(helper.getDataNascimento()));
		}

		// Pessoa sexo
		if (helper.getIdPessoaSexo() != null && !helper.getIdPessoaSexo().equals("")) {
			PessoaSexo pessoaSexo = new PessoaSexo();
			pessoaSexo.setId(Integer.valueOf(helper.getIdPessoaSexo()));
			cliente.setPessoaSexo(pessoaSexo);
		}

		if (clienteAnterior != null) {

			// CLTP_ID anterior ou do arquivo
			cliente.setClienteTipo(clienteAnterior.getClienteTipo());
			// CLIE_ICCOBRANCAACRESCIMOS anterior
			cliente.setIndicadorAcrescimos(clienteAnterior.getIndicadorAcrescimos());
			// CLIE_ICGERAARQUIVOTEXTO anterior
			cliente.setIndicadorGeraArquivoTexto(clienteAnterior.getIndicadorGeraArquivoTexto());
			// CLIE_ICVENCIMENTOMESSEGUINTE anterior
			cliente.setIndicadorVencimentoMesSeguinte(clienteAnterior.getIndicadorVencimentoMesSeguinte());
			// CLIE_ICGERAFATURAANTECIPADA anterior
			cliente.setIndicadorGeraFaturaAntecipada(clienteAnterior.getIndicadorGeraFaturaAntecipada());
			// CLIE_ICUSONOMEFANTASIACONTA anterior
			cliente.setIndicadorUsoNomeFantasiaConta(clienteAnterior.getIndicadorUsoNomeFantasiaConta());
			// CLIE_ICPERMITENEGATIVACAO anterior
			cliente.setIndicadorPermiteNegativacao(clienteAnterior.getIndicadorPermiteNegativacao());

			cliente.setIndicadorNegativacaoPeriodo(clienteAnterior.getIndicadorNegativacaoPeriodo());
			
		} else {
			if (helper.getIdClienteTipo() != null) {
				ClienteTipo clienteTipo = new ClienteTipo();
				clienteTipo.setId(Integer.valueOf(helper.getIdClienteTipo()));
				cliente.setClienteTipo(clienteTipo);
			} else {
				ClienteTipo clienteTipo = new ClienteTipo();
				clienteTipo.setId(Integer.valueOf(helper.getIdClienteTipo()));
				cliente.setClienteTipo(clienteTipo);
			}

			cliente.setIndicadorAcrescimos(ConstantesSistema.SIM);
			cliente.setIndicadorGeraArquivoTexto(ConstantesSistema.NAO);
			cliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
			cliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
			cliente.setIndicadorUsoNomeFantasiaConta(ConstantesSistema.NAO);
			cliente.setIndicadorPermiteNegativacao(ConstantesSistema.SIM);
			cliente.setIndicadorNegativacaoPeriodo(ConstantesSistema.NAO);
			
		}

		cliente.setUltimaAlteracao(new Date());

		Collection<AtualizarClienteFoneAPartirDispositivoMovelHelper> colecaoClienteFoneHelper = helper.getClienteFoneHelper();
		Collection<ClienteFone> colecaoFones = new ArrayList();
		if (colecaoClienteFoneHelper != null && !colecaoClienteFoneHelper.isEmpty()) {

			Iterator<AtualizarClienteFoneAPartirDispositivoMovelHelper> iteratorClienteFoneHelper = colecaoClienteFoneHelper.iterator();
			while (iteratorClienteFoneHelper.hasNext()) {

				AtualizarClienteFoneAPartirDispositivoMovelHelper helperClienteFone = (AtualizarClienteFoneAPartirDispositivoMovelHelper) iteratorClienteFoneHelper.next();

				ClienteFone clienteFone = new ClienteFone();

				// ATCF_CDDDD
				if (helperClienteFone.getDddFone() != null) {
					clienteFone.setDdd(helperClienteFone.getDddFone());
				}

				// ATCF_NNFONE
				if (helperClienteFone.getNumeroFone() != null) {
					clienteFone.setTelefone(helperClienteFone.getNumeroFone());
				}

				// ARCF_NNFONERAMAL
				if (helperClienteFone.getRamalFone() != null) {
					clienteFone.setRamal(helperClienteFone.getRamalFone());
				}

				// 1 correspondente a "Fone Padrao"
				clienteFone.setIndicadorTelefonePadrao(ConstantesSistema.SIM);

				// FNET_ID da tabela ARQ_TXT_RET_FONE_VISCAMPO
				FoneTipo foneTipo = new FoneTipo();
				foneTipo.setId(FoneTipo.RESIDENCIAL);
				clienteFone.setFoneTipo(foneTipo);

				// Data e hora correntes.
				clienteFone.setUltimaAlteracao(new Date());

				colecaoFones.add(clienteFone);
			}
		}

		// 1.3. Tabela CLIENTE_ENDERECO:
		Collection<ClienteEndereco> colecaoEnderecos = new ArrayList<ClienteEndereco>();
		ClienteEndereco clienteEndereco = new ClienteEndereco();

		if (clienteAnterior != null) {
			ClienteEndereco clienteEnderecoAnterior = this.getControladorArrecadacao().pesquisarClienteEnderecoPagamento(clienteAnterior.getId());
			if (clienteEnderecoAnterior != null) {
				// EDTP_ID anterior
				clienteEndereco.setEnderecoTipo(clienteEnderecoAnterior.getEnderecoTipo());
				// EDRF_ID anterior
				clienteEndereco.setEnderecoReferencia(clienteEnderecoAnterior.getEnderecoReferencia());
				// CLED_NNIMOVEL anterior
				clienteEndereco.setNumero(clienteEnderecoAnterior.getNumero());
				// CLED_DSCOMPLEMENTOENDERECO anterior
				clienteEndereco.setComplemento(clienteEnderecoAnterior.getComplemento());
				// CLED_ICENDERECOCORRESPONDENCIA anterior
				clienteEndereco.setIndicadorEnderecoCorrespondencia(clienteEnderecoAnterior.getIndicadorEnderecoCorrespondencia());
				// Data e hora correntes.
				clienteEndereco.setUltimaAlteracao(new Date());
				// LGBR_ID anterior
				clienteEndereco.setLogradouroBairro(clienteEnderecoAnterior.getLogradouroBairro());
				// LGCP_ID anterior
				clienteEndereco.setLogradouroCep(clienteEnderecoAnterior.getLogradouroCep());

				colecaoEnderecos.add(clienteEndereco);

			}
		} else {
			// EDTP_ID anterior
			EnderecoTipo enderecoTipo = new EnderecoTipo();
			if (helper.getCpfNovo() != null	&& Util.validacaoCPF(helper.getCpfNovo())) {
				enderecoTipo.setId(1);
				clienteEndereco.setEnderecoTipo(enderecoTipo);
			} else {
				enderecoTipo.setId(2);
				clienteEndereco.setEnderecoTipo(enderecoTipo);
			}

			Imovel imovel = getControladorImovel().pesquisarImovel(
					Integer.valueOf(helper.getMatricula()));

			// EDRF_ID anterior
			clienteEndereco.setEnderecoReferencia(imovel.getEnderecoReferencia());
			// CLED_NNIMOVEL anterior
			clienteEndereco.setNumero(imovel.getNumeroImovel());
			// CLED_DSCOMPLEMENTOENDERECO anterior
			clienteEndereco.setComplemento(imovel.getComplementoEndereco());
			// CLED_ICENDERECOCORRESPONDENCIA anterior
			clienteEndereco.setIndicadorEnderecoCorrespondencia(ConstantesSistema.SIM);
			// Data e hora correntes.
			clienteEndereco.setUltimaAlteracao(new Date());
			// LGBR_ID anterior
			clienteEndereco.setLogradouroBairro(imovel.getLogradouroBairro());
			// LGCP_ID anterior
			clienteEndereco.setLogradouroCep(imovel.getLogradouroCep());

			colecaoEnderecos.add(clienteEndereco);
		}

		boolean ehAtuCadastral = false;
		if(helper.getImovelAtualizacaoCadastral() != null && helper.getImovelAtualizacaoCadastral().getId() != null){
			ehAtuCadastral = true;
		}
		
		cliente.setIndicadorAcaoCobranca(ConstantesSistema.INDICADOR_USO_ATIVO);
		cliente.setIndicadorValidaCpfCnpj(2);
		cliente.setIndicadorEnviarEmail(ConstantesSistema.NAO);
		cliente.setIndicadorEnviarSms(ConstantesSistema.NAO);
		
		codigoCliente = this.inserirCliente(cliente,colecaoFones, colecaoEnderecos, Usuario.USUARIO_BATCH,
				Operacao.OPERACAO_CONSULTAR_DADOS_DA_OS_DE_VISITA,ehAtuCadastral);

		if ( codigoCliente != null ) {
			//Cria o tipo de alteracao - Inclusao do cliente 
			TipoAlteracaoTransacaoBatchHelper tipoAlteracaoClienteTransacaoBatchHelper = new TipoAlteracaoTransacaoBatchHelper();
			tipoAlteracaoClienteTransacaoBatchHelper.setIdTabela(Tabela.CLIENTE);
			tipoAlteracaoClienteTransacaoBatchHelper.setTipoAlteracao(AlteracaoTipo.INCLUSAO);
	 		Collection<ParametrosTransacaoBatchHelper> colecaoParametrosTransacaoBatchHelper = new ArrayList<ParametrosTransacaoBatchHelper>();
	 		
			ParametrosTransacaoBatchHelper idClienteTransacao = new ParametrosTransacaoBatchHelper(
	 				null, String.valueOf(codigoCliente), TabelaColuna.IDCLIENTE_TABELA_CLIENTE);
			colecaoParametrosTransacaoBatchHelper.add(idClienteTransacao);
			
			ParametrosTransacaoBatchHelper nomeClienteTransacao = new ParametrosTransacaoBatchHelper(
	 				null, cliente.getNome(), TabelaColuna.NOME_CLIENTE_TABELA_CLIENTE);
			colecaoParametrosTransacaoBatchHelper.add(nomeClienteTransacao);

			if ( cliente.getCpf() != null && !cliente.getCpf().equals("") ) {
				ParametrosTransacaoBatchHelper cpfClienteTransacao = new ParametrosTransacaoBatchHelper(
		 				null, cliente.getCpf(), TabelaColuna.CPF_CLIENTE_TABELA_CLIENTE);
				colecaoParametrosTransacaoBatchHelper.add(cpfClienteTransacao);
			} else {
				ParametrosTransacaoBatchHelper cpfClienteTransacao = new ParametrosTransacaoBatchHelper(
		 				null, cliente.getCnpj(), TabelaColuna.CNPJ_CLIENTE_TABELA_CLIENTE);
				colecaoParametrosTransacaoBatchHelper.add(cpfClienteTransacao);
			}
			
			if ( cliente.getRg() != null && !cliente.getRg().equals("") ) {
				ParametrosTransacaoBatchHelper rgClienteTransacao = new ParametrosTransacaoBatchHelper(
		 				null, cliente.getRg(), TabelaColuna.RG_TABELA_CLIENTE);
				colecaoParametrosTransacaoBatchHelper.add(rgClienteTransacao);
			}
			
			//recupera a nova descricao do orgao expedidor
			if ( cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getId() != null ) {
				
				FiltroOrgaoExpedidorRg filtroOrgaoExpedidorRg = new FiltroOrgaoExpedidorRg();
				filtroOrgaoExpedidorRg.adicionarParametro( new ParametroSimples(FiltroOrgaoExpedidorRg.ID, cliente.getOrgaoExpedidorRg().getId() ));
				Collection colecaoOrgao = getControladorUtil().pesquisar(filtroOrgaoExpedidorRg, OrgaoExpedidorRg.class.getName());
				if ( colecaoOrgao != null && !colecaoOrgao.isEmpty()) {
					OrgaoExpedidorRg expedidorRg = (OrgaoExpedidorRg) Util.retonarObjetoDeColecao(colecaoOrgao);
						
					//armazena o orgao expedidor
					ParametrosTransacaoBatchHelper orgaoExpedidorTransacao = new ParametrosTransacaoBatchHelper(
							null, expedidorRg.getDescricao(), TabelaColuna.ORGAO_EXPEDIDOR_TABELA_CLIENTE);
					colecaoParametrosTransacaoBatchHelper.add(orgaoExpedidorTransacao);
				}
			}

			//recupera a nova unidade federacao
			if ( cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getId() != null ) {
				
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				filtroUnidadeFederacao.adicionarParametro( new ParametroSimples(FiltroUnidadeFederacao.ID, cliente.getUnidadeFederacao().getId()));
				Collection colecaoUnidade = getControladorUtil().pesquisar(filtroUnidadeFederacao, UnidadeFederacao.class.getName());
				if ( colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
					UnidadeFederacao unidadeFederecao = (UnidadeFederacao) Util.retonarObjetoDeColecao(colecaoUnidade);
						
					//armazena a unidade federacao
					ParametrosTransacaoBatchHelper unidadeTransacao = new ParametrosTransacaoBatchHelper(
							null, unidadeFederecao.getDescricao(), TabelaColuna.UNIDADE_FEDERECAO_TABELA_CLIENTE);
					colecaoParametrosTransacaoBatchHelper.add(unidadeTransacao);
				}
			}
			
			
			
			tipoAlteracaoClienteTransacaoBatchHelper.setColecaoParametrosTransacao(colecaoParametrosTransacaoBatchHelper);
			colecaoTipoAlteracaoHelper.add(tipoAlteracaoClienteTransacaoBatchHelper);
			
		}
		
		return codigoCliente;
	}
	
	/**
	 * Insere um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser inserido
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @return Descrição do retorno
	 * @throws ControladorException
	 */
	public Integer inserirCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario, Integer idOperacao,boolean ehAtuCadastral) throws ControladorException {
		FiltroCliente filtroCliente = new FiltroCliente();

//		// ------------ REGISTRAR TRANSAÇÃO ----------------
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				idOperacao, cliente.getId(), cliente.getId(), 
//				new UsuarioAcaoUsuarioHelper(usuario,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//		// ------------ REGISTRAR TRANSAÇÃO ----------------


		// Validar CPF de cliente
		if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cliente.getCpf()));

			Collection clienteComCpfExistente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			if (!clienteComCpfExistente.isEmpty()) {
				Cliente clienteComCpf = (Cliente) clienteComCpfExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.cpf.cliente.ja_cadastrado", null, ""
								+ clienteComCpf.getId());

			}
		}

		// Validar CNPJ de cliente
		if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
			filtroCliente.limparListaParametros();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cliente.getCnpj()));

			Collection clienteComCnpjExistente = getControladorUtil()
					.pesquisar(filtroCliente, Cliente.class.getName());

			if (!clienteComCnpjExistente.isEmpty()) {
				Cliente clienteComCnpj = (Cliente) clienteComCnpjExistente
						.iterator().next();
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.cnpj.cliente.ja_cadastrado", null, ""
								+ clienteComCnpj.getId());
			}
		}

		// Validar RG de cliente
		if (cliente.getRg() != null && !cliente.getRg().equals("")&& 
				cliente.getOrgaoExpedidorRg() != null && !cliente.getOrgaoExpedidorRg().equals("") ) {
			
			filtroCliente.limparListaParametros();
			
              filtroCliente.adicionarParametro(new ParametroSimples(
                                  FiltroCliente.RG, cliente.getRg()));
          
	          if(cliente.getOrgaoExpedidorRg() != null){
	                  filtroCliente.adicionarParametro(new ParametroSimples(
	                                  FiltroCliente.ORGAO_EXPEDIDOR_RG_ID, cliente
	                                                  .getOrgaoExpedidorRg().getId()));
	          }
	          
	          if(cliente.getUnidadeFederacao() != null){
	                  filtroCliente.adicionarParametro(new ParametroSimples(
	                                  FiltroCliente.UNIDADE_FEDERACAO_ID, cliente
	                                                  .getUnidadeFederacao().getId()));
	          }

			Collection clienteComRgExistente = getControladorUtil().pesquisar(
					filtroCliente, Cliente.class.getName());

			if (!clienteComRgExistente.isEmpty()) {
				
				if(ehAtuCadastral){
					cliente.setRg(null);
					cliente.setOrgaoExpedidorRg(null);
					cliente.setUnidadeFederacao(null);
				}else{
					Cliente clienteComRg = (Cliente) clienteComRgExistente.iterator().next();
					sessionContext.setRollbackOnly();
					throw new ControladorException("atencao.rg.cliente.ja_cadastrado", null, ""	+ clienteComRg.getId());
				}
			}
		}

		// -------------Parte que insere um cliente na
		// base----------------------
		cliente.setIndicadorGeraArquivoTexto(new Short("2"));
		
		//*************************************************************************
		// Autor: Ivan Sergio
		// Data: 06/08/2009
		// CRC2103
		// Caso em que o Inserir Cliente eh chamdo como PopUp pelo Manter Imovel 
		//*************************************************************************
//		AtributoGrupo atributoGrupo = null;
//		if (cliente.getOperacaoEfetuada() != null) {
//			if (cliente.getOperacaoEfetuada().getAtributoGrupo() != null) {
//				atributoGrupo = cliente.getOperacaoEfetuada().getAtributoGrupo();
//			}
//		}

//		registradorOperacao.registrarOperacao(cliente);
		
//		if (atributoGrupo != null) {
//			cliente.getOperacaoEfetuada().setAtributoGrupo(atributoGrupo);
//		}
		//*************************************************************************
		
//		cliente.setClienteFones(new HashSet(telefones));
//		cliente.setClienteEnderecos(new HashSet(enderecos));	
		
		Integer chaveClienteGerada = null;
		try{
			chaveClienteGerada = (Integer) getControladorUtil().inserir(cliente);			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ControladorException(e.getMessage());
		}

		cliente.setId(chaveClienteGerada);

		if (telefones != null) {

			// Inserir os fones do cliente
			Iterator iteratorTelefones = telefones.iterator();

			while (iteratorTelefones.hasNext()) {
				ClienteFone clienteFone = (ClienteFone) iteratorTelefones
						.next();

				clienteFone.setCliente(cliente);
//				registradorOperacao.registrarOperacao(clienteFone);
				getControladorUtil().inserir(clienteFone);

			}
		}

		// Inserir os endereços do cliente
		Iterator iteratorEnderecos = enderecos.iterator();

		while (iteratorEnderecos.hasNext()) {
			ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
					.next();

			clienteEndereco.setCliente(cliente);
//			registradorOperacao.registrarOperacao(clienteEndereco);
			getControladorUtil().inserir(clienteEndereco);

		}

		return chaveClienteGerada;
		/*
		 * } catch (ErroRepositorioException ex) {
		 * sessionContext.setRollbackOnly(); throw new
		 * ControladorException("erro.sistema", ex); }
		 */
		// -------------Fim da parte que insere um cliente na
		// base---------------
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo Mï¿½vel
	 * 
	 * [SB0005] - Atualizar Dados do Fone do Cliente Atual
	 * 
	 * @author Mariana Victor
	 * @date 29/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public void atualizarDadosFoneClienteAtual(
			AtualizarClienteFoneAPartirDispositivoMovelHelper helper,
			Integer idCliente, Usuario usuarioLogado)
			throws ControladorException {

		ClienteFone clienteFone = new ClienteFone();

		FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
		filtroClienteFone.adicionarParametro(new ParametroSimples(
				FiltroClienteFone.CLIENTE_ID, idCliente));
		filtroClienteFone.adicionarParametro(new ParametroSimples(
				FiltroClienteFone.FONE_TIPO, FoneTipo.RESIDENCIAL));
		filtroClienteFone
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteFone.CLIENTE);
		Collection<ClienteFone> colecaoClienteFone = this.getControladorUtil()
				.pesquisar(filtroClienteFone, ClienteFone.class.getName());

		if (colecaoClienteFone != null && !colecaoClienteFone.isEmpty()) {
			clienteFone = (ClienteFone) Util
					.retonarObjetoDeColecao(colecaoClienteFone);
		}

		filtroClienteFone = new FiltroClienteFone();
		filtroClienteFone.adicionarParametro(new ParametroSimples(
				FiltroClienteFone.CLIENTE_ID, idCliente));
		filtroClienteFone
				.adicionarParametro(new ParametroSimples(
						FiltroClienteFone.INDICADOR_FONE_PADRAO,
						ConstantesSistema.SIM));
		filtroClienteFone
				.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteFone.CLIENTE);
		Collection<ClienteFone> colecaoClienteFonePadrao = this
				.getControladorUtil().pesquisar(filtroClienteFone,
						ClienteFone.class.getName());
		if (colecaoClienteFonePadrao != null
				&& !colecaoClienteFonePadrao.isEmpty()) {
			ClienteFone clienteFonePadrao = (ClienteFone) Util
					.retonarObjetoDeColecao(colecaoClienteFonePadrao);

			if (clienteFonePadrao.getIndicadorTelefonePadrao() != null
					&& clienteFonePadrao.getIndicadorTelefonePadrao()
							.compareTo(ConstantesSistema.SIM) == 0) {
				clienteFonePadrao
						.setIndicadorTelefonePadrao(ConstantesSistema.NAO);
				clienteFonePadrao.setUltimaAlteracao(new Date());

				// ------------ REGISTRAR TRANSAÇÃO ----------------
//				RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//						Operacao.OPERACAO_CONSULTAR_DADOS_DA_OS_DE_VISITA,
//						idCliente, idCliente, new UsuarioAcaoUsuarioHelper(
//								usuarioLogado,
//								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				// ------------ REGISTRAR TRANSAÇÃO ----------------

//				registradorOperacao.registrarOperacao(clienteFonePadrao);

				this.getControladorUtil().atualizar(clienteFonePadrao);
			}
		}

		Cliente cliente = new Cliente();
		cliente.setId(idCliente);

		if (clienteFone.getId() == null) {
			FoneTipo foneTipo = new FoneTipo();
			foneTipo.setId(FoneTipo.RESIDENCIAL);
			clienteFone.setFoneTipo(foneTipo);
		}

		clienteFone.setCliente(cliente);
		clienteFone.setIndicadorTelefonePadrao(ConstantesSistema.SIM);
		clienteFone.setDdd(helper.getDddFone());
		clienteFone.setRamal(helper.getRamalFone());
		clienteFone.setTelefone(helper.getNumeroFone());
		clienteFone.setUltimaAlteracao(new Date());

		// 1. O sistema efetua a alteração dos dados do Fone do cliente usuário
		// atual
		// 2. O sistema registra a transação de alteração do cliente <<Inclui>>
		// [UC0107 - Registrar Transação],
		// passando a identificação do cliente atualizado (CLIE_ID)
		// ------------ REGISTRAR TRANSAÇÃO ----------------
//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_CONSULTAR_DADOS_DA_OS_DE_VISITA, idCliente,
//				idCliente, new UsuarioAcaoUsuarioHelper(usuarioLogado,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		// ------------ REGISTRAR TRANSAÇÃO ----------------

//		registradorOperacao.registrarOperacao(clienteFone);

		this.getControladorUtil().inserirOuAtualizar(clienteFone);

	}


	/**
	 * [UC1297] - Atualizar Dados Cadastrais Para Imóveis Inconsistentes
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosClienteFoneAtualizacaoCadastral
		(Integer idClienteAtualizacaoCadastral) throws ControladorException {
			try{			
				return repositorioCliente.obterDadosClienteFoneAtualizacaoCadastral(idClienteAtualizacaoCadastral);
				
			} catch (ErroRepositorioException ex) {
		        throw new ControladorException("erro.sistema", ex);
		    }
	}
	
	/**
	 * [UC1299] Atualizar Cliente para Atualização Cadastral
	 * 
	 * @author Arthur Carvalho, Bruno Barreto
	 * 
	 * @param imovelAtualizacaoCadastral
	 * @param clienteAtualizacaoCadastral
	 * @param clienteImovel
	 * @throws ControladorException
	 */
	public boolean atualizarClienteAtualizacaoCadastral(ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral, ClienteAtualizacaoCadastralDM clienteAtualizacaoCadastral, 
			ClienteImovel clienteImovel, Integer idParametroTabelaAtualizacaoCadastro, boolean funcionalidadeOnline, Imovel imovel) throws ControladorException {
		
		//mota o helper com os parametros necessarios para atualizar o cliente.
		AtualizarClienteAPartirDispositivoMovelHelper helper = this.montarAtualizarClienteAPartirDispositivoMovelHelper(imovel.getId(),clienteAtualizacaoCadastral, clienteImovel, Operacao.OPERACAO_ATUALIZAR_CLIENTE_ATUALIZACAO_CADASTRAL_BATCH);
		
		ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastro = new ParametroTabelaAtualizacaoCadastralDM();
		parametroTabelaAtualizacaoCadastro.setId(idParametroTabelaAtualizacaoCadastro);
		
		imovelAtualizacaoCadastral.setParametroTabelaAtualizacaoCadastralDM(parametroTabelaAtualizacaoCadastro);
		helper.setImovelAtualizacaoCadastral(imovelAtualizacaoCadastral);
		
		//atualiza os dados do cliente.
		helper = this.atualizarClienteAPartirDoDispositivoMovel(helper);

		return helper.isClienteAtualizar();
	}
	
	/**
	 * Metodo responsavel por montar o helper com os dados do cliente atualizado
	 * pelo dispositivo movel.
	 * 
	 * @author Bruno Sá Barreto
	 * @date 10/10/2014
	 */
	public AtualizarClienteAPartirDispositivoMovelHelper montarAtualizarClienteAPartirDispositivoMovelHelper(
			Integer idImovel,
			ClienteAtualizacaoCadastralDM clienteAtualizacaoCadastral,
			ClienteImovel clienteImovel, Integer tipoOperacao) throws ControladorException {
		
		// montar o helper para atualizar o cliente caso necessario.
		AtualizarClienteAPartirDispositivoMovelHelper helper = new AtualizarClienteAPartirDispositivoMovelHelper();
		helper.setTipoOperacao(tipoOperacao);

		if (clienteAtualizacaoCadastral != null && clienteAtualizacaoCadastral.getId() != null) {

			// MATRICULA
			helper.setMatricula(String.valueOf(idImovel));

			if (clienteAtualizacaoCadastral.getClienteTipo().getId() != null) {
				helper.setIdClienteTipo(String.valueOf(clienteAtualizacaoCadastral.getClienteTipo().getId()));
			}

			// Nome
			helper.setNomeNovo(clienteAtualizacaoCadastral.getNomeCliente());

			// CPF
			if (clienteAtualizacaoCadastral.getCpfCnpj() != null
					&& Util.validacaoCPF(clienteAtualizacaoCadastral.getCpfCnpj())) {
				helper.setCpfNovo(clienteAtualizacaoCadastral.getCpfCnpj());
			}

			// CNPJ
			if (clienteAtualizacaoCadastral.getCpfCnpj() != null
					&& Util.validacaoCNPJ(clienteAtualizacaoCadastral.getCpfCnpj())) {
				helper.setCnpjNovo(clienteAtualizacaoCadastral.getCpfCnpj());
			}

			// UNIDADE FEDERACAO
			if (clienteAtualizacaoCadastral.getUnidadeFederacao() != null) {
				helper.setUnidadeFederacaoNovo(String.valueOf(clienteAtualizacaoCadastral.getUnidadeFederacao().getId()));
			}

			// RG
			helper.setRgNovo(clienteAtualizacaoCadastral.getRg());

			// ORGAO EXPEDIDOR
			if (clienteAtualizacaoCadastral.getOrgaoExpedidorRG() != null) {
				helper.setOrgaoExpedidorNovo(clienteAtualizacaoCadastral.getOrgaoExpedidorRG().getId().toString());
			}

			// Data Emissao
			if (clienteAtualizacaoCadastral.getDataEmissaoRg() != null) {
				helper.setDataEmissaoRG(Util.formatarData(clienteAtualizacaoCadastral.getDataEmissaoRg()));
			}

			// Data Nascimento
			if (clienteAtualizacaoCadastral.getDataNascimento() != null) {
				helper.setDataNascimento(Util.formatarData(clienteAtualizacaoCadastral.getDataNascimento()));
			}

			// Pessoa sexo
			if (clienteAtualizacaoCadastral.getSexo() != null) {
				helper.setIdPessoaSexo(String.valueOf(clienteAtualizacaoCadastral.getSexo().getId()));
			}

			//indicadores retornados de campo
			if(clienteAtualizacaoCadastral.getIndicadorDocumentacao() != null){
				helper.setIndicadorDocumentacao(clienteAtualizacaoCadastral.getIndicadorDocumentacao());
			}
			if(clienteAtualizacaoCadastral.getIndicadorResponsavel() != null){
				helper.setIndicadorResponsavel(clienteAtualizacaoCadastral.getIndicadorResponsavel());
			}
			if(clienteAtualizacaoCadastral.getIndicadorProprietario() != null){
				helper.setIndicadorProprietario(clienteAtualizacaoCadastral.getIndicadorProprietario());
			}
			
			if (clienteImovel != null && clienteImovel.getId() != null) {
				// nome
				helper.setNomeAnterior(clienteImovel.getCliente().getNome());

				// cpf
				if (clienteImovel.getCliente().getCpf() != null
						&& Util.validacaoCPF(clienteImovel.getCliente().getCpf())) {
					helper.setCpfAnterior(clienteImovel.getCliente().getCpf());
				}

				if (clienteImovel.getCliente().getCnpj() != null
						&& Util.validacaoCNPJ(clienteImovel.getCliente().getCnpj())) {
					helper.setCnpjAnterior(clienteImovel.getCliente().getCnpj());
				}

				helper.setRgAnterior(clienteImovel.getCliente().getRg());

				if (clienteImovel.getCliente().getUnidadeFederacao() != null
						&& clienteImovel.getCliente().getUnidadeFederacao().getId() != null) {
					helper.setUnidadeFederacaoAnterior(clienteImovel
							.getCliente().getUnidadeFederacao().getId().toString());
				}

				if (clienteImovel.getCliente().getOrgaoExpedidorRg() != null
						&& clienteImovel.getCliente().getOrgaoExpedidorRg().getId() != null) {
					helper.setOrgaoExpedidorAnterior(clienteImovel.getCliente()
							.getOrgaoExpedidorRg().getId().toString());
				}

				// ID CLIENTE ANTERIOR
				helper.setIdClienteAnterior(clienteImovel.getCliente().getId().toString());

			}

			// Responsável por atualizar somente o telefone com o indicador
			// principal = false ( dispositivos moveis que so trabalham com um campo de telefone)
			// Caso o valor seja igual a true, remove todos os telefones
			// atual do cliente e cadastra os que retornaram de campo (
			// dispositivos moveis que trabalham com todos os telefones do cliente)
			helper.setRemoverTelefone(true);

			helper.setIdClienteAtualizacaoCadastral(clienteAtualizacaoCadastral.getId());

			helper.setIndicadorRegistrarTransacao(false);
		}
		return helper;
	}
	
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
	public String retornaCPFCNPJCliente(Integer idCliente)throws ControladorException {
		try{			
			Object[] dadosCPFCNPJ =  repositorioCliente.pesquisarCPFCNPJCliente(idCliente);
			String retorno = null;
			
			if(dadosCPFCNPJ != null){
				if(dadosCPFCNPJ[0] != null){
					retorno = (String)dadosCPFCNPJ[0];
				}else if(dadosCPFCNPJ[1] != null){
					retorno = (String)dadosCPFCNPJ[1];
				}
			}
			
			return retorno;
			
		} catch (ErroRepositorioException ex) {
	        throw new ControladorException("erro.sistema", ex);
	    }
	}
	
	
	
}
