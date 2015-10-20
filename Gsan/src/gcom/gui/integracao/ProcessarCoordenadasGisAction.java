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
 * Yara Taciane de Souza
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
package gcom.gui.integracao;

import gcom.gui.GcomAction;
import gcom.gui.SessaoHttpListener;
import gcom.integracao.GisRetornoMotivo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado
 * 
 * 
 * 
 * @author Genival Barbosa
 * @since 06/05/2009
 */
public class ProcessarCoordenadasGisAction extends GcomAction {
	
	/**
	 * Action que captura as requisi��es vindas da Integra��o do Gis com o Gsan. 
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		
		ActionForward actionForward = null;			
		
		//RETORNO_MOTIVO GSAN			
		Integer retorno = GisRetornoMotivo.OPERACAO_SUCESSO;	
			
		String loginUsuario = request.getParameter("usur_nmlogin");
		
		//Flag usada para verificar se a requisi��o gis veio da tela correta. 
		//inser��o de um R.A (Aba n� 02 - Dados do local de ocorr�ncia
		HttpSession sessaoUsuario = SessaoHttpListener.listaSessoesAtivasGis.get(loginUsuario);
		
		if(sessaoUsuario == null){
			retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;		
		}else{
			//Boolean marcadorGis = (Boolean) sessao.getAttribute("gis");
		     
		    GisHelper gisHelper = new GisHelper();
		     
				
				//Login do usu�rio
					
				if(loginUsuario==null){
					retorno = GisRetornoMotivo.LOGIN_USUARIO_INEXISTENTE;
				}		
				
				//Coordenada norte da ocorr�ncia
				String nnCoordenadaNorte = request.getParameter("rgat_nncoordenadanorte");	
				if("".equals(nnCoordenadaNorte) || nnCoordenadaNorte == null){
					retorno = GisRetornoMotivo.COORDENADA_NORTE_INVALIDA;
				}else{
					gisHelper.setNnCoordenadaNorte(nnCoordenadaNorte);
				}
				
				//Coordenada leste da ocorr�ncia
				String nnCoordenadaLeste = request.getParameter("rgat_nncoordenadaleste");
				if("".equals(nnCoordenadaLeste) || nnCoordenadaLeste == null){
					retorno = GisRetornoMotivo.COORDENADA_LESTE_INVALIDA;
				}else{
					gisHelper.setNnCoordenadaLeste(nnCoordenadaLeste);
				}							
				
				//Colocando o helper na sess�o.
				sessaoUsuario.setAttribute("gisHelper",gisHelper);	
		}
		
	
			try{
			
		    PrintWriter pw = response.getWriter();	  
		    pw.println(retorno);	
		    pw.flush();
	        
		     } catch (IOException e) {
		           e.printStackTrace();
		     }

		
		return actionForward;
	}
}