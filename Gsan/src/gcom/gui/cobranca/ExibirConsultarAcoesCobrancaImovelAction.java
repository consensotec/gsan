package gcom.gui.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1370] Consultar Ações de Cobrança por Imóvel
 * 
 * @author Davi Menezes
 * @date 15/08/2012
 *
 */
public class ExibirConsultarAcoesCobrancaImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("consultarAcoesCobrancaImovel");
		
		ExibirConsultarAcoesCobrancaImovelActionForm form = 
				(ExibirConsultarAcoesCobrancaImovelActionForm) actionForm;
		
		Fachada fachada = this.getFachada();
		
		if(form.getIdImovel() != null && !form.getIdImovel().equals("")){
			this.pesquisarImovel(form, fachada, httpServletRequest);
		}
		
		return retorno;
	}

	/**
	 * Pesquisar o Imóvel Digitado
	 * 
	 * @author Davi Menezes
	 * @date 15/08/2012
	 * 
	 */
	private void pesquisarImovel(ExibirConsultarAcoesCobrancaImovelActionForm form, Fachada fachada, HttpServletRequest request) {
		Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(form.getIdImovel()));
		
		if(imovel != null){
			form.setInscricaoImovel(imovel.getInscricaoFormatada());
			
			request.removeAttribute("imovelNaoEncontrado");
			request.setAttribute("nomeCampo", "periodoInicialAcao");
		}else{
			form.setIdImovel("");
			form.setInscricaoImovel("Imóvel Inexistente");
			
			request.setAttribute("imovelNaoEncontrado", true);
		}
	}
	
}
