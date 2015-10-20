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
package gcom.faturamento.bean;

import gcom.relatorio.faturamento.RelatorioFaturasAgrupadasBean;
import gcom.util.serializacao.Campo;

import java.math.BigDecimal;

/**
 * [UC1684] - Gerar Arquivo Exporta��o Faturas
 * Identifica��o das faturas
 * 
 * @author Andr� Miranda
 * @date 09/06/2015
 */
public class ArquivoExportacaoFaturasAgrupadasRegistro03Helper {
	private String matriculaImovel;
	private String nomeClienteUsuario;
	private String sistema; // C�digo da Localidade + Nome da Localidade
	private String numeroHidrometro;
	private String tipoLigacao;
	private String leitura;
	private String consumo;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private BigDecimal valorDebito;
	private BigDecimal valorCredito;
	private BigDecimal retencaoImpostos;
	private BigDecimal valorTotal;

	public ArquivoExportacaoFaturasAgrupadasRegistro03Helper() { }

	public ArquivoExportacaoFaturasAgrupadasRegistro03Helper(RelatorioFaturasAgrupadasBean fatura) {
		matriculaImovel = fatura.getMatricula();
		nomeClienteUsuario = fatura.getNomeUsuario();
		sistema = fatura.getSistema();
		numeroHidrometro = fatura.getNumeroHidrometro();
		tipoLigacao = fatura.getTipo();
		leitura = fatura.getLeitura();
		consumo = fatura.getConsumo();
		valorAgua = fatura.getValorAgua();
		valorEsgoto = fatura.getValorEsgoto();
		valorDebito = fatura.getValorDebito();
		valorCredito = fatura.getValorCredito();
		retencaoImpostos = fatura.getRetencao();
		valorTotal = fatura.getValor();
	}

	@Campo(posicao = 1)
	public String getTipoRegistro() {
		return "03";
	}

	@Campo(posicao = 2)
	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	@Campo(posicao = 3)
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	@Campo(posicao = 4)
	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	@Campo(posicao = 5)
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	@Campo(posicao = 6)
	public String getTipoLigacao() {
		return tipoLigacao;
	}

	public void setTipoLigacao(String tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
	}

	@Campo(posicao = 7)
	public String getLeitura() {
		return leitura;
	}

	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}

	@Campo(posicao = 8)
	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	@Campo(posicao = 9, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	@Campo(posicao = 10, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	@Campo(posicao = 11, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	@Campo(posicao = 12, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	@Campo(posicao = 13, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getRetencaoImpostos() {
		return retencaoImpostos;
	}

	public void setRetencaoImpostos(BigDecimal retencaoImpostos) {
		this.retencaoImpostos = retencaoImpostos;
	}

	@Campo(posicao = 14, formato = Campo.FORMATO_MOEDA)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
