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
package gcom.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

public class ExtratoDebitoRelatorioHelper {
	
	private Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas;
	
	private Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemGuiasPagamento;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar;

    
    /** Campo adicionado para utiliza��o no caso de uso [UC0251] */
    private CobrancaDocumento documentoCobranca ;
	
	public CobrancaDocumento getDocumentoCobranca() {
        return documentoCobranca;
    }

    public void setDocumentoCobranca(CobrancaDocumento documentoCobranca) {
        this.documentoCobranca = documentoCobranca;
    }
    /** **********************************************************/


    public ExtratoDebitoRelatorioHelper() {
	}
	

	
	public ExtratoDebitoRelatorioHelper(Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas, 
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiasPagamento, 
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar,
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		
		this.colecaoCobrancaDocumentoItemContas = colecaoCobrancaDocumentoItemContas;
		this.colecaoCobrancaDocumentoItemGuiasPagamento = colecaoCobrancaDocumentoItemGuiasPagamento;
		this.colecaoCobrancaDocumentoItemDebitosACobrar = colecaoCobrancaDocumentoItemDebitosACobrar; 
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
		
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemDebitosACobrar() {
		return colecaoCobrancaDocumentoItemDebitosACobrar;
	}

	public void setColecaoCobrancaDocumentoItemDebitosACobrar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar) {
		this.colecaoCobrancaDocumentoItemDebitosACobrar = colecaoCobrancaDocumentoItemDebitosACobrar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemGuiasPagamento() {
		return colecaoCobrancaDocumentoItemGuiasPagamento;
	}

	public void setColecaoCobrancaDocumentoItemGuiasPagamento(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiasPagamento) {
		this.colecaoCobrancaDocumentoItemGuiasPagamento = colecaoCobrancaDocumentoItemGuiasPagamento;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemContas() {
		return colecaoCobrancaDocumentoItemContas;
	}

	public void setColecaoCobrancaDocumentoItemContas(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemContas) {
		this.colecaoCobrancaDocumentoItemContas = colecaoCobrancaDocumentoItemContas;
	}

	 /*
     * C�lcula o valor total da conta (�gua + esgoto + d�bitos - creditos)
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalConta(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemContas().iterator();
		
		while (iterator.hasNext()) {
			Conta conta = ((CobrancaDocumentoItem) iterator.next()).getContaGeral().getConta();
			
			if (conta != null){
				
				if (conta.getValorTotal() != null) {
					retorno = retorno.add(conta.getValorTotal());
				}
			}
			
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    /*
     * C�lcula o valor total das guias de pagamento(GPAG_VLDEBITO)
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalGuiaPagamento(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		if(this.getColecaoCobrancaDocumentoItemGuiasPagamento() != null){
			Iterator iterator = this.getColecaoCobrancaDocumentoItemGuiasPagamento().iterator();
			
			while (iterator.hasNext()) {
				GuiaPagamento guiaPagamento = ((CobrancaDocumentoItem) iterator.next()).getGuiaPagamentoGeral().getGuiaPagamento();
				
				if (guiaPagamento.getValorDebito() != null){
					retorno = retorno.add(guiaPagamento.getValorDebito());
				}
			}
		}
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    
    /*
     * Calcula o valor total restante dos debitos a cobrar 
     * (DBAC_VLDEBITO - ((DBAC_VLDEBITO/DBAC_NNPRESTAODEBITO) * (DBAC_NNPRESTACAOCOBRADA + dbac_nnparcelabonus)))
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalRestanteDebitosACobrar(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		if(this.getColecaoCobrancaDocumentoItemDebitosACobrar() != null){
			Iterator iterator = this.getColecaoCobrancaDocumentoItemDebitosACobrar().iterator();
			
			while (iterator.hasNext()) {
				
				CobrancaDocumentoItem cobrancaDocumentoItem = (CobrancaDocumentoItem) iterator.next();
				
				DebitoACobrar debitoACobrar = cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar();
				
				//ANTECIPA��O DE PARCELAS
				if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
					retorno = retorno.add(cobrancaDocumentoItem.getValorItemCobrado());
				}
				else{
					retorno = retorno.add(debitoACobrar.getValorTotalComBonus());
				}
			}
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaDocumentoItemCreditoARealizar.
	 */
	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemCreditoARealizar() {
		return colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	/**
	 * @param colecaoCobrancaDocumentoItemCreditoARealizar O colecaoCobrancaDocumentoItemCreditoARealizar a ser setado.
	 */
	public void setColecaoCobrancaDocumentoItemCreditoARealizar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
	}   
	
}
