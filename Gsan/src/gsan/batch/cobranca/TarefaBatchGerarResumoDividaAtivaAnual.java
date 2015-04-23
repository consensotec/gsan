package gsan.batch.cobranca;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;
/**
 * [UC1591] Gerar Resumo Divida Atida Anual
 * 
 * @author Ana Maria 
 * @date 12/03/2014
 *
 */
public class TarefaBatchGerarResumoDividaAtivaAnual extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchGerarResumoDividaAtivaAnual(){
		super(null, 0);
	}
	
	public TarefaBatchGerarResumoDividaAtivaAnual(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Override
	public Object executar() throws TarefaException {

		
		Collection colecaoIdsSetorComercial = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		  Iterator iterator = colecaoIdsSetorComercial.iterator();
	        
	        while (iterator.hasNext()) {
	        	Integer idSetorComercial = (Integer) iterator.next();
				enviarMensagemControladorBatch(
		                ConstantesJNDI.BATCH_GERAR_RESUMO_DIVIDA_ATIVA_ANUAL,
		                new Object[] {this.getIdFuncionalidadeIniciada(), idSetorComercial});
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
		AgendadorTarefas.agendarTarefa("BaixaGerarResumoDividaAtivaAnualBatch", this);
	}

}
