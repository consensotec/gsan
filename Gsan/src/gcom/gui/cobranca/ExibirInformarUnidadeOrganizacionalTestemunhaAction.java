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
package gcom.gui.cobranca;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.FiltroUnidadeOrganizacionalTestemunha;
import gcom.cobranca.UnidadeOrganizacionalTestemunha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
public class ExibirInformarUnidadeOrganizacionalTestemunhaAction extends GcomAction {

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
				.findForward("exibirInformarUnidadeOrganizacionalTestemunha");
		
		InformarUnidadeOrganizacionalTestemunhaActionForm informarUnidadeOrganizacionalTestemunhaActionForm = (InformarUnidadeOrganizacionalTestemunhaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idUnidadeOrganizacional = informarUnidadeOrganizacionalTestemunhaActionForm.getIdUnidadeOrganizacional();
		String idTestemunha = informarUnidadeOrganizacionalTestemunhaActionForm.getIdTestemunha();
		String loginTestemunha = informarUnidadeOrganizacionalTestemunhaActionForm.getLoginTestemunha();
		
		// Pesquisa a unidade organizacional
		if (idUnidadeOrganizacional != null && !idUnidadeOrganizacional.trim().equals("")) {
			
			// Verifica se deve desabilitar o bot�o adicionar e a testemunha
			if (httpServletRequest.getParameter("desabilitaCampos") != null
					&& !httpServletRequest.getParameter("desabilitaCampos")
							.trim().equals("")) {
				httpServletRequest.setAttribute("desabilitaCampos", "sim");
			}
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
			
			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeUnidadeOrganizacional(unidadeOrganizacional.getDescricao());
			} else {
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdUnidadeOrganizacional("");
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeUnidadeOrganizacional("UNIDADE INEXISTENTE");
				
				httpServletRequest.setAttribute("unidadeOrganizacionalInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeOrganizacional");
			}
			
		} else {
			informarUnidadeOrganizacionalTestemunhaActionForm.setNomeUnidadeOrganizacional("");
		}
		
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
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
			
