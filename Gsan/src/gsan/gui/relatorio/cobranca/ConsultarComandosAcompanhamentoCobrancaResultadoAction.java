package gsan.gui.relatorio.cobranca;

import gsan.cobranca.ComandoEmpresaCobrancaConta;
import gsan.cobranca.FiltroComandoEmpresaCobrancaConta;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * [UC1186] Gerar Relatório Ordem de Serviço Cobrança p/Resultado
 * 
 * @author Hugo Azevedo 
 * @date 02/07/2011
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */


public class ConsultarComandosAcompanhamentoCobrancaResultadoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("exibirGerar");
		ConsultarComandosAcompanhamentoCobrancaResultadoActionForm form = (ConsultarComandosAcompanhamentoCobrancaResultadoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		String idRegistro = form.getIdRegistro();
		String idEmpresa = form.getIdEmpresa();
		
		
		
		
		FiltroComandoEmpresaCobrancaConta filtro = new FiltroComandoEmpresaCobrancaConta();
		filtro.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaConta.ID, idRegistro));
		
		Collection colecao = fachada.pesquisar(filtro, ComandoEmpresaCobrancaConta.class.getName());
		ComandoEmpresaCobrancaConta comando = null;
		if(colecao.size() > 0){
			comando = (ComandoEmpresaCobrancaConta)Util.retonarObjetoDeColecao(colecao);
		}
		
		HashMap hash = new HashMap();
		hash.put("idEmpresa",idEmpresa);
		hash.put("comando",comando);
		
		sessao.setAttribute("objetos",hash);
		
		return retorno;
	}

}
