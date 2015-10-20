package gcom.seguranca;

import gcom.cadastro.MensagemRetornoReceitaFederal;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsultarReceitaFederal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String codigoDDDComercial;
	
	private String telefoneComercialCliente;
	
	private Funcionario funcionario;
	
	private String loginUsuario;
	
	private String cpfUsuario;
	
	private Cliente cliente;
	
	private Short acaoUsuario;
	
	private String ufCliente;
	
	private Integer cepCliente;
	
	private String cidadeCliente;
	
	private String bairroCliente;
	
	private String complementoEnderecoCliente;
	
	private String logradouroCliente;
	
	private String numeroFaxCliente;
	
	private String codigoDDDFaxCliente;
	
	private String codigoDDDCelularCliente;
	
	private String numeroCelularCliente;
	
	private String codigoDDDTelefoneResidencialCliente;

	private String numeroTelefoneResidencialCliente;
	
	private String nomeCliente;
	
	private String cpfCliente;
	
	private String signoCliente;
	
	private String sexoCliente;
	
	private String tituloEleitorCliente;
	
	private String numeroRGCliente;
	
	private String nomeDoPaiCliente;
	
	private String nomeDaMaeCliente;
	
	private Integer idade;
	
	private String estadoCivil;
	
	private Date dataNascimento;
	
	private String situacaoRG;
	
	private String situacaoCPF;
	
	private String cnpjCliente;
	
	private String situacaoCNPJ;
	
	private String situacaoInscricaoEstadual;
	
	private String naturezaJuridica;
	
	private String atividadeEconomicaPrincipal;
	
	private String atividadeEconomicaSecundaria;
	
	private Date dataFundacao;
	
	private String inscricaoEstadual;
	
	private String nomeComercial;
	
	private String numeroNIRENIEC;
	
	private String razaoSocial;
	
	private String razaoSocialAnterior;
	
	private BigDecimal valorCapitalSocial;
	
	private Date dataUltimaAlteracaoConsulta;
	
	private String numeroEnderecoCliente;
	
	private Usuario usuarioSolicitante;
	
	private Integer idLogConsulta;
	
	private String ramoAtividade;
	
	private Short codigoOrigemConsulta;
	
	private String situacaoEspecial;
	
    private MensagemRetornoReceitaFederal mensagemRetornoReceitaFederal;
    
    //Variaveis Retornadas pelo WebService não persistidas no Banco
    private String nomePessoaFisica;
    private String dataConsulta;
    private String comprovanteAutenticidade;
    private String horaConsulta;
    private String quadroSocietario;
    
    public ConsultarReceitaFederal(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoDDDComercial() {
		return codigoDDDComercial;
	}

	public void setCodigoDDDComercial(String codigoDDDComercial) {
		this.codigoDDDComercial = codigoDDDComercial;
	}

	public String getTelefoneComercialCliente() {
		return telefoneComercialCliente;
	}

	public void setTelefoneComercialCliente(String telefoneComercialCliente) {
		this.telefoneComercialCliente = telefoneComercialCliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Short getAcaoUsuario() {
		return acaoUsuario;
	}

	public void setAcaoUsuario(Short acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}

	public String getUfCliente() {
		return ufCliente;
	}

	public void setUfCliente(String ufCliente) {
		this.ufCliente = ufCliente;
	}

	public Integer getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(Integer cepCliente) {
		this.cepCliente = cepCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getComplementoEnderecoCliente() {
		return complementoEnderecoCliente;
	}

	public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
		this.complementoEnderecoCliente = complementoEnderecoCliente;
	}

	public String getLogradouroCliente() {
		return logradouroCliente;
	}

	public void setLogradouroCliente(String logradouroCliente) {
		this.logradouroCliente = logradouroCliente;
	}

	public String getNumeroFaxCliente() {
		return numeroFaxCliente;
	}

	public void setNumeroFaxCliente(String numeroFaxCliente) {
		this.numeroFaxCliente = numeroFaxCliente;
	}

	public String getCodigoDDDFaxCliente() {
		return codigoDDDFaxCliente;
	}

	public void setCodigoDDDFaxCliente(String codigoDDDFaxCliente) {
		this.codigoDDDFaxCliente = codigoDDDFaxCliente;
	}

	public String getCodigoDDDCelularCliente() {
		return codigoDDDCelularCliente;
	}

	public void setCodigoDDDCelularCliente(String codigoDDDCelularCliente) {
		this.codigoDDDCelularCliente = codigoDDDCelularCliente;
	}

	public String getNumeroCelularCliente() {
		return numeroCelularCliente;
	}

	public void setNumeroCelularCliente(String numeroCelularCliente) {
		this.numeroCelularCliente = numeroCelularCliente;
	}

	public String getCodigoDDDTelefoneResidencialCliente() {
		return codigoDDDTelefoneResidencialCliente;
	}

	public void setCodigoDDDTelefoneResidencialCliente(
			String codigoDDDTelefoneResidencialCliente) {
		this.codigoDDDTelefoneResidencialCliente = codigoDDDTelefoneResidencialCliente;
	}

	public String getNumeroTelefoneResidencialCliente() {
		return numeroTelefoneResidencialCliente;
	}

	public void setNumeroTelefoneResidencialCliente(
			String numeroTelefoneResidencialCliente) {
		this.numeroTelefoneResidencialCliente = numeroTelefoneResidencialCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getSignoCliente() {
		return signoCliente;
	}

	public void setSignoCliente(String signoCliente) {
		this.signoCliente = signoCliente;
	}

	public String getSexoCliente() {
		return sexoCliente;
	}

	public void setSexoCliente(String sexoCliente) {
		this.sexoCliente = sexoCliente;
	}

	public String getTituloEleitorCliente() {
		return tituloEleitorCliente;
	}

	public void setTituloEleitorCliente(String tituloEleitorCliente) {
		this.tituloEleitorCliente = tituloEleitorCliente;
	}

	public String getNumeroRGCliente() {
		return numeroRGCliente;
	}

	public void setNumeroRGCliente(String numeroRGCliente) {
		this.numeroRGCliente = numeroRGCliente;
	}

	public String getNomeDoPaiCliente() {
		return nomeDoPaiCliente;
	}

	public void setNomeDoPaiCliente(String nomeDoPaiCliente) {
		this.nomeDoPaiCliente = nomeDoPaiCliente;
	}

	public String getNomeDaMaeCliente() {
		return nomeDaMaeCliente;
	}

	public void setNomeDaMaeCliente(String nomeDaMaeCliente) {
		this.nomeDaMaeCliente = nomeDaMaeCliente;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSituacaoRG() {
		return situacaoRG;
	}

	public void setSituacaoRG(String situacaoRG) {
		this.situacaoRG = situacaoRG;
	}

	public String getSituacaoCPF() {
		return situacaoCPF;
	}

	public void setSituacaoCPF(String situacaoCPF) {
		this.situacaoCPF = situacaoCPF;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getSituacaoCNPJ() {
		return situacaoCNPJ;
	}

	public void setSituacaoCNPJ(String situacaoCNPJ) {
		this.situacaoCNPJ = situacaoCNPJ;
	}

	public String getSituacaoInscricaoEstadual() {
		return situacaoInscricaoEstadual;
	}

	public void setSituacaoInscricaoEstadual(String situacaoInscricaoEstadual) {
		this.situacaoInscricaoEstadual = situacaoInscricaoEstadual;
	}

	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	public String getAtividadeEconomicaPrincipal() {
		return atividadeEconomicaPrincipal;
	}

	public void setAtividadeEconomicaPrincipal(String atividadeEconomicaPrincipal) {
		this.atividadeEconomicaPrincipal = atividadeEconomicaPrincipal;
	}

	public String getAtividadeEconomicaSecundaria() {
		return atividadeEconomicaSecundaria;
	}

	public void setAtividadeEconomicaSecundaria(String atividadeEconomicaSecundaria) {
		this.atividadeEconomicaSecundaria = atividadeEconomicaSecundaria;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}

	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}

	public String getNumeroNIRENIEC() {
		return numeroNIRENIEC;
	}

	public void setNumeroNIRENIEC(String numeroNIRENIEC) {
		this.numeroNIRENIEC = numeroNIRENIEC;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getRazaoSocialAnterior() {
		return razaoSocialAnterior;
	}

	public void setRazaoSocialAnterior(String razaoSocialAnterior) {
		this.razaoSocialAnterior = razaoSocialAnterior;
	}

	public BigDecimal getValorCapitalSocial() {
		return valorCapitalSocial;
	}

	public void setValorCapitalSocial(BigDecimal valorCapitalSocial) {
		this.valorCapitalSocial = valorCapitalSocial;
	}

	public Date getDataUltimaAlteracaoConsulta() {
		return dataUltimaAlteracaoConsulta;
	}

	public void setDataUltimaAlteracaoConsulta(Date dataUltimaAlteracaoConsulta) {
		this.dataUltimaAlteracaoConsulta = dataUltimaAlteracaoConsulta;
	}

	public String getNumeroEnderecoCliente() {
		return numeroEnderecoCliente;
	}

	public void setNumeroEnderecoCliente(String numeroEnderecoCliente) {
		this.numeroEnderecoCliente = numeroEnderecoCliente;
	}

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Integer getIdLogConsulta() {
		return idLogConsulta;
	}

	public void setIdLogConsulta(Integer idLogConsulta) {
		this.idLogConsulta = idLogConsulta;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public Short getCodigoOrigemConsulta() {
		return codigoOrigemConsulta;
	}

	public void setCodigoOrigemConsulta(Short codigoOrigemConsulta) {
		this.codigoOrigemConsulta = codigoOrigemConsulta;
	}

	public MensagemRetornoReceitaFederal getMensagemRetornoReceitaFederal() {
		return mensagemRetornoReceitaFederal;
	}

	public void setMensagemRetornoReceitaFederal(
			MensagemRetornoReceitaFederal mensagemRetornoReceitaFederal) {
		this.mensagemRetornoReceitaFederal = mensagemRetornoReceitaFederal;
	}

	public String getSituacaoEspecial() {
		return situacaoEspecial;
	}

	public void setSituacaoEspecial(String situacaoEspecial) {
		this.situacaoEspecial = situacaoEspecial;
	}

	public String getNomePessoaFisica() {
		return nomePessoaFisica;
	}

	public void setNomePessoaFisica(String nomePessoaFisica) {
		this.nomePessoaFisica = nomePessoaFisica;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getComprovanteAutenticidade() {
		return comprovanteAutenticidade;
	}

	public void setComprovanteAutenticidade(String comprovanteAutenticidade) {
		this.comprovanteAutenticidade = comprovanteAutenticidade;
	}

	public String getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public String getQuadroSocietario() {
		return quadroSocietario;
	}

	public void setQuadroSocietario(String quadroSocietario) {
		this.quadroSocietario = quadroSocietario;
	}
	

}