package gsan.batch.cobranca.cobrancaporresultado;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1168] Encerrar Comandos de Cobrança por Empresa
 * Tarefa que manda para batch Encerrar Comandos de Cobrança por Empresa
 * 
 * @author Mariana Victor
 * @created 09/05/2011
 */
public class TarefaBatchEncerrarComandosDeCobrancaPorEmpresa extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEncerrarComandosDeCobrancaPorEmpresa(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarComandosDeCobrancaPorEmpresa() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		String idEmpresa = (String) getParametro("idEmpresa");
		Integer idRegistro = (Integer) getParametro("idRegistro");
		Usuario usuario = (Usuario) getParametro("usuario");
		Integer idCobrancaSituacao = (Integer) getParametro("idCobrancaSituacao");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENCERRAR_COMANDO_DE_COBRANCA_POR_EMPRESA,
					new Object[] {
								this.getIdFuncionalidadeIniciada(),
								idEmpresa,
								usuario,
								idRegistro,
								idCobrancaSituacao});

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
		AgendadorTarefas.agendarTarefa("EncerrarComandosDeCobrancaPorEmpresaBatch", this);
	}

}
