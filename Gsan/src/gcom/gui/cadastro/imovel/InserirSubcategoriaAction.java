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
import gcom.cadastro.imovel.FiltroSubCategoria;
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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela inser��o da subcategoria
 * 
 * [UC0058] Inserir Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 28/12/2005
 */
public class InserirSubcategoriaAction extends GcomAction {
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

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirSubcategoriaActionForm inserirSubcategoriaActionForm = (InserirSubcategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		//------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBCATEGORIA_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		//------------ REGISTRAR TRANSA��O ----------------

		//Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		//Obt�m a sess�o
		//HttpSession sessao = httpServletRequest.getSession(false);

		String categoria = inserirSubcategoriaActionForm.getCategoria();
		String descricaoSubcategoria = (String) inserirSubcategoriaActionForm
				.getDescricaoSubcategoria();
		Integer codigoSubcategoria = new Integer(inserirSubcategoriaActionForm
				.getCodigoSubcategoria());
		String descricaoAbreviada = inserirSubcategoriaActionForm
				.getDescricaoAbreviada();
		String codigoTarifaSocial = inserirSubcategoriaActionForm
				.getCodigoTarifaSocial();
		Integer codigoGrupoSubcategoria = null;

		if (inserirSubcategoriaActionForm.getCodigoGrupoSubcategoria() != null && !inserirSubcategoriaActionForm.getCodigoGrupoSubcategoria().equalsIgnoreCase("")) {

			codigoGrupoSubcategoria = new Integer(inserirSubcategoriaActionForm
					.getCodigoGrupoSubcategoria());
		}
		String numeroFatorFiscalizacao = inserirSubcategoriaActionForm
				.getNumeroFatorFiscalizacao();

		Short indicadorTarifaConsumo = null;

		if (inserirSubcategoriaActionForm.getIndicadorTarifaConsumo() != null  && !inserirSubcategoriaActionForm.getIndicadorTarifaConsumo().equalsIgnoreCase("")) {

			indicadorTarifaConsumo = new Integer(inserirSubcategoriaActionForm
					.getIndicadorTarifaConsumo()).shortValue();
		}

		String indicadorSazonalidade = inserirSubcategoriaActionForm
				.getIndicadorSazonalidade();

		Categoria categoriaSelecionada = null;
		Subcategoria subcategoriaSelecionada = null;

		//Verifica a descri��o da categoria 
		if (categoria != null && !categoria.equals("")) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, categoria));

			Collection<Categoria> categorias = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (categorias != null && categorias.isEmpty()) {
				throw new ActionServletException(
						"atencao.categoria_inexistente", null, "codigo");
			}

			categoriaSelecionada = categorias.iterator().next();
		}
		if (codigoSubcategoria != null) {
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CODIGO, codigoSubcategoria));

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, categoria));

			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection<Subcategoria> subcategorias = fachada.pesquisar(
					filtroSubcategoria, Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {
				subcategoriaSelecionada = subcategorias.iterator().next();
				throw new ActionServletException(
						"atencao.subcategoria_ja_existente",
						codigoSubcategoria.toString(), subcategoriaSelecionada
								.getCategoria().getDescricao());
			}
		}

		Short indicadorDeUso = ConstantesSistema.INDICADOR_USO_ATIVO;

		//cria o objeto subcategoria para ser inserido
		
		Subcategoria subcategoria = new Subcategoria(codigoSubcategoria,
				descricaoSubcategoria, indicadorDeUso, new Short(
						indicadorSazonalidade), descricaoAbreviada,
				codigoTarifaSocial, codigoGrupoSubcategoria,
				new Short(numeroFatorFiscalizacao), 
						indicadorTarifaConsumo, new Date(),
				categoriaSelecionada);

		//------------ REGISTRAR TRANSA��O ----------------
		subcategoria.setOperacaoEfetuada(operacaoEfetuada);
		subcategoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(subcategoria);
		//------------ REGISTRAR TRANSA��O ----------------

		fachada.inserir(subcategoria);

		montarPaginaSucesso(httpServletRequest, "Subcategoria de c�digo "
				+ subcategoria.getCodigo() + " da categoria "
				+ subcategoria.getCategoria().getDescricao()
				+ " inserida com sucesso.", "Inserir outra Subcategoria",
				"exibirInserirSubcategoriaAction.do?menu=sim",
				"exibirAtualizarSubcategoriaAction.do?idRegistroAtualizacao="
						+ subcategoria.getId(),
				"Atualizar Subcategoria Inserida");

		return retorno;
	}
}
