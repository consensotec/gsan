package gsan.cadastro.imovel;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VwImovelPrincipalCategoriaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gsan.cadastro.imovel.Imovel imovel;

    /** identifier field */
    private gsan.cadastro.imovel.Categoria categoria;

    /** full constructor */
    public VwImovelPrincipalCategoriaPK(gsan.cadastro.imovel.Imovel imovel, gsan.cadastro.imovel.Categoria categoria) {
        this.imovel = imovel;
        this.categoria = categoria;
    }

    /** default constructor */
    public VwImovelPrincipalCategoriaPK() {
    }

    public gsan.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gsan.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    }

    public gsan.cadastro.imovel.Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(gsan.cadastro.imovel.Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("imovel", getImovel())
            .append("categoria", getCategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof VwImovelPrincipalCategoriaPK) ) return false;
        VwImovelPrincipalCategoriaPK castOther = (VwImovelPrincipalCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getImovel(), castOther.getImovel())
            .append(this.getCategoria(), castOther.getCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getImovel())
            .append(getCategoria())
            .toHashCode();
    }

}
