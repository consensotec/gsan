package gcom.batch.cobranca;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * [UC 1351] Retirar Contas Revisão Extrato Entrada de Parcelamento
 * 
 * @author Davi Menezes
 * @date 02/07/2012
 *
 */
public class TarefaBatchRetirarContasRevisaoExtratoEntradaParcelamento extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchRetirarContasRevisaoExtratoEntradaParcelamento(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchRetirarContasRevisaoExtratoEntradaParcelamento(){
		super(null, 0);
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
	public Object executar() throws TarefaException {
		
		Date dataLimiteValidade = (Date) getParametro("dataLimiteValidade"); 
		Collection<Localidade> colecaoLocalidades = (Collection<Localidade>) 
					getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Iterator iterator = colecaoLocalidades.iterator();

        while (iterator.hasNext()) {
        	Localidade localidade = (Localidade)iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_RETIRAR_CONTAS_REVISAO_EXTRATO_ENTRADA_PARCELAMENTO,
                    new Object[] {this.getIdFuncionalidadeIniciada(),
                    			  localidade.getId(),
	                    		  dataLimiteValidade });
                            
        }

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RetirarContasRevisaoExtratoEntradaParcelamentoBatch", this);
	}

}
