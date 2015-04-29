package gsan.relatorio.arrecadacao.pagamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gsan.batch.Relatorio;
import gsan.cadastro.localidade.FiltroGerenciaRegional;
import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.FiltroUnidadeNegocio;
import gsan.cadastro.localidade.GerenciaRegional;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
		
		// Par�metros do relat�rio
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
				baixaAutomatica = "CR�DITO";
			}else if(idBaixaAutomatica.equals("2")){
				baixaAutomatica = "D�BITO";
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

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_PAGAMENTO_BAIXA_AUTOMATICA_SINTETICO,
				parametros, ds, tipoFormatoRelatorio);

		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_PAGAMENTO_BAIXA_AUTOMATICA_SINTETICO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
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
