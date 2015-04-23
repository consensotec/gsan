package gsan.gui.relatorio.seguranca;

import gsan.fachada.Fachada;
import gsan.gui.seguranca.parametrosistema.ParametroSistemaActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.seguranca.RelatorioFiltroParametroSistemaNovo;
import gsan.seguranca.acesso.FiltroModulo;
import gsan.seguranca.acesso.Modulo;
import gsan.seguranca.parametrosistema.FiltroParametroTipo;
import gsan.seguranca.parametrosistema.ParametroTipo;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioFiltroParametroSistemaNovoAction extends
			ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;
		
		ParametroSistemaActionForm form = (ParametroSistemaActionForm) actionForm;
		
		
		String codigoConstante = form.getCodigoConstanteParametroSistema();
		String nome = form.getNomeParametroSistema();
		String tipo = form.getParametroTipo();
		String modulo = form.getModuloParametroSistema();
		String indicadorRestrito = form.getParametroRestritoParametroSistema();	
		String indicadorUso = form.getIndicadorUsoParametroSistema();
		
		Fachada fachada = Fachada.getInstancia();
		
		Modulo moduloParametro = new Modulo();
		
		if(modulo.equals("-1") || modulo == null){
			modulo = "";
		}else {
			
			FiltroModulo filtro = new FiltroModulo();
			filtro.adicionarParametro(new ParametroSimples(FiltroModulo.ID, modulo));					
			Collection colecaoModulo = fachada.pesquisar(filtro, Modulo.class.getName());
			
			if (colecaoModulo != null && !colecaoModulo.isEmpty()) {
				moduloParametro = (Modulo) Util.retonarObjetoDeColecao(colecaoModulo);
			}
			
			modulo = moduloParametro.getDescricaoModulo();
			
		}
		
		ParametroTipo parametroTipo = new ParametroTipo();
		
		if(tipo.equals("-1") || tipo == null){
			tipo = "";
		}else {
			
			FiltroParametroTipo filtro = new FiltroParametroTipo();
			filtro.adicionarParametro(new ParametroSimples(FiltroParametroTipo.ID, tipo));					
			Collection colecaoParametroTipo = fachada.pesquisar(filtro, ParametroTipo.class.getName());
			
			if (colecaoParametroTipo != null && !colecaoParametroTipo.isEmpty()) {
				parametroTipo = (ParametroTipo) Util.retonarObjetoDeColecao(colecaoParametroTipo);
			}
			
			tipo = parametroTipo.getDescricao();
			
		}
		
		
		
		if(indicadorUso.equals("1")){
			indicadorUso = "ATIVO";
		}else if (indicadorUso.equals("2")){
			indicadorUso = "INATIVO";
		}else{
			indicadorUso = "TODOS";
		}
		
		if(indicadorRestrito.equals("1")){
			indicadorRestrito = "SIM";
		}else if(indicadorRestrito.equals("2")){
			indicadorRestrito = "NÃO";
		}else{
			indicadorRestrito = "TODOS";
		}
		
		RelatorioFiltroParametroSistemaNovo relatorioFiltroParametroSistema = new RelatorioFiltroParametroSistemaNovo(
				getUsuarioLogado(httpServletRequest));
		
		relatorioFiltroParametroSistema.addParametro("colecaoParametroSistema", 
				httpServletRequest.getSession().getAttribute("colecaoParametrosRelatorio"));
		
		String tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		
		relatorioFiltroParametroSistema
			.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
		
		relatorioFiltroParametroSistema
		.addParametro("nome", nome);
		
		relatorioFiltroParametroSistema
		.addParametro("codigoConstante", codigoConstante);
		
		relatorioFiltroParametroSistema
		.addParametro("parametroTipo", tipo);
		
		relatorioFiltroParametroSistema
		.addParametro("modulo", modulo);
		
		relatorioFiltroParametroSistema
		.addParametro("indicadorRestrito", indicadorRestrito);
		
		relatorioFiltroParametroSistema
		.addParametro("indicadorUso", indicadorUso);
		
		retorno = processarExibicaoRelatorio(relatorioFiltroParametroSistema,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);
		
		return retorno;
	}

}
