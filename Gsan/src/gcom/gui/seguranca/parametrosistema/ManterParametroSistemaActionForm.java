package gcom.gui.seguranca.parametrosistema;

import org.apache.struts.validator.ValidatorActionForm;

public class ManterParametroSistemaActionForm extends ValidatorActionForm {

private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private String  nome;
	
	private String parametroTipo;
			
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getParametroTipo() {
		return parametroTipo;
	}

	public void setParametroTipo(String parametroTipo) {
		this.parametroTipo = parametroTipo;
	}
	
	
	
	
}
