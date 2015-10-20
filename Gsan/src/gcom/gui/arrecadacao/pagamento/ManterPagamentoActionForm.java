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
package gcom.gui.arrecadacao.pagamento;


import org.apache.struts.validator.ValidatorActionForm;


/**
 * Form utilizado no manter pagamentos para remover ou atualizar pagamentos
 *
 * @author Pedro Alexandre
 * @date 21/03/2006
 */
public class ManterPagamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idAvisoBancario;
	private String codigoAgenteArrecadador;
	private String dataLancamentoAviso;
	private String numeroSequencialAviso;
	
	private String dataPagamento;
	private String idFormaArrecadacao;
	private String descricaoFormaArrecadacao;
	private String valorPagamento;
	private String idTipoDocumento;
	
	private String idImovel;
	private String codigoImovel;
	private String descricaoImovel;
						
	private String idGuiaPagamento;
	private String codigoGuiaPagamento;
	private String descricaoGuiaPagamento;
	private String valorGuiaPagamento;
	
	private String idDebitoACobrar;
	private String codigoDebitoACobrar;
	private String descricaoDebitoACobrar;
	private String valorDebitoACobrar;
				
	private String idLocalidade;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	
	private String referenciaConta;
	private String descricaoReferenciaConta;
	
	private String idCliente;			
	private String codigoCliente;
	private String nomeCliente;
	
	private String idTipoDebito;
	private String descricaoTipoDebito;
	
	private String nomeClienteArrecadador;	
	private String ultimaAlteracaoPagamento;
	
	
	private String[] idRegistrosRemocao;

	private String idSituacaoAtualPagamento;
	
	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public String getIdSituacaoAtualPagamento() {
		return idSituacaoAtualPagamento;
	}

	public void setIdSituacaoAtualPagamento(String idSituacaoAtualPagamento) {
		this.idSituacaoAtualPagamento = idSituacaoAtualPagamento;
	}

	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}

	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}

	public String getDataLancamentoAviso() {
		return dataLancamentoAviso;
	}

	public void setDataLancamentoAviso(String dataLancamentoAviso) {
		this.dataLancamentoAviso = dataLancamentoAviso;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescricaoDebitoACobrar() {
		return descricaoDebitoACobrar;
	}

	public void setDescricaoDebitoACobrar(String descricaoDebitoACobrar) {
		this.descricaoDebitoACobrar = descricaoDebitoACobrar;
	}

	public String getDescricaoFormaArrecadacao() {
		return descricaoFormaArrecadacao;
	}

	public void setDescricaoFormaArrecadacao(String descricaoFormaArrecadacao) {
		this.descricaoFormaArrecadacao = descricaoFormaArrecadacao;
	}

	public String getDescricaoGuiaPagamento() {
		return descricaoGuiaPagamento;
	}

	public void setDescricaoGuiaPagamento(String descricaoGuiaPagamento) {
		this.descricaoGuiaPagamento = descricaoGuiaPagamento;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoReferenciaConta() {
		return descricaoReferenciaConta;
	}

	public void setDescricaoReferenciaConta(String descricaoReferenciaConta) {
		this.descricaoReferenciaConta = descricaoReferenciaConta;
	}

	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	public void setIdDebitoACobrar(String idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}

	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroSequencialAviso() {
		return numeroSequencialAviso;
	}

	public void setNumeroSequencialAviso(String numeroSequencialAviso) {
		this.numeroSequencialAviso = numeroSequencialAviso;
	}

	public String getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(String referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public String getValorDebitoACobrar() {
		return valorDebitoACobrar;
	}

	public void setValorDebitoACobrar(String valorDebitoACobrar) {
		this.valorDebitoACobrar = valorDebitoACobrar;
	}

	public String getValorGuiaPagamento() {
		return valorGuiaPagamento;
	}

	public void setValorGuiaPagamento(String valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoDebitoACobrar() {
		return codigoDebitoACobrar;
	}

	public void setCodigoDebitoACobrar(String codigoDebitoACobrar) {
		this.codigoDebitoACobrar = codigoDebitoACobrar;
	}

	public String getCodigoGuiaPagamento() {
		return codigoGuiaPagamento;
	}

	public void setCodigoGuiaPagamento(String codigoGuiaPagamento) {
		this.codigoGuiaPagamento = codigoGuiaPagamento;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public String getNomeClienteArrecadador() {
		return nomeClienteArrecadador;
	}

	public void setNomeClienteArrecadador(String nomeClienteArrecadador) {
		this.nomeClienteArrecadador = nomeClienteArrecadador;
	}

	public String getUltimaAlteracaoPagamento() {
		return ultimaAlteracaoPagamento;
	}

	public void setUltimaAlteracaoPagamento(String ultimaAlteracaoPagamento) {
		this.ultimaAlteracaoPagamento = ultimaAlteracaoPagamento;
	}

			
}
