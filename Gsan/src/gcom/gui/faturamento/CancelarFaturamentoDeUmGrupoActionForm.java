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
 * 
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC1241 - Filtrar Grupo Para Cancelar Faturamento
 * 
 * @author Arthur Carvalho
 * @date 17/10/2011
 */

public class CancelarFaturamentoDeUmGrupoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	
	private String referenciaFaturada;
	
	private String valorFaturadoAgua;
	
	private String valorFaturadoEsgoto;
	
	private String valorFaturadoDebito;
	
	private String valorFaturadoCredito;
	
	private String valorFaturadoImpostos;
	
	private String valorFatudadoTotalCobrado;
	
	private String contasFaturadasNormal;
	
	private String contasFaturadasIncluida;
	
	private String contasFaturadasCancelada;
	
	private String contasFaturadasRetificada;
	
	private String contasFaturadasParcelada;
	
	private String contasFaturadaPaga;
	
	private String contasFaturadasVinculadasADcoumentoCobranca;
	
	private String contasFaturadasVinculadasAFaturamentoAgrupada;
	
	private String totalDeContasParaExclusao;
	
	private String grupoFaturamento;
	
	private String motivoCancelamento;
	
	private String referenciaGrupoMenosUmMes;

	public String getReferenciaFaturada() {
		return referenciaFaturada;
	}

	public void setReferenciaFaturada(String referenciaFaturada) {
		this.referenciaFaturada = referenciaFaturada;
	}

	public String getValorFaturadoAgua() {
		return valorFaturadoAgua;
	}

	public void setValorFaturadoAgua(String valorFaturadoAgua) {
		this.valorFaturadoAgua = valorFaturadoAgua;
	}

	public String getValorFaturadoEsgoto() {
		return valorFaturadoEsgoto;
	}

	public void setValorFaturadoEsgoto(String valorFaturadoEsgoto) {
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
	}

	public String getValorFaturadoDebito() {
		return valorFaturadoDebito;
	}

	public void setValorFaturadoDebito(String valorFaturadoDebito) {
		this.valorFaturadoDebito = valorFaturadoDebito;
	}

	public String getValorFaturadoCredito() {
		return valorFaturadoCredito;
	}

	public void setValorFaturadoCredito(String valorFaturadoCredito) {
		this.valorFaturadoCredito = valorFaturadoCredito;
	}

	public String getValorFaturadoImpostos() {
		return valorFaturadoImpostos;
	}

	public void setValorFaturadoImpostos(String valorFaturadoImpostos) {
		this.valorFaturadoImpostos = valorFaturadoImpostos;
	}

	public String getValorFatudadoTotalCobrado() {
		return valorFatudadoTotalCobrado;
	}

	public void setValorFatudadoTotalCobrado(String valorFatudadoTotalCobrado) {
		this.valorFatudadoTotalCobrado = valorFatudadoTotalCobrado;
	}

	public String getContasFaturadasNormal() {
		return contasFaturadasNormal;
	}

	public void setContasFaturadasNormal(String contasFaturadasNormal) {
		this.contasFaturadasNormal = contasFaturadasNormal;
	}

	public String getContasFaturadasIncluida() {
		return contasFaturadasIncluida;
	}

	public void setContasFaturadasIncluida(String contasFaturadasIncluida) {
		this.contasFaturadasIncluida = contasFaturadasIncluida;
	}

	public String getContasFaturadasCancelada() {
		return contasFaturadasCancelada;
	}

	public void setContasFaturadasCancelada(String contasFaturadasCancelada) {
		this.contasFaturadasCancelada = contasFaturadasCancelada;
	}

	public String getContasFaturadasRetificada() {
		return contasFaturadasRetificada;
	}

	public void setContasFaturadasRetificada(String contasFaturadasRetificada) {
		this.contasFaturadasRetificada = contasFaturadasRetificada;
	}

	public String getContasFaturadasParcelada() {
		return contasFaturadasParcelada;
	}

	public void setContasFaturadasParcelada(String contasFaturadasParcelada) {
		this.contasFaturadasParcelada = contasFaturadasParcelada;
	}

	public String getContasFaturadaPaga() {
		return contasFaturadaPaga;
	}

	public void setContasFaturadaPaga(String contasFaturadaPaga) {
		this.contasFaturadaPaga = contasFaturadaPaga;
	}

	public String getContasFaturadasVinculadasADcoumentoCobranca() {
		return contasFaturadasVinculadasADcoumentoCobranca;
	}

	public void setContasFaturadasVinculadasADcoumentoCobranca(String contasFaturadasVinculadasADcoumentoCobranca) {
		this.contasFaturadasVinculadasADcoumentoCobranca = contasFaturadasVinculadasADcoumentoCobranca;
	}

	public String getContasFaturadasVinculadasAFaturamentoAgrupada() {
		return contasFaturadasVinculadasAFaturamentoAgrupada;
	}

	public void setContasFaturadasVinculadasAFaturamentoAgrupada(String contasFaturadasVinculadasAFaturamentoAgrupada) {
		this.contasFaturadasVinculadasAFaturamentoAgrupada = contasFaturadasVinculadasAFaturamentoAgrupada;
	}

	public String getTotalDeContasParaExclusao() {
		return totalDeContasParaExclusao;
	}

	public void setTotalDeContasParaExclusao(String totalDeContasParaExclusao) {
		this.totalDeContasParaExclusao = totalDeContasParaExclusao;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public String getReferenciaGrupoMenosUmMes() {
		return referenciaGrupoMenosUmMes;
	}

	public void setReferenciaGrupoMenosUmMes(String referenciaGrupoMenosUmMes) {
		this.referenciaGrupoMenosUmMes = referenciaGrupoMenosUmMes;
	}

	

}