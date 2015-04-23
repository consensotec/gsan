package gsan.gui.cadastro.imovel;

import gsan.cadastro.imovel.FiltroImovelProgramaEspecial;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.imovel.ImovelProgramaEspecial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC ]
 * 
 * @author Rodrigo Cabral
 * @date 17/08/2012
 *
 */

public class ExibirConsultarHistoricoProgramaEspecialAction extends GcomAction {
	
	/**
	 * Método responsavel por consultar o documento de arrecadação pelo nosso número
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("consultarHistoricoProgramaEspecial");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega uma instancia do actionform
		ConsultarHistoricoProgramaEspecialActionForm consultarHistoricoProgramaEspecialActionForm = (ConsultarHistoricoProgramaEspecialActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		
		
		String idImovel = consultarHistoricoProgramaEspecialActionForm.getIdImovel();


		// Verifica se foi feita uma pesquisa de imovel que retornou para este
		// exibir
		if (idImovel != null  && !idImovel.equals("")) {

			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));
			
			if (imovel != null){
				
			FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = new FiltroImovelProgramaEspecial();
			filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.USUARIO_RESPONSAVEL);
			filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.USUARIO_SUSPENSAO);
			filtroImovelProgramaEspecial.adicionarParametro(new ParametroSimples(FiltroImovelProgramaEspecial.IMOVEL_ID, imovel.getId()));
			
			
			Collection<ImovelProgramaEspecial> colecaoProgramaEspecial = fachada.pesquisar(filtroImovelProgramaEspecial, ImovelProgramaEspecial.class.getName());
			
			consultarHistoricoProgramaEspecialActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			
			if (!colecaoProgramaEspecial.isEmpty()) {
				
			
		        	sessao.setAttribute("colecaoProgramaEspecial", colecaoProgramaEspecial);
		        } else {
		        	sessao.removeAttribute("colecaoProgramaEspecial");
		        	throw new ActionServletException(
							"atencao.imovel_nao_programa_especial",
							null, imovel.getId().toString());
		        } 
				
			} else {
				// Matrícula inexistente
				httpServletRequest.setAttribute("imovelNaoEncontrado", true);
				consultarHistoricoProgramaEspecialActionForm.setIdImovel("");
				consultarHistoricoProgramaEspecialActionForm.setInscricaoImovel("Imóvel Inexistente");
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
				sessao.removeAttribute("colecaoProgramaEspecial");
			}
		}else {
			sessao.removeAttribute("colecaoProgramaEspecial");
		}
		
		return retorno;
	}

}
