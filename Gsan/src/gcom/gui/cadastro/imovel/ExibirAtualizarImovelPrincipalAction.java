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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirAtualizarImovelPrincipalAction extends GcomAction {

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
                .findForward("atualizarImovelPrincipal");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm atualizarImovelPrincipalActionForm = (DynaValidatorForm) actionForm;

        String pesquisar = httpServletRequest.getParameter("pesquisar");

        //Cria variaveis
        String idImovel = (String) atualizarImovelPrincipalActionForm
                .get("idImovel");

        
        if(pesquisar != null && !pesquisar.equalsIgnoreCase("")){
	        
	        //Cria variaveis
	        //Cria Filtros
	        FiltroImovel filtroImovel = new FiltroImovel();
	        
	        //Objetos que ser�o retornados pelo Hibernate
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
	        
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.quadra");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroBairro.bairro.municipio.unidadeFederacao");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.cep");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTipo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.logradouroCep.logradouro.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.enderecoReferencia");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.setorComercial");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.perimetroFinal.logradouroTitulo");
	        filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal.imovelPrincipal");
	        
	        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(
					FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
					Imovel.IMOVEL_EXCLUIDO, FiltroParametro.CONECTOR_OR,2));

	        filtroImovel.adicionarParametro(new ParametroNulo(
					FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));
	        
	        //Obt�m a inst�ncia da Fachada
	        Fachada fachada = Fachada.getInstancia();
	
	        Collection imoveis = null;//new HashSet();
	
	        //       sessao.setAttribute("imoveisPrincipal", imoveis);
	
	        if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
	            filtroImovel.adicionarParametro(new ParametroSimples(
	                    FiltroImovel.ID, new Integer(idImovel)));
	
	            Imovel imovelAtualizado = (Imovel)sessao.getAttribute("imovelAtualizacao");
	            
	            //testa se o imovel a ser principal � o mesmo que esta sendo atualizado
	            if(idImovel.equals(imovelAtualizado.getId().toString())){
	            	//atualizarImovelPrincipalActionForm.set("idImovel","");
	            	throw new ActionServletException("atencao.imovel.principal.igual.atualizado");
	            }
	            
	            imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
	
	            if (imoveis != null && !imoveis.isEmpty()) {
	                /*
	                 * inserirImovelPrincipalActionForm.set("idImovel", ((Imovel)
	                 * ((List) imoveis).get(0)).getId().toString());
	                 * inserirImovelPrincipalActionForm.set("descricaoImovel",
	                 * ((Imovel) ((List) imoveis).get(0)).getNumeroImovel());
	                 * inserirImovelPrincipalActionForm.set("tipoLogradouro",
	                 * ((Imovel) ((List)
	                 * imoveis).get(0)).getLogradouro().getLogradouroTipo().getDescricao());
	                 * inserirImovelPrincipalActionForm.set("tituloLogradouro",
	                 * ((Imovel) ((List)
	                 * imoveis).get(0)).getLogradouro().getLogradouroTitulo().getDescricao());
	                 * inserirImovelPrincipalActionForm.set("logradouro", ((Imovel)
	                 * ((List) imoveis).get(0)).getLogradouro().getNome());
	                 * inserirImovelPrincipalActionForm.set("municipio", ((Imovel)
	                 * ((List)
	                 * imoveis).get(0)).getLogradouro().getMunicipio().getNome());
	                 * inserirImovelPrincipalActionForm.set("bairro", ((Imovel)
	                 * ((List) imoveis).get(0)).getQuadra().getBairro().getNome());
	                 */
	            	sessao.setAttribute("idImoveilPrincipal", ((Imovel)imoveis.iterator().next()).getId().toString());
	                sessao.setAttribute("imoveisPrincipal", imoveis);
	                atualizarImovelPrincipalActionForm.set("idImovel",idImovel);
	            } else {
	            	if(pesquisar != null && !pesquisar.equalsIgnoreCase("")){
	            		throw new ActionServletException("atencao.pesquisa.imovel.inexistente",null, idImovel);
	            	}
	            }
	
	        }
        }else{
        	 atualizarImovelPrincipalActionForm.set("idImovel",idImovel);
        }

        return retorno;
    }

}
