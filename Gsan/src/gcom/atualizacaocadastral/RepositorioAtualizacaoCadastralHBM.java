package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atualizacaocadastral.bean.MapaQuadraHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public class RepositorioAtualizacaoCadastralHBM implements IRepositorioAtualizacaoCadastral {

	private static IRepositorioAtualizacaoCadastral instancia; 
	
	private RepositorioAtualizacaoCadastralHBM() {}
	
	/**
	 * Retorna o valor de instance
	 * @return O valor de instance
	 */
	public static IRepositorioAtualizacaoCadastral getInstancia() {
		//modelo de implementação do singleton de outros repositorios
//		String dialect = HibernateUtil.getDialect();
//		if (dialect.toUpperCase().contains("ORACLE")) {
//			if (instancia == null) {
//				instancia = new RepositorioImovelHBM();
//			}
//		} else {
//			if (instancia == null) {
//				instancia = new RepositorioImovelPostgresHBM();
//			}
//		}

		//levando em consideração a implementação em um branche separado e a não utilização de mais de um dialeto
		if (instancia == null) instancia = new RepositorioAtualizacaoCadastralHBM();
		return instancia;
	}

	/**
	 * [UC1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [FE0003] - Verificar Falta de Mapa Para Quadra Selecionada
	 * Este método pesquisa os as quadras que não possuem mapas.
	 * 
	 * @param idsQuadras - conjunto das quadras a serem pesquisadas.
	 * @return String - numeros das quadras separados por virgula.
	 */
	public List<String> verificarQuadrasSemMapaPorImoveis(Collection<Integer> idsQuadras) throws Exception {
		List<String> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT DISTINCT qd.qdra_nnquadra AS quadra_sem_mapa " + 
					"FROM cadastro.quadra qd " +
					"LEFT JOIN atualizacaocadastral.mapa_atlz_cad_dm mp " +
					"ON mp.qdra_id = qd.qdra_id " +
					"WHERE qd.qdra_id IN (:idsQuadras) " +
					"AND mp.qdra_id IS NULL " +
					"ORDER BY quadra_sem_mapa ASC ";
					
			retorno = session.createSQLQuery(consulta)
					.addScalar("quadra_sem_mapa", Hibernate.TEXT)
					.setParameterList("idsQuadras", idsQuadras)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
	 * [IT0008] - Baixar Arquivo Texto e Mapas
	 * Retorna uma coleção de tuplas com nome e arquivo map da quadra
	 *
	 * @author Bruno Sá Barreto
	 * @date 27/02/2015
	 *
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @return ArrayList<MapaQuadraHelper> - tuplas com nome e arquivo map da quadra
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<MapaQuadraHelper> pesquisarMapasQuadrasPorLocalidadeSetor(
			Integer idLocalidade, Integer codigoSetorComercial) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		StringBuilder sbQuery = new StringBuilder("");
		ArrayList<MapaQuadraHelper> result = null;
		
		sbQuery.append("SELECT mapa.macd_armapamap map, quadra.qdra_nnquadra nQuadra FROM atualizacaocadastral.mapa_atlz_cad_dm mapa, cadastro.quadra quadra, cadastro.setor_comercial setor ");
		sbQuery.append("WHERE mapa.qdra_id = quadra.qdra_id AND mapa.stcm_id = quadra.stcm_id ");
		sbQuery.append("AND mapa.loca_id = :idLocalidade AND mapa.stcm_id = setor.stcm_id ");
		sbQuery.append("AND setor.loca_id = mapa.loca_id ");
		sbQuery.append("AND setor.stcm_cdsetorcomercial = :codigoSetorComercial ");
		
		try{
			
			Query query = session.createSQLQuery(sbQuery.toString())
				.addScalar("map", Hibernate.BINARY)
				.addScalar("nQuadra", Hibernate.STRING);
			
			if(idLocalidade!=null)query.setInteger("idLocalidade", idLocalidade);
			if(codigoSetorComercial!=null)query.setInteger("codigoSetorComercial", codigoSetorComercial);
			
			Collection<Object[]> rs = query.list();
			if(!Util.isVazioOrNulo(rs)) {
				Iterator<Object[]> iterator = rs.iterator();
				result = new ArrayList<MapaQuadraHelper>();
				while(iterator.hasNext()){
					Object[] atual = iterator.next();
					MapaQuadraHelper mapa = new MapaQuadraHelper();
					mapa.setArquivoQuadra((byte[])atual[0]);
					mapa.setnQuadra((String)atual[1]);
					result.add(mapa);
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return result;
	}

	/**
	 * Método responsável pela pesquisa de imóveis que possuem fotos na atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 22/06/2015
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ImovelAtualizacaoCadastralDM> pesquisarImovelComFotoAtualizacaoCadastralDM(Integer idImovel)
		throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<ImovelAtualizacaoCadastralDM> result = null;
		String consulta = "";

		try {
			consulta = " SELECT imac"
					 + " FROM ImovelAtualizacaoCadastralDM imac"
					 + " LEFT JOIN FETCH imac.parametroTabelaAtualizacaoCadastralDM parametro"
					 + " LEFT JOIN FETCH imac.parametroTabelaAtualizacaoCadastralDM.usuario usuario"
					 + " WHERE imac.idImovel = :idImovel "
					 + " AND EXISTS( SELECT foto.id FROM ImovelFotoAtualizacaoCadastralDM foto WHERE foto.imovelAtualizacaoCadastralDM.id = imac.id )"
					 + " ORDER BY imac.dataVisita DESC ";

			result = session.createQuery(consulta).setInteger("idImovel", idImovel).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return result;
	}

	/**
	 * Método responsável pela pesquisa dos dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 10/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<DadosFinanceirosAtualizacaoCadastralDM> pesquisarDadosFinanceirosAtualizacaoCadastralDM(Integer comando)
		throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<Object[]> result = null;
		List<DadosFinanceirosAtualizacaoCadastralDM> retorno =
				new ArrayList<DadosFinanceirosAtualizacaoCadastralDM>();
		String consulta = "";

		try {
			consulta = "SELECT leit.usur_id                AS cadastrador, " +
					"ptac.ptac_tmultimaalteracao           AS dataGeracao, " +
					"loca.greg_id                          AS idGerenciaRegional, " +
					"loca.uneg_id                          AS idUnidadeNegocio, " +
					"loca.loca_id                          AS idLocalidade, " +
					"imacAntes.imac_cdsetorcomercial       AS codSetorAntes, " +
					"imacAntes.imac_nnquadra               AS numQuadraAntes, " +
					"imacDepois.imov_id                    AS matricula, " +
					"clacAntes.clac_nmcliente              AS nomeClienteAntes, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 1 AND isac.imac_id = imacAntes.imac_id),0) AS qtdeEconomiaResAntes, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 2 AND isac.imac_id = imacAntes.imac_id),0) AS qtdeEconomiaComAntes, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 3 AND isac.imac_id = imacAntes.imac_id),0) AS qtdeEconomiaIndAntes, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 4 AND isac.imac_id = imacAntes.imac_id),0) AS qtdeEconomiaPubAntes, " +
					"imacAntes.last_id                     AS sitAguaAntes, " +
					"imacAntes.lest_id                     AS sitEsgotoAntes, " +
					"imacAntes.logr_id                     AS idLogradouroAntes, " +
					"imacAntes.edrf_id                     AS refEnderecoAntes, " +
					"imacAntes.imac_nnimovel               AS numImovelAntes, " +
					"imacAntes.imac_dscomplementoendereco  AS complementoAntes, " +
					"imacAntes.bair_id                     AS idBairroAntes, " +
					"imacAntes.imac_cdcep                  AS codCEPAntes, " +
					"clacAntes.clac_nnrg                   AS numRGAntes, " +
					"clacAntes.clac_nncpfcnpj              AS numCPFCNPJAntes, " +
					"hiacAntes.hiac_nnhidrometro           AS numHidrometroAntes, " +
					"imacDepois.imac_cdsetorcomercial      AS codSetorDepois, " +
					"imacDepois.imac_nnquadra              AS numQuadraDepois, " +
					"clacDepois.clac_nmcliente             AS nomeClienteDepois, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 1 AND isac.imac_id = imacDepois.imac_id),0) AS qtdeEconomiaResDepois, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 2 AND isac.imac_id = imacDepois.imac_id),0) AS qtdeEconomiaComDepois, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 3 AND isac.imac_id = imacDepois.imac_id),0) AS qtdeEconomiaIndDepois, " +
					"COALESCE((SELECT SUM(isac_qteconomia) FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE catg_id = 4 AND isac.imac_id = imacDepois.imac_id),0) AS qtdeEconomiaPubDepois, " +
					"imacDepois.last_id                    AS sitAguaDepois, " +
					"imacDepois.lest_id                    AS sitEsgotoDepois, " +
					"imacDepois.logr_id                    AS idLogradouroDepois, " +
					"imacDepois.edrf_id                    AS refEnderecoDepois, " +
					"imacDepois.imac_nnimovel              AS numImovelDepois, " +
					"imacDepois.imac_dscomplementoendereco AS complementoDepois, " +
					"imacDepois.bair_id                    AS idBairroDepois, " +
					"imacDepois.imac_cdcep                 AS codCEPDepois, " +
					"clacDepois.clac_nnrg                  AS numRGDepois, " +
					"clacDepois.clac_nncpfcnpj             AS numCPFCNPJDepois, " +
					"hiacDepois.hiac_nnhidrometro          AS numHidrometroDepois, " +
					"imacAntes.imac_id                     AS imacAntes, " +
					"imacDepois.imac_id                    AS imacDepois, " +
					"CASE WHEN(NOT EXISTS (SELECT reat.imac_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND matc_id <> 10 AND reat_cdopcaoalteracao IS NULL)) THEN 1 ELSE 2 END AS icImovelFinalizado, " +
					"(SELECT MAX(reat_dtatualizacao) FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND matc_id <> 10 AND reat_cdopcaoalteracao IS NOT NULL) AS dataAlteracao, " +
					"CASE " +
					"  WHEN imacAntes.imac_id IS NULL THEN 2 " +
					"  WHEN EXISTS ( " +
					"	(SELECT isac.scat_id FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE isac.imac_id = imacAntes.imac_id " +
					"	  EXCEPT SELECT isac.scat_id FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE isac.imac_id = imacDepois.imac_id " +
					"	) UNION ALL " +
					"	(SELECT isac.scat_id FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE isac.imac_id = imacDepois.imac_id " +
					"	  EXCEPT SELECT isac.scat_id FROM atualizacaocadastral.imovel_subca_atlz_cad_dm isac WHERE isac.imac_id = imacAntes.imac_id " +
					"	) " +
					"  ) " +
					"  THEN 1 ELSE 2 " +
					"END AS indSubcategoriaAlterada, " +
					"CASE WHEN osacAgua.orse_id IS NOT NULL THEN COALESCE( " +
					"  (SELECT dbac_vldebito FROM faturamento.debito_a_cobrar WHERE DBTP_ID = 82 AND orse_id = osacAgua.orse_id), " +
					"  (SELECT dahi_vldebito FROM faturamento.deb_a_cobrar_hist WHERE DBTP_ID = 82 AND orse_id = osacAgua.orse_id), 0) " +
					"ELSE 0 END AS vlMultaAgua, " +
					"CASE WHEN osacAgua.orse_id IS NOT NULL THEN COALESCE( " +
					"  (SELECT dbac_vldebito FROM faturamento.debito_a_cobrar WHERE DBTP_ID = 16 AND orse_id = osacAgua.orse_id), " +
					"  (SELECT dahi_vldebito FROM faturamento.deb_a_cobrar_hist WHERE DBTP_ID = 16 AND orse_id = osacAgua.orse_id), 0) " +
					"ELSE 0 END AS vlConsumoFraudadoAgua, " +
					"CASE WHEN osacEsgoto.orse_id IS NOT NULL THEN COALESCE( " +
					"  (SELECT dbac_vldebito FROM faturamento.debito_a_cobrar WHERE DBTP_ID = 82 AND orse_id = osacEsgoto.orse_id), " +
					"  (SELECT dahi_vldebito FROM faturamento.deb_a_cobrar_hist WHERE DBTP_ID = 82 AND orse_id = osacEsgoto.orse_id), 0) " +
					"ELSE 0 END AS vlMultaEsgoto, " +
					"CASE WHEN osacEsgoto.orse_id IS NOT NULL THEN COALESCE( " +
					"  (SELECT dbac_vldebito FROM faturamento.debito_a_cobrar WHERE DBTP_ID = 16 AND orse_id = osacEsgoto.orse_id), " +
					"  (SELECT dahi_vldebito FROM faturamento.deb_a_cobrar_hist WHERE DBTP_ID = 16 AND orse_id = osacEsgoto.orse_id), 0) " +
					"ELSE 0 END AS vlConsumoFraudadoEsgoto, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 2  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraCategoria, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 4  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraEconomia, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 3  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraSitAgua, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 9  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraSitEsgoto, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 1  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraCpfCnpj, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 8  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraLogradouro, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraInscricao, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 5  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraHidrometro," +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 10 AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN 2 ELSE 1 END AS icAlteraCliente,  " +
					"imacDepois.imac_tmultimaalteracao AS dataAtualizacaoBatch " +
					"FROM        atualizacaocadastral.param_tab_atl_cad_dm ptac " +
					"INNER JOIN  atualizacaocadastral.imovel_atlz_cadastral_dm  imacDepois ON imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno = 1 AND imacDepois.imac_icatualizado = 1 " +
					"LEFT  JOIN  atualizacaocadastral.cliente_atlz_cadastral_dm clacDepois ON clacDepois.imac_id = imacDepois.imac_id " +
					"LEFT  JOIN  atualizacaocadastral.hid_inst_hist_atl_cad_dm  hiacDepois ON hiacDepois.imac_id = imacDepois.imac_id " +
					"LEFT  JOIN  atualizacaocadastral.imovel_atlz_cadastral_dm  imacAntes  ON imacAntes.ptac_id = ptac.ptac_id AND imacAntes.imac_icdadosretorno = 2 AND imacDepois.imov_id = imacAntes.imov_id " +
					"LEFT  JOIN  atualizacaocadastral.cliente_atlz_cadastral_dm clacAntes  ON clacAntes.imac_id = imacAntes.imac_id " +
					"LEFT  JOIN  atualizacaocadastral.hid_inst_hist_atl_cad_dm  hiacAntes  ON hiacAntes.imac_id = imacAntes.imac_id " +
					"INNER JOIN  cadastro.localidade                            loca       ON ptac.loca_id = loca.loca_id " +
					"INNER JOIN  atualizacaocadastral.arquivo_txt_atlz_cad_dm   atac       ON atac.ptac_id = ptac.ptac_id " +
					"INNER JOIN  micromedicao.leiturista                        leit       ON leit.leit_id = atac.leit_id " +
					"LEFT  JOIN  atualizacaocadastral.ordem_servico_atl_cad_dm  osacAgua   ON osacAgua.imac_id = imacDepois.imac_id AND osacAgua.lgti_id = 1 " +
					"LEFT  JOIN  atualizacaocadastral.ordem_servico_atl_cad_dm  osacEsgoto ON osacEsgoto.imac_id = imacDepois.imac_id AND osacEsgoto.lgti_id = 2 " +
					"WHERE ptac.ptac_id = :comando";
			
			result = session.createSQLQuery(consulta)
					.addScalar("cadastrador", Hibernate.INTEGER)
					.addScalar("dataGeracao", Hibernate.DATE)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("codSetorAntes", Hibernate.INTEGER)
					.addScalar("numQuadraAntes", Hibernate.INTEGER)
					.addScalar("matricula", Hibernate.INTEGER)
					.addScalar("nomeClienteAntes", Hibernate.STRING)
					.addScalar("qtdeEconomiaResAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaComAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaIndAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaPubAntes", Hibernate.INTEGER)
					.addScalar("sitAguaAntes", Hibernate.INTEGER)
					.addScalar("sitEsgotoAntes", Hibernate.INTEGER)
					.addScalar("idLogradouroAntes", Hibernate.INTEGER)
					.addScalar("refEnderecoAntes", Hibernate.INTEGER)
					.addScalar("numImovelAntes", Hibernate.STRING)
					.addScalar("complementoAntes", Hibernate.STRING)
					.addScalar("idBairroAntes", Hibernate.INTEGER)
					.addScalar("codCEPAntes", Hibernate.INTEGER)
					.addScalar("numRGAntes", Hibernate.STRING)
					.addScalar("numCPFCNPJAntes", Hibernate.STRING)
					.addScalar("numHidrometroAntes", Hibernate.STRING)
					.addScalar("codSetorDepois", Hibernate.INTEGER)
					.addScalar("numQuadraDepois", Hibernate.INTEGER)
					.addScalar("nomeClienteDepois", Hibernate.STRING)
					.addScalar("qtdeEconomiaResDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaComDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaIndDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaPubDepois", Hibernate.INTEGER)
					.addScalar("sitAguaDepois", Hibernate.INTEGER)
					.addScalar("sitEsgotoDepois", Hibernate.INTEGER)
					.addScalar("idLogradouroDepois", Hibernate.INTEGER)
					.addScalar("refEnderecoDepois", Hibernate.INTEGER)
					.addScalar("numImovelDepois", Hibernate.STRING)
					.addScalar("complementoDepois", Hibernate.STRING)
					.addScalar("idBairroDepois", Hibernate.INTEGER)
					.addScalar("codCEPDepois", Hibernate.INTEGER)
					.addScalar("numRGDepois", Hibernate.STRING)
					.addScalar("numCPFCNPJDepois", Hibernate.STRING)
					.addScalar("numHidrometroDepois", Hibernate.STRING)
					.addScalar("icImovelFinalizado", Hibernate.SHORT)
					.addScalar("dataAlteracao", Hibernate.DATE)
					.addScalar("imacAntes", Hibernate.INTEGER)
					.addScalar("imacDepois", Hibernate.INTEGER)
					.addScalar("indSubcategoriaAlterada", Hibernate.INTEGER)
					.addScalar("vlMultaAgua", Hibernate.BIG_DECIMAL)
					.addScalar("vlConsumoFraudadoAgua", Hibernate.BIG_DECIMAL)
					.addScalar("vlMultaEsgoto", Hibernate.BIG_DECIMAL)
					.addScalar("vlConsumoFraudadoEsgoto", Hibernate.BIG_DECIMAL)
					.addScalar("icAlteraCategoria", Hibernate.SHORT)
					.addScalar("icAlteraEconomia", Hibernate.SHORT)
					.addScalar("icAlteraSitAgua", Hibernate.SHORT)
					.addScalar("icAlteraSitEsgoto", Hibernate.SHORT)
					.addScalar("icAlteraCpfCnpj", Hibernate.SHORT)
					.addScalar("icAlteraLogradouro", Hibernate.SHORT)
					.addScalar("icAlteraInscricao", Hibernate.SHORT)
					.addScalar("icAlteraHidrometro", Hibernate.SHORT)
					.addScalar("icAlteraCliente",  Hibernate.SHORT)
					.addScalar("dataAtualizacaoBatch", Hibernate.DATE)
					.setInteger("comando", comando)
					.list();

			for (Object[] obj : result) {
				retorno.add(new DadosFinanceirosAtualizacaoCadastralDM(obj));
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método responsável por pesquisar o ID da dimensão localização na base do pentaho.
	 * 
	 * @author André Miranda
	 * @since 21/07/2015
	 * @param idLocalidade
	 * @param cdSetorComercial
	 * @param nnQuadra
	 * @return ID da dimensão localização
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLocalizacao(Integer idLocalidade, Integer cdSetorComercial, Integer nnQuadra)
		throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Integer retorno = null;

		try {
			String consulta = "SELECT dmlo_id FROM gerencial.dimen_localizacao " +
					"WHERE loca_id = :idLocalidade " +
					"AND stcm_cdsetorcomercial = :cdSetorComercial " +
					"AND qdra_nnquadra = :nnQuadra " +
					"ORDER BY dmlo_id " +
					"LIMIT 1 ";

			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("dmlo_id", Hibernate.INTEGER)
					.setParameter("idLocalidade", idLocalidade)
					.setParameter("cdSetorComercial", cdSetorComercial)
					.setParameter("nnQuadra", nnQuadra)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Método responsável pela pesquisa do resumo dados financeiros da atualização cadastral.
	 * 
	 * @author André Miranda
	 * @since 22/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List<ResumoDadosFinanceirosAtualizacaoCadastralDM> pesquisarResumoDadosFinanceirosAtualizacaoCadastralDM(Integer comando)
		throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		List<Object[]> result = null;
		List<ResumoDadosFinanceirosAtualizacaoCadastralDM> retorno =
				new ArrayList<ResumoDadosFinanceirosAtualizacaoCadastralDM>();

		try {
			String consulta = "SELECT comando    AS comando, " +
					"cadastrador                 AS cadastrador, " + 
					"dataGeracao                 AS dataGeracao, " + 
					"idGerenciaRegional          AS idGerenciaRegional, " + 
					"idUnidadeNegocio            AS idUnidadeNegocio, " + 
					"idLocalidade                AS idLocalidade, " + 
					"coalesce(codSetorAntes,0)   AS codSetorAntes, " + 
					"coalesce(numQuadraAntes,0)  AS numQuadraAntes, " + 
					"coalesce(codSetorDepois,0)  AS codSetorDepois, " + 
					"coalesce(numQuadraDepois,0) AS numQuadraDepois, " + 
					"sum(qtdeParaVisita)         AS qtdeParaVisita, " + 
					"sum(qtdeAVisitar)           AS qtdeAVisitar, " + 
					"sum(qtdeComPendencia)       AS qtdeComPendencia,  " +
					"sum(qtdeNovos)              AS qtdeNovos, " +
					"sum(qtdeRetornaCampo)       AS qtdeRetornaCampo " +
					"FROM ( " + 
					"SELECT ptac.ptac_id             AS comando, " + 
					"leit.usur_id                    AS cadastrador,  " +
					"ptac.ptac_tmultimaalteracao     AS dataGeracao,  " + 
					"loca.greg_id                    AS idGerenciaRegional, " + 
					"loca.uneg_id                    AS idUnidadeNegocio, " + 
					"loca.loca_id                    AS idLocalidade, " + 
					"imacAntes.imac_cdsetorcomercial AS codSetorAntes, " + 
					"imacAntes.imac_nnquadra         AS numQuadraAntes, " + 
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN  imacAntes.imac_cdsetorcomercial ELSE  imacDepois.imac_cdsetorcomercial END AS codSetorDepois, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN  imacAntes.imac_nnquadra  ELSE imacDepois.imac_nnquadra  END AS numQuadraDepois, " +
					"COUNT(imacAntes.imov_id)        AS qtdeParaVisita, " + 
					"0                               AS qtdeAVisitar,  " +
					"0                               AS qtdeComPendencia, " + 
					"0                               AS qtdeNovos, " +
					"0                               AS qtdeRetornaCampo " +
					"FROM atualizacaocadastral.param_tab_atl_cad_dm ptac " + 
					"INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacAntes ON imacAntes.ptac_id = ptac.ptac_id AND imacAntes.imac_icdadosretorno = 2 " + 
					"LEFT  JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacDepois ON imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno IN (1,3) AND imacAntes.imov_id = imacDepois.imov_id " +
					"INNER JOIN cadastro.localidade loca ON ptac.loca_id = loca.loca_id " + 
					"INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm atac ON atac.ptac_id = ptac.ptac_id " + 
					"INNER JOIN micromedicao.leiturista leit ON leit.leit_id = atac.leit_id " + 
					"WHERE ptac.ptac_id = :comando " + 
					"GROUP BY 1,2,3,4,5,6,7,8,9,10 " +

					"  UNION  " +

					"SELECT ptac.ptac_id             AS comando,  " +
					"leit.usur_id                    AS cadastrador, " + 
					"ptac.ptac_tmultimaalteracao     AS dataGeracao, " + 
					"loca.greg_id                    AS idGerenciaRegional, " + 
					"loca.uneg_id                    AS idUnidadeNegocio, " + 
					"loca.loca_id                    AS idLocalidade, " + 
					"imacAntes.imac_cdsetorcomercial AS codSetorAntes, " + 
					"imacAntes.imac_nnquadra         AS numQuadraAntes, " + 
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN  imacAntes.imac_cdsetorcomercial ELSE  imacDepois.imac_cdsetorcomercial END AS codSetorDepois, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN  imacAntes.imac_nnquadra  ELSE imacDepois.imac_nnquadra  END AS numQuadraDepois, " +
					"0                               AS qtdeParaVisita, " + 
					"COUNT(imacAntes.imov_id)        AS qtdeAVisitar,  " +
					"0                               AS qtdeComPendencia,  " +
					"0                               AS qtdeNovos, " +
					"0                               AS qtdeRetornaCampo " +
					"FROM atualizacaocadastral.param_tab_atl_cad_dm ptac " + 
					"INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacAntes ON imacAntes.ptac_id = ptac.ptac_id AND imacAntes.imac_icdadosretorno = 2 " + 
					"LEFT  JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacDepois ON imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno IN (1,3) AND imacAntes.imov_id = imacDepois.imov_id " +
					"INNER JOIN cadastro.localidade loca ON ptac.loca_id = loca.loca_id  " +
					"INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm atac ON atac.ptac_id = ptac.ptac_id " + 
					"INNER JOIN micromedicao.leiturista leit ON leit.leit_id = atac.leit_id  " +
					"WHERE NOT EXISTS (  " +
					"SELECT imov_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm imacDepois " + 
					"WHERE imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno IN (1,3) AND imacDepois.imac_icimovelnovo = 2 AND imacDepois.imov_id = imacAntes.imov_id) " + 
					"AND ptac.ptac_id = :comando  " +
					"GROUP BY 1,2,3,4,5,6,7,8,9,10 " +

					"UNION  " +

					"SELECT ptac.ptac_id              AS comando,  " +
					"leit.usur_id                     AS cadastrador,  " +
					"ptac.ptac_tmultimaalteracao      AS dataGeracao,  " +
					"loca.greg_id                     AS idGerenciaRegional, " + 
					"loca.uneg_id                     AS idUnidadeNegocio,  " +
					"loca.loca_id                     AS idLocalidade,  " +
					"imacAntes.imac_cdsetorcomercial  AS codSetorAntes,  " +
					"imacAntes.imac_nnquadra          AS numQuadraAntes,  " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN  imacAntes.imac_cdsetorcomercial ELSE  imacDepois.imac_cdsetorcomercial END AS codSetorDepois, " +
					"CASE WHEN EXISTS (SELECT reat_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imacDepois.imac_id AND reat.atac_id = 7  AND matc_id != 10 AND (reat_cdopcaoalteracao = 3 OR reat_cdopcaoalteracao is null)) THEN  imacAntes.imac_nnquadra  ELSE imacDepois.imac_nnquadra  END AS numQuadraDepois, " +
					"0                                AS qtdeParaVisita,  " +
					"0                                AS qtdeAVisitar,  " +
					"COUNT(imacDepois.imov_id)        AS qtdeComPendencia,  " +
					"0                                AS qtdeNovos, " +
					"0                               AS qtdeRetornaCampo " +
					"FROM atualizacaocadastral.param_tab_atl_cad_dm ptac " + 
					"INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacDepois ON imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno IN (1,3) " + 
					"LEFT  JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacAntes ON imacAntes.ptac_id = ptac.ptac_id  AND imacAntes.imac_icdadosretorno = 2 AND imacDepois.imov_id = imacAntes.imov_id " + 
					"INNER JOIN cadastro.localidade loca ON ptac.loca_id = loca.loca_id " + 
					"INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm atac ON atac.ptac_id = ptac.ptac_id " + 
					"INNER JOIN micromedicao.leiturista leit ON leit.leit_id = atac.leit_id " + 
					"WHERE ptac.ptac_id = :comando " +

				    "AND ((imacDepois.imac_icdadosretorno = 3) OR (imacDepois.imac_icatualizado = 2 AND imacDepois.imac_icregistroexcluido = 2 AND imacDepois.imac_cdsituacao = 1) OR " +
				    "     (EXISTS (SELECT reat.imac_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat " +
					"	          WHERE reat.imac_id = imacDepois.imac_id and matc_id <> 10 and reat_cdopcaoalteracao is null))) " +
					"GROUP BY 1,2,3,4,5,6,7,8,9,10 " +

					"  UNION  " +

					"  SELECT ptac.ptac_id              AS comando,  " +
					"leit.usur_id                     AS cadastrador,  " +
					"ptac.ptac_tmultimaalteracao      AS dataGeracao,  " +
					"loca.greg_id                     AS idGerenciaRegional, " + 
					"loca.uneg_id                     AS idUnidadeNegocio,  " +
					"loca.loca_id                     AS idLocalidade,  " +
					"0                                AS codSetorAntes,  " +
					"0                                AS numQuadraAntes,  " +
					"imacDepois.imac_cdsetorcomercial AS codSetorDepois,  " +
					"imacDepois.imac_nnquadra         AS numQuadraDepois,  " +
					"0                                AS qtdeParaVisita,  " +
					"0                                AS qtdeAVisitar,  " +
					"0                                AS qtdeComPendencia,  " +
					"COUNT(imov_id)                   AS qtdeNovos, " +
					"0                                AS qtdeRetornaCampo " +
					"FROM atualizacaocadastral.param_tab_atl_cad_dm ptac " + 
					"INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacDepois ON imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno IN (1,3) AND imacDepois.imac_icimovelnovo = 1 " + 
					"INNER JOIN cadastro.localidade loca ON ptac.loca_id = loca.loca_id  " +
					"INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm atac ON atac.ptac_id = ptac.ptac_id " + 
					"INNER JOIN micromedicao.leiturista leit ON leit.leit_id = atac.leit_id " + 
					"WHERE ptac.ptac_id = :comando  " +
					"GROUP BY 1,2,3,4,5,6,7,8,9,10 " +
					
					"  UNION  " +

					"SELECT ptac.ptac_id              AS comando, " + 
					"leit.usur_id                     AS cadastrador, " +
					"ptac.ptac_tmultimaalteracao      AS dataGeracao, " +
					"loca.greg_id                     AS idGerenciaRegional, " +  
					"loca.uneg_id                     AS idUnidadeNegocio, " +  
					"loca.loca_id                     AS idLocalidade, " +
					"imacAntes.imac_cdsetorcomercial  AS codSetorAntes, " +
					"imacAntes.imac_nnquadra          AS numQuadraAntes, " +
					"imacAntes.imac_cdsetorcomercial  AS codSetorDepois, " + 
					"imacAntes.imac_nnquadra          AS numQuadraDepois, " + 
					"0                                AS qtdeParaVisita, " + 
					"0                                AS qtdeAVisitar, " + 
					"0                                AS qtdeComPendencia, " + 
					"0                                AS qtdeNovos, " +
					"COUNT(imacDepois.imov_id)        AS qtdeRetornaCampo " +  
					"FROM atualizacaocadastral.param_tab_atl_cad_dm ptac  " +
					"INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacDepois ON imacDepois.ptac_id = ptac.ptac_id AND imacDepois.imac_icdadosretorno  = 1 " + 
					"LEFT  JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imacAntes ON imacAntes.ptac_id = ptac.ptac_id  AND imacAntes.imac_icdadosretorno = 2 AND imacDepois.imov_id = imacAntes.imov_id " +  
					"INNER JOIN cadastro.localidade loca ON ptac.loca_id = loca.loca_id  " +
					"INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm atac ON atac.ptac_id = ptac.ptac_id " +  
					"INNER JOIN micromedicao.leiturista leit ON leit.leit_id = atac.leit_id  " +
					"WHERE ptac.ptac_id = :comando " +
					"AND  (imacDepois.imac_icatualizado = 2 AND imacDepois.imac_cdsituacao = 2) " + 
					"GROUP BY 1,2,3,4,5,6,7,8,9,10 " +
					
					") temp  " +
					"GROUP BY 1,2,3,4,5,6,7,8,9,10 ";

			result = session.createSQLQuery(consulta)
					.addScalar("comando", Hibernate.INTEGER)
					.addScalar("cadastrador", Hibernate.INTEGER)
					.addScalar("dataGeracao", Hibernate.DATE)
					.addScalar("idGerenciaRegional", Hibernate.INTEGER)
					.addScalar("idUnidadeNegocio", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("codSetorAntes", Hibernate.INTEGER)
					.addScalar("numQuadraAntes", Hibernate.INTEGER)
					.addScalar("codSetorDepois", Hibernate.INTEGER)
					.addScalar("numQuadraDepois", Hibernate.INTEGER)
					.addScalar("qtdeParaVisita", Hibernate.INTEGER)
					.addScalar("qtdeAVisitar", Hibernate.INTEGER)
					.addScalar("qtdeComPendencia", Hibernate.INTEGER)
					.addScalar("qtdeNovos", Hibernate.INTEGER)
					.addScalar("qtdeRetornaCampo", Hibernate.INTEGER)
					.setInteger("comando", comando).list();

			for (Object[] obj : result) {
				retorno.add(new ResumoDadosFinanceirosAtualizacaoCadastralDM(obj));
			}
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC0000] - Gerar Dados Financeiros da Atualização Cadastral 
	 * @author Vivianne Sousa
	 * @date 23/07/2015
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarParametroTabelaAtualizacaoCadastralNaoFinalizado() throws ErroRepositorioException {
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT ptac.id "
							+ "FROM ParametroTabelaAtualizacaoCadastralDM ptac "
							+ "WHERE ptac.indicadorFinalizado = :indicadorFinalizado ";

			retorno = (Collection<Integer>)session.createQuery(consulta)
					.setShort("indicadorFinalizado", ConstantesSistema.NAO)
					.list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método responsável por excluir os dados do comando na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param comando
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosComando(Integer comando) throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();

		try {
			String consulta = "DELETE DadosFinanceirosAtualizacaoCadastralDM WHERE comando = :comando";
			session.createQuery(consulta)
				.setParameter("comando", comando).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Método responsável por excluir os resumos do comando na base do pentaho.
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param comando
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoDadosComando(Integer comando) throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();

		try {
			String consulta = "DELETE ResumoDadosFinanceirosAtualizacaoCadastralDM WHERE comando = :comando";
			session.createQuery(consulta)
				.setParameter("comando", comando).executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Método responsável por pesquisar o ID da dimensão situação de água na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param idSituacaoLigacaoAgua
	 * @return ID da dimensão situação de água
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdSituacaoAgua(Integer idSituacaoLigacaoAgua)throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Integer retorno = null;
		try {
			String consulta = "SELECT dmsa_id FROM gerencial.dimen_situacao_agua " +
					"WHERE last_id = :idSituacaoLigacaoAgua " +
					"ORDER BY dmsa_id " +
					"LIMIT 1 ";
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("dmsa_id", Hibernate.INTEGER)
					.setParameter("idSituacaoLigacaoAgua", idSituacaoLigacaoAgua)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método responsável por pesquisar o ID da dimensão situação de esgoto na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 23/07/2015
	 * @param idSituacaoLigacaoEsgoto
	 * @return ID da dimensão situação de esgoto
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdSituacaoEsgoto(Integer idSituacaoLigacaoEsgoto) throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Integer retorno = null;
		try {
			String consulta = "SELECT dmse_id FROM gerencial.dimen_situacao_esgoto " +
					"WHERE lest_id = :idSituacaoLigacaoEsgoto " +
					"ORDER BY dmse_id " +
					"LIMIT 1 ";
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("dmse_id", Hibernate.INTEGER)
					.setParameter("idSituacaoLigacaoEsgoto", idSituacaoLigacaoEsgoto)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método responsável por pesquisar o ID da dimensão tempo na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param dataGeracao
	 * @return ID da dimensão tempo
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdTempo(Date dataGeracao) throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Integer retorno = null;
		try {
			String consulta = "SELECT dmtp_id FROM gerencial.dimen_tempo " +
					"WHERE data_string = :dataGeracao " +
					"ORDER BY dmtp_id " +
					"LIMIT 1 ";
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("dmtp_id", Hibernate.INTEGER)
					.setParameter("dataGeracao", Util.recuperaAnoMesDiaDaData(dataGeracao))
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método responsável por pesquisar o ID da dimensão usuário na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param idUsuario
	 * @return ID da dimensão usuário
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdUsuario(Integer idUsuario) throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Integer retorno = null;
		try {
			String consulta = "SELECT dmus_id FROM gerencial.dimen_usuario " +
							"WHERE usur_id = :idUsuario " +
							"ORDER BY dmus_id " +
							"LIMIT 1 ";
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("dmus_id", Hibernate.INTEGER)
					.setParameter("idUsuario", idUsuario)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método responsável por verificar se comando esta finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarComandoFinalizado(Integer comando) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Integer result = null;
		boolean retorno = false;
		String consulta = "";

		try {
			consulta =
					" SELECT distinct(ptac.ptac_id) as id " +
					" FROM       atualizacaocadastral.param_tab_atl_cad_dm     ptac " +
					" INNER JOIN atualizacaocadastral.arquivo_txt_atlz_cad_dm  atac ON atac.ptac_id = ptac.ptac_id AND atac.stac_id = :situacaoTransmissao " +
					" INNER JOIN atualizacaocadastral.imovel_atlz_cadastral_dm imac ON imac.ptac_id = ptac.ptac_id AND imac_icdadosretorno = :ambienteVirtual2 " +
					" 																AND imac_icatualizado = :indicadorAtualizado AND imac_icregistroexcluido = :indicadorRegExcluido " +
					" WHERE " + 
					"     NOT EXISTS (SELECT reat.imac_id FROM atualizacaocadastral.retorno_atlz_cad_dm reat WHERE reat.imac_id = imac.imac_id AND matc_id <> 10 AND reat_cdopcaoalteracao is null) " +
					" AND NOT EXISTS (SELECT imacPre.imac_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm imacPre WHERE imac_icdadosretorno = :ambientePreGSAN AND imacPre.ptac_id = ptac.ptac_id) " +
					" AND NOT EXISTS (SELECT imacVirtual.imac_id FROM atualizacaocadastral.imovel_atlz_cadastral_dm imacVirtual " +
					"                 WHERE imac_icatualizado = 2 AND imac_cdsituacao = 1 AND imac_icregistroexcluido = 2 AND imacVirtual.ptac_id = ptac.ptac_id) " +
					" AND ptac.ptac_id = :comando";

			result = (Integer)session.createSQLQuery(consulta)
					.addScalar("id", Hibernate.INTEGER)
					.setInteger("situacaoTransmissao",SituacaoTransmissaoAtualizacaoCadastralDM.FINALIZADO)
					.setInteger("ambienteVirtual2",ImovelAtualizacaoCadastralDM.AMBIENTE_VIRTUAL_2)
					.setInteger("ambientePreGSAN",ImovelAtualizacaoCadastralDM.AMBIENTE_PRE_GSAN)
					.setShort("indicadorAtualizado", ConstantesSistema.SIM)
					.setShort("indicadorRegExcluido", ConstantesSistema.NAO)
					.setInteger("comando", comando)
					.uniqueResult();
			
			if(result != null){
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
	 * Método responsável por atualizar indicador de comando finalizado.
	 * 
	 * @author Vivianne Sousa
	 * @since 24/07/2015
	 * @param comando
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorComandoFinalizado(Integer comando) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;
		try {
			update = "update ParametroTabelaAtualizacaoCadastralDM as ptac set"
					+ " indicadorFinalizado = :indicadorFinalizado "
					+ " where ptac.id = :comando";
			session.createQuery(update)
					.setInteger("indicadorFinalizado", ConstantesSistema.SIM)
					.setInteger("comando", comando)
					.executeUpdate();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * Método responsável por pesquisar o ID da dimensão tarifa na base do pentaho.
	 * 
	 * @author Vivianne Sousa
	 * @since 31/07/2015
	 * @param idTarifa
	 * @return ID da dimensão tarifa
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdTarifa(Integer idTarifa)throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Integer retorno = null;
		try {
			String consulta = "SELECT dmpt_id FROM gerencial.dimen_perfil_imovel_tarifa " +
					"WHERE cstf_id = :idTarifa " +
					"ORDER BY dmpt_id " +
					"LIMIT 1 ";
			retorno = (Integer) session.createSQLQuery(consulta)
					.addScalar("dmpt_id", Hibernate.INTEGER)
					.setParameter("idTarifa", idTarifa)
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * Método responsável por pesquisar somatório dos dados financeiro da atualização cadastral  
	 * 
	 * @author Vivianne Sousa
	 * @since 26/08/2015
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarDadosFinanceirosAtuCadastral(ResumoDadosFinanceirosAtualizacaoCadastralDM resumo, 
			Map<Integer, Integer> cacheSitAgua, Map<Integer, Integer> cacheSitEsgoto)throws ErroRepositorioException {
		Session session = HibernateUtil.getSessionPentaho();
		Object[] result = null;
		try {
			
			String consulta = "SELECT "
					+ " SUM(CASE WHEN dmsa_idantes  = :dmPotAgua   THEN 1 ELSE 0 END) AS qtdeImovelPotAguaAntes,"
  					+ " SUM(CASE WHEN dmsa_iddepois = :dmPotAgua   THEN 1 ELSE 0 END) AS qtdeImovelPotAguaDepois,"
  					+ " SUM(CASE WHEN dmsa_idantes  = :dmFatAgua   THEN 1 ELSE 0 END) AS qtdeImovelFatAguaAntes,"
  					+ " SUM(CASE WHEN dmsa_iddepois = :dmFatAgua   THEN 1 ELSE 0 END) AS qtdeImovelFatAguaDepois,"
  					+ " SUM(CASE WHEN dmsa_idantes  = :dmLigAgua   THEN 1 ELSE 0 END) AS qtdeImovelLigAguaAntes,"
  					+ " SUM(CASE WHEN dmsa_iddepois = :dmLigAgua   THEN 1 ELSE 0 END) AS qtdeImovelLigAguaDepois,"
  					+ " SUM(CASE WHEN dmsa_idantes  = :dmCorAgua   THEN 1 ELSE 0 END) AS qtdeImovelCorAguaAntes,"
  					+ " SUM(CASE WHEN dmsa_iddepois = :dmCorAgua   THEN 1 ELSE 0 END) AS qtdeImovelCorAguaDepois,"
  					+ " SUM(CASE WHEN dmsa_idantes  = :dmSupAgua   THEN 1 ELSE 0 END) AS qtdeImovelSupAguaAntes,"
  					+ " SUM(CASE WHEN dmsa_iddepois = :dmSupAgua   THEN 1 ELSE 0 END) AS qtdeImovelSupAguaDepois,"
  					+ " SUM(CASE WHEN dmse_idantes  = :dmPotEsgoto THEN 1 ELSE 0 END) AS qtdeImovelPotEsgotoAntes,"
  					+ " SUM(CASE WHEN dmse_iddepois = :dmPotEsgoto THEN 1 ELSE 0 END) AS qtdeImovelPotEsgotoDepois,"
  					+ " SUM(CASE WHEN dmse_idantes  = :dmFatEsgoto THEN 1 ELSE 0 END) AS qtdeImovelFatEsgotoAntes,"
  					+ " SUM(CASE WHEN dmse_iddepois = :dmFatEsgoto THEN 1 ELSE 0 END) AS qtdeImovelFatEsgotoDepois,"
  					+ " SUM(CASE WHEN dmse_idantes  = :dmLigEsgoto THEN 1 ELSE 0 END) AS qtdeImovelLigEsgotoAntes,"
  					+ " SUM(CASE WHEN dmse_iddepois = :dmLigEsgoto THEN 1 ELSE 0 END) AS qtdeImovelLigEsgotoDepois,"
  					+ " SUM(CASE WHEN dmse_idantes  = :dmSupEsgoto THEN 1 ELSE 0 END) AS qtdeImovelSupEsgotoAntes,"
  					+ " SUM(CASE WHEN dmse_iddepois = :dmSupEsgoto THEN 1 ELSE 0 END) AS qtdeImovelSupEsgotoDepois,"
  					+ " SUM(CASE WHEN dfac_qteconomiaresantes  = 0 THEN 0 ELSE 1 END) AS qtdeImovelResAntes,"
  					+ " SUM(CASE WHEN dfac_qteconomiaresdepois = 0 THEN 0 ELSE 1 END) AS qtdeImovelResDepois,"
  					+ " SUM(CASE WHEN dfac_qteconomiacomantes  = 0 THEN 0 ELSE 1 END) AS qtdeImovelComAntes,"
  					+ " SUM(CASE WHEN dfac_qteconomiacomdepois = 0 THEN 0 ELSE 1 END) AS qtdeImovelComDepois,"
  					+ " SUM(CASE WHEN dfac_qteconomiaindantes  = 0 THEN 0 ELSE 1 END) AS qtdeImovelIndAntes,"
  					+ " SUM(CASE WHEN dfac_qteconomiainddepois = 0 THEN 0 ELSE 1 END) AS qtdeImovelIndDepois,"
  					+ " SUM(CASE WHEN dfac_qteconomiapubantes  = 0 THEN 0 ELSE 1 END) AS qtdeImovelPubAntes,"
  					+ " SUM(CASE WHEN dfac_qteconomiapubdepois = 0 THEN 0 ELSE 1 END) AS qtdeImovelPubDepois,"
  					+ " SUM(dfac_qteconomiaresantes)  AS qtdeEconomiaResAntes,"
  					+ " SUM(dfac_qteconomiaresdepois) AS qtdeEconomiaResDepois,"
  					+ " SUM(dfac_qteconomiacomantes)  AS qtdeEconomiaComAntes,"
  					+ " SUM(dfac_qteconomiacomdepois) AS qtdeEconomiaComDepois,"
  					+ " SUM(dfac_qteconomiaindantes)  AS qtdeEconomiaIndAntes,"
  					+ " SUM(dfac_qteconomiainddepois) AS qtdeEconomiaIndDepois,"
  					+ " SUM(dfac_qteconomiapubantes)  AS qtdeEconomiaPubAntes,"
  					+ " SUM(dfac_qteconomiapubdepois) AS qtdeEconomiaPubDepois,"
  					+ " SUM(dfac_vlaguaantes)		  AS vlAguaAntes,"
  					+ " SUM(dfac_vlaguadepois)		  AS vlAguaDepois,"
  					+ " SUM(dfac_vlesgotoantes)		  AS vlEsgotoAntes,"
  					+ " SUM(dfac_vlesgotodepois)	  AS vlEsgotoDepois,"
  					+ " SUM(dfac_vlmulta)			  AS vlMulta,"
  					+ " SUM(dfac_vlconsumofraudado)	  AS vlConsumoFraudado,"
  					+ " SUM(CASE WHEN dfac_icalteracaonome           = 1 THEN 1 ELSE 0 END) AS qtdeAltNome,"
  					+ " SUM(CASE WHEN dfac_icalteracaoendereco       = 1 THEN 1 ELSE 0 END) AS qtdeAltEndereco,"
  					+ " SUM(CASE WHEN dfac_icalteracaocategoria      = 1 THEN 1 ELSE 0 END) AS qtdeAltCategoria,"
  					+ " SUM(CASE WHEN dfac_icalteracaosubcategoria   = 1 THEN 1 ELSE 0 END) AS qtdeAltSubcategoria,"
  					+ " SUM(CASE WHEN dfac_icalteracaoeconomia       = 1 THEN 1 ELSE 0 END) AS qtdeAltEconomia,"
  					+ " SUM(CASE WHEN dfac_icalteracaosituacaoagua   = 1 THEN 1 ELSE 0 END) AS qtdeAltSitAgua,"
  					+ " SUM(CASE WHEN dfac_icalteracaosituacaoesgoto = 1 THEN 1 ELSE 0 END) AS qtdeAltSitEsgoto,"
  					+ " SUM(CASE WHEN dfac_icalteracaohidrometro     = 1 THEN 1 ELSE 0 END) AS qtdeAltHidrometro,"
  					+ " SUM(CASE WHEN dfac_icalteracaorg             = 1 THEN 1 ELSE 0 END) AS qtdeAltRg,"
  					+ " SUM(CASE WHEN dfac_icinclusaorg              = 1 THEN 1 ELSE 0 END) AS qtdeIncRg,"
  					+ " SUM(CASE WHEN dfac_icalteracaocpfcnpj        = 1 THEN 1 ELSE 0 END) AS qtdeAltCPFCNPJ,"
  					+ " SUM(CASE WHEN dfac_icinclusaocpfcnpj         = 1 THEN 1 ELSE 0 END) AS qtdeIncCPFCNPJ,"
  					+ " SUM(CASE WHEN dfac_icexclusaocpfcnpj         = 1 THEN 1 ELSE 0 END) AS qtdeExcCPFCNPJ,"
  					+ " SUM(CASE WHEN dfac_icalteracaonome = 1 OR dfac_icalteracaoendereco = 1     OR dfac_icalteracaosubcategoria = 1" 
      				+ "     OR dfac_icalteracaoeconomia = 1    OR dfac_icalteracaosituacaoagua = 1 OR dfac_icalteracaosituacaoesgoto = 1" 
      				+ "     OR dfac_icalteracaohidrometro = 1  OR dfac_icalteracaorg = 1           OR dfac_icinclusaorg = 1 "
      				+ "     OR dfac_icalteracaocpfcnpj = 1     OR dfac_icinclusaocpfcnpj = 1       OR dfac_icexclusaocpfcnpj = 1 THEN 1 ELSE 0 END) AS qtdeImovelAlt,"
  					+ " SUM(CASE WHEN dfac_icalteracaonome = 2 AND dfac_icalteracaoendereco = 2     AND dfac_icalteracaosubcategoria = 2 "
      				+ "     AND dfac_icalteracaoeconomia = 2   AND dfac_icalteracaosituacaoagua = 2 AND dfac_icalteracaosituacaoesgoto = 2" 
      				+ "     AND dfac_icalteracaohidrometro = 2 AND dfac_icalteracaorg = 2           AND dfac_icinclusaorg = 2 "
      				+ "     AND dfac_icalteracaocpfcnpj = 2    AND dfac_icinclusaocpfcnpj = 2       AND dfac_icexclusaocpfcnpj = 2 THEN 1 ELSE 0 END) AS qtdeImovelSemAlt"    
					+ " FROM gerencial.fato_dados_fin_atlz_cad"
					+ " WHERE "
    				+ "     dmlo_idantes = :dmLocalizacaoAntes" 
					+ " AND dmlo_iddepois = :dmLocalizacaoDepois"
					+ " AND dmus_id = :dmUsuario "
					+ " AND dmtp_id = :dmTempo";

			result = (Object[])session.createSQLQuery(consulta)
					.addScalar("qtdeImovelPotAguaAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelPotAguaDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelFatAguaAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelFatAguaDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelLigAguaAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelLigAguaDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelCorAguaAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelCorAguaDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelSupAguaAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelSupAguaDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelPotEsgotoAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelPotEsgotoDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelFatEsgotoAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelFatEsgotoDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelLigEsgotoAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelLigEsgotoDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelSupEsgotoAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelSupEsgotoDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelResAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelResDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelComAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelComDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelIndAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelIndDepois", Hibernate.INTEGER)
					.addScalar("qtdeImovelPubAntes", Hibernate.INTEGER)
					.addScalar("qtdeImovelPubDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaResAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaResDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaComAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaComDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaIndAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaIndDepois", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaPubAntes", Hibernate.INTEGER)
					.addScalar("qtdeEconomiaPubDepois", Hibernate.INTEGER)
					.addScalar("vlAguaAntes", Hibernate.BIG_DECIMAL)
					.addScalar("vlAguaDepois", Hibernate.BIG_DECIMAL)
					.addScalar("vlEsgotoAntes", Hibernate.BIG_DECIMAL)
					.addScalar("vlEsgotoDepois", Hibernate.BIG_DECIMAL)
					.addScalar("vlMulta", Hibernate.BIG_DECIMAL)
					.addScalar("vlConsumoFraudado", Hibernate.BIG_DECIMAL)
					.addScalar("qtdeAltNome", Hibernate.INTEGER)
					.addScalar("qtdeAltEndereco", Hibernate.INTEGER)
					.addScalar("qtdeAltCategoria", Hibernate.INTEGER)
					.addScalar("qtdeAltSubcategoria", Hibernate.INTEGER)
					.addScalar("qtdeAltEconomia", Hibernate.INTEGER)
					.addScalar("qtdeAltSitAgua", Hibernate.INTEGER)
					.addScalar("qtdeAltSitEsgoto", Hibernate.INTEGER)
					.addScalar("qtdeAltHidrometro", Hibernate.INTEGER)
					.addScalar("qtdeAltRg", Hibernate.INTEGER)
					.addScalar("qtdeIncRg", Hibernate.INTEGER)
					.addScalar("qtdeAltCPFCNPJ", Hibernate.INTEGER)
					.addScalar("qtdeIncCPFCNPJ", Hibernate.INTEGER)
					.addScalar("qtdeExcCPFCNPJ", Hibernate.INTEGER)
					.addScalar("qtdeImovelAlt", Hibernate.INTEGER)
					.addScalar("qtdeImovelSemAlt", Hibernate.INTEGER)   
					.setParameter("dmPotAgua", cacheSitAgua.get(LigacaoAguaSituacao.POTENCIAL))
					.setParameter("dmFatAgua", cacheSitAgua.get(LigacaoAguaSituacao.FACTIVEL))
					.setParameter("dmLigAgua", cacheSitAgua.get(LigacaoAguaSituacao.LIGADO))
					.setParameter("dmCorAgua", cacheSitAgua.get(LigacaoAguaSituacao.CORTADO))
					.setParameter("dmSupAgua", cacheSitAgua.get(LigacaoAguaSituacao.SUPRIMIDO))
					.setParameter("dmPotEsgoto", cacheSitEsgoto.get(LigacaoEsgotoSituacao.POTENCIAL))
					.setParameter("dmFatEsgoto", cacheSitEsgoto.get(LigacaoEsgotoSituacao.FACTIVEL))
					.setParameter("dmLigEsgoto", cacheSitEsgoto.get(LigacaoEsgotoSituacao.LIGADO))
					.setParameter("dmSupEsgoto", cacheSitEsgoto.get(LigacaoEsgotoSituacao.SUPRIMIDO))
					.setParameter("dmLocalizacaoAntes", resumo.getIdLocalizacaoAntes())
					.setParameter("dmLocalizacaoDepois", resumo.getIdLocalizacaoDepois())
					.setParameter("dmUsuario", resumo.getIdUsuario())
					.setParameter("dmTempo", resumo.getIdTempo())
					.uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return result;
	}
}
