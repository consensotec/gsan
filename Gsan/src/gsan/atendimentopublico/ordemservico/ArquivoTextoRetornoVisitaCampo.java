/**
 * 
 */
package gsan.atendimentopublico.ordemservico;

import gsan.cadastro.imovel.PavimentoCalcada;
import gsan.cadastro.imovel.PavimentoRua;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Davi Menezes
 *
 */
public class ArquivoTextoRetornoVisitaCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricaoAnormalidadeLeitura;
	
	private Short indicadorNenhumaAcao;
	
	private Date dataUltimaAlteracao;
	
	private ArquivoTextoVisitaCampo arquivoTextoVisitaCampo;
	
	private OrdemServico ordemServico;
	
	private PavimentoRua pavimentoRua;
	
	private PavimentoCalcada pavimentoCalcada;
	
	private LeituraAnormalidade leituraAnormalidade;
	
	private Usuario usuario;
	
	private Date dataRecebimento;
	
	private Short indicadorConferida;

	//Constantes do Arquivo de Retorno de Fiscalização de anormalidade
	public static final String DADOS_PARAMETROS = "00";
	public static final String DADOS_ORDEM_SERVICO = "01";
	public static final String DADOS_CLIENTE = "02";
	public static final String DADOS_ANORMALIDADE = "03";
	
	public ArquivoTextoVisitaCampo getArquivoTextoVisitaCampo() {
		return arquivoTextoVisitaCampo;
	}

	public void setArquivoTextoVisitaCampo(
			ArquivoTextoVisitaCampo arquivoTextoVisitaCampo) {
		this.arquivoTextoVisitaCampo = arquivoTextoVisitaCampo;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricaoAnormalidadeLeitura() {
		return descricaoAnormalidadeLeitura;
	}

	public void setDescricaoAnormalidadeLeitura(String descricaoAnormalidadeLeitura) {
		this.descricaoAnormalidadeLeitura = descricaoAnormalidadeLeitura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LeituraAnormalidade getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Short getIndicadorNenhumaAcao() {
		return indicadorNenhumaAcao;
	}

	public void setIndicadorNenhumaAcao(Short indicadorNenhumaAcao) {
		this.indicadorNenhumaAcao = indicadorNenhumaAcao;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Short getIndicadorConferida() {
		return indicadorConferida;
	}

	public void setIndicadorConferida(Short indicadorConferida) {
		this.indicadorConferida = indicadorConferida;
	}
	
}
