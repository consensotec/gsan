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
 * [UC0000] - Gerar Dados Financeiros Atualização Cadastral - Sintético
 * 
 * @author Vivianne Sousa
 * @date 27/07/2015
 */
public class TarefaBatchGerarResumoDadosFinanceirosAtualizacaoCadastral extends TarefaBatch {
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoDadosFinanceirosAtualizacaoCadastral(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoDadosFinanceirosAtualizacaoCadastral(){
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
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_RESUMO_DADOS_FINANCEIROS_ATUALIZACAO_CADASTRAL, 
					new Object[]{ this.getIdFuncionalidadeIniciada(), idParametroTabelaAtualizacaoCadastral});
		}
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarResumoDadosFinanceirosAtualizacaoCadastral", this);
	}
}
