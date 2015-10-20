package gcom.cadastro.cliente.bean;

import gcom.util.Util;

import java.util.Comparator;

public class ComparatorClienteImovelHelper implements Comparator<ClienteImovelHelper> {

	public int compare(ClienteImovelHelper p1, ClienteImovelHelper p2) {
		Integer dataInicio1 = Util.formatarDiaMesAnoComBarraParaAnoMesDia(p1.getDataInicioRelacao());
		Integer dataInicio2  = Util.formatarDiaMesAnoComBarraParaAnoMesDia(p2.getDataInicioRelacao());
		
		if ( dataInicio1.compareTo(dataInicio2) == 0) {
			
			if ( p1.getDataFimRelacao() == null) {
				return 1; 
			}
		}
		
		return dataInicio1.compareTo(dataInicio2);
	}

}