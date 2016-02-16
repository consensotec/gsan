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
package gcom.cobranca.bean;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Conta do Imovel ou Cliente e : Valor Pago Valor da multa Valor dos juros de
 * mora Valor da atualiza��o monet�ria
 * 
 * @author Rafael Santos
 * @since 04/01/2006
 */
public class ContaValoresHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Conta do Imovel ou Cliente
	 */
	private Conta conta;

	/**
	 * Valor Pago
	 */
	private BigDecimal valorPago;

	/**
	 * Valor da Multa
	 */
	private BigDecimal valorMulta;

	/**
	 * Valor Juros Mora
	 */
	private BigDecimal valorJurosMora;

	/**
	 * Valor Atualizacao Monetaria
	 */
	private BigDecimal valorAtualizacaoMonetaria;

	/**
	 * Valor Atualizacao Monetaria
	 */
	private Integer indicadorContasDebito;
	
	private Short existeParcelamento;
	
	private Integer revisao;

	//utilizado no [UC1131] Efetuar Devolu��o de Valores Pagos em Duplicidade
	private BigDecimal valorCreditoConta;
	private BigDecimal valorAtualConta;

	/*
	 * [UC1214] Informar Acerto Documentos N�o Aceitos
	 * */
	private Short indicadorDebitoPago;
	
	/**
	 * 
	 */
	
	
	private short indicadorGerarLink;
	
	/*
	 * [UC0482] Emitir 2 Via da Conta
	 */
	private Short indicadorMensagem;
	
	private Integer idClienteConta;
	
	private Integer idClienteImovel;
	
	public ContaValoresHelper() {
		this.revisao = 2;
	}

	/**
	 * @param conta
	 * @param valorPago
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valoratualizacaoMonetaria
	 */
	public ContaValoresHelper(Conta conta, BigDecimal valorPago,
			BigDecimal valorMulta, BigDecimal valorJurosMora,
			BigDecimal valoratualizacaoMonetaria) {
		this.conta = conta;
		this.valorPago = valorPago;
		this.valorMulta = valorMulta;
		this.valorJurosMora = valorJurosMora;
		this.valorAtualizacaoMonetaria = valoratualizacaoMonetaria;
		this.revisao = 2;
	}

	/**
	 * @return Returns the conta.
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * @param conta
	 *            The conta to set.
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * @return Returns the valoratualizacaoMonetaria.
	 */
	public BigDecimal getValorAtualizacaoMonetaria() {
		return ( valorAtualizacaoMonetaria != null ? valorAtualizacaoMonetaria : new BigDecimal(0) );
	}

	/**
	 * @param valoratualizacaoMonetaria
	 *            The valoratualizacaoMonetaria to set.
	 */
	public void setValorAtualizacaoMonetaria(
			BigDecimal valoratualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valoratualizacaoMonetaria;
	}

	/**
	 * @return Returns the valorJurosMora.
	 */
	public BigDecimal getValorJurosMora() {
		return ( valorJurosMora != null ? valorJurosMora : new BigDecimal(0) );
	}

	/**
	 * @param valorJurosMora
	 *            The valorJurosMora to set.
	 */
	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	/**
	 * @return Returns the valorMulta.
	 */
	public BigDecimal getValorMulta() {
		return ( valorMulta != null ? valorMulta : new BigDecimal(0) );
	}

	/**
	 * @param valorMulta
	 *            The valorMulta to set.
	 */
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	/**
	 * @return Returns the valorPago.
	 */
	public BigDecimal getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago
	 *            The valorPago to set.
	 */
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * @return Retorna o campo indicadorContasDebito.
	 */
	public Integer getIndicadorContasDebito() {
		return indicadorContasDebito;
	}

	/**
	 * @param indicadorContasDebito
	 *            O indicadorContasDebito a ser setado.
	 */
	public void setIndicadorContasDebito(Integer indicadorContasDebito) {
		this.indicadorContasDebito = indicadorContasDebito;
	}
	
	/**
	 * @return Retorna o campo existeParcelamento.
	 */
	public Short getExisteParcelamento() {
		return existeParcelamento;
	}

	/**
	 * @param existeParcelamento O existeParcelamento a ser setado.
	 */
	public void setExisteParcelamento(Short existeParcelamento) {
		this.existeParcelamento = existeParcelamento;
	}

	public Integer getRevisao() {
		return revisao;
	}

	public void setRevisao(Integer revisao) {
		this.revisao = revisao;
	}

	public BigDecimal getValorTotalContaValores() {

		BigDecimal retorno = new BigDecimal("0.00");

		// Valor de Multa
		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta());
		}
		// Valor de JurosMora
		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora());
		}
		// Valor de AtualizacaoMonetaria
		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria());
		}

		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}

	/*
	 * [UC0214] - Efetuar Parcelamento de D�bitos C�lcula o valor total da conta
	 * (�gua + esgoto + d�bitos - creditos) @author Roberta Costa @created
	 * 03/03/2006
	 */
	public BigDecimal getValorTotalConta() {

		BigDecimal retorno = new BigDecimal("0.00");

		// Valor de �gua
		if (this.getConta().getValorAgua() != null) {
			retorno = retorno.add(this.getConta().getValorAgua());
		}
		// Valor de Esgoto
		if (this.getConta().getValorEsgoto() != null) {
			retorno = retorno.add(this.getConta().getValorEsgoto());
		}
		// Valor de D�bitos
		if (this.getConta().getDebitos() != null) {
			retorno = retorno.add(this.getConta().getDebitos());
		}
		// Valor de Cr�ditos
		if (this.getConta().getValorCreditos() != null) {
			retorno = retorno.subtract(this.getConta().getValorCreditos());
		}

		// Valor do Imposto
		//if (this.getConta().getValorImposto() != null) {
			//retorno = retorno.subtract(this.getConta().getValorImposto());
		//}

		
		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}

	/**
	 * Este m�todo retorna o valor total da conta (VALOR_AGUA + VALOR_ESGOTO +
	 * VALOR_DEBITOS) - VALOR_CREDITOS + (VALOR_MULTA + VALOR_JUROS_MORA +
	 * VALOR_ATUALIZACAO_MONETARIA)
	 * 
	 * OBS - Este m�todo foi alterado por Raphael pois n�o estava refletindo
	 * corretamente o valor da conta
	 * 
	 * @author R�mulo Aur�lio
	 * @date 18/01/2006
	 * 
	 */
	public BigDecimal getValorTotalComValorAtualizacaoMonetaria() {

		BigDecimal valorTotalConta = new BigDecimal("0.00");

		// Valor de �gua
		if (this.getConta().getValorAgua() != null) {
			valorTotalConta = valorTotalConta.add(this.getConta()
					.getValorAgua());
		}
		// Valor de Esgoto
		if (this.getConta().getValorEsgoto() != null) {
			valorTotalConta = valorTotalConta.add(this.getConta()
					.getValorEsgoto());
		}
		// Valor de D�bitos
		if (this.getConta().getDebitos() != null) {
			valorTotalConta = valorTotalConta.add(this.getConta().getDebitos());
		}
		// Valor de Cr�ditos
		if (this.getConta().getValorCreditos() != null) {
			valorTotalConta = valorTotalConta.subtract(this.getConta()
					.getValorCreditos());
		}

		// Valor de Multa
		if (this.getValorMulta() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorMulta());
		}
		// Valor de JurosMora
		if (this.getValorJurosMora() != null) {
			valorTotalConta = valorTotalConta.add(this.getValorJurosMora());
		}
		// Valor de AtualizacaoMonetaria
		if (this.getValorAtualizacaoMonetaria() != null) {
			valorTotalConta = valorTotalConta.add(this
					.getValorAtualizacaoMonetaria());
		}

		valorTotalConta = valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);

		return valorTotalConta;
	}

	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getConta().getReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	
	public String getVencimentoFormatado() {
		return Util.formatarData(this.getConta().getDataVencimentoConta());
	}

	
	
	/*
     * [UC0214] - Efetuar Parcelamento de D�bitos
     * (multa + juros de mora + atualiza��o monet�ria ) com o arredondamento de Parcelamento
     * @author Vivianne Sousa
     * @created 31/01/2007
     */
	public BigDecimal getValorTotalContaValoresParcelamento() {

		BigDecimal retorno = new BigDecimal("0.00");

		// Valor de Multa
		if (this.getValorMulta() != null) {
			retorno = retorno.add(this.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		// Valor de JurosMora
		if (this.getValorJurosMora() != null) {
			retorno = retorno.add(this.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}
		// Valor de AtualizacaoMonetaria
		if (this.getValorAtualizacaoMonetaria() != null) {
			retorno = retorno.add(this.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
		}

		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);

		return retorno;
	}

	
	/**
	 * M�todo equals
	 * 
	 * @author Raphael Rossiter
	 * @date 30/09/2008
	 *
	 * @param other
	 * @return boolean
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContaValoresHelper)) {
			return false;
		}
		ContaValoresHelper castOther = (ContaValoresHelper) other;

		return this.getConta().getId().equals(castOther.getConta().getId());
	}

	public BigDecimal getValorAtualConta() {
		return valorAtualConta;
	}

	public void setValorAtualConta(BigDecimal valorAtualConta) {
		this.valorAtualConta = valorAtualConta;
	}

	public BigDecimal getValorCreditoConta() {
		return valorCreditoConta;
	}

	public void setValorCreditoConta(BigDecimal valorCreditoConta) {
		this.valorCreditoConta = valorCreditoConta;
	}

	public Short getIndicadorDebitoPago() {
		return indicadorDebitoPago;
	}

	public void setIndicadorDebitoPago(Short indicadorDebitoPago) {
		this.indicadorDebitoPago = indicadorDebitoPago;
	}

	public short getIndicadorGerarLink() {
		return indicadorGerarLink;
	}

	public void setIndicadorGerarLink(short indicadorGerarLink) {
		this.indicadorGerarLink = indicadorGerarLink;
	}
	
	public Short getIndicadorMensagem() {
		return indicadorMensagem;
	}

	public void setIndicadorMensagem(Short indicadorMensagem) {
		this.indicadorMensagem = indicadorMensagem;
	}

	public Integer getIdClienteConta() {
		return idClienteConta;
	}

	public void setIdClienteConta(Integer idClienteConta) {
		this.idClienteConta = idClienteConta;
	}

	public Integer getIdClienteImovel() {
		return idClienteImovel;
	}

	public void setIdClienteImovel(Integer idClienteImovel) {
		this.idClienteImovel = idClienteImovel;
	}	
	
}