package gcom.cobranca;

import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe de entidade da tabela
 * cobranca.cmd_empr_cobr_conta_stcm
 * 
 * @author Raimundo Martins
 * */
public class ComandoEmpresaCobrancaContaSetorComercial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ComandoEmpresaCobrancaContaSetorComercialPK pk;
	
	private ComandoEmpresaCobrancaConta comando;
	
	private SetorComercial setorComercial;
	
	private Date dataUltimaAlteracao;

	public ComandoEmpresaCobrancaConta getComando() {
		return comando;
	}

	public void setComando(ComandoEmpresaCobrancaConta comando) {
		this.comando = comando;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public ComandoEmpresaCobrancaContaSetorComercialPK getPk() {
		return pk;
	}

	public void setPk(ComandoEmpresaCobrancaContaSetorComercialPK pk) {
		this.pk = pk;
	}
	
}
