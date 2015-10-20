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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que finaliza a p�gina de atualizar pagamento
 * 
 * @author Pedro Alexandre
 * @created 22/03/2006
 */
public class AtualizarPagamentosAction extends GcomAction {

	/**
	 * @author Pedro Alexandre
	 * @date 22/03/2006
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

		// Cria a vari�vel de retorno e seta o mapeamento para a tela de sucesso
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		 
		// Recupera o form
		ManterPagamentoActionForm manterPagamentoActionForm = 
			(ManterPagamentoActionForm) actionForm;

		// Recupera o pagamento que vai ser atualizado da sess�o
		Pagamento pagamentoAtualizacao = 
			(Pagamento) this.getSessao(httpServletRequest).getAttribute("pagamento");

		// Recupera o valor do pagamento atual e seta na vari�vel que vai
		// armazenar
		// o valor do pagamento anteriro
		BigDecimal valorPagamentoAnteriror = 
			pagamentoAtualizacao.getValorPagamento();

		// Recupera a vari�vel para indicar se o usu�rio apertou o bot�o de
		// confirmar da tela de confirma��o
		String confirmado = httpServletRequest.getParameter("confirmado");

		// [FS0019] - Validar data do pagamento
		// Recupera a data de pagamento e verifica se a data � uma data v�lida
		String dataPagamentoString = 
			manterPagamentoActionForm.getDataPagamento();
		Date dataPagamento = null;
		SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataPagamento = dataFormato.parse(dataPagamentoString);
		} catch (ParseException ex) {
			throw new ActionServletException("atencao.data_pagamento_invalida");
		}

		// Recupera o c�digo da forma de arrecada��o e pesquisa o objeto no
		// sistema
		String idFormaArrecadacao = manterPagamentoActionForm.getIdFormaArrecadacao();
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		arrecadacaoForma.setId(new Integer(idFormaArrecadacao));

		// Recupera o c�digo do aviso banc�rio e pesquisa o objeto no sistema
		String idAvisoBancario = manterPagamentoActionForm.getIdAvisoBancario();
		FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
		filtroAvisoBancario.adicionarParametro(
			new ParametroSimples(
				FiltroAvisoBancario.ID, 
				idAvisoBancario));
		
		AvisoBancario avisoBancario = 
			(AvisoBancario) (this.getFachada().pesquisar(filtroAvisoBancario, 
				AvisoBancario.class.getName())).iterator().next();

		// Recupera o c�digo da situa��o atual do pagamento
		String idSituacaoAtualPagamento = 
			manterPagamentoActionForm.getIdSituacaoAtualPagamento();

		// cria as vari�veis que v�o armazenar as situa��es atual e anterior do
		// pagamento
		PagamentoSituacao situacaoPagamentoAtual = null;
		PagamentoSituacao situacaoPagamentoAnterior = null;

		// Caso a nova situa��o atual do pagamento tenha sido informada na
		// p�gina
		if (idSituacaoAtualPagamento != null && 
			!idSituacaoAtualPagamento.trim().equalsIgnoreCase("")) {
			
			// Cria o filtro da situa��o de pagamento e pesquisa a situa��o
			// informada no sistema
			FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
			
			/*
			 * Colocado por Raphael Rossiter em 19/10/2007
			 * OBJ:Selecionar apenas a situacao escolhida pelo usuario
			 */
			filtroPagamentoSituacao.adicionarParametro(
				new ParametroSimples(FiltroPagamentoSituacao.CODIGO,
				new Integer(idSituacaoAtualPagamento)));
			
			//Situa��o atual
			situacaoPagamentoAtual = 
				(PagamentoSituacao) Util.retonarObjetoDeColecao(
					this.getFachada().pesquisar(filtroPagamentoSituacao, 
						PagamentoSituacao.class.getName()));

