package gsan.gui.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Date;



/**
 * @author Jonathan Marcos
 * @since 08/10/2014
 */
public class AtualizacaoCadastralDMOnlineHelper {
	
	private CepDM cepDM;
	private LogradouroDM logradouroDM;
	private LogradouroBairroDM logradouroBairroDM;
	private LogradouroCepDM logradouroCepDM;
	private ImovelDM imovelDM;
	private ClienteDM clienteDM;
	private ArrayList<ClienteFoneDM> listaClienteFoneDM;
	private HidrometroInstacaoHistoricoDM hidrometroInstacaoHistoricoDM;
	private ArrayList<ImovelSubcategoriaDM> listaImovelSubcategoriaDM;
	private ArrayList<ImovelOcorrenciaDM> listaImovelOcorrenciaDM;
	private byte[] fotoFrenteCasaByte;
	private Integer idFotoTipoFrenteCasa;
	private byte[] fotoHidrometroByte;
	private Integer idFotoTipoHidrometro;

	public static class CepDM {
		private String id;
		private Integer codigoCep;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		public Integer getCodigoCep() {
			return codigoCep;
		}
		public void setCodigoCep(Integer codigoCep) {
			this.codigoCep = codigoCep;
		}
	}

	public static class LogradouroDM {
		private String codigo;
		private String nome;
		private String nomePopular;
		private String nomeLoteamento;
		private Integer idMunicipio;
		private Integer idLogradouroTipo;
		private Integer idLogradouroTitulo;
		private Integer idLocalidade;
		private Integer idEmpresa;

		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getNomePopular() {
			return nomePopular;
		}
		public void setNomePopular(String nomePopular) {
			this.nomePopular = nomePopular;
		}

		public String getNomeLoteamento() {
			return nomeLoteamento;
		}
		public void setNomeLoteamento(String nomeLoteamento) {
			this.nomeLoteamento = nomeLoteamento;
		}

		public Integer getIdMunicipio() {
			return idMunicipio;
		}
		public void setIdMunicipio(Integer idMunicipio) {
			this.idMunicipio = idMunicipio;
		}

		public Integer getIdLogradouroTipo() {
			return idLogradouroTipo;
		}
		public void setIdLogradouroTipo(Integer idLogradouroTipo) {
			this.idLogradouroTipo = idLogradouroTipo;
		}

		public Integer getIdLogradouroTitulo() {
			return idLogradouroTitulo;
		}
		public void setIdLogradouroTitulo(Integer idLogradouroTitulo) {
			this.idLogradouroTitulo = idLogradouroTitulo;
		}

		public Integer getIdLocalidade() {
			return idLocalidade;
		}
		public void setIdLocalidade(Integer idLocalidade) {
			this.idLocalidade = idLocalidade;
		}

		public Integer getIdEmpresa() {
			return idEmpresa;
		}
		public void setIdEmpresa(Integer idEmpresa) {
			this.idEmpresa = idEmpresa;
		}
	}

	public static class LogradouroBairroDM {
		private Integer idBairro;
		private String codigoLogradouro;

		public Integer getIdBairro() {
			return idBairro;
		}
		public void setIdBairro(Integer idBairro) {
			this.idBairro = idBairro;
		}

		public String getCodigoLogradouro() {
			return codigoLogradouro;
		}
		public void setCodigoLogradouro(String codigoLogradouro) {
			this.codigoLogradouro = codigoLogradouro;
		}
	}

	public static class LogradouroCepDM {
		private String codigoUnicoCep;
		private String codigoUnicoLogradouro;
		
		public String getCodigoUnicoCep() {
			return codigoUnicoCep;
		}
		public void setCodigoUnicoCep(String codigoUnicoCep) {
			this.codigoUnicoCep = codigoUnicoCep;
		}
		
		public String getCodigoUnicoLogradouro() {
			return codigoUnicoLogradouro;
		}
		public void setCodigoUnicoLogradouro(String codigoUnicoLogradouro) {
			this.codigoUnicoLogradouro = codigoUnicoLogradouro;
		}
	}

