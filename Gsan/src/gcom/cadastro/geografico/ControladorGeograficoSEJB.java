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
package gcom.cadastro.geografico;

import gcom.gui.ActionServletException;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.ErroRepositorioException;
import gcom.util.RemocaoInvalidaException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Defini��o da l�gica de neg�cio do Session Bean de ControladorCliente
 * 
 * @author S�vio Luiz
 * @created 25 de Abril de 2005
 */
public class ControladorGeograficoSEJB implements SessionBean {
	
	
	private static final long serialVersionUID = 1L;

	private IRepositorioGeografico repositorioGeografico = null;

	SessionContext sessionContext;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @exception CreateException
	 *                Descri��o da exce��o
	 */
	public void ejbCreate() throws CreateException {
		repositorioGeografico = RepositorioGeograficoHBM.getInstancia();
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
	 * Atualiza um bairro no sistema
	 * 
	 * @param bairro
	 *            Description of the Parameter
	 * @throws ControladorException
	 */
	public void atualizarBairro(Bairro bairro, Collection colecaoBairroArea,
			Collection colecaoBairroAreaRemover, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_BAIRRO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		bairro.setOperacaoEfetuada(operacaoEfetuada);
		bairro.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(bairro);
		// ------------ REGISTRAR TRANSA��O ----------------------------

		FiltroBairro filtroBairro = new FiltroBairro();

		// Parte que atualiza um bairro na base

		// Parte de Validacao com Timestamp

		// Seta o filtro para buscar o bairro na base
		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.ID,
				bairro.getId()));

		// Procura o bairro na base

		// Alterado Por Tiago Moreno - 17/07/2006

		// Bairro bairroNaBase = (Bairro) ((List)
		// (getControladorUtil().pesquisar(
		// filtroBairro, Bairro.class.getName()))).get(0);

		Bairro bairroNaBase = null;
		Collection bairroNaBaseColecao = (Collection) getControladorUtil()
				.pesquisar(filtroBairro, Bairro.class.getName());

