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
package gcom.seguranca.transacao;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.seguranca.acesso.FiltroOperacaoEfetuada;
import gcom.seguranca.acesso.FiltroOperacaoOrdemExibicao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesColecao;
import gcom.util.filtro.PersistenciaUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public class RepositorioTransacaoHBM implements IRepositorioTransacao {

	private static IRepositorioTransacao instancia;

	/**
	 * Constructor for the RepositorioClienteTipoHBM object
	 */
	public RepositorioTransacaoHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static IRepositorioTransacao getInstancia() {

		if (instancia == null) {
			instancia = new RepositorioTransacaoHBM();
		}

		return instancia;
	}

	/**
	 * M�todo que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param hotaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id
	 * @return
	 * @throws ControladorException
	 * 
	 * @author thiago toscano
	 * @date 17/02/2006
	 */	
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
		Integer idFuncionalidade, Integer idUsuario,Date dataInicial, Date dataFinal, 
		Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, Integer id1, String unidadeNegocio)
		throws ErroRepositorioException{

        FiltroOperacaoEfetuada filtroOperacaoEfetuada = new FiltroOperacaoEfetuada(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO);
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO);
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO);
        filtroOperacaoEfetuada.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoEfetuada.OPERACAO_ARGUMENTO_PESQUISA_TABELA);

        if (idUsuarioAcao != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimplesColecao("ua.usuarioAcao.id",idUsuarioAcao));
        }
        if (idFuncionalidade != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples(FiltroOperacaoEfetuada.OPERACAO_FUNCIONALIDADE_ID,
        		idFuncionalidade));
        }
        if (idUsuario != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimplesColecao("ua.usuario.id",idUsuario));
        }
        if (id1 != null) {
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimplesColecao(FiltroOperacaoEfetuada.TABELA_LINHA_ALTERACAO_ID1,id1));
        }
        
        //Unidade Negocio
        if(unidadeNegocio != null){
        	filtroOperacaoEfetuada.adicionarParametro(new ParametroSimples("ua.usuario.unidadeNegocio", unidadeNegocio));
        }

// Date inicio = null;
		if (dataInicial != null) {
		
			Calendar di = Calendar.getInstance();
			di.setTime(dataInicial);
			if (horaInicial != null) {
				Calendar hi = Calendar.getInstance();
				hi.setTime(horaInicial);
				di.set(Calendar.HOUR, hi.get(Calendar.HOUR));
				di.set(Calendar.MINUTE, hi.get(Calendar.MINUTE));
			} else {
				di.set(Calendar.HOUR, 0);
				di.set(Calendar.MINUTE, 0);
				di.set(Calendar.SECOND, 0);
			}
			filtroOperacaoEfetuada.adicionarParametro(new MaiorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,di.getTime()));
		}else{
			
			if (horaInicial != null) {
				Calendar di = Calendar.getInstance();
				Calendar hi = Calendar.getInstance();
				di.set(Calendar.YEAR, new Integer("1985").intValue());
				di.set(Calendar.MONTH, 0);
				di.set(Calendar.DATE, new Integer("01").intValue());
				hi.setTime(horaInicial);
				di.set(Calendar.HOUR, hi.get(Calendar.HOUR));
				di.set(Calendar.MINUTE, hi.get(Calendar.MINUTE));
				di.set(Calendar.SECOND, 0);
				// di.set(Calendar.)
				
				filtroOperacaoEfetuada.adicionarParametro(new MaiorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,di.getTime()));
			}
			
		}

      
