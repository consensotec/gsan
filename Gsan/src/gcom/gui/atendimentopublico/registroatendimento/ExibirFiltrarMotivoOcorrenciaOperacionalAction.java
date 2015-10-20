package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarMotivoOcorrenciaOperacionalAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		ActionForward retorno = actionMapping.findForward("exibirFiltrarMotivoOcorrenciaOperacional");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltrarMotivoOcorrenciaOperacionalActionForm form = (FiltrarMotivoOcorrenciaOperacionalActionForm) actionForm;
		FiltroOcorrenciaOperacionalTipo filtroOcorrenciaOperacionalTipo = new FiltroOcorrenciaOperacionalTipo(FiltroOcorrenciaOperacionalTipo.DESCRICAO);
		Collection colecaoOcorrenciaOperacionalTipo = fachada.pesquisar(filtroOcorrenciaOperacionalTipo, OcorrenciaOperacionalTipo.class.getName()); 
		String opcaoAtualizar = httpServletRequest.getParameter("opcaoAtualizar");
		
		if(sessao.getAttribute("filtrarMotivoOcorrenciaOperacionalActionForm")!=null){
			FiltrarMotivoOcorrenciaOperacionalActionForm filtrarMotivoOcorrenciaOperacionalActionForm = 
					(FiltrarMotivoOcorrenciaOperacionalActionForm) sessao.getAttribute("filtrarMotivoOcorrenciaOperacionalActionForm");
			form.setDescricao(filtrarMotivoOcorrenciaOperacionalActionForm.getDescricao());
			form.setDescricaoAbreviada(filtrarMotivoOcorrenciaOperacionalActionForm.getDescricaoAbreviada());
			form.setTipoOcorrencia(filtrarMotivoOcorrenciaOperacionalActionForm.getTipoOcorrencia());
			form.setIndicadorAtualizar(filtrarMotivoOcorrenciaOperacionalActionForm.getIndicadorAtualizar());
			form.setIndicadorUso(filtrarMotivoOcorrenciaOperacionalActionForm.getIndicadorUso());
			if(opcaoAtualizar.equals("1")){
				form.setIndicadorAtualizar("1");
				httpServletRequest.setAttribute("atualizar", "1");
			}else if(opcaoAtualizar.equals("2")){
				form.setIndicadorAtualizar("2");
				httpServletRequest.setAttribute("atualizar", "2");
			}
			httpServletRequest.setAttribute("uso", "2");
		}else{
			httpServletRequest.setAttribute("atualizar", "1");
			httpServletRequest.setAttribute("uso", "1");
		}
		
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalTipo", colecaoOcorrenciaOperacionalTipo);
		
		return retorno;
	}
	
}
