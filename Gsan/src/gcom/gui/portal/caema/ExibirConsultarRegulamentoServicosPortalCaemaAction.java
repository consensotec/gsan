package gcom.gui.portal.caema;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Classe responsável por configurar as coleções de regulamentos servicos
 *  para serem exibidas na tela 
 * informacoes_regulamento_servicos_portal_caema.jsp
 * 
 * @author Nathalia Santos
 * @since 24/01/2012
 *
 */
public class ExibirConsultarRegulamentoServicosPortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarRegulamentoServicosPortalCaemaAction");
			
			request.setAttribute("voltarInformacoes", true);

			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			this.setarDownloadsLoja(sistemaParametro, request);
			
			String download = (String)request.getParameter("download");
			if(Util.verificarNaoVazio(download)){
				this.retornaArquivo(download, response, sistemaParametro, request);
			}
			return retorno;
		}
			
			
		private void setarDownloadsLoja(SistemaParametro sistemaParametro, HttpServletRequest request){
			if(sistemaParametro.getArquivoRegulamento() != null && sistemaParametro.getArquivoRegulamento().length != 0){
				request.setAttribute("regulamento", true);
				if(Util.verificarNaoVazio(sistemaParametro.getDescricaoRegulamento())){
					request.setAttribute("labelRegulamento", sistemaParametro.getDescricaoRegulamento());
				}
			}
		}
		private void retornaArquivo(String arquivo, HttpServletResponse response, SistemaParametro sistemaParametro, HttpServletRequest request){
			String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
			OutputStream out = null;
			byte[] file = null;
			
			if(arquivo.equalsIgnoreCase("regulamento")){
				file = sistemaParametro.getArquivoRegulamento();
			}
			try {
				response.setContentType(mimeType);
				out = response.getOutputStream();
				out.write(file);
				out.flush();
				out.close();
			} 
			catch (IOException e) {
				request.setAttribute("arquivoNaoEncontrado", true);
			}
		}
	}