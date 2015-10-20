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

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o processamento da p�gina de pesquisa de Setor Comercial
 * 
 * @author Fl�vio
 */
public class PesquisarSetorComercialAction extends GcomAction {

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
                .findForward("listaSetorComercial");

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        String codigoSetorComercial = (String) pesquisarActionForm
                .get("codigoSetorComercial");
        String descricao = (String) pesquisarActionForm.get("descricao");
        String descricaoMunicipio = (String) pesquisarActionForm
                .get("descricaoMunicipio");
        String tipoPesquisaDescricao = (String) pesquisarActionForm.get("tipoPesquisaDescricao");
        String tipoPesquisaMunicipio = (String) pesquisarActionForm.get("tipoPesquisaMunicipio");
        String indicadorSetorAlternativo = (String) pesquisarActionForm.get("indicadorSetorAlternativo");
        
        boolean peloMenosUmParametroInformado = false;

        String idLocalidade = null;
        String tipoPesquisa = null;
        
        FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
        
        //Objetos que ser�o retornados pelo Hibernate
        filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");
        filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
        
        filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL);
        
        Fachada fachada = Fachada.getInstancia();

        if (sessao.getAttribute("idLocalidade") != null) {
            idLocalidade = sessao.getAttribute("idLocalidade").toString();
        }
        
        if (sessao.getAttribute("indicadorSetorAlternativo") != null) {
        	indicadorSetorAlternativo = sessao.getAttribute("indicadorSetorAlternativo").toString();
        }

        if (sessao.getAttribute("tipoPesquisa") != null) {
            tipoPesquisa = sessao.getAttribute("tipoPesquisa").toString();
        }
        if( sessao.getAttribute("indicadorUsoTodos") == null )
        {
    	    filtroSetorComercial.adicionarParametro(new ParametroSimples(
                FiltroSetorComercial.INDICADORUSO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        if (codigoSetorComercial != null
                && !codigoSetorComercial.trim().equalsIgnoreCase("")) {
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
                            codigoSetorComercial)));
            peloMenosUmParametroInformado = true;
        }
        if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            if (tipoPesquisaDescricao != null
					&& tipoPesquisaDescricao
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
            	filtroSetorComercial.adicionarParametro(new ComparacaoTextoCompleto(
                        FiltroSetorComercial.DESCRICAO, descricao));
			} else {
				filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
	                    FiltroSetorComercial.DESCRICAO, descricao));
			}
            peloMenosUmParametroInformado = true;
        }
        if (descricaoMunicipio != null
                && !descricaoMunicipio.trim().equalsIgnoreCase("")) {
            if (tipoPesquisaMunicipio != null
					&& tipoPesquisaMunicipio
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {
            	filtroSetorComercial.adicionarParametro(new ComparacaoTextoCompleto(
                        FiltroSetorComercial.DESCRICAO_MUNICIPIO,
                        descricaoMunicipio));
             } else {
				filtroSetorComercial.adicionarParametro(new ComparacaoTexto(
	                    FiltroSetorComercial.DESCRICAO_MUNICIPIO,
	                    descricaoMunicipio));
			}
            peloMenosUmParametroInformado = true;
        }
        if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.ID_LOCALIDADE, new Integer(
                            idLocalidade)));
            peloMenosUmParametroInformado = true;
        }
        
        if ( indicadorSetorAlternativo != null && !indicadorSetorAlternativo.trim().equalsIgnoreCase("")) {
            filtroSetorComercial.adicionarParametro(new ParametroSimples(
                    FiltroSetorComercial.INDICADOR_SETOR_ALTERNATIVO, new Integer(
                    		indicadorSetorAlternativo)));
            peloMenosUmParametroInformado = true;
        }

        if (!peloMenosUmParametroInformado) {
            //throw new ActionServletException(
              //      "atencao.filtro.nenhum_parametro_informado");
        }
        

        Collection setorComerciais = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        if (setorComerciais != null && !setorComerciais.isEmpty()) {
//       	 Aciona o controle de pagina��o para que sejam pesquisados apenas
			// os registros que aparecem na p�gina
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroSetorComercial, SetorComercial.class.getName());
			setorComerciais = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
                sessao.setAttribute("setorComerciais", setorComerciais);

        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "setor comercial");
        }

        //Passa o parametro para o tipo de resultado e o tira da sess�o
        httpServletRequest.setAttribute("tipoPesquisa", tipoPesquisa);
        
        return retorno;
    }

}
