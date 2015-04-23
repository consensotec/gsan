package gsan.gui.relatorio.cobranca;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

public class ExibirFiltroSupressoesReligacoesReestabelecimentosAction  extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("filtroSupresoesReligacoesReestabelecimentos");
		
		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		//gerencia regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME_ABREVIADO);
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
		FiltroGerenciaRegional.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoGerenciaRegional = 
			fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		if (colecaoGerenciaRegional != null
				&& !colecaoGerenciaRegional.isEmpty()){

			httpServletRequest.setAttribute("colecaoGerenciaRegional",
					colecaoGerenciaRegional);
		}else{
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Gerência Regional");
		}
		
		
		//unidade de negócio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO);
		Collection colecaoUnidadeNegocio = (Collection) fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		
		if (colecaoUnidadeNegocio != null
				&& !colecaoUnidadeNegocio.isEmpty()) {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Unidade Negócio");
		}
		
		//localidade
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.INDICADORUSO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoLocalidade = (Collection) fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());
		
		if (colecaoLocalidade != null
				&& !colecaoLocalidade.isEmpty()) {
			httpServletRequest.setAttribute("colecaoLocalidade",
					colecaoLocalidade);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Localidade");
		}
		
		//Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		Collection colecaoEmpresa = (Collection) fachada.pesquisar(
				filtroEmpresa, Empresa.class.getName());
		
		if (colecaoEmpresa != null
				&& !colecaoEmpresa.isEmpty()) {
			httpServletRequest.setAttribute("colecaoEmpresa",
					colecaoEmpresa);
		}else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Empresa");
		}
		
		return retorno;
	}

}
