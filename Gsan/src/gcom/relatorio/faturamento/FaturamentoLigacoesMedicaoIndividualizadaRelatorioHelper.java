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
package gcom.relatorio.faturamento;

public class FaturamentoLigacoesMedicaoIndividualizadaRelatorioHelper {
	
	private String idLocalidade;
	private String nomeLocalidade; 
	private String matriculaImovel;
	private String nomeConsumidor;
	private String qtdeEconomias;
	private String leituraAnterior;
	private String dataLeituraAnterior;
	private String leituraAtual;
	private String dataLeituraAtual;
	private String media;
	private String consumoImoveisVinculados;
	private String consumoFaturado;
	private String rateio;
	private String consumoEsgoto;
	private String anormalidade;
	private String anormalidadeConsumo;
	private String tipoConsumo;
	private String indicadorPoco;

	private String indicadorQuebraImovelCondominio;
	private String totalConsumidoresRateioMacromedidor;
	private String numeroEconomiasRateio;
	
	private String codigoSetorComercial;
	private String numeroQuadra;
	private String numeroLote;
	private String numeroSubLote;
	
	
	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}
	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public String getNumeroSubLote() {
		return numeroSubLote;
	}
	public void setNumeroSubLote(String numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}
	public String getAnormalidade() {
		return anormalidade;
	}
	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}
	public String getConsumoEsgoto() {
		return consumoEsgoto;
	}
	public void setConsumoEsgoto(String consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	public String getConsumoFaturado() {
		return consumoFaturado;
	}
	public void setConsumoFaturado(String consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}
	public String getConsumoImoveisVinculados() {
		return consumoImoveisVinculados;
	}
	public void setConsumoImoveisVinculados(String consumoImoveisVinculados) {
		this.consumoImoveisVinculados = consumoImoveisVinculados;
	}
	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}
	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}
	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}
	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIndicadorPoco() {
		return indicadorPoco;
	}
	public void setIndicadorPoco(String indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}
	public String getIndicadorQuebraImovelCondominio() {
		return indicadorQuebraImovelCondominio;
	}
	public void setIndicadorQuebraImovelCondominio(
			String indicadorQuebraImovelCondominio) {
		this.indicadorQuebraImovelCondominio = indicadorQuebraImovelCondominio;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}
	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	public String getLeituraAtual() {
		return leituraAtual;
	}
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getNomeConsumidor() {
		return nomeConsumidor;
	}
	public void setNomeConsumidor(String nomeConsumidor) {
		this.nomeConsumidor = nomeConsumidor;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNumeroEconomiasRateio() {
		return numeroEconomiasRateio;
	}
	public void setNumeroEconomiasRateio(String numeroEconomiasRateio) {
		this.numeroEconomiasRateio = numeroEconomiasRateio;
	}
	public String getQtdeEconomias() {
		return qtdeEconomias;
	}
	public void setQtdeEconomias(String qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}
	public String getRateio() {
		return rateio;
	}
	public void setRateio(String rateio) {
		this.rateio = rateio;
	}
	public String getTipoConsumo() {
		return tipoConsumo;
	}
	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}
	public String getTotalConsumidoresRateioMacromedidor() {
		return totalConsumidoresRateioMacromedidor;
	}
	public void setTotalConsumidoresRateioMacromedidor(
			String totalConsumidoresRateioMacromedidor) {
		this.totalConsumidoresRateioMacromedidor = totalConsumidoresRateioMacromedidor;
	}
	
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = this.idLocalidade;
		setorComercial = this.codigoSetorComercial;
		quadra = this.numeroQuadra;
		lote = this.numeroLote;
		subLote = this.numeroSubLote;

		if (this.idLocalidade != null ){
			if (this.idLocalidade.length() < 3 && this.idLocalidade.length() > 1) {
				localidade = zeroUm + this.idLocalidade;
			} else if (String.valueOf(this.idLocalidade)
					.length() < 3) {
				localidade = zeroDois + this.idLocalidade;
			}	
		}

		if (this.codigoSetorComercial != null ){
			if (this.codigoSetorComercial.length() < 3 && this.codigoSetorComercial.length() > 1) {
				setorComercial = zeroUm + this.codigoSetorComercial;
			} else if (this.codigoSetorComercial.length() < 3) {
				setorComercial = zeroDois + this.codigoSetorComercial;
			}
		}
		
		if (this.numeroQuadra != null ){
			if (this.numeroQuadra.length() < 3 && this.numeroQuadra.length() > 1) {
				quadra = zeroUm + this.numeroQuadra;
			} else if (this.numeroQuadra.length() < 3) {
				quadra = zeroDois + this.numeroQuadra;
			}
		}
		
		if (this.numeroLote != null ){
			if (this.numeroLote.length() < 4 && this.numeroLote.length() > 2) {
				lote = zeroUm + this.numeroLote;
			} else if (this.numeroLote.length() < 3	&& this.numeroLote.length() > 1) {
				lote = zeroDois + this.numeroLote;
			} else if (this.numeroLote.length() < 2) {
				lote = zeroTres + this.numeroLote;
			}
		}
		
		if (this.numeroSubLote != null ){
			if (this.numeroSubLote.length() < 3 && this.numeroSubLote.length() > 1) {
				subLote = zeroUm + this.numeroSubLote;
			} else if (this.numeroSubLote.length() < 3) {
				subLote = zeroDois + this.numeroSubLote;
			}
		}
		
		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}

	public String getIndicadorPocoExtenso() {
		String poco = "";
		
		if (this.indicadorPoco != null && this.indicadorPoco.equals("1")){
			poco = "SIM";
		}else if (this.indicadorPoco != null && this.indicadorPoco.equals("2")){
			poco = "N�O";
		}	
		return poco;
	}
}
