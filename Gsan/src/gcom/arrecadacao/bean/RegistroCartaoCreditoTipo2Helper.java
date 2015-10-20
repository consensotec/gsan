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
package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Movimento do Tipo 1 do TXT do retorno do cart�o de cr�dito
 * 
 * @author Raphael Rossiter
 * @date 29/01/2010
 */
public class RegistroCartaoCreditoTipo2Helper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoSubmissor;
	
	private String numeroResumoOperacao;
	
	private String numeroCartao;
	
	private Date dataCompraAjuste;
	
	private String sinalValorCompraParcela;
	
	private BigDecimal valorCompraParcela;
	
	private Integer parcela;
	
	private Integer totalParcelas;
	
	private String motivoRejeicao;
	
	private String codigoAutorizacao;
	
	private String tid;
	
	private String nsu;
	
	private BigDecimal valorTrocoFacil;
	
	private String numeroDigitosCartao;
	
	private String numeroNotaFiscal;
	
	private String codigoPaisEmissorCartao;
	
	private String numeroLogicoTerminal;
	
	private String identificadorTaxaEmbarqueOUValorEntrada;
	
	
	//CONSTANTE TIPO MOVIMENTO 2
	public final static Short CODIGO_MOVIMENTO_TIPO_2 = new Short("2");
	
	
	public RegistroCartaoCreditoTipo2Helper(){}


	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}


	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}


	public String getCodigoPaisEmissorCartao() {
		return codigoPaisEmissorCartao;
	}


	public void setCodigoPaisEmissorCartao(String codigoPaisEmissorCartao) {
		this.codigoPaisEmissorCartao = codigoPaisEmissorCartao;
	}


	public Date getDataCompraAjuste() {
		return dataCompraAjuste;
	}


	public void setDataCompraAjuste(Date dataCompraAjuste) {
		this.dataCompraAjuste = dataCompraAjuste;
	}


	public String getEstabelecimentoSubmissor() {
		return estabelecimentoSubmissor;
	}


	public void setEstabelecimentoSubmissor(String estabelecimentoSubmissor) {
		this.estabelecimentoSubmissor = estabelecimentoSubmissor;
	}


	public String getIdentificadorTaxaEmbarqueOUValorEntrada() {
		return identificadorTaxaEmbarqueOUValorEntrada;
	}


	public void setIdentificadorTaxaEmbarqueOUValorEntrada(
			String identificadorTaxaEmbarqueOUValorEntrada) {
		this.identificadorTaxaEmbarqueOUValorEntrada = identificadorTaxaEmbarqueOUValorEntrada;
	}


	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}


	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}


	public String getNsu() {
		return nsu;
	}


	public void setNsu(String nsu) {
		this.nsu = nsu;
	}


	public String getNumeroCartao() {
		return numeroCartao;
	}


	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}


	public String getNumeroDigitosCartao() {
		return numeroDigitosCartao;
	}


	public void setNumeroDigitosCartao(String numeroDigitosCartao) {
		this.numeroDigitosCartao = numeroDigitosCartao;
	}


	public String getNumeroLogicoTerminal() {
		return numeroLogicoTerminal;
	}


	public void setNumeroLogicoTerminal(String numeroLogicoTerminal) {
		this.numeroLogicoTerminal = numeroLogicoTerminal;
	}


	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}


	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}


	public String getNumeroResumoOperacao() {
		return numeroResumoOperacao;
	}


	public void setNumeroResumoOperacao(String numeroResumoOperacao) {
		this.numeroResumoOperacao = numeroResumoOperacao;
	}


	public String getSinalValorCompraParcela() {
		return sinalValorCompraParcela;
	}


	public void setSinalValorCompraParcela(String sinalValorCompraParcela) {
		this.sinalValorCompraParcela = sinalValorCompraParcela;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public Short getTipoRegistro() {
		return tipoRegistro;
	}


	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}


	public BigDecimal getValorCompraParcela() {
		return valorCompraParcela;
	}


	public void setValorCompraParcela(BigDecimal valorCompraParcela) {
		this.valorCompraParcela = valorCompraParcela;
	}


	public BigDecimal getValorTrocoFacil() {
		return valorTrocoFacil;
	}


	public void setValorTrocoFacil(BigDecimal valorTrocoFacil) {
		this.valorTrocoFacil = valorTrocoFacil;
	}


	public Integer getParcela() {
		return parcela;
	}


	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}


	public Integer getTotalParcelas() {
		return totalParcelas;
	}


	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
}
