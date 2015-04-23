package gsan.gui.relatorio.faturamento;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.conta.ImpostoDeduzidoHelper;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.faturamento.RelatorioImpostosPorClienteResponsavelBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
		List<RelatorioImpostosPorClienteResponsavelBean> relatorioBeans = this.inicializarBeansRelatorio(helpersRelatorio, tipoRelatorio);

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
		parametros.put("tipoRelatorio",tipoRelatorio);
				
		if (relatorioBeans != null && !relatorioBeans.isEmpty()) {
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
			
			if(tipoRelatorio.equalsIgnoreCase("ANALÍTICO") && tipoImposto.equalsIgnoreCase("DA ARRECADAÇÃO")){
				retorno = this.gerarRelatorio(
							ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL_ANALITICO_ARRECADACAO,
							parametros, ds, tipoFormatoRelatorio);
			}else{
				retorno = this.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_IMPOSTOS_POR_CLIENTE_RESPONSAVEL,
						parametros, ds, tipoFormatoRelatorio);
			}
			
		} else {
			/*
			 * Adicionado por: Mariana Victor - 24/02/2011
			 */
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
			throw new TarefaException(	"Erro ao gravar relatório no sistema",
										e);
		}
		// ------------------------------------
				
		return retorno;
	}
	
	/**
	 * Processa os registros trazidos do banco
	 * 28/11/2012
	 * 
	 * @author Erivan Sousa
	 * @param helpersRelatorio
	 * @param relatorioTipo
	 * 
	 * @return List
	 */
	protected List<RelatorioImpostosPorClienteResponsavelBean> inicializarBeansRelatorio(
			Collection<ImpostoDeduzidoHelper> helpersRelatorio, String relatorioTipo) {
		// Inicializa o bean do relatorio
		List<RelatorioImpostosPorClienteResponsavelBean> relatorioBeans = new ArrayList<RelatorioImpostosPorClienteResponsavelBean>();

		if (relatorioTipo.equalsIgnoreCase("sintetico")) {

			if (helpersRelatorio != null && !helpersRelatorio.isEmpty()) {

				for (Iterator<ImpostoDeduzidoHelper> iter = helpersRelatorio.iterator(); iter.hasNext();) {

					ImpostoDeduzidoHelper elementoHelper = (ImpostoDeduzidoHelper) iter.next();

					// cria o novo bean - cada bean representa um imposto por
					// cliente e por fatura no relatorio
					RelatorioImpostosPorClienteResponsavelBean relatorioBean = new RelatorioImpostosPorClienteResponsavelBean();

					if (elementoHelper.getIdCliente() != null && elementoHelper.getIdCliente() != null) {
						relatorioBean.setClienteIdNome(elementoHelper.getIdCliente() + " - "
								+ elementoHelper.getNomeCliente());
					}

					if (elementoHelper.getCnpjCliente() != null) {
						relatorioBean.setCnpjCliente(elementoHelper.getCnpjCliente());
					}

					if (elementoHelper.getValorFatura() != null) {
						relatorioBean.setValorFatura(elementoHelper.getValorFatura());
					}

					if (elementoHelper.getIdImpostoTipo() != null) {
						relatorioBean.setIdImpostoTipo(elementoHelper.getIdImpostoTipo());
					}

					if (elementoHelper.getDescricaoImposto() != null) {
						relatorioBean.setDescricaoImposto(elementoHelper.getDescricaoImposto());
					}

					if (elementoHelper.getPercentualAliquota() != null) {
						relatorioBean.setPercentualAliquota(Util.formataBigDecimal(elementoHelper.getPercentualAliquota(), 2, true));
					}

					if (elementoHelper.getValor() != null) {
						relatorioBean.setValorImposto(elementoHelper.getValor());
					}
					
					if (elementoHelper.getBaseCalculo() != null) {
						relatorioBean.setBaseCalculo(elementoHelper.getBaseCalculo());
					}

					relatorioBeans.add(relatorioBean);
				}
			}

		} else if (relatorioTipo.equalsIgnoreCase("analitico")) {

			if (helpersRelatorio != null && !helpersRelatorio.isEmpty()) {

				for (Iterator<ImpostoDeduzidoHelper> iter = helpersRelatorio.iterator(); iter.hasNext();) {

					ImpostoDeduzidoHelper elementoHelper = (ImpostoDeduzidoHelper) iter.next();

					RelatorioImpostosPorClienteResponsavelBean relatorioBean = new RelatorioImpostosPorClienteResponsavelBean();

					if (elementoHelper.getIdCliente() != null && elementoHelper.getIdCliente() != null) {
						relatorioBean.setClienteIdNome(elementoHelper.getIdCliente() + " - "
								+ elementoHelper.getNomeCliente());
					}

					if (elementoHelper.getCnpjCliente() != null) {
						relatorioBean.setCnpjCliente(Util.formatarCnpj(elementoHelper.getCnpjCliente()));
					}

					if (elementoHelper.getValorFatura() != null) {
						relatorioBean.setValorFatura(elementoHelper.getValorFatura());
					}

					if (elementoHelper.getIdImpostoTipo() != null) {
						relatorioBean.setIdImpostoTipo(elementoHelper.getIdImpostoTipo());
					}

					if (elementoHelper.getDescricaoImposto() != null) {
						relatorioBean.setDescricaoImposto(elementoHelper.getDescricaoImposto());
					}

					if (elementoHelper.getPercentualAliquota() != null) {
						relatorioBean.setPercentualAliquota(Util.formataBigDecimal(elementoHelper.getPercentualAliquota(), 2, true));
					}

					if (elementoHelper.getValor() != null) {
						relatorioBean.setValorImposto(elementoHelper.getValor());
					}

					if (elementoHelper.getIdImovel() != null) {
						relatorioBean.setImovelID(Util.retornaMatriculaImovelFormatada(elementoHelper.getIdImovel()));
					}
					
					if (elementoHelper.getBaseCalculo() != null) {
						relatorioBean.setBaseCalculo(elementoHelper.getBaseCalculo());
					}	
					
					if (elementoHelper.getIdConta() != null) {
						relatorioBean.setIdConta(elementoHelper.getIdConta().toString());
					}
					
					if (elementoHelper.getAnoMesReferencia() != null) {
						relatorioBean.setAnoMesReferenciaConta(Util.formatarAnoMesParaMesAno(elementoHelper.getAnoMesReferencia()));
					}
					
					relatorioBeans.add(relatorioBean);
				}
			}
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
