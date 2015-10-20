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
* Anderson Cabral do Nascimento
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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirConsultarRegistroAtendimentoPendentesImovelAction extends
		GcomAction {
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

		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoPendentesImovel");
		ConsultarRegistroAtendimentoPendentesImovelActionForm form = (ConsultarRegistroAtendimentoPendentesImovelActionForm) actionForm;
		Fachada fachada    = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idImovel = httpServletRequest.getParameter("idImovel");
		String situacao = httpServletRequest.getParameter("situacao");
		
		sessao.removeAttribute("enderecoImovel");
		sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
		
		if (idImovel != null && !idImovel.trim().equals("")) {
						
			Imovel imovelSelecionado = fachada.pesquisarImovelRegistroAtendimento(Util.converterStringParaInteger(idImovel));
					
			form.setMatriculaImovel(imovelSelecionado.getId().toString());
			form.setInscricaoImovel(imovelSelecionado.getInscricaoFormatada());
			form.setSituacaoLigacaoAgua(imovelSelecionado.getLigacaoAguaSituacao().getDescricao());
			form.setSituacaoLigacaoEsgoto(imovelSelecionado.getLigacaoEsgotoSituacao().getDescricao());
		
			httpServletRequest.setAttribute("enderecoImovel", imovelSelecionado.getEnderecoFormatado());
			
			//Registro de Atendimento
			montaRegistroDeAtendimento(imovelSelecionado, situacao, httpServletRequest, fachada);
		
			//Debitos
			montaDebitos(imovelSelecionado, situacao, httpServletRequest, fachada, form);
			
			//Pagamentos
			montaPagamentos(imovelSelecionado, httpServletRequest, fachada);
		}			
			
		return retorno;
	}
	
	/**
	 * Cria uma colecao de Registro de Atendimento do imovel informado
	 * [UC0153] Apresentar Dados Para Analise da Medicao e Consumo
	 * 
	 * @param Imovel
	 * @param Situacao
	 * @param HttpServletRequest
	 * @param Fachada
	 ***/
	private void montaRegistroDeAtendimento(Imovel imovelSelecionado, String situacao, HttpServletRequest httpServletRequest, Fachada fachada){
		
		HttpSession sessao = httpServletRequest.getSession(false);

		if (imovelSelecionado != null){
			
			Collection colecaoConsultarImovelRegistroAtendimentoHelper  = null;
			Collection colecaoRegistroAtendimento = null;
			
			if (situacao != null && !situacao.equalsIgnoreCase("")){
				colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(imovelSelecionado.getId(), situacao);
			}
			else{
				colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(imovelSelecionado.getId(), null);
			}
			
			
			if(colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()){
		      
		      Iterator iteratorColecaoRegistroAtendimento = colecaoRegistroAtendimento.iterator();
		      
		      colecaoConsultarImovelRegistroAtendimentoHelper = new ArrayList();
		      
		      while (iteratorColecaoRegistroAtendimento.hasNext()) {
		    	  RegistroAtendimento registroAtendimento = (RegistroAtendimento) iteratorColecaoRegistroAtendimento.next();
		       
		    	  ConsultarImovelRegistroAtendimentoHelper consultarImovelRegistroAtendimentoHelper = new ConsultarImovelRegistroAtendimentoHelper();

			       //id registro atendimento
			       if(registroAtendimento != null  && registroAtendimento.getId() != null ){
			        consultarImovelRegistroAtendimentoHelper.setIdRegistroAtendimento(registroAtendimento.getId().toString());
			       }
			       
			       //tipo de solicitação
			       if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null 
			         && registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo() != null){
			        
			        consultarImovelRegistroAtendimentoHelper.setTipoSolicitacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
			       }
			       
			       //especificação
			       if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null){
			        consultarImovelRegistroAtendimentoHelper.setEspecificacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			       }
			       
			       //data de atendimento
			       if(registroAtendimento != null && registroAtendimento.getRegistroAtendimento() != null ){
			        consultarImovelRegistroAtendimentoHelper.setDataAtendimento(Util.formatarData(registroAtendimento.getRegistroAtendimento()));
			       }
			       
			       //situacao
			       if(registroAtendimento != null && registroAtendimento.getId() != null){
			        ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = 
			         fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
			        consultarImovelRegistroAtendimentoHelper.setSituacao(obterDescricaoSituacaoRAHelper.getDescricaoSituacao());
			        
			       }
			       
			       colecaoConsultarImovelRegistroAtendimentoHelper.add(consultarImovelRegistroAtendimentoHelper);
		      	}
		      
		      sessao.setAttribute("colecaoConsultarImovelRegistroAtendimentoHelper",colecaoConsultarImovelRegistroAtendimentoHelper);
			}
			else{
				throw new ActionServletException("atencao.imovel_sem_ra_pendente");
			}
		}
	}
	
	/**
	 * Cria uma colecao de Contas, Debitos a cobrar, Creditos a realizar e Guia de Pagamento do imovel informado
	 * [UC0153] Apresentar Dados Para Analise da Medicao e Consumo
	 * 
	 * @author Anderson Cabral
	 * @since 12/06/2013
	 * 
	 * @param Imovel
	 * @param Situacao
	 * @param HttpServletRequest
	 * @param Fachada
	 ***/
	private void montaDebitos(Imovel imovelSelecionado, String situacao, HttpServletRequest httpServletRequest, Fachada fachada, ConsultarRegistroAtendimentoPendentesImovelActionForm form){
		
		//CONSTANTES PARA CONSULTAR DEBITOS DO IMOVEL
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		Integer tipoImovel 		   = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta 	   = new Integer(1);
		Integer indicadorDebito    = new Integer(1);
		Integer indicadorCredito   = new Integer(1);
		Integer indicadorNotas 	   = new Integer(1);
		Integer indicadorGuias 	   = new Integer(1);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
								imovelSelecionado.getId().toString().trim(), 
								null, null,
								anoMesInicial, anoMesFinal,
								dataVencimentoDebitoI,
								dataVencimentoDebitoF, 
								indicadorPagamento.intValue(), 
								indicadorConta.intValue(), 
								indicadorDebito.intValue(), 
								indicadorCredito.intValue(), 
								indicadorNotas.intValue(), 
								indicadorGuias.intValue(), 
								indicadorAtualizar.intValue(), 
								null,
								2);
		
		//CONTAS
		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();

		ContaValoresHelper dadosConta = null;

		BigDecimal valorConta = new BigDecimal("0.00");
		BigDecimal valorAcrescimo = new BigDecimal("0.00");
		BigDecimal valorAgua = new BigDecimal("0.00");
		BigDecimal valorEsgoto = new BigDecimal("0.00");
		BigDecimal valorDebito = new BigDecimal("0.00");
		BigDecimal valorCredito = new BigDecimal("0.00");
		BigDecimal valorImposto = new BigDecimal("0.00");
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		
		if (colecaoContaValores != null	&& !colecaoContaValores.isEmpty()) {
			java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
			// percorre a colecao de conta somando o valor para obter um valor total
			while (colecaoContaValoresIterator.hasNext()) {

				dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
				valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
				valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
				valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
				valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
				valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
				valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
				valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
				
				if (dadosConta.getValorAtualizacaoMonetaria() != null && !dadosConta.getValorAtualizacaoMonetaria().equals("")) {
					valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosConta.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosConta.getValorJurosMora() != null	&& !dadosConta.getValorJurosMora().equals("")) {
					valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorJurosMora = valorJurosMora.add(dadosConta.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosConta.getValorMulta() != null && !dadosConta.getValorMulta().equals("")) {
					valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorMulta = valorMulta.add(dadosConta.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
			}
		}
		
		//SITUACOES DE COBRANCA ATIVA
		Collection colecaoCobrancaSituacao = fachada.pesquisarImovelCobrancaSituacaoPorImovel(imovelSelecionado.getId());
		
		//HISTORICO DE RETORNO DE NEGATIVACOES
		Collection colecaoDadosNegativacaoRetorno = fachada.pesquisarNegativadorRetornoMotivoDoReg(imovelSelecionado.getId());
		
		//DEBITOS A COBRAR
		Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

		BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		BigDecimal valorDebitoACobrarSemJurosParcelamento = new BigDecimal("0.00");
		DebitoACobrar dadosDebito = null;
		BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
		int indiceCurtoPrazo = 0;
		int indiceLongoPrazo = 1;

		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
			java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
			// percorre a colecao de debito a cobrar somando o valor para obter um valor total
			while (colecaoDebitoACobrarIterator.hasNext()) {

				dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
				valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
				
				if (dadosDebito.getDebitoTipo() != null &&
						!dadosDebito.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					valorDebitoACobrarSemJurosParcelamento = valorDebitoACobrarSemJurosParcelamento.add(dadosDebito.getValorTotalComBonus());
				}
				
				//Debitos A Cobrar - Parcelamento
				if (dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
					|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
					|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
					
					// [SB0001] Obter Valores de Curto e Longo Prazo
					valorRestanteACobrar = dadosDebito.getValorTotalComBonus();

					BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
							dadosDebito.getNumeroPrestacaoDebito(),	
							dadosDebito.getNumeroPrestacaoCobradasMaisBonus(),
							valorRestanteACobrar);
					valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
					valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
				}
				
			}
			valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
		}
		
		//CREDITOS A REALIZAR
		Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		BigDecimal valorCreditoARealizarSemDescontosParcelamento = new BigDecimal("0.00");
		CreditoARealizar dadosCredito = null;

		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
			java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
			// percorre a colecao de credito a realizar somando o valor para obter um valor total
			while (colecaoCreditoARealizarIterator.hasNext()) {

				dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
				valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
				
				if (dadosCredito.getCreditoOrigem() != null && 
						!dadosCredito.getCreditoOrigem().getId().equals(CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO)){
					valorCreditoARealizarSemDescontosParcelamento = valorCreditoARealizarSemDescontosParcelamento.add(dadosCredito.getValorTotalComBonus());
				}
			}
		}
		
		//GUIA DE PAGAMENTO
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel.getColecaoGuiasPagamentoValores();

		BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
		GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

		if (colecaoGuiaPagamentoValores != null	&& !colecaoGuiaPagamentoValores.isEmpty()) {
			java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
			// percorre a colecao de guia de pagamento somando o valor para obter um valor total
			while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

				dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
				valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
				
				if (dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
					valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosGuiaPagamentoValoresHelper.getValorJurosMora() != null && !dadosGuiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
					valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorJurosMora = valorJurosMora.add(dadosGuiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosGuiaPagamentoValoresHelper.getValorMulta() != null	&& !dadosGuiaPagamentoValoresHelper.getValorMulta().equals("")) {
					valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorMulta = valorMulta.add(dadosGuiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
			}
		}
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//SETA AS VARIAVEIS NA REQUEST
		if ((colecaoContaValores == null)&& (colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty())
				&& (colecaoCreditoARealizar == null || colecaoCreditoARealizar.isEmpty()) && (colecaoGuiaPagamentoValores == null)) {
			if (colecaoContaValores == null){
				sessao.removeAttribute("colecaoContaValores");
				sessao.removeAttribute("valorConta");
				sessao.removeAttribute("valorAcrescimo");
				sessao.removeAttribute("valorAgua");
				sessao.removeAttribute("valorEsgoto");
				sessao.removeAttribute("valorDebito");
				sessao.removeAttribute("valorCredito");
				sessao.removeAttribute("valorContaAcrescimo");
				sessao.removeAttribute("valorImposto");
				sessao.removeAttribute("valorTotalSemAcrescimo");
				sessao.removeAttribute("valorTotalComAcrescimo");
				sessao.removeAttribute("valorToralSemAcrescimoESemJurosParcelamento");							
			} 
			if (colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty()){
				sessao.removeAttribute("colecaoDebitoACobrar");
				sessao.removeAttribute("valorDebitoACobrar");							
			}
			if (colecaoCreditoARealizar == null || colecaoCreditoARealizar.isEmpty()){
				sessao.removeAttribute("colecaoCreditoARealizar");
				sessao.removeAttribute("valorCreditoARealizar");
				sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");							
			}
			if (colecaoGuiaPagamentoValores == null || colecaoGuiaPagamentoValores.isEmpty()){
				sessao.removeAttribute("colecaoGuiaPagamentoValores");
				sessao.removeAttribute("valorGuiaPagamento");							
			}
			
			if(colecaoCobrancaSituacao == null){
				sessao.removeAttribute("colecaoCobrancaSituacao");
			}
			
			if(colecaoDadosNegativacaoRetorno == null){
				sessao.removeAttribute("colecaoDadosNegativacaoRetorno");
			}
			
		} else {
			// Manda a colecao pelo request
			sessao.setAttribute("colecaoContaValores",colecaoContaValores);

			// Manda a colecao e os valores total de conta pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrar);
			sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
			sessao.setAttribute("valorAcrescimo", Util.formatarMoedaReal(valorAcrescimo));
			sessao.setAttribute("valorAgua", Util.formatarMoedaReal(valorAgua));
			sessao.setAttribute("valorEsgoto", Util.formatarMoedaReal(valorEsgoto));
			sessao.setAttribute("valorDebito", Util.formatarMoedaReal(valorDebito));
			sessao.setAttribute("valorCredito", Util.formatarMoedaReal(valorCredito));
			sessao.setAttribute("valorContaAcrescimo", Util.formatarMoedaReal(valorConta.add(valorAcrescimo)));
			sessao.setAttribute("valorImposto", Util.formatarMoedaReal(valorImposto));

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrar);
			sessao.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));

			// Manda a colecao e o valor total de CreditoARealizar pelo request
			sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
			sessao.setAttribute("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
			sessao.setAttribute("valorCreditoARealizarSemDescontosParcelamento",Util.formatarMoedaReal(valorCreditoARealizarSemDescontosParcelamento));

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			sessao.setAttribute("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValores);
			sessao.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));

			// Soma o valor total dos debitos e subtrai dos creditos
			BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);

			BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo.add(valorAcrescimo);
			
			BigDecimal valorToralSemAcrescimoESemJurosParcelamento = 
				valorConta.add(valorDebitoACobrarSemJurosParcelamento);
			
			valorToralSemAcrescimoESemJurosParcelamento = 
				valorToralSemAcrescimoESemJurosParcelamento.add(valorGuiaPagamento);
			
			
			sessao.setAttribute("valorTotalSemAcrescimo", Util
					.formatarMoedaReal(valorTotalSemAcrescimo));
			sessao.setAttribute("valorTotalComAcrescimo", Util
					.formatarMoedaReal(valorTotalComAcrescimo));
			sessao.setAttribute("valorToralSemAcrescimoESemJurosParcelamento", 
					Util.formatarMoedaReal(valorToralSemAcrescimoESemJurosParcelamento));
			
			sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
			sessao.setAttribute("colecaoDadosNegativacaoRetorno",colecaoDadosNegativacaoRetorno);
			
			///////////////////////////////////////////////////////////////////////
			Short indicadorContasRevisao = 2; 
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			if(sistemaParametro.getResolucaoDiretoria() != null){
				indicadorContasRevisao = 1;
				Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
				ImovelPerfil imovelPerfil = fachada.obterImovelPerfil(new Integer(imovelSelecionado.getId().toString().trim())); 
				Short numeroReparcelamentoConsecutivos = fachada.consultarNumeroReparcelamentoConsecutivosImovel(new Integer(imovelSelecionado.getId().toString().trim()));
				
				if(numeroReparcelamentoConsecutivos == null){
					numeroReparcelamentoConsecutivos = new Short("0");
				}
				
				IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = 
					new IndicadoresParcelamentoHelper();
				
				indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));
				
				//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
				ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
						sistemaParametro.getResolucaoDiretoria().getId(), 
						new Integer(imovelSelecionado.getId().toString().trim()), 
						new BigDecimal("0.00"), 
						new Integer(form.getSituacaoLigacaoAgua()), 
						new Integer(form.getSituacaoLigacaoEsgoto()), 
						imovelPerfil.getId(), 
						"01/0001", 
						new Integer("2"),//indicador de restabelecimento 
						colecaoContaValores, 
						valorTotalComAcrescimo, 
						valorMulta, 
						valorJurosMora, 
						valorAtualizacaoMonetaria, 
						new Integer(numeroReparcelamentoConsecutivos.toString()), 
						colecaoGuiaPagamentoValores, 
						usuarioLogado, 
						valorTotalRestanteParcelamentosACobrar, 
						Util.formatarMesAnoComBarraParaAnoMes("01/0001"),
						Util.formatarMesAnoComBarraParaAnoMes("12/9999"), 
						indicadoresParcelamentoHelper,
						valorCreditoARealizar,false);
				
				NegociacaoOpcoesParcelamentoHelper negociacaoOpcoesParcelamentoHelper = 
					fachada.calcularValorDosDescontosPagamentoAVista(helper);
				
				BigDecimal valorTotalDescontoPagamentoAVista = negociacaoOpcoesParcelamentoHelper.getValorTotalDescontoPagamentoAVista();
				BigDecimal valorPagamentoAVista = valorTotalComAcrescimo.subtract(valorTotalDescontoPagamentoAVista);

				sessao.setAttribute("valorTotalDescontoPagamentoAVista", valorTotalDescontoPagamentoAVista);
				sessao.setAttribute("valorPagamentoAVista", valorPagamentoAVista);
				
			
			}
			sessao.setAttribute("indicadorContasRevisao",indicadorContasRevisao);
			//////////////////////////////////////////////////////////////////////
		}

	}
	
	/**
	 * Cria uma colecao Pagamento das contas, Pagamento das Guias de Pagamentos e Pagamentos dos Debitos a cobrar do imovel informado
	 * [UC0153] Apresentar Dados Para Analise da Medicao e Consumo
	 * 
	 * @author Anderson Cabral
	 * @since 13/06/2013
	 * 
	 * @param Imovel
	 * @param Situacao
	 * @param HttpServletRequest
	 * @param Fachada
	 ***/
	private void montaPagamentos(Imovel imovelSelecionado, HttpServletRequest httpServletRequest, Fachada fachada){

		// Imóvel
		Collection<Pagamento> colecaoPagamentosImovelConta = new ArrayList<Pagamento>();
		Collection<Pagamento> colecaoPagamentosImovelGuiaPagamento = new ArrayList<Pagamento>();
		Collection<Pagamento> colecaoPagamentosImovelDebitoACobrar = new ArrayList<Pagamento>();
		
		Integer qtdePagContas = 0;
		Integer qtdePagGuiaPagamento = 0;
		Integer qtdePagDebitoACobrar = 0;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<Pagamento> colecaoImoveisPagamentos = fachada.pesquisarPagamentoImovel(imovelSelecionado.getId().toString().trim(), null, null, null,
			null, null, null,null, null,null, null,null, null,null, null,null, null, null, null);		
		
		// Consultar Pagamentos do Imóvel
		if (colecaoImoveisPagamentos != null && !colecaoImoveisPagamentos.isEmpty()) {

			Iterator<Pagamento> colecaoPagamentoIterator = colecaoImoveisPagamentos.iterator();

			// Divide os pagamentos do imóvel pelo tipo de pagamento
			while (colecaoPagamentoIterator.hasNext()) {
				Pagamento pagamento = (Pagamento) colecaoPagamentoIterator.next();

				/*
				 * Alterado por Raphael Rossiter em 15/01/2007 - Analistas: Aryed e Eduardo
				 * OBJ: Mostrar todos os pagamentos da tabela de Pagamento
				 */ 
				if (pagamento.getDocumentoTipo().getId().equals(
						DocumentoTipo.DEBITO_A_COBRAR)) {
					colecaoPagamentosImovelDebitoACobrar.add(pagamento);
				} else if (pagamento.getDocumentoTipo().getId().equals(
						DocumentoTipo.GUIA_PAGAMENTO)) {
					colecaoPagamentosImovelGuiaPagamento.add(pagamento);
				}
				else{
					colecaoPagamentosImovelConta.add(pagamento);
				}
			}

			// Organizar a coleção de Conta
			if (colecaoPagamentosImovelConta != null
					&& !colecaoPagamentosImovelConta.isEmpty()) {
				Collections.sort((List) colecaoPagamentosImovelConta,
						new Comparator() {
							public int compare(Object a, Object b) {
								Integer anoMesReferencia1 = ((Pagamento) a)
										.getAnoMesReferencia();
								Integer anoMesReferencia2 = ((Pagamento) b)
										.getAnoMesReferencia();

								return anoMesReferencia1
										.compareTo(anoMesReferencia2);
							}
					});
				
				sessao.setAttribute("colecaoPagamentosImovelConta",
						colecaoPagamentosImovelConta);
				
				qtdePagContas = colecaoPagamentosImovelConta.size();
			}
			else{
				
				sessao.removeAttribute("colecaoPagamentosImovelConta");
			}

			// Organizar a coleção de Guia de Pagamento 
			if (colecaoPagamentosImovelGuiaPagamento != null
					&& !colecaoPagamentosImovelGuiaPagamento.isEmpty()) {
				Collections.sort((List) colecaoPagamentosImovelGuiaPagamento,
						new Comparator() {
							public int compare(Object a, Object b) {
								String tipoDebito1 = ((Pagamento) a)
										.getDebitoTipo() == null ? ""
										: ((Pagamento) a).getDebitoTipo()
												.getDescricao();
								String tipoDebito2 = ((Pagamento) b)
										.getDebitoTipo() == null ? ""
										: ((Pagamento) b).getDebitoTipo()
												.getDescricao();

								return tipoDebito1.compareTo(tipoDebito2);

							}
						});
				
				
				sessao.setAttribute("colecaoPagamentosImovelGuiaPagamento",
						colecaoPagamentosImovelGuiaPagamento);
				
				qtdePagGuiaPagamento = colecaoPagamentosImovelGuiaPagamento.size();
			}
			else{				
				sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
			}
			
			// Organizar a coleção de Guia de Pagamento 
			if (colecaoPagamentosImovelDebitoACobrar != null
					&& !colecaoPagamentosImovelDebitoACobrar.isEmpty()) {

				// Organizar a coleção
				Collections.sort((List) colecaoPagamentosImovelDebitoACobrar,
						new Comparator() {
							public int compare(Object a, Object b) {
								Integer anoMesReferencia1 = ((Pagamento) a)
										.getAnoMesReferencia();
								Integer anoMesReferencia2 = ((Pagamento) b)
										.getAnoMesReferencia();

								return anoMesReferencia1
										.compareTo(anoMesReferencia2);

							}
						});
				
				
				sessao.setAttribute("colecaoPagamentosImovelDebitoACobrar",
						colecaoPagamentosImovelDebitoACobrar);
				
				qtdePagDebitoACobrar =  colecaoPagamentosImovelDebitoACobrar.size();
			}
			else{				
				sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
			}
			
		}
		else{			
			sessao.removeAttribute("colecaoPagamentosImovelConta");
        	sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
        	sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
		}
		
		//PAGAMENTO HISTORICO
		Collection<PagamentoHistorico> colecaoImoveisPagamentosHistorico = fachada.pesquisarPagamentoHistoricoImovel(imovelSelecionado.getId().toString().trim(), null, null, null,
				null, null, null,null, null,null, null,null, null,null, null,null, null);

		Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelConta = new ArrayList<PagamentoHistorico>();
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelGuiaPagamento = new ArrayList<PagamentoHistorico>();
		Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelDebitoACobrar = new ArrayList<PagamentoHistorico>();

		if (colecaoImoveisPagamentosHistorico != null && !colecaoImoveisPagamentosHistorico.isEmpty()) {

			Iterator<PagamentoHistorico> colecaoPagamentoHistoricoIterator = colecaoImoveisPagamentosHistorico.iterator();
		
			// Divide os pagamentos do imovel pelo tipo de pagamento
			while (colecaoPagamentoHistoricoIterator.hasNext()) {
				PagamentoHistorico pagamentoHistorico = (PagamentoHistorico) colecaoPagamentoHistoricoIterator.next();
				
				if (pagamentoHistorico.getAvisoBancario() == null) {
					AvisoBancario avisoBancario = new AvisoBancario();
					Arrecadador arrecadador = new Arrecadador();
					Cliente cliente = new Cliente();
					
					String nomeCliente = fachada.pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
					
					if (nomeCliente != null){
						
						cliente.setNome(nomeCliente);
						arrecadador.setCliente(cliente);
						avisoBancario.setArrecadador(arrecadador);
						pagamentoHistorico.setAvisoBancario(avisoBancario);
					}
				}
				
				/*
				 * Alterado por Raphael Rossiter em 15/01/2007 - Analistas: Aryed e Eduardo
				 * OBJ: Mostrar todos os pagamentos da tabela de Pagamento
				 */
				if (pagamentoHistorico.getDocumentoTipo().getId().equals(
						DocumentoTipo.DEBITO_A_COBRAR)) {
					colecaoPagamentosHistoricoImovelDebitoACobrar.add(pagamentoHistorico);
				} else if (pagamentoHistorico.getDocumentoTipo().getId().equals(
						DocumentoTipo.GUIA_PAGAMENTO)) {
					colecaoPagamentosHistoricoImovelGuiaPagamento.add(pagamentoHistorico);
				}
				else{
					colecaoPagamentosHistoricoImovelConta.add(pagamentoHistorico);
				}
			}

			// Organizar a coleção de Conta
			if (colecaoPagamentosHistoricoImovelConta != null
					&& !colecaoPagamentosHistoricoImovelConta.isEmpty()) {				
				
				sessao.setAttribute("colecaoPagamentosHistoricoImovelConta",
						colecaoPagamentosHistoricoImovelConta);
				
				qtdePagContas = qtdePagContas + colecaoPagamentosHistoricoImovelConta.size();
			}
			else{				
				sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
			}

			// Organizar a coleção de Guia de Pagamento 
			if (colecaoPagamentosHistoricoImovelGuiaPagamento != null
					&& !colecaoPagamentosHistoricoImovelGuiaPagamento.isEmpty()) {
				
				sessao.setAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento",
						colecaoPagamentosHistoricoImovelGuiaPagamento);
				qtdePagGuiaPagamento =  qtdePagGuiaPagamento + colecaoPagamentosHistoricoImovelGuiaPagamento.size();
			}
			else{				
				sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
			}
			
			// Organizar a coleção de Débito a Cobrar
			if (colecaoPagamentosHistoricoImovelDebitoACobrar != null
					&& !colecaoPagamentosHistoricoImovelDebitoACobrar.isEmpty()) {
				
				sessao.setAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar",
						colecaoPagamentosHistoricoImovelDebitoACobrar);
				qtdePagDebitoACobrar = qtdePagDebitoACobrar + colecaoPagamentosHistoricoImovelDebitoACobrar.size();
			}
			else{			
				sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");
			}
		}
		else{			
			sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
        	sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
   			sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");
		}
		
		sessao.setAttribute("qtdePagContas",qtdePagContas);
		sessao.setAttribute("qtdePagGuiaPagamento",qtdePagGuiaPagamento);
		sessao.setAttribute("qtdePagDebitoACobrar",qtdePagDebitoACobrar);		
	}
}