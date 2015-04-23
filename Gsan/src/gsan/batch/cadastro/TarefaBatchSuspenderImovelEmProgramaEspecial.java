package gsan.batch.cadastro;

import java.util.Collection;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

/**
 * Tarefa que suspende todos os imóveis ativos que estão em programa especial.
 * 
 * @author Hugo Amorim
 * @created 13/11/2010
 * 
 */
public class TarefaBatchSuspenderImovelEmProgramaEspecial extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchSuspenderImovelEmProgramaEspecial(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchSuspenderImovelEmProgramaEspecial() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Usuario usuarioLogado = this.getUsuario();
		
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_SUSPENDER_IMOVEL_EM_PROGRAMA_ESPECIAL,
                new Object[] { this.getIdFuncionalidadeIniciada(),usuarioLogado,colecaoRotasParaExecucao});
		
		return null;
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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchSuspenderImovelEmProgramaEspecial",this);
	}

	

}
