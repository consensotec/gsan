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
package gcom.gui.cadastro.imovel;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class CategoriaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idCategoria;

    private String descricao;

    private String descricaoAbreviada;

    private String tipoCategoria;

    private String consumoMinimo;

    private String consumoEstouro;

    private String vezesMediaEstouro;
    
    private String mediaBaixoConsumo;
    
    private String porcentagemMediaBaixoConsumo;
    
    private String consumoAlto;

    private String vezesMediaAltoConsumo;

    private String indicadorUso;
    
    private String limiteConsumo;
    
    private String fatorLimiteConsumo;
    
    private String verificaIndicadorTarifaCategoria;
    
	public String getLimiteConsumo() {
		return limiteConsumo;
	}



	public void setLimiteConsumo(String limiteConsumo) {
		this.limiteConsumo = limiteConsumo;
	}



	public String getFatorLimiteConsumo() {
		return fatorLimiteConsumo;
	}



	public void setFatorLimiteConsumo(String fatorLimiteConsumo) {
		this.fatorLimiteConsumo = fatorLimiteConsumo;
	}



	/**
	 * @return Returns the consumoAlto.
	 */
	public String getConsumoAlto() {
		return consumoAlto;
	}



	/**
	 * @param consumoAlto The consumoAlto to set.
	 */
	public void setConsumoAlto(String consumoAlto) {
		this.consumoAlto = consumoAlto;
	}



	/**
	 * @return Returns the consumoEstouro.
	 */
	public String getConsumoEstouro() {
		return consumoEstouro;
	}



	/**
	 * @param consumoEstouro The consumoEstouro to set.
	 */
	public void setConsumoEstouro(String consumoEstouro) {
		this.consumoEstouro = consumoEstouro;
	}



	/**
	 * @return Returns the consumoMinimo.
	 */
	public String getConsumoMinimo() {
		return consumoMinimo;
	}



	/**
	 * @param consumoMinimo The consumoMinimo to set.
	 */
	public void setConsumoMinimo(String consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}



	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}



	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	/**
	 * @return Returns the descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	/**
	 * @param descricaoAbreviada The descricaoAbreviada to set.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}



	/**
	 * @return Returns the idCategoria.
	 */
	public String getIdCategoria() {
		return idCategoria;
	}



	/**
	 * @param idCategoria The idCategoria to set.
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}



	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}



	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}



	/**
	 * @return Returns the mediaBaixoConsumo.
	 */
	public String getMediaBaixoConsumo() {
		return mediaBaixoConsumo;
	}



	/**
	 * @param mediaBaixoConsumo The mediaBaixoConsumo to set.
	 */
	public void setMediaBaixoConsumo(String mediaBaixoConsumo) {
		this.mediaBaixoConsumo = mediaBaixoConsumo;
	}



	/**
	 * @return Returns the porcentagemMediaBaixoConsumo.
	 */
	public String getPorcentagemMediaBaixoConsumo() {
		return porcentagemMediaBaixoConsumo;
	}



	/**
	 * @param porcentagemMediaBaixoConsumo The porcentagemMediaBaixoConsumo to set.
	 */
	public void setPorcentagemMediaBaixoConsumo(String porcentagemMediaBaixoConsumo) {
		this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
	}



	/**
	 * @return Returns the vezesMediaAltoConsumo.
	 */
	public String getVezesMediaAltoConsumo() {
		return vezesMediaAltoConsumo;
	}



	/**
	 * @param vezesMediaAltoConsumo The vezesMediaAltoConsumo to set.
	 */
	public void setVezesMediaAltoConsumo(String vezesMediaAltoConsumo) {
		this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
	}



	/**
	 * @return Returns the vezesMediaEstouro.
	 */
	public String getVezesMediaEstouro() {
		return vezesMediaEstouro;
	}

	/**
	 * @param vezesMediaEstouro The vezesMediaEstouro to set.
	 */
	public void setVezesMediaEstouro(String vezesMediaEstouro) {
		this.vezesMediaEstouro = vezesMediaEstouro;
	}
	
	/**
	 * @return Retorna o campo tipoCategoria.
	 */

	public String getTipoCategoria() {
		return tipoCategoria;
	}


	/**
	 * @param tipoCategoria O tipoCategoria a ser setado.
	 */
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	
	

	public String getVerificaIndicadorTarifaCategoria() {
		return verificaIndicadorTarifaCategoria;
	}



	public void setVerificaIndicadorTarifaCategoria(
			String verificaIndicadorTarifaCategoria) {
		this.verificaIndicadorTarifaCategoria = verificaIndicadorTarifaCategoria;
	}



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
    	idCategoria = null;
        descricao = null;
        descricaoAbreviada = null;
        consumoMinimo = null;
        consumoEstouro = null;
        vezesMediaEstouro = null;
        mediaBaixoConsumo = null;
        porcentagemMediaBaixoConsumo = null;
        consumoAlto = null;
        vezesMediaAltoConsumo = null;
        limiteConsumo = null;
        fatorLimiteConsumo = null;
		indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
		tipoCategoria = null;
		verificaIndicadorTarifaCategoria = null;
		
    }



	



	
}