// Date inicio = null;
		if (dataFinal != null) {
			Calendar df = Calendar.getInstance();
			df.setTime(dataFinal);
			if (horaFinal != null) {
				Calendar hf = Calendar.getInstance();
				hf.setTime(horaFinal);
				df.set(Calendar.HOUR, hf.get(Calendar.HOUR));
				df.set(Calendar.MINUTE, hf.get(Calendar.MINUTE));
			} else {
				df.set(Calendar.HOUR, 23);
				df.set(Calendar.MINUTE, 59);
				df.set(Calendar.SECOND, 59);
			}
			filtroOperacaoEfetuada.adicionarParametro(new MenorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,df.getTime()));
		}else{
			if (horaFinal != null) {
				Calendar df = Calendar.getInstance();
				Date dataFim = new Date();
				df.setTime(dataFim);
				Calendar hf = Calendar.getInstance();
				hf.setTime(horaFinal);
				df.set(Calendar.HOUR, hf.get(Calendar.HOUR));
				df.set(Calendar.MINUTE, hf.get(Calendar.MINUTE));
				// di.set
				
				filtroOperacaoEfetuada.adicionarParametro(new MenorQue(FiltroOperacaoEfetuada.ULTIMA_ALTERACAO,df.getTime()));
			}
		}
		// adicionando no filtro a cole��o de pares de (argumento, valor) .:.
		// (operacao.idargumento, operacaoefetuada.valorargumento)
		if (argumentos != null && argumentos.size() > 0 ){
			int i = 0;
			for(Enumeration e = argumentos.keys(); e.hasMoreElements();) {
				String idArgumento = (String) e.nextElement();
				String valorArgumento = argumentos.get(idArgumento);
				if (i ==0 ) {
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO,idArgumento, 
									FiltroParametro.CONECTOR_OR, argumentos.size()));
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.ARGUMENTO_VALOR,valorArgumento, 
									FiltroParametro.CONECTOR_OR, argumentos.size()));					
				} else if (i !=	argumentos.size() - 1) {
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO, idArgumento, 
									FiltroParametro.CONECTOR_OR));	
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.ARGUMENTO_VALOR, valorArgumento, 
									FiltroParametro.CONECTOR_OR));	
				} else {	
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.OPERACAO_NOME_ARGUMENTO,idArgumento));
					filtroOperacaoEfetuada.adicionarParametro(
							new ParametroSimplesColecao(FiltroOperacaoEfetuada.ARGUMENTO_VALOR,valorArgumento));

				}
				i++;
				
			}
		}

		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(
				new CopyOnWriteArraySet(
					GeradorHQLCondicional.gerarCondicionalQuery(filtroOperacaoEfetuada, "operacaoEfetuada",
						" select distinct operacaoEfetuada " +
						" from OperacaoEfetuada as operacaoEfetuada " +
						" left join operacaoEfetuada.usuarioAlteracoes as " + FiltroOperacaoEfetuada.USUARIO_ALTERACAO +
						" left join operacaoEfetuada.tabelaLinhaAlteracoes as " + FiltroOperacaoEfetuada.TABELA_LINHA_ALTERACAO +
						" left join tla.tabelaLinhaColunaAlteracao as "  + FiltroOperacaoEfetuada.TABELA_LINHA_COLUNA_ALTERACAO +
						"", session).list()));

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
	 * M�todo que consulta os usuario alteracao de uma determinada operacao com
	 * as restricoes passadas
	 * 
	 * 
	 * @param idUsuarioAcao
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param horaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * @author R�mulo Aur�lio / Rafael Correa
	 */
	
	public Integer pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
			throws ErroRepositorioException {
	
		// cria a cole��o de retorno
		Integer retorno = null;
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();
		
		try {

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			 consulta =	" select count(distinct operacaoEfetuada.id)  " +
				" from OperacaoEfetuada operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " + 
				" inner join tabCol.tabela as tab " + 
				" inner join usAlt.usuario as usuario " + 
				" inner join operacaoEfetuada.operacao as operacao " +
				" left join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
	 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, 
						id1, unidadeNegocio);
			 
			 if (dataInicial != null) {
				 retorno = (Integer) session
					.createQuery(consulta).setMaxResults(1).uniqueResult();
			 } else {
				 retorno = (Integer) session
					.createQuery(consulta)
					.setMaxResults(1).uniqueResult();
			 }
			 
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
	
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, Integer numeroPagina, String unidadeNegocio, boolean indicadorConsultarImovel)
			throws ErroRepositorioException {
	
		// cria a cole��o de retorno
		Collection retorno = null;
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();
		
		try {

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			 consulta =	" select distinct operacaoEfetuada " +
				" from OperacaoEfetuada operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " + 
				" inner join tabCol.tabela as tab " +
				" inner join usAlt.usuario as usuario " + 
				" inner join fetch operacaoEfetuada.operacao as operacao " +
				" left join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
			 
			 if ( indicadorConsultarImovel ) {
				 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadasConsultarImovel(argumentos);
			 }else{
				 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
							idOperacoes, idUsuario, dataInicial, dataFinal, 
							horaInicial, horaFinal, argumentos, 
							id1, unidadeNegocio);
			 }
			 
			 consulta += " ORDER BY operacaoEfetuada.ultimaAlteracao desc";
			 
			//caso o metodo tenha sido chamado a partir da funcionalidade consultar imovel.
			 if ( indicadorConsultarImovel ) {
				 retorno = session.createQuery(consulta).list();
			 } else {
				 retorno = session
					.createQuery(consulta)
					.setFirstResult(10 * numeroPagina).setMaxResults(10).list();
			 }
			 
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
	
	
	public Collection pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlRelatorio(Integer idUsuarioAcao,
			String[] idOperacoes, String idUsuario,Date dataInicial, Date dataFinal, 
			Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, 
			Integer id1, String unidadeNegocio)
			throws ErroRepositorioException {
	
		// cria a cole��o de retorno
		Collection retorno = null;
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();
		
		try {

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			 consulta =	" select distinct operacaoEfetuada " +
				" from OperacaoEfetuada operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " + 
				" inner join tabCol.tabela as tab " +
				" inner join usAlt.usuario as usuario " + 
				" inner join fetch operacaoEfetuada.operacao as operacao " +
				" left join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
			 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, 
						id1, unidadeNegocio);
			 
			 retorno = session
				.createQuery(consulta).list();
			 
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;	
		
		
		/*
		try {

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			 consulta =	" select distinct operacaoEfetuada " +
				" from OperacaoEfetuada as operacaoEfetuada " +
				" inner join operacaoEfetuada.usuarioAlteracoes as usAlt " +
				" inner join operacaoEfetuada.tabelaLinhaAlteracoes as tabLinAlt " +
				" inner join tabLinAlt.tabelaLinhaColunaAlteracao as tabLinColAlt " +
				" inner join tabLinColAlt.tabelaColuna as tabCol " +
				" inner join usAlt.usuario as usuario " + 
				" inner join tabCol.tabela as tab " + 
				" inner join fetch operacaoEfetuada.operacao as operacao " +
				" inner join usuario.unidadeNegocio as unid " +
				" LEFT OUTER join operacao.argumentoPesquisa as argumento " +
				" LEFT OUTER join argumento.tabela as argTab "; 
			 
			 consulta += criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal, 
						horaInicial, horaFinal, argumentos, id1 , unidadeNegocio);
			 
			 if (dataInicial != null) {
				 retorno = session
					.createQuery(consulta).list();
			 } else {
				 retorno = session
					.createQuery(consulta).list();
			 }
			 
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;
		*/
	}
	
	/**
	 * M�todo que cria condicionais para o metodo
	 * pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHql
	 * 
	 * 
	 * @param idUsuarioAcao
	 * @param idOperacao
	 * @param idUsuario
	 * @param dataInicial
	 * @param dataFinal
	 * @param horaInicial
	 * @param horaFinal
	 * @param idTabela
	 * @param idTabelaColuna
	 * @param id1
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * @author R�mulo Aur�lio / Rafael Correa
	 */					
		
	public String criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadas(Integer idUsuarioAcao,
				String[] idOperacoes, String idUsuario, Date dataInicial, Date dataFinal, 
				Date horaInicial, Date horaFinal, Hashtable<String,String> argumentos, Integer id1,String unidadeNegocio){
			
		String condicoes = " where ";	
		
		if (idOperacoes != null && idOperacoes.length > 0){
			condicoes += " operacao.id in (";
			String valoresIn = "";
			for (int i = 0; i < idOperacoes.length; i++) {
				valoresIn += idOperacoes[i] + ", ";
			}
			if (valoresIn.length() > 0){
				valoresIn = valoresIn.substring(0, valoresIn.length() - 2);
			}
			condicoes += valoresIn + ") and ";			
		}
		
		if(idUsuario != null){
			condicoes += " usuario.login = '" + idUsuario + "' and ";
		}
		
		if(idUsuarioAcao != null){
			condicoes += " usAlt.usuarioAcao.id = " + idUsuarioAcao + " and ";
		}
		
		if (dataInicial != null && dataFinal != null) {
			String dataInicialFormatada = Util.formatarDataComTracoAAAAMMDD(dataInicial);
			String dataFinalFormatada = Util.formatarDataComTracoAAAAMMDD(dataFinal);
			
			condicoes += "to_char(operacaoEfetuada.ultimaAlteracao, 'yyyy-mm-dd') between '" 
				+ dataInicialFormatada + "' and '" 
				+ dataFinalFormatada + "' and ";
		}
		
		if(unidadeNegocio != null && 
			!unidadeNegocio.equals("") && 
			!unidadeNegocio.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			condicoes += " unid.id = " + unidadeNegocio +" and ";
		}
		
		if (horaInicial != null) {
			condicoes += " to_char(operacaoEfetuada.ultimaAlteracao, 'hh:MI') between '" 
				+ Util.formatarHoraSemSegundos(horaInicial) + "' and '" 
				+ Util.formatarHoraSemSegundos(horaFinal) + "' and ";
		}
		
		// Verificando se o id do argumento de pesquisa foi preenchido, 
		// caso nao.. considerar a busca por todos os campos imov_id
		if (argumentos != null && argumentos.size() > 0 ){
			for(Enumeration e = argumentos.keys(); e.hasMoreElements();) {
				String idArgumento = (String) e.nextElement();
				String valorArgumento = argumentos.get(idArgumento);
				if (idArgumento != null && !idArgumento.equals("")){
					condicoes += " operacaoEfetuada.operacao.argumentoPesquisa.id = " + idArgumento;	
				} else {
					condicoes += " operacaoEfetuada.operacao.argumentoPesquisa.coluna = 'imov_id' ";
				}
				condicoes += " and operacaoEfetuada.argumentoValor = " + valorArgumento + " and ";
			}
		} else {
			condicoes += " operacaoEfetuada.argumentoValor is not null and ";
		}
		
		if(id1 != null){
			condicoes += " tabLinAlt.id1 = " + id1 + " and ";	
		}
		
		// retira o " and " q fica sobrando no final da query
		condicoes = Util.removerUltimosCaracteres(condicoes, 4);
		
		return condicoes;
		
	}
	
	// UC12880 - Consultar Cliente.
	public String criarCondicionaisUsuarioAlteracaoDasOperacoesEfetuadasConsultarImovel(Hashtable<String,String> argumentos){
		
		String condicoes = " where ";	
		
		// Verificando se o id do argumento de pesquisa foi preenchido, 
		// caso nao.. considerar a busca por todos os campos imov_id
		if (argumentos != null && argumentos.size() > 0 ){
			for(Enumeration e = argumentos.keys(); e.hasMoreElements();) {
				String idArgumento = (String) e.nextElement();
				String valorArgumento = argumentos.get(idArgumento);
				if (idArgumento != null && !idArgumento.equals("")){
					condicoes += " operacaoEfetuada.operacao.argumentoPesquisa.coluna = '"+idArgumento+"'";	
				} else {
					condicoes += " operacaoEfetuada.operacao.argumentoPesquisa.coluna = 'imov_id' ";
				}
				condicoes += " and operacaoEfetuada.argumentoValor = " + valorArgumento ;
			}
		} else {
			condicoes += " operacaoEfetuada.argumentoValor is not null ";
		}
		
		return condicoes;
		
	}
	
	public Collection pesquisarOperacaoOrdemExibicao(int[] idTabelaColuna, int idOperacao) 
		throws ErroRepositorioException{
		
		// cria a cole��o de retorno
		Collection retorno = null;
		String consulta;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();
		
		try {
			String ids = "";
			for (int i = 0; i < idTabelaColuna.length; i++) {
				ids += idTabelaColuna[i] + ", ";
			}
			ids = ids.substring(0, ids.length() - 2);

			FiltroOperacaoOrdemExibicao filtroOperacaoOrdem = new FiltroOperacaoOrdemExibicao();
			filtroOperacaoOrdem
				.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.OPERACAO);
			filtroOperacaoOrdem
				.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoOrdemExibicao.TABELA_COLUNA_TABELA);

			
			consulta = "select operacaoOrdem from gcom.seguranca.acesso.OperacaoOrdemExibicao operacaoOrdem " +
				PersistenciaUtil.processaObjetosParaCarregamentoJoinFetch(
					"operacaoOrdem",
					filtroOperacaoOrdem
							.getColecaoCaminhosParaCarregamentoEntidades())			+
				" where operacaoOrdem.tabelaColuna.id in (" + ids + ") and " +
						"operacaoOrdem.operacao.id = " + idOperacao;
			
			retorno = new ArrayList(new CopyOnWriteArraySet<OperacaoOrdemExibicao>(
					session.createQuery(consulta).list()));						
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
	 * Pesquisa a quantidade de registros na tabela de Opera��o Efetuada
	 * para os argumentos passados.
	 * 		
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idOperacao
	 * @param argumentoValor
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarOperacaoEfetuada(Integer idOperacao,
			Integer argumentoValor, Integer id1)throws ErroRepositorioException {
	
		// cria a cole��o de retorno
		Integer retorno = null;
	
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();
		
		try {
			String sql = " select count(*) as quantidade "				
				   + " from  seguranca.operacao_efetuada opef "
				   + " inner join seguranca.tabela_linha_alteracao tbla on tbla.tref_id = opef.opef_id"
				   + " where opef.oper_id= "+ idOperacao 
				   + " and opef.opef_cnargumento= " + argumentoValor
				   + " and tbla.tbla_id1= " + id1;

			
			retorno = (Integer)session.createSQLQuery(sql)
			.addScalar("quantidade" , Hibernate.INTEGER)			
			.setMaxResults(1).uniqueResult();	
			
			 
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
	 * Pesquisa os registros na TabelaLinhaColunaAlteracao para o argumento passado.
	 * 
	 * @author Yara Taciane
	 * @date 15/07/2008
	 *
	 * @param idTabelaColuna
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarTabelaLinhaColunaAlteracao(Integer idObjetoAlterado, 
			Integer idTabelaColuna)throws ErroRepositorioException {
	
		// cria a cole��o de retorno
		Integer retorno = null;
	
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();
		
		try {
			String sql = " select count(tbca.tbca_id) quantidade "				
				   + " from seguranca.tab_linha_col_alteracao tbca"
				   + " inner join seguranca.tabela_linha_alteracao  tbla on tbca.tbla_id = tbla.tbla_id "
				   + " where tbca.tbco_id= "+ idTabelaColuna 
				   + " and tbla.tbla_id1 = " + idObjetoAlterado;
			
			retorno = (Integer)session.createSQLQuery(sql)
			.addScalar("quantidade" , Hibernate.INTEGER)			
			.setMaxResults(1).uniqueResult();	
			
			 
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
	 * Consultar Movimento Atualiza��o Cadastral 
	 * 
	 * @author Ana Maria
	 * @date 02/05/2009
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection<ConsultarMovimentoAtualizacaoCadastralHelper> pesquisarMovimentoAtualizacaoCadastral(String idArquivoTxt, 
			String idEmpresa, String idLeiturista, String exibirCampos, Collection colunaImoveisSelecionados,String matriculaImovel) throws ErroRepositorioException{

		
		Collection retornoConsulta = null;
		Session session = HibernateUtil.getSession();
		String consulta = "";
		Collection consultarMovimentoAtualizacaoCadastralHelper = new ArrayList();

		try {
				consulta = " select tatc.altp_id as tipoAlteracao ,"//0
					     + " tatc.tatc_cdimovel as idImovel,"//1
					     + " tatc.tatc_cdcliente as idCliente,"//2
					     + " sum(case when (tatc.tabe_id in(661,664)) then 1 else 0 end) as qtdImovel,"//3
					     + " sum(case when (tatc.tabe_id in(662,663)) then 1 else 0 end) as qtdCliente,"//4
					     + " func.func_nmfuncionario as nomeFuncionario," //5
					     + " clie.clie_nmcliente as nomeCliente,"//6
					     + " txac.txac_id as idArquivo,"//7
					     + " tatc.tatc_icautorizado as icAutorizado,"//8
					     + " tatc.tatc_idregistroalterado as idRegistroAlterado,"//9
					     + " tatc.imac_id as idImovelAtualizacaoCadastral" //10
					     //+ " altp_id as tipoAlteracao"//11
					    // + " tatc.tatc_ultimaalteracao as dataRealizacao"//12
					     + " from seguranca.tab_atlz_cadastral tatc"
					     + " inner join seguranca.operacao_efetuada opef on(opef.opef_id = tatc.opef_id)"
					     + " left join seguranca.tab_col_atlz_cadastral tcac on (tatc.tatc_id = tcac.tatc_id)"
					     + " inner join cadastro.arquivo_texto_atlz_cad txac on(tatc.txac_id = txac.txac_id)"
					     + " inner join micromedicao.leiturista leit on(tatc.leit_id = leit.leit_id)"
					     + " left join cadastro.funcionario func on(leit.func_id = func.func_id)"
					     + " left join cadastro.cliente clie on(leit.clie_id = clie.clie_id)"
					     + " where 1=1 ";
				
						 if(idArquivoTxt != null && !idArquivoTxt.equals("")){
							 consulta = consulta + " and txac.txac_id ="+ idArquivoTxt;							 
						 }

						 if(idEmpresa != null && !idEmpresa.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
							 consulta = consulta + " and leit.empr_id = "+ idEmpresa;							 
						 }
						 
						 if(idLeiturista != null  && !idLeiturista.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
							 consulta = consulta + " and leit.leit_id = "+idLeiturista;
						 }
						 
						 if(exibirCampos != null && !exibirCampos.trim().equals("")){
							 if (exibirCampos.equals("1")){
								 consulta = consulta + " and tcac.tcac_dtprocessamento is null ";
							 } else if (exibirCampos.equals("2")){
								 consulta = consulta + " and tcac.tcac_dtprocessamento is not null ";
							 }
						 }
						 
						 if (colunaImoveisSelecionados != null && !colunaImoveisSelecionados.isEmpty()){
							 consulta = consulta + " and tcac.tbco_id in (:colunaImoveisSelecionados)" ;
						 }
						 
						 if(matriculaImovel != null && !matriculaImovel.equals("")){
							 consulta = consulta + " and tatc.tatc_cdimovel ="+ matriculaImovel;							 
						 }
					 
						 consulta = consulta + " group by tatc.altp_id,tatc.tatc_cdimovel,tatc.tatc_cdcliente,func.func_nmfuncionario,clie.clie_nmcliente,txac.txac_id,tatc.tatc_icautorizado,tatc.tatc_idregistroalterado,tatc.imac_id";
						 consulta = consulta + " order by tatc.tatc_cdimovel,tatc.tatc_cdcliente,func.func_nmfuncionario,clie.clie_nmcliente,txac.txac_id,tatc.tatc_icautorizado,tatc.tatc_idregistroalterado";
						 

						 if (colunaImoveisSelecionados != null && !colunaImoveisSelecionados.isEmpty()){
								retornoConsulta = session.createSQLQuery(consulta)
										.addScalar("tipoAlteracao", Hibernate.INTEGER)
										.addScalar("idImovel", Hibernate.INTEGER)
										.addScalar("idCliente", Hibernate.INTEGER)
										.addScalar("qtdImovel", Hibernate.INTEGER)
										.addScalar("qtdCliente", Hibernate.INTEGER)
										.addScalar("nomeFuncionario", Hibernate.STRING)
										.addScalar("nomeCliente", Hibernate.STRING)
										.addScalar("idArquivo", Hibernate.INTEGER)
										.addScalar("icAutorizado", Hibernate.INTEGER)
										.addScalar("idRegistroAlterado",Hibernate.INTEGER)
										.addScalar("idImovelAtualizacaoCadastral", Hibernate.INTEGER)
										.setParameterList("colunaImoveisSelecionados", colunaImoveisSelecionados)
										//.addScalar("tipoAlteracao", Hibernate.INTEGER)
										//.addScalar("dataRealizacao", Hibernate.DATE)
										.list();
						} else {
											 
									retornoConsulta = session.createSQLQuery(consulta)
									.addScalar("tipoAlteracao", Hibernate.INTEGER)
									.addScalar("idImovel", Hibernate.INTEGER)
									.addScalar("idCliente", Hibernate.INTEGER)
									.addScalar("qtdImovel", Hibernate.INTEGER)
									.addScalar("qtdCliente", Hibernate.INTEGER)
									.addScalar("nomeFuncionario", Hibernate.STRING)
									.addScalar("nomeCliente", Hibernate.STRING)
									.addScalar("idArquivo", Hibernate.INTEGER)
									.addScalar("icAutorizado", Hibernate.INTEGER)
									.addScalar("idRegistroAlterado",Hibernate.INTEGER)
									.addScalar("idImovelAtualizacaoCadastral", Hibernate.INTEGER)
									.list();
								}

//				TreeMap<String, ConsultarMovimentoAtualizacaoCadastralHelper> colecao = 
//					new TreeMap<String, ConsultarMovimentoAtualizacaoCadastralHelper>();
				
				Integer ultimoImovel = null;
				Integer ultimoCliente = null;
				
				if (retornoConsulta.size() > 0) {
					Iterator helperIter = retornoConsulta.iterator();
					while (helperIter.hasNext()) {
		
						Object[] element = (Object[]) helperIter.next();
						
						Integer novoImovel = (Integer) element[1]; 
						Integer novoCliente = (Integer) element[2];
						
						if (ultimoImovel != null && 
								(novoImovel.intValue() == ultimoImovel.intValue() && novoCliente == null)){
							novoCliente = ultimoCliente;
						}
						
						//String chave = novoImovel + (novoCliente != null ? (novoCliente + "") : "");
						
						//ConsultarMovimentoAtualizacaoCadastralHelper helper = colecao.get(chave);
						
						ConsultarMovimentoAtualizacaoCadastralHelper helper = null;
						
						if (helper == null){
							
							helper = new ConsultarMovimentoAtualizacaoCadastralHelper();														 
							
							helper.setIdTipoAlteracao((Integer)element[0]);
							
							helper.setIdImovel((Integer)element[1]);
							
							helper.setIdCliente((Integer) element[2]);
							
							if((Integer) element[10] == null){
								String sql = " select  array_to_string(ARRAY" 
									+" (select tcac_cnvaloratual" 
									+" from seguranca.tab_atlz_cadastral tatc" 
									+" inner join seguranca.tab_col_atlz_cadastral tcac on (tatc.tatc_id = tcac.tatc_id)" 
							        +" inner join seguranca.tabela_coluna tbco on(tcac.tbco_id = tbco.tbco_id)" 
							        +" where tatc_cdimovel = "+(Integer)element[1]
							        +" and altp_id = 2 and tbco_nmcoluna in('loca_id','imac_cdsetorcomercial','imac_nnquadra','imac_nnlote','imac_nnsublote')), '.') as inscricao";
							        
								/*String sql = "SELECT SUBSTR(inscricao, 2, LENGTH(inscricao)) as inscricao FROM ( " 
								            +" SELECT REPLACE(MAX(SYS_CONNECT_BY_PATH(tcac_cnvaloratual, '.')), '.', '.') as inscricao FROM ( " 
								            +"      SELECT tcac_cnvaloratual, ROW_NUMBER() OVER (ORDER BY 1) r " 
											+" from seguranca.tab_atlz_cadastral tatc" 
											+" inner join seguranca.tab_col_atlz_cadastral tcac on (tatc.tatc_id = tcac.tatc_id)" 
									        +" inner join seguranca.tabela_coluna tbco on(tcac.tbco_id = tbco.tbco_id)" 
									        +" where tatc_cdimovel = "+(Integer)element[1]
								            +"             and altp_id = 2 and tbco_nmcoluna in('loca_id','imac_cdsetorcomercial','imac_nnquadra','imac_nnlote','imac_nnsublote')) " 
								            +"      START WITH r=1 CONNECT BY PRIOR r = r-1 ) "; */
								String inscricao = (String)session.createSQLQuery(sql)
										.addScalar("inscricao" , Hibernate.STRING)			
											.setMaxResults(1).uniqueResult();
								
								if(inscricao != null && !inscricao.equals("")){
									helper.setInscricao(inscricao);
								}
							}else{
								helper.setInscricao("NOVO IM�VEL");
							}
							
							if(element[2] != null && !element[2].equals("")){
								String sqlCliente = " select crtp_dsclienterelacaotipo as clienteTipo"
												   +" from cadastro.cliente_relacao_tipo"
												   +" where crtp_id in("
												   +" select tcac_cnvaloratual"
												   +" from seguranca.tab_atlz_cadastral tatc"
												   +" inner join seguranca.tab_col_atlz_cadastral tcac on (tcac.tatc_id = tatc.tatc_id)"
												   +" inner join seguranca.tabela_coluna tbco on(tcac.tbco_id = tbco.tbco_id)"
												   +" where tatc_cdcliente = "+(Integer)element[2] 
											       +" and tbco_nmcoluna = 'crtp_id' and altp_id = 2)";
						
								String clienteTipo = (String)session.createSQLQuery(sqlCliente)
										.addScalar("clienteTipo" , Hibernate.STRING)			
										.setMaxResults(1).uniqueResult();	
								
								if(clienteTipo != null && !clienteTipo.equals("")){
									helper.setTipoClienteNovo("NOVO "+clienteTipo);
								}
							}
														
							helper.setQtdAlteracaoImovel((Integer) element[3]);
							
							helper.setQtdAlteracaoCliente((Integer) element[4]);
							
							helper.setNomeFuncionario((String) element[5]);
							
							helper.setNomeCliente((String) element[6]);
							
							helper.setIdArquivo((Integer) element[7]);
							
							helper.setIcAutorizado((Integer) element[8]);
							
							helper.setIdRegistroAlterado((Integer) element[9]);
							
							helper.setIdImovelAtualizacaoCadastral((Integer) element[10]);
							
//							if (element[2] == null) {
//								chave = chave + "2";
//							}
//							colecao.put(chave, helper);							
							
						} else {
							if (helper.getQtdAlteracaoImovel() == 0){
								helper.setQtdAlteracaoImovel((Integer) element[3]); 
							}
						}
						
						ultimoImovel = (Integer)element[1];
						ultimoCliente = (Integer)element[2];
					
						if ( element[10] == null || (element[10] != null && element[2]  != null) ) {
							consultarMovimentoAtualizacaoCadastralHelper.add(helper);	
						}
						

					}
					//consultarMovimentoAtualizacaoCadastralHelper = colecao.values();
				}
            
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return consultarMovimentoAtualizacaoCadastralHelper;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date 03/06/2009
	 *
	 * @param idRegistroAlterado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public List consultarDadosTabelaColunaAtualizacaoCadastral(
			Integer idRegistroAlterado,
			Integer idArquivo, Integer idImovel, Integer idCliente,Integer idTipoAlteracao) throws ErroRepositorioException {
		List retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql =
				"select " +
				"	tac.id," + // 0
				"	tab.id," + // 1
				"	tab.descricao," + // 2
				"	col.id," + // 3
				"	col.descricaoColuna," + // 4
				"	tcol.id," + // 5
				"	tcol.colunaValorAnterior," + // 6
				"	tcol.colunaValorAtual," + // 7
				"	tcol.indicadorAutorizado," + // 8
				"	tcol.ultimaAlteracao," + // 9
				"	atp.id," + // 10
				"	atp.descricao, " + // 11
				"   col.coluna, " +//12
				"   tcol.dataProcessamento " +//13
				"from " +
				"	gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral tcol " +
				"	inner join tcol.tabelaColuna col " +
				"	inner join tcol.tabelaAtualizacaoCadastral tac " +
				"	inner join tac.tabela tab " +
				"	inner join tac.alteracaoTipo atp " +
				"where tac.idRegistroAlterado = :idRegistroAlterado " +
				"and tac.arquivoTextoAtualizacaoCadastral.id = :idArquivo " +
				"and tac.codigoImovel = :idImovel "+
				"and atp.id = :idTipoAlteracao ";
				if(idCliente != null){
					hql = hql + "and tac.codigoCliente = " +idCliente;
				}else{
					hql = hql + "and tac.codigoCliente is null";
				}
				hql = hql + " order by tcol.id";
			 
			 retorno = session.createQuery(hql)
			 	.setInteger("idRegistroAlterado", idRegistroAlterado)
			 	.setInteger("idArquivo", idArquivo)
			 	.setInteger("idImovel", idImovel)
			 	.setInteger("idTipoAlteracao",idTipoAlteracao)
			 	.list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Ivan Sergio
	 * @date 12/06/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral tcol " +
			"SET tcol.indicadorAutorizado = :indicador, " +
			"tcol.ultimaAlteracao = :dataAtual " ;
		if(indicador.equals(ConstantesSistema.SIM)){
			hql = hql + ",tcol.dataProcessamento = :dataAtual ";
		}
		
			
		hql = hql + "WHERE tcol.id = :idAtualizacaoCadastral";
		
		try {
			session.createQuery(hql)
				.setShort("indicador", indicador)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idAtualizacaoCadastral", idAtualizacaoCadastral)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	
	/**
	 * @author Ana Maria
	 * @date 16/06/2009
	 *
	 * @param idArgumento
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarIndicadorAutorizacaoTabelaAtualizacaoCadastral(Integer idArgumento, Short indicador) 
	throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String update = "UPDATE seguranca.tab_atlz_cadastral " +
							"SET tatc_icautorizado = ?, " +
							"tatc_ultimaalteracao = ? " +
							"WHERE tatc_id in(SELECT tatc.tatc_id " +
							"                 FROM seguranca.tab_atlz_cadastral tatc" +
							"                 INNER JOIN seguranca.operacao_efetuada opef on(tatc.opef_id = opef.opef_id)" +
							"				   where opef.opef_cnargumento = ? )";
			
			st = jdbcCon.prepareStatement(update);
			st.setShort(1, indicador);
			st.setTimestamp(2, Util.getSQLTimesTemp(new Date()));
			st.setInt(3, idArgumento);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idEmpresa
	 * @param idArquivo
	 * @param idLeiturista
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRegistroAutorizadoTabelaAtualizacaoCadastral(
			String idEmpresa, String idArquivo, String idLeiturista) throws ErroRepositorioException {
		List retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql = " select distinct(tatc.idRegistroAlterado), tatc.tabela.id, tatc.id, tatc.alteracaoTipo.id "
			    + " from TabelaAtualizacaoCadastral tatc "
			    + " inner join tatc.arquivoTextoAtualizacaoCadastral txac "
			    + " inner join txac.leiturista leit "
			    + " where indicadorAutorizado = 1 and " 
			    + " leit.empresa.id = "+idEmpresa;
			
			 if(idArquivo != null && !idArquivo.equals("")){
				 hql = hql + " and txac.id ="+ idArquivo;							 
			 }
			 
			 if(idLeiturista != null  && !idLeiturista.trim().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				 hql = hql + " and leit.id = "+idLeiturista;
			 }

			 retorno = session.createQuery(hql).list();
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Ana Maria
	 * @date 25/06/2009
	 *
	 * @param idTabelaAtualizacaoCadastral
	 * @return List
	 * @throws ErroRepositorioException
	 */
	public List pesquisarRegistroAutorizadoTabelaColunaAtualizacaoCadastral(
			Integer idTabelaAtualizacaoCadastral) throws ErroRepositorioException {
		List retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql = " select coluna, colunaValorAtual "
			    + " from TabelaColunaAtualizacaoCadastral tcac "
			    + " inner join tcac.tabelaColuna tbco "
			    + " where indicadorAutorizado = 1 and tcac.tabelaAtualizacaoCadastral.id ="+idTabelaAtualizacaoCadastral;
			
			 retorno = session.createQuery(hql).list();
			 
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * @author Genival Barbosa
	 * @date 27/07/2009
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorAutorizacaoAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaAtualizacaoCadastral tcol " +
			"SET tcol.indicadorAutorizado = :indicador, " +
			"tcol.ultimaAlteracao = :dataAtual " +
		    "WHERE tcol.id = :idAtualizacaoCadastral";
		
		try {
			session.createQuery(hql)
				.setShort("indicador", indicador)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idAtualizacaoCadastral", idAtualizacaoCadastral)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter cliente pendente de atualizacao do imovel.
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idObjeto
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarOperacaoPendente(Integer idObjeto, Integer idUsuario) throws ErroRepositorioException {
		Integer retorno = null;
		String hql;
		Session session = HibernateUtil.getSession();
		
		try {
			hql =
				"select " +
				"	tla.operacaoEfetuada.id " +
				"from gcom.seguranca.transacao.TabelaLinhaAlteracao tla " +
				"inner join tla.operacaoEfetuada ope " +
				"inner join ope.usuarioAlteracoes usu " +
				"where " +
				"tla.id1 = " + idObjeto + " " +
				"and tla.id2 = -1 " +
				"and ope.operacao.id in (" + 
				Operacao.OPERACAO_CLIENTE_INSERIR + ", " +
				Operacao.OPERACAO_CLIENTE_ATUALIZAR + ") " +
				"and usu.usuario.id = " + idUsuario + " " +
				"order by " +
				"tla.operacaoEfetuada.id desc";

			retorno = (Integer) session.createQuery(hql).setMaxResults(1).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return retorno;
	}
	
	/**
	 * CRC2103 - [FS0026] - Realiza a alteracao em OperacaoEfetuada em cliente
	 * pendente de atualizacao do imovel.
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idOperacaoEfetuada
	 * @param idGrupoAtributo
	 * @throws ErroRepositorioException
	 */
	public void atualizarOperacaoEfetuadaPendente(Integer idOperacaoEfetuada, Integer idGrupoAtributo) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.acesso.OperacaoEfetuada ope " +
			"SET ope.atributoGrupo.id = :idGrupoAtributo, " +
			"ope.ultimaAlteracao = :dataAtual " +
		    "WHERE ope.id = :idOperacaoEfetuada";
		
		try {
			session.createQuery(hql)
				.setInteger("idGrupoAtributo", idGrupoAtributo)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idOperacaoEfetuada", idOperacaoEfetuada)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * CRC2103 - [FS0026] - Realiza a alteracao em TabelaLinhaAlteracao em cliente
	 * pendente de atualizacao do imovel. 
	 *
	 * @author Ivan Sergio
	 * @date 24/07/2009
	 *
	 * @param idOperacaoEfetuada
	 * @param idImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarTabelaLinhaAlteracaoPendente(Integer idOperacaoEfetuada, Integer idImovel) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaLinhaAlteracao tla " +
			"SET tla.id2 = :idImovel, " +
			"tla.ultimaAlteracao = :dataAtual " +
		    "WHERE tla.operacaoEfetuada.id = :idOperacaoEfetuada";
		
		try {
			session.createQuery(hql)
				.setInteger("idImovel", idImovel)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idOperacaoEfetuada", idOperacaoEfetuada)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author Ana Maria
	 * @date 17/12/2009
	 *
	 * @param codigoImovel
	 * @param codigoCliente
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarClienteRelacaoTipoAtualizacaoCadastral(Integer codigoImovel, Integer codigoCliente) 
		throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		PreparedStatement st = null;

		try {
			
			Connection jdbcCon = session.connection();
			
			String update = "UPDATE seguranca.tab_col_atlz_cadastral " +
							"SET tcac_cnvaloratual = 4 " +
							"WHERE tcac_id in(SELECT tcac_id " +
							"                 FROM seguranca.tab_col_atlz_cadastral tcac" +
							"                 INNER JOIN seguranca.tab_atlz_cadastral tatc on(tatc.tatc_id = tcac.tatc_id)" +
							"                 INNER JOIN seguranca.tabela_coluna tbco on(tbco.tbco_id = tcac.tbco_id)" +
							"				  WHERE tatc_cdimovel = ? AND tatc_cdcliente = ? AND tbco_nmcoluna = 'crtp_id')";
			
			st = jdbcCon.prepareStatement(update);
			st.setInt(1, codigoImovel);
			st.setInt(2, codigoCliente);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					throw new ErroRepositorioException(e, "Erro no Hibernate");
				}
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * @author S�vio Luiz
	 * @date 19/04/2011
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public TabelaColunaAtualizacaoCadastral pesquisarTabelaColunaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		TabelaColunaAtualizacaoCadastral tabelaColunaAtualizacaoCadastral = null;
		
		try {
		
		
		String hql = " select tcac "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join fetch  tcac.tabelaAtualizacaoCadastral tac " 
		    + " where tcac.id ="+idAtualizacaoCadastral;
		
		tabelaColunaAtualizacaoCadastral = (TabelaColunaAtualizacaoCadastral)session.createQuery(hql).setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return tabelaColunaAtualizacaoCadastral;
	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClientePeloCPF(String cpf,Integer idCliente)
			throws ErroRepositorioException {

		Cliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select cliente "
					+ "from Cliente cliente "
					+ " left join fetch cliente.clienteTipo clienteTipo "
					+ " left join fetch cliente.ramoAtividade ramoAtividade "
					+ " left join fetch cliente.profissao profissao "
					+ "where cliente.cpf = :cpf ";
			if(idCliente != null){
				consulta = consulta + " and cliente.id <> :idCliente";
			}

			retorno = (Cliente) session.createQuery(consulta)
				.setString("cpf",cpf).setMaxResults(1)
				.setInteger("idCliente",idCliente)
				.uniqueResult();

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
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterClientePeloCNPJ(String cnpj,Integer idCliente)
			throws ErroRepositorioException {

		Cliente retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		try {

			consulta = "select cliente " // 1
					+ "from Cliente cliente "
					+ " left join fetch cliente.clienteTipo clienteTipo "
					+ " left join fetch cliente.ramoAtividade ramoAtividade "
					+ " left join fetch cliente.profissao profissao "
					+ "where cliente.cnpj = :cnpj";
					if(idCliente != null){
						consulta = consulta + " and cliente.id <> :idCliente";
					}

			retorno = (Cliente) session.createQuery(consulta)
				.setString("cnpj",cnpj).setMaxResults(1)
				.setInteger("idCliente",idCliente)
				.uniqueResult();

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
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterValorAtualTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select tcac.colunaValorAtual "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join tcac.tabelaAtualizacaoCadastral tac " 
		    + " inner join tcac.tabelaColuna tc " 
		    + " where tac.id = :idAtualizacaoCadastral "
		    + " and tc.id = :idTabelaColuna "
		    + " and tcac.indicadorAutorizado = :icAutorizado ";
		
		retorno = (String)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setInteger("idTabelaColuna",idTabelaColuna)
		           .setShort("icAutorizado",ConstantesSistema.SIM)
		           .setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ImovelSubcategoria recuperaImovelSubcategoriaAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException {
		
		ImovelSubcategoriaAtualizacaoCadastral imovelSubcategoriaAtualizacaoCadastral = null;
		ImovelSubcategoria imovelSubcategoria = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select imSubAtC "
		    + " from ImovelSubcategoriaAtualizacaoCadastral imSubAtC "
		    + " where imSubAtC.id in ( "
		    + "                      select tac.idRegistroAlterado "
		    + "                      from TabelaAtualizacaoCadastral tac " 
		    + "                      where tac.id = :idAtualizacaoCadastral) ";
		
		imovelSubcategoriaAtualizacaoCadastral = (ImovelSubcategoriaAtualizacaoCadastral)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setMaxResults(1).uniqueResult();
		
		if(imovelSubcategoriaAtualizacaoCadastral != null){
			String hql1 = " select imSub "
			    + " from ImovelSubcategoria imSub "
			    + " left join fetch imSub.comp_id.subcategoria subcategoria "
				+ " left join fetch subcategoria.categoria "
			    + " where imSub.comp_id.imovel.id = "+ imovelSubcategoriaAtualizacaoCadastral.getIdImovel()
			    + " and subcategoria.id = "+ imovelSubcategoriaAtualizacaoCadastral.getIdSubcategoria() ;
			
			imovelSubcategoria = (ImovelSubcategoria)session.createQuery(hql1)
			           .setMaxResults(1).uniqueResult();
		}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return imovelSubcategoria;

	}
	
	/**
	 * @author S�vio Luiz
	 * @date 05/05/2011
	 *
	 * @param idAtualizacaoCadastral
	 * @param indicador
	 * @throws ErroRepositorioException
	 */
	public void atualizarDadosTabelaAtualizacaoCadastral(
			Integer idAtualizacaoCadastral,
			Short indicador) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		
		String hql =
			"UPDATE gcom.seguranca.transacao.TabelaAtualizacaoCadastral tcol " +
			"SET tcol.indicadorAutorizado = :indicador, " +
			"tcol.ultimaAlteracao = :dataAtual " +
			"tcol.dataProcessamento = :dataAtual "+
		    "WHERE tcol.id = :idAtualizacaoCadastral";
		
		try {
			session.createQuery(hql)
				.setShort("indicador", indicador)
				.setTimestamp("dataAtual", new Date())
				.setInteger("idAtualizacaoCadastral", idAtualizacaoCadastral)
				.executeUpdate();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * Verifica se existe na base algum rela��o de cliente im�vel que seja com outro im�vel
	 * 
	 * @author S�vio Luiz
	 * @date 06/05/2011
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovelDiferenteImovel(Integer idImovel,Integer idCliente)
		throws ErroRepositorioException {

		Collection retorno = null;
		Session session = HibernateUtil.getSession();
		String consulta = null;
		
		try {
			consulta = "SELECT clienteImovel " 
					 + "from ClienteImovel clienteImovel " 
					 + "where clienteImovel.cliente.id = :idCliente "
					 + " and clienteImovel.imovel.id <> :idImovel " 
					 + "and clienteImovel.dataFimRelacao is null and "
					 + "clienteImovel.clienteRelacaoTipo.id = 2";
		
			retorno = session.createQuery(consulta)
				.setInteger("idImovel",idImovel.intValue())
				.setInteger("idCliente",idCliente.intValue())
				.list();
		
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 11/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteRelacaoTipo recuperaTipoRelacaoClienteAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException {
		
		ClienteRelacaoTipo clienteRelacaoTipo = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select idClienteRelacaoTipo "
		    + " from ClienteAtualizacaoCadastral cliAtuCadastral "
		    + " where cliAtuCadastral.id in ( "
		    + "                      select tac.idRegistroAlterado "
		    + "                      from TabelaAtualizacaoCadastral tac " 
		    + "                      where tac.id = :idAtualizacaoCadastral) ";
		
		Integer idTipoRelacaoCliente = (Integer)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setMaxResults(1).uniqueResult();
		
		if(idTipoRelacaoCliente != null){
			String hql1 = " select crt "
			    + " from ClienteRelacaoTipo crt "
			    + " where crt.id = "+ idTipoRelacaoCliente;
			
			clienteRelacaoTipo = (ClienteRelacaoTipo)session.createQuery(hql1)
			           .setMaxResults(1).uniqueResult();
		}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return clienteRelacaoTipo;

	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 16/05/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ClienteFone recuperaClienteFoneAtualizacaoCadastral(Integer idAtualizacaoCadastral)
			throws ErroRepositorioException {
		
		Object[] dadosClienteFone = null;
		ClienteFone clienteFone = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select cliFoneAtC.telefone,cliAtC.idCliente "
		    + " from ClienteFoneAtualizacaoCadastral cliFoneAtC "
		    + " inner join cliFoneAtC.clienteAtualizacaoCadastral cliAtC "
		    + " where cliFoneAtC.id in ( "
		    + "                      select tac.idRegistroAlterado "
		    + "                      from TabelaAtualizacaoCadastral tac " 
		    + "                      where tac.id = :idAtualizacaoCadastral) ";
		
		dadosClienteFone = (Object[])session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setMaxResults(1).uniqueResult();
		
		if(dadosClienteFone != null){
			String hql1 = " select cliF "
			    + " from ClienteFone cliF "
			    + " where cliF.telefone = "+ dadosClienteFone[0]
			    + " and cliF.cliente.id = "+ (Integer)dadosClienteFone[1] ;
			
			clienteFone = (ClienteFone)session.createQuery(hql1)
			           .setMaxResults(1).uniqueResult();
		}


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return clienteFone;

	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String obterValorAnteriorTabelaColunaAtualizacaoCadastral(Integer idAtualizacaoCadastral, Integer idTabelaColuna)
			throws ErroRepositorioException {
		
		String retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select tcac.colunaValorAnterior "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join tcac.tabelaAtualizacaoCadastral tac " 
		    + " inner join tcac.tabelaColuna tc " 
		    + " where tac.id = :idAtualizacaoCadastral "
		    + " and tc.id = :idTabelaColuna "
		    + " and tcac.indicadorAutorizado = :icAutorizado ";
		
		retorno = (String)session.createQuery(hql)
		           .setInteger("idAtualizacaoCadastral",idAtualizacaoCadastral)
		           .setInteger("idTabelaColuna",idTabelaColuna)
		           .setShort("icAutorizado",ConstantesSistema.SIM)
		           .setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
	
	/**
	 * [[UC1165] - Confirmar Altera��es Cadastrais
	 * 
	 * @author S�vio Luiz
	 * @date 20/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public TabelaAtualizacaoCadastral obterIdTabelaAtualizacaoCadastralPorCliente(Integer idCliente, Integer idTabelaColuna)
			throws ErroRepositorioException {
		
		TabelaAtualizacaoCadastral retorno = null;
		Session session = HibernateUtil.getSession();
		
		try {
		
		
		String hql = " select tac "
		    + " from TabelaColunaAtualizacaoCadastral tcac "
		    + " inner join tcac.tabelaAtualizacaoCadastral tac " 
		    + " inner join tcac.tabelaColuna tc " 
		    + " where tac.codigoCliente = :idCliente "
		    + " and tc.id = :idTabelaColuna ";
		
		retorno = (TabelaAtualizacaoCadastral)session.createQuery(hql)
		           .setInteger("idCliente",idCliente)
		           .setInteger("idTabelaColuna",idTabelaColuna)
		           .setMaxResults(1).uniqueResult();


		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}

	
	/**
	 * [UC1537] - Gerar Relat�rio Clientes com CPF CNPJ Validados e Informado no Mobile
	 * 
	 * @author Diogo Luiz
	 * @date 21/08/2013
	 * 
	 * @return
	 */
	public Collection pesquisarExistenciaIdentificadorArquivo(Integer idArquivo)
			throws ErroRepositorioException{
		
		Collection colecaoArquivo = null;
		Session sessao = HibernateUtil.getSession();
		
		try{			
			String sql = "select tcac.tatc_id as tbAtualizacaoArquivo "
					+ "FROM seguranca.tab_atlz_cadastral as tac "
					+ "INNER JOIN seguranca.tab_col_atlz_cadastral as tcac "
					+ "on tcac.tatc_id = tac.tatc_id and tcac.tbco_id in (271,275) "
					+ "and tcac.tcac_cnvaloranterior <> tcac.tcac_cnvaloratual "
					+ "INNER JOIN cadastro.cliente as c on c.clie_id = tac.tatc_cdcliente "
					+ "and c.clie_iccpfcnpjvalidado = " + ConstantesSistema.SIM.intValue()
					+ "WHERE tac.txac_id = :idArquivo";
			
			colecaoArquivo = sessao.createSQLQuery(sql)
					.addScalar("tbAtualizacaoArquivo", Hibernate.INTEGER)
					.setInteger("idArquivo", idArquivo)					
					.list();			
			
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(sessao);	
		}	
		
		return colecaoArquivo;
	}
	
	

	/**
	 * [UC1537] - Gerar Relat�rio Clientes com CPF CNPJ Validados e Informado no Mobile
	 * 
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return
	 */
	public Collection pesquisarExistenciaDados(String idEmpresa,
			String periodoInicial, String periodoFinal, String idLeiturista)
			throws ErroRepositorioException {
		
		Date dtInicial = Util.converteStringParaDate(periodoInicial);
		Date dtFinal = Util.converteStringParaDate(periodoFinal);
		Integer empresa = Integer.parseInt(idEmpresa);
		Integer leiturista = Integer.parseInt(idLeiturista);
		Collection colecaoArquivo = null;
		Session sessao = HibernateUtil.getSession();		
		
		try{
		String sql = "select count(*) as qtd from seguranca.tab_atlz_cadastral  tac "
				+ "inner join seguranca.tab_col_atlz_cadastral tcac on tcac.tatc_id = tac.tatc_id "
				+ "and tbco_id in  (271,275) "
				+ "and tcac_dtprocessamento between :dtInicial and :dtFinal "
				+ "and tcac_cnvaloranterior <> tcac_cnvaloratual "
				+ "inner join cadastro.cliente cl on cl.clie_id = tac.tatc_cdcliente and clie_iccpfcnpjvalidado = " 
				+ ConstantesSistema.SIM.intValue()
				+ "inner join cadastro.empresa emp on emp.empr_id = " + empresa 
				+ "inner join micromedicao.leiturista ml on ml.empr_id = " + empresa;
		
		if(leiturista != null && !leiturista.equals("") && !leiturista.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			sql += "and ml.leit_id = " + leiturista;
		}
		
		colecaoArquivo = sessao.createSQLQuery(sql)		
				.addScalar("qtd", Hibernate.INTEGER)
				.setDate("dtInicial", dtInicial)
				.setDate("dtFinal", dtFinal)								
				.list();
		}catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(sessao);	
		}	                                                 
		
		return colecaoArquivo;
	}
	
	
	/**
	 * [UC1537] - Gerar Relat�rio Clientes com CPF CNPJ Validados e Informado no Mobile
	 * 
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return
	 */
	
	public Collection gerarRelatorioClienteCpfCnpjValidados(Integer idIdentArquivo)
			throws ErroRepositorioException{
		
		Collection colecaoArquivo = null;
		Session sessao = HibernateUtil.getSession();		
		
		try{
		String sql = "select tac.tatc_cdcliente as codCliente, " // 0 
				+ "cl.clie_nmcliente as descricao, " //1
				+ "tcac_cnvaloratual as valorAtual, " //2
				+ "tcac_cnvaloranterior as valorAnterior " //3				
				+ "from seguranca.tab_atlz_cadastral tac "
				+ "inner join seguranca.tab_col_atlz_cadastral tcac on tcac.tatc_id = tac.tatc_id "
				+ "and tbco_id in (271,275) and tcac_cnvaloranterior <> tcac_cnvaloratual "
				+ "inner join cadastro.cliente cl on cl.clie_id = tatc_cdcliente and clie_iccpfcnpjvalidado = "
				+ ConstantesSistema.SIM.intValue()
				+ " where txac_id = " + idIdentArquivo;
		
		colecaoArquivo = sessao.createSQLQuery(sql)
				.addScalar("codCliente", Hibernate.INTEGER)
				.addScalar("descricao", Hibernate.STRING)
				.addScalar("valorAtual", Hibernate.STRING)
				.addScalar("valorAnterior", Hibernate.STRING)
				.list();
		
			return colecaoArquivo;
			
		}
		catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(sessao);	
		}		
	}
	
	
	/**
	 * [UC1537] - Gerar Relat�rio Clientes com CPF CNPJ Validados e Informado no Mobile
	 * 
	 * @author Diogo Luiz
	 * @date 22/08/2013
	 * 
	 * @return
	 */
	
	public Collection gerarRelatorioClienteCpfCnpjValidados(String idEmpresa, String periodoInicial, 
			String periodoFinal, String idLeiturista)
			throws ErroRepositorioException{		
		
		Date dtInicial = Util.converteStringParaDate(periodoInicial);
		Date dtFinal = Util.converteStringParaDate(periodoFinal);
		Integer empresa = Integer.parseInt(idEmpresa);
		Integer leiturista = Integer.parseInt(idLeiturista);
		Collection colecaoArquivo = null;
		Session sessao = HibernateUtil.getSession();		
		
		try{
		String sql = "select tac.tatc_cdcliente as codCliente, " // 0 
				+ "cl.clie_nmcliente as descricao, " //1
				+ "tcac_cnvaloratual as valorAtual, " //2
				+ "tcac_cnvaloranterior as valorAnterior " //3				
				+ "from seguranca.tab_atlz_cadastral tac "
				+ "inner join seguranca.tab_col_atlz_cadastral tcac on tcac.tatc_id = tac.tatc_id "
				+ "and tbco_id in (271,275) and tcac_cnvaloranterior <> tcac_cnvaloratual "
				+ "inner join cadastro.cliente cl on cl.clie_id = tatc_cdcliente and clie_iccpfcnpjvalidado = "
				+ ConstantesSistema.SIM.intValue()
				+ " inner join cadastro.empresa emp on empr_id = :empresa " 				
				+ "where tcac_dtprocessamento between :dtInicial and :dtFinal ";
		
		if(leiturista != null && !leiturista.equals("") && !leiturista.equals(-1)){
			sql += "and leit_id = " + leiturista;
		}	
		
		colecaoArquivo = sessao.createSQLQuery(sql)					
				.addScalar("codCliente", Hibernate.INTEGER)
				.addScalar("descricao", Hibernate.STRING)
				.addScalar("valorAtual", Hibernate.STRING)
				.addScalar("valorAnterior", Hibernate.STRING)
				.setInteger("empresa", empresa)
				.setDate("dtInicial", dtInicial)
				.setDate("dtFinal", dtFinal)
				.list();
		
			return colecaoArquivo;
			
		}
		catch(HibernateException e){
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		}finally{
			HibernateUtil.closeSession(sessao);	
		}		
	}
	
	
}
