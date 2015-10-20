package gcom.cadastro.imovel;

import gcom.micromedicao.hidrometro.HidrometroCapacidade;

import java.io.Serializable;
import java.util.Date;

public class ImovelPerfilCapacidadeHidrometro implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private ImovelPerfil imovelPerfil;
	
	private HidrometroCapacidade hidrometroCapacidade;
	
	private Short indicadorUso;
	
	private Date ultimaAlteracao;
	
	public ImovelPerfilCapacidadeHidrometro(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public HidrometroCapacidade getHidrometroCapacidade() {
		return hidrometroCapacidade;
	}

	public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
		this.hidrometroCapacidade = hidrometroCapacidade;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}