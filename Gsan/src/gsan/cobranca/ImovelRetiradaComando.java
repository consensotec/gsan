package gsan.cobranca;

import gsan.cadastro.MotivoRetiradaCobranca;
import gsan.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe de entidade da tabela
 * cobranca.imovel_retirada_comando
 * 
 * @author Raimundo Martins
 * @date 16/12/2011
 * */
public class ImovelRetiradaComando implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ImovelRetiradaComandoPK pk;
	private Imovel imovel;
	private ComandoEmpresaCobrancaConta comando;
	private MotivoRetiradaCobranca motivoRetirada;
	private Date dataRetirada;
	private Date ultimaAlteracao;
	
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public ComandoEmpresaCobrancaConta getComando() {
		return comando;
	}
	public void setComando(ComandoEmpresaCobrancaConta comando) {
		this.comando = comando;
	}
	public MotivoRetiradaCobranca getMotivoRetirada() {
		return motivoRetirada;
	}
	public void setMotivoRetirada(MotivoRetiradaCobranca motivoRetirada) {
		this.motivoRetirada = motivoRetirada;
	}
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public ImovelRetiradaComandoPK getPk() {
		return pk;
	}
	public void setPk(ImovelRetiradaComandoPK pk) {
		this.pk = pk;
	}
	
}
