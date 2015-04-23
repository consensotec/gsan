package gsan.batch.atualizacaocadastral;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1288] Atualizar Dados Cadastrais do Imóvel pelo Recadastramento
 * 
 * @author Vivianne Sousa
 * @date 09/10/2014
 *
 */
public class TarefaBatchAtualizarDadosCadastraisImovelRecadastramento extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchAtualizarDadosCadastraisImovelRecadastramento(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchAtualizarDadosCadastraisImovelRecadastramento(){
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
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ATUALIZAR_DADOS_CADASTRAIS_IMOVEL_RECADASTRAMENTO_MDB,
				new Object[]{ this.getIdFuncionalidadeIniciada()});
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchAtualizarDadosCadastraisImovelRecadastramento", this);
	}

}