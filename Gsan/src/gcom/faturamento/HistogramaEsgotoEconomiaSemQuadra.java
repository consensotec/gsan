package gcom.faturamento;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.consumotarifa.ConsumoTarifa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HistogramaEsgotoEconomiaSemQuadra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** identifier field */
    private Integer id;

    /** persistent field */
    private int referencia;

    /** persistent field */
    GerenciaRegional gerenciaRegional;
    
    /** persistent field */
    UnidadeNegocio unidadeNegocio;
    
    /** persistent field */
    Localidade localidadeElo;
    
    /** persistent field */
    Localidade localidade;
    
    /** persistent field */
    SetorComercial setorComercial;
    
    /** persistent field */
    private int codigoSetorComercial;
    
    /** persistent field */
    CategoriaTipo categoriaTipo;
    
    /** persistent field */
    Categoria categoria;
    
    /** persistent field */
    ConsumoTarifa consumoTarifa;
    
    /** persistent field */
    ImovelPerfil imovelPerfil;
    
    /** persistent field */
    EsferaPoder esferaPoder;
    
    /** persistent field */
    LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
    
    /** persistent field */
    private int indicadorConsumoReal;

    /** persistent field */
    private int indicadorHidrometro;
    
    /** persistent field */
    private int indicadorPoco;
    
    /** persistent field */
    private int indicadorVolumeFixadoEsgoto;

    /** persistent field */
    private int percentualEsgoto;
    
    /** persistent field */
    private int quantidadeConsumo;
    
    /** persistent field */
    private int quantidadeEconomia;
    
    /** persistent field */
    private BigDecimal valorFaturadoEconomia;

    /** persistent field */
    private int volumeFaturadoEconomia;
    
    /** persistent field */
    private int quantidadeLigacao;
    
    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    Subcategoria subcategoria;
    
    /** persistent field */
    private BigDecimal valorSimuladoEconomia;

	public HistogramaEsgotoEconomiaSemQuadra(Integer id, int referencia,
			GerenciaRegional gerenciaRegional, UnidadeNegocio unidadeNegocio,
			Localidade localidadeElo, Localidade localidade,
			SetorComercial setorComercial, int codigoSetorComercial,
			CategoriaTipo categoriaTipo, Categoria categoria,
			ConsumoTarifa consumoTarifa, ImovelPerfil imovelPerfil,
			EsferaPoder esferaPoder,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			int indicadorConsumoReal, int indicadorHidrometro,
			int indicadorPoco, int indicadorVolumeFixadoEsgoto,
			int percentualEsgoto, int quantidadeConsumo,
			int quantidadeEconomia, BigDecimal valorFaturadoEconomia,
			int volumeFaturadoEconomia, int quantidadeLigacao,
			Date ultimaAlteracao, Subcategoria subcategoria,
			BigDecimal valorSimuladoEconomia) {
		super();
	
		this.id = id;
		this.referencia = referencia;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.localidadeElo = localidadeElo;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.categoriaTipo = categoriaTipo;
		this.categoria = categoria;
		this.consumoTarifa = consumoTarifa;
		this.imovelPerfil = imovelPerfil;
		this.esferaPoder = esferaPoder;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.indicadorConsumoReal = indicadorConsumoReal;
		this.indicadorHidrometro = indicadorHidrometro;
		this.indicadorPoco = indicadorPoco;
		this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
		this.percentualEsgoto = percentualEsgoto;
		this.quantidadeConsumo = quantidadeConsumo;
		this.quantidadeEconomia = quantidadeEconomia;
		this.valorFaturadoEconomia = valorFaturadoEconomia;
		this.volumeFaturadoEconomia = volumeFaturadoEconomia;
		this.quantidadeLigacao = quantidadeLigacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.subcategoria = subcategoria;
		this.valorSimuladoEconomia = valorSimuladoEconomia;
	}
	
	public HistogramaEsgotoEconomiaSemQuadra() {
		super();
		
	}

	public Integer getId() {
		return id;
	}

	public int getReferencia() {
		return referencia;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public Localidade getLocalidadeElo() {
		return localidadeElo;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public CategoriaTipo getCategoriaTipo() {
		return categoriaTipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public int getIndicadorConsumoReal() {
		return indicadorConsumoReal;
	}

	public int getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public int getIndicadorPoco() {
		return indicadorPoco;
	}

	public int getIndicadorVolumeFixadoEsgoto() {
		return indicadorVolumeFixadoEsgoto;
	}

	public int getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public int getQuantidadeConsumo() {
		return quantidadeConsumo;
	}

	public int getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public BigDecimal getValorFaturadoEconomia() {
		return valorFaturadoEconomia;
	}

	public int getVolumeFaturadoEconomia() {
		return volumeFaturadoEconomia;
	}

	public int getQuantidadeLigacao() {
		return quantidadeLigacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public BigDecimal getValorSimuladoEconomia() {
		return valorSimuladoEconomia;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public void setLocalidadeElo(Localidade localidadeElo) {
		this.localidadeElo = localidadeElo;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public void setCategoriaTipo(CategoriaTipo categoriaTipo) {
		this.categoriaTipo = categoriaTipo;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setConsumoTarifa(ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public void setEsferaPoder(EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public void setIndicadorConsumoReal(int indicadorConsumoReal) {
		this.indicadorConsumoReal = indicadorConsumoReal;
	}

	public void setIndicadorHidrometro(int indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public void setIndicadorPoco(int indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public void setIndicadorVolumeFixadoEsgoto(int indicadorVolumeFixadoEsgoto) {
		this.indicadorVolumeFixadoEsgoto = indicadorVolumeFixadoEsgoto;
	}

	public void setPercentualEsgoto(int percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public void setQuantidadeConsumo(int quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}

	public void setQuantidadeEconomia(int quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public void setValorFaturadoEconomia(BigDecimal valorFaturadoEconomia) {
		this.valorFaturadoEconomia = valorFaturadoEconomia;
	}

	public void setVolumeFaturadoEconomia(int volumeFaturadoEconomia) {
		this.volumeFaturadoEconomia = volumeFaturadoEconomia;
	}

	public void setQuantidadeLigacao(int quantidadeLigacao) {
		this.quantidadeLigacao = quantidadeLigacao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public void setValorSimuladoEconomia(BigDecimal valorSimuladoEconomia) {
		this.valorSimuladoEconomia = valorSimuladoEconomia;
	}

	
}
