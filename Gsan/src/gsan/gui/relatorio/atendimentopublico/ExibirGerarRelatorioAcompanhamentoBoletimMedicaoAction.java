package gsan.gui.relatorio.atendimentopublico;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.ContratoEmpresaServico;
import gsan.micromedicao.FiltroContratoEmpresaServico;
import gsan.seguranca.acesso.FiltroOperacao;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
 * 
 * Action responsável por carregar os dados para exibição
 * da tela relatorio_acompanhamento_boletim_medicao_gerar.jsp
 *
 * @author Diogo Peixoto
 *
 */
public class ExibirGerarRelatorioAcompanhamentoBoletimMedicaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAcompanhamentoBoletimMedicao");
		GerarRelatorioAcompanhamentoBoletimMedicaoActionForm form = (GerarRelatorioAcompanhamentoBoletimMedicaoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
	
		String empresaSelecionada = httpServletRequest.getParameter("empresaSelecionada");
		/*Caso o submit do form tenha vindo através da seleção da empresa, não precisa
		 * carregar novamente todas as coleções, basta apenas carregar o contrato associado
		 * a empresa selecionada
		 */
		if(empresaSelecionada != null && empresaSelecionada.equalsIgnoreCase("sim")){
			String idEmpresa = form.getIdEmpresa();
			//Caso a empresa selecionada não seja a opção "Branco (value = -1)"
			if(!idEmpresa.equals("-1")){
				this.pesquisarContrato(idEmpresa, sessao, httpServletRequest);
			}
		}
		this.pesquisarEmpresa(sessao);
		
		return retorno;
	}
	
	private void pesquisarContrato(String idEmpresa, HttpSession sessao, HttpServletRequest request){
		FiltroContratoEmpresaServico filtroContrato = new FiltroContratoEmpresaServico();
		filtroContrato.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.EMPRESA, idEmpresa));
		filtroContrato.setCampoOrderBy(FiltroContratoEmpresaServico.DESCRICAO);
		
		Collection<ContratoEmpresaServico> colecaoContrato = this.getFachada().pesquisar(filtroContrato, ContratoEmpresaServico.class.getName());
		if (!Util.isVazioOrNulo(colecaoContrato)) {
			sessao.setAttribute("colecaoContrato", colecaoContrato);
			request.setAttribute("contratoEmpresa", true);
		} else {
			sessao.removeAttribute("colecaoCobrancaGrupo");
			request.setAttribute("contratoEmpresa", false);
			throw new ActionServletException("atencao.naocadastrado", "exibirGerarRelatorioAcompanhamentoBoletimMedicaoAction.do?voltarContrato=sim", 
					new ActionServletException("atencao.naocadastrado"), "Contrato Empresa Serviço");
		}
	}
	
	private void pesquisarEmpresa(HttpSession sessao){
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_EMPRESA_CONTRATADA_COBRANCA, new Short("1")));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
}
