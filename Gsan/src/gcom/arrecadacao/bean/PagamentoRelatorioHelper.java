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
package gcom.arrecadacao.bean;

import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe que ir� auxiliar na constru��o do relat�rio do pagamento
 * 
 * @author Rafael Corr�a
 * @date 12/12/2006
 */
public class PagamentoRelatorioHelper {

	private Integer idLocalidade;

	private String descricaoLocalidade;

	private Integer idGerenciaRegional;

	private String nomeGerenciaRegional;

	private Integer idImovel;

	private Integer idCliente;

	private String nomeCliente;

	private String nomeArrecadador;

	private Date dataPagamento;

	private Integer anoMesReferenciaPagamento;

	private String descricaoTipoDebito;

	private BigDecimal valorDocumento;

	private BigDecimal valorPagamento;

	private Integer idSituacaoPagamentoAtual;

	private String descricaoSituacaoPagamentoAtual;
	
	private String descricaoSituacaoPagamentoAnterior;

	private BigDecimal valorAgua;

	private BigDecimal valorEsgoto;

	private BigDecimal debitos;

	private BigDecimal valorCreditos;
	
	private BigDecimal valorImpostos;

	private BigDecimal valorDebito;

	private Short numeroPrestacaoDebito;

	private Short numeroPrestacaoCobradas;
	
	private Integer idDocumentoTipo;
	
	private String descricaoDocumentoTipo;

	/**
	 * @return Retorna o campo idDocumentoTipo.
	 */
	public Integer getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	/**
	 * @param idDocumentoTipo O idDocumentoTipo a ser setado.
	 */
	public void setIdDocumentoTipo(Integer idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	/**
	 * @return Retorna o campo anoMesReferenciaPagamento.
	 */
	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	/**
	 * @param anoMesReferenciaPagamento
	 *            O anoMesReferenciaPagamento a ser setado.
	 */
	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento
	 *            O dataPagamento a ser setado.
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @return Retorna o campo descricaoSituacaoPagamentoAtual.
	 */
	public String getDescricaoSituacaoPagamentoAtual() {
		return descricaoSituacaoPagamentoAtual;
	}

	/**
	 * @param descricaoSituacaoPagamentoAtual
	 *            O descricaoSituacaoPagamentoAtual a ser setado.
	 */
	public void setDescricaoSituacaoPagamentoAtual(
			String descricaoSituacaoPagamentoAtual) {
		this.descricaoSituacaoPagamentoAtual = descricaoSituacaoPagamentoAtual;
	}

	/**
	 * @return Retorna o campo descricaoTipoDebito.
	 */
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}

	/**
	 * @param descricaoTipoDebito
	 *            O descricaoTipoDebito a ser setado.
	 */
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}

	/**
	 * @return Retorna o campo idCliente.
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente
	 *            O idCliente a ser setado.
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional
	 *            O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel
	 *            O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade
	 *            O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo nomeArrecadador.
	 */
	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	/**
	 * @param nomeArrecadador
	 *            O nomeArrecadador a ser setado.
	 */
	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional
	 *            O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	/**
	 * @param descricaoLocalidade
	 *            O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	/**
	 * @param valorDocumento
	 *            O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * @return Retorna o campo valorPagamento.
	 */
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	/**
	 * @param valorPagamento
	 *            O valorPagamento a ser setado.
	 */
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	/**
	 * @return Retorna o campo debitos.
	 */
	public BigDecimal getDebitos() {
		return debitos;
	}

	/**
	 * @param debitos
	 *            O debitos a ser setado.
	 */
	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	/**
	 * @return Retorna o campo idSituacaoPagamentoAtual.
	 */
	public Integer getIdSituacaoPagamentoAtual() {
		return idSituacaoPagamentoAtual;
	}

	/**
	 * @param idSituacaoPagamentoAtual
	 *            O idSituacaoPagamentoAtual a ser setado.
	 */
	public void setIdSituacaoPagamentoAtual(Integer idSituacaoPagamentoAtual) {
		this.idSituacaoPagamentoAtual = idSituacaoPagamentoAtual;
	}

	/**
	 * @return Retorna o campo numeroPrestacaoCobradas.
	 */
	public Short getNumeroPrestacaoCobradas() {
		return numeroPrestacaoCobradas;
	}

	/**
	 * @param numeroPrestacaoCobradas
	 *            O numeroPrestacaoCobradas a ser setado.
	 */
	public void setNumeroPrestacaoCobradas(Short numeroPrestacaoCobradas) {
		this.numeroPrestacaoCobradas = numeroPrestacaoCobradas;
	}

	/**
	 * @return Retorna o campo numeroPrestacoesDebito.
	 */
	public Short getNumeroPrestacaoDebito() {
		return numeroPrestacaoDebito;
	}

	/**
	 * @param numeroPrestacoesDebito
	 *            O numeroPrestacoesDebito a ser setado.
	 */
	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	/**
	 * @return Retorna o campo valorAgua.
	 */
	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	/**
	 * @param valorAgua
	 *            O valorAgua a ser setado.
	 */
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	/**
	 * @return Retorna o campo valorCreditos.
	 */
	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	/**
	 * @param valorCreditos
	 *            O valorCreditos a ser setado.
	 */
	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito
	 *            O valorDebito a ser setado.
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo valorEsgoto.
	 */
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	/**
	 * @param valorEsgoto
	 *            O valorEsgoto a ser setado.
	 */
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	/**
	 * Este m�todo retorna o valor total do documento
	 * 
	 * @author Rafael Corr�a
	 * @date 12/12/2006
	 * 
	 */
