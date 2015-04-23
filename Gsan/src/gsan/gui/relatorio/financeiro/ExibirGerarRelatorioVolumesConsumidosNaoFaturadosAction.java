package gsan.gui.relatorio.financeiro;

import gsan.cadastro.geografico.FiltroMunicipio;
import gsan.cadastro.geografico.Municipio;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0822] Gerar Relatório do Valor Referente a Volumes Consumidos e Não Faturados
 * 
 * @see gsan.gui.relatorio.financeiro.GerarRelatorioVolumesConsumidosNaoFaturadosActionForm
 * @see gsan.gui.relatorio.financeiro.GerarRelatorioVolumesConsumidosNaoFaturadosAction
 * 
 * @author Victor Cisneiros
 * @date 09/07/2008
 */
public class ExibirGerarRelatorioVolumesConsumidosNaoFaturadosAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioVolumesConsumidosNaoFaturadosAction");
		Fachada fachada = Fachada.getInstancia();
		
		// ActionForm
		GerarRelatorioVolumesConsumidosNaoFaturadosActionForm form = 
			(GerarRelatorioVolumesConsumidosNaoFaturadosActionForm) actionForm;
		
		// Pesquisando GerencialRegional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());
		for (GerenciaRegional gerencia : colecaoGerenciaRegional) {
			gerencia.setNome(gerencia.getNomeAbreviado() + "-" + gerencia.getNome());
		}
		request.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		// Pesquisando UnidadeNegocio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		request.setAttribute("colecaoUnidadeNegocio", fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName()));
		
		String pesquisarLocalidade = request.getParameter("pesquisarLocalidade"); 
		// Pesquisando a Localidade pelo código que o usuário digitou
		if(pesquisarLocalidade != null && pesquisarLocalidade.equalsIgnoreCase("OK")){
			String codigoLocalidade = form.getCodigoLocalidade();
			if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
				pesquisarLocalidade(request, form);
			}
			// Localidade
			if (form.getCodigoLocalidade() != null && 
					!form.getCodigoLocalidade().equals("") &&
					form.getDescricaoLocalidade() != null && 
					!form.getDescricaoLocalidade().equals("")) {
				request.setAttribute("localidadeEncontrada", true);
			}
		}
		String pesquisarMunicipio = request.getParameter("pesquisarMunicipio");
		//Pesquisando o município pelo código que o usuário digitou
		if(pesquisarMunicipio != null && pesquisarMunicipio.equalsIgnoreCase("OK")){
			String codigoMunicipio = form.getCodigoMunicipio();
			if (codigoMunicipio != null && !codigoMunicipio.trim().equals("")) {
				pesquisarMunicipio(request, form);
			}
			// Localidade
			if (form.getCodigoMunicipio() != null && 
					!form.getCodigoMunicipio().equals("") &&
					form.getDescricaoMunicipio() != null && 
					!form.getDescricaoMunicipio().equals("")) {
				request.setAttribute("municipioEncontrado", true);
			}
		}
		
		return retorno;
	}
	
	private void pesquisarLocalidade(HttpServletRequest request, 
			GerarRelatorioVolumesConsumidosNaoFaturadosActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getCodigoLocalidade()));
		
		Collection pesquisa = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoLocalidade("" + localidade.getId());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
		}
	}

	private void pesquisarMunicipio(HttpServletRequest request, 
			GerarRelatorioVolumesConsumidosNaoFaturadosActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(
				FiltroMunicipio.ID, form.getCodigoMunicipio()));
		
		Collection pesquisa = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if (pesquisa != null && !pesquisa.isEmpty()) {
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(pesquisa);
			
			form.setCodigoMunicipio("" + municipio.getId());
			form.setDescricaoMunicipio(municipio.getNome());
		} else {
			form.setCodigoLocalidade("");
			form.setDescricaoLocalidade("Município Inexiste");
		}
	}
}
