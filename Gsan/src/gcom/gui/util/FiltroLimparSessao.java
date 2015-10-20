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
package gcom.gui.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filtro que limpa a sess�o a cada vez que o usu�rio seleciona uma
 * funcionalidade no menu
 * 
 * @author Rafael Santos
 */
public class FiltroLimparSessao extends HttpServlet implements Filter {
	private FilterConfig filterConfig;
	private static final long serialVersionUID = 1L;
	// Handle the passed-in FilterConfig
	/**
	 * <<Descri��o do m�todo>>
	 * 
	 * @param filterConfig
	 *            Descri��o do par�metro
	 */
	public void init(FilterConfig filterConfig) {
		setFilterConfig(filterConfig);
	}

	// Process the request/response pair
	/**
	 * <<Descri��o do m�todo>>
	 * 
	 * @param request
	 *            Descri��o do par�metro
	 * @param response
	 *            Descri��o do par�metro
	 * @param filterChain
	 *            Descri��o do par�metro
	 * @throws ServletException 
	 * @throws IOException 
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			String url = ((HttpServletRequest) request).getServletPath();

			if (/*(url.startsWith("/Exibir") || url.startsWith("/exibir")) && */url.endsWith(".do")) {
				HttpServletRequest requestPagina = ((HttpServletRequest) request);
				HttpSession sessao = requestPagina.getSession(false);

				if (requestPagina.getParameter("menu") != null && 
					requestPagina.getParameter("menu").equals("sim")) {
					
					Enumeration parametrosSessao = null;
					if(requestPagina.getSession(false) != null){
						parametrosSessao = requestPagina.getSession(false).getAttributeNames();
					}
					
					if(parametrosSessao != null){
						while (parametrosSessao.hasMoreElements()) {
							
							String nomeParametro = (String) parametrosSessao.nextElement();

							if (nomeParametro.equalsIgnoreCase("menuGCOM") || 
								nomeParametro.equalsIgnoreCase("arvoreFuncionalidades") || 
								nomeParametro.equalsIgnoreCase("usuarioLogado") || 
								nomeParametro.equalsIgnoreCase("dataAtual") || 
								nomeParametro.equalsIgnoreCase("dataUltimoAcesso") || 
								nomeParametro.equalsIgnoreCase("colecaoGruposUsuario") || 
								nomeParametro.equalsIgnoreCase("ultimosAcessos") || 
								nomeParametro.equalsIgnoreCase("mensagemExpiracao") || 
								nomeParametro.equalsIgnoreCase("org.apache.struts.action.LOCALE") || 
								nomeParametro.equalsIgnoreCase("gisHelper") || 
								nomeParametro.equalsIgnoreCase("gis")) {

								continue;
							} else {
								sessao.removeAttribute(nomeParametro);
							}
						}
					}
				}
			}

			filterChain.doFilter(request, response);
		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
	}

	// Clean up resources
	/**
	 * <<Descri��o do m�todo>>
	 */
	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
