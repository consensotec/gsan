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
 * Thiago Silva Toscano de Brito
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

package gsan.integracao;

import gsan.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gsan.cadastro.imovel.PavimentoCalcada;
import gsan.cadastro.imovel.PavimentoRua;
import gsan.integracao.upa.OrdensServico;
import gsan.util.ErroRepositorioException;
import gsan.util.HibernateUtil;
import gsan.util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.exception.ConstraintViolationException;

public class RepositorioIntegracaoHBM implements IRepositorioIntegracao {

	private static IRepositorioIntegracao instancia;

	/**
	 * Construtor da classe RepositorioFaturamentoHBM
	 */
	private RepositorioIntegracaoHBM() {
	}

	/**
	 * Retorna o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static IRepositorioIntegracao getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioIntegracaoHBM();
		}
		return instancia;
	}

	public Collection pesquisarOrdemServicoMovimentoParaEnvioSAM()
			throws ErroRepositorioException {
		// obt�m a sess�o

		Session session = HibernateUtil.getSession();

		Collection retorno = new ArrayList();

		try {

			retorno = session
					.createQuery(

							"select osmv from OrdemServicoMovimento osmv "
									+ " left join fetch osmv.imovel imov"
									+ " left join fetch imov.setorComercial "
									+ " left join fetch imov.quadra "
									+ " where osmv.indicadorMovimento = 1 "
									+ "and osmv.unidadeOrganizacionalExecutora is not null ")
					//				+ "and osmv.dataGeracao between :data1 and :data2 ")
					//.setTimestamp("data1", Util.formatarDataInicial(new Date()))
					//.setTimestamp("data2", Util.formatarDataFinal(new Date()))
					.list();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exce��o para a pr�xima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sess�o

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void exportarOrdemServicoMovimentos(
			Collection ordensServicoParaExportacao)
			throws ErroRepositorioException {

		StatelessSession session = HibernateUtil
				.getStatelessSessionIntegracaoSAM();

		if (ordensServicoParaExportacao != null
				&& !ordensServicoParaExportacao.isEmpty()) {
			Iterator it = ordensServicoParaExportacao.iterator();

			try {
				while (it.hasNext()) {

					Object obj = it.next();

					try {
					//System.out.println("INSERINDO: " + i + "-" + obj.getClass().getSimpleName());
					session.insert(obj);
					} catch (ConstraintViolationException exception) {		
						//N�o fazer nada pois o registro j� est� inserido no banco da SAM
						//System.out.println("entrou!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}	 

				}
				
			} catch (HibernateException e) {
				// levanta a exce��o para a pr�xima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	}
	
	/**
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 * 
	 * [SB0005] Realizar integra��o com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer valorMaximoChaveIFS()
			throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSessionIntegracaoIFS();
		
		Integer retorno = 1;
		
		Connection con = null;
		Statement stmt = null;
		
		String consulta = null;
		try {
			con = session.connection();
			stmt = con.createStatement();
			
			consulta = "SELECT MAX(TO_NUMBER(LOAD_ID)) as valor FROM IFSCPSA.C_GSAN_CONTABILIDADE_TAB";
			
			ResultSet set = stmt.executeQuery(consulta);
			
			if(set != null){
				while (set.next()) {
					retorno = set.getInt("valor");
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
			try {
				
				if(stmt != null){
					stmt.close();	
				}
				if(con != null){
					con.close();	
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�es");
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			}
		}
		return retorno;
	}
	
	/**
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 * 
	 * [SB0005] Realizar integra��o com sistema IFS para COMPESA
	 *
	 * @author Rafael Pinto
	 * @date 25/10/2011
	 * 
	 * @throws ErroRepositorioException
	 */
	public void exportarDadosContabilidadeIFS(String company,
			String voucher_type,String currency_code,
			String load_id,Date transaction_date,
			String accounting_cod,String cost_center,
			String	accounting_year,String accounting_period,
			BigDecimal debet_amount,BigDecimal credit_amount,
			String	text,String code_c,
			String code_d,String code_g,
			String code_h,String activity_sequence,
			String status,String transaction_type,
			String error_text,Integer record_no,
			Date rowversion)
		throws ErroRepositorioException {
		
		StatelessSession session = HibernateUtil.getStatelessSessionIntegracaoIFS();
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		String insert = null;
		try {
			con = session.connection();
			
			//String sequence = Util.obterNextValSequence("cobranca.seq_empresa_cobranca_conta");

			insert = "INSERT INTO IFSCPSA.C_GSAN_CONTABILIDADE_TAB " 
				+ " (LOAD_ID," 
				+ "RECORD_NO," 
				+ "COMPANY," 
				+ "CURRENCY_CODE," 
				+ "TRANSACTION_DATE," 
				+ "ACCOUNTING_COD," 
				+ "COST_CENTER," 
				+ "ACCOUNTING_YEAR," 
				+ "ACCOUNTING_PERIOD,"
				+ "DEBET_AMOUNT," 
				+ "CREDIT_AMOUNT," 
				+ "TEXT," 
				+ "ACTIVITY_SEQUENCE," 
				+ "TRANSACTION_TYPE," 
				+ "ERROR_TEXT," 
				+ "STATUS,"
				+ "VOUCHER_TYPE," 
				+ "CODE_C," 
				+ "CODE_D," 
				+ "CODE_G," 
				+ "CODE_H," 
				+ "ROWVERSION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
			stmt = con.prepareStatement(insert);
			
			stmt.setString(1, load_id);
			stmt.setInt(2, record_no);
			stmt.setString(3, company);
			stmt.setString(4, currency_code);
			stmt.setDate(5,  Util.getSQLDate(transaction_date));
			
			stmt.setString(6, accounting_cod);
			stmt.setString(7, cost_center);
			stmt.setString(8, accounting_year);
			stmt.setString(9, accounting_period);
			
			stmt.setBigDecimal(10, debet_amount);
			stmt.setBigDecimal(11, credit_amount);
			
			stmt.setString(12, text);
			stmt.setString(13, activity_sequence);
			stmt.setString(14, transaction_type);
			stmt.setString(15, error_text);
			stmt.setString(16, status);
			stmt.setString(17, voucher_type);
			stmt.setString(18, code_c);
			stmt.setString(19, code_d);
			stmt.setString(20, code_g);
			stmt.setString(21, code_h);
			
			stmt.setDate(22,  Util.getSQLDate(rowversion));
			
			stmt.executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
			try {
				if(stmt != null){
					stmt.close();	
				}
				if(con != null){
					con.close();	
				}

			} catch (SQLException e) {
				System.out.println("Erro ao fechar conex�es");
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			}
		}
	}

