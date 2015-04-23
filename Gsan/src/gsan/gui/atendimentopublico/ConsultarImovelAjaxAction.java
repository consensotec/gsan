package gsan.gui.atendimentopublico;

import gsan.cadastro.imovel.FiltroImovelInscricaoAlterada;
import gsan.cadastro.imovel.ImovelInscricaoAlterada;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0472] Consultar Imóvel
 * 
 * [FS0003] - Verificar exclusão do imóvel no encerramento do faturamento 
 * Autor: Arthur Carvalho
 * 
 * Data: 18/05/2009
 */
public class ConsultarImovelAjaxAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession sessao = httpServletRequest.getSession(false);

		PrintWriter print;
		String idImovel  = httpServletRequest.getParameter("idImovel");
		String desfazer = httpServletRequest.getParameter("desfazer");
		
		if ( desfazer != null && desfazer.equals("sim") ) {
			sessao.setAttribute("imovelAnteriorPesquisado", "");
		} else {
			boolean pesquisaNovoImovel = true;
			
			if ( sessao.getAttribute("imovelAnteriorPesquisado") != null &&  !sessao.getAttribute("imovelAnteriorPesquisado").equals("") ) {
				String idImovelAnteriorPesquisado = (String) sessao.getAttribute("imovelAnteriorPesquisado");
				if (idImovel.toString().equals(idImovelAnteriorPesquisado) ) {
					pesquisaNovoImovel = false;
				}
			}
			
			sessao.setAttribute("imovelAnteriorPesquisado", idImovel);
	
			if ( pesquisaNovoImovel ) {
			
				FiltroImovelInscricaoAlterada filtroAlterada = new FiltroImovelInscricaoAlterada();
				filtroAlterada.adicionarParametro( new ParametroSimples( FiltroImovelInscricaoAlterada.INDICADOR_ATUALIZADO , ConstantesSistema.NAO));
				filtroAlterada.adicionarParametro( new ParametroSimples( FiltroImovelInscricaoAlterada.INDICADOR_ALTERACAO_EXCLUIDA , ConstantesSistema.NAO));
				filtroAlterada.adicionarParametro( new ParametroNulo( FiltroImovelInscricaoAlterada.INDICADOR_ERRO_ALTERACAO ));
				filtroAlterada.adicionarParametro( new ParametroSimples( FiltroImovelInscricaoAlterada.INDICADOR_AUTORIZADO, ConstantesSistema.SIM));
				filtroAlterada.adicionarParametro( new ParametroSimples( FiltroImovelInscricaoAlterada.INDICADOR_IMOVEL_EXCLUIDO , ConstantesSistema.SIM));
				filtroAlterada.adicionarParametro( new ParametroSimples( FiltroImovelInscricaoAlterada.IMOVEL_ID , idImovel));
				
				Collection<ImovelInscricaoAlterada> colecaoImovelInscricaoAlterada = Fachada.getInstancia().pesquisar(filtroAlterada, ImovelInscricaoAlterada.class.getName());
				
				try {
					//[FS0003] - Verificar exclusão do imóvel no encerramento do faturamento 
					if ( colecaoImovelInscricaoAlterada != null && colecaoImovelInscricaoAlterada.size() > 0 ) {
						print = httpServletResponse.getWriter();
						print.print("O imóvel será excluído após o fechamento do faturamento.");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	  return null;
	}
}
