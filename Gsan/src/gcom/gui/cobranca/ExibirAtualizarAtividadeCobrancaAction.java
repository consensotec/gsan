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
package gcom.gui.cobranca;

import gcom.batch.FiltroProcesso;
import gcom.batch.Processo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * @author Tiago Moreno
 */
public class ExibirAtualizarAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAtividadeCobranca");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
		filtroCobrancaAtividade.setCampoOrderBy(FiltroCobrancaAtividade.DESCRICAO);
		Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade, CobrancaAtividade.class.getName());
			
		sessao.setAttribute("colecaoAtividadePredecessora", colecaoCobrancaAtividade);
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
		
		AtualizarAtividadeCobrancaActionForm atualizarAtividadeCobrancaActionForm = (AtualizarAtividadeCobrancaActionForm) actionForm;
		
		String idProcessoAssociado = atualizarAtividadeCobrancaActionForm.getIdProcessoAssociado();
		String idAtividadeCobranca = httpServletRequest.getParameter("idRegistroAtualizar");
		
		if (idAtividadeCobranca != null && !idAtividadeCobranca.equalsIgnoreCase("")){
			sessao.setAttribute("idAtividadeCobranca", idAtividadeCobranca);
			FiltroCobrancaAtividade filtroCobrancaAtividadeNaBase = new FiltroCobrancaAtividade();
			filtroCobrancaAtividadeNaBase.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID, idAtividadeCobranca));
			filtroCobrancaAtividadeNaBase.adicionarCaminhoParaCarregamentoEntidade("processo");
			
			CobrancaAtividade cobrancaAtividade = 
				(CobrancaAtividade) fachada.pesquisar(filtroCobrancaAtividadeNaBase, 
						CobrancaAtividade.class.getName()).iterator().next();
			
			if (cobrancaAtividade != null){
				atualizarAtividadeCobrancaActionForm.setDescricaoAtividadeCobranca(
						cobrancaAtividade.getDescricaoCobrancaAtividade());
				
				if (cobrancaAtividade.getCobrancaAtividadePredecessora() != null){
					atualizarAtividadeCobrancaActionForm.setIdAtividadePredecessora(
							cobrancaAtividade.getCobrancaAtividadePredecessora().getId().toString());
				} else {
					atualizarAtividadeCobrancaActionForm.
						setIdAtividadePredecessora("-1");
				}
				atualizarAtividadeCobrancaActionForm.setDescricaoProcessoAssociado(
						cobrancaAtividade.getProcesso().getDescricaoProcesso());
				atualizarAtividadeCobrancaActionForm.setIdProcessoAssociado(
						cobrancaAtividade.getProcesso().getId().toString());
				atualizarAtividadeCobrancaActionForm.setOrdemCronograma(
						cobrancaAtividade.getOrdemRealizacao().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoAtividadeObrigatoria(
						cobrancaAtividade.getIndicadorObrigatoriedade().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoCompoeCronograda(
						cobrancaAtividade.getIndicadorCronograma().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoPodeSerComandada(
						cobrancaAtividade.getIndicadorComando().toString());
				atualizarAtividadeCobrancaActionForm.setOpcaoPodeSerExecutada(
						cobrancaAtividade.getIndicadorExecucao().toString());
				atualizarAtividadeCobrancaActionForm.setUltimaAlteracao(
						cobrancaAtividade.getUltimaAlteracao());
				if (cobrancaAtividade.getNumeroDiasExecucao() != null) {
					atualizarAtividadeCobrancaActionForm.setQuantidadeDiasExecucao(
							cobrancaAtividade.getNumeroDiasExecucao().toString());
				}
				if (cobrancaAtividade.getCobrancaAcao() != null) {
					atualizarAtividadeCobrancaActionForm.setCobrancaAcao(
							cobrancaAtividade.getCobrancaAcao().getId().toString());
				}
			}
		}
		
		
		if (idProcessoAssociado != null && !idProcessoAssociado.trim().equals("")) {
			FiltroProcesso filtroProcesso = new FiltroProcesso();
			filtroProcesso.adicionarParametro(new ParametroSimples(FiltroProcesso.ID, idProcessoAssociado));
			
			Collection<Processo> colecaoProcesso = fachada.pesquisar(filtroProcesso, Processo.class.getName());
			
			if (colecaoProcesso != null && !colecaoProcesso.isEmpty()) {
				Processo processo = (Processo) colecaoProcesso.iterator().next();
				atualizarAtividadeCobrancaActionForm.setDescricaoProcessoAssociado(processo.getDescricaoProcesso());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
			} else {
				atualizarAtividadeCobrancaActionForm.setIdProcessoAssociado("");
				atualizarAtividadeCobrancaActionForm.setDescricaoProcessoAssociado("PROCESSO INEXISTENTE");
				httpServletRequest.setAttribute("processoInexistente", true);
			}
		
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		return retorno;

	}

}
