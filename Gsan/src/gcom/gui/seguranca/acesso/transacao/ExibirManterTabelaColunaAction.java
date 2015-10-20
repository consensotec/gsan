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
package gcom.gui.seguranca.acesso.transacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.FiltroOperacaoTabela;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.HibernateUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Permite exibir uma lista com as resolu��es de diretoria retornadas do
 * FiltrarManterTipoRetornoOrdemServicoReferidaAction ou ir para o
 * ExibirManterTipoRetornoOrdemServicoReferidaAction
 * 
 * @author Thiago Ten�rio
 * @since 31/10/2006
 */
public class ExibirManterTabelaColunaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterTabelaColuna");

		FiltrarTabelaColunaActionForm filtrarForm = (FiltrarTabelaColunaActionForm) actionForm;
		
		String idTabela = filtrarForm.getIdTabela();
		
		String[] operacoes = filtrarForm.getOperacoes();
		
		Vector<Tabela> tabelas = new Vector<Tabela>();
		
		if (operacoes != null && operacoes.length > 0){
			FiltroOperacaoTabela filtro = new FiltroOperacaoTabela();
			for (int i = 0; i < operacoes.length; i++) {
				filtro.adicionarParametro(new ParametroSimples(
						FiltroOperacaoTabela.OPERACAO_ID, operacoes[i]));
				filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacaoTabela.TABELA_COLUNAS);
				Collection colOperTabelas = Fachada.getInstancia().pesquisar(filtro,
						OperacaoTabela.class.getSimpleName());
				
				Iterator iterOperTab = colOperTabelas.iterator();
				while (iterOperTab.hasNext()) {
					OperacaoTabela operTabela = (OperacaoTabela) iterOperTab.next();
					tabelas.add(operTabela.getTabela());										
				}				
			}			
		} else if (idTabela != null && !idTabela.equals("")){
			FiltroTabela filtro = new FiltroTabela();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroTabela.ID, idTabela));
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroTabela.TABELA_COLUNAS);
		
			Collection colTabelas = Fachada.getInstancia().pesquisar(filtro,
					Tabela.class.getSimpleName());
		
			Tabela tabela =  (Tabela) Util.retonarObjetoDeColecao(colTabelas);
			
			if (tabela == null){
				throw new ActionServletException("atencao.tabela.inexistente");
			}
			
			// A partir do nome da tabela, precisa-se buscar os atributos da classe
			// desta tabela que estao definidos para ser registrados
			String nomeClasse = HibernateUtil.getClassName(tabela.getNomeTabela());
			
			String nomesColunaParaPesquisa = "";
			try {
				Class classe = Class.forName(nomeClasse);
				Object instancia = classe.newInstance();
				if (instancia instanceof ObjetoTransacao){
					ObjetoTransacao objTransacao = (ObjetoTransacao) instancia;
					String[] atributosSelecionados = objTransacao.retornarAtributosSelecionadosRegistro();
					for (int i = 0; i < atributosSelecionados.length; i++) {
						String nomeColuna = HibernateUtil.getNameColumn(classe, atributosSelecionados[i]);
						nomesColunaParaPesquisa += "$" + nomeColuna + "$";
					}					
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			HttpSession sessao = httpServletRequest.getSession(false);
			sessao.setAttribute("tabela", tabela);
			sessao.setAttribute("nomesColunasSelecionadas", nomesColunaParaPesquisa);	
			
			tabelas.add(tabela);

			// consultar operacao_tabela das tabelas selecionadas			
			FiltroOperacaoTabela filtroOperTab = new FiltroOperacaoTabela();
			filtroOperTab.adicionarParametro(new ParametroSimples(
					FiltroOperacaoTabela.TABELA_ID, idTabela));
			filtroOperTab.adicionarCaminhoParaCarregamentoEntidade(
					FiltroOperacaoTabela.OPERACAO);
			filtroOperTab.setCampoOrderBy(new String[]{FiltroOperacaoTabela.OPERACAO_ARGUMENTO_ID,
				FiltroOperacaoTabela.OPERACAO_DESCRICAO});

			Collection colOperTabelas = Fachada.getInstancia().pesquisar(filtroOperTab,
					OperacaoTabela.class.getSimpleName());
			
			httpServletRequest.setAttribute("colecaoOperTabela", colOperTabelas);	
			
		}		

		httpServletRequest.setAttribute("colecaoTabela", tabelas);

		return retorno;

	}

}
