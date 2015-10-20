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
package gcom.operacional;

import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroAbastecimentoProgramacao;
import gcom.operacional.abastecimento.FiltroManutencaoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
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
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de ControladorCliente
 * 
 * @author Leandro Cavalcanti
 * @created 12 de junho de 2006
 */
public class ControladorOperacionalSEJB implements SessionBean {
	
	private static final long serialVersionUID = 1L;
	
	SessionContext sessionContext;
	
	protected IRepositorioOperacional repositorioOperacional;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioOperacional = RepositorioOperacionalHBM.getInstancia();
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
	 * Faz o controle de concorrencia de programa��o de abastecimento
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 04/12/2006
	 * @throws ControladorException
	 */
	private void verificarAbastecimentoProgramacaoControleConcorrencia(
			AbastecimentoProgramacao abastecimentoProgramacao)
			throws ControladorException {

		FiltroAbastecimentoProgramacao filtro = new FiltroAbastecimentoProgramacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroAbastecimentoProgramacao.ID, abastecimentoProgramacao
						.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				AbastecimentoProgramacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		AbastecimentoProgramacao abastecimentoProgramacaoAtual = (AbastecimentoProgramacao) Util
				.retonarObjetoDeColecao(colecao);

		if (abastecimentoProgramacaoAtual.getUltimaAlteracao().after(
				abastecimentoProgramacao.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * Faz o controle de concorrencia da programa��o da manuten��o
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 04/12/2006
	 * @throws ControladorException
	 */
	private void verificarManutencaoProgramacaoControleConcorrencia(
			ManutencaoProgramacao manutencaoProgramacao)
			throws ControladorException {

		FiltroManutencaoProgramacao filtro = new FiltroManutencaoProgramacao();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroManutencaoProgramacao.ID, manutencaoProgramacao.getId()));

		Collection colecao = getControladorUtil().pesquisar(filtro,
				ManutencaoProgramacao.class.getName());

		if (colecao == null || colecao.isEmpty()) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		ManutencaoProgramacao manutencaoProgramacaoAtual = (ManutencaoProgramacao) Util
				.retonarObjetoDeColecao(colecao);

		if (manutencaoProgramacaoAtual.getUltimaAlteracao().after(
				manutencaoProgramacaoAtual.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}
	}

	/**
	 * [UC0001] Inserir Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 26/01/2007
	 * 
	 * @param Distrito
	 *            Operaciona Descri��o do par�metro
	 */
	public Integer inserirDistritoOperacional(String descricao,
			String descricaoAbreviada, String idSetorAbastecimento,
			Usuario usuarioLogado)
			throws ControladorException {

		DistritoOperacional distritoOperacional = new DistritoOperacional();
		distritoOperacional.setDescricao(descricao);
		distritoOperacional.setDescricaoAbreviada(descricaoAbreviada);

		SetorAbastecimento setorAbesteciento = new SetorAbastecimento();
		setorAbesteciento.setId(new Integer(idSetorAbastecimento));
		distritoOperacional.setSetorAbastecimento(setorAbesteciento);

		distritoOperacional.setUltimaAlteracao(new Date());
		distritoOperacional.setIndicadorUso( new Integer(1).shortValue() );

		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
				FiltroDistritoOperacional.DESCRICAO, descricao));

		Collection colecaoDistritoOperacional = getControladorUtil().pesquisar(
				filtroDistritoOperacional, DistritoOperacional.class.getName());

		Integer idDistritoOperacional = null;

		if (colecaoDistritoOperacional.isEmpty()) {
			idDistritoOperacional = (Integer) getControladorUtil().inserir(
					distritoOperacional);
		} else {
			throw new ControladorException(
					"atencao.distrito_operacional_existente");
		}

		// ------------ REGISTRAR TRANSA��O----------------------------

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DISTRITO_OPERACIONAL_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_DISTRITO_OPERACIONAL_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		distritoOperacional.setOperacaoEfetuada(operacaoEfetuada);
		distritoOperacional.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(distritoOperacional);

		// ------------ REGISTRAR TRANSA��O----------------------------

		return idDistritoOperacional;
	}

