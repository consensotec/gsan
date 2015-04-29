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
package gsan.relatorio.arrecadacao.pagamento;

import gsan.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 * [UC0379] Emitir Guia de Pagamento
 * @author Vivianne Sousa, Mariana Victor
 * @date 22/09/2006, 02/03/2011
 */
public class RelatorioEmitirGuiaPagamentoBean implements RelatorioBean {

	private JRBeanCollectionDataSource arrayJRDetail;
	private ArrayList arrayRelatorioEmitirGuiaPagamentoDetailBean;
	
	private String matricula ;
	private String nomeCliente ;
	private String dataVencimento ;
	private String inscricao ; 
	private String enderecoImovel ; 
	private String enderecoClienteResponsavel ;
	private String representacaoNumericaCodBarraFormatada ;
	private String representacaoNumericaCodBarraSemDigito ;
	private String dataValidade ;
	private String valorDebito;
	private String idGuiaPagamento;
	private String observacao;
	private String cpfCnpjCliente;
	private String msgVencimentoGuiaEntradaParc;
	
	/* Ficha de Compensa��o */
	private String idImovel;
	private String nossoNumero;
	private String sacadoParte01;
	private String sacadoParte02;
	private String enderecoImovelSacado;
	private JRBeanCollectionDataSource arrayJRDetailSub;
	
	private String subRelatorio;
	
	
	public RelatorioEmitirGuiaPagamentoBean( Collection colecaoDetail,
											 String matricula,
											 String nomeCliente,
											 String dataVencimento,
											 String inscricao,
											 String enderecoImovel, 
											 String enderecoClienteResponsavel,
											 String representacaoNumericaCodBarraFormatada,
											 String representacaoNumericaCodBarraSemDigito,
											 String dataValidade,
											 String valorDebito,
											 String idGuiaPagamento,
											 String observacao,
											 String cpfCnpjCliente,
											 String subRelatorio,
											 String msgVencimentoGuiaEntradaParc) {
		
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
		
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.inscricao = inscricao;
		this.enderecoImovel = enderecoImovel;
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.dataValidade = dataValidade;
		this.valorDebito = valorDebito;
		this.idGuiaPagamento = idGuiaPagamento;
		this.observacao = observacao;
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.subRelatorio = subRelatorio;
		this.msgVencimentoGuiaEntradaParc = msgVencimentoGuiaEntradaParc;
		
	}
	

	public RelatorioEmitirGuiaPagamentoBean( Collection colecaoDetail,
											 String matricula,
											 String nomeCliente,
											 String dataVencimento,
											 String inscricao,
											 String enderecoImovel, 
											 String enderecoClienteResponsavel,
											 String representacaoNumericaCodBarraFormatada,
											 String representacaoNumericaCodBarraSemDigito,
											 String dataValidade,
											 String valorDebito,
											 String idGuiaPagamento,
											 String observacao,
											 String cpfCnpjCliente,
											 String idImovel,
											 String nossoNumero,
											 String sacadoParte01,
											 String sacadoParte02,
											 String subRelatorio,
											 Collection colecaoDetailSub,
											 String msgVencimentoGuiaEntradaParc) {
		
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
		
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.dataVencimento = dataVencimento;
		this.inscricao = inscricao;
		this.enderecoImovel = enderecoImovel;
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.dataValidade = dataValidade;
		this.valorDebito = valorDebito;
		this.idGuiaPagamento = idGuiaPagamento;
		this.observacao = observacao;
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.idImovel = idImovel;
		this.nossoNumero = nossoNumero;
		this.sacadoParte01 = sacadoParte01;
		this.sacadoParte02 = sacadoParte02;
		this.subRelatorio = subRelatorio;
		this.msgVencimentoGuiaEntradaParc = msgVencimentoGuiaEntradaParc;

		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = new ArrayList();
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean.addAll(colecaoDetailSub);
		this.arrayJRDetailSub = new JRBeanCollectionDataSource(
				this.arrayRelatorioEmitirGuiaPagamentoDetailBean);
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorioEmitirGuiaPagamentoDetailBean() {
		return arrayRelatorioEmitirGuiaPagamentoDetailBean;
	}

	public void setArrayRelatorioEmitirGuiaPagamentoDetailBean(
			ArrayList arrayRelatorioEmitirGuiaPagamentoDetailBean) {
		this.arrayRelatorioEmitirGuiaPagamentoDetailBean = arrayRelatorioEmitirGuiaPagamentoDetailBean;
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

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getEnderecoImovelSacado() {
		return enderecoImovelSacado;
	}

	public void setEnderecoImovelSacado(String enderecoImovelSacado) {
		this.enderecoImovelSacado = enderecoImovelSacado;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte02() {
		return sacadoParte02;
	}

	public void setSacadoParte02(String sacadoParte02) {
		this.sacadoParte02 = sacadoParte02;
	}

	public String getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}

	public JRBeanCollectionDataSource getArrayJRDetailSub() {
		return arrayJRDetailSub;
	}

	public void setArrayJRDetailSub(JRBeanCollectionDataSource arrayJRDetailSub) {
		this.arrayJRDetailSub = arrayJRDetailSub;
	}


	public String getMsgVencimentoGuiaEntradaParc() {
		return msgVencimentoGuiaEntradaParc;
	}


	public void setMsgVencimentoGuiaEntradaParc(String msgVencimentoGuiaEntradaParc) {
		this.msgVencimentoGuiaEntradaParc = msgVencimentoGuiaEntradaParc;
	}

}
