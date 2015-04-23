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
package gsan.gui.arrecadacao.pagamento;

import gsan.arrecadacao.ArrecadadorMovimento;
import gsan.arrecadacao.ArrecadadorMovimentoItem;
import gsan.arrecadacao.FiltroArrecadadorMovimento;
import gsan.arrecadacao.pagamento.FiltroGuiaPagamento;
import gsan.arrecadacao.pagamento.FiltroGuiaPagamentoHistorico;
import gsan.arrecadacao.pagamento.FiltroGuiaPagamentoItemHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gsan.arrecadacao.pagamento.GuiaPagamentoItem;
import gsan.arrecadacao.pagamento.GuiaPagamentoItemHistorico;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.arrecadacao.pagamento.PagamentoHistorico;
import gsan.cobranca.DocumentoTipo;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroGuiaPagamentoItem;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroNaoNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarGuiaPagamento");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		// Recebe o id da guia de pagamento para fazer a consulta
		String guiaPagamentoId = httpServletRequest
				.getParameter("guiaPagamentoId");
		
		String guiaPagamentoHistoricoId = httpServletRequest
		.getParameter("guiaPagamentoHistoricoId");
		
		String nossoNumero = "";
		if(guiaPagamentoId != null && !guiaPagamentoId.equals("")){
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.GUIA_PAGAMENTO.toString(),guiaPagamentoId);
			nossoNumero = nossoNumero2.toString();
		}else if(guiaPagamentoHistoricoId != null && !guiaPagamentoHistoricoId.equals("")){
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.GUIA_PAGAMENTO.toString(),guiaPagamentoHistoricoId);
			nossoNumero = nossoNumero2.toString();
		}
		
		// Se chegar na funcionalidade sem o parâmetro indica situação de erro
		if ((guiaPagamentoId == null || guiaPagamentoId.trim().equals(""))
				&& (guiaPagamentoHistoricoId == null || guiaPagamentoHistoricoId.trim().equals(""))) {
			throw new ActionServletException("erro.sistema");

		}
		
		if(guiaPagamentoId != null && !guiaPagamentoId.equals("")){
			Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(guiaPagamentoId), DocumentoTipo.GUIA_PAGAMENTO);
			
			if(Util.isVazioOrNulo(colPagamentos)){
				Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(guiaPagamentoId), DocumentoTipo.GUIA_PAGAMENTO);
				if(!Util.isVazioOrNulo(colHistoricoPagamentos)){
					PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colHistoricoPagamentos);
					ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(pagamentoHistorico, null);
					ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamentoHistorico.getArrecadadorMovimentoItem();
					
					httpServletRequest.setAttribute("pagamento", pagamentoHistorico);
					httpServletRequest.setAttribute("nossoNumero", nossoNumero);
					httpServletRequest.setAttribute("arrecadador", arrecadador);
					if(arrecadadorMovimentoItem != null){
						httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
					}
					sessao.setAttribute("contaPaga", "pago");
				}else{
					sessao.removeAttribute("contaPaga");
				}
			}else{
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colPagamentos);
				ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(null, pagamento);
				ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamento.getArrecadadorMovimentoItem();
				
				httpServletRequest.setAttribute("pagamento", pagamento);
				httpServletRequest.setAttribute("nossoNumero", nossoNumero);
				httpServletRequest.setAttribute("arrecadador", arrecadador);
				if(arrecadadorMovimentoItem != null){
					httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
				}
				sessao.setAttribute("contaPaga", "pago");
			}
			
		}else if(guiaPagamentoHistoricoId != null && !guiaPagamentoHistoricoId.equals("")){
			Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(guiaPagamentoHistoricoId), DocumentoTipo.GUIA_PAGAMENTO);
			if(Util.isVazioOrNulo(colPagamentos)){
				Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(guiaPagamentoHistoricoId), DocumentoTipo.GUIA_PAGAMENTO);
				if(!Util.isVazioOrNulo(colHistoricoPagamentos)){
					PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) Util.retonarObjetoDeColecao(colHistoricoPagamentos);
					ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(pagamentoHistorico, null);
					ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamentoHistorico.getArrecadadorMovimentoItem();
					
					httpServletRequest.setAttribute("pagamento", pagamentoHistorico);
					httpServletRequest.setAttribute("nossoNumero", nossoNumero);
					httpServletRequest.setAttribute("arrecadador", arrecadador);
					if(arrecadadorMovimentoItem != null){
						httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
					}
					sessao.setAttribute("contaPaga", "pago");
				}else{
					sessao.removeAttribute("contaPaga");
				}
			}else{
				Pagamento pagamento = (Pagamento) Util.retonarObjetoDeColecao(colPagamentos);
				ArrecadadorMovimento arrecadador = pesquisarArrecadadorMovimento(null, pagamento);
				ArrecadadorMovimentoItem arrecadadorMovimentoItem = pagamento.getArrecadadorMovimentoItem();
				
				httpServletRequest.setAttribute("pagamento", pagamento);
				httpServletRequest.setAttribute("nossoNumero", nossoNumero);
				httpServletRequest.setAttribute("arrecadador", arrecadador);
				if(arrecadadorMovimentoItem != null){
					httpServletRequest.setAttribute("indicadorAceitacao", arrecadadorMovimentoItem.getIndicadorAceitacao());
				}
				sessao.setAttribute("contaPaga", "pago");
			}
		}
		
		if (guiaPagamentoHistoricoId != null){
			// GUIA PAGAMENTO HISTORICO
			
			
			
			
			FiltroGuiaPagamentoHistorico filtroGuiaPagamentoHistorico = new FiltroGuiaPagamentoHistorico();
			filtroGuiaPagamentoHistorico.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamentoItemHistorico.ID, guiaPagamentoHistoricoId));

			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("localidade");			
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoByDcstIdatual");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			// Para a exibição do endereço do imóvel
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
			filtroGuiaPagamentoHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");

			GuiaPagamentoHistorico guiaPagamentoHistorico = (GuiaPagamentoHistorico)Util.retonarObjetoDeColecao
				(fachada.pesquisar(filtroGuiaPagamentoHistorico, GuiaPagamentoHistorico.class.getName()));

			// Envia o objeto consultado para a página
			httpServletRequest.setAttribute("guiaPagamentoHistorico", guiaPagamentoHistorico);
			
			sessao.removeAttribute("colecaoGuiaDebitoHistoricoTipoConsulta");
			
			FiltroGuiaPagamentoItemHistorico filtroGuiaPagamentoItemHistorico = new FiltroGuiaPagamentoItemHistorico();
			filtroGuiaPagamentoItemHistorico.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItemHistorico.GUIA_PAGAMENTO_HISTORICO_ID, guiaPagamentoHistorico.getId()));
			filtroGuiaPagamentoItemHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItemHistorico.adicionarParametro(new ParametroNaoNulo(FiltroGuiaPagamentoItemHistorico.DEBITO_TIPO_ID));
			filtroGuiaPagamentoItemHistorico.setCampoOrderBy(new String[]{"guiaPagamentoHistorico","debitoTipo"});
			Collection<GuiaPagamentoItemHistorico> colecaoGuiaPagamentoItemHistorico = fachada.pesquisar(filtroGuiaPagamentoItemHistorico, GuiaPagamentoItemHistorico.class.getName());
			
			
			if(colecaoGuiaPagamentoItemHistorico.isEmpty()){
				GuiaPagamentoItemHistorico guiaPagamentoItemHistorico = new GuiaPagamentoItemHistorico();
				guiaPagamentoItemHistorico.setDebitoTipo(guiaPagamentoHistorico.getDebitoTipo());
				guiaPagamentoItemHistorico.setValorDebito(guiaPagamentoHistorico.getValorDebito());
				colecaoGuiaPagamentoItemHistorico.add(guiaPagamentoItemHistorico);
			}
			sessao.setAttribute("colecaoGuiaDebitoHistoricoTipoConsulta", colecaoGuiaPagamentoItemHistorico);
			
			sessao.removeAttribute("colecaoGuiaCreditoHistoricoTipoConsulta");
			
			FiltroGuiaPagamentoItemHistorico filtroGuiaPagamentoItemHistoricoCredito = new FiltroGuiaPagamentoItemHistorico();
			filtroGuiaPagamentoItemHistoricoCredito.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItemHistorico.GUIA_PAGAMENTO_HISTORICO_ID, guiaPagamentoHistorico.getId()));
			filtroGuiaPagamentoItemHistoricoCredito.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
			filtroGuiaPagamentoItemHistoricoCredito.adicionarParametro(new ParametroNaoNulo(FiltroGuiaPagamentoItemHistorico.CREDITO_TIPO_ID));
			filtroGuiaPagamentoItemHistoricoCredito.setCampoOrderBy(new String[]{"guiaPagamentoHistorico","creditoTipo"});
			Collection<GuiaPagamentoItemHistorico> colecaoGuiaPagamentoItemHistoricoCredito = fachada.pesquisar(filtroGuiaPagamentoItemHistoricoCredito, GuiaPagamentoItemHistorico.class.getName());
			
			sessao.setAttribute("colecaoGuiaCreditoHistoricoTipoConsulta", colecaoGuiaPagamentoItemHistoricoCredito);
			
			
			
			
			
		}else{	
			
			// GUIA PAGAMENTO
			
//			 Consulta do GuiaPagamento
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(
					FiltroGuiaPagamento.ID, guiaPagamentoId));

			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");

			// Para a exibição do endereço do imóvel
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("origem.guiaPagamento.imovel");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("usuario");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
			filtroGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("cliente.profissao");

			GuiaPagamento guiaPagamento = (GuiaPagamento)Util.retonarObjetoDeColecao
				(fachada.pesquisar(filtroGuiaPagamento,GuiaPagamento.class.getName()));

			// Envia o objeto consultado para a página
			httpServletRequest.setAttribute("guiaPagamento", guiaPagamento);
			
			sessao.removeAttribute("colecaoGuiaDebitoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItem = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamento.getId()));
			filtroGuiaPagamentoItem.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
			filtroGuiaPagamentoItem.adicionarParametro(new ParametroNaoNulo(FiltroGuiaPagamentoItem.DEBITO_TIPO_ID));
			filtroGuiaPagamentoItem.setCampoOrderBy(new String[]{"guiaPagamentoGeral","debitoTipo"});
			
			
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItem = fachada.pesquisar(filtroGuiaPagamentoItem, GuiaPagamentoItem.class.getName());
			
			if(colecaoGuiaPagamentoItem.isEmpty()){
				GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
				guiaPagamentoItem.setDebitoTipo(guiaPagamento.getDebitoTipo());
				guiaPagamentoItem.setValorDebito(guiaPagamento.getValorDebito());
				colecaoGuiaPagamentoItem.add(guiaPagamentoItem);
			}
			sessao.setAttribute("colecaoGuiaDebitoTipoConsulta", colecaoGuiaPagamentoItem);
			
			
			sessao.removeAttribute("colecaoGuiaCreditoTipoConsulta");
			
			FiltroGuiaPagamentoItem filtroGuiaPagamentoItemCredito = new FiltroGuiaPagamentoItem();
			filtroGuiaPagamentoItemCredito.adicionarParametro(new ParametroSimples(FiltroGuiaPagamentoItem.GUIA_PAGAMENTO_GERAL_ID, guiaPagamento.getId()));
			filtroGuiaPagamentoItemCredito.adicionarCaminhoParaCarregamentoEntidade("creditoTipo");
			filtroGuiaPagamentoItemCredito.adicionarParametro(new ParametroNaoNulo(FiltroGuiaPagamentoItem.CREDITO_TIPO_ID));
			filtroGuiaPagamentoItemCredito.setCampoOrderBy(new String[]{"guiaPagamentoGeral","creditoTipo"});
			
			
			Collection<GuiaPagamentoItem> colecaoGuiaPagamentoItemCredito = fachada.pesquisar(filtroGuiaPagamentoItemCredito, GuiaPagamentoItem.class.getName());
			
			sessao.setAttribute("colecaoGuiaCreditoTipoConsulta", colecaoGuiaPagamentoItemCredito);
			
			
		}
		
		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaGuiaPagamento") != null) {
			sessao.setAttribute("caminhoRetornoTelaConsultaGuiaPagamento",httpServletRequest.getParameter("caminhoRetornoTelaConsultaGuiaPagamento"));
		}

		return retorno;
	}
	
	/**
	 * Pesquisa o Arrecadador do Movimento
	 * 
	 * @author Davi
	 * @date 21/10/2011
	 * 
	 * @param pagamentoHistorico
	 * @param pagamento
	 * @return
	 */
	private ArrecadadorMovimento pesquisarArrecadadorMovimento(PagamentoHistorico pagamentoHistorico, Pagamento pagamento){
		ArrecadadorMovimento arrecadador = null;
		if(pagamentoHistorico != null){
			if(pagamentoHistorico.getArrecadadorMovimentoItem() != null){
				if(pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento() != null){
					FiltroArrecadadorMovimento filtroArrecadador = new FiltroArrecadadorMovimento();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.SEQUENCIA_ARQUIVO, 
						pagamentoHistorico.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()));
					
					Fachada fachada = Fachada.getInstancia();
					Collection<ArrecadadorMovimento> colArrecadador = fachada.pesquisar(filtroArrecadador, ArrecadadorMovimento.class.getName());
					
					arrecadador = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colArrecadador);
				}
			}
		}else if(pagamento != null){
			if(pagamento.getArrecadadorMovimentoItem() != null){
				if(pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento() != null){
					FiltroArrecadadorMovimento filtroArrecadador = new FiltroArrecadadorMovimento();
					filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadadorMovimento.SEQUENCIA_ARQUIVO, 
							pagamento.getArrecadadorMovimentoItem().getArrecadadorMovimento().getNumeroSequencialArquivo()));
					
					Fachada fachada = Fachada.getInstancia();
					Collection<ArrecadadorMovimento> colArrecadador = fachada.pesquisar(filtroArrecadador, ArrecadadorMovimento.class.getName());
					
					arrecadador = (ArrecadadorMovimento) Util.retonarObjetoDeColecao(colArrecadador);
				}
			}
		}
		
		return arrecadador;
	}
	
}
