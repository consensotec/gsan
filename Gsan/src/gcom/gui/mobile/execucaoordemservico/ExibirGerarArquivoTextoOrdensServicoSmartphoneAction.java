package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.mobile.execucaoordemservico.bean.GerarArquivoTxtSmartphoneHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1497] - Gerar Arquivo Texto de Ordens de Serviço para Smartphone
 * 
 * @author André Miranda
 * @date 13/11/2015
 */
public class ExibirGerarArquivoTextoOrdensServicoSmartphoneAction extends GcomAction {
	private GerarArquivoTextoOrdensServicoSmartphoneActionForm form;
	private HttpSession sessao;

	private static final Integer TIPO_FILTRO_GRUPO_COBRANCA = 1;
	private static final Integer TIPO_FILTRO_COMANDO_EVENTUAL = 2;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirGerarArquivoTextoOrdensServicoSmartphone");
		form = (GerarArquivoTextoOrdensServicoSmartphoneActionForm) actionForm;
		sessao = request.getSession(false);

		// Verificar os tipos de pequisa
		String tipoPesquisa = request.getParameter("tipoPesquisa");

		// Carregamento inicial do formulario
		carregamentoInicial(request);

		if ("voltarAlerta".equals(tipoPesquisa)) {
			limparResultados();
			limparFiltros(true);
			return retorno;
		}

		// Pequisar Grupos de Cobrança
		if (TIPO_FILTRO_GRUPO_COBRANCA.equals(form.getTipoFiltro())) {
			pesquisarGrupoCobranca();
		}

		if ("selecionarEmpresa".equals(tipoPesquisa)) {
			limparResultados();
			limparFiltros(false);
			return retorno;
		}

		if ("consultarLocalidade".equals(tipoPesquisa)) {
			limparResultados();
			consultarLocalidade();
			return retorno;
		}

		if ("tipoFiltro".equals(tipoPesquisa)) {
			limparResultados();
			limparFiltros(false);
			return retorno;
		}

		if ("consultarComandos".equals(tipoPesquisa)) {
			limparResultados();
			pesquisarComandosEventuais();
			return retorno;
		}

		if ("selecionarComando".equals(tipoPesquisa)) {
			limparResultados();
			return retorno;
		}

		if ("selecionarServicoTipo".equals(tipoPesquisa)) {
			limparResultados();
			limparFiltros(false);
			return retorno;
		}

		if ("selecionar".equals(tipoPesquisa)) {
			selecionarRotas();
			return retorno;
		}

		if ("consultarQuadras".equals(tipoPesquisa)) {
			consultarQuadras();
			return retorno;
		}

