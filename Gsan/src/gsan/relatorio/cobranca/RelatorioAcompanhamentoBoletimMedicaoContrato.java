package gsan.relatorio.cobranca;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
 * 
 * [SB0002] - Emitir Boletim de Contrato
 * 
 * @author Mariana Victor
 *
 * @date 22/11/2011
 */
public class RelatorioAcompanhamentoBoletimMedicaoContrato extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioAcompanhamentoBoletimMedicaoContrato(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public Object executar() throws TarefaException {
		FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper helper = (FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper) 
				getParametro("filtrarRelatorioAcompanhamentoBoletimMedicaoHelper");
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
		String definitivoSimulacao = (String) getParametro("definitivoSimulacao");
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String numeroContrato = (String) getParametro("numeroContrato");
		String dataReferencia = (String) getParametro("dataReferencia");
		String descricaoContrato = (String) getParametro("descricaoContrato");
		String pcTaxaSucesso = (String) getParametro("pcTaxaSucesso");
		String gruposIncluidos = (String) getParametro("gruposIncluidos");
		
		RelatorioAcompanhamentoBoletimMedicaoContratoHelper helperRelatorio = Fachada.getInstancia()
				.pesquisarRelatorioAcompanhamentoBoletimMedicaoContrato(helper);

		
		if(helperRelatorio == null || Util.isVazioOrNulo(helperRelatorio.getBeans())){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		Collection<RelatorioAcompanhamentoBoletimMedicaoContratoBean> beans = helperRelatorio.getBeans();
		
		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1178");
		
		parametros.put("tipoRelatorio", tipoFormatoRelatorio);
		parametros.put("definitivoSimulacao", definitivoSimulacao);
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("numeroContrato", numeroContrato);
		parametros.put("descricaoContrato", descricaoContrato);
		parametros.put("anoMesReferencia", dataReferencia);
		
		parametros.put("taxaSucesso", helperRelatorio.getTaxaSucesso());
		parametros.put("penalidadeOS", helperRelatorio.getPenalidadeOS());
		parametros.put("penalidadeFiscalizacao", helperRelatorio.getPenalidadeFiscalizacao());
		parametros.put("penalidadeNaoExecucao", helperRelatorio.getPenalidadeContratoNaoExecucao());
		parametros.put("penalidadeCorteSupressao", helperRelatorio.getPenalidadeCorteSupressaoIndevida());
		parametros.put("penalidadeNaoRealizacaoServicos", helperRelatorio.getPenalidadeNaoRealizacaoServico());
		parametros.put("valorDesconto", helperRelatorio.getValorDesconto());
		parametros.put("pcTaxaSucesso", pcTaxaSucesso);
		parametros.put("gruposIncluidos", gruposIncluidos);
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource(
				(List<RelatorioAcompanhamentoBoletimMedicaoContratoBean>) beans);
		
		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO_CONTRATO, 
				parametros, ds, tipoFormatoRelatorio);
			
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoBoletimMedicaoContrato", this);
	}

}
