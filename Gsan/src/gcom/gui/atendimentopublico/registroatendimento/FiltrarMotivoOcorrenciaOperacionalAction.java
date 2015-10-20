package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroOcorrenciaOperacionalMotivo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarMotivoOcorrenciaOperacionalAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("exibirManterMotivoOcorrenciaOperacional");
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltrarMotivoOcorrenciaOperacionalActionForm form = 
				(FiltrarMotivoOcorrenciaOperacionalActionForm) actionForm;
		
		FiltroOcorrenciaOperacionalMotivo filtroOcorrenciaOperacionalMotivo = new FiltroOcorrenciaOperacionalMotivo();
		
		boolean informarUnicoCampo = false;
		
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		Integer tipoOcorrencia = form.getTipoOcorrencia();
		short indicadorUso = form.getIndicadorUso();
		String opcaoAtualizar = httpServletRequest.getParameter("opcaoAtualizar");
		if(descricao !=null && !descricao.trim().equalsIgnoreCase("")){
				informarUnicoCampo = true;
				filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.DESCRICAO, descricao.toUpperCase()));
		}
		
		if(descricaoAbreviada!=null && !descricaoAbreviada.trim().equalsIgnoreCase("")){
			informarUnicoCampo = true;
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.DESCRICAO_ABREVIADA, descricaoAbreviada.toUpperCase()));
		}
		
		if(tipoOcorrencia!=null && tipoOcorrencia!=-1){
			informarUnicoCampo = true;
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.OCORRENCIA_OPERACIONAL_TIPO_ID,tipoOcorrencia));
		}
		
		if(indicadorUso!=3){
			informarUnicoCampo = true;
			filtroOcorrenciaOperacionalMotivo.adicionarParametro(new ParametroSimples(FiltroOcorrenciaOperacionalMotivo.INDICADOR_USO, indicadorUso));
		}
		
		if (form.getIndicadorAtualizar()!= null && form.getIndicadorAtualizar().equalsIgnoreCase("1")) {
			if(opcaoAtualizar.equals("1")){
				form.setIndicadorAtualizar("1");
				httpServletRequest.setAttribute("atualizar", form.getIndicadorAtualizar());
			}else if(opcaoAtualizar.equals("2")){
				form.setIndicadorAtualizar("2");
				httpServletRequest.setAttribute("atualizar", form.getIndicadorAtualizar());
			}
		}
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!informarUnicoCampo) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		filtroOcorrenciaOperacionalMotivo.adicionarCaminhoParaCarregamentoEntidade(FiltroOcorrenciaOperacionalMotivo.OCORRENCIA_OPERACIONAL_TIPO);
		
		sessao.setAttribute("opcaoAtualizar", opcaoAtualizar);
		sessao.setAttribute("filtroOcorrenciaOperacionalMotivo", filtroOcorrenciaOperacionalMotivo);
		sessao.setAttribute("filtrarMotivoOcorrenciaOperacionalActionForm", form);
		httpServletRequest.setAttribute("filtroOcorrenciaOperacionalMotivo", filtroOcorrenciaOperacionalMotivo);
		
		return retorno;
	}
}
