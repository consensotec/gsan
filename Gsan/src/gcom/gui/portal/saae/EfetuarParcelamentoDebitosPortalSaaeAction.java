package gcom.gui.portal.saae;

import gcom.arrecadacao.BandeiraCartao;
import gcom.arrecadacao.FiltroBandeiraCartao;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.ConcluirParcelamentoDebitosHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe respons�vel por efetuar o parcelamento dos d�bitos
 * de um im�vel atrav�s da loja virtual. Os m�todos chamados  
 * s�o os m�todos j� existentes do GSAN
 * 
 * @author Cesar Medeiros
 * @date 16/09/2015
 */
public class EfetuarParcelamentoDebitosPortalSaaeAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("recarregarPagina");
        HttpSession sessao = request.getSession(false);
		EfetuarParcelamentoDebitosPortalSaaeActionForm form = (EfetuarParcelamentoDebitosPortalSaaeActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		String parcelamentoEfetuado = (String)sessao.getAttribute("parcelamentoEfetuado");

		if (parcelamentoEfetuado != null) {
			request.setAttribute("parcelamentoJaEfetuado", true);
			return retorno;
		}

		// Usu�rio respons�vel por fazer o parcelamneto no portal ser� por padr�o usu�rio internet.
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, "INTERNET"));
		Usuario usuarioLogado = (Usuario) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUsuario, Usuario.class.getName()));

        String codigoImovel = form.getMatriculaImovel();
        Date dataParcelamento = new Date();
        String indicadorRestabelecimento = form.getIndicadorRestabelecimento();
        String indicadorContasRevisao = form.getIndicadorContasRevisao();
        String indicadorGuiasPagamento = form.getIndicadorGuiasPagamento();
        String indicadorAcrescimosImpotualidade = form.getIndicadorAcrescimosImpotualidade();
        String indicadorDebitosACobrar = form.getIndicadorDebitosACobrar();
        String indicadorCreditoARealizar = form.getIndicadorCreditoARealizar();
        String cpfClienteParcelamentoDigitado = form.getCpfCliente();
        String indicadorDividaAtiva = form.getIndicadorDividaAtiva();

        // Recupera da sess�o as op��es de parcelamento para verificar se foi selecionada alguma op��o de parcelamento.
        Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>) 
			sessao.getAttribute("colecaoOpcoesParcelamento");

        // Vari�veis que ir�o armazenar os valores de acordo com a op��o de parcelamento selecionada.
        Short numeroPrestacoes = new Short("0");
		BigDecimal valorPrestacao = new BigDecimal("0.00");
		BigDecimal valorEntradaMinima = new BigDecimal("0.00");
		BigDecimal taxaJuros = new BigDecimal("0.00");

		// Vari�veis para o valor a ser parcelado
		BigDecimal valorASerParcelado = new BigDecimal("0.00");

		if (Util.isVazioOrNulo(colecaoOpcoesParcelamento) || Util.verificarVazio(form.getIndicadorQuantidadeParcelas())) {
			request.setAttribute("opcaoParcelamentoNaoInformada", true);
			request.setAttribute("erroEfetuarParcelamento", "SIM");
			return retorno;
		}

		for(OpcoesParcelamentoHelper opcao : colecaoOpcoesParcelamento) {
			// Verifica se o usu�rio escolheu uma op��o de parcelamento, caso tenha escolhido configura
			// os valores do parcelamento e sai do loop.
			if((form.getIndicadorQuantidadeParcelas()).equals(opcao.getQuantidadePrestacao().toString()) ){
				//valorJurosParcelamento = opcoesParcelamentoHelper.getTaxaJuros(); 
				numeroPrestacoes = opcao.getQuantidadePrestacao();
				valorPrestacao = opcao.getValorPrestacao();
				valorEntradaMinima = opcao.getValorEntradaMinima();
				taxaJuros = opcao.getTaxaJuros();
				valorASerParcelado = opcao.getValorPrestacao().multiply(new BigDecimal(opcao.getQuantidadePrestacao()));
				break;
			}
		}

		// Recupera os dados do formul�rio
		if (form.getValorEntradaInformado().equals("")) {
			request.setAttribute("valorEntradaNaoDigitado", true);
			request.setAttribute("erroEfetuarParcelamento", "SIM");
			return retorno;
		}

		BigDecimal valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal(form.getValorEntradaInformado());

		if (valorEntradaInformado.doubleValue() != valorEntradaMinima.doubleValue()) {
			request.setAttribute("recalcularOpcaoParcelamento", true);
			request.setAttribute("erroEfetuarParcelamento", "SIM");
			return retorno;
		}

		// Verificar valor da entrada m�nima permitida
		if (valorEntradaInformado != null && valorEntradaInformado.intValue() != 0 &&
				valorEntradaInformado.compareTo(valorEntradaMinima
						.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)) == -1) {
			request.setAttribute("entradaInformadaMenorMinima", true);
			request.setAttribute("entradaMinima", valorEntradaMinima);
			request.setAttribute("erroEfetuarParcelamento", "SIM");
			return retorno;
		}

		BigDecimal valorDebitoTotalAtualizado = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorDebitoTotalAtualizado())) {
			valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoTotalAtualizado());
		}

		Integer parcelamentoPerfilId = new Integer(form.getParcelamentoPerfilId());
		ParcelamentoPerfil parcelamentoPerfil = new ParcelamentoPerfil();
		parcelamentoPerfil.setId(parcelamentoPerfilId);
		verificarValorEntradaMinimaPermitida(numeroPrestacoes, valorEntradaInformado, valorDebitoTotalAtualizado, parcelamentoPerfil);

		BigDecimal valorTotalContaValores = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorTotalContaValores())) {
			valorTotalContaValores = Util.formatarMoedaRealparaBigDecimal(form.getValorTotalContaValores());
		}

		BigDecimal valorGuiasPagamento = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorGuiasPagamento())) {
			valorGuiasPagamento = Util.formatarMoedaRealparaBigDecimal(form.getValorGuiasPagamento());
		}

		BigDecimal valorDebitoACobrarServico = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorDebitoACobrarServico())) {
			valorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServico());
		}

		BigDecimal valorDebitoACobrarParcelamento = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorDebitoACobrarParcelamento())) {
			valorDebitoACobrarParcelamento = Util
					.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamento());
		}

		BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorCreditoARealizar())) {
			valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal(form.getValorCreditoARealizar());
		}

		BigDecimal valorAtualizacaoMonetaria = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorAtualizacaoMonetaria())) {
			valorAtualizacaoMonetaria = Util.formatarMoedaRealparaBigDecimal(form.getValorAtualizacaoMonetaria());
		}

		BigDecimal valorJurosMora = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorJurosMora())) {
			valorJurosMora = Util.formatarMoedaRealparaBigDecimal(form.getValorJurosMora());
		}

		BigDecimal valorMulta = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorMulta())) {
			valorMulta = Util.formatarMoedaRealparaBigDecimal(form.getValorMulta());
		}

		BigDecimal descontoAcrescimosImpontualidade = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getDescontoAcrescimosImpontualidade())) {
			descontoAcrescimosImpontualidade = Util
					.formatarMoedaRealparaBigDecimal(form.getDescontoAcrescimosImpontualidade());
		}

		BigDecimal descontoSancoesRDEspecial = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getDescontoSancoesRDEspecial())) {
			descontoSancoesRDEspecial = Util.formatarMoedaRealparaBigDecimal(form.getDescontoSancoesRDEspecial());
		}

		BigDecimal descontoTarifaSocialRDEspecial = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getDescontoTarifaSocialRDEspecial())) {
			descontoTarifaSocialRDEspecial = Util
					.formatarMoedaRealparaBigDecimal(form.getDescontoTarifaSocialRDEspecial());
		}

		BigDecimal descontoAntiguidadeDebito = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getDescontoAntiguidadeDebito())) {
			descontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal(form.getDescontoAntiguidadeDebito());
		}

		BigDecimal descontoInatividadeLigacaoAgua = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getDescontoInatividadeLigacaoAgua())) {
			descontoInatividadeLigacaoAgua = Util
					.formatarMoedaRealparaBigDecimal(form.getDescontoInatividadeLigacaoAgua());
		}

		BigDecimal descontoSobreDebitoTotal = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getDescontoSobreDebitoTotal())) {
			descontoSobreDebitoTotal = Util.formatarMoedaRealparaBigDecimal(form.getDescontoSobreDebitoTotal());
		}

		BigDecimal percentualDescontoAcrescimosImpontualidade = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getPercentualDescontoAcrescimosImpontualidade())) {
			percentualDescontoAcrescimosImpontualidade = Util
					.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoAcrescimosImpontualidade());
		}

		BigDecimal percentualDescontoAntiguidadeDebito = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getPercentualDescontoAntiguidadeDebito())) {
			percentualDescontoAntiguidadeDebito = Util
					.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoAntiguidadeDebito());
		}

		BigDecimal percentualDescontoInatividadeLigacaoAgua = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getPercentualDescontoInatividadeLigacaoAgua())) {
			percentualDescontoInatividadeLigacaoAgua = Util
					.formatarMoedaRealparaBigDecimal(form.getPercentualDescontoInatividadeLigacaoAgua());
		}

		BigDecimal valorAcrescimosImpontualidade = BigDecimal.ZERO;
		if (Util.verificarNaoVazio(form.getValorAcrescimosImpontualidade())) {
			valorAcrescimosImpontualidade = Util
					.formatarMoedaRealparaBigDecimal(form.getValorAcrescimosImpontualidade());
		}

		BigDecimal valorDebitoACobrarServicoLongoPrazo = new BigDecimal("0.00");
		if (Util.verificarNaoVazio(form.getValorDebitoACobrarServicoLongoPrazo())) {
			valorDebitoACobrarServicoLongoPrazo = Util
					.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServicoLongoPrazo());
		}

		BigDecimal valorDebitoACobrarServicoCurtoPrazo = new BigDecimal("0.00");
		if (Util.verificarNaoVazio(form.getValorDebitoACobrarServicoCurtoPrazo())) {
			valorDebitoACobrarServicoCurtoPrazo = Util
					.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarServicoCurtoPrazo());
		}

		BigDecimal valorDebitoACobrarParcelamentoLongoPrazo = new BigDecimal("0.00");
		if (Util.verificarNaoVazio(form.getValorDebitoACobrarParcelamentoLongoPrazo())) {
			valorDebitoACobrarParcelamentoLongoPrazo = Util
					.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamentoLongoPrazo());
		}

		BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo = new BigDecimal("0.00");
		if (Util.verificarNaoVazio(form.getValorDebitoACobrarParcelamentoCurtoPrazo())) {
			valorDebitoACobrarParcelamentoCurtoPrazo = Util
					.formatarMoedaRealparaBigDecimal(form.getValorDebitoACobrarParcelamentoCurtoPrazo());
		}
	
		sessao.setAttribute("formaCobranca", form.getIndicadorFormaCobranca());
		
		// Valor a ser Negociado
		BigDecimal valorASerNegociado = new BigDecimal("0.00");
		BigDecimal valorDesconto = new BigDecimal("0.00");
		valorDesconto = valorDesconto.add(descontoAcrescimosImpontualidade);
		valorDesconto = valorDesconto.add(descontoAntiguidadeDebito);
		valorDesconto = valorDesconto.add(descontoInatividadeLigacaoAgua);
		form.setValorDesconto(Util.formatarMoedaReal(valorDesconto));
		valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorDesconto);

		// Montar o objeto Imovel para as inser��es no banco
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);
		Collection<Imovel> colecaoPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoPesquisado);

		// Recupera as cole��es de contas, guiaPagamentos, debitos e creditos
		Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
		}
		
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList<GuiaPagamentoValoresHelper>();
		if (sessao.getAttribute("colecaoGuiaPagamentoNegociacao") != null || indicadorGuiasPagamento.equals("1")) {
			colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoNegociacao");
		}

		Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList<DebitoACobrar>();
		if (sessao.getAttribute("colecaoDebitoACobrar") != null || indicadorDebitosACobrar.equals("1")) {
			colecaoDebitoACobrar = (Collection<DebitoACobrar>)sessao.getAttribute("colecaoDebitoACobrar");
		}

		Collection<CreditoARealizar> colecaoCreditoARealizar = new ArrayList<CreditoARealizar>();
		if (sessao.getAttribute("colecaoCreditoARealizar") != null || indicadorCreditoARealizar.equals("1")) {
			colecaoCreditoARealizar = (Collection<CreditoARealizar>)sessao.getAttribute("colecaoCreditoARealizar");
		}

		// Configura o cliente respons�vel pelo parcelamento que ser� o cliente cujo CPF foi digitado na tela
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.CPF, form.getCpfCliente()));
		Collection<Cliente> clientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(clientes);

		// Colocado por Raphael Rossiter em 25/08/2008 - Analista: Rosana Carvalho
		// O sistema verifica se o parcelamento � para ser inclu�do obrigatoriamente j� confirmado
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		String indicadorConfirmacaoParcelamento = ConstantesSistema.NAO.toString();
		if (sistemaParametro.getIndicadorParcelamentoConfirmado() == ConstantesSistema.SIM.shortValue()) {
			indicadorConfirmacaoParcelamento = ConstantesSistema.SIM.toString();
		}

		// CARREGANDO INFORMA��ES PARA CONCLUS�O DO PARCELAMENTO
		NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = (NegociacaoOpcoesParcelamentoHelper) sessao.getAttribute("opcoesParcelamento");
		Collection colecaoContasEmAntiguidade = null;
		if (opcoesParcelamento != null) {
			colecaoContasEmAntiguidade = opcoesParcelamento.getColecaoContasEmAntiguidade();
		}

		ConcluirParcelamentoDebitosHelper helper = new ConcluirParcelamentoDebitosHelper(
				colecaoContaValores, 
				colecaoGuiaPagamentoValores,
				colecaoDebitoACobrar, 
				colecaoCreditoARealizar, 
				indicadorRestabelecimento,
				indicadorContasRevisao, 
				indicadorGuiasPagamento, 
				indicadorAcrescimosImpotualidade,
				indicadorDebitosACobrar, 
				indicadorCreditoARealizar, 
				indicadorDividaAtiva,
				null, 
		        null, 
				imovel, 
				valorEntradaInformado,
				valorASerNegociado, 
				valorASerParcelado, 
				dataParcelamento, 
				valorTotalContaValores,
				valorGuiasPagamento, 
				valorDebitoACobrarServico, 
				valorDebitoACobrarParcelamento,
				valorCreditoARealizar, 
				valorAtualizacaoMonetaria, 
				valorJurosMora, 
				valorMulta,
				valorDebitoTotalAtualizado, 
				descontoAcrescimosImpontualidade, 
				descontoAntiguidadeDebito,
				descontoInatividadeLigacaoAgua, 
				percentualDescontoAcrescimosImpontualidade, 
				percentualDescontoAntiguidadeDebito, 
				percentualDescontoInatividadeLigacaoAgua, 
				parcelamentoPerfilId, 
				valorAcrescimosImpontualidade, 
				valorDebitoACobrarServicoLongoPrazo,
				valorDebitoACobrarServicoCurtoPrazo, 
				valorDebitoACobrarParcelamentoLongoPrazo,
				valorDebitoACobrarParcelamentoCurtoPrazo, 
				numeroPrestacoes, 
				valorPrestacao,
				valorEntradaMinima,
				taxaJuros,
				indicadorConfirmacaoParcelamento,
				cliente,
				usuarioLogado,
				cpfClienteParcelamentoDigitado,
				descontoSancoesRDEspecial,
				descontoTarifaSocialRDEspecial, 
				colecaoContasEmAntiguidade,
				descontoSobreDebitoTotal,
				opcoesParcelamento.getValorDescontoDebitoTipo(),
				opcoesParcelamento.getValorDescontoMulta(),
				opcoesParcelamento.getValorDescontoJuros(),
				opcoesParcelamento.getValorDescontoAtualizacaoMonetaria(),
				opcoesParcelamento.getEntradaEhExtratoDebito(),
				opcoesParcelamento.getValorDescontoEntrada());

		if (CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString().equals(form.getIndicadorFormaCobranca())) {
			
			ParcelamentoPagamentoCartaoCredito pagamento = fachada.inserirParcelamentoPagamentoCartaoCredito(helper, form.getEmail());

			request.setAttribute("idLoja", "9ca90e3b-c8b3-486a-9d2b-b46f56139f00"); // TODO: parametrizar
			request.setAttribute("nomeCliente", cliente.getNome());
			request.setAttribute("cpfCliente", cpfClienteParcelamentoDigitado.replaceAll("\\D", ""));
			request.setAttribute("idPagamento", pagamento.getId());
			request.setAttribute("idPagamento", pagamento.getId());
			request.setAttribute("valor", valorASerParcelado.toString().replaceAll("\\D", ""));
			request.setAttribute("numeroPrestacoes", numeroPrestacoes);

			FiltroBandeiraCartao filtroBandeira = new FiltroBandeiraCartao();
			filtroBandeira.adicionarParametro(new ParametroSimples(FiltroBandeiraCartao.INDICADOR_USO, ConstantesSistema.SIM));
			Collection<BandeiraCartao> colecaoBandeiras = fachada.pesquisar(filtroBandeira, BandeiraCartao.class.getName());
			request.setAttribute("bandeiras", colecaoBandeiras);

			retorno = actionMapping.findForward("parcelamentoCartaoCredito");
			return retorno;
		}

		try {
			Integer idParcelamento = fachada.concluirParcelamentoDebitosPortal(helper, usuarioLogado);
			sessao.setAttribute("idParcelamento", idParcelamento.toString());

			//[FS0013] - Verificar sucesso da transa��o
			// Monta a p�gina de sucesso
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, idParcelamento));
			Collection<GuiaPagamento> colecaoGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			
			if (!Util.isVazioOrNulo(colecaoGuiaPagamento)) {
				if (indicadorRestabelecimento.equals("1")) {
					request.setAttribute("mensagemReativacao", true);
				} else {
					retorno = actionMapping.findForward("gerarDocumentos");
				}
				GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(colecaoGuiaPagamento);
				String idGuiaPagamento = guiaPagamento.getId().toString();
				sessao.setAttribute("idGuiaPagamento", idGuiaPagamento);
			} else if (retorno.getName().equalsIgnoreCase("recarregarPagina")) {
				retorno = actionMapping.findForward("gerarDocumentos");
			}
		} catch (FachadaException e) {
			request.setAttribute("parcelamentoJaEfetuado", true);
		}

		return retorno;
	}

	// Vivianne Sousa 10/07/2008
	public boolean verificarValorEntradaMinimaPermitida(Short numeroPrestacoesSelecionada, BigDecimal valorEntrada,
			BigDecimal valorDebitoTotalAtualizado, ParcelamentoPerfil parcelamentoPerfil) {
		boolean valorMinimoPermitido = true;

		// caso o indicador de entrada m�nima seja SIM
		if (ConstantesSistema.SIM.equals(parcelamentoPerfil.getIndicadorEntradaMinima())) {
			BigDecimal valorPrestacao = Util.dividirArredondando(valorDebitoTotalAtualizado, new BigDecimal(numeroPrestacoesSelecionada));
			valorPrestacao = valorPrestacao.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
			// O sistema verifica se a entrada informada � menor que o valor 
			// da presta��o calculada da op��o de quantidade de parcelas selecionada 
			if (valorPrestacao.compareTo(valorEntrada) > 0) {
				valorMinimoPermitido = false;
			}
		}

		return valorMinimoPermitido;
	}
}
