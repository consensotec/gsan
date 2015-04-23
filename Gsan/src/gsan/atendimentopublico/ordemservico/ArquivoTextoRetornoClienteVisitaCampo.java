/**
 * 
 */
package gsan.atendimentopublico.ordemservico;

import gsan.cadastro.cliente.OrgaoExpedidorRg;
import gsan.cadastro.geografico.UnidadeFederacao;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Davi Menezes
 *
 */
public class ArquivoTextoRetornoClienteVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nomeCliente;
	
	private String cpf;
	
	private String cnpj;
	
	private String rg;
	
	private Short indicadorAtualizado;
	
	private String mensagemAtualizacao;
	
	private Date dataUltimaAlteracao;
	
	private ArquivoTextoRetornoVisitaCampo arquivoTextoRetornoVisitaCampo;
	
	private OrgaoExpedidorRg orgaoExpedidorRg;
	
	private UnidadeFederacao unidadeFederacaoRg;

	public ArquivoTextoRetornoVisitaCampo getArquivoTextoRetornoVisitaCampo() {
		return arquivoTextoRetornoVisitaCampo;
	}

	public void setArquivoTextoRetornoVisitaCampo(
			ArquivoTextoRetornoVisitaCampo arquivoTextoRetornoVisitaCampo) {
		this.arquivoTextoRetornoVisitaCampo = arquivoTextoRetornoVisitaCampo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public String getMensagemAtualizacao() {
		return mensagemAtualizacao;
	}

	public void setMensagemAtualizacao(String mensagemAtualizacao) {
		this.mensagemAtualizacao = mensagemAtualizacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return orgaoExpedidorRg;
	}

	public void setOrgaoExpedidorRg(OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public UnidadeFederacao getUnidadeFederacaoRg() {
		return unidadeFederacaoRg;
	}

	public void setUnidadeFederacaoRg(UnidadeFederacao unidadeFederacaoRg) {
		this.unidadeFederacaoRg = unidadeFederacaoRg;
	}
}
