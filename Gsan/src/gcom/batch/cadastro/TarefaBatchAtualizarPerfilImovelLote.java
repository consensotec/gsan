package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchAtualizarPerfilImovelLote extends TarefaBatch  {
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarPerfilImovelLote(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarPerfilImovelLote() {
		super(null, 0);
	}
	
	@SuppressWarnings("unchecked")
	public Object executar() throws TarefaException {

		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Iterator<Integer> iterator = colecaoIdsLocalidade.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = iterator.next();

			System.out.println(
							"*********************************************************"
							+"Localidade ATUALIZAR PERFIL IMOVEL LOTE "
							+(idLocalidade)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ATUALIZAR_PERFIL_IMOVEL_LOTE,
					new Object[]{this.getIdFuncionalidadeIniciada(),getUsuario(),
							idLocalidade});
		}
		
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchAtualizarPerfilImovelLote", this);
	}
}