	/**
	 * [UC0414] - Informar Programa��o de Abastecimento e Manuten��o
	 * 
	 * [SB0006] - Atualizar Programa��o de Abastecimento na Base de Dados
	 * [SB0007] - Atualizar Programa��o de Manuten��o na Base de Dados
	 * 
	 * @author Rafael Pinto
	 * @date 09/11/2006
	 * 
	 * @throws ControladorException
	 *             Controlador Exception
	 */
	public void atualizarProgramacaoAbastecimentoManutencao(
			Collection colecaoProgramacaoAbastecimento,
			Collection colecaoProgramacaoAbastecimentoRemovidas,
			Collection colecaoProgramacaoManutencao,
			Collection colecaoProgramacaoManutencaoRemovidas, Usuario usuario)
			throws ControladorException {

		// [UC0107] - Registrar Transa��o
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao
				.setId(Operacao.OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		Iterator itera = null;

		// [SB0006] - Atualizar Programa��o de Abastecimento na Base de Dados
		if (colecaoProgramacaoAbastecimento != null
				&& !colecaoProgramacaoAbastecimento.isEmpty()) {

			itera = colecaoProgramacaoAbastecimento.iterator();

			while (itera.hasNext()) {

				AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) itera
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// ent�o verifica o controle de concorrencia
				if (abastecimentoProgramacao.getId() != null
						&& abastecimentoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarAbastecimentoProgramacaoControleConcorrencia(abastecimentoProgramacao);
				}

				abastecimentoProgramacao.setUltimaAlteracao(new Date());

				// [UC0107] - Registrar Transa��o
				abastecimentoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				abastecimentoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(abastecimentoProgramacao);

				this.getControladorUtil().inserirOuAtualizar(
						abastecimentoProgramacao);
			}
		}

		if (colecaoProgramacaoAbastecimentoRemovidas != null
				&& !colecaoProgramacaoAbastecimentoRemovidas.isEmpty()) {

			Iterator iter = colecaoProgramacaoAbastecimentoRemovidas.iterator();

			while (iter.hasNext()) {

				AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) iter
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// ent�o verifica o controle de concorrencia
				if (abastecimentoProgramacao.getId() != null
						&& abastecimentoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarAbastecimentoProgramacaoControleConcorrencia(abastecimentoProgramacao);
				}

				abastecimentoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				abastecimentoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(abastecimentoProgramacao);

				this.getControladorUtil().remover(abastecimentoProgramacao);
			}
		}

