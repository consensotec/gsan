package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1193] Consultar Comandos de OS Seletiva de Inspeção de Anormalidade
 * 
 * @author Vivianne Sousa
 * @since 11/07/2011
 */
public class ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idComando;

	private String descComando;

	private Date dataExecucao;

	private Date dataEncerramento;

	private String situacao;
	
	private Date dataEncerramentoPrevista;
	
	private String localidadeInicial;

	private String localidadeFinal;

	private String setorInicial;

	private String setorFinal;

	private String matiruclaImovel;

	
	public ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper(Integer idComando, String descComando, Date dataExecucao, Date dataEncerramento, String situacao, Date dataEncerramentoPrevista) {
		super();		
		this.idComando = idComando;
		this.descComando = descComando;
		this.dataExecucao = dataExecucao;
		this.dataEncerramento = dataEncerramento;
		this.situacao = situacao;
		this.dataEncerramentoPrevista = dataEncerramentoPrevista;
	}

	public ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper() {
		super();		
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public String getDescComando() {
		return descComando;
	}

	public void setDescComando(String descComando) {
		this.descComando = descComando;
	}

	public Integer getIdComando() {
		return idComando;
	}

	public void setIdComando(Integer idComando) {
		this.idComando = idComando;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public Date getDataEncerramentoPrevista() {
		return dataEncerramentoPrevista;
	}

	public void setDataEncerramentoPrevista(Date dataEncerramentoPrevista) {
		this.dataEncerramentoPrevista = dataEncerramentoPrevista;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getSetorInicial() {
		return setorInicial;
	}

	public void setSetorInicial(String setorInicial) {
		this.setorInicial = setorInicial;
	}

	public String getSetorFinal() {
		return setorFinal;
	}

	public void setSetorFinal(String setorFinal) {
		this.setorFinal = setorFinal;
	}

	public String getMatiruclaImovel() {
		return matiruclaImovel;
	}

	public void setMatiruclaImovel(String matiruclaImovel) {
		this.matiruclaImovel = matiruclaImovel;
	}
	
}
