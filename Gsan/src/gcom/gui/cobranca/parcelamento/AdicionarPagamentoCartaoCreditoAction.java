package gcom.gui.cobranca.parcelamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdicionarPagamentoCartaoCreditoAction extends GcomAction {

	/**
	 * @param args
	 * Gsan
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarPagamentoCartaoCreditoAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		ParcelamentoCartaoConfirmarForm form = (ParcelamentoCartaoConfirmarForm) actionForm;
		
		if(form.getIdCliente()!=null && 
				!form.getIdCliente().equals("")){
			
		
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					form.getIdCliente()));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado==null || (clienteEncontrado != null && clienteEncontrado.isEmpty())) {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		}
		
		Collection colecaoTransacao = null;
		

		if (sessao.getAttribute("colecaoTransacao") != null) {
			colecaoTransacao = (Collection) sessao
					.getAttribute("colecaoTransacao");
		}else{
			colecaoTransacao = new ArrayList();
		}
		
		ParcelamentoCartaoCreditoHelper helper = new ParcelamentoCartaoCreditoHelper();
		
		if(form.getCartaoCredito() != null && !form.getCartaoCredito().trim().equals("")){
			helper.setIdClienteArrecadador(form.getCartaoCredito());
		}else{		
			throw new ActionServletException("atencao.required",
					null, "a operadora do cartão.");				
		}
	
		//Cliente titular do cartão
		if(form.getIdCliente() != null  && !form.getIdCliente().trim().equals("")){
			helper.setIdCliente(form.getIdCliente());
		}else{		
			throw new ActionServletException("atencao.required",
				null, "o cliente titular do cartão");				
		}
		
		//Numero do cartão 
		if(form.getNumeroCartao() != null && !form.getNumeroCartao().trim().equals("")){
			
			if(form.getNumeroCartao().matches("^[0\\s]*$") || form.getNumeroCartao().trim().length() < 11){
				throw new ActionServletException("atencao.numero_cartao_invalido", null ,"" );	
			}else{
				helper.setNumeroCartao(form.getNumeroCartao());
			}
		}
		
		//Data de validade do cartão
		if(form.getValidadeCartao() != null && !form.getValidadeCartao().trim().equals("")){	
			if(Util.validarAnoMesInvalido(form.getValidadeCartao(),"mm/yyyy")){					
				throw new ActionServletException("atencao.data_validade_cartao_invalido",
					null, "");
			}else{				
				helper.setValidadeCartao(form.getValidadeCartao());		
			}
		}else{		
			throw new ActionServletException("atencao.required",
				null, "a data de validade do cartão");				
		}
		
		if (form.getValidadeCartao() != null && !form.getValidadeCartao().trim().equals("")) {
			if (form.getValidadeCartao() != null
					&& !form.getValidadeCartao().equalsIgnoreCase("")) {
				Date dataAtual = new Date(System.currentTimeMillis());
				Integer anoMesAtual = Util.getAnoMesComoInteger(dataAtual);
				Integer anoMesValidade = Util.formatarMesAnoComBarraParaAnoMes(form.getValidadeCartao());
				
				if(anoMesValidade < anoMesAtual){					
					throw new ActionServletException("atencao.data_validade_cartao_invalido",
						null, "");
				}else{
					anoMesValidade = Util
							.formatarMesAnoComBarraParaAnoMes(form
									.getValidadeCartao());
				}
				if (Util.compararAnoMesReferencia(anoMesValidade, anoMesAtual,
						"<")) {
					sessao.setAttribute("validadeCartaoinvalida", "true");
					form.setValidadeCartaoInvalida("true");

				} else {
					sessao.removeAttribute("validadeCartaoinvalida");
					form.setValidadeCartaoInvalida("false");
				}
				
				
			}

		}
		
		
		//Numero documento do cartão
		if(form.getDocumentoCartao() != null && !form.getDocumentoCartao().trim().equals("")){
			helper.setDocumentoCartao(form.getDocumentoCartao());
		}else{		
			throw new ActionServletException("atencao.required",
				null, "o número do documento do cartão");				
		}
		
		//Número de Identificação da Transação (NSU)
		if(form.getNumeroIdentificacaoTransacao() != null && !form.getNumeroIdentificacaoTransacao().trim().equals("")){
			helper.setNumeroIdentificacaoTransacao(form.getNumeroIdentificacaoTransacao());
		}else{		
			throw new ActionServletException("atencao.required",
				null, "o número de identificação da transação (NSU)");				
		}		
		
		//Numero de autorização do cartão
		if(form.getAutorizacaoCartao() != null && !form.getAutorizacaoCartao().trim().equals("")){
			helper.setAutorizacaoCartao(form.getAutorizacaoCartao());
		}else{		
			throw new ActionServletException("atencao.required",
				null, "o número autorização cartão");				
		}
		
		//Valor da Transação
		if(form.getValorTransacao() != null && !form.getValorTransacao().trim().equals("")){		
			helper.setValorTransacao(form.getValorTransacao());
		}else{		
			throw new ActionServletException("atencao.required",
				null, "o valor da transação");				
		}
		
		//Quantidade de Parcelas
		if(form.getQtdParcelas() != null){
			helper.setQtdParcelas(form.getQtdParcelas());
		}
		
		helper.setUsuario(usuarioLogado);
		helper.setTempoInclusao(System.currentTimeMillis()+"");			
		
		
		if (form.getModalidadeCartao().equals(ConstantesSistema.MODALIDADE_CARTAO_CREDITO.toString())){
			
			//[FS0008] – Verificar validade da data
			fachada.verificarValidadeData(Util.converteStringParaDate(form.getDataOperadora()),
			Integer.valueOf(form.getCartaoCredito()), ArrecadacaoForma.CARTAO_CREDITO);
		}
		else{
			
			//[FS0008] – Verificar validade da data
			fachada.verificarValidadeData(Util.converteStringParaDate(form.getDataOperadora()),			
					Integer.valueOf(form.getCartaoCredito()), ArrecadacaoForma.CARTAO_DEBITO);
		}
		
		//Data da confirmação na operadora
		if(form.getDataOperadora() != null && !form.getDataOperadora().trim().equals("")){
			helper.setDataConfirmacaoOperadora(Util.converteStringParaDate(form.getDataOperadora()));
		}
		
		//Verifica se o arquivo (.JPG) foi passado
		if(form.getArquivoComprovante() != null && !form.getArquivoComprovante().getFileName().equals("")){
			
			if(form.getArquivoComprovante().getFileName().toUpperCase().endsWith(".JPG")){

					try {
						helper.setImagemArquivoComprovante(form.getArquivoComprovante().getFileData());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{

					throw new ActionServletException(
						"atencao.required",
						null, "Apenas arquivo em formato (.jpg)");
				}			
		}		
		
		colecaoTransacao.add(helper);
		
		if(!colecaoTransacao.isEmpty()){		
			sessao.setAttribute("colecaoTransacao",colecaoTransacao);
			httpServletRequest.setAttribute("reload", true);
		}else{
			sessao.setAttribute("colecaoPagamentos",null);
			httpServletRequest.setAttribute("reload", true);
		}
		
		return retorno;
	}

}
