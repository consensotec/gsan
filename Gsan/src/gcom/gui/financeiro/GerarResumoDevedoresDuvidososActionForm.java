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
package gcom.gui.financeiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author pedro alexandre
 * @created 08/06/2007
 */
public class GerarResumoDevedoresDuvidososActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    
    private String anoMesReferenciaContabil;
    
    private String idTipoPerda;
    
    private String periodoBaixaInicial;
    
    private String periodoBaixaFinal;
    
    private String numeroMesesContasInferiores;
    
    private String indicadorCategoriaResidencial;
    
    private String indicadorCategoriaComercial;
    
    private String indicadorCategoriaIndustrial;
    
    private String indicadorCategoriaPublica;
    
    private String indicadorEsferaParticular;
    
    private String indicadorEsferaMunicipal;
    
    private String indicadorEsferaEstadual;
    
    private String indicadorEsferaFederal;
    
    private String indicadorTipoGeracao;
    
    private String numeroMesesContasVencidas;
    
    private String indicadorEsferaPoderMunicipal;
    
    private String indicadorEsferaPoderFederal;

    private String mesAnoAtual;
    
    
    

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	anoMesReferenciaContabil = null;
    }

	public String getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(String anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	/**
	 * @return the tipoPerda
	 */
	public String getIdTipoPerda() {
		return idTipoPerda;
	}

	/**
	 * @param tipoPerda the tipoPerda to set
	 */
	public void setIdTipoPerda(String idTipoPerda) {
		this.idTipoPerda = idTipoPerda;
	}

	/**
	 * @return the periodoBaixaInicial
	 */
	public String getPeriodoBaixaInicial() {
		return periodoBaixaInicial;
	}

	/**
	 * @param periodoBaixaInicial the periodoBaixaInicial to set
	 */
	public void setPeriodoBaixaInicial(String periodoBaixaInicial) {
		this.periodoBaixaInicial = periodoBaixaInicial;
	}

	/**
	 * @return the periodoBaixaFinal
	 */
	public String getPeriodoBaixaFinal() {
		return periodoBaixaFinal;
	}

	/**
	 * @param periodoBaixaFinal the periodoBaixaFinal to set
	 */
	public void setPeriodoBaixaFinal(String periodoBaixaFinal) {
		this.periodoBaixaFinal = periodoBaixaFinal;
	}

	/**
	 * @return the numeroMesesContasInferiores
	 */
	public String getNumeroMesesContasInferiores() {
		return numeroMesesContasInferiores;
	}

	/**
	 * @param numeroMesesContasInferiores the numeroMesesContasInferiores to set
	 */
	public void setNumeroMesesContasInferiores(String numeroMesesContasInferiores) {
		this.numeroMesesContasInferiores = numeroMesesContasInferiores;
	}

	/**
	 * @return the indicadorCategoriaResidencial
	 */
	public String getIndicadorCategoriaResidencial() {
		return indicadorCategoriaResidencial;
	}

	/**
	 * @param indicadorCategoriaResidencial the indicadorCategoriaResidencial to set
	 */
	public void setIndicadorCategoriaResidencial(String indicadorCategoriaResidencial) {
		this.indicadorCategoriaResidencial = indicadorCategoriaResidencial;
	}

	/**
	 * @return the indicadorCategoriaComercial
	 */
	public String getIndicadorCategoriaComercial() {
		return indicadorCategoriaComercial;
	}

	/**
	 * @param indicadorCategoriaComercial the indicadorCategoriaComercial to set
	 */
	public void setIndicadorCategoriaComercial(String indicadorCategoriaComercial) {
		this.indicadorCategoriaComercial = indicadorCategoriaComercial;
	}

	/**
	 * @return the indicadorCategoriaIndustrial
	 */
	public String getIndicadorCategoriaIndustrial() {
		return indicadorCategoriaIndustrial;
	}

	/**
	 * @param indicadorCategoriaIndustrial the indicadorCategoriaIndustrial to set
	 */
	public void setIndicadorCategoriaIndustrial(String indicadorCategoriaIndustrial) {
		this.indicadorCategoriaIndustrial = indicadorCategoriaIndustrial;
	}

	/**
	 * @return the indicadorCategoriaPublica
	 */
	public String getIndicadorCategoriaPublica() {
		return indicadorCategoriaPublica;
	}

	/**
	 * @param indicadorCategoriaPublica the indicadorCategoriaPublica to set
	 */
	public void setIndicadorCategoriaPublica(String indicadorCategoriaPublica) {
		this.indicadorCategoriaPublica = indicadorCategoriaPublica;
	}

	/**
	 * @return the indicadorEsferaParticular
	 */
	public String getIndicadorEsferaParticular() {
		return indicadorEsferaParticular;
	}

	/**
	 * @param indicadorEsferaParticular the indicadorEsferaParticular to set
	 */
	public void setIndicadorEsferaParticular(String indicadorEsferaParticular) {
		this.indicadorEsferaParticular = indicadorEsferaParticular;
	}

	/**
	 * @return the indicadorEsferaMunicipal
	 */
	public String getIndicadorEsferaMunicipal() {
		return indicadorEsferaMunicipal;
	}

	/**
	 * @param indicadorEsferaMunicipal the indicadorEsferaMunicipal to set
	 */
	public void setIndicadorEsferaMunicipal(String indicadorEsferaMunicipal) {
		this.indicadorEsferaMunicipal = indicadorEsferaMunicipal;
	}

	/**
	 * @return the indicadorEsferaEstadual
	 */
	public String getIndicadorEsferaEstadual() {
		return indicadorEsferaEstadual;
	}

	/**
	 * @param indicadorEsferaEstadual the indicadorEsferaEstadual to set
	 */
	public void setIndicadorEsferaEstadual(String indicadorEsferaEstadual) {
		this.indicadorEsferaEstadual = indicadorEsferaEstadual;
	}

	/**
	 * @return the indicadorEsferaFederal
	 */
	public String getIndicadorEsferaFederal() {
		return indicadorEsferaFederal;
	}

	/**
	 * @param indicadorEsferaFederal the indicadorEsferaFederal to set
	 */
	public void setIndicadorEsferaFederal(String indicadorEsferaFederal) {
		this.indicadorEsferaFederal = indicadorEsferaFederal;
	}

	/**
	 * @return the indicadorTipoGeracao
	 */
	public String getIndicadorTipoGeracao() {
		return indicadorTipoGeracao;
	}

	/**
	 * @param indicadorTipoGeracao the indicadorTipoGeracao to set
	 */
	public void setIndicadorTipoGeracao(String indicadorTipoGeracao) {
		this.indicadorTipoGeracao = indicadorTipoGeracao;
	}


	/**
	 * @return the numeroMesesContasVencidas
	 */
	public String getNumeroMesesContasVencidas() {
		return numeroMesesContasVencidas;
	}

	/**
	 * @param numeroMesesContasVencidas the numeroMesesContasVencidas to set
	 */
	public void setNumeroMesesContasVencidas(String numeroMesesContasVencidas) {
		this.numeroMesesContasVencidas = numeroMesesContasVencidas;
	}

	/**
	 * @return the indicadorEsferaPoderMunicipal
	 */
	public String getIndicadorEsferaPoderMunicipal() {
		return indicadorEsferaPoderMunicipal;
	}

	/**
	 * @param indicadorEsferaPoderMunicipal the indicadorEsferaPoderMunicipal to set
	 */
	public void setIndicadorEsferaPoderMunicipal(String indicadorEsferaPoderMunicipal) {
		this.indicadorEsferaPoderMunicipal = indicadorEsferaPoderMunicipal;
	}

	/**
	 * @return the indicadorEsferaPoderFederal
	 */
	public String getIndicadorEsferaPoderFederal() {
		return indicadorEsferaPoderFederal;
	}

	/**
	 * @param indicadorEsferaPoderFederal the indicadorEsferaPoderFederal to set
	 */
	public void setIndicadorEsferaPoderFederal(String indicadorEsferaPoderFederal) {
		this.indicadorEsferaPoderFederal = indicadorEsferaPoderFederal;
	}

	/**
	 * @return the mesAnoAtual
	 */
	public String getMesAnoAtual() {
		return mesAnoAtual;
	}

	/**
	 * @param mesAnoAtual the mesAnoAtual to set
	 */
	public void setMesAnoAtual(String mesAnoAtual) {
		this.mesAnoAtual = mesAnoAtual;
	}



}
