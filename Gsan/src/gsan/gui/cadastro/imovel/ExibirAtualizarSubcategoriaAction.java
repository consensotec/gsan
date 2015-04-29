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

import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.FiltroCategoria;
import gsan.cadastro.imovel.FiltroSubCategoria;
import gsan.cadastro.imovel.Subcategoria;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de atualizar subcategoria
 * 
 * [UC0059] Atualizar Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 04/01/2005
 */
public class ExibirAtualizarSubcategoriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarSubcategoria");

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		String codigoSubcategoria = httpServletRequest
				.getParameter("idRegistroAtualizacao");
		
		if (codigoSubcategoria == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				codigoSubcategoria = (String) sessao.getAttribute("codigoSubcategoria");
			}else{
				codigoSubcategoria = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
			
		sessao.setAttribute("codigoSubcategoria", codigoSubcategoria);

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<Categoria> collectionImovelCategoria = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		httpServletRequest.setAttribute("collectionImovelCategoria",
				collectionImovelCategoria);

		// ------Inicio da parte que verifica se vem da p�gina de manter_subcategoria.jsp
		if (codigoSubcategoria != null && !codigoSubcategoria.equals("")) {

			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.ID, codigoSubcategoria));

			// Informa ao filtro para ele buscar objetos associados a Subcategoria
			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection subcategorias = fachada.pesquisar(filtroSubcategoria,
					Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {
				// A subcategoria foi encontrada
				filtrarSubcategoriaActionForm
						.setIdCategoria(formatarResultado(((Subcategoria) ((List) subcategorias)
								.get(0)).getCategoria().getId().toString()));

				filtrarSubcategoriaActionForm
						.setCodigoSubcategoria(formatarResultado(""
								+ ((Subcategoria) ((List) subcategorias).get(0))
										.getCodigo()));

				filtrarSubcategoriaActionForm
						.setDescricaoSubcategoria(formatarResultado(((Subcategoria) ((List) subcategorias)
								.get(0)).getDescricao()));
				
				filtrarSubcategoriaActionForm
				.setDescricaoAbreviada(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getDescricaoAbreviada()));
				
				filtrarSubcategoriaActionForm
				.setCodigoTarifaSocial(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getCodigoTarifaSocial()));
				
				filtrarSubcategoriaActionForm
				.setCodigoGrupoSubcategoria(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getCodigoGrupoSubcategoria()));

				filtrarSubcategoriaActionForm
				.setNumeroFatorFiscalizacao(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getNumeroFatorFiscalizacao()));
				
				filtrarSubcategoriaActionForm
				.setIndicadorTarifaConsumo(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getIndicadorTarifaConsumo()));
				
				filtrarSubcategoriaActionForm
				.setIndicadorSazonalidade(formatarResultado(""
						+ ((Subcategoria) ((List) subcategorias).get(0))
								.getIndicadorSazonalidade()));
				
				filtrarSubcategoriaActionForm
						.setIndicadorUso(formatarResultado(""
								+ ((Subcategoria) ((List) subcategorias).get(0))
										.getIndicadorUso()));

				filtrarSubcategoriaActionForm.setIdSubcategoria(codigoSubcategoria);
				
				Subcategoria subcategoria = ((Subcategoria) ((List) subcategorias)
						.get(0));

				sessao.setAttribute("subcategoria", subcategoria);
				sessao.setAttribute("filtrarSubcategoriaActionForm",
						filtrarSubcategoriaActionForm);

			}

		}
		// ------Fim da parte que verifica se vem da p�gina de
		// manter_subcategoria.jsp
		
		// caso ainda n�o tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
		return retorno;
	}

	/**
	 * Formata o resultado 
	 * 
	 * @param parametro
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}
}