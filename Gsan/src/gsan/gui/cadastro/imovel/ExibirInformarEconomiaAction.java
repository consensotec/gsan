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
package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelSubcategoria;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.filtro.FiltroParametro;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pelo pre-processamento de inserir economia
 * 
 * @author S�vio Luiz
 * @created 1 de Junho de 2004
 */
public class ExibirInformarEconomiaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("informarEconomia");

		EconomiaActionForm economiaActionForm = (EconomiaActionForm) actionForm;

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equalsIgnoreCase(
						"sim")) {

			httpServletRequest.setAttribute("nomeCampo", "idImovel");
		}

		// Cria Cole�ao
		Collection colecaoImovelSubCategoriasCadastradas = null;

		Collection colecaoImovelEconomiasModificadas = null;

		// int i = 1;
		// Cole��o vinda do exibirInserirEconomiaAcion
		// nessa cole��o est�o todos os imoveis sub categorias que foi
		// pesquisado no economia_inserir_jsp
		if (sessao.getAttribute("colecaoImovelSubCategoriasCadastradas") != null
				&& !sessao
						.getAttribute("colecaoImovelSubCategoriasCadastradas")
						.equals("")) {

			colecaoImovelSubCategoriasCadastradas = (Collection) sessao
					.getAttribute("colecaoImovelSubCategoriasCadastradas");

		} else {
			colecaoImovelSubCategoriasCadastradas = new ArrayList();
		}

		// cria uma cole��o para os imoveis economias inseridos e/ou modificado
		// essa cole��o ser� respons�vel pela inser��o ou modifica��o na base
		if (sessao.getAttribute("colecaoImovelEconomiasModificadas") != null
				&& !sessao.getAttribute("colecaoImovelEconomiasModificadas")
						.equals("")) {

			colecaoImovelEconomiasModificadas = (Collection) sessao
					.getAttribute("colecaoImovelEconomiasModificadas");

		} else {
			colecaoImovelEconomiasModificadas = new ArrayList();
		}

		// caso venha do popup de inserir_economia_popup n�o faz
		// nada so manda a cole��o para a pagina de economia inserir
		if (httpServletRequest.getParameter("retornaDoPopup") == null) {

			FiltroImovel filtroImovel = new FiltroImovel();

			Fachada fachada = Fachada.getInstancia();

			String idImovel = null;

			// Verifica se o tipoConsulta � diferente de nulo ou vazio esse tipo
			// consulta vem do
			// imovel_resultado_pesquisa.jsp.
			// � feita essa verifica��o pois pode ser que ainda n�o tenha
			// feito a pesquisa de imovel.
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {

				// Verifica se vem algum parametro do tipo consulta, que pode
				// vim do removerEconomiaAction,
				// ou da pagina economia_informar,ou pelo enter ou pela pesquisa
				// da lupa

				idImovel = (String) economiaActionForm.getIdImovel();

				// remove da sessao o caminho da pesquisa pela lupa de im�vel
				sessao.removeAttribute("caminhoRetornoTelaPesquisaImovel");

				sessao
						.removeAttribute("colecaoClientesImoveisEconomiaRemovidas");

				if (!httpServletRequest.getParameter("tipoConsulta").equals(
						"remover")) {
					colecaoImovelEconomiasModificadas = new ArrayList();
				}

			} else {

				sessao.removeAttribute("colecaoImovelSubCategoriasCadastradas");
				colecaoImovelSubCategoriasCadastradas = new ArrayList();

				sessao.removeAttribute("colecaoClientesImoveisEconomia");
				sessao
						.removeAttribute("clientesImoveisEconomiaSemDataFimRelacao");
				sessao.removeAttribute("colecaoImovelEconomiasModificadas");
				sessao.removeAttribute("imovel");
				sessao
						.removeAttribute("colecaoClientesImoveisEconomiaRemovidas");

				colecaoImovelEconomiasModificadas = new ArrayList();
				economiaActionForm.setIdImovel(null);
				economiaActionForm.setEnderecoImovel(null);
				economiaActionForm.setMatriculaImovel(null);

			}

			// -------Parte que trata do c�digo quando o usu�rio tecla enter
			// se o id do cliente for diferente de nulo
			if (idImovel != null
					&& !idImovel.toString().trim().equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoRemovidas") != null) {
					sessao.removeAttribute("colecaoRemovidas");
				}

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));

				// adiciona o indicador de exclus�o do imovel
				filtroImovel
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
								Imovel.IMOVEL_EXCLUIDO,
								FiltroParametro.CONECTOR_OR, 2));

				filtroImovel.adicionarParametro(new ParametroNulo(
						FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO));

				/*
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				 * 
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
				 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
				 * 
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				 * 
				 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("lote");
				 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("subLote");
				 * 
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("localidade");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
				 * filtroImovel
				 * .adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
				 */
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

				Collection imoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (imoveis == null || imoveis.isEmpty()) {

					// Caso a cole��o n�o tenha retornado objetos
					economiaActionForm
							.setMatriculaImovel("MATRICULA INEXISTENTE");
					httpServletRequest.setAttribute("matInexistente", "ok");
					sessao
							.removeAttribute("colecaoImovelSubCategoriasCadastradas");
					colecaoImovelSubCategoriasCadastradas = new ArrayList();

					sessao.removeAttribute("colecaoClientesImoveisEconomia");
					sessao
							.removeAttribute("clientesImoveisEconomiaSemDataFimRelacao");
					sessao.removeAttribute("colecaoImovelEconomiasModificadas");
					sessao.removeAttribute("imovel");

					colecaoImovelEconomiasModificadas = new ArrayList();
					economiaActionForm.setIdImovel(null);
					economiaActionForm.setEnderecoImovel(null);
				} else {

					// retorna o im�vel que veio da cole��o
					Imovel imovel = (Imovel) ((List) imoveis).get(0);

					Collection imoveisSubCategorias = fachada
							.obterColecaoImovelSubcategorias(imovel, 2);

					Iterator colecaoimoveisSubCategoriasIterator = imoveisSubCategorias
							.iterator();

					Collection colecao = new ArrayList();

					ImovelSubcategoria imovelSubcategoriaAux = null;

					while (colecaoimoveisSubCategoriasIterator.hasNext()) {

						ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) colecaoimoveisSubCategoriasIterator
								.next();
						imovelSubcategoriaAux = imovelSubcategoria;

						// idImovel
//						FiltroImovelEconomia filtroImovelEconomia = new FiltroImovelEconomia();
//
//						filtroImovelEconomia.adicionarParametro(new ParametroSimples(
//								FiltroImovelEconomia.IMOVEL_ID, idImovel));
//						
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.AREA_CONSTRUIDA_FAIXA);
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelEconomia.IMOVEL_SUB_CATEGORIA);
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.subcategoria.categoria");
//						filtroImovelEconomia.adicionarCaminhoParaCarregamentoEntidade("imovelSubcategoria.comp_id.imovel.imovelPerfil");
//
//						filtroImovelEconomia.adicionarParametro(new ParametroSimples(
//							FiltroImovelEconomia.SUB_CATEGORIA_ID, imovelSubcategoria.getComp_id().getSubcategoria().getId()));
//
//						Collection colecaoImovelEconomia = fachada.pesquisar(
//								filtroImovelEconomia, ImovelEconomia.class.getName());
						
						
						Collection colecaoImovelEconomia = fachada.pesquisarImovelEconomia(
								new Integer(idImovel), imovelSubcategoria.getComp_id().getSubcategoria().getId());
						
						
						HashSet colecaoImovelEconomiaSet = new HashSet();
						colecaoImovelEconomiaSet.addAll(colecaoImovelEconomia);

						imovelSubcategoriaAux.setImovelEconomias(colecaoImovelEconomiaSet);
						colecao.add(imovelSubcategoriaAux);

					}
					// Imovel imovel = fachada.pe

					String enderecoFormatado = fachada
							.pesquisarEndereco(new Integer(idImovel));

					if (enderecoFormatado != null
							&& !enderecoFormatado.trim().equals("")) {
						economiaActionForm.setEnderecoImovel(enderecoFormatado);
					} else {
						economiaActionForm
								.setEnderecoImovel("Im�vel sem endere�o");
					}

					colecaoImovelSubCategoriasCadastradas = new ArrayList();

					colecaoImovelSubCategoriasCadastradas.addAll(colecao);

					economiaActionForm.setMatriculaImovel(imovel
							.getInscricaoFormatada());
					sessao.setAttribute("imovel", imovel);
				}
			}
			// fim da parte que trata do codigo do usuario que tecla enter

		} else {
			sessao.removeAttribute("imovelEconomia");
		}

		sessao.setAttribute("colecaoImovelSubCategoriasCadastradas",
				colecaoImovelSubCategoriasCadastradas);

		// manda a cole��o para os imoveis economias inseridos e/ou modificado
		sessao.setAttribute("colecaoImovelEconomiasModificadas",
				colecaoImovelEconomiasModificadas);

		return retorno;
	}
}
