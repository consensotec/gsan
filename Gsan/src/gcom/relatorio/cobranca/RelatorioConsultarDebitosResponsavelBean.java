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
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;
import gcom.util.serializacao.Campo;

import java.math.BigDecimal;


/**
 * classe respons�vel por criar o Relat�rio de D�bitos Por R�sponsavel
 * 
 * @author Cesar Medeiros
 * @date 15/04/2015
 * 
 */
public class RelatorioConsultarDebitosResponsavelBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;

	private String codigoClienteSuperior;
	
	private String nomeClienteSuperior;
	
	private Integer idResponsavel;
	
	private String nomeResponsavel;
	
	private Integer qtdMatriculas;
	
	private Integer qtdEconomias;	
	
	private Integer qtdContas;
	
	private BigDecimal valorConta;
	
	private BigDecimal valorGuiaPagamento;
	
	private BigDecimal valorAcrescimos;
	
	private BigDecimal valorAtualizado;
	
	private BigDecimal valorDebitoCobrar;
	
	private BigDecimal valorCreditoRealizar;
	
	private BigDecimal valorGeral;
	
	private Integer idImovel;

	
	
	@Campo(posicao=1, nome="CODIGO DO CLIENTE SUPERIOR", formato="")
	public String getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	public void setCodigoClienteSuperior(String codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}	
	
	@Campo(posicao=2, nome="NOME DO CLIENTE SUPERIOR", formato="")
	public String getNomeClienteSuperior() {
		return nomeClienteSuperior;
	}
	
	public void setNomeClienteSuperior(String nomeUsuarioSuperior) {
		this.nomeClienteSuperior = nomeUsuarioSuperior;
	}	

	@Campo(posicao=3, nome="CODIGO DO CLIENTE RESPONSAVEL ", formato="")
	public Integer getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Integer idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	
	@Campo(posicao=4, nome="NOME DO CLIENTE RESPONSAVEL ", formato="")
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	@Campo(posicao=5, nome="QUANTIDADE DE MATRICULAS", formato="")
	public Integer getQtdMatriculas() {
		if(qtdMatriculas == null){
			return 0;
		}
		return qtdMatriculas;
	}

	public void setQtdMatriculas(Integer qtdMatriculas) {
		this.qtdMatriculas = qtdMatriculas;
	}
			
	@Campo(posicao=6, nome="QUANTIDADE DE ECONOMIAS", formato="")
	public Integer getQtdEconomias() {
		if(qtdEconomias == null){
			return 0;
		}
		return qtdEconomias;
	}
	
	public void setQtdEconomias(Integer qtdEconomias) {
		this.qtdEconomias = qtdEconomias;
	}
	
	
	@Campo(posicao=7, nome="QUANTIDADE DE CONTAS ", formato="")
	public Integer getQtdContas() {
		if(qtdContas == null){
			return 0;
		}
		return qtdContas;
	}

	public void setQtdContas(Integer qtdContas) {
		this.qtdContas = qtdContas;
	}
	
	@Campo(posicao=8, nome="VALOR DAS CONTAS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorConta() {
		if(valorConta == null){
			return BigDecimal.ZERO;
		}
		return valorConta;
	}
	
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}
	
	@Campo(posicao=10, nome="VALOR DAS GUIAS DE PAGAMENTO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorGuiaPagamento() {
		if(valorGuiaPagamento == null){
			return BigDecimal.ZERO;
		}
		return valorGuiaPagamento;
	}

	public void setValorGuiaPagamento(BigDecimal valorGuiaPagamento) {
		this.valorGuiaPagamento = valorGuiaPagamento;
	}
		
	@Campo(posicao=11, nome="ACRESCIMOS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorAcrescimos() {
		if(valorAcrescimos == null){
			return BigDecimal.ZERO;
		}
		return valorAcrescimos;
	}

	public void setValorAcrescimos(BigDecimal valorAcrescimos) {
		this.valorAcrescimos = valorAcrescimos;
	}
	
	@Campo(posicao=12, nome="VALOR ATUALIZADO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorAtualizado() {
		if(valorAtualizado == null){
			return BigDecimal.ZERO;
		}
		return valorAtualizado;
	}

	public void setValorAtualizado(BigDecimal valorAtualizado) {
		this.valorAtualizado = valorAtualizado;
	}
	
	@Campo(posicao=13, nome="DEBITOS A COBRAR", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorDebitoCobrar() {
		if(valorDebitoCobrar == null){
			return BigDecimal.ZERO;
		}
		return valorDebitoCobrar;
	}

	public void setValorDebitoCobrar(BigDecimal valorDebitoCobrar) {
		this.valorDebitoCobrar = valorDebitoCobrar;
	}
	
	@Campo(posicao=14, nome="CREDITOS A REALIZAR", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorCreditoRealizar() {
		if(valorCreditoRealizar == null){
			return BigDecimal.ZERO;
		}
		return valorCreditoRealizar;
	}

	public void setValorCreditoRealizar(BigDecimal valorCreditoRealizar) {
		this.valorCreditoRealizar = valorCreditoRealizar;
	}
	
	@Campo(posicao=15, nome="TOTAL GERAL", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorGeral() {
		if(valorGeral == null){
			return BigDecimal.ZERO;
		}
		return valorGeral;
	}

	public void setValorGeral(BigDecimal valorGeral) {
		this.valorGeral = valorGeral;
	}
	
	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	
	
	
	

}