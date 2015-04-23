/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Thiago Vieira
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gui.cobranca.spcserasa;

import gsan.cobranca.FiltroNegativacaoComando;
import gsan.cobranca.FiltroNegativacaoCriterio;
import gsan.cobranca.FiltroNegativacaoCriterioClienteTipo;
import gsan.cobranca.NegativacaoComando;
import gsan.cobranca.NegativacaoCriterio;
import gsan.cobranca.NegativacaoCriterioClienteTipo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Cesar Medeiros
 */
public class ExibirConsultarComandoNegativacaoTipoGuiaPagamentoPopupAction extends GcomAction {


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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarComandoGuiaPagamentoPopup");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConsultarNegativacaoComandoGuiaPagamentoActionForm form = (ConsultarNegativacaoComandoGuiaPagamentoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("idSelecionado") != null
				&& !httpServletRequest.getParameter("idSelecionado").equals("")) {

			String idComandoNegativacaoSelecionado = httpServletRequest
					.getParameter("idSelecionado");

			FiltroNegativacaoComando filtroNegativacaoComando = new FiltroNegativacaoComando();
			filtroNegativacaoComando.adicionarParametro(new ParametroSimples(
					FiltroNegativacaoComando.ID, new Integer(
							idComandoNegativacaoSelecionado)));
			filtroNegativacaoComando
					.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			filtroNegativacaoComando
					.adicionarCaminhoParaCarregamentoEntidade("usuario");

			Collection collNegativacaoComando = fachada.pesquisar(
					filtroNegativacaoComando,
					NegativacaoComando.class.getName());
			NegativacaoComando negativacaoComando = (NegativacaoComando) collNegativacaoComando
					.iterator().next();

			if (negativacaoComando.getNegativador().getCliente().getNome() != null) {
				form.setNegativador(negativacaoComando.getNegativador()
						.getCliente().getNome());
			}
			
		
			FiltroNegativacaoCriterio filtroNegativacaoCriterio = new FiltroNegativacaoCriterio();
			filtroNegativacaoCriterio.adicionarParametro(new ParametroSimples(
					FiltroNegativacaoCriterio.ID_NEGATIVACAO_COMANDO,
					negativacaoComando.getId()));
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("localidadeInicial");
			filtroNegativacaoCriterio
					.adicionarCaminhoParaCarregamentoEntidade("localidadeFinal");

			Collection collNegativacaoCriterio = fachada.pesquisar(
					filtroNegativacaoCriterio,
					NegativacaoCriterio.class.getName());
			NegativacaoCriterio negativacaoCriterio = (NegativacaoCriterio) collNegativacaoCriterio
					.iterator().next();

			if (negativacaoCriterio.getDescricaoTitulo() != null) {
				form.setTituloComando(negativacaoCriterio.getDescricaoTitulo());
			}

			if (negativacaoCriterio.getDescricaoSolicitacao() != null) { 
				form.setDescricaoSolicitacao(negativacaoCriterio
						.getDescricaoSolicitacao());
			}

			if (new Integer(negativacaoComando.getIndicadorSimulacao()) != null) {
				form.setSimularNegativacao(new Integer(negativacaoComando
						.getIndicadorSimulacao()).toString());
			}

			if (negativacaoComando.getDataPrevista() != null) {
				form.setDataPrevistaExecucao(Util
						.formatarData(negativacaoComando.getDataPrevista()));
			}

			if (negativacaoComando.getUsuario().getNomeUsuario() != null) {
				form.setUsuarioResponsavel(negativacaoComando.getUsuario()
						.getNomeUsuario());
			}

			if (negativacaoCriterio.getQuantidadeMaximaInclusoes() != null) {
				form.setQuantidadeMaximaInclusoes(negativacaoCriterio
						.getQuantidadeMaximaInclusoes().toString());
			}

			
			if (negativacaoCriterio.getAnoMesReferenciaContaInicial() != null) {
				form.setPeriodoReferenciaDebitoInicial(Util
						.formatarAnoMesParaMesAno(negativacaoCriterio
								.getAnoMesReferenciaContaInicial()));
			}

			if (negativacaoCriterio.getAnoMesReferenciaContaFinal() != null) {
				form.setPeriodoReferenciaDebitoFinal(Util
						.formatarAnoMesParaMesAno(negativacaoCriterio
								.getAnoMesReferenciaContaFinal()));
			}

			if (negativacaoCriterio.getDataVencimentoDebitoInicial() != null) {
				form.setPeriodoVencimentoDebitoInicial(Util
						.formatarData(negativacaoCriterio
								.getDataVencimentoDebitoInicial()));
			}

			if (negativacaoCriterio.getDataVencimentoDebitoFinal() != null) {
				form.setPeriodoVencimentoDebitoFinal(Util
						.formatarData(negativacaoCriterio
								.getDataVencimentoDebitoFinal()));
			}

			if (negativacaoCriterio.getValorMinimoDebito() != null) {
				form.setValorDebitoInicial(negativacaoCriterio
						.getValorMinimoDebito().toString());
			}

			if (negativacaoCriterio.getValorMaximoDebito() != null) {
				form.setValorDebitoFinal(negativacaoCriterio
						.getValorMaximoDebito().toString());
			}

			if (negativacaoCriterio.getCliente() != null) {
				if (negativacaoCriterio.getCliente().getNome() != null) {
					form.setCliente(negativacaoCriterio.getCliente().getNome());
				}

				if (negativacaoCriterio.getCliente().getId() != null) {
					form.setIdCliente(negativacaoCriterio.getCliente().getId()
							.toString());
				}

			}

			
			// Lista de Cliente Tipo
			FiltroNegativacaoCriterioClienteTipo filtroNegativacaoCriterioClienteTipo = new FiltroNegativacaoCriterioClienteTipo();
			filtroNegativacaoCriterioClienteTipo
					.adicionarParametro(new ParametroSimples(
							FiltroNegativacaoCriterioClienteTipo.COMP_ID_NEGATIVACAO_CRITERIO_ID,
							negativacaoCriterio.getId()));
			filtroNegativacaoCriterioClienteTipo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.clienteTipo");
			filtroNegativacaoCriterioClienteTipo
					.adicionarCaminhoParaCarregamentoEntidade("comp_id.negativacaoCriterio");
			filtroNegativacaoCriterioClienteTipo
					.adicionarParametro(new ParametroSimples(
							"comp_id.clienteTipo.indicadorUso",
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativacaoCriterioClienteTipo
					.setCampoOrderBy("comp_id.clienteTipo.descricao");
			Collection collNegativacaoCriterioClienteTipo = fachada.pesquisar(
					filtroNegativacaoCriterioClienteTipo,
					NegativacaoCriterioClienteTipo.class.getName());
		
			
			sessao.setAttribute("collNegativacaoCriterioClienteTipo",
					collNegativacaoCriterioClienteTipo);


		}
		
		return retorno;

        
    }
}