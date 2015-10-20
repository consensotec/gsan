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
package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.seguranca.transacao.Tabela;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarTabelaAction extends GcomAction {
	/**
	 * Pesquisar Tabela
	 * 
	 * @author Vinicius Medeiros
	 * @date 12/05/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	   public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        ActionForward retorno = actionMapping.findForward("pesquisarTabela");
	        
	        //Obt�m a inst�ncia da Fachada
	        Fachada fachada = Fachada.getInstancia();
	        
	        //HttpSession sessao = httpServletRequest.getSession(false);
	        
			// Obt�m o action form
	        PesquisarTabelaActionForm pesquisarTabelaActionForm = (PesquisarTabelaActionForm) actionForm;

			// Recupera os par�metros do form
	        String id = (String) pesquisarTabelaActionForm.getId();
	        String descricao = (String) pesquisarTabelaActionForm.getDescricao();
	        String nomeTabela = (String) pesquisarTabelaActionForm.getNomeTabela();	  
	        String tipoPesquisa = (String) pesquisarTabelaActionForm.getTipoPesquisa();

	        boolean peloMenosUmParametroInformado = false;

	         FiltroTabela filtroTabela = new FiltroTabela(FiltroTabela.DESCRICAO);	        

	        if (id != null && !id.trim().equalsIgnoreCase("")) {
	        	filtroTabela.adicionarParametro(new ParametroSimples(
	        			FiltroTabela.ID, new Integer(id)));
                peloMenosUmParametroInformado = true;
	        }
	        
	        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true; 
    			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
    				filtroTabela.adicionarParametro(new ComparacaoTextoCompleto(
    						FiltroTabela.DESCRICAO, descricao));
    			} else {
    				filtroTabela.adicionarParametro(new ComparacaoTexto(
    						FiltroTabela.DESCRICAO, descricao));
    			}
	        }
	        
	        if (nomeTabela != null && !nomeTabela.trim().equalsIgnoreCase("")) {
	            peloMenosUmParametroInformado = true;
	            filtroTabela.adicionarParametro(new ComparacaoTextoCompleto(
	            		FiltroTabela.NOME, nomeTabela));
	        }	      
			// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
	        if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
	        }
	        
			// Faz a pesquisa baseada no filtro
	        Collection tabelas = fachada.pesquisar(filtroTabela, Tabela.class.getName());
	        
			// Verificar se a pesquisa de atividade n�o est� vazia
	        if (tabelas != null && !tabelas.isEmpty()) {
                 // Aciona o controle de pagina��o para que sejam pesquisados apenas
				// os registros que aparecem na p�gina
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroTabela, Tabela.class.getName());
				tabelas = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
				//sessao.setAttribute("atividades", atividades);
				// Manda a cole��o das Atividade pesquisadas para o request
				httpServletRequest.getSession(false).setAttribute("tabelas",
						tabelas);
				
	        } else {
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "tabela");
	        }
	        
	        return retorno;
	    }

	}
