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


/*
 * Fl�vio Leonardo
 * 03/11/2008
 */
package gcom.arrecadacao.pagamento;

import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;


/** @author Hibernate CodeGenerator */
public class GuiaPagamentoItemHistorico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];

		retorno[0] = "id";
		
		return retorno;
	}
	
	public Filtro retornaFiltro() {
		FiltroGuiaPagamentoItemHistorico filtroGuiaPagamentoItemHistorico = new FiltroGuiaPagamentoItemHistorico();

		filtroGuiaPagamentoItemHistorico.adicionarParametro(new ParametroSimples(
				FiltroGuiaPagamentoItemHistorico.ID, this.getId()));
		filtroGuiaPagamentoItemHistorico.adicionarCaminhoParaCarregamentoEntidade(FiltroGuiaPagamentoItemHistorico.GUIA_PAGAMENTO_HISTORICO);

		return filtroGuiaPagamentoItemHistorico;
	}

    /** identifier field */
	private Integer id;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private GuiaPagamentoHistorico guiaPagamentoHistorico;

    /** nullable persistent field */
    private DebitoTipo debitoTipo;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private ContaGeral contaGeral;
    
    private CreditoTipo creditoTipo;

    /** full constructor */
    public GuiaPagamentoItemHistorico(BigDecimal valorDebito, GuiaPagamentoHistorico guiaPagamentoHistorico, DebitoTipo debitoTipo, Date ultimaAlteracao , CreditoTipo creditoTipo) {
        this.valorDebito = valorDebito;
        this.guiaPagamentoHistorico = guiaPagamentoHistorico;
        this.debitoTipo = debitoTipo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.creditoTipo = creditoTipo;
    }

    /** default constructor */
    public GuiaPagamentoItemHistorico(){}
   

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GuiaPagamentoItemHistorico) ) return false;
        GuiaPagamentoItemHistorico castOther = (GuiaPagamentoItemHistorico) other;
        return new EqualsBuilder()
            .append(this.getGuiaPagamentoHistorico(), castOther.getGuiaPagamentoHistorico())
            .append(this.getDebitoTipo(), castOther.getDebitoTipo())
            .append(this.getCreditoTipo(), castOther.getCreditoTipo())
            .isEquals();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public GuiaPagamentoHistorico getGuiaPagamentoHistorico() {
		return guiaPagamentoHistorico;
	}

	public void setGuiaPagamentoHistorico(GuiaPagamentoHistorico guiaPagamentoHistorico) {
		this.guiaPagamentoHistorico = guiaPagamentoHistorico;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public CreditoTipo getCreditoTipo() {
		return creditoTipo;
	}

	public void setCreditoTipo(CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}
	
}
