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

import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Rafael Corr�a
 * @date 09/05/2006
 */
public class AtualizarGuiaDevolucaoAction extends GcomAction {

	/**
	 * Permite a Inclusao de Devolucoes
	 * 
	 * [UC0271] Inserir Devolucoes
	 * 
	 * @author Fernanda Paiva
	 * @date 10/03/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		AtualizarGuiaDevolucaoActionForm atualizarGuiaDevolucaoActionForm = (AtualizarGuiaDevolucaoActionForm) actionForm;

		String idGuiaDevolucao = atualizarGuiaDevolucaoActionForm
				.getIdGuiaDevolucao();
		String idRegistroAtendimento = atualizarGuiaDevolucaoActionForm
				.getIdRegistroAtendimento();
		String idOrdemServico = atualizarGuiaDevolucaoActionForm
				.getIdOrdemServico();
		String tipoDocumento = atualizarGuiaDevolucaoActionForm
				.getDocumentoTipoHidden();
		String idLocalidade = atualizarGuiaDevolucaoActionForm
				.getIdLocalidadeHidden();
		String referenciaConta = atualizarGuiaDevolucaoActionForm
				.getReferenciaConta();
		String idGuiaPagamento = atualizarGuiaDevolucaoActionForm
				.getIdGuiaPagamento();
		String idDebitoACobrar = atualizarGuiaDevolucaoActionForm
				.getIdDebitoACobrar();
		String idDebitoTipo = atualizarGuiaDevolucaoActionForm
				.getIdTipoDebitoHidden();
		String dataValidade = atualizarGuiaDevolucaoActionForm
				.getDataValidade();
		String valorDevolucao = atualizarGuiaDevolucaoActionForm
				.getValorDevolucao();

		GuiaDevolucao guiaDevolucao = (GuiaDevolucao) sessao
				.getAttribute("guiaDevolucaoAtualizar");

//		FiltroGuiaDevolucao filtroGuiaDevolucao = new FiltroGuiaDevolucao();
//		filtroGuiaDevolucao.adicionarParametro(new ParametroSimples(
//				FiltroGuiaDevolucao.ID, idGuiaDevolucao));
//
//		Collection colecaoGuiaDevolucao = fachada.pesquisar(
//				filtroGuiaDevolucao, GuiaDevolucao.class.getName());
//
//		if (colecaoGuiaDevolucao != null && !colecaoGuiaDevolucao.isEmpty()) {
//			guiaDevolucao = (GuiaDevolucao) colecaoGuiaDevolucao.iterator()
//					.next();
//		} else {
//			throw new ActionServletException("atencao.atualizacao.timestamp");
//		}

		RegistroAtendimento registroAtendimento = new RegistroAtendimento();

		DocumentoTipo documentoTipo = new DocumentoTipo();

		guiaDevolucao.setId(new Integer(idGuiaDevolucao));

		if (idRegistroAtendimento != null
				&& !idRegistroAtendimento.trim().equals("")) {
			registroAtendimento.setId(new Integer(idRegistroAtendimento));
			guiaDevolucao.setRegistroAtendimento(registroAtendimento);
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Registro de Atendimento");
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(new Integer(idOrdemServico));
			guiaDevolucao.setOrdemServico(ordemServico);
		} else {
			guiaDevolucao.setOrdemServico(null);
		}

		if (tipoDocumento != null
				&& !tipoDocumento
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			documentoTipo.setId(new Integer(tipoDocumento));
			guiaDevolucao.setDocumentoTipo(documentoTipo);
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Tipo de Documento");
		}

		if (idLocalidade != null && !idLocalidade.equals("")) {
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(idLocalidade));
			guiaDevolucao.setLocalidade(localidade);
		} else {
			guiaDevolucao.setLocalidade(null);
		}

		if (referenciaConta != null && !referenciaConta.trim().equals("")) {
			if (Util.validarAnoMes(referenciaConta)) {
				new ActionServletException(
						"atencao.adicionar_debito_ano_mes_referencia_invalido",
						null, referenciaConta);
			}
			Conta conta = new Conta();
			String referenciaContaFormatada = Util
					.formatarMesAnoParaAnoMesSemBarra(referenciaConta);
			conta.setReferencia(new Integer(referenciaContaFormatada)
					.intValue());
			guiaDevolucao.setConta(conta);
		} else {
			guiaDevolucao.setConta(null);
		}

		if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {
			GuiaPagamento guiaPagamento = new GuiaPagamento();
			guiaPagamento.setId(new Integer(idGuiaPagamento));
			guiaDevolucao.setGuiaPagamento(guiaPagamento);
		} else {
			guiaDevolucao.setGuiaPagamento(null);
		}

		if (idDebitoACobrar != null && !idDebitoACobrar.trim().equals("")) {
			
			DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
			debitoACobrarGeral.setId(new Integer(idDebitoACobrar));
			
			guiaDevolucao.setDebitoACobrarGeral(debitoACobrarGeral);
		} else {
			guiaDevolucao.setDebitoACobrarGeral(null);
		}

		if (idDebitoTipo != null && !idDebitoTipo.trim().equals("")) {
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(new Integer(idDebitoTipo));
			guiaDevolucao.setDebitoTipo(debitoTipo);
		} else {
			guiaDevolucao.setDebitoTipo(null);
		}

		if (dataValidade != null && !dataValidade.trim().equals("")) {
			guiaDevolucao.setDataValidade(Util
					.converteStringParaDate(dataValidade));
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Data de Validade");
		}

		if (valorDevolucao != null && !valorDevolucao.trim().equals("")) {
			BigDecimal valorDevolucaoFormatado = new BigDecimal(valorDevolucao
					.replace(".", "").replace(",", "."));
			if (valorDevolucaoFormatado.equals(new BigDecimal("0.00"))
					|| valorDevolucaoFormatado.equals(new BigDecimal("0"))) {
				throw new ActionServletException("atencao.Informe_entidade",
						null, "Valor da Devolu��o");
			}
			guiaDevolucao.setValorDevolucao(valorDevolucaoFormatado);
		}

		fachada.atualizarGuiaDevolucao(guiaDevolucao,usuarioLogado);

		montarPaginaSucesso(httpServletRequest,
				"Guia de Devolu��o " + guiaDevolucao.getId().toString()
						+ " atualizada com sucesso.",
				"Realizar outra Manuten��o de Guia de Devololu��o",
				"exibirFiltrarGuiaDevolucaoAction.do?menu=sim");

		return retorno;

	}

}