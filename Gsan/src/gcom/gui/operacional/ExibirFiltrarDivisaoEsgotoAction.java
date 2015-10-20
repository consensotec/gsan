
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
package gcom.gui.operacional;


import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Arthur Carvalho
 * @date 10/06/2008
 */

public class ExibirFiltrarDivisaoEsgotoAction extends GcomAction {
	
	
	private String unidadeOrganizacionalId;

	private Collection colecaoPesquisa;
	/*
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarDivisaoEsgoto");

		Fachada fachada = Fachada.getInstancia();
		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarDivisaoEsgotoActionForm filtrarDivisaoEsgotoActionForm = (FiltrarDivisaoEsgotoActionForm) actionForm;
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarDivisaoEsgotoActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());	
		}
		
		if(filtrarDivisaoEsgotoActionForm.getIndicadorAtualizar()==null){
			filtrarDivisaoEsgotoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        
        	filtrarDivisaoEsgotoActionForm.setDescricao("");
        	filtrarDivisaoEsgotoActionForm.setIndicadorUso("");
        	filtrarDivisaoEsgotoActionForm.setTipoPesquisa("");
        	filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
        	filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao("");
        	
        	
        }

		
		 String objetoConsulta = (String) httpServletRequest
	        .getParameter("objetoConsulta");
	        
	        if (objetoConsulta != null
	                && !objetoConsulta.trim().equalsIgnoreCase("")) {
	        	
	            //Recebe o valor do campo unidade organizacional do formul�rio.
	            unidadeOrganizacionalId = filtrarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();


	            switch (Integer.parseInt(objetoConsulta)) {
	            	// Distrito Operacional
	            	case 1:
	                    pesquisarUnidadeOrganizacional(filtrarDivisaoEsgotoActionForm,
	                            fachada, httpServletRequest);
	                    break;
	                default:
	                    break;
	            }
	        }
		
		
		String idUnidadeOrganizacional = filtrarDivisaoEsgotoActionForm.getUnidadeOrganizacionalId();
		
       //Verificar se o n�mero da unidade organizacional n�o est� cadastrado
		if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional= new FiltroUnidadeOrganizacional();

			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, 
						idUnidadeOrganizacional));

			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional== null || colecaoUnidadeOrganizacional.isEmpty()) {
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao( "CLIENTE INEXISTENTE" );
				httpServletRequest.setAttribute("existeUnidadeOrganizacional","exception");
				httpServletRequest.setAttribute("nomeCampo","idUnidadeOrganizacional");
			}else{
				UnidadeOrganizacional unidadeOrganizacional= (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId(unidadeOrganizacional.getId().toString());
				filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
				httpServletRequest.setAttribute("nomeCampo","idUnidadeOrganizacional");
			}
		}

       return retorno;

	}
	   private void pesquisarUnidadeOrganizacional(FiltrarDivisaoEsgotoActionForm filtrarDivisaoEsgotoActionForm, Fachada fachada, HttpServletRequest httpServletRequest) {
	        if (unidadeOrganizacionalId == null || unidadeOrganizacionalId.trim().equalsIgnoreCase("")) {
	            //Limpa o campo unidadeOrganizacionalID do formulario
	        	filtrarDivisaoEsgotoActionForm
	                    .setUnidadeOrganizacionalDescricao("Informe Unidade Organizacional");
	            httpServletRequest.setAttribute("corUnidadeOrganizacional", "exception");
	        } else {
	            FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

	            filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
	                    FiltroDistritoOperacional.ID, unidadeOrganizacionalId));

	            //Retorna unidadeOrganizacional
	            colecaoPesquisa = fachada.pesquisar(filtroUnidadeOrganizacional,
	                    UnidadeOrganizacional.class.getName());

	            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	                //unidadeOrganizacional nao encontrada
	                //[FS0001] verifica a existencia da unidade
	            	//Limpa o campo unidadeOrganizacionalID do formul�rio
	            	filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId("");
	            	filtrarDivisaoEsgotoActionForm
	                        .setUnidadeOrganizacionalDescricao("Unidade Organizacional inexistente.");
	                httpServletRequest.setAttribute("corUnidadeOrganizacional", "exception");
	                
	                httpServletRequest.setAttribute("nomeCampo", "unidadeOrganizacionalID");
	            } else {
	                UnidadeOrganizacional objetoUnidadeOrganizacional = (UnidadeOrganizacional) Util
	                	.retonarObjetoDeColecao(colecaoPesquisa);
	                filtrarDivisaoEsgotoActionForm.setUnidadeOrganizacionalId(String
	                		.valueOf(objetoUnidadeOrganizacional.getId()));
	                filtrarDivisaoEsgotoActionForm
	                	.setUnidadeOrganizacionalDescricao(objetoUnidadeOrganizacional.getDescricao());
	                httpServletRequest.setAttribute("corUnidadeOrganizacional", "valor");
	            }
	        }
	   }
}
