package gsan.cobranca;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;

import java.io.Serializable;
import java.util.Collection;

public class ComandoEmpresaCobrancaContaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;
	
	private Collection<UnidadeNegocio> colecaoUnidadeNegocio;
	
	private Collection<GerenciaRegional> colecaoGerenciaRegional;
	
	private Collection<ImovelPerfil> colecaoImovelPerfil;

	private Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao;
	
	private Collection<SetorComercial> colecaoSetoresComponent;
	
	private Boolean indicadorTotalDebito;

	public ComandoEmpresaCobrancaContaHelper() {
		super();
	}

	public ComandoEmpresaCobrancaContaHelper(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Collection<UnidadeNegocio> colecaoUnidadeNegocio, Collection<GerenciaRegional> colecaoGerenciaRegional, Collection<ImovelPerfil> colecaoImovelPerfil) {
		super();
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<GerenciaRegional> getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(
			Collection<GerenciaRegional> colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	public Collection<ImovelPerfil> getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}

	public void setColecaoImovelPerfil(Collection<ImovelPerfil> colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection<UnidadeNegocio> getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	public void setColecaoUnidadeNegocio(
			Collection<UnidadeNegocio> colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public Collection<LigacaoAguaSituacao> getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}

	public void setColecaoLigacaoAguaSituacao(
			Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}

	public Boolean getIndicadorTotalDebito() {
		return indicadorTotalDebito;
	}

	public void setIndicadorTotalDebito(Boolean indicadorTotalDebito) {
		this.indicadorTotalDebito = indicadorTotalDebito;
	}

	public Collection<SetorComercial> getColecaoSetoresComponent() {
		return colecaoSetoresComponent;
	}

	public void setColecaoSetoresComponent(Collection<SetorComercial> colecaoSetoresComponent) {
		this.colecaoSetoresComponent = colecaoSetoresComponent;
	}
	
	
	
}
