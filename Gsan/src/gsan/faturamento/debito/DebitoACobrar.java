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

import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cobranca.CobrancaForma;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.ParcelamentoGrupo;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.financeiro.FinanciamentoTipo;
import gsan.financeiro.lancamento.LancamentoItemContabil;
import gsan.interceptor.ControleAlteracao;
import gsan.interceptor.ObjetoTransacao;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class DebitoACobrar extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_DEBITO_A_COBRAR_INSERIR = 70; //Operacao.OPERACAO_DEBITO_A_COBRAR_INSERIR;
	public static final int ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR = 71; //Operacao.OPERACAO_DEBITO_A_COBRAR_INSERIR;
	public static final int ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO = 1522; //OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date geracaoDebito;

    /** nullable persistent field */
    private Integer anoMesReferenciaDebito;

    /** nullable persistent field */
    private Integer anoMesCobrancaDebito;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private BigDecimal valorDebito;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,
    		ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR, LigacaoAgua.ATRIBUTOS_EFETUAR_LIGACAO})
    private short numeroPrestacaoDebito;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private short numeroPrestacaoCobradas;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private Short numeroLote;

    /** nullable persistent field */
    private Short numeroSubLote;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer anoMesReferenciaContabil;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private BigDecimal percentualTaxaJurosFinanciamento;
    
    private Short numeroParcelaBonus;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private DocumentoTipo documentoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.PARCELAMENTO,funcionalidade={ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO})
    private Parcelamento parcelamento;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.FINANCIAMENTO_TIPO,
    		funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private FinanciamentoTipo financiamentoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.ORDEM_SERVICO,
    		funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})    
    private OrdemServico ordemServico;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.DEBITO_TIPO,
    		funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private gsan.faturamento.debito.DebitoTipo debitoTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.REGISTRO_ATENDIMENTO,
    		funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})        
    private RegistroAtendimento registroAtendimento;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.LANCAMENTO_ITEM_CONTABIL,
    		funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private LancamentoItemContabil lancamentoItemContabil;

    /** persistent field */
    private gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.DEBITO_CREDITO_SITUACAO,
    		funcionalidade={ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO,ATRIBUTOS_DEBITO_A_COBRAR_CANCELAR})
    private gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual;

    /** persistent field */
    private ParcelamentoGrupo parcelamentoGrupo;

    /** persistent field */
    @ControleAlteracao(value=FiltroDebitoACobrar.COBRANCA_FORMA,
    		funcionalidade={ATRIBUTOS_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO})
    private CobrancaForma cobrancaForma;
    
    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private Set debitoACobrarCategorias;
    
    /** este campo foi criado s� para o [UC 0155], e n�o est� mapeado */
    private BigDecimal valorDebitoPorCategoria;
    
    private DebitoACobrarGeral debitoACobrarGeralOrigem;
    
    private DebitoACobrarGeral debitoACobrarGeral;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR})
    private BigDecimal valorEntrada;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_DEBITO_A_COBRAR_INSERIR})
    private BigDecimal valorTotalDebito;
    
    private Integer anoMesReferenciaPrestacao;
    
    private Integer numeroParcelasAntecipadas;
    
    private Date dataRevisao;
    
    private ContaMotivoRevisao contaMotivoRevisao;

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
		
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("parcelamento");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("parcelamentoGrupo");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
		filtroDebitoACobrar. adicionarCaminhoParaCarregamentoEntidade("usuario");
		
		filtroDebitoACobrar. adicionarParametro(
				new ParametroSimples(FiltroDebitoACobrar .ID, this.getId()));
		return filtroDebitoACobrar; 
	}
    
    /** full constructor */
    public DebitoACobrar(Date geracaoDebito, Integer anoMesReferenciaDebito, Integer anoMesCobrancaDebito, BigDecimal valorDebito, short numeroPrestacaoDebito, short numeroPrestacaoCobradas, Integer codigoSetorComercial, Integer numeroQuadra, Short numeroLote, Short numeroSubLote, Date ultimaAlteracao, Integer anoMesReferenciaContabil, BigDecimal percentualTaxaJurosFinanciamento, Imovel imovel, DocumentoTipo documentoTipo, Parcelamento parcelamento, FinanciamentoTipo financiamentoTipo, OrdemServico ordemServico, Quadra quadra, Localidade localidade, gsan.faturamento.debito.DebitoTipo debitoTipo, RegistroAtendimento registroAtendimento, LancamentoItemContabil lancamentoItemContabil, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual, ParcelamentoGrupo parcelamentoGrupo, CobrancaForma cobrancaForma, Usuario usuario, Set debitoACobrarCategorias) {
        this.geracaoDebito = geracaoDebito;
        this.anoMesReferenciaDebito = anoMesReferenciaDebito;
        this.anoMesCobrancaDebito = anoMesCobrancaDebito;
        this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroLote = numeroLote;
        this.numeroSubLote = numeroSubLote;
        this.ultimaAlteracao = ultimaAlteracao;
        this.anoMesReferenciaContabil = anoMesReferenciaContabil;
        this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.parcelamento = parcelamento;
        this.financiamentoTipo = financiamentoTipo;
        this.ordemServico = ordemServico;
        this.quadra = quadra;
        this.localidade = localidade;
        this.debitoTipo = debitoTipo;
        this.registroAtendimento = registroAtendimento;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.parcelamentoGrupo = parcelamentoGrupo;
        this.cobrancaForma = cobrancaForma;
        this.usuario = usuario;
        this.debitoACobrarCategorias = debitoACobrarCategorias;
    }

    /** default constructor */
    public DebitoACobrar() {
    }

    /** minimal constructor */
    public DebitoACobrar(Date geracaoDebito, BigDecimal valorDebito, short numeroPrestacaoDebito, short numeroPrestacaoCobradas, Imovel imovel, DocumentoTipo documentoTipo, Parcelamento parcelamento, FinanciamentoTipo financiamentoTipo, OrdemServico ordemServico, Quadra quadra, Localidade localidade, gsan.faturamento.debito.DebitoTipo debitoTipo, RegistroAtendimento registroAtendimento, LancamentoItemContabil lancamentoItemContabil, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior, gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual, ParcelamentoGrupo parcelamentoGrupo, CobrancaForma cobrancaForma, Set debitoACobrarCategorias) {
        this.geracaoDebito = geracaoDebito;
        this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.imovel = imovel;
        this.documentoTipo = documentoTipo;
        this.parcelamento = parcelamento;
        this.financiamentoTipo = financiamentoTipo;
        this.ordemServico = ordemServico;
        this.quadra = quadra;
        this.localidade = localidade;
        this.debitoTipo = debitoTipo;
        this.registroAtendimento = registroAtendimento;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
        this.parcelamentoGrupo = parcelamentoGrupo;
        this.cobrancaForma = cobrancaForma;
        this.debitoACobrarCategorias = debitoACobrarCategorias;
    }

    public DebitoACobrar(BigDecimal valorDebito, short numeroPrestacaoDebito, short numeroPrestacaoCobradas, LancamentoItemContabil lancamentoItemContabil) {
        this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }
    
    public DebitoACobrar(Integer id,BigDecimal valorDebito, short numeroPrestacaoDebito, 
    	short numeroPrestacaoCobradas, Short numeroParcelaBonus) {
    	
    	this.id = id;
    	this.valorDebito = valorDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
        this.numeroParcelaBonus = numeroParcelaBonus;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGeracaoDebito() {
        return this.geracaoDebito;
    }

    public void setGeracaoDebito(Date geracaoDebito) {
        this.geracaoDebito = geracaoDebito;
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

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public short getNumeroPrestacaoDebito() {
        return this.numeroPrestacaoDebito;
    }

    public void setNumeroPrestacaoDebito(short numeroPrestacaoDebito) {
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
    }

    public short getNumeroPrestacaoCobradas() {
        return this.numeroPrestacaoCobradas;
    }

    public void setNumeroPrestacaoCobradas(short numeroPrestacaoCobradas) {
        this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
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

    public Short getNumeroLote() {
        return this.numeroLote;
    }

    public void setNumeroLote(Short numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Short getNumeroSubLote() {
        return this.numeroSubLote;
    }

    public void setNumeroSubLote(Short numeroSubLote) {
        this.numeroSubLote = numeroSubLote;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getAnoMesReferenciaContabil() {
        return this.anoMesReferenciaContabil;
    }

    public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
        this.anoMesReferenciaContabil = anoMesReferenciaContabil;
    }

    public BigDecimal getPercentualTaxaJurosFinanciamento() {
        return this.percentualTaxaJurosFinanciamento;
    }

    public void setPercentualTaxaJurosFinanciamento(BigDecimal percentualTaxaJurosFinanciamento) {
        this.percentualTaxaJurosFinanciamento = percentualTaxaJurosFinanciamento;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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

    public gsan.faturamento.debito.DebitoTipo getDebitoTipo() {
        return this.debitoTipo;
    }

    public void setDebitoTipo(gsan.faturamento.debito.DebitoTipo debitoTipo) {
        this.debitoTipo = debitoTipo;
    }

    public RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public gsan.faturamento.debito.DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
        return this.debitoCreditoSituacaoAnterior;
    }

    public void setDebitoCreditoSituacaoAnterior(gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
        this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
    }

    public gsan.faturamento.debito.DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
        return this.debitoCreditoSituacaoAtual;
    }

    public void setDebitoCreditoSituacaoAtual(gsan.faturamento.debito.DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
        this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
    }

    public ParcelamentoGrupo getParcelamentoGrupo() {
        return this.parcelamentoGrupo;
    }

    public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo) {
        this.parcelamentoGrupo = parcelamentoGrupo;
    }

    public CobrancaForma getCobrancaForma() {
        return this.cobrancaForma;
    }

    public void setCobrancaForma(CobrancaForma cobrancaForma) {
        this.cobrancaForma = cobrancaForma;
    }

    public Set getDebitoACobrarCategorias() {
        return this.debitoACobrarCategorias;
    }

    public void setDebitoACobrarCategorias(Set debitoACobrarCategorias) {
        this.debitoACobrarCategorias = debitoACobrarCategorias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    /**
     * @author Vivianne Sousa
    */
    public BigDecimal getValorTotal(){
    	 		
 		BigDecimal retornoDivisao = new BigDecimal("0.00");
 		BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
 		BigDecimal retorno = new BigDecimal("0.00");

 		if (valorDebito != null ){
 			retornoDivisao = getValorPrestacao(); 
 		}
 		
 		retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoCobradas));
 		retorno = this.valorDebito.subtract(retornoMultiplicacao);
 			
 		return retorno;
 	}
    
 	public BigDecimal getValorDebitoPorCategoria() {
 		return valorDebitoPorCategoria;
 	}
 
 	public void setValorDebitoPorCategoria(BigDecimal valorDebitoPorCategoria) {
 		this.valorDebitoPorCategoria = valorDebitoPorCategoria;
 	}
 	
// 	/**
// 	 * Realiza o calculo do valor restante a ser pago do d�bito 
// 	 * 
// 	 * @author Pedro Alexandre
// 	 * @created 7 de Mar�o de 2006
// 	 */
// 	public BigDecimal getValorRestanteCobrado(){
// 		
// 		BigDecimal retorno = new BigDecimal("0.00");
// 		
// 		BigDecimal valorDebito = getValorDebito();
// 		BigDecimal numeroPrestacaoDebito = new BigDecimal(getNumeroPrestacaoDebito());
// 		BigDecimal numeroPrestacaoCobradas = new BigDecimal(getNumeroPrestacaoCobradas());
// 		numeroPrestacaoDebito = numeroPrestacaoDebito.setScale(2);
// 		
// 		retorno =  Util.dividirArredondando(valorDebito,numeroPrestacaoDebito);
// 		retorno = retorno.multiply(numeroPrestacaoCobradas);
// 		retorno = valorDebito.subtract(retorno);
// 		
//// 		retorno = valorDebito.subtract(((valorDebito.divide(numeroPrestacaoDebito)).multiply(numeroPrestacaoCobradas)));
// 		 		
// 		return retorno;
// 	}
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
 	 * Realiza o calculo de quantas parcelas falta para cobrar  
 	 * 
 	 * @author Fernanda Paiva
 	 * @created 7 de Abril de 2006
 	*/
 	public short getParcelasRestante(){
 		
 	   short retorno = Short.parseShort(""+
               (getNumeroPrestacaoDebito() -  getNumeroPrestacaoCobradas()));
       
 		return retorno;
 	}

	/**
	 * @return Retorna o campo debitoACobrarGeral.
	 */
	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	/**
	 * @param debitoACobrarGeral O debitoACobrarGeral a ser setado.
	 */
	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

	public DebitoACobrarGeral getDebitoACobrarGeralOrigem() {
		return debitoACobrarGeralOrigem;
	}

	public void setDebitoACobrarGeralOrigem(
			DebitoACobrarGeral debitoACobrarGeralOrigem) {
		this.debitoACobrarGeralOrigem = debitoACobrarGeralOrigem;
	}

	@Override
	public void initializeLazy() {
		if (debitoTipo != null){
			getDebitoTipo().initializeLazy();
		}
		if (lancamentoItemContabil != null){
			getLancamentoItemContabil().initializeLazy();
		}
		if (financiamentoTipo != null){
			getFinanciamentoTipo().initializeLazy();
		}
		if (registroAtendimento != null) {
			getRegistroAtendimento().initializeLazy();
		}
		if(ordemServico != null){
			getOrdemServico().initializeLazy();
		}
	}

	public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }

	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"debitoTipo.descricao"
				, "valorDebito"
				};
			return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Tipo Debito"
				, "Valor"
				};
			return labels;		
	}

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	public Integer getAnoMesReferenciaPrestacao() {
		return anoMesReferenciaPrestacao;
	}

	public void setAnoMesReferenciaPrestacao(Integer anoMesReferenciaPrestacao) {
		this.anoMesReferenciaPrestacao = anoMesReferenciaPrestacao;
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
               (getNumeroPrestacaoDebito() -  getNumeroPrestacaoCobradas()));
       
       if (getNumeroParcelaBonus() != null){
           retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
       }
            
        return retorno;
    }

	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
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
        
        if (numeroPrestacaoCobradas == numeroPrestacaoDebito - bonus){
        	retorno = new BigDecimal("0.00");
        	
        }else{
        	if (valorDebito != null ){
                retornoDivisao = getValorPrestacao();
            }
        	retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoCobradas + bonus));
            retorno = this.valorDebito.subtract(retornoMultiplicacao);
        }

		if(retorno != null){
			return retorno.setScale(2);
		}else{
			return retorno;	
		}	        
    }

    /**
     * @author Vivianne Sousa
     * @created 21/02/2008
    */
    public short getNumeroPrestacaoDebitoMenosBonus() {
        short retorno =getNumeroPrestacaoDebito();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }
    
    /**
     * @author Vivianne Sousa
     * @created 15/04/2008
    */
    public BigDecimal getValorPrestacao(){
        
        //truncando o resultado com 2 casas decimais
        BigDecimal retornoDivisao = 
            this.valorDebito.divide(new BigDecimal(numeroPrestacaoDebito),2,BigDecimal.ROUND_DOWN);
       
        return retornoDivisao;
    }
    
    /**
     * @author Vivianne Sousa
     * @created 17/04/2008
    */
    public short getNumeroPrestacaoCobradasMaisBonus() {
        short retorno =getNumeroPrestacaoCobradas();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno + getNumeroParcelaBonus().shortValue()));
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
    
    public Date getDataRevisao() {
		return dataRevisao;
	}
    
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}
	
	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}

	/**
     * @author R�mulo Aur�lio
     * @created 28/08/2008
    */
    public BigDecimal getValorParcela(){
    	
    	return getValorPrestacao();
    	
//    	short retorno =getNumeroPrestacaoDebito();
//    	
//    	if (getNumeroParcelaBonus() != null){
//            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
//        }
//        //truncando o resultado com 2 casas decimais
//        BigDecimal retornoDivisao = 
//            this.valorDebito.divide(new BigDecimal(retorno),2,BigDecimal.ROUND_DOWN);
//       
//        return retornoDivisao;
    }
    
    public short getNumeroPrestacaoRestante() {
        
		short retorno = this.getNumeroPrestacaoDebitoMenosBonus();
        
        retorno = Short.parseShort(""+ (retorno - this.getNumeroPrestacaoCobradas()));
             
        return retorno;
    }
    
    public boolean isAntecipacaoParcela() {
		
    	if (this.getNumeroPrestacaoRestante() > 1){
			return true;
		}
		else{
			return false;
		}
	}
    
    public BigDecimal getValorAntecipacaoParcela(int quantidadePrestacoes){
    	
    	return this.getValorPrestacao().multiply(BigDecimal.valueOf(quantidadePrestacoes));
    }

	public Integer getNumeroParcelasAntecipadas() {
		return numeroParcelasAntecipadas;
	}

	public void setNumeroParcelasAntecipadas(Integer numeroParcelasAntecipadas) {
		this.numeroParcelasAntecipadas = numeroParcelasAntecipadas;
	}
	
	
	public boolean equals(Object other) {
		
		boolean retorno = false;
		
		if (other instanceof DebitoACobrar) {
			
			DebitoACobrar castOther = (DebitoACobrar) other;
			
			retorno = this.getId().compareTo(castOther.getId())==0;
		}
		

		return retorno;
	}
}