/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;
import gcom.util.serializacao.Campo;

import java.math.BigDecimal;

/**
 * [UCXXXX] - Gerar Relatório Consultar Debitos
 * 
 * @author Rafael Corrêa
 * @date 07/03/2007
 */
public class RelatorioConsultarDebitosResumidoBean implements RelatorioBean {
	private String idImovel;
	private String inscricao;
	private String nomeUsuario;
	private String cpfCnpjUsuario;
	private String endereco;
	private String situacaoAgua;
	private String situacaoEsgoto;
	private String categoria;
	private Integer qtdContas;
	private BigDecimal valorContas;
	private BigDecimal guiasPagamento;
	private BigDecimal acrescimos;
	private BigDecimal totalAtualizado;
	private BigDecimal debitosACobrar;
	private BigDecimal creditosARealizar;
	private BigDecimal totalGeral;
	private Integer idResponsavel;
	private String nomeResponsavel;
	private String cpfCnpjResponsavel;
	private String enderecoResponsavel;
	private Integer qtdeEconomias;
	private Integer codigoClienteUsuario;
	private Integer codigoClienteInformado;
	private String nomeClienteInformado;
	
	public RelatorioConsultarDebitosResumidoBean(String idImovel, String inscricao, String nomeUsuario,
			String cpfCnpjUsuario, String endereco, String situacaoAgua, String situacaoEsgoto, String categoria,
			Integer qtdContas, BigDecimal valorContas, BigDecimal guiasPagamento, BigDecimal acrescimos,
			BigDecimal totalAtualizado, BigDecimal debitosACobrar, BigDecimal creditosARealizar, BigDecimal totalGeral,
			Integer idResponsavel, String nomeResponsavel, String cpfCnpjResponsavel, String enderecoResponsavel, Integer qtdeEconomias, 
			Integer codigoClienteUsuario, Integer codigoClienteInformado, 
			String nomeClienteInformado) {
		this.idImovel = idImovel;
		this.inscricao = inscricao;
		this.nomeUsuario = nomeUsuario;
		this.cpfCnpjUsuario = cpfCnpjUsuario;
		this.endereco = endereco;
		this.situacaoAgua = situacaoAgua;
		this.situacaoEsgoto = situacaoEsgoto;
		this.categoria = categoria;
		this.qtdContas = qtdContas;
		this.valorContas = valorContas;
		this.guiasPagamento = guiasPagamento;
		this.acrescimos = acrescimos;
		this.totalAtualizado = totalAtualizado;
		this.debitosACobrar = debitosACobrar;
		this.creditosARealizar = creditosARealizar;
		this.totalGeral = totalGeral;
		this.idResponsavel = idResponsavel;
		this.nomeResponsavel = nomeResponsavel;
		this.cpfCnpjResponsavel = cpfCnpjResponsavel;
		this.enderecoResponsavel = enderecoResponsavel;
		this.qtdeEconomias = qtdeEconomias;
		this.codigoClienteUsuario = codigoClienteUsuario;
		this.codigoClienteInformado = codigoClienteInformado;
		this.nomeClienteInformado = nomeClienteInformado;
	}
	
	
	@Campo(posicao = 1, nome="CODIGO DO CLIENTE INFORMADO")
	public Integer getCodigoClienteInformado() {
		return codigoClienteInformado;
	}
	public void setCodigoClienteInformado(Integer codigoClienteInformado) {
		this.codigoClienteInformado = codigoClienteInformado;
	}
	
	@Campo(posicao = 2, nome="NOME DO CLIENTE INFORMADO")	
	public String getNomeClienteInformado() {
		return nomeClienteInformado;
	}
	public void setNomeClienteInformado(String nomeClienteInformado) {
		this.nomeClienteInformado = nomeClienteInformado;
	}
	
