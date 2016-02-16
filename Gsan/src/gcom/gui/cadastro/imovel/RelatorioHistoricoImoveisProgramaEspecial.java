package gcom.gui.cadastro.imovel;


import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.seguranca.RelatorioAlteracoesSistemaColunaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC1373] Gerar Relat�rio Hist�rico Im�veis Programa Especial.
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

		// cole��o de beans do relat�rio
		List relatorioBeans =  new ArrayList();

		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioHistoriocoImoveisProgramaEspecialHelper> colecao =   
			fachada.pesquisarRelatorioHistoriocoImoveisProgramaEspecial(filtro);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecao.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {

				RelatorioHistoriocoImoveisProgramaEspecialHelper helper = 
					(RelatorioHistoriocoImoveisProgramaEspecialHelper) colecaoIterator.next();
				
				RelatorioHistoriocoImoveisProgramaEspecialBean relatorioAnormalidadeImoveisCorrigidosBean = 
					new RelatorioHistoriocoImoveisProgramaEspecialBean(helper);

				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioAnormalidadeImoveisCorrigidosBean);				
				
				
			}
		}
		// __________________________________________________________________

		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R1373");
		
		// cria uma inst�ncia do dataSource do relat�rio
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
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_HISTORICO_IMOVEIS_PROGRAMA_ESPECIAL,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		
	}

}
