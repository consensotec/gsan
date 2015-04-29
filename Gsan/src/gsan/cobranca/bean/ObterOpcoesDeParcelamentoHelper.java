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

import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class ObterOpcoesDeParcelamentoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idResolucaoDiretoria; 
	
	private Integer idImovel;
	
	private BigDecimal valorEntradaInformado;
	
	private Integer idLigacaoAguaSituacao;
	
	private Integer idLigacaoEsgotoSituacao;
	
	private Integer idImovelPerfil;
	
	private String inicioIntervaloParcelamento;
	
	private Integer indicadorRestabelecimento;
	
	private Collection colecaoContaValores;
	
	private BigDecimal valorDebitoAtualizado;
	
	private BigDecimal valorTotalMultas;
	
	private BigDecimal valorTotalJurosMora;
	
	private BigDecimal valorTotalAtualizacoesMonetarias;
	
	private Integer numeroReparcelamentoConsecutivos;
	
	private Collection<GuiaPagamento> colecaoGuiaPagamento;
	
	private Usuario usuario;
	
	private BigDecimal valorDebitoACobrarParcelamentoImovel;
	
	private Integer anoMesInicialReferenciaDebito;
	
	private Integer anoMesFinalReferenciaDebito;
	
	private IndicadoresParcelamentoHelper indicadoresParcelamentoHelper;
	
	private BigDecimal valorCreditoARealizarParcelamentoImovel;
	
	//UTILIZADO APENAS VIA EXTRATO DE D�BITO
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores;
	
	private Short calcularEntradaMinima; 
	
	private boolean indicadorApenasCalculoPagAVista;
	
	private BigDecimal valorDebitoAtualizadoMenosDesconto;
	
	public ObterOpcoesDeParcelamentoHelper(){}
	
	public ObterOpcoesDeParcelamentoHelper(Integer resolucaoDiretoria,
			Integer codigoImovel, BigDecimal valorEntradaInformado, Integer situacaoAguaId, Integer situacaoEsgotoId,
			Integer perfilImovel, String inicioIntervaloParcelamento, Integer indicadorRestabelecimento,
			Collection colecaoContaValoresNegociacao, BigDecimal valorDebitoTotalAtualizado, 
			BigDecimal valorTotalMultas, BigDecimal valorTotalJurosMora, BigDecimal valorTotalAtualizacoesMonetarias,
			Integer numeroReparcelamentoConsecutivos, Collection colecaoGuiaPagamento, Usuario  usuario,
			BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal, Integer inicioIntervaloParcelamentoFormatado,
			Integer fimIntervaloParcelamentoFormatado, IndicadoresParcelamentoHelper indicadoresParcelamentoHelper,
			BigDecimal valorCreditoARealizarParcelamentoImovel, boolean indicadorApenasCalculoPagAVista){
		
		this.setIdResolucaoDiretoria(resolucaoDiretoria);
		this.setIdImovel(codigoImovel);
		this.setValorEntradaInformado(valorEntradaInformado);
		this.setIdLigacaoAguaSituacao(situacaoAguaId);
		this.setIdLigacaoEsgotoSituacao(situacaoEsgotoId);
		this.setIdImovelPerfil(perfilImovel);
		this.setInicioIntervaloParcelamento(inicioIntervaloParcelamento);
		this.setIndicadorRestabelecimento(indicadorRestabelecimento);
		this.setColecaoContaValores(colecaoContaValoresNegociacao);
		this.setValorDebitoAtualizado(valorDebitoTotalAtualizado);
		this.setValorTotalMultas(valorTotalMultas);
		this.setValorTotalJurosMora(valorTotalJurosMora);
		this.setValorTotalAtualizacoesMonetarias(valorTotalAtualizacoesMonetarias);
		this.setNumeroReparcelamentoConsecutivos(numeroReparcelamentoConsecutivos);
		this.setColecaoGuiaPagamento(colecaoGuiaPagamento);
		this.setUsuario(usuario);
		this.setValorDebitoACobrarParcelamentoImovel(valorDebitoACobrarParcelamentoImovelBigDecimal);
		this.setAnoMesInicialReferenciaDebito(inicioIntervaloParcelamentoFormatado);
		this.setAnoMesFinalReferenciaDebito(fimIntervaloParcelamentoFormatado);
		this.setIndicadoresParcelamentoHelper(indicadoresParcelamentoHelper);
		this.setValorCreditoARealizarParcelamentoImovel(valorCreditoARealizarParcelamentoImovel);
		this.setIndicadorApenasCalculoPagAVista(indicadorApenasCalculoPagAVista);
	}

	public Integer getAnoMesFinalReferenciaDebito() {
		return anoMesFinalReferenciaDebito;
	}

	public void setAnoMesFinalReferenciaDebito(Integer anoMesFinalReferenciaDebito) {
		this.anoMesFinalReferenciaDebito = anoMesFinalReferenciaDebito;
	}

	public Integer getAnoMesInicialReferenciaDebito() {
		return anoMesInicialReferenciaDebito;
	}

	public void setAnoMesInicialReferenciaDebito(
			Integer anoMesInicialReferenciaDebito) {
		this.anoMesInicialReferenciaDebito = anoMesInicialReferenciaDebito;
	}

	public Collection getColecaoContaValores() {
		return colecaoContaValores;
	}

	public void setColecaoContaValores(Collection colecaoContaValores) {
		this.colecaoContaValores = colecaoContaValores;
	}

	public Collection<GuiaPagamento> getColecaoGuiaPagamento() {
		return colecaoGuiaPagamento;
	}

	public void setColecaoGuiaPagamento(
			Collection<GuiaPagamento> colecaoGuiaPagamento) {
		this.colecaoGuiaPagamento = colecaoGuiaPagamento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	public Integer getIdResolucaoDiretoria() {
		return idResolucaoDiretoria;
	}

	public void setIdResolucaoDiretoria(Integer idResolucaoDiretoria) {
		this.idResolucaoDiretoria = idResolucaoDiretoria;
	}

	public IndicadoresParcelamentoHelper getIndicadoresParcelamentoHelper() {
		return indicadoresParcelamentoHelper;
	}

	public void setIndicadoresParcelamentoHelper(
			IndicadoresParcelamentoHelper indicadoresParcelamentoHelper) {
		this.indicadoresParcelamentoHelper = indicadoresParcelamentoHelper;
	}

	public Integer getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(Integer indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public String getInicioIntervaloParcelamento() {
		return inicioIntervaloParcelamento;
	}

	public void setInicioIntervaloParcelamento(String inicioIntervaloParcelamento) {
		this.inicioIntervaloParcelamento = inicioIntervaloParcelamento;
	}

	public Integer getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}

	public void setNumeroReparcelamentoConsecutivos(
			Integer numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValorDebitoACobrarParcelamentoImovel() {
		return valorDebitoACobrarParcelamentoImovel;
	}

	public void setValorDebitoACobrarParcelamentoImovel(
			BigDecimal valorDebitoACobrarParcelamentoImovel) {
		this.valorDebitoACobrarParcelamentoImovel = valorDebitoACobrarParcelamentoImovel;
	}

	public BigDecimal getValorDebitoAtualizado() {
		return valorDebitoAtualizado;
	}

	public void setValorDebitoAtualizado(BigDecimal valorDebitoAtualizado) {
		this.valorDebitoAtualizado = valorDebitoAtualizado;
	}

	public BigDecimal getValorEntradaInformado() {
		return valorEntradaInformado;
	}

	public void setValorEntradaInformado(BigDecimal valorEntradaInformado) {
		this.valorEntradaInformado = valorEntradaInformado;
	}

	public BigDecimal getValorTotalAtualizacoesMonetarias() {
		return valorTotalAtualizacoesMonetarias;
	}

	public void setValorTotalAtualizacoesMonetarias(
			BigDecimal valorTotalAtualizacoesMonetarias) {
		this.valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias;
	}

	public BigDecimal getValorTotalJurosMora() {
		return valorTotalJurosMora;
	}

	public void setValorTotalJurosMora(BigDecimal valorTotalJurosMora) {
		this.valorTotalJurosMora = valorTotalJurosMora;
	}

	public BigDecimal getValorTotalMultas() {
		return valorTotalMultas;
	}

	public void setValorTotalMultas(BigDecimal valorTotalMultas) {
		this.valorTotalMultas = valorTotalMultas;
	}

	public BigDecimal getValorCreditoARealizarParcelamentoImovel() {
		return valorCreditoARealizarParcelamentoImovel;
	}

	public void setValorCreditoARealizarParcelamentoImovel(
			BigDecimal valorCreditoARealizarParcelamentoImovel) {
		this.valorCreditoARealizarParcelamentoImovel = valorCreditoARealizarParcelamentoImovel;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiaPagamentoValores() {
		return colecaoGuiaPagamentoValores;
	}

	public void setColecaoGuiaPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores) {
		this.colecaoGuiaPagamentoValores = colecaoGuiaPagamentoValores;
	}

	public Short getCalcularEntradaMinima() {
		return calcularEntradaMinima;
	}

	public void setCalcularEntradaMinima(Short calcularEntradaMinima) {
		this.calcularEntradaMinima = calcularEntradaMinima;
	}

	public boolean isIndicadorApenasCalculoPagAVista() {
		return indicadorApenasCalculoPagAVista;
	}

	public void setIndicadorApenasCalculoPagAVista(
			boolean indicadorApenasCalculoPagAVista) {
		this.indicadorApenasCalculoPagAVista = indicadorApenasCalculoPagAVista;
	}

	public BigDecimal getValorDebitoAtualizadoMenosDesconto() {
		return valorDebitoAtualizadoMenosDesconto;
	}

	public void setValorDebitoAtualizadoMenosDesconto(
			BigDecimal valorDebitoAtualizadoMenosDesconto) {
		this.valorDebitoAtualizadoMenosDesconto = valorDebitoAtualizadoMenosDesconto;
	}
	
	
}
