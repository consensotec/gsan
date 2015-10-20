/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
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
        

		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
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

		// 2º Passo - Chamar a função de Paginação passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
		// da pesquisa que está no request
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
            throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Imóvel");
        }

        return retorno;
    }
    
    
}    