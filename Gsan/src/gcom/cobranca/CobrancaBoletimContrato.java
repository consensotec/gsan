package gcom.cobranca;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

/** @author Hibernate CodeGenerator */
public class CobrancaBoletimContrato extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Integer anoMesReferencia;

    /** persistent field */
    private String descricaoBoletimContrato;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private ContratoEmpresaServico contratoEmpresaServico;

	public CobrancaBoletimContrato() {
		super();
	}

	public CobrancaBoletimContrato(Integer id, Integer anoMesReferencia, 
			Date ultimaAlteracao, ContratoEmpresaServico contratoEmpresaServico) {
		super();
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.ultimaAlteracao = ultimaAlteracao;
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}

	public void setContratoEmpresaServico(
			ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

    public String getDescricaoBoletimContrato() {
		return descricaoBoletimContrato;
	}

	public void setDescricaoBoletimContrato(String descricaoBoletimContrato) {
		this.descricaoBoletimContrato = descricaoBoletimContrato;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

    @Override
	public Filtro retornaFiltro(){
    	FiltroCobrancaBoletimContrato filtroCobrancaBoletimContrato = new FiltroCobrancaBoletimContrato();

    	filtroCobrancaBoletimContrato.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimContrato.ID,
				this.getId()));

		
		return filtroCobrancaBoletimContrato;
	}

}
