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
package gcom.atendimentopublico.ordemservico;

import gcom.faturamento.debito.DebitoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoServicoACobrar implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private FiscalizacaoSituacaoServicoACobrarPK comp_id;

	/** persistent field */
	private short indicadorHidrometroCapacidade;

	/** persistent field */
	private short numeroVezesServicoCalculadoValor;

	private Short consumoCalculo;

	/** persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private DebitoTipo debitoTipo;
	
	/** persistent field */
	private BigDecimal percentualParticipacaoMultaFuncionario;
	
	/** persistent field */
	private short indicadorMultaInfracao;

	/** nullable persistent field */
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	
	private BigDecimal valorMultaAutoInfracao;
	
	private Short numeroVezesServicoCalculadoValorReincidencia;
	
	public static final Short CONSUMO_CALCULO_ZERO = 0;
	
	public static final Short CONSUMO_CALCULO_UM = 1;

	public static final Short CONSUMO_CALCULO_DOIS = 2;

	public static final Short CONSUMO_CALCULO_TRES = 3;

	public static final Short CONSUMO_CALCULO_QUATRO = 4;
	
	public static final Short CONSUMO_CALCULO_CINCO = 5;
	
	public static final Short CONSUMO_CALCULO_SEIS = 6;
	
	public static final Short CONSUMO_CALCULO_SETE = 7;
	
	public static final Short CONSUMO_CALCULO_OITO = 8;
	
	public static final Short CONSUMO_CALCULO_NOVE = 9;
	
	public static final Short CONSUMO_CALCULO_DEZ = 10;
	
	public static final Short CONSUMO_CALCULO_ONZE = 11;

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoServicoACobrarPK getComp_id() {
		return comp_id;
	}

	/**
	 * @param comp_id
	 *            O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoServicoACobrarPK comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * @return Retorna o campo debitoTipo.
	 */
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	/**
	 * @param debitoTipo
	 *            O debitoTipo a ser setado.
	 */
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	/**
	 * @param fiscalizacaoSituacao
	 *            O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(
			FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	/**
	 * @return Retorna o campo indicadorHidrometroCapacidade.
	 */
	public short getIndicadorHidrometroCapacidade() {
		return indicadorHidrometroCapacidade;
	}

	/**
	 * @param indicadorHidrometroCapacidade
	 *            O indicadorHidrometroCapacidade a ser setado.
	 */
	public void setIndicadorHidrometroCapacidade(
			short indicadorHidrometroCapacidade) {
		this.indicadorHidrometroCapacidade = indicadorHidrometroCapacidade;
	}

	/**
	 * @return Retorna o campo numeroVezesServicoCalculadoValor.
	 */
	public short getNumeroVezesServicoCalculadoValor() {
		return numeroVezesServicoCalculadoValor;
	}

	/**
	 * @param numeroVezesServicoCalculadoValor
	 *            O numeroVezesServicoCalculadoValor a ser setado.
	 */
	public void setNumeroVezesServicoCalculadoValor(
			short numeroVezesServicoCalculadoValor) {
		this.numeroVezesServicoCalculadoValor = numeroVezesServicoCalculadoValor;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao
	 *            O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getConsumoCalculo() {
		return consumoCalculo;
	}

	public void setConsumoCalculo(Short consumoCalculo) {
		this.consumoCalculo = consumoCalculo;
	}

	public BigDecimal getValorMultaAutoInfracao() {
		return valorMultaAutoInfracao;
	}

	public void setValorMultaAutoInfracao(BigDecimal valorMultaAutoInfracao) {
		this.valorMultaAutoInfracao = valorMultaAutoInfracao;
	}

	public short getIndicadorMultaInfracao() {
		return indicadorMultaInfracao;
	}

	public void setIndicadorMultaInfracao(short indicadorMultaInfracao) {
		this.indicadorMultaInfracao = indicadorMultaInfracao;
	}

	public BigDecimal getPercentualParticipacaoMultaFuncionario() {
		return percentualParticipacaoMultaFuncionario;
	}

	public void setPercentualParticipacaoMultaFuncionario(
			BigDecimal percentualParticipacaoMultaFuncionario) {
		this.percentualParticipacaoMultaFuncionario = percentualParticipacaoMultaFuncionario;
	}

	public Short getNumeroVezesServicoCalculadoValorReincidencia() {
		return numeroVezesServicoCalculadoValorReincidencia;
	}

	public void setNumeroVezesServicoCalculadoValorReincidencia(
			Short numeroVezesServicoCalculadoValorReincidencia) {
		this.numeroVezesServicoCalculadoValorReincidencia = numeroVezesServicoCalculadoValorReincidencia;
	}

}
