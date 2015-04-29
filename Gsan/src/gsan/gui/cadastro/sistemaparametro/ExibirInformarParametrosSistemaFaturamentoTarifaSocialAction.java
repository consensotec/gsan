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
package gsan.gui.cadastro.sistemaparametro;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.gui.GcomAction;
import gsan.util.Util;

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
 * @date 08/01/2007
 */
public class ExibirInformarParametrosSistemaFaturamentoTarifaSocialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirInformarParametrosSistemaFaturamentoTarifaSocial");

		// obt�m a inst�ncia da sess�o
		HttpSession sessao = this.getSessao(httpServletRequest);

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		SistemaParametro sistemaParametro = 
			(SistemaParametro) sessao.getAttribute("sistemaParametro");

		Integer cont;
		
		// Verifica se ja entrou nesse action, caso nao coloca no form os dados
		// do objeto sistemaParametro
		if (sessao.getAttribute("FaturamentoTarifaSocial") == null) {
			
			cont = 1;
			sessao.setAttribute("FaturamentoTarifaSocial", cont);

			String anoMesFaturamento = 
				Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento().toString());
			
			form.setMesAnoReferencia(anoMesFaturamento);
			form.setMenorConsumo(sistemaParametro.getMenorConsumoGrandeUsuario().toString());

			if (sistemaParametro.getValorMinimoEmissaoConta() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getValorMinimoEmissaoConta());

				form.setMenorValor(valorAux);
			}

			form.setQtdeEconomias(sistemaParametro.getMenorEconomiasGrandeUsuario().toString());
			
			form.setQtdeContasRetificadas(sistemaParametro.getQtdMaxContasRetificadas()+"");
			
			if(sistemaParametro.getMesesMediaConsumo() != null){
				form.setMesesCalculoMedio(sistemaParametro.getMesesMediaConsumo().toString());	
			}

			if(sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null){
				form.setDiasMinimoVencimento(sistemaParametro.getNumeroMinimoDiasEmissaoVencimento().toString());
			}
			
			if(sistemaParametro.getNumeroDiasAdicionaisCorreios() != null){
				form.setDiasMinimoVencimentoCorreio(sistemaParametro.getNumeroDiasAdicionaisCorreios().toString());
			}
			
			if(sistemaParametro.getDiasVencimentoAlternativo() != null){
				form.setDiasVencimentoAlternativo(sistemaParametro.getDiasVencimentoAlternativo());
			}

			if(sistemaParametro.getNumeroMesesValidadeConta() != null){
				form.setNumeroMesesValidadeConta(sistemaParametro.getNumeroMesesValidadeConta().toString());
			}
			
			if(sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento() != null){
				form.setNumeroMesesAlteracaoVencimento(sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento().toString());
			}


			if(sistemaParametro.getNumeroMesesCalculoCorrecao() != null){
				form.setNumeroMesesCalculoCorrecao(sistemaParametro.getNumeroMesesCalculoCorrecao().toString());
			}
			
			if(sistemaParametro.getNumeroVezesSuspendeLeitura() != null){
				form.setNumeroVezesSuspendeLeitura(sistemaParametro.getNumeroVezesSuspendeLeitura().toString());
			}
			
			if(sistemaParametro.getNumeroMesesLeituraSuspensa() != null){
				form.setNumeroMesesLeituraSuspensa(sistemaParametro.getNumeroMesesLeituraSuspensa().toString());
			}
			
			if(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento() != null){
				form.setNumeroMesesReinicioSitEspFatu(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento().toString());
			}
			
			if(sistemaParametro.getNumeroMaximoMesesInserirContaAntecipada() != null){
				form.setNumeroMaximoMesesInserirContaAntecipada(sistemaParametro.getNumeroMaximoMesesInserirContaAntecipada().toString());
			}
						
			if (sistemaParametro.getIndicadorRoteiroEmpresa() != null) {
				form.setIndicadorRoteiroEmpresa(sistemaParametro.getIndicadorRoteiroEmpresa().toString());
			}
			
			if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento() != null) {
				form.setIndicadorLimiteAlteracaoVencimento(sistemaParametro.getIndicadorLimiteAlteracaoVencimento().toString());
			}

			if (sistemaParametro.getIndicadorCalculaVencimento() != null) {
				form.setIndicadorCalculaVencimento(sistemaParametro.getIndicadorCalculaVencimento().toString());
			}

			if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
				form.setIndicadorTarifaCategoria(sistemaParametro.getIndicadorTarifaCategoria().toString());
			}

			form.setIndicadorAtualizacaoTarifaria(""+sistemaParametro.getIndicadorAtualizacaoTarifaria());
			
			if(sistemaParametro.getAnoMesAtualizacaoTarifaria() != null){

				String anoMes = 
					Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesAtualizacaoTarifaria().toString());
				
				form.setMesAnoAtualizacaoTarifaria(anoMes);
			}

			if (sistemaParametro.getIndicadorFaturamentoAntecipado() != null) {
				form.setIndicadorFaturamentoAntecipado(sistemaParametro.getIndicadorFaturamentoAntecipado().toString());
			}
			
			form.setIndicadorRetificacaoValorMenor("" + sistemaParametro.getIndicadorRetificacaoValorMenor());
			
			form.setIndicadorTransferenciaComDebito("" + sistemaParametro.getIndicadorTransferenciaComDebito());
			
			if(sistemaParametro.getNumeroMaximoDiasNovaLigacao() != null){
				form.setNumeroMaximoDiasNovaLigacao(sistemaParametro.getNumeroMaximoDiasNovaLigacao().toString());
			}
			
			form.setIndicadorNaoMedidoTarifa("" + sistemaParametro.getIndicadorNaoMedidoTarifa());
			
			form.setIndicadorQuadraFace("" + sistemaParametro.getIndicadorQuadraFace());
			
			if (sistemaParametro.getValorSalarioMinimo() != null) {

				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getValorSalarioMinimo());

				form.setSalarioMinimo(valorAux);
			}
			
			if (sistemaParametro.getAreaMaximaTarifaSocial() != null) {
				form.setAreaMaxima(sistemaParametro.getAreaMaximaTarifaSocial().toString());
			}
			
			if (sistemaParametro.getConsumoEnergiaMaximoTarifaSocial() != null) {
				form.setConsumoMaximo(sistemaParametro.getConsumoEnergiaMaximoTarifaSocial().toString());
			}
			
			if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
				form.setConsumoMaximo(sistemaParametro.getConsumoEnergiaMaximoTarifaSocial().toString());
			}
			
			if (sistemaParametro.getNumeroDiasVariacaoConsumo() != null) {
				form.setNumeroDiasVariacaoConsumo(sistemaParametro.getNumeroDiasVariacaoConsumo().toString());
			}
			
			if ( sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao() != null ) {
				form.setNnDiasPrazoRecursoAutoInfracao( sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao().toString() );
			}
			
			
			if ( sistemaParametro.getPercentualBonusSocial() != null ) {
				
				String valorAux = 
					Util.formatarMoedaReal(sistemaParametro.getPercentualBonusSocial());
				
				form.setPercentualBonusSocial( valorAux );
			}
			
			
			if(sistemaParametro.getIndicadorBloqueioContaMobile()!=null){
				form.setIndicadorBloqueioContaMobile(
						sistemaParametro.getIndicadorBloqueioContaMobile()
							.toString());
		

				if ( sistemaParametro.getValorContaFichaComp() != null ) {
					
					String valorAux = 
						Util.formatarMoedaReal(sistemaParametro.getValorContaFichaComp());
					
					form.setValorContaFichaComp( valorAux );
				}
	
				if (sistemaParametro.getNumeroMesesRetificarConta() != null) {
	
					String valorAux = sistemaParametro
							.getNumeroMesesRetificarConta().toString();
	
					form.setNumeroMesesRetificarConta(valorAux);
				}
				
				
				if (sistemaParametro.getMensagemContaBraile() != null) {
	
					String msgContaBraile = sistemaParametro
							.getMensagemContaBraile().toString();
	
					form.setMensagemContaBraile(msgContaBraile);
				}
				
				if(sistemaParametro.getCodigoTipoCalculoNaoMedido() != null){
					form.setCodigoTipoCalculoNaoMedido(sistemaParametro.getCodigoTipoCalculoNaoMedido().toString());
				}
				
				if (sistemaParametro.getIndicadorNormaRetificacao() != null) {
					form.setIndicadorNormaRetificacao(sistemaParametro.getIndicadorNormaRetificacao().toString());
				}
				
				if (sistemaParametro.getIndicadorUtilizaTarifaSimulacao() != null) {
					form.setIndicadorUtilizaTarifaSimulacao(sistemaParametro.getIndicadorUtilizaTarifaSimulacao().toString());
				}
				
				if(sistemaParametro.getIndicadorCriticarConteudoRetornoMovimentoNegativacao() != null){
					form.setIndicadorCriticarConteudoRetornoMovimentoNegativacao(
							sistemaParametro.
							getIndicadorCriticarConteudoRetornoMovimentoNegativacao().toString());
				}
				
				if(sistemaParametro.getIndicadorCalcAcresImpontGuiaPagamento() != null){
					form.setIndicadorCalcAcresImpontGuiaPagamento(
							sistemaParametro.
							getIndicadorCalcAcresImpontGuiaPagamento().toString());
				}
				
				
			}
		}
		return retorno;
	}
}


