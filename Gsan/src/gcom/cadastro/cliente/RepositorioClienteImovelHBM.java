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
package gcom.cadastro.cliente;

import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.GeradorHQLCondicional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public class RepositorioClienteImovelHBM implements IRepositorioClienteImovel {

	private static IRepositorioClienteImovel instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioClienteImovelHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioClienteImovel getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioClienteImovelHBM();
		}

		return instancia;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param clienteImovel
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			session.save(clienteImovel);
			session.flush();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
			// session.close();
		}

	}

	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteImovel,
							"clienteImovel",
							"select distinct new gcom.cadastro.cliente.ClienteImovelSimplificado(clienteImovel.imovel.id,clienteImovel.imovel.numeroImovel,clienteImovel.imovel.logradouroCep, clienteImovel.imovel.logradouroBairro,"
									+ "clienteImovel.imovel.quadra,clienteImovel.imovel.enderecoReferencia,clienteImovel.imovel.complementoEndereco,clienteImovel.cliente.nome,clienteImovel.cliente.id,clienteImovel.imovel.setorComercial,clienteImovel.imovel.localidade,clienteImovel.dataFimRelacao,clienteImovel.imovel.ultimaAlteracao) "
									+ "from gcom.cadastro.cliente.ClienteImovel as clienteImovel "
									+ "left join clienteImovel.imovel.logradouroCep "
									+ "left join clienteImovel.imovel.logradouroBairro "
									+ "left join clienteImovel.cliente "
									+ "left join clienteImovel.imovel.setorComercial "
									+ "left join clienteImovel.imovel.enderecoReferencia "
									+ "left join clienteImovel.imovel.quadra "
									+ "left join clienteImovel.imovel.localidade ",

							session).setFirstResult(10 * numeroPagina)
					.setMaxResults(10).list()));

			// Carrega os objetos informados no filtro
/*			if (!filtroClienteImovel
					.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil.processaObjetosParaCarregamento(
						filtroClienteImovel
								.getColecaoCaminhosParaCarregamentoEntidades(),
						retorno);
			}
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
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public int pesquisarQuantidadeClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Integer quantidade = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		filtroClienteImovel.getColecaoCaminhosParaCarregamentoEntidades().clear();
		
		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			quantidade = (Integer)GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteImovel,
							"clienteImovel",
							"select count(clienteImovel.imovel.id) "
									+ "from gcom.cadastro.cliente.ClienteImovel as clienteImovel ",

							session).uniqueResult();


			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)

		quantidade.intValue();

		return quantidade;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * @param clienteRelacaoTipo
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarClienteImovelResponsavelEsferaPoder(
			Imovel imovel, ClienteRelacaoTipo clienteRelacaoTipo)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select ci.id, c.id, "
					+ "c.nome, ci.dataFimRelacao from "
					+ "ClienteImovel ci "
					+ "inner join ci.cliente c "
					+ "inner join c.clienteTipo.esferaPoder ep "
					+ "where ci.imovel.id = :imovel ep.id is not null "
					+ "and ci.clienteRelacaoTipo.id = :clienteRelacaoTipo and ci.dataFimRelacao is null ";

			retorno = session.createQuery(consulta).setInteger("imovel",
					imovel.getId().intValue()).setInteger("clienteRelacaoTipo",
					clienteRelacaoTipo.getId().intValue()).list();

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
	 * Pesquisa o nome do cliente a partir do im�vel Autor: S�vio Luiz Data:
	 * 21/12/2005
	 */
	public String pesquisarNomeClientePorImovel(Integer idImovel)
			throws ErroRepositorioException {
		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select clienteImovel.cliente.nome "
					+ "from ClienteImovel clienteImovel "
					+ "left join clienteImovel.cliente cliente "
					+ "left join clienteImovel.imovel imovel "
					+ "where clienteImovel.clienteRelacaoTipo.id = :tipoUsuario  and "
					+ "clienteImovel.dataFimRelacao = null and "
					+ "imovel.id = :idImovel";
			retorno = (String) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setShort("tipoUsuario",
					ClienteRelacaoTipo.USUARIO).setMaxResults(1).uniqueResult();

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
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteImovel,
							"clienteImovel",
							"select distinct new gcom.cadastro.cliente.ClienteImovelSimplificado(clienteImovel.imovel.id,clienteImovel.imovel.numeroImovel,clienteImovel.imovel.logradouroCep, clienteImovel.imovel.logradouroBairro,"
									+ "clienteImovel.imovel.quadra,clienteImovel.imovel.enderecoReferencia,clienteImovel.imovel.complementoEndereco,clienteImovel.cliente.nome,clienteImovel.cliente.id,clienteImovel.imovel.setorComercial,clienteImovel.imovel.localidade,clienteImovel.dataFimRelacao,clienteImovel.imovel.ultimaAlteracao) "
									+ "from gcom.cadastro.cliente.ClienteImovel as clienteImovel "
									+ "left join clienteImovel.imovel.logradouroCep "
									+ "left join clienteImovel.imovel.logradouroBairro "
									+ "left join clienteImovel.cliente "
									+ "left join clienteImovel.imovel.setorComercial "
									+ "left join clienteImovel.imovel.enderecoReferencia "
									+ "left join clienteImovel.imovel.quadra "
									+ "left join clienteImovel.imovel.localidade ",

							session).list()));

			// Carrega os objetos informados no filtro
