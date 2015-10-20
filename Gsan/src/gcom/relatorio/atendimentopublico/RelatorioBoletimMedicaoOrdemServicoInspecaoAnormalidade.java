package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.atendimentopublico.FiltrarOrdensServicosComandoOrdemSeletivaHelper;
import gcom.gui.relatorio.atendimentopublico.GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidade extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidade(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_COMANDO_OS_SELETIVA);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String mesAnoApuracao = (String) getParametro("mesAnoApuracao");
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();

		FiltrarOrdensServicosComandoOrdemSeletivaHelper relatorioHelper = 
			(FiltrarOrdensServicosComandoOrdemSeletivaHelper) getParametro("filtrarRelatorioBoletimMedicaoOSSeletiva");
		
		List<GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean> beans = null;
		beans = Fachada.getInstancia().pesquisarRelatorioOrdensServicosComandoOrdemSeletiva(relatorioHelper);

		byte[] retorno = null;
		
		if (beans == null || beans.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}else{
			Map<String, String> parametros = new HashMap<String, String>();
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
			parametros.put("numeroRelatorio", "R1221");
			parametros.put("mesAnoApuracao", mesAnoApuracao);
			
			RelatorioDataSource ds = new RelatorioDataSource((List<GerarRelatorioBoletimMedicaoOrdemServicoInspecaoAnormalidadeBean>) beans);
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_COMANDO_OS_SELETIVA, parametros, ds, tipoFormatoRelatorio);
			try {
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_MEDICAO_COMANDO_OS_SELETIVA, idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
		}
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {

	}
}