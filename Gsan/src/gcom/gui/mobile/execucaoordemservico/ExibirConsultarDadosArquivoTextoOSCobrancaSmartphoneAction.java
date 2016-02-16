package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.DadosLeiturista;
import gcom.micromedicao.FiltroSituacaoTransmissaoLeitura;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
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
 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone (Novo)
 *
 * @author Jean Varela
 * @throws ErroRepositorioException 
 * @date   16/11/2015	
 */
public class ExibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction extends GcomAction {
	
	private Usuario usuario;
	private ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form;
	private Fachada fachada;
	private HttpSession sessao;
	
	private static final Integer TIPO_FILTRO_GRUPO_COBRANCA = 1;
	private static final Integer TIPO_FILTRO_COMANDO_EVENTUAL = 2;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
								 HttpServletRequest request, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirConsultarDadosArquivoTextoOSCobrancaSmartphone");
					
		sessao = request.getSession(false);
 		usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;
		fachada = Fachada.getInstancia();
		
		carregamentoInicial(request);
			
		String tipoPesquisa = request.getParameter("tipoPesquisa");
		
		if ("consultarLocalidade".equals(tipoPesquisa)) {
			sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
			consultarLocalidade();
			return retorno;
		}
		
		if ("consultarComandos".equals(tipoPesquisa)) {
			pesquisarComandosEventuais();
			form.setIdComando(null);
			sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
			return retorno;
		}
		
		if ("voltarAlerta".equals(tipoPesquisa)) {
			// Limpar dados
			sessao.removeAttribute("colecaoGrupoCobranca");
			sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
			sessao.removeAttribute("colecaoLocalidade");
			sessao.removeAttribute("colecaoComandosEventuais");
			form.setColecaoOS(null);
			form.setIdsRota(null);
			form.setIdGrupoCobranca(null);
			form.setMesAnoCronograma(null);
			form.setIdsLocalidade(null);
			form.setIdComando(null);
			form.setDataCobrancaEventualInicial(null);
			form.setDataCobrancaEventualFinal(null);

			return retorno;
		}
		
		if ("tipoServico".equals(tipoPesquisa)){
			sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
			sessao.removeAttribute("colecaoLocalidade");
			sessao.removeAttribute("colecaoComandosEventuais");
			sessao.removeAttribute("colecaoGrupoCobranca");
			form.setColecaoOS(null);
			form.setIdsRota(null);
			form.setIdGrupoCobranca(null);
			form.setMesAnoCronograma(null);
			form.setIdsLocalidade(null);
			form.setIdComando(null);
			form.setDataCobrancaEventualInicial(null);
			form.setDataCobrancaEventualFinal(null);
			form.setSituacaoTextoLeitura(null);
			form.setIdAgenteComercial(null);
		}
		
		if (TIPO_FILTRO_GRUPO_COBRANCA.equals(form.getTipoFiltro())) {
			pesquisarGrupoCobranca();
		}
		
		if ("selecionarEmpresa".equals(tipoPesquisa)) {
			sessao.setAttribute("colecaoAgenteComercial", getFachada().pesquisarColecaoAgenteComercial(form.getIdEmpresa()));

			// Limpar dados
			sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
			sessao.removeAttribute("colecaoLocalidade");
			sessao.removeAttribute("colecaoComandosEventuais");
			form.setColecaoOS(null);
			form.setIdsRota(null);
			form.setIdGrupoCobranca(null);
			form.setMesAnoCronograma(null);
			form.setIdsLocalidade(null);
			form.setIdComando(null);
			form.setDataCobrancaEventualInicial(null);
			form.setDataCobrancaEventualFinal(null);
			form.setSituacaoTextoLeitura(null);
			form.setIdAgenteComercial(null);
			
			return retorno;
		}
		
		if ("tipoFiltro".equals(tipoPesquisa)) {
			sessao.setAttribute("colecaoAgenteComercial", getFachada().pesquisarColecaoAgenteComercial(form.getIdEmpresa()));
			// Limpar dados
			sessao.removeAttribute("colecaoComandosEventuais");
			sessao.removeAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
			sessao.removeAttribute("colecaoLocalidade");
			form.setColecaoOS(null);
			form.setIdsRota(null);
			form.setIdGrupoCobranca(null);
			form.setMesAnoCronograma(null);
			form.setIdsLocalidade(null);
			form.setIdComando(null);
			form.setDataCobrancaEventualInicial(null);
			form.setDataCobrancaEventualFinal(null);
			form.setSituacaoTextoLeitura(null);
			
			return retorno;
		}
		
		if ("consultarGrupoCobranca".equals(tipoPesquisa)){		
	
			return retorno;
		}
		
