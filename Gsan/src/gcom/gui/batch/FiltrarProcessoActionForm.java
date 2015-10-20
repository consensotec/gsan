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
package gcom.gui.batch;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form que manipula os dados do caso de uso Filtrar Processo
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class FiltrarProcessoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idProcesso;

	private String descricaoProcesso;

	private String idSituacaoProcesso;

	private String dataAgendamentoInicial;

	private String horaAgendamentoInicial;

	private String dataAgendamentoFinal;

	private String horaAgendamentoFinal;

	private String dataPeriodoInicioInicial;

	private String horaPeriodoInicioInicial;

	private String dataPeriodoInicioFinal;

	private String horaPeriodoInicioFinal;

	private String dataConclusaoInicial;

	private String horaConclusaoInicial;

	private String dataConclusaoFinal;

	private String horaConclusaoFinal;

	private String dataComandoInicial;

	private String horaComandoInicial;

	private String dataComandoFinal;

	private String horaComandoFinal;
	
	private String usuarioId;
	
	private String usuarioDescricao;

	public void resetFormCustom() {
		this.idProcesso = null;
		this.descricaoProcesso = null;
		this.idSituacaoProcesso = null;
		this.dataAgendamentoInicial = null;
		this.horaAgendamentoInicial = null;
		this.dataAgendamentoFinal = null;
		this.horaAgendamentoFinal = null;
		this.dataPeriodoInicioInicial = null;
		this.horaPeriodoInicioInicial = null;
		this.dataPeriodoInicioFinal = null;
		this.horaPeriodoInicioFinal = null;
		this.dataConclusaoInicial = null;
		this.horaConclusaoInicial = null;
		this.dataConclusaoFinal = null;
		this.horaConclusaoFinal = null;
		this.dataComandoInicial = null;
		this.horaComandoInicial = null;
		this.dataComandoFinal = null;
		this.horaComandoFinal = null;

	}

	public String getDataAgendamentoFinal() {
		return dataAgendamentoFinal;
	}

	public void setDataAgendamentoFinal(String dataAgendamentoFinal) {
		this.dataAgendamentoFinal = dataAgendamentoFinal;
	}

	public String getDataAgendamentoInicial() {
		return dataAgendamentoInicial;
	}

	public void setDataAgendamentoInicial(String dataAgendamentoInicial) {
		this.dataAgendamentoInicial = dataAgendamentoInicial;
	}

	public String getDataComandoFinal() {
		return dataComandoFinal;
	}

	public void setDataComandoFinal(String dataComandoFinal) {
		this.dataComandoFinal = dataComandoFinal;
	}

	public String getDataComandoInicial() {
		return dataComandoInicial;
	}

	public void setDataComandoInicial(String dataComandoInicial) {
		this.dataComandoInicial = dataComandoInicial;
	}

	public String getDataConclusaoFinal() {
		return dataConclusaoFinal;
	}

	public void setDataConclusaoFinal(String dataConclusaoFinal) {
		this.dataConclusaoFinal = dataConclusaoFinal;
	}

	public String getDataConclusaoInicial() {
		return dataConclusaoInicial;
	}

	public void setDataConclusaoInicial(String dataConclusaoInicial) {
		this.dataConclusaoInicial = dataConclusaoInicial;
	}

	public String getDataPeriodoInicioFinal() {
		return dataPeriodoInicioFinal;
	}

	public void setDataPeriodoInicioFinal(String dataPeriodoInicioFinal) {
		this.dataPeriodoInicioFinal = dataPeriodoInicioFinal;
	}

	public String getDataPeriodoInicioInicial() {
		return dataPeriodoInicioInicial;
	}

	public void setDataPeriodoInicioInicial(String dataPeriodoInicioInicial) {
		this.dataPeriodoInicioInicial = dataPeriodoInicioInicial;
	}

	public String getDescricaoProcesso() {
		return descricaoProcesso;
	}

	public void setDescricaoProcesso(String descricaoProcesso) {
		this.descricaoProcesso = descricaoProcesso;
	}

	public String getHoraAgendamentoFinal() {
		return horaAgendamentoFinal;
	}

	public void setHoraAgendamentoFinal(String horaAgendamentoFinal) {
		this.horaAgendamentoFinal = horaAgendamentoFinal;
	}

	public String getHoraAgendamentoInicial() {
		return horaAgendamentoInicial;
	}

	public void setHoraAgendamentoInicial(String horaAgendamentoInicial) {
		this.horaAgendamentoInicial = horaAgendamentoInicial;
	}

	public String getHoraComandoFinal() {
		return horaComandoFinal;
	}

	public void setHoraComandoFinal(String horaComandoFinal) {
		this.horaComandoFinal = horaComandoFinal;
	}

	public String getHoraComandoInicial() {
		return horaComandoInicial;
	}

	public void setHoraComandoInicial(String horaComandoInicial) {
		this.horaComandoInicial = horaComandoInicial;
	}

	public String getHoraConclusaoFinal() {
		return horaConclusaoFinal;
	}

	public void setHoraConclusaoFinal(String horaConclusaoFinal) {
		this.horaConclusaoFinal = horaConclusaoFinal;
	}

	public String getHoraConclusaoInicial() {
		return horaConclusaoInicial;
	}

	public void setHoraConclusaoInicial(String horaConclusaoInicial) {
		this.horaConclusaoInicial = horaConclusaoInicial;
	}

	public String getHoraPeriodoInicioFinal() {
		return horaPeriodoInicioFinal;
	}

	public void setHoraPeriodoInicioFinal(String horaPeriodoInicioFinal) {
		this.horaPeriodoInicioFinal = horaPeriodoInicioFinal;
	}

	public String getHoraPeriodoInicioInicial() {
		return horaPeriodoInicioInicial;
	}

	public void setHoraPeriodoInicioInicial(String horaPeriodoInicioInicial) {
		this.horaPeriodoInicioInicial = horaPeriodoInicioInicial;
	}

	public String getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getIdSituacaoProcesso() {
		return idSituacaoProcesso;
	}

	public void setIdSituacaoProcesso(String idSituacaoProcesso) {
		this.idSituacaoProcesso = idSituacaoProcesso;
	}

	public String getUsuarioDescricao() {
		return usuarioDescricao;
	}

	public void setUsuarioDescricao(String usuarioDescricao) {
		this.usuarioDescricao = usuarioDescricao;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

}
