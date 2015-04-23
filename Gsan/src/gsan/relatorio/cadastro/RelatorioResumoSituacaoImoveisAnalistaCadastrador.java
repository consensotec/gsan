package gsan.relatorio.cadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public class RelatorioResumoSituacaoImoveisAnalistaCadastrador extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;
	
	public RelatorioResumoSituacaoImoveisAnalistaCadastrador(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RESUMO_SITUACAO_IMOVEIS_ANALISTA_CADASTRADOR);
	}
	
	@Deprecated
	public RelatorioResumoSituacaoImoveisAnalistaCadastrador(){
		super(null, "");
	}

	@Override
	public Object executar() throws TarefaException {
		
		//Valor de Retorno
		byte[] retorno = null;
		
		String nomeEmpresa = (String) getParametro("nomeEmpresa");
		String nomeGerencia = (String) getParametro("nomeGerencia");
		String nomeUnidadeNegocio = (String)getParametro("nomeUnidadeNegocio");
		String codSetorInicial = (String) getParametro("codigoSetorInicial");
		String quadraInicial = (String) getParametro("quadraInicial");	
		String cadastrador = (String) getParametro("cadastrador");
		String analista = (String) getParametro("analista");
		String tipoInconsistencia = (String) getParametro("tipoInconsistencia");

		
		Integer tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		DadosResumoMovimentoAtualizacaoCadastralHelper helper = 
				(DadosResumoMovimentoAtualizacaoCadastralHelper) getParametro("dadosRelatorio");
		
		// coleção de beans do relatório
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		Collection<RelatorioResumoSituacaoImoveisAnalistaCadastradorBean> colResumos =
			Fachada.getInstancia().pesquisarResumoSituacaoImoveis(helper);
		
		if(Util.isVazioOrNulo(colResumos)){
			throw new ActionServletException("atencao.relatorio.vazio");
		}else{
			Iterator<?> iterator = colResumos.iterator();
			while(iterator.hasNext()){
				RelatorioResumoSituacaoImoveisAnalistaCadastradorBean bean = 
					(RelatorioResumoSituacaoImoveisAnalistaCadastradorBean) iterator.next();
				
				relatorioBeans.add(bean);
			}
		}
		
		//Parametros do Relatorio
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("nomeGerencia", nomeGerencia);	
		parametros.put("nomeUnidadeNegocio",nomeUnidadeNegocio);		
		parametros.put("codigoSetorInicial", codSetorInicial);		
		parametros.put("quadraInicial", quadraInicial);		
		parametros.put("cadastrador", cadastrador);
		parametros.put("analista", analista);
		parametros.put("tipoInconsistencia", tipoInconsistencia);
		parametros.put("tipoFormatoRelatorio", "R1312");
		
		//Cria uma instancia do dataSource do Relatorio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RESUMO_SITUACAO_IMOVEIS_ANALISTA_CADASTRADOR, 
				parametros, ds, tipoFormatoRelatorio);
		
		//Retorna o relatório gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoSituacaoImoveisAnalistaCadastrador", this);
	}
	
}
