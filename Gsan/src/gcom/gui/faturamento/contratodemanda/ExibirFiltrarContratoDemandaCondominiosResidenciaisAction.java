package gcom.gui.faturamento.contratodemanda;

import gcom.cadastro.imovel.Imovel;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1229] - Manter Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 23/09/2011
 *
 */
public class ExibirFiltrarContratoDemandaCondominiosResidenciaisAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward retorno = mapping.findForward("filtrarContratoDemandaCondominiosResidenciais");
		FiltrarContratoDemandaCondominiosResidenciaisActionForm form = (FiltrarContratoDemandaCondominiosResidenciaisActionForm) formulario;
		
		String voltar = request.getParameter("voltar");
		String desfazer = request.getParameter("desfazer");
		if(voltar == null || !voltar.equalsIgnoreCase("sim")){
			if(Util.verificarIdNaoVazio(form.getMatriculaImovel())){
				Imovel imovel = this.getFachada().pesquisarImovel(Integer.valueOf(form.getMatriculaImovel()));
				if(imovel != null){	
					form.setInscricaoImovel(imovel.getInscricaoFormatada());
				}else{
					form.setInscricaoImovel("IMOVEL INEXISTENTE");
					request.setAttribute("codigoImovelNaoEncontrado", true);
				}
			}else{
				form.setIndicadorAtualizar("1");
			}
		}else if(Util.verificarNaoVazio(desfazer) && desfazer.equalsIgnoreCase("sim")){
			form.setDataInicioContrato("");
			form.setDataInicioContrato2("");		
			form.setDataFimContrato("");
			form.setDataFimContrato2("");
			form.setIndicadorAtualizar("");
			form.setInscricaoImovel("");
			form.setMatriculaImovel("");
			form.setNumeroContrato("");
		}
			
		return retorno;
	}
}