package gsan.batch.faturamento;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.micromedicao.Rota;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * [UC1001] Emitir declara��o de quita��o anual de d�bitos
 * 
 * 	Este caso de uso permite a gera��o de declara��o de quita��o de d�bitos.
 * 
 * @author Hugo Amorim
 * @date 17/03/2010
 */
public class TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDadosDeclaracaoQuitacaoAnualDebitos() {
		super(null, 0);
	}


	@Override
	public Object executar() throws TarefaException {
		
		
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("SistemaParametros");
		
		if(sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao() == null
				|| sistemaParametro.getNumeroAnoQuitacao() == null){
			throw new TarefaException("atencao.sistema_nao_parametrizado");
		}
		
		
		//Quantidade de anos para gerar a declara��o 
		//(realizar a diferen�a entre o ano de SISTEMA_PARAMETROS.PARM_AMREFERENCIAARRECADACAO 
		//e SISTEMA_PARAMETROS.PARM_NNANOQUITACAO, o valor obtido dever� ser utilizado 
		//para considerar os anos a serem geradas as declara��es at� o ano de  
		//SISTEMA_PARAMETROS.PARM_AMREFERENCIAARRECADACAO -1);
		Integer numeroAnoQuitacao = sistemaParametro.getNumeroAnoQuitacao();
		Integer anoMesArrecadacao = sistemaParametro.getAnoMesArrecadacao();
		
		Integer anoArrecadacao = Util.obterAno(anoMesArrecadacao);
		Integer anoArrecadacaoMenosUm = Util.obterAno(anoMesArrecadacao)-1;
		
		Integer anoInicialParaGerarDeclaracao = anoArrecadacao - numeroAnoQuitacao;
		
		Collection<Integer> anosParaGeracaoDeclaracao = new ArrayList<Integer>();
		
		while(anoInicialParaGerarDeclaracao<=anoArrecadacaoMenosUm){
			anosParaGeracaoDeclaracao.add(anoInicialParaGerarDeclaracao);
			anoInicialParaGerarDeclaracao++;
		}
		
		//	Verificar o indicador de conta parcelada (SISTEMA_PARAMETROS.PARM_ICCONTAPARCELADA) 
		//	Caso este esteja como 1, as contas parceladas dever�o ser verificadas;
		//	Caso contr�rio, n�o dever�o ser consideradas na gera��o da declara��o.
		//	Verificar o indicador de conta em cobran�a judicial (SISTEMA_PARAMETROS.PARM_ICCOBRANCAJUDICIAL) 
		//	Caso este esteja como 1, as contas em cobran�a judicial dever�o ser verificadas; 
		//	Caso contr�rio, n�o dever�o ser consideradas na gera��o da declara��o.
		Short indicadorContaParcelada = ConstantesSistema.NAO;			
		if(sistemaParametro.getIndicadorContaParcelada()!=null
				&& sistemaParametro.getIndicadorContaParcelada()
					.compareTo(ConstantesSistema.SIM)==0){
			indicadorContaParcelada = ConstantesSistema.SIM;
		}
		
		Short indicadorCobrancaJudical = ConstantesSistema.NAO;
		if(sistemaParametro.getIndicadorCobrancaJudical()!=null
				&& sistemaParametro.getIndicadorCobrancaJudical()
					.compareTo(ConstantesSistema.SIM)==0){
			indicadorCobrancaJudical = ConstantesSistema.SIM;
		}
		
		Integer numeroMesesAnterioresParaDeclaracaoQuitacao = sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao();
		
		Integer anoMesSubtraido = Util.subtrairMesDoAnoMes(anoMesArrecadacao, numeroMesesAnterioresParaDeclaracaoQuitacao);
		
		Date dataVerificacaoPagamentos = Util.gerarDataApartirAnoMesRefencia(anoMesSubtraido);
		
						
			Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

			Iterator iterator = colecaoRotasParaExecucao.iterator();

			while (iterator.hasNext()) {
				
				Rota rota = (Rota) iterator.next();

				System.out.println("ROTA GERAR DADOS QUITA��O ANUAL DE D�BITOS "
								+ rota.getId()
								+ "*********************************************************");
				

					enviarMensagemControladorBatch(
			                ConstantesJNDI.BATCH_GERAR_DECLARACAO_QUITACAO_ANUAL_DEBITOS,
			                new Object[] { 
			                		this.getIdFuncionalidadeIniciada(),
			                		anosParaGeracaoDeclaracao,
			                		rota,
			                		indicadorContaParcelada,
			                		indicadorCobrancaJudical,
			                		dataVerificacaoPagamentos,
			                		sistemaParametro
			                		});			
			}	
		
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
		AgendadorTarefas.agendarTarefa(
				"BatchGerarDadosDeclaracaoQuitacaoAnualDebitos", this);

	}

}
