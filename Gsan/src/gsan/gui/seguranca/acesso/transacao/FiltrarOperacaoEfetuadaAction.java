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
package gsan.gui.seguranca.acesso.transacao;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.transacao.RelatorioOperacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.SistemaException;
import gsan.util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarOperacaoEfetuadaAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * @author toscano / Romulo Aurelio - Correcao de Bug 
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

  
		ActionForward retorno = actionMapping.findForward("exibirConsultaOperacaoEfetuada");

		FiltrarOperacaoEfetuadaActionForm form = ( FiltrarOperacaoEfetuadaActionForm ) actionForm;

		// inicia a sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("usuarioAcao");

		Integer   idUsuarioAcao    = null;
		Integer   idFuncionalidade = null;
		String[]  idOperacoes      = null;
		String    idUsuario        = null;
		Date      dataInicial      = null;
		Date      dataFinal        = null;
		Date      horaInicial      = null;
		Date      horaFinal        = null;
		Integer   idTabela         = null;
		Integer[] idTabelaColuna   = null;
		Integer   id1              = null;
		String    unidadeNegocio   = null;

		
		
		if ( httpServletRequest.getParameter("funcionalidadeLimpa") != null &&
				  httpServletRequest.getParameter("funcionalidadeLimpa").equals("1") ){
			sessao.removeAttribute("idFuncionalidade");
			sessao.removeAttribute("argumentos");			
			sessao.removeAttribute("nomeOperacaoSelecionada");
		}

		if ( !"".equals(form.getIdFuncionalidade()) && form.getIdFuncionalidade() != null ) {
			idFuncionalidade = new Integer(form.getIdFuncionalidade());
			sessao.setAttribute("idFuncionalidade", idFuncionalidade);
		} 
		else {
			if ( sessao.getAttribute("idFuncionalidade") != null ) 
			idFuncionalidade = (Integer) sessao.getAttribute("idFuncionalidade");
			
		}
		if ( httpServletRequest.getParameter("usuarioLimpo") != null &&
		     httpServletRequest.getParameter("usuarioLimpo").equals("1") ){
			sessao.removeAttribute("idUsuario");
			sessao.removeAttribute("nomeUsuario");
		}
		
		if ( httpServletRequest.getParameter("dataLimpa") != null &&
			 httpServletRequest.getParameter("dataLimpa").equals("1") ){
			sessao.removeAttribute("dataInicial");
			sessao.removeAttribute("dataFinal");
		}	
		
		if ( httpServletRequest.getParameter("horaLimpa") != null &&
			 httpServletRequest.getParameter("horaLimpa").equals("1") ){
			sessao.removeAttribute("horaInicial");
			sessao.removeAttribute("horaFinal");
		}	
		
		if ( httpServletRequest.getParameter("argumentoLimpo") != null &&
			 httpServletRequest.getParameter("argumentoLimpo").equals("1")){
			sessao.removeAttribute("argumentos");
		}		

		if ( form.getNomeFuncionalidade() != null && !form.getNomeFuncionalidade().equals("") ){
			sessao.setAttribute("nomeOperacaoSelecionada",form.getNomeFuncionalidade());	
		} 
		
		else {
			sessao.removeAttribute("idOperacoes");
			sessao.setAttribute("variasOperacoesSelecionadas","true");
		}
		
		if ( form.getOperacoes() != null ){
			
			idOperacoes = form.getOperacoes();
			if ( idOperacoes.length > 1 ){
				sessao.setAttribute("variasOperacoesSelecionadas","true");	
			} 
			else {				
				sessao.setAttribute("nomeOperacaoSelecionada", form.getNomeFuncionalidade());
				sessao.setAttribute("variasOperacoesSelecionadas" , "false");
			}
			sessao.setAttribute("idOperacoes", idOperacoes);
			
		}  
		else {
			sessao.removeAttribute("idOperacoes");
			sessao.setAttribute("variasOperacoesSelecionadas","true");
		}
		
		
		//Unidade Neg�cio
		if ( form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO) ){
			
			unidadeNegocio = form.getUnidadeNegocio();
			 
			sessao.setAttribute("unidadeNegocio", unidadeNegocio);
			
		}  
		else {
			sessao.removeAttribute("unidadeNegocio");
		}
		
		
		if ( !"".equals(form.getIdUsuario()) && form.getIdUsuario() != null ) {
			idUsuario = form.getIdUsuario();
			sessao.setAttribute("idUsuario", idUsuario);
		} 
		else {
//			sessao.removeAttribute("idUsuario");
			if ( sessao.getAttribute("idUsuario") != null ) {
				idUsuario = (String) sessao.getAttribute("idUsuario");				
			}
		}
		// Neste caso, foi selecionado um usuario, sem especificar as operacoes
		if ( idUsuario != null && (form.getOperacoes() == null || form.getOperacoes().length == 0) ){
			sessao.setAttribute("variasOperacoesSelecionadas","true");			
		}

		if ( !"".equals(form.getDataInicial()) && form.getDataInicial() != null ) {
			dataInicial = Util.converteStringParaDate(form.getDataInicial());
			sessao.setAttribute("dataInicial", dataInicial);
		} 
		else {
//			sessao.removeAttribute("dataInicial");
			if (sessao.getAttribute("dataInicial") != null) {
				//String data = (String)sessao.getAttribute("dataInicial");
				dataInicial = (Date) sessao.getAttribute("dataInicial"); /*Util.converteStringParaDate(data);*/
			}
		}

		if ( !"".equals(form.getDataFinal()) && form.getDataFinal() != null ) {
			dataFinal = Util.converteStringParaDate(form.getDataFinal());
			sessao.setAttribute("dataFinal", dataFinal);
		} 
		else {
//			sessao.removeAttribute("dataFinal");
			if ( sessao.getAttribute("dataFinal") != null ) {
				dataFinal = (Date) sessao.getAttribute("dataFinal");
				//Util.converteStringParaDate((String)sessao.getAttribute("dataFinal"));
			}
		}

		if ( !"".equals(form.getHoraInicial()) && form.getHoraInicial() != null ) {
			horaInicial = Util.converterStringParaHoraMinuto(form
					.getHoraInicial());
			sessao.setAttribute("horaInicial", horaInicial);
		} 
		else {
//			sessao.removeAttribute("horaInicial");
			if (sessao.getAttribute("horaInicial") != null) {
			horaInicial = /*Util.converterStringParaHoraMinuto(*/(Date) sessao
						.getAttribute("horaInicial");
			}
		}

		if ( !"".equals(form.getHoraFinal()) && form.getHoraFinal() != null ) {
			horaFinal = Util.converterStringParaHoraMinuto(form.getHoraFinal());
			sessao.setAttribute("horaFinal", horaFinal);
		} 
		else {
//			sessao.removeAttribute("horaFinal");
			if (sessao.getAttribute("horaFinal") != null) {
				horaFinal = (Date) sessao.getAttribute("horaFinal");
			}
		}

		if ( !"".equals(form.getIdTabela()) && form.getIdTabela() != null ) {
			idTabela = new Integer(form.getIdTabela());
			sessao.setAttribute("idTabela", idTabela);
		} 
		else {
			if ( sessao.getAttribute("idTabela") != null ) {
				idTabela = (Integer) sessao.getAttribute("idTabela");
			}
		}

	if ( form.getIdTabelaColuna() != null && form.getIdTabelaColuna().length > 0 ) {
		
			idTabelaColuna = new Integer[form.getIdTabelaColuna().length];
			for ( int i = 0 ; i < form.getIdTabelaColuna().length ; i++ ) {
	try {
	if ( form.getIdTabelaColuna()[i] != null && !"".equals(form.getIdTabelaColuna()[i]) ) {
		
		Integer integer = new Integer( form.getIdTabelaColuna()[i] );
			if ( integer != null && integer.intValue() > 0 ) 
			idTabelaColuna[i] = integer;
			
	}
	} 
	catch (Exception e) {
	}
		   }
	}
	
		if (form.getIdTabelaColuna().length == 0
				&& !"".equals(form.getIdTabelaColunaSemOperacao())
				&& form.getIdTabelaColunaSemOperacao() != null) {
			idTabelaColuna = new Integer[1];
			try {
	idTabelaColuna[0] = new Integer( form.getIdTabelaColunaSemOperacao() );
			} catch (Exception e) {
			}
		}

    if ( dataInicial != null && dataFinal != null && dataFinal.getTime() < dataInicial.getTime() ) {
			throw new ActionServletException("atencao.data_fim_menor_inicio");
		}

    if ( horaInicial != null && horaFinal != null && horaFinal.getTime() < horaInicial.getTime() ) {
			throw new ActionServletException("atencao.hora_fim_menor_inicio");
		}

		Fachada fachada = Fachada.getInstancia();


 if ( sessao.getAttribute("acao") == null || !"relatorio".equalsIgnoreCase(sessao.getAttribute("acao").toString())) {
			
			// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
		
			// Em argumentosAdicionados � guardado o par (nome do argumento = id da coluna do argumento de pesquisa, 
			// valor do argumento)
			Hashtable<String,String> argumentos = new Hashtable<String,String>();
			
			if( form.getValorArgumentoPesquisa() != null && !form.getValorArgumentoPesquisa().equals("") ){
				argumentos.put(form.getIdArgumentoPesquisa(), form.getValorArgumentoPesquisa());
				form.setValorArgumentoPesquisa("");
				sessao.setAttribute("argumentos", argumentos);				
			} 
			else {
				Object arg = sessao.getAttribute("argumentos");
				if (arg != null)
				argumentos = (Hashtable) arg;	
										
			}
			
			
			sessao.setAttribute("numeroPaginasPesquisa" , httpServletRequest.getAttribute("numeroPaginasPesquisa") );
			
			Integer totalRegistros = (Integer) fachada.pesquisarUsuarioAlteracaoDasOperacoesEfetuadasHqlCount(
					idUsuarioAcao, idOperacoes, idUsuario, dataInicial,
					dataFinal, horaInicial, horaFinal, argumentos, id1, unidadeNegocio);

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao( httpServletRequest , retorno , totalRegistros );

			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
			// da pesquisa que est� no request
			
			//Integer idUsuarioInt = (idUsuario == null) ? null : Integer.parseInt(idUsuario); 
			//TODO colocar a RM
			Collection coll = Fachada.getInstancia().pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
					idUsuarioAcao , idOperacoes , idUsuario , dataInicial ,
					dataFinal , horaInicial , horaFinal   , argumentos, id1 , 
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") , unidadeNegocio, false);

			//			    		Collection coll = (Collection) fachada.pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(
			//			    				idUsuarioAcao,idOperacao, idUsuario,dataInicial, dataFinal, horaInicial, horaFinal, idTabela, idTabelaColuna, id1,
			//			    				(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));

			Collection resumos = new ArrayList();
			if (coll == null || coll.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Transa��o");
			} 
			else {
				for ( Iterator iter = coll.iterator() ; iter.hasNext(); ) {
					OperacaoEfetuada operacaoEfet = (OperacaoEfetuada) iter.next();
					resumos.add( consultarInformacoesExtrasOperacaoEfetuada(operacaoEfet) );					
				}
			}
			sessao.setAttribute( "resumosDados"     , resumos          ); 
			sessao.setAttribute( "operacaoEfetuada" , coll             );
			sessao.setAttribute( "idUsuarioAcao"    , idUsuarioAcao    );
			sessao.setAttribute( "idFuncionalidade" , idFuncionalidade );
			sessao.setAttribute( "idUsuario"        , idUsuario        );
			sessao.setAttribute( "dataInicial"      , dataInicial      );
			sessao.setAttribute( "dataFinal"        , dataFinal        );
			sessao.setAttribute( "horaInicial"      , horaInicial      );
			sessao.setAttribute( "horaFinal"        , horaFinal        );
			sessao.setAttribute( "idTabela"         , idTabela         );
			sessao.setAttribute( "idTabelaColuna"   , idTabelaColuna   );
			sessao.setAttribute( "id1"              , id1              );
			sessao.setAttribute( "unidadeNegocio"   , unidadeNegocio   );

		} 
		
    else if ( "relatorio".equalsIgnoreCase( sessao.getAttribute("acao").toString()) ) {

			byte[] relatorioRetorno = null;

			OutputStream out = null;

			try {

				// cria uma inst�ncia da classe do relat�rio
				RelatorioOperacao relatorioOperacao = new RelatorioOperacao(
	(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
				
	relatorioOperacao.addParametro( "idUsuarioAcao"    , idUsuarioAcao    );
	relatorioOperacao.addParametro( "idFuncionalidade" , idFuncionalidade );
	relatorioOperacao.addParametro( "idUsuario"        , idUsuario        );
	relatorioOperacao.addParametro( "dataInicial"      , dataInicial      );
	relatorioOperacao.addParametro( "dataFinal"        , dataFinal        );
	relatorioOperacao.addParametro( "horaInicial"      , horaInicial      );
	relatorioOperacao.addParametro( "horaFinal"        , horaFinal        );
	relatorioOperacao.addParametro( "idTabela"         , idTabela         );
	relatorioOperacao.addParametro( "idTabelaColuna"   , idTabelaColuna   );
	relatorioOperacao.addParametro( "id1"              , id1              );
	relatorioOperacao.addParametro( "unidadeNegocio"   , unidadeNegocio   );

				// condicao de ser armazenado a tarefa e ser executado em batch 
				if (false) {
					// fachada.getInstantcia().agendarTarefa(relatorioTransacao);

					// tela de agendamento de tarefa.

				} 
				else {
					relatorioRetorno = (byte[]) relatorioOperacao.executar();
					if ( retorno == null ) {
						httpServletResponse.setContentType("application/pdf");
						out = httpServletResponse.getOutputStream();
						out.write(relatorioRetorno);
						out.flush();
						out.close();
					}
				}

				retorno = null;

			} 
			catch (IOException ex) {
				throw new ActionServletException("erro.sistema");
			} 
			catch (SistemaException ex) {
				throw new ActionServletException("erro.sistema");
			} 
			catch (RelatorioVazioException ex) {
				throw new ActionServletException("atencao.relatorio.vazio");
			}
		
		}

		return retorno;
	}
	
	private HashMap consultarInformacoesExtrasOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada){
		
		HashMap resumo = new HashMap();
		       
		String dadosAdicionais = operacaoEfetuada.getDadosAdicionais();		
		if (dadosAdicionais != null){
			
			StringTokenizer stk = new StringTokenizer(dadosAdicionais,"$");
			while ( stk.hasMoreElements() ) {
				
				String element = (String) stk.nextElement();
				int ind = element.indexOf(":");
				
				if (ind != -1){
					String label = element.substring(0,ind);
					String valor = element.substring(ind+1, element.length());
					resumo.put(label,valor);
				}
				
			}
			
		}
		return resumo;
	}

}
