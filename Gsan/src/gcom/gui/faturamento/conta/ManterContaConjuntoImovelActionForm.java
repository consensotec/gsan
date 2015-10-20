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
package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class ManterContaConjuntoImovelActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricaoInicial;
	private String inscricaoFinal;
	private String quatidadeImovel;
	private String mesAnoConta;
	private String quatidadeConta;
	private String codigoCliente;
	private String nomeCliente;
	private String codigoClienteSuperior;
	
	private String dataVencimentoInicial;
	private String dataVencimentoFinal;
	
	private String idGrupoFaturamento;
	private String mesAnoContaFinal;
	
	private String indicadorContaPaga;
	
	
	/**
	 * @return Retorna o campo indicadorContaPaga.
	 */
	public String getIndicadorContaPaga() {
		return indicadorContaPaga;
	}
	/**
	 * @param indicadorContaPaga O indicadorContaPaga a ser setado.
	 */
	public void setIndicadorContaPaga(String indicadorContaPaga) {
		this.indicadorContaPaga = indicadorContaPaga;
	}
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return Retorna o campo inscricaoFinal.
	 */
	public String getInscricaoFinal() {
		return inscricaoFinal;
	}
	/**
	 * @param inscricaoFinal O inscricaoFinal a ser setado.
	 */
	public void setInscricaoFinal(String inscricaoFinal) {
		this.inscricaoFinal = inscricaoFinal;
	}
	/**
	 * @return Retorna o campo inscricaoInicial.
	 */
	public String getInscricaoInicial() {
		return inscricaoInicial;
	}
	/**
	 * @param inscricaoInicial O inscricaoInicial a ser setado.
	 */
	public void setInscricaoInicial(String inscricaoInicial) {
		this.inscricaoInicial = inscricaoInicial;
	}
	/**
	 * @return Retorna o campo mesAnoConta.
	 */
	public String getMesAnoConta() {
		return mesAnoConta;
	}
	/**
	 * @param mesAnoConta O mesAnoConta a ser setado.
	 */
	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}
	/**
	 * @return Retorna o campo quatidadeConta.
	 */
	public String getQuatidadeConta() {
		return quatidadeConta;
	}
	/**
	 * @param quatidadeConta O quatidadeConta a ser setado.
	 */
	public void setQuatidadeConta(String quatidadeConta) {
		this.quatidadeConta = quatidadeConta;
	}
	/**
	 * @return Retorna o campo quatidadeImovel.
	 */
	public String getQuatidadeImovel() {
		return quatidadeImovel;
	}
	/**
	 * @param quatidadeImovel O quatidadeImovel a ser setado.
	 */
	public void setQuatidadeImovel(String quatidadeImovel) {
		this.quatidadeImovel = quatidadeImovel;
	}
	public String getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	public void setDataVencimentoFinal(String dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	public String getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	public void setDataVencimentoInicial(String dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	public String getMesAnoContaFinal() {
		return mesAnoContaFinal;
	}
	public void setMesAnoContaFinal(String mesAnoContaFinal) {
		this.mesAnoContaFinal = mesAnoContaFinal;
	}
	/**
	 * @return Retorna o campo codigoClienteSuperior.
	 */
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}
	/**
	 * @param codigoClienteSuperior O codigoClienteSuperior a ser setado.
	 */
	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}
	
	

}
