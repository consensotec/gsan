/**
 * 
 */
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
package gcom.micromedicao.bean;


/**
 * Cole��o dos dados do Consumo Historico da Medicao Individualizada
 * @author Rafael Santos
 * @since 19/01/2006
 *
 */
public class LigacaoMedicaoIndividualizadaHelper {

	
	private Integer idImovel;
	private String inscricaoImovel;
	private Integer qtdEconomias;
	private Integer leituraAnterior;
	private String dataLeituraAnterior;
	private Integer leituraAtual;
	private String dataLeituraAtual;
	private Integer consumoMedia;
	private Integer consumoMedido;
	private Integer consumoFaturado;
	private Integer rateio;
	private Integer consumoEsgoto;
	private Integer idLeituraAnormalidade;
	private Integer consumoAnormalidade;
	private Integer consumoTipo;
	private Integer idMedicaoHistorico;
	private Integer idConsumoHistorico;
	private String dsAbreviadaAnormalidadeConsumo;
	private String dsAbreviadaTipoConsumo;
	private Integer idTipoMedicao;
	private Integer consumoInformado;
	private Integer consumosVinculados;
	
	public Integer getConsumosVinculados() {
		return consumosVinculados;
	}
	public void setConsumosVinculados(Integer consumosVinculados) {
		this.consumosVinculados = consumosVinculados;
	}
	public Integer getConsumoAnormalidade() {
		return consumoAnormalidade;
	}
	public void setConsumoAnormalidade(Integer consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}
	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}
	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	public Integer getConsumoFaturado() {
		return consumoFaturado;
	}
	public void setConsumoFaturado(Integer consumoFaturado) {
		this.consumoFaturado = consumoFaturado;
	}
	public Integer getConsumoMedia() {
		return consumoMedia;
	}
	public void setConsumoMedia(Integer consumoMedia) {
		this.consumoMedia = consumoMedia;
	}
	public Integer getConsumoMedido() {
		return consumoMedido;
	}
	public void setConsumoMedido(Integer consumoMedido) {
		this.consumoMedido = consumoMedido;
	}
	public Integer getConsumoTipo() {
		return consumoTipo;
	}
	public void setConsumoTipo(Integer consumoTipo) {
		this.consumoTipo = consumoTipo;
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
	public String getDsAbreviadaAnormalidadeConsumo() {
		return dsAbreviadaAnormalidadeConsumo;
	}
	public void setDsAbreviadaAnormalidadeConsumo(
			String dsAbreviadaAnormalidadeConsumo) {
		this.dsAbreviadaAnormalidadeConsumo = dsAbreviadaAnormalidadeConsumo;
	}
	public Integer getIdConsumoHistorico() {
		return idConsumoHistorico;
	}
	public void setIdConsumoHistorico(Integer idConsumoHistorico) {
		this.idConsumoHistorico = idConsumoHistorico;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getIdMedicaoHistorico() {
		return idMedicaoHistorico;
	}
	public void setIdMedicaoHistorico(Integer idMedicaoHistorico) {
		this.idMedicaoHistorico = idMedicaoHistorico;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public Integer getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}
	public void setIdLeituraAnormalidade(Integer idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
	}
	public Integer getLeituraAnterior() {
		return leituraAnterior;
	}
	public void setLeituraAnterior(Integer leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	public Integer getLeituraAtual() {
		return leituraAtual;
	}
	public void setLeituraAtual(Integer leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	public Integer getQtdEconomias() {
		return qtdEconomias;
	}
	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}
	public Integer getRateio() {
		return rateio;
	}
	public void setRateio(Integer rateio) {
		this.rateio = rateio;
	}
	public String getDsAbreviadaTipoConsumo() {
		return dsAbreviadaTipoConsumo;
	}
	public void setDsAbreviadaTipoConsumo(String dsAbreviadaTipoConsumo) {
		this.dsAbreviadaTipoConsumo = dsAbreviadaTipoConsumo;
	}
	public Integer getIdTipoMedicao() {
		return idTipoMedicao;
	}
	public void setIdTipoMedicao(Integer idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}
	public Integer getConsumoInformado() {
		return consumoInformado;
	}
	public void setConsumoInformado(Integer consumoInformado) {
		this.consumoInformado = consumoInformado;
	}
	
	
	
	
}
