package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1369] Gerar Itens Receita Liquida Indireta Agua Esgoto
 * 
 * @author Anderson Cabral
 * @created 09/08/2012
 */
public class TarefaBatchReceitaLiquidaIndiretaDeAguaEEsgotoMDB extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchReceitaLiquidaIndiretaDeAguaEEsgotoMDB() {
		super(null, 0);
	}
	public TarefaBatchReceitaLiquidaIndiretaDeAguaEEsgotoMDB(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_RECEITA_LIQUIDA_INDIRETA_DE_AGUA_E_ESGOTO,
                new Object[] {this.getIdFuncionalidadeIniciada()});
	
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
		AgendadorTarefas.agendarTarefa("BatchReceitaLiquidaIndiretaDeAguaEEsgoto",this);

	}

}
