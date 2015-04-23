package gsan.atualizacaocadastral;

import gsan.atualizacaocadastral.bean.ImovelGeracaoTabelasTemporariasCadastroDMHelper;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.localidade.Localidade;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class ParametroTabelaAtualizacaoCadastralDM implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Localidade localidade;	
	
	private Integer codigoSetorComercial;	
	
	private Date ultimaAlteracao;
	
	private Usuario usuario;
	
	private Empresa empresa;	
	
	public ParametroTabelaAtualizacaoCadastralDM(){}
	
	public ParametroTabelaAtualizacaoCadastralDM(Localidade localidade, Integer codigoSetorComercial,
			Date ultimaAlteracao, Usuario usuario, Empresa empresa){
		
		this.localidade = localidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.ultimaAlteracao = ultimaAlteracao;
		this.usuario = usuario;
		this.empresa = empresa;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public void montarParametroTabelaAtualizacaoCadastro(
			ImovelGeracaoTabelasTemporariasCadastroDMHelper helper){
			
			if(helper.getFirma() != null && !helper.getFirma().equals("")){
				Empresa empresa = new Empresa();
				empresa.setId(new Integer(helper.getFirma()));
				
				this.setEmpresa(empresa);
			}
			
			if(helper.getUsuario() != null){
				this.setUsuario(helper.getUsuario());
			}			
			
			if(helper.getLocalidadeInicial() != null && 
				!helper.getLocalidadeInicial().equals("")){
				
				Localidade localidade = new Localidade();
				localidade.setId(new Integer(helper.getLocalidade()));
				
				this.setLocalidade(localidade);
			}			
			
			if(helper.getCodigoSetorComercial() != null && 
				!helper.getCodigoSetorComercial().equals("")){
				
				this.setCodigoSetorComercial(new Integer(helper.getCodigoSetorComercial()));
			}
			
			this.setUltimaAlteracao(new Date());			
	}
}