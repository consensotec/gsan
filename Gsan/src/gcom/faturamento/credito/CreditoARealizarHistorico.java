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
package gcom.faturamento.credito;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CreditoARealizarHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** persistent field */
	private Date geracaoCreditoARealizar;

	/** nullable persistent field */
	private Integer anoMesReferenciaCredito;

	/** nullable persistent field */
	private Integer anoMesCobrancaCredito;

	/** nullable persistent field */
	private BigDecimal valorResidualMesAnterior;

	/** nullable persistent field */
	private BigDecimal valorCredito;

	/** nullable persistent field */
	private Short prestacaoCredito;

	/** nullable persistent field */
	private Short prestacaoRealizadas;

	/** nullable persistent field */
	private Integer codigoSetorComercial;

	/** nullable persistent field */
	private Integer numeroQuadra;

	/** nullable persistent field */
	private Short lote;

	/** nullable persistent field */
	private Short subLote;

	/** nullable persistent field */
	private Integer anoMesReferenciaContabil;

	/** nullable persistent field */
	private Date ultimaAlteracao;

    private Short numeroParcelaBonus;
    
	/** persistent field */
	private LancamentoItemContabil lancamentoItemContabil;

	/** persistent field */
	private DebitoCreditoSituacao debitoCreditoSituacaoAtual;

	/** persistent field */
	private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

	/** persistent field */
	private RegistroAtendimento registroAtendimento;

	/** persistent field */
	private Imovel imovel;

	/** persistent field */
	private OrdemServico ordemServico;

	/** persistent field */
	private Quadra quadra;

	/** persistent field */
	private Localidade localidade;

	/** persistent field */
	private gcom.faturamento.credito.CreditoTipo creditoTipo;

	/** persistent field */
	private gcom.faturamento.credito.CreditoOrigem creditoOrigem;

	/** persistent field */
	private Parcelamento parcelamento;

	private DocumentoTipo documentoTipo;
	
	private CreditoARealizarGeral origem;
	
    /** persistent field */
    private Usuario usuario;

	/** full constructor */
	public CreditoARealizarHistorico(Date geracaoCreditoARealizar,
			Integer anoMesReferenciaCredito, Integer anoMesCobrancaCredito,
			BigDecimal valorResidualMesAnterior, BigDecimal valorCredito,
			Short prestacaoCredito, Short prestacaoRealizadas,
			Integer codigoSetorComercial, Integer numeroQuadra, Short lote,
			Short subLote, Integer anoMesReferenciaContabil,
			Date ultimaAlteracao,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			OrdemServico ordemServico, Quadra quadra, Localidade localidade,
			gcom.faturamento.credito.CreditoTipo creditoTipo,
			gcom.faturamento.credito.CreditoOrigem creditoOrigem,
			Parcelamento parcelamento, DocumentoTipo documentoTipo, Usuario usuario) {
		this.geracaoCreditoARealizar = geracaoCreditoARealizar;
		this.anoMesReferenciaCredito = anoMesReferenciaCredito;
		this.anoMesCobrancaCredito = anoMesCobrancaCredito;
		this.valorResidualMesAnterior = valorResidualMesAnterior;
		this.valorCredito = valorCredito;
		this.prestacaoCredito = prestacaoCredito;
		this.prestacaoRealizadas = prestacaoRealizadas;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.ultimaAlteracao = ultimaAlteracao;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ordemServico = ordemServico;
		this.quadra = quadra;
		this.localidade = localidade;
		this.creditoTipo = creditoTipo;
		this.creditoOrigem = creditoOrigem;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.usuario = usuario;
	}

	/** default constructor */
	public CreditoARealizarHistorico() {
	}

	/** minimal constructor */
	public CreditoARealizarHistorico(Date geracaoCreditoARealizar,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			OrdemServico ordemServico, Quadra quadra, Localidade localidade,
			gcom.faturamento.credito.CreditoTipo creditoTipo,
			gcom.faturamento.credito.CreditoOrigem creditoOrigem,
			Parcelamento parcelamento, DocumentoTipo documentoTipo) {
		this.geracaoCreditoARealizar = geracaoCreditoARealizar;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ordemServico = ordemServico;
		this.quadra = quadra;
		this.localidade = localidade;
		this.creditoTipo = creditoTipo;
		this.creditoOrigem = creditoOrigem;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getGeracaoCreditoARealizar() {
		return this.geracaoCreditoARealizar;
	}

	public void setGeracaoCreditoARealizar(Date geracaoCreditoARealizar) {
		this.geracaoCreditoARealizar = geracaoCreditoARealizar;
	}

	public Integer getAnoMesReferenciaCredito() {
		return this.anoMesReferenciaCredito;
	}

	public void setAnoMesReferenciaCredito(Integer anoMesReferenciaCredito) {
		this.anoMesReferenciaCredito = anoMesReferenciaCredito;
	}

	public Integer getAnoMesCobrancaCredito() {
		return this.anoMesCobrancaCredito;
	}

	public void setAnoMesCobrancaCredito(Integer anoMesCobrancaCredito) {
		this.anoMesCobrancaCredito = anoMesCobrancaCredito;
	}

	public BigDecimal getValorResidualMesAnterior() {
		return this.valorResidualMesAnterior;
	}

	public void setValorResidualMesAnterior(BigDecimal valorResidualMesAnterior) {
		this.valorResidualMesAnterior = valorResidualMesAnterior;
	}

	public BigDecimal getValorCredito() {
		return this.valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	public Short getPrestacaoCredito() {
		return this.prestacaoCredito;
	}

	public void setPrestacaoCredito(Short prestacaoCredito) {
		this.prestacaoCredito = prestacaoCredito;
	}

	public Short getPrestacaoRealizadas() {
		return this.prestacaoRealizadas;
	}

	public void setPrestacaoRealizadas(Short prestacaoRealizadas) {
		this.prestacaoRealizadas = prestacaoRealizadas;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getLote() {
		return this.lote;
	}

	public void setLote(Short lote) {
		this.lote = lote;
	}

	public Short getSubLote() {
		return this.subLote;
	}

	public void setSubLote(Short subLote) {
		this.subLote = subLote;
	}

	public Integer getAnoMesReferenciaContabil() {
		return this.anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return this.lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return this.debitoCreditoSituacaoAtual;
	}

	public void setDebitoCreditoSituacaoAtual(
			DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return this.debitoCreditoSituacaoAnterior;
	}

	public void setDebitoCreditoSituacaoAnterior(
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.faturamento.credito.CreditoTipo getCreditoTipo() {
		return this.creditoTipo;
	}

	public void setCreditoTipo(gcom.faturamento.credito.CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public gcom.faturamento.credito.CreditoOrigem getCreditoOrigem() {
		return this.creditoOrigem;
	}

	public void setCreditoOrigem(
			gcom.faturamento.credito.CreditoOrigem creditoOrigem) {
		this.creditoOrigem = creditoOrigem;
	}

	public Parcelamento getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Returns the documentoTipo.
	 */
	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	/**
	 * @param documentoTipo
	 *            The documentoTipo to set.
	 */
	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public CreditoARealizarGeral getOrigem() {
		return origem;
	}

	public void setOrigem(CreditoARealizarGeral origem) {
		this.origem = origem;
	}

    public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }

    /**
     * Realiza o calculo de quantas parcelas falta para creditar  
     * numero total de presta��es menos
     * numero de parcelas realizadas menos
     * numero de parcelas bonus
     * 
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public short getParcelasRestanteComBonus(){
        
       short retorno = Short.parseShort(""+
               (getPrestacaoCredito() -  getPrestacaoRealizadas()));
       
      if (getNumeroParcelaBonus() != null){
          retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
      }
            
        return retorno;
    }
    
    /**
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public BigDecimal getValorTotalComBonus() {

        BigDecimal retornoDivisao = new BigDecimal("0.00");
        BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
        BigDecimal retornoSubtracao = new BigDecimal("0.00");
        BigDecimal retorno = new BigDecimal("0.00");

        retornoDivisao = Util.dividirArredondando(this.valorCredito,new BigDecimal(prestacaoCredito));

        if (numeroParcelaBonus != null){
            retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(prestacaoRealizadas + numeroParcelaBonus));
        }else{
            retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(prestacaoRealizadas));
        }

        retornoSubtracao = this.valorCredito.subtract(retornoMultiplicacao);
        if (valorResidualMesAnterior != null) {
            retorno = retornoSubtracao.add(valorResidualMesAnterior);
        }
        retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

        return retorno;
    }
    
    /**
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public short getNumeroPrestacaoCreditoMenosBonus() {
        short retorno = getPrestacaoCredito();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }

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


}
