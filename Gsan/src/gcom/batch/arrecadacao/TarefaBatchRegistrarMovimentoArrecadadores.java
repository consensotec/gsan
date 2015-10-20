package gcom.batch.arrecadacao;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1575] Gerar Arquivo Carteira 17
 * 
 * @author Davi Menezes
 * @date 12/11/2013
 *
 */
public class TarefaBatchRegistrarMovimentoArrecadadores extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchRegistrarMovimentoArrecadadores(){
		super(null, 0);
	}
	
	public TarefaBatchRegistrarMovimentoArrecadadores(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object executar() throws TarefaException {
		
		
		StringBuilder stringBuilderTxt = (StringBuilder) getParametro("stringBuilderTxt");
		Short codigoAgente = (Short) getParametro("codigoAgente");
		String nomeArrecadador = (String) getParametro("nomeArrecadador");
		String idTipoMovimento = (String) getParametro("idTipoMovimento");
		int quantidadeRegistros = (Integer)getParametro("quantidadeRegistros");
		Integer idArrecadador = (Integer) getParametro("idArrecadador");
		ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) getParametro("arrecadadorContrato");
		Collection<Integer> colecaoLinhas = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
			
		enviarMensagemControladorBatch(
	            ConstantesJNDI.BATCH_REGISTRAR_MOVIMENTOS_ARRECADADORES,
	            new Object[] {stringBuilderTxt, codigoAgente, nomeArrecadador,
	            		idTipoMovimento,quantidadeRegistros,getUsuario(),
	            		idArrecadador, arrecadadorContrato,colecaoLinhas,
	            		this.getIdFuncionalidadeIniciada()});
			
		

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
		AgendadorTarefas.agendarTarefa("GerarArquivoCarteira17Batch", this);
	}

}
