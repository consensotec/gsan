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
package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.gui.cobranca.ConsultarDebitoClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioConsultarDebitos;
import gcom.relatorio.cobranca.RelatorioConsultarDebitosCliente;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioDebitosConsultarAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String pesquisaCliente = httpServletRequest.getParameter("pesquisaCliente");
		String relatorioEndereco = httpServletRequest.getParameter("relatorioEndereco");
		
		Collection colecaoContaValores = new ArrayList();
		Collection colecaoDebitoACobrar = new ArrayList();
		Collection colecaoCreditoARealizar = new ArrayList();
		Collection colecaoGuiasPagamento = new ArrayList();
		String valorTotalDebitos = null;
		String valorTotalDebitosAtualizado = null;
		 
		 if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection)sessao.getAttribute("colecaoContaValores");
		 }
		 
		 if(sessao.getAttribute("colecaoDebitoACobrar") != null){
			 colecaoDebitoACobrar = (Collection)sessao.getAttribute("colecaoDebitoACobrar"); 
		 }
		 
		 if(sessao.getAttribute("colecaoCreditoARealizar") != null){
			 colecaoCreditoARealizar = (Collection)sessao.getAttribute("colecaoCreditoARealizar"); 
		 }
		 
		 if(sessao.getAttribute("colecaoGuiaPagamentoValores") != null){
			 colecaoGuiasPagamento = (Collection)sessao.getAttribute("colecaoGuiaPagamentoValores"); 
		 }
		 
		 if (sessao.getAttribute("valorTotalSemAcrescimo") != null) {
			 valorTotalDebitos = (String) sessao.getAttribute("valorTotalSemAcrescimo");
		 }
		 
		 if (sessao.getAttribute("valorTotalComAcrescimo") != null) {
			 valorTotalDebitosAtualizado = (String) sessao.getAttribute("valorTotalComAcrescimo");
		 }
		 
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (actionForm instanceof ConsultarImovelActionForm) {
			
			RelatorioConsultarDebitos relatorioConsultarDebitos = new RelatorioConsultarDebitos(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
			
			String endereco = fachada.pesquisarEndereco(new Integer(consultarImovelActionForm.getIdImovelDebitos()));

			Cliente cliente = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelDebitos()));

			relatorioConsultarDebitos.addParametro("idImovel", consultarImovelActionForm.getIdImovelDebitos());
			relatorioConsultarDebitos.addParametro("inscricao", consultarImovelActionForm.getMatriculaImovelDebitos());
			relatorioConsultarDebitos.addParametro("endereco", endereco);
			String cpfCnpjCliente = "";
			
			if (cliente != null) {
				relatorioConsultarDebitos.addParametro("clienteUsuario", cliente.getNome());
				if ( cliente.getCnpj() != null && !cliente.getCnpj().trim().equals("") ) {
					cpfCnpjCliente = cliente.getCnpjFormatado();
				} else if ( cliente.getCpf() != null && !cliente.getCpf().trim().equals("") ) {
					cpfCnpjCliente = cliente.getCpfFormatado();
				}
			} else {
				relatorioConsultarDebitos.addParametro("clienteUsuario", "");
			}
			relatorioConsultarDebitos.addParametro("colecaoContaValores", colecaoContaValores);
			relatorioConsultarDebitos.addParametro("colecaoDebitoACobrar", colecaoDebitoACobrar);
			relatorioConsultarDebitos.addParametro("colecaoCreditoARealizar", colecaoCreditoARealizar);
			relatorioConsultarDebitos.addParametro("colecaoGuiasPagamento", colecaoGuiasPagamento);
			relatorioConsultarDebitos.addParametro("cpfCnpjCliente", cpfCnpjCliente);
			relatorioConsultarDebitos.addParametro("clienteSuperior", false);
			relatorioConsultarDebitos.addParametro("valorTotalDebitos", valorTotalDebitos);
			relatorioConsultarDebitos.addParametro("valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);
			relatorioConsultarDebitos.addParametro("pesquisaCliente", pesquisaCliente);
			relatorioConsultarDebitos.addParametro("relatorioEndereco", relatorioEndereco);
			relatorioConsultarDebitos.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			retorno = processarExibicaoRelatorio(
					relatorioConsultarDebitos, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
			
		} else {
			
			RelatorioConsultarDebitosCliente relatorioConsultarDebitosCliente = new RelatorioConsultarDebitosCliente(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			ConsultarDebitoClienteActionForm consultarDebitoClienteActionForm = (ConsultarDebitoClienteActionForm) actionForm;
			
			ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) sessao.getAttribute("tipoRelacao");
			
			if (consultarDebitoClienteActionForm.getCodigoCliente() != null 
				&& !consultarDebitoClienteActionForm.getCodigoCliente().trim().equals("")) {
				relatorioConsultarDebitosCliente.addParametro("codigoCliente", consultarDebitoClienteActionForm.getCodigoCliente());
				relatorioConsultarDebitosCliente.addParametro("nomeCliente", consultarDebitoClienteActionForm.getNomeCliente());
				relatorioConsultarDebitosCliente.addParametro("cpfCnpj", consultarDebitoClienteActionForm.getCpfCnpj());
				relatorioConsultarDebitosCliente.addParametro("tipoRelacao", tipoRelacao);
				relatorioConsultarDebitosCliente.addParametro("clienteSuperior", false);
			} else {
				relatorioConsultarDebitosCliente.addParametro("codigoCliente", consultarDebitoClienteActionForm.getCodigoClienteSuperior());
				relatorioConsultarDebitosCliente.addParametro("nomeCliente", consultarDebitoClienteActionForm.getNomeClienteSuperior());
				relatorioConsultarDebitosCliente.addParametro("cpfCnpj", consultarDebitoClienteActionForm.getCpfCnpj());
				relatorioConsultarDebitosCliente.addParametro("tipoRelacao", null);
				relatorioConsultarDebitosCliente.addParametro("clienteSuperior", true);
			}
			
			relatorioConsultarDebitosCliente.addParametro("periodoReferenciaInicial", consultarDebitoClienteActionForm.getReferenciaInicial());
			relatorioConsultarDebitosCliente.addParametro("periodoReferenciaFinal",consultarDebitoClienteActionForm.getReferenciaFinal());
			relatorioConsultarDebitosCliente.addParametro("dataVencimentoInicial",	consultarDebitoClienteActionForm.getDataVencimentoInicial());
			relatorioConsultarDebitosCliente.addParametro("dataVencimentoFinal", consultarDebitoClienteActionForm.getDataVencimentoFinal());
			relatorioConsultarDebitosCliente.addParametro("colecaoContaValores", colecaoContaValores);
			relatorioConsultarDebitosCliente.addParametro("colecaoDebitoACobrar", colecaoDebitoACobrar);
			relatorioConsultarDebitosCliente.addParametro("colecaoCreditoARealizar", colecaoCreditoARealizar);
			relatorioConsultarDebitosCliente.addParametro("colecaoGuiasPagamento", colecaoGuiasPagamento);
			relatorioConsultarDebitosCliente.addParametro("valorTotalDebitos", valorTotalDebitos);
			relatorioConsultarDebitosCliente.addParametro("valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);
			relatorioConsultarDebitosCliente.addParametro("pesquisaCliente", pesquisaCliente);
			relatorioConsultarDebitosCliente.addParametro("relatorioEndereco", relatorioEndereco);
			
			if (String.valueOf(TarefaRelatorio.TIPO_CSV).equals(tipoRelatorio)) {
				tipoRelatorio = String.valueOf(TarefaRelatorio.TIPO_CSV);
			} else {
				tipoRelatorio = String.valueOf(TarefaRelatorio.TIPO_PDF);
			}

			relatorioConsultarDebitosCliente.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			retorno = processarExibicaoRelatorio(
					relatorioConsultarDebitosCliente, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
