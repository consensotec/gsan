package gcom.relatorio.cadastro;

public class GerarRelatorioClienteCpfCnpjValidadosHelper {

	private String empresa;	
	private String arquivo;
	private String valorAtual;
	private String valorAnterior;
	private Integer codCliente;
	private String clieMobile;
	private String clieGsan;
	private String dataAtual;
	private String dataFinal;
	
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public String getArquivo() {
		return arquivo;
	}
	
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	
	
	
	public Integer getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getClieMobile() {
		return clieMobile;
	}
	
	public void setClieMobile(String clieMobile) {
		this.clieMobile = clieMobile;
	}
	
	public String getClieGsan() {
		return clieGsan;
	}
	
	public void setClieGsan(String clieGsan) {
		this.clieGsan = clieGsan;
	}
	
	public String getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
}
