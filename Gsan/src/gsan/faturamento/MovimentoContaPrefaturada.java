package gsan.faturamento;

import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.CobrancaDocumento;
import gsan.faturamento.conta.Conta;
import gsan.micromedicao.Rota;
import gsan.micromedicao.consumo.ConsumoAnormalidade;
import gsan.micromedicao.consumo.ConsumoTipo;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MovimentoContaPrefaturada implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Integer anoMesReferenciaPreFaturamento;

    /** nullable persistent field */
    private Integer leituraHidrometro;

    /** persistent field */
    private Date dataHoraLeitura;

    /** persistent field */
    private Short indicadorSituacaoLeitura;

    /** nullable persistent field */
    private Integer leituraFaturamento;

    /** nullable persistent field */
    private Integer consumoMedido;

    /** nullable persistent field */
    private Integer consumoCobrado;

    /** persistent field */
    private Date dataHoraGeracaoMovimento;

    /** persistent field */
    private Short indicadorAtualizacaoFaturamento;

    /** persistent field */
    private Date utlimaAlteracao;

    /** persistent field */
    private Short indicadorEmissaoConta;
    
    /** persistent field */
    private Short indicadorGeracaoConta;

    /** nullable persistent field */
    private Integer consumoRateioAgua;

    /** nullable persistent field */
    private Integer consumoRateioEsgoto;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private MedicaoTipo medicaoTipo;

    /** persistent field */
    private gsan.faturamento.FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private Rota rota;

    /** persistent field */
    private LeituraAnormalidade leituraAnormalidadeFaturamento;

    /** persistent field */
    private ConsumoTipo consumoTipo;

    /** persistent field */
    private ConsumoAnormalidade consumoAnormalidade;

    /** persistent field */
    private Conta conta;

    /** persistent field */
    private Set movimentoContaPrefaturadaCategorias;

    /** persistent field */
    private Set movimentoContaImpostoDeduzidos;
    
    private MovimentoContaPrefaturada movimentoContaPreFaturadaEsgoto;
    
    /** persistent field */
    private LeituraAnormalidade leituraAnormalidadeLeitura;
    
    /** persistent field */
    private Integer consumoImoveisVinculados;
    
    /** persistent field */
    private CobrancaDocumento cobrancaDocumento;
    
    /** nullable persistent field */
    private Integer leituraHidrometroAnterior;
    
    /** persistent field */
    private Short indicadorAlteracao;
    
    /* RM 5635 - Registrar a quantidade de vezes que a conta foi impressa atrav�s da ISC
   	Amelia Pessoa */
       /** persistent field */
   	private Integer qntVezesImpressa;
   	
   	/** persistent field */
   	private BigDecimal valorAgua;
   	
   	/** persistent field */
   	private BigDecimal valorEsgoto;
   	
   	/** persistent field */
   	private BigDecimal valorDebitos;
   	
   	/** persistent field */
   	private BigDecimal valorCreditos;
   	
   	/** persistent field */
   	private BigDecimal valorImpostos;
   	
   	/** persistent field */
   	private BigDecimal numeroCoordenadaX;
   	
   	/** persistent field */
   	private BigDecimal numeroCoordenadaY;

    public MovimentoContaPrefaturada getMovimentoContaPreFaturadaEsgoto() {
		return movimentoContaPreFaturadaEsgoto;
	}

	public void setMovimentoContaPreFaturadaEsgoto(
			MovimentoContaPrefaturada movimentoContaPreFaturadaEsgoto) {
		this.movimentoContaPreFaturadaEsgoto = movimentoContaPreFaturadaEsgoto;
	}

	/** full constructor */
    public MovimentoContaPrefaturada(Integer id, Integer anoMesReferenciaPreFaturamento, Integer leituraHidrometro, Date dataHoraLeitura, Short indicadorSituacaoLeitura, Integer leituraFaturamento, Integer consumoMedido, Integer consumoCobrado, Date dataHoraGeracaoMovimento, Short indicadorAtualizacaoFaturamento, Date utlimaAlteracao, Short indicadorEmissaoConta, Integer consumoRateioAgua, Integer consumoRateioEsgoto, Imovel imovel, MedicaoTipo medicaoTipo, gsan.faturamento.FaturamentoGrupo faturamentoGrupo, Rota rota, LeituraAnormalidade leituraAnormalidadeFaturamento, ConsumoTipo consumoTipo, ConsumoAnormalidade consumoAnormalidade, Conta conta, Set movimentoContaPrefaturadaCategorias, Set movimentoContaImpostoDeduzidos) {
        this.id = id;
        this.anoMesReferenciaPreFaturamento = anoMesReferenciaPreFaturamento;
        this.leituraHidrometro = leituraHidrometro;
        this.dataHoraLeitura = dataHoraLeitura;
        this.indicadorSituacaoLeitura = indicadorSituacaoLeitura;
        this.leituraFaturamento = leituraFaturamento;
        this.consumoMedido = consumoMedido;
        this.consumoCobrado = consumoCobrado;
        this.dataHoraGeracaoMovimento = dataHoraGeracaoMovimento;
        this.indicadorAtualizacaoFaturamento = indicadorAtualizacaoFaturamento;
        this.utlimaAlteracao = utlimaAlteracao;
        this.indicadorEmissaoConta = indicadorEmissaoConta;
        this.consumoRateioAgua = consumoRateioAgua;
        this.consumoRateioEsgoto = consumoRateioEsgoto;
        this.imovel = imovel;
        this.medicaoTipo = medicaoTipo;
        this.faturamentoGrupo = faturamentoGrupo;
        this.rota = rota;
        this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
        this.consumoTipo = consumoTipo;
        this.consumoAnormalidade = consumoAnormalidade;
        this.conta = conta;
        this.movimentoContaPrefaturadaCategorias = movimentoContaPrefaturadaCategorias;
        this.movimentoContaImpostoDeduzidos = movimentoContaImpostoDeduzidos;
    }

    /** default constructor */
    public MovimentoContaPrefaturada() {
    }

    /** minimal constructor */
    public MovimentoContaPrefaturada(Integer id, Integer anoMesReferenciaPreFaturamento, Date dataHoraLeitura, Short indicadorSituacaoLeitura, Date dataHoraGeracaoMovimento, Short indicadorAtualizacaoFaturamento, Date utlimaAlteracao, Short indicadorEmissaoConta, Imovel imovel, MedicaoTipo medicaoTipo, gsan.faturamento.FaturamentoGrupo faturamentoGrupo, Rota rota, LeituraAnormalidade leituraAnormalidadeFaturamento, ConsumoTipo consumoTipo, ConsumoAnormalidade consumoAnormalidade, Conta conta, Set movimentoContaPrefaturadaCategorias, Set movimentoContaImpostoDeduzidos) {
        this.id = id;
        this.anoMesReferenciaPreFaturamento = anoMesReferenciaPreFaturamento;
        this.dataHoraLeitura = dataHoraLeitura;
        this.indicadorSituacaoLeitura = indicadorSituacaoLeitura;
        this.dataHoraGeracaoMovimento = dataHoraGeracaoMovimento;
        this.indicadorAtualizacaoFaturamento = indicadorAtualizacaoFaturamento;
        this.utlimaAlteracao = utlimaAlteracao;
        this.indicadorEmissaoConta = indicadorEmissaoConta;
        this.imovel = imovel;
        this.medicaoTipo = medicaoTipo;
        this.faturamentoGrupo = faturamentoGrupo;
        this.rota = rota;
        this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
        this.consumoTipo = consumoTipo;
        this.consumoAnormalidade = consumoAnormalidade;
        this.conta = conta;
        this.movimentoContaPrefaturadaCategorias = movimentoContaPrefaturadaCategorias;
        this.movimentoContaImpostoDeduzidos = movimentoContaImpostoDeduzidos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferenciaPreFaturamento() {
        return this.anoMesReferenciaPreFaturamento;
    }

    public void setAnoMesReferenciaPreFaturamento(Integer anoMesReferenciaPreFaturamento) {
        this.anoMesReferenciaPreFaturamento = anoMesReferenciaPreFaturamento;
    }

    public Integer getLeituraHidrometro() {
        return this.leituraHidrometro;
    }

    public void setLeituraHidrometro(Integer leituraHidrometro) {
        this.leituraHidrometro = leituraHidrometro;
    }

    public Date getDataHoraLeitura() {
        return this.dataHoraLeitura;
    }

    public void setDataHoraLeitura(Date dataHoraLeitura) {
        this.dataHoraLeitura = dataHoraLeitura;
    }

    public Short getIndicadorSituacaoLeitura() {
        return this.indicadorSituacaoLeitura;
    }

    public void setIndicadorSituacaoLeitura(Short indicadorSituacaoLeitura) {
        this.indicadorSituacaoLeitura = indicadorSituacaoLeitura;
    }

    public Integer getLeituraFaturamento() {
        return this.leituraFaturamento;
    }

    public void setLeituraFaturamento(Integer leituraFaturamento) {
        this.leituraFaturamento = leituraFaturamento;
    }

    public Integer getConsumoMedido() {
        return this.consumoMedido;
    }

    public void setConsumoMedido(Integer consumoMedido) {
        this.consumoMedido = consumoMedido;
    }

    public Integer getConsumoCobrado() {
        return this.consumoCobrado;
    }

    public void setConsumoCobrado(Integer consumoCobrado) {
        this.consumoCobrado = consumoCobrado;
    }

    public Date getDataHoraGeracaoMovimento() {
        return this.dataHoraGeracaoMovimento;
    }

    public void setDataHoraGeracaoMovimento(Date dataHoraGeracaoMovimento) {
        this.dataHoraGeracaoMovimento = dataHoraGeracaoMovimento;
    }

    public Short getIndicadorAtualizacaoFaturamento() {
        return this.indicadorAtualizacaoFaturamento;
    }

    public void setIndicadorAtualizacaoFaturamento(Short indicadorAtualizacaoFaturamento) {
        this.indicadorAtualizacaoFaturamento = indicadorAtualizacaoFaturamento;
    }

    public Date getUtlimaAlteracao() {
        return this.utlimaAlteracao;
    }

    public void setUtlimaAlteracao(Date utlimaAlteracao) {
        this.utlimaAlteracao = utlimaAlteracao;
    }

    public Short getIndicadorEmissaoConta() {
        return this.indicadorEmissaoConta;
    }

    public void setIndicadorEmissaoConta(Short indicadorEmissaoConta) {
        this.indicadorEmissaoConta = indicadorEmissaoConta;
    }

    public Integer getConsumoRateioAgua() {
        return this.consumoRateioAgua;
    }

    public void setConsumoRateioAgua(Integer consumoRateioAgua) {
        this.consumoRateioAgua = consumoRateioAgua;
    }

    public Integer getConsumoRateioEsgoto() {
        return this.consumoRateioEsgoto;
    }

    public void setConsumoRateioEsgoto(Integer consumoRateioEsgoto) {
        this.consumoRateioEsgoto = consumoRateioEsgoto;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public MedicaoTipo getMedicaoTipo() {
        return this.medicaoTipo;
    }

    public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
        this.medicaoTipo = medicaoTipo;
    }

    public gsan.faturamento.FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(gsan.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public Rota getRota() {
        return this.rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public LeituraAnormalidade getLeituraAnormalidadeFaturamento() {
        return this.leituraAnormalidadeFaturamento;
    }

    public void setLeituraAnormalidadeFaturamento(LeituraAnormalidade leituraAnormalidadeFaturamento) {
        this.leituraAnormalidadeFaturamento = leituraAnormalidadeFaturamento;
    }

    public ConsumoTipo getConsumoTipo() {
        return this.consumoTipo;
    }

    public void setConsumoTipo(ConsumoTipo consumoTipo) {
        this.consumoTipo = consumoTipo;
    }

    public ConsumoAnormalidade getConsumoAnormalidade() {
        return this.consumoAnormalidade;
    }

    public void setConsumoAnormalidade(ConsumoAnormalidade consumoAnormalidade) {
        this.consumoAnormalidade = consumoAnormalidade;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Set getMovimentoContaPrefaturadaCategorias() {
        return this.movimentoContaPrefaturadaCategorias;
    }

    public void setMovimentoContaPrefaturadaCategorias(Set movimentoContaPrefaturadaCategorias) {
        this.movimentoContaPrefaturadaCategorias = movimentoContaPrefaturadaCategorias;
    }

    public Set getMovimentoContaImpostoDeduzidos() {
        return this.movimentoContaImpostoDeduzidos;
    }

    public void setMovimentoContaImpostoDeduzidos(Set movimentoContaImpostoDeduzidos) {
        this.movimentoContaImpostoDeduzidos = movimentoContaImpostoDeduzidos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorGeracaoConta() {
		return indicadorGeracaoConta;
	}

	public void setIndicadorGeracaoConta(Short indicadorGeracaoConta) {
		this.indicadorGeracaoConta = indicadorGeracaoConta;
	}

	public Integer getConsumoImoveisVinculados() {
		return consumoImoveisVinculados;
	}

	public void setConsumoImoveisVinculados(Integer consumoImoveisVinculados) {
		this.consumoImoveisVinculados = consumoImoveisVinculados;
	}

	public LeituraAnormalidade getLeituraAnormalidadeLeitura() {
		return leituraAnormalidadeLeitura;
	}

	public void setLeituraAnormalidadeLeitura(
			LeituraAnormalidade leituraAnormalidadeLeitura) {
		this.leituraAnormalidadeLeitura = leituraAnormalidadeLeitura;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public Integer getLeituraHidrometroAnterior() {
		return leituraHidrometroAnterior;
	}

	public void setLeituraHidrometroAnterior(Integer leituraHidrometroAnterior) {
		this.leituraHidrometroAnterior = leituraHidrometroAnterior;
	}

	public Short getIndicadorAlteracao() {
		return indicadorAlteracao;
	}

	public void setIndicadorAlteracao(Short indicadorAlteracao) {
		this.indicadorAlteracao = indicadorAlteracao;
	}  
	
	public Integer getQntVezesImpressa() {
		return qntVezesImpressa;
	}

	public void setQntVezesImpressa(Integer qntVezesImpressa) {
		this.qntVezesImpressa = qntVezesImpressa;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getValorDebitos() {
		return valorDebitos;
	}

	public void setValorDebitos(BigDecimal valorDebitos) {
		this.valorDebitos = valorDebitos;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public BigDecimal getNumeroCoordenadaX() {
		return numeroCoordenadaX;
	}

	public void setNumeroCoordenadaX(BigDecimal numeroCoordenadaX) {
		this.numeroCoordenadaX = numeroCoordenadaX;
	}

	public BigDecimal getNumeroCoordenadaY() {
		return numeroCoordenadaY;
	}

	public void setNumeroCoordenadaY(BigDecimal numeroCoordenadaY) {
		this.numeroCoordenadaY = numeroCoordenadaY;
	}


	public String getAnoMesReferenciaFormatado(){
		return Util.formatarAnoMesParaMesAno(String.valueOf(this.getAnoMesReferenciaPreFaturamento()));
	}

}
