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
package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterUnidadeOrganizacionalBean implements RelatorioBean {
	
	private String codigo;

	private String descricao;
	
	private String nivel;
	
	private String unidadeTipo;
	
	private String indicadorAberturaRa;
	
	private String indicadorTramite;
	
	private String indicadorUso;
	
	private String empresa;
	
	private String indicadorEsgoto;
	
	private String sigla;
	
	private String gerenciaRegional;
	
	private String localidade;
	
	private String unidadeSuperior;
	
	private String unidadeCentralizadora;
	
	private String unidadeRepavimentadora;
	
	private String meioSolicitacao;

	/**
	 * Construtor da classe RelatorioManterUnidadeOrganizacionalBean
	 * 
	 * @param id
	 *            Descri��o do par�metro
	 * @param descricao
	 *            Descri��o do par�metro
	 * @param sistemaEsgoto
	 *            Descri��o do par�metro
	 */

	public RelatorioManterUnidadeOrganizacionalBean(String codigo,
			String descricao,  String nivel, String unidadeTipo, String indicadorAberturaRa, 
			String indicadorTramite, String indicadorUso, String empresa, String indicadorEsgoto,
			String sigla, String gerenciaRegional, String localidade, String unidadeSuperior,
			String unidadeCentralizadora, String unidadeRepavimentadora, String meioSolicitacao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.nivel= nivel;
		this.unidadeTipo= unidadeTipo;
		this.indicadorAberturaRa = indicadorAberturaRa;
		this.indicadorTramite = indicadorTramite;
		this.indicadorUso = indicadorUso;
		this.empresa = empresa;
		this.indicadorEsgoto = indicadorEsgoto;
		this.sigla = sigla;
		this.gerenciaRegional = gerenciaRegional;
		this.localidade = localidade;
		this.unidadeCentralizadora = unidadeCentralizadora;
		this.unidadeRepavimentadora = unidadeRepavimentadora;
		this.unidadeSuperior = unidadeSuperior;
		this.meioSolicitacao = meioSolicitacao;
	}

	/**
	 * Retorna o valor do id
	 * 
	 * @return O valor do id
	 */

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna o valor da descricao
	 * 
	 * @return O valor da descricao
	 */

	public String getDescricao() {
		return descricao;
	}

	/**
	 * Seta o valor do id
	 * 
	 * @param id
	 *            O novo valor do id
	 */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIndicadorAberturaRa() {
		return indicadorAberturaRa;
	}

	public void setIndicadorAberturaRa(String indicadorAberturaRa) {
		this.indicadorAberturaRa = indicadorAberturaRa;
	}

	public String getIndicadorTramite() {
		return indicadorTramite;
	}

	public void setIndicadorTramite(String indicadorTramite) {
		this.indicadorTramite = indicadorTramite;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getUnidadeTipo() {
		return unidadeTipo;
	}

	public void setUnidadeTipo(String unidadeTipo) {
		this.unidadeTipo = unidadeTipo;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getIndicadorEsgoto() {
		return indicadorEsgoto;
	}

	public void setIndicadorEsgoto(String indicadorEsgoto) {
		this.indicadorEsgoto = indicadorEsgoto;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public String getUnidadeCentralizadora() {
		return unidadeCentralizadora;
	}

	public void setUnidadeCentralizadora(String unidadeCentralizadora) {
		this.unidadeCentralizadora = unidadeCentralizadora;
	}

	public String getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(String unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public String getUnidadeSuperior() {
		return unidadeSuperior;
	}

	public void setUnidadeSuperior(String unidadeSuperior) {
		this.unidadeSuperior = unidadeSuperior;
	}
	
}