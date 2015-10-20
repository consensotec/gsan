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
* Thiago Vieira
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativacaoComando;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.bean.DadosInclusoesComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class ExibirConsultarDadosInclusoesAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//   	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirConsultarDadosInclusoes");
		HttpSession sessao = httpServletRequest.getSession(false);
		InclusaoDadosComandoNegativacaoPopupActionForm form = (InclusaoDadosComandoNegativacaoPopupActionForm) actionForm; 
		
		Fachada fachada = Fachada.getInstancia();
		
		if ((httpServletRequest.getParameter("idSelecionado") != null && !httpServletRequest.getParameter("idSelecionado").equals("")) ||
				(sessao.getAttribute("idSelecionado") != null && !sessao.getAttribute("idSelecionado").equals(""))){
			String idSelecionado = "";
			if (httpServletRequest.getParameter("idSelecionado") != null && !httpServletRequest.getParameter("idSelecionado").equals("")){
				idSelecionado = httpServletRequest.getParameter("idSelecionado");
				sessao.setAttribute("idSelecionado", httpServletRequest.getParameter("idSelecionado"));
			} else {
				idSelecionado = (String) sessao.getAttribute("idSelecionado");
			}
			
			FiltroNegativacaoComando filtroNegativacaoComando = new FiltroNegativacaoComando();
			filtroNegativacaoComando.adicionarParametro(new ParametroSimples(FiltroNegativacaoComando.ID, new Integer(idSelecionado)));
			filtroNegativacaoComando.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			
			Collection collNegativacaoComando = Fachada.getInstancia().pesquisar(filtroNegativacaoComando, NegativacaoComando.class.getName());
			NegativacaoComando negativacaoComando = (NegativacaoComando)collNegativacaoComando.iterator().next();
			
			if (negativacaoComando.getNegativador().getCliente().getNome() == null){
				form.setNegativador("");
			} else {
				form.setNegativador(negativacaoComando.getNegativador().getCliente().getNome());
			}
			
			if (negativacaoComando.getQuantidadeInclusoes() == null){
				form.setQtdInclusoes("");
			} else {
				form.setQtdInclusoes(negativacaoComando.getQuantidadeInclusoes().toString());
			}
			
			if (negativacaoComando.getValorDebito() == null){
				form.setValorTotalDebito("");
			} else {
				form.setValorTotalDebito(Util.formatarMoedaReal(negativacaoComando.getValorDebito()));
			}
			
			if (negativacaoComando.getQuantidadeItensIncluidos() == null){
				form.setQtdItensIncluidos("");
			} else {
				form.setQtdItensIncluidos(negativacaoComando.getQuantidadeItensIncluidos().toString());
			}
			
			FiltroNegativadorMovimentoReg filtroNegMovReg = new FiltroNegativadorMovimentoReg();
			filtroNegMovReg.adicionarParametro(new ParametroNaoNulo("imovel.id"));
			filtroNegMovReg.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.NEGATIVADOR_MOVIMENTO_NEGATIVACAO_COMANDO_ID, negativacaoComando.getId()));
			filtroNegMovReg.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroNegMovReg.adicionarCaminhoParaCarregamentoEntidade("cobrancaDebitoSituacao");
			filtroNegMovReg.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
//			Map resultado = controlarPaginacao(httpServletRequest, retorno,
//					filtroNegMovReg, NegativadorMovimentoReg.class.getName());
//			
//			
//			Collection collNegativadorMovimentoReg = (Collection) resultado.get("colecaoRetorno");
//			retorno = (ActionForward) resultado.get("destinoActionForward");
			
//			Collection collNegativadorMovimentoReg = fachada.pesquisar(filtroNegMovReg, NegativadorMovimentoReg.class.getName());
//					
//			
//			sessao.setAttribute("collNegativadorMovimentoReg", collNegativadorMovimentoReg);
			
			
			
			//PAGINACAO DA TABELA			
			
			Integer idComandoNegativacao = new Integer(idSelecionado);
		       
        	Integer totalRegistrosSegundaPaginacao = 0;
        	if(sessao.getAttribute("totalRegistrosSegundaPaginacao") == null ){
        		
        		totalRegistrosSegundaPaginacao = (Integer)fachada.pesquisarDadosInclusoesComandoNegativacao(idComandoNegativacao);

        		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
        		 
        		
        	}else{
        		totalRegistrosSegundaPaginacao = (Integer)sessao.getAttribute("totalRegistrosSegundaPaginacao");
        	}
			
			// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistrosSegundaPaginacao,false);
	
			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
			// da pesquisa que est� no request
//			Collection collectionDadosInclusoesComandoNegativacao = fachada.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao,
//					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
			Collection collectionDadosInclusoesComandoNegativacao = fachada.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao,
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			//[FS0006 NENHUM REGISTRO ENCONTRADO]
			if (collectionDadosInclusoesComandoNegativacao == null || collectionDadosInclusoesComandoNegativacao.isEmpty()) {
				throw new ActionServletException("atencao.negativador_movimento_registro_nao_existe", null, idComandoNegativacao+"");
			}
			
			// SETANDO OS DADOS GERAIS DA TELA
			
			DadosInclusoesComandoNegativacaoHelper dadosInclusoesComandoNegativacaoHelper = (DadosInclusoesComandoNegativacaoHelper)
			collectionDadosInclusoesComandoNegativacao.iterator().next();
			
			if (dadosInclusoesComandoNegativacaoHelper.getNomeCliente() != null && 
					!dadosInclusoesComandoNegativacaoHelper.getNomeCliente().equals("")){
				
				form.setNegativador(dadosInclusoesComandoNegativacaoHelper.getNomeCliente());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes()!= null &&
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().equals("")){
				
				form.setQtdInclusoes(dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().toString());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito().equals("")){
				
				form.setValorTotalDebito(Util.formatarMoedaReal(dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito()));
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().equals("")){
				
				form.setQtdItensIncluidos(dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().toString());
			}
			
			
			sessao.setAttribute("collectionDadosInclusoesComandoNegativacao", collectionDadosInclusoesComandoNegativacao);			
			sessao.setAttribute("totalRegistrosSegundaPaginacao", totalRegistrosSegundaPaginacao);
			sessao.setAttribute("numeroPaginasPesquisaSegundaPaginacao",httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
		}
		return retorno;
        
    }
}