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
package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Arthur Carvalho
 * @date 17/04/2008
 */
public class InserirEmpresaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma Empresa
	 * 
	 * [UC0782] Inserir Empresa
	 * 
	 * 
	 * @author Arthur Carvalho
	 * @date 14/05/2008
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirEmpresaActionForm inserirEmpresaActionForm = (InserirEmpresaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirEmpresaActionForm.getDescricao();

		Empresa empresa = new Empresa();

		// Nome
		if (!"".equals(inserirEmpresaActionForm.getDescricao())) {
			empresa.setDescricao(inserirEmpresaActionForm.getDescricao());
		}
		// Nome Abreviado
		if (!"".equals(inserirEmpresaActionForm.getDescricaoAbreviada())) {
			empresa.setDescricaoAbreviada(inserirEmpresaActionForm
					.getDescricaoAbreviada());
		}
		// E-mail
		if (!"".equals(inserirEmpresaActionForm.getEmail())) {
			empresa.setEmail(inserirEmpresaActionForm.getEmail());
		}
		// Empresa Principal
		if (inserirEmpresaActionForm.getIndicadorEmpresaPrincipal() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorEmpresaPrincipal())) {
			empresa.setIndicadorEmpresaPrincipal(inserirEmpresaActionForm
					.getIndicadorEmpresaPrincipal());
		}

		// Indicador Empresa Contratada Cobranca
		if (inserirEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorEmpresaCobranca())) {
			empresa.setIndicadorEmpresaContratadaCobranca(new Integer(
					inserirEmpresaActionForm.getIndicadorEmpresaCobranca())
					.shortValue());
		}

		// Indicador Atualiza Cadastro
		if (inserirEmpresaActionForm.getIndicadorAtualizaCadastro() != null
				&& !"".equals(inserirEmpresaActionForm
						.getIndicadorAtualizaCadastro())) {
			empresa.setIndicadorAtualizaCadastro(new Integer(
					inserirEmpresaActionForm.getIndicadorAtualizaCadastro())
					.shortValue());
		}

		if (inserirEmpresaActionForm.getIndicadorLeitura() != null
				&& !"".equals(inserirEmpresaActionForm.getIndicadorLeitura())) {

			empresa.setIndicadorLeitura(inserirEmpresaActionForm
					.getIndicadorLeitura());

		}

		// Indicador de Uso
		Short iu = ConstantesSistema.INDICADOR_USO_ATIVO;

		empresa.setIndicadorUso(iu);

		EmpresaContratoCobranca empresaCobranca = null;

		// Verifica se h� empresa de cobranca
		if (inserirEmpresaActionForm.getIndicadorEmpresaCobranca() != null
				&& inserirEmpresaActionForm.getIndicadorEmpresaCobranca()
						.equals("" + ConstantesSistema.INDICADOR_USO_ATIVO)) {

			empresaCobranca = new EmpresaContratoCobranca();

			// validar Data Inicio Contrato de Cobran�a
			if (inserirEmpresaActionForm.getDataInicioContratoCobranca() != null
					&& !inserirEmpresaActionForm
							.getDataInicioContratoCobranca().equals("")) {

				Date data = Util
						.converteStringParaDate(inserirEmpresaActionForm
								.getDataInicioContratoCobranca());
				empresaCobranca.setDataInicioContrato(data);
			} else {
				throw new ActionServletException("atencao.informe_campo", null,
						" Data do In�cio do Contrato");
			}

			// Percentual de Cobranca
			if (inserirEmpresaActionForm.getPercentualPagamento() != null
					&& !inserirEmpresaActionForm.getPercentualPagamento()
							.equals("")
					&& (inserirEmpresaActionForm
							.getPercentualDaFaixaInformado() == null || !inserirEmpresaActionForm
							.getPercentualDaFaixaInformado().equalsIgnoreCase(
									"sim"))) {

				BigDecimal percentualPagamentoAtual = null;

				String percentualPagamento = inserirEmpresaActionForm
						.getPercentualPagamento().toString().replace(".", "");

				percentualPagamento = percentualPagamento.replace(",", ".");
				percentualPagamentoAtual = new BigDecimal(percentualPagamento);

				empresaCobranca
						.setPercentualContratoCobranca(percentualPagamentoAtual);
				empresaCobranca.setCodigoLayoutTxt(ConstantesSistema.SIM);

			}

			// validar Data de encerramento de contrato de cobran�a
			if (inserirEmpresaActionForm.getDataEncerramentoContratoCobranca() != null
					&& !inserirEmpresaActionForm
							.getDataEncerramentoContratoCobranca().equals("")) {

				Date data = Util
						.converteStringParaDate(inserirEmpresaActionForm
								.getDataEncerramentoContratoCobranca());
				empresa.setDataEncerramentoContratoCobranca(data);
			}

			// validar mes
			Short numeroMaximoMeses = null;

			if (!inserirEmpresaActionForm.getQuantidadeMesValidoPagamento()
					.equals("")) {
				numeroMaximoMeses = new Short(
						inserirEmpresaActionForm
								.getQuantidadeMesValidoPagamento());

				if (numeroMaximoMeses.intValue() == 0
						|| numeroMaximoMeses.intValue() > 99) {
					throw new ActionServletException(
							"atencao.numero.meses.fora.faixa.permitido");
				}
				empresa.setQuantidadeMesValidoPagamento(new Integer(numeroMaximoMeses));
				

			}

		}

		List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = null;

		if (sessao.getAttribute("colecaoEmpresaCobrancaFaixa") != null
				&& !sessao.getAttribute("colecaoEmpresaCobrancaFaixa").equals(
						"")) {

			colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) sessao
					.getAttribute("colecaoEmpresaCobrancaFaixa");

			empresaCobranca.setCodigoLayoutTxt(ConstantesSistema.NAO);

		}

		Integer idEmpresa = (Integer) fachada.inserirEmpresa(empresa,
				empresaCobranca, usuarioLogado, colecaoEmpresaCobrancaFaixa);

		montarPaginaSucesso(httpServletRequest, "Empresa " + descricao
				+ " inserido com sucesso.", "Inserir outra Empresa",
				"exibirInserirEmpresaAction.do?menu=sim",
				"exibirAtualizarEmpresaAction.do?idRegistroAtualizacao="
						+ idEmpresa, "Atualizar Empresa Inserida");

		sessao.removeAttribute("InserirEmpresaActionForm");

		return retorno;

	}
}
