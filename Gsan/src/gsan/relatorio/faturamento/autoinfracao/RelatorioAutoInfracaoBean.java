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
package gsan.relatorio.faturamento.autoinfracao;

import java.math.BigDecimal;

import gsan.relatorio.RelatorioBean;

/**
 * Bean respons�vel de pegar os parametros que ser�o exibidos na parte de detail
 * do relat�rio.
 * 
 * @author Rafael Corr�a
 * @created 08/08/2006
 */
/**
 * @author Administrador
 *
 */
public class RelatorioAutoInfracaoBean implements RelatorioBean {

	private String unidadeNegocio;

	private String funcionario;

	private String dataPagamento;

	private String autoInfracao;
	
	private String descricaoServico;

	private BigDecimal valorServico;

	private BigDecimal valorFuncionario;

	private String matricula;
	
	private String nomeCliente;
	
	private String qtdParcelas;
	
	private String parcelaPaga;
	
	private BigDecimal valorParcelaPaga;

	public RelatorioAutoInfracaoBean(String unidadeNegocio, String funcionario,
			String dataPagamento, String autoInfracao, String descricaoServico,
			BigDecimal valorServico, BigDecimal valorFuncionario,
			String matricula, String nomeCliente, String qtdParcelas, String parcelaPaga,
			BigDecimal valorParcelaPaga ) {
		this.unidadeNegocio = unidadeNegocio;
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
		this.autoInfracao = autoInfracao;
		this.descricaoServico = descricaoServico;
		this.valorServico = valorServico;
		this.valorFuncionario = valorFuncionario;
		this.matricula = matricula;
		this.nomeCliente = nomeCliente;
		this.qtdParcelas = qtdParcelas;
		this.parcelaPaga = parcelaPaga;
		this.valorParcelaPaga = valorParcelaPaga;
	}

	public RelatorioAutoInfracaoBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Retorna o campo autoInfracao.
	 */
	public String getAutoInfracao() {
		return autoInfracao;
	}

	/**
	 * @param autoInfracao O autoInfracao a ser setado.
	 */
	public void setAutoInfracao(String autoInfracao) {
		this.autoInfracao = autoInfracao;
	}

	/**
	 * @return Retorna o campo dataPagamento.
	 */
	public String getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento O dataPagamento a ser setado.
	 */
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @return Retorna o campo descricaoServico.
	 */
	public String getDescricaoServico() {
		return descricaoServico;
	}

	/**
	 * @param descricaoServico O descricaoServico a ser setado.
	 */
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	/**
	 * @return Retorna o campo funcionario.
	 */
	public String getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario O funcionario a ser setado.
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * @return Retorna o campo valorFuncionario.
	 */
	public BigDecimal getValorFuncionario() {
		return valorFuncionario;
	}

	/**
	 * @param valorFuncionario O valorFuncionario a ser setado.
	 */
	public void setValorFuncionario(BigDecimal valorFuncionario) {
		this.valorFuncionario = valorFuncionario;
	}

	/**
	 * @return Retorna o campo valorServico.
	 */
	public BigDecimal getValorServico() {
		return valorServico;
	}

	/**
	 * @param valorServico O valorServico a ser setado.
	 */
	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public String getParcelaPaga() {
		return parcelaPaga;
	}

	public void setParcelaPaga(String parcelaPaga) {
		this.parcelaPaga = parcelaPaga;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public BigDecimal getValorParcelaPaga() {
		return valorParcelaPaga;
	}

	public void setValorParcelaPaga(BigDecimal valorParcelaPaga) {
		this.valorParcelaPaga = valorParcelaPaga;
	}

	
}
