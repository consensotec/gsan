package gsan.batch.faturamento;

import java.util.Collection;
import java.util.Iterator;

import gsan.cadastro.localidade.Localidade;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchAlterarInscricaoImovel extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchAlterarInscricaoImovel(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAlterarInscricaoImovel() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		 Collection<Localidade> colecaoIdsLocalidades = 
	        	(Collection<Localidade>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
	        
	        Iterator iterator = colecaoIdsLocalidades.iterator();

	        while (iterator.hasNext()) {

	            Localidade localidade = (Localidade) iterator.next();

	            enviarMensagemControladorBatch(
	            		ConstantesJNDI.BATCH_ALTERAR_INSCRICOES_IMOVEIS_MDB,
	                    new Object[] {
	            			this.getIdFuncionalidadeIniciada(),	
	            			localidade.getId()
	                     }
	            );
	                            
	        }

	        return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchAlterarInscricaoImovel",this);
	}

}
