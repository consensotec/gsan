package gsan.relatorio.micromedicao;

import gsan.relatorio.RelatorioBean;
/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company:
 * 
 * @author Diego Maciel
 * @date 08/03/2012
 * @version 1.0
 */




public class RelatorioManterAnormalidadesLeituraBean implements RelatorioBean {
	
	private String 	codigo;
	
	private String descricaoAnormalidade ;
	
	private String indicadorRelativoHidrometro;
	
	private String indicadorImovelSemHidrometro;
	
	private String usoRestritoSistema;
	
	private String perdaTarifaSocial;
	
	
	
	public  RelatorioManterAnormalidadesLeituraBean(String codigo,String descricaoAnormalidade,String indicadorRelativoHidrometro ,
			String indicadorImovelSemHidrometro,String usoRestritoSistema,String perdaTarifaSocial){
		
		this.codigo = codigo;
		this.descricaoAnormalidade = descricaoAnormalidade;
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
		this.usoRestritoSistema = usoRestritoSistema;
		this.perdaTarifaSocial = perdaTarifaSocial;	
		
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getDescricaoAnormalidade() {
		return descricaoAnormalidade;
	}



	public void setDescricaoAnormalidade(String descricaoAnormalidade) {
		this.descricaoAnormalidade = descricaoAnormalidade;
	}



	public String getIndicadorRelativoHidrometro() {
		return indicadorRelativoHidrometro;
	}



	public void setIndicadorRelativoHidrometro(String indicadorRelativoHidrometro) {
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
	}



	public String getIndicadorImovelSemHidrometro() {
		return indicadorImovelSemHidrometro;
	}



	public void setIndicadorImovelSemHidrometro(String indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}



	public String getUsoRestritoSistema() {
		return usoRestritoSistema;
	}



	public void setUsoRestritoSistema(String usoRestritoSistema) {
		this.usoRestritoSistema = usoRestritoSistema;
	}



	public String getPerdaTarifaSocial() {
		return perdaTarifaSocial;
	}



	public void setPerdaTarifaSocial(String perdaTarifaSocial) {
		this.perdaTarifaSocial = perdaTarifaSocial;
	}
	
	
		
	

}
