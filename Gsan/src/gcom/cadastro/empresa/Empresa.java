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
package gcom.cadastro.empresa;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Empresa extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	private String email;

	/** nullable persistent field */
	private Short indicadorEmpresaPrincipal;
	
	private Short indicadorEmpresaContratadaCobranca;
	
	private Short indicadorLeitura;

	private Short indicadorAtualizaCadastro;
	
	private Date dataEncerramentoContratoCobranca;
	
	private Integer quantidadeMesValidoPagamento;
	

	public static final Short INDICADOR_EMPRESA_PRINCIPAL = new Short("1");

	public static final Short EMPRESA_FEBRABAN_COMPESA = new Short("18");

	public static final Short INDICADOR_EMPRESA_COBRANCA = new Short("1");

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** full constructor */
	public Empresa(String descricao, String descricaoAbreviada,
			Short indicadorUso, String email, Date ultimaAlteracao, Short indicadorEmpresaPrincipal,
			Date dataEncerramentoContratoCobranca, Integer quantidadeMesValidoPagamento, Short indicadorAtualizaCadastro) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.email = email;
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
		this.dataEncerramentoContratoCobranca = dataEncerramentoContratoCobranca;
		this.quantidadeMesValidoPagamento = quantidadeMesValidoPagamento;
		this.indicadorAtualizaCadastro = indicadorAtualizaCadastro;
	}

	/** default constructor */
	public Empresa() {
	}

	public Integer getId() {
		return this.id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(Short indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	public Filtro retornaFiltro() {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				this.getId()));
		return filtroEmpresa;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID,
				this.getId()));
		return filtro;
	}

	public Short getIndicadorEmpresaContratadaCobranca() {
		return indicadorEmpresaContratadaCobranca;
	}

	public void setIndicadorEmpresaContratadaCobranca(
			Short indicadorEmpresaContratadaCobranca) {
		this.indicadorEmpresaContratadaCobranca = indicadorEmpresaContratadaCobranca;
	}

	public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}

	public Date getDataEncerramentoContratoCobranca() {
		return dataEncerramentoContratoCobranca;
	}

	public Short getIndicadorAtualizaCadastro() {
		return indicadorAtualizaCadastro;
	}

	public void setIndicadorAtualizaCadastro(Short indicadorAtualizaCadastro) {
		this.indicadorAtualizaCadastro = indicadorAtualizaCadastro;
	}

	public void setDataEncerramentoContratoCobranca(
			Date dataEncerramentoContratoCobranca) {
		this.dataEncerramentoContratoCobranca = dataEncerramentoContratoCobranca;
	}

	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}

	public Integer getQuantidadeMesValidoPagamento() {
		return quantidadeMesValidoPagamento;
	}

	public void setQuantidadeMesValidoPagamento(Integer quantidadeMesValidoPagamento) {
		this.quantidadeMesValidoPagamento = quantidadeMesValidoPagamento;
	}
}
