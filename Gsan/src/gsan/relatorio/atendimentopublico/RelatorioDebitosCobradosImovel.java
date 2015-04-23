package gsan.relatorio.atendimentopublico;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioDebitosCobrancaImovelHelper;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1254] Relatório Ordem de Serviço com valores de cobrança
 * @author Amélia Pessoa
 * @date 24/11/2011
 */

public class RelatorioDebitosCobradosImovel extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;

	public RelatorioDebitosCobradosImovel(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public Object executar() throws TarefaException {

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
				
		//Recupera filtro dos parametros e faz consulta
		FiltrarRelatorioDebitosCobrancaImovelHelper filtro = (FiltrarRelatorioDebitosCobrancaImovelHelper) getParametro("filtro");
		Collection<RelatorioDebitosCobradosImovelBean> beans = Fachada.getInstancia().filtrarRelatorioDebitosCobradosImovel(filtro);
		
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
		parametros.put("matriculaImovel", getParametro("matriculaImovel"));
		
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		String periodo = "";
		if (dataInicial != null && !dataInicial.equals("")){
			periodo = "Período de Cobrança: "+dataInicial;
			if (dataFinal != null && !dataFinal.equals("")){
				periodo += " À "+dataFinal;
			}
			
		} 
		parametros.put("periodoCobranca", periodo);
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioDebitosCobradosImovelBean>) beans);
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DEBITOS_COBRADOS_IMOVEL, parametros, ds, tipoFormatoRelatorio);
		
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_DEBITOS_COBRANCA_IMOVEL, idFuncionalidadeIniciada);
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
		AgendadorTarefas.agendarTarefa("RelatorioDebitosCobradosImovel", this);
	}
}