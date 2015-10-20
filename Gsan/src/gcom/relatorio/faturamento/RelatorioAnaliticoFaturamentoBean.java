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
package gcom.relatorio.faturamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */
public class RelatorioAnaliticoFaturamentoBean implements RelatorioBean {

	private String idImovel;
	private BigDecimal consumoAgua;
	private BigDecimal consumoRateioAgua;
	private BigDecimal valorAgua;
	private BigDecimal consumoEsgoto;
	private BigDecimal consumoRateioEsgoto;
	private BigDecimal valorEsgoto;
	private BigDecimal debitos;
	private BigDecimal valorCreditos;
	private BigDecimal totalConta;
	private String totalGeral;
	private String codigoSetorComercial;
	private String inscricao;
	private String idLocalidade;
	private String codigoBarra;
	
	private String idDescricaoLocalidade;
	private String idDescricaoUnidadeNegocio;
	
	private String nomeCliente;
	
	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public RelatorioAnaliticoFaturamentoBean(
			 String idImovel,
			 BigDecimal consumoAgua,
			 BigDecimal consumoRateioAgua,
			 BigDecimal valorAgua,
			 BigDecimal consumoEsgoto,
			 BigDecimal consumoRateioEsgoto,
			 BigDecimal valorEsgoto,
			 BigDecimal debitos,
			 BigDecimal valorCreditos,
			 BigDecimal totalConta,
			 String totalGeral,
			 String codigoSetorComercial,
			 String inscricao,
			 String idLocalidade,
			 String codigoBarra,
			 String idDescricaoLocalidade,
			 String idDescricaoUnidadeNegocio,
			 String nomeCliente) {
		
		this.idImovel = idImovel;
		this.consumoAgua = consumoAgua;
		this.consumoRateioAgua = consumoRateioAgua;
		this.valorAgua = valorAgua;
		this.consumoEsgoto = consumoEsgoto;
		this.consumoRateioEsgoto = consumoRateioEsgoto;
		this.valorEsgoto = valorEsgoto;
		this.debitos = debitos;
		this.valorCreditos = valorCreditos;
		this.totalConta = totalConta;
		this.totalGeral = totalGeral;
		this.codigoSetorComercial = codigoSetorComercial;
		this.inscricao = inscricao;
		this.idLocalidade = idLocalidade;
		this.codigoBarra = codigoBarra;
		
		this.idDescricaoLocalidade = idDescricaoLocalidade;
		this.idDescricaoUnidadeNegocio = idDescricaoUnidadeNegocio;
		this.nomeCliente = nomeCliente;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public BigDecimal getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(BigDecimal consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public BigDecimal getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(BigDecimal consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public BigDecimal getConsumoRateioAgua() {
		return consumoRateioAgua;
	}

	public void setConsumoRateioAgua(BigDecimal consumoRateioAgua) {
		this.consumoRateioAgua = consumoRateioAgua;
	}

	public BigDecimal getConsumoRateioEsgoto() {
		return consumoRateioEsgoto;
	}

	public void setConsumoRateioEsgoto(BigDecimal consumoRateioEsgoto) {
		this.consumoRateioEsgoto = consumoRateioEsgoto;
	}

	public BigDecimal getDebitos() {
		return debitos;
	}

	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public BigDecimal getTotalConta() {
		return totalConta;
	}

	public void setTotalConta(BigDecimal totalConta) {
		this.totalConta = totalConta;
	}

	public String getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(String totalGeral) {
		this.totalGeral = totalGeral;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorCreditos() {
		return valorCreditos;
	}

	public void setValorCreditos(BigDecimal valorCreditos) {
		this.valorCreditos = valorCreditos;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public String getIdDescricaoLocalidade() {
		return idDescricaoLocalidade;
	}

	public void setIdDescricaoLocalidade(String idDescricaoLocalidade) {
		this.idDescricaoLocalidade = idDescricaoLocalidade;
	}

	public String getIdDescricaoUnidadeNegocio() {
		return idDescricaoUnidadeNegocio;
	}

	public void setIdDescricaoUnidadeNegocio(String idDescricaoUnidadeNegocio) {
		this.idDescricaoUnidadeNegocio = idDescricaoUnidadeNegocio;
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
	
}
