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
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDadosCadastraisImovelBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String inscricaoImovel;

	private String situacaoAguaImovel;

	private String situacaoEsgotoImovel;

	private String perfilImovel;

	private String tipoDespejo;

	private String areaConstruida;

	private String testadaLote;

	private String volumeInferiorReservatorio;

	private String volumeSuperiorReservatorio;

	private String volumePiscina;

	private String fonteAbastecimento;

	private String tipoPoco;

	private String distritoAbastecimento;

	private String divisaoEsgoto;

	private String pavimentoCalcada;

	private String pavimentoRua;

	private String numeroIptu;

	private String numeroCompanhiaEletrica;

	private String coordenadaUTMX;

	private String coordenadaUTMY;

	private String ocorrenciaCadastro;

	private String eloAnormalidade;

	private String indicadorImovelCondominio;

	private String matriculaCondominio;

	private String matriculaImovelPrincipal;

	private String numeroPontosUtilizacao;

	private String numeroMoradores;

	private String indicadorJardim;

	private String tipoHabitacao;

	private String tipoPropriedade;

	private String tipoConstrucao;

	private String tipoCobertura;

	private String enderecoImovel;

	private JRBeanCollectionDataSource colecaoClienteImovel;

	private JRBeanCollectionDataSource colecaoImovelSubcategoriaHelper;

	public RelatorioDadosCadastraisImovelBean() {
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String id) {
		this.inscricaoImovel = id;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String codigo) {
		this.matriculaImovel = codigo;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String codAgencia) {
		this.situacaoAguaImovel = codAgencia;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String nomeBanco) {
		this.situacaoEsgotoImovel = nomeBanco;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(
			String enderecoCorrespondenciaCliente) {
		this.enderecoImovel = enderecoCorrespondenciaCliente;
	}

	public JRBeanCollectionDataSource getColecaoClienteImovel() {
		return colecaoClienteImovel;
	}

	public void setColecaoClienteImovel(
			JRBeanCollectionDataSource colecaoClienteImovelHelper) {
		this.colecaoClienteImovel = colecaoClienteImovelHelper;
	}

	public JRBeanCollectionDataSource getColecaoImovelSubcategoriaHelper() {
		return colecaoImovelSubcategoriaHelper;
	}

	public void setColecaoImovelSubcategoriaHelper(
			JRBeanCollectionDataSource coelcaoClienteImovelEconomia) {
		
		this.colecaoImovelSubcategoriaHelper = coelcaoClienteImovelEconomia;
	}

	public String getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(String perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getTipoDespejo() {
		return tipoDespejo;
	}

	public void setTipoDespejo(String tipoDespejo) {
		this.tipoDespejo = tipoDespejo;
	}

	public String getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(String areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public String getTestadaLote() {
		return testadaLote;
	}

	public void setTestadaLote(String testadaLote) {
		this.testadaLote = testadaLote;
	}

	public String getVolumeInferiorReservatorio() {
		return volumeInferiorReservatorio;
	}

	public void setVolumeInferiorReservatorio(String volumeInferiorReservatorio) {
		this.volumeInferiorReservatorio = volumeInferiorReservatorio;
	}

	public String getVolumeSuperiorReservatorio() {
		return volumeSuperiorReservatorio;
	}

	public void setVolumeSuperiorReservatorio(String volumeSuperiorReservatorio) {
		this.volumeSuperiorReservatorio = volumeSuperiorReservatorio;
	}

	public String getVolumePiscina() {
		return volumePiscina;
	}

	public void setVolumePiscina(String volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	public String getFonteAbastecimento() {
		return fonteAbastecimento;
	}

	public void setFonteAbastecimento(String fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	public String getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(String tipoPoco) {
		this.tipoPoco = tipoPoco;
	}

	public String getDistritoAbastecimento() {
		return distritoAbastecimento;
	}

	public void setDistritoAbastecimento(String distritoAbastecimento) {
		this.distritoAbastecimento = distritoAbastecimento;
	}

	public String getDivisaoEsgoto() {
		return divisaoEsgoto;
	}

	public void setDivisaoEsgoto(String divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}

	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public String getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public String getNumeroIptu() {
		return numeroIptu;
	}

	public void setNumeroIptu(String numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public String getNumeroCompanhiaEletrica() {
		return numeroCompanhiaEletrica;
	}

	public void setNumeroCompanhiaEletrica(String numeroCompanhiaEletrica) {
		this.numeroCompanhiaEletrica = numeroCompanhiaEletrica;
	}

	public String getCoordenadaUTMX() {
		return coordenadaUTMX;
	}

	public void setCoordenadaUTMX(String coordenadaUTMX) {
		this.coordenadaUTMX = coordenadaUTMX;
	}

	public String getCoordenadaUTMY() {
		return coordenadaUTMY;
	}

	public void setCoordenadaUTMY(String coordenadaUTMY) {
		this.coordenadaUTMY = coordenadaUTMY;
	}

	public String getOcorrenciaCadastro() {
		return ocorrenciaCadastro;
	}

	public void setOcorrenciaCadastro(String ocorrenciaCadastro) {
		this.ocorrenciaCadastro = ocorrenciaCadastro;
	}

	public String getEloAnormalidade() {
		return eloAnormalidade;
	}

	public void setEloAnormalidade(String eloAnormalidade) {
		this.eloAnormalidade = eloAnormalidade;
	}

	public String getIndicadorImovelCondominio() {
		return indicadorImovelCondominio;
	}

	public void setIndicadorImovelCondominio(String indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	public String getMatriculaCondominio() {
		return matriculaCondominio;
	}

	public void setMatriculaCondominio(String matriculaCondominio) {
		this.matriculaCondominio = matriculaCondominio;
	}

	public String getMatriculaImovelPrincipal() {
		return matriculaImovelPrincipal;
	}

	public void setMatriculaImovelPrincipal(String matriculaImovelPrincipal) {
		this.matriculaImovelPrincipal = matriculaImovelPrincipal;
	}

	public String getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	public void setNumeroPontosUtilizacao(String numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	public String getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public String getIndicadorJardim() {
		return indicadorJardim;
	}

	public void setIndicadorJardim(String indicadorJardim) {
		this.indicadorJardim = indicadorJardim;
	}

	public String getTipoHabitacao() {
		return tipoHabitacao;
	}

	public void setTipoHabitacao(String tipoHabitacao) {
		this.tipoHabitacao = tipoHabitacao;
	}

	public String getTipoPropriedade() {
		return tipoPropriedade;
	}

	public void setTipoPropriedade(String tipoPropriedade) {
		this.tipoPropriedade = tipoPropriedade;
	}

	public String getTipoConstrucao() {
		return tipoConstrucao;
	}

	public void setTipoConstrucao(String tipoConstrucao) {
		this.tipoConstrucao = tipoConstrucao;
	}

	public String getTipoCobertura() {
		return tipoCobertura;
	}

	public void setTipoCobertura(String tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}	
}