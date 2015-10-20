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

import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;

public class CobrancaDocumentoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private CobrancaDocumento cobrancaDocumento;
	private Integer quantidadeItensCobrancaDocumento;
	
	private Integer idOrdemServico;
	
	private String situacaoOrdemServico;
	
	private Usuario usuario;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemConta;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitoACobrar;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiaPagamento;
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar;

	public CobrancaDocumentoHelper() {
		
	}
	
	public Collection getColecaoCobrancaDocumentoItem() {
		return colecaoCobrancaDocumentoItem;
	}

	public void setColecaoCobrancaDocumentoItem(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem) {
		this.colecaoCobrancaDocumentoItem = colecaoCobrancaDocumentoItem;
	}
		
	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}
	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}
	public Integer getQuantidadeItensCobrancaDocumento() {
		return quantidadeItensCobrancaDocumento;
	}
	public void setQuantidadeItensCobrancaDocumento(
			Integer quantidadeItensCobrancaDocumento) {
		this.quantidadeItensCobrancaDocumento = quantidadeItensCobrancaDocumento;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemConta() {
		return colecaoCobrancaDocumentoItemConta;
	}

	public void setColecaoCobrancaDocumentoItemConta(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemConta) {
		this.colecaoCobrancaDocumentoItemConta = colecaoCobrancaDocumentoItemConta;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemDebitoACobrar() {
		return colecaoCobrancaDocumentoItemDebitoACobrar;
	}

	public void setColecaoCobrancaDocumentoItemDebitoACobrar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitoACobrar) {
		this.colecaoCobrancaDocumentoItemDebitoACobrar = colecaoCobrancaDocumentoItemDebitoACobrar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemCreditoARealizar() {
		return colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	public void setColecaoCobrancaDocumentoItemCreditoARealizar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemGuiaPagamento() {
		return colecaoCobrancaDocumentoItemGuiaPagamento;
	}

	public void setColecaoCobrancaDocumentoItemGuiaPagamento(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiaPagamento) {
		this.colecaoCobrancaDocumentoItemGuiaPagamento = colecaoCobrancaDocumentoItemGuiaPagamento;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
