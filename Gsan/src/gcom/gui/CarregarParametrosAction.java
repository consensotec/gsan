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
package gcom.gui;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.util.HibernateUtil;
import gcom.util.Internacionalizador;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.Util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.PropertyMessageResources;

public class CarregarParametrosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaLogin");

		Fachada fachada = Fachada.getInstancia();

		// instacia a variavel de aplicacao tituloPagina com o valor cadastrado
		// em sistema parametro.
		if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("tituloPagina"))) {

			// Recupera o objeto que cont�m os par�metros do sistema
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			servlet.getServletContext().setAttribute("tituloPagina",sistemaParametro.getTituloPagina());
			servlet.getServletContext().setAttribute("logoMarca",sistemaParametro.getImagemLogomarca());
			servlet.getServletContext().setAttribute("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());

			if( Util.verificarNaoVazio(sistemaParametro.getUrlhelp())){
				servlet.getServletContext().setAttribute("urlHelp",sistemaParametro.getUrlhelp());
			}
			if( Util.verificarNaoVazio(sistemaParametro.getIndicadorSenhaForte().toString())){
				servlet.getServletContext().setAttribute("indicadorSenhaForte",sistemaParametro.getIndicadorSenhaForte().toString());
			}
		}

		if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("rodapePagina"))) {

			// Recupera o objeto que cont�m as datas de Implementacao e
			// alteracao do Banco			
			DbVersaoBase dbVersaoBase = fachada.pesquisarDbVersaoBase();

			if ( dbVersaoBase != null ) {

				String versaoDataBase = Util.formatarData(dbVersaoBase.getVersaoDataBase());

				servlet.getServletContext().setAttribute("versaoDataBase",versaoDataBase);
			}
			
			try {
				
				if (ServiceLocator.getResource("java:/BatchDS") != null) {
					
					servlet.getServletContext().setAttribute("versaoTipo", "Batch");
				}
				else{
					
					servlet.getServletContext().setAttribute("versaoTipo", "Online");
				}
			} 
			catch (ServiceLocatorException e) {
				
				e.printStackTrace();
			}

			
		}

		
		Internacionalizador.setLocale(
				(Locale)httpServletRequest.getSession(false).getAttribute(Globals.LOCALE_KEY));
		
		Internacionalizador.setProperties(
				(PropertyMessageResources)servlet.getServletContext().getAttribute(Globals.MESSAGES_KEY));

		return retorno;

	}
}
