package gsan.micromedicao;


import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de exibir consultar Foto Anormalidade
 * 
 * @author Carlos Chaves
 * @created 24/10/2012
 */
public class ExibirConsultarFotoMicroMedicaoPopupAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
		ActionForm actionForm, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("consultarFotoMicroMedicaoPopup");
		Fachada fachada = Fachada.getInstancia();				
		
		Integer idImovel = (new Integer(httpServletRequest.getParameter("id")));
		String anoMesReferencia = httpServletRequest.getParameter("anoMes");
		Integer idMedicaoTipo = (new Integer(httpServletRequest.getParameter("metp")));
		Integer anoMesReferenciaFormatado = 0;
		
		if( anoMesReferencia.indexOf('/') > -1 ) {
			anoMesReferenciaFormatado = Util.formatarMesAnoComBarraParaAnoMes(anoMesReferencia);
		}else{
			anoMesReferenciaFormatado = Integer.parseInt(anoMesReferencia);
		}
		
		Collection <MovimentoRoteiroEmpresaFoto> fotos = 
				fachada.pesquisarFotoAnormalidadeImovel (idImovel, anoMesReferenciaFormatado, idMedicaoTipo);
		
		if(fotos !=null && !fotos.isEmpty()){
			httpServletRequest.setAttribute("fotos", fotos);
			httpServletRequest.setAttribute("idImovel", idImovel);
			httpServletRequest.setAttribute("anoMes", anoMesReferencia);
		}else{
			throw new ActionServletException("atencao.msg_personalizada", "Não existe foto registrada para anormalidade de leitura.");
		}
		return retorno;
	}
}