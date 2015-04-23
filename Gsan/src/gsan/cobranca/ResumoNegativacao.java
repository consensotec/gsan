package gsan.cobranca;

import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gsan.cadastro.cliente.ClienteTipo;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ResumoNegativacao implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorNegativacaoConfirmada;

    /** persistent field */
    private int codigoSetorcomercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeInclusoes;

    /** persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private BigDecimal valorPendente;

    /** nullable persistent field */
    private BigDecimal valorPago;

    /** nullable persistent field */
    private BigDecimal valorParcelado;

    /** nullable persistent field */
    private BigDecimal valorCancelado;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Date dataProcessamentoEnvio;
    
    /** persistent field */
    private Localidade localidadeElo;
    
    /** persistent field */
    private Negativador negativador;

    /** persistent field */
    private GerenciaRegional gerenciaRegional;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private gsan.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao;

    /** persistent field */
    private SetorComercial setorComercial;

    /** persistent field */
    private gsan.cobranca.CobrancaGrupo cobrancaGrupo;

    /** persistent field */
    private gsan.cobranca.NegativacaoComando negativacaoComando;

    /** persistent field */
    private ClienteTipo clienteTipo;

    /** persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** persistent field */
    private EsferaPoder esferaPoder;

    /** persistent field */
    private Categoria categoria;
    
    private Integer numeroExecucaoResumoNegativacao;

    //************************************************************
	// RM3755
	// Autor: Ivan Sergio
	// Data: 10/01/2011
	//************************************************************
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	//************************************************************
    
    
    
    
    /** full constructor novo */
    public ResumoNegativacao(Integer id, short indicadorNegativacaoConfirmada, int codigoSetorcomercial, int numeroQuadra, int quantidadeInclusoes, BigDecimal valorDebito, BigDecimal valorPendente, BigDecimal valorPago, BigDecimal valorParcelado, BigDecimal valorCancelado, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, ImovelPerfil imovelPerfil, gsan.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao, SetorComercial setorComercial, gsan.cobranca.CobrancaGrupo cobrancaGrupo, gsan.cobranca.NegativacaoComando negativacaoComando, ClienteTipo clienteTipo, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, Categoria categoria,Integer numeroExecucaoResumoNegativacao) {
        this.id = id;
        this.indicadorNegativacaoConfirmada = indicadorNegativacaoConfirmada;
        this.codigoSetorcomercial = codigoSetorcomercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeInclusoes = quantidadeInclusoes;
        this.valorDebito = valorDebito;
        this.valorPendente = valorPendente;
        this.valorPago = valorPago;
        this.valorParcelado = valorParcelado;
        this.valorCancelado = valorCancelado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.quadra = quadra;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
        this.negativacaoComando = negativacaoComando;
        this.clienteTipo = clienteTipo;
        this.unidadeNegocio = unidadeNegocio;
        this.esferaPoder = esferaPoder;
        this.categoria = categoria;
        this.numeroExecucaoResumoNegativacao = numeroExecucaoResumoNegativacao;
    }
    
         
    /** full constructor */
    public ResumoNegativacao(Integer id, short indicadorNegativacaoConfirmada, int codigoSetorcomercial, int numeroQuadra, int quantidadeInclusoes, BigDecimal valorDebito, BigDecimal valorPendente, BigDecimal valorPago, BigDecimal valorParcelado, BigDecimal valorCancelado, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, ImovelPerfil imovelPerfil, gsan.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao, SetorComercial setorComercial, gsan.cobranca.CobrancaGrupo cobrancaGrupo, gsan.cobranca.NegativacaoComando negativacaoComando, ClienteTipo clienteTipo, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, Categoria categoria) {
        this.id = id;
        this.indicadorNegativacaoConfirmada = indicadorNegativacaoConfirmada;
        this.codigoSetorcomercial = codigoSetorcomercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeInclusoes = quantidadeInclusoes;
        this.valorDebito = valorDebito;
        this.valorPendente = valorPendente;
        this.valorPago = valorPago;
        this.valorParcelado = valorParcelado;
        this.valorCancelado = valorCancelado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.quadra = quadra;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
        this.negativacaoComando = negativacaoComando;
        this.clienteTipo = clienteTipo;
        this.unidadeNegocio = unidadeNegocio;
        this.esferaPoder = esferaPoder;
        this.categoria = categoria;
    }

    /** default constructor */
    public ResumoNegativacao() {
    }

    /** minimal constructor */
    public ResumoNegativacao(Integer id, short indicadorNegativacaoConfirmada, int codigoSetorcomercial, int numeroQuadra, int quantidadeInclusoes, BigDecimal valorDebito, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, Localidade localidade, Quadra quadra, ImovelPerfil imovelPerfil, gsan.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao, SetorComercial setorComercial, gsan.cobranca.CobrancaGrupo cobrancaGrupo, gsan.cobranca.NegativacaoComando negativacaoComando, ClienteTipo clienteTipo, UnidadeNegocio unidadeNegocio, EsferaPoder esferaPoder, Categoria categoria) {
        this.id = id;
        this.indicadorNegativacaoConfirmada = indicadorNegativacaoConfirmada;
        this.codigoSetorcomercial = codigoSetorcomercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeInclusoes = quantidadeInclusoes;
        this.valorDebito = valorDebito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.localidade = localidade;
        this.quadra = quadra;
        this.imovelPerfil = imovelPerfil;
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
        this.setorComercial = setorComercial;
        this.cobrancaGrupo = cobrancaGrupo;
        this.negativacaoComando = negativacaoComando;
        this.clienteTipo = clienteTipo;
        this.unidadeNegocio = unidadeNegocio;
        this.esferaPoder = esferaPoder;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorNegativacaoConfirmada() {
        return this.indicadorNegativacaoConfirmada;
    }

    public void setIndicadorNegativacaoConfirmada(short indicadorNegativacaoConfirmada) {
        this.indicadorNegativacaoConfirmada = indicadorNegativacaoConfirmada;
    }

    public int getCodigoSetorcomercial() {
        return this.codigoSetorcomercial;
    }

    public void setCodigoSetorcomercial(int codigoSetorcomercial) {
        this.codigoSetorcomercial = codigoSetorcomercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeInclusoes() {
        return this.quantidadeInclusoes;
    }

    public void setQuantidadeInclusoes(int quantidadeInclusoes) {
        this.quantidadeInclusoes = quantidadeInclusoes;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public BigDecimal getValorPendente() {
        return this.valorPendente;
    }

    public void setValorPendente(BigDecimal valorPendente) {
        this.valorPendente = valorPendente;
    }

    public BigDecimal getValorPago() {
        return this.valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public BigDecimal getValorParcelado() {
        return this.valorParcelado;
    }

    public void setValorParcelado(BigDecimal valorParcelado) {
        this.valorParcelado = valorParcelado;
    }

    public BigDecimal getValorCancelado() {
        return this.valorCancelado;
    }

    public void setValorCancelado(BigDecimal valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public gsan.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
        return this.cobrancaDebitoSituacao;
    }

    public void setCobrancaDebitoSituacao(gsan.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
        this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
    }

    public SetorComercial getSetorComercial() {
        return this.setorComercial;
    }

    public void setSetorComercial(SetorComercial setorComercial) {
        this.setorComercial = setorComercial;
    }

    public gsan.cobranca.CobrancaGrupo getCobrancaGrupo() {
        return this.cobrancaGrupo;
    }

    public void setCobrancaGrupo(gsan.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public gsan.cobranca.NegativacaoComando getNegativacaoComando() {
        return this.negativacaoComando;
    }

    public void setNegativacaoComando(gsan.cobranca.NegativacaoComando negativacaoComando) {
        this.negativacaoComando = negativacaoComando;
    }

    public ClienteTipo getClienteTipo() {
        return this.clienteTipo;
    }

    public void setClienteTipo(ClienteTipo clienteTipo) {
        this.clienteTipo = clienteTipo;
    }

    public UnidadeNegocio getUnidadeNegocio() {
        return this.unidadeNegocio;
    }

    public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }

    public EsferaPoder getEsferaPoder() {
        return this.esferaPoder;
    }

    public void setEsferaPoder(EsferaPoder esferaPoder) {
        this.esferaPoder = esferaPoder;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Date getDataProcessamentoEnvio() {
		return dataProcessamentoEnvio;
	}

	public void setDataProcessamentoEnvio(Date dataProcessamentoEnvio) {
		this.dataProcessamentoEnvio = dataProcessamentoEnvio;
	}

	public Localidade getLocalidadeElo() {
		return localidadeElo;
	}

	public void setLocalidadeElo(Localidade localidadeElo) {
		this.localidadeElo = localidadeElo;
	}

	public Negativador getNegativador() {
		return negativador;
	}

	public void setNegativador(Negativador negativador) {
		this.negativador = negativador;
	}


	/**
	 * @return Retorna o campo numeroExecucaoResumoNegativacao.
	 */
	public Integer getNumeroExecucaoResumoNegativacao() {
		return numeroExecucaoResumoNegativacao;
	}


	/**
	 * @param numeroExecucaoResumoNegativacao O numeroExecucaoResumoNegativacao a ser setado.
	 */
	public void setNumeroExecucaoResumoNegativacao(
			Integer numeroExecucaoResumoNegativacao) {
		this.numeroExecucaoResumoNegativacao = numeroExecucaoResumoNegativacao;
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


	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}
	
	

}
