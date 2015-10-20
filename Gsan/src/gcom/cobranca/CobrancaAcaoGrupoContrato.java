package gcom.cobranca;

import gcom.micromedicao.ContratoEmpresaServico;

import java.io.Serializable;
import java.util.Date;

public class CobrancaAcaoGrupoContrato implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private CobrancaAcao cobrancaAcao;
	private CobrancaGrupo cobrancaGrupo;
	private ContratoEmpresaServico contratoEmpresaServico;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}
	
	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}
	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}
	
	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}
	public void setContratoEmpresaServico(ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}