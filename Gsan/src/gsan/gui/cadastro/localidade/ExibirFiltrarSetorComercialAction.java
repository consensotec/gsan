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
package gsan.gui.cadastro.localidade;

import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarSetorComercialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarSetorComercial");

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obt�m a sess�o
        HttpSession sessao = httpServletRequest.getSession(false);
        
        if (sessao.getAttribute("consulta") != null) {
			sessao.removeAttribute("consulta");
		}

        PesquisarFiltrarSetorComercialActionForm pesquisarFiltrarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;

        String objetoConsulta = (String) httpServletRequest
                .getParameter("objetoConsulta");
        
        httpServletRequest.setAttribute("nomeCampo","localidadeID");
        
        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")) {

            Collection colecaoPesquisa = null;
            String localidadeID = null;

            switch (Integer.parseInt(objetoConsulta)) {
            //Localidade
            case 1:
                FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

                //Recebe o valor do campo localidadeID do formul�rio.
                localidadeID = pesquisarFiltrarSetorComercialActionForm
                        .getLocalidadeID();

                if(localidadeID != null && !localidadeID.equalsIgnoreCase(""))
                {
	                filtroLocalidade.adicionarParametro(new ParametroSimples(
	                        FiltroLocalidade.ID, localidadeID));
	
	                filtroLocalidade.adicionarParametro(new ParametroSimples(
	                        FiltroLocalidade.INDICADORUSO,
	                        ConstantesSistema.INDICADOR_USO_ATIVO));
	
	                //Retorna localidade
	                colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
	                        Localidade.class.getName());
	
	                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                    //Localidade nao encontrada
	                    //Limpa os campos localidadeID e localidadeNome do
	                    // formul�rio
	                    httpServletRequest.setAttribute("corLocalidade",
	                            "exception");
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setLocalidadeID("");
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setLocalidadeNome("Localidade Inexistente.");
	                    httpServletRequest.setAttribute("nomeCampo",
	                    "localidadeID");
	                } else {
	                    httpServletRequest.setAttribute("corLocalidade", "valor");
	                    Localidade objetoLocalidade = (Localidade) Util
	                            .retonarObjetoDeColecao(colecaoPesquisa);
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setLocalidadeID(String.valueOf(objetoLocalidade
	                                    .getId()));
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setLocalidadeNome(objetoLocalidade.getDescricao());
	                    httpServletRequest.setAttribute("nomeCampo",
	                    "setorComercialCD");
	                }
                }
                break;
            //Municipio
            case 3:
                FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

                //Recebe o valor do campo municipioID do formul�rio.
                String municipioID = pesquisarFiltrarSetorComercialActionForm
                        .getMunicipioID();

                if(municipioID != null && !municipioID.equalsIgnoreCase(""))
                {
                	filtroMunicipio.adicionarParametro(new ParametroSimples(
                        FiltroMunicipio.ID, municipioID));

	                filtroMunicipio.adicionarParametro(new ParametroSimples(
	                        FiltroMunicipio.INDICADOR_USO,
	                        ConstantesSistema.INDICADOR_USO_ATIVO));
	
	                //Retorna municipio
	                colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
	                        Municipio.class.getName());
	
	                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                    //Municipio nao encontrado
	                    //Limpa os campos municipioID e municipioNome do formul�rio
	                    httpServletRequest
	                            .setAttribute("corMunicipio", "exception");
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setMunicipioID("");
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setMunicipioNome("Munic�pio Inexistente.");
	                    httpServletRequest.setAttribute("nomeCampo",
	                    "municipioID");
	                } else {
	                    httpServletRequest.setAttribute("corMunicipio", "valor");
	                    Municipio objetoMunicipio = (Municipio) Util
	                            .retonarObjetoDeColecao(colecaoPesquisa);
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setMunicipioID(String.valueOf(objetoMunicipio
	                                    .getId()));
	                    pesquisarFiltrarSetorComercialActionForm
	                            .setMunicipioNome(objetoMunicipio.getNome());
	                    httpServletRequest.setAttribute("nomeCampo",
	                    "botaoFiltrar");
	                }
                }
                break;

            default:

                break;
            }
        } else {

            //Limpando o formul�rio
            pesquisarFiltrarSetorComercialActionForm.setLocalidadeID("");
            pesquisarFiltrarSetorComercialActionForm.setLocalidadeNome("");
            pesquisarFiltrarSetorComercialActionForm.setMunicipioID("");
            pesquisarFiltrarSetorComercialActionForm.setMunicipioNome("");
            pesquisarFiltrarSetorComercialActionForm.setSetorComercialCD("");
            pesquisarFiltrarSetorComercialActionForm
                    .setSetorComercialNome("");
            pesquisarFiltrarSetorComercialActionForm.setIndicadorUso("3");
            pesquisarFiltrarSetorComercialActionForm.setIndicadorSetorAlternativo("3");
            pesquisarFiltrarSetorComercialActionForm.setIndicadorAtualizacaoCadastral("3");
            pesquisarFiltrarSetorComercialActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

        }

        httpServletRequest.removeAttribute("i");
		
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar","1");
			pesquisarFiltrarSetorComercialActionForm.setIndicadorUso("3");
			pesquisarFiltrarSetorComercialActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		// se voltou da tela de Atualizar Setor Comercial
		if (sessao.getAttribute("voltar") !=null &&
				sessao.getAttribute("voltar").equals("filtrar")){
			sessao.setAttribute("indicadorAtualizar","1");
		}
		//devolve o mapeamento de retorno
        return retorno;
    }

}
