package gsan.gui.faturamento;

import gsan.atendimentopublico.portal.AcessoLojaVirtual;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.faturamento.RelatorioDeclaracaoAnualQuitacaoDebitos;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Emitir2viaDeclaracaoAnualQuitacaoDebitosAction extends ExibidorProcessamentoTarefaRelatorio {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno para a tela de sucesso.
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Fachada fachada = Fachada.getInstancia();
						
		
		Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm form = 
			(Emitir2viaDeclaracaoAnualQuitacaoDebitosActionForm) actionForm;
		
		//caso o ano não tenha sido selecionado. Mostra tela de erro.
		if(form.getAno() == null || form.getAno().equals("-1")){
			if(form.getAnoGrupo() == null || form.getAnoGrupo().equals("")){				
				throw new ActionServletException("atencao.ano.nao_informado");
			}
		}
		
		//Verifica se o imovel tem quitação de contas caso ele esteja gerando pelo imovel
		if(form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("")){
			if(!fachada.verificarQuitacaoContas(new Integer(form.getMatriculaImovel()),new Integer(form.getAno()))){
				throw new ActionServletException("atencao.imovel_nao_possui_extrato", form.getMatriculaImovel().toString());
			}
		}
		
		SistemaParametro sistemaParametro
			= fachada.pesquisarParametrosDoSistema();
		
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		RelatorioDeclaracaoAnualQuitacaoDebitos relatorio = null;
		
		if(nomeEmpresa.equalsIgnoreCase("CAERN")){
				
			relatorio
				= new RelatorioDeclaracaoAnualQuitacaoDebitos(
						usuario,ConstantesRelatorios.RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS_CAERN);
			
		}else if(nomeEmpresa.equalsIgnoreCase("CAEMA")){
				
			relatorio
				= new RelatorioDeclaracaoAnualQuitacaoDebitos(
						usuario,ConstantesRelatorios.RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS_CAEMA);
			
		}else{
			
			relatorio
				= new RelatorioDeclaracaoAnualQuitacaoDebitos(
					usuario,ConstantesRelatorios.RELATORIO_DECLARACAO_ANUAL_QUITACAO_DEBITOS);
		}
		
		/*
		 * Se o grupo foi informado, usa o ano do grupo, caso contrario usa o ano do imovel
		 */
		String ano = "";
		if(form.getIdGrupo() != null && !form.getIdGrupo().equals("-1")){
			ano = form.getAnoGrupo().trim();
		}else{
			ano = form.getAno();			
		}
		
		relatorio.addParametro("matricula", form.getMatriculaImovel());
		relatorio.addParametro("ano", ano);
		relatorio.addParametro("opcaoTotalizacao", form.getOpcaoTotalizacao());
		
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		relatorio.addParametro("tipoFormatoRelatorio", new Integer(tipoRelatorio));
		relatorio.addParametro("idGrupo", form.getIdGrupo());
		relatorio.addParametro("permissaoEmitirPorGrupo", sessao.getAttribute("permissaoEmitirPorGrupo"));
		
		String origem = httpServletRequest.getParameter("origem");
		
		if(origem == null || !origem.equals("portal")){
			
			retorno = actionMapping.findForward("telaSucesso");
			
			httpServletRequest.setAttribute("telaSucessoRelatorio",true);
			
			processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);	

			// montando página de sucesso
			montarPaginaSucesso(httpServletRequest, 
				"Relatório Gerado com Sucesso.",
				"Voltar", 
				"exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction.do");
			
		}else{
			retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);	
			
		}
		
		//Contabiliza os servicos utilizados
		if(httpServletRequest.getParameter("lojaVirtual") != null){
			String ip = httpServletRequest.getRemoteAddr(); 
			Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.DECLARACAO_ANUAL_QUITACAO_DEBITO, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO); 
		}		
		
		return retorno;
	}
}
