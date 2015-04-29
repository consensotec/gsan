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
package gsan.gui.cobranca;

import gsan.gui.GcomAction;
import gsan.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action inicial do caso de uso [UC0214] Efetuar Parcelamento de D�bitos
 * 
 * @author Rodrigo
 */
public class ExibirEfetuarParcelamentoDebitosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		// Vai para a primeira p�gina do caso de uso

		ActionForward retorno = actionMapping
				.findForward("exibirEfetuarParcelamentoDebitosProcesso1Action");

		// obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm efetuarParcelamentoDebitosActionForm = (DynaValidatorForm) actionForm;
		
		if (httpServletRequest.getParameter("guardarMatriculaImovel") != null && !httpServletRequest.getParameter("guardarMatriculaImovel").equals("")){
			String codigoImovel = (String)httpServletRequest.getParameter("guardarMatriculaImovel");
			
			if (!efetuarParcelamentoDebitosActionForm.get("matriculaImovel").equals(codigoImovel)){
				//limpa a sess�o
				sessao.removeAttribute("EfetuarParcelamentoDebitosActionForm");
				
				//cria uma nova instancia do actionForm e seta apenas a matricula do imovel nele
				 ModuleConfig module = actionMapping.getModuleConfig();
				 FormBeanConfig formBeanConfig = module.findFormBeanConfig("EfetuarParcelamentoDebitosActionForm");
				 DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formBeanConfig);
				 DynaValidatorForm form = null;
				 try {
					form = (DynaValidatorForm) dynaClass.newInstance();
					form.set("matriculaImovel",codigoImovel);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}

				sessao.setAttribute("EfetuarParcelamentoDebitosActionForm",form);
			}
			
		}else{
			//limpa a sess�o
			sessao.removeAttribute("EfetuarParcelamentoDebitosActionForm");
		}
		
//		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
//			
//			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
//			
//			if (sistemaParametro.getIndicadorDividaAtiva() == 1) {
//				sessao.setAttribute("empresaDividaAtiva", "sim");
//			}
//			
//		}
			
		// Monta o Status do Wizard - Do tipo Valida Avan�ar e Valida Voltar
		StatusWizard statusWizard = new StatusWizard(
				"efetuarParcelamentoDebitosWizardAction",
				"concluirProcessoAction",
				"cancelarEfetuarParcelamentoDebitosAction",
				"",
				"",
				"exibirEfetuarParcelamentoDebitosAction.do?menu=sim",
				"");
        

        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                1, "ImovelPrimeiraAbaA.gif", "ImovelPrimeiraAbaD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso1Action", "processarProcesso1Action"));
        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                2, "DebitosA.gif", "DebitosD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso2Action", "processarProcesso2Action"));
        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                3, "NegociacaoA.gif", "NegociacaoD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso3Action", "processarProcesso3Action"));
        statusWizard
        .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                4, "ConclusaoA.gif", "ConclusaoD.gif",
                "exibirEfetuarParcelamentoDebitosProcesso4Action", "processarProcesso4Action"));

		/*statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ImovelPrimeiraAbaA.gif", "ImovelPrimeiraAbaD.gif",
						"exibirProcesso1Action", "processarProcesso1Action"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "DebitosA.gif", "DebitosD.gif",
						"exibirProcesso2Action", "processarProcesso2Action"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "NegociacaoA.gif", "NegociacaoD.gif",
						"exibirProcesso3Action", "processarProcesso3Action"));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "ConclusaoA.gif", "ConclusaoD.gif",
						"exibirProcesso4Action", "processarProcesso4Action"));
*/
		// manda o statusWizard para a sessao
		sessao.setAttribute("statusWizard", statusWizard);

		return retorno;
	}
}