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
package gcom.relatorio.financeiro;

import java.math.BigDecimal;
/**
 * UC0718 Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil
 * @author Francisco do Nascimento
 *
 */
public class RelatorioEvolucaoContasAReceberContabilHelper {

	private String tipoGrupo;
	
	private String descricaoGrupo;
	
	private int ordemGrupo;
	
	private String descricaoElementoGrupo;

	private int sequenciaTipoLancamento;
	
	private String descricaoLancamento;
	
	private String idCategoriaTipo;	
	
	private BigDecimal valorItem;

	public RelatorioEvolucaoContasAReceberContabilHelper(String tipoGrupo, String descricaoGrupo, int ordemGrupo, String descricaoElementoGrupo, int sequenciaTipoLancamento, String descricaoLancamento, String idCategoriaTipo, BigDecimal valorItem) {
		super();
		this.tipoGrupo = tipoGrupo;
		this.descricaoGrupo = descricaoGrupo;
		this.ordemGrupo = ordemGrupo;
		this.descricaoElementoGrupo = descricaoElementoGrupo;
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
		this.descricaoLancamento = descricaoLancamento;
		this.idCategoriaTipo = idCategoriaTipo;
		this.valorItem = valorItem;
	}

	public String getDescricaoElementoGrupo() {
		return descricaoElementoGrupo;
	}

	public void setDescricaoElementoGrupo(String descricaoElementoGrupo) {
		this.descricaoElementoGrupo = descricaoElementoGrupo;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public int getOrdemGrupo() {
		return ordemGrupo;
	}

	public void setOrdemGrupo(int ordemGrupo) {
		this.ordemGrupo = ordemGrupo;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public String getDescricaoLancamento() {
		return descricaoLancamento;
	}

	public void setDescricaoLancamento(String descricaoLancamento) {
		this.descricaoLancamento = descricaoLancamento;
	}

	public String getIdCategoriaTipo() {
		return idCategoriaTipo;
	}

	public void setIdCategoriaTipo(String idCategoriaTipo) {
		this.idCategoriaTipo = idCategoriaTipo;
	}

	public int getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	public void setSequenciaTipoLancamento(int sequenciaTipoLancamento) {
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
	}
	


}
