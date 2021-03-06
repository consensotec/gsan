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
package gcom.gerencial.cadastro.imovel;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialImovelHBM implements
		IRepositorioGerencialImovel {

	private static IRepositorioGerencialImovel instancia;

	/**
	 * Construtor da classe RepositorioMicromedicaoHBM
	 */
	private RepositorioGerencialImovelHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioGerencialImovel getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioGerencialImovelHBM();
		}

		return instancia;
	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 27/04/2007
	 * 
	 * @param imovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarObterQuantidadeEconomiasCategoria(Integer imovel)
			throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select c.id, c.descricao, c.consumoEstouro, "
					+ "c.vezesMediaEstouro, sum(isb.quantidadeEconomias), "
					+ "isb.comp_id.imovel.id, "
					+ "c.consumoAlto, "
					+ "c.mediaBaixoConsumo, "
					+ "c.vezesMediaAltoConsumo, "
					+ "c.porcentagemMediaBaixoConsumo,"
					+ "c.descricaoAbreviada, "
					+ "c.numeroConsumoMaximoEc, "
					+ "c.indicadorCobrancaAcrescimos "
					+ "from GImovelSubcategoria isb "
					+ "inner join isb.comp_id.gSubcategoria sb "
					+ "inner join sb.gCategoria c "
					+ "where isb.comp_id.imovel.id = :imovelId "
					+ "group by c.id, c.descricao, c.consumoEstouro, c.vezesMediaEstouro," 
					+ "isb.comp_id.imovel.id, c.consumoAlto, c.mediaBaixoConsumo, c.vezesMediaAltoConsumo," 
					+ "c.porcentagemMediaBaixoConsumo,c.descricaoAbreviada,c.numeroConsumoMaximoEc, c.indicadorCobrancaAcrescimos ";
					//+ "having isb.comp_id.imovel.id = :imovelId ";

			retorno = session.createQuery(consulta).setInteger("imovelId",
					imovel.intValue()).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * <Breve descri��o sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Administrador
	 * @date 27/04/2007
	 *
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterSubCategoriasPorCategoria(Integer idCategoria) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			String hql = "select new ImovelSubCategoria("
					   + "imovelSubCategoria.id, "
					   + "imovelSubCategoria.quantidadeEconomias, "
					   + "subCimovelSubCategoriaategoria.ultimaAlteracao) "
					   + "from ImovelSubCategoria as imovelSubCategoria "
					   + "where imovelSubCategoria.subcategoria.categoria.id = :idCategoria ";

			retorno = session.createQuery(hql).setInteger("idCategoria", idCategoria).list();

		} catch (HibernateException e) {

			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
}