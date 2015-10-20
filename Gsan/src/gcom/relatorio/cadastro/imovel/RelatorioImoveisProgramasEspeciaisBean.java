/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.relatorio.cadastro.imovel;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * classe respons�vel por criar o relat�rio de imoveis por Programas Especiais
 * 
 * @author Hugo Leonardo
 * @created 18/01/2010
 */
public class RelatorioImoveisProgramasEspeciaisBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	//private Integer idUnidadeNegocio;
	//private String nomeUnidadeNegocio;
	private Integer idRegiaoDesenvolvimento;
	private String nomeRegiaoDesenvolvimento;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private String idImovel;
	private String nomeUsuario;
	private String situacaoMedicao;
	private BigDecimal valorConta;
	private Integer consumoAgua;
	private String endereco;
	
	private Integer qtdImoveisSemHidr;
	private BigDecimal valorContasSemHidr;
	private Integer qtdImoveisComHidr;
	private BigDecimal valorContasComHidr;
	
	private Integer qtdTotalImoveis;
	private BigDecimal valorTotalContas;
	

	public RelatorioImoveisProgramasEspeciaisBean(Integer idRegiaoDesenvolvimento, String nomeRegiaoDesenvolvimento, 
			Integer idLocalidade, String nomeLocalidade, String idImovel, String nomeUsuario, String situacaoMedicao,
			Integer consumoAgua, BigDecimal valorConta, String endereco) {
		
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.idImovel = idImovel;
		this.nomeUsuario = nomeUsuario;
		this.situacaoMedicao = situacaoMedicao;
		this.valorConta = valorConta;
		this.consumoAgua = consumoAgua;
		this.endereco = endereco;
	}
	
	public RelatorioImoveisProgramasEspeciaisBean(Integer idRegiaoDesenvolvimento, String nomeRegiaoDesenvolvimento, 
			Integer idLocalidade, String nomeLocalidade, Integer qtdImoveisSemHidr,
			BigDecimal valorContasSemHidr, Integer qtdImoveisComHidr, BigDecimal valorContasComHidr, 
			Integer qtdTotalImoveis, BigDecimal valorTotalContas){
		
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
		this.idLocalidade = idLocalidade;
		this.nomeLocalidade = nomeLocalidade;
		this.qtdImoveisSemHidr = qtdImoveisSemHidr;
		this.valorContasSemHidr = valorContasSemHidr;
		this.qtdImoveisComHidr = qtdImoveisComHidr;
		this.valorContasComHidr = valorContasComHidr;
		this.qtdTotalImoveis = qtdTotalImoveis;
		this.valorTotalContas = valorTotalContas;
	}

	/**
	 * @return Returns the consumoAgua.
	 */
	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	/**
	 * @param consumoAgua The consumoAgua to set.
	 */
	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	/**
	 * @return Returns the idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel The idImovel to set.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Returns the idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Returns the nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade The nomeLocalidade to set.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Returns the nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario The nomeUsuario to set.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Returns the situacaoMedicao.
	 */
	public String getSituacaoMedicao() {
		return situacaoMedicao;
	}

	/**
	 * @param situacaoMedicao The situacaoMedicao to set.
	 */
	public void setSituacaoMedicao(String situacaoMedicao) {
		this.situacaoMedicao = situacaoMedicao;
	}

	/**
	 * @return Returns the valorConta.
	 */
	public BigDecimal getValorConta() {
		return valorConta;
	}

	/**
	 * @param valorConta The valorConta to set.
	 */
	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	/**
	 * @return Returns the endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco The endereco to set.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Returns the qtdImoveisComHidr.
	 */
	public Integer getQtdImoveisComHidr() {
		return qtdImoveisComHidr;
	}

	/**
	 * @param qtdImoveisComHidr The qtdImoveisComHidr to set.
	 */
	public void setQtdImoveisComHidr(Integer qtdImoveisComHidr) {
		this.qtdImoveisComHidr = qtdImoveisComHidr;
	}

	/**
	 * @return Returns the qtdImoveisSemHidr.
	 */
	public Integer getQtdImoveisSemHidr() {
		return qtdImoveisSemHidr;
	}

	/**
	 * @param qtdImoveisSemHidr The qtdImoveisSemHidr to set.
	 */
	public void setQtdImoveisSemHidr(Integer qtdImoveisSemHidr) {
		this.qtdImoveisSemHidr = qtdImoveisSemHidr;
	}

	/**
	 * @return Returns the valorContasComHidr.
	 */
	public BigDecimal getValorContasComHidr() {
		return valorContasComHidr;
	}

	/**
	 * @param valorContasComHidr The valorContasComHidr to set.
	 */
	public void setValorContasComHidr(BigDecimal valorContasComHidr) {
		this.valorContasComHidr = valorContasComHidr;
	}

	/**
	 * @return Returns the valorContasSemHidr.
	 */
	public BigDecimal getValorContasSemHidr() {
		return valorContasSemHidr;
	}

	/**
	 * @param valorContasSemHidr The valorContasSemHidr to set.
	 */
	public void setValorContasSemHidr(BigDecimal valorContasSemHidr) {
		this.valorContasSemHidr = valorContasSemHidr;
	}

	/**
	 * @return Returns the qtdTotalImoveis.
	 */
	public Integer getQtdTotalImoveis() {
		return qtdTotalImoveis;
	}

	/**
	 * @param qtdTotalImoveis The qtdTotalImoveis to set.
	 */
	public void setQtdTotalImoveis(Integer qtdTotalImoveis) {
		this.qtdTotalImoveis = qtdTotalImoveis;
	}

	/**
	 * @return Returns the valorTotalContas.
	 */
	public BigDecimal getValorTotalContas() {
		return valorTotalContas;
	}

	/**
	 * @param valorTotalContas The valorTotalContas to set.
	 */
	public void setValorTotalContas(BigDecimal valorTotalContas) {
		this.valorTotalContas = valorTotalContas;
	}

	public Integer getIdRegiaoDesenvolvimento() {
		return idRegiaoDesenvolvimento;
	}

	public void setIdRegiaoDesenvolvimento(Integer idRegiaoDesenvolvimento) {
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
	}

	public String getNomeRegiaoDesenvolvimento() {
		return nomeRegiaoDesenvolvimento;
	}

	public void setNomeRegiaoDesenvolvimento(String nomeRegiaoDesenvolvimento) {
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
	}
	
}
