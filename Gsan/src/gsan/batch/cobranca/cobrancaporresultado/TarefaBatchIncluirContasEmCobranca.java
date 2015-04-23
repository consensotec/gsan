package gsan.batch.cobranca.cobrancaporresultado;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1264] Incluir Contas em Cobrança
 * Tarefa que manda para batch Incluir Contas em Cobrança
 * 
 * @author Mariana Victor
 * @created 27/12/2011
 */
public class TarefaBatchIncluirContasEmCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchIncluirContasEmCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchIncluirContasEmCobranca() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		if (colecaoIdsLocalidade != null && !colecaoIdsLocalidade.isEmpty()){
			Iterator<Integer> iter = colecaoIdsLocalidade.iterator();
			
			while (iter.hasNext()) {
				Integer idLocalidade = (Integer) iter.next();
				
				Integer anoMesArrecadacaoSistemaParametro = (Integer) getParametro("anoMesArrecadacaoSistemaParametro");
				
				enviarMensagemControladorBatch(ConstantesJNDI.BATCH_INCLUIR_CONTAS_EM_COBRANCA_MDB,
					new Object[]{idLocalidade,anoMesArrecadacaoSistemaParametro,
					             this.getIdFuncionalidadeIniciada()});
				
			}
		}
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("IncluirContasEmCobrancaBatch", this);
	}

}
