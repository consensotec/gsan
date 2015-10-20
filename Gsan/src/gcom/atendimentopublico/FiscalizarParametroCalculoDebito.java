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
package gcom.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.faturamento.debito.DebitoACobrar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * RM1165 - Registrar em tabela os par�metros que foram utilizados para calcular o valor do 
 *          d�bito a cobrar gerado decorrente da situa��o de fiscaliza��o informada
 * UC0488 - Informar Retorno Ordem de Fiscaliza��o
 * Analista: Cl�udio Lira
 * 
 * [SB0012] - Registrar  par�metros utilizados para calcular o valor do d�bito
 * 
 * @autor Th�lio Ara�jo
 * @since 13/12/2011
 */

/** @author Hibernate CodeGenerator */
public class FiscalizarParametroCalculoDebito implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** identifier field */
    private Integer anoMesApuracaoInicial;

    /** identifier field */
    private Integer anoMesApuracaoFinal;

    /** identifier field */
    private Integer numeroMesesApurado;

    /** identifier field */
    private Integer consumoAgua;
    
    /** identifier field */
    private Integer consumoEsgoto;
    
    /** persistent field */
    private BigDecimal valorAguar;
    
    /** persistent field */
    private BigDecimal valorEsgoto;

    /** persistent field */
    private Integer consumoMinimo;
    
    /** persistent field */
    private BigDecimal percentualEsgoto;

    /** persistent field */
    private Integer qtdEconomiaAutoInfracao;
    
    /** persistent field */
    private BigDecimal valorTarifaAutoInfracao;
    
    /** persistent field */
    private Integer codCalculoConsumo;
    
    /** persistent field */
	private Date dataUltimaAlteracao;
	
	/** persistent field */
	private BigDecimal fatorMultiplicador;
	
	/** persistent field */
	private BigDecimal fatorReincidencia;
    
	/** persistent field */
	private DebitoACobrar debitoACobrar;
	
	/** persistent field */
	private Categoria categoria;
	
	/** persistent field */
	private Subcategoria subcategoria;
	
	//RM1165
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	private Short numeroMesesFatura;
	private Short numeroMaximoMesesSancoes;
	private Date dataCorte;
	private Date dataSupressaoAgua;
	private Date dataGeracaoDebito;	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesApuracaoInicial() {
		return anoMesApuracaoInicial;
	}

	public void setAnoMesApuracaoInicial(Integer anoMesApuracaoInicial) {
		this.anoMesApuracaoInicial = anoMesApuracaoInicial;
	}

	public Integer getAnoMesApuracaoFinal() {
		return anoMesApuracaoFinal;
	}

	public void setAnoMesApuracaoFinal(Integer anoMesApuracaoFinal) {
		this.anoMesApuracaoFinal = anoMesApuracaoFinal;
	}

	public Integer getNumeroMesesApurado() {
		return numeroMesesApurado;
	}

	public void setNumeroMesesApurado(Integer numeroMesesApurado) {
		this.numeroMesesApurado = numeroMesesApurado;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}

	public BigDecimal getValorAguar() {
		return valorAguar;
	}

	public void setValorAguar(BigDecimal valorAguar) {
		this.valorAguar = valorAguar;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public Integer getConsumoMinimo() {
		return consumoMinimo;
	}

	public void setConsumoMinimo(Integer consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public BigDecimal getPercentualEsgoto() {
		return percentualEsgoto;
	}

	public void setPercentualEsgoto(BigDecimal percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	public Integer getQtdEconomiaAutoInfracao() {
		return qtdEconomiaAutoInfracao;
	}

	public void setQtdEconomiaAutoInfracao(Integer qtdEconomiaAutoInfracao) {
		this.qtdEconomiaAutoInfracao = qtdEconomiaAutoInfracao;
	}

	public BigDecimal getValorTarifaAutoInfracao() {
		return valorTarifaAutoInfracao;
	}

	public void setValorTarifaAutoInfracao(BigDecimal valorTarifaAutoInfracao) {
		this.valorTarifaAutoInfracao = valorTarifaAutoInfracao;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public DebitoACobrar getDebitoACobrar() {
		return debitoACobrar;
	}

	public void setDebitoACobrar(DebitoACobrar debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Integer getCodCalculoConsumo() {
		return codCalculoConsumo;
	}

	public void setCodCalculoConsumo(Integer codCalculoConsumo) {
		this.codCalculoConsumo = codCalculoConsumo;
	}

	public BigDecimal getFatorMultiplicador() {
		return fatorMultiplicador;
	}

	public void setFatorMultiplicador(BigDecimal fatorMultiplicador) {
		this.fatorMultiplicador = fatorMultiplicador;
	}

	public BigDecimal getFatorReincidencia() {
		return fatorReincidencia;
	}

	public void setFatorReincidencia(BigDecimal fatorReincidencia) {
		this.fatorReincidencia = fatorReincidencia;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Short getNumeroMesesFatura() {
		return numeroMesesFatura;
	}

	public void setNumeroMesesFatura(Short numeroMesesFatura) {
		this.numeroMesesFatura = numeroMesesFatura;
	}

	public Short getNumeroMaximoMesesSancoes() {
		return numeroMaximoMesesSancoes;
	}

	public void setNumeroMaximoMesesSancoes(Short numeroMaximoMesesSancoes) {
		this.numeroMaximoMesesSancoes = numeroMaximoMesesSancoes;
	}

	public Date getDataCorte() {
		return dataCorte;
	}

	public void setDataCorte(Date dataCorte) {
		this.dataCorte = dataCorte;
	}

	public Date getDataSupressaoAgua() {
		return dataSupressaoAgua;
	}

	public void setDataSupressaoAgua(Date dataSupressaoAgua) {
		this.dataSupressaoAgua = dataSupressaoAgua;
	}

	public Date getDataGeracaoDebito() {
		return dataGeracaoDebito;
	}

	public void setDataGeracaoDebito(Date dataGeracaoDebito) {
		this.dataGeracaoDebito = dataGeracaoDebito;
	}
}
