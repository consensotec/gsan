package gcom.gui.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ImpostoDeduzidoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.faturamento.RelatorioImpostosPorClienteResponsavelBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * Essa classe é responsável por realizar a formatação
 * e lógica do relatório
 *
 * @author José Guilherme Macedo Vieira
 * @date 09/06/2009
 */
public class RelatorioImpostosPorClienteResponsavel extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;

	public RelatorioImpostosPorClienteResponsavel(Usuario usuario, String relatorioTipo) {
		super(usuario, relatorioTipo);
	}
	
	@Deprecated
	public RelatorioImpostosPorClienteResponsavel() {
		super(null,"");
	}
		
	@Override
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		// valor de retorno
		byte[] retorno = null;

		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String tipoRelatorio = ((String) getParametro("tipoRelatorio")).toLowerCase();
		
		String anoMesInicial = (String) getParametro("anoMesInicial");
		String anoMesFinal = (String) getParametro("anoMesFinal");
		
		Integer anoMesInicialTemp = Util.formatarMesAnoComBarraParaAnoMes(anoMesInicial);
		Integer anoMesFinalTemp = Util.formatarMesAnoComBarraParaAnoMes(anoMesFinal);
			
		String tipoImposto = (String) getParametro("tipoImposto");
		
		Integer clienteID = (Integer) getParametro("clienteID");	
		
		Fachada fachada = Fachada.getInstancia();
		
		
		Collection<ImpostoDeduzidoHelper>helpersRelatorio = null;
		
		//Caso o relatório a ser gerado seja do tipo faturamento
		if(tipoImposto.equals("1")){
			//pega todas as faturas com seus respectivos impostos e clientes
			helpersRelatorio = fachada.pesquisarImpostosPorClienteResponsavelFederal( 
				anoMesInicialTemp,anoMesFinalTemp, clienteID, tipoRelatorio);
			tipoImposto = "DO FATURAMENTO";
		//Caso o relatório a ser gerado seja do tipo arrecadação	
		}else if(tipoImposto.equals("2")){
			helpersRelatorio = fachada.pesquisarImpostosArrecadacaoClienteResponsavelFederal(
				anoMesInicialTemp, anoMesFinalTemp,clienteID, tipoRelatorio);
			tipoImposto = "DA ARRECADAÇÃO";
		}

		// coleção de beans do relatório
		List<RelatorioImpostosPorClienteResponsavelBean> relatorioBeans =
				this.inicializarBeansRelatorio(helpersRelatorio, tipoRelatorio);

		//Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Adiciona a imagem do relatorio identificando a empresa aos parâmetros
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMesInicial",anoMesInicial);
		parametros.put("anoMesFinal",anoMesFinal);
		parametros.put("tipoImposto", tipoImposto);
		
		if(tipoRelatorio != null && tipoRelatorio.equalsIgnoreCase("analitico")){
			tipoRelatorio = "ANALÍTICO";
		}else if(tipoRelatorio.equalsIgnoreCase("sintetico")){
			tipoRelatorio = "SINTÉTICO";
		}
		parametros.put("tipoRelatorio", tipoRelatorio);
				
		if (relatorioBeans != null && !relatorioBeans.isEmpty()) {
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			if(tipoRelatorio.equalsIgnoreCase("ANALÍTICO") && tipoImposto.equalsIgnoreCase("DA ARRECADAÇÃO")){
				retorno = this.gerarRelatorio(
							ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL_ANALITICO_ARRECADACAO,
							parametros, ds, tipoFormatoRelatorio);
			} else {
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL,
						parametros, ds, tipoFormatoRelatorio);
			}
			
		} else {
			// Adicionado por: Mariana Victor - 24/02/2011
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioImpostosPorClienteResponsavelBean relatorioBean = 
				new RelatorioImpostosPorClienteResponsavelBean();
				
			relatorioBeans.add(relatorioBean);
			
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMPOSTOS_CLIENTE_RESPONSAVEL, idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}

		return retorno;
	}

	/**
	 * Inicializa o bean do relatorio com os registros trazidos do banco
	 * 
	 * @author Erivan Sousa
	 * @date 28/11/2012
	 * 
	 * @author André Miranda
	 * @date 06/11/2015
	 * 
	 * @param helpersRelatorio
	 * @param relatorioTipo
	 * 
	 * @return List
	 */
	protected List<RelatorioImpostosPorClienteResponsavelBean> inicializarBeansRelatorio(
			Collection<ImpostoDeduzidoHelper> helpersRelatorio, String relatorioTipo) {
		boolean isAnalitico = relatorioTipo.equalsIgnoreCase("analitico");
		RelatorioImpostosPorClienteResponsavelBean bean;
		List<RelatorioImpostosPorClienteResponsavelBean> relatorioBeans = new ArrayList<RelatorioImpostosPorClienteResponsavelBean>();

		if (Util.isVazioOrNulo(helpersRelatorio)) {
			return relatorioBeans;
		}

		for (ImpostoDeduzidoHelper helper : helpersRelatorio) {
			// cria o novo bean - cada bean representa um imposto por cliente e
			// por fatura no relatorio
			bean = new RelatorioImpostosPorClienteResponsavelBean();

			bean.setValorFatura(helper.getValorFatura());
			bean.setIdImpostoTipo(helper.getIdImpostoTipo());
			bean.setDescricaoImposto(helper.getDescricaoImposto());
			bean.setValorImposto(helper.getValor());
			bean.setBaseCalculo(helper.getBaseCalculo());
			bean.setIdFatura(helper.getIdFatura());

			if (helper.getCnpjCliente() != null) {
				bean.setCnpjCliente(Util.formatarCnpj(helper.getCnpjCliente()));
			}

			if (helper.getReferenciaFatura() != null) {
				bean.setReferenciaFatura(Util.formatarAnoMesParaMesAno(helper.getReferenciaFatura()));
			}

			if (helper.getIdCliente() != null && helper.getNomeCliente() != null) {
				bean.setClienteIdNome(helper.getIdCliente() + " - " + helper.getNomeCliente());
			}

			if (helper.getPercentualAliquota() != null) {
				bean.setPercentualAliquota(Util.formataBigDecimal(helper.getPercentualAliquota(), 2, true));
			}

			if (isAnalitico) {
				if (helper.getIdImovel() != null) {
					bean.setImovelID(Util.retornaMatriculaImovelFormatada(helper.getIdImovel()));
				}

				if (helper.getIdConta() != null) {
					bean.setIdConta(helper.getIdConta().toString());
				}

				if (helper.getAnoMesReferencia() != null) {
					bean.setAnoMesReferenciaConta(Util.formatarAnoMesParaMesAno(helper.getAnoMesReferencia()));
				}
			}

			relatorioBeans.add(bean);
		}

		return relatorioBeans;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImpostosPorClienteResponsavel", this);
		
	}
}