		return retorno;
	}
	
	private void carregamentoInicial(HttpServletRequest request) {
		pesquisarEmpresas();
		pesquisarServicoTipo();
		pesquisarSituacaoArquivo();
		form.setIdTipoOS(OrdemServico.ORDEM_SERVICO_COBRANCA.toString());
		form.setDescricaoTipoOS(OrdemServico.DESCRICAO_ORDEM_SERVICO_COBRANCA);
	}
	
	/**
	 * Pesquisar situação de transmissão
	 * 
	 * [IT0009] Exibir Lista de Situações de Transmissão.
	 */
	private void pesquisarSituacaoArquivo() {
		
		FiltroSituacaoTransmissaoLeitura filtroSituacaoTransmissaoLeitura = new FiltroSituacaoTransmissaoLeitura();
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimples(FiltroSituacaoTransmissaoLeitura.INDICADOR_USO, ConstantesSistema.SIM));
		filtroSituacaoTransmissaoLeitura.adicionarParametro(new ParametroSimples(FiltroSituacaoTransmissaoLeitura.INDICADOR_COBRANCA, ConstantesSistema.SIM));
		Collection<?> colecaoSituacaoArquivoTexto = fachada.pesquisar(filtroSituacaoTransmissaoLeitura, SituacaoTransmissaoLeitura.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoSituacaoArquivoTexto)){
			sessao.setAttribute("colecaoSituacaoArquivoTexto", colecaoSituacaoArquivoTexto);
		}else{
			sessao.removeAttribute("colecaoSituacaoArquivoTexto");
		}
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

			if(Util.compararData(dataInicial, dataFinal) > 0) {
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}
			sessao.removeAttribute("colecaoComandosEventuais");
			if (Util.verificarIdNaoVazio(idServicoTipo) && dataInicial != null && dataFinal != null) {
				Collection<CobrancaAcaoAtividadeComando> colecaoComandosEventuais = this.getFachada()
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
	
	/**
	 * Pesquisar empresas 
	 * 
	 * [IT0002] Exibir Lista de Empresas.
	 */
	private void pesquisarEmpresas() {
		Collection colecaoEmpresa = (Collection) sessao.getAttribute("colecaoEmpresa");

		if (colecaoEmpresa == null && Util.verificarVazio(form.getDescricaoEmpresa())) {

			colecaoEmpresa = fachada.validarEmpresaPrincipal(usuario);

			if (colecaoEmpresa.size() == 1) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
				form.setIdEmpresa(empresa.getId());
				form.setDescricaoEmpresa(empresa.getDescricao());
			} else {
				sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
			}
		}
	}	

	/**
	 *Pesquisar Grupo Cobrança
	 *
	 *[IT0018] Exibir Lista de Grupos de Cobrança
	 */	
	private void pesquisarGrupoCobranca() {
		Collection colecaoGrupoCobranca = fachada.pesquisaGrupoCobrancaPorEmpresa(form.getIdEmpresa());
		
		if (Util.isVazioOrNulo(colecaoGrupoCobranca)) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
					"exibirConsultarDadosArquivoTextoOSCobrancaSmartphoneAction.do?tipoPesquisa=voltarAlerta",
					null, "Grupo de Cobrança");
		}
		sessao.setAttribute("colecaoGrupoCobranca", colecaoGrupoCobranca);

		sessao.setAttribute("colecaoGrupoCobranca", colecaoGrupoCobranca);	
	}
	
	/**
	 * Pesquisar a lista de localidades
	 * 
	 * [IT0005] Exibir Lista de Localidades
	 */
	private void consultarLocalidade() {
		Integer idGrupoCobranca = form.getIdGrupoCobranca();

		if (Util.verificarIdNaoVazio(idGrupoCobranca)) {
			Collection<?> colecaoLocalidade = fachada.pesquisarLocalidadeGrupoCobranca(idGrupoCobranca);

			if (Util.isVazioOrNulo(colecaoLocalidade)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Localidade");
			}

			sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
		} else {
			sessao.removeAttribute("colecaoLocalidade");
		}
	}
	
	/**
	 * Pesquisar lista de serviço tipo
	 * 
	 * [IT0007] Exibir Lista de Tipos de Serviço
	 */
	private void pesquisarServicoTipo() {
		// Pesquisar Tipos de Servico
		Collection colecaoServicoTipo = (Collection) sessao.getAttribute("colecaoServicoTipo");

		if (colecaoServicoTipo == null) {
			FiltroServicoTipo filtro = new FiltroServicoTipo(FiltroServicoTipo.DESCRICAO);
			filtro.setConsultaSemLimites(true);
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO,
														   ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_SERVICO_COBRANCA,
									                       ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoServicoTipo = fachada.pesquisar(filtro, ServicoTipo.class.getName());

			if (Util.isVazioOrNulo(colecaoServicoTipo)) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tipo de Serviço");
			}

			sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
		}
	}
	
	/**
	 * Procurar dados agente comercial
	 * 
	 *[IT0008] Exibir Lista de Agentes Comerciais. 
	 */	
	private void pesquisarAgenteComercial() {
		Collection<DadosLeiturista> colecaoLeiturista = fachada.pesquisarColecaoAgenteComercial(Integer.parseInt(form.getEmpresa()));
		sessao.setAttribute("colecaoLeiturista", colecaoLeiturista);
	}
	
}