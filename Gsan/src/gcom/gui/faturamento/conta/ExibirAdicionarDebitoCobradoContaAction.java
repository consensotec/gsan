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
package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FinanciamentoTipo;

import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirAdicionarDebitoCobradoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarDebitoCobradoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        //Inst�ncia do formul�rio que est� sendo utilizado
        AdicionarDebitoCobradoContaActionForm adicionarDebitoCobradoContaActionForm = 
        (AdicionarDebitoCobradoContaActionForm) actionForm;
        
        /*
         * Costante que informa o ano limite para o campo anoMesReferencia da conta
         */
        httpServletRequest.setAttribute("anoLimite", ConstantesSistema.ANO_LIMITE);
        
        //Recebimento da matricula do im�vel selecionado
        String idImovel = (String) httpServletRequest.getParameter("imovel");
        
        if (idImovel == null || idImovel.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.adicionar_debito_imovel_obrigatorio");
        }
        else{
        	//O id do im�vel ser� utilizado no action de inser��o dos d�bitos cobrados.
        	adicionarDebitoCobradoContaActionForm.setImovelID(idImovel);
        }
        
        //Carregar tipo do d�bito
        if (sessao.getAttribute("colecaoAdicionarDebitoTipo") == null){
        
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo(FiltroDebitoTipo.DESCRICAO_ABREVIADA);
        	filtroDebitoTipo.setConsultaSemLimites(true);
        	
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.FINANCIAMENTO_TIPO, FinanciamentoTipo.SERVICO_NORMAL));
        	
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
        	Collection colecaoAdicionarDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
        		DebitoTipo.class.getName());
        
        	if (colecaoAdicionarDebitoTipo == null || colecaoAdicionarDebitoTipo.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "DEBITO_TIPO");
        	}
        
        	// Disponibiliza a cole��o pela sess�o
        	sessao.setAttribute("colecaoAdicionarDebitoTipo", colecaoAdicionarDebitoTipo);
        
        }
        
        if (adicionarDebitoCobradoContaActionForm.getDebitoTipoID() != null && !adicionarDebitoCobradoContaActionForm.getDebitoTipoID().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        	filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, adicionarDebitoCobradoContaActionForm.getDebitoTipoID()));
        	
        	Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        	
        	DebitoTipo debitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
        	
        	adicionarDebitoCobradoContaActionForm.setValorDebito(Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
        } else {
        	adicionarDebitoCobradoContaActionForm.setValorDebito("");
        }
        
        boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						this.getUsuarioLogado(httpServletRequest));

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);
        
		if (adicionarDebitoCobradoContaActionForm.getValorDebito() == null
				|| adicionarDebitoCobradoContaActionForm.getValorDebito()
						.equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
		
        return retorno;
    }

}
