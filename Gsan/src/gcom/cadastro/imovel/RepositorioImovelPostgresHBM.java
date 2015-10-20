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
* Thiago Silva Toscano de Brito
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
package gcom.cadastro.imovel;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.DataException;
/**
 * Classe criada para sobrescrever(override) os metodos no padr�o da base de dados Postgres
 * 
 * @author Fernando Fontelles Filho
 * @date 25/01/2011
 */
public class RepositorioImovelPostgresHBM extends RepositorioImovelHBM {

	/**
	 * Filtrar o Imovel pelos parametros informados, para saber a quantidade de imoveis.
	 * Utilizado para corrigir o erro da listagem de Imoveis: o metodo pesquisarQuantidadeImovel nao
	 * traz a mesma quantidade de imovel do metodo pesquisarImovelInscricaoNew.  
	 * 
	 * @author Ivan S�rgio
	 * @date 11/03/2008
	 * 
	 * @param idImovel
	 * @param idLocalidade
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param lote
	 * @param subLote
	 * @param codigoCliente
	 * @param idMunicipio
	 * @param cep
	 * @param idBairro
	 * @param idLogradouro
	 * @param pesquisarImovelCondominio
	 * @param numeroPagina
	 * @return
	 * @throws ErroRepositorioException
	 */
	@Override
	public Object pesquisarQuantidadeImovelInscricao(
		String idImovel, String idLocalidade, String codigoSetorComercial,
		String numeroQuadra, String lote, String subLote,
		String codigoCliente, String idMunicipio, String cep,
		String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal, 
		boolean pesquisarImovelCondominio) throws ErroRepositorioException {

			Object retorno = null;
			Session session = HibernateUtil.getSession();
			String consulta = null;

			try {
				consulta = "select count(distinct imovel.imov_id) as qtd "

					+ "from cadastro.cliente_imovel clienteImovel "
					
					+ "inner join cadastro.imovel imovel "
					+ "on imovel.imov_id = clienteImovel.imov_id "

					+ "inner join cadastro.quadra quadra "
					+ "on quadra.qdra_id = imovel.qdra_id "

					+ "inner join cadastro.localidade localidade "
					+ "on localidade.loca_id = imovel.loca_id "

					+ "inner join cadastro.setor_comercial setorComercial "
					+ "on setorComercial.stcm_id = imovel.stcm_id "

					+ "left join cadastro.logradouro_cep logradouroCep "
					+ "on logradouroCep.lgcp_id = imovel.lgcp_id "

					+ "left join cadastro.cep cep "
					+ "on cep.cep_id = logradouroCep.cep_id "

					+ "left join cadastro.logradouro logradouro "
					+ "on logradouro.logr_id = logradouroCep.logr_id "

					+ "left join cadastro.logradouro_tipo logradouroTipo "
					+ "on logradouroTipo.lgtp_id = logradouro.lgtp_id "

					+ "left join cadastro.logradouro_titulo logradouroTitulo "
					+ "on logradouroTitulo.lgtt_id = logradouro.lgtt_id "

					+ "left join cadastro.logradouro_bairro logradouroBairro "
					+ "on logradouroBairro.lgbr_id = imovel.lgbr_id "

					+ "left join cadastro.bairro bairro "
					+ "on bairro.bair_id = logradouroBairro.bair_id "

					+ "left join cadastro.municipio municipio "
					+ "on municipio.muni_id = bairro.muni_id "

					+ "left join cadastro.unidade_federacao unidadeFederacao "
					+ "on unidadeFederacao.unfe_id = municipio.unfe_id "

					+ "left join cadastro.endereco_referencia enderecoReferencia "
					+ "on enderecoReferencia.edrf_id = imovel.edrf_id "

					+ "inner join cadastro.cliente_relacao_tipo clienteRelacaoTipo "
					+ "on clienteRelacaoTipo.crtp_id = clienteImovel.crtp_id "

					+ "inner join cadastro.cliente clienteUsuario "
					+ "on clienteUsuario.clie_id = clienteImovel.clie_id ";


				/* * ## CONDI��ES ## *  */

				consulta = consulta

				+ " where clienteImovel.clim_dtrelacaofim is null "

				+ " and imovel.imov_icexclusao <> "

				+ Imovel.IMOVEL_EXCLUIDO + " and  ";

				// pesquisar imovel condominio

				if (pesquisarImovelCondominio) {

					consulta = consulta + " imovel.imov_icimovelcondominio = "

					+ Imovel.IMOVEL_CONDOMINIO + "  and  ";

				}

				// imovel

				if (idImovel != null

				&& !idImovel.equals("")

				&& !idImovel.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " imovel.imov_id = :idImovel  and  ";

				}

				// localidade

				if (idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

				.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " localidade.loca_id = :idLocalidade  and  ";

				}

				// setor comercial

				if (codigoSetorComercial != null

				&& !codigoSetorComercial.equals("") && !codigoSetorComercial

				.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta

					+ " setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial  and  ";

				}

				// quadra

				if (numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

				.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta

					+ " quadra.qdra_nnquadra = :numeroQuadra and  ";

				}

				// lote

				if (lote != null && !lote.equals("") && !lote.trim()

				.equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " imovel.imov_nnlote = :lote  and  ";

				}

				// sublote

				if (subLote != null && !subLote.equals("") && !subLote.trim()

				.equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " imovel.imov_nnsublote = :subLote  and  ";

				}
				
				
				
				if (numeroImovelInicial != null && !numeroImovelInicial.trim().equals("")
						&& numeroImovelFinal != null && !numeroImovelFinal.trim().equals("")) {
					
					consulta += " RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "-�.''', ''))) <> '' and ";
					
					consulta += " to_number(RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "-�.', ''))), 99999) between '" + numeroImovelInicial.trim() + "' and '" + numeroImovelFinal.trim() + "' and ";
				}

				// cliente

				if (codigoCliente != null

				&& !codigoCliente.equals("")

				&& !codigoCliente.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta

					+ " clienteUsuario.clie_id = :codigoCliente  and  ";

				}

				// municipio

				if (idMunicipio != null

				&& !idMunicipio.equals("")

				&& !idMunicipio.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " municipio.muni_id = :idMunicipio  and  ";

				}

				// cep

				if (cep != null

				&& !cep.equals("")

				&& !cep.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " cep.cep_cdcep = :cep  and  ";

				}

				// bairro

				if (idBairro != null

				&& !idBairro.equals("")

				&& !idBairro.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " bairro.bair_cdbairro = :idBairro  and  ";

				}

				// logradouro

				if (idLogradouro != null

				&& !idLogradouro.equals("")

				&& !idLogradouro.trim().equalsIgnoreCase(

				new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

				.toString())) {

					consulta = consulta + " logradouro.logr_id = :idLogradouro  and  ";

				}

				Query query = session.createSQLQuery(consulta.substring(0, consulta.length() - 5)).addScalar("qtd", Hibernate.INTEGER);
				// seta os valores na condi��o where
				// imovel principal
				if (idImovel != null && !idImovel.equals("") && !idImovel.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idImovel", new Integer(idImovel).intValue());
				}

				// localidade
				if (idLocalidade != null && !idLocalidade.equals("") && !idLocalidade.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idLocalidade", new Integer(idLocalidade).intValue());
				}

				// setor
				if (codigoSetorComercial != null && !codigoSetorComercial.equals("") && !codigoSetorComercial.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("codigoSetorComercial", new Integer(codigoSetorComercial).intValue());
				}

				// quadra
				if (numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("numeroQuadra", new Integer(numeroQuadra).intValue());
				}

				// lote
				if (lote != null && !lote.equals("") && !lote.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("lote", new Integer(lote).intValue());
				}

				// subLote
				if (subLote != null && !subLote.equals("") && !subLote.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("subLote", new Integer(subLote).intValue());
				}
				
				// cliente
				if (codigoCliente != null && !codigoCliente.equals("") && !codigoCliente.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("codigoCliente", new Integer(codigoCliente).intValue());
				}

				// municipio
				if (idMunicipio != null && !idMunicipio.equals("") && !idMunicipio.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idMunicipio", new Integer(idMunicipio).intValue());
				}

				// cep
				if (cep != null && !cep.equals("") && !cep.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("cep", new Integer(cep).intValue());
				}
				// bairro
				if (idBairro != null && !idBairro.equals("") && !idBairro.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idBairro", new Integer(idBairro).intValue());
				}

				// logradouro
				if (idLogradouro != null && !idLogradouro.equals("") && !idLogradouro.trim().equalsIgnoreCase(
					new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())) {

					query.setInteger("idLogradouro", new Integer(idLogradouro).intValue());
				}
				
				try{
					retorno = query.uniqueResult();
				}catch (DataException ex) {
					//caso seja exce��o devido a convers�o de alfanumerico para numerico
					//levanta a exce��o para a pr�xima camada
					throw new ErroRepositorioException(ex, ex.getSQLState());
				}
			} catch (HibernateException e) {
				// levanta a exce��o para a pr�xima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				// fecha a sess�o
				HibernateUtil.closeSession(session);
			}

			return retorno;
		}
	
	
	/**
	 * 
	 * 
	 * 
	 * Usado pelo Pesquisar Imovel Retorno o Imovel, com o Nome do Cliente,
	 * 
	 * Matricula e Endere�o
	 * 
	 * 
	 * 
	 * @author Rafael Santos, Ivan Sergio
	 * 
	 * @date 27/11/2006, 26/02/2010
	 * 
	 * @alteracao: 26/02/2010 - CRC3755 - Adicionado a condicao de apenas clientes com relacao do tipo USUARIO;
	 * 
	 * 
	 * 
	 * @param idImovel
	 * 
	 * @return
	 * 
	 * @throws ErroRepositorioException
	 * 
	 */

	public Collection pesquisarImovelInscricaoNew(String idImovel,

	String idLocalidade, String codigoSetorComercial,

	String numeroQuadra, String lote, String subLote,

	String codigoCliente, String idMunicipio, String cep,

	String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,

	boolean pesquisarImovelCondominio, Integer numeroPagina)

	throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		String consulta = null;

		try {

			consulta = "select distinct logradouro.logr_nmlogradouro as nomeLogradouro, "//" logradouro.nome," // 0
					+ " logradouroTipo.lgtp_dsabreviado as logradouroTipoAbreviado, " //"logradouroTipo.descricaoAbreviada," // 1
					+ " logradouroTitulo.lgtt_dsabreviado as logradouroTituloAbreviado, "//" logradouroTitulo.descricaoAbreviada," // 2
					+ " bairro.bair_id as idBairro, " //"bairro.id," // 3
					+ " bairro.bair_nmBairro as nomeBairro, " //"bairro.nome," // 4
					+ " municipio.muni_id as idMunicipio, " // "municipio.id," // 5
					+ " municipio.muni_nmmunicipio as nomeMunicipio, "//"municipio.nome," // 6
					+ " unidadeFederacao.unfe_id as idUnidadeFederacao, "//"unidadeFederacao.id," // 7
					+ " unidadeFederacao.unfe_dsufsigla as siglaUnidadeFederacao, "//"unidadeFederacao.sigla," // 8
					+ " enderecoReferencia.edrf_dsabreviado as enderecoReferenciaAbreviado, "//"enderecoReferencia.descricaoAbreviada," // 9
					+ " cep.cep_id as idCep, " //"cep.cepId," // 10
					+ " cep.cep_nmlogradouro as nomeLogradouroCep, " // 11
					+ " cep.cep_dslogradourotipo as logradouroTipoCep, " // 12
					+ " cep.cep_nmbairro as bairroCep, " // 13
					+ " cep.cep_nmmunicipio as nomeMunicipioCep, " // 14 
					+ " cep.cep_dsufsigla as siglaUnidadeFederacaoCep, " // 15
					+ " cep.cep_cdcep as codigoCep, " // 16
//					+ " cep.logradouro," // 11
//					+ " cep.descricaoTipoLogradouro," // 12
//					+ " cep.bairro," // 13
//					+ " cep.municipio," // 14
//					+ " cep.sigla, " // 15
//					+ " cep.codigo, " // 16
					+ " imovel.imov_nnimovel as numeroImovel, " // "imovel.numeroImovel," // 17
					+ " imovel.imov_dscomplementoendereco as complementoEndereco, " //"imovel.complementoEndereco," // 18
					+ " logradouro.logr_id as idLogradouro, " //"logradouro.id," // 19
					+ " logradouroCep.lgcp_id as idLogradouroCep, "//"logradouroCep.id," // 20
					+ " logradouroBairro.lgbr_id as idLogradouroBairro, " //"logradouroBairro.id," // 21
					+ " logradouroTipo.lgtp_dslogradourotipo as logradouroTipo, " //"logradouroTipo.descricao," // 22
					+ " logradouroTitulo.lgtt_dslogradourotitulo as logradouroTitulo, " //"logradouroTitulo.descricao," // 23
					+ " enderecoReferencia.edrf_dsenderecoreferencia as enderecoReferencia, " //"enderecoReferencia.descricao, " // 24
					+ " imovel.imov_id as idImovel, " //"imovel.id, " // 25
					+ " imovel.imov_nnlote as lote, " //"imovel.lote, " // 26
					+ " imovel.imov_nnsublote as sublote, " //"imovel.subLote, " // 27
					+ " localidade.loca_id as idLocalidade, " //"localidade.id, " // 28
					+ " setorComercial.stcm_cdsetorcomercial as codigoSetorComercial, " //"setorComercial.codigo, " // 29
					+ " quadra.qdra_nnquadra as numeroQuadra, " //"quadra.numeroQuadra, " // 30
					+ " clienteUsuario.clie_nmcliente as nomeUsuario, " //"clienteUsuario.nome " // 31
					+ " perimetroInicial.logr_id as idPerimetroInicial, " // 32
					+ " perimetroInicial.logr_nmlogradouro as nomePerimetroInicial, " // 33
					+ " logradouroTipoPerimetroInicial.lgtp_dsabreviado as logradouroTipoPerimetroInicial, " // 34
					+ " logradourotituloperimetroinici.lgtt_dsabreviado as logradourotituloperimetroinici, " // 35
					+ " perimetroFinal.logr_id as idPerimetroFinal, " // 36
					+ " perimetroFinal.logr_nmlogradouro as nomePerimetroFinal, " // 37
					+ " logradouroTipoPerimetroFinal.lgtp_dsabreviado as logradouroTipoPerimetroFinal, " // 38
					+ " logradouroTituloPerimetroFinal.lgtt_dsabreviado as logradouroTituloPerimetroFinal " // 39
					+ " from cadastro.cliente_imovel clienteImovel "

					/*
					 * ## JOIN ##
					 * 
					 */

					// join necess�rios
					+ "inner join cadastro.imovel imovel "
					+ "on imovel.imov_id = clienteImovel.imov_id "

					+ "inner join cadastro.quadra quadra "
					+ "on quadra.qdra_id = imovel.qdra_id "

					+ "inner join cadastro.localidade localidade "
					+ "on localidade.loca_id = imovel.loca_id "

					+ "inner join cadastro.setor_comercial setorComercial "
					+ "on setorComercial.stcm_id = imovel.stcm_id "

					+ "inner join cadastro.logradouro_cep logradouroCep "
					+ "on logradouroCep.lgcp_id = imovel.lgcp_id "

					+ "inner join cadastro.cep cep "
					+ "on cep.cep_id = logradouroCep.cep_id "

					+ "inner join cadastro.logradouro logradouro "
					+ "on logradouro.logr_id = logradouroCep.logr_id "

					+ "inner join cadastro.logradouro_tipo logradouroTipo "
					+ "on logradouroTipo.lgtp_id = logradouro.lgtp_id "

					+ "left join cadastro.logradouro_titulo logradouroTitulo "
					+ "on logradouroTitulo.lgtt_id = logradouro.lgtt_id "

					+ "inner join cadastro.logradouro_bairro logradouroBairro "
					+ "on logradouroBairro.lgbr_id = imovel.lgbr_id "

					+ "inner join cadastro.bairro bairro "
					+ "on bairro.bair_id = logradouroBairro.bair_id "

					+ "inner join cadastro.municipio municipio "
					+ "on municipio.muni_id = bairro.muni_id "

					+ "inner join cadastro.unidade_federacao unidadeFederacao "
					+ "on unidadeFederacao.unfe_id = municipio.unfe_id "

					+ "left join cadastro.endereco_referencia enderecoReferencia "
					+ "on enderecoReferencia.edrf_id = imovel.edrf_id "
					
					+ "left join cadastro.logradouro perimetroInicial "
					+ "on perimetroInicial.logr_id = imovel.logr_idinicioperimetro "
					
					+ "left join cadastro.logradouro_tipo logradouroTipoPerimetroInicial "
					+ "on logradouroTipoPerimetroInicial.lgtp_id = perimetroInicial.lgtp_id "
					
					+ "left join cadastro.logradouro_titulo logradourotituloperimetroinici "
					+ "on logradourotituloperimetroinici.lgtt_id = perimetroInicial.lgtt_id "
					
					+ "left join cadastro.logradouro perimetroFinal "
					+ "on perimetroFinal.logr_id = imovel.logr_idfimperimetro "
					
					+ "left join cadastro.logradouro_tipo logradouroTipoPerimetroFinal "
					+ "on logradouroTipoPerimetroFinal.lgtp_id = perimetroFinal.lgtp_id "
					
					+ "left join cadastro.logradouro_titulo logradouroTituloPerimetroFinal "
					+ "on logradouroTituloPerimetroFinal.lgtt_id = perimetroFinal.lgtt_id "

					//+ "and clienteImovel.crtp_id = " + ClienteRelacaoTipo.USUARIO + " "
					+ "inner join cadastro.cliente clienteUsuario "
					+ "on clienteUsuario.clie_id = clienteImovel.clie_id ";

			
			/*
			 * ## CONDI��ES ##
			 * 
			 */

			consulta = consulta

			+ " where clienteImovel.clim_dtrelacaofim is null "
			
			+ " and clienteImovel.crtp_id = " + ClienteRelacaoTipo.USUARIO

			+ " and imovel.imov_icexclusao <> "

			+ Imovel.IMOVEL_EXCLUIDO ;

			// pesquisar imovel condominio

			if (pesquisarImovelCondominio) {

				consulta = consulta + " and imovel.imov_icimovelcondominio = "

				+ Imovel.IMOVEL_CONDOMINIO ;

			}

			// imovel

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and imovel.imov_id = :idImovel ";

			}

			// localidade

			if (idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and localidade.loca_id = :idLocalidade";

			}

			// setor comercial

			if (codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " and setorComercial.stcm_cdsetorcomercial = :codigoSetorComercial";

			}

			// quadra

			if (numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " and quadra.qdra_nnquadra = :numeroQuadra";

			}

			// lote

			if (lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and imovel.imov_nnlote = :lote";

			}

			// sublote

			if (subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and imovel.imov_nnsublote = :subLote";

			}
			
			
			
			if (numeroImovelInicial != null && !numeroImovelInicial.trim().equals("")
					&& numeroImovelFinal != null && !numeroImovelFinal.trim().equals("")) {
				consulta += " and RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "-�.''', ''))) <> '' and ";
				
