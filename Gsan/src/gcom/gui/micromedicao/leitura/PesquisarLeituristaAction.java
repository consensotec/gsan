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
package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o processamento da p�gina de pesquisa de Localidade
 * 
 * @author Administrador
 */

public class PesquisarLeituristaAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("retornarLeituristaPesquisar");
        
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        PesquisarLeituristaActionForm pesquisarActionForm = (PesquisarLeituristaActionForm) actionForm;

        if (httpServletRequest.getParameter("limparForm") != null
				&& httpServletRequest.getParameter("limparForm")
						.equals("sim")) {
        	pesquisarActionForm.setIdFuncionario("");
        	pesquisarActionForm.setIdCliente("");
            pesquisarActionForm.setDDDTelefone("");
            pesquisarActionForm.setNumeroTelefone(""); 
        }
        
        
        //cria variaveis
        String idEmpresa = pesquisarActionForm.getEmpresa();
        String idFuncionario = pesquisarActionForm.getIdFuncionario();
        String idCliente = pesquisarActionForm.getIdCliente();
        String ddd = pesquisarActionForm.getDDDTelefone();
        String numeroTelefone = pesquisarActionForm.getNumeroTelefone(); 
        
        //
        String empresas = httpServletRequest.getParameter("empresas");
        String[] empresasSeparadas = null;
        if(empresas!=null && !empresas.equals("")){
        	empresasSeparadas =  empresas.split(",");
        }
        
        boolean peloMenosUmParametroInformado = false;

        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();

        FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.ID);
        
        //Objetos que ser�o retornados pelo Hibernate
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("empresa");
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("funcionario");
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade("cliente");

        if( sessao.getAttribute("indicadorUsoTodos") == null ){
    	    filtroLeiturista.adicionarParametro(new ParametroSimples(
                FiltroLeiturista.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        }
        
        if(empresasSeparadas!=null && empresasSeparadas.length>0){
        	Collection<Integer> idsEmpresas = new ArrayList<Integer>();
        	for (int i = 0; i < empresasSeparadas.length; i++) {
				Integer idEmpresaPesquisa = new Integer(empresasSeparadas[i]);
				if(!idsEmpresas.contains(idEmpresaPesquisa)){
					idsEmpresas.add(idEmpresaPesquisa);
				}
			}
        	
        	filtroLeiturista.adicionarParametro(new ParametroSimplesIn(FiltroLeiturista.EMPRESA_ID, idsEmpresas));	
        	filtroLeiturista.setCampoOrderBy(FiltroLeiturista.EMPRESA_ID);
        	peloMenosUmParametroInformado = true;
        }
        
        if (idEmpresa != null && !idEmpresa.trim().equalsIgnoreCase("")
                && !idEmpresa.trim().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.EMPRESA, new Integer(idEmpresa)));
            peloMenosUmParametroInformado = true;
        }
        if (idFuncionario != null && !idFuncionario.trim().equalsIgnoreCase("")) {
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.FUNCIONARIO, new Integer(idFuncionario)));
            peloMenosUmParametroInformado = true;
        }
        if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.CLIENTE, new Integer(idCliente)));
            peloMenosUmParametroInformado = true;
        }
        if (ddd != null && !ddd.trim().equalsIgnoreCase("")) {
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.DDD, new Integer(ddd)));
            peloMenosUmParametroInformado = true;
        }
        if (numeroTelefone != null && !numeroTelefone.trim().equalsIgnoreCase("")) {
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.TELEFONE  , new Integer(numeroTelefone)));
            peloMenosUmParametroInformado = true;
        }        
        
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }
        
        Collection leituristas = fachada.pesquisar(filtroLeiturista,
                Leiturista.class.getName());

        if (leituristas != null && !leituristas.isEmpty()) {
//        	 Aciona o controle de pagina��o para que sejam pesquisados apenas
			// os registros que aparecem na p�gina
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLeiturista, Leiturista.class.getName());
			leituristas = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("leituristas", leituristas);
        } else {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhumresultado", null, "Leiturista");
        }
        
        return retorno;
    }

}
