package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.mobile.execucaoOrdemServico.RelatorioConsultarArquivoTextoOrdensServicoSmartphone;
import gcom.relatorio.mobile.execucaoOrdemServico.RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
 * 
 * @author Jean Varela
 * @date 14/12/2015
 */

public class GerarRelatorioConsultarArquivoTextoOrdensServicoSmartphoneAction extends ExibidorProcessamentoTarefaRelatorio {

	private Fachada fachada;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
								HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		
		ActionForward retorno = null;
		fachada = Fachada.getInstancia();

		HttpSession sessao = request.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);

		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;

		Integer idComando = form.getIdComando();
		Integer idGrupoCobranca = form.getIdGrupoCobranca();
		Integer idEmpresa = form.getIdEmpresa();
		String mesAnoCronograma = "";
		String referenciaInicial = form.getDataCobrancaEventualInicial();
		String referenciaFinal = form.getDataCobrancaEventualFinal();
		Integer idTipoServico = form.getIdServicoTipo();
		Integer tipoFiltro = form.getTipoFiltro();
		Integer idSituacaoArquivo = Integer.valueOf(form.getSituacaoTextoLeitura());
		Integer idAgenteComercial = form.getIdAgenteComercial();
		String escolhaTipoRelatorio = request.getParameter("escolhaTipoRelatorio");

		List<ArquivoTxtOSCobrancaSmartphoneHelper> listaArquivoHelper = (List<ArquivoTxtOSCobrancaSmartphoneHelper>) sessao
																		.getAttribute("colecaoArquivoTextoOSCobrancaSmartphone");
		List<RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean> listaArquivosBean = null;
		if (listaArquivoHelper != null) {
			listaArquivosBean = new ArrayList<RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean>();
			for (ArquivoTxtOSCobrancaSmartphoneHelper helper : listaArquivoHelper) {
				listaArquivosBean.add(new RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean(helper));
			}
		}
		
		if (form.getMesAnoCronograma() != null){
			mesAnoCronograma = form.getMesAnoCronograma();
		}
		
		TarefaRelatorio relatorio = new RelatorioConsultarArquivoTextoOrdensServicoSmartphone(
										(Usuario) (request.getSession(false)).getAttribute("usuarioLogado"));

		relatorio.addParametro("grupoCobranca", getGrupoCobranca(idGrupoCobranca));
		relatorio.addParametro("empresa", getNomeEmpresa(idEmpresa));
		relatorio.addParametro("mesAnoCronograma", mesAnoCronograma);
		relatorio.addParametro("referenciaInicial", referenciaInicial);
		relatorio.addParametro("referenciaFinal", referenciaFinal);
		relatorio.addParametro("servicoTipo", getServicoTipo(idTipoServico));
		relatorio.addParametro("escolhaTipoRelatorio", escolhaTipoRelatorio);
		relatorio.addParametro("usuarioLogado", usuarioLogado);
		relatorio.addParametro("list", listaArquivosBean);
		relatorio.addParametro("tipoFiltro", tipoFiltro);
		relatorio.addParametro("idComando", idComando);
		relatorio.addParametro("nomeAgenteComercial", getNomeAgenteComercial(idAgenteComercial));
		relatorio.addParametro("situacaoArquivo", getSituacaoArquivo(idSituacaoArquivo));

		retorno = processarExibicaoRelatorio(relatorio, escolhaTipoRelatorio, request, response, mapping);
	
		return retorno;	
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * Método utilizado para recuperar o nome do agente comercial
	 * 
	 * @author Jean Varela
	 * @date 14/12/2015
	 */
	private String getNomeAgenteComercial(Integer idAgenteComercial) {
		String nomeAgenteComercial = new String();

		FiltroLeiturista filtro = new FiltroLeiturista();
		filtro.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID, idAgenteComercial));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
		Collection<Leiturista> colLeiturista = fachada.pesquisar(filtro, Leiturista.class);
		Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(colLeiturista);

		if (leiturista != null) {
			if (leiturista.getFuncionario().getNome() != null) {
				nomeAgenteComercial = leiturista.getFuncionario().getNome();
			} else {
				nomeAgenteComercial = leiturista.getCliente().getNome();
			}
		}
		return nomeAgenteComercial;
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * Método utilizado para recuperar a descrição da situação 
	 * 
	 * @author Jean Varela
	 * @date 14/12/2015
	 */
	private String getSituacaoArquivo(Integer idSituacaoArquivo) {
		String descricaoSituacao = new String();

		SituacaoTransmissaoLeitura situacaoTransmissao = fachada.pesquisar(SituacaoTransmissaoLeitura.class,
																		   idSituacaoArquivo);

		if (situacaoTransmissao != null) {
			descricaoSituacao = situacaoTransmissao.getDescricaoSituacao();
		}

		return descricaoSituacao;
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * Método utilizado para recuperar a descrição do grupo cobrança.
	 * 
	 * @author Jean Varela
	 * @date 14/12/2015
	 */
	private String getGrupoCobranca(Integer idGrupoCobranca) {
		String descricaoGrupoCobranca = "";

		if (Util.verificarIdNaoVazio(idGrupoCobranca)){
			CobrancaGrupo grupoCobranca = fachada.pesquisar(CobrancaGrupo.class, idGrupoCobranca);
	
			if (grupoCobranca != null) {
				descricaoGrupoCobranca = grupoCobranca.getDescricao();
			}
		}

		return descricaoGrupoCobranca;
	}

	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * Método utilizado para recuperar o nome da empresa selecionada
	 * 
	 * @author Jean Varela
	 * @date 14/12/2015
	 */
	private String getNomeEmpresa(Integer idEmpresa) {
		String nomeEmpresa = "";

		Empresa empresa = fachada.pesquisar(Empresa.class, idEmpresa);

		if (empresa != null) {
			nomeEmpresa = empresa.getDescricao();
		}

		return nomeEmpresa;
	}
	
	/**
	 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
	 * 
	 * Método utilizado para recuraperar a descrição do serviço tipo.
	 * 
	 * @author Jean Varela
	 * @date 14/12/2015
	 */
	private String getServicoTipo(Integer idServicoTipo){
		String descricaoServicoTipo = "";
		
		ServicoTipo servicoTipo = fachada.pesquisar(ServicoTipo.class, idServicoTipo);
		
		if (servicoTipo != null){
			descricaoServicoTipo = servicoTipo.getDescricao();
		}
		
		return descricaoServicoTipo;
	}
}
