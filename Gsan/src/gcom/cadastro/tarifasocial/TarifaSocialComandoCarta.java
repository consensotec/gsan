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
package gcom.cadastro.tarifasocial;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */
public class TarifaSocialComandoCarta extends ObjetoGcom implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer codigoTipoCarta;
	private Integer quantidadeDiasComparecimento;
	private Integer quantidadeCartasGeradas;
	private Integer quantidadeDiasDebitoVencimento;
	private Integer anoMesInicialImplantacao;
	private Integer anoMesFinalImplantacao;
	private Short indicadorCriterioCpf;
	private Short indicadorCriterioIdentidade;
	private Short indicadorCriterioContratoEnergia;
	private Short indicadorCriterioDadosEnergia;
	private Short indicadorCriterioProgramaSocial;
	private Short indicadorCriterioSeguroDesemprego;
	private Short indicadorCriterioRendaComprovada;
	private Short indicadorCriterioRendaDeclarada;
	private Short indicadorCriterioQtdeEconomia;
	private Short indicadorCriterioRecadastramento;
	private Date dataSimulacao;
	private Date dataGeracao;
	private Date dataProcessamento;
	private Date dataExecucao;
	private Date ultimaAlteracao;
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Localidade localidade;
	private Usuario usuario;
	
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public Integer getAnoMesFinalImplantacao() {
		return anoMesFinalImplantacao;
	}

	public void setAnoMesFinalImplantacao(Integer anoMesFinalImplantacao) {
		this.anoMesFinalImplantacao = anoMesFinalImplantacao;
	}

	public Integer getAnoMesInicialImplantacao() {
		return anoMesInicialImplantacao;
	}

	public void setAnoMesInicialImplantacao(Integer anoMesInicialImplantacao) {
		this.anoMesInicialImplantacao = anoMesInicialImplantacao;
	}

	public Integer getCodigoTipoCarta() {
		return codigoTipoCarta;
	}

	public void setCodigoTipoCarta(Integer codigoTipoCarta) {
		this.codigoTipoCarta = codigoTipoCarta;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorCriterioContratoEnergia() {
		return indicadorCriterioContratoEnergia;
	}

	public void setIndicadorCriterioContratoEnergia(
			Short indicadorCriterioContratoEnergia) {
		this.indicadorCriterioContratoEnergia = indicadorCriterioContratoEnergia;
	}

	public Short getIndicadorCriterioCpf() {
		return indicadorCriterioCpf;
	}

	public void setIndicadorCriterioCpf(Short indicadorCriterioCpf) {
		this.indicadorCriterioCpf = indicadorCriterioCpf;
	}

	public Short getIndicadorCriterioDadosEnergia() {
		return indicadorCriterioDadosEnergia;
	}

	public void setIndicadorCriterioDadosEnergia(Short indicadorCriterioDadosEnergia) {
		this.indicadorCriterioDadosEnergia = indicadorCriterioDadosEnergia;
	}

	public Short getIndicadorCriterioIdentidade() {
		return indicadorCriterioIdentidade;
	}

	public void setIndicadorCriterioIdentidade(Short indicadorCriterioIdentidade) {
		this.indicadorCriterioIdentidade = indicadorCriterioIdentidade;
	}

	public Short getIndicadorCriterioProgramaSocial() {
		return indicadorCriterioProgramaSocial;
	}

	public void setIndicadorCriterioProgramaSocial(
			Short indicadorCriterioProgramaSocial) {
		this.indicadorCriterioProgramaSocial = indicadorCriterioProgramaSocial;
	}

	public Short getIndicadorCriterioQtdeEconomia() {
		return indicadorCriterioQtdeEconomia;
	}

	public void setIndicadorCriterioQtdeEconomia(Short indicadorCriterioQtdeEconomia) {
		this.indicadorCriterioQtdeEconomia = indicadorCriterioQtdeEconomia;
	}

	public Short getIndicadorCriterioRecadastramento() {
		return indicadorCriterioRecadastramento;
	}

	public void setIndicadorCriterioRecadastramento(
			Short indicadorCriterioRecadastramento) {
		this.indicadorCriterioRecadastramento = indicadorCriterioRecadastramento;
	}

	public Short getIndicadorCriterioRendaComprovada() {
		return indicadorCriterioRendaComprovada;
	}

	public void setIndicadorCriterioRendaComprovada(
			Short indicadorCriterioRendaComprovada) {
		this.indicadorCriterioRendaComprovada = indicadorCriterioRendaComprovada;
	}

	public Short getIndicadorCriterioRendaDeclarada() {
		return indicadorCriterioRendaDeclarada;
	}

	public void setIndicadorCriterioRendaDeclarada(
			Short indicadorCriterioRendaDeclarada) {
		this.indicadorCriterioRendaDeclarada = indicadorCriterioRendaDeclarada;
	}

	public Short getIndicadorCriterioSeguroDesemprego() {
		return indicadorCriterioSeguroDesemprego;
	}

	public void setIndicadorCriterioSeguroDesemprego(
			Short indicadorCriterioSeguroDesemprego) {
		this.indicadorCriterioSeguroDesemprego = indicadorCriterioSeguroDesemprego;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getQuantidadeCartasGeradas() {
		return quantidadeCartasGeradas;
	}

	public void setQuantidadeCartasGeradas(Integer quantidadeCartasGeradas) {
		this.quantidadeCartasGeradas = quantidadeCartasGeradas;
	}

	public Integer getQuantidadeDiasComparecimento() {
		return quantidadeDiasComparecimento;
	}

	public void setQuantidadeDiasComparecimento(Integer quantidadeDiasComparecimento) {
		this.quantidadeDiasComparecimento = quantidadeDiasComparecimento;
	}

	public Integer getQuantidadeDiasDebitoVencimento() {
		return quantidadeDiasDebitoVencimento;
	}

	public void setQuantidadeDiasDebitoVencimento(
			Integer quantidadeDiasDebitoVencimento) {
		this.quantidadeDiasDebitoVencimento = quantidadeDiasDebitoVencimento;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Date getDataSimulacao() {
		return dataSimulacao;
	}

	public void setDataSimulacao(Date dataSimulacao) {
		this.dataSimulacao = dataSimulacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getAnoMesInicialImplantacaoFormatado() {
		String retorno = "";
		if(anoMesInicialImplantacao != null){
			retorno = Util.formatarMesAnoReferencia(anoMesInicialImplantacao);
		}
		return retorno;
	}
	
	public String getAnoMesFinalImplantacaoFormatado() {
		String retorno = "";
		if(anoMesFinalImplantacao != null){
			retorno = Util.formatarMesAnoReferencia(anoMesFinalImplantacao);
		}
		return retorno;
	}
	
	public String getDataLimiteComparecimentoFormatado() {
		String retorno = "";
		if(dataGeracao != null && quantidadeDiasComparecimento != null){
			Date dataLimite = Util.adicionarNumeroDiasDeUmaData(dataGeracao,quantidadeDiasComparecimento);
			retorno = Util.formatarData(dataLimite);
		}
		return retorno;
	}
}
