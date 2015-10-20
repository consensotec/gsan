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
package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0859]FILTRAR Situacao de Cobranca
 * 
 * @author Arthur Carvalho
 * @date 05/09/2008
 */

public class FiltrarCobrancaSituacaoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String contaMotivoRevisao;
	private String indicadorExigenciaAdvogado;
	private String indicadorBloqueioParcelamento;
	private String indicadorUso;
	private String indicadorAtualizar;
	private String tipoPesquisa;
	private String indicadorBloqueioRetirada;
	private String profissao;
	private String ramoAtividade;
	private String indicadorBloqueioInclusao;
	private String indicadorSelecaoApenasComPermissao;
	private String indicadorPrescricaoImoveisParticulares;
	private String indicadorAlterarVencimentoConta;
	
	public String getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}
	public void setContaMotivoRevisao(String contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}
	public String getIndicadorBloqueioParcelamento() {
		return indicadorBloqueioParcelamento;
	}
	public void setIndicadorBloqueioParcelamento(
			String indicadorBloqueioParcelamento) {
		this.indicadorBloqueioParcelamento = indicadorBloqueioParcelamento;
	}
	public String getIndicadorExigenciaAdvogado() {
		return indicadorExigenciaAdvogado;
	}
	public void setIndicadorExigenciaAdvogado(String indicadorExigenciaAdvogado) {
		this.indicadorExigenciaAdvogado = indicadorExigenciaAdvogado;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getIndicadorBloqueioRetirada() {
		return indicadorBloqueioRetirada;
	}
	public void setIndicadorBloqueioRetirada(String indicadorBloqueioRetirada) {
		this.indicadorBloqueioRetirada = indicadorBloqueioRetirada;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	public String getIndicadorBloqueioInclusao() {
		return indicadorBloqueioInclusao;
	}
	public void setIndicadorBloqueioInclusao(String indicadorBloqueioInclusao) {
		this.indicadorBloqueioInclusao = indicadorBloqueioInclusao;
	}
	public String getIndicadorSelecaoApenasComPermissao() {
		return indicadorSelecaoApenasComPermissao;
	}
	public void setIndicadorSelecaoApenasComPermissao(
			String indicadorSelecaoApenasComPermissao) {
		this.indicadorSelecaoApenasComPermissao = indicadorSelecaoApenasComPermissao;
	}
	public String getIndicadorPrescricaoImoveisParticulares() {
		return indicadorPrescricaoImoveisParticulares;
	}
	public void setIndicadorPrescricaoImoveisParticulares(
			String indicadorPrescricaoImoveisParticulares) {
		this.indicadorPrescricaoImoveisParticulares = indicadorPrescricaoImoveisParticulares;
	}
	public String getIndicadorAlterarVencimentoConta() {
		return indicadorAlterarVencimentoConta;
	}
	public void setIndicadorAlterarVencimentoConta(String indicadorAlterarVencimentoConta) {
		this.indicadorAlterarVencimentoConta = indicadorAlterarVencimentoConta;
	}
}	