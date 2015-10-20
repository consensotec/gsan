package gcom.gui.portal.caern;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarConsumoHistoricoAguaPortalCaernAction extends GcomAction{
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping.findForward("exibirConsultarConsumoHistoricoAguaPortalCaernAction");
		
		httpServletRequest.setAttribute("voltarServicos", true);

		Integer matricula = (Integer) sessao.getAttribute("matricula");
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.CONSULTAR_CONSUMO_HISTORICO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		
		Collection<ImovelMicromedicao> imoveisMicromedicao = 
			Fachada.getInstancia().carregarDadosConsumo(
					matricula,true);

		if (!Util.isVazioOrNulo(imoveisMicromedicao)) {				
			Collections.sort((List<ImovelMicromedicao>) imoveisMicromedicao, new ComparatorImovelMicromedicao());

			httpServletRequest.setAttribute("imoveisMicromedicao",imoveisMicromedicao);
		}else {
			httpServletRequest.removeAttribute("voltarServicos");
			retorno = actionMapping.findForward("imovelSemConsumoCaern");
			httpServletRequest.setAttribute("imovelSemConsumos", true);
		}
		
		return retorno;
	}
	
	class ComparatorImovelMicromedicao implements Comparator<ImovelMicromedicao>{
		public int compare(ImovelMicromedicao a, ImovelMicromedicao b) {
			
			int retorno = 0;
			Integer anoMesReferencia1 = a.getConsumoHistorico().getReferenciaFaturamento();
			Integer anoMesReferencia2 = b.getConsumoHistorico().getReferenciaFaturamento();

			if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
				retorno = -1;
			}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
				retorno = 1;
			}			
			return retorno;
		}
	}

}
