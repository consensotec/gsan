package gsan.batch.faturamento;

import gsan.cadastro.empresa.Empresa;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1008] Gerar TXT declara��o de quita��o anual de d�bitos
 * 
 * 	Este caso de uso permite a gera��o do TXT da declara��o de quita��o de d�bitos.
 * 
 * @author Hugo Amorim
 * @date 23/03/2010
 */
public class TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos extends
		TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		

		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");

		Collection colecaoEmpresasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Iterator iterator = colecaoEmpresasParaExecucao.iterator();
		
		while (iterator.hasNext()) {
			
			Empresa empresa = (Empresa) iterator.next();
		
			enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_DECLARACAO_QUITACAO_ANUAL_DEBITOS,
                new Object[] { 
                		this.getIdFuncionalidadeIniciada(),          
                		idGrupoFaturamento,
                		empresa});
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
		AgendadorTarefas.agendarTarefa(
				"BatchGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos", this);

	}



}
