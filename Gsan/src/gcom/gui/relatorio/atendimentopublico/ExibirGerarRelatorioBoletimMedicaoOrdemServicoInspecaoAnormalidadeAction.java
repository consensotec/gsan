package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1221] – Gerar Boletim de Medição Ordem de Serviço Inspeção Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class ExibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction");
		GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm form = (GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm) formulario;
		HttpSession sessao = request.getSession(false);

		/*
		 * Caso o usuário tenha clicado no botão avançar, o sistema vai redicionar o usuário para a segunda tela,
		 * onde irá passar os parâmetros do comando.
		 */
		if(request.getParameter("avancar") != null && request.getParameter("avancar").equals("sim")){
			ComandoOrdemSeletiva comando = Fachada.getInstancia().pesquisarComandoOSSeletiva(Integer.valueOf(form.getIdComando()));
			sessao.setAttribute("comandoOS", comando);
			retorno = mapping.findForward("exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2");
		}else{
			this.pesquisarEmpresa(form, request, sessao);	
			
			//Caso o usuário tenha clicado no botão limpar, remover a coleção dos comandos da sessão.
			if (request.getParameter("limpar") != null && request.getParameter("limpar").equalsIgnoreCase("sim")) {
				sessao.removeAttribute("colecaoConsultarComandosOSHelper");
			}
			
			if (request.getParameter("selecionarComandos") != null) {
				this.pesquisarComandosOS(request, form, sessao);
			}
		}
		return retorno;
	}

	/**
	 * Método auxiliar para pesquisar a empresa pelo ID (digitado pelo usuário)
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisarEmpresa(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm form, HttpServletRequest request, HttpSession sessao){

		String idEmpresa = form.getIdEmpresa();

		//Verifica se o ID passado pelo usuário é válido para pesquisa.
		if (Util.verificarIdNaoVazio(idEmpresa)){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
			Collection<Empresa> colecaoEmpresa = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
			/*
			 * Verifica se a empresa foi encontrada. Caso tenha sido encontrada, o nome da empresa
			 * é configurado. Caso contrário, o nome de EMPRESA INEXISTENTE é configurado e o 
			 * atributo para exibir a descrição em vermelho é configurado.
			 */
			if (!Util.isVazioOrNulo(colecaoEmpresa)){
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
			}else{
				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");
				request.setAttribute("empresaInexistente", true);
			}
			request.setAttribute("nomeCampo", "idEmpresa");
		}else{
			form.setNomeEmpresa("");
		}
		sessao.removeAttribute("colecaoConsultarComandosOSHelper");
	}
	
	/**
	 * Método auxiliar responsável por pesquisar os Comandos das Ordens de Serviço
	 * 
	 * @param request
	 * @param form
	 * @param sessao
	 */
	private void pesquisarComandosOS(HttpServletRequest request, GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm form, 
			HttpSession sessao){

		Integer idEmpresa = null;
		Fachada fachada = this.getFachada();
		String periodoExecucaoInicial = form.getPeriodoExecucaoInicial();
		String periodoExecucaoFinal = form.getPeriodoExecucaoFinal();
		form.setIdComando("0");
		Date execucaoInicial = null;
		Date execucaoFinal = null;

		// Pesquisa a empresa
		if (Util.verificarIdNaoVazio(form.getIdEmpresa())){
			try{
				idEmpresa = Integer.valueOf(form.getIdEmpresa());
				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
				Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
				
				if (Util.isVazioOrNulo(colecaoEmpresa)) {
					throw new ActionServletException("atencao.empresa.inexistente");
				}
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.codigo_empresa_invalido");
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Empresa");
		}

		if (periodoExecucaoFinal != null && !periodoExecucaoFinal.equals("")
			&& periodoExecucaoInicial != null && !periodoExecucaoInicial.equals("")) {

			execucaoInicial = Util.converteStringParaDate(periodoExecucaoInicial);
			execucaoFinal = Util.converteStringParaDate(periodoExecucaoFinal);
			
			if(execucaoFinal != null && execucaoInicial != null){
				if (execucaoInicial.compareTo(execucaoFinal) > 0) {
					throw new ActionServletException("atencao.data_inicial_periodo_execucao.posterior.data_final_periodo_execucao");
				}
			}else{
				throw new ActionServletException("atencao.periodo_execucao_comando_invalido");	
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Período de Execução do Comando");
		}
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		int quantidadeRegistros = 10;

		Collection<ConsultarComandosOSSeletivaInspecaoAnormalidadeHelper> colecaoConsultarComandosOSHelper = fachada
		.pesquisarDadosComandoOSSeletivaResumido(idEmpresa, execucaoInicial, execucaoFinal,
				0,quantidadeRegistros, sistemaParametro.getQtdeDiasValidadeOSAnormalidadeFiscalizacao(), false);
		
		if(!Util.isVazioOrNulo(colecaoConsultarComandosOSHelper)){
			sessao.setAttribute("dataInicial",execucaoInicial);	
			sessao.setAttribute("dataFinal",execucaoFinal);	
			sessao.setAttribute("colecaoConsultarComandosOSHelper",	colecaoConsultarComandosOSHelper);
			request.setAttribute("nomeCampo", "colecaoConsultarComandosOSHelper");
		}else{
			sessao.removeAttribute("colecaoConsultarComandosOSHelper");
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction.do", null, new String[0]);
		}
	}
}