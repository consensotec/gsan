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
package gsan.arrecadacao.pagamento;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.faturamento.GuiaPagamentoGeral;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.faturamento.debito.DebitoTipo;
import gsan.financeiro.FinanciamentoTipo;
import gsan.financeiro.lancamento.LancamentoItemContabil;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferenciaContabil;

    /** persistent field */
    private Date dataEmissao;

    /** persistent field */
    private Date dataVencimento;

    /** persistent field */
    private BigDecimal valorDebito;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Short indicadorMulta;
    
    /** persistent field */
    private Short numeroPrestacaoDebito;
    
    /** persistent field */
    private Short numeroPrestacaoTotal;
    
    private String observacao;
	
	private Short indicadorEmitirObservacao;

    /** nullable persistent field */
    private GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private FinanciamentoTipo financiamentoTipo;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private DebitoTipo debitoTipo;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;

    /** persistent field */
    private Parcelamento parcelamento;

    /** persistent field */
    private DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdanterior;

    /** persistent field */
    private DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdatual;

    /** persistent field */
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private OrdemServico ordemServico;

    /** persistent field */
    private Set guiaPagamentoCategoriaHistoricos;
    
    /** persistent field */
    private GuiaPagamentoGeral origem;
    
    /** persistent field */
    private Usuario usuario;
    
    /** persistent field */
    private String numeroGuiaFatura;


    /**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String toString() {
        return new ToStringBuilder(this)
            .append("gpagId", getId())
            .toString();
    }


	/**
	 * @return Retorna o campo anoMesReferenciaContabil.
	 */
	public int getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}


	/**
	 * @param anoMesReferenciaContabil O anoMesReferenciaContabil a ser setado.
	 */
	public void setAnoMesReferenciaContabil(int anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}


	/**
	 * @return Retorna o campo cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}


	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	/**
	 * @return Retorna o campo dataEmissao.
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}


	/**
	 * @param dataEmissao O dataEmissao a ser setado.
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	/**
	 * @return Retorna o campo dataVencimento.
	 */
	public Date getDataVencimento() {
		return dataVencimento;
	}


	/**
	 * @param dataVencimento O dataVencimento a ser setado.
	 */
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	/**
	 * @return Retorna o campo debitoCreditoSituacaoByDcstIdanterior.
	 */
	public DebitoCreditoSituacao getDebitoCreditoSituacaoByDcstIdanterior() {
		return debitoCreditoSituacaoByDcstIdanterior;
	}


	/**
	 * @param debitoCreditoSituacaoByDcstIdanterior O debitoCreditoSituacaoByDcstIdanterior a ser setado.
	 */
	public void setDebitoCreditoSituacaoByDcstIdanterior(
			DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdanterior) {
		this.debitoCreditoSituacaoByDcstIdanterior = debitoCreditoSituacaoByDcstIdanterior;
	}


	/**
	 * @return Retorna o campo debitoCreditoSituacaoByDcstIdatual.
	 */
	public DebitoCreditoSituacao getDebitoCreditoSituacaoByDcstIdatual() {
		return debitoCreditoSituacaoByDcstIdatual;
	}


	/**
	 * @param debitoCreditoSituacaoByDcstIdatual O debitoCreditoSituacaoByDcstIdatual a ser setado.
	 */
	public void setDebitoCreditoSituacaoByDcstIdatual(
			DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdatual) {
		this.debitoCreditoSituacaoByDcstIdatual = debitoCreditoSituacaoByDcstIdatual;
	}


	/**
	 * @return Retorna o campo debitoTipo.
	 */
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}


	/**
	 * @param debitoTipo O debitoTipo a ser setado.
	 */
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}


	/**
	 * @return Retorna o campo documentoTipo.
	 */
	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}


	/**
	 * @param documentoTipo O documentoTipo a ser setado.
	 */
	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}


	/**
	 * @return Retorna o campo financiamentoTipo.
	 */
	public FinanciamentoTipo getFinanciamentoTipo() {
		return financiamentoTipo;
	}


	/**
	 * @param financiamentoTipo O financiamentoTipo a ser setado.
	 */
	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}


	/**
	 * @return Retorna o campo guiaPagamentoCategoriaHistoricos.
	 */
	public Set getGuiaPagamentoCategoriaHistoricos() {
		return guiaPagamentoCategoriaHistoricos;
	}


	/**
	 * @param guiaPagamentoCategoriaHistoricos O guiaPagamentoCategoriaHistoricos a ser setado.
	 */
	public void setGuiaPagamentoCategoriaHistoricos(
			Set guiaPagamentoCategoriaHistoricos) {
		this.guiaPagamentoCategoriaHistoricos = guiaPagamentoCategoriaHistoricos;
	}


	/**
	 * @return Retorna o campo guiaPagamentoGeral.
	 */
	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}


	/**
	 * @param guiaPagamentoGeral O guiaPagamentoGeral a ser setado.
	 */
	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}


	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}


	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	/**
	 * @return Retorna o campo indicadorMulta.
	 */
	public Short getIndicadorMulta() {
		return indicadorMulta;
	}


	/**
	 * @param indicadorMulta O indicadorMulta a ser setado.
	 */
	public void setIndicadorMulta(Short indicadorMulta) {
		this.indicadorMulta = indicadorMulta;
	}


	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}


	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}


	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}


	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}


	/**
	 * @return Retorna o campo ordemServico.
	 */
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}


	/**
	 * @param ordemServico O ordemServico a ser setado.
	 */
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}


	/**
	 * @return Retorna o campo parcelamento.
	 */
	public Parcelamento getParcelamento() {
		return parcelamento;
	}


	/**
	 * @param parcelamento O parcelamento a ser setado.
	 */
	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}


	/**
	 * @return Retorna o campo registroAtendimento.
	 */
	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}


	/**
	 * @param registroAtendimento O registroAtendimento a ser setado.
	 */
	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}


	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	/**
	 * @return Retorna o campo valorDebito.
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}


	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}


	public GuiaPagamentoGeral getOrigem() {
		return origem;
	}


	public void setOrigem(GuiaPagamentoGeral origem) {
		this.origem = origem;
	}


	public Short getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}


	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}


	public Short getNumeroPrestacaoTotal() {
		return numeroPrestacaoTotal;
	}


	public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal) {
		this.numeroPrestacaoTotal = numeroPrestacaoTotal;
	}

	public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
//        if(getNumeroPrestacaoDebito() != 0 && getNumeroPrestacaoTotal() != 0){
            prestacaoFormatada = prestacaoFormatada + getNumeroPrestacaoDebito() + "/" + getNumeroPrestacaoTotal();
//        }
        
        return  prestacaoFormatada ;
    }


	/**
	 * @return Retorna o campo indicadorEmitirObservacao.
	 */
	public Short getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}


	/**
	 * @param indicadorEmitirObservacao O indicadorEmitirObservacao a ser setado.
	 */
	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}


	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}


	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return Returns the numeroGuiaFatura.
	 */
	public String getNumeroGuiaFatura() {
		return numeroGuiaFatura;
	}

	/**
	 * @param numeroGuiaFatura The numeroGuiaFatura to set.
	 */
	public void setNumeroGuiaFatura(String numeroGuiaFatura) {
		this.numeroGuiaFatura = numeroGuiaFatura;
	}
	
	
}
