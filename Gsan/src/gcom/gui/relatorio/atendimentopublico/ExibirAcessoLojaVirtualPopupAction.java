package gcom.gui.relatorio.atendimentopublico;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper;
import gcom.util.Util;

public class ExibirAcessoLojaVirtualPopupAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirAcessoLojaVirtualPopupAction");

		FiltrarAcessoLojaVirtualActionForm form = (FiltrarAcessoLojaVirtualActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		Collection<AcessoLojaVirtualHelper> colecaoHelperPopup = null;
		
		Date periodoInicial = null;
		if ( form.getPeriodoAtendimentoInicial() != null && !form.getPeriodoAtendimentoInicial().equals("") ) {
			periodoInicial = Util.converteStringParaDate(form.getPeriodoAtendimentoInicial());
		}
		
		Date periodoFinal = null;
		if ( form.getPeriodoAtendimentoFinal() != null && !form.getPeriodoAtendimentoFinal().equals("") ) {
			periodoFinal = Util.converteStringParaDate(form.getPeriodoAtendimentoFinal());
		}
		
		String tipoAtendimento = null;
		if ( form.getTipoAtendimento() != null ) {
			tipoAtendimento = form.getTipoAtendimento();
		}
		
		String indicadorServicoExecutado = null;
		if ( form.getIndicadorServicoExecutado() != null ) {
			indicadorServicoExecutado = form.getIndicadorServicoExecutado();
		}
		
		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
		
		Integer totalRegistro = fachada.pesquisarQuantidadeAcessoLojaVirtualTipoAtendimento(periodoInicial, periodoFinal, tipoAtendimento, indicadorServicoExecutado);
		
		if(totalRegistro == null ||  totalRegistro <= 0){
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAcessoLojaVirtualAction.do?limparFormulario=sim", null, new String[0] );
		}
		
		// 2º Passo - Chamar a função de Paginação passando o total de registros
				retorno = this.controlarPaginacao(httpServletRequest, retorno,
						totalRegistro);

				// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
				// da pesquisa que está no request
				colecaoHelperPopup = fachada.pesquisarAcessoLojaVirtualTipoAtendimento(periodoInicial, periodoFinal, tipoAtendimento, 
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") , indicadorServicoExecutado);
			    
				
				httpServletRequest.setAttribute("colecaoHelperPopup", colecaoHelperPopup);
				
				AcessoLojaVirtualHelper acessoLojaVirtualHelper = new AcessoLojaVirtualHelper();
				acessoLojaVirtualHelper.setTipoAtendimento(tipoAtendimento);
				
				httpServletRequest.setAttribute("tipoAtendimento", acessoLojaVirtualHelper.getTipoAtendimento());
				
				return retorno;
	}	

}
