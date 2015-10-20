package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.cadastro.ParametroTabelaAtualizacaoCadastro;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelAtualizacaoCadastral extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;
	
    /** identifier field */
    private Integer id;

	/** identifier field */
    private Imovel imovel;
    
    /** identifier field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLocalidade;
	
    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private int codigoSetorComercial;

    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private int numeroQuadra;

    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private short lote;

    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private short subLote;
	
    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer numeroSequencialRota;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouroTipo;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String dsLogradouroTipo;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouroTitulo;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String dsLogradouroTitulo;
    
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLogradouro;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoLogradouro;
	
    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idBairro;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nomeBairro;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer codigoCep;

    /** persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroImovel;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String complementoEndereco;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idEnderecoReferencia;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idDespejo;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal volumeReservatorioSuperior;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal volumeReservatorioInferior;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idPavimentoCalcada;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idPavimentoRua;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idFonteAbastecimento;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLigacaoAguaSituacao;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLigacaoEsgotoSituacao;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idImovelPerfil;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal volumePiscina;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idPocoTipo;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short numeroPontosUtilizacao;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short numeroMorador;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal numeroIptu;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Long numeroContratoEnergia;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal coordenadaX;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal coordenadaY;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private BigDecimal areaConstruida;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short indicadorJardim;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer numeroLeituraInstalacaoHidrometro;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idCapacidadeHidrometro;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idMarcaHidrometro;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idLocalInstalacaoHidrometro;

    /** nullable persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idProtecaoHidrometro;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroHidrometro;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Short indicadorCavalete;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String numeroMedidirEnergia;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idCadastroOcorrencia;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoOutrasInformacoes;
	
    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String nomeEntrevistado;
	
    /** persistent field */
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer codigoImovelPrincipal;
    
    /** persistent field */
    private Integer idSituacaoAtualizacaoCadastral;
    
    /** persistent field */
    private Integer idEmpresa;

	private Integer idMunicipio;
	
	private String nomeMunicipio;
	
	private Integer idUinidadeFederacao;
	
	private String dsUFSiglaMunicipio;
	
	private ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro;
	
	private Short indicadorAtualizado;
	
	private Short indicadorHidrometro;
	
	private Date dataLigacaoAgua;
	
	private Date dataLigacaoEsgoto;
	
	private BigDecimal percentualColeta;
	
	private LigacaoAguaDiametro ligacaoAguaDiametro;
	
	private LigacaoAguaMaterial ligacaoAguaMaterial;
	
	private LigacaoAguaPerfil ligacaoAguaPerfil;
	
	private LigacaoEsgotoDiametro ligacaoEsgotoDiametro;
	
	private LigacaoEsgotoMaterial ligacaoEsgotoMaterial;
	
	private LigacaoEsgotoPerfil ligacaoEsgotoPerfil;
	
	public ImovelAtualizacaoCadastral(Imovel imovel, Integer idLocalidade, int codigoSetorComercial, int numeroQuadra, short lote, short subLote, Integer numeroSequencialRota, Integer idLogradouroTipo, String dsLogradouroTipo, Integer idLogradouroTitulo, String dsLogradouroTitulo, Integer idLogradouro, String descricaoLogradouro, Integer idBairro, String nomeBairro, Integer codigoCep, String numeroImovel, String complementoEndereco, Integer idEnderecoReferencia, Integer idDespejo, BigDecimal volumeReservatorioSuperior, BigDecimal volumeReservatorioInferior, Integer idPavimentoCalcada, Integer idPavimentoRua, Integer idFonteAbastecimento, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao, Integer idImovelPerfil, BigDecimal volumePiscina, Integer idPocoTipo, Short numeroPontosUtilizacao, Short numeroMorador, BigDecimal numeroIptu, Long numeroContratoEnergia, BigDecimal coordenadaX, BigDecimal coordenadaY, Date ultimaAlteracao, BigDecimal areaConstruida, Short indicadorJardim, Integer numeroLeituraInstalacaoHidrometro, Integer idCapacidadeHidrometro, Integer idMarcaHidrometro, Integer idLocalInstalacaoHidrometro, Integer idProtecaoHidrometro, String numeroHidrometro, Short indicadorCavalete, String numeroMedidirEnergia, Integer idCadastroOcorrencia, String descricaoOutrasInformacoes, String nomeEntrevistado, Integer codigoImovelPrincipal, Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa, Integer idMunicipio, String nomeMunicipio, Integer idUinidadeFederacao, String dsUFSiglaMunicipio, gcom.cadastro.ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro, Short indicadorAtualizado) {
		this.imovel = imovel;
		this.idLocalidade = idLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.numeroSequencialRota = numeroSequencialRota;
		this.idLogradouroTipo = idLogradouroTipo;
		this.dsLogradouroTipo = dsLogradouroTipo;
		this.idLogradouroTitulo = idLogradouroTitulo;
		this.dsLogradouroTitulo = dsLogradouroTitulo;
		this.idLogradouro = idLogradouro;
		this.descricaoLogradouro = descricaoLogradouro;
		this.idBairro = idBairro;
		this.nomeBairro = nomeBairro;
		this.codigoCep = codigoCep;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.idEnderecoReferencia = idEnderecoReferencia;
		this.idDespejo = idDespejo;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.idPavimentoCalcada = idPavimentoCalcada;
		this.idPavimentoRua = idPavimentoRua;
		this.idFonteAbastecimento = idFonteAbastecimento;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idImovelPerfil = idImovelPerfil;
		this.volumePiscina = volumePiscina;
		this.idPocoTipo = idPocoTipo;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.numeroIptu = numeroIptu;
		this.numeroContratoEnergia = numeroContratoEnergia;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ultimaAlteracao = ultimaAlteracao;
		this.areaConstruida = areaConstruida;
		this.indicadorJardim = indicadorJardim;
		this.numeroLeituraInstalacaoHidrometro = numeroLeituraInstalacaoHidrometro;
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
		this.idMarcaHidrometro = idMarcaHidrometro;
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
		this.idProtecaoHidrometro = idProtecaoHidrometro;
		this.numeroHidrometro = numeroHidrometro;
		this.indicadorCavalete = indicadorCavalete;
		this.numeroMedidirEnergia = numeroMedidirEnergia;
		this.idCadastroOcorrencia = idCadastroOcorrencia;
		this.descricaoOutrasInformacoes = descricaoOutrasInformacoes;
		this.nomeEntrevistado = nomeEntrevistado;
		this.codigoImovelPrincipal = codigoImovelPrincipal;
		this.idSituacaoAtualizacaoCadastral = idSituacaoAtualizacaoCadastral;
		this.idEmpresa = idEmpresa;
		this.idMunicipio = idMunicipio;
		this.nomeMunicipio = nomeMunicipio;
		this.idUinidadeFederacao = idUinidadeFederacao;
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public ImovelAtualizacaoCadastral(){    	
    }

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public short getLote() {
        return this.lote;
    }

    public void setLote(short lote) {
        this.lote = lote;
    }

    public short getSubLote() {
        return this.subLote;
    }

    public void setSubLote(short subLote) {
        this.subLote = subLote;
    }

    public String getNumeroImovel() {
        return this.numeroImovel;
    }

    public void setNumeroImovel(String numeroImovel) {
        this.numeroImovel = numeroImovel;
    }

    public String getComplementoEndereco() {
        return this.complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public Integer getIdDespejo() {
        return this.idDespejo;
    }

    public void setIdDespejo(Integer idDespejo) {
        this.idDespejo = idDespejo;
    }

    public BigDecimal getVolumeReservatorioSuperior() {
        return this.volumeReservatorioSuperior;
    }

    public void setVolumeReservatorioSuperior(BigDecimal volumeReservatorioSuperior) {
        this.volumeReservatorioSuperior = volumeReservatorioSuperior;
    }

    public BigDecimal getVolumeReservatorioInferior() {
        return this.volumeReservatorioInferior;
    }

    public void setVolumeReservatorioInferior(BigDecimal volumeReservatorioInferior) {
        this.volumeReservatorioInferior = volumeReservatorioInferior;
    }

    public Integer getIdPavimentoCalcada() {
        return this.idPavimentoCalcada;
    }

    public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
        this.idPavimentoCalcada = idPavimentoCalcada;
    }

    public Integer getIdPavimentoRua() {
        return this.idPavimentoRua;
    }

    public void setIdPavimentoRua(Integer idPavimentoRua) {
        this.idPavimentoRua = idPavimentoRua;
    }

    public Integer getIdFonteAbastecimento() {
        return this.idFonteAbastecimento;
    }

    public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
        this.idFonteAbastecimento = idFonteAbastecimento;
    }

    public Integer getIdLigacaoAguaSituacao() {
        return this.idLigacaoAguaSituacao;
    }

    public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
        this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
    }

    public Integer getIdLigacaoEsgotoSituacao() {
        return this.idLigacaoEsgotoSituacao;
    }

    public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
        this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
    }

    public Integer getIdImovelPerfil() {
        return this.idImovelPerfil;
    }

    public void setIdImovelPerfil(Integer idImovelPerfil) {
        this.idImovelPerfil = idImovelPerfil;
    }

    public BigDecimal getVolumePiscina() {
        return this.volumePiscina;
    }

    public void setVolumePiscina(BigDecimal volumePiscina) {
        this.volumePiscina = volumePiscina;
    }
    
    public Integer getIdPocoTipo() {
		return idPocoTipo;
	}

	public void setIdPocoTipo(Integer idPocoTipo) {
		this.idPocoTipo = idPocoTipo;
	}

	public Short getNumeroPontosUtilizacao() {
        return this.numeroPontosUtilizacao;
    }

    public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
        this.numeroPontosUtilizacao = numeroPontosUtilizacao;
    }

    public Short getNumeroMorador() {
        return this.numeroMorador;
    }

    public void setNumeroMorador(Short numeroMorador) {
        this.numeroMorador = numeroMorador;
    }

    public BigDecimal getNumeroIptu() {
        return this.numeroIptu;
    }

    public void setNumeroIptu(BigDecimal numeroIptu) {
        this.numeroIptu = numeroIptu;
    }

    public Long getNumeroContratoEnergia() {
        return this.numeroContratoEnergia;
    }

    public void setNumeroContratoEnergia(Long numeroContratoEnergia) {
        this.numeroContratoEnergia = numeroContratoEnergia;
    }

    public BigDecimal getCoordenadaX() {
        return this.coordenadaX;
    }

    public void setCoordenadaX(BigDecimal coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public BigDecimal getCoordenadaY() {
        return this.coordenadaY;
    }

    public void setCoordenadaY(BigDecimal coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }


    public Integer getIdEnderecoReferencia() {
		return idEnderecoReferencia;
	}

	public void setIdEnderecoReferencia(Integer idEnderecoReferencia) {
		this.idEnderecoReferencia = idEnderecoReferencia;
	}

	public Integer getIdLogradouro() {
        return this.idLogradouro;
    }

    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public BigDecimal getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Integer getIdBairro() {
        return this.idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Short getIndicadorJardim() {
        return this.indicadorJardim;
    }

    public void setIndicadorJardim(Short indicadorJardim) {
        this.indicadorJardim = indicadorJardim;
    }

    public Integer getNumeroSequencialRota() {
        return this.numeroSequencialRota;
    }

    public void setNumeroSequencialRota(Integer numeroSequencialRota) {
        this.numeroSequencialRota = numeroSequencialRota;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public String getDescricaoLogradouro() {
        return this.descricaoLogradouro;
    }

    public void setDescricaoLogradouro(String descricaoLogradouro) {
        this.descricaoLogradouro = descricaoLogradouro;
    }

    public Integer getCodigoCep() {
        return this.codigoCep;
    }

    public void setCodigoCep(Integer codigoCep) {
        this.codigoCep = codigoCep;
    }

    public String getNomeBairro() {
        return this.nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Integer getNumeroLeituraInstalacaoHidrometro() {
        return this.numeroLeituraInstalacaoHidrometro;
    }

    public void setNumeroLeituraInstalacaoHidrometro(Integer numeroLeituraInstalacaoHidrometro) {
        this.numeroLeituraInstalacaoHidrometro = numeroLeituraInstalacaoHidrometro;
    }

    public Integer getIdCapacidadeHidrometro() {
        return this.idCapacidadeHidrometro;
    }

    public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
        this.idCapacidadeHidrometro = idCapacidadeHidrometro;
    }

    public Integer getIdMarcaHidrometro() {
        return this.idMarcaHidrometro;
    }

    public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
        this.idMarcaHidrometro = idMarcaHidrometro;
    }

    public Integer getIdLocalInstalacaoHidrometro() {
        return this.idLocalInstalacaoHidrometro;
    }

    public void setIdLocalInstalacaoHidrometro(Integer idLocalInstalacaoHidrometro) {
        this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
    }

    public Integer getIdProtecaoHidrometro() {
        return this.idProtecaoHidrometro;
    }

    public void setIdProtecaoHidrometro(Integer idProtecaoHidrometro) {
        this.idProtecaoHidrometro = idProtecaoHidrometro;
    }

    public String getNumeroHidrometro() {
        return this.numeroHidrometro;
    }

    public void setNumeroHidrometro(String numeroHidrometro) {
        this.numeroHidrometro = numeroHidrometro;
    }

	public Short getIndicadorCavalete() {
		return indicadorCavalete;
	}

	public void setIndicadorCavalete(Short indicadorCavalete) {
		this.indicadorCavalete = indicadorCavalete;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
    public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdSituacaoAtualizacaoCadastral() {
		return idSituacaoAtualizacaoCadastral;
	}

	public void setIdSituacaoAtualizacaoCadastral(
			Integer idSituacaoAtualizacaoCadastral) {
		this.idSituacaoAtualizacaoCadastral = idSituacaoAtualizacaoCadastral;
	}
	
    public String getDsLogradouroTipo() {
		return dsLogradouroTipo;
	}

	public void setDsLogradouroTipo(String dsLogradouroTipo) {
		this.dsLogradouroTipo = dsLogradouroTipo;
	}

	public String getDsLogradouroTitulo() {
		return dsLogradouroTitulo;
	}

	public void setDsLogradouroTitulo(String dsLogradouroTitulo) {
		this.dsLogradouroTitulo = dsLogradouroTitulo;
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
	

	public gcom.cadastro.ParametroTabelaAtualizacaoCadastro getParametroTabelaAtualizacaoCadastro() {
		return parametroTabelaAtualizacaoCadastro;
	}

	public void setParametroTabelaAtualizacaoCadastro(
			gcom.cadastro.ParametroTabelaAtualizacaoCadastro parametroTabelaAtualizacaoCadastro) {
		this.parametroTabelaAtualizacaoCadastro = parametroTabelaAtualizacaoCadastro;
	}
	
	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	/*
	 * Retorna a inscri��o do im�vel.
	 */
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getIdLocalidade().intValue());
		setorComercial = String.valueOf(this.getCodigoSetorComercial());
		quadra = String.valueOf(this.getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getIdLocalidade().intValue()).length() < 3
				&& String.valueOf(this.getIdLocalidade().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getIdLocalidade().intValue();
		} else if (String.valueOf(this.getIdLocalidade().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getIdLocalidade().intValue();
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

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	/**
     *  Descri��o do m�todo>>
     * 
     * @param other
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImovelAtualizacaoCadastral)) {
            return false;
        }
        ImovelAtualizacaoCadastral castOther = (ImovelAtualizacaoCadastral) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroTabela();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getId()));
		return filtro;

	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}



	public Integer getCodigoImovelPrincipal() {
		return codigoImovelPrincipal;
	}



	public void setCodigoImovelPrincipal(Integer codigoImovelPrincipal) {
		this.codigoImovelPrincipal = codigoImovelPrincipal;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdUinidadeFederacao() {
		return idUinidadeFederacao;
	}

	public void setIdUinidadeFederacao(Integer idUinidadeFederacao) {
		this.idUinidadeFederacao = idUinidadeFederacao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getDsUFSiglaMunicipio() {
		return dsUFSiglaMunicipio;
	}

	public void setDsUFSiglaMunicipio(String dsUFSiglaMunicipio) {
		this.dsUFSiglaMunicipio = dsUFSiglaMunicipio;
	}

	public String getDescricaoOutrasInformacoes() {
		return descricaoOutrasInformacoes;
	}

	public void setDescricaoOutrasInformacoes(String descricaoOutrasInformacoes) {
		this.descricaoOutrasInformacoes = descricaoOutrasInformacoes;
	}

	public Integer getIdCadastroOcorrencia() {
		return idCadastroOcorrencia;
	}

	public void setIdCadastroOcorrencia(Integer idCadastroOcorrencia) {
		this.idCadastroOcorrencia = idCadastroOcorrencia;
	}

	public String getNomeEntrevistado() {
		return nomeEntrevistado;
	}

	public void setNomeEntrevistado(String nomeEntrevistado) {
		this.nomeEntrevistado = nomeEntrevistado;
	}

	public String getNumeroMedidirEnergia() {
		return numeroMedidirEnergia;
	}

	public void setNumeroMedidirEnergia(String numeroMedidirEnergia) {
		this.numeroMedidirEnergia = numeroMedidirEnergia;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Date getDataLigacaoAgua() {
		return dataLigacaoAgua;
	}

	public void setDataLigacaoAgua(Date dataLigacaoAgua) {
		this.dataLigacaoAgua = dataLigacaoAgua;
	}

	public Date getDataLigacaoEsgoto() {
		return dataLigacaoEsgoto;
	}

	public void setDataLigacaoEsgoto(Date dataLigacaoEsgoto) {
		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	public BigDecimal getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(BigDecimal percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public LigacaoAguaDiametro getLigacaoAguaDiametro() {
		return ligacaoAguaDiametro;
	}

	public void setLigacaoAguaDiametro(LigacaoAguaDiametro ligacaoAguaDiametro) {
		this.ligacaoAguaDiametro = ligacaoAguaDiametro;
	}

	public LigacaoAguaMaterial getLigacaoAguaMaterial() {
		return ligacaoAguaMaterial;
	}

	public void setLigacaoAguaMaterial(LigacaoAguaMaterial ligacaoAguaMaterial) {
		this.ligacaoAguaMaterial = ligacaoAguaMaterial;
	}

	public LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return ligacaoAguaPerfil;
	}

	public void setLigacaoAguaPerfil(LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}
	
	public LigacaoEsgotoDiametro getLigacaoEsgotoDiametro() {
		return ligacaoEsgotoDiametro;
	}

	public void setLigacaoEsgotoDiametro(LigacaoEsgotoDiametro ligacaoEsgotoDiametro) {
		this.ligacaoEsgotoDiametro = ligacaoEsgotoDiametro;
	}

	public LigacaoEsgotoMaterial getLigacaoEsgotoMaterial() {
		return ligacaoEsgotoMaterial;
	}

	public void setLigacaoEsgotoMaterial(LigacaoEsgotoMaterial ligacaoEsgotoMaterial) {
		this.ligacaoEsgotoMaterial = ligacaoEsgotoMaterial;
	}

	public LigacaoEsgotoPerfil getLigacaoEsgotoPerfil() {
		return ligacaoEsgotoPerfil;
	}

	public void setLigacaoEsgotoPerfil(LigacaoEsgotoPerfil ligacaoEsgotoPerfil) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
	}

}
