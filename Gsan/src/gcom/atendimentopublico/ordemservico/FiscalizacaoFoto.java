package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class FiscalizacaoFoto extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private OrdemServico ordemServico;
	private FotoTipo fotoTipo;
	private byte[] arquivo;
	private String descricao;
	private String extensao;
	private Date ultimaAlteracao;

	public FiscalizacaoFoto() {
		super();
	}

	public FiscalizacaoFoto(Integer id, OrdemServico ordemServico,
			FotoTipo fotoTipo, byte[] arquivo, String descricao,
			String extensao, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.setOrdemServico(ordemServico);
		this.fotoTipo = fotoTipo;
		this.arquivo = arquivo;
		this.descricao = descricao;
		this.extensao = extensao;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public FotoTipo getFotoTipo() {
		return fotoTipo;
	}

	public void setFotoTipo(FotoTipo fotoTipo) {
		this.fotoTipo = fotoTipo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
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
		String[] retorno = {"fzft_id"};
		return retorno;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	

}
