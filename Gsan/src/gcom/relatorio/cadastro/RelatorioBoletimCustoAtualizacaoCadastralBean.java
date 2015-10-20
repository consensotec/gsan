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

import java.math.BigDecimal;

/**
 * [UC0912] Gerar Boletim de Custo Atualiza��o Cadastral
 * @author Vivianne Sousa
 * @date 30/06/2009
 */
public class RelatorioBoletimCustoAtualizacaoCadastralBean implements RelatorioBean {
	
	private String itemAtributoGrupo;
	private String descricaoAtributoGrupo;
	
	private String itemAtributo;
	private String descricaoAtributo;
	private BigDecimal vlAtualizacao;
	
	private Integer qtdeAtualizacoes;
	
	public RelatorioBoletimCustoAtualizacaoCadastralBean(
			String itemAtributoGrupo,
			String descricaoAtributoGrupo,
			String itemAtributo,
			String descricaoAtributo,
			BigDecimal vlAtualizacao,
			Integer qtdeAtualizacoes) {
	    	
			this.itemAtributoGrupo = itemAtributoGrupo;
			this.descricaoAtributoGrupo = descricaoAtributoGrupo;
			this.itemAtributo = itemAtributo;
			this.descricaoAtributo = descricaoAtributo;
			this.vlAtualizacao = vlAtualizacao;
			this.qtdeAtualizacoes = qtdeAtualizacoes;
	    	
	}

	public String getDescricaoAtributo() {
		return descricaoAtributo;
	}

	public void setDescricaoAtributo(String descricaoAtributo) {
		this.descricaoAtributo = descricaoAtributo;
	}

	public String getDescricaoAtributoGrupo() {
		return descricaoAtributoGrupo;
	}

	public void setDescricaoAtributoGrupo(String descricaoAtributoGrupo) {
		this.descricaoAtributoGrupo = descricaoAtributoGrupo;
	}

	public String getItemAtributo() {
		return itemAtributo;
	}

	public void setItemAtributo(String itemAtributo) {
		this.itemAtributo = itemAtributo;
	}

	public String getItemAtributoGrupo() {
		return itemAtributoGrupo;
	}

	public void setItemAtributoGrupo(String itemAtributoGrupo) {
		this.itemAtributoGrupo = itemAtributoGrupo;
	}

	public BigDecimal getVlAtualizacao() {
		return vlAtualizacao;
	}

	public void setVlAtualizacao(BigDecimal vlAtualizacao) {
		this.vlAtualizacao = vlAtualizacao;
	}

	public Integer getQtdeAtualizacoes() {
		return qtdeAtualizacoes;
	}

	public void setQtdeAtualizacoes(Integer qtdeAtualizacoes) {
		this.qtdeAtualizacoes = qtdeAtualizacoes;
	}
	
}
