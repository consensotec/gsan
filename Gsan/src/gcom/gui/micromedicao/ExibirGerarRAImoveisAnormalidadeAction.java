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
package gcom.gui.micromedicao;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
public class ExibirGerarRAImoveisAnormalidadeAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
 		ActionForward retorno = actionMapping
				.findForward("exibirGerarRAImoveisAnormalidade");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		GerarRAImoveisAnormalidadeActionForm gerarRAImoveisAnormalidadeActionForm = (GerarRAImoveisAnormalidadeActionForm) actionForm;

		if (httpServletRequest.getParameter("reload") != null
				&& !httpServletRequest.getParameter("reload").trim().equals("")) {
			httpServletRequest.setAttribute("reload", "sim");
		} else {

			if (httpServletRequest.getParameter("limparForm") != null
					&& !httpServletRequest.getParameter("limparForm").trim()
							.equals("")) {
				gerarRAImoveisAnormalidadeActionForm.setSolicitacaoTipo(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO);
				gerarRAImoveisAnormalidadeActionForm
						.setSolicitacaoTipoEspecificacao(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO);
				
				// Recupera os im�veis selecionados pelo usu�rio
				Collection<Imovel> colecaoImoveisGerarOS = null;
				HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) 
					sessao.getAttribute("colecaoObservacaoOS");

				Collection colecaoIdsImovel = (Collection) sessao
						.getAttribute("colecaoIdsImovelTotal");
				int index = (Integer) sessao.getAttribute("index");

				Imovel imovelAtual = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
						.get(index)).getImovel();

				// Verifica se o im�vel o im�vel atual foi selecionado, em caso
				// afirmativo adiciona-o a cole��o
				if (httpServletRequest.getParameter("gerarOS") != null
						&& !httpServletRequest.getParameter("gerarOS").trim()
								.equals("")) {

					if (colecaoImoveisGerarOS == null) {
						colecaoImoveisGerarOS = new ArrayList();
						colecaoObservacaoOS = new HashMap<Integer, String>();
					}

					if (!colecaoImoveisGerarOS.contains(imovelAtual)) {
						colecaoImoveisGerarOS.add(imovelAtual);
						if(httpServletRequest.getParameter("observacao") != null && !httpServletRequest.getParameter("observacao").equals("")){
							String observacao = (String) httpServletRequest.getParameter("observacao");
							colecaoObservacaoOS.put(imovelAtual.getId(), observacao);
						}
					}
				} else {
					if (colecaoImoveisGerarOS != null
							&& colecaoImoveisGerarOS.contains(imovelAtual)) {
						colecaoImoveisGerarOS.remove(imovelAtual);
						colecaoObservacaoOS.remove(imovelAtual.getId());
					}
				}
				
				sessao.setAttribute("colecaoImoveisGerarOS", colecaoImoveisGerarOS);
				sessao.setAttribute("colecaoObservacaoOS", colecaoObservacaoOS);
			}

			/*
			 * Tipo de Solicita��o - Carregando a cole��o que ir� ficar
			 * dispon�vel para escolha do usu�rio
			 * 
			 * [FS0003] - Verificar exist�ncia de dados
			 */
			Collection colecaoSolicitacaoTipo = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipo");

			if (colecaoSolicitacaoTipo == null) {

				FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo(
						FiltroSolicitacaoTipo.DESCRICAO);

				filtroSolicitacaoTipo.setConsultaSemLimites(true);

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
						FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA,
						SolicitacaoTipo.INDICADOR_USO_SISTEMA_SIM));

				colecaoSolicitacaoTipo = fachada.pesquisar(
						filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());

				if (colecaoSolicitacaoTipo == null
						|| colecaoSolicitacaoTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"SOLICITACAO_TIPO");
				} else {
					sessao.setAttribute("colecaoSolicitacaoTipo",
							colecaoSolicitacaoTipo);
				}
			}

			/*
			 * Especifica��o - Carregando a cole��o que ir� ficar dispon�vel
			 * para escolha do usu�rio
			 * 
			 * [FS0003] - Verificar exist�ncia de dados
			 */
			String idSolicitacaoTipo = gerarRAImoveisAnormalidadeActionForm
					.getSolicitacaoTipo();

			if (idSolicitacaoTipo != null
					&& !idSolicitacaoTipo.equals(""
							+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(
						FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID,
								new Integer(idSolicitacaoTipo)));

				filtroSolicitacaoTipoEspecificacao.setConsultaSemLimites(true);

				filtroSolicitacaoTipoEspecificacao
						.adicionarParametro(new ParametroSimples(
								FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroNaoNulo(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO_ID));

				Collection colecaoSolicitacaoTipoEspecificacao = fachada
						.pesquisar(filtroSolicitacaoTipoEspecificacao,
								SolicitacaoTipoEspecificacao.class.getName());

				if (colecaoSolicitacaoTipoEspecificacao == null
						|| colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
					sessao
							.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"SOLICITACAO_TIPO_ESPECIFICACAO");
				} else {
					sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
							colecaoSolicitacaoTipoEspecificacao);
				}
			} else {
				sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
			}

		}

		return retorno;

	}

}