	public static class ImovelDM {
		private Integer id;
		private Integer idImovel;
		private Integer idMunicipio;
		private Integer idLocalidade;
		private Integer codigoSetorComercial;
		private Integer numeroQuadra;
		private Integer numeroLote;
		private Integer numeroSubLote;
		private String codigoUnicoLogradouro;
		private Integer idEnderecoReferencia;
		private String numeroImovel;
		private String descricaoComplementoEndereco;
		private Integer idImovelPerfil;
		private String numeroMedidorEnergia;
		private Short numeroMorador;
		private Integer idPavimentoRua;
		private Integer idPavimentoCalcada;
		private Integer idFonteAbastecimento;
		private Integer idLigacaoAguaSituacao;
		private Integer idLigacaoEsgotoSituacao;
		private Integer idUnidadeFederacao;
		private String dsUFSiglaMunicipio;
		private Date dataVisita;
		private String codigoImovelIdIntegracao;
		private Integer idComando;
		private Integer idEmpresa;
		private String login;
		private Integer idLeituraAnormalidade;
		private String observacao;
		private Integer idLogradouro;
		private Integer idLogradouroBairro;
		private Integer idLogradouroCep;
		private Integer codigoCep;
		private Integer idBairro;
		private String coordenadaX;
		private String coordenadaY;

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getIdImovel() {
			return idImovel;
		}
		public void setIdImovel(Integer idImovel) {
			this.idImovel = idImovel;
		}

		public Integer getIdMunicipio() {
			return idMunicipio;
		}
		public void setIdMunicipio(Integer idMunicipio) {
			this.idMunicipio = idMunicipio;
		}

		public Integer getIdLocalidade() {
			return idLocalidade;
		}
		public void setIdLocalidade(Integer idLocalidade) {
			this.idLocalidade = idLocalidade;
		}

		public Integer getCodigoSetorComercial() {
			return codigoSetorComercial;
		}
		public void setCodigoSetorComercial(Integer codigoSetorComercial) {
			this.codigoSetorComercial = codigoSetorComercial;
		}

		public Integer getNumeroQuadra() {
			return numeroQuadra;
		}
		public void setNumeroQuadra(Integer numeroQuadra) {
			this.numeroQuadra = numeroQuadra;
		}

		public Integer getNumeroLote() {
			return numeroLote;
		}
		public void setNumeroLote(Integer numeroLote) {
			this.numeroLote = numeroLote;
		}

		public Integer getNumeroSubLote() {
			return numeroSubLote;
		}
		public void setNumeroSubLote(Integer numeroSubLote) {
			this.numeroSubLote = numeroSubLote;
		}

		public String getCodigoUnicoLogradouro() {
			return codigoUnicoLogradouro;
		}
		public void setCodigoUnicoLogradouro(String codigoUnicoLogradouro) {
			this.codigoUnicoLogradouro = codigoUnicoLogradouro;
		}

		public Integer getIdEnderecoReferencia() {
			return idEnderecoReferencia;
		}
		public void setIdEnderecoReferencia(Integer idEnderecoReferencia) {
			this.idEnderecoReferencia = idEnderecoReferencia;
		}

		public String getNumeroImovel() {
			return numeroImovel;
		}
		public void setNumeroImovel(String numeroImovel) {
			this.numeroImovel = numeroImovel;
		}

		public String getDescricaoComplementoEndereco() {
			return descricaoComplementoEndereco;
		}
		public void setDescricaoComplementoEndereco(String descricaoComplementoEndereco) {
			this.descricaoComplementoEndereco = descricaoComplementoEndereco;
		}

		public Integer getIdImovelPerfil() {
			return idImovelPerfil;
		}
		public void setIdImovelPerfil(Integer idImovelPerfil) {
			this.idImovelPerfil = idImovelPerfil;
		}

		public String getNumeroMedidorEnergia() {
			return numeroMedidorEnergia;
		}
		public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
			this.numeroMedidorEnergia = numeroMedidorEnergia;
		}

		public Short getNumeroMorador() {
			return numeroMorador;
		}
		public void setNumeroMorador(Short numeroMorador) {
			this.numeroMorador = numeroMorador;
		}

		public Integer getIdPavimentoRua() {
			return idPavimentoRua;
		}
		public void setIdPavimentoRua(Integer idPavimentoRua) {
			this.idPavimentoRua = idPavimentoRua;
		}

