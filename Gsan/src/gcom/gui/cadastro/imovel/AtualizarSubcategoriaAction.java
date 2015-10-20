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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela atualiza��o da subcategoria
 * 
 * [UC0059] Atualizar Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 4/01/2006
 */
public class AtualizarSubcategoriaAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		Fachada fachada = Fachada.getInstancia();

		Subcategoria subcategoria = (Subcategoria) sessao
				.getAttribute("subcategoria");

		String codigoSubcategoria = filtrarSubcategoriaActionForm
				.getCodigoSubcategoria();

		String idCategoria = (String) filtrarSubcategoriaActionForm
				.getIdCategoria();

		Short indicadorDeUso = new Short(filtrarSubcategoriaActionForm
				.getIndicadorUso());
		
		String descricaoAbreviada = filtrarSubcategoriaActionForm.getDescricaoAbreviada();
        String  numeroFatorFiscalizacao =  filtrarSubcategoriaActionForm.getNumeroFatorFiscalizacao() ;
        String indicadorSazonalidade = filtrarSubcategoriaActionForm.getIndicadorSazonalidade();
        
        Short  indicadorTarifaConsumo = null;
        
        if(filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo() != null && !filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo().equalsIgnoreCase("")){

        	indicadorTarifaConsumo =  new Short(filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo()) ;
        }
        
        String codigoTarifaSocial = null;
		
        if ( filtrarSubcategoriaActionForm.getCodigoTarifaSocial() != null && !filtrarSubcategoriaActionForm.getCodigoTarifaSocial().equalsIgnoreCase("")){
        
			codigoTarifaSocial = filtrarSubcategoriaActionForm.getCodigoTarifaSocial();
		}
        Integer codigoGrupoSubcategoria = null; 
        
        if(filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria() != null && !filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria().equalsIgnoreCase("")){
        
        	codigoGrupoSubcategoria = new Integer(filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria());
        }

		Categoria categoria = null;

		if (idCategoria != null && !idCategoria.equals("")) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, idCategoria));
			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection categorias = fachada.pesquisar(filtroCategoria,
					Categoria.class.getName());

			if (categorias != null && !categorias.isEmpty()) {
				// A categoria foi encontrada
				Iterator categoriaIterator = categorias.iterator();

				categoria = (Categoria) categoriaIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Categoria");
			}

		}
		// seta os campos para serem atualizados
		subcategoria.setId(new Integer(filtrarSubcategoriaActionForm
				.getIdSubcategoria()));
		subcategoria.setCategoria(categoria);
		subcategoria.setCodigo(Integer.parseInt(codigoSubcategoria));
		
		subcategoria.setDescricao(filtrarSubcategoriaActionForm
				.getDescricaoSubcategoria());
		subcategoria.setIndicadorUso(indicadorDeUso);
		subcategoria.setDescricaoAbreviada(descricaoAbreviada);
		subcategoria.setCodigoTarifaSocial(codigoTarifaSocial);
		subcategoria.setCodigoGrupoSubcategoria(codigoGrupoSubcategoria);
		subcategoria.setNumeroFatorFiscalizacao(new Short(numeroFatorFiscalizacao));
		subcategoria.setIndicadorTarifaConsumo(indicadorTarifaConsumo);
		subcategoria.setIndicadorSazonalidade(new Short(indicadorSazonalidade));
		
		
		// ------------ REGISTRAR TRANSA��O ----------------
		subcategoria.setOperacaoEfetuada(operacaoEfetuada);
		subcategoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(subcategoria);
		// ------------ REGISTRAR TRANSA��O ----------------

		Subcategoria subcategoriaVelha = (Subcategoria) sessao
				.getAttribute("subcategoria");

		fachada.atualizarSubcategoria(subcategoria, subcategoriaVelha);

		montarPaginaSucesso(httpServletRequest, "Subcategoria de c�digo "
				+ subcategoria.getCodigo() + " da categoria "
				+ subcategoria.getCategoria().getDescricao()
				+ " atualizada com sucesso.",
				"Realizar outra Manuten��o de Subcategoria",
				"exibirManterSubcategoriaAction.do?menu=sim");

		return retorno;
	}

}
