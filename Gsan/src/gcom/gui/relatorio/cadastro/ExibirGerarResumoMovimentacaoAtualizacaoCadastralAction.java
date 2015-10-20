package gcom.gui.relatorio.cadastro;

import gcom.atualizacaocadastral.FiltroMensagemAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.MensagemAtualizacaoCadastralDM;
import gcom.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesNotIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarResumoMovimentacaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("resumoMovimentacaoAtualizacaoCadastral");

		GerarResumoMovimentacaoAtualizacaoCadastralActionForm form = (GerarResumoMovimentacaoAtualizacaoCadastralActionForm) actionForm;

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Verificamos se foi chamado do menu
		if (httpServletRequest.getParameter("menu") != null && ((String) httpServletRequest.getParameter("menu")).equals("sim")) {
			form.reset();
		}

		// Pesquisar Localidade
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("1")) {
			// Faz a consulta de Localidade
			this.pesquisarLocalidade(form, objetoConsulta);
		}

		// Pesquisar Setor Comercial
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("2")) {
			// Faz a consulta de Setor Comercial
			this.pesquisarSetorComercial(form, objetoConsulta);
		}

		// Pesquisar Quadra
		if (form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial())) {
			this.pesquisarQuadras(form, httpServletRequest);
		}

		this.pesquisarEmpresa(httpServletRequest);
		this.pesquisarGerenciaRegional(httpServletRequest);

		if (form.getGerenciaRegional() != null && !"".equals(form.getGerenciaRegional())) {
			this.pesquisarUnidadeNegocio(form, httpServletRequest);
		}

		this.pesquisarAnalista(httpServletRequest);
		this.pesquisarMensagemAtualizacaoCadastral(httpServletRequest);

		if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("-1")) {
			this.pesquisarCadastrador(httpServletRequest, form);
		} else {
			httpServletRequest.removeAttribute("colecaoCadastrador");
		}

		// Seta os request´s encontrados
		this.setaRequest(httpServletRequest, form);
		this.setaRelatorios(httpServletRequest);

		if (form != null && form.getIdsRegistro() != null) {
			httpServletRequest.setAttribute("IdsRegistroSelecionado", form.getIdsRegistro()[0]);
		}

		return retorno;
	}

	private void pesquisarMensagemAtualizacaoCadastral(HttpServletRequest request) {
		Collection<Integer> colecaoIdsMensagens = new ArrayList<Integer>();
		colecaoIdsMensagens.add(10);
		FiltroMensagemAtualizacaoCadastralDM filtroMensagemAtualizacaoCadastral = new FiltroMensagemAtualizacaoCadastralDM();
		filtroMensagemAtualizacaoCadastral.adicionarParametro(new ParametroSimplesNotIn(FiltroMensagemAtualizacaoCadastralDM.ID, colecaoIdsMensagens));
		filtroMensagemAtualizacaoCadastral.setCampoOrderBy(FiltroMensagemAtualizacaoCadastralDM.MENSAGEM);
		Collection<?> colecaoMensagemAtualizacaoCadastral = Fachada.getInstancia().pesquisar(filtroMensagemAtualizacaoCadastral, MensagemAtualizacaoCadastralDM.class.getName());

		if (colecaoMensagemAtualizacaoCadastral != null && !colecaoMensagemAtualizacaoCadastral.isEmpty()) {
			request.setAttribute("colecaoMensagem", colecaoMensagemAtualizacaoCadastral);
		} else {
			request.removeAttribute("colecaoMensagem");
		}
	}

	private void pesquisarAnalista(HttpServletRequest httpServletRequest) {
		FiltroUsuario filtroUsuario = new FiltroUsuario(FiltroUsuario.NOME_USUARIO);
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.USUARIO_SITUACAO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colUsuario = this.getFachada().pesquisar(filtroUsuario, Usuario.class.getName());

		if (!Util.isVazioOrNulo(colUsuario)) {
			httpServletRequest.setAttribute("colecaoUsuario", colUsuario);
		} else {
			httpServletRequest.removeAttribute("colecaoUsuario");
		}
	}

	private void pesquisarCadastrador(HttpServletRequest httpServletRequest, GerarResumoMovimentacaoAtualizacaoCadastralActionForm form) {
		Collection<Usuario> colCadastrador = this.getFachada().pesquisarUsuarioAtuCadastral(new Integer(form.getIdEmpresa()));

		if (!Util.isVazioOrNulo(colCadastrador)) {
			httpServletRequest.setAttribute("colecaoCadastrador", colCadastrador);
		} else {
			httpServletRequest.removeAttribute("colecaoCadastrador");
		}
	}

	private void pesquisarEmpresa(HttpServletRequest httpServletRequest) {
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_ATUALIZA_CADASTRO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (!Util.isVazioOrNulo(colEmpresa)) {
			httpServletRequest.setAttribute("colecaoEmpresa", colEmpresa);
		} else {
			httpServletRequest.removeAttribute("colecaoEmpresa");
		}
	}

	private void pesquisarLocalidade(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form, String objetoConsulta) {
		String local = form.getLocalidadeInicial();

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, local));

		if (form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("-1")) {
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, form.getGerenciaRegional()));
		}

		// Recupera Localidade
		Collection<?> colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

		if (!Util.isVazioOrNulo(colecaoLocalidade)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

			form.setLocalidadeInicial(localidade.getId().toString());
			form.setNomeLocalidadeInicial(localidade.getDescricao());
		} else {
			form.setLocalidadeInicial("");
			form.setNomeLocalidadeInicial("Localidade inexistente");
		}
	}

	private void pesquisarSetorComercial(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form, String objetoConsulta) {
		Object local = form.getLocalidadeInicial();
		Object setor = form.getSetorComercialInicial();

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, setor));

		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.LOCALIDADE, local));

		// Recupera Setor Comercial
		Collection<?> colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

		if (!Util.isVazioOrNulo(colecaoSetorComercial)) {

			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

			form.setSetorComercialInicial("" + setorComercial.getCodigo());
			form.setIdSetorComercial("" + setorComercial.getId());
			form.setNomeSetorComercialInicial(setorComercial.getDescricao());
		} else {
			form.setSetorComercialInicial("");
			form.setIdSetorComercial("");
			form.setNomeSetorComercialInicial("Setor Comercial inexistente");
		}
	}

	private void pesquisarQuadras(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		Integer setorComercial = new Integer(form.getIdSetorComercial());
	
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial));
        filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
        filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
        
		Collection<?> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
	
		if (quadras != null && !quadras.isEmpty()) {
			httpServletRequest.setAttribute("colecaoQuadra", quadras);
		} else {
			form.setQuadraSelecionados(null);
		}
	}

	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest) {
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<?> colecaoGerenciaRegional = this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		if (Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			httpServletRequest.removeAttribute("colecaoGerenciaRegional");
		} else {
			httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		}
	}

	private void pesquisarUnidadeNegocio(GerarResumoMovimentacaoAtualizacaoCadastralActionForm form, HttpServletRequest httpServletRequest) {
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, form.getGerenciaRegional()));
		//filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		if (Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			httpServletRequest.removeAttribute("colecaoUnidadeNegocio");
		} else {
			httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest, GerarResumoMovimentacaoAtualizacaoCadastralActionForm form) {
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && !form.getLocalidadeInicial().equals("") && form.getNomeLocalidadeInicial() != null && !form.getNomeLocalidadeInicial().equals("")) {
			httpServletRequest.setAttribute("localidadeInicialEncontrada", "true");
		}

		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("") && form.getNomeSetorComercialInicial() != null && !form.getNomeSetorComercialInicial().equals("")) {
			httpServletRequest.setAttribute("setorComercialInicialEncontrado", "true");
		}
	}

	private void setaRelatorios(HttpServletRequest httpServletRequest) {
		DadosResumoMovimentoAtualizacaoCadastralHelper helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		Collection<DadosResumoMovimentoAtualizacaoCadastralHelper> colecaoHelper = new ArrayList<DadosResumoMovimentoAtualizacaoCadastralHelper>();

		helper.setId("1");
		helper.setNomeRelatorio("Resumo da Posição da Atualização Cadastral");
		colecaoHelper.add(helper);

		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("2");
		helper.setNomeRelatorio("Resumo da Situação dos Imóveis por Cadastrador/Analista");
		colecaoHelper.add(helper);

		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("3");
		helper.setNomeRelatorio("Resumo das Mensagens Pendentes por Cadastrador");
		colecaoHelper.add(helper);

		helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		helper.setId("4");
		helper.setNomeRelatorio("Resumo do Quantitativo das Mensagens Pendentes");
		colecaoHelper.add(helper);

		// helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		// helper.setId("5");
		// helper.setNomeRelatorio("Relatório de Análise das Inconsistências");
		// colecaoHelper.add(helper);

		// helper = new DadosResumoMovimentoAtualizacaoCadastralHelper();
		// helper.setId("5");
		// helper.setNomeRelatorio("Resumo da Posição da Atualização Cadastral por Pacote");
		// colecaoHelper.add(helper);

		httpServletRequest.setAttribute("colecaoDadosResumoMovimentoAtualizacaoCadastralHelper", colecaoHelper);
	}
}
