package gsan.batch.atendimentopublico;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC0713] Emitir Ordem de Serviço Seletiva
 * [SB0002]-Gerar TXT 
 * 
 * @author Vivianne Sousa
 * @date 28/06/2011
 */
public class TarefaBatchGerarTxtOsInspecaoAnormalidade extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarTxtOsInspecaoAnormalidade(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarTxtOsInspecaoAnormalidade() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Integer idComandoOrdemSeletiva = (Integer) getParametro("idComandoOrdemSeletiva");
		Integer qtdAnormalidadesConsecutivas = (Integer) getParametro("qtdAnormalidadesConsecutivas");
		
		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_TXT_OS_INSPECAO_ANORMALIDADE,
                new Object[] { this.getIdFuncionalidadeIniciada(),idComandoOrdemSeletiva,qtdAnormalidadesConsecutivas});
		
		
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
		AgendadorTarefas.agendarTarefa("BatchGerarTxtOsInspecaoAnormalidade",this);

	}

}
