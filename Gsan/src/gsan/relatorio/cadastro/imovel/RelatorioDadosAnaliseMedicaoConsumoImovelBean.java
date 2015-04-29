/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.relatorio.cadastro.imovel;

import gsan.relatorio.RelatorioBean;

public class RelatorioDadosAnaliseMedicaoConsumoImovelBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String inscricaoImovel;

	private String situacaoAguaImovel;

	private String situacaoEsgotoImovel;
	
	private String enderecoImovel;

	private String grupoFaturamento;

	private String diaVencimento;

	private String mesAnoFaturamento;

	private String empresaLeiturista;

	private String rota;

	private String sequencialRota;

	private String dataLigacaoDadosLigacaoAgua;

	private String dataCorte;

	private String dataReligacao;

	private String dataSupressao;

	private String dataReestabelecimento;

	private String diametroDadosLigacaoAgua;

	private String materialDadosLigacaoAgua;

	private String perfilLigacaoDadosLigacaoAgua;

	private String consumoMinimoDadosLigacaoAgua;
	
	private String tipoMedicaoDadosHidrometroLigacaoAgua;

	private String hidrometroDadosHidrometroLigacaoAgua;

	private String dataInstalacaoDadosHidrometroLigacaoAgua;

	private String capacidadeDadosHidrometroLigacaoAgua;

	private String tipoHidrometroDadosHidrometroLigacaoAgua;

	private String marcaDadosHidrometroLigacaoAgua;

	private String localInstalacaoDadosHidrometroLigacaoAgua;

	private String diametroDadosHidrometroLigacaoAgua;

	private String protecaoDadosHidrometroLigacaoAgua;

	private String indicadorCavaleteDadosHidrometroLigacaoAgua;

	private String anoFabricacaoDadosHidrometroLigacaoAgua;

	private String tipoRelojoariaDadosHidrometroLigacaoAgua;

	private String usuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua;

	private String numeroLacreInstalacaoDadosHidrometroLigacaoAgua;
	
	private String dataLigacaoDadosLigacaoEsgoto;
	
	private String diametroDadosLigacaoEsgoto;

	private String materialDadosLigacaoEsgoto;

	private String perfilLigacaoDadosLigacaoEsgoto;

	private String consumoMinimoDadosLigacaoEsgoto;

	private String percentualEsgoto;

	private String percentualColeta;

	private String indicadorPoco;
	
	private String condicaoEsgotamento;

	private String sistemaCaixaInspecao;

	private String destinoDejetos;

	private String destinoAguasPluviais;

	private String tipoMedicaoDadosHidrometroPoco;

	private String hidrometroDadosHidrometroPoco;

	private String dataInstalacaoDadosHidrometroPoco;

	private String capacidadeDadosHidrometroPoco;

	private String tipoHidrometroDadosHidrometroPoco;

	private String marcaDadosHidrometroPoco;

	private String localInstalacaoDadosHidrometroPoco;

	private String diametroDadosHidrometroPoco;

	private String protecaoDadosHidrometroPoco;

	private String indicadorCavaleteDadosHidrometroPoco;

	private String anoFabricacaoDadosHidrometroPoco;

	private String tipoRelojoariaDadosHidrometroPoco;

	private String usuarioResponsavelInstalacaoDadosHidrometroPoco;

	private String numeroLacreInstalacaoDadosHidrometroPoco;
	
	private String dataCorteEsgoto;
	
	private String dataReligacaoEsgoto;
	
	private String dataSupressaoEsgoto;
	
	private String dataRestabelecimentoEsgoto;
	
	private String motivoCorteSupressaoEsgoto;	

	public RelatorioDadosAnaliseMedicaoConsumoImovelBean() {
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String situacaoAguaImovel) {
		this.situacaoAguaImovel = situacaoAguaImovel;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
		this.situacaoEsgotoImovel = situacaoEsgotoImovel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getEmpresaLeiturista() {
		return empresaLeiturista;
	}

	public void setEmpresaLeiturista(String empresaLeiturista) {
		this.empresaLeiturista = empresaLeiturista;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getDataLigacaoDadosLigacaoAgua() {
		return dataLigacaoDadosLigacaoAgua;
	}

	public void setDataLigacaoDadosLigacaoAgua(String dataLigacaoDadosLigacaoAgua) {
		this.dataLigacaoDadosLigacaoAgua = dataLigacaoDadosLigacaoAgua;
	}

	public String getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	public String getDataReligacao() {
		return dataReligacao;
	}

	public void setDataReligacao(String dataReligacao) {
		this.dataReligacao = dataReligacao;
	}

	public String getDataSupressao() {
		return dataSupressao;
	}

	public void setDataSupressao(String dataSupressao) {
		this.dataSupressao = dataSupressao;
	}

	public String getDataReestabelecimento() {
		return dataReestabelecimento;
	}

	public void setDataReestabelecimento(String dataReestabelecimento) {
		this.dataReestabelecimento = dataReestabelecimento;
	}

	public String getDiametroDadosLigacaoAgua() {
		return diametroDadosLigacaoAgua;
	}

	public void setDiametroDadosLigacaoAgua(String diametroDadosLigacaoAgua) {
		this.diametroDadosLigacaoAgua = diametroDadosLigacaoAgua;
	}

	public String getMaterialDadosLigacaoAgua() {
		return materialDadosLigacaoAgua;
	}

	public void setMaterialDadosLigacaoAgua(String materialDadosLigacaoAgua) {
		this.materialDadosLigacaoAgua = materialDadosLigacaoAgua;
	}

	public String getPerfilLigacaoDadosLigacaoAgua() {
		return perfilLigacaoDadosLigacaoAgua;
	}

	public void setPerfilLigacaoDadosLigacaoAgua(
			String perfilLigacaoDadosLigacaoAgua) {
		this.perfilLigacaoDadosLigacaoAgua = perfilLigacaoDadosLigacaoAgua;
	}

	public String getConsumoMinimoDadosLigacaoAgua() {
		return consumoMinimoDadosLigacaoAgua;
	}

	public void setConsumoMinimoDadosLigacaoAgua(
			String consumoMinimoDadosLigacaoAgua) {
		this.consumoMinimoDadosLigacaoAgua = consumoMinimoDadosLigacaoAgua;
	}

	public String getTipoMedicaoDadosHidrometroLigacaoAgua() {
		return tipoMedicaoDadosHidrometroLigacaoAgua;
	}

	public void setTipoMedicaoDadosHidrometroLigacaoAgua(
			String tipoMedicaoDadosHidrometroLigacaoAgua) {
		this.tipoMedicaoDadosHidrometroLigacaoAgua = tipoMedicaoDadosHidrometroLigacaoAgua;
	}

	public String getHidrometroDadosHidrometroLigacaoAgua() {
		return hidrometroDadosHidrometroLigacaoAgua;
	}

	public void setHidrometroDadosHidrometroLigacaoAgua(
			String hidrometroDadosHidrometroLigacaoAgua) {
		this.hidrometroDadosHidrometroLigacaoAgua = hidrometroDadosHidrometroLigacaoAgua;
	}

	public String getDataInstalacaoDadosHidrometroLigacaoAgua() {
		return dataInstalacaoDadosHidrometroLigacaoAgua;
	}

	public void setDataInstalacaoDadosHidrometroLigacaoAgua(
			String dataInstalacaoDadosHidrometroLigacaoAgua) {
		this.dataInstalacaoDadosHidrometroLigacaoAgua = dataInstalacaoDadosHidrometroLigacaoAgua;
	}

	public String getCapacidadeDadosHidrometroLigacaoAgua() {
		return capacidadeDadosHidrometroLigacaoAgua;
	}

	public void setCapacidadeDadosHidrometroLigacaoAgua(
			String capacidadeDadosHidrometroLigacaoAgua) {
		this.capacidadeDadosHidrometroLigacaoAgua = capacidadeDadosHidrometroLigacaoAgua;
	}

	public String getTipoHidrometroDadosHidrometroLigacaoAgua() {
		return tipoHidrometroDadosHidrometroLigacaoAgua;
	}

	public void setTipoHidrometroDadosHidrometroLigacaoAgua(
			String tipoHidrometroDadosHidrometroLigacaoAgua) {
		this.tipoHidrometroDadosHidrometroLigacaoAgua = tipoHidrometroDadosHidrometroLigacaoAgua;
	}

	public String getMarcaDadosHidrometroLigacaoAgua() {
		return marcaDadosHidrometroLigacaoAgua;
	}

	public void setMarcaDadosHidrometroLigacaoAgua(
			String marcaDadosHidrometroLigacaoAgua) {
		this.marcaDadosHidrometroLigacaoAgua = marcaDadosHidrometroLigacaoAgua;
	}

	public String getLocalInstalacaoDadosHidrometroLigacaoAgua() {
		return localInstalacaoDadosHidrometroLigacaoAgua;
	}

	public void setLocalInstalacaoDadosHidrometroLigacaoAgua(
			String localInstalacaoDadosHidrometroLigacaoAgua) {
		this.localInstalacaoDadosHidrometroLigacaoAgua = localInstalacaoDadosHidrometroLigacaoAgua;
	}

	public String getDiametroDadosHidrometroLigacaoAgua() {
		return diametroDadosHidrometroLigacaoAgua;
	}

	public void setDiametroDadosHidrometroLigacaoAgua(
			String diametroDadosHidrometroLigacaoAgua) {
		this.diametroDadosHidrometroLigacaoAgua = diametroDadosHidrometroLigacaoAgua;
	}

	public String getProtecaoDadosHidrometroLigacaoAgua() {
		return protecaoDadosHidrometroLigacaoAgua;
	}

	public void setProtecaoDadosHidrometroLigacaoAgua(
			String protecaoDadosHidrometroLigacaoAgua) {
		this.protecaoDadosHidrometroLigacaoAgua = protecaoDadosHidrometroLigacaoAgua;
	}

	public String getIndicadorCavaleteDadosHidrometroLigacaoAgua() {
		return indicadorCavaleteDadosHidrometroLigacaoAgua;
	}

	public void setIndicadorCavaleteDadosHidrometroLigacaoAgua(
			String indicadorCavaleteDadosHidrometroLigacaoAgua) {
		this.indicadorCavaleteDadosHidrometroLigacaoAgua = indicadorCavaleteDadosHidrometroLigacaoAgua;
	}

	public String getAnoFabricacaoDadosHidrometroLigacaoAgua() {
		return anoFabricacaoDadosHidrometroLigacaoAgua;
	}

	public void setAnoFabricacaoDadosHidrometroLigacaoAgua(
			String anoFabricacaoDadosHidrometroLigacaoAgua) {
		this.anoFabricacaoDadosHidrometroLigacaoAgua = anoFabricacaoDadosHidrometroLigacaoAgua;
	}

	public String getTipoRelojoariaDadosHidrometroLigacaoAgua() {
		return tipoRelojoariaDadosHidrometroLigacaoAgua;
	}

	public void setTipoRelojoariaDadosHidrometroLigacaoAgua(
			String tipoRelojoariaDadosHidrometroLigacaoAgua) {
		this.tipoRelojoariaDadosHidrometroLigacaoAgua = tipoRelojoariaDadosHidrometroLigacaoAgua;
	}

	public String getUsuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua() {
		return usuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua;
	}

	public void setUsuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua(
			String usuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua) {
		this.usuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua = usuarioResponsavelInstalacaoDadosHidrometroLigacaoAgua;
	}

	public String getNumeroLacreInstalacaoDadosHidrometroLigacaoAgua() {
		return numeroLacreInstalacaoDadosHidrometroLigacaoAgua;
	}

	public void setNumeroLacreInstalacaoDadosHidrometroLigacaoAgua(
			String numeroLacreInstalacaoDadosHidrometroLigacaoAgua) {
		this.numeroLacreInstalacaoDadosHidrometroLigacaoAgua = numeroLacreInstalacaoDadosHidrometroLigacaoAgua;
	}

	public String getDataLigacaoDadosLigacaoEsgoto() {
		return dataLigacaoDadosLigacaoEsgoto;
	}

	public void setDataLigacaoDadosLigacaoEsgoto(
			String dataLigacaoDadosLigacaoEsgoto) {
		this.dataLigacaoDadosLigacaoEsgoto = dataLigacaoDadosLigacaoEsgoto;
	}

	public String getDiametroDadosLigacaoEsgoto() {
		return diametroDadosLigacaoEsgoto;
	}

	public void setDiametroDadosLigacaoEsgoto(String diametroDadosLigacaoEsgoto) {
		this.diametroDadosLigacaoEsgoto = diametroDadosLigacaoEsgoto;
	}

	public String getMaterialDadosLigacaoEsgoto() {
		return materialDadosLigacaoEsgoto;
	}

	public void setMaterialDadosLigacaoEsgoto(String materialDadosLigacaoEsgoto) {
		this.materialDadosLigacaoEsgoto = materialDadosLigacaoEsgoto;
	}

	public String getPerfilLigacaoDadosLigacaoEsgoto() {
		return perfilLigacaoDadosLigacaoEsgoto;
	}

	public void setPerfilLigacaoDadosLigacaoEsgoto(
			String perfilLigacaoDadosLigacaoEsgoto) {
		this.perfilLigacaoDadosLigacaoEsgoto = perfilLigacaoDadosLigacaoEsgoto;
	}

	public String getConsumoMinimoDadosLigacaoEsgoto() {
		return consumoMinimoDadosLigacaoEsgoto;
	}

	public void setConsumoMinimoDadosLigacaoEsgoto(
			String consumoMinimoDadosLigacaoEsgoto) {
		this.consumoMinimoDadosLigacaoEsgoto = consumoMinimoDadosLigacaoEsgoto;
	}

	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public String getPercentualColeta() {
		return percentualColeta;
	}

	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}

	public String getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(String indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public String getCondicaoEsgotamento() {
		return condicaoEsgotamento;
	}

	public void setCondicaoEsgotamento(String condicaoEsgotamento) {
		this.condicaoEsgotamento = condicaoEsgotamento;
	}

	public String getSistemaCaixaInspecao() {
		return sistemaCaixaInspecao;
	}

	public void setSistemaCaixaInspecao(String sistemaCaixaInspecao) {
		this.sistemaCaixaInspecao = sistemaCaixaInspecao;
	}

	public String getDestinoDejetos() {
		return destinoDejetos;
	}

	public void setDestinoDejetos(String destinoDejetos) {
		this.destinoDejetos = destinoDejetos;
	}

	public String getDestinoAguasPluviais() {
		return destinoAguasPluviais;
	}

	public void setDestinoAguasPluviais(String destinoAguasPluviais) {
		this.destinoAguasPluviais = destinoAguasPluviais;
	}

	public String getTipoMedicaoDadosHidrometroPoco() {
		return tipoMedicaoDadosHidrometroPoco;
	}

	public void setTipoMedicaoDadosHidrometroPoco(
			String tipoMedicaoDadosHidrometroPoco) {
		this.tipoMedicaoDadosHidrometroPoco = tipoMedicaoDadosHidrometroPoco;
	}

	public String getHidrometroDadosHidrometroPoco() {
		return hidrometroDadosHidrometroPoco;
	}

	public void setHidrometroDadosHidrometroPoco(
			String hidrometroDadosHidrometroPoco) {
		this.hidrometroDadosHidrometroPoco = hidrometroDadosHidrometroPoco;
	}

	public String getDataInstalacaoDadosHidrometroPoco() {
		return dataInstalacaoDadosHidrometroPoco;
	}

	public void setDataInstalacaoDadosHidrometroPoco(
			String dataInstalacaoDadosHidrometroPoco) {
		this.dataInstalacaoDadosHidrometroPoco = dataInstalacaoDadosHidrometroPoco;
	}

	public String getCapacidadeDadosHidrometroPoco() {
		return capacidadeDadosHidrometroPoco;
	}

	public void setCapacidadeDadosHidrometroPoco(
			String capacidadeDadosHidrometroPoco) {
		this.capacidadeDadosHidrometroPoco = capacidadeDadosHidrometroPoco;
	}

	public String getTipoHidrometroDadosHidrometroPoco() {
		return tipoHidrometroDadosHidrometroPoco;
	}

	public void setTipoHidrometroDadosHidrometroPoco(
			String tipoHidrometroDadosHidrometroPoco) {
		this.tipoHidrometroDadosHidrometroPoco = tipoHidrometroDadosHidrometroPoco;
	}

	public String getMarcaDadosHidrometroPoco() {
		return marcaDadosHidrometroPoco;
	}

	public void setMarcaDadosHidrometroPoco(String marcaDadosHidrometroPoco) {
		this.marcaDadosHidrometroPoco = marcaDadosHidrometroPoco;
	}

	public String getLocalInstalacaoDadosHidrometroPoco() {
		return localInstalacaoDadosHidrometroPoco;
	}

	public void setLocalInstalacaoDadosHidrometroPoco(
			String localInstalacaoDadosHidrometroPoco) {
		this.localInstalacaoDadosHidrometroPoco = localInstalacaoDadosHidrometroPoco;
	}

	public String getDiametroDadosHidrometroPoco() {
		return diametroDadosHidrometroPoco;
	}

	public void setDiametroDadosHidrometroPoco(String diametroDadosHidrometroPoco) {
		this.diametroDadosHidrometroPoco = diametroDadosHidrometroPoco;
	}

	public String getProtecaoDadosHidrometroPoco() {
		return protecaoDadosHidrometroPoco;
	}

	public void setProtecaoDadosHidrometroPoco(String protecaoDadosHidrometroPoco) {
		this.protecaoDadosHidrometroPoco = protecaoDadosHidrometroPoco;
	}

	public String getIndicadorCavaleteDadosHidrometroPoco() {
		return indicadorCavaleteDadosHidrometroPoco;
	}

	public void setIndicadorCavaleteDadosHidrometroPoco(
			String indicadorCavaleteDadosHidrometroPoco) {
		this.indicadorCavaleteDadosHidrometroPoco = indicadorCavaleteDadosHidrometroPoco;
	}

	public String getAnoFabricacaoDadosHidrometroPoco() {
		return anoFabricacaoDadosHidrometroPoco;
	}

	public void setAnoFabricacaoDadosHidrometroPoco(
			String anoFabricacaoDadosHidrometroPoco) {
		this.anoFabricacaoDadosHidrometroPoco = anoFabricacaoDadosHidrometroPoco;
	}

	public String getTipoRelojoariaDadosHidrometroPoco() {
		return tipoRelojoariaDadosHidrometroPoco;
	}

	public void setTipoRelojoariaDadosHidrometroPoco(
			String tipoRelojoariaDadosHidrometroPoco) {
		this.tipoRelojoariaDadosHidrometroPoco = tipoRelojoariaDadosHidrometroPoco;
	}

	public String getUsuarioResponsavelInstalacaoDadosHidrometroPoco() {
		return usuarioResponsavelInstalacaoDadosHidrometroPoco;
	}

	public void setUsuarioResponsavelInstalacaoDadosHidrometroPoco(
			String usuarioResponsavelInstalacaoDadosHidrometroPoco) {
		this.usuarioResponsavelInstalacaoDadosHidrometroPoco = usuarioResponsavelInstalacaoDadosHidrometroPoco;
	}

	public String getNumeroLacreInstalacaoDadosHidrometroPoco() {
		return numeroLacreInstalacaoDadosHidrometroPoco;
	}

	public void setNumeroLacreInstalacaoDadosHidrometroPoco(
			String numeroLacreInstalacaoDadosHidrometroPoco) {
		this.numeroLacreInstalacaoDadosHidrometroPoco = numeroLacreInstalacaoDadosHidrometroPoco;
	}

	public String getDataCorteEsgoto() {
		return dataCorteEsgoto;
	}

	public void setDataCorteEsgoto(String dataCorteEsgoto) {
		this.dataCorteEsgoto = dataCorteEsgoto;
	}

	public String getDataReligacaoEsgoto() {
		return dataReligacaoEsgoto;
	}

	public void setDataReligacaoEsgoto(String dataReligacaoEsgoto) {
		this.dataReligacaoEsgoto = dataReligacaoEsgoto;
	}

	public String getDataSupressaoEsgoto() {
		return dataSupressaoEsgoto;
	}

	public void setDataSupressaoEsgoto(String dataSupressaoEsgoto) {
		this.dataSupressaoEsgoto = dataSupressaoEsgoto;
	}

	public String getDataRestabelecimentoEsgoto() {
		return dataRestabelecimentoEsgoto;
	}

	public void setDataRestabelecimentoEsgoto(String dataRestabelecimentoEsgoto) {
		this.dataRestabelecimentoEsgoto = dataRestabelecimentoEsgoto;
	}

	public String getMotivoCorteSupressaoEsgoto() {
		return motivoCorteSupressaoEsgoto;
	}

	public void setMotivoCorteSupressaoEsgoto(String motivoCorteSupressaoEsgoto) {
		this.motivoCorteSupressaoEsgoto = motivoCorteSupressaoEsgoto;
	}
}