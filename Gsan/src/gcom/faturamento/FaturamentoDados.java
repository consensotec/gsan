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
package gcom.faturamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoDados implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Short indicadorDebitoConta;

    /** nullable persistent field */
    private Short codigoSetorComercial;

    /** nullable persistent field */
    private Short numeroQuadra;

    /** nullable persistent field */
    private Short indicadorSimulacao;

    /** nullable persistent field */
    private Integer quantidadeEconomia;

    /** nullable persistent field */
    private BigDecimal valorAgua;

    /** nullable persistent field */
    private Integer consumoAgua;

    /** nullable persistent field */
    private BigDecimal valorEsgoto;

    /** nullable persistent field */
    private Integer consumoEsgoto;

    /** nullable persistent field */
    private BigDecimal valorDebitos;

    /** nullable persistent field */
    private BigDecimal valorCreditos;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Categoria categoria;

    /** full constructor */
    public FaturamentoDados(Integer anoMesReferencia, Short indicadorDebitoConta, Short codigoSetorComercial, Short numeroQuadra, Short indicadorSimulacao, Integer quantidadeEconomia, BigDecimal valorAgua, Integer consumoAgua, BigDecimal valorEsgoto, Integer consumoEsgoto, BigDecimal valorDebitos, BigDecimal valorCreditos, Date ultimaAlteracao, gcom.faturamento.FaturamentoGrupo faturamentoGrupo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria) {
        this.anoMesReferencia = anoMesReferencia;
        this.indicadorDebitoConta = indicadorDebitoConta;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.indicadorSimulacao = indicadorSimulacao;
        this.quantidadeEconomia = quantidadeEconomia;
        this.valorAgua = valorAgua;
        this.consumoAgua = consumoAgua;
        this.valorEsgoto = valorEsgoto;
        this.consumoEsgoto = consumoEsgoto;
        this.valorDebitos = valorDebitos;
        this.valorCreditos = valorCreditos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoGrupo = faturamentoGrupo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
    }

    /** default constructor */
    public FaturamentoDados() {
    }

    /** minimal constructor */
    public FaturamentoDados(gcom.faturamento.FaturamentoGrupo faturamentoGrupo, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, Quadra quadra, Localidade localidade, LigacaoAguaSituacao ligacaoAguaSituacao, Categoria categoria) {
        this.faturamentoGrupo = faturamentoGrupo;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.quadra = quadra;
        this.localidade = localidade;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Short getIndicadorDebitoConta() {
        return this.indicadorDebitoConta;
    }

    public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
        this.indicadorDebitoConta = indicadorDebitoConta;
    }

    public Short getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Short codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }


    public Short getIndicadorSimulacao() {
        return this.indicadorSimulacao;
    }

    public void setIndicadorSimulacao(Short indicadorSimulacao) {
        this.indicadorSimulacao = indicadorSimulacao;
    }

    public Integer getQuantidadeEconomia() {
        return this.quantidadeEconomia;
    }

    public void setQuantidadeEconomia(Integer quantidadeEconomia) {
        this.quantidadeEconomia = quantidadeEconomia;
    }

    public BigDecimal getValorAgua() {
        return this.valorAgua;
    }

    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }

    public Integer getConsumoAgua() {
        return this.consumoAgua;
    }

    public void setConsumoAgua(Integer consumoAgua) {
        this.consumoAgua = consumoAgua;
    }

    public BigDecimal getValorEsgoto() {
        return this.valorEsgoto;
    }

    public void setValorEsgoto(BigDecimal valorEsgoto) {
        this.valorEsgoto = valorEsgoto;
    }

    public Integer getConsumoEsgoto() {
        return this.consumoEsgoto;
    }

    public void setConsumoEsgoto(Integer consumoEsgoto) {
        this.consumoEsgoto = consumoEsgoto;
    }

    public BigDecimal getValorDebitos() {
        return this.valorDebitos;
    }

    public void setValorDebitos(BigDecimal valorDebitos) {
        this.valorDebitos = valorDebitos;
    }

    public BigDecimal getValorCreditos() {
        return this.valorCreditos;
    }

    public void setValorCreditos(BigDecimal valorCreditos) {
        this.valorCreditos = valorCreditos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public Quadra getQuadra() {
        return this.quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Short getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Short numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

}
