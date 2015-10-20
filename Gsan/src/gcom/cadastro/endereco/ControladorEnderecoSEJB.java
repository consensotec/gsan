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
package gcom.cadastro.endereco;

import gcom.arrecadacao.ControladorArrecadacaoLocal;
import gcom.arrecadacao.ControladorArrecadacaoLocalHome;
import gcom.arrecadacao.banco.Agencia;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocal;
import gcom.atendimentopublico.registroatendimento.ControladorRegistroAtendimentoLocalHome;
import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ControladorClienteLocal;
import gcom.cadastro.cliente.ControladorClienteLocalHome;
import gcom.cadastro.endereco.bean.AtualizarLogradouroBairroHelper;
import gcom.cadastro.endereco.bean.AtualizarLogradouroCepHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroImovelEnderecoAnterior;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEnderecoAnterior;
import gcom.cadastro.localidade.ControladorLocalidadeLocal;
import gcom.cadastro.localidade.ControladorLocalidadeLocalHome;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.RemocaoInvalidaException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


/**
 * Description of the Class
 * 
 * @author compesa
 * @created 19 de Julho de 2005
 */
public class ControladorEnderecoSEJB implements SessionBean {

	private static final long serialVersionUID = 1L;

	private IRepositorioEndereco repositorioEndereco = null;

	SessionContext sessionContext;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioEndereco = RepositorioEnderecoHBM.getInstancia();
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbRemove() {
	}

	/**
	 * < <Descri��o do m�todo>>
	 */
	public void ejbActivate() {
	}

	/**
	 * < <Descri��o do m�todo>>
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
	 * inseri um logradouro na base
	 * 
	 * @param logradouro
	 *            Description of the Parameter
	 * @param bairro
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @throws ControladorException
	 */
	public Integer inserirLogradouro(Logradouro logradouro,
			Collection<Bairro> colecaoBairros, Collection<Cep> colecaoCeps)
			throws ControladorException {

		Integer chaveGeradaLogradouro = null;

		// ------------ REGISTRAR TRANSA��O
		// LOGRADOURO----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOGRADOURO_INSERIR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		logradouro.setOperacaoEfetuada(operacaoEfetuada);
		logradouro.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(logradouro);
		// ------------ REGISTRAR TRANSA��O
		// LOGRADOURO----------------------------

		chaveGeradaLogradouro = (Integer) getControladorUtil().inserir(
				logradouro);

		logradouro.setId(chaveGeradaLogradouro);

		/*
		 * Inseri na tabela LOGRADOURO_BAIRRO todos os bairros selecionados pelo
		 * usu�rio para um determinado logradouro
		 */

		this.inserirLogradouroBairro(logradouro, colecaoBairros,
				registradorOperacao);

		/*
		 * Inseri na tabela LOGRADOURO_CEP todos os ceps selecionados pelo
		 * usu�rio para um determinado logradouro
		 */
		this.inserirLogradouroCep(logradouro, colecaoCeps, registradorOperacao);

		return chaveGeradaLogradouro;
	}

	/**
	 * Inseri na tabela LOGRADOURO_BAIRRO todos os bairros selecionados pelo
	 * usu�rio para um determinado logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 03/05/2006
	 * 
	 * @param logradouro,
	 *            colecaoBairros
	 * @return void
	 */
	public void inserirLogradouroBairro(Logradouro logradouro,
			Collection<Bairro> colecaoBairros,
			RegistradorOperacao registradorOperacao)
			throws ControladorException {

		Iterator iteratorColecaoBairros = colecaoBairros.iterator();
		Bairro bairro = null;
		LogradouroBairro logradouroBairro = null;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		while (iteratorColecaoBairros.hasNext()) {
			bairro = (Bairro) iteratorColecaoBairros.next();

			if (!bairro.getMunicipio().getId().equals(
					logradouro.getMunicipio().getId())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.logradouro_municipio_bairro_invalido", null,
						bairro.getNome(), logradouro.getMunicipio().getNome());
			}

			logradouroBairro = new LogradouroBairro();

			// ------------ REGISTRAR TRANSA��O LOGRADOURO BAIRRO--------------
			logradouroBairro.setOperacaoEfetuada(operacaoEfetuada);
			logradouroBairro.adicionarUsuario(Usuario.USUARIO_TESTE,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(logradouroBairro);
			// ------------ REGISTRAR TRANSA��O LOGRADOURO
			// BAIRRO----------------

			logradouroBairro.setLogradouro(logradouro);
			logradouroBairro.setBairro(bairro);
			logradouroBairro.setUltimaAlteracao(new Date());

			this.getControladorUtil().inserir(logradouroBairro);
		}
	}

