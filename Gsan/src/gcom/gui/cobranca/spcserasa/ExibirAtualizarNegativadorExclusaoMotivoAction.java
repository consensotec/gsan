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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroCobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Action de Exibir Atualizar Negativador Exclusao Motivo
 * 
 * @author Yara Taciane
 * @created 04/01/2008
 */

public class ExibirAtualizarNegativadorExclusaoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("atualizarNegativadorExclusaoMotivo");
		AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm = (AtualizarNegativadorExclusaoMotivoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// volta da msg de Negativador Exclusao Motivo j� utilizado, n�o pode ser
		// alterado nem exclu�do.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorExclusaoMotivo = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do Negativador Exclusao Motivo que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idNegativadorExclusaoMotivo = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do bot�o Voltar p Filtrar Negativador Exclusao Motivo			
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorExclusaoMotivo);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idNegativadorExclusaoMotivo = (String) sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do bot�o Voltar p Filtrar Negativador Exclusao Motivo				
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idNegativadorExclusaoMotivo = httpServletRequest.getParameter("idRegistroAtualizacao");
				// Definindo a volta do bot�o Voltar p Manter Negativador Exclusao Motivo		
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorExclusaoMotivo);
			}
		} else {
			idNegativadorExclusaoMotivo = (String) sessao.getAttribute("idRegistroAtualizacao");
		}


		
		Collection colecaoCobrancaDebitoSituacao = (Collection) sessao.getAttribute("colecaoCobrancaDebitoSituacao");

		if (colecaoCobrancaDebitoSituacao == null) {

			FiltroCobrancaDebitoSituacao filtroCobrancaDebitoSituacao = new FiltroCobrancaDebitoSituacao();	
			
			filtroCobrancaDebitoSituacao.setConsultaSemLimites(true);

			colecaoCobrancaDebitoSituacao = fachada.pesquisar(filtroCobrancaDebitoSituacao,
					CobrancaDebitoSituacao.class.getName());

			if (colecaoCobrancaDebitoSituacao == null || colecaoCobrancaDebitoSituacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"COBRANCA DEBITO SITUACAO");
			} else {
				sessao.setAttribute("colecaoCobrancaDebitoSituacao", colecaoCobrancaDebitoSituacao);
			}
		}
		
			

		// Verifica se o usu�rio est� selecionando o Negativador Exclusao Motivo da
		// p�gina de manter
		// Caso contr�rio o usu�rio est� teclando enter na p�gina de atualizar
		if ((idNegativadorExclusaoMotivo != null && !idNegativadorExclusaoMotivo.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirNegativadorExclusaoMotivo(idNegativadorExclusaoMotivo,
					atualizarNegativadorExclusaoMotivoActionForm, fachada, sessao,
					httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			exibirNegativadorExclusaoMotivo(idNegativadorExclusaoMotivo,
					atualizarNegativadorExclusaoMotivoActionForm, fachada, sessao,
					httpServletRequest);

		}

		return retorno;

	}

	private void exibirNegativadorExclusaoMotivo(
			String idNegativadorExclusaoMotivo,
			AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// Cria a vari�vel que vai armazenar o ParcelamentoPerfil para ser
		// atualizado
		NegativadorExclusaoMotivo negativadorExclusaoMotivo = null;

		// Cria o filtro de NegativadorExclusaoMotivo e seta o id do
		// NegativadorExclusaoMotivo para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
				FiltroNegativadorExclusaoMotivo.ID, idNegativadorExclusaoMotivo));

		filtroNegativadorExclusaoMotivo
				.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorExclusaoMotivo> collectionNegativadorExclusaoMotivo = fachada
				.pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class
						.getName());

		// Caso a pesquisa tenha retornado o NegativadorExclusaoMotivo
		if (collectionNegativadorExclusaoMotivo != null
				&& !collectionNegativadorExclusaoMotivo.isEmpty()) {

			// Recupera da cole��o o NegativadorExclusaoMotivo que vai ser atualizado
			negativadorExclusaoMotivo = (NegativadorExclusaoMotivo) Util
					.retonarObjetoDeColecao(collectionNegativadorExclusaoMotivo);
	
			// Seta no form os dados de NegativadorExclusaoMotivo
			if (negativadorExclusaoMotivo.getNegativador() != null
					&& !negativadorExclusaoMotivo.getNegativador().equals("")) {

				atualizarNegativadorExclusaoMotivoActionForm.setIdNegativador(""
						+ negativadorExclusaoMotivo.getNegativador());

				atualizarNegativadorExclusaoMotivoActionForm.setNegativadorCliente(""
						+ negativadorExclusaoMotivo.getNegativador().getCliente()
								.getNome());
			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setNegativadorCliente("");
			}
			
			if (negativadorExclusaoMotivo.getCodigoExclusaoMotivo()!= 0) {

				atualizarNegativadorExclusaoMotivoActionForm.setCodigoMotivo(""
						+ negativadorExclusaoMotivo.getCodigoExclusaoMotivo());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setCodigoMotivo("");
			}
			
			if (negativadorExclusaoMotivo.getDescricaoExclusaoMotivo() != null
					&& !negativadorExclusaoMotivo.getDescricaoExclusaoMotivo().equals("")) {

				atualizarNegativadorExclusaoMotivoActionForm.setDescricaoExclusaoMotivo(""
						+ negativadorExclusaoMotivo.getDescricaoExclusaoMotivo());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setDescricaoExclusaoMotivo("");
			}
			
			
			if (negativadorExclusaoMotivo.getIndicadorUso() != 0) {

				atualizarNegativadorExclusaoMotivoActionForm.setIndicadorUso(""
						+ negativadorExclusaoMotivo.getIndicadorUso());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setIndicadorUso("");
			}
			
			

			if (negativadorExclusaoMotivo.getCobrancaDebitoSituacao() != null
					&& !negativadorExclusaoMotivo.getCobrancaDebitoSituacao().equals("")) {

				atualizarNegativadorExclusaoMotivoActionForm.setIdCobrancaDebitoSituacao(""
						+ negativadorExclusaoMotivo.getCobrancaDebitoSituacao().getId());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setIdCobrancaDebitoSituacao("-1");
			}
			
					
			atualizarNegativadorExclusaoMotivoActionForm.setTime(Long
					.toString(negativadorExclusaoMotivo.getUltimaAlteracao()
							.getTime()));

			sessao.setAttribute("negativadorExclusaoMotivo", negativadorExclusaoMotivo);

		}

	}

}