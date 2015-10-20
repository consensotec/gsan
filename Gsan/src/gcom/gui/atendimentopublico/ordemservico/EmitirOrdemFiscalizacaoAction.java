package gcom.gui.atendimentopublico.ordemservico;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemFiscalizacaoConsumoInferior;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOrdemFiscalizacaoConsumoSuperior;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0983] Emitir Ordem de Fiscaliza��o
 * 
 * Este Caso Uso permite realizar a emiss�o de Documentos de Ordem de Fiscaliza��o
 * de forma individual para um determinado im�vel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class EmitirOrdemFiscalizacaoAction extends ExibidorProcessamentoTarefaRelatorio {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		EmitirOrdemFiscalizacaoForm form =
			(EmitirOrdemFiscalizacaoForm) actionForm;
		
		TarefaRelatorio relatorio = null;
		
		if( httpServletRequest.getParameter("idImovelTipo1") != null && httpServletRequest.getParameter("idImovelTipo1") != ""){
			relatorio
			= new RelatorioOrdemFiscalizacaoConsumoInferior(usuario);
		}else{
			relatorio
			= new RelatorioOrdemFiscalizacaoConsumoSuperior(usuario);
		
		}
		
		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		Integer[] idImovelOS  = (Integer[]) sessao.getAttribute("idImovelOS");
		
		relatorio.addParametro("form", form);
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
		relatorio.addParametro("idImovelOS", idImovelOS);
	
		
		retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		
		
		return retorno;
	}
}