/*			if (!filtroClienteImovel
					.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil.processaObjetosParaCarregamento(
						filtroClienteImovel
								.getColecaoCaminhosParaCarregamentoEntidades(),
						retorno);
			}
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
	
	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovelRelatorio(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();		

		try {
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.SETOR_COMERCIAL);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.QUADRA);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.hidrometroInstalacaoHistorico");	
			
			
			filtroClienteImovel.setCampoOrderBy(
					FiltroClienteImovel.LOCALIDADE_ID,
					FiltroClienteImovel.SETOR_COMERCIAL_CODIGO,
					FiltroClienteImovel.QUADRA_NUMERO,
					FiltroClienteImovel.LOTE,
					FiltroClienteImovel.SUBLOTE
					);
			
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional
					.gerarCondicionalQuery(
							filtroClienteImovel,							
							"clienteImovel",
							"select imovel "
									+ "from gcom.cadastro.cliente.ClienteImovel as clienteImovel "
									+ "inner join clienteImovel.imovel as imovel "
									+ "inner join fetch imovel.imovelPerfil "
									+ "inner join fetch imovel.ligacaoAguaSituacao "	
									+ "inner join fetch imovel.ligacaoEsgotoSituacao "
									+ "inner join fetch imovel.setorComercial setor "
									+ "left join fetch imovel.enderecoReferencia "
									+ "inner join fetch imovel.quadra quad "
									+ "inner join fetch imovel.quadra.rota "
									+ "inner join fetch imovel.localidade loca "
									+ "inner join fetch loca.gerenciaRegional "
									+ "inner join fetch loca.unidadeNegocio "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometroProtecao "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometroLocalInstalacao "									
									+ "left join fetch imovel.imovelPrincipal "	
									+ "left join fetch imovel.pavimentoCalcada "
									+ "left join fetch imovel.pavimentoRua "
									+ "left join fetch imovel.despejo "
									+ "left join fetch imovel.pocoTipo "
									+ "left join fetch imovel.ligacaoEsgoto.ligacaoEsgotoDiametro "
									+ "left join fetch imovel.ligacaoEsgoto.ligacaoEsgotoMaterial "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometro "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroMarca "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroDiametro "
									+ "left join fetch imovel.hidrometroInstalacaoHistorico.hidrometro.hidrometroTipo "
									+ "left join fetch imovel.reservatorioVolumeFaixaSuperior "
									+ "left join fetch imovel.reservatorioVolumeFaixaInferior "
									+ "left join fetch imovel.piscinaVolumeFaixa "
									+ "left join fetch imovel.areaConstruidaFaixa "									
									+ "left join fetch imovel.ligacaoAgua lagu " 
									+ "left join fetch lagu.ligacaoAguaDiametro "
									+ "left join fetch lagu.ligacaoAguaMaterial "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometro "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometro.hidrometroCapacidade "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometro.hidrometroMarca "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometro.hidrometroDiametro "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometro.hidrometroTipo "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometroLocalInstalacao "
									+ "left join fetch lagu.hidrometroInstalacaoHistorico.hidrometroProtecao "
									+ "left join fetch imovel.logradouroCep as lgcp "									
									+ "left join fetch lgcp.logradouro as lgrd "
									+ "left join fetch lgrd.logradouroTipo " 
									+ "left join fetch lgrd.logradouroTitulo " 	
									+ "left join fetch lgcp.cep "									
									+ "left join fetch imovel.logradouroBairro lgbr "
									+ "left join fetch lgbr.logradouro "
									+ "left join fetch lgbr.bairro bair "
									+ "left join fetch bair.municipio munc "
									+ "left join fetch munc.unidadeFederacao "
									+ "left join fetch clienteImovel.cliente " 
									+ "left join fetch imovel.perimetroInicial "
									+ "left join fetch imovel.perimetroFinal "
									+ "left join fetch imovel.perimetroInicial.logradouroTitulo "
									+ "left join fetch imovel.perimetroFinal.logradouroTitulo ",
									session).list()));
			
			// Carrega os objetos informados no filtro
/*			if (!filtroClienteImovel
					.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil.processaObjetosParaCarregamento(
						filtroClienteImovel
								.getColecaoCaminhosParaCarregamentoEntidades(),
						retorno);
			}
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
	
	/**
	 * [UC0544] Gerar Arwuivo Texto do Faturamento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 4/04/2006
	 * 
	 * 
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelGerarArquivoFaturamento()
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select distinct(clim.cliente.id), clim.cliente.nome"
					+ " from ClienteImovel clim"
					+ " left join clim.cliente clie"
					+ " left join clim.imovel imov"
					+ " where clim.cliente.indicadorGeraArquivoTexto =:indicadorGera " 
					+ " and clim.dataFimRelacao IS NULL "
					+ " and clim.clienteRelacaoTipo.id =:idRelacao";

			retorno = (Collection) session.createQuery(consulta)
				.setInteger("indicadorGera", Cliente.GERA_ARQUIVO_TEXTO_SIM)
				.setInteger("idRelacao", ClienteRelacaoTipo.RESPONSAVEL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
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
	 * @param idCliente, idImovel
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovel(Integer idCliente, Integer idImovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " select clim.cliente.id, clim.cliente.nome"
					+ " from ClienteImovel clim"
					+ " left join clim.cliente clie"
					+ " left join clim.imovel imov"
					+ " where clie.id  = :idCliente AND imov.id = :idImovel AND clim.dataFimRelacao IS NULL ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Pesquisa o nome, cnpj e id do cliente a partir do im�vel Autor: Rafael Corr�a Data:
	 * 25/09/2006
	 */
	public Object[] pesquisarDadosClienteRelatorioParcelamentoPorImovel(Integer idImovel)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select cliente.id, cliente.nome, " 
					+ "cliente.cpf, cliente.cnpj, orgaoExpedidorRg.descricao, unidadeFederacao.sigla, cliente.rg "
					+ "from ClienteImovel clienteImovel "
					+ "left join clienteImovel.cliente cliente "
					+ "left join clienteImovel.imovel imovel "
					+ "left join cliente.orgaoExpedidorRg orgaoExpedidorRg "
					+ "left join cliente.unidadeFederacao unidadeFederacao "
					+ "where clienteImovel.clienteRelacaoTipo.id = :tipoUsuario  and "
					+ "clienteImovel.dataFimRelacao = null and "
					+ "imovel.id = :idImovel";
			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setShort("tipoUsuario",
					ClienteRelacaoTipo.USUARIO).uniqueResult();

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
	 * Autor: S�vio Luiz Data:
	 * 27/11/2006
	 */
	public Collection pesquisarParmsClienteImovel(Integer idImovel)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select cliente.id, " 
					+ "clienteImovel.clienteRelacaoTipo.id "
					+ "from ClienteImovel clienteImovel "
					+ "left join clienteImovel.cliente cliente "
					+ "left join clienteImovel.imovel imovel "
					+ "where imovel.id = :idImovel and clienteImovel.dataFimRelacao = null ";
			retorno =  session.createQuery(consulta).setInteger(
					"idImovel", idImovel).list();

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
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Cliente, indicador de uso, motivo
	 * do fim da rela��o, pelo perfil do im�vel e pelo tipo da rela��o do cliente carregando o im�vel
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente)
			throws ErroRepositorioException {
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			
			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " INNER JOIN FETCH clienteImovel.imovel imov "
					+ " INNER JOIN FETCH imov.setorComercial sc "
					+ " INNER JOIN FETCH sc.municipio muni "
					+ " INNER JOIN imov.imovelPerfil imovPerfil "
					+ " LEFT JOIN clienteImovel.clienteRelacaoTipo crtp "
					+ " LEFT JOIN clienteImovel.clienteImovelFimRelacaoMotivo cliImovFimRelMotivo "
					+ " WHERE cliente.id = :idCliente " 
//					+ " AND imovPerfil.id = " + ImovelPerfil.TARIFA_SOCIAL.toString()
					+ " AND cliente.indicadorUso = " + ConstantesSistema.INDICADOR_USO_ATIVO.toString()
					+ " AND crtp.id = " + ClienteRelacaoTipo.USUARIO.toString()
					+ " AND cliImovFimRelMotivo.id is null "
					+ " AND imov.quantidadeEconomias = 1 ";
			
			retorno = session.createQuery(consulta).setInteger("idCliente",
					idCliente).list();

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
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel carregando o im�vel, o cliente, o perfil do im�vel, 
	 * o org�o expedidor do RG e a unidade da federa��o
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(
			Integer idImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.imovel imov "
					+ " INNER JOIN FETCH imov.setorComercial sc "
					+ " INNER JOIN FETCH sc.municipio muni "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " INNER JOIN FETCH imov.imovelPerfil imovPerfil "
					+ " LEFT JOIN FETCH cliente.orgaoExpedidorRg orgaoRg "
					+ " LEFT JOIN FETCH cliente.unidadeFederacao unidFed "
					+ " WHERE imov.id = :idImovel "
					+ " AND clienteImovel.dataFimRelacao is null ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

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
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel carregando os dados necess�rios para retornar o seu endere�o 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(
			Integer idImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " INNER JOIN FETCH clienteImovel.imovel imov "
					+ " INNER JOIN FETCH imov.localidade loc "
					+ " INNER JOIN FETCH imov.setorComercial sc "
					+ " INNER JOIN FETCH sc.municipio muniSc "
					+ " INNER JOIN FETCH muniSc.unidadeFederacao ufSc "
					+ " INNER JOIN FETCH imov.imovelPerfil imovPerfil "
					+ " INNER JOIN FETCH imov.quadra quadra "
					+ " LEFT JOIN FETCH imov.enderecoReferencia endRef "
					+ " LEFT JOIN FETCH imov.logradouroBairro logrBairro "
					+ " LEFT JOIN FETCH logrBairro.bairro bairro "
					+ " LEFT JOIN FETCH bairro.municipio muniBairro "
					+ " LEFT JOIN FETCH muniBairro.unidadeFederacao ufBairro "
					+ " LEFT JOIN FETCH imov.logradouroCep logrCep "
					+ " LEFT JOIN FETCH logrCep.cep cep "
					+ " LEFT JOIN FETCH logrCep.logradouro logr "
					+ " LEFT JOIN FETCH logr.logradouroTipo logrTp "
					+ " LEFT JOIN FETCH logr.logradouroTitulo logrTitulo "
					+ " LEFT JOIN FETCH imov.perimetroInicial perimetroInicial "
					+ " LEFT JOIN FETCH perimetroInicial.logradouroTipo perimetroInicialLogrTipo "
					+ " LEFT JOIN FETCH perimetroInicial.logradouroTitulo perimetroInicialLogrTitulo "
					+ " LEFT JOIN FETCH imov.perimetroFinal perimetroFinal "
					+ " LEFT JOIN FETCH perimetroFinal.logradouroTipo perimetroFinalLogrTipo "
					+ " LEFT JOIN FETCH perimetroFinal.logradouroTitulo perimetroFinalLogrTitulo "
					+ " WHERE imov.id = :idImovel ";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

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
	 *Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Fl�vio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteUsuario(Integer idImovel) throws ErroRepositorioException{
		Cliente cliente = new Cliente();
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Object[] objetoCliente = null;
		Collection colecaoCliente = null;

		try {

			consulta = "select cliente.clie_id as idCliente,"
				+ " cliente.clie_nmcliente as nomeCliente"
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " where clienteImovel.imov_id ="
				+ idImovel
				+ " and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.USUARIO
				+ " and clienteImovel.clim_dtrelacaofim is null";

			colecaoCliente = session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.list();
			
			objetoCliente = (Object[])colecaoCliente.iterator().next();
			
			if(objetoCliente[0] != null){
				cliente.setId((Integer)objetoCliente[0]);
			}
			if(objetoCliente[1] != null){
				cliente.setNome((String)objetoCliente[1]);
			}

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return cliente;
	}
	
	/**
	 * 
	 * Retorna os clientes e suas rela��es tipos a partir do id do imovel
	 *
	 * @author S�vio Luiz
	 * @date 22/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection retornaClientesRelacao(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Collection colecao = null;
		
		try {

			consulta = "select cliente.clie_id as idCliente "
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " where clienteImovel.imov_id ="
				+ idImovel
				+ " and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.PROPRIETARIO
				+ " and clienteImovel.clim_dtrelacaofim is null";

			colecao = session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.list();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return colecao;
	}
	
	/**
	 * 
	 * Retorna o cliente usu�rio
	 *
	 * @author S�vio Luiz
	 * @date 22/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteUsuario(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Integer id = null;
		
		try {

			consulta = "select cliente.clie_id as idCliente "
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " where clienteImovel.imov_id ="
				+ idImovel
				+ " and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.USUARIO
				+ " and clienteImovel.clim_dtrelacaofim is null";

			id = (Integer)session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return id;
	}
	
	/**
	 * 
	 * Retorna o cliente usu�rio
	 *
	 * @author S�vio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavel(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Integer id = null;
		
		try {

			consulta = "select cliente.clie_id as idCliente "
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " where clienteImovel.imov_id ="
				+ idImovel
				+ " and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.RESPONSAVEL
				+ " and clienteImovel.clim_dtrelacaofim is null";

			id = (Integer)session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return id;
	}
	
	/**
	 * 
	 * Retorna o tipo da rela��o do cliente com indicador nome conta
	 *
	 * @author Rafael Corr�a
	 * @date 17/05/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaTipoRelacaoClienteImovelNomeConta(Integer idImovel) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Integer idRelacaoTipo = null;
		
		try {

			consulta = "select crtp.id "
				+ " from ClienteImovel clienteImovel"
				+ " inner join clienteImovel.clienteRelacaoTipo crtp "
				+ " where clienteImovel.imovel.id = :idImovel"
				+ " and clienteImovel.indicadorNomeConta = 1 "
				+ " and clienteImovel.dataFimRelacao is null";

			idRelacaoTipo = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return idRelacaoTipo;
	}
	
	
	
	/**
	 * Pesquisa o rg do cliente do parcelamento a partir do idParcelamento
	 * Autor: Vivianne Sousa 
	 * Data: 20/06/2007
	 */
	public Object[] pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento)
			throws ErroRepositorioException {
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = "select orgaoExpedidorRg.descricao, unidadeFederacao.sigla, cliente.rg "
					+ "from Parcelamento parcelamento "
					+ "left join parcelamento.cliente cliente "
					+ "left join cliente.orgaoExpedidorRg orgaoExpedidorRg "
					+ "left join cliente.unidadeFederacao unidadeFederacao "
					+ "where parcelamento.id = :idParcelamento";
			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idParcelamento", idParcelamento).uniqueResult();

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
	 * Retorna o cliente usu�rio
	 *
	 * @author S�vio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Integer id = null;
		
		try {

			consulta = "select cliente.clie_id as idCliente "
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " inner join cadastro.imovel imovel on imovel.imov_id = clienteImovel.imov_id"
				+ " where imovel.imov_id ="
				+ idImovel
				+ " and (imovel.icte_id = :indicadorEnvioResponsavel or imovel.icte_id = :indicadorEnvioResponsavelNaoPagavel) "
				+ "and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.RESPONSAVEL
				+ " and clienteImovel.clim_dtrelacaofim is null";

			id = (Integer)session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.setInteger("indicadorEnvioResponsavel",ImovelContaEnvio.ENVIAR_CLIENTE_RESPONSAVEL)
					.setInteger("indicadorEnvioResponsavelNaoPagavel",ImovelContaEnvio.NAO_PAGAVEL_IMOVEL_PAGAVEL_RESPONSAVEL)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return id;
	}

	/**
	 * Pesquisa o codigo da rota e o sequencia da rota 
	 * apartir do codigo do cliente
	 * 
	 * @author Rafael Pinto

	 * @date 19/09/2007
	 * 
	 * @throws ErroRepositorioException
	 * @return Object[3] onde :
	 * 
	 * Object[0]=id localidade
	 * Object[1]=codigo do setor
	 * Object[2]=codigo da rota
	 * Object[2]=sequencial
	 */
	public Object[] pesquisarCodigoSequencialRotaPorUsuario(Integer idCliente)
			throws ErroRepositorioException {

		Object[] retorno = null;

		String consulta = "";

		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT local.id," 
				+ "setor.codigo," 
				+ "rota.codigo," 
				+ "imov.numeroSequencialRota " 
				+ "FROM ClienteImovel clienteImovel "
				+ "LEFT JOIN FETCH clienteImovel.cliente cliente "
				+ "LEFT JOIN FETCH clienteImovel.clienteRelacaoTipo clienteRelacao "
				+ "LEFT JOIN FETCH clienteImovel.imovel imov "
				+ "LEFT JOIN FETCH imov.quadra qu "
				+ "LEFT JOIN FETCH imov.setorComercial setor "
				+ "LEFT JOIN FETCH imov.localidade local "
				+ "LEFT JOIN FETCH qu.rota rota "
				+ "WHERE cliente.id = :idCliente "
				+ "AND clienteRelacao.id = :relacao "
				+ "AND clienteImovel.dataFimRelacao is null";

			retorno = (Object[]) session.createQuery(consulta).
				setInteger("idCliente", idCliente).
				setInteger("relacao", ClienteRelacaoTipo.USUARIO).
				setMaxResults(1).
				uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		// retorna 
		return retorno;
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
	public Cliente retornaClienteProprietario(Integer idImovel) throws ErroRepositorioException{
		Cliente cliente = null;
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Object[] objetoCliente = null;
		Collection colecaoCliente = null;

		try {

			consulta = "select cliente.clie_id as idCliente,"
				+ " cliente.clie_nmcliente as nomeCliente"
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " where clienteImovel.imov_id ="
				+ idImovel
				+ " and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.PROPRIETARIO
				+ " and clienteImovel.clim_dtrelacaofim is null";

			colecaoCliente = session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.list();
			
			if(colecaoCliente != null && !colecaoCliente.isEmpty()) {
				cliente = new Cliente();
				objetoCliente = (Object[])colecaoCliente.iterator().next();
				
				if(objetoCliente[0] != null){
					cliente.setId((Integer)objetoCliente[0]);
				}
				if(objetoCliente[1] != null){
					cliente.setNome((String)objetoCliente[1]);
				}
			}
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return cliente;
	}
	
	/**
	 * [UC0014] Manter Im�vel
	 * [FS0017] Registra Fim de Rela��o do(s) Cliente(s) com Im�vel
	 *
	 * @author Ana Maria
	 * @date 13/10/2008
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(Integer idImovel)
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = "SELECT clienteImovel " 
					 + "from ClienteImovel clienteImovel " 
					 + "where clienteImovel.imovel.id = :idImovel " 
					 + "and clienteImovel.dataFimRelacao is null ";
		
			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel.intValue()).list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}

	/**
	 * EMITIR CONTAS CAEMA
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel cujo cliente seja o responsavel pela conta
	 * 
	 * Autor: Tiago Moreno
	 * 
	 * Data: 29/10/2008
	 */
	public Collection pesquisarClienteImovelResponsavelConta(
			Integer idImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " INNER JOIN FETCH clienteImovel.imovel imov "
					+ " WHERE imov.id = :idImovel AND clienteImovel.indicadorNomeConta = 1";

			retorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();

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
	 * Pesquisar Cliente Imovel Atualiza��o Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarClienteImovelAtualizacaoCadastral(Integer idImovel)
		throws ErroRepositorioException{
		
		Collection colecaoClienteImovel = new ArrayList();
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = " SELECT cliente" 
					 + " FROM ClienteAtualizacaoCadastral cliente" 
					 + " WHERE cliente.idImovel = :idImovel";
		
			colecaoClienteImovel = session.createQuery(consulta).setInteger("idImovel",
								idImovel.intValue()).list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoClienteImovel;
	}
	
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param imovel
	 *            Descri��o do par�metro
	 * 
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Integer pesquisarEsferaPoderClienteImovelResponsavel(Integer imovel)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select ep.id "
					+ "from "
					+ "ClienteImovel ci "
					+ "inner join ci.cliente c "
					+ "inner join c.clienteTipo.esferaPoder ep "
					+ "where ci.imovel.id = :imovel "
					+ "and ci.clienteRelacaoTipo.id = 3 and ci.dataFimRelacao is null ";

			retorno = (Integer) session.createQuery(consulta).setInteger("imovel",
					imovel.intValue()).setMaxResults(1).uniqueResult();

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
	 * @author Daniel Alves
	 * @date 02/09/2010
	 * @param idClienteImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String 
			consulta = "UPDATE gcom.cadastro.cliente.ClienteImovel " +
					   "SET indicadorNomeConta = 1 " +
					   "WHERE id = " + idClienteImovel ;

					session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
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
	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ErroRepositorioException {
		Cliente cliente = new Cliente();
		
		Session session = HibernateUtil.getSession();
		String consulta = null;
		Object[] objetoCliente = null;
		Collection colecaoCliente = null;

		try {

			consulta = "select cliente.clie_id as idCliente,"
				+ " cliente.clie_nmcliente as nomeCliente,"
				+ " cliente.clie_nncpf as cpfCliente, "
				+ " cliente.clie_nncnpj as cnpjCliente, "
				+ " cliente.clie_icusonomefantasiaconta as indicadorNMFantasiaConta, "
				+ " cliente.clie_nmabreviado as nomeAbreviado, "
				+ " cliente.clie_nnrg as numRG," 
				+ " orrg.oerg_dsabreviado as orgaoExp, "
				+ " unfe.unfe_dsufsigla as unidadeFed "
				+ " from cadastro.cliente_imovel clienteImovel"
				+ " inner join cadastro.cliente cliente on cliente.clie_id = clienteImovel.clie_id"
				+ " left join cadastro.orgao_expedidor_rg orrg on orrg.oerg_id = cliente.oerg_id "
				+ " left join cadastro.unidade_federacao unfe on unfe.unfe_id = cliente.unfe_id "
				+ " where clienteImovel.imov_id ="
				+ idImovel
				+ " and clienteImovel.crtp_id = "
				+ ClienteRelacaoTipo.USUARIO
				+ " and clienteImovel.clim_dtrelacaofim is null";

			colecaoCliente = session.createSQLQuery(consulta)
					.addScalar("idCliente", Hibernate.INTEGER)
					.addScalar("nomeCliente", Hibernate.STRING)
					.addScalar("cpfCliente", Hibernate.STRING)
					.addScalar("cnpjCliente", Hibernate.STRING)
					.addScalar("indicadorNMFantasiaConta", Hibernate.SHORT)
					.addScalar("nomeAbreviado", Hibernate.STRING)
					.addScalar("numRG", Hibernate.STRING)
					.addScalar("orgaoExp", Hibernate.STRING)
					.addScalar("unidadeFed", Hibernate.STRING)
					.list();
			
			objetoCliente = (Object[])colecaoCliente.iterator().next();
			
			if(objetoCliente[0] != null){
				cliente.setId((Integer)objetoCliente[0]);
			}
			if(objetoCliente[1] != null){
				cliente.setNome((String)objetoCliente[1]);
			}
			if(objetoCliente[2] != null){
				cliente.setCpf((String)objetoCliente[2]);
			}
			if(objetoCliente[3] != null){
				cliente.setCnpj((String)objetoCliente[3]);
			}
			if(objetoCliente[4] != null){
				cliente.setIndicadorUsoNomeFantasiaConta((Short)objetoCliente[4]);
			}
			if(objetoCliente[5] != null){
				cliente.setNomeAbreviado((String)objetoCliente[5]);
			}
			if(objetoCliente[6] != null){
				cliente.setRg((String)objetoCliente[6]);
			}
			if(objetoCliente[7] != null){
				OrgaoExpedidorRg orgaoExpedidorRg = new OrgaoExpedidorRg();
				orgaoExpedidorRg.setDescricaoAbreviada((String)objetoCliente[7]);
				cliente.setOrgaoExpedidorRg(orgaoExpedidorRg);
			}
			if(objetoCliente[8] != null){
				UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
				unidadeFederacao.setSigla((String)objetoCliente[8]);
				cliente.setUnidadeFederacao(unidadeFederacao);
			}

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		
		return cliente;
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
			Integer idImovel) throws ErroRepositorioException {

		ClienteImovel retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " INNER JOIN FETCH clienteImovel.imovel imov "
					+ " WHERE imov.id = :idImovel AND clienteImovel.clienteRelacaoTipo.id = 3 AND clienteImovel.dataFimRelacao is null ";

			Collection collRetorno = session.createQuery(consulta).setInteger("idImovel",
					idImovel).list();
			
			if(collRetorno != null && !collRetorno.isEmpty()){
				retorno = (ClienteImovel) collRetorno.iterator().next();
			}

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
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0003] - Substituir Cliente Usu�rio do Im�vel
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvinculaClienteUsuarioAtualDoImovel(Integer idImovel) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "update gcom.cadastro.cliente.ClienteImovel "
					+ " set clim_dtrelacaofim = :dataFimRelacao, clim_tmultimaalteracao = :ultimaAlteracao, cifr_id = :motivo "
					+ " where imov_id = :idImovel and clim_dtrelacaofim is null and crtp_id = :tipoRelacao ";
			
			session.createQuery(consulta)
					.setDate("dataFimRelacao", new Date())
					.setTimestamp("ultimaAlteracao", new Date())
					.setInteger("motivo", ClienteImovelFimRelacaoMotivo.ATU_CADASTRAL)
					.setInteger("tipoRelacao", ClienteRelacaoTipo.USUARIO)
					.setInteger("idImovel", idImovel)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0003] - Substituir Cliente Usu�rio do Im�vel
	 * 
	 * @author Mariana Victor
	 * @date 30/09/2011
	 * 
	 * @param Integer
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndicadorNomeConta(Integer idCliente, Integer idImovel) throws ErroRepositorioException {

		Session sessao = HibernateUtil.getSession();
		Short retorno = null;
		
		try{
			String consulta = "SELECT "
				+ "clim.clim_icnomeconta AS icNomeConta " //0
				+ "FROM cadastro.cliente_imovel clim "
				+ "WHERE clim.imov_id = :idImovel AND clim.clie_id = :idCliente "
				+ "AND clim.clim_dtrelacaofim IS NULL AND clim.crtp_id = :idClienteUsuario ";
			
			retorno = (Short) sessao.createSQLQuery(consulta)
				.addScalar("icNomeConta", Hibernate.SHORT)
				.setInteger("idCliente", idCliente)
				.setInteger("idImovel", idImovel)
				.setInteger("idClienteUsuario", ClienteRelacaoTipo.USUARIO.intValue())
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}
		return retorno;
	}
	
	/**
	 * [UC0671] Gerar Movimento de Inclus�o de Negativa��o
	 * [SB0005] - Gerar Negativa��o para o Im�vel
	 * 
	 * @author Vivianne Sousa
	 * @date 06/12/2011
	 */
	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(
			Integer idImovel, Integer idClienteRelacaoTipo)
			throws ErroRepositorioException {

		Short retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select clie.indicadorNegativacaoPeriodo "
					+ "from ClienteImovel clim "
					+ "inner join clim.cliente clie "
					+ "where clim.imovel.id = :idImovel "
					+ "and clim.clienteRelacaoTipo.id = :idClienteRelacaoTipo " 
					+ "and clim.dataFimRelacao is null ";

			retorno =(Short) session.createQuery(consulta)
					.setInteger("idImovel",idImovel)
					.setInteger("idClienteRelacaoTipo",idClienteRelacaoTipo)
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
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * 
	 * [SB0003] - Substituir Cliente Usu�rio do Im�vel
	 * 
	 * @author Mariana Victor
	 * @date 26/09/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvinculaClienteUsuarioAtualDoImovel(Integer idImovel, Date dataFim) 
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {
			String consulta = "update gcom.cadastro.cliente.ClienteImovel "
					+ " set clim_dtrelacaofim = :dataFimRelacao, clim_tmultimaalteracao = :ultimaAlteracao, cifr_id = :motivo "
					+ " where imov_id = :idImovel and clim_dtrelacaofim is null and crtp_id = :tipoRelacao ";
			
			session.createQuery(consulta)
					.setDate("dataFimRelacao", dataFim)
					.setTimestamp("ultimaAlteracao", dataFim)
					.setInteger("motivo", ClienteImovelFimRelacaoMotivo.ATU_CADASTRAL)
					.setInteger("tipoRelacao", ClienteRelacaoTipo.USUARIO)
					.setInteger("idImovel", idImovel)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0013] - Substituir Cliente Respons�vel do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvincularClienteResponsavelImovel(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			
			String queryDesvincularClienteResponsavel = " UPDATE gcom.cadastro.cliente.ClienteImovel" +
					" SET clim_dtrelacaofim = :dataCorrente, cifr_id = :fimRelacaoMotivo, clim_tmultimaalteracao = :timestampAtual " +
					" WHERE imov_id = :idImovel and crtp_id = :relacaoTipo and clim_dtrelacaofim is null";

			session.createQuery(queryDesvincularClienteResponsavel)
				.setDate("dataCorrente", new Date())
				.setInteger("fimRelacaoMotivo", ClienteImovelFimRelacaoMotivo.ATU_CADASTRAL)
				.setTimestamp("timestampAtual", new Date())
				.setInteger("idImovel", idImovel)
				.setInteger("relacaoTipo", ClienteRelacaoTipo.RESPONSAVEL)
				.executeUpdate();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0014] - Substituir Cliente Propriet�rio do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void desvincularClienteProprietarioImovel(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String queryDesvincularClienteProprietario = " UPDATE gcom.cadastro.cliente.ClienteImovel" +
					" SET clim_dtrelacaofim = :dataCorrente, cifr_id = :fimRelacaoMotivo, clim_tmultimaalteracao = :timestampAtual " +
					" WHERE imov_id = :idImovel and crtp_id = :relacaoTipo and clim_dtrelacaofim is null";

			session.createQuery(queryDesvincularClienteProprietario)
				.setDate("dataCorrente", new Date())
				.setInteger("fimRelacaoMotivo", ClienteImovelFimRelacaoMotivo.ATU_CADASTRAL)
				.setTimestamp("timestampAtual", new Date())
				.setInteger("idImovel", idImovel)
				.setInteger("relacaoTipo", ClienteRelacaoTipo.PROPRIETARIO)
				.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0014] - Substituir Cliente Propriet�rio do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void associarClienteProprietarioImovel(Integer idImovel,
			Integer idCliente, Short icNomeConta) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String queryAssociarClienteProprietario = " INSERT INTO cadastro.cliente_imovel"
					+ " (clie_id, imov_id, clim_dtrelacaoinicio, crtp_id, clim_icnomeconta, clim_tmultimaalteracao)"
					+ " VALUES ("
					+ ":idCliente ,"
					+ ":idImovel ,"
					+ ":dtRelacaoInicio ,"
					+ ":idClienteRelacaoTipo ,"
					+ ":icNomeConta ," + ":timestampAtual " + ");";

			session.createSQLQuery(queryAssociarClienteProprietario)
					.setInteger("idImovel", idImovel)
					.setInteger("idCliente", idCliente)
					.setDate("dtRelacaoInicio", new Date())
					.setInteger("idClienteRelacaoTipo",
							ClienteRelacaoTipo.PROPRIETARIO)
					.setInteger("icNomeConta", icNomeConta)
					.setTimestamp("timestampAtual", new Date()).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1222] Atualizar Cliente a Partir do Dispositivo M�vel
	 * [SB0013] - Substituir Cliente Respons�vel do Im�vel;
	 * 
	 * @author Bruno S� Barreto
	 * @date 10/10/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public void associarClienteResponsavelImovel(Integer idImovel,
			Integer idCliente, Short icNomeConta) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			String queryAssociarClienteResponsavel = " INSERT INTO cadastro.cliente_imovel"
					+ " (clie_id, imov_id, clim_dtrelacaoinicio, crtp_id, clim_icnomeconta, clim_tmultimaalteracao)"
					+ " VALUES ("
					+ ":idCliente ,"
					+ ":idImovel ,"
					+ ":dtRelacaoInicio ,"
					+ ":idClienteRelacaoTipo ,"
					+ ":icNomeConta ," + ":timestampAtual " + ");";

			session.createSQLQuery(queryAssociarClienteResponsavel)
					.setInteger("idImovel", idImovel)
					.setInteger("idCliente", idCliente)
					.setDate("dtRelacaoInicio", new Date())
					.setInteger("idClienteRelacaoTipo",
							ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("icNomeConta", icNomeConta)
					.setTimestamp("timestampAtual", new Date())
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0150] - Retifcar Conta
	 * 
	 * Pesquisar Cliente Imovel
	 * 
	 * @author Rafael Corr�a
	 * @date 16/04/2015
	 */
	public ClienteImovel pesquisarClienteImovel(String idImovel, int indicadoNomeConta) throws ErroRepositorioException {
		ClienteImovel retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " INNER JOIN FETCH clienteImovel.imovel imov "
					+ " WHERE imov.id = :idImovel AND clienteImovel.indicadorNomeConta = :indicadoNomeConta AND clienteImovel.dataFimRelacao is null ";

			Collection collRetorno = session.createQuery(consulta)
									.setString("idImovel", idImovel)
									.setInteger("indicadoNomeConta", indicadoNomeConta).list();
			
			if(collRetorno != null && !collRetorno.isEmpty()){
				retorno = (ClienteImovel) collRetorno.iterator().next();
     		}
		

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}




//    Pesquisar Cliente Imovel
	public Collection<ClienteImovel> pesquisarClientesImovelDadosComplementares(Integer idImovel) 
			throws ErroRepositorioException{
		Collection<ClienteImovel> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = "SELECT clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN FETCH clienteImovel.cliente cliente "
					+ " LEFT JOIN FETCH clienteImovel.clienteRelacaoTipo clienteRelacaoTipo "
					+ " WHERE clienteImovel.imovel.id = :idImovel " 
					+ " ORDER BY clienteImovel.dataInicioRelacao desc, clienteImovel.dataFimRelacao desc";

			retorno = session.createQuery(consulta)
									.setInteger("idImovel", idImovel)
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
	 * [UC 1366] - Alterar V�nculo de Clientes com o Im�vel e Contas
	 * 
	 * @author Rafael Corr�a
	 * @date 24/04/2015
	 */
	public Date pesquisarMenorDataClienteImovel(Integer idImovel) throws ErroRepositorioException {
		Date data = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			consulta = 	"Select min(clim_dtrelacaoinicio) as dataInicio "
					 +	"from cadastro.cliente_imovel "
					 +	"where imov_id = :idImovel ";
			
			data = (Date) session.createSQLQuery(consulta)
									.addScalar("dataInicio", Hibernate.DATE)
									.setInteger("idImovel", idImovel)
									.uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return data;
	}
	
	/**
	 * [UC 1366] - Alterar V�nculo de Clientes com o Im�vel e Contas
	 * 
	 * @author Rafael Corr�a
	 * @date 24/04/2015
	 */
	public Collection pesquisarClienteImovelPeriodo(Integer idImovel, Date periodo) throws ErroRepositorioException {
		Collection colecaoCliente = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		Date dataInicial = Util.formatarDataInicial(periodo);
		
		try{
			consulta = 	"Select clie_id as idCliente, "
					 +	"crtp_id as idClienteRelacaoTipo, "
					 +	"clim_icnomeconta as indicadorNomeConta "
					 +	"from cadastro.cliente_imovel "
					 +	"where imov_id = :idImovel "
					 +	"and ((clim_dtrelacaoinicio <= :dataInicial and clim_dtrelacaofim is null ) or "
					 +	":dataInicial between clim_dtrelacaoinicio and clim_dtrelacaofim ) ";
			
			colecaoCliente = (Collection) session.createSQLQuery(consulta)
											.addScalar("idCliente", Hibernate.INTEGER)
											.addScalar("idClienteRelacaoTipo", Hibernate.INTEGER)
											.addScalar("indicadorNomeConta", Hibernate.SHORT)
											.setInteger("idImovel", idImovel)
											.setTimestamp("dataInicial", dataInicial)
											.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoCliente;
	}

	/**
	 * [UC 1366] - Alterar V�nculo de Clientes com o Im�vel e Contas
	 * 
	 * @author Rafael Corr�a
	 * @date 24/04/2015
	 */
	public Collection pesquisarClienteImovelPeriodo(Integer idCliente, Integer idImovel, Integer idTipoRelacao, Date periodo) throws ErroRepositorioException {
		
		Collection colecaoCliente = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		Date dataInicial = Util.formatarDataInicial(periodo);
		
		try{
			consulta = 	"Select clie_id as idCliente, "
					 +	"crtp_id as idClienteRelacaoTipo, "
					 +	"clim_icnomeconta as indicadorNomeConta "
					 +	"from cadastro.cliente_imovel "
					 +	"where imov_id = :idImovel "
					 +  "and clie_id = :idCliente "
					 +  "and crtp_id = :idTipoRelacao "
					 +	"and (clim_dtrelacaoinicio <= :dataInicial and (clim_dtrelacaofim is null or clim_dtrelacaofim > :dataInicial))";
			
			colecaoCliente = (Collection) session.createSQLQuery(consulta)
											.addScalar("idCliente", Hibernate.INTEGER)
											.addScalar("idClienteRelacaoTipo", Hibernate.INTEGER)
											.addScalar("indicadorNomeConta", Hibernate.SHORT)
											.setInteger("idImovel", idImovel)
											.setInteger("idCliente", idCliente)
											.setInteger("idTipoRelacao", idTipoRelacao)
											.setTimestamp("dataInicial", dataInicial)
											.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoCliente;
	}
	
}

