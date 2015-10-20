/**
 * 
 */
package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Davi Menezes
 *
 */
public class ArquivoTextoVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private BigDecimal imei;
	
	private Integer codigoSetorComercialInicial;
	
	private Integer codigoSetorComercialFinal;
	
	private Integer numeroQuadraInicial;
	
	private Integer numeroQuadraFinal;
	
	private Integer quantidadeOrdemServico;
	
	private Date dataUltimaAlteracao;
	
	private Date dataEnvioArquivo;
	
	private Localidade localidade;
	
	private Leiturista leiturista;
	
	private SituacaoTransmissaoLeitura situacaoTransmissaoLeitura;
	
	private ComandoOrdemSeletiva comandoOrdemSeletiva;

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public ComandoOrdemSeletiva getComandoOrdemSeletiva() {
		return comandoOrdemSeletiva;
	}

	public void setComandoOrdemSeletiva(ComandoOrdemSeletiva comandoOrdemSeletiva) {
		this.comandoOrdemSeletiva = comandoOrdemSeletiva;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
	public Date getDataEnvioArquivo() {
		return dataEnvioArquivo;
	}

	public void setDataEnvioArquivo(Date dataEnvioArquivo) {
		this.dataEnvioArquivo = dataEnvioArquivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getImei() {
		return imei;
	}

	public void setImei(BigDecimal imei) {
		this.imei = imei;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getQuantidadeOrdemServico() {
		return quantidadeOrdemServico;
	}

	public void setQuantidadeOrdemServico(Integer quantidadeOrdemServico) {
		this.quantidadeOrdemServico = quantidadeOrdemServico;
	}

	public SituacaoTransmissaoLeitura getSituacaoTransmissaoLeitura() {
		return situacaoTransmissaoLeitura;
	}

	public void setSituacaoTransmissaoLeitura(
			SituacaoTransmissaoLeitura situacaoTransmissaoLeitura) {
		this.situacaoTransmissaoLeitura = situacaoTransmissaoLeitura;
	}
	
}
