package gcom.relatorio.micromedicao;

import gcom.batch.Relatorio;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1054] Gerar Relat�rio Boletim de Medi��o
 * [SB0002 - Gerar TXT]
 * 
 * @autor Mariana Victor
 * @data 23/02/2011
 */
public class RelatorioBoletimMedicaoArquivoTxt extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimMedicaoArquivoTxt(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT);
	}

	@Deprecated
	public RelatorioBoletimMedicaoArquivoTxt() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioBoletimMedicaoHelper relatorioHelper = 
			(FiltrarRelatorioBoletimMedicaoHelper) getParametro("filtrarRelatorioBoletimMedicaoHelper");
		
		String mesAno = (String)getParametro("mesAno");
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecao = fachada.pesquisarRelatorioBoletimMedicao(relatorioHelper);
		
		retorno = fachada.emitirBoletimMedicao(colecao, mesAno);

		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 2;
   
		if (retorno == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao
			// usu�rio;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBoletimMedicaoArquivoTxt", this);
	}

}
