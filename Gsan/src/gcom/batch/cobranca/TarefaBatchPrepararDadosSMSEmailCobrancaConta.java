package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1616] Preparar Dados SMS/EMAIL Cobranca de Conta
 * 
 * @author Hugo Azevedo
 * @date 10/07/2014
 *
 */
public class TarefaBatchPrepararDadosSMSEmailCobrancaConta extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchPrepararDadosSMSEmailCobrancaConta(){
		super(null, 0);
	}
	
	public TarefaBatchPrepararDadosSMSEmailCobrancaConta(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Integer idFaturamentoGrupo = (Integer)this.getParametro("idFaturamentoGrupo");
		Integer anoMesFaturamentoGrupo = (Integer)this.getParametro("anoMesFaturamentoGrupo");

			enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_PREPARAR_DADOS_SMS_EMAIL_COBRANCA_CONTA,
                new Object[] {idFaturamentoGrupo,anoMesFaturamentoGrupo,this.getIdFuncionalidadeIniciada(), this.getUsuario()});
			
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
		AgendadorTarefas.agendarTarefa("BatchPrepararDadosSMSEmailCobrancaConta", this);
	}

}