public BigDecimal getValorTotalDocumento() {

		BigDecimal valorTotalDocumento = new BigDecimal("0.00");

		// Verifica se a pesquisa j� retorno o valor do documento
		// calculado(pesquisa por cliente) ou se o pagamento � de guia de
		// pagamento, pois o valor do documento n�o precisar� ser calculado
		if (this.getValorDocumento() != null) {
			valorTotalDocumento = this.getValorDocumento();
		}
		// Verifica se o pagamento � de conta e calcula o valor do documento
		else if (this.getValorAgua() != null) {

			// Valor de �gua
			valorTotalDocumento = valorTotalDocumento.add(this.getValorAgua());

			// Valor de esgoto
			if (this.getValorEsgoto() != null) {
				valorTotalDocumento = valorTotalDocumento.add(this
						.getValorEsgoto());
			}

			// Valor dos d�bitos
			if (this.getDebitos() != null) {
				valorTotalDocumento = valorTotalDocumento
						.add(this.getDebitos());
			}

			// Valor dos cr�ditos
			if (this.getValorCreditos() != null) {
				valorTotalDocumento = valorTotalDocumento.subtract(this
						.getValorCreditos());
			}
			
			// Valor dos impostos
			if (this.getValorImpostos() != null) {
				valorTotalDocumento = valorTotalDocumento.subtract(this.getValorImpostos());
			}
			

		} 
		// Verifica se o pagamento � de d�bito a cobrar e calcula o valor do documento
		else if (this.getValorDebito() != null) {
            
//			BigDecimal retornoDivisao = new BigDecimal("0.00");
//	 		BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
//	 		
//	 		retornoDivisao = Util.dividirArredondando(this.valorDebito,new BigDecimal(numeroPrestacaoDebito));
//	 		
//	 		retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoCobradas));
//	 		
//	 		valorTotalDocumento = this.valorDebito.subtract(retornoMultiplicacao);
            
            //alterado por Vivianne Sousa data:11/04/2008
            //analista:Aryed
            DebitoACobrar debitoACobrar = new DebitoACobrar();
            
            debitoACobrar.setValorDebito(valorDebito);
            debitoACobrar.setNumeroPrestacaoDebito(numeroPrestacaoDebito);
            debitoACobrar.setNumeroPrestacaoCobradas(numeroPrestacaoCobradas);
            
            valorTotalDocumento = debitoACobrar.getValorTotalComBonus();
		}

		valorTotalDocumento = valorTotalDocumento.setScale(2,
				BigDecimal.ROUND_HALF_UP);

		return valorTotalDocumento;
	}

	/**
	 * @return Retorna o campo descricaoDocumentoTipo.
	 */
	public String getDescricaoDocumentoTipo() {
		return descricaoDocumentoTipo;
	}

	/**
	 * @param descricaoDocumentoTipo O descricaoDocumentoTipo a ser setado.
	 */
	public void setDescricaoDocumentoTipo(String descricaoDocumentoTipo) {
		this.descricaoDocumentoTipo = descricaoDocumentoTipo;
	}

	/**
	 * @return Retorna o campo descricaoSituacaoPagamentoAnterior.
	 */
	public String getDescricaoSituacaoPagamentoAnterior() {
		return descricaoSituacaoPagamentoAnterior;
	}

	/**
	 * @param descricaoSituacaoPagamentoAnterior O descricaoSituacaoPagamentoAnterior a ser setado.
	 */
	public void setDescricaoSituacaoPagamentoAnterior(
			String descricaoSituacaoPagamentoAnterior) {
		this.descricaoSituacaoPagamentoAnterior = descricaoSituacaoPagamentoAnterior;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}
	
	
}
