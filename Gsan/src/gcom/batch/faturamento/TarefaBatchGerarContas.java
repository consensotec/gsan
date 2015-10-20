package gcom.batch.faturamento;

import gcom.relatorio.faturamento.conta.RelatorioConta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

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
