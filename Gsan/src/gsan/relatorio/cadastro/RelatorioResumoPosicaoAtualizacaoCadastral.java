package gsan.relatorio.cadastro;

import gsan.cadastro.atualizacaocadastral.bean.DadosResumoMovimentoAtualizacaoCadastralHelper;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioResumoPosicaoAtualizacaoCadastral extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;
	
	public RelatorioResumoPosicaoAtualizacaoCadastral(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_POSICAO_ATUALIZACAO_CADASTRAL);
	}
	
	@Deprecated
	public RelatorioResumoPosicaoAtualizacaoCadastral(){
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		//Valor de Retorno
		byte[] retorno = null;
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String nomeGerencia = (String) getParametro("nomeGerencia");
		String nomeUnidade = (String) getParametro("nomeUnidadeNegocio");
		String codSetorInicial = (String) getParametro("codigoSetorInicial");
		String quadraInicial = (String) getParametro("quadraInicial");
		String cadastrador = (String) getParametro("cadastrador");
		String analista = (String) getParametro("analista");
		String tipoInconsistencia = (String) getParametro("tipoInconsistencia");
		String periodoInicial = (String) getParametro("periodoInicial"); 
		String periodoFinal = (String) getParametro("periodoFinal");
		String localidadeInicial = (String) getParametro("localidadeInicial");

		
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		DadosResumoMovimentoAtualizacaoCadastralHelper helper = 
				(DadosResumoMovimentoAtualizacaoCadastralHelper) getParametro("dadosRelatorio");

		// colecao de beans do relatorio
		List<RelatorioResumoPosicaoAtualizacaoCadastralBean> relatorioBeans =
				(List<RelatorioResumoPosicaoAtualizacaoCadastralBean>) Fachada.getInstancia().pesquisarResumoPosicaoAtualizacaoCadastral(helper);
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		//Parametros do Relatorio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relatorio
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("nomeGerencia", nomeGerencia);
		parametros.put("unidadeNegocio", nomeUnidade);
		parametros.put("codigoSetorInicial", codSetorInicial);	
		parametros.put("quadraInicial", quadraInicial);	
		parametros.put("cadastrador", cadastrador);
		parametros.put("analista", analista);
		parametros.put("periodoInicial", periodoInicial);
		parametros.put("periodoFinal", periodoFinal);
		parametros.put("localidadeInicial", localidadeInicial);		
		parametros.put("tipoInconsistencia", tipoInconsistencia);
		parametros.put("tipoFormatoRelatorio", "R1311");
		
		//Cria uma instancia do dataSource do Relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_POSICAO_ATUALIZACAO_CADASTRAL, 
				parametros, ds, tipoFormatoRelatorio);
		
		//Retorna o relatorio gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoPosicaoAtualizacaoCadastral", this);
	}
	
}