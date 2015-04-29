package gsan.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC1202]Form para transferir Im�veis 
 * 	para outro Logradouro
 * 
 * @author Davi Menezes
 * @date 01/08/2011
 */
public class TransferirImovelLogradouroActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	private String logradouroImovelOrigemFiltro;

	private String descricaoLogradouroImovelOrigemFiltro;

	private String logradouroImovelDestinoFiltro;

	private String descricaoLogradouroImovelDestinoFiltro;

	private String bairroImovelFiltro;

	private String cepImovelFiltro;
	
	private String bairroOrigemImovelFiltro;

	private String cepOrigemImovelFiltro;
	
	private String idsRegistro[];
	
	private String tipoTransferencia;

	public String getBairroImovelFiltro() {
		return bairroImovelFiltro;
	}

	public void setBairroImovelFiltro(String bairroImovelFiltro) {
		this.bairroImovelFiltro = bairroImovelFiltro;
	}

	public String getCepImovelFiltro() {
		return cepImovelFiltro;
	}

	public void setCepImovelFiltro(String cepImovelFiltro) {
		this.cepImovelFiltro = cepImovelFiltro;
	}

	public String getDescricaoLogradouroImovelDestinoFiltro() {
		return descricaoLogradouroImovelDestinoFiltro;
	}

	public void setDescricaoLogradouroImovelDestinoFiltro(String descricaoLogradouroImovelDestinoFiltro) {
		this.descricaoLogradouroImovelDestinoFiltro = descricaoLogradouroImovelDestinoFiltro;
	}

	public String getDescricaoLogradouroImovelOrigemFiltro() {
		return descricaoLogradouroImovelOrigemFiltro;
	}

	public void setDescricaoLogradouroImovelOrigemFiltro(String descricaoLogradouroImovelOrigemFiltro) {
		this.descricaoLogradouroImovelOrigemFiltro = descricaoLogradouroImovelOrigemFiltro;
	}

	public String getLogradouroImovelDestinoFiltro() {
		return logradouroImovelDestinoFiltro;
	}

	public void setLogradouroImovelDestinoFiltro(String logradouroImovelDestinoFiltro) {
		this.logradouroImovelDestinoFiltro = logradouroImovelDestinoFiltro;
	}

	public String getLogradouroImovelOrigemFiltro() {
		return logradouroImovelOrigemFiltro;
	}

	public void setLogradouroImovelOrigemFiltro(String logradouroImovelOrigemFiltro) {
		this.logradouroImovelOrigemFiltro = logradouroImovelOrigemFiltro;
	}

	public String getBairroOrigemImovelFiltro() {
		return bairroOrigemImovelFiltro;
	}

	public void setBairroOrigemImovelFiltro(String bairroOrigemImovelFiltro) {
		this.bairroOrigemImovelFiltro = bairroOrigemImovelFiltro;
	}

	public String getCepOrigemImovelFiltro() {
		return cepOrigemImovelFiltro;
	}

	public void setCepOrigemImovelFiltro(String cepOrigemImovelFiltro) {
		this.cepOrigemImovelFiltro = cepOrigemImovelFiltro;
	}

	public String[] getIdsRegistro() {
		return idsRegistro;
	}

	public void setIdsRegistro(String[] idsRegistro) {
		this.idsRegistro = idsRegistro;
	}

	public String getTipoTransferencia() {
		return tipoTransferencia;
	}

	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}
	
}