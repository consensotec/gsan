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
package gsan.gui.relatorio.cobranca.parcelamento;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.Categoria;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.ResolucaoDiretoria;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gsan.cobranca.bean.GuiaPagamentoValoresHelper;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoTipo;
import gsan.financeiro.FinanciamentoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.portal.caema.EfetuarParcelamentoDebitosPortalCaemaActionForm;
import gsan.gui.portal.caer.EfetuarParcelamentoDebitosPortalCaerActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gsan.relatorio.cobranca.parcelamento.RelatorioExtratoDebito;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * [UC0444] Gerar e Emitir Extrato de D�bito
 * @author Vivianne Sousa
 * @date 07/09/2006
 */

public class GerarRelatorioExtratoDebitoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
		//Linha 2
		String inscricao = "";    	
		String nomeUsuario = "";         
		String matricula = ""; 	
		String cpfCnpj = "";
		
		//Linha 3
		String enderecoImovel = "";

		Imovel imovel = null;
		Collection<ContaValoresHelper> colecaoContas =  null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;
		Collection<DebitoACobrar> colecaoDebitosACobrar = null;
		Collection<CreditoARealizar> colecaoCreditoARealizar = null;
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento = null;
		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento = null;
		BigDecimal valorAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal valorDocumento = new BigDecimal("0.00");
		BigDecimal valorDesconto =  new BigDecimal("0.00");
		BigDecimal valorDescontoCredito =  new BigDecimal("0.00");
		Short indicadorGeracaoTaxaCobranca = new Short("2") ;  // no caso do parcelamento sempre 2
		
		BigDecimal parcelamentoValorDebitoACobrarServico = null;
		BigDecimal parcelamentoValorDebitoACobrarParcelamento = null;
		
		Short indicadorContasRevisao = 2; 
		
		ResolucaoDiretoria resolucaoDiretoria = null;
		Integer idParcelamento = null;
		
		if(httpServletRequest.getParameter("extratoDebito") != null){
			//relatorio chamado a partir da tela de extrato de debito
			Integer idImovel = new Integer((String)sessao.getAttribute("idImovelExtrato"));
			imovel = fachada.pesquisarImovel(idImovel);
			
			colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContasExtrato");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiasPagamentoExtrato");
			colecaoDebitosACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitosACobrarExtrato");
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizarExtrato");
			colecaoAntecipacaoDebitosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) sessao.getAttribute("colecaoAntecipacaoDebitosDeParcelamento");
        	colecaoAntecipacaoCreditosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) sessao.getAttribute("colecaoAntecipacaoCreditosDeParcelamento");
			valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidadeExtrato");
			valorDocumento = (BigDecimal) sessao.getAttribute("valorDocumentoExtrato");
			valorDesconto =  (BigDecimal) sessao.getAttribute("valorDescontoExtrato");
			valorDescontoCredito = (BigDecimal)sessao.getAttribute("valorCreditoARealizar");
			//Linha 2
			 inscricao = imovel.getInscricaoFormatada();    	
			 nomeUsuario = (String)sessao.getAttribute("nomeClienteExtrato");   
			 matricula = imovel.getId().toString(); 	
			 cpfCnpj = (String)sessao.getAttribute("cpfCnpj");
			 
	
			 //Linha 3
			 try {
				enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
			} catch (ControladorException e) {
				e.printStackTrace();
			}
			
			 if (sessao.getAttribute("indicadorContasRevisao") != null){
				 indicadorContasRevisao = (Short)sessao.getAttribute("indicadorContasRevisao");
			 }
			
		}else if (httpServletRequest.getParameter("parcelamento") != null){
			//PAGAMENTO � VISTA
			//relatorio chamado a partir da tela de efetuar parcelamento
			
			if (httpServletRequest.getParameter("RD") != null){
				resolucaoDiretoria = new ResolucaoDiretoria();
				resolucaoDiretoria.setId(new Integer(httpServletRequest.getParameter("RD")));
			}
			
			//Verifica se a aba 3 � chamada pela aba 2(colecaoContaValores) ou pela aba 1(colecaoContaValoresImovel)
			if (sessao.getAttribute("colecaoContaValoresSemContasNB") != null){
				//efetuar parcelamento (Pagamento a vista)
				colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValoresSemContasNB");
				colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
				valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidade");
				colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
				
				BigDecimal valorAcrescimosNB = new BigDecimal("0.00");
				if(sessao.getAttribute("valorAcrescimosNB") != null){
					valorAcrescimosNB = (BigDecimal)sessao.getAttribute("valorAcrescimosNB");
				}
				valorAcrescimosImpontualidade = valorAcrescimosImpontualidade.subtract(valorAcrescimosNB);
				
			}else if (sessao.getAttribute("colecaoContaValoresNegociacao") != null 
					|| (sessao.getAttribute("colecaoContaValoresNegociacao") != null)){
				//efetuar parcelamento (Pagamento a vista)			
				colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValoresNegociacao");
				colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoNegociacao");
				valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidadeNegociacao");
				colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
				
			}else if(sessao.getAttribute("colecaoContaValores") != null ||
					sessao.getAttribute("colecaoGuiaPagamentoValores") != null){
				colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
				colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
				valorAcrescimosImpontualidade = (BigDecimal) sessao.getAttribute("valorAcrescimosImpontualidade");
				colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
				
			}
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
			colecaoDebitosACobrar = obterColecaoDebitosACobrarDoParcelamento(colecaoDebitosACobrar);
			
			String parcelamentoPortal = httpServletRequest.getParameter("parcelamentoPortal");
			if(Util.verificarNaoVazio(parcelamentoPortal) && parcelamentoPortal.equalsIgnoreCase("sim")){
				EfetuarParcelamentoDebitosPortalCaemaActionForm form = (EfetuarParcelamentoDebitosPortalCaemaActionForm) sessao.getAttribute("formParcelamento");
				valorDocumento = Util.formatarMoedaRealparaBigDecimal(form.getValorPagamentoAVista());
				valorDesconto = Util.formatarMoedaRealparaBigDecimal(form.getValorDescontoPagamentoAVista());
				valorDescontoCredito = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditoARealizar());
				parcelamentoValorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServico());
				parcelamentoValorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamento());
				
				//Linha 2
				inscricao = form.getInscricaoImovel();
				nomeUsuario = form.getNomeCliente();
				matricula = form.getMatriculaImovel();
				cpfCnpj = form.getCpfCliente();
				enderecoImovel = form.getEnderecoImovel();
			}else if(Util.verificarNaoVazio(parcelamentoPortal) && parcelamentoPortal.equalsIgnoreCase("CAER")){
				EfetuarParcelamentoDebitosPortalCaerActionForm form = (EfetuarParcelamentoDebitosPortalCaerActionForm) sessao.getAttribute("formParcelamento");
				valorDocumento = Util.formatarMoedaRealparaBigDecimal(form.getValorPagamentoAVista());
				valorDesconto = Util.formatarMoedaRealparaBigDecimal(form.getValorDescontoPagamentoAVista());
				valorDescontoCredito = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditoARealizar());
				parcelamentoValorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServico());
				parcelamentoValorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamento());
				
				//Linha 2
				inscricao = form.getInscricaoImovel();
				nomeUsuario = form.getNomeCliente();
				matricula = form.getMatriculaImovel();
				cpfCnpj = form.getCpfCliente();
				enderecoImovel = form.getEnderecoImovel();
			}else{
				DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
				valorDocumento = Util.formatarMoedaRealparaBigDecimal(efetuarParcelamentoDebitosActionForm.get("valorPagamentoAVista").toString());
				valorDesconto =  Util.formatarMoedaRealparaBigDecimal(efetuarParcelamentoDebitosActionForm.get("valorDescontoPagamentoAVista").toString());
				valorDescontoCredito =  Util.formatarMoedaRealparaBigDecimal(efetuarParcelamentoDebitosActionForm.get("valorCreditoARealizar").toString());
				parcelamentoValorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarServico").toString());
				parcelamentoValorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal(efetuarParcelamentoDebitosActionForm.get("valorDebitoACobrarParcelamento").toString());
				
				//Linha 2
				inscricao = (String)efetuarParcelamentoDebitosActionForm.get("inscricaoImovel");    	
				nomeUsuario = (String)efetuarParcelamentoDebitosActionForm.get("nomeCliente");   
				matricula = (String)efetuarParcelamentoDebitosActionForm.get("matriculaImovel"); 	
				cpfCnpj = (String)efetuarParcelamentoDebitosActionForm.get("cpfCnpj");
				//Linha 3
				enderecoImovel = (String)efetuarParcelamentoDebitosActionForm.get("endereco");
			}
			imovel = (Imovel)sessao.getAttribute("imovel");
			 if (httpServletRequest.getParameter("indicadorContasRevisao") != null){
				 indicadorContasRevisao = new Short(httpServletRequest.getParameter("indicadorContasRevisao"));
			 }
			 
		}else if (httpServletRequest.getParameter("idParcelamento") != null){
			//EMISS�O DA ENTRADA DO PARCELAMENTO - APENAS CONTAS EP
			 
			idParcelamento = new Integer((String)httpServletRequest.getParameter("idParcelamento").trim());
			
		}else if (httpServletRequest.getParameter("consultarDebito") != null){
			
			//Consultar D�bito por Im�vel 

			colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
			colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
			
			
			Integer idImovel = new Integer((String)sessao.getAttribute("idImovel"));
			imovel = fachada.pesquisarImovel(idImovel);
			
			
			valorDescontoCredito = (BigDecimal)sessao.getAttribute("valorCreditoARealizar");
			valorDesconto = (BigDecimal)sessao.getAttribute("valorTotalDescontoPagamentoAVista");
			valorDocumento = (BigDecimal)sessao.getAttribute("valorPagamentoAVista");
			
			String valor = (String) sessao.getAttribute("valorAcrescimo");
			if(valor != null && !valor.equals("")){
				valor = valor.replaceAll("[.]","").replaceAll("[,]", "");
				valorAcrescimosImpontualidade = new BigDecimal(valor);	
			}
			
			inscricao = imovel.getInscricaoFormatada();
			matricula = idImovel.toString();
			
			colecaoDebitosACobrar = obterColecaoDebitosACobrarSemJurosParcelamento(colecaoDebitosACobrar);

			
			//Filtro para recuperar informa��o do cliente relacionado com o im�vel
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

			Collection<ClienteImovel> clientesImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

			Cliente cliente = clientesImovel.iterator().next().getCliente();

			if (cliente != null) {
				nomeUsuario = cliente.getNome();
				if ( cliente.getCpf()!= null ) {
					cpfCnpj = cliente.getCpfFormatado();
				} else if ( cliente.getCnpj() != null ) {
					cpfCnpj = cliente.getCnpjFormatado();
				}
			}
			
			try {
				enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
			} catch (ControladorException e) {
				e.printStackTrace();
			}
			
			
			 if (sessao.getAttribute("indicadorContasRevisao") != null){
				 indicadorContasRevisao = (Short)sessao.getAttribute("indicadorContasRevisao");
			 }
			
			
		}else{
			//Consultar Imovel aba de D�bito 
			colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
			colecaoDebitosACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
			colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores");
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
			
			Integer idImovel = new Integer((String)sessao.getAttribute("idImovelPrincipalAba"));
			imovel = fachada.pesquisarImovel(idImovel);
			
			valorDesconto = (BigDecimal)sessao.getAttribute("valorTotalDescontoPagamentoAVista");
			valorDocumento = (BigDecimal)sessao.getAttribute("valorPagamentoAVista");
			
			valorDescontoCredito = Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorCreditoARealizar"));
			
			if(valorDocumento == null){
				valorDocumento = Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorToralSemAcrescimoESemJurosParcelamento"));
	            
	            // descomentado por Vivianne Sousa
	            // analista: Aryed
	            // 27/02/2008
				valorDocumento = valorDocumento.subtract(valorDescontoCredito);
			}else{
				valorAcrescimosImpontualidade =  Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorAcrescimo")); 
			}
			
//			valorDesconto = Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorCreditoARealizar"));
			
			inscricao = imovel.getInscricaoFormatada();
			matricula = idImovel.toString();
			
			colecaoDebitosACobrar = obterColecaoDebitosACobrarSemJurosParcelamento(colecaoDebitosACobrar);
			
			//Filtro para recuperar informa��o do cliente relacionado com o im�vel
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO,ClienteRelacaoTipo.USUARIO));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

			Collection<ClienteImovel> clientesImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

			Cliente cliente = clientesImovel.iterator().next().getCliente();

			if (cliente != null) {
				nomeUsuario = cliente.getNome();
				if ( cliente.getCpf()!= null ) {
					cpfCnpj = cliente.getCpfFormatado();
				} else if ( cliente.getCnpj() != null ) {
					cpfCnpj = cliente.getCnpjFormatado();
				}
			}
			
				try {
					enderecoImovel = fachada.pesquisarEnderecoFormatado(idImovel);
				} catch (ControladorException e) {
					e.printStackTrace();
				}
			
			 if (sessao.getAttribute("indicadorContasRevisao") != null){
				 indicadorContasRevisao = (Short)sessao.getAttribute("indicadorContasRevisao");
			 }
				
		}
		
		String nomeDocumento = "EXTRATO DE D�BITO";
		Date maiorDataVencimentoContas = null;
		String dataValidade = null;
		ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper = null;
		if(idParcelamento != null){
			//RM3453 - adicionado por Vivianne Sousa - 06/07/2012
			//REEMISS�O DA ENTRADA DO PARCELAMENTO - APENAS CONTAS
			extratoDebitoRelatorioHelper = fachada.pesquisarCobrancaDocumentoEmitirEntradaParcelamento(idParcelamento);
			
			CobrancaDocumento documentoCobranca = extratoDebitoRelatorioHelper.getDocumentoCobranca();
			
			if(Util.compararData(new Date(), documentoCobranca.getDataValidade()) == 1){
				//Entrada perdeu validade
				throw new ActionServletException("atencao.entrada_perdeu_validade");
			}
			nomeDocumento = "EXTRATO DE D�BITO COM DESCONTO - ENTRADA DE PARCELAMENTO";
			dataValidade = Util.formatarData(documentoCobranca.getDataValidade());
			imovel = documentoCobranca.getImovel();
			
			if(documentoCobranca.getValorAcrescimos() != null){
				valorAcrescimosImpontualidade = documentoCobranca.getValorAcrescimos();
			}
			if(documentoCobranca.getValorDesconto() != null){
				valorDesconto = documentoCobranca.getValorDesconto();
			}
			if(documentoCobranca.getValorDocumento() != null){
				valorDocumento = documentoCobranca.getValorDocumento();
			}
		
			matricula = imovel.getId().toString();
			inscricao = fachada.pesquisarInscricaoImovel(imovel.getId());
			try {
				enderecoImovel = fachada.pesquisarEnderecoFormatado(imovel.getId());
			} catch (ControladorException e) {
				e.printStackTrace();
			}
			
			Cliente cliente = fachada.pesquisarClienteDoParcelamento(idParcelamento);
			nomeUsuario = cliente.getNome();
			if(cliente.getCpf() != null){
				cpfCnpj = cliente.getCpfFormatado();
			}else if(cliente.getCnpj() != null){
				cpfCnpj = cliente.getCnpjFormatado();
			}
			
		}else{
			
			/*
			 * --Erivan Sousa--
			 * Impede que o extrato seja emitido caso o indicador cbsp_icemitedoccobranca 
			 * da tabela cobranca.cobranca_situacao_tipo seja igual a 2
			 */
			
			if(imovel.getCobrancaSituacaoTipo() != null){
				if(imovel.getCobrancaSituacaoTipo().getIndicadorEmitirDocumentoCobranca().equals(ConstantesSistema.NAO)){
					throw new  ActionServletException("atencao.extratonaoemitido_imovel_situacaoespecial");
				}
			}

			if (valorAcrescimosImpontualidade == null){
				valorAcrescimosImpontualidade = BigDecimal.ZERO;
			}
			
			if (valorDocumento.compareTo(new BigDecimal("0.00")) < 0){
				throw new ActionServletException("atencao.resultado.extrato.negativo");
			}

			if(valorDesconto == null){
				valorDesconto = new BigDecimal("0.00");
			}
			
			//Vivianne Sousa 11/12/2008 analista:Adriano Britto
			//verifica se as contas em revis�o v�o aparecer no extrato de debito
			//na tela de parcelamento o usuario tem a op��o de escolher 
			//se as contas em revis�o entram no parcelamento
			if(indicadorContasRevisao.equals(ConstantesSistema.NAO)){
				//Vivianne Sousa - 02/07/2008
				Map mapContas =  retirarContasEmRevisaoDeColecaoContas(colecaoContas);
				colecaoContas =  (Collection)mapContas.get("colecaoContasSemContasEmRevisao");
				BigDecimal valorTotalContasRevisao = (BigDecimal) mapContas.get("valorTotalContasRevisao");
				valorDocumento = valorDocumento.subtract(valorTotalContasRevisao);
				maiorDataVencimentoContas = (Date)mapContas.get("maiorDataVencimentoContas");
			}else{
				maiorDataVencimentoContas = obterMaiorDataVencimentoContas(colecaoContas);
			}
			
			//Carlos Chaves 25/06/2012 analista:Claudio Lira
			//Retira do extrato de debito as contas pertencente ao programa viva agua.
			Map mapContas =  retirarContasEmProgramaEspecialDeColecaoContas(colecaoContas);
			colecaoContas =  (Collection)mapContas.get("colecaoContasSemProgramaEspecial");
			BigDecimal valorTotalContasProgramaEspecial = (BigDecimal) mapContas.get("valorTotalContasProgramaEspecial");
			valorDocumento = valorDocumento.subtract(valorTotalContasProgramaEspecial);
			
			extratoDebitoRelatorioHelper = fachada.gerarEmitirExtratoDebito(
			imovel,indicadorGeracaoTaxaCobranca,colecaoContas,colecaoGuiasPagamento,colecaoDebitosACobrar,
			valorAcrescimosImpontualidade,valorDesconto,valorDocumento, 
			colecaoCreditoARealizar, null, resolucaoDiretoria, colecaoAntecipacaoDebitosDeParcelamento,
			colecaoAntecipacaoCreditosDeParcelamento,null,null, "RelatorioExtratoDebito");
			
		}

		CobrancaDocumento documentoCobranca = extratoDebitoRelatorioHelper.getDocumentoCobranca();
		
		documentoCobranca.setUsuario(usuario);
		
		fachada.atualizar(documentoCobranca);
		
		//Linha 1
		String nomeLocalidade = documentoCobranca.getLocalidade().getDescricao();

		//Linha 3
		String seqDocCobranca = "" + documentoCobranca.getNumeroSequenciaDocumento();						
				
		//Linha 4		
		String situacaoAgua = "" + documentoCobranca.getImovel().getLigacaoAguaSituacao().getId();//efetuarParcelamentoDebitosActionForm.get("situacaoAgua");	
		String situacaoEsgoto = "" + documentoCobranca.getImovel().getLigacaoEsgotoSituacao().getId();//(String)efetuarParcelamentoDebitosActionForm.get("situacaoEsgoto");	
		String qtdResidencial = "";
		String qtdComercial = "";
		String qtdIndustrial = "";
		String qtdPublico = "";
		String descPerfilImovel = "" + documentoCobranca.getImovelPerfil().getDescricao();	
		String dataEmissao = Util.formatarData(documentoCobranca.getEmissao());

