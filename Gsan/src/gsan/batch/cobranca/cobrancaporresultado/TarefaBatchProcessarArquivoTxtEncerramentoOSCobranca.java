package gsan.batch.cobranca.cobrancaporresultado;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1182] Recepcionar Arquivo TXT Encerramento OS Cobrança
 * 
 * Tarefa que manda para batch Processar Arquivo TXT Encerramento OS Cobrança
 * 
 * @author Mariana Victor
 * @date 21/06/2011
 */
public class TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProcessarArquivoTxtEncerramentoOSCobranca() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		String idEmpresa = (String) getParametro("idEmpresa");
		StringBuilder stringBuilder = (StringBuilder) getParametro("stringBuilder");
		Usuario usuario = (Usuario) getParametro("usuario");
		String nomeArquivo = (String) getParametro("nomeArquivo");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_PROCESSAR_ARQUIVO_TXT_ENCERRAMENTO_OS_COBRANCA,
					new Object[] {
								this.getIdFuncionalidadeIniciada(),
								idEmpresa,
								usuario,
								stringBuilder,
								nomeArquivo});

		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("ProcessarArquivoTxtEncerramentoOSCobrancaBatch", this);
	}

}
