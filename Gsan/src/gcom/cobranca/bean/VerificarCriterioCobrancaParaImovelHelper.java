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
package gcom.cobranca.bean;

import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Objeto utilizado no retorno do met�do: do [SB0004] Verificar Crit�rio de
 * Cobran�a para Im�vel do [UC 0251]
 * 
 * @author Pedro Alexandre
 * @since 09/02/2006
 */
public class VerificarCriterioCobrancaParaImovelHelper {

	/**
	 * Quantidade de itens cobrados nos documentos
	 */
	private Integer quantidadeItensEmDebito;

	/**
	 * Valor dos d�bitos do im�vel
	 */
	private BigDecimal valorDebitoImovel;

	/**
	 * Flag que indica se o im�vel satifaz o crit�rio de cobran�a
	 */
	private boolean flagCriterioCobrancaImovel;

	/**
	 * Cole��o de valores de conta do  d�bito do im�vel
	 */
	private Collection<ContaValoresHelper> colecaoContasValores;
	
	/**
	 * Cole��o de d�bito a cobrar do  d�bito do im�vel
	 */
	private Collection<DebitoACobrar> colecaoDebitoACobrar;
	
	/**
	 * Cole��o de valores de guia de pagamento do  d�bito do im�vel
	 */
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores;
	
	/**
	 * Valor dos descontos
	 */
	private BigDecimal valorDesconto;
	
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	private int motivoRejeicao;
	
	public static final int SITUACAO_LIGACAO_AGUA_INVALIDA = 1;
	
	public static final int SITUACAO_LIGACAO_ESGOTO_INVALIDA = 1;
	
	public static final int SITUACAO_ESPECIAL_COBRANCA = 3;
	
	public static final int SITUACAO_COBRANCA = 4;
	
	public static final int SITUACAO_COBRANCA_DIFERENTE_SELECIONADAS = 5;
	
	public static final int PERFIL_SEM_CRITERIO = 6;
	
	public static final int IMOVEL_SEM_DEBITO = 7;
	
	public static final int NAO_CONSIDERAR_CONTA_MES = 8;
	
	public static final int NAO_CONSIDERAR_CONTA_ANTIGA = 9;
	
	public static final int APENAS_CONTA_ANTIGA_CONTA_MES = 10;
	
	public int getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(int motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public VerificarCriterioCobrancaParaImovelHelper() {}

	public boolean isFlagCriterioCobrancaImovel() {
		return flagCriterioCobrancaImovel;
	}

	public void setFlagCriterioCobrancaImovel(boolean flagCriterioCobrancaImovel) {
		this.flagCriterioCobrancaImovel = flagCriterioCobrancaImovel;
	}

	public Integer getQuantidadeItensEmDebito() {
		return quantidadeItensEmDebito;
	}

	public void setQuantidadeItensEmDebito(Integer quantidadeItensEmDebito) {
		this.quantidadeItensEmDebito = quantidadeItensEmDebito;
	}

	public BigDecimal getValorDebitoImovel() {
		return valorDebitoImovel;
	}

	public void setValorDebitoImovel(BigDecimal valorDebitoImovel) {
		this.valorDebitoImovel = valorDebitoImovel;
	}

	public Collection<ContaValoresHelper> getColecaoContasValores() {
		return colecaoContasValores;
	}

	public void setColecaoContasValores(
			Collection<ContaValoresHelper> colecaoContasValores) {
		this.colecaoContasValores = colecaoContasValores;
	}

	public Collection<DebitoACobrar> getColecaoDebitoACobrar() {
		return colecaoDebitoACobrar;
	}

	public void setColecaoDebitoACobrar(
			Collection<DebitoACobrar> colecaoDebitoACobrar) {
		this.colecaoDebitoACobrar = colecaoDebitoACobrar;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiasPagamentoValores() {
		return colecaoGuiasPagamentoValores;
	}

	public void setColecaoGuiasPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamentoValores) {
		this.colecaoGuiasPagamentoValores = colecaoGuiasPagamentoValores;
	}

}
