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

import gsan.cadastro.cliente.ClienteEndereco;
import gsan.util.ConstantesSistema;
import gsan.util.ErroRepositorioException;
import gsan.util.HibernateUtil;
import gsan.util.Util;
import gsan.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/**
 * O reposit�rio faz a comunica��o com a base de dados atrav�s do hibernate. O
 * cliente usa o reposit�rio como fonte de dados.
 * 
 * @author S�vio Luiz
 * @created 25 de Agosto de 2005
 */

public class RepositorioEnderecoHBM implements IRepositorioEndereco {
	private static IRepositorioEndereco instancia;

	/**
	 * Construtor da classe RepositorioEnderecoHBM
	 */
	private RepositorioEnderecoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioEndereco getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioEnderecoHBM();
		}

		return instancia;
	}

	/**
	 * pesquisa uma cole��o de cep(s) de acordo com o c�digo
	 * 
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCep() throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			retorno = session
					.createQuery(
							"select new gsan.cadastro.endereco.Cep(cep.cepId,cep.codigo,cep.cepTipo) from gsan.cadastro.endereco.Cep as cep left join cep.cepTipo")
					.list();

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
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void inserirImportacaoCep(Collection cepsImportados)
			throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();

		Iterator iteratorCepImportado = cepsImportados.iterator();

		try {
			// int i = 1;
			while (iteratorCepImportado.hasNext()) {
				Cep cep = (Cep) iteratorCepImportado.next();

				session.insert(cep);
				/*
				 * if (i % 50 == 0) { // 20, same as the JDBC batch size //
				 * flush a batch of inserts and release memory: session.flush();
				 * session.clear(); }
				 * 
				 * i++;
				 */

			}
			// session.flush();
			// session.clear();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {

			// session.clear();
			HibernateUtil.closeSession(session);
			// session.close();
		}

	}

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void atualizarImportacaoCep(Collection cepsImportados)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		Iterator iteratorCepImportado = cepsImportados.iterator();

		try {
			int i = 1;
			while (iteratorCepImportado.hasNext()) {
				Cep cep = (Cep) iteratorCepImportado.next();

				// session.setFlushMode(FlushMode.COMMIT);
				session.update(cep);

				if (i % 50 == 0) {
					// 20, same as the JDBC batch size
					// flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}

			}
			session.flush();
			session.clear();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
			// session.close();
		}

	}

	public Collection pesquisarEndereco(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" enderecoReferencia.descricaoAbreviada,"
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" imovel.numeroImovel,"
					+ // 17
					" imovel.complementoEndereco,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao,"
					+ // 23
					" enderecoReferencia.descricao, "
					+ // 24
					" perimetroInicial.id, "
					+ // 25
					" perimetroInicial.nome, "
					+ // 26
					" logradouroTipoPerimetroInicial.descricaoAbreviada, "
					+ // 27
					" logradouroTituloPerimetroInicial.descricaoAbreviada, "
					+ // 28
					" perimetroFinal.id, "
					+ // 29
					" perimetroFinal.nome, "
					+ // 30
					" logradouroTipoPerimetroFinal.descricaoAbreviada, "
					+ // 31
					" logradouroTituloPerimetroFinal.descricaoAbreviada " // 32
					+ "from Imovel imovel "
					+ "left join imovel.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "					
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join imovel.enderecoReferencia enderecoReferencia "
					+ "left join imovel.perimetroInicial perimetroInicial "
					+ "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ "left join imovel.perimetroFinal perimetroFinal "
					+ "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ "where imovel.id = :idImovel";
			retorno = session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection pesquisarEnderecoClienteAbreviado(Integer idCliente)
			throws ErroRepositorioException {
		Collection retorno = null;
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" enderecoReferencia.descricaoAbreviada,"
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" clienteEndereco.numero,"
					+ // 17
					" clienteEndereco.complemento,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao,"
					// 23
					+ " enderecoReferencia.descricao, " // 24
					+ " municipioBairro.id,"
					// 25
					+ " municipioBairro.nome, " // 26
					+ // 27
					" unidadeFederacaoBairro.id,"
					+ // 28
					" unidadeFederacaoBairro.sigla, "
					+ // 29
					" perimetroInicial.id, "
					+ // 30
					" perimetroInicial.nome, "
					+ // 31
					" logradouroTipoPerimetroInicial.descricaoAbreviada, "
					+ // 32
					" logradouroTituloPerimetroInicial.descricaoAbreviada, "
					+ // 33
					" perimetroFinal.id, "
					+ // 34
					" perimetroFinal.nome, "
					+ // 35
					" logradouroTipoPerimetroFinal.descricaoAbreviada, "
					+ // 36
					" logradouroTituloPerimetroFinal.descricaoAbreviada "
					+ "from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join clienteEndereco.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join logradouro.municipio municipio "
					+ "left join bairro.municipio municipioBairro "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join clienteEndereco.enderecoReferencia enderecoReferencia "
					+ "left join municipioBairro.unidadeFederacao unidadeFederacaoBairro "
					+ "left join clienteEndereco.perimetroInicial perimetroInicial "
					+ "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ "left join clienteEndereco.perimetroFinal perimetroFinal "
					+ "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ "inner join clienteEndereco.cliente cliente "
					+ "where cliente.id = :idCliente AND "
					+ "clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia";

			retorno = session
					.createQuery(consulta)
					.setInteger("idCliente", idCliente.intValue())
					.setShort(
							"indicadorEnderecoCorrespondencia",
							ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)
					.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection pesquisarEnderecoFormatado(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome,"
					+ // 0
					" logradouroTipo.descricao,"
					+ // 1
					" logradouroTitulo.descricao,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" enderecoReferencia.descricao,"
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" imovel.numeroImovel,"
					+ // 17
					" imovel.complementoEndereco ,"
					+ // 18
					" logradouro.id, "
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricaoAbreviada,"
					+ // 22
					" logradouroTitulo.descricaoAbreviada,"
					+ // 23
					" enderecoReferencia.descricaoAbreviada, "
					+ // 24
					" enderecoReferencia.id, "
					+ // 25
					" perimetroInicial.id, "
					+ // 26
					" perimetroInicial.nome, "
					+ // 27
					" logradouroTipoPerimetroInicial.descricaoAbreviada, "
					+ // 28
					" logradouroTituloPerimetroInicial.descricaoAbreviada, "
					+ // 29
					" perimetroFinal.id, "
					+ // 30
					" perimetroFinal.nome, "
					+ // 31
					" logradouroTipoPerimetroFinal.descricaoAbreviada, "
					+ // 32
					" logradouroTituloPerimetroFinal.descricaoAbreviada " // 33
					+ "from Imovel imovel "
					+ "left join imovel.logradouroCep logradouroCep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join logradouro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join imovel.enderecoReferencia enderecoReferencia "
					+ "left join logradouroCep.cep cep "
					+ "left join imovel.perimetroInicial perimetroInicial "
					+ "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ "left join imovel.perimetroFinal perimetroFinal "
					+ "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ "where imovel.id = :idImovel";
			retorno = session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	public Collection pesquisarEnderecoAbreviadoCAER(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" enderecoReferencia.descricao,"
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" imovel.numeroImovel,"
					+ // 17
					" imovel.complementoEndereco ,"
					+ // 18
					" logradouro.id, "
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricaoAbreviada,"
					+ // 22
					" logradouroTitulo.descricaoAbreviada,"
					+ // 23
					" enderecoReferencia.descricaoAbreviada, "
					+ // 24
					" enderecoReferencia.id " // 25
					+ "from Imovel imovel "
					+ "left join imovel.logradouroCep logradouroCep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join imovel.setorComercial setorComercial "
					+ "left join logradouro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join imovel.enderecoReferencia enderecoReferencia "
					+ "left join logradouroCep.cep cep "
					+ "where imovel.id = :idImovel";
			retorno = session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

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
	 * @param idCliente
	 *            ,
	 * @return Collection
	 */
	public Collection pesquisarEnderecoFormatadoCliente(Integer idCliente)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select logradouro.nome,"
					+ // 0
					" logradouroTipo.descricao,"
					+ // 1
					" logradouroTitulo.descricao,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" enderecoReferencia.descricao,"
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" clienteEndereco.numero,"
					+ // 17
					" clienteEndereco.complemento ,"
					+ // 18
					" logradouro.id, "
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricaoAbreviada,"
					+ // 22
					" logradouroTitulo.descricaoAbreviada,"
					+ // 23
					" enderecoReferencia.descricaoAbreviada, "
					+ // 24
					" enderecoReferencia.id " // 25
					+ "from ClienteEndereco clienteEndereco "
					+ "inner join clienteEndereco.cliente cliente "
					+ "left join clienteEndereco.logradouroCep logradouroCep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join clienteEndereco.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join logradouro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join clienteEndereco.enderecoReferencia enderecoReferencia "
					+ "left join logradouroCep.cep cep "
					+ "where cliente.id = :idCliente";

			retorno = session.createQuery(consulta)
					.setInteger("idCliente", idCliente.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroCep j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idCep
	 *            , idLogradouro
	 * @return LogradouroCep
	 */
	public LogradouroCep pesquisarAssociacaoLogradouroCep(Integer idCep,
			Integer idLogradouro) throws ErroRepositorioException {

		Collection<LogradouroCep> colecaoLogradouroCep = new ArrayList();
		LogradouroCep retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT lgcp "
					+ "FROM LogradouroCep as lgcp "
					+ "INNER JOIN FETCH lgcp.cep as cep "
					+ "INNER JOIN FETCH lgcp.logradouro as logr "
					+ "LEFT JOIN FETCH logr.logradouroTipo as lgtp "
					+ "LEFT JOIN FETCH logr.logradouroTitulo as lgti "
					+ "WHERE lgcp.cep.cepId = :idCep AND lgcp.logradouro.id = :idLogradouro ";

			colecaoLogradouroCep = session.createQuery(consulta)
					.setInteger("idCep", idCep)
					.setInteger("idLogradouro", idLogradouro).list();

			/*
			 * Iterator<LogradouroCep> iterator = session.createQuery(consulta)
			 * .setInteger("idCep", idCep).setInteger("idLogradouro",
			 * idLogradouro).iterate();
			 * 
			 * LogradouroCep logradouroCepColecao = null;
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * logradouroCepColecao = iterator.next(); // carrega todos os
			 * objetos Hibernate.initialize(logradouroCepColecao.getCep());
			 * Hibernate.initialize(logradouroCepColecao.getLogradouro());
			 * Hibernate.initialize(logradouroCepColecao.getLogradouro()
			 * .getLogradouroTipo());
			 * Hibernate.initialize(logradouroCepColecao.getLogradouro()
			 * .getLogradouroTitulo());
			 * 
			 * colecaoLogradouroCep.add(logradouroCepColecao); }
			 */

			if (colecaoLogradouroCep != null && !colecaoLogradouroCep.isEmpty()) {
				retorno = (LogradouroCep) Util
						.retonarObjetoDeColecao(colecaoLogradouroCep);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Pesquisar associa��o de LogradouroBairro j� existente
	 * 
	 * @author Raphael Rossiter
	 * @data 24/05/2006
	 * 
	 * @param idBairro
	 *            , idLogradouro
	 * @return LogradouroBairro
	 */
	public LogradouroBairro pesquisarAssociacaoLogradouroBairro(
			Integer idBairro, Integer idLogradouro)
			throws ErroRepositorioException {

		Collection<LogradouroBairro> colecaoLogradouroBairro = new ArrayList();
		LogradouroBairro retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT lgbr "
					+ "FROM LogradouroBairro as lgbr "
					+ "INNER JOIN FETCH lgbr.bairro as bairro "
					+ "INNER JOIN FETCH lgbr.logradouro as logr "
					+ "LEFT JOIN FETCH bairro.municipio as muni "
					+ "LEFT JOIN FETCH muni.unidadeFederacao as unfe "
					+ "WHERE lgbr.bairro.id = :idBairro AND lgbr.logradouro.id = :idLogradouro ";

			colecaoLogradouroBairro = session.createQuery(consulta)
					.setInteger("idBairro", idBairro)
					.setInteger("idLogradouro", idLogradouro).list();

			/*
			 * Iterator<LogradouroBairro> iterator =
			 * session.createQuery(consulta) .setInteger("idBairro",
			 * idBairro).setInteger( "idLogradouro", idLogradouro).iterate();
			 * 
			 * LogradouroBairro logradouroBairroColecao = null;
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * logradouroBairroColecao = iterator.next(); // carrega todos os
			 * objetos
			 * Hibernate.initialize(logradouroBairroColecao.getBairro());
			 * Hibernate.initialize(logradouroBairroColecao.getBairro()
			 * .getMunicipio());
			 * Hibernate.initialize(logradouroBairroColecao.getBairro()
			 * .getMunicipio().getUnidadeFederacao());
			 * Hibernate.initialize(logradouroBairroColecao.getLogradouro());
			 * 
			 * colecaoLogradouroBairro.add(logradouroBairroColecao); }
			 */

			if (colecaoLogradouroBairro != null
					&& !colecaoLogradouroBairro.isEmpty()) {
				retorno = (LogradouroBairro) Util
						.retonarObjetoDeColecao(colecaoLogradouroBairro);
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
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
			Integer idLogradouro) throws ErroRepositorioException {

		Collection<LogradouroCep> retorno = new ArrayList();

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "SELECT lgcp "
					+ "FROM LogradouroCep as lgcp "
					+ "INNER JOIN FETCH lgcp.cep as cep "
					+ "INNER JOIN FETCH lgcp.logradouro as logr "
					+ "LEFT JOIN FETCH cep.cepTipo as cptp "
					+ "WHERE lgcp.logradouro.id = :idLogradouro AND lgcp.indicadorUso = :indicadorAtivo ";

			retorno = session
					.createQuery(consulta)
					.setInteger("idLogradouro", idLogradouro)
					.setInteger("indicadorAtivo",
							ConstantesSistema.INDICADOR_USO_ATIVO).list();

			/*
			 * Iterator<LogradouroCep> iterator = session.createQuery(consulta)
			 * .setInteger("idLogradouro", idLogradouro).setInteger(
			 * "indicadorAtivo",
			 * ConstantesSistema.INDICADOR_USO_ATIVO).iterate();
			 * 
			 * LogradouroCep logradouroCepColecao = null;
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * logradouroCepColecao = iterator.next(); // carrega todos os
			 * objetos Hibernate.initialize(logradouroCepColecao.getCep());
			 * Hibernate
			 * .initialize(logradouroCepColecao.getCep().getCepTipo());
			 * Hibernate.initialize(logradouroCepColecao.getLogradouro());
			 * 
			 * retorno.add(logradouroCepColecao); }
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0003] Informar Endere�o
	 * 
	 * Atualiza a situa��o de uma associa��o de LogradouroCep (Motivo: CEP
	 * Inicial de Munic�pio)
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idCep
	 *            , idLogradouro, indicadorUso
	 * @return void
	 */
	public void atualizarIndicadorUsoLogradouroCep(Integer idCep,
			Integer idLogradouro, Short indicadorUso)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizarIndicadorUsoLogradouroCep;

		try {

			atualizarIndicadorUsoLogradouroCep = "UPDATE LogradouroCep "
					+ "SET lgcp_icuso = :indicadorUso, lgcp_tmultimaalteracao = :dataAlteracao "
					+ "WHERE cep_id = :idCep AND logr_id = :idLogradouro";

			session.createQuery(atualizarIndicadorUsoLogradouroCep)
					.setInteger("indicadorUso", indicadorUso)
					.setTimestamp("dataAlteracao", new Date())
					.setInteger("idCep", idCep)
					.setInteger("idLogradouro", idLogradouro).executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Integer pesquisarLogradouroCount(FiltroLogradouro filtroLogradouro)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Integer retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();

			camposOrderBy = filtroLogradouro.getCamposOrderBy();

			filtroLogradouro.limparCamposOrderBy();

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = (Integer) GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroLogradouro,
							"logr",
							"select count(logr.id) "
									+ "from gsan.cadastro.endereco.Logradouro as logr "
									+ "left join logr.logradouroTipo logradouroTipo "
									+ "left join logr.logradouroTitulo logradouroTitulo ",
							session).uniqueResult();

			filtroLogradouro.setCampoOrderBy((String[]) camposOrderBy
					.toArray(new String[camposOrderBy.size()]));

			// erro no hibernate
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

	public Collection<Logradouro> pesquisarLogradouro(
			FiltroLogradouro filtroLogradouro, Integer numeroPaginas)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(
					new CopyOnWriteArraySet<Logradouro>(
							GeradorHQLCondicional
									.gerarCondicionalQuery(
											filtroLogradouro,
											"logr",
											"select logr "
													+ "from gsan.cadastro.endereco.Logradouro as logr "
													+ "left join logr.logradouroTipo logradouroTipo "
													+ "left join logr.logradouroTitulo logradouroTitulo ",
											session)
									.setFirstResult(10 * numeroPaginas)
									.setMaxResults(10).list()));

			// Carrega os objetos informados no filtro
			/*
			 * if
			 * (!filtroLogradouro.getColecaoCaminhosParaCarregamentoEntidades()
			 * .isEmpty()) { PersistenciaUtil.processaObjetosParaCarregamento(
			 * filtroLogradouro .getColecaoCaminhosParaCarregamentoEntidades(),
			 * retorno); }
			 */
			// erro no hibernate
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

	// metodo que serve para fazer a pesquisa do logradouro
	// apartir dos parametros informados

	public Collection pesquisarLogradouroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// Collection resultadoFinal = new ArrayList();
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			String sql = "select distinct(logr) "
					+ " from gsan.cadastro.endereco.Logradouro as logr "
					+ " left join FETCH logr.logradouroTipo logradouroTipo "
					+ " left join FETCH logr.logradouroTitulo logradouroTitulo "
					+ " left join logr.logradouroBairros logradouroBairro "
					+ " left join logr.logradouroCeps logradouroCep "
					+ " inner join FETCH logr.municipio municipio "
					+ " left join logradouroBairro.bairro bairro "
					+ " left join logradouroCep.cep cep "
					+ " left join FETCH logr.programaCalibragem programaCalibragem ";

			// construindo as condicionais do select
			String condicional = "";
			// se foi informado o codigo do municipio
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				condicional = " municipio.id = " + codigoMunicipio + " and ";
			}
			// se foi informado o codigo do bairro
			if (codigoBairro != null && !codigoBairro.trim().equals("")) {
				condicional = condicional + " bairro.codigo = " + codigoBairro
						+ " and ";
			}
			// se foi informado o nome do logradouro
			if (nome != null && !nome.trim().equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logr.nome like '%"
							+ nome.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logr.nome like '"
							+ nome.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o nome popular do logradouro
			if (nomePopular != null && !nomePopular.trim().equals("")) {
				if (tipoPesquisaPopular != null
						&& tipoPesquisaPopular
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logr.nomePopular like '%"
							+ nomePopular.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logr.nomePopular like '"
							+ nomePopular.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o tipo do logradouro
			if (logradouroTipo != null
					&& !logradouroTipo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTipo.id = "
						+ logradouroTipo + " and ";
			}
			// se foi informado o titulo do logradouro
			if (logradouroTitulo != null
					&& !logradouroTitulo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTitulo.id = "
						+ logradouroTitulo + " and ";
			}
			// se foi informado o cep
			if (cep != null && !cep.trim().equals("")) {
				condicional = condicional + " cep.codigo = " + cep + " and ";
			}
			// se foi informado codigo do logradouro
			if (codigoLogradouro != null && !codigoLogradouro.trim().equals("")) {
				condicional = condicional + " logr.id = " + codigoLogradouro
						+ " and ";
			}
			// se foi informado codigo do logradouro
			if (indicadorUso != null && !indicadorUso.trim().equals("")) {
				condicional = condicional + " logr.indicadorUso = "
						+ indicadorUso + " and ";
			}

			// testa se vai haver condicional ou n�o no hql
			if (condicional != null && !condicional.trim().equals("")) {
				sql = sql + " where " + condicional;
			} else {
				sql = sql + " and ";
			}

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = session
					.createQuery(Util.removerUltimosCaracteres(sql, 4))
					.setFirstResult(10 * numeroPaginas).setMaxResults(10)
					.list();

			/*
			 * Iterator iterator = retorno.iterator(); Logradouro logradouro =
			 * null;
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * logradouro = (Logradouro) iterator.next(); // carrega todos os
			 * objetos Hibernate.initialize(logradouro.getMunicipio());
			 * Hibernate.initialize(logradouro.getLogradouroTipo());
			 * Hibernate.initialize(logradouro.getLogradouroTitulo());
			 * 
			 * resultadoFinal.add(logradouro); }
			 */

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		//Fazer loop no retorno setando o grau de importancia
//				Collection<Logradouro> colecaoLogradouro = retorno;
//				ControladorCadastro controladorCadastro = new ControladorCadastro();
//				
//				for (Logradouro logradouro : colecaoLogradouro){
//					if (logradouro.getProgramaCalibragem()==null){
//						logradouro.setProgramaCalibragem(new OSProgramacaoCalibragem());
//					}
//					try {
//						logradouro.getProgramaCalibragem().setGrauImportancia(controladorCadastro.retornarGrauImportancia(logradouro.getId()));
//					} catch (ControladorException e) {
//						e.printStackTrace();
//					}
//				}

		// retorna a cole��o de atividades pesquisada(s)
		// return resultadoFinal;
		//return colecaoLogradouro;
		return retorno;
	}

	public Collection pesquisarLogradouroBairroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		//Collection<LogradouroBairro> retorno2 = null;
		// Collection resultadoFinal = new ArrayList();
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			String sql = "select distinct(logr) "
					+ " from gsan.cadastro.endereco.LogradouroBairro as logr "
					+ " left join FETCH logr.logradouro logradouro "
					+ " left join FETCH logradouro.logradouroTipo logradouroTipo "
					+ " left join FETCH logradouro.logradouroTitulo logradouroTitulo "
					+ " left join logradouro.logradouroCeps logradouroCep "
					+ " left join logradouroCep.cep cep "
					+ " inner join FETCH logradouro.municipio municipio "
					+ " left join FETCH logr.bairro bairro"
					+ " left join FETCH logr.programaCalibragem programaCalibragem ";
			
			
			
			

			// construindo as condicionais do select
			String condicional = "";
			// se foi informado o codigo do municipio
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				condicional = " municipio.id = " + codigoMunicipio + " and ";
			}
			// se foi informado o codigo do bairro
			if (codigoBairro != null && !codigoBairro.trim().equals("")) {
				condicional = condicional + " bairro.codigo = " + codigoBairro
						+ " and ";
			}
			// se foi informado o nome do logradouro
			if (nome != null && !nome.trim().equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logradouro.nome like '%"
							+ nome.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logradouro.nome like '"
							+ nome.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o nome popular do logradouro
			if (nomePopular != null && !nomePopular.trim().equals("")) {
				if (tipoPesquisaPopular != null
						&& tipoPesquisaPopular
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logradouro.nomePopular like '%"
							+ nomePopular.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logradouro.nomePopular like '"
							+ nomePopular.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o tipo do logradouro
			if (logradouroTipo != null
					&& !logradouroTipo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTipo.id = "
						+ logradouroTipo + " and ";
			}
			// se foi informado o titulo do logradouro
			if (logradouroTitulo != null
					&& !logradouroTitulo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTitulo.id = "
						+ logradouroTitulo + " and ";
			}
			// se foi informado o cep
			if (cep != null && !cep.trim().equals("")) {
				condicional = condicional + " cep.codigo = " + cep + " and ";
			}
			// se foi informado codigo do logradouro
			if (codigoLogradouro != null && !codigoLogradouro.trim().equals("")) {
				condicional = condicional + " logradouro.id = " + codigoLogradouro
						+ " and ";
			}
			// se foi informado codigo do logradouro
			if (indicadorUso != null && !indicadorUso.trim().equals("")) {
				condicional = condicional + " logradouro.indicadorUso = "
						+ indicadorUso + " and ";
			}

			// testa se vai haver condicional ou n�o no hql
			if (condicional != null && !condicional.trim().equals("")) {
				sql = sql + " where " + condicional;
			} else {
				sql = sql + " and ";
			}
			
			
			
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = session
					.createQuery(Util.removerUltimosCaracteres(sql, 4))
					.setFirstResult(10 * numeroPaginas).setMaxResults(10)
					.list();

			/*
			 * Iterator iterator = retorno.iterator(); Logradouro logradouro =
			 * null;
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * logradouro = (Logradouro) iterator.next(); // carrega todos os
			 * objetos Hibernate.initialize(logradouro.getMunicipio());
			 * Hibernate.initialize(logradouro.getLogradouroTipo());
			 * Hibernate.initialize(logradouro.getLogradouroTitulo());
			 * 
			 * resultadoFinal.add(logradouro); }
			 */

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	public Collection pesquisarLogradouroCompletoRelatorio(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo,
			String cep, String codigoLogradouro, String indicadorUso,
			String tipoPesquisa, String tipoPesquisaPopular)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// Collection resultadoFinal = new ArrayList();
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			String sql = "select distinct(logr),bairro "
					+ " from gsan.cadastro.endereco.Logradouro as logr "
					+ " left join FETCH logr.logradouroTipo logradouroTipo "
					+ " left join FETCH logr.logradouroTitulo logradouroTitulo "
					+ " left join logr.logradouroBairros logradouroBairro "
					+ " left join logr.logradouroCeps logradouroCep "
					+ " inner join FETCH logr.municipio municipio "
					+ " left join logradouroBairro.bairro bairro "
					+ " left join logradouroCep.cep cep ";

			// construindo as condicionais do select
			String condicional = "";
			// se foi informado o codigo do municipio
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				condicional = " municipio.id = " + codigoMunicipio + " and ";
			}
			// se foi informado o codigo do bairro
			if (codigoBairro != null && !codigoBairro.trim().equals("")) {
				condicional = condicional + " bairro.codigo = " + codigoBairro
						+ " and ";
			}
			// se foi informado o nome do logradouro
			if (nome != null && !nome.trim().equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logr.nome like '%"
							+ nome.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logr.nome like '"
							+ nome.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o nome popular do logradouro
			if (nomePopular != null && !nomePopular.trim().equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logr.nomePopular like '%"
							+ nomePopular.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logr.nomePopular like '"
							+ nomePopular.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o tipo do logradouro
			if (logradouroTipo != null
					&& !logradouroTipo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTipo.id = "
						+ logradouroTipo + " and ";
			}
			// se foi informado o titulo do logradouro
			if (logradouroTitulo != null
					&& !logradouroTitulo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTitulo.id = "
						+ logradouroTitulo + " and ";
			}
			// se foi informado o cep
			if (cep != null && !cep.trim().equals("")) {
				condicional = condicional + " cep.codigo = " + cep + " and ";
			}
			// se foi informado codigo do logradouro
			if (codigoLogradouro != null && !codigoLogradouro.trim().equals("")) {
				condicional = condicional + " logr.id = " + codigoLogradouro
						+ " and ";
			}
			// se foi informado codigo do logradouro
			if (indicadorUso != null && !indicadorUso.trim().equals("")) {
				condicional = condicional + " logr.indicadorUso = "
						+ indicadorUso + " and ";
			}

			// testa se vai haver condicional ou n�o no hql
			if (condicional != null && !condicional.trim().equals("")) {
				sql = sql + " where " + condicional;
			} else {
				sql = sql + " and ";
			}

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = session
					.createQuery(Util.removerUltimosCaracteres(sql, 4)).list();

			/*
			 * Iterator iterator = retorno.iterator(); Logradouro logradouro =
			 * null;
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * logradouro = (Logradouro) iterator.next(); // carrega todos os
			 * objetos Hibernate.initialize(logradouro.getMunicipio());
			 * Hibernate.initialize(logradouro.getLogradouroTipo());
			 * Hibernate.initialize(logradouro.getLogradouroTitulo());
			 * 
			 * resultadoFinal.add(logradouro); }
			 */

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		// return resultadoFinal;
		
		
		return retorno;
		
	}

	// metodo que serve para fazer a pesquisa do logradouro
	// apartir dos parametros informados

	public Integer pesquisarLogradouroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ErroRepositorioException {

		// cria a cole��o de retorno
		Integer retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			String sql = "select count(distinct logr) "
					+ " from gsan.cadastro.endereco.Logradouro as logr "
					+ " left join logr.logradouroTipo logradouroTipo "
					+ " left join logr.logradouroTitulo logradouroTitulo "
					+ " left join logr.logradouroBairros logradouroBairro "
					+ " left join logr.logradouroCeps logradouroCep "
					+ " left join logr.municipio municipio "
					+ " left join logradouroBairro.bairro bairro"
					+ " left join logradouroCep.cep cep ";

			// construindo as condicionais do select
			String condicional = "";
			// se foi informado o codigo do municipio
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				condicional = " municipio.id = " + codigoMunicipio + " and ";
			}
			// se foi informado o codigo do bairro
			if (codigoBairro != null && !codigoBairro.trim().equals("")) {
				condicional = condicional + " bairro.codigo = " + codigoBairro
						+ " and ";
			}
			// se foi informado o nome do logradouro
			if (nome != null && !nome.trim().equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logr.nome like '%"
							+ nome.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logr.nome like '"
							+ nome.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o nome popular do logradouro
			if (nomePopular != null && !nomePopular.trim().equals("")) {
				if (tipoPesquisaPopular != null
						&& tipoPesquisaPopular
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logr.nomePopular like '%"
							+ nomePopular.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logr.nomePopular like '"
							+ nomePopular.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o tipo do logradouro
			if (logradouroTipo != null
					&& !logradouroTipo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTipo.id = "
						+ logradouroTipo + " and ";
			}
			// se foi informado o titulo do logradouro
			if (logradouroTitulo != null
					&& !logradouroTitulo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTitulo.id = "
						+ logradouroTitulo + " and ";
			}
			// se foi informado o cep
			if (cep != null && !cep.trim().equals("")) {
				condicional = condicional + " cep.codigo = " + cep + " and ";
			}
			// se foi informado codigo do logradouro
			if (codigoLogradouro != null && !codigoLogradouro.trim().equals("")) {
				condicional = condicional + " logr.id = " + codigoLogradouro
						+ " and ";
			}
			// se foi informado codigo do logradouro
			if (indicadorUso != null && !indicadorUso.trim().equals("")) {
				condicional = condicional + " logr.indicadorUso = "
						+ indicadorUso + " and ";
			}

			// testa se vai haver condicional ou n�o no hql
			if (condicional != null && !condicional.trim().equals("")) {
				sql = sql + " where " + condicional;
			} else {
				sql = sql + " and ";
			}

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = (Integer) session.createQuery(
					Util.removerUltimosCaracteres(sql, 4)).uniqueResult();

			// erro no hibernate
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

	public Integer pesquisarLogradouroBairroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ErroRepositorioException {

		// cria a cole��o de retorno
		Integer retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			String sql = "select count(distinct logr) "
					+ " from gsan.cadastro.endereco.LogradouroBairro as logr "
					+ " left join logr.logradouro logradouro "
					+ " left join logr.logradouro.logradouroTipo logradouroTipo "
					+ " left join logr.logradouro.logradouroTitulo logradouroTitulo "
					+ " left join logr.logradouro.logradouroBairros logradouroBairro "
					+ " left join logr.logradouro.logradouroCeps logradouroCep "
					+ " left join logr.logradouro.municipio municipio "
					+ " right outer join logr.bairro bairro"
					+ " left join logradouroCep.cep cep ";

			// construindo as condicionais do select
			String condicional = "";
			// se foi informado o codigo do municipio
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				condicional = " municipio.id = " + codigoMunicipio + " and ";
			}
			// se foi informado o codigo do bairro
			if (codigoBairro != null && !codigoBairro.trim().equals("")) {
				condicional = condicional + " bairro.codigo = " + codigoBairro
						+ " and ";
			}
			// se foi informado o nome do logradouro
			if (nome != null && !nome.trim().equals("")) {
				if (tipoPesquisa != null
						&& tipoPesquisa
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logradouro.nome like '%"
							+ nome.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logradouro.nome like '"
							+ nome.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o nome popular do logradouro
			if (nomePopular != null && !nomePopular.trim().equals("")) {
				if (tipoPesquisaPopular != null
						&& tipoPesquisaPopular
								.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
										.toString())) {
					condicional = condicional + " logradouro.nomePopular like '%"
							+ nomePopular.toUpperCase() + "%' and ";
				} else {
					condicional = condicional + " logradouro.nomePopular like '"
							+ nomePopular.toUpperCase() + "%' and ";
				}
			}
			// se foi informado o tipo do logradouro
			if (logradouroTipo != null
					&& !logradouroTipo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTipo.id = "
						+ logradouroTipo + " and ";
			}
			// se foi informado o titulo do logradouro
			if (logradouroTitulo != null
					&& !logradouroTitulo.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				condicional = condicional + " logradouroTitulo.id = "
						+ logradouroTitulo + " and ";
			}
			// se foi informado o cep
			if (cep != null && !cep.trim().equals("")) {
				condicional = condicional + " cep.codigo = " + cep + " and ";
			}
			// se foi informado codigo do logradouro
			if (codigoLogradouro != null && !codigoLogradouro.trim().equals("")) {
				condicional = condicional + " logr.id = " + codigoLogradouro
						+ " and ";
			}
			// se foi informado codigo do logradouro
			if (indicadorUso != null && !indicadorUso.trim().equals("")) {
				condicional = condicional + " logr.indicadorUso = "
						+ indicadorUso + " and ";
			}

			// testa se vai haver condicional ou n�o no hql
			if (condicional != null && !condicional.trim().equals("")) {
				sql = sql + " where " + condicional;
			} else {
				sql = sql + " and ";
			}

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = (Integer) session.createQuery(
					Util.removerUltimosCaracteres(sql, 4)).uniqueResult();

			// erro no hibernate
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
	public Collection pesquisarEnderecoRegistroAtendimentoFormatado(
			Integer idRegistroAtendimento) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select logradouro.nome," // 0
					+ " logradouroTipo.descricao," // 1
					+ " logradouroTitulo.descricao," // 2
					+ " bairro.id," // 3
					+ " bairro.nome," // 4
					+ " municipio.id," // 5
					+ " municipio.nome," // 6
					+ " unidadeFederacao.id," // 7
					+ " unidadeFederacao.sigla," // 8
					+ " cep.cepId," // 9
					+ " cep.logradouro," // 10
					+ " cep.descricaoTipoLogradouro," // 11
					+ " cep.bairro," // 12
					+ " cep.municipio," // 13
					+ " cep.sigla," // 14
					+ " cep.codigo," // 15
					+ " registroAtendimento.complementoEndereco ," // 16
					+ " logradouro.id, " // 17
					+ " logradouroCep.id," // 18
					+ " logradouroBairro.id," // 19
					+ " logradouroTipo.descricaoAbreviada, " // 20
					+ " logradouroTitulo.descricaoAbreviada, " // 21
					+ " registroAtendimento.numeroImovel, " // 22
					+ " perimetroInicial.id, " // 23
					+ " perimetroInicial.nome, " // 24
					+ " logradouroTipoPerimetroInicial.descricaoAbreviada, " // 25
					+ " logradouroTituloPerimetroInicial.descricaoAbreviada, " // 26
					+ " perimetroFinal.id, " // 27
					+ " perimetroFinal.nome, " // 28
					+ " logradouroTipoPerimetroFinal.descricaoAbreviada, " // 29
					+ " logradouroTituloPerimetroFinal.descricaoAbreviada " // 30
					+ " from RegistroAtendimento registroAtendimento "
					+ " left join registroAtendimento.logradouroCep logradouroCep "
					+ " left join logradouroCep.cep cep "
					+ " left join logradouroCep.logradouro logradouro "
					+ " left join logradouro.logradouroTipo logradouroTipo "
					+ " left join logradouro.logradouroTitulo logradouroTitulo "
					+ " left join registroAtendimento.logradouroBairro logradouroBairro "
					+ " left join logradouroBairro.bairro bairro "
					+ " left join bairro.municipio municipio "
					+ " left join municipio.unidadeFederacao unidadeFederacao "
					+ " left join registroAtendimento.perimetroInicial perimetroInicial "
					+ " left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ " left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ " left join registroAtendimento.perimetroFinal perimetroFinal "
					+ " left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ " left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ " where registroAtendimento.id = :idRegistroAtendimento";
			retorno = session
					.createQuery(consulta)
					.setInteger("idRegistroAtendimento",
							idRegistroAtendimento.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

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
	public Collection pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(
			Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select logradouro.nome," // 0
					+ " logradouroTipo.descricao," // 1
					+ " logradouroTitulo.descricao," // 2
					+ " bairro.id," // 3
					+ " bairro.nome," // 4
					+ " municipio.id," // 5
					+ " municipio.nome," // 6
					+ " unidadeFederacao.id," // 7
					+ " unidadeFederacao.sigla," // 8
					+ " cep.cepId," // 9
					+ " cep.logradouro," // 10
					+ " cep.descricaoTipoLogradouro," // 11
					+ " cep.bairro," // 12
					+ " cep.municipio," // 13
					+ " cep.sigla," // 14
					+ " cep.codigo," // 15
					+ " registroAtendimentoSolicitante.complementoEndereco ," // 16
					+ " logradouro.id, " // 17
					+ " logradouroCep.id," // 18
					+ " logradouroBairro.id," // 19
					+ " logradouroTipo.descricao," // 20
					+ " logradouroTitulo.descricao," // 21
					+ " registroAtendimentoSolicitante.numeroImovel," // 22
					+ " perimetroInicial.id, " // 23
					+ " perimetroInicial.nome, " // 24
					+ " logradouroTipoPerimetroInicial.descricaoAbreviada, " // 25
					+ " logradouroTituloPerimetroInicial.descricaoAbreviada, " // 26
					+ " perimetroFinal.id, " // 27
					+ " perimetroFinal.nome, " // 28
					+ " logradouroTipoPerimetroFinal.descricaoAbreviada, " // 29
					+ " logradouroTituloPerimetroFinal.descricaoAbreviada " // 30
					+ " from RegistroAtendimentoSolicitante registroAtendimentoSolicitante "
					+ " left join registroAtendimentoSolicitante.logradouroCep logradouroCep "
					+ " left join logradouroCep.cep cep "
					+ " left join logradouroCep.logradouro logradouro "
					+ " left join logradouro.logradouroTipo logradouroTipo "
					+ " left join logradouro.logradouroTitulo logradouroTitulo "
					+ " left join registroAtendimentoSolicitante.logradouroBairro logradouroBairro "
					+ " left join logradouroBairro.bairro bairro "
					+ " left join bairro.municipio municipio "
					+ " left join municipio.unidadeFederacao unidadeFederacao "
					+ " left join registroAtendimentoSolicitante.perimetroInicial perimetroInicial "
					+ " left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ " left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ " left join registroAtendimentoSolicitante.perimetroFinal perimetroFinal "
					+ " left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ " left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ " where registroAtendimentoSolicitante.id = :idRegistroAtendimentoSolicitante";
			retorno = session
					.createQuery(consulta)
					.setInteger("idRegistroAtendimentoSolicitante",
							idRegistroAtendimentoSolicitante.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

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
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select logradouro.id, " // 0
					+ " logradouro.nome," // 1
					+ " logradouroTipo.descricaoAbreviada," // 2
					+ " logradouroTitulo.descricaoAbreviada," // 3
					+ " cep.cepId," // 4
					+ " cep.codigo" // 5
					+ " from LogradouroCep logradouroCep "
					+ " left join logradouroCep.cep cep "
					+ " left join logradouroCep.logradouro logradouro "
					+ " left join logradouro.logradouroTipo logradouroTipo "
					+ " left join logradouro.logradouroTitulo logradouroTitulo "
					+ " where logradouroCep.id = :idLogradouroCep";
			retorno = session.createQuery(consulta)
					.setInteger("idLogradouroCep", idLogradouroCep.intValue())
					.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

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
			Integer idLogradouroBairro) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select bairro.id," // 0
					+ " bairro.nome," // 1
					+ " municipio.id," // 2
					+ " municipio.nome," // 3
					+ " unidadeFederacao.id," // 4
					+ " unidadeFederacao.sigla" // 5
					+ " from LogradouroBairro logradouroBairro "
					+ " left join logradouroBairro.bairro bairro "
					+ " left join bairro.municipio municipio "
					+ " left join municipio.unidadeFederacao unidadeFederacao "
					+ " where logradouroBairro.id = :idLogradouroBairro";

			retorno = session
					.createQuery(consulta)
					.setInteger("idLogradouroBairro",
							idLogradouroBairro.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

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
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome,"
					+ // 0
					" logradouroTipo.descricaoAbreviada,"
					+ // 1
					" logradouroTitulo.descricaoAbreviada,"
					+ // 2
					" bairro.id,"
					+ // 3
					" bairro.nome,"
					+ // 4
					" municipio.id,"
					+ // 5
					" municipio.nome,"
					+ // 6
					" unidadeFederacao.id,"
					+ // 7
					" unidadeFederacao.sigla,"
					+ // 8
					" enderecoReferencia.descricaoAbreviada,"
					+ // 9
					" cep.cepId,"
					+ // 10
					" cep.logradouro,"
					+ // 11
					" cep.descricaoTipoLogradouro,"
					+ // 12
					" cep.bairro,"
					+ // 13
					" cep.municipio,"
					+ // 14
					" cep.sigla, "
					+ // 15
					" cep.codigo, "
					+ // 16
					" clienteEndereco.numero,"
					+ // 17
					" clienteEndereco.complemento,"
					+ // 18
					" logradouro.id,"
					+ // 19
					" logradouroCep.id,"
					+ // 20
					" logradouroBairro.id,"
					+ // 21
					" logradouroTipo.descricao,"
					+ // 22
					" logradouroTitulo.descricao,"
					+ // 23
					" enderecoReferencia.descricao, " // 24
					+ // 25
					" perimetroInicial.id, "
					+ // 26
					" perimetroInicial.nome, "
					+ // 27
					" logradouroTipoPerimetroInicial.descricaoAbreviada, "
					+ // 28
					" logradouroTituloPerimetroInicial.descricaoAbreviada, "
					+ // 29
					" perimetroFinal.id, "
					+ // 30
					" perimetroFinal.nome, "
					+ // 31
					" logradouroTipoPerimetroFinal.descricaoAbreviada, "
					+ // 32
					" logradouroTituloPerimetroFinal.descricaoAbreviada "
					+ "from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join clienteEndereco.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "left join bairro.municipio municipio "
					+ "left join municipio.unidadeFederacao unidadeFederacao "
					+ "left join clienteEndereco.enderecoReferencia enderecoReferencia "
					+ "left join clienteEndereco.perimetroInicial perimetroInicial "
					+ "left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ "left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ "left join clienteEndereco.perimetroFinal perimetroFinal "
					+ "left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ "left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ "inner join clienteEndereco.cliente cliente "
					+ "where cliente.id = :idCliente  ";
			retorno = session.createQuery(consulta)
					.setInteger("idCliente", idCliente.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

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
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterEnderecoAbreviadoImovel(Integer idImovel)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome, " // 0
					+ "imovel.numeroImovel, " // 1
					+ "bairro.nome " // 2
					+ "from Imovel imovel "
					+ "left join imovel.logradouroCep logradouroCep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join imovel.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "where imovel.id = :idImovel";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue())
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

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
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome, " // 0
					+ "ra.numeroImovel, " // 1
					+ "bairro.nome " // 2
					+ "from RegistroAtendimento ra "
					+ "left join ra.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.logradouro logradouro "
					+ "left join logradouroBairro.bairro bairro "
					+ "where ra.id = :idRA";

			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idRA", idRA.intValue()).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * Pesquisar o cep pelo codigo do cep
	 * 
	 * @author S�vio Luiz
	 * @date 05/11/2007
	 * 
	 */

	public Cep pesquisarCep(Integer codigoCep) throws ErroRepositorioException {
		Cep retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select cep " + "from Cep cep "
					+ "where cep.codigo = :codigoCep";
			retorno = (Cep) session.createQuery(consulta)
					.setInteger("codigoCep", codigoCep).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Verifica a exist�ncia do endere�o de correspond�ncia do cliente pelo seu
	 * id
	 * 
	 */
	public boolean verificarExistenciaClienteEndereco(Integer idCliente)
			throws ErroRepositorioException {

		Integer idClienteEndereco = null;

		boolean existeClienteEndereco = false;

		String hql = "";

		Session session = HibernateUtil.getSession();

		try {

			hql = "select cliEnd.id "
					+ " from ClienteEndereco cliEnd "
					+ " inner JOIN cliEnd.cliente cli "
					+ " where cli.id = :idCliente "
					+ " and cliEnd.indicadorEnderecoCorrespondencia = "
					+ ClienteEndereco.INDICADOR_ENDERECO_CORRESPONDENCIA
							.toString();

			idClienteEndereco = (Integer) session.createQuery(hql)
					.setInteger("idCliente", idCliente).setMaxResults(1)
					.uniqueResult();

			if (idClienteEndereco != null && !idClienteEndereco.equals("")) {
				existeClienteEndereco = true;
			}

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return existeClienteEndereco;

	}

	/**
	 * Retorna a cole��o de Logradouro Tipos presentes na tabela CEP
	 * 
	 * @author: Vinicius Medeiros
	 */
	public Collection retornaTipoLogradouroPeloCep()
			throws ErroRepositorioException {

		Collection colecaoLogradouroTipo = null;

		String hql = "";

		Session session = HibernateUtil.getSession();

		try {

			hql = "select distinct(descricaoTipoLogradouro) from Cep";

			colecaoLogradouroTipo = (Collection) session.createQuery(hql)
					.list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecaoLogradouroTipo;

	}

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: R�mulo Aur�lio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(
			Integer idCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection<Object[]> retorno = null;

		String consulta = null;
		try {
			consulta = "select logradouro.logr_nmlogradouro as nomeLogradouro, "// 0
					+ "clienteEndereco.cled_dscomplementoendereco as complementoEndereco, " // 1
					+ "cep.cep_cdcep as codigoCep, " // 2
					+ "bairro.bair_nmbairro as nomeBairro, " // 3
					+ "clienteEndereco.cled_nnimovel as numeroImovel, " // 4
					+ "logradouro.lgtp_id as idTipoLogradouro " // 5
					+ "from cadastro.cliente_endereco clienteEndereco "
					+ "left outer join cadastro.logradouro_bairro logradouroBairro on logradouroBairro.lgbr_id = clienteEndereco.lgbr_id "
					+ "left outer join cadastro.logradouro logradouro on logradouro.logr_id = logradouroBairro.logr_id "
					+ "left outer join cadastro.bairro bairro on bairro.bair_id = logradouroBairro.bair_id "
					+ "left outer join cadastro.logradouro_cep logradouroCep on logradouroCep.lgcp_id  = clienteEndereco.lgcp_id "
					+ "left outer join cadastro.cep cep on cep.cep_id   = logradouroCep.cep_id "
					+ "where clienteEndereco.clie_id = :idCliente and clienteEndereco.cled_icenderecocorrespondencia = 1 ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("nomeLogradouro", Hibernate.STRING)
					.addScalar("complementoEndereco", Hibernate.STRING)
					.addScalar("codigoCep", Hibernate.INTEGER)
					.addScalar("nomeBairro", Hibernate.STRING)
					.addScalar("numeroImovel", Hibernate.STRING)
					.addScalar("idTipoLogradouro", Hibernate.INTEGER)
					.setInteger("idCliente", idCliente).list();
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Obter Logradouro(Tipo + Nome Logradouro + T�tulo)
	 */

	public Collection pesquisarLogradouro(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome," // 0
					+ "logradouroTipo.descricao," // 1
					+ "logradouroTitulo.descricao, " // 2
					+ "logradouroTipo.id, "// 3
					+ "logradouroTitulo.id, "// 4
					+ "municipio.id,"// 5
					+ "municipio.nome,"// 6
					+ "unidadeFederacao.id,"// 7
					+ "unidadeFederacao.sigla, "// 8
					+ "logradouroCep.id, " //9
					+ "logradouroBairro.id " //10
					+ "from Imovel imovel "
					+ "left join imovel.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "left join logradouro.logradouroBairros logradouroBairro "
					+ "inner join logradouro.municipio municipio "
					+ "inner join municipio.unidadeFederacao unidadeFederacao "
					+ "where imovel.id = :idImovel";
			retorno = session.createQuery(consulta)
					.setInteger("idImovel", idImovel.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Obter Logradouro(Tipo + Nome Logradouro + T�tulo)
	 */
	public Collection pesquisarLogradouroCliente(Integer idCliente)
			throws ErroRepositorioException {
		Collection retorno = null;
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta = null;
		try {
			consulta = "select logradouro.nome,"// 0
					+ "logradouroTipo.descricao," // 1
					+ "logradouroTitulo.descricao, "// 2
					+ "logradouroTipo.id, "// 3
					+ "logradouroTitulo.id, "// 4
					+ "municipio.id,"// 5
					+ "municipio.nome,"// 6
					+ "unidadeFederacao.id,"// 7
					+ "unidadeFederacao.sigla "// 8
					+ "from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.logradouroCep logradouroCep "
					+ "left join logradouroCep.cep cep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join logradouro.logradouroTipo logradouroTipo "
					+ "left join logradouro.logradouroTitulo logradouroTitulo "
					+ "inner join logradouro.municipio municipio "
					+ "inner join municipio.unidadeFederacao unidadeFederacao "
					+ "inner join clienteEndereco.cliente cliente "
					+ "where cliente.id = :idCliente AND "
					+ "clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia";

			retorno = session
					.createQuery(consulta)
					.setInteger("idCliente", idCliente.intValue())
					.setShort(
							"indicadorEnderecoCorrespondencia",
							ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)
					.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

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
			Integer idraReiteracao) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " select logradouro.nome," // 0
					+ " logradouroTipo.descricao," // 1
					+ " logradouroTitulo.descricao," // 2
					+ " bairro.id," // 3
					+ " bairro.nome," // 4
					+ " municipio.id," // 5
					+ " municipio.nome," // 6
					+ " unidadeFederacao.id," // 7
					+ " unidadeFederacao.sigla," // 8
					+ " cep.cepId," // 9
					+ " cep.logradouro," // 10
					+ " cep.descricaoTipoLogradouro," // 11
					+ " cep.bairro," // 12
					+ " cep.municipio," // 13
					+ " cep.sigla," // 14
					+ " cep.codigo," // 15
					+ " raReiteracao.complementoEndereco ," // 16
					+ " logradouro.id, " // 17
					+ " logradouroCep.id," // 18
					+ " logradouroBairro.id," // 19
					+ " logradouroTipo.descricao," // 20
					+ " logradouroTitulo.descricao," // 21
					+ " raReiteracao.numeroImovel," // 22
					+ " perimetroInicial.id, " // 23
					+ " perimetroInicial.nome, " // 24
					+ " logradouroTipoPerimetroInicial.descricaoAbreviada, " // 25
					+ " logradouroTituloPerimetroInicial.descricaoAbreviada, " // 26
					+ " perimetroFinal.id, " // 27
					+ " perimetroFinal.nome, " // 28
					+ " logradouroTipoPerimetroFinal.descricaoAbreviada, " // 29
					+ " logradouroTituloPerimetroFinal.descricaoAbreviada " // 30

					+ " from RAReiteracao raReiteracao "
					+ " left join raReiteracao.logradouroCep logradouroCep "
					+ " left join logradouroCep.cep cep "
					+ " left join logradouroCep.logradouro logradouro "
					+ " left join logradouro.logradouroTipo logradouroTipo "
					+ " left join logradouro.logradouroTitulo logradouroTitulo "
					+ " left join raReiteracao.logradouroBairro logradouroBairro "
					+ " left join logradouroBairro.bairro bairro "
					+ " left join bairro.municipio municipio "
					+ " left join municipio.unidadeFederacao unidadeFederacao "
					+ " left join raReiteracao.perimetroInicial perimetroInicial "
					+ " left join perimetroInicial.logradouroTipo logradouroTipoPerimetroInicial "
					+ " left join perimetroInicial.logradouroTitulo logradouroTituloPerimetroInicial "
					+ " left join raReiteracao.perimetroFinal perimetroFinal "
					+ " left join perimetroFinal.logradouroTipo logradouroTipoPerimetroFinal "
					+ " left join perimetroFinal.logradouroTitulo logradouroTituloPerimetroFinal "
					+ " where raReiteracao.id = :idraReiteracao";

			retorno = session.createQuery(consulta)
					.setInteger("idraReiteracao", idraReiteracao).list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 20/05/2011
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(
			Integer idCliente) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection<Object[]> retorno = null;

		String consulta = null;
		try {
			consulta = "select logradouro.logr_nmlogradouro as nomeLogradouro, "// 0
					+ "clienteEndereco.cled_dscomplementoendereco as complementoEndereco, " // 1
					+ "cep.cep_cdcep as codigoCep, " // 2
					+ "bairro.bair_nmbairro as nomeBairro, " // 3
					+ "clienteEndereco.cled_nnimovel as numeroImovel, " // 4
					+ "logradouro.lgtp_id as idTipoLogradouro, " // 5
					+ "municipio.muni_nmmunicipio as nomeMunicipio " // 6
					+ "from cadastro.cliente_endereco clienteEndereco "
					+ "left outer join cadastro.logradouro_bairro logradouroBairro on logradouroBairro.lgbr_id = clienteEndereco.lgbr_id "
					+ "left outer join cadastro.logradouro logradouro on logradouro.logr_id = logradouroBairro.logr_id "
					+ "left outer join cadastro.municipio municipio on logradouro.muni_id = municipio.muni_id "
					+ "left outer join cadastro.bairro bairro on bairro.bair_id = logradouroBairro.bair_id "
					+ "left outer join cadastro.logradouro_cep logradouroCep on logradouroCep.lgcp_id  = clienteEndereco.lgcp_id "
					+ "left outer join cadastro.cep cep on cep.cep_id   = logradouroCep.cep_id "
					+ "where clienteEndereco.clie_id = :idCliente and clienteEndereco.cled_icenderecocorrespondencia = 1 ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("nomeLogradouro", Hibernate.STRING)
					.addScalar("complementoEndereco", Hibernate.STRING)
					.addScalar("codigoCep", Hibernate.INTEGER)
					.addScalar("nomeBairro", Hibernate.STRING)
					.addScalar("numeroImovel", Hibernate.STRING)
					.addScalar("idTipoLogradouro", Hibernate.INTEGER)
					.addScalar("nomeMunicipio", Hibernate.STRING)
					.setInteger("idCliente", idCliente).list();
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 20/05/2011
	 */
	public Object[] obterEnderecoAbreviadoCliente(Integer idCliente)
			throws ErroRepositorioException {
		
		Object[] retorno = null;
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta = null;
		try {
			consulta = " select logradouro.nome," //0
					+  " clienteEndereco.numero," //1
					+  " bairro.nome " //2					
					+ "from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.logradouroCep logradouroCep "
					+ "left join logradouroCep.logradouro logradouro "
					+ "left join clienteEndereco.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "inner join clienteEndereco.cliente cliente "
					+ "where cliente.id = :idCliente AND "
					+ "clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia";

			
			retorno = (Object[]) session.createQuery(consulta)
					.setInteger("idCliente", idCliente.intValue())
					.setShort(
							"indicadorEnderecoCorrespondencia",ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Recuperar Nome do Bairro do Cliente
	 * 
	 * @author: Rodrigo Cabral
	 * @date: 10/07/2014
	 */
	public String pesquisarBairroCliente(Integer idCliente)
			throws ErroRepositorioException {
		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select bairro.nome "
					+ "from ClienteEndereco clienteEndereco "
					+ "left join clienteEndereco.logradouroBairro logradouroBairro "
					+ "left join logradouroBairro.bairro bairro "
					+ "inner join clienteEndereco.cliente cliente "
					+ "where cliente.id = :idCliente AND "
					+ "clienteEndereco.indicadorEnderecoCorrespondencia = :indicadorEnderecoCorrespondencia ";
			retorno = (String) session.createQuery(consulta)
					.setInteger("idCliente", idCliente.intValue())
					.setShort("indicadorEnderecoCorrespondencia",ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}
}
