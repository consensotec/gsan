package gsan.gui.cobranca.cobrancaporresultado;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

/**
 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
 * 
 * @author Raimundo Martins
 * @date 13/12/2011
 * */
public class ExibirRetirarImoveisContasEmpresasCobrancaAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("retirarImoveisContasEmpresaCobranca");		
		RetirarImoveisContasEmpresaCobrancaActionForm form = (RetirarImoveisContasEmpresaCobrancaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		String pagina = httpServletRequest.getParameter("page.offset");
				
		if (httpServletRequest.getParameter("selecionarComandos") != null || pagina!=null) {
			if(pagina==null){
				pagina = "0";
			}			
			
			/*retorno = this.pesquisarComandosOS(httpServletRequest, form, fachada,
					sessao,pagina,retorno);*/
			this.pesquisarComandosOS(httpServletRequest, form, fachada,
				sessao,pagina,retorno);
		}
		if(httpServletRequest.getParameter("limpar") !=null){
			sessao.removeAttribute("colecaoRetirarImoveisContasEmpresaCobrancaHelper");
		}
		
		this.pesquisarCampoEnter(httpServletRequest, form, fachada);
		
		return retorno;
	}
	
	private void pesquisarCampoEnter(HttpServletRequest httpServletRequest, RetirarImoveisContasEmpresaCobrancaActionForm form, Fachada fachada) {
	
		String idEmpresa = form.getIdEmpresa();
		
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
	
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
	
			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());
	
			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId().toString());
				form.setNomeEmpresa(empresa.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			} else {
				form.setIdEmpresa("");
				form.setNomeEmpresa("EMPRESA INEXISTENTE");
	
				httpServletRequest.setAttribute("empresaInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idEmpresa");
			}
	
		} else {
			form.setNomeEmpresa("");
		}
	}
	
	private ActionForward pesquisarComandosOS(HttpServletRequest httpServletRequest,
			RetirarImoveisContasEmpresaCobrancaActionForm form,
			Fachada fachada, HttpSession sessao, String pagina, ActionForward retorno) {
		
		ActionForward retorno2 = new ActionForward();
		
		String idEmpresa = form.getIdEmpresa();
		String periodoExecucaoInicial = form.getPeriodoComandoInicial();
		String periodoExecucaoFinal = form.getPeriodoComandoFinal();
		
		Date execucaoInicial = null;
		Date execucaoFinal = null;
		// Pesquisa a empresa
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));

			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,Empresa.class.getName());

			if (Util.isVazioOrNulo(colecaoEmpresa)) {
				throw new ActionServletException("atencao.empresa.inexistente");
			}
		}else{
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Empresa");
		}
		if((periodoExecucaoInicial == null || periodoExecucaoInicial.equals("")) && 
				(periodoExecucaoFinal !=null && !periodoExecucaoFinal.equals(""))){
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Inicial");
			}
			if((periodoExecucaoFinal == null || periodoExecucaoFinal.equals("")) && 
					(periodoExecucaoInicial !=null && !periodoExecucaoInicial.equals(""))){
					throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "o Período Final");
			}
			
			if (periodoExecucaoFinal != null && !periodoExecucaoFinal.equals("")
					&& periodoExecucaoInicial != null && !periodoExecucaoInicial.equals("")) {
									
					Boolean b1 = Util.verificaSeDataValida(periodoExecucaoInicial, "dd/MM/yyyy");
					
					if(b1){
						execucaoInicial = Util.converteStringParaDate(periodoExecucaoInicial);
						
						b1 = Util.verificaSeDataValida(periodoExecucaoFinal, "dd/MM/yyyy");
						if(b1){
							execucaoFinal = Util.converteStringParaDate(periodoExecucaoFinal);
						}
						else{
							throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Final Válido");
						}
					}
					else{
						throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Um Período Inicial Válido");
					}

					if (execucaoInicial !=null && execucaoFinal!=null && execucaoInicial.compareTo(execucaoFinal) > 0) {
						throw new ActionServletException("atencao.data_final_periodo.anterior.data_inicial_periodo");
					}

				}
			int quantidadeRegistros = 100000;
			/*Integer totalRegistros = fachada.pesquisarDadosRetirarImoveisContasEmpresaCobrancaCount(Integer.parseInt(idEmpresa), execucaoInicial, execucaoFinal);
			retorno2 = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);*/
			
			/*Collection<RetirarImoveisContasEmpresaCobrancaHelper> helper = fachada
					.pesquisarDadosRetirarImoveisContasEmpresaCobranca(Integer.parseInt(idEmpresa), execucaoInicial, execucaoFinal,
						(Integer)httpServletRequest.getAttribute("numeroPaginasPesquisa"),quantidadeRegistros);*/
			Collection<RetirarImoveisContasEmpresaCobrancaHelper> helper = fachada
					.pesquisarDadosRetirarImoveisContasEmpresaCobranca(Integer.parseInt(idEmpresa), 
						execucaoInicial, execucaoFinal, 0,quantidadeRegistros);
			
			if(helper != null && !helper.isEmpty()){
				sessao.setAttribute("colecaoRetirarImoveisContasEmpresaCobrancaHelper",	helper);
			}else{
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		
		return retorno2;
	}
	
}
