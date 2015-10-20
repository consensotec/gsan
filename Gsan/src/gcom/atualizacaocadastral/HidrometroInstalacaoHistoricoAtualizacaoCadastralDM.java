package gcom.atualizacaocadastral;

import gcom.interceptor.ObjetoGcom;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;

import java.util.Date;

/**
 * 
 * @author André Miranda
 * @date 25/08/2014
 *
 */
public class HidrometroInstalacaoHistoricoAtualizacaoCadastralDM extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer numeroInstalacaoHidrometro;
	private String numeroHidrometro;
	private Date dataInstalacaoHidrometro;
	private Date ultimaAlteracao;

	private MedicaoTipo medicaoTipo;
	private HidrometroLocalInstalacao hidrometroLocalInstalacao;
	private HidrometroProtecao hidrometroProtecao;
	private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM;

	public HidrometroInstalacaoHistoricoAtualizacaoCadastralDM(Integer id, Integer numeroInstalacaoHidrometro, String numeroHidrometro, Date dataInstalacaoHidrometro, Date ultimaAlteracao, MedicaoTipo medicaoTipo, HidrometroLocalInstalacao hidrometroLocalInstalacao, HidrometroProtecao hidrometroProtecao, ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		super();
		this.id = id;
		this.numeroInstalacaoHidrometro = numeroInstalacaoHidrometro;
		this.numeroHidrometro = numeroHidrometro;
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
		this.ultimaAlteracao = ultimaAlteracao;
		this.medicaoTipo = medicaoTipo;
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
		this.hidrometroProtecao = hidrometroProtecao;
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public HidrometroInstalacaoHistoricoAtualizacaoCadastralDM(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public Date getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}

	public void setDataInstalacaoHidrometro(Date dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}

	public Integer getNumeroInstalacaoHidrometro() {
		return numeroInstalacaoHidrometro;
	}

	public void setNumeroInstalacaoHidrometro(Integer numeroInstalacaoHidrometro) {
		this.numeroInstalacaoHidrometro = numeroInstalacaoHidrometro;
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

	public HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
		return hidrometroLocalInstalacao;
	}

	public void setHidrometroLocalInstalacao(HidrometroLocalInstalacao hidrometroLocalInstalacao) {
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
	}

	public HidrometroProtecao getHidrometroProtecao() {
		return hidrometroProtecao;
	}

	public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
		this.hidrometroProtecao = hidrometroProtecao;
	}

	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastralDM() {
		return imovelAtualizacaoCadastralDM;
	}

	public void setImovelAtualizacaoCadastralDM(ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public String getDataInstalacaoHidrometroFormatada() {
		return Util.formatarData(this.dataInstalacaoHidrometro);
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
