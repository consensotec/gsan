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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00802] Filtrar Debito Automatico
 * 
 * @author Bruno Barros
 *
 * @date 23/05/2008
 */
public class ExibirFiltrarDebitoAutomaticoAction extends GcomAction {

	private Collection colecaoPesquisa;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarDebitoAutomatico");
		
        Fachada fachada = Fachada.getInstancia();
		
		FiltrarDebitoAutomaticoActionForm filtrarDebitoAutomaticoActionForm = (FiltrarDebitoAutomaticoActionForm) actionForm;

		String objetoConsulta = (String) httpServletRequest
        	.getParameter("objetoConsulta");

		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {	
		
			// Banco
            	case 1:
            		//Recebe o valor do campo bancoID do formul�rio.
            		String bancoID = filtrarDebitoAutomaticoActionForm
                        .getBancoID();

            		FiltroBanco filtroBanco = new FiltroBanco();

            		filtroBanco
                        .adicionarParametro(new ParametroSimples(
                                FiltroBanco.ID, bancoID));

            		filtroBanco
                        .adicionarParametro(new ParametroSimples(
                        		FiltroBanco.INDICADOR_USO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Banco
                colecaoPesquisa = fachada.pesquisar(filtroBanco,
                        Banco.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
	            	filtrarDebitoAutomaticoActionForm.setAgenciaCodigo( null );
	            	filtrarDebitoAutomaticoActionForm.setAgenciaDescricao( null );	                	
                    //Setor censitario nao encontrado
                    //Limpa o campo bancoID do formul�rio
                	filtrarDebitoAutomaticoActionForm.setBancoID("");
                	filtrarDebitoAutomaticoActionForm
                            .setBancoDescricao("Banco inexistente.");
                    httpServletRequest.setAttribute("corBanco",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "bancoID");
                } else {
                    Banco objetoBanco = (Banco) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    filtrarDebitoAutomaticoActionForm.setBancoID(String
                            .valueOf(objetoBanco.getId()));
                    filtrarDebitoAutomaticoActionForm
                            .setBancoDescricao(objetoBanco
                                    .getDescricao());
                    httpServletRequest.setAttribute("corBanco",
                            "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "bancoID");
                }
                break;
                
                // Ag�ncia
            	case 2:

                //Recebe o valor do campo agenciaID do formul�rio.
                String agenciaCodigo = filtrarDebitoAutomaticoActionForm
                        .getAgenciaCodigo();

                FiltroAgencia filtroAgencia = new FiltroAgencia();

                filtroAgencia
                        .adicionarParametro(new ParametroSimples(
                                FiltroAgencia.CODIGO_AGENCIA, agenciaCodigo));                

                //Retorna Setor censitario
                colecaoPesquisa = fachada.pesquisar(filtroAgencia,
                        Agencia.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Agencia nao encontrado
                    //Limpa o campo agenciaID do formul�rio
                    filtrarDebitoAutomaticoActionForm.setAgenciaCodigo("");
                    filtrarDebitoAutomaticoActionForm
                            .setAgenciaDescricao("Ag�ncia inexistente.");
                    httpServletRequest.setAttribute("corAgencia",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "agenciaID");
                } else {
                    filtroAgencia
                    .adicionarParametro(new ParametroSimples(
                            FiltroAgencia.BANCO_ID, filtrarDebitoAutomaticoActionForm.getBancoID() ) );                
                	
		            //Retorna Setor censitario
		            colecaoPesquisa = fachada.pesquisar(filtroAgencia,
		                    Agencia.class.getName());
		            
		            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            	filtrarDebitoAutomaticoActionForm.setAgenciaCodigo( null );
		            	filtrarDebitoAutomaticoActionForm.setAgenciaDescricao( null );		            	
		            	
		            	throw new ActionServletException( "atencao.agencia.banco_errado", "exibirFiltrarDebitoAutomaticoAction.do", null, new String[] {} ); 		            	
		            } else {
	                    Agencia objetoAgencia = (Agencia) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
		                filtrarDebitoAutomaticoActionForm.setAgenciaCodigo(String
		                        .valueOf(objetoAgencia.getCodigoAgencia()));
		                filtrarDebitoAutomaticoActionForm
		                        .setAgenciaDescricao(objetoAgencia
		                                .getNomeAgencia());
		                httpServletRequest.setAttribute("corAgencia",
		                        "valor");
		                
		                httpServletRequest.setAttribute("nomeCampo", "agenciaCodigo");
		            }	
                }
                break;
            
            default:

                break;
            }
		}
		
		
		if(filtrarDebitoAutomaticoActionForm.getIndicadorAtualizar()==null){
			filtrarDebitoAutomaticoActionForm.setIndicadorAtualizar("1");
		}
        
        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {        	
        	filtrarDebitoAutomaticoActionForm.setMatricula( "" );
        	filtrarDebitoAutomaticoActionForm.setBancoID("");
        	filtrarDebitoAutomaticoActionForm.setAgenciaCodigo("");
        	filtrarDebitoAutomaticoActionForm.setIndicadorAtualizar("");
        }		
		
		return retorno;
	}	
}