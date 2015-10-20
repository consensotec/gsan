package gcom.gui.relatorio.arrecadacao;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Gerar Relatório de Pagamento de Baixa Automática
 * [UC1518]
 * 
 * @author Diogo Luiz
 * @created 09/07/2013
 */
public class ExibirGerarRelatorioPagamentoBaixaAutomaticaAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioPagamentoBaixaAutomaticaAction");		
		
		GerarRelatorioPagamentoBaixaAutomaticaActionForm gerarRelatorioPagamentoBaixaAutomaticaActionForm = 
				(GerarRelatorioPagamentoBaixaAutomaticaActionForm) actionForm;
		
		String menu = httpServletRequest.getParameter("menu");
		if(menu != null && menu.equals("sim")){
			gerarRelatorioPagamentoBaixaAutomaticaActionForm.setTipo("sintetico");
			gerarRelatorioPagamentoBaixaAutomaticaActionForm.setBaixaAutomaticaPagamento("3");
		}
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String dataInicial = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getDataInicial();
		String dataFinal = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getDataFinal();
		
		if(dataFinal != null && !dataFinal.equals("")
				&& dataInicial != null && !dataInicial.equals("")){
			
			Date dtInicial = Util.converteStringParaDate(dataInicial);
			Date dtFinal = Util.converteStringParaDate(dataFinal);
			
			if(Util.compararData(dtInicial, dtFinal) == 1){				
				throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Invalida" );				
			}			
		}		
		
		String idMatricula = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getidMatricula();
		
		if(idMatricula != null && !idMatricula.trim().equals("")){
			Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(idMatricula));
			
			if(imovel != null ){
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setTipo("analitico");
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setidMatricula(imovel.getId().toString());
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setNomeMatricula(imovel.getInscricaoFormatada());
				
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIdGerenciaRegional("-1");
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIdUnidadeNegocio("-1");
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIdLocalidade("-1");
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIdSetorComercial("-1");
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIndicadorEstado(null);
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIndicadorGerenciaRegional(null);
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIndicadorUnidadeNegocio(null);
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIndicadorLocalidade(null);
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setIndicadorSetorComercial(null);
				
			}else{
				httpServletRequest.setAttribute("idMatriculaNaoEncontrado", true);
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setidMatricula("");
				gerarRelatorioPagamentoBaixaAutomaticaActionForm.setNomeMatricula("Imóvel Inexistente");
			}
		}		
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		
		Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, 
				GerenciaRegional.class.getName());
		
		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		
		Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroSetorComercial.setCampoOrderBy(FiltroSetorComercial.DESCRICAO);
		
		Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());
		
		sessao.setAttribute("colecaoSetorComercial", colecaoSetorComercial);		
		
		return retorno;	
	}	
}