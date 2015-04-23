package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.contratoadesao.ContratoAdesao;
import gsan.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.atendimentopublico.RelatorioFiltroContratoAdesaoBean;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;
import gsan.util.filtro.ParametroSimples;

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
