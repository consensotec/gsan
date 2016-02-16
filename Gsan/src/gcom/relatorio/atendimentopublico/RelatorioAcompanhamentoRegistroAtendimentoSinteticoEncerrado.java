package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.atendimentopublico.registroatendimento.FiltrarAcompanhamentoRegistroAtendimentoHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe respons�vel por criar o relat�rio de Relatorio de Acompanhamento de Registro de Atendimento
 * 
 * [UC1056] - Gerar Relat�rio de Acompanhamento dos Registros de Atendimento
 * 
 * @author Hugo Leonardo
 *
 * @date 28/09/2010
 */
public class RelatorioAcompanhamentoRegistroAtendimentoSinteticoEncerrado extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioAcompanhamentoRegistroAtendimentoSinteticoEncerrado(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ENCERRADO);
	}

	@Deprecated
	public RelatorioAcompanhamentoRegistroAtendimentoSinteticoEncerrado() {
		super(null, "");
	}

	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		FiltrarAcompanhamentoRegistroAtendimentoHelper relatorioHelper = 
			(FiltrarAcompanhamentoRegistroAtendimentoHelper) getParametro("filtroHelper");
		// ------------------------------------
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String situacao = (String)  getParametro("situacao");
		
		String periodoAbertura = (String)  getParametro("periodoAbertura");
		
		String periodoEncerramento = (String)  getParametro("periodoEncerramento");
		
		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimentos = fachada.pesquisarRelatorioAcompanhamentoRASinteticoEncerrado(relatorioHelper);

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoRegistroAtendimentos != null && !colecaoRegistroAtendimentos.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator colecaoIterator = colecaoRegistroAtendimentos.iterator();
			
			//Map<String, RelatorioMapHelper> countMotivoEncerramentoMap = new HashMap<String, RelatorioMapHelper>();

			//RelatorioMapHelper relatorioMapHelper;
			
			Integer contador = 0;
			RelatorioAcompanhamentoRAHelper helper = null;
			
			// la�o para criar a cole��o de par�metros da analise
			while (colecaoIterator.hasNext()) {
				helper = (RelatorioAcompanhamentoRAHelper) colecaoIterator.next();

				relatorioBean = new RelatorioAcompanhamentoRegistroAtendimentoBean();
				
				relatorioBean.setDescricaoUnidadeAtendimento(helper.getDescricaoUnidadeAtendimento());
				relatorioBean.setMotivoEncerramento(helper.getMotivoEncerramento());
				relatorioBean.setQuantidadeTotal(helper.getQuantidade());
				relatorioBean.setDescricaoMunicipio(helper.getDescricaoMunicipio());
				contador += helper.getQuantidade();
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
			
			RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean1 = 
				new RelatorioAcompanhamentoRegistroAtendimentoBean();
			relatorioBean1.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			
			RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean2 = 
				new RelatorioAcompanhamentoRegistroAtendimentoBean();
			relatorioBean2.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			
			RelatorioAcompanhamentoRegistroAtendimentoBean relatorioBean3 = 
				new RelatorioAcompanhamentoRegistroAtendimentoBean();
			
			relatorioBean3.setDescricaoMunicipio(helper.getDescricaoMunicipio());
			relatorioBean3.setQuantidadeTotal(contador);
			relatorioBean3.setQuantidade("");
			relatorioBeans.add(relatorioBean1);
			relatorioBeans.add(relatorioBean2);
			relatorioBeans.add(relatorioBean3);
			
			/*
			
			RelatorioAcompanhamentoRegistroAtendimentoBean beanFooter;
			
			RelatorioMapHelper helper;
			
			for (Map.Entry<String, RelatorioMapHelper> entry : countMotivoEncerramentoMap.entrySet()) {
				
				String motivo = entry.getKey();
				helper = entry.getValue();

				beanFooter = new RelatorioAcompanhamentoRegistroAtendimentoBean();
				
				beanFooter.setDescricaoUnidadeAtendimento(helper.getDescricao());
				beanFooter.setMotivoEncerramento(motivo);
				beanFooter.setQuantidadeTotal(helper.getQuantidade());
				
				relatorioBeans.add(beanFooter);
			}
			
			*/
			
		}else{
			
			relatorioBean = new RelatorioAcompanhamentoRegistroAtendimentoBean();
			
			relatorioBeans.add(relatorioBean);
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		SistemaParametro sistemaParametro = fachada	.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("periodoAbertura", periodoAbertura);
		parametros.put("periodoEncerramento", periodoEncerramento);
		
		Integer idsituacao = new Integer(situacao);

		switch (idsituacao) {
			// Aberta
			case 0:
				parametros.put("situacao", "Aberta");
				break;
				
			// Encerrados
			case 1:
				parametros.put("situacao", "Encerrado");
				break;
				
			default:
				break;
		}

		// cria uma inst�ncia do dataSource do relat�rio
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(colecaoRegistroAtendimentos != null && colecaoRegistroAtendimentos.size() > 0){
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ENCERRADO,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
			
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ACOMPANHAMENTO_RA,
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
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 0;
		
		retorno = Fachada.getInstancia().pesquisarTotalRelatorioAcompanhamentoRAAnalitico(
				(FiltrarAcompanhamentoRegistroAtendimentoHelper) getParametro("filtroHelper"));
		   
		if (retorno == 0) {
			// Caso a pesquisa n�o retorne nenhum resultado comunica ao
			// usu�rio;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {

		AgendadorTarefas.agendarTarefa("RelatorioAcompanhamentoRegistroAtendimentoSinteticoEncerrado", this);
	}

}
