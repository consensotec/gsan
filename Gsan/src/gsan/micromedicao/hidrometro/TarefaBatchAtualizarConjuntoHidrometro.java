package gsan.micromedicao.hidrometro;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchAtualizarConjuntoHidrometro extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchAtualizarConjuntoHidrometro(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarConjuntoHidrometro() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		String fixo = (String) getParametro("fixo");
		
		String fixoInicial = (String) getParametro("fixoInicial");
		
		String fixoFinal = (String) getParametro("fixoFinal");
		
		int qtdPaginas = (Integer) getParametro("qtdPaginas");
		
		Hidrometro hidrometroAtualizado = (Hidrometro) getParametro("hidrometroAtualizado");
		
		Usuario usuarioLogago = (Usuario) getParametro("usuario");
		
		for ( int i=0;i<qtdPaginas; i++ ){
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ATUALIZAR_CONJUNTO_HIDROMETRO_MDB,
					new Object[]{fixo,fixoInicial,fixoFinal,hidrometroAtualizado,usuarioLogago,i,this.getIdFuncionalidadeIniciada()});
		}
			
		return null;
	}
	
	@Override
	protected Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}	

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("AtualizarConjuntoHidrometroBatch", this);
	}

}
