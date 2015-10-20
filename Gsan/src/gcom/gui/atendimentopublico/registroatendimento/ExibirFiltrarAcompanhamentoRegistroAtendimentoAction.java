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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1055] Filtrar Acompanhamento dos Registros de Atendimento
 * 
 * Classe que ir� auxiliar no formato de entrada da pesquisa 
 * do relatorio de Acompanhamento dos Registros de Atendimento.
 * 
 * @author Hugo Leonardo
 * @date 28/09/2010
 */
public class ExibirFiltrarAcompanhamentoRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.
			findForward("filtrarAcompanhamentoRegistroAtendimento");
		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		FiltrarAcompanhamentoRegistroAtendimentoActionForm form = 
			(FiltrarAcompanhamentoRegistroAtendimentoActionForm) actionForm;
		
		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.setSituacaoRAAbertos("2");
			form.setOpcaoRelatorio("0");
			
			Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
			
			if(Util.verificarNaoVazio(usuarioLogado.getUnidadeOrganizacional().toString()) ){
				
				UnidadeOrganizacional unidadeOrganizacional = usuarioLogado.getUnidadeOrganizacional();
			
				form.setUnidadeAtendimentoId("" + unidadeOrganizacional.getId());
				form.setUnidadeAtendimentoDescricao( unidadeOrganizacional.getDescricao());
				
			}
		}
		
		if (form.getUnidadeAtendimentoId() != null 
				&& !form.getUnidadeAtendimentoId().trim().equals("")) {
			
			this.pesquisarUnidadeAtendimento( httpServletRequest, form);
		}
		
		if(sessao.getAttribute("colecaoAtendimentoMotivoEncerramento") == null ){
			
			this.pesquisarMotivoEncerramento(sessao);
		}
		
		this.pesquisarMunicipioAssociado(httpServletRequest);
		
		return retorno;
	}
	
	private void pesquisarMotivoEncerramento(HttpSession sessao){
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
			new FiltroAtendimentoMotivoEncerramento();
		
		filtroAtendimentoMotivoEncerramento.adicionarParametro(
				new ParametroSimples(
						FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(
				FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		
		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
			Fachada.getInstancia().pesquisar( filtroAtendimentoMotivoEncerramento, 
					AtendimentoMotivoEncerramento.class.getName());
		
		if (colecaoAtendimentoMotivoEncerramento == null || colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo Encerramento");
		}else{
			
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		}
	}
	
	/**
	 * Pesquisa Munic�pios associados � localidade
	 *
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 */
	private void pesquisarMunicipioAssociado(HttpServletRequest httpServletRequest){
		Collection colecaoMunicipioAssociado = this.getFachada().pesquisarMunicipiosAssociadoLocalidade();
		if (colecaoMunicipioAssociado == null || colecaoMunicipioAssociado.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Munic�pios associados a localidade");
		} else {
			httpServletRequest.setAttribute("colecaoMunicipioAssociado",colecaoMunicipioAssociado);
		}
	}	
	
	private void pesquisarUnidadeAtendimento(HttpServletRequest httpServletRequest, 
				FiltrarAcompanhamentoRegistroAtendimentoActionForm form) {

		// Pesquisa a unidade organizacional na base
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getUnidadeAtendimentoId()));

		Collection<UnidadeOrganizacional> collUnidadeOrganizacional = Fachada.getInstancia().pesquisar(
				filtro, UnidadeOrganizacional.class.getName());

		// Se nenhum usu�rio for encontrado a mensagem � enviada para a p�gina
		if (collUnidadeOrganizacional != null && !collUnidadeOrganizacional.isEmpty()) {
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) 
				Util.retonarObjetoDeColecao(collUnidadeOrganizacional);
			
			form.setUnidadeAtendimentoId("" + unidadeOrganizacional.getId());
			form.setUnidadeAtendimentoDescricao( unidadeOrganizacional.getDescricao());

		} else {
			form.setUnidadeAtendimentoId("");
			form.setUnidadeAtendimentoDescricao("UNIDADE INEXISTENTE");
			httpServletRequest.setAttribute("unidadeInexistente",true);
		}
	}
}
