package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * UC1242 - Cancelar Faturamento Grupo
 *
 * @author Arthur Carvalho
 * @created 18/10/2011
 */
public class TarefaBatchCancelarGrupoFaturamento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchCancelarGrupoFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchCancelarGrupoFaturamento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
		Integer anoMesReferencia = (Integer) getParametro("anoMesReferencia");
		Integer anoMesReferenciaGrupoMenosUmMes = (Integer) getParametro("anoMesReferenciaGrupoMenosUmMes");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_CANCELAR_GRUPO_FATURAMENTO_MDB,
					new Object[] { this.getIdFuncionalidadeIniciada(), 
					               idGrupoFaturamento, 
					               anoMesReferencia,
					               anoMesReferenciaGrupoMenosUmMes});

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
		AgendadorTarefas.agendarTarefa("CancelarGrupoFaturamentoBatch", this);
	}

}
