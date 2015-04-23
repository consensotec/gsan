package gsan.gui.cadastro.imovel;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gsan.batch.Relatorio;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.seguranca.RelatorioAlteracoesSistemaColunaBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ControladorException;
import gsan.util.Util;

/**
 * [UC1373] Gerar Relatório Histórico Imóveis Programa Especial.
 * 
 * @author Jonathan Marcos
 * @date 06/05/2013
 *  
 */

public class RelatorioHistoricoImoveisProgramaEspecial extends TarefaRelatorio {
	
	public RelatorioHistoricoImoveisProgramaEspecial(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_HISTORICO_IMOVEIS_PROGRAMA_ESPECIAL);
	}
	
	@Deprecated
	public RelatorioHistoricoImoveisProgramaEspecial() {
		super(null, "");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object executar() throws TarefaException {
		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper filtro = 
			(FiltrarRelatorioHistoricoImoveisProgramaEspecialHelper) getParametro("filtrarRelatorioHistoricoImoveisProgramaEspecialHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans =  new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioHistoriocoImoveisProgramaEspecialHelper> colecao =   
			fachada.pesquisarRelatorioHistoriocoImoveisProgramaEspecial(filtro);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioHistoriocoImoveisProgramaEspecialHelper helper = 
					(RelatorioHistoriocoImoveisProgramaEspecialHelper) colecaoIterator.next();
				
				RelatorioHistoriocoImoveisProgramaEspecialBean relatorioAnormalidadeImoveisCorrigidosBean = 
					new RelatorioHistoriocoImoveisProgramaEspecialBean(helper);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioAnormalidadeImoveisCorrigidosBean);				
				
				
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1373");
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(Util.isVazioOrNulo(relatorioBeans)){
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			RelatorioAlteracoesSistemaColunaBean relatorioAlteracoesSistemaColunaBean = 
					new RelatorioAlteracoesSistemaColunaBean();
				
				relatorioBeans.add(relatorioAlteracoesSistemaColunaBean);
				
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		
		}else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_HISTORICO_IMOVEIS_PROGRAMA_ESPECIAL,
				parametros, ds, tipoFormatoRelatorio);
							
		}
		
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_HISTORICO_IMOVEIS_PROGRAMA_ESPECIAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub
		
	}

}
