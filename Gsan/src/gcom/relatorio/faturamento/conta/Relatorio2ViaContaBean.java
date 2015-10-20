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
package gcom.relatorio.faturamento.conta;

import gcom.faturamento.bean.EmitirContaHelper;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0482] Emitir 2� Via de Conta
 * @author Vivianne Sousa
 * @date 15/09/2006
 */
public class Relatorio2ViaContaBean implements RelatorioBean {
	
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorio2ViaContaDetailBean;
	
	private JRBeanCollectionDataSource arrayJRBoleto;
	private ArrayList arrayRelatorio2ViaContaBoletoBancarioBean;
	private String boleto;
	
	//Linha 1
	private String empresa;
	private String enderecoEmpresa;
	//Linha 2 
	private String descricaoLocalidade;
	//Linha 3 
	private String matriculaImovelFormatada;
	private String dataVencimento;
	//Linha 4 
	private String nomeCliente;
	//Linha 5 
	private String enderecoImovel;
	private String fatura;
	//Linha 6 
	private String inscricao;
	//Linha 7
	private String idClienteResponsavel;
	private String enderecoClienteResponsavel;
	private String enderecoClienteResponsavelLinha2;
	private String descricaoAguaSituacao;
	private String descricaoEsgotoSituacao;
	//Linha 9
	private String dadosConsumoMes1;
	private String dadosConsumoMes4;
	//Linha 10
	private String dadosConsumoMes2;
	private String dadosConsumoMes5;
	private String leituraAnterior;
	private String leituraAtual;
	private String consumoFaturamento;
	private String diasConsumo;
	private String consumoMedioDiario;
	//Linha 11
	private String dadosConsumoMes3;
	private String dadosConsumoMes6;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	//Linha 12
	private String descricaoTipoConsumo;
	private String descricaoAnormalidadeConsumo;
	//Linha 13
	private String quantidadeEconomiaConta;
	private String consumoEconomia;
	private String codigoAuxiliarString;
	private String mesagemConsumoString;

	//Linha 17
	private String valorContaString;
	//Linha 18
	private String primeiraParte;
	//Linha 19
	private String segundaParte;
	//Linha 20
	private String terceiraParte;
	//Linha 21
	private String nomeGerenciaRegional;
	private String mesAnoFormatado;
	//Linha 22
	private String numeroIndiceTurbidez;
	private String numeroCloroResidual;
	
	//Linha 24
	private String representacaoNumericaCodBarraFormatada;
	//Linha 25
	private String representacaoNumericaCodBarraSemDigito;
	//Linha28
	private String dataValidade;
	//Linha 31
	private String grupo;	
	private String firma;
	
	private String idConta;
	
	private String totalPaginasRelatorio;
	
	private String contaSemCodigoBarras;
	
	private String numeroDocumento;
	private String numeroCpfCnpj;
	
	private String codigoDebitoAutomatico;
	
	//s� aparece na CAERN
	private String rotaEntrega;
	private String debitoCreditoSituacaoAtualConta;
	private String contaPaga;
	
	private String numeroNitrato;
	private String numeroColiformesTotais;
	private String numeroPH;
	private String coliformesTermotolerantes;
	private String numeroPh;
	private String indiceDurezaTotal;
	private String indiceNumeroCor;
	private String corPadrao;
	private String turbidezPadrao;
	private String phPadrao;
	private String durezaPadrao;
	private String cloroPadrao;
	private String cloriformePadrao;
	
	private String pisBaseCalculo;
	private String pisPercentual;
	private String pisValorImposto;
	private String cofinsBaseCalculo;
	private String cofinsPercentual;
	private String cofinsValorImposto;
	
