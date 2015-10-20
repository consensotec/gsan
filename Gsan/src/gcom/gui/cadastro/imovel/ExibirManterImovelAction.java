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
package gcom.gui.cadastro.imovel;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterImovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	ActionForward retorno = actionMapping.findForward("manterImovel");

        Fachada fachada = Fachada.getInstancia();
        
        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);   
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");        
        
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        if(sistemaParametro.getSituacaoAguaExclusaoImovel() != null 
        		&& sistemaParametro.getSituacaoEsgotoExclusaoImovel() != null){
        	
        	sessao.setAttribute("situacaoExclusaoConfirmada", true);
        }else{
        	sessao.removeAttribute("situacaoExclusaoConfirmada");
        }

        Collection imoveis = null;       

        sessao.removeAttribute("imoveisFiltrados");
        
        String idLocalidade = (String) sessao.getAttribute("idLocalidade");
        String codigoSetorComercial = (String) sessao.getAttribute("idSetorComercial");
	    String numeroQuadra = (String) sessao.getAttribute("idQuadra");
	    String lote = (String) sessao.getAttribute("lote");
	    String subLote = (String) sessao.getAttribute("subLote");
	    String codigoCliente = (String) sessao.getAttribute("codigoCliente");
	    String idMunicipio = (String) sessao.getAttribute("idMunicipio");
	    String cep = (String) sessao.getAttribute("cep");
	    String idBairro = (String) sessao.getAttribute("idBairro");
	    String idLogradouro = (String) sessao.getAttribute("idLogradouro");
	    String idImovel = (String) sessao.getAttribute("idImovel");
	    String numeroImovelInicial = (String) sessao.getAttribute("numeroImovelInicial");
	    String numeroImovelFinal = (String) sessao.getAttribute("numeroImovelFinal");
        

		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
		int totalRegistros = fachada
				.pesquisarQuantidadeImovel( idImovel,
		    			 idLocalidade,
		    		     codigoSetorComercial,
		    		     numeroQuadra,
		    		     lote,
		    		     subLote,
		    		     codigoCliente,
		    		     idMunicipio,
		    		     cep,
		    		     idBairro,
		    		     idLogradouro, numeroImovelInicial, numeroImovelFinal, false,false).intValue();

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
		// da pesquisa que est� no request
        imoveis = fachada.pesquisarImovel(
    			 idImovel,
    			 idLocalidade,
    		     codigoSetorComercial,
    		     numeroQuadra,
    		     lote,
    		     subLote,
    		     codigoCliente,
    		     idMunicipio,
    		     cep,
    		     idBairro,
    		     idLogradouro, numeroImovelInicial, numeroImovelFinal, false,false, (Integer) httpServletRequest
 						.getAttribute("numeroPaginasPesquisa"));

        if (imoveis != null && !imoveis.isEmpty()) {
            Iterator iteratorImoveis = imoveis.iterator();
            Collection imoveisSemDuplicar = new ArrayList();
            Collection imoveisLista = new ArrayList();
            //Imovel imovel = null;
            ClienteImovel clienteImovel = null;
            while (iteratorImoveis.hasNext()) {
            	clienteImovel = (ClienteImovel) iteratorImoveis.next();                       	
            		
	    		imoveisSemDuplicar.add(clienteImovel);
	            imoveisLista.add(clienteImovel);
	            
	            Imovel imovel = clienteImovel.getImovel();	                           
            }
            
            if(imoveisLista != null && !imoveisLista.isEmpty()){            	
            	
            	if(imoveisLista.size() == 1){
            	  if(httpServletRequest.getAttribute("atualizar") != null) {
					retorno = actionMapping.findForward("atualizarImovel");
					ClienteImovel imovelUnico = (ClienteImovel) imoveisLista.iterator().next();
					
					httpServletRequest.setAttribute("idRegistroAtualizacao",imovelUnico.getImovel().getId().toString());
					httpServletRequest.setAttribute("atualizar","atualizar");
					
            	  }else{
            		  sessao.setAttribute("imoveisFiltrados", imoveisLista);
            	  }
				} else {
					sessao.setAttribute("imoveisFiltrados", imoveisLista);
				}            	
            }            
        } else {
            throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Im�vel");
        }

        return retorno;
    }
    
    
}    