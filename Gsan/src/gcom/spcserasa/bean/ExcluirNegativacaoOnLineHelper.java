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
package gcom.spcserasa.bean;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;

import java.util.Collection;

/**
 * [UC0675] Excluir Negativa��o OnLine
 * RM6364 - Altera��o para negativa��o por per�odo
 * 
 * @author Vivianne Sousa
 * @date 12/12/2011
 */
public class ExcluirNegativacaoOnLineHelper implements Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private NegativadorMovimentoReg negativadorMovimentoReg;
	private Collection<NegativadorMovimentoRegItem> itensConta;
	private Collection<NegativadorMovimentoRegItem> itensGuiaPagamento;
	private String situacaoNegativacao;
	
	public ExcluirNegativacaoOnLineHelper() {
		super();
	}
	public ExcluirNegativacaoOnLineHelper(
			Cliente cliente, NegativadorMovimentoReg negativadorMovimentoReg,
			Collection<NegativadorMovimentoRegItem> itensConta, Collection<NegativadorMovimentoRegItem> itensGuiaPagamento,
			String situacaoNegativacao) {
		super();
		this.cliente = cliente;
		this.negativadorMovimentoReg = negativadorMovimentoReg;
		this.itensConta = itensConta;
		this.itensGuiaPagamento = itensGuiaPagamento;
		this.situacaoNegativacao = situacaoNegativacao;
	}
	public String getSituacaoNegativacao() {
		return situacaoNegativacao;
	}
	public void setSituacaoNegativacao(String situacaoNegativacao) {
		this.situacaoNegativacao = situacaoNegativacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public NegativadorMovimentoReg getNegativadorMovimentoReg() {
		return negativadorMovimentoReg;
	}
	public void setNegativadorMovimentoReg(NegativadorMovimentoReg negativadorMovimentoReg) {
		this.negativadorMovimentoReg = negativadorMovimentoReg;
	}
	public Collection<NegativadorMovimentoRegItem> getItensConta() {
		return itensConta;
	}
	public void setItensConta(Collection<NegativadorMovimentoRegItem> itensConta) {
		this.itensConta = itensConta;
	}
	public Collection<NegativadorMovimentoRegItem> getItensGuiaPagamento() {
		return itensGuiaPagamento;
	}
	public void setItensGuiaPagamento(Collection<NegativadorMovimentoRegItem> itensGuiaPagamento) {
		this.itensGuiaPagamento = itensGuiaPagamento;
	}
		
}