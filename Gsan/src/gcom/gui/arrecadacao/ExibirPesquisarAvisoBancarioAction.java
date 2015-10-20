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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirPesquisarAvisoBancarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarAvisoBancario");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarAvisoBancarioActionForm pesquisarAvisoBancarioActionForm = (PesquisarAvisoBancarioActionForm) actionForm;

		sessao.removeAttribute("tipoPesquisa");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaArrecadador");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaContaBancaria");
		sessao.removeAttribute("caminhoRetornoTelaPesquisaMovimentoArrecadador");

		if (httpServletRequest.getParameter("limparForm") != null) {
//			if (httpServletRequest.getParameter("limparForm").equals("1")) {
				
				pesquisarAvisoBancarioActionForm.setIdArrecadador("");
				pesquisarAvisoBancarioActionForm.setDataLancamentoInicio("");
				pesquisarAvisoBancarioActionForm.setDataLancamentoFim("");
				pesquisarAvisoBancarioActionForm.setTipoAviso("");
				pesquisarAvisoBancarioActionForm.setPeriodoArrecadacaoInicio("");
				pesquisarAvisoBancarioActionForm.setPeriodoArrecadacaoFim("");
				pesquisarAvisoBancarioActionForm.setDataPrevisaoCreditoDebitoFim("");
				pesquisarAvisoBancarioActionForm.setDataPrevisaoCreditoDebitoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorPrevistoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorPrevistoFim("");
				pesquisarAvisoBancarioActionForm.setDataRealizacaoCreditoDebitoFim("");
				pesquisarAvisoBancarioActionForm.setDataRealizacaoCreditoDebitoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorRealizadoInicio("");
				pesquisarAvisoBancarioActionForm.setIntervaloValorRealizadoFim("");
				pesquisarAvisoBancarioActionForm.setArrecadadorNome("");
				pesquisarAvisoBancarioActionForm.setIdConta("");
				pesquisarAvisoBancarioActionForm.setIdBancoConta("");
				pesquisarAvisoBancarioActionForm.setNumeroConta("");
				pesquisarAvisoBancarioActionForm.setCodigoBanco("");
				pesquisarAvisoBancarioActionForm.setCodigoRemessa("");
				pesquisarAvisoBancarioActionForm.setIdentificacaoServico("");
				pesquisarAvisoBancarioActionForm.setNumeroSequencial("");
				pesquisarAvisoBancarioActionForm.setIdMovimento("");
				
//				pesquisarAvisoBancarioActionForm.reset(actionMapping,
//						httpServletRequest);
				sessao.removeAttribute("PesquisarAvisoBancarioActionForm");
//			}
		}

		// -------Inicio da Parte que trata do c�digo quando o usu�rio tecla enter
		String idDigitadoEnterArrecadador = (String) pesquisarAvisoBancarioActionForm
			.getIdArrecadador();
		
		/*Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		if(requestMap.get("idArrecadador") != null){
			sessao.setAttribute("idArrecadador","idArrecadador");
		}else if ((httpServletRequest.getParameter("tipoConsulta") != null)
				&& (!httpServletRequest.getParameter("tipoConsulta")
						.equals("arrecadador"))) {

			sessao.setAttribute("idArrecadador","idArrecadador");
		}

		if(requestMap.get("idArrecadador") == null && sessao.getAttribute("idArrecadador") == null && idDigitadoEnterArrecadador != null){
			pesquisarAvisoBancarioActionForm.setIdArrecadador("");
			pesquisarAvisoBancarioActionForm.setArrecadadorNome("");
		}else*/if (idDigitadoEnterArrecadador != null
				&& !idDigitadoEnterArrecadador.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterArrecadador) > 0) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE,
					idDigitadoEnterArrecadador));

			filtroArrecadador
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection arrecadadorEncontrado = fachada.pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			if (arrecadadorEncontrado != null
					&& !arrecadadorEncontrado.isEmpty()) {
				// O arrecadador foi encontrado
				pesquisarAvisoBancarioActionForm.setIdArrecadador(""
						+ ((Arrecadador) ((List) arrecadadorEncontrado)
								.get(0)).getCodigoAgente());
				pesquisarAvisoBancarioActionForm
						.setArrecadadorNome(""
								+ ((Arrecadador) ((List) arrecadadorEncontrado)
										.get(0)).getCliente().getNome());
				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "true");
				httpServletRequest.setAttribute("nomeCampo", "tipoRemessa");

			} else {

				pesquisarAvisoBancarioActionForm.setIdArrecadador("");
				pesquisarAvisoBancarioActionForm
				.setArrecadadorNome("ARRECADADOR INEXISTENTE");

				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "exception");

			}
		}
	 	// -------Fim da Parte que trata do c�digo quando o usu�rio tecla enter
		
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"contaBancaria")) {

				pesquisarAvisoBancarioActionForm.setIdConta(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarAvisoBancarioActionForm
						.setIdBancoConta(httpServletRequest
								.getParameter("descricaoCampoEnviarDados1"));

				pesquisarAvisoBancarioActionForm
						.setIdAgenciaConta(httpServletRequest
								.getParameter("descricaoCampoEnviarDados2"));

				pesquisarAvisoBancarioActionForm
						.setNumeroConta(httpServletRequest
								.getParameter("descricaoCampoEnviarDados3"));

			}

			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"movimentoArrecadador")) {

				pesquisarAvisoBancarioActionForm
						.setIdMovimento(httpServletRequest
								.getParameter("idCampoEnviarDados"));

				pesquisarAvisoBancarioActionForm
						.setCodigoBanco(httpServletRequest
								.getParameter("descricaoCampoEnviarDados1"));

				pesquisarAvisoBancarioActionForm
						.setCodigoRemessa(httpServletRequest
								.getParameter("descricaoCampoEnviarDados2"));

				pesquisarAvisoBancarioActionForm
						.setIdentificacaoServico(httpServletRequest
								.getParameter("descricaoCampoEnviarDados3"));

				pesquisarAvisoBancarioActionForm
						.setNumeroSequencial(httpServletRequest
								.getParameter("descricaoCampoEnviarDados4"));

			}
			if ((httpServletRequest.getParameter("tipoConsulta") != null)
					&& (httpServletRequest.getParameter("tipoConsulta")
							.equals("arrecadador"))) {

				pesquisarAvisoBancarioActionForm
						.setArrecadadorNome(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				pesquisarAvisoBancarioActionForm
						.setIdArrecadador(httpServletRequest
								.getParameter("idCampoEnviarDados"));

			}
	
		}
		
		return retorno;

	}
}
