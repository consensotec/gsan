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
package gcom.gerencial.faturamento;

import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/** 
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class RepositorioGerencialFaturamentoPostgresHBM extends RepositorioGerencialFaturamentoHBM {

	
	/**
	 * Atualiza os dados na tabela un_resumo_indicadores_faturamento
	 * 
 	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Arthur Carvalho
	 * @date 09/12/2010
	 * 
	 * @return 
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosResumoIndicadoresFaturamento(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas) throws ErroRepositorioException {
		
		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {
			
			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO faturamento.un_res_ind_fat "
					+ " SELECT "
					+ " refa_amreferencia, refa_anoreferencia, refa_mesreferencia, greg_id, uneg_id, "
					+ " loca_id, loca_cdelo, stcm_id, qdra_id, rota_id, refa_cdsetorcomercial, refa_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, lapf_id, lepf_id, "
					+ " crog_id, lict_id, dotp_id, fntp_id, dbtp_id, crti_id, "
					+ " refa_qtcontasemitidas, rerf_qtcontasretificadas, rerf_qtcontascanceladas, rerf_qtcontasincluidas, "
					+ " refa_qteconomiasfaturadas, refa_vofaturadoagua, rerf_vocanceladoagua, rerf_voincluidoagua, "
					+ " refa_vofaturadoesgoto, rerf_vocanceladoesgoto, rerf_voincluidoesgoto, "
					+ " refa_vlfaturadoagua, rerf_vlcanceladoagua, rerf_vlincluidoagua, "
					+ " refa_vlfaturadoesgoto, rerf_vlcanceladoesgoto, rerf_vlincluidoesgoto, "
					+ " refa_vldocumentosfaturadoscred, rerf_vlcanceladocredito, rerf_vlincluidocredito, "
					+ " refa_vldocumentosfaturadosoutr, rerf_vlcanceladooutros, rerf_vlincluidooutros, "
					+ " refa_vlacrescimoimpontualidade, refa_qtcontasemitidasma, rerf_qtcontasretificadasma, "
					+ " rerf_qtcontascanceladasma, rerf_qtcontasincluidasma, refa_qteconomiasfaturadasma, "
					+ " refa_vofaturadoaguama, rerf_vocanceladoaguama, rerf_voincluidoaguama, "
					+ " refa_vofaturadoesgotoma, rerf_vocanceladoesgotoma, rerf_voincluidoesgotoma, "
					+ " refa_vlfaturadoaguama, rerf_vlcanceladoaguama, rerf_vlincluidoaguama, "
					+ " refa_vlfaturadoesgotoma, rerf_vlcanceladoesgotoma, rerf_vlincluidoesgotoma, "
					+ " refa_vldocumentosfaturadoscredma, rerf_vlcanceladocreditoma, rerf_vlincluidocreditoma, "
					+ " refa_vldocumentosfaturadosoutrma, rerf_vlcanceladooutrosma, rerf_vlincluidooutrosma, "
					+ " refa_vlacrescimoimpontualidadema, refa_vlarrastos, refa_vlparcelamento, "
					+ " rerf_qtguiascanceladas, "
					+ " faturamento.seq_un_res_ind_fat.nextval,  now() "
					+ " FROM faturamento.vw_un_res_ind_fat ";
			
			if (anoMesReferenciaIndicador != null) {
				consulta = consulta
						+ " WHERE refa_amreferencia > " + anoMesReferenciaIndicador + " and refa_amreferencia <= " + anoMesReferenciaTabelas;
			} else {
				consulta = consulta
				+ " WHERE refa_amreferencia <= " + anoMesReferenciaTabelas;
			}
			
//			consulta += "\n limit 1";
			System.out.println("inicio Batch atualizarDadosResumoIndicadoresFaturamento:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadoresFaturamento:" + new Date());

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�es");
			}
		}
	}
	
	
	
	
	
		
}
