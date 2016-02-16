package gcom.gui.portal.comum.cartaocredito;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.bean.RetornoCartaoCreditoHelper;
import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1692] - Registrar Retorno do Cartão de Crédito
 * 
 * @author André Miranda
 * @date 30/09/2015
 */
public class RegistrarRetornoDadosCartaoCreditoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("retornoPgtoCartoCredito");
        HttpSession sessao = request.getSession(false);
		Fachada fachada = Fachada.getInstancia();

		String codRetorno = request.getParameter("CODRETORNO");
		String descRetorno = request.getParameter("DESRETORNO");
		String idRequest = request.getParameter("REQUESTID");
		String idMerchant = request.getParameter("MERCHANTID");
		String ip = request.getParameter("IPADDRESS");
		String idTransaction = request.getParameter("TRANSACTIONID");
		String idTrans = request.getParameter("TRANSID");
		String nomeCliente = request.getParameter("NOME");
		String cpfCliente = request.getParameter("CPF");
		String formaPagamento = request.getParameter("FORMAPAGTO");
		String codPagamento = request.getParameter("CODPAGAMENTO");
		String valor = request.getParameter("VALOR");
		String codAutorizacao = request.getParameter("CODAUTORIZACAO");
		String qtdParcelas = request.getParameter("PARCELAS");
		String idPedidoBraspag = request.getParameter("BraspagOrderId");
		String proofOfSale = request.getParameter("PROOFOFSALE");
		Integer idVenda = Integer.valueOf(request.getParameter("VENDAID"));

		// Verificar se houve erro
		// Os campos codRetorno e descRetorno vêm em branco em caso de sucesso
		if(Util.verificarNaoVazio(codRetorno)) {
			FiltroParcelamentoPagamentoCartaoCredito filtro = new FiltroParcelamentoPagamentoCartaoCredito();
			filtro.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID, idVenda));
			Collection<ParcelamentoPagamentoCartaoCredito> colecao = getFachada().pesquisar(filtro,
							ParcelamentoPagamentoCartaoCredito.class.getName());
			ParcelamentoPagamentoCartaoCredito ppcc = (ParcelamentoPagamentoCartaoCredito) Util
					.retonarObjetoDeColecao(colecao);

			if(ppcc != null) {
				getFachada().remover(ppcc);
			}

			request.setAttribute("messagemFalha", "Pagamento por cartão de crédito cancelado. Motivo: " + descRetorno);
			return retorno;
		}

		RetornoCartaoCreditoHelper helper = new RetornoCartaoCreditoHelper();
		helper.setIdVenda(idVenda);
		helper.setNomeCliente(nomeCliente);
		helper.setCpfCliente(cpfCliente);
		helper.setValorTransacao(valor);
		helper.setNumeroPedido(idPedidoBraspag);
		helper.setQtdParcelas(qtdParcelas);
		helper.setNumeroIdentificacaoTransacao(idTrans);
		helper.setIdentificacaoRequisicao(idRequest);
		helper.setCodigoComprovanteVenda(proofOfSale);
		helper.setIdentificacaoPagamento(idTransaction);

		Integer idParcelamento = fachada.registrarRetornoCartaoCreditoAprovado(helper);
		if(idParcelamento == null) {
			request.setAttribute("messagemFalha", "Falha na consistência dos dados informados na transação de pagamento por cartão de crédito.");
			return retorno;
		}

		sessao.setAttribute("idParcelamento", idParcelamento.toString());
		sessao.setAttribute("formaCobranca", CobrancaForma.COBRANCA_EM_CARTAO_CREDITO.toString());

		return retorno;
	}
}
