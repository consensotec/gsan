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
package gcom.gui.batch;

import gcom.batch.FiltroProcesso;
import gcom.batch.FiltroProcessoFuncionalidade;
import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.Processo;
import gcom.batch.ProcessoFuncionalidade;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.batch.ProcessoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir processo
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class ExibirInserirProcessoMensalEventualAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirProcessoMensalEventual");

		// Pesquisa as situa��es do processos para o select da p�gina
		// this.pesquisarProcessoSituacao(httpServletRequest);

		// Pesquisa o Processo digitado e os ProcessoFuncionalidade relacionados
		this.pesquisarProcessoDigitado(httpServletRequest,
				(InserirProcessoMensalEventualActionForm) actionForm);

		// Pesquisa o Processo iniciado precedente digitado e os
		// ProcessoFuncionalidade relacionados
		this.pesquisarProcessoIniciadoPrecedenteDigitado(httpServletRequest,
				(InserirProcessoMensalEventualActionForm) actionForm);

		return retorno;
	}

	/**
	 * Pesquisa todas as Situa��es do Processo para popular o select da p�gina
	 * de inserir processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/07/2006
	 * 
	 * @param httpServletRequest
	 */
	// private void pesquisarProcessoSituacao(HttpServletRequest
	// httpServletRequest) {
	// FiltroProcessoSituacao filtroProcessoSituacao = new
	// FiltroProcessoSituacao();
	// Collection<ProcessoSituacao> colecao = Fachada.getInstancia()
	// .pesquisar(filtroProcessoSituacao,
	// ProcessoSituacao.class.getName());
	// httpServletRequest.setAttribute("colecaoProcessoSituacao", colecao);
	//
	// }
	/**
	 * Fun��o que pesquisa um Processo digitado e pesquisa todos os
	 * ProcessoFuncionalidade relacionados
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/07/2006
	 * 
	 * @param httpServletRequest
	 * @param actionForm
	 */
	private void pesquisarProcessoDigitado(
			HttpServletRequest httpServletRequest,
			InserirProcessoMensalEventualActionForm actionForm) {

		String idDigitadoEnterProcesso = (String) actionForm.getIdProcesso();

		actionForm.setDescricaoProcesso("");
		actionForm.setIdProcesso("");

		// Verifica se o c�digo foi digitado
		if (idDigitadoEnterProcesso != null
				&& !idDigitadoEnterProcesso.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterProcesso) > 0) {
			Fachada fachada = Fachada.getInstancia();

			// Este trecho pesquisa todos do processoFuncionalidade relacionados
			// com o processo digitado

			FiltroProcessoFuncionalidade filtroProcessoFuncionalidade = new FiltroProcessoFuncionalidade();

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.ID_PROCESSO,
							idDigitadoEnterProcesso));
			
			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));


			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.MENSAL, FiltroParametro.CONECTOR_OR, 4));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.EVENTUAL, FiltroParametro.CONECTOR_OR));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.DIARIO, FiltroParametro.CONECTOR_OR));

			filtroProcessoFuncionalidade
					.adicionarParametro(new ParametroSimples(
							FiltroProcessoFuncionalidade.PROCESSO_PROCESSO_TIPO_ID,
							ProcessoTipo.SEMANAL, FiltroParametro.CONECTOR_OR));

			filtroProcessoFuncionalidade
					.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
			filtroProcessoFuncionalidade
					.adicionarCaminhoParaCarregamentoEntidade("unidadeProcessamento");

			filtroProcessoFuncionalidade
					.setCampoOrderBy(FiltroProcessoFuncionalidade.SEQUENCIAL_EXECUCAO);

			Collection processosFuncionaliadade = fachada.pesquisar(
					filtroProcessoFuncionalidade, ProcessoFuncionalidade.class
							.getName());

			httpServletRequest.setAttribute("colecaoProcessoFuncionalidade",
					processosFuncionaliadade);

			// Este trecho pesquisa o processo digitado e altera o form para
			// refletir o resultado da busca na p�gina de inserir processo

			FiltroProcesso filtroProcesso = new FiltroProcesso();

			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.ID, idDigitadoEnterProcesso));

			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.MENSAL,
					FiltroParametro.CONECTOR_OR, 5));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.EVENTUAL,
					FiltroParametro.CONECTOR_OR));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.RELATORIO,
					FiltroParametro.CONECTOR_OR));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.SEMANAL,
					FiltroParametro.CONECTOR_OR));
			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.PROCESSO_TIPO, ProcessoTipo.DIARIO,
					FiltroParametro.CONECTOR_OR));

			Collection processos = fachada.pesquisar(filtroProcesso,
					Processo.class.getName());

			if (processos != null && !processos.isEmpty()) {
				// O processo foi encontrado
				actionForm.setIdProcesso(((Processo) ((List) processos).get(0))
						.getId().toString());
				actionForm.setDescricaoProcesso(((Processo) ((List) processos)
						.get(0)).getDescricaoProcesso());

			} else {
				actionForm.setIdProcesso("");
				httpServletRequest.setAttribute("idProcessoNaoEncontrado",
						"true");
				actionForm.setDescricaoProcesso("Processo inexistente");

			}
		}

	}

	/**
	 * Fun��o que pesquisa um Processo iniciado precedente digitado
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/07/2006
	 * 
	 * @param httpServletRequest
	 * @param actionForm
	 */
	private void pesquisarProcessoIniciadoPrecedenteDigitado(
			HttpServletRequest httpServletRequest,
			InserirProcessoMensalEventualActionForm actionForm) {

		String idDigitadoEnterProcesso = (String) actionForm
				.getIdProcessoIniciadoPrecedente();

		actionForm.setDescricaoProcessoIniciadoPrecedente("");
		actionForm.setIdProcessoIniciadoPrecedente("");

		// Verifica se o c�digo foi digitado
		if (idDigitadoEnterProcesso != null
				&& !idDigitadoEnterProcesso.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterProcesso) > 0
				&& !idDigitadoEnterProcesso.trim().equals(
						actionForm.getIdProcesso())) {
			Fachada fachada = Fachada.getInstancia();

			// Este trecho pesquisa o processo digitado e altera o form para
			// refletir o resultado da busca na p�gina de inserir processo

			FiltroProcessoIniciado filtroProcessoIniciado = new FiltroProcessoIniciado();

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.ID, idDigitadoEnterProcesso));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.AGENDADO, FiltroParametro.CONECTOR_OR));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.EM_ESPERA, FiltroParametro.CONECTOR_OR));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.EM_PROCESSAMENTO,
					FiltroParametro.CONECTOR_OR));

			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(
					FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,
					ProcessoSituacao.INICIO_A_COMANDAR));

			filtroProcessoIniciado
					.adicionarCaminhoParaCarregamentoEntidade("processo");

			Collection processos = fachada.pesquisar(filtroProcessoIniciado,
					ProcessoIniciado.class.getName());

			if (processos != null && !processos.isEmpty()) {
				// O processo foi encontrado
				actionForm
						.setIdProcessoIniciadoPrecedente(((ProcessoIniciado) ((List) processos)
								.get(0)).getId().toString());
				actionForm
						.setDescricaoProcessoIniciadoPrecedente(((ProcessoIniciado) ((List) processos)
								.get(0)).getProcesso().getDescricaoProcesso());

			} else {
				actionForm.setIdProcessoIniciadoPrecedente("");
				httpServletRequest.setAttribute(
						"idProcessoIniciadoPrecedenteNaoEncontrado", "true");
				actionForm
						.setDescricaoProcessoIniciadoPrecedente("Processo inexistente");

			}
		}

	}

}