//		String dataValidade = Util.formatarData(documentoCobranca.getDataValidade(usuario));
//		Vivianne Sousa 15/09/2008
		if(dataValidade == null){
			dataValidade = Util.formatarData(fachada.
					obterDataValidadeDocumentoCobranca(documentoCobranca, usuario,maiorDataVencimentoContas));
		}
	
		//Obter Quantidade de economias por categoria
		Collection colecaoCategorias = fachada.obterQuantidadeEconomiasCategoria(imovel);	
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
			Iterator iteratorColecaoCategorias = colecaoCategorias.iterator();
			Categoria categoria = null;

			while (iteratorColecaoCategorias.hasNext()) {
				categoria = (Categoria) iteratorColecaoCategorias.next();

				if (categoria.getId().equals(Categoria.RESIDENCIAL)) {
					qtdResidencial = "" + categoria.getQuantidadeEconomiasCategoria();
				} else if (categoria.getId().equals(Categoria.COMERCIAL)) {
					qtdComercial = "" + categoria.getQuantidadeEconomiasCategoria();
				} else if (categoria.getId().equals(Categoria.INDUSTRIAL)) {
					qtdIndustrial = "" + categoria.getQuantidadeEconomiasCategoria();
				} else if (categoria.getId().equals(Categoria.PUBLICO)) {
					qtdPublico = "" + categoria.getQuantidadeEconomiasCategoria();
				}
			}
		}
		
		// linhas 7 , 8, 9 e 10
		BigDecimal valorTotalContas = new BigDecimal("0.00") ;
		BigDecimal valorServicosAtualizacoes = new BigDecimal("0.00");
		
		String valorTotalContasString = "" ;
		String valorServicosAtualizacoesString = "";
		String valorDescontoString =  ""; 
		String valorTotalComDescontoString = "";
		
		
		valorTotalContas = extratoDebitoRelatorioHelper.getValorTotalConta();
		valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		//valor dos acrescimos por impontualidade +
		//valor total das guias de pagamento +
		//valor total restante dos debitos a cobrar 
		
		BigDecimal valorTotalRestanteDebitosACobrar = new BigDecimal("0.00");
		
		if (parcelamentoValorDebitoACobrarServico != null && parcelamentoValorDebitoACobrarParcelamento != null){
			valorTotalRestanteDebitosACobrar = parcelamentoValorDebitoACobrarServico.add(parcelamentoValorDebitoACobrarParcelamento);
		}else{
			valorTotalRestanteDebitosACobrar = extratoDebitoRelatorioHelper.getValorTotalRestanteDebitosACobrar();
		}
		
		valorServicosAtualizacoes = valorAcrescimosImpontualidade.add(
				extratoDebitoRelatorioHelper.getValorTotalGuiaPagamento().add(valorTotalRestanteDebitosACobrar));
		
		 valorTotalContasString = Util.formatarMoedaReal(valorTotalContas);
		 valorServicosAtualizacoesString = Util.formatarMoedaReal(valorServicosAtualizacoes);
		 
		 valorDescontoCredito  = valorDescontoCredito.add(valorDesconto);
		 valorDescontoString = Util.formatarMoedaReal(valorDescontoCredito);
		 
		 valorTotalComDescontoString = Util.formatarMoedaReal(valorDocumento); 
		
		// Parte que vai mandar o relat�rio para a tela
		// cria uma inst�ncia da classe do relat�rio
		 RelatorioExtratoDebito relatorioExtratoDebito = new RelatorioExtratoDebito((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		//Linha 1
		 relatorioExtratoDebito.addParametro("nomeDocumento", nomeDocumento);
		 relatorioExtratoDebito.addParametro("nomeLocalidade",nomeLocalidade);
		
		//Linha 2
		 relatorioExtratoDebito.addParametro("inscricao",inscricao);
		 relatorioExtratoDebito.addParametro("nomeUsuario",nomeUsuario);
		 relatorioExtratoDebito.addParametro("matricula",matricula);
		 relatorioExtratoDebito.addParametro("cpfCnpj", cpfCnpj);
		
		//Linha 3
		 relatorioExtratoDebito.addParametro("enderecoImovel",enderecoImovel);
		 relatorioExtratoDebito.addParametro("seqDocCobranca",seqDocCobranca);
		
		//Linha 4
		 relatorioExtratoDebito.addParametro("situacaoAgua",situacaoAgua);
		 relatorioExtratoDebito.addParametro("situacaoEsgoto",situacaoEsgoto);
		 relatorioExtratoDebito.addParametro("qtdResidencial",qtdResidencial);
		 relatorioExtratoDebito.addParametro("qtdComercial",qtdComercial);
		 relatorioExtratoDebito.addParametro("qtdIndustrial",qtdIndustrial);
		 relatorioExtratoDebito.addParametro("qtdPublico",qtdPublico);
		 relatorioExtratoDebito.addParametro("descPerfilImovel",descPerfilImovel);
		 relatorioExtratoDebito.addParametro("dataEmissao",dataEmissao);
		 relatorioExtratoDebito.addParametro("dataValidade",dataValidade);
		
		//linhas 7 , 8, 9 e 10
		 relatorioExtratoDebito.addParametro("valorTotalContas",valorTotalContasString);
		 relatorioExtratoDebito.addParametro("valorServicosAtualizacoes",valorServicosAtualizacoesString);
		 relatorioExtratoDebito.addParametro("valorDesconto",valorDescontoString);
		 relatorioExtratoDebito.addParametro("valorTotalComDesconto",valorTotalComDescontoString);
		 relatorioExtratoDebito.addParametro("imovel", imovel);

		//Linha 11
		//CRC0959 - Vivianne Sousa - 08/09/2010 - analista:Fatima Sampaio
		
		if(extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento()!= null 
			&& sistemaParametro.getValorExtratoFichaComp() != null
			&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
			&& extratoDebitoRelatorioHelper.getDocumentoCobranca().getValorDocumento().
				compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
			 
			 	//representa��o num�rica do c�digo de barras
				//[SB0010] Obter Representa��o num�rica do Nosso N�mero da Ficha de Compensa��o
				StringBuilder nossoNumero = fachada.obterNossoNumeroFichaCompensacao(
						DocumentoTipo.EXTRATO_DE_DEBITO.toString(),documentoCobranca.getId().toString()) ;
				String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
				relatorioExtratoDebito.addParametro("nossoNumero",nossoNumero.toString());
				
				Date dataVencimentoMais75 = Util.adicionarNumeroDiasDeUmaData(new Date(),75);
				String fatorVencimento = fachada.obterFatorVencimento(dataVencimentoMais75);
				
				String especificacaoCodigoBarra = fachada.
					obterEspecificacaoCodigoBarraFichaCompensacao(
				    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, 
				    ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
				    documentoCobranca.getValorDocumento(), nossoNumeroSemDV.toString(),
					ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
				                                
				String representacaoNumericaCodigoBarraFichaCompensacao = 
				fachada.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
				
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarraSemDigito",especificacaoCodigoBarra);
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarra",representacaoNumericaCodigoBarraFichaCompensacao);
				
		 }else{
			 
			 
				String representacaoNumericaCodBarra = "";
				//[UC0229] Obt�m a representa��o num�rica do c�digo de barra
				
				representacaoNumericaCodBarra = fachada
							.obterRepresentacaoNumericaCodigoBarra(
									5,
		                            valorDocumento,
									documentoCobranca.getLocalidade().getId(),
									imovel.getId(),
									null,
									null,
									null,
									null,
									seqDocCobranca,
									DocumentoTipo.EXTRATO_DE_DEBITO,
									null, null,null);
				
				
				//Formata a representa��o n�merica do c�digo de barras
				String representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarra
						.substring(0, 11)
						+ "-"
						+ representacaoNumericaCodBarra.substring(11, 12)
						+ " "
						+ representacaoNumericaCodBarra.substring(12, 23)
						+ "-"
						+ representacaoNumericaCodBarra.substring(23, 24)
						+ " "
						+ representacaoNumericaCodBarra.substring(24, 35)
						+ "-"
						+ representacaoNumericaCodBarra.substring(35, 36)
						+ " "
						+ representacaoNumericaCodBarra.substring(36, 47)
						+ "-"
						+ representacaoNumericaCodBarra.substring(47, 48);
				
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarra",representacaoNumericaCodBarraFormatada);
				
				String representacaoNumericaCodBarraSemDigito = 
					representacaoNumericaCodBarra.substring(0, 11)
					+ representacaoNumericaCodBarra.substring(12, 23)
					+ representacaoNumericaCodBarra.substring(24, 35)
					+ representacaoNumericaCodBarra.substring(36, 47);
				
				relatorioExtratoDebito.addParametro("representacaoNumericaCodBarraSemDigito",representacaoNumericaCodBarraSemDigito);
			 
		 }
		 
		
		
		relatorioExtratoDebito.addParametro("valorAcrescimosImpontualidade",valorAcrescimosImpontualidade);
		relatorioExtratoDebito.addParametro("extratoDebitoRelatorioHelper",extratoDebitoRelatorioHelper);
		
		String codigoRotaESequencialRota = fachada.obterRotaESequencialRotaDoImovel(imovel.getId());
		relatorioExtratoDebito.addParametro("codigoRotaESequencialRota", codigoRotaESequencialRota);
		
		//String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		//if (tipoRelatorio == null) {
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		//}

		relatorioExtratoDebito.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioExtratoDebito,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}


		return retorno;
	}

	private Collection obterColecaoDebitosACobrarDoParcelamento(Collection colecaoDebitosACobrar){
		
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = new ArrayList();
		
		if (colecaoDebitosACobrar != null && !colecaoDebitosACobrar.isEmpty()) {
			Iterator debitoACobrarValores = colecaoDebitosACobrar.iterator();

			while (debitoACobrarValores.hasNext()) {
				DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
				
				//Verificar exist�ncia de juros sobre im�vel
				if(debitoACobrar.getDebitoTipo().getId() != null && !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					
					// Debitos A Cobrar - Servi�o
					if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
					
						colecaoDebitosACobrarParcelamento.add(debitoACobrar);
					}

					// Debitos A Cobrar - Parcelamento
					if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
						|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
						|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
					
						colecaoDebitosACobrarParcelamento.add(debitoACobrar);
					}
				}
			}
			
		}
		
		
		
		return colecaoDebitosACobrarParcelamento;
	}
	
	
	private Collection obterColecaoDebitosACobrarSemJurosParcelamento(Collection colecaoDebitosACobrar){
		
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = new ArrayList();
		
		if (colecaoDebitosACobrar != null && !colecaoDebitosACobrar.isEmpty()) {
			Iterator debitoACobrarValores = colecaoDebitosACobrar.iterator();

			while (debitoACobrarValores.hasNext()) {
				DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
				
				//Verificar exist�ncia de juros sobre im�vel
				if(debitoACobrar.getDebitoTipo().getId() != null && 
						!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					colecaoDebitosACobrarParcelamento.add(debitoACobrar);
				}
			}
			
		}
		
		return colecaoDebitosACobrarParcelamento;
	}
	
	
	//Vivianne Sousa - 02/07/2008
	//retornaa colecao de contas som as contas em revis�o e 
	//o valor total das contas em revis�o para diminuir do valor do documento 
	private Map retirarContasEmRevisaoDeColecaoContas(Collection<ContaValoresHelper> colecaoContas){
		
        Map retorno = new HashMap();
		BigDecimal valorTotalContasRevisao =  BigDecimal.ZERO;
		Collection<ContaValoresHelper> colecaoContasSemContasEmRevisao = new ArrayList<ContaValoresHelper>();
		Date maiorDataVencimentoContas = Util.converteStringParaDate("01/01/0001");
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator iter = colecaoContas.iterator();

		
			while (iter.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter.next();
				Conta conta = contaValoresHelper.getConta();
				
				if (conta.getContaMotivoRevisao() != null){
					
					valorTotalContasRevisao = valorTotalContasRevisao.
						add(contaValoresHelper.getValorTotalConta());
					
				}else{
					colecaoContasSemContasEmRevisao.add(contaValoresHelper);

					if(Util.compararData(conta.getDataVencimentoConta(),maiorDataVencimentoContas) == 1){
						maiorDataVencimentoContas = conta.getDataVencimentoConta();
					}
				}

			}
			
		}
		
		retorno.put("colecaoContasSemContasEmRevisao",colecaoContasSemContasEmRevisao);
		retorno.put("valorTotalContasRevisao",valorTotalContasRevisao);
		retorno.put("maiorDataVencimentoContas",maiorDataVencimentoContas);
		
		return retorno;
	}
	
	//Carlos Chaves - 21/06/2012
	//retornaa colecao de contas com as contas com perfil = iper_idprogramaespecial de sistema parametro
	//o valor total das contas em revis�o para diminuir do valor do documento 
	private Map retirarContasEmProgramaEspecialDeColecaoContas(Collection<ContaValoresHelper> colecaoContas){
		
        Map retorno = new HashMap();
		BigDecimal valorTotalContasProgramaEspecial =  BigDecimal.ZERO;
		Collection<ContaValoresHelper> colecaoContasSemProgramaEspecial = new ArrayList<ContaValoresHelper>();
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			
			Fachada fachada = Fachada.getInstancia();
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			Iterator iter = colecaoContas.iterator();

			while (iter.hasNext()) {
				
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter.next();
				Conta conta = contaValoresHelper.getConta();
				
				if(sistemaParametro.getPerfilProgramaEspecial() != null 
						&& sistemaParametro.getPerfilProgramaEspecial().getId() != null){
					
					if(conta.getImovelPerfil().getId().equals(sistemaParametro.getPerfilProgramaEspecial().getId())){
						valorTotalContasProgramaEspecial = valorTotalContasProgramaEspecial.add(contaValoresHelper.getValorTotalConta());
					}else{
						colecaoContasSemProgramaEspecial.add(contaValoresHelper);
					}
					
				}else{
					colecaoContasSemProgramaEspecial.add(contaValoresHelper);
				}
				

			}
			
		}
		
		retorno.put("colecaoContasSemProgramaEspecial",colecaoContasSemProgramaEspecial);
		retorno.put("valorTotalContasProgramaEspecial",valorTotalContasProgramaEspecial);
		
		return retorno;
	}
	
	
	//Vivianne Sousa - 16/04/2009 
	//retorna a maior Data de Vencimento da cole��o de Contas
	private Date obterMaiorDataVencimentoContas(Collection<ContaValoresHelper> colecaoContas){
		
		Date maiorDataVencimentoContas = Util.converteStringParaDate("01/01/0001");
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator iter = colecaoContas.iterator();

			while (iter.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iter.next();
				Conta conta = contaValoresHelper.getConta();
				
				if(Util.compararData(conta.getDataVencimentoConta(),maiorDataVencimentoContas) == 1){
					maiorDataVencimentoContas = conta.getDataVencimentoConta();
				}
			}	
		}

		return maiorDataVencimentoContas;
		
	}
	
	
	
