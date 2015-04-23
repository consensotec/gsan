package gsan.batch.faturamento;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarArquivoTextoContasProjetosEspeciais extends TarefaBatch {

	private static final long serialVersionUID = 1L;


	public TarefaBatchGerarArquivoTextoContasProjetosEspeciais(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoContasProjetosEspeciais() {
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

		String anoMes = (String) getParametro("anoMes");

		Integer idCliente = (Integer) getParametro("idCliente");

		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_PROJETOS_ESPECIAIS,
					new Object[] {anoMes,idCliente, this.getIdFuncionalidadeIniciada()});

		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"TarefaBatchGerarArquivoTextoContasProjetosEspeciais",
				this);

	}

}
