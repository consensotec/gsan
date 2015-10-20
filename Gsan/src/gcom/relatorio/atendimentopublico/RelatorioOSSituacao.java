package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioOSSituacaoHelper;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**[UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * @author Diogo Peixoto
 * @since 03/06/2011
 *
 */

public class RelatorioOSSituacao extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioOSSituacao(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		FiltrarRelatorioOSSituacaoHelper helper = (FiltrarRelatorioOSSituacaoHelper) getParametro("filtrarRelatorioOSSituacaoHelper");
		
		Collection<RelatorioOSSituacaoBean> beans = Fachada.getInstancia().pesquisarDadosRelatorioOSSituacao(helper);

		if(Util.isVazioOrNulo(beans)){
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// Parâmetros do relatório
		Map<String, String> parametros = montarParametrosRelatorio(helper);
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioOSSituacaoBean>) beans);
		try {
			if(helper.getOpcaoRelatorio().equals("1")){
				retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OS_SITUACAO_ANALITICO, parametros, ds, tipoFormatoRelatorio);
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_OS_SITUACAO_ANALITICO, idFuncionalidadeIniciada);
			}else if(helper.getOpcaoRelatorio().equals("2")){
				retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OS_SITUACAO_SINTETICO, parametros, ds, tipoFormatoRelatorio);
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_OS_SITUACAO_SINTETICO, idFuncionalidadeIniciada);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;
	}
	
	public Map<String, String> montarParametrosRelatorio(FiltrarRelatorioOSSituacaoHelper helper) {
		
		Map<String, String> parametros = new HashMap<String, String>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1177");
		
		String origemOS = "";
		if(helper.getCobranca()!=null){
			origemOS +="COBRANÇA";
		}
		
		if(helper.getSeletiva()!=null){
			if(helper.getCobranca()!=null){
				origemOS+=" | SELETIVA";
			}else{
				origemOS+="SELETIVA";
			}
			
		}
		
		if(helper.getAtendimento()!=null){
			if(helper.getCobranca()!=null || helper.getSeletiva()!=null){
				origemOS+=" | ATENDIMENTO";
			}else{
				origemOS+="ATENDIMENTO";
			}
			
		}
		
		parametros.put("origemOS", origemOS);
		
		String codSituacaoOS = helper.getSituacaoOS();
		String descricaoSituacaoOS = "";
		
		if (codSituacaoOS != null && !codSituacaoOS.equals("-1") && !codSituacaoOS.equals("")){
			
			if (codSituacaoOS.equals("1")) {
				descricaoSituacaoOS = "DESCONTADAS";
			} else if (codSituacaoOS.equals("2")) {
				descricaoSituacaoOS = "ENCERRADAS";
			} else if (codSituacaoOS.equals("3")) {
				descricaoSituacaoOS = "EXECUTADAS";
			} else if (codSituacaoOS.equals("4")) {
				descricaoSituacaoOS = "FISCALIZADAS";
			} else if (codSituacaoOS.equals("5")) {
				descricaoSituacaoOS = "JUSTIFICADAS";
			} else if (codSituacaoOS.equals("6")) {
				descricaoSituacaoOS = "PENALIZADAS POR FISCALIZAÇÃO";
			} else if (codSituacaoOS.equals("7")) {
				descricaoSituacaoOS = "PENALIZADAS POR DECURSO DE PRAZO";
			} else if (codSituacaoOS.equals("8")) {
				descricaoSituacaoOS = "TODAS";
			} else if (codSituacaoOS.equals("9")) {
				descricaoSituacaoOS = "ENCERRADAS COM EXECUÇÃO";
			} else if (codSituacaoOS.equals("10")) {
				descricaoSituacaoOS = "ENCERRADAS POR DECURSO DE PRAZO";
			} else if (codSituacaoOS.equals("11")) {
				descricaoSituacaoOS = "PENDENTES";
			} else if (codSituacaoOS.equals("12")) {
				descricaoSituacaoOS = "FISCALIZADAS";
			} else if (codSituacaoOS.equals("13")) {
				descricaoSituacaoOS = "TODAS";
			}
				
		}
		
		parametros.put("situacaoOS", descricaoSituacaoOS);

		if (helper.getDataReferenciaInicial() != null) {
			parametros.put("periodoReferenciaInicial", helper.getDataReferenciaInicial());
		} else {
			parametros.put("periodoReferenciaInicial", "");
		}
		
		if (helper.getDataReferenciaFinal() != null) {
			parametros.put("periodoReferenciaFinal", helper.getDataReferenciaFinal());
		} else {
			parametros.put("periodoReferenciaFinal", "");
		}
		
		if (helper.getReferenciaCobranca() != null) {
			parametros.put("referenciaCobranca", Util.formatarAnoMesParaMesAno(helper.getReferenciaCobranca().toString()));
		} else {
			parametros.put("referenciaCobranca", "");
		}
		
		if(helper.getOpcaoRelatorio().equals("1")) {
			parametros.put("tipoRelatorio", "ANALÍTICO");
		}else if(helper.getOpcaoRelatorio().equals("2")) {
			parametros.put("tipoRelatorio", "SINTÉTICO");
		}
		
		Integer idEmpresa = helper.getIdEmpresa();
		
		if (idEmpresa != null && !idEmpresa.equals(new Integer("-1"))){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
			Collection<Empresa> empresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(empresas);
			
			parametros.put("descricaoEmpresa", empresa.getDescricao());
		} else {
			parametros.put("descricaoEmpresa", "");
		}
		
		Integer idGrupoCobranca = helper.getIdGrupoCobranca();
		
		if (idGrupoCobranca != null && !idGrupoCobranca.equals(new Integer("-1"))){
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, idGrupoCobranca));
			Collection<CobrancaGrupo> cobrancasGrupo = Fachada.getInstancia().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName()); 
			CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) Util.retonarObjetoDeColecao(cobrancasGrupo);
			
			parametros.put("grupoCobranca", cobrancaGrupo.getDescricao());
		} else {
			parametros.put("grupoCobranca", "");
		}

		Integer idContratoCobranca = helper.getIdContratoCobranca();
		
		if (idContratoCobranca != null && !idContratoCobranca.equals(new Integer("-1"))){
			FiltroContratoEmpresaServico filtroContratoEmpresa = new FiltroContratoEmpresaServico();
			filtroContratoEmpresa.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.ID, idContratoCobranca));
			Collection<ContratoEmpresaServico> contratosEmpresa = Fachada.getInstancia().pesquisar(filtroContratoEmpresa, ContratoEmpresaServico.class.getName());
			ContratoEmpresaServico contratoEmpresaServico = (ContratoEmpresaServico) Util.retonarObjetoDeColecao(contratosEmpresa);
		
			parametros.put("numeroContrato", contratoEmpresaServico.getDescricaoContrato());
		} else {
			parametros.put("numeroContrato", "");
		}
		
		if (helper.getGerenciaRegional() != null) {
			parametros.put("gerenciaRegional", helper.getGerenciaRegional().getNome());
		} else {
			parametros.put("gerenciaRegional", "");
		}
		
		if (helper.getUnidadeNegocio() != null) {
			parametros.put("unidadeNegocio", helper.getUnidadeNegocio().getNome());
		} else {
			parametros.put("unidadeNegocio", "");
		}
		
		if (helper.getEloPolo() != null) {
			parametros.put("localidadeEloPolo", helper.getEloPolo().getId() + " - " + helper.getEloPolo().getDescricao());
		} else {
			parametros.put("localidadeEloPolo", "");
		}
		
		if (helper.getLocalidade() != null) {
			parametros.put("localidade", helper.getLocalidade().getId() + " - " + helper.getLocalidade().getDescricao());
		} else {
			parametros.put("localidade", "");
		}
		
		if (helper.getSetorComercial() != null) {
			parametros.put("setorComercial", helper.getSetorComercial().getCodigo() + " - " + helper.getSetorComercial().getDescricao());
		} else {
			parametros.put("setorComercial", "");
		}
		
		if (helper.getQuadra() != null) {
			parametros.put("quadra", helper.getQuadra().getDescricao());
		} else {
			parametros.put("quadra", "");
		}
		
		if (helper.getServicoTipo() != null) {
			parametros.put("servicoTipo", helper.getServicoTipo().getDescricao());
		} else {
			parametros.put("servicoTipo", "");
		}
		
		String imovelSuperior = helper.getImoveSuperior(); 
		
		if(imovelSuperior != null && !imovelSuperior.equals("")){
			if(imovelSuperior.equals("0")){
				parametros.put("imovelSuperior", "Imóvel médio igual ou inferior a 150m3 e sem irregularidades dentro do prazo.");
			}else{
				parametros.put("imovelSuperior", "Imóvel médio superior a 150m3 ou com qualquer irregularidade dentro do prazo..");
			}
		}else{
			parametros.put("imovelSuperior", "");
		}
		
		return parametros;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOSSituacao", this);
	}
}