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
package gcom.atendimentopublico.ligacaoesgoto;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

/**
 * Implementa��o do Reposit�rio de Liga��o de Esgoto
 * 
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public class RepositorioLigacaoEsgotoHBM implements IRepositorioLigacaoEsgoto {

	private static IRepositorioLigacaoEsgoto instancia;

	/**
	 * Construtor da classe RepositorioLigacaoEsgotoHBM
	 */
	private RepositorioLigacaoEsgotoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return instancia
	 */
	public static IRepositorioLigacaoEsgoto getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioLigacaoEsgotoHBM();
		}
		return instancia;
	}

	/**
	 * [UC0464] Atualizar Volume M�nimo da Liga��o de Esgoto
	 * 
	 * [SB0001] Atualizar Liga��o de Esgoto.
	 * 
	 * @author Leonardo Regis
	 * @date 22/09/2006
	 * 
	 * @param ligacaoEsgoto
	 * @throws ErroRepositorioException
	 */
	public void atualizarVolumeMinimoLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String update;
		try {
			update = "update LigacaoEsgoto set ";
				
				if (ligacaoEsgoto.getConsumoMinimo() != null) {
					update = update + "consumoMinimo = :consumoMinimo, ";
				} else {
					update = update + "consumoMinimo = null, ";
				}
					
				update = update + "ultimaAlteracao = :dataCorrente "
					+ "where id = :ligacaoEsgotoId";

				if (ligacaoEsgoto.getConsumoMinimo() != null) {
					session.createQuery(update).setInteger("consumoMinimo",
						ligacaoEsgoto.getConsumoMinimo()).setTimestamp(
						"dataCorrente", ligacaoEsgoto.getUltimaAlteracao())
						.setInteger("ligacaoEsgotoId", ligacaoEsgoto.getId())
						.executeUpdate();
				} else {
					session.createQuery(update).setTimestamp(
							"dataCorrente", ligacaoEsgoto.getUltimaAlteracao())
							.setInteger("ligacaoEsgotoId", ligacaoEsgoto.getId())
							.executeUpdate();
				}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC0488] Informar Retorno Ordem de Fiscaliza��o
	 * 
	 * [SB0004] - Calcular Valor de �gua e/ou Esgoto
	 * 
	 * 
	 * @author S�vio Luiz
	 * @date 04/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public BigDecimal recuperarPercentualEsgoto(Integer idLigacaoEsgoto)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		BigDecimal retorno = null;
		try {
			consulta = "SELECT ligEsgoto.percentual "
					+ "FROM LigacaoEsgoto ligEsgoto "
					+ "WHERE ligEsgoto.id = :idLigacaoEsgoto";

			retorno = (BigDecimal) session.createQuery(consulta).setInteger(
					"idLigacaoEsgoto", idLigacaoEsgoto).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0349] Emitir Documento de Cobran�a - Ordem de Fiscaliza��o
	 * 
	 * @author S�vio Luiz
	 * @date 21/12/2006
	 * 
	 * @param idOS
	 * @return OrdemServico
	 * @throws ControladorException
	 */
	public Integer recuperarConsumoMinimoEsgoto(Integer idImovel)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;
		Integer retorno = null;
		try {
			consulta = "SELECT ligEsgoto.consumoMinimo "
					+ "FROM LigacaoEsgoto ligEsgoto "
					+ "WHERE ligEsgoto.id = :idImovel";

			retorno = (Integer) session.createQuery(consulta).setInteger(
					"idImovel", idImovel).setMaxResults(1)
					.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 *
	 * [SB0001 - Determinar Faturamento para o Im�vel] 
	 *
	 * @author Raphael Rossiter
	 * @date 04/06/2008
	 *
	 * @param idLigacaoEsgotoSituacao
	 * @param idConsumoTipo
	 * @return LigacaoEsgotoSituacaoConsumoTipo
	 * @throws ErroRepositorioException
	 */
	public LigacaoEsgotoSituacaoConsumoTipo pesquisarLigacaoEsgotoSituacaoConsumoTipo(Integer idLigacaoEsgotoSituacao,
			Integer idConsumoTipo) throws ErroRepositorioException{
		
		Session session = HibernateUtil.getSession();
		LigacaoEsgotoSituacaoConsumoTipo retorno = null;
		
		try {

			String consulta = "SELECT lect "
					+ "FROM LigacaoEsgotoSituacaoConsumoTipo lect "
					+ "INNER JOIN lect.consumoTipo consumoTipo "
					+ "INNER JOIN lect.ligacaoEsgotoSituacao lest "
					+ "WHERE consumoTipo.id = :idConsumoTipo AND lest.id = :idLigacaoEsgotoSituacao ";

			retorno = (LigacaoEsgotoSituacaoConsumoTipo) session.createQuery(consulta)
					.setInteger("idConsumoTipo", idConsumoTipo)
					.setInteger("idLigacaoEsgotoSituacao", idLigacaoEsgotoSituacao)
					.setMaxResults(1).uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}
}