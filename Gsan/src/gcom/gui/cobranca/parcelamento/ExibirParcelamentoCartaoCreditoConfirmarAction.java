package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.ObterDadosConfirmarCartaoCreditoDebitoHelper;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade confirmar o parcelamento para pagamento atrav�s cart�o de cr�dito ou
 * cart�o de d�bito
 *
 * @author Hugo Amorim, Raphael Rossiter
 * @date 00/00/0000, 24/07/2006
 */
public class ExibirParcelamentoCartaoCreditoConfirmarAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirParcelamentoCartaoCreditoConfirmarAction");
		HttpSession sessao = request.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		ParcelamentoCartaoConfirmarForm form = (ParcelamentoCartaoConfirmarForm) actionForm;

		String confirmado = request.getParameter("confirmado");

		String idParcelamento = request.getParameter("numeroParcelamento");
		Boolean parcelamentoRecebidoParametro = Util.verificarIdNaoVazio(idParcelamento);

		if (!parcelamentoRecebidoParametro) {
			idParcelamento = form.getIdParcelamento();
			parcelamentoRecebidoParametro = (Boolean) sessao.getAttribute("parcelamentoRecebidoParametro");
			if(parcelamentoRecebidoParametro == null) parcelamentoRecebidoParametro = false;
		}

		//LIMPANDO O FORMUL�RIO
		String limpar = request.getParameter("limpar");
		if (limpar != null && limpar.equalsIgnoreCase("sim")) {
			this.limpar(form, sessao);
			return retorno;
		}

		//PESQUISAR IM�VEL
		String novoImovel = request.getParameter("novoImovel");

		if (!parcelamentoRecebidoParametro && novoImovel != null && !novoImovel.equals("")) {
			//CARREGANDO O FORMUL�RIO COM AS INFORMA��ES DO IM�VEL
			getImovel(form,request);

			if (form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")) {
				//IDENTIFICANDO A MODALIDADE DE CART�O QUE SER� UTILIZADA
				Short modalidade = Short.valueOf(form.getModalidadeCartao());
				Integer matriculaImovel = Integer.valueOf(form.getMatriculaImovel());

				ObterDadosConfirmarCartaoCreditoDebitoHelper helper = 
						fachada.obterDadosConfirmarCartaoCreditoDebito(modalidade, matriculaImovel);

				this.limparModalidadeCartaoDebito(form, sessao);
				this.limparModalidadeCartaoCredito(form, sessao);
				
				if (helper.getModalidade().equals(ConstantesSistema.MODALIDADE_CARTAO_CREDITO)){
					sessao.setAttribute("cartaoCredito","");
					sessao.setAttribute("colecaoParcelamentos", 
					helper.getColecaoParcelamentosModalidadeCredito());
				} else {
					sessao.setAttribute("cartaoDebito", "");
					
					//CONTA
					sessao.setAttribute("colecaoConta", helper
							.getDebitosImovelModalidadeDebito().getColecaoContasValoresImovel());
					
					//DEBITO_A_COBRAR
					sessao.setAttribute("colecaoDebitoACobrar", helper
							.getDebitosImovelModalidadeDebito().getColecaoDebitoACobrar());
					
					//GUIA_PAGAMENTO
					sessao.setAttribute("colecaoGuiaPagamento", helper
							.getDebitosImovelModalidadeDebito().getColecaoGuiasPagamentoValores());
					
					//PARCELAMENTO
					sessao.setAttribute("colecaoDebitoCreditoParcelamento", helper
							.getDebitosImovelModalidadeDebito().getColecaoDebitoCreditoParcelamentoHelper());
				}
			}
		}

		//PESQUISAR CLIENTE - UTILIZADO PELO POPUP
		String pesquisaCliente = request.getParameter("pesquisaCliente");
		if (pesquisaCliente != null && pesquisaCliente.equalsIgnoreCase("sim")) {
			this.pesquisarCliente(form.getIdCliente() ,form,fachada,request);
		}

		//PESQUISAR PARCELAMENTO (CART�O DE CR�DITO)
		if(idParcelamento!= null && !idParcelamento.equals("")){
			
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
			filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, idParcelamento));
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
			Collection<?> colecaoParcelamento = fachada.pesquisar(filtroParcelamento, Parcelamento.class.getName() );
			Parcelamento parcelamento = (Parcelamento) Util.retonarObjetoDeColecao(colecaoParcelamento);
			
			/*
			 * [FS0005 � Alerta Parcelamento Com Parcela Paga]
			 * [FS0006 � Parcelamento Sem D�bito a Cobrar]
			 * [FS0007 � Parcelamento Com Pagamento de Cart�o de Cr�dito J� Informado]
			 */
			int numeroPrestacaoCobradas = fachada.validarParcelamentoCartaoCredito(parcelamento);
			
			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
				if(numeroPrestacaoCobradas != 0){
					request.setAttribute("caminhoActionConclusao","/gsan/exibirParcelamentoCartaoCreditoConfirmarAction.do");
					//[FS0005 � Alerta Parcelamento Com Parcela Paga]
					return  montarPaginaConfirmacao("atencao.parcelamento_conta_paga",
					request,actionMapping, numeroPrestacaoCobradas + "");
				}
			}

			sessao.setAttribute("parcelamento", idParcelamento);
			sessao.setAttribute("ParcelamemtoSelecionado",idParcelamento);
			if(parcelamentoRecebidoParametro) {
				sessao.setAttribute("parcelamentoRecebidoParametro", true);
				form.setModalidadeCartao(ConstantesSistema.MODALIDADE_CARTAO_CREDITO.toString());
				form.setMatriculaImovel(parcelamento.getImovel().getId().toString());
				getImovel(form, request);
			}

			form.setNumeroPrestacoes(parcelamento.getNumeroPrestacoes());
			form.setValorPrestacao(parcelamento.getValorPrestacao());
