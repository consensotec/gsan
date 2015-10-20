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
package gcom.gui.atendimentopublico.registroatendimento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * Filtrar Acompanhamento Registro Atendimento
 * 
 * @author Fl�vio Ferreira
 */
public class FiltrarAcompanhamentoRegistroAtendimentoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String situacaoRA;
	private String situacaoRAAbertos;
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	private String unidadeAtendimentoId;
	private String unidadeAtendimentoDescricao;	
	private Integer[] idsMotivoEncerramentoDisponiveis; 
	private Integer[] idsMotivoEncerramentoSelecionados;
	private String opcaoRelatorio;
	private String[] municipiosAssociados;
	
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

	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}

	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}

	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}

	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	public String getUnidadeAtendimentoDescricao() {
		return unidadeAtendimentoDescricao;
	}

	public void setUnidadeAtendimentoDescricao(String unidadeAtendimentoDescricao) {
		this.unidadeAtendimentoDescricao = unidadeAtendimentoDescricao;
	}

	public String getUnidadeAtendimentoId() {
		return unidadeAtendimentoId;
	}

	public void setUnidadeAtendimentoId(String unidadeAtendimentoId) {
		this.unidadeAtendimentoId = unidadeAtendimentoId;
	}

	public Integer[] getIdsMotivoEncerramentoDisponiveis() {
		return idsMotivoEncerramentoDisponiveis;
	}

	public void setIdsMotivoEncerramentoDisponiveis(
			Integer[] idsMotivoEncerramentoDisponiveis) {
		this.idsMotivoEncerramentoDisponiveis = idsMotivoEncerramentoDisponiveis;
	}

	public Integer[] getIdsMotivoEncerramentoSelecionados() {
		return idsMotivoEncerramentoSelecionados;
	}

	public void setIdsMotivoEncerramentoSelecionados(
			Integer[] idsMotivoEncerramentoSelecionados) {
		this.idsMotivoEncerramentoSelecionados = idsMotivoEncerramentoSelecionados;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getSituacaoRAAbertos() {
		return situacaoRAAbertos;
	}

	public void setSituacaoRAAbertos(String situacaoRAAbertos) {
		this.situacaoRAAbertos = situacaoRAAbertos;
	}

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public String[] getMunicipiosAssociados() {
		return municipiosAssociados;
	}

	public void setMunicipiosAssociados(String[] municipiosAssociados) {
		this.municipiosAssociados = municipiosAssociados;
	}
}