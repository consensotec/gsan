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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.atendimentopublico.ordemservico.FiltroUnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 27/12/2010
 */
public class RemoverCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		if(httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("removerRua") ){
			
			String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

			// mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
			// nenhum registro
			if (ids == null || ids.length == 0) {
				throw new ActionServletException(
						"atencao.registros.nao_selecionados");
			}
			
			FiltroUnidadeRepavimentadoraCustoPavimentoRua filtroUnidadeRepavimentadoraCustoPavimentoRua = 
					new FiltroUnidadeRepavimentadoraCustoPavimentoRua();
			
			Collection idsUnidadeRepavimentadoraCustoPavimentoRua = new ArrayList(ids.length);
			
			for (int i = 0; i < ids.length; i++) {
				idsUnidadeRepavimentadoraCustoPavimentoRua.add(new Integer(ids[i]));
			}
			
			filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarParametro(new ParametroSimplesIn(
					FiltroUnidadeRepavimentadoraCustoPavimentoRua.ID, idsUnidadeRepavimentadoraCustoPavimentoRua));
			
			filtroUnidadeRepavimentadoraCustoPavimentoRua.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUnidadeRepavimentadoraCustoPavimentoRua.PAVIMENTO_RUA);

			Collection coletionUnidadeRepavimentadoraCustoPavimentoRua = fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoRua,
					UnidadeRepavimentadoraCustoPavimentoRua.class.getName());
			
			Iterator itera = coletionUnidadeRepavimentadoraCustoPavimentoRua.iterator();
			
			while(itera.hasNext()){
			
				UnidadeRepavimentadoraCustoPavimentoRua unidRepavCustoPavimentoRua = (UnidadeRepavimentadoraCustoPavimentoRua) itera.next();
				
				if(!fachada.verificaRemoverCustoPavimentoPorRepavimentadora(
						unidRepavCustoPavimentoRua.getUnidadeRepavimentadora().getId(), 
						unidRepavCustoPavimentoRua.getPavimentoRua().getId(), new Integer("1"))){
					
					// ------------ REGISTRAR TRANSA��O ----------------
					RegistradorOperacao registradorOperacao = new RegistradorOperacao(
							Operacao.OPERACAO_EXCLUIR_CUSTO_PAVIMENTO, unidRepavCustoPavimentoRua.getId(),
							unidRepavCustoPavimentoRua.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
							UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
					// ------------ REGISTRAR TRANSA��O ----------------
					registradorOperacao.registrarOperacao(unidRepavCustoPavimentoRua);
					
					// Remover Unidade Repavimentadora Custo Pavimento Rua
					fachada.remover(unidRepavCustoPavimentoRua);
				}else{
					throw new ActionServletException("atencao.custo_pavimento_por_repavimentadora_rua.remover");
				}
			}

			Integer idQt = ids.length;

			montarPaginaSucesso(httpServletRequest, idQt.toString()
					+ " Custo(s) de Pavimento de Rua removido(s) com sucesso.",
					"Realizar outra Manuten��o de Custo do Pavimento de Rua",
					"exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
			
		}else if(httpServletRequest.getParameter("acao") != null && 
	        	httpServletRequest.getParameter("acao").equals("removerCalcada") ){
			
			String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao_segunda();

			// mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
			// nenhum registro
			if (ids == null || ids.length == 0) {
				throw new ActionServletException(
						"atencao.registros.nao_selecionados");
			}
			
			FiltroUnidadeRepavimentadoraCustoPavimentoCalcada filtroUnidadeRepavimentadoraCustoPavimentoCalcada = 
					new FiltroUnidadeRepavimentadoraCustoPavimentoCalcada();
			
			Collection idsUnidadeRepavimentadoraCustoPavimentoCalcada = new ArrayList(ids.length);
			
			for (int i = 0; i < ids.length; i++) {
				idsUnidadeRepavimentadoraCustoPavimentoCalcada.add(new Integer(ids[i]));
			}
			
			filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarParametro(new ParametroSimplesIn(
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.ID, idsUnidadeRepavimentadoraCustoPavimentoCalcada));
			
			filtroUnidadeRepavimentadoraCustoPavimentoCalcada.adicionarCaminhoParaCarregamentoEntidade(
					FiltroUnidadeRepavimentadoraCustoPavimentoCalcada.PAVIMENTO_CALCADA);

			Collection coletionUnidadeRepavimentadoraCustoPavimentoCalcada = fachada.pesquisar(filtroUnidadeRepavimentadoraCustoPavimentoCalcada,
					UnidadeRepavimentadoraCustoPavimentoCalcada.class.getName());
			
			Iterator itera = coletionUnidadeRepavimentadoraCustoPavimentoCalcada.iterator();
			
			while(itera.hasNext()){
			
				UnidadeRepavimentadoraCustoPavimentoCalcada unidRepavCustoPavimentoCalcada = (UnidadeRepavimentadoraCustoPavimentoCalcada) itera.next();
				
				if(!fachada.verificaRemoverCustoPavimentoPorRepavimentadora(
						unidRepavCustoPavimentoCalcada.getUnidadeRepavimentadora().getId(), 
						unidRepavCustoPavimentoCalcada.getPavimentoCalcada().getId(), new Integer("2"))){
					
					// Remover Unidade Repavimentadora Custo Pavimento Rua
					fachada.remover(unidRepavCustoPavimentoCalcada);
				}else{
					throw new ActionServletException("atencao.custo_pavimento_por_repavimentadora_calcada.remover");
				}

			}

			Integer idQt = ids.length;

			montarPaginaSucesso(httpServletRequest, idQt.toString()
					+ " Custo(s) de Pavimento de Cal�ada removido(s) com sucesso.",
					"Realizar outra Manuten��o de Custo do Pavimento de Cal�ada",
					"exibirFiltrarCustoPavimentoPorRepavimentadoraAction.do?menu=sim");
		}
		
		return retorno;
	}

}
