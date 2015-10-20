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
package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.ConsultarDebitoClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioConsultarDebitosResponsavel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Cesar Medeiros
 * @created 16/04/2015
 */
public class GerarRelatorioDebitosResumidoResponsavelConsultarAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		ConsultarDebitoClienteActionForm form = (ConsultarDebitoClienteActionForm) actionForm;

		
		SistemaParametro sistemaParametro = Fachada.getInstancia().
				pesquisarParametrosDoSistema();
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoContaValores = new ArrayList();
		Collection colecaoDebitoACobrar = new ArrayList();
		Collection colecaoCreditoARealizar = new ArrayList();
		Collection colecaoGuiasPagamento = new ArrayList();
		
		String valorTotalDebitos = null;
		String valorTotalDebitosAtualizado = null;				
		
		String periodoReferenciaInicialDebito = form.getReferenciaInicial();
		String periodoReferenciaFinalDebito = form.getReferenciaFinal();
		String dataVencimentoInicial = form.getDataVencimentoInicial();
		String dataVencimentoFinal = form.getDataVencimentoFinal();		
		
		String cpfCnpj = form.getCpfCnpj();
		String clienteTipo = form.getClienteTipo();
		
		String codigoClienteSuperior = form.getCodigoClienteSuperior();
		
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, codigoClienteSuperior));
		Collection <Cliente> colecaoCliente = Fachada.getInstancia().pesquisar(filtroCliente, Cliente.class.getName());
		
		Cliente cliente = (Cliente)Util.retonarObjetoDeColecao(colecaoCliente);		
		String nomeCliente = cliente.getNome();
		
		ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) sessao.getAttribute("tipoRelacao");		
		
		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection) sessao
					.getAttribute("colecaoContaValores");
		}

		if (sessao.getAttribute("colecaoDebitoACobrar") != null) {
			colecaoDebitoACobrar = (Collection) sessao
					.getAttribute("colecaoDebitoACobrar");
		}

		if (sessao.getAttribute("colecaoCreditoARealizar") != null) {
			colecaoCreditoARealizar = (Collection) sessao
					.getAttribute("colecaoCreditoARealizar");
		}

		if (sessao.getAttribute("colecaoGuiaPagamentoValores") != null) {
			colecaoGuiasPagamento = (Collection) sessao
					.getAttribute("colecaoGuiaPagamentoValores");
		}

		if (sessao.getAttribute("valorTotalSemAcrescimo") != null) {
			valorTotalDebitos = (String) sessao
					.getAttribute("valorTotalSemAcrescimo");
		}

		if (sessao.getAttribute("valorTotalComAcrescimo") != null) {
			valorTotalDebitosAtualizado = (String) sessao
					.getAttribute("valorTotalComAcrescimo");
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
	
		RelatorioConsultarDebitosResponsavel relatorioConsultarDebitosResponsavel = new RelatorioConsultarDebitosResponsavel(
				(Usuario) sessao.getAttribute("usuarioLogado"));
		
		//Colecao valores conta
		relatorioConsultarDebitosResponsavel.addParametro("colecaoContaValores", colecaoContaValores);
		
		//Colecao valores debito
		relatorioConsultarDebitosResponsavel.addParametro("colecaoDebitoACobrar", colecaoDebitoACobrar);
		//Colecao valor credito a realizar
		relatorioConsultarDebitosResponsavel.addParametro("colecaoCreditoARealizar", colecaoCreditoARealizar);
		
		//Colecao valores Guia pagamento
		relatorioConsultarDebitosResponsavel.addParametro("colecaoGuiaPagamentoValores", colecaoGuiasPagamento);
		
		//Valor total sem acrescimo
		relatorioConsultarDebitosResponsavel.addParametro("valorTotal", valorTotalDebitos);
		
		//Valor total sem acrescimo
		relatorioConsultarDebitosResponsavel.addParametro("valorTotalAtualizado", valorTotalDebitosAtualizado);		
		
		//Peridodo referencia inicial do debito 
		if(periodoReferenciaInicialDebito != null){
			relatorioConsultarDebitosResponsavel.addParametro("periodoReferenciaInicialDebito", periodoReferenciaInicialDebito);
		}
		//Peridodo referencia final do debito 
		if(periodoReferenciaFinalDebito != null){
			relatorioConsultarDebitosResponsavel.addParametro("periodoReferenciaFinalDebito", periodoReferenciaFinalDebito);
		}
		//Data vencimento inicial 
		if(dataVencimentoInicial != null){
			relatorioConsultarDebitosResponsavel.addParametro("dataVencimentoInicial", dataVencimentoInicial);
		}
		//Data vencimento final 
		if(dataVencimentoFinal != null){
			relatorioConsultarDebitosResponsavel.addParametro("dataVencimentoFinal", dataVencimentoFinal);
		}
		//Nome Cliente
		if(nomeCliente != null){
			relatorioConsultarDebitosResponsavel.addParametro("nomeCliente", nomeCliente);
		}
		//Tipo Relacao
		if(tipoRelacao != null){
			relatorioConsultarDebitosResponsavel.addParametro("tipoRelacao", tipoRelacao);
		}
		//CPF E CNPJ
		if(cpfCnpj != null){
			relatorioConsultarDebitosResponsavel.addParametro("cpfCnpj", cpfCnpj);
		}
		//Codigo
		if(codigoClienteSuperior != null){
			relatorioConsultarDebitosResponsavel.addParametro("codigoCliente", codigoClienteSuperior);
		}

		//Cliente Tipo
		if(clienteTipo != null){
			relatorioConsultarDebitosResponsavel.addParametro("clienteTipo", clienteTipo);
		}
		//Tipo relatorio
		if (tipoRelatorio == null){
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
	
		relatorioConsultarDebitosResponsavel.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorioConsultarDebitosResponsavel,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
