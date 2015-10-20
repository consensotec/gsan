package gcom.gui.cadastro.projeto;

import gcom.cadastro.projeto.FiltroProjeto;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.projeto.RelatorioProjetoManter;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioProjetoManterAction extends
		ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarProjetoActionForm form = (FiltrarProjetoActionForm) actionForm;

		FiltroProjeto filtroProjeto = (FiltroProjeto) sessao
				.getAttribute("filtroProjeto");
		
		//cria uma inst�ncia da classe do relat�rio
		RelatorioProjetoManter relatorioProjetoManter = new RelatorioProjetoManter(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioProjetoManter.addParametro("filtroProjeto",filtroProjeto);
		
		if(form.getId()!=null && !form.getId().equals("")){
			relatorioProjetoManter.addParametro("id",form.getId());
		}
		if(form.getNome()!=null && !form.getNome().equals("")){
			relatorioProjetoManter.addParametro("nome",form.getNome());
		}
		if(form.getNomeAbreviado()!=null && !form.getNomeAbreviado().equals("")){
			relatorioProjetoManter.addParametro("nomeAbreviado",form.getNomeAbreviado());
		}
		if(form.getIdOrgaoFinanciador()!=null && !form.getIdOrgaoFinanciador().equals("")){
			relatorioProjetoManter.addParametro("idOrgao",form.getIdOrgaoFinanciador());
		}
		if(form.getNomeOrgaoFinanciador()!=null && !form.getNomeOrgaoFinanciador().equals("")){
			relatorioProjetoManter.addParametro("nomeOrgao",form.getNomeOrgaoFinanciador());
		}
		if(form.getSituacao()!=null && !form.getSituacao().equals("")){
			relatorioProjetoManter.addParametro("situacao",form.getSituacao());
		}

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioProjetoManter.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioProjetoManter,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	
	}

}
