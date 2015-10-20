package gcom.relatorio.arrecadacao.pagamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class RelatorioPagamentoBaixaAutomaticoSintetico extends TarefaRelatorio {
	
	public RelatorioPagamentoBaixaAutomaticoSintetico(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_PAGAMENTO_BAIXA_AUTOMATICA_SINTETICO);
		
	}

	private static final long serialVersionUID = 1L;	

	@Override
	public Object executar() throws TarefaException {		
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		String periodoInicial = (String) getParametro("periodoInicial");
		String periodoFinal = (String) getParametro("periodoFinal");
		String idGerenciaRegional = (String) getParametro("gerenciaRegional");
		String idUnidadeNegocio = (String) getParametro("unidadeNegocio");
		String idLocalidade = (String) getParametro("localidade");
		String idSetorComercial = (String) getParametro("setorComercial");
		String idBaixaAutomatica = (String) getParametro("baixaAutomatica");
		String faixaInicial = (String) getParametro("faixaInicial");
		String faixaFinal = (String) getParametro("faixaInicial");
		String indicadorGerenciaRegional = (String) getParametro("indicadorGerenciaRegional");
		String indicadorUnidadeNegocio = (String) getParametro("indicadorUnidadeNegocio");
		String indicadorLocalidade = (String) getParametro("indicadorLocalidade");
		String indicadorSetorComercial = (String) getParametro("indicadorSetorComercial");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		PagamentosBaixadosAutomaticamenteRelatorioHelper helper = (PagamentosBaixadosAutomaticamenteRelatorioHelper) getParametro("helper");
		
		byte[] retorno = null;
		
		List relatorioBeans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection<RelatorioPagamentosBaixadosAutomaticamenteSinteticoBean> colecaoRelatorioBean = null;
		
		colecaoRelatorioBean = fachada.pesquisarRelatorioSinteticoPagamentosBaixadosAutomaticamente(helper);		
		
		if(Util.isVazioOrNulo(colecaoRelatorioBean)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1518");
		parametros.put("periodoInicial", periodoInicial);
		parametros.put("periodoFinal", periodoFinal);
		parametros.put("agrupamentoGerenciaRegional", indicadorGerenciaRegional);
		parametros.put("agrupamentoUnidadeNegocio", indicadorUnidadeNegocio);
		parametros.put("agrupamentoLocalidade", indicadorLocalidade);
		parametros.put("agrupamentoSetorComercial", indicadorSetorComercial);
		
		String baixaAutomatica = "TODOS";
		if(idBaixaAutomatica != null){
			if(idBaixaAutomatica.equals("1")){
				baixaAutomatica = "CRÉDITO";
			}else if(idBaixaAutomatica.equals("2")){
				baixaAutomatica = "DÉBITO";
			}
		}
		
		parametros.put("baixaAutomatica", baixaAutomatica);
		
		String valorInicial = null;
		if(faixaInicial != null && !faixaInicial.equals("")){
			valorInicial = faixaInicial;
		}
		
		parametros.put("valorInicial", valorInicial);
		
		String valorFinal = null;
		if(faixaFinal != null && !faixaFinal.equals("")){
			valorFinal = faixaFinal;
		}
		
		parametros.put("valorFinal", valorFinal);
		
		String gerenciaRegional = null;
		if(idGerenciaRegional != null && !idGerenciaRegional.equals("") && !idGerenciaRegional.equals("-1")){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, idGerenciaRegional));
			
			Collection<?> colGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			if(!Util.isVazioOrNulo(colGerenciaRegional)){
				GerenciaRegional gerRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(colGerenciaRegional);
				gerenciaRegional = "" + gerRegional.getId() + " - " + gerRegional.getNomeAbreviado();
			}
		}
		
		parametros.put("gerenciaRegional", gerenciaRegional);
		
		String unidadeNegocio = null;
		if(idUnidadeNegocio != null && !idUnidadeNegocio.equals("") && !idUnidadeNegocio.equals("-1")){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, idUnidadeNegocio));
			
			Collection<?> colUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			if(!Util.isVazioOrNulo(colUnidadeNegocio)){
				UnidadeNegocio unidNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colUnidadeNegocio);
				unidadeNegocio = "" + unidNegocio.getId() + " - " + unidNegocio.getNomeAbreviado();
			}
		}
		
		parametros.put("unidadeNegocio", unidadeNegocio);
		
		String localidade = null;
		if(idLocalidade != null && !idLocalidade.equals("") && !idLocalidade.equals("-1")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			if(!Util.isVazioOrNulo(colLocalidade)){
				Localidade loc = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
				localidade = "" + loc.getId() + " - " + loc.getDescricao();
			}
		}
		
		parametros.put("localidade", localidade);
		
		String setorComercial = null;
		if(idSetorComercial != null && !idSetorComercial.equals("") && !idSetorComercial.equals("-1")){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercial));
			
			Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			if(!Util.isVazioOrNulo(colSetorComercial)){
				SetorComercial setComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
				setorComercial = "" + setComercial.getCodigo() + " - " + setComercial.getDescricao();
			}
		}
		
		parametros.put("setorComercial", setorComercial);
		
		relatorioBeans = (ArrayList) colecaoRelatorioBean;

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PAGAMENTO_BAIXA_AUTOMATICA_SINTETICO,
				parametros, ds, tipoFormatoRelatorio);

		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_PAGAMENTO_BAIXA_AUTOMATICA_SINTETICO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		
		return retorno;		
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		
	}

}
