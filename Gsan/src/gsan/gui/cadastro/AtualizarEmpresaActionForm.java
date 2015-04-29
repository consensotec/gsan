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
package gsan.gui.cadastro;

import java.sql.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * @author Arthur Carvalho
 * @date 08/05/2008
 */

public class AtualizarEmpresaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String descricaoAbreviada;

	private Short indicadorUso;

	private Date ultimaAlteracao;

	private String email;

	private Short indicadorEmpresaPrincipal;

	private String indicadorEmpresaCobranca;

	private String indicadorAtualizaCadastro;

	private String dataInicioContratoCobranca;
	
	private String dataFimContratoCobranca;

	private String percentualPagamento;
	
	private Short indicadorLeitura;
	
	private String quantidadeMinimaContas;
    
	private String percentualDaFaixa;
	
	private String percentualDaFaixaInformado;
	
	private String quantidadeMesValidoPagamento;
	
	private String dataEncerramentoContratoCobranca;

	public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(Short indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getDataInicioContratoCobranca() {
		return dataInicioContratoCobranca;
	}

	public void setDataInicioContratoCobranca(String dataInicioContratoCobranca) {
		this.dataInicioContratoCobranca = dataInicioContratoCobranca;
	}

	public String getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}

	public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}

	public String getIndicadorAtualizaCadastro() {
		return indicadorAtualizaCadastro;
	}

	public void setIndicadorAtualizaCadastro(String indicadorAtualizaCadastro) {
		this.indicadorAtualizaCadastro = indicadorAtualizaCadastro;
	}

	public String getPercentualPagamento() {
		return percentualPagamento;
	}

	public void setPercentualPagamento(String percentualPagamento) {
		this.percentualPagamento = percentualPagamento;
	}

	public String getDataFimContratoCobranca() {
		return dataFimContratoCobranca;
	}

	public void setDataFimContratoCobranca(String dataFimContratoCobranca) {
		this.dataFimContratoCobranca = dataFimContratoCobranca;
	}

	public String getPercentualDaFaixa() {
		return percentualDaFaixa;
	}

	public void setPercentualDaFaixa(String percentualDaFaixa) {
		this.percentualDaFaixa = percentualDaFaixa;
	}

	public String getQuantidadeMinimaContas() {
		return quantidadeMinimaContas;
	}

	public void setQuantidadeMinimaContas(String quantidadeMinimaContas) {
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getPercentualDaFaixaInformado() {
		return percentualDaFaixaInformado;
	}

	public void setPercentualDaFaixaInformado(String percentualDaFaixaInformado) {
		this.percentualDaFaixaInformado = percentualDaFaixaInformado;
	}

	public String getQuantidadeMesValidoPagamento() {
		return quantidadeMesValidoPagamento;
	}

	public void setQuantidadeMesValidoPagamento(String quantidadeMesValidoPagamento) {
		this.quantidadeMesValidoPagamento = quantidadeMesValidoPagamento;
	}

	public String getDataEncerramentoContratoCobranca() {
		return dataEncerramentoContratoCobranca;
	}

	public void setDataEncerramentoContratoCobranca(
			String dataEncerramentoContratoCobranca) {
		this.dataEncerramentoContratoCobranca = dataEncerramentoContratoCobranca;
	}
	
	
}
