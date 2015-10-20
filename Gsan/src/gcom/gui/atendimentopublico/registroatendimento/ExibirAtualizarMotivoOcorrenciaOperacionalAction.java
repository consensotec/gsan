package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalTipo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalMotivo;
import gcom.atendimentopublico.registroatendimento.OcorrenciaOperacionalTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarMotivoOcorrenciaOperacionalAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		

		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = actionMapping.findForward("atualizarMotivoOcorrenciaOperacional");
		AtualizarMotivoOcorrenciaOperacionalActionForm form = (AtualizarMotivoOcorrenciaOperacionalActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		String[] idMotivoOcorrenciaOperacional = new String[1];
		String idMotivoOcorrenciaOperacionalVindoTelaSucessoInserir = null;
		
		//colecao ocorrencia operacional tipo
		FiltroOcorrenciaOperacionalTipo filtroOcorrenciaOperacionalTipo = new FiltroOcorrenciaOperacionalTipo(FiltroOcorrenciaOperacionalTipo.DESCRICAO);
		Collection colecaoOcorrenciaOperacionalTipoGeral = fachada.pesquisar(filtroOcorrenciaOperacionalTipo, OcorrenciaOperacionalTipo.class.getName()); 
		httpServletRequest.setAttribute("colecaoOcorrenciaOperacionalTipoGeral", colecaoOcorrenciaOperacionalTipoGeral);
		
		if(sessao.getAttribute("objetoOcorrenciaOperacionalMotivo")!=null){
			OcorrenciaOperacionalMotivo operacionalMotivo = (OcorrenciaOperacionalMotivo) sessao.getAttribute("objetoOcorrenciaOperacionalMotivo"); 
			form.setDescricao(operacionalMotivo.getDescricao());
			form.setDescricaoAbreviada(operacionalMotivo.getDescricaoAbreviada());
			form.setIndicadorUso(operacionalMotivo.getIndicadorUso());
			form.setTipoOcorrencia(operacionalMotivo.getOcorrenciaOperacionalTipo().getId());
			httpServletRequest.setAttribute("idMotivoOcorrenciaOperacional", operacionalMotivo.getId());
		}else{
			if(httpServletRequest.getParameter("idMotivoOcorrencia")!=null){
				idMotivoOcorrenciaOperacionalVindoTelaSucessoInserir = httpServletRequest.getParameter("idMotivoOcorrencia");
				idMotivoOcorrenciaOperacional[0] = idMotivoOcorrenciaOperacionalVindoTelaSucessoInserir.toString();
			}
			
			
			
			//objeto com id passado pelo parametro
			FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = new FiltroOcorrenciaOperacionalMotivo();
			if(idMotivoOcorrenciaOperacionalVindoTelaSucessoInserir!=null){
				form.setIdMotivoOcorrenciaOperacional(idMotivoOcorrenciaOperacional);
				filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.ID, idMotivoOcorrenciaOperacional[0]));
			}else{
				filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.ID, form.getIdMotivoOcorrenciaOperacional()[0]));
			}
			Collection colecaoOcorrenciaMotivoOperacional = fachada.pesquisar(filtroOcorrenciaOperacionalMotivo, OcorrenciaOperacionalMotivo.class.getName());
			OcorrenciaOperacionalMotivo ocorrenciaOperacionalMotivo = (OcorrenciaOperacionalMotivo) colecaoOcorrenciaMotivoOperacional.iterator().next();
			
			//setando a ocorrencia operacional motivo
			form.setDescricao(ocorrenciaOperacionalMotivo.getDescricao());
			form.setDescricaoAbreviada(ocorrenciaOperacionalMotivo.getDescricaoAbreviada());
			form.setIndicadorUso(ocorrenciaOperacionalMotivo.getIndicadorUso());
			form.setTipoOcorrencia(ocorrenciaOperacionalMotivo.getOcorrenciaOperacionalTipo().getId());
			
			httpServletRequest.setAttribute("idMotivoOcorrenciaOperacional", ocorrenciaOperacionalMotivo.getId());
		}
		return retorno;
	}
}
