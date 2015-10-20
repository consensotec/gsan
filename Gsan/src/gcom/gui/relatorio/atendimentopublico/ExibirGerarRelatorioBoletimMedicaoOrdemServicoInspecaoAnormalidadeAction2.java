package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

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
public class ExibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2 extends GcomAction{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirGerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeAction2");
		GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form = (GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2) formulario;
		HttpSession sessao = request.getSession();

		ComandoOrdemSeletiva comandoOS = (ComandoOrdemSeletiva) sessao.getAttribute("comandoOS");
		form.setNomeLocalidade("");
		
		//Verifica se houve mudanças nos filtros, para recarregar novas coleções.
		String mudouGerencia = request.getParameter("mudouGerencia");
		String mudouRegiao = request.getParameter("mudouRegiao");
		String mudouMicroregiao = request.getParameter("mudouMicroregiao");
		String desfazer = request.getParameter("desfazer");
		
		//Caso tenha mudado a gerência, carregar as unidades de negócio associadas à gerência selecionada
		if(Util.verificarNaoVazio(mudouGerencia) && mudouGerencia.equalsIgnoreCase("sim")){
			if(Util.verificarIdNaoVazio(form.getIdGerencia())){
				sessao.setAttribute("filtroGerenciaLocalidade", true);
			}else{
				if(!(Util.verificarIdNaoVazio(form.getIdLocalidade()) && Util.verificarNaoVazio(form.getNomeLocalidade()))){
					sessao.removeAttribute("filtroGerenciaLocalidade");
				}
			}
			this.pesquisarUnidadeNegocio(form, comandoOS, sessao, form.getIdGerencia());
		//Caso tenha mudado a região carregar as microregiões associadas à região selecionada
		}else if(Util.verificarNaoVazio(mudouRegiao) && mudouRegiao.equalsIgnoreCase("sim")){
			if(Util.verificarIdNaoVazio(form.getIdRegiao())){
				sessao.setAttribute("filtroRegiao", true);
			}else{
				sessao.removeAttribute("filtroRegiao");
			}
			sessao.removeAttribute("colecaoMunicipio");
			form.setIdMicroregiao("-1");
			this.pesquisarMicroregiao(sessao, form.getIdRegiao());
		//Caso tenha mudado a microregião carregar os municípios associados à região selecionada
		}else if(Util.verificarNaoVazio(mudouMicroregiao) && mudouMicroregiao.equalsIgnoreCase("sim")){
			this.pesquisarMunicipio(sessao, form.getIdMicroregiao());
			form.setIdMunicipio("-1");
		}else if(Util.verificarNaoVazio(desfazer) && desfazer.equalsIgnoreCase("sim")){
			if(comandoOS.getGerenciaRegional() == null){
				form.setIdGerencia("-1");
				form.setIdUnidadeNegocio("-1");
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				form.setIdRegiao("");
				sessao.removeAttribute("filtroRegiao");
				sessao.removeAttribute("filtroGerenciaLocalidade");
				sessao.removeAttribute("colecaoMicroregiao");
				sessao.removeAttribute("colecaoMunicipio");
				sessao.removeAttribute("possuiUnidade");
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale("pt", "br"));
				form.setPeriodoApuracao(sdf.format(new Date()));
				
				Collection<UnidadeNegocio> colecaoUnidadeNegocio = (Collection<UnidadeNegocio>) sessao.getAttribute("colecaoUnidade");
				colecaoUnidadeNegocio.clear();
				sessao.setAttribute("colecaoUnidade", colecaoUnidadeNegocio);
			}
			else{
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
			}
			
		}else{
			form.setDescricaoComando(comandoOS.getDescricaoComando());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", new Locale("pt", "br"));
			sessao.setAttribute("periodoGeracao", sdf.format(comandoOS.getDataGeracao()));
			//form.setPeriodoApuracao(sdf.format(comandoOS.getDataGeracao()));
			form.setPeriodoApuracao(sdf.format(new Date()));
			this.pesquisarGerencia(form, comandoOS, sessao);
			this.pesquisarLocalidade(form, request, sessao, comandoOS);	
			this.pesquisarRegiao(sessao);
		}
		
		return retorno;
	}
	
	/**
	 * Método auxiliar para pesquisar a localidade pelo ID (digitado pelo usuário)
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisarLocalidade(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form, HttpServletRequest request,
			HttpSession sessao, ComandoOrdemSeletiva comandoOS){

		String idLocaldiade = form.getIdLocalidade();

		//Verifica se o ID passado pelo usuário é válido para a pesquisa
		if (Util.verificarIdNaoVazio(idLocaldiade)){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocaldiade));
			Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			/*
			 * Verifica se a localidade foi encontrada. Caso tenha sido encontrada, o nome da localidade
			 * é configurado. Caso contrário, o nome de LOCALIDADE INEXISTENTE é configurado e o 
			 * atributo para exibir a descrição em vermelho é configurado.
			 */
			if (!Util.isVazioOrNulo(colecaoLocalidade)){
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				form.setIdLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());
				sessao.setAttribute("filtroGerenciaLocalidade", true);
			}else{
				form.setIdLocalidade("");
				form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
				request.setAttribute("localidadeInexistente", true);
				//Caso o único filtro tenha sido a localidade, desabilitar apenas o filtroGeranciaLocalidade
				if(!Util.verificarIdNaoVazio(form.getIdGerencia())){
					sessao.removeAttribute("filtroGerenciaLocalidade");
				}
			}
			request.setAttribute("nomeCampo", "idLocalidade");
		}else{
			form.setNomeLocalidade("");
		}
	}
	
	/**
	 * Método auxiliar para pesquisar todas as gerências cadastradas
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisarGerencia(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form,
			ComandoOrdemSeletiva comandoOS, HttpSession sessao){

		Collection<GerenciaRegional> colecaoGerencia = new ArrayList<GerenciaRegional>();
		String idGerencia = "";
		if(comandoOS.getGerenciaRegional() != null){
			idGerencia = comandoOS.getGerenciaRegional().getId().toString();
			colecaoGerencia.add(comandoOS.getGerenciaRegional());
			form.setIdGerencia(idGerencia);
			sessao.setAttribute("colecaoGerencia", colecaoGerencia);
			sessao.setAttribute("possuiGerencia", true);
			sessao.setAttribute("filtroGerenciaLocalidade", true);
		}else{
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			colecaoGerencia = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			sessao.setAttribute("colecaoGerencia", colecaoGerencia);
		}
		this.pesquisarUnidadeNegocio(form, comandoOS, sessao, form.getIdGerencia());
	}
	
	/**
	 * Método auxiliar para pesquisar a unidade de negócio
	 * 
	 * @author Diogo Peixoto
	 * @since 12/09/2011
	 * 
	 * @param form
	 * @param request
	 */
	public void pesquisarUnidadeNegocio(GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeActionForm2 form, 
			ComandoOrdemSeletiva comandoOS, HttpSession sessao, String idGerencia){
		Collection<UnidadeNegocio> colecaoUnidade = new ArrayList<UnidadeNegocio>();
		if(comandoOS.getUnidadeNegocio() != null){
			colecaoUnidade.add(comandoOS.getUnidadeNegocio());
			form.setIdUnidadeNegocio(comandoOS.getUnidadeNegocio().getId().toString());
			sessao.setAttribute("colecaoUnidade", colecaoUnidade);
			sessao.setAttribute("possuiUnidade", true);
		}else{
			if(Util.verificarIdNaoVazio(idGerencia)){
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, idGerencia));
				colecaoUnidade = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			}
			sessao.setAttribute("colecaoUnidade", colecaoUnidade);
		}
	}
	
	/**
	 * Método auxiliar para pesquisar TODAS as regiões
	 * 
	 * @author Diogo Peixoto
	 * @since 13/09/2011
	 * 
	 * @param request
	 */
	private void pesquisarRegiao(HttpSession sessao){
		Collection<Regiao> colecaoRegiao = null;
		FiltroRegiao filtroRegiao = new FiltroRegiao();
		filtroRegiao.setCampoOrderBy(FiltroRegiao.DESCRICAO);
		colecaoRegiao = Fachada.getInstancia().pesquisar(filtroRegiao, Regiao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoRegiao)){
			sessao.setAttribute("colecaoRegiao", colecaoRegiao);
		}
	}
	
	/**
	 * Método auxiliar para pesquisar TODAS as microregiões
	 * 
	 * @author Diogo Peixoto
	 * @since 13/09/2011
	 * 
	 * @param request
	 */
	public void pesquisarMicroregiao(HttpSession sessao, String idRegiao){
		Collection<Microrregiao> colecaoMicroregiao = null;
		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
		filtroMicrorregiao.adicionarParametro(new ParametroSimples(FiltroMicrorregiao.REGIAO_ID, idRegiao));
		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
		colecaoMicroregiao = Fachada.getInstancia().pesquisar(filtroMicrorregiao, Microrregiao.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMicroregiao)){
			sessao.setAttribute("colecaoMicroregiao", colecaoMicroregiao);
		}else{
			sessao.removeAttribute("colecaoMicroregiao");
			sessao.removeAttribute("colecaoMunicipio");
		}
	}
	
	/**
	 * Método auxiliar para pesquisar TODAS os municípios
	 * 
	 * @author Diogo Peixoto
	 * @since 13/09/2011
	 * 
	 * @param request
	 */
	public void pesquisarMunicipio(HttpSession sessao, String idMicroregiao){
		Collection<Municipio> colecaoMunicipio = null;
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.MICRORREGICAO_ID, idMicroregiao));
		filtroMunicipio.setCampoOrderBy(FiltroMunicipio.NOME);
		colecaoMunicipio = Fachada.getInstancia().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMunicipio)){
			sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
		}else{
			sessao.removeAttribute("colecaoMunicipio");
		}
	}
}