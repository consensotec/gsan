package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroImovelHistoricoPerfil extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroImovelHistoricoPerfil(){}
	
	public FiltroImovelHistoricoPerfil(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}	
	
	public static final String ULTIMA_ALTERACAO = "ultimaAlteracao";
	
	public static final String IMOVEL = "imovel";
	
	public static final String IMOVEL_ID = "imovel.id";
	
	public static final String IMOVEL_PERFIL_ANTERIOR = "imovelPerfilAnterior";
	
	public static final String IMOVEL_PERFIL_ANTERIOR_ID = "imovelPerfilAnterior.id";
	
	public static final String IMOVEL_PERFIL_POSTERIOR = "imovelPerfilPosterior";
	
	public static final String IMOVEL_PERFIL_POSTERIOR_ID = "imovelPerfilPosterior.id";
	
	public static final String PERFIL_ALTERACAO_TIPO = "perfilAlteracaoTipo";
	
	public static final String PERFIL_ALTERACAO_TIPO_ID = "perfilAlteracaoTipo.id";
	
	public static final String USUARIO = "usuario";
	
	public static final String USUARIO_ID = "usuario.id";
	
	public static final String PERFIL_ALTERACAO_MOTIVO = "perfilAlteracaoMotivo";
	
	public static final String PERFIL_ALTERACAO_MOTIVO_ID = "perfilAlteracaoMotivo.id";

}