package gsan.batch.faturamento;

import gsan.relatorio.faturamento.conta.RelatorioConta;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarContas extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarContas(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarContas() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		RelatorioConta relatorio = (RelatorioConta) getParametro("relatorio");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_CONTAS, 
			new Object[]{relatorio, this.getIdFuncionalidadeIniciada()});

		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarContasBatch", this);
	}

}
