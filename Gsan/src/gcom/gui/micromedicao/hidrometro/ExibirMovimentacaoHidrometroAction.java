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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentacao;
import gcom.micromedicao.hidrometro.FiltroHidrometroMovimentado;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o da tela de consultar cr�dito a realizar
 * 
 * @author Fernanda Paiva
 * @created 17 de Janeiro de 2006
 */
public class ExibirMovimentacaoHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno e seta o mapeamento para a tela de
		// consultar Movimentacao Hidrometro
		ActionForward retorno = actionMapping
				.findForward("movimentacaoHidrometro");

		// cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// cria uma inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera o c�digo da conta do request
		String idMovimentacao = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		// se o c�digo n�o for nulo
		if (idMovimentacao != null && !idMovimentacao.equalsIgnoreCase("")) {
			// remove a cole��o de movimentac�es da sess�o
			sessao.removeAttribute("colecaoMovimentacaoHidrometro");
		}

		/*
		 * Pesquisando a movimentacao
		 * =====================================================================
		 */

		// se n�o existir a cole��o na sess�o
		// cria o filtro de cr�ditos a realizar
		FiltroHidrometroMovimentacao filtroHidrometroMovimentacao = new FiltroHidrometroMovimentacao();

		// carrega o motivo
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroMotivoMovimentacao");

		// carrega o local de armazenagem origem
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemOrigem");

		// carrega o local de armazenagem destino
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroLocalArmazenagemDestino");
		
		//		 carrega o local de armazenagem origem
		filtroHidrometroMovimentacao
				.adicionarCaminhoParaCarregamentoEntidade("usuario");

		// seta o c�digo do imovel no filtro
		filtroHidrometroMovimentacao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroMovimentacao.ID, idMovimentacao));

		// pesquisa a cole��o de cr�ditos a realizar
		Collection<HidrometroMovimentacao> colecaoMovimentacaoHidrometro = fachada
				.pesquisar(filtroHidrometroMovimentacao,
						HidrometroMovimentacao.class.getName());

		Collection colecaoHidrometroMovimentado = null;
		
		if (!colecaoMovimentacaoHidrometro.isEmpty()) {

			Iterator hidrometroMovimentacaoIterator = colecaoMovimentacaoHidrometro.iterator();
			
			while (hidrometroMovimentacaoIterator.hasNext()) {
				
				HidrometroMovimentacao hidrometroMovimentacao = (HidrometroMovimentacao) hidrometroMovimentacaoIterator.next();

				FiltroHidrometroMovimentado filtroHidrometroMovimentado = new FiltroHidrometroMovimentado();
				
				filtroHidrometroMovimentado
						.adicionarCaminhoParaCarregamentoEntidade("hidrometro");

				filtroHidrometroMovimentado
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroMovimentado.HIDROMETRO_MOVIMENTACAO_ID,
								hidrometroMovimentacao.getId()));
				
				filtroHidrometroMovimentado.setConsultaSemLimites(true);
				
				colecaoHidrometroMovimentado = fachada.pesquisar(
						filtroHidrometroMovimentado,
						HidrometroMovimentado.class.getName());

				Integer quantidade = colecaoHidrometroMovimentado.size();
				
				if (!colecaoHidrometroMovimentado.isEmpty()) 
				{
					String faixaInicial = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(0)).getHidrometro().getNumero();
					
					String faixaFinal = ((HidrometroMovimentado) ((List) colecaoHidrometroMovimentado).get(quantidade-1)).getHidrometro().getNumero();
					
					if(faixaInicial != null)
					{
						Integer tamanhoFaixaInicial = faixaInicial.length();
						if(tamanhoFaixaInicial > 4)
						{
							String fixo = faixaInicial.substring(0,4);
							hidrometroMovimentacao.setFixo(fixo);
						}
						faixaInicial = faixaInicial.substring(4,tamanhoFaixaInicial-1);
					}
					if(faixaFinal != null)
					{
						Integer tamanhoFaixaFinal = faixaFinal.length();
						faixaFinal = faixaFinal.substring(4,tamanhoFaixaFinal-1);
					}
					hidrometroMovimentacao.setFaixaInicial(faixaInicial);
					
					hidrometroMovimentacao.setFaixaFinal(faixaFinal);
					
				}
				
				hidrometroMovimentacao.setQuantidade(quantidade.toString());
			}
		}
		Collection colecaoHidrometro = null;
		Collection colecaoObterHidrometro = new ArrayList();
		
		if (!colecaoHidrometroMovimentado.isEmpty()) {

			Iterator hidrometroMovimentadoIterator = colecaoHidrometroMovimentado.iterator();
			
			while (hidrometroMovimentadoIterator.hasNext()) {
				
				HidrometroMovimentado hidrometroMovimentado = (HidrometroMovimentado) hidrometroMovimentadoIterator.next();

				FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
				
				filtroHidrometro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometro.ID,
								hidrometroMovimentado.getHidrometro().getId()));
				filtroHidrometro
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroMarca");
				filtroHidrometro
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
				filtroHidrometro
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroSituacao");
				filtroHidrometro
                        .setCampoOrderBy(FiltroHidrometro.NUMERO_HIDROMETRO);
                
				colecaoHidrometro = fachada.pesquisar(
						filtroHidrometro, Hidrometro.class.getName());

				if (!colecaoHidrometro.isEmpty()) 
				{
					colecaoObterHidrometro.addAll(colecaoHidrometro);
				}
			}
		}

		// ====================================================================
		if (colecaoMovimentacaoHidrometro == null
				|| colecaoMovimentacaoHidrometro.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Movimenta��o de Hidr�metro");
		} else {
			// seta a cole��o de movimenta��es na sess�o
			sessao.setAttribute("colecaoMovimentacaoHidrometro",
					colecaoMovimentacaoHidrometro);
			sessao.setAttribute("colecaoObterHidrometro",
					colecaoObterHidrometro);
		}
		
		// retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
