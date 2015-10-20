package gcom.gui.atendimentopublico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.faturamento.FiltroMovimentoContaPrefaturada;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

public class ExibirVisualizarCoordenadasImpressaoSimultaneaPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        
		ActionForward retorno = actionMapping.findForward("visualizarCoordenadas");
        
		String idImovel = httpServletRequest.getParameter("idImovel");
		
		Fachada fachada = this.getFachada();
		
		FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = 
				new FiltroMovimentoContaPrefaturada();
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(
				FiltroMovimentoContaPrefaturada.MATRICULA, idImovel));
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroNaoNulo(
				FiltroMovimentoContaPrefaturada.NUMERO_COORDENADA_X));
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroNaoNulo(
				FiltroMovimentoContaPrefaturada.NUMERO_COORDENADA_Y));
		
		filtroMovimentoContaPrefaturada.setCampoDistinct("(" +
				FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO+ ") , "+ 
				FiltroMovimentoContaPrefaturada.NUMERO_COORDENADA_X + "," +FiltroMovimentoContaPrefaturada.NUMERO_COORDENADA_Y);
		
		Collection<?> colRetorno = fachada.pesquisar(filtroMovimentoContaPrefaturada, MovimentoContaPrefaturada.class.getName());
		
		if(!Util.isVazioOrNulo(colRetorno)){
			Collection<MovimentoContaPrefaturada> colMovimento = new ArrayList<MovimentoContaPrefaturada>();
			MovimentoContaPrefaturada movimento = null;
			
			Iterator<?> it = colRetorno.iterator();
			while(it.hasNext()){
				Object[] array = (Object []) it.next();
				
				movimento = new MovimentoContaPrefaturada();
				
				movimento.setAnoMesReferenciaPreFaturamento((Integer) array[0]);
				movimento.setNumeroCoordenadaX((BigDecimal) array[1]);
				movimento.setNumeroCoordenadaY((BigDecimal) array[2]);
				
				colMovimento.add(movimento);
			}
			
			httpServletRequest.setAttribute("colecaoMovimento", colMovimento);
		}else{
			throw new ActionServletException("atencao.nao_existem_coordenadas");
		}
        
        return retorno;
	}
}
