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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Este caso de uso permite gerar o resumo das a��es de cobran�a com a 
 * atividade emitir j� realizada e a atividade encerrar ainda n�o realizada 
 * e realizar a atividade encerrar das a��es que estejam comandadas.
 *
 * [UC0478] Gerar Resumo das A��es de Cobran�a do Cronograma
 *
 * [SB0004] - Atualizar Item do Documento de Cobran�a
 * 
 * Acumula a quantidade e o valor do item, na situia�o de d�bito correspondente
 * Armazena a data da situa��o do d�bito do imte do documento de cobran�a refrente a situa��o do d�bito do item do documento de cobran�a
 *
 * Objeto composto de:
 * Id Situa��o do D�bito
 * Quantidade de Ocorrencia das Situa��es de D�bito
 * Valor do Item Cobrado
 * Data situa��o do D�bito
 * 
 * @author Rafael Santos
 * @since 18/10/2006
 */
public class GerarResumoAcoesCobrancaCronogramaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Quantidade de Ocorrencia da Situa��oi de D�bito 
	 */
	private int quantidadeOcorrenciaSituacaoDebito;
	
	/**
	 * Valor do Item Pago
	 */
	private BigDecimal valorItemCobrado;

	/**
	 * Id da Situa��oi do D�bito
	 */
	private int idSituacaoDebito;
	
	/**
	 * Data da Situa��o do D�bito
	 */
	private Date dataSituacaoDebito;

	/**
	 * @return Retorna o campo dataSituacaoDebito.
	 */
	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	/**
	 * @param dataSituacaoDebito O dataSituacaoDebito a ser setado.
	 */
	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	/**
	 * @return Retorna o campo idSituacaoDebito.
	 */
	public int getIdSituacaoDebito() {
		return idSituacaoDebito;
	}

	/**
	 * @param idSituacaoDebito O idSituacaoDebito a ser setado.
	 */
	public void setIdSituacaoDebito(int idSituacaoDebito) {
		this.idSituacaoDebito = idSituacaoDebito;
	}

	/**
	 * @return Retorna o campo quantidadeOcorrenciaSituacaoDebito.
	 */
	public int getQuantidadeOcorrenciaSituacaoDebito() {
		return quantidadeOcorrenciaSituacaoDebito;
	}

	/**
	 * @param quantidadeOcorrenciaSituacaoDebito O quantidadeOcorrenciaSituacaoDebito a ser setado.
	 */
	public void setQuantidadeOcorrenciaSituacaoDebito(
			int quantidadeOcorrenciaSituacaoDebito) {
		this.quantidadeOcorrenciaSituacaoDebito = quantidadeOcorrenciaSituacaoDebito;
	}

	/**
	 * @return Retorna o campo valorItemCobrado.
	 */
	public BigDecimal getValorItemCobrado() {
		return valorItemCobrado;
	}

	/**
	 * @param valorItemCobrado O valorItemCobrado a ser setado.
	 */
	public void setValorItemCobrado(BigDecimal valorItemCobrado) {
		this.valorItemCobrado = valorItemCobrado;
	}

	/**
	 * Construtor de GerarResumoAcoesCobrancaCronogramaHelper 
	 * 
	 * @param quantidadeOcorrenciaSituacaoDebito
	 * @param valorItemCobrado
	 * @param idSituacaoDebito
	 * @param dataSituacaoDebito
	 */
	public GerarResumoAcoesCobrancaCronogramaHelper(int quantidadeOcorrenciaSituacaoDebito, BigDecimal valorItemCobrado, int idSituacaoDebito, Date dataSituacaoDebito) {
		super();
		this.quantidadeOcorrenciaSituacaoDebito = quantidadeOcorrenciaSituacaoDebito;
		this.valorItemCobrado = valorItemCobrado;
		this.idSituacaoDebito = idSituacaoDebito;
		this.dataSituacaoDebito = dataSituacaoDebito;
	}
	
	/**
	 * Construtor de GerarResumoAcoesCobrancaCronogramaHelper 
	 * 
	 * @param quantidadeOcorrenciaSituacaoDebito
	 * @param valorItemCobrado
	 * @param idSituacaoDebito
	 * @param dataSituacaoDebito
	 */
	public GerarResumoAcoesCobrancaCronogramaHelper() {
		super();
	}	
}