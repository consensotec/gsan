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

import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ConsultarDebitoAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a a��o de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		 HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		ConsultarDebitoActionForm consultarDebitoActionForm = (ConsultarDebitoActionForm) actionForm;

		String codigoImovel = null;

		if (httpServletRequest.getParameter("codigoImovel") != null) {

			codigoImovel = httpServletRequest.getParameter("codigoImovel");

		} else {

			codigoImovel = consultarDebitoActionForm.getCodigoImovel();

		}
        	
		String codigoClienteSuperior = consultarDebitoActionForm
			.getCodigoClienteSuperior();
		
		String codigoCliente = consultarDebitoActionForm
				.getCodigoCliente();

		Integer tipoRelacao = null;

		if (codigoCliente != null && !codigoCliente.trim().equals("")) {
		
		if (consultarDebitoActionForm.getTipoRelacao() != null
					&& !consultarDebitoActionForm.getTipoRelacao().trim()
							.equals("-1")) {
				tipoRelacao = new Integer(consultarDebitoActionForm
						.getTipoRelacao());
			} else {
				tipoRelacao = null;
			}

		}

		// Verifica se o usu�rio n�o digitou c�digo do cliente nem a matricula
		// do imovel
		if ((codigoImovel == null || codigoImovel.equals(""))
				&& (codigoCliente == null || codigoCliente.equals(""))
				&& (codigoClienteSuperior == null || codigoClienteSuperior
						.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_cliente");
		}

		boolean peloMenosUmParametroInformado = false;

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {

			codigoImovel = codigoImovel.trim();

			// Seta o retorno para a p�gina que vai detalhar o imovel
			retorno = actionMapping.findForward("exibirDebitoImovel");

			peloMenosUmParametroInformado = true;
		} else if ((codigoCliente != null && !codigoCliente.trim().equals(""))) {
			codigoCliente = codigoCliente.trim();

			// Seta o retorno para a p�gina que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;

		} else if ((codigoClienteSuperior != null && !codigoClienteSuperior
				.trim().equals(""))) {
			
			codigoClienteSuperior = codigoClienteSuperior.trim();
			
			// Seta o retorno para a p�gina que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;
			
		}

		// ClienteRelacaoTipo tipoRelacaoSelecionada = null;

		// Verifica o tipo de rela��o
		if (tipoRelacao != null && !tipoRelacao.equals("")
				&& tipoRelacao != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

			filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
					tipoRelacao));

			Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada
					.pesquisar(filtroClienteRelacaoTipo,
							ClienteRelacaoTipo.class.getName());

			if (collectionClienteRelacaoTipo != null
					&& collectionClienteRelacaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.collectionClienteRelacaoTipo_inexistente",
						null, "id");
			}

			/* tipoRelacaoSelecionada = */sessao.setAttribute("tipoRelacao", collectionClienteRelacaoTipo
					.iterator().next());
		} else {
			tipoRelacao = null;
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.selecionar.nenhum_parametro_informado");
		}

		if (httpServletRequest.getParameter("ehPopup") != null) {
			sessao.setAttribute("ehPopup", "true");
		}

		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaDebito") != null) {

			httpServletRequest.setAttribute("caminhoRetornoTelaConsultaDebito",
					httpServletRequest
							.getParameter("caminhoRetornoTelaConsultaDebito"));

		}

		return retorno;
	}
}
