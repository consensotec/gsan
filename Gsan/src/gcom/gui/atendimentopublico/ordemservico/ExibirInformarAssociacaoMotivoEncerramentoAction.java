package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarAssociacaoMotivoEncerramentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
			.findForward("exibirInformarAssociacaoMotivoEncerramento");

		InformarAssociacaoMotivoEncerramentoActionForm informarAssociacaoMotivoEncerramentoActionForm = (InformarAssociacaoMotivoEncerramentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		if (httpServletRequest.getParameter("abrirPopup") != null && 
				httpServletRequest.getParameter("abrirPopup").trim().equals("SIM") &&
				httpServletRequest.getParameter("idMotivoEncerramento") != null && 
				!httpServletRequest.getParameter("idMotivoEncerramento").trim().equals("")) {
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
					FiltroAtendimentoMotivoEncerramento.ID, httpServletRequest.getParameter("idMotivoEncerramento")));
			
			Collection colecao = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				
				AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecao);
	
				informarAssociacaoMotivoEncerramentoActionForm.setDescricaoMotivoEncerramento(atendimentoMotivoEncerramento.getDescricao());
				informarAssociacaoMotivoEncerramentoActionForm.setIdMotivoEncerramento(atendimentoMotivoEncerramento.getId().toString());
				
			} else {
				throw new ActionServletException(
						"atencao.motivo_encerramento.inexistente", null, "Motivo Encerramento");
			}
			
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.SIM));
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
			
			Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			
			if (colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()) {
				sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
			} else {
				sessao.removeAttribute("colecaoCobrancaAcao");
			}
						
			informarAssociacaoMotivoEncerramentoActionForm.setIndicadorGeraPagamento("");
			informarAssociacaoMotivoEncerramentoActionForm.setIndicadorGeraSucessor("");
			informarAssociacaoMotivoEncerramentoActionForm.setIndicadorExibeDocumento("");
		}
		if(informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento() == null || informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraPagamento().equals("")){
			informarAssociacaoMotivoEncerramentoActionForm.setIndicadorGeraPagamento("2");
		}
		
		if(informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor() == null || informarAssociacaoMotivoEncerramentoActionForm.getIndicadorGeraSucessor().equals("")){
			informarAssociacaoMotivoEncerramentoActionForm.setIndicadorGeraSucessor("2");
		}
		
		if(informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento() == null || informarAssociacaoMotivoEncerramentoActionForm.getIndicadorExibeDocumento().equals("")){
			informarAssociacaoMotivoEncerramentoActionForm.setIndicadorExibeDocumento("2");
		}
		
		sessao.removeAttribute("fecharPopup");
		
		return retorno;
		
		
	}

}
