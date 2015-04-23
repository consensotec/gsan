package gsan.atualizacaocadastral;

import java.io.Serializable;

import gsan.util.filtro.Filtro;

/**
 * 
 * @author Davi Menezes
 * @date 22/11/2012
 *
 */
public class FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastralDM(){
		
	}
	
	public FiltroHidrometroInstalacaoHistoricoAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String ID_MEDICAO_TIPO = "medicaoTipo.id";
	
	public final static String MEDICAO_TIPO = "medicaoTipo";
	
	public final static String ID_HIDROMETRO_LOCAL_INSTALACAO = "hidrometroLocalInstalacao.id";
	
	public final static String HIDROMETRO_LOCAL_INSTALACAO = "hidrometroLocalInstalacao";
	
	public final static String ID_HIDROMETRO_PROTECAO = "hidrometroProtecao.id";
	
	public final static String HIDROMETRO_PROTECAO = "hidrometroProtecao";
	
	public final static String ID_IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastralDM.id";
	
	public final static String IMOVEL_ATUALIZACAO_CADASTRAL = "imovelAtualizacaoCadastralDM";
}