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
package gcom.gui.arrecadacao.banco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter aviso bancario 
 *
 * @author thiago
 *
 * @date 10/03/2006
 */
public class AvisoBancarioActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String acao = "";
	private String idAvisoBancario = "";
	private String posicao = "";
	private String idContaBancaria = "";
	private String tipoAcesso = "";
	private String acerto = "";
	private String dataAcerto = "";
	private String valorAcerto = "";
	private String idTipoDeducao = "";
	private String valorDeducao = "";
	private String valorDeducoes = "";
	private String valorAviso = "";
	private String idFormaArrecadacao = "";

	private String numeroDocumento = "";
	private String dataRealizacao = "";
	private String valorArrecadacao = "";
	private String valorDevolucao = "";
	private String dataLancamento= "";
	
	/**
	 * M�todo que limpa os atributos 
	 *
	 * @author Administrador
	 * @date 10/03/2006
	 *
	 * @param arg0 mapeamento
	 * @param arg1 request
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);

		this.acao = ""; 
		this.idAvisoBancario = "";
		this.posicao = "";
		this.idContaBancaria = "";
		this.tipoAcesso = "";
		this.acerto = "";
		this.dataAcerto = "";
		this.valorAcerto = "";
		this.idTipoDeducao = "";
		this.valorDeducao = "";
		this.valorDeducoes = "";
		this.valorAviso = "";
		
		this.numeroDocumento = "";
		this.dataRealizacao = "";
		this.valorArrecadacao = "";
		this.valorDevolucao = "";
		this.dataLancamento= "";
	}

	
	
	public String getDataLancamento() {
		return dataLancamento;
	}



	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}



	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getValorArrecadacao() {
		return valorArrecadacao;
	}

	public void setValorArrecadacao(String valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}

	public String getValorDevolucao() {
		return valorDevolucao;
	}

	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getDataAcerto() {
		return dataAcerto;
	}

	public void setDataAcerto(String dataAcerto) {
		this.dataAcerto = dataAcerto;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}

	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}

	public String getIdContaBancaria() {
		return idContaBancaria;
	}

	public void setIdContaBancaria(String idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}

	public String getIdTipoDeducao() {
		return idTipoDeducao;
	}

	public void setIdTipoDeducao(String idTipoDeducao) {
		this.idTipoDeducao = idTipoDeducao;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

	public String getValorAcerto() {
		return valorAcerto;
	}

	public void setValorAcerto(String valorAcerto) {
		this.valorAcerto = valorAcerto;
	}

	public String getValorDeducao() {
		return valorDeducao;
	}

	public void setValorDeducao(String valorDeducao) {
		this.valorDeducao = valorDeducao;
	}

	/**
	 * @return Retorna o campo valorAviso.
	 */
	public String getValorAviso() {
		return valorAviso;
	}

	/**
	 * @param valorAviso O valorAviso a ser setado.
	 */
	public void setValorAviso(String valorAviso) {
		this.valorAviso = valorAviso;
	}

	/**
	 * @return Retorna o campo valorDeducoes.
	 */
	public String getValorDeducoes() {
		return valorDeducoes;
	}

	/**
	 * @param valorDeducoes O valorDeducoes a ser setado.
	 */
	public void setValorDeducoes(String valorDeducoes) {
		this.valorDeducoes = valorDeducoes;
	}

	/**
	 * @return Retorna o campo acerto.
	 */
	public String getAcerto() {
		return acerto;
	}

	/**
	 * @param acerto O acerto a ser setado.
	 */
	public void setAcerto(String acerto) {
		this.acerto = acerto;
	}



	public String getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}



	public void setIdFormaArrecadacao(String idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

}