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
package gsan.cobranca.bean;

import gsan.cobranca.ResolucaoDiretoria;
import gsan.cobranca.parcelamento.ParcelamentoPerfil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class DeterminarValorDescontoPagamentoAVistaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper;
	
	private ParcelamentoPerfil parcelamentoPerfil;
	
	private BigDecimal valorDescontoAcrecismosImpotualidade;
	
	private BigDecimal valorDescontoInatividade;
	
	private BigDecimal valorDescontoAntiguidade;
	
	private BigDecimal valorDescontoSancoes;
	
	private BigDecimal valorDescontoTarifaSocial;
	
	private Integer anoMesLimiteMaximo;
	
	private ResolucaoDiretoria resolucaoDiretoria;
	
	private BigDecimal valorCreditoARealizar;
	
	private BigDecimal valorDescontoInatividadeAVista;
	
	private Collection colecaoContasParaParcelamento;
	
	private BigDecimal valorDescontoDebitoTipoPagAVista;
	   
    public DeterminarValorDescontoPagamentoAVistaHelper(ObterOpcoesDeParcelamentoHelper helper, ParcelamentoPerfil parcelamentoPerfil,
        BigDecimal valorDescontoAcrecismosImpotualidade, BigDecimal valorDescontoInatividade,
        BigDecimal valorDescontoAntiguidade, BigDecimal valorDescontoSancoes,
        BigDecimal valorDescontoTarifaSocial, Integer anoMesLimiteMaximo,
        ResolucaoDiretoria resolucaoDiretoria, BigDecimal valorCreditoARealizar,
        BigDecimal valorDescontoInatividadeAVista,Collection colecaoContasParaParcelamento){
       
        this.setObterOpcoesDeParcelamentoHelper(helper);
        this.setParcelamentoPerfil(parcelamentoPerfil);
        this.setValorDescontoAcrecismosImpotualidade(valorDescontoAcrecismosImpotualidade);
        this.setValorDescontoInatividade(valorDescontoInatividade);
        this.setValorDescontoAntiguidade(valorDescontoAntiguidade);
        this.setValorDescontoSancoes(valorDescontoSancoes);
        this.setValorDescontoTarifaSocial(valorDescontoTarifaSocial);
        this.setAnoMesLimiteMaximo(anoMesLimiteMaximo);
        this.setResolucaoDiretoria(resolucaoDiretoria);
        this.setValorCreditoARealizar(valorCreditoARealizar);
        this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
        this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
    }
    
	
	public DeterminarValorDescontoPagamentoAVistaHelper(
			ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper,
			ParcelamentoPerfil parcelamentoPerfil,
			BigDecimal valorDescontoAcrecismosImpotualidade,
			BigDecimal valorDescontoInatividade,
			BigDecimal valorDescontoAntiguidade,
			BigDecimal valorDescontoSancoes,
			BigDecimal valorDescontoTarifaSocial, Integer anoMesLimiteMaximo,
			ResolucaoDiretoria resolucaoDiretoria,
			BigDecimal valorCreditoARealizar,
			BigDecimal valorDescontoInatividadeAVista,
			Collection colecaoContasParaParcelamento,
			BigDecimal valorDescontoDebitoTipoPagAVista) {
		super();
		this.obterOpcoesDeParcelamentoHelper = obterOpcoesDeParcelamentoHelper;
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
		this.valorDescontoInatividade = valorDescontoInatividade;
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
		this.valorDescontoSancoes = valorDescontoSancoes;
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
		this.anoMesLimiteMaximo = anoMesLimiteMaximo;
		this.resolucaoDiretoria = resolucaoDiretoria;
		this.valorCreditoARealizar = valorCreditoARealizar;
		this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
		this.valorDescontoDebitoTipoPagAVista = valorDescontoDebitoTipoPagAVista;
	}


	public BigDecimal getValorDescontoDebitoTipoPagAVista() {
		return valorDescontoDebitoTipoPagAVista;
	}

	public void setValorDescontoDebitoTipoPagAVista(
			BigDecimal valorDescontoDebitoTipoPagAVista) {
		this.valorDescontoDebitoTipoPagAVista = valorDescontoDebitoTipoPagAVista;
	}

	public Collection getColecaoContasParaParcelamento() {
		return colecaoContasParaParcelamento;
	}

	public void setColecaoContasParaParcelamento(Collection colecaoContasParaParcelamento) {
		this.colecaoContasParaParcelamento = colecaoContasParaParcelamento;
	}

	public Integer getAnoMesLimiteMaximo() {
		return anoMesLimiteMaximo;
	}

	public void setAnoMesLimiteMaximo(Integer anoMesLimiteMaximo) {
		this.anoMesLimiteMaximo = anoMesLimiteMaximo;
	}

	public ObterOpcoesDeParcelamentoHelper getObterOpcoesDeParcelamentoHelper() {
		return obterOpcoesDeParcelamentoHelper;
	}

	public void setObterOpcoesDeParcelamentoHelper(
			ObterOpcoesDeParcelamentoHelper obterOpcoesDeParcelamentoHelper) {
		this.obterOpcoesDeParcelamentoHelper = obterOpcoesDeParcelamentoHelper;
	}

	public ParcelamentoPerfil getParcelamentoPerfil() {
		return parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public BigDecimal getValorDescontoAcrecismosImpotualidade() {
		return valorDescontoAcrecismosImpotualidade;
	}

	public void setValorDescontoAcrecismosImpotualidade(
			BigDecimal valorDescontoAcrecismosImpotualidade) {
		this.valorDescontoAcrecismosImpotualidade = valorDescontoAcrecismosImpotualidade;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public BigDecimal getValorDescontoInatividade() {
		return valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public BigDecimal getValorDescontoSancoes() {
		return valorDescontoSancoes;
	}

	public void setValorDescontoSancoes(BigDecimal valorDescontoSancoes) {
		this.valorDescontoSancoes = valorDescontoSancoes;
	}

	public BigDecimal getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public BigDecimal getValorCreditoARealizar() {
		return valorCreditoARealizar;
	}

	public void setValorCreditoARealizar(BigDecimal valorCreditoARealizar) {
		this.valorCreditoARealizar = valorCreditoARealizar;
	}

	public BigDecimal getValorDescontoInatividadeAVista() {
		return valorDescontoInatividadeAVista;
	}

	public void setValorDescontoInatividadeAVista(
			BigDecimal valorDescontoInatividadeAVista) {
		this.valorDescontoInatividadeAVista = valorDescontoInatividadeAVista;
	}
	
	
}
