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
package gsan.relatorio.arrecadacao.pagamento;

import gsan.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Fl�vio Leonardo
 * @created 28/01/2009
 */
public class RelatorioGuiaPagamentoEmAtrasoBean implements RelatorioBean {

	private String financiamentoTipoId;
	
	private String financiamentoTipoDescricao;

	private String vencimentoInicial;

	private String vencimentoFinal;

	private String referenciaInicialContabil;

	private String referenciaFinalContabil;
	
	private String clienteId;
	
	private String clienteNome;
	
	private String clienteCpf;
	
	private String clienteEndereco;
	
	private String emissao;
	
	private String pagamento;
	
	private String valor;
	
	private String referencia;
	
	private String parcelas;
	
	private String parcelasTotal;
	
	private String vencimento;
	
	private String imovel;
	
	private String debitoTipoDescricao;
	
	private String idGuiaPagamento;

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	/**
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioGuiaPagamentoEmAtrasoBean(String financiamentoTipoId, String financiamentoTipoDescricao,
			String vencimentoInicial, String vencimentoFinal, String referenciaInicialContabil,
			String referenciaFinalContabil, String clienteId, String clienteNome, String clienteCpf,
			String clienteEndereco, String emissao, String pagamento, String valor, String referencia,
			String parcelas, String parcelasTotal, String vencimento) {
		this.financiamentoTipoId = financiamentoTipoId;
		this.financiamentoTipoDescricao = financiamentoTipoDescricao;
		this.vencimentoInicial = vencimentoInicial;
		this.vencimentoFinal = vencimentoFinal;
		this.referenciaInicialContabil = referenciaInicialContabil;
		this.referenciaFinalContabil = referenciaFinalContabil;
		
		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.clienteCpf = clienteCpf;
		this.clienteEndereco = clienteEndereco;
		this.emissao = emissao;
		
		this.pagamento = pagamento;
		this.valor = valor;
		this.referencia = referencia;
		this.parcelas = parcelas;
		this.parcelasTotal = parcelasTotal;
		this.vencimento = vencimento;

	}
	
	public RelatorioGuiaPagamentoEmAtrasoBean(RelatorioGuiaPagamentoEmAtrasoHelper helper) {
		this.financiamentoTipoId = helper.getFinanciamentoTipoId();
		this.financiamentoTipoDescricao = helper.getFinanciamentoTipoDescricao();
		this.vencimentoInicial = helper.getVencimentoInicial();
		this.vencimentoFinal = helper.getVencimentoFinal();
		this.referenciaInicialContabil = helper.getReferenciaInicialContabil();
		this.referenciaFinalContabil = helper.getReferenciaFinalContabil();
		
		this.clienteId = helper.getClienteId();
		this.clienteNome = helper.getClienteNome();
		this.clienteCpf = helper.getClienteCpf();
		this.clienteEndereco = helper.getClienteEndereco();
		this.emissao = helper.getEmissao();
		
		this.idGuiaPagamento = helper.getIdGuiaPagamento();
		this.pagamento = helper.getPagamento();
		this.valor = helper.getValor();
		this.referencia = helper.getReferencia();
		this.parcelas = helper.getParcelas();
		this.parcelasTotal = helper.getParcelasTotal();
		this.vencimento = helper.getVencimento();
		this.imovel = helper.getImovel();
		this.debitoTipoDescricao = helper.getDebitoTipoDescricao();

	}

	public String getClienteCpf() {
		return clienteCpf;
	}

	public void setClienteCpf(String clienteCpf) {
		this.clienteCpf = clienteCpf;
	}

	public String getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(String clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getEmissao() {
		return emissao;
	}

	public void setEmissao(String emissao) {
		this.emissao = emissao;
	}

	public String getFinanciamentoTipoDescricao() {
		return financiamentoTipoDescricao;
	}

	public void setFinanciamentoTipoDescricao(String financiamentoTipoDescricao) {
		this.financiamentoTipoDescricao = financiamentoTipoDescricao;
	}

	public String getFinanciamentoTipoId() {
		return financiamentoTipoId;
	}

	public void setFinanciamentoTipoId(String financiamentoTipoId) {
		this.financiamentoTipoId = financiamentoTipoId;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

	public String getParcelasTotal() {
		return parcelasTotal;
	}

	public void setParcelasTotal(String parcelasTotal) {
		this.parcelasTotal = parcelasTotal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferenciaFinalContabil() {
		return referenciaFinalContabil;
	}

	public void setReferenciaFinalContabil(String referenciaFinalContabil) {
		this.referenciaFinalContabil = referenciaFinalContabil;
	}

	public String getReferenciaInicialContabil() {
		return referenciaInicialContabil;
	}

	public void setReferenciaInicialContabil(String referenciaInicialContabil) {
		this.referenciaInicialContabil = referenciaInicialContabil;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getVencimentoFinal() {
		return vencimentoFinal;
	}

	public void setVencimentoFinal(String vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}

	public String getVencimentoInicial() {
		return vencimentoInicial;
	}

	public void setVencimentoInicial(String vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getDebitoTipoDescricao() {
		return debitoTipoDescricao;
	}

	public void setDebitoTipoDescricao(String debitoTipoDescricao) {
		this.debitoTipoDescricao = debitoTipoDescricao;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	
}
