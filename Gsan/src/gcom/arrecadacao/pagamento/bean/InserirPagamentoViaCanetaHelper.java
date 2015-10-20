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
package gcom.arrecadacao.pagamento.bean;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.pagamento.Pagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Objeto helper utilizado para armazenar os dados necess�rio para inserir 
 * um pagamento por leitura optica de c�digo de barras
 *
 * @author Pedro Alexandre
 * @date 16/02/2006
 */
public class InserirPagamentoViaCanetaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public InserirPagamentoViaCanetaHelper() {
	}

	private String codigoBarra;

	private BigDecimal valorPagamento;
	
	private Collection<Pagamento> colecaoPagamento;
	
	private Collection<Devolucao> colecaoDevolucao;
	
	//adicionado por Vivianne Sousa - 22/12/2009
	//[UC0971] Inserir Pagamentos para Faturas Especiais
	private RegistroHelperCodigoBarras registroHelperCodigoBarras;
	
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String cobigoBarra) {
		this.codigoBarra = cobigoBarra;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getCodigoBarraFormatado() {
		if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.length() == 44){
		  return  codigoBarra.substring(0,11) + " " + codigoBarra.substring(11,22) + " " + codigoBarra.substring(22,33) + " " + codigoBarra.substring(33,44); 
		}else{
			return "";
		}
	}
	
	public String getCodigoBarraFichaCompensacaoFormatado() {
		if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.length() == 47){
		  return  codigoBarra.substring(0,5) + "." + codigoBarra.substring(5,10) + " " 
		  + codigoBarra.substring(10,15) + "." + codigoBarra.substring(15,21) + " "
		  + codigoBarra.substring(21,26) + "." + codigoBarra.substring(26,32) + " "
		  + codigoBarra.substring(32,33) + " " + codigoBarra.substring(33,47);
		}
		else{
			return "";
		}
	}

	public Collection<Pagamento> getColecaoPagamento() {
		return colecaoPagamento;
	}

	public void setColecaoPagamento(Collection<Pagamento> colecaoPagamento) {
		this.colecaoPagamento = colecaoPagamento;
	}

	public Collection<Devolucao> getColecaoDevolucao() {
		return colecaoDevolucao;
	}

	public void setColecaoDevolucao(Collection<Devolucao> colecaoDevolucao) {
		this.colecaoDevolucao = colecaoDevolucao;
	}

	public RegistroHelperCodigoBarras getRegistroHelperCodigoBarras() {
		return registroHelperCodigoBarras;
	}

	public void setRegistroHelperCodigoBarras(
			RegistroHelperCodigoBarras registroHelperCodigoBarras) {
		this.registroHelperCodigoBarras = registroHelperCodigoBarras;
	}

		
}
