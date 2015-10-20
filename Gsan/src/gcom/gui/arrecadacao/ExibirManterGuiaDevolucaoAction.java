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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroGuiaDevolucao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite filtrar resolu��es de diretoria [UC0219] Filtrar Resolu��o de
 * Diretoria
 * 
 * @author Rafael Corr�a
 * @since 31/03/2006
 */
public class ExibirManterGuiaDevolucaoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterGuiaDevolucaoCliente");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		ManterGuiaDevolucaoActionForm manterGuiaDevolucaoActionForm = (ManterGuiaDevolucaoActionForm) actionForm;

		// Limpa o atributo se o usu�rio voltou para o manter
		if (sessao.getAttribute("guiaDevolucaoAtualizar") != null) {
			sessao.removeAttribute("guiaDevolucaoAtualizar");
		}
		FiltroGuiaDevolucao filtroGuiaDevolucao = null;

		if (sessao.getAttribute("filtroGuiaDevolucaoCliente") != null) {
			filtroGuiaDevolucao = (FiltroGuiaDevolucao) sessao
					.getAttribute("filtroGuiaDevolucaoCliente");
			sessao.setAttribute("filtroGuiaDevolucaoCliente", filtroGuiaDevolucao);
			Cliente cliente = (Cliente) sessao.getAttribute("cliente");
			sessao.setAttribute("cliente", cliente);

			manterGuiaDevolucaoActionForm.setIdCliente(cliente.getId()
					.toString());
			manterGuiaDevolucaoActionForm.setNomeCliente(cliente.getNome());

			// Verifica o tipo do cliente para setar o cpf ou cnpj e profiss�o
			// ou ramo atividade
			if (cliente.getClienteTipo().getId().equals(
					ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				manterGuiaDevolucaoActionForm.setCpfCnpj(cliente
						.getCpfFormatado());
				if (cliente.getProfissao() != null) {
					manterGuiaDevolucaoActionForm.setProfissao(cliente
							.getProfissao().getDescricao());
				}
			} else {
				manterGuiaDevolucaoActionForm.setCpfCnpj(cliente
						.getCnpjFormatado());
				if (cliente.getRamoAtividade() != null) {
					manterGuiaDevolucaoActionForm.setRamoAtividade(cliente
							.getRamoAtividade().getDescricao());
				}
			}

		} else {
			filtroGuiaDevolucao = (FiltroGuiaDevolucao) sessao
					.getAttribute("filtroGuiaDevolucaoImovel");
			sessao.setAttribute("filtroGuiaDevolucaoImovel", filtroGuiaDevolucao);
			Imovel imovel = (Imovel) sessao.getAttribute("imovel");
			sessao.setAttribute("imovel", imovel);

			manterGuiaDevolucaoActionForm
					.setIdImovel(imovel.getId().toString());
			manterGuiaDevolucaoActionForm.setInscricao(imovel
					.getInscricaoFormatada());

			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
					ClienteRelacaoTipo.USUARIO));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(
					FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel
					.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection colecaoClienteImovel = fachada.pesquisar(
					filtroClienteImovel, ClienteImovel.class.getName());

			if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
				ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
						.iterator().next();
				manterGuiaDevolucaoActionForm.setNomeCliente(clienteImovel
						.getCliente().getNome());
			}

			if (imovel.getLigacaoAguaSituacao() != null) {
				manterGuiaDevolucaoActionForm.setSituacaoLigacaoAgua(imovel
						.getLigacaoAguaSituacao().getDescricao());
			}

			if (imovel.getLigacaoEsgotoSituacao() != null) {
				manterGuiaDevolucaoActionForm.setSituacaoLigacaoEsgoto(imovel
						.getLigacaoEsgotoSituacao().getDescricao());
			}

			retorno = actionMapping
					.findForward("exibirManterGuiaDevolucaoImovel");
		}


		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta
		// que aparecer� na tela
		Integer totalRegistros = fachada
				.pesquisarGuiaDevolucaoCount(filtroGuiaDevolucao);

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando
		// o numero de paginas
		// da pesquisa que est� no request
		// Faz os carregamentos necess�rios
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.localidade");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.setorComercial");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.imovel.quadra");
//		filtroGuiaDevolucao
//				.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento.cliente");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("ordemServico.servicoTipo.debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroGuiaDevolucao.adicionarCaminhoParaCarregamentoEntidade("conta");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("guiaPagamento.debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAnterior");
		filtroGuiaDevolucao
				.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
		
		filtroGuiaDevolucao.setCampoOrderBy(
				FiltroGuiaDevolucao.CREDITO_TIPO_ID,
				FiltroGuiaDevolucao.DOCUMENTO_TIPO_ID);

		
		Collection colecaoGuiaDevolucao = fachada.pesquisarGuiaDevolucao(
				filtroGuiaDevolucao, (Integer) httpServletRequest
						.getAttribute("numeroPaginasPesquisa"));
		
		filtroGuiaDevolucao.limparCamposOrderBy();

		if (colecaoGuiaDevolucao != null && !colecaoGuiaDevolucao.isEmpty()) {
//			if (colecaoGuiaDevolucao.size() == 1
//					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
//							.getParameter("page.offset").equals("1"))) {
//				if (httpServletRequest.getParameter("atualizar") != null) {
//					retorno = actionMapping
//							.findForward("exibirAtualizarGuiaDevolucao");
//					GuiaDevolucao guiaDevolucao = (GuiaDevolucao) colecaoGuiaDevolucao
//							.iterator().next();
//					sessao.setAttribute("guiaDevolucao", guiaDevolucao);
//				} else {
//					httpServletRequest.setAttribute("colecaoGuiaDevolucao",
//							colecaoGuiaDevolucao);
//				}
//			} else {
				httpServletRequest.setAttribute("colecaoGuiaDevolucao",
						colecaoGuiaDevolucao);
//			}
		} else {
			// Nenhuma resolu��o de diretoria cadastrada
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
