package gsan.batch.atendimentopublico;

import gsan.atendimentopublico.bean.ProcessarAtualizacaoOSArquivoTextoHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
 *
 * @author Mariana Victor
 * @created 30/09/2011
 */
public class TarefaBatchProcessarAtualizacaoDasOrdensServicoDoArquivoTexto extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchProcessarAtualizacaoDasOrdensServicoDoArquivoTexto(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProcessarAtualizacaoDasOrdensServicoDoArquivoTexto() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection<ProcessarAtualizacaoOSArquivoTextoHelper> colecaoHelper = (Collection<ProcessarAtualizacaoOSArquivoTextoHelper>) 
				getParametro("colecaoProcessarAtualizacaoOSArquivoTextoHelper");
		
		Usuario usuario = (Usuario) getParametro("usuarioLogado");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_PROCESSAR_ATUAL_ORDENS_SERVICO_ARQ_TXT_MDB,
					new Object[] { this.getIdFuncionalidadeIniciada(),
					                colecaoHelper,
					                usuario});

		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchProcessarAtualizacaoDasOrdensServicoDoArquivoTexto", this);
	}

}
