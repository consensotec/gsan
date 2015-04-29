package gsan.batch.atendimentopublico;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1210] Processar Encerramento de Ordens de Servi�o de Fiscaliza��o por Decurso de Prazo
 *
 * @author Hugo Azevedo
 * @created 10/08/2011
 */
public class TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO_MDB,
					new Object[] { this.getIdFuncionalidadeIniciada()
								/*this.getIdFuncionalidadeIniciada(),
								usuario,
								idRegistro,
								idMotivoEncerramento*/});

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
		AgendadorTarefas.agendarTarefa("ProcessarEncerramentoOSFiscalizacaoDecursoPrazoBatch", this);
	}

}
