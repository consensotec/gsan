package gsan.batch.atualizacaocadastral;

import java.util.Collection;

import gsan.atualizacaocadastral.bean.ComandoAtualizacaoCadastralDMHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1391] - Gerar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Diogo Luiz
 * @date 26/08/2014
 *
 */
public class TarefaBatchGerarRoteiroDispositivoMovel extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarRoteiroDispositivoMovel(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarRoteiroDispositivoMovel(){
		super(null, 0);
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
	public Object executar() throws TarefaException {
		
		Collection<String> colecaoImoveis = (Collection<String>) getParametro("colecaoImoveis");
		ComandoAtualizacaoCadastralDMHelper comandoHelper = (ComandoAtualizacaoCadastralDMHelper) getParametro("helper");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ROTEIRO_DISPOSITIVO_MOVEL_MDB, 
				new Object[]{ this.getIdFuncionalidadeIniciada(), colecaoImoveis, comandoHelper});
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarRoteiroDispositivoMovel", this);
	}

}