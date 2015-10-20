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
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.GeradorHQLCondicional;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public class RepositorioUsuarioHBM implements IRepositorioUsuario {

	private static IRepositorioUsuario instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioUsuarioHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioUsuario getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioUsuarioHBM();
		}

		return instancia;
	}

	/**
	 * M�todo que consulta os grupos do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuario(Integer idUsuario)
			throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select grupo " + "from Grupo grupo "
					+ "where grupo.id in(select grupo.id "
					+ "from UsuarioGrupo usuarioGrupo "
					+ "inner join usuarioGrupo.usuario usuario  "
					+ "inner join usuarioGrupo.grupo grupo "
					+ "where usuario.id = :idUsuario) AND "
					+ "grupo.indicadorUso = :indicadorUso "
					+ "ORDER BY grupo.descricao ";

			retorno = session.createQuery(consulta).setInteger("idUsuario",
					idUsuario.intValue()).setShort("indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * M�todo que consulta os grupos do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 27/06/2006
	 */
	public Collection pesquisarGruposUsuarioAcesso(Collection colecaoUsuarioGrupos)
			throws ErroRepositorioException {
		Collection retorno = null;
		
		Collection idsGrupos = new ArrayList();
		if(colecaoUsuarioGrupos != null){
			Iterator iteUsuarioGrupo = colecaoUsuarioGrupos.iterator();
			while(iteUsuarioGrupo.hasNext()){
				Grupo grupo = (Grupo)iteUsuarioGrupo.next();
				idsGrupos.add(grupo.getId());
			}
		}

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select grupo " + "from Grupo grupo "
					+ "where grupo.id in (select distinct grupoAcesso.comp_id.grupIdacesso "
					+ "from gcom.seguranca.acesso.GrupoAcesso grupoAcesso "
					+ "where grupoAcesso.comp_id.grupId in (:idsGrupos)) AND "
					+ "grupo.indicadorUso = :indicadorUso ";

			retorno = session.createQuery(consulta).setParameterList("idsGrupos",
					idsGrupos).setShort("indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

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
			Integer idUsuarioAbrangenciaLogado) throws ErroRepositorioException {
		Collection retorno = null;

		Collection idsUsuarioAbrangencia = new ArrayList();
		Iterator usuarioAbrangenciaIterator = colecaoUsuarioAbrangencia
				.iterator();
		while (usuarioAbrangenciaIterator.hasNext()) {
			UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) usuarioAbrangenciaIterator
					.next();
			idsUsuarioAbrangencia.add(usuarioAbrangencia);
		}
		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {

			consulta = "select usuarioAbrangencia "
					+ "from UsuarioAbrangencia usuarioAbrangencia "
					+ "inner join usuarioAbrangencia.usuarioAbrangenciaSuperior usuarioAbrangenciaSuperior "
					+ "where usuarioAbrangenciaSuperior.id in( :idsUsuarioAbrangencia) and "
					+ "usuarioAbrangencia.id != :idUsuarioAbrangenciaLogado";

			retorno = session.createQuery(consulta).setParameterList(
					"idsUsuarioAbrangencia", idsUsuarioAbrangencia).setInteger(
					"idUsuarioAbrangenciaLogado", idUsuarioAbrangenciaLogado)
					.list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

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
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		int retorno = 0;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();
			camposOrderBy = filtroUsuarioGrupo.getCamposOrderBy();

			filtroUsuarioGrupo.limparCamposOrderBy();
			filtroUsuarioGrupo.limparColecaoCaminhosParaCarregamentoEntidades();
			
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = 
				(Integer) GeradorHQLCondicional.gerarCondicionalQuery(
					filtroUsuarioGrupo,
					"usuarioGrupo",
					"select count(distinct usuarioGrupo.usuario.id) from UsuarioGrupo as usuarioGrupo",
					session).
				uniqueResult();

			filtroUsuarioGrupo.setCampoOrderBy((String[]) camposOrderBy.toArray(new String[camposOrderBy.size()]));

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;

	}

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
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		Collection retorno = new ArrayList();
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();

			camposOrderBy = filtroUsuarioGrupo.getCamposOrderBy();

			// filtroUsuarioGrupo
			// .adicionarCaminhoParaCarregamentoEntidade("usuario");
			filtroUsuarioGrupo
					.adicionarCaminhoParaCarregamentoEntidade("usuario.unidadeOrganizacional");
			filtroUsuarioGrupo
					.adicionarCaminhoParaCarregamentoEntidade("usuario.usuarioAbrangencia");
			filtroUsuarioGrupo
					.adicionarCaminhoParaCarregamentoEntidade("usuario.usuarioSituacao");
			filtroUsuarioGrupo
					.adicionarCaminhoParaCarregamentoEntidade("usuario.usuarioTipo");
			filtroUsuarioGrupo
					.adicionarCaminhoParaCarregamentoEntidade("usuario.funcionario.unidadeOrganizacional");

			filtroUsuarioGrupo.limparCamposOrderBy();

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"

			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(

			filtroUsuarioGrupo,

			"usuario",

			"from Usuario as u left join fetch u.usuarioGrupos usuario",

			session).setFirstResult(10 * numeroPagina).setMaxResults(10).list()));

     
			


			filtroUsuarioGrupo.setCampoOrderBy((String[]) camposOrderBy
					.toArray(new String[camposOrderBy.size()]));

			// Iterator iterator = colecao.iterator();
			// while (iterator.hasNext()) {
			//
			// Usuario usuario = (Usuario) iterator.next();
			//
			// // carrega todos os objetos
			// Hibernate.initialize(usuario.getUsuarioTipo());
			// Hibernate.initialize(usuario.getUnidadeOrganizacional());
			// Hibernate.initialize(usuario.getUsuarioAbrangencia());
			// Hibernate.initialize(usuario.getUsuarioSituacao());
			//
			// retorno.add(usuario);
			// }

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;

	
	}
	/**
	 * M�todo que consulta os grupos funcion�rios opera��es passando os ids dos
	 * grupos
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */

	public Collection pesquisarGruposFuncionalidadesOperacoes(Integer[] idsGrupos)
			throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select distinct operacao "
					+ "from GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao "
					+ "inner join grupoFuncionalidadeOperacao.grupo grupo "
					+ "inner join grupoFuncionalidadeOperacao.operacao operacao "
					+ "where grupo.id in(:idsGrupos) ";

			retorno = session.createQuery(consulta).setParameterList(
					"idsGrupos", idsGrupos).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que consulta os grupos funcion�rios opera��es passando os ids dos
	 * grupos e o id da funcionalidade
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarGruposFuncionalidadesOperacoesPelaFuncionalidade(
			Integer[] idsGrupos, Integer idFuncionalidade)
			throws ErroRepositorioException {
		Collection retorno = new ArrayList();

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select distinct ope "
					+ "from GrupoFuncionalidadeOperacao gfo "
					+ "inner join gfo.grupo grp "
					+ "inner join gfo.operacao ope "
					+ "inner join gfo.funcionalidade fun "
					+ "left join fetch ope.funcionalidade func "
					+ "where grp.id in(:idsGrupos) and "
					+ "fun.id = :idFuncionalidade ";

			retorno = session.createQuery(consulta).setParameterList(
					"idsGrupos", idsGrupos).setInteger("idFuncionalidade",
					idFuncionalidade).list();

			// while (iterator.hasNext()) {
			// Operacao operacao = (Operacao) iterator.next();
			// Hibernate.initialize(operacao.getFuncionalidade());
			// retorno.add(operacao);
			//
			// }

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que consulta os usu�rios restrin��o passando os ids dos grupos , o
	 * id da funcionalidade e o id do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 11/07/2006
	 */
	public Collection pesquisarUsuarioRestrincao(Integer[] idsGrupos,
			Integer idFuncionalidade, Integer idUsuario)
			throws ErroRepositorioException {
		Collection retorno = new ArrayList();

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select distinct ope "
					+ "from UsuarioGrupoRestricao usuarioGrup "
					+ "left join usuarioGrup.grupoFuncionalidadeOperacao grupoFuncOp "
					+ "left join grupoFuncOp.operacao ope "
					+ "left join fetch ope.funcionalidade func "
					+ "where usuarioGrup.comp_id.grupoId in(:idsGrupos) and "
					+ "usuarioGrup.comp_id.funcionalidadeId = :idFuncionalidade and "
					+ "usuarioGrup.comp_id.usuarioId = :idUsuario";

			retorno = new ArrayList(new CopyOnWriteArraySet(session.createQuery(consulta)
					.setParameterList("idsGrupos", idsGrupos).setInteger(
							"idFuncionalidade", idFuncionalidade).setInteger(
							"idUsuario", idUsuario).list()));

			// while (iterator.hasNext()) {
			// Operacao operacao = (Operacao) iterator.next();
			// Hibernate.initialize(operacao.getFuncionalidade());
			// retorno.add(operacao);
			//
			// }

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que consulta as funcionalidades da(s) funcionalidade(s)
	 * princpial(is)
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarFuncionanidadesDependencia(
			Collection idsFuncionalidades) throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select funcDependencia.id "
					+ "from FuncionalidadeDependencia funDepen "
					+ "inner join funDepen.funcionalidade func "
					+ "inner join funDepen.funcionalidadeDependencia funcDependencia "
					+ "where func.id in(:idsFuncionalidades)";

			retorno = session.createQuery(consulta).setParameterList(
					"idsFuncionalidades", idsFuncionalidades).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que consulta as opera��es da(s) funcionalidade(s)
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarOperacoes(Collection idsFuncionalidades)
			throws ErroRepositorioException {
		Collection retorno = new ArrayList();

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select op " + "from Operacao op "
					+ "inner join fetch op.funcionalidade func "
					+ "where func.id in(:idsFuncionalidades)";

			retorno = session.createQuery(consulta).setParameterList(
					"idsFuncionalidades", idsFuncionalidades).list();

			// while (iterator.hasNext()) {
			// Operacao operacao = (Operacao) iterator.next();
			// Hibernate.initialize(operacao.getFuncionalidade());
			// retorno.add(operacao);
			//
			// }

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que consulta as permiss�es especiais do usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuario(Integer idUsuario)
			throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select upe.permissaoEspecial "
					+ "from UsuarioPermissaoEspecial upe "
					+ "where upe.comp_id.usuarioId = :idUsuario "
					+ "order by upe.permissaoEspecial.descricao";

			retorno = session.createQuery(consulta).setInteger("idUsuario",
					idUsuario).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * M�todo que consulta as permiss�es especiais do usu�rio com os parametros
	 * das permiss�es de outro usu�rio
	 * 
	 * @author S�vio Luiz
	 * @date 12/07/2006
	 */
	public Collection pesquisarPermissaoEspecialUsuarioComPermissoes(
			Integer idUsuario, Collection permissoesEspeciais)
			throws ErroRepositorioException {
		Collection retorno = null;

		Iterator iterator = permissoesEspeciais.iterator();
		Collection idsPermissoesEspeciais = new ArrayList();
		while (iterator.hasNext()) {
			PermissaoEspecial permissaoEspecial = (PermissaoEspecial) iterator
					.next();
			idsPermissoesEspeciais.add(permissaoEspecial.getId());
		}

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select pe " + "from UsuarioPermissaoEspecial upe "
					+ "inner join upe.usuario usu "
					+ "inner join upe.permissaoEspecial pe"
					+ "where usu.id = :idUsuario and "
					+ "pe.id in(:idsPermissoesEspeciais) ";

			retorno = session.createQuery(consulta).setInteger("idUsuario",
					idUsuario).setParameterList("idsPermissoesEspeciais",
					idsPermissoesEspeciais).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

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
			throws ErroRepositorioException {

		Iterator iterator = permissoesEspeciais.iterator();
		Collection idsPermissoesEspeciais = new ArrayList();
		while (iterator.hasNext()) {
			PermissaoEspecial permissaoEspecial = (PermissaoEspecial) iterator
					.next();
			idsPermissoesEspeciais.add(permissaoEspecial.getId());
		}

		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select pe " + "from UsuarioPermissaoEspecial upe "
					+ "inner join upe.usuario usu "
					+ "inner join upe.permissaoEspecial pe"
					+ "where usu.id = :idUsuario and "
					+ "pe.id not in(:idsPermissoesEspeciais) ";

			retorno = session.createQuery(consulta).setInteger("idUsuario",
					idUsuario).setParameterList("idsPermissoesEspeciais",
					idsPermissoesEspeciais).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

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
			throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "select distinct grp.id "
					+ "from GrupoFuncionalidadeOperacao gfo "
					+ "inner join gfo.grupo grp "
					+ "inner join gfo.operacao ope "
					+ "inner join gfo.funcionalidade fun "
					+ "where grp.id in(:idsGrupos) and "
					+ "fun.id = :idFuncionalidade and "
					+ "ope.id = :idOperacao";

			retorno = session.createQuery(consulta).setParameterList(
					"idsGrupos", idsGrupos).setInteger("idFuncionalidade",
					idFuncionalidade).setInteger("idOperacao", idOperacao)
					.list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * M�todo que consulta o nome do usu�rio de uma guia de devolu��o, passando
	 * por par�metro o id da guia de devolucao
	 * 
	 * @author Daniel Alves
	 * @date 22/02/2010
	 */
	public String pesquisarUsuarioPorGuiaDevolucao(Integer idGuiaDevolucao)
			throws ErroRepositorioException {

		String retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter a sql
		String consulta = "";

		try {
			consulta = "select usuario.usur_nmlogin as nome from seguranca.usuario usuario "
					+ "inner join arrecadacao.guia_devolucao guia_devolucao on (usuario.usur_id = guia_devolucao.usur_id) "
					+ "inner join arrecadacao.devolucao devolucao on (guia_devolucao.gdev_id = devolucao.gdev_id)"
					+ "and (guia_devolucao.gdev_id = :idGuiaDevolucao)";

			retorno = session.createSQLQuery(consulta).addScalar("nome",
					Hibernate.STRING).setInteger("idGuiaDevolucao",
					idGuiaDevolucao).uniqueResult().toString();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 * M�todo para pesquisar os usu�rios de uma Unidade Organizacional
	 * 
	 * @author Daniel Alves
	 * @date 11/06/2010
	 */
	public Collection pesquisarUsuariosUnidadeOrganizacional(Integer idUnidadeOrganizacional) throws ErroRepositorioException {
		Collection retorno = null;
		
		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {

			consulta = "SELECT usuario "
			 		   + "FROM gcom.seguranca.acesso.usuario.Usuario usuario "
					  + "INNER JOIN usuario.unidadeOrganizacional unidade "
					  + "WHERE unidade.id = :idUnidadeOrganizacional";

			retorno = session.createQuery(consulta)
			.setInteger("idUnidadeOrganizacional", idUnidadeOrganizacional)
			.list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0204] Consultar Conta
	 * 
	 * @author Vivianne Sousa
	 * @date 16/11/2010
	 */
	public Collection pesquisarUsuario(Integer idOperacao,
			Integer idImovel,String referenciaConta)
			throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
			consulta = "SELECT usat.usuario "
				+ "FROM UsuarioAlteracao usat "
				+ "INNER JOIN usat.operacaoEfetuada  opef " 
				+ "where " 
				+ "opef.operacao.id = :idOperacao and " 
				+ "opef.argumentoValor = :idImovel and "
				+ "opef.dadosAdicionais like '%" + referenciaConta + "%' " 
				+ "order by opef.ultimaAlteracao ";

			retorno = session.createQuery(consulta)
			.setInteger("idOperacao", idOperacao)
			.setInteger("idImovel", idImovel)
//			.setString("referenciaConta", referenciaConta)
			.list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public Collection pesquisarGrupoUsuario(Integer idUsuario)throws ErroRepositorioException {
		Collection retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
		
			consulta = "SELECT grupo "
				+ "FROM UsuarioGrupo usuarioGrupo "
				+ "INNER JOIN usuarioGrupo.usuario usuario  "
				+ "INNER JOIN usuarioGrupo.grupo grupo "
				+ "WHERE usuario.id = :idUsuario " 
				+ "AND grupo.indicadorUso = :indicadorUso "
				+ "AND grupo.competenciaRetificacao is not null "
				+ "AND grupo.competenciaRetificacao > 0 "
				+ "ORDER BY grupo.competenciaRetificacao ";
			
			retorno = session.createQuery(consulta)
					.setInteger("idUsuario",idUsuario.intValue())
					.setShort("indicadorUso",ConstantesSistema.INDICADOR_USO_ATIVO).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0146] Manter Conta
	 * [SB0012] � Determinar compet�ncia de retifica��o de consumo
	 * 
	 * @author Vivianne Sousa
	 * @date 16/02/2011
	 */
	public BigDecimal pesquisarMaiorCompetenciaRetificacaoGrupo()throws ErroRepositorioException {
		BigDecimal retorno = null;

		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a vari�vel que vai conter o hql
		String consulta = "";

		try {
		
			consulta = "SELECT max(grupo.competenciaRetificacao) "
						+ "FROM Grupo grupo "
						+ "WHERE grupo.indicadorUso = :indicadorUso ";
			
			retorno = (BigDecimal)session.createQuery(consulta)
					.setShort("indicadorUso",ConstantesSistema.INDICADOR_USO_ATIVO).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Retorna o usu�rio selecionado para rotina batch caso existe
	 * 
	 * @author Paulo Diniz
	 * @date 04/03/2011
	 * 
	 * @return Usuario da rotina batch
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public Usuario pesquisarUsuarioRotinaBatch()throws ErroRepositorioException {
		// cria a cole��o de retorno
		Usuario retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("indicadorUsuarioBatch",Short.parseShort("1")));
			retorno = (Usuario) crit.uniqueResult();
			
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna o usuario pesquisado
		return retorno;

	}
	
	/**
	 * Retorna o usu�rio selecionado para internet caso existe
	 * 
	 * @author Paulo Diniz
	 * @date 04/03/2011
	 * 
	 * @return Usuario da internet
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public Usuario pesquisarUsuarioInternet()throws ErroRepositorioException {
		// cria a cole��o de retorno
		Usuario retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("indicadorUsuarioInternet",Short.parseShort("1")));
			retorno = (Usuario) crit.uniqueResult();
			
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna o usuario pesquisado
		return retorno;

	}
	
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
	public Collection filtrarAutocompleteUsuario(String valor)throws ErroRepositorioException{
		Collection retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = " SELECT usur_id as id,usur_nmusuario as nome from Seguranca.Usuario where ";
			
			valor = valor.trim();
			if(valor.contains("-")){
				valor = valor.split(" - ")[0].trim();
			}
			
			if(Util.validarStringNumerica(valor)){
				
				consulta += "usur_id like '%" + valor + "%'";
				consulta += " order by 1,2 ";
				retorno = session.createSQLQuery(consulta)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("nome",Hibernate.STRING).setMaxResults(200).list();
				
			}else{
				if(valor.length() > 3){
					consulta += "usur_nmusuario like '%" + valor.trim().toUpperCase() + "%'";
					consulta += " order by 1,2 ";
					retorno = session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("nome",Hibernate.STRING).setMaxResults(200).list();
				}
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * [FS-0001] - Validar usu�rio da base para usu�rio logado
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param idUsuario
	 * @param loginUsuario
	 * @return UsuarioBanco
	 * @throws ErroRepositorioException
	 */
	public UsuarioBanco validarUsuarioBaseLogado(Integer idUsuario) throws ErroRepositorioException{
		UsuarioBanco usuarioBanco = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			// 1  .Caso exista usu�rio de banco cadastrado na base para o usu�rio informado 
			// ( Selecionar o usu�rio cadastrado na tabela seguranca.usuario_base com
			// USUR_ID = USUR_ID da tabela seguranca.usuario com
			// USUR_NMLOGIN = <<Login informado durante o login do usu�rio>>)
			consulta = "from UsuarioBanco usurBd " 
					+ "where " 
					+ "usurBd.usuario.id = :idUsuario";
			
			usuarioBanco = (UsuarioBanco) session.createQuery(consulta)
					.setInteger("idUsuario",idUsuario.intValue()).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return usuarioBanco;
	}
	
	/**
	 * [RM6365] - 21/11/2011 - Bruno Barros - Grava��o das altera��es no banco de dados por usu�rio
	 * [UC1252] - Alterar Usu�rio Logado no Banco de Dados
	 * 
	 * [FS-0001] - Validar usu�rio da base para usu�rio logado
	 * 
	 * @author Th�lio Ara�jo
	 * @since 22/11/2011
	 * 
	 * @param idUsuario
	 * @param loginUsuario
	 * @return UsuarioBanco
	 * @throws ErroRepositorioException
	 */
	public String validarUsuarioBaseLogadoDicionarioDados(String loginUsuario, SistemaParametro sistemaParametro) throws ErroRepositorioException{
		String usuario = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		String mensagemRetorno = null;
		
		try {
			if (sistemaParametro.getNomeAbreviadoEmpresa().equals(SistemaParametro.EMPRESA_COMPESA)){
				consulta = "select username as usuario from dba_users " 
						+ "where username = :loginUsuario";
			}else{
				consulta = "select usename as usuario from pg_user " 
						+ "where usename = :loginUsuario";
			}
			
			// 1.1. Caso o usu�rio logado, tenha direitos sobre o dicion�rio de dados 
			// ( Verificar se � poss�vel fazer um SELECT na tabela vis�o DBA_USERS )
			usuario = (String) session.createSQLQuery(consulta)
					.addScalar("usuario", Hibernate.STRING)
					.setString("loginUsuario", loginUsuario).uniqueResult();
			
			// 1.1.1. Caso o usu�rio selecionado no passo 1 existe a como usu�rio do BANCO DE DADOS 
			// ( selecionar o usu�rio da vis�o do dicion�rio de dados dba_users onde 
			// USERNAME = <<login informado ao caso de uso>>
			if (usuario == null){
				// 1.1.2. Caso contr�rio, retorna a seguinte mensagem de erro: 
				// "[0003] - Usu�rio n�o � registrado como usu�rio do banco de dados."
				mensagemRetorno = UsuarioBanco.MENSAGEM_ERRO_0003;
			}
		} catch (HibernateException e) {
			// 1.2. Caso contr�rio, retorna a seguinte mensagem de erro:  
			// "[0002] - O usu�rio do sistema GSAN n�o possui acesso ao dicion�rio de dados."
			if (e.getCause().getMessage().contains("ORA-00942: a tabela ou view n�o existe\n") || 
					e.getCause().getMessage().contains("does not exist")){
				mensagemRetorno = UsuarioBanco.MENSAGEM_ERRO_0002;
			}
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return mensagemRetorno;
	}

	/**
	 * [UC0279] Manter Grupo
	 * RM 3892.1 - Implantar Normas de Senhas no GSAN
	 * @author Am�lia Pessoa
	 * @date 28/12/2011
	 */
	public void excluirPermissoesEspeciais(Integer grupoId)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {

			consulta = "delete GrupoPermissaoEspecial gpe where gpe.comp_id.grupoId = :idGrupo";
					
			// Executa o hql
			session.createQuery(consulta).setInteger(
					"idGrupo", grupoId)
					.executeUpdate();
			
			// Erro no hibernate
		} catch (HibernateException e) {
			// Levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// Fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}
		
	}
	
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 18/01/2012
	 *
	 * @throws ErroRepositorioException
	 */
	public void inserirAcessoLojaVirtual(String codigoTipoAtendimento, String ipAcesso, Short indicadorServicoExecutado) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		Statement stmt = null;
		con = session.connection();
		try {
			stmt = con.createStatement();
			
			String insert = "insert into atendimentopublico.acesso_loja_virtual "
						+ "(aclj_id, "
						+ "aclj_nncodigoatendimento, "
						+ "aclj_ipacesso, "
						+ "aclj_icservicoexecutado, "
						+ "aclj_tmultimaalteracao)  "
						+ "values (" + Util.obterNextValSequence("atendimentopublico.seq_acesso_loja_virtual") 
						+ ", '" + codigoTipoAtendimento + "' , '" + ipAcesso + "' , '" + indicadorServicoExecutado + "' , now() )";
			
			stmt.executeUpdate(insert);

		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no SQL");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1274] Registrar Acessos Realizados na Loja Virtual
	 * 
	 * @author Anderson Cabral
	 * @date 14/07/2013
	 */
	public AcessoLojaVirtual retornaAcessoLojaVirtual(String ip, String codigoTipoAtendimento) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		ArrayList<Integer> retorno = null;
		AcessoLojaVirtual acessoLojaVirtual = null;
		String consulta = "";

		try {
			consulta = "SELECT aclj_id id "
					+ " FROM atendimentopublico.acesso_loja_virtual" 
					+ " where aclj_nncodigoatendimento = :codigoTipoAtendimento AND "
					+ " TO_CHAR(aclj_tmultimaalteracao + INTERVAL '15' MINUTE, 'YYYY-MM-DD HH24-MI-SS') >= TO_CHAR(now(), 'YYYY-MM-DD HH24-MI-SS')"
					+ " and aclj_ipacesso = :ip"
					+ " order by aclj_id desc";

			retorno = (ArrayList<Integer>) session.createSQLQuery(consulta)
					.addScalar("id",Hibernate.INTEGER)
					.setString("ip",ip)
					.setString("codigoTipoAtendimento" , codigoTipoAtendimento).list();

			if (retorno != null && !retorno.isEmpty()) {
				acessoLojaVirtual = new AcessoLojaVirtual();
				acessoLojaVirtual.setId(retorno.get(0));
				acessoLojaVirtual.setNumeroCodigoAtendimento(codigoTipoAtendimento);
				acessoLojaVirtual.setIpAcesso(ip);
			}
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		
		return acessoLojaVirtual;
	}	
	
	/**
	 * [UC1274] Registrar Acessos Realizados na Loja Virtual
	 * [FS0001] - Verificar exist�ncia do registro
	 * @author Arthur Carvalho
	 * @date 09/01/2012
	 */
	public boolean verificarExistenciaAcessoLojaVirtual(String ip, String codigoTipoAtendimento) throws ErroRepositorioException {
		// cria uma sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		Integer retorno = null;
		boolean existe = false;
		// cria a vari�vel que vai conter a sql
		String consulta = "";

		try {
			consulta = "SELECT aclj_id id"
					+ " FROM atendimentopublico.acesso_loja_virtual" 
					+ " where TO_CHAR(aclj_tmultimaalteracao + INTERVAL '15' MINUTE, 'YYYY-MM-DD HH24-MI-SS') >= TO_CHAR(now(), 'YYYY-MM-DD HH24-MI-SS')"
					+ " and aclj_nncodigoatendimento = :codigoTipoAtendimento"
					+ " and aclj_ipacesso = :ip";


			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("id",Hibernate.INTEGER)
					.setString("ip",ip)
					.setString("codigoTipoAtendimento" , codigoTipoAtendimento)
					.setMaxResults(1).uniqueResult();

			if (retorno != null && retorno > 0 ) {
				existe = true;
			}
			
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o com o hibernate
			HibernateUtil.closeSession(session);
		}
		
		
		return existe;
	}
}