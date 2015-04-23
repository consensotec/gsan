package gsan.gui.cobranca.contratoparcelamento;

import gsan.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gsan.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gsan.cobranca.contratoparcelamento.FiltroContratoParcelamentoPrestacao;
import gsan.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gsan.fachada.Fachada;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.cobranca.contratoparcelamento.RelatorioEmitirComprovantePagamentoContratoParcelamento;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioEmitirComprovantePagContratoParcelamentoAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * [UC1142] Emitir Comprovante de Pagamento de Contrato de Parcelamento por Cliente 
	 * 
	 * @author 
	 * @date 
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
		
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		EmitirComprovantePagamentoContratoParcelamentoClienteActionForm emitirComprovantePagamento = (EmitirComprovantePagamentoContratoParcelamentoClienteActionForm) actionForm;
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		TarefaRelatorio relatorio = null;
		
		ContratoParcelamentoCliente contratoParcelamentoCliente = null;
		ArrayList<PrestacaoContratoParcelamento> collectionPrestacaoContratoParcelamento = null;
		
		// Ocultar Parcela
		String ocultarParcela = "";
		
		//Parcela para Emissao
		String parcelaInicial = "";
		
		//Parcela Emissão
		String parcelaEmissao = "";
		
		if (httpServletRequest.getParameter("numeroParcela") != null
				&& !httpServletRequest.getParameter("numeroParcela").toString().trim().equals("")
				&& httpServletRequest.getParameter("idContrato") != null
				&& !httpServletRequest.getParameter("idContrato").toString().trim().equals("")) {
			/*
			 * Adicionado por: Mariana Victor
			 * Data: 08/07/2011
			 * 
			 * Emitir o comprovante da parcela paga a partir da funcionalidade 
			 *   "Informar Pagamento de Contrato Parcelamento por Cliente".
			 * */
			String idContrato = httpServletRequest.getParameter("idContrato").toString();
			
			Integer numeroParcela = new Integer(httpServletRequest.getParameter("numeroParcela"));
			
			FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = new FiltroContratoParcelamentoCliente();
			filtroContratoParcelamentoCliente.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoCliente.CONTRATO_PARCELAMENTO_ID, idContrato));
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato");
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.contratoAnterior");
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.relacaoCliente");
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.cobrancaForma");
			filtroContratoParcelamentoCliente.adicionarCaminhoParaCarregamentoEntidade("contrato.motivoDesfazer");
			Collection<ContratoParcelamentoCliente> colecaoContrato = fachada.pesquisar(
					filtroContratoParcelamentoCliente, ContratoParcelamentoCliente.class.getName());
			
			// Rotina para buscar o cliente superior se a ocorrência não for única
			if (colecaoContrato.size() > 1) {
				Iterator<ContratoParcelamentoCliente> it = colecaoContrato.iterator();
				while (it.hasNext()) {
					contratoParcelamentoCliente = (ContratoParcelamentoCliente) it.next();
					if (contratoParcelamentoCliente.getIndicadorClienteSuperior().toString().equals("1"))
						break;
				}
			} else {
				contratoParcelamentoCliente = (ContratoParcelamentoCliente)  
					Util.retonarObjetoDeColecao(colecaoContrato);
				if(contratoParcelamentoCliente.getIndicadorClienteSuperior().toString().equals("2") 
						&& contratoParcelamentoCliente.getClienteSuperior() == null
						&& contratoParcelamentoCliente.getContrato().getRelacaoCliente() != null){
							emitirComprovantePagamento.setTipoRelacao(
									contratoParcelamentoCliente.getContrato().getRelacaoCliente().getDescricao());
				}
			}
			
			
			FiltroContratoParcelamentoPrestacao filtroContratoParcelamentoPrestacao = new FiltroContratoParcelamentoPrestacao();
			filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroSimples(
					FiltroContratoParcelamentoPrestacao.NUMERO, numeroParcela));
			filtroContratoParcelamentoPrestacao.adicionarParametro(new ParametroSimples(
					FiltroContratoParcelamentoPrestacao.CONTRATO_PARCEL_ID, contratoParcelamentoCliente.getId()));
			
			collectionPrestacaoContratoParcelamento = new ArrayList<PrestacaoContratoParcelamento>(fachada.pesquisar(
					filtroContratoParcelamentoPrestacao, PrestacaoContratoParcelamento.class.getName()));

			ocultarParcela = "0";
			parcelaEmissao = numeroParcela.toString();
			
		} else {
			
			contratoParcelamentoCliente = (ContratoParcelamentoCliente) sessao.getAttribute("contratoParcelamentoCliente");
			collectionPrestacaoContratoParcelamento = (ArrayList<PrestacaoContratoParcelamento>)sessao.getAttribute("parcelas");
			
			ocultarParcela = emitirComprovantePagamento.getOcultarParcela();
			parcelaEmissao = emitirComprovantePagamento.getParcelaEmissao();
			parcelaInicial = emitirComprovantePagamento.getInicioParcelas();
		}
		
		relatorio = new RelatorioEmitirComprovantePagamentoContratoParcelamento(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("contratoParcelamentoCliente", contratoParcelamentoCliente);
		relatorio.addParametro("collectionContratoParcelamentoItem",collectionPrestacaoContratoParcelamento);
		relatorio.addParametro("ocultarParcela",ocultarParcela);
		relatorio.addParametro("parcelaEmissao",parcelaEmissao);
		relatorio.addParametro("inicioParcelas",parcelaInicial);
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
	}
}
