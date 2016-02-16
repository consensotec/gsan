package gcom.relatorio.mobile.execucaoOrdemServico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioErrosEncerramentoOSCobranca extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioErrosEncerramentoOSCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ERROS_ENCERRAMENTO_OS_COBRANCA);
	}
	
	@Deprecated
	public RelatorioErrosEncerramentoOSCobranca(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		//Valor de Retorno
		byte[] retorno = null;
		
		Collection<RelatorioErrosEncerramentoOSCobrancaBean> colecaoBeans = (Collection<RelatorioErrosEncerramentoOSCobrancaBean>) getParametro("colecaoBeans");
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		if(!Util.isVazioOrNulo(colecaoBeans)){
			relatorioBeans.addAll(colecaoBeans);
		}
		
		//Parametros do Relatorio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1499");
		
		//Cria uma instancia do dataSource do Relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ERROS_ENCERRAMENTO_OS_COBRANCA, 
				parametros, ds, tipoFormatoRelatorio);
		
		//Retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioErrosEncerramentoOSCobranca", this);
	}

}
