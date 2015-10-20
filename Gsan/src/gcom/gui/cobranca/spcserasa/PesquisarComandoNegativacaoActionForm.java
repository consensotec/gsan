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
package gcom.gui.cobranca.spcserasa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC 0653] Pesquisar Comando Negativa��o
 * 
 * @author K�ssia Albuquerque
 * @date 22/10/2007
 */


public class PesquisarComandoNegativacaoActionForm extends ValidatorActionForm {
	
	
	private static final long serialVersionUID = 1L;
	private String tituloComando;
	private String tipoPesquisa;
	private String indicadorComandoSimulado;
	private String periodoGeracaoComandoInicial;
	private String periodoGeracaoComandoFinal;
	private String periodoExecucaoComandoInicial;
	private String periodoExecucaoComandoFinal;
	private String usuarioResponsavelId;
	private String usuarioResponsavelNome;
	private String negativadorId;
	private String popup;
	
	/**
	 * @return Retorna o campo negativadorId.
	 */
	public String getNegativadorId() {
		return negativadorId;
	}
	/**
	 * @param negativadorId O negativadorId a ser setado.
	 */
	public void setNegativadorId(String negativadorId) {
		this.negativadorId = negativadorId;
	}
	/**
	 * @return Retorna o campo usuarioResponsavelId.
	 */
	public String getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}
	/**
	 * @param usuarioResponsavelId O usuarioResponsavelId a ser setado.
	 */
	public void setUsuarioResponsavelId(String usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}
	/**
	 * @return Retorna o campo usuarioResponsavelNome.
	 */
	public String getUsuarioResponsavelNome() {
		return usuarioResponsavelNome;
	}
	/**
	 * @param usuarioResponsavelNome O usuarioResponsavelNome a ser setado.
	 */
	public void setUsuarioResponsavelNome(String usuarioResponsavelNome) {
		this.usuarioResponsavelNome = usuarioResponsavelNome;
	}
	/**
	 * @return Retorna o campo indicadorComandoSimulado.
	 */
	public String getIndicadorComandoSimulado() {
		return indicadorComandoSimulado;
	}
	/**
	 * @param indicadorComandoSimulado O indicadorComandoSimulado a ser setado.
	 */
	public void setIndicadorComandoSimulado(String indicadorComandoSimulado) {
		this.indicadorComandoSimulado = indicadorComandoSimulado;
	}
	/**
	 * @return Retorna o campo periodoExecucaoComandoFinal.
	 */
	public String getPeriodoExecucaoComandoFinal() {
		return periodoExecucaoComandoFinal;
	}
	/**
	 * @param periodoExecucaoComandoFinal O periodoExecucaoComandoFinal a ser setado.
	 */
	public void setPeriodoExecucaoComandoFinal(String periodoExecucaoComandoFinal) {
		this.periodoExecucaoComandoFinal = periodoExecucaoComandoFinal;
	}
	/**
	 * @return Retorna o campo periodoExecucaoComandoInicial.
	 */
	public String getPeriodoExecucaoComandoInicial() {
		return periodoExecucaoComandoInicial;
	}
	/**
	 * @param periodoExecucaoComandoInicial O periodoExecucaoComandoInicial a ser setado.
	 */
	public void setPeriodoExecucaoComandoInicial(
			String periodoExecucaoComandoInicial) {
		this.periodoExecucaoComandoInicial = periodoExecucaoComandoInicial;
	}
	/**
	 * @return Retorna o campo periodoGeracaoComandoFinal.
	 */
	public String getPeriodoGeracaoComandoFinal() {
		return periodoGeracaoComandoFinal;
	}
	/**
	 * @param periodoGeracaoComandoFinal O periodoGeracaoComandoFinal a ser setado.
	 */
	public void setPeriodoGeracaoComandoFinal(String periodoGeracaoComandoFinal) {
		this.periodoGeracaoComandoFinal = periodoGeracaoComandoFinal;
	}
	/**
	 * @return Retorna o campo periodoGeracaoComandoInicial.
	 */
	public String getPeriodoGeracaoComandoInicial() {
		return periodoGeracaoComandoInicial;
	}
	/**
	 * @param periodoGeracaoComandoInicial O periodoGeracaoComandoInicial a ser setado.
	 */
	public void setPeriodoGeracaoComandoInicial(String periodoGeracaoComandoInicial) {
		this.periodoGeracaoComandoInicial = periodoGeracaoComandoInicial;
	}
	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}
	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}
	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	/**
	 * @return Returns the popup.
	 */
	public String getPopup() {
		return popup;
	}
	/**
	 * @param popup The popup to set.
	 */
	public void setPopup(String popup) {
		this.popup = popup;
	}
	

	

}
