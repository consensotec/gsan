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

import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FiltroUnidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.UnidadeIniciada;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.tarefa.TarefaBatch;
import gcom.util.ConstantesSistema;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de totaliza��o de unidade de
 * processamento por funcionalidade iniciada
 * 
 * @author Rodrigo Silveira
 * @created 31/07/2006
 */
public class ExibirConsultarUnidadeProcessamentoProcessoInseridoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarDadosUnidadeProcessamentoProcessoIniciado");

		Fachada fachada = Fachada.getInstancia();

		Integer idFuncionalidadeIniciada = Integer.parseInt(httpServletRequest
				.getParameter("idFuncionalidadeIniciada"));

		FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
		filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroFuncionalidadeIniciada.ID, idFuncionalidadeIniciada));
		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.funcionalidade");
		filtroFuncionalidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("processoFuncionalidade.unidadeProcessamento");

		FuncionalidadeIniciada funcionalidadeIniciada = (FuncionalidadeIniciada) Util
				.retonarObjetoDeColecao(fachada.pesquisar(
						filtroFuncionalidadeIniciada,
						FuncionalidadeIniciada.class.getName()));

		httpServletRequest
				.setAttribute("descricao", funcionalidadeIniciada
						.getProcessoFuncionalidade().getFuncionalidade()
						.getDescricao());

		FiltroUnidadeIniciada filtroUnidadeIniciada = new FiltroUnidadeIniciada();

		filtroUnidadeIniciada.adicionarParametro(new ParametroSimples(
				FiltroUnidadeIniciada.ID_FUNCIONALIDADE_INICIADA,
				idFuncionalidadeIniciada));

		filtroUnidadeIniciada.adicionarParametro(new ParametroNaoNulo(
				FiltroUnidadeIniciada.DATA_HORA_TERMINO));

		filtroUnidadeIniciada
				.adicionarCaminhoParaCarregamentoEntidade("unidadeProcessamento");

		Collection<UnidadeIniciada> colecaoUnidadeIniciadaFinalizadas = fachada
				.pesquisar(filtroUnidadeIniciada, UnidadeIniciada.class
						.getName());

		Collection<Integer> colecaoIdUnidadeFinalizadas = new ArrayList<Integer>();

		for (UnidadeIniciada unidadeIniciada : colecaoUnidadeIniciadaFinalizadas) {
			colecaoIdUnidadeFinalizadas.add(unidadeIniciada
					.getCodigoRealUnidadeProcessamento());

		}

		Collection<Integer> colecaoIdUnidades = new ArrayList<Integer>();

		try {
			Object tarefa = IoUtil
					.transformarBytesParaObjeto(funcionalidadeIniciada
							.getTarefaBatch());

			if (tarefa instanceof TarefaBatch) {

				TarefaBatch tarefaBatch = (TarefaBatch) tarefa;

				Collection unidadesProcessamento = (Collection) tarefaBatch
						.getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

				if (unidadesProcessamento != null
						&& !unidadesProcessamento.isEmpty()) {

					Iterator iterator = unidadesProcessamento.iterator();

					while (iterator.hasNext()) {
						Object objetoUnidadeProcessamento = iterator.next();
						if (objetoUnidadeProcessamento instanceof Integer) {

							Integer id = (Integer) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(id);

						} else if (objetoUnidadeProcessamento instanceof Object[]) {
							if (((Object[]) objetoUnidadeProcessamento)[1] instanceof Rota) {
								Rota rota = (Rota) ((Object[]) objetoUnidadeProcessamento)[1];
								colecaoIdUnidades.add(rota.getId());
							} else {
								if (((Object[]) objetoUnidadeProcessamento)[0] instanceof Integer) {
									Integer parametroInteger = (Integer) ((Object[]) objetoUnidadeProcessamento)[0];
									colecaoIdUnidades.add(parametroInteger);
								}
							}
						} else if (objetoUnidadeProcessamento instanceof Rota) {
							Rota rota = (Rota) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(rota.getId());
						} else if (objetoUnidadeProcessamento instanceof SetorComercial) {
							SetorComercial setorComercial = (SetorComercial) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(setorComercial.getId());
						} else if (objetoUnidadeProcessamento instanceof Localidade) {
							Localidade localidade = (Localidade) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(localidade.getId());
						} else if (objetoUnidadeProcessamento instanceof UnidadeNegocio) {
							UnidadeNegocio unidadeNegocio = (UnidadeNegocio) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(unidadeNegocio.getId());
						}else if (objetoUnidadeProcessamento instanceof Empresa) {
							Empresa empresa = (Empresa) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(empresa.getId());
						}else if (objetoUnidadeProcessamento instanceof Quadra) {
							Quadra quadra = (Quadra) objetoUnidadeProcessamento;
							colecaoIdUnidades.add(quadra.getId());
						}					}

					colecaoIdUnidades.removeAll(colecaoIdUnidadeFinalizadas);
				}

			
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		// filtroUnidadeIniciada
		// .adicionarCaminhoParaCarregamentoEntidade("unidadeProcessamento");

		/*
		 * Collection<UnidadeIniciada> colecaoUnidadeIniciadaInacabadas =
		 * fachada .pesquisar(filtroUnidadeIniciada, UnidadeIniciada.class
		 * .getName());
		 */
		if ((colecaoUnidadeIniciadaFinalizadas == null || colecaoUnidadeIniciadaFinalizadas
				.isEmpty())
				&& (colecaoIdUnidades == null || colecaoIdUnidades.isEmpty())) {
			throw new ActionServletException(
					"atencao.processo_iniciado.unidade_iniciada.inexistente");

		} else {

			if (colecaoUnidadeIniciadaFinalizadas != null
					&& !colecaoUnidadeIniciadaFinalizadas.isEmpty()) {
				httpServletRequest
						.setAttribute(
								"unidadeProcessamento",
								((UnidadeIniciada) Util
										.retonarObjetoDeColecao(colecaoUnidadeIniciadaFinalizadas))
										.getUnidadeProcessamento()
										.getDescricaoUnidadeProcessamento());
			}
		}

		httpServletRequest.setAttribute("tamanhoColecaoFinalizada",
				colecaoUnidadeIniciadaFinalizadas.size());

		httpServletRequest.setAttribute("tamanhoColecaoInacabada",
				colecaoIdUnidades.size());

		httpServletRequest.setAttribute("colecaoFinalizada",
				colecaoUnidadeIniciadaFinalizadas);

		httpServletRequest.setAttribute("colecaoInacabada", colecaoIdUnidades);

		return retorno;
	}

}
