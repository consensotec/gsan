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
package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.gui.ActionServletException;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * <<Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class FiltrarTabelaAuxiliarAbreviadaTipoAction extends Action {
	/**
	 * <<Descri��o do M�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroTabelaAuxiliarAbreviadaTipo");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		//Recupera os par�metros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String descricaoAbreviada = (String) pesquisarActionForm
				.get("descricaoAbreviada");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
		String atualizar = (String) pesquisarActionForm.get("atualizar");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		Integer tipo = (Integer) pesquisarActionForm.get("tipo");
		
		//cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		
		boolean peloMenosUmParametroInformado = false;
		
		//Adiciona a Descri��o da Tabela Auxiliar Abreviada Tipo ao filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {			
			peloMenosUmParametroInformado = true;			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ComparacaoTextoCompleto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, 
						descricao));
			} else {
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ComparacaoTexto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, descricao));
			}
		}
		
		//Insere os par�metros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;	
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviadaTipo.ID, id));
		}

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;	
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ComparacaoTexto(
							FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}
		
		// Tipo
		if (tipo != null && tipo != -1) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviadaTipo.SISTEMAABASTECIMENTO,tipo));
			
		}
		
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviadaTipo.INDICADORUSO, indicadorUso));
		}
		
		//Verifica se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
	
		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}

		//Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviadaTipo
		
		httpServletRequest.setAttribute("filtroTabelaAuxiliarAbreviadaTipo",
				filtroTabelaAuxiliarAbreviadaTipo);

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