		public Integer getIdPavimentoCalcada() {
			return idPavimentoCalcada;
		}
		public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
			this.idPavimentoCalcada = idPavimentoCalcada;
		}

		public Integer getIdFonteAbastecimento() {
			return idFonteAbastecimento;
		}
		public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
			this.idFonteAbastecimento = idFonteAbastecimento;
		}

		public Integer getIdLigacaoAguaSituacao() {
			return idLigacaoAguaSituacao;
		}
		public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
			this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		}

		public Integer getIdLigacaoEsgotoSituacao() {
			return idLigacaoEsgotoSituacao;
		}
		public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
			this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		}

		public Integer getIdUnidadeFederacao() {
			return idUnidadeFederacao;
		}
		public void setIdUnidadeFederacao(Integer idUnidadeFederacao) {
			this.idUnidadeFederacao = idUnidadeFederacao;
		}

		public String getDsUFSiglaMunicipio() {
			return dsUFSiglaMunicipio;
		}
		public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
			this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
		}

		public Date getDataVisita() {
			return dataVisita;
		}
		public void setDataVisita(Date dataVisita) {
			this.dataVisita = dataVisita;
		}

		public String getCodigoImovelIdIntegracao() {
			return codigoImovelIdIntegracao;
		}
		public void setCodigoImovelIdIntegracao(String codigoImovelIdIntegracao) {
			this.codigoImovelIdIntegracao = codigoImovelIdIntegracao;
		}

		public Integer getIdComando() {
			return idComando;
		}
		public void setIdComando(Integer idComando) {
			this.idComando = idComando;
		}

		public Integer getIdEmpresa() {
			return idEmpresa;
		}
		public void setIdEmpresa(Integer idEmpresa) {
			this.idEmpresa = idEmpresa;
		}

		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}

		public Integer getIdLeituraAnormalidade() {
			return idLeituraAnormalidade;
		}
		public void setIdLeituraAnormalidade(Integer idLeituraAnormalidade) {
			this.idLeituraAnormalidade = idLeituraAnormalidade;
		}
		
		public Integer getIdLogradouro() {
			return idLogradouro;
		}
		public void setIdLogradouro(Integer idLogradouro) {
			this.idLogradouro = idLogradouro;
		}
		
		public Integer getIdLogradouroBairro() {
			return idLogradouroBairro;
		}
		public void setIdLogradouroBairro(Integer idLogradouroBairro) {
			this.idLogradouroBairro = idLogradouroBairro;
		}
		
		public Integer getIdLogradouroCep() {
			return idLogradouroCep;
		}
		public void setIdLogradouroCep(Integer idLogradouroCep) {
			this.idLogradouroCep = idLogradouroCep;
		}
		
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
		
		public Integer getCodigoCep() {
			return codigoCep;
		}
		public void setCodigoCep(Integer codigoCep) {
			this.codigoCep = codigoCep;
		}
		
		public Integer getIdBairro() {
			return idBairro;
		}
		public void setIdBairro(Integer idBairro) {
			this.idBairro = idBairro;
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
	}

	public static class ClienteDM {
		private String codigoImovelIdIntegracao;
		private Integer idClienteTipo;
		private String numeroCpfCnpj;
		private String nomeCliente;
		private String numeroRG;
		private Integer idOrgaoExpedidor;
		private Integer idUnidadeFederacao;
		private String dataRGemissao;
		private Integer idSexo;
		private String dataNascimento;
		private Integer idCliente;
		private String nomeMae;
		private Short indicadorDocumentacao;
		private Short indicadorProprietario;
		private Short indicadorResponsavel;

		public String getCodigoImovelIdIntegracao() {
			return codigoImovelIdIntegracao;
		}
		public void setCodigoImovelIdIntegracao(String codigoImovelIdIntegracao) {
			this.codigoImovelIdIntegracao = codigoImovelIdIntegracao;
		}

		public Integer getIdClienteTipo() {
			return idClienteTipo;
		}
		public void setIdClienteTipo(Integer idClienteTipo) {
			this.idClienteTipo = idClienteTipo;
		}

		public String getNumeroCpfCnpj() {
			return numeroCpfCnpj;
		}
		public void setNumeroCpfCnpj(String numeroCpfCnpj) {
			this.numeroCpfCnpj = numeroCpfCnpj;
		}

		public String getNomeCliente() {
			return nomeCliente;
		}
		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}

		public String getNumeroRG() {
			return numeroRG;
		}
		public void setNumeroRG(String numeroRG) {
			this.numeroRG = numeroRG;
		}

		public Integer getIdOrgaoExpedidor() {
			return idOrgaoExpedidor;
		}
		public void setIdOrgaoExpedidor(Integer idOrgaoExpedidor) {
			this.idOrgaoExpedidor = idOrgaoExpedidor;
		}

		public Integer getIdUnidadeFederacao() {
			return idUnidadeFederacao;
		}
		public void setIdUnidadeFederacao(Integer idUnidadeFederacao) {
			this.idUnidadeFederacao = idUnidadeFederacao;
		}

		public String getDataRGemissao() {
			return dataRGemissao;
		}
		public void setDataRGemissao(String dataRGemissao) {
			this.dataRGemissao = dataRGemissao;
		}

		public Integer getIdSexo() {
			return idSexo;
		}
		public void setIdSexo(Integer idSexo) {
			this.idSexo = idSexo;
		}

		public String getDataNascimento() {
			return dataNascimento;
		}
		public void setDataNascimento(String dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		public Integer getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(Integer idCliente) {
			this.idCliente = idCliente;
		}

		public String getNomeMae() {
			return nomeMae;
		}
		public void setNomeMae(String nomeMae) {
			this.nomeMae = nomeMae;
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

	public static class ClienteFoneDM {
		private String codigoImovelIdIntegracao;
		private Integer idFoneTipo;
		private String ddd;
		private String telefone;
		private Short indicadorFonePadrao;

		public String getCodigoImovelIdIntegracao() {
			return codigoImovelIdIntegracao;
		}
		public void setCodigoImovelIdIntegracao(String codigoImovelIdIntegracao) {
			this.codigoImovelIdIntegracao = codigoImovelIdIntegracao;
		}

		public Integer getIdFoneTipo() {
			return idFoneTipo;
		}
		public void setIdFoneTipo(Integer idFoneTipo) {
			this.idFoneTipo = idFoneTipo;
		}

		public String getDdd() {
			return ddd;
		}
		public void setDdd(String ddd) {
			this.ddd = ddd;
		}

		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public Short getIndicadorFonePadrao() {
			return indicadorFonePadrao;
		}
		public void setIndicadorFonePadrao(Short indicadorFonePadrao) {
			this.indicadorFonePadrao = indicadorFonePadrao;
		}
	}

	public static class HidrometroInstacaoHistoricoDM {
		private Integer idImac;
		private String codigoImovelIdIntegracao;
		private Integer idHidrometroLocalInstalacao;
		private Integer idHidrometroProtecao;
		private String numeroHidrometro;
		private Integer numeroInstalacaoHidrometro;

		public Integer getIdImac() {
			return idImac;
		}
		public void setIdImac(Integer idImac) {
			this.idImac = idImac;
		}

		public String getCodigoImovelIdIntegracao() {
			return codigoImovelIdIntegracao;
		}
		public void setCodigoImovelIdIntegracao(String codigoImovelIdIntegracao) {
			this.codigoImovelIdIntegracao = codigoImovelIdIntegracao;
		}

		public Integer getIdHidrometroLocalInstalacao() {
			return idHidrometroLocalInstalacao;
		}
		public void setIdHidrometroLocalInstalacao(Integer idHidrometroLocalInstalacao) {
			this.idHidrometroLocalInstalacao = idHidrometroLocalInstalacao;
		}

		public Integer getIdHidrometroProtecao() {
			return idHidrometroProtecao;
		}
		public void setIdHidrometroProtecao(Integer idHidrometroProtecao) {
			this.idHidrometroProtecao = idHidrometroProtecao;
		}
		
		public String getNumeroHidrometro() {
			return numeroHidrometro;
		}
		public void setNumeroHidrometro(String numeroHidrometro) {
			this.numeroHidrometro = numeroHidrometro;
		}
		
		public Integer getNumeroInstalacaoHidrometro() {
			return numeroInstalacaoHidrometro;
		}
		public void setNumeroInstalacaoHidrometro(Integer numeroInstalacaoHidrometro) {
			this.numeroInstalacaoHidrometro = numeroInstalacaoHidrometro;
		}
	}

	public static class ImovelSubcategoriaDM {
		private String codigoImovelIdIntegracao;
		private Integer idCategoria;
		private Integer idSubcategoria;
		private Short quantidadeEconomias;

		public String getCodigoImovelIdIntegracao() {
			return codigoImovelIdIntegracao;
		}
		public void setCodigoImovelIdIntegracao(String codigoImovelIdIntegracao) {
			this.codigoImovelIdIntegracao = codigoImovelIdIntegracao;
		}

		public Integer getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(Integer idCategoria) {
			this.idCategoria = idCategoria;
		}

		public Integer getIdSubcategoria() {
			return idSubcategoria;
		}
		public void setIdSubcategoria(Integer idSubcategoria) {
			this.idSubcategoria = idSubcategoria;
		}

		public Short getQuantidadeEconomias() {
			return quantidadeEconomias;
		}
		public void setQuantidadeEconomias(Short quantidadeEconomias) {
			this.quantidadeEconomias = quantidadeEconomias;
		}
	}

	public static class ImovelOcorrenciaDM {
		private String codigoImovelIdIntegracao;
		private Integer idCadastroOcorrencia;

		public String getCodigoImovelIdIntegracao() {
			return codigoImovelIdIntegracao;
		}
		public void setCodigoImovelIdIntegracao(String codigoImovelIdIntegracao) {
			this.codigoImovelIdIntegracao = codigoImovelIdIntegracao;
		}

		public Integer getIdCadastroOcorrencia() {
			return idCadastroOcorrencia;
		}
		public void setIdCadastroOcorrencia(Integer idCadastroOcorrencia) {
			this.idCadastroOcorrencia = idCadastroOcorrencia;
		}
	}

	public CepDM getCepDM() {
		return cepDM;
	}
	public void setCepDM(CepDM cepDM) {
		this.cepDM = cepDM;
	}

	public LogradouroDM getLogradouroDM() {
		return logradouroDM;
	}
	public void setLogradouroDM(LogradouroDM logradouroDM) {
		this.logradouroDM = logradouroDM;
	}

	public LogradouroBairroDM getLogradouroBairroDM() {
		return logradouroBairroDM;
	}
	public void setLogradouroBairroDM(LogradouroBairroDM logradouroBairroDM) {
		this.logradouroBairroDM = logradouroBairroDM;
	}

	public LogradouroCepDM getLogradouroCepDM() {
		return logradouroCepDM;
	}
	public void setLogradouroCepDM(LogradouroCepDM logradouroCepDM) {
		this.logradouroCepDM = logradouroCepDM;
	}

	public ImovelDM getImovelDM() {
		return imovelDM;
	}
	public void setImovelDM(ImovelDM imovelDM) {
		this.imovelDM = imovelDM;
	}

	public ClienteDM getClienteDM() {
		return clienteDM;
	}
	public void setClienteDM(ClienteDM clienteDM) {
		this.clienteDM = clienteDM;
	}

	public ArrayList<ClienteFoneDM> getListaClienteFoneDM() {
		return listaClienteFoneDM;
	}
	public void setListaClienteFoneDM(ArrayList<ClienteFoneDM> listaClienteFoneDM) {
		this.listaClienteFoneDM = listaClienteFoneDM;
	}

	public ArrayList<ImovelSubcategoriaDM> getListaImovelSubcategoriaDM() {
		return listaImovelSubcategoriaDM;
	}
	public void setListaImovelSubcategoriaDM(
			ArrayList<ImovelSubcategoriaDM> listaImovelSubcategoriaDM) {
		this.listaImovelSubcategoriaDM = listaImovelSubcategoriaDM;
	}

	public ArrayList<ImovelOcorrenciaDM> getListaImovelOcorrenciaDM() {
		return listaImovelOcorrenciaDM;
	}
	public void setListaImovelOcorrenciaDM(
			ArrayList<ImovelOcorrenciaDM> listaImovelOcorrenciaDM) {
		this.listaImovelOcorrenciaDM = listaImovelOcorrenciaDM;
	}
	
	public HidrometroInstacaoHistoricoDM getHidrometroInstacaoHistoricoDM() {
		return hidrometroInstacaoHistoricoDM;
	}
	public void setHidrometroInstacaoHistoricoDM(
			HidrometroInstacaoHistoricoDM hidrometroInstacaoHistoricoDM) {
		this.hidrometroInstacaoHistoricoDM = hidrometroInstacaoHistoricoDM;
	}
	public Integer getIdFotoTipoFrenteCasa() {
		return idFotoTipoFrenteCasa;
	}
	public void setIdFotoTipoFrenteCasa(Integer idFotoTipoFrenteCasa) {
		this.idFotoTipoFrenteCasa = idFotoTipoFrenteCasa;
	}
	public Integer getIdFotoTipoHidrometro() {
		return idFotoTipoHidrometro;
	}
	public void setIdFotoTipoHidrometro(Integer idFotoTipoHidrometro) {
		this.idFotoTipoHidrometro = idFotoTipoHidrometro;
	}
	
	public byte[] getFotoFrenteCasaByte() {
		return fotoFrenteCasaByte;
	}
	public void setFotoFrenteCasaByte(byte[] fotoFrenteCasaByte) {
		this.fotoFrenteCasaByte = fotoFrenteCasaByte;
	}
	
	public byte[] getFotoHidrometroByte() {
		return fotoHidrometroByte;
	}
	public void setFotoHidrometroByte(byte[] fotoHidrometroByte) {
		this.fotoHidrometroByte = fotoHidrometroByte;
	}
}