	public Relatorio2ViaContaBean(
			    String indicadorPrimeiraPagina, 
			    Collection colecaoDetail,
	    		String descricaoLocalidade,
	    		String matriculaImovelFormatada,
	    		String dataVencimento,
	    		String nomeCliente,
	    		String enderecoImovel,
	    		String fatura,
	    		String inscricao,
	    		String idClienteResponsavel,
	    		String enderecoClienteResponsavel,
	    		String descricaoAguaSituacao,
	    		String descricaoEsgotoSituacao,
	    		String dadosConsumoMes1,
	    		String dadosConsumoMes4,
	    		String dadosConsumoMes2,
	    		String dadosConsumoMes5,
	    		String leituraAnterior,
	    		String leituraAtual,
	    		String consumoFaturamento,
	    		String diasConsumo,
	    		String consumoMedioDiario,
	    		String dadosConsumoMes3,
	    		String dadosConsumoMes6,
	    		String dataLeituraAnterior,
	    		String dataLeituraAtual,
	    		String descricaoTipoConsumo,
	    		String descricaoAnormalidadeConsumo,
	    		String quantidadeEconomiaConta,
	    		String consumoEconomia,
	    		String codigoAuxiliarString,
	    		String mesagemConsumoString,
	    		String valorContaString,
	    		String primeiraParte,
	    		String segundaParte,
	    		String terceiraParte,
	    		String nomeGerenciaRegional,
	    		String mesAnoFormatado,
	    		String numeroIndiceTurbidez,
	    		String numeroCloroResidual,	
	    		String numeroNitrato,
	    		String numeroColiformesTotais,
	    		String numeroPH,
	    		String representacaoNumericaCodBarraFormatada,
	    		String representacaoNumericaCodBarraSemDigito,
	    		String dataValidade,
	    		String grupo,	
	    		String firma,
	    		String totalPaginasRelatorio,
	    		String idConta,
	    		String rotaEntrega,
	    		String contaSemCodigoBarras,
	    		String debitoCreditoSituacaoAtualConta,
	    		String contaPaga,
	    		String enderecoClienteResponsavelLinha2,
	    		Collection colecaoBoleto) {
	    	
		    this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
			this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
			this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
			this.arrayJRDetail = new JRBeanCollectionDataSource(
					this.arrayRelatorio2ViaContaDetailBean);
		  
	    	this.descricaoLocalidade = descricaoLocalidade;
	    	this.matriculaImovelFormatada = matriculaImovelFormatada;
	    	this.dataVencimento = dataVencimento;
	    	this.nomeCliente = nomeCliente;
	    	this.enderecoImovel = enderecoImovel;
	    	this.fatura = fatura;
	    	this.inscricao = inscricao;
	    	this.idClienteResponsavel = idClienteResponsavel;
	    	this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	    	this.descricaoAguaSituacao = descricaoAguaSituacao;
	    	this.descricaoEsgotoSituacao = descricaoEsgotoSituacao;
	    	this.dadosConsumoMes1 = dadosConsumoMes1;
	    	this.dadosConsumoMes4 = dadosConsumoMes4;
	    	this.dadosConsumoMes2 = dadosConsumoMes2;
	    	this.dadosConsumoMes5 = dadosConsumoMes5;
	    	this.leituraAnterior = leituraAnterior;
	    	this.leituraAtual = leituraAtual;
	    	this.consumoFaturamento = consumoFaturamento;
	    	this.diasConsumo = diasConsumo;
	    	this.consumoMedioDiario = consumoMedioDiario;
	    	this.dadosConsumoMes3 = dadosConsumoMes3;
	    	this.dadosConsumoMes6 = dadosConsumoMes6;
	    	this.dataLeituraAnterior = dataLeituraAnterior;
	    	this.dataLeituraAtual = dataLeituraAtual;
	    	this.descricaoTipoConsumo = descricaoTipoConsumo;
	    	this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	    	this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	    	this.consumoEconomia = consumoEconomia;
	    	this.codigoAuxiliarString = codigoAuxiliarString;
	    	this.mesagemConsumoString = mesagemConsumoString;
	    	this.valorContaString = valorContaString;
	    	this.primeiraParte = primeiraParte;
	    	this.segundaParte = segundaParte;
	    	this.terceiraParte = terceiraParte;
	    	this.nomeGerenciaRegional = nomeGerenciaRegional;
	    	this.mesAnoFormatado = mesAnoFormatado;
	    	this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	    	this.numeroCloroResidual = numeroCloroResidual;
	    	this.numeroNitrato = numeroNitrato;
	    	this.numeroColiformesTotais = numeroColiformesTotais;
	    	this.numeroPH = numeroPH;
	    	this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	    	this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	    	this.dataValidade = dataValidade;
	    	this.grupo = grupo;	
	    	this.firma = firma;
	    	this.totalPaginasRelatorio = totalPaginasRelatorio;
	    	this.idConta = idConta;
	    	this.rotaEntrega = rotaEntrega;
	    	this.contaSemCodigoBarras = contaSemCodigoBarras;
	    	this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	    	this.contaPaga = contaPaga;
	    	this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
	    	
	    	this.arrayRelatorio2ViaContaBoletoBancarioBean = new ArrayList();
			this.arrayRelatorio2ViaContaBoletoBancarioBean.addAll(colecaoBoleto);
			this.arrayJRBoleto = new JRBeanCollectionDataSource(
					this.arrayRelatorio2ViaContaBoletoBancarioBean);
	    }	

