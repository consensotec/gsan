package gcom.atualizacaocadastral.bean;

import java.io.Serializable;

/**
 * 
 * [UC1222] Atualizar Cliente a Partir do Dispositivo Movel
 * 
 * @author Arthur Carvalho
 *
 * @date 12/09/2013
 * 
 */
public class AtualizarClienteFoneAPartirDispositivoMovelHelper implements Serializable{
	
    private static final long serialVersionUID = 1L;
	
    private String dddFone;
    
    private String numeroFone;
    
    private String ramalFone;

	public String getDddFone() {
		return dddFone;
	}

	public void setDddFone(String dddFone) {
		this.dddFone = dddFone;
	}

	public String getNumeroFone() {
		return numeroFone;
	}

	public void setNumeroFone(String numeroFone) {
		this.numeroFone = numeroFone;
	}

	public String getRamalFone() {
		return ramalFone;
	}

	public void setRamalFone(String ramalFone) {
		this.ramalFone = ramalFone;
	}
    
    
}