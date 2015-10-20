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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


/**
 * Action que define o pr�-processamento da p�gina de pesquisa de Localidade
 * 
 * @author Administrador
 * @created 27 de Maio de 2004
 */

public class ExibirPesquisarLocalidadeAction extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
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

        ActionForward retorno = actionMapping
                .findForward("pesquisarLocalidade");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Verifica se o pesquisar localidade foi chamado a partir do inserir localidade
		// e em caso afirmativo recebe o par�metro e manda-o pela sess�o para
		// ser verificado no localidade_resultado_pesquisa e depois retirado da
		// sess�o no ExibirFiltrarLocalidadeAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		}else{
			sessao.removeAttribute("consulta");
		}
	
		if (sessao.getAttribute("caminhoRetornoTelaPesquisaLocalidade") != null){
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLocalidade");
		}
		
        if(httpServletRequest.getParameter("objetoConsulta") == null){
	        pesquisarActionForm.set("idLocalidade", "");
	        pesquisarActionForm.set("descricaoLocalidade", "");
	        pesquisarActionForm.set("idGerenciaRegional", "");
	        pesquisarActionForm.set("tipoPesquisa", ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        }

        String tipo = httpServletRequest.getParameter("tipo");
        
        String idElo = httpServletRequest.getParameter("idElo");

        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

        if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
        	sessao.removeAttribute("indicadorUsoTodos");
	        filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
	                FiltroGerenciaRegional.INDICADOR_USO,
	                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        else
        {
        	sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
        }
        filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
        
        Collection gerencias = fachada.pesquisar(filtroGerenciaRegional,
                GerenciaRegional.class.getName());

        if (gerencias != null && !gerencias.isEmpty()) {
        	GerenciaRegional gerenciaRegional = null;
            Iterator iterator = gerencias.iterator();
            
            while (iterator.hasNext()) {
            
            	gerenciaRegional = (GerenciaRegional) iterator.next();
            
            	String descGerenciaRegional = gerenciaRegional.getNomeAbreviado() + "-" + gerenciaRegional.getNome();
            
            	gerenciaRegional.setNome(descGerenciaRegional);
            
            }
            
            httpServletRequest.setAttribute("gerenciasRegionais", gerencias);
        } else {
            throw new ActionServletException("atencao.naocadastrado", null,
                    "ger�ncia regional");
        }

        sessao.setAttribute("tipoPesquisa", tipo);
        sessao.setAttribute("idElo",idElo);
        if(httpServletRequest.getParameter("menu")!= null && !httpServletRequest.getParameter("menu").equals("")){
        	sessao.removeAttribute("idElo");
        }

        //envia uma flag que ser� verificado no
        // localidade_resultado_pesquisa.jsp
        //para saber se ir� usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaLocalidade", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisa"));
        }

        return retorno;
    }

}
