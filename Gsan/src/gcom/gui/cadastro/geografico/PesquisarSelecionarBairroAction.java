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
package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.cadastro.geografico.Municipio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio os bairros que ser�o retornados
 * a partir dos par�metros que foram passados pelo filtro
 *
 * @author Raphael Rossiter
 * @date 02/05/2006
 */
public class PesquisarSelecionarBairroAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("exibirSelecionarBairro");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        SelecionarBairroActionForm selecionarBairroActionForm = (SelecionarBairroActionForm) actionForm;

        FiltroBairro filtroBairro = new FiltroBairro();
        filtroBairro.setConsultaSemLimites(true);
        
        //Objetos que ser�o retornados pelo hibernate.
        filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
        
        filtroBairro.setCampoOrderBy(FiltroBairro.NOME);
        
        Collection colecaoBairro = null;

        //Validando os campos do formulario
        boolean peloMenosUmParametroInformado = false;

        
        //Munic�pio
        if (selecionarBairroActionForm.getIdMunicipio() != null && 
        	!selecionarBairroActionForm.getIdMunicipio().equals("")) {

            String municipioJSP = selecionarBairroActionForm.getIdMunicipio();

            peloMenosUmParametroInformado = true;

            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
            
            filtroMunicipio.adicionarParametro(new ParametroSimples(
            FiltroMunicipio.ID, municipioJSP));
            
            Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
            
            if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()){
            	
            	throw new ActionServletException("atencao.municipio.inexistente");
            }
            
            filtroBairro.adicionarParametro(new ParametroSimples(
            FiltroBairro.MUNICIPIO_ID, municipioJSP));
        }

        
        //Bairro
        if (selecionarBairroActionForm.getNomeBairro() != null &&
        	!selecionarBairroActionForm.getNomeBairro().equals("")) {

            String nomeBairroJSP = selecionarBairroActionForm.getNomeBairro();

            peloMenosUmParametroInformado = true;

            filtroBairro.adicionarParametro(new ComparacaoTexto(
            FiltroBairro.NOME, nomeBairroJSP));
            
            filtroBairro.setCampoOrderBy(FiltroBairro.NOME);
        }
        
        
        //Erro caso o usu�rio mandou filtrar sem nenhum par�metro
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }

        //Retorna o(s) bairro(s)
        colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
        

        if (colecaoBairro == null || colecaoBairro.isEmpty()) {
            //Nenhum bairro cadastrado de acordo com os par�metros passados
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "bairro");
        } else {
        	sessao.setAttribute("colecaoBairrosSelecionados", colecaoBairro);
        }
        
        httpServletRequest.setAttribute("nomeCampo", "botaoSelecionar");

        return retorno;
    }

}
