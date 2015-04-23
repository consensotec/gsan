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
* Anderson Cabral do Nascimento
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
package gsan.gui.faturamento.conta;

import gsan.arrecadacao.ArrecadadorMovimento;
import gsan.arrecadacao.ArrecadadorMovimentoItem;
import gsan.arrecadacao.ContratoDemanda;
import gsan.arrecadacao.FiltroArrecadadorMovimento;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gsan.arrecadacao.debitoautomatico.FiltroDebitoAutomaticoMovimento;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.arrecadacao.pagamento.PagamentoHistorico;
import gsan.cadastro.imovel.ImovelPerfil;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.DocumentoTipo;
import gsan.fachada.Fachada;
import gsan.faturamento.FiltroContratoDemanda;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.ContaCategoria;
import gsan.faturamento.conta.ContaCategoriaHistorico;
import gsan.faturamento.conta.ContaHistorico;
import gsan.faturamento.conta.ContaImpostosDeduzidos;
import gsan.faturamento.conta.ContaMotivoRevisao;
import gsan.faturamento.conta.FiltroContaCategoria;
import gsan.faturamento.conta.FiltroContaCategoriaHistorico;
import gsan.faturamento.conta.FiltroContaHistorico;
import gsan.faturamento.conta.FiltroContaImpostosDeduzidos;
import gsan.faturamento.debito.DebitoCreditoSituacao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.Operacao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsavel pela exibicao da tela de consultar conta
 * 
 * @author pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class ExibirConsultarContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarConta");

		HttpSession sessao = this.getSessao(httpServletRequest);
		Fachada fachada = Fachada.getInstancia();

		String limparForm = httpServletRequest.getParameter("limparForm");

		// Removendo colecoes da sessao
		if (limparForm != null && !limparForm.equalsIgnoreCase("")) {
			sessao.removeAttribute("colecaoContaImovel");
		}

		String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
		
		sessao.removeAttribute("idContaHistorico");
		sessao.removeAttribute("idConta");
		
		Integer idImovel = null;
		String referenciaConta = null;
		Integer situacaoAtualConta = null;
		
		if (tipoConsulta.equalsIgnoreCase("conta")) {

			String idConta = httpServletRequest.getParameter("contaID");
			
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.CONTA.toString(),idConta);
			String nossoNumero = nossoNumero2.toString();
			
			if(idConta != null && !idConta.equals("")){
				Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(idConta), DocumentoTipo.CONTA);
				if(Util.isVazioOrNulo(colPagamentos)){
					Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(idConta), DocumentoTipo.CONTA);
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

			// vai ser recuperado na geracao do relatorio 2a Via da Conta
			sessao.setAttribute("idConta", idConta);

			Conta conta = null;

			if (idConta != null && !idConta.equalsIgnoreCase("")) {

				// faz a consulta da conta por hql. Fernanda Paiva
				Collection colecaoConta = 
					this.getFachada().consultarConta(new Integer(idConta));

				if (colecaoConta == null || colecaoConta.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.conta.inexistente");
				}

				conta = (Conta) Util.retonarObjetoDeColecao(colecaoConta);

				idImovel = conta.getImovel().getId();
				referenciaConta = conta.getReferenciaFormatada();
				situacaoAtualConta = conta.getDebitoCreditoSituacaoAtual().getId();
				
				/**
				 * [UC0204] - Incluir a descriÃ§Ã£o do perfil do imÃ³vel
				 * 
				 * @author Maxwell Moreira
				 * @date 02/05/2013
				 * 
				 */		        
		        ImovelPerfil imovelPerfil = conta.getImovelPerfil();
		        sessao.setAttribute("imovelPerfil", imovelPerfil);
		        sessao.removeAttribute("imovelPerfilHistorico");
		        
				// Colocando o objeto conta selecionado na sessao
				sessao.setAttribute("conta", conta);
				sessao.removeAttribute("contaHistorico");
				sessao.removeAttribute("colecaoContaCategoriaHistorico");
				
				verificarEmissao2Via(conta.getDebitoCreditoSituacaoAtual().getId(), conta.getDataRevisao(), conta.getContaMotivoRevisao(), conta.getImovel().getId(), httpServletRequest);

			} else if (sessao.getAttribute("conta") != null) {
				conta = (Conta) sessao.getAttribute("conta");
			} else {
				throw new ActionServletException("atencao.pesquisa.conta.inexistente");
			}

			Collection colecaoContaCategoria, colecaoContaImpostosDeduzidos;

			// Removendo colecoes da sessao
			if (idConta != null && !idConta.equalsIgnoreCase("")) {

				sessao.removeAttribute("colecaoContaCategoria");
				sessao.removeAttribute("colecaoContaCategoriaHistorico");
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");
				sessao.removeAttribute("contaImpostosDeduzidos");
				sessao.removeAttribute("dataInscricao");
			}

			/*
			 * Categorias (Carregar colecao)
			 */
			if (sessao.getAttribute("colecaoContaCategoria") == null) {

				FiltroContaCategoria filtroContaCategoria = new FiltroContaCategoria();
				filtroContaCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.conta");
				filtroContaCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.categoria");

				filtroContaCategoria.adicionarParametro(
					new ParametroSimples(FiltroContaCategoria.CONTA_ID, conta.getId()));

				colecaoContaCategoria = 
					this.getFachada().pesquisar(filtroContaCategoria,
						ContaCategoria.class.getName());

				sessao.setAttribute("colecaoContaCategoria",colecaoContaCategoria);
			}
			
			/***DATA INSCRICAO DIVIDA ATIVA**/
			Date dataInscricao = fachada.pesquisarDataInscricaoContaDividaAtiva(Integer.parseInt(idConta));
			if(dataInscricao != null){
				sessao.setAttribute("dataInscricao", Util.formatarData(dataInscricao));
			}
			
			/*
			 * Impostos Deduzidos (Pesquisar o registrode impostos deduzidos)
			 */
			if (sessao.getAttribute("contaImpostosDeduzidos") == null) {

				FiltroContaImpostosDeduzidos filtroContaImpostosDeduzidos = new FiltroContaImpostosDeduzidos();

				filtroContaImpostosDeduzidos.adicionarCaminhoParaCarregamentoEntidade("conta");
				filtroContaImpostosDeduzidos.adicionarCaminhoParaCarregamentoEntidade("impostoTipo");
				filtroContaImpostosDeduzidos.adicionarParametro(new ParametroSimples(FiltroContaImpostosDeduzidos.CONTA_ID, conta.getId()));

				colecaoContaImpostosDeduzidos = 
					this.getFachada().pesquisar(filtroContaImpostosDeduzidos,
						ContaImpostosDeduzidos.class.getName());

				ContaImpostosDeduzidos contaImpostosDeduzidos = (ContaImpostosDeduzidos) Util.retonarObjetoDeColecao(colecaoContaImpostosDeduzidos);

				sessao.setAttribute("contaImpostosDeduzidos",contaImpostosDeduzidos);
				sessao.setAttribute("colecaoContaImpostosDeduzidos",colecaoContaImpostosDeduzidos);
			}
			
			//Pesquisa O Debito Automatico Movimento
			if(conta != null && conta.getIndicadorDebitoConta() != null && 
				conta.getIndicadorDebitoConta().equals(ConstantesSistema.SIM)){
				
				this.pesquisarDebitoAutomaticoMovimento(conta.getId(),httpServletRequest);
			}

		} else if (tipoConsulta.equalsIgnoreCase("contaHistorico")) {

			String idContaHistorico = httpServletRequest.getParameter("contaID");
			
			StringBuilder nossoNumero2 = fachada.obterNossoNumeroFichaCompensacao(DocumentoTipo.CONTA.toString(),idContaHistorico);
			String nossoNumero = nossoNumero2.toString();
			
			if(idContaHistorico != null && !idContaHistorico.equals("")){
				Collection<Pagamento> colPagamentos = fachada.pesquisarPagamentoPeloId(Integer.parseInt(idContaHistorico), DocumentoTipo.CONTA);
				if(Util.isVazioOrNulo(colPagamentos)){
					Collection<PagamentoHistorico> colHistoricoPagamentos = fachada.pesquisarPagamentoHistoricoPeloId(Integer.parseInt(idContaHistorico), DocumentoTipo.CONTA);
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
			
			//vai ser recuperado na geracao do relatorio 2a Via da Conta
			sessao.setAttribute("idContaHistorico",idContaHistorico);

			/*
			 * Pesquisando a conta a partir do id recebido
			 */
			ContaHistorico contaHistorico = null;

			if (idContaHistorico != null && !idContaHistorico.equalsIgnoreCase("")) {
	
				FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
	
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("motivoNaoEntregaDocumento");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("contaMotivoInclusao");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRetificacao");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("contaMotivoCancelamento");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("usuario");
				filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
	
				filtroContaHistorico.adicionarParametro(
					new ParametroSimples(
						FiltroContaHistorico.ID, new Integer(idContaHistorico)));
	
				Collection colecaoContaHistorico = 
					this.getFachada().pesquisar(
						filtroContaHistorico, ContaHistorico.class.getName());
	
				if (colecaoContaHistorico == null || colecaoContaHistorico.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.conta.inexistente");
				}
	
				contaHistorico = (ContaHistorico) Util
						.retonarObjetoDeColecao(colecaoContaHistorico);
	
				idImovel = contaHistorico.getImovel().getId();
				referenciaConta = contaHistorico.getFormatarAnoMesParaMesAno();
				situacaoAtualConta = contaHistorico.getDebitoCreditoSituacaoAtual().getId();
				        
		        /**
				 * [UC0204] - Incluir a descricao do perfil do imovel
				 * 
				 * @author Maxwell Moreira
				 * @date 02/05/2013
				 * 
				 */		        
		        ImovelPerfil imovelPerfil = contaHistorico.getImovelPerfil();
		        sessao.setAttribute("imovelPerfilHistorico", imovelPerfil);
		        sessao.removeAttribute("imovelPerfil");
		        
				// Colocando o objeto conta selecionado na sessao
				sessao.setAttribute("contaHistorico", contaHistorico);
	
				sessao.removeAttribute("conta");
				sessao.removeAttribute("colecaoContaCategoria");
				sessao.removeAttribute("colecaoContaImpostosDeduzidos");
				sessao.removeAttribute("contaImpostosDeduzidos");
				
				sessao.removeAttribute("colecaoContaCategoriaHistorico");
				
				verificarEmissao2Via(contaHistorico.getDebitoCreditoSituacaoAtual().getId(), 
						contaHistorico.getDataRevisao(), contaHistorico.getContaMotivoRevisao(), contaHistorico.getImovel().getId(), httpServletRequest);
	
			} else if (sessao.getAttribute("contaHistorico") != null) {
				contaHistorico = (ContaHistorico) sessao
						.getAttribute("contaHistorico");
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.conta.inexistente");
			}

			/* 
			 * Categorias (Carregar colecao)
			 */
			if (sessao.getAttribute("colecaoContaCategoriaHistorico") == null) {
			 
				FiltroContaCategoriaHistorico filtroContaHistoricoCategoria = new
				FiltroContaCategoriaHistorico();
				
				filtroContaHistoricoCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.contaHistorico");
				filtroContaHistoricoCategoria.adicionarCaminhoParaCarregamentoEntidade("comp_id.categoria");
			  
				filtroContaHistoricoCategoria.adicionarParametro(new
					ParametroSimples(FiltroContaCategoriaHistorico.CONTA_ID,
							contaHistorico.getId()));
			  
				Collection colecaoContaCategoriaHistorico = 
					this.getFachada().pesquisar(filtroContaHistoricoCategoria,
							ContaCategoriaHistorico.class.getName());
			  
				sessao.setAttribute("colecaoContaCategoriaHistorico",colecaoContaCategoriaHistorico); 
			}
			
			//Pesquisa O Debito Automatico Movimento
			if(contaHistorico != null && contaHistorico.getIndicadorDebitoConta() != null && 
				contaHistorico.getIndicadorDebitoConta().equals(ConstantesSistema.SIM)){
				
				this.pesquisarDebitoAutomaticoMovimento(contaHistorico.getId(),httpServletRequest);
			}

		}

		// utilizado para saber se no emitir 2 via de conta, mostrara o codigo
		// de barras ou naum
		if (httpServletRequest.getParameter("contaSemCodigoBarras") != null) {
			httpServletRequest.setAttribute("contaSemCodigoBarras", 1);
		}

		sessao.removeAttribute("contaSemCodigoBarras");
		
		// Verifica se deve ser cobrada taxa por emissao de 2 via
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getValorSegundaVia().equals(new BigDecimal("0.00"))) {
			httpServletRequest.setAttribute("naoCobrarTaxa", true);
		}
			
		// envia uma flag que sera verificado no cliente_resultado_pesquisa.jsp
		// para saber se irao usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest.getParameter("caminhoRetornoTelaConsulta") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaConsultaConta",
				httpServletRequest.getParameter("caminhoRetornoTelaConsulta"));
		}
		
		sessao.removeAttribute("colecaoUsuariosContaRetificada"); 
		sessao.removeAttribute("colecaoUsuariosContaEmRevisao"); 
		sessao.removeAttribute("colecaoUsuariosContaCancelada"); 
		
		
		//adicionado por Vivianne Sousa 18/11/2010 - analista:Adriana Ribeiro
		if(!situacaoAtualConta.equals(DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO)){
			//pesquisar usuarios que retificaram a conta 
			Collection colecaoUsuariosContaRetificada =  fachada.pesquisarUsuario(
					Operacao.OPERACAO_CONTA_RETIFICAR,idImovel,referenciaConta);
			if(colecaoUsuariosContaRetificada != null && !colecaoUsuariosContaRetificada.isEmpty()){
				sessao.setAttribute("colecaoUsuariosContaRetificada",colecaoUsuariosContaRetificada); 
			}
			//pesquisar usuarios que colocaram a conta em revisao 
			Collection colecaoUsuariosContaEmRevisao =  fachada.pesquisarUsuario(
					Operacao.OPERACAO_COLOCAR_CONTA_REVISAO,idImovel,referenciaConta);
			if(colecaoUsuariosContaEmRevisao != null && !colecaoUsuariosContaEmRevisao.isEmpty()){
				sessao.setAttribute("colecaoUsuariosContaEmRevisao",colecaoUsuariosContaEmRevisao); 
			}
			//pesquisar usuarios que cancelaram a conta 
			Collection colecaoUsuariosContaCancelada =  fachada.pesquisarUsuario(
					Operacao.OPERACAO_CANCELAR_CONTA,idImovel,referenciaConta);
			if(colecaoUsuariosContaCancelada != null && !colecaoUsuariosContaCancelada.isEmpty()){
				sessao.setAttribute("colecaoUsuariosContaCancelada",colecaoUsuariosContaCancelada); 
			}
		}
		
		// Adicionado por Mariana Victor 06/01/2011
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
		filtroContratoDemanda.adicionarParametro(
				new ParametroSimples(FiltroContratoDemanda.IMOVEL, idImovel));
		filtroContratoDemanda.adicionarParametro(
				new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
		Collection colecaoContratoDemanda = fachada.pesquisar(
				filtroContratoDemanda, ContratoDemanda.class.getName());
		
		if (colecaoContratoDemanda != null && !colecaoContratoDemanda.isEmpty()) {
			Object[] consumoContratado = fachada.consultarConsumoCadastrado(idImovel);
			if (consumoContratado != null) {
				sessao.setAttribute("consumoContratado", consumoContratado[0]);
			} else {
				sessao.removeAttribute("consumoContratado");
			}
		}
		return retorno;
	}
	
	/**
	 * Verifica se emite 2a via
	 * 
	 * @author Vivianne Sousa
	 * @date 06/07/2007
	 * 
	 * @param situacaoAtualConta
	 * @param dtRevisaoConta
	 */
	private void verificarEmissao2Via(Integer situacaoAtualConta, Date dtRevisaoConta, ContaMotivoRevisao contaMotivoRevisao, Integer idImovel,
			HttpServletRequest httpServletRequest) {

		// Caso o imovel nao tenha sido excluido o usuario tem a opcao de emitir a 2 via
		String inscricao = Fachada.getInstancia().pesquisarInscricaoImovel(idImovel);
		
		if (inscricao != null) {
		
			//Caso a situacao atual da conta corresponda a normal, incluirda ou retificada
			// e a data de revisao esteja nula
			// o usuario tem a opcao de emitir a 2 via
			if (situacaoAtualConta.equals(DebitoCreditoSituacao.NORMAL) || 
				situacaoAtualConta.equals(DebitoCreditoSituacao.RETIFICADA) || 
				situacaoAtualConta.equals(DebitoCreditoSituacao.INCLUIDA)){
			
				if(dtRevisaoConta == null || (contaMotivoRevisao != null && contaMotivoRevisao.getId().equals(ContaMotivoRevisao.REVISAO_ENTRADA_DE_PARCELAMENTO))){
					httpServletRequest.setAttribute("emitirSegundaVia", 2);
				} 
			}
		}
	}
	
	/**
	 * Pesquisa O Debito Automatico Movimento
	 * 
	 * @author Rafael Pinto
	 * @date 09/07/2008
	 * 
	 * @param idConta
	 * @param httpServletRequest
	 */
	private void pesquisarDebitoAutomaticoMovimento(Integer idConta,HttpServletRequest httpServletRequest){
		
		FiltroDebitoAutomaticoMovimento filtroDebitoAutomaticoMovimento = new FiltroDebitoAutomaticoMovimento();

		filtroDebitoAutomaticoMovimento.adicionarCaminhoParaCarregamentoEntidade("debitoAutomaticoRetornoCodigo");
		filtroDebitoAutomaticoMovimento.adicionarParametro(
			new ParametroSimples(FiltroDebitoAutomaticoMovimento.CONTA_GERAL_ID, idConta));

		Collection colecaoDebitoAutomaticoMovimento = 
			this.getFachada().pesquisar(filtroDebitoAutomaticoMovimento,
					DebitoAutomaticoMovimento.class.getName());

		DebitoAutomaticoMovimento debitoAutomaticoMovimento = 
			(DebitoAutomaticoMovimento) Util.retonarObjetoDeColecao(colecaoDebitoAutomaticoMovimento);
		
		httpServletRequest.setAttribute("debitoAutomaticoMovimento",debitoAutomaticoMovimento);
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