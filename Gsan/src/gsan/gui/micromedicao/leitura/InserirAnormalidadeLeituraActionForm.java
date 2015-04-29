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
package gsan.gui.micromedicao.leitura;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descri��o da classe>>
 * 
 * @author Thiago Tenorio
 * @date 26/07/2006
 */
public class InserirAnormalidadeLeituraActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;

	private String abreviatura;

	private String indicadorRelativoHidrometro;

	private String indicadorImovelSemHidrometro;

	private String usoRestritoSistema;

	private String perdaTarifaSocial;

	private String osAutomatico;

	private String tipoServico;

	private String consumoLeituraNaoInformado;

	private String consumoLeituraInformado;

	private String leituraLeituraNaoturaInformado;

	private String leituraLeituraInformado;

	private String IndicadorUso;

	private String numeroFatorSemLeitura;

	private String numeroFatorComLeitura;

	private String indicadorLeitura;
	
	private String indicadorNaoImprimirConta;
	
	private String indicadorExibirMensagemHidrometrosCalcada;
	
	private String indicadorExibirMensagemHidrometrosSubstituidos;
	
	//RM 3403
	private String indicadorFotoObrigatoria;
	
	/**
	 * Indicador para exibir a anormalidade no relat�rio 
	 * de leituras e anormalidades informadas
	 */
	private String indicadorExibirAnormalidadeRelatorio;
	
	/**
	 * Quantidade de ocorr�ncias para uma mesma anormalidade em um im�vel
	 * suspender a leitura
	 */
	private String numeroVezesSuspendeLeitura;

	/**
	 * Quantidade de meses que o im�vel ficar� com a leitura suspensa em fun��o
	 * de ocorr�ncia de uma mesma anormalidade
	 */
	private String numeroMesesLeituraSuspensa;

	public String getIndicadorUso() {
		return IndicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		IndicadorUso = indicadorUso;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getConsumoLeituraInformado() {
		return consumoLeituraInformado;
	}

	public void setConsumoLeituraInformado(String consumoLeituraInformado) {
		this.consumoLeituraInformado = consumoLeituraInformado;
	}

	public String getConsumoLeituraNaoInformado() {
		return consumoLeituraNaoInformado;
	}

	public void setConsumoLeituraNaoInformado(String consumoLeituraNaoInformado) {
		this.consumoLeituraNaoInformado = consumoLeituraNaoInformado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorImovelSemHidrometro() {
		return indicadorImovelSemHidrometro;
	}

	public void setIndicadorImovelSemHidrometro(String indicadorImovelSemHidrometro) {
		this.indicadorImovelSemHidrometro = indicadorImovelSemHidrometro;
	}

	public String getIndicadorRelativoHidrometro() {
		return indicadorRelativoHidrometro;
	}

	public void setIndicadorRelativoHidrometro(String indicadorRelativoHidrometro) {
		this.indicadorRelativoHidrometro = indicadorRelativoHidrometro;
	}

	public String getLeituraLeituraInformado() {
		return leituraLeituraInformado;
	}

	public void setLeituraLeituraInformado(String leituraLeituraInformado) {
		this.leituraLeituraInformado = leituraLeituraInformado;
	}

	public String getLeituraLeituraNaoturaInformado() {
		return leituraLeituraNaoturaInformado;
	}

	public void setLeituraLeituraNaoturaInformado(String leituraLeituraNaoturaInformado) {
		this.leituraLeituraNaoturaInformado = leituraLeituraNaoturaInformado;
	}

	public String getOsAutomatico() {
		return osAutomatico;
	}

	public void setOsAutomatico(String osAutomatico) {
		this.osAutomatico = osAutomatico;
	}

	public String getPerdaTarifaSocial() {
		return perdaTarifaSocial;
	}

	public void setPerdaTarifaSocial(String perdaTarifaSocial) {
		this.perdaTarifaSocial = perdaTarifaSocial;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getUsoRestritoSistema() {
		return usoRestritoSistema;
	}

	public void setUsoRestritoSistema(String usoRestritoSistema) {
		this.usoRestritoSistema = usoRestritoSistema;
	}

	public String getNumeroFatorSemLeitura() {
		return numeroFatorSemLeitura;
	}

	public void setNumeroFatorSemLeitura(String numeroFatorSemLeitura) {
		this.numeroFatorSemLeitura = numeroFatorSemLeitura;
	}

	public String getNumeroFatorComLeitura() {
		return numeroFatorComLeitura;
	}

	public void setNumeroFatorComLeitura(String numeroFatorComLeitura) {
		this.numeroFatorComLeitura = numeroFatorComLeitura;
	}

	public String getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(String indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public String getNumeroMesesLeituraSuspensa() {
		return numeroMesesLeituraSuspensa;
	}

	public void setNumeroMesesLeituraSuspensa(String numeroMesesLeituraSuspensa) {
		this.numeroMesesLeituraSuspensa = numeroMesesLeituraSuspensa;
	}

	public String getNumeroVezesSuspendeLeitura() {
		return numeroVezesSuspendeLeitura;
	}

	public void setNumeroVezesSuspendeLeitura(String numeroVezesSuspendeLeitura) {
		this.numeroVezesSuspendeLeitura = numeroVezesSuspendeLeitura;
	}

	public String getIndicadorExibirAnormalidadeRelatorio() {
		return indicadorExibirAnormalidadeRelatorio;
	}

	public void setIndicadorExibirAnormalidadeRelatorio(
			String indicadorExibirAnormalidadeRelatorio) {
		this.indicadorExibirAnormalidadeRelatorio = indicadorExibirAnormalidadeRelatorio;
	}

	public String getIndicadorNaoImprimirConta() {
		return indicadorNaoImprimirConta;
	}

	public void setIndicadorNaoImprimirConta(String indicadorNaoImprimirConta) {
		this.indicadorNaoImprimirConta = indicadorNaoImprimirConta;
	}

	public String getIndicadorExibirMensagemHidrometrosCalcada() {
		return indicadorExibirMensagemHidrometrosCalcada;
	}

	public void setIndicadorExibirMensagemHidrometrosCalcada(
			String indicadorExibirMensagemHidrometrosCalcada) {
		this.indicadorExibirMensagemHidrometrosCalcada = indicadorExibirMensagemHidrometrosCalcada;
	}

	public String getIndicadorExibirMensagemHidrometrosSubstituidos() {
		return indicadorExibirMensagemHidrometrosSubstituidos;
	}

	public void setIndicadorExibirMensagemHidrometrosSubstituidos(
			String indicadorExibirMensagemHidrometrosSubstituidos) {
		this.indicadorExibirMensagemHidrometrosSubstituidos = indicadorExibirMensagemHidrometrosSubstituidos;
	}

	public String getIndicadorFotoObrigatoria() {
		return indicadorFotoObrigatoria;
	}

	public void setIndicadorFotoObrigatoria(String indicadorFotoObrigatoria) {
		this.indicadorFotoObrigatoria = indicadorFotoObrigatoria;
	}
	
}