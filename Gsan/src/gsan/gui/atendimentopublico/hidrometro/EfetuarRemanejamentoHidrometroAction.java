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
package gsan.gui.atendimentopublico.hidrometro;

import gsan.atendimentopublico.bean.IntegracaoComercialHelper;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.interceptor.RegistradorOperacao;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.seguranca.acesso.Operacao;
import gsan.seguranca.acesso.OperacaoEfetuada;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioAcao;
import gsan.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gsan.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.Date;

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
 * @date 28/06/2006
 */
public class EfetuarRemanejamentoHidrometroAction extends GcomAction {

	/**
	 * [UC0365] Efetuar Remanejamento de Hidr�metro
	 * 
	 * Este caso de uso permite efetuar o remanejamento de hidrometro, sendo
	 * chamado pela funcionalidade que encerra a execu��o da ordem de servi�o ou
	 * chamada diretamente do Menu.
	 * 
	 * @param actionMapping Description of the Parameter
	 * @param actionForm Description of the Parameter
	 * @param httpServletRequest Description of the Parameter
	 * @param httpServletResponse Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarRemanejamentoHidrometroActionForm efetuarRemanejamentoHidrometroActionForm = (EfetuarRemanejamentoHidrometroActionForm) actionForm;

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		/*
		 * -------------[UC0107] Registrar Transa��o-----------------------
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(Operacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR, new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// ----------------[UC0107] -Fim- Registrar Transa��o-----------

		String localInstalacao = efetuarRemanejamentoHidrometroActionForm.getLocalInstalacao();
		String protecao = efetuarRemanejamentoHidrometroActionForm.getProtecao();
		String cavalete = efetuarRemanejamentoHidrometroActionForm.getTipoCavalete();
		String idServicoMotivoNaoCobranca = efetuarRemanejamentoHidrometroActionForm.getMotivoNaoCobranca();
		String valorPercentual = efetuarRemanejamentoHidrometroActionForm.getPercentualCobranca();
		String qtdParcelas = efetuarRemanejamentoHidrometroActionForm.getQuantidadeParcelas();

		OrdemServico ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");

		Imovel imovel = null;
		if (ordemServico.getRegistroAtendimento() != null && ordemServico.getRegistroAtendimento().getImovel() != null) {
			imovel = ordemServico.getRegistroAtendimento().getImovel();
		} else if (ordemServico.getImovel() != null) {
			imovel = ordemServico.getImovel();
		}

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) sessao.getAttribute("hidrometroInstalacaoHistorico");

		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(new Integer(localInstalacao));

		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(new Integer(protecao));

		hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(new Short(
				cavalete));

		// regitrando operacao
		hidrometroInstalacaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
		hidrometroInstalacaoHistorico.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		// if (ordemServico != null
		// && efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS()
		// .equalsIgnoreCase("true")
		// && efetuarRemanejamentoHidrometroActionForm.getIdTipoDebito() !=
		// null) {
		//
		// ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
		//
		// ordemServico.setIndicadorComercialAtualizado(new Short("1"));
		//
		// if (efetuarRemanejamentoHidrometroActionForm.getValorDebito() !=
		// null) {
		// ordemServico
		// .setValorAtual(new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getValorDebito().toString().replace(
		// ",", ".")));
		// }
		//
		// if (idServicoMotivoNaoCobranca != null) {
		// servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
		// servicoNaoCobrancaMotivo.setId(new Integer(
		// idServicoMotivoNaoCobranca));
		// }
		// ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
		//
		// if (valorPercentual != null) {
		// ordemServico.setPercentualCobranca(new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getPercentualCobranca()));
		// }
		//
		// if (idServicoMotivoNaoCobranca != null) {
		// servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
		// servicoNaoCobrancaMotivo.setId(new Integer(
		// idServicoMotivoNaoCobranca));
		// }
		// ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
		//
		// if (valorPercentual != null) {
		// ordemServico.setPercentualCobranca(new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getPercentualCobranca()));
		// }
		//
		// BigDecimal valorAtual = new BigDecimal(0);
		// if (efetuarRemanejamentoHidrometroActionForm.getValorDebito() !=
		// null) {
		// valorAtual = new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getValorDebito().toString().replace(",", "."));
		// }
		//
		// if (ordemServico.getServicoTipo().getDebitoTipo() != null
		// && (ordemServico.getServicoNaoCobrancaMotivo() == null ||
		// ordemServico
		// .getServicoNaoCobrancaMotivo().getId() ==
		// ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		// fachada.gerarDebitoOrdemServico(ordemServico.getId(),
		// ordemServico.getServicoTipo().getDebitoTipo().getId(),
		// valorAtual, new Integer(
		// efetuarRemanejamentoHidrometroActionForm
		// .getQuantidadeParcelas()).intValue());
		// }
		// }

		BigDecimal valorAtual = new BigDecimal(0);

		if (ordemServico != null && efetuarRemanejamentoHidrometroActionForm.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);

			if (efetuarRemanejamentoHidrometroActionForm.getValorDebito() != null) {
				String valorDebito = efetuarRemanejamentoHidrometroActionForm.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (idServicoMotivoNaoCobranca != null && !idServicoMotivoNaoCobranca.equals("-1")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(efetuarRemanejamentoHidrometroActionForm.getPercentualCobranca()));
			}

			ordemServico.setUltimaAlteracao(new Date());
		}

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);

		if (efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada.efetuarRemanejamentoHidrometro(integracaoComercialHelper);
		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}

		/*
		 * fachada.efetuarRemanejamentoHidrometro(hidrometroInstalacaoHistorico,
		 * ordemServico, efetuarRemanejamentoHidrometroActionForm
		 * .getVeioEncerrarOS(), qtdParcelas);
		 */

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, "Remanejamento do Hidr�metro no im�vel " + imovel.getId() + " efetuada com sucesso",
					"Efetuar outro Remanejamento de hidr�metro", "exibirEfetuarRemanejamentoHidrometroAction.do?menu=sim");
		}

		return retorno;
	}
}