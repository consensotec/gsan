package gsan.atualizacaocadastral;

import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.OrgaoExpedidorRg;
import gsan.cadastro.cliente.PessoaSexo;
import gsan.cadastro.geografico.UnidadeFederacao;
import gsan.interceptor.ObjetoGcom;

import java.util.Date;

public class ClienteAtualizacaoCadastralDM extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idCliente;
	private String cpfCnpj;
	private String nomeCliente;
	private String nomeMae;
	private String rg;
	private Date dataNascimento;
	private Date dataEmissaoRg;
	private Date ultimaAlteracao;

	private ClienteRelacaoTipo clienteRelacaoTipo;
	private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM;
	private OrgaoExpedidorRg orgaoExpedidorRG;
	private ClienteTipo clienteTipo;
	private PessoaSexo sexo;
	private UnidadeFederacao unidadeFederacao;

	private String telefoneFormatadoCliente;

	private Short indicadorDocumentacao;
	private Short indicadorProprietario;
	private Short indicadorResponsavel;
	
	public ClienteAtualizacaoCadastralDM() {
		super();
	}

	public ClienteAtualizacaoCadastralDM(Integer idCliente, String cpfCnpj,
			String nomeCliente, String nomeMae, String rg, Date dataNascimento,
			Date dataEmissaoRg, Date ultimaAlteracao,
			ClienteRelacaoTipo clienteRelacaoTipo,
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM,
			OrgaoExpedidorRg orgaoExpedidorRG, ClienteTipo clienteTipo,
			PessoaSexo sexo, UnidadeFederacao unidadeFederacao) {
		super();
		this.idCliente = idCliente;
		this.cpfCnpj = cpfCnpj;
		this.nomeCliente = nomeCliente;
		this.nomeMae = nomeMae;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.dataEmissaoRg = dataEmissaoRg;
		this.ultimaAlteracao = ultimaAlteracao;
		this.clienteRelacaoTipo = clienteRelacaoTipo;
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
		this.orgaoExpedidorRG = orgaoExpedidorRG;
		this.clienteTipo = clienteTipo;
		this.sexo = sexo;
		this.unidadeFederacao = unidadeFederacao;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ClienteRelacaoTipo getClienteRelacaoTipo() {
		return clienteRelacaoTipo;
	}

	public void setClienteRelacaoTipo(ClienteRelacaoTipo clienteRelacaoTipo) {
		this.clienteRelacaoTipo = clienteRelacaoTipo;
	}

	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastralDM() {
		return imovelAtualizacaoCadastralDM;
	}

	public void setImovelAtualizacaoCadastralDM(
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public OrgaoExpedidorRg getOrgaoExpedidorRG() {
		return orgaoExpedidorRG;
	}

	public void setOrgaoExpedidorRG(OrgaoExpedidorRg orgaoExpedidorRG) {
		this.orgaoExpedidorRG = orgaoExpedidorRG;
	}

	public ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public PessoaSexo getSexo() {
		return sexo;
	}

	public void setSexo(PessoaSexo sexo) {
		this.sexo = sexo;
	}

	public String getSexoFormatado() {
		if(sexo == null || sexo.getId() == null) {
			return "";
		}

		if(sexo.getId() == 1) {
			return "MASCULINO";
		}

		if(sexo.getId() == 2) {
			return "FEMININO";
		}

		return "";
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getDescricaoRelacaoTipoCliente() {
		String retorno = "";

		if (clienteRelacaoTipo != null && clienteRelacaoTipo.getId() != null) {
			if (clienteRelacaoTipo.getId() == ClienteRelacaoTipo.PROPRIETARIO.intValue()) {
				retorno = "PROPRIETARIO";
			} else if (clienteRelacaoTipo.getId() == ClienteRelacaoTipo.RESPONSAVEL.intValue()) {
				retorno = "RESPONSAVEL";
			} else if (clienteRelacaoTipo.getId() == ClienteRelacaoTipo.USUARIO.intValue()) {
				retorno = "USUARIO";
			}
		}

		return retorno;
	}

	public String getDescricaoTipoCliente() {
		if (clienteTipo != null && clienteRelacaoTipo.getId() != null) {
			return clienteTipo.getDescricao();
		}

		return "";
	}

	public String getTelefoneFormatadoCliente() {
		return telefoneFormatadoCliente;
	}

	public void setTelefoneFormatadoCliente(String telefoneFormatadoCliente) {
		this.telefoneFormatadoCliente = telefoneFormatadoCliente;
	}

	public String getRgFormatado() {
		String retorno = "";

		if (rg != null) {
			retorno = rg;
		}

		if (orgaoExpedidorRG != null && orgaoExpedidorRG.getDescricaoAbreviada() != null) {
			retorno += " " + orgaoExpedidorRG.getDescricaoAbreviada();
		}

		if (unidadeFederacao != null && unidadeFederacao.getDescricao() != null) {
			retorno += "/" + unidadeFederacao.getDescricao();
		}

		return retorno;
	}

	public Short getIndicadorDocumentacao() {
		return indicadorDocumentacao;
	}

	public void setIndicadorDocumentacao(Short indicadorDocumentacao) {
		this.indicadorDocumentacao = indicadorDocumentacao;
	}

	public Short getIndicadorProprietario() {
		return indicadorProprietario;
	}

	public void setIndicadorProprietario(Short indicadorProprietario) {
		this.indicadorProprietario = indicadorProprietario;
	}

	public Short getIndicadorResponsavel() {
		return indicadorResponsavel;
	}

	public void setIndicadorResponsavel(Short indicadorResponsavel) {
		this.indicadorResponsavel = indicadorResponsavel;
	}
	
}
