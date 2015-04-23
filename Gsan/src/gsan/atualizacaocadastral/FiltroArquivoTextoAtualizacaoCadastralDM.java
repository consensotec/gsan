package gsan.atualizacaocadastral;

import gsan.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroArquivoTextoAtualizacaoCadastralDM extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FiltroArquivoTextoAtualizacaoCadastralDM(){
	}
	
	public FiltroArquivoTextoAtualizacaoCadastralDM(String campoOrderBy){
		this.campoOrderBy = campoOrderBy;
	}
	
	public final static String ID = "id";
	
	public final static String DESCRICAO_ARQUIVO = "descricaoArquivo";
	
	public final static String QUANTIDADE_IMOVEL = "quantidadeImovel";
	
	public final static String ID_LEITURISTA = "leiturista.id";
	
	public final static String LEITURISTA = "leiturista";
	
	public final static String ID_SITUACAO_TRANSMISSAO = "situacaoTransmissao.id";
	
	public final static String SITUACAO_TRANSMISSAO = "situacaoTransmissao";
	
	public final static String ID_PARAMETRO_TABELA_ATUALIZACAO_CADASTRO = "parametroTabelaAtualizacaoCadastralDM.id";
	
	public final static String PARAMETRO_TABELA_ATUALIZACAO_CADASTRO = "parametroTabelaAtualizacaoCadastralDM";
	
	public final static String EMPRESA = "parametroTabelaAtualizacaoCadastralDM.empresa";

}
