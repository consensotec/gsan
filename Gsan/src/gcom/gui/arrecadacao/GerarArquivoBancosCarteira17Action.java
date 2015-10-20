package gcom.gui.arrecadacao;
import gcom.arrecadacao.bean.DebitoCarteiraMovimentoHelper;
import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
 * [UC1574] - Solicitar Geracao de Arquivo Carteira 17 
 * 
 * @author Diogo Luiz
 * @Date 07/11/2013
 *
 */
public class GerarArquivoBancosCarteira17Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		DebitoCarteiraMovimentoHelper debitoCarteiraMovimentoHelper = new DebitoCarteiraMovimentoHelper();
		
		Collection colecao = new ArrayList();
		
		if(sessao.getAttribute("debitosAutomaticoBancosCarteira17") != null 
				&& !sessao.getAttribute("debitosAutomaticoBancosCarteira17").equals("")){
			colecao = (Collection) sessao.getAttribute("debitosAutomaticoBancosCarteira17");
		}		
		
		if(!Util.isVazioOrNulo(colecao)){
			debitoCarteiraMovimentoHelper = (DebitoCarteiraMovimentoHelper) 
					Util.retonarObjetoDeColecao(colecao);
		}else{
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		Map parametros = new HashMap();
    	parametros.put("helper",debitoCarteiraMovimentoHelper);
    	Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
         		Processo.GERAR_ARQUIVO_BANCO_CARTEIRA_17,
         		this.getUsuarioLogado(httpServletRequest));		
		
		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Gerar Arquivo Bancos Carteira 17 Enviado para Processamento Batch", "Voltar",
				"/exibirGerarArquivoBancosCarteira17Action.do");

		return retorno;
	}
}