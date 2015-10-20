package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.gui.cobranca.ConsultarDebitoClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioConsultarDebitosAcrescimosDetalhado;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioDebitosAcrescimosDetalhadoConsultarAction extends
ExibidorProcessamentoTarefaRelatorio {

	/**
	* < <Descri��o do m�todo>>
	* 
	* @param actionMapping
	*            Descri��o do par�metro
	* @param actionForm
	*            Descri��o do par�metro
	* @param httpServletRequest
	*            Descri��o do par�metro
	* @param httpServletResponse
	*            Descri��o do par�metro
	* @return Descri��o do retorno
	*/
	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String pesquisaCliente = httpServletRequest.getParameter("pesquisaCliente");
		String relatorioEndereco = httpServletRequest.getParameter("relatorioEndereco");
		
		Collection colecaoContaValores = new ArrayList();
		Collection colecaoDebitoACobrar = new ArrayList();
		Collection colecaoCreditoARealizar = new ArrayList();
		Collection colecaoGuiasPagamento = new ArrayList();
		String valorTotalDebitos = null;
		String valorTotalDebitosAtualizado = null;
		 
		 if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection)sessao.getAttribute("colecaoContaValores");
		 }
		 
		 if(sessao.getAttribute("colecaoDebitoACobrar") != null){
			 colecaoDebitoACobrar = (Collection)sessao.getAttribute("colecaoDebitoACobrar"); 
		 }
		 
		 if(sessao.getAttribute("colecaoCreditoARealizar") != null){
			 colecaoCreditoARealizar = (Collection)sessao.getAttribute("colecaoCreditoARealizar"); 
		 }
		 
		 if(sessao.getAttribute("colecaoGuiaPagamentoValores") != null){
			 colecaoGuiasPagamento = (Collection)sessao.getAttribute("colecaoGuiaPagamentoValores"); 
		 }
		 
		 if (sessao.getAttribute("valorTotalSemAcrescimo") != null) {
			 valorTotalDebitos = (String) sessao.getAttribute("valorTotalSemAcrescimo");
		 }
		 
		 if (sessao.getAttribute("valorTotalComAcrescimo") != null) {
			 valorTotalDebitosAtualizado = (String) sessao.getAttribute("valorTotalComAcrescimo");
		 }
		 
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		RelatorioConsultarDebitosAcrescimosDetalhado relatorioConsultarDebitos = new RelatorioConsultarDebitosAcrescimosDetalhado(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioConsultarDebitos.addParametro("colecaoContaValores", colecaoContaValores);
		relatorioConsultarDebitos.addParametro("colecaoDebitoACobrar", colecaoDebitoACobrar);
		relatorioConsultarDebitos.addParametro("colecaoCreditoARealizar", colecaoCreditoARealizar);
		relatorioConsultarDebitos.addParametro("colecaoGuiasPagamento", colecaoGuiasPagamento);
		if (actionForm instanceof ConsultarImovelActionForm) {
			
			ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
			
			String endereco = fachada.pesquisarEndereco(new Integer(
					consultarImovelActionForm.getIdImovelDebitos()));
		
			Cliente cliente = fachada
					.pesquisarClienteUsuarioImovelExcluidoOuNao(new Integer(
							consultarImovelActionForm.getIdImovelDebitos()));
		
			relatorioConsultarDebitos.addParametro("idImovel",
					consultarImovelActionForm.getIdImovelDebitos());
			relatorioConsultarDebitos.addParametro("inscricao",
					consultarImovelActionForm.getMatriculaImovelDebitos());
			relatorioConsultarDebitos.addParametro("endereco", endereco);
			String cpfCnpjCliente = "";
			
			if (cliente != null) {
				relatorioConsultarDebitos.addParametro("clienteUsuario", cliente
						.getNome());
				if ( cliente.getCnpj() != null ) {
					cpfCnpjCliente = cliente.getCnpjFormatado();
				} else if ( cliente.getCpf() != null ) {
					cpfCnpjCliente = cliente.getCpfFormatado();
				}
			} else {
				relatorioConsultarDebitos.addParametro("clienteUsuario", "");
			}
			
			relatorioConsultarDebitos.addParametro("cpfCnpjCliente", cpfCnpjCliente);
		
		} else {
			
			ConsultarDebitoClienteActionForm consultarDebitoClienteActionForm = (ConsultarDebitoClienteActionForm) actionForm;
			
			ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) sessao.getAttribute("tipoRelacao");
			
			if (consultarDebitoClienteActionForm.getCodigoCliente() != null 
					&& !consultarDebitoClienteActionForm.getCodigoCliente().trim().equals("")) {
						relatorioConsultarDebitos.addParametro("codigoCliente",
								consultarDebitoClienteActionForm.getCodigoCliente());
						relatorioConsultarDebitos.addParametro("nomeCliente",
								consultarDebitoClienteActionForm.getNomeCliente());
						relatorioConsultarDebitos.addParametro("cpfCnpj",
								consultarDebitoClienteActionForm.getCpfCnpj());
						relatorioConsultarDebitos.addParametro("tipoRelacao", tipoRelacao);
			} else {
						relatorioConsultarDebitos.addParametro("codigoCliente",
								consultarDebitoClienteActionForm.getCodigoClienteSuperior());
						relatorioConsultarDebitos.addParametro("nomeCliente",
								consultarDebitoClienteActionForm.getNomeClienteSuperior());
						relatorioConsultarDebitos.addParametro("cpfCnpj",
								consultarDebitoClienteActionForm.getCpfCnpj());
						relatorioConsultarDebitos.addParametro("tipoRelacao", null);
			}
			
			relatorioConsultarDebitos.addParametro("periodoReferenciaInicial",
					consultarDebitoClienteActionForm.getReferenciaInicial());
			relatorioConsultarDebitos.addParametro("periodoReferenciaFinal",
					consultarDebitoClienteActionForm.getReferenciaFinal());
			relatorioConsultarDebitos
					.addParametro("dataVencimentoInicial",
							consultarDebitoClienteActionForm
									.getDataVencimentoInicial());
			relatorioConsultarDebitos.addParametro("dataVencimentoFinal",
					consultarDebitoClienteActionForm.getDataVencimentoFinal());
			
			
		}
		
		relatorioConsultarDebitos.addParametro("pesquisaCliente", pesquisaCliente);
		relatorioConsultarDebitos.addParametro("relatorioEndereco", relatorioEndereco);
		
		relatorioConsultarDebitos.addParametro("valorTotalDebitos", valorTotalDebitos);
		relatorioConsultarDebitos.addParametro("valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);
		
		 
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioConsultarDebitos.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(
				relatorioConsultarDebitos, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
