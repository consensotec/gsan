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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Arthur Carvalho
 * @date 13/02/2007
 */
public class RelatorioHistoricoMedicaoPocoBean implements RelatorioBean {
	
	private String mesAno;
	private String dataLeituraInformada;
	private String leituraInformada;
	private String dataLeituraFaturada;
	private String leituraFaturada;
	private String consumo;
	private String media;
	private String anormalidadeConsumo;	
	private String anormalidadeLeitura;	
	private String sitLeituraAtual;
	/**
	 * @return Retorna o campo anormalidadeConsumo.
	 */
	public String getAnormalidadeConsumo() {
		return anormalidadeConsumo;
	}
	/**
	 * @param anormalidadeConsumo O anormalidadeConsumo a ser setado.
	 */
	public void setAnormalidadeConsumo(String anormalidadeConsumo) {
		this.anormalidadeConsumo = anormalidadeConsumo;
	}
	/**
	 * @return Retorna o campo anormalidadeLeitura.
	 */
	public String getAnormalidadeLeitura() {
		return anormalidadeLeitura;
	}
	/**
	 * @param anormalidadeLeitura O anormalidadeLeitura a ser setado.
	 */
	public void setAnormalidadeLeitura(String anormalidadeLeitura) {
		this.anormalidadeLeitura = anormalidadeLeitura;
	}
	/**
	 * @return Retorna o campo consumo.
	 */
	public String getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return Retorna o campo dataLeituraFaturada.
	 */
	public String getDataLeituraFaturada() {
		return dataLeituraFaturada;
	}
	/**
	 * @param dataLeituraFaturada O dataLeituraFaturada a ser setado.
	 */
	public void setDataLeituraFaturada(String dataLeituraFaturada) {
		this.dataLeituraFaturada = dataLeituraFaturada;
	}
	/**
	 * @return Retorna o campo dataLeituraInformada.
	 */
	public String getDataLeituraInformada() {
		return dataLeituraInformada;
	}
	/**
	 * @param dataLeituraInformada O dataLeituraInformada a ser setado.
	 */
	public void setDataLeituraInformada(String dataLeituraInformada) {
		this.dataLeituraInformada = dataLeituraInformada;
	}
	/**
	 * @return Retorna o campo leituraFaturada.
	 */
	public String getLeituraFaturada() {
		return leituraFaturada;
	}
	/**
	 * @param leituraFaturada O leituraFaturada a ser setado.
	 */
	public void setLeituraFaturada(String leituraFaturada) {
		this.leituraFaturada = leituraFaturada;
	}
	/**
	 * @return Retorna o campo leituraInformada.
	 */
	public String getLeituraInformada() {
		return leituraInformada;
	}
	/**
	 * @param leituraInformada O leituraInformada a ser setado.
	 */
	public void setLeituraInformada(String leituraInformada) {
		this.leituraInformada = leituraInformada;
	}
	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}
	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	/**
	 * @return Retorna o campo sitLeituraAtual.
	 */
	public String getSitLeituraAtual() {
		return sitLeituraAtual;
	}
	/**
	 * @param sitLeituraAtual O sitLeituraAtual a ser setado.
	 */
	public void setSitLeituraAtual(String sitLeituraAtual) {
		this.sitLeituraAtual = sitLeituraAtual;
	}
	public RelatorioHistoricoMedicaoPocoBean(String mesAno, String dataLeituraInformada, String leituraInformada,
			String dataLeituraFaturada, String leituraFaturada, String consumo, String media, String anormalidadeConsumo, 
			String anormalidadeLeitura, String sitLeituraAtual) {
		this.mesAno = mesAno;
		this.dataLeituraInformada = dataLeituraInformada;
		this.leituraInformada = leituraInformada;
		this.dataLeituraFaturada = dataLeituraFaturada;
		this.leituraFaturada = leituraFaturada;
		this.consumo = consumo;
		this.media = media;
		this.anormalidadeConsumo = anormalidadeConsumo;
		this.anormalidadeLeitura = anormalidadeLeitura;
		this.sitLeituraAtual = sitLeituraAtual;
	}
	/**
	 * @return Retorna o campo media.
	 */
	public String getMedia() {
		return media;
	}
	/**
	 * @param media O media a ser setado.
	 */
	public void setMedia(String media) {
		this.media = media;
	}




}
