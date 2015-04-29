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
package gsan.cadastro.imovel.bean;

import gsan.util.Util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Esta classe tem a finalidade para facilitar a visualiza��o dos dados na tela 
 * [UC0472] Consultar Imovel
 * 
 * @author Rafael Santos
 * @date 25/09/2006
 */
public class ConsultarImovelRegistroAtendimentoHelper {
	
	private String idRegistroAtendimento;

	private String tipoSolicitacao;
	
	private String especificacao;
	
	private String dataAtendimento;
	
	private String situacao;
	
	private String numeroProtocolo;
	
	private String dataEncerramento;
	
	private String motivoEncerramento;
	
	/**
	 * @return Retorna o campo dataAtendimento.
	 */
	public String getDataAtendimento() {
		return dataAtendimento;
	}

	/**
	 * @param dataAtendimento O dataAtendimento a ser setado.
	 */
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	/**
	 * @return Retorna o campo especificacao.
	 */
	public String getEspecificacao() {
		return especificacao;
	}

	/**
	 * @param especificacao O especificacao a ser setado.
	 */
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	/**
	 * @return Retorna o campo idRegistroAtendimento.
	 */
	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	/**
	 * @param idRegistroAtendimento O idRegistroAtendimento a ser setado.
	 */
	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo tipoSolicitacao.
	 */
	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	/**
	 * @param tipoSolicitacao O tipoSolicitacao a ser setado.
	 */
	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	
	public String getDataEncerramentoFormatada(){
		if(dataEncerramento == null){
			return "";
		}
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(Util.converteStringParaDate(dataEncerramento));
		String mes = data.get(Calendar.MONTH)+"";
		if(mes.length() == 1){
			mes = "0" + mes;
		}
		String dia = data.get(Calendar.DAY_OF_MONTH)+"";
		if(dia.length() == 1){
			dia = "0" + dia;
		}
		return data.get(Calendar.YEAR) + "-" + mes + "-" + dia;
	}
	
	public String getDataAtendimentoFormatada(){
		if(dataAtendimento == null){
			return "";
		}
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(Util.converteStringParaDate(dataAtendimento));
		String mes = data.get(Calendar.MONTH)+"";
		if(mes.length() == 1){
			mes = "0" + mes;
		}
		String dia = data.get(Calendar.DAY_OF_MONTH)+"";
		if(dia.length() == 1){
			dia = "0" + dia;
		}
		return data.get(Calendar.YEAR) + "-" + mes + "-" + dia;
	}

	public Integer getAnoMesDia() {
		return Util.formatarDiaMesAnoComBarraParaAnoMesDia(this.getDataAtendimento());
	}

}
