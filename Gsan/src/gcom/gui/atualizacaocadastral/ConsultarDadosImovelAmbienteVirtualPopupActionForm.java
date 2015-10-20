package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.ClienteAtualizacaoCadastralDM;

import org.apache.struts.validator.ValidatorActionForm;

public class ConsultarDadosImovelAmbienteVirtualPopupActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private String dataVisita;
	private String cadastrador;

	//Localidade
	private String idLocalidade;
	private String localidade;
	private String matriculaReferencia;
	private String setorComercial;
	private String descricaoSetorComercial;
	private String quadra;
	private String lote;
	private String subLote;

	//Endereço
	private String tipoLogradouro;
	private String tituloLogradouro;
	private String descricaoLogradouro;
	private String numeroImovel;
	private String complementoLogradouro;
	private String descricaoReferencia;
	private String bairro;
	private String cep;
	private String municipio;

	//Cliente
	private String tipoCliente;
	private String nomeCliente;
	private String cpfcnpj;
	private String numeroRg;
	private String orgaoExpedidor;
	private String uf;
	private String sexo;
	private String telefones;
	private String email;

	//Economias
	private String categoria;
	private String subCategoria;
	private String quantidadeEconomias;

	//Características
	private String numeroMoradores;
	private String idImovelPerfil;
	private String imovelPerfil;
	private String numeroMedidorEnergia;
	private String idPavimentoCalcada;
	private String pavimentoCalcada;
	private String idPavimentoRua;
	private String pavimentoRua;
	private String idFonteAbastecimento;
	private String fonteAbastecimento;

	//Ligação
	private String idSituacaoLigacaoAgua;
	private String situacaoLigacaoAgua;
	private String idSituacaoLigacaoEsgoto;
	private String situacaoLigacaoEsgoto;
	private String numeroHidrometro;
	private String identificacaoPoco;
	private String observacao;
	private String descricaoOcorrenciaHidrometro;
	private String dataInstalacaoHidrometroFormatada;

	//Hidrometro
	private String idLocalInstalacao;
	private String localInstalacao;
	private String idTipoProtecao;
	private String tipoProtecao;
	private String cavalete;
	private String leitura;
	private String medicaoTipo;

	private String indicadorPesquisarImovel;
	private String idImovelAtualizacaoCadastral;
	private String idImovel;
	private String inscricaoImovel;

	//Coordenadas
	private String coordenadaX;
	private String coordenadaY;
	
	private ClienteAtualizacaoCadastralDM cliente;
	
	private String descricaoOcorrencia;
	
	public void limpar(){

		setMatricula(null);
		setDataVisita(null);
		setDescricaoOcorrencia(null);
		
		//Localidade
		setIdLocalidade(null);
		setLocalidade(null);
		setMatriculaReferencia(null);
		setSetorComercial(null);
		setDescricaoSetorComercial(null);
		setQuadra(null);
		setLote(null);
		setSubLote(null);

		//Endereço
		setTipoLogradouro(null);
		setTituloLogradouro(null);
		setDescricaoLogradouro(null);
		setNumeroImovel(null);
		setComplementoLogradouro(null);
		setDescricaoReferencia(null);
		setBairro(null);
		setCep(null);
		setMunicipio(null);

		//Cliente
		setTipoCliente(null);
		setNomeCliente(null);
		setCpfcnpj(null);
		setNumeroRg(null);
		setOrgaoExpedidor(null);
		setUf(null);
		setSexo(null);
		setTelefones(null);
		setEmail(null);
		setCliente(null);

		//Economias
		setCategoria(null);
		setSubCategoria(null);
		setQuantidadeEconomias(null);

		//Características
		setNumeroMoradores(null);
		setIdImovelPerfil(null);
		setImovelPerfil(null);
		setNumeroMedidorEnergia(null);
		setIdPavimentoCalcada(null);
		setPavimentoCalcada(null);
		setIdPavimentoRua(null);
		setPavimentoRua(null);
		setIdFonteAbastecimento(null);
		setFonteAbastecimento(null);

		//Ligação
		setIdSituacaoLigacaoAgua(null);
		setSituacaoLigacaoAgua(null);
		setIdSituacaoLigacaoEsgoto(null);
		setSituacaoLigacaoEsgoto(null);
		setNumeroHidrometro(null);
		setIdentificacaoPoco(null);
		setObservacao(null);
		setDataInstalacaoHidrometroFormatada(null);
		
		//Hidrometro
		setIdLocalInstalacao(null);
		setLocalInstalacao(null);
		setIdTipoProtecao(null);
		setTipoProtecao(null);
		setCavalete(null);
		setLeitura(null);
		setMedicaoTipo(null);

		//Imovel
		setIndicadorPesquisarImovel(null);
		setIdImovelAtualizacaoCadastral(null);
		setIdImovel(null);
		setInscricaoImovel(null);
		
		setCoordenadaX(null);
		setCoordenadaY(null);
		
	}
	public String getMatricula() {
		return matricula;
	}
	public String getDescricaoOcorrencia() {
		return descricaoOcorrencia;
	}
	public void setDescricaoOcorrencia(String descricaoOcorrencia) {
		this.descricaoOcorrencia = descricaoOcorrencia;
	}
	public ClienteAtualizacaoCadastralDM getCliente() {
		return cliente;
	}
	public void setCliente(ClienteAtualizacaoCadastralDM cliente) {
		this.cliente = cliente;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMedicaoTipo() {
		return medicaoTipo;
	}
	public void setMedicaoTipo(String medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getSubLote() {
		return subLote;
	}
	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	public String getTituloLogradouro() {
		return tituloLogradouro;
	}
	public void setTituloLogradouro(String tituloLogradouro) {
		this.tituloLogradouro = tituloLogradouro;
	}
	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}
	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}
	public String getNumeroImovel() {
		return numeroImovel;
	}
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}
	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}
	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfcnpj() {
		return cpfcnpj;
	}
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	public String getNumeroRg() {
		return numeroRg;
	}
	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}
	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}
	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefones() {
		return telefones;
	}
	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	public String getImovelPerfil() {
		return imovelPerfil;
	}
	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}
	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}
	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}
	public String getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}
	public void setIdSituacaoLigacaoAgua(String idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}
	public void setIdSituacaoLigacaoEsgoto(String idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getIdentificacaoPoco() {
		return identificacaoPoco;
	}
	public void setIdentificacaoPoco(String identificacaoPoco) {
		this.identificacaoPoco = identificacaoPoco;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getMatriculaReferencia() {
		return matriculaReferencia;
	}
	public void setMatriculaReferencia(String matriculaReferencia) {
		this.matriculaReferencia = matriculaReferencia;
	}
	public String getDescricaoReferencia() {
		return descricaoReferencia;
	}
	public void setDescricaoReferencia(String descricaoReferencia) {
		this.descricaoReferencia = descricaoReferencia;
	}
	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}
	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}
	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}
	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}
	public String getPavimentoRua() {
		return pavimentoRua;
	}
	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	public String getIdFonteAbastecimento() {
		return idFonteAbastecimento;
	}
	public void setIdFonteAbastecimento(String idFonteAbastecimento) {
		this.idFonteAbastecimento = idFonteAbastecimento;
	}
	public String getFonteAbastecimento() {
		return fonteAbastecimento;
	}
	public void setFonteAbastecimento(String fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}
	public String getDataVisita() {
		return dataVisita;
	}
	public void setDataVisita(String dataVisita) {
		this.dataVisita = dataVisita;
	}
	public String getIdLocalInstalacao() {
		return idLocalInstalacao;
	}
	public void setIdLocalInstalacao(String idLocalInstalacao) {
		this.idLocalInstalacao = idLocalInstalacao;
	}
	public String getLocalInstalacao() {
		return localInstalacao;
	}
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	public String getIdTipoProtecao() {
		return idTipoProtecao;
	}
	public void setIdTipoProtecao(String idTipoProtecao) {
		this.idTipoProtecao = idTipoProtecao;
	}
	public String getTipoProtecao() {
		return tipoProtecao;
	}
	public void setTipoProtecao(String tipoProtecao) {
		this.tipoProtecao = tipoProtecao;
	}
	public String getCavalete() {
		return cavalete;
	}
	public void setCavalete(String cavalete) {
		this.cavalete = cavalete;
	}
	public String getLeitura() {
		return leitura;
	}
	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}
	public String getIndicadorPesquisarImovel() {
		return indicadorPesquisarImovel;
	}
	public void setIndicadorPesquisarImovel(String indicadorPesquisarImovel) {
		this.indicadorPesquisarImovel = indicadorPesquisarImovel;
	}
	public String getIdImovelAtualizacaoCadastral() {
		return idImovelAtualizacaoCadastral;
	}
	public void setIdImovelAtualizacaoCadastral(String idImovelAtualizacaoCadastral) {
		this.idImovelAtualizacaoCadastral = idImovelAtualizacaoCadastral;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getCadastrador() {
		return cadastrador;
	}
	public void setCadastrador(String cadastrador) {
		this.cadastrador = cadastrador;
	}
	public String getDescricaoOcorrenciaHidrometro() {
		return descricaoOcorrenciaHidrometro;
	}
	public void setDescricaoOcorrenciaHidrometro(
			String descricaoOcorrenciaHidrometro) {
		this.descricaoOcorrenciaHidrometro = descricaoOcorrenciaHidrometro;
	}
	public String getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public String getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public String getDataInstalacaoHidrometroFormatada() {
		return dataInstalacaoHidrometroFormatada;
	}
	public void setDataInstalacaoHidrometroFormatada(
			String dataInstalacaoHidrometroFormatada) {
		this.dataInstalacaoHidrometroFormatada = dataInstalacaoHidrometroFormatada;
	}
	
}
