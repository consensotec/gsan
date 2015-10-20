package gcom.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class CertidaoNegativaDebito extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	private Date dataGeracao;

	private Date dataVencimento;
	
	private String numeroAutenticacao;

	private byte[] documentoGerado;
	
	private Date ultimaAlteracao;
	
	private Imovel imovel;
	
	private Cliente cliente;
	
	private Usuario usuario;
	
	public CertidaoNegativaDebito() {
		super();
	}

	public CertidaoNegativaDebito(
			Integer id, Date dataGeracao, Date dataVencimento, String numeroAutenticacao, byte[] documentoGerado,
			Date ultimaAlteracao, Imovel imovel, Cliente cliente, Usuario usuario) {
		super();
		this.id = id;
		this.dataGeracao = dataGeracao;
		this.dataVencimento = dataVencimento;
		this.numeroAutenticacao = numeroAutenticacao;
		this.documentoGerado = documentoGerado;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.cliente = cliente;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNumeroAutenticacao() {
		return numeroAutenticacao;
	}

	public void setNumeroAutenticacao(String numeroAutenticacao) {
		this.numeroAutenticacao = numeroAutenticacao;
	}

	public byte[] getDocumentoGerado() {
		return documentoGerado;
	}

	public void setDocumentoGerado(byte[] documentoGerado) {
		this.documentoGerado = documentoGerado;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroCertidaoNegativaDebito filtroCertidaoNegativaDebito = new FiltroCertidaoNegativaDebito();

		filtroCertidaoNegativaDebito.adicionarParametro(new ParametroSimples(
				FiltroCertidaoNegativaDebito.ID, this.getId()));
		return filtroCertidaoNegativaDebito;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
}
