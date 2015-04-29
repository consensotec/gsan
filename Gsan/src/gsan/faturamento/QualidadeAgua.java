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
* R�mulo Aur�lio de Melo Souza Filho
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
package gsan.faturamento;


import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.interceptor.ObjetoTransacao;
import gsan.operacional.FonteCaptacao;
import gsan.operacional.SistemaAbastecimento;
import gsan.util.filtro.Filtro;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * Representa a Qualidade da Agua
 *
 * @author Thiago Toscano
 * @date 24/05/2006
 */
public class QualidadeAgua extends ObjetoTransacao{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private Date ultimaAlteracao;

    private Integer anoMesReferencia;

    private BigDecimal numeroIndiceTurbidez;

    private BigDecimal numeroCloroResidual;
    
    private BigDecimal numeroIndicePh;
    
    private BigDecimal numeroIndiceCor;
    
    private BigDecimal numeroIndiceFluor;
    
    private BigDecimal numeroIndiceFerro;
    
    private BigDecimal numeroIndiceColiformesTotais;
    
    private BigDecimal numeroIndiceColiformesFecais;
    
    private BigDecimal numeroNitrato;

    private Localidade localidade;
    
    private SetorComercial setorComercial;
    
    private Integer quantidadeTurbidezExigidas;
    
    private Integer quantidadeTurbidezAnalisadas;
    
    private Integer quantidadeTurbidezConforme;
    
    private Integer quantidadeCorExigidas;
    
    private Integer quantidadeCorAnalisadas;
    
    private Integer quantidadeCorConforme;
    
    private Integer quantidadeCloroExigidas;
    
    private Integer quantidadeCloroAnalisadas;
    
    private Integer quantidadeCloroConforme;
    
    private Integer quantidadeFluorExigidas;
    
    private Integer quantidadeFluorAnalisadas;
    
    private Integer quantidadeFluorConforme;
    
    private Integer quantidadeColiformesTotaisExigidas;
    
    private Integer quantidadeColiformesTotaisAnalisadas;
    
    private Integer quantidadeColiformesTotaisConforme;
    
    private Integer quantidadeColiformesFecaisExigidas;
    
    private Integer quantidadeColiformesFecaisAnalisadas;
    
    private Integer quantidadeColiformesFecaisConforme;
    
    private Integer quantidadeColiformesTermotolerantesExigidas;
    
    private BigDecimal numeroIndiceColiformesTermotolerantes;
    
    private Integer quantidadeColiformesTermotolerantesAnalisadas;
    
    private Integer quantidadeColiformesTermotolerantesConforme;
    
    private FonteCaptacao fonteCaptacao;
    
    private BigDecimal numeroIndiceAlcalinidade;
    
    private Integer quantidadeAlcalinidadeExigidas;
    
    private Integer quantidadeAlcalinidadeAnalisadas;
    
    private Integer quantidadeAlcalinidadeConforme;
    
    private SistemaAbastecimento sistemaAbastecimento;
    
    private Integer indiceDurezaTotal;
    
    private Integer quantidadeDurezaConforme;
    
    private Integer quantidadeDurezaExigida;
    
    private Integer quantidadeDurezaAnalisada;
    
    private Integer quantidadePhConforme;
    
    private Integer quantidadePhExigida;
    
    private Integer quantidadePhAnalisada;
    
    public QualidadeAgua(){
    	
    }

	public QualidadeAgua(Integer id, Date ultimaAlteracao, Integer anoMesReferencia, BigDecimal numeroIndiceTurbidez, BigDecimal numeroCloroResidual, Localidade localidade) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.anoMesReferencia = anoMesReferencia;
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
		this.numeroCloroResidual = numeroCloroResidual;
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo numeroCloroResidual.
	 */
	public BigDecimal getNumeroCloroResidual() {
		return numeroCloroResidual;
	}

	/**
	 * @param numeroCloroResidual O numeroCloroResidual a ser setado.
	 */
	public void setNumeroCloroResidual(BigDecimal numeroCloroResidual) {
		this.numeroCloroResidual = numeroCloroResidual;
	}