	public Collection pesquisarOrdensServicoParaRecebimentoUPA()
			throws ErroRepositorioException {
		// obt�m a sess�o

		Session session = HibernateUtil.getSession();

		Collection retorno = new ArrayList();

		try {

			retorno = session
					.createQuery(

							"select os from OrdemServicoMovimento os inner join fetch os.atendimentoMotivoEncerramento"
									+ " left join fetch os.unidadeOrganizacionalExecutora unidExecutora "
									+ " left join fetch unidExecutora.unidadeRepavimentadora unidPavimentadora "
									+ " left join fetch unidPavimentadora.empresa "
									+ " left join fetch os.servicoTipo "
									+ " left join fetch os.registroAtendimento "
									+ " left join fetch os.pavimentoRua "
									+ " where os.indicadorMovimento = 2 and os.atendimentoMotivoEncerramento.id is not null")
					.list();
			

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exce��o para a pr�xima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sess�o

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

	public void importarOrdensServico(Collection ordensServicoParaImportacao)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		if (ordensServicoParaImportacao != null
				&& !ordensServicoParaImportacao.isEmpty()) {
			Iterator it = ordensServicoParaImportacao.iterator();

			try {
				while (it.hasNext()) {

					OrdensServico ordensServico = (OrdensServico) it.next();

					String consulta = "update OrdemServicoMovimento set "
						+ "unidadeOrganizacionalExecutora = :osUnidadeExecutora,"
						+ "loginUsuario = :osUsuarioExecutora,"
						+ "dataExecucao = :osDataEncerramento, "
						+ "servicoTipo =:servicoId, ";
						
						if (ordensServico.getOsPavimentoRua() != null) {		
							consulta = consulta + " pavimentoRua =:osPavimentoRua,";
								
						}
						
						if (ordensServico.getOsPavimentoCalcada() != null) {
							consulta = consulta + "pavimentoCalcada =:osPavimentoCalcada,";
						}
						
						consulta = consulta + "areaPavimentoRua =:osAreaPavimentoRua,"
						+ "areaPavimentoCalcada =:osAreaPavimentoCalcada,"
						+ "parecer =:osParecerEncerramento,"
						+ "dataTramite =:dataTramite, " +
						" atendimentoMotivoEncerramento =:motivoEncerramento where id = :id";
					
					

					
					Query query = session
							.createQuery(consulta)
							.setInteger("motivoEncerramento", ordensServico.getOsMotivoEncerramento())
							.setTimestamp("dataTramite", new Date())
							.setInteger("osUnidadeExecutora",
									ordensServico.getOsUnidadeExecutora())
							.setString("osUsuarioExecutora",
									ordensServico.getOsUsuarioExecutora())
							.setTimestamp("osDataEncerramento",
									ordensServico.getOsDataEncerramento())
							.setInteger("servicoId",
									ordensServico.getServicoId())
							
							
									
							.setBigDecimal(
									"osAreaPavimentoRua",
									ordensServico.getOsAreaPvtoRua() == null ? null
											: new BigDecimal(ordensServico
													.getOsAreaPvtoRua()))
							
									
							.setBigDecimal(
									"osAreaPavimentoCalcada",
									ordensServico.getOsAreaPvtoCalcada() == null ? null
											: new BigDecimal(ordensServico
													.getOsAreaPvtoCalcada()))
							.setString(
									"osParecerEncerramento",
									ordensServico.getOsParecerEncerramento() == null ? ""
											: ordensServico
													.getOsParecerEncerramento())
							.setInteger("id", ordensServico.getId());
							
							
					if (ordensServico.getOsPavimentoRua() != null) {		
						query.setParameter("osPavimentoRua", new PavimentoRua(ordensServico.getOsPavimentoRua()));
							
					}
					
					if (ordensServico.getOsPavimentoCalcada() != null) {
						query.setParameter("osPavimentoCalcada", new PavimentoCalcada(ordensServico.getOsPavimentoCalcada()));
					}
					
					query.executeUpdate();

					// Caso exista dados de Pavimento - atualizar
					// ORDEM_SERVICO_PAVIMENTO
					if (ordensServico.getOsAreaPvtoCalcada() != null
							|| ordensServico.getOsAreaPvtoRua() != null) {
						session
								.createQuery(
										"update OrdemServicoPavimento set"
												+ " pavimentoRuaRetorno =:osPavimentoRua,"
												+ " pavimentoCalcadaRetorno =:osPavimentoCalcada,"
												+ " areaPavimentoRuaRetorno =:osAreaPavimentoRua,"
												+ " areaPavimentoCalcadaRetorno =:osAreaPavimentoCalcada where ordemServico =:ordemServicoId")
								.setInteger("ordemServicoId",
										ordensServico.getId())
								.setParameter("osPavimentoRua",
									ordensServico.getOsPavimentoRua() == null ? null
											: new PavimentoRua(ordensServico.getOsPavimentoRua())
									)
								.setBigDecimal(
										"osAreaPavimentoRua",
										ordensServico.getOsAreaPvtoRua() == null ? null
												: new BigDecimal(ordensServico
														.getOsAreaPvtoRua()))
								.setParameter("osPavimentoCalcada",
									ordensServico.getOsPavimentoCalcada() == null ? null
											: new PavimentoCalcada(ordensServico.getOsPavimentoCalcada()))
								.setBigDecimal(
										"osAreaPavimentoCalcada",
										ordensServico.getOsAreaPvtoCalcada() == null ? null
												: new BigDecimal(ordensServico
														.getOsAreaPvtoCalcada()))
								.executeUpdate();

					}

					//System.out.println(resultado);
				}

			} catch (HibernateException e) {
				// levanta a exce��o para a pr�xima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	}

	public void atualizarIndicadorMovimentoOrdemServicoMovimento(
			OrdemServicoMovimento movimento) throws ErroRepositorioException {
		//		 obt�m a sess�o

		Session session = HibernateUtil.getSession();

		try {

			session.update(movimento);
			session.flush();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exce��o para a pr�xima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sess�o

			HibernateUtil.closeSession(session);

		}

	}
	
	public Object[] pesquisarHorarioProcessoIntegracaoUPA()
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object[] retorno;

		try {

			retorno = (Object[]) session
					.createQuery(

					"select horaInicioProcesso, intervaloHorasProcesso from SistemaParametro")
					.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();

			// levanta a exce��o para a pr�xima camada

			throw new ErroRepositorioException(e, "Erro no Hibernate: ");

		} finally {

			// fecha a sess�o

			HibernateUtil.closeSession(session);

		}

		return retorno;

	}

}