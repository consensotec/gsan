package gsan.atualizacaocadastral.bean;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.localidade.Localidade;
import gsan.micromedicao.Leiturista;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

public class ComandoAtualizacaoCadastralDMHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa;
	
	private Localidade localidade;
	
	private Integer codigoSetorComercial;

	private Integer[] quadrasSelecionadas;
	
	private Usuario usuarioLogado;
	
	private Leiturista leiturista;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer[] getQuadrasSelecionadas() {
		return quadrasSelecionadas;
	}

	public void setQuadrasSelecionadas(Integer[] quadrasSelecionadas) {
		this.quadrasSelecionadas = quadrasSelecionadas;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

}
