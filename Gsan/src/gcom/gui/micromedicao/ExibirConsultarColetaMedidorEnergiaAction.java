package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarColetaMedidorEnergiaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarColetaMedidorEnergia");

		//Recupera os par�metros da sess�o para ser efetuada a pesquisa
		ColetaMedidorEnergiaHelper helper = (ColetaMedidorEnergiaHelper)httpServletRequest.getAttribute("helper");
		
		FiltrarColetaMedidorEnergiaActionForm form = (FiltrarColetaMedidorEnergiaActionForm) actionForm;
		
		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
        //Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
		
		//Integer qtdImoveis = fachada.countColetaMedidorEnergia(helper);
		
		Collection colecaoColetaMedidorEnergia =  (Collection) fachada.pesquisarColetaMedidorEnergia(helper);
		
		if (colecaoColetaMedidorEnergia != null
				&& !colecaoColetaMedidorEnergia.isEmpty()) {

			// pega os dados do retorno e passa pela sess�o para ser exibido na tela
			ColetaMedidorEnergiaHelper helper1 = (ColetaMedidorEnergiaHelper) colecaoColetaMedidorEnergia.iterator().next();
			
			form.setDescricaoEmpresa(helper1.getDescricaoEmpresa());
			form.setFaturamentoGrupo(helper1.getFaturamentoGrupo());
			form.setIdLocalidade(helper1.getLocalidadeId());
			form.setCodigoSetorComercial( helper1.getSetorComercialId());
			form.setRota( helper1.getRotaId());
			
			//Verifica se a cole��o retornada pela pesquisa � nula	
			sessao.setAttribute(
					"colecaoColetaMedidorEnergia", colecaoColetaMedidorEnergia);

		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}

}
