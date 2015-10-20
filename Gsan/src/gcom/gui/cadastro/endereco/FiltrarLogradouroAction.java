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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que filtra os par�metros inseridos no logradouro_filtrar.jsp para
 * recuperar os logradouros
 * 
 * @author S�vio Luiz
 * @date 28/06/2006
 */
public class FiltrarLogradouroAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	//filtrarLogradouro
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("retornarFiltroLogradouro");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		// Recupera os par�metros do form
		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioFiltro");
		String idBairro = (String) pesquisarActionForm.get("idBairro");
		
		String nomeLogradouro = (String) pesquisarActionForm
				.get("nomeLogradouro");
		String nomePopularLogradouro = (String) pesquisarActionForm
				.get("nomePopularLogradouro");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouro");
		String codigoCep = (String) pesquisarActionForm.get("cep");
		Integer idTipoLogradouro = (Integer) pesquisarActionForm.get("idTipo");
		Integer idTituloLogradouro = (Integer) pesquisarActionForm
				.get("idTitulo");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
		filtroLogradouro
				.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro

		if (idMunicipio != null && !idMunicipio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID_MUNICIPIO, idMunicipio));
		}
		if (nomeLogradouro != null
				&& !nomeLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ComparacaoTexto(
			// FiltroLogradouro.NOME, nomeLogradouro));
		}
		if (nomePopularLogradouro != null
				&& !nomePopularLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ComparacaoTexto(
			// FiltroLogradouro.NOME_POPULAR, nomePopularLogradouro));
		}
		if (idLogradouro != null && !idLogradouro.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID, idLogradouro));
		}

		if (idTipoLogradouro != null
				&& idTipoLogradouro.intValue() > ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID_LOGRADOUROTIPO, idTipoLogradouro));
		}
		if (idTituloLogradouro != null
				&& idTituloLogradouro.intValue() > ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.ID_LOGRADOUROTITULO, idTituloLogradouro));
		}

		/*
		 * Caso seja passado como par�metro o c�digo do Munic�pio
		 */
		if (codigoCep != null && !codigoCep.equals("")) {

			if (!peloMenosUmParametroInformado) {
				httpServletRequest.setAttribute("consultaPorCep", "OK");
			}

			peloMenosUmParametroInformado = true;
			httpServletRequest.setAttribute("codigoCep", codigoCep);
		}
		if (idBairro != null && !idBairro.equals("")) {

			if (!peloMenosUmParametroInformado) {
				httpServletRequest.setAttribute("consultaPorBairro", "OK");
			}

			peloMenosUmParametroInformado = true;
			
			sessao.setAttribute("idBairroFiltro","true");
			
			httpServletRequest.setAttribute("idBairro", idBairro);
		}else{
			sessao.setAttribute("idBairroFiltro","false");
		}
		
		if (indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			// filtroLogradouro.adicionarParametro(new ParametroSimples(
			// FiltroLogradouro.INDICADORUSO, indicadorUso));
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Manda o filtro pelo request para o ExibirManterLogradouroAction
		httpServletRequest.setAttribute("filtroLogradouro", filtroLogradouro);
		// sessao.setAttribute("filtroLogradouro", filtroLogradouro);

		String atualizar = null;

		atualizar = (String) httpServletRequest.getParameter("atualizar");

		if (atualizar != null && !atualizar.equalsIgnoreCase("")) {
			
			sessao.setAttribute("atualizar", "true");
		}
		
		//Retirado conforme RM 6141
		/*FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem(
				FiltroOSProgramaCalibragem.NUMERO_GRAU_IMPORTANCIA);
		filtroOSProgramaCalibragem.setConsultaSemLimites(true);
		filtroOSProgramaCalibragem.adicionarParametro(new ParametroSimples(
				FiltroOSProgramaCalibragem.PRIORIZACAO_TIPO_ID,
				new Integer(1)));
		filtroOSProgramaCalibragem.adicionarCaminhoParaCarregamentoEntidade("priorizacaoTipo");
		Collection osProgramacaoCalibragem = Fachada.getInstancia().pesquisar(
				filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());

		if (osProgramacaoCalibragem != null && !osProgramacaoCalibragem.isEmpty()) {
		
			sessao.setAttribute("osProgramacaoCalibragem", osProgramacaoCalibragem);
		}*/

		return retorno;		
		
	}
	
	
}
