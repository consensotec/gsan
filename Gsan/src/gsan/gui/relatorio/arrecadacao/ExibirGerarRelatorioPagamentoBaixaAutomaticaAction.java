package gsan.gui.relatorio.arrecadacao;

import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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