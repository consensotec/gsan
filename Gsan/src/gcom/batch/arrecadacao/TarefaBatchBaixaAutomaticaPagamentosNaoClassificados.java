package gcom.batch.arrecadacao;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1517] Baixa Automática dos Pagamentos Não Classificados
 * 
 * @author Davi Menezes
 * @date 08/07/2013
 *
 */
public class TarefaBatchBaixaAutomaticaPagamentosNaoClassificados extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchBaixaAutomaticaPagamentosNaoClassificados(){
		super(null, 0);
	}
	
	public TarefaBatchBaixaAutomaticaPagamentosNaoClassificados(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Iterator iterator = colecaoRotasParaExecucao.iterator();
		
		while (iterator.hasNext()) {

			Rota rota = (Rota) iterator.next();

			enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_BAIXA_AUTOMATICA_PAGAMENTOS_NAO_CLASSIFICADOS,
                new Object[] {this.getIdFuncionalidadeIniciada(),rota });

		}
		
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
		AgendadorTarefas.agendarTarefa("BaixaAutomaticaPagamentosNaoClassificadosBatch", this);
	}

}
