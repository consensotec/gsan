package gsan.cadastro.imovel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import gsan.cadastro.cliente.RamoAtividade;
import gsan.interceptor.ObjetoGcom;

public class ImovelRamoAtividadePK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gsan.cadastro.imovel.Imovel imovel;

    /** identifier field */
    private RamoAtividade ramo_atividade;

    /** full constructor */
    public ImovelRamoAtividadePK(gsan.cadastro.imovel.Imovel imovel,
            RamoAtividade ramo_atividade) {
        this.imovel = imovel;
        this.ramo_atividade = ramo_atividade;
    }

    /** default constructor */
    public ImovelRamoAtividadePK() {
    }

    public gsan.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gsan.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    } 
    
    

    public RamoAtividade getRamo_atividade() {
		return ramo_atividade;
	}

	public void setRamo_atividade(RamoAtividade ramo_atividade) {
		this.ramo_atividade = ramo_atividade;
	}

	public String toString() {
        return new ToStringBuilder(this).append("imovel", getImovel()).append(
                "ramo_atividade", this.getRamo_atividade()).toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if (!(other instanceof ImovelSubcategoriaPK))
            return false;
        ImovelRamoAtividadePK castOther = (ImovelRamoAtividadePK) other;
        return new EqualsBuilder().append(this.getImovel(),
                castOther.getImovel()).append(this.getRamo_atividade(),
                castOther.getRamo_atividade()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getImovel()).append(
                this.getRamo_atividade()).toHashCode();
    }

	@Override
	public String[] retornaCamposChavePrimaria() {
 		String[] retorno = new String[2];
 		retorno[0] = "ramo_atividade";
 		retorno[1] = "imovel";
 		return retorno;		
	}
	
}
