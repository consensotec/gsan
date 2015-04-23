package gsan.gui.cobranca;

import org.apache.struts.action.ActionForm;

public class InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private Integer idGrupo;
	private Integer idEmpresa;
	private Integer idContrato;
	private Integer idCobrancaAcao;
	private String[] idRegistrosRemocao;
	
	public InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm(){}

	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	public Integer getIdEmpresa(){
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa){
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public Integer getIdCobrancaAcao() {
		return idCobrancaAcao;
	}
	public void setIdCobrancaAcao(Integer idCobrancaAcao) {
		this.idCobrancaAcao = idCobrancaAcao;
	}

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}
	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}
}