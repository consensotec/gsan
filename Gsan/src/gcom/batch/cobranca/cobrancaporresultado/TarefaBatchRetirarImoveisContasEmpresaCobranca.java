package gcom.batch.cobranca.cobrancaporresultado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC 1256] Retirar Imóveis e Contas das Empresas de Cobrança
 * 
 * @author Raimundo Martins
 * @date 14/12/2011
 * */
public class TarefaBatchRetirarImoveisContasEmpresaCobranca extends TarefaBatch{

	public TarefaBatchRetirarImoveisContasEmpresaCobranca(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario,idFuncionalidadeIniciada);
	}


	@Deprecated
	public TarefaBatchRetirarImoveisContasEmpresaCobranca() {
		super(null, 0);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public Object executar() throws TarefaException {		
		Integer[] idRegistros = (Integer[]) getParametro("idsRegistro");
		Usuario usuario = (Usuario) getParametro("usuarioLogado");
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_RETIRAR_IMOVEIS_CONTAS_EMPRESA_COBRANCA_MDB,
			new Object[] {this.getIdFuncionalidadeIniciada(), usuario, idRegistros});
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RetirarImoveisContasEmpresaCobrancaBatch", this);
		
	}

}
