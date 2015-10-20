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
package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.FiltroContaMensagem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		FiltrarMensagemContaActionForm filtrarMensagemContaActionForm = (FiltrarMensagemContaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String referenciaFaturamento = filtrarMensagemContaActionForm
				.getReferenciaFaturamento();
		String atualizar = httpServletRequest.getParameter("atualizar");
		String mensagemConta01 = filtrarMensagemContaActionForm
				.getMensagemConta01();
/*		String mensagemConta02 = filtrarMensagemContaActionForm
			.getMensagemConta02();
		String mensagemConta03 = filtrarMensagemContaActionForm
			.getMensagemConta03();
*/		
		String grupoFaturamento = filtrarMensagemContaActionForm
				.getGrupoFaturamentoHidden();
		String gerenciaRegional = filtrarMensagemContaActionForm
				.getGerenciaRegionalHidden();
		String localidade = filtrarMensagemContaActionForm.getLocalidade();
		String setorComercial = filtrarMensagemContaActionForm
				.getSetorComercial();
		String quadra = filtrarMensagemContaActionForm.getQuadra();

		if ((referenciaFaturamento == null || referenciaFaturamento
				.equalsIgnoreCase(""))
				&& (grupoFaturamento == null || grupoFaturamento
						.equalsIgnoreCase(""))
				&& (mensagemConta01 == null || mensagemConta01.equalsIgnoreCase(""))
				&& (gerenciaRegional == null || gerenciaRegional
						.equalsIgnoreCase(""))
				&& (localidade == null || localidade.equalsIgnoreCase(""))
				&& (setorComercial == null || setorComercial
						.equalsIgnoreCase(""))) {

			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		FiltroContaMensagem filtroContaMensagem = new FiltroContaMensagem();

		if (referenciaFaturamento != null
				&& !referenciaFaturamento.equalsIgnoreCase("")) {
			if (Util.validarAnoMes(referenciaFaturamento)) {
				throw new ActionServletException("atencao.ano_mes_invalido");
			} else {
				Integer mes = new Integer(referenciaFaturamento.substring(0, 2));
				Integer ano = new Integer(referenciaFaturamento.substring(3, 7));

				if (mes <= 0 || mes > 12) {
					throw new ActionServletException("atencao.ano_mes_invalido");
				}

				if (ano < 1900) {
					throw new ActionServletException("atencao.ano_mes_invalido");
				}

				if (referenciaFaturamento != null
						&& !referenciaFaturamento.equalsIgnoreCase("")) {
					Integer referenciaFaturametoTratado = new Integer(
							Util
									.formatarMesAnoParaAnoMesSemBarra(referenciaFaturamento));

					filtroContaMensagem
							.adicionarParametro(new ParametroSimples(
									FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO,
									referenciaFaturametoTratado));
				}
			}
		}

		if (grupoFaturamento != null && !grupoFaturamento.equalsIgnoreCase("")) {
			filtroContaMensagem
					.adicionarParametro(new ParametroSimples(
							FiltroContaMensagem.GRUPO_FATURAMENTO_ID,
							grupoFaturamento));

		}

		if (mensagemConta01 != null && !mensagemConta01.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ComparacaoTexto(
					FiltroContaMensagem.MENSAGEM_CONTA_01, mensagemConta01));
		}
		
/*		if (mensagemConta02 != null && !mensagemConta02.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ComparacaoTexto(
					FiltroContaMensagem.MENSAGEM_CONTA_02, mensagemConta02));
		}
		
		if (mensagemConta01 != null && !mensagemConta01.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ComparacaoTexto(
					FiltroContaMensagem.MENSAGEM_CONTA_03, mensagemConta03));
		}
*/		

		if (gerenciaRegional != null && !gerenciaRegional.equalsIgnoreCase("")) {
			filtroContaMensagem
					.adicionarParametro(new ParametroSimples(
							FiltroContaMensagem.GERENCIA_REGIONAL_ID,
							gerenciaRegional));

		}

		if (localidade != null && !localidade.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.LOCALIDADE_ID, localidade));

		}

		if (setorComercial != null && !setorComercial.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.SETOR_COMERCIAL_CD, setorComercial));

		}
		
		if (quadra != null && !quadra.equalsIgnoreCase("")) {
			filtroContaMensagem.adicionarParametro(new ParametroSimples(
					FiltroContaMensagem.NUMERO_QUADRA, quadra));
		}
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroContaMensagem
				.adicionarCaminhoParaCarregamentoEntidade("quadra");
		
		filtroContaMensagem.setCampoOrderBy(FiltroContaMensagem.ANO_MES_REFERECIA_FATURAMENTO);
		
		Collection<ContaMensagem> colecaoContaMensagem = (Collection<ContaMensagem>) fachada
				.pesquisar(filtroContaMensagem, ContaMensagem.class.getName());
		sessao.setAttribute("colecaoContaMensagem", colecaoContaMensagem);

		ActionForward retorno = null;

		if (colecaoContaMensagem != null && !colecaoContaMensagem.isEmpty()) {
			if (colecaoContaMensagem.size() == 1 && atualizar != null) {
				httpServletRequest.setAttribute("contaMensagemID",
						colecaoContaMensagem.iterator().next().getId());
				retorno = actionMapping
						.findForward("exibirAtualizarMensagemConta");
			} else {
				retorno = actionMapping
						.findForward("exibirManterMensagemConta");
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		
		if (sessao.getAttribute("filtroContaMensagem") != null){
			sessao.removeAttribute("filtroContaMensagem");
		}
		
		sessao.setAttribute("filtroContaMensagem", filtroContaMensagem);
		
		return retorno;
	}
	
	
	
}