	public Relatorio2ViaContaBean(EmitirContaHelper emitirContaHelper,
			int indicadorPrimeiraPagina,
			Collection colecaoDetail,
			String dataVencimentoFormatada,
			String enderecoClienteResponsavel,
			int totalPaginasRelatorio,
			String codigoRota,
			String debitoCreditoSituacaoAtualConta,
			String contaPaga,
			String enderecoClienteResponsavelLinha2,
			Collection colecaoBoleto,
			int boleto){
		
		this.indicadorPrimeiraPagina = "" + indicadorPrimeiraPagina;
		this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
		this.arrayRelatorio2ViaContaDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaDetailBean);
		this.descricaoLocalidade = emitirContaHelper.getDescricaoLocalidade();
		this.matriculaImovelFormatada = emitirContaHelper.getMatriculaImovelFormatada();
		this.dataVencimento = dataVencimentoFormatada;
		this.nomeCliente = emitirContaHelper.getNomeCliente();
		this.enderecoImovel = emitirContaHelper.getEnderecoImovel();
		this.fatura = emitirContaHelper.getFatura();
		this.inscricao = emitirContaHelper.getInscricaoImovel();
		this.idClienteResponsavel = emitirContaHelper.getIdClienteResponsavel();
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.descricaoAguaSituacao = emitirContaHelper.getDescricaoLigacaoAguaSituacao();
		this.descricaoEsgotoSituacao = emitirContaHelper.getDescricaoLigacaoEsgotoSituacao();
		this.dadosConsumoMes1 = emitirContaHelper.getDadosConsumoMes1();
		this.dadosConsumoMes4 = emitirContaHelper.getDadosConsumoMes4();
		this.dadosConsumoMes2 = emitirContaHelper.getDadosConsumoMes2();
		this.dadosConsumoMes5 = emitirContaHelper.getDadosConsumoMes5();
		this.leituraAnterior = emitirContaHelper.getLeituraAnterior();
		this.leituraAtual = emitirContaHelper.getLeituraAtual();
		this.consumoFaturamento = emitirContaHelper.getConsumoFaturamento();
		this.diasConsumo = emitirContaHelper.getDiasConsumo();
		this.consumoMedioDiario = emitirContaHelper.getConsumoMedioDiario();
		this.dadosConsumoMes3 = emitirContaHelper.getDadosConsumoMes3();
		this.dadosConsumoMes6 = emitirContaHelper.getDadosConsumoMes6();
		this.dataLeituraAnterior = emitirContaHelper.getDataLeituraAnterior();
		this.dataLeituraAtual = emitirContaHelper.getDataLeituraAtual();
		this.descricaoTipoConsumo = emitirContaHelper.getDescricaoTipoConsumo();
		this.descricaoAnormalidadeConsumo = emitirContaHelper.getDescricaoAnormalidadeConsumo();
		this.quantidadeEconomiaConta = emitirContaHelper.getQuantidadeEconomiaConta();
		this.consumoEconomia = emitirContaHelper.getConsumoEconomia();
		this.codigoAuxiliarString = emitirContaHelper.getCodigoAuxiliarString();
		this.mesagemConsumoString = emitirContaHelper.getMensagemConsumoString();
		this.valorContaString = emitirContaHelper.getValorContaString();
		this.primeiraParte = emitirContaHelper.getPrimeiraParte();
		this.segundaParte = emitirContaHelper.getSegundaParte();
		this.terceiraParte = emitirContaHelper.getTerceiraParte();
		this.nomeGerenciaRegional = emitirContaHelper.getNomeGerenciaRegional();
		this.mesAnoFormatado = emitirContaHelper.getMesAnoFormatado();
		this.numeroIndiceTurbidez = emitirContaHelper.getNumeroIndiceTurbidez();
		this.numeroCloroResidual = emitirContaHelper.getNumeroCloroResidual();
		this.numeroNitrato = emitirContaHelper.getNumeroNitrato();
		this.numeroColiformesTotais = emitirContaHelper.getValorMedioColiformesTotais();
		this.numeroPH = emitirContaHelper.getValorMedioPh();
		this.representacaoNumericaCodBarraFormatada = emitirContaHelper.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito = emitirContaHelper.getRepresentacaoNumericaCodBarraSemDigito();
		this.dataValidade = emitirContaHelper.getDataValidade();
		this.grupo = emitirContaHelper.getIdFaturamentoGrupo().toString();
		this.firma = emitirContaHelper.getIdEmpresa().toString();
		this.totalPaginasRelatorio = "" + totalPaginasRelatorio;
		this.idConta = emitirContaHelper.getIdConta().toString();
		this.rotaEntrega = codigoRota;
		this.contaSemCodigoBarras = emitirContaHelper.getContaSemCodigoBarras();
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
		this.contaPaga = contaPaga;
		this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
		this.arrayRelatorio2ViaContaBoletoBancarioBean = new ArrayList();
		this.arrayRelatorio2ViaContaBoletoBancarioBean.addAll(colecaoBoleto);
		this.arrayJRBoleto = new JRBeanCollectionDataSource(
				this.arrayRelatorio2ViaContaBoletoBancarioBean);
		this.boleto = "" + boleto;
		this.numeroDocumento = (""+emitirContaHelper.getAmReferencia() ) +
							   (emitirContaHelper.getIdImovel().toString());
		
