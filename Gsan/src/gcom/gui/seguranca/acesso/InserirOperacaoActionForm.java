/**
 * 
 */
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
package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form respons�vel por manipular os dados no inserir opera��o
 * 
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class InserirOperacaoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String descricaoAbreviada;

	private String caminhoUrl;

	private String idFuncionalidade;
	
	private String descricaoFuncionalidade;
	
	private String idTipoOperacao;
	
	private String idArgumentoPesquisa;
	
	private String descricaoArgumentoPesquisa;
	
	private String[] idTabelas;
	
	private String idOperacaoPesquisa;
	
	private String descricaoOperacaoPesquisa;

	public String getCaminhoUrl() {
		return caminhoUrl;
	}

	public void setCaminhoUrl(String caminhoUrl) {
		this.caminhoUrl = caminhoUrl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getIdArgumentoPesquisa() {
		return idArgumentoPesquisa;
	}

	public void setIdArgumentoPesquisa(String idArgumentoPesquisa) {
		this.idArgumentoPesquisa = idArgumentoPesquisa;
	}

	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getIdOperacaoPesquisa() {
		return idOperacaoPesquisa;
	}

	public void setIdOperacaoPesquisa(String idOperacaoPesquisa) {
		this.idOperacaoPesquisa = idOperacaoPesquisa;
	}

	public String[] getIdTabelas() {
		return idTabelas;
	}

	public void setIdTabelas(String[] idTabelas) {
		this.idTabelas = idTabelas;
	}

	public String getIdTipoOperacao() {
		return idTipoOperacao;
	}

	public void setIdTipoOperacao(String idTipoOperacao) {
		this.idTipoOperacao = idTipoOperacao;
	}

	public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}

	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}

	public String getDescricaoArgumentoPesquisa() {
		return descricaoArgumentoPesquisa;
	}

	public void setDescricaoArgumentoPesquisa(String descricaoArgumentoPesquisa) {
		this.descricaoArgumentoPesquisa = descricaoArgumentoPesquisa;
	}

	public String getDescricaoOperacaoPesquisa() {
		return descricaoOperacaoPesquisa;
	}

	public void setDescricaoOperacaoPesquisa(String descricaoOperacaoPesquisa) {
		this.descricaoOperacaoPesquisa = descricaoOperacaoPesquisa;
	}

	
}
