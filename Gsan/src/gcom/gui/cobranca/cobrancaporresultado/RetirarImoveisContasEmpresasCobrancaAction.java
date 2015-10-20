package gcom.gui.cobranca.cobrancaporresultado;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;

/**
 * [UC 1256] Retirar Im�veis e Contas das Empresas de Cobran�a
 * 
 * @author Raimundo Martins
 * @date 13/12/2011
 * */
public class RetirarImoveisContasEmpresasCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		RetirarImoveisContasEmpresaCobrancaActionForm form = (RetirarImoveisContasEmpresaCobrancaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		Integer[] idsRegistros = form.getIdRegistros() ;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Map<String, Object> parametros = new HashMap<String,Object>();		
		parametros.put("idsRegistro", idsRegistros);
		
		
		fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
          		Processo.RETIRAR_IMOVEIS_CONTAS_EMPRESAS_COBRANCA, usuarioLogado);
		
				
		//montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"O Processamento dos Comandos foi Encaminhado para Batch", 
			"Voltar",
			"exibirRetirarImoveisContasEmpresasCobrancaAction.do?menu=sim");

	
		return retorno;
	}
}
