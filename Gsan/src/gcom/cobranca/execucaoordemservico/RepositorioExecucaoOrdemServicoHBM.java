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
package gcom.cobranca.execucaoordemservico;

import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Andr� Miranda
 * @date 16/11/2015
 */
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
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection buscarColecaoArquivoTextoOSCobrancaSmartphone() throws ErroRepositorioException {
		return null;
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public Collection<Integer> pesquisarArquivosEmAbertoPorLeiturista(Integer idLeiturista)
			throws ErroRepositorioException {
		
		Collection<Integer> retorno = null;
		Session session = HibernateUtil.getSession();
		
		try{
			String sql = "SELECT arq.aosc_id as idArquivo " +
						 "FROM mobile.arq_txt_os_cobranca arq " +
						 "WHERE arq.sitl_id in (" + SituacaoTransmissaoLeitura.LIBERADO + ", " + SituacaoTransmissaoLeitura.EM_CAMPO + ") AND " +
						 "arq.leit_id = :idLeiturista ";
					
			retorno = (Collection<Integer>) session.createSQLQuery(sql)
					.addScalar("idArquivo", Hibernate.INTEGER)
					.setInteger("idLeiturista", idLeiturista)
					.list();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return retorno;	
	}
	
	/**
	 * [UC1498] - Consultar Arquivo Texto de Ordens de Servi�o para Smartphone (Novo)
	 *
	 * @author Jean Varela
	 * @throws ErroRepositorioException 
	 * @date   16/11/2015	
	 */
	public void atualizarListaArquivoTextoOSCobrancaSmartphone(
			Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone,
			Integer idSituacaoLeituraNova, Leiturista leiturista, Date date) throws ErroRepositorioException{
			
			 Session session = HibernateUtil.getSession();
			 String sql = "";
			
			 Collection<Integer> colecaoIds = new ArrayList<Integer>();
			 Iterator iterator = colecaoArquivoTextoOSCobrancaSmartphone.iterator();
			
			 while(iterator.hasNext()){
				 ArquivoTxtOSCobrancaSmartphoneHelper arquivo = (ArquivoTxtOSCobrancaSmartphoneHelper)iterator.next();
				 colecaoIds.add(arquivo.getIdArquivo());
			 }
			
			 try{
				sql = " update ArquivoTextoOSCobranca set" +
					  " sitl_id = :idSituacao," +
					  " aosc_tmultimaalteracao = :dataUltimaAlteracao ";
				
				if(leiturista != null)
					sql += ", leit_id  = :idLeiturista";
				
				sql += " where aosc_id in ( :conjuntoArquivos )";
				
				Query query = session.createQuery(sql)
						.setInteger("idSituacao", idSituacaoLeituraNova)	
						.setTimestamp("dataUltimaAlteracao", date)
						.setParameterList("conjuntoArquivos", colecaoIds);
				
				if(leiturista != null)
					query.setInteger( "idLeiturista", leiturista.getId() );
				
				query.executeUpdate();
				
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new ErroRepositorioException("Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}
}