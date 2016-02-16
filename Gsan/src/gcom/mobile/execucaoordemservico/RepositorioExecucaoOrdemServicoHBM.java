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
package gcom.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author André Miranda
 * @date 16/11/2015
 */
@SuppressWarnings("unchecked")
public class RepositorioExecucaoOrdemServicoHBM implements IRepositorioExecucaoOrdemServico {
	private static IRepositorioExecucaoOrdemServico instancia;

	public RepositorioExecucaoOrdemServicoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 */
	public static IRepositorioExecucaoOrdemServico getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioExecucaoOrdemServicoHBM();
		}

		return instancia;
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection<Integer> pesquisarArquivosEmAbertoPorLeiturista(Integer idLeiturista) throws ErroRepositorioException {

		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String sql = "SELECT arq.aosc_id as idArquivo "
					   + "FROM mobile.arq_txt_os_cobranca arq "
					   + "WHERE arq.sitl_id in (" + SituacaoTransmissaoLeitura.LIBERADO + ", " + SituacaoTransmissaoLeitura.EM_CAMPO + ") AND " 
					   + "arq.leit_id = :idLeiturista ";

			retorno = (Collection<Integer>) session.createSQLQuery(sql)
											.addScalar("idArquivo", Hibernate.INTEGER)
											.setInteger("idLeiturista", idLeiturista).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;	
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(
										Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
										Integer idSituacaoLeituraNova, Leiturista leiturista, Date dataLiberacao) throws ErroRepositorioException {
			
			 Session session = HibernateUtil.getSession();
			 String sql = "";
			 Collection<Integer> colecaoIds = new ArrayList<Integer>();
			 Iterator iterator = colecaoArquivoTextoOSCobrancaSmartphone.iterator();
			 while(iterator.hasNext()){
				 ArquivoTxtOSCobrancaSmartphoneHelper arquivo = (ArquivoTxtOSCobrancaSmartphoneHelper)iterator.next();
				 colecaoIds.add(arquivo.getIdArquivo());
			 }
			
			 try {
				sql = " update ArquivoTextoOSCobranca set" 
					+ " sitl_id = :idSituacao,"
					+ " aosc_tmultimaalteracao = :dataUltimaAlteracao ";
	
				if (leiturista != null)
					sql += ", leit_id  = :idLeiturista ";
				
				if(idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.LIBERADO)
					|| idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.DISPONIVEL))
					sql += ", aosc_tmliberacaoarquivo = :dataLiberacao "	;
	
				sql += " where aosc_id in ( :conjuntoArquivos )";
	
				Query query =  session.createQuery(sql)
							  .setInteger("idSituacao", idSituacaoLeituraNova)
							  .setTimestamp("dataUltimaAlteracao", new Date())
							  .setParameterList("conjuntoArquivos", colecaoIds);
	
				if (leiturista != null)
					query.setInteger("idLeiturista", leiturista.getId());
				
				if(idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.LIBERADO)
						|| idSituacaoLeituraNova.equals(SituacaoTransmissaoLeitura.DISPONIVEL))
					query.setTimestamp("dataLiberacao", dataLiberacao);
	
				query.executeUpdate();
				
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new ErroRepositorioException("Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0017] - Exibir Lista de Comandos Eventuais
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<CobrancaAcaoAtividadeComando> pesquisarComandosEventuais(Integer idServicoTipo, Integer idEmpresa,
			Date dataInicial, Date dataFinal) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		dataInicial = Util.formatarDataInicial(dataInicial);
		dataFinal = Util.formatarDataFinal(dataFinal);
		
		try {
			consulta = 	"select distinct cacm from CobrancaDocumento as cbdo " +
					"join cbdo.cobrancaAcaoAtividadeComando cacm " +
					"join cacm.cobrancaAcao cbac " +
					"where cacm.comando is not null " +
					"and cacm.realizacao between :dataInicial and :dataFinal " +
					"and cbac.servicoTipo.id = :idServicoTipo " +
					"and cacm.indicadorDebito = :icDebito " +
					"and cacm.dataEncerramentoRealizada is null " +
					"and cbdo.empresa.id = :idEmpresa ";

			return session.createQuery(consulta)
					.setInteger("idServicoTipo", idServicoTipo)
					.setTimestamp("dataInicial", dataInicial)
					.setTimestamp("dataFinal", dataFinal)
					.setShort("icDebito", ConstantesSistema.SIM)
					.setInteger("idEmpresa", idEmpresa)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0005] - Exibir Lista de Localidades
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Localidade> pesquisarLocalidadeGrupoCobranca(Integer idGrupoCobranca) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct loca from Rota as rota " +
						"join rota.setorComercial stcm " +
						"join stcm.localidade loca " +
						"where rota.cobrancaGrupo.id = :idGrupoCobranca ";

			return session.createQuery(consulta)
					.setInteger("idGrupoCobranca", idGrupoCobranca)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Serviço
	 * [IT0018] - Selecionar Ordens de Serviço Cronograma
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarQuantidadeOrdemServicoCronograma(Integer idCobrancaGrupo, Integer idEmpresa,
			Integer referencia, Integer idTipoServico, Integer[] idsLocalidade) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select loca.loca_id, loca.loca_nmlocalidade, " +
						"stcm.stcm_id, stcm.stcm_cdsetorcomercial, " +
						"rota.rota_id, rota.rota_cdrota, " +
						"caac.caac_tmrealizacao, count(1) as qtd " +
						"from atendimentopublico.ordem_servico		as orse " +
						"join cobranca.cobranca_documento			as cbdo on cbdo.cbdo_id = orse.cbdo_id " +
						"join cobranca.cobranca_acao_ativ_crg		as caac on caac.caac_id = cbdo.caac_id " +
						"join cobranca.cobranca_acao_cronograma		as cbac on cbac.cbcr_id = caac.cbcr_id " +
						"join cobranca.cobranca_grupo_crg_mes		as cbcm on cbcm.cbcm_id = cbac.cbcm_id " +
						"join cadastro.imovel						as imov on imov.imov_id = orse.imov_id " +
						"join cadastro.localidade					as loca on loca.loca_id = imov.loca_id " +
						"join cadastro.setor_comercial				as stcm on stcm.stcm_id = imov.stcm_id " +
						"join cadastro.quadra						as qdra on qdra.qdra_id = imov.qdra_id " +
						"join micromedicao.rota						as rota on rota.rota_id = qdra.rota_id and rota.cbgr_id = cbcm.cbgr_id " +
						"where orse.orse_cdsituacao = :idSituacaoOS " +
						"and orse.svtp_id = :idTipoServico " +
						"and cbdo.empr_id = :idEmpresa " +
						"and cbcm.cbgr_id = :idCobrancaGrupo " +
						"and cbcm.cbcm_amreferencia = :referencia " +
						"and orse.orse_id not in ( " +
						"	select orse_id  " +
						"	from mobile.arq_txt_os_cobranca_item aoci " +
						"	inner join mobile.arq_txt_os_cobranca aosc on aoci.aosc_id = aosc.aosc_id " +
						"	where sitl_id in (1,2,3,4,5) " +
						") ";

			if (!Util.isVazioOrNulo(idsLocalidade)) {
				consulta += "and loca.loca_id in (:idsLocalidade) ";
			}

			consulta += "group by loca.loca_id, loca.loca_nmlocalidade, " +
					"stcm.stcm_id, stcm.stcm_cdsetorcomercial, " +
					"rota.rota_id, rota.rota_cdrota, " +
					"caac.caac_tmrealizacao " +
					"order by loca_id, stcm_id, rota_id ";

			Query query = session.createSQLQuery(consulta)
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("loca_nmlocalidade", Hibernate.STRING)
					.addScalar("stcm_id", Hibernate.INTEGER)
					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					.addScalar("rota_id", Hibernate.INTEGER)
					.addScalar("rota_cdrota", Hibernate.INTEGER)
					.addScalar("caac_tmrealizacao", Hibernate.TIMESTAMP)
					.addScalar("qtd", Hibernate.INTEGER)
					.setShort("idSituacaoOS", OrdemServico.SITUACAO_PENDENTE)
					.setInteger("idTipoServico", idTipoServico)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("idCobrancaGrupo", idCobrancaGrupo)
					.setInteger("referencia", referencia);

			if (!Util.isVazioOrNulo(idsLocalidade)) {
				query.setParameterList("idsLocalidade", idsLocalidade);
			}

			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0001] - Exibir Totais Ordens de Serviço
	 * [IT0019] - Selecionar Ordens de Serviço Comando
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarQuantidadeOrdemServicoComando(Integer idComando,
			Integer idEmpresa, Integer idTipoServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select loca.loca_id, loca.loca_nmlocalidade, " +
						"stcm.stcm_id, stcm.stcm_cdsetorcomercial, " +
						"rota.rota_id, rota.rota_cdrota, " +
						"cacm.cacm_tmrealizacao, count(1) as qtd " +
						"from atendimentopublico.ordem_servico	as orse " +
						"join cobranca.cobranca_documento		as cbdo	on cbdo.cbdo_id = orse.cbdo_id " +
						"join cobranca.cobranca_acao_ativ_cmd	as cacm	on cacm.cacm_id = cbdo.cacm_id " +
						"join cadastro.imovel					as imov on imov.imov_id = orse.imov_id " +
						"join cadastro.localidade				as loca on loca.loca_id = imov.loca_id " +
						"join cadastro.setor_comercial			as stcm on stcm.stcm_id = imov.stcm_id " +
						"join cadastro.quadra					as qdra on qdra.qdra_id = imov.qdra_id " +
						"join micromedicao.rota					as rota on rota.rota_id = qdra.rota_id " +
						"where orse.orse_cdsituacao = :idSituacaoOS " +
						"and orse.svtp_id = :idTipoServico " +
						"and cbdo.empr_id = :idEmpresa " +
						"and cacm.cacm_id = :idComando " +
						"and orse.orse_id not in ( " +
						"	select orse_id  " +
						"	from mobile.arq_txt_os_cobranca_item aoci " +
						"	inner join mobile.arq_txt_os_cobranca aosc on aoci.aosc_id = aosc.aosc_id " +
						"	where sitl_id in (1,2,3,4,5) " +
						") " +
						"group by loca.loca_id, loca.loca_nmlocalidade, " +
						"stcm.stcm_id, stcm.stcm_cdsetorcomercial, " +
						"rota.rota_id, rota.rota_cdrota, " +
						"cacm.cacm_tmrealizacao " +
						"order by loca_id, stcm_id, rota_id";

			Query query = session.createSQLQuery(consulta)
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("loca_nmlocalidade", Hibernate.STRING)
					.addScalar("stcm_id", Hibernate.INTEGER)
					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					.addScalar("rota_id", Hibernate.INTEGER)
					.addScalar("rota_cdrota", Hibernate.INTEGER)
					.addScalar("cacm_tmrealizacao", Hibernate.TIMESTAMP)
					.addScalar("qtd", Hibernate.INTEGER)
					.setShort("idSituacaoOS", OrdemServico.SITUACAO_PENDENTE)
					.setInteger("idTipoServico", idTipoServico)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("idComando", idComando);

			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0002] - Exibir Ordens de Serviço
	 * [IT0021] - Selecionar Ordens de Serviço Cronograma das rotas selecionadas
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarOrdemServicoCronograma(Integer[] idsRota, Integer idEmpresa,
			Integer referencia, Integer idTipoServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select orse.orse_id, loca.loca_id, loca.loca_nmlocalidade, stcm.stcm_cdsetorcomercial, " +
						"rota.rota_cdrota, imov.imov_id, orse.orse_tmgeracao, cbdo.cbdo_id, qdra.qdra_nnquadra " +
						"from atendimentopublico.ordem_servico 	as orse " +
						"join cobranca.cobranca_documento		as cbdo on cbdo.cbdo_id = orse.cbdo_id " +
						"join cobranca.cobranca_acao_ativ_crg	as caac on caac.caac_id = cbdo.caac_id " +
						"join cobranca.cobranca_acao_cronograma	as cbac on cbac.cbcr_id = caac.cbcr_id " +
						"join cobranca.cobranca_grupo_crg_mes	as cbcm on cbcm.cbcm_id = cbac.cbcm_id " +
						"join cadastro.imovel					as imov on imov.imov_id = orse.imov_id " +
						"join cadastro.localidade				as loca on loca.loca_id = imov.loca_id " +
						"join cadastro.setor_comercial			as stcm on stcm.stcm_id = imov.stcm_id " +
						"join cadastro.quadra					as qdra on qdra.qdra_id = imov.qdra_id " +
						"join micromedicao.rota					as rota on rota.rota_id = qdra.rota_id " +
						"where orse.orse_cdsituacao = :idSituacaoOS " +
						"and orse.svtp_id = :idTipoServico " +
						"and cbdo.empr_id = :idEmpresa " +
						"and cbcm.cbcm_amreferencia = :referencia " +
						"and rota.rota_id in (:idsRota) " +
						"and orse.orse_id not in ( " +
						"	select orse_id  " +
						"	from mobile.arq_txt_os_cobranca_item aoci " +
						"	inner join mobile.arq_txt_os_cobranca aosc on aoci.aosc_id = aosc.aosc_id " +
						"	where sitl_id in (1,2,3,4,5) " +
						") " +
						"order by loca.loca_id,stcm.stcm_cdsetorcomercial,rota.rota_cdrota,qdra.qdra_nnquadra ";

			Query query = session.createSQLQuery(consulta)
					.addScalar("orse_id", Hibernate.INTEGER)
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("loca_nmlocalidade", Hibernate.STRING)
					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					.addScalar("rota_cdrota", Hibernate.INTEGER)
					.addScalar("imov_id", Hibernate.INTEGER)
					.addScalar("orse_tmgeracao", Hibernate.TIMESTAMP)
					.addScalar("cbdo_id", Hibernate.INTEGER)
					.addScalar("qdra_nnquadra", Hibernate.INTEGER)
					.setShort("idSituacaoOS", OrdemServico.SITUACAO_PENDENTE)
					.setInteger("idTipoServico", idTipoServico)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("referencia", referencia)
					.setParameterList("idsRota", idsRota);

			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [SB0002] - Exibir Ordens de Serviço
	 * [IT0022] - Selecionar Ordens de Serviço Comando das rotas selecionadas
	 * 
	 * @author André Miranda
	 * @date 13/11/2015
	 */
	public Collection<Object[]> consultarOrdemServicoComando(Integer[] idsRota, Integer idEmpresa,
			Integer idTipoServico, Integer idComando) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select orse.orse_id, loca.loca_id, loca.loca_nmlocalidade, stcm.stcm_cdsetorcomercial, " +
						"rota.rota_cdrota, imov.imov_id, orse.orse_tmgeracao, cbdo.cbdo_id, qdra.qdra_nnquadra " +
						"from atendimentopublico.ordem_servico 	as orse " +
						"join cobranca.cobranca_documento		as cbdo on cbdo.cbdo_id = orse.cbdo_id " +
						"join cobranca.cobranca_acao_ativ_cmd	as cacm	on cacm.cacm_id = cbdo.cacm_id " +
						"join cadastro.imovel					as imov on imov.imov_id = orse.imov_id " +
						"join cadastro.localidade				as loca on loca.loca_id = imov.loca_id " +
						"join cadastro.setor_comercial			as stcm on stcm.stcm_id = imov.stcm_id " +
						"join cadastro.quadra					as qdra on qdra.qdra_id = imov.qdra_id " +
						"join micromedicao.rota					as rota on rota.rota_id = qdra.rota_id " +
						"where orse.orse_cdsituacao = :idSituacaoOS " +
						"and orse.svtp_id = :idTipoServico " +
						"and cbdo.empr_id = :idEmpresa " +
						"and cacm.cacm_id =  :idComando " +
						"and rota.rota_id in (:idsRota) " +
						"and orse.orse_id not in ( " +
						"	select orse_id  " +
						"	from mobile.arq_txt_os_cobranca_item aoci " +
						"	inner join mobile.arq_txt_os_cobranca aosc on aoci.aosc_id = aosc.aosc_id " +
						"	where sitl_id in (1,2,3,4,5) " +
						") " +
						"order by loca.loca_id, stcm.stcm_cdsetorcomercial, rota.rota_cdrota, qdra.qdra_nnquadra ";

			Query query = session.createSQLQuery(consulta)
					.addScalar("orse_id", Hibernate.INTEGER)
					.addScalar("loca_id", Hibernate.INTEGER)
					.addScalar("loca_nmlocalidade", Hibernate.STRING)
					.addScalar("stcm_cdsetorcomercial", Hibernate.INTEGER)
					.addScalar("rota_cdrota", Hibernate.INTEGER)
					.addScalar("imov_id", Hibernate.INTEGER)
					.addScalar("orse_tmgeracao", Hibernate.TIMESTAMP)
					.addScalar("cbdo_id", Hibernate.INTEGER)
					.addScalar("qdra_nnquadra", Hibernate.INTEGER)
					.setShort("idSituacaoOS", OrdemServico.SITUACAO_PENDENTE)
					.setInteger("idTipoServico", idTipoServico)
					.setInteger("idEmpresa", idEmpresa)
					.setInteger("idComando", idComando)
					.setParameterList("idsRota", idsRota);

			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 *
	 * [UC1484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * [IT0021] - Selecionar Débitos da OS
	 * 
	 * @author Hugo Azevedo
	 * @date 24/10/2013
	 */
	public Collection<Object[]> obterItensDocCobranca(Integer idOrdemServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		String consulta = "";

		try {
			consulta = 
					" SELECT  " +
					" CASE WHEN cnta.cnta_id IS NOT NULL THEN cnta.cnta_amreferenciaconta ELSE cnhi.cnhi_amreferenciaconta END AS anoMesConta, " +
					" CASE WHEN cnta.cnta_id IS NOT NULL THEN cnta.cnta_dtvencimentoconta ELSE cnhi.cnhi_dtvencimentoconta END AS dtVencimento, " +                
					" CASE WHEN cnta.cnta_id IS NOT NULL THEN dcst1.dcst_dsabreviado      ELSE dcst2.dcst_dsabreviado      END AS debitoCreditoSituacao, " +  
					" cbdo.cdit_vlitemcobrado AS valorConta " +    
					" FROM cobranca.cobranca_documento_item cbdo " +
					" INNER JOIN atendimentopublico.ordem_servico orse  	ON orse.cbdo_id = cbdo.cbdo_id " + 
					" LEFT JOIN faturamento.conta cnta  					ON cnta.cnta_id = cbdo.cnta_id " +
					" LEFT JOIN faturamento.debito_credito_situacao dcst1  	ON cnta.dcst_idatual = dcst1.dcst_id " + 
					" LEFT JOIN faturamento.conta_historico cnhi  			ON cnhi.cnta_id = cbdo.cnta_id " + 
					" LEFT JOIN faturamento.debito_credito_situacao dcst2  	ON cnhi.dcst_idatual  = dcst2.dcst_id " + 
					" WHERE orse.orse_id = :idOS " +
					" AND cbdo.cnta_id IS NOT NULL ";

			retorno = session.createSQLQuery(consulta)
							 .addScalar("anoMesConta", Hibernate.INTEGER)
							 .addScalar("dtVencimento", Hibernate.DATE)
							 .addScalar("debitoCreditoSituacao", Hibernate.STRING)
							 .addScalar("valorConta", Hibernate.BIG_DECIMAL)
							 .setInteger("idOS", idOrdemServico)
							 .list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 19/11/2015
	 */
	public Collection<Object[]> consultarArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		
		String sql = "SELECT loca.loca_id 				AS idLocalidade, " //0
				+ "  orse.orse_id 						AS idOS, " //1
				+ "  svtp.svtp_id 						AS idServicoTipo, " //2
				+ "  imov.imov_id 						AS idImovel, " //3
				+ "  iper.iper_dsimovelperfil 			AS dsImovelPerfil, " //4
				+ "  catg.catg_id 						AS idCategoriaPrincipal, " //5
				+ "  catg.catg_dscategoria 				AS dsCategoriaPrincipal, " //6
				+ "  last.last_dsligacaoaguasituacao 	AS dsLigacaoAguaSituacao, " //7
				+ "  lest.lest_dsligacaoesgotosituacao 	AS dsLigacaoEsgotoSituacao, "//8
				+ "  usur.usur_id 						AS idUsuario, "//9
				+ "  usur.usur_nmlogin 					AS loginUsuario, "//10
				+ "  usur.usur_nmsenha 					AS senhaUsuario, "//11
				+ "  leit.leit_nnimei 					AS imeiLeiturista,"//12
				+ "  leit.leit_id 						AS idLeiturista, "//13
				+ "  imov.loca_id 						AS idLocalidade, " //14
				+ "  orse.orse_tmgeracao				AS dtGeracaoOS " //15
//				+ "  ,leit.leit_nnenderecomac 			AS enderecoMac "
				+ "FROM MOBILE.arq_txt_os_cobranca_item item "
				+ "INNER JOIN mobile.arq_txt_os_cobranca aosc 					ON aosc.aosc_id = item.aosc_id "
				+ "INNER JOIN ATENDIMENTOPUBLICO.ordem_servico orse 			on item.orse_id = orse.orse_id "
				+ "INNER JOIN CADASTRO.imovel imov 								ON orse.imov_id = imov.imov_id "
				+ "INNER JOIN CADASTRO.localidade loca 							ON imov.loca_id = loca.loca_id "
				+ "INNER JOIN CADASTRO.gerencia_regional greg 					ON loca.greg_id = greg.greg_id "
				+ "INNER JOIN CADASTRO.unidade_negocio uneg 					ON loca.uneg_id = uneg.uneg_id "
				+ "INNER JOIN CADASTRO.setor_comercial stcm 					ON imov.stcm_id = stcm.stcm_id "
				+ "INNER JOIN CADASTRO.quadra qdra 								ON qdra.qdra_id = imov.qdra_id "
				+ "INNER JOIN ATENDIMENTOPUBLICO.servico_tipo svtp 				ON orse.svtp_id = svtp.svtp_id "
				+ "INNER JOIN CADASTRO.imovel_perfil iper 						ON iper.iper_id = imov.iper_id "
				+ "INNER JOIN CADASTRO.categoria catg 							ON catg.catg_id = imov.imov_idcategoriaprincipal "
				+ "INNER JOIN atendimentopublico.ligacao_agua_situacao last	 	ON last.last_id = imov.last_id "
				+ "INNER JOIN atendimentopublico.ligacao_esgoto_situacao lest 	ON lest.lest_id = imov.lest_id "				
				+ "INNER JOIN micromedicao.leiturista leit 						ON leit.leit_id = aosc.leit_id "
				+ "INNER JOIN seguranca.usuario usur 							ON usur.usur_id = leit.usur_id "
				
				+ "WHERE " 
				+ " item.aosc_id = :ididArquivoTextoOSCobranca ";//and "
				//+ " not exists( select orse_id from mobile.arq_txt_os_cobranca_item aoscItemTemp where aoscItemTemp.orse_id = item.orse_id )  ";
		
			sql += " ORDER BY loca.loca_id, stcm.stcm_cdsetorcomercial, qdra.qdra_nnquadra, imov.imov_nnlote, imov.imov_nnsublote ";

		try {
			
			Query query = session.createSQLQuery(sql)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("idOS", Hibernate.INTEGER)
					.addScalar("idServicoTipo", Hibernate.INTEGER)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("dsImovelPerfil", Hibernate.STRING)
					.addScalar("idCategoriaPrincipal", Hibernate.INTEGER)
					.addScalar("dsCategoriaPrincipal", Hibernate.STRING)
					.addScalar("dsLigacaoAguaSituacao", Hibernate.STRING)
					.addScalar("dsLigacaoEsgotoSituacao", Hibernate.STRING)
					.addScalar("idUsuario", Hibernate.INTEGER)
					.addScalar("loginUsuario", Hibernate.STRING)
					.addScalar("senhaUsuario", Hibernate.STRING)
					.addScalar("imeiLeiturista", Hibernate.LONG)
					.addScalar("idLeiturista", Hibernate.INTEGER)
					.addScalar("idLocalidade", Hibernate.INTEGER)
					.addScalar("dtGeracaoOS", Hibernate.DATE);
//					.addScalar("enderecoMac", Hibernate.STRING);

			query.setInteger("ididArquivoTextoOSCobranca", idArquivoTextoOSCobranca);
			
			retorno = query.list();
			
			return retorno;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public Collection<Object[]> consultarDadosArquivoTextoOSCobranca(Integer idEmpresa, Integer idTipoServico, 
			Integer referenciaCronograma, Integer idCobrancaGrupo, Integer[] idsLocalidade, Integer idComandoEventual,Integer idAgenteComercial,Integer idSituacao) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;
		Map parameters = new HashMap();
		try {
			
			String sql = " SELECT "
					+ "CASE WHEN (select count(distinct loca_id) from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id) = 1 "
					+ "      THEN (select distinct loca_id from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id) "
					+ "      ELSE 0 END AS idLocalidade,"
					+ " CASE WHEN (select count(distinct aoci_cdsetorcomercial) from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id) = 1" 
					+ "      THEN (select distinct aoci_cdsetorcomercial from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id)" 
					+ " 	 ELSE 0 END AS codSetor,"
					+ " CASE WHEN (select count(distinct aoci_cdrota) from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id) = 1" 
					+ " 	 THEN (select distinct aoci_cdrota from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id)" 
					+ " 	 ELSE 0 END AS codRota,"
					+ " CASE WHEN (select count(distinct aoci_nnquadra) from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id) = 1  "
					+ "      THEN (select distinct aoci_nnquadra from mobile.arq_txt_os_cobranca_item aoci WHERE aoci.aosc_id = aosc.aosc_id) "
					+ "      ELSE 0 END AS nnQuadra,"
					+ " aosc_nnqtdordemservico 	AS qtdeOS," 
					+ " clie.clie_nmcliente     AS nomeCliente,"
					+ " sitl.sitl_dssituacao 	AS situacao,"
					+ " sitl.sitl_id            AS idSituacao,"
					+ " aosc_tmliberacaoarquivo AS dataLiberacao,"
					+ " aosc.aosc_id 			AS idArquivo,"
					+ " leit.leit_nnimei        AS imei,"
					+ " leit.leit_id            AS idLeiturista,"
					+ " func.func_nmfuncionario AS nomeFuncionario"
					+ " FROM mobile.arq_txt_os_cobranca aosc"
					+ " INNER JOIN mobile.par_arq_txt_os_cobranca posc			ON posc.posc_id = aosc.posc_id"
					+ " INNER JOIN micromedicao.leiturista leit 				ON leit.leit_id = aosc.leit_id" 
					+ " LEFT  JOIN cadastro.cliente clie 						ON clie.clie_id = leit.clie_id"
					+ " LEFT  JOIN cadastro.funcionario func 					ON func.func_id = leit.func_id"
					+ " INNER JOIN micromedicao.situacao_transm_leitura sitl 	ON sitl.sitl_id = aosc.sitl_id"
					+ " WHERE"
					+ " posc.empr_id = :idEmpresa "     			
					+ " AND posc.svtp_id = :idTipoServico ";
			
			parameters.put("idEmpresa", idEmpresa);
			parameters.put("idTipoServico", idTipoServico);
			
			if(referenciaCronograma != null){
				sql += " AND posc.posc_amreferenciacronograma = :referenciaCronograma ";
				parameters.put("referenciaCronograma", referenciaCronograma);
			}
			if(idCobrancaGrupo != null){
				sql += " AND posc.cbgr_id = :idCobrancaGrupo ";
				parameters.put("idCobrancaGrupo", idCobrancaGrupo);
			}
			if(!Util.isVazioOrNulo(idsLocalidade)){
				sql += " AND posc.posc_id IN (SELECT polo.posc_id FROM mobile.param_arq_txt_os_loca polo WHERE posc.posc_id = polo.posc_id AND polo.loca_id IN (:idsLocalidade)) ";
				parameters.put("idsLocalidade", idsLocalidade);
			}
			if(idComandoEventual != null){
				sql += " AND posc.cacm_id = :idComandoEventual ";
				parameters.put("idComandoEventual", idComandoEventual);
			}
			if (idAgenteComercial != null){
				sql += " AND leit.leit_id = :idAgenteComercial ";
				parameters.put("idAgenteComercial", idAgenteComercial);
			}
			if (idSituacao != null){
				sql += " AND aosc.sitl_id = :idSituacao ";
				parameters.put("idSituacao", idSituacao);
			}
			
			sql += " ORDER BY idLocalidade,codSetor,codRota ";
			
			Query query = session.createSQLQuery(sql)
						  .addScalar("idLocalidade", Hibernate.INTEGER)
						  .addScalar("codSetor", Hibernate.INTEGER)
						  .addScalar("codRota", Hibernate.INTEGER)
						  .addScalar("qtdeOS", Hibernate.INTEGER)
						  .addScalar("nomeCliente", Hibernate.STRING)
						  .addScalar("situacao", Hibernate.STRING)
						  .addScalar("dataLiberacao", Hibernate.DATE)
						  .addScalar("idArquivo", Hibernate.INTEGER)
						  .addScalar("idSituacao",Hibernate.INTEGER)
						  .addScalar("imei", Hibernate.LONG)
						  .addScalar("idLeiturista",Hibernate.INTEGER)
						  .addScalar("nomeFuncionario", Hibernate.STRING)
						  .addScalar("nnQuadra",Hibernate.INTEGER);

			Set set = parameters.keySet();
			Iterator iterMap = set.iterator();
			while (iterMap.hasNext()) {
				String key = (String) iterMap.next();
				if (parameters.get(key) instanceof Set) {
					Set setList = (HashSet) parameters.get(key);
					query.setParameterList(key, setList);
				} else if (parameters.get(key) instanceof Collection) {
					Collection collection = (ArrayList) parameters.get(key);
					query.setParameterList(key, collection);
				} else if (parameters.get(key) instanceof Integer[]) {
						Integer[] collection = (Integer[]) parameters.get(key);
						query.setParameterList(key, collection);
				} else {
					query.setParameter(key, parameters.get(key));
				}
			}
			retorno = query.list();
			return retorno;

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public List<Integer> pesquisarSetorArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct aosc.aoci_cdsetorcomercial from " +
					   "mobile.arq_txt_os_cobranca_item  aosc where aosc.aosc_id = :idArquivoTextoOSCobranca " +
					   "order by aosc.aoci_cdsetorcomercial";
			
			Query query = session.createSQLQuery(consulta)
							     .addScalar("aoci_cdsetorcomercial", Hibernate.INTEGER).
							      setInteger("idArquivoTextoOSCobranca", idArquivoTextoOSCobranca);
			
			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public List<Integer> pesquisarRotaArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct aosc.aoci_cdrota from " +
					   "mobile.arq_txt_os_cobranca_item  aosc where aosc.aosc_id = :idArquivoTextoOSCobranca " +
					   "order by  aosc.aoci_cdrota";
			
			Query query = session.createSQLQuery(consulta)
							     .addScalar("aoci_cdrota", Hibernate.INTEGER).
							      setInteger("idArquivoTextoOSCobranca", idArquivoTextoOSCobranca);
			
			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author Vivianne Sousa	
	 * @date 23/11/2015
	 */
	public List<Integer> pesquisarLocalidadeArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct aosc.loca_id from " +
					   "mobile.arq_txt_os_cobranca_item  aosc where aosc.aosc_id = :idArquivoTextoOSCobranca " + 
					   "order by aosc.loca_id";
			
			Query query = session.createSQLQuery(consulta)
							     .addScalar("loca_id", Hibernate.INTEGER).
							      setInteger("idArquivoTextoOSCobranca", idArquivoTextoOSCobranca);
			
			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author André Miranda
	 * @date 24/11/2015
	 * @return Lista de idConta
	 */
	public List<Integer> pesquisarIdContaCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta = null;

		try {
			consulta = "select cnta_id " +
					"from cobranca.cobranca_documento_item " +
					"where cnta_id is not null " +
					"and cbdo_id = :idCobrancaDocumento " +
					"and (cdst_id = :idSituacaoDebito or cdst_id is null) ";

			return session.createSQLQuery(consulta)
					.addScalar("cnta_id", Hibernate.INTEGER)
					.setInteger("idCobrancaDocumento", idCobrancaDocumento)
					.setInteger("idSituacaoDebito", CobrancaDebitoSituacao.PENDENTE)
					.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobrança para Smartphone
	 * Retorna as informações das ordens de serviço 
	 * 
	 * @author Bruno Barros,Vivianne Sousa
	 * @date 27/06/2013,24/11/2015
	 * 
	 * @param Integer
	 * 
	 * @return Collection<Object[]> 
	 * @throws ControladorException
	 */
	public Collection<Object[]> pesquisarDadosOrdensServicoCobrancaSmartphone(
			Integer idArquivoTexto)	throws ErroRepositorioException {

		Session sessao = HibernateUtil.getSession();
		Collection<Object[]> retorno = null;

		try {
			
			String consulta = 
					" SELECT \n" +  
					"   EOOS.ORSE_ID idOrdemServico, \n" + // 0
					"   orse.IMOV_ID idImovel, \n" + // 1
					"   EOOS_CDSITUACAO situacaoOS, \n" + // 2
					"   EOOS_TMRECEBIMENTO dataRecebimento, \n" + // 3
					"   EOOS_ICCONFERIDA indicadorConferida, \n" + // 4
					"   rota.ftgr_id AS grupoFaturamento, \n" + // 5
					"   last.last_dsligacaoaguasituacao AS ligAguaSituacao, \n" + //6
					"   lest.lest_dsligacaoesgotosituacao AS ligEsgSituacao, \n" + // 7 
					"   lesg.lesg_nnconsumominimoesgoto AS consumoFixoEsgoto, \n" +  // 8
					"   imov.imov_tmultimaalteracao AS ultimaAlteracao, \n" + // 9
					"   svtp.svtp_dsservicotipo AS descServivoTipo, \n" + // 10
					"   svtp.svtp_icfiscalizacaoinfracao as indicadorFiscalizacaoInfracao \n" + //11
					" FROM \n" + 
					"   MOBILE.EXE_OS_ORDEM_SERVICO EOOS \n" +
					"   INNER JOIN atendimentopublico.ordem_servico orse ON EOOS.orse_id = orse.orse_id \n" +
					"   INNER JOIN atendimentopublico.servico_tipo svtp on svtp.svtp_id = orse.svtp_id " +
					"   INNER JOIN cadastro.imovel imov ON imov.imov_id = orse.imov_id \n" +
					"   INNER JOIN cadastro.quadra qdra ON qdra.qdra_id = imov.qdra_id \n" +
					"   INNER JOIN micromedicao.rota rota ON qdra.rota_id = rota.rota_id \n" +
					"   LEFT JOIN atendimentopublico.ligacao_agua_situacao last ON last.last_id = imov.last_id \n" +
					"   LEFT JOIN atendimentopublico.ligacao_esgoto_situacao lest ON lest.lest_id = imov.lest_id \n" +
					"   LEFT JOIN atendimentopublico.ligacao_esgoto lesg ON lesg.lesg_id = orse.imov_id \n" +
					" WHERE \n" + 
					"   EOOS.AOSC_ID = :idArquivoTexto \n" +
					" ORDER BY \n" + 
					"   EOOS.orse_id";
			
			retorno = sessao.createSQLQuery(consulta)
					.addScalar("idOrdemServico", Hibernate.INTEGER)
					.addScalar("idImovel", Hibernate.INTEGER)
					.addScalar("situacaoOS", Hibernate.SHORT)
					.addScalar("dataRecebimento", Hibernate.TIMESTAMP)
					.addScalar("indicadorConferida", Hibernate.INTEGER)
					.addScalar("grupoFaturamento", Hibernate.INTEGER)
					.addScalar("ligAguaSituacao", Hibernate.STRING)
					.addScalar("ligEsgSituacao", Hibernate.STRING)
					.addScalar("consumoFixoEsgoto", Hibernate.INTEGER)
					.addScalar("ultimaAlteracao", Hibernate.TIMESTAMP)
					.addScalar("descServivoTipo", Hibernate.STRING)
					.addScalar("indicadorFiscalizacaoInfracao", Hibernate.INTEGER)
					.setInteger("idArquivoTexto", idArquivoTexto).list();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}
		return retorno;
	}

	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException
	 * @date   25/11/2015
	 */
	public Collection obterColecaoAgenteComercial(Integer idEmpresa) throws ErroRepositorioException {
		String sql = "";
		Session session = HibernateUtil.getSession();
		Collection retorno = null;

		try {
			sql = " select lei.leit_id," + " cli.clie_nmcliente,"
				+ " fun.func_nmfuncionario"
				+ " from micromedicao.leiturista lei "
				+ " left join cadastro.cliente cli on cli.clie_id = lei.clie_id "
				+ " left join cadastro.funcionario fun on fun.func_id = lei.func_id "
				+ " where lei.leit_icagencomercial = :agenteComercial and lei.usur_id IS NOT NULL "
				+ " AND lei.empr_id = :idEmpresa AND lei.leit_icuso = :icUso"
				+ " order by cli.clie_nmcliente, fun.func_nmfuncionario";

			retorno = session.createSQLQuery(sql)
				.addScalar("leit_id", Hibernate.INTEGER)
				.addScalar("clie_nmcliente", Hibernate.STRING)
				.addScalar("func_nmfuncionario", Hibernate.STRING)
				.setInteger("agenteComercial", ConstantesSistema.INDICADOR_USO_ATIVO)
				.setInteger("idEmpresa", idEmpresa)
				.setInteger("icUso", ConstantesSistema.INDICADOR_USO_ATIVO)
				.list();

			return retorno;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */
	public Object[] pesquisarDadosEncerramentoOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			
			String sql = "SELECT " +
						 "dadosOS.eoos_tmexecucao as dataExecucao, " +
						 "motEnc.amen_id as idMotivoEncerramento, " +
						 "motEnc.amen_icexecucao as indicadorExecucao, " +
						 "dadosOS.eoos_dsparecerencerramento as parecerEncerramento, " +
						 "servTipo.svtp_icvistoria as indicadorVistoria, " + 
						 "servTipo.svtp_icpavimento as indicadorPavimento, " + 
						 "os.svtp_idreferencia as idServicoTipoReferencia " +
						 "FROM mobile.exe_os_ordem_servico dadosOS " +
						 "INNER JOIN atendimentopublico.atend_motivo_encmt motEnc on motEnc.amen_id = dadosOS.amen_id " + 
						 "INNER JOIN atendimentopublico.servico_tipo servTipo on servTipo.svtp_id = dadosOS.svtp_id " + 
						 "INNER JOIN atendimentopublico.ordem_servico os on os.orse_id = dadosOS.orse_id " +
						 "WHERE dadosOS.aosc_id = :idArquivo " +
					     "AND dadosOS.orse_id = :idOS";
					
			retorno = (Object[]) session.createSQLQuery(sql)
					.addScalar("dataExecucao", Hibernate.TIMESTAMP)
					.addScalar("idMotivoEncerramento", Hibernate.INTEGER)
					.addScalar("indicadorExecucao", Hibernate.SHORT)
					.addScalar("parecerEncerramento", Hibernate.STRING)
					.addScalar("indicadorVistoria", Hibernate.SHORT)
					.addScalar("indicadorPavimento", Hibernate.SHORT)
					.addScalar("idServicoTipoReferencia", Hibernate.INTEGER)
					.setInteger("idArquivo", idArquivo)
					.setInteger("idOS", idOS)
					.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;	
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */	
	public Collection<ExecucaoOSFoto> pesquisarFotosOSCobrancaSmartphone(Integer idArquivo, Integer idOS) 
			throws ErroRepositorioException {
		
		Collection<ExecucaoOSFoto> retorno = null;
		
		Session session = HibernateUtil.getSession();		
		try {						
			String hql = "SELECT foto FROM ExecucaoOSFoto foto " +
						 "WHERE foto.arquivoTextoOSCobranca.id = :idArquivo AND " +
						 "foto.ordemServico.id = :idOS ";						
			retorno = session.createQuery(hql)
					.setInteger("idArquivo", idArquivo)
					.setInteger("idOS", idOS)
					.list();		
		} catch (HibernateException e) {			
			throw new ErroRepositorioException(e, "Erro no Hibernate");		
		} finally {			
			HibernateUtil.closeSession(session);		
		}
		
		return retorno;
	}
	

	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 18/06/2013
	 */
	public Object[] pesquisarDadosCorteOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ErroRepositorioException {
		
		Object[] retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			String sql = "SELECT dadosCorte.mtco_id as idMotivoCorte, " +
						 "dadosCorte.cotp_id as idTipoCorte,  " +
						 "dadosCorte.eoco_nnselo as numeroSelo, " +
						 "dadosCorte.eoco_nnleituracorte as leituraCorte, " +
						 "dadosOS.eoos_tmexecucao as dataCorte " +
						 "FROM mobile.exe_os_corte dadosCorte " +
						 "INNER JOIN mobile.exe_os_ordem_servico dadosOS ON dadosOS.aosc_id = dadosCorte.aosc_id AND dadosOS.orse_id = dadosCorte.orse_id " +
						 "WHERE dadosCorte.aosc_id = :idArquivo " +
					     "AND dadosCorte.orse_id = :idOS";
					
			retorno = (Object[]) session.createSQLQuery(sql)
					.addScalar("idMotivoCorte", Hibernate.INTEGER)
					.addScalar("idTipoCorte", Hibernate.INTEGER)
					.addScalar("numeroSelo", Hibernate.INTEGER)
					.addScalar("leituraCorte", Hibernate.INTEGER)
					.addScalar("dataCorte", Hibernate.TIMESTAMP)
					.setInteger("idArquivo", idArquivo)
					.setInteger("idOS", idOS)
					.setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;	
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 04/07/2013
	 */
	public void atualizarSituacaoOSSmartphone(Integer idArquivo, Integer idOS, Short situacao) throws ErroRepositorioException {
		
		Session session = HibernateUtil.getSession();
		String update;

		try {
			update = "UPDATE ExecucaoOSOrdemServico "
					+ "SET eoos_tmultimaalteracao = :ultimaAlteracao, "
					+ "eoos_cdsituacao = :situacao "
					+ "WHERE aosc_id = :idArquivo and orse_id = :idOS ";

			session.createQuery(update)
                .setTimestamp("ultimaAlteracao", new Date())
                .setShort("situacao",situacao)
                .setInteger("idArquivo",idArquivo)
                .setInteger("idOS",idOS)
                .executeUpdate();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Rafael Correa
	 * @date 25/06/2013
	 */
	public Collection<Object[]> pesquisarDadosFiscalizacaoOSCobrancaSmartphone(Integer idArquivo, Integer idOS)
			throws ErroRepositorioException {
		
		Collection<Object[]> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			String sql = "SELECT os.cbdo_id as idDocumentoCobranca, " +
						 "dadosFiscalizacao.dotp_id as documentoTipo, " +	 
						 "dadosOS.eoos_tmexecucao as dataExecucao, " +
						 "sitEncontradas.fzst_id as idFiscalizacaoSituacao " +
						 "FROM mobile.exe_os_fiscalizacao dadosFiscalizacao " +
						 "INNER JOIN mobile.exe_os_ordem_servico dadosOS ON dadosOS.aosc_id = dadosFiscalizacao.aosc_id AND dadosOS.orse_id = dadosFiscalizacao.orse_id " +
						 "INNER JOIN atendimentopublico.ordem_servico os on os.orse_id = dadosOS.orse_id " +
						 "INNER JOIN mobile.exe_os_st_encontradas sitEncontradas on sitEncontradas.aosc_id = dadosFiscalizacao.aosc_id and sitEncontradas.orse_id = dadosFiscalizacao.orse_id " +
						 "WHERE dadosFiscalizacao.aosc_id = :idArquivo " +
					     "AND dadosFiscalizacao.orse_id = :idOS";
					
			retorno = (Collection<Object[]>) session.createSQLQuery(sql)
					.addScalar("idDocumentoCobranca", Hibernate.INTEGER)
					.addScalar("documentoTipo", Hibernate.INTEGER)
					.addScalar("dataExecucao", Hibernate.TIMESTAMP)
					.addScalar("idFiscalizacaoSituacao", Hibernate.INTEGER)
					.setInteger("idArquivo", idArquivo)
					.setInteger("idOS", idOS)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;	
	}
	
	/**
	 * [UC01484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa	
	 * @date 30/11/2015
	 */
	public Collection<DocumentoTipo> pesquisarDocumentoTipoDaSituacaoFiscalizacao() throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct documentoTipo " +
					    "from FiscalizacaoSituacao  " ;
				
			return session.createQuery(consulta).list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Verifica se entre lista de Ids passados como parâmetro existe algum
	 * com situação diferente de DISPONÍVEL
	 * 
	 * @author Bruno Barros
	 * @date 19/09/2013
	 * @param ids
	 * @throws ErroRepositorioException
	 */
	public Integer verificarSituacaoArquivosOSCobrancaParaExclusao( String[] ids ) 
			throws ErroRepositorioException{
		
		Session sessao = HibernateUtil.getSession();
		
		Integer retorno;
		
		String scriptSql = "";
		try {
			scriptSql = 
					"select count(*) qtd from MOBILE.arq_txt_os_cobranca where aosc_id in ( :ids ) and sitl_id <> 1";

			retorno = (Integer)sessao.createSQLQuery(scriptSql)
					.addScalar("qtd", Hibernate.INTEGER )
					.setParameterList( "ids", ids)
					.uniqueResult();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}
		
		return retorno;
	}
	
	/**
	 * [UC-1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * [IT0016] Excluir Arquivo
	 * 
	 * Exclui da base de dados os IDs passados como parâmetros
	 * 
	 * @author Bruno Barros,Jean Varela
	 * @date 19/09/2013,01/12/2015
	 * @param ids
	 * @throws ErroRepositorioException
	 */	
	public void excluirArquivosOSCobranca(String[] ids) throws ErroRepositorioException {
	
		Session session = HibernateUtil.getSession();
		 
	    try {
	    	
			String sql = "select posc_id from mobile.arq_txt_os_cobranca where "
					   + "aosc_id in ( :ids ) ";
	    	
	    	// Selecionamos os ids dos parâmetros usados para gerar os arquivos
			Collection<Integer> idsParametros = (Collection<Integer>) session.createSQLQuery(sql)
																	  .addScalar("posc_id", Hibernate.INTEGER)
																	  .setParameterList("ids", ids).list();
	    	
	        // Primeiro os itens
	    	sql = "delete from gcom.mobile.execucaoordemservico.ArquivoTextoOSCobrancaItem item where item.arquivoTextoOSCobranca.id  in ( :ids )";
	    	session.createQuery(sql).setParameterList("ids", ids).executeUpdate();
	        
	        // Apaga os clientes
	    	sql = "delete from gcom.mobile.execucaoordemservico.ArquivoTextoOSCobrancaCliente cliente where cliente.arquivoTextoOSCobranca.id in ( :ids )";
			session.createQuery(sql).setParameterList("ids", ids).executeUpdate();
	        
	        // Apaga o arquivo
	    	sql = "delete from gcom.mobile.execucaoordemservico.ArquivoTextoOSCobranca arquivo where arquivo.id in ( :ids )";
	    	session.createQuery(sql).setParameterList("ids", ids).executeUpdate();	
	    	
	    	//Apaga o parametro localidade
	        sql = "delete from gcom.mobile.execucaoordemservico.ParametrosArquivoTextoOSLocalidade parmLoca where parmLoca.parametrosArquivoTextoOSCobranca.id in (:idsParametros)";
	        session.createQuery(sql).setParameterList("idsParametros", idsParametros).executeUpdate();	        
	     
	    	sql = "delete from gcom.mobile.execucaoordemservico.ParametrosArquivoTextoOSCobranca where id in ( :ids )";
	    	session.createQuery(sql).setParameterList("ids", idsParametros).executeUpdate();
	        
	    } catch (HibernateException e) {
	        throw new ErroRepositorioException(e, "Erro no Hibernate");
	    } finally {
	        HibernateUtil.closeSession(session);
	    }
	}
	
	/**
	 * [UC1499] Consultar Dados Arquivo Texto OS Cobranca para Smartphone
	 * 
	 * @author Vivianne Sousa
	 * @date 02/12/2015
	 */
	public Collection<String> pesquisarLocalidadesArquivo(Integer idArquivo ) 
			throws ErroRepositorioException{
		
		Session sessao = HibernateUtil.getSession();
		Collection<String> retorno;
		String scriptSql = "";
		try {
			scriptSql = "select distinct(loca.loca_nmlocalidade) as idlocalidade"
					+ " from mobile.param_arq_txt_os_loca polo"
					+ " inner join cadastro.localidade loca          ON loca.loca_id = polo.loca_id"
					+ " inner join mobile.arq_txt_os_cobranca aosc   ON aosc.posc_id = polo.posc_id"
					+ " where aosc.aosc_id = :idArquivo ";

			retorno = sessao.createSQLQuery(scriptSql)
					.addScalar("idlocalidade", Hibernate.STRING)
					.setInteger("idArquivo", idArquivo)
					.list();
					
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(sessao);
		}
		return retorno;
	}
	
	/**
	 * [UC-1487] Processar Arquivo Texto Dispositivo Movel Execução de OS
	 * 
	 * Pesquisa o Arquivo Texto da Execução de OS para o imei informado
	 * 
	 * @author Bruno Barros
	 * @date 17/06/2013
	 * 
	 * @param long imei - Imei para seleção do arquivo de ida
	 * 
	 * @return Id do leiturista que esta pesquisando o arquivo
	 * 
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarArquivoTextoExecucaoOS(Integer leiturista) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Integer retorno;
		String consulta;
			try {
				 consulta = "select aosc_id from mobile.arq_txt_os_cobranca where  " +
 						    "leit_id = :idLeiturista and sitl_id = 2";

				retorno = (Integer)session.createSQLQuery(consulta)
								  .addScalar("aosc_id",Hibernate.INTEGER)
						          .setInteger("idLeiturista", leiturista)
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
	 * [UC1484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public Integer obterNumDiasVencimentoCobrancaAcao(Integer idOrdemServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		String consulta = "";

		try {
			consulta = 
					"SELECT cbac.cbac_nndiasvencimento as numDiasVencimento " +
					"FROM cobranca.cobranca_documento cbdo " +
					"INNER JOIN atendimentopublico.ordem_servico orse ON orse.cbdo_id = cbdo.cbdo_id " +
					"INNER JOIN cobranca.cobranca_acao cbac           ON cbac.cbac_id = cbdo.cbac_id " +
					"WHERE orse.orse_id = :idOS ";

			retorno = (Integer)session.createSQLQuery(consulta)
							 .addScalar("numDiasVencimento", Hibernate.INTEGER)
							 .setInteger("idOS", idOrdemServico)
							 .uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public ArquivoTextoOSCobranca pesquisarArquivoTextoOSCobrancaEmCampoPorCadastrador(Integer idAgenteComercial)  throws ErroRepositorioException{
		ArquivoTextoOSCobranca retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT arquivo " +
					" FROM ArquivoTextoOSCobranca arquivo " +
					" LEFT JOIN FETCH arquivo.parametrosArquivoTextoOSCobranca parametro " +
					" LEFT JOIN FETCH parametro.cobrancaAcaoAtividadeComando " +
					" LEFT JOIN FETCH parametro.cobrancaGrupo " +
					" WHERE arquivo.leiturista.id = :idAgenteComercial" +
					" AND arquivo.situacao.id IN (2, 3)";

			retorno = (ArquivoTextoOSCobranca) session.createQuery(consulta)
					.setInteger("idAgenteComercial", idAgenteComercial)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public CobrancaDocumento pesquisarDocumentoCobrancaOS(Integer idOrdemServico)  throws ErroRepositorioException{
		CobrancaDocumento retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT cbdo " +
					" FROM OrdemServico orse " +
					" LEFT JOIN orse.cobrancaDocumento cbdo " +
					" WHERE orse.id = :idOrdemServico ";

			retorno = (CobrancaDocumento) session.createQuery(consulta)
					.setInteger("idOrdemServico", idOrdemServico)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * @author Vivianne Sousa
	 * @date 04/12/2015
	 */
	public String pesquisarArquivoEmCampoPorAgenteComercial(Integer idAgenteComercial) throws ErroRepositorioException{
		Session session = HibernateUtil.getSession();
		try{
			String consulta = 
				"SELECT " +
				"CASE WHEN posc.cbgr_id is null " +
				"     THEN CONCAT (' COMANDO EVENTUAL ' , cacm.cacm_dstitulo , '', '') " +
				"     ELSE CONCAT (' ANO/MES ' , posc_amreferenciacronograma, ' GRUPO DE COBRANCA ', cbgr_dscobrancagrupo) " +
				"END  AS arquivo " +
				"FROM mobile.arq_txt_os_cobranca aosc " + 
				"INNER JOIN mobile.par_arq_txt_os_cobranca posc  ON aosc.posc_id = posc.posc_id " + 	
				"LEFT JOIN  cobranca.cobranca_acao_ativ_cmd cacm ON cacm.cacm_id = posc.cacm_id " +
				"LEFT JOIN  cobranca.cobranca_grupo cbgr 	     ON cbgr.cbgr_id = posc.cbgr_id " +
				"WHERE " +
				"aosc.leit_id= :idAgente AND aosc.sitl_id in (2,3) " ;

			return  (String)session.createSQLQuery(consulta)
				   .addScalar("arquivo", Hibernate.TEXT)
				   .setInteger("idAgente", idAgenteComercial)
				   .setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * @author Vivianne Sousa
	 * @date 15/12/2015
	 */
	public Integer pesquisarQtdeExecucaoOSOrdemServico(Integer idArquivoTextoOSCobranca)  throws ErroRepositorioException{
		Integer retorno = null;
		Session session = HibernateUtil.getSession();

		try {
			String consulta = "SELECT count(eoos.id) " +
							  "FROM ExecucaoOSOrdemServico eoos " +
							  "WHERE eoos.arquivoTextoOSCobranca.id = :idArquivoTextoOSCobranca ";

			retorno = (Integer) session.createQuery(consulta)
					.setInteger("idArquivoTextoOSCobranca", idArquivoTextoOSCobranca)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1484] - Gerar Arquivo de Ida para Execucao de OS de Cobranca Android
	 * 
	 * @author Vivianne Sousa
	 * @date 16/12/2015
	 */
	public Integer obterNumDiasValidadeCobrancaAcao(Integer idOrdemServico) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		String consulta = "";

		try {
			consulta = 
					"SELECT cbac.cbac_nndiasvalidade as numDiasValidade " +
					"FROM cobranca.cobranca_documento cbdo " +
					"INNER JOIN atendimentopublico.ordem_servico orse ON orse.cbdo_id = cbdo.cbdo_id " +
					"INNER JOIN cobranca.cobranca_acao cbac           ON cbac.cbac_id = cbdo.cbac_id " +
					"WHERE orse.orse_id = :idOS ";

			retorno = (Integer)session.createSQLQuery(consulta)
							 .addScalar("numDiasValidade", Hibernate.INTEGER)
							 .setInteger("idOS", idOrdemServico)
							 .uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * @author Jean Varela 	
	 * @date 05/01/2015
	 */
	public List<Integer> pesquisarQuadrasArquivoTextoOSCobrancaItem(Integer idArquivoTextoOSCobranca) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		String consulta;

		try {
			consulta = "select distinct aoci_nnquadra from " +
					   "mobile.arq_txt_os_cobranca_item  aosc where aosc.aosc_id = :idArquivoTextoOSCobranca " +
					   "order by aosc.aoci_nnquadra";
			
			Query query = session.createSQLQuery(consulta)
							     .addScalar("aoci_nnquadra", Hibernate.INTEGER)
							     .setInteger("idArquivoTextoOSCobranca", idArquivoTextoOSCobranca);
			
			return query.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
}
