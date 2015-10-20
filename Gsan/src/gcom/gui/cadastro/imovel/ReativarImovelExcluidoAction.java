package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1577] - Reativar Imovel Excluido
 * 
 * @author Diogo Luiz
 * @date 16/12/2013
 *
 */
public class ReativarImovelExcluidoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		
		Fachada fachada = Fachada.getInstancia();		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		ReativarImovelExcluidoActionForm reativarImovelExcluidoActionForm = 
				(ReativarImovelExcluidoActionForm) actionForm;	
		
		String matricula = null;
		
		if(reativarImovelExcluidoActionForm.getMatricula() != null 
				&& !reativarImovelExcluidoActionForm.getMatricula().equals("")){
			
			if(!Util.validarStringNumerica(reativarImovelExcluidoActionForm.getMatricula())){
				throw new ActionServletException("atencao.campo.invalido", null, 
						"Matricula do Imovel");
			}else{
				matricula = reativarImovelExcluidoActionForm.getMatricula();
			}
		}else{
			throw new ActionServletException("atencao.campo.informado", null, 
				"Matri­cula do Imovel");
		}		
		
		Imovel imovelNovaSituacao = new Imovel();
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, matricula));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacaoAnterior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacaoAnterior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfilAnterior");		
		Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoImovel)){
			imovelNovaSituacao = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
		}				 
		
		fachada.atualizarImovelExcluido(imovelNovaSituacao, usuario);		
		
		montarPaginaSucesso(httpServletRequest, "Imovel " + matricula + 
			" Reativado com sucesso.",
			"Reativar outro Imovel",
			"exibirReativarImovelExcluido.do?menu=sim");
		
		return retorno;
	}	
}
