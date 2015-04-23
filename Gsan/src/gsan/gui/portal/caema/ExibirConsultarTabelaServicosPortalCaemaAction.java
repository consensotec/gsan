package gsan.gui.portal.caema;

import gsan.atendimentopublico.ordemservico.FiltroServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.Maior;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**[UC1194] Consultar Estrutura Tarifária Loja Virtual
 * 
 * Classe responsável por configurar as coleções da estrutura
 * tarifária para serem exibidas na tela 
 * informacoes_estrutura_tarifaria_portal_consultar.jsp
 * 
 * @author Arthur Carvalho
 * @since 20/01/2012
 *
 */
public class ExibirConsultarTabelaServicosPortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarTabelaServicosPortalCaemaAction");
		request.setAttribute("voltarInformacoes", true);
	
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo(FiltroServicoTipo.ID);
		filtroServicoTipo.adicionarParametro(new Maior(FiltroServicoTipo.VALORSERVICO, 0));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<ServicoTipo> colecaoServicoTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		Collection<ConsultarTabelaServicosPortalCaemaHelper> colecaoTabelaServicos = new ArrayList<ConsultarTabelaServicosPortalCaemaHelper>();
		
		if ( colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty() ) {
		
			Iterator iteratorServicoTipo = colecaoServicoTipo.iterator();
			while ( iteratorServicoTipo.hasNext() ) {
				ConsultarTabelaServicosPortalCaemaHelper helper = new ConsultarTabelaServicosPortalCaemaHelper();
				ServicoTipo servicoTipo = (ServicoTipo) iteratorServicoTipo.next();
				
				helper.setCodigo(servicoTipo.getId().toString() );
				helper.setDescricao(servicoTipo.getDescricao());
				helper.setValor(Util.formatarMoedaReal(servicoTipo.getValor()));
				colecaoTabelaServicos.add(helper);
			}
			request.setAttribute("colecaoServicoTipoHelper", colecaoTabelaServicos);
			
		}
		
		
		
		
		
		
		
		return retorno;
	}
}