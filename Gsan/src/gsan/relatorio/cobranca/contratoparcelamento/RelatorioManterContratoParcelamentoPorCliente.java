package gsan.relatorio.cobranca.contratoparcelamento;

import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.MaiorQue;
import gsan.util.filtro.MenorQue;
import gsan.util.filtro.ParametroNaoNulo;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Paulo Diniz
 * @param <dataImplantacaoInicial>
 * @date 25/03/2011
 * 
 */
public class RelatorioManterContratoParcelamentoPorCliente<dataImplantacaoInicial> extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioManterContratoParcelamentoPorCliente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE);
	}
	public Object executar() throws TarefaException {

		FiltroContratoParcelamentoCliente filtroContratoParcelamentoCliente = (FiltroContratoParcelamentoCliente) getParametro("filtroContratoParcelamentoCliente"); 
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		List<RelatorioManterContratoParcelamentoPorClienteBean> beans = fachada.gerarRelatorioManterContratoParcelamentoPorCliente(filtroContratoParcelamentoCliente);
		
		RelatorioDataSource ds = new RelatorioDataSource(beans);
		
		String numeroContrato = "";
		String numeroContratoAnt = "";
		String clienteContrato = "";
		String usuarioResp = "";
		String dataNegociacaoInicial = "";
		String dataNegociacaoFinal = "";
		String dataImplantacaoInicial = "";
		String dataImplantacaoFinal = "";
		String situacaoPgto = "Todos";
		String situacaoCancel = "Todos";
		String dataCancelamentoInicial = "";
		String dataCancelamentoFinal = "";
		String motivoCancelamento = "";
		
		List parametrosFiltro = new ArrayList(filtroContratoParcelamentoCliente.getParametros());
		for (Object object : parametrosFiltro) {
			if(object instanceof ParametroSimples){
				ParametroSimples parametro = (ParametroSimples) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.NUMERO_CONTRATO)){
					numeroContrato = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.NUMERO_ANTERIOR)){
					numeroContratoAnt = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.ID_CLIENTE)){
					clienteContrato = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.USUARIO_RESPONSAVEL_ID)){
					usuarioResp = parametro.getValor() + "";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR)){
					situacaoPgto = "Pagos";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER)){
					motivoCancelamento += parametro.getValor() + ", ";
				}
			}else if(object instanceof MaiorQue){
				MaiorQue parametro = (MaiorQue) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CONTRATO)){
					Date data = (Date)parametro.getNumero();
					dataNegociacaoInicial = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_IMPLANTACAO)){
					Date data = (Date)parametro.getNumero();
					dataImplantacaoInicial = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.VALOR_A_COBRAR)){
					situacaoPgto = "Pendentes";
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO)){
					Date data = (Date)parametro.getNumero();
					dataCancelamentoInicial = (Util.formatarData(data));
				}
			}else if(object instanceof MenorQue){
				MenorQue parametro = (MenorQue) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CONTRATO)){
					Date data = (Date)parametro.getNumero();
					dataNegociacaoInicial = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_IMPLANTACAO)){
					Date data = (Date)parametro.getNumero();
					dataImplantacaoFinal = (Util.formatarData(data));
				}else if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO)){
					Date data = (Date)parametro.getNumero();
					dataCancelamentoFinal = (Util.formatarData(data));
				}
			}else if(object instanceof ParametroNulo){
				ParametroNulo parametro = (ParametroNulo) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER)){
					situacaoCancel = "N�o Cancelados";
				}
			}else if(object instanceof ParametroNaoNulo){
				ParametroNaoNulo parametro = (ParametroNaoNulo) object;
				if(parametro.getNomeAtributo().equals(FiltroContratoParcelamentoCliente.MOTIVO_DESFAZER)){
					situacaoCancel = "Cancelados";
				}
			}
		}
		
		if(!motivoCancelamento.equals("")){
			motivoCancelamento = motivoCancelamento.substring(0, motivoCancelamento.length() - 2);
		}
		
		parametros.put("numeroContrato", numeroContrato);
		parametros.put("numeroContratoAnt", numeroContratoAnt);
		parametros.put("clienteContrato", clienteContrato);
		parametros.put("usuarioResp", usuarioResp);
		parametros.put("dataNegociacaoInicial", dataNegociacaoInicial);
		parametros.put("dataNegociacaoFinal", dataNegociacaoFinal);
		parametros.put("dataImplantacaoInicial", dataImplantacaoInicial);
		parametros.put("dataImplantacaoFinal", dataImplantacaoFinal);
		parametros.put("situacaoPgto", situacaoPgto);
		parametros.put("situacaoCancel", situacaoCancel);
		parametros.put("dataCancelamentoInicial", dataCancelamentoInicial);
		parametros.put("dataCancelamentoFinal", dataCancelamentoFinal);
		parametros.put("motivoCancelamento", motivoCancelamento);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_CONTRATO_PARCELAMENTO_POR_CLIENTE,
		parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterContratoParcelamentoPorCliente",
				this);
	}
}
