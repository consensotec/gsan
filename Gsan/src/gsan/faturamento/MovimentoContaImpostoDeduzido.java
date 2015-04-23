package gsan.faturamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class MovimentoContaImpostoDeduzido implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoImposto;

    /** persistent field */
    private BigDecimal percentualAliquota;

    /** persistent field */
    private BigDecimal valorImposto;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gsan.faturamento.MovimentoContaPrefaturada movimentoContaPrefaturada;
    
    /** persistent field */
    private gsan.faturamento.ImpostoTipo impostoTipo;    

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer mcirId) {
        this.id = mcirId;
    }

    public String getDescricaoImposto() {
        return this.descricaoImposto;
    }

    public void setDescricaoImposto(String mcirDsimposto) {
        this.descricaoImposto = mcirDsimposto;
    }

    public BigDecimal getPercentualAliquota() {
        return this.percentualAliquota;
    }

    public void setPercentualAliquota(BigDecimal mcirPcaliquota) {
        this.percentualAliquota = mcirPcaliquota;
    }

    public BigDecimal getValorImposto() {
        return this.valorImposto;
    }

    public void setValorImposto(BigDecimal mcirVlimposto) {
        this.valorImposto = mcirVlimposto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date mcirTmultimaalteracao) {
        this.ultimaAlteracao = mcirTmultimaalteracao;
    }

    public gsan.faturamento.MovimentoContaPrefaturada getMovimentoContaPrefaturada() {
        return this.movimentoContaPrefaturada;
    }

    public void setMovimentoContaPrefaturada(gsan.faturamento.MovimentoContaPrefaturada movimentoContaPrefaturada) {
        this.movimentoContaPrefaturada = movimentoContaPrefaturada;
    }

    
    public gsan.faturamento.ImpostoTipo getImpostoTipo() {
        return impostoTipo;
    }

    
    public void setImpostoTipo(gsan.faturamento.ImpostoTipo impostoTipo) {
        this.impostoTipo = impostoTipo;
    }
}
