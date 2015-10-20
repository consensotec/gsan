package gcom.gui.relatorio.atendimentopublico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioAcessoLojaVirtual;
import gcom.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;


/**
 * [UC1275] Gerar Relatório Quantidade de Acessos Loja Virtual
 * 
 * @author Flávio Ferreira
 * @date 01/10/2013
 * 
 */
public class GerarRelatorioAcessoLojaVirtualAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		// cria variavel de retorno
		ActionForward retorno = null;
		
		FiltrarAcessoLojaVirtualActionForm form = (FiltrarAcessoLojaVirtualActionForm) actionForm;
		
		RelatorioAcessoLojaVirtual relatorioAcessoLojaVirtual = new RelatorioAcessoLojaVirtual((Usuario) httpServletRequest.getSession().getAttribute("usuarioLogado"));
		
		Collection<AcessoLojaVirtualHelper> colecaoLojaVirtualHelper = (Collection<AcessoLojaVirtualHelper>) httpServletRequest.getSession().getAttribute("colecaoLojaVirtualHelper");
		
		String periodoInicial = (String) httpServletRequest.getSession().getAttribute("dataInicial");
		String periodoFinal = (String) httpServletRequest.getSession().getAttribute("dataFinal");
		String tipoAtendimento = (String) httpServletRequest.getSession().getAttribute("atendimento");
		
		relatorioAcessoLojaVirtual.addParametro("periodoInicial", periodoInicial);
		relatorioAcessoLojaVirtual.addParametro("periodoFinal", periodoFinal);
		relatorioAcessoLojaVirtual.addParametro("tipoAtendimento", tipoAtendimento);
		
		String indicadorServicoExecutado = "";
		
		switch(Integer.parseInt(httpServletRequest.getSession().getAttribute("servicoExecutado").toString())){
			
		case 1:
			indicadorServicoExecutado = "Sim";
			break;
			
		case 2:
			indicadorServicoExecutado = "Não";
			break;
			
		case 3:
			indicadorServicoExecutado = "Todos";
		}
		
		relatorioAcessoLojaVirtual.addParametro("indicadorServicoExecutado", indicadorServicoExecutado);
		relatorioAcessoLojaVirtual.addParametro("colecaoLojaVirtualHelper", colecaoLojaVirtualHelper);
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if(tipoRelatorio == null){
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioAcessoLojaVirtual.addParametro("tipoformatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		try{
			
			retorno = processarExibicaoRelatorio(relatorioAcessoLojaVirtual, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
			
		}catch(SistemaException ex){
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
			
		}catch(RelatorioVazioException ex){
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
		
		
	}

}
