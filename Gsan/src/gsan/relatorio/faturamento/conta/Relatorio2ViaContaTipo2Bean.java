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
package gsan.relatorio.faturamento.conta;

import gsan.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC0482] Emitir 2� Via de Conta
 * @author Vivianne Sousa
 * @date 03/03/2007
 */
public class Relatorio2ViaContaTipo2Bean implements RelatorioBean {
	
	private String indicadorPrimeiraPagina;
	
	private JRBeanCollectionDataSource arrayJRDetail;

	private ArrayList arrayRelatorio2ViaContaDetailBean;
	
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
	private String descricaoAguaSituacao;
	private String descricaoEsgotoSituacao;
	//Linha 9
	private String mesAno1;
	private String consumo1;

	private String mesAno4;
	private String consumo4;
	
	private String mesAno2;
	private String consumo2;

	private String mesAno5;
	private String consumo5;
	
	private String leituraAnterior;
	private String leituraAtual;
	private String consumoFaturamento;
	private String diasConsumo;
	private String consumoMedioDiario;
	//Linha 11
	private String mesAno3;
	private String consumo3;

	private String mesAno6;
	private String consumo6;
	
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
//	private String primeiraParte;
//	//Linha 19
//	private String segundaParte;
//	//Linha 20
//	private String terceiraParte;
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
	
	private String datasVencimento;
	private String consumoMedio;
	private String categoriaImovel;
	private String numeroHidrometro;
	
	private String rotaEntrega;
	
	private String idConta;
	
	private String totalPaginasRelatorio;
	
	private String contaSemCodigoBarras;
	
	
	
	private String msgConta;
	private String msgLinha1Conta;
	private String msgLinha2Conta;
	private String msgLinha3Conta;
	private String msgLinha4Conta;
	private String msgLinha5Conta;
	private String valorMedioTurbidez;
	private String padraoTurbidez;
	private String valorMedioPh;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioFerro;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesfecais;
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;
	private String enderecoLinha2;
	private String enderecoLinha3; 
	private String grupoFaturamento;
	private String numeroDocumento;
	
	private String quantidadeCloroExigidas;
	private String quantidadeCloroAnalisadas;
	private String quantidadeCloroConforme;
	private String quantidadeTurbidezExigidas;
	private String quantidadeTurbidezAnalisadas;
	private String quantidadeTurbidezConforme;
	private String quantidadeCorExigidas;
	private String quantidadeCorAnalisadas;
	private String quantidadeCorConforme;
	private String quantidadeColiformesTotaisExigidas;
	private String quantidadeColiformesTotaisAnalisadas;
	private String quantidadeColiformesTotaisConforme;
	private String quantidadeColiformesTermotolerantesExigidas;
	private String quantidadeColiformesTermotolerantesAnalisadas;
	private String quantidadeColiformesTermotolerantesConforme;
	
	  public String getEnderecoLinha2() {
		return enderecoLinha2;
	}


