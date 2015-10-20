package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverImovelRamoAtividadeAction extends GcomAction {
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

		// Prepara o retorno da A��o
		ActionForward retorno = actionMapping
				.findForward("inserirImovelSubCategoria");

		// Obt�m a inst�ncia da sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		//DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		// Cria variaveis
		Collection colecaoImovelRamosAtividades = (Collection) sessao
				.getAttribute("colecaoImovelRamosAtividade");

		Collection colecaoImovelRamosAtividadeRemovidos = new ArrayList();

		ImovelRamoAtividade imovelRamoAtividade = null;

		// Obt�m os ids de remo��o
		String[] removerImovelRamoAtividade = httpServletRequest.getParameterValues("removerImovelRamoAtividade");

		if (colecaoImovelRamosAtividades != null
				&& !colecaoImovelRamosAtividades.equals("")) {

			Iterator imovelRamosAtividadesIterator = colecaoImovelRamosAtividades.iterator();

			while (imovelRamosAtividadesIterator.hasNext()) {
				imovelRamoAtividade = (ImovelRamoAtividade) imovelRamosAtividadesIterator.next();
				for (int i = 0; i < removerImovelRamoAtividade.length; i++) {
					if (imovelRamoAtividade.getUltimaAlteracao().getTime() == Long
							.parseLong(removerImovelRamoAtividade[i])) {
						if(!(colecaoImovelRamosAtividadeRemovidos.contains(imovelRamoAtividade))){
							colecaoImovelRamosAtividadeRemovidos
									.add(imovelRamoAtividade);
							imovelRamosAtividadesIterator.remove();
						}
					}
				}
			}
			sessao.setAttribute(
					"colecaoImovelRamosAtividadesRemovidos",
					colecaoImovelRamosAtividadeRemovidos);

		}


		return retorno;
	}

}