		String documentoCpfCnpj = "";
		if (emitirContaHelper.getCpf() != null && !emitirContaHelper.getCpf().equals("")){
			documentoCpfCnpj = Util.formatarCpf(emitirContaHelper.getCpf()) ;
		}else if (emitirContaHelper.getCnpj() != null && !emitirContaHelper.getCnpj().equals("")){
			documentoCpfCnpj = Util.formatarCnpj(emitirContaHelper.getCnpj());
		}
		
		this.numeroCpfCnpj = documentoCpfCnpj;
		this.coliformesTermotolerantes = emitirContaHelper.getColiformesTermotolerantes();
		this.numeroPh = emitirContaHelper.getNumeroPh();
		this.indiceDurezaTotal = emitirContaHelper.getIndiceDurezaTotal();
		this.indiceNumeroCor = emitirContaHelper.getIndiceNumeroCor();
		
		this.corPadrao = emitirContaHelper.getCorPadrao();
		this.turbidezPadrao = emitirContaHelper.getTurbidezPadrao();
		this.phPadrao = emitirContaHelper.getPhPadrao();
		this.durezaPadrao = emitirContaHelper.getDurezaPadrao();
		this.cloroPadrao = emitirContaHelper.getCloroPadrao();
		this.cloriformePadrao = emitirContaHelper.getCloriformePadrao();
	}	
	
	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorio2ViaContaDetailBean() {
		return arrayRelatorio2ViaContaDetailBean;
	}

	public void setArrayRelatorio2ViaContaDetailBean(
			ArrayList arrayRelatorio2ViaContaDetailBean) {
		this.arrayRelatorio2ViaContaDetailBean = arrayRelatorio2ViaContaDetailBean;
	}

	public String getIndicadorPrimeiraPagina() {
		return indicadorPrimeiraPagina;
	}

	public void setIndicadorPrimeiraPagina(String indicadorPrimeiraPagina) {
		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
	}

	public String getCodigoAuxiliarString() {
		return codigoAuxiliarString;
	}

	public void setCodigoAuxiliarString(String codigoAuxiliarString) {
		this.codigoAuxiliarString = codigoAuxiliarString;
	}

	public String getConsumoEconomia() {
		return consumoEconomia;
	}

	public void setConsumoEconomia(String consumoEconomia) {
		this.consumoEconomia = consumoEconomia;
	}

	public String getConsumoFaturamento() {
		return consumoFaturamento;
	}

	public void setConsumoFaturamento(String consumoFaturamento) {
		this.consumoFaturamento = consumoFaturamento;
	}

	public String getConsumoMedioDiario() {
		return consumoMedioDiario;
	}

	public void setConsumoMedioDiario(String consumoMedioDiario) {
		this.consumoMedioDiario = consumoMedioDiario;
	}

	public String getDadosConsumoMes1() {
		return dadosConsumoMes1;
	}

	public void setDadosConsumoMes1(String dadosConsumoMes1) {
		this.dadosConsumoMes1 = dadosConsumoMes1;
	}

	public String getDadosConsumoMes2() {
		return dadosConsumoMes2;
	}

	public void setDadosConsumoMes2(String dadosConsumoMes2) {
		this.dadosConsumoMes2 = dadosConsumoMes2;
	}

	public String getDadosConsumoMes3() {
		return dadosConsumoMes3;
	}

	public void setDadosConsumoMes3(String dadosConsumoMes3) {
		this.dadosConsumoMes3 = dadosConsumoMes3;
	}

	public String getDadosConsumoMes4() {
		return dadosConsumoMes4;
	}

	public void setDadosConsumoMes4(String dadosConsumoMes4) {
		this.dadosConsumoMes4 = dadosConsumoMes4;
	}

	public String getDadosConsumoMes5() {
		return dadosConsumoMes5;
	}

	public void setDadosConsumoMes5(String dadosConsumoMes5) {
		this.dadosConsumoMes5 = dadosConsumoMes5;
	}

	public String getDadosConsumoMes6() {
		return dadosConsumoMes6;
	}

	public void setDadosConsumoMes6(String dadosConsumoMes6) {
		this.dadosConsumoMes6 = dadosConsumoMes6;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricaoAguaSituacao() {
		return descricaoAguaSituacao;
	}

	public void setDescricaoAguaSituacao(String descricaoAguaSituacao) {
		this.descricaoAguaSituacao = descricaoAguaSituacao;
	}

	public String getDescricaoAnormalidadeConsumo() {
		return descricaoAnormalidadeConsumo;
	}

	public void setDescricaoAnormalidadeConsumo(String descricaoAnormalidadeConsumo) {
		this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	}

	public String getDescricaoEsgotoSituacao() {
		return descricaoEsgotoSituacao;
	}

	public void setDescricaoEsgotoSituacao(String descricaoEsgotoSituacao) {
		this.descricaoEsgotoSituacao = descricaoEsgotoSituacao;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoTipoConsumo() {
		return descricaoTipoConsumo;
	}

	public void setDescricaoTipoConsumo(String descricaoTipoConsumo) {
		this.descricaoTipoConsumo = descricaoTipoConsumo;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}

	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getFatura() {
		return fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getIdClienteResponsavel() {
		return idClienteResponsavel;
	}

	public void setIdClienteResponsavel(String idClienteResponsavel) {
		this.idClienteResponsavel = idClienteResponsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getMatriculaImovelFormatada() {
		return matriculaImovelFormatada;
	}

	public void setMatriculaImovelFormatada(String matriculaImovelFormatada) {
		this.matriculaImovelFormatada = matriculaImovelFormatada;
	}

	public String getMesagemConsumoString() {
		return mesagemConsumoString;
	}

	public void setMesagemConsumoString(String mesagemConsumoString) {
		this.mesagemConsumoString = mesagemConsumoString;
	}

	public String getMesAnoFormatado() {
		return mesAnoFormatado;
	}

	public void setMesAnoFormatado(String mesAnoFormatado) {
		this.mesAnoFormatado = mesAnoFormatado;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNumeroCloroResidual() {
		return numeroCloroResidual;
	}

	public void setNumeroCloroResidual(String numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}

	public String getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}

	public void setNumeroIndiceTurbidez(String numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}
	
	public String getNumeroColiformesTotais() {
		return numeroColiformesTotais;
	}
	
	public void setNumeroColiformesTotais(String numeroColiformesTotais) {
		this.numeroColiformesTotais = numeroColiformesTotais;
	}
	
	public String getNumeroNitrato() {
		return numeroNitrato;
	}
	
	public void setNumeroNitrato(String numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}
	
	public String getNumeroPH() {
		return numeroPH;
	}

	public void setNumeroPH(String numeroPH) {
		this.numeroPH = numeroPH;
	}

	public String getPrimeiraParte() {
		return primeiraParte;
	}

	public void setPrimeiraParte(String primeiraParte) {
		this.primeiraParte = primeiraParte;
	}

	public String getQuantidadeEconomiaConta() {
		return quantidadeEconomiaConta;
	}

	public void setQuantidadeEconomiaConta(String quantidadeEconomiaConta) {
		this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	}

	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public String getSegundaParte() {
		return segundaParte;
	}

	public void setSegundaParte(String segundaParte) {
		this.segundaParte = segundaParte;
	}

	public String getTerceiraParte() {
		return terceiraParte;
	}

	public void setTerceiraParte(String terceiraParte) {
		this.terceiraParte = terceiraParte;
	}

	public String getValorContaString() {
		return valorContaString;
	}

	public void setValorContaString(String valorContaString) {
		this.valorContaString = valorContaString;
	}

	public String getTotalPaginasRelatorio() {
		return totalPaginasRelatorio;
	}

	public void setTotalPaginasRelatorio(String totalPaginasRelatorio) {
		this.totalPaginasRelatorio = totalPaginasRelatorio;
	}

	public String getIdConta() {
		return idConta;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getRotaEntrega() {
		return rotaEntrega;
	}

	public void setRotaEntrega(String rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}

	public String getContaSemCodigoBarras() {
		return contaSemCodigoBarras;
	}

	public void setContaSemCodigoBarras(String contaSemCodigoBarras) {
		this.contaSemCodigoBarras = contaSemCodigoBarras;
	}

	public String getDebitoCreditoSituacaoAtualConta() {
		return debitoCreditoSituacaoAtualConta;
	}

	public void setDebitoCreditoSituacaoAtualConta(
			String debitoCreditoSituacaoAtualConta) {
		this.debitoCreditoSituacaoAtualConta = debitoCreditoSituacaoAtualConta;
	}

	public String getContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(String contaPaga) {
		this.contaPaga = contaPaga;
	}

	public String getEnderecoClienteResponsavelLinha2() {
		return enderecoClienteResponsavelLinha2;
	}

	public void setEnderecoClienteResponsavelLinha2(
			String enderecoClienteResponsavelLinha2) {
		this.enderecoClienteResponsavelLinha2 = enderecoClienteResponsavelLinha2;
	}

	public JRBeanCollectionDataSource getArrayJRBoleto() {
		return arrayJRBoleto;
	}

	public void setArrayJRBoleto(JRBeanCollectionDataSource arrayJRBoleto) {
		this.arrayJRBoleto = arrayJRBoleto;
	}

	public ArrayList getArrayRelatorio2ViaContaBoletoBancarioBean() {
		return arrayRelatorio2ViaContaBoletoBancarioBean;
	}

	public void setArrayRelatorio2ViaContaBoletoBancarioBean(
			ArrayList arrayRelatorio2ViaContaBoletoBancarioBean) {
		this.arrayRelatorio2ViaContaBoletoBancarioBean = arrayRelatorio2ViaContaBoletoBancarioBean;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroCpfCnpj() {
		return numeroCpfCnpj;
	}

	public void setNumeroCpfCnpj(String numeroCpfCnpj) {
		this.numeroCpfCnpj = numeroCpfCnpj;
	}

	public String getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}

	public void setCodigoDebitoAutomatico(String codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public String getColiformesTermotolerantes() {
		return coliformesTermotolerantes;
	}
	
	public void setColiformesTermotolerantes(String coliformesTermotolerantes) {
		this.coliformesTermotolerantes = coliformesTermotolerantes;
	}
	
	public String getNumeroPh() {
		return numeroPh;
	}

	public void setNumeroPh(String numeroPh) {
		this.numeroPh = numeroPh;
	}

	public String getIndiceDurezaTotal() {
		return indiceDurezaTotal;
	}
	
	public void setIndiceDurezaTotal(String indiceDurezaTotal) {
		this.indiceDurezaTotal = indiceDurezaTotal;
	}

	public String getIndiceNumeroCor() {
		return indiceNumeroCor;
	}

	public void setIndiceNumeroCor(String indiceNumeroCor) {
		this.indiceNumeroCor = indiceNumeroCor;
	}

	public String getCorPadrao() {
		return corPadrao;
	}

	public void setCorPadrao(String corPadrao) {
		this.corPadrao = corPadrao;
	}

	public String getTurbidezPadrao() {
		return turbidezPadrao;
	}

	public void setTurbidezPadrao(String turbidezPadrao) {
		this.turbidezPadrao = turbidezPadrao;
	}

	public String getPhPadrao() {
		return phPadrao;
	}

	public void setPhPadrao(String phPadrao) {
		this.phPadrao = phPadrao;
	}

	public String getDurezaPadrao() {
		return durezaPadrao;
	}

	public void setDurezaPadrao(String durezaPadrao) {
		this.durezaPadrao = durezaPadrao;
	}

	public String getCloroPadrao() {
		return cloroPadrao;
	}

	public void setCloroPadrao(String cloroPadrao) {
		this.cloroPadrao = cloroPadrao;
	}
	
	public String getCloriformePadrao() {
		return cloriformePadrao;
	}

	public void setCloriformePadrao(String cloriformePadrao) {
		this.cloriformePadrao = cloriformePadrao;
	}

	public String getPisBaseCalculo() {
		return pisBaseCalculo;
	}
	
	public void setPisBaseCalculo(String pisBaseCalculo) {
		this.pisBaseCalculo = pisBaseCalculo;
	}
	
	public String getPisPercentual() {
		return pisPercentual;
	}

	public void setPisPercentual(String pisPercentual) {
		this.pisPercentual = pisPercentual;
	}

	public String getPisValorImposto() {
		return pisValorImposto;
	}

	public void setPisValorImposto(String pisValorImposto) {
		this.pisValorImposto = pisValorImposto;
	}

	public String getCofinsBaseCalculo() {
		return cofinsBaseCalculo;
	}

	public void setCofinsBaseCalculo(String cofinsBaseCalculo) {
		this.cofinsBaseCalculo = cofinsBaseCalculo;
	}

	public String getCofinsPercentual() {
		return cofinsPercentual;
	}

	public void setCofinsPercentual(String cofinsPercentual) {
		this.cofinsPercentual = cofinsPercentual;
	}

	public String getCofinsValorImposto() {
		return cofinsValorImposto;
	}

	public void setCofinsValorImposto(String cofinsValorImposto) {
		this.cofinsValorImposto = cofinsValorImposto;
	}
	
}