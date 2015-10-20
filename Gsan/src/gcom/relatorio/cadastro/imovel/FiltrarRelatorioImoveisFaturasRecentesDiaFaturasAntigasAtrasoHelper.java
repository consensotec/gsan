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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC00730] Gerar Relat�rio de Im�veis com Faturas Recentes em Dia e Faturas Antigas em Atraso
 * 
 * @author Rafael Pinto
 * @date 08/01/2008
 */
public class FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer unidadeNegocio;
	private Integer gerenciaRegional;
	
	private Integer localidadeInicial;
	private Integer setorComercialInicial;
	private Integer rotaInicial;
	private Integer sequencialRotalInicial;

	private Integer localidadeFinal;
	private Integer setorComercialFinal;
	private Integer rotaFinal;
	private Integer sequencialRotalFinal;
	
	private Collection<Integer> situacaoLigacaoAgua;
	private Collection<Integer> categorias;

	private Integer referenciaFaturasDiaInicial;
	private Integer referenciaFaturasDiaFinal;

	private Integer referenciaFaturasAtrasoInicial;
	private Integer referenciaFaturasAtrasoFinal;


	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Integer rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Integer getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Integer rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSequencialRotalFinal() {
		return sequencialRotalFinal;
	}

	public void setSequencialRotalFinal(Integer sequencialRotalFinal) {
		this.sequencialRotalFinal = sequencialRotalFinal;
	}

	public Integer getSequencialRotalInicial() {
		return sequencialRotalInicial;
	}

	public void setSequencialRotalInicial(Integer sequencialRotalInicial) {
		this.sequencialRotalInicial = sequencialRotalInicial;
	}

	public Collection<Integer> getCategorias() {
		return categorias;
	}

	public void setCategorias(Collection<Integer> categorias) {
		this.categorias = categorias;
	}

	public Integer getReferenciaFaturasAtrasoFinal() {
		return referenciaFaturasAtrasoFinal;
	}

	public void setReferenciaFaturasAtrasoFinal(Integer referenciaFaturasAtrasoFinal) {
		this.referenciaFaturasAtrasoFinal = referenciaFaturasAtrasoFinal;
	}

	public Integer getReferenciaFaturasAtrasoInicial() {
		return referenciaFaturasAtrasoInicial;
	}

	public void setReferenciaFaturasAtrasoInicial(
			Integer referenciaFaturasAtrasoInicial) {
		this.referenciaFaturasAtrasoInicial = referenciaFaturasAtrasoInicial;
	}

	public Integer getReferenciaFaturasDiaFinal() {
		return referenciaFaturasDiaFinal;
	}

	public void setReferenciaFaturasDiaFinal(Integer referenciaFaturasDiaFinal) {
		this.referenciaFaturasDiaFinal = referenciaFaturasDiaFinal;
	}

	public Integer getReferenciaFaturasDiaInicial() {
		return referenciaFaturasDiaInicial;
	}

	public void setReferenciaFaturasDiaInicial(Integer referenciaFaturasDiaInicial) {
		this.referenciaFaturasDiaInicial = referenciaFaturasDiaInicial;
	}

	public Collection<Integer> getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Collection<Integer> situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	
	
	
}