package gsan.atualizacaocadastral;

import gsan.interceptor.ObjetoGcom;
import gsan.micromedicao.Leiturista;

import java.util.Date;

/**
 * 
 * @author André Miranda
 * @date 25/08/2014
 *
 */
public class ArquivoTextoAtualizacaoCadastralDM extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer quantidadeImovel;
    private String descricaoArquivo;
    private byte[] arquivoTexto;
    private Date ultimaAlteracao;
    private Date dataArquivoLiberado;
    private Date dataFinalizacaoArquivo;

    private Leiturista leiturista;
    private SituacaoTransmissaoAtualizacaoCadastralDM situacaoTransmissao;
    private ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM;

	public ArquivoTextoAtualizacaoCadastralDM(Integer id, String descricaoArquivo, Date dataArquivoLiberado, Integer quantidadeImovel, byte[] arquivoTexto, Date ultimaAlteracao, Leiturista leiturista, SituacaoTransmissaoAtualizacaoCadastralDM situacaoTransmissao, ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM) {
		this.id = id;
		this.descricaoArquivo = descricaoArquivo;
		this.quantidadeImovel = quantidadeImovel;
		this.dataArquivoLiberado = dataArquivoLiberado;
		this.arquivoTexto = arquivoTexto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.leiturista = leiturista;
		this.situacaoTransmissao = situacaoTransmissao;
		this.parametroTabelaAtualizacaoCadastralDM = parametroTabelaAtualizacaoCadastralDM;
	}

	public ArquivoTextoAtualizacaoCadastralDM() {
	}

	public byte[] getArquivoTexto() {
		return arquivoTexto;
	}

	public void setArquivoTexto(byte[] arquivoTexto) {
		this.arquivoTexto = arquivoTexto;
	}

	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}

	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Integer getQuantidadeImovel() {
		return quantidadeImovel;
	}

	public void setQuantidadeImovel(Integer quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	public SituacaoTransmissaoAtualizacaoCadastralDM getSituacaoTransmissao() {
		return situacaoTransmissao;
	}

	public void setSituacaoTransmissao(SituacaoTransmissaoAtualizacaoCadastralDM situacaoTransmissao) {
		this.situacaoTransmissao = situacaoTransmissao;
	}

	public ParametroTabelaAtualizacaoCadastralDM getParametroTabelaAtualizacaoCadastralDM() {
		return parametroTabelaAtualizacaoCadastralDM;
	}

	public void setParametroTabelaAtualizacaoCadastralDM(
			ParametroTabelaAtualizacaoCadastralDM parametroTabelaAtualizacaoCadastralDM) {
		this.parametroTabelaAtualizacaoCadastralDM = parametroTabelaAtualizacaoCadastralDM;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataArquivoLiberado() {
		return dataArquivoLiberado;
	}

	public void setDataArquivoLiberado(Date dataArquivoLiberado) {
		this.dataArquivoLiberado = dataArquivoLiberado;
	}

	
	public Date getDataFinalizacaoArquivo() {
		return dataFinalizacaoArquivo;
	}

	public void setDataFinalizacaoArquivo(Date dataFinalizacaoArquivo) {
		this.dataFinalizacaoArquivo = dataFinalizacaoArquivo;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
