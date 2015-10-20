package gcom.relatorio.seguranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.parametrosistema.FiltroParametroSistema;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioFiltroParametroSistemaNovo extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	public RelatorioFiltroParametroSistemaNovo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRO_PARAMETRO_SISTEMA_NOVO);
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
		
		
		String nome = (String) getParametro("nome");
		String tipo = (String) getParametro("parametroTipo");
		String modulo = (String) getParametro("modulo");
		String codigoConstante = (String) getParametro("codigoConstante");
		String indicadorRestrito = (String) getParametro("indicadorRestrito");
		String indicadorUso = (String) getParametro("indicadorUso");
		
		
		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
		filtroParametroSistema.adicionarCaminhoParaCarregamentoEntidade("parametroTipo");
		filtroParametroSistema.adicionarCaminhoParaCarregamentoEntidade("modulo");
		filtroParametroSistema.setCampoOrderBy(filtroParametroSistema.ID);		
		
		Collection colecaoParametroSistema = (Collection) getParametro("colecaoParametroSistema");
		
		RelatorioFiltroParametroSistemaNovoBean relatorioFiltroParametroSistemaBean = null;
		
		Iterator iterator = colecaoParametroSistema.iterator();
		
		List<Object> relatorioBeans = new ArrayList<Object>();
		
		ParametroSistema parametroSistema = null;
		while(iterator.hasNext()){
			parametroSistema = (ParametroSistema) iterator.next();
			relatorioFiltroParametroSistemaBean = new RelatorioFiltroParametroSistemaNovoBean(parametroSistema.getId().toString(),
				parametroSistema.getNome(),parametroSistema.getParametroTipo().getDescricao());
			relatorioBeans.add(relatorioFiltroParametroSistemaBean);
		}
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().
				pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nome", nome);
		parametros.put("codigoConstante", codigoConstante);
		parametros.put("tipo", tipo);
		parametros.put("modulo", modulo);
		parametros.put("indicadorRestrito", indicadorRestrito);
		parametros.put("indicadorUso", indicadorUso);
		
		
		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_FILTRO_PARAMETRO_SISTEMA_NOVO,
				parametros, ds, tipoFormatoRelatorio);
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFiltroParametroSistemaNovo", this);
	}

}
