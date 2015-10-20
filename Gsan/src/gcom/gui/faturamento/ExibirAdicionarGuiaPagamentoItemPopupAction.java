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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibir Adicionar Guia Pagamento Item
 * 
 * @author Fl�vio Leonardo
 * @since 04/11/2008
 */
public class ExibirAdicionarGuiaPagamentoItemPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAdicionarGuiaPagamentoItemPopup");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		AdicionarGuiaPagamentoItemActionForm form = (AdicionarGuiaPagamentoItemActionForm) actionForm;
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		String matriculaImovel = (String) httpServletRequest.getParameter("matriculaImovel");
		
		if(limparForm != null && !limparForm.equals("")){
			form.setIdTipoDebito("");
			form.setValorTotalServico("");
			form.setDescricaoTipoDebito("");
			form.setMatriculaImovel("");
		}
		
		if (matriculaImovel != null && !matriculaImovel.equals("")){
			form.setMatriculaImovel(matriculaImovel);
		}

		String idDebitoTipo = form.getIdTipoDebito();
		
		if(httpServletRequest.getParameter("idCampoEnviarDados") != null){
			idDebitoTipo = httpServletRequest.getParameter("idCampoEnviarDados");
		}
		
		if(httpServletRequest.getParameter("idDebitoTipo") != null){
			idDebitoTipo = httpServletRequest.getParameter("idDebitoTipo");
			httpServletRequest.setAttribute("desabilitaIdDebito","sim");
			sessao.removeAttribute("colecaoGuiaDebitoTipo");
		}
		
		DebitoTipo debitoTipo = null;
		if (idDebitoTipo != null && !idDebitoTipo.equals("")) {
				debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);		
				/*inserirDebitoACobrarPopupActionForm.setIdTipoDebito(
					httpServletRequest.getParameter("idCampoEnviarDados"));
				
				inserirDebitoACobrarPopupActionForm.setDescricaoTipoDebito(
					httpServletRequest.getParameter("descricaoCampoEnviarDados"));*/
				if(debitoTipo != null){
					form.setIdTipoDebito(debitoTipo.getId().toString());
					form.setDescricaoTipoDebito(debitoTipo.getDescricao());
					form.setValorTotalServico(
							Util.formatarMoedaReal(debitoTipo.getValorSugerido()));

					httpServletRequest.setAttribute("corDebitoTipo","valor");
					httpServletRequest.setAttribute("nomeCampo","valorTotalServico");
				}else{
					form.setDescricaoTipoDebito("Debito Tipo Inexistente");
					httpServletRequest.setAttribute("corDebitoTipo","exception");
				}
				
				sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoDebito");
		}
		
		
		//VALOR SUGERIDO
		boolean alterarValorSugeridoEmTipoDebito = Fachada.getInstancia()
		.verificarPermissaoEspecial(PermissaoEspecial.ALTERAR_VALOR_SUGERIDO_EM_TIPO_DEBITO, usuario);
		
		if(debitoTipo != null && debitoTipo.getValorSugerido() == null){
			alterarValorSugeridoEmTipoDebito = true;
		}

		httpServletRequest.setAttribute("alterarValorSugeridoEmTipoDebito", alterarValorSugeridoEmTipoDebito);
		
		
		/*
		 * CONTAS PARA SELE��O
		 * Caso o tipo de d�bito informado seja "Pagamento Antecipado de Conta"
		 */
		Integer idImovel = null;
		if (form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			idImovel = Integer.valueOf(form.getMatriculaImovel());
		}
		
		Integer idDebitoTipoInteger = null;
		if (debitoTipo != null){
			idDebitoTipoInteger = debitoTipo.getId();
		}
		
		Collection colecaoContaParaPagamentoParcial = fachada.obterContasParaPagamentoParcial(
		idImovel, idDebitoTipoInteger);
		
		if (colecaoContaParaPagamentoParcial != null &&
			!colecaoContaParaPagamentoParcial.isEmpty()){
			
			sessao.setAttribute("colecaoContaParaPagamentoParcial", colecaoContaParaPagamentoParcial);
		}
		else{
			
			sessao.removeAttribute("colecaoContaParaPagamentoParcial");
		}
		
		
		
		
		sessao.setAttribute("caminhoRetornoOpener", "exibirInserirGuiaPagamentoAction.do");
		
		httpServletRequest.setAttribute("nomeCampo", "idTipoDebito");
		
		return retorno;
	}
}