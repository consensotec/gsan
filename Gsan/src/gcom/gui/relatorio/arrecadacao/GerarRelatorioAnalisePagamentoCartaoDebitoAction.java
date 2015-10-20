package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioAnalisePagamentoCartaoDebito;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1043] Gerar Relat�rio An�lise Pagamento Cart�o D�bito
 * 
 * @author Hugo Amorim
 * @since 21/07/2010
 *
 */
public class GerarRelatorioAnalisePagamentoCartaoDebitoAction 
	extends ExibidorProcessamentoTarefaRelatorio{
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);	
		
		RelatorioAnalisePagamentoCartaoDebitoForm form =
			(RelatorioAnalisePagamentoCartaoDebitoForm) actionForm;
		
		//Montar objeto helper para realiza��o das pesquisas.
		ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper 
			helper = new ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper(

				form.getDataConfirmacaoPagamentoInicial()!=null ? 
						form.getDataConfirmacaoPagamentoInicial():null,

				form.getDataConfirmacaoPagamentoFinal()!=null ? 
					form.getDataConfirmacaoPagamentoFinal():null,

				form.getIdUsuarioConfirmacao()!=null ? 
					form.getIdUsuarioConfirmacao():null,
			
				form.getIndicadorConfirmacaoOperadora()!=null ? 
						form.getIndicadorConfirmacaoOperadora():null,		
			
				form.getDataConfirmacaoOperadoraInicial()!=null ? 
						form.getDataConfirmacaoOperadoraInicial():null,	

				form.getDataConfirmacaoOperadoraFinal()!=null ? 
					form.getDataConfirmacaoOperadoraFinal():null		
				);
		
		fachada.validarGerarRelatorioAnalisePagamentoCartaoDebito(helper);
		
		
		RelatorioAnalisePagamentoCartaoDebito relatorio
			= new RelatorioAnalisePagamentoCartaoDebito(usuario);
	
		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		
		relatorio.addParametro("helper", helper);
		
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		return retorno;
	}

}