	public void setEnderecoLinha2(String enderecoLinha2) {
		this.enderecoLinha2 = enderecoLinha2;
	}


	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}


	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}


	public String getMsgConta() {
		return msgConta;
	}


	public void setMsgConta(String msgConta) {
		this.msgConta = msgConta;
	}


	public String getMsgLinha1Conta() {
		return msgLinha1Conta;
	}


	public void setMsgLinha1Conta(String msgLinha1Conta) {
		this.msgLinha1Conta = msgLinha1Conta;
	}


	public String getMsgLinha2Conta() {
		return msgLinha2Conta;
	}


	public void setMsgLinha2Conta(String msgLinha2Conta) {
		this.msgLinha2Conta = msgLinha2Conta;
	}


	public String getMsgLinha3Conta() {
		return msgLinha3Conta;
	}


	public void setMsgLinha3Conta(String msgLinha3Conta) {
		this.msgLinha3Conta = msgLinha3Conta;
	}


	public String getMsgLinha4Conta() {
		return msgLinha4Conta;
	}


	public void setMsgLinha4Conta(String msgLinha4Conta) {
		this.msgLinha4Conta = msgLinha4Conta;
	}


	public String getMsgLinha5Conta() {
		return msgLinha5Conta;
	}


	public void setMsgLinha5Conta(String msgLinha5Conta) {
		this.msgLinha5Conta = msgLinha5Conta;
	}


	public String getPadraoCloro() {
		return padraoCloro;
	}


	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}


	public String getPadraoColiformesfecais() {
		return padraoColiformesfecais;
	}


	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesfecais = padraoColiformesfecais;
	}


	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}


	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}


	public String getPadraoCor() {
		return padraoCor;
	}


	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}


	public String getPadraoFerro() {
		return padraoFerro;
	}


	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}


	public String getPadraoFluor() {
		return padraoFluor;
	}


	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}


	public String getPadraoPh() {
		return padraoPh;
	}


	public void setPadraoPh(String padraoPh) {
		this.padraoPh = padraoPh;
	}


	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}


	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}


	public String getValorMedioCloro() {
		return valorMedioCloro;
	}


	public void setValorMedioCloro(String valorMedioCloro) {
		this.valorMedioCloro = valorMedioCloro;
	}


	public String getValorMedioColiformesfecais() {
		return valorMedioColiformesfecais;
	}


	public void setValorMedioColiformesfecais(String valorMedioColiformesfecais) {
		this.valorMedioColiformesfecais = valorMedioColiformesfecais;
	}


	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}


	public void setValorMedioColiformesTotais(String valorMedioColiformesTotais) {
		this.valorMedioColiformesTotais = valorMedioColiformesTotais;
	}


	public String getValorMedioCor() {
		return valorMedioCor;
	}


	public void setValorMedioCor(String valorMedioCor) {
		this.valorMedioCor = valorMedioCor;
	}


	public String getValorMedioFerro() {
		return valorMedioFerro;
	}


	public void setValorMedioFerro(String valorMedioFerro) {
		this.valorMedioFerro = valorMedioFerro;
	}


	public String getValorMedioFluor() {
		return valorMedioFluor;
	}


	public void setValorMedioFluor(String valorMedioFluor) {
		this.valorMedioFluor = valorMedioFluor;
	}


	public String getValorMedioPh() {
		return valorMedioPh;
	}


	public void setValorMedioPh(String valorMedioPh) {
		this.valorMedioPh = valorMedioPh;
	}


	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}


	public void setValorMedioTurbidez(String valorMedioTurbidez) {
		this.valorMedioTurbidez = valorMedioTurbidez;
	}
	


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getQuantidadeCloroExigidas() {
		return quantidadeCloroExigidas;
	}


	public void setQuantidadeCloroExigidas(String quantidadeCloroExigidas) {
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
	}


	public String getQuantidadeCloroAnalisadas() {
		return quantidadeCloroAnalisadas;
	}


	public void setQuantidadeCloroAnalisadas(String quantidadeCloroAnalisadas) {
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
	}


	public String getQuantidadeCloroConforme() {
		return quantidadeCloroConforme;
	}


	public void setQuantidadeCloroConforme(String quantidadeCloroConforme) {
		this.quantidadeCloroConforme = quantidadeCloroConforme;
	}


	public String getQuantidadeTurbidezExigidas() {
		return quantidadeTurbidezExigidas;
	}


	public void setQuantidadeTurbidezExigidas(String quantidadeTurbidezExigidas) {
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
	}


	public String getQuantidadeTurbidezAnalisadas() {
		return quantidadeTurbidezAnalisadas;
	}


	public void setQuantidadeTurbidezAnalisadas(String quantidadeTurbidezAnalisadas) {
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
	}


	public String getQuantidadeTurbidezConforme() {
		return quantidadeTurbidezConforme;
	}


	public void setQuantidadeTurbidezConforme(String quantidadeTurbidezConforme) {
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
	}


	public String getQuantidadeCorExigidas() {
		return quantidadeCorExigidas;
	}


	public void setQuantidadeCorExigidas(String quantidadeCorExigidas) {
		this.quantidadeCorExigidas = quantidadeCorExigidas;
	}


	public String getQuantidadeCorAnalisadas() {
		return quantidadeCorAnalisadas;
	}


	public void setQuantidadeCorAnalisadas(String quantidadeCorAnalisadas) {
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
	}


	public String getQuantidadeCorConforme() {
		return quantidadeCorConforme;
	}


	public void setQuantidadeCorConforme(String quantidadeCorConforme) {
		this.quantidadeCorConforme = quantidadeCorConforme;
	}


	public String getQuantidadeColiformesTotaisExigidas() {
		return quantidadeColiformesTotaisExigidas;
	}


	public void setQuantidadeColiformesTotaisExigidas(String quantidadeColiformesTotaisExigidas) {
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
	}


	public String getQuantidadeColiformesTotaisAnalisadas() {
		return quantidadeColiformesTotaisAnalisadas;
	}


	public void setQuantidadeColiformesTotaisAnalisadas(String quantidadeColiformesTotaisAnalisadas) {
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
	}


	public String getQuantidadeColiformesTotaisConforme() {
		return quantidadeColiformesTotaisConforme;
	}


	public void setQuantidadeColiformesTotaisConforme(String quantidadeColiformesTotaisConforme) {
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
	}


	public String getQuantidadeColiformesTermotolerantesExigidas() {
		return quantidadeColiformesTermotolerantesExigidas;
	}


	public void setQuantidadeColiformesTermotolerantesExigidas(String quantidadeColiformesTermotolerantesExigidas) {
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
	}


	public String getQuantidadeColiformesTermotolerantesAnalisadas() {
		return quantidadeColiformesTermotolerantesAnalisadas;
	}


	public void setQuantidadeColiformesTermotolerantesAnalisadas(String quantidadeColiformesTermotolerantesAnalisadas) {
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
	}


	public String getQuantidadeColiformesTermotolerantesConforme() {
		return quantidadeColiformesTermotolerantesConforme;
	}


	public void setQuantidadeColiformesTermotolerantesConforme(String quantidadeColiformesTermotolerantesConforme) {
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	}
	
	
	public Relatorio2ViaContaTipo2Bean(
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
	    		String leituraAnterior,
	    		String leituraAtual,
	    		String consumoFaturamento,
	    		String diasConsumo,
	    		String consumoMedioDiario,
	    		String dataLeituraAnterior,
	    		String dataLeituraAtual,
	    		String descricaoTipoConsumo,
	    		String descricaoAnormalidadeConsumo,
	    		String quantidadeEconomiaConta,
	    		String consumoEconomia,
	    		String codigoAuxiliarString,
	    		String mesagemConsumoString,
	    		String valorContaString,
//	    		String primeiraParte,
//	    		String segundaParte,
//	    		String terceiraParte,
	    		String nomeGerenciaRegional,
	    		String mesAnoFormatado,
	    		String numeroIndiceTurbidez,
	    		String numeroCloroResidual,	
	    		String representacaoNumericaCodBarraFormatada,
	    		String representacaoNumericaCodBarraSemDigito,
	    		String dataValidade,
	    		String grupo,	
	    		String firma,
	    		String totalPaginasRelatorio,
	    		String idConta,
	    		String rotaEntrega,
	    		String datasVencimento,
	    		String consumoMedio,
	    		String mesAno1,
	    		String mesAno2,
	    		String mesAno3,
	    		String mesAno4,
	    		String mesAno5,
	    		String mesAno6,
	    		String consumo1,
	    		String consumo2,
	    		String consumo3,
	    		String consumo4,
	    		String consumo5,
	    		String consumo6,
	    		String categoriaImovel,
	    		String numeroHidrometro,
	    		String contaSemCodigoBarras,
	    		String msgConta,
	    		String msgLinha1Conta,
	    		String msgLinha2Conta,
	    		String msgLinha3Conta,
	    		String msgLinha4Conta,
	    		String msgLinha5Conta,
	    		String valorMedioTurbidez,
	    		String padraoTurbidez,
	    		String valorMedioPh,
	    		String valorMedioCor,
	    		String valorMedioCloro,
	    		String valorMedioFluor,
	    		String valorMedioFerro,
	    		String valorMedioColiformesTotais,
	    		String valorMedioColiformesfecais,
	    		String padraoPh,
	    		String padraoCor,
	    		String padraoCloro,
	    		String padraoFluor,
	    		String padraoFerro,
	    		String padraoColiformesTotais,
	    		String padraoColiformesfecais,
	    		String enderecoLinha2,
	    		String enderecoLinha3,
	    		String grupoFaturamento,
	    		String idImovel,
	    		String anoMesReferencia,
	    		String quantidadeCloroExigidas,
				String quantidadeCloroAnalisadas,
				String quantidadeCloroConforme,
				String quantidadeTurbidezExigidas,
				String quantidadeTurbidezAnalisadas,
				String quantidadeTurbidezConforme,
				String quantidadeCorExigidas,
				String quantidadeCorAnalisadas,
				String quantidadeCorConforme,
				String quantidadeColiformesTotaisExigidas,
				String quantidadeColiformesTotaisAnalisadas,
				String quantidadeColiformesTotaisConforme,
				String quantidadeColiformesTermotolerantesExigidas,
				String quantidadeColiformesTermotolerantesAnalisadas,
				String quantidadeColiformesTermotolerantesConforme) {
	    	
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
	    	this.leituraAnterior = leituraAnterior;
	    	this.leituraAtual = leituraAtual;
	    	this.consumoFaturamento = consumoFaturamento;
	    	this.diasConsumo = diasConsumo;
	    	this.consumoMedioDiario = consumoMedioDiario;
	    	this.dataLeituraAnterior = dataLeituraAnterior;
	    	this.dataLeituraAtual = dataLeituraAtual;
	    	this.descricaoTipoConsumo = descricaoTipoConsumo;
	    	this.descricaoAnormalidadeConsumo = descricaoAnormalidadeConsumo;
	    	this.quantidadeEconomiaConta = quantidadeEconomiaConta;
	    	this.consumoEconomia = consumoEconomia;
	    	this.codigoAuxiliarString = codigoAuxiliarString;
	    	this.mesagemConsumoString = mesagemConsumoString;
	    	this.valorContaString = valorContaString;
//	    	this.primeiraParte = primeiraParte;
//	    	this.segundaParte = segundaParte;
//	    	this.terceiraParte = terceiraParte;
	    	this.nomeGerenciaRegional = nomeGerenciaRegional;
	    	this.mesAnoFormatado = mesAnoFormatado;
	    	this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	    	this.numeroCloroResidual = numeroCloroResidual;	
	    	this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	    	this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	    	this.dataValidade = dataValidade;
	    	this.grupo = grupo;	
	    	this.firma = firma;
	    	this.totalPaginasRelatorio = totalPaginasRelatorio;
	    	this.idConta = idConta;
	    	this.rotaEntrega = rotaEntrega;
	    	this.datasVencimento = datasVencimento;
	    	this.consumoMedio = consumoMedio;
	    	
	    	this.mesAno1 = mesAno1;
    		this.mesAno2 = mesAno2;
    		this.mesAno3 = mesAno3;
    		this.mesAno4 = mesAno4;
    		this.mesAno5 = mesAno5;
    		this.mesAno6 = mesAno6;
    		this.consumo1 = consumo1;
    		this.consumo2 = consumo2;
    		this.consumo3 = consumo3;
    		this.consumo4 = consumo4;
    		this.consumo5 = consumo5;
    		this.consumo6 =consumo6;
    		this.categoriaImovel = categoriaImovel;
    		this.numeroHidrometro = numeroHidrometro;
    		this.contaSemCodigoBarras = contaSemCodigoBarras;
    		
    		this.msgConta = msgConta;
			this.msgLinha1Conta = msgLinha1Conta;
			this.msgLinha2Conta = msgLinha2Conta;
			this.msgLinha3Conta = msgLinha3Conta;
			this.msgLinha4Conta = msgLinha4Conta;
			this.msgLinha5Conta = msgLinha5Conta;
			this.valorMedioTurbidez = valorMedioTurbidez;
			this.padraoTurbidez = padraoTurbidez;
			this.valorMedioPh = valorMedioPh;
			this.valorMedioCor = valorMedioCor;
			this.valorMedioCloro = valorMedioCloro;
			this.valorMedioFluor = valorMedioFluor;
			this.valorMedioFerro = valorMedioFerro;
			this.valorMedioColiformesTotais = valorMedioColiformesTotais;
			this.valorMedioColiformesfecais = valorMedioColiformesfecais;
			this.padraoPh = padraoPh;
			this.padraoCor = padraoCor;
			this.padraoCloro = padraoCloro;
			this.padraoFluor = padraoFluor;
			this.padraoFerro = padraoFerro;
			this.padraoColiformesTotais = padraoColiformesTotais;
			this.padraoColiformesfecais = padraoColiformesfecais;
			this.enderecoLinha2 = enderecoLinha2;
			this.enderecoLinha3 = enderecoLinha3;
			this.grupoFaturamento = grupoFaturamento;
			this.numeroDocumento = anoMesReferencia + idImovel;
			
			this.quantidadeCloroExigidas = quantidadeCloroExigidas;
			this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
			this.quantidadeCloroConforme = quantidadeCloroConforme;
			this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
			this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
			this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
			this.quantidadeCorExigidas = quantidadeCorExigidas;
			this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
			this.quantidadeCorConforme = quantidadeCorConforme;
			this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
			this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
			this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
			this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
			this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
			this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	    }

	