			// Verifica se a pesquisa foi pelo enter
			if (loginTestemunha != null && !loginTestemunha.trim().equals("")) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginTestemunha));
			} 
			// Verifica se a pesquisa foi feita pela lupa
			else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idTestemunha));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdTestemunha(usuario.getId().toString());
				informarUnidadeOrganizacionalTestemunhaActionForm.setLoginTestemunha(usuario.getLogin());
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeTestemunha(usuario.getNomeUsuario());
			} else {
				informarUnidadeOrganizacionalTestemunhaActionForm.setIdTestemunha("");
				informarUnidadeOrganizacionalTestemunhaActionForm.setLoginTestemunha("");
				informarUnidadeOrganizacionalTestemunhaActionForm.setNomeTestemunha("TESTEMUNHA INEXISTENTE");
				
				httpServletRequest.setAttribute("testemunhaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "loginUsuario");
			}
			
		} else {
			informarUnidadeOrganizacionalTestemunhaActionForm.setNomeTestemunha("");
		}
		
		// Cole��o retornada na consulta
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha = (Collection<UnidadeOrganizacionalTestemunha>) sessao.getAttribute("colecaoUnidadeOrganizacionalTestemunha");
		
		// Cole��o com os objetos adicionados pelo usu�rio
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas = (Collection<UnidadeOrganizacionalTestemunha>) sessao.getAttribute("colecaoUnidadeOrganizacionalTestemunhaAdicionadas");
		
		// Cole��o com os objetos removidos pelo usu�rio
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaRemovidas = (Collection<UnidadeOrganizacionalTestemunha>) sessao
		.getAttribute("colecaoUnidadeOrganizacionalTestemunhaRemovidas");
		
		// Consulta as Unidades de Neg�cio Testemunha
		if (httpServletRequest.getParameter("consultar") != null && !httpServletRequest.getParameter("consultar").equals("")) {
			
			colecaoUnidadeOrganizacionalTestemunhaAdicionadas = new ArrayList<UnidadeOrganizacionalTestemunha>();
			colecaoUnidadeOrganizacionalTestemunhaRemovidas = new ArrayList<UnidadeOrganizacionalTestemunha>();
			
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaAdicionadas", colecaoUnidadeOrganizacionalTestemunhaAdicionadas);
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaRemovidas", colecaoUnidadeOrganizacionalTestemunhaRemovidas);
			
			colecaoUnidadeOrganizacionalTestemunha = consultarUnidadeOrganizacionalTestemunha(idUnidadeOrganizacional);
			
			if (colecaoUnidadeOrganizacionalTestemunha == null) {
				colecaoUnidadeOrganizacionalTestemunha = new ArrayList<UnidadeOrganizacionalTestemunha>();
			}
			
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunha", colecaoUnidadeOrganizacionalTestemunha);
		}
		
		// Adiciona a Unidade de Neg�cio Testemunha
		if (httpServletRequest.getParameter("adicionar") != null && !httpServletRequest.getParameter("adicionar").equals("")) {
			
			UnidadeOrganizacionalTestemunha unidadeNegocioTestemunha = criarUnidadeOrganizacionalTestemunha(idUnidadeOrganizacional, loginTestemunha);
			
			colecaoUnidadeOrganizacionalTestemunhaAdicionadas = adicionarUnidadeOrganizacionalTestemunha(colecaoUnidadeOrganizacionalTestemunha, colecaoUnidadeOrganizacionalTestemunhaAdicionadas, unidadeNegocioTestemunha);
			
			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaAdicionadas",
					colecaoUnidadeOrganizacionalTestemunhaAdicionadas);
			
		}
		
		// Remove a Unidade de Neg�cio Testemunha
		if (httpServletRequest.getParameter("removerUnidadeOrganizacionalTestemunha") != null && !httpServletRequest.getParameter("removerUnidadeOrganizacionalTestemunha").equals("")) {
			
			Integer posicaoRemocao = new Integer(httpServletRequest
					.getParameter("removerUnidadeOrganizacionalTestemunha"));

			removerUnidadeOrganizacionalTestemunha(colecaoUnidadeOrganizacionalTestemunha,
					posicaoRemocao, colecaoUnidadeOrganizacionalTestemunhaRemovidas,
					colecaoUnidadeOrganizacionalTestemunhaAdicionadas);

			sessao.setAttribute("colecaoUnidadeOrganizacionalTestemunhaRemovidas",
					colecaoUnidadeOrganizacionalTestemunhaRemovidas);
			
		}

		return retorno;

	}

	/**
	 * Adiciona a Unidade de Neg�cio Testemunha selecionada pelo usu�rio 
	 *
	 * @author Rafael Corr�a
	 * @date 19/05/2008
	 *
	 * @param colecaoUnidadeOrganizacionalTestemunha
	 * @param colecaoUnidadeOrganizacionalTestemunhaAdicionadas
	 * @param unidadeNegocioTestemunha
	 * @return
	 */
	private Collection<UnidadeOrganizacionalTestemunha> adicionarUnidadeOrganizacionalTestemunha(
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha,
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas,
			UnidadeOrganizacionalTestemunha unidadeOrganizacionalTestemunhaAdicionar) {
		if (colecaoUnidadeOrganizacionalTestemunhaAdicionadas == null) {
			colecaoUnidadeOrganizacionalTestemunhaAdicionadas = new ArrayList<UnidadeOrganizacionalTestemunha>();
		} else {
			// [FS0001] - Validar data rela��o fim
			for (UnidadeOrganizacionalTestemunha unidadeNegocioTestemunha : colecaoUnidadeOrganizacionalTestemunha) {
				if (unidadeNegocioTestemunha.getDataFimRelacao() == null
						&& unidadeNegocioTestemunha.getUsuario().getId()
								.equals(
										unidadeOrganizacionalTestemunhaAdicionar
												.getUsuario().getId())) {
					throw new ActionServletException(
							"atencao.testemunha.ja.existente",
							unidadeOrganizacionalTestemunhaAdicionar
									.getUnidadeOrganizacional().getDescricao(),
							unidadeOrganizacionalTestemunhaAdicionar.getUsuario()
									.getNomeUsuario());
				}
			}
		}
		
		colecaoUnidadeOrganizacionalTestemunhaAdicionadas.add(unidadeOrganizacionalTestemunhaAdicionar);
		colecaoUnidadeOrganizacionalTestemunha.add(unidadeOrganizacionalTestemunhaAdicionar);
		
		return colecaoUnidadeOrganizacionalTestemunhaAdicionadas;
	}

	/**
	 * Executa a consulta feita pelo usu�rio
	 *
	 * @author Rafa
	 * @date 16/05/2008
	 *
	 * @param idUnidadeOrganizacional
	 * @param idTestemunha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<UnidadeOrganizacionalTestemunha> consultarUnidadeOrganizacionalTestemunha(String idUnidadeOrganizacional) {
		Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha;
		FiltroUnidadeOrganizacionalTestemunha filtroUnidadeOrganizacionalTestemunha = new FiltroUnidadeOrganizacionalTestemunha();
		filtroUnidadeOrganizacionalTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalTestemunha.UNIDADE_ORGANIZACIONAL);
		filtroUnidadeOrganizacionalTestemunha.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacionalTestemunha.USUARIO);
		
		filtroUnidadeOrganizacionalTestemunha.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacionalTestemunha.UNIDADE_ORGANIZACIONAL_ID, idUnidadeOrganizacional));
		
		colecaoUnidadeOrganizacionalTestemunha = Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacionalTestemunha, UnidadeOrganizacionalTestemunha.class.getName());
		
		return colecaoUnidadeOrganizacionalTestemunha;
	}
	
	/**
	 * Remove a Unidade de Neg�cio Testemunha  
	 *
	 * @author Rafael Corr�a
	 * @date 16/05/2008
	 *
	 * @param colecaoUnidadeOrganizacionalTestemunha
	 * @param posicao
	 */
	private void removerUnidadeOrganizacionalTestemunha(Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunha, int posicaoRemocao, Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaRemovidas, Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas) {
		int i = 0;
		
		for (UnidadeOrganizacionalTestemunha unidadeOrganizacionalTestemunha : colecaoUnidadeOrganizacionalTestemunha) {
			i++;
			
			// Verifica se � este o objeto a ser removido
			if (i == posicaoRemocao) {
				
				if (unidadeOrganizacionalTestemunha.getId() == null) {
					colecaoUnidadeOrganizacionalTestemunha.remove(unidadeOrganizacionalTestemunha);
					colecaoUnidadeOrganizacionalTestemunhaAdicionadas.remove(unidadeOrganizacionalTestemunha);
				} else {
					unidadeOrganizacionalTestemunha.setDataFimRelacao(new Date());
					
					if (colecaoUnidadeOrganizacionalTestemunhaRemovidas == null) {
						colecaoUnidadeOrganizacionalTestemunhaRemovidas = new ArrayList<UnidadeOrganizacionalTestemunha>();
					}
					
					colecaoUnidadeOrganizacionalTestemunhaRemovidas.add(unidadeOrganizacionalTestemunha);
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
	 * @param idUnidadeOrganizacional
	 * @param idUsuario
	 */
	private UnidadeOrganizacionalTestemunha criarUnidadeOrganizacionalTestemunha(String idUnidadeOrganizacional, String loginUsuario) {
		
		Fachada fachada = Fachada.getInstancia();
		
		UnidadeOrganizacionalTestemunha retorno = new UnidadeOrganizacionalTestemunha();
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidadeOrganizacional));
		
		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		retorno.setUnidadeOrganizacional(unidadeOrganizacional);
		
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
