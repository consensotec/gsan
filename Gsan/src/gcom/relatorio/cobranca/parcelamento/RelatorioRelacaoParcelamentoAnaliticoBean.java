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
package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0878] Gerar Rela��o de Parcelamento - Vis�o Analitica
 * 
 * Bean que preencher� o relatorio
 * 
 * @author Bruno Barros
 * @date 04/02/2009
 *
 */
public class RelatorioRelacaoParcelamentoAnaliticoBean implements RelatorioBean {
	
	private String nome;
	private String matricula;
	private String telefone;
	private String unidade;
	private String numero;
	private Integer vencimento;
	private BigDecimal debito;
	private BigDecimal valorParcela;
	private BigDecimal valorEntrada;
	private Date data;
	private Integer parcela;
	private Integer qtdParcelasQuitadas;
	private Integer qtdParcelasCobradas;
	private Integer qtdParcelasACobrar;
	private BigDecimal valorParcelasQuitadas;
	private BigDecimal valorCobradas;
	private BigDecimal valorACobrar;
	private Integer idGerencia;
	private Integer idLocalidade;
	private Integer idMunicipio;
	private String descricaoGerencia;
	private String descricaoLocalidade;
	private String descricaoMunicipio;
	private String login;
	
	
	/**
	 * 
	 * Cria uma instancia do bean para o relat�rio
	 * 
	 * @param nome
	 * @param matricula
	 * @param telefone
	 * @param unidade
	 * @param numero
	 * @param vencimento
	 * @param debito
	 * @param valorParcelamento
	 * @param valorEntrada
	 * @param data
	 * @param parcela
	 * @param qtdParcelasQuitadas
	 * @param qtdParcelasCobradas
	 * @param qtdParcelasACobrar
	 * @param valorParcelasQuitadas
	 * @param valorCobradas
	 * @param valorACobrar
	 * @param idGerencia
	 * @param idLocalidade
	 * @param idMunicipio
	 * @param descricaoMunicipio
	 */
	public RelatorioRelacaoParcelamentoAnaliticoBean(
			String nome, 
			String matricula, 
			String telefone, 
			String unidade, 
			String numero, 
			Integer vencimento, 
			BigDecimal debito, 
			BigDecimal valorParcela, 
			BigDecimal valorEntrada, 
			Date data, 
			Integer parcela, 
			Integer qtdParcelasQuitadas, 
			Integer qtdParcelasCobradas, 
			Integer qtdParcelasACobrar, 
			BigDecimal valorParcelasQuitadas, 
			BigDecimal valorCobradas, 
			BigDecimal valorACobrar, 
			Integer idGerencia, 
			Integer idLocalidade,
			Integer idMunicipio,
			String descricaoGerencia, 
			String descricaoLocalidade,
			String descricaoMunicipio,
			String login) {
		super();

		this.nome = nome;
		this.matricula = matricula;
		this.telefone = telefone;
		this.unidade = unidade;
		this.numero = numero;
		this.vencimento = vencimento;
		this.debito = debito;
		this.valorParcela = valorParcela;
		this.valorEntrada = valorEntrada;
		this.data = data;
		this.parcela = parcela;
		this.qtdParcelasQuitadas = qtdParcelasQuitadas;
		this.qtdParcelasCobradas = qtdParcelasCobradas;
		this.qtdParcelasACobrar = qtdParcelasACobrar;
		this.valorParcelasQuitadas = valorParcelasQuitadas;
		this.valorCobradas = valorCobradas;
		this.valorACobrar = valorACobrar;
		this.idGerencia = idGerencia;
		this.idLocalidade = idLocalidade;
		this.idMunicipio = idMunicipio;
		this.descricaoGerencia = idGerencia + " " + descricaoGerencia;
		this.descricaoLocalidade = idLocalidade + " " + descricaoLocalidade;
		if(idMunicipio != null && descricaoMunicipio != null){
			this.descricaoMunicipio = idMunicipio + " " + descricaoMunicipio;
		}
		this.login = login;
	}
	public Date getData() {
		return data;
	}
	public BigDecimal getDebito() {
		return debito;
	}
	public Integer getIdGerencia() {
		return idGerencia;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public String getNumero() {
		return numero;
	}
	public Integer getParcela() {
		return parcela;
	}
	public Integer getQtdParcelasACobrar() {
		return qtdParcelasACobrar;
	}
	public Integer getQtdParcelasCobradas() {
		return qtdParcelasCobradas;
	}
	public Integer getQtdParcelasQuitadas() {
		return qtdParcelasQuitadas;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getUnidade() {
		return unidade;
	}
	public BigDecimal getValorACobrar() {
		return valorACobrar;
	}
	public BigDecimal getValorCobradas() {
		return valorCobradas;
	}
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public BigDecimal getValorParcelasQuitadas() {
		return valorParcelasQuitadas;
	}
	public Integer getVencimento() {
		return vencimento;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
}