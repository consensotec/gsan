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
package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos d�bitos de um im�vel
 * 
 * [UC0214] Efetuar Parcelamento de D�bitos
 *
 * @author Roberta Costa
 * @date 24/01/2006
 */
public class ExibirEfetuarParcelamentoDebitosProcesso4Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo4");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;

		// Verifica se entrou na aba de Negocia��o
        Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>)
			sessao.getAttribute("colecaoOpcoesParcelamento");
		if( colecaoOpcoesParcelamento == null || colecaoOpcoesParcelamento.isEmpty() ){
			throw new ActionServletException(
					"atencao.parametros.obrigatorios.nao.selecionados");
		}
		
		// Pega dados do formul�rio
		BigDecimal valorDebitoTotalAtualizado = Util
			.formatarMoedaRealparaBigDecimal((String) efetuarParcelamentoDebitosActionForm
				.get("valorDebitoTotalAtualizado"));

		// 5.1.4 Valor do desconto
		BigDecimal valorASerNegociado = new BigDecimal("0.00");
		BigDecimal valorTotalDescontos = Util
			.formatarMoedaRealparaBigDecimal((String)efetuarParcelamentoDebitosActionForm
					.get("valorTotalDescontos"));
		
		valorASerNegociado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);
			efetuarParcelamentoDebitosActionForm
				.set("valorNegociado", Util.formatarMoedaReal(valorASerNegociado));

		
		// 5.1.6 Condi��es da Negocia��o
		if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
			Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			while(opcoesParcelamentoValores.hasNext()) {
				OpcoesParcelamentoHelper opcoesParcelamento = 
					(OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				if( ((String)efetuarParcelamentoDebitosActionForm.get("indicadorQuantidadeParcelas"))
						.equals(opcoesParcelamento.getQuantidadePrestacao().toString()) ){
					efetuarParcelamentoDebitosActionForm
						.set("parcelaEscolhida", opcoesParcelamento.getQuantidadePrestacao().toString());

					efetuarParcelamentoDebitosActionForm
						.set("valorParcelaEscolhida",Util.formatarMoedaReal(opcoesParcelamento.getValorPrestacao()));

					BigDecimal valorASerParcelado = new BigDecimal("0.00");
					valorASerParcelado = opcoesParcelamento.getValorPrestacao()
						.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)
							.multiply(new BigDecimal(opcoesParcelamento.getQuantidadePrestacao())); 
					
					efetuarParcelamentoDebitosActionForm
						.set("valorASerParcelado",Util.formatarMoedaReal(valorASerParcelado));
					
					efetuarParcelamentoDebitosActionForm
						.set("taxaJurosEscolhida",Util.formatarMoedaReal(opcoesParcelamento.getTaxaJuros()));

				}
			}
		}
		
		/*
		 * Colocado por Raphael Rossiter em 25/08/2008 - Analista: Rosana Carvalho
		 * 
		 * O sistema verifica se o parcelamento � para ser inclu�do obrigatoriamente j� confirmado
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorParcelamentoConfirmado() == 
			ConstantesSistema.SIM.shortValue()){
			
			httpServletRequest.setAttribute("parcelamentoConfirmado", "OK");
		}

		return retorno;
	}
}