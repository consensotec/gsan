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
package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.gui.arrecadacao.pagamento.ConsultarPagamentoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioPagamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioPagamentoAction extends
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

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarPagamentoActionForm consultarPagamentoActionForm = (ConsultarPagamentoActionForm) actionForm;

//		Collection colecaoPagamentos = (Collection) sessao
//				.getAttribute("colecaoPagamentosLocalidade");

		// Inicio da parte que vai mandar os parametros para o relat�rio

		Pagamento pagamentoParametrosInicial = new Pagamento();
		Pagamento pagamentoParametrosFinal = new Pagamento();

		String idImovel = null;
		String idCliente = null;
		String clienteRelacaoTipo = null;
		String idAvisoBancario = null;
		String idMovimentoArrecadador = null;
		String localidadeInicial = null;
		String localidadeFinal = null;
		String periodoArrecadacaoInicial = null;
		String periodoArrecadacaoFinal = null;
		String periodoPagamentoInicial = null;
		String periodoPagamentoFinal = null;
		String dataPagamentoInicial = null;
		String dataPagamentoFinal = null;
		String[] idsPagamentoSituacao = null;
		String[] idsArrecadacaoForma = null;
		String[] idsDocumentoTipo = null;
		String[] idsDebitoTipo = null;
		String opcaoPagamento = null;
        String valorPagamentoInicial = 
            consultarPagamentoActionForm.getValorPagamentoInicial();
        
        String valorPagamentoFinal = 
            consultarPagamentoActionForm.getValorPagamentoFinal();
		
		if (sessao.getAttribute("idImovelPrincipalAba") != null && !sessao.getAttribute("idImovelPrincipalAba").equals("")) {
			idImovel = (String) sessao.getAttribute("idImovelPrincipalAba");
			opcaoPagamento = "ambos";
		} else {
			idImovel = consultarPagamentoActionForm.getIdImovel();
			idCliente = consultarPagamentoActionForm.getIdCliente();
			clienteRelacaoTipo = consultarPagamentoActionForm
					.getClienteRelacaoTipo();
			idAvisoBancario = consultarPagamentoActionForm
					.getIdAvisoBancario();
			idMovimentoArrecadador = consultarPagamentoActionForm
					.getIdArrecadador();
			localidadeInicial = consultarPagamentoActionForm
					.getLocalidadeInicial();
			localidadeFinal = consultarPagamentoActionForm
					.getLocalidadeFinal();
			periodoArrecadacaoInicial = consultarPagamentoActionForm
					.getPeriodoArrecadacaoInicio();
			periodoArrecadacaoFinal = consultarPagamentoActionForm
					.getPeriodoArrecadacaoFim();
			periodoPagamentoInicial = consultarPagamentoActionForm
					.getPeriodoPagamentoInicio();
			periodoPagamentoFinal = consultarPagamentoActionForm
					.getPeriodoPagamentoFim();
			dataPagamentoInicial = consultarPagamentoActionForm
					.getDataPagamentoInicio();
			dataPagamentoFinal = consultarPagamentoActionForm
					.getDataPagamentoFim();
			idsPagamentoSituacao = consultarPagamentoActionForm
					.getPagamentoSituacao();
			idsArrecadacaoForma = consultarPagamentoActionForm
					.getArrecadacaoForma();
			idsDocumentoTipo = consultarPagamentoActionForm
					.getDocumentoTipo();
			idsDebitoTipo = consultarPagamentoActionForm.getDebitoTipoSelecionados();
			opcaoPagamento = consultarPagamentoActionForm.getOpcaoPagamento();
		}
		
		
		//caso venha da tela de manter pagamento o padr�o ser� consultar o atual
		if(opcaoPagamento == null){
			opcaoPagamento = "atual";
		}

		// seta os parametros que ser�o mostrados no relat�rio
		if (periodoArrecadacaoInicial != null
				&& !periodoArrecadacaoInicial.equals("")) {
			pagamentoParametrosInicial.setAnoMesReferenciaPagamento(Util
					.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial));
			pagamentoParametrosFinal.setAnoMesReferenciaPagamento(Util
					.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal));
		}
		if (dataPagamentoInicial != null && !dataPagamentoInicial.equals("")) {
			pagamentoParametrosInicial.setDataPagamento(Util
					.converteStringParaDate(dataPagamentoInicial));
			pagamentoParametrosFinal.setDataPagamento(Util
					.converteStringParaDate(dataPagamentoFinal));
		}

		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
		RelatorioPagamento relatorioPagamento = new RelatorioPagamento((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (sessao.getAttribute("idImovelPrincipalAba") != null && !sessao.getAttribute("idImovelPrincipalAba").equals("")) {
			relatorioPagamento.addParametro("relatorioConsultarImovel", true);
		}
		
		if (sessao.getAttribute("avisoBancarioHelper") != null) {
			relatorioPagamento.addParametro("relatorioConsultarAvisoBancario", true);
			relatorioPagamento.addParametro("avisoBancarioHelper", sessao.getAttribute("avisoBancarioHelper"));
			idAvisoBancario = (String) sessao.getAttribute("idAvisoBancario"); 
		}
		relatorioPagamento.addParametro("pagamentoParametrosInicial",
				pagamentoParametrosInicial);
		relatorioPagamento.addParametro("pagamentoParametrosFinal",
				pagamentoParametrosFinal);

		// Par�metros de Pesquisa
		relatorioPagamento.addParametro("idImovel", idImovel);
		relatorioPagamento.addParametro("idCliente", idCliente);
		relatorioPagamento.addParametro("clienteRelacaoTipo",
				clienteRelacaoTipo);
		relatorioPagamento.addParametro("idAvisoBancario", idAvisoBancario);
		relatorioPagamento.addParametro("idMovimentoArrecadador",
				idMovimentoArrecadador);
		relatorioPagamento.addParametro("localidadeInicial", localidadeInicial);
		relatorioPagamento.addParametro("localidadeFinal", localidadeFinal);
		relatorioPagamento.addParametro("periodoArrecadacaoInicial",
				periodoArrecadacaoInicial);
		relatorioPagamento.addParametro("periodoArrecadacaoFinal",
				periodoArrecadacaoFinal);
		relatorioPagamento.addParametro("periodoPagamentoInicial",
				periodoPagamentoInicial);
		relatorioPagamento.addParametro("periodoPagamentoFinal",
				periodoPagamentoFinal);
		relatorioPagamento.addParametro("dataPagamentoInicial",
				Util.converteStringParaDate(dataPagamentoInicial));
		relatorioPagamento.addParametro("dataPagamentoFinal",
				Util.converteStringParaDate(dataPagamentoFinal));
		relatorioPagamento.addParametro("idsPagamentoSituacao",
				idsPagamentoSituacao);
		relatorioPagamento.addParametro("idsArrecadacaoForma",
				idsArrecadacaoForma);
		relatorioPagamento.addParametro("idsDocumentoTipo", idsDocumentoTipo);
		relatorioPagamento.addParametro("idsDebitoTipo", idsDebitoTipo);
		relatorioPagamento.addParametro("opcaoPagamento",opcaoPagamento);
        
        relatorioPagamento.addParametro( "valorPagamentoInicial", valorPagamentoInicial );
        relatorioPagamento.addParametro( "valorPagamentoFinal", valorPagamentoFinal );

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioPagamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioPagamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}

