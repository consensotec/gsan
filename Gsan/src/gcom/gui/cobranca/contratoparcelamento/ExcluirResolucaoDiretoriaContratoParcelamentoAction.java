package gcom.gui.cobranca.contratoparcelamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExcluirResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma resolu��o de diretoria
	 * 
	 * [UC1134] Manter Resolu��o de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obt�m a inst�ncia da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		ExcluirResolucaoDiretoriaContratoParcelamentoActionForm form = (ExcluirResolucaoDiretoriaContratoParcelamentoActionForm) actionForm;
		String ids[] = form.getIds();
		
		for (String id : ids) {
			boolean verificaContratoAssociado = fachada.verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(Integer.parseInt(id));
			if(verificaContratoAssociado){
				throw new ActionServletException("atencao.existe.contrato.parcelamento.associado.remover", null, "");
			}
		}
		
		boolean sucesso = this.getFachada().excluirContratosParcelamentoResolucaoDiretoria(ids, usuarioLogado);
	
		if(sucesso){
			montarPaginaSucesso(httpServletRequest, ids.length+" Resolu��o(�es) de diretoria removida(s) com sucesso",
					"Realizar outra Manuten��o de Resolu��o de diretoria para contratos de parcelamento por cliente",
			"exibirFiltrarResolucaoDiretoriaContratoParcelamentoAction.do?menu=sim");
		}else{
			
		}
		
		
		return retorno;
	}
		
}
