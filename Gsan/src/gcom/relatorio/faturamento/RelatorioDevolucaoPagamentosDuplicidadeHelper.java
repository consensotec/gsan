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
package gcom.relatorio.faturamento;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * classe respons�vel por criar o Relat�rio Devolu��o dos Pagamentos em Duplicidade.
 * 
 * [UC1129] Gerar Relat�rio Devolu��o dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */
public class RelatorioDevolucaoPagamentosDuplicidadeHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idLocalidade;
	private String nomeLocalidade;
	private String idGerencia;
	private String nomeGerencia;
	private String idUnidade;
	private String nomeUnidade;
	private String numeroRA;
	private String matricula;
	private String mesAnoPagamentoDuplicidade;
	private BigDecimal valorPagamentoDuplicidade;
	private String mesAnoConta;
	private BigDecimal valorConta;
	private BigDecimal creditoRealizado;
	private BigDecimal creditoARealizar;
	private String dataAtualizacao;
	
	public BigDecimal getCreditoARealizar() {
		return creditoARealizar;
	}
	
	public void setCreditoARealizar(BigDecimal creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	public BigDecimal getCreditoRealizado() {
		return creditoRealizado;
	}

	public void setCreditoRealizado(BigDecimal creditoRealizado) {
		this.creditoRealizado = creditoRealizado;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getMesAnoPagamentoDuplicidade() {
		return mesAnoPagamentoDuplicidade;
	}

	public void setMesAnoPagamentoDuplicidade(String mesAnoPagamentoDuplicidade) {
		this.mesAnoPagamentoDuplicidade = mesAnoPagamentoDuplicidade;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorPagamentoDuplicidade() {
		return valorPagamentoDuplicidade;
	}

	public void setValorPagamentoDuplicidade(BigDecimal valorPagamentoDuplicidade) {
		this.valorPagamentoDuplicidade = valorPagamentoDuplicidade;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RelatorioDevolucaoPagamentosDuplicidadeHelper)) {
			return false;
		} else {
			RelatorioDevolucaoPagamentosDuplicidadeHelper helper = (RelatorioDevolucaoPagamentosDuplicidadeHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
//			return propriedadesIguais(this.numeroRA,helper.numeroRA) && 
//				propriedadesIguais(this.matricula, helper.matricula) && 
//				propriedadesIguais(this.mesAnoPagamentoDuplicidade, helper.mesAnoPagamentoDuplicidade);

			return propriedadesIguais(this.numeroRA,helper.numeroRA);

		}
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(String pro1, String pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}
	
	
	
	
}