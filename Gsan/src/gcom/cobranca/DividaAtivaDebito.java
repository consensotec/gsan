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

package gcom.cobranca;

import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DividaAtivaDebito implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    /** persistent field */
    private BigDecimal valorDebitoSemCorrecao;
    
    /** persistent field */
    private BigDecimal valorDebitoComCorrecao;
    
    /** persistent field */
    private BigDecimal valorDebitoAmortizado;
    
    /** persistent field */
    private Date dataRetirada;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private DividaAtivaImovel dividaAtivaImovel;
    
    /** persistent field */
    private Conta conta;
    
    /** persistent field */
    private Parcelamento parcelamento;
    
    private Integer anoMesReferenciaDebito;

	public DividaAtivaDebito(Integer id, BigDecimal valorDebitoSemCorrecao,
			BigDecimal valorDebitoComCorrecao,
			BigDecimal valorDebitoAmortizado, Date dataRetirada,
			Date ultimaAlteracao, DividaAtivaImovel dividaAtivaImovel,
			Conta conta, Parcelamento parcelamento, Integer anoMesReferenciaDebito) {
		this.id = id;
		this.valorDebitoSemCorrecao = valorDebitoSemCorrecao;
		this.valorDebitoComCorrecao = valorDebitoComCorrecao;
		this.valorDebitoAmortizado = valorDebitoAmortizado;
		this.dataRetirada = dataRetirada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dividaAtivaImovel = dividaAtivaImovel;
		this.conta = conta;
		this.parcelamento = parcelamento;
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
	}

	/** default constructor */
	public DividaAtivaDebito() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorDebitoSemCorrecao() {
		return valorDebitoSemCorrecao;
	}

	public void setValorDebitoSemCorrecao(BigDecimal valorDebitoSemCorrecao) {
		this.valorDebitoSemCorrecao = valorDebitoSemCorrecao;
	}

	public BigDecimal getValorDebitoComCorrecao() {
		return valorDebitoComCorrecao;
	}

	public void setValorDebitoComCorrecao(BigDecimal valorDebitoComCorrecao) {
		this.valorDebitoComCorrecao = valorDebitoComCorrecao;
	}

	public BigDecimal getValorDebitoAmortizado() {
		return valorDebitoAmortizado;
	}

	public void setValorDebitoAmortizado(BigDecimal valorDebitoAmortizado) {
		this.valorDebitoAmortizado = valorDebitoAmortizado;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DividaAtivaImovel getDividaAtivaImovel() {
		return dividaAtivaImovel;
	}

	public void setDividaAtivaImovel(DividaAtivaImovel dividaAtivaImovel) {
		this.dividaAtivaImovel = dividaAtivaImovel;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Integer getAnoMesReferenciaDebito() {
		return anoMesReferenciaDebito;
	}

	public void setAnoMesReferenciaDebito(Integer anoMesReferenciaDebito) {
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
}
