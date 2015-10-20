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
package gcom.gui.relatorio.arrecadacao;

import org.apache.struts.action.ActionForm;
/**
 * action respons�vel pela exibi��o do Relat�rio de Pagamento para Entidades Beneficentes
 * [UC0959]
 * @author Daniel Alves
 * @created 13/01/2010
 */
public class GerarRelatorioPagamentoEntidadesBeneficentesActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	
	private String tipoRelatorio;
	
	private String idEntidadeBeneficente;
	
	private String mesAnoInicial;
	
	private String mesAnoFinal;

	private String indicadorEstado;

	private String indicadorGerenciaRegional;	               
		
	private String indicadorUnidadeNegocio;
	
	private String indicadorLocalidade;
	
	private String idGerenciaRegional;
	
	private String idUnidadeNegocio;
	
	private String idLocalidade;
	
	private int opcaoTotalizacao;
	
	
	/*public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		mesAnoInicial = null;
		mesAnoFinal = null;		
		tipo = null;
	}*/

	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getIdEntidadeBeneficente() {
		return idEntidadeBeneficente;
	}


	public void setIdEntidadeBeneficente(String idEntidadeBeneficente) {
		this.idEntidadeBeneficente = idEntidadeBeneficente;
	}


	public String getMesAnoInicial() {
		return mesAnoInicial;
	}


	public void setMesAnoInicial(String mesAnoInicial) {
		this.mesAnoInicial = mesAnoInicial;
	}


	public String getMesAnoFinal() {
		return mesAnoFinal;
	}


	public void setMesAnoFinal(String mesAnoFinal) {
		this.mesAnoFinal = mesAnoFinal;
	}


	public String getIndicadorEstado() {
		return indicadorEstado;
	}


	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	
	public String getIndicadorUnidadeNegocio() {
		return indicadorUnidadeNegocio;
	}


	public void setIndicadorUnidadeNegocio(String indicadorUnidadeNegocio) {
		this.indicadorUnidadeNegocio = indicadorUnidadeNegocio;
	}


	public String getIndicadorGerenciaRegional() {
		return indicadorGerenciaRegional;
	}


	public void setIndicadorGerenciaRegional(String indicadorGerenciaRegional) {
		this.indicadorGerenciaRegional = indicadorGerenciaRegional;
	}
	
	
	public String getIndicadorLocalidade() {
		return indicadorLocalidade;
	}


	public void setIndicadorLocalidade(String indicadorLocalidade) {
		this.indicadorLocalidade = indicadorLocalidade;
	}


	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}


	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}


	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}


	public String getIdLocalidade() {
		return idLocalidade;
	}


	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}


	public int getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}


	public void setOpcaoTotalizacao(int opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}


	public String getTipoRelatorio() {
		return tipoRelatorio;
	}


	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	
}