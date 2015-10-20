package gcom.cadastro.cliente;

import gcom.faturamento.conta.ContaGeral;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class ClienteContaAnterior implements Serializable{
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
	/** persistent field */
    private Date dataVinculo;

	/*** persistent field */
	private Short indicadorNomeConta;
	
    /** persistent field */
    private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;
    
    /** persistent field */
    private gcom.cadastro.cliente.Cliente cliente;
    
    /** persistent field */
    private ContaGeral contaGeral;
    
    /** persistent field */
    private Usuario usuario;
    
	/** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** default constructor */
	public ClienteContaAnterior() {
	}

	/** full constructor */
	public ClienteContaAnterior(Integer id, Date dataVinculo, Short indicadorNomeConta,
			ClienteRelacaoTipo clienteRelacaoTipo, Cliente cliente,
			ContaGeral contaGeral, Usuario usuario, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.dataVinculo = dataVinculo;
		this.indicadorNomeConta = indicadorNomeConta;
		this.clienteRelacaoTipo = clienteRelacaoTipo;
		this.cliente = cliente;
		this.contaGeral = contaGeral;
		this.usuario = usuario;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataVinculo() {
		return dataVinculo;
	}

	public void setDataVinculo(Date dataVinculo) {
		this.dataVinculo = dataVinculo;
	}

	public Short getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(Short indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}

	public gcom.cadastro.cliente.ClienteRelacaoTipo getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(
			gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public gcom.cadastro.cliente.Cliente getCliente() {
		return cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}  
    
}
