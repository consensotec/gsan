/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.spcserasa.bean;

import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;

import java.util.Collection;

/**
 * [UC0675] Excluir Negativação OnLine
 * RM6364 - Alteração para negativação por período
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