	/**
	 * @return Retorna o campo numeroIndiceTurbidez.
	 */
	public BigDecimal getNumeroIndiceTurbidez() {
		return numeroIndiceTurbidez;
	}

	/**
	 * @param numeroIndiceTurbidez O numeroIndiceTurbidez a ser setado.
	 */
	public void setNumeroIndiceTurbidez(BigDecimal numeroIndiceTurbidez) {
		this.numeroIndiceTurbidez = numeroIndiceTurbidez;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getNumeroIndiceColiformesFecais() {
		return numeroIndiceColiformesFecais;
	}

	public void setNumeroIndiceColiformesFecais(
			BigDecimal numeroIndiceColiformesFecais) {
		this.numeroIndiceColiformesFecais = numeroIndiceColiformesFecais;
	}

	public BigDecimal getNumeroIndiceColiformesTotais() {
		return numeroIndiceColiformesTotais;
	}

	public void setNumeroIndiceColiformesTotais(
			BigDecimal numeroIndiceColiformesTotais) {
		this.numeroIndiceColiformesTotais = numeroIndiceColiformesTotais;
	}

	public BigDecimal getNumeroIndiceCor() {
		return numeroIndiceCor;
	}

	public void setNumeroIndiceCor(BigDecimal numeroIndiceCor) {
		this.numeroIndiceCor = numeroIndiceCor;
	}

	public BigDecimal getNumeroIndiceFerro() {
		return numeroIndiceFerro;
	}

	public void setNumeroIndiceFerro(BigDecimal numeroIndiceFerro) {
		this.numeroIndiceFerro = numeroIndiceFerro;
	}

	public BigDecimal getNumeroIndiceFluor() {
		return numeroIndiceFluor;
	}

	public void setNumeroIndiceFluor(BigDecimal numeroIndiceFluor) {
		this.numeroIndiceFluor = numeroIndiceFluor;
	}

	public BigDecimal getNumeroIndicePh() {
		return numeroIndicePh;
	}

	public void setNumeroIndicePh(BigDecimal numeroIndicePh) {
		this.numeroIndicePh = numeroIndicePh;
	}

	public BigDecimal getNumeroNitrato() {
		return numeroNitrato;
	}

	public void setNumeroNitrato(BigDecimal numeroNitrato) {
		this.numeroNitrato = numeroNitrato;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}
	
	
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroQualidadeAgua filtroQualidadeAgua = new FiltroQualidadeAgua();
		
		filtroQualidadeAgua.adicionarParametro(new ParametroSimples(FiltroQualidadeAgua.ID,this.getId()));
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		

		return filtroQualidadeAgua;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	
	public String getMesAno(){
		String anoMesStr = this.anoMesReferencia + "";
		String mesAno = "";
		
		mesAno = anoMesStr.substring(4,6) + "/" + anoMesStr.substring(0,4)  ;
		
		return mesAno;
	}

	public BigDecimal getNumeroIndiceColiformesTermotolerantes() {
		return numeroIndiceColiformesTermotolerantes;
	}

	public void setNumeroIndiceColiformesTermotolerantes(
			BigDecimal numeroIndiceColiformesTermotolerantes) {
		this.numeroIndiceColiformesTermotolerantes = numeroIndiceColiformesTermotolerantes;
	}

	public Integer getQuantidadeCloroAnalisadas() {
		return quantidadeCloroAnalisadas;
	}

	public void setQuantidadeCloroAnalisadas(Integer quantidadeCloroAnalisadas) {
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
	}

	public Integer getQuantidadeCloroConforme() {
		return quantidadeCloroConforme;
	}

	public void setQuantidadeCloroConforme(Integer quantidadeCloroConforme) {
		this.quantidadeCloroConforme = quantidadeCloroConforme;
	}

	public Integer getQuantidadeCloroExigidas() {
		return quantidadeCloroExigidas;
	}

	public void setQuantidadeCloroExigidas(Integer quantidadeCloroExigidas) {
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
	}

	public Integer getQuantidadeColiformesFecaisAnalisadas() {
		return quantidadeColiformesFecaisAnalisadas;
	}

	public void setQuantidadeColiformesFecaisAnalisadas(
			Integer quantidadeColiformesFecaisAnalisadas) {
		this.quantidadeColiformesFecaisAnalisadas = quantidadeColiformesFecaisAnalisadas;
	}

	public Integer getQuantidadeColiformesFecaisConforme() {
		return quantidadeColiformesFecaisConforme;
	}

	public void setQuantidadeColiformesFecaisConforme(
			Integer quantidadeColiformesFecaisConforme) {
		this.quantidadeColiformesFecaisConforme = quantidadeColiformesFecaisConforme;
	}

	public Integer getQuantidadeColiformesFecaisExigidas() {
		return quantidadeColiformesFecaisExigidas;
	}

	public void setQuantidadeColiformesFecaisExigidas(
			Integer quantidadeColiformesFecaisExigidas) {
		this.quantidadeColiformesFecaisExigidas = quantidadeColiformesFecaisExigidas;
	}

	public Integer getQuantidadeColiformesTermotolerantesAnalisadas() {
		return quantidadeColiformesTermotolerantesAnalisadas;
	}

	public void setQuantidadeColiformesTermotolerantesAnalisadas(
			Integer quantidadeColiformesTermotolerantesAnalisadas) {
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
	}

	public Integer getQuantidadeColiformesTermotolerantesConforme() {
		return quantidadeColiformesTermotolerantesConforme;
	}

	public void setQuantidadeColiformesTermotolerantesConforme(
			Integer quantidadeColiformesTermotolerantesConforme) {
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	}

	public Integer getQuantidadeColiformesTermotolerantesExigidas() {
		return quantidadeColiformesTermotolerantesExigidas;
	}

	public void setQuantidadeColiformesTermotolerantesExigidas(
			Integer quantidadeColiformesTermotolerantesExigidas) {
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
	}

	public Integer getQuantidadeColiformesTotaisAnalisadas() {
		return quantidadeColiformesTotaisAnalisadas;
	}

	public void setQuantidadeColiformesTotaisAnalisadas(
			Integer quantidadeColiformesTotaisAnalisadas) {
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
	}

	public Integer getQuantidadeColiformesTotaisConforme() {
		return quantidadeColiformesTotaisConforme;
	}

	public void setQuantidadeColiformesTotaisConforme(
			Integer quantidadeColiformesTotaisConforme) {
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
	}

	public Integer getQuantidadeColiformesTotaisExigidas() {
		return quantidadeColiformesTotaisExigidas;
	}

	public void setQuantidadeColiformesTotaisExigidas(
			Integer quantidadeColiformesTotaisExigidas) {
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
	}

	public Integer getQuantidadeCorAnalisadas() {
		return quantidadeCorAnalisadas;
	}

	public void setQuantidadeCorAnalisadas(Integer quantidadeCorAnalisadas) {
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
	}

	public Integer getQuantidadeCorConforme() {
		return quantidadeCorConforme;
	}

	public void setQuantidadeCorConforme(Integer quantidadeCorConforme) {
		this.quantidadeCorConforme = quantidadeCorConforme;
	}

	public Integer getQuantidadeCorExigidas() {
		return quantidadeCorExigidas;
	}

	public void setQuantidadeCorExigidas(Integer quantidadeCorExigidas) {
		this.quantidadeCorExigidas = quantidadeCorExigidas;
	}

	public Integer getQuantidadeFluorAnalisadas() {
		return quantidadeFluorAnalisadas;
	}

	public void setQuantidadeFluorAnalisadas(Integer quantidadeFluorAnalisadas) {
		this.quantidadeFluorAnalisadas = quantidadeFluorAnalisadas;
	}

	public Integer getQuantidadeFluorConforme() {
		return quantidadeFluorConforme;
	}

	public void setQuantidadeFluorConforme(Integer quantidadeFluorConforme) {
		this.quantidadeFluorConforme = quantidadeFluorConforme;
	}

	public Integer getQuantidadeFluorExigidas() {
		return quantidadeFluorExigidas;
	}

	public void setQuantidadeFluorExigidas(Integer quantidadeFluorExigidas) {
		this.quantidadeFluorExigidas = quantidadeFluorExigidas;
	}

	public Integer getQuantidadeTurbidezAnalisadas() {
		return quantidadeTurbidezAnalisadas;
	}

	public void setQuantidadeTurbidezAnalisadas(Integer quantidadeTurbidezAnalisadas) {
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
	}

	public Integer getQuantidadeTurbidezConforme() {
		return quantidadeTurbidezConforme;
	}

	public void setQuantidadeTurbidezConforme(Integer quantidadeTurbidezConforme) {
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
	}

	public Integer getQuantidadeTurbidezExigidas() {
		return quantidadeTurbidezExigidas;
	}

	public void setQuantidadeTurbidezExigidas(Integer quantidadeTurbidezExigidas) {
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
	}

	public FonteCaptacao getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(FonteCaptacao fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public BigDecimal getNumeroIndiceAlcalinidade() {
		return numeroIndiceAlcalinidade;
	}

	public void setNumeroIndiceAlcalinidade(BigDecimal numeroIndiceAlcalinidade) {
		this.numeroIndiceAlcalinidade = numeroIndiceAlcalinidade;
	}

	public Integer getQuantidadeAlcalinidadeAnalisadas() {
		return quantidadeAlcalinidadeAnalisadas;
	}

	public void setQuantidadeAlcalinidadeAnalisadas(
			Integer quantidadeAlcalinidadeAnalisadas) {
		this.quantidadeAlcalinidadeAnalisadas = quantidadeAlcalinidadeAnalisadas;
	}

	public Integer getQuantidadeAlcalinidadeConforme() {
		return quantidadeAlcalinidadeConforme;
	}

	public void setQuantidadeAlcalinidadeConforme(
			Integer quantidadeAlcalinidadeConforme) {
		this.quantidadeAlcalinidadeConforme = quantidadeAlcalinidadeConforme;
	}

	public Integer getQuantidadeAlcalinidadeExigidas() {
		return quantidadeAlcalinidadeExigidas;
	}

	public void setQuantidadeAlcalinidadeExigidas(
			Integer quantidadeAlcalinidadeExigidas) {
		this.quantidadeAlcalinidadeExigidas = quantidadeAlcalinidadeExigidas;
	}

	public SistemaAbastecimento getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(SistemaAbastecimento sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	public Integer getIndiceDurezaTotal() {
		return indiceDurezaTotal;
	}

	public void setIndiceDurezaTotal(Integer indiceDurezaTotal) {
		this.indiceDurezaTotal = indiceDurezaTotal;
	}

	public Integer getQuantidadeDurezaConforme() {
		return quantidadeDurezaConforme;
	}

	public void setQuantidadeDurezaConforme(Integer quantidadeDurezaConforme) {
		this.quantidadeDurezaConforme = quantidadeDurezaConforme;
	}

	public Integer getQuantidadeDurezaExigida() {
		return quantidadeDurezaExigida;
	}

	public void setQuantidadeDurezaExigida(Integer quantidadeDurezaExigida) {
		this.quantidadeDurezaExigida = quantidadeDurezaExigida;
	}

	public Integer getQuantidadeDurezaAnalisada() {
		return quantidadeDurezaAnalisada;
	}

	public void setQuantidadeDurezaAnalisada(Integer quantidadeDurezaAnalisada) {
		this.quantidadeDurezaAnalisada = quantidadeDurezaAnalisada;
	}

	public Integer getQuantidadePhConforme() {
		return quantidadePhConforme;
	}

	public void setQuantidadePhConforme(Integer quantidadePhConforme) {
		this.quantidadePhConforme = quantidadePhConforme;
	}

	public Integer getQuantidadePhExigida() {
		return quantidadePhExigida;
	}

	public void setQuantidadePhExigida(Integer quantidadePhExigida) {
		this.quantidadePhExigida = quantidadePhExigida;
	}

	public Integer getQuantidadePhAnalisada() {
		return quantidadePhAnalisada;
	}

	public void setQuantidadePhAnalisada(Integer quantidadePhAnalisada) {
		this.quantidadePhAnalisada = quantidadePhAnalisada;
	}
}