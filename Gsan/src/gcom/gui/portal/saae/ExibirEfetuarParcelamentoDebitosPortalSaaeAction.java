package gcom.gui.portal.saae;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1191] Efetuar Parcelamento de Debitos Atraves da Loja Virtual
 * 
 * Classe Responsavel por carregar todos os dados necessarios para exibir
 * a tela parcelamento_debitos_portal_efetuar.jsp 
 * 
 * @author Cesar Medeiros
 * @since 16/09/2015
 */

public class ExibirEfetuarParcelamentoDebitosPortalSaaeAction extends GcomAction {

	private ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = null;
	private boolean reativacaoDaligacaoAgua = false;	

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno = actionMapping.findForward("efetuarParcelamento");
		Fachada fachada = Fachada.getInstancia();
		httpServletRequest.setAttribute("voltarServicos", true);
		String method = httpServletRequest.getParameter("method");
		String erroEfetuarParcelamento = (String)httpServletRequest.getAttribute("erroEfetuarParcelamento");
		Boolean mensagemReativacao = (Boolean)httpServletRequest.getAttribute("mensagemReativacao");
		
		Integer matricula = (Integer) sessao.getAttribute("matricula");
	
		EfetuarParcelamentoDebitosPortalSaaeActionForm form = (EfetuarParcelamentoDebitosPortalSaaeActionForm) actionForm;

		if (matricula != null) {
			matricula = (Integer) sessao.getAttribute("matricula");
		} else {
			matricula = Integer.parseInt((String) sessao.getAttribute("idImovelPrincipalAba"));
			form.setIndicadorFormaCobranca(CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString());
			form.setMatriculaImovel(String.valueOf(matricula));
			sessao.setAttribute("gsanPortal", "SIM");
		}

