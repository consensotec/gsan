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

/**
 * [UC1122] Automatizar Perfis de Grandes Consumidores
 *  
 * @author Mariana Victor
 * @date 07/02/2011
 * 
 */
public class TarefaBatchAutomatizarPerfisDeGrandesConsumidores extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAutomatizarPerfisDeGrandesConsumidores(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAutomatizarPerfisDeGrandesConsumidores() {
		super(null, 0);
	}
	
	public Object executar() throws TarefaException {
		 Collection<Localidade> colecaoIdsLocalidades = 
	        	(Collection<Localidade>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Localidade localidade = (Localidade) iterator.next();

            enviarMensagemControladorBatch(
            		ConstantesJNDI.BATCH_AUTOMATIZAR_PERFIS_DE_GRANDES_CONSUMIDORES_MDB,
                    new Object[] { 
            			localidade.getId(),
                    	this.getIdFuncionalidadeIniciada() }
            );
                            
        }
    
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
		AgendadorTarefas.agendarTarefa("AutomatizarPerfisDeGrandesConsumidores",
				this);
	}
}
