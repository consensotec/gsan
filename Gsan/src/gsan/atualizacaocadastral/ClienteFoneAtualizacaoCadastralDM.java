package gsan.atualizacaocadastral;

import gsan.cadastro.cliente.FoneTipo;
import gsan.interceptor.ObjetoGcom;

import java.util.Date;

/**
 * 
 * @author André Miranda
 * @date 25/08/2014
 *
 */
public class ClienteFoneAtualizacaoCadastralDM  extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String ddd;
    private String telefone;
    private String ramal;
    private String contato;
    private Date ultimaAlteracao;
    private Short indicadorFonePadrao;

    private ClienteAtualizacaoCadastralDM clienteAtualizacaoCadastralDM;
    private FoneTipo foneTipo;

    public ClienteFoneAtualizacaoCadastralDM(Integer id, String ddd, String telefone, String ramal, Date ultimaAlteracao, Short indicadorFonePadrao, String contato, ClienteAtualizacaoCadastralDM clienteAtualizacaoCadastralDM, FoneTipo foneTipo) {
		super();
		this.id = id;
		this.ddd = ddd;
		this.telefone = telefone;
		this.ramal = ramal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFonePadrao = indicadorFonePadrao;
		this.contato = contato;
		this.clienteAtualizacaoCadastralDM = clienteAtualizacaoCadastralDM;
		this.foneTipo = foneTipo;
	}

	public ClienteFoneAtualizacaoCadastralDM() {
	}

	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRamal() {
		return this.ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorFonePadrao() {
		return indicadorFonePadrao;
	}

	public void setIndicadorFonePadrao(Short indicadorFonePadrao) {
		this.indicadorFonePadrao = indicadorFonePadrao;
	}

	public ClienteAtualizacaoCadastralDM getClienteAtualizacaoCadastralDM() {
		return clienteAtualizacaoCadastralDM;
	}

	public void setClienteAtualizacaoCadastralDM(ClienteAtualizacaoCadastralDM clienteAtualizacaoCadastralDM) {
		this.clienteAtualizacaoCadastralDM = clienteAtualizacaoCadastralDM;
	}

	public FoneTipo getFoneTipo() {
		return foneTipo;
	}

	public void setFoneTipo(FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getTelefoneFormatado() {
		String retorno = "";

		if (this.telefone != null) {
			if (this.ddd != null) {
				retorno = "(" + this.ddd + ") " + this.telefone;
			}
			if (this.ramal != null) {
				retorno += "-" + this.ramal;
			}
		}

		return retorno;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
