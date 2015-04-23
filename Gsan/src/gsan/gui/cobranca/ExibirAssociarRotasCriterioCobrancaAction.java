package gsan.gui.cobranca;

import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cobranca.CobrancaAcao;
import gsan.cobranca.CobrancaCriterio;
import gsan.cobranca.CobrancaGrupo;
import gsan.cobranca.FiltroCobrancaAcao;
import gsan.cobranca.FiltroCobrancaCriterio;
import gsan.cobranca.FiltroCobrancaGrupo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * @author Flavio Leonardo, Raphael Rossiter
 * @date 24/01/2008 
 */
public class ExibirAssociarRotasCriterioCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		AssociarConjuntoRotasCriterioCobrancaActionForm form = 
			(AssociarConjuntoRotasCriterioCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		sessao.setAttribute("AssociarConjuntoRotasCriterioCobrancaActionForm", form);

	    retorno = actionMapping.findForward("exibirAssociarRotasCriterioCobranca");
		
		if(sessao.getAttribute("colecaoAcaoCobranca") == null){
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
			Collection colecaoAcaoCobranca = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			if(colecaoAcaoCobranca.isEmpty()){
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Ação de Cobrança");
			}else{
				sessao.setAttribute("colecaoAcaoCobranca", colecaoAcaoCobranca);
			}
			
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
			Collection colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			if(colecaoGrupoCobranca.isEmpty()){
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Grupo de Cobrança");
			}else{
				sessao.setAttribute("colecaoGrupoCobranca", colecaoGrupoCobranca);
			}
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			if(colecaoGerenciaRegional.isEmpty()){
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Gerência Regional");
			}else{
				sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
			}
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			if(colecaoUnidadeNegocio.isEmpty()){
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Unidade de Negócio");
			}else{
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			}
			
			sessao.setAttribute("colecaoRotaInicial", new ArrayList());
			sessao.setAttribute("colecaoRotaFinal", new ArrayList());
			
			/*
			 * Colocado por Raphael Rossiter em 23/01/2008
			 */
			httpServletRequest.setAttribute("nomeCampo","idAcaoCobranca");
		}
		
		if(form.getIdCriterioCobranca() != null && !form.getIdCriterioCobranca().equals("")
				&& (form.getDescricaoCriterioCobranca() == null || form.getDescricaoCriterioCobranca().equals(""))){
			
			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID, form.getIdCriterioCobranca()));
			Collection colecaoCriterio = fachada.pesquisar(filtroCobrancaCriterio, CobrancaCriterio.class.getName());
			
			if(colecaoCriterio.isEmpty()){
				form.setIdCriterioCobranca("");
				form.setDescricaoCriterioCobranca("Critério de Cobrança Inexistente");
				httpServletRequest.setAttribute("idCriterioCobrancaNaoEncontrado","exception");
				httpServletRequest.setAttribute("nomeCampo","idCriterioCobranca");
			}else{
				CobrancaCriterio cobrancaCriterio = (CobrancaCriterio)Util.retonarObjetoDeColecao(colecaoCriterio);
				 form.setIdCriterioCobranca(cobrancaCriterio.getId().toString());
				 form.setDescricaoCriterioCobranca(cobrancaCriterio.getDescricaoCobrancaCriterio());
				 httpServletRequest.setAttribute("nomeCampo","idGrupoCobranca");
			}
		}
		
		if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")
				&& (form.getDescricaoLocalidadeInicial() == null || form.getDescricaoLocalidadeInicial().equals(""))){
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeInicial()));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(colecaoLocalidade.isEmpty()){
				form.setIdLocalidadeInicial("");
				form.setDescricaoLocalidadeInicial("Localidade Inexistente");
				httpServletRequest.setAttribute("idLocalidadeInicialNaoEncontrado","exception");
				httpServletRequest.setAttribute("nomeCampo","idLocalidadeInicial");
			}else{
				Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidadeInicial(localidade.getId().toString());
				form.setDescricaoLocalidadeInicial(localidade.getDescricao());
				if(form.getIdLocalidadeFinal() == null || form.getIdLocalidadeFinal().equals("")){
					form.setIdLocalidadeFinal(localidade.getId().toString());
					form.setDescricaoLocalidadeFinal(localidade.getDescricao());
				}
				httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
				
			}
		}
		
		if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")
				&&(form.getDescricaoLocalidadeFinal() == null || form.getDescricaoLocalidadeFinal().equals(""))){
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeFinal()));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(colecaoLocalidade.isEmpty()){
				form.setIdLocalidadeFinal("");
				form.setDescricaoLocalidadeFinal("Localidade Inexistente");
				httpServletRequest.setAttribute("nomeCampo","idLocalidadeFinal");
				httpServletRequest.setAttribute("idLocalidadeFinalNaoEncontrado","exception");
			}else{
				Localidade localidade = (Localidade)Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidadeFinal(localidade.getId().toString());
				form.setDescricaoLocalidadeFinal(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
			}
		}
		
		if(form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().equals("")
				&&(form.getDescricaoSetorComercialInicial() == null || form.getDescricaoSetorComercialInicial().equals(""))){
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialInicial()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeInicial()));
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if(colecaoSetor.isEmpty()){
				form.setCodigoSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("Setor Comercial Inexistente");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialInicial");
				httpServletRequest.setAttribute("codigoSetorComercialInicialNaoEncontrado","exception");
			}else{
				SetorComercial setorComercial = (SetorComercial)Util.retonarObjetoDeColecao(colecaoSetor);
				form.setCodigoSetorComercialInicial(setorComercial.getCodigo() + "");
				form.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
				
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, setorComercial.getId()));
				Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
				sessao.setAttribute("colecaoRotaInicial", colecaoRota);
				
				if(form.getCodigoSetorComercialFinal() == null || form.getCodigoSetorComercialFinal().equals("")){
					form.setCodigoSetorComercialFinal(setorComercial.getCodigo() + "");
					form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
					sessao.setAttribute("colecaoRotaFinal", colecaoRota);
				}
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
		
		if(form.getCodigoSetorComercialFinal() != null && !form.getCodigoSetorComercialFinal().equals("")
				&& (form.getDescricaoSetorComercialFinal() == null || form.getDescricaoSetorComercialFinal().equals(""))){
			
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialFinal()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeFinal()));
			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if(colecaoSetor.isEmpty()){
				form.setCodigoSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("Setor Comercial Inexistente");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
				httpServletRequest.setAttribute("codigoSetorComercialFinalNaoEncontrado","exception");
			}else{
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetor);
				form.setCodigoSetorComercialFinal(setorComercial.getCodigo() + "");
				form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
				
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_ID, setorComercial.getId()));
				Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
				sessao.setAttribute("colecaoRotaFinal", colecaoRota);
				sessao.setAttribute("colecaoRotaInicial", colecaoRota);
			}
		}

		String atualizarQtd = httpServletRequest.getParameter("atualizarQtd");
		
		if(sessao.getAttribute("qtdRotas") != null && 
		   (atualizarQtd != null && !atualizarQtd.equals(""))){
			
			Integer qtdRotas = new Integer((String)sessao.getAttribute("qtdRotas"));
			Integer qtdRotasCom = new Integer((String)sessao.getAttribute("qtdRotasCom"));
			Integer qtdRotasSem = new Integer((String)sessao.getAttribute("qtdRotasSem"));
			form.setQtdRotasSelecionadas(qtdRotas+ "");
			form.setQtdRotasComCriterio(qtdRotasCom + "");
			form.setQtdRotasSemCriterio(qtdRotasSem + "");
		}
		else{
			form.setQtdRotasSelecionadas("");
			form.setQtdRotasComCriterio("");
			form.setQtdRotasSemCriterio("");
			
			sessao.removeAttribute("qtdRotas");
		}
		
		return retorno;
	}
}
