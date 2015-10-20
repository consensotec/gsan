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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descri��o da classe>>
 * 
 * @author lms
 * @date 14/08/2006
 */
public class AtualizarOrdemServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private OrdemServico ordemServico = new OrdemServico();
	
	private String idRegistroAtendimento;
	private String forward;
	
	private String idServicoTipo;
	private String descricaoServicoTipo;	
	private String idOrdemServicoReferencia;
	private String descricaoOrdemServicoReferencia;
	private String idServicoTipoReferencia;
	private String descricaoServicoTipoReferencia;
	private String valorServicoOriginal;
	private String valorServicoAtual;
	private String idPrioridadeServicoOriginal;
	private String descricaoPrioridadeServicoOriginal;
	private String idPrioridadeServicoAtual;	
	private String observacao;
	
	//dados gerais
	private String numeroOS;

	private String situacaoOS;

	private String situacaoOSId;

	private String numeroRA;

	private String situacaoRA;

	private String numeroDocumentoCobranca;

	private String dataGeracao;

	private String numeroOSReferencia;

	private String tipoServicoOSId;

	private String tipoServicoOSDescricao;

	private String tipoServicoReferenciaId;

	private String tipoServicoReferenciaDescricao;

	private String retornoOSReferida;

	private String observacaoDadosGerais;

	//private String valorServicoOriginal;

	//private String valorServicoAtual;

	private String prioridadeOriginal;

	private String prioridadeAtual;

	private String unidadeGeracaoId;

	private String unidadeGeracaoDescricao;

	private String usuarioGeracaoId;

	private String usuarioGeracaoNome;

	private String dataUltimaEmissao;

	
	public String getDescricaoOrdemServicoReferencia() {
		return descricaoOrdemServicoReferencia;
	}
	public void setDescricaoOrdemServicoReferencia(
			String descricaoOrdemServicoReferencia) {
		this.descricaoOrdemServicoReferencia = descricaoOrdemServicoReferencia;
	}
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	public String getDescricaoServicoTipoReferencia() {
		return descricaoServicoTipoReferencia;
	}
	public void setDescricaoServicoTipoReferencia(
			String descricaoServicoTipoReferencia) {
		this.descricaoServicoTipoReferencia = descricaoServicoTipoReferencia;
	}
	public String getIdOrdemServicoReferencia() {
		return idOrdemServicoReferencia;
	}
	public void setIdOrdemServicoReferencia(String idOrdemServicoReferencia) {
		this.idOrdemServicoReferencia = idOrdemServicoReferencia;
	}
	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}
	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}
	public String getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public String getIdServicoTipoReferencia() {
		return idServicoTipoReferencia;
	}
	public void setIdServicoTipoReferencia(String idServicoTipoReferencia) {
		this.idServicoTipoReferencia = idServicoTipoReferencia;
	}
	public String getIdPrioridadeServicoAtual() {
		return idPrioridadeServicoAtual;
	}
	public void setIdPrioridadeServicoAtual(String idPrioridadeServicoAtual) {
		this.idPrioridadeServicoAtual = idPrioridadeServicoAtual;
	}
	public String getDescricaoPrioridadeServicoOriginal() {
		return descricaoPrioridadeServicoOriginal;
	}
	public void setDescricaoPrioridadeServicoOriginal(
			String descricaoPrioridadeServicoOriginal) {
		this.descricaoPrioridadeServicoOriginal = descricaoPrioridadeServicoOriginal;
	}
	public String getValorServicoAtual() {
		return valorServicoAtual;
	}
	public void setValorServicoAtual(String valorServicoAtual) {
		this.valorServicoAtual = valorServicoAtual;
	}
	public String getValorServicoOriginal() {
		return valorServicoOriginal;
	}
	public void setValorServicoOriginal(String valorServicoOriginal) {
		this.valorServicoOriginal = valorServicoOriginal;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public String getIdPrioridadeServicoOriginal() {
		return idPrioridadeServicoOriginal;
	}
	public void setIdPrioridadeServicoOriginal(String idPrioridadeServicoOriginal) {
		this.idPrioridadeServicoOriginal = idPrioridadeServicoOriginal;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	
	public OrdemServico setFormValues(OrdemServico ordemServico) {

		//valor do servi�o atual
		if (getValorServicoAtual() != null) {
			try {
				ordemServico.setValorAtual(new BigDecimal(getValorServicoAtual()));
			} catch (NumberFormatException e) {			
			}
		}
		
		//prioridade do servi�o atual
		Integer idPrioridadeServicoAtual = Util.converterStringParaInteger(getIdPrioridadeServicoAtual());
		
		if (Util.validarNumeroMaiorQueZERO(idPrioridadeServicoAtual)) {
			ServicoTipoPrioridade servicoTipoPrioridadeAtual = new ServicoTipoPrioridade();
			servicoTipoPrioridadeAtual.setId(idPrioridadeServicoAtual);
			ordemServico.setServicoTipoPrioridadeAtual(servicoTipoPrioridadeAtual);
		}
		
		//observacao
		ordemServico.setObservacao(getObservacao());
		
		//data �ltima altera��o
		ordemServico.setUltimaAlteracao(new Date());
		
		return ordemServico;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDataUltimaEmissao() {
		return dataUltimaEmissao;
	}
	public void setDataUltimaEmissao(String dataUltimaEmissao) {
		this.dataUltimaEmissao = dataUltimaEmissao;
	}
	public String getNumeroDocumentoCobranca() {
		return numeroDocumentoCobranca;
	}
	public void setNumeroDocumentoCobranca(String numeroDocumentoCobranca) {
		this.numeroDocumentoCobranca = numeroDocumentoCobranca;
	}
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getNumeroOSReferencia() {
		return numeroOSReferencia;
	}
	public void setNumeroOSReferencia(String numeroOSReferencia) {
		this.numeroOSReferencia = numeroOSReferencia;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getObservacaoDadosGerais() {
		return observacaoDadosGerais;
	}
	public void setObservacaoDadosGerais(String observacaoDadosGerais) {
		this.observacaoDadosGerais = observacaoDadosGerais;
	}
	public String getPrioridadeAtual() {
		return prioridadeAtual;
	}
	public void setPrioridadeAtual(String prioridadeAtual) {
		this.prioridadeAtual = prioridadeAtual;
	}
	public String getPrioridadeOriginal() {
		return prioridadeOriginal;
	}
	public void setPrioridadeOriginal(String prioridadeOriginal) {
		this.prioridadeOriginal = prioridadeOriginal;
	}
	public String getRetornoOSReferida() {
		return retornoOSReferida;
	}
	public void setRetornoOSReferida(String retornoOSReferida) {
		this.retornoOSReferida = retornoOSReferida;
	}
	public String getSituacaoOS() {
		return situacaoOS;
	}
	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}
	public String getSituacaoOSId() {
		return situacaoOSId;
	}
	public void setSituacaoOSId(String situacaoOSId) {
		this.situacaoOSId = situacaoOSId;
	}
	public String getSituacaoRA() {
		return situacaoRA;
	}
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}
	public String getTipoServicoOSDescricao() {
		return tipoServicoOSDescricao;
	}
	public void setTipoServicoOSDescricao(String tipoServicoOSDescricao) {
		this.tipoServicoOSDescricao = tipoServicoOSDescricao;
	}
	public String getTipoServicoOSId() {
		return tipoServicoOSId;
	}
	public void setTipoServicoOSId(String tipoServicoOSId) {
		this.tipoServicoOSId = tipoServicoOSId;
	}
	public String getTipoServicoReferenciaDescricao() {
		return tipoServicoReferenciaDescricao;
	}
	public void setTipoServicoReferenciaDescricao(
			String tipoServicoReferenciaDescricao) {
		this.tipoServicoReferenciaDescricao = tipoServicoReferenciaDescricao;
	}
	public String getTipoServicoReferenciaId() {
		return tipoServicoReferenciaId;
	}
	public void setTipoServicoReferenciaId(String tipoServicoReferenciaId) {
		this.tipoServicoReferenciaId = tipoServicoReferenciaId;
	}
	public String getUnidadeGeracaoDescricao() {
		return unidadeGeracaoDescricao;
	}
	public void setUnidadeGeracaoDescricao(String unidadeGeracaoDescricao) {
		this.unidadeGeracaoDescricao = unidadeGeracaoDescricao;
	}
	public String getUnidadeGeracaoId() {
		return unidadeGeracaoId;
	}
	public void setUnidadeGeracaoId(String unidadeGeracaoId) {
		this.unidadeGeracaoId = unidadeGeracaoId;
	}
	public String getUsuarioGeracaoId() {
		return usuarioGeracaoId;
	}
	public void setUsuarioGeracaoId(String usuarioGeracaoId) {
		this.usuarioGeracaoId = usuarioGeracaoId;
	}
	public String getUsuarioGeracaoNome() {
		return usuarioGeracaoNome;
	}
	public void setUsuarioGeracaoNome(String usuarioGeracaoNome) {
		this.usuarioGeracaoNome = usuarioGeracaoNome;
	}
	
	public void resetarConsultarDadosOSPopup() {
    	this.numeroOS = null;
    	this.situacaoOS = null;
    	this.situacaoOSId = null;
        this.numeroRA = null;
        this.situacaoRA = null;
        this.numeroDocumentoCobranca = null;
        this.dataGeracao = null;
        this.numeroOSReferencia = null;
        this.tipoServicoOSId = null;
        this.tipoServicoOSDescricao = null;
        this.tipoServicoReferenciaId = null;
        this.tipoServicoReferenciaDescricao = null;
        this.retornoOSReferida = null;
        this.observacao = null;
        this.valorServicoOriginal = null;
        this.valorServicoAtual = null;
        this.prioridadeOriginal = null;
        this.prioridadeAtual = null;
        this.unidadeGeracaoId = null;
        this.unidadeGeracaoDescricao = null;
        this.usuarioGeracaoId = null;
        this.usuarioGeracaoNome = null;
        this.dataUltimaEmissao = null;
	}
	
	
	
}
