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
package gcom.cadastro.localidade;

import gcom.micromedicao.bean.NumeroQuadraMinimoMaximoDaRotaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rossiter
 * @created 8 de Setembro de 2005
 * @version 1.0
 */

public class RepositorioQuadraHBM implements IRepositorioQuadra {

	private static IRepositorioQuadra instancia;

	/**
	 * Constructor for the RepositorioQuadraHBM object
	 */
	public RepositorioQuadraHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioQuadra getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioQuadraHBM();
		}

		return instancia;
	}

	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param idSetorComercial
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarQuadra(int idSetorComercial)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// Query
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de quadras e atribui a vari�vel "retorno"
			consulta = "SELECT quadra.id, quadra.numeroQuadra, "
					+ "rota.id, ftgr.id "
					+ "FROM gcom.cadastro.localidade.Quadra as quadra "
					+ "INNER JOIN quadra.rota rota "
					+ "INNER JOIN rota.faturamentoGrupo ftgr "
					+ "WHERE quadra.setorComercial.id = " + idSetorComercial
					+ " and quadra.indicadorUso = 1";

			retorno = session.createQuery(consulta).list();

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
	 * M�todo que retorna o maior n�mero da quadra de um setor comercial
	 * 
	 * @author Rafael Corr�a - Vivianne Sousa
	 * @date 11/07/2006 - 12/07/2006
	 * 
	 * @param idSetorComercial
	 * @return
	 * @throws ControladorException
	 */

	public int pesquisarMaximoCodigoQuadra(Integer idSetorComercial)
			throws ErroRepositorioException {
		int retorno = 0;
		Object maxCodigoQuadra;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(q.numeroQuadra) " + "FROM Quadra q "
					+ "INNER JOIN q.setorComercial sc "
					+ "WHERE sc.id = :idSetorComercial ";

			maxCodigoQuadra = session.createQuery(consulta).setInteger(
					"idSetorComercial", idSetorComercial).setMaxResults(1)
					.uniqueResult();

			if (maxCodigoQuadra != null) {
				retorno = (Integer) maxCodigoQuadra;
			}

		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Description of the Method
	 * 
	 * @param quadraParametros
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	/*
	 * public Collection pesquisarQuadraRelatorio(Quadra quadraParametros)
	 * throws ErroRepositorioException { //cria a cole��o de retorno Collection
	 * retorno = null;
	 * 
	 * //Inicializa os parametros da quadra Integer idLocalidade =
	 * quadraParametros.getSetorComercial().getLocalidade().getId(); int
	 * codigoSetorComercial = quadraParametros.getSetorComercial().getCodigo();
	 * int numeroQuadra = quadraParametros.getNumeroQuadra(); String nomeBairro =
	 * quadraParametros.getBairro().getNome(); Short indicadorUso =
	 * quadraParametros.getIndicadorUso(); //Query StringBuffer consulta = new
	 * StringBuffer();
	 * 
	 * //obt�m a sess�o Session session = HibernateUtil.getSession(); try {
	 * //pesquisa a cole��o de quadras e atribui a vari�vel "retorno"
	 * consulta.append("select new
	 * gcom.relatorio.cadastro.localidade.RelatorioManterQuadraBean" +
	 * "(quadra.numeroQuadra,localidade.descricao," +
	 * "setorComercial.codigo,setorComercial.descricao," +
	 * "quadra.quadraPerfil.descricao," +
	 * "quadra.indicadorRedeAgua,quadra.indicadorRedeEsgoto," +
	 * "setorComercial.municipio.nome,quadra.bairro.nome," +
	 * "quadra.bacia.sistemaEsgoto.descricaoAbreviada," +
	 * "quadra.bacia.descricao," +
	 * "quadra.distritoOperacional.descricaoAbreviada," +
	 * "quadra.ibgeSetorCensitario.descricao," +
	 * "quadra.zeis.descricaoAbreviada," + "quadra.rota.id,quadra.indicadorUso) " +
	 * "from gcom.cadastro.localidade.Localidade as localidade," +
	 * "gcom.cadastro.localidade.SetorComercial as setorComercial," +
	 * "gcom.cadastro.localidade.Quadra as quadra " + "left join quadra.bacia "+
	 * "left join quadra.bacia.sistemaEsgoto " + "left join
	 * quadra.ibgeSetorCensitario " + "left join quadra.zeis " + "left join
	 * quadra.distritoOperacional " + "where (quadra.setorComercial.id =
	 * setorComercial.id) AND " + "(setorComercial.localidade.id =
	 * localidade.id) "); if (idLocalidade != null && !idLocalidade.equals("")) {
	 * consulta.append("AND(quadra.setorComercial.localidade.id=" + idLocalidade + ")
	 * "); } if (codigoSetorComercial != 0) {
	 * consulta.append("AND(quadra.setorComercial.codigo=" +
	 * codigoSetorComercial + ") "); } if (numeroQuadra != 0) {
	 * consulta.append("AND(quadra.numeroQuadra=" + numeroQuadra + ") "); } if
	 * (nomeBairro != null && !nomeBairro.equals("")) {
	 * consulta.append("AND(quadra.bairro.nome like" + nomeBairro + "%) "); }
	 * 
	 * if (indicadorUso != null && !indicadorUso.equals("")) {
	 * consulta.append("AND(quadra.indicadorUso=" + indicadorUso + ") "); }
	 * pesquisar
	 * 
	 * consulta.append("order by
	 * setorComercial.localidade.id,setorComercial.codigo,quadra.numeroQuadra");
	 * 
	 * retorno = session.createQuery(consulta.toString()).list();
	 * 
	 * //erro no hibernate } catch (HibernateException e) { //levanta a exce��o
	 * para a pr�xima camada throw new ErroRepositorioException(e, "Erro no
	 * Hibernate"); } finally { //fecha a sess�o
	 * HibernateUtil.closeSession(session); } //retorna a cole��o de atividades
	 * pesquisada(s) return retorno; }
	 * 
	 */

	public Collection pesquisarQuadrasPorSetorComercialFaturamentoGrupo(
			int localidade, int idSetoresComercial[], Integer idFaturamentoGrupo)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// Query
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		Collection idsSetores = new ArrayList();
		for (int i = 0; i < idSetoresComercial.length; i++) {
			idsSetores.add(new Integer(idSetoresComercial[i]));
		}

		try {
			// pesquisa a cole��o de quadras e atribui a vari�vel "retorno"
			consulta = "SELECT quadra.id, quadra.numeroQuadra, "
					+ "quadra.setorComercial.id, quadra.setorComercial.codigo, re.id, quadra.ultimaAlteracao," +
					" quadra.ultimaAlteracao "
					+ "FROM gcom.cadastro.localidade.Quadra as quadra "
					+ "INNER JOIN quadra.setorComercial stcm "
					+ "LEFT OUTER JOIN quadra.roteiroEmpresa re "
					+ "WHERE quadra.setorComercial.id in (:idsSetores) "
					+ " and stcm.localidade = "
					+ localidade
					+ " and quadra.indicadorUso = 1 "
					+ " and quadra.rota.id in "
					+ "(SELECT rota.id from quadra.rota as rota "
					+ "where rota.faturamentoGrupo.id = "
					+ idFaturamentoGrupo
					+ ") " 
					+ " and quadra.roteiroEmpresa is null"
					+ " order by quadra.setorComercial.codigo, quadra.numeroQuadra";

			retorno = session.createQuery(consulta).setParameterList(
					"idsSetores", idsSetores).list();

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
	 * 
	 * [UC608] Efetuar Liga��o de Esgoto sem RA
	 * @author S�vio Luiz
	 * @date 10/09/2007
	 * 
	 * @return

	 */

	public Short pesquisarIndicadorRedeEsgotoQuadra(int idImovel)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Short retorno = null;
		// Query
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de quadras e atribui a vari�vel "retorno"
			consulta = "SELECT quad.indicadorRedeEsgoto " + "FROM Imovel imov "
					+ "INNER JOIN imov.quadra quad " + "WHERE imov.id = "
					+ idImovel + " and quad.indicadorUso = 1";

			retorno = (Short) session.createQuery(consulta).setMaxResults(1)
					.uniqueResult();

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

	public Collection pesquisarQuadrasPorRoteiroEmpresa(
			int idRoteiroEmpresa)
			throws ErroRepositorioException {

		// cria a cole��o de retorno
		Collection retorno = null;
		// Query
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de quadras e atribui a vari�vel "retorno"
			consulta = "SELECT quadra.id, quadra.numeroQuadra, "
					+ "quadra.setorComercial.id, quadra.setorComercial.codigo, re.id, rota.id, "
					+ "rota.faturamentoGrupo.id, stcm.localidade.id, loca.descricao, quadra.ultimaAlteracao "
					+ "FROM gcom.cadastro.localidade.Quadra as quadra "
					+ "INNER JOIN quadra.setorComercial stcm "
					+ "INNER JOIN stcm.localidade loca "					
					+ "INNER JOIN quadra.rota rota "
					+ "LEFT OUTER JOIN quadra.roteiroEmpresa re "
					+ "WHERE re.id = " + idRoteiroEmpresa
					+ " order by quadra.setorComercial.codigo, quadra.numeroQuadra";

			retorno = session.createQuery(consulta).list();

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
     * @author: Vivianne Sousa
     * @date: 16/05/2008 
     */
    public Collection pesquisarIdQuadraPorSetorComercial(Integer idSetorComercial)
        throws ErroRepositorioException {

        // cria a cole��o de retorno
        Collection retorno = null;
        // Query
        String consulta;
        // obt�m a sess�o
        Session session = HibernateUtil.getSession();
        
        try {
            // pesquisa a cole��o de quadras e atribui a vari�vel "retorno"
            consulta = " SELECT quadra.id "
                     + " FROM Quadra as quadra "
                     + " WHERE quadra.setorComercial.id = :idSetorComercial"
                     + " AND quadra.indicadorUso = :indicadorUso ";
        
            retorno = session.createQuery(consulta)
            .setInteger("idSetorComercial", idSetorComercial)
            .setInteger("indicadorUso", ConstantesSistema.INDICADOR_USO_ATIVO)
            .list();
        
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
     * [????] Informar Subdivis�es de Rota
     * 
     * Atualiza o campo rota das quadras que pertencem ao intervalo de numero informado e ao setor comercial
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public void atualizarRotaDasQuadrasNoIntervaloNumero(
    		int idNovaRota, int idSetorComercial, int numeroInicial, int numeroFinal) throws ErroRepositorioException {
    	
        String consulta;
        Session session = HibernateUtil.getSession();
        
        try {
            consulta = " UPDATE Quadra q SET rota.id = :idNovaRota WHERE " +
            		"q.setorComercial.id = :idSetorComercial AND " +
            		"q.numeroQuadra BETWEEN :numeroInicial AND :numeroFinal ";
        
            session.createQuery(consulta)
            .setInteger("idNovaRota", idNovaRota)
            .setInteger("idSetorComercial", idSetorComercial)
            .setInteger("numeroInicial", numeroInicial)
            .setInteger("numeroFinal", numeroFinal)
            .executeUpdate();

        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
    
    /**
     * [????] Informar Subdivis�es de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 30/09/2008
     */
    public Collection<NumeroQuadraMinimoMaximoDaRotaHelper> pesquisarNumeroQuadraMininoMaximoDaRota(
    		Collection<Integer> idsRotas) throws ErroRepositorioException {
    	
    	List<NumeroQuadraMinimoMaximoDaRotaHelper> retorno = new ArrayList<NumeroQuadraMinimoMaximoDaRotaHelper>();

        String consulta;
        Session session = HibernateUtil.getSession();
        
        try {
            consulta = 
            	"SELECT " + 
            		"q.rota.id,  " + 
            		"MIN(q.numeroQuadra), " + 
            		"MAX(q.numeroQuadra)," +
            		"COUNT(q.rota.id) " + 
            	"FROM Quadra q " + 
            	"WHERE " + 
            		"q.rota.id IN (:idsRotas) " + 
            	"GROUP BY " + 
            		"q.rota.id ";
        
            Collection<Object[]> retornoConsulta = session.createQuery(consulta)
            	.setParameterList("idsRotas", idsRotas).list();
            
            for (Object[] linha : retornoConsulta) {
            	NumeroQuadraMinimoMaximoDaRotaHelper helper = new NumeroQuadraMinimoMaximoDaRotaHelper();
            	helper.setIdRota((Integer) linha[0]);
            	helper.setNumeroQuadraMinimo((Integer) linha[1]);
            	helper.setNumeroQuadraMaximo((Integer) linha[2]);
            	helper.setQuantidadeQuadras(new Integer(linha[3].toString()));
            	retorno.add(helper);
            }
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }
    	
    	return retorno;
    }
    
    /**
     * [UC0???] Informar Subdivis�es de Rota
     * 
     * @author: Victor Cisneiros
     * @date: 27/10/2008
     */
    public Integer pesqisarQuantidadeQuadrasDaRota(
    		Integer idRota, Integer quadraInicial, Integer quadraFinal) throws ErroRepositorioException {
    	
    	Integer retorno = 0;

        String consulta;
        Session session = HibernateUtil.getSession();
        
        try {
            consulta = 
            	"SELECT " + 
            		"COUNT(*) " + 
            	"FROM Quadra q " + 
            	"WHERE " + 
            		"q.rota.id = (:idRota) ";
            
            if (quadraInicial != null) {
            	consulta = consulta +
            			"AND q.numeroQuadra >= :quadraInicial " +
            			"AND q.numeroQuadra <= :quadraFinal ";
        
            	retorno = (Integer) session.createQuery(consulta)
            			.setInteger("idRota", idRota)
            			.setInteger("quadraInicial", quadraInicial)
            			.setInteger("quadraFinal", quadraFinal)
            			.uniqueResult();
            } else {
            	retorno = (Integer) session.createQuery(consulta)
            			.setInteger("idRota", idRota)
            			.uniqueResult();
            }
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }
    	
    	return retorno;
    }
    
    
    /**
	 * Integra��o com o conceito de face da quadra
	 *
	 * @author Raphael Rossiter
	 * @date 21/05/2009
	 *
	 * @param idImovel
	 * @return Quadra
	 * @throws ErroRepositorioException
	 */
	public Quadra pesquisarQuadra(Integer idImovel)throws ErroRepositorioException {
		Quadra retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT qdra " + "FROM Imovel imov "
					+ "INNER JOIN imov.quadra qdra "
					+ "LEFT JOIN FETCH qdra.distritoOperacional diop "
					+ "LEFT JOIN FETCH qdra.bacia baci "
					+ "LEFT JOIN FETCH baci.sistemaEsgoto sesg "
					+ "LEFT JOIN FETCH sesg.divisaoEsgoto dves "
					+ "WHERE imov.id = :idImovel ";

			retorno = (Quadra) session.createQuery(consulta).setInteger(
			"idImovel", idImovel.intValue()).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}

	
	/**
	 * Integra��o com o conceito de face da quadra
	 *
	 * @author Raphael Rossiter
	 * @date 21/05/2009
	 *
	 * @param idImovel
	 * @return QuadraFace
	 * @throws ErroRepositorioException
	 */
	public QuadraFace pesquisarQuadraFace(Integer idImovel)throws ErroRepositorioException {
		QuadraFace retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT qdfa " + "FROM Imovel imov "
					+ "INNER JOIN imov.quadraFace qdfa "
					+ "LEFT JOIN FETCH qdfa.distritoOperacional diop "
					+ "LEFT JOIN FETCH qdfa.bacia baci "
					+ "LEFT JOIN FETCH baci.sistemaEsgoto sesg "
					+ "LEFT JOIN FETCH sesg.divisaoEsgoto dves "
					+ "WHERE imov.id = :idImovel ";

			retorno = (QuadraFace) session.createQuery(consulta).setInteger(
			"idImovel", idImovel.intValue()).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
		
	}

}

