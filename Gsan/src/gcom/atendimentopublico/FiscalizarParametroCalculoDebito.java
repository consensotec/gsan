/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
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
 * RM1165 - Registrar em tabela os parâmetros que foram utilizados para calcular o valor do 
 *          débito a cobrar gerado decorrente da situação de fiscalização informada
 * UC0488 - Informar Retorno Ordem de Fiscalização
 * Analista: Cláudio Lira
 * 
 * [SB0012] - Registrar  parâmetros utilizados para calcular o valor do débito
 * 
 * @autor Thúlio Araújo
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
