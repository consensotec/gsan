package gsan.batch.cadastro;

import java.util.Collection;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchEnviarEmailCobrancaFaturamento extends TarefaBatch {
	
	public TarefaBatchEnviarEmailCobrancaFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchEnviarEmailCobrancaFaturamento() {
		super(null, 0);
	}
	
	private static final long serialVersionUID = 1L;

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
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_ENVIAR_EMAIL_COBRANCA_FATURAMENTO,
				new Object[]{this.getIdFuncionalidadeIniciada()});
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"BatchEnviarEMAILCobrancaFaturamento", this);
	}
}
