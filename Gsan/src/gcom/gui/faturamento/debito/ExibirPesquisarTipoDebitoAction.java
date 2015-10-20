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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de tipos d�bitos 
 * 
 * @author Pedro Alexandre
 * @created 09 de mar�o de 2006
 */
public class ExibirPesquisarTipoDebitoAction extends GcomAction {
	
	/**
	 * consiste em pesquisar os tipos de d�bitos cadastrados no sistema
	 *
	 * [UC0303] Pesquisar Tipo de D�bito
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Administrador
	 * @date 09/03/2006
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//seta o mapeamento de retorno para a tela de pesquisar tipos de d�bitos
		ActionForward retorno = actionMapping.findForward("pesquisarTipoDebito");
		
		//cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		  
		//se essa variavel tiver algum valor, isso indica que apenas do Tipo de Financimento SERVI�O deve ser carregado na colecao
		String tipoFinanciamentoServico = "";
		
		if (httpServletRequest.getParameter("tipoFinanciamentoServico") != null &&
				!httpServletRequest.getParameter("tipoFinanciamentoServico").equals("")){
			tipoFinanciamentoServico = httpServletRequest.getParameter("tipoFinanciamentoServico");
			sessao.setAttribute("tipoFinanciamentoServico",httpServletRequest.getParameter("tipoFinanciamentoServico"));
		}else if(sessao.getAttribute("tipoFinanciamentoServico")!= null &&
				!sessao.getAttribute("tipoFinanciamentoServico").equals("")){
			tipoFinanciamentoServico = (String)sessao.getAttribute("tipoFinanciamentoServico");
		}
		
		
		PesquisarTipoDebitoActionForm pesquisarTipoDebitoActionForm = (PesquisarTipoDebitoActionForm) actionForm;
        if ((httpServletRequest.getParameter("limparForm") != null
				&& httpServletRequest.getParameter("limparForm").equalsIgnoreCase("1")) 
				|| (httpServletRequest.getParameter("objetoConsulta") == null
						&& httpServletRequest.getParameter("tipoConsulta") == null
						&& httpServletRequest.getParameter("voltarPesquisa") == null)){
        	
        	pesquisarTipoDebitoActionForm.setIdTipoDebito("");
        	pesquisarTipoDebitoActionForm.setDescricao("");
        	pesquisarTipoDebitoActionForm.setIdTipoFinanciamento(null);
        	pesquisarTipoDebitoActionForm.setIdItemLancamentoContabil(null);
        	pesquisarTipoDebitoActionForm.setIntervaloValorLimiteInicial("");
        	pesquisarTipoDebitoActionForm.setIntervaloValorLimiteFinal("");
        	pesquisarTipoDebitoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        	
        }
        
		//cria o filtro de lan�amentos de item cont�bil para pesquisa  
		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();

		//seta a ordena��o do resultado da pesquisa de lan�amentos de item cont�bil pela descri��o
		filtroLancamentoItemContabil.setCampoOrderBy(FiltroLancamentoItemContabil.DESCRICAO);
		
		//pesquisa a cole��o de lan�amentos de item cont�bil no sistema
		Collection colecaoLancamentoItemContabil = fachada.pesquisar(filtroLancamentoItemContabil, LancamentoItemContabil.class.getName());
		
		//se nenhum lan�amento de item cont�bil cadastrado no sistema
        if(colecaoLancamentoItemContabil == null || colecaoLancamentoItemContabil.isEmpty()){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Lan�amento de Item Cont�bil");
        	
        }else{
        	//se existir lan�amento de item cont�bil cadastrado(s) no sistema, manda 
        	//a cole��o pesquisada no request para a p�gina de pesquisar item de lan�amento cont�bil
        	httpServletRequest.setAttribute("colecaoLancamentoItemContabil",colecaoLancamentoItemContabil);
        }
        
        //cria o filtro de tipo de financiamento para pesquisa  
        FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();

		//seta para pesquisar apenas o tipo de financiamento SERVI�O
		if(tipoFinanciamentoServico != null && !tipoFinanciamentoServico.equals("")){
			filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID,FinanciamentoTipo.SERVICO_NORMAL));
		}
        //seta a ordena��o do resultado da pesquisa de tipo de financiamento pela descri��o  
        filtroFinanciamentoTipo.setCampoOrderBy(FiltroFinanciamentoTipo.DESCRICAO);
        
        //pesquisa a cole��o de tipo(s) de financiamento no sistema
        Collection colecaoFinanciamentoTipo = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
        
        //se nenhum tipo de financiamento cadastrado no sistema
        if(colecaoFinanciamentoTipo == null || colecaoFinanciamentoTipo.isEmpty()){
        	//levanta a exce��o para a pr�xima camada
        	throw new ActionServletException("atencao.naocadastrado",null, "Tipo de Financiamento");
        	
        }else{
        	//se existir tipo(s) de financiamento cadastrado(s) no sistema, manda 
        	//a cole��o pesquisada no request para a p�gina de pesquisar tipo de financiamento
        	httpServletRequest.setAttribute("colecaoFinanciamentoTipo",colecaoFinanciamentoTipo);
        }
        
        if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoDebito") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoDebito",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaTipoDebito"));

		}
        

        
        //retorna o mapeamento contido na vari�vel "retorno"
		return retorno;
	}
}