//			form.setValorTotal(parcelamento.getValorParcelado());
			// Alterado por R�mulo Aur�lio CRC 4055 Data: 26/03/2010
			// Analista Rosana Carvalho
//			BigDecimal valorTotalParcelamento = new BigDecimal(0.0);
//			valorTotalParcelamento = parcelamento.getValorPrestacao().multiply(new BigDecimal(parcelamento.getNumeroPrestacoes()));
//			form.setValorTotal(valorTotalParcelamento);
			// Fim altera��o CRC 4055
			//Alterado por Vivianne Sousa - Eduardo Borges - 20/05/2015
			form.setValorTotal(parcelamento.getValorTotalParcelado());
			form.setParcelamento(parcelamento.getParcelamento());
			form.setIdParcelamento(idParcelamento);
		}
		
		
		if (limpar == null && novoImovel == null){
			
			//ANTECIPA��O DE PARCELAS DE PARCELAMENTO
	        Map<String, String[]> requestAntecipacaoParcelasMap = request.getParameterMap();
	        
	        this.obterAntecipacaoParcelasParcelamentosSelecionados(sessao, requestAntecipacaoParcelasMap, fachada);
		}
		
		
		//MARCA��O CHECKBOX
		if (form.getConta() != null){
			request.setAttribute("idsConta", this.montarString(form.getConta()));
		}
		
		if (form.getDebito() != null){
			request.setAttribute("idsDebito", this.montarString(form.getDebito()));
		}
		
		if (form.getGuiaPagamento() != null){
			request.setAttribute("idsGuia", this.montarString(form.getGuiaPagamento()));
		}
		
		if (form.getParcelamentoDebito() != null){
			request.setAttribute("idsParcelamentoDebito", this.montarString(form.getParcelamentoDebito()));
		}

		return retorno;
	}
	
	private String montarString(String[] selecionadas){
		
		String retorno = "";
		
		for (int i = 0; i < selecionadas.length; i++) {
			
			if (retorno.length() < 1){
				retorno = selecionadas[i];
			}
			else{
				retorno = retorno + "," + selecionadas[i];
			}
		}
		
		return retorno;
	}


	/**
	 * Obtendo os dados do im�vel informado 
	 *
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 00/00/0000, 05/01/2010
	 *
	 * @param parcelamentoCartaoConfirmarForm
	 * @param httpServletRequest
	 */
	private void getImovel(ParcelamentoCartaoConfirmarForm parcelamentoCartaoConfirmarForm,
			HttpServletRequest httpServletRequest) {
		
			Fachada fachada = Fachada.getInstancia();
		
			HttpSession sessao = httpServletRequest.getSession(false);

			FiltroImovel filtroImovel = new FiltroImovel();
			
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
			parcelamentoCartaoConfirmarForm.getMatriculaImovel()));
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
				
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);				
				
				//OBTENDO O ENDERE�O DO IM�VEL ABREVIADO
				String enderecoFormatado = fachada.pesquisarEnderecoAbreviadoCAER(imovel.getId());
				
				sessao.setAttribute("enderecoFormatado", enderecoFormatado);
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				parcelamentoCartaoConfirmarForm.setInscricaoImovel(imovel.getInscricaoFormatada());
				parcelamentoCartaoConfirmarForm.setEnderecoImovel(enderecoFormatado);
			} 
			else {
				
				//IM�VEL INEXISTENTE
				this.limpar(parcelamentoCartaoConfirmarForm, sessao);
				sessao.removeAttribute("inscricaoImovelEncontrada");
				parcelamentoCartaoConfirmarForm.setMatriculaImovel("");
				parcelamentoCartaoConfirmarForm.setInscricaoImovel("Matr�cula inexistente");
			}
	}
	
	/**
	 * Limpando os dados do formul�rio para inicio de uma nova requisi��o 
	 *
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 00/00/0000, 05/01/2010
	 *
	 * @param form
	 * @param sessao
	 */
	public void limpar(ParcelamentoCartaoConfirmarForm form,HttpSession sessao){
		
		//DADOS GERAIS
		form.setModalidadeCartao("");
		form.setMatriculaImovel("");
		form.setInscricaoImovel("");
		form.setEnderecoImovel("");
		form.setValorTotal(new BigDecimal(0.0));

		sessao.removeAttribute("inscricaoImovelEncontrada");
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("parcelamentoRecebidoParametro");

		//DADOS PARA TRANSA��O COM CART�O DE CR�DITO
		this.limparModalidadeCartaoCredito(form, sessao);
		
		//DADOS PARA TRANSA��O COM CART�O DE D�BITO
		this.limparModalidadeCartaoDebito(form, sessao);
		
		//DADOS DO POPUP PARA CADASTRO DA TRANSA��O
		form.setCartaoCredito("-1");
		form.setIdCliente("");
		form.setNomeCliente("");
		form.setAutorizacaoCartao("");
		form.setValidadeCartao("");
		form.setDocumentoCartao("");
		form.setNumeroIdentificacaoTransacao("");
		form.setNumeroCartao("");
		form.setValorTransacao("");
		form.setDataOperadora("");
		form.setQtdParcelas("");
	}
	
	/**
	 * Limpando os dados do formul�rio para inicio de uma nova requisi��o 
	 *
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 00/00/0000, 05/01/2010
	 *
	 * @param form
	 * @param sessao
	 */
	public void limparModalidadeCartaoCredito(ParcelamentoCartaoConfirmarForm form,HttpSession sessao){
		
		form.setValorTotal(new BigDecimal(0.0));
		
		form.setIdParcelamento("");
		form.setValorPrestacao(new BigDecimal(0.0));
		form.setNumeroPrestacoes(new Short("0"));
		form.setParcelamento(null);
		
		sessao.removeAttribute("cartaoCredito");
		sessao.removeAttribute("ParcelamemtoSelecionado");
		sessao.removeAttribute("parcelamento");
		sessao.removeAttribute("colecaoParcelamentos");
		
		sessao.removeAttribute("colecaoTransacao");
	}
	
	/**
	 * Limpando os dados do formul�rio para inicio de uma nova requisi��o 
	 *
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 00/00/0000, 05/01/2010
	 *
	 * @param form
	 * @param sessao
	 */
	public void limparModalidadeCartaoDebito(ParcelamentoCartaoConfirmarForm form,HttpSession sessao){
		
		form.setValorTotal(new BigDecimal(0.0));
		
		sessao.removeAttribute("cartaoDebito");
		sessao.removeAttribute("colecaoConta");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("colecaoGuiaPagamento");
		sessao.removeAttribute("colecaoDebitoCreditoParcelamento");
		
		sessao.removeAttribute("colecaoTransacao");
		
		form.setConta(new String[0]);
		form.setGuiaPagamento(new String[0]);
		form.setDebito(new String[0]);
		form.setParcelamentoDebito(new String[0]);
	}
	
	/**
	 * Obtendo os dados do cliente 
	 *
	 * @author Hugo Amorim, Raphael Rossiter
	 * @date 00/00/0000, 05/01/2010
	 *
	 * @param idCliente
	 * @param filtro
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente( String idCliente,
			ParcelamentoCartaoConfirmarForm filtro,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(
		FiltroCliente.ID, idCliente));
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
		Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			
			filtro.setIdCliente("" + ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			filtro.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0)).getNome());
			
			httpServletRequest.setAttribute("idClienteNaoEncontrado", "true");
			httpServletRequest.setAttribute("nomeCampo", "cepFiltro");

		} 
		else {
			
			filtro.setIdCliente("");
			
			httpServletRequest.setAttribute("idClienteNaoEncontrado", "exception");
			filtro.setNomeCliente("Cliente inexistente");

			httpServletRequest.setAttribute("nomeCampo", "idClienteFiltro");
		}
	}
	
	/**
	 * [UC0630] Solicitar Emiss�o do Extrato de D�bitos 
	 *
	 * @author Raphael Rossiter
	 * @date 26/04/2010
	 *
	 * @param sessao
	 * @param requestMap
	 * @param fachada
	 * @return Object[]
	 */
	private Object[] obterAntecipacaoParcelasParcelamentosSelecionados(HttpSession sessao, 
			Map<String, String[]> requestMap, Fachada fachada){
		
		Object[] retorno = null;
		
		//PARCELAMENTOS DISPONIBILIZADOS PARA O USU�RIO SELECIONAR
		Collection colecaoDebitoCreditoParcelamentoSessao = (Collection) 
		sessao.getAttribute("colecaoDebitoCreditoParcelamento");
		
		if (colecaoDebitoCreditoParcelamentoSessao != null && 
			!colecaoDebitoCreditoParcelamentoSessao.isEmpty()){
			
			Iterator itColecaoDebitoCreditoParcelamentoSessao = colecaoDebitoCreditoParcelamentoSessao.iterator();
			DebitoCreditoParcelamentoHelper debitoCreditoParcelamentoHelper = null;
			
			BigDecimal valorTotalDebito = BigDecimal.ZERO;
			BigDecimal valorTotalCredito = BigDecimal.ZERO;
			
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitos = new ArrayList();
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditos = new ArrayList();
			
			while (itColecaoDebitoCreditoParcelamentoSessao.hasNext()){
				
				debitoCreditoParcelamentoHelper = (DebitoCreditoParcelamentoHelper) 
				itColecaoDebitoCreditoParcelamentoSessao.next();
			
				if (requestMap.get("parc" + debitoCreditoParcelamentoHelper.getParcelamento().getId()) != null) {
					
					//QUANTIDADE DE PARCELAS QUE SER�O ANTECIPADAS
					String qtdAntecipacaoParcelas = (requestMap.get("parc" + 
					debitoCreditoParcelamentoHelper.getParcelamento().getId()))[0];
					
					if(qtdAntecipacaoParcelas != null && !qtdAntecipacaoParcelas.equals("")){
						
						//INICIALIZANDO O OBJETO DE RETORNO
						if (retorno == null){
							retorno = new Object[4];
						}
						
						//QUANTIDADE DE PARCELAS QUE SER�O ANTECIPADAS
						debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(
						Integer.valueOf(qtdAntecipacaoParcelas));
						
						/*
						 * SELECIONANDO OS D�BITOS RELACIONADOS AO PARCELAMENTO E CALCULANDO O VALOR QUE SER�
						 * COLOCADO NO EXTRATO DE ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA PARA
						 * ANTECIPA��O.
						 */
						if (debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento() != null &&
					       !debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento().isEmpty()){
						
							Collection colecaoDebito = debitoCreditoParcelamentoHelper
						   .getColecaoDebitoACobrarParcelamento();
						
						   Iterator iterDebito = colecaoDebito.iterator();
						
						   while (iterDebito.hasNext()) {
							
							   DebitoACobrar debitoACobrar = (DebitoACobrar) iterDebito.next();
							
							   /*
							    * [UC0630] Solicitar Emiss�o do Extrato de D�bitos
							    * [FS0003] � Quantidade de Parcelas Informadas Inv�lida
							    */
							   fachada.verificarQuantidadeParcelasInformada(debitoACobrar, 
							   Short.valueOf(qtdAntecipacaoParcelas));
							   
							   //CALCULANDO O VALOR QUE SER� ANTECIPADO
							   valorTotalDebito = valorTotalDebito.add(debitoACobrar
							   .getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
						   }
						   
						   //D�BITOS A COBRAR QUE FAR�O PARTE DO EXTRATO DE D�BITO
						   colecaoAntecipacaoDebitos.add(debitoCreditoParcelamentoHelper);
						}
						
						
						/*
						 * SELECIONANDO OS CR�DITOS RELACIONADOS AO PARCELAMENTO E CALCULANDO O VALOR QUE SER�
						 * COLOCADO NO EXTRATO DE ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA PARA
						 * ANTECIPA��O.
						 */
						if(debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento() != null &&
						   !debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento().isEmpty()){
							
							Collection colecaoCredito = debitoCreditoParcelamentoHelper
							.getColecaoCreditoARealizarParcelamento();
							
							Iterator iterCredito = colecaoCredito.iterator();
							
							while (iterCredito.hasNext()) {
								
								CreditoARealizar creditoARealizar = (CreditoARealizar) iterCredito.next();
								
								//CALCULANDO O VALOR QUE SER� ANTECIPADO
								valorTotalCredito = valorTotalCredito.add(creditoARealizar
								.getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
							}
							
							//CR�DITOS A REALIZAR QUE FAR�O PARTE DO EXTRATO DE D�BITO
							colecaoAntecipacaoCreditos.add(debitoCreditoParcelamentoHelper);
						}
					}
					else{
						
						//QUANTIDADE DE PARCELAS QUE SER�O ANTECIPADAS
						debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(null);
					}
				}
				else{
					
					//QUANTIDADE DE PARCELAS QUE SER�O ANTECIPADAS
					debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(null);
				}
			}
			
			if (retorno != null){
				
				retorno[0] = colecaoAntecipacaoDebitos;
				retorno[1] = valorTotalDebito;
				retorno[2] = colecaoAntecipacaoCreditos;
				retorno[3] = valorTotalCredito;
			}
		}
		
		return retorno;
	}
}

