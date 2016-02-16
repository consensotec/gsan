package gcom.relatorio.gerencial.micromedicao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioResumoZonaAbastecimento extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioResumoZonaAbastecimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_ZONA_ABASTECIMENTO);
	}
	@Deprecated
	public RelatorioResumoZonaAbastecimento() {
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		// valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		FiltrarRelatorioResumoDistritoOperacionalHelper filtro = (FiltrarRelatorioResumoDistritoOperacionalHelper) getParametro("filtrarRelatorioResumoDistritoOperacionalHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioResumoZonaAbastecimentoBean relatorioBean = null;

		Collection<RelatorioResumoZonaAbastecimentoHelper> colecao = fachada
				.pesquisarRelatorioResumoZonaAbastecimento(filtro);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioResumoZonaAbastecimentoHelper helper = (RelatorioResumoZonaAbastecimentoHelper) colecaoIterator
						.next();

				relatorioBean = new RelatorioResumoZonaAbastecimentoBean(helper);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);

			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		parametros = this.setarParametros();

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_ZONA_ABASTECIMENTO,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_RESUMO_ZONA_ABASTECIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoZonaAbastecimento", this);
		
	}
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		retorno = Fachada
				.getInstancia()
				.pesquisarTotalRegistroRelatorioResumoDistritoOperacional(
						(FiltrarRelatorioResumoDistritoOperacionalHelper) getParametro("filtrarRelatorioResumoDistritoOperacionalHelper"));

		if (retorno == 0) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Relat�rio");
		}

		return retorno;
	}
	public Map setarParametros() {
		Map parametros = new HashMap();
		FiltrarRelatorioResumoDistritoOperacionalHelper filtro = (FiltrarRelatorioResumoDistritoOperacionalHelper) getParametro("filtrarRelatorioResumoDistritoOperacionalHelper");
		SistemaParametro sistemaParametro = Fachada.getInstancia()
				.pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		String mesAno = gcom.util.Util.formatarAnoMesParaMesAno(filtro
				.getMesAno());

		if (filtro.getMesAno() != null && !filtro.getMesAno().equals("")) {
			parametros.put("mesAno", mesAno);
		}
		if (filtro.getDescDistritoOperacional() != null
				&& !filtro.getDescDistritoOperacional().equals("")) {
			parametros.put("nomeDistritoOperacional", filtro
					.getDescDistritoOperacional());
		}
		if (filtro.getDistritoOperacional() != null
				&& !filtro.getDistritoOperacional().equals("")) {
			parametros.put("idDistritoOperacional", filtro
					.getDistritoOperacional());
		}
		if(filtro.getCodigoSetorComercialInicial()!=null && !filtro.getCodigoSetorComercialInicial().equals("")){
			parametros.put("codigoSetorInicial",filtro.getCodigoSetorComercialInicial());
		}
		if(filtro.getCodigoSetorComercialFinal()!=null && !filtro.getCodigoSetorComercialFinal().equals("")){
			parametros.put("codigoSetorFinal",filtro.getCodigoSetorComercialFinal());
		}
		if (filtro.getDescUnidadeNegocio() != null
				&& !filtro.getDescUnidadeNegocio().equals("")) {
			parametros
					.put("nomeUnidadeNegocio", filtro.getDescUnidadeNegocio());
		}
		if (filtro.getUnidadeNegocio() != null
				&& !filtro.getUnidadeNegocio().equals("")) {
			parametros
					.put("idUnidadeNegocio", filtro.getUnidadeNegocio());
		}
		if (filtro.getDescGerenciaRegional() != null
				&& !filtro.getDescGerenciaRegional().equals("")) {
			parametros.put("nomeGerenciaRegional", filtro
					.getDescGerenciaRegional());
		}
		if (filtro.getGerenciaRegional() != null
				&& !filtro.getGerenciaRegional().equals("")) {
			parametros.put("idGerenciaRegional", filtro
					.getGerenciaRegional());
		}
		if (filtro.getLocalidadeInicial() != null
				&& !filtro.getLocalidadeInicial().equals("")) {
			parametros.put("idLocalidadeInicial", filtro.getLocalidadeInicial()
					.toString());
		}
		if (filtro.getNomeLocalidadeInicial() != null
				&& !filtro.getNomeLocalidadeInicial().equals("")) {
			parametros.put("nomeLocalidadeInicial", filtro
					.getNomeLocalidadeInicial().toString());
		}
		if (filtro.getSetorComercialInicial() != null
				&& !filtro.getSetorComercialInicial().equals("")) {
			parametros.put("idSetorComercialInicial", filtro
					.getSetorComercialInicial().toString());
		}
		if (filtro.getNomeSetorComercialInicial() != null
				&& !filtro.getNomeSetorComercialInicial().equals("")) {
			parametros.put("nomeSetorComercialInicial", filtro
					.getNomeSetorComercialInicial().toString());
		}
		if (filtro.getLocalidadeFinal() != null
				&& !filtro.getLocalidadeFinal().equals("")) {
			parametros.put("idLocalidadeFinal", filtro.getLocalidadeInicial()
					.toString());
		}
		if (filtro.getNomeLocalidadeFinal() != null
				&& !filtro.getNomeLocalidadeFinal().equals("")) {
			parametros.put("nomeLocalidadeFinal", filtro
					.getNomeLocalidadeInicial().toString());
		}
		if (filtro.getSetorComercialFinal() != null
				&& !filtro.getSetorComercialFinal().equals("")) {
			parametros.put("idSetorComercialFinal", filtro
					.getSetorComercialInicial().toString());
		}
		if (filtro.getNomeSetorComercialFinal() != null
				&& !filtro.getNomeSetorComercialFinal().equals("")) {
			parametros.put("nomeSetorComercialFinal", filtro
					.getNomeSetorComercialInicial().toString());
		}
		return parametros;
	}
}
