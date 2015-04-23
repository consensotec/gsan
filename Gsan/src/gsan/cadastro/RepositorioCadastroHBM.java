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
package gsan.cadastro;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ClienteAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.HidrometroInstalacaoHistoricoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ImovelSubcategoriaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.ParametroTabelaAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gsan.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gsan.atualizacaocadastral.bean.ConsultarRoteiroDispositivoMovelHelper;
import gsan.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gsan.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.empresa.EmpresaCobrancaFaixa;
import gsan.cadastro.endereco.Logradouro;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.geografico.MunicipioFeriado;
import gsan.cadastro.imovel.CadastroOcorrencia;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelContaEnvio;
import gsan.cadastro.imovel.ImovelProgramaEspecial;
import gsan.cadastro.imovel.ImovelSubcategoriaPK;
import gsan.cadastro.imovel.ItemMovimentoProgramaEspecial;
import gsan.cadastro.imovel.Subcategoria;
import gsan.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.sistemaparametro.NacionalFeriado;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gsan.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gsan.cobranca.CobrancaAcaoAtividadeComando;
import gsan.cobranca.CobrancaSituacao;
import gsan.gui.cadastro.imovel.FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper;
import gsan.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gsan.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gsan.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gsan.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gsan.micromedicao.Leiturista;
import gsan.micromedicao.Rota;
import gsan.micromedicao.RotaAtualizacaoSeq;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gsan.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gsan.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gsan.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.FachadaException;
import gsan.util.HibernateUtil;
import gsan.util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author Administrador
 */
public class RepositorioCadastroHBM implements IRepositorioCadastro {

	private static IRepositorioCadastro instancia;

	/**
	 * Construtor da classe RepositorioFaturamentoHBM
	 */
	private RepositorioCadastroHBM() {
	}

	/**
	 * Retorna o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static IRepositorioCadastro getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioCadastroHBM();
		}
		return instancia;
	}

	/**
	 * Pesquisa os feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (tipoFeriado != 1) {

				consulta = " select 2 as tipoFeriado, mfer.mfer_id as id, mfer.mfer_dsferiado as descricao, "
						+ " muni.muni_nmmunicipio as descricaoMunicipio, mfer.mfer_dtferiado as data"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado == 3) {
				consulta += "union all";
			}

			if (tipoFeriado != 2) {
				consulta += " select 1 as tipoFeriado, nfer_id as id, nfer_dsferiado as descricao,"
						+ " '' as descricaoMunicipio, nfer_dtferiado as data"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";

					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			consulta = consulta + "order by data";

			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setDate("dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).setFirstResult(
								10 * numeroPagina).setMaxResults(10).list();
			} else {
				retorno = session.createSQLQuery(consulta).addScalar(
						"tipoFeriado", Hibernate.SHORT).addScalar("id",
						Hibernate.INTEGER).addScalar("descricao",
						Hibernate.STRING).addScalar("descricaoMunicipio",
						Hibernate.STRING).addScalar("data", Hibernate.DATE)
						.setFirstResult(10 * numeroPagina).setMaxResults(10)
						.list();
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar quantidade de registro dos feriados(nacionais e municipais)
	 * 
	 * @author Kássia Albuquerque
	 * @date 22/01/2007
	 * 
	 */
	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao,
			Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			if (tipoFeriado != 1) {

				consulta = " select count(mfer.mfer_id) as id"
						+ " from cadastro.municipio_feriado mfer"
						+ " inner join cadastro.municipio muni on(mfer.muni_id = muni.muni_id)";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))
						|| (idMunicipio != null && !idMunicipio.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(mfer.mfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "mfer.mfer_dtferiado between :dataInicio and :dataFim and ";
					}

					if (idMunicipio != null && !idMunicipio.equals("")) {
						consulta += "mfer.muni_id = " + idMunicipio + " and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			if (tipoFeriado == 3) {
				consulta += "union all";
			}

			if (tipoFeriado != 2) {
				consulta += " select count(nfer_id) as id"
						+ " from cadastro.nacional_feriado ";

				if ((descricao != null && !descricao.equals(""))
						|| (dataFeriadoInicio != null && !dataFeriadoInicio
								.equals(""))) {
					consulta += "where ";
					if (descricao != null && !descricao.equals("")) {
						consulta += "upper(nfer_dsferiado) like '"
								+ descricao.toUpperCase() + "%' and ";
					}

					if (dataFeriadoInicio != null
							&& !dataFeriadoInicio.equals("")) {
						consulta += "nfer_dtferiado between :dataInicio and :dataFim and ";
					}

					consulta = Util.removerUltimosCaracteres(consulta, 4);
				}
			}

			Collection valores = null;
			if (dataFeriadoInicio != null && !dataFeriadoInicio.equals("")) {
				valores = (Collection) session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER).setDate(
								"dataInicio", dataFeriadoInicio).setDate(
								"dataFim", dataFeriadoFim).list();
			} else {
				valores = (Collection) session.createSQLQuery(consulta)
						.addScalar("id", Hibernate.INTEGER).list();
			}

			Integer valor = 0;
			Iterator iteratorValor = valores.iterator();
			while (iteratorValor.hasNext()) {
				valor = valor + (Integer) iteratorValor.next();
			}

			retorno = valor;

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Faz um Update na base
	 * 
	 * @author Kassia Albuquerque e Ana Maria
	 * @date 06/03/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarMensagemSistema(String mensagemSistema)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "update SistemaParametro sp "
				+ "set sp.mensagemSistema =:mensagemSistema, sp.ultimaAlteracao = :dataAtual ";

		try {

			session.createQuery(consulta).setString("mensagemSistema",
					mensagemSistema).setTimestamp("dataAtual", new Date())
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * Pesquisa os dados do email do batch para ser enviado
	 * 
	 * @author Sávio Luiz
	 * @date 13/03/2007
	 * 
	 */
	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail)
			throws ErroRepositorioException {

		EnvioEmail retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select envioEmail " + "from EnvioEmail envioEmail "
					+ "where envioEmail.id = :idEnvioEmail";

			retorno = (EnvioEmail) session.createQuery(consulta).setInteger(
					"idEnvioEmail", idEnvioEmail).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros()
			throws ErroRepositorioException {

		DadosEnvioEmailHelper retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select new gsan.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper(ipServidorSmtp, dsEmailResponsavel) "
					+ "from SistemaParametro sistemaParametro ";

			retorno = (DadosEnvioEmailHelper) session.createQuery(consulta)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar todos ids dos setores comerciais.
	 * 
	 * [UC0564 - Gerar Resumo das Instalações de Hidrômetros]
	 * 
	 * @author Pedro Alexandre
	 * @date 25/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarTodosIdsSetorComercial()
			throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = "select stcm.id from SetorComercial stcm  ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * Migração dos dados do município de Ribeirão - O sistema gerar as tabelas
	 * cliente, cliente_endereço, imovel, cliente_imovel, imovel_subcategoria,
	 * ligacao_agua a parti da tabela Cadastro_ribeirao;
	 * 
	 * @author Ana Maria
	 * 
	 * @throws ControladorException
	 */

	public Object[] pesquisarSetorQuadra(Integer idLocalidade)
			throws ErroRepositorioException {

		Object[] retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select stcm.id, qdra.id " + "from Quadra qdra "
					+ "inner join qdra.setorComercial stcm "
					+ "where stcm.localidade.id = :idLocalidade";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarCEP() throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select cep.cepId " + "from Cep cep "
					+ "where cep.municipio like 'RIBEIRAO'";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarBairro() throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select bair.id " + "from Bairro bair "
					+ "where bair.municipio.id = 1180";

			retorno = (Integer) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarLogradouroBairro(Integer codigoLogradouro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logB.id " + "from LogradouroBairro logB "
					+ "where logB.logradouro.id = :codigoLogradouro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"codigoLogradouro", codigoLogradouro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarLogradouroCep(Integer codigoLogradouro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logC.id " + "from LogradouroCep logC "
					+ "where logC.logradouro.id = :codigoLogradouro";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"codigoLogradouro", codigoLogradouro).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void inserirClienteEndereco(Integer idCliente,
			String numeroImovelMenor, String numeroImovelMaior, Integer idCep,
			Integer idBairro, Integer idLograd, Integer idLogradBairro,
			Integer idLogradCep) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;
		String sequence = Util
				.obterNextValSequence("cadastro.seq_cliente_endereco");
		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into cadastro.cliente_endereco(cled_id, clie_id, edtp_id, "
					+ "edrf_id, cled_nnimovel, cled_dscomplementoendereco, "
					+ "cep_id, bair_id, cled_icenderecocorrespondencia, "
					+ "cled_tmultimaalteracao, logr_id, lgbr_id, lgcp_id) "
					+ "values ( "
					+ sequence
					+ ", "
					+ idCliente
					+ ", 1, 1, "
					+ numeroImovelMenor
					+ ", "
					+ numeroImovelMaior
					+ ", "
					+ idCep
					+ ", "
					+ idBairro
					+ ", 1, "
					+ Util.obterSQLDataAtual()
					+ " , "
					+ idLograd
					+ ", "
					+ idLogradBairro + ", " + idLogradCep + ")";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public void inserirClienteImovel(Integer idCliente, Integer idImovel,
			String data) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			String sequence = Util
					.obterNextValSequence("cadastro.seq_cliente_imovel");
			insert = "insert into cadastro.cliente_imovel(clim_id, "
					+ "clie_id, imov_id, clim_dtrelacaoinicio, "
					+ "clim_tmultimaalteracao, "
					+ "crtp_id, clim_icnomeconta) " + "values ( " + sequence
					+ ", " + idCliente + ", " + idImovel + ", " + data + ", "
					+ Util.obterSQLDataAtual() + " , " + "2, " + "1)";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public void inserirImovelSubcategoria(Integer idImovel,
			Integer idSubcategoria) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into cadastro.imovel_subcategoria(imov_id, scat_id, "
					+ "imsb_qteconomia, imsb_tmultimaalteracao) "
					+ "values ( "
					+ idImovel
					+ ", "
					+ idSubcategoria
					+ ", "
					+ "1, "
					+ Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public void inserirLigacaoAgua(Integer idImovel, String dataBD)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();

			insert = "insert into atendimentopublico.ligacao_agua(lagu_id, lagu_dtimplantacao, lagu_dtligacaoagua, "
					+ "lagu_icemissaocortesupressao, lagd_id, lagm_id, lapf_id, lagu_tmultimaalteracao) "
					+ "values ( "
					+ idImovel
					+ ", "
					+ dataBD
					+ ", "
					+ dataBD
					+ ", 1, 2, 1, 1," + Util.obterSQLDataAtual() + " )";

			stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}
	}

	public Collection pesquisarCadastroRibeiraop()
			throws ErroRepositorioException {

		Collection retorno = new ArrayList();

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select crp " + "from CadastroRibeiraop crp "
					+ "where crp.imovel.id is null " + "order by crp.codigo";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public void atualizarImovelRibeirao(Integer idImovel, Integer codigo)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizarValorExcedente;

		try {

			atualizarValorExcedente = "UPDATE CadastroRibeiraop "
					+ "SET imov_id = :idImovel " + "WHERE codigo = :codigo ";

			session.createQuery(atualizarValorExcedente).setInteger("idImovel",
					idImovel).setInteger("codigo", codigo).executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * Pesquisa os imóveis do cliente de acordo com o tipo de relação [UC0251]
	 * Gerar Atividade de Ação de Cobrança [SB0001] Gerar Atividade de Ação de
	 * Cobrança para os Imóveis do Cliente
	 * 
	 * @author Sávio Luiz
	 * @created 23/11/2007
	 * 
	 * @param cliente
	 * @param relacaoClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClientesSubordinados(Integer idCliente)

	throws ErroRepositorioException {

		// cria a coleção de retorno
		Collection retorno = null;

		// Query
		String consulta;

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			consulta = "select ci.id " + "from Cliente ci "
					+ "where ci.cliente.id = :idCliente";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		// retorna a coleção de atividades pesquisada(s)
		return retorno;
	}

	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * 
	 * @author Bruno Sá Barreto
	 * @since 26/08/2014
	 * 
	 * @param idParametro
	 * @return Retorna um Collection<Integer> com os numeros das quadras
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer>  pesquisarRoteiroQuadra(Integer idParametro) throws ErroRepositorioException{
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String consulta = ""
					+ "SELECT quadra.ptqd_nnquadra AS numeroQuadra "
					+ "FROM   ATUALIZACAOCADASTRAL.PAR_TAB_ATLZ_CAD_QDRA_DM quadra "
					+ "WHERE  quadra.ptac_id = :idParametro "
					+ "ORDER BY  quadra.ptqd_nnquadra  ";
			
			retorno = (Collection<Integer>) 
					session.createSQLQuery(consulta)
					.addScalar("numeroQuadra",Hibernate.INTEGER)
					.setInteger("idParametro", idParametro)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * 
	 * @author Bruno Sá Barreto
	 * @since 26/08/2014
	 * 
	 * @param idParametro
	 * @return Retorna um Integer com a quantidade de imoveis recebidos da atualização cadastral
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisRecebidosAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException {
		Integer quantidade = null;
//		Integer somaCadastral = null;		
		Session session = HibernateUtil.getSession();
		String consulta = "";		
		
		try{

			consulta = 	"select count(*) as quantidade "
					 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm "
					 +	"where imac_icdadosretorno in (1,3) "					 
					 +	"and ptac_id = :idParametro "; 					
			
			quantidade = (Integer) 
				session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("idParametro", idParametro)
					.uniqueResult();
			
//			Esta consulta abaixo, contava os imóveis que foram removidos
//			para adicionar(incrementar) ao numero de registros da consulta, mas esta
//			funcionalidade ainda não foi implementada.
			
//			consulta = 	"select count(*) as quantidade "
//					 +	"from CADASTRO.imovel_atlz_cad_removido "
//					 +  "where ptac_id = :idParametro ";					 			 
//			
//			somaCadastral = (Integer) 
//						session.createSQLQuery(consulta)
//						.addScalar("quantidade", Hibernate.INTEGER)	
//						.setInteger("idParametro", idParametro)
//						.uniqueResult();
//			
//			quantidade = quantidade.intValue() + somaCadastral.intValue();					
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return quantidade;
	}
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 
	 * @author Davi Menezes
	 * @date 28/11/2012
	 *
	 */
	public void atualizarListaAtualizacaoCadastralArquivoTexto(
			Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoAtualizacaoCadastralArquivoTexto,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String sql = "";
		
		Collection<Integer> colecaoIds = new ArrayList<Integer>();
		Iterator iterator = colecaoAtualizacaoCadastralArquivoTexto.iterator();
		while(iterator.hasNext()){
			AtualizacaoCadastralArquivoTextoHelper arquivo = (AtualizacaoCadastralArquivoTextoHelper) iterator.next();
			colecaoIds.add(new Integer(arquivo.getId()));
		}
		
		try{
			sql = 	"update gsan.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM set "
				+	"stac_id = :idSituacao, "
				+	"atac_tmultimaalteracao = :dataUltimaAlteracao ";
			
			if(leiturista != null){
				sql += ", leit_id  = :idLeiturista ";
			}
			
			if ( idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.LIBERADO) ) {
				sql += ", atac_dtarqliberado  = :dataUltimaAlteracao ";
			} else if ( idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.DISPONIVEL) ) {
				sql += ", atac_dtarqliberado  = null ";
			} else if( idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				sql += ", atac_dtarqfinalizado = :dataArquivoFinalizado ";
			}
			
			sql += " where atac_id in ( :conjuntoArquivos )";
			
			Query query = session.createQuery(sql)
					.setInteger("idSituacao", idSituacaoLeituraNova)	
					.setTimestamp("dataUltimaAlteracao", date)
					.setParameterList("conjuntoArquivos", colecaoIds);
			
			if(leiturista != null){
				query.setInteger("idLeiturista", leiturista.getId());
			}
			
			if(idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				query.setTimestamp("dataArquivoFinalizado", date);
			}
			
			query.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT 0006] Exibir Dados Cadastrador
	 */
	public Collection pesquisarDadosCadastrador(Integer idParametroAtualizacaoCadastral) throws ErroRepositorioException{
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try{
			
			consulta = "select imac_nmlogin as cpf, usur.usur_nmusuario as nomeusuario, count(distinct (imac_id)) as quantidadeImoveis "
					  +"from atualizacaocadastral.imovel_atlz_cadastral_dm imac "
					  +"left join seguranca.usuario usur on (usur.usur_nmlogin = imac.imac_nmlogin or usur.usur_nncpf = imac.imac_nmlogin) "
					  +"where imac.imac_icdadosretorno in (1,3) "
					  +"and imac.ptac_id = :idParametro "
					  +"group by imac_nmlogin, usur_nmusuario "
					  +"order by usur_nmusuario ";
			
			//antiga
//			consulta = 	"select imac_nmlogin as cpf, "
//					 + 	"usur_nmusuario as nomeusuario, "
//					 +	"count(distinct (imac_id)) as quantidadeImoveis "
//					 +	"from cadastro.imovel_atlz_cadastral imac "
//					 +	"left join seguranca.usuario usur "
//					 +	"on (usur.usur_nmlogin = imac.imac_nmlogin " 
//					 +	"or usur.usur_nncpf = imac.imac_nmlogin) "
//					 +	"where imac.imac_icdadosretorno in (1,3) "
//					 +	"and imac.ptac_id = :idParametro "
//					 +	"group by imac_nmlogin, usur_nmusuario "
//					 +	"order by usur_nmusuario "; 
			
			retorno = session.createSQLQuery(consulta)
								.addScalar("cpf", Hibernate.STRING)
								.addScalar("nomeUsuario", Hibernate.STRING)
								.addScalar("quantidadeImoveis", Hibernate.INTEGER)
								.setInteger("idParametro", idParametroAtualizacaoCadastral)
								.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	/**
	 * @author Anderson Cabral
	 * @since 17/10/2013
	 */
	public ArquivoTextoAtualizacaoCadastralDM pesquisarArquivoAtlzCadEmCampoPorCadastrador(Integer idCadastrador)  throws ErroRepositorioException{
		ArquivoTextoAtualizacaoCadastralDM retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT arquivo " +
					" FROM ArquivoTextoAtualizacaoCadastralDM arquivo" +
					" LEFT JOIN FETCH arquivo.parametroTabelaAtualizacaoCadastralDM" +
					" WHERE arquivo.leiturista.id = :idCadastrador" +
					" AND arquivo.situacaoTransmissao.id IN (2, 3)";

			retorno = (ArquivoTextoAtualizacaoCadastralDM) session.createQuery(consulta)
					.setInteger("idCadastrador", idCadastrador)
					.setMaxResults(1).uniqueResult();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 *  [UC1392] - Consultar Roteiro Dispositivo Movel Atualizacao Cadastral
	 * 
	 * 
	 * @author Bruno Sá Barreto
	 * @since 26/08/2014
	 * 
	 * @param consultarRoteiroDispositivoMovelHelper
	 * @return Retorna um Collection<Object[]> com os roteiros
	 * @throws ErroRepositorioException
	 */
	@SuppressWarnings("unchecked")
	public Collection<Object[]> pesquisarArquivoRoteiroAtualizacaoCadastral(ConsultarRoteiroDispositivoMovelHelper helper)
			throws ErroRepositorioException {
		
		Collection<Object []> colecaoArquivos = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			
			consulta =   "select " 
						+"atac_id as idArquivo, " 
						+"ptac.ptac_id as idParametro, " 
						+"ptac.loca_id as idLocalidade, "
						+"ptac.ptac_cdsetorcomercial as codigoSetor, " 
						+"count(ptqd.PTQD_NNQUADRA) as qtdQuadra, " 
						+"atac_qtdimovel as qtdImovelEnviado, " 
						+"usur_nmusuario as nomeUsuario, " 
						+"stac_dssituacao as descricaoSituacaoArquivo, " 
						+"stac.stac_id as idSituacaoArquivo, " 
						+"atac_dtarqliberado as dataArquivoLiberado, " 
						+"atac_dtarqfinalizado as dataFinalizacaoArquivo " 
						
						+"from atualizacaocadastral.arquivo_txt_atlz_cad_dm atac "
						+"inner join atualizacaocadastral.param_tab_atl_cad_dm ptac on atac.ptac_id = ptac.ptac_id "  
						+"inner join ATUALIZACAOCADASTRAL.sit_transm_atlz_cad_dm stac on atac.stac_id = stac.stac_id "
						+"inner join MICROMEDICAO.leiturista leit on atac.leit_id = leit.leit_id "
						+"inner join SEGURANCA.usuario usur on leit.usur_id = usur.usur_id "
						+"inner join ATUALIZACAOCADASTRAL.PAR_TAB_ATLZ_CAD_QDRA_DM ptqd on ptqd.ptac_id = ptac.ptac_id ";
			
			consulta +=	"where ptac.loca_id = :idLocalidade "; 
			
			if(helper.getNumeroQuadra() != null){
				consulta +=	" and ptqd.ptqd_nnquadra = " + helper.getNumeroQuadra();
			}
			
			if(helper.getCodigoSetorComercial() != null){
				consulta += " and ptac.ptac_cdsetorcomercial = " + helper.getCodigoSetorComercial();
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += " and atac.leit_id = " + helper.getIdCadastrador();
			}
			
			if(helper.getSituacaoArquivoTexto() != null){
				consulta += " and atac.stac_id = " + helper.getSituacaoArquivoTexto();
			}
			
			consulta += " group by atac_id, " +
						"ptac.ptac_id, " +
						"ptac.loca_id, " +
						"ptac.ptac_cdsetorcomercial, " +
						"atac.atac_qtdimovel, " +
						"usur.usur_nmusuario, " +
						"stac.stac_dssituacao," +
						"stac.stac_id, " +
						"atac.atac_dtarqliberado, " +
						"atac.atac_dtarqfinalizado ";
			
			consulta += " order by atac_id ";
			
			colecaoArquivos = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idArquivo", Hibernate.INTEGER)
															.addScalar("idParametro", Hibernate.INTEGER)
															.addScalar("idLocalidade", Hibernate.INTEGER)
															.addScalar("codigoSetor", Hibernate.INTEGER)
															.addScalar("qtdQuadra", Hibernate.INTEGER)
															.addScalar("qtdImovelEnviado", Hibernate.INTEGER)
															.addScalar("nomeUsuario", Hibernate.STRING)
															.addScalar("descricaoSituacaoArquivo", Hibernate.STRING)
															.addScalar("idSituacaoArquivo", Hibernate.INTEGER)
															.addScalar("dataArquivoLiberado", Hibernate.DATE)
															.addScalar("dataFinalizacaoArquivo", Hibernate.DATE)
															.setInteger("idLocalidade", helper.getIdLocalidade())
															.list();
															
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoArquivos;
	}
	
	
	/**
	 * [UC0624] Gerar Relatório para Atualização Cadastral
	 */

	public Collection pesquisarRelatorioAtualizacaoCadastral(
			Collection idLocalidades, Collection idSetores,
			Collection idQuadras, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select imovel.id, "// 0
					+ " cliente.nome, "// 1
					+ " localidade.id, "// 2
					+ " localidade.descricao, "// 3
					+ " setorComercial.codigo, "// 4
					+ " setorComercial.descricao, "// 5
					+ " unidadeNegocio.nome,"// 6
					+ " imovel.quantidadeEconomias, "// 7
					+ " rota.codigo,"// 8
					+ " imovel.numeroSequencialRota, "// 9
					+ " imovel.indicadorExclusao,"// 10
					+ " unidadeNegocio.id "// 11
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota "
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " where relacaoTipo.id =:idRelacaoTipo ";

			if (idLocalidades != null && !idLocalidades.isEmpty()) {
				consulta = consulta + " and localidade.id in (";
				Iterator iterator = idLocalidades.iterator();
				while (iterator.hasNext()) {
					Localidade localidade = (Localidade) iterator.next();
					consulta = consulta + localidade.getId().toString() + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (idSetores != null && !idSetores.isEmpty()) {
				consulta = consulta
						+ " and setorComercial.codigo in (:setores)";
				Iterator iterator = idSetores.iterator();
				while (iterator.hasNext()) {
					SetorComercial setorComercial = (SetorComercial) iterator
							.next();
					consulta = consulta + setorComercial.getId().toString()
							+ ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (idQuadras != null && !idQuadras.isEmpty()) {
				consulta = consulta + " and quadra.numeroQuadra in (:quadras)";
				Iterator iterator = idQuadras.iterator();
				while (iterator.hasNext()) {
					Quadra quadra = (Quadra) iterator.next();
					consulta = consulta + quadra.getId().toString() + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ")";
			}
			if (rotaInicial != null && !rotaInicial.trim().equals("")
					&& rotaFinal != null && !rotaFinal.trim().equals("")) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null
					&& !sequencialRotaInicial.trim().equals("")
					&& sequencialRotaFinal != null
					&& !sequencialRotaFinal.trim().equals("")) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			retorno = session.createQuery(consulta).setInteger("idRelacaoTipo",
					ClienteRelacaoTipo.USUARIO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 03/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoSituacaoLigacaoEsgoto = filtro
				.getSituacaoLigacaoEsgoto();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id " // 18
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua) "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoEsgoto != null
					&& !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				consulta = consulta
						+ " and ligacaoEsgotoSituacao.id in (:situacaoEsgoto) ";
				parameters.put("situacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and (localidade.id >= "
						+ localidadeInicial + " and localidade.id <= "
						+ localidadeFinal + ")";
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and (setorComercial.codigo >= "
						+ setorComercialInicial
						+ " and setorComercial.codigo <= "
						+ setorComercialFinal + ")";
			}

			if (rotaInicial != null) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0725] Gerar Relatório de Imóveis por Situação da Ligação de Agua
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Rafael Pinto
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = 0;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoSituacaoLigacaoEsgoto = filtro
				.getSituacaoLigacaoEsgoto();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) " // 0
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua) "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoEsgoto != null
					&& !colecaoSituacaoLigacaoEsgoto.isEmpty()) {
				consulta = consulta
						+ " and ligacaoEsgotoSituacao.id in (:situacaoEsgoto) ";
				parameters.put("situacaoEsgoto", colecaoSituacaoLigacaoEsgoto);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and (localidade.id >= "
						+ localidadeInicial + " and localidade.id <= "
						+ localidadeFinal + ")";
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and (setorComercial.codigo >= "
						+ setorComercialInicial
						+ " and setorComercial.codigo <= "
						+ setorComercialFinal + ")";
			}

			if (rotaInicial != null) {
				consulta = consulta + " and (rota.codigo >= " + rotaInicial
						+ " and rota.codigo <= " + rotaFinal + ")";
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta + " and (imovel.numeroSequencialRota >= "
						+ sequencialRotaInicial
						+ " and imovel.numeroSequencialRota <= "
						+ sequencialRotaFinal + ")";
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * @author Bruno Barros
	 * @date 06/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtraso
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer quadraInicial = filtro.getQuadraInicial();
		Integer quadraFinal = filtro.getQuadraFinal();
		
		Integer tipoRelacao = filtro.getTipoRelacao();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Integer quantidadeFaturasAtrasoInicial = filtro
				.getQuantidadeFaturasAtrasoInicial();
		Integer quantidadeFaturasAtrasoFinal = filtro
				.getQuantidadeFaturasAtrasoFinal();

		Float valorFaturasAtrasoInicial = filtro.getValorFaturasAtrasoInicial();
		Float valorFaturasAtrasoFinal = filtro.getValorFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();
		Collection<Integer> colecaoTiposCliente = filtro.getTiposCliente();
		
		Integer situacaoCobranca = filtro.getSituacaoCobranca();
		Integer grupoCobranca	 = filtro.getGrupoCobranca();
		
		String hidrometro = filtro.getHidrometro();
		

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			Map<String, Object> parameters = new HashMap<String, Object>();

			String consulta = " select \n"
					+
					// 0
					"   gr.id, \n"
					+
					// 1
					"   gr.nome, \n"
					+
					// 2
					"   un.id, \n"
					+
					// 3
					"   un.nome, \n"
					+
					// 4
					"   sc.codigo, \n"
					+
					// 5
					"   sc.descricao, \n"
					+
					// 6
					"   loc.id, \n"
					+
					// 7
					"   loc.descricao, \n"
					+
					// 8
					"   cli.nome, \n"
					+
					// 9
					"   las.descricaoAbreviado, \n"
					+
					// 10
					"   rot.codigo, \n"
					+
					// 11
					"   imo.numeroSequencialRota, \n"
					+
					// 12
					"   imo.id, \n"
					+
					// 13
					"   les.id, \n"
					+
					// 14
					"   les.descricaoAbreviado, \n"
					+
					// 15
					"   qua.numeroQuadra, \n"
					+
					// 16
					"   min( c.referencia ) as referenciaMinima, \n"
					+
					// 17
					"   count(*) as quatidadeContas, \n"
					+
					// 18
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as total, \n"
					+
					// 19
					"   max( c.referencia ) as referenciaMaxima, \n" +
					// 20
					"   imo.lote, \n" +
					// 21
					"   imo.subLote, \n" +
					// 22
					"   cli.cpf, \n" +
					// 23
					"   cli.cnpj \n" +

					" from \n" + "   Conta c, ClienteImovel ci  " + categoria
					+ " \n" + "   inner join c.imovel imo \n"
					+ "   inner join imo.localidade loc \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join loc.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
				//	+ "	  inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n"
					+ "   inner join ci.cliente cli \n"
					+ "   inner join cli.clienteTipo \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)|| !Util.isVazioOrNulo(colecaoTiposCliente)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}
			
			consulta += "where \n";
			
			if(grupoCobranca != null){
				consulta += "rot.cobrancaGrupo.id = :grupoCobranca and \n";
				parameters.put("grupoCobranca", grupoCobranca);
			}

			consulta += "   imo.id = ci.imovel.id and \n"
//					+ "   ci.clienteRelacaoTipo.id = "
//					+ ClienteRelacaoTipo.USUARIO
//					+ " and \n"
//					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) > 0 and \n"	
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			
			if (tipoRelacao != null) {
				consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao "
						+ " and \n"
						+ "   ci.dataFimRelacao is null ";
				parameters.put("tipoRelacao", tipoRelacao);
			}else{
				consulta +=  " and  ci.clienteRelacaoTipo.id = "
				+ ClienteRelacaoTipo.USUARIO
				+ " and \n"
				+ "   ci.dataFimRelacao is null ";
			}
			
			
			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";

				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}
			
			if (quadraInicial != null) {
				consulta += "  and qua.numeroQuadra between :quadraInicial and :quadraFinal ";

				parameters.put("quadraInicial", quadraInicial);
				parameters.put("quadraFinal", quadraFinal);
			}
				
			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";

				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			if(colecaoTiposCliente !=null && colecaoTiposCliente.size() >0){
//				if(tipoRelacao == null){
//					consulta += "  and ci.clienteRelacaoTipo.id = 2 and ci.dataFimRelacao = null ";
//				}
				consulta += " and cltp.id in (:colecaoClienteTipo) ";
				parameters.put("colecaoClienteTipo", colecaoTiposCliente);
			}
				
			
			//Consulta com o Hidrômetro
			if (hidrometro != null && !hidrometro.equals("0")) {
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";

				// consulta += " and imo.cobrancaSituacao.id = :situacaoCobranca
				// ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			/*
			 * -Erivan Sousa-
			 * -Caso o usuário tenha informado o mes/ano de referencia inicial e final 
			 * a pesquisa só traz resultados entre as duas referencias;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano inicial, a pesquisa só 
			 * traz resultador com referencia maior que a informada;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano final, a pesquisa só 
			 * traz resultados com referencia menor que a informada.
			 */
			if (referenciaImoveisFaturasAtrasoInicial != null && referenciaImoveisFaturasAtrasoFinal != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
				
			}else if(referenciaImoveisFaturasAtrasoInicial != null){
				consulta += " and (c.referencia >= :referenciaImoveisFaturasAtrasoInicial) ";
				parameters.put("referenciaImoveisFaturasAtrasoInicial",
					referenciaImoveisFaturasAtrasoInicial);
				
			}else if(referenciaImoveisFaturasAtrasoFinal != null){
				consulta += " and (c.referencia <= :referenciaImoveisFaturasAtrasoFinal) ";
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
					referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by \n" + "   gr.id, \n" + "   gr.nome, \n"
					+ "   un.id, \n" + "   un.nome, \n" + "   sc.codigo, \n"
					+ "   sc.descricao, \n" + "   loc.id, \n"
					+ "   loc.descricao, \n" + "   cli.nome, \n"
					+ "   cli.cpf, \n" + "   cli.cnpj, \n"
					+ "   las.descricaoAbreviado, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n" + "   imo.id, \n"
					+ "   les.id, \n" + "   les.descricaoAbreviado, \n"
					+ "   qua.numeroQuadra, \n" + "   imo.lote, \n"
					+ "   imo.subLote \n";

			if (valorFaturasAtrasoInicial != null
					|| quantidadeFaturasAtrasoInicial != null) {
				consulta += " having ";

				if (quantidadeFaturasAtrasoInicial != null) {
					consulta += "  count(*) between :quantidadeFaturasAtrasoInicial and :quantidadeFaturasAtrasoFinal";

					parameters.put("quantidadeFaturasAtrasoInicial",
							quantidadeFaturasAtrasoInicial);
					parameters.put("quantidadeFaturasAtrasoFinal",
							quantidadeFaturasAtrasoFinal);
				}

				if (valorFaturasAtrasoInicial != null) {
					if (quantidadeFaturasAtrasoInicial != null) {
						consulta += " and ";
					}

					consulta += " sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) between :valorFaturasAtrasoInicial and :valorFaturasAtrasoFinal";

					parameters.put("valorFaturasAtrasoInicial",
							valorFaturasAtrasoInicial);
					parameters.put("valorFaturasAtrasoFinal",
							valorFaturasAtrasoFinal);
				}
			}

			consulta += " order by \n" + "   gr.id, \n" + "   un.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   rot.codigo, \n" + "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			Integer referenciaImoveisFaturasAtrasoInicial = filtro
					.getReferenciaFaturasAtrasoInicial();
			Integer referenciaImoveisFaturasAtrasoFinal = filtro
					.getReferenciaFaturasAtrasoFinal();

			Integer quantidadeFaturasAtrasoInicial = filtro
					.getQuantidadeFaturasAtrasoInicial();
			Integer quantidadeFaturasAtrasoFinal = filtro
					.getQuantidadeFaturasAtrasoFinal();

			Float valorFaturasAtrasoInicial = filtro
					.getValorFaturasAtrasoInicial();
			Float valorFaturasAtrasoFinal = filtro.getValorFaturasAtrasoFinal();

			Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
					.getSituacaoLigacaoAgua();
			Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
			Collection<Integer> colecaoCategorias = filtro.getCategorias();
			Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();
			Collection<Integer> colecaoTiposCliente = filtro.getTiposCliente();
			
			Integer situacaoCobranca = filtro.getSituacaoCobranca();
			Integer grupoCobranca = filtro.getGrupoCobranca();
			
			String hidrometro = filtro.getHidrometro();

			Integer clienteSuperior = filtro.getClienteSuperior();
			Integer cliente = filtro.getCliente();
			Integer tipoRelacao = filtro.getTipoRelacao();
			Integer responsavel = filtro.getResponsavel();

			String clienteConta = "";
			if (cliente != null && responsavel != null
					&& !responsavel.equals(1)) {
				clienteConta = " , ClienteConta clienteConta ";
			}

			String categoria = "";
			if (!Util.isVazioOrNulo(colecaoCategorias)) {
				categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
			}
			
			

			String consulta = "";
			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta = " select \n"
					+
					// 0
					"   cli.id, \n"
					+
					// 1
					"   cli.nome as  cliente, \n"
					+
					// 2
					"   gr.id, \n"
					+
					// 3
					"   loc.id, \n"
					+
					// 4
					"   sc.codigo, \n"
					+
					// 5
					"   qua.numeroQuadra, \n"
					+
					// 6
					"   las.descricaoAbreviado, \n"
					+
					// 7
					"   min( c.referencia ) as referenciaMinima, \n"
					+
					// 8
					"   count(*) as quatidadeContas, \n"
					+
					// 9
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as totalSemEncargos, \n"
					+
					// 10
					"   rot.codigo, \n" +
					// 11
					"   imo.numeroSequencialRota, \n" +
					// 12
					"   imo.id, \n" +
					// 13
					"   les.descricaoAbreviado, \n" +
					// 15
					"   max( c.referencia ) as referenciaMaxima \n" +

					" from Cliente cli " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "	  inner join imo.ligacaoAgua la \n"	
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join imo.localidade loc \n"
					+ "   inner join qua.rota rot \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder) || !Util.isVazioOrNulo(colecaoTiposCliente)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}
			
			consulta += "where \n";
			
			if(grupoCobranca != null){
				consulta += "rot.cobrancaGrupo.id = :grupoCobranca and \n";
				parameters.put("grupoCobranca", grupoCobranca);
			}

			consulta += " ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) > 0 and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo.id = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2 ) ";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id ";
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";

				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			if(colecaoTiposCliente !=null && colecaoTiposCliente.size() >0){
				if(tipoRelacao == null){
					consulta += "  and ci.clienteRelacaoTipo.id = 2 and ci.dataFimRelacao = null ";
				}else{
					consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao "
							+ " and  ci.dataFimRelacao is null ";
					parameters.put("tipoRelacao", tipoRelacao);
				}
				consulta += " and cltp.id in (:colecaoClienteTipo) ";
				parameters.put("colecaoClienteTipo", colecaoTiposCliente);
			}
						
			// Consulta com o Hidrômetro
			if (hidrometro != null && !hidrometro.equals("0")) {
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			/*
			 * -Erivan Sousa-
			 * -Caso o usuário tenha informado o mes/ano de referencia inicial e final 
			 * a pesquisa só traz resultados entre as duas referencias;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano inicial, a pesquisa só 
			 * traz resultador com referencia maior que a informada;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano final, a pesquisa só 
			 * traz resultados com referencia menor que a informada.
			 */
			if (referenciaImoveisFaturasAtrasoInicial != null && referenciaImoveisFaturasAtrasoFinal != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
				
			}else if(referenciaImoveisFaturasAtrasoInicial != null){
				consulta += " and (c.referencia >= :referenciaImoveisFaturasAtrasoInicial) ";
				parameters.put("referenciaImoveisFaturasAtrasoInicial",
					referenciaImoveisFaturasAtrasoInicial);
				
			}else if(referenciaImoveisFaturasAtrasoFinal != null){
				consulta += " and (c.referencia <= :referenciaImoveisFaturasAtrasoFinal) ";
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
					referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by  cli.id,cli.nome,gr.id,loc.id,sc.codigo,"
					+ "qua.numeroQuadra,las.descricaoAbreviado,rot.codigo,"
					+ "imo.numeroSequencialRota,imo.id,les.descricaoAbreviado";

			if (valorFaturasAtrasoInicial != null
					|| quantidadeFaturasAtrasoInicial != null) {
				consulta += " having ";

				if (quantidadeFaturasAtrasoInicial != null) {
					consulta += "  count(*) between :quantidadeFaturasAtrasoInicial and :quantidadeFaturasAtrasoFinal";

					parameters.put("quantidadeFaturasAtrasoInicial",
							quantidadeFaturasAtrasoInicial);
					parameters.put("quantidadeFaturasAtrasoFinal",
							quantidadeFaturasAtrasoFinal);
				}

				if (valorFaturasAtrasoInicial != null) {
					if (quantidadeFaturasAtrasoInicial != null) {
						consulta += " and ";
					}

					consulta += " sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) between :valorFaturasAtrasoInicial and :valorFaturasAtrasoFinal";

					parameters.put("valorFaturasAtrasoInicial",
							valorFaturasAtrasoInicial);
					parameters.put("valorFaturasAtrasoFinal",
							valorFaturasAtrasoFinal);
				}
			}

			consulta += " order by \n" + "   cli.id, \n" + "   gr.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota ";
			
			Query query = criarQueryComParametros(consulta, parameters, session);
			System.out.println(query.getQueryString());
			
			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0726] Gerar Relatório de Imóveis com Faturas em Atraso
	 * 
	 * Pesquisa o Total Registro
	 * 
	 * @author Bruno Barros
	 * @date 04/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisFaturasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();
		Integer tipoRelacao = filtro.getTipoRelacao();
		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer qtd1 = filtro.getQuantidadeFaturasAtrasoInicial();
		Integer qtd2 = filtro.getQuantidadeFaturasAtrasoFinal();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoTiposCliente = filtro.getTiposCliente();
		Integer situacaoCobranca = filtro.getSituacaoCobranca();
		Integer grupoCobranca	 = filtro.getGrupoCobranca();

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			Map<String, Object> parameters = new HashMap<String, Object>();

			String consulta = " select "
					+ "   imo.id, "
					+ "   count(*) as quatidadeContas "
					+ " from "
					+ "   Conta c "
					+ "   inner join c.imovel imo "
					+ "   inner join imo.localidade loc "
					+ "   inner join loc.gerenciaRegional gr "
					+ "   inner join loc.unidadeNegocio un "
					+ "   inner join imo.setorComercial sc "
					+ "   inner join imo.ligacaoAguaSituacao las "
					+ "   inner join imo.ligacaoEsgotoSituacao les "
					+ "   inner join imo.quadra.rota rot, "
					+ "   ClienteImovel ci  "
					+ categoria
					+ "   inner join ci.cliente cli "
					+ "   inner join cli.clienteTipo cltp "
					+ " where "
					+ "   imo.id = ci.imovel.id and "
//					+ "   ci.clienteRelacaoTipo.id = "
//					+ ClienteRelacaoTipo.USUARIO
//					+ " and "
//					+ "   ci.dataFimRelacao is null and "
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and "
					+ "   coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) > 0 and "
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) ";
		
			
			if (tipoRelacao != null) {
				consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao "
						+ " and \n"
						+ "   ci.dataFimRelacao is null ";
				parameters.put("tipoRelacao", tipoRelacao);
			}else{
				consulta +=  " and  ci.clienteRelacaoTipo.id = "
				+ ClienteRelacaoTipo.USUARIO
				+ " and \n"
				+ "   ci.dataFimRelacao is null ";
			}
			
			
			if(grupoCobranca != null){
				consulta += " and rot.cobrancaGrupo.id = :grupoCobranca";
				parameters.put("grupoCobranca", grupoCobranca);
			}
			
			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";

				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (colecaoEsferasPoder != null) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}
			
			if(colecaoTiposCliente !=null && colecaoTiposCliente.size() >0){
//				if(tipoRelacao == null){
//					consulta += "  and ci.clienteRelacaoTipo.id = 2 and ci.dataFimRelacao = null ";
//				}
				consulta += " and cltp.id in (:colecaoClienteTipo) ";
				parameters.put("colecaoClienteTipo", colecaoTiposCliente);
			}
				

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca)";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			/*
			 * -Erivan Sousa-
			 * -Caso o usuário tenha informado o mes/ano de referencia inicial e final 
			 * a pesquisa só traz resultados entre as duas referencias;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano inicial, a pesquisa só 
			 * traz resultador com referencia maior que a informada;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano final, a pesquisa só 
			 * traz resultados com referencia menor que a informada.
			 */
			if (referenciaImoveisFaturasAtrasoInicial != null && referenciaImoveisFaturasAtrasoFinal != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
				
			}else if(referenciaImoveisFaturasAtrasoInicial != null){
				consulta += " and (c.referencia >= :referenciaImoveisFaturasAtrasoInicial) ";
				parameters.put("referenciaImoveisFaturasAtrasoInicial",
					referenciaImoveisFaturasAtrasoInicial);
				
			}else if(referenciaImoveisFaturasAtrasoFinal != null){
				consulta += " and (c.referencia <= :referenciaImoveisFaturasAtrasoFinal) ";
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
					referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by imo.id " +

			" having " + "   count(*) between :qtd1 and :qtd2 ";

			parameters.put("qtd1", qtd1);
			parameters.put("qtd2", qtd2);

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list().size();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer qtd1 = filtro.getQuantidadeFaturasAtrasoInicial();
		Integer qtd2 = filtro.getQuantidadeFaturasAtrasoFinal();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoTiposCliente = filtro.getTiposCliente();
		

		Integer situacaoCobranca = filtro.getSituacaoCobranca();
		Integer grupoCobranca	 = filtro.getGrupoCobranca();

		Integer clienteSuperior = filtro.getClienteSuperior();
		Integer cliente = filtro.getCliente();
		Integer tipoRelacao = filtro.getTipoRelacao();
		Integer responsavel = filtro.getResponsavel();

		String clienteConta = "";
		if (cliente != null && responsavel != null && !responsavel.equals(1)) {
			clienteConta = " , ClienteConta clienteConta ";
		}

		String categoria = "";
		if (colecaoCategorias != null) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}
		
		

		String consulta = "";
		Map<String, Object> parameters = new HashMap<String, Object>();

		try {
			consulta = " select " + "   imo.id, "
					+ "   count(*) as quatidadeContas " + " from "
					+ "   Cliente cli  " + categoria + clienteConta +  " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.contas c \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder) || !Util.isVazioOrNulo(colecaoTiposCliente)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}
			
			consulta += "where \n";
			
			if(grupoCobranca != null){
				consulta += "rot.cobrancaGrupo.id = :grupoCobranca and \n";
				parameters.put("grupoCobranca", grupoCobranca);
			}

			consulta += "   ci.dataFimRelacao is null and "
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and "
					+ "   coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) > 0 and "
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) ";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2) ";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}
			
			if (cliente != null && responsavel != null) {
				
				
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id ";
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente ";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo = :tipoRelacao ";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {

					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			
         }
			if (colecaoEsferasPoder != null) {
				consulta += " and cltp.esferaPoder.id in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}
			
			if(colecaoTiposCliente !=null && colecaoTiposCliente.size() >0){
				if(tipoRelacao == null){
					consulta += "  and ci.clienteRelacaoTipo.id = 2 and ci.dataFimRelacao = null ";
				}else{
					consulta += " and ci.clienteRelacaoTipo.id = :tipoRelacao "
							+ " and  ci.dataFimRelacao is null ";
					parameters.put("tipoRelacao", tipoRelacao);
				}
				consulta += " and cltp.id in (:colecaoClienteTipo) ";
				parameters.put("colecaoClienteTipo", colecaoTiposCliente);
			}
			

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			/*
			 * -Erivan Sousa-
			 * -Caso o usuário tenha informado o mes/ano de referencia inicial e final 
			 * a pesquisa só traz resultados entre as duas referencias;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano inicial, a pesquisa só 
			 * traz resultador com referencia maior que a informada;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano final, a pesquisa só 
			 * traz resultados com referencia menor que a informada.
			 */
			if (referenciaImoveisFaturasAtrasoInicial != null && referenciaImoveisFaturasAtrasoFinal != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
				
			}else if(referenciaImoveisFaturasAtrasoInicial != null){
				consulta += " and (c.referencia >= :referenciaImoveisFaturasAtrasoInicial) ";
				parameters.put("referenciaImoveisFaturasAtrasoInicial",
					referenciaImoveisFaturasAtrasoInicial);
				
			}else if(referenciaImoveisFaturasAtrasoFinal != null){
				consulta += " and (c.referencia <= :referenciaImoveisFaturasAtrasoFinal) ";
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
					referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by imo.id " +

			" having " + "   count(*) between :qtd1 and :qtd2 ";

			parameters.put("qtd1", qtd1);
			parameters.put("qtd2", qtd2);

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list().size();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros, Raphael Rossiter
	 * @date 17/12/2007, 11/06/2008
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();

		Collection<Integer> colecaoPerfisImovel = filtro.getColecaoPerfisImovel();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		Integer indicadorMedicaoComHidrometro = filtro
				.getIndicadorMedicaoComHidrometro();
		
		Integer anoMesReferencia = filtro
				.getAnoMesReferencia();

		String consulta = "";
		/*
		 * Query query = null; Map parameters = new HashMap();
		 */

		try {
			consulta = "select greg.greg_id as gerencia, " + // 0
					"greg.greg_nmregional as nomeGerencia, " + // 1
					"uneg.uneg_id as unidadeNegocio, " + // 2
					"uneg.uneg_nmunidadenegocio as nomeUnidadeNegocio, " + // 3
					"loca.loca_id as localidade, " + // 4
					"loca.loca_nmlocalidade as nomeLocalidade," + // 5
					"stcm.stcm_cdsetorcomercial as codigoSetor, " + // 6
					"stcm.stcm_nmsetorcomercial as nomeSetor, " + // 7
					"clie.clie_nmcliente as nomeCliente, " + // 8
					"last.last_dsligacaoaguasituacao as ligacaoAguaSituacao, " + // 9
					"consumoAgua.cshi_nnconsumomedio as consumoAgua, " + // 10
					"rota.rota_cdrota as codigoRota, " + // 11
					"imov.imov_nnsequencialrota as sequencialRota, " + // 12
					"imov.imov_id as imovel, " + // 13
					"lest.lest_dsligacaoesgotosituacao as ligacaoEsgotoSituacao, "
					+ // 14
					"consumoEsgoto.cshi_nnconsumomedio as consumoEsgoto, " + // 15
					"imov.imov_nnlote as lote, " + // 16
					"imov.imov_nnsublote as subLote, " + // 17
					"qdra.qdra_nnquadra as numeroQuadra "; // 18

			consulta += "from cadastro.cliente_imovel clim "
					+

					"inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "inner join atendimentopublico.ligacao_agua lagu on imov.imov_id=lagu.lagu_id "
					+

					"left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USUÁRIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesReferencia ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesReferencia ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				String clausulaIn = "";
				Iterator perfis = colecaoPerfisImovel.iterator();

				while (perfis.hasNext()) {
					clausulaIn += String.valueOf(perfis.next());
					if (perfis.hasNext()) {
						clausulaIn += ",";
					}
				}

				consulta += " and imov.iper_id in ( " + clausulaIn + " )";
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 1) {
				consulta += " and lagu.hidi_id is not null ";
			} else if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 2) {
				consulta += " and lagu.hidi_id is null ";
			}

			// Ordenamos
			consulta += " order by greg.greg_id, uneg.uneg_id, loca.loca_id, stcm.stcm_cdsetorcomercial, "
					+ " rota.rota_cdrota, imov.imov_nnsequencialrota ";

			retorno = session.createSQLQuery(consulta).addScalar("gerencia",
					Hibernate.INTEGER).addScalar("nomeGerencia",
					Hibernate.STRING).addScalar("unidadeNegocio",
					Hibernate.INTEGER).addScalar("nomeUnidadeNegocio",
					Hibernate.STRING).addScalar("localidade", Hibernate.INTEGER)
						.addScalar("nomeLocalidade", Hibernate.STRING)
						.addScalar("codigoSetor", Hibernate.INTEGER)
						.addScalar("nomeSetor", Hibernate.STRING)
						.addScalar("nomeCliente", Hibernate.STRING)
						.addScalar("ligacaoAguaSituacao", Hibernate.STRING)
						.addScalar("consumoAgua", Hibernate.INTEGER)
						.addScalar("codigoRota", Hibernate.SHORT)
						.addScalar("sequencialRota", Hibernate.INTEGER)
						.addScalar("imovel", Hibernate.INTEGER)
						.addScalar("ligacaoEsgotoSituacao", Hibernate.STRING)
						.addScalar("consumoEsgoto", Hibernate.INTEGER)
						.addScalar( "lote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("numeroQuadra", Hibernate.INTEGER)
						.setInteger("anoMesReferencia", anoMesReferencia.intValue())
						.list();

			/*
			 * query = session.createQuery(consulta);
			 * 
			 * Set set = parameters.keySet(); Iterator iterMap = set.iterator();
			 * while (iterMap.hasNext()) { String key = (String) iterMap.next();
			 * if (parameters.get(key) instanceof Collection) { Collection
			 * collection = (ArrayList) parameters.get(key);
			 * query.setParameterList(key, collection); } else {
			 * query.setParameter(key, parameters.get(key)); }
			 *  }
			 * 
			 * retorno = query.list();
			 */

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio Pesquisa a
	 * quantidade de imoveis para o relatorio de imoveis por consumo medio
	 * 
	 * @author Bruno Barros, Hugo Leonardo
	 * @data 17/12/2007, 12/07/2010
	 * 
	 * @param filtro
	 * @param anoMesFaturamento
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();
		
		Collection<Integer> colecaoPerfisImovel = filtro.getColecaoPerfisImovel();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		Integer indicadorMedicaoComHidrometro = filtro
				.getIndicadorMedicaoComHidrometro();
		
		Integer anoMesReferencia = filtro.getAnoMesReferencia();

		String consulta = "";
		// Map parameters = new HashMap();

		try {
			consulta = "select count(clim.clim_id) as clienteImo "; // 18

			consulta += "from cadastro.cliente_imovel clim "
					+ "inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "inner join atendimentopublico.ligacao_agua lagu on imov.imov_id=lagu.lagu_id "
					+

					"left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USUÁRIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesReferencia ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesReferencia ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";
			

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}
			
			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {

				Iterator perfis = colecaoPerfisImovel.iterator();

				String perfisImovel = "";
				
				while (perfis.hasNext()) {
					perfisImovel += perfis.next();
					if (perfis.hasNext()) {
						perfisImovel += ", ";
					}
				}
				
				consulta += " and imov.iper_id in ( " + perfisImovel + " )";
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 1) {
				consulta += " and lagu.hidi_id is not null ";
			} else if (indicadorMedicaoComHidrometro != null
					&& indicadorMedicaoComHidrometro == 2) {
				consulta += " and lagu.hidi_id is null ";
			}

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"clienteImo", Hibernate.INTEGER).setInteger(
					"anoMesReferencia", anoMesReferencia.intValue())
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfil = filtro.getPerfilImovel();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id, " // 18
					+ " imovel.quantidadeEconomias " // 19
					+ " from ClienteImovel clienteImovel,"
					+ " ImovelSubcategoria imovelSubcateg"
					+ " left join imovelSubcateg.comp_id.subcategoria subcateg"
					+ " left join subcateg.categoria categ"
					+ " left join imovelSubcateg.comp_id.imovel imovelCateg"
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.imovelPerfil perfil "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where imovelCateg.id = clienteImovel.imovel.id "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAguaSituacao.id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta + " and categ.id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (colecaoPerfil != null && colecaoPerfil.size() > 0){
				consulta = consulta + " and perfil.id in (:colecaoPerfil) ";
				parameters.put("colecaoPerfil", colecaoPerfil);
			}
			
			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0731] Gerar Relatório de Imóveis com os Ultimos Consumos de Agua
	 * 
	 * @author Rafael Pinto
	 * @date 18/12/2007
	 * 
	 * @param FiltrarRelatorioImoveisUltimosConsumosAguaHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfilImovel = filtro.getPerfilImovel();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) "
					+ " from ClienteImovel clienteImovel,"
					+ " ImovelSubcategoria imovelSubcateg"
					+ " left join imovelSubcateg.comp_id.subcategoria subcateg"
					+ " left join subcateg.categoria categ"
					+ " left join imovelSubcateg.comp_id.imovel imovelCateg"
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join imovel.imovelPerfil perfil "
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where imovelCateg.id = clienteImovel.imovel.id "
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null ";

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAguaSituacao.id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta + " and categ.id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}
			
			if (colecaoPerfilImovel != null && colecaoPerfilImovel.size() > 0) {
				consulta = consulta + " and perfil.id in (:colecaoPerfilImovel) ";
				parameters.put("colecaoPerfilImovel", colecaoPerfilImovel);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select imovel.id, " // 0
					+ " gerenciaRegional.id, " // 1
					+ " gerenciaRegional.nome, "// 2
					+ " unidadeNegocio.id," // 3
					+ " unidadeNegocio.nome," // 4
					+ " localidade.id, " // 5
					+ " localidade.descricao, " // 6
					+ " setorComercial.codigo, "// 7
					+ " setorComercial.descricao, "// 8
					+ " quadra.numeroQuadra, " // 9
					+ " cliente.nome, " // 10
					+ " ligacaoAguaSituacao.descricao, " // 11
					+ " ligacaoEsgotoSituacao.descricao, " // 12
					+ " rota.codigo," // 13
					+ " imovel.numeroSequencialRota, " // 14
					+ " imovel.lote, " // 15
					+ " imovel.subLote, " // 16
					+ " setorComercial.id, "// 17
					+ " rota.id " // 18
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " inner join imovel.ligacaoAgua ligacaoAgua"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua1,:situacaoAgua2) "
					+ " and ligacaoAgua.hidrometroInstalacaoHistorico is null"
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null "
					+ " and imovel.indicadorExclusao <> :indicadorExclusao";

			parameters.put("situacaoAgua1", LigacaoAguaSituacao.LIGADO);
			parameters.put("situacaoAgua2", LigacaoAguaSituacao.CORTADO);
			parameters.put("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO);

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by gerenciaRegional.id,unidadeNegocio.id,localidade.id,"
					+ "setorComercial.codigo,quadra.numeroQuadra,imovel.lote,imovel.subLote,"
					+ "rota.codigo,imovel.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00728] Gerar Relatório de Imóveis Ativos e Não Medidos
	 * 
	 * @author Rafael Pinto
	 * @date 03/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisAtivosNaoMedidosHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {
			consulta = "select count(*) "
					+ " from ClienteImovel clienteImovel "
					+ " inner join clienteImovel.clienteRelacaoTipo relacaoTipo"
					+ " inner join clienteImovel.imovel imovel "
					+ " inner join imovel.localidade localidade "
					+ " inner join imovel.setorComercial setorComercial "
					+ " inner join imovel.quadra quadra "
					+ " inner join quadra.rota rota"
					+ " inner join clienteImovel.cliente cliente"
					+ " inner join localidade.unidadeNegocio unidadeNegocio"
					+ " inner join localidade.gerenciaRegional gerenciaRegional"
					+ " inner join imovel.ligacaoAguaSituacao ligacaoAguaSituacao"
					+ " left join imovel.ligacaoEsgotoSituacao ligacaoEsgotoSituacao"
					+ " where ligacaoAguaSituacao.id in (:situacaoAgua1,:situacaoAgua2) "
					+ " and imovel.hidrometroInstalacaoHistorico is null"
					+ " and relacaoTipo.id = :idRelacaoTipo"
					+ " and clienteImovel.dataFimRelacao is null "
					+ " and imovel.indicadorExclusao <> :indicadorExclusao";

			parameters.put("situacaoAgua1", LigacaoAguaSituacao.LIGADO);
			parameters.put("situacaoAgua2", LigacaoAguaSituacao.CORTADO);
			parameters.put("indicadorExclusao", Imovel.IMOVEL_EXCLUIDO);

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidadeNegocio.id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta
						+ " and gerenciaRegional.id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and localidade.id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta + " and setorComercial.codigo between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rota.codigo between " + rotaInicial
						+ " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imovel.numeroSequencialRota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param
	 * FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer referenciaDiaInicial = filtro.getReferenciaFaturasDiaInicial();
		Integer referenciaDiaFinal = filtro.getReferenciaFaturasDiaFinal();

		Integer referenciaAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "select "
					+ "imoveis_em_dia.imov_id as idImovel, " // 0
					+ "gerencia.greg_id as idGerencia, " // 1
					+ "gerencia.greg_nmregional as nomeGerencia, " // 2
					+ "unidade.uneg_id as idUnidade, " // 3
					+ "unidade.uneg_nmunidadenegocio as nomeUnidade, " // 4
					+ "local.loca_id as idLocalidade, " // 5
					+ "local.loca_nmlocalidade as nomeLocalidade, " // 6
					+ "setor.stcm_cdsetorcomercial as codigoSetor, "// 7
					+ "setor.stcm_nmsetorcomercial as nomeSetor, " // 8
					+ "qua.qdra_nnquadra as numeroQuadra, " // 9
					+ "cli.clie_nmcliente as nomeCliente, " // 10
					+ "ligacaoAgua.last_dsligacaoaguasituacao as situacaoAgua, " // 11
					+ "ligacaoEsgoto.lest_dsligacaoesgotosituacao as situacaoEsgoto, " // 12
					+ "rot.rota_cdrota as codigoRota, " // 13
					+ "imov.imov_nnsequencialrota as sequenciaRota, " // 14
					+ "imov.imov_nnlote as numeroLote, " // 15
					+ "imov.imov_nnsublote as numeroSubLote, " // 16
					+ "setor.stcm_id as idSetor, " // 17
					+ "rot.rota_id as idRota, " // 18
					+ "imov.imov_qteconomia as qtdEconomia "// 19
					+ "from "
					+ "( select i.imov_id from "
					+ "cadastro.imovel i "
					+ "where not exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaDiaInicial
					+ " and "
					+ referenciaDiaFinal
					+ " and "
					+ "contaAtual.imov_id = i.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "
					+ ") imoveis_em_dia "

					+ "inner join cadastro.imovel imov on imov.imov_id = imoveis_em_dia.imov_id "
					+ "inner join cadastro.cliente_imovel cliImovel on cliImovel.imov_id = imov.imov_id "
					+ "inner join cadastro.cliente cli on cli.clie_id = cliImovel.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo relacaoTipo on relacaoTipo.crtp_id = cliImovel.crtp_id "
					+ "inner join cadastro.localidade local on local.loca_id = imov.loca_id "
					+ "inner join cadastro.setor_comercial setor on setor.stcm_id = imov.stcm_id "
					+ "inner join cadastro.quadra qua on qua.qdra_id = imov.qdra_id "
					+ "inner join micromedicao.rota rot on rot.rota_id = qua.rota_id "
					+ "inner join cadastro.unidade_negocio unidade on unidade.uneg_id = local.uneg_id "
					+ "inner join cadastro.gerencia_regional gerencia on gerencia.greg_id = local.greg_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoAgua on ligacaoAgua.last_id = imov.last_id "
					+ "left join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgoto on ligacaoEsgoto.lest_id = imov.lest_id ";

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ "inner join cadastro.imovel_subcategoria imovelSubCat on imovelSubCat.imov_id = imov.imov_id "
						+ "inner join cadastro.subcategoria subCat on subCat.scat_id = imovelSubCat.scat_id ";
			}

			consulta = consulta
					+ "where exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaAtrasoInicial
					+ " and "
					+ referenciaAtrasoFinal
					+ " and "
					+ "contaAtual.imov_id = imoveis_em_dia.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "

					+ "and relacaoTipo.crtp_id = :idRelacaoTipo "
					+ "and cliImovel.clim_dtrelacaofim is null ";

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAgua.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ " and subCat.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidade.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and gerencia.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and local.loca_id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta
						+ " and setor.stcm_cdsetorcomercial between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rot.rota_cdrota between "
						+ rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			consulta += " order by idGerencia,idUnidade,idLocalidade,"
					+ "codigoSetor,numeroQuadra,numeroLote,numeroSubLote,"
					+ "codigoRota,sequenciaRota";

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("nomeGerencia",
					Hibernate.STRING).addScalar("idUnidade", Hibernate.INTEGER)
					.addScalar("nomeUnidade", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"codigoSetor", Hibernate.INTEGER).addScalar(
							"nomeSetor", Hibernate.STRING).addScalar(
							"numeroQuadra", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar(
							"situacaoAgua", Hibernate.STRING).addScalar(
							"situacaoEsgoto", Hibernate.STRING).addScalar(
							"codigoRota", Hibernate.SHORT).addScalar(
							"sequenciaRota", Hibernate.INTEGER).addScalar(
							"numeroLote", Hibernate.SHORT).addScalar(
							"numeroSubLote", Hibernate.SHORT).addScalar(
							"idSetor", Hibernate.INTEGER).addScalar("idRota",
							Hibernate.INTEGER).addScalar("qtdEconomia",
							Hibernate.SHORT);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e
	 * Faturas Antigas em Atraso
	 * 
	 * @author Rafael Pinto
	 * @date 08/01/2008
	 * 
	 * @param
	 * FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
				.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();

		Integer referenciaDiaInicial = filtro.getReferenciaFaturasDiaInicial();
		Integer referenciaDiaFinal = filtro.getReferenciaFaturasDiaFinal();

		Integer referenciaAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "select count(*) as quantidade "
					+ "from "
					+ "( select i.imov_id from "
					+ "cadastro.imovel i "
					+ "where exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaDiaInicial
					+ " and "
					+ referenciaDiaFinal
					+ " and "
					+ "contaAtual.imov_id = i.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "
					+ ") imoveis_em_dia "

					+ "inner join cadastro.imovel imov on imov.imov_id = imoveis_em_dia.imov_id "
					+ "inner join cadastro.cliente_imovel cliImovel on cliImovel.imov_id = imov.imov_id "
					+ "inner join cadastro.cliente cli on cli.clie_id = cliImovel.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo relacaoTipo on relacaoTipo.crtp_id = cliImovel.crtp_id "
					+ "inner join cadastro.localidade local on local.loca_id = imov.loca_id "
					+ "inner join cadastro.setor_comercial setor on setor.stcm_id = imov.stcm_id "
					+ "inner join cadastro.quadra qua on qua.qdra_id = imov.qdra_id "
					+ "inner join micromedicao.rota rot on rot.rota_id = qua.rota_id "
					+ "inner join cadastro.unidade_negocio unidade on unidade.uneg_id = local.uneg_id "
					+ "inner join cadastro.gerencia_regional gerencia on gerencia.greg_id = local.greg_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao ligacaoAgua on ligacaoAgua.last_id = imov.last_id "
					+ "left join atendimentopublico.ligacao_esgoto_situacao ligacaoEsgoto on ligacaoEsgoto.lest_id = imov.lest_id ";

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ "inner join cadastro.imovel_subcategoria imovelSubCat on imovelSubCat.imov_id = imov.imov_id "
						+ "inner join cadastro.subcategoria subCat on subCat.scat_id = imovelSubCat.scat_id ";
			}

			consulta = consulta
					+ "where not exists( "
					+ "select contaAtual.cnta_id "
					+ "from faturamento.conta contaAtual "
					+ "where contaAtual.dcst_idatual in ( 0,1,2 ) and "
					+ "contaAtual.cnta_amreferenciaconta between "
					+ referenciaAtrasoInicial
					+ " and "
					+ referenciaAtrasoFinal
					+ " and "
					+ "contaAtual.imov_id = imoveis_em_dia.imov_id and "
					+ "not exists( select pgto.pgmt_id from arrecadacao.pagamento pgto where contaAtual.cnta_id = pgto.cnta_id ) ) "

					+ "and relacaoTipo.crtp_id = :idRelacaoTipo "
					+ "and cliImovel.clim_dtrelacaofim is null ";

			parameters.put("idRelacaoTipo", ClienteRelacaoTipo.USUARIO);

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta = consulta
						+ " and ligacaoAgua.last_id in (:situacaoAgua) ";
				parameters.put("situacaoAgua", colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta = consulta
						+ " and subCat.catg_id in (:colecaoCategorias) ";
				parameters.put("colecaoCategorias", colecaoCategorias);
			}

			if (unidadeNegocio != null) {
				consulta = consulta + " and unidade.uneg_id in (:unidade) ";
				parameters.put("unidade", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta = consulta + " and gerencia.greg_id in (:gerencia) ";
				parameters.put("gerencia", gerencia);
			}

			if (localidadeInicial != null) {
				consulta = consulta + " and local.loca_id between "
						+ localidadeInicial + " and " + localidadeFinal;
			}

			if (setorComercialInicial != null) {
				consulta = consulta
						+ " and setor.stcm_cdsetorcomercial between "
						+ setorComercialInicial + " and " + setorComercialFinal;
			}

			if (rotaInicial != null) {
				consulta = consulta + " and rot.rota_cdrota between "
						+ rotaInicial + " and " + rotaFinal;
			}

			if (sequencialRotaInicial != null) {
				consulta = consulta
						+ " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial + " and " + sequencialRotaFinal;
			}

			query = session.createSQLQuery(consulta).addScalar("quantidade",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisInicial = filtro.getReferenciaInicial();
		Integer referenciaImoveisFinal = filtro.getReferenciaFinal();

		Collection<Integer> colecaoTiposConsumo = filtro.getTiposConsumo();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select \n" +
			// Gerencia Regional id - Indice 0
					"   gr.id, \n" +
					// Gerencia Regional nome - Indice 1
					"   gr.nome, \n" +
					// Unidade Negócio id - Indice 2
					"   un.id, \n" +
					// Unidade Negócio nome - Indice 3
					"   un.nome, \n" +
					// Localidade id - Indice 4
					"   loca.id, \n" +
					// Localidade descricao - Indice 5
					"   loca.descricao, \n" +
					// Setor Comercial codigo - Indice 6
					"   sc.codigo, \n" +
					// Setor Comercial descricao - Indice 7
					"   sc.descricao, \n" +
					// Imovel id - Indice 8
					"   imo.id, \n" +
					// Cliente nome- Indice 9
					"   cl.nome, \n" +
					// Ligacao Agua Situacao Descricao - Indice 10
					"   las.descricao, \n" +
					// Consumo Tipo descricao - Indice 11
					"   ct.descricao, \n" +
					// Rota codigo - Indice 12
					"   rt.codigo, \n" +
					// Numero do Sequencial da Rota - Indice 13
					"   imo.numeroSequencialRota, \n" +
					// Ligacao Esgoto Situacao - Indice 14
					"   les.descricao, \n" +
					// Numero da Quadra - Indice 15
					"   qua.numeroQuadra, \n" +
					// Lote - Indice 16
					"   imo.lote, \n" +
					// Sublote - Indice 17
					" 	imo.subLote, \n" +
					// Referencia - Indice 18
					"   ch.referenciaFaturamento \n" + " from \n"
					+ "   ConsumoHistorico ch \n"
					+ "   inner join ch.imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join ch.consumoTipo ct \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les, \n"
					+ "   ClienteImovel ci \n"
					+ "   inner join ci.cliente cl \n" + " where \n"
					+ "   ci.imovel.id = imo.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO + " and \n"
					+ "   ci.dataFimRelacao is null \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (referenciaImoveisInicial != null) {
				consulta += " and ch.referenciaFaturamento between :amInicial and :amFinal \n";

				parameters.put("amInicial", referenciaImoveisInicial);
				parameters.put("amFinal", referenciaImoveisFinal);
			}

			if (colecaoTiposConsumo != null && colecaoTiposConsumo.size() > 0) {
				consulta += " and ch.consumoTipo.id in (:tiposConsumo) \n";
				parameters.put("tiposConsumo", colecaoTiposConsumo);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.codigo, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota, \n "
					+ "   imo.id";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0762] Gerar Arquivo Texto com Dados Cadastrais - CAERN
	 * 
	 * A pesquisa retorna uma colecao de Imoveis para que a partir daí comece a
	 * geracao das linhas TXTs.
	 * 
	 * @author Tiago Moreno
	 * @date 08/04/2008
	 * 
	 * @param ArquivoTextoDadosCadastraisHelper
	 * 
	 * @return Collection<Imovel>
	 * @throws ControladorException
	 */
	public Collection<Imovel> pesquisarImovelArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper objeto)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = objeto.getLocalidadeInicial();
		Integer localidadeFinal = objeto.getLocalidadeFinal();

		Integer setorComercialInicial = objeto.getSetorComercialInicial();
		Integer setorComercialFinal = objeto.getSetorComercialFinal();

		Integer rotaInicial = objeto.getRotaInicial();
		Integer rotaFinal = objeto.getRotaFinal();

		Integer sequencialRotaInicial = objeto.getSequencialRotalInicial();
		Integer sequencialRotaFinal = objeto.getSequencialRotalFinal();

		Integer unidadeNegocio = objeto.getUnidadeNegocio();
		Integer gerencia = objeto.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select imo " + " from \n" + "   Imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   left join imo.ligacaoEsgoto le \n"
					+ "   left join le.ligacaoEsgotoPerfil le \n" + " where \n"
					+ "   imo.indicadorExclusao = 2 \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.id, \n " + "   imo.id, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0763] Gerar Arquivo Texto de Ligacoes com Hidrometro - CAERN
	 * 
	 * @author Tiago Moreno
	 * @date 10/04/2008
	 * 
	 * @param ArquivoTextoLigacoesHidrometroHelper
	 * 
	 * @return
	 * @throws ControladorException
	 */

	public Collection<HidrometroInstalacaoHistorico> pesquisarImovelArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper objeto)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = objeto.getLocalidadeInicial();
		Integer localidadeFinal = objeto.getLocalidadeFinal();

		Integer setorComercialInicial = objeto.getSetorComercialInicial();
		Integer setorComercialFinal = objeto.getSetorComercialFinal();

		Integer rotaInicial = objeto.getRotaInicial();
		Integer rotaFinal = objeto.getRotaFinal();

		Integer sequencialRotaInicial = objeto.getSequencialRotalInicial();
		Integer sequencialRotaFinal = objeto.getSequencialRotalFinal();

		Integer unidadeNegocio = objeto.getUnidadeNegocio();
		Integer gerencia = objeto.getGerenciaRegional();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select iih " + " from \n"
					+ "   HidrometroInstalacaoHistorico iih , Imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   left join fetch iih.hidrometro hd \n"
					+ "   left join fetch iih.hidrometroLocalInstalacao hli \n"
					+ "   left join fetch hd.hidrometroCapacidade hc \n"
					+ " where \n" + "   imo.indicadorExclusao = 2 \n"
					+ "   and las.id in (3,5) and iih.dataRetirada is null \n"
					+ "   and iih.ligacaoAgua.id = imo.id \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.codigo between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			consulta += " order by \n " + "   gr.id, \n " + "   un.id, \n "
					+ "   loca.id, \n " + "   sc.id, \n " + "   imo.id, \n "
					+ "   rt.codigo, \n " + "   imo.numeroSequencialRota";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0729] Gerar Relatório de Imóveis por Tipo Consumo
	 * 
	 * @author Bruno Barros
	 * @date 14/01/2008
	 * 
	 * @param FiltrarRelatorioImoveisTipoConsumo
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisInicial = filtro.getReferenciaInicial();
		Integer referenciaImoveisFinal = filtro.getReferenciaFinal();

		Collection<Integer> colecaoTiposConsumo = filtro.getTiposConsumo();

		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select \n" + "   count(*) \n" + " from \n"
					+ "   ConsumoHistorico ch \n"
					+ "   inner join ch.imovel imo \n"
					+ "   inner join imo.localidade loca \n"
					+ "   inner join loca.gerenciaRegional gr \n"
					+ "   inner join loca.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join ch.consumoTipo ct \n"
					+ "   inner join imo.quadra qua \n"
					+ "   inner join qua.rota rt \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les, \n"
					+ "   ClienteImovel ci \n"
					+ "   inner join ci.cliente cl \n" + " where \n"
					+ "   ci.imovel.id = imo.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO + " and \n"
					+ "   ci.dataFimRelacao is null \n";

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio \n";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional \n";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (localidadeInicial != null) {
				consulta += " and loca.id between :localidadeInicial and :localidadeFinal \n";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.id between :setorComercialInicial and :setorComercialFinal \n";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}

			if (rotaInicial != null) {
				consulta += " and rt.id between :rotaInicial and :rotaFinal \n";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal \n";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
			}

			if (referenciaImoveisInicial != null) {
				consulta += " and ch.referenciaFaturamento between :amInicial and :amFinal \n";

				parameters.put("amInicial", referenciaImoveisInicial);
				parameters.put("amFinal", referenciaImoveisFinal);
			}

			if (colecaoTiposConsumo != null && colecaoTiposConsumo.size() > 0) {
				consulta += " and ch.consumoTipo.id in (:tiposConsumo) \n";
				parameters.put("tiposConsumo", colecaoTiposConsumo);
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();

			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = (Integer) query.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * Pesquisa o id localidade,codigo setor e codigo da rota apartir do id da
	 * rota
	 * 
	 * @author Rafael Pinto
	 * 
	 * @date 02/06/2008
	 * 
	 * @throws ErroRepositorioException
	 * @return Object[3] onde :
	 * 
	 * Object[0]=id localidade Object[1]=codigo do setor Object[2]=codigo da
	 * rota
	 */
	public Object[] pesquisarDadosRotaEntregaContaPorRota(Integer idRota)
			throws ErroRepositorioException {

		Object[] retorno = null;

		String consulta = "";

		// obtém a sessão
		Session session = HibernateUtil.getSession();

		try {

			consulta = "SELECT local.id," + "setor.codigo," + "rot.codigo "
					+ "FROM Rota rot "
					+ "LEFT JOIN FETCH rot.setorComercial setor "
					+ "LEFT JOIN FETCH setor.localidade local "
					+ "WHERE rot.id = :idRota ";

			retorno = (Object[]) session.createQuery(consulta).setInteger(
					"idRota", idRota).setMaxResults(1).uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna
		return retorno;
	}

	/**
	 * [UC0330] Inserir Mensagem da Conta
	 * 
	 * [SB0001] Pesquisar Setor Comercial
	 * 
	 * @author Raphael Rossiter
	 * @date 25/06/2008
	 * 
	 * @param tipoArgumento
	 * @param indiceInicial
	 * @param indiceFinal
	 * @param anoMesReferencia
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarSetorComercialPorQualidadeAgua(
			int tipoArgumento, BigDecimal indiceInicial,
			BigDecimal indiceFinal, Integer anoMesReferencia)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Collection retorno = null;
		String consulta = null;

		try {

			consulta = "SELECT DISTINCT(loca.id), loca.descricao, stcm "
					+ "FROM QualidadeAgua qlag "
					+ "LEFT JOIN qlag.localidade loca "
					+ "LEFT JOIN qlag.setorComercial stcm "
					+ "WHERE qlag.anoMesReferencia = :anoMesReferencia ";

			switch (tipoArgumento) {

			case ConstantesSistema.TURBIDEZ:

				consulta += "AND qlag.numeroIndiceTurbidez BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.CLORO:

				consulta += "AND qlag.numeroCloroResidual BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.PH:

				consulta += "AND qlag.numeroIndicePh BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COR:

				consulta += "AND qlag.numeroIndiceCor BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.FLUOR:

				consulta += "AND qlag.numeroIndiceFluor BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.FERRO:

				consulta += "AND qlag.numeroIndiceFerro BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COLIFORMES_TOTAIS:

				consulta += "AND qlag.numeroIndiceColiformesTotais BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			case ConstantesSistema.COLIFORMES_FECAIS:

				consulta += "AND qlag.numeroIndiceColiformesFecais BETWEEN :indiceInicial AND :indiceFinal ";
				break;

			default:

				consulta += "AND qlag.numeroNitrato BETWEEN :indiceInicial AND :indiceFinal ";
				break;
			}

			consulta += "ORDER BY loca.descricao, stcm.descricao";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setBigDecimal(
					"indiceInicial", indiceInicial).setBigDecimal(
					"indiceFinal", indiceFinal).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Object[] obterImovelGeracaoTabelasTemporarias(Integer idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retorno = null;

		Query query = null;

		try {

			consulta = "SELECT distinct (imov.imov_id) as idImovel, "
					+ // 0
					" imov.loca_id as idLocalidade, "
					+ // 1
					" stcm.stcm_cdsetorcomercial as codigoSetorComercial, "
					+ // 2
					" qdra.qdra_nnquadra as numeroQuadra, "
					+ // 3
					" imov_nnlote as lote, "
					+ // 4
					" imov_nnsublote as sublote, "
					+ // 5
					" imov_nnsequencialrota as numSequencialRota, "
					+ // 6
					" imov_nnmorador as numMorador, "
					+ // 7
					" lgcp.logr_id as cepLogradouro, "
					+ // 8
					" lgbr.logr_id as bairroLogradouro, "
					+ // 9
					" cep.cep_cdcep as codigoCep,"
					+ // 10
					" bairro.bair_id as idBairro,"
					+ // 11
					" bairro.bair_nmbairro as descricaoBairro,"
					+ // 12
					" imov.edrf_id as enderecoReferencia, "
					+ // 13
					" imov_nnimovel as numImovel, "
					+ // 14
					" imov_dscomplementoendereco as complementoEndereco, "
					+ // 15
					" imov_nnareaconstruida as areaConstruidaFaixa, "
					+ // 16
					" last_id as ligacaoAguaSituacao, "
					+ // 17
					" imov_voreservatorioinferior as volResInferior, "
					+ // 18
					" imov_voreservatoriosuperior as volResSuperior, "
					+ // 19
					" imov_vopiscina as volumePiscina, "
					+ // 20
					" imov_icjardim as indJardim, "
					+ // 21
					" pcal_id as pavimentoCalcada, "
					+ // 22
					" prua_id as pavimentoRua, "
					+ // 23
					" ftab_id as fonteAbastecimento, "
					+ // 24
					" poco_id as pocoTipo, "
					+ // 25
					" imov_nnpontosutilizacao as numPontosUtilizacao, "
					+ // 26
					" lest_id as ligacaoEsgotoSituacao, "
					+ // 27
					" iper_id  as imovelPerfil, "
					+ // 28
					" depj_id as despejo, "
					+ // 29
					" imov_nncoordenadax as coordenadaX, "
					+ // 30
					" imov_nncoordenaday as coordenadaY, "
					+ // 31
					" imov_idimovelprincipal as imovelPrincipal, "
					+ // 32
					" imov_nniptu as numIptu, "
					+ // 33
					" imov_nncontratoenergia as numCelpe "
					+ // 34
					" from cadastro.imovel imov "
					+ " inner join cadastro.setor_comercial stcm on(imov.stcm_id = stcm.stcm_id)"
					+ " inner join cadastro.quadra qdra on(imov.qdra_id = qdra.qdra_id)"
					+ " left join cadastro.logradouro_cep lgcp on(imov.lgcp_id = lgcp.lgcp_id)"
					+ " left join cadastro.cep cep on (lgcp.cep_id = cep.cep_id)"
					+ " left join cadastro.logradouro_bairro lgbr on(imov.lgbr_id = lgbr.lgbr_id)"
					+ " left join cadastro.bairro bairro on (lgbr.bair_id = bairro.bair_id)"
					+ " where imov.imov_id =:idImovel";

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("codigoSetorComercial",
					Hibernate.INTEGER).addScalar("numeroQuadra",
					Hibernate.INTEGER).addScalar("lote", Hibernate.INTEGER)
					.addScalar("sublote", Hibernate.INTEGER).addScalar(
							"numSequencialRota", Hibernate.INTEGER).addScalar(
							"numMorador", Hibernate.SHORT).addScalar(
							"cepLogradouro", Hibernate.INTEGER).addScalar(
							"bairroLogradouro", Hibernate.LONG).addScalar(
							"codigoCep", Hibernate.INTEGER).addScalar(
							"idBairro", Hibernate.INTEGER).addScalar(
							"descricaoBairro", Hibernate.STRING).addScalar(
							"enderecoReferencia", Hibernate.INTEGER).addScalar(
							"numImovel", Hibernate.STRING).addScalar(
							"complementoEndereco", Hibernate.STRING).addScalar(
							"areaConstruidaFaixa", Hibernate.BIG_DECIMAL)
					.addScalar("ligacaoAguaSituacao", Hibernate.INTEGER)
					.addScalar("volResInferior", Hibernate.BIG_DECIMAL)
					.addScalar("volResSuperior", Hibernate.BIG_DECIMAL)
					.addScalar("volumePiscina", Hibernate.BIG_DECIMAL)
					.addScalar("indJardim", Hibernate.SHORT).addScalar(
							"pavimentoCalcada", Hibernate.INTEGER).addScalar(
							"pavimentoRua", Hibernate.INTEGER).addScalar(
							"fonteAbastecimento", Hibernate.INTEGER).addScalar(
							"pocoTipo", Hibernate.INTEGER).addScalar(
							"numPontosUtilizacao", Hibernate.SHORT).addScalar(
							"ligacaoEsgotoSituacao", Hibernate.INTEGER)
					.addScalar("imovelPerfil", Hibernate.INTEGER).addScalar(
							"despejo", Hibernate.INTEGER).addScalar(
							"coordenadaX", Hibernate.BIG_DECIMAL).addScalar(
							"coordenadaY", Hibernate.BIG_DECIMAL).addScalar(
							"imovelPrincipal", Hibernate.INTEGER).addScalar(
							"numIptu", Hibernate.BIG_DECIMAL).addScalar(
							"numCelpe", Hibernate.LONG).setInteger("idImovel",
							idImovel);

			retorno = (Object[]) query.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC0831] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 20/09/2008
	 * 
	 * @return ImovelSubcategoria
	 * @throws ErroRepositorioException
	 */

	public Collection obterImovelSubcategoriaAtualizacaoCadastral(
			Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retornoConsulta = null;
		Collection imovelSubcategorias = new ArrayList();

		try {

			consulta = " select scat.scat_id as idSubcategoria,"
					+ // 0
					" imsb_qteconomia as qteEconomia,"
					+ // 1
					" scat_dssubcategoria as descricaoSubcategoria,"
					+ // 2
					" catg.catg_id as idCategoria,"
					+ // 3
					" catg_dscategoria as descricaoCategoria"
					+ // 4
					" from cadastro.imovel_subcategoria isac"
					+ " inner join cadastro.subcategoria scat on (isac.scat_id = scat.scat_id)"
					+ " inner join cadastro.categoria  catg on (scat.catg_id = catg.catg_id)"
					+ " where imov_id = :idImovel";

			retornoConsulta = session.createSQLQuery(consulta).addScalar(
					"idSubcategoria", Hibernate.INTEGER).addScalar(
					"qteEconomia", Hibernate.SHORT).addScalar(
					"descricaoSubcategoria", Hibernate.STRING).addScalar(
					"idCategoria", Hibernate.INTEGER).addScalar(
					"descricaoCategoria", Hibernate.STRING).setInteger(
					"idImovel", idImovel).list();

			if (retornoConsulta.size() > 0) {
				Iterator imovelSubcategoriaIter = retornoConsulta.iterator();
				while (imovelSubcategoriaIter.hasNext()) {

					Object[] element = (Object[]) imovelSubcategoriaIter.next();

					ImovelSubcategoriaAtualizacaoCadastralDM imovSubAtual = new ImovelSubcategoriaAtualizacaoCadastralDM();

					ImovelAtualizacaoCadastralDM imovel = new ImovelAtualizacaoCadastralDM();
					imovel.setId(idImovel);
					
					imovSubAtual.setImovelAtualizacaoCadastralDM(imovel);
					
					Subcategoria sub = new Subcategoria();
					sub.setId((Integer) element[0]);
					sub.setDescricao((String) element[2]);

					imovSubAtual.setSubcategoria(sub);

					imovSubAtual.setQuantidadeEconomias((Short) element[1]);
					Categoria categoria = new Categoria();
					categoria.setId((Integer) element[3]);
					categoria.setDescricao((String) element[4]);
					imovSubAtual.setCategoria(categoria);
					
					imovelSubcategorias.add(imovSubAtual);
				}
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return imovelSubcategorias;

	}

	/*
	 * [CRC] - 1672 - Melhorar performance das consultas do relatorio de imóveis
	 * com faturas em atraso.
	 * 
	 * O filtro da qtd de faturas em atraso e valor das Faturas em Atraso não
	 * são usados nas querys pois para que ela trouxesse o valor correto seria
	 * necessário um subselect, o que tornaria a consulta mais lenta. Assim,
	 * esse filtro é usado no momento de preparar os relatoriosBeans.
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();
		
		Integer quadraInicial = filtro.getQuadraInicial();
		Integer quadraFinal = filtro.getQuadraFinal();
		
		Integer tipoRelacao = filtro.getTipoRelacao();

		Short rotaInicial = filtro.getRotaInicial();
		Short rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer referenciaImoveisFaturasAtrasoInicial = filtro
				.getReferenciaFaturasAtrasoInicial();
		Integer referenciaImoveisFaturasAtrasoFinal = filtro
				.getReferenciaFaturasAtrasoFinal();

		Collection<Integer> colecaoSituacaoLigacaoAgua = filtro.getSituacaoLigacaoAgua();
		Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
		Collection<Integer> colecaoCategorias = filtro.getCategorias();
		Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();
		Collection<Integer> colecaoTiposCliente = filtro.getTiposCliente();

		String hidrometro = filtro.getHidrometro();
		
		Integer situacaoCobranca = filtro.getSituacaoCobranca();
		Integer grupoCobranca	 = filtro.getGrupoCobranca();
		

		String categoria = "";
		if (!Util.isVazioOrNulo(colecaoCategorias)) {
			categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
		}

		try {

			String consulta = "";
			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta = " select \n"
					+
					// 0
					"   gr.id, \n"
					+
					// 1
					"   gr.nome, \n"
					+
					// 2
					"   un.id, \n"
					+
					// 3
					"   un.nome, \n"
					+
					// 4
					"   sc.codigo, \n"
					+
					// 5
					"   sc.descricao, \n"
					+
					// 6
					"   loc.id, \n"
					+
					// 7
					"   loc.descricao, \n"
					+
					// 8
					"   cli.nome, \n"
					+
					// 9
					"   las.descricaoAbreviado, \n"
					+
					// 10
					"   rot.codigo, \n"
					+
					// 11
					"   imo.numeroSequencialRota, \n"
					+
					// 12
					"   imo.id, \n"
					+
					// 13
					"   les.id, \n"
					+
					// 14
					"   les.descricaoAbreviado, \n"
					+
					// 15
					"   qua.numeroQuadra, \n"
					+
					// 16
					"   c.referencia as referenciaMinima, \n"
					+
					// 17
					"  sum( ( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) )) as valor, \n"
					+
					// 18
					"   imo.lote, \n" +
					// 19
					"   imo.subLote, \n" +
					// 20
					"   c.dataVencimentoConta, \n" +
					// 21
					"   cli.cpf, \n" +
					// 22
					"   cli.cnpj \n" +

					" from \n" + "   Conta c, ClienteImovel ci  " + categoria
					+ " \n" + "   inner join c.imovel imo \n"
					+ "   inner join imo.localidade loc \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join loc.unidadeNegocio un \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join qua.rota rot \n"
					+ "   inner join ci.cliente cli \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)|| !Util.isVazioOrNulo(colecaoTiposCliente)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}
			
			consulta += "where \n";
			
			if(grupoCobranca != null){
				consulta += "rot.cobrancaGrupo.id = :grupoCobranca and \n";
				parameters.put("grupoCobranca", grupoCobranca);
			}

			consulta += "   imo.id = ci.imovel.id and \n"
					+ "   ci.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO
					+ " and \n"
					+ "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (gerencia != null) {
				consulta += " and gr.id = :gerenciaRegional";
				parameters.put("gerenciaRegional", gerencia);
			}

			if (unidadeNegocio != null) {
				consulta += " and un.id = :unidadeNegocio";
				parameters.put("unidadeNegocio", unidadeNegocio);
			}

			if (localidadeInicial != null) {
				consulta += " and loc.id between :localidadeInicial and :localidadeFinal";
				parameters.put("localidadeInicial", localidadeInicial);
				parameters.put("localidadeFinal", localidadeFinal);
			}

			if (setorComercialInicial != null) {
				consulta += " and sc.codigo between :setorComercialInicial and :setorComercialFinal ";

				parameters.put("setorComercialInicial", setorComercialInicial);
				parameters.put("setorComercialFinal", setorComercialFinal);
			}
			
			if (quadraInicial != null) {
				consulta += " and qua.numeroQuadra between :quadraInicial and :quadraFinal ";

				parameters.put("quadraInicial", quadraInicial);
				parameters.put("quadraFinal", quadraFinal);
			}

			if (rotaInicial != null) {
				consulta += "  and rot.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", rotaInicial);
				parameters.put("rotaFinal", rotaFinal);
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imo.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", sequencialRotaInicial);
				parameters.put("sequencialRotaFinal", sequencialRotaFinal);
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder in(:esferaPoder) ";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) ";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) ";
				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			if(colecaoTiposCliente !=null && colecaoTiposCliente.size() >0){
				if(tipoRelacao == null){
					consulta += "  and ci.clienteRelacaoTipo.id = 2 and ci.dataFimRelacao = null ";
				}
				consulta += " and cltp.id in (:colecaoClienteTipo) ";
				parameters.put("colecaoClienteTipo", colecaoTiposCliente);
			}
				
			
			//Consulta com Hidrômetro
			if(hidrometro != null && !hidrometro.equals("0")){
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca)";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			/*
			 * -Erivan Sousa-
			 * -Caso o usuário tenha informado o mes/ano de referencia inicial e final 
			 * a pesquisa só traz resultados entre as duas referencias;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano inicial, a pesquisa só 
			 * traz resultador com referencia maior que a informada;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano final, a pesquisa só 
			 * traz resultados com referencia menor que a informada.
			 */
			if (referenciaImoveisFaturasAtrasoInicial != null && referenciaImoveisFaturasAtrasoFinal != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
				
			}else if(referenciaImoveisFaturasAtrasoInicial != null){
				consulta += " and (c.referencia >= :referenciaImoveisFaturasAtrasoInicial) ";
				parameters.put("referenciaImoveisFaturasAtrasoInicial",
					referenciaImoveisFaturasAtrasoInicial);
				
			}else if(referenciaImoveisFaturasAtrasoFinal != null){
				consulta += " and (c.referencia <= :referenciaImoveisFaturasAtrasoFinal) ";
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
					referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by gr.id,gr.nome,un.id,un.nome,sc.codigo,sc.descricao,loc.id,loc.descricao,cli.nome,las.descricaoAbreviado,"
					+ " rot.codigo,imo.numeroSequencialRota,imo.id,les.id,les.descricaoAbreviado,qua.numeroQuadra,c.referencia,imo.lote,"
					+ " imo.subLote, c.dataVencimentoConta, c.dataVencimentoConta, cli.cpf, cli.cnpj ";

			consulta += " order by \n" + "   gr.id, \n" + "   un.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   rot.codigo, \n" + "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/*
	 * [CRC] - 1672 - Melhorar performance das consultas do relatorio de imóveis
	 * com faturas em atraso.
	 * 
	 * O filtro da qtd de faturas em atraso e valor das Faturas em Atraso não
	 * são usados nas querys pois para que ela trouxesse o valor correto seria
	 * necessário um subselect, o que tornaria a consulta mais lenta. Assim,
	 * esse filtro é usado no momento de preparar os relatoriosBeans.
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();

		try {

			Integer clienteSuperior = filtro.getClienteSuperior();
			Integer cliente = filtro.getCliente();
			Integer tipoRelacao = filtro.getTipoRelacao();
			Integer responsavel = filtro.getResponsavel();

			Collection<Integer> colecaoSituacaoLigacaoAgua = filtro
					.getSituacaoLigacaoAgua();
			Collection<Integer> colecaoEsferasPoder = filtro.getEsferaPoder();
			Collection<Integer> colecaoCategorias = filtro.getCategorias();
			Collection<Integer> colecaoPerfisImovel = filtro.getPerfisImovel();
			Collection<Integer> colecaoTiposCliente = filtro.getTiposCliente();
			
			Integer situacaoCobranca = filtro.getSituacaoCobranca();
			Integer grupoCobranca	 = filtro.getGrupoCobranca();
			
			String hidrometro = filtro.getHidrometro();

			Integer referenciaImoveisFaturasAtrasoInicial = filtro
					.getReferenciaFaturasAtrasoInicial();
			Integer referenciaImoveisFaturasAtrasoFinal = filtro
					.getReferenciaFaturasAtrasoFinal();

			String clienteConta = "";
			if (cliente != null && responsavel != null
					&& !responsavel.equals(1)) {
				clienteConta = " , ClienteConta clienteConta ";
			}

			String categoria = "";
			if (!Util.isVazioOrNulo(colecaoCategorias)) {
				categoria = " , VwImovelPrincipalCategoria vwPrincCatg ";
			}

			String consulta = "";

			Map<String, Object> parameters = new HashMap<String, Object>();

			consulta =

			" select \n"
					+
					// 1
					"   cli.id, \n"
					+
					// 2
					"   cli.nome, \n"
					+
					// 3
					"   gr.id, \n"
					+
					// 4
					"   loc.id, \n"
					+
					// 5
					"   sc.codigo, \n"
					+
					// 6
					"   qua.numeroQuadra, \n"
					+
					// 7
					"   rot.codigo, \n"
					+
					// 8
					"   imo.numeroSequencialRota, \n"
					+
					// 9
					"   imo.id, \n"
					+
					// 10
					"   las.descricaoAbreviado, \n"
					+
					// 11
					"   les.descricaoAbreviado, \n"
					+
					// 12
					"   c.id, \n"
					+
					// 13
					"   c.referencia , \n"
					+
					// 14
					"   c.dataVencimentoConta , \n"
					+
					// 15
					"   c.indicadorCobrancaMulta , \n"
					+
					// 16
					"   sum( coalesce( c.valorAgua, 0 ) + coalesce( c.valorEsgoto, 0 ) + coalesce( c.debitos, 0 ) - coalesce( c.valorCreditos, 0 ) ) as totalSemEncargos \n"
					+

					" from Cliente cli " + categoria + clienteConta + " \n"
					+ "   inner join cli.clienteImoveis ci \n"
					+ "   inner join ci.imovel imo \n"
					+ "   inner join imo.ligacaoAgua la \n"
					+ "   inner join imo.ligacaoAguaSituacao las \n"
					+ "   inner join imo.setorComercial sc \n"
					+ "   inner join imo.ligacaoEsgotoSituacao les \n"
					+ "   inner join imo.quadra qua \n "
					+ "   inner join imo.localidade loc \n"
					+ "   inner join qua.rota rot \n"
					+ "   inner join loc.gerenciaRegional gr \n"
					+ "   inner join imo.contas c \n";

			if (!Util.isVazioOrNulo(colecaoEsferasPoder) || !Util.isVazioOrNulo(colecaoTiposCliente)) {
				consulta += "   inner join cli.clienteTipo cltp \n ";
			}
			
			consulta += "where \n";
			
			if(grupoCobranca != null){
				consulta += "rot.cobrancaGrupo.id = :grupoCobranca and \n";
				parameters.put("grupoCobranca", grupoCobranca);
			}

			consulta += "   ci.dataFimRelacao is null and \n"
					+ "   c.debitoCreditoSituacaoAtual in ( 0,1,2 ) and \n"
					+ "   not exists( select pgto.id from Pagamento pgto where pgto.contaGeral.id = c.id ) \n";

			if (!clienteConta.trim().equals("")) {
				consulta += " and clienteConta.conta.id = c.id ";
			}

			if (clienteSuperior != null) {
				consulta += " and ci.clienteRelacaoTipo = "
						+ ClienteRelacaoTipo.RESPONSAVEL;
				consulta += " and ( cli.id = :clienteSuperior or cli.cliente.id = :clienteSuperior2) \n";

				parameters.put("clienteSuperior", clienteSuperior);
				parameters.put("clienteSuperior2", clienteSuperior);
			}

			if (cliente != null && responsavel != null) {
				if (responsavel.equals(0)) {
					consulta += " and clienteConta.cliente.id = cli.id \n";
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and clienteConta.clienteRelacaoTipo.id = :tipoRelacao \n";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(1)) {
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ci.clienteRelacaoTipo = :tipoRelacao \n";
						parameters.put("tipoRelacao", tipoRelacao);
					}

				} else if (responsavel.equals(2)) {
					consulta += " and cli.id = :cliente \n";
					parameters.put("cliente", cliente);

					if (tipoRelacao != null) {
						consulta += " and ( \n";
						consulta += " 	(clienteConta.cliente.id = cli.id  ";
						consulta += " 		and clienteConta.clienteRelacaoTipo.id = :tipoRelacao1 ) \n";
						consulta += " 	or  ";
						consulta += " 	( ci.clienteRelacaoTipo = :tipoRelacao2) ";
						consulta += " )";

						parameters.put("tipoRelacao1", tipoRelacao);
						parameters.put("tipoRelacao2", tipoRelacao);
					}
				}
			}

			if (!Util.isVazioOrNulo(colecaoEsferasPoder)) {
				consulta += " and cltp.esferaPoder in(:esferaPoder) \n";
				parameters.put("esferaPoder", colecaoEsferasPoder);
			}

			if (colecaoSituacaoLigacaoAgua != null
					&& colecaoSituacaoLigacaoAgua.size() > 0) {
				consulta += " and las.id in (:situacaoLigacaoAgua) \n";
				parameters.put("situacaoLigacaoAgua",
						colecaoSituacaoLigacaoAgua);
			}

			if (colecaoCategorias != null && colecaoCategorias.size() > 0) {
				consulta += " and vwPrincCatg.comp_id.imovel.id = imo.id ";
				consulta += " and vwPrincCatg.comp_id.categoria.id in ( :categorias ) \n";
				parameters.put("categorias", colecaoCategorias);
			}

			if (colecaoPerfisImovel != null && colecaoPerfisImovel.size() > 0) {
				consulta += " and imo.imovelPerfil.id in ( :perfisImovel ) \n";
				parameters.put("perfisImovel", colecaoPerfisImovel);
			}
			
			if(colecaoTiposCliente !=null && colecaoTiposCliente.size() >0){
				if(tipoRelacao == null){
					consulta += "  and ci.clienteRelacaoTipo.id = 2 and ci.dataFimRelacao = null ";
				}
				consulta += " and cltp.id in (:colecaoClienteTipo) ";
				parameters.put("colecaoClienteTipo", colecaoTiposCliente);
			}
			
			// Consulta com Hidrômetro
			if(hidrometro != null && !hidrometro.equals("0")){
				if(hidrometro.equalsIgnoreCase("S")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is not null \n";
				}
				if(hidrometro.equalsIgnoreCase("N")){
					consulta += " and imo.ligacaoAgua.hidrometroInstalacaoHistorico is null \n";
				}
			}

			if (situacaoCobranca != null) {
				consulta += " and exists( select ics.id from ImovelCobrancaSituacao ics "
						+ " where ics.imovel.id = imo.id  and ics.dataRetiradaCobranca is null "
						+ " and ics.cobrancaSituacao.id = :situacaoCobranca )";
				// consulta += " and imo.cobrancaSituacao.id in
				// (:situacaoCobranca) \n ";
				parameters.put("situacaoCobranca", situacaoCobranca);
			}

			/*
			 * -Erivan Sousa-
			 * -Caso o usuário tenha informado o mes/ano de referencia inicial e final 
			 * a pesquisa só traz resultados entre as duas referencias;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano inicial, a pesquisa só 
			 * traz resultador com referencia maior que a informada;
			 * 
			 * -Caso o usuário só tenha informado o mes/ano final, a pesquisa só 
			 * traz resultados com referencia menor que a informada.
			 */
			if (referenciaImoveisFaturasAtrasoInicial != null && referenciaImoveisFaturasAtrasoFinal != null) {
				consulta += " and (c.referencia between :referenciaImoveisFaturasAtrasoInicial and :referenciaImoveisFaturasAtrasoFinal) ";

				parameters.put("referenciaImoveisFaturasAtrasoInicial",
						referenciaImoveisFaturasAtrasoInicial);
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
						referenciaImoveisFaturasAtrasoFinal);
				
			}else if(referenciaImoveisFaturasAtrasoInicial != null){
				consulta += " and (c.referencia >= :referenciaImoveisFaturasAtrasoInicial) ";
				parameters.put("referenciaImoveisFaturasAtrasoInicial",
					referenciaImoveisFaturasAtrasoInicial);
				
			}else if(referenciaImoveisFaturasAtrasoFinal != null){
				consulta += " and (c.referencia <= :referenciaImoveisFaturasAtrasoFinal) ";
				parameters.put("referenciaImoveisFaturasAtrasoFinal",
					referenciaImoveisFaturasAtrasoFinal);
			}

			consulta += " group by \n" + "   cli.id, \n" + "   cli.nome, \n"
					+ "   gr.id, \n" + "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota, \n" + "   imo.id, \n"
					+ "   las.descricaoAbreviado, \n"
					+ "   les.descricaoAbreviado, \n" + "   imo.subLote, \n"
					+ "	c.id, \n" + "	c.referencia, \n"
					+ " 	c.dataVencimentoConta, \n"
					+ " 	c.indicadorCobrancaMulta \n";

			consulta += " order by \n" + "   cli.id, \n" + "   gr.id, \n"
					+ "   loc.id, \n" + "   sc.codigo, \n"
					+ "   qua.numeroQuadra, \n" + "   rot.codigo, \n"
					+ "   imo.numeroSequencialRota ";

			retorno = criarQueryComParametros(consulta, parameters, session)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Metodo seta os parametros numa determinada consulta e retorna um objeto
	 * Query com o sql/hql já com parametros.
	 * 
	 * @since 02/09/2009
	 * @author Marlon Patrick
	 */
	private Query criarQueryComParametros(String consulta,
			Map<String, Object> parameters, Session session) {

		Query query = session.createQuery(consulta);

		Set<String> set = parameters.keySet();
		Iterator<String> iterMap = set.iterator();
		while (iterMap.hasNext()) {
			String key = iterMap.next();
			if (parameters.get(key) instanceof Collection) {
				Collection<? extends Object> collection = (ArrayList<? extends Object>) parameters
						.get(key);
				query.setParameterList(key, collection);
			} else {
				query.setParameter(key, parameters.get(key));
			}
		}

		return query;
	}

	/**
	 * 
	 * [UC0535] Manter Feriado
	 * 
	 * @author bruno
	 * @date 12/01/2009
	 * 
	 * @param anoOrigemFeriado
	 */
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais(
			String anoOrigemFeriado) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "from " + "  NacionalFeriado " + "where "
					+ "  to_char( data, 'yyyy' ) = :ano";

			retorno = session.createQuery(consulta).setString("ano",
					anoOrigemFeriado).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * [UC0535] Manter Feriado
	 * 
	 * @author bruno
	 * @date 12/01/2009
	 * 
	 * @param anoOrigemFeriado
	 */
	public Collection<MunicipioFeriado> pesquisarFeriadosMunicipais(
			String anoOrigemFeriado) throws ErroRepositorioException {
		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "from " + "  MunicipioFeriado " + "where "
					+ "  to_char( dataFeriado, 'yyyy' ) = :ano";

			retorno = session.createQuery(consulta).setString("ano",
					anoOrigemFeriado).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;

	}

	/**
	 * 
	 * [UC0535] Manter Feriados
	 * 
	 * @author bruno
	 * @date 13/01/2009
	 * 
	 * @param anoDestino
	 * @throws ErroRepositorioException
	 */
	public void excluirFeriadosNacionais(String anoDestino)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "delete " + "from " + "  NacionalFeriado " + "where "
					+ "  to_char( data, 'yyyy' ) = :ano";

			session.createQuery(consulta).setString("ano", anoDestino)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * [UC0535] Manter Feriados
	 * 
	 * @author bruno
	 * @date 13/01/2009
	 * 
	 * @param anoDestino
	 * @throws ErroRepositorioException
	 */
	public void excluirFeriadosMunicipais(String anoDestino)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "delete " + "from " + "  MunicipioFeriado " + "where "
					+ "  to_char( dataFeriado, 'yyyy' ) = :ano";

			session.createQuery(consulta).setString("ano", anoDestino)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0880] - Gerar Movimento de Extensao de Contas em Cobranca por Empresa
	 * 
	 * @author Rômulo Aurélio
	 * @date 09/02/2009
	 * 
	 * @param idRota
	 * @param anoMesReferencia
	 * @return boolean
	 * @throws ControladorException
	 */

	public Collection pesquisarLocalidades() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection retorno = null;
		try {
			consulta = "select DISTINCT loca.id " + "from Localidade loca ";

			retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarArquivoTextoAtualizacaoCadastro(
			String idEmpresa, String idLocalidade, String idAgenteComercial,
			String idSituacaoTransmissao) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac.id, "// 0
					+ " loc.id,"// 1
					+ " txac.codigoSetorComercial,"// 2
					+ " txac.codigoRota,"// 3
					+ " txac.descricaoArquivo,"// 4
					+ " txac.quantidadeImovel,"// 5
					+ " clie.nome,"// 6
					+ " func.nome,"// 7
					+ " sitTram.descricaoSituacao"// 8
					+ " from ArquivoTextoAtualizacaoCadastral txac "
					+ " inner join txac.leiturista leit"
					+ " left join txac.localidade loc"
					+ " left join txac.situacaoTransmissaoLeitura sitTram"
					+ " left join leit.cliente clie"
					+ " left join leit.funcionario func"
					+ " where leit.empresa.id = " + idEmpresa;

			if (idLocalidade != null && !idLocalidade.equals("")) {
				consulta = consulta + " and txac.localidade.id = "
						+ idLocalidade;
			}

			if (idAgenteComercial != null
					&& !idAgenteComercial.trim().equals(
							"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				consulta = consulta + " and leit.id = " + idAgenteComercial;
			}

			if (idSituacaoTransmissao != null
					&& !idSituacaoTransmissao.equals("")) {
				consulta = consulta + " and sitTram.id = "
						+ idSituacaoTransmissao;
			}

			consulta = consulta + " order by txac.id";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			String idArquivoTxt) throws ErroRepositorioException {

		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"// 2
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.id = " + idArquivoTxt;

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(
					consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 05/03/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt,
			Integer idSituacaoTransmissao) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String consulta = " update ArquivoTextoAtualizacaoCadastral txac"
				+ " set txac.situacaoTransmissaoLeitura.id =:idSituacaoTransmissao,"
				+ " txac.ultimaAlteracao = :dataAtual" + " where txac.id = "
				+ idArquivoTxt;

		try {

			session.createQuery(consulta).setInteger("idSituacaoTransmissao",
					idSituacaoTransmissao)
					.setTimestamp("dataAtual", new Date()).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(
			Integer idSetor,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = "SELECT distinct(imov.imov_id) as idImovel "
					+ "from cadastro.imovel imov "
					+ "inner JOIN cadastro.setor_comercial setor ON setor.stcm_id = imov.stcm_id "
					;

			if (helper.getCliente() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.cliente_imovel clim ON clim.imov_id = imov.imov_id ";
			}

			if (helper.getLocalidadeInicial() != null
					|| helper.getElo() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.localidade local ON local.loca_id = imov.loca_id ";
			}

			if (helper.getRotaInicial() != null || helper.getQuadraInicial() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id "
						+ "INNER JOIN micromedicao.rota rota ON rota.rota_id = qdra.rota_id "
						;
			}

			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.imovel_subcategoria imsb ON imsb.imov_id = imov.imov_id "
						+ "LEFT JOIN cadastro.subcategoria scat ON scat.scat_id = imsb.scat_id ";
			}

			consulta = consulta
					+ "where imov.imov_icexclusao <> 1 AND (imov.siac_id is null OR imov.siac_id = 0) AND ";

			if (idSetor != null) {
				consulta = consulta + "imov.stcm_id =:idSetor AND ";
				parameters.put("idSetor", idSetor);
			}

			if (helper.getMatricula() != null) {

				consulta = consulta + "imov.imov_id = :matricula AND ";
				parameters.put("matricula", helper.getMatricula());
			}

			if (helper.getCliente() != null) {

				consulta = consulta + "clim.clie_id = :cliente AND ";
				parameters.put("cliente", helper.getCliente());
			}

			if (helper.getElo() != null) {

				consulta = consulta + "local.loca_cdelo = :elo AND ";
				parameters.put("elo", helper.getElo());
			}

			if (helper.getLocalidadeInicial() != null) {

				consulta = consulta
						+ " local.loca_id between :localidadeInicial AND :localidadeFinal AND ";

				parameters.put("localidadeInicial", helper
						.getLocalidadeInicial());
				parameters.put("localidadeFinal", helper.getLocalidadeFinal());
			}

			if (helper.getSetorComercialInicial() != null) {

				consulta = consulta
						+ " imov.stcm_id between :setorComercialInicial AND :setorComercialFinal AND ";

				parameters.put("setorComercialInicial", helper
						.getSetorComercialInicial());
				parameters.put("setorComercialFinal", helper
						.getSetorComercialFinal());

			}
			
			if (helper.getCodigoSetorComercialInicial() != null) {

				consulta = consulta
						+ " setor.stcm_cdsetorcomercial between :codigoSetorComercialInicial AND :codigoSetorComercialFinal AND ";

				parameters.put("codigoSetorComercialInicial", helper.getCodigoSetorComercialInicial());
				parameters.put("codigoSetorComercialFinal", helper.getCodigoSetorComercialFinal());
			}
			
			if (helper.getQuadraInicial() != null) {

				consulta = consulta
						+ " qdra.qdra_nnquadra between :numeroQuadraInicial AND :numeroQuadraFinal AND ";

				parameters.put("numeroQuadraInicial", helper.getQuadraInicial());
				parameters.put("numeroQuadraFinal", helper.getQuadraFinal());
			}

			if (helper.getIdQuadraInicial() != null) {

				consulta = consulta
						+ " imov.qdra_id between :quadraInicial AND :quadraFinal AND ";

				parameters.put("quadraInicial", helper.getIdQuadraInicial());
				parameters.put("quadraFinal", helper.getIdQuadraFinal());
			}

			if (helper.getRotaInicial() != null) {

				consulta = consulta
						+ " rota.rota_cdrota between :rotaInicial AND :rotaFinal AND ";

				parameters.put("rotaInicial", helper.getRotaInicial());
				parameters.put("rotaFinal", helper.getRotaFinal());
			}

			if (helper.getRotaSequenciaInicial() != null) {

				consulta = consulta
						+ " imov.imov_nnsequencialrota between :rotaSequenciaInicial AND :rotaSequenciaFinal AND ";

				parameters.put("rotaSequenciaInicial", helper
						.getRotaSequenciaInicial());
				parameters.put("rotaSequenciaFinal", helper
						.getRotaSequenciaFinal());
			}

			if (helper.getPerfilImovel() != null) {

				consulta = consulta + "imov.iper_id = :perfilImovel AND ";
				parameters.put("perfilImovel", helper.getPerfilImovel());
			}

			if (helper.getCategoria() != null) {

				consulta = consulta + "scat.catg_id = :categoria AND ";
				parameters.put("categoria", helper.getCategoria());
			}

			if (helper.getSubCategoria() != null) {

				consulta = consulta + "scat.scat_id = :subCategoria AND ";
				parameters.put("subCategoria", helper.getSubCategoria());
			}

			if (helper.getIdSituacaoLigacaoAgua() != null) {
				consulta = consulta
						+ "imov.last_id = :idSituacaoLigacaoAgua AND ";
				parameters.put("idSituacaoLigacaoAgua", helper
						.getIdSituacaoLigacaoAgua());
			}

			consulta = Util.removerUltimosCaracteres(consulta, 4);

			query = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				query.setParameter(key, parameters.get(key));
			}

			retorno = (Collection<Integer>) query.list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Ana Maria
	 * @date 26/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelDebitoAtualizacaoCadastral(
			Collection colecaoIdsImovel) throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT	distinct(debitos.idImovel) as idImovel"
					+ " FROM (SELECT imov.imov_id idImovel, 'CONTA' as tipoDebito, 	sum(CASE WHEN pagtoConta.pgmt_id is null and conta.cnta_id is not null THEN conta.cnta_vlagua +  "
					+ "       conta.cnta_vlesgoto + conta.cnta_vldebitos - conta.cnta_vlcreditos -coalesce(conta.cnta_vlimpostos, 0 ) ELSE 0.00 END) valorDebitos, "
					+ "       count(CASE WHEN pagtoConta.pgmt_id is null and conta.cnta_id is not null THEN conta.cnta_id END) qtdeDebitos 	 "
					+ " 	   FROM cadastro.imovel imov  "
					+ " 	   LEFT OUTER JOIN faturamento.conta conta on conta.imov_id = imov.imov_id and conta.dcst_idatual in (0, 1, 2)  "
					+ " 	   LEFT OUTER JOIN arrecadacao.pagamento pagtoConta on conta.cnta_id = pagtoConta.cnta_id  "
					+ "	   WHERE cnta_dtvencimentoconta < :dataAtual "
					+ "       and imov.imov_id in(:colecaoIdsImovel)  "
					+ " 	   GROUP BY distinct(debitos.idimovel), tipoDebito  "
					+ "       UNION  "
					+ " 	   SELECT imov.imov_id as idImovel, 'GUIA' as tipoDebito, sum(CASE WHEN pagtoGuia.pgmt_id is null and gpag.gpag_id is not null THEN gpag.gpag_vldebito ELSE 0.00 END) as valorDebitos, "
					+ "       count(CASE WHEN pagtoGuia.pgmt_id is null and gpag.gpag_id is not null THEN gpag.gpag_id END) as qtdeDebitos  "
					+ " 	   FROM cadastro.imovel imov  "
					+ " 	   LEFT OUTER JOIN faturamento.guia_pagamento gpag on gpag.imov_id = imov.imov_id  "
					+ " 	   LEFT OUTER JOIN arrecadacao.pagamento pagtoGuia on gpag.gpag_id = pagtoGuia.gpag_id "
					+ " 	   WHERE gpag_dtvencimento < :dataAtual  "
					+ " 	   and imov.imov_id in (:colecaoIdsImovel)  "
					+ " 	   GROUP BY 	(imov.imov_id), tipoDebito) debitos "
					+ " INNER JOIN cadastro.imovel imov on debitos.idImovel = imov.imov_id  "
					+ " WHERE debitos.valorDebitos is null or debitos.valorDebitos > 0 "
					+ " order by 1 ";

			retorno = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setParameterList("colecaoIdsImovel",
					colecaoIdsImovel).setTimestamp("dataAtual", new Date())
					.list();


		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0893] - Inserir Unidade de Negocio
	 * 
	 * Verificar se o Cliente Selecionado existe na tabela Funcionario
	 * 
	 * @author Vinicius Medeiros
	 * @date 08/04/2009
	 * 
	 * @param idCliente
	 * @throws ControladorException
	 */

	public Integer verificarClienteSelecionadoFuncionario(Integer idCliente)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Integer retorno = null;

		try {
			consulta = "select func.id "
					+ "from Funcionario func, Cliente clie "
					+ "where func.numeroCpf = clie.cpf "
					+ " and clie.id = :idCliente";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idCliente", idCliente).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa a(s) quadra face associada a quadra
	 * 
	 * Autor: Arthur Carvalho
	 * 
	 * Data: 28/04/2009
	 */
	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(
			Integer idQuadra) throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {

			consulta = " SELECT qFace.numeroQuadraFace, bacia.descricao, qFace.indicadorRedeEsgoto, " // 0,
																										// 1, 2
					+ " qFace.indicadorRedeAgua, distritoOperacional.descricao, " // 3, 4
					+ " sistemaEsgoto.descricao " // 5
					+ " FROM QuadraFace qFace "
					+ " left join qFace.bacia bacia "
					+ " left join bacia.sistemaEsgoto sistemaEsgoto "
					+ " left join qFace.distritoOperacional distritoOperacional "
					+ " WHERE " + " qFace.quadra.id = :idQuadra";

			retorno = (Collection<Object[]>) session.createQuery(consulta)
					.setInteger("idQuadra", idQuadra).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0830] Gerar Tabelas para Atualização Cadastral via celular
	 * 
	 * @author Ana Maria
	 * @date 22/06/2009
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Integer> retorno = null;

		Query query = null;

		try {

			consulta = "SELECT distinct (imov.stcm_id) as idSetor "
				+ "from cadastro.imovel imov "
				+ "INNER JOIN cadastro.setor_comercial setor ON setor.stcm_id = imov.stcm_id ";

			if (helper.getCliente() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.cliente_imovel clim ON clim.imov_id = imov.imov_id ";
			}

			if (helper.getLocalidadeInicial() != null || helper.getElo() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.localidade local ON local.loca_id = imov.loca_id ";
			}

			if (helper.getRotaInicial() != null) {
				consulta = consulta
						+ "INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id "
						+ "INNER JOIN micromedicao.rota rota ON rota.rota_id = qdra.rota_id ";
			}

			if (helper.getCategoria() != null
					|| helper.getSubCategoria() != null) {
				consulta = consulta
						+ "LEFT JOIN cadastro.imovel_subcategoria imsb ON imsb.imov_id = imov.imov_id "
						+ "LEFT JOIN cadastro.subcategoria scat ON scat.scat_id = imsb.scat_id ";
			}

			consulta = consulta
					+ "where imov.imov_icexclusao <> 1 AND (imov.siac_id is null OR imov.siac_id = 0) ";

			if (helper.getMatricula() != null) {

				consulta = consulta + " AND imov.imov_id = "
						+ helper.getMatricula();
			}

			if (helper.getCliente() != null) {

				consulta = consulta + " AND clim.clie_id = "
						+ helper.getCliente();
			}

			if (helper.getElo() != null) {

				consulta = consulta + " AND local.loca_cdelo = "
						+ helper.getElo();
			}

			if (helper.getLocalidadeInicial() != null) {

				consulta = consulta + " AND local.loca_id between "
						+ helper.getLocalidadeInicial() + " AND "
						+ helper.getLocalidadeFinal();

			}

			if (helper.getCodigoSetorComercialInicial() != null) {

				consulta = consulta + " AND setor.stcm_cdsetorcomercial between "
						+ helper.getCodigoSetorComercialInicial() + " AND "
						+ helper.getCodigoSetorComercialFinal();

			}
			
			if (helper.getSetorComercialInicial() != null) {

				consulta = consulta + " AND imov.stcm_id between "
						+ helper.getSetorComercialInicial() + " AND "
						+ helper.getSetorComercialFinal();

			}


			if (helper.getIdQuadraInicial() != null) {

				consulta = consulta + " AND imov.qdra_id between "
						+ helper.getIdQuadraInicial() + " AND "
						+ helper.getIdQuadraFinal();

			}

			if (helper.getRotaInicial() != null) {

				consulta = consulta + " AND rota.rota_cdrota between "
						+ helper.getRotaInicial() + " AND "
						+ helper.getRotaFinal();
			}

			if (helper.getRotaSequenciaInicial() != null) {

				consulta = consulta
						+ " AND imov.imov_nnsequencialrota between "
						+ helper.getRotaSequenciaInicial() + " AND "
						+ helper.getRotaSequenciaFinal();

			}

			if (helper.getPerfilImovel() != null) {

				consulta = consulta + " AND imov.iper_id = "
						+ helper.getPerfilImovel();
			}

			if (helper.getCategoria() != null) {

				consulta = consulta + " AND scat.catg_id = "
						+ helper.getCategoria();
			}

			if (helper.getSubCategoria() != null) {

				consulta = consulta + " AND scat.scat_id = "
						+ helper.getSubCategoria();
			}

			if (helper.getIdSituacaoLigacaoAgua() != null) {

				consulta = consulta + " AND imov.last_id = "
						+ helper.getIdSituacaoLigacaoAgua();
			}

			query = session.createSQLQuery(consulta).addScalar("idSetor",
					Hibernate.INTEGER);

			if (helper.getQuantidadeMaxima() == null) {
				retorno = (Collection<Integer>) query.list();
			} else {
				retorno = (Collection<Integer>) query.setMaxResults(
						helper.getQuantidadeMaxima()).list();
			}

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema obtém os dados do contrato com a empresa (a partir da tabela
	 * EMPRESA_CONTRATO_CADASTRO com EMPR_ID=Id da empresa retornado e
	 * ECCD_DTFINALCONTRATO maior ou igual à data corrente e
	 * ECCD_DTCANCELCONTRATO com o valor nulo)
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public EmpresaContratoCadastro pesquisarEmpresaContratoCadastro(
			Integer idEmpresa) throws ErroRepositorioException {

		EmpresaContratoCadastro retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select ecc " + "from EmpresaContratoCadastro ecc "
					+ "where ecc.empresa = :idEmpresa "
					+ "and ecc.dataFinalContrato >= :dataAtual "
					+ "and ecc.dataCancelamentoContrato is null "
					+ "order by ecc.dataInicioContrato  desc ";

			retorno = (EmpresaContratoCadastro) session.createQuery(consulta)
					.setInteger("idEmpresa", idEmpresa).setDate("dataAtual",
							new Date()).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema seleciona as operações efetuadas pela empresa no período
	 * informado e com imóvel associado [SB0001 - Selecionar Operações Efetuadas
	 * com Imóvel Associado].
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasComImovelAssociado(
			Date dataInicio, Date dataFim, Integer idEmpresa)
			throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select "
					+ "opef.opef_cnargumento as argumento, "
					+ "tbla.tbla_id2 as id2, "
					+ "atrb.atrb_id as idAtributo, "
					+ "ecca.ecca_vlatualizacao as valorAtualizacao, "
					+ "atrb.atgr_id as idGrupo, "
					+ "atrb.atrb_nnordememissao as ordemEmissao, "
					+ "count(opef.opef_cnargumento) as qtdade "
					+ "from seguranca.OPERACAO_EFETUADA opef "
					+ "  inner join seguranca.OPERACAO oper on oper.oper_id=opef.oper_id "
					+ "  inner join seguranca.FUNCIONALIDADE_ATRIBUTO fnat on fnat.fncd_id=oper.fncd_id "
					+ "  inner join seguranca.ATRIBUTO atrb on fnat.atrb_id=atrb.atrb_id "
					+ "  inner join seguranca.ATRIBUTO_GRUPO atgr on atgr.atgr_id=atrb.atgr_id and atgr.atgr_icimovel=1 "
					+ "  inner join seguranca.USUARIO_ALTERACAO usat on usat.tref_id=opef.opef_id and usat.usac_id=1 "
					+ "  inner join seguranca.usuario usur on usur.usur_id=usat.usis_id and usur.empr_id= :idEmpresa "
					+ "  inner join seguranca.TABELA_LINHA_ALTERACAO tbla on tbla.tref_id=opef.opef_id "
					+ "  inner join seguranca.tab_linha_col_alteracao tlco on tlco.tbla_id=tbla.tbla_id "
					+ "  inner join seguranca.TABELA_COLUNA_ATRIBUTO tcat on tcat.tbco_id=tlco.tbco_id and tcat.atrb_id=fnat.atrb_id "
					+ "  inner join cadastro.empr_contrato_cad_atrib ecca on ecca.atrb_id = atrb.atrb_id "
					+ "where   "
					+ "   to_date(to_char(OPEF_TMULTIMAALTERACAO,'YYYY/MM/DD'),'YYYY/MM/DD') between :dataInicio and :dataFim "
					+ "group by  opef.opef_cnargumento, tcat.atrb_id, tbla.tbla_id2 , atrb.atrb_id , ecca.ecca_vlatualizacao , atrb.atgr_id , atrb.atrb_nnordememissao "
					+ "order by " + "   opef.OPEF_CNARGUMENTO, tcat.ATRB_ID ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("argumento", Hibernate.INTEGER)
					.addScalar("id2", Hibernate.INTEGER)
					.addScalar("idAtributo", Hibernate.INTEGER)
					.addScalar("valorAtualizacao", Hibernate.BIG_DECIMAL)
					.addScalar("idGrupo", Hibernate.INTEGER)
					.addScalar("ordemEmissao", Hibernate.SHORT)
					.addScalar("qtdade", Hibernate.INTEGER)
					.setDate("dataInicio", Util.formatarDataInicial(dataInicio))
					.setDate("dataFim", Util.formatarDataFinal(dataFim))
					.setInteger("idEmpresa", idEmpresa).list();

			if (retornoConsulta.size() > 0) {
				Iterator operacoesEfetuadasIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				AtributosBoletimChaveHelper atributosBoletimChaveHelper = null;
				OperacoesEfetuadasHelper helper = null;
				while (operacoesEfetuadasIter.hasNext()) {

					Object[] element = (Object[]) operacoesEfetuadasIter.next();

					helper = new OperacoesEfetuadasHelper();

					helper.setArgumento((Integer) element[0]);
					if (element[1] != null) {
						helper.setId2TabelaLinhaAlteracao((Integer) element[1]);
					} else {
						helper.setId2TabelaLinhaAlteracao(0);
					}

					helper.setValorAtualizacaoAtributo((BigDecimal) element[3]);

					atributosBoletimChaveHelper = new AtributosBoletimChaveHelper(
							(Integer) element[2], (Integer) element[4],
							(Short) element[5]);

					helper
							.setAtributosBoletimChaveHelper(atributosBoletimChaveHelper);

					retorno.add(helper);
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
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema seleciona as operações efetuadas pela empresa no período
	 * informado e sem imóvel associado [SB0002] - Selecionar Operações
	 * Efetuadas sem Imóvel Associado
	 * 
	 * @author Vivianne Sousa
	 * @date 25/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarOperacoesEfetuadasSemImovelAssociado(
			Date dataInicio, Date dataFim, Integer idEmpresa)
			throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select "
					+ "opef.opef_cnargumento as argumento, "
					+ "tbla.tbla_id2 as id2, "
					+ "atrb.atrb_id as idAtributo, "
					+ "ecca.ecca_vlatualizacao as valorAtualizacao, "
					+ "atrb.atgr_id as idGrupo, "
					+ "atrb.atrb_nnordememissao as ordemEmissao, "
					+ "count(opef.opef_cnargumento) as qtdade "
					+ "from seguranca.OPERACAO_EFETUADA opef "
					+ "  inner join seguranca.OPERACAO oper on oper.oper_id=opef.oper_id "
					+ "  inner join seguranca.FUNCIONALIDADE_ATRIBUTO fnat on fnat.fncd_id=oper.fncd_id "
					+ "  inner join seguranca.ATRIBUTO atrb on fnat.atrb_id=atrb.atrb_id "
					+ "  inner join seguranca.ATRIBUTO_GRUPO atgr on atgr.atgr_id=atrb.atgr_id and atgr.atgr_id=opef.atgr_id "
					+
					// "on atgr.atgr_id=atrb.atgr_id and atgr.atgr_icimovel=2 "
					// +
					"  inner join seguranca.USUARIO_ALTERACAO usat on usat.tref_id=opef.opef_id and usat.usac_id=1 "
					+ "  inner join seguranca.usuario usur on usur.usur_id=usat.usis_id and usur.empr_id= :idEmpresa "
					+ "  inner join seguranca.TABELA_LINHA_ALTERACAO tbla on tbla.tref_id=opef.opef_id "
					+ "  inner join seguranca.tab_linha_col_alteracao tlco on tlco.tbla_id=tbla.tbla_id "
					+ "  inner join seguranca.TABELA_COLUNA_ATRIBUTO tcat on tcat.tbco_id=tlco.tbco_id and tcat.atrb_id=fnat.atrb_id "
					+ " inner join cadastro.empr_contrato_cad_atrib ecca on ecca.atrb_id = atrb.atrb_id "
					+ "where "
					+ "   to_date(to_char(OPEF_TMULTIMAALTERACAO,'YYYY/MM/DD'),'YYYY/MM/DD') between :dataInicio and :dataFim "
					+ "group by  opef.opef_cnargumento, tcat.atrb_id, tbla.tbla_id2 , atrb.atrb_id , ecca.ecca_vlatualizacao , atrb.atgr_id , atrb.atrb_nnordememissao "
					+ "order by " + "   tbla.TBLA_ID2, tcat.ATRB_ID ";

			retornoConsulta = session
					.createSQLQuery(consulta)
					.addScalar("argumento", Hibernate.INTEGER)
					.addScalar("id2", Hibernate.INTEGER)
					.addScalar("idAtributo", Hibernate.INTEGER)
					.addScalar("valorAtualizacao", Hibernate.BIG_DECIMAL)
					.addScalar("idGrupo", Hibernate.INTEGER)
					.addScalar("ordemEmissao", Hibernate.SHORT)
					.addScalar("qtdade", Hibernate.INTEGER)
					.setDate("dataInicio", Util.formatarDataInicial(dataInicio))
					.setDate("dataFim", Util.formatarDataFinal(dataFim))
					.setInteger("idEmpresa", idEmpresa).list();

			if (retornoConsulta.size() > 0) {
				Iterator operacoesEfetuadasIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				AtributosBoletimChaveHelper atributosBoletimChaveHelper = null;
				OperacoesEfetuadasHelper helper = null;
				while (operacoesEfetuadasIter.hasNext()) {

					Object[] element = (Object[]) operacoesEfetuadasIter.next();

					helper = new OperacoesEfetuadasHelper();

					// 9.2 9.2. Neste caso, o Conteúdo do Argumento deve
					// corresponder ao conteúdo do segundo argumento (TBLA_ID2).
					helper.setArgumento((Integer) element[1]);
					// helper.setArgumento((Integer)element[0]);

					if (element[1] != null) {
						helper.setId2TabelaLinhaAlteracao((Integer) element[1]);
					} else {
						helper.setId2TabelaLinhaAlteracao(0);
					}

					helper.setValorAtualizacaoAtributo((BigDecimal) element[3]);

					atributosBoletimChaveHelper = new AtributosBoletimChaveHelper(
							(Integer) element[2], (Integer) element[4],
							(Short) element[5]);

					helper
							.setAtributosBoletimChaveHelper(atributosBoletimChaveHelper);

					retorno.add(helper);
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
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * O sistema seleciona os atributos que compõem o boletim (a partir da
	 * tabela ATRIBUTO ordenando pelo grupo do atributo (ATGR_ID) e pela ordem
	 * de emissão (ATRB_NNORDEMEMISSAO)).
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAtributosBoletim()
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select atributo "
					+ "from Atributo atributo "
					+ "inner join fetch atributo.atributoGrupo atributoGrupo "
					+ "order by atributoGrupo.id, atributo.numeroOrdemEmissao  ";

			retorno = (Collection) session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
	 * 
	 * Valor de Atualização do Atributo (ECCA_VLATUALIZACAO da tabela
	 * EMPRESA_CONTRATO_CADASTRO_ATRIBUTO com ATRB_ID=ATRB_ID da tabela ATRIBUTO
	 * e ECCD_ID=ECCD_ID da tabela EMPRESA_CONTRATO_CADASTRO);
	 * 
	 * @author Vivianne Sousa
	 * @date 26/06/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorAtualizacaoAtributo(Integer idAtributo,
			Integer idEmpresaContratoCadastro) throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select ecca.valorAtualizacaoAtributo "
					+ "from EmpresaContratoCadastroAtributo ecca "
					+ "where ecca.atributo.id = :idAtributo "
					+ "and ecca.empresaContratoCadastro.id = :idEmpresaContratoCadastro ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idAtributo", idAtributo).setInteger(
					"idEmpresaContratoCadastro", idEmpresaContratoCadastro)
					.uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0925] Emitir Boletos
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosBoleto(int quantidadeInicio, Integer grupo,
			String nomeEmpresa) throws ErroRepositorioException {

		Collection retorno = null;
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select distinct "
					+ "	im.loca_id AS idLocalidade,  "
					+ "	sc.stcm_cdsetorcomercial AS codigoSetor,  "
					+ "	qd.qdra_nnquadra AS numeroQuadra, "
					+ "	im.imov_nnlote AS lote,	 "
					+ "	im.imov_nnsublote AS sublote, "
					+ "	im.imov_id AS matricula,  "
					+ "	clie_nmcliente AS nomeCliente, "
					+ "	rota.ftgr_id AS grupo, "
					+ "   rota.empr_id AS empresa, "
					+ "   rota.rota_cdrota AS codigoRota, "
					+ "   im.imov_nnsequencialrota as sequencialRota "
					+

					"from   "
					+ "	cadastro.imovel		 		            im "
					+ "	INNER JOIN cadastro.cliente_imovel 	    clieImov   ON im.imov_id = clieImov.imov_id "
					+ "	INNER JOIN cadastro.cliente 		    cliente	   ON clieImov.clie_id = cliente.clie_id "
					+ "	INNER JOIN cadastro.setor_comercial     sc		   ON im.stcm_id = sc.stcm_id "
					+ "	INNER JOIN cadastro.quadra 		        qd		   ON im.qdra_id = qd.qdra_id "
					+ "	INNER JOIN micromedicao.rota		    rota	   ON qd.rota_id = rota.rota_id "
					+ "	INNER JOIN cadastro.imovel_subcategoria isc1 	   ON im.imov_id = isc1.imov_id "
					+ "	INNER JOIN cadastro.subcategoria        scat1 	   ON scat1.scat_id = isc1.scat_id "
					+

					"where  "
					+ "	(im.last_id in (:ligadoAgua, :ligadoAnaliseAgua ) or im.lest_id = :ligadoEsgoto)  ";

			// Caso seja CAERN, considera todos os municipios
			// Alterado por Rômulo Aurélio / Analista: Rafael Pinto
			// Data: 25/11/2009
			if (!nomeEmpresa.equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {

				consulta = consulta
						+ "   and sc.muni_id in (005, 090, 105, 290, 345, 520, 680, 720, 760, 775, 790, 940, 1070, 1130, 1140, 1160, 1640, 960) ";
			}

			// Fim da alteracao
			consulta = consulta + "   and rota.ftgr_id = :grupo "
					+ "	and ClieImov.crtp_id = :clienteRelacaoTipo "
					+ "	and ClieImov.clim_dtrelacaofim is null "
					+ "	and scat1.catg_id <> :categoria  ";

			// Caso seja CAERN, Ordena também por codigo da rota e Sequencial da
			// Rota
			// Alterado por Rômulo Aurélio / Analista: Rafael Pinto
			// Data: 25/11/2009
			if (!nomeEmpresa.equalsIgnoreCase(SistemaParametro.EMPRESA_CAERN)) {
				consulta = consulta
						+ "order by grupo, empresa, idLocalidade, codigoSetor, numeroQuadra, lote, sublote  ";
			} else {
				consulta = consulta + "order by idLocalidade, codigoSetor, "
						+ " codigoRota, sequencialRota, lote, sublote  ";
			}
			retornoConsulta = session.createSQLQuery(consulta).addScalar(
					"idLocalidade", Hibernate.INTEGER).addScalar("codigoSetor",
					Hibernate.INTEGER).addScalar("numeroQuadra",
					Hibernate.INTEGER).addScalar("lote", Hibernate.SHORT)
					.addScalar("sublote", Hibernate.SHORT).addScalar(
							"matricula", Hibernate.INTEGER).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar("grupo",
							Hibernate.INTEGER).addScalar("empresa",
							Hibernate.INTEGER).addScalar("codigoRota",
							Hibernate.SHORT).addScalar("sequencialRota",
							Hibernate.INTEGER).setInteger("ligadoAgua",
							LigacaoAguaSituacao.LIGADO).setInteger(
							"ligadoAnaliseAgua",
							LigacaoAguaSituacao.LIGADO_EM_ANALISE).setInteger(
							"ligadoEsgoto", LigacaoEsgotoSituacao.LIGADO)
					.setInteger("clienteRelacaoTipo",
							ClienteRelacaoTipo.USUARIO).setInteger("categoria",
							Categoria.PUBLICO).setInteger("grupo", grupo).

					setFirstResult(quantidadeInicio).setMaxResults(1000).list();

			if (retornoConsulta.size() > 0) {
				Iterator dadosBoletoIter = retornoConsulta.iterator();
				retorno = new ArrayList();
				DadosBoletoHelper helper = null;
				Imovel imovel = null;

				while (dadosBoletoIter.hasNext()) {

					Object[] element = (Object[]) dadosBoletoIter.next();
					helper = new DadosBoletoHelper();
					imovel = new Imovel();
					Localidade localidade = new Localidade();
					SetorComercial setorComercial = new SetorComercial();
					Quadra quadra = new Quadra();

					if (element[0] != null) {
						localidade.setId((Integer) element[0]);
						imovel.setLocalidade(localidade);
					}

					if (element[1] != null) {
						setorComercial.setCodigo((Integer) element[1]);
						imovel.setSetorComercial(setorComercial);
					}

					if (element[2] != null) {
						quadra.setNumeroQuadra((Integer) element[2]);
						imovel.setQuadra(quadra);
					}

					if (element[3] != null) {
						imovel.setLote((Short) element[3]);
					}

					if (element[4] != null) {
						imovel.setSubLote((Short) element[4]);
					}

					if (element[5] != null) {
						imovel.setId((Integer) element[5]);
					}

					helper.setImovel(imovel);

					if (element[6] != null) {
						helper.setNomeCliente((String) element[6]);
					}

					if (element[7] != null) {
						helper.setIdGrupoFaturamento((Integer) element[7]);
					}

					if (element[8] != null) {
						helper.setIdEmpresa((Integer) element[8]);
					}

					if (element[9] != null) {
						helper.setCodigoRota((Short) element[9]);
					}

					if (element[10] != null) {
						helper.setSequencialRota((Integer) element[10]);
					}

					retorno.add(helper);
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
	 * [UC0925] Emitir Boletos
	 * 
	 * retrona DBTP_VLLIMITE para DBTP_ID = idDebitoTipo
	 * 
	 * @author Vivianne Sousa
	 * @date 09/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorLimiteDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select dbtp.valorLimite " + "from DebitoTipo dbtp "
					+ "where dbtp.id = :idDebitoTipo ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idDebitoTipo", idDebitoTipo).uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0407]-Filtrar Imóveis para Inserir ou Manter Conta [FS0011]-Verificar
	 * a abrangência do código do usuário
	 * 
	 * @author Vivianne Sousa
	 * @date 31/07/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario)
			throws ErroRepositorioException {

		UnidadeNegocio retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select unidadeNegocio " + "FROM Usuario usuario "
					+ "INNER JOIN usuario.unidadeNegocio unidadeNegocio "
					+ "WHERE usuario.id = :idUsuario ";

			retorno = (UnidadeNegocio) session.createQuery(consulta)
					.setInteger("idUsuario", idUsuario).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social CRC - 2113
	 * 
	 * @author Genival Barbosa
	 * @date 15/09/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public List pesquisarImoveisExcluirDaTarifaSocial(Integer idSetor,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		List retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select i.id, " + "i.quantidadeEconomias, "
					+ "ch.consumoMedio " + "FROM ConsumoHistorico ch "
					+ "INNER JOIN ch.imovel i "
					+ "WHERE i.imovelPerfil = 4 and "
					+ "i.indicadorExclusao = 2 and "
					+ "ch.consumoMedio > 19 and " + "i.setorComercial = "
					+ idSetor + " and ch.ligacaoTipo = 1 and "
					+ "ch.referenciaFaturamento = " + anoMesFaturamento
					+ " GROUP BY i.id, i.quantidadeEconomias, ch.consumoMedio ";

			retorno = session.createQuery(consulta).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaDadoEconomia(String idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		// Update na tabela TarifaSocialDadoEconomia
		String consulta = " update TarifaSocialDadoEconomia tsde "
				+ " set tsde.tarifaSocialExclusaoMotivo = 37, "
				+ " tsde.dataExclusao =  " + Util.obterSQLDataAtual() + " , "
				+ " tsde.dataRevisao = null, "
				+ " tsde.tarifaSocialRevisaoMotivo = null, "
				+ " tsde.ultimaAlteracao =  " + Util.obterSQLDataAtual()
				+ " where tsde.imovel = " + idImovel;

		try {

			session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * [UCXXXX]- Excluir Imoveis Da Tarifa Social CRC - 2113
	 * 
	 * @author Genival Barbora
	 * @date 15/09/2009
	 * 
	 * @return void
	 * @throws ErroRepositorioException
	 */
	public void atualizarExcluirDaTarifaSocialTabelaImovel(String idImovel)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		// update na tabela Imovel
		String consulta = " update Imovel imov "
				+ " set imov.imovelPerfil = 5, " + " imov.ultimaAlteracao = "
				+ Util.obterSQLDataAtual() + " where imov.id = " + idImovel;

		try {

			session.createQuery(consulta).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros, Raphael Rossiter
	 * @date 17/12/2007, 11/06/2008
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro,
			Integer anoMesFaturamento) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		Integer localidadeInicial = filtro.getLocalidadeInicial();
		Integer localidadeFinal = filtro.getLocalidadeFinal();

		Integer setorComercialInicial = filtro.getSetorComercialInicial();
		Integer setorComercialFinal = filtro.getSetorComercialFinal();

		Integer rotaInicial = filtro.getRotaInicial();
		Integer rotaFinal = filtro.getRotaFinal();

		Integer sequencialRotaInicial = filtro.getSequencialRotalInicial();
		Integer sequencialRotaFinal = filtro.getSequencialRotalFinal();

		Integer unidadeNegocio = filtro.getUnidadeNegocio();
		Integer gerencia = filtro.getGerenciaRegional();

		Integer consumoMedioAguaInicial = filtro.getConsumoMedioAguaInicial();
		Integer consumoMedioAguaFinal = filtro.getConsumoMedioAguaFinal();

		Integer consumoMedioEsgotoInicial = filtro
				.getConsumoMedioEsgotoInicial();
		Integer consumoMedioEsgotoFinal = filtro.getConsumoMedioEsgotoFinal();

		String consulta = "";

		try {
			consulta = "select count(distinct imov.imov_id) as cont "
					+

					"from cadastro.cliente_imovel clim "
					+

					"inner join cadastro.imovel imov on clim.imov_id=imov.imov_id "
					+ "inner join cadastro.localidade loca on imov.loca_id=loca.loca_id "
					+ "inner join cadastro.gerencia_regional greg on loca.greg_id=greg.greg_id "
					+ "inner join cadastro.unidade_negocio uneg on loca.uneg_id=uneg.uneg_id "
					+ "inner join cadastro.setor_comercial stcm on imov.stcm_id=stcm.stcm_id "
					+ "inner join cadastro.quadra qdra on imov.qdra_id=qdra.qdra_id "
					+ "inner join micromedicao.rota rota on qdra.rota_id=rota.rota_id "
					+ "inner join atendimentopublico.ligacao_agua_situacao last on imov.last_id=last.last_id "
					+ "inner join atendimentopublico.ligacao_esgoto_situacao lest on imov.lest_id=lest.lest_id "
					+ "left outer join cadastro.logradouro_cep lgcp on imov.lgcp_id=lgcp.lgcp_id "
					+ "left outer join cadastro.logradouro logr on lgcp.logr_id=logr.logr_id "
					+ "left outer join cadastro.logradouro_bairro lgbr on imov.lgbr_id=lgbr.lgbr_id "
					+ "left outer join cadastro.bairro bair on lgbr.bair_id=bair.bair_id "
					+ "inner join cadastro.cliente clie on clim.clie_id=clie.clie_id "
					+ "inner join cadastro.cliente_relacao_tipo crtp on clim.crtp_id=crtp.crtp_id "
					+

					// CLIENTE USUÁRIO
					"and (crtp.crtp_id = 2 ) "
					+

					// AGUA
					"left join micromedicao.consumo_historico consumoAgua on imov.imov_id=consumoAgua.imov_id "
					+ "and (consumoAgua.lgti_id = 1 ) and (consumoAgua.cshi_amfaturamento = :anoMesFaturamento ) "
					+

					// ESGOTO
					"left join micromedicao.consumo_historico consumoEsgoto on imov.imov_id=consumoEsgoto.imov_id "
					+ "and (consumoEsgoto.lgti_id = 2 ) and (consumoEsgoto.cshi_amfaturamento = :anoMesFaturamento ) ";

			consulta += "where clim.clim_dtrelacaofim is null ";

			if (unidadeNegocio != null) {
				consulta += " and uneg.uneg_id = " + unidadeNegocio.toString();
			}

			if (gerencia != null) {
				consulta += " and greg.greg_id = " + gerencia.toString();
			}

			if (localidadeInicial != null) {
				consulta += " and loca.loca_id between "
						+ localidadeInicial.toString() + " and "
						+ localidadeFinal.toString();
			}

			if (setorComercialInicial != null) {
				consulta += " and stcm.stcm_cdsetorcomercial between "
						+ setorComercialInicial.toString() + " and "
						+ setorComercialFinal.toString();
			}

			if (rotaInicial != null) {
				consulta += " and rota.rota_cdrota between "
						+ rotaInicial.toString() + " and "
						+ rotaFinal.toString();
			}

			if (sequencialRotaInicial != null) {
				consulta += " and imov.imov_nnsequencialrota between "
						+ sequencialRotaInicial.toString() + " and "
						+ sequencialRotaFinal;
			}

			if (consumoMedioAguaInicial != null) {
				consulta += " and consumoAgua.cshi_nnconsumomedio between "
						+ consumoMedioAguaInicial.toString() + " and "
						+ consumoMedioAguaFinal;
			}

			if (consumoMedioEsgotoInicial != null) {
				consulta += " and consumoEsgoto.cshi_nnconsumomedio between "
						+ consumoMedioEsgotoInicial.toString() + " and "
						+ consumoMedioEsgotoFinal;
			}

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).setInteger("anoMesFaturamento",
					anoMesFaturamento.intValue()).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0727] Gerar Relatório de Imóveis por Consumo Medio
	 * 
	 * @author Bruno Barros, Raphael Rossiter
	 * @date 17/12/2007, 11/06/2008
	 * 
	 * @param FiltrarRelatorioImoveisConsumoMedio
	 * 
	 * @return Collection<FiltrarRelatorioImoveisConsumoMedio[]>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount()
			throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select count(distinct imov_id) as cont " +

			"from cadastro.imovel_atlz_cadastral imovel ";

			consulta += " where  siac_id = " + ConstantesSistema.ZERO;

			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	public Collection<Integer> pesquisarIdsImoveisAtualizacaoCadastral(
			Integer idEmpresaLeiturista)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select idImovel from ImovelAtualizacaoCadastral "

			+ "where idSituacaoAtualizacaoCadastral = "
					+ ConstantesSistema.ZERO

					+ " and idEmpresa = :idEmpresaLeiturista ";

			retorno = session.createQuery(consulta).setInteger(
					"idEmpresaLeiturista", idEmpresaLeiturista.intValue())
					.list();

		} catch (HibernateException e) {

			// levanta a exceção para a próxima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {

			// fecha a sessão

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	/**
	 * Pesquisa as críticas existentes para um determinado arquivo importado.
	 * 
	 * [UC0969] Importar arquivo de atualização cadastral simplificado
	 * 
	 * @author Samuel Valerio
	 * @date 22/10/2009
	 * 
	 * @param idArquivo
	 *            Id do arquivo
	 * @return Coleção de críticas do arquivo
	 */
	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(
			int idArquivo) throws ErroRepositorioException {
		Collection<AtualizacaoCadastralSimplificadoCritica> retorno = new ArrayList<AtualizacaoCadastralSimplificadoCritica>();

		String consulta = "from AtualizacaoCadastralSimplificadoCritica critica"
				+ " join fetch critica.tipo tipo"
				+ " join fetch critica.linhas linha"
				+ " where linha.arquivo.id = :idArquivo"
				+ " order by tipo.descricao, critica.descricao";

		Session session = HibernateUtil.getSession();

		try {
			Query q = session.createQuery(consulta);
			q.setInteger("idArquivo", idArquivo);
			retorno = q.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		retorno = new HashSet<AtualizacaoCadastralSimplificadoCritica>(retorno);

		return retorno;
	}

	/**
	 * [UC0925] Emitir Boletos
	 * 
	 * retrona DBTP_VLSUGERIDO para DBTP_ID = idDebitoTipo
	 * 
	 * @author Rômulo Aurélio
	 * @date 22/12/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorSugeridoDebitoTipo(Integer idDebitoTipo)
			throws ErroRepositorioException {

		BigDecimal retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = "select dbtp.valorSugerido " + "from DebitoTipo dbtp "
					+ "where dbtp.id = :idDebitoTipo ";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idDebitoTipo", idDebitoTipo).uniqueResult();

			if (retorno == null) {
				retorno = new BigDecimal("0.00");
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0890]Consultar Arquivo Texto Atualização Cadastral
	 * 
	 * @author Ana Maria
	 * @date 04/03/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			String idArquivoTxt, Integer idSituacaoTransmissao)
			throws ErroRepositorioException {

		ArquivoTextoAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " select txac"// 2
					+ " from ArquivoTextoAtualizacaoCadastral txac"
					+ " inner join fetch txac.leiturista leit"
					+ " where txac.id = " + idArquivoTxt
					+ " and txac.situacaoTransmissaoLeitura.id ="
					+ idSituacaoTransmissao;

			retorno = (ArquivoTextoAtualizacaoCadastral) session.createQuery(
					consulta).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * [UC0976] Suspender Imóvel em Programa Especial Batch Pesquisa imoveis
	 * para execução do batch
	 * 
	 * @author Hugo Amorim
	 * @since 13/01/2010
	 * 
	 */
	public Collection pesquisarImovelEmProgramaEspecial(
			Integer idPerfilProgramaEspecial, Rota rota, int numeroIndice,
			int quantidadeRegistros) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT "
					+ " imovelProgramaEspecial,"
					+ " imovel,"
					+ " quadra.id,"
					+ " rota.id,"
					+ " faturamentoGrupo.id,"
					+ " faturamentoGrupo.anoMesReferencia, "
					+ " imovelProgramaEspecial.id "
					+ "FROM ImovelProgramaEspecial imovelProgramaEspecial"
					+ " INNER JOIN FETCH imovelProgramaEspecial.imovel imovel"
					+ " INNER JOIN imovel.quadra quadra"
					+ " INNER JOIN quadra.rota rota"
					+ " INNER JOIN rota.faturamentoGrupo faturamentoGrupo"
					
					+ " INNER JOIN imovel.clienteImoveis clienteImoveisUsuario with (clienteImoveisUsuario.clienteRelacaoTipo.id = "
					+ ClienteRelacaoTipo.USUARIO.toString()
					+ ") and clienteImoveisUsuario.dataFimRelacao is null "
					
					+ " INNER JOIN clienteImoveisUsuario.cliente clienteUsuario "
					+ " INNER JOIN clienteUsuario.clienteTipo clienteTipo "
					
					+ " WHERE imovelProgramaEspecial.imovelPerfil.id = :idPerfilProgramaEspecial "
					+ " AND (imovelProgramaEspecial.dataSuspensao IS NULL or imovelProgramaEspecial.formaSuspensao = :formaSuspensao)"
					+ " AND clienteTipo.indicadorProgramaEspecial = :NAO "
					+ " AND rota.id = :idRota "

					+ "ORDER BY imovelProgramaEspecial.id";

			retorno = session.createQuery(consulta)
					.setInteger("idPerfilProgramaEspecial", idPerfilProgramaEspecial)
					.setInteger("idRota", rota.getId())
					.setShort("formaSuspensao", ImovelProgramaEspecial.FORMA_SUSPENSAO_OPERADOR)
					.setShort("NAO", ConstantesSistema.NAO)
					.setMaxResults(quantidadeRegistros).setFirstResult(numeroIndice).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais Analitico
	 * 
	 * @author Hugo Leonardo, Ivan Sergio
	 * @date 18/01/2010 15/09/2010 - CRC4734: Retirar do filtro o perfil do
	 *       imóvel e obter as contas a partir de fatura item da fatura
	 *       selecionada;
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select "
					+ "	imov.imov_id as idImovelEsp, "
					+ "	rgds.rdes_id as idRegDes, "
					+ "	rgds.rdes_nmregiaodesenvolvimento as nomeRegDes, "
					+ "	loca.loca_id as idLocalidade, "
					+ "	loca.loca_nmlocalidade as nomeLocalidade, "
					+ "	clie.clie_nmcliente as nomeCliente, "
					+ "	case when (lagu.hidi_id is null) then "
					+ "		'SEM HIDR.' "
					+ "	else "
					+ "		'COM HIDR.' "
					+ "	end as hidi, "
					+ "	( coalesce(cnta.cnta_nnconsumoagua, 0) + coalesce(cths.cnhi_nnconsumoagua, 0) ) as consumoAgua, "
					+ "	( ( ( coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + coalesce(cnta.cnta_vldebitos, 0) ) "
					+ "	- ( coalesce(cnta.cnta_vlcreditos, 0) + coalesce(cnta.cnta_vlimpostos, 0) ) ) + "
					+ "	( ( coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + coalesce(cths.cnhi_vldebitos, 0) ) "
					+ "	- ( coalesce(cths.cnhi_vlcreditos, 0) + coalesce(cths.cnhi_vlimpostos, 0) ) ) ) as valorConta "
					+ "from "
					+ "	faturamento.fatura fatu "
					+ "	inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "	inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "	left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "	left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "	inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "	inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "	inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "	inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "	inner join cadastro.regiao_desenvolvimento rgds on rgds.rdes_id = muni.rdes_id "
					+ "	inner join atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imov.imov_id "
					+ "where "
					+ "	fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "	and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta = consulta + "	and loca.loca_id = "
						+ helper.getIdLocalidade() + " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta = consulta + "	and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			consulta = consulta
					+ "	order by idRegDes, idLocalidade, idImovelEsp";

			retorno = session.createSQLQuery(consulta).addScalar("idImovelEsp",
					Hibernate.INTEGER).addScalar("idRegDes", Hibernate.INTEGER)
					.addScalar("nomeRegDes", Hibernate.STRING).addScalar(
							"idLocalidade", Hibernate.INTEGER).addScalar(
							"nomeLocalidade", Hibernate.STRING).addScalar(
							"nomeCliente", Hibernate.STRING).addScalar("hidi",
							Hibernate.STRING).addScalar("consumoAgua",
							Hibernate.INTEGER).addScalar("valorConta",
							Hibernate.BIG_DECIMAL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório
	 * Analítico
	 * 
	 * @author Hugo Leonardo, Ivan Sergio
	 * @date 18/01/2010 15/09/2010 - CRC4734: Retirar do filtro o perfil do
	 *       imóvel e obter as contas a partir de fatura item da fatura
	 *       selecionada;
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */
	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Integer retorno = 0;
		String consulta = "";
		Collection colecao = null;

		Session session = HibernateUtil.getSession();

		try {
			consulta = "select "
					+ "	count(ftit.cnta_id) as qtd "
					+ "from "
					+ "	faturamento.fatura fatu "
					+ "	inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "	inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "	left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "	left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "	inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "	inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "	inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "	inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "where "
					+ "	fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "	and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta += "	and loca.loca_id = " + helper.getIdLocalidade()
						+ " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta += "	and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			colecao = (Collection) session.createSQLQuery(consulta).addScalar(
					"qtd", Hibernate.INTEGER).list();

			if (colecao != null && !colecao.isEmpty()) {
				Iterator iter = colecao.iterator();
				Integer element = (Integer) iter.next();
				retorno = (Integer) element;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais - Relatório
	 * Sintético
	 * 
	 * @author Hugo Leonardo, Ivan Sergio
	 * @date 25/01/2010 15/09/2010 - CRC4734: Retirar do filtro o perfil do
	 *       imóvel e obter as contas a partir de fatura item da fatura
	 *       selecionada;
	 * 
	 * @param RelatorioImoveisProgramasEspeciaisHelper
	 * 
	 * @return Collection<RelatorioImoveisProgramasEspeciaisHelper>
	 * @throws FachadaException
	 */

	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select A.idRegDes as idReg, "
					+ "	A.nomeRegDes as nomeReg, "
					+ "	A.idLocalidade as idLoca, "
					+ "	A.nomeLocalidade as nomeLoca, "
					+ "	SUM(A.qtdContaSemHidr) as qtdContaSemHidr, "
					+ "	SUM(A.valorContasSemHidr) as valorContasSemHidr, "
					+ "	SUM(A.qtdContaComHidr) as qtdContaComHidr, "
					+ "	SUM(A.valorContasComHidr) as valorContasComHidr "
					+ "FROM ( "
					+ "	select "
					+ "		rgds.rdes_id idRegDes, "
					+ "		rgds.rdes_nmregiaodesenvolvimento as nomeRegDes, "
					+ "		loca.loca_id as idLocalidade, "
					+ "		loca.loca_nmlocalidade as nomeLocalidade, "
					+ "		case when (lagu.hidi_id is null) then 1 else 0 end as qtdContaSemHidr, "
					+ "		case when (lagu.hidi_id is null) then "
					+ "		( ( ( (coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + "
					+ "		coalesce(cnta.cnta_vldebitos, 0)) - (coalesce(cnta.cnta_vlcreditos, 0) + "
					+ "		coalesce(cnta.cnta_vlimpostos, 0)) ) ) ) + "
					+ "		( ( ( (coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + "
					+ "		coalesce(cths.cnhi_vldebitos, 0)) - (coalesce(cths.cnhi_vlcreditos, 0) + "
					+ "		coalesce(cths.cnhi_vlimpostos,0)) ) ) ) "
					+ "		else 0 end as valorContasSemHidr, "
					+ "		case when (lagu.hidi_id is not null) then 1 else 0 end as qtdContaComHidr, "
					+ "		case when (lagu.hidi_id is not null) then "
					+ "		( ( ( (coalesce(cnta.cnta_vlagua, 0) + coalesce(cnta.cnta_vlesgoto, 0) + "
					+ "		coalesce(cnta.cnta_vldebitos, 0)) - (coalesce(cnta.cnta_vlcreditos, 0) + "
					+ "		coalesce(cnta.cnta_vlimpostos, 0)) ) ) ) + "
					+ "		( ( ( (coalesce(cths.cnhi_vlagua, 0) + coalesce(cths.cnhi_vlesgoto, 0) + "
					+ "		coalesce(cths.cnhi_vldebitos, 0)) - (coalesce(cths.cnhi_vlcreditos, 0) + "
					+ "		coalesce(cths.cnhi_vlimpostos, 0)) ) ) ) "
					+ "		else 0 end as valorContasComHidr "
					+ "	from "
					+ "		faturamento.fatura fatu "
					+ "		inner join faturamento.fatura_item ftit on ftit.fatu_id = fatu.fatu_id "
					+ "		inner join cadastro.cliente clie on clie.clie_id = fatu.clie_id "
					+ "		left outer join faturamento.conta cnta on cnta.cnta_id = ftit.cnta_id "
					+ "		left outer join faturamento.conta_historico cths on cths.cnta_id = ftit.cnta_id "
					+ "		inner join cadastro.localidade loca on (loca.loca_id = cnta.loca_id or loca.loca_id = cths.loca_id) "
					+ "		inner join cadastro.imovel imov on (imov.imov_id = cnta.imov_id or imov.imov_id = cths.imov_id) "
					+ "		inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "
					+ "		inner join cadastro.municipio muni on muni.muni_id = stcm.muni_id "
					+ "		inner join cadastro.regiao_desenvolvimento rgds on rgds.rdes_id = muni.rdes_id "
					+ "		left join atendimentopublico.ligacao_agua lagu on lagu.lagu_id = imov.imov_id "
					+ "	where "
					+ "		fatu.fatu_amreferencia = "
					+ helper.getMesAnoReferencia()
					+ " "
					+ "		and fatu.clie_id = (select clie_idprogramaespecial from cadastro.sistema_parametros) ";

			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equalsIgnoreCase("")) {
				consulta += "		and loca.loca_id = " + helper.getIdLocalidade()
						+ " ";
			}

			if (helper.getIdRegiaoDesenvolvimento() != null
					&& !helper.getIdRegiaoDesenvolvimento()
							.equalsIgnoreCase("")) {
				consulta += "		and muni.rdes_id = "
						+ helper.getIdRegiaoDesenvolvimento() + " ";
			}

			consulta += ") A "
					+ "group by "
					+ "	A.idRegDes, A.nomeRegDes, A.idLocalidade, A.nomeLocalidade "
					+ "order by " + "	idReg, idLoca";

			retorno = (Collection) session.createSQLQuery(consulta).addScalar(
					"idReg", Hibernate.INTEGER).addScalar("nomeReg",
					Hibernate.STRING).addScalar("idLoca", Hibernate.INTEGER)
					.addScalar("nomeLoca", Hibernate.STRING).addScalar(
							"qtdContaSemHidr", Hibernate.INTEGER).addScalar(
							"valorContasSemHidr", Hibernate.BIG_DECIMAL)
					.addScalar("qtdContaComHidr", Hibernate.INTEGER).addScalar(
							"valorContasComHidr", Hibernate.BIG_DECIMAL).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * [UC0973] Inserir Imóvel em Programa Especial
	 * 
	 * @author Hugo Leonardo
	 * @date 10/02/2010
	 * 
	 * @param idImovel
	 * 
	 * @return Quantidade de Parcelamentos do Imóvel
	 * @throws FachadaException
	 */
	public Integer verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta += " select count (dc.imov_id) as cont "
				+ " from faturamento.debito_a_cobrar dc "
				+ " where dc.parc_id is not null "
				+ " and dc.dbac_nnprestacaocobradas <> dc.dbac_nnprestacaodebito "
				+ " and dc.imov_id = " + idImovel + " ";
		try {
			retorno = (Integer) session.createSQLQuery(consulta).addScalar(
					"cont", Hibernate.INTEGER).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @return Collection<RelatorioColetaMedidorEnergiaHelper>
	 * @throws ControladorException
	 */
	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial,
			String idLocalidadeFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		Collection retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		try {

			consulta += " select distinct faturamentoGrupo.id, " // 0
					+ " faturamentoGrupo.descricao, " // 1
					+ " imovel.localidade.id, " // 2
					+ " imovel.localidade.descricao, " // 3
					+ " rota.codigo, " // 4
					+ " cliente.nome, " // 5
					+ " imovel.id, " // 6
					+ " localidade.gerenciaRegional, " // 7
					+ " imovel.localidade, " // 8
					+ " setor.codigo, " // 9
					+ " quadra.numeroQuadra, " // 10
					+ " roteiro.numeroLoteImovel, " // 11
					+ " roteiro.numeroSubloteImovel " // 12
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.clienteImoveis ci "
					+ " inner join ci.cliente cliente "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join imovel.localidade localidade "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " where roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
					+ " and imovel.ligacaoAguaSituacao in (:last) "
					+ " and imovel.ligacaoEsgotoSituacao in (:lest) "
					+ " and ci.dataFimRelacao is null "
					+ " and ci.clienteRelacaoTipo = :cliRelacaoTipo ";

			if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {

				consulta += " and faturamentoGrupo.id = :faturamentoGrupo ";

				parameters.put("faturamentoGrupo",
						new Integer(faturamentoGrupo));
			}

			if (idLocalidadeInicial != null && idLocalidadeFinal != null
					&& !idLocalidadeInicial.equals("")
					&& !idLocalidadeFinal.equals("")) {

				consulta += " and imovel.localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

				parameters.put("idLocalidadeInicial", new Integer(
						idLocalidadeInicial));
				parameters.put("idLocalidadeFinal", new Integer(
						idLocalidadeFinal));

				// Setor Comercial
				if (idSetorComercialInicial != null
						&& idSetorComercialFinal != null
						&& !idSetorComercialInicial.equals("")
						&& !idSetorComercialFinal.equals("")) {

					consulta += " and setor.codigo between :idSetorComercialInicial and :idSetorComercialFinal ";

					parameters.put("idSetorComercialInicial", new Integer(
							idSetorComercialInicial));
					parameters.put("idSetorComercialFinal", new Integer(
							idSetorComercialFinal));
				}

			}

			if (rotaInicial != null && rotaFinal != null
					&& !rotaInicial.equals("") && !rotaFinal.equals("")) {

				consulta += " and rota.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", new Integer(rotaInicial));
				parameters.put("rotaFinal", new Integer(rotaFinal));

			}

			if (sequencialRotaInicial != null && sequencialRotaFinal != null
					&& !sequencialRotaInicial.equals("")
					&& !sequencialRotaFinal.equals("")) {

				consulta += " and imovel.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", new Integer(
						sequencialRotaInicial));
				parameters.put("sequencialRotaFinal", new Integer(
						sequencialRotaFinal));

			}

			consulta += " order by localidade.gerenciaRegional, imovel.localidade, setor.codigo, quadra.numeroQuadra, "
					+ " roteiro.numeroLoteImovel, roteiro.numeroSubloteImovel, imovel.id, faturamentoGrupo.id, "
					+ " faturamentoGrupo.descricao, imovel.localidade.id, imovel.localidade.descricao, "
					+ " rota.codigo, cliente.nome ";

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);

			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);

			retorno = query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest).setInteger(
							"cliRelacaoTipo", ClienteRelacaoTipo.USUARIO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
	 * 
	 * Obtém a quantidade de imoveis de acordo com o filtro.
	 * 
	 * @author Hugo Leonardo
	 * @date 09/03/2010
	 * 
	 * @param FiltrarRelatorioColetaMedidorEnergiaHelper
	 * 
	 * @return Integer
	 * @throws ControladorException
	 */
	public Integer pesquisarTotalRegistroRelatorioColetaMedidorEnergia(
			String faturamentoGrupo, String idLocalidadeInicial,
			String idLocalidadeFinal, String idSetorComercialInicial,
			String idSetorComercialFinal, String rotaInicial, String rotaFinal,
			String sequencialRotaInicial, String sequencialRotaFinal)
			throws ErroRepositorioException {

		int retorno = 0;

		Session session = HibernateUtil.getSession();

		Query query = null;

		Map parameters = new HashMap();

		String consulta = "";

		try {

			consulta += " select count (roteiro.imovel.id) as cont "
					+ " from MovimentoRoteiroEmpresa roteiro "
					+ " inner join roteiro.imovel imovel "
					+ " inner join imovel.quadra quadra "
					+ " inner join imovel.setorComercial setor "
					+ " inner join quadra.rota rota "
					+ " inner join rota.faturamentoGrupo faturamentoGrupo "
					+ " inner join rota.empresa empresa "
					+ " where roteiro.anoMesMovimento = faturamentoGrupo.anoMesReferencia "
					+ " and imovel.ligacaoAguaSituacao in (:last) "
					+ " and imovel.ligacaoEsgotoSituacao in (:lest) ";

			if (faturamentoGrupo != null && !faturamentoGrupo.equals("")) {

				consulta += " and faturamentoGrupo.id = :faturamentoGrupo ";
				parameters.put("faturamentoGrupo",
						new Integer(faturamentoGrupo));
			}

			if (idLocalidadeInicial != null && idLocalidadeFinal != null
					&& !idLocalidadeInicial.equals("")
					&& !idLocalidadeFinal.equals("")) {

				consulta += " and imovel.localidade.id between :idLocalidadeInicial and :idLocalidadeFinal ";

				parameters.put("idLocalidadeInicial", new Integer(
						idLocalidadeInicial));
				parameters.put("idLocalidadeFinal", new Integer(
						idLocalidadeFinal));

				// Setor Comercial
				if (idSetorComercialInicial != null
						&& idSetorComercialFinal != null
						&& !idSetorComercialInicial.equals("")
						&& !idSetorComercialFinal.equals("")) {

					consulta += " and setor.codigo between :idSetorComercialInicial and :idSetorComercialFinal ";

					parameters.put("idSetorComercialInicial", new Integer(
							idSetorComercialInicial));
					parameters.put("idSetorComercialFinal", new Integer(
							idSetorComercialFinal));
				}

			}

			if (rotaInicial != null && rotaFinal != null
					&& !rotaInicial.equals("") && !rotaFinal.equals("")) {

				consulta += " and rota.codigo between :rotaInicial and :rotaFinal ";

				parameters.put("rotaInicial", new Integer(rotaInicial));
				parameters.put("rotaFinal", new Integer(rotaFinal));

			}

			if (sequencialRotaInicial != null && sequencialRotaFinal != null
					&& !sequencialRotaInicial.equals("")
					&& !sequencialRotaFinal.equals("")) {

				consulta += " and imovel.numeroSequencialRota between :sequencialRotaInicial and :sequencialRotaFinal ";

				parameters.put("sequencialRotaInicial", new Integer(
						sequencialRotaInicial));
				parameters.put("sequencialRotaFinal", new Integer(
						sequencialRotaFinal));
			}

			query = session.createQuery(consulta);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			Collection colecaoLast = new ArrayList();
			colecaoLast.add(LigacaoAguaSituacao.LIGADO);
			colecaoLast.add(LigacaoAguaSituacao.LIGADO_EM_ANALISE);
			colecaoLast.add(LigacaoAguaSituacao.CORTADO);
			colecaoLast.add(LigacaoAguaSituacao.SUPRIMIDO);

			Collection colecaoLest = new ArrayList();
			colecaoLest.add(LigacaoEsgotoSituacao.POTENCIAL);
			colecaoLest.add(LigacaoEsgotoSituacao.LIGADO);
			colecaoLest.add(LigacaoEsgotoSituacao.LIG_FORA_DE_USO);
			colecaoLest.add(LigacaoEsgotoSituacao.TAMPONADO);

			retorno = (Integer) query.setParameterList("last", colecaoLast)
					.setParameterList("lest", colecaoLest).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do
	 * imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010
	 */
	public Collection<Integer> pesquisarIdsImoveisDoSetorComercial(
			Integer idSetor, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException {
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
			consulta = " SELECT id " + " FROM Imovel"
					+ " WHERE setorComercial = :idSetor" + " ORDER BY id";

			retorno = (Collection<Integer>) session.createQuery(consulta)
					.setInteger("idSetor", idSetor).setFirstResult(
							quantidadeInicio).setMaxResults(quantidadeMaxima)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * Batch criado para atualização da coluna codigo debito automatico do
	 * imovel.
	 * 
	 * @author Hugo Amorim
	 * @date 30/03/2010
	 */
	public void atualizarCodigoDebitoAutomatico(Integer idImovel,
			Integer codigoDebitoAutomatico) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "UPDATE Imovel "
				+ "set codigoDebitoAutomatico =:codigoDebitoAutomatico where id = :idImovel ";

		try {

			session.createQuery(consulta).setInteger("codigoDebitoAutomatico",
					codigoDebitoAutomatico).setInteger("idImovel", idImovel)
					.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);

		}

	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa a nova versão do JAD do mobile para o celular
	 * 
	 * @author Bruno Barros
	 * @date 08/06/2010
	 * 
	 * @param
	 * @throws IOException
	 */
	public byte[] baixarNovaVersaoJad() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] retorno = null;

		try {
			consulta = " select vemo_arquivojad as jad from cadastro.versao_mobile order by replace( vemo_nnversao, '.', '' ) desc";

			retorno = (byte[]) session.createSQLQuery(consulta).addScalar(
					"jad", Hibernate.BINARY).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		/*
		 * if ( retorno != null) {
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * 
		 * try { sb = new StringBuilder( retorno.toString() ); } catch
		 * (IOException e) { throw new ErroRepositorioException( "Erro em
		 * Transformar Array de Byte em Object"); } catch
		 * (ClassNotFoundException e) { throw new ErroRepositorioException(
		 * "Erro em Transformar Array de Byte em Object"); } retorno =
		 * sb.toString().getBytes(); }
		 */

		return retorno;
	}

	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa a nova versão do JAR do mobile para o celular
	 * 
	 * @author Bruno Barros
	 * @date 08/06/2010
	 * 
	 * @param
	 * @throws IOException
	 */
	public byte[] baixarNovaVersaoJar() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] retorno = null;

		try {
			consulta = " select vemo_arquivojar as jar from cadastro.versao_mobile order by replace( vemo_nnversao, '.', '' ) desc";

			retorno = (byte[]) session.createSQLQuery(consulta).addScalar(
					"jar", Hibernate.BINARY).setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 07/07/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel)
			throws ErroRepositorioException {

		boolean retorno = false;

		List resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  i.imov_id as idImovel, "
					+ "   cs.cbst_dscobrancasituacao as situacao"
					+ " FROM cadastro.imovel i"
					+ " INNER JOIN faturamento.conta c on c.imov_id = i.imov_id"
					+ " INNER JOIN cobranca.cobranca_situacao cs on cs.cmrv_id = c.cmrv_id"
					+ " WHERE cs.cbst_id = :cobrancaSituacao"
					+ " and i.imov_id = :idImovel";

			resultado = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).addScalar("situacao", Hibernate.STRING)
					.setInteger("cobrancaSituacao",
							CobrancaSituacao.EM_COBRANCA_JUDICIAL).setInteger(
							"idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		if (resultado != null && resultado.size() > 0) {
			// Imovel esta em cobranca judicial
			retorno = true;

		} else {
			// imovel nao esta em cobranca judicial
			retorno = false;
		}

		return retorno;

	}

	/**
	 * 
	 * @author Fernando Fontelles
	 * @date 07/07/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public boolean verificarSituacaoImovelNegativacao(Integer idImovel)
			throws ErroRepositorioException {

		boolean retorno = false;

		List resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  i.imov_id as idImovel "
					+ " FROM cadastro.imovel i"
					+ " INNER JOIN cobranca.negatd_movimento_reg neg on neg.imov_id = i.imov_id"
					+ " WHERE (neg.nmrg_icaceito is null or neg.nmrg_icaceito = 1) "
					+ " and neg.nmrg_idreginclusao is null and neg.nmrg_cdexclusaotipo is null "
					+ " and i.imov_id = :idImovel";

			resultado = session.createSQLQuery(consulta).addScalar("idImovel",
					Hibernate.INTEGER).setInteger("idImovel", idImovel).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		if (resultado != null && resultado.size() > 0) {
			// Imovel esta em negativacao
			retorno = true;

		} else {
			// imovel nao esta em negativacao
			retorno = false;
		}

		return retorno;

	}

	/**
	 * 
	 * [UC1036] - Inserir Cadastro de Email do Cliente
	 * 
	 * @author Fernando Fontelles
	 * @date 09/07/2010
	 * 
	 * @param idCliente
	 * @param nomeClienteAnterior
	 * @param cpfAnterior
	 * @param cnpjAnterior
	 * @param emailAnterior
	 * @param nomeSolicitante
	 * @param cpfSolicitante
	 * @param nomeClienteAtual
	 * @param cpfClienteAtual
	 * @param cnpjClienteAtual
	 * @param emailAtual
	 * @return
	 */
	public Integer inserirCadastroEmailCliente(Integer idCliente,
			String nomeClienteAnterior, String cpfAnterior,
			String cnpjAnterior, String emailAnterior, String nomeSolicitante,
			String cpfSolicitante, String nomeClienteAtual,
			String cpfClienteAtual, String cnpjClienteAtual, String emailAtual)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String insert;

		Integer retorno;

		Connection con = null;
		Statement stmt = null;

		try {

			con = session.connection();
			stmt = con.createStatement();
			String sequence = Util
					.obterNextValSequence("cadastro.sequence_email_client_alterada");
			insert = "insert into cadastro.email_client_alterada( ecla_id, "
					+ " clie_id, " + " ecla_nmclienteanterior, "
					+ " ecla_nncpfanterior, " + " ecla_nncnpjanterior, "
					+ " ecla_dsemailanterior, " + " ecla_nmsolicitante, "
					+ " ecla_nncpfsolicitante, " + " ecla_nmclienteatual, "
					+ " ecla_nncpfatual, " + " ecla_nncnpjatual, "
					+ " ecla_dsemailatual, " + " ecla_tmconfirmacaoonline, "
					+ " ecla_tmsolicitacaoonline, "
					+ " ecla_tmultimaalteracao ) " + "values ( " + sequence
					+ ", " + idCliente + ", " + nomeClienteAnterior + ", "
					+ cpfAnterior + ", " + cnpjAnterior + ", " + emailAnterior
					+ ", " + nomeSolicitante + ", " + cpfSolicitante + ", "
					+ nomeClienteAtual + ", " + cpfClienteAtual + ", "
					+ cnpjClienteAtual + ", " + emailAtual + ", " + null + ", "
					+ Util.obterSQLDataAtual() + ", "
					+ Util.obterSQLDataAtual() + ")";

			retorno = (Integer) stmt.executeUpdate(insert);

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Insert");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				throw new ErroRepositorioException(e, "Erro ao fechar conexões");
			}
		}

		return retorno;

	}

	/**
	 * Atualiza o sequencial de rota do imóvel correspondente ao
	 * RotaAtualizacaoSeq recebido
	 * 
	 * @author Bruno Barros
	 * @date 11/08/2010
	 * 
	 * @param rotaAtualizacaoSeq -
	 *            Registro da tabela micromedicao.rota_atualizacao_sequencial
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarSequenciaRotaImovel(RotaAtualizacaoSeq seq)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		try {
			String delete = "update Imovel as imovel "
					+ "set imovel.numeroSequencialRota = :sequencial "
					+ "where imovel.id = :idImovel";

			session.createQuery(delete).setInteger("sequencial",
					seq.getSequencialRota() * 10).setInteger("idImovel",
					seq.getImovel().getId()).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * 
	 * @author Rômulo Aurélio
	 * @date 28/09/2010
	 * 
	 * @param idImovel
	 * @return
	 * @throws ControladorException
	 */
	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(
			Integer idImovel) throws ErroRepositorioException {

		ClienteImovel resultado;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = " SELECT  clienteImovel "
					+ " FROM ClienteImovel clienteImovel "
					+ " INNER JOIN clienteImovel.cliente cliente "
					+ " INNER JOIN clienteImovel.imovel imovel "
					+ " LEFT JOIN cliente.clienteTipo clienteTipo "
					+ " WHERE clienteImovel.clienteRelacaoTipo = :responsavel "
					+ " and clienteImovel.dataFimRelacao is NULL "
					+ " and clienteTipo.esferaPoder.id in ("
					+ EsferaPoder.ESTADUAL + "," + EsferaPoder.MUNICIPAL + ","
					+ EsferaPoder.FEDERAL + ") "
					+ " and clienteImovel.imovel.id = :idImovel";

			resultado = (ClienteImovel) session.createQuery(consulta)
					.setInteger("responsavel", ClienteRelacaoTipo.RESPONSAVEL)
					.setInteger("idImovel", idImovel).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return resultado;

	}

	/**
	 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
	 * 
	 * @author Hugo Amorim
	 * @date 08/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorUsuario(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = "SELECT us.unid_dsunidade as unidSuperior,"
					+ " uo.unid_dsunidade as unidOrganizacional,"
					+ " u.usur_nmusuario as usuario,"
					+ " ms.meso_dsmeiosolicitacao as meio,"
					+ " count(*) as contador"
					+ " FROM seguranca.tab_linha_col_alteracao tbco"
					+ " INNER JOIN seguranca.tabela_coluna tc on tc.tbco_id = tbco.tbco_id"
					+ " INNER JOIN seguranca.tabela_linha_alteracao tla on tla.tbla_id = tbco.tbla_id"
					+ " INNER JOIN seguranca.operacao_efetuada oe on oe.opef_id = tla.tref_id"
					+ " INNER JOIN seguranca.operacao o on o.oper_id = oe.oper_id"
					+ " INNER JOIN seguranca.usuario_alteracao ua on ua.tref_id = oe.opef_id"
					+ " INNER JOIN seguranca.usuario u on u.usur_id = ua.usis_id"
					+ " INNER JOIN cadastro.unidade_organizacional uo on uo.unid_id = u.unid_id"
					+ " LEFT JOIN cadastro.unidade_organizacional us on us.unid_id = uo.unid_idsuperior"
					+ " INNER JOIN atendimentopublico.meio_solicitacao ms on ms.meso_id = uo.meso_id"
					+ " WHERE tbco.tbco_id = :idColuna"
					+ " and to_date(to_char(tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoInicial and :periodoFinal"
					+ " and ";

			if (helper.getIdMeioSolicitacao() != null
					&& !helper.getIdMeioSolicitacao().equals("-1")
					&& !helper.getIdMeioSolicitacao().equals("0")) {
				consulta += " uo.meso_id = :meioSolicitacao and ";
				parameters.put("meioSolicitacao", Integer.parseInt(helper
						.getIdMeioSolicitacao()));
			}
			if (helper.getIdFuncionalidade() != null
					&& !helper.getIdFuncionalidade().equals("")) {
				consulta += " o.fncd_id = :idFuncionalidade and ";
				parameters.put("idFuncionalidade", Integer.parseInt(helper
						.getIdFuncionalidade()));
			}
			if (helper.getIdOperacao() != null
					&& !helper.getIdOperacao().equals("")) {
				consulta += " o.oper_id = :idOperacao and ";
				parameters.put("idOperacao", Integer.parseInt(helper
						.getIdOperacao()));
			}
			if (helper.getIdUnidadeSuperior() != null
					&& !helper.getIdUnidadeSuperior().equals("")) {
				consulta += " uo.unid_idsuperior = :idUnidadeSuperior and ";
				parameters.put("idUnidadeSuperior", Integer.parseInt(helper
						.getIdUnidadeSuperior()));
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUnidadeOrganizacional())) {
				consulta += " uo.unid_id in ( :idsUnidadeOrganizacional ) and ";
				parameters.put("idsUnidadeOrganizacional", helper
						.getColecaoUnidadeOrganizacional());
			}
			if (helper.getIdUsuario() != null
					&& !helper.getIdUsuario().equals("")) {
				consulta += " u.usur_id = :idUsuario and ";
				parameters.put("idUsuario", Integer.parseInt(helper
						.getIdUsuario()));
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY us.unid_dsunidade,uo.unid_dsunidade,ms.meso_dsmeiosolicitacao,u.usur_nmusuario";
			consulta += " ORDER BY us.unid_dsunidade,uo.unid_dsunidade,ms.meso_dsmeiosolicitacao,u.usur_nmusuario";

			query = session.createSQLQuery(consulta).addScalar("unidSuperior",
					Hibernate.STRING).addScalar("unidOrganizacional",
					Hibernate.STRING).addScalar("usuario", Hibernate.STRING)
					.addScalar("meio", Hibernate.STRING).addScalar("contador",
							Hibernate.INTEGER).setInteger("idColuna",
							Integer.parseInt(helper.getIdColuna()))
					.setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
	 * 
	 * @author Hugo Amorim
	 * @date 08/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesSistemaColunaPorLocalidade(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT g.greg_nmregional as gerencia,\n "
					+ "  un.uneg_nmunidadenegocio as unidade,\n "
					+ "   l.loca_nmlocalidade as localidade,\n  "
					+ "   ms.meso_dsmeiosolicitacao as meio,\n "
					+ "  count(*) as contador \n"
					+ "  FROM seguranca.tab_linha_col_alteracao tbco \n"
					+ "   INNER JOIN seguranca.tabela_coluna tc on tc.tbco_id = tbco.tbco_id \n"
					+ "   INNER JOIN seguranca.tabela_linha_alteracao tla on tla.tbla_id = tbco.tbla_id \n"
					+ "   INNER JOIN seguranca.operacao_efetuada oe on oe.opef_id = tla.tref_id \n"
					+ "  INNER JOIN seguranca.operacao o on o.oper_id = oe.oper_id \n"
					+ "   INNER JOIN seguranca.usuario_alteracao ua on ua.tref_id = oe.opef_id \n"
					+ "   INNER JOIN seguranca.usuario u on u.usur_id = ua.usis_id \n"
					+ "   INNER JOIN cadastro.imovel ic on ic.imov_id = tla.tbla_id1 \n"
					+ "   LEFT JOIN cadastro.imovel i on i.imov_id = tla.tbla_id2 \n"
					+ "  INNER JOIN cadastro.localidade l on l.loca_id = ic.loca_id  \n"
					+ "   INNER JOIN cadastro.unidade_negocio un on un.uneg_id = l.uneg_id \n"
					+ "   INNER JOIN cadastro.gerencia_regional g on g.greg_id = un.greg_id \n"
					+ "   INNER JOIN cadastro.unidade_organizacional uo on uo.loca_id = l.loca_id \n"
					+ "   INNER JOIN atendimentopublico.meio_solicitacao ms on ms.meso_id = uo.meso_id \n"
					+ " WHERE tbco.tbco_id = :idColuna \n"
					+ " and to_date(to_char(tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD') between :periodoInicial and :periodoFinal \n"
					+ " and ";

			if (helper.getIdMeioSolicitacao() != null
					&& !helper.getIdMeioSolicitacao().equals("-1")
					&& !helper.getIdMeioSolicitacao().equals("0")) {
				consulta += " uo.meso_id = :meioSolicitacao and ";
				parameters.put("meioSolicitacao", Integer.parseInt(helper
						.getIdMeioSolicitacao()));
			}
			if (helper.getIdFuncionalidade() != null
					&& !helper.getIdFuncionalidade().equals("")) {
				consulta += " o.fncd_id = :idFuncionalidade and ";
				parameters.put("idFuncionalidade", Integer.parseInt(helper
						.getIdFuncionalidade()));
			}
			if (helper.getIdOperacao() != null
					&& !helper.getIdOperacao().equals("")) {
				consulta += " o.oper_id = :idOperacao and ";
				parameters.put("idOperacao", Integer.parseInt(helper
						.getIdOperacao()));
			}
			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("")) {
				consulta += " g.greg_id = :idGerenciaRegional and ";
				parameters.put("idGerenciaRegional", Integer.parseInt(helper
						.getIdGerenciaRegional()));
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("")) {
				consulta += " un.uneg_id = :idUnidadeNegocio and ";
				parameters.put("idUnidadeNegocio", Integer.parseInt(helper
						.getIdUnidadeNegocio()));
			}
			if (helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equals("")) {
				consulta += " l.loca_id = :idLocalidade and ";
				parameters.put("idLocalidade", Integer.parseInt(helper
						.getIdLocalidade()));
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY g.greg_nmregional,un.uneg_nmunidadenegocio,l.loca_nmlocalidade,ms.meso_dsmeiosolicitacao \n";
			consulta += " ORDER BY g.greg_nmregional,un.uneg_nmunidadenegocio,l.loca_nmlocalidade,ms.meso_dsmeiosolicitacao";

			query = session.createSQLQuery(consulta).addScalar("gerencia",
					Hibernate.STRING).addScalar("unidade", Hibernate.STRING)
					.addScalar("localidade", Hibernate.STRING).addScalar(
							"meio", Hibernate.STRING).addScalar("contador",
							Hibernate.INTEGER).setInteger("idColuna",
							Integer.parseInt(helper.getIdColuna()))
					.setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1074] Gerar Relatório Alterações no Sistema por Coluna
	 * 
	 * [FS0007]
	 * 
	 * @author Hugo Amorim
	 * @date 08/09/2010
	 */
	public boolean verificarRelacaoColuna(Integer idColuna)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retornoConsulta = null;
		boolean retorno = false;
		try {
			String consulta = "SELECT tbco_id as coluna"
					+ " FROM seguranca.tabela_coluna"
					+ " WHERE tabe_id = (SELECT tabe_id"
					+ " FROM seguranca.tabela_coluna WHERE tbco_id  = :idColuna)"
					+ " and (tbco_nmcoluna like 'clie_id' or tbco_nmcoluna like 'imov_id')";

			retornoConsulta = (Integer) session.createSQLQuery(consulta)
					.addScalar("coluna", Hibernate.INTEGER).setInteger(
							"idColuna", idColuna).setMaxResults(1)
					.uniqueResult();

			if (retornoConsulta != null) {
				retorno = true;
			}

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
	 * 
	 * @author Daniel Alves
	 * @date 28/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " SELECT imovel.id,"
					+ " localidade.id, "
					+ " emailClienteAlterado.nomeClienteAnterior, "
					+ " emailClienteAlterado.nomeClienteAtual, "
					+ " emailClienteAlterado.cpfAnterior, "
					+ " emailClienteAlterado.cpfAtual, "
					+ " emailClienteAlterado.cnpjAnterior, "
					+ " emailClienteAlterado.cnpjAtual, "
					+ " emailClienteAlterado.emailAnterior, "
					+ " emailClienteAlterado.emailAtual, "
					+ " emailClienteAlterado.confirmacaoOnline, "
					+ " emailClienteAlterado.nomeSolicitante, "
					+ " emailClienteAlterado.cpfSolicitante, "
					+ " emailClienteAlterado.telefoneContato, "
					+ " unidadeNegocio.nome, "
					+ " gerenciaRegional.nome, "
					+ " emailClienteAlterado.confirmacaoOnline, "
					+ " emailClienteAlterado.idCliente.id "
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " and clienteImovel.clienteRelacaoTipo.id = 2 and clienteImovel.dataFimRelacao is null "
					+ " and imovel.imovelContaEnvio = :idImovelContaEnvio ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();
			}

			consulta += " order by emailClienteAlterado.confirmacaoOnline,gerenciaRegional.id,unidadeNegocio.id,localidade.id ";

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59")).setInteger("idImovelContaEnvio",
					ImovelContaEnvio.ENVIAR_PARA_IMOVEL_E_PARA_EMAIL);

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
	 * 
	 * @author Daniel Alves
	 * @date 28/09/2010
	 */
	public Collection<Object[]> pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		try {
			consulta = " SELECT SUM(CASE WHEN emailClienteAlterado.nomeClienteAtual != nvl(emailClienteAlterado.nomeClienteAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.cpfAtual != nvl(emailClienteAlterado.cpfAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.cnpjAtual != nvl(emailClienteAlterado.cnpjAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " SUM(CASE WHEN emailClienteAlterado.emailAtual != nvl(emailClienteAlterado.emailAnterior,0) "
					+ "THEN 1 ELSE 0 END), "
					+ " COUNT(distinct cliente.id)"
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " and clienteImovel.clienteRelacaoTipo = 2 and clienteImovel.dataFimRelacao is null ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();

			}

			// consulta+=" GROUP BY unidadeNegocio.nome,gerenciaRegional.nome";

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59"));

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1076] Gerar Relatório Atualizações Cadastrais Via Internet.
	 * 
	 * @author Hugo Amorim de Lyra
	 * @date 06/10/2010
	 */
	public Integer countRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ErroRepositorioException {

		Integer retorno = new Integer(0);
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta;

		// Date dataInicio =
		// Util.converteStringParaDate(helper.getPeriodoReferenciaInicial());
		// Date dataFim =
		// Util.converteStringParaDate(helper.getPeriodoReferenciaFinal());

		try {
			consulta = " SELECT count(*) "
					+ "FROM EmailClienteAlterado emailClienteAlterado "
					+ "INNER JOIN emailClienteAlterado.idCliente cliente "
					+ "INNER JOIN cliente.clienteImoveis clienteImovel "
					+ "INNER JOIN clienteImovel.imovel imovel "
					+ "INNER JOIN imovel.localidade localidade "
					+ "INNER JOIN localidade.unidadeNegocio  unidadeNegocio "
					+ "INNER JOIN unidadeNegocio.gerenciaRegional gerenciaRegional "
					+

					" WHERE emailClienteAlterado.confirmacaoOnline BETWEEN :periodoReferenciaInicial AND :periodoReferenciaFinal "
					+ " AND clienteImovel.clienteRelacaoTipo.id = 2 and clienteImovel.dataFimRelacao is null ";

			if (helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("-1")) {
				consulta += " and gerenciaRegional.id = "
						+ helper.getIdGerenciaRegional();
			}
			if (helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("-1")) {
				consulta += " and unidadeNegocio.id = "
						+ helper.getIdUnidadeNegocio();
			}
			if (helper.getIdLocalidadeInicial() != null
					&& !helper.getIdLocalidadeInicial().equals("")
					&& helper.getIdLocalidadeFinal() != null
					&& !helper.getIdLocalidadeFinal().equals("")) {

				consulta += " and localidade.id BETWEEN "
						+ helper.getIdLocalidadeInicial() + " and "
						+ helper.getIdLocalidadeFinal();

			}

			query = session.createQuery(consulta).setTimestamp(
					"periodoReferenciaInicial",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaInicial()
							+ " 00:00:00")).setTimestamp(
					"periodoReferenciaFinal",
					Util.converteStringParaDateHora(helper
							.getPeriodoReferenciaFinal()
							+ " 23:59:59"));

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " select iia "
					+ " from ImovelInscricaoAlterada iia "
					+ " inner join fetch iia.imovel imov "
					+ " inner join fetch imov.clienteImoveis cliImo "
					+ " inner join fetch cliImo.cliente clie "
					+ " inner join iia.localidadeAtual locaAtual "
					+ " inner join fetch iia.setorComercialAtual setorAtual "
					+ " inner join fetch iia.quadraAtual quadraAtual "
					+ " inner join quadraAtual.rota rotaAtual "
					+ " inner join iia.localidadeAnterior locaAnterior "
					+ " inner join fetch iia.setorComercialAnterior setorAnterior "
					+ " inner join fetch iia.quadraAnterior quadraAnterior "
					+ " where iia.indicadorAtualizacaoExcluida = 2 "
					+ " and cliImo.clienteRelacaoTipo = 2 "
					+ " and cliImo.dataFimRelacao is null ";
					

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Imóveis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóveis sem alteração devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
						  + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóvel pendente de alteração.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
						  + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			// Localidade
			if (relatorioHelper.getLocalidadeInicial() != null
					&& relatorioHelper.getLocalidadeFinal() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());
				parameters.put("localidadeFinal", relatorioHelper
						.getLocalidadeFinal());

				consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
			} else if (relatorioHelper.getLocalidadeInicial() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());

				consulta += " and iia.localidadeAtual =:localidadeInicial ";
			}

			// Setor Comercial
			if (relatorioHelper.getSetorComercialInicial() != null
					&& relatorioHelper.getSetorComercialFinal() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());
				parameters.put("setoComercialFinal", relatorioHelper
						.getSetorComercialFinal());

				consulta += " and iia.setorComercialAtual between :setoComercialInicial and :setoComercialFinal ";
			} else if (relatorioHelper.getSetorComercialInicial() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());

				consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
			}

			// Quadra
			if (relatorioHelper.getQuadraInicial() != null
					&& relatorioHelper.getQuadraFinal() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());
				parameters.put("quadraFinal", relatorioHelper.getQuadraFinal());

				consulta += " and iia.quadraAtual between :quadraInicial and :quadraFinal ";
			} else if (relatorioHelper.getQuadraInicial() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());

				consulta += " and iia.quadraAtual =:quadraInicial ";
			}

			// Lote
			if (relatorioHelper.getLoteInicial() != null
					&& relatorioHelper.getLoteFinal() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());
				parameters.put("loteFinal", relatorioHelper.getLoteFinal());

				consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
			} else if (relatorioHelper.getLoteInicial() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());

				consulta += " and iia.loteAtual =:loteInicial ";
			}

			// Sub-Lote
			if (relatorioHelper.getSubLoteInicial() != null
					&& relatorioHelper.getSubLoteFinal() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());
				parameters.put("subLoteFinal", relatorioHelper
						.getSubLoteFinal());

				consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
			} else if (relatorioHelper.getSubLoteInicial() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());

				consulta += " and iia.subLoteAtual =:subLoteInicial ";
			}

			consulta += " order by locaAtual.id, setorAtual.codigo, "
					+ " rotaAtual.codigo, imov.numeroSequencialRota, quadraAtual.numeroQuadra, "
					+ " iia.loteAtual, iia.subLoteAtual ";

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch.
	 * 
	 * @author Hugo Leonardo
	 * @date 19/01/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ErroRepositorioException {

		Integer retorno = new Integer(0);

		Session session = HibernateUtil.getSession();
		String consulta = "";
		Query query = null;
		Map parameters = new HashMap();

		try {

			consulta = " SELECT count(iia.id) "
					+ " from ImovelInscricaoAlterada iia "
					+ " inner join iia.imovel imov "
					+ " inner join imov.clienteImoveis cliImo "
					+ " inner join cliImo.cliente clie "
					+ " inner join iia.localidadeAtual locaAtual "
					+ " inner join iia.setorComercialAtual setorAtual "
					+ " inner join iia.quadraAtual quadraAtual "
					+ " inner join iia.localidadeAnterior locaAnterior "
					+ " inner join iia.setorComercialAnterior setorAnterior "
					+ " inner join iia.quadraAnterior quadraAnterior "
					+ " where iia.indicadorAtualizacaoExcluida = 2 "
					+ " and cliImo.clienteRelacaoTipo = 2 "
					+ " and cliImo.dataFimRelacao is null ";

			parameters.put("dataInicial", relatorioHelper.getDataInicio());
			parameters.put("dataFinal", relatorioHelper.getDataFim());

			// Tipo da Consulta

			// Imóveis alterados com sucesso.
			if (relatorioHelper.getEscolhaRelatorio().intValue() == 1) {

				consulta += " and iia.indicadorAtualizado = 1 "
					      + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóveis sem alteração devido a erro.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 2) {

				consulta += " and iia.indicadorErroAlteracao = 1 "
					      + " and iia.dataAlteracaoBatch between :dataInicial and :dataFinal ";
			}
			// Imóvel pendente de alteração.
			else if (relatorioHelper.getEscolhaRelatorio().intValue() == 3) {

				consulta += " and iia.indicadorAtualizado = 2 and iia.indicadorErroAlteracao is null "
					      + " and iia.ultimaAlteracao between :dataInicial and :dataFinal ";
			}

			// Localidade
			if (relatorioHelper.getLocalidadeInicial() != null
					&& relatorioHelper.getLocalidadeFinal() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());
				parameters.put("localidadeFinal", relatorioHelper
						.getLocalidadeFinal());

				consulta += " and iia.localidadeAtual between :localidadeInicial and :localidadeFinal ";
			} else if (relatorioHelper.getLocalidadeInicial() != null) {

				parameters.put("localidadeInicial", relatorioHelper
						.getLocalidadeInicial());

				consulta += " and iia.localidadeAtual =:localidadeInicial ";
			}

			// Setor Comercial
			if (relatorioHelper.getSetorComercialInicial() != null
					&& relatorioHelper.getSetorComercialFinal() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());
				parameters.put("setoComercialFinal", relatorioHelper
						.getSetorComercialFinal());

				consulta += " and iia.setorComercialAtual between :setoComercialInicial and :setoComercialFinal ";
			} else if (relatorioHelper.getSetorComercialInicial() != null) {

				parameters.put("setoComercialInicial", relatorioHelper
						.getSetorComercialInicial());

				consulta += " and iia.setorComercialAtual =:setoComercialInicial ";
			}

			// Quadra
			if (relatorioHelper.getQuadraInicial() != null
					&& relatorioHelper.getQuadraFinal() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());
				parameters.put("quadraFinal", relatorioHelper.getQuadraFinal());

				consulta += " and iia.quadraAtual between :quadraInicial and :quadraFinal ";
			} else if (relatorioHelper.getQuadraInicial() != null) {

				parameters.put("quadraInicial", relatorioHelper
						.getQuadraInicial());

				consulta += " and iia.quadraAtual =:quadraInicial ";
			}

			// Lote
			if (relatorioHelper.getLoteInicial() != null
					&& relatorioHelper.getLoteFinal() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());
				parameters.put("loteFinal", relatorioHelper.getLoteFinal());

				consulta += " and iia.loteAtual between :loteInicial and :loteFinal ";
			} else if (relatorioHelper.getLoteInicial() != null) {

				parameters.put("loteInicial", relatorioHelper.getLoteInicial());

				consulta += " and iia.loteAtual =:loteInicial ";
			}

			// Sub-Lote
			if (relatorioHelper.getSubLoteInicial() != null
					&& relatorioHelper.getSubLoteFinal() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());
				parameters.put("subLoteFinal", relatorioHelper
						.getSubLoteFinal());

				consulta += " and iia.subLoteAtual between :subLoteInicial and :subLoteFinal ";
			} else if (relatorioHelper.getSubLoteInicial() != null) {

				parameters.put("subLoteInicial", relatorioHelper
						.getSubLoteInicial());

				consulta += " and iia.subLoteAtual =:subLoteInicial ";
			}

			query = session.createQuery(consulta);

			// ITERA OS PARAMETROS E COLOCA
			// OS MESMOS NA QUERY
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Date) {
					Date data = (Date) parameters.get(key);
					query.setTimestamp(key, data);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = (Integer) query.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Usuário
	 * 
	 * @author Mariana Victor
	 * @date 16/02/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorUsuario(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT usur.usur_nmusuario AS nome, usur.usur_nmlogin AS login, "
					+ " unid.unid_dsunidade AS unidade, meso.meso_dsmeiosolicitacao AS meio, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) contadorCnpj, "
					+ " count(*) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN atendimentopublico.meio_solicitacao meso ON unid.meso_id = meso.meso_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ "  AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ "  between :periodoInicial and :periodoFinal" + " and ";

			if (helper.getIdUnidadeSuperior() != null
					&& !helper.getIdUnidadeSuperior().equals("")) {
				consulta += " unid.unid_idsuperior = :idUnidadeSuperior and ";
				parameters.put("idUnidadeSuperior", Integer.parseInt(helper
						.getIdUnidadeSuperior()));
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUnidadeOrganizacional())) {
				consulta += " unid.unid_id in ( :idsUnidadeOrganizacional ) and ";
				parameters.put("idsUnidadeOrganizacional", helper
						.getColecaoUnidadeOrganizacional());
			}
			if (!Util.isVazioOrNulo(helper.getColecaoUsuario())) {
				consulta += " usur.usur_id in ( :idsUsuario ) and ";
				parameters.put("idsUsuario", helper.getColecaoUsuario());
			}
			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " meso.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY usur.usur_nmusuario, usur.usur_nmlogin, unid.unid_dsunidade, meso.meso_dsmeiosolicitacao";

			consulta += " ORDER BY usur.usur_nmusuario, usur.usur_nmlogin, unid.unid_dsunidade, meso.meso_dsmeiosolicitacao";

			query = session.createSQLQuery(consulta).addScalar("nome",
					Hibernate.STRING).addScalar("login", Hibernate.STRING)
					.addScalar("unidade", Hibernate.STRING).addScalar("meio",
							Hibernate.STRING).addScalar("contadorCpf",
							Hibernate.DOUBLE).addScalar("contadorCnpj",
							Hibernate.DOUBLE).addScalar("contador",
							Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Localidade
	 * 
	 * @author Mariana Victor
	 * @date 17/02/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorLocalidade(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT greg.greg_nmregional AS gerenciaRegional, uneg.uneg_nmunidadenegocio AS unidadeNegocio, "
					+ " loca.loca_nmlocalidade AS localidade, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) contadorCnpj, "
					+ " count(tbca.tbca_id) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON  usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN cadastro.imovel imov ON imov.imov_id = tbla.tbla_id2 "
					+ " INNER JOIN cadastro.localidade loca ON loca.loca_id = imov.loca_id "
					+ " INNER JOIN cadastro.unidade_negocio uneg ON loca.uneg_id = uneg.uneg_id "
					+ " INNER JOIN cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ " AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ " between :periodoInicial and :periodoFinal" + " and ";

			if (helper.getOpcaoTotalizacao().equals("gerenciaRegional")
					&& helper.getIdGerenciaRegional() != null
					&& !helper.getIdGerenciaRegional().equals("")) {
				consulta += " greg.greg_id = :idGerenciaRegional and ";
				parameters.put("idGerenciaRegional", Integer.parseInt(helper
						.getIdGerenciaRegional()));
			} else if (helper.getOpcaoTotalizacao().equals(
					"gerenciaRegionalLocalidade")
					&& helper.getIdGerenciaRegionalPorLocalidade() != null
					&& !helper.getIdGerenciaRegionalPorLocalidade().equals("")) {
				consulta += " greg.greg_id = :idGerenciaRegionalLocalidade and ";
				parameters.put("idGerenciaRegionalLocalidade", Integer
						.parseInt(helper.getIdGerenciaRegionalPorLocalidade()));
			} else if (helper.getOpcaoTotalizacao().equals("unidadeNegocio")
					&& helper.getIdUnidadeNegocio() != null
					&& !helper.getIdUnidadeNegocio().equals("")) {
				consulta += " uneg.uneg_id = :idUnidadeNegocio and ";
				parameters.put("idUnidadeNegocio", Integer.parseInt(helper
						.getIdUnidadeNegocio()));
			} else if (helper.getOpcaoTotalizacao().equals("localidade")
					&& helper.getIdLocalidade() != null
					&& !helper.getIdLocalidade().equals("")) {
				consulta += " loca.loca_id = :idLocalidade and ";
				parameters.put("idLocalidade", Integer.parseInt(helper
						.getIdLocalidade()));
			}
			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " unid.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY greg.greg_nmregional, uneg.uneg_nmunidadenegocio, loca.loca_nmlocalidade ";
			consulta += " ORDER BY greg.greg_nmregional, uneg.uneg_nmunidadenegocio, loca.loca_nmlocalidade ";

			query = session.createSQLQuery(consulta).addScalar(
					"gerenciaRegional", Hibernate.STRING).addScalar(
					"unidadeNegocio", Hibernate.STRING).addScalar("localidade",
					Hibernate.STRING)
					.addScalar("contadorCpf", Hibernate.DOUBLE).addScalar(
							"contadorCnpj", Hibernate.DOUBLE).addScalar(
							"contador", Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1124] Gerar Relatório de Alterações de CPF/CNPJ por Meio de
	 * Solicitação
	 * 
	 * @author Mariana Victor
	 * @date 16/02/2011
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpjPorMeio(
			GerarRelatorioAlteracoesCpfCnpjHelper helper)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = new ArrayList();
		Session session = HibernateUtil.getSession();
		Query query = null;
		Map parameters = new HashMap();
		String consulta;

		try {
			consulta = " SELECT meso.meso_dsmeiosolicitacao AS meio, "
					+ " count(CASE WHEN tbca.tbco_id = 271 THEN tbca.tbca_id END) AS contadorCpf, "
					+ " count(CASE WHEN tbca.tbco_id = 275 THEN tbca.tbca_id END) AS contadorCnpj, "
					+ " count(tbca.tbca_id) AS contador "
					+ " FROM seguranca.tab_linha_col_alteracao tbca "
					+ " INNER JOIN seguranca.tabela_linha_alteracao tbla ON tbla.tbla_id = tbca.tbla_id "
					+ " INNER JOIN seguranca.usuario_alteracao usat ON usat.tref_id = tbla.tref_id "
					+ " INNER JOIN seguranca.usuario usur ON  usur.usur_id = usat.usis_id "
					+ " INNER JOIN cadastro.unidade_organizacional unid ON usur.unid_id = unid.unid_id "
					+ " INNER JOIN atendimentopublico.meio_solicitacao meso ON unid.meso_id = meso.meso_id "
					+ " WHERE (tbca.tbco_id = 271 OR tbca.tbco_id = 275) "
					+ "  AND to_date(to_char(tbca.tbca_tmultimaalteracao,'YYYY/MM/DD'),'YYYY/MM/DD')"
					+ "  between :periodoInicial and :periodoFinal" + " and ";

			if (Util.isCampoComboboxMultiploInformado(helper.getColecaoMeio())) {
				consulta += " meso.meso_id in ( ";
				String[] colecaoMeio = helper.getColecaoMeio();
				for (int i = 0; i < colecaoMeio.length; i++) {
					consulta = consulta + colecaoMeio[i] + ",";
				}
				consulta = consulta.substring(0, (consulta.length() - 1));
				consulta = consulta + ") and ";
			}

			// Remove o ultimo "AND "
			consulta = Util.removerUltimosCaracteres(consulta, 4);

			consulta += " GROUP BY meso.meso_dsmeiosolicitacao ";
			consulta += " ORDER BY meso.meso_dsmeiosolicitacao ";

			query = session.createSQLQuery(consulta).addScalar("meio",
					Hibernate.STRING)
					.addScalar("contadorCpf", Hibernate.DOUBLE).addScalar(
							"contadorCnpj", Hibernate.DOUBLE).addScalar(
							"contador", Hibernate.DOUBLE).setTimestamp(
							"periodoInicial",
							Util.converteStringParaDate(helper
									.getPeriodoInicial())).setTimestamp(
							"periodoFinal",
							Util.converteStringParaDate(helper
									.getPeriodoFinal()));

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();

			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}

			retorno = query.list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * UC1162 – AUTORIZAR ALTERACAO INSCRICAO IMOVEL
	 * 
	 * @author Rodrigo Cabral
	 * @date 05/06/2011
	 */
	public Collection pesquisaImovelInscricaoAlterada(
			ImovelInscricaoAlteradaHelper helper)
			throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();

		Integer idLocalidade = helper.getIdLocalidade();
		Integer codigoSetorComercial = helper.getCodigoSetorComercial();
		String consulta = "";

		try {
			consulta = "SELECT count(imia.imov_id) as qtdImoveis, imia.qdra_idatual as idQuadra"
					+ " FROM cadastro.imovel_inscr_alterada imia "
					+ " INNER JOIN cadastro.setor_comercial stcm on stcm.stcm_id = imia.stcm_idatual "
					+ " WHERE imia.imia_icatualizado = :indicadorAtualizado "
					+ " AND imia.imia_icalteracaoexcluida = :alteracaoExcluida "
					+ " AND imia.imia_icerroalteracao is null "
					+ " AND imia.imia_icautorizado = :indicadorAutorizado "
					+ " AND imia.loca_idatual = :idLocalidade "
					+ " AND stcm.stcm_cdsetorcomercial = :codigoSetorComercial ";
					
					if ( helper.getIndicadorImovelAlteradoExcluido() != null ) {
						consulta = consulta + " AND imia.imia_icimovelexcluido = " + helper.getIndicadorImovelAlteradoExcluido();
					}
					
					consulta = consulta
					+ " GROUP BY imia.qdra_idatual "
					+ " ORDER BY imia.qdra_idatual, qtdImoveis";

			retorno = session.createSQLQuery(consulta)
					.addScalar("qtdImoveis",Hibernate.INTEGER)
					.addScalar("idQuadra", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("codigoSetorComercial", codigoSetorComercial)
					.setShort("indicadorAtualizado",ConstantesSistema.INDICADOR_USO_DESATIVO)
					.setShort("alteracaoExcluida",ConstantesSistema.INDICADOR_USO_DESATIVO)
					.setShort("indicadorAutorizado",ConstantesSistema.INDICADOR_USO_DESATIVO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorUnidadeNegocio(
			Integer idUnidadeNegocio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.id = :idUnidadeNegocio ";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idUnidadeNegocio", idUnidadeNegocio).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidade() throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.indicadorUso = :indicadorUso ";

			retorno = (Collection) session.createQuery(consulta).setShort(
					"indicadorUso", ConstantesSistema.SIM).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 24/03/2011
	 * 
	 * @throws ControladorException
	 */
	public TarifaSocialMotivoCarta pesquisarTarifaSocialMotivoCarta(
			Integer idTarifaSocialMotivoCarta) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		TarifaSocialMotivoCarta retorno = null;

		try {

			String consulta = "SELECT tsmc "
					+ " FROM TarifaSocialMotivoCarta as tsmc "
					+ " WHERE tsmc.id = :idTarifaSocialMotivoCarta ";

			retorno = (TarifaSocialMotivoCarta) session.createQuery(consulta)
					.setInteger("idTarifaSocialMotivoCarta",
							idTarifaSocialMotivoCarta).setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social
	 * 
	 * @author Vivianne Sousa
	 * @date 02/05/2011
	 * 
	 * @throws ControladorException
	 */
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(
			Integer idGerenciaRegional, Integer idUnidadeNegocio)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {

			String consulta = "SELECT DISTINCT(loca.id) "
					+ " FROM Localidade as loca "
					+ " WHERE loca.unidadeNegocio.gerenciaRegional.id = :idGerenciaRegional and "
					+ " loca.unidadeNegocio.id = :idUnidadeNegocio";

			retorno = (Collection) session.createQuery(consulta).setInteger(
					"idGerenciaRegional", idGerenciaRegional).setInteger(
					"idUnidadeNegocio", idUnidadeNegocio).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1170] Gerar Relatório Acesso ao SPC
	 * 
	 * @author: Diogo Peixoto
	 * @date: 06/05/2011
	 * 
	 * @param FiltrarRelatorioAcessoSPCHelper
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> filtrarRelatorioAcessoSPC(
			FiltrarRelatorioAcessoSPCHelper filtro)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		StringBuilder sb = new StringBuilder();
		Query query = null;

		try {

			sb.append(" SELECT ");
			sb.append(" usu.unidadeOrganizacional.id, "); // 0
			sb.append(" usu.unidadeOrganizacional.descricao, "); // 1
			sb.append(" receitaFederal.usuarioSolicitante.nomeUsuario, "); // 2
			sb.append(" receitaFederal.cpfCliente, "); // 3
			sb.append(" receitaFederal.cnpjCliente, "); // 4
			sb.append(" receitaFederal.nomeCliente, "); // 5
			sb.append(" receitaFederal.dataUltimaAlteracaoConsulta "); // 6
			sb.append(" FROM gsan.seguranca.ConsultarReceitaFederal receitaFederal ");
			sb.append(" INNER JOIN receitaFederal.usuarioSolicitante usu ");
			sb.append(" INNER JOIN usu.unidadeOrganizacional uni ");
			sb.append(" WHERE ");

			String joinWhere = "";
			if (filtro.getLoginUsuarioResponsavel() != null
					&& !filtro.getLoginUsuarioResponsavel().equals("")) {
				joinWhere = " usu.login = :loginUsuario AND ";
			}
			if (filtro.getIdUnidadaOrganizacional() != null
					&& filtro.getIdUnidadaOrganizacional() > 0) {
				joinWhere += " uni.id = :idUnidade AND ";
			}
			if (filtro.getReferenciaInicial() != null
					&& filtro.getReferenciaFinal() != null) {
				joinWhere += " receitaFederal.dataUltimaAlteracaoConsulta BETWEEN :dataInicio and :dataFim AND ";
			}
			joinWhere = Util.removerUltimosCaracteres(joinWhere, 4);
			sb.append(joinWhere);
			sb.append(" GROUP BY ");
			sb.append(" usu.unidadeOrganizacional.id, ");
			sb.append(" usu.unidadeOrganizacional.descricao, ");
			sb.append(" receitaFederal.dataUltimaAlteracaoConsulta, ");
			sb.append(" receitaFederal.cpfCliente, ");
			sb.append(" receitaFederal.cnpjCliente, ");
			sb.append(" receitaFederal.nomeCliente, ");
			sb.append(" receitaFederal.usuarioSolicitante.nomeUsuario ");

			sb.append(" ORDER BY ");
			sb.append("usu.unidadeOrganizacional.id, ");
			sb.append("receitaFederal.usuarioSolicitante.nomeUsuario ");

			query = session.createQuery(sb.toString());

			if (filtro.getLoginUsuarioResponsavel() != null
					&& !filtro.getLoginUsuarioResponsavel().equals("")) {
				query.setParameter("loginUsuario", filtro
						.getLoginUsuarioResponsavel());
			}
			if (filtro.getIdUnidadaOrganizacional() != null
					&& filtro.getIdUnidadaOrganizacional() > 0) {
				query.setParameter("idUnidade", filtro
						.getIdUnidadaOrganizacional());
			}
			if (filtro.getReferenciaInicial() != null
					&& filtro.getReferenciaFinal() != null) {
				query.setParameter("dataInicio", filtro.getReferenciaInicial());
				query.setParameter("dataFim", filtro.getReferenciaFinal());
			}
			retorno = (Collection<Object[]>) query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
     * Obtém a coleção de categorias.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterCategorias() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = "";
		
		consulta = "SELECT cat.id, cat.descricao"
		         + " FROM Categoria cat";
		
		try{
			retorno = session.createQuery(consulta).list();
		}
		 catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}
		
		return retorno;
		
	}
	
	/**
     * Obtém a coleção de perfis dos imóveis.
     * 
     * @author Hugo Azevedo
     * @date 22/06/2011
     * 
     * @throws ErroRepositorioException
     */
	
	public Collection obterPerfisImoveis() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection retorno = null;
		String consulta = "";
		
		consulta = "SELECT per.id,per.descricao"
			      +" FROM ImovelPerfil per"
			      +" WHERE per.indicadorUso = :indicador";
		
		try{
			retorno = session.createQuery(consulta)
							.setInteger("indicador",ConstantesSistema.INDICADOR_USO_ATIVO)
							.list();
		}
		 catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
		}		
		return retorno;
	}
	


	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarIdentificacaoUsuario(Integer idUsuario) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade " //0
				+ "  FROM seguranca.usuario "
				+ "  WHERE usur_id = :idUsuario ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idUsuario", idUsuario)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Consulta chamada pelo "[FS0010 – Validar Identificação do Usuário.]" 
	 * 
	 * @author Mariana Victor
	 * @data 21/06/2011
	 */
	public Boolean verificarUsuarioEmpresaComandoCobranca(Integer idUsuario, Integer idComando) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade "
				+ " FROM cadastro.unidade_organizacional unid "
				+ "   INNER JOIN cobranca.cmd_empr_cobr_conta cecc ON cecc.empr_id = unid.empr_id "
				+ "   INNER JOIN seguranca.usuario usur ON unid.unid_id = usur.unid_id "
				+ " WHERE usur.usur_id = :idUsuario "
				+ "   AND cecc.cecc_id = :idComando ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idUsuario", idUsuario)
				.setInteger("idComando", idComando)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}

	/**
	 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
	 * 
	 * Pesquisa o email da Empresa 
	 * 
	 * @author Mariana Victor
	 * @data 22/06/2011
	 */
	public String pesquisarEmailEmpresa(Integer idEmpresa) throws ErroRepositorioException {

		String retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT empr_dsemail AS email "
				+ " FROM cadastro.empresa "
				+ " WHERE empr_id = :idEmpresa ";
			
			retorno = (String) session.createSQLQuery(consulta)
				.addScalar("email", Hibernate.STRING)
				.setInteger("idEmpresa", idEmpresa)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	/**
	 * [UC34] Importância Logradouro
	 * 
	 * Atualiza a Importância do Logradouro
	 * 
	 * @author Fernanda Almeida
	 * @data 30/06/2011
	 */
	
	public void atualizarGrauImportancia(LogradouroBairro logradouroBairro, Integer grauImportancia)
			throws ErroRepositorioException {

				Session session = HibernateUtil.getSession();
				try {
					String update = "update LogradouroBairro "
							+ "set ospc_id = :grauImportancia, lgbr_tmultimaalteracao = :dataAtual "
							+ "where lgbr_id = :idLogradouro";
				
					session.createQuery(update).setInteger("grauImportancia",
							grauImportancia).setInteger("idLogradouro",
									logradouroBairro.getId()).setTimestamp("dataAtual", new Date()).
									executeUpdate();
				
				} catch (HibernateException e) {
					// levanta a exceção para a próxima camada
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				} finally {
					// fecha a sessão
					HibernateUtil.closeSession(session);
				}
			}
	

	/**
	 * [MA2011061013]
	 * 
	 * @author Paulo Diniz
	 * @date 02/07/2011
	 * 
	 * @param idImovel
	 * 
	 * @return HidrometroMovimentado
	 * @throws ErroRepositorioException
	 */
	public List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		List<HidrometroInstalacaoHistorico> retorno = null;
		
		try {
			
			Criteria crit = session.createCriteria(HidrometroInstalacaoHistorico.class);
			crit.setFetchMode("ligacaoAgua", FetchMode.JOIN);
			crit.add(Restrictions.eq("ligacaoAgua.id",idImovel.intValue()));
			retorno = (List<HidrometroInstalacaoHistorico>) crit.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC0588 / UC0589] Verifica existencia de DDD 
	 * 
	 * @author Nathalia Santos 
	 * @data 23/09/2011
	 */
	public Boolean verificarDdd(Short Ddd) throws ErroRepositorioException {
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT count(*) AS quantidade "
				+ " FROM cadastro.municipio "
				+ " WHERE muni_cdddd = :Ddd ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setShort("Ddd", Ddd)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * [UC0588] Inserir Leiturista
	 * 
	 * @author Nathalia Santos
	 * @data 03/10/2011
	 */
	public Boolean pesquisarFuncionarioOuCliente(Integer IdFuncionario, Integer IdCliente) throws ErroRepositorioException { 
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = " SELECT  count(*) AS quantidade " 
					+ " FROM micromedicao.leiturista";
					
			if (IdFuncionario != null) {
				consulta = consulta
					+ " WHERE func_id = :IdFuncionario";
			} else {
				consulta = consulta
						+ "  WHERE clie_id = :IdCliente";
			} 
			
			Query query = session.createSQLQuery(consulta)
					.addScalar("quantidade", Hibernate.INTEGER);
			
			if (IdFuncionario != null) {
				query.setInteger("IdFuncionario", IdFuncionario);
			} else {
				query.setInteger("IdCliente", IdCliente);
			} 
			
			retorno = (Integer) query
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		if (retorno != null
				&& retorno.compareTo(new Integer(0)) > 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Collection<Logradouro> pesquisarLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ErroRepositorioException{
		Collection<Logradouro> colLogradouros = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select log " +
					"from Logradouro log " +
					"where log.nome = :logradouroNome and " +
					"log.municipio.id = :idMunicipio";
					
			colLogradouros = session.createQuery(consulta)
					.setString("logradouroNome", logradouroNome)
					.setInteger("idMunicipio", idMunicipio)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
	
		if (!colLogradouros.isEmpty()){
			return colLogradouros;
		}else{
			return null;
		}
	}
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * Função utilizada para retornar os imóveis que contém o mesmo informado pelo usuário 
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Collection<Logradouro> filtrarLogradouroMesmoNome(String logradouroNome, Integer numeroPagina,
			Integer idMunicipio) 
			throws ErroRepositorioException{
		Collection<Logradouro> colLogradouros= null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select distinct log.id, log.nome, bairro.nome, muni.nome, cep.codigo " +
					"from Logradouro log " +
					"left join log.logradouroBairros logBai " +
					"left join logBai.bairro bairro " +
					"left join log.municipio muni " +
					"left join log.logradouroCeps logCep " +
					"left join logCep.cep cep " +
					"where log.nome = :logradouroNome and " +
					"log.municipio.id = :idMunicipio";
					
			colLogradouros = session.createQuery(consulta)
					.setString("logradouroNome", logradouroNome)
					.setInteger("idMunicipio", idMunicipio)
					.setFirstResult(10 * numeroPagina).setMaxResults(10)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return colLogradouros;
	}
	
	/**
	 * [UC0032] Inserir Logradouro - [UC0033] Manter Logradouro
	 * 
	 * Proposta: 05/10/2011 - Tiago Moreno - PE2011065447 - Verificar existência de Logradouro com mesmo nome
	 *
	 * [FS0012] - Verificar existência de Logradouro com mesmo nome
	 * 
	 * Método usado para retornar a quantidade de logradouros com o mesmo nome
	 * 
	 * @author Thúlio Araújo
	 * @since 10/10/2011
	 * @param logradouroNome
	 * @return Collection<Logradouro>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeLogradouroMesmoNome(String logradouroNome, Integer idMunicipio) 
			throws ErroRepositorioException{
		Integer colLogradouros= null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select distinct count(*) " +
					"from Logradouro log " +
					"left join log.logradouroBairros logBai " +
					"left join logBai.bairro bairro " +
					"left join log.municipio muni " +
					"left join log.logradouroCeps logCep " +
					"left join logCep.cep cep " +
					"where log.nome = :logradouroNome and " +
					"log.municipio.id = :idMunicipio";
					
			colLogradouros = (Integer) session.createQuery(consulta)
					.setString("logradouroNome", logradouroNome)
					.setInteger("idMunicipio", idMunicipio)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return colLogradouros;
	}

	
	/**
	 * Método que pesquisa uma 
	 * EmpresaCobrancaFaixa pelo id
	 * 
	 * @author Raimundo Martins
	 * @date 24/10/2011
	 * */
	public EmpresaCobrancaFaixa pesquisarEmpresaCobrancaFaixa(Integer idCobrancaFaixa) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try{
			
			return (EmpresaCobrancaFaixa) session.createQuery("SELECT emcf FROM EmpresaCobrancaFaixa emcf WHERE emcf.id = :idCobFaixa")
					.setInteger("idCobFaixa", idCobrancaFaixa)
					.uniqueResult();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Atualizar nome do usuario com id de funcionario igual ao informado 
	 * 
	 * @author Erivan Sousa
	 * @date 06/12/2011
	 * 
	 * @param idFuncionario
	 * @param nomeFuncionario
	 * 
	 * @throws ErroRepositorioException
	 */

	public void atualizarNomeUsuarioComIdFuncionario(Integer idFuncionario,	String nomeFuncionario)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE Usuario usur ");
		builder.append("SET usur.nomeUsuario = :nomeFuncionario ");
		builder.append("WHERE usur.funcionario.id = :funcId ");
		
		try{
			
			session.createQuery(builder.toString())
					.setString("nomeFuncionario", nomeFuncionario)
					.setInteger("funcId", idFuncionario)
					.executeUpdate();
			
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelCobrancaSituacao(Integer idImovel, Integer idCobrancaSituacao)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();		
		try{
			String sql = "UPDATE gsan.cadastro.imovel.ImovelCobrancaSituacao SET iscb_dtretiradacobranca = :data, " +
					" iscb_tmultimaalteracao = :dataAtual " +
					" WHERE imov_id = :idImovel AND iscb_dtretiradacobranca IS NULL AND cbst_id = :idCobrancaSituacao";
			
			session.createQuery(sql)
				.setInteger("idImovel", idImovel)
				.setDate("data", new Date())
				.setTimestamp("dataAtual", new Date())
				.setInteger("idCobrancaSituacao", idCobrancaSituacao)
				.executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que atualiza a situação do imovel
	 * 
	 * @author Raimundo Martins
	 * @date 16/12/2010
	 * */
	
	public void atualizarImovelSituacao(Integer idImovel)throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();		
		try{
			String sql = "UPDATE gsan.cadastro.imovel.Imovel SET cbst_id = null, imov_tmultimaalteracao = :dataAtual " +
					" WHERE imov_id = :idImovel ";
			session.createQuery(sql).setInteger("idImovel", idImovel).setDate("dataAtual", new Date()).executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
	 * 
	 * Método que pesquisa os dados de Motivo de Retirada Cobrança
	 * 
	 * @author Raimundo Martins
	 * @date 02/02/2012
	 * */
	public Collection<MotivoRetiradaCobranca> pesquisarDadosMotivoRetiradaCobranca() throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try{	
			String hql = "SELECT motivo FROM MotivoRetiradaCobranca motivo";
			return session.createQuery(hql).list();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		
		}
	}
	
	/**
	 * 
	 *	Pega o id do Tipo de Cliente, através do id do imóvel
	 * 
	 * @author Rodrigo Cabral
	 * @data 08/06/2012
	 */
	public Integer obterClienteTipoId(Integer idImovel) throws ErroRepositorioException {

		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
			consulta = " SELECT clie.cltp_id AS clienteTipoId "
				+ " FROM cadastro.cliente_imovel clim "
				+ " INNER JOIN cadastro.cliente clie ON clie.clie_id = clim.clie_id "	
				+ " WHERE clim.clim_dtrelacaofim is null "
				+ " AND clim.crtp_id = :clienteRelacaoTipo "
				+ " AND clim.imov_id = :idImovel ";
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("clienteTipoId", Hibernate.INTEGER)
				.setInteger("idImovel", idImovel)
				.setInteger("clienteRelacaoTipo", ClienteRelacaoTipo.RESPONSAVEL)
				.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0002] Inserir Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 20/08/2012
	 * 
	 */
	public ItemMovimentoProgramaEspecial pesquisarItemMovimentoProgramaEspecial(Integer idImovel, Integer idMovimento) throws ErroRepositorioException{
		
		ItemMovimentoProgramaEspecial retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
	
		try{	
		
			consulta = " select item " +
					   " from ItemMovimentoProgramaEspecial item " +
					   " where item.imovel.id = :idImovel " +
					   " and item.movimentoProgramaEspecial.id = :idMovimento";
			
			retorno = (ItemMovimentoProgramaEspecial) session.createQuery(consulta)
										 .setInteger("idImovel", idImovel)
										 .setInteger("idMovimento", idMovimento)
										 .setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0003] Suspender Imóveis em Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @date 22/08/2012
	 * 
	 */
	public ImovelProgramaEspecial obterImovelProgramaEspecial(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta;
		ImovelProgramaEspecial retorno = null;
	
		try{	
		
			consulta = " select ipe " +
					   " from ImovelProgramaEspecial ipe " +
					   " where ipe.imovel.id = :idImovel ";
					   
			
			retorno = (ImovelProgramaEspecial)session.createQuery(consulta)
										 .setInteger("idImovel", idImovel)
										 .setMaxResults(1).uniqueResult();
			
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [SB0005] Gerar Relatório do Movimento do Programa Especial
	 * 
	 * @author Hugo Azevedo
	 * @param form 
	 * @date 21/08/2012
	 * 
	 */
	public Collection obterDadosRelatorioMovimentoProgramaEspecial(Integer idMovimento) throws ErroRepositorioException{
		
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try{
			
			consulta = 
					" select  "+
					" loca.loca_id as idLocalidade, "+
					" stcm.stcm_cdsetorcomercial as codSetCom, "+
					" qdra.qdra_nnquadra as numQuadra, "+
					" imov.imov_nnlote as numLote, "+
					" imov.imov_nnsublote as numSublote, "+
					" imov.imov_id as matImovel, "+
					" pemi.pemi_icatualizacao as icAtualizacao "+
					" from cadastro.PROGR_ESPECIAL_MOV_ITEM pemi "+
					" inner join cadastro.imovel imov on pemi.imov_id = imov.imov_id "+
					" inner join cadastro.localidade loca on loca.loca_id = imov.loca_id "+
					" inner join cadastro.setor_comercial stcm on stcm.stcm_id = imov.stcm_id "+
					" inner join cadastro.quadra qdra on qdra.qdra_id = imov.qdra_id " +
					" where pemi.pemv_id = :idMovimento";
			
			retorno = session.createSQLQuery(consulta)
							 .addScalar("idLocalidade", Hibernate.STRING)
							 .addScalar("codSetCom", Hibernate.STRING)
							 .addScalar("numQuadra", Hibernate.STRING)
							 .addScalar("numLote", Hibernate.STRING)
							 .addScalar("numSublote", Hibernate.STRING)
							 .addScalar("matImovel", Hibernate.STRING)
							 .addScalar("icAtualizacao", Hibernate.STRING)
							 .setInteger("idMovimento", idMovimento)
							 .list();

			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	/**
	 * 
	 * [UC1367] Registrar Movimento do Programa Especial
	 * [FS0006] Verificar Arquivo Processado
	 * 
	 * @author Hugo Azevedo
	 * @param form 
	 * @date 21/08/2012
	 * 
	 */
	public Integer verificarProcessamentoArquivoMovimentoProgramaEspecial(String fileName) throws ErroRepositorioException{
		
		Integer retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try{
			
			consulta = " select count(*) as qtd " +
					   " from cadastro.PROGR_ESPECIAL_MOVIMENTO " +
					   " where pemv_nmarquivo = :fileName";
			
			retorno = (Integer)session.createSQLQuery(consulta)
													.addScalar("qtd", Hibernate.INTEGER)
													.setString("fileName", fileName)
													.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
		
	}
	
	/**
	 * [UC1309] Download nova versão Sistemas Android
	 * 
	 * @author Fernanda Almeida
	 * @date 23/04/2012
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] baixarNovaVersaoApk( Integer idSistemaAndroid ) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Object[] retorno = null;

		try {
			consulta = " select vsan_arquivopk as apk, " //0
					   + " vsan_nnversao as versao "//1
					   + " from cadastro.versao_sistemas_android vsa " 
					   + " INNER JOIN cadastro.sistema_android sa ON sa.sian_id = vsa.sian_id and vsa.sian_id = :idSistemaAndroid " 
					   + " order by replace( vsan_nnversao, '.', '' ) desc ";

			retorno =(Object[]) session.createSQLQuery(consulta).addScalar(
					"apk", Hibernate.BINARY).addScalar(
					"versao", Hibernate.STRING)
					.setInteger("idSistemaAndroid",idSistemaAndroid)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1373] Gerar Relatório Histórico Imóveis Programa Especial.
	 * 
	 * @author Jonathan Marcos
	 * @date 02/05/2013
	 *  
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioHistoriocoImoveisProgramaEspecial(
		FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper filtro) 
	throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Collection<Object[]> colecaoHistoriocoImoveisProgramaEspecial = null;
		
		String idImovel = filtro.getIdImovel();
		
		try {
			
			String pesquisar="SELECT " +
					  "ci.imov_id as matricula," +
					  "ci.loca_id as codigoLocalidade," +
					  "cst.stcm_cdsetorcomercial as codigoSetorComercial," +
					  "cq.qdra_nnquadra as numeroQuadra," +
					  "ci.imov_nnlote as numeroLote," +
					  "ci.imov_nnsublote as numeroSublote," +
					  "cc.clie_nmcliente as nome,"+
					  "cipe.impe_dtapresentacaodocumentos as dataApresentacao," +
					  "cipe.impe_amreferenciainicio as dataInicio," +
					  "cipe.impe_dtinclusao as dataInclusao," +
					  "us.usur_nmusuario as usuarioInclusao," +
					  "cipe.impe_amreferenciasaida as dataSaida," +
					  "cipe.impe_dtsuspensao as dataSuspensao," +
					  "us2.usur_nmusuario as usuarioSuspensao," +
					  "cipe.impe_icformasuspensao as formaSuspensao," +
					  "cipe.impe_nnbolsafamilia as numero " +
					  "FROM " +
					  "cadastro.imovel ci " +
					  "inner join cadastro.setor_comercial cst on (ci.stcm_id=cst.stcm_id) " +
					  "inner join cadastro.quadra cq on (ci.qdra_id=cq.qdra_id) " +
					  "inner join cadastro.cliente_imovel cci on (ci.imov_id=cci.imov_id) " +
					  "inner join cadastro.cliente cc on (cc.clie_id=cci.clie_id and cci.crtp_id=2) " +
					  "inner join cadastro.imovel_programa_especial cipe on (ci.imov_id=cipe.imov_id) " +
					  "inner join seguranca.usuario us on (us.usur_id=cipe.usur_idinclusao) " +
					  "left join seguranca.usuario us2 on (us2.usur_id=cipe.usur_idsuspensao) " +
					  "where " +
					  "ci.imov_id = "+idImovel+" "+
					  " and cci.clim_dtrelacaofim is null ";
					  
			pesquisar+="group by " +
					  "ci.imov_id," +
					  "ci.loca_id," +
					  "cst.stcm_cdsetorcomercial," +
					  "cq.qdra_nnquadra," +
					  "ci.imov_nnlote," +
					  "ci.imov_nnsublote," +
					  "cc.clie_nmcliente,"+
					  "cipe.impe_dtapresentacaodocumentos," +
					  "cipe.impe_amreferenciainicio," +
					  "cipe.impe_dtinclusao," +
					  "us.usur_nmusuario," +
					  "cipe.impe_amreferenciasaida," +
					  "cipe.impe_dtsuspensao," +
					  "us2.usur_nmusuario," +
					  "cipe.impe_icformasuspensao," +
					  "cipe.impe_nnbolsafamilia";
			
			colecaoHistoriocoImoveisProgramaEspecial = (Collection<Object[]>) session.createSQLQuery(pesquisar)
					.addScalar("matricula", Hibernate.STRING)
					.addScalar("codigoLocalidade", Hibernate.STRING)
					.addScalar("codigoSetorComercial", Hibernate.STRING)
					.addScalar("numeroQuadra", Hibernate.STRING)
					.addScalar("numeroLote", Hibernate.STRING)
					.addScalar("numeroSublote", Hibernate.STRING)
					.addScalar("nome", Hibernate.STRING)
					.addScalar("dataApresentacao", Hibernate.DATE)
					.addScalar("dataInicio", Hibernate.STRING)
					.addScalar("dataInclusao", Hibernate.DATE)
					.addScalar("usuarioInclusao", Hibernate.STRING)
					.addScalar("dataSaida", Hibernate.STRING)
					.addScalar("dataSuspensao", Hibernate.DATE)
					.addScalar("usuarioSuspensao", Hibernate.STRING)
					.addScalar("formaSuspensao", Hibernate.STRING)
					.addScalar("numero", Hibernate.STRING).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoHistoriocoImoveisProgramaEspecial;
		
		
	}
	
	
	/**
	 * 
	 * [UC1527] Inserir Ocorrência Operacional
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/07/2013
	 * 
	 */
	public Collection<Object> obterLocalidadesdoMunicipio(Integer idMunicipio) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta;
		Collection<Object> retorno = null;
	
		try{	
		
			consulta = " select loca.loca_id as idLocalidade, loca.loca_nmlocalidade as nomeLocalidade" 
					   +" from cadastro.localidade loca " 
					   + "where loca.muni_idprincipal = :idMunicipio "
					   + "and loca.loca_icuso = 1 "
					   + "order by loca.loca_nmlocalidade ";
					   
			
			retorno = (Collection<Object>)session.createSQLQuery(consulta)
										.addScalar("idLocalidade", Hibernate.INTEGER)
										.addScalar("nomeLocalidade", Hibernate.STRING)
										.setInteger("idMunicipio", idMunicipio)
										.list();
			
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	
	/**
	 * 
	 * [UC0738] Gerar Certidão Negativa por Imóvel
	 * [FS0006] Validar CPF/CNPJ         
	 * 
	 * @author Hugo Azevedo
	 * @date 07/03/2014
	 * 
	 */
	public ClienteImovel obterClienteImovel(Integer idImovel) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta;
		ClienteImovel retorno = null;
		try{	
		
			consulta = " select clim " +
					   " from ClienteImovel clim " +
					   " inner join fetch clim.cliente clie " +
					   " where clim.clienteRelacaoTipo.id = 2 " +
					   " and clim.dataFimRelacao is null " +
					   " and clim.imovel.id = :idImovel ";
					   
			retorno = (ClienteImovel)session.createQuery(consulta)
										 .setInteger("idImovel", idImovel)
										 .uniqueResult();
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}

/**
	 * @author Jonathan Marcos
	 * @date 11/02/2014
	 * RM10044
	 */
	public Short verificarClienteTipoProgramaEspecial(Integer idImovel)
		throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String scripSql;
		Short indicadorProgramaEspecial = null;
		
		try {
			
			scripSql = "SELECT ct.cltp_icprogramaespecial AS indicadorProgramaEspecial"+ 
					   " FROM cadastro.cliente_tipo ct"+
					   " INNER JOIN cadastro.cliente c ON (c.cltp_id = ct.cltp_id)"+
				 	   " INNER JOIN cadastro.cliente_imovel ci ON (c.clie_id = ci.clie_id)"+
					   " WHERE ci.imov_id = :idImovel"+
					   " AND ci.crtp_id = :indicadorNao "+
					   " AND ci.clim_dtrelacaofim IS NULL"+
					   " GROUP BY ct.cltp_icprogramaespecial";
			
			indicadorProgramaEspecial = (Short)session.createSQLQuery(scripSql)
					.addScalar("indicadorProgramaEspecial", Hibernate.SHORT)
					.setInteger("idImovel", idImovel)
					.setShort("indicadorNao", ConstantesSistema.NAO)
					.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return indicadorProgramaEspecial;
	}
	
	
	
	
	public Collection<Object[]> obterMensagemEmailFaturamentoCobrancaParaEnviar(String dataPrevistaEnvio,String dataLimiteEnvio) 
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Collection<Object[]> colecaoRetorno  = null;
		String scriptSql = "";
		
		try {
			
			scriptSql = "SELECT mefc.mefc_id AS id,"+
						"mefc.clie_id AS cliente,"+
						"mefc.pmse_id AS parametroMensagemSMSEmail,"+
						"mefc.cbac_id AS acaoCobranca,"+
						"mefc.mefc_dsmensagem AS descricaoMensagem,"+
						"mefc.mefc_dsemaildestino AS emailDestino,"+
						"mefc.mefc_dtprevistaenvio AS dataPrevisaoEnvio,"+
						"mefc.mefc_dtlimiteenvio  AS dataLimiteEnvio,"+
						"mefc.mefc_dtenvio AS dataEnvio,"+
						"mefc.mefc_qttentativasenvio AS quantidadeTentativasEnvio,"+
						"mefc.mefc_dtultimaalteracao AS ultimaAlteracao,"+
						"mefc.cnta_id AS conta,"+
						"mefc.mefc_amreferenciaconta AS anoMesReferenciaConta,"+
						"mefc.ftgr_id AS grupoFaturamento,"+
						"mefc.cacm_id AS cobrancaAcaoAtividadeComando,"+
						"mefc.cbdo_id AS cobrancaDocumento"+
						" FROM cadastro.MENSAGEM_EMAIL_FAT_COB mefc"+
						" WHERE mefc.MEFC_DTPREVISTAENVIO<=to_date(:dataPrevistaEnvio,'yyyy-mm-dd')"+
						" AND mefc.MEFC_DTLIMITEENVIO>=to_date(:dataLimiteEnvio,'yyyy-mm-dd')";
			
			colecaoRetorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("cliente", Hibernate.INTEGER)
					.addScalar("parametroMensagemSMSEmail", Hibernate.INTEGER)
					.addScalar("acaoCobranca", Hibernate.INTEGER)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("emailDestino", Hibernate.STRING)
					.addScalar("dataPrevisaoEnvio", Hibernate.DATE)
					.addScalar("dataLimiteEnvio", Hibernate.DATE)
					.addScalar("dataEnvio", Hibernate.DATE)
					.addScalar("quantidadeTentativasEnvio", Hibernate.INTEGER)
					.addScalar("ultimaAlteracao", Hibernate.TIMESTAMP)
					.addScalar("conta", Hibernate.INTEGER)
					.addScalar("anoMesReferenciaConta", Hibernate.INTEGER)
					.addScalar("grupoFaturamento", Hibernate.INTEGER)
					.addScalar("cobrancaAcaoAtividadeComando", Hibernate.INTEGER)
					.addScalar("cobrancaDocumento", Hibernate.INTEGER)
					.setString("dataPrevistaEnvio", dataPrevistaEnvio)
					.setString("dataLimiteEnvio", dataLimiteEnvio).list();
					
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return colecaoRetorno;
	}
	
	public Collection<Object[]> obterMensagemEmailFaturamentoCobrancaDataLimite(String dataCorrente) 
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Collection<Object[]> colecaoRetorno  = null;
		String scriptSql = "";
		
		try {
			
			scriptSql = "SELECT mefc.mefc_id AS id,"+
						"mefc.clie_id AS cliente,"+
						"mefc.pmse_id AS parametroMensagemSMSEmail,"+
						"mefc.cbac_id AS acaoCobranca,"+
						"mefc.mefc_dsmensagem AS descricaoMensagem,"+
						"mefc.mefc_dsemaildestino AS emailDestino,"+
						"mefc.mefc_dtprevistaenvio AS dataPrevisaoEnvio,"+
						"mefc.mefc_dtlimiteenvio  AS dataLimiteEnvio,"+
						"mefc.mefc_dtenvio AS dataEnvio,"+
						"mefc.mefc_qttentativasenvio AS quantidadeTentativasEnvio,"+
						"mefc.mefc_dtultimaalteracao AS ultimaAlteracao,"+
						"mefc.cnta_id AS conta,"+
						"mefc.mefc_amreferenciaconta AS anoMesReferenciaConta,"+
						"mefc.ftgr_id AS grupoFaturamento,"+
						"mefc.cacm_id AS cobrancaAcaoAtividadeComando,"+
						"mefc.cbdo_id AS cobrancaDocumento"+
						" FROM cadastro.MENSAGEM_EMAIL_FAT_COB mefc"+
						" WHERE mefc.MEFC_DTLIMITEENVIO<to_date(:dataCorrente,'yyyy-mm-dd')";
			
			colecaoRetorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("cliente", Hibernate.INTEGER)
					.addScalar("parametroMensagemSMSEmail", Hibernate.INTEGER)
					.addScalar("acaoCobranca", Hibernate.INTEGER)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("emailDestino", Hibernate.STRING)
					.addScalar("dataPrevisaoEnvio", Hibernate.DATE)
					.addScalar("dataLimiteEnvio", Hibernate.DATE)
					.addScalar("dataEnvio", Hibernate.DATE)
					.addScalar("quantidadeTentativasEnvio", Hibernate.INTEGER)
					.addScalar("ultimaAlteracao", Hibernate.TIMESTAMP)
					.addScalar("conta", Hibernate.INTEGER)
					.addScalar("anoMesReferenciaConta", Hibernate.INTEGER)
					.addScalar("grupoFaturamento", Hibernate.INTEGER)
					.addScalar("cobrancaAcaoAtividadeComando", Hibernate.INTEGER)
					.addScalar("cobrancaDocumento", Hibernate.INTEGER)
					.setString("dataCorrente", dataCorrente).list();
					
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return colecaoRetorno;
	}
	
	public Collection<Object> obterCobrancaAcaoAtividadeComandoEmail(String dataCorrente) 
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Collection<Object> colecaoRetorno  = null;
		String scriptSql = "";
		try {
			scriptSql+="SELECT MEFC.CACM_ID AS idCobrancaAcaoAtividadeComando"+
					   " FROM cadastro.MENSAGEM_EMAIL_FAT_COB mefc"+
					   " WHERE mefc.MEFC_DTPREVISTAENVIO<=to_date(:dataCorrente,'yyyy-mm-dd')"+
					   " AND mefc.MEFC_DTLIMITEENVIO>=to_date(:dataCorrente,'yyyy-mm-dd')"+
					   " AND MEFC.CACM_ID IS NOT NULL"+
					   " GROUP BY MEFC.CACM_ID";
			
			colecaoRetorno = (Collection<Object>)session.createSQLQuery(scriptSql)
					.addScalar("idCobrancaAcaoAtividadeComando", Hibernate.INTEGER)
					.setString("dataCorrente", dataCorrente)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoRetorno;
	}
	
	public Collection<Object[]> obterMensagemSMSFaturamentoCobrancaParaEnviar(String dataPrevistaEnvio,String dataLimiteEnvio,
			Integer codigoRetornoGerado) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Collection<Object[]> colecaoRetorno  = null;
		String scriptSql = "";
		try {
	
			scriptSql = "SELECT msfc.msfc_id AS id,"+
						"msfc.clie_id AS cliente,"+
						"msfc.pmse_id AS parametroMensagemSMSEmail,"+
						"msfc.cbac_id AS acaoCobranca,"+
						"msfc.msfc_dsmensagem AS descricaoMensagem,"+
						"msfc.msfc_nntelefonedestino AS numeroTelefoneDestino,"+
						"msfc.msfc_dtprevistaenvio AS dataPrevisaoEnvio,"+
						"msfc.msfc_dtlimiteenvio  AS dataLimiteEnvio,"+
						"msfc.msfc_dtenvio AS dataEnvio,"+
						"msfc.msfc_qttentativasenvio AS quantidadeTentativasEnvio,"+
						"msfc.msfc_dtultimaalteracao AS ultimaAlteracao,"+
						"msfc.cnta_id AS conta,"+
						"msfc.msfc_amreferenciaconta AS anoMesReferenciaConta,"+
						"msfc.ftgr_id AS grupoFaturamento,"+
						"msfc.cacm_id AS cobrancaAcaoAtividadeComando,"+
						"msfc.cbdo_id AS cobrancaDocumento,"+
						"msfc.smss_id AS numeroLote,"+
						"msfc.msfc_cdretorno AS codigoRetorno"+
						" FROM cadastro.MENSAGEM_SMS_FAT_COB msfc"+
						" WHERE msfc.MSFC_DTPREVISTAENVIO<=to_date(:dataPrevistaEnvio,'yyyy-mm-dd')"+
						" AND msfc.MSFC_DTLIMITEENVIO>=to_date(:dataLimiteEnvio,'yyyy-mm-dd')"+
						" AND msfc.msfc_cdretorno = :codigoRetornoGerado AND msfc.smss_id IS NULL ";
			
			colecaoRetorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("cliente", Hibernate.INTEGER)
					.addScalar("parametroMensagemSMSEmail", Hibernate.INTEGER)
					.addScalar("acaoCobranca", Hibernate.INTEGER)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("numeroTelefoneDestino", Hibernate.STRING)
					.addScalar("dataPrevisaoEnvio", Hibernate.DATE)
					.addScalar("dataLimiteEnvio", Hibernate.DATE)
					.addScalar("dataEnvio", Hibernate.DATE)
					.addScalar("quantidadeTentativasEnvio", Hibernate.INTEGER)
					.addScalar("ultimaAlteracao", Hibernate.DATE)
					.addScalar("conta", Hibernate.INTEGER)
					.addScalar("anoMesReferenciaConta", Hibernate.INTEGER)
					.addScalar("grupoFaturamento", Hibernate.INTEGER)
					.addScalar("cobrancaAcaoAtividadeComando", Hibernate.INTEGER)
					.addScalar("cobrancaDocumento", Hibernate.INTEGER)
					.addScalar("numeroLote", Hibernate.INTEGER)
					.addScalar("codigoRetorno", Hibernate.INTEGER)
					.setString("dataPrevistaEnvio", dataPrevistaEnvio)
					.setString("dataLimiteEnvio", dataLimiteEnvio)
					.setInteger("codigoRetornoGerado", codigoRetornoGerado)
					.list();
					
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return colecaoRetorno;
	}

	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [FS0001] - Gerar Lista de Lotes de SMS pendentes 
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer[]> pesquisarLotesSMSPendentes() throws ErroRepositorioException {

		Collection<Integer[]> retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "SELECT smss.smss_id as sequencialEnvio, smss.smss_tmenviolote as dataEnvio, count(*) as quantidade from cadastro.mensagem_sms_fat_cob msfc "
					+"INNER JOIN cadastro.sms_sequencia_envio smss ON smss.smss_id = msfc.smss_id "
					+"WHERE msfc.msfc_cdretorno = :TRANSMITIDO AND smss.smss_tmenviolote + interval '24 hours' < now() "
					+"GROUP BY smss.smss_id, smss.smss_tmenviolote ORDER BY smss.smss_id, smss.smss_tmenviolote ";

			
			retorno = session.createSQLQuery(consulta)
					.addScalar("sequencialEnvio", Hibernate.INTEGER)
					.addScalar("dataEnvio", Hibernate.TIMESTAMP)
					.addScalar("quantidade", Hibernate.INTEGER)
					.setInteger("TRANSMITIDO", MensagemSMSFaturamentoCobranca.TRANSMITIDO)
					.list();

		} catch (HibernateException e) {
			// levanta a excecao para a proxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessao
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0007] - Atualizar total SMS enviados 
	 * 
	 * @author Raphael Rossiter
	 * @date 07/08/2014
	 * 
	 * @param cobrancaAcaoAtividadeComando
	 * @throws ErroRepositorioException
	 */
	public void atualizarTotalSMSEnviado(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizar;

		try {
			
			atualizar = "UPDATE gsan.cobranca.CobrancaAcaoAtividadeComando "
						+ "SET cacm_tmultimaalteracao = :dataAtual, cacm_qtsmsenviados = (coalesce(cacm_qtsmsenviados,0) + 1) "
						+ "WHERE cacm_id = :idComando ";

			session.createQuery(atualizar)
					.setTimestamp("dataAtual",new Date())
					.setInteger("idComando", cobrancaAcaoAtividadeComando.getId())
					.executeUpdate();
		} 
		catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sessï¿½o
			HibernateUtil.closeSession(session);
		}
	}
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0006] - Atualizar contador de erro no envio por lote 
	 * 
	 * @author Raphael Rossiter
	 * @date 11/08/2014
	 * 
	 * @param smsSequenciaEnvio
	 * @throws ErroRepositorioException
	 */
	public void atualizarTotalSMSErro(SMSSequenciaEnvio smsSequenciaEnvio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizar;

		try {
			
			atualizar = "UPDATE gsan.cadastro.SMSSequenciaEnvio "
						+ "SET smss_tmultimaalteracao = :dataAtual, smss_qterroenvio = (coalesce(smss_qterroenvio,0) + 1) "
						+ "WHERE smss_id = :smsSequenciaEnvio ";

			session.createQuery(atualizar)
					.setTimestamp("dataAtual",new Date())
					.setInteger("smsSequenciaEnvio", smsSequenciaEnvio.getId())
					.executeUpdate();
		} 
		catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sessï¿½o
			HibernateUtil.closeSession(session);
		}
	}
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0005] - Atualizar Tentativas de envio SMS 
	 * 
	 * @author Raphael Rossiter
	 * @date 11/08/2014
	 * 
	 * @param mensagemSMSFaturamentoCobranca
	 * @throws ErroRepositorioException
	 */
	public void atualizarTotalSMSTentativa(MensagemSMSFaturamentoCobranca mensagemSMSFaturamentoCobranca) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizar;

		try {
			
			atualizar = "UPDATE gsan.cadastro.MensagemSMSFaturamentoCobranca "
						+ "SET msfc_dtultimaalteracao = :dataAtual, msfc_qttentativasenvio = (coalesce(msfc_qttentativasenvio,0) + 1), msfc_cdretorno = :ERRO_OPERADORA "
						+ "WHERE msfc_id = :idMensagemSMSFaturamentoCobranca ";

			session.createQuery(atualizar)
					.setTimestamp("dataAtual",new Date())
					.setInteger("ERRO_OPERADORA", MensagemSMSFaturamentoCobranca.DOC_GERADO)
					.setInteger("idMensagemSMSFaturamentoCobranca", mensagemSMSFaturamentoCobranca.getId())
					.executeUpdate();
		} 
		catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sessï¿½o
			HibernateUtil.closeSession(session);
		}
	}
	
	public Collection<Object[]> obterMensagemSMSFaturamentoCobrancaDataLimite(String dataCorrente) 
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Collection<Object[]> colecaoRetorno  = null;
		String scriptSql = "";
		try {
			scriptSql = "SELECT msfc.msfc_id AS id,"+
					"msfc.clie_id AS cliente,"+
					"msfc.pmse_id AS parametroMensagemSMSEmail,"+
					"msfc.cbac_id AS acaoCobranca,"+
					"msfc.msfc_dsmensagem AS descricaoMensagem,"+
					"msfc.msfc_nntelefonedestino AS numeroTelefoneDestino,"+
					"msfc.msfc_dtprevistaenvio AS dataPrevisaoEnvio,"+
					"msfc.msfc_dtlimiteenvio  AS dataLimiteEnvio,"+
					"msfc.msfc_dtenvio AS dataEnvio,"+
					"msfc.msfc_qttentativasenvio AS quantidadeTentativasEnvio,"+
					"msfc.msfc_dtultimaalteracao AS ultimaAlteracao,"+
					"msfc.cnta_id AS conta,"+
					"msfc.msfc_amreferenciaconta AS anoMesReferenciaConta,"+
					"msfc.ftgr_id AS grupoFaturamento,"+
					"msfc.cacm_id AS cobrancaAcaoAtividadeComando,"+
					"msfc.cbdo_id AS cobrancaDocumento,"+
					"msfc.smss_id AS numeroLote,"+
					"msfc.msfc_cdretorno AS codigoRetorno"+
					" FROM cadastro.MENSAGEM_SMS_FAT_COB msfc"+
					" WHERE msfc.MSFC_DTLIMITEENVIO<to_date(:dataCorrente,'yyyy-mm-dd')";

			colecaoRetorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("cliente", Hibernate.INTEGER)
				.addScalar("parametroMensagemSMSEmail", Hibernate.INTEGER)
				.addScalar("acaoCobranca", Hibernate.INTEGER)
				.addScalar("descricaoMensagem", Hibernate.STRING)
				.addScalar("numeroTelefoneDestino", Hibernate.STRING)
				.addScalar("dataPrevisaoEnvio", Hibernate.DATE)
				.addScalar("dataLimiteEnvio", Hibernate.DATE)
				.addScalar("dataEnvio", Hibernate.DATE)
				.addScalar("quantidadeTentativasEnvio", Hibernate.INTEGER)
				.addScalar("ultimaAlteracao", Hibernate.DATE)
				.addScalar("conta", Hibernate.INTEGER)
				.addScalar("anoMesReferenciaConta", Hibernate.INTEGER)
				.addScalar("grupoFaturamento", Hibernate.INTEGER)
				.addScalar("cobrancaAcaoAtividadeComando", Hibernate.INTEGER)
				.addScalar("cobrancaDocumento", Hibernate.INTEGER)
				.addScalar("numeroLote", Hibernate.INTEGER)
				.addScalar("codigoRetorno", Hibernate.INTEGER)
				.setString("dataCorrente", dataCorrente)
				.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoRetorno;
	}
	
	public Integer obterQuantidadeSMSGerados(String dataPrevistaEnvio,String dataLimiteEnvio,Integer codigoRetornoGerado) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		Integer quantidadeRetorno = 0;
		String scriptSQL = null;
		try {
			scriptSQL="SELECT COUNT(*) AS quantidade"+
					  " FROM cadastro.mensagem_sms_fat_cob msfc"+
					  " WHERE msfc.MSFC_DTPREVISTAENVIO <= to_date(:dataPrevistaEnvio,'yyyy-mm-dd')"+
					  " AND msfc.MSFC_DTLIMITEENVIO >= to_date(:dataLimiteEnvio,'yyyy-mm-dd')"+
					  " AND msfc_cdretorno = :codigoRetornoGerado AND msfc.smss_id IS NULL ";
			
			quantidadeRetorno = (Integer) session.createSQLQuery(scriptSQL)
								.addScalar("quantidade", Hibernate.INTEGER)
								.setString("dataPrevistaEnvio", dataPrevistaEnvio)
								.setString("dataLimiteEnvio", dataLimiteEnvio)
								.setInteger("codigoRetornoGerado", codigoRetornoGerado)
								.uniqueResult();
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return quantidadeRetorno;
	}
	
	/*
	 * [UC1619] - Processar Retorno do SMS
	 * 
	 * [SB0005] - Atualizar Tentativas de envio SMS 
	 * 
	 * @author Raphael Rossiter
	 * @date 11/08/2014
	 * 
	 * @param mensagemSMSFaturamentoCobranca
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterMensagemSMSFaturamentoCobrancaParaRenviar(String dataPrevistaEnvio,String dataLimiteEnvio,
		Integer codigoRetornoGerado) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		Collection<Object[]> colecaoRetorno  = null;
		String scriptSql = "";
		try {
	
			scriptSql = "SELECT msfc.msfc_id AS id,"+
						"msfc.clie_id AS cliente,"+
						"msfc.pmse_id AS parametroMensagemSMSEmail,"+
						"msfc.cbac_id AS acaoCobranca,"+
						"msfc.msfc_dsmensagem AS descricaoMensagem,"+
						"msfc.msfc_nntelefonedestino AS numeroTelefoneDestino,"+
						"msfc.msfc_dtprevistaenvio AS dataPrevisaoEnvio,"+
						"msfc.msfc_dtlimiteenvio  AS dataLimiteEnvio,"+
						"msfc.msfc_dtenvio AS dataEnvio,"+
						"msfc.msfc_qttentativasenvio AS quantidadeTentativasEnvio,"+
						"msfc.msfc_dtultimaalteracao AS ultimaAlteracao,"+
						"msfc.cnta_id AS conta,"+
						"msfc.msfc_amreferenciaconta AS anoMesReferenciaConta,"+
						"msfc.ftgr_id AS grupoFaturamento,"+
						"msfc.cacm_id AS cobrancaAcaoAtividadeComando,"+
						"msfc.cbdo_id AS cobrancaDocumento,"+
						"msfc.smss_id AS numeroLote,"+
						"msfc.msfc_cdretorno AS codigoRetorno"+
						" FROM cadastro.MENSAGEM_SMS_FAT_COB msfc"+
						" INNER JOIN cadastro.sms_sequencia_envio smss ON smss.smss_id = msfc.smss_id "+
						" WHERE msfc.MSFC_DTPREVISTAENVIO<=to_date(:dataPrevistaEnvio,'yyyy-mm-dd')"+
						" AND msfc.MSFC_DTLIMITEENVIO>=to_date(:dataLimiteEnvio,'yyyy-mm-dd')"+
						" AND msfc.msfc_cdretorno = :codigoRetornoGerado "+
						" ORDER BY smss.smss_id ";
			
			colecaoRetorno = (Collection<Object[]>)session.createSQLQuery(scriptSql)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("cliente", Hibernate.INTEGER)
					.addScalar("parametroMensagemSMSEmail", Hibernate.INTEGER)
					.addScalar("acaoCobranca", Hibernate.INTEGER)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("numeroTelefoneDestino", Hibernate.STRING)
					.addScalar("dataPrevisaoEnvio", Hibernate.DATE)
					.addScalar("dataLimiteEnvio", Hibernate.DATE)
					.addScalar("dataEnvio", Hibernate.DATE)
					.addScalar("quantidadeTentativasEnvio", Hibernate.INTEGER)
					.addScalar("ultimaAlteracao", Hibernate.DATE)
					.addScalar("conta", Hibernate.INTEGER)
					.addScalar("anoMesReferenciaConta", Hibernate.INTEGER)
					.addScalar("grupoFaturamento", Hibernate.INTEGER)
					.addScalar("cobrancaAcaoAtividadeComando", Hibernate.INTEGER)
					.addScalar("cobrancaDocumento", Hibernate.INTEGER)
					.addScalar("numeroLote", Hibernate.INTEGER)
					.addScalar("codigoRetorno", Hibernate.INTEGER)
					.setString("dataPrevistaEnvio", dataPrevistaEnvio)
					.setString("dataLimiteEnvio", dataLimiteEnvio)
					.setInteger("codigoRetornoGerado", codigoRetornoGerado)
					.list();
					
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		return colecaoRetorno;
	}
	
	/*
	 * [UC1618] - Enviar SMS em lote
	 * 
	 * @author Raphael Rossiter
	 * @date 26/08/2014
	 * 
	 * @param idSmsSequenciaEnvio
	 * @throws ErroRepositorioException
	 */
	public void atualizarDataEnvio(Integer idSmsSequenciaEnvio) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String atualizar;

		try {
			
			atualizar = "UPDATE gsan.cadastro.SMSSequenciaEnvio "
						+ "SET smss_tmenviolote = :dataAtual WHERE smss_id = :smsSequenciaEnvio ";

			session.createQuery(atualizar)
					.setTimestamp("dataAtual",new Date())
					.setInteger("smsSequenciaEnvio", idSmsSequenciaEnvio)
					.executeUpdate();
		} 
		catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			// fecha a sessï¿½o
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1618] - Enviar SMS em lote
	 * 
	 * @author Rodrigo Cabral
	 * @date 24/09/2014
	 * 
	 * @throws ErroRepositorioException
	 */
	public ContaEmpresaSMS recuperarDadosContaEmpresaSMS() throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta;
		ContaEmpresaSMS retorno = null;
		try{	
		
			consulta = " Select contaEmpresaSMS " +
					   " from ContaEmpresaSMS contaEmpresaSMS " +
					   " where contaEmpresaSMS.indicadorUso = :indicadorUso ";
					   
			retorno = (ContaEmpresaSMS)session.createQuery(consulta)
										 .setInteger("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
										 .uniqueResult();
			
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}	
		
		return retorno;
	}
	
	
	
	/**
	 * 
	 * [IT 0009 - Pesquisar Imóveis]
	 * 
	 * @author Diogo Luiz
	 * @date 26/08/2014
	 */
	public Collection<Object []> pesquisarImoveisRoteiroDispositivoMovel(String idLocalidade, 
			String codigoSetorComercial, Collection<Integer> quadrasSelecionadas, 
			Collection<Integer> colecaoLigacaoAguaSituacao, String clienteUsuario, String[] indicadorSituacaoImovel) throws ErroRepositorioException {
		
		Collection<Object []> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		boolean imoveisAtualizados = false;
		boolean imoveisNaoAtualizados = false;
		boolean imoveisRetornarCampo = false;
		
		if (indicadorSituacaoImovel != null && indicadorSituacaoImovel.length != 0) {
			for (int i = 0; i < indicadorSituacaoImovel.length; i++) {
			
				String ids = indicadorSituacaoImovel[i];
				
				if(ids.equals("1")){
					imoveisAtualizados = true;
				}else if(ids.equals("2")){
					imoveisNaoAtualizados = true;
				}else if(ids.equals("3")){
					imoveisRetornarCampo = true;
				}
			}
		}
		
		
		try{
			consulta = 	"Select distinct im.imov_id as idImovel, " //0
					 +	"loc.loca_id as localidade, " //1
					 +	"sc.stcm_cdsetorcomercial as codSetor, " //2
					 +	"qd.qdra_nnquadra as numQuadra, " //3
					 +	"im.imov_nnlote as numLote, " //4
					 +	"im.imov_nnsublote as subLote, " //5
					 +	"rt.rota_cdrota as codigoRota " //6
					 +	"FROM cadastro.imovel im "
					 +	"inner join cadastro.cliente_imovel ci on ci.imov_id = im.imov_id "
					 +	"inner join cadastro.cliente cl on ci.clie_id = cl.clie_id "
					 +	"inner join cadastro.localidade loc on im.loca_id = loc.loca_id "
					 +	"inner join cadastro.setor_comercial sc on sc.stcm_id = im.stcm_id "
					 +	"inner join cadastro.quadra qd on qd.qdra_id = im.qdra_id "
					 +	"inner join micromedicao.rota rt on rt.rota_id = qd.rota_id "
					 +	"inner join atendimentopublico.ligacao_agua_situacao last on im.last_id = last.last_id "
					 +	"where loc.loca_id = :idLocalidade "
					 +	"and sc.stcm_cdsetorcomercial = :codigoSetor "
					 +	"and im.imov_icexclusao = :indicadorExcluido ";
					 
					 
			 //RM10166 - adicionado por Vivianne Sousa - 15/04/2014
			 if (imoveisAtualizados && imoveisNaoAtualizados && imoveisRetornarCampo) {
				 //ATUALIZADOS / NÃO ATUALIZADOS / RETORNAR PARA CAMPO
				 consulta += "and (im.siac_id = 0 OR im.siac_id is null OR im.siac_id = 3)";
				 
			 }else if (imoveisAtualizados && imoveisNaoAtualizados) {
				 //ATUALIZADOS / NÃO ATUALIZADOS 	 
				 consulta += "and ((im.siac_id = 3) " 
						  +	 " or ((im.siac_id = 0 OR im.siac_id is null) "   
						  +	 "     and (not exists (SELECT imac_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm "  
						  +	 "                      WHERE imac_id = (SELECT max(imac_id) FROM atualizacaocadastral.imovel_atlz_cadastral_dm imac where imac_icdadosretorno = 1 and imac.imov_id = im.imov_id) "  
						  +	 "                      AND imac_cdsituacao = 2)) ))";
				 
			 }else if (imoveisAtualizados && imoveisRetornarCampo) {
				 //ATUALIZADOS / RETORNAR PARA CAMPO	
				 consulta += "and ((im.siac_id = 3)  "
						  +	 "or ((im.siac_id = 0 OR im.siac_id is null) "  
						  +	 "	  and (exists (SELECT imac_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm " 
                  		  +	 "				   WHERE imac_id = (SELECT max(imac_id) FROM atualizacaocadastral.imovel_atlz_cadastral_dm imac where imac_icdadosretorno = 1 and imac.imov_id = im.imov_id) " 
                    	  +	 "					 AND imac_cdsituacao = 2))))";
				 
			 }else if (imoveisNaoAtualizados && imoveisRetornarCampo) {
				 //NÃO ATUALIZADOS / RETORNAR PARA CAMPO
				 consulta += "and (im.siac_id = 0 OR im.siac_id is null) ";
				 
			 }else if (imoveisAtualizados) { 
				 //ATUALIZADOS - situacao = coletado
				 consulta += "and im.siac_id = 3 ";
				 
			 } else if (imoveisNaoAtualizados) {
				 //NÃO ATUALIZADOS
				 consulta += "and (im.siac_id = 0 OR im.siac_id is null) "  
						  +	 "and (not exists (SELECT imac_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm  "
				          +	 "                 WHERE imac_id = (SELECT max(imac_id) FROM atualizacaocadastral.imovel_atlz_cadastral_dm imac where imac_icdadosretorno = 1 and imac.imov_id = im.imov_id) " 
				          +	 " 					 AND imac_cdsituacao = 2)) ";
				 
			 } else if (imoveisRetornarCampo) {	 
				 //RETORNAR PARA CAMPO
				 consulta += "and (im.siac_id = 0 OR im.siac_id is null) " 
				          +  "and (exists (SELECT imac_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm " 
						  +	 "             WHERE imac_id = (SELECT max(imac_id) FROM atualizacaocadastral.imovel_atlz_cadastral_dm imac where imac_icdadosretorno = 1 and imac.imov_id = im.imov_id) " 
	                      +	 "				 AND imac_cdsituacao = 2)) ";
			 } 

			
			if (!Util.isVazioOrNulo(quadrasSelecionadas)) {
				consulta += 	"and qd.qdra_nnquadra IN (:quadrasSelecionadas) ";
			}
			
			if(!Util.isVazioOrNulo(colecaoLigacaoAguaSituacao)){
				consulta += "and last.last_id IN (:colecaoLigacaoAguaSituacao) ";
			}
			
			if(clienteUsuario != null && clienteUsuario.equals("1")){
				consulta += "and (cl.clie_nncpf is not null OR cl.clie_nncnpj is not null) and ci.crtp_id = :relacaoTipo and ci.clim_dtrelacaofim is null ";
			}else if(clienteUsuario != null && clienteUsuario.equals("2")){
				consulta += "and (cl.clie_nncpf is null AND cl.clie_nncnpj is null) and ci.crtp_id = :relacaoTipo and ci.clim_dtrelacaofim is null ";
			}else{
				consulta += "and ci.crtp_id = :relacaoTipo and ci.clim_dtrelacaofim is null ";
			}
			
			consulta += " order by loc.loca_id, sc.stcm_cdsetorcomercial, qd.qdra_nnquadra, im.imov_nnlote, im.imov_nnsublote ";
			
			if(!Util.isVazioOrNulo(colecaoLigacaoAguaSituacao) && !Util.isVazioOrNulo(quadrasSelecionadas)){
				retorno = (Collection<Object []>) session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("localidade", Hibernate.INTEGER)
						.addScalar("codSetor", Hibernate.INTEGER)
						.addScalar("numQuadra", Hibernate.INTEGER)
						.addScalar("numLote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("codigoRota", Hibernate.SHORT)
						.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
						.setInteger("codigoSetor", Integer.parseInt(codigoSetorComercial))
						.setInteger("relacaoTipo", ClienteRelacaoTipo.USUARIO.intValue())
						.setParameterList("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao)
						.setParameterList("quadrasSelecionadas", quadrasSelecionadas)
						.setShort("indicadorExcluido", ConstantesSistema.NAO)
						.list();
			}else if(!Util.isVazioOrNulo(colecaoLigacaoAguaSituacao) && Util.isVazioOrNulo(quadrasSelecionadas)){
				retorno = (Collection<Object []>) session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("localidade", Hibernate.INTEGER)
						.addScalar("codSetor", Hibernate.INTEGER)
						.addScalar("numQuadra", Hibernate.INTEGER)
						.addScalar("numLote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("codigoRota", Hibernate.SHORT)
						.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
						.setInteger("codigoSetor", Integer.parseInt(codigoSetorComercial))
						.setInteger("relacaoTipo", ClienteRelacaoTipo.USUARIO.intValue())
						.setParameterList("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao)
						.setShort("indicadorExcluido", ConstantesSistema.NAO)
						.list();
			}else if(Util.isVazioOrNulo(colecaoLigacaoAguaSituacao) && !Util.isVazioOrNulo(quadrasSelecionadas)){
				
				retorno = (Collection<Object []>) session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("localidade", Hibernate.INTEGER)
						.addScalar("codSetor", Hibernate.INTEGER)
						.addScalar("numQuadra", Hibernate.INTEGER)
						.addScalar("numLote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("codigoRota", Hibernate.SHORT)
						.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
						.setInteger("codigoSetor", Integer.parseInt(codigoSetorComercial))
						.setInteger("relacaoTipo", ClienteRelacaoTipo.USUARIO.intValue())
						.setParameterList("quadrasSelecionadas", quadrasSelecionadas)
						.setShort("indicadorExcluido", ConstantesSistema.NAO)
						.list();
			}else{
				retorno = (Collection<Object []>) session.createSQLQuery(consulta)
						.addScalar("idImovel", Hibernate.INTEGER)
						.addScalar("localidade", Hibernate.INTEGER)
						.addScalar("codSetor", Hibernate.INTEGER)
						.addScalar("numQuadra", Hibernate.INTEGER)
						.addScalar("numLote", Hibernate.SHORT)
						.addScalar("subLote", Hibernate.SHORT)
						.addScalar("codigoRota", Hibernate.SHORT)
						.setInteger("idLocalidade", Integer.parseInt(idLocalidade))
						.setInteger("codigoSetor", Integer.parseInt(codigoSetorComercial))
						.setInteger("relacaoTipo", ClienteRelacaoTipo.USUARIO.intValue())
						.setShort("indicadorExcluido", ConstantesSistema.NAO)
						.list();
			}
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 */
	public Collection<Object []> pesquisarBairrosImovel(Integer idLocalidade) throws ErroRepositorioException{
		Collection<Object []> colecaoBairros =  new ArrayList<Object[]>();
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = "select distinct bairro.bair_id as idBairro, " //0 
					 +	"bairro.bair_cdbairro as codigoBairro, " //1 
					 +	"bairro.bair_nmbairro as nomeBairro " //2
					 +	"FROM CADASTRO.bairro bairro "
					 +	"INNER JOIN CADASTRO.localidade localidade ON localidade.muni_idprincipal = bairro.muni_id "
					 +	"WHERE localidade.loca_id = :idLocalidade ";
			

				colecaoBairros = (Collection<Object[]>) session.createSQLQuery(consulta)
										.addScalar("idBairro", Hibernate.INTEGER)
										.addScalar("codigoBairro", Hibernate.INTEGER)
										.addScalar("nomeBairro", Hibernate.STRING)
										.setInteger("idLocalidade", idLocalidade)
										.list();
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoBairros;
	}
	
	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 */
	public Collection<Object []> pesquisarCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoCep = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct cep.cep_id as idCep, " //0
					 +	"cep.cep_cdcep as codigoCep " //1
					 +	"from cadastro.cep cep "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.cep_id = cep.cep_id "
					 +	"inner join CADASTRO.logradouro lo on lo.logr_id = lc.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = lo.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) "; 
			
			colecaoCep = (Collection<Object[]>) session.createSQLQuery(consulta)
													.addScalar("idCep", Hibernate.INTEGER)
													.addScalar("codigoCep", Hibernate.INTEGER)
													.setParameterList("colecaoBairro", colecaoBairros)
													.list();
													
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoCep;
	}
	
	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 */
	public Collection<Object []> pesquisarLogradouro(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoLogradouros = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct logr.logr_id as idLogradouro, " 
					 +	"logr.logr_nmlogradouro as descricaoLogradouro, " 
					 +	"logr.logr_nmpopular as descricaoPopular, "					 
					 +	"logr.muni_id as municipio, "
					 +	"logr.lgtp_id as tipo, "
					 +	"logr.lgtt_id as titulo "
					 +	"from CADASTRO.logradouro logr "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.logr_id = logr.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = logr.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) ";
			
			colecaoLogradouros = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idLogradouro", Hibernate.INTEGER)
															.addScalar("descricaoLogradouro", Hibernate.STRING)
															.addScalar("descricaoPopular", Hibernate.STRING)															
															.addScalar("municipio", Hibernate.INTEGER)
															.addScalar("tipo", Hibernate.INTEGER)
															.addScalar("titulo", Hibernate.INTEGER)
															.setParameterList("colecaoBairro", colecaoBairros)
															.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoLogradouros;
	}
	
	
	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 */
	public Collection<Object []> pesquisarLogradouroBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoLogradouros = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct lb.lgbr_id as idLogradouroBairro, " 
					 +	"lb.bair_id as idBairro, " 
					 +  "lb.logr_id as idLogradouro "
					 +	"from CADASTRO.logradouro logr "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.logr_id = logr.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = logr.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) ";
			
			colecaoLogradouros = (Collection<Object[]>) session.createSQLQuery(consulta)
															.addScalar("idLogradouroBairro", Hibernate.INTEGER)
															.addScalar("idBairro", Hibernate.INTEGER)
															.addScalar("idLogradouro", Hibernate.INTEGER)
															.setParameterList("colecaoBairro", colecaoBairros)
															.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoLogradouros;
	}
	
	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * @param colecaoBairros
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarLogradouroCepBairros(Collection<Integer> colecaoBairros) throws ErroRepositorioException{
		Collection<Object []> colecaoCep = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		
		try{
			consulta = 	"select distinct lc.lgcp_id as idLogradouroCep, " //0
					 +	"lc.cep_id as idCep, " //1
					 +  "lc.logr_id as idLogradouro " //3
					 +	"from cadastro.cep cep "
					 +	"inner join CADASTRO.logradouro_cep lc on lc.cep_id = cep.cep_id "
					 +	"inner join CADASTRO.logradouro lo on lo.logr_id = lc.logr_id "
					 +	"inner join CADASTRO.logradouro_bairro lb on lb.logr_id = lo.logr_id "
					 +	"where lb.bair_id in (:colecaoBairro) "; 
			
			colecaoCep = (Collection<Object[]>) session.createSQLQuery(consulta)
													.addScalar("idLogradouroCep", Hibernate.INTEGER)
													.addScalar("idCep", Hibernate.INTEGER)
													.addScalar("idLogradouro", Hibernate.INTEGER)
													.setParameterList("colecaoBairro", colecaoBairros)
													.list();
													
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return colecaoCep;
	}
	
	
	
	/**
	 * [UC 1391] - Gerar Roteiro Dispositivo Movel
	 * 
	 * [SB 0002] - Inserir Imóvel no Ambiente Virtual
	 * 
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterDadosImovelInsricaoResetorizada(Integer idImovel) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String consulta;
		Object[] retorno = null;

		Query query = null;
		
		try{
			consulta = 	"SELECT loca_id as idLocalidade, " //0
					 +	"imir_cdsetorcomercial as codigoSetorComercial, " //1
					 +	"imir_nnquadra as numeroQuadra, " //2
					 +	"imir_nnlote as numeroLote, " //3
					 +	"imir_nnsublote as numeroSublote " //4
					 +	"FROM cadastro.imovel_insc_resetorizada "
					 +	"WHERE imov_id = :idImovel "
					 +	"AND imir_tmultimaalteracao = "
					 +	"(SELECT max(imir_tmultimaalteracao) "
					 +	"FROM cadastro.imovel_insc_resetorizada "
					 +	"WHERE imov_id = :idImovel) ";
			
			query = session.createSQLQuery(consulta)
							.addScalar("idLocalidade", Hibernate.INTEGER)
							.addScalar("codigoSetorComercial", Hibernate.INTEGER)
							.addScalar("numeroQuadra", Hibernate.INTEGER)
							.addScalar("numeroLote", Hibernate.INTEGER)
							.addScalar("numeroSublote", Hibernate.INTEGER)
							.setInteger("idImovel", idImovel);
							
			retorno = (Object[]) query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1297] Pesquisar Imovel Subcategoria Atualização Cadastral
	 * 
	 * @author Diogo Luiz
	 * @since 27/08/2014
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarSubCategoriaAtualizacaoCadastral(
		Integer idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		
		Collection<ImovelSubcategoriaAtualizacaoCadastralDM> imovelSubcategoria = null;
		String consulta = "";
		
		Session session = HibernateUtil.getSession();
		
		try{
			consulta =  "Select scac "
					 +	"FROM ImovelSubcategoriaAtualizacaoCadastral scac "
					 +	"WHERE scac.imovelAtualizacaoCadastral.id = :idImovelAtualizacaoCadastral ";
			
			imovelSubcategoria = (Collection<ImovelSubcategoriaAtualizacaoCadastralDM>) session.createQuery(consulta)
									.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
									.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return imovelSubcategoria;
	}
	
	
	
	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Diogo Luiz
     * @date 27/08/2014
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralClienteUsuario(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException {
	
		Collection<ClienteAtualizacaoCadastralDM> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT clie"
				     + " FROM ClienteAtualizacaoCadastralDM clie" 				    				    
				     + " WHERE clie.imovelAtualizacaoCadastralDM.id = :imovelAtualizacaoCadastral"
				     + " and clie.clienteRelacaoTipo.id = :clienteUsuario";
			
			colecao = (Collection<ClienteAtualizacaoCadastralDM>)session.createQuery(consulta)
										.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.setInteger("clienteUsuario", ClienteRelacaoTipo.USUARIO)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;	
	}

	/**
	 * @author Diogo Luiz
	 * @date 27/08/2014
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosHidrometroInstalacaoHistorico(Integer idImovel) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		String consulta;
		Collection retorno = null;

		try {

			consulta = "select hidr.hidr_nnhidrometro numeroHidrometro, " 
					+"hidi.hidi_dtinstalacaohidrometro dataInstalacao, " 
					+"hidi.medt_id idMedicaoTipo, "
					+"hidi.hili_id idHidrometroLocalInstalacao, " 
					+"hidi.hipr_id idHidrometroProtecao," 
					+"hidi.hidi_nnleitinstalacaohidmt numeroLeitura, "
					+"hidi.hidi_iccavalete indicadorCavalete "
					+"from MICROMEDICAO.hidrometro_inst_hist hidi "
					+"inner join micromedicao.hidrometro hidr on hidr.hidr_id = hidi.hidr_id and hidi_dtretiradahidrometro is null "
					+"where lagu_id = :idImovel or imov_id = :idImovel";

			retorno = session.createSQLQuery(consulta)
					.addScalar("numeroHidrometro", Hibernate.STRING)
					.addScalar("dataInstalacao", Hibernate.DATE)
					.addScalar("idMedicaoTipo", Hibernate.INTEGER)
					.addScalar("idHidrometroLocalInstalacao", Hibernate.INTEGER)
					.addScalar("idHidrometroProtecao", Hibernate.INTEGER)
					.addScalar("numeroLeitura", Hibernate.INTEGER)
					.addScalar("indicadorCavalete", Hibernate.SHORT)
					.setInteger("idImovel", idImovel)
					.list();

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}
	
	
	
public Collection<Integer> pesquisarImoveisAtualizacaoCadastral(Integer idParametro) throws ErroRepositorioException{
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String consulta = ""
					
					+ "SELECT imovelAtualizacaoCadastral.imov_id as imovel FROM ATUALIZACAOCADASTRAL.IMOVEL_ATLZ_CADASTRAL_DM imovelAtualizacaoCadastral WHERE IMAC_ICDADOSRETORNO = 2 " +
					" AND imovelAtualizacaoCadastral.ptac_id = :idParametro ORDER BY imovel";
				
			retorno = (Collection<Integer>) 
					session.createSQLQuery(consulta)
					.addScalar("imovel",Hibernate.INTEGER)
					.setInteger("idParametro", idParametro)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}



/**
 * Pesquisa se o imovel ja foi transmitido do tablet pro pre-gsan
 * 
 * @author Arthur Carvalho
 * @since 20/04/2012
 * 
 * @param idImovel
 * @return Integer
 * @throws ErroRepositorioException
 */
public Integer pesquisarImovelAtlzCadastralJaTransmitido(Integer idImovel, Integer idComando)throws ErroRepositorioException{
	
	Integer quantidade = null;
	
	Session session = HibernateUtil.getSession();
	
	String consulta = "";
	
	try{
		consulta += "select count(imac.imac_id) quantidade "
				 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm imac " 
				 +	"where imac.imac_icdadosretorno in (1, 3) and imac.imov_id = :idImovel and imac.ptac_id = :idComando";
		
		quantidade = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("idImovel", idImovel)
				.setInteger("idComando", idComando)
				.uniqueResult();
		
		
	}catch(HibernateException ex){
		throw new ErroRepositorioException("Erro no Hibernate");
	}finally{
		HibernateUtil.closeSession(session);
	}
	return quantidade;
}

/**
 * Pesquisa se o imovel novo ja foi transmitido do tablet pro pre-gsan
 * 
 * @author Diogo Luiz
 * @since 04/09/2014
 * 
 * @param codigoImovel
 * @param idComando
 * 
 * @return Integer
 * @throws ErroRepositorioException
 */
public Integer pesquisarImovelAtlzCadastralNovoJaTransmitido(String codigoImovel, Integer idComando)
	throws ErroRepositorioException{
	
	Integer quantidade = null;
	
	Session session = HibernateUtil.getSession();
	
	String consulta = "";
	
	try{
		consulta += "select count(imac.imac_id) quantidade "
				 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm imac " 
				 +	"where imac.imac_icdadosretorno in (1, 3) and imac.imac_cdimovel = :codigoImovel and imac.ptac_id = :idComando";
		
		quantidade = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setString("codigoImovel", codigoImovel)
				.setInteger("idComando", idComando)
				.uniqueResult();
		
	}catch(HibernateException ex){
		throw new ErroRepositorioException("Erro no Hibernate");
	}finally{
		HibernateUtil.closeSession(session);
	}
	return quantidade;
}


/**
 * Metodo responsavel que verifica se o imovel contem todos os itens obrigatorios.
 * 
 * @param codigoImovelAtualizacaoCadastra
 * @return
 * @throws ErroRepositorioException
 */
public Integer pesquisarIntegridadeImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastra)throws ErroRepositorioException{
	
	Integer quantidade = null;
	
	Session session = HibernateUtil.getSession();
	
	String consulta = "";
	
	try{
		consulta += "select count(imac.imac_id) quantidade "
				 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm imac "
				 +  "inner join atualizacaocadastral.imovel_subca_atlz_cad_dm isac on isac.imac_id = imac.imac_id "
				 +  "inner join atualizacaocadastral.imovel_ocorr_atlz_cad_dm ioac on ioac.imac_id = imac.imac_id "
				 +	"where imac.imac_id = :codigo ";
		
		quantidade = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade", Hibernate.INTEGER)
				.setInteger("codigo", idImovelAtualizacaoCadastra)
				.uniqueResult();
		
		
	}catch(HibernateException ex){
		throw new ErroRepositorioException("Erro no Hibernate");
	}finally{
		HibernateUtil.closeSession(session);
	}
	return quantidade;
}

	/**
	 *  Metodo responsavel por remover todos os dados do imovel atualizacao cadastral, caso ocorra erro no carregamento
	 * @param codigoImovel
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosImovelAtualizacaoCadastral(Integer idImovelAtualizacaoCadastral)throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
	
		PreparedStatement st = null;
		try {
			Connection jdbcCon = session.connection();
			
			//verificar tabelas que ainda não foram mapeadas
	
			//cliente fone
			String deleteFone =  " DELETE FROM atualizacaocadastral.cliente_fone_atlz_cad_dm "
					+ "where clac_id in (select clac_id FROM atualizacaocadastral.cliente_atlz_cadastral_dm "
										+ "where imac_id in ("+idImovelAtualizacaoCadastral+" ) )";
			st = jdbcCon.prepareStatement(deleteFone);
			st.executeUpdate();
			
			st = null;	
			//cliente		
			String deleteCliente = "DELETE FROM atualizacaocadastral.cliente_atlz_cadastral_dm "
					+ " where imac_id in (select imac_id from atualizacaocadastral.imovel_atlz_cadastral_dm where imac_id = "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteCliente);
			st.executeUpdate();
			
			st = null;
			//subcategoria
			String deleteCategoria = "DELETE FROM atualizacaocadastral.imovel_subca_atlz_cad_dm "
					+ " where imac_id in ( "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteCategoria);
			st.executeUpdate();
			
			st = null;
			
			//hidrometro		
			String deleteHidrometro = "DELETE FROM atualizacaocadastral.hid_inst_hist_atl_cad_dm "
					+ "where  imac_id in ("+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteHidrometro);
			st.executeUpdate();
			
			st = null;
	
			//ocorrencia
			String deleteOcorrencia = "DELETE FROM atualizacaocadastral.imovel_ocorr_atlz_cad_dm "
					+ "where imac_id in ( "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteOcorrencia);
			st.executeUpdate();
			
			st = null;
			
			//foto
			String deleteFoto = "DELETE FROM atualizacaocadastral.imovel_foto_atlz_cad_dm "
					+ "where imac_id in ( "+idImovelAtualizacaoCadastral+" )";
			st = jdbcCon.prepareStatement(deleteFoto);
			st.executeUpdate();
			
			st = null;
	
			//imovel
			String deleteImovel = "DELETE FROM atualizacaocadastral.imovel_atlz_cadastral_dm "
					+ "WHERE imac_id = "+idImovelAtualizacaoCadastral;
			st = jdbcCon.prepareStatement(deleteImovel);
			st.executeUpdate();
			
			st = null;
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author Arthur Carvalho
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdImovelAtualizacaoCadastral(String codigo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select imac.id " + "from ImovelAtualizacaoCadastralDM imac "
					+ "where imac.codigoImovel = :codigo ";

			retorno = (Integer) session.createQuery(consulta)
					.setString("codigo", codigo)
					.setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * @author Diogo Luiz
	 * @param codigo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteAtualizacaoCadastral(String codigo) throws ErroRepositorioException {

		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select clac.id " + "from ClienteAtualizacaoCadastralDM clac "
					+ " inner join clac.imovelAtualizacaoCadastralDM imac "
					+ "where imac.codigoImovel = :codigo ";

			retorno = (Integer) session.createQuery(consulta)
					.setString("codigo", codigo)
					.setMaxResults(1)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método responsável por
	 * pesquisar os dados do
	 * banco extraido  do 
	 * tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarCepTablet(Connection connection) 
			throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(
					"SELECT c.CEP_CDUNICO,c.CEP_CDCEP FROM cep c"
					+ " INNER JOIN logradouro_cep lc ON lc.cep_id = c.cep_id"
					+ " WHERE lc.lgcp_icnovo = 1"
					+ " AND c.cep_icnovo = 1 "
					+ " AND c.cep_ictransmitido = 2");
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os logradouros do 
	 * banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarLogradouroTablet(Connection connection)
		throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(
					"SELECT logra.LOGR_ID," 
					+"logra.LOGR_NMLOGRADOURO," 
					+"logra.LOGR_NMPOPULAR," 
					+"logra.LOGR_NMLOTEAMENTO," 
					+"logra.MUNI_ID," 
					+"logra.LGTP_ID," 
					+"logra.LGTT_ID," 
					+"logra.LOGR_CDUNICO," 
					+"logra.LOGR_ICNOVO," 
					+"logra.LOGR_ICTRANSMITIDO" 
					+" FROM logradouro logra"
					+" INNER JOIN imovel_atlz_cadastral imov ON imov.logr_id = logra.logr_id"
					+" WHERE logra.logr_icnovo = 1"
					+" AND logra.logr_ictransmitido = 2"
					+" GROUP BY logra.logr_id");
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os logradouros bairros
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarLogradouroBairroTablet(Connection connection,Integer idLogradouroTablet)
		throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet =  connection.createStatement().
					executeQuery("SELECT BAIR_ID FROM logradouro_bairro WHERE logr_id = "+
							idLogradouroTablet);
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os logradouros ceps
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarLogradouroCepTablet(Connection connection)
		throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(
					"SELECT c.CEP_CDUNICO,l.LOGR_CDUNICO FROM logradouro_cep lc"
					+" INNER JOIN cep c ON lc.cep_id = c.cep_id"
					+" INNER JOIN logradouro l ON lc.logr_id = l.logr_id"
					+" INNER JOIN imovel_atlz_cadastral imov ON imov.logr_id = l.logr_id"
					+" WHERE lc.lgcp_icnovo = 1"
					+" AND lc.lgcp_ictransmitido = 2"
					+" GROUP BY c.cep_cdunico");
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os imoveis
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelAtualizacaoCadastralTablet(Connection connection)
		throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(
					"SELECT * FROM imovel_atlz_cadastral WHERE IMAC_ICFINALIZADO = 1 AND IMAC_ICTRANSMITIDO = 2 ");
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os clientes
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarClienteAtualizacaoCadastralTablet(Connection connection,Integer 
			idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(
					"SELECT * FROM cliente_atlz_cadastral WHERE IMAC_ID = "+idImovelAtualizacaoCadastral);
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os cliente fone
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarClienteFoneAtualizacaoCadastral(Connection connection,Integer 
			idClienteAtualizacaoCadastral) throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().executeQuery(
					"SELECT * FROM cliente_fone_atlz_cad WHERE CLAC_ID = "
					+idClienteAtualizacaoCadastral);
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os hidrometro instalação
	 * histórico
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastral(Connection connection,Integer
			idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().
					executeQuery(
							"SELECT * FROM HIDROM_INST_HIST_ATL_CAD WHERE IMAC_ID = "
							+idImovelAtualizacaoCadastral);
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os imovel subcategoria
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelSubcategoriaAtualizacaoCadastral(Connection connection,Integer
			idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().
					executeQuery(
							"SELECT * FROM imovel_subcatg_atlz_cad WHERE IMAC_ID = " +
							+idImovelAtualizacaoCadastral);
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os imovel ocorrência
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelOcorrenciaAtualizacaoCadastral(Connection connection,Integer
			idImovelAtualizacaoCadastral) throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().
					executeQuery(
							"SELECT * FROM imovel_ocorrencia WHERE IMAC_ID = "
							+idImovelAtualizacaoCadastral);
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}
	
	/**
	 * Método responsável por
	 * pesquisar os path das fotos
	 * dos imoveis atualizados 
	 * do banco extraido do tablet
	 * @author Jonathan Marcos
	 * @since 01/10/2014
	 * @param statement
	 * @return ResultSet
	 * @throws ErroRepositorioException
	 */
	public ResultSet pesquisarImovelFotoAtualizacaoCadastral(Connection connection)
		throws ErroRepositorioException{
		ResultSet resultSet = null;
		try {
			resultSet = connection.createStatement().
					executeQuery(
							"SELECT f.imac_id,iac.imac_integracaoid,f.foto_path,f.foto_tipo" 
							+" FROM imovel_atlz_cadastral iac"
							+" inner join foto f on f.imac_id=iac.imac_id" +
							" WHERE iac.IMAC_ICFINALIZADO = 1" +
							" AND iac.IMAC_ICTRANSMITIDO = 2");
		} catch (SQLException e) {
			resultSet = null;
		}
		return resultSet;
	}

	/**
	 * Método responsável pela pesquisa das ocorrências de cadastro de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<CadastroOcorrencia> obterOcorrenciasImovelAtualizacaoCadastralDM(Integer idImovel)
		throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		List<CadastroOcorrencia> listOcorrencia = new ArrayList<CadastroOcorrencia>();
		String scriptSql = "";
		List retorno;

		try {
			scriptSql = "SELECT"+
					    " co.COCR_ID AS idOcorrencia,"+
						" co.COCR_DSCADASTROOCORRENCIA AS descricaoOcorrencia"+
					    " FROM CADASTRO.CADASTRO_OCORRENCIA co"+
					    " INNER JOIN ATUALIZACAOCADASTRAL.IMOVEL_OCORR_ATLZ_CAD_DM ioac ON (co.cocr_id=ioac.cocr_id)"+
					    " WHERE ioac.imac_id = :idImovel";

			retorno = session.createSQLQuery(scriptSql)
					.addScalar("idOcorrencia", Hibernate.INTEGER)
					.addScalar("descricaoOcorrencia", Hibernate.STRING)
					.setInteger("idImovel", idImovel)
					.list();

			for (Object row : retorno) {
				Object[] objeto = (Object[]) row;
				CadastroOcorrencia ocorrencia = new CadastroOcorrencia();

				if(objeto[0]!=null){
					ocorrencia.setId((Integer)objeto[0]);
				}

				if(objeto[1]!=null){
					ocorrencia.setDescricao((String)objeto[1]);
				}

				listOcorrencia.add(ocorrencia);
			}

		} catch(HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return listOcorrencia;
	}

	/**
	 * Método responsável pela pesquisa dos clientes de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralDM(Integer idImovel)
		throws ErroRepositorioException {
		List<ClienteAtualizacaoCadastralDM> list = null;
		String consulta = "";

		Session session = HibernateUtil.getSession();

		try {
			consulta =  "SELECT clac "
					 +	"FROM ClienteAtualizacaoCadastralDM clac "
					 + " LEFT JOIN FETCH clac.orgaoExpedidorRG "
					 + " LEFT JOIN FETCH clac.unidadeFederacao "
					 + " LEFT JOIN FETCH clac.clienteTipo "
					 +	"WHERE clac.imovelAtualizacaoCadastralDM.id = :idImovel ";

			list = session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return list;
	}

	/**
	 * Método responsável pela pesquisa das subcategorias de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarSubCategoriaAtualizacaoCadastralDM(Integer idImovel)
		throws ErroRepositorioException {
		List<ImovelSubcategoriaAtualizacaoCadastralDM> list = null;
		String consulta = "";

		Session session = HibernateUtil.getSession();

		try{
			consulta =  "Select scac "
					 +	"FROM ImovelSubcategoriaAtualizacaoCadastralDM scac "
					 +	"WHERE scac.imovelAtualizacaoCadastralDM.id = :idImovel ";

			list = session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.list();

		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}

		return list;
	}

	/**
	 * Método responsável pela pesquisa dos hidrometrosInstalacao de imóvel
	 * de atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 01/10/2014
	 * @param idImovel
	 * @param medicaoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<HidrometroInstalacaoHistoricoAtualizacaoCadastralDM> pesquisarHidrometroInstalacaoHistoricoAtualizacaoCadastralDM(
		Integer idImovel, Integer medicaoTipo) throws ErroRepositorioException {
		List<HidrometroInstalacaoHistoricoAtualizacaoCadastralDM> list = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "SELECT hiac "
				+ " FROM HidrometroInstalacaoHistoricoAtualizacaoCadastralDM hiac "
				+ " LEFT JOIN FETCH hiac.hidrometroLocalInstalacao "
				+ " LEFT JOIN FETCH hiac.hidrometroProtecao "
				+ " LEFT JOIN FETCH hiac.imovelAtualizacaoCadastralDM "
				+ " LEFT JOIN FETCH hiac.medicaoTipo "
				+ " WHERE hiac.imovelAtualizacaoCadastralDM.id = :idImovel";

			if (medicaoTipo != null) {
				consulta += " and hiac.medicaoTipo =" + medicaoTipo;
			}

			list = session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return list;
	}

	/**
	 * [UC1297] Pesquisar Imovel Atualização Cadastral
	 * 
	 * @author Arthur Carvalho
	 * @since 01/03/2012
	 *
	 * @param numeroHidrometro
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Hidrometro pesquisarHidrometroPeloNumero(String numeroHidrometro) throws ErroRepositorioException {
		Hidrometro retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT hidr " 
					+ "FROM Hidrometro hidr "
					+ "WHERE hidr.numero =:numeroHidrometro";

			retorno = (Hidrometro) session.createQuery(consulta).setString(
					"numeroHidrometro", numeroHidrometro.toUpperCase()).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [SB0002] Atualizar Imóvel/Ligação de Água
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarImovel(Integer idImovel,
			Integer idHidrometroInstalacaoHistorico)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;

		try {
			update = "update gsan.cadastro.imovel.Imovel set "
					+ "hidi_id = :hidrometroInstalacaoHistorico, " 
					+ "imov_tmultimaalteracao = :dataAtual "
					+ "where imov_id = :imovelId ";

			session.createQuery(update)
			.setInteger("imovelId",idImovel)
			.setInteger("hidrometroInstalacaoHistorico", idHidrometroInstalacaoHistorico)
			.setDate("dataAtual", new Date()).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1292] Efetuar Instalação de Hidrômetro para Atualização Cadastral
	 * [SB0002] Atualizar Imóvel/Ligação de Água
	 * 
	 * @author André Miranda
	 * @since 08/10/2014
	 * 
	 * @param idImovel
	 * @param idHidrometroInstalacaoHistorico
	 * @throws ErroRepositorioException
	 */
	public void atualizarLigacaoAgua(Integer idImovel,
			Integer idHidrometroInstalacaoHistorico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;

		try {
			update = "update gsan.atendimentopublico.ligacaoagua.LigacaoAgua set "
					+ "hidi_id = :hidrometroInstalacaoHistorico, " 
					+ "lagu_tmultimaalteracao = :dataAtual "
					+ "where lagu_id = :imovelId ";

			session.createQuery(update)
			.setInteger("imovelId",idImovel)
			.setInteger("hidrometroInstalacaoHistorico", idHidrometroInstalacaoHistorico)
			.setTimestamp("dataAtual", new Date()).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}


	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * 
	 * @author André Miranda
	 * @since 10/10/2014
	 * 
	 * @param indicadorAtualizacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<ImovelAtualizacaoCadastralDM> pesquisarImovelAtualizacaoCadastralDM(
			Short indicadorAtualizacao, Short indicadorDadosRetorno, Integer idLocalidade,
			int quantidadeRegistros ) throws ErroRepositorioException {

		Collection<ImovelAtualizacaoCadastralDM> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
			consulta = " SELECT imac"
				     + " FROM ImovelAtualizacaoCadastralDM imac"
					 + " INNER JOIN FETCH imac.parametroTabelaAtualizacaoCadastralDM ptac "
				     + " WHERE"
				     + " imac.indicadorAtualizado = :indicadorAtualizacao "
				     + " and imac.indicadorDadosRetorno = :indicadorDadosRetorno"
				     + " and (imac.codigoSituacao <> :codigoSituacao or imac.codigoSituacao is null)"
				     + " and imac.indicadorRegistroExcluido = :indicadorRegistroExcluido";

			if(idLocalidade != null) {
				consulta += " and imac.localidade.id = :idLocalidade";
			}

			Query query = session.createQuery(consulta)
					.setShort("indicadorAtualizacao", indicadorAtualizacao)
					.setShort("indicadorDadosRetorno", indicadorDadosRetorno)
					.setInteger("codigoSituacao", ImovelAtualizacaoCadastralDM.SITUACAO_RETORNADO_PARA_CAMPO)
					.setShort("indicadorRegistroExcluido", ConstantesSistema.NAO)
					.setMaxResults(quantidadeRegistros);

			if(idLocalidade != null) {
				query.setInteger("idLocalidade", idLocalidade);
			}

			colecao = (Collection<ImovelAtualizacaoCadastralDM>) query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecao;
	}

	/**
	 * Pesquisar dados do Parametro da Atualização Cadastral
	 * 
	 * @author André Miranda
     * @since 13/10/2014
     * 
     * @return ParametroTabelaAtualizacaoCadastro
	 * @exception ErroRepositorioException
	 */
	public ParametroTabelaAtualizacaoCadastralDM pesquisarParametroTabelaAtualizacaoCadastroDM(Integer idParametro)
		throws ErroRepositorioException {
		ParametroTabelaAtualizacaoCadastralDM parametro = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = " SELECT ptac"
				     + " FROM ParametroTabelaAtualizacaoCadastroDM ptac" 				    				    
				     + " WHERE ptac.id = :idParametro";

			parametro = (ParametroTabelaAtualizacaoCadastralDM)
					session.createQuery(consulta)
					.setInteger("idParametro", idParametro)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return parametro;
	}

	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0008] Validar Cliente usuario do imovel
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param idImovelAtualizacaoCadastral
	 * @return cpfcnpj
	 * @throws ErroRepositorioException
	 */
	public String obterCpfCnpjClienteUsuarioAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
			throws ErroRepositorioException {
		String retorno = null;
		String consulta = "";
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select clac_nncpfcnpj cpfcnpj "
					+  " from atualizacaocadastral.cliente_atlz_cadastral_dm "
					+  " where imac_id = :idImovelAtualizacaoCadastral "
					+  " and crtp_id = :clienteUsuario ";

			retorno =  (String) session.createSQLQuery(consulta)
					.addScalar("cpfcnpj", Hibernate.STRING)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.setShort("clienteUsuario", ClienteRelacaoTipo.USUARIO)
					.setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisar dados do Retorno Atualização Cadastral
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @return RetornoAtualizacaoCadastral
	 * @exception ErroRepositorioException
	 */
	public Collection<RetornoAtualizacaoCadastralDM> pesquisarRetornoAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException {
		String consulta = "";
		Session session = HibernateUtil.getSession();
		Collection<RetornoAtualizacaoCadastralDM> colecao = null;
	
		try {
			consulta = " SELECT reat"
				     + " FROM RetornoAtualizacaoCadastralDM reat" 				    				    
				     + " WHERE reat.imovelAtualizacaoCadastralDM = :imovelAtualizacaoCadastral";
			
			colecao = (Collection<RetornoAtualizacaoCadastralDM>)
					session.createQuery(consulta)
					.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecao;
	}

	/**
	 * Pesquisar dados do Cliente Atualização Cadastral
	 * 
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author André Miranda
     * @date 13/10/2014 
	 * @exception ErroRepositorioException
	 */
	public Collection<ClienteAtualizacaoCadastralDM> pesquisarClienteAtualizacaoCadastralDM(Integer idImovel,
			Integer clienteRelacaoTipo ) throws ErroRepositorioException {
		String consulta = "";
		Session session = HibernateUtil.getSession();
		Collection<ClienteAtualizacaoCadastralDM> colecao = null;

		try {
			consulta = " SELECT clie"
				     + " FROM ClienteAtualizacaoCadastralDM clie" 				    				    
					 + " LEFT JOIN FETCH clie.unidadeFederacao "
				     + " WHERE clie.imovelAtualizacaoCadastralDM = :idImovel"; 

			if (clienteRelacaoTipo != null) {
				consulta += " and clie.clienteRelacaoTipo.id = " + clienteRelacaoTipo;
			}

			colecao = (Collection<ClienteAtualizacaoCadastralDM>)
					session.createQuery(consulta)
					.setInteger("idImovel", idImovel)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecao;
	}

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0003] Validar Atributo Economias
	 * 
	 * @author André Miranda
	 * @since 13/10/2014
	 * 
	 * @param idImovelAtlzCad
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Short obterQuantidadeEconomiaAtualizacaoCadastralDM(Integer idImovelAtlzCad) throws ErroRepositorioException {
		String consulta = "";
		Short retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select sum(isac_qteconomia) quantidadeEconomias "
					 + "from atualizacaocadastral.imovel_subca_atlz_cad_dm "
					 + "where imac_id = :idImovelAtualizacaoCadastral ";

			retorno =  (Short) session.createSQLQuery(consulta)
					.addScalar("quantidadeEconomias", Hibernate.SHORT)
					.setInteger("idImovelAtualizacaoCadastral", idImovelAtlzCad)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0001] Validar Atributo Categoria
	 * 
	 * @author André Miranda
	 * @since 14/10/2014
	 * 
	 * @param idImovelAtlzCad
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterCategoriaAtualizacaoCadastralDM(Integer idImovelAtlzCad) throws ErroRepositorioException {
		String consulta = "";
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select catg_id categoria"
					 + " from atualizacaocadastral.imovel_subca_atlz_cad_dm"
					 + " where imac_id = :idImovelAtlzCad ";

			retorno =  (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idImovelAtlzCad", idImovelAtlzCad)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0001] Validar Atributo Categoria
	 * 
	 * @author André Miranda
	 * @since 14/10/2014
	 * 
	 * @param idImovelAtlzCad
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterSubcategoriaAtualizacaoCadastral(
			Integer idImovelAtlzCad) throws ErroRepositorioException {
		String consulta = "";
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select scat_id subcategoria"
					+ " from atualizacaocadastral.imovel_subca_atlz_cad_dm"
					+ " where imac_id = :idImovelAtlzCad";

			retorno =  (Collection<Integer>) session.createSQLQuery(consulta)
					.addScalar("subcategoria", Hibernate.INTEGER)
					.setInteger("idImovelAtlzCad", idImovelAtlzCad)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Pesquisa o setor comercial passando como parametro o id da localidade e o
	 * codigo do setor
	 * 
	 * @param idLocalidade
	 * @param codigoSetor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public SetorComercial pesquisarSetorComercial(int idLocalidade,	Integer codigoSetor) throws ErroRepositorioException {
		String consulta;
		SetorComercial retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			consulta = "select new gsan.cadastro.localidade.SetorComercial(setorComercial.id, setorComercial.codigo,"
					+ "setorComercial.descricao) "
					+ "from gsan.cadastro.localidade.SetorComercial as setorComercial "
					+ "where setorComercial.localidade.id = :idLocalidade "
					+ "and setorComercial.indicadorUso = :icUso "
					+ "and setorComercial.codigo = :codigoSetor";

			retorno = (SetorComercial) session.createQuery(consulta)
					.setInteger("idLocalidade", idLocalidade)
					.setShort("icUso", ConstantesSistema.SIM)
					.setInteger("codigoSetor", codigoSetor)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	

	/**
	 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
	 * [SB0010] - Verificar Alteração do Cliente por Usuário da COMPESA
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idImovel
	 * @param dataEnvio
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean  verificarRegistroAtendimentoAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException {

		Integer retorno = 0;
		boolean existe = false;
		
		Session session = HibernateUtil.getSession();

		String consulta = "";

		consulta +="  SELECT COUNT(ra.rgat_id) contador " 
				+ " FROM atendimentopublico.registro_atendimento ra  "
				+ " INNER JOIN atendimentopublico.solicitacao_tipo_espec step on ra.step_id = step.step_id "
				+ " WHERE step.step_nncodigoconstante = :averbacao "
				+ " and ra.imov_id = :idImovel";

		try {
			
			retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("contador", Hibernate.INTEGER)
				.setInteger("averbacao", Integer.valueOf("560"))	
				.setInteger("idImovel", idImovel)
				.setMaxResults(1).uniqueResult();
			
			if (retorno != null && retorno.intValue() > 0 ) {
				existe = true;
			}
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return existe;
	}
	
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0020] Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection obterQtdeEconomiaPorCategoriaAtlzCadastral( 
			Integer idImovelAtualizacaoCadastralDM) throws ErroRepositorioException {

		Collection<ImovelAtualizacaoCadastralDM> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
			consulta = "select catg.id, catg.descricao, catg.consumoEstouro, "
					+ "catg.vezesMediaEstouro, sum(isac.quantidadeEconomias), isac.imovelAtualizacaoCadastralDM.id, "
					+ "catg.consumoAlto, catg.mediaBaixoConsumo, catg.vezesMediaAltoConsumo, "
					+ "catg.porcentagemMediaBaixoConsumo, catg.descricaoAbreviada, catg.numeroConsumoMaximoEc, "
					+ "catg.indicadorCobrancaAcrescimos, catg.fatorEconomias, catg.categoriaTipo.id, "
					+ "catg.categoriaTipo.descricao, catg.numeroConsumoMaximoEc, catg.quantidadeDiasPrimeiroFaturamento "
					+ "from ImovelSubcategoriaAtualizacaoCadastralDM isac "
					+ "inner join isac.subcategoria scat "
					+ "inner join scat.categoria catg "
					+ "inner join catg.categoriaTipo cgtp "
					+ "where isac.imovelAtualizacaoCadastralDM.id = :idImovelAtualizacaoCadastralDM "
					+ "group by catg.id, catg.descricao, catg.consumoEstouro, catg.vezesMediaEstouro, "
					+ "isac.imovelAtualizacaoCadastralDM.id, catg.consumoAlto, catg.mediaBaixoConsumo, catg.vezesMediaAltoConsumo, "
					+ "catg.porcentagemMediaBaixoConsumo,catg.descricaoAbreviada,catg.numeroConsumoMaximoEc, "
					+ "catg.indicadorCobrancaAcrescimos, catg.fatorEconomias, catg.categoriaTipo.id, catg.categoriaTipo.descricao, "
					+ "catg.quantidadeDiasPrimeiroFaturamento ";

			colecao = (Collection)session.createQuery(consulta)
							.setInteger("idImovelAtualizacaoCadastralDM", idImovelAtualizacaoCadastralDM) 
							.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecao;
	}
	
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0020] Calcular Valor de Água e/ou Esgoto
	 * 
	 * @author Vivianne Sousa
	 * @date 20/10/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection obterQtdeEconomiaPorSubcategoriaAtlzCadastral( 
			Integer idImovelAtualizacaoCadastralDM) throws ErroRepositorioException {

		Collection<ImovelAtualizacaoCadastralDM> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
			
			consulta = "select scat.id, scat.codigo, scat.descricao, "
					+ "sum(isac.quantidadeEconomias), scat.codigoTarifaSocial, "
					+ "scat.numeroFatorFiscalizacao, scat.indicadorTarifaConsumo, "
					+ "catg.id, catg.descricao, catg.fatorEconomias, "
					+ "scat.indicadorSazonalidade, catg.descricaoAbreviada, "
					+ "scat.descricaoAbreviada "
					+ "from ImovelSubcategoriaAtualizacaoCadastralDM isac "
					+ "inner join isac.subcategoria scat "
					+ "inner join scat.categoria catg "
//					+ "inner join scat.categoria   "
					+ "where isac.imovelAtualizacaoCadastralDM.id = :idImovelAtualizacaoCadastralDM "
					+ "group by scat.id, scat.codigo, scat.descricao, "
					+ "scat.codigoTarifaSocial, scat.numeroFatorFiscalizacao, "
					+ "scat.indicadorTarifaConsumo, catg.id, catg.descricao,"
					+ "isac.imovelAtualizacaoCadastralDM.id, catg.fatorEconomias, " 
					+ "scat.indicadorSazonalidade, catg.descricaoAbreviada,scat.descricaoAbreviada ";

			colecao = (Collection)session.createQuery(consulta)
							.setInteger("idImovelAtualizacaoCadastralDM", idImovelAtualizacaoCadastralDM) 
							.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return colecao;
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar nomes dos usuários<br>
	 * que são leituristas associados<br>
	 * a empresa
	 * @author Jonathan Marcos
	 * @since 21/10/2014
	 * @param idEmpresa
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<Usuario> pesquisarUsuarioAtualizacaoCadastral(Integer idEmpresa) 
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			consulta = " SELECT distinct usur " 
					+ " FROM Leiturista leit " 
					+ " INNER JOIN leit.usuario usur "
					+ " INNER JOIN leit.empresa empr "
					+ " WHERE empr.id = :idEmpresa " 
					+ " AND leit.indicadorAtualizacaoCadastral = :indicadorAtualizacaoCadastral "
					+ " ORDER BY usur.nomeUsuario ";
			
			retorno = (Collection) session.createQuery(consulta)
					.setShort("indicadorAtualizacaoCadastral", ConstantesSistema.SIM)
					.setInteger("idEmpresa", idEmpresa)
					.list();
		} catch (HibernateException e) {
			//levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			//fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	/**
	 * 
	 * @return ImovelSubcategoriaAtualizacaoCadastral
	 * 
	 * @author Arthur Carvalho
     * @date 12/03/2012
	 * @exception ErroRepositorioException
	 */
	public Collection<ImovelSubcategoriaAtualizacaoCadastralDM> pesquisarImovelSubcategoriaAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
		throws ErroRepositorioException {
	
		Collection<ImovelSubcategoriaAtualizacaoCadastralDM> result = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
	
			consulta = " SELECT isac"
				     + " FROM ImovelSubcategoriaAtualizacaoCadastralDM isac" 				    				    
				     + " WHERE isac.imovelAtualizacaoCadastralDM = :imovelAtualizacaoCadastral";
			
			result = (Collection<ImovelSubcategoriaAtualizacaoCadastralDM>)session.createQuery(consulta)
										.setInteger("imovelAtualizacaoCadastral", idImovelAtualizacaoCadastral)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return result;
	}
	
	/**
	 * [UC1297] Atualizar Dados Cadastrais para Imóveis Inconsistentes
	 * [SB0008] Identificar atributo
	 * 
	 * @author Bruno Sá Barreto
	 * @date 20/10/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeEconomiasImovelAtualizacaoCadastralDM(Integer idImovelAtualizacaoCadastral)
			throws ErroRepositorioException{

		Integer result = null;
		Session session = HibernateUtil.getSession();
	
		try {
			String consulta = "select sum(subcategoria.isac_qteconomia) as qtdEconomias " +
					"from atualizacaocadastral.imovel_subca_atlz_cad_dm subcategoria, atualizacaocadastral.imovel_atlz_cadastral_dm imovel " +
					"where imovel.imac_id = :idImovelAtlzCadastral and subcategoria.imac_id = imovel.imac_id;";
			
			result = (Integer) session.createSQLQuery(consulta)
					.addScalar("qtdEconomias", Hibernate.INTEGER)
					.setInteger("idImovelAtlzCadastral", idImovelAtualizacaoCadastral)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return result;
		
	}
	
	/**
	 * @author Jonathan Marcos
	 * @since 11/11/2014
	 * @param idImac
	 * @param codigoSetor
	 * @param idLocalidade
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndicadorRedeAguaAtualizacaoCadastralDM(Integer idImac,Integer codigoSetor,
			Integer idLocalidade) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String querySQL = "";
		Short indicadorRedeAgua = null;;
		try {
			querySQL +="SELECT q.qdra_icredeagua AS indicadorRedeAgua"+
					   " FROM cadastro.quadra q"+
							" INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm iac on (iac.imac_nnquadra=q.qdra_nnquadra)"+
							" INNER JOIN cadastro.setor_comercial sc on (q.stcm_id=sc.stcm_id)"+
							" INNER JOIN cadastro.localidade loc on (loc.loca_id=iac.loca_id and loc.loca_id=sc.loca_id)"+
					" WHERE iac.imac_id = :idImac"+
					" AND sc.stcm_cdsetorcomercial = :codigoSetor"+
					" AND iac.loca_id = :idLocalidade"+
					" GROUP BY q.qdra_icredeagua";
			indicadorRedeAgua = (Short)session.createSQLQuery(querySQL)
					.addScalar("indicadorRedeAgua", Hibernate.SHORT)
					.setInteger("idImac", idImac)
					.setInteger("codigoSetor", codigoSetor)
					.setInteger("idLocalidade", idLocalidade)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return indicadorRedeAgua;
	}
	
	/**
	 * [UC1290] Inserir ou Atualizar Imóvel Atualização Cadastral
	 * [SB0004] Atualizar Categoria/Subcategoria e Economia do Imóvel
	 * 
	 * Este método altera a subcategoria da chave composta do relacionamento de imovelsubcategoria
	 * 
	 * @author Bruno Sá Barreto
	 * @date 12/11/2014
	 *
	 * @throws ErroRepositorioException
	 */
	public void atualizarChaveSubcategoriaImovel(ImovelSubcategoriaPK comp_id,
			Integer idSubcategoriaAtlzCad) throws ErroRepositorioException{

		Session session = HibernateUtil.getSession();
		try {
			String query = "update ImovelSubcategoria "
					+ " SET scat_id = :idSubcategoriaAtlzCad " 
					+ " WHERE imov_id = :idImovel and scat_id = :idSubcategoriaGsan ";

			session.createQuery(query)
				.setInteger("idSubcategoriaAtlzCad", idSubcategoriaAtlzCad)
				.setInteger("idImovel", comp_id.getImovel().getId())
				.setInteger("idSubcategoriaGsan", comp_id.getSubcategoria().getId())
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/**
	 * @author Jonathan Marcos
	 * @since 11/11/2014
	 * @param idImac
	 * @param codigoSetor
	 * @param idLocalidade
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarIndicadorRedeEsgotoAtualizacaoCadastralDM(Integer idImac,Integer codigoSetor,
			Integer idLocalidade) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String querySQL = "";
		Short indicadorRedeAgua = null;;
		try {
			querySQL +="SELECT q.qdra_icredeesgoto AS indicadorRedeEsgoto"+
					   " FROM cadastro.quadra q"+
							" INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm iac on (iac.imac_nnquadra=q.qdra_nnquadra)"+
							" INNER JOIN cadastro.setor_comercial sc on (q.stcm_id=sc.stcm_id)"+
							" INNER JOIN cadastro.localidade loc on (loc.loca_id=iac.loca_id and loc.loca_id=sc.loca_id)"+
					" WHERE iac.imac_id = :idImac"+
					" AND sc.stcm_cdsetorcomercial = :codigoSetor"+
					" AND iac.loca_id = :idLocalidade"+
					" GROUP BY q.qdra_icredeesgoto";
			indicadorRedeAgua = (Short)session.createSQLQuery(querySQL)
					.addScalar("indicadorRedeEsgoto", Hibernate.SHORT)
					.setInteger("idImac", idImac)
					.setInteger("codigoSetor", codigoSetor)
					.setInteger("idLocalidade", idLocalidade)
					.uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return indicadorRedeAgua;
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral, Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = ""
					+ "SELECT logra.loac_id                       AS idLogradouro, " //0
					+ "       lograTipo.lgtp_id                   AS idTipo, " //1
					+ "       lograTipo.lgtp_dslogradourotipo     AS nomeTipo, " //2
					+ "       lograTitulo.lgtt_id                 AS idTitulo, " //3
					+ "       lograTitulo.lgtt_dslogradourotitulo AS nomeTitulo, " //4
					+ "       logra.loac_nmlogradouro             AS nomeLogradouro, " //5
					+ "       logra.loac_nmpopular                AS nomePopular, " //6
					+ "       municipio.muni_id                   AS idMunicipio, " //7
					+ "       municipio.muni_nmmunicipio          AS nomeMunicipio " //8
					+ "FROM   atualizacaocadastral.logradouro_atlz_cad_dm logra "
					+ "       left join cadastro.logradouro_tipo lograTipo "
					+ "              ON lograTipo.lgtp_id = logra.lgtp_id "
					+ "       left join cadastro.logradouro_titulo lograTitulo "
					+ "              ON lograTitulo.lgtt_id = logra.lgtt_id "
					+ "       left join cadastro.municipio municipio "
					+ "              ON logra.muni_id = municipio.muni_id "
					+ "WHERE  logra.loca_id = :idLocalidade "
					+ "       AND logra.empr_id = :idEmpresa ";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLogradouro",Hibernate.STRING)
					.addScalar("idTipo",Hibernate.STRING)
					.addScalar("nomeTipo",Hibernate.STRING)
					.addScalar("idTitulo",Hibernate.STRING)
					.addScalar("nomeTitulo",Hibernate.STRING)
					.addScalar("nomeLogradouro",Hibernate.STRING)
					.addScalar("nomePopular",Hibernate.STRING)
					.addScalar("idMunicipio",Hibernate.STRING)
					.addScalar("nomeMunicipio",Hibernate.STRING)
					.setString("idEmpresa", idEmpresa)
					.setString("idLocalidade", idLocalidade)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroBairroAtlzCad(String idLogradouro) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = ""
					+ "SELECT lograBairro.lbac_id AS idLograBairro, "
					+ "       lograbairro.bair_id AS idBairro, "
					+ "       bairro.bair_nmbairro AS nomeBairro "
					+ "FROM   atualizacaocadastral.log_bairro_atlz_cad_dm lograBairro "
					+ "       left join cadastro.bairro bairro "
					+ "              ON lograBairro.bair_id = bairro.bair_id "
					+ "WHERE  lograBairro.loac_id = :idLogradouro";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLograBairro",Hibernate.STRING)
					.addScalar("idBairro",Hibernate.STRING)
					.addScalar("nomeBairro",Hibernate.STRING)
					.setString("idLogradouro", idLogradouro)
					.list();			
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idLogradouro
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]>  pesquisarLogradouroCepAtlzCad(String idLogradouro) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {		
			String consulta = ""
					+ "SELECT lograCep.lcac_id AS idLograCep, "
					+ "       cep.ceac_id      AS idCep, "
					+ "       cep.ceac_cdcep   AS codCep "
					+ "FROM   atualizacaocadastral.log_cep_atlz_cad_dm lograCep "
					+ "       left join atualizacaocadastral.cep_atlz_cadastral_dm cep "
					+ "              ON cep.ceac_id = lograCep.ceac_id "
					+ "WHERE  lograCep.loac_id = :idLogradouro";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLograCep",Hibernate.STRING)
					.addScalar("idCep",Hibernate.STRING)
					.addScalar("codCep",Hibernate.STRING)
					.setString("idLogradouro", idLogradouro)
					.list();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1442] Inserir Novos Logradouros Atualização Cadastral
	 * IT010 - Pesquisar Imóveis associados ao Logradouro
	 * 
	 * @author Anderson Cabral
	 * @author Bruno Sá Barreto
	 * @since 27/12/2013
	 * 
	 * @param idLogradouro
	 * @return ArrayList<Integer>
	 * @throws ErroRepositorioException
	 */
	public ArrayList<Integer> pesquisarImovelAtualizacaoCadastralPorLogradouro(Integer idLogradouro ) throws ErroRepositorioException {
	
		ArrayList<Integer> colecao = null;
		String consulta = "";
	
		Session session = HibernateUtil.getSession();
	
		try {
			
			consulta =  " SELECT imov_id imoveID " +
						" FROM atualizacaocadastral.imovel_atlz_cadastral_dm imov " +
						" INNER JOIN atualizacaocadastral.logradouro_atlz_cad_dm logra ON logra.loac_cdlogradouro = imov.logr_id " +
						" WHERE logra.loac_id = :idLogradouro "; 
			
			colecao = (ArrayList<Integer>) session.createSQLQuery(consulta)
										.addScalar("imoveID", Hibernate.INTEGER)
										.setInteger("idLogradouro", idLogradouro)
										.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
	
			HibernateUtil.closeSession(session);
		}
	
		return colecao;
	
	}
	
	/**
	 * [UC1443] - Gerar Relatorio de Novos Logradouros
	 * 
	 * @author  Bruno Sá Barreto
	 * @since 15/03/2013
	 * 
	 * @param idEmpresa
	 * @param idLocalidade
	 * @param idsSelecionados
	 * @return  Collection<Object[]>
	 * @throws ControladorException
	 */
	public Collection<Object[]>  pesquisarLogradouroAtlzCad(String idEmpresa, String idLocalidade, String idsSelecionados) throws ErroRepositorioException{
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
			String consulta = ""
					+ "SELECT logra.loac_id                       AS idLogradouro, " //0
					+ "       lograTipo.lgtp_id                   AS idTipo, " //1
					+ "       lograTipo.lgtp_dslogradourotipo     AS nomeTipo, " //2
					+ "       lograTitulo.lgtt_id                 AS idTitulo, " //3
					+ "       lograTitulo.lgtt_dslogradourotitulo AS nomeTitulo, " //4
					+ "       logra.loac_nmlogradouro             AS nomeLogradouro, " //5
					+ "       logra.loac_nmpopular                AS nomePopular, " //6
					+ "       municipio.muni_id                   AS idMunicipio, " //7
					+ "       municipio.muni_nmmunicipio          AS nomeMunicipio " //8
					+ "FROM   atualizacaocadastral.logradouro_atlz_cad_dm logra "
					+ "       left join cadastro.logradouro_tipo lograTipo "
					+ "              ON lograTipo.lgtp_id = logra.lgtp_id "
					+ "       left join cadastro.logradouro_titulo lograTitulo "
					+ "              ON lograTitulo.lgtt_id = logra.lgtt_id "
					+ "       left join cadastro.municipio municipio "
					+ "              ON logra.muni_id = municipio.muni_id "
					+ "WHERE  logra.loca_id = :idLocalidade "
					+ "        AND logra.empr_id = :idEmpresa "
					+ " 	  AND logra.loac_id in("+idsSelecionados+")";
			
			retorno = (Collection<Object[]>) 
					session.createSQLQuery(consulta)
					.addScalar("idLogradouro",Hibernate.STRING)
					.addScalar("idTipo",Hibernate.STRING)
					.addScalar("nomeTipo",Hibernate.STRING)
					.addScalar("idTitulo",Hibernate.STRING)
					.addScalar("nomeTitulo",Hibernate.STRING)
					.addScalar("nomeLogradouro",Hibernate.STRING)
					.addScalar("nomePopular",Hibernate.STRING)
					.addScalar("idMunicipio",Hibernate.STRING)
					.addScalar("nomeMunicipio",Hibernate.STRING)
					.setString("idEmpresa", idEmpresa)
					.setString("idLocalidade", idLocalidade)
					.list();
					
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
		
	}
	
	/**
	 * [UC1442] Inserir Novos Logradouros Atualizacao Cadastral
	 * [FS0002] Verificar CEP Associado a Logradouro
	 * 
	 * @author Bruno Sá Barreto
	 * 
	 * @since 22/11/2014
	 * 
	 * @param codigoCep
	 * @return boolean 
	 */
	public boolean verificaSeExisteAssociacaoCepLogradouro(Integer codigo) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = "";
		boolean retorno = false;

		try {
			consulta = " select count(*) result "
					+ " from cadastro.logradouro_cep logradouroCep, cadastro.cep cep"
					+ " where logradouroCep.cep_id = cep.cep_id "
					+ " AND cep.cep_cdcep = :codigoCep ";

			int count = (Integer) session.createSQLQuery(consulta).addScalar("result", Hibernate.INTEGER).setInteger("codigoCep", codigo).setMaxResults(1).uniqueResult();
			if(count>0) retorno = true;
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 *	Pesquisa na base e retorna
	 * o objeto associado ao id passado
	 *  como parâmetro.
	 *   
	 * @author Bruno Sá Barreto
	 * @since 22/11/2014
	 *
	 * @return LogradouroBairro
	 * @param idLogradouroBairro
	 */
	public LogradouroBairro pesquisarLogradouroBairroPorId(Integer idLogradouroBairro)
			throws ErroRepositorioException {

		LogradouroBairro retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {
			consulta = "select logB from LogradouroBairro logB "
					+ "where logB.logradouro.id = :idLogradouroBairro";

			retorno = (LogradouroBairro) session.createQuery(consulta).setInteger(
					"idLogradouroBairro", idLogradouroBairro).setMaxResults(1)
					.uniqueResult();

			Hibernate.initialize(retorno.getBairro());
			Hibernate.initialize(retorno.getLogradouro().getMunicipio());
			Hibernate.initialize(retorno.getLogradouro().getMunicipio().getUnidadeFederacao());
			Hibernate.initialize(retorno.getLogradouro().getLogradouroTipo());
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC 1312] Gerar Resumo da Situação dos imóveis por cadastrador/analista
	 * 
	 * @author Cesar Medeiros
	 * @since 04/12/2014
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoSituacaoImoveisPorCadastradorAnalista(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta =  "select idLocalidade, nomeLocalidade, idCliente, nomeCliente, qtd1, qtd2, qtd3, qtd4, tipo from( "
					 +	"select imac.loca_id idLocalidade, loca.loca_nmlocalidade nomeLocalidade, " 
				//	 +	"cadt.cadt_id idCliente, cadt.cadt_nmcadastrador nomeCliente, "
					 +  "usurCad.usur_id idCliente, usurCad.usur_nmusuario nomeCliente, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 1 then raet.reat_id end)) qtd1, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 2 then raet.reat_id end)) qtd2, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 3 then raet.reat_id end)) qtd3, "
					 +	"0 qtd4, "
					 +	"case when usurCad.usur_id is not null then 'cad' end tipo "
					 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm imac "
					 +	"inner join cadastro.localidade loca on loca.loca_id = imac.loca_id "
					 +	"inner join cadastro.setor_comercial sec ON sec.loca_id = loca.loca_id and imac.imac_cdsetorcomercial = sec.stcm_cdsetorcomercial "
					 +	"inner join cadastro.quadra qdra ON qdra.stcm_id = sec.stcm_id "
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loca.uneg_id "
					 +	"left join atualizacaocadastral.retorno_atlz_cad_dm raet on raet.imac_id  = imac.imac_id "					 	
				//	 +	"left join cadastro.cadastrador cadt on cadt.cadt_id = imac.imac_cadastrador "
					 +  "left join seguranca.usuario usurCad on imac.imac_nmlogin = usurCad.usur_nmlogin "
					 +	"left join seguranca.usuario usur on usur.usur_id = raet.usur_id "
					 +	"where imac.empr_id = :idEmpresa " 
					 +	"and usurCad.usur_id is not null ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdUnidade() != null){
				consulta += "and uni.uneg_id = :idUnidade ";
				parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
			}

			
			if(helper.getIdLocalidadeInicial() != null ){
				consulta += "and imac.loca_id = :idLocalidadeInicial  ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				
			}
			
			if(helper.getIdSetorComercialInicial() != null ){
				consulta += "and imac.imac_cdsetorcomercial = :idSetorInicial ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
				
			}
			
			if(helper.getNumerosQuadras() != null && !helper.getNumerosQuadras().trim().equals("")){
				consulta += "and imac.imac_nnquadra in (" + helper.getNumerosQuadras() + ")";
				
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and usurCad.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and raet.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and raet.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			} 
			
			consulta += "group by imac.loca_id, loca.loca_nmlocalidade , usurCad.usur_id, usurCad.usur_nmusuario, "
					 +	"case when usurCad.usur_id is not null then 'cad' end ";
		
			
			consulta += "UNION ALL ";
			
			consulta += "select imac.loca_id localidade, loca.loca_nmlocalidade nomeLocalidade, usur.usur_id cliente, usur.usur_nmusuario nomeCliente, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 1 then raet.reat_id end)) qtd1, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 2 then raet.reat_id end)) qtd2, "
					 +	"count( distinct(case when raet.reat_cdopcaoalteracao = 3 then raet.reat_id end)) qtd3, "
					 +	"0 qtd4, "
					 +	"case when raet.usur_id is not null then 'ana' end tipo "
    				 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm imac "
    				 +	"inner join cadastro.localidade loca on loca.loca_id = imac.loca_id "
    				 +	"inner join cadastro.setor_comercial sec ON sec.loca_id = loca.loca_id "
					 +	"inner join cadastro.quadra qdra ON qdra.stcm_id = sec.stcm_id "
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loca.greg_id "
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loca.uneg_id "
    				 +	"left join atualizacaocadastral.retorno_atlz_cad_dm raet on raet.imac_id = imac.imac_id "
    				 +	"left join seguranca.usuario usur on usur.usur_id = raet.usur_id "
    				 +  "left join seguranca.usuario usurCad on imac.imac_nmlogin = usurCad.usur_nmlogin "
    				 +	"where imac.empr_id = :idEmpresa and usur.usur_id is not null ";
    				 
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdUnidade() != null){
				consulta += "and uni.uneg_id = :idUnidade ";
				parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
			}

			if(helper.getIdLocalidadeInicial() != null){
				consulta += "and imac.loca_id = :idLocalidadeInicial  ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				
			}
			
			if(helper.getIdSetorComercialInicial() != null ){
				consulta += "and imac.imac_cdsetorcomercial = :idSetorInicial  ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
		
			}
			
			if(helper.getNumerosQuadras() != null && !helper.getNumerosQuadras().trim().equals("")){
				consulta += "and imac.imac_nnquadra in (" + helper.getNumerosQuadras() + ")";
			
			}
			
			if(helper.getIdCadastrador() != null){
				consulta += "and usurCad.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and raet.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and raet.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta +=	"group by imac.loca_id, loca.loca_nmlocalidade,  usur.usur_id, usur.usur_nmusuario, "
					 +	"case when raet.usur_id is not null then 'ana' end "
					
					 +	") as subquery  order by idLocalidade, tipo, nomeCliente ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER) //0
					.addScalar("nomeLocalidade", Hibernate.STRING) //1
					.addScalar("idCliente", Hibernate.INTEGER) //2
					.addScalar("nomeCliente", Hibernate.STRING) //3
					.addScalar("qtd1", Hibernate.INTEGER) //4
					.addScalar("qtd2", Hibernate.INTEGER) //5
					.addScalar("qtd3", Hibernate.INTEGER) //6
					.addScalar("qtd4", Hibernate.INTEGER) //7
					.addScalar("tipo", Hibernate.STRING); //8
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}

	
	
	/**
	 * [UC 1313] Gerar Resumo Quantitativo de Mensagens Pendentes por Cadastrador
	 * 
	 * @author Cesar Medeiros
	 * @since 09/12/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentesCadastrador(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			consulta = 	"select loc.loca_id idLocalidade, loc.loca_nmlocalidade nomeLocalidade, "
//					 +	"cad.cadt_id idCadastrador, cad.cadt_nmcadastrador nomeCadastrador, "
					 +  "usur.usur_id idCadastrador, usur.usur_nmusuario nomeCadastrador, "
					 +	"matc.matc_dsmensagem descricaoMensagem, count( imac.imac_id) quantidade "
					 +	"from atualizacaocadastral.retorno_atlz_cad_dm ratc "
					 +	"inner join atualizacaocadastral.imovel_atlz_cadastral_dm imac on ratc.imac_id = imac.imac_id " 
					 +	"inner join atualizacaocadastral.mensagem_atlz_cad_dm matc on matc.matc_id = ratc.matc_id "
//					 +	"inner join cadastro.cadastrador cad on cad.cadt_id = imac.cadt_id "
					 +  "inner join seguranca.usuario usur on imac.imac_nmlogin = usur.usur_nmlogin "
					 +	"left join cadastro.imovel imo on imo.imov_id = imac.imov_id "
					 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id " 
					 +	"inner join cadastro.setor_comercial sec ON sec.stcm_id = imo.stcm_id " 
					 +	"inner join cadastro.quadra qdra ON qdra.qdra_id = imo.qdra_id " 
					 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loc.greg_id " 
					 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "
					 +	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO);
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and greg.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdUnidade() != null){
				consulta += "and uni.uneg_id = :idUnidade ";
				parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
			}

			
			if(helper.getIdLocalidadeInicial() != null){
				consulta += "and imac.loca_id = :idLocalidadeInicial ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				
			}
			
			if(helper.getIdSetorComercialInicial() != null ){
				consulta += "and imac.imac_cdsetorcomercial = :idSetorInicial ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
		
			}
			
			if(helper.getNumerosQuadras() != null && !helper.getNumerosQuadras().trim().equals("")){
				consulta += "and imac.imac_nnquadra in (" + helper.getNumerosQuadras() + ")";
			}
			
			if(helper.getIdCadastrador() != null){
//				consulta += "and imac.cadt_id = :idCadastrador ";
				consulta += "and usur.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			if(helper.getIdAnalista() != null){
				consulta += "and ratc.usur_id = :idAnalista ";
				parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
			}
			
			if(helper.getMensagem() != null){
				consulta += "and ratc.matc_id = :idTipoInconsistencia ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			consulta += "group by loc.loca_id, loc.loca_nmlocalidade, "
					 +	"usur.usur_id, usur.usur_nmusuario, matc.matc_dsmensagem "
					 +	"order by loc.loca_id, usur.usur_nmusuario, matc.matc_dsmensagem ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("idCadastrador", Hibernate.INTEGER)
					.addScalar("nomeCadastrador", Hibernate.STRING)
					.addScalar("descricaoMensagem", Hibernate.STRING)
					.addScalar("quantidade", Hibernate.INTEGER);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();
		
		}catch(HibernateException ex){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC 1314] Gerar Resumo Quantitativo de Mensagens Pendentes
	 * 
	 * @author Cesar Medeiros
	 * @since 09/12/2014
	 * 
	 * @param Helper
	 * @return Collection<>
	 * @throws ErroRepositorioException
	 */
	
	public Collection<Object[]> pesquisarResumoQuantitativoMensagensPendentes(
		DadosResumoMovimentoAtualizacaoCadastralHelper helper) throws ErroRepositorioException {
	
	Collection retorno = null;
	
	Session session = HibernateUtil.getSession();
	
	String consulta = "";
	SQLQuery query = null;
	Map parameters = new HashMap();
	
	try{
		consulta += "select loc.loca_id idLocalidade, loc.loca_nmlocalidade nomeLocalidade, "
				 +	"matc.matc_dsmensagem descricaoMensagem, count(imac.imac_id) quantidade " 
				 +	"from atualizacaocadastral.imovel_atlz_cadastral_dm imac "
				 +	"inner join atualizacaocadastral.retorno_atlz_cad_dm ratc on ratc.imac_id = imac.imac_id "
				 +	"inner join atualizacaocadastral.mensagem_atlz_cad_dm matc on matc.matc_id = ratc.matc_id "
				 +	"left  join atualizacaocadastral.imovel_atlz_cadastral_dm imo on imo.imov_id = imac.imov_id "
				 +	"inner join cadastro.localidade loc on imac.loca_id = loc.loca_id "
				 +	"inner join cadastro.setor_comercial sec ON sec.stcm_id = imo.imac_cdsetorcomercial "
				 +	"inner join cadastro.quadra qdra ON qdra.qdra_id = imo.imac_nnquadra "
				 +	"inner join cadastro.gerencia_regional greg ON greg.greg_id = loc.greg_id "
				 +	"inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "
				 
				 +  "left join atualizacaocadastral.arquivo_txt_atlz_cad_dm arquivo on arquivo.ptac_id = imac.ptac_id " 
				 +  "left join micromedicao.leiturista leitu on leitu.LEIT_ID = arquivo.LEIT_ID " 
				 +  "left JOIN seguranca.USUARIO usua on usua.usur_id=leitu.usur_id " 				 
				 
				 + 	"where imac.empr_id = :idEmpresa and matc.matc_id <> :atualizacaoComSucesso ";
			
		parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
		parameters.put("atualizacaoComSucesso", MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO);
		
		if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
				&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
			
			Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
			Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
			
			dataInicial = Util.formatarDataInicial(dataInicial);
			dataFinal = Util.formatarDataFinal(dataFinal);
			
			consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
			parameters.put("periodoInicial", dataInicial);
			parameters.put("periodoFinal", dataFinal);
		}
		
		if(helper.getIdGerenciaRegional() != null){
			consulta += "and greg.greg_id = :idGerencia ";
			parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
		}
		
		if(helper.getIdUnidade() != null){
			consulta += "and uni.uneg_id = :idUnidade ";
			parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
		}
		
		if(helper.getIdLocalidadeInicial() != null){
			consulta += "and imac.loca_id = :idLocalidadeInicial  ";
			parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
			
		}
		
		if(helper.getIdSetorComercialInicial() != null ){
			consulta += "and imac.imac_cdsetorcomercial = :idSetorInicial  ";
			parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));

		}
		
		if(helper.getNumerosQuadras() != null && !helper.getNumerosQuadras().trim().equals("")){
			consulta += "and imac.imac_nnquadra in (" + helper.getNumerosQuadras() + ")";
		}
		
		if(helper.getIdCadastrador() != null){
			consulta += "and usua.usur_id = :idCadastrador ";
			parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
		}
		
		if(helper.getIdAnalista() != null){
			consulta += "and ratc.usur_id = :idAnalista ";
			parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
		}
		
		if(helper.getMensagem() != null){
			consulta += "and ratc.matc_id = :idTipoInconsistencia ";
			parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
		}
		
		consulta += "group by loc.loca_id, loc.loca_nmlocalidade, matc.matc_dsmensagem "
				 +	"order by loc.loca_id,matc.matc_dsmensagem ";
		
		query = session.createSQLQuery(consulta)
				.addScalar("idLocalidade", Hibernate.INTEGER)
				.addScalar("nomeLocalidade", Hibernate.STRING)
				.addScalar("descricaoMensagem", Hibernate.STRING)
				.addScalar("quantidade", Hibernate.INTEGER);
				
		Set set = parameters.keySet();
		Iterator iterMap = set.iterator();
		while (iterMap.hasNext()) {
			String key = (String) iterMap.next();
			if (parameters.get(key) instanceof Collection) {
				Collection collection = (ArrayList) parameters.get(key);
				query.setParameterList(key, collection);
			} else {
				query.setParameter(key, parameters.get(key));
			}

		}

		retorno = query.list();
	
	}catch(HibernateException e){
		throw new ErroRepositorioException("Erro no Hibernate");
	}finally{
		HibernateUtil.closeSession(session);
	}
	
	return retorno;
}
	
	/**
	 * Método responsável por<br>
	 * pesquisar se existe o<br>
	 * arquivo KMZ cadastrado
	 * para Setor Comercial cadastrado
	 * @author Jonathan Marcos
	 * @since 09/12/2014
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarSetorComercialMapaKMZ(Integer idLocalidade,Integer codigoSetorComercial)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		try {
			String scriptSQL = "SELECT"+
							   " mapa.stcm_id AS idSetor,"+
							   " mapa.macd_nncoordenaday AS coordenadaX,"+
							   " mapa.macd_nncoordenadax AS coordenadaY "+
							   " FROM atualizacaocadastral.mapa_atlz_cad_dm mapa"+
							   " INNER JOIN cadastro.localidade localidade on mapa.loca_id=localidade.loca_id"+
							   " INNER JOIN cadastro.setor_comercial setorComercial on mapa.stcm_id=setorComercial.stcm_id"+
							   " WHERE "+
							   " 	mapa.loca_id = :idLocalidade"+
							   " AND"+
							   " 	setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial";
			
			return (Object[])session.createSQLQuery(scriptSQL)
			.addScalar("idSetor", Hibernate.INTEGER)
			.addScalar("coordenadaX",Hibernate.STRING)
			.addScalar("coordenadaY", Hibernate.STRING)
			.setInteger("idLocalidade", idLocalidade)
			.setInteger("codigoSetorComercial", codigoSetorComercial)
			.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/** [UC1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
	 * 	[FE0003] - Verificar existência de mapa do setor comercial
	 * 
	 * Caso não exista mapa associado ao setor comercial 
	 * (ATUALIZACAOCADASTRAL.MAPA_ATLZ_CAD_DM onde STCM_ID 
	 * = ID do setor comercial)
	 *   
	 * @author Bruno Sá Barreto
	 * @since 09/12/2014
	 *
	 * @return resultado
	 * @param codigoSetorComercial
	 */
	public boolean verificarExistenciaMapaSetorComercial(Integer idLocalidade,Integer codigoSetorComercial)
			throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		String consulta = "";
		boolean retorno = false;

		try {
			consulta = "SELECT"+
					   " count(*) result "+
					   " FROM atualizacaocadastral.mapa_atlz_cad_dm mapa"+
					   " INNER JOIN cadastro.localidade localidade on mapa.loca_id=localidade.loca_id"+
					   " INNER JOIN cadastro.setor_comercial setorComercial on mapa.stcm_id=setorComercial.stcm_id"+
					   " WHERE "+
					   " 	mapa.loca_id = :idLocalidade"+
					   " AND"+
					   " 	setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial";

			int count = (Integer) session.createSQLQuery(consulta).addScalar("result", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("codigoSetorComercial", codigoSetorComercial)
					.setMaxResults(1).uniqueResult();
			if(count>0) retorno = true;
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/** [UC1393] - Processar Requisição do Dispositivo Móvel Atualização Cadastral 
	 *  retorna o arquivo de mapa
	 *  do setor comercial caso ele
	 *  exista.
	 *   
	 * @author Bruno Sá Barreto
	 * @since 11/12/2014
	 *
	 */
	public byte[] pesquisarArquivoMapSetorComercial(Integer idLocalidade,Integer codigoSetorComercial) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = "";
		byte[] result = null;

		try {
			consulta = "SELECT"+
					   " mapa.macd_armapamap result "+
					   " FROM atualizacaocadastral.mapa_atlz_cad_dm mapa"+
					   " INNER JOIN cadastro.localidade localidade on mapa.loca_id=localidade.loca_id"+
					   " INNER JOIN cadastro.setor_comercial setorComercial on mapa.stcm_id=setorComercial.stcm_id"+
					   " WHERE "+
					   " 	mapa.loca_id = :idLocalidade"+
					   " AND"+
					   " 	setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial";

			result = (byte[]) session.createSQLQuery(consulta)
					.addScalar("result", Hibernate.BINARY)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("codigoSetorComercial", codigoSetorComercial)
					.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return result;
	}
	
	/**
	 * Recupera quantidade de imoveis enviados para atualiação cadastral
	 * 
	 * @author Cesar Medeiros
	 * @date 09/12/2014
	 */
	public Integer recuperaQtdeImoveisPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)
			throws ErroRepositorioException {

		Integer retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			consulta = " SELECT COUNT(imov_id) AS qtdeImoveis"
					+ " FROM atualizacaocadastral.imovel_atlz_cadastral_dm"
					+ " WHERE loca_id = :idLocalidade " 
					+ " AND imac_icdadosretorno in (1,3) "
					+ " AND empr_id = :idEmpresa ";

			retornoConsulta = (Integer)session.createSQLQuery(consulta)
					.addScalar("qtdeImoveis", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idEmpresa", idEmpresa)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}
	
	
	/**
	 * Recupera quantidade de imoveis com algum tipo de inconsistencia enviados para atualziacao cadastral
	 * 
	 * @author Cesar Medeiros
	 * @date 09/12/2014
	 */
	public Integer recuperaQtdeImoveisComInconsistenciasPorLocalidadeEEmpresa(
			Integer idLocalidade, Integer idEmpresa)
			throws ErroRepositorioException {

		Integer retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		try {
			
			consulta = "SELECT count(distinct imac.imov_id) AS qtdeImoveis " 
				+ "FROM atualizacaocadastral.imovel_atlz_cadastral_dm imac "
				+ "INNER JOIN cadastro.localidade loc ON loc.loca_id = imac.loca_id "
				+ "INNER JOIN atualizacaocadastral.retorno_atlz_cad_dm ratc on ratc.imac_id = imac.imac_id "
				+ "INNER JOIN atualizacaocadastral.mensagem_atlz_cad_dm matc on matc.matc_id = ratc.matc_id "
				+ "WHERE empr_id = :idEmpresa " 
				+ "AND matc.matc_id <> :atualizacaoComSucesso " 
				+ "AND loc.loca_id = :idLocalidade";

			retornoConsulta = (Integer)session.createSQLQuery(consulta)
					.addScalar("qtdeImoveis", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("atualizacaoComSucesso", MensagemAtualizacaoCadastralDM.ATUALIZACAO_COM_SUCESSO)
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}	
	
	/** 
	 * Verifica se o id de um logradouro existe na base de dados
	 * retorna o booleano afirmando ou negando a assertiva.
	 * 
	 * @author Bruno Sá Barreto
	 * @since 12/12/2014
	 *
	 * @return resultado
	 * @param idLogradouro
	 */
	public boolean verificarSeLogradouroExisteNoGsan(Long idLogradouro)
			throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = "";
		boolean retorno = false;

		try {
			consulta = " select count(*) result "
					+ " from cadastro.logradouro logradouro"
					+ " where logradouro.logr_id = :idLogradouro ";

			int count = (Integer) session.createSQLQuery(consulta).addScalar("result", Hibernate.INTEGER).setLong("idLogradouro", idLogradouro).setMaxResults(1).uniqueResult();
			if(count>0) retorno = true;
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC 1311] Gerar Resumo da Posição de Atualização Cadastral
	 * 
	 * @author Cesar Medeiros
	 * @since 15/12/2014
	 * 
	 * @param Helper
	 * @return Collection<Object []>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object []> pesquisarResumoPosicaoAtualizacaoCadastral(DadosResumoMovimentoAtualizacaoCadastralHelper helper)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		
		String consulta = "";
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		try{
			
		    consulta =	" SELECT principal.idLocalidade as idLocalidade, "+
						"  principal.nomeLocalidade as nomeLocalidade, "+
						"  SUM(principal.quantidadeImoveisRoteiro) as quantidadeImoveisRoteiro, "+
						"  SUM(principal.quantidadePreGsan) as quantidadePreGsan, "+
						"  SUM(principal.quantidadeAmbienteII) as quantidadeAmbienteII, "+
						"  SUM(principal.quantidadeAtualizadosGSAN) as quantidadeAtualizadosGSAN, "+
						"  SUM(principal.parametroAC) as parametroAC, "+
						"  SUM(principal.quantidadeIncluidos) as quantidadeIncluidos, "+
						"  SUM(principal.quantidadeAtualizados) as quantidadeAtualizados, "+
						"  principal.nomeUsuario as nomeUsuario, "+
						"  principal.codigoSetor as codigoSetor, "+
						"  SUM(principal.preGsanAtualizados) as preGsanAtualizados, "+
						"  SUM(principal.preGsanIncluidos) as preGsanIncluidos, "+
						"  SUM(principal.comInconsistencia) as comInconsistencia, "+
						"  SUM(principal.semInconsistencia) as semInconsistencia, "+
						"  SUM(principal.retornoCampo) as retornoCampo, "+
						"  SUM(principal.removido) as removido "+
						"  FROM "+
					   	"  ( ";
			
			consulta += "SELECT imac.loca_id as idLocalidade, " +
					   "loc.loca_nmlocalidade as nomeLocalidade, " +
					   "arquivo.atac_qtdimovel  as quantidadeImoveisRoteiro, " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 3 AND imac.IMAC_CDSITUACAO is null THEN 1 ELSE 0 END) as quantidadePreGsan, " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado = 2 AND (imac.IMAC_CDSITUACAO <> 2 or imac.IMAC_CDSITUACAO is null) THEN 1 ELSE 0 END) as quantidadeAmbienteII, " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado = 1 THEN 1 ELSE 0 END) as quantidadeAtualizadosGSAN, " +
					   "imac.ptac_id as parametroAC, " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 1 and imac.imac_icdadosretorno = 1 THEN 1 ELSE 0 END) as quantidadeIncluidos, " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 2 and imac.imac_icdadosretorno = 1 THEN 1 ELSE 0 END) as quantidadeAtualizados, " +
					   "usua.usur_nmusuario as nomeUsuario, " +
					   "param.ptac_cdsetorcomercial as codigoSetor, " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 2 AND imac.imac_icdadosretorno = 3 THEN 1 ELSE 0 END) as preGsanAtualizados,  " +
					   "SUM (CASE WHEN imac.IMAC_ICIMOVELNOVO = 1 AND imac.imac_icdadosretorno = 3 THEN 1 ELSE 0 END) as preGsanIncluidos,  " +
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado    = 1 "+
					   " 		AND NOT EXISTS "+
					   " 	       (SELECT * "+
					   " 	       FROM atualizacaocadastral.retorno_atlz_cad_dm rac "+
					   " 	       WHERE rac.matc_id <> 10 "+
					   " 	       AND imac.imac_id   = rac.imac_id "+
					   " 	       ) "+
					   " 	     THEN 1 "+
					   " 	     ELSE 0 "+
					   " 	   END) AS semInconsistencia, "+
					   "SUM (CASE WHEN imac.imac_icdadosretorno = 1 AND imac.imac_icatualizado = 1 "+
					   "	     AND EXISTS "+
					   "	       (SELECT * "+
					   "	       FROM atualizacaocadastral.retorno_atlz_cad_dm rac "+
					   "	       WHERE rac.matc_id <> 10 "+
					   "	       AND imac.imac_id   = rac.imac_id "+
					   "	       ) "+
					   "	     THEN 1 "+
					   "	     ELSE 0 "+
					   "	   END) AS comInconsistencia, "+
					   "SUM (CASE WHEN imac.imac_cdsituacao    = 2 AND imac.imac_icdadosretorno = 1 THEN 1 ELSE 0 END) AS retornoCampo, "+
					   "0 as removido "+
					   "FROM atualizacaocadastral.imovel_atlz_cadastral_dm imac " +
					   "INNER JOIN cadastro.localidade loc on loc.loca_id = imac.loca_id " +
					   "INNER JOIN atualizacaocadastral.param_tab_atl_cad_dm param on param.ptac_id = imac.ptac_id " +
					   "INNER join atualizacaocadastral.arquivo_txt_atlz_cad_dm arquivo on arquivo.ptac_id = imac.ptac_id " +
					   "INNER join micromedicao.leiturista leitu on leitu.LEIT_ID = arquivo.LEIT_ID " +
					   "INNER JOIN seguranca.USUARIO usua on usua.usur_id=leitu.usur_id " +
					   "inner join cadastro.unidade_negocio uni ON uni.uneg_id = loc.uneg_id "+
					   "WHERE imac.empr_id = :idEmpresa ";

			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += "and imac.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and loc.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdUnidade() != null){
				consulta += "and loc.uneg_id = :idUnidade ";
				parameters.put("idUnidade", Integer.parseInt(helper.getIdUnidade()));
			}
			
			if(helper.getIdLocalidadeInicial() != null ){
				consulta += "and imac.loca_id = :idLocalidadeInicial  ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
				
			}
			
			if(helper.getIdSetorComercialInicial() != null ){
				consulta += "and param.ptac_cdsetorcomercial = :idSetorInicial ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
			
			}
			
			if(helper.getNumerosQuadras() != null && !helper.getNumerosQuadras().trim().equals("")){
				consulta += "and imac.imac_nnquadra in (" + helper.getNumerosQuadras() + ")";
			}

			if(helper.getIdCadastrador() != null){
				consulta += "and usua.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
					
			
			if(helper.getMensagem() != null){
				consulta += "AND imac.imac_ID in (SELECT imac_id  FROM atualizacaocadastral.retorno_atlz_cad_dm WHERE MATC_ID = :idTipoInconsistencia) ";
				parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
			}
			
			if(helper.getIdAnalista() != null || helper.getMensagem() != null){
				String whereIn = " WHERE ";
				
				boolean analistaInformado = false;
				if(helper.getIdAnalista() != null){
					whereIn += " usur_id = :idAnalista ";
					analistaInformado = true;
					
					parameters.put("idAnalista", Integer.parseInt(helper.getIdAnalista()));
				}
				
				if(helper.getMensagem() != null){
					if(analistaInformado){
						whereIn += " and MATC_ID = :idTipoInconsistencia ";	
					}else{
						whereIn += " MATC_ID = :idTipoInconsistencia ";
					}
					
					parameters.put("idTipoInconsistencia", Integer.parseInt(helper.getMensagem()));
				}
				
				consulta += "AND imac.imac_ID in (SELECT imac_ID  FROM atualizacaocadastral.retorno_atlz_cad_dm "+whereIn+") ";
			}
			
			consulta += "group by imac.loca_id, arquivo.atac_qtdimovel,loc.loca_nmlocalidade , imac.ptac_id,usua.usur_nmusuario,param.PTAC_CDSETORCOMERCIAL ";
			
			//============= Query para obter os removidos =======================
			
			consulta += " UNION ALL "+
						" SELECT iacr.loca_id                  AS idLocalidade, "+
						" loc.loca_nmlocalidade              AS nomeLocalidade, "+
						" 0            						 AS quantidadeImoveisRoteiro, "+
						" 0                                  AS quantidadePreGsan, "+
						" 0                                  AS quantidadeAmbienteII, "+
						" 0                                  AS quantidadeAtualizadosGSAN, "+
						" iacr.ptac_id                       AS parametroAC, "+
						" 0                                  AS quantidadeIncluidos, "+
						" 0                                  AS quantidadeAtualizados, "+
						" usua.usur_nmusuario                AS nomeUsuario, "+
						" param.PTAC_CDSETORCOMERCIAL AS codigoSetor, "+
						" 0                                  AS preGsanAtualizados, "+
						" 0                                  AS preGsanIncluidos, "+
						" 0                                  AS semInconsistencia, "+
						" 0                                  AS comInconsistencia, "+
						" 0                                  AS retornoCampo, "+
						" SUM(1)                             AS removido "+
						" FROM atualizacaocadastral.imovel_atlz_cadastral_dm iacr "+
						" INNER JOIN cadastro.localidade loc "+
						" ON loc.loca_id = iacr.loca_id "+
						" INNER JOIN atualizacaocadastral.param_tab_atl_cad_dm param "+
						" ON param.ptac_id = iacr.ptac_id "+
						" INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm arquivo "+
						" ON arquivo.ptac_id = iacr.ptac_id "+
						" INNER JOIN micromedicao.leiturista leitu "+
						" ON leitu.LEIT_ID = arquivo.LEIT_ID "+
						" INNER JOIN seguranca.USUARIO usua "+
						" ON usua.usur_id    =leitu.usur_id "+
						" WHERE imac_icregistroexcluido = 1 ";
			
			parameters.put("idEmpresa", Integer.parseInt(helper.getIdEmpresa()));
			
			if(helper.getPeriodoInicial() != null && !helper.getPeriodoInicial().equals("") 
					&& helper.getPeriodoFinal() != null && !helper.getPeriodoFinal().equals("")){
				
				Date dataInicial = Util.converteStringParaDate(helper.getPeriodoInicial());
				Date dataFinal = Util.converteStringParaDate(helper.getPeriodoFinal());
				
				dataInicial = Util.formatarDataInicial(dataInicial);
				dataFinal = Util.formatarDataFinal(dataFinal);
				
				consulta += " and iacr.imac_dtrecebimentomovimento between :periodoInicial and :periodoFinal ";
				parameters.put("periodoInicial", dataInicial);
				parameters.put("periodoFinal", dataFinal);
			}
			
			if(helper.getIdGerenciaRegional() != null){
				consulta += "and loc.greg_id = :idGerencia ";
				parameters.put("idGerencia", Integer.parseInt(helper.getIdGerenciaRegional()));
			}
			
			if(helper.getIdLocalidadeInicial() != null ){
				consulta += "and iacr.loca_id = :idLocalidadeInicial ";
				parameters.put("idLocalidadeInicial", Integer.parseInt(helper.getIdLocalidadeInicial()));
		
			}
			
			if(helper.getIdSetorComercialInicial() != null ){
				consulta += "and param.ptac_cdsetorcomercial = :idSetorInicial ";
				parameters.put("idSetorInicial", Integer.parseInt(helper.getIdSetorComercialInicial()));
		
			}
			

			
			if(helper.getNumerosQuadras() != null && !helper.getNumerosQuadras().trim().equals("")){
				consulta += "and iacr.imac_nnquadra in (" + helper.getNumerosQuadras() + ")";
			}

			if(helper.getIdCadastrador() != null){
				consulta += "and usua.usur_id = :idCadastrador ";
				parameters.put("idCadastrador", Integer.parseInt(helper.getIdCadastrador()));
			}
			
			consulta += " GROUP BY iacr.loca_id, "+
						" arquivo.atac_qtdimovel, "+
						" loc.loca_nmlocalidade , "+
						" iacr.ptac_id, "+
						" usua.usur_nmusuario, "+
						" param.PTAC_CDSETORCOMERCIAL "+
						" ORDER BY idLocalidade, "+
						" codigoSetor ";
			
			consulta +=
						") principal " +
						"  GROUP BY principal.idLocalidade,"+
						"  principal.nomeLocalidade,"+
						"  principal.nomeUsuario,"+
						"  principal.codigoSetor"+
						"  ORDER BY principal.idLocalidade,"+
						"  principal.nomeUsuario," +
						"  principal.codigoSetor";
						
			
			
			query = session.createSQLQuery(consulta)
					.addScalar("idLocalidade", Hibernate.INTEGER)                //0
					.addScalar("nomeLocalidade", Hibernate.STRING)				 //1
					.addScalar("quantidadeImoveisRoteiro", Hibernate.INTEGER)    //2 
					.addScalar("quantidadePreGsan", Hibernate.INTEGER)           //3
					.addScalar("quantidadeAmbienteII", Hibernate.INTEGER)        //4
					.addScalar("quantidadeAtualizadosGSAN", Hibernate.INTEGER)   //5 
					.addScalar("parametroAC", Hibernate.INTEGER)                 //6 
					.addScalar("quantidadeIncluidos", Hibernate.INTEGER)         //7
					.addScalar("quantidadeAtualizados", Hibernate.INTEGER)       //8
					.addScalar("nomeUsuario", Hibernate.STRING)                  //9  
					.addScalar("codigoSetor", Hibernate.INTEGER)                 //10 
					.addScalar("preGsanAtualizados", Hibernate.INTEGER)          //11 
					.addScalar("preGsanIncluidos", Hibernate.INTEGER)            //12 
					.addScalar("comInconsistencia", Hibernate.INTEGER)           //13 
					.addScalar("semInconsistencia", Hibernate.INTEGER)           //14 
					.addScalar("retornoCampo", Hibernate.INTEGER)           	 //15
					.addScalar("removido", Hibernate.INTEGER);           	     //16
			
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}

			retorno = query.list();
		
		}catch(HibernateException e){
			throw new ErroRepositorioException("Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	/** 
	 * retorna o logr_id do imóvel
	 * 
	 * @author Bruno Sá Barreto
	 * @since 12/12/2014
	 *
	 * @return resultado
	 * @param idLogradouro
	 */
	public Long pesquisarIdLogradouroDoImovel(Integer idImovel)
			throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Long result = null;

		try {
			consulta = " select logr_id result"
					+ " from cadastro.imovel imovel"
					+ " where imovel.imov_id = :idImovel ";

			result = (Long) session.createSQLQuery(consulta).addScalar("result", Hibernate.LONG).setInteger("idImovel", idImovel).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return result;
	}
	
	/**
	 * [UC1297] Atualizar Dados Cadastrais para Imoveis Inconsistentes
	 * 
	 * [SB0006] Relatório dos Imoveis Inconsistentes
	 * 
	 * @author Davi Menezes
	 * @author Bruno Sá Barreto 
	 * 
	 * @date 26/03/2012
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarRelatorioImoveisInconsistentesMovimento(Integer idMovimento, Date dataMovimento,
			String idLocalidade, String codigoSetorComercial, String numeroQuadras, String idCadastrador, String indicadorSituacaoMovimento,
			String tipoInconsistencia)throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		SQLQuery query = null;
		Map parameters = new HashMap();
		
		Date dataMovimentoInicial = Util.formatarDataInicial(dataMovimento);
		Date dataMovimentoFinal = Util.formatarDataFinal(dataMovimento);
		
		try{
			String consulta = "SELECT iac.imov_id idImovel, " + //0
					"iac.imac_dtrecebimentomovimento dataRecebimento, " + //1
					"loc.loca_id idLocalidade, " + //2
					"loc.loca_nmlocalidade nomeLocalidade, " + //3
					"iac.imac_cdsetorcomercial codigoSetor, " + //4
					"iac.imac_nnquadra numeroQuadra, " + //5
					"sac.siac_dssituacao descSituacao, " + //6
					"aac.atac_nmatributo numeroAtributo, " +//7
					"mac.matc_dsmensagem descMensagem, " +//8
					"usuario.usur_nmusuario nomeCadastrador " +//9
					
					"FROM atualizacaocadastral.imovel_atlz_cadastral_dm iac " +
					"left join cadastro.imovel imov on imov.imov_id = iac.imov_id " + 
					"inner join cadastro.localidade loc on loc.loca_id = iac.loca_id " +
					"left join CADASTRO.situacao_atlz_cadastral sac on sac.siac_id = imov.siac_id " +
					
					" left join seguranca.usuario usuario ON usuario.usur_nmlogin = iac.imac_nmlogin OR usuario.usur_nncpf = iac.imac_nmlogin "+
					" inner join micromedicao.leiturista leiturista ON leiturista.usur_id = usuario.usur_id "+
					
 	 				"inner join atualizacaocadastral.retorno_atlz_cad_dm rac on rac.matc_id <> 10 and REAT_CDOPCAOALTERACAO is null " +
					"and rac.imov_id = iac.imov_id and rac.imac_id = iac.imac_id " +
					"left join atualizacaocadastral.atributo_atlz_cad_dm aac on aac.atac_id = rac.atac_id " +
					"left join atualizacaocadastral.mensagem_atlz_cad_dm mac  on mac.matc_id = rac.matc_id " +
					"where iac.ptac_id = :idMovimento " +
					"and iac.imac_dtrecebimentomovimento between :dataMovimentoInicial and :dataMovimentoFinal " +
					"and iac.imac_dtrecebimentomovimento is not null ";
			
			parameters.put("idMovimento", idMovimento);
			parameters.put("dataMovimentoInicial", dataMovimentoInicial);
			parameters.put("dataMovimentoFinal", dataMovimentoFinal);
			
			//Localidade
			if ( Util.verificarNaoVazio(idLocalidade) ){ 
				consulta += " and iac.loca_id = :idLocalidade ";
				parameters.put("idLocalidade", Integer.parseInt(idLocalidade));
			}
			
			//Setor Comercial
			if ( Util.verificarNaoVazio(codigoSetorComercial) ){ 
				consulta += " and iac.imac_cdsetorcomercial = :codigoSetorComercial ";
				parameters.put("codigoSetorComercial", Integer.parseInt(codigoSetorComercial));
			}
			
			//Quadra 
			if ( Util.verificarNaoVazio(numeroQuadras) && !"-1".equals(numeroQuadras)) { 
				consulta += " and iac.imac_nnquadra in ("+numeroQuadras+")";
			} 
			
			//Cadastrador
			if ( Util.parametroNumericoValido(idCadastrador) ){ 
				consulta += " and usuario.usur_id = "
						+ idCadastrador;
			}

			if ( indicadorSituacaoMovimento != null && 
					(indicadorSituacaoMovimento.equals("1") || indicadorSituacaoMovimento.equals("2"))) {
				consulta += " and iac.imac_icpendente = :indicadorSituacaoMovimento ";
				parameters.put("indicadorSituacaoMovimento", Short.parseShort(indicadorSituacaoMovimento));
			} 
			
			if ( Util.parametroNumericoValido(tipoInconsistencia) ) { 
				consulta += " and rac.matc_id = :tipoInconsistencia ";
				parameters.put("tipoInconsistencia", Integer.parseInt(tipoInconsistencia));
			}
			
			consulta += " group by iac.imac_dtrecebimentomovimento, loc.loca_id, iac.imov_id,loc.loca_nmlocalidade,iac.imac_cdsetorcomercial,iac.imac_nnquadra "
					+ " ,sac.siac_dssituacao,aac.atac_nmatributo,mac.matc_dsmensagem,usuario.usur_nmusuario ";
			
			consulta += "order by iac.imac_dtrecebimentomovimento, loc.loca_id, iac.imov_id ";
			
			query = session.createSQLQuery(consulta)
					.addScalar("idImovel", Hibernate.STRING)
					.addScalar("dataRecebimento", Hibernate.DATE)
					.addScalar("idLocalidade", Hibernate.STRING)
					.addScalar("nomeLocalidade", Hibernate.STRING)
					.addScalar("codigoSetor", Hibernate.STRING)
					.addScalar("numeroQuadra", Hibernate.STRING)
					.addScalar("descSituacao", Hibernate.STRING)
					.addScalar("numeroAtributo", Hibernate.STRING)
					.addScalar("descMensagem", Hibernate.STRING)
					.addScalar("nomeCadastrador", Hibernate.STRING);
					
			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}

			}
			
			retorno = query.list();
			
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1669] Atualizar Dados nas Tabelas Resumos Grenciais Faturamento
	 * @author Fábio Aguiar
	 * @date 04/02/2015
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException
	 */
	public void gerarResumoCadastroAtualizaDados() throws ErroRepositorioException, SQLException {

		Session session = HibernateUtil.getSessionGerencial();
		Connection con = null;
		String sql = "";
		try {
			con = session.connection();
			sql = " SELECT cadastro.sp_un_cadastro_atualiza_dados() ";
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.executeQuery();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(	e,
												"Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
}
