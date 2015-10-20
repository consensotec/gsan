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
package gcom.financeiro;

import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.faturamento.ImpostoTipo;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.financeiro.bean.ParametrosPerdasFiscaisHelper;
import gcom.financeiro.bean.ParametrosPerdasFiscaisItensHelper;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturadosBean;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.StatelessSession;



/**
 * Repositorio para financeiro
 * 
 * @author Raphael Rossiter
 * @since 09/01/2006
 */
public class RepositorioFinanceiroHBM implements IRepositorioFinanceiro {

	/** Repositorio Financeiro Hibernate */
	protected static RepositorioFinanceiroHBM instancia;

	/**
	 * Construtor da classe RepositorioFinanceiroHBM
	 */
	protected RepositorioFinanceiroHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static RepositorioFinanceiroHBM getInstancia() {
		String dialect = HibernateUtil.getDialect();
		
		if (dialect.toUpperCase().contains("ORACLE")){
			if (instancia == null) {
				instancia = new RepositorioFinanceiroHBM();
			}
		} else {
			if (instancia == null) {
				instancia = new RepositorioFinanceiroPostgresHBM();
			}
		}

		return instancia;
	}
	
	/**
	 * Obtém os dados do resumoFaturamento a partir do ano e mês de referência
	 *
	 * [UC0175] - Gerar Lançamentos Contábeis do Faturamento
	 *
	 * @author Raphael Rossiter, Pedro Alexandre 
	 * @date 16/01/2006, 24/05/2007
	 *
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoFaturamento(Integer anoMesReferenciaFaturamento, Integer idLocalidade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT loca.id, lctp.id, lcit.id, lict.id, catg.id, SUM(rfat.valorItemFaturamento) "
					+ "FROM ResumoFaturamento rfat "
					+ "LEFT JOIN rfat.localidade loca "
					+ "LEFT JOIN rfat.lancamentoTipo lctp "
					+ "LEFT JOIN rfat.lancamentoItem lcit "
					+ "LEFT JOIN rfat.lancamentoItemContabil lict "
					+ "LEFT JOIN rfat.categoria catg "
					+ "WHERE rfat.anoMesReferencia = :anoMesReferenciaFaturamento AND loca.id = :idLocalidade "
					+ "GROUP BY loca.id, lctp.id, lcit.id, lict.id, catg.id "
					+ "ORDER BY loca.id, lctp.id, lcit.id, lict.id, catg.id ";

			retorno = session.createQuery(consulta)
						.setInteger("anoMesReferenciaFaturamento",anoMesReferenciaFaturamento)
						.setInteger("idLocalidade",idLocalidade)
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
	 * Obtém a conta contábil a partir do número da razão contábil e do núemro da conta
	 * @param razao
	 * @param conta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil obterContaContabil(Short razao, Integer conta) throws ErroRepositorioException {
		
		ContaContabil retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT new ContaContabil(cnct.id, cnct.razao, cnct.conta, cnct.prefixo, cnct.ultimaAlteracao) "
					+ "FROM contaContabil cnct "
					+ "WHERE  cnct.razao = :nnRazao AND cnct.conta = :nnConta ";

			retorno = (ContaContabil) session.createQuery(consulta).setInteger("nnRazao",
					 razao.intValue()).setInteger(
					 "nnConta", conta.intValue()).uniqueResult();

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
	 * Obtém a conta contábil a partir da tabela LANCAMENTO_ITEM_CONTABIL
	 * @param razao
	 * @param conta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil obterContaContabil(LancamentoItemContabil lancamentoItemContabil) throws ErroRepositorioException {
		
		ContaContabil retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT new ContaContabil(cnct.id, cnct.razao, cnct.conta, cnct.prefixo, cnct.ultimaAlteracao) "
					+ "FROM lancamentoItemContabil lict "
					+ "INNER JOIN lict.contaContabil cnct "
					+ "WHERE  lict.id = :idlancamentoItemContabil ";

			retorno = (ContaContabil) session.createQuery(consulta).setInteger("idlancamentoItemContabil",
					lancamentoItemContabil.getId().intValue()).uniqueResult();

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
	 * Gera Lançamentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lançamento Constábeis da Arrecadação
	 *
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 23/05/2006, 25/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosResumoArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			
			consulta = "SELECT loca.id, rctp.id, lctp.id, lcit.id, lict.id, catg.id, SUM(rarr.valorItemArrecadacao) "
					+ "FROM ResumoArrecadacao rarr "
					+ "LEFT JOIN rarr.localidade loca "
					+ "LEFT JOIN rarr.lancamentoTipo lctp "
					+ "LEFT JOIN rarr.lancamentoItem lcit "
					+ "LEFT JOIN rarr.lancamentoItemContabil lict "
					+ "LEFT JOIN rarr.categoria catg "
					+ "LEFT JOIN rarr.recebimentoTipo rctp "
					+ "WHERE rarr.anoMesReferencia = :anoMesReferenciaArrecadacao AND loca.id = :idLocalidade "
					+ "GROUP BY loca.id, rctp.id, lctp.id, lcit.id, lict.id, catg.id "
					+ "ORDER BY loca.id, rctp.id, lctp.id, lcit.id, lict.id, catg.id ";

			retorno = session.createQuery(consulta)
					.setInteger("anoMesReferenciaArrecadacao",anoMesReferenciaArrecadacao)
					.setInteger("idLocalidade",idLocalidade)
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
	 * 
	 * Gera Lançamentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lançamento Constábeis da Arrecadação
	 *
	 * Obter O Parametros Contabile Arrecadacao
	 *
	 * @author Rafael Santos
	 * @date 23/05/2006
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public Collection obterParametrosContabilArrecadacao(Integer idCategoria,Integer idItemLancamentoContabil,
			Integer idItemLancamento,Integer idTipoLancamento,
			Integer idTipoRecebimento) throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT acp.contaContabilDebito.id,acp.contaContabilCredito.id,acp.descricaoHistoricoDebito,acp.descricaoHistoricoCredito " 
					+ " from ArrecadacaoContabilParametros acp "
					+ " where acp.categoria.id = :idCategoria,acp.lancamentoItemContabil = :idItemLancamentoContabil," 
					+ " acp.lancamentoItem.id = :idItemLancamento,acp.lancamentoTipo.id = :idTipoLancamento," 
					+ " acp.recebimentoTipo.id = :idTipoRecebimento";

			retorno = session.createQuery(consulta)
					.setInteger("idCategoria",idCategoria.intValue())
					.setInteger("idItemLancamentoContabil",idItemLancamentoContabil.intValue())
					.setInteger("idItemLancamento",idItemLancamento.intValue())
					.setInteger("idTipoLancamento",idTipoLancamento.intValue())
					.setInteger("idTipoRecebimento",idTipoRecebimento.intValue()).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
		
		
	}
	*/
	

	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Pedro Alexandre
	 * @date 28/05/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException{
		Collection retorno;

		Session session = HibernateUtil.getSession();
		String consulta;
		try{
		consulta = "SELECT "
		+ " cntc.numeroConta ,"
		+ " CASE cntc.indicadorCentroCusto " +
		" WHEN 1 THEN loca.codigoCentroCusto" +
		" WHEN 2 THEN loca.codigoCentroCustoEsgoto " +
		" END ,"
		+ " CASE " +
		" WHEN lcti.indicadorDebitoCredito = 2 THEN lcti.valorLancamento" +
		" END ,"
		+ " CASE " +
		" WHEN lcti.indicadorDebitoCredito = 1 THEN lcti.valorLancamento" +
		" END ,"
		+ " loca.descricao, "
		+ " loca.id, "
		+ " loca.municipio.id "
		+ " from LancamentoContabilItem lcti"
		+ " inner join lcti.lancamentoContabil lcnb"
		+ " inner join lcnb.localidade loca"
		+ " inner join lcnb.lancamentoOrigem lcor"
		+ " inner join lcti.contaContabil cntc"
		+ " WHERE lcnb.anoMes = :anoMes "
		+ " and lcor.id = :idLancamentoOrigem"
		+ " ORDER BY loca.id";

		retorno = session.createQuery(consulta).
			setInteger("anoMes",new Integer(anoMes)).
			setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem)).
			list();

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
	 * Exclui os registros da tabela RESUMO_DEVEDORES_DUVIDOSOS 
	 * por ano mês refência contábil 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil)
		throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ResumoDevedoresDuvidosos as resumo "
					+ "where resumo.anoMesReferenciaContabil = :anoMesReferencia ";

			session.createQuery(delete).
				setInteger("anoMesReferencia", anoMesReferenciaContabil).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	/**
	 * Atualiza com o valor nulo o mês/ano de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil) throws ErroRepositorioException {

		/*String update;
		Session session = HibernateUtil.getSession();

		try {
			update = "UPDATE gcom.faturamento.conta.Conta SET "
					+ "cnta_amreferenciabaixacontabil = NULL, "
					+ "cnta_tmultimaalteracao = :dataUltimaAlteracao "
					+ "WHERE cnta_amreferenciacontabil = :anoMesReferenciaContabil ";

			session.createQuery(update)
				.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
				.setTimestamp("dataUltimaAlteracao",new Date())
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}*/
		String update;
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		
		try {

			Connection jdbcCon = session.connection();

			update =  "update faturamento.conta set "
				+ "cnta_amreferenciabaixacontabil = null "
				+ "where cnta_id in ( "
				+	"select "
				+   "contacategoria.cnta_id as contaid " 
				+   "from "
				+ 	"faturamento.conta_categoria contacategoria, "
				+ 	"faturamento.conta conta " 
				+ 	"where "
				+ 	"contacategoria.cnta_id=conta.cnta_id " 
				+  	"and contacategoria.catg_id <> ? " 
				//+  	"and conta.loca_id= ? " 
				+  	"and (conta.dcst_idatual in (?, ?, ?)) " 
				+  	"and (conta.cnta_amreferenciabaixacontabil is not null) " 
				+  	"and conta.cnta_amreferenciaconta < ? " 
				+ ")";
			
			st = jdbcCon.prepareStatement(update);
			st.setInt(1, Categoria.PUBLICO);
			//st.setInt(2, idLocalidade);
			st.setInt(2, DebitoCreditoSituacao.NORMAL);
			st.setInt(3, DebitoCreditoSituacao.INCLUIDA);
			st.setInt(4, DebitoCreditoSituacao.RETIFICADA);
			st.setInt(5, anoMesReferenciaContabil);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Seleciona todas as ocorrencias dos itens dos parâmetros
	 * baixa contábil
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre 
	 * @date 22/11/2006, 24/07/2007
	 *
	 * @param idParametrosDevedoresDuvidosos
	 * @throws ErroRepositorioException
	 */	
	public Collection<ParametrosDevedoresDuvidososItem> pesquisaParametrosDevedoresDuvidososItem(
		Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException{
		
		Collection<ParametrosDevedoresDuvidososItem> retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT parametroItem " 
					+ "FROM ParametrosDevedoresDuvidososItem parametroItem "
					+ "INNER JOIN parametroItem.parametrosDevedoresDuvidosos parametro "					
					+ "WHERE parametro.id = :idParametrosDevedoresDuvidosos";

			retorno = session.createQuery(consulta)
					.setInteger("idParametrosDevedoresDuvidosos",idParametrosDevedoresDuvidosos)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 01 Retorna o valor de água acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorAgua(int anoMesReferenciaBaixaContabil, 
		int idLocalidade, int idCategoria) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		ResumoDevedoresDuvidosos retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(ctcg.valorAgua)," 
					+ "cnta.localidade," 
					+ "cnta.localidade.gerenciaRegional," 
					+ "ctcg.comp_id.categoria) "
					+ "from ContaCategoria ctcg "
					+ "inner join ctcg.comp_id.conta cnta "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and cnta.localidade.id = :localidade "
					+ "and ctcg.comp_id.categoria.id = :categoria "
					+ "group by cnta.localidade,cnta.localidade.gerenciaRegional,ctcg.comp_id.categoria ";

			// executa o hql
			retorno = (ResumoDevedoresDuvidosos) session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				uniqueResult();
			

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;

	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 02 Retorna o valor do esgoto acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorEsgoto(int anoMesReferenciaBaixaContabil, 
		int idLocalidade, int idCategoria) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		ResumoDevedoresDuvidosos retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(ctcg.valorEsgoto)," 
					+ "cnta.localidade," 
					+ "cnta.localidade.gerenciaRegional," 
					+ "ctcg.comp_id.categoria) "
					+ "from ContaCategoria ctcg "
					+ "inner join ctcg.comp_id.conta cnta "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and cnta.localidade.id = :localidade "
					+ "and ctcg.comp_id.categoria.id = :categoria "
					+ "group by cnta.localidade,cnta.localidade.gerenciaRegional,ctcg.comp_id.categoria ";

			// executa o hql
			retorno = (ResumoDevedoresDuvidosos) session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				uniqueResult();
			

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;

	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 03 Retorna o valor da categoria acumulado por tipo financiamento por parcelamento agua, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		ResumoDevedoresDuvidosos retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(dccg.valorCategoria)," 
					+ "dbcb.localidade," 
					+ "dbcb.localidade.gerenciaRegional," 
					+ "dccg.categoria ) "
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and dbcb.localidade.id = :localidade "
					+ "and dccg.categoria.id = :categoria "
					+ "and dbcb.financiamentoTipo = :tipoFinanciamento "
					+ "group by dbcb.localidade,dbcb.localidade.gerenciaRegional,dccg.categoria ";

			// executa o hql
			retorno = (ResumoDevedoresDuvidosos) session.createQuery(consulta).
					setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
					setInteger("localidade", idLocalidade).
					setInteger("categoria", idCategoria).
					setInteger("tipoFinanciamento", FinanciamentoTipo.PARCELAMENTO_AGUA).
					uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 04 Retorna o valor da categoria acumulado por financiamento por parcelamento esgoto, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		ResumoDevedoresDuvidosos retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(dccg.valorCategoria)," 
					+ "dbcb.localidade," 
					+ "dbcb.localidade.gerenciaRegional," 
					+ "dccg.categoria ) "
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and dbcb.localidade.id = :localidade "
					+ "and dccg.categoria.id = :categoria "
					+ "and dbcb.financiamentoTipo = :tipoFinanciamento "
					+ "group by dbcb.localidade,dbcb.localidade.gerenciaRegional,dccg.categoria ";

			// executa o hql
			retorno = (ResumoDevedoresDuvidosos)
				session.createQuery(consulta).
					setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
					setInteger("localidade", idLocalidade).
					setInteger("categoria", idCategoria).
					setInteger("tipoFinanciamento", FinanciamentoTipo.PARCELAMENTO_ESGOTO).
					uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}	
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServicos(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException {

		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(dccg.valorCategoria)," +
					"lict.sequenciaImpressao," +
					"lict," +
					"dbcb.localidade," +
					"dbcb.localidade.gerenciaRegional," +
					"dccg.categoria ) "
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "inner join dbcb.lancamentoItemContabil lict "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and dbcb.localidade.id = :localidade"
					+ "and dccg.categoria.id = :categoria"
					+ "and dbcb.financiamentoTipo = :tipoFinanciamento"
					+ "group by lict.sequenciaImpressao,lict,dbcb.localidade,dbcb.localidade.gerenciaRegional,dccg.categoria ";

			// executa o hql
			retorno = session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				setInteger("tipoFinanciamento", FinanciamentoTipo.PARCELAMENTO_SERVICO).
				list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna a coleção de resumo de faturamento criada
		return retorno;

	}	
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 06 Retorna o valor da categoria acumulado por financiamento juros parcelamentos, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		ResumoDevedoresDuvidosos retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(dccg.valorCategoria)," 
					+ "dbcb.localidade," 
					+ "dbcb.localidade.gerenciaRegional," 
					+ "dccg.categoria ) "
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and dbcb.localidade.id = :localidade "
					+ "and dccg.categoria.id = :categoria "
					+ "and dbcb.financiamentoTipo = :tipoFinanciamento "
					+ "group by dbcb.localidade,dbcb.localidade.gerenciaRegional,dccg.categoria ";

			// executa o hql
			retorno = (ResumoDevedoresDuvidosos)
				session.createQuery(consulta).
					setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
					setInteger("localidade", idLocalidade).
					setInteger("categoria", idCategoria).
					setInteger("tipoFinanciamento", FinanciamentoTipo.JUROS_PARCELAMENTO).
					uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 07 Retorna o valor da categoria acumulado por financiamento por serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e mês de referência do faturamento
	 * @param idLocalidade 		Código da localidade
	 * @param idCategoria 		Código da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServico(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException {

		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select new ResumoDevedoresDuvidosos(sum(dccg.valorCategoria)," +
					"lict.sequenciaImpressao," +
					"lict," +
					"dbcb.localidade," +
					"dbcb.localidade.gerenciaRegional," +
					"dccg.categoria ) "
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "inner join dbcb.lancamentoItemContabil lict "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and dbcb.localidade.id = :localidade"
					+ "and dccg.categoria.id = :categoria"
					+ "and dbcb.financiamentoTipo = :tipoFinanciamento"
					+ "group by lict.sequenciaImpressao,lict,dbcb.localidade,dbcb.localidade.gerenciaRegional,dccg.categoria ";

			// executa o hql
			retorno = session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				setInteger("tipoFinanciamento", FinanciamentoTipo.SERVICO_NORMAL).
				list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna a coleção pesquisada
		return retorno;
	}
	
	/**
	 * [UC0345] - Gerar Relatorio de Resumo da Arrecadação
	 *
	 * @author Vivianne Sousa
	 * @date 10/04/2007
	 *
	 * @param idLancamentoTipo
	 * @throws ErroRepositorioException
	 */	
	public String obterDescricaoLancamentoTipo(Integer idLancamentoTipo) 
		throws ErroRepositorioException {

		String retorno;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "SELECT descricao "
					+ "FROM LancamentoTipo "
					+ "WHERE id = :idLancamentoTipo ";

			retorno = (String)session.createQuery(consulta).setInteger("idLancamentoTipo",idLancamentoTipo).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}

	
	/**
	 * [UC00175] Gerar Lançamentos Contábeis do Faturamento
	 *
	 * Pesquisa os parâmetros contábil do faturamento.
	 *
	 * @author Pedro Alexandre
	 * @date 24/05/2007
	 *
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilFaturamento(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException{
		
		Object[] retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT plcf.contaContabilDebito.id,plcf.contaContabilCredito.id,plcf.descricaoHistoricoDebito,plcf.descricaoHistoricoCredito " 
					+ "FROM FaturamentoContabilParametros plcf "
					+ "LEFT JOIN plcf.categoria catg "
					+ "LEFT JOIN plcf.lancamentoItemContabil lict "
					+ "LEFT JOIN plcf.lancamentoItem lcit "
					+ "LEFT JOIN plcf.lancamentoTipo lctp "
					+ "WHERE catg.id = :idCategoria AND " 
					+       "lcit.id = :idItemLancamento AND " 
					+		"lctp.id = :idTipoLancamento "; 
					
					if(idLancamentoItemContabil == null){
						consulta = consulta + "AND lict.id is null ";
					}else{
						consulta = consulta + "AND lict.id = :idLancamentoItemContabil ";
					}
				
			if(idLancamentoItemContabil == null){		
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
						.uniqueResult();
			}else{
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idLancamentoItemContabil",idLancamentoItemContabil)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
						.uniqueResult();
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
	 * Pesquisa as localidades que tem resumo de faturamento 
	 * para o ano/mês de faturamento informado.
	 *
	 * [UC00175] Gerar Lançamentos Contábeis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 25/05/2007
	 *
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT DISTINCT (loca.id) " 
					+ "FROM ResumoFaturamento rfat "
					+ "LEFT JOIN rfat.localidade loca "
					+ "WHERE rfat.anoMesReferencia = :anoMesFaturamento" ;
					
				retorno = session.createQuery(consulta)
						.setInteger("anoMesFaturamento",anoMesFaturamento)
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
	 * [UC00348] Gerar Lançamentos Contábeis da Arrecadação
	 *
	 * Pesquisa os parâmetros contábil da arrecadação.
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param idRecebimentoTipo	
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilArrecadacao(Integer idRecebimentoTipo, Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException{
		
		Object[] retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT plca.contaContabilDebito.id,plca.contaContabilCredito.id,plca.descricaoHistoricoDebito,plca.descricaoHistoricoCredito " 
					+ "FROM ArrecadacaoContabilParametros plca "
					+ "LEFT JOIN plca.recebimentoTipo rctp "
					+ "LEFT JOIN plca.categoria catg "
					+ "LEFT JOIN plca.lancamentoItemContabil lict "
					+ "LEFT JOIN plca.lancamentoItem lcit "
					+ "LEFT JOIN plca.lancamentoTipo lctp "
					+ "WHERE rctp.id = :idRecebimentoTipo AND " 
					+		"catg.id = :idCategoria AND " 
					+       "lcit.id = :idItemLancamento AND " 
					+		"lctp.id = :idTipoLancamento "; 
					
					if(idLancamentoItemContabil == null){
						consulta = consulta + "AND lict.id is null ";
					}else{
						consulta = consulta + "AND lict.id = :idLancamentoItemContabil ";
					}
				
			if(idLancamentoItemContabil == null){		
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idRecebimentoTipo",idRecebimentoTipo)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
						.uniqueResult();
			}else{
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idRecebimentoTipo",idRecebimentoTipo)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idLancamentoItemContabil",idLancamentoItemContabil)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
						.uniqueResult();
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
	 * Pesquisa as localidades que tem resumo da arrecadação 
	 * para o ano/mês de arrecadação informado.
	 *
	 * [UC00348] Gerar Lançamentos Contábeis da Arrecadação
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT DISTINCT (loca.id) " 
					+ "FROM ResumoArrecadacao rarr "
					+ "LEFT JOIN rarr.localidade loca "
					+ "WHERE rarr.anoMesReferencia = :anoMesArrecadacao" ;
					
				retorno = session.createQuery(consulta)
						.setInteger("anoMesArrecadacao",anoMesArrecadacao)
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
	 * Pesquisa os parâmetros dos devedores duvidosos por
	 * ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ErroRepositorioException {

		ParametrosDevedoresDuvidosos retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT pded "
					+ "FROM ParametrosDevedoresDuvidosos pded "
					+ "WHERE pded.anoMesReferenciaContabil = :anoMesReferenciaContabil ";

			retorno =(ParametrosDevedoresDuvidosos) session.createQuery(consulta)
						.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
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
	 * Atualiza com o valor nulo o mês/ano de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 * para a localidade informada.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, Integer idPerdaTipo)	throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		
		try {

			Connection jdbcCon = session.connection();

			update =  "update faturamento.conta set "
				+ "cnta_amreferenciabaixacontabil = null , "
				+ "cnta_tmultimaalteracao = ? , "
				+ "petp_id = null "
				+ "where loca_id= ? "
				+ "and qdra_id = ? "
				+ "and cnta_amreferenciabaixacontabil = ? "
				+ "and petp_id = ?"; 
			
			st = jdbcCon.prepareStatement(update);
			st.setTimestamp(1, Util.getSQLTimesTemp(new Date()));
			st.setInt(2, idLocalidade);
			st.setInt(3, idQuadra);
			st.setInt(4, anoMesReferenciaContabil);
			st.setInt(5, idPerdaTipo);
			
		
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		

		
	}
	
	/**
	 * Atualiza com o valor nulo o mês/ano de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 * para a localidade informada.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade)	throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		
		try {

			Connection jdbcCon = session.connection();

			update =  "update faturamento.conta set "
				+ "cnta_amreferenciabaixacontabil = null , "
				+ "cnta_tmultimaalteracao = ? , "
				+ "petp_id = null "
				+ "where conta.loca_id= ? "
				+ "and conta.cnta_amreferenciabaixacontabil = ? " ; 
			
			st = jdbcCon.prepareStatement(update);
			st.setTimestamp(1, Util.getSQLTimesTemp(new Date()));
			st.setInt(2, idLocalidade);
			st.setInt(3, anoMesReferenciaContabil);
			
		
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		

		
	}
	
	/**
	 * Exclui os registros da tabela RESUMO_DEVEDORES_DUVIDOSOS 
	 * por ano mês refência contábil e localidade
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre, Arthur Carvalho
	 * @date 22/11/2006, 06/06/2007 , 22/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idTipoPerda
	 * 
	 * @throws ErroRepositorioException
	 */	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade, Integer idTipoPerda)
		throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ResumoDevedoresDuvidosos as resumo "
					+ "where resumo.anoMesReferenciaContabil = :anoMesReferencia " 
					+ "and resumo.localidade.id = :idLocalidade "
					+ "and resumo.perdasTipo.id = :idPerdasTipo ";

			session.createQuery(delete).
				setInteger("anoMesReferencia", anoMesReferenciaContabil).
				setInteger("idLocalidade", idLocalidade).
				setInteger("idPerdasTipo", idTipoPerda)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	
	/**
	 * Pesquisa a coleção de ids das localidades para processar o resumo 
	 * dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT DISTINCT loca.id "
				+ "FROM Localidade loca ";
				//+ "WHERE loca.indicadorUso = :indicadorUso ";

		retorno = session.createQuery(consulta)
				//.setInteger("indicadorUso", ConstantesSistema.SIM)
				.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	
	/**
	 * Linha 01 Retorna o valor de água acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 12/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(ctcg.valorAgua) " 
					 + "from ContaCategoria ctcg "
					 + "inner join ctcg.comp_id.conta cnta "
					 + "inner join ctcg.comp_id.categoria catg "
					 + "inner join cnta.localidade loca "
					 + "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					 + "and loca.id = :localidade "
					 + "and catg.id = :categoria "
					 + "and cnta.id in (:idsContas) ";

			// executa o hql
			retorno = (BigDecimal) session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				setParameterList("idsContas", colecaoIdsContas).
				uniqueResult();
			

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;

	}

	/**
	 * Linha 02 Retorna o valor do esgoto acumulado, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(ctcg.valorEsgoto) " 
					+ "from ContaCategoria ctcg "
					+ "inner join ctcg.comp_id.conta cnta "
					+ "inner join ctcg.comp_id.categoria catg "
					+ "inner join cnta.localidade loca "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and loca.id = :localidade "
					+ "and catg.id = :categoria "
					+ "and cnta.id in(:idsContas) ";

			// executa o hql
			retorno = (BigDecimal) session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				setParameterList("idsContas", colecaoIdsContas).
				uniqueResult();
			

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}
	
	
	
	/**
	 * Linha 03 Retorna o valor da categoria acumulado por tipo financiamento por parcelamento agua, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(dccg.valorCategoria) " 
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "inner join dccg.categoria catg "
					+ "inner join dbcb.localidade loca "
					+ "inner join dbcb.financiamentoTipo fntp "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and loca.id = :localidade "
					+ "and catg.id = :categoria "
					+ "and fntp.id = :tipoFinanciamento "
					+ "and cnta.id in (:idsContas) ";

			// executa o hql
			retorno = (BigDecimal) session.createQuery(consulta).
					setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
					setInteger("localidade", idLocalidade).
					setInteger("categoria", idCategoria).
					setInteger("tipoFinanciamento", FinanciamentoTipo.PARCELAMENTO_AGUA).
					setParameterList("idsContas", colecaoIdsContas).
					uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}
	
	
	/**
	 * Linha 04 Retorna o valor da categoria acumulado por financiamento por parcelamento esgoto, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(dccg.valorCategoria) " 
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "inner join dbcb.localidade loca "
					+ "inner join dccg.categoria catg "
					+ "inner join dbcb.financiamentoTipo fntp "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and loca.id = :localidade "
					+ "and catg.id = :categoria "
					+ "and fntp.id = :tipoFinanciamento "
					+ "and cnta.id in (:idsContas) ";

			// executa o hql
			retorno = (BigDecimal)
				session.createQuery(consulta).
					setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
					setInteger("localidade", idLocalidade).
					setInteger("categoria", idCategoria).
					setInteger("tipoFinanciamento", FinanciamentoTipo.PARCELAMENTO_ESGOTO).
					setParameterList("idsContas", colecaoIdsContas).
					uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}	

	
	/**
	 * Linha 06 Retorna o valor da categoria acumulado por financiamento juros parcelamentos, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os parâmetros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException {

		// cria o objeto de resumo de faturamento
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(dccg.valorCategoria) " 
					+ "from DebitoCobradoCategoria dccg "
					+ "inner join dccg.debitoCobrado dbcb "
					+ "inner join dbcb.conta cnta "
					+ "inner join dbcb.localidade loca "
					+ "inner join dccg.categoria catg "
					+ "inner join dbcb.financiamentoTipo fntp "
					+ "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					+ "and loca.id = :localidade "
					+ "and catg.id = :categoria "
					+ "and fntp.id = :tipoFinanciamento "
					+ "and cnta.id in (:idsContas) ";

			// executa o hql
			retorno = (BigDecimal)
				session.createQuery(consulta).
					setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
					setInteger("localidade", idLocalidade).
					setInteger("categoria", idCategoria).
					setInteger("tipoFinanciamento", FinanciamentoTipo.JUROS_PARCELAMENTO).
					setParameterList("idsContas", colecaoIdsContas).
					uniqueResult();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}

	
	/**
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamento(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,int idFinanciamentoTipo,Collection<Integer> colecaoIdsContas)
			throws ErroRepositorioException {

		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;
		
		Collection colecaoTiposFinanciamento = new ArrayList();
		colecaoTiposFinanciamento.add(idFinanciamentoTipo);
		colecaoTiposFinanciamento.add(FinanciamentoTipo.ARRASTO_AGUA);
		colecaoTiposFinanciamento.add(FinanciamentoTipo.ARRASTO_ESGOTO);
		colecaoTiposFinanciamento.add(FinanciamentoTipo.ARRASTO_SERVICO);
		
		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(dccg.valorCategoria),lict.sequenciaImpressao,lict.id " 
					 + "from DebitoCobradoCategoria dccg "
					 + "inner join dccg.debitoCobrado dbcb "
					 + "inner join dbcb.conta cnta "
					 + "inner join dbcb.lancamentoItemContabil lict "
					 + "inner join dbcb.localidade loca "
					 + "inner join dccg.categoria catg "
					 + "inner join dbcb.financiamentoTipo fntp "
					 + "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					 + "and loca.id = :localidade "
					 + "and catg.id = :categoria "
					 + "and fntp.id in (:idsTiposFinanciamento) "
					 + "and cnta.id in (:idsContas) "
					 + "group by lict.sequenciaImpressao,lict.id ";

			// executa o hql
			retorno = session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				setParameterList("idsTiposFinanciamento", colecaoTiposFinanciamento).
				setParameterList("idsContas", colecaoIdsContas).
				list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna a coleção de resumo de faturamento criada
		return retorno;

	}	


	/**
	 * Atualiza com o valor de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre, Arthur Carvalho
	 * @date 22/11/2006, 14/06/2007 , 06/12-2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param colecaoIdsContas
	 * @throws ErroRepositorioException
	 */	
	//Override - Metodo sobrescrito na classe RespositorioFinanceiroPostgres
	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos , Integer idTipoPerda ) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
	
		try {
			// declara o tipo de conexao
			Connection jdbcCon = session.connection();
		
	update =	"UPDATE faturamento.conta cnta "
			+	" SET cnta_amreferenciabaixacontabil = " + anoMesReferenciaContabil + " , "
			+   " petp_id = " + idTipoPerda
			+   " where cnta.loca_id = " + idLocalidade
			+   " and cnta.qdra_id = " +  idQuadra
			+   " and cnta.dcst_idatual in ( 0 , 1 ,2 ) " 
			+   " and cnta.cnta_amreferenciaconta < " + anoMesReferenciaContabil
			+   " and cnta.cnta_amreferenciabaixacontabil is null " 
			+   " and exists     "
			+   " (select ctcg.cnta_id "    
			+	" from faturamento.conta_categoria ctcg , cadastro.imovel_cobranca_situacao icbs, financeiro.param_deved_duvid_item pdd " 
			+	" where    (cnta.cnta_id = ctcg.cnta_id and ctcg.catg_id in(1,2,3) ) "
			+	" and (cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null) " 
			+	" and (     (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) "
			+	" and (pdd.pded_id = "+idParametrosDevedoresDuvidosos+")  "
			+   " and abs(MONTHS_BETWEEN ( "
			+	"	to_date( SUBSTR(to_char( " + anoMesReferenciaContabil + " ), 5, 2) || '-01-' || SUBSTR(to_char(" + anoMesReferenciaContabil + "), 1, 4), 'MM-DD-YYYY'), "
			+	"	to_date(to_char(cnta.cnta_dtvencimentoconta, 'MM-DD-YYYY'), 'MM-DD-YYYY') "
			+	"	)) "
			+	"	>= pdd.pdit_nnmeses "
			+	" and    (cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos - cnta.cnta_vlimpostos ) <= pdd.pdit_vlvalorlimite " 
			+	" ) "
			+	" ) "; 
			
			st = jdbcCon.prepareStatement(update);
			
			/*st.setInt(1, anoMesReferenciaContabil);
			st.setInt(2, idLocalidade.intValue());
			st.setInt(3, idQuadra.intValue());
			st.setInt(4, DebitoCreditoSituacao.NORMAL.intValue());
			st.setInt(5, DebitoCreditoSituacao.INCLUIDA.intValue());
			st.setInt(6, DebitoCreditoSituacao.RETIFICADA.intValue());
			st.setInt(7, anoMesReferenciaContabil);		
			st.setInt(8, Categoria.PUBLICO.intValue() );
			st.setInt(9, idParametrosDevedoresDuvidosos.intValue() );
			st.setString(10, anoMesReferenciaContabil+"" );
			st.setString(11, anoMesReferenciaContabil+"" );*/
		
			
		
			// executa o update
			st.executeUpdate();
			session.flush();
			
			
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Atualiza com o valor de referência de baixa contábil
	 * das contas baixadas contabilmente no ano/mês de referência 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre, Arthur Carvalho, Anderson Cabral
	 * @date 22/11/2006, 14/06/2007 , 06/12/2010, 05/02/2013
	 *
	 * @param anoMesReferenciaContabil
	 * @param colecaoIdsContas
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidososPorEsferaDoPoder(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos, Integer idTipoPerda, String colecaoEsferas ) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
	
		try {
			// declara o tipo de conexao
			Connection jdbcCon = session.connection();
			
	update = 	"UPDATE faturamento.conta as conta "
			+	"SET cnta_amreferenciabaixacontabil = ? , "
			+   "petp_id = ? " 
			+ 	"WHERE conta.cnta_id in ( "
			+ 	"SELECT DISTINCT cnta.cnta_id "
			+ 	"FROM faturamento.conta cnta "
			+ 	"INNER JOIN cadastro.cliente_conta cliConta on cnta.cnta_id = cliConta.cnta_id and cliConta.crtp_id = " + ClienteRelacaoTipo.USUARIO + " "
			+ 	"INNER JOIN cadastro.cliente cliente on cliConta.clie_id = cliente.clie_id "
			+ 	"INNER JOIN cadastro.cliente_tipo cliTipo on cliente.cltp_id  = cliTipo.cltp_id "
			+ 	"INNER JOIN financeiro.param_deved_duvid_item pdd on pdd.pded_id = ? "
			+ 	"LEFT JOIN cadastro.imovel_cobranca_situacao icbs on cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null "
			+ 	"WHERE cliTipo.epod_id in (" + colecaoEsferas + ") "
			+ 	"and cnta.loca_id = ? " 
			+ 	"and cnta.qdra_id = ? "
			+ 	"and cnta.dcst_idatual in (?, ?, ?) " 
			+ 	"and cnta.cnta_amreferenciaconta < ? "
			+ 	"and cnta.cnta_amreferenciabaixacontabil is null "
			+ 	"and (coalesce(cnta.cnta_vlagua,0) + coalesce(cnta.cnta_vlesgoto,0) + coalesce(cnta.cnta_vldebitos,0) - coalesce(cnta.cnta_vlcreditos,0) - coalesce(cnta.cnta_vlimpostos,0)) <= pdd.pdit_vlvalorlimite "
			+ 	"and ( "
			+ 	"		(cast(substr(?, 1, 4) as integer) - extract(year from cnta.cnta_dtvencimentoconta)) * 12 + " 
			+ 	"		(cast(substr(?, 5, 2) as integer) - extract(month from cnta.cnta_dtvencimentoconta)) "
			+ 	"	 ) >= pdd.pdit_nnmeses "
			+ 	"and (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) )";
		
			st = jdbcCon.prepareStatement(update);
			
			st.setInt(1, anoMesReferenciaContabil);
			st.setInt(2, idTipoPerda);
			st.setInt(3, idParametrosDevedoresDuvidosos );
			st.setInt(4, idLocalidade);
			st.setInt(5, idQuadra);
			st.setInt(6, DebitoCreditoSituacao.NORMAL);
			st.setInt(7, DebitoCreditoSituacao.INCLUIDA);
			st.setInt(8, DebitoCreditoSituacao.RETIFICADA);
			st.setInt(9, anoMesReferenciaContabil);				
			
			st.setInt(10, anoMesReferenciaContabil );
			st.setInt(11, anoMesReferenciaContabil );
//			st.setString(14, colecaoEsferas);
			
			// executa o update
			st.executeUpdate();
			session.flush();
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}	

//	*******************************Caern	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Flávio Cordeiro
	 * @date 06/06/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	//Override - Metodo sobrescrito na classe RepositorioFinanceiroPostgresHBM
	public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException{
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select  lcor.numeroCartao ,"//0
					   + " lcor.codigoTipo ,"//1
					   + " lcor.numeroFolha ,"//2
					   + " cntc.indicadorLinha ,"//3
					   + " cntc.prefixoContabil ,"//4
					   + " cntc.numeroConta ,"//5
					   + " cntc.numeroDigito ,"//6
					   + " cntc.numeroTerceiros ,"//7
					   + " lcor.codigoReferencia ,"//8
					   + " sum(lcti.valorLancamento) ,"//9
					   + " lcti.indicadorDebitoCredito ,"//10
					   + " lcor.numeroCartao2 ,"//11
					   + " lcor.numeroVersao ,"//12
					   + " CASE WHEN cntc.indicadorCentroCusto = 1 THEN loca.codigoCentroCusto" 
					   + " ELSE null END as col_13_0_, "//13
					   + " cntc.indicadorCentroCusto "//14
					   + " from LancamentoContabilItem lcti " // lançamento contabil item
					   + " left join lcti.lancamentoContabil lcnb" //lançamento contábil
					   + " left join lcnb.localidade loca" //localidade
					   + " left join lcnb.lancamentoOrigem lcor" //lançamento origem
					   + " left join lcti.contaContabil cntc" //conta contabil
					   + " where lcnb.anoMes= :anoMes and lcor.id= :idLancamentoOrigem"
					   + " group by lcti.indicadorDebitoCredito,"
					   + " cntc.numeroConta, cntc.numeroDigito, cntc.numeroTerceiros ,"
					   + " lcor.numeroCartao, lcor.codigoTipo,"
					   + " lcor.numeroFolha, cntc.indicadorLinha, cntc.prefixoContabil ,"
					   + " lcor.codigoReferencia, "
					   + " lcor.numeroCartao2, lcor.numeroVersao, case when cntc.indicadorcentrocusto = 1 then loca.codigocentrocusto  else null end, "
					   + " cntc.indicadorCentroCusto";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMes",new Integer(anoMes))
					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
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
	 * Obtém os dados do resumo dos devedores duvidosos 
	 * a partir do ano e mês de referência contábil e da localidade.
	 *
	 * [UC0486] - Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre 
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idTipoPerda) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT loca.id, lctp.id, lcit.id, lict.id, catg.id, SUM(rded.valorBaixado) "
					+ "FROM ResumoDevedoresDuvidosos rded "
					+ "LEFT JOIN rded.localidade loca "
					+ "LEFT JOIN rded.lancamentoTipo lctp "
					+ "LEFT JOIN rded.lancamentoItem lcit "
					+ "LEFT JOIN rded.lancamentoItemContabil lict "
					+ "LEFT JOIN rded.categoria catg "
					+ "WHERE rded.anoMesReferenciaContabil = :anoMesReferenciaContabil " 
					+ "AND loca.id = :idLocalidade "
					+ "and rded.perdasTipo = :idTipoPerda "
					+ "GROUP BY loca.id, lctp.id, lcit.id, lict.id, catg.id "
					+ "ORDER BY loca.id, lctp.id, lcit.id, lict.id, catg.id ";

			retorno = session.createQuery(consulta)
						.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
						.setInteger("idLocalidade",idLocalidade)
						.setInteger("idTipoPerda",idTipoPerda)
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
	 * [UC0486] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * Pesquisa os parâmetros contábil dos devedores duvidosos.
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilDevedoresDuvidosos(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento,
			Integer idTipoPerda ) throws ErroRepositorioException{
		
		Object[] retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT ddcp.contaContabilDebito.id,ddcp.contaContabilCredito.id,ddcp.descricaoHistoricoDebito,ddcp.descricaoHistoricoCredito " 
					+ "FROM DevedoresDuvidososContabilParametro ddcp "
					+ "LEFT JOIN ddcp.categoria catg "
					+ "LEFT JOIN ddcp.lancamentoItemContabil lict "
					+ "LEFT JOIN ddcp.lancamentoItem lcit "
					+ "LEFT JOIN ddcp.lancamentoTipo lctp "
					+ "WHERE catg.id = :idCategoria AND " 
					+       "lcit.id = :idItemLancamento AND " 
					+		"lctp.id = :idTipoLancamento AND "
					+       "ddcp.perdasTipo = :idTipoPerda ";
					
					if(idLancamentoItemContabil == null){
						consulta = consulta + "AND lict.id is null ";
					}else{
						consulta = consulta + "AND lict.id = :idLancamentoItemContabil ";
					}
				
			if(idLancamentoItemContabil == null){		
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
						.setInteger("idTipoPerda",idTipoPerda)
						.uniqueResult();
			}else{
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idLancamentoItemContabil",idLancamentoItemContabil)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
						.setInteger("idTipoPerda",idTipoPerda)
						.uniqueResult();
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
	 * Pesquisa a coleção de ids das localidades para processar os lançamentos 
	 * contábeis dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT DISTINCT loca.id "
				+ "FROM ResumoDevedoresDuvidosos rded "
				+ "INNER JOIN rded.localidade loca "
				+ "WHERE rded.anoMesReferenciaContabil = :anoMesReferenciaContabil ";

		retorno = session.createQuery(consulta)
				.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
				.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Pesquisa uma coleção de ids de lançamentos contábeis por localidade.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idLancamentoOrigem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLancamentosContabeis(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem)throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT lcnb.id "
				+ "FROM LancamentoContabil lcnb "
				+ "INNER JOIN lcnb.localidade loca "
				+ "INNER JOIN lcnb.lancamentoOrigem lcor "
				+ "WHERE lcnb.anoMes = :anoMesReferenciaContabil "
				+ "AND loca.id = :idLocalidade "
				+ "AND lcor.id = :idLancamentoOrigem ";

		retorno = session.createQuery(consulta)
				.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
				.setInteger("idLocalidade", idLocalidade)
				.setInteger("idLancamentoOrigem", idLancamentoOrigem)
				.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * Remove os Itens do lançamento contábil.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param idLancamentoContabil
	 * @throws ErroRepositorioException
	 */
	public void removerItensLancamentoContabil(Integer idLancamentoContabil) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete LancamentoContabilItem lcti "
						  + "where lcti.lancamentoContabil.id = :idLancamentoContabil ";

			session.createQuery(delete).setInteger("idLancamentoContabil", idLancamentoContabil)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	/**
	 * Remove os Lançamentos Contábeis.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param colecaoIdsLancamentosContabeis
	 * @throws ErroRepositorioException
	 */
	public void removerLancamentosContabeis(Collection<Integer> colecaoIdsLancamentosContabeis) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete LancamentoContabil lcnb "
						  + "where lcnb.id in (:idsLancamentosContabeis) ";

			session.createQuery(delete).setParameterList("idsLancamentosContabeis", colecaoIdsLancamentosContabeis)
			.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	/**
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento serviço, 
	 * de acordo com o ano/mês de referência baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServico(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,Collection<Integer> colecaoIdsContas) throws ErroRepositorioException {

		// cria a variável que vai armazenar a coleção pesquisada
		Collection retorno = null;
		
		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "select sum(dccg.valorCategoria),lict.sequenciaImpressao,lict.id " 
					 + "from DebitoCobradoCategoria dccg "
					 + "inner join dccg.debitoCobrado dbcb "
					 + "inner join dbcb.conta cnta "
					 + "inner join dbcb.lancamentoItemContabil lict "
					 + "inner join dbcb.localidade loca "
					 + "inner join dccg.categoria catg "
					 + "inner join dbcb.financiamentoTipo fntp "
					 + "where cnta.referenciaBaixaContabil = :anoMesReferencia "
					 + "and loca.id = :localidade "
					 + "and catg.id = :categoria "
					 + "and fntp.id = :idTipoFinanciamento "
					 + "and cnta.id in (:idsContas) "
					 + "group by lict.sequenciaImpressao,lict.id ";

			// executa o hql
			retorno = session.createQuery(consulta).
				setInteger("anoMesReferencia", anoMesReferenciaBaixaContabil).
				setInteger("localidade", idLocalidade).
				setInteger("categoria", idCategoria).
				setInteger("idTipoFinanciamento", FinanciamentoTipo.PARCELAMENTO_SERVICO).
				setParameterList("idsContas", colecaoIdsContas).
				list();

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

		// retorna a coleção de resumo de faturamento criada
		return retorno;

	}	
	
//////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstado(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			
			consulta = "select sum(rdd.valorBaixado), " //0
				+ "lt.descricao, " //1
				+ "li.descricao, " //2
				+ "lic.descricao, " //3
				+ "lt.indicadorImpressao, " //4
				+ "lt.indicadorTotal, " //5
				+ "lt.id, " //6
				+ "lt.lancamentoTipo.id, " //7
				+ "rdd.categoria.id, " //8
				+ "rdd.sequencialTipoLancamento, " //9
				+ "rdd.sequencialItemTipoLancamento " //10
				+ "from ResumoDevedoresDuvidosos rdd "
				+ "left join rdd.lancamentoTipo lt "
				+ "left join rdd.lancamentoItem li "
				+ "left join rdd.lancamentoItemContabil lic "
				+ "inner join rdd.perdasTipo perdas "
				+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and "
				+ "perdas.id = :tipoPerda and "
				+ "(rdd.categoria.id = 1 or "
				+ "rdd.categoria.id = 2 or "
				+ "rdd.categoria.id = 3 or "
				+ "rdd.categoria.id = 4) "
				+ "group by lt.descricao,li.descricao,lic.descricao,lt.indicadorImpressao,lt.indicadorTotal,lt.id,lt.lancamentoTipo.id,rdd.categoria.id,rdd.sequencialTipoLancamento,rdd.sequencialItemTipoLancamento  "
				+ "order by rdd.sequencialTipoLancamento,rdd.sequencialItemTipoLancamento,rdd.categoria.id ";
		

			retorno = session.createQuery(consulta).setInteger("anoMesReferencia", anoMesReferencia).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorGerenciaRegional(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select sum(rdd.valorBaixado), "//0
					+ "lt.descricao, "//1
					+ "li.descricao, "//2
					+ "lic.descricao, "//3
					+ "lt.indicadorImpressao, "//4
					+ "lt.indicadorTotal, "//5
					+ "lt.id, "//6
					+ "lt.lancamentoTipo.id, "//7
					+ "rdd.categoria.id, "//8
					+ "rdd.gerenciaRegional.nome, "//9
					+ "rdd.gerenciaRegional.id, "//10
					+ "rdd.sequencialTipoLancamento, "//11
					+ "rdd.sequencialItemTipoLancamento "//12
					+ "from ResumoDevedoresDuvidosos rdd "
					+ "left join rdd.lancamentoTipo lt "
					+ "left join rdd.lancamentoItem li "
					+ "left join rdd.lancamentoItemContabil lic "
					+ "inner join rdd.perdasTipo perdas "
					+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and  "
					+ "perdas.id = :tipoPerda and "
					+ "(rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
					+ "group by rdd.gerenciaRegional.nome, rdd.gerenciaRegional.id, lt.descricao, li.descricao, "
					+ "lic.descricao, lt.indicadorImpressao,lt.indicadorTotal, lt.id,lt.lancamentoTipo.id, "
					+ "rdd.categoria.id, rdd.sequencialTipoLancamento,rdd.sequencialItemTipoLancamento "
					+ "order by rdd.gerenciaRegional.id, rdd.sequencialTipoLancamento, "
					+ "rdd.sequencialItemTipoLancamento, rdd.categoria.id ";

			retorno = session.createQuery(consulta).setInteger("anoMesReferencia", anoMesReferencia).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta =  "select sum(rdd.valorBaixado), "//0
				+ "lt.descricao, "//1
				+ "li.descricao, "//2
				+ "lic.descricao, "//3
				+ "lt.indicadorImpressao, "//4
				+ "lt.indicadorTotal, "//5
				+ "lt.id, "//6
				+ "lt.lancamentoTipo.id, "//7
				+ "rdd.categoria.id, "//8
//				+ "rdd.unidadeNegocio.nome, "//9
//				+ "rdd.unidadeNegocio.id, "//10
				+ "rdd.localidade.unidadeNegocio.nome, "//9
				+ "rdd.localidade.unidadeNegocio.id, "//10
				+ "rdd.sequencialTipoLancamento, "//11
				+ "rdd.sequencialItemTipoLancamento, "//12
				+ "rdd.gerenciaRegional.nome, "//13
				+ "rdd.gerenciaRegional.id "//14
				+ "from ResumoDevedoresDuvidosos rdd "
				+ "left join rdd.lancamentoTipo lt "
				+ "left join rdd.lancamentoItem li "
				+ "left join rdd.lancamentoItemContabil lic "
				+ "inner join rdd.perdasTipo perdas "
				+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and "
				+ "perdas.id = :tipoPerda and "
				+ "(rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
				+ "group by rdd.localidade.unidadeNegocio.nome,rdd.localidade.unidadeNegocio.id,"
				+ "lt.descricao, li.descricao, lic.descricao, lt.indicadorImpressao, lt.indicadorTotal, lt.id, "
				+ "lt.lancamentoTipo.id, rdd.categoria.id, rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento, "
				+ "rdd.gerenciaRegional.nome, rdd.gerenciaRegional.id "
				+ "order by rdd.gerenciaRegional.nome, rdd.localidade.unidadeNegocio.nome, rdd.sequencialTipoLancamento, "
				+ "rdd.sequencialItemTipoLancamento, rdd.categoria.id ";

			retorno = session.createQuery(consulta).setInteger("anoMesReferencia", anoMesReferencia).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select sum(rdd.valorBaixado), "//0
					+ "lt.descricao, " //1
					+ "li.descricao, " //2
					+ "lic.descricao, " //3
					+ "lt.indicadorImpressao, "//4 
					+ "lt.indicadorTotal, " //5
					+ "lt.id, " //6
					+ "lt.lancamentoTipo.id, "//7 
					+ "rdd.categoria.id, " //8
					+ "rdd.gerenciaRegional.nome, "//9 
					+ "rdd.gerenciaRegional.id, " //10
					+ "rdd.localidade.descricao, " //11
					+ "rdd.localidade.id, " //12
					+ "rdd.sequencialTipoLancamento, "//13 
					+ "rdd.sequencialItemTipoLancamento, "//14 
					+ "rdd.localidade.unidadeNegocio.nome, " //15
					+ "rdd.localidade.unidadeNegocio.id " //16
					+ "from ResumoDevedoresDuvidosos rdd "
					+ "left join rdd.lancamentoTipo lt "
					+ "left join rdd.lancamentoItem li "
					+ "left join rdd.lancamentoItemContabil lic "
					+ "inner join rdd.perdasTipo perdas "
					+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and "
					+ "perdas.id = :tipoPerda and "
					+ "(rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
					+ "group by rdd.gerenciaRegional.nome, rdd.gerenciaRegional.id, rdd.localidade.descricao, rdd.localidade.id, "
					+ "lt.descricao, li.descricao, lic.descricao, lt.indicadorImpressao, lt.indicadorTotal, lt.id, "
					+ "lt.lancamentoTipo.id, rdd.categoria.id, rdd.sequencialTipoLancamento, " 
					+ "rdd.sequencialItemTipoLancamento,rdd.localidade.unidadeNegocio.nome, rdd.localidade.unidadeNegocio.id "
					+ " order by " 
					+ " rdd.localidade.id,"
					+ " rdd.localidade.unidadeNegocio.id,"
					+ " rdd.gerenciaRegional.id," 
					+ " rdd.sequencialTipoLancamento,"
					+ " rdd.sequencialItemTipoLancamento," 
					+ " rdd.categoria.id";
				

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(
			int anoMesReferencia, Integer gerenciaRegional, int tipoPerda)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select sum(rdd.valorBaixado), "//0
					+ "lt.descricao, "//1
					+ "li.descricao, "//2
					+ "lic.descricao, "//3
					+ "lt.indicadorImpressao, "//4
					+ "lt.indicadorTotal, "//5
					+ "lt.id, "//6
					+ "lt.lancamentoTipo.id, "//7
					+ "rdd.categoria.id, "//8
					+ "rdd.gerenciaRegional.nome, "//9
					+ "rdd.gerenciaRegional.id, "//10
					+ "rdd.sequencialTipoLancamento, "//11
					+ "rdd.sequencialItemTipoLancamento "//12
					+ "from ResumoDevedoresDuvidosos rdd "
					+ "left join rdd.lancamentoTipo lt "
					+ "left join rdd.lancamentoItem li "
					+ "left join rdd.lancamentoItemContabil lic "
					+ "inner join rdd.perdasTipo perdas "
					+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and rdd.gerenciaRegional = :gerenciaRegional and "
					+ "perdas.id = :tipoPerda "
					+ "and (rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
					+ "group by lt.descricao, li.descricao, lic.descricao, lt.indicadorImpressao, "
					+ "lt.indicadorTotal, lt.id, lt.lancamentoTipo.id, rdd.categoria.id, rdd.gerenciaRegional.nome, "
					+ "rdd.gerenciaRegional.id, rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento "
					+ "order by rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento, rdd.categoria.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"gerenciaRegional", gerenciaRegional).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegionalPorLocalidade(
			int anoMesReferencia, Integer gerenciaRegional, int tipoPerda)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select sum(rdd.valorBaixado), "//0
					+ "lt.descricao, "//1
					+ "li.descricao, "//2
					+ "lic.descricao, "//3
					+ "lt.indicadorImpressao, "//4
					+ "lt.indicadorTotal, "//5
					+ "lt.id, "//6
					+ "lt.lancamentoTipo.id, "//7
					+ "rdd.categoria.id, "//8
					+ "rdd.gerenciaRegional.nome, "//9
					+ "rdd.gerenciaRegional.id, "//10
					+ "rdd.localidade.descricao, "//11
					+ "rdd.localidade.id, "//12
					+ "rdd.sequencialTipoLancamento, "//13
					+ "rdd.sequencialItemTipoLancamento "//14
					+ "from ResumoDevedoresDuvidosos rdd "
					+ "left join rdd.lancamentoTipo lt left join rdd.lancamentoItem li "
					+ "left join rdd.lancamentoItemContabil lic "
					+ "inner join rdd.perdasTipo perdas "
					+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and rdd.gerenciaRegional = :gerenciaRegional and "
					+ "perdas.id = :tipoPerda and "
					+ "(rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
					+ "group by rdd.localidade.descricao, rdd.localidade.id,  lt.descricao, "
					+ "li.descricao, lic.descricao, lt.indicadorImpressao, lt.indicadorTotal, lt.id, "
					+ "lt.lancamentoTipo.id, rdd.categoria.id, rdd.gerenciaRegional.nome, rdd.gerenciaRegional.id, "
					+ "rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento  "
					+ "order by rdd.localidade.id, rdd.sequencialTipoLancamento,  "
					+ "rdd.sequencialItemTipoLancamento, rdd.categoria.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"gerenciaRegional", gerenciaRegional).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer unidadeNegocio, int tipoPerda) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select sum(rdd.valorBaixado), "//0
				+ "lt.descricao, "//1
				+ "li.descricao, "//2
				+ "lic.descricao, "//3
				+ "lt.indicadorImpressao, "//4
				+ "lt.indicadorTotal, "//5
				+ "lt.id, "//6
				+ "lt.lancamentoTipo.id, "//7
				+ "rdd.categoria.id, "//8
				+ "rdd.localidade.unidadeNegocio.nome, "//9
				+ "rdd.localidade.unidadeNegocio.id, "//10
				+ "rdd.sequencialTipoLancamento, "//11
				+ "rdd.sequencialItemTipoLancamento, "//12
				+ "rdd.gerenciaRegional.nome," //13
				+ "rdd.gerenciaRegional.id " //14
				+ "from ResumoDevedoresDuvidosos rdd "
				+ "left join rdd.lancamentoTipo lt "
				+ "left join rdd.lancamentoItem li "
				+ "left join rdd.lancamentoItemContabil lic "
				+ "inner join rdd.perdasTipo perdas "
				+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and "
				+ "rdd.localidade.unidadeNegocio = :unidadeNegocio and "
				+ "perdas.id = :tipoPerda and "
				+ "(rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
				+ "group by  lt.descricao, li.descricao, lic.descricao, lt.indicadorImpressao, lt.indicadorTotal, lt.id, lt.lancamentoTipo.id, "
				+ "rdd.categoria.id, rdd.localidade.unidadeNegocio.nome, rdd.localidade.unidadeNegocio.id, rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento, rdd.gerenciaRegional.nome, rdd.gerenciaRegional.id  "
				+ "order by rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento, rdd.categoria.id ";
			
			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger("unidadeNegocio",unidadeNegocio).setInteger("tipoPerda", tipoPerda).list();

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
	 * Consulta ResumoDevedoresDuvidosos para a geração do relatório 
	 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
	 * de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorLocalidade(
			int anoMesReferencia, Integer localidade, int tipoPerda)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select sum(rdd.valorBaixado), "//0
					+ "lt.descricao, "//1
					+ "li.descricao, "//2
					+ "lic.descricao, "//3
					+ "lt.indicadorImpressao, "//4
					+ "lt.indicadorTotal, "//5
					+ "lt.id, "//6
					+ "lt.lancamentoTipo.id, "//7
					+ "rdd.categoria.id, "//8
					+ "rdd.localidade.descricao, "//9
					+ "rdd.localidade.id, "//10
					+ "rdd.sequencialTipoLancamento, "//11
					+ "rdd.sequencialItemTipoLancamento "//12
					+ "from ResumoDevedoresDuvidosos rdd "
					+ "left join rdd.lancamentoTipo lt "
					+ "left join rdd.lancamentoItem li "
					+ "left join rdd.lancamentoItemContabil lic "
					+ "inner join rdd.perdasTipo perdas "
					+ "where rdd.anoMesReferenciaContabil = :anoMesReferencia and "
					+ "rdd.localidade = :localidade and "
					+ "perdas.id = :tipoPerda and "
					+ "(rdd.categoria.id = 1 or rdd.categoria.id = 2 or rdd.categoria.id = 3 or rdd.categoria.id = 4) "
					+ "group by  lt.descricao, li.descricao, lic.descricao, lt.indicadorImpressao, lt.indicadorTotal, lt.id, lt.lancamentoTipo.id, "
					+ "rdd.categoria.id, rdd.localidade.descricao, rdd.localidade.id, rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento "
					+ "order by rdd.sequencialTipoLancamento, rdd.sequencialItemTipoLancamento, rdd.categoria.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).setInteger(
					"localidade", localidade).setInteger("tipoPerda", tipoPerda).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Remove as contas a receber contábil
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void removerContasAReceberContabil(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "DELETE FROM ContaAReceberContabil contaAReceberContabil "
					+ " WHERE contaAReceberContabil.localidade.id = :idLocalidade "
					+ " and contaAReceberContabil.anoMesReferencia = :anoMesReferencia";
			
			session.createQuery(consulta).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferencia",
					anoMesReferenciaContabil).executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores de água e esgoto pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
					+ " contaCat.catg_id as idCategoria, sum(contaCat.ctcg_vlagua) as valorAgua, sum(contaCat.ctcg_vlesgoto) as valorEsgoto "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.conta conta "
					+ " on loca.loca_id = conta.loca_id "
					+ " INNER JOIN faturamento.conta_categoria contaCat "
					+ " on conta.cnta_id = contaCat.cnta_id "
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and conta.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
					+ " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, contacat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorAgua",
					Hibernate.BIG_DECIMAL).addScalar("valorEsgoto",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores de impostos pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorImpostos(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " contaCat.catg_id as idCategoria, "
					+ " sum( round ( ( (cnta_vlimpostos / (select sum(ctcg_qteconomia) from faturamento.conta_categoria where cnta_id = conta.cnta_id)) * contaCat.ctcg_qteconomia ), 2 )) as valorImpostos "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.conta conta "
					+ " on loca.loca_id = conta.loca_id "
					+ " INNER JOIN faturamento.conta_categoria contaCat " 
					+ " on conta.cnta_id = contaCat.cnta_id "
					+ " WHERE loca.loca_id = :idLocalidade and conta.cnta_vlimpostos is not null and conta.cnta_vlimpostos > 0 "
					+ " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and conta.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
					+ " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in ( :situacaoRetificada , :situacaoIncluida ) ) ) ) ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, contacat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorImpostos",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos cobrados para serviços pela gerência,
     * localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaServico(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " debCobCat.catg_id as idCategoria, debCob.lict_id as idLancamentoItemContabil, "
                    + " sum(debCobCat.dccg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.debito_cobrado debCob "
                    + " on debCob.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.debito_cobrado_categoria debCobCat "
                    + " on debCobCat.dbcb_id = debCob.dbcb_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada, :situacaoIncluida) ) ) ) ) "
                    + " and ( debCob.fntp_id = :servico ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, debcobcat.catg_id, debcob.lict_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria, idLancamentoItemContabil ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("idLancamentoItemContabil",
					Hibernate.INTEGER).addScalar("valorCategoria",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
							"servico", FinanciamentoTipo.SERVICO_NORMAL).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores dos débitos cobrados para parcelamentos pela gerência,
	 * localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
    public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaParcelamento(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " debCobCat.catg_id as idCategoria, debCob.lict_id as idLancamentoItemContabil, "
                    + " sum(debCobCat.dccg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca"
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.debito_cobrado debCob "
                    + " on debCob.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.debito_cobrado_categoria debCobCat "
                    + " on debCobCat.dbcb_id = debCob.dbcb_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and debCob.fntp_id in ( :parcelamentoAgua, "
                    + " :parcelamentoEsgoto, :parcelamentoServico, "
                    + " :arrastoAgua, :arrastoEsgoto, "
                    + " :arrastoServico, :jurosParcelamento ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, debcobcat.catg_id, debcob.lict_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria, idLancamentoItemContabil ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("idLancamentoItemContabil",
					Hibernate.INTEGER).addScalar("valorCategoria",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "parcelamentoAgua",
                            FinanciamentoTipo.PARCELAMENTO_AGUA).setInteger(
                            "parcelamentoEsgoto",
                            FinanciamentoTipo.PARCELAMENTO_ESGOTO).setInteger(
                            "parcelamentoServico",
                            FinanciamentoTipo.PARCELAMENTO_SERVICO).setInteger(
                            "arrastoAgua", FinanciamentoTipo.ARRASTO_AGUA)
                    .setInteger("arrastoEsgoto",
                            FinanciamentoTipo.ARRASTO_ESGOTO)
                    .setInteger("arrastoServico",
                            FinanciamentoTipo.ARRASTO_SERVICO).setInteger(
                            "jurosParcelamento",
                            FinanciamentoTipo.JUROS_PARCELAMENTO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores das guias de pagamento para entradas de parcelamento
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaEntradaParcelamento(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " gpagCat.catg_id as idCategoria, sum(gpagCat.gpcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.guia_pagamento gpag "
                    + " on loca.loca_id = gpag.loca_id "
                    + " INNER JOIN faturamento.guia_pagamento_categoria gpagCat "
                    + " on gpag.gpag_id = gpagCat.gpag_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( gpag.gpag_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and gpag.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( gpag.gpag_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and gpag.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and gpag.dcst_idanterior is null ) ) "
                    + " and ( gpag.fntp_id = :entradaParcelamento ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, gpagcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "entradaParcelamento",
                            FinanciamentoTipo.ENTRADA_PARCELAMENTO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores das guias de pagamento para serviços pela gerência,
     * localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaServico(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " gpagCat.catg_id as idCategoria, gpag.lict_id as idLancamentoItemContabil, "
                    + " sum(gpagCat.gpcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.guia_pagamento gpag "
                    + " on loca.loca_id = gpag.loca_id "
                    + " INNER JOIN faturamento.guia_pagamento_categoria gpagCat "
                    + " on gpag.gpag_id = gpagCat.gpag_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( gpag.gpag_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and gpag.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( gpag.gpag_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and gpag.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and gpag.dcst_idanterior is null ) ) "
                    + " and ( gpag.fntp_id = :servico ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, gpagcat.catg_id, gpag.lict_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria, idLancamentoItemContabil ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("idLancamentoItemContabil",
					Hibernate.INTEGER).addScalar("valorCategoria",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "servico", FinanciamentoTipo.SERVICO_NORMAL).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos realizados para pagamentos em duplicidade
     * ou em excesso pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaPagamentoExcesso(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " credRealizadoCat.catg_id as idCategoria, sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.credito_realizado credRealizado "
                    + " on credRealizado.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat "
                    + " on credRealizadoCat.crrz_id = credRealizado.crrz_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and ( credRealizado.crog_id = :pagamentoEmExcesso ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "pagamentoEmExcesso",
                            CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos realizados para descontos no parcelamento
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoParcelamento(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " credRealizadoCat.catg_id as idCategoria, sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.credito_realizado credRealizado "
                    + " on credRealizado.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat "
                    + " on credRealizadoCat.crrz_id = credRealizado.crrz_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and ( credRealizado.crog_id = :descontoParcelamento ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "descontoParcelamento",
                            CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos realizados para descontos condicionais
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoCondicional(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " credRealizadoCat.catg_id as idCategoria, sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.credito_realizado credRealizado "
                    + " on credRealizado.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat "
                    + " on credRealizadoCat.crrz_id = credRealizado.crrz_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and ( credRealizado.crog_id = :descontoCondicional ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "descontoCondicional",
                            CreditoOrigem.DESCONTOS_CONDICIONAIS).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos realizados para descontos incondicionais
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoIncondicional(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " credRealizadoCat.catg_id as idCategoria, sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.credito_realizado credRealizado "
                    + " on credRealizado.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat "
                    + " on credRealizadoCat.crrz_id = credRealizado.crrz_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and ( credRealizado.crog_id = :descontoIncondicional ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "descontoIncondicional",
                            CreditoOrigem.DESCONTOS_INCONDICIONAIS).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos realizados para ajustes para zerar conta
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaAjusteZerarConta(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " credRealizadoCat.catg_id as idCategoria, sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.credito_realizado credRealizado "
                    + " on credRealizado.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat "
                    + " on credRealizadoCat.crrz_id = credRealizado.crrz_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and ( credRealizado.crog_id = :ajusteZerarConta ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "ajusteZerarConta",
                            CreditoOrigem.AJUSTES_PARA_ZERAR_CONTA).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos realizados para devoluções pela gerência,
     * localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDevolucao(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " credRealizadoCat.catg_id as idCategoria, sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.conta conta "
                    + " on loca.loca_id = conta.loca_id "
                    + " INNER JOIN faturamento.credito_realizado credRealizado "
                    + " on credRealizado.cnta_id = conta.cnta_id "
                    + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat "
                    + " on credRealizadoCat.crrz_id = credRealizado.crrz_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and conta.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and ( conta.dcst_idanterior is null or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
                    + " and credRealizado.crog_id in ( :devolucaoAgua, "
                    + " :devolucaoEsgoto, :servicoPagoIndevidamente, "
                    + " :devolucaoJuros ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger("devolucaoAgua",
                            CreditoOrigem.DEVOLUCAO_TARIFA_AGUA)
                    .setInteger("devolucaoEsgoto",
                            CreditoOrigem.DEVOLUCAO_TARIFA_ESGOTO)
                    .setInteger(
                            "servicoPagoIndevidamente",
                            CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE)
                    .setInteger("devolucaoJuros",
                            CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para serviço pela gerência,
     * localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaServico(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " dbacCat.catg_id as idCategoria, dbac.lict_id as idLancamentoItemContabil, " 
                    + " sum( " 
        			+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ "				THEN 1 " 
        			+ "				ELSE 0 " 
        			+ "			END ) ) ) < 13 ) "
        			+ "		 THEN "
        			+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ "				THEN 1 "
        			+ "		 		ELSE 0 "
        			+ " 		END ) ) ) "  
        			+ " 	ELSE " 
        			+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
        			+ " 	END " 
        			+ " ) as valorCurtoPrazo, " 
        			+ " sum( " 
        			+ " 	CASE WHEN ( "
        			+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ " 			THEN 1 "
        			+ "				ELSE 0 " 
        			+ " 		END) ) ) < 13 ) " 
        			+ " 	THEN " 
        			+ "			0.00 " 
        			+ " 	ELSE " 
        			+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
        			+ " 			THEN 1 "
        			+ " 			ELSE 0 "
        			+ " 		END ) ) ) "
        			+ " 	END " 
        			+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.fntp_id = :servico ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id, dbac.lict_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria, idLancamentoItemContabil ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("idLancamentoItemContabil",
                    Hibernate.INTEGER).addScalar("valorCurtoPrazo",
                    Hibernate.BIG_DECIMAL).addScalar("valorLongoPrazo",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "servico", FinanciamentoTipo.SERVICO_NORMAL).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para documentos emitidos pela
     * gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaDocumentosEmitidos(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = " SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
        			+ " dbacCat.catg_id as idCategoria, " 
        			+ " sum( " 
        			+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ "				THEN 1 " 
        			+ "				ELSE 0 " 
        			+ "			END ) ) ) < 13 ) "
        			+ "		 THEN "
        			+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ "				THEN 1 "
        			+ "		 		ELSE 0 "
        			+ " 		END ) ) ) "  
        			+ " 	ELSE " 
        			+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
        			+ " 	END " 
        			+ " ) as valorCurtoPrazo, " 
        			+ " sum( " 
        			+ " 	CASE WHEN ( "
        			+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ " 			THEN 1 "
        			+ "				ELSE 0 " 
        			+ " 		END) ) ) < 13 ) " 
        			+ " 	THEN " 
        			+ "			0.00 " 
        			+ " 	ELSE " 
        			+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
        			+ " 			THEN 1 "
        			+ " 			ELSE 0 "
        			+ " 		END ) ) ) "
        			+ " 	END " 
        			+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.pcgr_id = :documentoEmitido ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCurtoPrazo",
                    Hibernate.BIG_DECIMAL).addScalar("valorLongoPrazo",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "documentoEmitido",
                            ParcelamentoGrupo.DOCUMENTOS_EMITIDOS).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para financiamentos a cobrar de
     * curto prazo pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosCurtoPrazo(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = " SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
    				+ " dbacCat.catg_id as idCategoria, " 
    				+ " sum( " 
    				+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ "				THEN 1 " 
    				+ "				ELSE 0 " 
    				+ "			END ) ) ) < 13 ) "
    				+ "		 THEN "
    				+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ "				THEN 1 "
    				+ "		 		ELSE 0 "
    				+ " 		END ) ) ) "  
    				+ " 	ELSE " 
    				+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
    				+ " 	END " 
    				+ " ) as valorCurtoPrazo, " 
    				+ " sum( " 
    				+ " 	CASE WHEN ( "
    				+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ " 			THEN 1 "
    				+ "				ELSE 0 " 
    				+ " 		END) ) ) < 13 ) " 
    				+ " 	THEN " 
    				+ "			0.00 " 
    				+ " 	ELSE " 
    				+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
    				+ " 			THEN 1 "
    				+ " 			ELSE 0 "
    				+ " 		END ) ) ) "
    				+ " 	END " 
    				+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.pcgr_id = :financiamentoCurtoPrazo ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCurtoPrazo", Hibernate.BIG_DECIMAL)
                    .addScalar("valorLongoPrazo", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger(
                            "financiamentoCurtoPrazo",
                            ParcelamentoGrupo.FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para financiamentos a cobrar de
     * longo prazo pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosLongoPrazo(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = " SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
    				+ " dbacCat.catg_id as idCategoria, " 
    				+ " sum( " 
    				+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ "				THEN 1 " 
    				+ "				ELSE 0 " 
    				+ "			END ) ) ) < 13 ) "
    				+ "		 THEN "
    				+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ "				THEN 1 "
    				+ "		 		ELSE 0 "
    				+ " 		END ) ) ) "  
    				+ " 	ELSE " 
    				+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
    				+ " 	END " 
    				+ " ) as valorCurtoPrazo, " 
    				+ " sum( " 
    				+ " 	CASE WHEN ( "
    				+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ " 			THEN 1 "
    				+ "				ELSE 0 " 
    				+ " 		END) ) ) < 13 ) " 
    				+ " 	THEN " 
    				+ "			0.00 " 
    				+ " 	ELSE " 
    				+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
    				+ " 			THEN 1 "
    				+ " 			ELSE 0 "
    				+ " 		END ) ) ) "
    				+ " 	END " 
    				+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.pcgr_id = :financiamentoLongoPrazo ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCurtoPrazo", Hibernate.BIG_DECIMAL)
                    .addScalar("valorLongoPrazo", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger(
                            "financiamentoLongoPrazo",
                            ParcelamentoGrupo.FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para parcelamentos a cobrar de
     * curto prazo pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosCurtoPrazo(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = " SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
        			+ " dbacCat.catg_id as idCategoria, " 
        			+ " sum( " 
        			+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ "				THEN 1 " 
        			+ "				ELSE 0 " 
        			+ "			END ) ) ) < 13 ) "
        			+ "		 THEN "
        			+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ "				THEN 1 "
        			+ "		 		ELSE 0 "
        			+ " 		END ) ) ) "  
        			+ " 	ELSE " 
        			+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
        			+ " 	END " 
        			+ " ) as valorCurtoPrazo, " 
        			+ " sum( " 
        			+ " 	CASE WHEN ( "
        			+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
        			+ " 			THEN 1 "
        			+ "				ELSE 0 " 
        			+ " 		END) ) ) < 13 ) " 
        			+ " 	THEN " 
        			+ "			0.00 " 
        			+ " 	ELSE " 
        			+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
        			+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
        			+ " 			THEN 1 "
        			+ " 			ELSE 0 "
        			+ " 		END ) ) ) "
        			+ " 	END " 
        			+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.pcgr_id = :parcelamentoCurtoPrazo ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCurtoPrazo", Hibernate.BIG_DECIMAL)
                    .addScalar("valorLongoPrazo", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger(
                            "parcelamentoCurtoPrazo",
                            ParcelamentoGrupo.PARCELAMENTOS_A_COBRAR_CURTO_PRAZO)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para parcelamentos a cobrar de
     * longo prazo pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosLongoPrazo(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = " SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
    				+ " dbacCat.catg_id as idCategoria, " 
    				+ " sum( " 
    				+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ "				THEN 1 " 
    				+ "				ELSE 0 " 
    				+ "			END ) ) ) < 13 ) "
    				+ "		 THEN "
    				+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ "				THEN 1 "
    				+ "		 		ELSE 0 "
    				+ " 		END ) ) ) "  
    				+ " 	ELSE " 
    				+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
    				+ " 	END " 
    				+ " ) as valorCurtoPrazo, " 
    				+ " sum( " 
    				+ " 	CASE WHEN ( "
    				+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
    				+ " 			THEN 1 "
    				+ "				ELSE 0 " 
    				+ " 		END) ) ) < 13 ) " 
    				+ " 	THEN " 
    				+ "			0.00 " 
    				+ " 	ELSE " 
    				+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
    				+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
    				+ " 			THEN 1 "
    				+ " 			ELSE 0 "
    				+ " 		END ) ) ) "
    				+ " 	END " 
    				+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.pcgr_id = :parcelamentoLongoPrazo ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCurtoPrazo", Hibernate.BIG_DECIMAL)
                    .addScalar("valorLongoPrazo", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger(
                            "parcelamentoLongoPrazo",
                            ParcelamentoGrupo.PARCELAMENTOS_A_COBRAR_LONGO_PRAZO)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para juros cobrados de
     * longo prazo pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 26/05/2008
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaJurosCobrados(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = " SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
					+ " dbacCat.catg_id as idCategoria, " 
					+ " sum( " 
					+ " 	CASE WHEN ( ( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
					+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
					+ "				THEN 1 " 
					+ "				ELSE 0 " 
					+ "			END ) ) ) < 13 ) "
					+ "		 THEN "
					+ " 		dbacCat.dbcg_vlcategoria - (trunc( (dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
					+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
					+ "				THEN 1 "
					+ "		 		ELSE 0 "
					+ " 		END ) ) ) "  
					+ " 	ELSE " 
					+ " 		trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito), 2 ) * 12 " 
					+ " 	END " 
					+ " ) as valorCurtoPrazo, " 
					+ " sum( " 
					+ " 	CASE WHEN ( "
					+ " 		( dbac.dbac_nnprestacaodebito - (dbac.dbac_nnprestacaocobradas - ( " 
					+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) "
					+ " 			THEN 1 "
					+ "				ELSE 0 " 
					+ " 		END) ) ) < 13 ) " 
					+ " 	THEN " 
					+ "			0.00 " 
					+ " 	ELSE " 
					+ " 		dbacCat.dbcg_vlcategoria - ( trunc( ( dbacCat.dbcg_vlcategoria / dbac.dbac_nnprestacaodebito ), 2 ) * ( 12 + dbac.dbac_nnprestacaocobradas - ( " 
					+ " 		CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
					+ " 			THEN 1 "
					+ " 			ELSE 0 "
					+ " 		END ) ) ) "
					+ " 	END " 
					+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and ( dbac.pcgr_id = :jurosCobrados ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCurtoPrazo", Hibernate.BIG_DECIMAL)
                    .addScalar("valorLongoPrazo", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger(
                            "jurosCobrados",
                            ParcelamentoGrupo.JUROS_COBRADOS)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos débitos a cobrar para arrasto de água, arrasto de
     * esgoto e arrasto de serviço pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaArrasto(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " dbacCat.catg_id as idCategoria, sum(dbacCat.dbcg_vlcategoria) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.debito_a_cobrar dbac "
                    + " on dbac.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.deb_a_cobrar_catg dbacCat "
                    + " on dbacCat.dbac_id = dbac.dbac_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and dbac.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and dbac.dcst_idanterior is null ) ) "
                    + " and dbac.fntp_id in ( :arrastoAgua, :arrastoEsgoto, :arrastoServico ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, dbaccat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "arrastoAgua", FinanciamentoTipo.ARRASTO_AGUA)
                    .setInteger("arrastoEsgoto",
                            FinanciamentoTipo.ARRASTO_ESGOTO)
                    .setInteger("arrastoServico",
                            FinanciamentoTipo.ARRASTO_SERVICO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos a realizar para descontos concedidos no
     * parcelamento pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontosParcelamento(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
        	consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
					+ " crarCat.catg_id as idCategoria, " 
					+ " sum( " 
					+ " 	CASE WHEN ( ( crar.crar_nnprestacaocredito - (crar.crar_nnprestacaorealizadas - ( " 
					+ " 		CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
					+ "				THEN 1 " 
					+ "				ELSE 0 " 
					+ "			END ) ) ) < 13 ) "
					+ "		 THEN "
					+ " 		crarCat.cacg_vlcategoria - (trunc( (crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * (crar.crar_nnprestacaorealizadas - ( "
					+ " 		CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
					+ "				THEN 1 "
					+ "		 		ELSE 0 "
					+ " 		END ) ) ) "  
					+ " 	ELSE " 
					+ " 		trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * 12 " 
					+ " 	END " 
					+ " ) as valorCurtoPrazo, " 
					+ " sum( " 
					+ " 	CASE WHEN ( "
					+ " 		( crar.crar_nnprestacaocredito - (crar.crar_nnprestacaorealizadas - ( " 
					+ " 		CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
					+ " 			THEN 1 "
					+ "				ELSE 0 " 
					+ " 		END) ) ) < 13 ) " 
					+ " 	THEN " 
					+ "			0.00 " 
					+ " 	ELSE " 
					+ " 		crarCat.cacg_vlcategoria - ( trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito ), 2 ) * ( 12 + crar.crar_nnprestacaorealizadas - ( " 
					+ " 		CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) " 
					+ " 			THEN 1 "
					+ " 			ELSE 0 "
					+ " 		END ) ) ) "
					+ " 	END " 
					+ " ) as valorLongoPrazo "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.credito_a_realizar crar "
                    + " on crar.loca_id = loca.loca_id "
                    + " INNER JOIN faturamento.cred_a_realiz_catg crarCat "
                    + " on crarCat.crar_id = crar.crar_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
                    + " and ( crar.crog_id = :descontoParcelamento ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCurtoPrazo",
                    Hibernate.BIG_DECIMAL).addScalar("valorLongoPrazo",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "descontoParcelamento",
                            CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)
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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os descontos concedidos no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontosParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " crarCat.catg_id as idCategoria, "
					+ " sum( round ( ( (crar_vlresidualmesanterior / (select sum(cacg_qteconomia) from faturamento.cred_a_realiz_catg where crar_id = crar.crar_id)) * crarCat.cacg_qteconomia ), 2 )) as valorResidual "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar "
					+ " on loca.loca_id = crar.loca_id "
					+ " INNER JOIN faturamento.cred_a_realiz_catg crarCat " 
					+ " on crar.crar_id = crarCat.crar_id "
					+ " WHERE loca.loca_id = :idLocalidade and crar.crar_vlresidualmesanterior is not null and crar.crar_vlresidualmesanterior > 0 "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
					+ " and ( crar.crog_id = :descontoParcelamento ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorResidual",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO)
					.setInteger(
                            "descontoParcelamento",
                            CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos a realizar para devoluções pela gerência,
     * localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDevolucao(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " crarCat.catg_id as idCategoria, "
                    + " sum( crarCat.cacg_vlcategoria - (trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * ( crar.crar_nnprestacaorealizadas - "
                    + " ( CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
                    + " THEN 1 "
                    + " ELSE 0 "
                    + " END ) "
                    + " ) ) ) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.credito_a_realizar crar "
                    + " on loca.loca_id = crar.loca_id "
                    + " INNER JOIN faturamento.cred_a_realiz_catg crarCat "
                    + " on crarCat.crar_id = crar.crar_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
                    + " and crar.crog_id in ( :devolucaoAgua, "
                    + " :devolucaoEsgoto, :servicoPagoIndevidamente, "
                    + " :devolucaoJuros ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session
                    .createSQLQuery(consulta)
                    .addScalar("idGerencia", Hibernate.INTEGER)
                    .addScalar("idUnidadeNegocio", Hibernate.INTEGER)
                    .addScalar("idLocalidade", Hibernate.INTEGER)
                    .addScalar("idCategoria", Hibernate.INTEGER)
                    .addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
                    .setInteger("idLocalidade", idLocalidade)
                    .setInteger("anoMesReferenciaContabil",
                            anoMesReferenciaContabil)
                    .setInteger("situacaoNormal", DebitoCreditoSituacao.NORMAL)
                    .setInteger("situacaoIncluida",
                            DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA)
                    .setInteger("situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA)
                    .setInteger("situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA)
                    .setInteger("situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO)
                    .setInteger("devolucaoAgua",
                            CreditoOrigem.DEVOLUCAO_TARIFA_AGUA)
                    .setInteger("devolucaoEsgoto",
                            CreditoOrigem.DEVOLUCAO_TARIFA_ESGOTO)
                    .setInteger(
                            "servicoPagoIndevidamente",
                            CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE)
                    .setInteger("devolucaoJuros",
                            CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para as devoluções pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " crarCat.catg_id as idCategoria, "
					+ " sum( round ( ( (crar_vlresidualmesanterior / (select sum(cacg_qteconomia) from faturamento.cred_a_realiz_catg where crar_id = crar.crar_id)) * crarCat.cacg_qteconomia ), 2 )) as valorResidual "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar "
					+ " on loca.loca_id = crar.loca_id "
					+ " INNER JOIN faturamento.cred_a_realiz_catg crarCat " 
					+ " on crar.crar_id = crarCat.crar_id "
					+ " WHERE loca.loca_id = :idLocalidade and crar.crar_vlresidualmesanterior is not null and crar.crar_vlresidualmesanterior > 0 "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
					+ " and crar.crog_id in ( :devolucaoAgua, "
	                + " :devolucaoEsgoto, :servicoPagoIndevidamente, "
	                + " :devolucaoJuros ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorResidual",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO)
					.setInteger("devolucaoAgua",
                            CreditoOrigem.DEVOLUCAO_TARIFA_AGUA)
                    .setInteger("devolucaoEsgoto",
                            CreditoOrigem.DEVOLUCAO_TARIFA_ESGOTO)
                    .setInteger(
                            "servicoPagoIndevidamente",
                            CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE)
                    .setInteger("devolucaoJuros",
                            CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos a realizar para descontos incondicionais
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoIncondicional(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
            		+ " crarCat.catg_id as idCategoria, "
            		+ " sum( crarCat.cacg_vlcategoria - (trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * ( crar.crar_nnprestacaorealizadas - "
                    + " ( CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
                    + " THEN 1 "
                    + " ELSE 0 "
                    + " END ) "
                    + " ) ) ) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.credito_a_realizar crar "
                    + " on loca.loca_id = crar.loca_id "
                    + " INNER JOIN faturamento.cred_a_realiz_catg crarCat "
                    + " on crarCat.crar_id = crar.crar_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
                    + " and ( crar.crog_id = :descontoIncondicional ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "descontoIncondicional",
                            CreditoOrigem.DESCONTOS_INCONDICIONAIS).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os descontos incondicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " crarCat.catg_id as idCategoria, "
					+ " sum( round ( ( (crar_vlresidualmesanterior / (select sum(cacg_qteconomia) from faturamento.cred_a_realiz_catg where crar_id = crar.crar_id)) * crarCat.cacg_qteconomia ), 2 )) as valorResidual "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar "
					+ " on loca.loca_id = crar.loca_id "
					+ " INNER JOIN faturamento.cred_a_realiz_catg crarCat " 
					+ " on crar.crar_id = crarCat.crar_id "
					+ " WHERE loca.loca_id = :idLocalidade and crar.crar_vlresidualmesanterior is not null and crar.crar_vlresidualmesanterior > 0 "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
					+ " and ( crar.crog_id = :descontoIncondicional ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorResidual",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO)
					.setInteger(
                            "descontoIncondicional",
                            CreditoOrigem.DESCONTOS_INCONDICIONAIS).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos a realizar para contas pagas em excesso
     * ou em duplicidade pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaPagamentoExcesso(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " crarCat.catg_id as idCategoria, "
                    + " sum( crarCat.cacg_vlcategoria - (trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * ( crar.crar_nnprestacaorealizadas - "
                    + " ( CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
                    + " THEN 1 "
                    + " ELSE 0 "
                    + " END ) "
                    + " ) ) ) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.credito_a_realizar crar "
                    + " on loca.loca_id = crar.loca_id "
                    + " INNER JOIN faturamento.cred_a_realiz_catg crarCat "
                    + " on crarCat.crar_id = crar.crar_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
                    + " and ( crar.crog_id = :pagamentoExcesso ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "pagamentoExcesso",
                            CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO)
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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os pagamentos em excesso ou duplicidade no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " crarCat.catg_id as idCategoria, "
					+ " sum( round ( ( (crar_vlresidualmesanterior / (select sum(cacg_qteconomia) from faturamento.cred_a_realiz_catg where crar_id = crar.crar_id)) * crarCat.cacg_qteconomia ), 2 )) as valorResidual "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar "
					+ " on loca.loca_id = crar.loca_id "
					+ " INNER JOIN faturamento.cred_a_realiz_catg crarCat " 
					+ " on crar.crar_id = crarCat.crar_id "
					+ " WHERE loca.loca_id = :idLocalidade and crar.crar_vlresidualmesanterior is not null and crar.crar_vlresidualmesanterior > 0 "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
					+ " and ( crar.crog_id = :pagamentoExcesso ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorResidual",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO)
					.setInteger(
                            "pagamentoExcesso",
                            CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos a realizar para descontos condicionais
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoCondicional(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " crarCat.catg_id as idCategoria, "
                    + " sum( crarCat.cacg_vlcategoria - (trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * ( crar.crar_nnprestacaorealizadas - "
                    + " ( CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
                    + " THEN 1 "
                    + " ELSE 0 "
                    + " END ) "
                    + " ) ) ) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.credito_a_realizar crar "
                    + " on loca.loca_id = crar.loca_id "
                    + " INNER JOIN faturamento.cred_a_realiz_catg crarCat "
                    + " on crarCat.crar_id = crar.crar_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
                    + " and ( crar.crog_id = :descontoCondicional ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "descontoCondicional",
                            CreditoOrigem.DESCONTOS_CONDICIONAIS).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os descontos condicionais pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " crarCat.catg_id as idCategoria, "
					+ " sum( round ( ( (crar_vlresidualmesanterior / (select sum(cacg_qteconomia) from faturamento.cred_a_realiz_catg where crar_id = crar.crar_id)) * crarCat.cacg_qteconomia ), 2 )) as valorResidual "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar "
					+ " on loca.loca_id = crar.loca_id "
					+ " INNER JOIN faturamento.cred_a_realiz_catg crarCat " 
					+ " on crar.crar_id = crarCat.crar_id "
					+ " WHERE loca.loca_id = :idLocalidade and crar.crar_vlresidualmesanterior is not null and crar.crar_vlresidualmesanterior > 0 "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
					+ " and ( crar.crog_id = :descontoCondicional ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorResidual",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO)
					.setInteger(
                            "descontoCondicional",
                            CreditoOrigem.DESCONTOS_CONDICIONAIS).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     *
     * Acumula os valores dos créditos a realizar para ajustes para zerar contas
     * pela gerência, localidade e categoria
     *
     * @author Rafael Corrêa
     * @date 08/11/2007
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e mês de referência contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaAjusteZerarConta(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, "
                    + " crarCat.catg_id as idCategoria, "
                    + " sum( crarCat.cacg_vlcategoria - (trunc( ( crarCat.cacg_vlcategoria / crar.crar_nnprestacaocredito), 2 ) * ( crar.crar_nnprestacaorealizadas - "
                    + " ( CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao > :anoMesReferenciaContabil ) "
                    + " THEN 1 "
                    + " ELSE 0 "
                    + " END ) "
                    + " ) ) ) as valorCategoria "
                    + " FROM cadastro.localidade loca "
                    + " INNER JOIN faturamento.credito_a_realizar crar "
                    + " on loca.loca_id = crar.loca_id "
                    + " INNER JOIN faturamento.cred_a_realiz_catg crarCat "
                    + " on crarCat.crar_id = crar.crar_id "
                    + " WHERE loca.loca_id = :idLocalidade "
                    + " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoNormal, "
                    + " :situacaoIncluida, :situacaoRetificada ) ) "
                    + " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil "
                    + " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
                    + " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
                    + " and ( crar.crog_id = :ajusteZerarConta ) "
                    + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
                    + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
                    Hibernate.INTEGER).addScalar("idUnidadeNegocio",
                    Hibernate.INTEGER).addScalar("idLocalidade",
                    Hibernate.INTEGER).addScalar("idCategoria",
                    Hibernate.INTEGER).addScalar("valorCategoria",
                    Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
                    idLocalidade).setInteger("anoMesReferenciaContabil",
                    anoMesReferenciaContabil).setInteger("situacaoNormal",
                    DebitoCreditoSituacao.NORMAL).setInteger(
                    "situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
                    .setInteger("situacaoRetificada",
                            DebitoCreditoSituacao.RETIFICADA).setInteger(
                            "situacaoCancelada",
                            DebitoCreditoSituacao.CANCELADA).setInteger(
                            "situacaoCanceladaPorRetificacao",
                            DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
                    .setInteger("situacaoParcelada",
                            DebitoCreditoSituacao.PARCELADA).setInteger(
                            "situacaoDebitoPrescrito",
                            DebitoCreditoSituacao.DEBITO_PRESCRITO).setInteger(
                            "ajusteZerarConta",
                            CreditoOrigem.AJUSTES_PARA_ZERAR_CONTA).list();

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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula os valores residuais para os ajustes para zerar a conta no parcelamento pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e mês de referência contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, loca.loca_id as idLocalidade, " 
					+ " crarCat.catg_id as idCategoria, "
					+ " sum( round ( ( (crar_vlresidualmesanterior / (select sum(cacg_qteconomia) from faturamento.cred_a_realiz_catg where crar_id = crar.crar_id)) * crarCat.cacg_qteconomia ), 2 )) as valorResidual "
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar "
					+ " on loca.loca_id = crar.loca_id "
					+ " INNER JOIN faturamento.cred_a_realiz_catg crarCat " 
					+ " on crar.crar_id = crarCat.crar_id "
					+ " WHERE loca.loca_id = :idLocalidade and crar.crar_vlresidualmesanterior is not null and crar.crar_vlresidualmesanterior > 0 "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoNormal, "
					+ " :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil "
					+ " and crar.dcst_idatual in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) and crar.dcst_idanterior is null ) ) "
					+ " and ( crar.crog_id = :ajusteZerarConta ) "
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, crarcat.catg_id "
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta).addScalar("idGerencia",
					Hibernate.INTEGER).addScalar("idUnidadeNegocio",
					Hibernate.INTEGER).addScalar("idLocalidade",
					Hibernate.INTEGER).addScalar("idCategoria",
					Hibernate.INTEGER).addScalar("valorResidual",
					Hibernate.BIG_DECIMAL).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferenciaContabil",
					anoMesReferenciaContabil).setInteger("situacaoNormal",
					DebitoCreditoSituacao.NORMAL).setInteger(
					"situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada",
							DebitoCreditoSituacao.RETIFICADA).setInteger(
							"situacaoCancelada",
							DebitoCreditoSituacao.CANCELADA).setInteger(
							"situacaoCanceladaPorRetificacao",
							DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada",
							DebitoCreditoSituacao.PARCELADA).setInteger(
							"situacaoDebitoPrescrito",
							DebitoCreditoSituacao.DEBITO_PRESCRITO)
					.setInteger(
                            "ajusteZerarConta",
                            CreditoOrigem.AJUSTES_PARA_ZERAR_CONTA).list();

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
	 * Seleciona as quadras da localidade informada onde existe contas a serem
	 * baixadas contabiolmente
	 * 
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Pedro Alexandre
	 * @date 22/11/2006
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterQuadrasPorLocalidadeParaGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, int idLocalidade) throws ErroRepositorioException {

		Collection<Integer> retorno = null;

		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;

		try {
			consulta = " select distinct (qdra.qdra_id) as idQuadra " +  			
					   " from cadastro.quadra as qdra " +					   
					   " inner join cadastro.setor_comercial as setor on setor.stcm_id = qdra.stcm_id " +
					   " inner join cadastro.localidade as loca on loca.loca_id = setor.loca_id " +
					   " where loca.loca_id = :idLocalidade ";
			
			
			retorno = session.createSQLQuery(consulta)
						.addScalar("idQuadra", Hibernate.INTEGER)
						.setInteger("idLocalidade",idLocalidade)
						.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * verifica se a conta informada possui cliente responsável 
	 * com esfera de poder de tipo de cliente igual a municipal,
	 * estadual ou federal.
	 *
	 * @author Pedro Alexandre
	 * @date 23/07/2007
	 *
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaClienteResponsavelConta(int idConta) throws ErroRepositorioException {

		boolean retorno = false;

		Collection retornoPesquisa = null;
		Collection<Short> colecaoIdsEsferasPoder = new ArrayList();
		colecaoIdsEsferasPoder.add(EsferaPoder.MUNICIPAL);
		colecaoIdsEsferasPoder.add(EsferaPoder.ESTADUAL);
		colecaoIdsEsferasPoder.add(EsferaPoder.FEDERAL);
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;

		try {
			consulta = "select clct.id " + 
						"from ClienteConta clct " + 
						"inner join clct.conta cnta " + 
						"inner join clct.cliente clie " + 
						"inner join clct.clienteRelacaoTipo crtp " + 
						"inner join clie.clienteTipo cltp " + 
						"inner join cltp.esferaPoder epod " + 
						"where cnta.id = :idConta and crtp.id = :idRelacaoTipo " + 
						"and epod.id in (:idsEsferasPoder) ";
			
			
			retornoPesquisa = session.createQuery(consulta)
						.setInteger("idConta", idConta)
						.setInteger("idRelacaoTipo",ClienteRelacaoTipo.RESPONSAVEL)
						.setParameterList("idsEsferasPoder",colecaoIdsEsferasPoder)
						.list();

			
			if(retornoPesquisa != null && !retornoPesquisa.isEmpty()){
				retorno = true; 
			}
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento, Ivan Sergio
	 * @date: 21/12/2007
	 * 		  29/07/2010 - Solicitante: Eduardo. Retirado o item IMPOSTOS DEDUZIDOS da soma;
	 * 		  12/04/2011
	 * @param anoMesReferencia ano/mes 
	 * @param gerencia Id da gerencia regional 
	 * @param unidadeNegocio Id da unidade de negocio 
	 * @param localidade Id da localidade
	 * @param municipio Id do município
	 *  
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException {
		

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String groupBy = "";
		String orderBy = "";
		try {
			// foram colocados os primeiros campos com valores fixos para permitir um unico tratamento 
			// na pesquisa por Estado, por Gerencia, por Unidade e por Localidade
			consulta = "select '', 0, 0, ct.id, sum(crc.valorItemLancamento) "
					+ (municipio != null ? ", muni.id " : "")
					+ "from ContaAReceberContabil crc "
					+ (municipio != null ? " inner join crc.localidade loc " +
										   " inner join loc.municipio muni " : "")
					+ "left join crc.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where crc.anoMesReferencia = :anoMesReferencia "
					+ " and crc.lancamentoTipo != " + LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.toString()
					+ " and crc.lancamentoTipo != " + LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.toString()
					+ " and crc.lancamentoItem != " + LancamentoItem.IMPOSTOS_DEDUZIDOS.toString()
					+ (gerencia != null ? " and crc.gerenciaRegional = " + gerencia : "") 
					+ (unidadeNegocio != null ? " and crc.unidadeNegocio = " + unidadeNegocio : "")
					+ (localidade != null ? " and crc.localidade.id = " + localidade : "")
					+ (municipio != null ? " and loc.municipio.id = muni.id " +
										   " and muni.id = " + municipio : "");
			
					if(municipio != null){
						groupBy = " GROUP BY muni.id, ct.id ";
						orderBy = " ORDER BY muni.id, ct.id ";
					}else{
						groupBy = " GROUP BY ct.id ";
						orderBy = " ORDER BY ct.id ";
					}
					consulta += groupBy + orderBy;
			
					retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 26/12/2007, 12/04/2011 
	 * @param anoMesReferencia ano/mes 
	 * @param gerencia Id da gerencia regional 
	 * @param unidadeNegocio Id da unidade de negocio 
	 * @param localidade Id da localidade
	 * @param municipio Id do município
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String groupBy = "";
		String orderBy = "";
		
		try {
			// foram colocados os dois primeiros campos com valores fixos para permitir um unico tratamento 
			// na pesquisa por Estado, por Gerencia, por Unidade e por Localidade
			consulta = "select '', 0, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento," +
					" ct.id, sum(rf.valorItemFaturamento) " 
					+ (municipio != null ? ", muni.id " : "")
					+ "from ResumoFaturamento rf "
					+ (municipio != null ? " inner join rf.localidade loc " +
							   " inner join loc.municipio muni " : "")
					+ "left join rf.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where rf.anoMesReferencia = :anoMesReferencia "
					+ (gerencia != null ? " and rf.gerenciaRegional = " + gerencia : "") 
					+ (unidadeNegocio != null ? " and rf.unidadeNegocio = " + unidadeNegocio : "")
					+ (localidade != null ? " and rf.localidade.id = " + localidade : "")					
					+ (municipio != null ? " and loc.municipio.id = muni.id " +
					   " and muni.id = " + municipio : "");
			
			if(municipio != null){
				groupBy = " group by muni.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";
				orderBy = " order by muni.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";
			}else{
				groupBy = " group by rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";
				orderBy = " order by rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";
			}
			consulta += groupBy + orderBy;
						
			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 07/01/2008, 12/04/2011 
 	 * @param anoMesReferencia ano/mes 
	 * @param gerencia Id da gerencia regional 
	 * @param unidadeNegocio Id da unidade de negocio 
	 * @param localidade Id da localidade
	 * @param municipio Id do município
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio)
			throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		String groupBy = "";
		String orderBy = "";
		
		try {
			// foram colocados os dois primeiros campos com valores fixos para permitir um unico tratamento 
			// na pesquisa por Estado, por Gerencia, por Unidade e por Localidade
			consulta = "select '', 0,  ra.sequenciaTipoLancamento, ct.id, sum(ra.valorItemArrecadacao) "
					+ (municipio != null ? ", muni.id " : "")		
					+ "from ResumoArrecadacao ra "
					+ (municipio != null ? " inner join ra.localidade loc " +
							   " inner join loc.municipio muni " : "")
					+ "left join ra.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where ra.anoMesReferencia = :anoMesReferencia "
					+ (gerencia != null ? " and ra.gerenciaRegional = " + gerencia : "") 
					+ (unidadeNegocio != null ? " and ra.unidadeNegocio = " + unidadeNegocio : "")
					+ (localidade != null ? " and ra.localidade.id = " + localidade : "")					
					+ (municipio != null ? " and loc.municipio.id = muni.id " +
					   " and muni.id = " + municipio : "");

					if(municipio != null){
						groupBy = " group by muni.id, ra.sequenciaTipoLancamento, ct.id ";
						orderBy = " order by muni.id, ra.sequenciaTipoLancamento, ct.id ";
					}else{
						groupBy = " group by ra.sequenciaTipoLancamento, ct.id ";
						orderBy = " order by ra.sequenciaTipoLancamento, ct.id ";
					}
					consulta += groupBy + orderBy;
					
					retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Gerencia Regional
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select crc.gerenciaRegional.nome, crc.gerenciaRegional.id, 0, ct.id, " +
					"sum(crc.valorItemLancamento) " +
					"from ContaAReceberContabil crc "
					+ "left join crc.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where crc.anoMesReferencia = :anoMesReferencia "
					+ " and crc.lancamentoTipo != " + LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.toString()
					+ " and crc.lancamentoTipo != " + LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.toString()
					+ " group by crc.gerenciaRegional.nome, crc.gerenciaRegional.id, ct.id "
					+ "order by crc.gerenciaRegional.id, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Gerencia Regional
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select rf.gerenciaRegional.nome, rf.gerenciaRegional.id, " +
					"rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id, sum(rf.valorItemFaturamento) " +
					"from ResumoFaturamento rf "
					+ "left join rf.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where rf.anoMesReferencia = :anoMesReferencia "
					+ "group by rf.gerenciaRegional.nome, rf.gerenciaRegional.id,rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id "
					+ "order by rf.gerenciaRegional.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Gerencia Regional
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select ra.gerenciaRegional.nome, ra.gerenciaRegional.id, " +
					"ra.sequenciaTipoLancamento, ct.id, sum(ra.valorItemArrecadacao) " +
					"from ResumoArrecadacao ra "
					+ "left join ra.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where ra.anoMesReferencia = :anoMesReferencia "
					+ "group by ra.gerenciaRegional.nome, ra.gerenciaRegional.id, ra.sequenciaTipoLancamento, ct.id "
					+ "order by ra.gerenciaRegional.id, ra.sequenciaTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select crc.unidadeNegocio.nome, crc.unidadeNegocio.id , 0, ct.id, " +
					"sum(crc.valorItemLancamento) " +
					"from ContaAReceberContabil crc "
					+ "left join crc.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where crc.anoMesReferencia = :anoMesReferencia "
					+ " and crc.lancamentoTipo != " + LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.toString()
					+ " and crc.lancamentoTipo != " + LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.toString()
					+ (gerencia != null ? " and crc.gerenciaRegional = " + gerencia : "")
					+ " group by crc.unidadeNegocio.nome, crc.unidadeNegocio.id, ct.id "
					+ "order by crc.unidadeNegocio.id, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select rf.unidadeNegocio.nome, rf.unidadeNegocio.id, " +
					"rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id, sum(rf.valorItemFaturamento) " +
					"from ResumoFaturamento rf "
					+ "left join rf.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where rf.anoMesReferencia = :anoMesReferencia "
					+ (gerencia != null ? " and rf.gerenciaRegional = " + gerencia : "")
					+ " group by rf.unidadeNegocio.nome, rf.unidadeNegocio.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id "
					+ "order by rf.unidadeNegocio.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select ra.unidadeNegocio.nome, ra.unidadeNegocio.id, " +
					"ra.sequenciaTipoLancamento, ct.id, sum(ra.valorItemArrecadacao) " +
					"from ResumoArrecadacao ra "
					+ "left join ra.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where ra.anoMesReferencia = :anoMesReferencia "
					+ (gerencia != null ? " and ra.gerenciaRegional = " + gerencia : "")
					+ " group by ra.unidadeNegocio.nome, ra.unidadeNegocio.id, ra.sequenciaTipoLancamento, ct.id "
					+ "order by ra.unidadeNegocio.id, ra.sequenciaTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select crc.localidade.descricao, crc.localidade.id , 0, ct.id, " +
					"sum(crc.valorItemLancamento) " +
					"from ContaAReceberContabil crc "
					+ "left join crc.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where crc.anoMesReferencia = :anoMesReferencia "
					+ " and crc.lancamentoTipo != " + LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.toString()
					+ " and crc.lancamentoTipo != " + LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.toString()
					+ (gerencia != null ? " and crc.gerenciaRegional = " + gerencia : "")
					+ (unidadeNegocio != null ? " and crc.unidadeNegocio = " + unidadeNegocio : "")					
					+ " group by crc.localidade.descricao, crc.localidade.id, ct.id "
					+ "order by crc.localidade.id, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Município
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select muni.nome, muni.id , 0, ct.id, " +
					"sum(crc.valorItemLancamento) " +
					"from ContaAReceberContabil crc "
					+ "inner join crc.localidade loc "
					+ "inner join loc.municipio muni "
					+ "left join crc.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where crc.anoMesReferencia = :anoMesReferencia "
					+ " and crc.lancamentoTipo != " + LancamentoTipo.VALORES_CONTABILIZADOS_COMO_PERDAS.toString()
					+ " and crc.lancamentoTipo != " + LancamentoTipo.RECEBIMENTOS_NAO_IDENTIFICADOS.toString()
					+ " group by muni.nome, muni.id, ct.id "
					+ " order by muni.id, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select rf.localidade.descricao, rf.localidade.id, " +
					"rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id, sum(rf.valorItemFaturamento) " +
					"from ResumoFaturamento rf "
					+ "left join rf.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where rf.anoMesReferencia = :anoMesReferencia "
					+ (gerencia != null ? " and rf.gerenciaRegional = " + gerencia : "")
					+ (unidadeNegocio != null ? " and rf.unidadeNegocio = " + unidadeNegocio : "")
					+ " group by rf.localidade.descricao, rf.localidade.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id "
					+ "order by rf.localidade.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Município
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			
			consulta = "select muni.nome, muni.id, " +
					"rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id, sum(rf.valorItemFaturamento) " +
					"from ResumoFaturamento rf "
					+ "inner join rf.localidade loc "
					+ "inner join loc.municipio muni "
					+ "left join rf.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where rf.anoMesReferencia = :anoMesReferencia "
					+ " group by muni.nome, muni.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id "
					+ "order by muni.id, rf.sequenciaTipoLancamento, rf.sequenciaItemTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * [UC0751] - Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Remove o valor dos volumes consumidos e não faturados 
	 * 
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 19/02/2008, 08/07/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento Ano e mês de referência faturamento
	 * @throws ErroRepositorioException   Erro no hibernate
	 */
	public void removerValorVolumesConsumidosNaoFaturados(int anoMesReferenciaFaturamento, Integer idLocalidade) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
		
		try {

			Connection jdbcCon = session.connection();

			String consulta = "delete from financeiro.valor_vol_cons_nao_fatur vcnf "
				+ "where vcnf.loca_id = ? "
				+ "and vcnf.vcnf_amreferencia = ?";
					
			st = jdbcCon.prepareStatement(consulta);
			st.setInt(1, idLocalidade);
			st.setInt(2, anoMesReferenciaFaturamento);
			
			st.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
		/*// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o hql
			consulta = "delete from financeiro.valor_vol_cons_nao_fatur vcnf "
					+ "where vcnf.loca_id = :idLocalidade "
					+ "and vcnf.vcnf_amreferencia = :anoMesReferencia";
			
			session.createSQLQuery(consulta)
					.setInteger("idLocalidade",idLocalidade)
					.setInteger("anoMesReferencia",anoMesReferenciaFaturamento)
					.executeUpdate();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}*/

	}
	
	/**
	 * [UC0751] - Gerar Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * Acumula os valores de água e esgoto pela gerência, localidade e categoria
	 * 
	 * @author Rafael Corrêa, Pedro Alexandre
	 * @date 08/11/2007, 08/07/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 *            Ano e mês de referência faturamento
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosValorVolumesConsumidosNaoFaturadosAguaEsgoto(
			int anoMesReferenciaFaturamento, Integer idLocalidade, Date ultimoDiaMesCorrenteFaturamento)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT contaCat.catg_id as idCategoria , "
					+ " sum( "
					+ " CASE WHEN ( ( mdhi.mdhi_dtleituraatualfaturamento - mdhi.mdhi_dtleitantfatmt ) > 0 ) "
					+ " THEN ( "
					+ " ( round( "
					+ " contaCat.ctcg_vlagua / ( mdhi.mdhi_dtleituraatualfaturamento - mdhi.mdhi_dtleitantfatmt ), 2 "
					+ " ) ) * " 
					+ " ( :ultimoDiaMesCorrenteFaturamento - mdhi.mdhi_dtleituraatualfaturamento ) "
					+ " ) "
					+ " ELSE "
					+ " 0 "
					+ " END "
					+ " ) as valorAgua, "
					+ " sum( "
					+ " CASE WHEN ( ( mdhi.mdhi_dtleituraatualfaturamento - mdhi.mdhi_dtleitantfatmt ) > 0 ) "
					+ " THEN ( "
					+ " ( round( "
					+ " contaCat.ctcg_vlesgoto / ( mdhi.mdhi_dtleituraatualfaturamento - mdhi.mdhi_dtleitantfatmt ), 2 "
					+ " ) ) * " 
					+ " ( :ultimoDiaMesCorrenteFaturamento - mdhi.mdhi_dtleituraatualfaturamento ) "
					+ " ) "
					+ " ELSE "
					+ " 0 "
					+ " END "
					+ " ) as valorEsgoto "
					+ " FROM faturamento.conta conta "
					+ " INNER JOIN faturamento.conta_categoria contaCat on conta.cnta_id = contaCat.cnta_id "
					+ " INNER JOIN micromedicao.medicao_historico mdhi on conta.imov_id = mdhi.lagu_id and conta.cnta_amreferenciaconta = mdhi.mdhi_amleitura and mdhi.medt_id = :ligacaoAgua and  mdhi.mdhi_amleitura =:anoMesReferenciaFaturamento "
					+ " WHERE conta.loca_id = :idLocalidade "
					+ " and conta.cnta_amreferenciaconta = :anoMesReferenciaFaturamento "
					+ " and ( conta.dcst_idatual = :situacaoNormal or conta.dcst_idanterior = :situacaoNormal ) "
					+ " GROUP BY contacat.catg_id " ;

			// executa o sql
			retorno = session.createSQLQuery(consulta)
					.addScalar("idCategoria",Hibernate.INTEGER)
					.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
					.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
					.setInteger("idLocalidade",idLocalidade)
					.setInteger("anoMesReferenciaFaturamento",anoMesReferenciaFaturamento)
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("ligacaoAgua",MedicaoTipo.LIGACAO_AGUA)
					.setDate("ultimoDiaMesCorrenteFaturamento",ultimoDiaMesCorrenteFaturamento)
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
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select ra.localidade.descricao, ra.localidade.id, " +
					"ra.sequenciaTipoLancamento, ct.id, sum(ra.valorItemArrecadacao) " +
					"from ResumoArrecadacao ra "
					+ "left join ra.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where ra.anoMesReferencia = :anoMesReferencia "
					+ (gerencia != null ? " and ra.gerenciaRegional = " + gerencia : "")
					+ (unidadeNegocio != null ? " and ra.unidadeNegocio = " + unidadeNegocio : "")					
					+ " group by ra.localidade.descricao, ra.localidade.id, ra.sequenciaTipoLancamento, ct.id "
					+ "order by ra.localidade.id, ra.sequenciaTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

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
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relatório da Evolução do Contas a Receber Contábil] por Estado e por Município
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException {
		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {
			
			consulta = "select muni.nome, muni.id, " +
					"ra.sequenciaTipoLancamento, ct.id, sum(ra.valorItemArrecadacao) " +
					"from ResumoArrecadacao ra "
					+ "inner join ra.localidade loc "
					+ "inner join loc.municipio muni "
					+ "left join ra.categoria cat "
					+ "left join cat.categoriaTipo ct "
					+ "where ra.anoMesReferencia = :anoMesReferencia "
					+ " group by muni.nome, muni.id, ra.sequenciaTipoLancamento, ct.id "
					+ "order by muni.id, ra.sequenciaTipoLancamento, ct.id ";

			retorno = session.createQuery(consulta).setInteger(
					"anoMesReferencia", anoMesReferencia).list();

		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	public Collection consultarDadosRelatorioSaldoContasAReceberContabil(String opcaoTotalizacao,
		int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
		throws ErroRepositorioException{
		
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;
		String joinMunicipio = " left join cadastro.municipio muni on ( loc.muni_idprincipal = muni.muni_id )\n ";
		String where = "";
		
		// Unidade de Negocio
		if ( unidadeNegocio != null && !unidadeNegocio.equals( 0 ) ){
			where += " and  uniNeg.uneg_id = :idUnidadeNegocio";	
		}
		
		// Gerencia Regional		
		if ( gerencia != null && !gerencia.equals( 0 ) ){
			where += " and  gerReg.greg_id = :idGerenciaRegional";	
		}		
		
		// Localidade
		if ( localidade != null && !localidade.equals( 0 ) ){
			where += " and  loc.loca_id = :idLocalidade";	
		}
		
		// Município
		if (municipio != null && !municipio.equals(0)){
			where += " and  muni.muni_id = :idMunicipio";	
		}
		
		if(opcaoTotalizacao.equals("estadoMunicipio") || opcaoTotalizacao.equals("municipio")){
			joinMunicipio = "  inner join cadastro.municipio muni on ( loc.muni_idprincipal = muni.muni_id )\n ";
		}

		try {	
			// constroi o sql
			consulta =			
			"select \n " +
			"  visao.col0, \n " +
			"  visao.col1, \n " +
			"  visao.col2, \n " +
			"  visao.col3, \n " +
			"  visao.col4, \n " +
			"  visao.col5, \n " +
			"  visao.col6, \n " +
			"  visao.crct_nnsequenciatipolancamento, \n " +
			"  visao.crct_nnseqitemtplanc, \n " +
			"  visao.lctp_dstipolancamento, \n " +
			"  visao.lcit_dsitemlancamento, \n " +
			"  visao.numeroConta, \n " +
			"  visao.cgtp_id, \n " +
			"  sum(coalesce(visao.crct_vlitemlancamento, 0)) as valor,\n " +
			"  visao.lict_dsitemlancamentocontabil,\n " +
			"  visao.lctp_id, \n " +
			"  visao.catg_id \n " +
			(municipio != null && !municipio.equals(0) ? ", visao.muni_nmmunicipio " : "") +
			"from \n " +
			"( \n " +
			"select \n " +
			"  -- 0 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'unidadeNegocioLocalidade' or \n " +
			"         :opcaoTotalizacao = 'unidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'estadoUnidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      gerReg.greg_id \n " +
			"    else \n " +
			"      0 \n " +
			"  end as col0, \n " +
			"  -- 1 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'unidadeNegocioLocalidade' or \n " +
			"         :opcaoTotalizacao = 'unidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'estadoUnidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      gerReg.greg_nmregional \n " +
			"    else \n " +
			"      '' \n " +
			"  end as col1, \n " +
			"  -- 2 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      uniNeg.uneg_id \n " +
			"    else \n " +
			"      0 \n " +
			"  end as col2, \n " +
			"  -- 3 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      uniNeg.uneg_nmunidadenegocio \n " +
			"    else \n " +
			"      '' \n " +
			"  end as col3, \n " +
			"  -- 4 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      loc.loca_cdcentrocusto \n " +
			"    else \n " +
			"      '' \n " +
			"  end as col4, \n " +
			"  -- 5 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'estadoGerencia' then \n " +
			"      gerReg.greg_nmregional \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      loc.loca_nmlocalidade \n " +
			"    when :opcaoTotalizacao = 'unidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'estadoUnidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      uniNeg.uneg_nmunidadenegocio \n " +
			"    when :opcaoTotalizacao = 'estadoMunicipio' or \n " +
			"         :opcaoTotalizacao = 'municipio' then \n " +
			"      muni.muni_nmmunicipio \n " +
			"    else \n " +
			"      '' \n " +
			"  end as col5, \n " +
			"  -- 6 \n " +
			"  case \n " +
			"    when :opcaoTotalizacao = 'estadoGerencia' then \n " +
			"      gerReg.greg_id \n " +
			"    when :opcaoTotalizacao = 'localidade' or \n " +
			"         :opcaoTotalizacao = 'estadoLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalLocalidade' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      loc.loca_id \n " +
			"    when :opcaoTotalizacao = 'unidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'estadoUnidadeNegocio' or \n " +
			"         :opcaoTotalizacao = 'gerenciaRegionalUnidadeNegocio' then \n " +
			"      uniNeg.uneg_id \n " +
			"    when :opcaoTotalizacao = 'estadoMunicipio' or \n " +
			"         :opcaoTotalizacao = 'municipio' then \n " +
			"      muni.muni_id \n " +
			"    else \n " +
			"      0 \n " +
			"  end as col6, \n " +
			"  conRecCon.crct_nnsequenciatipolancamento, \n " +
			"  conRecCon.crct_nnseqitemtplanc, \n " +
			"  lanTip.lctp_dstipolancamento, \n " +
			"  lanIte.lcit_dsitemlancamento, \n " +
			"  case\n " +
			"    when catTip.cgtp_id = 1 then\n " +
			"       coalesce( conConPar.cnct_nnconta, '0' )\n " +
			"    else\n " +
			"       coalesce( conConPub.cnct_nnconta, '0' )\n " +
			"  end as numeroConta,\n " +
			"  catTip.cgtp_id,\n " +
			"  conRecCon.crct_vlitemlancamento,\n " +
			"  lanIteCon.lict_dsitemlancamentocontabil,\n " +
			"  lanTip.lctp_id,\n " +
			"  cat.catg_id\n " +
			(municipio != null && !municipio.equals(0) ? ", muni.muni_nmmunicipio " : "") +
			"from\n " +
			"  financeiro.contas_a_receber_contb conRecCon\n " +
			"  left join cadastro.gerencia_regional gerReg on ( conRecCon.greg_id = gerReg.greg_id )\n " +
			"  left join cadastro.unidade_negocio uniNeg on ( conRecCon.uneg_id = uniNeg.uneg_id )\n " +
			"  left join cadastro.localidade loc on ( conRecCon.loca_id = loc.loca_id )\n " +
			joinMunicipio +
			"  left join cadastro.categoria cat on ( conRecCon.catg_id = cat.catg_id )\n " +
			"  left join cadastro.categoria_tipo catTip on ( cat.cgtp_id = catTip.cgtp_id )\n " +
			"  left join financeiro.lancamento_tipo lanTip on ( conRecCon.lctp_id = lanTip.lctp_id )\n " +
			"  left join financeiro.lancamento_item lanIte on ( conRecCon.lcit_id = lanIte.lcit_id )\n " +
			"  left join financeiro.lancamento_item_contabil lanIteCon on ( conRecCon.lict_id = lanIteCon.lict_id )\n " +
			"  left join financeiro.contas_a_rec_contb_param conRecConParPar on ( ( conRecConParPar.lict_id = conRecCon.lict_id or conRecConParPar.lict_id is null ) and conRecConParPar.lctp_id = conRecCon.lctp_id and conRecConParPar.lcit_id = conRecCon.lcit_id and conRecConParPar.cgtp_id = 1 )\n " +
			"  left join financeiro.conta_contabil conConPar on ( conRecConParPar.cnct_id = conConPar.cnct_id )\n " +
			"  left join financeiro.contas_a_rec_contb_param conRecConParPub on ( ( conRecConParPub.lict_id = conRecCon.lict_id or conRecConParPub.lict_id is null ) and conRecConParPub.lctp_id = conRecCon.lctp_id and conRecConParPub.lcit_id = conRecCon.lcit_id and conRecConParPub.cgtp_id = 2 )\n " +
			"  left join financeiro.conta_contabil conConPub on ( conRecConParPub.cnct_id = conConPub.cnct_id )\n " +
			"where\n " +
			"  conRecCon.crct_amreferencia = :anoMesReferencia" + where +
			") visao\n " +
			"group by\n " +
			(municipio != null && !municipio.equals(0) ? " visao.muni_nmmunicipio, " : "") +
			"  visao.col0,\n " +
			"  visao.col1,\n " +
			"  visao.col2,\n " +
			"  visao.col3,\n " +
			"  visao.col4,\n " +
			"  visao.col5,\n " +
			"  visao.col6,\n " +
			"  visao.crct_nnsequenciatipolancamento,\n " +
			"  visao.crct_nnseqitemtplanc,\n " +
			"  visao.lctp_dstipolancamento,\n " +
			"  visao.lcit_dsitemlancamento,\n " +
			"  visao.cgtp_id,\n " +
			"  visao.numeroConta,\n " +
			"  visao.lict_dsitemlancamentocontabil,\n " +
			"  visao.lctp_id,\n " +
			"  visao.catg_id " +
			"order by\n " +
			(municipio != null && !municipio.equals(0) ? " visao.muni_nmmunicipio, " : "") +
			"  visao.col0,\n " +
			"  visao.col1,\n " +
			"  visao.col2,\n " +
			"  visao.col3,\n " +
			"  visao.col4,\n " +
			"  visao.col5,\n " +
			"  visao.col6,\n " +
			"  visao.crct_nnsequenciatipolancamento,\n " +
			"  visao.crct_nnseqitemtplanc,\n " +
			"  visao.lctp_dstipolancamento,\n " +
			"  visao.lcit_dsitemlancamento,\n " +
			"  visao.numeroConta,\n " +
			"  visao.cgtp_id,\n " +
			"  visao.lict_dsitemlancamentocontabil,\n " +
			"  visao.lctp_id,\n " +
			"  visao.catg_id";	
			
			// executa o sql
			SQLQuery query = 
				session.createSQLQuery(consulta);
			
			// Unidade de Negocio
			if ( unidadeNegocio != null && !unidadeNegocio.equals( 0 ) ){
				query.setInteger( "idUnidadeNegocio", unidadeNegocio );	
			}
			
			// Gerencia Regional		
			if ( gerencia != null && !gerencia.equals( 0 ) ){
				query.setInteger( "idGerenciaRegional", gerencia );	
			}		
			
			// Localidade
			if ( localidade != null && !localidade.equals( 0 ) ){
				query.setInteger( "idLocalidade", localidade );	
			}
			
			//Município
			if(municipio != null && !municipio.equals(0)){
				query.setInteger( "idMunicipio", municipio);
			}
			
			retorno = query
				.addScalar( "col0", Hibernate.INTEGER )
				.addScalar( "col1", Hibernate.STRING )
				.addScalar( "col2", Hibernate.INTEGER )
				.addScalar( "col3", Hibernate.STRING )
				.addScalar( "col4", Hibernate.STRING )
				.addScalar( "col5", Hibernate.STRING )
				.addScalar( "col6", Hibernate.INTEGER )
				.addScalar( "crct_nnsequenciatipolancamento", Hibernate.INTEGER )
				.addScalar( "crct_nnseqitemtplanc", Hibernate.INTEGER )
				.addScalar( "lctp_dstipolancamento", Hibernate.STRING )
				.addScalar( "lcit_dsitemlancamento", Hibernate.STRING )
				.addScalar( "numeroConta", Hibernate.INTEGER )
				.addScalar( "cgtp_id", Hibernate.INTEGER )
				.addScalar( "valor", Hibernate.BIG_DECIMAL )
				.addScalar( "lict_dsitemlancamentocontabil", Hibernate.STRING )
				.addScalar( "lctp_id", Hibernate.INTEGER )
				.addScalar( "catg_id", Hibernate.INTEGER )
				.setString( "opcaoTotalizacao", opcaoTotalizacao )
				.setInteger( "anoMesReferencia", anoMesReferencia ).list();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessãoMunicipio
			HibernateUtil.closeSession(session);
		}

		return retorno;			

	}
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/04/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa1(
            Integer referenciaInicio, Integer referenciaFinal ,Integer quadra,Short periodicidade) throws ErroRepositorioException {
        
        Collection<Object[]> retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
            
            consulta = montarConsultaContasBaixadasContabilmente(
                    referenciaInicio, referenciaFinal, false, true,periodicidade);
           
            if (referenciaInicio == null || referenciaInicio.equals(0)){
                retorno = session.createSQLQuery(consulta)
                .addScalar("referenciaBaixaContabil",Hibernate.INTEGER)
                .addScalar("idImovel",Hibernate.INTEGER)
                .addScalar("referenciaFatura",Hibernate.INTEGER)
                .addScalar("idLocalidade",Hibernate.INTEGER)
                .addScalar("cdSetorComercial",Hibernate.INTEGER)
                .addScalar("quadra",Hibernate.INTEGER)
                .addScalar("lote",Hibernate.SHORT)
                .addScalar("sublote",Hibernate.SHORT)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .addScalar("usuario",Hibernate.STRING)
                .addScalar("situacaoAgua",Hibernate.STRING)
                .addScalar("situacaoEsgoto",Hibernate.STRING)
                .setInteger("quadra",quadra)
                .setInteger("usuario",ClienteRelacaoTipo.USUARIO)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_1)
                .list();
            }else{
                retorno = session.createSQLQuery(consulta)
                .addScalar("referenciaBaixaContabil",Hibernate.INTEGER)
                .addScalar("idImovel",Hibernate.INTEGER)
                .addScalar("referenciaFatura",Hibernate.INTEGER)
                .addScalar("idLocalidade",Hibernate.INTEGER)
                .addScalar("cdSetorComercial",Hibernate.INTEGER)
                .addScalar("quadra",Hibernate.INTEGER)
                .addScalar("lote",Hibernate.SHORT)
                .addScalar("sublote",Hibernate.SHORT)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .addScalar("usuario",Hibernate.STRING)
                .addScalar("situacaoAgua",Hibernate.STRING)
                .addScalar("situacaoEsgoto",Hibernate.STRING)
                .setInteger("quadra",quadra)
                .setInteger("usuario",ClienteRelacaoTipo.USUARIO)
                .setInteger("referenciaInicio",referenciaInicio)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_1)
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
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa2(
            Integer referenciaInicio, Integer referenciaFinal ,Integer quadra,Short periodicidade) throws ErroRepositorioException {
        
        Collection<Object[]> retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
            
            consulta = montarConsultaContasBaixadasContabilmente(
                    referenciaInicio, referenciaFinal, true, true, periodicidade);
           
            if (referenciaInicio == null || referenciaInicio.equals(0)){
                retorno = session.createSQLQuery(consulta)
                .addScalar("referenciaBaixaContabil",Hibernate.INTEGER)
                .addScalar("idImovel",Hibernate.INTEGER)
                .addScalar("referenciaFatura",Hibernate.INTEGER)
                .addScalar("idLocalidade",Hibernate.INTEGER)
                .addScalar("cdSetorComercial",Hibernate.INTEGER)
                .addScalar("quadra",Hibernate.INTEGER)
                .addScalar("lote",Hibernate.SHORT)
                .addScalar("sublote",Hibernate.SHORT)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .addScalar("usuario",Hibernate.STRING)
                .addScalar("situacaoAgua",Hibernate.STRING)
                .addScalar("situacaoEsgoto",Hibernate.STRING)
                .setInteger("quadra",quadra)
                .setInteger("usuario",ClienteRelacaoTipo.USUARIO)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_1_MAIS_1)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_2)
                .list();
            }else{
                retorno = session.createSQLQuery(consulta)
                .addScalar("referenciaBaixaContabil",Hibernate.INTEGER)
                .addScalar("idImovel",Hibernate.INTEGER)
                .addScalar("referenciaFatura",Hibernate.INTEGER)
                .addScalar("idLocalidade",Hibernate.INTEGER)
                .addScalar("cdSetorComercial",Hibernate.INTEGER)
                .addScalar("quadra",Hibernate.INTEGER)
                .addScalar("lote",Hibernate.SHORT)
                .addScalar("sublote",Hibernate.SHORT)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .addScalar("usuario",Hibernate.STRING)
                .addScalar("situacaoAgua",Hibernate.STRING)
                .addScalar("situacaoEsgoto",Hibernate.STRING)
                .setInteger("quadra",quadra)
                .setInteger("usuario",ClienteRelacaoTipo.USUARIO)
                .setInteger("referenciaInicio",referenciaInicio)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_1_MAIS_1)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_2)
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
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa3(
            Integer referenciaInicio, Integer referenciaFinal ,Integer quadra,Short periodicidade) throws ErroRepositorioException {
        
        Collection<Object[]> retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
            
            consulta = montarConsultaContasBaixadasContabilmente(
                    referenciaInicio, referenciaFinal, true, false, periodicidade);
           
            if (referenciaInicio == null || referenciaInicio.equals(0)){
                retorno = session.createSQLQuery(consulta)
                .addScalar("referenciaBaixaContabil",Hibernate.INTEGER)
                .addScalar("idImovel",Hibernate.INTEGER)
                .addScalar("referenciaFatura",Hibernate.INTEGER)
                .addScalar("idLocalidade",Hibernate.INTEGER)
                .addScalar("cdSetorComercial",Hibernate.INTEGER)
                .addScalar("quadra",Hibernate.INTEGER)
                .addScalar("lote",Hibernate.SHORT)
                .addScalar("sublote",Hibernate.SHORT)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .addScalar("usuario",Hibernate.STRING)
                .addScalar("situacaoAgua",Hibernate.STRING)
                .addScalar("situacaoEsgoto",Hibernate.STRING)
                .setInteger("quadra",quadra)
                .setInteger("usuario",ClienteRelacaoTipo.USUARIO)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_3)
                .list();
            }else{
                retorno = session.createSQLQuery(consulta)
                .addScalar("referenciaBaixaContabil",Hibernate.INTEGER)
                .addScalar("idImovel",Hibernate.INTEGER)
                .addScalar("referenciaFatura",Hibernate.INTEGER)
                .addScalar("idLocalidade",Hibernate.INTEGER)
                .addScalar("cdSetorComercial",Hibernate.INTEGER)
                .addScalar("quadra",Hibernate.INTEGER)
                .addScalar("lote",Hibernate.SHORT)
                .addScalar("sublote",Hibernate.SHORT)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .addScalar("usuario",Hibernate.STRING)
                .addScalar("situacaoAgua",Hibernate.STRING)
                .addScalar("situacaoEsgoto",Hibernate.STRING)
                .setInteger("quadra",quadra)
                .setInteger("usuario",ClienteRelacaoTipo.USUARIO)
                .setInteger("referenciaInicio",referenciaInicio)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_3)
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
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @exception ErroRepositorioException
     *                Repositorio Exception
     */
    public String montarConsultaContasBaixadasContabilmente(
            Integer referenciaInicio, Integer referenciaFinal,
            boolean valorFaixaInicio, boolean valorFaixaFinal,Short periodicidade) {

        String consulta = "";

        consulta = " (SELECT " +
        " ct.cnta_amreferenciabaixacontabil as referenciaBaixaContabil,  " +
        " im.imov_id as idImovel,  " +
        " ct.cnta_amreferenciaconta as referenciaFatura, " +
        " ct.loca_id as idLocalidade,  " +
        " ct.cnta_cdsetorcomercial as cdSetorComercial, " +
        " ct.cnta_nnquadra as quadra,  " +
        " ct.cnta_nnlote as lote,  " +
        " ct.cnta_nnsublote as sublote,  " +
        " ( (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos) - ( " +
        "       coalesce((SELECT sum(debCob.dbcb_vlprestacao) " +
        "			 	  FROM faturamento.debito_cobrado debCob " +
        "		 		  WHERE debCob.cnta_id = ct.cnta_id and debCob.fntp_id = " + FinanciamentoTipo.DOACOES +
        "		), 0) " + 
        "	) ) as valorConta,  " +
        " cl.clie_nmcliente as usuario,  " +
        " la.last_dsligacaoaguasituacao as situacaoAgua,  " +
        " le.lest_dsligacaoesgotosituacao as situacaoEsgoto  " +

        " FROM  faturamento.conta ct  " +
        " INNER JOIN atendimentopublico.ligacao_agua_situacao la ON la.last_id = ct.last_id  " +
        " INNER JOIN atendimentopublico.ligacao_esgoto_situacao le ON le.lest_id = ct.lest_id  " +
        " INNER JOIN cadastro.imovel im ON im.imov_id = ct.imov_id  " +
        " INNER JOIN cadastro.cliente_imovel ci ON im.imov_id = ci.imov_id AND ci.crtp_id = :usuario AND ci.clim_dtrelacaofim is null  " +
        " INNER JOIN cadastro.cliente cl ON ci.clie_id = cl.clie_id  " +

        " WHERE ct.qdra_id = :quadra ";
                
        if (referenciaInicio != null && !referenciaInicio.equals(0) &&
            referenciaFinal != null && !referenciaFinal.equals(0)){
            
            consulta = consulta + 
            " AND ct.cnta_amreferenciabaixacontabil between :referenciaInicio AND :referenciaFinal "; 
            
        }else if(referenciaFinal != null && !referenciaFinal.equals(0)){
            
            consulta = consulta + 
            " AND ct.cnta_amreferenciabaixacontabil <= :referenciaFinal ";
        }  
        
        if (valorFaixaInicio && valorFaixaFinal){
            
            consulta = consulta + 
            " AND (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos)" +
            " between :valorFaixaInicio AND :valorFaixaFinal "; 
            
        }else if(valorFaixaInicio){
            
            consulta = consulta + 
            " AND (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos) >= :valorFaixaInicio ";
            
        }else if(valorFaixaFinal){
            
            consulta = consulta + 
            " AND (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos) <= :valorFaixaFinal ";
            
        }
                
        consulta = consulta +  ")" ;
       
//        " ORDER BY ct.cnta_amreferenciabaixacontabil, im.imov_id, ct.cnta_amreferenciaconta) " +

        
        if(!periodicidade.equals(ConstantesSistema.ACUMULADO)){
        	 consulta = consulta + " UNION ALL " +

             " (SELECT  " +
             " ct.cnhi_amreferenciabaixacontabil as referenciaBaixaContabil,  " +
             " im.imov_id as idImovel,  " +
             " ct.cnhi_amreferenciaconta as referenciaFatura, " +
             " ct.loca_id as idLocalidade,  " +
             " ct.cnhi_cdsetorcomercial as cdSetorComercial, " +
             " ct.cnhi_nnquadra as quadra,  " +
             " ct.cnhi_nnlote as lote,  " +
             " ct.cnhi_nnsublote as sublote,  " +
             " ( (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos) - ( " +
             "       coalesce((SELECT sum(debCobHist.dbhi_vlprestacao) " +
             "			 	  FROM faturamento.debito_cobrado_historico debCobHist " +
             "		 		  WHERE debCobHist.cnta_id = ct.cnta_id and debCobHist.fntp_id = " + FinanciamentoTipo.DOACOES +
             "		), 0) " + 
             "	) ) as valorConta, " +
             " cl.clie_nmcliente as usuario,  " +
             " la.last_dsligacaoaguasituacao as situacaoAgua,  " +
             " le.lest_dsligacaoesgotosituacao as situacaoEsgoto " +

             " FROM faturamento.conta_historico ct  " +
             " INNER JOIN atendimentopublico.ligacao_agua_situacao la ON la.last_id = ct.last_id  " +
             " INNER JOIN atendimentopublico.ligacao_esgoto_situacao le ON le.lest_id = ct.lest_id  " +
             " INNER JOIN cadastro.imovel im ON im.imov_id = ct.imov_id  " +
             " INNER JOIN cadastro.cliente_imovel ci ON im.imov_id = ci.imov_id AND ci.crtp_id = :usuario AND ci.clim_dtrelacaofim is null  " +
             " INNER JOIN cadastro.cliente cl ON ci.clie_id = cl.clie_id  " +

             " WHERE ct.qdra_id = :quadra ";
                     
             if (referenciaInicio != null && !referenciaInicio.equals(0) &&
                 referenciaFinal != null && !referenciaFinal.equals(0)){
                 
                 consulta = consulta + 
                 " AND ct.cnhi_amreferenciabaixacontabil between :referenciaInicio AND :referenciaFinal "; 
                 
             }else if(referenciaFinal != null && !referenciaFinal.equals(0)){
                 
                 consulta = consulta + 
                 " AND ct.cnhi_amreferenciabaixacontabil <= :referenciaFinal ";
             }  
                  
             if (valorFaixaInicio && valorFaixaFinal){
                 
                 consulta = consulta + 
                 "AND (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos)" +
                 " between :valorFaixaInicio AND :valorFaixaFinal "; 
                 
             }else if(valorFaixaInicio){
                 
                 consulta = consulta + 
                 "AND (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos) >= :valorFaixaInicio ";
                 
             }else if(valorFaixaFinal){
                 
                 consulta = consulta + 
                 " AND (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos) <= :valorFaixaFinal ";
                 
             }   
             
             consulta = consulta +  ")";  
             
//           " ORDER BY ct.cnhi_amreferenciabaixacontabil, im.imov_id, ct.cnhi_amreferenciaconta) " ;
        }

        return consulta;
    }
    
    
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade) throws ErroRepositorioException {
        
        Collection retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
            
            consulta = montarConsultaSomatorioValorContasBaixadasContabilmente(
                    referenciaInicio, referenciaFinal, false, true, periodicidade);
           
            if (referenciaInicio == null || referenciaInicio.equals(0)){
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_1)
                .list();
            }else{
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .setInteger("referenciaInicio",referenciaInicio)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_1)
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
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade) throws ErroRepositorioException {
        
        Collection retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
            
            consulta = montarConsultaSomatorioValorContasBaixadasContabilmente(
                referenciaInicio, referenciaFinal, true, true, periodicidade);
           
            if (referenciaInicio == null || referenciaInicio.equals(0)){
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_1_MAIS_1)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_2)
                .list();
            }else{
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .setInteger("referenciaInicio",referenciaInicio)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_1_MAIS_1)
                .setBigDecimal("valorFaixaFinal",ConstantesSistema.FAIXA_VALOR_2)
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
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException {
        
        Collection retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
            
            consulta = montarConsultaSomatorioValorContasBaixadasContabilmente(
                    referenciaInicio, referenciaFinal, true, false, periodicidade);

            if (referenciaInicio == null || referenciaInicio.equals(0)){
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_3)
                .list();
            }else{
                retorno = session.createSQLQuery(consulta)
                .addScalar("valorConta",Hibernate.BIG_DECIMAL)
                .setInteger("referenciaInicio",referenciaInicio)
                .setInteger("referenciaFinal",referenciaFinal)
                .setBigDecimal("valorFaixaInicio",ConstantesSistema.FAIXA_VALOR_3)
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
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @exception ErroRepositorioException
     *                Repositorio Exception
     */
    public String montarConsultaSomatorioValorContasBaixadasContabilmente(
            Integer referenciaInicio, Integer referenciaFinal,
            boolean valorFaixaInicio, boolean valorFaixaFinal,Short periodicidade) {

        String consulta = "";

        consulta = " (SELECT " +
        " sum(ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos) as valorConta " +
        " FROM  faturamento.conta ct  " +
        " WHERE ";
                
        if (referenciaInicio != null && !referenciaInicio.equals(0) &&
            referenciaFinal != null && !referenciaFinal.equals(0)){
            
            consulta = consulta + 
            " ct.cnta_amreferenciabaixacontabil between :referenciaInicio AND :referenciaFinal "; 
            
        }else if(referenciaFinal != null && !referenciaFinal.equals(0)){
            
            consulta = consulta + 
            " ct.cnta_amreferenciabaixacontabil <= :referenciaFinal ";
        }  
        
        if (valorFaixaInicio && valorFaixaFinal){
            
            consulta = consulta + 
            " AND (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos)" +
            " between :valorFaixaInicio AND :valorFaixaFinal "; 
            
        }else if(valorFaixaInicio){
            
            consulta = consulta + 
            " AND (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos) >= :valorFaixaInicio ";
            
        }else if(valorFaixaFinal){
            
            consulta = consulta + 
            " AND (ct.cnta_vlagua+ct.cnta_vlesgoto+ct.cnta_vldebitos-ct.cnta_vlcreditos) <= :valorFaixaFinal ";
            
        }
                
        consulta = consulta +  ")" ;
        
        if(!periodicidade.equals(ConstantesSistema.ACUMULADO)){
        	
        	consulta = consulta +" UNION ALL " +
            " (SELECT  " +
            " sum(ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos) as valorConta  " +
            " FROM faturamento.conta_historico ct  " +
            " WHERE ";
                    
            if (referenciaInicio != null && !referenciaInicio.equals(0) &&
                referenciaFinal != null && !referenciaFinal.equals(0)){
                
                consulta = consulta + 
                " ct.cnhi_amreferenciabaixacontabil between :referenciaInicio AND :referenciaFinal "; 
                
            }else if(referenciaFinal != null && !referenciaFinal.equals(0)){
                
                consulta = consulta + 
                " ct.cnhi_amreferenciabaixacontabil <= :referenciaFinal ";
            }  
                 
            if (valorFaixaInicio && valorFaixaFinal){
                
                consulta = consulta + 
                "AND (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos)" +
                " between :valorFaixaInicio AND :valorFaixaFinal "; 
                
            }else if(valorFaixaInicio){
                
                consulta = consulta + 
                "AND (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos) >= :valorFaixaInicio ";
                
            }else if(valorFaixaFinal){
                
                consulta = consulta + 
                " AND (ct.cnhi_vlagua+ct.cnhi_vlesgoto+ct.cnhi_vldebitos-ct.cnhi_vlcreditos) <= :valorFaixaFinal ";
                
            }   
            
            consulta = consulta +  ")";   
        }
        
          

        return consulta;
    }
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Rafael Corrêa
     * @date: 29/05/2013 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<BigDecimal> consultarSomatorioValorDoacoesContasBaixadasContabilmente(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException {
        
        Collection<BigDecimal> retorno = null;
        Session session = HibernateUtil.getSession();
        String consulta = null;
       
        try {
        	
        	String whereReferencia = "";
        	
        	if (referenciaInicio != null && !referenciaInicio.equals(0) &&
                    referenciaFinal != null && !referenciaFinal.equals(0)) {
        		whereReferencia = whereReferencia + " BETWEEN :referenciaInicio AND :referenciaFinal";
            } else {
            	whereReferencia = whereReferencia + " <= :referenciaFinal";
            }
            
            consulta = "SELECT sum(debCob.dbcb_vlprestacao) as valorDoacao " 
            		+ "FROM faturamento.conta conta "
            		+ "INNER JOIN faturamento.debito_cobrado debCob on debCob.cnta_id = conta.cnta_id "
            		+ "WHERE debCob.fntp_id = " + FinanciamentoTipo.DOACOES + " and conta.cnta_amreferenciabaixacontabil "
            		+ whereReferencia;
            
            if(!periodicidade.equals(ConstantesSistema.ACUMULADO)){
            
            		consulta += " UNION ALL "
            				+ "SELECT sum(debCobHist.dbhi_vlprestacao) " 
            				+ "FROM faturamento.conta_historico contaHist "
            				+ "INNER JOIN faturamento.debito_cobrado_historico debCobHist on debCobHist.cnta_id = contaHist.cnta_id "
            				+ "WHERE debCobHist.fntp_id = " + FinanciamentoTipo.DOACOES + " and contaHist.cnhi_amreferenciabaixacontabil "
            				+ whereReferencia;
            		
            }

            if (referenciaInicio == null || referenciaInicio.equals(0)) {
                retorno = session.createSQLQuery(consulta)
                			.addScalar("valorDoacao",Hibernate.BIG_DECIMAL)
                			.setInteger("referenciaFinal",referenciaFinal)
                			.list();
            } else {
                retorno = session.createSQLQuery(consulta)
                			.addScalar("valorDoacao",Hibernate.BIG_DECIMAL)
                			.setInteger("referenciaInicio",referenciaInicio)
                			.setInteger("referenciaFinal",referenciaFinal)
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
	 * [UC0824] Gerar Relatório dos Parâmetros Contábeis
	 * 
	 * @author Bruno Barros
	 * @date 08/07/2008
	 * 
	 * @return Collection<RelatorioParametrosContabeisFaturamentoBean>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> 
		pesquisarDadosRelatorioParametrosContabeisFaturamento( Integer referenciaContabil ) throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			if ( referenciaContabil != null ){
				consulta =
                    "select " +
                    "  * " +
                    "from " +
                    "( " +
                    "select " +
                    "  lanTip.lctp_dstipolancamento as descricaoTipoLancamento, " +
                    "  lanIte.lcit_dsitemlancamento as descricaoItemLancamento, " +
                    "  null as descricaoitemlancamentocontabi, " +
                    "  cat.catg_dsabreviado as descricaoCategoria, " +
                    "  conConDeb.cnct_nnconta as numeroContaDebito, " +
                    "  conConCre.cnct_nnconta as numeroContaCredito, " +
                    "  sum(coalesce( resFat.rfat_vlitemfaturamento, 0 ) ) as total, " +
                    "  fatConPar.plcf_id, " +
                    "  resFat.rfat_nnsequenciatipolancamento, " +
                    "  resfat.rfat_nnseqitemtplanc, " +
                    "  resFat.catg_id " +
                    "from " +
                    "  financeiro.resumo_faturamento resFat " +
                    "  inner join financeiro.lancamento_tipo lanTip on ( lanTip.lctp_id = resFat.lctp_id ) " +
                    "  inner join financeiro.lancamento_item lanIte on ( lanIte.lcit_id = resFat.lcit_id ) " +
                    "  inner join faturamento.fatur_contb_parametros fatConPar on ( fatConPar.lctp_id = resFat.lctp_id and fatConPar.lcit_id = resFat.lcit_id and fatConPar.catg_id = resFat.catg_id ) " +
                    "  inner join financeiro.conta_contabil conConCre on (  conConCre.cnct_id = fatConPar.cnct_idcredito ) " +
                    "  inner join financeiro.conta_contabil conConDeb on (  conConDeb.cnct_id = fatConPar.cnct_iddebito ) " +
                    "  inner join cadastro.categoria cat on ( cat.catg_id = resFat.catg_id ) " +
                    "where " +
                    "  resFat.rfat_amreferencia = :amReferencia and resFat.lict_id is null " +
                    "group by " +
                    "  lanTip.lctp_dstipolancamento, " +
                    "  lanIte.lcit_dsitemlancamento, " +
                    "  cat.catg_dsabreviado, " +
                    "  conConCre.cnct_nnconta, " +
                    "  conConDeb.cnct_nnconta, " +
                    "  fatConPar.plcf_id, " +
                    "  resFat.rfat_nnsequenciatipolancamento, " +
                    "  resfat.rfat_nnseqitemtplanc, " +
                    "  resFat.catg_id " +
                    "union " +
                    "select " +
                    "  lanTip.lctp_dstipolancamento as descricaoTipoLancamento, " +
                    "  lanIte.lcit_dsitemlancamento as descricaoItemLancamento, " +
                    "  lanIteCon.lict_dsitemlancamentocontabil, " +
                    "  cat.catg_dsabreviado as descricaoCategoria, " +
                    "  conConDeb.cnct_nnconta as numeroContaDebito, " +
                    "  conConCre.cnct_nnconta as numeroContaCredito, " +
                    "  sum(coalesce( resFat.rfat_vlitemfaturamento, 0 ) ) as total, " +
                    "  fatConPar.plcf_id, " +
                    "  resFat.rfat_nnsequenciatipolancamento, " +
                    "  resfat.rfat_nnseqitemtplanc, " +
                    "  resFat.catg_id " +
                    "from " +
                    "  financeiro.resumo_faturamento resFat " +
                    "  inner join financeiro.lancamento_tipo lanTip on ( lanTip.lctp_id = resFat.lctp_id ) " +
                    "  inner join financeiro.lancamento_item lanIte on ( lanIte.lcit_id = resFat.lcit_id ) " +
                    "  inner join faturamento.fatur_contb_parametros fatConPar on ( fatConPar.lctp_id = resFat.lctp_id and fatConPar.lcit_id = resFat.lcit_id and fatConPar.catg_id = resFat.catg_id and resFat.lict_id = fatConPar.lict_id ) " +
                    "  left join financeiro.lancamento_item_contabil lanIteCon on ( resFat.lict_id = lanIteCon.lict_id ) " +
                    "  inner join financeiro.conta_contabil conConCre on (  conConCre.cnct_id = fatConPar.cnct_idcredito ) " +
                    "  inner join financeiro.conta_contabil conConDeb on (  conConDeb.cnct_id = fatConPar.cnct_iddebito ) " +
                    "  inner join cadastro.categoria cat on ( cat.catg_id = resFat.catg_id ) " +
                    "where " +
                    "  resFat.rfat_amreferencia = :amReferencia and resFat.lict_id is not null " +
                    "group by " +
                    "  lanTip.lctp_dstipolancamento, " +
                    "  lanIte.lcit_dsitemlancamento, " +
                    "  lanIteCon.lict_dsitemlancamentocontabil, " +
                    "  cat.catg_dsabreviado, " +
                    "  conConCre.cnct_nnconta, " +
                    "  conConDeb.cnct_nnconta, " +
                    "  fatConPar.plcf_id, " +
                    "  resFat.rfat_nnsequenciatipolancamento, " +
                    "  resfat.rfat_nnseqitemtplanc, " +
                    "  resFat.catg_id ) juncao " +
                    "order by " +
                    "  juncao.rfat_nnsequenciatipolancamento, " +
                    "  juncao.rfat_nnseqitemtplanc, " +
                    "  juncao.catg_id ";
				
				// executa o sql
				retorno = session.createSQLQuery(consulta)
							.addScalar("descricaoTipoLancamento", Hibernate.STRING)
							.addScalar("descricaoItemLancamento", Hibernate.STRING)
							.addScalar("descricaoitemlancamentocontabi", Hibernate.STRING)
							.addScalar("descricaoCategoria", Hibernate.STRING)
							.addScalar("numeroContaDebito", Hibernate.STRING)
							.addScalar("numeroContaCredito", Hibernate.STRING)
							.addScalar("total", Hibernate.BIG_DECIMAL)
							.setInteger( "amReferencia", referenciaContabil ).list();				
			} else {
				consulta =
                  "select " + 
                  "  lanTip.lctp_dstipolancamento descricaoTipoLancamento, " +
                  "  lanIte.lcit_dsitemlancamento as descricaoItemLancamento, " +
                  "  lanIteCon.lict_dsitemlancamentocontabil as descricaoitemlancamentocontabi, " +
                  "  cat.catg_dsabreviado as descricaoCategoria, " +
                  "  conConDeb.cnct_nnconta as numeroContaDebito, " +
                  "  conConCre.cnct_nnconta as numeroContaCredito " +
                  "from " +
                  "  faturamento.fatur_contb_parametros fatConPar " +
                  "  inner join financeiro.lancamento_tipo lanTip on ( fatConPar.lctp_id = lanTip.lctp_id ) " +
                  "  inner join financeiro.lancamento_item lanIte on ( fatConPar.lcit_id = lanIte.lcit_id ) " +
                  "  inner join financeiro.conta_contabil conConCre on ( fatConPar.cnct_idcredito = conConCre.cnct_id ) " +
                  "  inner join financeiro.conta_contabil conConDeb on ( fatConPar.cnct_iddebito = conConDeb.cnct_id ) " +
                  "  inner join cadastro.categoria cat on ( cat.catg_id = fatConPar.catg_id ) " +
                  "  left join financeiro.lancamento_item_contabil lanIteCon on ( lanIteCon.lict_id = fatConPar.lict_id ) " +
                  "order by " +
                  "  case when ( lanIteCon.lict_dsitemlancamentocontabil is null ) then 0 else 1 end, " +
                  "  fatConPar.plcf_id ";
		
				
				// executa o sql
				retorno = session.createSQLQuery(consulta)
							.addScalar("descricaoTipoLancamento", Hibernate.STRING)
							.addScalar("descricaoItemLancamento", Hibernate.STRING)
							.addScalar("descricaoitemlancamentocontabi", Hibernate.STRING)
							.addScalar("descricaoCategoria", Hibernate.STRING)
							.addScalar("numeroContaDebito", Hibernate.STRING)				
							.addScalar("numeroContaCredito", Hibernate.STRING).list();
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
         * [UC0824] Gerar Relatório dos Parâmetros Contábeis
         * 
         * @author Bruno Barros
         * @date 08/07/2008
         * 
         * @return Collection<RelatorioParametrosContabeisArrecadacaoBean>
         * @throws ErroRepositorioException
         */
        public Collection<Object[]> 
            pesquisarDadosRelatorioParametrosContabeisArrecadacao( Integer referenciaContabil ) throws ErroRepositorioException {
            Collection<Object[]> retorno = null;

            // cria uma sessão com o hibernate
            Session session = HibernateUtil.getSession();

            // cria a variável que vai conter o hql
            String consulta;

            try {
                // constroi o sql
                if ( referenciaContabil != null ){
                    consulta =
                        "select " +
                        "  * " +
                        "from " +
                        "( " +
                        "select " +
                        "  b.rctp_dsrecebimentotipo as recebimentoTipo, " +
                        "  c.lctp_dstipolancamento as descricaoTipoLancamento, " +
                        "  d.lcit_dsitemlancamento as descricaoItemLancamento, " +
                        "  h.catg_dsabreviado as descricaoCategoria, " +
                        "  null as descricaoitemlancamentocontabi, " +
                        "  g.cnct_nnconta as numeroContaCredito, " +
                        "  f.cnct_nnconta as numeroContaDebito, " +                        
                        "  sum( coalesce( a.rarr_vlitemarrecadacao, 0 ) ) as total, " +
                        "  e.plca_id, " +
                        "  a.rarr_nnsequenciatipolancamento, " +
                        "  a.rarr_nnseqitemtplanc, " +
                        "  a.catg_id " +
                        "from " +
                        "  arrecadacao.resumo_arrecadacao a " +
                        "  inner join arrecadacao.recebimento_tipo b on ( a.rctp_id=b.rctp_id ) " +
                        "  inner join financeiro.lancamento_tipo c on ( a.lctp_id=c.lctp_id ) " +
                        "  inner join financeiro.lancamento_item d on ( a.lcit_id=d.lcit_id ) " +
                        "  inner join arrecadacao.arrec_contb_parametros e on ( a.rctp_id=e.rctp_id and a.lctp_id=e.lctp_id and a.lcit_id=e.lcit_id and a.catg_id = e.catg_id) " +
                        "  inner join financeiro.conta_contabil f on ( e.cnct_iddebito=f.cnct_id ) " +
                        "  inner join financeiro.conta_contabil g on ( e.cnct_idcredito=g.cnct_id ) " +
                        "  inner join cadastro.categoria h on ( a.catg_id = h.catg_id ) " +
                        "where " +
                        "  a.rarr_amreferencia = :amReferencia and a.lict_id is null " +
                        "group by " +
                        "  b.rctp_dsrecebimentotipo,c.lctp_dstipolancamento,d.lcit_dsitemlancamento,h.catg_dsabreviado,g.cnct_nnconta,f.cnct_nnconta,e.plca_id,a.rarr_nnsequenciatipolancamento,a.rarr_nnseqitemtplanc,a.catg_id " +
                        "union " +
                        "select " +
                        "  b.rctp_dsrecebimentotipo, " +
                        "  c.lctp_dstipolancamento, " +
                        "  d.lcit_dsitemlancamento, " +
                        "  i.catg_dsabreviado, " +
                        "  e.lict_dsitemlancamentocontabil, " +
                        "  h.cnct_nnconta, " +
                        "  g.cnct_nnconta, " +
                        "  sum( coalesce( a.rarr_vlitemarrecadacao, 0 ) ), " +
                        "  f.plca_id, " +
                        "  a.rarr_nnsequenciatipolancamento, " +
                        "  a.rarr_nnseqitemtplanc, " +
                        "  a.catg_id " +
                        "from " +
                        "  arrecadacao.resumo_arrecadacao a " +
                        "  inner join arrecadacao.recebimento_tipo b on ( a.rctp_id=b.rctp_id ) " +
                        "  inner join financeiro.lancamento_tipo c on ( a.lctp_id=c.lctp_id ) " +
                        "  inner join financeiro.lancamento_item d on ( a.lcit_id=d.lcit_id ) " +
                        "  inner join financeiro.lancamento_item_contabil e on ( a.lict_id=e.lict_id ) " +
                        "  inner join arrecadacao.arrec_contb_parametros f on ( a.rctp_id=f.rctp_id and a.lctp_id=f.lctp_id and a.lcit_id=f.lcit_id and a.lict_id=f.lict_id and f.catg_id = a.catg_id ) " +
                        "  inner join financeiro.conta_contabil g on ( f.cnct_iddebito=g.cnct_id ) " +
                        "  inner join financeiro.conta_contabil h on ( f.cnct_idcredito=h.cnct_id ) " +
                        "  inner join cadastro.categoria i on ( a.catg_id=i.catg_id ) " +
                        "where " +
                        "  a.rarr_amreferencia = :amReferencia and a.lict_id is not null " +
                        "group by " +
                        " b.rctp_dsrecebimentotipo,c.lctp_dstipolancamento,d.lcit_dsitemlancamento,i.catg_dsabreviado,e.lict_dsitemlancamentocontabil,h.cnct_nnconta,g.cnct_nnconta,f.plca_id,a.rarr_nnsequenciatipolancamento,a.rarr_nnseqitemtplanc,a.catg_id " +
                        ") juncao " +
                        "order by " +
                        "  juncao.rarr_nnsequenciatipolancamento, " +
                        "  juncao.rarr_nnseqitemtplanc, " +
                        "  juncao.catg_id ";
                    // executa o sql
                    retorno = session.createSQLQuery(consulta)
                                .addScalar("recebimentoTipo", Hibernate.STRING)
                                .addScalar("descricaoTipoLancamento", Hibernate.STRING)
                                .addScalar("descricaoItemLancamento", Hibernate.STRING)
                                .addScalar("descricaoCategoria", Hibernate.STRING)
                                .addScalar("descricaoitemlancamentocontabi", Hibernate.STRING)                                
                                .addScalar("numeroContaCredito", Hibernate.STRING)
                                .addScalar("numeroContaDebito", Hibernate.STRING)
                                .addScalar("total", Hibernate.BIG_DECIMAL)
                                .setInteger( "amReferencia", referenciaContabil ).list();               
                } else {
                    consulta =
                        "select " +
                        "  * " +
                        "from " +
                        "( " +
                        "select " +
                        "  b.rctp_dsrecebimentotipo recebimentoTipo, " +
                        "  c.lctp_dstipolancamento as descricaoTipoLancamento, " +
                        "  d.lcit_dsitemlancamento as descricaoItemLancamento, " +
                        "  g.catg_dsabreviado as descricaoCategoria, " +
                        "  null as descricaoitemlancamentocontabi, " +
                        "  e.cnct_nnconta as numeroContaCredito, " +
                        "  f.cnct_nnconta as numeroContaDebito, " +
                        "  a.plca_id, " +
                        "  1 as ordenacao " +
                        "from " +
                        "  arrecadacao.arrec_contb_parametros a " +
                        "  inner join arrecadacao.recebimento_tipo b on ( a.rctp_id = b.rctp_id ) " +
                        "  inner join financeiro.lancamento_tipo c on ( a.lctp_id =c.lctp_id ) " +
                        "  inner join financeiro.lancamento_item d on ( a.lcit_id = d.lcit_id ) " +
                        "  inner join financeiro.conta_contabil e on ( a.cnct_iddebito = e.cnct_id ) " +
                        "  inner join financeiro.conta_contabil f on ( a.cnct_idcredito = f.cnct_id ) " +
                        "  inner join cadastro.categoria g on ( a.catg_id = g.catg_id ) " +
                        "where " +
                        "  a.lict_id is null " +
                        "union " +
                        "select " +
                        "  b.rctp_dsrecebimentotipo as recebimentoTipo, " +
                        "  c.lctp_dstipolancamento as descricaoTipoLancamento, " +
                        "  d.lcit_dsitemlancamento as descricaoItemLancamento, " +
                        "  h.catg_dsabreviado as descricaoCategoria, " +
                        "  e.lict_dsitemlancamentocontabil as descricaoitemlancamentocontabi, " +
                        "  f.cnct_nnconta as numeroContaCredito, " +
                        "  g.cnct_nnconta as numeroContaDebito, " +
                        "  a.plca_id, " +
                        "  2 as ordenacao " +
                        "from " +
                        "  arrecadacao.arrec_contb_parametros a " +
                        "  inner join arrecadacao.recebimento_tipo b on ( a.rctp_id = b.rctp_id ) " +
                        "  inner join financeiro.lancamento_tipo c on ( a.lctp_id =c.lctp_id ) " +
                        "  inner join financeiro.lancamento_item d on ( a.lcit_id = d.lcit_id ) " +
                        "  inner join financeiro.lancamento_item_contabil e on ( a.lict_id = e.lict_id ) " +
                        "  inner join financeiro.conta_contabil f on ( a.cnct_iddebito = f.cnct_id ) " +
                        "  inner join financeiro.conta_contabil g on ( a.cnct_idcredito = g.cnct_id ) " +
                        "  inner join cadastro.categoria h on ( a.catg_id = h.catg_id ) " +
                        "where " +
                        "  a.lict_id is not null " +
                        ") as juncao " +
                        "order by " +
                        "  ordenacao, plca_id ";            
                    
                    // executa o sql
                    retorno = session.createSQLQuery(consulta)
                                .addScalar("recebimentoTipo", Hibernate.STRING)
                                .addScalar("descricaoTipoLancamento", Hibernate.STRING)
                                .addScalar("descricaoItemLancamento", Hibernate.STRING)
                                .addScalar("descricaoCategoria", Hibernate.STRING)
                                .addScalar("descricaoitemlancamentocontabi", Hibernate.STRING)                                
                                .addScalar("numeroContaCredito", Hibernate.STRING)
                                .addScalar("numeroContaDebito", Hibernate.STRING).list();               
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
	 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
	 * 
	 * @author Victor Cisneiros
	 * @date 15/07/2008
	 */
    public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
    		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ErroRepositorioException {
    	
    	List<RelatorioVolumesConsumidosNaoFaturadosBean> retorno = 
    		new ArrayList<RelatorioVolumesConsumidosNaoFaturadosBean>();
    	List<Object[]> retornoConsulta = null;
    	Session session = HibernateUtil.getSession();
    	String groupBy = "GROUP BY ";
    	String orderBy = "ORDER BY ";
    	String joinMunicipio = "LEFT JOIN l.municipio m ";
    	if(opcaoTotalizacao.equals("municipio") || opcaoTotalizacao.equals("estadoPorMunicipio")){
    		joinMunicipio = "INNER JOIN l.municipio m ";
    	}
    	
    	String consulta = 
    		"SELECT " + 
    			"g.id, " + // 0
    			"g.nomeAbreviado, " + // 1
    			"g.nome, " + // 2
    			"u.id, " + // 3
    			"u.nomeAbreviado, " + // 4
    			"u.nome, " + // 5 
    			"l.id, " + // 6
    			"l.descricao, " + // 7
    			"c.id, " + // 8
    			"c.descricaoAbreviada, " + // 9
    			"sum( CASE WHEN v.lancamentoItem.id = 1 THEN v.valorItemLancamento ELSE 0 END ), " + // 10
    			"sum( CASE WHEN v.lancamentoItem.id = 2 THEN v.valorItemLancamento ELSE 0 END ), " + // 11
    			"sum( v.valorItemLancamento ), " + // 12
    			"m.id, " + //13
    			"m.nome " + //14
    		"FROM ValorVolumesConsumidosNaoFaturado v " +
    			"INNER JOIN v.gerenciaRegional g " + 
    			"INNER JOIN v.unidadeNegocio u " + 
    			"INNER JOIN v.localidade l " + 
    			joinMunicipio + 
    			"INNER JOIN v.categoria c " +
    		"WHERE " + 
    			"(v.lancamentoItem.id = 1 OR v.lancamentoItem.id = 2) " +
    			" AND v.anoMesReferencia = :anoMesReferencia ";
    	
    	if (opcaoTotalizacao.equals("gerenciaRegional") ||
    		opcaoTotalizacao.equals("gerenciaRegionalPorUnidadeNegocio") ||
    		opcaoTotalizacao.equals("gerenciaRegionalPorLocalidade")) {
    		consulta += " AND g.id = :idEntidade ";
    	}
    	else if (opcaoTotalizacao.equals("unidadeNegocio") ||
    		opcaoTotalizacao.equals("unidadeNegocioPorLocalidade")) {
    		consulta += " AND u.id = :idEntidade ";
    	}
    	else if (opcaoTotalizacao.equals("localidade")) {
    		consulta += " AND l.id = :idEntidade ";
    	}
    	else if(opcaoTotalizacao.equals("municipio")){
    		consulta += " AND m.id = :idEntidade ";
    	}
    	
    	if(opcaoTotalizacao.equals("municipio") || opcaoTotalizacao.equals("estadoPorMunicipio")){
    		groupBy +=	
    			"m.id, " +
    			"m.nome, " +
    			"g.id, " + // 0
    			"g.nomeAbreviado, " + // 1
    			"g.nome, " + // 2
    			"u.id, " + // 3
    			"u.nomeAbreviado, " + // 4
    			"u.nome, " + // 5 
    			"l.id, " + // 6
    			"l.descricao, " + // 7
    			"c.id, " + // 8
    			"c.descricaoAbreviada "; // 9
    		orderBy += 
    			"m.id, " +
    			"m.nome, " +
    			"g.id, " + // 0
	    		"g.nomeAbreviado, " + // 1
	    		"g.nome, " + // 2
	    		"u.id, " + // 3
	    		"u.nomeAbreviado, " + // 4
	    		"u.nome, " + // 5 
	    		"l.id, " + // 6
	    		"l.descricao, " + // 7
	    		"c.id, " + // 8
	    		"c.descricaoAbreviada "; // 9
    	}else{
    		groupBy +=	
    			"g.id, " + // 0
    			"g.nomeAbreviado, " + // 1
    			"g.nome, " + // 2
    			"u.id, " + // 3
    			"u.nomeAbreviado, " + // 4
    			"u.nome, " + // 5 
    			"l.id, " + // 6
    			"l.descricao, " + // 7
    			"c.id, " + // 8
    			"c.descricaoAbreviada, " + // 9
	    		"m.id, " + // 12
	    		"m.nome "; // 13
    		orderBy += "g.id, " + // 0
    		"g.nomeAbreviado, " + // 1
    		"g.nome, " + // 2
    		"u.id, " + // 3
    		"u.nomeAbreviado, " + // 4
    		"u.nome, " + // 5 
    		"l.id, " + // 6
    		"l.descricao, " + // 7
    		"c.id, " + // 8
    		"c.descricaoAbreviada, " + // 9
    		"m.id, " + // 12
    		"m.nome "; // 13
    	}
		
		consulta += groupBy + orderBy;
    	try {
	    	Query q = session.createQuery(consulta);
	    	q.setInteger("anoMesReferencia", mesAno);
	    	if (idEntidade != null) {
	    		q.setInteger("idEntidade", idEntidade);
	    	}
	    	
	    	retornoConsulta = (List<Object[]>) q.list();
	    	
	    	RelatorioVolumesConsumidosNaoFaturadosBean bean = null;
	    	GerenciaRegional gerencia = null;
	    	UnidadeNegocio unidade = null;
	    	Localidade localidade = null;
	    	Municipio municipio = null;
	    	Categoria categoria = null;

	    	for (Object[] linha : retornoConsulta) {
	    		bean = new RelatorioVolumesConsumidosNaoFaturadosBean();
	    		gerencia = new GerenciaRegional();
	    		unidade = new UnidadeNegocio();
	    		localidade = new Localidade();
	    		categoria = new Categoria();
	    		
	    		gerencia.setId((Integer) linha[0]);
	    		gerencia.setNomeAbreviado((String) linha[1]);
	    		gerencia.setNome((String) linha[2]);
	    		
	    		unidade.setId((Integer) linha[3]);
	    		unidade.setNomeAbreviado((String) linha[4]);
	    		unidade.setNome((String) linha[5]);
	    		
	    		localidade.setId((Integer) linha[6]);
	    		localidade.setDescricao((String) linha[7]);

	    		Integer idMuni = (Integer) linha[13]; 
	    		String nomeMuni = (String) linha[14];
	    		if(idMuni != null && nomeMuni != null && !nomeMuni.equals("")){
	    			municipio = new Municipio();
	    			municipio.setId(idMuni);
	    			municipio.setNome(nomeMuni);
	    		}
	    		
	    		categoria.setId((Integer) linha[8]);
	    		categoria.setDescricaoAbreviada((String) linha[9]);
	    		
	    		bean.setValorDeAgua((BigDecimal) linha[10]);
	    		bean.setValorDeEsgoto((BigDecimal) linha[11]);
	    		bean.setValorTotal((BigDecimal) linha[12]);
	    		
	    		
	    		bean.setGerencia(gerencia);
	    		bean.setUnidade(unidade);
	    		bean.setLocalidade(localidade);
	    		bean.setCategoria(categoria);
	    		bean.setMunicipio(municipio);
	    		
	    		retorno.add(bean);
	    	}
	    	
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;		
	}
    
//	*******************************Caern	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Arthur Carvalho
	 * @date 02/03/09
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaema(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException{
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select  lcor.numeroCartao ,"//0
					   + " lcor.codigoTipo ,"//1
					   + " lcor.numeroFolha ,"//2
					   + " cntc.indicadorLinha ,"//3
					   + " cntc.prefixoContabil ,"//4
					   + " cntc.numeroConta ,"//5
					   + " cntc.numeroDigito ,"//6
					   + " cntc.numeroTerceiros ,"//7
					   + " lcor.codigoReferencia ,"//8
					   + " sum(lcti.valorLancamento) ,"//9
					   + " lcti.indicadorDebitoCredito ,"//10
					   + " lcor.numeroCartao2 ,"//11
					   + " lcor.numeroVersao ,"//12
					   + " loca.id, "//13
					   + " loca.codigoCentroCusto,"//14
					   + " cntc.indicadorCentroCusto, " //15
					   + " lcor.numeroHistoricoDebito, " //16
					   + " lcor.numeroHistoricoCredito " //17
					   + " from LancamentoContabilItem lcti " // lançamento contabil item
					   + " left join lcti.lancamentoContabil lcnb" //lançamento contábil
					   + " left join lcnb.localidade loca" //localidade
					   + " left join lcnb.lancamentoOrigem lcor" //lançamento origem
					   + " left join lcti.contaContabil cntc" //conta contabil
					   + " where lcnb.anoMes= :anoMes and lcor.id= :idLancamentoOrigem " 
					   + " group by loca.id, cntc.numeroConta, lcti.indicadorDebitoCredito,"
					   + " cntc.numeroConta ,cntc.numeroDigito ,cntc.numeroTerceiros ,"
					   + " lcor.numeroCartao, lcor.codigoTipo,"
					   + " lcor.numeroFolha, cntc.indicadorLinha ,cntc.prefixoContabil ,"
					   + " lcor.codigoReferencia ,"
					   + " lcor.numeroCartao2 ,lcor.numeroVersao ,loca.codigoCentroCusto, cntc.indicadorCentroCusto, "
					   + " lcor.numeroHistoricoDebito, lcor.numeroHistoricoCredito "
					   + " order by loca.id, cntc.numeroConta, lcti.indicadorDebitoCredito desc";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMes",new Integer(anoMes))
					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
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
	 * [UC0714] - Gerar Contas a Receber Contábil
	 * 
	 * Acumula CTCG_VLAGUA e CTCG_VLESGOTO da tabela CONTA_CATEGORIA 
	 * e da tabela CONTA com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
	 * 
	 * @author Vivianne Sousa
	 * @date 14/08/2009
	 * 
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(Integer idLocalidade)
			throws ErroRepositorioException {
		Collection<Object[]> retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();

		// cria a variável que vai conter o hql
		String consulta;

		try {
			// constroi o sql
			consulta = "SELECT " 
					 + " loca.greg_id as idGerencia, " 
					 + " loca.uneg_id as idUnidadeNegocio, " 
					 + " loca.loca_id as idLocalidade, " 
					 + " contaCat.catg_id as idCategoria, " 
					 + " sum(contaCat.ctcg_vlagua) as valorAgua, " 
					 + " sum(contaCat.ctcg_vlesgoto) as valorEsgoto " 
					 + " FROM cadastro.localidade loca " 
					 + " INNER JOIN faturamento.conta conta on loca.loca_id = conta.loca_id " 
					 + " INNER JOIN faturamento.conta_categoria contaCat on conta.cnta_id = contaCat.cnta_id " 
					 + " WHERE loca.loca_id = :idLocalidade " 
					 + " and conta.cnta_amreferenciabaixacontabil is not null "
					 + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, contacat.catg_id " 
					 + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

			// executa o sql
			retorno = session.createSQLQuery(consulta)
				.addScalar("idGerencia",Hibernate.INTEGER)
				.addScalar("idUnidadeNegocio",Hibernate.INTEGER)
				.addScalar("idLocalidade",Hibernate.INTEGER)
				.addScalar("idCategoria",Hibernate.INTEGER)
				.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
				.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
				.setInteger("idLocalidade",idLocalidade).list();

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
     * [UC0714] - Gerar Contas a Receber Contábil
     * 
     * Acumula DCCG_VLCATEGORIA da tabela DEBITO_COBRADO_CATEGORIA 
     * com DBCB_ID=DBCB_ID da tabela DEBITO_COBRADO e 
     * CNTA_ID=CNTA_ID da tabela CONTA 
     * com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
     *
     * @author Vivianne Sousa
     * @date 14/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitosCobradosCategoria( Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT "
				 + " loca.greg_id as idGerencia, " 
				 + " loca.uneg_id as idUnidadeNegocio, " 
				 + " loca.loca_id as idLocalidade, " 
				 + " debCobCat.catg_id as idCategoria, " 
				 + " sum(debCobCat.dccg_vlcategoria) as valorCategoria " 
				 + " FROM cadastro.localidade loca " 
				 + " INNER JOIN faturamento.conta conta on loca.loca_id = conta.loca_id " 
				 + " INNER JOIN faturamento.debito_cobrado debCob on debCob.cnta_id = conta.cnta_id " 
				 + " INNER JOIN faturamento.debito_cobrado_categoria debCobCat on debCobCat.dbcb_id = debCob.dbcb_id " 
				 + " WHERE loca.loca_id = :idLocalidade " 
				 + " and conta.cnta_amreferenciabaixacontabil is not null "
				 + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, debcobcat.catg_id "
				 + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta)
            		.addScalar("idGerencia",Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio",Hibernate.INTEGER)
					.addScalar("idLocalidade",Hibernate.INTEGER)
					.addScalar("idCategoria",Hibernate.INTEGER)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.setInteger("idLocalidade",idLocalidade)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     * 
     * Acumula negativamente CRCG_VLCATEGORIA 
     * da tabela CREDITO_REALIZADO_CATEGORIA 
     * com CRRZ_ID=CRRZ_ID da tabela CREDITO_REALIZADO 
     * e CNTA_ID=CNTA_ID da tabela CONTA 
     * com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
     *
     * @author Vivianne Sousa
     * @date 14/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoria(Integer idLocalidade)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        // cria uma sessão com o hibernate
        Session session = HibernateUtil.getSession();

        // cria a variável que vai conter o hql
        String consulta;

        try {
            // constroi o sql
            consulta = "SELECT "
				 + " loca.greg_id as idGerencia, " 
				 + " loca.uneg_id as idUnidadeNegocio, " 
				 + " loca.loca_id as idLocalidade, " 
				 + " credRealizadoCat.catg_id as idCategoria, "
				 + " sum(credRealizadoCat.crcg_vlcategoria) as valorCategoria " 
				 + " FROM cadastro.localidade loca " 
				 + " INNER JOIN faturamento.conta conta on loca.loca_id = conta.loca_id " 
				 + " INNER JOIN faturamento.credito_realizado credRealizado on credRealizado.cnta_id = conta.cnta_id " 
				 + " INNER JOIN faturamento.cred_realizado_catg credRealizadoCat on credRealizadoCat.crrz_id = credRealizado.crrz_id " 
				 + " WHERE loca.loca_id = :idLocalidade   "
				 + " and conta.cnta_amreferenciabaixacontabil is not null "
				 + " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, credrealizadocat.catg_id " 
				 + " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idCategoria ";

            // executa o sql
            retorno = session.createSQLQuery(consulta)
            		.addScalar("idGerencia",Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio",Hibernate.INTEGER)
					.addScalar("idLocalidade",Hibernate.INTEGER)
					.addScalar("idCategoria",Hibernate.INTEGER)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.setInteger("idLocalidade",idLocalidade)
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
     * [UC0714] - Gerar Contas a Receber Contábil
     * 
      * @author Vivianne Sousa
     * @date 17/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarValorPagamentoImovel(
    		Integer idLocalidade, Integer anoMesReferenciaArrecadacao)
            throws ErroRepositorioException {
        Collection<Object[]> retorno = null;

        Session session = HibernateUtil.getSession();
        String consulta;

        try {
            // constroi o sql
            consulta = " SELECT "
				 + " sum(pgmt.pgmt_vlpagamento) as valorPagamento, "
				 + " pgmt.imov_id as idImovel "
				 + " FROM arrecadacao.pagamento pgmt " 
				 + " WHERE " 
				 + " pgmt.loca_id= :idLocalidade " 
				 + " and pgmt.pgmt_amreferenciaarrecadacao < :anoMesReferenciaArrecadacao " 
				 + " and pgmt.pgst_idatual in (:idDoctoInexistente, :idValorNConfere, :idDoctoAContabilizar) "
				 + " GROUP BY pgmt.imov_id ";

            // executa o sql
            retorno = session.createSQLQuery(consulta)
					.addScalar("idImovel",Hibernate.INTEGER)
					.addScalar("valorPagamento",Hibernate.BIG_DECIMAL)
					.setInteger("idLocalidade",idLocalidade)
					.setInteger("anoMesReferenciaArrecadacao",anoMesReferenciaArrecadacao)
					.setInteger("idDoctoInexistente", PagamentoSituacao.DOCUMENTO_INEXISTENTE)
					.setInteger("idValorNConfere", PagamentoSituacao.VALOR_NAO_CONFERE)
					.setInteger("idDoctoAContabilizar", PagamentoSituacao.DOCUMENTO_A_CONTABILIZAR)
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
     * [UC0714] - Gerar Contas a Receber Contábil
	 * @author Vivianne Sousa
	 * @date 17/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarUnidadeNegocioEGerenciaDaLocalidade(
			Integer idLocalidade)throws ErroRepositorioException{
		
		Localidade retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";

		try {
				consulta = "select loca "
					+ "FROM Localidade loca "
					+ "INNER JOIN loca.unidadeNegocio unidade "
					+ "INNER JOIN loca.gerenciaRegional gerencia "
					+ "WHERE loca.id = :idLocalidade ";

			retorno = (Localidade)session.createQuery(consulta)
				.setInteger("idLocalidade",idLocalidade).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade - COSANPA
	 *
	 * @author Raphael Rossiter
	 * @date 17/11/2009
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCOSANPA(String idLancamentoOrigem, String anoMes) 
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select cntc.numeroConta, " // 0
					   + " loca.codigoCentroCusto, "// 1
					   + " lcti.indicadorDebitoCredito, "// 2
					   + " lcor.codigoReferencia, "// 3
					   + " lcti.valorLancamento, "// 4
					   + " cntc.prefixoContabil, "// 5
					   + " cntc.nomeConta, "// 6
					   + " lcti.dataLancamento, "// 7
					   + " lcti.descricaoHistorico, "// 8
					   + " lcti.codigoTerceiro, "// 9
					   + " cntc.indicadorCentroCusto "// 10
					   + " from LancamentoContabilItem lcti " // lançamento contabil item
					   + " left join lcti.lancamentoContabil lcnb" //lançamento contábil
					   + " left join lcnb.localidade loca" //localidade
					   + " left join lcnb.lancamentoOrigem lcor" //lançamento origem
					   + " left join lcti.contaContabil cntc" //conta contabil
					   + " where lcnb.anoMes= :anoMes and lcor.id= :idLancamentoOrigem"
					   + " order by loca.id";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMes",new Integer(anoMes))
					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
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
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberResumo(
			int anoMesReferenciaRecebimentos, Integer idLocalidade)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String delete;

		try {
			
			delete = "DELETE FROM DocumentosAReceberResumo drrs "
					+ " WHERE drrs.localidade.id = :idLocalidade "
					+ " and drrs.anoMesReferenciaRecebimentos = :anoMesReferencia ";
			
			session.createQuery(delete).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferencia",
					anoMesReferenciaRecebimentos).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter, Mariana Victor
	 * @date 10/03/2010, 29/03/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarContasAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN conta.iper_id IS NOT NULL THEN conta.iper_id ELSE 5 END) as idImovelPerfil, "//3 ImovelPerfil.NORMAL = 5
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE 4 END) as idEsferaPoder, "//4 EsferaPoder.PARTICULAR = 4
					
					+ " (CASE WHEN (select ctcg.catg_id from faturamento.conta_categoria ctcg "
					+ " where ctcg.cnta_id = conta.cnta_id "
					+ " and ROWNUM <= 1 order by ctcg.ctcg_qteconomia desc, ctcg.catg_id asc) IS NOT NULL "
					+ " THEN (select ctcg.catg_id from faturamento.conta_categoria ctcg "
					+ " where ctcg.cnta_id = conta.cnta_id "
					+ " and ROWNUM <= 1 order by ctcg.ctcg_qteconomia desc, ctcg.catg_id asc ) ELSE 1 END) as idCategoria, "//5 Categoria.RESIDENCIAL = 1
					
					+" 1 as idDocumentoTipo, "//6  DocumentoTipo.CONTA
					+ " conta.cnta_dtvencimentoconta as dataVencimento, "//7
					
					// VALORES DE ÁGUA, ESGOTO, DÉBITO E CRÉDITO
					+ " count(conta.cnta_id) as quantidadeDocumentos, "//8
					+ " sum(coalesce(conta.cnta_vlagua,0)) as valorAguaCategoria, "//9
					+ " sum(coalesce(conta.cnta_vlesgoto,0)) as valorEsgotoCategoria, "//10
					+ " sum(coalesce(conta.cnta_vldebitos,0)) as valorDebitoCategoria, "//11
					+ " sum(coalesce(conta.cnta_vlcreditos,0)) as valorCreditoCategoria, "//12
					
					// VALOR DOS IMPOSTOS
					+ " sum(coalesce(conta.cnta_vlimpostos,0)) as valorImpostos, "//13
					+ " fdrc.fdrc_id AS idFaixa " //14
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.conta conta on loca.loca_id = conta.loca_id "
					+ " LEFT JOIN cadastro.cliente_conta clct on conta.cnta_id = clct.cnta_id and clct.crtp_id = 3 " //ClienteRelacaoTipo.RESPONSAVEL = 3
					+ " LEFT JOIN cadastro.cliente resp on clct.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON (fdrc.fdrc_vlfaixainicial <= (coalesce(conta.cnta_vlagua,0) + coalesce(conta.cnta_vlesgoto,0) + coalesce(conta.cnta_vldebitos,0) - (coalesce(conta.cnta_vlcreditos,0) + coalesce(conta.cnta_vlimpostos,0))) "
					+ "   AND fdrc.fdrc_vlfaixafinal >= (coalesce(conta.cnta_vlagua,0) + coalesce(conta.cnta_vlesgoto,0) + coalesce(conta.cnta_vldebitos,0) - (coalesce(conta.cnta_vlcreditos,0) + coalesce(conta.cnta_vlimpostos,0)))) "
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( conta.cnta_amreferenciacontabil <= :anoMesReferenciaContabil and conta.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( conta.cnta_amreferenciacontabil > :anoMesReferenciaContabil and conta.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and ( conta.dcst_idanterior is null "
					+ " or ( conta.cnta_amreferenciaconta <= :anoMesReferenciaContabil and conta.dcst_idanterior not in (:situacaoRetificada , :situacaoIncluida) ) ) ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when conta.iper_id is not null then conta.iper_id else " +  ImovelPerfil.NORMAL.toString()+ "   end), (case when conta.iper_id is not null then conta.iper_id else " + ImovelPerfil.NORMAL.toString() +"  end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, dataVencimento, idFaixa "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, dataVencimento, idFaixa  ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					.addScalar("dataVencimento", Hibernate.DATE)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorAguaCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorEsgotoCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorDebitoCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorCreditoCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("valorImpostos", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter, Mariana Victor
	 * @date 10/03/2010, 29/03/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarGuiasPagamentoAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN imov.iper_id IS NOT NULL THEN imov.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
					
					+ " (CASE WHEN (select gpcg.catg_id from faturamento.guia_pagamento_categoria gpcg "
					+ " where gpcg.gpag_id = gpag.gpag_id "
					+ " and ROWNUM <= 1 order by gpcg.gpcg_qteconomia desc, gpcg.catg_id asc ) IS NOT NULL "
					+ " THEN (select gpcg.catg_id from faturamento.guia_pagamento_categoria gpcg "
					+ " where gpcg.gpag_id = gpag.gpag_id "
					+ " and ROWNUM <= 1 order by gpcg.gpcg_qteconomia desc, gpcg.catg_id asc ) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.GUIA_PAGAMENTO.toString() + " as idDocumentoTipo, "//6
					+ " gpag.gpag_dtvencimento as dataVencimento, "//7
					
					+ " count(gpag.gpag_id) as quantidadeDocumentos, "//8
					+ " sum(coalesce(gpag.gpag_vldebito,0)) as valorCategoria, "//9
					+ " fdrc.fdrc_id AS idFaixa " //10
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.guia_pagamento gpag on loca.loca_id = gpag.loca_id "
					+ " LEFT JOIN cadastro.imovel imov on imov.imov_id = gpag.imov_id "
					+ " LEFT JOIN cadastro.cliente_guia_pagamento clgp on gpag.gpag_id = clgp.gpag_id and clgp.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString()
					+ " LEFT JOIN cadastro.cliente resp on clgp.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON fdrc.fdrc_vlfaixainicial <= coalesce(gpag.gpag_vldebito,0) "
					+ "  AND fdrc.fdrc_vlfaixafinal >= coalesce(gpag.gpag_vldebito,0)"
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( gpag.gpag_amreferenciacontabil <= :anoMesReferenciaContabil and gpag.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( gpag.gpag_amreferenciacontabil > :anoMesReferenciaContabil and gpag.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and gpag.dcst_idanterior is null ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when imov.iper_id is not null then imov.iper_id else "+ ImovelPerfil.NORMAL.toString() + "   end), (case when imov.iper_id is not null then imov.iper_id else "+ ImovelPerfil.NORMAL.toString() +"  end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, dataVencimento, idFaixa  "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, dataVencimento, idFaixa  ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					.addScalar("dataVencimento", Hibernate.DATE)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter, Mariana Victor
	 * @date 10/03/2010, 29/03/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDebitosACobrarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN imov.iper_id IS NOT NULL THEN imov.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
				    + " (CASE WHEN (select dbcg.catg_id from faturamento.deb_a_cobrar_catg dbcg "
					+ " where dbcg.dbac_id = dbac.dbac_id "
					+ "  and ROWNUM <= 1 order by dbcg.dbcg_qteconomia desc, dbcg.catg_id asc) IS NOT NULL "
					+ " THEN (select dbcg.catg_id from faturamento.deb_a_cobrar_catg dbcg "
					+ " where dbcg.dbac_id = dbac.dbac_id "
					+ " and ROWNUM <= 1 order by dbcg.dbcg_qteconomia desc, dbcg.catg_id asc ) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.DEBITO_A_COBRAR.toString() + " as idDocumentoTipo, "//6
					
					+ " count(dbac.dbac_id) as quantidadeDocumentos, "//7
					//inicio alteracao
					+ "sum( ( dbac.dbac_vldebito - "
					+"		(trunc( (dbac.dbac_vldebito / dbac.dbac_nnprestacaodebito), 2 ) * (dbac.dbac_nnprestacaocobradas - ( "
					+"				CASE WHEN ( dbac.dbac_amreferenciaprestacao is not null and dbac.dbac_amreferenciaprestacao > "+ anoMesReferenciaContabil +"  )" 
					+"				THEN 1 	    ELSE 0  END ))))) "
					+ " as valorCategoria, "//8
					+ " fdrc.fdrc_id AS idFaixa " //9
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.debito_a_cobrar dbac on loca.loca_id = dbac.loca_id "
					+ " INNER JOIN cadastro.imovel imov on imov.imov_id = dbac.imov_id "
					+ " LEFT JOIN cadastro.cliente_imovel clim on imov.imov_id = clim.imov_id and clim.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString() + " and clim.clim_dtrelacaofim is null "
					+ " LEFT JOIN cadastro.cliente resp on clim.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON (fdrc.fdrc_vlfaixainicial <= (coalesce(dbac.dbac_vldebito,0) - (trunc(( coalesce(dbac.dbac_vldebito,0) /dbac.dbac_nnprestacaodebito ),2) " +
					" * dbac.dbac_nnprestacaocobradas)) "
					+ "   AND fdrc.fdrc_vlfaixafinal >= (coalesce(dbac.dbac_vldebito,0) - (trunc(( coalesce(dbac.dbac_vldebito,0) /dbac.dbac_nnprestacaodebito ),2) " +
					" * dbac.dbac_nnprestacaocobradas))) "	
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( dbac.dbac_amreferenciacontabil <= :anoMesReferenciaContabil and dbac.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( dbac.dbac_amreferenciacontabil > :anoMesReferenciaContabil and dbac.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and dbac.dcst_idanterior is null ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when imov.iper_id is not null then imov.iper_id else  " + ImovelPerfil.NORMAL.toString() +"  end), (case when imov.iper_id is not null then imov.iper_id else " + ImovelPerfil.NORMAL.toString() +"  end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, idFaixa  "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, idFaixa  ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter, Mariana Victor
	 * @date 10/03/2010, 29/03/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarCreditosARealizarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta;

		try {
			
			consulta = "SELECT loca.greg_id as idGerencia, loca.uneg_id as idUnidadeNegocio, "//0, 1
					+ " loca.loca_id as idLocalidade, "//2
					+ " (CASE WHEN imov.iper_id IS NOT NULL THEN imov.iper_id ELSE "
					+ ImovelPerfil.NORMAL.toString() + " END) as idImovelPerfil, "//3
					+ " (CASE WHEN cltp.epod_id IS NOT NULL THEN cltp.epod_id ELSE "
					+ EsferaPoder.PARTICULAR.toString() + " END) as idEsferaPoder, "//4
					
					+ " (CASE WHEN (select cacg.catg_id from faturamento.cred_a_realiz_catg cacg "
					+ " where cacg.crar_id = crar.crar_id "
					+ " and ROWNUM <= 1 order by cacg.cacg_qteconomia desc, cacg.catg_id asc) IS NOT NULL "
					+ " THEN (select cacg.catg_id from faturamento.cred_a_realiz_catg cacg "
					+ " where cacg.crar_id = crar.crar_id "
					+ " and ROWNUM <= 1 order by cacg.cacg_qteconomia desc, cacg.catg_id asc ) ELSE "
					+ Categoria.RESIDENCIAL.toString() + " END) as idCategoria, "//5
					
					+ DocumentoTipo.CREDITO_A_REALIZAR.toString() + " as idDocumentoTipo, "//6
					
					+ " count(crar.crar_id) as quantidadeDocumentos, "//7 
					+ " sum(coalesce(crar.crar_vlcredito,0) - (trunc((coalesce(crar.crar_vlcredito,0) / crar.crar_nnprestacaocredito),2) "
					+ " * (crar.crar_nnprestacaorealizadas - (CASE WHEN ( crar.crar_amreferenciaprestacao is not null and crar.crar_amreferenciaprestacao >= sp.parm_amreferenciaarrecadacao) THEN 1 ELSE 0 END))) + coalesce(crar.crar_vlresidualmesanterior,0)) as valorCategoria, "//8
					+ " fdrc.fdrc_id AS idFaixa " //9
					
					+ " FROM cadastro.localidade loca "
					+ " INNER JOIN faturamento.credito_a_realizar crar on loca.loca_id = crar.loca_id "
					+ " INNER JOIN cadastro.imovel imov on imov.imov_id = crar.imov_id "
					+ " LEFT JOIN cadastro.cliente_imovel clim on imov.imov_id = clim.imov_id and clim.crtp_id = "
					+ ClienteRelacaoTipo.RESPONSAVEL.toString() + " and clim.clim_dtrelacaofim is null "
					+ " LEFT JOIN cadastro.cliente resp on clim.clie_id = resp.clie_id "
					+ " LEFT JOIN cadastro.cliente_tipo cltp on resp.cltp_id = cltp.cltp_id "
					+ " LEFT JOIN financeiro.faixa_docs_a_receber fdrc ON (fdrc.fdrc_vlfaixainicial <= ( ( coalesce(crar.crar_vlcredito,0) - trunc((coalesce(crar.crar_vlcredito,0) /  crar.crar_nnprestacaocredito),2) " 
					+ " * crar.crar_nnprestacaorealizadas ) + coalesce(crar.crar_vlresidualmesanterior,0) ) "
					+ "   AND fdrc.fdrc_vlfaixafinal >= ( ( coalesce(crar.crar_vlcredito,0) - trunc((coalesce(crar.crar_vlcredito,0) /  crar.crar_nnprestacaocredito),2) " 
					+ " * crar.crar_nnprestacaorealizadas ) + coalesce(crar.crar_vlresidualmesanterior,0) )) "
					+ " INNER JOIN cadastro.sistema_parametros sp on 1=1 "
					
					+ " WHERE loca.loca_id = :idLocalidade "
					+ " and ( ( crar.crar_amreferenciacontabil <= :anoMesReferenciaContabil and crar.dcst_idatual "
					+ " in ( :situacaoNormal, :situacaoIncluida, :situacaoRetificada ) ) "
					+ " or ( crar.crar_amreferenciacontabil > :anoMesReferenciaContabil and crar.dcst_idatual "
					+ " in ( :situacaoCancelada, :situacaoCanceladaPorRetificacao, "
					+ " :situacaoParcelada, :situacaoDebitoPrescrito ) "
					+ " and crar.dcst_idanterior is null ) ) "
					
					+ " GROUP BY loca.greg_id, loca.uneg_id, loca.loca_id, (case when imov.iper_id is not null then imov.iper_id else " + ImovelPerfil.NORMAL.toString() +"   end), (case when imov.iper_id is not null then imov.iper_id else  " + ImovelPerfil.NORMAL.toString() +"   end), "
					+ " (case when cltp.epod_id is not null then cltp.epod_id else  "+ EsferaPoder.PARTICULAR.toString()+"   end), idCategoria, idDocumentoTipo, idFaixa  "
					
					+ " ORDER BY idGerencia, idUnidadeNegocio, idLocalidade, idImovelPerfil, idImovelPerfil, "
					+ " idEsferaPoder, idCategoria, idDocumentoTipo, idFaixa  ";

			retorno = session.createSQLQuery(consulta)
					.addScalar("idGerencia", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idImovelPerfil", Hibernate.INTEGER)
					.addScalar("idEsferaPoder", Hibernate.INTEGER)
					.addScalar("idCategoria", Hibernate.INTEGER)
					.addScalar("idDocumentoTipo", Hibernate.INTEGER)
					
					.addScalar("quantidadeDocumentos", Hibernate.INTEGER)
					.addScalar("valorCategoria", Hibernate.BIG_DECIMAL)
					.addScalar("idFaixa", Hibernate.INTEGER)
					
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferenciaContabil", anoMesReferenciaContabil)
					
					.setInteger("situacaoNormal",DebitoCreditoSituacao.NORMAL)
					.setInteger("situacaoIncluida", DebitoCreditoSituacao.INCLUIDA)
					.setInteger("situacaoRetificada", DebitoCreditoSituacao.RETIFICADA)
					
					.setInteger("situacaoCancelada", DebitoCreditoSituacao.CANCELADA)
					.setInteger("situacaoCanceladaPorRetificacao", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("situacaoParcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("situacaoDebitoPrescrito", DebitoCreditoSituacao.DEBITO_PRESCRITO).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "    
						+ " localidade.greg_id as gregId,"  //0
						+ " localidade.uneg_id as unegId, " //1
						+ " pag.loca_id as locaId,  "//2
						+ " categoria.catg_id as catgId,"//3  
						+ " aviso.arrc_id as arrecadadorId,"//4
						+ " agencia.bnco_id as banco,"//5
						+ " aviso.ctbc_id as contaBancariaId,"//6
						+ " aviso.avbc_dtrealizada as dtRealizada," //7
						+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra, "
						+ " sum(contaCategoria.ctcg_vlagua) as somaagua,"//8  
						+ " sum(contaCategoria.ctcg_vlesgoto) as somaesgoto"//9  
					
						+ " from arrecadacao.pagamento pag"  
					
						+ " inner join faturamento.conta conta on conta.cnta_id = pag.cnta_id"  
						+ " inner join faturamento.conta_categoria contaCategoria on contaCategoria.cnta_id = conta.cnta_id"  
						+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"  
						+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"  
						+ " inner join cadastro.categoria categoria on categoria.catg_id = contaCategoria.catg_id"  
						
						+ " inner join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
						+ " inner join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"	
						+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
						+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
						
						+ " where"   
						+ " (pgst_idatual = 0"   
						+ " or ( "
						+ "	pgst_idatual = 10 "
						+ "	and pgmt_vlpagamento = "
						+ "	(cnta_vlagua+cnta_vlesgoto+ "
						+ "	cnta_vldebitos-cnta_vlcreditos- "
						+ "	cnta_vlimpostos))) "  
						+ " and not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "  
						+ " and avbc_dtrealizada between :dataInicial and :dataFinal"
						
						+ " group by"   
						+ " localidade.greg_id,"  
						+ " localidade.uneg_id, " 
						+ " pag.loca_id,  "
						+ " categoria.catg_id,"
						+ " aviso.arrc_id,"
						+ " agencia.bnco_id,"
						+ " aviso.ctbc_id,"
						+ " aviso.avbc_dtrealizada,"
						+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
						
						+ " order by"  
						+ " localidade.greg_id,"  
						+ " localidade.uneg_id,"  
						+ " pag.loca_id,"  
						+ " categoria.catg_id," 
						+ " aviso.arrc_id,"
						+ " agencia.bnco_id,"
						+ " aviso.ctbc_id,"
						+ " aviso.avbc_dtrealizada,"
						+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gregId", Hibernate.INTEGER)
					.addScalar("unegId", Hibernate.INTEGER)
					.addScalar("locaId", Hibernate.INTEGER)
					.addScalar("catgId", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.addScalar("somaagua", Hibernate.BIG_DECIMAL)
					.addScalar("somaesgoto", Hibernate.BIG_DECIMAL)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param anoMesReferenciaArrecadacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisosBancariosParaGerarLancamentosContabeis(Integer anoMesReferenciaArrecadacao) 
	throws ErroRepositorioException{
	
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "SELECT avbc " 
					   + " FROM AvisoBancario avbc " 
					   + " INNER JOIN avbc.arrecadador arrc "
					   + " INNER JOIN FETCH avbc.contaBancaria ctbc "
					   + " LEFT JOIN FETCH avbc.arrecadacaoForma arfm "
					   + " WHERE avbc.anoMesReferenciaArrecadacao <= :anoMesReferenciaArrecadacao "
					   + " AND avbc.valorContabilizado <> avbc.valorRealizado "
					   + " AND (avbc.valorRealizado IS NOT NULL AND avbc.valorRealizado > :zero) "
					   + " ORDER BY arrc.id, avbc.dataRealizada ";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMesReferenciaArrecadacao", anoMesReferenciaArrecadacao.intValue())
					.setBigDecimal("zero", new BigDecimal("0.00")).list();
			
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
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param nomeConta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil pesquisarContaContabilPorNomeConta(String nomeConta) 
	throws ErroRepositorioException{
	
		ContaContabil retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "SELECT cnct " 
					   + " FROM ContaContabil cnct " 
					   + " WHERE cnct.nomeConta = :nomeConta ";
			
			retorno = (ContaContabil) session.createQuery(consulta)
					.setString("nomeConta", nomeConta).setMaxResults(1).uniqueResult();
					
			
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
	 * [UC0992] Gerar Lançamentos Contábeis dos Avisos Bancários 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param idAvisoBancario
	 * @param valorContabilizado
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorContabilizado(Integer idAvisoBancario, BigDecimal valorContabilizado)
			throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();

		String atualizarValorContabilizado;

		try {

			atualizarValorContabilizado = "UPDATE AvisoBancario "
					+ "SET avbc_vlcontabilizado = :valorContabilizado, avbc_tmultimaalteracao = :ultimaAlteracao "
					+ "WHERE avbc_id = :idAvisoBancario ";

			session.createQuery(atualizarValorContabilizado)
				.setInteger("idAvisoBancario", idAvisoBancario)
				.setBigDecimal("valorContabilizado", valorContabilizado)
				.setTimestamp("ultimaAlteracao", new Date()).executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "       
					+ " localidade.greg_id as gregId, "  
					+ " localidade.uneg_id as unegId,  " 
					+ " pag.loca_id as locaId,  "
					+ " creditoCategoria.catg_id as categoriaId,"
					+ " aviso.arrc_id as arrecadadorId,"  
					+ " agencia.bnco_id as banco,"
					+ " aviso.ctbc_id as contaBancariaId," 
					
					+ " aviso.avbc_dtrealizada as dtRealizada, "
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra, "
					+ " sum(creditoCategoria.crcg_vlcategoria) as somacreditos"
					
					+ " from arrecadacao.pagamento pag"
					
					+ " inner join faturamento.conta conta on conta.cnta_id = pag.cnta_id"    
					+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"    
					 
					+ " left join faturamento.credito_realizado credito on credito.cnta_id = pag.cnta_id"    
					+ " left join faturamento.cred_realizado_catg creditoCategoria on creditoCategoria.crrz_id = credito.crrz_id"    
					
					+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"  
					+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					
					+ " where "
					+ " (pgst_idatual = 0 or "     
					+ " ( "
					+ "	pgst_idatual = 10 "
					+ "	and pgmt_vlpagamento = "
					+ "	(cnta_vlagua+cnta_vlesgoto + " 
					+ "	cnta_vldebitos-cnta_vlcreditos - "
					+ "	cnta_vlimpostos))) "    
					+ " and not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) " 
					+ " and creditoCategoria.crcg_vlcategoria > 0"
					+ " and avbc_dtrealizada between :dataInicial and :dataFinal"
					
					+ " group by"
					+ " localidade.greg_id,"    
					+ " localidade.uneg_id,"    
					+ " pag.loca_id," 
					+ " creditocategoria.catg_id,"
					+ " aviso.arrc_id,"  
					+ " agencia.bnco_id,"  
					+ " aviso.ctbc_id,"  
					+ " aviso.avbc_dtrealizada,"
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
					
					+ " order by"    
					+ " localidade.greg_id,"    
					+ " localidade.uneg_id,"    
					+ " pag.loca_id,"  
					+ " categoriaId,"
					+ " aviso.arrc_id,"  
					+ " agencia.bnco_id,"  
					+ " aviso.ctbc_id,"  
					+ " aviso.avbc_dtrealizada,"
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gregId", Hibernate.INTEGER)
					.addScalar("unegId", Hibernate.INTEGER)
					.addScalar("locaId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.addScalar("somacreditos", Hibernate.BIG_DECIMAL)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaServico(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "       
						+ " localidade.greg_id as gregId,"   
						+ " localidade.uneg_id as unegId, "  
						+ " pag.loca_id as locaId,"
						+ " debitoCategoria.catg_id as categoriaId, "
						+ " aviso.arrc_id as arrecadadorId,"  
						+ " agencia.bnco_id as banco,"
						+ " aviso.ctbc_id as contaBancariaId," 
						+ " aviso.avbc_dtrealizada as dtRealizada, "
						+ " sum(debitoCategoria.dccg_vlcategoria) as somacreditos, "
						+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil "
						
						+ " from arrecadacao.pagamento pag"
						
						+ " inner join faturamento.conta conta on conta.cnta_id = pag.cnta_id"    
						+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
						+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"    
						+ " left join faturamento.debito_cobrado debito on debito.cnta_id = pag.cnta_id"    
						+ " left join faturamento.debito_cobrado_categoria debitoCategoria on debitoCategoria.dbcb_id = debito.dbcb_id"    
						
						+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"  
						+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
						+ " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = debito.dbtp_id "
						+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
						+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
						
						+ " where"
						+ " (pgst_idatual = 0"     
						+ " or ( "
						+ "	pgst_idatual = 10 "
						+ "	and pgmt_vlpagamento = "
						+ "	(cnta_vlagua+cnta_vlesgoto + "
						+ "	cnta_vldebitos-cnta_vlcreditos - "
						+ "	cnta_vlimpostos))) "
						+ " and not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "
						+ " and debitoTipo.dbtp_icdividaativa = " + ConstantesSistema.NAO + " "    
						+ " and avbc_dtrealizada between :dataInicial and :dataFinal"
						
						+ " group by"
						+ " localidade.greg_id,"    
						+ " localidade.uneg_id,"   
						+ " pag.loca_id,"
						+ " debitocategoria.catg_id,"  
						+ " aviso.arrc_id,"  
						+ " agencia.bnco_id,"  
						+ " aviso.ctbc_id,"  
						+ " aviso.avbc_dtrealizada, "
						+ " contaContabil "
						
						+ " order by"    
						+ " localidade.greg_id,"    
						+ " localidade.uneg_id,"    
						+ " pag.loca_id,"    
						+ " categoriaId,"   
						+ " aviso.arrc_id,"  
						+ " agencia.bnco_id,"  
						+ " aviso.ctbc_id,"  
						+ " aviso.avbc_dtrealizada, "
						+ " contaContabil ";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gregId", Hibernate.INTEGER)
					.addScalar("unegId", Hibernate.INTEGER)
					.addScalar("locaId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("somacreditos", Hibernate.BIG_DECIMAL)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarImpostoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select " 
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " pag.imov_id as imovelId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " localidade.loca_nmlocalidade as localidadeNome,"
					  + " sum(conta.cnta_vlimpostos) as somaimposto,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra"
					  
					  
					  + " from arrecadacao.pagamento pag"
					
					  + " inner join faturamento.conta conta on conta.cnta_id = pag.cnta_id"
					  + " inner join cadastro.imovel imovel on imovel.imov_id = pag.imov_id"
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "

					  + " where "
					  + " not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "
					  + " and conta.cnta_vlimpostos > 0"
					  + " and (pgst_idatual = 0"     
					  + " or ( pgst_idatual = 10 and pgmt_vlpagamento = "
					  + " (cnta_vlagua + cnta_vlesgoto + cnta_vldebitos - cnta_vlcreditos - cnta_vlimpostos) ))" 
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " pag.imov_id,"
					  + " imovel.imov_idcategoriaprincipal,"
					  + " aviso.avbc_dtrealizada,"
					  + " localidade.loca_nmlocalidade,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
					  + " order by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " pag.imov_id,"
					  + " imovel.imov_idcategoriaprincipal,"
					  + " aviso.avbc_dtrealizada,"
					  + " localidade.loca_nmlocalidade,"
					  + " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("localidadeNome", Hibernate.STRING)
					.addScalar("somaimposto", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco" , Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDividaAtivaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select" 
					+ " localidade.greg_id as gerenciaId,"
					+ " localidade.uneg_id as unidadeId,"
					+ " localidade.loca_id as localidadeId,"
					+ " pag.imov_id as imovelId,"
					+ " aviso.avbc_dtrealizada as dtRealizada,"
					+ " sum("
					+ "		CASE WHEN (dad.dade_id is not null) THEN "
					+ "			pgmt_vlpagamento "
					+ "		ELSE "
					+ "			(SELECT sum(dc.dbcb_vlprestacao) "
					+ "			FROM faturamento.debito_cobrado dc "
					+ "			INNER JOIN faturamento.debito_tipo dbtp on dbtp.dbtp_id = dc.dbtp_id "
					+ "			WHERE dc.cnta_id = conta.cnta_id and dbtp.dbtp_icdividaativa = " + ConstantesSistema.SIM + ") "
					+ "		END "
					+ ") as somaativa, "
					+ " aviso.ctbc_id as contaBancariaId,"
					+ " agencia.bnco_id as banco,"
					+ " aviso.arrc_id as arrecadadorId"
					  
					+ " from arrecadacao.pagamento pag"
				
					+ " inner join faturamento.conta conta on conta.cnta_id = pag.cnta_id"
					+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					
					+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					+ " left join cobranca.divida_ativa_debito dad on dad.cnta_id = conta.cnta_id and dad.dade_dtretirada is null "
				
					+ " where "
					+ " (dad.dade_id is not null or "
					+ " exists(SELECT dbcb.cnta_id FROM faturamento.debito_cobrado dbcb INNER JOIN faturamento.debito_tipo dbtp on dbtp.dbtp_id = dbcb.dbtp_id WHERE dbcb.cnta_id = conta.cnta_id and dbtp.dbtp_icdividaativa = " + ConstantesSistema.SIM + ")) "
					+ " and (pgst_idatual = 0  "   
					+ "		 or ( pgst_idatual = 10 and pgmt_vlpagamento = "
					+ "		 (cnta_vlagua + cnta_vlesgoto + cnta_vldebitos - cnta_vlcreditos - cnta_vlimpostos) )) "
					+ " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					+ " group by" 
					+ " localidade.greg_id,"
					+ " localidade.uneg_id,"
					+ " localidade.loca_id,"
					+ " pag.imov_id,"
					+ " aviso.avbc_dtrealizada,"
					+ " aviso.ctbc_id,"
					+ " agencia.bnco_id,"
					+ " aviso.arrc_id"
					+ " order by" 
					+ " localidade.greg_id,"
					+ " localidade.uneg_id,"
					+ " localidade.loca_id,"
					+ " pag.imov_id,"
					+ " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("somaativa", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDividaAtivaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select " 
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " pag.imov_id as imovelId,"
					  + " aviso.avbc_dtrealizada as dtRealizada,"
					  + " sum("
					  + "		CASE WHEN (dad.dade_id is not null) THEN "
					  + "			pghi_vlpagamento "
					  + "		ELSE "
					  + "			(SELECT sum(dc.dbhi_vlprestacao) "
					  + "			FROM faturamento.debito_cobrado_historico dc "
					  + "			INNER JOIN faturamento.debito_tipo dbtp on dbtp.dbtp_id = dc.dbtp_id "
					  + "			WHERE dc.cnta_id = conta.cnta_id and dbtp.dbtp_icdividaativa = " + ConstantesSistema.SIM + ") "
					  + "		END "
					  + ") as somaativa, "

					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId"
					  
					  + " from arrecadacao.pagamento_historico pag"
					
					  + " inner join faturamento.conta_historico conta on conta.cnta_id = pag.cnta_id"
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  + " left join cobranca.divida_ativa_debito dad on dad.cnta_id = conta.cnta_id and dad.dade_dtretirada is null "
					
					  + " where "
					  + " (dad.dade_id is not null or "
					  + " exists(SELECT dbcb.cnta_id FROM faturamento.debito_cobrado_historico dbcb INNER JOIN faturamento.debito_tipo dbtp on dbtp.dbtp_id = dbcb.dbtp_id WHERE dbcb.cnta_id = conta.cnta_id and dbtp.dbtp_icdividaativa = " + ConstantesSistema.SIM + ")) "
					  + " and (pgst_idatual = 0  "   
					  + "		 or ( pgst_idatual = 10 and pghi_vlpagamento = "
					  + "		 (cnhi_vlagua + cnhi_vlesgoto + cnhi_vldebitos - cnhi_vlcreditos - cnhi_vlimpostos) )) "
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id," 
					  + " pag.imov_id,"
					  + " aviso.avbc_dtrealizada, "
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id"
					  + " order by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("somaativa", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select"    
					+ " localidade.greg_id as gregId," //0 
					+ " localidade.uneg_id as unegId, " //1
					+ " pag.loca_id as locaId,"//2
					+ " contaCategoria.catg_id as catgId,"//3  
					+ " aviso.arrc_id as arrecadadorId,"//4
					+ " aviso.ctbc_id as contaBancariaId,"//5
					+ " agencia.bnco_id as banco,"//6
					+ " aviso.avbc_dtrealizada as dtRealizada,"  //7
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra, " //8
					+ " sum(contaCategoria.ctch_vlagua) as somaagua,"  //9
					+ " sum(contaCategoria.ctch_vlesgoto) as somaesgoto"  //10
						
					
					+ " from arrecadacao.pagamento_historico pag"  
					
					+ " inner join faturamento.conta_historico conta on conta.cnta_id = pag.cnta_id"  
					+ " inner join faturamento.conta_catg_hist contaCategoria on contaCategoria.cnta_id = conta.cnta_id"  
					+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"  
					+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"  
					
					
					+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					
					+ " where "
					+ " (pgst_idatual = 0"   
					+ " or ( "
					+ "	pgst_idatual = 10 "
					+ "	and pghi_vlpagamento = "
					+ "	(cnhi_vlagua+cnhi_vlesgoto + "
					+ "	cnhi_vldebitos-cnhi_vlcreditos - "
					+ "	cnhi_vlimpostos ))) "  
					+ " and not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "  
					+ " and avbc_dtrealizada between :dataInicial and :dataFinal" 
					+ " group by"
					+ " localidade.greg_id,"  
					+ " localidade.uneg_id,"  
					+ " pag.loca_id,"  
					+ " contacategoria.catg_id,"  
					+ " aviso.arrc_id,"
					+ " agencia.bnco_id,"
					+ " aviso.ctbc_id,"
					+ " aviso.avbc_dtrealizada,"
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
						
					+ " order by"  
					+ " localidade.greg_id,"  
					+ " localidade.uneg_id,"  
					+ " pag.loca_id,"  
					+ " catgId,"  
					+ " aviso.arrc_id,"
					+ " agencia.bnco_id,"
					+ " aviso.ctbc_id,"
					+ " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gregId", Hibernate.INTEGER)
					.addScalar("unegId", Hibernate.INTEGER)
					.addScalar("locaId", Hibernate.INTEGER)
					.addScalar("catgId", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.addScalar("somaagua", Hibernate.BIG_DECIMAL)
					.addScalar("somaesgoto", Hibernate.BIG_DECIMAL)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "       
					+ " localidade.greg_id as gregId, "  
					+ " localidade.uneg_id as unegId,  " 
					+ " pag.loca_id as locaId,  "
					+ " creditoCategoria.catg_id as categoriaId,"
					+ " aviso.arrc_id as arrecadadorId,"  
					+ " agencia.bnco_id as banco,"
					+ " aviso.ctbc_id as contaBancariaId," 
					
					+ " aviso.avbc_dtrealizada as dtRealizada, "
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra, "
					+ " sum(creditoCategoria.crch_vlcategoria) as somacreditos"
					
					+ " from arrecadacao.pagamento_historico pag"
					
					+ " inner join faturamento.conta_historico conta on conta.cnta_id = pag.cnta_id"    
					+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"    
					 
					+ " left join faturamento.cred_realizado_hist credito on credito.cnta_id = pag.cnta_id"    
					+ " left join faturamento.cred_realizado_catg_hist creditoCategoria on creditoCategoria.crhi_id = credito.crhi_id"    
					
					+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"  
					+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					
					+ " where "
					+ " (pgst_idatual = 0"     
					+ " or ( "
					+ "	pgst_idatual = 10 "
					+ "	and pghi_vlpagamento = "
					+ "	(cnhi_vlagua+cnhi_vlesgoto + "
					+ "	cnhi_vldebitos-cnhi_vlcreditos - "
					+ "	cnhi_vlimpostos )))"    
					+ " and not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "    
					+ " and creditoCategoria.crch_vlcategoria > 0"
					+ " and avbc_dtrealizada between :dataInicial and :dataFinal"
					
					+ " group by"
					+ " localidade.greg_id,"    
					+ " localidade.uneg_id,"    
					+ " pag.loca_id," 
					+ " creditocategoria.catg_id,"
					+ " aviso.arrc_id,"  
					+ " agencia.bnco_id,"  
					+ " aviso.ctbc_id,"  
					+ " aviso.avbc_dtrealizada,"    
					+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
					
					+ " order by"    
					+ " localidade.greg_id,"    
					+ " localidade.uneg_id,"    
					+ " pag.loca_id,"  
					+ " categoriaId,"
					+ " aviso.arrc_id,"  
					+ " agencia.bnco_id,"  
					+ " aviso.ctbc_id,"  
					+ " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gregId", Hibernate.INTEGER)
					.addScalar("unegId", Hibernate.INTEGER)
					.addScalar("locaId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.addScalar("somacreditos", Hibernate.BIG_DECIMAL)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoContaServico(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "       
						+ " localidade.greg_id as gregId,"   
						+ " localidade.uneg_id as unegId, "  
						+ " pag.loca_id as locaId,"
						+ " debitoCategoria.catg_id as categoriaId, "
						+ " aviso.arrc_id as arrecadadorId,"  
						+ " agencia.bnco_id as banco,"
						+ " aviso.ctbc_id as contaBancariaId," 
						+ " aviso.avbc_dtrealizada as dtRealizada, "
						+ " sum(debitoCategoria.dcch_vlcategoria) as somacreditos, "
						+ " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil "
						
						+ " from arrecadacao.pagamento_historico pag"
						
						+ " inner join faturamento.conta_historico conta on conta.cnta_id = pag.cnta_id"    
						+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
						+ " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"    
						+ " left join faturamento.debito_cobrado_historico debito on debito.cnta_id = pag.cnta_id"    
						+ " left join faturamento.deb_cobrado_catg_hist debitoCategoria on debitoCategoria.dbhi_id = debito.dbhi_id"    
						 
						+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"  
						+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
						+ " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = debito.dbtp_id "
						+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
						+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
						
						+ " where"
						+ " (pgst_idatual = 0"     
						+ " or ( "
						+ "	pgst_idatual = 10 "
						+ "	and pghi_vlpagamento = "
						+ "	(cnhi_vlagua+cnhi_vlesgoto + "
						+ "	cnhi_vldebitos-cnhi_vlcreditos - "
						+ "	cnhi_vlimpostos))) "    
						+ " and not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "
						+ " and debitoTipo.dbtp_icdividaativa = " + ConstantesSistema.NAO + " "     
						+ " and avbc_dtrealizada between :dataInicial and :dataFinal"
						
						+ " group by"
						+ " localidade.greg_id,"    
						+ " localidade.uneg_id,"   
						+ " pag.loca_id,"
						+ " debitocategoria.catg_id,"  
						+ " aviso.arrc_id,"  
						+ " agencia.bnco_id,"  
						+ " aviso.ctbc_id,"  
						+ " aviso.avbc_dtrealizada, "    
						+ " contaContabil "
						+ " order by"    
						+ " localidade.greg_id,"    
						+ " localidade.uneg_id,"    
						+ " pag.loca_id,"    
						+ " categoriaId,"   
						+ " aviso.arrc_id,"  
						+ " agencia.bnco_id,"  
						+ " aviso.ctbc_id,"  
						+ " aviso.avbc_dtrealizada, "
						+ " contaContabil ";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gregId", Hibernate.INTEGER)
					.addScalar("unegId", Hibernate.INTEGER)
					.addScalar("locaId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("dtRealizada", Hibernate.TIMESTAMP)
					.addScalar("somacreditos", Hibernate.BIG_DECIMAL)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarImpostoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select " 
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " pag.imov_id as imovelId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " localidade.loca_nmlocalidade as localidadeNome,"
					  + " sum(conta.cnhi_vlimpostos) as somaimposto,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra"

					  
					  + " from arrecadacao.pagamento_historico pag"
					
					  + " inner join faturamento.conta_historico conta on conta.cnta_id = pag.cnta_id"
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					
					  + " where "
					  + " not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) "
					  + " and conta.cnhi_vlimpostos > 0"
					  + " and(pgst_idatual = 0"   
					  + " or ( "
					  +	"	pgst_idatual = 10 "
					  +	"	and pghi_vlpagamento = "
					  + "	(cnhi_vlagua+cnhi_vlesgoto+ "
					  + "	cnhi_vldebitos-cnhi_vlcreditos- "
					  +	"	cnhi_vlimpostos))) "
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " pag.imov_id,"
					  + " aviso.avbc_dtrealizada,"
					  + " localidade.loca_nmlocalidade,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
					  + " order by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " pag.imov_id,"
					  + " aviso.avbc_dtrealizada,"
					  + " localidade.loca_nmlocalidade";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("localidadeNome", Hibernate.STRING)
					.addScalar("somaimposto", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco" , Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoGuiaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " CASE WHEN (guiaPagamentoItemCategoria.gpai_id is not null) THEN guiaPagamentoItemCategoria.catg_id ELSE guiaPagamentoCategoria.catg_id END as categoriaId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " sum(CASE WHEN (guiaPagamentoItemCategoria.gpai_id is not null) THEN guiaPagamentoItemCategoria.gpic_vlcategoria ELSE guiaPagamentoCategoria.gpcg_vlcategoria END) as somapagamentoguia,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  //+ " CASE WHEN (guiaPagamentoItemCategoria.gpai_id is not null and debitoTipoItem.dbtp_icdividaativa = 1) THEN " + ContaContabil.RECEITA_DIVIDA_ATIVA + " WHEN ( (clie.cltp_id is not null or clieGuia.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieGuia.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil "
					  
					  + " CASE \n"
					  + "   WHEN (guiaPagamentoItemCategoria.gpai_id is not null and debitoTipoItem.dbtp_icdividaativa = 1) THEN \n" 
					  + "     " + ContaContabil.RECEITA_DIVIDA_ATIVA +  
					    "   WHEN ( (clie.cltp_id is not null or clieGuia.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieGuia.cltp_id = " + ClienteTipo.RECEITA_INTRA + " ) ) THEN \n"
					  + "     CASE \n"
					  + "       WHEN ( debitoTipoItem.dbtp_id is not null )THEN \n"
					  + "         debitoTipoItem.cnct_idintra \n"
					  + "       ELSE \n"
					  + "         debitoTipo.cnct_idintra \n"         
					  + "       END \n"
					  + "   ELSE \n" 
					  + "     CASE \n" 
					  + "       WHEN ( debitoTipoItem.dbtp_id is not null )THEN \n"
					  + "         debitoTipoItem.cnct_id \n"
					  + "       ELSE \n"
					  + "         debitoTipo.cnct_id \n" 
					  + "     END \n"
					  + "   END as contaContabil \n"
					  + " from arrecadacao.pagamento pag "
					
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  + " inner join faturamento.guia_pagamento guiaPagamento on guiaPagamento.gpag_id = pag.gpag_id"
					  + " inner join faturamento.guia_pagamento_categoria guiaPagamentoCategoria on guiaPagamentoCategoria.gpag_id = pag.gpag_id"
					  + " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = guiaPagamento.dbtp_id"
					  
					  + " left join faturamento.guia_pagamento_item guiaPagamentoItem on guiaPagamentoItem.gpag_id = guiaPagamento.gpag_id "
					  + " left join faturamento.guia_pagamento_item_catg guiaPagamentoItemCategoria on guiaPagamentoItemCategoria.gpai_id = guiaPagamentoItem.gpai_id and guiaPagamentoItemCategoria.catg_id = guiaPagamentoCategoria.catg_id "
					  + " left join faturamento.debito_tipo debitoTipoItem on debitoTipoItem.dbtp_id = guiaPagamentoItem.dbtp_id"
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = guiaPagamento.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					  + " left join cadastro.cliente clieGuia on clieGuia.clie_id = guiaPagamento.clie_id "
					
					  + " where "
					  + " (pag.pgst_idatual = 0 or pag.pgst_idatual = 10)"
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " categoriaId,"
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " contaContabil"
					  + " order by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " categoriaId,"
					  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamentoguia", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " CASE WHEN (guiaPagamentoItemCategoria.gpih_id is not null) THEN guiaPagamentoItemCategoria.catg_id ELSE guiaPagamentoCategoria.catg_id END as categoriaId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " sum(CASE WHEN (guiaPagamentoItemCategoria.gpih_id is not null) THEN guiaPagamentoItemCategoria.gich_vlcategoria ELSE guiaPagamentoCategoria.gpch_vlcategoria END) as somapagamentoguia,"
					  + " aviso.ctbc_id as contaBancariaId, "
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId, "
					  // + " CASE WHEN (guiaPagamentoItemCategoria.gpih_id is not null and debitoTipoItem.dbtp_icdividaativa = 1) THEN " + ContaContabil.RECEITA_DIVIDA_ATIVA + " WHEN ( (clie.cltp_id is not null or clieGuia.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieGuia.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil "					  
					  + " CASE \n"
					  + "   WHEN (guiaPagamentoItemCategoria.gpih_id is not null and debitoTipoItem.dbtp_icdividaativa = 1) THEN \n" 
					  + "     " + ContaContabil.RECEITA_DIVIDA_ATIVA +  
					    "   WHEN ( (clie.cltp_id is not null or clieGuia.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieGuia.cltp_id = " + ClienteTipo.RECEITA_INTRA + " ) ) THEN \n"
					  + "     CASE \n"
					  + "       WHEN ( debitoTipoItem.dbtp_id is not null )THEN \n"
					  + "         debitoTipoItem.cnct_idintra \n"
					  + "       ELSE \n"
					  + "         debitoTipo.cnct_idintra \n"         
					  + "       END \n"
					  + "   ELSE \n" 
					  + "     CASE \n" 
					  + "       WHEN ( debitoTipoItem.dbtp_id is not null )THEN \n"
					  + "         debitoTipoItem.cnct_id \n"
					  + "       ELSE \n"
					  + "         debitoTipo.cnct_id \n" 
					  + "     END \n"
					  + " END as contaContabil \n"
					  
					  + " from arrecadacao.pagamento_historico pag"
					
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  + " inner join faturamento.guia_pagto_catg_hist guiaPagamentoCategoria on guiaPagamentoCategoria.gpag_id = pag.gpag_id"
					  
					  + " inner join faturamento.guia_pagamento_historico guiaPagamento on guiaPagamento.gpag_id = pag.gpag_id "
					  + " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = guiaPagamento.dbtp_id "
					
					  + " left join faturamento.guia_pagamento_item_hist guiaPagamentoItem on guiaPagamentoItem.gpag_id = guiaPagamento.gpag_id "
					  + " left join faturamento.guia_pagt_item_catg_hist guiaPagamentoItemCategoria on guiaPagamentoItemCategoria.gpih_id = guiaPagamentoItem.gpih_id and guiaPagamentoItemCategoria.catg_id = guiaPagamentoCategoria.catg_id "
					  + " left join faturamento.debito_tipo debitoTipoItem on debitoTipoItem.dbtp_id = guiaPagamentoItem.dbtp_id"
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = guiaPagamento.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					  + " left join cadastro.cliente clieGuia on clieGuia.clie_id = guiaPagamento.clie_id "
					  
					  + " where" 
					  + " (pag.pgst_idatual = 0 or pag.pgst_idatual = 10)"
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " categoriaId,"
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id, "
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id, "
					  + " contaContabil "
					  + " order by "
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " categoriaId,"
					  + " aviso.avbc_dtrealizada," 
					  +	" contaContabil ";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamentoguia", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarOutrasReceitasResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select" 
				  + " localidade.greg_id as gerenciaId,"
				  + " localidade.uneg_id as unidadeId,"
				  + " localidade.loca_id as localidadeId,"
				  + " pag.imov_id as imovelId,"
				  + " aviso.avbc_dtrealizada as dataRealizada,"
				  + " sum(pag.pgmt_vlpagamento) as somapagamento,"
				  + " aviso.ctbc_id as contaBancariaId, "
				  + " agencia.bnco_id as banco,"
				  + " aviso.arrc_id as arrecadadorId,"
				  + " imovel.imov_idcategoriaprincipal as categoriaId,"
				  + " CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END as receitaIntra"
				  
				  + " from arrecadacao.pagamento pag"
				
				  + " inner join faturamento.conta conta on conta.cnta_id = pag.cnta_id"
				  + " inner join cadastro.imovel imovel on imovel.imov_id = pag.imov_id"
				  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
				  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
				  
				  
				  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
				  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
				  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = conta.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
				  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
				
				  + " where" 
//				  + " not exists(SELECT dad.cnta_id FROM cobranca.divida_ativa_debito dad WHERE dad.dade_dtretirada is null and dad.cnta_id = conta.cnta_id) and"
				  + " pag.pgst_idatual = 10"
				  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
				  + " and (pag.pgmt_vlpagamento <> (conta.cnta_vlagua + conta.cnta_vlesgoto + conta.cnta_vldebitos - conta.cnta_vlcreditos - conta.cnta_vlimpostos))"
				  + " group by "
				  + " localidade.greg_id,"
				  + " localidade.uneg_id,"
				  + " localidade.loca_id ,"
				  + " pag.imov_id,"
				  + " aviso.avbc_dtrealizada,"
				  + " aviso.ctbc_id, "
				  + " agencia.bnco_id,"
				  + " aviso.arrc_id,"
				  + " imovel.imov_idcategoriaprincipal,"
				  + "CASE WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN true ELSE false END"
				  + " order by" 
				  + " localidade.greg_id,"
				  + " localidade.uneg_id,"
				  + " localidade.loca_id,"
				  + " pag.imov_id,"
				  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamento", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " pag.imov_id as imovelId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " sum(pag.pgmt_vlpagamento) as somapagamento, "
					  + " aviso.ctbc_id as contaBancariaId, "
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END as receitaIntra"
					  
					  + " from arrecadacao.pagamento pag"
					
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  
					  
					  + " inner join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " inner join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = pag.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					  + " left join cadastro.cliente cliePagto on cliePagto.clie_id = pag.clie_id "
					
					  + " where "
					  + " pag.pgst_idatual not in (10, 0)"
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					 
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " pag.imov_id,"
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id, "
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END"
					  + " order by" 
					  + " pag.imov_id desc";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamento", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " pag.imov_id as imovelId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " sum(pag.pghi_vlpagamento) as somapagamento,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END as receitaIntra"
					  
					  + " from arrecadacao.pagamento_historico pag"
					
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = pag.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					  + " left join cadastro.cliente cliePagto on cliePagto.clie_id = pag.clie_id "
					
					  + " where "
//					  + " (pag.pgst_idatual <> 10 or pag.pgst_idatual <> 0)"
					  + " pag.pgst_idatual not in (10, 0)"
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					 
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " pag.imov_id,"
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END"
					  + " order by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " pag.imov_id,"
					  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamento", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select" 
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " imovel.imov_idcategoriaprincipal  as categoriaId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " sum(pag.pgmt_vlpagamento) as somapagamentoDebCobrar,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN (debitoTipo.dbtp_icdividaativa = 1) THEN " + ContaContabil.RECEITA_DIVIDA_ATIVA + " WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil "
					  
					  + " from arrecadacao.pagamento pag"
					
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  + " inner join cadastro.imovel imovel on imovel.imov_id = pag.imov_id"
					  
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  
					  + " inner join faturamento.debito_a_cobrar debitoCobrar on debitoCobrar.dbac_id = pag.dbac_id "
					  + " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = pag.dbtp_id "
					  
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = pag.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					
					  + " where "
					  + " (pag.pgst_idatual = 0 or pag.pgst_idatual = 10)"
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " imovel.imov_idcategoriaprincipal ,"
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " contaContabil"
					  + " order by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " imovel.imov_idcategoriaprincipal ,"
					  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamentoDebCobrar", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select" 
					  + " localidade.greg_id as gerenciaId,"
					  + " localidade.uneg_id as unidadeId,"
					  + " localidade.loca_id as localidadeId,"
					  + " imovel.imov_idcategoriaprincipal as categoriaId,"
					  + " aviso.avbc_dtrealizada as dataRealizada,"
					  + " sum(pag.pghi_vlpagamento) as somapagamentoDebCobrar,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN (debitoTipo.dbtp_icdividaativa = 1) THEN " + ContaContabil.RECEITA_DIVIDA_ATIVA + " WHEN (clie.cltp_id is not null and clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil "
					  
					  + " from arrecadacao.pagamento_historico pag"
					
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
					  + " inner join cadastro.imovel imovel on imovel.imov_id = pag.imov_id"
					  
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  
					  + " inner join faturamento.deb_a_cobrar_catg_hist debitoCobrar on debitoCobrar.dbac_id = pag.dbac_id "
					  + " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = pag.dbtp_id "
					  
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = pag.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					  
					
					  + " where "
					  + " (pag.pgst_idatual = 0 or pag.pgst_idatual = 10)"
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
					  + " group by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id ,"
					  + " imovel.imov_idcategoriaprincipal,"
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " contaContabil"
					  + " order by" 
					  + " localidade.greg_id,"
					  + " localidade.uneg_id,"
					  + " localidade.loca_id,"
					  + " imovel.imov_idcategoriaprincipal,"
					  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamentoDebCobrar", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Flávio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select "   
					  + " localidade.greg_id as gerenciaId,"  
					  + " localidade.uneg_id as unidadeId,  "
					  + " localidade.loca_id as localidadeId,"  
					  + " pag.imov_id as imovelId,  "
					  + " aviso.avbc_dtrealizada as dataRealizada,"  
					  + " sum(pag.pghi_vlpagamento) as somapagamento,"
					  + " aviso.ctbc_id as contaBancariaId,"
					  + " agencia.bnco_id as banco,"
					  + " aviso.arrc_id as arrecadadorId,"
					  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END as receitaIntra"
										  
					  + " from arrecadacao.pagamento_historico pag  "
										
					  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"  
					  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id" 
					  
					  
					  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
					  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
					  
					  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = pag.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
					  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
					  + " left join cadastro.cliente cliePagto on cliePagto.clie_id = pag.clie_id "
					  
										
					  + " where"   
					  // + " pag.cnta_id is null and pag.gpag_id is null and dbac_id is null and"
					  + " pag.pgst_idatual not in ( 10, 0 )"  
					  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal" 
										 
					  + " group by"   
					  + " localidade.greg_id,"  
					  + " localidade.uneg_id,"  
					  + " localidade.loca_id ,  "
					  + " pag.imov_id,"  
					  + " aviso.avbc_dtrealizada,"
					  + " aviso.ctbc_id,"
					  + " agencia.bnco_id,"
					  + " aviso.arrc_id,"
					  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END"
					  + " order by"   
					  + " localidade.greg_id,"  
					  + " localidade.uneg_id,"  
					  + " localidade.loca_id,"  
					  + " pag.imov_id,"  
					  + " aviso.avbc_dtrealizada";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamento", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					.addScalar("receitaIntra", Hibernate.BOOLEAN)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	
	public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo)
		throws ErroRepositorioException{
		
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try{
			consulta = "SELECT "
					+ " resumo.banco.id, "
					+ " resumo.contaBancaria.numeroConta,"
					+ " resumo.contaContabil.numeroConta,"
					+ " sum(resumo.valorReceita) as receita "
					+ " FROM ResumoReceita resumo "
					+ " WHERE 1=1 ";
			
			if(resumo.getAnoMes() != null && !resumo.getAnoMes().equals("")){
				consulta += " and resumo.anoMesReferencia = " + resumo.getAnoMes();
			}
			if(resumo.getGerenciaRegionalId() != null && !resumo.getGerenciaRegionalId().equals("")){
				consulta += " and resumo.gerenciaRegional.id = " + resumo.getGerenciaRegionalId();
			}
			if(resumo.getLocalidadeInicial() != null && !resumo.getLocalidadeInicial().equals("")){
				if(resumo.getLocalidadeFinal() != null && !resumo.getLocalidadeFinal().equals("")){
					consulta += "and (resumo.localidade.id between " 
						+ resumo.getLocalidadeInicial()
						+ " and " 
						+ resumo.getLocalidadeFinal()+") ";
				}else{
					consulta += " and resumo.localidade.id = " + resumo.getLocalidadeInicial();
				}
				
			}
			if(resumo.getCategoriaId() != null && !resumo.getCategoriaId().equals("")){
				consulta += " and resumo.categoria.id = " + resumo.getCategoriaId();
			}
			if(resumo.getUnidadeNegocioId() != null && !resumo.getUnidadeNegocioId().equals("")){
				consulta += " and resumo.unidadeNegocio = " + resumo.getUnidadeNegocioId();
			}
			
			consulta += " GROUP BY "
					+ " resumo.banco.id, "
					+ " resumo.contaBancaria.numeroConta, "
					+ " resumo.contaContabil.numeroConta "
					+ " ORDER BY resumo.banco.id, "
					+ " resumo.contaBancaria.numeroConta, "
					+ " resumo.contaContabil.numeroConta ";
		
			retorno = session.createQuery(consulta).list();
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}
	
	public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo)
	throws ErroRepositorioException{
	
	Collection retorno = null;
	
	Session session = HibernateUtil.getSession();
	String consulta;
	
	try{
		consulta = "SELECT "
				+ " resumo.banco.id, "
				+ " resumo.contaBancaria.numeroConta,"
				+ " resumo.arrecadador.id, "
				+ " resumo.dataRealizada, "
				+ " resumo.contaContabil.numeroConta,"
				+ " sum(resumo.valorReceita) as receita "
				+ " FROM ResumoReceita resumo "
				+ " WHERE 1=1 ";
		
		if(resumo.getAnoMes() != null && !resumo.getAnoMes().equals("")){
			consulta += " and resumo.anoMesReferencia = " + resumo.getAnoMes();
		}
		if(resumo.getGerenciaRegionalId() != null && !resumo.getGerenciaRegionalId().equals("")){
			consulta += " and resumo.gerenciaRegional.id = " + resumo.getGerenciaRegionalId();
		}
		if(resumo.getLocalidadeInicial() != null && !resumo.getLocalidadeInicial().equals("")){
			if(resumo.getLocalidadeFinal() != null && !resumo.getLocalidadeFinal().equals("")){
				consulta += "and (resumo.localidade.id between " 
					+ resumo.getLocalidadeInicial()
					+ " and " 
					+ resumo.getLocalidadeFinal()+") ";
			}else{
				consulta += " and resumo.localidade.id = " + resumo.getLocalidadeInicial();
			}
			
		}
		if(resumo.getCategoriaId() != null && !resumo.getCategoriaId().equals("")){
			consulta += " and resumo.categoria.id = " + resumo.getCategoriaId();
		}
		if(resumo.getUnidadeNegocioId() != null && !resumo.getUnidadeNegocioId().equals("")){
			consulta += " and resumo.unidadeNegocio = " + resumo.getUnidadeNegocioId();
		}
		
		consulta += " GROUP BY "
				+ " resumo.banco.id, "
				+ " resumo.contaBancaria.numeroConta, "
				+ " resumo.arrecadador.id, "
				+ " resumo.dataRealizada, "
				+ " resumo.contaContabil.numeroConta"
				
				+ " ORDER BY resumo.banco.id, "
				+ " resumo.contaBancaria.numeroConta, "
				+ " resumo.arrecadador.id, "
				+ " resumo.dataRealizada, "
				+ " resumo.contaContabil.numeroConta";
	
		retorno = session.createQuery(consulta).list();
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
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
		
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select" 
				  + " localidade.greg_id as gerenciaId,"
				  + " localidade.uneg_id as unidadeId,"
				  + " localidade.loca_id as localidadeId,"
				  + " pag.imov_id as imovelId,"
				  + " aviso.avbc_dtrealizada as dataRealizada,"
				  + " sum(pag.pghi_vlpagamento) as somapagamento,"
				  + " aviso.ctbc_id as contaBancariaId, "
				  + " agencia.bnco_id as banco,"
				  + " aviso.arrc_id as arrecadadorId, "
				  //+ " contaCategoria.catg_id as categoriaId"
				  + " CASE WHEN ( (clie.cltp_id is not null or cliePagto.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or cliePagto.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN debitoTipo.cnct_idintra ELSE debitoTipo.cnct_id END as contaContabil"
				  
				  + " from arrecadacao.pagamento_historico pag"
				
				  //+ " inner join faturamento.conta_historico conta on conta.cnta_id = pag.cnta_id"
				  //+ " inner join faturamento.conta_categoria_historico contaCategoria on conta.cnta_id = contaCategoria.cnta_id"
				  + " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = pag.avbc_id"
				  + " inner join cadastro.localidade localidade on localidade.loca_id = pag.loca_id"
				  
				  
				  + " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
				  + " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
				  + " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = pag.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
				  + " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
				  + " left join cadastro.cliente cliePagto on cliePagto.clie_id = pag.clie_id "
				  
				  + " inner join faturamento.debito_tipo debitoTipo on debitoTipo.dbtp_id = pag.dbtp_id "
				
				  + " where" 
				  //+ " conta.cnta_amreferenciabaixacontabil is null"
				  + " pag.pgst_idatual = 10 or pag.pgst_idatual = 0"
				  + " and pag.cnta_id is null and pag.gpag_id is null and dbac_id is null "
				  + " and aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
				  //+ " and (pag.pgmt_vlpagamento <> (conta.cnta_vlagua + conta.cnta_vlesgoto + conta.cnta_vldebitos - conta.cnta_vlcreditos - conta.cnta_vlimpostos))"
				  + " group by "
				  + " localidade.greg_id,"
				  + " localidade.uneg_id,"
				  + " localidade.loca_id ,"
				  + " pag.imov_id,"
				  + " aviso.avbc_dtrealizada,"
				  + " contaBancariaId, "
				  + " banco,"
				  + " arrecadadorId, "
				  + " contaContabil "
				  //+ " categoriaId"
				  + " order by" 
				  + " localidade.greg_id,"
				  + " localidade.uneg_id,"
				  + " localidade.loca_id,"
				  + " pag.imov_id,"
				  + " aviso.avbc_dtrealizada,"
				  + " contaContabil ";
			
			retorno = session.createSQLQuery(consulta)
					.addScalar("gerenciaId", Hibernate.INTEGER)
					.addScalar("unidadeId", Hibernate.INTEGER)
					.addScalar("localidadeId", Hibernate.INTEGER)
					.addScalar("imovelId", Hibernate.INTEGER)
					.addScalar("dataRealizada", Hibernate.TIMESTAMP)
					.addScalar("somapagamento", Hibernate.BIG_DECIMAL)
					.addScalar("contaBancariaId", Hibernate.INTEGER)
					.addScalar("banco", Hibernate.INTEGER)
					.addScalar("arrecadadorId", Hibernate.INTEGER)
					//.addScalar("categoriaId", Hibernate.INTEGER)
					.addScalar("contaContabil", Hibernate.INTEGER)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioResumo(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
			
			Collection retorno;
			
			Session session = HibernateUtil.getSession();
			String consulta;
			try{
				consulta = "select" 
						+ " localidade.greg_id as gerenciaId,"
						+ " localidade.uneg_id as unidadeId,"
						+ " localidade.loca_id as localidadeId,"
						+ " dev.imov_id as imovelId,"
						+ " aviso.avbc_dtrealizada as dtRealizada,"
						+ " sum(devl_vldevolucao) as somaDevolucao, "
						+ " aviso.ctbc_id as contaBancariaId,"
						+ " agencia.bnco_id as banco,"
						+ " aviso.arrc_id as arrecadadorId,"
						+ " CASE WHEN ( (clie.cltp_id is not null or clieDev.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieDev.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END as receitaIntra"
						  
						+ " from arrecadacao.devolucao dev"
					
						+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = dev.avbc_id"
						+ " inner join cadastro.localidade localidade on localidade.loca_id = dev.loca_id"
						
						+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
						+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
						
						+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = dev.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
						+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
						+ " left join cadastro.cliente clieDev on clieDev.clie_id = dev.clie_id "
						
					
						+ " where "
						//+ " avbc_vldevolucaocalculado > 0 "
						+ " aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
						+ " group by" 
						+ " localidade.greg_id,"
						+ " localidade.uneg_id,"
						+ " localidade.loca_id,"
						+ " dev.imov_id,"
						+ " aviso.avbc_dtrealizada,"
						+ " contaBancariaId,"
						+ " banco,"
						+ " arrecadadorId,"
						+ " CASE WHEN ( (clie.cltp_id is not null or clieDev.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieDev.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END"
						+ " order by" 
						+ " localidade.greg_id,"
						+ " localidade.uneg_id,"
						+ " localidade.loca_id,"
						+ " dev.imov_id,"
						+ " aviso.avbc_dtrealizada";
				
				retorno = session.createSQLQuery(consulta)
						.addScalar("gerenciaId", Hibernate.INTEGER)
						.addScalar("unidadeId", Hibernate.INTEGER)
						.addScalar("localidadeId", Hibernate.INTEGER)
						.addScalar("imovelId", Hibernate.INTEGER)
						.addScalar("dtRealizada", Hibernate.TIMESTAMP)
						.addScalar("somaDevolucao", Hibernate.BIG_DECIMAL)
						.addScalar("contaBancariaId", Hibernate.INTEGER)
						.addScalar("banco", Hibernate.INTEGER)
						.addScalar("arrecadadorId", Hibernate.INTEGER)
						.addScalar("receitaIntra", Hibernate.BOOLEAN)
						.setTimestamp("dataInicial", dataInicial)
						.setTimestamp("dataFinal", dataFinal)
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
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioHistoricoResumo(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException{
			
			Collection retorno;
			
			Session session = HibernateUtil.getSession();
			String consulta;
			try{
				consulta = "select" 
						+ " localidade.greg_id as gerenciaId,"
						+ " localidade.uneg_id as unidadeId,"
						+ " localidade.loca_id as localidadeId,"
						+ " dev.imov_id as imovelId,"
						+ " aviso.avbc_dtrealizada as dtRealizada,"
						+ " sum(dehi_vldevolucao) as somaDevolucao, "
						+ " aviso.ctbc_id as contaBancariaId,"
						+ " agencia.bnco_id as banco,"
						+ " aviso.arrc_id as arrecadadorId,"
						+ " CASE WHEN ( (clie.cltp_id is not null or clieDev.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieDev.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END as receitaIntra"
						  
						+ " from arrecadacao.devolucao_historico dev"
					
						+ " inner join arrecadacao.aviso_bancario aviso on aviso.avbc_id = dev.avbc_id"
						+ " inner join cadastro.localidade localidade on localidade.loca_id = dev.loca_id"
						
						+ " left join arrecadacao.conta_bancaria contaBancaria on contaBancaria.ctbc_id = aviso.ctbc_id"
						+ " left join arrecadacao.agencia agencia on agencia.agen_id = contaBancaria.agen_id"
						
						+ " left join cadastro.cliente_imovel clieImov on clieImov.imov_id = dev.imov_id and clieImov.clim_dtrelacaofim is null and clieImov.crtp_id = " + ClienteRelacaoTipo.RESPONSAVEL
						+ " left join cadastro.cliente clie on clie.clie_id = clieImov.clie_id "
						+ " left join cadastro.cliente clieDev on clieDev.clie_id = dev.clie_id "
					
						+ " where "
						//+ " avbc_vldevolucaocalculado > 0 "
						+ " aviso.avbc_dtrealizada between :dataInicial and :dataFinal"
						+ " group by" 
						+ " localidade.greg_id,"
						+ " localidade.uneg_id,"
						+ " localidade.loca_id,"
						+ " dev.imov_id,"
						+ " aviso.avbc_dtrealizada,"
						+ " contaBancariaId,"
						+ " banco,"
						+ " arrecadadorId,"
						+ " CASE WHEN ( (clie.cltp_id is not null or clieDev.cltp_id is not null) and (clie.cltp_id = " + ClienteTipo.RECEITA_INTRA + " or clieDev.cltp_id = " + ClienteTipo.RECEITA_INTRA + ") ) THEN true ELSE false END"
						+ " order by" 
						+ " localidade.greg_id,"
						+ " localidade.uneg_id,"
						+ " localidade.loca_id,"
						+ " dev.imov_id,"
						+ " aviso.avbc_dtrealizada";
				
				retorno = session.createSQLQuery(consulta)
						.addScalar("gerenciaId", Hibernate.INTEGER)
						.addScalar("unidadeId", Hibernate.INTEGER)
						.addScalar("localidadeId", Hibernate.INTEGER)
						.addScalar("imovelId", Hibernate.INTEGER)
						.addScalar("dtRealizada", Hibernate.TIMESTAMP)
						.addScalar("somaDevolucao", Hibernate.BIG_DECIMAL)
						.addScalar("contaBancariaId", Hibernate.INTEGER)
						.addScalar("banco", Hibernate.INTEGER)
						.addScalar("arrecadadorId", Hibernate.INTEGER)
						.addScalar("receitaIntra", Hibernate.BOOLEAN)
						.setTimestamp("dataInicial", dataInicial)
						.setTimestamp("dataFinal", dataFinal)
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
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString, 
			Integer idParametrosDevedoresDuvidosos)
			throws ErroRepositorioException {

		BigDecimal retorno = BigDecimal.ZERO;
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;
		
		try {
			
			consulta =	" select sum(cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos ) as valorContas "
					+   " from faturamento.conta cnta "
					+   " where cnta.loca_id = :idLocalidade "
					+   " and cnta.qdra_id =  :idQuadra " 
					+   " and cnta.dcst_idatual in ( 0 , 1 ,2 ) " 
					+   " and cnta.cnta_amreferenciaconta < :anoMesReferencia " 
					+   " and cnta.cnta_amreferenciabaixacontabil is null "
					+   " and exists     "
					+   " (select ctcg.cnta_id "    
					+	" from faturamento.conta_categoria ctcg , cadastro.imovel_cobranca_situacao icbs, financeiro.param_deved_duvid_item pdd " 
					+	" where    (cnta.cnta_id = ctcg.cnta_id and ctcg.catg_id in (1,2,3) ) "
					+	" and (cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null) " 
					+	" and (     (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) "
					+	" and (pdd.pded_id = :idParametrosDevedoresDuvidosos )  "
					+   " and abs(MONTHS_BETWEEN ( "
					+	"	to_date( SUBSTR(to_char( :anoMesReferencia ), 5, 2) || '-01-' || SUBSTR(to_char( :anoMesReferencia ), 1, 4), 'MM-DD-YYYY'), "
					+	"	to_date(to_char(cnta.cnta_dtvencimentoconta, 'MM-DD-YYYY'), 'MM-DD-YYYY') "
					+	"	)) "
					+	"	>= pdd.pdit_nnmeses "
					+	" and    (cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos - cnta.cnta_vlimpostos ) <= pdd.pdit_vlvalorlimite " 
					+	" ) "
					+	" ) "; 

			
			//System.out.println("" + consulta);
				retorno = (BigDecimal) session.createSQLQuery(consulta)
						.addScalar("valorContas", Hibernate.BIG_DECIMAL)
						.setInteger("idLocalidade", idLocalidade)
						.setInteger("anoMesReferencia", anoMesReferenciaContabil)
						.setInteger("idParametrosDevedoresDuvidosos", idParametrosDevedoresDuvidosos)
						.setInteger("idQuadra", idQuadra)
						.uniqueResult();
						
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * @author Arthur Carvalho, Anderson Cabral
	 * @date 08/11/2010, 05/02/2013
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterValorTotalContasDevedoresDuvidososPorEsferaDePoder( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString, 
			Integer idParametrosDevedoresDuvidosos, Collection colecaoEsferas)
			throws ErroRepositorioException {

		Collection<Object[]> retorno = null;
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;
		
		try {

		consulta = "SELECT sum(coalesce(cnta.cnta_vlagua,0) + coalesce(cnta.cnta_vlesgoto,0) + coalesce(cnta.cnta_vldebitos,0) - coalesce(cnta.cnta_vlcreditos,0) ) as valorContas, " 
				+ 	" categoria.catg_id as idCategoria "
				+ " FROM faturamento.conta as cnta "
				+ " INNER JOIN faturamento.conta_categoria  ctcg ON (cnta.cnta_id = ctcg.cnta_id) "
				+ " INNER JOIN cadastro.categoria as categoria ON (ctcg.catg_id = categoria.catg_id) "
				+ " WHERE"
				+ " cnta.loca_id = :idLocalidade "
				+ " AND cnta.qdra_id = :idQuadra "
				+ " AND cnta.dcst_idatual in ( 0 , 1 , 2 ) "
				+ " AND cnta.cnta_amreferenciaconta < :anoMesReferencia " 
				+ " AND cnta.cnta_amreferenciabaixacontabil is null "
				+ " AND exists ( select ctcg.cnta_id "   
				+      "FROM faturamento.conta_categoria as ctcg "
				+      "LEFT JOIN cadastro.imovel_cobranca_situacao as icbs ON (cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null) "
				+      "LEFT JOIN financeiro.param_deved_duvid_item as pdd ON ( (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) "
				+      "AND (pdd.pded_id = :idParametrosDevedoresDuvidosos) " 
				+      "AND abs((( extract( year from to_date( substring(:anoMesReferencia , 1, 4) || substring(:anoMesReferencia , 5, 2)  || '-01-', 'YYYY-MM-DD' ) ) - extract( year from to_date( cnta.cnta_dtvencimentoconta, 'YYYY-MM-DD' ) ) ) * 12 ) + "
				+	   "( extract( month from to_date(  substring(:anoMesReferencia , 1, 4) || substring(:anoMesReferencia , 5, 2)  || '-01-', 'YYYY-MM-DD' )  ) ) - extract( month from to_date( cnta.cnta_dtvencimentoconta, 'YYYY-MM-DD' ) ) ) >= pdd.pdit_nnmeses "
		        +      "AND    (cnta.cnta_vlagua + cnta.cnta_vlesgoto + cnta.cnta_vldebitos - cnta.cnta_vlcreditos - cnta.cnta_vlimpostos ) <= pdd.pdit_vlvalorlimite "
		        +      " ) "
		        +	   " INNER JOIN cadastro.cliente_conta cliConta ON (cnta.cnta_id = cliConta.cnta_id AND cliConta.crtp_id = 2)"
		        +	   " INNER JOIN cadastro.cliente cliente ON (cliConta.clie_id = cliente.clie_id) "
		        +	   " INNER JOIN cadastro.cliente_tipo cliTipo ON (cliente.cltp_id  = cliTipo.cltp_id) "
				+	   " WHERE (cliTipo.epod_id in (:colecaoEsferas))"
				+      " ) "
				+	" group by categoria.catg_id"; 
			
				retorno = session.createSQLQuery(consulta)
						.addScalar("valorContas", Hibernate.BIG_DECIMAL)
						.addScalar("idCategoria", Hibernate.INTEGER)
						.setInteger("idLocalidade", idLocalidade)
						.setInteger("anoMesReferencia", anoMesReferenciaContabil)
						.setInteger("idParametrosDevedoresDuvidosos", idParametrosDevedoresDuvidosos)
						.setParameterList("colecaoEsferas", colecaoEsferas)
						.setInteger("idQuadra", idQuadra)
						.list();
								
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * @author Rafael Corrêa
	 * @date 28/05/2013
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString, 
			Integer idParametrosDevedoresDuvidosos, Collection colecaoEsferas) throws ErroRepositorioException {

		BigDecimal retorno = null;
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta;
		
		try {

		consulta = "SELECT sum(coalesce(conta.cnta_vlagua,0) + coalesce(conta.cnta_vlesgoto,0) + coalesce(conta.cnta_vldebitos,0) - coalesce(conta.cnta_vlcreditos,0) ) as valorContas " 
				+ " FROM faturamento.conta as conta "
				+ " WHERE conta.cnta_id in ( "
						+ 	"SELECT DISTINCT cnta.cnta_id "
						+ 	"FROM faturamento.conta cnta "
						+ 	"INNER JOIN cadastro.cliente_conta cliConta on cnta.cnta_id = cliConta.cnta_id and cliConta.crtp_id = " + ClienteRelacaoTipo.USUARIO + " "
						+ 	"INNER JOIN cadastro.cliente cliente on cliConta.clie_id = cliente.clie_id "
						+ 	"INNER JOIN cadastro.cliente_tipo cliTipo on cliente.cltp_id  = cliTipo.cltp_id "
						+ 	"INNER JOIN financeiro.param_deved_duvid_item pdd on pdd.pded_id = :idParametrosDevedoresDuvidosos "
						+ 	"LEFT JOIN cadastro.imovel_cobranca_situacao icbs on cnta.imov_id = icbs.imov_id and icbs.iscb_dtretiradacobranca is null "
						+ 	"WHERE cliTipo.epod_id in (:colecaoEsferas) "
						+ 	"and cnta.loca_id = :idLocalidade " 
						+ 	"and cnta.qdra_id = :idQuadra "
						+ 	"and cnta.dcst_idatual in (0, 1, 2) " 
						+ 	"and cnta.cnta_amreferenciaconta < :anoMesReferencia "
						+ 	"and cnta.cnta_amreferenciabaixacontabil is null "
						+ 	"and (coalesce(cnta.cnta_vlagua,0) + coalesce(cnta.cnta_vlesgoto,0) + coalesce(cnta.cnta_vldebitos,0) - coalesce(cnta.cnta_vlcreditos,0) - coalesce(cnta.cnta_vlimpostos,0)) <= pdd.pdit_vlvalorlimite "
						+ 	"and ( "
						+ 	"		((cast(substr(:anoMesReferencia, 1, 4) as integer) - extract(year from cnta.cnta_dtvencimentoconta)) * 12) + " 
						+ 	"		(cast(substr(:anoMesReferencia, 5, 2) as integer) - extract(month from cnta.cnta_dtvencimentoconta)) "
						+ 	"	 ) >= pdd.pdit_nnmeses "
						+ 	"and (pdd.cbst_id is null or icbs.cbst_id = pdd.cbst_id) ) ";
			
				retorno = (BigDecimal) session.createSQLQuery(consulta)
						.addScalar("valorContas", Hibernate.BIG_DECIMAL)
						.setInteger("idLocalidade", idLocalidade)
						.setInteger("anoMesReferencia", anoMesReferenciaContabil)
						.setInteger("idParametrosDevedoresDuvidosos", idParametrosDevedoresDuvidosos)
						.setParameterList("colecaoEsferas", colecaoEsferas)
						.setInteger("idQuadra", idQuadra)
						.uniqueResult();
								
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idTipoPerda
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda) throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
		
		consulta = "select sum(ctcg.ctcg_vlagua) as valorAgua, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_categoria  ctcg"
			+ " inner join faturamento.conta  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cadastro.categoria  categoria on categoria.catg_id = ctcg.catg_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda"
			+ " and cnta.loca_id = :idLocalidade"
			+ " and cnta.qdra_id = :idQuadra"
			+ " and "+referencia+" = :anoMesReferencia"
			+ " group by categoria.catg_id";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESTADUAL
	 * 
	 * @author Rafael Pinto
	 * @date 11/04/2013
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idTipoPerda
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorTotalEsferaPoderEstadualAgrupadoPorCategoriaDevedoresDuvidosos(
		int anoMesReferenciaContabil,Integer idLocalidade, Integer idQuadra, 
		Integer idTipoPerda) throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
		
			consulta = "SELECT sum(coalesce(cnta.cnta_vlagua,0) + coalesce(cnta.cnta_vlesgoto,0) + coalesce(cnta.cnta_vldebitos,0) - coalesce(cnta.cnta_vlcreditos,0) ) as valorContas, " 
				+ " categoria.catg_id as categoria " 
				+ " FROM "
				+ " faturamento.conta cnta "
				+ " INNER JOIN cadastro.imovel on imovel.imov_id = cnta.imov_id"
				+ " INNER JOIN cadastro.categoria categoria on categoria.catg_id = imovel.imov_idcategoriaprincipal "
		        + " INNER JOIN cadastro.cliente_conta cliConta ON (cnta.cnta_id = cliConta.cnta_id AND cliConta.crtp_id = 2)"
		        + " INNER JOIN cadastro.cliente cliente ON (cliConta.clie_id = cliente.clie_id)"
		        + " INNER JOIN cadastro.cliente_tipo cliTipo ON (cliente.cltp_id  = cliTipo.cltp_id AND cliTipo.epod_id = :idEsferaPoder)"
				+ " where cnta.petp_id = :idTipoPerda"
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and "+referencia+" = :anoMesReferencia"
				+ " group by categoria.catg_id";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorContas",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.setInteger("idEsferaPoder", EsferaPoder.ESTADUAL)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda)  throws ErroRepositorioException{
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
		
		consulta = "select sum(ctcg.ctcg_vlesgoto) as valorEsgoto, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_categoria  ctcg"
			+ " inner join faturamento.conta  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cadastro.categoria  categoria on categoria.catg_id = ctcg.catg_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda "
			+ " and cnta.loca_id = :idLocalidade "
			+ " and cnta.qdra_id = :idQuadra "
			+ " and "+referencia+" = :anoMesReferencia " 
			+ " group by categoria.catg_id ";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idQuadra", idQuadra)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			
		
		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
			
		consulta = " select   sum(dccg.dccg_vlcategoria) as valorCategoria," 
		         + " dccg.catg_id as categoria"
				 + " from  faturamento.conta  cnta " 
				 + " inner join faturamento.debito_cobrado  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.debito_cobrado_categoria  dccg on dccg.dbcb_id = dbcb.dbcb_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and "+referencia+" = :anoMesReferencia " 
				 + " and dbcb.fntp_id = :parcelamentoAgua "		

				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelamentoAgua" , FinanciamentoTipo.PARCELAMENTO_AGUA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
		
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda )  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
		
		consulta = " select   sum(dccg.dccg_vlcategoria) as valorCategoria," 
		         + " 		  dccg.catg_id as categoria"
				 + " from faturamento.conta cnta " 
				 + "  inner join faturamento.debito_cobrado  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + "  inner join faturamento.debito_cobrado_categoria  dccg on dccg.dbcb_id = dbcb.dbcb_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + "  and cnta.loca_id = :idLocalidade"
				 + "  and cnta.qdra_id = :idQuadra"
				 + "  and "+referencia+" = :anoMesReferencia " 
				 + "  and dbcb.fntp_id = :parcelamentoEsgoto"	

				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("parcelamentoEsgoto" , FinanciamentoTipo.PARCELAMENTO_ESGOTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
		
		consulta= " select   sum(dccg.dccg_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao, "//1
				+ " 		  lict.lict_id as financiamentoTipo, "//2
				+ " 		  dccg.catg_id as categoria "//3
				+ " from faturamento.conta  cnta "
				+ "  inner join faturamento.debito_cobrado  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_categoria  dccg on dccg.dbcb_id = dbcb.dbcb_id"
				+ "  inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ "  and cnta.loca_id = :idLocalidade"
				+ "  and cnta.qdra_id = :idQuadra"
				+ "  and "+referencia+" = :anoMesReferencia  "
				+ "  and dbcb.fntp_id in( :parcelamentoServico , :entradaParcelamento) "		
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("parcelamentoServico", FinanciamentoTipo.PARCELAMENTO_SERVICO)
					.setInteger("entradaParcelamento", FinanciamentoTipo.ENTRADA_PARCELAMENTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda )  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
			
		
		consulta= " select   sum(dccg.dccg_vlcategoria) as valorCategoria,"
				+ "          dccg.catg_id as categoria"
				+ " from faturamento.conta  cnta "
				+ "  inner join faturamento.debito_cobrado  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_categoria  dccg on dccg.dbcb_id = dbcb.dbcb_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ "  and cnta.loca_id = :idLocalidade"
				+ "  and cnta.qdra_id = :idQuadra"
				+ "  and "+referencia+" = :anoMesReferencia  "
				+ "  and dbcb.fntp_id = :jurosParcelamento  "
				+ "  group by dccg.catg_id ";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("jurosParcelamento" , FinanciamentoTipo.JUROS_PARCELAMENTO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
			
		consulta= " select   sum(dccg.dccg_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  dccg.catg_id as categoria"//3
				+ " from faturamento.conta  cnta  "
				+ "  inner join faturamento.debito_cobrado  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_categoria  dccg on dccg.dbcb_id = dbcb.dbcb_id"
				+ "  inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and " + referencia + " = :anoMesReferencia "
				+ " and dbcb.fntp_id  in ( :servicoNormal, :arrastoAgua, :arrastoEsgoto, :arrastoServico) "
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("servicoNormal" , FinanciamentoTipo.SERVICO_NORMAL)
					.setInteger("arrastoAgua" , FinanciamentoTipo.ARRASTO_AGUA)
					.setInteger("arrastoEsgoto" , FinanciamentoTipo.ARRASTO_ESGOTO)
					.setInteger("arrastoServico" , FinanciamentoTipo.ARRASTO_SERVICO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idTipoPerda
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idTipoPerda)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			String referencia = "";
			if ( idTipoPerda == PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS) {
				referencia = " cnta.cnta_amreferenciabaixasocial ";
			} else {
				referencia = " cnta.cnta_amreferenciabaixacontabil ";
			}
		
		consulta= " select   sum(crcg.crcg_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  crcg.catg_id as categoria"//3
				+ " from faturamento.cred_realizado_catg  crcg"
				+ "  inner join faturamento.credito_realizado  crrz on crrz.crrz_id = crcg.crrz_id"
				+ "  inner join faturamento.conta  cnta on cnta.cnta_id = crrz.cnta_id"
				+ "  inner join financeiro.lancamento_item_contabil  lict on crrz.lict_id = lict.lict_id"
				+ "  inner join financeiro.lancamento_item  lcit on lict.lcit_id = lcit.lcit_id" 								
				+ " where "
				+ " cnta.petp_id = :idTipoPerda"
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and "+ referencia + " = :anoMesReferencia "	
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , crcg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", idTipoPerda)
					.setInteger("idQuadra", idQuadra)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
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
	 */
	public Collection<Integer> pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos(Integer idLocalidade)throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {

			consulta = "SELECT DISTINCT qdra.id "
				+ "FROM Quadra qdra "
				+ "inner join qdra.setorComercial setor "
				+ "inner join setor.localidade loca "
				+ "WHERE loca.id = :idLocalidade ";

		retorno = session.createQuery(consulta)
				.setInteger("idLocalidade", idLocalidade)
				.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Mariana Victor
	 * @date 28/03/2011
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberFaixaResumo(
			int anoMesReferenciaRecebimentos, Integer idLocalidade)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		String delete;

		try {
			
			delete = "DELETE FROM DocumentosAReceberFaixaResumo drfx "
					+ " WHERE drfx.documentosAReceberResumo.id IN "
					+ " (SELECT drrs.id FROM DocumentosAReceberResumo drrs WHERE drrs.localidade.id = :idLocalidade "
					+ " AND drrs.anoMesReferenciaRecebimentos = :anoMesReferencia) ";
			
			session.createQuery(delete).setInteger("idLocalidade",
					idLocalidade).setInteger("anoMesReferencia",
					anoMesReferenciaRecebimentos).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Tiago Moreno
	 * @date 28/06/11
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	public Collection pesquisarGerarIntegracaoContabilidadeCosama(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException{
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select  lcor.numeroCartao ,"//0
					   + " lcor.codigoTipo ,"//1
					   + " lcor.numeroFolha ,"//2
					   + " cntc.indicadorLinha ,"//3
					   + " cntc.prefixoContabil ,"//4
					   + " cntc.numeroConta ,"//5
					   + " cntc.numeroDigito ,"//6
					   + " cntc.numeroTerceiros ,"//7
					   + " lcor.codigoReferencia ,"//8
					   + " sum(lcti.valorLancamento) ,"//9
					   + " lcti.indicadorDebitoCredito ,"//10
					   + " lcor.numeroCartao2 ,"//11
					   + " lcor.numeroVersao ,"//12
					   + " loca.id, "//13
					   + " loca.codigoCentroCusto,"//14
					   + " cntc.indicadorCentroCusto, " //15
					   + " lcor.numeroHistoricoDebito, " //16
					   + " lcor.numeroHistoricoCredito " //17
					   + " from LancamentoContabilItem lcti " // lançamento contabil item
					   + " left join lcti.lancamentoContabil lcnb" //lançamento contábil
					   + " left join lcnb.localidade loca" //localidade
					   + " left join lcnb.lancamentoOrigem lcor" //lançamento origem
					   + " left join lcti.contaContabil cntc" //conta contabil
					   + " where lcnb.anoMes= :anoMes and lcor.id= :idLancamentoOrigem " 
					   + " group by loca.id, cntc.numeroConta, lcti.indicadorDebitoCredito,"
					   + " cntc.numeroConta ,cntc.numeroDigito ,cntc.numeroTerceiros ,"
					   + " lcor.numeroCartao, lcor.codigoTipo,"
					   + " lcor.numeroFolha, cntc.indicadorLinha ,cntc.prefixoContabil ,"
					   + " lcor.codigoReferencia ,"
					   + " lcor.numeroCartao2 ,lcor.numeroVersao ,loca.codigoCentroCusto, cntc.indicadorCentroCusto, "
					   + " lcor.numeroHistoricoDebito, lcor.numeroHistoricoCredito "
					   + " order by loca.id, cntc.numeroConta, lcti.indicadorDebitoCredito desc";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMes",new Integer(anoMes))
					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
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
	 * 
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 16/09/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, BigDecimal valorTotalValoresBaixados) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		
		// declara o tipo de conexao
		Connection jdbcCon = session.connection();

		PreparedStatement st = null;
	
		try {

//			update = " update financeiro.param_deved_duvid pdd "
//					+ "	set pdd.pded_vlbaixado = (" + valorTotalValoresBaixados + "  + (select p.pded_vlbaixado from financeiro.param_deved_duvid p where p.pded_amreferenciacontabil = "+ anoMesReferenciaContabil+ " )) "	
//					+ "WHERE pdd.pded_amreferenciacontabil = " + anoMesReferenciaContabil +" ";
			
			update = "UPDATE financeiro.param_deved_duvid "
					+ "	SET pded_vlbaixado = pded_vlbaixado + " + valorTotalValoresBaixados	
					+ " WHERE pded_amreferenciacontabil = " + anoMesReferenciaContabil +" ";
		
			st = jdbcCon.prepareStatement(update);
			
			// executa o update
			st.executeUpdate();
			session.flush();
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
	}
	
	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Corrêa
	 * @date 31/05/2013
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idTipoPerda, Integer idParametro) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		
		// declara o tipo de conexao
		Connection jdbcCon = session.connection();

		PreparedStatement st = null;
	
		try {
			
			update = "UPDATE financeiro.param_deved_duvid SET pded_vlbaixado = ( "
					+ "		SELECT sum( "
					+ "			CASE WHEN (lctp_id = 23) THEN rded_vlvalorbaixado*(-1) ELSE rded_vlvalorbaixado END "
					+ "		) "
					+ "		FROM financeiro.resumo_deved_duvid "
					+ "		WHERE lctp_id <> 100 and rded_amreferenciacontabil = " + anoMesReferenciaContabil + " AND "
					+ "		petp_id = " + idTipoPerda + " "
					+ ") "
					+ "WHERE "
					+ "pded_id = " + idParametro;
		
			st = jdbcCon.prepareStatement(update);
			
			// executa o update
			st.executeUpdate();
			session.flush();
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
		
	}

	
	/**
	 * 
	 *  Pesquisa os Valores Baixados No resumo Agrupados Por Localidade
	 * 
	 * @author Arthur Carvalho
	 * @date 19/09/2011
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorBaixadoAgrupadoPorLocalidadeResumoDevedoresDuvidosos( int anoMesReferenciaContabil )  throws ErroRepositorioException {
	
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(" 
		+ 	" 	CASE WHEN (lctp_id = 23) THEN rded_vlvalorbaixado*(-1) ELSE rded_vlvalorbaixado END "
		+ 	" ) as valorBaixado , loca_id as localidade " 
		+   " from financeiro.resumo_deved_duvid "
		+ 	" WHERE lctp_id <> 100 and rded_amreferenciacontabil = :anoMesReferencia "
		+ 	" GROUP BY loca_id" ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorBaixado",Hibernate.BIG_DECIMAL)
					.addScalar("localidade", Hibernate.INTEGER)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [FS0009-Pesquisar Resumo Perdas Órgão Público] 
     * [FS0013] - Pesquisar Resumo de Recuperação da Provisão de Perdas Societárias
     * 
	 * @author Arthur Carvalho
	 * @date 17/11/2011
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarResumoDevedoresDuvidososPerdasOrgaoPublico( int anoMesReferenciaContabil , int idTipoPerda)  throws ErroRepositorioException {
	
		StatelessSession session = HibernateUtil.getStatelessSession();
		Integer qtd = null;
		boolean retorno = false;
		String consulta = "";
		
		try {
			
		
		consulta = " select count(*) as quantidade " 
		+   " from financeiro.resumo_deved_duvid "
		+ 	" where rded_amreferenciacontabil = :anoMesReferencia "
		+   " petp_id = :idPerdaTipo ";

		
		qtd = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade",Hibernate.INTEGER)
				.setInteger("anoMesReferencia", anoMesReferenciaContabil)
				.setInteger("idPerdaTipo", idTipoPerda)
				.setMaxResults(1).uniqueResult();
		
		if (qtd != null && qtd > 0) {
			retorno = true;
		}
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	

	
	/**
	 * O sistema verifica se existe contas que atendam os critérios informados para baixa societária
	 * 
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0003] - Validar Critérios Para Perdas Societárias
	 * 
	 *  @author Arthur Carvalho
	 *  @date 18/11/2011
	 *   
	 * @param anoMesInicial
	 * @param anoMesFinal
	 * @param anoMesMenosNumeroDeMesesInformados
	 * @param colecaoEsferaPoder
	 * @param colecaoCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisaQuantidadeContasBaixaSocietaria(String anoMesInicial, String anoMesFinal, Integer anoMesMenosNumeroDeMesesInformados,
			Collection<Integer> colecaoEsferaPoder, Collection<Integer> colecaoCategoria) throws ErroRepositorioException {
		Integer retorno = null;
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta = "";
		
		try {
			
		
		consulta =  "select count(*) as quantidade from faturamento.conta conta "
				+   "inner join cadastro.cliente_imovel clim on clim.imov_id = conta.imov_id "
				+   "inner join cadastro.cliente clie on clie.clie_id = clim.clie_id "
				+   "inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id "
			    + 	"where conta.dcst_idatual = :normal "
			    +   "and conta.cnta_amreferenciabaixacontabil is null " 
		 	    +   "and conta.cnta_amreferenciabaixasocial is null "
		        +   "and conta.cnta_amreferenciaconta between :anoMesInicial and :anoMesFinal "
		 	    +   "and conta.cnta_id not in ( select pag.cnta_id from arrecadacao.pagamento pag where conta.cnta_id = pag.cnta_id ) "
		        +   "and cltp.epod_id in ( :colecaoEsferaPoder ) "
		        +   "and clim.crtp_id = :clienteUsuario and clim.clim_dtrelacaofim is null "
		        +   "and conta.imov_id in (select cnta.imov_id from faturamento.conta cnta "
		        						+ "inner join faturamento.conta_categoria catg on catg.cnta_id = cnta.cnta_id "
		        						+ "where cnta.cnta_amreferenciabaixacontabil is not null " 
		        						+ "and conta.imov_id = cnta.imov_id " 
		        						+ "and cnta.cnta_amreferenciaconta < :anoMesMenosNumeroDeMesesInformados "
		        						+ "and catg.catg_id in ( :colecaoCategoria ) )";

		
		retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade",Hibernate.INTEGER)
				.setInteger("normal", DebitoCreditoSituacao.NORMAL)
				.setShort("clienteUsuario", ClienteRelacaoTipo.USUARIO)
				.setInteger("anoMesInicial", Util.formatarMesAnoComBarraParaAnoMes(anoMesInicial) )
				.setInteger("anoMesFinal", Util.formatarMesAnoComBarraParaAnoMes(anoMesFinal) )
				.setInteger("anoMesMenosNumeroDeMesesInformados", anoMesMenosNumeroDeMesesInformados )
				.setParameterList("colecaoEsferaPoder", colecaoEsferaPoder)
				.setParameterList("colecaoCategoria" , colecaoCategoria)
				.setMaxResults(1).uniqueResult();
		
		
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	
	/**
	 * O sistema verifica se existe contas que atendam os critérios informados para baixa societária
	 * 
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0011] - Validar Critérios Para Perdas Órgãos Públicos
	 * 
	 *  @author Arthur Carvalho
	 *  @date 18/11/2011
	 * 
	 * @param quantidadeMinimaInformada
	 * @param colecaoEsferaPoder
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisaQuantidadeContasBaixaPerdasOrgaoPublico(Integer quantidadeMinimaInformada, Collection<Integer> colecaoEsferaPoder ) 
			throws ErroRepositorioException {
		
		Integer retorno = null;
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		String consulta = "";
		
		try {
			
		
		consulta =  "select count(*) as quantidade from faturamento.conta conta "
				+   "inner join cadastro.cliente_conta  clco on clco.cnta_id = conta.cnta_id "
				+   "inner join cadastro.cliente clie on clie.clie_id = clco.clie_id "
				+   "inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id "
				+   "inner join faturamento.conta_categoria catg on catg.cnta_id = conta.cnta_id "
			    + 	"where conta.dcst_idatual in ( :normal , :retificada , :incluida ) "
			    +   "and clco.crtp_id = :clienteResponsavel "
			    +   "and conta.cnta_amreferenciabaixacontabil is null " 
		 	    +   "and conta.cnta_amreferenciabaixasocial is null "
			    +   "and catg.catg_id = :categoriaPublica "
			    +   "and conta.cnta_id not in ( select pag.cnta_id from arrecadacao.pagamento pag where conta.cnta_id = pag.cnta_id )"
				+   "and abs(MONTHS_BETWEEN ( "
				+	"	to_date(to_char( conta.cnta_dtvencimentoconta , 'MM-DD-YYYY') , 'MM-DD-YYYY'), "
				+	"	to_date(to_char(:dataAtual, 'MM-DD-YYYY'), 'MM-DD-YYYY') "
				+	"	)) "
				+	"	>= :quantidadeMinimaInformada "
		        +   "and cltp.epod_id in ( :colecaoEsferaPoder ) ";
		        
		
		retorno = (Integer) session.createSQLQuery(consulta)
				.addScalar("quantidade",Hibernate.INTEGER)
				.setInteger("normal", DebitoCreditoSituacao.NORMAL)
				.setInteger("retificada", DebitoCreditoSituacao.RETIFICADA)
				.setInteger("incluida", DebitoCreditoSituacao.INCLUIDA)
				.setInteger("categoriaPublica", Categoria.PUBLICO)
				.setInteger("quantidadeMinimaInformada", quantidadeMinimaInformada)
				.setDate("dataAtual",new Date())
				.setShort("clienteResponsavel", ClienteRelacaoTipo.RESPONSAVEL)
				.setParameterList("colecaoEsferaPoder", colecaoEsferaPoder)
				.setMaxResults(1).uniqueResult();
		
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}


	/**
	 * Pesquisa os parâmetros Perdas Societarias por
	 * ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *[SB0004] - Processar Perdas Societárias
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosPerdasSocietarias pesquisarParametrosPerdasSocietarias(Integer anoMesReferenciaContabil) throws ErroRepositorioException {

		ParametrosPerdasSocietarias retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT pps "
					+ "FROM ParametrosPerdasSocietarias pps "
					+ "WHERE pps.anoMesReferenciaContabil = :anoMesReferenciaContabil ";

			retorno = (ParametrosPerdasSocietarias) session.createQuery(consulta)
						.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
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
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void retiraBaixaDasContasPerdasSocietarias(int anoMesReferenciaContabil)	throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		
		try {

			Connection jdbcCon = session.connection();

			update =  "update faturamento.conta set "
				+ "cnta_amreferenciabaixasocial = null , "
				+ "cnta_tmultimaalteracao = ? ,"
				+ "petp_id = null " 
				+ "where petp_id d = ? "
				+ "and cnta_amreferenciabaixacontabil = ? " ; 
			
			st = jdbcCon.prepareStatement(update);
			st.setTimestamp(1, Util.getSQLTimesTemp(new Date()));
			st.setInt(2, PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS);
			st.setInt(3, anoMesReferenciaContabil);
		
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0007] - Excluir Parâmetros Perdas Societárias  
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void deletarParametrosPerdasSocietarias(int anoMesReferenciaContabil) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		try {
			String delete = "delete ParametrosPerdasSocietarias as parametros "
					+ "where parametros.anoMesReferenciaContabil = :anoMesReferencia ";

			session.createQuery(delete).
				setInteger("anoMesReferencia", anoMesReferenciaContabil).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0008] - Excluir Resumo    
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void deletarResumoDevedoresDuvidososPerdasTipo(int anoMesReferenciaContabil, int idPerdasTipo) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		try {
			String delete = "delete ResumoDevedoresDuvidosos as resumo "
					+ "where resumo.anoMesReferenciaContabil = :anoMesReferencia "
					+ "and resumo.perdasTipo = :idPerdasTipo ";

			session.createQuery(delete).
				setInteger("anoMesReferencia", anoMesReferenciaContabil)
				.setInteger("idPerdasTipo", idPerdasTipo)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0010] - Atualizar Conta
	 * 
	 * @author  Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaPerdasSocietarias(int anoMesReferenciaContabil , Integer anoMesInicial, Integer anoMesFinal, Integer anoMesMenosNumeroDeMesesInformados,
			ParametrosPerdasSocietarias parametros, Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();

		PreparedStatement st = null;
		
		String colecaoEsferaPoder = montarColecaoEsferaPoder(parametros);
		String colecaoCategoria = montarColecaoCategoria(parametros);
		
		try {

			Connection jdbcCon = session.connection();
			
		update =	"update faturamento.conta conta "
				+   "set cnta_amreferenciabaixasocial = ? , "
				+   "petp_id = ? "
			    + 	"where " 
				+   "conta.loca_id = ? "
			    +   "and conta.qdra_id = ? "
			    +   "and exists ( select c.imov_id from faturamento.conta c "
						    			+   "inner join cadastro.cliente_imovel clim on clim.imov_id = c.imov_id "
						    			+   "inner join cadastro.cliente clie on clie.clie_id = clim.clie_id "
						    			+   "inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id " 
						    			+   "where c.dcst_idatual = ? "
						    			+   "and conta.imov_id = c.imov_id "
									    +   "and c.cnta_amreferenciabaixacontabil is null " 
								 	    +   "and c.cnta_amreferenciabaixasocial is null "
								        +   "and c.cnta_amreferenciaconta between ? and ? "
								 	    +   "and not exists ( select pag.cnta_id from arrecadacao.pagamento pag where pag.cnta_id = c.cnta_id ) "
								        +   "and clim.crtp_id = ? and clim.clim_dtrelacaofim is null "
								        +   "and cltp.epod_id in ( " + colecaoEsferaPoder + " ) ) "
		        +   "and exists (select cnta.imov_id from faturamento.conta cnta "
		        						+ "inner join faturamento.conta_categoria catg on catg.cnta_id = cnta.cnta_id "
		        						+ "where cnta.cnta_amreferenciabaixacontabil is not null "
		        						+ "and cnta.imov_id = conta.imov_id "
		        						+ "and cnta.cnta_amreferenciaconta < ? "
		        						+ "and catg.catg_id in ( " + colecaoCategoria + " ) )";
			
		
		st = jdbcCon.prepareStatement(update);
		st.setInt(1, anoMesReferenciaContabil);
		st.setInt(2, PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS);
		st.setInt(3, idLocalidade);
		st.setInt(4, idQuadra);
		st.setInt(5, DebitoCreditoSituacao.NORMAL);
		st.setInt(6, anoMesInicial);
		st.setInt(7, anoMesFinal);
		st.setInt(8, ClienteRelacaoTipo.USUARIO);
		st.setInt(9, anoMesMenosNumeroDeMesesInformados);
		
		st.executeUpdate();

		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}

	}
	
	
	
	private String montarColecaoEsferaPoder( ParametrosPerdasSocietarias parametrosPerdasSocietarias ) {
		String colecaoEsferaPoder = "";
		//ColecaoEsferaPoder
		if ( parametrosPerdasSocietarias.getIndicadorEsferaParticular().equals(ConstantesSistema.SIM) ) {
			colecaoEsferaPoder = String.valueOf(EsferaPoder.PARTICULAR) + ",";
		}
		if ( parametrosPerdasSocietarias.getIndicadorEsferaMunicipal().equals(ConstantesSistema.SIM) ) {
			colecaoEsferaPoder = colecaoEsferaPoder + String.valueOf(EsferaPoder.MUNICIPAL) + ",";
		}
		if ( parametrosPerdasSocietarias.getIndicadorEsferaEstadual().equals(ConstantesSistema.SIM )) {
			colecaoEsferaPoder = colecaoEsferaPoder + String.valueOf(EsferaPoder.ESTADUAL) + ",";
		}
		if ( parametrosPerdasSocietarias.getIndicadorEsferaFederal().equals(ConstantesSistema.SIM)) {
			colecaoEsferaPoder = colecaoEsferaPoder + String.valueOf(EsferaPoder.FEDERAL) + ",";
		}
		colecaoEsferaPoder = colecaoEsferaPoder.substring(0, colecaoEsferaPoder.length() - 1);
		return colecaoEsferaPoder;
		
	}
	
	private String montarColecaoCategoria( ParametrosPerdasSocietarias parametrosPerdasSocietarias ) {
		
		String colecaoCategoria = "";
		//ColecaoCategoria
		if ( parametrosPerdasSocietarias.getIndicadorCategoriaResidencial().equals(ConstantesSistema.SIM)) {
			colecaoCategoria =  String.valueOf(Categoria.RESIDENCIAL) + ",";
		}
		if ( parametrosPerdasSocietarias.getIndicadorCategoriaComercial().equals(ConstantesSistema.SIM )) {
			colecaoCategoria =  colecaoCategoria + String.valueOf(Categoria.COMERCIAL) + ",";
		}
		if ( parametrosPerdasSocietarias.getIndicadorCategoriaIndustrial().equals(ConstantesSistema.SIM )) {
			colecaoCategoria =  colecaoCategoria + String.valueOf(Categoria.INDUSTRIAL) + ",";
		}
		if ( parametrosPerdasSocietarias.getIndicadorCategoriaPublica().equals(ConstantesSistema.SIM )) {
			colecaoCategoria =  colecaoCategoria + String.valueOf(Categoria.PUBLICO) + ",";
		}
		colecaoCategoria = colecaoCategoria.substring(0, colecaoCategoria.length() - 1);
		return colecaoCategoria;
	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0015-Excluir Parâmetros Perdas Órgãos Públicos
	 *   
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void deletarParametrosPerdasOrgaoPublico(int anoMesReferenciaContabil) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		
		try {
			String delete = "delete ParametrosPerdasOrgaoPublico as parametros "
					+ "where parametros.anoMesReferenciaContabil = :anoMesReferencia ";

			session.createQuery(delete).
				setInteger("anoMesReferencia", anoMesReferenciaContabil).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}		
	}
	
	/**
	 * Pesquisa os parâmetros Perdas Orgao Publico por ano/mês de referência contábil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0012] - Processar Perdas Órgãos Públicos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosPerdasOrgaoPublico pesquisarParametrosPerdasOrgaoPublico(Integer anoMesReferenciaContabil) throws ErroRepositorioException {

		ParametrosPerdasOrgaoPublico retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT pps "
					+ "FROM ParametrosPerdasOrgaoPublico pps "
					+ "WHERE pps.anoMesReferenciaContabil = :anoMesReferenciaContabil ";

			retorno =(ParametrosPerdasOrgaoPublico) session.createQuery(consulta)
						.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
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
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void retiraBaixaDasContasPerdasOrgaoPublico(int anoMesReferenciaContabil)	throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		
		try {

			Connection jdbcCon = session.connection();

			update =  "update faturamento.conta set "
				+ "cnta_amreferenciabaixacontabil = null , "
				+ "cnta_tmultimaalteracao = ? ,"
				+ "petp_id = null " 
				+ "where petp_id d = ? "
				+ "and cnta_amreferenciabaixacontabil = ? " ; 
			
			st = jdbcCon.prepareStatement(update);
			st.setTimestamp(1, Util.getSQLTimesTemp(new Date()));
			st.setInt(2, PerdasTipo.PERDAS_ORGAO_PUBLICO);
			st.setInt(3, anoMesReferenciaContabil);
		
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0017] - Atualizar Conta Baixa Órgãos Públicos
	 * 
	 * @author  Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaPerdasOrgaoPublico(int anoMesReferenciaContabil, ParametrosPerdasOrgaoPublico parametros,  Integer idLocalidade,
			Integer idQuadra ) 
			throws ErroRepositorioException {

		String update;
		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;
		
		String esferaPoder = "";
		if ( parametros.getIndicadorEsferaFederal().equals(ConstantesSistema.SIM) ) {
			  esferaPoder = " "+ EsferaPoder.FEDERAL ;
			  if ( parametros.getIndicadorEsferaMunicipal().equals(ConstantesSistema.SIM) ) {
				  esferaPoder = esferaPoder + ", "+ EsferaPoder.MUNICIPAL ;  
			  }
	    } else {
			  esferaPoder = " "+ EsferaPoder.MUNICIPAL ;
		}

		try {
			Connection jdbcCon = session.connection();
			
			  update =  "update faturamento.conta cnta "
					+   "set cnta.cnta_amreferenciabaixacontabil = ? , "
					+   "cnta.petp_id = ? "
					+   "where cnta.loca_id = ? "
					+   "and cnta.qdra_id = ? "
					+   "and exists (select conta.cnta_id from faturamento.conta conta "  
											+   "inner join cadastro.cliente_conta clco on clco.cnta_id = conta.cnta_id "
											+   "inner join cadastro.cliente clie on clie.clie_id = clco.clie_id "
											+   "inner join cadastro.cliente_tipo cltp on cltp.cltp_id = clie.cltp_id "
											+   "inner join faturamento.conta_categoria catg on catg.cnta_id = conta.cnta_id "
										    + 	"where conta.dcst_idatual in ( ? , ? , ? ) "
										    +   "and cnta.cnta_id = conta.cnta_id "
										    +   "and clco.crtp_id = ? "
										    +   "and conta.cnta_amreferenciabaixacontabil is null " 
									 	    +   "and conta.cnta_amreferenciabaixasocial is null "
										    +   "and catg.catg_id = ? "
										    +   "and not exists ( select pag.cnta_id from arrecadacao.pagamento pag where pag.cnta_id = conta.cnta_id) "
											+   "and abs(MONTHS_BETWEEN ( "
											+	"	to_date(to_char( conta.cnta_dtvencimentoconta , 'MM-DD-YYYY') , 'MM-DD-YYYY'), "
											+	"	to_date(to_char(sysdate, 'MM-DD-YYYY'), 'MM-DD-YYYY') "
											+	"	)) "
											+	"	>= ? "
									        +   "and cltp.epod_id in ( "+ esferaPoder + " ) )";
			  
			
				
				st = jdbcCon.prepareStatement(update);
				st.setInt(1, anoMesReferenciaContabil);
				st.setInt(2, PerdasTipo.PERDAS_ORGAO_PUBLICO);
				st.setInt(3, idLocalidade);
				st.setInt(4, idQuadra);
				st.setInt(5, DebitoCreditoSituacao.NORMAL);
				st.setInt(6, DebitoCreditoSituacao.RETIFICADA);
				st.setInt(7, DebitoCreditoSituacao.INCLUIDA);
				st.setInt(8, ClienteRelacaoTipo.RESPONSAVEL);
				st.setInt(9, Categoria.PUBLICO);
				st.setInt(10, Integer.valueOf(parametros.getNumeroMesesContasVencidas()));
				
				st.executeUpdate();

				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				} finally {
					if (null != st)
						try {
							st.close();
						} catch (SQLException e) {
							throw new ErroRepositorioException(e, "Erro no Hibernate");
						}
					HibernateUtil.closeSession(session);
				}
	}
	
	/**
	 * Pesquisa Resumo devedores duvidos.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idPerdaTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoDevedoresDuvidosos pesquisarResumoDevedoresDuvidososTipoPerda(Integer anoMesReferenciaContabil, Integer idPerdaTipo) throws ErroRepositorioException {

		ResumoDevedoresDuvidosos retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT pps "
					+ "FROM ResumoDevedoresDuvidosos pps "
					+ "WHERE pps.anoMesReferenciaContabil = :anoMesReferenciaContabil "
					+ "and pps.perdasTipo = :idPerdaTipo ";

			retorno =(ResumoDevedoresDuvidosos) session.createQuery(consulta)
						.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
						.setInteger("idPerdaTipo",idPerdaTipo)
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
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctch_vlagua) as valorAgua, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_catg_hist  ctcg"
			+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id "
			+ " inner join cadastro.categoria  categoria on categoria.catg_id = ctcg.catg_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda"
			+ " and cnta.loca_id = :idLocalidade"
			+ " and cnta.qdra_id = :idQuadra"
			+ " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
			+ " and cnta.cnhi_amreferenciabaixasocial is not null"
			+ " group by categoria.catg_id";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idQuadra", idQuadra)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException{
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctch_vlesgoto) as valorEsgoto, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_catg_hist  ctcg"
			+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id"
			+ " inner join cadastro.categoria  categoria on categoria.catg_id = ctcg.catg_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda "
			+ " and cnta.loca_id = :idLocalidade "
			+ " and cnta.qdra_id = :idQuadra "
			+ " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
			+ " and cnta.cnhi_amreferenciabaixasocial is not null"
			+ " group by categoria.catg_id ";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idQuadra", idQuadra)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			
		
		return retorno;
	}
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			
		consulta = " select   sum(dccg.dcch_vlcategoria) as valorCategoria," 
		         + " dccg.catg_id as categoria"
				 + " from  faturamento.conta_historico  cnta " 
		         + " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
				 + " and cnta.cnhi_amreferenciabaixasocial is not null" 
				 + " and dbcb.fntp_id = :parcelamentoAgua "		
				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelamentoAgua" , FinanciamentoTipo.PARCELAMENTO_AGUA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
		
		consulta = " select   sum(dccg.dcch_vlcategoria) as valorCategoria," 
		         + " 		  dccg.catg_id as categoria"
		         + " from  faturamento.conta_historico  cnta " 
		         + " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and dbcb.fntp_id = :parcelamentoEsgoto"	
				 + " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
				 + " and cnta.cnhi_amreferenciabaixasocial is not null" 
				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelamentoEsgoto" , FinanciamentoTipo.PARCELAMENTO_ESGOTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra) 
			throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao, "//1
				+ " 		  lict.lict_id as financiamentoTipo, "//2
				+ " 		  dccg.catg_id as categoria "//3
				+ " from faturamento.conta_historico  cnta "
				+ " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ "  inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id in( :parcelamentoServico , :entradaParcelamento) "		
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelamentoServico", FinanciamentoTipo.PARCELAMENTO_SERVICO)
					.setInteger("entradaParcelamento", FinanciamentoTipo.ENTRADA_PARCELAMENTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria,"
				+ "          dccg.catg_id as categoria"
				+ " from faturamento.conta_historico  cnta "
				+ " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id "
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id = :jurosParcelamento  "
				+ " group by dccg.catg_id ";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("jurosParcelamento" , FinanciamentoTipo.JUROS_PARCELAMENTO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  dccg.catg_id as categoria"//3
				+ " from faturamento.conta_historico  cnta  "
				+ " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id "
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id  in ( :servicoNormal, :arrastoAgua, :arrastoEsgoto, :arrastoServico) "
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("servicoNormal" , FinanciamentoTipo.SERVICO_NORMAL)
					.setInteger("arrastoAgua" , FinanciamentoTipo.ARRASTO_AGUA)
					.setInteger("arrastoEsgoto" , FinanciamentoTipo.ARRASTO_ESGOTO)
					.setInteger("arrastoServico" , FinanciamentoTipo.ARRASTO_SERVICO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		consulta= " select   sum(crcg.crch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  crcg.catg_id as categoria"//3
				+ " from faturamento.cred_realizado_catg_hist  crcg"
				+ " inner join faturamento.cred_realizado_hist  crrz on crrz.crhi_id = crcg.crhi_id"
				+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = crrz.cnta_id"
				+ " inner join arrecadacao.pagamento_historico a on a.cnta_id = cnta.cnta_id "
				+ " inner join financeiro.lancamento_item_contabil  lict on crrz.lict_id = lict.lict_id"
				+ " inner join financeiro.lancamento_item  lcit on lict.lcit_id = lcit.lcit_id" 								
				+ " where "
				+ " cnta.petp_id = :idTipoPerda"
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and a.pghi_amreferenciaarrecadacao  = :anoMesReferencia"
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , crcg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idQuadra", idQuadra)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctch_vlagua) as valorAgua, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_catg_hist  ctcg"
			+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cadastro.categoria  categoria on categoria.catg_id = ctcg.catg_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda"
			+ " and cnta.loca_id = :idLocalidade"
			+ " and cnta.qdra_id = :idQuadra"
			+ " and cnta.dcst_idatual = :retificada"
			+ " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "
			+ " and cnta.cnhi_amreferenciabaixasocial is not null"
			+ " group by categoria.catg_id";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idQuadra", idQuadra)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException{
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctch_vlesgoto) as valorEsgoto, "
		    + "            categoria.catg_id as categoria " 
			+ " from faturamento.conta_catg_hist  ctcg"
			+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cadastro.categoria  categoria on categoria.catg_id = ctcg.catg_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda "
			+ " and cnta.loca_id = :idLocalidade "
			+ " and cnta.qdra_id = :idQuadra "
			+ " and cnta.dcst_idatual = :retificada"
			+ " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "
			+ " and cnta.cnhi_amreferenciabaixasocial is not null"
			+ " group by categoria.catg_id ";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idQuadra", idQuadra)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("idLocalidade", idLocalidade)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			
		consulta = " select   sum(dccg.dcch_vlcategoria) as valorCategoria," 
		         + " dccg.catg_id as categoria"
				 + " from  faturamento.conta_historico  cnta " 
				 + " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and cnta.dcst_idatual = :retificada"
				 + " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "
				 + " and cnta.cnhi_amreferenciabaixasocial is not null" 
				 + " and dbcb.fntp_id = :parcelamentoAgua "		
				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("parcelamentoAgua" , FinanciamentoTipo.PARCELAMENTO_AGUA)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
		
		consulta = " select   sum(dccg.dcch_vlcategoria) as valorCategoria," 
		         + " 		  dccg.catg_id as categoria"
		         + " from  faturamento.conta_historico  cnta " 
				 + " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and dbcb.fntp_id = :parcelamentoEsgoto"	
				 + " and cnta.dcst_idatual = :retificada"
				 + " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "
				 + " and cnta.cnhi_amreferenciabaixasocial is not null" 
				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelamentoEsgoto" , FinanciamentoTipo.PARCELAMENTO_ESGOTO)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, Integer idLocalidade, 
		Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao, "//1
				+ " 		  lict.lict_id as financiamentoTipo, "//2
				+ " 		  dccg.catg_id as categoria "//3
				+ " from faturamento.conta_historico  cnta "
				+ "  inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ "  inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ "  inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :retificada"
				+ " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id in( :parcelamentoServico , :entradaParcelamento) "		
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelamentoServico", FinanciamentoTipo.PARCELAMENTO_SERVICO)
					.setInteger("entradaParcelamento", FinanciamentoTipo.ENTRADA_PARCELAMENTO)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria,"
				+ "          dccg.catg_id as categoria"
				+ " from faturamento.conta_historico  cnta "
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :retificada"
				+ " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id = :jurosParcelamento  "
				+ " group by dccg.catg_id ";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setInteger("jurosParcelamento" , FinanciamentoTipo.JUROS_PARCELAMENTO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  dccg.catg_id as categoria"//3
				+ " from faturamento.conta_historico  cnta  "
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :retificada"
				+ " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "		
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id  in ( :servicoNormal, :arrastoAgua, :arrastoEsgoto, :arrastoServico) "
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setInteger("servicoNormal" , FinanciamentoTipo.SERVICO_NORMAL)
					.setInteger("arrastoAgua" , FinanciamentoTipo.ARRASTO_AGUA)
					.setInteger("arrastoEsgoto" , FinanciamentoTipo.ARRASTO_ESGOTO)
					.setInteger("arrastoServico" , FinanciamentoTipo.ARRASTO_SERVICO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		consulta= " select   sum(crcg.crch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  crcg.catg_id as categoria"//3
				+ " from faturamento.cred_realizado_catg_hist  crcg"
				+ " inner join faturamento.cred_realizado_hist  crrz on crrz.crhi_id = crcg.crhi_id"
				+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = crrz.cnta_id"
				+ " inner join financeiro.lancamento_item_contabil  lict on crrz.lict_id = lict.lict_id"
				+ " inner join financeiro.lancamento_item  lcit on lict.lcit_id = lcit.lcit_id" 								
				+ " where "
				+ " cnta.petp_id = :idTipoPerda"
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :retificada " 
				+ " and cnta.cnhi_dtretificacao  between :dataInicial and :dataFinal "					
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , crcg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idQuadra", idQuadra)
					.setInteger("retificada", DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)
					.setDate("dataInicial", Util.getSQLDate(Util.gerarDataInicialApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.setDate("dataFinal", Util.getSQLDate(Util.gerarDataApartirAnoMesRefencia(anoMesReferenciaContabil)))
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctch_vlagua) as valorAgua, "
		    + "            ctcg.catg_id as categoria " 
			+ " from faturamento.conta_catg_hist  ctcg"
			+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
			+ " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda"
			+ " and cnta.loca_id = :idLocalidade"
			+ " and cnta.qdra_id = :idQuadra"
			+ " and cnta.dcst_idatual = :parcelada"
			+ " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"
			+ " and cnta.cnhi_amreferenciabaixasocial is not null"
			+ " group by ctcg.catg_id";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorAgua",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idQuadra", idQuadra)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException{
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta = "select sum(ctcg.ctch_vlesgoto) as valorEsgoto, "
		    + "            ctcg.catg_id as categoria " 
			+ " from faturamento.conta_catg_hist  ctcg"
			+ " inner join faturamento.conta_historico  cnta on cnta.cnta_id = ctcg.cnta_id" 
			+ " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
			+ " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
			+ " where"
			+ " cnta.petp_id = :idTipoPerda"
			+ " and cnta.loca_id = :idLocalidade"
			+ " and cnta.qdra_id = :idQuadra"
			+ " and cnta.dcst_idatual = :parcelada"
			+ " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"
			+ " and cnta.cnhi_amreferenciabaixasocial is not null"
			+ " group by ctcg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorEsgoto",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idQuadra", idQuadra)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
			
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			
		consulta = " select   sum(dccg.dcch_vlcategoria) as valorCategoria," 
		         + " dccg.catg_id as categoria"
				 + " from  faturamento.conta_historico  cnta " 
				 + " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
				 + " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
				 + " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and cnta.dcst_idatual = :parcelada"
				 + " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"
				 + " and cnta.cnhi_amreferenciabaixasocial is not null" 
				 + " and dbcb.fntp_id = :parcelamentoAgua "		
				 + " group by dccg.catg_id";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("parcelamentoAgua" , FinanciamentoTipo.PARCELAMENTO_AGUA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
		
		consulta = " select   sum(dccg.dcch_vlcategoria) as valorCategoria," 
		         + " 		  dccg.catg_id as categoria"
		         + " from  faturamento.conta_historico  cnta " 
		         + " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
				 + " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
				 + " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				 + " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				 + " where " 
				 + " cnta.petp_id = :idTipoPerda "
				 + " and cnta.loca_id = :idLocalidade"
				 + " and cnta.qdra_id = :idQuadra"
				 + " and dbcb.fntp_id = :parcelamentoEsgoto"	
				 + " and cnta.dcst_idatual = :parcelada"
				 + " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"
				 + " and cnta.cnhi_amreferenciabaixasocial is not null" 
				 + " group by dccg.catg_id";

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelamentoEsgoto" , FinanciamentoTipo.PARCELAMENTO_ESGOTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, Integer idLocalidade, 
		Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao, "//1
				+ " 		  lict.lict_id as financiamentoTipo, "//2
				+ " 		  dccg.catg_id as categoria "//3
				+ " from faturamento.conta_historico  cnta "
				+ " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
				+ " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :parcelada"
				+ " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id in( :parcelamentoServico , :entradaParcelamento) "		
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("parcelamentoServico", FinanciamentoTipo.PARCELAMENTO_SERVICO)
					.setInteger("entradaParcelamento", FinanciamentoTipo.ENTRADA_PARCELAMENTO)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria,"
				+ "          dccg.catg_id as categoria"
				+ " from faturamento.conta_historico  cnta "
				+ " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
				+ " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :parcelada"
				+ " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id = :jurosParcelamento  "
				+ " group by dccg.catg_id ";
		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("jurosParcelamento" , FinanciamentoTipo.JUROS_PARCELAMENTO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
			
		consulta= " select   sum(dccg.dcch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  dccg.catg_id as categoria"//3
				+ " from faturamento.conta_historico  cnta  "
				+ " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
				+ " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
				+ " inner join faturamento.debito_cobrado_historico  dbcb on dbcb.cnta_id = cnta.cnta_id"
				+ " inner join faturamento.deb_cobrado_catg_hist  dccg on dccg.dbhi_id = dbcb.dbhi_id"
				+ " inner join financeiro.lancamento_item_contabil  lict on dbcb.lict_id = lict.lict_id"
				+ " where "
				+ " cnta.petp_id = :idTipoPerda "
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :parcelada"
				+ " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"			
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " and dbcb.fntp_id  in ( :servicoNormal, :arrastoAgua, :arrastoEsgoto, :arrastoServico) "
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , dccg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.setInteger("servicoNormal" , FinanciamentoTipo.SERVICO_NORMAL)
					.setInteger("arrastoAgua" , FinanciamentoTipo.ARRASTO_AGUA)
					.setInteger("arrastoEsgoto" , FinanciamentoTipo.ARRASTO_ESGOTO)
					.setInteger("arrastoServico" , FinanciamentoTipo.ARRASTO_SERVICO)
					.setInteger("idQuadra", idQuadra)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();
		Collection retorno = null;
		
		String consulta = "";
		
		try {
			
		consulta= " select   sum(crcg.crch_vlcategoria) as valorCategoria," //0
				+ " 		  lict.lict_nnsequenciaimpressao as numeroSequenciaImpressao,"//1
				+ " 		  lict.lict_id as financiamentoTipo,"//2
				+ " 		  crcg.catg_id as categoria"//3
				+ " from faturamento.cred_realizado_catg_hist  crcg"
				+ " inner join faturamento.cred_realizado_hist  crrz on crrz.crhi_id = crcg.crhi_id"
				+ " inner join faturamento.conta_historico cnta on cnta.cnta_id = crrz.cnta_id"
				+ " inner join cobranca.parcelamento_item pait on pait.cnta_id = cnta.cnta_id"
				+ " inner join cobranca.parcelamento parc on parc.parc_id = pait.parc_id"
				+ " inner join financeiro.lancamento_item_contabil  lict on crrz.lict_id = lict.lict_id"
				+ " inner join financeiro.lancamento_item  lcit on lict.lcit_id = lcit.lcit_id" 								
				+ " where "
				+ " cnta.petp_id = :idTipoPerda"
				+ " and cnta.loca_id = :idLocalidade"
				+ " and cnta.qdra_id = :idQuadra"
				+ " and cnta.dcst_idatual = :parcelada"
				+ " and parc.parc_amreferenciafaturamento  = :anoMesReferencia"			
				+ " and cnta.cnhi_amreferenciabaixasocial is not null" 
				+ " group by lict.lict_nnsequenciaimpressao , lict.lict_id , crcg.catg_id " ;

		
			retorno = session.createSQLQuery(consulta)
					.addScalar("valorCategoria",Hibernate.BIG_DECIMAL)
					.addScalar("numeroSequenciaImpressao", Hibernate.SHORT)
					.addScalar("financiamentoTipo", Hibernate.INTEGER)
					.addScalar("categoria", Hibernate.INTEGER)
					.setInteger("idLocalidade", idLocalidade)
					.setInteger("idTipoPerda", PerdasTipo.PROVISAO_PERDAS_SOCIETARIAS)
					.setInteger("idQuadra", idQuadra)
					.setInteger("parcelada", DebitoCreditoSituacao.PARCELADA)
					.setInteger("anoMesReferencia", anoMesReferenciaContabil)
					.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * Obtém os dados do resumo dos devedores duvidosos 
	 * a partir do ano e mês de referência contábil e da localidade.
	 *
	 * [UC0486] - Gerar Lançamentos Contábeis dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho 
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoVolumesConsumidosNaoFaturados(Integer anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT loca.id, lctp.id, lcit.id,  catg.id, SUM(rded.valorItemLancamento) "
					+ "FROM ValorVolumesConsumidosNaoFaturado rded "
					+ "LEFT JOIN rded.localidade loca "
					+ "LEFT JOIN rded.lancamentoTipo lctp "
					+ "LEFT JOIN rded.lancamentoItem lcit "
					+ "LEFT JOIN rded.categoria catg "
					+ "WHERE rded.anoMesReferencia = :anoMesReferenciaContabil " 
					+ "AND loca.id = :idLocalidade "
					+ "GROUP BY loca.id, lctp.id, lcit.id,  catg.id "
					+ "ORDER BY loca.id, lctp.id, lcit.id,  catg.id ";

			retorno = session.createQuery(consulta)
						.setInteger("anoMesReferenciaContabil",anoMesReferenciaContabil)
						.setInteger("idLocalidade",idLocalidade)
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
	 * [UC0841] Gerar Lançamentos Contábeis Volumes Consumidos Não Faturados
	 *
	 * @author Arthur Carvalho
	 * @date 28/11/2011
	 *
	 * @param idCategoria
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilVolumesConsumidosNaoFaturados(Integer idCategoria , Integer idItemLancamento,Integer idTipoLancamento
			) throws ErroRepositorioException{
		
		Object[] retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT ddcp.contaContabilDebito.id,ddcp.contaContabilCredito.id,ddcp.descricaoHistoricoDebito,ddcp.descricaoHistoricoCredito " 
					+ "FROM ValorConsumidoNaoFaturadoParametro ddcp "
					+ "LEFT JOIN ddcp.categoria catg "
					+ "LEFT JOIN ddcp.lancamentoItem lcit "
					+ "LEFT JOIN ddcp.lancamentoTipo lctp "
					+ "WHERE catg.id = :idCategoria AND " 
					+       "lcit.id = :idItemLancamento AND " 
					+		"lctp.id = :idTipoLancamento ";
				
				retorno =(Object[]) session.createQuery(consulta)
						.setInteger("idCategoria",idCategoria)
						.setInteger("idItemLancamento",idItemLancamento)
						.setInteger("idTipoLancamento",idTipoLancamento)
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
	 * Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * @author Erivan Sousa
	 * @date 07/11/2011
	 * @param idLancamentoOrigem
	 * @param dtInicioIntervalo
	 * @param dtFimIntervalo
	 * @return Collection<object>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarGerarIntegracaoContabilidadeJUAZEIRO(String idLancamentoOrigem, String dtInicioIntervalo, String dtFimIntervalo)  throws ErroRepositorioException {
Collection retorno;
		
		Session session = HibernateUtil.getSession();
		StringBuilder consulta = new StringBuilder();
		try{
			consulta.append("SELECT resReceita.rrec_dtrealizada as dataRealizada, ");
			consulta.append("sum(resReceita.rrec_vlreceita) as valor, ");
			consulta.append("contContabil.cnct_nnprefixocontabil as codigoFicha, ");
			consulta.append("contContabil.cnct_nnterceiros as codigoEvento, ");
			consulta.append("cntBancaria.ctbc_nncontacontabil as codigoAgente ");
			
			consulta.append("FROM financeiro.resumo_receita resReceita ");
			consulta.append("INNER JOIN financeiro.conta_contabil contContabil ON contContabil.cnct_id = resReceita.cnct_id ");
			consulta.append("INNER JOIN arrecadacao.conta_bancaria cntBancaria ON cntBancaria.ctbc_id = resReceita.ctbc_id ");
			
			consulta.append("AND resReceita.rrec_dtrealizada BETWEEN :dtInicioIntervalo AND :dtFimIntervalo ");
			consulta.append("GROUP BY resReceita.rrec_dtrealizada, contContabil.cnct_nnprefixocontabil, contContabil.cnct_nnterceiros,  cntBancaria.ctbc_nncontacontabil ");
			consulta.append("ORDER BY resReceita.rrec_dtrealizada, cntBancaria.ctbc_nncontacontabil ");
			
		retorno = session.createSQLQuery(consulta.toString())
					.addScalar("dataRealizada", Hibernate.DATE)
					.addScalar("valor", Hibernate.BIG_DECIMAL)
					.addScalar("codigoFicha", Hibernate.STRING)
					.addScalar("codigoEvento", Hibernate.STRING)
					.addScalar("codigoAgente", Hibernate.STRING)
//					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
					.setDate("dtInicioIntervalo", new SimpleDateFormat("dd/MM/yyyy").parse(dtInicioIntervalo))
					.setDate("dtFimIntervalo", new SimpleDateFormat("dd/MM/yyyy").parse(dtFimIntervalo))
					.list();
			
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		}catch(ParseException e){
			// exception lancada pelo parse de SimpleDateFormat
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		}catch(NumberFormatException e){
			// Exception lancada pelo construtor de Integer
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		}finally {
			// fecha a sessão
			HibernateUtil.closeSession(session);
		}
	
		return retorno;
	}

	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * 
	 * Obtem o valor do item
	 * 
	 * @author Erivan Sousa
	 * @date 30/01/2012
	 *
	 * @param percPis
	 * @param lancTipo
	 * @param lancItem
	 * @return BigDecimal
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorItemArquivoEFDPisConfins(Collection<Integer> lancTipo, 
			Collection<Integer> lancItem, int anoMesReferencia) throws ErroRepositorioException{
		//objeto de retorno
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		
		StringBuilder query = new StringBuilder();
				
		try {
			//Monta a query
			query.append("select coalesce(sum(refat.rfat_vlitemfaturamento), 0) as vl_pis ");
			query.append("from financeiro.resumo_faturamento refat ");
			query.append("where refat.rfat_amreferencia = :anoMesReferencia ");
			
			if(lancItem != null){
				query.append("and refat.lcit_id in (:lancItem) ");
			}
			if(lancTipo != null){
				query.append("and refat.lctp_id in (:lancTipo) ");
			}			
			
			//Executa a query
			 SQLQuery result =  (SQLQuery) session.createSQLQuery(query.toString())
					.addScalar("vl_pis", Hibernate.BIG_DECIMAL);
			 
			 if(lancItem != null){
				 result.setParameterList("lancItem", lancItem);
			 }
			 if(lancTipo != null){
				 result.setParameterList("lancTipo", lancTipo);
			 }
					
			result.setInteger("anoMesReferencia", anoMesReferencia);
	
			retorno = (BigDecimal) result.setMaxResults(1).uniqueResult();
			 
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}
	
	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * [SB0005] - Gerar registro F600 do EFD-PIS/Confins 
	 * 
	 * Obtem os dados para gerar o arquivo F600
	 * 
	 * @author Erivan Sousa
	 * @date 01/02/2012
	 *
	 * @return Collection
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosArquivoEFDPisConfinsF600(Integer anoMesReferencia, Municipio municipio) throws ErroRepositorioException{
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		StringBuilder consulta = new StringBuilder();
		SQLQuery result = null;
		
		try{
			
			consulta.append(" select cliente.clie_nncnpj as cnpj,");
			consulta.append(" sum(coalesce(conta.cnhi_vlagua,0) + coalesce(conta.cnhi_vlesgoto,0) + ");
			consulta.append(" coalesce(conta.cnhi_vldebitos, 0) - coalesce(conta.cnhi_vlcreditos, 0)) as Valor_Conta,");
			consulta.append(" sum(coalesce(conta.cnhi_vlimpostos,0)) as valor_retencoes_conta,");
			consulta.append(" coalesce(totalimpostos.totalpis,0) as Valor_Retencao_Pis,");
			consulta.append(" coalesce(totalimpostos.totalcofins,0) as Valor_Retencao_Cofins");
			consulta.append(" from arrecadacao.pagamento_historico pagamento");
			consulta.append(" inner join faturamento.conta_historico conta");
			consulta.append(" on conta.cnta_id          = pagamento.cnta_id");
			consulta.append(" and conta.cnhi_vlimpostos > 0");
			consulta.append(" inner join cadastro.cliente_conta_historico clicont");
			consulta.append(" on clicont.cnta_id = conta.cnta_id");
			consulta.append(" inner join cadastro.cliente cliente");
			consulta.append(" on cliente.clie_id = clicont.clie_id");
			if(municipio != null){
				consulta.append(" inner join cadastro.localidade localidade on localidade.loca_id = pagamento.loca_id ");
			}
			consulta.append(" left join");
			consulta.append(" (select cnta_id,");
			consulta.append(" sum(");
			consulta.append(" case");
			consulta.append(" when (cimpded.imtp_id = :impTpCof)");
			consulta.append(" then coalesce(cimpded.cidh_vlimposto,0)");
			consulta.append(" else 0");
			consulta.append(" end) as totalcofins,");
			consulta.append(" sum(");
			consulta.append(" case");
			consulta.append(" when (cimpded.imtp_id = :impTpPis)");
			consulta.append(" then coalesce(cimpded.cidh_vlimposto,0)");
			consulta.append(" else 0");
			consulta.append(" end) as totalpis");
			consulta.append(" from faturamento.conta_impostos_dedz_hist cimpded");
			consulta.append(" where cimpded.imtp_id in (:impTpCof, :impTpPis)");
			consulta.append(" group by cnta_id");
			consulta.append(" ) totalimpostos");
			consulta.append(" on totalimpostos.cnta_id                     = conta.cnta_id");
			consulta.append(" where pagamento.pghi_amreferenciaarrecadacao = :anoMesReferencia");
			consulta.append(" and pagamento.pgst_idatual                   = :pagSit");
			consulta.append(" and clicont.crtp_id                          = :cliRelTipo");
			if(municipio != null){
				consulta.append(" and localidade.muni_idprincipal = :idMunicipio ");
			}
			consulta.append(" and cliente.clie_nncnpj is not null");
			consulta.append(" group by cliente.clie_nncnpj, totalimpostos.totalpis, totalimpostos.totalcofins");
			consulta.append(" order by cliente.clie_nncnpj");
			
			result = (SQLQuery) session.createSQLQuery(consulta.toString())
					.addScalar("cnpj", Hibernate.STRING)
					.addScalar("Valor_Conta", Hibernate.BIG_DECIMAL)
					.addScalar("valor_retencoes_conta", Hibernate.BIG_DECIMAL)
					.addScalar("Valor_Retencao_Pis", Hibernate.BIG_DECIMAL)
					.addScalar("Valor_Retencao_Cofins", Hibernate.BIG_DECIMAL)
					.setInteger("impTpPis", ImpostoTipo.PIS_PASEP)
					.setInteger("impTpCof", ImpostoTipo.COFINS)
					.setInteger("anoMesReferencia", anoMesReferencia)
					.setInteger("pagSit", PagamentoSituacao.PAGAMENTO_CLASSIFICADO)
//					.setInteger("categId", Categoria.PUBLICO)
					.setInteger("cliRelTipo", ClienteRelacaoTipo.RESPONSAVEL);
			
			if(municipio != null){
				result.setInteger("idMunicipio", municipio.getId());
			}
			
			retorno = result.list();
			
			
		}catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		}finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] ? Gerar registro C600 do EFD-PIS/Confins
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @return Collection<Municipio>
	 * @throws ErroRepositorioException
	 */
	public Collection<Municipio> obterMunicipiosGerarArquivoEFDPisConfins(Integer anoMesReferencia) throws ErroRepositorioException{

		Collection<Municipio> retorno;
	
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "SELECT distinct muni " 
					+ "FROM ResumoFaturamento rfat "
					+ "INNER JOIN rfat.localidade loca "
					+ "INNER JOIN loca.municipio muni "
					+ "WHERE rfat.anoMesReferencia = :anoMesReferencia " 
					+ "ORDER BY muni.id ";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMesReferencia",anoMesReferencia)
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
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] ? Gerar registro C600 do EFD-PIS/Confins
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @param idMunicipio
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterQuantidadeContasResumoFaturamentoSimulacao(Integer anoMesReferencia, Integer idMunicipio) 
		throws ErroRepositorioException{

		Integer retorno;
	
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "SELECT sum(rfts.quantidadeContas) " 
					+ "FROM ResumoFaturamentoSimulacao rfts "
					+ "INNER JOIN rfts.localidade loca "
					+ "INNER JOIN loca.municipio muni "
					+ "WHERE rfts.anoMesReferencia = :anoMesReferencia AND muni.id = :idMunicipio ";
			
			retorno = (Integer) session.createQuery(consulta)
					.setInteger("anoMesReferencia",anoMesReferencia)
					.setInteger("idMunicipio", idMunicipio).setMaxResults(1).uniqueResult();
			
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
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] ? Gerar registro C600 do EFD-PIS/Confins
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterQuantidadeContasResumoFaturamentoSimulacao(Integer anoMesReferencia) 
		throws ErroRepositorioException{

		Integer retorno;
	
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "SELECT sum(rfts.quantidadeContas) " 
					+ "FROM ResumoFaturamentoSimulacao rfts "
					+ "WHERE rfts.anoMesReferencia = :anoMesReferencia ";
			
			retorno = (Integer) session.createQuery(consulta)
					.setInteger("anoMesReferencia",anoMesReferencia).setMaxResults(1).uniqueResult();
			
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
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * 
	 * Obtem o valor do item
	 * 
	 * @author Erivan Sousa
	 * @date 30/01/2012
	 *
	 * @param percPis
	 * @param lancTipo
	 * @param lancItem
	 * @return BigDecimal
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorItemArquivoEFDPisConfins(Collection<Integer> lancTipo, 
			Collection<Integer> lancItem, int anoMesReferencia, Integer idMunicipio) throws ErroRepositorioException{
		//objeto de retorno
		BigDecimal retorno = null;

		// cria uma sessão com o hibernate
		Session session = HibernateUtil.getSession();
		
		StringBuilder query = new StringBuilder();
				
		try {
			//Monta a query
			query.append("select coalesce(sum(refat.rfat_vlitemfaturamento), 0) as vl_pis ");
			query.append("from financeiro.resumo_faturamento refat ");
			query.append("INNER JOIN cadastro.localidade loca on refat.loca_id = loca.loca_id ");
			query.append("INNER JOIN cadastro.municipio muni on loca.muni_idprincipal = muni.muni_id ");
			query.append("where refat.rfat_amreferencia = :anoMesReferencia and muni.muni_id = :idMunicipio ");
			
			if(lancItem != null){
				query.append("and refat.lcit_id in (:lancItem) ");
			}
			if(lancTipo != null){
				query.append("and refat.lctp_id in (:lancTipo) ");
			}			
			
			//Executa a query
			 SQLQuery result =  (SQLQuery) session.createSQLQuery(query.toString())
					.addScalar("vl_pis", Hibernate.BIG_DECIMAL);
			 
			 if(lancItem != null){
				 result.setParameterList("lancItem", lancItem);
			 }
			 if(lancTipo != null){
				 result.setParameterList("lancTipo", lancTipo);
			 }
					
			result.setInteger("anoMesReferencia", anoMesReferencia);
			result.setInteger("idMunicipio", idMunicipio);
	
			retorno = (BigDecimal) result.setMaxResults(1).uniqueResult();
			 
			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}

		// retorna o resumo de faturamento criado
		return retorno;
	}

	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * [SB0009] - Gerar registro M230 do EFD-PIS/Confins 
	 * [SB0010] - Gerar registro M630 do EFD-PIS/Confins 
	 * 
	 * Obtem os dados para gerar o arquivo M230 e M630
	 * 
	 * @author Erivan Sousa
	 * @date 06/03/2012
	 *
	 * @return Collection
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosArquivoEFDPisConfinsM230_M630(Integer anoMesReferencia, Municipio municipio) throws ErroRepositorioException{
		Collection retorno = null;
		
		Session session = HibernateUtil.getSession();
		StringBuilder consulta = new StringBuilder();
		SQLQuery result = null;
		
		try{
			consulta.append("select cli.clie_nncnpj as cnpj, ");
			
			consulta.append("sum( coalesce( ");
			consulta.append("case ");
			consulta.append("when rfa.lctp_id = :lanctipo ");//lancamentoTipo = 12
			consulta.append("and rfa.lcit_id  = :lancitem ");//lancamentoItem = 76
			consulta.append("then rfa.rfaa_vlitemfaturamento ");
			consulta.append("end, 0)) as vl_vend, ");
			
			consulta.append("sum(coalesce( ");
			consulta.append("(select sum(pgmt.pgmt_vlpagamento) ");
			consulta.append("from arrecadacao.pagamento pgmt ");
			consulta.append("where pgmt.imov_id                  = rfa.imov_id ");
			consulta.append("and pgmt.pgmt_amreferenciapagamento = rfa.rfaa_amreferenciafaturamento ");
			consulta.append("), 0) + coalesce( ");
			consulta.append("(select sum(pghi.pghi_vlpagamento) ");
			consulta.append("from arrecadacao.pagamento_historico pghi ");
			consulta.append("where pghi.imov_id                  = rfa.imov_id ");
			consulta.append("and pghi.pghi_amreferenciapagamento = rfa.rfaa_amreferenciafaturamento ");
			consulta.append("), 0)) as valor_n_rec ");
			
			consulta.append("from faturamento.res_fat_anal rfa ");
			consulta.append("inner join cadastro.cliente_imovel cliMov ");
			consulta.append("on climov.imov_id = rfa.imov_id ");
			consulta.append("inner join cadastro.cliente cli ");
			consulta.append("on climov.clie_id    = cli.clie_id ");
			
			//caso tenha informado o municipio faz join com localidade
			if(municipio != null){
				consulta.append("inner join cadastro.localidade localidade ");
				consulta.append("on localidade.loca_id    = rfa.loca_id ");
			}
			
			consulta.append("where rfa.catg_id    = :categoria ");//catg_id = 4
			consulta.append("and cli.clie_nncnpj is not null ");
			consulta.append("and rfaa_amreferenciafaturamento = :amReferencia ");//anoMes informado
			consulta.append("and climov.crtp_id = :cliRelTp ");//cliente relacao tipo
			consulta.append("and climov.clim_dtrelacaofim is null ");//data de fim de relacao do cliente 
			
			//caso tenha informado municipio compara o id do municipio com muni_idprincipal em localidade
			if(municipio != null){
				consulta.append("and localidade.muni_idprincipal = :idMunicipio ");
			}
			
			consulta.append("group by cli.clie_nncnpj ");
			consulta.append("order by cli.clie_nncnpj ");
			
			result = (SQLQuery) session.createSQLQuery(consulta.toString())
					.addScalar("cnpj", Hibernate.STRING)
					.addScalar("vl_vend", Hibernate.BIG_DECIMAL)
					.addScalar("valor_n_rec", Hibernate.BIG_DECIMAL)
					.setInteger("lanctipo", LancamentoTipo.RECEITA_LIQUIDA_INT)
					.setInteger("lancitem", LancamentoItem.TOTAL)
					.setInteger("categoria", Categoria.PUBLICO)
					.setInteger("amReferencia", anoMesReferencia)
					.setInteger("cliRelTp", ClienteRelacaoTipo.RESPONSAVEL);
			
			if(municipio != null){
				result.setInteger("idMunicipio", municipio.getId());
			}
			
			retorno = result.list();
			
			
		}catch (HibernateException e) {
			// levanta a exceção para a próxima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		
		}finally {
			// fecha a sessão com o hibernate
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
	
	/**
	 *
	 *
	 * @author Rodrigo Cabral
	 * @date 17/10/2012
	 *
	 * @throws ErroRepositorioException
	 */	
	public void removerLancamentosContabeisEItens(Integer idLocalidade, Integer anoMesReferenciaContabil, Integer lancamentoOrigem)
		throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		try {
			String deleteLancamentoItem = "delete LancamentoContabilItem as lcti "
					+ "where lcti.lancamentoContabil in (select lcnb.id from LancamentoContabil lcnb " 
					+ "where lcnb.localidade = :idLocalidade and lcnb.anoMes = :anoMesReferencia and lcnb.lancamentoOrigem = :lancamentoOrigem) ";

			session.createQuery(deleteLancamentoItem).
				setInteger("anoMesReferencia", anoMesReferenciaContabil)
				.setInteger("idLocalidade", idLocalidade)
				.setInteger("lancamentoOrigem", lancamentoOrigem).executeUpdate();
			
			String deleteLancamento = "delete LancamentoContabil lcnb " 
					+ "where lcnb.localidade = :idLocalidade and lcnb.anoMes = :anoMesReferencia and lcnb.lancamentoOrigem = :lancamentoOrigem ";

			session.createQuery(deleteLancamento).
				setInteger("anoMesReferencia", anoMesReferenciaContabil)
				.setInteger("idLocalidade", idLocalidade)
				.setInteger("lancamentoOrigem", lancamentoOrigem).executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilAnaliticoTabelaAuxiliar() throws ErroRepositorioException, SQLException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		String sql = "";
		try {
			con = session.connection();
			sql = " SELECT financeiro.sp1_gerar_conta_rec_ctb() ";
			PreparedStatement pstm = con.prepareStatement(sql); 
			pstm.executeQuery();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilAnalitico() throws ErroRepositorioException, SQLException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		String sql = "";
		try {
			con = session.connection();
			sql = " SELECT financeiro.sp2_gerar_conta_rec_ctb() ";
			PreparedStatement pstm = con.prepareStatement(sql); 
			pstm.executeQuery();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilSintetico() throws ErroRepositorioException, SQLException {

		Session session = HibernateUtil.getSession();
		Connection con = null;
		String sql = "";
		try {
			con = session.connection();
			sql = " SELECT financeiro.sp3_gerar_conta_rec_ctb() ";
			PreparedStatement pstm = con.prepareStatement(sql); 
			pstm.executeQuery();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public Boolean pesquisarAnoMesReferencia(Integer anoMesReferenciaContabil)
			throws ErroRepositorioException {
		
		boolean retorno = true;
		ParametrosPerdasFiscaisHelper helper = new ParametrosPerdasFiscaisHelper();
		Session session = HibernateUtil.getSession();
		Query query = null;
		String consulta = null;
		
		try {
			consulta = " SELECT pddu.id " +
					   " FROM ParametrosDevedoresDuvidosos pddu " +
					   " WHERE pddu.anoMesReferenciaContabil = :anoMesReferenciaContabil ";
			
			Collection coll = session
					.createQuery(consulta)
					.setInteger("anoMesReferenciaContabil",
							anoMesReferenciaContabil).list();
			
			if(coll != null && !coll.isEmpty()){
				retorno = false;
			} else{
				retorno = true;
			}
			
			//retorno 

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void removeParametrosPerdasFiscaisItens(int idParametrosPerdasFiscais)
			throws ErroRepositorioException{
			
			Session session = HibernateUtil.getSession();
			try {
				String delete = "delete ParametrosDevedoresDuvidososItem as item "
						+ "where item.parametrosDevedoresDuvidosos.id = :idParametrosPerdasFiscais ";

				session.createQuery(delete).
					setInteger("idParametrosPerdasFiscais", idParametrosPerdasFiscais).executeUpdate();

			} catch (HibernateException e) {
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}		
		}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorABaixarParametrosDevedoresDuvidosos(Integer idParametrosDevedoresDuvidosos, BigDecimal valorABaixar)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		ParametrosPerdasFiscaisHelper retornoConsulta = null;
		String atualizarValorABaixar;
		Query query = null;
		try {

			atualizarValorABaixar = " UPDATE ParametrosDevedoresDuvidosos " +
									"SET pded_vlvalorabaixar = :valorABaixar, " +
									"pded_tmultimaalteracao = :ultimaAlteracao " + 
									" WHERE pded_id = :idParametrosDevedoresDuvidosos ";

			query = session.createQuery(atualizarValorABaixar)
					.setInteger("idParametrosDevedoresDuvidosos", idParametrosDevedoresDuvidosos)
					.setBigDecimal("valorABaixar", valorABaixar)
					.setTimestamp("ultimaAlteracao", new Date());

			query.executeUpdate();

		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 */
	public ParametrosPerdasFiscaisHelper pesquisarParametrosPerdasFiscais(ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper)
			throws ErroRepositorioException{
	
		ParametrosPerdasFiscaisHelper retornoConsulta = null;
				
		Session session = HibernateUtil.getSession();

		Query query = null;
		String consulta = null;
		
		try {
			consulta = " SELECT pddu "+
					   " FROM ParametrosDevedoresDuvidosos pddu" +
					   " WHERE pddu.anoMesReferenciaContabil = :anoMesReferenciaContabil ";
			
			
			ParametrosDevedoresDuvidosos parametrosDevedoresDuvidosos = (ParametrosDevedoresDuvidosos)session.createQuery(consulta)
			.setInteger("anoMesReferenciaContabil",parametrosPerdasFiscaisHelper.getAnoMesReferenciaContabil())
			.setMaxResults(1).uniqueResult();
		
			if(parametrosDevedoresDuvidosos != null && !parametrosDevedoresDuvidosos.equals("")){
				retornoConsulta = new ParametrosPerdasFiscaisHelper();
				retornoConsulta.setIdParametrosPerdasFiscais(parametrosDevedoresDuvidosos.getId());
				retornoConsulta.setAnoMesReferenciaContabil(parametrosDevedoresDuvidosos.getAnoMesReferenciaContabil());
				retornoConsulta.setValorABaixar(parametrosDevedoresDuvidosos.getValorABaixar());
				retornoConsulta.setUltimaAlteracao(parametrosDevedoresDuvidosos.getUltimaAlteracao());
							
			
				String consulta1 = " SELECT pddi " +
					   " FROM ParametrosDevedoresDuvidososItem pddi " +
					   " LEFT JOIN pddi.parametrosDevedoresDuvidosos pddu" +
					   " LEFT JOIN FETCH pddi.cobrancaSituacao cob" +
					   " WHERE pddu.id = :idDevedoresDuvidosos ";
			
				Collection<ParametrosDevedoresDuvidososItem> colecaoParametrosPerdasItensFiscais = session.createQuery(consulta1)
					.setInteger("idDevedoresDuvidosos",parametrosDevedoresDuvidosos.getId()).list() ;
				
				if(colecaoParametrosPerdasItensFiscais != null && !colecaoParametrosPerdasItensFiscais.isEmpty()){
					Collection<ParametrosPerdasFiscaisItensHelper> collParametrosPerdasFiscaisItensHelper = new ArrayList();
					for(ParametrosDevedoresDuvidososItem parametrosDevedoresDuvidososItem : colecaoParametrosPerdasItensFiscais){
						ParametrosPerdasFiscaisItensHelper parametrosPerdasFiscaisItensHelper = new ParametrosPerdasFiscaisItensHelper();
						parametrosPerdasFiscaisItensHelper.setIdParametrosPerdasFiscaisItens(parametrosDevedoresDuvidososItem.getId());
						parametrosPerdasFiscaisItensHelper.setValorLimite(parametrosDevedoresDuvidososItem.getValorLimite());
						parametrosPerdasFiscaisItensHelper.setNumeroMeses(parametrosDevedoresDuvidososItem.getNumeroMeses());
						parametrosPerdasFiscaisItensHelper.setUltimaAlteracao(parametrosDevedoresDuvidososItem.getUltimaAlteracao());
						
						if(parametrosDevedoresDuvidososItem.getCobrancaSituacao()!=null){
							parametrosPerdasFiscaisItensHelper.setSituacaoCobranca(parametrosDevedoresDuvidososItem.getCobrancaSituacao().getId());
							parametrosPerdasFiscaisItensHelper.setDescricaoSituacaoCobranca(parametrosDevedoresDuvidososItem.getCobrancaSituacao().getDescricao());
						}
						
						collParametrosPerdasFiscaisItensHelper.add(parametrosPerdasFiscaisItensHelper);
					}
					retornoConsulta.setCollParametrosPerdasFiscaisItensHelper(collParametrosPerdasFiscaisItensHelper);
				}
			
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retornoConsulta;
	}
	
	
	/** Este metódo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integração para a Contabilidade
	 * [SB0008] Gera arquivo txt para a CAER
	 *
	 * @author Hugo Azevedo
	 * @date 28/03/2014
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaer(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException{
		Collection retorno;
		
		Session session = HibernateUtil.getSession();
		String consulta;
		try{
			consulta = "select  lcor.numeroCartao ,"                                               //0
					   + " case "																   	
					   + " 	when lcti.indicadorDebitoCredito = 2 then cntc.numeroConta "
					   + " end,"																   //1
					   + " case "																   	
					   + " 	when lcti.indicadorDebitoCredito = 1 then cntc.numeroConta "
					   + " end,"																   //2
					   + " lcti.valorLancamento,"												   //3
					   + " case "
					   + " 	when lcti.indicadorDebitoCredito = 1 then lcor.numeroHistoricoCredito "
					   + "  else lcor.numeroHistoricoDebito "
					   + " end,"																   //4	
					   + " lcti.descricaoHistorico,"											   //5
					   + " loca.descricao,"														   //6
					   + " case "
					   + "  when lcti.indicadorDebitoCredito = 1 then loca.codigoCentroCusto "
					   + " end "																   //7
					   + " from LancamentoContabilItem lcti " // lançamento contabil item
					   + " left join lcti.lancamentoContabil lcnb" //lançamento contábil
					   + " left join lcnb.localidade loca" //localidade
					   + " left join lcnb.lancamentoOrigem lcor" //lançamento origem
					   + " left join lcti.contaContabil cntc " //conta contabil
					   + " where lcnb.anoMes= :anoMes and lcor.id= :idLancamentoOrigem " 				
					   + " order by loca.id, lcnb.lancamentoTipo.id, cntc.numeroConta, lcti.indicadorDebitoCredito desc, lcti.id";
			
			retorno = session.createQuery(consulta)
					.setInteger("anoMes",new Integer(anoMes))
					.setInteger("idLancamentoOrigem",new Integer(idLancamentoOrigem))
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
	 * Este caso de uso gera a integração para a contabilidade
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 * 
	 * Altera a númeração do sequencial no lancamento contábil
	 * 
	 * @author Bruno Barros
	 * @date 10/10/2014
	 * @param idLancamento - Id do lancamento que terá seu sequencial atualizado
	 * @param sequencial - Novo valor do sequencial
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarUltimoSequencialLancamentoContabil(int idLancamento, int sequencial)
			throws ErroRepositorioException {
		
		String update;
		Session session = HibernateUtil.getSession();
		
		// declara o tipo de conexao
		Connection jdbcCon = session.connection();

		PreparedStatement st = null;
	
		try {			
			update = "update financeiro.lancamento_origem set LCOR_NNULTIMOSSEQUENCIAL = ? where lcor_id = ?";
		
			st = jdbcCon.prepareStatement(update);
			st.setInt( 1 , sequencial);
			st.setInt( 2 , idLancamento);			
			
			// executa o update
			st.executeUpdate();
			
			session.flush();
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
}