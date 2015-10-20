package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
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

public class EmitirOrdemServicoFiscalizacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		EmitirOrdemFiscalizacaoForm emitirOrdemdeFiscalizacaoForm =
			(EmitirOrdemFiscalizacaoForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		OrdemServico ordemServicoFiscalizacao = new OrdemServico();
		
		if(emitirOrdemdeFiscalizacaoForm.getMatriculaImovel()!=null
				&& !emitirOrdemdeFiscalizacaoForm.getMatriculaImovel().equals("")){
			
			FiltroImovel filtro = new FiltroImovel();
			
			filtro.adicionarParametro(
					new ParametroSimples(
							FiltroImovel.ID, 
							emitirOrdemdeFiscalizacaoForm.getMatriculaImovel()));
			
			Collection colecaoimovel = 
				fachada.pesquisar(filtro, Imovel.class.getName());
			
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoimovel);

			ordemServicoFiscalizacao.setImovel(imovel);
		}
			
	    Integer idOsGerada = fachada.gerarOrdemServicoFiscalizacao(ordemServicoFiscalizacao, usuario);
		
	    // RM5200
		Integer[] idImovelOS = new Integer[2];
		
		idImovelOS[0] = new Integer(emitirOrdemdeFiscalizacaoForm.getMatriculaImovel());
		idImovelOS[1] = idOsGerada;
		sessao.setAttribute("idImovelOS", idImovelOS);
		
		Integer idLigacao = null;
		if(emitirOrdemdeFiscalizacaoForm.getTipo() != null && !emitirOrdemdeFiscalizacaoForm.getTipo().equals("")){
			idLigacao = new Integer(emitirOrdemdeFiscalizacaoForm.getTipo());
		}
		
		//[SB0001] Emissão de relatório Tipo 1 					
		if(fachada.verificarEmissaoOrdemFiscalizacao(idOsGerada,emitirOrdemdeFiscalizacaoForm.getMatriculaImovel(), idLigacao))	{				
			sessao.removeAttribute("idImovelTipo2");
			sessao.setAttribute("idImovelTipo1", emitirOrdemdeFiscalizacaoForm.getMatriculaImovel());
			
		//[SB0002] Emissão de relatório Tipo 2 		
		}else{
			sessao.removeAttribute("idImovelTipo1");
			sessao.setAttribute("idImovelTipo2", emitirOrdemdeFiscalizacaoForm.getMatriculaImovel());
		}
	    
	    emitirOrdemdeFiscalizacaoForm.setIndicadorPermitirEmitir("sim");
	    emitirOrdemdeFiscalizacaoForm.setIndicadorPermitirGerarOs("nao");
	    emitirOrdemdeFiscalizacaoForm.setOrdemServico(idOsGerada.toString());
	    
		montarPaginaSucesso(httpServletRequest,
				"Ordem de Serviço de Fiscalização " +idOsGerada+ " gerada com sucesso.",
				"Emitir Ordem de Fiscalização",
				"exibirEmitirOrdemFiscalizacaoAction.do");
		
		return retorno;
	}
}
