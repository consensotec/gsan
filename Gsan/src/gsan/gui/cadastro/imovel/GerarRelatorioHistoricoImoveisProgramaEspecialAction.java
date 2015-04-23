package gsan.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.tarefa.TarefaRelatorio;

/**
 * [UC1373] Gerar Relatório Histórico Imóveis Programa Especial.
 * 
 * @author Jonathan Marcos
 * @date 06/05/2013
 *  
 */

public class GerarRelatorioHistoricoImoveisProgramaEspecialAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
		FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper filtro = new FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper();
		ActionForward retorno = null;
		
		String idImovel = httpServletRequest.getParameter("idImovel");
		
		if(idImovel!=null
				&& !idImovel.equals("")){
			
			filtro.setIdImovel(idImovel);
			
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		//httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		RelatorioHistoricoImoveisProgramaEspecial relatorio = 
				new RelatorioHistoricoImoveisProgramaEspecial(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("filtrarRelatorioHistoricoImoveisProgramaEspecialHelper", filtro);
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);

			return retorno;
		
	}
	
}