	@Campo(posicao = 3, nome="CODIGO DO CLIENTE RESPONSAVEL")
	public Integer getIdResponsavel() {
		return idResponsavel;
	}
	public void setIdResponsavel(Integer idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	
	@Campo(posicao = 4, nome="NOME DO CLIENTE RESPONSAVEL")
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	@Campo(posicao = 5, nome="CODIGO DO CLIENTE USUARIO")
	public Integer getCodigoClienteUsuario() {
		return codigoClienteUsuario;
	}
	public void setCodigoClienteUsuario(Integer codigoClienteUsuario) {
		this.codigoClienteUsuario = codigoClienteUsuario;
	}
	
	@Campo(posicao = 6, nome="NOME DO CLIENTE USUARIO")
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	@Campo(posicao = 7, nome="MATRICULA")
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	
	@Campo(posicao = 8, nome="INSCRICAO", tab=true)
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	
	@Campo(posicao = 9, nome="CATEGORIA")
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Campo(posicao = 10, nome="QUANTIDADE DE ECONOMIAS")	
	public Integer getQtdeEconomias() {
		return qtdeEconomias;
	}
	public void setQtdeEconomias(Integer qtdeEconomias) {
		this.qtdeEconomias = qtdeEconomias;
	}
	
	@Campo(posicao = 11, nome="SITUACAO DA LIGACAO DA AGUA")
	public String getSituacaoAgua() {
		return situacaoAgua;
	}
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}
	
	@Campo(posicao = 12, nome="SITUACAO DA LIGACAO DO ESGOTO")
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}
	
	@Campo(posicao = 13, nome="QUANTIDADE DE CONTAS")	
	public Integer getQtdContas() {
		return qtdContas;
	}
	public void setQtdContas(Integer qtdContas) {
		this.qtdContas = qtdContas;
	}
	
	@Campo(posicao = 14, nome="CONTAS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getValorContas() {
		return valorContas;
	}
	public void setValorContas(BigDecimal valorContas) {
		this.valorContas = valorContas;
	}
	
	@Campo(posicao = 15, nome="GUIAS DE PAGAMENTO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getGuiasPagamento() {
		return guiasPagamento;
	}
	public void setGuiasPagamento(BigDecimal guiasPagamento) {
		this.guiasPagamento = guiasPagamento;
	}
	
	@Campo(posicao = 16, nome="ACRESCIMOS", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getAcrescimos() {
		return acrescimos;
	}
	public void setAcrescimos(BigDecimal acrescimos) {
		this.acrescimos = acrescimos;
	}
	
	@Campo(posicao = 17, nome="TOTAL ATUALIZADO", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getTotalAtualizado() {
		return totalAtualizado;
	}
	public void setTotalAtualizado(BigDecimal totalAtualizado) {
		this.totalAtualizado = totalAtualizado;
	}
	
	@Campo(posicao = 18, nome="DEBITOS A COBRAR", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getDebitosACobrar() {
		return debitosACobrar;
	}
	public void setDebitosACobrar(BigDecimal debitosACobrar) {
		this.debitosACobrar = debitosACobrar;
	}
	
	@Campo(posicao = 19, nome="CREDITOS A REALIZAR", formato=Campo.FORMATO_MOEDA)	
	public BigDecimal getCreditosARealizar() {
		return creditosARealizar;
	}
	public void setCreditosARealizar(BigDecimal creditosARealizar) {
		this.creditosARealizar = creditosARealizar;
	}
	
	@Campo(posicao = 20, nome="TOTAL GERAL", formato=Campo.FORMATO_MOEDA)
	public BigDecimal getTotalGeral() {
		return totalGeral;
	}
	public void setTotalGeral(BigDecimal totalGeral) {
		this.totalGeral = totalGeral;
	}

	public String getCpfCnpjResponsavel() {
		return cpfCnpjResponsavel;
	}

	public void setCpfCnpjResponsavel(String cpfCnpjResponsavel) {
		this.cpfCnpjResponsavel = cpfCnpjResponsavel;
	}

	public String getEnderecoResponsavel() {
		return enderecoResponsavel;
	}
	
	public void setEnderecoResponsavel(String enderecoResponsavel) {
		this.enderecoResponsavel = enderecoResponsavel;
	}
	
	public String getCpfCnpjUsuario() {
		return cpfCnpjUsuario;
	}

	public void setCpfCnpjUsuario(String cpfCnpjUsuario) {
		this.cpfCnpjUsuario = cpfCnpjUsuario;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
