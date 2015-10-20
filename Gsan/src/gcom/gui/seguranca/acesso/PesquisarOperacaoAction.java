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

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroPesquisarOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe 
 *
 * @author Administrador
 * @date 05/05/2006
 */
public class PesquisarOperacaoAction extends GcomAction {
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
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("listaPesquisarOperacaoResultado");

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarOperacaoActionForm pesquisarOperacaoActionForm = (PesquisarOperacaoActionForm) actionForm;

		// Recupera os par�metros do form
		String idOperacao = pesquisarOperacaoActionForm.getCodigoOperacao();
		String nomeOperacao = pesquisarOperacaoActionForm.getNomeOperacao();
		String tipoOperacao = pesquisarOperacaoActionForm.getTipoOperacao();
		String idFuncionalidade = pesquisarOperacaoActionForm
				.getIdFuncionalidade();

		// filtro para a pesquisa de endereco do cliente
		FiltroPesquisarOperacao filtroPesquisarOperacao = new FiltroPesquisarOperacao();

		filtroPesquisarOperacao.setCampoOrderBy(FiltroOperacao.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os par�metros informados no filtro
		if (idOperacao != null && !idOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
					FiltroPesquisarOperacao.ID, idOperacao));
		}

		if (nomeOperacao != null && !nomeOperacao.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ComparacaoTexto(
					FiltroPesquisarOperacao.DESCRICAO, nomeOperacao));
		}

		if (tipoOperacao != null
				&& !tipoOperacao.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
					FiltroPesquisarOperacao.OPERACAO_TIPO, tipoOperacao));
		}

		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
					FiltroPesquisarOperacao.FUNCIONALIDADE, idFuncionalidade));
		}

		// if (idfuncionalidade != null && !idfuncionalidade.trim().equals(""))
		// {
		// peloMenosUmParametroInformado = true;
		// filtroPesquisarOperacao.adicionarParametro(new ParametroSimples(
		// FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idfuncionalidade));
		// }

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroPesquisarOperacao
				.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroPesquisarOperacao
				.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroPesquisarOperacao
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");

		Collection colecaoPesquisarOperacao = null;

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// pesquisa os endere�os do cliente
		colecaoPesquisarOperacao = fachada.pesquisar(filtroPesquisarOperacao,
				Operacao.class.getName());

		if (colecaoPesquisarOperacao == null
				|| colecaoPesquisarOperacao.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Opera��o");
		} else if (colecaoPesquisarOperacao.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a cole��o na sess�o
			sessao.setAttribute("colecaoPesquisarOperacao",
					colecaoPesquisarOperacao);

		}

		return retorno;
	}

}
