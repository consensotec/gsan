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
* Thiago Vieira
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