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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteVirtual;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1260] Atualizar cliente virtual
 * 
 * @author Arthur Carvalho
 * @created 16/12/2011
 */
public class ExibirFiltrarClienteVirtualAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("exibirFiltrarClienteVirtualAction");

		FiltrarClienteVirtualActionForm form = (FiltrarClienteVirtualActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Collection<ClienteVirtual> clientevirtuais = null;
		
		if ( httpServletRequest.getParameter("limparFormulario") != null && httpServletRequest.getParameter("limparFormulario").equals("sim") ) {
			form.setPeriodoAtendimentoInicial("");
			form.setPeriodoAtendimentoFinal("");
			
			//limpar a paginacao
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
								0);
		}
		
		Date periodoInicial = null;
		if ( form.getPeriodoAtendimentoInicial() != null && !form.getPeriodoAtendimentoInicial().equals("") ) {
			periodoInicial = Util.converteStringParaDate(form.getPeriodoAtendimentoInicial());
		}
		
		Date periodoFinal = null;
		if ( form.getPeriodoAtendimentoFinal() != null && !form.getPeriodoAtendimentoFinal().equals("") ) {
			periodoFinal = Util.converteStringParaDate(form.getPeriodoAtendimentoFinal());
		}
		
		
		if ( periodoInicial != null && periodoFinal != null ) {
			// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
			//Integer totalRegistros = fachada
				///	.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);
			Integer totalRegistros = (Integer) fachada.pesquisarQuantidadeClienteVirtual(periodoInicial, periodoFinal);
	
			if (totalRegistros == null || totalRegistros <= 0 ) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", "exibirFiltrarClienteVirtualAction.do?limparFormulario=sim", null, new String[0] );
			}
			// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);
	
			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
			// da pesquisa que est� no request
			clientevirtuais = fachada.pesquisarClienteVirtual(periodoInicial, periodoFinal, (Integer) httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));
		    
			
			
			httpServletRequest.setAttribute("clienteVirtuais", clientevirtuais);
		}
		
		
		
		
		
		return retorno;
	}
	
}