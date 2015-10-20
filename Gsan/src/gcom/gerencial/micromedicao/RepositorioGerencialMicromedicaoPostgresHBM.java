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
package gcom.gerencial.micromedicao;

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
public class RepositorioGerencialMicromedicaoPostgresHBM extends
		RepositorioGerencialMicromedicaoHBM {

	/**
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao
	 * 
	 * [UC????] - Gerar Resumo Indicadores da Micromedi��o
	 * 
	 * @author Arthur Carvalho
	 * @date 09/12/2010
	 * @alteracao: Alterado o esquema de cadastro para micromedicao.
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicao(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO micromedicao.un_resi_des_mmd "
					+ " SELECT "
					+ " micromedicao.seq_un_resi_des_mmd.nextval, "
					+ " reca_amreferencia, to_number(reca_anoreferencia, 9999), "
					+ " reca_mesreferencia, greg_id, uneg_id, loca_id, loca_cdelo, "
					+ " stcm_id, qdra_id, rota_id, reca_cdsetorcomercial, reca_nnquadra, "
					+ " iper_id, last_id, lest_id, catg_id, scat_id, epod_id, cltp_id, "
					+ " lapf_id, lepf_id, reca_qtligacoes_ativas, reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, reca_qteconomias_com_hidrometro, reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, reca_consumoagua_com_hidrometro, reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, reca_vofaturadoaguamedido, reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, rece_qteconomias, rece_voesgoto, "
					+ " rece_vofaturadoesgoto, rece_vofaturadoesgotomedido, rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, relt_qtleituras_efetuadas, relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, reih_qthidrometro_substituido_ramal, reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, reih_qthidrometro_substituido_poco, reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados,  now() "
					+ " FROM micromedicao.vw_un_resi_des_mmd ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE reca_amreferencia > "
						+ anoMesReferenciaIndicador
						+ " and reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

			//consulta += "\n limit 1";
			System.out.println("inicio Batch atualizarDadosResumoIndicadorDesempenhoMicromedicao:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorDesempenhoMicromedicao:" + new Date());

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
	
	
	/**
	 * Atualiza os dados na tabela un_resumo_indicador_desempenho_micromedicao_ref_2010
	 * 
	 * @author Arthur Carvalho
	 * @date 09/12/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public void atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno(
			Integer anoMesReferenciaIndicador, Integer anoMesReferenciaTabelas)
			throws ErroRepositorioException {

		Connection con = null;
		Statement stmt = null;

		Session session = HibernateUtil.getSessionGerencial();
		String consulta;

		try {

			con = session.connection();
			stmt = con.createStatement();

			consulta = "INSERT INTO micromedicao.un_resi_des_mmd_ref_2010 (" 
					+ " reca_id, "
					+ " reca_amreferencia, "
					+ " greg_id, "
					+ " uneg_id, "
					+ " loca_id, "
					+ " loca_cdelo, "
					+ " stcm_id, "
					+ " reca_cdsetorcomercial, "
					+ " iper_id, "
					+ " last_id, "
					+ " lest_id, "
					+ " catg_id, "
					+ " epod_id, "
					+ " cltp_id, "
					+ " lapf_id, "
					+ " lepf_id, "
					+ " reca_qtligacoes_ativas, "
					+ " reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, "
					+ " reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, "
					+ " reca_qteconomias_com_hidrometro, "
					+ " reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, "
					+ " reca_consumoagua_com_hidrometro, "
					+ " reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, "
					+ " reca_vofaturadoaguamedido, "
					+ " reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, "
					+ " rece_qteconomias, "
					+ " rece_voesgoto, "
					+ " rece_vofaturadoesgoto, "
					+ " rece_vofaturadoesgotomedido, "
					+ " rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, "
					+ " relt_qtleituras_efetuadas, "
					+ " relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, "
					+ " reih_qthidrometro_substituido_ramal, "
					+ " reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, "
					+ " reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, "
					+ " reih_qthidrometro_substituido_poco, "
					+ " reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, "
					+ " reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados, "
					+ " reca_tmultimaalteracao "
					+ " ) "
					+ " SELECT "
					+ " micromedicao.seq_un_resi_des_mmd_ref_2007.nextval, "
					+ " reca_amreferencia, " 
					//+ " to_number(reca_anoreferencia, 9999), "
					//+ " reca_mesreferencia," 
					+ " greg_id, " 
					+ " uneg_id, " 
					+ " loca_id, " 
					+ " loca_cdelo, "
					+ " stcm_id, " 
					//+ " qdra_id, " 
					//+ " rota_id, " 
					+ " reca_cdsetorcomercial, " 
					//+ " reca_nnquadra, "
					+ " iper_id, " 
					+ " last_id, " 
					+ " lest_id, " 
					+ " catg_id, " 
					//+ " scat_id, " 
					+ " epod_id, " 
					+ " cltp_id, "
					+ " lapf_id, " 
					+ " lepf_id, " 
					+ " reca_qtligacoes_ativas, " 
					+ " reca_qtligacoes_com_hidrometro, "
					+ " reca_qtligacoes_com_medicao_real, " 
					+ " reca_qtligacoes_com_hidrometro_e_medicao_estimada, "
					+ " reca_qteconomias_ativas, " 
					+ " reca_qteconomias_com_hidrometro, " 
					+ " reca_qteconomias_com_medicao_real, "
					+ " reca_qteconomias_com_hidrometro_e_medicao_estimada, "
					+ " reca_consumoagua_ativas, " 
					+ " reca_consumoagua_com_hidrometro, " 
					+ " reca_consumoagua_com_medicao_real, "
					+ " reca_consumoagua_com_hidrometro_e_medicao_estimada, "
					+ " reca_vofaturadoagua, " 
					+ " reca_vofaturadoaguamedido, " 
					+ " reca_vofaturadoaguanaomedido, "
					+ " rece_qtligacoes, " 
					+ " rece_qteconomias, " 
					+ " rece_voesgoto, "
					+ " rece_vofaturadoesgoto, " 
					+ " rece_vofaturadoesgotomedido, " 
					+ " rece_vofaturadoesgotonaomedido, "
					+ " relt_qtvisitas_realizadas, " 
					+ " relt_qtleituras_efetuadas, " 
					+ " relt_qtleituras_com_anormalidade_hidrometro, "
					+ " reih_qthidrometro_instalado_ramal, " 
					+ " reih_qthidrometro_substituido_ramal, " 
					+ " reih_qthidrometro_remanejado_ramal, "
					+ " reih_qthidrometro_retirado_ramal, " 
					+ " reih_qthidrometrosatualinstaladosramal, "
					+ " reih_qthidrometro_instalado_poco, " 
					+ " reih_qthidrometro_substituido_poco, " 
					+ " reih_qthidrometro_remanejado_poco, "
					+ " reih_qthidrometro_retirado_poco, " 
					+ " reih_qthidrometrosatualinstaladospoco, "
					+ " reih_qthidrometro_dadosatualizados," 
					+ "  now() "
					+ " FROM micromedicao.vw_un_resi_des_mmd ";

			if (anoMesReferenciaIndicador != null) {
				consulta = consulta + " WHERE reca_amreferencia > "
						+ anoMesReferenciaIndicador
						+ " and reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			} else {
				consulta = consulta + " WHERE reca_amreferencia <= "
						+ anoMesReferenciaTabelas;
			}

			System.out.println("inicio Batch atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno:" + new Date());
			stmt.executeUpdate(consulta);
			System.out.println("fim Batch atualizarDadosResumoIndicadorDesempenhoMicromedicaoPorAno:" + new Date());

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
