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
package gcom.arrecadacao;

import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoCarteiraMovimento implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    /** nullable persistent field */
    private Integer numeroCarteira;

    /** nullable persistent field */
    private Date dataVencimento;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private Date dataProcessamento;

    /** nullable persistent field */
    private Date dataEnvioBanco;

    /** nullable persistent field */
    private Date dataRetornoBanco;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer numeroSequenciaArquivoEnviado;

    /** nullable persistent field */
    private Integer numeroSequenciaArquivoRecebido;

    /** persistent field */
    private ContaGeral conta;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo;

    /** persistent field */
    private Banco banco;

    /** full constructor */
    public DebitoCarteiraMovimento(Integer numeroCarteira, Date dataVencimento, BigDecimal valorDebito, Date dataProcessamento, 
    		Date dataEnvioBanco, Date dataRetornoBanco, Date ultimaAlteracao, Integer numeroSequenciaArquivoEnviado, 
    		Integer numeroSequenciaArquivoRecebido, 
    		FaturamentoGrupo faturamentoGrupo, ContaGeral conta, 
    		DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo, 
    		Banco banco) {
    	
    	this.numeroCarteira = numeroCarteira;
        this.dataVencimento = dataVencimento;
        this.valorDebito = valorDebito;
        this.dataProcessamento = dataProcessamento;
        this.dataEnvioBanco = dataEnvioBanco;
        this.dataRetornoBanco = dataRetornoBanco;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroSequenciaArquivoEnviado = numeroSequenciaArquivoEnviado;
        this.numeroSequenciaArquivoRecebido = numeroSequenciaArquivoRecebido;
        this.banco = banco;
        this.faturamentoGrupo = faturamentoGrupo;
        this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
        this.conta = conta;
    }

    /** default constructor */
    public DebitoCarteiraMovimento() {
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroCarteira() {
		return numeroCarteira;
	}

	public void setNumeroCarteira(Integer numeroCarteira) {
		this.numeroCarteira = numeroCarteira;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Date getDataEnvioBanco() {
		return dataEnvioBanco;
	}

	public void setDataEnvioBanco(Date dataEnvioBanco) {
		this.dataEnvioBanco = dataEnvioBanco;
	}

	public Date getDataRetornoBanco() {
		return dataRetornoBanco;
	}

	public void setDataRetornoBanco(Date dataRetornoBanco) {
		this.dataRetornoBanco = dataRetornoBanco;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getNumeroSequenciaArquivoEnviado() {
		return numeroSequenciaArquivoEnviado;
	}

	public void setNumeroSequenciaArquivoEnviado(Integer numeroSequenciaArquivoEnviado) {
		this.numeroSequenciaArquivoEnviado = numeroSequenciaArquivoEnviado;
	}

	public Integer getNumeroSequenciaArquivoRecebido() {
		return numeroSequenciaArquivoRecebido;
	}

	public void setNumeroSequenciaArquivoRecebido(Integer numeroSequenciaArquivoRecebido) {
		this.numeroSequenciaArquivoRecebido = numeroSequenciaArquivoRecebido;
	}

	public ContaGeral getConta() {
		return conta;
	}

	public void setConta(ContaGeral conta) {
		this.conta = conta;
	}

	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public DebitoAutomaticoRetornoCodigo getDebitoAutomaticoRetornoCodigo() {
		return debitoAutomaticoRetornoCodigo;
	}

	public void setDebitoAutomaticoRetornoCodigo(DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo) {
		this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
