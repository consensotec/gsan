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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade receber os par�metros informados pelo usu�rio e realizar uma 
 * pesquisa de CEPs a partir dos mesmos 
 *
 * @author Raphael Rossiter
 * @date 06/05/2006
 */
public class PesquisarCepAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("pesquisarCep");

        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarCepActionForm pesquisarCepActionForm = (PesquisarCepActionForm) actionForm;
        Municipio municipio = null;

        if (pesquisarCepActionForm.getIdMunicipio() == null || 
        	pesquisarCepActionForm.getIdMunicipio().equals("")){
        	
        	throw new ActionServletException("errors.required", null, "munic�pio");	
        }
        else{
        	
        	FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
        	
        	filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
            pesquisarCepActionForm.getIdMunicipio()));
        	if( sessao.getAttribute("indicadorUsoTodos") == null ){
        		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, 
        				ConstantesSistema.INDICADOR_USO_ATIVO));
        	}    	
            Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
                	
            if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
            	municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
            }
            else{
            	throw new ActionServletException("atencao.municipio.inexistente");
            }
        }
        
        FiltroCep filtroCep = new FiltroCep(FiltroCep.CODIGO);

        filtroCep.adicionarParametro(new ComparacaoTexto(FiltroCep.MUNICIPIO, municipio.getNome()));
        
        if( sessao.getAttribute("indicadorUsoTodos") == null ){
    		filtroCep.adicionarParametro(new ParametroSimples(FiltroCep.INDICADORUSO, 
    				ConstantesSistema.INDICADOR_USO_ATIVO));
    	}
        
        if (pesquisarCepActionForm.getNomeLogradouro() != null &&
        	!pesquisarCepActionForm.getNomeLogradouro().equals("")){
        	
        	if (pesquisarCepActionForm.getTipoPesquisaLogradouro() != null && 
        			pesquisarCepActionForm.getTipoPesquisaLogradouro().equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
							.toString())) {
        		
        		filtroCep.adicionarParametro(new ComparacaoTextoCompleto(FiltroCep.LOGRADOURO,
        	        	pesquisarCepActionForm.getNomeLogradouro()));        		
        		
        	}else {
        	filtroCep.adicionarParametro(new ComparacaoTexto(FiltroCep.LOGRADOURO,
        	pesquisarCepActionForm.getNomeLogradouro()));
        }
        }
        if(pesquisarCepActionForm.getIdCepInicial() != null && pesquisarCepActionForm.getIdCepInicial() != 0){
        	filtroCep.adicionarParametro(new MaiorQue(FiltroCep.CODIGO, pesquisarCepActionForm.getIdCepInicial()));        	
        	
        }
        if(pesquisarCepActionForm.getIdCepFinal() != null && pesquisarCepActionForm.getIdCepFinal() != 0){
    		filtroCep.adicionarParametro(new MenorQue(FiltroCep.CODIGO, pesquisarCepActionForm.getIdCepFinal()));
    	}
        
        
        /*
         * C�digo respons�vel pela implementa��o da nova pagina��o
         */
        Map resultado = controlarPaginacao(httpServletRequest, retorno,
		filtroCep, Cep.class.getName());
 
        Collection colecaoCep = (Collection) resultado.get("colecaoRetorno");
        
        retorno = (ActionForward) resultado.get("destinoActionForward");
        if(colecaoCep == null || colecaoCep.isEmpty())
        {
            throw new ActionServletException(
                  "atencao.pesquisa.nenhumresultado", null, "cep");
        }
        sessao.setAttribute("colecaoCep", colecaoCep);

        
        return retorno;
    }

}
