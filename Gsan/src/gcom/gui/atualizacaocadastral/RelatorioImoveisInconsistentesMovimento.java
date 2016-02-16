package gcom.gui.atualizacaocadastral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC 1297] - Relatório Imóveis Inconsistentes - Atualização Cadastral
 * 
 * @author Davi Menezes
 * @author Bruno Sá Barreto
 * 
 * @date 23/03/2012
 *
 */
public class RelatorioImoveisInconsistentesMovimento extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioImoveisInconsistentesMovimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_INCONSISTENTES_MOVIMENTO);
	}
	
	@Deprecated
	public RelatorioImoveisInconsistentesMovimento(){
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		//valor de Retorno
		byte retorno[] = null;
		
		String nomeEmpresa = (String) getParametro("empresa");
		String descricaoLocalidade = (String) getParametro("descricaoLocalidade");
		String descricaoSetor = (String) getParametro("descricaoSetor");
		String situacaoMovimento = (String) getParametro("situacaoMovimento");
		String tipoInconsistencia = (String) getParametro("tipoInconsistencia");
		
		String [] idsRegistro = (String []) getParametro("idsRegistro");
		String idLocalidade = (String) getParametro("idLocalidade");
		String codigoSetorComercial = (String) getParametro("codigoSetorComercial");
		String idCadastrador = (String) getParametro("idCadastrador");
		String indicadorSituacaoMovimento = (String) getParametro("indicadorSituacaoMovimento");
		String idTipoInconsistencia = (String) getParametro("idTipoInconsistencia");

		String numeroQuadras = (String) getParametro("numeroQuadras");
		
		int tipoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// coleção de beans do relatório
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		Collection<RelatorioImoveisInconsistentesMovimentoBean> colBeans = 
			Fachada.getInstancia().pesquisarRelatorioImoveisInconsistentesMovimento(idsRegistro,
					idLocalidade, codigoSetorComercial, numeroQuadras,
					idCadastrador, indicadorSituacaoMovimento, idTipoInconsistencia);
		
		if(!Util.isVazioOrNulo(colBeans)){
			Iterator<?> iterator = colBeans.iterator();
			while(iterator.hasNext()){
				RelatorioImoveisInconsistentesMovimentoBean bean = 
					(RelatorioImoveisInconsistentesMovimentoBean) iterator.next();
				relatorioBeans.add(bean);
			}
		}else{
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		// __________________________________________________________________
		
		// Parâmetros do relatório
		Map<Object, Object> parametros = new HashMap<Object, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		
		//Adiciona os parametros no relatório
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("descricaoLocalidade", descricaoLocalidade);
		parametros.put("descricaoSetor", descricaoSetor);
		
		if(!"-1".equals(numeroQuadras) && numeroQuadras != null){
			parametros.put("numeroQuadras", limitarNumeroQuadras(numeroQuadras));
		}else{
			parametros.put("numeroQuadras", "");
		}

		if(Util.parametroNumericoValido(idCadastrador) && !"-1".equals(idCadastrador)){		
			RelatorioImoveisInconsistentesMovimentoBean bean = (RelatorioImoveisInconsistentesMovimentoBean) relatorioBeans.get(0); 
			parametros.put("cadastrador", bean.getCadastrador());
		}else{
			parametros.put("cadastrador", "");
		}
		
		parametros.put("situacaoMovimento", situacaoMovimento);
		parametros.put("tipoInconsistencia", tipoInconsistencia);
		parametros.put("tipoRelatorio", "R1297");
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_IMOVEIS_INCONSISTENTES_MOVIMENTO, 
			parametros, ds, tipoRelatorio);
		
		//retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisInconsistentesMovimento", this);
	}

	/**
	 * Este método, limita a string formatada e adiciona ... caso todas as quadras não caibam
	 * no parâmetro que será passado pro relatório. 	
	 *
	 * @author Bruno Sá Barreto
	 * @date 30/12/2014
	 *
	 * @param idsQuadras
	 * @return numeroQuadrasFormatado
	 */
	private String limitarNumeroQuadras(String quadrasFormatado) {
		String result = "";
		if(quadrasFormatado.length()>50){
			result = quadrasFormatado.substring(0, 49);
			int ultimaVirgula = result.lastIndexOf(",");
			result = result.substring(0, ultimaVirgula);
			result+="...";
		}else{
			result = quadrasFormatado;
		}
		return result;
	}

}
