package gsan.atualizacaocadastral;

import java.util.Date;

import gsan.cadastro.imovel.CadastroOcorrencia;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.Filtro;

public class ImovelOcorrenciaAtualizacaoCadastralDM extends ObjetoTransacao {
private static final long serialVersionUID = 1L;
	
	private Integer id;
	private ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral;
	private CadastroOcorrencia cadastroOcorrencia;
	private Date ultimaAlteracao;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelAtualizacaoCadastralDM getImovelAtualizacaoCadastral() {
		return imovelAtualizacaoCadastral;
	}

	public void setImovelAtualizacaoCadastral(
			ImovelAtualizacaoCadastralDM imovelAtualizacaoCadastral) {
		this.imovelAtualizacaoCadastral = imovelAtualizacaoCadastral;
	}

	public CadastroOcorrencia getCadastroOcorrencia() {
		return cadastroOcorrencia;
	}

	public void setCadastroOcorrencia(CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
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
}