			// Seta a situa��o atual do pagamento para a situa��o anterior
			situacaoPagamentoAnterior = pagamentoAtualizacao.getPagamentoSituacaoAtual();
			 
		} else {
			// Caso a nova situa��o do pagamento n�o tenha sido informada
			// o pagamento continua com as mesmas situa��es inalteradas
			if (pagamentoAtualizacao.getPagamentoSituacaoAtual() != null) {
				situacaoPagamentoAtual = pagamentoAtualizacao.getPagamentoSituacaoAtual();
			}

			if (pagamentoAtualizacao.getPagamentoSituacaoAnterior() != null) {
				situacaoPagamentoAnterior = pagamentoAtualizacao.getPagamentoSituacaoAnterior();
			}
		}

		// Recupera o valor do pagamento
		BigDecimal valorPagamentoNovo = 
			Util.formatarMoedaRealparaBigDecimal(manterPagamentoActionForm.getValorPagamento());

		// Recupera o c�digo do im�vel
		String idImovel = manterPagamentoActionForm.getIdImovel();

		// Cria a vari�vel que vai armazenar o im�vel informado
		Imovel imovel = null;

		// Caso o usu�rio tenha informado o im�vel
		if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {

			// [FS0004] - Verificar exist�ncia da matr�cula do im�vel
			imovel = this.getFachada().pesquisarImovelDigitado(new Integer(idImovel));

			if (imovel == null) {
				throw new ActionServletException("atencao.naocadastrado", 
					null,
					"Matr�cula do im�vel");
			}

		}

		// Recupera o tipo de documento
		String idTipoDocumento = manterPagamentoActionForm.getIdTipoDocumento();

		// Recupera o c�digo da localidade
		String idLocalidade = manterPagamentoActionForm.getIdLocalidade();

		// Cria a vari�vel que vai armazenar a localidade informada
		Localidade localidade = null;

		// Caso o tipo de documento n�o seja conta
		if (!idTipoDocumento.equals(DocumentoTipo.CONTA.toString())) {
			if (idLocalidade != null && !idLocalidade.equalsIgnoreCase("")) {
				// [FS0003] - Verificar exist�ncia da localidade
				localidade = 
					this.getFachada().pesquisarLocalidadeDigitada(
						new Integer(idLocalidade));
			}
		}

		// Caso o im�vel tenha sido informado verificar se a localidade
		// informada � igual a do im�vel
		if (idImovel != null && !idImovel.equalsIgnoreCase("")) {
			if (idLocalidade == null || idLocalidade.equalsIgnoreCase("")) {
				idLocalidade = "" + imovel.getLocalidade().getId();
			} else {
				// [FS0005] - Verificar localidade da matr�cula do im�vel
				if (!this.getFachada().verificarLocalidadeMatriculaImovel(idLocalidade,imovel)) {
					
					throw new ActionServletException(
						"atencao.localidade_imovel_diferente", 
						imovel.getLocalidade().getId().toString(),
						idLocalidade);
				}
			}
		}

		// Recupera o tipo de d�bito
		String idTipoDebito = manterPagamentoActionForm.getIdTipoDebito();

		// Cria a vari�vel que vai armazenar o tipo de d�bito
		DebitoTipo debitoTipo = null;

		// Caso nenhum tipo de documento informado, levanta uma exce��o para o
		// usu�rio
		// indicando que o tipo de documento n�o foi informado
		if (idTipoDocumento == null || idTipoDocumento.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.naoinformado", 
				null,
				"Tipo de Documento");
		}
		
		//[FS0025] � Verificar valor do aviso banc�rio
        //Caso o valor calculado do aviso banc�rio seja maior que valor informado 
        if ((avisoBancario.getValorArrecadacaoCalculado().subtract(avisoBancario.getValorDevolucaoCalculado()))
        	.compareTo(avisoBancario.getValorArrecadacaoInformado().subtract(avisoBancario.getValorDevolucaoInformado())) == 1 ){
        	
        	throw new ActionServletException("atencao.soma_dos_valores_maior_informado");
        }
        
		// Cria o objeto que vai armazenar o tipo de documento
		DocumentoTipo documentoTipo = new DocumentoTipo();

		// Caso o tipo de documento seja conta
		if (idTipoDocumento.equals(DocumentoTipo.CONTA.toString())) {
			// Seta o tipo de documento para conta
			documentoTipo.setId(new Integer(DocumentoTipo.CONTA));

			// Recupera a refer�ncia da conta
			String referenciaConta = 
				manterPagamentoActionForm.getReferenciaConta();

			// [FS0008] - Verificar exist�ncia da conta
			Conta conta = 
				this.getFachada().pesquisarContaDigitadaInserirPagamentos(idImovel,referenciaConta);
			
			ContaHistorico contaHistorico = 
				this.getFachada().pesquisarContaHistoricoDigitadaInserirPagamentos(idImovel,referenciaConta);

			// Caso a conta com a refer�ncia informada n�o esteja cadastrada
			// no sistema
			if (conta == null) {
				// Caso o usu�rio n�o tenha passado pela p�gina de
				// confirma��o
				if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

					httpServletRequest.setAttribute(
						"caminhoActionConclusao",
						"/gsan/atualizarPagamentosAction.do");

					// Monta a p�gina de confirma��o para perguntar se o
					// usu�rio quer atualizar
					// o pagamento mesmo sem a conta existir para a
					// refer�ncia e o im�vel informados
					return montarPaginaConfirmacao(
						"atencao.referencia.naocadastrada",
						httpServletRequest, 
						actionMapping,
						referenciaConta);
				}
			}

			// Formata a refer�ncia da conta para AAAAMM
			Integer anoMesReferencia = 
				new Integer(Util.formatarMesAnoParaAnoMesSemBarra(referenciaConta));

			// [SB0004] Atualiza Pagamento
			// Atualiza o pagamento para conta
			pagamentoAtualizacao.setAnoMesReferenciaPagamento(anoMesReferencia);
			pagamentoAtualizacao.setValorPagamento(valorPagamentoNovo);
			pagamentoAtualizacao.setDataPagamento(dataPagamento);
			pagamentoAtualizacao.setPagamentoSituacaoAtual(situacaoPagamentoAtual);
			pagamentoAtualizacao.setPagamentoSituacaoAnterior(situacaoPagamentoAnterior);
			pagamentoAtualizacao.setDebitoTipo(null);
			
			ContaGeral contaGeral = new ContaGeral();
			if (conta != null) {
				contaGeral.setId(conta.getId());
				contaGeral.setConta(conta);
			}else if( contaHistorico != null ){
				
				contaGeral.setId(contaHistorico.getId());
				contaGeral.setContaHistorico(contaHistorico);
				
			}
			
			pagamentoAtualizacao.setContaGeral(contaGeral);
			pagamentoAtualizacao.setGuiaPagamento(null);
			pagamentoAtualizacao.setDebitoACobrarGeral(null);
			pagamentoAtualizacao.setLocalidade(imovel.getLocalidade());
			pagamentoAtualizacao.setDocumentoTipo(documentoTipo);
			pagamentoAtualizacao.setImovel(imovel);
			pagamentoAtualizacao.setCliente(null);

			// Caso o tipo de documento seja guia de pagamento
		} else if (idTipoDocumento.equals(DocumentoTipo.GUIA_PAGAMENTO.toString())) {

			// Seta o tipo de documento para guia de pagamento
			documentoTipo.setId(new Integer(DocumentoTipo.GUIA_PAGAMENTO));

			// Recupera o c�digo do cliente
			String idCliente = manterPagamentoActionForm.getIdCliente();

			// [FS0006] - Verificar preenchimento do im�vel e do cliente
			this.getFachada().verificarPreeenchimentoImovelECliente(idImovel,idCliente);

			// Caso o usu�rio tenha informado o cliente
			// Recupera o cliente informado do sistema
			Cliente cliente = null;
			if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
				// [FS0007] - Verificar exist�ncia do c�digo do cliente
				cliente = 
					this.getFachada().pesquisarClienteDigitado(new Integer(idCliente));
			}

			// Recupera o c�digo da guia de pagamento
			String idGuiaPagamento = manterPagamentoActionForm.getIdGuiaPagamento();

			// [FS0021] - Verificar preenchimento da guia de pagamento e do
			// tipo de d�bito
			this.getFachada().verificarPreeenchimentoGuiaPagamentoETipoDebito(
				idGuiaPagamento, idTipoDebito);

			// Caso o usu�rio informou a guia de pagamento
			// Recupera a guia de pagamento informada do sistema
			GuiaPagamento guiaPagamento = null;
			if (idGuiaPagamento != null && !idGuiaPagamento.trim().equals("")) {

				// [FS0022] - Verificar exist�ncia da guia de pagamento
				guiaPagamento = 
					this.getFachada().pesquisarGuiaPagamentoDigitada(
						idImovel, 
						idCliente, 
						idGuiaPagamento);

				// Caso exista guia de pagamento, seta o tipo de d�bito da
				// guia de pagamento
				// para o tipo de d�bito do pagamento que vai ser inserido
				debitoTipo = guiaPagamento.getDebitoTipo();

				// [FS0010] - Verificar localidade da guia de pagamento
				this.getFachada().verificarLocalidadeGuiaPagamento(guiaPagamento,idLocalidade);
			}

			// Caso o tipo de d�bito tenha sido informado
			if (idTipoDebito != null && !idTipoDebito.trim().equals("")) {

				// [FS0020] - Verificar exist�ncia do tipo de d�bito
				debitoTipo = 
					this.getFachada().pesquisarTipoDebitoDigitado(
						new Integer(idTipoDebito));

				// [FS0009] - Verificar exist�ncia de guia de pagamento com
				// o tipo de d�bito informado
				if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
					
					guiaPagamento = 
						this.getFachada().verificarExistenciaGuiaPagamentoComTipoDebito(
							debitoTipo, 
							idImovel, 
							null);
				}

				if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
					
					guiaPagamento = 
						this.getFachada().verificarExistenciaGuiaPagamentoComTipoDebito(
							debitoTipo, 
							null, 
							idCliente);
				}

				// Caso n�o exista nenhuma guia de pagamento com o tipo de
				// d�bito informado
				if (guiaPagamento == null) {
					
					// Caso o usu�rio n�o tenha passado pela p�gina de
					// confirma��o do wizard
					if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {

						// Monta a p�gina de confirma��o para perguntar se o
						// usu�rio quer inserir
						// o pagamento mesmo sem existir guia de pagamento
						// para o tipo de d�bito e o im�vel
						// ou cliente informados
						if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
							
							httpServletRequest.setAttribute(
								"caminhoActionConclusao",
								"/gsan/atualizarPagamentosAction.do");

							return montarPaginaConfirmacao(
								"atencao.guia_pagamento.naocadastrada",
								httpServletRequest, 
								actionMapping,
								new String[]{debitoTipo.getDescricao(),"Im�vel "+idImovel});
						}
						return montarPaginaConfirmacao(
							"atencao.guia_pagamento.naocadastrada",
							httpServletRequest, 
							actionMapping,
							new String[]{debitoTipo.getDescricao(),"Cliente "+ idCliente}							);
					}
				}
			}

			// [SB0004] Atualiza Pagamento
			// Atualiza o pagamento para a guia de pagamento
			pagamentoAtualizacao.setAnoMesReferenciaPagamento(null);
			pagamentoAtualizacao.setValorPagamento(valorPagamentoNovo);
			pagamentoAtualizacao.setDataPagamento(dataPagamento);
			pagamentoAtualizacao.setPagamentoSituacaoAtual(situacaoPagamentoAtual);
			pagamentoAtualizacao.setPagamentoSituacaoAnterior(situacaoPagamentoAnterior);
			pagamentoAtualizacao.setDebitoTipo(debitoTipo);
			pagamentoAtualizacao.setContaGeral(null);
			pagamentoAtualizacao.setGuiaPagamento(guiaPagamento);
			pagamentoAtualizacao.setDebitoACobrarGeral(null);
			pagamentoAtualizacao.setLocalidade(localidade);
			pagamentoAtualizacao.setDocumentoTipo(documentoTipo);
			pagamentoAtualizacao.setImovel(imovel);
			pagamentoAtualizacao.setCliente(cliente);

			// Caso o tipo de documento seja d�bito a cobrar
		} else if (idTipoDocumento.equals(DocumentoTipo.DEBITO_A_COBRAR.toString())) {

			// Seta o tipo de documento do pagamento para d�bito a cobrar
			documentoTipo.setId(new Integer(DocumentoTipo.DEBITO_A_COBRAR));

			// Recupera o c�digo do d�bito a cobrar
			String idDebitoACobrar = 
				manterPagamentoActionForm.getIdDebitoACobrar();

			// [FS0023] - Verificar preenchimento do d�bito a cobrar e do
			// tipo de d�bito
			this.getFachada().verificarPreeenchimentoDebitoACobrarETipoDebito(
					idDebitoACobrar, 
					idTipoDebito);
			
			BigDecimal valorInformado = 
				Util.formatarMoedaRealparaBigDecimal(
					manterPagamentoActionForm.getValorPagamento());
			
			// Caso o usu�rio informou o d�bito a cobrar
			// Recupera o d�bito a cobrar informado do sistema
			DebitoACobrarGeral debitoACobrarGeral = null;
			
			if (idDebitoACobrar != null && !idDebitoACobrar.trim().equals("")) {

				// [FS0024] - Verificar exist�ncia do d�bito a cobrar
				debitoACobrarGeral = 
					this.getFachada().pesquisarDebitoACobrarGeralDigitado(
						idImovel, 
						idDebitoACobrar);

				if(debitoACobrarGeral.getDebitoACobrar() != null){

					// Caso exista o d�bito a cobrar, seta o tipo de d�bito do
					// d�bito a cobrar
					// para o tipo de d�bito do pagamento que vai ser inserido
					debitoTipo = debitoACobrarGeral.getDebitoACobrar().getDebitoTipo();
				
					//[FS0013] - Verificar localidade do d�bito a cobrar
					this.getFachada().verificarLocalidadeDebitoACobrarGeral(debitoACobrarGeral,idLocalidade);
				
					BigDecimal valorFaltaDebito = BigDecimal.ZERO;
					BigDecimal valorDebito = debitoACobrarGeral.getDebitoACobrar().getValorDebito();
					
					short numeroPrestacaoDebito = 
						debitoACobrarGeral.getDebitoACobrar().getNumeroPrestacaoDebito();
					
					short numeroPrestacaoCobrada = 
						debitoACobrarGeral.getDebitoACobrar().getNumeroPrestacaoCobradas();
			      
//					valorDebito = 
//						valorDebito.divide(new BigDecimal(numeroPrestacaoDebito+""));
					
					valorDebito = Util.dividirArredondando(valorDebito, new BigDecimal(numeroPrestacaoDebito+"") );
			      
					valorFaltaDebito = valorFaltaDebito.add(debitoACobrarGeral.getDebitoACobrar().getValorDebito());
					valorFaltaDebito = valorFaltaDebito.subtract(valorDebito);
					valorFaltaDebito = 
						valorFaltaDebito.multiply(new BigDecimal(numeroPrestacaoCobrada+""));
		      
					if(!valorInformado.equals(valorFaltaDebito)){
						debitoACobrarGeral = null;
					}
				
				}else if(debitoACobrarGeral.getDebitoACobrarHistorico() !=null){
				
					// Caso exista o d�bito a cobrar, seta o tipo de d�bito do
					// d�bito a cobrar
					// para o tipo de d�bito do pagamento que vai ser inserido
					debitoTipo = debitoACobrarGeral.getDebitoACobrarHistorico().getDebitoTipo();
				
					//[FS0013] - Verificar localidade do d�bito a cobrar
					this.getFachada().verificarLocalidadeDebitoACobrarGeral(
						debitoACobrarGeral,idLocalidade);
				
					BigDecimal valorFaltaDebito = BigDecimal.ZERO;
					BigDecimal valorDebito = debitoACobrarGeral.getDebitoACobrarHistorico().getValorDebito();
					
					short numeroPrestacaoDebito = 
						debitoACobrarGeral.getDebitoACobrarHistorico().getPrestacaoDebito();
					short numeroPrestacaoCobrada = 
						debitoACobrarGeral.getDebitoACobrarHistorico().getPrestacaoCobradas();
			      
					valorDebito = valorDebito.divide(new BigDecimal(numeroPrestacaoDebito),BigDecimal.ROUND_UP);
					valorFaltaDebito = valorFaltaDebito.add(debitoACobrarGeral.getDebitoACobrarHistorico().getValorDebito());
					valorFaltaDebito = valorFaltaDebito.subtract(valorDebito);
					valorFaltaDebito = valorFaltaDebito.multiply(new BigDecimal(numeroPrestacaoCobrada+""));
		      
		      
					if(!valorInformado.equals(valorFaltaDebito)){
						debitoACobrarGeral = null;
					}
				
				}
			}
			
			// Caso o tipo de d�bito tenha sido informado
/*			if (idTipoDebito != null && !idTipoDebito.trim().equals("")) {

				// [FS0020] - Verificar exist�ncia do tipo de d�bito
				debitoTipo = 
					this.getFachada().pesquisarTipoDebitoDigitado(
						new Integer(idTipoDebito));

				// [FS0012] - Verificar exist�ncia de d�bito a cobrar com o
				// tipo de d�bito
				debitoACobrarGeral = 
					this.getFachada().verificarExistenciaDebitoACobrarGeralComTipoDebito(
						debitoTipo, 
						idImovel, 
						valorInformado);

				// Caso n�o exista nenhum d�bito a cobrar com o tipo de
				// d�bito informado
				if (debitoACobrarGeral == null ||(
					debitoACobrarGeral.getDebitoACobrar() == null && 
					debitoACobrarGeral.getDebitoACobrarHistorico() == null)) {
					
					// Caso o usu�rio n�o tenha passado pela p�gina de
					// confirma��o do wizard
					if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
						
						httpServletRequest.setAttribute(
							"caminhoActionConclusao",
							"/gsan/atualizarPagamentosAction.do");

						// Monta a p�gina de confirma��o do wizard para
						// perguntar se o usu�rio quer inserir
						// o pagamento mesmo sem existir d�bito a cobrar
						// para o tipo de d�bito e o im�vel informados
						return montarPaginaConfirmacao(
							"atencao.debito_a_cobrar.naocadastrado",
							httpServletRequest, 
							actionMapping,
							debitoTipo.getDescricao(), idImovel);
					}
				}
			}*/
			if(debitoTipo == null){
				debitoTipo = new DebitoTipo();
				if(manterPagamentoActionForm.getIdTipoDebito() != null && 
						!manterPagamentoActionForm.getIdTipoDebito().equals("")){
						debitoTipo.setId(new Integer(manterPagamentoActionForm.getIdTipoDebito()));
				}
			}else if(manterPagamentoActionForm.getIdTipoDebito() != null && 
					!manterPagamentoActionForm.getIdTipoDebito().equals("") &&
					(debitoTipo.getId() == null || debitoTipo.getId().equals(""))){
				debitoTipo.setId(new Integer(manterPagamentoActionForm.getIdTipoDebito()));
			}

			// [SB0004] Atualiza Pagamento
			// Atualiza o pagamento para o d�bito a cobrar
			pagamentoAtualizacao.setAnoMesReferenciaPagamento(null);
			pagamentoAtualizacao.setValorPagamento(valorPagamentoNovo);
			pagamentoAtualizacao.setDataPagamento(dataPagamento);
			pagamentoAtualizacao.setPagamentoSituacaoAtual(situacaoPagamentoAtual);
			pagamentoAtualizacao.setPagamentoSituacaoAnterior(situacaoPagamentoAnterior);
			pagamentoAtualizacao.setDebitoTipo(debitoTipo);
			pagamentoAtualizacao.setContaGeral(null);
			pagamentoAtualizacao.setGuiaPagamento(null);
			
			//DebitoACobrarGeral debitoACobrarGeral = null;
			if(debitoACobrarGeral != null){
				if(debitoACobrarGeral.getDebitoACobrar() != null){
					debitoACobrarGeral.setId(debitoACobrarGeral.getDebitoACobrar().getId());
				}else if(debitoACobrarGeral.getDebitoACobrarHistorico() != null){
					debitoACobrarGeral.setId(debitoACobrarGeral.getDebitoACobrarHistorico().getId());
				}
			}
			
			pagamentoAtualizacao.setDebitoACobrarGeral(debitoACobrarGeral);
			
			pagamentoAtualizacao.setLocalidade(localidade);
			pagamentoAtualizacao.setDocumentoTipo(documentoTipo);
			pagamentoAtualizacao.setImovel(imovel);
			pagamentoAtualizacao.setCliente(null);
		}

		this.getFachada().atualizarPagamento(pagamentoAtualizacao);

		// Alterado por S�vio Luiz data:16/03/2007
		BigDecimal valorArrecadacaoCalculado = avisoBancario.getValorArrecadacaoCalculado();
		valorArrecadacaoCalculado = valorArrecadacaoCalculado.subtract(valorPagamentoAnteriror);
		valorArrecadacaoCalculado = valorArrecadacaoCalculado.add(valorPagamentoNovo);

		avisoBancario.setValorArrecadacaoCalculado(valorArrecadacaoCalculado);
		avisoBancario.setUltimaAlteracao(new Date());
		
		this.getFachada().atualizar(avisoBancario);

		// Caso o retorno seja para a tela de sucesso,
		// Monta a tela de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			montarPaginaSucesso(httpServletRequest,
					"Pagamento atualizado com sucesso.",
					"Realizar outra Manuten��o de Pagamento",
					"exibirFiltrarPagamentoAction.do?tela=manterPagamento&menu=sim");

		}

		// Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
	

}
