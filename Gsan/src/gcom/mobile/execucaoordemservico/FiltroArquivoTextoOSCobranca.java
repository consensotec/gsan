package gcom.mobile.execucaoordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroArquivoTextoOSCobranca extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID = "id";
	public static final String IMEI_LEITURISTA = "leiturista.numeroImei";
	public static final String ID_SITUACAO_TRANSMISSAO_LEITURA = "situacao.id";
	public static final String SITUACAO_TRANSMISSAO_LEITURA = "situacao";
	 
}
