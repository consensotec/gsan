package gcom.relatorio.mobile.execucaoOrdemServico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1498] Consultar Arquivo Texto de Ordens de Serviço para Smartphone
 * 
 * @author Jean Varela
 * @date 14/12/2015
 */

public class RelatorioConsultarArquivoTextoOrdensServicoSmartphone extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioConsultarArquivoTextoOrdensServicoSmartphone(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONSULTAR_ARQUIVO_TEXTO_ORDENS_SERVICO_SMARTPHONE);
	}	

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		Fachada fachada = Fachada.getInstancia();
		byte[] retorno = null;
		
		Integer tipoRelatorio = Integer.parseInt((String) getParametro("escolhaTipoRelatorio"));
		Integer tipoFiltro = (Integer) getParametro("tipoFiltro");

		String grupoCobranca = (String) getParametro("grupoCobranca");
		String nomeEmpresa = (String) getParametro("empresa");
		String mesAnoCronograma = (String) getParametro("mesAnoCronograma");
		String servicoTipo = (String) getParametro("servicoTipo");
		String nomeAgenteComercial = (String) getParametro("nomeAgenteComercial");
		String situacaoArquivo = (String) getParametro("situacaoArquivo");

		List<RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean> listBean = (List<RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean>) getParametro("list");

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		if (Util.isVazioOrNulo(listBean)) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}
//		
//		listBean = new ArrayList<RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean>();
//		listBean.add(new RelatorioConsultarArquivoTextoOrdensServicoSmartphoneBean(new ArquivoTxtOSCobrancaSmartphoneHelper()));
//			
		Map parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("empresa", nomeEmpresa);
		parametros.put("agenteComercial",nomeAgenteComercial);
		parametros.put("servicoTipo",servicoTipo);
		parametros.put("situacaoTransmissao",situacaoArquivo);
		parametros.put("grupoCobranca",grupoCobranca);
		parametros.put("mesAnoCronograma",mesAnoCronograma);
		
		List relatorioBeans = (ArrayList) listBean;

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTAR_ARQUIVO_TEXTO_ORDENS_SERVICO_SMARTPHONE,
							     parametros, ds, tipoRelatorio);

		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_CONSULTAR_ARQUIVO_TEXTO_ORDENS_SERVICO_SMARTPHONE,
					                    idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
	}
}
