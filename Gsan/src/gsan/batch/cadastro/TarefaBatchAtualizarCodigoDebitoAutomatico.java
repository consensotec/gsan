package gsan.batch.cadastro;

import gsan.cadastro.localidade.SetorComercial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/** 
 * 
 * Batch criado para atualização da coluna codigo debito automatico do imovel.
 * 
 * @author Hugo Amorim
 * @date 30/03/2010	 
 */
public class TarefaBatchAtualizarCodigoDebitoAutomatico extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchAtualizarCodigoDebitoAutomatico(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarCodigoDebitoAutomatico() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Collection colecaoSetores = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoSetores.iterator();
		
		while(iterator.hasNext()) {
			
			SetorComercial setor = (SetorComercial) iterator.next();
		
			enviarMensagemControladorBatch(
	                ConstantesJNDI.BATCH_ATUALIZAR_CODIGO_DEBITO_AUTOMATICO,
	                new Object[] { this.getIdFuncionalidadeIniciada(),setor});
		
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
		AgendadorTarefas.agendarTarefa("BatchAtualizarCodigoDebitoAutomatico",this);

	}


}
