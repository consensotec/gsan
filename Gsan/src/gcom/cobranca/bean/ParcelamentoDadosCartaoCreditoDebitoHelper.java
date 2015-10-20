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

import java.math.BigDecimal;
import java.util.Date;

import gcom.gui.cobranca.parcelamento.ParcelamentoCartaoConfirmarForm;



public class ParcelamentoDadosCartaoCreditoDebitoHelper {
	
	private String modalidadeCartao;
	
	private Short numeroPrestacoes;
	
	private BigDecimal valorPrestacao;
	
	private BigDecimal valorTotal;
	
	private Date parcelamento;
	
	private String idParcelamento;
	
	private String cartaoCredito;
	
	private String idCliente;
	
 	private String nomeCliente;
 	
 	private String validadeCartao;
 	
 	private String validadeCartaoInvalida;
 	
 	private String numeroCartao;
 	
 	private String documentoCartao;

 	private String autorizacaoCartao;
 	
 	private String numeroIdentificacaoTransacao;
 	
 	private String usuarioConfirmacao;
 	
 	private String valorTransacao;
 	
 	private String qtdParcelas;
 	
 	private String dataOperadora;
 	
 	public ParcelamentoDadosCartaoCreditoDebitoHelper(
 			String modalidadeCartao,
 			Short numeroPrestacoes,
 			BigDecimal valorPrestacao,
 			BigDecimal valorTotal,
 			Date parcelamento,
 			String idParcelamento,
 			String cartaoCredito,
 			String idCliente,
 			String nomeCliente,
 			String validadeCartao,
 			String validadeCartaoInvalida,
 			String numeroCartao,
 			String documentoCartao,
 			String autorizacaoCartao,
 			String numeroIdentificacaoTransacao,
 			String usuarioConfirmacao,
 			String valorTransacao,
 			String qtdParcelas,
 			String dataOperadora){
 		
 		this.modalidadeCartao = modalidadeCartao;
 		this.numeroPrestacoes = numeroPrestacoes;
 		this.valorPrestacao = valorPrestacao;
 		this.valorTotal = valorTotal; 
 		this.parcelamento = parcelamento; 
 		this.idParcelamento = idParcelamento;
 		this.cartaoCredito = cartaoCredito;
 		this.idCliente = idCliente;
 		this.nomeCliente = nomeCliente;
 		this.validadeCartao =validadeCartao;
 		this.validadeCartaoInvalida = validadeCartaoInvalida;
 		this.numeroCartao = numeroCartao;
 		this.documentoCartao = documentoCartao;
 		this.autorizacaoCartao = autorizacaoCartao;
 		this.numeroIdentificacaoTransacao = numeroIdentificacaoTransacao;
 		this.usuarioConfirmacao = usuarioConfirmacao;
 		this.valorTransacao = valorTransacao;
 		this.qtdParcelas = qtdParcelas;
 		this.dataOperadora = dataOperadora;
 	}
 	
 	public ParcelamentoDadosCartaoCreditoDebitoHelper(
 			ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm){
 		
 		this.modalidadeCartao = parcelamentoCartaoConfirmarForm.getModalidadeCartao();
 		this.numeroPrestacoes = parcelamentoCartaoConfirmarForm.getNumeroPrestacoes();
 		this.valorPrestacao = parcelamentoCartaoConfirmarForm.getValorPrestacao();
 		this.valorTotal = parcelamentoCartaoConfirmarForm.getValorTotal(); 
 		this.parcelamento = parcelamentoCartaoConfirmarForm.getParcelamento(); 
 		this.idParcelamento = parcelamentoCartaoConfirmarForm.getIdParcelamento();
 		this.cartaoCredito = parcelamentoCartaoConfirmarForm.getCartaoCredito();
 		this.idCliente = parcelamentoCartaoConfirmarForm.getIdCliente();
 		this.nomeCliente = parcelamentoCartaoConfirmarForm.getNomeCliente();
 		this.validadeCartao = parcelamentoCartaoConfirmarForm.getValidadeCartao();
 		this.validadeCartaoInvalida = parcelamentoCartaoConfirmarForm.getValidadeCartaoInvalida();
 		this.numeroCartao = parcelamentoCartaoConfirmarForm.getNumeroCartao();
 		this.documentoCartao = parcelamentoCartaoConfirmarForm.getDocumentoCartao();
 		this.autorizacaoCartao = parcelamentoCartaoConfirmarForm.getAutorizacaoCartao();
 		this.numeroIdentificacaoTransacao = parcelamentoCartaoConfirmarForm.getNumeroIdentificacaoTransacao();
 		this.usuarioConfirmacao = parcelamentoCartaoConfirmarForm.getUsuarioConfirmacao();
 		this.valorTransacao = parcelamentoCartaoConfirmarForm.getValorTransacao();
 		this.qtdParcelas = parcelamentoCartaoConfirmarForm.getQtdParcelas();
 		this.dataOperadora = parcelamentoCartaoConfirmarForm.getDataOperadora();
 	}

	public String getAutorizacaoCartao() {
		return autorizacaoCartao;
	}

	public void setAutorizacaoCartao(String autorizacaoCartao) {
		this.autorizacaoCartao = autorizacaoCartao;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public String getDataOperadora() {
		return dataOperadora;
	}

	public void setDataOperadora(String dataOperadora) {
		this.dataOperadora = dataOperadora;
	}

	public String getDocumentoCartao() {
		return documentoCartao;
	}

	public void setDocumentoCartao(String documentoCartao) {
		this.documentoCartao = documentoCartao;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(String idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public String getModalidadeCartao() {
		return modalidadeCartao;
	}

	public void setModalidadeCartao(String modalidadeCartao) {
		this.modalidadeCartao = modalidadeCartao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroIdentificacaoTransacao() {
		return numeroIdentificacaoTransacao;
	}

	public void setNumeroIdentificacaoTransacao(String numeroIdentificacaoTransacao) {
		this.numeroIdentificacaoTransacao = numeroIdentificacaoTransacao;
	}

	public Short getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Short numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public Date getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Date parcelamento) {
		this.parcelamento = parcelamento;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public String getUsuarioConfirmacao() {
		return usuarioConfirmacao;
	}

	public void setUsuarioConfirmacao(String usuarioConfirmacao) {
		this.usuarioConfirmacao = usuarioConfirmacao;
	}

	public String getValidadeCartao() {
		return validadeCartao;
	}

	public void setValidadeCartao(String validadeCartao) {
		this.validadeCartao = validadeCartao;
	}

	public String getValidadeCartaoInvalida() {
		return validadeCartaoInvalida;
	}

	public void setValidadeCartaoInvalida(String validadeCartaoInvalida) {
		this.validadeCartaoInvalida = validadeCartaoInvalida;
	}

	public BigDecimal getValorPrestacao() {
		return valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}



}
