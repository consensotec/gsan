package gsan.batch.cadastro;

import gsan.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/** 
 * @author Vivianne Sousa
 * @date 23/03/2011 
 */
public class TarefaBatchGerarCartaTarifaSocial extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarCartaTarifaSocial(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarCartaTarifaSocial() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta)getParametro("tarifaSocialComandoCarta");

		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_CARTA_TARIFA_SOCIAL,
				new Object[]{tarifaSocialComandoCarta, this.getIdFuncionalidadeIniciada()});

		
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
		AgendadorTarefas.agendarTarefa("BatchGerarCartaTarifaSocial",this);

	}


}
