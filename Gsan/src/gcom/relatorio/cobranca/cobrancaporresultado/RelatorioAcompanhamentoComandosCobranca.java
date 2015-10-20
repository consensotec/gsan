package gcom.relatorio.cobranca.cobrancaporresultado;

import gcom.batch.Relatorio;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1238] Gerar Relatório de Acompanhamento dos Comandos de Cobrança
 * 
 * @author Mariana Victor
 * @date 08/11/2011
 */
public class RelatorioAcompanhamentoComandosCobranca extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioAcompanhamentoComandosCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_COMANDOS_COBRANCA);
	}
	
	public Object executar() throws TarefaException {
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
        Fachada fachada = Fachada.getInstancia();
        
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");

		Date dataInicialFormatada = Util.converteStringParaDate(dataInicial);
		Date dataFinalFormatada = Util.converteStringParaDate(dataFinal);

		List<RelatorioAcompanhamentoComandosCobrancaBean> colecaoBean = fachada
				.obterDadosAcompanhamentoComandosCobranca(
					idEmpresa, dataInicialFormatada, dataFinalFormatada);
		
		// [FS0002] - Nenhum registro encontrado
		if(colecaoBean.size() == 0){
        	throw new ActionServletException("atencao.nao_existem_registros_periodo");
        }
		
		// valor de retorno
		byte[] retorno = null;

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
		Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		// Parâmetros do relatório
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("dataInicial", dataInicial);
		parametros.put("dataFinal", dataFinal);
		parametros.put("empresa", empresa.getDescricao());
		
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBean);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_COMANDOS_COBRANCA,
								parametros, ds, TarefaRelatorio.TIPO_PDF);
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_ACOMPANHAMENTO_COMANDOS_COBRANCA,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");

		Date dataInicialFormatada = Util.converteStringParaDate(dataInicial);
		Date dataFinalFormatada = Util.converteStringParaDate(dataFinal);
		
		Integer quantidade = Fachada.getInstancia().pesquisarQuantidadeComandosContasCobrancaEmpresa(
			idEmpresa, dataInicialFormatada, dataFinalFormatada);
		
		if (quantidade == null) {
			quantidade = 0;
		}
		
		return quantidade;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoComandosCobranca", this);
	}

}
