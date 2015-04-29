package gsan.batch.atendimentopublico;

import gsan.cadastro.localidade.SetorComercial;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC0996] Emitir Ordem de Fiscaliza��o para im�veis suprimidos
 * 
 *  Este caso de uso permite realizar a recupera��o das informa��es dos im�veis que estejam com seus ramais surprimidos,
 *  parcialmente ou totalmente, por um per�odo pr�-determinado e os armazena em uma base de dados
 * 	para uma posterior gera��o de arquivo de texto.
 * 
 * 
 * @author Hugo Amorim
 * @date 08/03/2010
 * 
 */
public class TarefaBatchEmitirOrdemDeFiscalizacao extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchEmitirOrdemDeFiscalizacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirOrdemDeFiscalizacao() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Usuario usuarioLogado = this.getUsuario();
		
		Collection colecaoSetores = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("SistemaParametros");
		
		Iterator iterator = colecaoSetores.iterator();
		
		while(iterator.hasNext()) {
			
			SetorComercial setor = (SetorComercial) iterator.next();
		
			enviarMensagemControladorBatch(
	                ConstantesJNDI.BATCH_EMITIR_ORDEM_FISCALIZACAO,
	                new Object[] { this.getIdFuncionalidadeIniciada(),usuarioLogado,setor,sistemaParametro});
		
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
		AgendadorTarefas.agendarTarefa("BatchEmitirOrdemDeFiscalizacao",this);

	}

}
