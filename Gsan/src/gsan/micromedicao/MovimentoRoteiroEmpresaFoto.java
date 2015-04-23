package gsan.micromedicao;

import gsan.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gsan.cadastro.imovel.Imovel;
import gsan.interceptor.ObjetoTransacao;
import gsan.micromedicao.leitura.LeituraAnormalidade;
import gsan.micromedicao.medicao.MedicaoTipo;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC 1359] - Requisições Foto Anormalidade
 * 
 * @author Davi Menezes
 * @date 31/08/2012
 *
 */
public class MovimentoRoteiroEmpresaFoto extends ObjetoTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Imovel imovel;
	private LeituraAnormalidade leituraAnormalidade;
	private Integer anoMesReferencia;
	private MedicaoTipo medicaoTipo;
	private FotoSituacaoOrdemServico fotoSituacao;
	private byte[] foto;
	private Integer medicaoId;
	private Date ultimaAlteracao;
	
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

	public LeituraAnormalidade getLeituraAnormalidade() {
		return leituraAnormalidade;
	}

	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public MedicaoTipo getMedicaoTipo() {
		return medicaoTipo;
	}

	public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}

	public FotoSituacaoOrdemServico getFotoSituacao() {
		return fotoSituacao;
	}

	public void setFotoSituacao(FotoSituacaoOrdemServico fotoSituacao) {
		this.fotoSituacao = fotoSituacao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroMovimentoRoteiroEmpresaFoto filtro = new FiltroMovimentoRoteiroEmpresaFoto();
		filtro.adicionarParametro(new ParametroSimples(FiltroMovimentoRoteiroEmpresaFoto.ID, this.id));
		
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Integer getMedicaoId() {
		return medicaoId;
	}

	public void setMedicaoId(Integer medicaoId) {
		this.medicaoId = medicaoId;
	}
	
}
