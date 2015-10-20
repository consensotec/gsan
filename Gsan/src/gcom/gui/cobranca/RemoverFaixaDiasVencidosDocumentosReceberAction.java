package gcom.gui.cobranca;

import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.filtro.ParametroSimplesIn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverFaixaDiasVencidosDocumentosReceberAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoFaixaDiasVencidosDocumentosReceberActionForm = 
				(ManutencaoRegistroActionForm) actionForm;
		
		// Obt�m os ids de remo��o
		String[] ids = manutencaoFaixaDiasVencidosDocumentosReceberActionForm.getIdRegistrosRemocao();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Obt�m a sess�o
		// HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// mensagem de erro quando o usu�rio tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		FiltroDocumentosReceberFaixaDiasVencidos filtroDocumentosReceberFaixaDiasVencidos = 
				new FiltroDocumentosReceberFaixaDiasVencidos();
		
		Collection idsDocumentos = new ArrayList(ids.length);
		
		for (int i = 0; i < ids.length; i++) {
			idsDocumentos.add(new Integer(ids[i]));
		}
		
		filtroDocumentosReceberFaixaDiasVencidos.adicionarParametro(new ParametroSimplesIn(FiltroDocumentosReceberFaixaDiasVencidos.ID,idsDocumentos));

		Collection projetosDocumentosReceberFaixaDiasVencidos = fachada.pesquisar(filtroDocumentosReceberFaixaDiasVencidos,
				DocumentosReceberFaixaDiasVencidos.class.getName());
		
		Iterator itera = projetosDocumentosReceberFaixaDiasVencidos.iterator();
		
		while(itera.hasNext()){
		
			DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos = (DocumentosReceberFaixaDiasVencidos) itera.next();
			
			fachada.remover(documentosReceberFaixaDiasVencidos);
			
		}

		Integer idQt = ids.length;
		
		//Monta a p�gina de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, idQt.toString()
					+ " Faixa de Dias Vencidos para Documentos a Receber removido(s) com sucesso.",
					"Realizar outra Manuten��o para Faixa de Dias Vencidos para Documentos a Receber",
					"exibirManterFaixaDiasVencidosDocumentosReceberAction.do?menu=sim");
		}
	
		return retorno;
	}	
}
