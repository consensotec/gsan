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

import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rossiter
 * @version 1.0
 */

public class RepositorioSetorComercialHBM implements IRepositorioSetorComercial {

    private static IRepositorioSetorComercial instancia;

    /**
     * Constructor for the RepositorioSetorComercialHBM object
     */
    public RepositorioSetorComercialHBM() {
    }

    /**
     * Retorna o valor de instancia
     * 
     * @return O valor de instancia
     */
    public static IRepositorioSetorComercial getInstancia() {

        if (instancia == null) {
            instancia = new RepositorioSetorComercialHBM();
        }

        return instancia;
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

    public Collection pesquisarSetorComercial(int idLocalidade)
            throws ErroRepositorioException {

        //cria a cole��o de retorno
        Collection retorno = null;
        //Query
        String consulta;
        //obt�m a sess�o
        Session session = HibernateUtil.getSession();

        try {
            //pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
            consulta = "select new gcom.cadastro.localidade.SetorComercial(setorComercial.id, setorComercial.codigo,"
                    + "setorComercial.descricao) "
                    + "from gcom.cadastro.localidade.SetorComercial as setorComercial "
                    + "where setorComercial.localidade.id = "
                    + idLocalidade
                    + " and setorComercial.indicadorUso = 1";

            retorno = session.createQuery(consulta).list();

            //erro no hibernate
        } catch (HibernateException e) {
            //levanta a exce��o para a pr�xima camada
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            //fecha a sess�o
            HibernateUtil.closeSession(session);
        }
        //retorna a cole��o de atividades pesquisada(s)
        return retorno;
    }
    
    /**
	 * M�todo que retorna o maior c�digo do setor comercial de uma localidade
	 * 
	 * @author Rafael Corr�a
	 * @date 11/07/2006
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ControladorException
	 */   
    
    public int pesquisarMaximoCodigoSetorComercial(Integer idLocalidade)
	throws ErroRepositorioException {
    	int retorno = 0;
    	Object maxCodigoSetorComercial;
    	
    	Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "SELECT max(sc.codigo) "
					+ "FROM SetorComercial sc "
					+ "INNER JOIN sc.localidade l "
					+ "WHERE l.id = :idLocalidade ";
		
			maxCodigoSetorComercial = session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade)
					.setMaxResults(1).uniqueResult();
			
			if (maxCodigoSetorComercial != null){
				retorno = (Integer)maxCodigoSetorComercial;	
			}
	
		} catch (HibernateException e) {
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
    	
    	return retorno;
    }
    
	/**
	 * Pesquisa um setor comercial pelo c�digo e pelo id da localidade
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return SetorComercial
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarObjetoSetorComercialRelatorio(Integer codigoSetorComercial,
			Integer idLocalidade) throws ErroRepositorioException {
		// cria a vari�vel que vai armazenar a cole��o pesquisada
				
		Object[] retorno = null;

		// cria a sess�o com o hibernate
		Session session = HibernateUtil.getSession();

		try {
			// cria o HQL para consulta
			String consulta = "select sc.stcm_cdsetorcomercial as codigo, " +
				"sc.stcm_nmsetorcomercial as descricao "
					+ "from cadastro.localidade loc, "
					+ "cadastro.setor_comercial sc "
					+ "where sc.loca_id = loc.loca_id and"  
					+ " loc.loca_id = " + idLocalidade.toString() 
					+ " and sc.stcm_cdsetorcomercial = " + codigoSetorComercial.toString();

			// pesquisa a cole��o de acordo com o par�metro informado
			Collection colecaoSetoresComerciais = session.createSQLQuery(consulta)
			.addScalar("codigo", Hibernate.INTEGER)
			.addScalar("descricao", Hibernate.STRING).list();
			
			retorno = Util.retonarObjetoDeColecaoArray(colecaoSetoresComerciais);

			// erro no hibernate
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o pesquisada
		return retorno;
	}
	
	/**
	 * Pesquisar os ids do Setor comercial pela localidade
	 * 
	 * @author Ana Maria
	 * @date 07/02/2007
	 * 
	 * @return Collection<Integer>
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = "";

		try {

			consulta = " select stcm.id"
					 + " from SetorComercial stcm"
					 + " where stcm.localidade.id =:idLocalidade";

			retorno = session.createQuery(consulta).setInteger(
					"idLocalidade", idLocalidade).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}	

    /**
     * Pesquisar os todos os ids de Setor comercial 
     * 
     * @author Vivianne Sousa
     * @date 14/05/2008
     * 
     * @return Collection<Integer>
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCodigosSetorComercial()
            throws ErroRepositorioException {

        Collection retorno = null;

        Session session = HibernateUtil.getSession();

        String consulta = "";

        try {

            consulta = " select stcm.id"
                     + " from SetorComercial stcm";

            retorno = session.createQuery(consulta).list();
            
        } catch (HibernateException e) {
            throw new ErroRepositorioException(e, "Erro no Hibernate");
        } finally {
            HibernateUtil.closeSession(session);
        }

        return retorno;
    }   

	/**
	 * [UC0928]-Manter Situa��o Especial de Faturamento
	 * [FS0003]-Verificar a exist�ncia do setor
	 *
	 * @author Marlon Patrick
	 * @date 11/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaSetorComercial(Integer idSetorComercial)throws ErroRepositorioException{
		Integer retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select count(setor.id) "

					+ "from SetorComercial setor "

					+ "where setor.id = :idSetor and (imovel.indicadorUso = :indicadorUso)";

			retorno = (Integer) session.createQuery(consulta).setInteger(

			"idSetor", idSetorComercial).setShort("indicadorUso",
					ConstantesSistema.INDICADOR_USO_ATIVO).setMaxResults(1).uniqueResult();

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