//	private Collection obterColecaoCreditoARealizarSemDescontoParcelamento(Collection colecaoCreditoARealizar){
//		
//		Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento = new ArrayList();
//		
//		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
//			Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
//
//			while (creditoARealizarValores.hasNext()) {
//				CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();
//				
//				
//				if(creditoARealizar.getCreditoOrigem() != null && 
//						!creditoARealizar.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
//					colecaoCreditoARealizarParcelamento.add(creditoARealizar);
//				}
//			}
//			
//		}
//		
//		return colecaoCreditoARealizarParcelamento;
//	}

	
	
	/*private Object[] obterContasSelecionadas(String idsContas, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<ContaValoresHelper> colecaoContas = null;
		BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalAcrescimoImpontualidade = BigDecimal.ZERO;
		
		if (idsContas != null && !idsContas.equals("")){
			retorno = new Object[3];
			colecaoContas = new ArrayList();
			
			Collection colecaoContasSessao = (Collection) sessao.getAttribute("colecaoConta");
			Iterator itColecaoContasSessao = colecaoContasSessao.iterator();
			ContaValoresHelper contaValoresHelper = null;
			
			String[] idsContasArray = idsContas.split(",");
			
			while (itColecaoContasSessao.hasNext()){
				
				contaValoresHelper = (ContaValoresHelper) itColecaoContasSessao.next();
				
				for(int x=0; x<idsContasArray.length; x++){
					
					if (contaValoresHelper.getConta().getId().equals(new Integer(idsContasArray[x]))){
						colecaoContas.add(contaValoresHelper);
						valorTotalConta = valorTotalConta.add(contaValoresHelper.getValorTotalConta());
						valorTotalAcrescimoImpontualidade = valorTotalAcrescimoImpontualidade.add(
								contaValoresHelper.getValorTotalContaValoresParcelamento());
					}
				}
			}
			retorno[0] = colecaoContas;
			retorno[1] = valorTotalConta;
			retorno[2] = valorTotalAcrescimoImpontualidade;
		}

		return retorno;
	}
	
	private Object[] obterDebitosSelecionados(String idsDebitos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<DebitoACobrar> colecaoDebitos = null;
		BigDecimal valorTotalDebitoACobrar = BigDecimal.ZERO;
		
		if (idsDebitos != null && !idsDebitos.equals("")){
			retorno = new Object[2];
			colecaoDebitos = new ArrayList();
			
			Collection colecaoDebitosSessao = (Collection) sessao.getAttribute("colecaoDebitoACobrar");
			Iterator itColecaoDebitosSessao = colecaoDebitosSessao.iterator();
			DebitoACobrar debitoACobrar = null;
			
			String[] idsDebitosArray = idsDebitos.split(",");
			
			while (itColecaoDebitosSessao.hasNext()){
				
				debitoACobrar = (DebitoACobrar) itColecaoDebitosSessao.next();
				
				for(int x=0; x<idsDebitosArray.length; x++){
					
					if (debitoACobrar.getId().equals(new Integer(idsDebitosArray[x]))){
						colecaoDebitos.add(debitoACobrar);
						valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotal());
						
					}
				}
			}
			retorno[0] = colecaoDebitos;
			retorno[1] = valorTotalDebitoACobrar;
		}

		return retorno;
	}
	
	private Object[] obterCreditosSelecionadas(String idsCreditos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<CreditoARealizar> colecaoCreditos = null;
		BigDecimal valorTotalCreditoARealizar = BigDecimal.ZERO;
		
		if (idsCreditos != null && !idsCreditos.equals("")){
			retorno = new Object[2];
			colecaoCreditos = new ArrayList();
			
			Collection colecaoCreditosSessao = (Collection) sessao.getAttribute("colecaoCreditoARealizar");
			Iterator itColecaoCreditosSessao = colecaoCreditosSessao.iterator();
			CreditoARealizar creditoARealizar = null;
			
			String[] idsCreditosArray = idsCreditos.split(",");
			
			while (itColecaoCreditosSessao.hasNext()){
				
				creditoARealizar = (CreditoARealizar) itColecaoCreditosSessao.next();
				
				for(int x=0; x<idsCreditosArray.length; x++){
					
					if (creditoARealizar.getId().equals(new Integer(idsCreditosArray[x]))){
						colecaoCreditos.add(creditoARealizar);
						valorTotalCreditoARealizar = valorTotalCreditoARealizar.add(creditoARealizar.getValorTotal());
						
					}
				}
			}
			retorno[0] = colecaoCreditos;
			retorno[1] = valorTotalCreditoARealizar;
		}
		
		return retorno;
	}
	
	private Object[] obterGuiasSelecionadas(String idsGuias, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuias = null;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		
		if (idsGuias != null && !idsGuias.equals("")){
			retorno = new Object[2];
			colecaoGuias = new ArrayList();
			
			Collection colecaoGuiasSessao = (Collection) sessao.getAttribute("colecaoGuiaPagamento");
			Iterator itColecaoGuiasSessao = colecaoGuiasSessao.iterator();
			GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = null;
			
			String[] idsGuiasArray = idsGuias.split(",");
			
			while (itColecaoGuiasSessao.hasNext()){
				
				guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) itColecaoGuiasSessao.next();
				
				for(int x=0; x<idsGuiasArray.length; x++){
					
					if (guiaPagamentoValoresHelper.getGuiaPagamento().getId().equals(new Integer(idsGuiasArray[x]))){
						colecaoGuias.add(guiaPagamentoValoresHelper);
						valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(
								guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
					}
				}
			}
			retorno[0] = colecaoGuias;
			retorno[1] = valorTotalGuiaPagamento;
		}
		
		return retorno;
	}*/

}
