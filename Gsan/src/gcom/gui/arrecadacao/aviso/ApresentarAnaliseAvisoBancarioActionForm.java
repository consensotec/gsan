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
package gcom.gui.arrecadacao.aviso;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formul�rio que ir� apresentar a an�lise do aviso banc�rio e os
 * pagamentos/devolu��es associados
 *
 * @author Raphael Rossiter
 * @date 23/03/2006
 */
public class ApresentarAnaliseAvisoBancarioActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idArrecadador;
	private String codigoNomeArrecadador;
	private String codigoDescricaoArrecadacaoForma;
	private String dataLancamento;
	private String sequencial;
	private String numeroDocumento;
	private String situacao;
	private String dataPrevistaCredito;
	private String valorPrevistoCredito;
	private String dataRealCredito;
	private String valorRealCredito;
	private String valorArrecadacao;
	private String valorDevolucao;
	private String valorContabilizado;
	private String anoMesArrecadacao;
	private String tipoAviso;
	private String contaBancaria;
	private String valorArrecadacaoCalculado;
	private String valorArrecadacaoInformado;
	private String valorDevolucaoCalculado;
	private String valorDevolucaoInformado;
	
	private String idAvisoBancario;
	private String bancoContaBancaria ;
	private String agenciaContaBancaria ;
	private String numeroContaBancaria ;
	private String valorSomatorioDeducoes ;
	private String valorSomatorioAcertosArrecadacao ;
	private String valorSomatorioAcertosDevolucao ;
	private String valorDiferencaArrecadacaoDevolucao ;
	
	
	public String getCodigoNomeArrecadador() {
		return codigoNomeArrecadador;
	}
	public void setCodigoNomeArrecadador(String codigoNomeArrecadador) {
		this.codigoNomeArrecadador = codigoNomeArrecadador;
	}
	public String getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getDataPrevistaCredito() {
		return dataPrevistaCredito;
	}
	public void setDataPrevistaCredito(String dataPrevistaCredito) {
		this.dataPrevistaCredito = dataPrevistaCredito;
	}
	public String getDataRealCredito() {
		return dataRealCredito;
	}
	public void setDataRealCredito(String dataRealCredito) {
		this.dataRealCredito = dataRealCredito;
	}
	public String getIdArrecadador() {
		return idArrecadador;
	}
	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getTipoAviso() {
		return tipoAviso;
	}
	public void setTipoAviso(String tipoAviso) {
		this.tipoAviso = tipoAviso;
	}
	public String getValorArrecadacao() {
		return valorArrecadacao;
	}
	public void setValorArrecadacao(String valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}
	public String getValorContabilizado() {
		return valorContabilizado;
	}
	public void setValorContabilizado(String valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}
	public String getValorDevolucao() {
		return valorDevolucao;
	}
	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}
	public String getValorPrevistoCredito() {
		return valorPrevistoCredito;
	}
	public void setValorPrevistoCredito(String valorPrevistoCredito) {
		this.valorPrevistoCredito = valorPrevistoCredito;
	}
	public String getValorRealCredito() {
		return valorRealCredito;
	}
	public void setValorRealCredito(String valorRealCredito) {
		this.valorRealCredito = valorRealCredito;
	}
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}
	public void setAnoMesArrecadacao(String anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}
	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public String getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}
	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(String valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}
	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public String getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}
	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(String valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}
	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public String getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}
	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(String valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}
	/**
	 * @return Retorna o campo valorDevolucaoInformado.
	 */
	public String getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}
	/**
	 * @param valorDevolucaoInformado O valorDevolucaoInformado a ser setado.
	 */
	public void setValorDevolucaoInformado(String valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}
	public String getAgenciaContaBancaria() {
		return agenciaContaBancaria;
	}
	public void setAgenciaContaBancaria(String agenciaContaBancaria) {
		this.agenciaContaBancaria = agenciaContaBancaria;
	}
	public String getBancoContaBancaria() {
		return bancoContaBancaria;
	}
	public void setBancoContaBancaria(String bancoContaBancaria) {
		this.bancoContaBancaria = bancoContaBancaria;
	}
	public String getNumeroContaBancaria() {
		return numeroContaBancaria;
	}
	public void setNumeroContaBancaria(String numeroContaBancaria) {
		this.numeroContaBancaria = numeroContaBancaria;
	}
	public String getValorDiferencaArrecadacaoDevolucao() {
		return valorDiferencaArrecadacaoDevolucao;
	}
	public void setValorDiferencaArrecadacaoDevolucao(
			String valorDiferencaArrecadacaoDevolucao) {
		this.valorDiferencaArrecadacaoDevolucao = valorDiferencaArrecadacaoDevolucao;
	}
	public String getValorSomatorioAcertosArrecadacao() {
		return valorSomatorioAcertosArrecadacao;
	}
	public void setValorSomatorioAcertosArrecadacao(
			String valorSomatorioAcertosArrecadacao) {
		this.valorSomatorioAcertosArrecadacao = valorSomatorioAcertosArrecadacao;
	}
	public String getValorSomatorioAcertosDevolucao() {
		return valorSomatorioAcertosDevolucao;
	}
	public void setValorSomatorioAcertosDevolucao(
			String valorSomatorioAcertosDevolucao) {
		this.valorSomatorioAcertosDevolucao = valorSomatorioAcertosDevolucao;
	}
	public String getValorSomatorioDeducoes() {
		return valorSomatorioDeducoes;
	}
	public void setValorSomatorioDeducoes(String valorSomatorioDeducoes) {
		this.valorSomatorioDeducoes = valorSomatorioDeducoes;
	}
	public String getIdAvisoBancario() {
		return idAvisoBancario;
	}
	public void setIdAvisoBancario(String idAvisoBancario) {
		this.idAvisoBancario = idAvisoBancario;
	}
	public String getCodigoDescricaoArrecadacaoForma() {
		return codigoDescricaoArrecadacaoForma;
	}
	public void setCodigoDescricaoArrecadacaoForma(
			String codigoDescricaoArrecadacaoForma) {
		this.codigoDescricaoArrecadacaoForma = codigoDescricaoArrecadacaoForma;
	}
	
	
}
