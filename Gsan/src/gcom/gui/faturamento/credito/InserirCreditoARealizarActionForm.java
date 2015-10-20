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
package gcom.gui.faturamento.credito;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0194] Cr�dito a Realizar
 * Permite inserir um cr�dito a realizar
 * @author Roberta Costa
 * @since  11/01/2006 
 */
public class InserirCreditoARealizarActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String nomeCliente;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String registroAtendimento;
	private String nomeRegistroAtendimento;
	private String ordemServico;
	private String nomeOrdemServico;
	private String tipoCredito;
	
	private String origemCredito;
	private String numeroPrestacoes;
	private String valorCredito;
	private String referencia;
	private String mesAnoInicialDoCredito;
	public String valorMinimoMesAnoInicialDoCredito;
	
	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	/**
	 * @param inscricaoImovel The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	/**
	 * @return Returns the matriculaImovel.
	 */
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	/**
	 * @param matriculaImovel The matriculaImovel to set.
	 */
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Returns the numeroPrestacoes.
	 */
	public String getNumeroPrestacoes() {
		return numeroPrestacoes;
	}
	/**
	 * @param numeroPrestacoes The numeroPrestacoes to set.
	 */
	public void setNumeroPrestacoes(String numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}
	/**
	 * @return Returns the ordemServico.
	 */
	public String getOrdemServico() {
		return ordemServico;
	}
	/**
	 * @param ordemServico The ordemServico to set.
	 */
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}
	/**
	 * @return Returns the origemCredito.
	 */
	public String getOrigemCredito() {
		return origemCredito;
	}
	/**
	 * @param origemCredito The origemCredito to set.
	 */
	public void setOrigemCredito(String origemCredito) {
		this.origemCredito = origemCredito;
	}
	/**
	 * @return Returns the registroAtendimento.
	 */
	public String getRegistroAtendimento() {
		return registroAtendimento;
	}
	/**
	 * @param registroAtendimento The registroAtendimento to set.
	 */
	public void setRegistroAtendimento(String registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}
	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	/**
	 * @param situacaoAgua The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	/**
	 * @param situacaoEsgoto The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	/**
	 * @return Returns the tipoCredito.
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}
	/**
	 * @param tipoCredito The tipoCredito to set.
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	/**
	 * @return Returns the valorCredito.
	 */
	public String getValorCredito() {
		return valorCredito;
	}
	/**
	 * @param valorCredito The valorCredito to set.
	 */
	public void setValorCredito(String valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	/**
	 * @return Retorna o campo referencia.
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia O referencia a ser setado.
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		/**@todo: finish this method, this is just the skeleton.*/
		return null;
	}
	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		matriculaImovel = null;
		inscricaoImovel = null;
		nomeCliente = null;
		situacaoAgua = null;
		situacaoEsgoto= null;
	}
	/**
	 * @return Retorna o campo nomeOrdemServico.
	 */
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	/**
	 * @param nomeOrdemServico O nomeOrdemServico a ser setado.
	 */
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	/**
	 * @return Retorna o campo nomeRegistroAtendimento.
	 */
	public String getNomeRegistroAtendimento() {
		return nomeRegistroAtendimento;
	}
	/**
	 * @param nomeRegistroAtendimento O nomeRegistroAtendimento a ser setado.
	 */
	public void setNomeRegistroAtendimento(String nomeRegistroAtendimento) {
		this.nomeRegistroAtendimento = nomeRegistroAtendimento;
	}
	public String getMesAnoInicialDoCredito() {
		return mesAnoInicialDoCredito;
	}
	public void setMesAnoInicialDoCredito(String mesAnoInicialDoCredito) {
		this.mesAnoInicialDoCredito = mesAnoInicialDoCredito;
	}
	public String getValorMinimoMesAnoInicialDoCredito() {
		return valorMinimoMesAnoInicialDoCredito;
	}
	public void setValorMinimoMesAnoInicialDoCredito(String valorMinimoMesAnoInicialDoCredito) {
		this.valorMinimoMesAnoInicialDoCredito = valorMinimoMesAnoInicialDoCredito;
	}
	
	
}