		if (colecaoProgramacaoManutencao != null
				&& !colecaoProgramacaoManutencao.isEmpty()) {
			itera = colecaoProgramacaoManutencao.iterator();

			// [SB0007] - Atualizar Programa��o de Manuten��o na Base de Dados
			while (itera.hasNext()) {

				ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) itera
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// ent�o verifica o controle de concorrencia
				if (manutencaoProgramacao.getId() != null
						&& manutencaoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarManutencaoProgramacaoControleConcorrencia(manutencaoProgramacao);
				}
				manutencaoProgramacao.setUltimaAlteracao(new Date());

				// [UC0107] - Registrar Transa��o
				manutencaoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				manutencaoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(manutencaoProgramacao);

				this.getControladorUtil().inserirOuAtualizar(
						manutencaoProgramacao);
			}
		}

		if (colecaoProgramacaoManutencaoRemovidas != null
				&& !colecaoProgramacaoManutencaoRemovidas.isEmpty()) {

			Iterator iter = colecaoProgramacaoManutencaoRemovidas.iterator();

			while (iter.hasNext()) {

				ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) iter
						.next();

				// Se existir id que dizer que existe esse objeto na base,
				// ent�o verifica o controle de concorrencia
				if (manutencaoProgramacao.getId() != null
						&& manutencaoProgramacao.getId().intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

					this
							.verificarManutencaoProgramacaoControleConcorrencia(manutencaoProgramacao);
				}

				manutencaoProgramacao.setOperacaoEfetuada(operacaoEfetuada);
				manutencaoProgramacao.adicionarUsuario(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

				registradorOperacao.registrarOperacao(manutencaoProgramacao);

				this.getControladorUtil().remover(manutencaoProgramacao);
			}
		}

	}
	
	/**
	 * [UC0522] MANTER DISTRITO OPERACIONAL 
	 * 			
	 * 			Remover Distrito Operacional
	 * 
	 * @author Eduardo Bianchi
	 * @date 05/02/2007
	 * 
	 * @pparam distritoOperacional
	 * @throws ControladorException
	 */
	public void removerDistritoOperacional(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSA��O ----------------
		
	       // [FS0003]Municipiopossui vinculos no sistema
        this.getControladorUtil().remover(ids, DistritoOperacional.class.getName(),null, null);
}

	/**
	 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Municipio
	 * 
	 * @author Eduardo Bianchi
	 * @date 09/02/2007
	 * 
	 * @pparam distritoOperacional
	 * @throws ControladorException
	 */
	public void atualizarDistritoOperacional(DistritoOperacional distritoOperacional,
			Usuario usuarioLogado) throws ControladorException {		
		
		// [UC0107] - Registrar Transa��o
		// ------------ REGISTRAR TRANSA��O----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		distritoOperacional.setOperacaoEfetuada(operacaoEfetuada);
		distritoOperacional.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(distritoOperacional);
		// ------------ REGISTRAR TRANSA��O----------------------------

		// [FS0002] - Atualiza��o realizada por outro usu�rio
		
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
		// Seta o filtro para buscar o Distrito Operacional na base
		filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.ID, distritoOperacional.getId()));

		// Procura o Distrito Operacional na base
		Collection distritosOperacionaisAtualizados = getControladorUtil().pesquisar(filtroDistritoOperacional,DistritoOperacional.class.getName());

		DistritoOperacional distritoOperacionalNaBase = (DistritoOperacional) Util.retonarObjetoDeColecao(distritosOperacionaisAtualizados);

		if (distritoOperacionalNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o distrito Operacional j� foi atualizado por outro usu�rio
		// durante esta atualiza��o

		if (distritoOperacionalNaBase.getUltimaAlteracao().after(distritoOperacional.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		distritoOperacional.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(distritoOperacional);

	}
	

	/**
	 * [UC0524] Inserir Sistema de Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 09/03/2007
	 * 
	 * 
	 */
	
	public Integer inserirSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado)
			throws ControladorException {

			// [FS0003] - Verificando a exist�ncia do Sistema de Esgoto
	
			FiltroSistemaEsgoto filtroSistemaEsgoto= new FiltroSistemaEsgoto();
			
			filtroSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSistemaEsgoto.DESCRICAO,sistemaEsgoto.getDescricao()));
			
			Collection colecaoSistemaEsgoto = getControladorUtil().pesquisar(filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			
			if (colecaoSistemaEsgoto != null && !colecaoSistemaEsgoto.isEmpty()){
				throw new ControladorException("atencao.divisao_esgoto.existente", null, sistemaEsgoto.getDescricao());
			}
	
			
			sistemaEsgoto.setUltimaAlteracao(new Date());
	
			// ------------ REGISTRAR TRANSA��O----------------------------
			RegistradorOperacao registradorOperacao = new RegistradorOperacao(
					Operacao.OPERACAO_INSERIR_SISTEMA_ESGOTO, new UsuarioAcaoUsuarioHelper
					(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
	
			Operacao operacao = new Operacao();
			operacao.setId(Operacao.OPERACAO_INSERIR_SISTEMA_ESGOTO);
	
			OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
			operacaoEfetuada.setOperacao(operacao);
	
			sistemaEsgoto.setOperacaoEfetuada(operacaoEfetuada);
			sistemaEsgoto.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(sistemaEsgoto);
			// ------------ REGISTRAR TRANSA��O----------------------------
	
			Integer idSistemaEsgoto = (Integer) getControladorUtil().inserir(sistemaEsgoto);
			sistemaEsgoto.setId(idSistemaEsgoto);
	
			return idSistemaEsgoto;
	}
	
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0001]Atualizar Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 19/03/2007
	 * 
	 */
	
	
	public void atualizarSistemaEsgoto(SistemaEsgoto sistemaEsgoto,Usuario usuarioLogado) 
						throws ControladorException {

		
		// [UC0107] - Registrar Transa��o
		// ------------ REGISTRAR TRANSA��O----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SISTEMA_ESGOTO_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SISTEMA_ESGOTO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		sistemaEsgoto.setOperacaoEfetuada(operacaoEfetuada);
		sistemaEsgoto.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(sistemaEsgoto);
		// ------------ REGISTRAR TRANSA��O----------------------------

		// [FS0002] - Atualiza��o realizada por outro usu�rio
		
		FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto();
		// Seta o filtro para buscar o sistema de esgoto na base
		filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.ID, sistemaEsgoto.getId()));

		// Procura sistema de esgoto na base
		Collection sistemaEsgotoAtualizados = getControladorUtil().pesquisar(filtroSistemaEsgoto,SistemaEsgoto.class.getName());

		SistemaEsgoto sistemaEsgotoNaBase = (SistemaEsgoto) Util.retonarObjetoDeColecao(sistemaEsgotoAtualizados);

		if (sistemaEsgotoNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o sistema de esgoto j� foi atualizado por outro usu�rio
		// durante esta atualiza��o

		if (sistemaEsgotoNaBase.getUltimaAlteracao().after(sistemaEsgoto.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		sistemaEsgoto.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(sistemaEsgoto);

	}
	
	/**
	 * [UC0525] Manter Sistema Esgoto [SB0002]Remover Sistema Esgoto
	 * 
	 * @author Kassia Albuquerque
	 * @date 20/03/2007
	 * 
	 */
	
	public void removerSistemaEsgoto(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SISTEMA_ESGOTO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSA��O ----------------
		

	       // [FS0003]Sistema de Esgoto possui vinculos no sistema
        this.getControladorUtil().remover(ids, SistemaEsgoto.class.getName(),operacaoEfetuada, colecaoUsuarios);

	}
	
	/**
	 * [UC0081] Manter Marca Hidrometro
	 * 
	 * @author Bruno Barros
	 * @date 03/07/2007
	 * 
	 */
	
	public void removerHidrometroMarca(String[] ids, Usuario usuarioLogado)throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REMOVER_MARCA_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSA��O ----------------
		

	       // [FS0003]Sistema de Esgoto possui vinculos no sistema
        this.getControladorUtil().remover(ids, HidrometroMarca.class.getName(),operacaoEfetuada, colecaoUsuarios);

	}
	
	/**
	 * [UC0081] Manter Hidrometro Marca
	 * 
	 * @author Bruno Barros
	 * @date 04/07/2007
	 * 
	 */		
	public void atualizarHidrometroMarca(HidrometroMarca hidrometroMarca,Usuario usuarioLogado) 
						throws ControladorException {

		
		// [UC0107] - Registrar Transa��o
		// ------------ REGISTRAR TRANSA��O----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_MARCA_HIDROMETRO,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_MARCA_HIDROMETRO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		hidrometroMarca.setOperacaoEfetuada(operacaoEfetuada);
		hidrometroMarca.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(hidrometroMarca);
		// ------------ REGISTRAR TRANSA��O----------------------------

		// [FS0002] - Atualiza��o realizada por outro usu�rio
		
		FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();
		// Seta o filtro para buscar a marca de hidrometro na base
		filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.ID, hidrometroMarca.getId()));

		// Procura sistema de esgoto na base
		Collection hidrometromMarcaAtualizados = getControladorUtil().pesquisar(filtroHidrometroMarca,HidrometroMarca.class.getName());

		HidrometroMarca hidrometromMarcaNaBase = (HidrometroMarca) Util.retonarObjetoDeColecao(hidrometromMarcaAtualizados);

		if (hidrometromMarcaNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o sistema de esgoto j� foi atualizado por outro usu�rio
		// durante esta atualiza��o

		if (hidrometromMarcaNaBase.getUltimaAlteracao().after(hidrometroMarca.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		hidrometroMarca.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(hidrometroMarca);

	}
	
	/**
	 * [UC0596] - Inserir Qualidade de agua
	 * 
	 * Pesquisa as fonte de captacao apatir da tabela de SetorFonteCaptacao
	 * 
	 * @author Rafael Pinto
	 * @date 15/10/2008
	 * 
	 * @param Collection colecaoSetorComercial
	 * @throws ControladorException
	 */
	
	public Collection<FonteCaptacao> pesquisarFonteCaptacao(Collection colecaoSetorComercial)
		throws ControladorException {
		
		try {
			return this.repositorioOperacional.pesquisarFonteCaptacao(colecaoSetorComercial);
		} catch (Exception e) {
			throw new ControladorException("erro.sistema", e);

		} 
	}	
}