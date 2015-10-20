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
package gcom.relatorio.cadastro.localidade;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corr�a
 * @created 7 de Novembro de 2005
 * @version 1.0
 */

public class RelatorioManterQuadraBean implements RelatorioBean {
	private String quadra;

	private String localidade;

	private String setorComercial;

	private String codigoSetorComercial;

	private String perfilQuadra;

	private String areaTipo;

	private String redeAgua;

	private String redeEsgoto;

	private String sistemaEsgoto;

	private String bacia;

	private String distritoOperacional;

	private String setorCensitario;

	private String zeis;

	private String rota;

	private String indicadorUso;
	
	private String indicadorQuadraFace;
	
	private String numeroQuadraFace;
	
	private String baciaQuadraFace;
	
	private String indicadorRedeEsgotoQuadraFace;
	
	private String idQuadra;
	
	private String indicadorRedeAguaQuadraFace;
	
	private String distritoOperacionalQuadraFace;
	
	private String descricaoSistemaEsgotoQuadraFace;
	/**
	 * Construtor da classe RelatorioManterQuadraBean
	 * 
	 * @param quadra
	 *            Descri��o do par�metro
	 * @param localidade
	 *            Descri��o do par�metro
	 * @param codigoSetorComercial
	 *            Descri��o do par�metro
	 * @param setorComercial
	 *            Descri��o do par�metro
	 * @param perfilQuadra
	 *            Descri��o do par�metro
	 * @param redeAgua
	 *            Descri��o do par�metro
	 * @param redeEsgoto
	 *            Descri��o do par�metro
	 * @param bairro
	 *            Descri��o do par�metro
	 * @param municipio
	 *            Descri��o do par�metro
	 * @param sistemaEsgoto
	 *            Descri��o do par�metro
	 * @param bacia
	 *            Descri��o do par�metro
	 * @param distritoOperacional
	 *            Descri��o do par�metro
	 * @param setorCensitario
	 *            Descri��o do par�metro
	 * @param zeis
	 *            Descri��o do par�metro
	 * @param rota
	 *            Descri��o do par�metro
	 * @param indicadorUso
	 *            Descri��o do par�metro
	 */
	public RelatorioManterQuadraBean(String quadra, String localidade,
			String codigoSetorComercial, String setorComercial,
			String perfilQuadra, String areaTipo, String redeAgua,
			String redeEsgoto, String sistemaEsgoto, String bacia,
			String distritoOperacional, String setorCensitario, String zeis,
			String rota, String indicadorUso, String indicadorQuadraFace, String numeroQuadraFace, 
			String baciaQuadraFace, String indicadorRedeEsgotoQuadraFace,String idQuadra, 
			String indicadorRedeAguaQuadraFace, String distritoOperacionalQuadraFace,
			String descricaoSistemaEsgotoQuadraFace
			) {
		this.quadra = quadra;
		this.localidade = localidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.setorComercial = setorComercial;
		this.perfilQuadra = perfilQuadra;
		this.areaTipo = areaTipo;
		this.redeAgua = redeAgua;
		this.redeEsgoto = redeEsgoto;
		this.sistemaEsgoto = sistemaEsgoto;
		this.bacia = bacia;
		this.distritoOperacional = distritoOperacional;
		this.setorCensitario = setorCensitario;
		this.zeis = zeis;
		this.rota = rota;
		this.indicadorUso = indicadorUso;
		this.indicadorQuadraFace = indicadorQuadraFace;
		this.numeroQuadraFace = numeroQuadraFace;
		this.baciaQuadraFace = baciaQuadraFace;
		this.indicadorRedeEsgotoQuadraFace = indicadorRedeEsgotoQuadraFace;
		this.idQuadra = idQuadra;
		this.indicadorRedeAguaQuadraFace = indicadorRedeAguaQuadraFace;
		this.distritoOperacionalQuadraFace = distritoOperacionalQuadraFace;
		this.descricaoSistemaEsgotoQuadraFace = descricaoSistemaEsgotoQuadraFace;
	}

	/**
	 * Retorna o valor de bacia
	 * 
	 * @return O valor de bacia
	 */
	public String getBacia() {
		return bacia;
	}

	/**
	 * Seta o valor de bacia
	 * 
	 * @param bacia
	 *            O novo valor de bacia
	 */
	public void setBacia(String bacia) {
		this.bacia = bacia;
	}

	/**
	 * Retorna o valor de distritoOperacional
	 * 
	 * @return O valor de distritoOperacional
	 */
	public String getDistritoOperacional() {
		return distritoOperacional;
	}

