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
package gsan.gui.relatorio.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.EsferaPoder;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.bean.ContaValoresHelper;
import gsan.cobranca.bean.GuiaPagamentoValoresHelper;
import gsan.cobranca.parcelamento.Parcelamento;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.Conta;
import gsan.gui.cobranca.ConsultarDebitoClienteActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.cobranca.RelatorioExtratoDebitoCliente;
import gsan.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.Util;

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

/**
 * Gerar e Emitir Extrato de Débito por Cliente
 * @author Ana Maria
 * @date 04/04/2007
 */

public class GerarRelatorioExtratoDebitoClienteAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		ConsultarDebitoClienteActionForm consultarDebitoClienteActionForm = (ConsultarDebitoClienteActionForm) actionForm;
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Linha 2
		String nomeCliente = "";    	
		String cpfCnpj = "";
		
		//Linha 3
		String enderecoCliente = "";
		
		//Linha 4 
		String tipoResponsavel = "";
		
		//Linha 11
		String dataEmissao = "";
			
		//Conta
		Collection<ContaValoresHelper> colecaoContas =  null;
		BigDecimal valorTotalContas = new BigDecimal("0.00");
		BigDecimal acrescimoImpontualidade = new BigDecimal("0.00");
		
		//Guia de Pagamento
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;
		BigDecimal valorTotalGuiaPagamento = null;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		BigDecimal valorAcrescimoImpontualidadeGuiaPagamento = new BigDecimal("0.00");
		BigDecimal valorTotalGuiaComAcrescimo = new BigDecimal("0.00");
		BigDecimal valorAcrescimo = new BigDecimal("0.00");
		
		//Consultar Débito 			
		colecaoContas = (Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores");
		String debitosACobrar = (String)sessao.getAttribute("valorDebitoACobrar");
		String valorContas = (String) sessao.getAttribute("valorConta");
		
		if ( sessao.getAttribute("valorAcrescimo")!= null ) {
			acrescimoImpontualidade = Util.formatarMoedaRealparaBigDecimal(sessao.getAttribute("valorAcrescimo").toString());
		}
		
		  //-----------------------------------------------------------------------------------------
	    //Vivianne Sousa - 03/07/2008
		Map mapContas =  retirarContasEmRevisaoDeColecaoContas(colecaoContas);
		colecaoContas =  (Collection)mapContas.get("colecaoContasSemContasEmRevisao");
		BigDecimal valorTotalContasRevisao = (BigDecimal) mapContas.get("valorTotalContasRevisao");
		Date maiorDataVencimentoContas = (Date)mapContas.get("maiorDataVencimentoContas");
		
		BigDecimal valorContasBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorContas);
	    valorContasBigDecimal = valorContasBigDecimal.subtract(valorTotalContasRevisao);
	    valorContas = Util.formatarMoedaReal(valorContasBigDecimal);
	    
	    //-----------------------------------------------------------------------------------------
	    
		//Na funcionalidade Gerar Extrato de débitos, uma vez que o cliente seja informado, 
		//é gerado um extrato por cliente com os valores de Guia de Pagamento.
		if ( httpServletRequest.getParameter("guiaPagamento") != null ) {

			//Obtem as guias selecionadas para emissao do extrato
			String idsGuias = httpServletRequest.getParameter("guiaPagamento");
	        Object[] guias = this.obterGuiasSelecionadas(idsGuias, sessao);

	      //GUIAS DE PAGAMENTO
		  if(guias != null){
		    	colecaoGuiasPagamento = (Collection<GuiaPagamentoValoresHelper>) guias[0];
		    	valorTotalGuiaPagamento = (BigDecimal)guias[1];
		    	
		    	valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add((BigDecimal)guias[2]);
				valorJurosMora = valorJurosMora.add((BigDecimal)guias[3]);
				valorMulta = valorMulta.add((BigDecimal)guias[4]);
				
				//Verifica se o usuario deseja incluir o valor do acrésimo para as guias geradas.
				if ( consultarDebitoClienteActionForm.getIndicadorIncluirAcrescimosImpontualidade() != null && 
						consultarDebitoClienteActionForm.getIndicadorIncluirAcrescimosImpontualidade().equals(CobrancaDocumento.INCLUIR_ACRESCIMOS) ) {
					valorAcrescimoImpontualidadeGuiaPagamento = valorAtualizacaoMonetaria.add(valorMulta).add(valorJurosMora);	
					valorAcrescimo = valorAcrescimoImpontualidadeGuiaPagamento;
					// cria os itens de cobran?a de documento para as guias de pagamento
					for (GuiaPagamentoValoresHelper guiaPagamentoValorHelper : colecaoGuiasPagamento) {
						guiaPagamentoValorHelper.setIndicadorCalcularAcrescimoImpontualidade(CobrancaDocumento.INCLUIR_ACRESCIMOS);
					}
				} else if ( consultarDebitoClienteActionForm.getIndicadorIncluirAcrescimosImpontualidade() != null && 
						consultarDebitoClienteActionForm.getIndicadorIncluirAcrescimosImpontualidade().equals(CobrancaDocumento.NAO_INCLUIR_ACRESCIMOS)  ) {
					// cria os itens de cobran?a de documento para as guias de pagamento
					for (GuiaPagamentoValoresHelper guiaPagamentoValorHelper : colecaoGuiasPagamento) {
						guiaPagamentoValorHelper.setIndicadorCalcularAcrescimoImpontualidade(CobrancaDocumento.NAO_INCLUIR_ACRESCIMOS);
					}
				}
				
				valorTotalGuiaComAcrescimo = valorTotalGuiaPagamento.add(valorAcrescimoImpontualidadeGuiaPagamento);
				valorContas = Util.formatarMoedaReal(valorTotalGuiaComAcrescimo);
		  }
		}
				
		/*
		 * Alterado por Raphael Rossiter em 24/09/2007
		 * OBJ:Obter o codigo do cliente tanto pelo campo de cliente superior como pelo campo 
		 * codigo do cliente. 
		 */
		Integer idCliente = null;
		if (consultarDebitoClienteActionForm.getCodigoCliente() != null &&
			!consultarDebitoClienteActionForm.getCodigoCliente().equals("")){		
			idCliente = new Integer(consultarDebitoClienteActionForm.getCodigoCliente());
			nomeCliente = consultarDebitoClienteActionForm.getNomeCliente();
			cpfCnpj = consultarDebitoClienteActionForm.getCpfCnpj();
		
		}else {
			idCliente = new Integer(consultarDebitoClienteActionForm.getCodigoClienteSuperior());
			nomeCliente = consultarDebitoClienteActionForm.getNomeClienteSuperior();
			cpfCnpj = consultarDebitoClienteActionForm.getCpfCnpj();
		}
		
		Cliente cliente = fachada.pesquisarClienteDigitado(idCliente);
		
		if(consultarDebitoClienteActionForm.getTipoRelacao() != null){
			  tipoResponsavel = consultarDebitoClienteActionForm.getTipoRelacao();
		} else if ( tipoResponsavel == null || tipoResponsavel.equals("") ) {
			tipoResponsavel = cliente.getClienteTipo().getDescricao();
		}
		
		enderecoCliente = consultarDebitoClienteActionForm.getEnderecoCliente();
		
		if ( consultarDebitoClienteActionForm.getCodigoCliente() != null && !consultarDebitoClienteActionForm.getCodigoCliente().equals("") ) {
			enderecoCliente = fachada.pesquisarEnderecoClienteAbreviado(Integer.valueOf(consultarDebitoClienteActionForm.getCodigoCliente()));
		}
		
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		RelatorioExtratoDebitoCliente relatorioExtratoDebitoCliente = new RelatorioExtratoDebitoCliente((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		 
		String tipo = (String)httpServletRequest.getParameter("tipo");
		ExtratoDebitoRelatorioHelper extratoDebitoRelatorioHelper = fachada.gerarEmitirExtratoDebito(
                 null, new Short("0"), colecaoContas, colecaoGuiasPagamento, null,
                 valorAcrescimo, new BigDecimal("0.00"), Util.formatarMoedaRealparaBigDecimal( valorContas ), null, cliente,null, null, null,null,null, "RelatorioExtratoDebito");
       
		 CobrancaDocumento documentoCobranca = null; 
		 
		 if ( extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemContas() != null && 
				 !extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemContas().isEmpty() ) {
         
			 documentoCobranca = extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemContas().iterator().next().getCobrancaDocumento();
		 } else if (extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemGuiasPagamento() != null && 
				 !extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemGuiasPagamento().isEmpty()) {
			 
			 documentoCobranca = extratoDebitoRelatorioHelper.getColecaoCobrancaDocumentoItemGuiasPagamento().iterator().next().getCobrancaDocumento();
		 }
        
        if(cliente.getClienteTipo() != null && cliente.getClienteTipo().getEsferaPoder()!=null
					&& cliente.getClienteTipo().getEsferaPoder().getId().compareTo(EsferaPoder.FEDERAL.intValue())==0){
					
        	if(documentoCobranca.getValorImpostos()!=null){
        		relatorioExtratoDebitoCliente.addParametro("valorTotalImpostos",Util.formatarMoedaReal(documentoCobranca.getValorImpostos()));
        	}else{
        		relatorioExtratoDebitoCliente.addParametro("valorTotalImpostos",Util.formatarMoedaReal(new BigDecimal("0.0")));
        	}
        	
        	relatorioExtratoDebitoCliente.addParametro("tipoFederal", "sim");
		}
        
        documentoCobranca.setUsuario(usuario);
        fachada.atualizar(documentoCobranca);
        
		if(httpServletRequest.getParameter("tipo") != null && tipo.equalsIgnoreCase("conta")){

          //Linha 3		
          String seqDocCobranca = ""+documentoCobranca.getNumeroSequenciaDocumento();						
           relatorioExtratoDebitoCliente.addParametro("seqDocCobranca",seqDocCobranca);
         
          //Linha 4		
          dataEmissao = Util.formatarData(documentoCobranca.getEmissao());
        
          //String dataValidade = Util.formatarData(documentoCobranca.getDataValidade());
          //Vivianne Sousa 15/09/2008
          String dataValidade = Util.formatarData(fachada.
          		obterDataValidadeDocumentoCobranca(documentoCobranca, usuario, maiorDataVencimentoContas));
        
          valorTotalContas = extratoDebitoRelatorioHelper.getValorTotalConta();
		
		  //Linha 15
		  relatorioExtratoDebitoCliente.addParametro("dataValidade", dataValidade);
		  
		  //Montar codigo de barras
		  if(valorTotalContas!= null && sistemaParametro.getValorExtratoFichaComp() != null
				&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
				&& valorTotalContas.compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
				 
				this.obterRepresentacaoNumericaCodigoBarra(relatorioExtratoDebitoCliente, documentoCobranca.getId().toString(), valorTotalContas);
				 	
			 }else{
				 this.montarRepresentacaoNumericaCodigoBarraExtratoDebitoCliente(relatorioExtratoDebitoCliente , 8, valorTotalContas, 0 , seqDocCobranca, 
						 documentoCobranca.getDocumentoTipo().getId(), idCliente);
		   }
		   
	       relatorioExtratoDebitoCliente.addParametro("colecaoDebitoACobrar",null);
	       relatorioExtratoDebitoCliente.addParametro("colecaoCreditoARealizar",null);
	       relatorioExtratoDebitoCliente.addParametro("colecaoGuiaPagamentoValores",null);
		  
		}else{
			
		   Collection colecaoGuiaPagamentoValores = null;
		   
		   if ( colecaoGuiasPagamento != null && !colecaoGuiasPagamento.isEmpty() ) {
			   acrescimoImpontualidade = valorAcrescimoImpontualidadeGuiaPagamento;
			   colecaoGuiaPagamentoValores = colecaoGuiasPagamento;
			   relatorioExtratoDebitoCliente.addParametro("indicadorIncluirAcrescimosImpontualidade",  consultarDebitoClienteActionForm.getIndicadorIncluirAcrescimosImpontualidade());
			   
		   } else {
			   colecaoGuiaPagamentoValores = (Collection)sessao.getAttribute("colecaoGuiaPagamentoValores");
	    	   
		   }

		   relatorioExtratoDebitoCliente.addParametro("debitosACobrar", debitosACobrar);
		   relatorioExtratoDebitoCliente.addParametro("acrescimoImpontualidade", Util.formatarMoedaReal(acrescimoImpontualidade));
		   relatorioExtratoDebitoCliente.addParametro("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValores);
		   
		   valorTotalContas = Util.formatarMoedaRealparaBigDecimal((String)sessao.getAttribute("valorTotalComAcrescimo"));
		   
           //Vivianne Sousa - 03/07/2008
           valorTotalContas = valorTotalContas.subtract(valorTotalContasRevisao);
           //Linha 4		
	       dataEmissao = Util.formatarData(documentoCobranca.getEmissao());
			
	       //adicionado por Vivianne Sousa - 27/04/2010 - CRC4239
	       //Caso existam serviços na fatura, DETALHAR estes serviços.
	       Collection colecaoDebitoACobrar = (Collection)sessao.getAttribute("colecaoDebitoACobrar");
	       Collection colecaoCreditoARealizar = (Collection)sessao.getAttribute("colecaoCreditoARealizar");
	      
	       relatorioExtratoDebitoCliente.addParametro("colecaoDebitoACobrar",colecaoDebitoACobrar);
	       relatorioExtratoDebitoCliente.addParametro("colecaoCreditoARealizar",colecaoCreditoARealizar);
	      
		}
		
		//Linha 2
		 relatorioExtratoDebitoCliente.addParametro("nomeCliente",nomeCliente);
		 relatorioExtratoDebitoCliente.addParametro("codigoClienteResponsavel", idCliente.toString());
		 relatorioExtratoDebitoCliente.addParametro("cpfCnpj", cpfCnpj);
		 
		//Linha 3
		 relatorioExtratoDebitoCliente.addParametro("enderecoCliente", enderecoCliente);

		//Linha 4
		 relatorioExtratoDebitoCliente.addParametro("tipoResponsavel", tipoResponsavel);
		 
		//Linhas 11
		 relatorioExtratoDebitoCliente.addParametro("dataEmissao",dataEmissao);
		 
		 relatorioExtratoDebitoCliente.addParametro("valorContas",valorContas);	
		 
		 BigDecimal valorGuiasPagamento = BigDecimal.ZERO;
		 if ( valorTotalGuiaPagamento != null ) {
			 valorGuiasPagamento = valorTotalGuiaPagamento; 
			 valorTotalContas = valorTotalGuiaPagamento.add(valorAcrescimoImpontualidadeGuiaPagamento);
			 relatorioExtratoDebitoCliente.addParametro("extratoGuiaCliente", ConstantesSistema.SIM.toString() );
			 
			//Montar codigo de barras
		    if(valorTotalContas!= null && sistemaParametro.getValorExtratoFichaComp() != null
				&& !sistemaParametro.getValorExtratoFichaComp().equals(new BigDecimal("0.00"))
				&& valorTotalContas.compareTo(sistemaParametro.getValorExtratoFichaComp()) >= 0){
				 
				this.obterRepresentacaoNumericaCodigoBarra(relatorioExtratoDebitoCliente, documentoCobranca.getId().toString(), valorTotalContas);
				 	
			  }else{
				  String seqDocCobranca = ""+documentoCobranca.getNumeroSequenciaDocumento();						
		           relatorioExtratoDebitoCliente.addParametro("seqDocCobranca",seqDocCobranca);
				 this.montarRepresentacaoNumericaCodigoBarraExtratoDebitoCliente(relatorioExtratoDebitoCliente , 8, valorTotalContas, 0 , seqDocCobranca, 
						 documentoCobranca.getDocumentoTipo().getId(), idCliente);
		      }
		 } else {
			 valorGuiasPagamento = Util.formatarMoedaRealparaBigDecimal( sessao.getAttribute("valorGuiaPagamento").toString() );	 
		 }
		 		 
		 relatorioExtratoDebitoCliente.addParametro("valorGuiasPagamento", Util.formatarMoedaReal( valorGuiasPagamento ) );
		 
		 BigDecimal valorDescontosCreditos = BigDecimal.ZERO;
		 if ( sessao.getAttribute("valorCreditoARealizar") != null ) {
			 valorDescontosCreditos = Util.formatarMoedaRealparaBigDecimal( sessao.getAttribute("valorCreditoARealizar").toString() );
		 }
		 
		relatorioExtratoDebitoCliente.addParametro("valorDescontosCreditos", Util.formatarMoedaReal( valorDescontosCreditos ) );
		 
		relatorioExtratoDebitoCliente.addParametro("valorTotalContas", Util.formatarMoedaReal(valorTotalContas));
		
		relatorioExtratoDebitoCliente.addParametro("colecaoContas",colecaoContas);
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";

		relatorioExtratoDebitoCliente.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioExtratoDebitoCliente,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}	
	
	
//	Vivianne Sousa - 02/07/2008
	//retornaa colecao de contas som as contas em revisão e 
	//o valor total das contas em revisão para diminuir do valor do documento 
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
	
	/**
	 * 
	 * @param idsGuias
	 * @param sessao
	 * @return
	 */
	private Object[] obterGuiasSelecionadas(String idsGuias, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuias = null;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		
		if (idsGuias != null && !idsGuias.equals("")){
			retorno = new Object[5];
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
						
						if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorMulta() != null	&& !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						
					}
				}
			}
			retorno[0] = colecaoGuias;
			retorno[1] = valorTotalGuiaPagamento;
			retorno[2] = valorAtualizacaoMonetaria;
			retorno[3] = valorJurosMora;
			retorno[4] = valorMulta;
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * @param relatorioExtratoDebitoCliente
	 * @param tipoPagamento
	 * @param valorCodigoBarra
	 * @param idLocalidade
	 * @param sequencialDocumentoCobranca
	 * @param idTipoDocumento
	 * @param idCliente
	 */
	private void montarRepresentacaoNumericaCodigoBarraExtratoDebitoCliente(RelatorioExtratoDebitoCliente relatorioExtratoDebitoCliente, Integer tipoPagamento,
			BigDecimal valorCodigoBarra, Integer idLocalidade,String sequencialDocumentoCobranca,Integer idTipoDocumento, Integer idCliente ){
		
		
		 String representacaoNumericaCodBarra = "";
			//[UC0229] Obtém a representação numérica do código de barra
			
		  representacaoNumericaCodBarra = Fachada.getInstancia()
			  		.obterRepresentacaoNumericaCodigoBarra(
			  				tipoPagamento,
			  				valorCodigoBarra,
			  				idLocalidade,
							null,
							null,
							null,
							null,
							null,
							sequencialDocumentoCobranca,
							idTipoDocumento,
							idCliente, null,null);
		
		
		  //Formata a representação númerica do código de barras
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
		
		  relatorioExtratoDebitoCliente.addParametro("representacaoNumericaCodBarra",representacaoNumericaCodBarraFormatada);
		
		  String representacaoNumericaCodBarraSemDigito = 
			representacaoNumericaCodBarra.substring(0, 11)
			+ representacaoNumericaCodBarra.substring(12, 23)
			+ representacaoNumericaCodBarra.substring(24, 35)
			+ representacaoNumericaCodBarra.substring(36, 47);
		
		   relatorioExtratoDebitoCliente.addParametro("representacaoNumericaCodBarraSemDigito",representacaoNumericaCodBarraSemDigito);
	}

	/**
	 * 
	 * @param relatorioExtratoDebitoCliente
	 * @param idDocumentoCobranca
	 * @param valorTotalContas
	 */
	private void obterRepresentacaoNumericaCodigoBarra(RelatorioExtratoDebitoCliente relatorioExtratoDebitoCliente, String idDocumentoCobranca, BigDecimal valorTotalContas){
		//representação numérica do código de barras
		//[SB0010] – Obter Representação numérica do Nosso Número da Ficha de Compensação
		StringBuilder nossoNumero = Fachada.getInstancia().obterNossoNumeroFichaCompensacao(
				DocumentoTipo.EXTRATO_DE_DEBITO.toString(),idDocumentoCobranca) ;
		String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
		relatorioExtratoDebitoCliente.addParametro("nossoNumero",nossoNumero.toString());
		
		Date dataVencimentoMais75 = Util.adicionarNumeroDiasDeUmaData(new Date(),75);
		String fatorVencimento = Fachada.getInstancia().obterFatorVencimento(dataVencimentoMais75);
		
		String especificacaoCodigoBarra = Fachada.getInstancia().
			obterEspecificacaoCodigoBarraFichaCompensacao(
		    ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, 
		    ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
		    valorTotalContas, nossoNumeroSemDV.toString(),
			ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, fatorVencimento);
		                                
		String representacaoNumericaCodigoBarraFichaCompensacao = 
				Fachada.getInstancia().obterRepresentacaoNumericaCodigoBarraFichaCompensacao(especificacaoCodigoBarra);
		
		relatorioExtratoDebitoCliente.addParametro("representacaoNumericaCodBarraSemDigito",especificacaoCodigoBarra);
		relatorioExtratoDebitoCliente.addParametro("representacaoNumericaCodBarra",representacaoNumericaCodigoBarraFichaCompensacao);
		
	}

}