//	public Relatorio2ViaContaBean(String indicadorPrimeiraPagina, Collection colecaoDetail) {
//		this.indicadorPrimeiraPagina = indicadorPrimeiraPagina;
//		this.arrayRelatorio2ViaContaDetailBean = new ArrayList();
//		this.arrayRelatorio2ViaContaDetailBean
//				.addAll(colecaoDetail);
//		this.arrayJRDetail = new JRBeanCollectionDataSource(
//				this.arrayRelatorio2ViaContaDetailBean);
//	}

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


	public String getDatasVencimento() {
		return datasVencimento;
	}


	public void setDatasVencimento(String datasVencimento) {
		this.datasVencimento = datasVencimento;
	}


	public String getConsumoMedio() {
		return consumoMedio;
	}


	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}


	public String getConsumo1() {
		return consumo1;
	}


	public void setConsumo1(String consumo1) {
		this.consumo1 = consumo1;
	}


	public String getConsumo2() {
		return consumo2;
	}


	public void setConsumo2(String consumo2) {
		this.consumo2 = consumo2;
	}


	public String getConsumo3() {
		return consumo3;
	}


	public void setConsumo3(String consumo3) {
		this.consumo3 = consumo3;
	}


	public String getConsumo4() {
		return consumo4;
	}


	public void setConsumo4(String consumo4) {
		this.consumo4 = consumo4;
	}


	public String getConsumo5() {
		return consumo5;
	}


	public void setConsumo5(String consumo5) {
		this.consumo5 = consumo5;
	}


	public String getConsumo6() {
		return consumo6;
	}


	public void setConsumo6(String consumo6) {
		this.consumo6 = consumo6;
	}


	public String getMesAno1() {
		return mesAno1;
	}


	public void setMesAno1(String mesAno1) {
		this.mesAno1 = mesAno1;
	}


	public String getMesAno2() {
		return mesAno2;
	}


	public void setMesAno2(String mesAno2) {
		this.mesAno2 = mesAno2;
	}


	public String getMesAno3() {
		return mesAno3;
	}


	public void setMesAno3(String mesAno3) {
		this.mesAno3 = mesAno3;
	}


	public String getMesAno4() {
		return mesAno4;
	}


	public void setMesAno4(String mesAno4) {
		this.mesAno4 = mesAno4;
	}


	public String getMesAno5() {
		return mesAno5;
	}


	public void setMesAno5(String mesAno5) {
		this.mesAno5 = mesAno5;
	}


	public String getMesAno6() {
		return mesAno6;
	}


	public void setMesAno6(String mesAno6) {
		this.mesAno6 = mesAno6;
	}


	public String getCategoriaImovel() {
		return categoriaImovel;
	}


	public void setCategoriaImovel(String categoriaImovel) {
		this.categoriaImovel = categoriaImovel;
	}


	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}


	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}


	public String getContaSemCodigoBarras() {
		return contaSemCodigoBarras;
	}


	public void setContaSemCodigoBarras(String contaSemCodigoBarras) {
		this.contaSemCodigoBarras = contaSemCodigoBarras;
	}


	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}


	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

}
