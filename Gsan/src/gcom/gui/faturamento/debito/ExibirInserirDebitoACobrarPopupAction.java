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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir D�bito a Cobrar Popup 
 * 
 * @author Vivianne Sousa
 * @since 28/08/2007
 */
public class ExibirInserirDebitoACobrarPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirDebitoACobrarPopup");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		InserirDebitoACobrarPopupActionForm inserirDebitoACobrarPopupActionForm = (InserirDebitoACobrarPopupActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");

		String idDebitoTipo = inserirDebitoACobrarPopupActionForm.getIdTipoDebito();
		
		  if (httpServletRequest.getParameter("objetoConsulta") != null
	                && httpServletRequest.getParameter("objetoConsulta").equalsIgnoreCase("1")) {
				
			  //pesquisar debito Tipo
				if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {
					DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
					if(debitoTipo != null){
						//[FS0008] - Verificar Exist�ncia de d�bito acobrar para o registro de atendimento
						
						inserirDebitoACobrarPopupActionForm.setIdTipoDebito(debitoTipo.getId().toString());
						inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
						inserirDebitoACobrarPopupActionForm.setValorTotalServico(
								Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
						httpServletRequest.setAttribute("corDebitoTipo","valor");
						httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
					}else{
						inserirDebitoACobrarPopupActionForm.setIdTipoDebito("");
						inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito("Tipo de D�bito Inexistente");
						httpServletRequest.setAttribute("corDebitoTipo",null);
						httpServletRequest.setAttribute("nomeCampo","idTipoDebito");
					}
				}
				
		  }
		

		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {

			inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito("");
			inserirDebitoACobrarPopupActionForm.setIdTipoDebito("");
			inserirDebitoACobrarPopupActionForm.setValorTotalServico("");

			if (sessao.getAttribute("imovelPesquisado") != null) {
				sessao.removeAttribute("imovelPesquisado");
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {
				DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(
						httpServletRequest.getParameter("idCampoEnviarDados"));		
				/*inserirDebitoACobrarPopupActionForm.setIdTipoDebito(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));*/
				inserirDebitoACobrarPopupActionForm.setIdTipoDebito(debitoTipo.getId().toString());
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(debitoTipo.getDescricao());
				inserirDebitoACobrarPopupActionForm.setValorTotalServico(
						Util.formatarMoedaReal(debitoTipo.getValorSugerido()));
						
				httpServletRequest.setAttribute("corDebitoTipo","valor");
				httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoDebito");
			}
		}
		
		
		boolean alterarValorSugeridoEmTipoDebito = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO,
						usuario);

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
				alterarValorSugeridoEmTipoDebito);

		if (inserirDebitoACobrarPopupActionForm.getValorTotalServico() == null
				|| inserirDebitoACobrarPopupActionForm.getValorTotalServico().equals("")) {

			httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito",
					true);

		}
		
		
		sessao.removeAttribute("informarImovel");
		
		return retorno;
	}
}