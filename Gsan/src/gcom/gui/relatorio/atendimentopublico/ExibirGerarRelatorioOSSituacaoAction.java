package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaBoletimMedicao;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** [UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * Action responsável por carregar os dados para exibição
 * da tela relatorio_os_situacao_gerar.jsp
 * 
 * @author Jonathan Marcos
 * @since 28/07/2014
 */
public class ExibirGerarRelatorioOSSituacaoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioOSSituacao");
		GerarRelatorioOSSituacaoActionForm form = (GerarRelatorioOSSituacaoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Limpar Formulário
		String limparFormulario =  httpServletRequest.getParameter("limparFormulario");
		if(limparFormulario!=null){
			limparForm(form);
		}
		
		// Seta colecaoEmpresa na session
		if(httpServletRequest.getSession().getAttribute("colecaoEmpresa")==null){
			this.pesquisarEmpresa(sessao);
		}
		
		// Seta valor default como NAO
		if(form.getIndicadorOSSemGrupo() == null || form.getIndicadorOSSemGrupo().equals("")){
			form.setIndicadorOSSemGrupo(""+ConstantesSistema.NAO);
		}
		
		// Seta valor "todos" default a opção é TODAS
		if(form.getOpcaoOSCobranca()==null || form.getOpcaoOSCobranca().trim().compareTo("")==0){
			form.setOpcaoOSCobranca("todas");
		}
		
		String pesquisarGrupoCobrancaAssociadoEmpresa = httpServletRequest.getParameter("pesquisarGrupoCobrancaEmpresa");
		if(pesquisarGrupoCobrancaAssociadoEmpresa!=null){
			if(form.getIdEmpresa().compareTo("-1")!=0){
				Collection<CobrancaGrupo> colecaoCobrancaGrupo = pesquisarGrupoCobrancaAssociadoEmpresa(form);
				if(colecaoCobrancaGrupo!=null && colecaoCobrancaGrupo.size()!=0){
					sessao.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);
				}else{
					sessao.removeAttribute("colecaoCobrancaGrupo");
				}
				
				Collection<ContratoEmpresaServico> colecaoContratoEmpresaServ = pesquisarContratoCobrancaAssociadoEmpresa(form);
				if(colecaoContratoEmpresaServ!=null && colecaoContratoEmpresaServ.size()!=0){
					sessao.setAttribute("colecaoContratoEmpresaServ", colecaoContratoEmpresaServ);
				}else{
					sessao.removeAttribute("colecaoContratoEmpresaServ");
				}
			}
		}
		
		String situacaoOSPadrao = httpServletRequest.getParameter("situacaoOSPadrao");
		if(situacaoOSPadrao!=null){
			sessao.setAttribute("situacaoOSEncontradaAtendimentoSeletiva", true);
			sessao.removeAttribute("situacaoOSEncontrada");
			sessao.removeAttribute("boletimGerado");
			
			// Limpar os dados relacionados situacaoOSPadrao
			form.setIdEmpresa("");
			form.setIdGrupoCobranca("");
			form.setContratoCobranca("");
			String[] arrayOrigemOS = new String[2];
			if(form.getOrigemOs()!=null){
				for(int posicao = 0;posicao<form.getOrigemOs().length;posicao++){
					if(form.getOrigemOs()[posicao].compareTo("seletiva")==0){
						arrayOrigemOS[0] = "seletiva";
					}else if(form.getOrigemOs()[posicao].compareTo("atendimento")==0){
						arrayOrigemOS[1] = "atendimento";
					}
				}
				form.setOrigemOs(arrayOrigemOS);
			}else{
				form.setOrigemOs(null);
			}
		}
		
		String situacaoOSPadraoIndicadorOSSemGrupo = httpServletRequest.getParameter("situacaoOSPadraoIndicadorOSSemGrupo");
		if(situacaoOSPadraoIndicadorOSSemGrupo!=null){
			if(situacaoOSPadraoIndicadorOSSemGrupo.compareTo("sim")==0){
				httpServletRequest.setAttribute("situacaoOSPadraoIndicadorOSSemGrupo", "1");
			}else if(situacaoOSPadraoIndicadorOSSemGrupo.compareTo("nao")==0){
				httpServletRequest.setAttribute("situacaoOSPadraoIndicadorOSSemGrupo", "2");
			}
		}
		
		String pesquisarSituacaoOSAssociadaGrupo = httpServletRequest.getParameter("pesquisarSituacaoOSAssociadaGrupo");
		if(pesquisarSituacaoOSAssociadaGrupo!=null){
			if(form.getIdGrupoCobranca().compareTo("-1")!=0){
				if(Util.validarMesAno(form.getReferenciaCobranca())){
					boolean retornoPesquisarSituacaoOSAssociadaGrupo = pesquisarSituacaoOSAssociadaGrupo(form);
					if(retornoPesquisarSituacaoOSAssociadaGrupo){
						sessao.removeAttribute("situacaoOSEncontradaAtendimentoSeletiva");
						sessao.setAttribute("situacaoOSEncontrada", true);
						sessao.setAttribute("boletimGerado", true);
					}else{
						sessao.removeAttribute("situacaoOSEncontradaAtendimentoSeletiva");
						sessao.setAttribute("situacaoOSEncontrada", true);
						sessao.removeAttribute("boletimGerado");
					}
				}else{
					throw new ActionServletException("atencao.referencia.cobranca.invalida", "");
				}
			}else{
				sessao.setAttribute("situacaoOSEncontradaAtendimentoSeletiva", true);
				sessao.removeAttribute("situacaoOSEncontrada");
				sessao.removeAttribute("boletimGerado");
			}
		}
		
		String pesquisarSituacaoOSAssociadaContrato = httpServletRequest.getParameter("pesquisarSituacaoOSAssociadaContrato");
		if(pesquisarSituacaoOSAssociadaContrato!=null){
			if(form.getContratoCobranca().compareTo("-1")!=0){
				if(Util.validarMesAno(form.getReferenciaCobranca())){
					boolean retornoPesquisarSituacaoOSAssociadaContrato = pesquisarSituacaoOSAssociadaContrato(form);
					if(retornoPesquisarSituacaoOSAssociadaContrato){
						sessao.removeAttribute("situacaoOSEncontradaAtendimentoSeletiva");
						sessao.setAttribute("situacaoOSEncontrada", true);
						sessao.setAttribute("boletimGerado", true);
					}else{
						sessao.removeAttribute("situacaoOSEncontradaAtendimentoSeletiva");
						sessao.setAttribute("situacaoOSEncontrada", true);
						sessao.removeAttribute("boletimGerado");
					}
				}else{
					throw new ActionServletException("atencao.referencia.cobranca.invalida", "");
				}
			}else{
				sessao.setAttribute("situacaoOSEncontradaAtendimentoSeletiva", true);
				sessao.removeAttribute("situacaoOSEncontrada");
				sessao.removeAttribute("boletimGerado");
			}
		}
		
		
		String pesquisarEloPolo = httpServletRequest.getParameter("pesquisarEloPolo");
		if(pesquisarEloPolo!=null){
			pesquisarEloPolo(form);
		}
		
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade"); 
		if(pesquisarLocalidade!=null){
			pesquisarLocalidade(httpServletRequest, form);
		}
		
		String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
		if(pesquisarSetorComercial!=null){
			pesquisarSetorComercial(httpServletRequest, form);
		}
		
		String pesquisarTipoServico = httpServletRequest.getParameter("pesquisarTipoServico");
		if(pesquisarTipoServico!=null){
			pesquisarTipoServico(httpServletRequest, form);
		}
		
		verificaObjetosPesquisarPeloUsuario(httpServletRequest, form);
		
		// Pesquisar Gerencia Regional
		pesquisarGerenciaRegional(httpServletRequest);
		
		// Pesquisar Unidade de Negocio
		pesquisarUnidadeNegocio(httpServletRequest);
		
		// Pesquisar Motivo de Encerramento
		pesquisarMotivoEncerramento(httpServletRequest);
		
		String motivoEncerramentoSelecionado = httpServletRequest.getParameter("selecionouMotivoEncerramento");
		if(motivoEncerramentoSelecionado != null && motivoEncerramentoSelecionado.equalsIgnoreCase("sim")){
			Collection<String> idsMotivoEncerramento = new ArrayList<String>();
			
			String[] idsMotivoEncerramentoForm = form.getMotivoEncerramento();
			
			for (int i = 0; i < form.getMotivoEncerramento().length; i++){
				idsMotivoEncerramento.add(idsMotivoEncerramentoForm[i]);
			}
			
			FiltroFiscalizacaoSituacao filtroFiscalizacaoSit = new FiltroFiscalizacaoSituacao();
			filtroFiscalizacaoSit.adicionarParametro(new ParametroSimplesIn(FiltroFiscalizacaoSituacao.ATENDIMENTO_MOTIVO_ENCERRAMENTO_ID,
					idsMotivoEncerramento));
			filtroFiscalizacaoSit.setCampoOrderBy(FiltroFiscalizacaoSituacao.DESCRICAO);
			Collection<FiscalizacaoSituacao> colecaoFiscalizacaoSituacao = this.getFachada().pesquisar(filtroFiscalizacaoSit,
					FiscalizacaoSituacao.class.getName());
			httpServletRequest.setAttribute("colecaoFiscalizacaoSituacao", colecaoFiscalizacaoSituacao);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	private boolean pesquisarSituacaoOSAssociadaGrupo(GerarRelatorioOSSituacaoActionForm gerarRelatorioOSSituacaoActionForm){
		
		boolean retornoPesquisarSituacaoOSAssociadaGrupo = false;
		
		// Montagem do Filtro
		FiltroCobrancaBoletimMedicao filtroMed = new FiltroCobrancaBoletimMedicao();
		filtroMed.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.COBRANCA_GRUPO_ID, gerarRelatorioOSSituacaoActionForm.getIdGrupoCobranca()));
		filtroMed.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.ANO_MES_REFERENCIA,
				Util.formatarMesAnoComBarraParaAnoMes(gerarRelatorioOSSituacaoActionForm.getReferenciaCobranca())));
		Collection<CobrancaBoletimMedicao> medicoes = 
			Fachada.getInstancia().pesquisar(filtroMed, CobrancaBoletimMedicao.class.getName());
		
		// Caso o grupo de cobrança Mês/Ano já tenha gerado o boletim 
		if(!Util.isVazioOrNulo(medicoes)){
			retornoPesquisarSituacaoOSAssociadaGrupo  = true;
		}
		
		return retornoPesquisarSituacaoOSAssociadaGrupo;
	}
	
	@SuppressWarnings("unchecked")
	private boolean pesquisarSituacaoOSAssociadaContrato(GerarRelatorioOSSituacaoActionForm gerarRelatorioOSSituacaoActionForm){
		
		boolean retornoPesquisarSituacaoOSAssociadaContrato = false;
		
		// Montagem do Filtro
		FiltroCobrancaBoletimMedicao filtroMed = new FiltroCobrancaBoletimMedicao();
		filtroMed.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.CONTRATO_EMPRESA_SERVICO_ID, gerarRelatorioOSSituacaoActionForm.getContratoCobranca()));
		filtroMed.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.ANO_MES_REFERENCIA,
				Util.formatarMesAnoComBarraParaAnoMes(gerarRelatorioOSSituacaoActionForm.getReferenciaCobranca())));
		Collection<CobrancaBoletimMedicao> medicoes = 
			Fachada.getInstancia().pesquisar(filtroMed, CobrancaBoletimMedicao.class.getName());
		
		// Caso o grupo de cobrança Mês/Ano já tenha gerado o boletim 
		if(!Util.isVazioOrNulo(medicoes)){
			retornoPesquisarSituacaoOSAssociadaContrato  = true;
		}
		
		return retornoPesquisarSituacaoOSAssociadaContrato;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<CobrancaGrupo> pesquisarGrupoCobrancaAssociadoEmpresa(GerarRelatorioOSSituacaoActionForm gerarRelatorioOSSituacaoActionForm){
		
		// Montagem do  Filtro
		FiltroCobrancaGrupo filtro = new FiltroCobrancaGrupo();
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.EMPRESA_ID, gerarRelatorioOSSituacaoActionForm.getIdEmpresa()));
		filtro.setCampoOrderBy(FiltroCobrancaGrupo.ID);
		
		// Obter colecaoCobrancaGrupo Associada a Empresa
		Collection<CobrancaGrupo> colecaoCobrancaGrupo = this.getFachada().pesquisar(filtro, CobrancaGrupo.class.getName());
		
		return colecaoCobrancaGrupo;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<ContratoEmpresaServico> pesquisarContratoCobrancaAssociadoEmpresa(GerarRelatorioOSSituacaoActionForm gerarRelatorioOSSituacaoActionForm){
		// Montagem do Filtro
		FiltroContratoEmpresaServico filtroContrato = new FiltroContratoEmpresaServico();
		filtroContrato.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.EMPRESA_ID, gerarRelatorioOSSituacaoActionForm.getIdEmpresa()));
		filtroContrato.setCampoOrderBy(FiltroContratoEmpresaServico.ID);
		
		// Obter colecaoContratoEmpprsaServ Associado a Empresa
		Collection<ContratoEmpresaServico> colecaoContratoEmpresaServ = this.getFachada().pesquisar(filtroContrato, 
				ContratoEmpresaServico.class.getName());
		
		return colecaoContratoEmpresaServ;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarEmpresa(HttpSession sessao){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_EMPRESA_CONTRATADA_COBRANCA, new Short("1")));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	//Métodos para carregar os dados que o usuário apertou ENTER
	@SuppressWarnings("unchecked")
	private void pesquisarLocalidade(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form) {
		Fachada fachada = Fachada.getInstancia();

		this.limparSetorComercial(form);
		this.limparQuara(form);
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		Collection<Localidade> localidadePesquisada = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setIdLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarSetorComercial(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form){
		
		this.limparQuara(form);
		
		if (Util.verificarNaoVazio(form.getIdLocalidade())) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.limparListaParametros();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,new Integer(form.getIdSetorComercial())));
			
			Collection<SetorComercial> setorComerciais;
			setorComerciais = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (setorComerciais != null && !setorComerciais.isEmpty()) {
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
				form.setIdSetorComercial(Util.adicionarZerosEsquedaNumero(3, new Integer(setorComercial.getCodigo()).toString()));
				form.setDescricaoSetorComercial(setorComercial.getDescricao());
			}else {
				form.setIdSetorComercial("");
				form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarTipoServico(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form){
		
		if (Util.verificarNaoVazio(form.getIdTipoServico())){
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
			filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, form.getIdTipoServico()));
			
			Collection<ServicoTipo> tiposServicos;
			tiposServicos = this.getFachada().pesquisar(filtroTipoServico, ServicoTipo.class.getName());
			
			if (!Util.isVazioOrNulo(tiposServicos)) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(tiposServicos);
				form.setIdTipoServico(servicoTipo.getId().toString());
				form.setDescricaoTipoServico(servicoTipo.getDescricao());
			}else {
				form.setIdTipoServico("");
				form.setDescricaoTipoServico("Serviço Tipo Inexistente");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarEloPolo(GerarRelatorioOSSituacaoActionForm form) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, form.getIdEloPolo()));
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
		
		// Recupera Elo Pólo
		Collection<Localidade> colecaoEloPolo = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoEloPolo != null && !colecaoEloPolo.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoEloPolo);
			localidade = localidade.getLocalidade();
			form.setIdEloPolo(localidade.getId().toString());
			form.setDescricaoEloPolo(localidade.getDescricao());
		} else {
			form.setIdEloPolo(null);
			form.setDescricaoEloPolo("Localidade inexistente");
		}
	}
	
	/*Método responsável por manter as cores (vermelho - caso não existe, preto - caso exista
	 * dos objetos procurados pelo usuário (Localidade, Elo Polo e Seter Comercial)
	 */
	private void verificaObjetosPesquisarPeloUsuario(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form){
		if(Util.verificarNaoVazio(form.getDescricaoLocalidade()) && Util.verificarIdNaoVazio(form.getIdLocalidade())){
			request.setAttribute("localidadeEncontrada", true);
		}
		if(Util.verificarNaoVazio(form.getDescricaoEloPolo()) && Util.verificarIdNaoVazio(form.getIdEloPolo())){
			request.setAttribute("eloPoloEncontrado", true);
		}
		if(Util.verificarNaoVazio(form.getDescricaoSetorComercial()) && Util.verificarIdNaoVazio(form.getIdSetorComercial())){
			request.setAttribute("setorComercialEncontrado", true);
		}
		if(Util.verificarNaoVazio(form.getDescricaoTipoServico()) && Util.verificarIdNaoVazio(form.getIdTipoServico())){
			request.setAttribute("tipoServicoEncontrado", true);
		}
	}
	
	private void limparSetorComercial(GerarRelatorioOSSituacaoActionForm form){
		form.setIdSetorComercial("");
		form.setDescricaoSetorComercial("");
	}
	
	private void limparQuara(GerarRelatorioOSSituacaoActionForm form){
		form.setIdQuadra("");
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarGerenciaRegional(HttpServletRequest httpServletRequest){
		FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
		filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerencia.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> gerenciasRegionais = this.getFachada().pesquisar(filtroGerencia, GerenciaRegional.class.getName());
		httpServletRequest.setAttribute("colecaoGerenciaRegional",gerenciasRegionais);
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarUnidadeNegocio(HttpServletRequest httpServletRequest){
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarMotivoEncerramento(HttpServletRequest httpServletRequest){
		FiltroAtendimentoMotivoEncerramento filtroMotivoEnc = new FiltroAtendimentoMotivoEncerramento();
		filtroMotivoEnc.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroMotivoEnc.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEnc = this.getFachada().pesquisar(filtroMotivoEnc,
				AtendimentoMotivoEncerramento.class.getName());
		httpServletRequest.setAttribute("colecaoMotivoEncerramento", colecaoAtendimentoMotivoEnc);
	}
	
	private void limparForm(GerarRelatorioOSSituacaoActionForm gerarRelatorioOSSituacaoActionForm){
		gerarRelatorioOSSituacaoActionForm.setOrigemOs(null);
		gerarRelatorioOSSituacaoActionForm.setOpcaoRelatorio("");
		gerarRelatorioOSSituacaoActionForm.setPeriodoReferenciaInicial("");
		gerarRelatorioOSSituacaoActionForm.setPeriodoReferenciaFinal("");
		gerarRelatorioOSSituacaoActionForm.setReferenciaCobranca("");
		gerarRelatorioOSSituacaoActionForm.setIdEmpresa("");
		gerarRelatorioOSSituacaoActionForm.setIndicadorOSSemGrupo(""+ConstantesSistema.NAO);
		gerarRelatorioOSSituacaoActionForm.setIdGrupoCobranca("");
		gerarRelatorioOSSituacaoActionForm.setContratoCobranca("");
		gerarRelatorioOSSituacaoActionForm.setSituacaoOS("");
		gerarRelatorioOSSituacaoActionForm.setIdGerenciaRegional("");
		gerarRelatorioOSSituacaoActionForm.setIdUnidadeNegocio("");
		gerarRelatorioOSSituacaoActionForm.setIdEloPolo("");
		gerarRelatorioOSSituacaoActionForm.setDescricaoEloPolo("");
		gerarRelatorioOSSituacaoActionForm.setIdLocalidade("");
		gerarRelatorioOSSituacaoActionForm.setDescricaoLocalidade("");
		gerarRelatorioOSSituacaoActionForm.setIdSetorComercial("");
		gerarRelatorioOSSituacaoActionForm.setDescricaoSetorComercial("");
		gerarRelatorioOSSituacaoActionForm.setIdQuadra("");
		gerarRelatorioOSSituacaoActionForm.setOpcaoOSCobranca("todas");
		gerarRelatorioOSSituacaoActionForm.setIdTipoServico("");
		gerarRelatorioOSSituacaoActionForm.setDescricaoTipoServico("");
		gerarRelatorioOSSituacaoActionForm.setMotivoEncerramento(null);
		gerarRelatorioOSSituacaoActionForm.setImovelSuperior("");
	}
}