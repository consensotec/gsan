package gcom.gui.micromedicao;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresaFoto;
import gcom.micromedicao.MovimentoRoteiroEmpresaFoto;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC 0629] Consultar Arquivo Texto Leitura
 * [SB 0008] Exibir Imagens de Anormalidade do Imóvel
 * 
 * @author Davi Menezes
 * @date 31/08/2012
 *
 */
public class ExibirFotoMovimentoRoteiroEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Fachada fachada = Fachada.getInstancia();
		
		String idFoto = httpServletRequest.getParameter("idFoto");
		OutputStream out = null;
		
		FiltroMovimentoRoteiroEmpresaFoto filtroMovimentoRoteiroFoto = new FiltroMovimentoRoteiroEmpresaFoto();
		filtroMovimentoRoteiroFoto.adicionarParametro(
				new ParametroSimples(FiltroMovimentoRoteiroEmpresaFoto.ID, idFoto));
		
		Collection colMovimentoRoteiroFoto = fachada.pesquisar(filtroMovimentoRoteiroFoto, MovimentoRoteiroEmpresaFoto.class.getName());
		
		MovimentoRoteiroEmpresaFoto movimentoRoteiroFoto = (MovimentoRoteiroEmpresaFoto) Util.retonarObjetoDeColecao(colMovimentoRoteiroFoto);
		
		String mimeType = "image/jpeg";
		
		if(movimentoRoteiroFoto.getFoto() == null || movimentoRoteiroFoto.getFoto().length == 0){
			throw new ActionServletException("atencao.imagem_nao_exibida");
		}
		
		try{
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			
			out.write(movimentoRoteiroFoto.getFoto());
			out.flush();
			out.close();
		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
		}
		
		return null;
	}
}
