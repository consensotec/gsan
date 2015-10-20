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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 05/08/2006
 */
public class FiltrarGerenciaRegionalAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servic�o
	 * 
	 * [UC0437] Pesquisar Tipo de Servi�o de Refer�ncia
	 * 
	 * 
	 * @author Thiago Ten�rio, Ivan S�rgio
	 * @date 31/07/2006, 11/06/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterGerenciaRegional");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarGerenciaRegionalActionForm filtrarGerenciaRegionalActionForm = (FiltrarGerenciaRegionalActionForm) actionForm;

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		// Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String gerenciaRegionalID = filtrarGerenciaRegionalActionForm.getGerenciaRegionalID();
		String gerenciaRegionalNome = filtrarGerenciaRegionalActionForm.getGerenciaRegionalNome();
		String gerenciaRegionalNomeAbre = filtrarGerenciaRegionalActionForm.getGerenciaRegionalNomeAbre();
		
		String indicadorUso = filtrarGerenciaRegionalActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarGerenciaRegionalActionForm.getTipoPesquisa();
		String cnpjGerenciaRegional = filtrarGerenciaRegionalActionForm.getCnpjGerenciaRegional();
		
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		
		// Verifica se o campo C�digo foi informado
		if (gerenciaRegionalID != null && !gerenciaRegionalID.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;

			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.ID, gerenciaRegionalID));
		}

		// Verifica se o campo Descri��o foi informado
		if (gerenciaRegionalNome != null && !gerenciaRegionalNome.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null &&
				tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
					
				filtroGerenciaRegional.adicionarParametro(new ComparacaoTextoCompleto(
					FiltroGerenciaRegional.NOME, gerenciaRegionalNome));
			}else {
				filtroGerenciaRegional.adicionarParametro(new ComparacaoTexto(
						FiltroGerenciaRegional.NOME, gerenciaRegionalNome));
			}
		}
		
		// Verifica se o CNPJ da Gerencia de configuracao foiinformada.
		if (cnpjGerenciaRegional != null && !cnpjGerenciaRegional.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
					
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.CNPJ_GERENCIA_REGIONAL, cnpjGerenciaRegional));
		}
		

		// Verifica se o campo descri��o abreviatura foi informado
		if (gerenciaRegionalNomeAbre != null
				&& !gerenciaRegionalNomeAbre.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroGerenciaRegional.adicionarParametro(new ComparacaoTexto(
					FiltroGerenciaRegional.NOME_ABREVIADO,
					gerenciaRegionalNomeAbre));

		}
		
		// Verifica se o campo Indicador de Uso foi informado
		if (indicadorUso != null && !indicadorUso.equalsIgnoreCase("") && !indicadorUso.equalsIgnoreCase("3")) {
			peloMenosUmParametroInformado = true;
			if (indicadorUso.equalsIgnoreCase(String.valueOf(ConstantesSistema.INDICADOR_USO_ATIVO))) {
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
			} else {
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
			}
		}
		
		// Carrega os dados do Endereco
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroGerenciaRegional
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		
		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		sessao.setAttribute("filtroGerenciaRegional", filtroGerenciaRegional);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		return retorno;
	}
}