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

import gsan.interceptor.ObjetoTransacao;
import gsan.spcserasa.FiltroNegativadorMovimento;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorMovimento extends ObjetoTransacao implements Serializable {

	public Filtro retornaFiltro() {
		FiltroNegativadorMovimento filtroNegativadorExclusaoMotivo = new FiltroNegativadorMovimento();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public static short CODIGO_MOVIMENTO_INCLUSAO = 1;
	
	public static short CODIGO_MOVIMENTO_EXCLUSAO = 2;
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short codigoMovimento;

    /** nullable persistent field */
    private Date dataEnvio;

    /** persistent field */
    private Date dataProcessamentoEnvio;

    /** nullable persistent field */
    private Date dataRetorno;

    /** nullable persistent field */
    private Date dataProcessamentoRetorno;

    /** nullable persistent field */
    private Integer numeroSequencialEnvio;

    /** nullable persistent field */
    private Integer numeroSequencialRetorno;

    /** nullable persistent field */
    private Integer numeroRegistrosEnvio;

    /** nullable persistent field */
    private Integer numeroRegistrosRetorno;

    /** nullable persistent field */
    private BigDecimal valorTotalEnvio;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gsan.cobranca.Negativador negativador;

    /** persistent field */
    private gsan.cobranca.NegativacaoComando negativacaoComando;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorMovimento(Integer id, short codigoMovimento, Date dataEnvio, Date dataProcessamentoEnvio, Date dataRetorno, Date dataProcessamentoRetorno, Integer numeroSequencialEnvio, Integer numeroSequencialRetorno, Integer numeroRegistrosEnvio, Integer numeroRegistrosRetorno, BigDecimal valorTotalEnvio, Date ultimaAlteracao, gsan.cobranca.Negativador negativador, gsan.cobranca.NegativacaoComando negativacaoComando, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoMovimento = codigoMovimento;
        this.dataEnvio = dataEnvio;
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
        this.dataRetorno = dataRetorno;
        this.dataProcessamentoRetorno = dataProcessamentoRetorno;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.numeroRegistrosEnvio = numeroRegistrosEnvio;
        this.numeroRegistrosRetorno = numeroRegistrosRetorno;
        this.valorTotalEnvio = valorTotalEnvio;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativacaoComando = negativacaoComando;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorMovimento() {
    }

    /** minimal constructor */
    public NegativadorMovimento(Integer id, short codigoMovimento, Date dataProcessamentoEnvio, gsan.cobranca.Negativador negativador, gsan.cobranca.NegativacaoComando negativacaoComando, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoMovimento = codigoMovimento;
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
        this.negativador = negativador;
        this.negativacaoComando = negativacaoComando;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getCodigoMovimento() {
        return this.codigoMovimento;
    }

    public void setCodigoMovimento(short codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public Date getDataEnvio() {
        return this.dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataProcessamentoEnvio() {
        return this.dataProcessamentoEnvio;
    }

    public void setDataProcessamentoEnvio(Date dataProcessamentoEnvio) {
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
    }

    public Date getDataRetorno() {
        return this.dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Date getDataProcessamentoRetorno() {
        return this.dataProcessamentoRetorno;
    }

    public void setDataProcessamentoRetorno(Date dataProcessamentoRetorno) {
        this.dataProcessamentoRetorno = dataProcessamentoRetorno;
    }

    public Integer getNumeroSequencialEnvio() {
        return this.numeroSequencialEnvio;
    }

    public void setNumeroSequencialEnvio(Integer numeroSequencialEnvio) {
        this.numeroSequencialEnvio = numeroSequencialEnvio;
    }

    public Integer getNumeroSequencialRetorno() {
        return this.numeroSequencialRetorno;
    }

    public void setNumeroSequencialRetorno(Integer numeroSequencialRetorno) {
        this.numeroSequencialRetorno = numeroSequencialRetorno;
    }

    public Integer getNumeroRegistrosEnvio() {
        return this.numeroRegistrosEnvio;
    }

    public void setNumeroRegistrosEnvio(Integer numeroRegistrosEnvio) {
        this.numeroRegistrosEnvio = numeroRegistrosEnvio;
    }

    public Integer getNumeroRegistrosRetorno() {
        return this.numeroRegistrosRetorno;
    }

    public void setNumeroRegistrosRetorno(Integer numeroRegistrosRetorno) {
        this.numeroRegistrosRetorno = numeroRegistrosRetorno;
    }

    public BigDecimal getValorTotalEnvio() {
        return this.valorTotalEnvio;
    }

    public void setValorTotalEnvio(BigDecimal valorTotalEnvio) {
        this.valorTotalEnvio = valorTotalEnvio;
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

    public gsan.cobranca.NegativacaoComando getNegativacaoComando() {
        return this.negativacaoComando;
    }

    public void setNegativacaoComando(gsan.cobranca.NegativacaoComando negativacaoComando) {
        this.negativacaoComando = negativacaoComando;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
