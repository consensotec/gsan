package gsan.gui.cadastro.imovel;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;
/**
 * [UC1577] - ReativarImóvelExcluido
 * 
 * @author Diogo Luiz
 * @date 16/12/2013
 *
 */
public class ExibirReativarImovelExcluidoAction extends GcomAction {	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirReativarImovelExcluido");
	
		Fachada fachada = Fachada.getInstancia();			
		
		ReativarImovelExcluidoActionForm reativarImovelExcluidoActionForm = 
				(ReativarImovelExcluidoActionForm) actionForm;		
		
		if (httpServletRequest.getParameter("objetoConsulta") != null ){
			
			Integer matricula = new Integer(reativarImovelExcluidoActionForm.getMatricula());			
			
			if (matricula != null){				
				
				String matriculaImovel = fachada.pesquisarInscricaoImovelExclusao(matricula);				
				
				String nomeCliente = fachada.pesquisarNomeClienteExcluido(matricula);
				
				if(matriculaImovel != null && !matriculaImovel.equals("")){
					String enderecoImovel = fachada.pesquisarEndereco(matricula);				
					
					Imovel imovel = null;				
					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, matricula));
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacaoAnterior");
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacaoAnterior");
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
					filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfilAnterior");
					Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
					
					if(!Util.isVazioOrNulo(colecaoImovel)){
						imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
					}					
					
					if(imovel.getLigacaoAguaSituacaoAnterior() != null && 
							imovel.getLigacaoEsgotoSituacaoAnterior() != null){
						httpServletRequest.setAttribute("imovel", imovel);		
						httpServletRequest.setAttribute("matriculaImovel", matriculaImovel);
						httpServletRequest.setAttribute("endereco", enderecoImovel);
						httpServletRequest.setAttribute("botaoReativar", true);
						
						if(nomeCliente != null && !nomeCliente.equals("")){
							httpServletRequest.setAttribute("cliente", nomeCliente);
							reativarImovelExcluidoActionForm.setNomeCliente(nomeCliente);
						}
					}else{
						httpServletRequest.setAttribute("imovelAtivo", true);												
					}				
					
				}else{					
					httpServletRequest.setAttribute("inexistente", 1);
					httpServletRequest.setAttribute("imovelAtivo", false);					
				}
			}
		}
		
		return retorno;
	}
}