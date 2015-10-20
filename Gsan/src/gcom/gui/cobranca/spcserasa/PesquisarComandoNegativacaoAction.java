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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC 0653] Pesquisar Comando Negativa��o
 * 
 * @author K�ssia Albuquerque
 * @date 22/10/2007
 */



public class PesquisarComandoNegativacaoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaComandoNegativacao");
			
			HttpSession sessao = httpServletRequest.getSession(false);
	
			PesquisarComandoNegativacaoActionForm form = (PesquisarComandoNegativacaoActionForm) actionForm;
			
			// Recupera todos os campos da p�gina para ser colocada no filtro posteriomente
			
		
			String tituloComando = form.getTituloComando();
			String tipoPesquisa = form.getTipoPesquisa();
			String indicadorComandoSimulado = form.getIndicadorComandoSimulado();
			String periodoGeracaoComandoInicial = form.getPeriodoGeracaoComandoInicial();
			String periodoGeracaoComandoFinal = form.getPeriodoGeracaoComandoFinal();
			String periodoExecucaoComandoInicial = form.getPeriodoExecucaoComandoInicial();
			String periodoExecucaoComandoFinal = form.getPeriodoExecucaoComandoFinal();
			String usuarioResponsavelId = form.getUsuarioResponsavelId();
			
			ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
			
			boolean peloMenosUmParametroInformado = false;
	
			//	TITULO COMANDO
			if (tituloComando != null && !tituloComando.trim().equals("")){
	
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setTituloComando(tituloComando);
				comandoNegativacaoHelper.setTipoPesquisaTituloComando(new Short(tipoPesquisa));
	
			}
		
			//   COMANDO SIMULADO
			if (indicadorComandoSimulado != null && !indicadorComandoSimulado.equals("") ) {
				
				if(!indicadorComandoSimulado.equals(ConstantesSistema.TODOS.toString())){
					peloMenosUmParametroInformado = true;
				}
				
				comandoNegativacaoHelper.setIndicadorComandoSimulado(new Short(indicadorComandoSimulado));
			}
			
			//	PERIODO GERA��O DO COMANDO
			if ((periodoGeracaoComandoInicial!= null && periodoGeracaoComandoFinal != null)
					&&(!periodoGeracaoComandoInicial.equalsIgnoreCase("")&& !periodoGeracaoComandoFinal.equalsIgnoreCase(""))) {
				
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setGeracaoComandoInicio(Util.converteStringParaDate(periodoGeracaoComandoInicial));
				comandoNegativacaoHelper.setGeracaoComandoFim(Util.converteStringParaDate(periodoGeracaoComandoFinal));
			}
			
			
			 //	PERIODO EXECU��O DO COMANDO
			if ((periodoExecucaoComandoInicial!= null && periodoExecucaoComandoFinal != null)
					&&(!periodoExecucaoComandoInicial.equalsIgnoreCase("")&& !periodoExecucaoComandoFinal.equalsIgnoreCase(""))) {
				
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setExecucaoComandoInicio(Util.converteStringParaDate(periodoExecucaoComandoInicial));
				comandoNegativacaoHelper.setExecucaoComandoFim(Util.converteStringParaDate(periodoExecucaoComandoFinal));
			}
	
			//	USUARIO RESPONSAVEL
			if (usuarioResponsavelId != null && !usuarioResponsavelId.equals("")) {
	
				peloMenosUmParametroInformado = true;
				comandoNegativacaoHelper.setIdUsuarioResponsavel(new Integer(usuarioResponsavelId));
	
			}
	
			// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
	
			// Manda o Helper pela sessao para o ExibirResultadoPesquisaComandoNegativacaoAction
			
			sessao.setAttribute("comandoNegativacaoHelper", comandoNegativacaoHelper);
	
	
			
			return retorno;
	}
}