	/**
	 * Altera na tabela LOGRADOURO_BAIRRO todos os bairros selecionados pelo
	 * usu�rio para um determinado logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 03/05/2006
	 * 
	 * @param logradouro,
	 *            colecaoBairros
	 * @return void
	 */
	public void atualizarLogradouroBairro(
			Logradouro logradouro,
			Collection<Bairro> colecaoBairros,
			Collection<AtualizarLogradouroBairroHelper> colecaoBairrosAtualizacao,
			RegistradorOperacao registradorOperacao)
			throws ControladorException {

		Iterator iteratorColecaoBairros = colecaoBairros.iterator();
		Bairro bairro = null;
		LogradouroBairro logradouroBairro = null;
		LogradouroBairro logradouroBairroNaBase = null;

		Collection colecaoBairroNaBase = this
				.obterTodosBairrosPorLogradouro(logradouro);
		Iterator iteratorColecaoBairroNaBase = null;
		Bairro bairroNaBase = null;

		Iterator iterator = null;
		AtualizarLogradouroBairroHelper atualizarLogradouroBairroHelper = null;
		boolean atualizacaoRealizada = false;

		if (colecaoBairroNaBase != null && !colecaoBairroNaBase.isEmpty()) {

			iteratorColecaoBairroNaBase = colecaoBairroNaBase.iterator();

			while (iteratorColecaoBairroNaBase.hasNext()) {

				bairroNaBase = (Bairro) iteratorColecaoBairroNaBase.next();

				if (!colecaoBairros.contains(bairroNaBase)) {

					logradouroBairroNaBase = this
							.pesquisarAssociacaoLogradouroBairro(bairroNaBase
									.getId(), logradouro.getId());

					if (logradouroBairroNaBase != null) {

						/*
						 * Verificando exist�ncia de atualiza��o
						 */
						// =============================================================================================
						if (colecaoBairrosAtualizacao != null
								&& !colecaoBairrosAtualizacao.isEmpty()) {

							iterator = colecaoBairrosAtualizacao.iterator();
							atualizarLogradouroBairroHelper = null;
							atualizacaoRealizada = false;

							while (iterator.hasNext()) {

								atualizarLogradouroBairroHelper = (AtualizarLogradouroBairroHelper) iterator
										.next();

								if (logradouroBairroNaBase.getId().equals(
										atualizarLogradouroBairroHelper
												.getLogradouroBairro().getId())) {

									/*
									 * Caso o bairro atual seja "BAIRRO NAO
									 * INFORMADO" e o novo bairro j� tenho sido
									 * adicionado na cole��o , removeremos
									 * apenas o "BAIRRO NAO INFORMADO" e todos
									 * os im�veis que estiverem associados ao
									 * mesmo, ser�o automaticamente associados
									 * ao bairro novo
									 * 
									 * Colocado em 22/02/2007 por Raphael
									 * Rossiter
									 */
									/*
									 * if
									 * (this.verificarBairroTipoBairroNaoInformado(logradouroBairroNaBase
									 * .getBairro())){
									 */

									LogradouroBairro logradouroBairroNovo = this
											.pesquisarAssociacaoLogradouroBairro(
													atualizarLogradouroBairroHelper
															.getBairro()
															.getId(),
													logradouro.getId());

									if (logradouroBairroNovo != null) {

										// Atualizar depend�ncias
										this
												.atualizarLogradouroBairroDependencias(
														logradouroBairroNaBase,
														logradouroBairroNovo);
									} else {

										// Atualiza��o no BD
										atualizarLogradouroBairroHelper
												.getLogradouroBairro()
												.setBairro(
														atualizarLogradouroBairroHelper
																.getBairro());

										this.atualizarObjetoLogradouroBairro(
												atualizarLogradouroBairroHelper
														.getLogradouroBairro(),
												registradorOperacao);
									}

									/*
									 * } else{
									 * 
									 * //Atualiza��o no BD
									 * atualizarLogradouroBairroHelper
									 * .getLogradouroBairro().setBairro(
									 * atualizarLogradouroBairroHelper
									 * .getBairro());
									 * 
									 * this
									 * .atualizarObjetoLogradouroBairro(atualizarLogradouroBairroHelper
									 * .getLogradouroBairro(),
									 * registradorOperacao); }
									 */

									atualizacaoRealizada = true;
									break;
								}
							}

							if (!atualizacaoRealizada) {
								getControladorUtil().remover(
										logradouroBairroNaBase);
							}
						} else {
							getControladorUtil()
									.remover(logradouroBairroNaBase);
						}
						// =============================================================================================
					}
				}
			}
		}

		while (iteratorColecaoBairros.hasNext()) {
			bairro = (Bairro) iteratorColecaoBairros.next();

			if (!bairro.getMunicipio().getId().equals(
					logradouro.getMunicipio().getId())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.logradouro_municipio_bairro_invalido", null,
						bairro.getNome(), logradouro.getMunicipio().getNome());
			}

			logradouroBairroNaBase = this.pesquisarAssociacaoLogradouroBairro(
					bairro.getId(), logradouro.getId());

			// Verificando se o bairro em quest�o j� foi atualizado
			// =========================================================================================================

			atualizacaoRealizada = false;
			if (logradouroBairroNaBase == null
					&& colecaoBairrosAtualizacao != null
					&& !colecaoBairrosAtualizacao.isEmpty()) {

				iterator = colecaoBairrosAtualizacao.iterator();
				atualizarLogradouroBairroHelper = null;

				while (iterator.hasNext()) {

					atualizarLogradouroBairroHelper = (AtualizarLogradouroBairroHelper) iterator
							.next();

					if (bairro.getId()
							.equals(
									atualizarLogradouroBairroHelper.getBairro()
											.getId())) {

						atualizacaoRealizada = true;
						break;
					}
				}

			}
			// =========================================================================================================

			if (logradouroBairroNaBase == null && !atualizacaoRealizada) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setLogradouro(logradouro);
				logradouroBairro.setBairro(bairro);
				logradouroBairro.setUltimaAlteracao(new Date());

				// ------------ REGISTRAR TRANSA��O LOGRADOURO
				// BAIRRO--------------
				Operacao operacao = new Operacao();
				operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
				OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
				operacaoEfetuada.setOperacao(operacao);
				logradouroBairro.setOperacaoEfetuada(operacaoEfetuada);
				logradouroBairro.adicionarUsuario(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(logradouroBairro);
				// ------------ REGISTRAR TRANSA��O LOGRADOURO
				// BAIRRO---------------

				this.getControladorUtil().inserir(logradouroBairro);

			}
		}
	}

	/**
	 * Altera todas as tebelas que tenham associa��o com LOGRADOURO_BAIRRO
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2004
	 * 
	 * @param logradouroBairroAntigo,
	 *            logradouroBairroNovo
	 * @return void
	 */
	public void atualizarLogradouroBairroDependencias(
			LogradouroBairro logradouroBairroAntigo,
			LogradouroBairro logradouroBairroNovo) throws ControladorException {

		// Im�vel
		this.getControladorImovel().atualizarLogradouroBairro(
				logradouroBairroAntigo, logradouroBairroNovo);

		// Agencia
		this.getControladorArrecadacao().atualizarLogradouroBairro(
				logradouroBairroAntigo, logradouroBairroNovo);

		// RegistroAtendimento
		this.getControladorRegistroAtendimento().atualizarLogradouroBairro(
				logradouroBairroAntigo, logradouroBairroNovo);

		// RegistroAtendimentoSolicitante
		this.getControladorRegistroAtendimento()
				.atualizarLogradouroBairroSolicitante(logradouroBairroAntigo,
						logradouroBairroNovo);

		// Localidade
		this.getControladorLocalidade().atualizarLogradouroBairro(
				logradouroBairroAntigo, logradouroBairroNovo);

		// GerenciaRegional
		this.getControladorLocalidade()
				.atualizarLogradouroBairroGerenciaRegional(
						logradouroBairroAntigo, logradouroBairroNovo);

		// ClienteEndereco
		this.getControladorCliente().atualizarLogradouroBairro(
				logradouroBairroAntigo, logradouroBairroNovo);

		// Remove o LogradouroBairro que foi substitu�do
		this.getControladorUtil().remover(logradouroBairroAntigo);

	}

	/**
	 * Altera todas as tebelas que tenham associa��o com LOGRADOURO_CEP
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2004
	 * 
	 * @param logradouroCepAntigo,
	 *            logradouroCepNovo
	 * @return void
	 */
	public void atualizarLogradouroCepDependencias(
			LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo)
			throws ControladorException {

		// Im�vel
		this.getControladorImovel().atualizarLogradouroCep(logradouroCepAntigo,
				logradouroCepNovo);

		// Agencia
		this.getControladorArrecadacao().atualizarLogradouroCep(
				logradouroCepAntigo, logradouroCepNovo);

		// RegistroAtendimento
		this.getControladorRegistroAtendimento().atualizarLogradouroCep(
				logradouroCepAntigo, logradouroCepNovo);

		// RegistroAtendimentoSolicitante
		this.getControladorRegistroAtendimento()
				.atualizarLogradouroCepSolicitante(logradouroCepAntigo,
						logradouroCepNovo);

		// Localidade
		this.getControladorLocalidade().atualizarLogradouroCep(
				logradouroCepAntigo, logradouroCepNovo);

		// GerenciaRegional
		this.getControladorLocalidade().atualizarLogradouroCepGerenciaRegional(
				logradouroCepAntigo, logradouroCepNovo);

		// ClienteEndereco
		this.getControladorCliente().atualizarLogradouroCep(
				logradouroCepAntigo, logradouroCepNovo);

		// Remove o LogradouroCep que foi substitu�do
		this.getControladorUtil().remover(logradouroCepAntigo);

	}

	/**
	 * Altera na tabela LOGRADOURO_BAIRRO
	 * 
	 * @author Raphael Rossiter
	 * @date 14/11/2006
	 * 
	 * @param logradouroBairro,
	 *            RegistradorOperacao
	 * @return void
	 */
	public void atualizarObjetoLogradouroBairro(
			LogradouroBairro logradouroBairro,
			RegistradorOperacao registradorOperacao)
			throws ControladorException {

		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();

		// Seta o filtro para buscar o logradouroBairro na base
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
				FiltroLogradouroBairro.ID, logradouroBairro.getId()));

		// Procura o logradouroBairro na base
		LogradouroBairro logradouroBairroNaBase = (LogradouroBairro) ((List) (getControladorUtil()
				.pesquisar(filtroLogradouroBairro, LogradouroBairro.class
						.getName()))).get(0);

		// Verificar se o logradouroBairro j� foi atualizado por outro usu�rio
		// durante
		// esta atualiza��o
		if (logradouroBairroNaBase.getUltimaAlteracao().after(
				logradouroBairro.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de �ltima altera��o
		logradouroBairro.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSA��O LOGRADOURO
		// BAIRRO--------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		logradouroBairro.setOperacaoEfetuada(operacaoEfetuada);
		logradouroBairro.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(logradouroBairro);
		// ------------ REGISTRAR TRANSA��O LOGRADOURO
		// BAIRRO---------------

		// Atualiza o logradouro
		getControladorUtil().atualizar(logradouroBairro);

	}

	/**
	 * Inseri na tabela LOGRADOURO_CEP todos os ceps selecionados pelo usu�rio
	 * para um determinado logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 03/05/2006
	 * 
	 * @param logradouro,
	 *            colecaoCeps
	 * @return void
	 */
	public void inserirLogradouroCep(Logradouro logradouro,
			Collection<Cep> colecaoCeps, RegistradorOperacao registradorOperacao)
			throws ControladorException {

		Iterator iteratorColecaoCeps = colecaoCeps.iterator();
		Cep cep = null;
		LogradouroCep logradouroCep = null;
		Logradouro logradouroAssociado = null;
		Cep cepInicial = null;
		Cep cepUnico = null;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		while (iteratorColecaoCeps.hasNext()) {
			cep = (Cep) iteratorColecaoCeps.next();

			if (!cep.getMunicipio().equalsIgnoreCase(
					logradouro.getMunicipio().getNome())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.logradouro_municipio_cep_invalido", null, cep
								.getCepFormatado(), logradouro.getMunicipio()
								.getNome());
			}

			cepInicial = this.obterCepInicialMunicipio(logradouro
					.getMunicipio());
			cepUnico = this.obterCepUnicoMunicipio(logradouro.getMunicipio());

			/*
			 * Quando o CEP for �nico de munic�pio ou inicial de munic�pio n�o
			 * deve haver a cr�tica CEP j� associado a outro logradouro
			 */

			if (cepUnico != null || cepInicial != null) {

				if (cepUnico != null
						&& !cepUnico.getCepId().equals(cep.getCepId())) {

					logradouroAssociado = this
							.verificarCepAssociadoOutroLogradouro(cep);

					if (logradouroAssociado != null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.logradouro_cep_ja_associado", null,
								cep.getCepFormatado(), logradouroAssociado
										.getId().toString()
										+ " - "
										+ logradouroAssociado
												.getDescricaoFormatada());
					}

				}

				if (cepInicial != null
						&& !cepInicial.getCepId().equals(cep.getCepId())) {

					logradouroAssociado = this
							.verificarCepAssociadoOutroLogradouro(cep);

					if (logradouroAssociado != null) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.logradouro_cep_ja_associado", null,
								cep.getCepFormatado(), logradouroAssociado
										.getId().toString()
										+ " - "
										+ logradouroAssociado
												.getDescricaoFormatada());
					}

				} else if (cepInicial != null
						&& cepInicial.getCepId().equals(cep.getCepId())
						&& colecaoCeps.size() > 1) {

					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.logradouro_cep_inicial_municipio_unico");
				}

			} else {

				logradouroAssociado = this
						.verificarCepAssociadoOutroLogradouro(cep);

				if (logradouroAssociado != null) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.logradouro_cep_ja_associado", null, cep
									.getCepFormatado(), logradouroAssociado
									.getId().toString()
									+ " - "
									+ logradouroAssociado
											.getDescricaoFormatada());
				}

			}

			logradouroCep = new LogradouroCep();

			// ------------ REGISTRAR TRANSA��O LOGRADOURO CEP--------------
			logradouroCep.setOperacaoEfetuada(operacaoEfetuada);
			logradouroCep.adicionarUsuario(Usuario.USUARIO_TESTE,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(logradouroCep);
			// ------------ REGISTRAR TRANSA��O LOGRADOURO CEP----------------
			logradouroCep
					.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			logradouroCep.setLogradouro(logradouro);
			logradouroCep.setCep(cep);
			logradouroCep.setUltimaAlteracao(new Date());

			this.getControladorUtil().inserir(logradouroCep);

		}
	}

	/**
	 * Inseri na tabela LOGRADOURO_CEP todos os ceps selecionados pelo usu�rio
	 * para um determinado logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 03/05/2006
	 * 
	 * @param logradouro,
	 *            colecaoCeps
	 * @return void
	 */
	public void atualizarLogradouroCep(Logradouro logradouro,
			Collection<Cep> colecaoCeps,
			Collection<AtualizarLogradouroCepHelper> colecaoCepsAtualizacao,
			RegistradorOperacao registradorOperacao)
			throws ControladorException {

		Iterator iteratorColecaoCeps = colecaoCeps.iterator();
		Cep cep = null;
		LogradouroCep logradouroCep = null;
		Logradouro logradouroAssociado = null;
		Cep cepInicial = null;
		Cep cepUnico = null;

		cepInicial = this.obterCepInicialMunicipio(logradouro.getMunicipio());
		cepUnico = this.obterCepUnicoMunicipio(logradouro.getMunicipio());

		Collection colecaoLogradouroCepNaBase = this
				.pesquisarAssociacaoLogradouroCepPorLogradouro(logradouro);

		LogradouroCep logradouroCepNaBase = null;

		// Vari�veis que auxiliam na identifica��o de uma atualiza��o
		Iterator iterator = null;
		AtualizarLogradouroCepHelper atualizarLogradouroCepHelper = null;
		boolean atualizacaoRealizada = false;

		if (colecaoLogradouroCepNaBase != null
				&& !colecaoLogradouroCepNaBase.isEmpty()) {

			Iterator iteratorColecaoLogradouroCepNaBase = colecaoLogradouroCepNaBase
					.iterator();

			while (iteratorColecaoLogradouroCepNaBase.hasNext()) {

				logradouroCepNaBase = (LogradouroCep) iteratorColecaoLogradouroCepNaBase
						.next();

				if (cepInicial != null) {

					/*
					 * Caso um Cep que esteja associado, na base, com o
					 * logradouro que estamos querendo atualizar n�o esteja
					 * presenta na cole��o gerada pelo usu�rio E o cep que faz
					 * parte desta associa��o n�o seja o cep inicial do
					 * munic�pio
					 * 
					 */
					/*
					 * if (!colecaoCeps.contains(logradouroCepNaBase.getCep()) &&
					 * !logradouroCepNaBase.getCep().getCepId().equals(cepInicial.getCepId())) {
					 */
					if (!colecaoCeps.contains(logradouroCepNaBase.getCep())) {

						/*
						 * Verificando exist�ncia de atualiza��o
						 */
						// =============================================================================================
						if (colecaoCepsAtualizacao != null
								&& !colecaoCepsAtualizacao.isEmpty()) {

							iterator = colecaoCepsAtualizacao.iterator();
							atualizarLogradouroCepHelper = null;
							atualizacaoRealizada = false;

							while (iterator.hasNext()) {

								atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper) iterator
										.next();

								if (logradouroCepNaBase.getCep().getCepId()
										.equals(
												atualizarLogradouroCepHelper
														.getLogradouroCep()
														.getCep().getCepId())) {

									/*
									 * Caso o cep atual seja inicial de
									 * munic�pio e o novo cep j� tenho sido
									 * adicionado na cole��o
									 * colecaoCepSelecionadosUsuario,
									 * removeremos apenas o cep inicial de
									 * munic�pio e todos os im�veis que
									 * estiverem associados ao mesmo, ser�o
									 * automaticamente associados ao cep de
									 * logradouro
									 * 
									 * Colocado em 22/02/2007 por Raphael
									 * Rossiter
									 */
									/*
									 * if
									 * (this.verificarCepInicialMunicipio(logradouroCepNaBase.getCep())){
									 */

									LogradouroCep logradouroCepNovo = this
											.pesquisarAssociacaoLogradouroCep(
													atualizarLogradouroCepHelper
															.getCep()
															.getCepId(),
													logradouro.getId());

									if (logradouroCepNovo != null) {

										// Atualizar depend�ncias
										this
												.atualizarLogradouroCepDependencias(
														logradouroCepNaBase,
														logradouroCepNovo);
									} else {

										// Atualiza��o no BD
										atualizarLogradouroCepHelper
												.getLogradouroCep().setCep(
														atualizarLogradouroCepHelper
																.getCep());

										this.atualizarObjetoLogradouroCep(
												atualizarLogradouroCepHelper
														.getLogradouroCep(),
												registradorOperacao);
									}

									/*
									 * } else{
									 * 
									 * //Atualiza��o no BD
									 * atualizarLogradouroCepHelper
									 * .getLogradouroCep().setCep(
									 * atualizarLogradouroCepHelper .getCep());
									 * 
									 * this
									 * .atualizarObjetoLogradouroCep(atualizarLogradouroCepHelper
									 * .getLogradouroCep(),
									 * registradorOperacao); }
									 */

									atualizacaoRealizada = true;
									break;
								}
							}

							if (!atualizacaoRealizada) {
								getControladorUtil().remover(
										logradouroCepNaBase);
							}
						} else {
							getControladorUtil().remover(logradouroCepNaBase);
						}
						// =============================================================================================

					}

				}
				/*
				 * Caso um Cep que esteja associado, na base, com o logradouro
				 * que estamos querendo atualizar n�o esteja presenta na cole��o
				 * gerada pelo usu�rio
				 */
				else if (!colecaoCeps.contains(logradouroCepNaBase.getCep())) {

					/*
					 * Verificando exist�ncia de atualiza��o
					 */
					// =============================================================================================
					if (colecaoCepsAtualizacao != null
							&& !colecaoCepsAtualizacao.isEmpty()) {

						iterator = colecaoCepsAtualizacao.iterator();
						atualizarLogradouroCepHelper = null;
						atualizacaoRealizada = false;

						while (iterator.hasNext()) {

							atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper) iterator
									.next();

							if (logradouroCepNaBase.getCep().getCepId().equals(
									atualizarLogradouroCepHelper
											.getLogradouroCep().getCep()
											.getCepId())) {

								/*
								 * Caso o cep atual seja inicial de munic�pio e
								 * o novo cep j� tenho sido adicionado na
								 * cole��o colecaoCepSelecionadosUsuario,
								 * removeremos apenas o cep inicial de munic�pio
								 * e todos os im�veis que estiverem associados
								 * ao mesmo, ser�o automaticamente associados ao
								 * cep de logradouro
								 * 
								 * Colocado em 22/02/2007 por Raphael Rossiter
								 */
								// if
								// (this.verificarCepInicialMunicipio(logradouroCepNaBase.getCep())){
								LogradouroCep logradouroCepNovo = this
										.pesquisarAssociacaoLogradouroCep(
												atualizarLogradouroCepHelper
														.getCep().getCepId(),
												logradouro.getId());

								if (logradouroCepNovo != null) {

									// Atualizar depend�ncias
									this.atualizarLogradouroCepDependencias(
											logradouroCepNaBase,
											logradouroCepNovo);
								} else {

									// Atualiza��o no BD
									atualizarLogradouroCepHelper
											.getLogradouroCep().setCep(
													atualizarLogradouroCepHelper
															.getCep());

									this.atualizarObjetoLogradouroCep(
											atualizarLogradouroCepHelper
													.getLogradouroCep(),
											registradorOperacao);
								}

								/*
								 * } else{
								 * 
								 * //Atualiza��o no BD
								 * atualizarLogradouroCepHelper
								 * .getLogradouroCep().setCep(
								 * atualizarLogradouroCepHelper .getCep());
								 * 
								 * this
								 * .atualizarObjetoLogradouroCep(atualizarLogradouroCepHelper
								 * .getLogradouroCep(), registradorOperacao); }
								 */

								atualizacaoRealizada = true;
								break;
							}
						}
						if (!atualizacaoRealizada) {
							getControladorUtil().remover(logradouroCepNaBase);
						}
					}else {
						logradouroCepNaBase.setIndicadorUso(ConstantesSistema.INDICADOR_USO_DESATIVO);
						getControladorUtil().atualizar(logradouroCepNaBase);
					}
					// =============================================================================================
				}
			}
		}
		
	
		while (iteratorColecaoCeps.hasNext()) {
			cep = (Cep) iteratorColecaoCeps.next();

			if (!cep.getMunicipio().equalsIgnoreCase(
					logradouro.getMunicipio().getNome())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.logradouro_municipio_cep_invalido", null, cep
								.getCepFormatado(), logradouro.getMunicipio()
								.getNome());
			}

			/*
			 * Quando o CEP for �nico de munic�pio ou inicial de munic�pio n�o
			 * deve haver a cr�tica CEP j� associado a outro logradouro
			 */

			if (cepUnico != null || cepInicial != null) {

				if (cepUnico != null
						&& !cepUnico.getCepId().equals(cep.getCepId())) {

					logradouroAssociado = this
							.verificarCepAssociadoOutroLogradouro(cep);

					if (logradouroAssociado != null
							&& !logradouroAssociado.getId().equals(
									logradouro.getId())) {
						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.logradouro_cep_ja_associado", null,
								cep.getCepFormatado(), logradouroAssociado
										.getId().toString()
										+ " - "
										+ logradouroAssociado
												.getDescricaoFormatada());
					}
				}

				if (cepInicial != null
						&& !cepInicial.getCepId().equals(cep.getCepId())) {

					logradouroAssociado = this
							.verificarCepAssociadoOutroLogradouro(cep);

					FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();

					filtroLogradouroCep
							.adicionarCaminhoParaCarregamentoEntidade("logradouro");
					filtroLogradouroCep
							.adicionarParametro(new ParametroSimples(
									FiltroLogradouroCep.ID_CEP, cep.getCepId()));
					filtroLogradouroCep
							.adicionarParametro(new ParametroSimples(
									FiltroLogradouroCep.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection colecaoLogradouroCep = this.getControladorUtil()
							.pesquisar(filtroLogradouroCep,
									LogradouroCep.class.getName());

					// verifica se o CEP j� existe para outro logradouro, por�m
					// se j� existir e for devido a migra��o
					// n�o critica
					boolean logradouroMigracao = false;
					if (colecaoLogradouroCep != null
							&& !colecaoLogradouroCep.isEmpty()) {
						Iterator iteratorColecaoLogradouroCep = colecaoLogradouroCep
								.iterator();
						while (iteratorColecaoLogradouroCep.hasNext()) {
							LogradouroCep logradouroCepPesquisado = (LogradouroCep) iteratorColecaoLogradouroCep
									.next();
							// se existir(na base) o cep para o o logradouro
							// corrente, mantem o cep
							if (logradouroCepPesquisado.getLogradouro().getId()
									.toString().equals(
											logradouro.getId().toString())) {
								logradouroMigracao = true;
							}
						}
					}
					if (!logradouroMigracao
							&& logradouroAssociado != null
							&& !logradouroAssociado.getId().equals(
									logradouro.getId())) {

						sessionContext.setRollbackOnly();
						throw new ControladorException(
								"atencao.logradouro_cep_ja_associado", null,
								cep.getCepFormatado(), logradouroAssociado
										.getId().toString()
										+ " - "
										+ logradouroAssociado
												.getDescricaoFormatada());
					}

				} else if (cepInicial != null
						&& cepInicial.getCepId().equals(cep.getCepId())
						&& colecaoCeps.size() > 1) {

					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.logradouro_cep_inicial_municipio_unico");
				}

			} else {

				logradouroAssociado = this
						.verificarCepAssociadoOutroLogradouro(cep);

				if (logradouroAssociado != null
						&& !logradouroAssociado.getId().equals(
								logradouro.getId())) {
					sessionContext.setRollbackOnly();
					throw new ControladorException(
							"atencao.logradouro_cep_ja_associado", null, cep
									.getCepFormatado(), logradouroAssociado
									.getId().toString()
									+ " - "
									+ logradouroAssociado
											.getDescricaoFormatada());
				}

			}

			logradouroCepNaBase = this.pesquisarAssociacaoLogradouroCep(cep
					.getCepId(), logradouro.getId());

			// Verificando se o cep em quest�o j� foi atualizado
			// =========================================================================================================
			atualizacaoRealizada = false;
			if (logradouroCepNaBase == null && colecaoCepsAtualizacao != null
					&& !colecaoCepsAtualizacao.isEmpty()) {

				iterator = colecaoCepsAtualizacao.iterator();
				atualizarLogradouroCepHelper = null;

				while (iterator.hasNext()) {

					atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper) iterator
							.next();

					if (cep.getCepId().equals(
							atualizarLogradouroCepHelper.getCep().getCepId())) {

						atualizacaoRealizada = true;
						break;
					}
				}

			}
			// =========================================================================================================

			if (logradouroCepNaBase == null && !atualizacaoRealizada) {

				logradouroCep = new LogradouroCep();
				logradouroCep
						.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
				logradouroCep.setLogradouro(logradouro);
				logradouroCep.setCep(cep);
				logradouroCep.setUltimaAlteracao(new Date());

				// ------------ REGISTRAR TRANSA��O LOGRADOURO
				// BAIRRO--------------
				Operacao operacao = new Operacao();
				operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
				OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
				operacaoEfetuada.setOperacao(operacao);
				logradouroCep.setOperacaoEfetuada(operacaoEfetuada);
				logradouroCep.adicionarUsuario(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(logradouroCep);
				// ------------ REGISTRAR TRANSA��O LOGRADOURO
				// BAIRRO---------------

				this.getControladorUtil().inserir(logradouroCep);

			} else if (logradouroCepNaBase != null
					&& cepInicial != null
					&& cepInicial.getCepId().equals(
							logradouroCepNaBase.getCep().getCepId())
					&& colecaoCeps.size() == 1) {

				try {

					// ------------ REGISTRAR TRANSA��O LOGRADOURO
					// BAIRRO--------------
					Operacao operacao = new Operacao();
					operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
					OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
					operacaoEfetuada.setOperacao(operacao);
					logradouroCepNaBase.setOperacaoEfetuada(operacaoEfetuada);
					logradouroCepNaBase.adicionarUsuario(Usuario.USUARIO_TESTE,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					registradorOperacao.registrarOperacao(logradouroCepNaBase);
					// ------------ REGISTRAR TRANSA��O LOGRADOURO
					// BAIRRO---------------

					this.repositorioEndereco
							.atualizarIndicadorUsoLogradouroCep(
									logradouroCepNaBase.getCep().getCepId(),
									logradouroCepNaBase.getLogradouro().getId(),
									ConstantesSistema.INDICADOR_USO_ATIVO);

					LogradouroCep logradouroCepNovo = this
							.pesquisarAssociacaoLogradouroCep((colecaoCeps
									.iterator().next()).getCepId(), logradouro
									.getId());

					if (logradouroCepNovo != null
							&& !logradouroCepNovo.equals(logradouroCepNaBase)) {

						// Atualizar depend�ncias
						this.atualizarLogradouroCepDependencias(
								logradouroCepNaBase, logradouroCepNovo);

					}
					// else{
					//						
					// //Atualiza��o no BD
					// logradouroCepNaBase.setCep(colecaoCeps.iterator().next());
					//						
					// this.atualizarObjetoLogradouroCep(logradouroCepNaBase,
					// registradorOperacao);
					//
					// }

				} catch (ErroRepositorioException ex) {
					ex.printStackTrace();
					throw new ControladorException("erro.sistema", ex);
				}
			}
		}

		colecaoLogradouroCepNaBase = this
				.pesquisarAssociacaoLogradouroCepPorLogradouro(logradouro);

		if (cepInicial != null && colecaoLogradouroCepNaBase.size() > 1) {

			logradouroCep = this.pesquisarAssociacaoLogradouroCep(cepInicial
					.getCepId(), logradouro.getId());

			if (logradouroCep != null) {
				// ------------ REGISTRAR TRANSA��O LOGRADOURO
				// BAIRRO--------------
				Operacao operacao = new Operacao();
				operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
				OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
				operacaoEfetuada.setOperacao(operacao);
				logradouroCep.setOperacaoEfetuada(operacaoEfetuada);
				logradouroCep.adicionarUsuario(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(logradouroCep);
				// ------------ REGISTRAR TRANSA��O LOGRADOURO
				// BAIRRO---------------

				this.inserirAssociacaoLogradouroCep(logradouroCep);

				if (colecaoCepsAtualizacao != null
						&& !colecaoCepsAtualizacao.isEmpty()) {

					iterator = colecaoCepsAtualizacao.iterator();
					atualizarLogradouroCepHelper = null;

					while (iterator.hasNext()) {

						atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper) iterator
								.next();

						if (logradouroCep.getCep().getCepId().equals(
								atualizarLogradouroCepHelper.getLogradouroCep()
										.getCep().getCepId())) {

							/*
							 * Caso o cep atual seja inicial de munic�pio e o
							 * novo cep j� tenho sido adicionado na cole��o
							 * colecaoCepSelecionadosUsuario, removeremos apenas
							 * o cep inicial de munic�pio e todos os im�veis que
							 * estiverem associados ao mesmo, ser�o
							 * automaticamente associados ao cep de logradouro
							 * 
							 * Colocado em 22/02/2007 por Raphael Rossiter
							 */

							LogradouroCep logradouroCepNovo = this
									.pesquisarAssociacaoLogradouroCep(
											atualizarLogradouroCepHelper
													.getCep().getCepId(),
											logradouro.getId());

							if (logradouroCepNovo != null) {

								// Atualizar depend�ncias
								this.atualizarLogradouroCepDependencias(
										logradouroCep, logradouroCepNovo);

							} else {

								// Atualiza��o no BD
								logradouroCep.setCep(colecaoCeps.iterator()
										.next());

								this.atualizarObjetoLogradouroCep(
										logradouroCepNaBase,
										registradorOperacao);
							}

							break;
						}
					}

				}
			}
		}
	}

	/**
	 * Altera na tabela LOGRADOURO_CEP
	 * 
	 * @author Raphael Rossiter
	 * @date 14/11/2006
	 * 
	 * @param logradouroCep,
	 *            RegistradorOperacao
	 * @return void
	 */
	public void atualizarObjetoLogradouroCep(LogradouroCep logradouroCep,
			RegistradorOperacao registradorOperacao)
			throws ControladorException {

		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();

		// Seta o filtro para buscar o logradouroCep na base
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.ID, logradouroCep.getId()));

		// Procura o logradouroCep na base
		LogradouroCep logradouroCepNaBase = (LogradouroCep) ((List) (getControladorUtil()
				.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName())))
				.get(0);

		// Verificar se o logradouroCep j� foi atualizado por outro usu�rio
		// durante
		// esta atualiza��o
		if (logradouroCepNaBase.getUltimaAlteracao().after(
				logradouroCep.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de �ltima altera��o
		logradouroCep.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSA��O LOGRADOURO
		// BAIRRO--------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		logradouroCep.setOperacaoEfetuada(operacaoEfetuada);
		logradouroCep.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(logradouroCep);
		// ------------ REGISTRAR TRANSA��O LOGRADOURO
		// BAIRRO---------------

		// Atualiza o logradouro
		getControladorUtil().atualizar(logradouroCep);

	}

	/**
	 * Obt�m o CEP Inicial para um determinado munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 04/05/2006
	 * 
	 * @param municipio
	 * @return Cep
	 */
	public Cep obterCepInicialMunicipio(Municipio municipio)
			throws ControladorException {

		Cep cep = null;

		if (municipio.getCepInicio() != null) {

			FiltroCep filtroCep = new FiltroCep();

			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
					municipio.getCepInicio()));
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.CEP_TIPO_ID, CepTipo.INICIAL));
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoCepPadrao = this.getControladorUtil().pesquisar(
					filtroCep, Cep.class.getName());

			if (colecaoCepPadrao != null && !colecaoCepPadrao.isEmpty()) {
				cep = (Cep) Util.retonarObjetoDeColecao(colecaoCepPadrao);
			}
		}

		return cep;
	}

	/**
	 * Obt�m o CEP �nico para um determinado munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 23/05/2006
	 * 
	 * @param municipio
	 * @return Cep
	 */
	public Cep obterCepUnicoMunicipio(Municipio municipio)
			throws ControladorException {

		Cep cep = null;

		if (municipio.getCepInicio() != null) {

			FiltroCep filtroCep = new FiltroCep();

			filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO,
					municipio.getCepInicio()));
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.CEP_TIPO_ID, CepTipo.UNICO));
			filtroCep.adicionarParametro(new ParametroSimples(
					FiltroCep.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoCepPadrao = this.getControladorUtil().pesquisar(
					filtroCep, Cep.class.getName());

			if (colecaoCepPadrao != null && !colecaoCepPadrao.isEmpty()) {
				cep = (Cep) Util.retonarObjetoDeColecao(colecaoCepPadrao);
			}
		}

		return cep;
	}

	/**
	 * Verificar se o CEP est� associado a outro logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 04/05/2006
	 * 
	 * @param cep
	 * @return Logradouro
	 */
	public Logradouro verificarCepAssociadoOutroLogradouro(Cep cep)
			throws ControladorException {

		Logradouro retorno = null;

		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();

		filtroLogradouroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroLogradouroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
		filtroLogradouroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
		filtroLogradouroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro.municipio");
		filtroLogradouroCep
				.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroBairros");
		
		filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.ID_CEP, cep.getCepId()));

		filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoLogradouroCep = this.getControladorUtil().pesquisar(
				filtroLogradouroCep, LogradouroCep.class.getName());

		if (colecaoLogradouroCep != null && !colecaoLogradouroCep.isEmpty()) {

			LogradouroCep logradouroCep = (LogradouroCep) Util
					.retonarObjetoDeColecao(colecaoLogradouroCep);
			retorno = logradouroCep.getLogradouro();
		}

		return retorno;
	}

	/**
	 * Verifica se o logradouro j� est� associado a CEPs do tipo logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 17/05/2006
	 * 
	 * @param logradouro
	 * @return boolean
	 */
	public boolean verificarLogradouroAssociadoCepTipoLogradouro(
			Logradouro logradouro) throws ControladorException {

		boolean retorno = false;

		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();

		filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.ID_LOGRADOURO, logradouro));

		filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.ID_CEP_TIPO_CEP, CepTipo.LOGRADOURO));

		Collection colecaoLogradouroCep = this.getControladorUtil().pesquisar(
				filtroLogradouroCep, LogradouroCep.class.getName());

		if (colecaoLogradouroCep != null && !colecaoLogradouroCep.isEmpty()) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Verifica se o CEP � �nico de munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param cep
	 * @return boolean
	 */
	public boolean verificarCepUnicoMunicipio(Cep cep)
			throws ControladorException {

		boolean retorno = false;

		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO, cep
				.getCodigo()));

		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.CEP_TIPO_ID, CepTipo.UNICO));

		Collection colecaoCep = this.getControladorUtil().pesquisar(filtroCep,
				Cep.class.getName());

		if (colecaoCep != null && !colecaoCep.isEmpty()) {

			retorno = true;
		}

		return retorno;
	}

	/**
	 * Verifica se o CEP � inicial de munic�pio
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param cep
	 * @return boolean
	 */
	public boolean verificarCepInicialMunicipio(Cep cep)
			throws ControladorException {

		boolean retorno = false;

		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.CODIGO, cep
				.getCodigo()));

		filtroCep.adicionarParametro(new ParametroSimples(
				FiltroCep.CEP_TIPO_ID, CepTipo.INICIAL));

		Collection colecaoCep = this.getControladorUtil().pesquisar(filtroCep,
				Cep.class.getName());

		if (colecaoCep != null && !colecaoCep.isEmpty()) {

			retorno = true;
		}

		return retorno;
	}

	/**
	 * Verifica se o Bairro � do tipo "BAIRRO NAO INFORMADO"
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param bairro
	 * @return boolean
	 */
	public boolean verificarBairroTipoBairroNaoInformado(Bairro bairro)
			throws ControladorException {

		boolean retorno = false;

		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID,
				bairro.getId()));

		filtroBairro.adicionarParametro(new ComparacaoTextoCompleto(
				FiltroBairro.NOME, Bairro.BAIRRO_NAO_INFORMADO));

		Collection colecaoBairro = this.getControladorUtil().pesquisar(
				filtroBairro, Bairro.class.getName());

		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			retorno = true;
		}

		return retorno;
	}

	/**
	 * Seleciona os bairros em que o logradouro est� contido
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param Logradouro
	 * @return Collection<Bairro>
	 */
	public Collection<Bairro> obterBairrosPorLogradouro(Logradouro logradouro)
			throws ControladorException {

		Collection retorno = null;

		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();

		filtroLogradouroBairro
				.adicionarCaminhoParaCarregamentoEntidade("bairro");

		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
				FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
				FiltroLogradouroBairro.INDICADORUSO_BAIRRO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoLogradouroBairro = this.getControladorUtil()
				.pesquisar(filtroLogradouroBairro,
						LogradouroBairro.class.getName());

		if (colecaoLogradouroBairro != null
				&& !colecaoLogradouroBairro.isEmpty()) {

			Iterator iteratorColecaoLogradouroBairro = colecaoLogradouroBairro
					.iterator();
			retorno = new ArrayList();
			LogradouroBairro logradouroBairro = null;

			while (iteratorColecaoLogradouroBairro.hasNext()) {
				logradouroBairro = (LogradouroBairro) iteratorColecaoLogradouroBairro
						.next();
				retorno.add(logradouroBairro.getBairro());
			}
		} else {
			retorno = new ArrayList();
		}
		
		return retorno;
	}

	
	
	/**
	 * Seleciona os bairros em que o logradouro est� contido (INCLUSIVE OS QUE
	 * ESTEJAM COM INDICADOR DESATIVADO)
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param Logradouro
	 * @return Collection<Bairro>
	 */
	public Collection<Bairro> obterTodosBairrosPorLogradouro(
			Logradouro logradouro) throws ControladorException {

		Collection retorno = null;

		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();

		filtroLogradouroBairro
				.adicionarCaminhoParaCarregamentoEntidade("bairro");

		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
				FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));

		Collection colecaoLogradouroBairro = this.getControladorUtil()
				.pesquisar(filtroLogradouroBairro,
						LogradouroBairro.class.getName());

		if (colecaoLogradouroBairro != null
				&& !colecaoLogradouroBairro.isEmpty()) {

			Iterator iteratorColecaoLogradouroBairro = colecaoLogradouroBairro
					.iterator();
			retorno = new ArrayList();
			LogradouroBairro logradouroBairro = null;

			while (iteratorColecaoLogradouroBairro.hasNext()) {
				logradouroBairro = (LogradouroBairro) iteratorColecaoLogradouroBairro
						.next();
				retorno.add(logradouroBairro.getBairro());
			}
		}

		return retorno;
	}

	/**
	 * 
	 * 
	 * @author Raphael Rossiter
	 * @date 10/05/2006
	 * 
	 * @param Logradouro
	 * @return Integer
	 */
	public Integer inserirAssociacaoLogradouroCep(LogradouroCep logradouroCep)
			throws ControladorException {

		Integer retorno = null;
		Collection<LogradouroCep> colecaoLogradouroCep = null;

		try {

			colecaoLogradouroCep = this.repositorioEndereco
					.pesquisarAssociacaoLogradouroCepPorLogradouro(logradouroCep
							.getLogradouro().getId());

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		if (colecaoLogradouroCep != null && !colecaoLogradouroCep.isEmpty()) {

			if (colecaoLogradouroCep.size() > 1) {

				Iterator iteratorColecaoLogradouroCep = colecaoLogradouroCep
						.iterator();
				LogradouroCep logradouroCepColecao = null;

				while (iteratorColecaoLogradouroCep.hasNext()) {
					logradouroCepColecao = (LogradouroCep) iteratorColecaoLogradouroCep
							.next();

					if (logradouroCepColecao.getCep().getCepTipo().getId()
							.equals(CepTipo.INICIAL)) {

						try {

							this.repositorioEndereco
									.atualizarIndicadorUsoLogradouroCep(
											logradouroCepColecao.getCep()
													.getCepId(),
											logradouroCepColecao
													.getLogradouro().getId(),
											ConstantesSistema.INDICADOR_USO_DESATIVO);

						} catch (ErroRepositorioException ex) {
							ex.printStackTrace();
							throw new ControladorException("erro.sistema", ex);
						}
					}
				}
			}
		}

		LogradouroCep logradouroCepExistente = null;

		logradouroCepExistente = this.pesquisarAssociacaoLogradouroCep(
				logradouroCep.getCep().getCepId(), logradouroCep
						.getLogradouro().getId());

		if (logradouroCepExistente == null) {
			logradouroCep.setUltimaAlteracao(new Date());
			retorno = (Integer) this.getControladorUtil()
					.inserir(logradouroCep);
		} else {
			retorno = logradouroCepExistente.getId();
		}

		return retorno;
	}

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
			Logradouro logradouro) throws ControladorException {

		Collection<LogradouroCep> retorno = null;

		try {

			retorno = this.repositorioEndereco
					.pesquisarAssociacaoLogradouroCepPorLogradouro(logradouro
							.getId());

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}

	/**
	 * atualiza um logradouro na base e se tiver um bairro inseri na tabela de
	 * liga��o logradouroBairro
	 * 
	 * @param logradouro
	 *            Description of the Parameter
	 * @param bairro
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarLogradouro(
			Logradouro logradouro,
			Collection colecaoBairros,
			Collection colecaoCeps,
			Collection<AtualizarLogradouroBairroHelper> colecaoBairrosAtualizacao,
			Collection<AtualizarLogradouroCepHelper> colecaoCepsAtualizacao)
			throws ControladorException {
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		// Seta o filtro para buscar o logradouro na base
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID, logradouro.getId()));

		// Procura o logradouro na base
		Collection colecaoLogradouro = getControladorUtil().pesquisar(
				filtroLogradouro, Logradouro.class.getName());
		if (colecaoLogradouro == null || colecaoLogradouro.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException(
					"atencao.registro_remocao_nao_existente");
		}

		Logradouro logradouroNaBase = (Logradouro) Util
				.retonarObjetoDeColecao(colecaoLogradouro);

		// Verificar se o logradouro j� foi atualizado por outro usu�rio
		// durante
		// esta atualiza��o
		if (logradouroNaBase.getUltimaAlteracao().after(
				logradouro.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// Atualiza a data de �ltima altera��o
		logradouro.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOGRADOURO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		logradouro.setOperacaoEfetuada(operacaoEfetuada);
		logradouro.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(logradouro);
		// ------------ REGISTRAR TRANSA��O ----------------

		// Atualiza o logradouro
		getControladorUtil().atualizar(logradouro);

		/*
		 * Atualiza todos os relacionamentos encontrados (LogradouroBairro)
		 * informados pelo usu�rio
		 */
		this.atualizarLogradouroBairro(logradouro, colecaoBairros,
				colecaoBairrosAtualizacao, registradorOperacao);

		/*
		 * Atualizando todos os relacionamentos encontrados (LogradouroCep)
		 * informados pelo usu�rio
		 */
		this.atualizarLogradouroCep(logradouro, colecaoCeps,
				colecaoCepsAtualizacao, registradorOperacao);

	}

	/**
	 * remove um logradouro e o bairro ta tabela liga��o logradouroBairro do
	 * logradouro removido.
	 * 
	 * @param ids
	 *            Description of the Parameter
	 * @param pacoteLogradouro
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void removerLogradouro(String[] ids, String pacoteLogradouro,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ControladorException {

		if (ids != null && ids.length != 0) {

			Logradouro logradouro = new Logradouro();

			for (int i = 0; i < ids.length; i++) {

				int idLogradouro = Integer.parseInt(ids[i]);

				this.getControladorUtil().remover(
						new String[] { "" + idLogradouro },
						logradouro.getClass().getName(), operacaoEfetuada,
						acaoUsuarioHelper);

				// chama o met�do de remover unidade executora do
				// reposit�rio
				/*
				 * getControladorUtil().removerUm(idLogradouro,
				 * pacoteLogradouro, operacaoEfetuada, acaoUsuarioHelper);
				 */
			}
		}
	}

	/**
	 * inseri os ceps importados
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void inserirCepImportados(Collection cepsImportados)
			throws ControladorException {
		Collection inserirCepImportados = new ArrayList();
		Collection atualizarCepImportados = new ArrayList();
		Iterator cepsImportadosIterator = cepsImportados.iterator();
		try {

			Collection cepsCadastrados = repositorioEndereco.pesquisarCep();

			Collections.sort((List) cepsCadastrados, new Comparator() {
				public int compare(Object a, Object b) {
					Integer codigo1 = ((Cep) a).getCodigo();
					Integer codigo2 = ((Cep) b).getCodigo();

					return codigo1.compareTo(codigo2);
				}
			});

			while (cepsImportadosIterator.hasNext()) {

				Cep cepImportado = (Cep) cepsImportadosIterator.next();
				if (cepsCadastrados.contains(cepImportado)) {

					int posicaoRegistro = Collections.binarySearch(
							(List) cepsCadastrados, cepImportado,
							new Comparator() {
								public int compare(Object a, Object b) {
									Integer codigo1 = ((Cep) a).getCodigo();
									Integer codigo2 = ((Cep) b).getCodigo();

									return codigo1.compareTo(codigo2);
								}
							});
					Cep cepCadastrado = (Cep) ((List) cepsCadastrados)
							.get(posicaoRegistro);

					if (atualizarCepImportados.contains(cepCadastrado)) {

						Collections.sort((List) atualizarCepImportados,
								new Comparator() {
									public int compare(Object a, Object b) {
										Integer codigo1 = ((Cep) a).getCodigo();
										Integer codigo2 = ((Cep) b).getCodigo();

										return codigo1.compareTo(codigo2);
									}
								});

						int posicaoRegistroAtual = Collections.binarySearch(
								(List) atualizarCepImportados, cepCadastrado,
								new Comparator() {
									public int compare(Object a, Object b) {
										Integer codigo1 = ((Cep) a).getCodigo();
										Integer codigo2 = ((Cep) b).getCodigo();

										return codigo1.compareTo(codigo2);
									}
								});
						Cep cepAtualizado = (Cep) ((List) atualizarCepImportados)
								.get(posicaoRegistroAtual);

						atualizarCepImportados.remove(cepAtualizado);

						cepImportado.setCepId(cepCadastrado.getCepId());
						cepImportado.setCepTipo(cepCadastrado.getCepTipo());

						atualizarCepImportados.add(cepImportado);

					} else {
						cepImportado.setCepId(cepCadastrado.getCepId());
						cepImportado.setCepTipo(cepCadastrado.getCepTipo());
						atualizarCepImportados.add(cepImportado);
					}

				} else {
					if (!inserirCepImportados.contains(cepImportado)) {
						inserirCepImportados.add(cepImportado);
					}
				}

			}

			if (atualizarCepImportados != null
					&& !atualizarCepImportados.isEmpty()) {
				repositorioEndereco
						.atualizarImportacaoCep(atualizarCepImportados);
			}

			if (inserirCepImportados != null && !inserirCepImportados.isEmpty()) {
				repositorioEndereco.inserirImportacaoCep(inserirCepImportados);
			}
		} catch (RemocaoInvalidaException ex) {
			throw new ControladorException("atencao.dependencias.existentes",
					ex);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Retorna o valor de controladorUtil
	 * 
	 * @return O valor de controladorUtil
	 */
	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * Retorna o valor de controladorImovel
	 * 
	 * @return O valor de controladorImovel
	 */
	private ControladorImovelLocal getControladorImovel() {

		ControladorImovelLocalHome localHome = null;
		ControladorImovelLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorImovelLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_IMOVEL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * Retorna o valor de controladorArrecadacao
	 * 
	 * @return O valor de controladorArrecadacao
	 */
	private ControladorArrecadacaoLocal getControladorArrecadacao() {

		ControladorArrecadacaoLocalHome localHome = null;
		ControladorArrecadacaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorArrecadacaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_ARRECADACAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * Retorna o valor de controladorRegistroAtendimento
	 * 
	 * @return O valor de controladorRegistroAtendimento
	 */
	private ControladorRegistroAtendimentoLocal getControladorRegistroAtendimento() {

		ControladorRegistroAtendimentoLocalHome localHome = null;
		ControladorRegistroAtendimentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorRegistroAtendimentoLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_REGISTRO_ATENDIMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorLocalidadeLocal getControladorLocalidade() {

		ControladorLocalidadeLocalHome localHome = null;
		ControladorLocalidadeLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorLocalidadeLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_LOCALIDADE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * Retorna o valor de controladorCliente
	 * 
	 * @return O valor de controladorCliente
	 */
	private ControladorClienteLocal getControladorCliente() {

		ControladorClienteLocalHome localHome = null;
		ControladorClienteLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorClienteLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_CLIENTE_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
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
	 * [UC0085] - Obter Endere�o Autor: S�vio Luiz Data: 26/12/2005
	 */

	public String pesquisarEndereco(Integer idImovel)
			throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco.pesquisarEndereco(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		if (arrayEndereco[20] != null && arrayEndereco[21] != null) {

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
				// Descri��o logradouro tipo
				if (arrayEndereco[22] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[22]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[23] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[23]);
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

			imovel.setLogradouroCep(logradouroCep);

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

					// id da unidade federa��o
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

			imovel.setLogradouroBairro(logradouroBairro);

			// descricao de endere�o refer�ncia
			if (arrayEndereco[24] != null) {
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setDescricao("" + arrayEndereco[24]);
				imovel.setEnderecoReferencia(enderecoReferencia);
			}

			// numero imovel
			if (arrayEndereco[17] != null) {
				imovel.setNumeroImovel("" + arrayEndereco[17]);
			}

			// complemento endere�o
			if (arrayEndereco[18] != null) {
				imovel.setComplementoEndereco("" + arrayEndereco[18]);
			}
			
			// Per�metro
			if (arrayEndereco[25] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[25]);
				
				if (arrayEndereco[26] != null) {
					perimetroInicial.setNome((String) arrayEndereco[26]);
				}
				
				if (arrayEndereco[27] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[27]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[28] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[28]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				imovel.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[29] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[29]);
				
				if (arrayEndereco[30] != null) {
					perimetroFinal.setNome((String) arrayEndereco[30]);
				}
				
				if (arrayEndereco[31] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[31]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[32] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[32]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				imovel.setPerimetroFinal(perimetroFinal);
			}

			endereco = imovel.getEnderecoFormatado();
		} else {

			FiltroImovelEnderecoAnterior filtroImovelEnderecoAnterior = new FiltroImovelEnderecoAnterior();

			filtroImovelEnderecoAnterior
					.adicionarParametro(new ParametroSimples(
							FiltroImovelEnderecoAnterior.ID, idImovel));

			Collection colecaoImovelEnderecoAnterior = this
					.getControladorUtil().pesquisar(
							filtroImovelEnderecoAnterior,
							ImovelEnderecoAnterior.class.getName());

			if (colecaoImovelEnderecoAnterior != null
					&& !colecaoImovelEnderecoAnterior.isEmpty()) {

				ImovelEnderecoAnterior imovelEnderecoAnterior = (ImovelEnderecoAnterior) colecaoImovelEnderecoAnterior
						.iterator().next();

				endereco = imovelEnderecoAnterior.getEnderecoAnterior();
			}
		}

		return endereco;
	}

	/**
	 * [UC0210] - Obter Endere�o Autor: Fernanda Paiva
	 */

	public String pesquisarEnderecoFormatado(Integer idImovel)
			throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoFormatado(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		Iterator enderecoIterator = colecaoEndereco.iterator();
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

			if (arrayEndereco[20] != null && arrayEndereco[21] != null) {

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
					// Descri��o abreviada logradouro tipo
					if (arrayEndereco[1] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[1]);
						if (arrayEndereco[22] != null) {
							logradouroTipo.setDescricaoAbreviada(""
									+ arrayEndereco[22]);
						}
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o abreviada logradouro titulo
					if (arrayEndereco[2] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[2]);
						if (arrayEndereco[23] != null) {
							logradouroTitulo.setDescricaoAbreviada(""
									+ arrayEndereco[23]);
						}
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

				imovel.setLogradouroCep(logradouroCep);

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

						// id da unidade federa��o
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

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[9]);
					if (arrayEndereco[24] != null) {
						enderecoReferencia.setDescricaoAbreviada(""
								+ arrayEndereco[24]);
					}
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					imovel.setNumeroImovel("" + arrayEndereco[17]);
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					imovel.setComplementoEndereco("" + arrayEndereco[18]);
				}
				
				// Per�metro
				if (arrayEndereco[26] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[26]);
					
					if (arrayEndereco[27] != null) {
						perimetroInicial.setNome((String) arrayEndereco[27]);
					}
					
					if (arrayEndereco[28] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[28]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[29] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[29]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[30] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[30]);
					
					if (arrayEndereco[31] != null) {
						perimetroFinal.setNome((String) arrayEndereco[31]);
					}
					
					if (arrayEndereco[32] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[32]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[33] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[33]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroFinal(perimetroFinal);
				}

				endereco = imovel.getEnderecoFormatadoAbreviado();

			} else {

				FiltroImovelEnderecoAnterior filtroImovelEnderecoAnterior = new FiltroImovelEnderecoAnterior();

				filtroImovelEnderecoAnterior
						.adicionarParametro(new ParametroSimples(
								FiltroImovelEnderecoAnterior.ID, idImovel));

				Collection colecaoImovelEnderecoAnterior = this
						.getControladorUtil().pesquisar(
								filtroImovelEnderecoAnterior,
								ImovelEnderecoAnterior.class.getName());

				if (colecaoImovelEnderecoAnterior != null
						&& !colecaoImovelEnderecoAnterior.isEmpty()) {

					ImovelEnderecoAnterior imovelEnderecoAnterior = (ImovelEnderecoAnterior) colecaoImovelEnderecoAnterior
							.iterator().next();

					endereco = imovelEnderecoAnterior.getEnderecoAnterior();
				}
			}
		}

		return endereco;
	}

	/**
	 * Obter os campos necess�rio para o endere�o do im�vel Autor:S�vio Luiz
	 */

	public Imovel pesquisarImovelParaEndereco(Integer idImovel)
			throws ControladorException {
		Imovel imovel = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoFormatado(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		imovel = new Imovel();

		Iterator enderecoIterator = colecaoEndereco.iterator();
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

			if (arrayEndereco[20] != null && arrayEndereco[21] != null) {

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
					// Descri��o abreviada logradouro tipo
					if (arrayEndereco[1] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[1]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o abreviada logradouro titulo
					if (arrayEndereco[2] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[2]);
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

				imovel.setLogradouroCep(logradouroCep);

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

						// id da unidade federa��o
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

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricaoAbreviada(""
							+ arrayEndereco[9]);
					enderecoReferencia.setId((Integer) arrayEndereco[25]);
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					imovel.setNumeroImovel("" + arrayEndereco[17]);
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					imovel.setComplementoEndereco("" + arrayEndereco[18]);
				}
				
				if (arrayEndereco[26] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[26]);
					
					if (arrayEndereco[27] != null) {
						perimetroInicial.setNome((String) arrayEndereco[27]);
					}
					
					if (arrayEndereco[28] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[28]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[29] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[29]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[30] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[30]);
					
					if (arrayEndereco[31] != null) {
						perimetroFinal.setNome((String) arrayEndereco[31]);
					}
					
					if (arrayEndereco[32] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[32]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[33] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[33]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroFinal(perimetroFinal);
				}

			}
		}

		return imovel;
	}

	/**
	 * [UC0085] - Obter Endere�o Autor: S�vio Luiz Data: 14/06/2006
	 */

	public String pesquisarEnderecoClienteAbreviado(Integer idCliente)
			throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoClienteAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		ClienteEndereco clienteEndereco = new ClienteEndereco();

		Iterator enderecoIterator = colecaoEndereco.iterator();
		while (enderecoIterator.hasNext()) {
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

					Municipio municipio = null;
					// nome municipio
					if (arrayEndereco[5] != null) {
						municipio = new Municipio();
						municipio.setId((Integer) arrayEndereco[5]);
						municipio.setNome("" + arrayEndereco[6]);

						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
							unidadeFederacao.setId((Integer) arrayEndereco[7]);
							unidadeFederacao.setSigla("" + arrayEndereco[8]);
							municipio.setUnidadeFederacao(unidadeFederacao);
						}

						logradouro.setMunicipio(municipio);
					}
				}
				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[22] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo
							.setDescricaoAbreviada("" + arrayEndereco[22]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
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
				if (arrayEndereco[25] != null) {
					municipio = new Municipio();
					municipio.setId((Integer) arrayEndereco[25]);
					municipio.setNome("" + arrayEndereco[26]);

					// id da unidade federa��o
					if (arrayEndereco[27] != null) {
						UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
						unidadeFederacao.setId((Integer) arrayEndereco[27]);
						unidadeFederacao.setSigla("" + arrayEndereco[28]);
						municipio.setUnidadeFederacao(unidadeFederacao);
					}

					bairro.setMunicipio(municipio);
				}

				logradouroBairro.setBairro(bairro);
			}

			clienteEndereco.setLogradouroBairro(logradouroBairro);

			// descricao de endere�o refer�ncia
			if (arrayEndereco[9] != null) {
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setDescricaoAbreviada("" + arrayEndereco[9]);
				clienteEndereco.setEnderecoReferencia(enderecoReferencia);
			}

			// numero imovel
			if (arrayEndereco[17] != null) {
				clienteEndereco.setNumero("" + arrayEndereco[17]);
			}

			// complemento endere�o
			if (arrayEndereco[18] != null) {
				clienteEndereco.setComplemento("" + arrayEndereco[18]);
			}
			
			// Per�metro
			if (arrayEndereco[29] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[29]);
				
				if (arrayEndereco[30] != null) {
					perimetroInicial.setNome((String) arrayEndereco[30]);
				}
				
				if (arrayEndereco[31] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[31]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[32] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[32]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				clienteEndereco.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[33] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[33]);
				
				if (arrayEndereco[34] != null) {
					perimetroFinal.setNome((String) arrayEndereco[34]);
				}
				
				if (arrayEndereco[35] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[35]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[36] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[36]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				clienteEndereco.setPerimetroFinal(perimetroFinal);
			}

			endereco = clienteEndereco.getEnderecoFormatadoAbreviado();
		}

		return endereco;
	}

	/**
	 * [UC0085] - Obter Endere�o Autor: S�vio Luiz Data: 09/04/2007 Recupera o
	 * endere�o em 5 partes:o endere�o abreviado formatado sem o municipio e a
	 * unidade federa��o,a descri��o do municipio, a terceira parte a sigla da
	 * unidade federa��o,quarta parte o nome do bairro e a quinta parte o cep
	 * formatado
	 */

	public String[] pesquisarEnderecoClienteAbreviadoDividido(Integer idCliente)
			throws ControladorException {
		// 0 - Endere�o sem municipio e UF
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		
		String[] parmsEndereco = new String[5];
		
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";
		
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoClienteAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		ClienteEndereco clienteEndereco = new ClienteEndereco();

		Iterator enderecoIterator = colecaoEndereco.iterator();
		while (enderecoIterator.hasNext()) {
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

					// nome municipio
					if (arrayEndereco[5] != null) {
						nomeMunicipio = "" + arrayEndereco[6];

						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							siglaUnidadeFederacao = "" + arrayEndereco[8];

						}

					}
				}
				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[22] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo
							.setDescricaoAbreviada("" + arrayEndereco[22]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[23] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada(""
							+ arrayEndereco[23]);
					logradouro.setLogradouroTitulo(logradouroTitulo);
				}
				if (arrayEndereco[10] != null) {
					cepFormatado = "" + (Integer) arrayEndereco[16];
				}

				logradouroCep.setLogradouro(logradouro);
			}

			clienteEndereco.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[21] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[21]);

				// nome bairro
				if (arrayEndereco[3] != null) {
					nomeBairro = "" + arrayEndereco[4];
				}

				// nome municipio
				if (arrayEndereco[25] != null) {
					nomeMunicipio = "" + arrayEndereco[26];

					// id da unidade federa��o
					if (arrayEndereco[27] != null) {
						siglaUnidadeFederacao = "" + arrayEndereco[28];
					}
				}

			}

			clienteEndereco.setLogradouroBairro(logradouroBairro);

			// descricao de endere�o refer�ncia
			if (arrayEndereco[9] != null) {
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setDescricaoAbreviada("" + arrayEndereco[9]);
				clienteEndereco.setEnderecoReferencia(enderecoReferencia);
			}

			// numero imovel
			if (arrayEndereco[17] != null) {
				clienteEndereco.setNumero("" + arrayEndereco[17]);
			}

			// complemento endere�o
			if (arrayEndereco[18] != null) {
				clienteEndereco.setComplemento("" + arrayEndereco[18]);
			}
			
			// Per�metro
			if (arrayEndereco[29] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[29]);
				
				if (arrayEndereco[30] != null) {
					perimetroInicial.setNome((String) arrayEndereco[30]);
				}
				
				if (arrayEndereco[31] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[31]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[32] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[32]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				clienteEndereco.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[33] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[33]);
				
				if (arrayEndereco[34] != null) {
					perimetroFinal.setNome((String) arrayEndereco[34]);
				}
				
				if (arrayEndereco[35] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[35]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[36] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[36]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				clienteEndereco.setPerimetroFinal(perimetroFinal);
			}

			endereco = clienteEndereco.getEnderecoFormatadoAbreviado();
		}

		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;

		return parmsEndereco;
	}
	
	
	public Collection pesquisarEnderecoTotalmenteDividido(Integer idImovel)
		throws ControladorException {
		
		try {
			return repositorioEndereco
					.pesquisarEnderecoFormatado(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
	}
	
	

	/**
	 * [UC0210] - Obter Endere�o Autor: S�vio Luiz
	 */

	public String[] pesquisarEnderecoFormatadoDividido(Integer idImovel)
			throws ControladorException {

		// 0 - Endere�o sem municipio e UF
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		String[] parmsEndereco = new String[5];
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoFormatado(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Iterator enderecoIterator = colecaoEndereco.iterator();
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

			if (arrayEndereco[20] != null && arrayEndereco[21] != null) {

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
					// Descri��o abreviada logradouro tipo
					if (arrayEndereco[1] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[1]);
						if (arrayEndereco[22] != null) {
							logradouroTipo.setDescricaoAbreviada(""
									+ arrayEndereco[22]);
						}
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o abreviada logradouro titulo
					if (arrayEndereco[2] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[2]);
						if (arrayEndereco[23] != null) {
							logradouroTitulo.setDescricaoAbreviada(""
									+ arrayEndereco[23]);
						}
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					if (arrayEndereco[10] != null) {
						cepFormatado = "" + (Integer) arrayEndereco[16];
					}

					logradouroCep.setLogradouro(logradouro);
				}

				imovel.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (arrayEndereco[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayEndereco[21]);

					// nome bairro
					if (arrayEndereco[3] != null) {
						nomeBairro = "" + arrayEndereco[4];
					}

					// nome municipio
					if (arrayEndereco[5] != null) {
						nomeMunicipio = "" + arrayEndereco[6];

						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							siglaUnidadeFederacao = "" + arrayEndereco[8];
						}

					}
				}

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[9]);
					if (arrayEndereco[24] != null) {
						enderecoReferencia.setDescricaoAbreviada(""
								+ arrayEndereco[24]);
					}
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					imovel.setNumeroImovel("" + arrayEndereco[17]);
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					imovel.setComplementoEndereco("" + arrayEndereco[18]);
				}

				// Per�metro
				if (arrayEndereco[26] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[26]);
					
					if (arrayEndereco[27] != null) {
						perimetroInicial.setNome((String) arrayEndereco[27]);
					}
					
					if (arrayEndereco[28] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[28]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[29] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[29]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[30] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[30]);
					
					if (arrayEndereco[31] != null) {
						perimetroFinal.setNome((String) arrayEndereco[31]);
					}
					
					if (arrayEndereco[32] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[32]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[33] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[33]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroFinal(perimetroFinal);
				}
				
				endereco = imovel.getEnderecoFormatadoAbreviado();

			}
		}
		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;

		return parmsEndereco;

	}

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
			Integer idBairro, Integer idLogradouro) throws ControladorException {

		// LogradouroBairro logradouroBairro = null;

		try {

			return /* logradouroBairro = */this.repositorioEndereco
					.pesquisarAssociacaoLogradouroBairro(idBairro, idLogradouro);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroCep j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 24/05/2006
	 * 
	 * @param idCep,
	 *            idLogradouro
	 * @return LogradouroBairro
	 */
	public LogradouroCep pesquisarAssociacaoLogradouroCep(Integer idCep,
			Integer idLogradouro) throws ControladorException {

		try {

			return this.repositorioEndereco.pesquisarAssociacaoLogradouroCep(
					idCep, idLogradouro);

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	public Collection<Logradouro> pesquisarLogradouro(
			FiltroLogradouro filtroLogradouro, Integer numeroPaginas)
			throws ControladorException {
		try {
			return repositorioEndereco.pesquisarLogradouro(filtroLogradouro,
					numeroPaginas);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Integer pesquisarLogradouroCount(FiltroLogradouro filtroLogradouro)
			throws ControladorException {
		try {
			return repositorioEndereco
					.pesquisarLogradouroCount(filtroLogradouro);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	// metodo que serve para fazer a pesquisa do logradouro
	// apartir dos parametros informados
	public Collection pesquisarLogradouroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ControladorException {
		try {
			return repositorioEndereco.pesquisarLogradouroCompleto(
					codigoMunicipio, codigoBairro, nome, nomePopular,
					logradouroTipo, logradouroTitulo, cep, codigoLogradouro,
					indicadorUso, tipoPesquisa, tipoPesquisaPopular,
					numeroPaginas);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Collection pesquisarLogradouroBairroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ControladorException {
		try {
			return repositorioEndereco.pesquisarLogradouroBairroCompleto(
					codigoMunicipio, codigoBairro, nome, nomePopular,
					logradouroTipo, logradouroTitulo, cep, codigoLogradouro,
					indicadorUso, tipoPesquisa, tipoPesquisaPopular,
					numeroPaginas);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	public Collection pesquisarLogradouroCompletoRelatorio(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo,
			String cep, String codigoLogradouro, String indicadorUso,
			String tipoPesquisa, String tipoPesquisaPopular)
			throws ControladorException {
		try {
			
			Collection colecaoLogradouroBairro = null;
			
			Collection colecaoLogradouro = repositorioEndereco.pesquisarLogradouroCompletoRelatorio(
					codigoMunicipio, codigoBairro, nome, nomePopular,
					logradouroTipo, logradouroTitulo, cep, codigoLogradouro,
					indicadorUso, tipoPesquisa, tipoPesquisaPopular);
			
			if (colecaoLogradouro != null){
				
				Iterator iterLogradouro = colecaoLogradouro.iterator();
				Logradouro logradouro = null;
				Bairro bairro = null;
				LogradouroBairro logBairro = null;
				colecaoLogradouroBairro = new ArrayList();
				
				while (iterLogradouro.hasNext()) {
					Object[] log =  (Object[])iterLogradouro.next();
					
					logradouro = new Logradouro();
					bairro = new Bairro();
					logBairro = new LogradouroBairro();
					
					logradouro = (Logradouro)log[0];
					if (log[1] != null){
						bairro = (Bairro)log[1];	
					}
					
					logBairro.setLogradouro(logradouro);
					logBairro.setBairro(bairro);
					
					colecaoLogradouroBairro.add(logBairro);
					
				}
				
			}
			
//			return repositorioEndereco.pesquisarLogradouroCompletoRelatorio(
//					codigoMunicipio, codigoBairro, nome, nomePopular,
//					logradouroTipo, logradouroTitulo, cep, codigoLogradouro,
//					indicadorUso, tipoPesquisa, tipoPesquisaPopular);
			
			return colecaoLogradouroBairro;

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Integer pesquisarLogradouroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ControladorException {
		try {
			return repositorioEndereco.pesquisarLogradouroCompletoCount(
					codigoMunicipio, codigoBairro, nome, nomePopular,
					logradouroTipo, logradouroTitulo, cep, codigoLogradouro,
					indicadorUso, tipoPesquisa, tipoPesquisaPopular);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	public Integer pesquisarLogradouroBairroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ControladorException {
		try {
			return repositorioEndereco.pesquisarLogradouroBairroCompletoCount(
					codigoMunicipio, codigoBairro, nome, nomePopular,
					logradouroTipo, logradouroTitulo, cep, codigoLogradouro,
					indicadorUso, tipoPesquisa, tipoPesquisaPopular);

		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	/**
	 * [UC0085] - Obter Endere�o Autor: Ana Maria
	 */

	public String pesquisarEnderecoRegistroAtendimentoFormatado(
			Integer idRegistroAtendimento) throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoRegistroAtendimentoFormatado(idRegistroAtendimento);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// idlogradouroCep e idlogradouroBairro
		if (arrayEndereco[18] != null && arrayEndereco[19] != null) {

			LogradouroCep logradouroCep = null;
			if (arrayEndereco[18] != null) {

				logradouroCep = new LogradouroCep();
				logradouroCep.setId((Integer) arrayEndereco[18]);

				// id do Logradouro
				Logradouro logradouro = null;
				if (arrayEndereco[17] != null) {
					logradouro = new Logradouro();
					logradouro.setId((Integer) arrayEndereco[17]);
					logradouro.setNome("" + arrayEndereco[0]);
				}

				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[1] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[1]);
				}

				// Descri��o abreviada logradouro tipo
				if (arrayEndereco[20] != null) {

					if (logradouroTipo == null) {
						logradouroTipo = new LogradouroTipo();
					}

					logradouroTipo
							.setDescricaoAbreviada("" + arrayEndereco[20]);
				}

				logradouro.setLogradouroTipo(logradouroTipo);

				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[2] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[2]);
				}

				// Descri��o Abreviada logradouro titulo
				if (arrayEndereco[21] != null) {

					if (logradouroTitulo == null) {
						logradouroTitulo = new LogradouroTitulo();
					}

					logradouroTitulo.setDescricaoAbreviada(""
							+ arrayEndereco[21]);
				}

				logradouro.setLogradouroTitulo(logradouroTitulo);

				// id do CEP
				Cep cep = null;
				if (arrayEndereco[9] != null) {
					cep = new Cep();
					cep.setCepId((Integer) arrayEndereco[9]);
					cep.setCodigo((Integer) arrayEndereco[15]);
				}

				logradouroCep.setLogradouro(logradouro);
				logradouroCep.setCep(cep);
			}

			registroAtendimento.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[19] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[19]);

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

					// id da unidade federa��o
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

			registroAtendimento.setLogradouroBairro(logradouroBairro);

			// complemento endere�o
			if (arrayEndereco[16] != null) {
				registroAtendimento.setComplementoEndereco(""
						+ arrayEndereco[16]);
			}

			// N�mero do Im�vel
			if (arrayEndereco[22] != null) {
				registroAtendimento.setNumeroImovel("" + arrayEndereco[22]);
			}
			
			// Per�metro
			if (arrayEndereco[23] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[23]);
				
				if (arrayEndereco[24] != null) {
					perimetroInicial.setNome((String) arrayEndereco[24]);
				}
				
				if (arrayEndereco[25] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[25]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[26] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[26]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimento.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[27] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[27]);
				
				if (arrayEndereco[28] != null) {
					perimetroFinal.setNome((String) arrayEndereco[28]);
				}
				
				if (arrayEndereco[29] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[29]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[30] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[30]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimento.setPerimetroFinal(perimetroFinal);
			}

			endereco = registroAtendimento.getEnderecoFormatado();

		}

		return endereco;
	}

	/**
	 * [UC0085] - Obter Endere�o Autor: Ana Maria
	 */

	public String pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(
			Integer idRegistroAtendimentoSolicitante)
			throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(idRegistroAtendimentoSolicitante);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = new RegistroAtendimentoSolicitante();

		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// idlogradouroCep e idlogradouroBairro
		if (arrayEndereco[18] != null && arrayEndereco[19] != null) {

			LogradouroCep logradouroCep = null;
			if (arrayEndereco[18] != null) {

				logradouroCep = new LogradouroCep();
				logradouroCep.setId((Integer) arrayEndereco[18]);

				// id do Logradouro
				Logradouro logradouro = null;
				if (arrayEndereco[17] != null) {
					logradouro = new Logradouro();
					logradouro.setId((Integer) arrayEndereco[17]);
					logradouro.setNome("" + arrayEndereco[0]);
				}
				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[1] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[1]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[2] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[2]);
					logradouro.setLogradouroTitulo(logradouroTitulo);
				}
				// id do CEP
				Cep cep = null;
				if (arrayEndereco[9] != null) {
					cep = new Cep();
					cep.setCepId((Integer) arrayEndereco[9]);
					cep.setCodigo((Integer) arrayEndereco[15]);
				}

				logradouroCep.setLogradouro(logradouro);
				logradouroCep.setCep(cep);
			}

			registroAtendimentoSolicitante.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[19] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[19]);

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

					// id da unidade federa��o
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

			registroAtendimentoSolicitante
					.setLogradouroBairro(logradouroBairro);

			// complemento endere�o
			if (arrayEndereco[16] != null) {
				registroAtendimentoSolicitante.setComplementoEndereco(""
						+ arrayEndereco[16]);
			}

			// N�mero do Im�vel
			if (arrayEndereco[22] != null) {
				registroAtendimentoSolicitante.setNumeroImovel(""
						+ arrayEndereco[22]);
			}
			
			// Per�metro
			if (arrayEndereco[23] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[23]);
				
				if (arrayEndereco[24] != null) {
					perimetroInicial.setNome((String) arrayEndereco[24]);
				}
				
				if (arrayEndereco[25] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[25]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[26] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[26]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimentoSolicitante.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[27] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[27]);
				
				if (arrayEndereco[28] != null) {
					perimetroFinal.setNome((String) arrayEndereco[28]);
				}
				
				if (arrayEndereco[29] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[29]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[30] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[30]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimentoSolicitante.setPerimetroFinal(perimetroFinal);
			}

			endereco = registroAtendimentoSolicitante.getEnderecoFormatado();

		}

		return endereco;
	}

	/**
	 * Obter o objeto de registro atendimento para recuperar Endere�o Autor:
	 * S�vio Luiz
	 */

	public RegistroAtendimento pesquisarRegistroAtendimentoEndereco(
			Integer idRegistroAtendimento) throws ControladorException {
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoRegistroAtendimentoFormatado(idRegistroAtendimento);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// idlogradouroCep e idlogradouroBairro
		if (arrayEndereco[18] != null && arrayEndereco[19] != null) {

			LogradouroCep logradouroCep = null;
			if (arrayEndereco[18] != null) {

				logradouroCep = new LogradouroCep();
				logradouroCep.setId((Integer) arrayEndereco[18]);

				// id do Logradouro
				Logradouro logradouro = null;
				if (arrayEndereco[17] != null) {
					logradouro = new Logradouro();
					logradouro.setId((Integer) arrayEndereco[17]);
					logradouro.setNome("" + arrayEndereco[0]);
				}
				LogradouroTipo logradouroTipo = null;
				// Descri��o abreviada logradouro tipo
				if (arrayEndereco[1] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[1]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o abreviada logradouro titulo
				if (arrayEndereco[2] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[2]);
					logradouro.setLogradouroTitulo(logradouroTitulo);
				}
				// id do CEP
				Cep cep = null;
				if (arrayEndereco[9] != null) {
					cep = new Cep();
					cep.setCepId((Integer) arrayEndereco[9]);
					cep.setCodigo((Integer) arrayEndereco[15]);
				}

				logradouroCep.setLogradouro(logradouro);
				logradouroCep.setCep(cep);
			}

			registroAtendimento.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[19] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[19]);

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

					// id da unidade federa��o
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

			registroAtendimento.setLogradouroBairro(logradouroBairro);

			// complemento endere�o
			if (arrayEndereco[16] != null) {
				registroAtendimento.setComplementoEndereco(""
						+ arrayEndereco[16]);
			}
			
			// Per�metro
			if (arrayEndereco[23] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[23]);
				
				if (arrayEndereco[24] != null) {
					perimetroInicial.setNome((String) arrayEndereco[24]);
				}
				
				if (arrayEndereco[25] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[25]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[26] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[26]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimento.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[27] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[27]);
				
				if (arrayEndereco[28] != null) {
					perimetroFinal.setNome((String) arrayEndereco[28]);
				}
				
				if (arrayEndereco[29] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[29]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[30] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[30]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimento.setPerimetroFinal(perimetroFinal);
			}

		}

		return registroAtendimento;
	}

	/**
	 * Obter os parametros de logradouroCep para o endere�o Autor: S�vio Luiz
	 */

	public LogradouroCep pesquisarLogradouroCepEndereco(Integer idLogradouroCep)
			throws ControladorException {
		Collection colecaoLogradouroCepEndereco = null;
		try {
			colecaoLogradouroCepEndereco = repositorioEndereco
					.obterDadosLogradouroCepEndereco(idLogradouroCep);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		LogradouroCep logradouroCep = new LogradouroCep();
		logradouroCep.setId(idLogradouroCep);

		Iterator enderecoIterator = colecaoLogradouroCepEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// id do Logradouro
		Logradouro logradouro = null;
		if (arrayEndereco[0] != null) {
			logradouro = new Logradouro();
			logradouro.setId((Integer) arrayEndereco[0]);
			logradouro.setNome((String) arrayEndereco[1]);
		}
		LogradouroTipo logradouroTipo = null;
		// Descri��o logradouro tipo
		if (arrayEndereco[2] != null) {
			logradouroTipo = new LogradouroTipo();
			logradouroTipo.setDescricaoAbreviada("" + arrayEndereco[2]);
			logradouro.setLogradouroTipo(logradouroTipo);
		}
		LogradouroTitulo logradouroTitulo = null;
		// Descri��o logradouro titulo
		if (arrayEndereco[3] != null) {
			logradouroTitulo = new LogradouroTitulo();
			logradouroTitulo.setDescricaoAbreviada("" + arrayEndereco[3]);
			logradouro.setLogradouroTitulo(logradouroTitulo);
		}
		// id do CEP
		Cep cep = null;
		if (arrayEndereco[4] != null) {
			cep = new Cep();
			cep.setCepId((Integer) arrayEndereco[4]);
			cep.setCodigo((Integer) arrayEndereco[5]);
		}

		logradouroCep.setLogradouro(logradouro);
		logradouroCep.setCep(cep);

		return logradouroCep;
	}

	/**
	 * Obter os parametros de logradouroBairro para o endere�o Autor: S�vio Luiz
	 */

	public LogradouroBairro pesquisarLogradouroBairroEndereco(
			Integer idLogradouroBairro) throws ControladorException {
		Collection colecaoLogradouroCepEndereco = null;
		try {
			colecaoLogradouroCepEndereco = repositorioEndereco
					.obterDadosLogradouroBairroEndereco(idLogradouroBairro);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		LogradouroBairro logradouroBairro = new LogradouroBairro();
		logradouroBairro.setId(idLogradouroBairro);

		Iterator enderecoIterator = colecaoLogradouroCepEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// id do Logradouro
		Bairro bairro = null;
		if (arrayEndereco[0] != null) {
			bairro = new Bairro();
			bairro.setId((Integer) arrayEndereco[0]);
			bairro.setNome((String) arrayEndereco[1]);
		}
		Municipio municipio = null;
		// Descri��o logradouro tipo
		if (arrayEndereco[2] != null) {
			municipio = new Municipio();
			municipio.setId((Integer) arrayEndereco[2]);
			municipio.setNome((String) arrayEndereco[3]);
		}
		UnidadeFederacao unidadeFederacao = null;
		// Descri��o logradouro tipo
		if (arrayEndereco[4] != null) {
			unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId((Integer) arrayEndereco[4]);
			unidadeFederacao.setSigla((String) arrayEndereco[5]);
		}

		municipio.setUnidadeFederacao(unidadeFederacao);
		bairro.setMunicipio(municipio);
		logradouroBairro.setBairro(bairro);

		return logradouroBairro;
	}

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
			throws ControladorException {

		Collection<String> colecaoClienteEndereco = null;

		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarClientesEnderecosAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		ClienteEndereco clienteEndereco = new ClienteEndereco();
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Iterator enderecoIterator = colecaoEndereco.iterator();

			colecaoClienteEndereco = new ArrayList();
			while (enderecoIterator.hasNext()) {
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
					// Descri��o logradouro tipo
					if (arrayEndereco[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada(""
								+ arrayEndereco[22]);
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o logradouro titulo
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

						// id da unidade federa��o
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

				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricaoAbreviada(""
							+ arrayEndereco[9]);
					clienteEndereco.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					clienteEndereco.setNumero("" + arrayEndereco[17]);
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					clienteEndereco.setComplemento("" + arrayEndereco[18]);
				}else{
					clienteEndereco.setComplemento(null);
				}
				
				// Per�metro
				if (arrayEndereco[25] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[25]);
					
					if (arrayEndereco[26] != null) {
						perimetroInicial.setNome((String) arrayEndereco[26]);
					}
					
					if (arrayEndereco[27] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[27]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[28] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[28]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					clienteEndereco.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[29] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[29]);
					
					if (arrayEndereco[30] != null) {
						perimetroFinal.setNome((String) arrayEndereco[30]);
					}
					
					if (arrayEndereco[31] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[31]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[32] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[32]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					clienteEndereco.setPerimetroFinal(perimetroFinal);
				}

				endereco = clienteEndereco.getEnderecoFormatadoAbreviado();

				colecaoClienteEndereco.add(endereco);
			}
		}

		return colecaoClienteEndereco;

	}

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
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoImovel(Integer idImovel)
			throws ControladorException {
		Object[] dadosEndereco = null;
		try {
			dadosEndereco = repositorioEndereco
					.obterEnderecoAbreviadoImovel(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		String endereco = "";

		if (dadosEndereco != null) {

			// Nome do Logradouro
			if (dadosEndereco[0] != null) {
				endereco = endereco + dadosEndereco[0].toString().trim();
			}

			// N�mero do Im�vel
			if (dadosEndereco[1] != null) {
				endereco = endereco + ", " + dadosEndereco[1].toString().trim();
			}

			// Nome do Bairro
			if (dadosEndereco[2] != null) {
				endereco = endereco + " - "
						+ dadosEndereco[2].toString().trim();
			}

		}

		return endereco;
	}

	/**
	 * 
	 * Pesquisar o endere�o abreviado a partir do id do RA
	 * 
	 * [UC0483] - Obter Endere�o Abreviado
	 * 
	 * @author Rafael Corr�a
	 * @date 18/10/2006
	 * 
	 * @param idImovel
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoRA(Integer idRA)
			throws ControladorException {
		Object[] dadosEndereco = null;
		try {
			dadosEndereco = repositorioEndereco.obterEnderecoAbreviadoRA(idRA);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		String endereco = "";

		if (dadosEndereco != null) {

			// Nome do Logradouro
			if (dadosEndereco[0] != null) {
				endereco = endereco + dadosEndereco[0].toString().trim();
			}

			// N�mero do Im�vel
			if (dadosEndereco[1] != null) {
				endereco = endereco + ", " + dadosEndereco[1].toString().trim();
			}

			// Nome do Bairro
			if (dadosEndereco[2] != null) {
				endereco = endereco + " - "
						+ dadosEndereco[2].toString().trim();
			}

		}

		return endereco;
	}

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
	public String[] pesquisarEnderecoFormatadoClienteDividido(Integer idCliente)
			throws ControladorException {

		// 0 - Endere�o sem municipio e UF
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		String[] parmsEndereco = new String[5];
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";

		try {

			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoFormatadoCliente(idCliente);

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Iterator enderecoIterator = colecaoEndereco.iterator();
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

			if (arrayEndereco[20] != null && arrayEndereco[21] != null) {

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
					// Descri��o abreviada logradouro tipo
					if (arrayEndereco[1] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[1]);
						if (arrayEndereco[22] != null) {
							logradouroTipo.setDescricaoAbreviada(""
									+ arrayEndereco[22]);
						}
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o abreviada logradouro titulo
					if (arrayEndereco[2] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[2]);
						if (arrayEndereco[23] != null) {
							logradouroTitulo.setDescricaoAbreviada(""
									+ arrayEndereco[23]);
						}
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					if (arrayEndereco[10] != null) {
						cepFormatado = "" + (Integer) arrayEndereco[16];
					}

					logradouroCep.setLogradouro(logradouro);
				}

				imovel.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (arrayEndereco[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayEndereco[21]);

					// nome bairro
					if (arrayEndereco[3] != null) {
						nomeBairro = "" + arrayEndereco[4];
					}

					// nome municipio
					if (arrayEndereco[5] != null) {
						nomeMunicipio = "" + arrayEndereco[6];

						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							siglaUnidadeFederacao = "" + arrayEndereco[8];
						}

					}
				}

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[9]);
					if (arrayEndereco[24] != null) {
						enderecoReferencia.setDescricaoAbreviada(""
								+ arrayEndereco[24]);
					}
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					imovel.setNumeroImovel("" + arrayEndereco[17]);
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					imovel.setComplementoEndereco("" + arrayEndereco[18]);
				}
				
				// Per�metro
				/*if (arrayEndereco[29] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[29]);
					
					if (arrayEndereco[30] != null) {
						perimetroInicial.setNome((String) arrayEndereco[30]);
					}
					
					if (arrayEndereco[31] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[31]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[32] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[32]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[33] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[33]);
					
					if (arrayEndereco[34] != null) {
						perimetroFinal.setNome((String) arrayEndereco[34]);
					}
					
					if (arrayEndereco[35] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[35]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[36] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[36]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroFinal(perimetroFinal);
				}*/

				endereco = imovel.getEnderecoFormatadoAbreviado();

			}
		}

		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;

		return parmsEndereco;

	}

	/**
	 * [UC0210] - Obter Endere�o Autor: S�vio Luiz
	 */

	public String pesquisarEnderecoAbreviadoCAER(Integer idImovel)
			throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoAbreviadoCAER(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		Iterator enderecoIterator = colecaoEndereco.iterator();
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

			if (arrayEndereco[20] != null && arrayEndereco[21] != null) {

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
					// Descri��o abreviada logradouro tipo
					if (arrayEndereco[1] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[1]);
						if (arrayEndereco[22] != null) {
							logradouroTipo.setDescricaoAbreviada(""
									+ arrayEndereco[22]);
						}
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o abreviada logradouro titulo
					if (arrayEndereco[2] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[2]);
						if (arrayEndereco[23] != null) {
							logradouroTitulo.setDescricaoAbreviada(""
									+ arrayEndereco[23]);
						}
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					// id do CEP
					Cep cep = null;

					logradouroCep.setLogradouro(logradouro);
					logradouroCep.setCep(cep);
				}

				imovel.setLogradouroCep(logradouroCep);

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

						// id da unidade federa��o
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

				imovel.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[9]);
					if (arrayEndereco[24] != null) {
						enderecoReferencia.setDescricaoAbreviada(""
								+ arrayEndereco[24]);
					}
					imovel.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					imovel.setNumeroImovel("" + arrayEndereco[17]);
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					imovel.setComplementoEndereco("" + arrayEndereco[18]);
				}

				endereco = imovel.getEnderecoFormatadoAbreviado();

			} else {

				FiltroImovelEnderecoAnterior filtroImovelEnderecoAnterior = new FiltroImovelEnderecoAnterior();

				filtroImovelEnderecoAnterior
						.adicionarParametro(new ParametroSimples(
								FiltroImovelEnderecoAnterior.ID, idImovel));

				Collection colecaoImovelEnderecoAnterior = this
						.getControladorUtil().pesquisar(
								filtroImovelEnderecoAnterior,
								ImovelEnderecoAnterior.class.getName());

				if (colecaoImovelEnderecoAnterior != null
						&& !colecaoImovelEnderecoAnterior.isEmpty()) {

					ImovelEnderecoAnterior imovelEnderecoAnterior = (ImovelEnderecoAnterior) colecaoImovelEnderecoAnterior
							.iterator().next();

					endereco = imovelEnderecoAnterior.getEnderecoAnterior();
				}
			}
		}

		return endereco;
	}

	/**
	 * 
	 * Pesquisar o cep pelo codigo do cep
	 * 
	 * @author S�vio Luiz
	 * @date 05/11/2007
	 * 
	 */

	public Cep pesquisarCep(Integer codigoCep) throws ControladorException {

		try {
			return repositorioEndereco.pesquisarCep(codigoCep);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Verifica a exist�ncia do endere�o de correspond�ncia do cliente pelo seu id 
	 * 
	 */
	public boolean verificarExistenciaClienteEndereco(Integer idCliente)
		throws ControladorException {

		try {
			return repositorioEndereco.verificarExistenciaClienteEndereco(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	
	
	/**
	 * [UC0085] - Obter Endere�o Autor:Yara Taciane
	 */

	public String pesquisarBairroLogradouroRegistroAtendimentoSolicitante(
			Integer idRegistroAtendimentoSolicitante)
			throws ControladorException {
		String  endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(idRegistroAtendimentoSolicitante);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	
		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		endereco = arrayEndereco[4] + " - " + arrayEndereco[1] + " " + arrayEndereco[0] + " - " + "N." + arrayEndereco[22];
	
		return endereco;
	}
	
	/**
	 * Retorna a cole��o de Logradouro Tipos presentes na tabela CEP 
	 * 
	 * @author: Vinicius Medeiros 
	 */
	public Collection retornaTipoLogradouroPeloCep()
			throws ControladorException {
		
		Collection colecaoLogradouroTipo = null;
		Collection retorno = new ArrayList();
		
		try{
			colecaoLogradouroTipo = repositorioEndereco.retornaTipoLogradouroPeloCep();
			
			Iterator listaLogradouroTipo = colecaoLogradouroTipo.iterator();
			
			Cep cep = null;
			
			while (listaLogradouroTipo.hasNext()) {
				String objeto = (String) listaLogradouroTipo.next();
				
				cep = new Cep();
				
				cep.setDescricaoTipoLogradouro(objeto);
			
				retorno.add(cep);
			}
			
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	
		return retorno;
		
	}
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(
			Integer idCliente) throws ControladorException {

		Collection retorno = new ArrayList();

		try {
			retorno = repositorioEndereco
					.pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(idCliente);

		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;

	}
	
	/**
	 * Obter Logradouro(Tipo + T�tulo + Nome Logradouro)
	 */
	
	public Collection pesquisarLogradouro(Integer idImovel)
		throws ControladorException {
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco.pesquisarLogradouro(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoEndereco;
	}
	
	/**
	 * Obter Logradouro(Tipo + T�tulo + Nome Logradouro)
	 */
	
	public Collection pesquisarLogradouroCliente(Integer idCliente)
		throws ControladorException {
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco.pesquisarLogradouroCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		return colecaoEndereco;
	}
	
	public String[] pesquisarEnderecoClienteAbreviadoDivididoCosanpa(Integer idCliente)
		throws ControladorException {
		// 0 - Endere�o sem municipio e UF
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		// 5 - LogradouroCep
		// 6 - LogradouroBairro
		String[] parmsEndereco = new String[7];
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";
		String logCep = "";
		String logBairro = "";
		
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoClienteAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		ClienteEndereco clienteEndereco = new ClienteEndereco();
		
		Iterator enderecoIterator = colecaoEndereco.iterator();
		while (enderecoIterator.hasNext()) {
			// cria um array de objetos para pegar os parametros de
			// retorno da pesquisa
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();
		
			LogradouroCep logradouroCep = null;
			if (arrayEndereco[20] != null) {
		
				logradouroCep = new LogradouroCep();
				logradouroCep.setId((Integer) arrayEndereco[20]);
				
				logCep = logradouroCep.getId()+"";
				
				// id do Logradouro
				Logradouro logradouro = null;
				if (arrayEndereco[19] != null) {
					logradouro = new Logradouro();
					logradouro.setId((Integer) arrayEndereco[19]);
					logradouro.setNome("" + arrayEndereco[0]);
		
					// nome municipio
					if (arrayEndereco[5] != null) {
						nomeMunicipio = "" + arrayEndereco[6];
		
						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							siglaUnidadeFederacao = "" + arrayEndereco[8];
		
						}
		
					}
				}
				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[22] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo
							.setDescricaoAbreviada("" + arrayEndereco[22]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[23] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada(""
							+ arrayEndereco[23]);
					logradouro.setLogradouroTitulo(logradouroTitulo);
				}
				if (arrayEndereco[10] != null) {
					cepFormatado = "" + (Integer) arrayEndereco[16];
				}
		
				logradouroCep.setLogradouro(logradouro);
			}
		
			clienteEndereco.setLogradouroCep(logradouroCep);
		
			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[21] != null) {
		
				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[21]);
				
				logBairro = logradouroBairro.getId()+"";
		
				// nome bairro
				if (arrayEndereco[3] != null) {
					nomeBairro = "" + arrayEndereco[4];
				}
		
				// nome municipio
				if (arrayEndereco[25] != null) {
					nomeMunicipio = "" + arrayEndereco[26];
		
					// id da unidade federa��o
					if (arrayEndereco[27] != null) {
						siglaUnidadeFederacao = "" + arrayEndereco[28];
					}
				}
		
			}
		
			clienteEndereco.setLogradouroBairro(logradouroBairro);
		
			// descricao de endere�o refer�ncia
			if (arrayEndereco[9] != null) {
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setDescricaoAbreviada("" + arrayEndereco[9]);
				clienteEndereco.setEnderecoReferencia(enderecoReferencia);
			}
		
			// numero imovel
			if (arrayEndereco[17] != null) {
				clienteEndereco.setNumero("" + arrayEndereco[17]);
			}
		
			// complemento endere�o
			if (arrayEndereco[18] != null) {
				clienteEndereco.setComplemento("" + arrayEndereco[18]);
			}
			
			// Per�metro
			if (arrayEndereco[29] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[29]);
				
				if (arrayEndereco[30] != null) {
					perimetroInicial.setNome((String) arrayEndereco[30]);
				}
				
				if (arrayEndereco[31] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[31]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[32] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[32]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				clienteEndereco.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[33] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[33]);
				
				if (arrayEndereco[34] != null) {
					perimetroFinal.setNome((String) arrayEndereco[34]);
				}
				
				if (arrayEndereco[35] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[35]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[36] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[36]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				clienteEndereco.setPerimetroFinal(perimetroFinal);
			}
		
			endereco = clienteEndereco.getEnderecoFormatadoAbreviado();
		}
		
		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;
		parmsEndereco[5] = logCep;
		parmsEndereco[6] = logBairro;
		
		return parmsEndereco;
	}
	public String[] pesquisarEnderecoFormatadoDivididoCosanpa(Integer idImovel)
			throws ControladorException {
		
		// 0 - Endere�o sem municipio e UF
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		// 5 - LogradouroCep
		// 6 - LogradouroBairro
		String[] parmsEndereco = new String[7];
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";
		String logCep = "";
		String logBairro = "";
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoFormatado(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
		
		Imovel imovel = new Imovel();
		
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Iterator enderecoIterator = colecaoEndereco.iterator();
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();
		
			if (arrayEndereco[20] != null && arrayEndereco[21] != null) {
		
				LogradouroCep logradouroCep = null;
				if (arrayEndereco[20] != null) {
		
					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) arrayEndereco[20]);
					logCep = logradouroCep.getId()+"";
		
					// id do Logradouro
					Logradouro logradouro = null;
					if (arrayEndereco[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) arrayEndereco[19]);
						logradouro.setNome("" + arrayEndereco[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// Descri��o abreviada logradouro tipo
					if (arrayEndereco[1] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[1]);
						if (arrayEndereco[22] != null) {
							logradouroTipo.setDescricaoAbreviada(""
									+ arrayEndereco[22]);
						}
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// Descri��o abreviada logradouro titulo
					if (arrayEndereco[2] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[2]);
						if (arrayEndereco[23] != null) {
							logradouroTitulo.setDescricaoAbreviada(""
									+ arrayEndereco[23]);
						}
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					if (arrayEndereco[10] != null) {
						cepFormatado = "" + (Integer) arrayEndereco[16];
					}
		
					logradouroCep.setLogradouro(logradouro);
				}
		
				imovel.setLogradouroCep(logradouroCep);
		
				LogradouroBairro logradouroBairro = null;
				if (arrayEndereco[21] != null) {
		
					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayEndereco[21]);
					logBairro = logradouroBairro.getId()+"";
		
					// nome bairro
					if (arrayEndereco[3] != null) {
						nomeBairro = "" + arrayEndereco[4];
					}
		
					// nome municipio
					if (arrayEndereco[5] != null) {
						nomeMunicipio = "" + arrayEndereco[6];
		
						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							siglaUnidadeFederacao = "" + arrayEndereco[8];
						}
		
					}
				}
		
				imovel.setLogradouroBairro(logradouroBairro);
		
				// descricao de endere�o refer�ncia
				if (arrayEndereco[9] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[9]);
					if (arrayEndereco[24] != null) {
						enderecoReferencia.setDescricaoAbreviada(""
								+ arrayEndereco[24]);
					}
					imovel.setEnderecoReferencia(enderecoReferencia);
				}
		
				// numero imovel
				if (arrayEndereco[17] != null) {
					imovel.setNumeroImovel("" + arrayEndereco[17]);
				}
		
				// complemento endere�o
				if (arrayEndereco[18] != null) {
					imovel.setComplementoEndereco("" + arrayEndereco[18]);
				}
		
				// Per�metro
				if (arrayEndereco[26] != null) {
					Logradouro perimetroInicial = new Logradouro();
					perimetroInicial.setId((Integer) arrayEndereco[26]);
					
					if (arrayEndereco[27] != null) {
						perimetroInicial.setNome((String) arrayEndereco[27]);
					}
					
					if (arrayEndereco[28] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[28]);
						perimetroInicial.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[29] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[29]);
						perimetroInicial.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroInicial(perimetroInicial);
				}
				
				if (arrayEndereco[30] != null) {
					Logradouro perimetroFinal = new Logradouro();
					perimetroFinal.setId((Integer) arrayEndereco[30]);
					
					if (arrayEndereco[31] != null) {
						perimetroFinal.setNome((String) arrayEndereco[31]);
					}
					
					if (arrayEndereco[32] != null) {
						LogradouroTipo logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[32]);
						perimetroFinal.setLogradouroTipo(logradouroTipo);
					}
					
					if (arrayEndereco[33] != null) {
						LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[33]);
						perimetroFinal.setLogradouroTitulo(logradouroTitulo);
					}
					
					imovel.setPerimetroFinal(perimetroFinal);
				}
				
				endereco = imovel.getEnderecoFormatadoAbreviado();
		
			}
		}
		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;
		parmsEndereco[5] = logCep;
		parmsEndereco[6] = logBairro;
		
		return parmsEndereco;
		
	}
	
	
	/**
	 * [UC0085] - Obter Endere�o Autor: Ana Maria
	 */

	public String pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(
			Integer idRegistroAtendimento) throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoRegistroAtendimentoFormatado(idRegistroAtendimento);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// idlogradouroCep e idlogradouroBairro
		if (arrayEndereco[18] != null && arrayEndereco[19] != null) {

			LogradouroCep logradouroCep = null;
			if (arrayEndereco[18] != null) {

				logradouroCep = new LogradouroCep();
				logradouroCep.setId((Integer) arrayEndereco[18]);

				// id do Logradouro
				Logradouro logradouro = null;
				if (arrayEndereco[17] != null) {
					logradouro = new Logradouro();
					logradouro.setId((Integer) arrayEndereco[17]);
					logradouro.setNome("" + arrayEndereco[0]);
				}

				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[1] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[1]);
				}

				// Descri��o abreviada logradouro tipo
				if (arrayEndereco[20] != null) {

					if (logradouroTipo == null) {
						logradouroTipo = new LogradouroTipo();
					}

					logradouroTipo
							.setDescricaoAbreviada("" + arrayEndereco[20]);
				}

				logradouro.setLogradouroTipo(logradouroTipo);

				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[2] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[2]);
				}

				// Descri��o Abreviada logradouro titulo
				if (arrayEndereco[21] != null) {

					if (logradouroTitulo == null) {
						logradouroTitulo = new LogradouroTitulo();
					}

					logradouroTitulo.setDescricaoAbreviada(""
							+ arrayEndereco[21]);
				}

				logradouro.setLogradouroTitulo(logradouroTitulo);

				// id do CEP
				Cep cep = null;
				if (arrayEndereco[9] != null) {
					cep = new Cep();
					cep.setCepId((Integer) arrayEndereco[9]);
					cep.setCodigo((Integer) arrayEndereco[15]);
				}

				logradouroCep.setLogradouro(logradouro);
				logradouroCep.setCep(cep);
			}

			registroAtendimento.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[19] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[19]);

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

					// id da unidade federa��o
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

			registroAtendimento.setLogradouroBairro(logradouroBairro);

			// complemento endere�o
			if (arrayEndereco[16] != null) {
				registroAtendimento.setComplementoEndereco(""
						+ arrayEndereco[16]);
			}

			// N�mero do Im�vel
			if (arrayEndereco[22] != null) {
				registroAtendimento.setNumeroImovel("" + arrayEndereco[22]);
			}
			
			// Per�metro
			if (arrayEndereco[23] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[23]);
				
				if (arrayEndereco[24] != null) {
					perimetroInicial.setNome((String) arrayEndereco[24]);
				}
				
				if (arrayEndereco[25] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[25]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[26] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[26]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimento.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[27] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[27]);
				
				if (arrayEndereco[28] != null) {
					perimetroFinal.setNome((String) arrayEndereco[28]);
				}
				
				if (arrayEndereco[29] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[29]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[30] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[30]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				registroAtendimento.setPerimetroFinal(perimetroFinal);
			}

			endereco = registroAtendimento.getEnderecoFormatadoIniciadoPeloBairro();

		}

		return endereco;
	}


	/**
	 * [UC0085] Obter Endere�o
	 * 
	 * @author Vivianne Sousa
	 * @data 17/05/2011
	 */
	public String pesquisarEnderecoSolicitanteRAReiteracaoFormatado(
			Integer idraReiteracao)	throws ControladorException {
		String endereco = null;
		Collection colecaoEndereco = null;
		try {
			colecaoEndereco = repositorioEndereco.pesquisarEnderecoSolicitanteRAReiteracaoFormatado(idraReiteracao);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		RAReiteracao raReiteracao = new RAReiteracao();

		Iterator enderecoIterator = colecaoEndereco.iterator();

		Object[] arrayEndereco = (Object[]) enderecoIterator.next();

		// idlogradouroCep e idlogradouroBairro
		if (arrayEndereco[18] != null && arrayEndereco[19] != null) {

			LogradouroCep logradouroCep = null;
			if (arrayEndereco[18] != null) {

				logradouroCep = new LogradouroCep();
				logradouroCep.setId((Integer) arrayEndereco[18]);

				// id do Logradouro
				Logradouro logradouro = null;
				if (arrayEndereco[17] != null) {
					logradouro = new Logradouro();
					logradouro.setId((Integer) arrayEndereco[17]);
					logradouro.setNome("" + arrayEndereco[0]);
				}
				LogradouroTipo logradouroTipo = null;
				// Descri��o logradouro tipo
				if (arrayEndereco[1] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[1]);
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o logradouro titulo
				if (arrayEndereco[2] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[2]);
					logradouro.setLogradouroTitulo(logradouroTitulo);
				}
				// id do CEP
				Cep cep = null;
				if (arrayEndereco[9] != null) {
					cep = new Cep();
					cep.setCepId((Integer) arrayEndereco[9]);
					cep.setCodigo((Integer) arrayEndereco[15]);
				}

				logradouroCep.setLogradouro(logradouro);
				logradouroCep.setCep(cep);
			}

			raReiteracao.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[19] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[19]);

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

					// id da unidade federa��o
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

			raReiteracao
					.setLogradouroBairro(logradouroBairro);

			// complemento endere�o
			if (arrayEndereco[16] != null) {
				raReiteracao.setComplementoEndereco(""
						+ arrayEndereco[16]);
			}

			// N�mero do Im�vel
			if (arrayEndereco[22] != null) {
				raReiteracao.setNumeroImovel((Integer) arrayEndereco[22]);
			}
			
			// Per�metro
			if (arrayEndereco[23] != null) {
				Logradouro perimetroInicial = new Logradouro();
				perimetroInicial.setId((Integer) arrayEndereco[23]);
				
				if (arrayEndereco[24] != null) {
					perimetroInicial.setNome((String) arrayEndereco[24]);
				}
				
				if (arrayEndereco[25] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[25]);
					perimetroInicial.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[26] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[26]);
					perimetroInicial.setLogradouroTitulo(logradouroTitulo);
				}
				
				raReiteracao.setPerimetroInicial(perimetroInicial);
			}
			
			if (arrayEndereco[27] != null) {
				Logradouro perimetroFinal = new Logradouro();
				perimetroFinal.setId((Integer) arrayEndereco[27]);
				
				if (arrayEndereco[28] != null) {
					perimetroFinal.setNome((String) arrayEndereco[28]);
				}
				
				if (arrayEndereco[29] != null) {
					LogradouroTipo logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricaoAbreviada((String) arrayEndereco[29]);
					perimetroFinal.setLogradouroTipo(logradouroTipo);
				}
				
				if (arrayEndereco[30] != null) {
					LogradouroTitulo logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricaoAbreviada((String) arrayEndereco[30]);
					perimetroFinal.setLogradouroTitulo(logradouroTitulo);
				}
				
				raReiteracao.setPerimetroFinal(perimetroFinal);
			}

			endereco = raReiteracao.getEnderecoFormatado();

		}

		return endereco;
	}

	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 20/05/2011
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(
			Integer idCliente) throws ControladorException {

			Collection retorno = new ArrayList();

			try {
				retorno = repositorioEndereco
						.pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(idCliente);

			} catch (ErroRepositorioException e) {
				e.printStackTrace();
			}
			return retorno;

		}
	
	 /**
	 * [UC0671] - Gerar Movimento Inclus�o de Negativa��o
	 * Ajuste do endere�o do arquivo ([SB0009]Gerar Registro Tipo Detalhe -D1.09)
	 * Autor: Ana Maria 12/07/2011
	 */

	public String[] pesquisarEnderecoClienteDividido(Integer idCliente)
			throws ControladorException {
		// 0 - Endere�o (Tipo + Titulo + Logradouro)
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		// 5 - numero
		// 6 - complemento
		String[] parmsEndereco = new String[7];
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";
		String numero = "";
		String complemento = "";
		try {
			colecaoEndereco = repositorioEndereco
					.pesquisarEnderecoClienteAbreviado(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		ClienteEndereco clienteEndereco = new ClienteEndereco();
		
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Iterator enderecoIterator = colecaoEndereco.iterator();
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

				LogradouroCep logradouroCep = null;
				if (arrayEndereco[20] != null) {

					logradouroCep = new LogradouroCep();
					logradouroCep.setId((Integer) arrayEndereco[20]);

					// Logradouro
					Logradouro logradouro = null;
					if (arrayEndereco[19] != null) {
						logradouro = new Logradouro();
						logradouro.setId((Integer) arrayEndereco[19]);
						logradouro.setNome("" + arrayEndereco[0]);
					}
					LogradouroTipo logradouroTipo = null;
					// logradouro tipo
					if (arrayEndereco[22] != null) {
						logradouroTipo = new LogradouroTipo();
						logradouroTipo.setDescricao("" + arrayEndereco[22]);
						if (arrayEndereco[1] != null) {
							logradouroTipo.setDescricaoAbreviada(""
									+ arrayEndereco[1]);
						}
						logradouro.setLogradouroTipo(logradouroTipo);
					}
					LogradouroTitulo logradouroTitulo = null;
					// logradouro titulo
					if (arrayEndereco[23] != null) {
						logradouroTitulo = new LogradouroTitulo();
						logradouroTitulo.setDescricao("" + arrayEndereco[23]);
						if (arrayEndereco[2] != null) {
							logradouroTitulo.setDescricaoAbreviada(""
									+ arrayEndereco[2]);
						}
						logradouro.setLogradouroTitulo(logradouroTitulo);
					}
					if (arrayEndereco[10] != null) {
						cepFormatado = "" + (Integer) arrayEndereco[16];
					}

					logradouroCep.setLogradouro(logradouro);
				}

				clienteEndereco.setLogradouroCep(logradouroCep);

				LogradouroBairro logradouroBairro = null;
				if (arrayEndereco[21] != null) {

					logradouroBairro = new LogradouroBairro();
					logradouroBairro.setId((Integer) arrayEndereco[21]);

					// nome bairro
					if (arrayEndereco[3] != null) {
						nomeBairro = "" + arrayEndereco[4];
					}

					// nome municipio
					if (arrayEndereco[5] != null) {
						nomeMunicipio = "" + arrayEndereco[6];

						// id da unidade federa��o
						if (arrayEndereco[7] != null) {
							siglaUnidadeFederacao = "" + arrayEndereco[8];
						}

					}
				}

				clienteEndereco.setLogradouroBairro(logradouroBairro);

				// descricao de endere�o refer�ncia
				if (arrayEndereco[24] != null) {
					EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
					enderecoReferencia.setDescricao("" + arrayEndereco[9]);
					if (arrayEndereco[9] != null) {
						enderecoReferencia.setDescricaoAbreviada(""
								+ arrayEndereco[9]);
					}
					clienteEndereco.setEnderecoReferencia(enderecoReferencia);
				}

				// numero imovel
				if (arrayEndereco[17] != null) {
					numero = "" + (String) arrayEndereco[17];
					
				}

				// complemento endere�o
				if (arrayEndereco[18] != null) {
					complemento = "" + (String) arrayEndereco[18];
				}
				
				endereco = clienteEndereco.getEnderecoTipoTituloLogradouro();

		}
		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;
		parmsEndereco[5] = numero;
		parmsEndereco[6] = complemento;

		return parmsEndereco;

	}

	 /**
	 * [UC0671] - Gerar Movimento Inclus�o de Negativa��o
	 * Ajuste do endere�o do arquivo ([SB0009]Gerar Registro Tipo Detalhe -D1.09)
	 * Autor: Ana Maria 12/07/2011
	 */
	public String[] pesquisarEnderecoImovelDividido(Integer idImovel)
			throws ControladorException {
		// 0 - Endere�o (Tipo + Titulo + Logradouro)
		// 1 - municipio
		// 2 - unidade federe��o
		// 3 - bairro
		// 4 - CEP
		// 5 - numero
		// 6 - complemento
		String[] parmsEndereco = new String[7];
		Collection colecaoEndereco = null;
		String endereco = "";
		String nomeMunicipio = "";
		String siglaUnidadeFederacao = "";
		String nomeBairro = "";
		String cepFormatado = "";
		String numero = "";
		String complemento = "";
		try {
			
			colecaoEndereco = repositorioEndereco.pesquisarEnderecoFormatado(idImovel);
			
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Iterator enderecoIterator = colecaoEndereco.iterator();
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
				// Descri��o abreviada logradouro tipo
				if (arrayEndereco[1] != null) {
					logradouroTipo = new LogradouroTipo();
					logradouroTipo.setDescricao("" + arrayEndereco[1]);
					if (arrayEndereco[22] != null) {
						logradouroTipo.setDescricaoAbreviada(""
								+ arrayEndereco[22]);
					}
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				LogradouroTitulo logradouroTitulo = null;
				// Descri��o abreviada logradouro titulo
				if (arrayEndereco[2] != null) {
					logradouroTitulo = new LogradouroTitulo();
					logradouroTitulo.setDescricao("" + arrayEndereco[2]);
					if (arrayEndereco[23] != null) {
						logradouroTitulo.setDescricaoAbreviada(""
								+ arrayEndereco[23]);
					}
					logradouro.setLogradouroTitulo(logradouroTitulo);
				}
				if (arrayEndereco[10] != null) {
					cepFormatado = "" + (Integer) arrayEndereco[16];
				}

				logradouroCep.setLogradouro(logradouro);
			}

			imovel.setLogradouroCep(logradouroCep);

			LogradouroBairro logradouroBairro = null;
			if (arrayEndereco[21] != null) {

				logradouroBairro = new LogradouroBairro();
				logradouroBairro.setId((Integer) arrayEndereco[21]);

				// nome bairro
				if (arrayEndereco[3] != null) {
					nomeBairro = "" + arrayEndereco[4];
				}

				// nome municipio
				if (arrayEndereco[5] != null) {
					nomeMunicipio = "" + arrayEndereco[6];

					// id da unidade federa��o
					if (arrayEndereco[7] != null) {
						siglaUnidadeFederacao = "" + arrayEndereco[8];
					}

				}
			}

			imovel.setLogradouroBairro(logradouroBairro);

			// descricao de endere�o refer�ncia
			if (arrayEndereco[9] != null) {
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setDescricao("" + arrayEndereco[9]);
				if (arrayEndereco[24] != null) {
					enderecoReferencia.setDescricaoAbreviada(""
							+ arrayEndereco[24]);
				}
				imovel.setEnderecoReferencia(enderecoReferencia);
			}

			// numero imovel
			if (arrayEndereco[17] != null) {
				//imovel.setNumeroImovel("" + arrayEndereco[17]);
				numero = "" + (String) arrayEndereco[17];
				
			}

			// complemento endere�o
			if (arrayEndereco[18] != null) {
				//imovel.setComplementoEndereco("" + arrayEndereco[18]);
				complemento = "" + (String) arrayEndereco[18];
			}
			
			endereco = imovel.getEnderecoTipoTituloLogradouro();
		}
		parmsEndereco[0] = endereco;
		parmsEndereco[1] = nomeMunicipio;
		parmsEndereco[2] = siglaUnidadeFederacao;
		parmsEndereco[3] = nomeBairro;
		parmsEndereco[4] = cepFormatado;
		parmsEndereco[5] = numero;
		parmsEndereco[6] = complemento;

		return parmsEndereco;

	}
	
	
	/**
	 * Metodo que Monta o endereco do cliente a partir do endereco do imovel
	 * 
	 * @date 04/01/2012
	 * @author Arthur Carvalho
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException 
	 */
	public ClienteEndereco pesquisarClienteEnderecoAPartirDoEnderecoDoImovel(Integer idImovel) throws ControladorException{
		
		ClienteEndereco clienteEndereco = new ClienteEndereco();
		Localidade localidade = new Localidade();
		GerenciaRegional gerenciaRegional = new GerenciaRegional();
		Agencia agencia = new Agencia();

		LogradouroCep logradouroCep = new LogradouroCep();
		LogradouroBairro logradouroBairro = new LogradouroBairro();
		
		Quadra quadra = new Quadra();
		Collection colecaoEndereco = null;
		try {
			
			colecaoEndereco = repositorioEndereco.pesquisarEnderecoFormatado(idImovel);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		Imovel imovel = new Imovel();

		Iterator enderecoIterator = colecaoEndereco.iterator();
		if (colecaoEndereco != null && !colecaoEndereco.isEmpty()) {
			Object[] arrayEndereco = (Object[]) enderecoIterator.next();

			//CEP
			if (arrayEndereco[10] != null ) {
				Cep cep = new Cep();
				cep.setCepId((Integer) arrayEndereco[10]);
				cep.setCodigo((Integer) arrayEndereco[16]);
				logradouroCep.setCep(cep);
					
			}
			
			//LOGRADOURO
			if (arrayEndereco[19] != null  ) {
				Logradouro logradouro = new Logradouro();
				logradouro.setId((Integer)  arrayEndereco[19]);
				logradouro.setNome((String)  arrayEndereco[0]);
				
				
				if ( arrayEndereco[1] != null  ) {
					LogradouroTipo logradouroTipo =new  LogradouroTipo();
					logradouroTipo.setDescricao((String) arrayEndereco[1]); 
					logradouro.setLogradouroTipo(logradouroTipo);
				}
				// Adiciona o logradouro ao objeto final
				logradouroCep.setLogradouro(logradouro);
			}
			if (arrayEndereco[3] != null ) {
				// Adiciona o bairro ao objeto final
				Bairro bairro = new Bairro();
				bairro.setId((Integer) arrayEndereco[3] ) ;
			
				logradouroBairro.setBairro(bairro);
			}
			
			if (arrayEndereco[3] != null ) {
				// Adiciona o bairro ao objeto final
				Bairro bairro = new Bairro();
				bairro.setId((Integer) arrayEndereco[3] ) ;
			
				logradouroBairro.setBairro(bairro);
			}
			
			if (arrayEndereco[26] != null ) {
				// Adiciona o bairro ao objeto final
				Logradouro logradouro = new Logradouro();
				logradouro.setId((Integer) arrayEndereco[26]);
				
				clienteEndereco.setPerimetroInicial(logradouro);
			}
			
			
			if (arrayEndereco[30] != null ) {
				// Adiciona o bairro ao objeto final
				Logradouro logradouro = new Logradouro();
				logradouro.setId((Integer) arrayEndereco[30]);
				
				clienteEndereco.setPerimetroFinal(logradouro);
			}
			if (arrayEndereco[25] != null ) {
				EnderecoReferencia enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setId((Integer)arrayEndereco[25]);
	
				// Adiciona o EnderecoReferencia ao objeto final
				clienteEndereco.setEnderecoReferencia(enderecoReferencia);
				imovel.setEnderecoReferencia(enderecoReferencia);
				localidade.setEnderecoReferencia(enderecoReferencia);
				gerenciaRegional.setEnderecoReferencia(enderecoReferencia);
				agencia.setEnderecoReferencia(enderecoReferencia);
			}
			
//			EnderecoTipo enderecoTipo = new EnderecoTipo() ;
//			enderecoTipo.setId(id)
//			// Adiciona o EnderecoTipo ao objeto final
//			clienteEndereco.setEnderecoTipo(enderecoTipo);

			// NUMERO
			if (arrayEndereco[17] != null ) {
				String numero = (String) arrayEndereco[17];

				clienteEndereco.setNumero(numero);
				imovel.setNumeroImovel(numero);
				localidade.setNumeroImovel(numero);
				gerenciaRegional.setNumeroImovel(numero);
				agencia.setNumeroImovel(numero);
			}
			
			if (arrayEndereco[18] != null ) {
				String complemento = (String) arrayEndereco[18];
				
				clienteEndereco.setComplemento(complemento);
				imovel.setComplementoEndereco(complemento);
				localidade.setComplementoEndereco(complemento);
				gerenciaRegional.setComplementoEndereco(complemento);
				agencia.setComplementoEndereco(complemento);
			}
			
		}

		clienteEndereco
				.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_NAO_ENDERECO_CORRESPONDENCIA);

		// Adiciona a data da �ltima altera��o ao objeto final
		imovel.setUltimaAlteracao(new Date());
		localidade.setUltimaAlteracao(new Date());
		clienteEndereco.setUltimaAlteracao(new Date());
		gerenciaRegional.setUltimaAlteracao(new Date());
		agencia.setUltimaAlteracao(new Date());

		// Carregando os objetos finais com o LogradouroCep e
		// LogradouroBairro
		logradouroCep
				.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		
		try {
			Integer idLogradouroCep;
			idLogradouroCep = inserirAssociacaoLogradouroCep(logradouroCep);
			logradouroCep.setId(idLogradouroCep);
			
			logradouroBairro = pesquisarAssociacaoLogradouroBairro(logradouroBairro.getBairro().getId(), logradouroCep.getLogradouro().getId());
			
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		imovel.setLogradouroCep(logradouroCep);
		imovel.setLogradouroBairro(logradouroBairro);

		localidade.setLogradouroCep(logradouroCep);
		localidade.setLogradouroBairro(logradouroBairro);
		
		gerenciaRegional.setLogradouroCep(logradouroCep);
		gerenciaRegional.setLogradouroBairro(logradouroBairro);
		
		agencia.setLogradouroCep(logradouroCep);
		agencia.setLogradouroBairro(logradouroBairro);

		clienteEndereco.setLogradouroCep(logradouroCep);
		clienteEndereco.setLogradouroBairro(logradouroBairro);
		
		return clienteEndereco;
	}
	
	/**
	 * 
	 * Pesquisar o endere�o abreviado a partir do id do cliente
	 * 
	 * [UC0483] - Obter Endere�o Abreviado
	 * 
	 * @author Carlos Chaves
	 * @date 08/01/2013
	 * 
	 * @param idCliente
	 * @return String
	 * @throws ControladorException
	 */

	public String obterEnderecoAbreviadoCliente(Integer idCliente) throws ControladorException {
		Object[] dadosEndereco = null;
		try {
			dadosEndereco = repositorioEndereco.obterEnderecoAbreviadoCliente(idCliente);
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}

		String endereco = "";

		if (dadosEndereco != null) {

			// Nome do Logradouro
			if (dadosEndereco[0] != null) {
				endereco = endereco + dadosEndereco[0].toString().trim();
			}

			// N�mero do Im�vel
			if (dadosEndereco[1] != null) {
				endereco = endereco + ", " + dadosEndereco[1].toString().trim();
			}

			// Nome do Bairro
			if (dadosEndereco[2] != null) {
				endereco = endereco + " - "
						+ dadosEndereco[2].toString().trim();
			}

		}

		return endereco;
	}
	
	/**
	 * Recuperar Nome do Bairro do Cliente
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 10/07/2014
	 */
	public String pesquisarBairroCliente(Integer idCliente)
			throws ControladorException {
			
			try {
				return repositorioEndereco
						.pesquisarBairroCliente(idCliente);
			} catch (ErroRepositorioException ex) {
				throw new ControladorException("erro.sistema", ex);
			}
			
		}

}