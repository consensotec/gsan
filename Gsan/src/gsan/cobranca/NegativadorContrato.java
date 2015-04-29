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

package gsan.cobranca;

import gsan.arrecadacao.ContratoMotivoCancelamento;
import gsan.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gsan.interceptor.ObjetoTransacao;
import gsan.spcserasa.FiltroNegativadorContrato;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorContrato extends ObjetoTransacao implements Serializable  {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String numeroContrato;

    /** nullable persistent field */
    private String codigoConvenio;

    /** nullable persistent field */
    private Date dataContratoInicio;

    /** nullable persistent field */
    private Date dataContratoFim;

    /** nullable persistent field */
    private Date dataContratoEncerramento;

    /** nullable persistent field */
    private String descricaoEmailEnvioArquivo;

    /** persistent field */
    private int numeroSequencialEnvio;

    /** persistent field */
    private int numeroSequencialRetorno;

    /** persistent field */
    private BigDecimal valorContrato;

    /** nullable persistent field */
    private BigDecimal valorTarifaInclusao;

    /** nullable persistent field */
    private Integer numeroInclusoesContratadas;

    /** nullable persistent field */
    private Integer numeroInclusoesEnviadas;

    /** nullable persistent field */
    private Integer numeroExclusoesEnviadas;

    /** persistent field */
    private int numeroTamanhoRegistroMovimento;

    /** persistent field */
    private short numeroPrazoInclusao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gsan.cobranca.Negativador negativador;

    /** persistent field */
    private ContratoMotivoCancelamento contratoMotivoCancelamento;
    
    private Short IndicadorObriControSequeRetorno;
    
    private Integer numeroEntidade;
    
    private Integer numeroAssociado;

    /** full constructor */
    public NegativadorContrato(Integer id, String numeroContrato, String codigoConvenio, Date dataContratoInicio, Date dataContratoFim, Date dataContratoEncerramento, String descricaoEmailEnvioArquivo, int numeroSequencialEnvio, int numeroSequencialRetorno, BigDecimal valorContrato, BigDecimal valorTarifaInclusao, Integer numeroInclusoesContratadas, Integer numeroInclusoesEnviadas, Integer numeroExclusoesEnviadas, int numeroTamanhoRegistroMovimento, short numeroPrazoInclusao, Date ultimaAlteracao, gsan.cobranca.Negativador negativador, ContratoMotivoCancelamento contratoMotivoCancelamento, Integer numeroEntidade, Integer numeroAssociado) {
        this.id = id;
        this.numeroContrato = numeroContrato;
        this.codigoConvenio = codigoConvenio;
        this.dataContratoInicio = dataContratoInicio;
        this.dataContratoFim = dataContratoFim;
        this.dataContratoEncerramento = dataContratoEncerramento;
        this.descricaoEmailEnvioArquivo = descricaoEmailEnvioArquivo;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.valorContrato = valorContrato;
        this.valorTarifaInclusao = valorTarifaInclusao;
        this.numeroInclusoesContratadas = numeroInclusoesContratadas;
        this.numeroInclusoesEnviadas = numeroInclusoesEnviadas;
        this.numeroExclusoesEnviadas = numeroExclusoesEnviadas;
        this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
        this.numeroPrazoInclusao = numeroPrazoInclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
        this.numeroEntidade = numeroEntidade;
        this.numeroAssociado = numeroAssociado;
    }

    /** default constructor */
    public NegativadorContrato() {
    }

    /** minimal constructor */
    public NegativadorContrato(Integer id, String numeroContrato, int numeroSequencialEnvio, int numeroSequencialRetorno, BigDecimal valorContrato, int numeroTamanhoRegistroMovimento, short numeroPrazoInclusao, Date ultimaAlteracao, gsan.cobranca.Negativador negativador, ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.id = id;
        this.numeroContrato = numeroContrato;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.valorContrato = valorContrato;
        this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
        this.numeroPrazoInclusao = numeroPrazoInclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
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

    public String getCodigoConvenio() {
        return this.codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public Date getDataContratoInicio() {
        return this.dataContratoInicio;
    }

    public void setDataContratoInicio(Date dataContratoInicio) {
        this.dataContratoInicio = dataContratoInicio;
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

    public String getDescricaoEmailEnvioArquivo() {
        return this.descricaoEmailEnvioArquivo;
    }

    public void setDescricaoEmailEnvioArquivo(String descricaoEmailEnvioArquivo) {
        this.descricaoEmailEnvioArquivo = descricaoEmailEnvioArquivo;
    }

    public int getNumeroSequencialEnvio() {
        return this.numeroSequencialEnvio;
    }

    public void setNumeroSequencialEnvio(int numeroSequencialEnvio) {
        this.numeroSequencialEnvio = numeroSequencialEnvio;
    }

    public int getNumeroSequencialRetorno() {
        return this.numeroSequencialRetorno;
    }

    public void setNumeroSequencialRetorno(int numeroSequencialRetorno) {
        this.numeroSequencialRetorno = numeroSequencialRetorno;
    }

    public BigDecimal getValorContrato() {
        return this.valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public BigDecimal getValorTarifaInclusao() {
        return this.valorTarifaInclusao;
    }

    public void setValorTarifaInclusao(BigDecimal valorTarifaInclusao) {
        this.valorTarifaInclusao = valorTarifaInclusao;
    }

    public Integer getNumeroInclusoesContratadas() {
        return this.numeroInclusoesContratadas;
    }

    public void setNumeroInclusoesContratadas(Integer numeroInclusoesContratadas) {
        this.numeroInclusoesContratadas = numeroInclusoesContratadas;
    }

    public Integer getNumeroInclusoesEnviadas() {
        return this.numeroInclusoesEnviadas;
    }

    public void setNumeroInclusoesEnviadas(Integer numeroInclusoesEnviadas) {
        this.numeroInclusoesEnviadas = numeroInclusoesEnviadas;
    }

    public Integer getNumeroExclusoesEnviadas() {
        return this.numeroExclusoesEnviadas;
    }

    public void setNumeroExclusoesEnviadas(Integer numeroExclusoesEnviadas) {
        this.numeroExclusoesEnviadas = numeroExclusoesEnviadas;
    }

    public int getNumeroTamanhoRegistroMovimento() {
        return this.numeroTamanhoRegistroMovimento;
    }

    public void setNumeroTamanhoRegistroMovimento(int numeroTamanhoRegistroMovimento) {
        this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
    }

    public short getNumeroPrazoInclusao() {
        return this.numeroPrazoInclusao;
    }

    public void setNumeroPrazoInclusao(short numeroPrazoInclusao) {
        this.numeroPrazoInclusao = numeroPrazoInclusao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gsan.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gsan.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public ContratoMotivoCancelamento getContratoMotivoCancelamento() {
        return this.contratoMotivoCancelamento;
    }

    public void setContratoMotivoCancelamento(ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
    }
    
	public Integer getNumeroEntidade() {
		return numeroEntidade;
	}

	public void setNumeroEntidade(Integer numeroEntidade) {
		this.numeroEntidade = numeroEntidade;
	}

	public Integer getNumeroAssociado() {
		return numeroAssociado;
	}

	public void setNumeroAssociado(Integer numeroAssociado) {
		this.numeroAssociado = numeroAssociado;
	}

	public Short getIndicadorObriControSequeRetorno() {
		return IndicadorObriControSequeRetorno;
	}

	public void setIndicadorObriControSequeRetorno(
			Short indicadorObriControSequeRetorno) {
		IndicadorObriControSequeRetorno = indicadorObriControSequeRetorno;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }


	
	
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		
		filtroNegativadorContrato. adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
		filtroNegativadorContrato. adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");		
		filtroNegativadorContrato. adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.ID, this.getId()));
		return filtroNegativadorContrato; 
	}

	
	
	
	

}