	/**
	 * Seta o valor de distritoOperacional
	 * 
	 * @param distritoOperacional
	 *            O novo valor de distritoOperacional
	 */
	public void setDistritoOperacional(String distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * Retorna o valor de localidade
	 * 
	 * @return O valor de localidade
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * Retorna o valor de perfilQuadra
	 * 
	 * @return O valor de perfilQuadra
	 */
	public String getPerfilQuadra() {
		return perfilQuadra;
	}

	/**
	 * Retorna o valor de quadra
	 * 
	 * @return O valor de quadra
	 */
	public String getQuadra() {
		return quadra;
	}

	/**
	 * Retorna o valor de redeAgua
	 * 
	 * @return O valor de redeAgua
	 */
	public String getRedeAgua() {
		return redeAgua;
	}

	/**
	 * Retorna o valor de redeEsgoto
	 * 
	 * @return O valor de redeEsgoto
	 */
	public String getRedeEsgoto() {
		return redeEsgoto;
	}

	/**
	 * Retorna o valor de rota
	 * 
	 * @return O valor de rota
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * Retorna o valor de setorCensitario
	 * 
	 * @return O valor de setorCensitario
	 */
	public String getSetorCensitario() {
		return setorCensitario;
	}

	/**
	 * Retorna o valor de setorComercial
	 * 
	 * @return O valor de setorComercial
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * Retorna o valor de sistemaEsgoto
	 * 
	 * @return O valor de sistemaEsgoto
	 */
	public String getSistemaEsgoto() {
		return sistemaEsgoto;
	}

	/**
	 * Retorna o valor de zeis
	 * 
	 * @return O valor de zeis
	 */
	public String getZeis() {
		return zeis;
	}

	/**
	 * Seta o valor de zeis
	 * 
	 * @param zeis
	 *            O novo valor de zeis
	 */
	public void setZeis(String zeis) {
		this.zeis = zeis;
	}

	/**
	 * Seta o valor de sistemaEsgoto
	 * 
	 * @param sistemaEsgoto
	 *            O novo valor de sistemaEsgoto
	 */
	public void setSistemaEsgoto(String sistemaEsgoto) {
		this.sistemaEsgoto = sistemaEsgoto;
	}

	/**
	 * Seta o valor de setorComercial
	 * 
	 * @param setorComercial
	 *            O novo valor de setorComercial
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * Seta o valor de setorCensitario
	 * 
	 * @param setorCensitario
	 *            O novo valor de setorCensitario
	 */
	public void setSetorCensitario(String setorCensitario) {
		this.setorCensitario = setorCensitario;
	}

	/**
	 * Seta o valor de rota
	 * 
	 * @param rota
	 *            O novo valor de rota
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * Seta o valor de redeEsgoto
	 * 
	 * @param redeEsgoto
	 *            O novo valor de redeEsgoto
	 */
	public void setRedeEsgoto(String redeEsgoto) {
		this.redeEsgoto = redeEsgoto;
	}

	/**
	 * Seta o valor de redeAgua
	 * 
	 * @param redeAgua
	 *            O novo valor de redeAgua
	 */
	public void setRedeAgua(String redeAgua) {
		this.redeAgua = redeAgua;
	}

	/**
	 * Seta o valor de quadra
	 * 
	 * @param quadra
	 *            O novo valor de quadra
	 */
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	/**
	 * Seta o valor de perfilQuadra
	 * 
	 * @param perfilQuadra
	 *            O novo valor de perfilQuadra
	 */
	public void setPerfilQuadra(String perfilQuadra) {
		this.perfilQuadra = perfilQuadra;
	}

	/**
	 * Seta o valor de localidade
	 * 
	 * @param localidade
	 *            O novo valor de localidade
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * Seta o valor de indicadorUso
	 * 
	 * @param indicadorUso
	 *            O novo valor de indicadorUso
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Retorna o valor de codigoSetorComercial
	 * 
	 * @return O valor de codigoSetorComercial
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * Seta o valor de codigoSetorComercial
	 * 
	 * @param codigoSetorComercial
	 *            O novo valor de codigoSetorComercial
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo areaTipo.
	 */
	public String getAreaTipo() {
		return areaTipo;
	}

	/**
	 * @param areaTipo O areaTipo a ser setado.
	 */
	public void setAreaTipo(String areaTipo) {
		this.areaTipo = areaTipo;
	}

	public String getNumeroQuadraFace() {
		return numeroQuadraFace;
	}

	public void setNumeroQuadraFace(String numeroQuadraFace) {
		this.numeroQuadraFace = numeroQuadraFace;
	}

	public String getIndicadorQuadraFace() {
		return indicadorQuadraFace;
	}

	public void setIndicadorQuadraFace(String indicadorQuadraFace) {
		this.indicadorQuadraFace = indicadorQuadraFace;
	}

	public String getBaciaQuadraFace() {
		return baciaQuadraFace;
	}

	public void setBaciaQuadraFace(String baciaQuadraFace) {
		this.baciaQuadraFace = baciaQuadraFace;
	}

	public String getIndicadorRedeEsgotoQuadraFace() {
		return indicadorRedeEsgotoQuadraFace;
	}

	public void setIndicadorRedeEsgotoQuadraFace(String sistemaEsgotoQuadraFace) {
		this.indicadorRedeEsgotoQuadraFace = sistemaEsgotoQuadraFace;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getDescricaoSistemaEsgotoQuadraFace() {
		return descricaoSistemaEsgotoQuadraFace;
	}

	public void setDescricaoSistemaEsgotoQuadraFace(
			String descricaoSistemaEsgotoQuadraFace) {
		this.descricaoSistemaEsgotoQuadraFace = descricaoSistemaEsgotoQuadraFace;
	}

	public String getDistritoOperacionalQuadraFace() {
		return distritoOperacionalQuadraFace;
	}

	public void setDistritoOperacionalQuadraFace(
			String distritoOperacionalQuadraFace) {
		this.distritoOperacionalQuadraFace = distritoOperacionalQuadraFace;
	}

	public String getIndicadorRedeAguaQuadraFace() {
		return indicadorRedeAguaQuadraFace;
	}

	public void setIndicadorRedeAguaQuadraFace(String indicadorRedeAguaQuadraFace) {
		this.indicadorRedeAguaQuadraFace = indicadorRedeAguaQuadraFace;
	}

}
