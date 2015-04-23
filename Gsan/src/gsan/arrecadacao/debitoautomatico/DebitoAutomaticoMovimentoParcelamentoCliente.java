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
package gsan.arrecadacao.debitoautomatico;

import gsan.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoAutomaticoMovimentoParcelamentoCliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataVencimento;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private Date processamento;

    /** nullable persistent field */
    private Date envioBanco;

    /** nullable persistent field */
    private Date retornoBanco;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer numeroSequenciaArquivoEnviado;

    /** nullable persistent field */
    private Integer numeroSequenciaArquivoRecebido;

    /** persistent field */
    private DebitoAutomaticoParcelamentoCliente debitoAutomaticoParcelamentoCliente;

    /** persistent field */
    private DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo;

    /** persistent field */
    private PrestacaoContratoParcelamento prestacaoContratoParcelamento;

    /** default constructor */
    public DebitoAutomaticoMovimentoParcelamentoCliente() {
    }

    public DebitoAutomaticoMovimentoParcelamentoCliente(
			Integer id, Date dataVencimento, BigDecimal valorDebito, Date processamento, Date envioBanco, Date retornoBanco,
			Date ultimaAlteracao, Integer numeroSequenciaArquivoEnviado, Integer numeroSequenciaArquivoRecebido,
			DebitoAutomaticoParcelamentoCliente debitoAutomaticoParcelamentoCliente,
			DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo,
			PrestacaoContratoParcelamento prestacaoContratoParcelamento) {
		super();
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.valorDebito = valorDebito;
		this.processamento = processamento;
		this.envioBanco = envioBanco;
		this.retornoBanco = retornoBanco;
		this.ultimaAlteracao = ultimaAlteracao;
		this.numeroSequenciaArquivoEnviado = numeroSequenciaArquivoEnviado;
		this.numeroSequenciaArquivoRecebido = numeroSequenciaArquivoRecebido;
		this.debitoAutomaticoParcelamentoCliente = debitoAutomaticoParcelamentoCliente;
		this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
		this.prestacaoContratoParcelamento = prestacaoContratoParcelamento;
	}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Date getProcessamento() {
        return this.processamento;
    }

    public void setProcessamento(Date processamento) {
        this.processamento = processamento;
    }

    public Date getEnvioBanco() {
        return this.envioBanco;
    }

    public void setEnvioBanco(Date envioBanco) {
        this.envioBanco = envioBanco;
    }

    public Date getRetornoBanco() {
        return this.retornoBanco;
    }

    public void setRetornoBanco(Date retornoBanco) {
        this.retornoBanco = retornoBanco;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getNumeroSequenciaArquivoEnviado() {
        return this.numeroSequenciaArquivoEnviado;
    }

    public void setNumeroSequenciaArquivoEnviado(Integer numeroSequenciaArquivoEnviado) {
        this.numeroSequenciaArquivoEnviado = numeroSequenciaArquivoEnviado;
    }

    public Integer getNumeroSequenciaArquivoRecebido() {
        return this.numeroSequenciaArquivoRecebido;
    }

    public void setNumeroSequenciaArquivoRecebido(Integer numeroSequenciaArquivoRecebido) {
        this.numeroSequenciaArquivoRecebido = numeroSequenciaArquivoRecebido;
    }

    public gsan.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo getDebitoAutomaticoRetornoCodigo() {
        return this.debitoAutomaticoRetornoCodigo;
    }

    public void setDebitoAutomaticoRetornoCodigo(gsan.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo) {
        this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
    }

    public DebitoAutomaticoParcelamentoCliente getDebitoAutomaticoParcelamentoCliente() {
		return debitoAutomaticoParcelamentoCliente;
	}

	public void setDebitoAutomaticoParcelamentoCliente(
			DebitoAutomaticoParcelamentoCliente debitoAutomaticoParcelamentoCliente) {
		this.debitoAutomaticoParcelamentoCliente = debitoAutomaticoParcelamentoCliente;
	}

	public PrestacaoContratoParcelamento getPrestacaoContratoParcelamento() {
		return prestacaoContratoParcelamento;
	}

	public void setPrestacaoContratoParcelamento(PrestacaoContratoParcelamento prestacaoContratoParcelamento) {
		this.prestacaoContratoParcelamento = prestacaoContratoParcelamento;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
