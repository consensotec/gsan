package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.contratoadesao.ContratoAdesao;
import gcom.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.atendimentopublico.RelatorioFiltroContratoAdesaoBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioFiltroContratoAdesao extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	public RelatorioFiltroContratoAdesao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRO_CONTRATO_ADESAO);
	}
	
	@Deprecated
	public RelatorioFiltroContratoAdesao() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object executar() throws TarefaException {
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		byte[] retorno = null;
		
		FiltroContratoAdesao filtroContratoAdesao = new FiltroContratoAdesao();
		filtroContratoAdesao.setCampoOrderBy(filtroContratoAdesao.ID);		
		
		Collection colecaoContratoAdesao = Fachada.getInstancia().
				pesquisar(filtroContratoAdesao, ContratoAdesao.class.getName());
		
		RelatorioFiltroContratoAdesaoBean relatorioFiltroContratoAdesaoBean = null;
		
		Iterator iterator = colecaoContratoAdesao.iterator();
		
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		ContratoAdesao contratoAdesao = null;
		while(iterator.hasNext()){
			contratoAdesao = (ContratoAdesao) iterator.next();
			relatorioFiltroContratoAdesaoBean = new RelatorioFiltroContratoAdesaoBean(
					contratoAdesao.getId().toString(), contratoAdesao.getDescricao());
			relatorioBeans.add(relatorioFiltroContratoAdesaoBean);
		}
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().
				pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_FILTRO_CONTRATO_ADESAO,
				parametros, ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFiltroContratoAdesao", this);
	}

}
