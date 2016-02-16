package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioDocumentosAReceber extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioDocumentosAReceber(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTOS_A_RECEBER);
	}

	@Deprecated
	public RelatorioDocumentosAReceber() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		FiltroRelatorioDocumentosAReceberHelper helper 
			= (FiltroRelatorioDocumentosAReceberHelper) getParametro("filtro");
		
		Integer qtdRegistros = Fachada.getInstancia().countRelatorioDocumentosAReceber(helper);
		
		 if(qtdRegistros == 0){
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		 }
		
		return qtdRegistros;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDocumentosAReceber", this);

	}

	@Override
	public Object executar() throws TarefaException {


		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		FiltroRelatorioDocumentosAReceberHelper helper 
			= (FiltroRelatorioDocumentosAReceberHelper) getParametro("filtro");

		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoDados = fachada.pesquisarRelatorioDocumentosAReceber(helper);

		for (Iterator iterator = colecaoDados.iterator(); iterator.hasNext();) {
			RelatorioDocumentosAReceberBean bean = (RelatorioDocumentosAReceberBean) iterator.next();
			relatorioBeans.add(bean);
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("mesAno", Util.formatarAnoMesParaMesAno(helper.getMesAno()));

		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_DOCUMENTOS_A_RECEBER, parametros, ds,
				tipoFormatoRelatorio);
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		// ------------------------------------
		// Grava o relat�rio no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_DOCUMENTOS_A_RECEBER,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

}
