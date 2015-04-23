package gsan.batch.faturamento;

import java.util.Collection;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

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
		AgendadorTarefas.agendarTarefa("BatchReceitaLiquidaIndiretaDeAguaEEsgoto",this);

	}

}
