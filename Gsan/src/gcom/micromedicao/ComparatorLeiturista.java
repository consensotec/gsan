package gcom.micromedicao;

import java.util.Comparator;

public class ComparatorLeiturista implements Comparator<Leiturista> {

	public int compare(Leiturista l1, Leiturista l2) {
		if(l1.getCliente() != null && !l1.getCliente().equals("")){
			if(l2.getCliente() != null && !l2.getCliente().equals("")){
				return l1.getCliente().getNome().compareTo(l2.getCliente().getNome());
			}else{
				return l1.getCliente().getNome().compareTo(l2.getFuncionario().getNome());
			}
		}else{
			if(l2.getCliente() != null && !l2.getCliente().equals("")){
				return l1.getFuncionario().getNome().compareTo(l2.getCliente().getNome());
			}else{
				return l1.getFuncionario().getNome().compareTo(l2.getFuncionario().getNome());
			}
		}
	}

}
