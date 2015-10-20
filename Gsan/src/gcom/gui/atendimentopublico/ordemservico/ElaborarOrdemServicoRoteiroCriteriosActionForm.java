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
package gcom.gui.atendimentopublico.ordemservico;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0456] Elaborar Roteiro de Programa��o de Ordens de Servi�o
 * 
 * @author Rafael Pinto
 *
 * @date 04/09/2006
 */
public class ElaborarOrdemServicoRoteiroCriteriosActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String origemServicos;
	private String criterioSelecao;
	
	private Integer[] tipoServico;
	private Integer[] tipoServicoSelecionados;
	
	private Integer[] equipe;
	private Integer[] equipesSelecionadas;

	private String servicoDiagnosticado;
	private String servicoAcompanhamento;
	
	private String diaAtrasoInicial;
	private String diaAtrasoFinal;
	
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	
	private String periodoGeracaoInicial;
	private String periodoGeracaoFinal;
	
	private String periodoClienteInicial;
	private String periodoClienteFinal;
	
	private String periodoAgenciaInicial;
	private String periodoAgenciaFinal;
	
	private String dataRoteiro;
	private String unidadeLotacao;

	//Usada na tela de programar roteiro
	private Integer[] osSelecionada;
	
	private int selecionadas;
	private int programadas;
	
	// Usada na tela de alerta
	private int idOrdemServico;
	private String descricaoOrdemServico;
	private String alertaEquipeLogradouro;
	private String alertaEquipeServico;
	

	public int getProgramadas() {
		return programadas;
	}

	public void setProgramadas(int programadas) {
		this.programadas = programadas;
	}

	public int getSelecionadas() {
		return selecionadas;
	}

	public void setSelecionadas(int selecionadas) {
		this.selecionadas = selecionadas;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		
		criterioSelecao = null;
		
		servicoDiagnosticado = null;
		servicoAcompanhamento = null;
		
		diaAtrasoInicial= null;
		diaAtrasoFinal= null;
		
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;

		periodoGeracaoInicial = null;
		periodoGeracaoFinal = null;

		periodoClienteInicial = null;
		periodoClienteFinal = null;
		
		periodoAgenciaInicial = null;
		periodoAgenciaFinal = null;

	}

	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}


	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}


	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}


	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	public String getPeriodoGeracaoFinal() {
		return periodoGeracaoFinal;
	}

	public void setPeriodoGeracaoFinal(String periodoGeracaoFinal) {
		this.periodoGeracaoFinal = periodoGeracaoFinal;
	}

	public String getPeriodoGeracaoInicial() {
		return periodoGeracaoInicial;
	}

	public void setPeriodoGeracaoInicial(String periodoGeracaoInicial) {
		this.periodoGeracaoInicial = periodoGeracaoInicial;
	}


	public Integer[] getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(Integer[] tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Integer[] getTipoServicoSelecionados() {
		return tipoServicoSelecionados;
	}

	public void setTipoServicoSelecionados(Integer[] tipoServicoSelecionados) {
		this.tipoServicoSelecionados = tipoServicoSelecionados;
	}

	public String getCriterioSelecao() {
		return criterioSelecao;
	}

	public void setCriterioSelecao(String criterioSelecao) {
		this.criterioSelecao = criterioSelecao;
	}

	public String getDiaAtrasoFinal() {
		return diaAtrasoFinal;
	}

	public void setDiaAtrasoFinal(String diaAtrasoFinal) {
		this.diaAtrasoFinal = diaAtrasoFinal;
	}

	public String getDiaAtrasoInicial() {
		return diaAtrasoInicial;
	}

	public void setDiaAtrasoInicial(String diaAtrasoInicial) {
		this.diaAtrasoInicial = diaAtrasoInicial;
	}

	public String getPeriodoAgenciaFinal() {
		return periodoAgenciaFinal;
	}

	public void setPeriodoAgenciaFinal(String periodoAgenciaFinal) {
		this.periodoAgenciaFinal = periodoAgenciaFinal;
	}

	public String getPeriodoAgenciaInicial() {
		return periodoAgenciaInicial;
	}

	public void setPeriodoAgenciaInicial(String periodoAgenciaInicial) {
		this.periodoAgenciaInicial = periodoAgenciaInicial;
	}

	public String getPeriodoClienteFinal() {
		return periodoClienteFinal;
	}

	public void setPeriodoClienteFinal(String periodoClienteFinal) {
		this.periodoClienteFinal = periodoClienteFinal;
	}

	public String getPeriodoClienteInicial() {
		return periodoClienteInicial;
	}

	public void setPeriodoClienteInicial(String periodoClienteInicial) {
		this.periodoClienteInicial = periodoClienteInicial;
	}

	public String getServicoAcompanhamento() {
		return servicoAcompanhamento;
	}

	public void setServicoAcompanhamento(String servicoAcompanhamento) {
		this.servicoAcompanhamento = servicoAcompanhamento;
	}

	public String getServicoDiagnosticado() {
		return servicoDiagnosticado;
	}

	public void setServicoDiagnosticado(String servicoDiagnosticado) {
		this.servicoDiagnosticado = servicoDiagnosticado;
	}

	public Integer[] getEquipe() {
		return equipe;
	}

	public void setEquipe(Integer[] equipe) {
		this.equipe = equipe;
	}

	public Integer[] getEquipesSelecionadas() {
		return equipesSelecionadas;
	}

	public void setEquipesSelecionadas(Integer[] equipesSelecionadas) {
		this.equipesSelecionadas = equipesSelecionadas;
	}

	public Integer[] getOsSelecionada() {
		return osSelecionada;
	}

	public void setOsSelecionada(Integer[] osSelecionada) {
		this.osSelecionada = osSelecionada;
	}

	public String getAlertaEquipeServico() {
		return alertaEquipeServico;
	}

	public void setAlertaEquipeServico(String alertaEquipeServico) {
		this.alertaEquipeServico = alertaEquipeServico;
	}

	public String getDescricaoOrdemServico() {
		return descricaoOrdemServico;
	}

	public void setDescricaoOrdemServico(String descricaoOrdemServico) {
		this.descricaoOrdemServico = descricaoOrdemServico;
	}

	public int getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getAlertaEquipeLogradouro() {
		return alertaEquipeLogradouro;
	}

	public void setAlertaEquipeLogradouro(String alertaEquipeLogradouro) {
		this.alertaEquipeLogradouro = alertaEquipeLogradouro;
	}

	public String getDataRoteiro() {
		return dataRoteiro;
	}

	public void setDataRoteiro(String dataRoteiro) {
		this.dataRoteiro = dataRoteiro;
	}

	public String getOrigemServicos() {
		return origemServicos;
	}

	public void setOrigemServicos(String origemServicos) {
		this.origemServicos = origemServicos;
	}

	public String getUnidadeLotacao() {
		return unidadeLotacao;
	}

	public void setUnidadeLotacao(String unidadeLotacao) {
		this.unidadeLotacao = unidadeLotacao;
	}

}