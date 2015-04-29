package gsan.gui.gerencial.faturamento;

import gsan.fachada.Fachada;
import gsan.gerencial.bean.InformarDadosGeracaoRelatorioConsultaPeriodoHelper;
import gsan.gerencial.bean.ResumoFaturamentoSimulacaoDetalheHelper;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirResumoAnaliseFaturamentoPeriodoDetalheAction extends GcomAction {
	
	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping.findForward("exibirResumoAnaliseFaturamentoPeriodoDetalhe");

	        //Obt�m a inst�ncia da fachada
	        Fachada fachada = Fachada.getInstancia();

	        //Obt�m a sess�o
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        String tipo = "";
	        
	        if(httpServletRequest.getParameter("tipo")!=null){
	        	tipo = httpServletRequest.getParameter("tipo");
	        	
	        	if(tipo.equalsIgnoreCase("DEBITO")){
	        		sessao.setAttribute("tipo", "D�BITO");
	        	}else if(tipo.equalsIgnoreCase("CREDITO")){
	        		sessao.setAttribute("tipo", "CR�DITO");
	        	}else{
	        		sessao.removeAttribute("tipo");
	        	}
	        }
	         
	        InformarDadosGeracaoRelatorioConsultaPeriodoHelper informarDadosGeracaoRelatorioConsultaPeriodoHelper =
	        	(InformarDadosGeracaoRelatorioConsultaPeriodoHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaPeriodoHelper");
	        
	        informarDadosGeracaoRelatorioConsultaPeriodoHelper.setTipoDetalhe(tipo);
	        
	        Collection<Object[]> retornoConsulta = 
	        		fachada.consultarResumoAnaliseFaturamentoPeriodoDetalhe(informarDadosGeracaoRelatorioConsultaPeriodoHelper);
	        
	        /*InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
	        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
	        
	        informarDadosGeracaoRelatorioConsultaHelper.setTipoDetalhe(tipo);
	        
	        Collection<Object[]> retornoConsulta = 
	        	fachada.consultarResumoAnaliseFaturamentoDetalhe(informarDadosGeracaoRelatorioConsultaHelper);*/
	        
	        Collection<ResumoFaturamentoSimulacaoDetalheHelper> colecaoDetalhes = new ArrayList<ResumoFaturamentoSimulacaoDetalheHelper>(); 
	        
	        for (Object[] dados : retornoConsulta) {
	        	ResumoFaturamentoSimulacaoDetalheHelper  helper 
	        	 	= new ResumoFaturamentoSimulacaoDetalheHelper(
	        	 			(String)dados[0],
	        	 			Util.formatarMoedaReal((BigDecimal) dados[1]),
	        	 			"");
	        	
	        	colecaoDetalhes.add(helper);
			}
	        
	        sessao.setAttribute("colecaoDetalhes", colecaoDetalhes);
	        
	        return retorno;
	 }
}
