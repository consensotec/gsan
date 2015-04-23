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
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 * Anderson Italo Felinto de Lima
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
package gsan.gui.cobranca;

import java.util.Collection;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processamento para filtrar a ação da cobrança
 * 
 * @author Sávio Luiz
 * @date 10/10/2007
 */
public class FiltrarAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("manterCobrancaAcao");

		AcaoCobrancaFiltrarActionForm acaoCobrancaFiltrarActionForm = (AcaoCobrancaFiltrarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		String indicadorAtualizar = httpServletRequest
				.getParameter("indicadorAtualizar");

		if (indicadorAtualizar == null) {
			acaoCobrancaFiltrarActionForm.setIndicadorAtualizar("2");
		} else {
			acaoCobrancaFiltrarActionForm
					.setIndicadorAtualizar(indicadorAtualizar);
		}

		Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmParametroInformado = false;

		FiltroCobrancaAcao filtroCobrancaAcao = fachada.filtrarAcaoCobranca(
				acaoCobrancaFiltrarActionForm.getDescricaoAcao(),
				acaoCobrancaFiltrarActionForm.getNumeroDiasValidade(),
				acaoCobrancaFiltrarActionForm.getIdAcaoPredecessora(),
				acaoCobrancaFiltrarActionForm.getNumeroDiasEntreAcoes(),
				acaoCobrancaFiltrarActionForm.getIdTipoDocumentoGerado(),
				acaoCobrancaFiltrarActionForm.getIdSituacaoLigacaoAgua(),
				acaoCobrancaFiltrarActionForm.getIdSituacaoLigacaoEsgoto(),
				acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio(),
				acaoCobrancaFiltrarActionForm.getDescricaoCobrancaCriterio(),
				acaoCobrancaFiltrarActionForm.getIdServicoTipo(),
				acaoCobrancaFiltrarActionForm.getDescricaoServicoTipo(),
				acaoCobrancaFiltrarActionForm.getOrdemCronograma(),
				acaoCobrancaFiltrarActionForm.getIcCompoeCronograma(),
				acaoCobrancaFiltrarActionForm.getIcAcaoObrigatoria(),
				acaoCobrancaFiltrarActionForm.getIcRepetidaCiclo(),
				acaoCobrancaFiltrarActionForm.getIcSuspensaoAbastecimento(),
				acaoCobrancaFiltrarActionForm.getIcDebitosACobrar(),
				acaoCobrancaFiltrarActionForm.getIcAcrescimosImpontualidade(),
				acaoCobrancaFiltrarActionForm.getIcGeraTaxa(),
				acaoCobrancaFiltrarActionForm.getIcEmitirBoletimCadastro(),
				acaoCobrancaFiltrarActionForm.getIcImoveisSemDebitos(),
				acaoCobrancaFiltrarActionForm.getIcMetasCronograma(),
				acaoCobrancaFiltrarActionForm.getIcOrdenamentoCronograma(),
				acaoCobrancaFiltrarActionForm.getIcOrdenamentoEventual(),
				acaoCobrancaFiltrarActionForm.getIcDebitoInterfereAcao(),
				acaoCobrancaFiltrarActionForm.getNumeroDiasRemuneracaoTerceiro(),
				acaoCobrancaFiltrarActionForm.getIcUso(),
				acaoCobrancaFiltrarActionForm.getIcCreditosARealizar(),
				acaoCobrancaFiltrarActionForm.getIcNotasPromissoria(),
				acaoCobrancaFiltrarActionForm.getIcEfetuarAcaoCpfCnpjValido(),
				acaoCobrancaFiltrarActionForm.getIcOrdenarMaiorValor(),
				acaoCobrancaFiltrarActionForm.getIcValidarItem(),
				acaoCobrancaFiltrarActionForm.getIndicadorMensagemSMS(),
				acaoCobrancaFiltrarActionForm.getIndicadorMensagemEmail());
			
			// Verifica se o campo cobrança critério
			if (acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio()!= null && !acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio()
					.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
			
				FiltroCobrancaCriterio filtroCobranca = new FiltroCobrancaCriterio ();
				filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID, acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio()));
				Collection collCobranca = fachada.pesquisar(filtroCobranca, CobrancaCriterio.class.getName());
				if(collCobranca == null || collCobranca.isEmpty()){
					throw new ActionServletException(
							"atencao.cobranca_criterio_inexistente");
				}
				filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAcao.COBRANCAO_CRITERIO, acaoCobrancaFiltrarActionForm.getIdCobrancaCriterio()));
			}
		
			// Verifica se o campo serviço tipo
				if (acaoCobrancaFiltrarActionForm.getIdServicoTipo()!= null && !acaoCobrancaFiltrarActionForm.getIdServicoTipo()
						.trim().equalsIgnoreCase("")) {
					peloMenosUmParametroInformado = true;
						
					FiltroServicoTipo filtroServico = new FiltroServicoTipo();
					filtroServico.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, acaoCobrancaFiltrarActionForm.getIdServicoTipo()));
					Collection collServico = fachada.pesquisar(filtroServico, ServicoTipo.class.getName());
					if(collServico == null || collServico.isEmpty()){
						throw new ActionServletException(
							"atencao.servico_tipo");
					}
					filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
							FiltroCobrancaAcao.SERVICO_TIPO, acaoCobrancaFiltrarActionForm.getIdServicoTipo()));
				}
				
		// Manda o filtro pelo request para o ExibirManterClienteAction
		sessao.setAttribute("filtroCobrancaCriterio", filtroCobrancaAcao);
		sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);

		return retorno;
	}

}