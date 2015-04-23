package gsan.cadastro;

import gsan.seguranca.Atributo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class EmpresaContratoCadastroAtributo implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	/** identifier field */
    private Integer id;

    /** persistent field */
    private BigDecimal valorAtualizacaoAtributo;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Atributo atributo;

    /** persistent field */
    private gsan.cadastro.EmpresaContratoCadastro empresaContratoCadastro;

    /** full constructor */
    public EmpresaContratoCadastroAtributo(Integer id, BigDecimal valorAtualizacaoAtributo, Date ultimaAlteracao, Atributo atributo, gsan.cadastro.EmpresaContratoCadastro empresaContratoCadastro) {
        this.id = id;
        this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.atributo = atributo;
        this.empresaContratoCadastro = empresaContratoCadastro;
    }

    /** default constructor */
    public EmpresaContratoCadastroAtributo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorAtualizacaoAtributo() {
        return this.valorAtualizacaoAtributo;
    }

    public void setValorAtualizacaoAtributo(BigDecimal valorAtualizacaoAtributo) {
        this.valorAtualizacaoAtributo = valorAtualizacaoAtributo;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Atributo getAtributo() {
        return this.atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public gsan.cadastro.EmpresaContratoCadastro getEmpresaContratoCadastro() {
        return this.empresaContratoCadastro;
    }

    public void setEmpresaContratoCadastro(gsan.cadastro.EmpresaContratoCadastro empresaContratoCadastro) {
        this.empresaContratoCadastro = empresaContratoCadastro;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
