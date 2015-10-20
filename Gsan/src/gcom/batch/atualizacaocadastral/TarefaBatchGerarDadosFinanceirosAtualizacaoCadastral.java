package gcom.batch.atualizacaocadastral;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC0000] - Gerar Dados Financeiros Atualização Cadastral - Analítico
 * [UC0000] - Gerar Dados Financeiros Atualização Cadastral - Sintético
 * 
 * @author André Miranda
 * @date 09/07/2015
 */
public class TarefaBatchGerarDadosFinanceirosAtualizacaoCadastral extends TarefaBatch {
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarDadosFinanceirosAtualizacaoCadastral(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDadosFinanceirosAtualizacaoCadastral(){
		super(null, 0);
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public Object executar() throws TarefaException {
		
		Collection<Integer> colecaoParametroTabelaAtualizacaoCadastral = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Iterator iterParametro = colecaoParametroTabelaAtualizacaoCadastral.iterator();
		while (iterParametro.hasNext()) {
			Integer idParametroTabelaAtualizacaoCadastral = (Integer) iterParametro.next();
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_DADOS_FINANCEIROS_ATUALIZACAO_CADASTRAL, 
					new Object[]{ this.getIdFuncionalidadeIniciada(), idParametroTabelaAtualizacaoCadastral});
		}
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarDadosFinanceirosAtualizacaoCadastral", this);
	}
}
