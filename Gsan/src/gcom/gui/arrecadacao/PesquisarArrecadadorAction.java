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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza a pesquisa de cliente de acordo com os par�metros informados
 * 
 * @author S�vio Luiz
 * @created 25 de Janeiro de 2006
 */
public class PesquisarArrecadadorAction extends GcomAction {
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("listaArrecadador");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarArrecadadorActionForm pesquisarArrecadadorActionForm = (PesquisarArrecadadorActionForm) actionForm;

		// Recupera os par�metros do form
		String inscricaoEstadual = (String) pesquisarArrecadadorActionForm
				.getInscricaoEstadual();
		String idLocalidade = (String) pesquisarArrecadadorActionForm
				.getIdLocalidade();
		String idCliente = (String) pesquisarArrecadadorActionForm
				.getIdCliente();
		String idImovel = (String) pesquisarArrecadadorActionForm.getIdImovel();

		// filtro para a pesquisa de endereco do cliente
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

		filtroArrecadador.setCampoOrderBy(FiltroArrecadador.CODIGO_AGENTE);

		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro

		if (inscricaoEstadual != null
				&& !inscricaoEstadual.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ComparacaoTexto(
					FiltroArrecadador.INSCRICAO_ESTATAL, inscricaoEstadual));
		}

		if (idLocalidade != null && !idLocalidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.LOCALIDADE_ID, idLocalidade));
		}

		if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CLIENTE_ID, idCliente));
		}

		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.IMOVEL_ID, idImovel));
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroArrecadador
				.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("imovel");

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoArrecadadores = null;

		// Obt�m a inst�ncia da Fachada
		//Fachada fachada = Fachada.getInstancia();

		// pesquisa os endere�os do cliente
		//colecaoArrecadadores = fachada.pesquisar(filtroArrecadador,
			//	Arrecadador.class.getName());
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroArrecadador, Arrecadador.class.getName());
		colecaoArrecadadores = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoArrecadadores == null || colecaoArrecadadores.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Arrecadador");
		} else if (colecaoArrecadadores.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a cole��o na sess�o
			sessao.setAttribute("colecaoArrecadadores", colecaoArrecadadores);

		}

		return retorno;
	}

}
