package gsan.gui.relatorio.atendimentopublico;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

/**
 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
 * 
 * @author Flávio Ferreira
 * @since 27/09/2013
 */

public class ExibirFiltrarAcessoLojaVirtualAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		
		ActionForward retorno = actionMapping.findForward("exibirFiltrarAcessoLojaVirtualAction");
		
		FiltrarAcessoLojaVirtualActionForm form = (FiltrarAcessoLojaVirtualActionForm) actionForm;
		
		if(form.getIndicadorServicoExecutado() == null || form.getIndicadorServicoExecutado().equals("")){
			form.setIndicadorServicoExecutado(ConstantesSistema.TODOS.toString()); // todos
		}
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<AcessoLojaVirtualHelper> colecaoLojaVirtualHelper = null;
		
		if(request.getParameter("limparFormulario") != null && request.getParameter("limparFormulario").equals("sim")){
			form.setPeriodoAtendimentoInicial("");
			form.setPeriodoAtendimentoFinal("");
			form.setTipoAtendimento("-1");
			form.setIndicadorServicoExecutado(ConstantesSistema.TODOS.toString());
			
			// limpar a paginação
			retorno = this.controlarPaginacao(request, retorno, 0);
		}
		
		Date periodoInicial = null;
		if(form.getPeriodoAtendimentoInicial() != null && !form.getPeriodoAtendimentoInicial().equals("")){
			periodoInicial = Util.converteStringParaDate(form.getPeriodoAtendimentoInicial());
		}
		
		Date periodoFinal = null;
		if(form.getPeriodoAtendimentoFinal() != null  && !form.getPeriodoAtendimentoFinal().equals("")){
			periodoFinal = Util.converteStringParaDate(form.getPeriodoAtendimentoFinal());
		}
		
		if(periodoInicial != null && periodoFinal == null){
			throw new ActionServletException("atencao.campo.informado", "Periodo de Atendimento Final");
			
		}else if(periodoFinal != null && periodoInicial == null){
			throw new ActionServletException("atencao.campo.informado", "Periodo de Atendimento Inicial"); 
			
		}else if(periodoInicial != null && periodoFinal != null && Util.compararData(periodoInicial, periodoFinal) > 0){
			throw new ActionServletException("atencao.periodo_atendimento_final_menor_inicial"); 
		}
		
		String tipoAtendimento = null;
		if(form.getTipoAtendimento() != null && !form.getTipoAtendimento().equals("")){
			tipoAtendimento = form.getTipoAtendimento();
		}
		
		String indicadorServicoExecutado = null;
		if(form.getIndicadorServicoExecutado() != null && !form.getIndicadorServicoExecutado().equals("")){
			indicadorServicoExecutado = form.getIndicadorServicoExecutado();
		}
		
		
		
		if(request.getParameter("acao") != null && request.getParameter("acao").equals("pesquisa")){
			
			colecaoLojaVirtualHelper = fachada.pesquisarAcessoLojaVirtual(periodoInicial, periodoFinal, tipoAtendimento, indicadorServicoExecutado);
			
			String dataInicial = form.getPeriodoAtendimentoInicial();
			String dataFinal = form.getPeriodoAtendimentoFinal();
			String atendimento = form.getTipoAtendimento();
			String servicoExecutado = form.getIndicadorServicoExecutado();
			
			if(colecaoLojaVirtualHelper.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAcessoLojaVirtualAction.do?limparFormulario=sim" , null, new String[0]);
			}
			
			request.setAttribute("colecaoLojaVirtualHelper", colecaoLojaVirtualHelper);
			request.getSession().setAttribute("colecaoLojaVirtualHelper", colecaoLojaVirtualHelper);
			
			request.getSession().setAttribute("dataInicial", dataInicial);
			request.getSession().setAttribute("dataFinal", dataFinal);
			request.getSession().setAttribute("atendimento", atendimento);
			request.getSession().setAttribute("servicoExecutado", servicoExecutado);
		}
		
		return retorno;
			
	}

}
