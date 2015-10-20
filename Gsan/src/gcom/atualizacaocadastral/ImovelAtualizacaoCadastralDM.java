package gcom.atualizacaocadastral;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.interceptor.ObjetoGcom;
import gcom.micromedicao.leitura.LeituraAnormalidade;

import java.math.BigDecimal;
import java.util.Date;

public class ImovelAtualizacaoCadastralDM extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

	public static final Short SITUACAO_RETORNADO_PARA_CAMPO = new Short("2");

	//indicador dados retorno - responsavel por identificar em que ambiente está o imovel atualizacao cadastral
	public static final Short AMBIENTE_VIRTUAL_1 = new Short("2");
	public static final Short AMBIENTE_VIRTUAL_2 = new Short("1");
	public static final Short AMBIENTE_PRE_GSAN = new Short("3");
	
	//indicador de situacao
	public static final Short LIBERADO_PARA_ATUALIZACAO = new Short("1");
	public static final Short RETORNADO_PARA_CAMPO = new Short("2");
	
	private Integer id;
	private Integer idImovel;
	private Integer idBairro;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer numeroLote;
	private Integer numeroSubLote;
	private Long idLogradouro;
	private Integer codigoLogradouroCep;
	private Integer codigoLogradouroBairro;
	private String numeroImovel;
	private Short numeroMorador;
	private Integer codigoCep;
	private Integer codigoSituacao;
	private String complementoEndereco;
	private String nomeBairro;
	private String numeroMedidorEnergia;
	private String login;
	private String dsUFSiglaMunicipio;
	private String observacao;
	private String codigoImovel;
	private Short indicadorAtualizado;
	private Short indicadorResetorizado;
	private Short indicadorImovelNovo;
	private Short indicadorPendente;
	private Short indicadorDadosRetorno;
	private Short indicadorRegistroExcluido;
	private Date dataRecebimentoMovimento;
	private Date dataVisita;
	private Date ultimaAlteracao;
	private BigDecimal coordenadaX;
	private BigDecimal coordenadaY;
	
	private Empresa empresa;
	private Municipio municipio;
	private Localidade localidade;
	private ImovelPerfil imovelPerfil;
	private PavimentoRua pavimentoRua;
	private UnidadeFederacao unidadeFederacao;
	private PavimentoCalcada pavimentoCalcada;
	private FonteAbastecimento fonteAbastecimento;
	private EnderecoReferencia enderecoReferencia;
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM;
	
	private LeituraAnormalidade ocorrenciaHidrometro;
	
	public ImovelAtualizacaoCadastralDM() {
		super();
	}

	public ImovelAtualizacaoCadastralDM(Integer idImovel, Integer idBairro,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Integer numeroLote, Integer numeroSubLote, Long idLogradouro,
			Integer codigoLogradouroCep, Integer codigoLogradouroBairro,
			String numeroImovel, Integer codigoCep, Integer codigoSituacao,
			String complementoEndereco, String nomeBairro,
			String numeroMedidorEnergia, String login,
			String dsUFSiglaMunicipio, String observacao, String codigoImovel,
			Short indicadorAtualizado, Short indicadorResetorizado,
			Short indicadorImovelNovo, Short indicadorPendente,
			Short indicadorDadosRetorno, Short indicadorRegistroExcluido,
			Date dataRecebimentoMovimento,
			Date dataVisita, Date ultimaAlteracao, Empresa empresa,
			Municipio municipio, Localidade localidade,
			ImovelPerfil imovelPerfil, PavimentoRua pavimentoRua,
			UnidadeFederacao unidadeFederacao,
			PavimentoCalcada pavimentoCalcada,
			FonteAbastecimento fonteAbastecimento,
			EnderecoReferencia enderecoReferencia,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		super();
		this.idImovel = idImovel;
		this.idBairro = idBairro;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.idLogradouro = idLogradouro;
		this.codigoLogradouroCep = codigoLogradouroCep;
		this.codigoLogradouroBairro = codigoLogradouroBairro;
		this.numeroImovel = numeroImovel;
		this.codigoCep = codigoCep;
		this.codigoSituacao = codigoSituacao;
		this.complementoEndereco = complementoEndereco;
		this.nomeBairro = nomeBairro;
		this.numeroMedidorEnergia = numeroMedidorEnergia;
		this.login = login;
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
		this.observacao = observacao;
		this.codigoImovel = codigoImovel;
		this.indicadorAtualizado = indicadorAtualizado;
		this.indicadorResetorizado = indicadorResetorizado;
		this.indicadorImovelNovo = indicadorImovelNovo;
		this.indicadorPendente = indicadorPendente;
		this.indicadorDadosRetorno = indicadorDadosRetorno;
		this.indicadorRegistroExcluido = indicadorRegistroExcluido;
		this.dataRecebimentoMovimento = dataRecebimentoMovimento;
		this.dataVisita = dataVisita;
		this.ultimaAlteracao = ultimaAlteracao;
		this.empresa = empresa;
		this.municipio = municipio;
		this.localidade = localidade;
		this.imovelPerfil = imovelPerfil;
		this.pavimentoRua = pavimentoRua;
		this.unidadeFederacao = unidadeFederacao;
		this.pavimentoCalcada = pavimentoCalcada;
		this.fonteAbastecimento = fonteAbastecimento;
		this.enderecoReferencia = enderecoReferencia;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

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

	public Integer getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public Short getNumeroMorador() {
		return numeroMorador;
	}

	public void setNumeroMorador(Short numeroMorador) {
		this.numeroMorador = numeroMorador;
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

	public Long getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Long idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public Integer getCodigoLogradouroCep() {
		return codigoLogradouroCep;
	}

	public void setCodigoLogradouroCep(Integer codigoLogradouroCep) {
		this.codigoLogradouroCep = codigoLogradouroCep;
	}

	public Integer getCodigoLogradouroBairro() {
		return codigoLogradouroBairro;
	}

	public void setCodigoLogradouroBairro(Integer codigoLogradouroBairro) {
		this.codigoLogradouroBairro = codigoLogradouroBairro;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public Integer getCodigoCep() {
		return codigoCep;
	}

	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	public Integer getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Integer codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public Short getIndicadorResetorizado() {
		return indicadorResetorizado;
	}

	public void setIndicadorResetorizado(Short indicadorResetorizado) {
		this.indicadorResetorizado = indicadorResetorizado;
	}

	public Short getIndicadorImovelNovo() {
		return indicadorImovelNovo;
	}

	public void setIndicadorImovelNovo(Short indicadorImovelNovo) {
		this.indicadorImovelNovo = indicadorImovelNovo;
	}

	public Short getIndicadorPendente() {
		return indicadorPendente;
	}

	public void setIndicadorPendente(Short indicadorPendente) {
		this.indicadorPendente = indicadorPendente;
	}

	public Short getIndicadorDadosRetorno() {
		return indicadorDadosRetorno;
	}

	public void setIndicadorDadosRetorno(Short indicadorDadosRetorno) {
		this.indicadorDadosRetorno = indicadorDadosRetorno;
	}

	public Short getIndicadorRegistroExcluido() {
		return indicadorRegistroExcluido;
	}

	public void setIndicadorRegistroExcluido(Short indicadorRegistroExcluido) {
		this.indicadorRegistroExcluido = indicadorRegistroExcluido;
	}

	public Date getDataRecebimentoMovimento() {
		return dataRecebimentoMovimento;
	}

	public void setDataRecebimentoMovimento(Date dataRecebimentoMovimento) {
		this.dataRecebimentoMovimento = dataRecebimentoMovimento;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public FonteAbastecimento getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public EnderecoReferencia getEnderecoReferencia() {
		return enderecoReferencia;
	}

	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public ParametroTabelaAtualizacaoCadastralDM getParametroTabelaAtualizacaoCadastralDM() {
		return parametroTabelaAtualizacaoCadastralDM;
	}

	public void setParametroTabelaAtualizacaoCadastralDM(
			ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM) {
		this.parametroTabelaAtualizacaoCadastralDM = parametroTabelaAtualizacaoCadastralDM;
	}

	public LeituraAnormalidade getOcorrenciaHidrometro() {
		return ocorrenciaHidrometro;
	}

	public void setOcorrenciaHidrometro(LeituraAnormalidade ocorrenciaHidrometro) {
		this.ocorrenciaHidrometro = ocorrenciaHidrometro;
	}

	public BigDecimal getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(BigDecimal coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public BigDecimal getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(BigDecimal coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	// métodos auxiliares
	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	/*
	 * Retorna a inscrição do imóvel.
	 */
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getLocalidade().getId().intValue());
		setorComercial = String.valueOf(this.getCodigoSetorComercial());
		quadra = String.valueOf(this.getNumeroQuadra());
		lote = String.valueOf(this.getNumeroLote());
		subLote = String.valueOf(this.getNumeroSubLote());

		if (String.valueOf(this.getLocalidade().getId().intValue()).length() < 3
				&& String.valueOf(this.getLocalidade().getId().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getLocalidade().getId().intValue();
		} else if (String.valueOf(this.getLocalidade().getId().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getLocalidade().getId().intValue();
		}

		if (String.valueOf(this.getCodigoSetorComercial()).length() < 3
				&& String.valueOf(this.getCodigoSetorComercial())
						.length() > 1) {
			setorComercial = zeroUm + this.getCodigoSetorComercial();
		} else if (String.valueOf(this.getCodigoSetorComercial())
				.length() < 3) {
			setorComercial = zeroDois + this.getCodigoSetorComercial();
		}

		if (String.valueOf(this.getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getNumeroQuadra()).length() > 1) {
			quadra = zeroUm + this.getNumeroQuadra();
		} else if (String.valueOf(this.getNumeroQuadra()).length() < 3) {
			quadra = zeroDois + this.getNumeroQuadra();
		}

		if (String.valueOf(this.getNumeroLote()).length() < 4
				&& String.valueOf(this.getNumeroLote()).length() > 2) {
			lote = zeroUm + this.getNumeroLote();
		} else if (String.valueOf(this.getNumeroLote()).length() < 3
				&& String.valueOf(this.getNumeroLote()).length() > 1) {
			lote = zeroDois + this.getNumeroLote();
		} else if (String.valueOf(this.getNumeroLote()).length() < 2) {
			lote = zeroTres + this.getNumeroLote();
		}

		if (String.valueOf(this.getNumeroSubLote()).length() < 3
				&& String.valueOf(this.getNumeroSubLote()).length() > 1) {
			subLote = zeroUm + this.getNumeroSubLote();
		} else if (String.valueOf(this.getNumeroSubLote()).length() < 3) {
			subLote = zeroDois + this.getNumeroSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}
	
}
