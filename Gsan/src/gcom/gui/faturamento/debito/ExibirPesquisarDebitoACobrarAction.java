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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pr�-processamento da p�gina de pesquisar d�bitos a cobrar do im�vel 
 *
 * @author Pedro Alexandre
 * @date 13/03/2006
 */
public class ExibirPesquisarDebitoACobrarAction extends GcomAction {
	/**
	 * Pesquisa os d�bitos a cobrar existentes para o im�vel 
	 *
	 * [UC0271] Pesquisar D�bito a Cobrar
	 *
	 * <Breve descri��o sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descri��o sobre o fluxo secund�rio>
	 *
	 * <Identificador e nome do fluxo secund�rio> 
	 *
	 * @author Pedro Alexandre
	 * @date 13/03/2006
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

		//Seta o mapeamento de retorno para a tela de pesquisar d�bitos a cobrar do im�vel
		ActionForward retorno = actionMapping.findForward("pesquisarDebitoACobrar");
		
		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		//Recupera o c�digo do im�vel do request
		String idImovel = (String)httpServletRequest.getParameter("idImovel");
		
		//PesquisarDebitoACobrarActionForm pesquisarDebitoACobrarActionForm = (PesquisarDebitoACobrarActionForm)actionForm;
		
		
		
		
		/*
		  if (httpServletRequest.getParameter("limparForm") != null
	                && !httpServletRequest.getParameter("limparForm").equalsIgnoreCase("")) {
			  
			  pesquisarDebitoACobrarActionForm.setDataGeracaoDebitoFinal("");
			  pesquisarDebitoACobrarActionForm.setDataGeracaoDebitoInicial("");
			  pesquisarDebitoACobrarActionForm.setReferenciaDebitoFinal("");
			  pesquisarDebitoACobrarActionForm.setReferenciaDebitoInicial("");
			  
			  pesquisarDebitoACobrarActionForm.setIdTipoDebitoSelecionados(null);			  
			  pesquisarDebitoACobrarActionForm.setIdTipoDebito(null);
			  pesquisarDebitoACobrarActionForm.setIdSituacaoDebitoACobrar(null);
			  
			 // pesquisarDebitoACobrarActionForm.reset(actionMapping,httpServletRequest);
			  
		  }
		*/

	
		
		
		//Caso o c�digo do im�vel tenha sido informado pelo caso de uso que chamou a tela de pesquisar d�bito a cobrar,
		//Caso contr�rio levanta a exce��o para o usu�rio indicando que o im�vel n�o foi informado
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Pesquisa se o im�vel informado esta cadastrado no sistema,
			//Caso contr�rio, levanta a exce��o para o usu�rio indicando que o im�vel n�o existe
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//Caso o im�vel informado n�o tenha sido cadastrado no sistema, levantaa exce��o para o usu�rio indicando que o
        	//im�vel informado n�o est� cadastrado no sistema
        	//Caso contr�rio manda o c�digo do im�vel no request
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Im�vel");
        	}else{
        		httpServletRequest.setAttribute("idImovel",idImovel);
        	}
        	
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Im�vel");
		}
		
		//Cria o filtro para pesquisar as situa��es de conta no sistema
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		filtroDebitoCreditoSituacao.setCampoOrderBy(FiltroDebitoCreditoSituacao.DESCRICAO);
		
		//Pesquisa todas as situa��es de d�bito a cobrar cadastradas no sistema
		Collection colecaoSituacaoDebitoACobrar = fachada.pesquisar(filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());
		
		//[FS0005] Caso n�o exista nenhuma situa��o de d�bito a cobrar no sistema, levanta a exce��o para o usu�rio indicando que 
		//nenhuma situa��o de d�bito a cobrar est� cadastrada no sistema.
		//Caso contr�rio, manda as situa��es de d�bito a cobrar cadastradas no request 
        if(colecaoSituacaoDebitoACobrar == null || colecaoSituacaoDebitoACobrar.isEmpty()){
        	throw new ActionServletException("atencao.naocadastrado",null, "Situa��o de D�bito a Cobrar");
        }else{
        	httpServletRequest.setAttribute("colecaoSituacaoDebitoACobrar",colecaoSituacaoDebitoACobrar);
        }
        
        //Cria o filtro para pesquisar os tipos de d�bitos cadastrados no sistema
        //e seta a ordena��o do resultado pela descri��o do tipo de d�bito
        FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.setConsultaSemLimites(true);
        filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
        
        //Pesquisa todos os tipos de d�bitos cadastrados no sistema
        Collection colecaoTipoDebito = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        
        //[FS0005] Caso n�o exista nenhum tipo de d�bito de d�bito a cobrar no sistema, levanta a exce��o para o usu�rio indicando que 
		//nenhum tipo de d�bito de d�bito a cobrar est� cadastrado no sistema.
		//Caso contr�rio, manda os tipos de d�bito de d�bito a cobrar cadastrados no request 
        if(colecaoTipoDebito == null || colecaoTipoDebito.isEmpty()){
        	throw new ActionServletException("atencao.naocadastrado",null, "Tipo de D�bito");
        }else{
        	httpServletRequest.setAttribute("colecaoTipoDebito",colecaoTipoDebito);
        }
        
        if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaDebitoACobrar") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaDebitoACobrar",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaDebitoACobrar"));
		}
        
        
        //Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
