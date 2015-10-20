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
package gcom.gui.arrecadacao.pagamento;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarGuiaPagamentoEmAtrasoAction extends GcomAction {

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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarGuiaPagamentoEmAtraso");

       // DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);

        //financiamento tipo
        FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
        
        filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.INDICADOR_USO, 
        		ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection colecaoFinancimentoTipo = fachada.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class.getName());
        
        sessao.setAttribute("colecaoFinancimentoTipo", colecaoFinancimentoTipo);
        
        
        FiltrarGuiaPagamentoEmAtrasoActionForm filtrarGuiaPagamentoEmAtrasoActionForm = 
                (FiltrarGuiaPagamentoEmAtrasoActionForm) actionForm;


        //Gerencia Regional
        FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
        filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
                
        sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
                
        //Unidade de Negocio
        String preencheUnidade = (String)httpServletRequest.getParameter("preencheUnidade");
        Collection colecaoUnidadeNegocio = null;
        
        if(preencheUnidade != null){
        	if(!filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
        		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
        		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional()));
        		colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
               
                sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
        	}
        }else{
        	if(sessao.getAttribute("colecaoUnidadeNegocio") == null){
        		colecaoUnidadeNegocio = new ArrayList();
                sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
        	}
        }
                
        //Buscar localidades Inicial e Final
        String tipoPesquisa = (String)httpServletRequest.getParameter("tipoPesquisa");
        Localidade localidade = null;
                
        if(tipoPesquisa != null && !tipoPesquisa.equals("")){
        	
        	if(tipoPesquisa.equals("pesquisaLocalidadeInicial")){
               
                //Verifica se foi selecionada a op��o de gerencia regional
                if(filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional() != null && 
                		!filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
                
                	//Verifica se foi selecionada a op��o de Unidade de negocio
                	if(filtrarGuiaPagamentoEmAtrasoActionForm.getIdUnidadeNegocio() != null && 
                			!filtrarGuiaPagamentoEmAtrasoActionForm.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
               
                			localidade = this.buscarLocalidadePorId(
                				filtrarGuiaPagamentoEmAtrasoActionForm.getIdLocalidadeInicial(), fachada, 
                				filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional(), filtrarGuiaPagamentoEmAtrasoActionForm.getIdUnidadeNegocio());
                	}else{
                		localidade = this.buscarLocalidadePorId(
                			filtrarGuiaPagamentoEmAtrasoActionForm.getIdLocalidadeInicial(), fachada, filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional(), null);
                }
        	}else{
        		localidade = this.buscarLocalidadePorId(
                filtrarGuiaPagamentoEmAtrasoActionForm.getIdLocalidadeInicial(), fachada, null, null);
        	}        
               
               
        	if(localidade != null){
        		
        		filtrarGuiaPagamentoEmAtrasoActionForm.setIdLocalidadeInicial(localidade.getId().toString());
                filtrarGuiaPagamentoEmAtrasoActionForm.setNomeLocalidadeInicial(localidade.getDescricao());
               
                //Replica os valores do campo de localidade inicial nos campos de localidade final
                filtrarGuiaPagamentoEmAtrasoActionForm.setIdLocalidadeFinal(localidade.getId().toString());
                filtrarGuiaPagamentoEmAtrasoActionForm.setNomeLocalidadeFinal(localidade.getDescricao());
           
                sessao.setAttribute("localidadeFinalEncontrada", true);
                sessao.setAttribute("localidadeInicialEncontrada", true);
        	}else{
                filtrarGuiaPagamentoEmAtrasoActionForm.setIdLocalidadeInicial("");
                filtrarGuiaPagamentoEmAtrasoActionForm.setIdLocalidadeFinal("");
                filtrarGuiaPagamentoEmAtrasoActionForm.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
                sessao.removeAttribute("localidadeInicialEncontrada");               
            }
               
        }else {
        	if(tipoPesquisa.equals("pesquisaLocalidadeFinal")){

        		//Verifica se foi selecionada a op��o de gerencia regional
                if(filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional() != null && 
                !filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
                
                	//Verifica se foi selecionada a op��o de Unidade de negocio
                    if(filtrarGuiaPagamentoEmAtrasoActionForm.getIdUnidadeNegocio() != null && 
                        !filtrarGuiaPagamentoEmAtrasoActionForm.getIdUnidadeNegocio().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
                   
                    	localidade = this.buscarLocalidadePorId(
                    		filtrarGuiaPagamentoEmAtrasoActionForm.getIdLocalidadeFinal(), fachada, 
                    		filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional(), filtrarGuiaPagamentoEmAtrasoActionForm.getIdUnidadeNegocio());
                    }else{
                    	localidade = this.buscarLocalidadePorId(
                    		filtrarGuiaPagamentoEmAtrasoActionForm.getIdLocalidadeFinal(), fachada, filtrarGuiaPagamentoEmAtrasoActionForm.getIdGerenciaRegional(), null);
                    }
                }else{
                    localidade = this.buscarLocalidadePorId(
                    	filtrarGuiaPagamentoEmAtrasoActionForm.getIdLocalidadeFinal(), fachada, null, null);
                }
               
                if(localidade != null){
                	filtrarGuiaPagamentoEmAtrasoActionForm.setIdLocalidadeFinal(localidade.getId().toString());
                	filtrarGuiaPagamentoEmAtrasoActionForm.setNomeLocalidadeFinal(localidade.getDescricao());
                	sessao.setAttribute("localidadeFinalEncontrada", true);
                }else{
                	filtrarGuiaPagamentoEmAtrasoActionForm.setIdLocalidadeFinal("");
                	filtrarGuiaPagamentoEmAtrasoActionForm.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
                	sessao.removeAttribute("localidadeFinalEncontrada");
                	}
        		}
        	}
        }
        return retorno;
    }
    
    
    /**
     * Procura a localidade pelo id informado;  
     * @author Erivan
     * @param id, fachada
     * @return Localidade
     */
    private Localidade buscarLocalidadePorId(String id, Fachada fachada, String gerenciaRegional, String unidadeNegocio){
	    Localidade localidade = null;
	   
	    FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, id));
	    filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.GERENCIA);
	    filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroLocalidade.UNIDADE_NEGOCIO);
	   
	    Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
   
	    if(colecaoLocalidade != null && !colecaoLocalidade.isEmpty()){
	    	localidade = (Localidade) colecaoLocalidade.iterator().next();
   
	    	if(localidade != null){
	    		if(gerenciaRegional != null){
	    			if(!localidade.getGerenciaRegional().getId().toString().equals(gerenciaRegional)){
   
	    				throw new ActionServletException("atencao.localidade_nao_pertence_gerencia_regional");
	    			}
	    		}
	    		if(unidadeNegocio != null){
	    			if(!localidade.getUnidadeNegocio().getId().toString().equals(unidadeNegocio)){
	    				throw new ActionServletException("atencao.localidade_nao_pertence_unidade_negocio");
	    			}    
	    		}
	    	}
	    }
   
	    return localidade;
    }
}
