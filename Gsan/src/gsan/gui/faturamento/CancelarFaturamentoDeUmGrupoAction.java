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
package gsan.gui.faturamento;


import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FaturamentoGrupoCanceladoHistorico;
import gsan.faturamento.conta.ContaMotivoCancelamento;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * @author Arthur Carvalho
 * @date 18/10/11
 */

public class CancelarFaturamentoDeUmGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Usuario usuario = this.getUsuarioLogado(httpServletRequest);
		
		Fachada fachada = Fachada.getInstancia();

		CancelarFaturamentoDeUmGrupoActionForm form = (CancelarFaturamentoDeUmGrupoActionForm) actionForm;

		FaturamentoGrupoCanceladoHistorico canceladoHistorico = new FaturamentoGrupoCanceladoHistorico();
		
		canceladoHistorico = carregaObjeto(form);
		
		canceladoHistorico.setUsuario(usuario);
		
		//[SB0002-Gravar Histórico do Grupo Selecionado]
		fachada.inserir(canceladoHistorico);
		
		Integer anoMesReferenciaGrupoMenosUmMes = new Integer(form.getReferenciaGrupoMenosUmMes());
		
		//<<Inclui>>[UC1242-CancelarFaturamentoGrupo] 
		fachada.inserirProcessoBatchCancelarGrupoFaturamento(usuario, canceladoHistorico.getFaturamentoGrupo().getId(), 
			canceladoHistorico.getAnoMesReferencia(), anoMesReferenciaGrupoMenosUmMes);

		montarPaginaSucesso(httpServletRequest, "Processo de cancelamento do faturamento para o grupo "
				+ canceladoHistorico.getFaturamentoGrupo().getId()
				+ " encaminhado para execução batch",
				"Cancelar outro grupo de faturamento. ",
				"exibirCancelarGrupoFaturamentoAction.do?menu=sim");

		
		return retorno;
	}
	
	/**
	 * [SB0002-Gravar Histórico do Grupo Selecionado]
	 * @author Arthur Carvalho
	 * Carrega o objeto FaturamentoGrupoCanceladoHistorico com os valores da tela.
	 * @param form
	 * @return
	 */
	private FaturamentoGrupoCanceladoHistorico carregaObjeto(CancelarFaturamentoDeUmGrupoActionForm form){
		
		FaturamentoGrupoCanceladoHistorico canceladoHistorico = new FaturamentoGrupoCanceladoHistorico();		
				
		//Faturamento grupo
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		faturamentoGrupo.setId(new Integer(form.getGrupoFaturamento()));
		canceladoHistorico.setFaturamentoGrupo(faturamentoGrupo);
		
		//Referencia
		canceladoHistorico.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFaturada()));
		
		//Normal
		if ( form.getContasFaturadasNormal() != null && !form.getContasFaturadasNormal().equals("") ) {
			canceladoHistorico.setQtdContasSituacaoNormal(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasNormal()));
		}
		//Incluida
		if ( form.getContasFaturadasIncluida() != null && !form.getContasFaturadasIncluida().equals("") ) {
			canceladoHistorico.setQtdContasSituacaoIncluida(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasIncluida()));
		}
		//Paga
		if ( form.getContasFaturadaPaga() != null && !form.getContasFaturadaPaga().equals("") ) {
			canceladoHistorico.setQtdContasSituacaoPaga(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadaPaga()));
		}
		//Cancelada
		if (form.getContasFaturadasCancelada() != null && !form.getContasFaturadasCancelada().equals("") ) {
			canceladoHistorico.setQtdContasSituacaoCancelada(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasCancelada()));
		}
		//parcelada
		if ( form.getContasFaturadasParcelada() != null && !form.getContasFaturadasParcelada().equals("") ) {
			canceladoHistorico.setQtdContasSituacaoParceladas(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasParcelada()));
		}
		//Retificada
		if ( form.getContasFaturadasRetificada() != null && !form.getContasFaturadasRetificada().equals("") ) {
			canceladoHistorico.setQtdContasSituacaoRetificada(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasRetificada()));
		}
		//Documento Cobranca
		if ( form.getContasFaturadasVinculadasADcoumentoCobranca() != null && !form.getContasFaturadasVinculadasADcoumentoCobranca().equals("") ) {
			canceladoHistorico.setQtdContasDocumentoCobranca(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasVinculadasADcoumentoCobranca()));
		}
		//fatura
		if (form.getContasFaturadasVinculadasAFaturamentoAgrupada() != null && !form.getContasFaturadasVinculadasAFaturamentoAgrupada().equals("")) {
			canceladoHistorico.setQtdContasFaturaItem(Util.formatarCampoNumericoEmMilharesParaInteger(form.getContasFaturadasVinculadasAFaturamentoAgrupada()));
		}
		//Excluida
		if (form.getTotalDeContasParaExclusao() != null && !"".equals(form.getTotalDeContasParaExclusao())) {
			canceladoHistorico.setQtdContasExcluidas(Util.formatarCampoNumericoEmMilharesParaInteger(form.getTotalDeContasParaExclusao()));
		}
		//Agua
		if ( form.getValorFaturadoAgua() != null && !form.getValorFaturadoAgua().equals("") ) {
			canceladoHistorico.setValorAguaFaturado(Util.formatarMoedaRealparaBigDecimal( form.getValorFaturadoAgua()));	
		}
		//Esgoto
		if ( form.getValorFaturadoEsgoto() != null && !form.getValorFaturadoEsgoto().equals("") ) {
			canceladoHistorico.setValorEsgotoFaturado(Util.formatarMoedaRealparaBigDecimal(form.getValorFaturadoEsgoto()));
		}
		//Debito
		if ( form.getValorFaturadoDebito() != null && !form.getValorFaturadoDebito().equals("") ) {
			canceladoHistorico.setValorDebitoFaturado(Util.formatarMoedaRealparaBigDecimal(form.getValorFaturadoDebito()));
		}
		//Credito
		if ( form.getValorFaturadoCredito() != null && !form.getValorFaturadoCredito().equals("") ) {
			canceladoHistorico.setValorCreditoFaturado(Util.formatarMoedaRealparaBigDecimal(form.getValorFaturadoCredito()));
		}
		//Impostos
		if ( form.getValorFaturadoImpostos() != null && !form.getValorFaturadoImpostos().equals("") ) {
			canceladoHistorico.setValorImpostosFaturado(Util.formatarMoedaRealparaBigDecimal(form.getValorFaturadoImpostos()));
		}
		
		//Conta Motivo Cancelamento
		ContaMotivoCancelamento contaMotivoCancelamento = new ContaMotivoCancelamento();
		contaMotivoCancelamento.setId(new Integer(form.getMotivoCancelamento()));
		canceladoHistorico.setContaMotivoCancelamento(contaMotivoCancelamento);
		
		//Data de cancelamento
		canceladoHistorico.setDataCancelamento(Util.getSQLTimesTemp(new Date()));
		//ultima alteracao
		canceladoHistorico.setUltimaAlteracao(Util.getSQLTimesTemp(new Date()));
		
		
		return canceladoHistorico;
	}
}
