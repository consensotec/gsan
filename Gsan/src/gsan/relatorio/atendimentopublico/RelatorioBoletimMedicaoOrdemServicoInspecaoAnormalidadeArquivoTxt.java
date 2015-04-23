package gsan.relatorio.atendimentopublico;

import gsan.batch.Relatorio;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.relatorio.atendimentopublico.FiltrarOrdensServicosComandoOrdemSeletivaHelper;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;

/**
 * [UC1221] – Gerar Boletim de Medição Ordem de Serviço Inspeção Anormalidade
 * 
 * @since 19/08/2011
 * @author Diogo Peixoto
 *
 */
public class RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeArquivoTxt extends TarefaRelatorio {

	public RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeArquivoTxt(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_COMANDO_OS_SELETIVA_ARQUIVO_TXT);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {

		byte[] retorno = null;

		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		FiltrarOrdensServicosComandoOrdemSeletivaHelper relatorioHelper = 
			(FiltrarOrdensServicosComandoOrdemSeletivaHelper) getParametro("filtrarRelatorioBoletimMedicaoOSSeletiva");
		
		retorno = Fachada.getInstancia().emitirRelatorioOrdensServicosComandoOrdemSeletivaTxt(relatorioHelper);

		if(retorno != null){
			try {
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_MEDICAO_COMANDO_OS_SELETIVA_ARQUIVO_TXT, idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {

	}

}
