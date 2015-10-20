/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição da tela de gerar arquivo texto de faturas agrupadas
 * 
 * @author Amelia Pessoa
 * @created 12/06/2012
 */
public class ExibirGerarArquivoFaturasAgrupadasAction extends GcomAction {

	private String MSG_CLIENTE_INEXISTENTE =  "Código do Cliente inexistente.";
			
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno e seta o mapeamento
		ActionForward retorno = actionMapping
				.findForward("gerarArquivoFaturasAgrupadas");

		//Form
		ExibirGerarArquivoFaturasAgrupadasActionForm 
			form = (ExibirGerarArquivoFaturasAgrupadasActionForm) actionForm;
	
		limparPesquisasAnteriores(form, httpServletRequest);
		
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		//1 = Cliente Responsavel	
		//2 = Cliente Responsavel Inicial
		//3 = Cliente Responsavel Final
		if (objetoConsulta!=null && (objetoConsulta.equals("1") || 
				objetoConsulta.equals("2") || objetoConsulta.equals("3"))){
			pesquisarCliente(Integer.parseInt(objetoConsulta), form, httpServletRequest);
		}
		
		return retorno;
	}

	
	/**
	 * Método auxiliar para pesquisar o cliente
	 * 1 = Cliente Responsavel	
	 * 2 = Cliente Responsavel Inicial
	 * 3 = Cliente Responsavel Final
	 * 
	 * @param tipoCliente
	 * @param form
	 * @param httpServletRequest 
	 */
	private void pesquisarCliente(int tipoCliente, ExibirGerarArquivoFaturasAgrupadasActionForm form, HttpServletRequest httpServletRequest) {
		String idCliente=null;
		if (tipoCliente==1){
			idCliente = form.getClienteResponsavelId();
		} else if (tipoCliente==2){
			idCliente = form.getClienteResponsavelInicialId();
		} else {
			idCliente = form.getClienteResponsavelFinalId();
		}
		
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idCliente));
		
		Collection<Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		
		if(cliente!=null){
			if (tipoCliente==1){
				form.setClienteResponsavelNome(cliente.getNome());				
			} else if (tipoCliente==2){
				form.setClienteResponsavelInicialNome(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo", "clienteResponsavelFinalId");
			} else {
				form.setClienteResponsavelFinalNome(cliente.getNome());
			}
		} else {
			if (tipoCliente==1){
				form.setClienteResponsavelId(null);
				form.setClienteResponsavelNome(MSG_CLIENTE_INEXISTENTE);
				httpServletRequest.setAttribute("clienteInexistente","true");
				httpServletRequest.setAttribute("nomeCampo", "clienteResponsavelId");
			} else if (tipoCliente==2){
				form.setClienteResponsavelInicialId(null);
				form.setClienteResponsavelInicialNome(MSG_CLIENTE_INEXISTENTE);
				httpServletRequest.setAttribute("clienteInicialInexistente","true");
				httpServletRequest.setAttribute("nomeCampo", "clienteResponsavelInicialId");
			} else {
				form.setClienteResponsavelFinalId(null);
				form.setClienteResponsavelFinalNome(MSG_CLIENTE_INEXISTENTE);
				httpServletRequest.setAttribute("clienteFinalInexistente","true");
				httpServletRequest.setAttribute("nomeCampo", "clienteResponsavelFinalId");
			}
		}
	}
	
	/**
	 * Metodo auxiliar para limpar os dados de pesquisas anteriores
	 * @param form
	 * @param httpServletRequest 
	 */
	private void limparPesquisasAnteriores(
			ExibirGerarArquivoFaturasAgrupadasActionForm form, HttpServletRequest httpServletRequest) {
		if (form.getClienteResponsavelNome()!=null && form.getClienteResponsavelNome().equalsIgnoreCase(MSG_CLIENTE_INEXISTENTE)){
			form.setClienteResponsavelNome(null);
			httpServletRequest.removeAttribute("clienteInexistente");
		}
		if (form.getClienteResponsavelInicialNome()!=null && form.getClienteResponsavelInicialNome().equalsIgnoreCase(MSG_CLIENTE_INEXISTENTE)){
			form.setClienteResponsavelInicialNome(null);
			httpServletRequest.removeAttribute("clienteInicialInexistente");
		}
		if (form.getClienteResponsavelFinalNome()!=null && form.getClienteResponsavelFinalNome().equalsIgnoreCase(MSG_CLIENTE_INEXISTENTE)){
			form.setClienteResponsavelFinalNome(null);
			httpServletRequest.removeAttribute("clienteFinalInexistente");
		}
		httpServletRequest.removeAttribute("nomeCampo");
	}

}
