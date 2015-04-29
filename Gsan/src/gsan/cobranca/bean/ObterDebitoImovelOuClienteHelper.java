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
package gsan.cobranca.bean;

import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;


/**
 * D�bitos de um im�vel ou de um cliente
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class ObterDebitoImovelOuClienteHelper {
	
	/**
	 * Contas em D�bito e Valores de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualiza��o monet�ria	 
	 * */
	private Collection<ContaValoresHelper> colecaoContasValores;
	
	/**
	 * Contas em D�bito e Valores Para exibi��o na aba de Im�vel de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualiza��o monet�ria	 
	 * */
	private Collection<ContaValoresHelper> colecaoContasValoresImovel;

	/**
	 * Cole��o de Debitos a Cobrar
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	/**
	 * Cole��o de Creditos a Realizar
	 */
	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	
	/**
	 * Guias de Pagamento e Valores de:
	 * Valor Pago
	 * Valor da multa
	 * Valor dos juros de mora
	 * Valor da atualiza��o monet�ria	 
	 * */
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	
	
	//----------------------------------------------------------
	//[UC0630] - Solicitar Emiss�o do Extrato de D�bitos
	//Vivianne Sousa - 21/08/2007
	/**
	 * Cole��o de Debitos/Creditos do Parcelamento
	 */
	private Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper;

	//----------------------------------------------------------
	

	//[UC0671] Gerar Movimento de Inclusao de Negativa��o
	//[SB0007] Gerar Registro de negativacao
	//RM6364 - Altera��o para negativa��o por per�odo
	private Integer anoMesReferenciaInicioDebito;
	private Integer anoMesReferenciaFinalDebito;

	
	/*
	 * Cole��o de Notas Promissorias?
	 */
	//private Collection<NotaPromissoria> colecaoNotasPromissorias;

	/**
	 * Constructor
	 */
	public ObterDebitoImovelOuClienteHelper() {
		
	}
	
	/**
	 * @param colecaoContasValores
	 * @param colecaoDebitoACobrar
	 * @param colecaoCreditoARealizar
	 * @param colecaoGuiasPagamentoValores
	 */
	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<ContaValoresHelper> colecaoContasValoresImovel,
		Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores, Collection<DebitoACobrar> colecaoDebitoACobrar, Collection<CreditoARealizar> colecaoCreditoARealizar, Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	public ObterDebitoImovelOuClienteHelper(Collection<ContaValoresHelper> colecaoContasValores,
			Collection<ContaValoresHelper> colecaoContasValoresImovel,
			Collection<DebitoACobrar> colecaoDebitoACobrar, 
			Collection<CreditoARealizar> colecaoCreditoARealizar, 
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores,
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		
		this.colecaoContasValores = colecaoContasValores;
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	
	/**
	 * @return Returns the colecaoContasValores.
	 */
	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	/**
	 * @param colecaoContasValores The colecaoContasValores to set.
	 */
	public void setColecaoContasValores(
			Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	/**
	 * @return Returns the colecaoCreditoARealizar.
	 */
	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	/**
	 * @param colecaoCreditoARealizar The colecaoCreditoARealizar to set.
	 */
	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	/**
	 * @return Returns the colecaoDebitoACobrar.
	 */
	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	/**
	 * @param colecaoDebitoACobrar The colecaoDebitoACobrar to set.
	 */
	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	/**
	 * @return Returns the colecaoGuiasPagamentoValores.
	 */
	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}

	/**
	 * @param colecaoGuiasPagamentoValores The colecaoGuiasPagamentoValores to set.
	 */
	public void setColecaoGuiasPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

	/**
	 * @return Retorna o campo colecaoContasValoresImovel.
	 */
	public Collection<ContaValoresHelper> getColecaoContasValoresImovel() {
		return colecaoContasValoresImovel;
	}

	/**
	 * @param colecaoContasValoresImovel O colecaoContasValoresImovel a ser setado.
	 */
	public void setColecaoContasValoresImovel(
			Collection<ContaValoresHelper> colecaoContasValoresImovel) {
		this.colecaoContasValoresImovel = colecaoContasValoresImovel;
	}

	public Collection<DebitoCreditoParcelamentoHelper> getColecaoDebitoCreditoParcelamentoHelper() {
		return colecaoDebitoCreditoParcelamentoHelper;
	}

	public void setColecaoDebitoCreditoParcelamentoHelper(
			Collection<DebitoCreditoParcelamentoHelper> colecaoDebitoCreditoParcelamentoHelper) {
		this.colecaoDebitoCreditoParcelamentoHelper = colecaoDebitoCreditoParcelamentoHelper;
	}

	public BigDecimal obterValorImpostosDasContas(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}

	public Integer getAnoMesReferenciaInicioDebito() {
		return anoMesReferenciaInicioDebito;
	}

	public void setAnoMesReferenciaInicioDebito(Integer anoMesReferenciaInicioDebito) {
		this.anoMesReferenciaInicioDebito = anoMesReferenciaInicioDebito;
	}

	public Integer getAnoMesReferenciaFinalDebito() {
		return anoMesReferenciaFinalDebito;
	}

	public void setAnoMesReferenciaFinalDebito(Integer anoMesReferenciaFinalDebito) {
		this.anoMesReferenciaFinalDebito = anoMesReferenciaFinalDebito;
	}
	
	
}
