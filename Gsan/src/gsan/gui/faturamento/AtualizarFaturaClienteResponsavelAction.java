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
package gsan.gui.faturamento;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Fatura;
import gsan.faturamento.conta.FaturaItem;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  AdicionarFaturaClienteResponsavelContaPopupAction
 * 
 * @author Fl�vio Leonardo
 * @created 25/04/2006
 */
public class AtualizarFaturaClienteResponsavelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Inst�ncia do formul�rio que est� sendo utilizado
		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;
		
		Fatura fatura = (Fatura)sessao.getAttribute("fatura");
		
		Collection<FaturaItem> colecaoFaturasItem = (Collection)sessao.getAttribute("colecaoFaturaItem");
		
		Integer quantidadeTotalFaturas = (Integer) sessao.getAttribute("quantidadeTotalFaturas");
		
		/* UC0871 [FS0009]*/
		if(quantidadeTotalFaturas != null && quantidadeTotalFaturas < 1){
			throw new ActionServletException("atencao.fatura.cliente.responsavel.conter.uma");
		}
		
		Collection<FaturaItem> colecaoFaturaItemRemover = (Collection)sessao.getAttribute("colecaoFaturaItemRemover");
		
		fachada.removerFaturaItemFaturaItemHistorico(colecaoFaturaItemRemover, usuario);
		
		int qtdInseridos = fachada.inserirFaturaItemFaturaItemHistorico(colecaoFaturasItem, usuario);
		
		BigDecimal valorTotal = fachada.somarValorFaturasItemFatura(fatura);
		
		Date dataVencimento = fachada.vencimentoFaturasItemFatura(fatura);
		
		fatura.setVencimento(dataVencimento);
		fatura.setDebito(valorTotal);
		
		fachada.alterarVencimentoFaturaFaturaItem(fatura);
		
		Cliente cliente = null;
		
		if(sessao.getAttribute("cliente") != null){
			cliente = (Cliente) sessao.getAttribute("cliente");
		}else{
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteId()));
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			cliente = (Cliente)Util.retonarObjetoDeColecao(colecaoCliente);
		}
		
		String qtdRemovidos = "0";
		if(colecaoFaturaItemRemover != null){
			qtdRemovidos = colecaoFaturaItemRemover.size() + "";
		}
		
		montarPaginaSucesso(httpServletRequest, "Foram inseridas "
				+ qtdInseridos + " contas e removidas " 
				+ qtdRemovidos
				+ " contas da fatura do cliente "
				+ cliente.getNome()
				+ " da refer�ncia "
				+ form.getMesAno(),
				"Realizar outra Manuten��o de Fatura Cliente Respons�vel",
				"exibirFiltrarFaturaClienteResponsavelAction.do?menu=sim");

		return retorno;

	}
}
