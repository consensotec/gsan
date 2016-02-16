package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.micromedicao.FiltrarRelatorioAcompanhamentoLeituristaForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioAcompanhamentoLeiturista extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelatorioAcompanhamentoLeiturista(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_LEITURISTA);
	}
	
	@Deprecated
	public RelatorioAcompanhamentoLeiturista() {
		super(null, "");
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection rotas = (Collection) getParametro("colecaoRotas");
		FiltrarRelatorioAcompanhamentoLeituristaForm form = (FiltrarRelatorioAcompanhamentoLeituristaForm) getParametro("filtrarRelatorioAcompanhamentoLeituristaForm");
		
		RelatorioAcompanhamentoLeituristaHelper helper;
		
		helper = criarHelper(form,rotas);
		
		Collection rotasLeituristas = fachada.pesquisarLeituristasDasRotas(helper);
		
		
		return rotasLeituristas.size();
	}

	@Override
	public Object executar() throws TarefaException {
		//valor de retorno
		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		
		Collection rotas = (Collection) getParametro("colecaoRotas");
		FiltrarRelatorioAcompanhamentoLeituristaForm form = (FiltrarRelatorioAcompanhamentoLeituristaForm) getParametro("filtrarRelatorioAcompanhamentoLeituristaForm");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioAcompanhamentoLeituristaBean relatorioBean = null;
		
		RelatorioAcompanhamentoLeituristaHelper helper;
		
		helper = criarHelper(form,rotas);
			
		
		Collection<RelatorioAcompanhamentoLeituristaHelper> colecao = fachada
				.pesquisarRelatorioAcompanhamentoLeiturista(helper);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();
			
			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {
				
				RelatorioAcompanhamentoLeituristaHelper relatorioAcompanhamentoLeituristaHelper = (RelatorioAcompanhamentoLeituristaHelper) colecaoIterator
						.next();

				relatorioBean = new RelatorioAcompanhamentoLeituristaBean(relatorioAcompanhamentoLeituristaHelper);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
				

			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", TarefaRelatorio.TIPO_PDF);
		parametros.put("imprimirHorarios",helper.getIndicadorHorario());
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_LEITURISTA,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ACOMPANHAMENTO_LEITURISTA,
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
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoLeiturista", this);
	}
	
	private RelatorioAcompanhamentoLeituristaHelper criarHelper(FiltrarRelatorioAcompanhamentoLeituristaForm form, Collection rotas ){
		RelatorioAcompanhamentoLeituristaHelper retorno = new RelatorioAcompanhamentoLeituristaHelper();
		
		if(form.getMesAno()!=null &&
				!form.getMesAno().equals("")){
			retorno.setMesAno(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno()));
		}
			
		retorno.setRotas(rotas);
		
		if(form.getIndicadorHorario()!=null && !form.getIndicadorHorario().equals("")){
			retorno.setIndicadorHorario(form.getIndicadorHorario());
		}
		
		if(form.getIdEmpresa()!=null && !form.getIdEmpresa().equals("-1")){
			retorno.setIdEmpresa(form.getIdEmpresa());
		}
		if(form.getIdLeiturista()!=null && !form.getIdLeiturista().equals("-1")){
			retorno.setIdLeiturista(form.getIdLeiturista());
		}
		
		if(form.getIdGrupoFaturamento()!=null && !form.getIdGrupoFaturamento().equals("-1")){
			retorno.setIdGrupoFaturamento(form.getIdGrupoFaturamento());
		}
		return retorno;
		
	}

}
