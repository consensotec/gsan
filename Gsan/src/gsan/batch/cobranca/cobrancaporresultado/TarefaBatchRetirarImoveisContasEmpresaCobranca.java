package gsan.batch.cobranca.cobrancaporresultado;

import java.util.Collection;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

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
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
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
