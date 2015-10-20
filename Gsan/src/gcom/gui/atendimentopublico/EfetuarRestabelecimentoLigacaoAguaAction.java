/**
 * 
 */
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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * @author R�mulo Aur�lio
 * @date 12/07/2006
 */
public class EfetuarRestabelecimentoLigacaoAguaAction extends GcomAction {

	/**
	 * [UC0357] Efetuar Religa��o de �gua
	 * 
	 * Este caso de uso permite efetuar a religa��o de �gua, sendo chamada pela
	 * funcionalidade que encerra a execu��o da ordem de servi�o ou chamada
	 * diretamente do menu.
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		EfetuarRestabelecimentoLigacaoAguaActionForm efetuarRestabelecimentoLigacaoAguaActionForm = (EfetuarRestabelecimentoLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		String idServicoMotivoNaoCobranca = efetuarRestabelecimentoLigacaoAguaActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarRestabelecimentoLigacaoAguaActionForm
				.getPercentualCobranca();

		OrdemServico ordemServico = null;

//		 Usuario logado no sistema
		 Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");


		// [SB0001] - Atualizar Im�vel/Liga��o de �gua

		Boolean veioEncerrarOS = null;
		if (httpServletRequest.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			veioEncerrarOS = Boolean.FALSE;
		}

		if (sessao.getAttribute("ordemServico") == null) {

			String idOrdemServico = efetuarRestabelecimentoLigacaoAguaActionForm
					.getIdOrdemServico();

			if (efetuarRestabelecimentoLigacaoAguaActionForm
					.getIdOrdemServico() != null) {
				idOrdemServico = efetuarRestabelecimentoLigacaoAguaActionForm
						.getIdOrdemServico();
			} else {
				idOrdemServico = (String) httpServletRequest
						.getAttribute("veioEncerrarOS");
				efetuarRestabelecimentoLigacaoAguaActionForm
						.setDataRestabelecimento((String) httpServletRequest
								.getAttribute("dataEncerramento"));

				sessao
						.setAttribute(
								"caminhoRetornoIntegracaoComercial",
								httpServletRequest
										.getAttribute("caminhoRetornoIntegracaoComercial"));
			}

			if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

				ordemServico = fachada.recuperaOSPorId(new Integer(
						idOrdemServico));

				if (ordemServico != null) {

					fachada.validarExibirRestabelecimentoLigacaoAgua(
							ordemServico, veioEncerrarOS);
				} else {
					throw new ActionServletException(
							"atencao.ordem_servico.inexistente");
				}
			}
		} else {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
		}
		Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();

		// regitrando operacao
		// ordemServico.setOperacaoEfetuada(operacaoEfetuada);
		// ordemServico.adicionarUsuario(usuario,
		// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		// registradorOperacao.registrarOperacao(ordemServico);
		// fachada.efetuarReligacaoAgua(ordemServico);

		if (ordemServico != null
				&& efetuarRestabelecimentoLigacaoAguaActionForm
						.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			BigDecimal valorAtual = new BigDecimal(0);
			if (efetuarRestabelecimentoLigacaoAguaActionForm.getValorDebito() != null) {
				String valorDebito = efetuarRestabelecimentoLigacaoAguaActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals("-1")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			/*
			 * Alterado por Raphael Rossiter em 24/07/2007
			 * OBJETIVO: Inserir o d�bito a cobrar com o valor correto
			 */
			
			/*if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(Util.formatarMoedaRealparaBigDecimal(
						efetuarRestabelecimentoLigacaoAguaActionForm
								.getValorParcelas()));
			}*/
			
			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(valorPercentual));
			}
			
		}
		String qtdParcelas = efetuarRestabelecimentoLigacaoAguaActionForm
				.getQuantidadeParcelas();

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		if (efetuarRestabelecimentoLigacaoAguaActionForm
				.getDataRestabelecimento() != null
				&& !efetuarRestabelecimentoLigacaoAguaActionForm
						.getDataRestabelecimento().equals("")) {
			ordemServico
					.setDataEncerramento(Util
							.converteStringParaDate(efetuarRestabelecimentoLigacaoAguaActionForm
									.getDataRestabelecimento()));
		}

		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if (efetuarRestabelecimentoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada
					.efetuarRestabelecimentoLigacaoAgua(integracaoComercialHelper);
		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper",
					integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			// fachada.efetuarRestabelecimentoLigacaoAgua(ordemServico,
			// efetuarRestabelecimentoLigacaoAguaActionForm.getVeioEncerrarOS().toString());

			/*
			 * if(ordemServico.getServicoTipo().getDebitoTipo() != null &&
			 * (ordemServico.getServicoNaoCobrancaMotivo() == null ||
			 * ordemServico.getServicoNaoCobrancaMotivo().getId() ==
			 * ConstantesSistema.NUMERO_NAO_INFORMADO)){
			 * fachada.gerarDebitoOrdemServico(ordemServico.getId(),
			 * ordemServico.getServicoTipo().getDebitoTipo().getId(),
			 * Util.calcularValorDebitoComPorcentagem(ordemServico.getValorAtual(),
			 * efetuarRestabelecimentoLigacaoAguaActionForm.getPercentualCobranca()) ,
			 * new
			 * Integer(efetuarRestabelecimentoLigacaoAguaActionForm.getQuantidadeParcelas()).intValue()); }
			 */

			// [FS006] Verificar Sucesso da Transa��o
			montarPaginaSucesso(httpServletRequest,
					"Restabelecimento da Liga��o de �gua para o im�vel "
							+ imovel.getId() + " efetuada com sucesso",
					"Efetuar outro Restabelecimento da Liga��o de �gua",
					"exibirEfetuarRestabelecimentoLigacaoAguaAction.do?menu=sim");
		}

		return retorno;

	}
}