		/*
		 * Se houver nao houver erro no parcelamento e nao houver uma mesangem de reativacao do ramal
		 * (quando o efetuar parcelamento for bem sucedido e tiver religacao de Agua).
		 */
		if(!Util.verificarNaoVazio(erroEfetuarParcelamento) && (mensagemReativacao == null || (mensagemReativacao != null && !mensagemReativacao))){
			//[FS0003] Ao Verificar existencia de parcelamento ativo
			Collection<Parcelamento> parcelamentos = fachada.pesquisarParcelamentosSituacaoNormal(matricula);
						
			if(Util.isVazioOrNulo(parcelamentos)){
				// [FS0012] Verificar existencia de parcelamento no mes
				parcelamentos = fachada.verificarParcelamentoMesImovel(matricula);
				if(Util.isVazioOrNulo(parcelamentos)){
					//Caso a solicitacao tenha vindo da pagina de servicos, limpar o form.
					String paginaServicos = httpServletRequest.getParameter("paginaServicos");
					if(Util.verificarNaoVazio(paginaServicos) && paginaServicos.equalsIgnoreCase("sim")){
						form.limparForm();
					}
					Imovel imovel = fachada.pesquisarImovel(matricula);
					sessao.setAttribute("imovel", imovel);
					form.setMatriculaImovel(matricula.toString());
					form.setInscricaoImovel(imovel.getInscricaoFormatada());
					

					// Pega as Quantidades de Reparcelamentos
					boolean imovelComParcelamentosAtivos = fachada.verificarExistenciaParcelamentosAtivosImovel(imovel.getId());
					
					if (imovelComParcelamentosAtivos) {
						if (imovel.getNumeroReparcelamento() != null) {
							form.setNumeroReparcelamento("" + imovel.getNumeroReparcelamento());
						} else {
							form.setNumeroReparcelamento("0");
						}
					} else {
						form.setNumeroReparcelamento("0");
					}
					
					//[UC0067] Obter Debito do Imovel ou Cliente - [FS0014] Verificar existencia de debitos para o imovel
					if(this.obterDebitosImovel(matricula)){
						//Validacoes relativas ao cpf/cliente.
						ParcelamentoPerfil parcelamentoPerfil = this.obterPerfilParcelamentoImovel(imovel, form);
						//Verifica se o imovel possui perfil de parcelamento
						if(parcelamentoPerfil != null){
							
							boolean perfilValido = validarPerfilParcelamento(form,
								parcelamentoPerfil,fachada,httpServletRequest);
							
							if(!perfilValido){
								retorno = actionMapping.findForward("telaServico");
							}else{
								//As tres situacoes onde pode ter reativacao da situacao de Agua.
								if(imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPRIMIDO ||
										imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC ||
										imovel.getLigacaoAguaSituacao().getId().intValue() == LigacaoAguaSituacao.SUPR_PARC_PEDIDO ){
									reativacaoDaligacaoAgua = true;
									httpServletRequest.setAttribute("reativacaoLigacaoAgua", reativacaoDaligacaoAgua);
									
									/*
									 * Caso tenha sido a primeira vez que a pa¡gina foi carrega, isto ao que
									 * o indicador do restabelecimento for nulo, por padrÃ£o colocar o indicador
									 * como 2.
									 */
									if(form.getIndicadorRestabelecimento() == null){
										form.setIndicadorRestabelecimento("2");
									}
								/*
								 * Caso nao esteja dentro das tres situacoes acima, o indicador de restabelecimento sera
								 * por padrao 2.
								 */
								}else{
									form.setIndicadorRestabelecimento("2");
								}
								
								if(method != null && method.equalsIgnoreCase("pesquisarCliente")){
									String nomeCliente = this.verificaExistenciaCpf(form.getCpfCliente(), matricula, httpServletRequest);
									
									if(form.getIndicadorFormaCobranca() == null){
										httpServletRequest.setAttribute("indicadorFormaNaoSelecionado", true);
										return retorno;
									}
									
									if(Util.verificarNaoVazio(nomeCliente)){
										form.setNomeCliente(nomeCliente);
										this.calcularDebitos(this.colecaoDebitoImovel, imovel, form, sessao, httpServletRequest, false, parcelamentoPerfil, imovelComParcelamentosAtivos);
										httpServletRequest.setAttribute("exibirDetalhesDebito", true);
									}
								}else{
									String calcularParcelas = httpServletRequest.getParameter("calcularParcelas");
									if(Util.verificarNaoVazio(calcularParcelas) && calcularParcelas.equalsIgnoreCase("sim")){
										if(form.getValorEntradaInformado().equals("")){
											httpServletRequest.setAttribute("valorEntradaNaoDigitado", true);
										}else{
											this.calcularDebitos(this.colecaoDebitoImovel, imovel, form, sessao, httpServletRequest, true, parcelamentoPerfil, imovelComParcelamentosAtivos);
										}
										httpServletRequest.setAttribute("exibirDetalhesDebito", true);
									}
									String endereco = fachada.pesquisarEndereco(matricula);
									form.setEnderecoImovel(endereco);
								}
							}
							
							
						}else{
							retorno = actionMapping.findForward("imovelNaoPossuiPerfilParcelamento");
							httpServletRequest.setAttribute("imovelNaoPossuiPerfilParcelamento", true);
						}
					}else{
						retorno = actionMapping.findForward("imovelSemDebitos");
						httpServletRequest.setAttribute("imovelSemDebitos", true);
					}
				}else{
					retorno = actionMapping.findForward("debitoParceladoMesCorrente");
					httpServletRequest.setAttribute("debitoParceladoMesCorrente", true);
				}
			}else{
				retorno = actionMapping.findForward("matriculaPossuiParcelamentoNaoQuitado");
				httpServletRequest.setAttribute("imovelParcelamentoAtivo", true);
			}
		}else{
			httpServletRequest.setAttribute("exibirDetalhesDebito", true);
			httpServletRequest.setAttribute("reativacaoLigacaoAgua", reativacaoDaligacaoAgua);
		}
		return retorno;
	}

	private String verificaExistenciaCpf(String cpf, Integer matricula, HttpServletRequest request){
		Fachada fachada = Fachada.getInstancia();
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, cpf));
		Collection<Cliente> clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		String nomeCliente = null;

		//[FS0001] - Verificar existencia do CPF do cliente
		if(!Util.isVazioOrNulo(clientes)){
			nomeCliente = fachada.validarCliente(cpf, matricula);
			//[FS0002]  Validar cliente
			if(!Util.verificarNaoVazio(nomeCliente)){
				request.setAttribute("clienteInvalido", true);
			}
		}else{
			request.setAttribute("cpfInvalido", true);
		}
		return nomeCliente;
	}

	private boolean obterDebitosImovel(Integer matricula){
		boolean possuiDebitos = false;
		this.colecaoDebitoImovel = Fachada.getInstancia().obterDebitoImovelOuCliente(
				1,// Indicador debito imovel 
				matricula.toString(), // Matricula do imovel
				null, // Codigo do cliente
				null, // Tipo de relacao do cliento com o imovel
				"000101", // Referencia inicial do debito
				"999912", // Referencia final do debito
				Util.converteStringParaDate("01/01/0001"), // Inicio Vencimento
				Util.converteStringParaDate("31/12/9999"), // Final Vencimento
				1, // Indicador pagamento
				2, // Indicador conta em revisÃ£o
				1, // Indicador debito a cobrar
				1, // Indicador credito a realizar
				1, // Indicador notas promissorias
				1, // Indicador guias de pagamento
				1, // Indicador acrescimos por impontualidade
				null,
				2); // Indicador Contas
		if (this.existeDebitos(colecaoDebitoImovel)) {
			possuiDebitos = true;
		}
		return possuiDebitos;
	}

	private boolean existeDebitos(ObterDebitoImovelOuClienteHelper debitos){
		boolean existe = true;
		if(Util.isVazioOrNulo(debitos.getColecaoContasValoresImovel()) 
				&& Util.isVazioOrNulo(debitos.getColecaoGuiasPagamentoValores())
				&& Util.isVazioOrNulo(debitos.getColecaoDebitoACobrar())){
			existe = false;
		}
		return existe;
	}

	private void calcularDebitos(ObterDebitoImovelOuClienteHelper debitos, Imovel imovel, EfetuarParcelamentoDebitosPortalSaaeActionForm form,
			HttpSession sessao, HttpServletRequest request, boolean recalcularOpcaoParcelamento, ParcelamentoPerfil parcelamentoPerfil, boolean imovelComParcelamentosAtivos){
		Fachada fachada = Fachada.getInstancia();

		/*
		 * Verifica se o usua¡rio digitou um novo valor de entrada para o calculo do parcelamento. Caso
		 * tenha digitado, nao sera necessario calcular os debitos novamente, apenas o novo valor do
		 * parcelamento.
		 */
		if(!recalcularOpcaoParcelamento){

			// Para o calculo do Debito Total Atualizado
			BigDecimal valorTotalContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidade = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalGuiasPagamento = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
			BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
			BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
			BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
			BigDecimal valorJurosMora = new BigDecimal("0.00");
			BigDecimal valorMulta = new BigDecimal("0.00");

			// Dados do Debito do Imovel - Contas
			Collection<ContaValoresHelper> colecaoContaValores = debitos.getColecaoContasValores();
			//[SB0011] Verificar Unica Fatura
			fachada.verificarUnicaFatura(colecaoContaValores,parcelamentoPerfil);

			if (!Util.isVazioOrNulo(colecaoContaValores)) {

				int quantidadeMinimaMesesAntiguidade = 0;
				int maiorQuantidadeMinimaMesesAntiguidade = 0;
				Iterator<ContaValoresHelper> contaValores = colecaoContaValores.iterator();

				while (contaValores.hasNext()) {

					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();

					//Colocado por Raphael Rossiter em 04/12/2008
					//=============================================================================================
					Collection<ParcelamentoDescontoAntiguidade> colecaoParcelamentoDescontoAntiguidade = 
						fachada.obterParcelamentoDescontoAntiguidadeParaConta(parcelamentoPerfil, contaValoresHelper.getConta());

					ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeMaior = new ParcelamentoDescontoAntiguidade();

					// Caso nenhuma ocorrencia tenha sido selecionada passar para a proxima conta
					if (!Util.isVazioOrNulo(colecaoParcelamentoDescontoAntiguidade)) {

						Iterator<ParcelamentoDescontoAntiguidade> parcelamentoDescontoAntiguidadeValores = colecaoParcelamentoDescontoAntiguidade.iterator();
						quantidadeMinimaMesesAntiguidade = 0;
						maiorQuantidadeMinimaMesesAntiguidade = 0;

						//Determina o percentual de desconto por antiguidade do debito
						while (parcelamentoDescontoAntiguidadeValores.hasNext()) {
							ParcelamentoDescontoAntiguidade descoAnt = (ParcelamentoDescontoAntiguidade) parcelamentoDescontoAntiguidadeValores.next();
							quantidadeMinimaMesesAntiguidade = descoAnt.getQuantidadeMinimaMesesDebito();
							if (quantidadeMinimaMesesAntiguidade > maiorQuantidadeMinimaMesesAntiguidade) {
								maiorQuantidadeMinimaMesesAntiguidade = quantidadeMinimaMesesAntiguidade;
								parcelamentoDescontoAntiguidadeMaior = descoAnt;
							}
						}

						/*
						 * Colocado por Raphael Rossiter em 03/12/2008
						 * As contas onde o perfil de parcelamento para desconto de antiguidade estiver com
						 * o motivo de revisao informado NAO entrarao no parcelamento.
						 */
						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para calculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());

						if (parcelamentoDescontoAntiguidadeMaior.getContaMotivoRevisao() != null){
							//CONTA ENTRARAO EM REVISAO
							contaValoresHelper.setRevisao(1);
						}
					}else{

						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para calculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
					}
					//=============================================================================================
				}

				form.setValorTotalContaValores(Util.formatarMoedaReal(valorTotalContas));
				sessao.setAttribute("valorTotalContaValores", valorTotalContas);
				sessao.setAttribute("colecaoContaValores", colecaoContaValores);
			}

			//----------------------------- Guia Pagamento -----------------------------//

			Collection<GuiaPagamentoValoresHelper> guiasPagamentoValores = debitos.getColecaoGuiasPagamentoValores();
			if (!Util.isVazioOrNulo(guiasPagamentoValores)){
				Iterator<GuiaPagamentoValoresHelper> guiaPagamentoValores = guiasPagamentoValores.iterator();
				while (guiaPagamentoValores.hasNext()) {
					GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores.next();
					valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalGuiasPagamento = valorTotalGuiasPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());

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

					// Para calculo do Acrescimo de Impontualidade
					valorTotalAcrescimoImpontualidadeGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias.add(guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade());
				}
				form.setValorGuiasPagamento(Util.formatarMoedaReal(valorTotalGuiasPagamento));

				// Seta as Guias de Pagamento em Debito
				sessao.setAttribute("guiasPagamentoValores", guiasPagamentoValores);
			} else {
				form.setValorGuiasPagamento("0,00");
			}

			//----------------------------- Acrescimos por Impontualidade -----------------------------//
			BigDecimal retornoSoma = new BigDecimal("0.00");
			retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
			retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);

			form.setValorAcrescimosImpontualidade(Util.formatarMoedaReal(retornoSoma));
			sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);

			form.setValorAtualizacaoMonetaria(Util.formatarMoedaReal(valorAtualizacaoMonetaria));
			form.setValorJurosMora(Util.formatarMoedaReal(valorJurosMora));
			form.setValorMulta(Util.formatarMoedaReal(valorMulta));

			valorTotalAcrescimoImpontualidade = retornoSoma;


			//----------------------------- Debitos a cobrar -----------------------------//
			//[FS0022]-Verificar existencia de juros sobre imovel
			Collection<DebitoACobrar> colecaoDebitoACobrar = debitos.getColecaoDebitoACobrar();

			if (!Util.isVazioOrNulo(colecaoDebitoACobrar)) {
				Iterator<DebitoACobrar> debitoACobrarValores = colecaoDebitoACobrar.iterator();

				final int indiceCurtoPrazo = 0;
				final int indiceLongoPrazo = 1;

				while (debitoACobrarValores.hasNext()) {
					DebitoACobrar debitoACobrar = debitoACobrarValores.next();

					//[FS0022]-Verificar existencia de juros sobre imovel
					if(debitoACobrar.getDebitoTipo().getId() != null && !debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){

						// Debitos A Cobrar - Servico
						if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
							// [SB0001] Obter Valores de Curto e Longo Prazo
							valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();

							BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
									debitoACobrar.getNumeroPrestacaoDebito(), debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),
									valorRestanteACobrar);
							valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
							form.setValorDebitoACobrarServicoCurtoPrazo(Util.formatarMoedaReal(valorTotalRestanteServicosACobrarCurtoPrazo));
							
							valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							form.setValorDebitoACobrarServicoLongoPrazo(Util.formatarMoedaReal(valorTotalRestanteServicosACobrarLongoPrazo));
						}

						// Debitos A Cobrar - Parcelamento
						if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
							// [SB0001] Obter Valores de Curto e Longo Prazo
							valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();

							BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
									debitoACobrar.getNumeroPrestacaoDebito(),
									debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(), valorRestanteACobrar);
							valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
							valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(
									valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
						}
					}
				}
				sessao.setAttribute("colecaoDebitoACobrar", colecaoDebitoACobrar);

				// Servicos
				valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo.add(valorTotalRestanteServicosACobrarLongoPrazo);
				form.setValorDebitoACobrarServico(Util.formatarMoedaReal(valorTotalRestanteServicosACobrar));
				// Parcelamentos
				valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo
					.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
				form.setValorDebitoACobrarParcelamento(Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
			}else{
				form.setValorDebitoACobrarServico("0,00");
				form.setValorDebitoACobrarParcelamento("0,00");
				form.setValorDebitoACobrarServicoLongoPrazo("0,00");
				form.setValorDebitoACobrarServicoCurtoPrazo("0,00");
				form.setValorDebitoACobrarParcelamentoLongoPrazo("0,00");
				form.setValorDebitoACobrarParcelamentoCurtoPrazo("0,00");
			}

			//----------------------------- Creditos a realizar -----------------------------//
			Collection<CreditoARealizar> colecaoCreditoARealizar = debitos.getColecaoCreditoARealizar();
			if (!Util.isVazioOrNulo(colecaoCreditoARealizar)) {
				Iterator<CreditoARealizar> creditoARealizarValores = colecaoCreditoARealizar.iterator();
				while (creditoARealizarValores.hasNext()) {
					CreditoARealizar creditoARealizar = creditoARealizarValores.next();
					valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
				}
				sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
				form.setValorCreditoARealizar(Util.formatarMoedaReal(valorCreditoARealizar));
			}else{
				form.setValorCreditoARealizar("0,00");
			}

			//----------------------------- Debito total atualizado -----------------------------//
			BigDecimal debitoTotalAtualizado = new BigDecimal("0.00");

			debitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);

			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalContas);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalGuiasPagamento);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalAcrescimoImpontualidade);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteServicosACobrar);
			debitoTotalAtualizado = debitoTotalAtualizado.add(valorTotalRestanteParcelamentosACobrar);
			debitoTotalAtualizado = debitoTotalAtualizado.subtract(valorCreditoARealizar);

			form.setValorDebitoTotalAtualizado(Util.formatarMoedaReal(debitoTotalAtualizado));
			sessao.setAttribute("valorDebitoTotalAtualizado", debitoTotalAtualizado);
		}
		/*
		 * Caso o usua¡rio tenha digitado um novo valor ou seja a primeira vez que esteja calculando os debitos do imovel,
		 * sera necessa¡rio obter as opcoes de parcelamento. A colecao de conta valores sera setada na sessao dentro do
		 * calcular debitos. Logo acima.
		 */
		this.obterOpcaoParcelamento((Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores"), 
				debitos.getColecaoGuiasPagamentoValores(), imovel, form, imovelComParcelamentosAtivos, sessao, request);
		
		sessao.setAttribute("formParcelamento", form);
	}

	//Metodo auxiliar que vai retornar o perfil do parcelamento na conta
	private ParcelamentoPerfil obterPerfilParcelamentoImovel(Imovel imovel, 
			EfetuarParcelamentoDebitosPortalSaaeActionForm form){
		Fachada fachada =  Fachada.getInstancia();

		ParcelamentoPerfil parcelamentoPerfil;
	
		//[FS004] Verificar existencia da situacao do imovel.
		ImovelSituacao imovelSituacao = null;
		Integer situacaoAguaId = imovel.getLigacaoAguaSituacao().getId();
		Integer situacaoEsgotoId = imovel.getLigacaoEsgotoSituacao().getId();
		// Condicao 1
		if (situacaoAguaId != null && situacaoEsgotoId != null) {
			imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, situacaoEsgotoId);
			// Condicao 2
			if (imovelSituacao == null) {
				imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, null);
			}
		}

		//Obtem o perfil do parcelamento para o imovel
		parcelamentoPerfil = null;
		
		FiltroResolucaoDiretoria filtro = new FiltroResolucaoDiretoria();

		if (CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString().equals(form.getIndicadorFormaCobranca())) {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_CARTAO_CREDITO, ConstantesSistema.SIM));
		} else {
			filtro.adicionarParametro(new ParametroSimples(FiltroResolucaoDiretoria.INDICADOR_PARCELAMENTO_LOJA_VIRTUAL, ConstantesSistema.SIM));
		}

		Collection<ResolucaoDiretoria> colecaoResolucaoDiretoria = fachada.pesquisar(filtro, ResolucaoDiretoria.class.getName());
		if(!Util.isVazioOrNulo(colecaoResolucaoDiretoria)){
			ResolucaoDiretoria resolucaoDiretoria = (ResolucaoDiretoria)Util.retonarObjetoDeColecao(colecaoResolucaoDiretoria);
			Integer idResolucao = resolucaoDiretoria.getId();
			form.setResolucaoDiretoria(idResolucao.toString());
			if (imovelSituacao != null) {
				// Pega a subcategoria do imovel
				Collection<ImovelSubcategoria> colecaoImovelSubCategoria = fachada.obterColecaoImovelSubcategorias(imovel, 1);
				Subcategoria subcategoria = null;
				Categoria categoria = null;

				if (colecaoImovelSubCategoria != null && !colecaoImovelSubCategoria.isEmpty()) {
					Iterator<ImovelSubcategoria> iteretorImovelSubCategoria = colecaoImovelSubCategoria.iterator();
					int quantidadeEconomisas = 0;
					int maiorQuantidadeEconomisas = 0;

					while (iteretorImovelSubCategoria.hasNext()) {
						ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) iteretorImovelSubCategoria	.next();
						quantidadeEconomisas = imovelSubCategoria.getQuantidadeEconomias();
						if (quantidadeEconomisas > maiorQuantidadeEconomisas) {
							maiorQuantidadeEconomisas = quantidadeEconomisas;
							subcategoria = imovelSubCategoria.getComp_id().getSubcategoria();
							categoria = subcategoria.getCategoria();

						}
					}
				}
				parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(), imovelSituacao.getImovelSituacaoTipo().getId(),
						imovel.getImovelPerfil().getId(), subcategoria.getId(), idResolucao, categoria.getId());

				if(parcelamentoPerfil == null){
					//Condicao 1 - iper_id = iper_id do imovel e scat_id = scat_id do imovel
					parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
							imovel.getImovelPerfil().getId(), subcategoria.getId(), idResolucao, null);

					if (parcelamentoPerfil == null) {
						parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
								imovel.getImovelPerfil().getId(), null, idResolucao, categoria.getId());

						//Condicao 2 - iper_id = iper_id do imovel e scat_id = null do imovel
						if (parcelamentoPerfil == null) {
							parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
									imovel.getImovelPerfil().getId(), null, idResolucao, null);

							if (parcelamentoPerfil == null) {
								parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
										null, subcategoria.getId(), idResolucao,categoria.getId());

								//Condicao 3 - iper_id = null do imovel e scat_id = scat_id do imovel
								if (parcelamentoPerfil == null) {
									parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(),
											null, subcategoria.getId(), idResolucao,null);

									if(parcelamentoPerfil == null){
										parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(), 
												null, null, idResolucao, categoria.getId());

										//Condicao 4 - iper_id = null do imovel e scat_id = null
										if (parcelamentoPerfil == null) {
											parcelamentoPerfil = fachada.obterPerfilParcelamento(imovel.getId(),imovelSituacao.getImovelSituacaoTipo().getId(), 
													null, null, idResolucao, null);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return parcelamentoPerfil;
	}

	private void obterOpcaoParcelamento(Collection<ContaValoresHelper> colecaoContaValores, Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamento,
			Imovel imovel, EfetuarParcelamentoDebitosPortalSaaeActionForm form, boolean imovelComParcelamentosAtivos, HttpSession sessao, HttpServletRequest request){

		//Configurando o helper dos indicadores de parcelamento
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer(2));
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(1));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(3));

		///Configurando os parametros para obter opcoes de parcelamento
		ResolucaoDiretoria resolucaoDiretoria = Fachada.getInstancia().pesquisarResolucaoDiretoriaPortal();
