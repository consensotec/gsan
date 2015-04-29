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
package gsan.gui.cobranca;

import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cobranca.FiltroUnidadeNegocioTestemunha;
import gsan.cobranca.UnidadeNegocioTestemunha;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibi��o da tela para o usu�rio setar os campos e permitir
 * que ele insera uma resolu��o de diretoria [UC0217] Inserir Resolu��o de
 * Diretoria
 * 
 * @author Rafael Corr�a
 * @since 30/03/2006
 */
public class ExibirInformarUnidadeNegocioTestemunhaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarUnidadeNegocioTestemunha");
		
		InformarUnidadeNegocioTestemunhaActionForm informarUnidadeNegocioTestemunhaActionForm = (InformarUnidadeNegocioTestemunhaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Carrega as cole��es que ser�o exibidas na tela
		
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio != null
					&& !colecaoUnidadeNegocio.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeNegocio",
						colecaoUnidadeNegocio);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"UNIDADE_NEGOCIO");
			}
		}
		
		String idUnidadeNegocio = informarUnidadeNegocioTestemunhaActionForm.getIdUnidadeNegocio();
		String idTestemunha = informarUnidadeNegocioTestemunhaActionForm.getIdTestemunha();
		String loginTestemunha = informarUnidadeNegocioTestemunhaActionForm.getLoginTestemunha();
		
		// Pesquisa a testemunha
		if ((loginTestemunha != null && !loginTestemunha.trim().equals("")) || (idTestemunha != null && !idTestemunha.trim().equals(""))) {
			
			// Verifica se deve desabilitar o bot�o adicionar e a testemunha,
			// caso o usu�rio
			// tenha alterado a unidade de neg�cio deixa o bot�o desabilitado
			if (httpServletRequest.getParameter("desabilitaCampos") != null
					&& !httpServletRequest.getParameter("desabilitaCampos")
							.trim().equals("")) {
				httpServletRequest.setAttribute("desabilitaCampos", "sim");
			}
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			// Verifica se a pesquisa foi pelo enter
			if ((loginTestemunha != null && !loginTestemunha.trim().equals(""))) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginTestemunha));
			} 
			// Verifica se a pesquisa foi feita pela lupa
			else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idTestemunha));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				informarUnidadeNegocioTestemunhaActionForm.setIdTestemunha(usuario.getId().toString());
				informarUnidadeNegocioTestemunhaActionForm.setLoginTestemunha(usuario.getLogin());
				informarUnidadeNegocioTestemunhaActionForm.setNomeTestemunha(usuario.getNomeUsuario());
			} else {
				informarUnidadeNegocioTestemunhaActionForm.setIdTestemunha("");
				informarUnidadeNegocioTestemunhaActionForm.setLoginTestemunha("");
				informarUnidadeNegocioTestemunhaActionForm.setNomeTestemunha("TESTEMUNHA INEXISTENTE");
				
				httpServletRequest.setAttribute("testemunhaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idTestemunha");
			}
			
		} else {
			informarUnidadeNegocioTestemunhaActionForm.setNomeTestemunha("");
		}
		
		// Cole��o retornada na consulta
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha = (Collection<UnidadeNegocioTestemunha>) sessao.getAttribute("colecaoUnidadeNegocioTestemunha");
		
		// Cole��o com os objetos adicionados pelo usu�rio
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaAdicionadas = (Collection<UnidadeNegocioTestemunha>) sessao.getAttribute("colecaoUnidadeNegocioTestemunhaAdicionadas");
		
		// Cole��o com os objetos removidos pelo usu�rio
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaRemovidas = (Collection<UnidadeNegocioTestemunha>) sessao
		.getAttribute("colecaoUnidadeNegocioTestemunhaRemovidas");
		
		// Consulta as Unidades de Neg�cio Testemunha
		if (httpServletRequest.getParameter("consultar") != null && !httpServletRequest.getParameter("consultar").equals("")) {
			
			colecaoUnidadeNegocioTestemunhaAdicionadas = new ArrayList<UnidadeNegocioTestemunha>();
			colecaoUnidadeNegocioTestemunhaRemovidas = new ArrayList<UnidadeNegocioTestemunha>();
			
			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaAdicionadas", colecaoUnidadeNegocioTestemunhaAdicionadas);
			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaRemovidas", colecaoUnidadeNegocioTestemunhaRemovidas);
			
			colecaoUnidadeNegocioTestemunha = consultarUnidadeNegocioTestemunha(idUnidadeNegocio);
			
			if (colecaoUnidadeNegocioTestemunha == null) {
				colecaoUnidadeNegocioTestemunha = new ArrayList<UnidadeNegocioTestemunha>();
			}
			
			sessao.setAttribute("colecaoUnidadeNegocioTestemunha", colecaoUnidadeNegocioTestemunha);
		}
		
		// Adiciona a Unidade de Neg�cio Testemunha
		if (httpServletRequest.getParameter("adicionar") != null && !httpServletRequest.getParameter("adicionar").equals("")) {
			
			UnidadeNegocioTestemunha unidadeNegocioTestemunha = criarUnidadeNegocioTestemunha(idUnidadeNegocio, loginTestemunha);
			
			colecaoUnidadeNegocioTestemunhaAdicionadas = adicionarUnidadeNegocioTestemunha(colecaoUnidadeNegocioTestemunha, colecaoUnidadeNegocioTestemunhaAdicionadas, unidadeNegocioTestemunha);
			
			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaAdicionadas",
					colecaoUnidadeNegocioTestemunhaAdicionadas);
			
		}
		
		// Remove a Unidade de Neg�cio Testemunha
		if (httpServletRequest.getParameter("removerUnidadeNegocioTestemunha") != null && !httpServletRequest.getParameter("removerUnidadeNegocioTestemunha").equals("")) {
			
			Integer posicaoRemocao = new Integer(httpServletRequest
					.getParameter("removerUnidadeNegocioTestemunha"));

			removerUnidadeNegocioTestemunha(colecaoUnidadeNegocioTestemunha,
					posicaoRemocao, colecaoUnidadeNegocioTestemunhaRemovidas,
					colecaoUnidadeNegocioTestemunhaAdicionadas);

			sessao.setAttribute("colecaoUnidadeNegocioTestemunhaRemovidas",
					colecaoUnidadeNegocioTestemunhaRemovidas);
			
		}

		return retorno;

	}

	/**
	 * Adiciona a Unidade de Neg�cio Testemunha selecionada pelo usu�rio 
	 *
	 * @author Rafael Corr�a
	 * @date 19/05/2008
	 *
	 * @param colecaoUnidadeNegocioTestemunha
	 * @param colecaoUnidadeNegocioTestemunhaAdicionadas
	 * @param unidadeNegocioTestemunha
	 * @return
	 */
	private Collection<UnidadeNegocioTestemunha> adicionarUnidadeNegocioTestemunha(
			Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha,
			Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaAdicionadas,
			UnidadeNegocioTestemunha unidadeNegocioTestemunhaAdicionar) {
		if (colecaoUnidadeNegocioTestemunhaAdicionadas == null) {
			colecaoUnidadeNegocioTestemunhaAdicionadas = new ArrayList<UnidadeNegocioTestemunha>();
		} else {
			// [FS0001] - Validar data rela��o fim
			for (UnidadeNegocioTestemunha unidadeNegocioTestemunha : colecaoUnidadeNegocioTestemunha) {
				if (unidadeNegocioTestemunha.getDataFimRelacao() == null
						&& unidadeNegocioTestemunha.getUsuario().getId()
								.equals(
										unidadeNegocioTestemunhaAdicionar
												.getUsuario().getId())) {
					throw new ActionServletException(
							"atencao.testemunha.ja.existente",
							unidadeNegocioTestemunhaAdicionar
									.getUnidadeNegocio().getNome(),
							unidadeNegocioTestemunhaAdicionar.getUsuario()
									.getNomeUsuario());
				}
			}
		}
		
		colecaoUnidadeNegocioTestemunhaAdicionadas.add(unidadeNegocioTestemunhaAdicionar);
		colecaoUnidadeNegocioTestemunha.add(unidadeNegocioTestemunhaAdicionar);
		
		return colecaoUnidadeNegocioTestemunhaAdicionadas;
	}

	/**
	 * Executa a consulta feita pelo usu�rio
	 *
	 * @author Rafa
	 * @date 16/05/2008
	 *
	 * @param idUnidadeNegocio
	 * @param idTestemunha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<UnidadeNegocioTestemunha> consultarUnidadeNegocioTestemunha(String idUnidadeNegocio) {
		Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha;
		FiltroUnidadeNegocioTestemunha filtroUnidadeNegocioTestemunha = new FiltroUnidadeNegocioTestemunha();
		filtroUnidadeNegocioTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocioTestemunha.UNIDADE_NEGOCIO);
		filtroUnidadeNegocioTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeNegocioTestemunha.USUARIO);
		
		filtroUnidadeNegocioTestemunha.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocioTestemunha.UNIDADE_NEGOCIO_ID, idUnidadeNegocio));
		
		colecaoUnidadeNegocioTestemunha = Fachada.getInstancia().pesquisar(filtroUnidadeNegocioTestemunha, UnidadeNegocioTestemunha.class.getName());
		
		return colecaoUnidadeNegocioTestemunha;
	}
	
	/**
	 * Remove a Unidade de Neg�cio Testemunha  
	 *
	 * @author Rafael Corr�a
	 * @date 16/05/2008
	 *
	 * @param colecaoUnidadeNegocioTestemunha
	 * @param posicao
	 */
	private void removerUnidadeNegocioTestemunha(Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunha, int posicaoRemocao, Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaRemovidas, Collection<UnidadeNegocioTestemunha> colecaoUnidadeNegocioTestemunhaAdicionadas) {
		int i = 0;
		
		for (UnidadeNegocioTestemunha unidadeNegocioTestemunha : colecaoUnidadeNegocioTestemunha) {
			i++;
			
			// Verifica se � este o objeto a ser removido
			if (i == posicaoRemocao) {
				
				if (unidadeNegocioTestemunha.getId() == null) {
					colecaoUnidadeNegocioTestemunha.remove(unidadeNegocioTestemunha);
					colecaoUnidadeNegocioTestemunhaAdicionadas.remove(unidadeNegocioTestemunha);
				} else {
					unidadeNegocioTestemunha.setDataFimRelacao(new Date());
					
					if (colecaoUnidadeNegocioTestemunhaRemovidas == null) {
						colecaoUnidadeNegocioTestemunhaRemovidas = new ArrayList<UnidadeNegocioTestemunha>();
					}
					
					colecaoUnidadeNegocioTestemunhaRemovidas.add(unidadeNegocioTestemunha);
				}
				
				break;
			}
		}
			
	}
	
	/**
	 * Cria a Unidade de Neg�cio Testemunha  
	 *
	 * @author Rafael Corr�a
	 * @date 16/05/2008
	 *
	 * @param idUnidadeNegocio
	 * @param idUsuario
	 */
	private UnidadeNegocioTestemunha criarUnidadeNegocioTestemunha(String idUnidadeNegocio, String loginUsuario) {
		
		Fachada fachada = Fachada.getInstancia();
		
		UnidadeNegocioTestemunha retorno = new UnidadeNegocioTestemunha();
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));
		
		Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
		
		retorno.setUnidadeNegocio(unidadeNegocio);
		
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
		
		Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			retorno.setUsuario(usuario);
			retorno.setDataInicioRelacao(new Date());
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Testemunha");
		}
		
		return retorno;
		
	}

}
