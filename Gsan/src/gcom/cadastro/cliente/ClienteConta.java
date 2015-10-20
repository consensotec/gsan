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
package gcom.cadastro.cliente;

import gcom.faturamento.conta.Conta;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


@ControleAlteracao()
public class ClienteConta extends ObjetoTransacao {
	
	public static final int OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL = 2127;
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

	/**
	 * persistent field
	 */
    @ControleAlteracao(funcionalidade={OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL})
	private Short indicadorNomeConta;
    
	/** nullable persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL})
    private Date ultimaAlteracao;
    
    /** persistent field */
    @ControleAlteracao(value=FiltroClienteConta.CLIENTE, funcionalidade={OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL})
    private gcom.cadastro.cliente.Cliente cliente;

    /** persistent field */
    @ControleAlteracao(funcionalidade={OPERACAO_ASSOCIAR_CONTA_NOVO_CLIENTE_RESPONSAVEL})
    private gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo;

    /** persistent field */
    private Conta conta;

    /** full constructor */
    public ClienteConta(gcom.cadastro.cliente.Cliente cliente, gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo, Conta conta) {
        this.cliente = cliente;
        this.clienteRelacaoTipo = clienteRelacaoTipo;
        this.conta = conta;
    }

    /** default constructor */
    public ClienteConta() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public gcom.cadastro.cliente.Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
        this.cliente = cliente;
    }

    public gcom.cadastro.cliente.ClienteRelacaoTipo getClienteRelacaoTipo() {
        return this.clienteRelacaoTipo;
    }

    public void setClienteRelacaoTipo(gcom.cadastro.cliente.ClienteRelacaoTipo clienteRelacaoTipo) {
        this.clienteRelacaoTipo = clienteRelacaoTipo;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getIndicadorNomeConta() {
		return indicadorNomeConta;
	}

	public void setIndicadorNomeConta(Short indicadorNomeConta) {
		this.indicadorNomeConta = indicadorNomeConta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroClienteConta filtroClienteConta = new FiltroClienteConta();
		filtroClienteConta.adicionarParametro(new ParametroSimples(FiltroClienteConta.ID,
				this.getId()));
		filtroClienteConta.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteConta.CLIENTE);
		filtroClienteConta.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteConta.CONTA);
		filtroClienteConta.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteConta.CLIENTE_RELACAO_TIPO);
		return filtroClienteConta;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"conta.imovel.id", "conta.id", "conta.referenciaFormatada"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Im�vel", "Conta", "Referencia"};
		return labels;		
	}

}
