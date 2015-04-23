package gsan.relatorio.cobranca.cobrancaporresultado;

import gsan.batch.Relatorio;
import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

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

	@Deprecated
	public RelatorioAcompanhamentoComandosCobranca() {
		super(null, "");
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