		return retorno;
	}

	/**
	 * Limpa a tabela de rotas, OSs e o agente comercial
	 */
	private void limparResultados() {
		sessao.removeAttribute("mapaOS");
		sessao.removeAttribute("colecaoHelperAgrupado");
		form.setColecaoHelper(null);
		form.setIdsOS(null);
		form.setIdsRota(null);
		form.setIdAgenteComercial(null);
	}

	/**
	 * Limpa os campos dos filtros por grupo de cobrança e cobrança eventual
	 */
	private void limparFiltros(boolean limparGrupoCobranca) {
		sessao.removeAttribute("colecaoComandosEventuais");
		sessao.removeAttribute("colecaoLocalidade");
		if (limparGrupoCobranca)
			sessao.removeAttribute("colecaoGrupoCobranca");
		form.setIdGrupoCobranca(null);
		form.setMesAnoCronograma(null);
		form.setIdsLocalidade(null);
		form.setIdComando(null);
		form.setDataCobrancaEventualInicial(null);
		form.setDataCobrancaEventualFinal(null);
	}

	private void carregamentoInicial(HttpServletRequest request) {
		form.setIdTipoOS(OrdemServico.ORDEM_SERVICO_COBRANCA.toString());
		form.setDescricaoTipoOS(OrdemServico.DESCRICAO_ORDEM_SERVICO_COBRANCA);

		// Pesquisar Empresas
		Collection<Empresa> colecaoEmpresa = (Collection) sessao.getAttribute("colecaoEmpresa");

		if (colecaoEmpresa == null && Util.verificarVazio(form.getDescricaoEmpresa())) {
			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			colecaoEmpresa = getFachada().validarEmpresaPrincipal(usuario);

			if (colecaoEmpresa.size() == 1) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId());
				form.setDescricaoEmpresa(empresa.getDescricao());
			} else {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}

		// Pesquisar Tipos de Servico
		Collection<ServicoTipo> colecaoServicoTipo = (Collection) sessao.getAttribute("colecaoServicoTipo");

		if (colecaoServicoTipo == null) {
			FiltroServicoTipo filtro = new FiltroServicoTipo(FiltroServicoTipo.DESCRICAO);
			filtro.setConsultaSemLimites(true);
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_SERVICO_COBRANCA,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			// filtro.adicionarParametro(new
			// ParametroSimples(FiltroServicoTipo.INDICADOR_SERVICO_COBRANCA,
			// ConstantesSistema.SIM));

			colecaoServicoTipo = getFachada().pesquisar(filtro, ServicoTipo.class);

			if (Util.isVazioOrNulo(colecaoServicoTipo)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tipo de Serviço");
			}

			sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
		}

		// Pequisar Limite de OS
		String limiteOS = (String) sessao.getAttribute("limiteOS");
		if (limiteOS == null) {
			ParametroSistema parametro = getFachada()
					.pesquisarParametroSistema(ParametroSistema.NUMERO_LIMITE_OS_COBRANCA);
			limiteOS = parametro.getValorParametro();
		}

		form.setQtdMaxOS(limiteOS == null ? "" : limiteOS);
	}

	private void selecionarRotas() {
		Integer idGrupoCobranca = form.getIdGrupoCobranca();
		Integer idEmpresa = form.getIdEmpresa();
		Integer idTipoServico = form.getIdServicoTipo();
		Integer idComando = form.getIdComando();

		List<GerarArquivoTxtSmartphoneHelper> list = null;

		if (Util.verificarIdNaoVazio(idGrupoCobranca)) {
			Integer referencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoCronograma());
			Integer[] idsLocalidade = form.getIdsLocalidade();

			if (idsLocalidade != null && idsLocalidade.length == 1) {
				if (ConstantesSistema.INVALIDO_ID.equals(idsLocalidade[0])) {
					idsLocalidade = null;
				}
			}

			list = getFachada().consultarQuantidadeOrdemServicoCronograma(idGrupoCobranca, idEmpresa, referencia,
					idTipoServico, idsLocalidade);
		} else {
			list = getFachada().consultarQuantidadeOrdemServicoComando(idComando, idEmpresa, idTipoServico);
		}

		if (Util.isVazioOrNulo(list)) {
			throw new ActionServletException("atencao.nao_existe_dados_filtro");
		}

		limparResultados();
		sessao.setAttribute("colecaoHelperAgrupado", list);
		sessao.setAttribute("colecaoAgenteComercial", getFachada().pesquisarColecaoAgenteComercial(form.getIdEmpresa()));
	}

	private void consultarQuadras() {
		Integer[] idsRota = form.getIdsRota(); 
		Integer idEmpresa = form.getIdEmpresa();
		Integer idTipoServico = form.getIdServicoTipo();
		Integer idComando = form.getIdComando();

		Collection<GerarArquivoTxtSmartphoneHelper> colecaoHelper = null;

		if (Util.verificarIdNaoVazio(idComando)) {
			colecaoHelper = getFachada().consultarOrdemServicoComando(idsRota, idEmpresa, idTipoServico, idComando);
		} else {
			Integer referencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoCronograma());
			colecaoHelper = getFachada().consultarOrdemServicoCronograma(idsRota, idEmpresa, referencia, idTipoServico);
		}

		if (Util.isVazioOrNulo(colecaoHelper)) {
			throw new ActionServletException("atencao.nao_existe_dados_filtro");
		}

		// Colocar listas de O.S. identificadas pela chave idLocalidade-cdSetor-cdRota-nnQuadra
		Map<String, List<Integer>> mapaOS = (Map<String, List<Integer>>) sessao.getAttribute("mapaOS");
		if (mapaOS == null) mapaOS = new HashMap<String, List<Integer>>();

		mapaOS.clear();
		for (GerarArquivoTxtSmartphoneHelper helper : colecaoHelper) {
			mapaOS.put(helper.getKey(), helper.getListaOS());
		}
		sessao.setAttribute("mapaOS", mapaOS);

		form.setColecaoHelper(colecaoHelper);
		form.setIdsOS(null);
	}

	/**
	 * [IT0005] Exibir Lista de Localidades
	 */
	private void consultarLocalidade() {
		Integer idGrupoCobranca = form.getIdGrupoCobranca();

		if (Util.verificarIdNaoVazio(idGrupoCobranca)) {
			Collection<?> colecaoLocalidade = getFachada().pesquisarLocalidadeGrupoCobranca(idGrupoCobranca);

			if (Util.isVazioOrNulo(colecaoLocalidade)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Localidade");
			}

			sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
		} else {
			sessao.removeAttribute("colecaoLocalidade");
		}
	}

	/**
	 * [IT0016] Exibir Lista de Grupos de Cobrança
	 */
	private void pesquisarGrupoCobranca() {
		Collection<CobrancaGrupo> colecaoGrupoCobranca = getFachada()
				.pesquisaGrupoCobrancaPorEmpresa(form.getIdEmpresa());
		if (Util.isVazioOrNulo(colecaoGrupoCobranca)) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
					"exibirGerarArquivoTextoOrdensServicoSmartphoneAction.do?tipoPesquisa=voltarAlerta",
					null, "Grupo de Cobrança");
		}
		sessao.setAttribute("colecaoGrupoCobranca", colecaoGrupoCobranca);
	}

	/**
	 * [IT0017] Exibir Lista de Comandos Eventuais
	 */
	private void pesquisarComandosEventuais() {
		try {
			Integer idServicoTipo = form.getIdServicoTipo();
			Integer idEmpresa = form.getIdEmpresa();
			Date dataInicial = Util.converteStringParaDate(form.getDataCobrancaEventualInicial());
			Date dataFinal = Util.converteStringParaDate(form.getDataCobrancaEventualFinal());

			if (Util.compararData(dataInicial, dataFinal) > 0) {
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}

			if (Util.verificarIdNaoVazio(idServicoTipo) && dataInicial != null && dataFinal != null) {
				Collection<CobrancaAcaoAtividadeComando> colecaoComandosEventuais = getFachada()
						.pesquisarComandosEventuais(idServicoTipo, idEmpresa, dataInicial, dataFinal);

				if (Util.isVazioOrNulo(colecaoComandosEventuais)) {
					throw new ActionServletException("atencao.nao_existe_dados_filtro");
				}

				sessao.setAttribute("colecaoComandosEventuais", colecaoComandosEventuais);
			}
		} catch (IllegalArgumentException e) {
			sessao.removeAttribute("colecaoComandosEventuais");
		}
	}
}
