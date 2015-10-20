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
package gcom.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PagamentoHistorico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private BigDecimal valorPagamento;

    /** persistent field */
    private BigDecimal valorExcedente;
    
    /** persistent field */
    private Integer anoMesReferenciaPagamento;

    /** persistent field */
    private Date dataPagamento;

    /** persistent field */
    private Integer anoMesReferenciaArrecadacao;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Integer codigoAgente;

    /** persistent field */
    private ArrecadacaoForma arrecadacaoForma;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private ContaGeral contaGeral;

    /** persistent field */
    private Localidade localidade;
    
    /** persistent field */
	private Short indicadorExpurgado;

    /** persistent field */
    private gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual;

    /** persistent field */
    private gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior;

    /** persistent field */
    private ArrecadadorMovimentoItem arrecadadorMovimentoItem;

    /** persistent field */
    private gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral;
    
	/** persistent field */
	private DebitoACobrarGeral debitoACobrarGeral;
    

    /** persistent field */
    private AvisoBancario avisoBancario;

    /** persistent field */
    private DebitoTipo debitoTipo;

    /** persistent field */
    private Cliente cliente;
    
    /** persistent field */
    private CobrancaDocumento cobrancaDocumentoAgregador;
    
    /** persistent field */
    private DocumentoTipo documentoTipoAgregador;
    
    /** persistent field */
    private Fatura fatura;
    
    /** nullable persistent field */
    private Date dataHoraProcessamento;
    
    /** persistent field */
    private Integer anoMesReferenciaHistorico;
    

	public final static Short INDICADOR_EXPURGADO_SIM = new Short("1");
	public final static Short INDICADOR_EXPURGADO_NAO = new Short("2");

    /** full constructor */
    public PagamentoHistorico(BigDecimal valorPagamento, Integer anoMesReferenciaPagamento, Date dataPagamento, Integer anoMesReferenciaArrecadacao, Date ultimaAlteracao, Integer codigoAgente, ArrecadacaoForma arrecadacaoForma, Imovel imovel, DocumentoTipo documentoTipo, ContaGeral contaGeral, Localidade localidade, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior, ArrecadadorMovimentoItem arrecadadorMovimentoItem, gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral, AvisoBancario avisoBancario, DebitoTipo debitoTipo, Cliente cliente) {
        this.valorPagamento = valorPagamento;
        this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
        this.dataPagamento = dataPagamento;
        this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.codigoAgente = codigoAgente;
        this.arrecadacaoForma = arrecadacaoForma;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.localidade = localidade;
        this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
        this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
        this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.avisoBancario = avisoBancario;
        this.debitoTipo = debitoTipo;
        this.cliente = cliente;
    }

    /** default constructor */
    public PagamentoHistorico() {
    }

    /** minimal constructor */
    public PagamentoHistorico(BigDecimal valorPagamento, Integer anoMesReferenciaPagamento, Date dataPagamento, Integer anoMesReferenciaArrecadacao, ArrecadacaoForma arrecadacaoForma, Imovel imovel, DocumentoTipo documentoTipo, ContaGeral contaGeral, Localidade localidade, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual, gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior, ArrecadadorMovimentoItem arrecadadorMovimentoItem, gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral, AvisoBancario avisoBancario, DebitoTipo debitoTipo, Cliente cliente) {
        this.valorPagamento = valorPagamento;
        this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
        this.dataPagamento = dataPagamento;
        this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
        this.arrecadacaoForma = arrecadacaoForma;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.contaGeral = contaGeral;
        this.localidade = localidade;
        this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
        this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
        this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.avisoBancario = avisoBancario;
        this.debitoTipo = debitoTipo;
        this.cliente = cliente;
    }

   
	
	public String getFormatarAnoMesParaMesAnoArrecadacao() {

		String anoMesArrecadacaoRecebido = "" + this.getAnoMesReferenciaArrecadacao();
		String mesArrecadacao = anoMesArrecadacaoRecebido.substring(4, 6);
		String anoArrecadacao = anoMesArrecadacaoRecebido.substring(0, 4);
		String anoMesArrecadacaoFormatado = mesArrecadacao + "/" + anoArrecadacao;

		return anoMesArrecadacaoFormatado.toString();
	}

	public String getFormatarAnoMesReferenciaPagamento() {

		if ( this.getAnoMesReferenciaPagamento() != null ){		
			String anoMesReferenciaPagamento = "" + this.getAnoMesReferenciaPagamento();
			String mesPagamento = anoMesReferenciaPagamento.substring(4, 6);
			String anoPagamento = anoMesReferenciaPagamento.substring(0, 4);
			String anoMesPagamentoFormatado = mesPagamento + "/" + anoPagamento;
	
			return anoMesPagamentoFormatado.toString();
		} else {
			return "";
		}
	}

	/**
	 * @return Retorna o campo anoMesReferenciaArrecadacao.
	 */
	public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	/**
	 * @param anoMesReferenciaArrecadacao O anoMesReferenciaArrecadacao a ser setado.
	 */
	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	/**
	 * @return Retorna o campo anoMesReferenciaPagamento.
	 */
	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	/**
	 * @param anoMesReferenciaPagamento O anoMesReferenciaPagamento a ser setado.
	 */
	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	/**
	 * @return Retorna o campo arrecadacaoForma.
	 */
	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	/**
	 * @param arrecadacaoForma O arrecadacaoForma a ser setado.
	 */
	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}

	/**
	 * @return Retorna o campo arrecadadorMovimentoItem.
	 */
	public ArrecadadorMovimentoItem getArrecadadorMovimentoItem() {
		return arrecadadorMovimentoItem;
	}

	/**
	 * @param arrecadadorMovimentoItem O arrecadadorMovimentoItem a ser setado.
	 */
	public void setArrecadadorMovimentoItem(
			ArrecadadorMovimentoItem arrecadadorMovimentoItem) {
		this.arrecadadorMovimentoItem = arrecadadorMovimentoItem;
	}

	/**
	 * @return Retorna o campo avisoBancario.
	 */
	public AvisoBancario getAvisoBancario() {
		return avisoBancario;
	}

	/**
	 * @param avisoBancario O avisoBancario a ser setado.
	 */
	public void setAvisoBancario(AvisoBancario avisoBancario) {
		this.avisoBancario = avisoBancario;
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
	 * @return Retorna o campo codigoAgente.
	 */
	public Integer getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * @param codigoAgente O codigoAgente a ser setado.
	 */
	public void setCodigoAgente(Integer codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * @return Retorna o campo conta.
	 */
	public ContaGeral getContaGeral() {
		return this.contaGeral;
	}

	/**
	 * @param conta O conta a ser setado.
	 */
	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
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
	 * @return Retorna o campo pagamentoSituacaoAnterior.
	 */
	public gcom.arrecadacao.pagamento.PagamentoSituacao getPagamentoSituacaoAnterior() {
		return pagamentoSituacaoAnterior;
	}

	/**
	 * @param pagamentoSituacaoAnterior O pagamentoSituacaoAnterior a ser setado.
	 */
	public void setPagamentoSituacaoAnterior(
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAnterior) {
		this.pagamentoSituacaoAnterior = pagamentoSituacaoAnterior;
	}

	/**
	 * @return Retorna o campo pagamentoSituacaoAtual.
	 */
	public gcom.arrecadacao.pagamento.PagamentoSituacao getPagamentoSituacaoAtual() {
		return this.pagamentoSituacaoAtual;
	}

	/**
	 * @param pagamentoSituacaoAtual O pagamentoSituacaoAtual a ser setado.
	 */
	public void setPagamentoSituacaoAtual(
			gcom.arrecadacao.pagamento.PagamentoSituacao pagamentoSituacaoAtual) {
		this.pagamentoSituacaoAtual = pagamentoSituacaoAtual;
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
	 * @return Retorna o campo valorExcedente.
	 */
	public BigDecimal getValorExcedente() {
		return valorExcedente;
	}

	/**
	 * @param valorExcedente O valorExcedente a ser setado.
	 */
	public void setValorExcedente(BigDecimal valorExcedente) {
		this.valorExcedente = valorExcedente;
	}

	/**
	 * @return Retorna o campo valorPagamento.
	 */
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	/**
	 * @param valorPagamento O valorPagamento a ser setado.
	 */
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Short getIndicadorExpurgado() {
		return indicadorExpurgado;
	}

	public void setIndicadorExpurgado(Short indicadorExpurgado) {
		this.indicadorExpurgado = indicadorExpurgado;
	}

	public CobrancaDocumento getCobrancaDocumentoAgregador() {
		return cobrancaDocumentoAgregador;
	}

	public void setCobrancaDocumentoAgregador(
			CobrancaDocumento cobrancaDocumentoAgregador) {
		this.cobrancaDocumentoAgregador = cobrancaDocumentoAgregador;
	}

	public Date getDataHoraProcessamento() {
		return dataHoraProcessamento;
	}

	public void setDataHoraProcessamento(Date dataHoraProcessamento) {
		this.dataHoraProcessamento = dataHoraProcessamento;
	}

	public DocumentoTipo getDocumentoTipoAgregador() {
		return documentoTipoAgregador;
	}

	public void setDocumentoTipoAgregador(DocumentoTipo documentoTipoAgregador) {
		this.documentoTipoAgregador = documentoTipoAgregador;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public gcom.faturamento.GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Integer getAnoMesReferenciaHistorico() {
		return anoMesReferenciaHistorico;
	}

	public void setAnoMesReferenciaHistorico(Integer anoMesReferenciaHistorico) {
		this.anoMesReferenciaHistorico = anoMesReferenciaHistorico;
	}
}
