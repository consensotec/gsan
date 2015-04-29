package gsan.relatorio.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.atendimentopublico.bean.AcessoLojaVirtualHelper;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1275] Gerar Relat�rio Quantidade de Acessos Loja Virtual
 * 
 * @author Fl�vio Ferreira
 * @date 01/10/2013
 * 
 */
public class RelatorioAcessoLojaVirtual extends TarefaRelatorio{

	private static final long serialVersionUID = 1L;
	
	public RelatorioAcessoLojaVirtual(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_ACESSO_LOJA_VIRTUAL);
	}
	
	public RelatorioAcessoLojaVirtual(){
		super(null, "");
	}
	
	public Object executar() throws TarefaException{
		
		String periodoInicial = (String) getParametro("periodoInicial");
		String periodoFinal = (String) getParametro("periodoFinal");
		String tipoAtendimento = (String) getParametro("tipoAtendimento");
		String indicadorServicoExecutado = (String) getParametro("indicadorServicoExecutado");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoformatoRelatorio");
		Collection colecaoLojaVirtualHelper = (Collection)  getParametro("colecaoLojaVirtualHelper");
		
		// valor de Retorno
		byte[] retorno = null;
		
		// colecao de beans do  Relatorio
		List relatorioBeans = new ArrayList();
		
		RelatorioAcessoLojaVirtualBean relatorioBean = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(colecaoLojaVirtualHelper != null && !colecaoLojaVirtualHelper.equals("")){
			
			// coloca a cole��o de par�metros da analise no iterator
			Iterator<AcessoLojaVirtualHelper> helperIterator = colecaoLojaVirtualHelper.iterator();
			
			// la�o para criar a cole��o de par�metros da analise
			while(helperIterator.hasNext()){
				
				AcessoLojaVirtualHelper helper = (AcessoLojaVirtualHelper) helperIterator.next();
				
				relatorioBean = new RelatorioAcessoLojaVirtualBean(helper.getTipoAtendimento(), helper.getQuantidade(), helper.getQtdServicosExecutados());
				
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}else{
			
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		// Par�metros do relat�rio
		Map parametro = new HashMap();
		

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParamntro = fachada.pesquisarParametrosDoSistema();
		
		parametro.put("imagem", sistemaParamntro.getImagemRelatorio());
		parametro.put("periodoInicial", periodoInicial);
		parametro.put("periodoFinal", periodoFinal);
		parametro.put("indicadorServicoExecutado", indicadorServicoExecutado);
		
		if(tipoAtendimento != null && !tipoAtendimento.equals("-1")){
			
			AcessoLojaVirtualHelper acessoLojaVirtualHelper = new AcessoLojaVirtualHelper();
			acessoLojaVirtualHelper.setTipoAtendimento(tipoAtendimento);
			
			parametro.put("tipoAtendimento", acessoLojaVirtualHelper.getTipoAtendimento());
		}else{
			parametro.put("tipoAtendimento", "");
		}
		
		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_ACESSO_LOJA_VIRTUAL, parametro, ds, tipoFormatoRelatorio);
		
		return retorno;
  	}
	
	public int calcularTotalRegistrosRelatorio(){
		
		int retorno = 1;
		
		return retorno;
	}
	
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAcessoLojaVirtual", this);
	}
	
}
