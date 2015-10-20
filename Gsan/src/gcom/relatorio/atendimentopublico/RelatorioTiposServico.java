package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioTiposServicoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @date 24/11/2011
 */

public class RelatorioTiposServico extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;

	public RelatorioTiposServico(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public Object executar() throws TarefaException {

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
				
		//Recupera filtro dos parametros e faz consulta
		FiltrarRelatorioTiposServicoHelper filtro = (FiltrarRelatorioTiposServicoHelper) getParametro("filtro");
		Collection<RelatorioTiposServicoBean> beans = Fachada.getInstancia().filtrarRelatorioTiposServico(filtro);
		
		if (beans == null || beans.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		//Seta Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1254");
		parametros.put("dadosRelatorio", beans);		
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		String periodo = "";
		if (dataInicial != null && !dataInicial.equals("")){
			periodo = "Período de Referência: "+dataInicial;
			if (dataFinal != null && !dataFinal.equals("")){
				periodo += " À "+dataFinal;
			}
		} 
		parametros.put("periodoReferencia", periodo);
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioTiposServicoBean>) beans);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_TIPOS_SERVICO, parametros, ds, tipoFormatoRelatorio);
		
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_TIPOS_SERVICO, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioTiposServico", this);
	}
}