//		Integer resolucaoDiretoria = 14;
		Integer codigoImovel = imovel.getId();
		BigDecimal valorEntradaInformado = null;
		/*
		 * Caso seja uma mudanca no indicador de restabelecimento o valor entrada informado vai ter
		 * null, pois o sitema vai calcular o valor minimo e atribuir ao valor informado
		 */
		String mudancaIndicadorRestabelecimento = request.getParameter("mudancaIndicadorRestabelecimento");
		if(mudancaIndicadorRestabelecimento != null){
			form.setValorEntradaInformado(null);
		}else{
			if(form.getValorEntradaInformado() != null){
				valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(form.getValorEntradaInformado());
			}
		}
		Integer situacaoAguaId = imovel.getLigacaoAguaSituacao().getId();
		Integer situacaoEsgotoId = imovel.getLigacaoEsgotoSituacao().getId();
		Integer perfilImovel = imovel.getImovelPerfil().getId();
		Integer indicadorRestabelecimento = null;
		indicadorRestabelecimento = form.getIndicadorRestabelecimento().equals("1") ? 1 : 2;
		BigDecimal valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoTotalAtualizado());
		BigDecimal valorTotalMultas = Util.formatarMoedaRealparaBigDecimal(form.getValorMulta());
		BigDecimal valorTotalJurosMora = Util.formatarMoedaRealparaBigDecimal(form.getValorJurosMora());
		BigDecimal valorTotalAtualizacoesMonetarias = Util.formatarMoedaRealparaBigDecimal(form.getValorAtualizacaoMonetaria());
		Integer numeroReparcelamentoConsecutivos = 0;
		
		if (imovelComParcelamentosAtivos) {
			numeroReparcelamentoConsecutivos = (imovel.getNumeroReparcelamentoConsecutivos() == null) ? 0 :imovel.getNumeroReparcelamentoConsecutivos().intValue();
		}
		
		//Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(
			new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, ConstantesSistema.SIM));
		
		Usuario usuario = 
			(Usuario) Util.retonarObjetoDeColecao(
				this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName()));
		//Fim Usuario
		
		
		
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamento());
		String inicioIntervaloParcelamento = "01/0001";
		Integer inicioIntervaloParcelamentoFormatado = 101;
		Integer fimIntervaloParcelamentoFormatado = 999912;
		BigDecimal valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditoARealizar());

		//Configurando os indicadores para o concluir parcelamento (valores explicitados no caso de uso)
		form.setIndicadorAcrescimosImpotualidade("1");
		form.setIndicadorContasRevisao("2");
		form.setIndicadorCreditoARealizar("1");
		form.setIndicadorDebitosACobrar("1");
		form.setIndicadorDividaAtiva("3");
		form.setIndicadorGuiasPagamento("1");
				
		/*
		 * [SB0002] - Obter Opcoes de Parcelamento de acordo com a entrada informada
		 * CARREGANDO O HELPER COM AS INFORMACAOES DO PARCELAMENTO
		 */
		ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
				resolucaoDiretoria.getId(), new Integer(codigoImovel), valorEntradaInformado, situacaoAguaId, 
				situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
				colecaoContaValores, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
				valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
				valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
				fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizar,false);

		NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = Fachada.getInstancia().obterOpcoesDeParcelamento(helper);


		BigDecimal descontoAcrescimosImpontualidade = opcoesParcelamento.getValorDescontoAcrecismosImpotualidade();
		BigDecimal descontoAntiguidadeDebito = opcoesParcelamento.getValorDescontoAntiguidade();
		BigDecimal descontoInatividadeLigacaoAgua = opcoesParcelamento.getValorDescontoInatividade();
		BigDecimal percentualDescontoAcrescimosImpontualidade = opcoesParcelamento.getPercentualDescontoAcrescimosImpontualidade();
		BigDecimal percentualDescontoAntiguidadeDebito = opcoesParcelamento.getPercentualDescontoAntiguidadeDebito();
		BigDecimal percentualDescontoInatividadeLigacaoAgua = opcoesParcelamento.getPercentualDescontoInatividadeLigacaoAgua();
		BigDecimal valorPagamentoAVista = new BigDecimal("0.00");
		ParcelamentoPerfil parcelamentoPerfil = opcoesParcelamento.getParcelamentoPerfil();
		BigDecimal valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
		BigDecimal valorTotalImpostosConta = new BigDecimal("0.00");
		BigDecimal descontoSancoesRDEspecial = opcoesParcelamento.getValorDescontoSancoesRDEspecial();
		BigDecimal descontoTarifaSocialRDEspecial = opcoesParcelamento.getValorDescontoTarifaSocialRDEspecial();
		BigDecimal descontoTotalPagamentoAVista = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
		BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
		BigDecimal valorDebitoDesconto = opcoesParcelamento.getValorDebitoDesconto();
		valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
		BigDecimal descontoSobreDebitoTotal = opcoesParcelamento.getValorDescontoSobreDebitoTotal();
		
		Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
		
		if (valorEntradaInformado != null && valorEntradaInformado.compareTo(valorEntradaMinima.setScale(
				Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)) == -1) {
			request.setAttribute("entradaInformadaMenorMinima", true);
			request.setAttribute("entradaMinima", valorEntradaMinima);
		}else{
			if(valorEntradaInformado == null || valorEntradaInformado.intValue() == 0){
				form.setValorEntradaInformado(Util.formatarBigDecimalParaStringComVirgula(valorEntradaMinima));
			}
			
			sessao.setAttribute("opcoesParcelamento",opcoesParcelamento);
			sessao.setAttribute("colecaoOpcoesParcelamento",colecaoOpcoesParcelamento);

			// Limpa os EP da Colecao de Contas
			if(colecaoContaValores != null && !colecaoContaValores.isEmpty()){
				Iterator<ContaValoresHelper> contaValores = colecaoContaValores.iterator();
				while(contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
					if (contaValoresHelper.getIndicadorContasDebito() != null && !contaValoresHelper.getIndicadorContasDebito().equals(new Integer("2"))){
						contaValoresHelper.setIndicadorContasDebito(null);
					}
				}
			}

			// Limpando a opcao de parcelamento
			if(!Util.isVazioOrNulo(colecaoOpcoesParcelamento)){
				Iterator<OpcoesParcelamentoHelper> opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(null);
					form.setIndicadorQuantidadeParcelas(null);
				}
			}else{
				request.setAttribute("valorEntradaInvalido", true);
			}

			// Verifica se o valor do debito menos o valor dos descontos ao menor que o valor minimo da parcela
			BigDecimal valorTotalDescontos = new BigDecimal("0.00");
			BigDecimal resultadoDiferenca = new BigDecimal("0.00");
			valorTotalDescontos.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			valorTotalDescontos = descontoAcrescimosImpontualidade.add(descontoAntiguidadeDebito);
			valorTotalDescontos = valorTotalDescontos.add(descontoInatividadeLigacaoAgua);
			valorTotalDescontos = valorTotalDescontos.add(descontoSancoesRDEspecial);
			valorTotalDescontos = valorTotalDescontos.add(descontoTarifaSocialRDEspecial);
			valorTotalDescontos = valorTotalDescontos.add(descontoSobreDebitoTotal);

			resultadoDiferenca.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			resultadoDiferenca = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);


			if (!Fachada.getInstancia().verificarQtdeReparcelamentoPerfil(parcelamentoPerfil.getId(),new Short(numeroReparcelamentoConsecutivos.shortValue()))){
				request.setAttribute("numeroReparcelamentos", true);
			}else{

				// Coloca os valores no formulario
				form.setDescontoAcrescimosImpontualidade(Util.formatarMoedaReal(descontoAcrescimosImpontualidade));
				form.setDescontoAntiguidadeDebito(Util.formatarMoedaReal(descontoAntiguidadeDebito));
				form.setDescontoInatividadeLigacaoAgua(Util.formatarMoedaReal(descontoInatividadeLigacaoAgua));
				form.setPercentualDescontoAcrescimosImpontualidade(Util.formatarMoedaReal(percentualDescontoAcrescimosImpontualidade));
				form.setValorTotalDescontos(Util.formatarMoedaReal(valorTotalDescontos));
				form.setDescontoSancoesRDEspecial(Util.formatarMoedaReal(descontoSancoesRDEspecial));
				form.setDescontoTarifaSocialRDEspecial(Util.formatarMoedaReal(descontoTarifaSocialRDEspecial));
				form.setDescontoSobreDebitoTotal(Util.formatarMoedaReal(descontoSobreDebitoTotal));
				
				valorTotalImpostosConta = obterValorImpostosDasContasDoParcelamento(colecaoContaValores);

				valorPagamentoAVista.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(descontoTotalPagamentoAVista);

				valorPagamentoAVista = valorPagamentoAVista.subtract(valorTotalImpostosConta);

				// Colocando os valores no formulario
				form.setValorDebitoTotalAtualizado(Util.formatarMoedaReal(valorDebitoTotalAtualizado));
				form.setValorDescontoPagamentoAVista(Util.formatarMoedaReal(descontoTotalPagamentoAVista));
				form.setValorPagamentoAVista(Util.formatarMoedaReal(valorPagamentoAVista));
				form.setValorTotalImpostos(Util.formatarMoedaReal(valorTotalImpostosConta));
				form.setValorDesconto(Util.formatarMoedaReal(valorDebitoDesconto));
				
				if (percentualDescontoAntiguidadeDebito != null) {
					form.setPercentualDescontoAntiguidadeDebito(Util.formatarMoedaReal(percentualDescontoAntiguidadeDebito));
				} else {
					form.setPercentualDescontoAntiguidadeDebito("0.00");
				}

				if (percentualDescontoInatividadeLigacaoAgua != null) {
					form.setPercentualDescontoInatividadeLigacaoAgua(Util.formatarMoedaReal(percentualDescontoInatividadeLigacaoAgua));
				} else {
					form.setPercentualDescontoInatividadeLigacaoAgua("0.00");
				}

				if (parcelamentoPerfil != null) {
					form.setParcelamentoPerfilId(parcelamentoPerfil.getId().toString());
					sessao.setAttribute("parcelamentoPerfil", parcelamentoPerfil);
				}

				// O valor do debito ao menor que o valor da parcela mi­nima permitida.
				// Utilizar a opcao Pagamento a  Vista. 
				if (valorDebitoTotalAtualizado.compareTo(valorMinimoPrestacao) == -1){
					request.setAttribute("vlDebitoMenor","1");
				}
				form.setValorMinimoPrestacao(Util.formatarMoedaReal(valorMinimoPrestacao));

				String habilitaPagamentoAVista = "1";
				if (colecaoContaValores == null || colecaoContaValores.isEmpty()) {
					habilitaPagamentoAVista = "2";
				}
				sessao.setAttribute("habilitaPagamentoAVista", habilitaPagamentoAVista);

				sessao.setAttribute("colecaoContaValoresNegociacao",colecaoContaValores);
				sessao.setAttribute("colecaoGuiaPagamentoNegociacao", colecaoGuiaPagamento);
				sessao.setAttribute("valorAcrescimosImpontualidadeNegociacao", 
						Util.formatarMoedaRealparaBigDecimal(form.getValorAcrescimosImpontualidade()));
			}
		}
	}

	private BigDecimal obterValorImpostosDasContasDoParcelamento(Collection<ContaValoresHelper> colecaoContas){
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator<ContaValoresHelper> contas = colecaoContas.iterator();
			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();

				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
	
	//adicionado por Vivianne Sousa - 20/10/2011
	private boolean validarPerfilParcelamento(EfetuarParcelamentoDebitosPortalSaaeActionForm form,
			ParcelamentoPerfil parcelamentoPerfil,Fachada fachada,HttpServletRequest request){
		
		boolean retorno = true;
		Integer idImovel = new Integer(form.getMatriculaImovel());
		
		//[FS0025] Verificar quantidade máxima de reparcelamento
		if (parcelamentoPerfil.getQuantidadeMaximaReparcelamento() != null && 
			parcelamentoPerfil.getQuantidadeMaximaReparcelamento().compareTo(new Integer(form.getNumeroReparcelamento())) == -1){
			
			request.setAttribute("quantidadeReparcelNaoPermiteParcel", true);
			retorno = false;
		}
	
//		if(form.getAreaConstruidaImovel() != null &&
//			!form.getAreaConstruidaImovel().equals("")){
//			BigDecimal areaConstruidaImovel = new BigDecimal (form.getAreaConstruidaImovel());
//			
//			if(parcelamentoPerfil.getNumeroAreaConstruida() != null && 
//				areaConstruidaImovel.compareTo(parcelamentoPerfil.getNumeroAreaConstruida()) > 0 ){
//				// area contruida do imovel > a area contruida do perfil do parcelamento
//				throw new ActionServletException("atencao.nao.existe.perfil.parcelamento.correspondente.situacao.imovel");
//			}
//		}
        Imovel imovel = new Imovel();
        imovel.setId(idImovel);
		if(parcelamentoPerfil.getCategoria() != null){
            Collection colecaoCategoria = fachada.pesquisarCategoriasImovel(imovel);
            boolean existePerfilParaCategoria = false;
            Iterator iter = colecaoCategoria.iterator();
            while (iter.hasNext()) {
                Categoria categoria = (Categoria) iter.next();
                
                if(categoria.getId().equals(parcelamentoPerfil.getCategoria().getId())){ 
                	 existePerfilParaCategoria = true;
                }
            }
            if(!existePerfilParaCategoria){
            	//categoria principal do imovel != categoria do perfl do parcelamento
    			request.setAttribute("naoExistePerfilSituacaoImovel", true);
                retorno = false;
            }
		}
		
		int qtdeEconomiasImovel = fachada.obterQuantidadeEconomias(imovel);
		
		if(parcelamentoPerfil.getNumeroConsumoEconomia() != null){
			
			Integer idLigacaoTipo = LigacaoTipo.LIGACAO_AGUA;
			
			ImovelSituacao imovelSituacao = null;
			Integer situacaoAguaId = imovel.getLigacaoAguaSituacao().getId();
			Integer situacaoEsgotoId = imovel.getLigacaoEsgotoSituacao().getId();
			// Condicao 1
			if (situacaoAguaId != null && situacaoEsgotoId != null) {
				imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, situacaoEsgotoId);
				// Condicao 2
				if (imovelSituacao == null) {
					imovelSituacao = fachada.obterSituacaoImovel(situacaoAguaId, null);
				}
			}
			
			if(imovelSituacao.getImovelSituacaoTipo().getId().equals(ImovelSituacaoTipo.LIGADO_SO_ESGOTO)){
				idLigacaoTipo = LigacaoTipo.LIGACAO_ESGOTO;
			}
			
			Integer consumoMedio = fachada.obterConsumoMedioEmConsumoHistorico(idImovel,idLigacaoTipo);
			
			Integer consumoMedioPorEconomia = 0;
			if(consumoMedio != null && consumoMedio.intValue() != 0){
				consumoMedioPorEconomia =  Util.dividirArredondarResultado(consumoMedio,qtdeEconomiasImovel);
			}
			
			if(consumoMedioPorEconomia.compareTo(parcelamentoPerfil.getNumeroConsumoEconomia()) > 0){
				request.setAttribute("naoExistePerfilSituacaoImovel", true);
				retorno = false;
			}
			
		}
		
		if(parcelamentoPerfil.getQuantidadeEconomias() != null){
			
			if(qtdeEconomiasImovel > parcelamentoPerfil.getQuantidadeEconomias().intValue()){
				request.setAttribute("naoExistePerfilSituacaoImovel", true);
				retorno = false;
			}
		}
			
		if(parcelamentoPerfil.getCapacidadeHidrometro() != null &&
				parcelamentoPerfil.getCapacidadeHidrometro().equals(ConstantesSistema.SIM)){
			
			HidrometroCapacidade hidrometroCapacidade = null;
			hidrometroCapacidade = fachada.obterHidrometroCapacidadeEmLigacaoAgua(idImovel);
			
			if (hidrometroCapacidade != null){
				if (!hidrometroCapacidade.getId().equals(1) &&
					!hidrometroCapacidade.getId().equals(2) &&
					!hidrometroCapacidade.getId().equals(8)){
					request.setAttribute("naoExistePerfilSituacaoImovel", true);
					retorno = false;
				}
			}
		}
		
		//[FS0021] - Verificar situação de cobrança
		if (parcelamentoPerfil.getIndicadorChequeDevolvido().equals(ConstantesSistema.SIM)){
			
			//CRC3323 - adicionado por Vivianne Sousa - analista:Fatima Sampaio - 05/05/2010 
			 Collection colecaoImovelCobrancaSituacao = fachada.pesquisarImovelCobrancaSituacaoPorImovel(idImovel);
             if(colecaoImovelCobrancaSituacao != null && !colecaoImovelCobrancaSituacao.isEmpty()){
            	 
            	 Iterator iterImovelCobrancaSituacao  = colecaoImovelCobrancaSituacao.iterator();
            	 String descricao = "";
            	 while (iterImovelCobrancaSituacao.hasNext()) {
					ImovelCobrancaSituacao imovelCobrancaSituacao = 
						(ImovelCobrancaSituacao) iterImovelCobrancaSituacao.next();
					
					if(imovelCobrancaSituacao.getCobrancaSituacao().getIndicadorBloqueioParcelamento().equals(ConstantesSistema.SIM)){
						
						descricao = descricao + imovelCobrancaSituacao.getCobrancaSituacao().getDescricao() + ", ";
					}
				}
            	 
            	if(!descricao.equalsIgnoreCase("")){
//            		descricao = Util.removerUltimosCaracteres(descricao, 2);
            		request.setAttribute("imovelSituacaoCobranca", true);
        			retorno = false;
            	}

             }

			
		}
		return retorno;		
	}
}