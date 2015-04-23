package gsan.batch.cadastro;

import gsan.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/** 
 * @author Vivianne Sousa
 * @date 23/03/2011 
 */
public class TarefaBatchProcessarComandoGerado extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchProcessarComandoGerado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProcessarComandoGerado() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta)getParametro("tarifaSocialComandoCarta");
		Collection idsLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = idsLocalidade.iterator();
		
		while(iterator.hasNext()) {
			
			Integer idLocalidade = (Integer) iterator.next();
		
			enviarMensagemControladorBatch(
	                ConstantesJNDI.BATCH_PROCESSAR_COMANDO_GERADO,
	                new Object[] { idLocalidade,this.getIdFuncionalidadeIniciada(),tarifaSocialComandoCarta});
		
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
		AgendadorTarefas.agendarTarefa("BatchProcessarComandoGerado",this);

	}


}
