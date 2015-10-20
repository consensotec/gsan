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

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManterGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection guiasPagamento = (Collection) sessao
				.getAttribute("guiasPagamentos");

		ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;

		String[] registrosRemocao = manterGuiaPagamentoActionForm
				.getIdRegistrosRemocao();
		String idImovel = manterGuiaPagamentoActionForm.getIdImovel();
		String idCliente = manterGuiaPagamentoActionForm.getCodigoCliente();

		GuiaPagamento guiaPagamento = new GuiaPagamento();

		Cliente cliente = new Cliente();

		if (idCliente != null && !idCliente.equals("")) {
			cliente.setId(new Integer(idCliente));

		}

		guiaPagamento.setCliente(cliente);

		Imovel imovel = new Imovel();

		ImovelCobrancaSituacao imovelCobrancaSituacao = null;

		if (idImovel != null && !idImovel.equals("")) {
			imovel.setId(new Integer(idImovel));
			imovelCobrancaSituacao = (ImovelCobrancaSituacao) sessao
					.getAttribute("imovelCobrancaSituacao");
		}

		guiaPagamento.setImovel(imovel);

        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usu�rio logado para passar no met�do de inserir guia de pagamento 
         * para verificar se o usu�rio tem abrang�ncia para inserir a guia de pagamento
         * para o im�vel caso a guia de pagamentoseja para o im�vel.
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        guiaPagamento.setUsuario(usuarioLogado);
        fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento,registrosRemocao, imovelCobrancaSituacao,usuarioLogado);
		//fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento,registrosRemocao, imovelCobrancaSituacao);

		sessao.removeAttribute("imovelCobrancaSituacao");
		sessao.removeAttribute("guiasPagamentos");
		sessao.removeAttribute("ManterGuiaPagamentoActionForm");

		if (idImovel != null && !idImovel.equals("")) {

			montarPaginaSucesso(httpServletRequest, registrosRemocao.length
					+ " Guia(s) de Pagamento do im�vel " + idImovel
					+ " cancelada(s) com sucesso.",
					"Realizar outro Cancelamento de Guia de Pagamento",
					"exibirManterGuiaPagamentoAction.do?menu=sim");

		}

		if (idCliente != null && !idCliente.equals("")) {

			montarPaginaSucesso(httpServletRequest, registrosRemocao.length
					+ " Guia(s) de Pagamento do cliente " + idCliente
					+ " cancelada(s) com sucesso.",
					"Realizar outro Cancelamento de Guia de Pagamento",
					"exibirManterGuiaPagamentoAction.do?menu=sim");

		}

		return retorno;
	}
}
