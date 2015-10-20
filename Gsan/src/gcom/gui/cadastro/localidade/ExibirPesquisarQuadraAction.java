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

import java.util.Collection;
import java.util.Map;

import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de Quadra
 * 
 * @author Fl�vio
 */
public class ExibirPesquisarQuadraAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("pesquisarQuadra");

		Fachada fachada = Fachada.getInstancia();
        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
        
		// Verifica se o pesquisar quadra foi chamado a partir do inserir quadra
		// e em caso afirmativo recebe o par�metro e manda-o pela sess�o para
		// ser verificado no quadra_resultado_pesquisa e depois retirado da
		// sess�o no ExibirFiltrarQuadraAction
		if (httpServletRequest.getParameter("consulta") != null) {
			String consulta = httpServletRequest.getParameter("consulta");
			sessao.setAttribute("consulta", consulta);
		}        
		
        String tipo = (String) httpServletRequest.getParameter("tipo");
        String idSetorComercial = null;
        String codigoSetorComercial = null;
        String idLocalidade = null;
        
        
        if(httpServletRequest
                .getParameter("idSetorComercial") != null 
                && !httpServletRequest
                .getParameter("idSetorComercial").trim().equalsIgnoreCase("")){
        	
        	idSetorComercial = (String) httpServletRequest.getParameter("idSetorComercial");
        	sessao.setAttribute("idSetorComercial", idSetorComercial);
        }else{
        	idSetorComercial = (String) sessao.getAttribute("idSetorComercial");
        }
        
        if(httpServletRequest
                .getParameter("idLocalidade") != null 
                && !httpServletRequest
                .getParameter("idLocalidade").trim().equalsIgnoreCase("")){
        	
        	idLocalidade = (String) httpServletRequest.getParameter("idLocalidade");
        	sessao.setAttribute("idLocalidade", idLocalidade);
        }else{
        	idLocalidade = (String) sessao.getAttribute("idLocalidade");
        }
        
        if(httpServletRequest
                .getParameter("codigoSetorComercial") != null 
                && !httpServletRequest
                .getParameter("codigoSetorComercial").trim().equalsIgnoreCase("")){
        	
        	codigoSetorComercial = (String) httpServletRequest
             .getParameter("codigoSetorComercial");
        	sessao.setAttribute("codigoSetorComercial", codigoSetorComercial);
        }else{
        	codigoSetorComercial = (String) sessao.getAttribute("codigoSetorComercial");
        }
        
        if(tipo != null && !tipo.trim().equalsIgnoreCase("")){
        	sessao.setAttribute("tipoPesquisa", tipo);
        }else{
        	 pesquisarActionForm.set("tipoPesquisa", ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
        }
        
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que ser�o retornados pelo Hibernate

		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
        
		filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
        if (idSetorComercial != null
				&& !idSetorComercial.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL, new Integer(
							idSetorComercial)));
		}
        
        if (idLocalidade != null
				&& !idLocalidade.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(
							idLocalidade)));
		}
        
		if (codigoSetorComercial != null
				&& !codigoSetorComercial.trim().equalsIgnoreCase("")) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(
							codigoSetorComercial)));
		}

		if (sessao.getAttribute("indicadorUsoTodos") == null) {
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}
		
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("quadraPerfil");
		filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
                
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());

		if (quadras != null && !quadras.isEmpty()) {
			// Aciona o controle de pagina��o para que sejam pesquisados apenas
			// os registros que aparecem na p�gina
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroQuadra, Quadra.class.getName());
			quadras = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			sessao.setAttribute("quadras", quadras);
		} else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "quadra");
		}

		// Passa parametros para distinguir o tipo de retorno
		sessao.setAttribute("tipoPesquisa", sessao
						.getAttribute("tipoPesquisa"));
        
		String idRota = (String) pesquisarActionForm.get("idRota");
		
        if (idRota != null && !idRota.trim().equals("")) {
        	FiltroRota filtroRota = new FiltroRota();
        	filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
        	
        	Collection colecaoRotas = fachada.pesquisar(filtroRota, Rota.class.getName());
        	
        	if (colecaoRotas != null && !colecaoRotas.isEmpty()) {
        		Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRotas);
        		
        		pesquisarActionForm.set("codigoRota", rota.getCodigo().toString());
        	}
        }
        
        //envia uma flag que ser� verificado no quadra_resultado_pesquisa.jsp
        //para saber se ir� usar o enviar dados ou o enviar dados parametros
        if(httpServletRequest.getParameter("caminhoRetornoTelaPesquisa") != null){
        	sessao.setAttribute("caminhoRetornoTelaPesquisaQuadra", httpServletRequest
                .getParameter("caminhoRetornoTelaPesquisa"));
        }
        
        if( httpServletRequest.getParameter("indicadorUsoTodos") == null ){
        	sessao.removeAttribute("indicadorUsoTodos");
	    }
        else
        {
        	sessao.setAttribute("indicadorUsoTodos",
				httpServletRequest.getParameter("indicadorUsoTodos"));
        }

        return retorno;
    }

}
