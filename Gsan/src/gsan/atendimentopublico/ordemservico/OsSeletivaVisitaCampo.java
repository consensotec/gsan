package gsan.atendimentopublico.ordemservico;

import gsan.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Davi Menezes
 *
 */
public class OsSeletivaVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataProgramacao;
	
	private Integer sequencialProgramacao;
	
	private String inscricaoImovel;
	
	private Integer quantidadeEconomias;
	
	private String descricaoEndereco;
	
	private String descricaoCategoria;
	
	private String descricaoPerfilImovel;
	
	private String descricaoLigacaoEsgotoSituacao;
	
	private String descricaoLigacaoAguaSituacao;
	
	private String descricaoFaturamentoGrupo;
	
	private String descricaoAnormalidadeRegistrada;
	
	private Integer consumoMedio;
	
	private Integer consumoFixo;
	
	private Short indicadorAtualizado;
	
	private Date dataUltimaAlteracao;
	
	private ArquivoTextoVisitaCampo arquivoTextoVisitaCampo;
	
	private OrdemServico ordemServico;
	
	private Imovel imovel;
	
	private String numeroHidrometroAgua;
	
	private String numeroHidrometroPoco;
	
	public Integer getConsumoFixo() {
		return consumoFixo;
	}

	public void setConsumoFixo(Integer consumoFixo) {
		this.consumoFixo = consumoFixo;
	}

	public Integer getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(Integer consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Date getDataProgramacao() {
		return dataProgramacao;
	}

	public void setDataProgramacao(Date dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricaoAnormalidadeRegistrada() {
		return descricaoAnormalidadeRegistrada;
	}

	public void setDescricaoAnormalidadeRegistrada(
			String descricaoAnormalidadeRegistrada) {
		this.descricaoAnormalidadeRegistrada = descricaoAnormalidadeRegistrada;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoEndereco() {
		return descricaoEndereco;
	}

	public void setDescricaoEndereco(String descricaoEndereco) {
		this.descricaoEndereco = descricaoEndereco;
	}

	public String getDescricaoFaturamentoGrupo() {
		return descricaoFaturamentoGrupo;
	}

	public void setDescricaoFaturamentoGrupo(String descricaoFaturamentoGrupo) {
		this.descricaoFaturamentoGrupo = descricaoFaturamentoGrupo;
	}

	public String getDescricaoLigacaoAguaSituacao() {
		return descricaoLigacaoAguaSituacao;
	}

	public void setDescricaoLigacaoAguaSituacao(String descricaoLigacaoAguaSituacao) {
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
	}

	public String getDescricaoLigacaoEsgotoSituacao() {
		return descricaoLigacaoEsgotoSituacao;
	}

	public void setDescricaoLigacaoEsgotoSituacao(
			String descricaoLigacaoEsgotoSituacao) {
		this.descricaoLigacaoEsgotoSituacao = descricaoLigacaoEsgotoSituacao;
	}

	public String getDescricaoPerfilImovel() {
		return descricaoPerfilImovel;
	}

	public void setDescricaoPerfilImovel(String descricaoPerfilImovel) {
		this.descricaoPerfilImovel = descricaoPerfilImovel;
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

	public Short getIndicadorAtualizado() {
		return indicadorAtualizado;
	}

	public void setIndicadorAtualizado(Short indicadorAtualizado) {
		this.indicadorAtualizado = indicadorAtualizado;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public Integer getSequencialProgramacao() {
		return sequencialProgramacao;
	}

	public void setSequencialProgramacao(Integer sequencialProgramacao) {
		this.sequencialProgramacao = sequencialProgramacao;
	}
	
	public String toString() {
        return new ToStringBuilder(this).append("osvcId", getId()).toString();
    }

	public ArquivoTextoVisitaCampo getArquivoTextoVisitaCampo() {
		return arquivoTextoVisitaCampo;
	}

	public void setArquivoTextoVisitaCampo(ArquivoTextoVisitaCampo arquivoTextoVisitaCampo) {
		this.arquivoTextoVisitaCampo = arquivoTextoVisitaCampo;
	}

	
	public String getNumeroHidrometroAgua() {
		return numeroHidrometroAgua;
	}

	public void setNumeroHidrometroAgua(String numeroHidrometroAgua) {
		this.numeroHidrometroAgua = numeroHidrometroAgua;
	}

	public String getNumeroHidrometroPoco() {
		return numeroHidrometroPoco;
	}

	public void setNumeroHidrometroPoco(String numeroHidrometroPoco) {
		this.numeroHidrometroPoco = numeroHidrometroPoco;
	}

	public String getDataProgramacaoFormatada(){
		String retorno = null;
		try {
			retorno = new SimpleDateFormat("yyyyMMdd", new Locale("pt", "BR")).format(this.getDataProgramacao());
		} catch (Exception e) {
			new IllegalArgumentException(this.getDataProgramacao() + " não tem o formato yyyyMMdd.");
		}
		return retorno;
	}
}
