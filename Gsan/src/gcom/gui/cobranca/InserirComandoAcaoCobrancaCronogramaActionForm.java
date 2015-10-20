/*
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
package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de A��o de Cobran�a - Tipo de Comando Cronograma 
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class InserirComandoAcaoCobrancaCronogramaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String referenciaCobranca;
	private String cobrancaGrupo;
	private String cobrancaAcao;
	private String cobrancaAtividade;
	private String rotaInicial;

	private String rotaFinal;
	
	
	
	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	/**
	 * @return Returns the acaoCobranca.
	 */
	public String getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param acaoCobranca The acaoCobranca to set.
	 */
	public void setCobrancaAcao(String acaoCobranca) {
		this.cobrancaAcao = acaoCobranca;
	}

	/**
	 * @return Returns the atividadeCobranca.
	 */
	public String getCobrancaAtividade() {
		return cobrancaAtividade;
	}

	/**
	 * @param atividadeCobranca The atividadeCobranca to set.
	 */
	public void setCobrancaAtividade(String atividadeCobranca) {
		this.cobrancaAtividade = atividadeCobranca;
	}

	/**
	 * @return Returns the grupoCobranca.
	 */
	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param grupoCobranca The grupoCobranca to set.
	 */
	public void setCobrancaGrupo(String grupoCobranca) {
		this.cobrancaGrupo = grupoCobranca;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		referenciaCobranca = null;
		cobrancaGrupo = null;
		cobrancaAcao = null;
		cobrancaAtividade = null;
		
	}

	/**
	 * @return Returns the referenciaCobranca.
	 */
	public String getReferenciaCobranca() {
		return referenciaCobranca;
	}

	/**
	 * @param referenciaCobranca The referenciaCobranca to set.
	 */
	public void setReferenciaCobranca(String referenciaCobranca) {
		this.referenciaCobranca = referenciaCobranca;
	}

}
