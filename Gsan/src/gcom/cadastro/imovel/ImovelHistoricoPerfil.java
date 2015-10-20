package gcom.cadastro.imovel;

import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class ImovelHistoricoPerfil {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date ultimaAlteracao;
	private Imovel imovel;
	private ImovelPerfil imovelPerfilAnterior;
	private ImovelPerfil imovelPerfilPosterior;
	private PerfilAlteracaoTipo perfilAlteracaoTipo;
	private Usuario usuario;
	private PerfilAlteracaoMotivo perfilAlteracaoMotivo;
	
	public ImovelHistoricoPerfil(){}
	
	public ImovelHistoricoPerfil(Date ultimaAlteracao, Imovel imovel, 
					  ImovelPerfil imovelPerfilAnterior, ImovelPerfil 
					  imovelPerfilPosterior, Usuario usuario, 
					  PerfilAlteracaoMotivo perfilAlteracaoMotivo){
		
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.imovelPerfilAnterior = imovelPerfilAnterior;
		this.imovelPerfilPosterior = imovelPerfilPosterior;
		this.usuario = usuario;
		this.perfilAlteracaoMotivo = perfilAlteracaoMotivo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public ImovelPerfil getImovelPerfilAnterior() {
		return imovelPerfilAnterior;
	}
	public void setImovelPerfilAnterior(ImovelPerfil imovelPerfilAnterior) {
		this.imovelPerfilAnterior = imovelPerfilAnterior;
	}
	public ImovelPerfil getImovelPerfilPosterior() {
		return imovelPerfilPosterior;
	}
	public void setImovelPerfilPosterior(ImovelPerfil imovelPerfilPosterior) {
		this.imovelPerfilPosterior = imovelPerfilPosterior;
	}	
	public PerfilAlteracaoTipo getPerfilAlteracaoTipo() {
		return perfilAlteracaoTipo;
	}
	public void setPerfilAlteracaoTipo(PerfilAlteracaoTipo perfilAlteracaoTipo) {
		this.perfilAlteracaoTipo = perfilAlteracaoTipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PerfilAlteracaoMotivo getPerfilAlteracaoMotivo() {
		return perfilAlteracaoMotivo;
	}

	public void setPerfilAlteracaoMotivo(PerfilAlteracaoMotivo perfilAlteracaoMotivo) {
		this.perfilAlteracaoMotivo = perfilAlteracaoMotivo;
	}
}
