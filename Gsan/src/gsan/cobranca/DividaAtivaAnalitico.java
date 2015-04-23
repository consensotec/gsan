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

package gsan.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Localidade;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.faturamento.conta.Conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DividaAtivaAnalitico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    /** persistent field */
    private Date dataVencimento;
    
    private Integer anoMesReferencia;
    
    /** persistent field */
    private BigDecimal valorDebitoOriginal;
    
    /** persistent field */
    private BigDecimal valorDebitoComCorrecao;
    
    /** persistent field */
    private BigDecimal valorDebitoAmortizado;
    
    /** persistent field */
    private short indicadorIntra;
    
    private Integer anoMesDividaAtiva;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Imovel imovel;
    
    /** persistent field */
    private Cliente cliente;
    
    /** persistent field */
    private Conta conta;
    
    /** persistent field */
    private Parcelamento parcelamento;
    
    /** persistent field */
    private Localidade localidade;
    
    public final static short INDICADOR_INTRA = 1;
    public final static short INDICADOR_NAO_INTRA = 2;
    
	public DividaAtivaAnalitico(Integer id, Date dataVencimento,
			Integer anoMesReferencia, BigDecimal valorDebitoOriginal,
			BigDecimal valorDebitoComCorrecao,
			BigDecimal valorDebitoAmortizado, short indicadorIntra,
			Integer anoMesDividaAtiva, Date ultimaAlteracao, Imovel imovel,
			Cliente cliente, Conta conta, Parcelamento parcelamento) {
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.anoMesReferencia = anoMesReferencia;
		this.valorDebitoOriginal = valorDebitoOriginal;
		this.valorDebitoComCorrecao = valorDebitoComCorrecao;
		this.valorDebitoAmortizado = valorDebitoAmortizado;
		this.indicadorIntra = indicadorIntra;
		this.anoMesDividaAtiva = anoMesDividaAtiva;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.cliente = cliente;
		this.conta = conta;
		this.parcelamento = parcelamento;
	}

	/** default constructor */
	public DividaAtivaAnalitico() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public BigDecimal getValorDebitoOriginal() {
		return valorDebitoOriginal;
	}

	public void setValorDebitoOriginal(BigDecimal valorDebitoOriginal) {
		this.valorDebitoOriginal = valorDebitoOriginal;
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

	public short getIndicadorIntra() {
		return indicadorIntra;
	}

	public void setIndicadorIntra(short indicadorIntra) {
		this.indicadorIntra = indicadorIntra;
	}

	public Integer getAnoMesDividaAtiva() {
		return anoMesDividaAtiva;
	}

	public void setAnoMesDividaAtiva(Integer anoMesDividaAtiva) {
		this.anoMesDividaAtiva = anoMesDividaAtiva;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
}
