package gcom.atualizacaocadastral;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.interceptor.ObjetoGcom;

import java.io.Serializable;
import java.util.Date;

public class AreaAtualizacaoCadastralDM extends ObjetoGcom implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Short codigoSituacao;
	private Date dataLiberacao;
	private Date dataSuspensao;
	private Date dataConclusao;
	private Date ultimaAlteracao;
	private Localidade localidade;
	private Empresa empresa;
	private SetorComercial setorComercial;	
	
	public AreaAtualizacaoCadastralDM(){}
	
	public AreaAtualizacaoCadastralDM(Short codigoSituacao, Date dataLiberacao, Date dataSuspensao, 
			Date dataConclusao, Date ultimaAlteracao, Localidade localidade, Empresa empresa, SetorComercial setorComercial){
		
		this.codigoSituacao = codigoSituacao;
		this.dataLiberacao = dataLiberacao;
		this.dataSuspensao = dataSuspensao;
		this.dataConclusao = dataConclusao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.localidade = localidade;
		this.empresa = empresa;
		this.setorComercial = setorComercial;		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	public Date getDataSuspensao() {
		return dataSuspensao;
	}

	public void setDataSuspensao(Date dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		
		String[] retorno = {"id"};
		return retorno;
	}
}