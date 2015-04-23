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
package gsan.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0450] Pesquisar Ordem Servi�o
 * 
 * @author Rafael Pinto
 *
 * @date 15/06/2006
 */
public class GerarRelatorioAcompanhamentoExecucaoOSActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String origemServico;
	private String situacaoOrdemServico;
	
	private String[] tipoServico;
	private String[] tipoServicoSelecionados;
	
	private String idUnidadeAtendimento;
	private String descricaoUnidadeAtendimento;
	
	private String idUnidadeAtual;
	private String descricaoUnidadeAtual;
	
	private String idUnidadeEncerramento;
	private String descricaoUnidadeEncerramento;
	
	private String periodoAtendimentoInicial;
	private String periodoAtendimentoFinal;
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	
	private String idEquipeProgramacao;
	private String descricaoEquipeProgramacao;
	private String idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private String tipoOrdenacao;
	private String periodoExecucaoInicial;
	private String periodoExecucaoFinal;

	public void resetOS() {
		
		origemServico= null;
		situacaoOrdemServico= null;
		
		idUnidadeAtendimento= null;
		descricaoUnidadeAtendimento= null;
		
		idUnidadeAtual= null;
		descricaoUnidadeAtual= null;
		
		idUnidadeEncerramento= null;
		descricaoUnidadeEncerramento= null;
		
		periodoAtendimentoInicial = null;
		periodoAtendimentoFinal = null;
		periodoEncerramentoInicial = null;
		periodoEncerramentoFinal = null;
		
		idEquipeProgramacao = null;
		descricaoEquipeProgramacao = null;
		idEquipeExecucao = null;
		descricaoEquipeExecucao = null;
		tipoOrdenacao = null;
		
		periodoExecucaoInicial = null;
		periodoExecucaoFinal = null;

	}

	/**
	 * @return Retorna o campo descricaoEquipeExecucao.
	 */
	public String getDescricaoEquipeExecucao() {
		return descricaoEquipeExecucao;
	}

	/**
	 * @param descricaoEquipeExecucao O descricaoEquipeExecucao a ser setado.
	 */
	public void setDescricaoEquipeExecucao(String descricaoEquipeExecucao) {
		this.descricaoEquipeExecucao = descricaoEquipeExecucao;
	}

	/**
	 * @return Retorna o campo descricaoEquipeProgramacao.
	 */
	public String getDescricaoEquipeProgramacao() {
		return descricaoEquipeProgramacao;
	}

	/**
	 * @param descricaoEquipeProgramacao O descricaoEquipeProgramacao a ser setado.
	 */
	public void setDescricaoEquipeProgramacao(String descricaoEquipeProgramacao) {
		this.descricaoEquipeProgramacao = descricaoEquipeProgramacao;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeAtendimento.
	 */
	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	/**
	 * @param descricaoUnidadeAtendimento O descricaoUnidadeAtendimento a ser setado.
	 */
	public void setDescricaoUnidadeAtendimento(String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeAtual.
	 */
	public String getDescricaoUnidadeAtual() {
		return descricaoUnidadeAtual;
	}

	/**
	 * @param descricaoUnidadeAtual O descricaoUnidadeAtual a ser setado.
	 */
	public void setDescricaoUnidadeAtual(String descricaoUnidadeAtual) {
		this.descricaoUnidadeAtual = descricaoUnidadeAtual;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeEncerramento.
	 */
	public String getDescricaoUnidadeEncerramento() {
		return descricaoUnidadeEncerramento;
	}

	/**
	 * @param descricaoUnidadeEncerramento O descricaoUnidadeEncerramento a ser setado.
	 */
	public void setDescricaoUnidadeEncerramento(String descricaoUnidadeEncerramento) {
		this.descricaoUnidadeEncerramento = descricaoUnidadeEncerramento;
	}

	/**
	 * @return Retorna o campo idEquipeExecucao.
	 */
	public String getIdEquipeExecucao() {
		return idEquipeExecucao;
	}

	/**
	 * @param idEquipeExecucao O idEquipeExecucao a ser setado.
	 */
	public void setIdEquipeExecucao(String idEquipeExecucao) {
		this.idEquipeExecucao = idEquipeExecucao;
	}

	/**
	 * @return Retorna o campo idEquipeProgramacao.
	 */
	public String getIdEquipeProgramacao() {
		return idEquipeProgramacao;
	}

	/**
	 * @param idEquipeProgramacao O idEquipeProgramacao a ser setado.
	 */
	public void setIdEquipeProgramacao(String idEquipeProgramacao) {
		this.idEquipeProgramacao = idEquipeProgramacao;
	}

	/**
	 * @return Retorna o campo idUnidadeAtendimento.
	 */
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	/**
	 * @param idUnidadeAtendimento O idUnidadeAtendimento a ser setado.
	 */
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo idUnidadeAtual.
	 */
	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	/**
	 * @param idUnidadeAtual O idUnidadeAtual a ser setado.
	 */
	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	/**
	 * @return Retorna o campo idUnidadeEncerramento.
	 */
	public String getIdUnidadeEncerramento() {
		return idUnidadeEncerramento;
	}

	/**
	 * @param idUnidadeEncerramento O idUnidadeEncerramento a ser setado.
	 */
	public void setIdUnidadeEncerramento(String idUnidadeEncerramento) {
		this.idUnidadeEncerramento = idUnidadeEncerramento;
	}

	/**
	 * @return Retorna o campo origemServico.
	 */
	public String getOrigemServico() {
		return origemServico;
	}

	/**
	 * @param origemServico O origemServico a ser setado.
	 */
	public void setOrigemServico(String origemServico) {
		this.origemServico = origemServico;
	}

	/**
	 * @return Retorna o campo periodoAtendimentoFinal.
	 */
	public String getPeriodoAtendimentoFinal() {
		return periodoAtendimentoFinal;
	}

	/**
	 * @param periodoAtendimentoFinal O periodoAtendimentoFinal a ser setado.
	 */
	public void setPeriodoAtendimentoFinal(String periodoAtendimentoFinal) {
		this.periodoAtendimentoFinal = periodoAtendimentoFinal;
	}

	/**
	 * @return Retorna o campo periodoAtendimentoInicial.
	 */
	public String getPeriodoAtendimentoInicial() {
		return periodoAtendimentoInicial;
	}

	/**
	 * @param periodoAtendimentoInicial O periodoAtendimentoInicial a ser setado.
	 */
	public void setPeriodoAtendimentoInicial(String periodoAtendimentoInicial) {
		this.periodoAtendimentoInicial = periodoAtendimentoInicial;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoFinal.
	 */
	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}

	/**
	 * @param periodoEncerramentoFinal O periodoEncerramentoFinal a ser setado.
	 */
	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoInicial.
	 */
	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}

	/**
	 * @param periodoEncerramentoInicial O periodoEncerramentoInicial a ser setado.
	 */
	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}

	/**
	 * @return Retorna o campo situacaoOrdemServico.
	 */
	public String getSituacaoOrdemServico() {
		return situacaoOrdemServico;
	}

	/**
	 * @param situacaoOrdemServico O situacaoOrdemServico a ser setado.
	 */
	public void setSituacaoOrdemServico(String situacaoOrdemServico) {
		this.situacaoOrdemServico = situacaoOrdemServico;
	}

	/**
	 * @return Retorna o campo tipoOrdenacao.
	 */
	public String getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	/**
	 * @param tipoOrdenacao O tipoOrdenacao a ser setado.
	 */
	public void setTipoOrdenacao(String tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}

	/**
	 * @return Retorna o campo tipoServico.
	 */
	public String[] getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServico O tipoServico a ser setado.
	 */
	public void setTipoServico(String[] tipoServico) {
		this.tipoServico = tipoServico;
	}

	/**
	 * @return Retorna o campo tipoServicoSelecionados.
	 */
	public String[] getTipoServicoSelecionados() {
		return tipoServicoSelecionados;
	}

	/**
	 * @param tipoServicoSelecionados O tipoServicoSelecionados a ser setado.
	 */
	public void setTipoServicoSelecionados(String[] tipoServicoSelecionados) {
		this.tipoServicoSelecionados = tipoServicoSelecionados;
	}

	public String getPeriodoExecucaoInicial() {
		return periodoExecucaoInicial;
	}

	public void setPeriodoExecucaoInicial(String periodoExecucaoInicial) {
		this.periodoExecucaoInicial = periodoExecucaoInicial;
	}
	
	public String getPeriodoExecucaoFinal() {
		return periodoExecucaoFinal;
	}

	public void setPeriodoExecucaoFinal(String periodoExecucaoFinal) {
		this.periodoExecucaoFinal = periodoExecucaoFinal;
	}
	
}