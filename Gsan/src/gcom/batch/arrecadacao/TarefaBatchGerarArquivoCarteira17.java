package gcom.batch.arrecadacao;

import java.util.Collection;
import java.util.Iterator;

import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1575] Gerar Arquivo Carteira 17
 * 
 * @author Davi Menezes
 * @date 12/11/2013
 *
 */
public class TarefaBatchGerarArquivoCarteira17 extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchGerarArquivoCarteira17(){
		super(null, 0);
	}
	
	public TarefaBatchGerarArquivoCarteira17(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Collection<?> colecaoGrupoFaturamento = (Collection<?>) getParametro("colecaoGrupoFaturamento");
		
		Integer anoMesReferencia = (Integer) getParametro("anoMesReferencia");
		
		Integer idBanco = (Integer) getParametro("idBanco");
		
		enviarMensagemControladorBatch(
            ConstantesJNDI.BATCH_GERAR_ARQUIVO_CARTEIRA_17,
            new Object[] {this.getIdFuncionalidadeIniciada(),
            colecaoGrupoFaturamento, anoMesReferencia, idBanco});

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
		AgendadorTarefas.agendarTarefa("GerarArquivoCarteira17Batch", this);
	}

}
