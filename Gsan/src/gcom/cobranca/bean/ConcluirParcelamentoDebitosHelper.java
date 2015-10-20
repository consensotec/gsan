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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class ConcluirParcelamentoDebitosHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<ContaValoresHelper> colecaoContaValores;
	
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores;
	
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	
	private Collection<CreditoARealizar> colecaoCreditoARealizar;
	
	private String indicadorRestabelecimento;
	
	private String indicadorContasRevisao;
	
	private String indicadorGuiasPagamento;
	
	private String indicadorAcrescimosImpotualidade;
	
	private String indicadorDebitosACobrar; 
	
	private String indicadorCreditoARealizar;
	
	private String indicadorDividaAtiva;
	
	private String indicadorParcelamentoCarne;
	
	private String indicadorCarneImovelCliente;
	
	private Imovel imovel;
	
	private BigDecimal valorEntradaInformado;
	
	private BigDecimal valorASerNegociado;
	
	private BigDecimal valorASerParcelado;
	
	private Date dataParcelamento;
	
	private BigDecimal valorTotalContaValores;
	
	private BigDecimal valorGuiasPagamento;
	
	private BigDecimal valorDebitoACobrarServico;
	
	private BigDecimal valorDebitoACobrarParcelamento;
	
	private BigDecimal valorCreditoARealizar;
	
	private BigDecimal valorAtualizacaoMonetaria;
	
	private BigDecimal valorJurosMora;
	
	private BigDecimal valorMulta;
	
	private BigDecimal valorDebitoTotalAtualizado;
	
	private BigDecimal descontoAcrescimosImpontualidade;
	
	private BigDecimal descontoAntiguidadeDebito;
	
	private BigDecimal descontoInatividadeLigacaoAgua;
	
	private BigDecimal percentualDescontoAcrescimosImpontualidade;
	
	private BigDecimal percentualDescontoAntiguidadeDebito;
	
	private BigDecimal percentualDescontoInatividadeLigacaoAgua;
	
	private Integer parcelamentoPerfilId;
	
	private BigDecimal valorAcrescimosImpontualidade;
	
	private BigDecimal valorDebitoACobrarServicoLongoPrazo;
	
	private BigDecimal valorDebitoACobrarServicoCurtoPrazo;
	
	private BigDecimal valorDebitoACobrarParcelamentoLongoPrazo;
	
	private BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo;
	
	private Short numeroPrestacoes; 
	
	private BigDecimal valorPrestacao;
	
	private BigDecimal valorEntradaMinima; 
	
	private BigDecimal taxaJuros;
	
	private String indicadorConfirmacaoParcelamento;
	
	private Cliente cliente;
	
	private Usuario usuarioLogado;
	
	private String cpfClienteParcelamentoDigitado;
	
	private BigDecimal descontoSancoesRDEspecial;
	
	private BigDecimal descontoTarifaSocialRDEspecial;
	
	private Collection colecaoContasEmAntiguidade;
	
	private BigDecimal descontoSobreDebitoTotal;
	
	private BigDecimal descontoDebitoTipo;
	private BigDecimal descontoMulta;
	private BigDecimal descontoJuros;
	private BigDecimal descontoAtualizacaoMonetaria;
	private Short entradaEhExtratoDebito;
	private BigDecimal valorDescontoEntrada;
	
	public BigDecimal getValorDescontoEntrada() {
		return valorDescontoEntrada;
	}


	public void setValorDescontoEntrada(BigDecimal valorDescontoEntrada) {
		this.valorDescontoEntrada = valorDescontoEntrada;
	}


	public Short getEntradaEhExtratoDebito() {
		return entradaEhExtratoDebito;
	}


	public void setEntradaEhExtratoDebito(Short entradaEhExtratoDebito) {
		this.entradaEhExtratoDebito = entradaEhExtratoDebito;
	}


	public ConcluirParcelamentoDebitosHelper(
			Collection<ContaValoresHelper> colecaoContaValores,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores,
			Collection<DebitoACobrar> colecaoDebitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoARealizar,
			String indicadorRestabelecimento, String indicadorContasRevisao,
			String indicadorGuiasPagamento,
			String indicadorAcrescimosImpotualidade,
			String indicadorDebitosACobrar, String indicadorCreditoARealizar,
			String indicadorDividaAtiva, Imovel imovel,
			BigDecimal valorEntradaInformado, BigDecimal valorASerNegociado,
			BigDecimal valorASerParcelado, Date dataParcelamento,
			BigDecimal valorTotalContaValores, BigDecimal valorGuiasPagamento,
			BigDecimal valorDebitoACobrarServico,
			BigDecimal valorDebitoACobrarParcelamento,
			BigDecimal valorCreditoARealizar,
			BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora,
			BigDecimal valorMulta, BigDecimal valorDebitoTotalAtualizado,
			BigDecimal descontoAcrescimosImpontualidade,
			BigDecimal descontoAntiguidadeDebito,
			BigDecimal descontoInatividadeLigacaoAgua,
			BigDecimal percentualDescontoAcrescimosImpontualidade,
			BigDecimal percentualDescontoAntiguidadeDebito,
			BigDecimal percentualDescontoInatividadeLigacaoAgua,
			Integer parcelamentoPerfilId,
			BigDecimal valorAcrescimosImpontualidade,
			BigDecimal valorDebitoACobrarServicoLongoPrazo,
			BigDecimal valorDebitoACobrarServicoCurtoPrazo,
			BigDecimal valorDebitoACobrarParcelamentoLongoPrazo,
			BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo,
			Short numeroPrestacoes, BigDecimal valorPrestacao,
			BigDecimal valorEntradaMinima, BigDecimal taxaJuros,
			String indicadorConfirmacaoParcelamento, Cliente cliente,
			Usuario usuarioLogado, String cpfClienteParcelamentoDigitado,
			BigDecimal descontoSancoesRDEspecial,
			BigDecimal descontoTarifaSocialRDEspecial,
			Collection colecaoContasEmAntiguidade,
			BigDecimal descontoSobreDebitoTotal) {
		super();
		this.colecaoContaValores = colecaoContaValores;
		this.colecaoGuiaPagamentoValores = colecaoGuiaPagamentoValores;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.indicadorRestabelecimento = indicadorRestabelecimento;
		this.indicadorContasRevisao = indicadorContasRevisao;
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
		this.indicadorAcrescimosImpotualidade = indicadorAcrescimosImpotualidade;
		this.indicadorDebitosACobrar = indicadorDebitosACobrar;
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
		this.indicadorDividaAtiva = indicadorDividaAtiva;
		this.imovel = imovel;
		this.valorEntradaInformado = valorEntradaInformado;
		this.valorASerNegociado = valorASerNegociado;
		this.valorASerParcelado = valorASerParcelado;
		this.dataParcelamento = dataParcelamento;
		this.valorTotalContaValores = valorTotalContaValores;
		this.valorGuiasPagamento = valorGuiasPagamento;
		this.valorDebitoACobrarServico = valorDebitoACobrarServico;
		this.valorDebitoACobrarParcelamento = valorDebitoACobrarParcelamento;
		this.valorCreditoARealizar = valorCreditoARealizar;
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
		this.valorJurosMora = valorJurosMora;
		this.valorMulta = valorMulta;
		this.valorDebitoTotalAtualizado = valorDebitoTotalAtualizado;
		this.descontoAcrescimosImpontualidade = descontoAcrescimosImpontualidade;
		this.descontoAntiguidadeDebito = descontoAntiguidadeDebito;
		this.descontoInatividadeLigacaoAgua = descontoInatividadeLigacaoAgua;
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
		this.parcelamentoPerfilId = parcelamentoPerfilId;
		this.valorAcrescimosImpontualidade = valorAcrescimosImpontualidade;
		this.valorDebitoACobrarServicoLongoPrazo = valorDebitoACobrarServicoLongoPrazo;
		this.valorDebitoACobrarServicoCurtoPrazo = valorDebitoACobrarServicoCurtoPrazo;
		this.valorDebitoACobrarParcelamentoLongoPrazo = valorDebitoACobrarParcelamentoLongoPrazo;
		this.valorDebitoACobrarParcelamentoCurtoPrazo = valorDebitoACobrarParcelamentoCurtoPrazo;
		this.numeroPrestacoes = numeroPrestacoes;
		this.valorPrestacao = valorPrestacao;
		this.valorEntradaMinima = valorEntradaMinima;
		this.taxaJuros = taxaJuros;
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
		this.cliente = cliente;
		this.usuarioLogado = usuarioLogado;
		this.cpfClienteParcelamentoDigitado = cpfClienteParcelamentoDigitado;
		this.descontoSancoesRDEspecial = descontoSancoesRDEspecial;
		this.descontoTarifaSocialRDEspecial = descontoTarifaSocialRDEspecial;
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
		this.descontoSobreDebitoTotal = descontoSobreDebitoTotal;
		
		this.descontoDebitoTipo = descontoDebitoTipo;
		this.descontoMulta = descontoMulta;
		this.descontoJuros = descontoJuros;
		this.descontoAtualizacaoMonetaria = descontoAtualizacaoMonetaria;
		this.entradaEhExtratoDebito = entradaEhExtratoDebito;
		this.valorDescontoEntrada = valorDescontoEntrada;
		
	}


	public ConcluirParcelamentoDebitosHelper(
			Collection<ContaValoresHelper> colecaoContaValores,
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores,
			Collection<DebitoACobrar> colecaoDebitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoARealizar,
			String indicadorRestabelecimento, String indicadorContasRevisao,
			String indicadorGuiasPagamento,
			String indicadorAcrescimosImpotualidade,
			String indicadorDebitosACobrar, String indicadorCreditoARealizar,
			String indicadorDividaAtiva, 
			String indicadorParcelamentoCarne, 
			String indicadorCarneImovelCliente, 
			Imovel imovel,
			BigDecimal valorEntradaInformado, BigDecimal valorASerNegociado,
			BigDecimal valorASerParcelado, Date dataParcelamento,
			BigDecimal valorTotalContaValores, BigDecimal valorGuiasPagamento,
			BigDecimal valorDebitoACobrarServico,
			BigDecimal valorDebitoACobrarParcelamento,
			BigDecimal valorCreditoARealizar,
			BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora,
			BigDecimal valorMulta, BigDecimal valorDebitoTotalAtualizado,
			BigDecimal descontoAcrescimosImpontualidade,
			BigDecimal descontoAntiguidadeDebito,
			BigDecimal descontoInatividadeLigacaoAgua,
			BigDecimal percentualDescontoAcrescimosImpontualidade,
			BigDecimal percentualDescontoAntiguidadeDebito,
			BigDecimal percentualDescontoInatividadeLigacaoAgua,
			Integer parcelamentoPerfilId,
			BigDecimal valorAcrescimosImpontualidade,
			BigDecimal valorDebitoACobrarServicoLongoPrazo,
			BigDecimal valorDebitoACobrarServicoCurtoPrazo,
			BigDecimal valorDebitoACobrarParcelamentoLongoPrazo,
			BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo,
			Short numeroPrestacoes, BigDecimal valorPrestacao,
			BigDecimal valorEntradaMinima, BigDecimal taxaJuros,
			String indicadorConfirmacaoParcelamento, Cliente cliente,
			Usuario usuarioLogado, String cpfClienteParcelamentoDigitado,
			BigDecimal descontoSancoesRDEspecial,
			BigDecimal descontoTarifaSocialRDEspecial,
			Collection colecaoContasEmAntiguidade,
			BigDecimal descontoSobreDebitoTotal, BigDecimal descontoDebitoTipo,
			BigDecimal descontoMulta, BigDecimal descontoJuros,
			BigDecimal descontoAtualizacaoMonetaria,
			Short entradaEhExtratoDebito,
			BigDecimal valorDescontoEntrada) {
		super();
		this.colecaoContaValores = colecaoContaValores;
		this.colecaoGuiaPagamentoValores = colecaoGuiaPagamentoValores;
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
		this.indicadorRestabelecimento = indicadorRestabelecimento;
		this.indicadorContasRevisao = indicadorContasRevisao;
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
		this.indicadorAcrescimosImpotualidade = indicadorAcrescimosImpotualidade;
		this.indicadorDebitosACobrar = indicadorDebitosACobrar;
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
		this.indicadorDividaAtiva = indicadorDividaAtiva;
		this.indicadorParcelamentoCarne = indicadorParcelamentoCarne;
		this.indicadorCarneImovelCliente = indicadorCarneImovelCliente;
		this.imovel = imovel;
		this.valorEntradaInformado = valorEntradaInformado;
		this.valorASerNegociado = valorASerNegociado;
		this.valorASerParcelado = valorASerParcelado;
		this.dataParcelamento = dataParcelamento;
		this.valorTotalContaValores = valorTotalContaValores;
		this.valorGuiasPagamento = valorGuiasPagamento;
		this.valorDebitoACobrarServico = valorDebitoACobrarServico;
		this.valorDebitoACobrarParcelamento = valorDebitoACobrarParcelamento;
		this.valorCreditoARealizar = valorCreditoARealizar;
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
		this.valorJurosMora = valorJurosMora;
		this.valorMulta = valorMulta;
		this.valorDebitoTotalAtualizado = valorDebitoTotalAtualizado;
		this.descontoAcrescimosImpontualidade = descontoAcrescimosImpontualidade;
		this.descontoAntiguidadeDebito = descontoAntiguidadeDebito;
		this.descontoInatividadeLigacaoAgua = descontoInatividadeLigacaoAgua;
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
		this.parcelamentoPerfilId = parcelamentoPerfilId;
		this.valorAcrescimosImpontualidade = valorAcrescimosImpontualidade;
		this.valorDebitoACobrarServicoLongoPrazo = valorDebitoACobrarServicoLongoPrazo;
		this.valorDebitoACobrarServicoCurtoPrazo = valorDebitoACobrarServicoCurtoPrazo;
		this.valorDebitoACobrarParcelamentoLongoPrazo = valorDebitoACobrarParcelamentoLongoPrazo;
		this.valorDebitoACobrarParcelamentoCurtoPrazo = valorDebitoACobrarParcelamentoCurtoPrazo;
		this.numeroPrestacoes = numeroPrestacoes;
		this.valorPrestacao = valorPrestacao;
		this.valorEntradaMinima = valorEntradaMinima;
		this.taxaJuros = taxaJuros;
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
		this.cliente = cliente;
		this.usuarioLogado = usuarioLogado;
		this.cpfClienteParcelamentoDigitado = cpfClienteParcelamentoDigitado;
		this.descontoSancoesRDEspecial = descontoSancoesRDEspecial;
		this.descontoTarifaSocialRDEspecial = descontoTarifaSocialRDEspecial;
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
		this.descontoSobreDebitoTotal = descontoSobreDebitoTotal;
		this.descontoDebitoTipo = descontoDebitoTipo;
		this.descontoMulta = descontoMulta;
		this.descontoJuros = descontoJuros;
		this.descontoAtualizacaoMonetaria = descontoAtualizacaoMonetaria;
		this.entradaEhExtratoDebito = entradaEhExtratoDebito;
		this.valorDescontoEntrada = valorDescontoEntrada;
	}



	public BigDecimal getDescontoSobreDebitoTotal() {
		return descontoSobreDebitoTotal;
	}

	public void setDescontoSobreDebitoTotal(BigDecimal descontoSobreDebitoTotal) {
		this.descontoSobreDebitoTotal = descontoSobreDebitoTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Collection<ContaValoresHelper> getColecaoContaValores() {
		return colecaoContaValores;
	}

	public void setColecaoContaValores(
			Collection<ContaValoresHelper> colecaoContaValores) {
		this.colecaoContaValores = colecaoContaValores;
	}

	public Collection<CreditoARealizar> getColecaoCreditoARealizar() {
		return colecaoCreditoARealizar;
	}

	public void setColecaoCreditoARealizar(
			Collection<CreditoARealizar> colecaoCreditoARealizar) {
		this.colecaoCreditoARealizar = colecaoCreditoARealizar;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiaPagamentoValores() {
		return colecaoGuiaPagamentoValores;
	}

	public void setColecaoGuiaPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores) {
		this.colecaoGuiaPagamentoValores = colecaoGuiaPagamentoValores;
	}

	public String getCpfClienteParcelamentoDigitado() {
		return cpfClienteParcelamentoDigitado;
	}

	public void setCpfClienteParcelamentoDigitado(
			String cpfClienteParcelamentoDigitado) {
		this.cpfClienteParcelamentoDigitado = cpfClienteParcelamentoDigitado;
	}

	public Date getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(Date dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public BigDecimal getDescontoAcrescimosImpontualidade() {
		return descontoAcrescimosImpontualidade;
	}

	public void setDescontoAcrescimosImpontualidade(
			BigDecimal descontoAcrescimosImpontualidade) {
		this.descontoAcrescimosImpontualidade = descontoAcrescimosImpontualidade;
	}

	public BigDecimal getDescontoAntiguidadeDebito() {
		return descontoAntiguidadeDebito;
	}

	public void setDescontoAntiguidadeDebito(BigDecimal descontoAntiguidadeDebito) {
		this.descontoAntiguidadeDebito = descontoAntiguidadeDebito;
	}

	public BigDecimal getDescontoInatividadeLigacaoAgua() {
		return descontoInatividadeLigacaoAgua;
	}

	public void setDescontoInatividadeLigacaoAgua(
			BigDecimal descontoInatividadeLigacaoAgua) {
		this.descontoInatividadeLigacaoAgua = descontoInatividadeLigacaoAgua;
	}

	public BigDecimal getDescontoSancoesRDEspecial() {
		return descontoSancoesRDEspecial;
	}

	public void setDescontoSancoesRDEspecial(BigDecimal descontoSancoesRDEspecial) {
		this.descontoSancoesRDEspecial = descontoSancoesRDEspecial;
	}

	public BigDecimal getDescontoTarifaSocialRDEspecial() {
		return descontoTarifaSocialRDEspecial;
	}

	public void setDescontoTarifaSocialRDEspecial(
			BigDecimal descontoTarifaSocialRDEspecial) {
		this.descontoTarifaSocialRDEspecial = descontoTarifaSocialRDEspecial;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getIndicadorAcrescimosImpotualidade() {
		return indicadorAcrescimosImpotualidade;
	}

	public void setIndicadorAcrescimosImpotualidade(
			String indicadorAcrescimosImpotualidade) {
		this.indicadorAcrescimosImpotualidade = indicadorAcrescimosImpotualidade;
	}

	public String getIndicadorConfirmacaoParcelamento() {
		return indicadorConfirmacaoParcelamento;
	}

	public void setIndicadorConfirmacaoParcelamento(
			String indicadorConfirmacaoParcelamento) {
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
	}

	public String getIndicadorContasRevisao() {
		return indicadorContasRevisao;
	}

	public void setIndicadorContasRevisao(String indicadorContasRevisao) {
		this.indicadorContasRevisao = indicadorContasRevisao;
	}

	public String getIndicadorCreditoARealizar() {
		return indicadorCreditoARealizar;
	}

	public void setIndicadorCreditoARealizar(String indicadorCreditoARealizar) {
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
	}

	public String getIndicadorDebitosACobrar() {
		return indicadorDebitosACobrar;
	}

	public void setIndicadorDebitosACobrar(String indicadorDebitosACobrar) {
		this.indicadorDebitosACobrar = indicadorDebitosACobrar;
	}

	public String getIndicadorGuiasPagamento() {
		return indicadorGuiasPagamento;
	}

	public void setIndicadorGuiasPagamento(String indicadorGuiasPagamento) {
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
	}

	public String getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(String indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public Short getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Short numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public Integer getParcelamentoPerfilId() {
		return parcelamentoPerfilId;
	}

	public void setParcelamentoPerfilId(Integer parcelamentoPerfilId) {
		this.parcelamentoPerfilId = parcelamentoPerfilId;
	}

	public BigDecimal getPercentualDescontoAcrescimosImpontualidade() {
		return percentualDescontoAcrescimosImpontualidade;
	}

	public void setPercentualDescontoAcrescimosImpontualidade(
			BigDecimal percentualDescontoAcrescimosImpontualidade) {
		this.percentualDescontoAcrescimosImpontualidade = percentualDescontoAcrescimosImpontualidade;
	}

	public BigDecimal getPercentualDescontoAntiguidadeDebito() {
		return percentualDescontoAntiguidadeDebito;
	}

	public void setPercentualDescontoAntiguidadeDebito(
			BigDecimal percentualDescontoAntiguidadeDebito) {
		this.percentualDescontoAntiguidadeDebito = percentualDescontoAntiguidadeDebito;
	}

	public BigDecimal getPercentualDescontoInatividadeLigacaoAgua() {
		return percentualDescontoInatividadeLigacaoAgua;
	}

	public void setPercentualDescontoInatividadeLigacaoAgua(
			BigDecimal percentualDescontoInatividadeLigacaoAgua) {
		this.percentualDescontoInatividadeLigacaoAgua = percentualDescontoInatividadeLigacaoAgua;
	}

	public BigDecimal getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public BigDecimal getValorAcrescimosImpontualidade() {
		return valorAcrescimosImpontualidade;
	}

	public void setValorAcrescimosImpontualidade(
			BigDecimal valorAcrescimosImpontualidade) {
		this.valorAcrescimosImpontualidade = valorAcrescimosImpontualidade;
	}

	public BigDecimal getValorASerNegociado() {
		return valorASerNegociado;
	}

	public void setValorASerNegociado(BigDecimal valorASerNegociado) {
		this.valorASerNegociado = valorASerNegociado;
	}

	public BigDecimal getValorASerParcelado() {
		return valorASerParcelado;
	}

	public void setValorASerParcelado(BigDecimal valorASerParcelado) {
		this.valorASerParcelado = valorASerParcelado;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public BigDecimal getValorCreditoARealizar() {
		return valorCreditoARealizar;
	}

	public void setValorCreditoARealizar(BigDecimal valorCreditoARealizar) {
		this.valorCreditoARealizar = valorCreditoARealizar;
	}

	public BigDecimal getValorDebitoACobrarParcelamento() {
		return valorDebitoACobrarParcelamento;
	}

	public void setValorDebitoACobrarParcelamento(
			BigDecimal valorDebitoACobrarParcelamento) {
		this.valorDebitoACobrarParcelamento = valorDebitoACobrarParcelamento;
	}

	public BigDecimal getValorDebitoACobrarParcelamentoCurtoPrazo() {
		return valorDebitoACobrarParcelamentoCurtoPrazo;
	}

	public void setValorDebitoACobrarParcelamentoCurtoPrazo(
			BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo) {
		this.valorDebitoACobrarParcelamentoCurtoPrazo = valorDebitoACobrarParcelamentoCurtoPrazo;
	}

	public BigDecimal getValorDebitoACobrarParcelamentoLongoPrazo() {
		return valorDebitoACobrarParcelamentoLongoPrazo;
	}

	public void setValorDebitoACobrarParcelamentoLongoPrazo(
			BigDecimal valorDebitoACobrarParcelamentoLongoPrazo) {
		this.valorDebitoACobrarParcelamentoLongoPrazo = valorDebitoACobrarParcelamentoLongoPrazo;
	}

	public BigDecimal getValorDebitoACobrarServico() {
		return valorDebitoACobrarServico;
	}

	public void setValorDebitoACobrarServico(BigDecimal valorDebitoACobrarServico) {
		this.valorDebitoACobrarServico = valorDebitoACobrarServico;
	}

	public BigDecimal getValorDebitoACobrarServicoCurtoPrazo() {
		return valorDebitoACobrarServicoCurtoPrazo;
	}

	public void setValorDebitoACobrarServicoCurtoPrazo(
			BigDecimal valorDebitoACobrarServicoCurtoPrazo) {
		this.valorDebitoACobrarServicoCurtoPrazo = valorDebitoACobrarServicoCurtoPrazo;
	}

	public BigDecimal getValorDebitoACobrarServicoLongoPrazo() {
		return valorDebitoACobrarServicoLongoPrazo;
	}

	public void setValorDebitoACobrarServicoLongoPrazo(
			BigDecimal valorDebitoACobrarServicoLongoPrazo) {
		this.valorDebitoACobrarServicoLongoPrazo = valorDebitoACobrarServicoLongoPrazo;
	}

	public BigDecimal getValorDebitoTotalAtualizado() {
		return valorDebitoTotalAtualizado;
	}

	public void setValorDebitoTotalAtualizado(BigDecimal valorDebitoTotalAtualizado) {
		this.valorDebitoTotalAtualizado = valorDebitoTotalAtualizado;
	}

	public BigDecimal getValorEntradaInformado() {
		return valorEntradaInformado;
	}

	public void setValorEntradaInformado(BigDecimal valorEntradaInformado) {
		this.valorEntradaInformado = valorEntradaInformado;
	}

	public BigDecimal getValorEntradaMinima() {
		return valorEntradaMinima;
	}

	public void setValorEntradaMinima(BigDecimal valorEntradaMinima) {
		this.valorEntradaMinima = valorEntradaMinima;
	}

	public BigDecimal getValorGuiasPagamento() {
		return valorGuiasPagamento;
	}

	public void setValorGuiasPagamento(BigDecimal valorGuiasPagamento) {
		this.valorGuiasPagamento = valorGuiasPagamento;
	}

	public BigDecimal getValorJurosMora() {
		return valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public BigDecimal getValorTotalContaValores() {
		return valorTotalContaValores;
	}

	public void setValorTotalContaValores(BigDecimal valorTotalContaValores) {
		this.valorTotalContaValores = valorTotalContaValores;
	}

	public Collection getColecaoContasEmAntiguidade() {
		return colecaoContasEmAntiguidade;
	}

	public void setColecaoContasEmAntiguidade(Collection colecaoContasEmAntiguidade) {
		this.colecaoContasEmAntiguidade = colecaoContasEmAntiguidade;
	}

	public String getIndicadorDividaAtiva() {
		return indicadorDividaAtiva;
	}

	public void setIndicadorDividaAtiva(String indicadorDividaAtiva) {
		this.indicadorDividaAtiva = indicadorDividaAtiva;
	}

	public BigDecimal getDescontoDebitoTipo() {
		return descontoDebitoTipo;
	}

	public void setDescontoDebitoTipo(BigDecimal descontoDebitoTipo) {
		this.descontoDebitoTipo = descontoDebitoTipo;
	}

	public BigDecimal getDescontoMulta() {
		return descontoMulta;
	}

	public void setDescontoMulta(BigDecimal descontoMulta) {
		this.descontoMulta = descontoMulta;
	}

	public BigDecimal getDescontoJuros() {
		return descontoJuros;
	}

	public void setDescontoJuros(BigDecimal descontoJuros) {
		this.descontoJuros = descontoJuros;
	}

	public BigDecimal getDescontoAtualizacaoMonetaria() {
		return descontoAtualizacaoMonetaria;
	}

	public void setDescontoAtualizacaoMonetaria(
			BigDecimal descontoAtualizacaoMonetaria) {
		this.descontoAtualizacaoMonetaria = descontoAtualizacaoMonetaria;
	}


	public String getIndicadorParcelamentoCarne() {
		return indicadorParcelamentoCarne;
	}


	public void setIndicadorParcelamentoCarne(String indicadorParcelamentoCarne) {
		this.indicadorParcelamentoCarne = indicadorParcelamentoCarne;
	}


	public String getIndicadorCarneImovelCliente() {
		return indicadorCarneImovelCliente;
	}


	public void setIndicadorCarneImovelCliente(String indicadorCarneImovelCliente) {
		this.indicadorCarneImovelCliente = indicadorCarneImovelCliente;
	}

}
