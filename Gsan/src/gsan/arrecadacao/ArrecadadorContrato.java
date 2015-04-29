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
package gsan.arrecadacao;

import gsan.arrecadacao.banco.ContaBancaria;
import gsan.cadastro.cliente.Cliente;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadadorContrato implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String numeroContrato;

    /** nullable persistent field */
    private Date dataContratoInicio;

    /** nullable persistent field */
    private String codigoConvenio;

    /** nullable persistent field */
    private Date dataContratoFim;

    /** nullable persistent field */
    private Date dataContratoEncerramento;

    /** nullable persistent field */
    private Short indicadorCobrancaIss;

    /** nullable persistent field */
    private Integer numeroSequecialArquivoRetornoCodigoBarras;

    /** nullable persistent field */
    private Integer numeroSequencialArquivoRetornoDebitoAutomatico;

    /** nullable persistent field */
    private Integer numeroSequencialArquivoEnvioDebitoAutomatico;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private ContaBancaria contaBancariaDepositoTarifa;

    /** persistent field */
    private ContaBancaria contaBancariaDepositoArrecadacao;

    /** persistent field */
    private gsan.arrecadacao.Arrecadador arrecadador;

    /** persistent field */
    private gsan.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento;

    /** persistent field */
    private Cliente cliente;
    
    private String descricaoEmail;
    
    /** nullable persistent field */
    private Integer numeroSequencialArquivoRetornoFichaCompensacao;
    
    private Short tamanhoMaximoIdentificacaoImovel;
    
    private Short  indicadorCobParClienteResponsavel;
    
    private Integer numeroSequencialEnvioCarteira17;
   
    

    public Short getTamanhoMaximoIdentificacaoImovel() {
		return tamanhoMaximoIdentificacaoImovel;
	}

	public void setTamanhoMaximoIdentificacaoImovel(
			Short tamanhoMaximoIdentificacaoImovel) {
		this.tamanhoMaximoIdentificacaoImovel = tamanhoMaximoIdentificacaoImovel;
	}

	/** full constructor */
    public ArrecadadorContrato(String numeroContrato, Date dataContratoInicio, String codigoConvenio, Date dataContratoFim, Date dataContratoEncerramento, Short indicadorCobrancaIss, Integer numeroSequecialArquivoRetornoCodigoBarras, Integer numeroSequencialArquivoRetornoDebitoAutomatico, Integer numeroSequencialArquivoEnvioDebitoAutomatico, Date ultimaAlteracao, ContaBancaria contaBancariaDepositoTarifa, ContaBancaria contaBancariaDepositoArrecadacao, gsan.arrecadacao.Arrecadador arrecadador, gsan.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento, Cliente cliente, Short indicadorCobParClienteResponsavel) {
        this.numeroContrato = numeroContrato;
        this.dataContratoInicio = dataContratoInicio;
        this.codigoConvenio = codigoConvenio;
        this.dataContratoFim = dataContratoFim;
        this.dataContratoEncerramento = dataContratoEncerramento;
        this.indicadorCobrancaIss = indicadorCobrancaIss;
        this.numeroSequecialArquivoRetornoCodigoBarras = numeroSequecialArquivoRetornoCodigoBarras;
        this.numeroSequencialArquivoRetornoDebitoAutomatico = numeroSequencialArquivoRetornoDebitoAutomatico;
        this.numeroSequencialArquivoEnvioDebitoAutomatico = numeroSequencialArquivoEnvioDebitoAutomatico;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaBancariaDepositoTarifa = contaBancariaDepositoTarifa;
        this.contaBancariaDepositoArrecadacao = contaBancariaDepositoArrecadacao;
        this.arrecadador = arrecadador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
        this.cliente = cliente;
        this.indicadorCobParClienteResponsavel =  indicadorCobParClienteResponsavel;
    }

    /** default constructor */
    public ArrecadadorContrato() {
    }

    /** minimal constructor */
    public ArrecadadorContrato(String numeroContrato, ContaBancaria contaBancariaDepositoTarifa, ContaBancaria contaBancariaDepositoArrecadacao, gsan.arrecadacao.Arrecadador arrecadador, gsan.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento, Cliente cliente) {
        this.numeroContrato = numeroContrato;
        this.contaBancariaDepositoTarifa = contaBancariaDepositoTarifa;
        this.contaBancariaDepositoArrecadacao = contaBancariaDepositoArrecadacao;
        this.arrecadador = arrecadador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
        this.cliente = cliente;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroContrato() {
        return this.numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Date getDataContratoInicio() {
        return this.dataContratoInicio;
    }

    public void setDataContratoInicio(Date dataContratoInicio) {
        this.dataContratoInicio = dataContratoInicio;
    }

    public String getCodigoConvenio() {
        return this.codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public Date getDataContratoFim() {
        return this.dataContratoFim;
    }

    public void setDataContratoFim(Date dataContratoFim) {
        this.dataContratoFim = dataContratoFim;
    }

    public Date getDataContratoEncerramento() {
        return this.dataContratoEncerramento;
    }

    public void setDataContratoEncerramento(Date dataContratoEncerramento) {
        this.dataContratoEncerramento = dataContratoEncerramento;
    }

    public Short getIndicadorCobrancaIss() {
        return this.indicadorCobrancaIss;
    }

    public void setIndicadorCobrancaIss(Short indicadorCobrancaIss) {
        this.indicadorCobrancaIss = indicadorCobrancaIss;
    }

    public Integer getNumeroSequecialArquivoRetornoCodigoBarras() {
        return this.numeroSequecialArquivoRetornoCodigoBarras;
    }

    public void setNumeroSequecialArquivoRetornoCodigoBarras(Integer numeroSequecialArquivoRetornoCodigoBarras) {
        this.numeroSequecialArquivoRetornoCodigoBarras = numeroSequecialArquivoRetornoCodigoBarras;
    }

    public Integer getNumeroSequencialArquivoRetornoDebitoAutomatico() {
        return this.numeroSequencialArquivoRetornoDebitoAutomatico;
    }

    public void setNumeroSequencialArquivoRetornoDebitoAutomatico(Integer numeroSequencialArquivoRetornoDebitoAutomatico) {
        this.numeroSequencialArquivoRetornoDebitoAutomatico = numeroSequencialArquivoRetornoDebitoAutomatico;
    }

    public Integer getNumeroSequencialArquivoEnvioDebitoAutomatico() {
        return this.numeroSequencialArquivoEnvioDebitoAutomatico;
    }

    public void setNumeroSequencialArquivoEnvioDebitoAutomatico(Integer numeroSequencialArquivoEnvioDebitoAutomatico) {
        this.numeroSequencialArquivoEnvioDebitoAutomatico = numeroSequencialArquivoEnvioDebitoAutomatico;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ContaBancaria getContaBancariaDepositoTarifa() {
        return this.contaBancariaDepositoTarifa;
    }

    public void setContaBancariaDepositoTarifa(ContaBancaria contaBancariaDepositoTarifa) {
        this.contaBancariaDepositoTarifa = contaBancariaDepositoTarifa;
    }

    public ContaBancaria getContaBancariaDepositoArrecadacao() {
        return this.contaBancariaDepositoArrecadacao;
    }

    public void setContaBancariaDepositoArrecadacao(ContaBancaria contaBancariaDepositoArrecadacao) {
        this.contaBancariaDepositoArrecadacao = contaBancariaDepositoArrecadacao;
    }

    public gsan.arrecadacao.Arrecadador getArrecadador() {
        return this.arrecadador;
    }

    public void setArrecadador(gsan.arrecadacao.Arrecadador arrecadador) {
        this.arrecadador = arrecadador;
    }

    public gsan.arrecadacao.ContratoMotivoCancelamento getContratoMotivoCancelamento() {
        return this.contratoMotivoCancelamento;
    }

    public void setContratoMotivoCancelamento(gsan.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public String getDescricaoEmail() {
		return descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getNumeroSequencialArquivoRetornoFichaCompensacao() {
		return numeroSequencialArquivoRetornoFichaCompensacao;
	}

	public void setNumeroSequencialArquivoRetornoFichaCompensacao(
			Integer numeroSequencialArquivoRetornoFichaCompensacao) {
		this.numeroSequencialArquivoRetornoFichaCompensacao = numeroSequencialArquivoRetornoFichaCompensacao;
	}

	public Short getIndicadorCobParClienteResponsavel() {
		return indicadorCobParClienteResponsavel;
	}

	public void setIndicadorCobParClienteResponsavel(
			Short indicadorCobParClienteResponsavel) {
		this.indicadorCobParClienteResponsavel = indicadorCobParClienteResponsavel;
	}

	public Integer getNumeroSequencialEnvioCarteira17() {
		return numeroSequencialEnvioCarteira17;
	}

	public void setNumeroSequencialEnvioCarteira17(Integer numeroSequencialEnvioCarteira17) {
		this.numeroSequencialEnvioCarteira17 = numeroSequencialEnvioCarteira17;
	}



	

}
