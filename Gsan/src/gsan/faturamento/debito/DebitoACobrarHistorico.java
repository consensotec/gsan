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
package gsan.faturamento.debito;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cobranca.CobrancaForma;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.ParcelamentoGrupo;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.financeiro.FinanciamentoTipo;
import gsan.financeiro.lancamento.LancamentoItemContabil;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoACobrarHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date debitoGeradoRealizar;

    /** nullable persistent field */
    private Integer anoMesReferenciaDebito;

    /** nullable persistent field */
    private Integer anoMesCobrancaDebito;

    /** nullable persistent field */
    private Integer anoMesReferenciaContabil;

    /** nullable persistent field */
    private Short indicadorSituacaoDebito;

    /** persistent field */
    private BigDecimal valorDebito;

    /** persistent field */
    private short prestacaoDebito;

    /** persistent field */
    private short prestacaoCobradas;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private Short lote;

    /** nullable persistent field */
    private Short sublote;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short numeroParcelaBonus;

    /** nullable persistent field */
    private BigDecimal percentualTaxaJurosFinanciamento;
    
    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;

    /** persistent field */
    private gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual;

    /** persistent field */
    private gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

    /** persistent field */
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private FinanciamentoTipo financiamentoTipo;

    /** persistent field */
    private OrdemServico ordemServico;

    /** persistent field */
    private CobrancaForma cobrancaForma;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private ParcelamentoGrupo parcelamentoGrupo;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    private Parcelamento parcelamento;

    /** persistent field */
    private gsan.faturamento.debito.DebitoTipo debitoTipo;
    
    /** persistent field */
    private gsan.faturamento.debito.DebitoACobrarGeral origem;
    
    /** persistent field */
    private Usuario usuario;

    /** full constructor */
    public DebitoACobrarHistorico(Date debitoGeradoRealizar, Integer anoMesReferenciaDebito, Integer anoMesCobrancaDebito, Integer anoMesReferenciaContabil, Short indicadorSituacaoDebito, BigDecimal valorDebito, short prestacaoDebito, short prestacaoCobradas, Integer codigoSetorComercial, Integer numeroQuadra, Short lote, Short sublote, Date ultimaAlteracao, LancamentoItemContabil lancamentoItemContabil, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior, RegistroAtendimento registroAtendimento, Imovel imovel, FinanciamentoTipo financiamentoTipo, OrdemServico ordemServico, CobrancaForma cobrancaForma, Quadra quadra, Localidade localidade, ParcelamentoGrupo parcelamentoGrupo, DocumentoTipo documentoTipo, Parcelamento parcelamento, gsan.faturamento.debito.DebitoTipo debitoTipo, BigDecimal percentualTaxaJurosFinanciamento, Usuario usuario) {
        this.debitoGeradoRealizar = debitoGeradoRealizar;
        this.anoMesReferenciaDebito = anoMesReferenciaDebito;
        this.anoMesCobrancaDebito = anoMesCobrancaDebito;
        this.anoMesReferenciaContabil = anoMesReferenciaContabil;
        this.indicadorSituacaoDebito = indicadorSituacaoDebito;
        this.valorDebito = valorDebito;
        this.prestacaoDebito = prestacaoDebito;
        this.prestacaoCobradas = prestacaoCobradas;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.lote = lote;
        this.sublote = sublote;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.registroAtendimento = registroAtendimento;
        this.imovel = imovel;
        this.financiamentoTipo = financiamentoTipo;
        this.ordemServico = ordemServico;
        this.cobrancaForma = cobrancaForma;
        this.quadra = quadra;
        this.localidade = localidade;
        this.parcelamentoGrupo = parcelamentoGrupo;
        this.documentoTipo = documentoTipo;
        this.parcelamento = parcelamento;
        this.debitoTipo = debitoTipo;
        this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
        this.usuario = usuario;
    }

    /** default constructor */
    public DebitoACobrarHistorico() {
    }

    /** minimal constructor */
    public DebitoACobrarHistorico(Date debitoGeradoRealizar, BigDecimal valorDebito, short prestacaoDebito, short prestacaoCobradas, LancamentoItemContabil lancamentoItemContabil, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior, RegistroAtendimento registroAtendimento, Imovel imovel, FinanciamentoTipo financiamentoTipo, OrdemServico ordemServico, CobrancaForma cobrancaForma, Quadra quadra, Localidade localidade, ParcelamentoGrupo parcelamentoGrupo, DocumentoTipo documentoTipo, Parcelamento parcelamento, gsan.faturamento.debito.DebitoTipo debitoTipo) {
        this.debitoGeradoRealizar = debitoGeradoRealizar;
        this.valorDebito = valorDebito;
        this.prestacaoDebito = prestacaoDebito;
        this.prestacaoCobradas = prestacaoCobradas;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.registroAtendimento = registroAtendimento;
        this.imovel = imovel;
        this.financiamentoTipo = financiamentoTipo;
        this.ordemServico = ordemServico;
        this.cobrancaForma = cobrancaForma;
        this.quadra = quadra;
        this.localidade = localidade;
        this.parcelamentoGrupo = parcelamentoGrupo;
        this.documentoTipo = documentoTipo;
        this.parcelamento = parcelamento;
        this.debitoTipo = debitoTipo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDebitoGeradoRealizar() {
        return this.debitoGeradoRealizar;
    }

    public void setDebitoGeradoRealizar(Date debitoGeradoRealizar) {
        this.debitoGeradoRealizar = debitoGeradoRealizar;
    }

    public Integer getAnoMesReferenciaDebito() {
        return this.anoMesReferenciaDebito;
    }

    public void setAnoMesReferenciaDebito(Integer anoMesReferenciaDebito) {
        this.anoMesReferenciaDebito = anoMesReferenciaDebito;
    }

    public Integer getAnoMesCobrancaDebito() {
        return this.anoMesCobrancaDebito;
    }

    public void setAnoMesCobrancaDebito(Integer anoMesCobrancaDebito) {
        this.anoMesCobrancaDebito = anoMesCobrancaDebito;
    }

    public Integer getAnoMesReferenciaContabil() {
        return this.anoMesReferenciaContabil;
    }

    public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
        this.anoMesReferenciaContabil = anoMesReferenciaContabil;
    }

    public Short getIndicadorSituacaoDebito() {
        return this.indicadorSituacaoDebito;
    }

    public void setIndicadorSituacaoDebito(Short indicadorSituacaoDebito) {
        this.indicadorSituacaoDebito = indicadorSituacaoDebito;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public short getPrestacaoDebito() {
        return this.prestacaoDebito;
    }

    public void setPrestacaoDebito(short prestacaoDebito) {
        this.prestacaoDebito = prestacaoDebito;
    }

    public short getPrestacaoCobradas() {
        return this.prestacaoCobradas;
    }

    public void setPrestacaoCobradas(short prestacaoCobradas) {
        this.prestacaoCobradas = prestacaoCobradas;
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

    public Short getSublote() {
        return this.sublote;
    }

    public void setSublote(Short sublote) {
        this.sublote = sublote;
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

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public gsan.faturamento.debito.DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
        return this.debitoCreditoSituacaoAtual;
    }

    public void setDebitoCreditoSituacaoAtual(gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
    }

    public gsan.faturamento.debito.DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
        return this.debitoCreditoSituacaoAnterior;
    }

    public void setDebitoCreditoSituacaoAnterior(gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
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

    public FinanciamentoTipo getFinanciamentoTipo() {
        return this.financiamentoTipo;
    }

    public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
        this.financiamentoTipo = financiamentoTipo;
    }

    public OrdemServico getOrdemServico() {
        return this.ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public CobrancaForma getCobrancaForma() {
        return this.cobrancaForma;
    }

    public void setCobrancaForma(CobrancaForma cobrancaForma) {
        this.cobrancaForma = cobrancaForma;
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

    public ParcelamentoGrupo getParcelamentoGrupo() {
        return this.parcelamentoGrupo;
    }

    public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo) {
        this.parcelamentoGrupo = parcelamentoGrupo;
    }

    public DocumentoTipo getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public Parcelamento getParcelamento() {
        return this.parcelamento;
    }

    public void setParcelamento(Parcelamento parcelamento) {
        this.parcelamento = parcelamento;
    }

    public gsan.faturamento.debito.DebitoTipo getDebitoTipo() {
        return this.debitoTipo;
    }

    public void setDebitoTipo(gsan.faturamento.debito.DebitoTipo debitoTipo) {
        this.debitoTipo = debitoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public BigDecimal getPercentualTaxaJurosFinanciamento() {
		return percentualTaxaJurosFinanciamento;
	}

	public void setPercentualTaxaJurosFinanciamento(
			BigDecimal percentualTaxaJurosFinanciamento) {
		this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
	}

	 public BigDecimal getValorTotal(){
	 		
	 		BigDecimal retornoDivisao = new BigDecimal("0.00");
	 		BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
	 		BigDecimal retorno = new BigDecimal("0.00");

//	 		retornoDivisao = Util.dividirArredondando(this.valorDebito,new BigDecimal(numeroPrestacaoDebito));
	 		if (valorDebito != null ){
	 			retornoDivisao = this.valorDebito.divide(new BigDecimal(prestacaoDebito),2 , BigDecimal.ROUND_DOWN);	
	 		}

	 		retornoDivisao = retornoDivisao.setScale(2, BigDecimal.ROUND_DOWN);
	 		
	 		retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(prestacaoCobradas));
	 		
	 		retornoMultiplicacao = retornoMultiplicacao.setScale(2, BigDecimal.ROUND_DOWN);
	 		
	 		retorno = this.valorDebito.subtract(retornoMultiplicacao);
	 		
	 		retorno = retorno.setScale(2, BigDecimal.ROUND_DOWN);
	 			
	 		return retorno;
	 	}

	public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }

    public gsan.faturamento.debito.DebitoACobrarGeral getOrigem() {
		return origem;
	}

	public void setOrigem(gsan.faturamento.debito.DebitoACobrarGeral origem) {
		this.origem = origem;
	}

    /**
     * Realiza o calculo de quantas parcelas falta para cobrar  
     * numero total de presta��es menos
     * numero de parcelas cobradas menos
     * numero de parcelas bonus
     * 
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public short getParcelasRestanteComBonus(){
        
       short retorno = Short.parseShort(""+
               (getPrestacaoDebito() -  getPrestacaoCobradas()));
       
       if (getNumeroParcelaBonus() != null){
           retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
       }
            
        return retorno;
    }

    /**
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public BigDecimal getValorTotalComBonus(){
        
    	//caso o n�mero de parcelas j� cobradas  seja igual 
    	//ao n�mero total de parcelas menos o n�mero de parcelas bonus 
    	//(DBAC_NNPRESTACAOCOBRADAS = DBAC_NNPRESTACAODEBITO -coalesce(DBAC_NNPARCELABONUS,0)) 
    	//atribuir o valor zero ao valor restante a ser cobrado, 
    	//caso contr�rio 
    	//(DBAC_VLDEBITO - ((DBAC_VLDEBITO/ DBAC_NNPRESTACAODEBITO) * 
    	// (DBAC_NNPRESTACAOCOBRADAS + coalesce (DBAC_NNPARCELABONUS,0))))
    	
        BigDecimal retornoDivisao = new BigDecimal("0.00");
        BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
        BigDecimal retorno = new BigDecimal("0.00");

        
        Short bonus = 0; 
        if (numeroParcelaBonus != null){
        	bonus = numeroParcelaBonus;
        }
        
        if (prestacaoCobradas == prestacaoDebito - bonus){
        	retorno = new BigDecimal("0.00");
        	
        }else{
        	if (valorDebito != null ){
                retornoDivisao = this.valorDebito.divide(new BigDecimal(prestacaoDebito),2 , BigDecimal.ROUND_DOWN);  
            }

            retornoDivisao = retornoDivisao.setScale(2, BigDecimal.ROUND_DOWN);
            
//            if (numeroParcelaBonus != null){
//                retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(prestacaoCobradas + numeroParcelaBonus));
//            }else{
//                retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(prestacaoCobradas));
//            }
        	retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(prestacaoCobradas + bonus));           
            retornoMultiplicacao = retornoMultiplicacao.setScale(2, BigDecimal.ROUND_DOWN);
            
            retorno = this.valorDebito.subtract(retornoMultiplicacao);
            
            retorno = retorno.setScale(2, BigDecimal.ROUND_DOWN);
        }
        
        
        
        
            
        return retorno;
    }
    
 	public String getFormatarAnoMesCobrancaDebito() {
 		
 		String anoMesFormatado = "";
		
		if (this.getAnoMesCobrancaDebito() != null 
				&& !this.getAnoMesCobrancaDebito().toString().trim().equals("")) {
			String anoMesRecebido = "" + this.getAnoMesCobrancaDebito();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}

		return anoMesFormatado.toString();
	}
 	
	public String getFormatarAnoMesReferenciaDebito() {

		String anoMesFormatado = "";

		if (this.getAnoMesReferenciaDebito() != null 
				&& !this.getAnoMesReferenciaDebito().toString().trim().equals("")) {
			String anoMesRecebido = "" + this.getAnoMesReferenciaDebito();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}

		return anoMesFormatado.toString();
	}
	
	public String getFormatarAnoMesReferenciaContabil() {

		String anoMesFormatado = "";
		
		if (this.getAnoMesReferenciaContabil() != null 
				&& !this.getAnoMesReferenciaContabil().toString().trim().equals("")) {
			String anoMesRecebido = "" + this.getAnoMesReferenciaContabil();
			String mes = anoMesRecebido.substring(4, 6);
			String ano = anoMesRecebido.substring(0, 4);
			anoMesFormatado = mes + "/" + ano;
		}

		return anoMesFormatado.toString();
	}

    /**
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public short getNumeroPrestacaoDebitoMenosBonus() {
        short retorno = getPrestacaoDebito();
        
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
	
	/**
     * @author Vivianne Sousa
     * @created 13/11/2008
    */
    public BigDecimal getValorPrestacao(){
        
        //truncando o resultado com 2 casas decimais
        BigDecimal retornoDivisao = 
            this.valorDebito.divide(new BigDecimal(prestacaoDebito),2,BigDecimal.ROUND_DOWN);
       
        return retornoDivisao;
    }
}
