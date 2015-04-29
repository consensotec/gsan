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
		
		//[SB0002-Gravar Hist�rico do Grupo Selecionado]
		fachada.inserir(canceladoHistorico);
		
		Integer anoMesReferenciaGrupoMenosUmMes = new Integer(form.getReferenciaGrupoMenosUmMes());
		
		//<<Inclui>>[UC1242-CancelarFaturamentoGrupo] 
		fachada.inserirProcessoBatchCancelarGrupoFaturamento(usuario, canceladoHistorico.getFaturamentoGrupo().getId(), 
			canceladoHistorico.getAnoMesReferencia(), anoMesReferenciaGrupoMenosUmMes);

		montarPaginaSucesso(httpServletRequest, "Processo de cancelamento do faturamento para o grupo "
				+ canceladoHistorico.getFaturamentoGrupo().getId()
				+ " encaminhado para execu��o batch",
				"Cancelar outro grupo de faturamento. ",
				"exibirCancelarGrupoFaturamentoAction.do?menu=sim");

		
		return retorno;
	}
	
	/**
	 * [SB0002-Gravar Hist�rico do Grupo Selecionado]
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