		if (bairroNaBaseColecao != null && !bairroNaBaseColecao.isEmpty()) {
			bairroNaBase = (Bairro) bairroNaBaseColecao.iterator().next();
		} else {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// fim da altera��o - Tiago Moreno - 17/07/2006

		// Verificar se o cliente j� foi atualizado por outro usu�rio
		// durante
		// esta atualiza��o
		if (bairroNaBase.getUltimaAlteracao()
				.after(bairro.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		// limpa os parametros para fazer uma nova pesquisa
		filtroBairro.limparListaParametros();

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, "" + bairro.getCodigo()));

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, bairro.getMunicipio().getId()));

		Collection bairrosPesquisados = getControladorUtil().pesquisar(
				filtroBairro, Bairro.class.getName());

		if (bairrosPesquisados != null && !bairrosPesquisados.isEmpty()) {

			// Procura o bairro na base
			Bairro bairroPesquisado = (Bairro) ((List) (bairrosPesquisados))
					.get(0);

			if (!bairroPesquisado.getId().equals(bairro.getId())) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.pesquisa_bairro_ja_cadastrada");
			}
		}

		// Atualiza a data de �ltima altera��o
		bairro.setUltimaAlteracao(new Date());

		// Atualiza o bairro
		getControladorUtil().atualizar(bairro);
		// Fim da parte que atualiza um bairro na base

		if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {
			Iterator iterator = colecaoBairroArea.iterator();

			while (iterator.hasNext()) {
				BairroArea bairroArea = (BairroArea) iterator.next();

				bairroArea.setBairro(bairro);
				bairroArea.setUltimaAlteracao(new Date());

				bairroArea.setOperacaoEfetuada(operacaoEfetuada);
				bairroArea.adicionarUsuario(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
				registradorOperacao.registrarOperacao(bairroArea);

				getControladorUtil().inserirOuAtualizar(bairroArea);
			}

		}

		if (colecaoBairroAreaRemover != null
				&& !colecaoBairroAreaRemover.isEmpty()) {
			Iterator iteratorRemover = colecaoBairroAreaRemover.iterator();

			while (iteratorRemover.hasNext()) {
				BairroArea bairroAreaRemover = (BairroArea) iteratorRemover
						.next();
				getControladorUtil().remover(bairroAreaRemover);
			}

		}

	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param codigoSetorComercial
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @throws ControladorException
	 */
	public Collection pesquisarMunicipoPeloSetorComercial(
			String codigoSetorComercial, String idMunicipio)
			throws ControladorException {
		try {
			return repositorioGeografico.pesquisarMunicipoPeloSetorComercial(
					codigoSetorComercial, idMunicipio);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
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
	 * Verifica se o munic�pio possui CEP por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 16/05/2006
	 * 
	 * @param municipio
	 * @return boolean
	 */
	public boolean verificarMunicipioComCepPorLogradouro(Municipio municipio)
			throws ControladorException {

		boolean retorno = true;

		if (municipio.getCepInicio() != null && municipio.getCepFim() != null
				&& municipio.getCepInicio().equals(municipio.getCepFim())) {

			retorno = false;

		}

		return retorno;
	}

	/**
	 * M�todo que retorna o maior c�digo do bairro de um munic�pio
	 * 
	 * @author Rafael Corr�a
	 * @date 10/07/2006
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoCodigoBairro(Integer idMunicipio)
			throws ControladorException {
		try {
			return repositorioGeografico
					.pesquisarMaximoCodigoBairro(idMunicipio);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}

	/**
	 * Pesquisa um munic�pio pelo id
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Munic�pio
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Municipio pesquisarObjetoMunicipioRelatorio(Integer idMunicipio)
			throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sistema
			Object[] objetoMunicipio = repositorioGeografico
					.pesquisarObjetoMunicipioRelatorio(idMunicipio);

			Municipio municipio = new Municipio();

			municipio.setId((Integer) objetoMunicipio[0]);
			municipio.setNome((String) objetoMunicipio[1]);

			return municipio;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Pesquisa um bairro pelo c�digo e pelo id do munic�pio
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Bairro
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Bairro pesquisarObjetoBairroRelatorio(Integer codigoBairro,
			Integer idMunicipio) throws ControladorException {
		try {
			// pesquisa as gerencias regionais existentes no sisitema
			Object[] objetoBairro = repositorioGeografico
					.pesquisarObjetoBairroRelatorio(codigoBairro, idMunicipio);

			Bairro bairro = new Bairro();

			bairro.setCodigo(((Integer) objetoBairro[0]).intValue());
			bairro.setNome((String) objetoBairro[1]);

			return bairro;

			// erro no hibernate
		} catch (ErroRepositorioException ex) {

			// levanta a exce��o para a pr�xima camada
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * [UC0001] Inserir Munic�pio
	 * 
	 * @author Kassia Albuquerque
	 * @date 18/12/2006
	 * 
	 * @param municipio
	 *            Descri��o do par�metro
	 */
	public Integer inserirMunicipio(Municipio municipio, Usuario usuarioLogado)
			throws ControladorException {

		// [FS0003] - Verificando a exist�ncia do Municipio

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, municipio.getId()));

		Collection colecaoMunicipio = getControladorUtil().pesquisar(
				filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
			throw new ControladorException("atencao.municipio.existente");
		}

		// [FS0005] - Validar CEP
		Integer cepInicial = new Integer(municipio.getCepInicio());
		Integer cepFinal = new Integer(municipio.getCepFim());

		if (cepInicial.intValue() > cepFinal.intValue()) {
			throw new ActionServletException(
					"atencao.cep_inicio.anterior.cep_fim");
		}
		municipio.setUltimaAlteracao(new Date());

		// ------------ REGISTRAR TRANSA��O----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MUNICIPIO_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		municipio.setOperacaoEfetuada(operacaoEfetuada);
		municipio.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(municipio);
		// ------------ REGISTRAR TRANSA��O----------------------------

		Integer idMunicipio = (Integer) getControladorUtil().inserir(municipio);
		municipio.setId(idMunicipio);

		return idMunicipio;
	}

	/**
	 * [UC0035] Inserir Bairro
	 * 
	 * Insere um objeto do tipo bairro no BD
	 * 
	 * @author Vivianne Sousa
	 * @date 22/12/2006
	 * @param bairro
	 * @param colecaoBairroArea
	 * @return idBairro
	 * @throws ControladorException
	 */
	public Integer inserirBairro(Bairro bairro, Collection colecaoBairroArea,
			Usuario usuarioLogado) throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_BAIRRO_INSERIR, new UsuarioAcaoUsuarioHelper(
						usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		bairro.setOperacaoEfetuada(operacaoEfetuada);
		bairro.adicionarUsuario(usuarioLogado,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(bairro);
		// ------------ REGISTRAR TRANSA��O ----------------------------

		// -- Inserir o bairro --
		Integer idBairro = (Integer) this.getControladorUtil().inserir(bairro);

		// -- codigo para inserir BairroArea --
		bairro.setId(idBairro);
		Iterator iterator = colecaoBairroArea.iterator();

		while (iterator.hasNext()) {

			BairroArea bairroArea = (BairroArea) iterator.next();
			bairroArea.setBairro(bairro);

			bairroArea.setUltimaAlteracao(new Date());

			// ------------ REGISTRAR TRANSA��O ----------------
			bairroArea.setOperacaoEfetuada(operacaoEfetuada);
			bairroArea.adicionarUsuario(usuarioLogado,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(bairroArea);
			// ------------ REGISTRAR TRANSA��O ----------------

			this.getControladorUtil().inserir(bairroArea);

		}
		// -- fim de codigo para inserir BairroArea --
		return idBairro;
	}

	/**
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
			throws ControladorException {
		try {
			return repositorioGeografico.pesquisarBairroArea(idBairro);
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}

	/**
	 * Remover Bairro
	 * 
	 * Remove os bairros e area bairro selecionados na lista da funcionalidade
	 * Manter Bairro
	 * 
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * @param bairro
	 * @param colecaoBairroArea
	 * @return idBairro
	 * @throws ControladorException
	 */
	public void removerBairro(String[] ids, Usuario usuarioLogado)
			throws ControladorException {

		// ------------ REGISTRAR TRANSA��O ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_REMOVER);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		UsuarioAcaoUsuarioHelper usuarioAcaoUsuarioHelper = new UsuarioAcaoUsuarioHelper(
				usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		Collection<UsuarioAcaoUsuarioHelper> colecaoUsuarios = new ArrayList();
		colecaoUsuarios.add(usuarioAcaoUsuarioHelper);
		// ------------ REGISTRAR TRANSA��O ----------------

		// remover areaBairro para cada bairro a remover
		int i = 0;

		while (i < ids.length) {
			String idBairro = ids[i];
			try {
				repositorioGeografico
						.removerTodosBairroAreaPorBairro(new Integer(idBairro));
			} catch (RemocaoInvalidaException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException(
						"atencao.dependencias.existentes", ex);
			} catch (ErroRepositorioException ex) {
				sessionContext.setRollbackOnly();
				throw new ControladorException("erro.sistema", ex);
			}

			i = i + 1;
		}

		// remover bairro(s)
		this.getControladorUtil().remover(ids, Bairro.class.getName(),
				operacaoEfetuada, colecaoUsuarios);

	}

	/**
	 * [UC0006] Filtrar Munic�pio
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @param Integer
	 * @return boolean
	 */
	public boolean verificarExistenciaMunicipio(Integer codigoMunicipio)
			throws ControladorException {

		// [FS0004] - Verificando a exist�ncia do Municipio
		boolean retorno = true;

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, codigoMunicipio));

		Collection colecaoMunicipio = getControladorUtil().pesquisar(
				filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
			throw new ControladorException("atencao.municipio.inexistente");
		}
		return retorno;

	}

	/**
	 * [UC0005] Manter Municipio 
	 * 			
	 * 			Remover Municipio
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void removerMunicipio(String[] ids, Usuario usuarioLogado)throws ControladorException {

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
        this.getControladorUtil().remover(ids, Municipio.class.getName(),null, null);
}

    
	
	/**
	 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
	 * 
	 * @author Kassia Albuquerque
	 * @date 10/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void atualizarMunicipio(Municipio municipio,
			Usuario usuarioLogado) throws ControladorException {

		
		// [FS0005] - Validar CEP
		Integer cepInicial = new Integer(municipio.getCepInicio());
		Integer cepFinal = new Integer(municipio.getCepFim());

		if (cepInicial.intValue() > cepFinal.intValue()) {
			throw new ActionServletException("atencao.cep_inicio.anterior.cep_fim");
		}
//		municipio.setUltimaAlteracao(new Date());		
		
		// [UC0107] - Registrar Transa��o
		// ------------ REGISTRAR TRANSA��O----------------------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_MUNICIPIO_ATUALIZAR,new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_MUNICIPIO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		municipio.setOperacaoEfetuada(operacaoEfetuada);
		municipio.adicionarUsuario(usuarioLogado,UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(municipio);
		// ------------ REGISTRAR TRANSA��O----------------------------

		// [FS0002] - Atualiza��o realizada por outro usu�rio
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		// Seta o filtro para buscar o municipio na base
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipio.getId()));

		// Procura municipio na base
		Collection municipioAtualizadas = getControladorUtil().pesquisar(filtroMunicipio,Municipio.class.getName());

		Municipio municipioNaBase = (Municipio) Util.retonarObjetoDeColecao(municipioAtualizadas);

		if (municipioNaBase == null) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.registro_remocao_nao_existente");
		}

		// Verificar se o municipio j� foi atualizado por outro usu�rio
		// durante esta atualiza��o

		if (municipioNaBase.getUltimaAlteracao().after(municipio.getUltimaAlteracao())) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("atencao.atualizacao.timestamp");
		}

		municipio.setUltimaAlteracao(new Date());

		// Atualiza o objeto na base
		getControladorUtil().atualizar(municipio);

	}
	
	 /**
	 * M�todo que retorna o maior id do Munic�pio
	 * 
	 * @author Rafael Corr�a
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoIdMunicipio() throws ControladorException {
		try {
			return repositorioGeografico.pesquisarMaximoIdMunicipio();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
	}
    
    /**
	 * M�todo que retorna o municipio do Imovel
	 * 
	 * @author Hugo Amorim
	 * @date 27/08/2009
	 * 
	 * @return Municipio
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipioDoImovel(Integer idImovel) 
    	throws ControladorException{
    		try {
    			return repositorioGeografico.pesquisarMunicipioDoImovel(idImovel);
    		} catch (ErroRepositorioException ex) {
    			throw new ControladorException("erro.sistema", ex);
    		}
    }
    
    /**
	 * [UC0592] Filtrar Rela��o de Parcelamentos
	 * 
	 * M�todo repons�vel por retornar todos os munic�pios que possuem alguma
	 * associa��o com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ControladorException{
		try {
			return repositorioGeografico.pesquisarMunicipiosAssociadoLocalidade();
		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}    	
    }
	
	
}
