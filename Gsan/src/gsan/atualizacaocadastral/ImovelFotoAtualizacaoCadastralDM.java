package gsan.atualizacaocadastral;

import gsan.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;

import java.util.Date;

import org.apache.axiom.util.base64.Base64Utils;


public class ImovelFotoAtualizacaoCadastralDM extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM;
	private FotoSituacaoOrdemServico fotoSituacao;
	private byte[] fotoImovel;
	private Date ultimaAlteracao;
	
	public ImovelFotoAtualizacaoCadastralDM(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastralDM() {
		return imovelAtualizacaoCadastralDM;
	}

	public void setImovelAtualizacaoCadastralDM(
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastralDM) {
		this.imovelAtualizacaoCadastralDM = imovelAtualizacaoCadastralDM;
	}

	public FotoSituacaoOrdemServico getFotoSituacao() {
		return fotoSituacao;
	}

	public void setFotoSituacao(FotoSituacaoOrdemServico fotoSituacao) {
		this.fotoSituacao = fotoSituacao;
	}
	
	public byte[] getFotoImovel() {
		return fotoImovel;
	}

	public void setFotoImovel(byte[] fotoImovel) {
		this.fotoImovel = fotoImovel;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public String getFotoImovelBase64() {
		return "data:image/jpg;base64," + Base64Utils.encode(this.fotoImovel);
	}
}