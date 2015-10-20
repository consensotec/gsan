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
package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.indicador.FiltroTabelaAuxiliarIndicador;
import gcom.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicador;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aur�lio
 *
 */
public class ExibirAtualizarTabelaAuxiliarIndicadorAction extends GcomAction {
	/**
	 * < <Descri��o do m�todo>>
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarTabelaAuxiliarIndicador");

		// Obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		int tamMaxCampoDescricao = 40;


		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		
		// String com path do pacote mais o nome do objeto
		String pacoteNomeObjeto = (String) sessao
				.getAttribute("pacoteNomeObjeto");
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		String id = null;
		// C�digo da tabela auxiliar a ser atualizada
		if (httpServletRequest.getAttribute("id") != null) {
			id = (String) httpServletRequest.getAttribute("id");
			sessao.setAttribute("id", id);
		} else {
			if (manutencaoRegistroActionForm.getIdRegistroAtualizacao() != null) {
				id = manutencaoRegistroActionForm.getIdRegistroAtualizacao();
				sessao.setAttribute("id", id);
			}
		}

		if (sessao.getAttribute("id") != null) {
			id = (String) sessao.getAttribute("id");
		}

		// Cria o filtro para atividade
		FiltroTabelaAuxiliarIndicador filtroTabelaAuxiliarIndicador = new FiltroTabelaAuxiliarIndicador();
		
		String tela = (String) sessao.getAttribute("tela");

		if (httpServletRequest.getAttribute("desfazer") == null) {

			// Adiciona o par�metro no filtro
			filtroTabelaAuxiliarIndicador
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarIndicador.ID, id));

	

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresIndicador = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarIndicador,
					pacoteNomeObjeto);

			// Caso a cole��o esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresIndicador == null
					|| tabelasAuxiliaresIndicador.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarIndicador = tabelasAuxiliaresIndicador
					.iterator();

			// A tabela auxiliar abreviada que ser� atualizada
			TabelaAuxiliarIndicador tabelaAuxiliarIndicador = (TabelaAuxiliarIndicador) iteratorTabelaAuxiliarIndicador
					.next();

			// Manda a tabela auxiliar na sess�o
			sessao.setAttribute("tabelaAuxiliarIndicador",
					tabelaAuxiliarIndicador);


			if (tela.equalsIgnoreCase("quadraPerfil")) {

				if (tabelaAuxiliarIndicador.getIndicadorUso().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
						.intValue()) {

					sessao.setAttribute("indicadorUso", "sim");
				} else {
					sessao.setAttribute("indicadorUso", "nao");
				}

			}
			
			
			if (tela.equalsIgnoreCase("quadraPerfil")) {

				if (tabelaAuxiliarIndicador.getIndicadorBaixaRenda().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO
						.intValue()) {

					sessao.setAttribute("indicadorBaixaRenda", "sim");
				} else {
					sessao.setAttribute("indicadorBaixaRenda", "nao");
				}

			}
			
			DadosTelaTabelaAuxiliarIndicador dados = (DadosTelaTabelaAuxiliarIndicador) DadosTelaTabelaAuxiliarIndicador
					.obterDadosTelaTabelaAuxiliar(tela);

			sessao.setAttribute("funcionalidadeTabelaAuxiliarIndicadorFiltrar",
					dados.getFuncionalidadeTabelaIndicadorFiltrar());

			sessao.setAttribute("tamMaxCampoDescricao", new Integer(
					tamMaxCampoDescricao));
	
		}

		if (httpServletRequest.getAttribute("desfazer") != null) {

			// Cria o filtro para atividade
			filtroTabelaAuxiliarIndicador.limparListaParametros();

			// Adiciona o par�metro no filtro
			filtroTabelaAuxiliarIndicador
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarIndicador.ID, id));

			// Pesquisa a tabela auxiliar no sistema
			Collection tabelasAuxiliaresIndicadorBase = fachada
					.pesquisarTabelaAuxiliar(filtroTabelaAuxiliarIndicador,
							pacoteNomeObjeto);

			// Caso a cole��o esteja vazia, indica erro inesperado
			if (tabelasAuxiliaresIndicadorBase == null
					|| tabelasAuxiliaresIndicadorBase.isEmpty()) {
				throw new ActionServletException("erro.sistema");
			}

			Iterator iteratorTabelaAuxiliarIndicadorBase = tabelasAuxiliaresIndicadorBase
					.iterator();

			// A tabela auxiliar abreviada que ser� atualizada
			TabelaAuxiliarIndicador tabelaAuxiliarIndicadorBase = (TabelaAuxiliarIndicador) iteratorTabelaAuxiliarIndicadorBase
					.next();

			// Manda a tabela auxiliar na sess�o
			sessao.setAttribute("tabelaAuxiliarIndicadorBase",
					tabelaAuxiliarIndicadorBase);

		}
		
		//Manda a tabela auxiliar na sess�o
		httpServletRequest.setAttribute("tela",tela);

		//Devolve o mapeamento de retorno
		return retorno;
	}

}

