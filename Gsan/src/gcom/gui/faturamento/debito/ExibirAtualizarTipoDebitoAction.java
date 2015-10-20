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
package gcom.gui.faturamento.debito;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FiltroFinanciamentoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.FiltroLancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
/** * Descri��o da classe *  * @author R�mulo Aur�lio * @date 14/03/2007 */
public class ExibirAtualizarTipoDebitoAction extends GcomAction {
	/**	 * Este caso de uso permite alterar e remover um Tipo de D�bito	 * 	 * [UC0530] Manter Tipo de D�bito	 * 	 * 	 * @author R�mulo Aur�lio	 * @date 14/03/2007	 * 	 * @param actionMapping	 * @param actionForm	 * @param httpServletRequest	 * @param httpServletResponse	 * @return	 */
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping				.findForward("atualizarTipoDebito");
		AtualizarTipoDebitoActionForm form = (AtualizarTipoDebitoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		String id = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		/*		 * if (httpServletRequest.getParameter("idTipoDebito") != null &&		 * !httpServletRequest.getParameter("idTipoDebito").equals( "")) { }		 */
		// Carregando dados da tabela LancamentoItemContabil		FiltroLancamentoItemContabil filtroLancamentoItemContabil = new FiltroLancamentoItemContabil();
		filtroLancamentoItemContabil				.setCampoOrderBy(FiltroLancamentoItemContabil.DESCRICAO);
		filtroLancamentoItemContabil.adicionarParametro(new ParametroSimples(				FiltroLancamentoItemContabil.INDICADOR_USO,				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa		// colecao
		Collection<LancamentoItemContabil> colecaoLancamentoItemContabil = fachada				.pesquisar(filtroLancamentoItemContabil,						LancamentoItemContabil.class.getName());
		if (colecaoLancamentoItemContabil == null				|| colecaoLancamentoItemContabil.isEmpty()) {
			throw new ActionServletException(					"atencao.entidade_sem_dados_para_selecao", null,					"Tabela Lancamento Item Contabil");
		}
		httpServletRequest.setAttribute("colecaoLancamentoItemContabil",				colecaoLancamentoItemContabil);
		// Carregando dados da tabela FinanciamentoTipo		FiltroFinanciamentoTipo filtroFinanciamentoTipo = new FiltroFinanciamentoTipo();
		filtroFinanciamentoTipo				.setCampoOrderBy(FiltroFinanciamentoTipo.DESCRICAO);
		filtroFinanciamentoTipo.adicionarParametro(new ParametroSimples(				FiltroFinanciamentoTipo.INDICADOR_USO,				ConstantesSistema.INDICADOR_USO_ATIVO));
		// Verifica se os dados foram informados da tabela existem e joga numa		// colecao
		Collection<FinanciamentoTipo> colecaoFinanciamentoTipo = fachada				.pesquisar(filtroFinanciamentoTipo, FinanciamentoTipo.class						.getName());
		if (colecaoFinanciamentoTipo == null				|| colecaoFinanciamentoTipo.isEmpty()) {
			throw new ActionServletException(					"atencao.entidade_sem_dados_para_selecao", null,					"Tabela Financiamento Tipo");
		}
		httpServletRequest.setAttribute("colecaoFinanciamentoTipo",				colecaoFinanciamentoTipo);
		// Verifica se veio do filtrar ou do manter		if (httpServletRequest.getParameter("manter") != null) {			sessao.setAttribute("manter", true);				} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		// Verifica se a funcionalidade j� est� na sess�o, em caso afirmativo		// significa que o usu�rio j� entrou na tela e apenas selecionou algum		// item que deu um reload na tela e em caso negativo significa que ele		// est� entrando pela primeira vez		if (sessao.getAttribute("colecaoTipoDebito") == null) {
			if (sessao.getAttribute("objetoDebitoTipo") != null) {
				DebitoTipo debitoTipo = (DebitoTipo) sessao						.getAttribute("objetoDebitoTipo");				sessao.setAttribute("idDebitoTipo", debitoTipo.getId()						.toString());
				form.setCodigo(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());								form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());
				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());
				form.setValorLimiteDebito("" + valorAux);								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);				form.setIdTipoDebito(debitoTipo.getId().toString());								form.setIndicadorDebitoCartaoCredito(""+debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(""+debitoTipo.getIndicadorJurosParCliente());
				id = debitoTipo.getId().toString();				sessao.setAttribute("idTipoDebito", id);
			} else {
				String idTipoDebito = null;				if (httpServletRequest.getParameter("idDebitoTipo") == null						|| httpServletRequest.getParameter("idDebitoTipo")								.equals("")) {
					idTipoDebito = (String) sessao.getAttribute("idTipoDebito");
				} else {					idTipoDebito = (String) httpServletRequest							.getParameter("idDebitoTipo");
					sessao.setAttribute("idDebitoTipo", idTipoDebito);
				}
				sessao.setAttribute("idTipoDebito", idTipoDebito);
				id = idTipoDebito;
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(						FiltroDebitoTipo.ID, idTipoDebito));
				Collection<DebitoTipo> colecaoDebitoTipo = fachada.pesquisar(						filtroDebitoTipo, DebitoTipo.class.getName());
				if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {					throw new ActionServletException(							"atencao.atualizacao.timestamp");
				}
				httpServletRequest.setAttribute("colecaoDebitoTipo",						colecaoDebitoTipo);
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo						.iterator().next();
				form.setCodigo(debitoTipo.getId().toString());				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());
				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);
				form.setValorLimiteDebito("" + valorAux);								form.setIndicadorDebitoCartaoCredito("" + debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(debitoTipo.getIndicadorJurosParCliente().toString());
				form.setIdTipoDebito(debitoTipo.getId().toString());
			}
		}
		// -------------- bt DESFAZER ---------------		if (httpServletRequest.getParameter("desfazer") != null				&& httpServletRequest.getParameter("desfazer")						.equalsIgnoreCase("S")) {
			String idDebitoTipo = null;			if (httpServletRequest.getParameter("idDebitoTipo") == null					|| httpServletRequest.getParameter("idDebitoTipo").equals(							"")) {
				idDebitoTipo = (String) sessao.getAttribute("idDebitoTipo");
			} else {
				idDebitoTipo = (String) httpServletRequest						.getParameter("idDebitoTipo");
				sessao.setAttribute("idDebitoTipo", idDebitoTipo);
			}
			if (idDebitoTipo.equalsIgnoreCase("")) {
				idDebitoTipo = null;
			}
			if ((idDebitoTipo == null) && (id == null)) {
				DebitoTipo debitoTipo = (DebitoTipo) sessao						.getAttribute("objetoDebitoTipo");
				form.setCodigo(debitoTipo.getId().toString());
				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricao(debitoTipo.getDescricao());
				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());
				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());
				form.setValorLimiteDebito("" + valorAux);								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);								form.setIndicadorDebitoCartaoCredito("" + debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(""+debitoTipo.getIndicadorJurosParCliente());				form.setIdTipoDebito(debitoTipo.getId().toString());
				sessao.setAttribute("debitoTipoAtualizar", debitoTipo);
				sessao.removeAttribute("debitoTipo");
			}
			if ((idDebitoTipo == null) && (id != null)) {
				idDebitoTipo = id;
			}
			if (idDebitoTipo != null) {
				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
				filtroDebitoTipo						.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
				filtroDebitoTipo.adicionarParametro(new ParametroSimples(						FiltroDebitoTipo.ID, idDebitoTipo));
				Collection<DebitoTipo> colecaoDebitoTipo = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());
				if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
					throw new ActionServletException(							"atencao.atualizacao.timestamp");				}
				httpServletRequest.setAttribute("colecaoDebitoTipo",						colecaoDebitoTipo);
				DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo						.iterator().next();
				form.setCodigo(debitoTipo.getId().toString());				form.setIdTipoDebito(debitoTipo.getId().toString());				form.setDescricao(debitoTipo.getDescricao());
				form.setDescricaoAbreviada(debitoTipo.getDescricaoAbreviada());
				form.setFinanciamentoTipo(debitoTipo.getFinanciamentoTipo()						.getId().toString());
				form.setLancamentoItemContabil(debitoTipo						.getLancamentoItemContabil().getId().toString());
				form.setIndicadorGeracaoDebitoAutomatica(""						+ debitoTipo.getIndicadorGeracaoAutomatica());				form.setIndicadorGeracaoDebitoConta(""						+ debitoTipo.getIndicadorGeracaoConta());
				form.setIndicadorUso("" + debitoTipo.getIndicadorUso());
				String valorAux = Util.formatarMoedaReal(debitoTipo						.getValorLimite());
				form.setValorLimiteDebito("" + valorAux);								String valorSug = Util.formatarMoedaReal(debitoTipo						.getValorSugerido());								form.setValorSugerido("" + valorSug);								form.setIndicadorDebitoCartaoCredito("" + debitoTipo.getIndicadorDebitoCartaoCredito());								form.setIndicadorJurosParCliente(""+debitoTipo.getIndicadorJurosParCliente());
				form.setIdTipoDebito(debitoTipo.getId().toString());
			}
		}
		// -------------- bt DESFAZER ---------------
		httpServletRequest.setAttribute("colecaoDebitoTipoTela", sessao				.getAttribute("colecaoDebitoTipoTela"));
		return retorno;
	}
}