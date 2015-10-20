package gcom.gui.atendimentopublico.hidrometro;

import gcom.cadastro.imovel.FiltroImovelHistoricoPerfil;
import gcom.cadastro.imovel.ImovelHistoricoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarEnquadramentoImovelPerfilAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirConsultarEnquadramentoImovelPerfilAction");
		
		Fachada fachada = Fachada.getInstancia();        
		
		HttpSession sessao = httpServletRequest.getSession(false);
        
        String idImovel = httpServletRequest.getParameter("idImovel");        
        
        if(idImovel != null){
        	
        	FiltroImovelHistoricoPerfil filtro = new FiltroImovelHistoricoPerfil(FiltroImovelHistoricoPerfil.ULTIMA_ALTERACAO);
        	filtro.adicionarParametro(new ParametroSimples(FiltroImovelHistoricoPerfil.IMOVEL_ID, new Integer(idImovel)));
        	filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelHistoricoPerfil.IMOVEL);
        	filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelHistoricoPerfil.IMOVEL_PERFIL_ANTERIOR);
        	filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelHistoricoPerfil.IMOVEL_PERFIL_POSTERIOR);
        	filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelHistoricoPerfil.PERFIL_ALTERACAO_TIPO);
        	filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelHistoricoPerfil.USUARIO);
        	filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelHistoricoPerfil.PERFIL_ALTERACAO_MOTIVO);
        	
        	Collection colecao = fachada.pesquisar(filtro, ImovelHistoricoPerfil.class.getName());
        	
        	if(!Util.isVazioOrNulo(colecao)){        		
        		sessao.setAttribute("imovelHistPerfils", colecao);
        		
        	}else{        		
        		sessao.removeAttribute("imovelHistPerfils");
        	}
        }		
		
		return retorno;
	}
}