				consulta += " to_number(RTRIM(LTRIM(translate(imovel.imov_nnimovel, '" + ConstantesSistema.CARACTERES_ALFANUMERICOS + "-�.', ''))), 99999) between '" + numeroImovelInicial.trim() + "' and '" + numeroImovelFinal.trim() + "'";
			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta

				+ " and clienteUsuario.clie_id = :codigoCliente";

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and municipio.muni_id = :idMunicipio";

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and cep.cep_cdcep = :cep";

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and bairro.bair_cdbairro = :idBairro";

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				consulta = consulta + " and logradouro.logr_id = :idLogradouro";

			}
			
			consulta += " order by imovel.imov_id ";

			Query query = session.createSQLQuery(consulta)
					.addScalar("nomeLogradouro", Hibernate.STRING) // 0
					.addScalar("logradouroTipoAbreviado", Hibernate.STRING) // 1
					.addScalar("logradouroTituloAbreviado", Hibernate.STRING) // 2
					.addScalar("idBairro", Hibernate.INTEGER) // 3
					.addScalar("nomeBairro", Hibernate.STRING) // 4
					.addScalar("idMunicipio", Hibernate.INTEGER) // 5
					.addScalar("nomeMunicipio",	Hibernate.STRING) // 6
					.addScalar("idUnidadeFederacao", Hibernate.INTEGER) // 7
					.addScalar("siglaUnidadeFederacao",	Hibernate.STRING) // 8 
					.addScalar("enderecoReferenciaAbreviado", Hibernate.STRING) // 9
					.addScalar("idCep", Hibernate.INTEGER) // 10
					.addScalar("nomeLogradouroCep", Hibernate.STRING) // 11
					.addScalar("logradouroTipoCep", Hibernate.STRING) // 12
					.addScalar("bairroCep", Hibernate.STRING) // 13
					.addScalar("nomeMunicipioCep", Hibernate.STRING) // 14
					.addScalar("siglaUnidadeFederacaoCep", Hibernate.STRING) // 15
					.addScalar("codigoCep", Hibernate.INTEGER) // 16
					.addScalar("numeroImovel", Hibernate.STRING) // 17
					.addScalar("complementoEndereco", Hibernate.STRING) // 18
					.addScalar("idLogradouro", Hibernate.INTEGER) // 19
					.addScalar("idLogradouroCep", Hibernate.INTEGER) // 20
					.addScalar("idLogradouroBairro", Hibernate.INTEGER) // 21
					.addScalar("logradouroTipo", Hibernate.STRING) // 22
					.addScalar("logradouroTitulo", Hibernate.STRING) // 23
					.addScalar("enderecoReferencia", Hibernate.STRING) // 24
					.addScalar("idImovel", Hibernate.INTEGER) // 25
					.addScalar("lote", Hibernate.SHORT) // 26 
					.addScalar("sublote", Hibernate.SHORT) //27
					.addScalar("idLocalidade", Hibernate.INTEGER) // 28
					.addScalar("codigoSetorComercial", Hibernate.INTEGER) // 29
					.addScalar("numeroQuadra", Hibernate.INTEGER) // 30
					.addScalar("nomeUsuario", Hibernate.STRING) // 31
					.addScalar("idPerimetroInicial", Hibernate.INTEGER) // 32
					.addScalar("nomePerimetroInicial", Hibernate.STRING) // 33
					.addScalar("logradouroTipoPerimetroInicial", Hibernate.STRING) // 34
					.addScalar("logradourotituloperimetroinici", Hibernate.STRING) // 35
					.addScalar("idPerimetroFinal", Hibernate.INTEGER) // 36
					.addScalar("nomePerimetroFinal", Hibernate.STRING) // 37
					.addScalar("logradouroTipoPerimetroFinal", Hibernate.STRING) // 38
					.addScalar("logradouroTituloPerimetroFinal", Hibernate.STRING);  // 39
			
			// seta os valores na condi��o where

			// imovel principal

			if (idImovel != null

			&& !idImovel.equals("")

			&& !idImovel.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idImovel", new Integer(idImovel).intValue());

			}

			// localidade

			if (idLocalidade != null && !idLocalidade.equals("") && !idLocalidade

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idLocalidade", new Integer(idLocalidade)

				.intValue());

			}

			// setor

			if (codigoSetorComercial != null

			&& !codigoSetorComercial.equals("") && !codigoSetorComercial

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("codigoSetorComercial", new Integer(

				codigoSetorComercial).intValue());

			}

			// quadra

			if (numeroQuadra != null && !numeroQuadra.equals("") && !numeroQuadra

			.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("numeroQuadra", new Integer(numeroQuadra)

				.intValue());

			}

			// lote

			if (lote != null && !lote.equals("") && !lote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("lote", new Integer(lote).intValue());

			}

			// subLote

			if (subLote != null && !subLote.equals("") && !subLote.trim()

			.equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("subLote", new Integer(subLote).intValue());

			}

			// cliente

			if (codigoCliente != null

			&& !codigoCliente.equals("")

			&& !codigoCliente.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("codigoCliente", new Integer(codigoCliente)

				.intValue());

			}

			// municipio

			if (idMunicipio != null

			&& !idMunicipio.equals("")

			&& !idMunicipio.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idMunicipio", new Integer(idMunicipio)

				.intValue());

			}

			// cep

			if (cep != null

			&& !cep.equals("")

			&& !cep.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("cep", new Integer(cep).intValue());

			}

			// bairro

			if (idBairro != null

			&& !idBairro.equals("")

			&& !idBairro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idBairro", new Integer(idBairro).intValue());

			}

			// logradouro

			if (idLogradouro != null

			&& !idLogradouro.equals("")

			&& !idLogradouro.trim().equalsIgnoreCase(

			new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)

			.toString())) {

				query.setInteger("idLogradouro", new Integer(idLogradouro)

				.intValue());

			}

			try{
				retorno = query.setFirstResult(10 * numeroPagina).setMaxResults(10)
				.list();
			}catch (DataException ex) {
				//caso seja exce��o devido a convers�o de alfanumerico para numerico
				//levanta a exce��o para a pr�xima camada
				throw new ErroRepositorioException(ex, ex.getSQLState());
			}

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
