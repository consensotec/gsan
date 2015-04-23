package gsan.atualizacaocadastral;

import gsan.atualizacaocadastral.bean.MapaQuadraHelper;
import gsan.util.ErroRepositorioException;
import gsan.util.HibernateUtil;
import gsan.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Bruno Sá Barreto
 * @date 24/02/2015
 */
public class RepositorioAtualizacaoCadastralHBM implements IRepositorioAtualizacaoCadastral{

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

	public List<String> verificarQuadrasSemMapaPorImoveis(String[] idsRegistros) throws Exception {
		
		List<String> retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta;
		
		try {
			consulta = "select distinct qdra.qdra_nnquadra as quadra_sem_mapa " +
					"from cadastro.imovel im left join atualizacaocadastral.mapa_atlz_cad_dm mp on mp.qdra_id = im.qdra_id" +
					" left join cadastro.quadra qdra on im.qdra_id = qdra.qdra_id " +
					"where imov_id in (:idsRegistros) and mp.qdra_id is null order by quadra_sem_mapa asc";
					
			retorno = session.createSQLQuery(consulta).
					addScalar("quadra_sem_mapa", Hibernate.TEXT).
					setParameterList("idsRegistros", idsRegistros).
					list();
			
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
}
