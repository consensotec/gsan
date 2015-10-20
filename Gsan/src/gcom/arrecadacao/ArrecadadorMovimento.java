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
package gcom.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ArrecadadorMovimento implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date dataGeracao;

	/** nullable persistent field */
	private Integer numeroSequencialArquivo;

	/** nullable persistent field */
	private String codigoConvenio;

	/** nullable persistent field */
	private String nomeEmpresa;

	/** nullable persistent field */
	private Short codigoBanco;

	/** nullable persistent field */
	private Integer numeroVersaoLayout;

	/** nullable persistent field */
	private Short codigoRemessa;

	/** nullable persistent field */
	private String nomeBanco;

	/** nullable persistent field */
	private String descricaoIdentificacaoServico;

	/** nullable persistent field */
	private Integer numeroRegistrosMovimento;

	/** nullable persistent field */
	private BigDecimal valorTotalMovimento;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private Set arrecadadorMovimentoItens;
	
	private Set avisoBancarios;

	public Set getAvisoBancarios() {
		return avisoBancarios;
	}

	public void setAvisoBancarios(Set avisoBancarios) {
		this.avisoBancarios = avisoBancarios;
	}

	/** full constructor */
	public ArrecadadorMovimento(Date dataGeracao,
			Integer numeroSequencialArquivo, String codigoConvenio,
			String nomeEmpresa, Short codigoBanco, Integer numeroVersaoLayout,
			Short codigoRemessa, String nomeBanco,
			String descricaoIdentificacaoServico,
			Integer numeroRegistrosMovimento, BigDecimal valorTotalMovimento,
			Date ultimaAlteracao) {
		this.dataGeracao = dataGeracao;
		this.numeroSequencialArquivo = numeroSequencialArquivo;
		this.codigoConvenio = codigoConvenio;
		this.nomeEmpresa = nomeEmpresa;
		this.codigoBanco = codigoBanco;
		this.numeroVersaoLayout = numeroVersaoLayout;
		this.codigoRemessa = codigoRemessa;
		this.nomeBanco = nomeBanco;
		this.descricaoIdentificacaoServico = descricaoIdentificacaoServico;
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
		this.valorTotalMovimento = valorTotalMovimento;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ArrecadadorMovimento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataGeracao() {
		return this.dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Integer getNumeroSequencialArquivo() {
		return this.numeroSequencialArquivo;
	}

	public void setNumeroSequencialArquivo(Integer numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}

	public String getCodigoConvenio() {
		return this.codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getNomeEmpresa() {
		return this.nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Short getCodigoBanco() {
		return this.codigoBanco;
	}

	public void setCodigoBanco(Short codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Integer getNumeroVersaoLayout() {
		return this.numeroVersaoLayout;
	}

	public void setNumeroVersaoLayout(Integer numeroVersaoLayout) {
		this.numeroVersaoLayout = numeroVersaoLayout;
	}

	public Short getCodigoRemessa() {
		return this.codigoRemessa;
	}

	public void setCodigoRemessa(Short codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	public String getNomeBanco() {
		return this.nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getDescricaoIdentificacaoServico() {
		return this.descricaoIdentificacaoServico;
	}

	public void setDescricaoIdentificacaoServico(
			String descricaoIdentificacaoServico) {
		this.descricaoIdentificacaoServico = descricaoIdentificacaoServico;
	}

	public Integer getNumeroRegistrosMovimento() {
		return this.numeroRegistrosMovimento;
	}

	public void setNumeroRegistrosMovimento(Integer numeroRegistrosMovimento) {
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
	}

	public BigDecimal getValorTotalMovimento() {
		return this.valorTotalMovimento;
	}

	public void setValorTotalMovimento(BigDecimal valorTotalMovimento) {
		this.valorTotalMovimento = valorTotalMovimento;
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

	public Set getArrecadadorMovimentoItens() {
		return arrecadadorMovimentoItens;
	}

	public void setArrecadadorMovimentoItens(Set arrecadadorMovimentoItens) {
		this.arrecadadorMovimentoItens = arrecadadorMovimentoItens;
	}

}
