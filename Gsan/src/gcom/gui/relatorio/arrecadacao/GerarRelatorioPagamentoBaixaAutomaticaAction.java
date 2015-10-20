package gcom.gui.relatorio.arrecadacao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.pagamento.PagamentosBaixadosAutomaticamenteRelatorioHelper;
import gcom.relatorio.arrecadacao.pagamento.RelatorioPagamentoBaixaAutomaticaAnalitico;
import gcom.relatorio.arrecadacao.pagamento.RelatorioPagamentoBaixaAutomaticoSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Gerar Relatório de Pagamento de Baixa Automática
 * [UC1518]
 * 
 * @author Diogo Luiz
 * @created 09/07/2013
 */
public class GerarRelatorioPagamentoBaixaAutomaticaAction extends 
	ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);		
		
		GerarRelatorioPagamentoBaixaAutomaticaActionForm gerarRelatorioPagamentoBaixaAutomaticaActionForm = 
				(GerarRelatorioPagamentoBaixaAutomaticaActionForm) actionForm;
		
		String tipo = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getTipo();
		String idMatricula = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getidMatricula();
		String dataInicial = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getDataInicial();
		String dataFinal = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getDataFinal();
		String idGerenciaRegional = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIdGerenciaRegional();
		String idUnidadeNegocio = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIdUnidadeNegocio();
		String idLocalidade = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIdLocalidade();
		String idSetorComercial = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIdSetorComercial();
		String baixaAutomaticaPagamento = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getBaixaAutomaticaPagamento();
		String faixaDiferencaValoresInicial = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getFaixaDiferencaValoresInicial();
		String faixaDiferencaValoresFinal = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getFaixaDiferencaValoresFinal();
		String indicadorEstado = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIndicadorEstado();
		String indicadorGerenciaRegional = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIndicadorGerenciaRegional();
		String indicadorUnidadeNegocio = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIndicadorUnidadeNegocio();
		String indicadorLocalidade = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIndicadorLocalidade();
		String indicadorSetorComercial = gerarRelatorioPagamentoBaixaAutomaticaActionForm.getIndicadorSetorComercial();
		
		if((idMatricula == null || idMatricula.equals("")) && indicadorEstado == null && indicadorGerenciaRegional == null 
				&& indicadorUnidadeNegocio == null && indicadorLocalidade == null && indicadorSetorComercial == null){
			throw new ActionServletException("atencao.naoinformado",null, " Opção de Totalização ");
		}
		
		if(idMatricula != null && !idMatricula.equals("") && tipo != null && !tipo.equalsIgnoreCase("analitico")){
			throw new ActionServletException("atencao.relatorio_analitico_para_imovel");
		}
		
		if((dataInicial == null || dataInicial.equals(""))
				&& (dataFinal!= null || !dataFinal.equals(""))){
			throw new ActionServletException("atencao.informe_periodo",null,"inicial");
		}else if((dataFinal == null || dataFinal.equals(""))
				&& (dataInicial != null || !dataInicial.equals(""))){
			throw new ActionServletException("atencao.informe_periodo",null,"final");			
		}
		
		if (dataFinal != null && !dataFinal.equals("")
				&& dataInicial != null && !dataInicial.equals("")) {

				Date dtInicial = Util.converteStringParaDate(dataInicial);
				Date dtFinal = Util.converteStringParaDate(dataFinal);
				
				if(dtInicial.getTime() > dtFinal.getTime()){
					throw new ActionServletException("atencao.data.intervalo.invalido", null, "Data Invalida");
				}				
		}
		
		if(baixaAutomaticaPagamento == null || baixaAutomaticaPagamento.equals("")){
			throw new ActionServletException("atencao.naoinformado",null, " Baixa Automática de Pagamentos por");
		}		
		
		PagamentosBaixadosAutomaticamenteRelatorioHelper pagamentosBaixadosAutomaticamenteRelatorioHelper = 
				new PagamentosBaixadosAutomaticamenteRelatorioHelper();
		
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setPeriodoInicial(dataInicial);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setPeriodoFinal(dataFinal);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setIdImovel(idMatricula);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setGerenciaRegional(idGerenciaRegional);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setUnidadeNegocio(idUnidadeNegocio);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setLocalidade(idLocalidade);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setSetorComercial(idSetorComercial);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setBaixaAutomatica(baixaAutomaticaPagamento);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setFaixaInicial(faixaDiferencaValoresInicial);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setFaixaFinal(faixaDiferencaValoresFinal);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setAgrupamentoGerenciaRegional(indicadorGerenciaRegional);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setAgrupamentoUnidadeNegocio(indicadorUnidadeNegocio);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setAgrupamentoLocalidade(indicadorLocalidade);
		pagamentosBaixadosAutomaticamenteRelatorioHelper.setAgrupamentoSetorComercial(indicadorSetorComercial);
		
		String tipoGeracaoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoGeracaoRelatorio == null || tipoGeracaoRelatorio.equalsIgnoreCase("")) {
			tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}	
		
		TarefaRelatorio relatorio = null;
		
		if(tipo != null && tipo.equals("analitico")){ 
			relatorio = new RelatorioPagamentoBaixaAutomaticaAnalitico(
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorio.addParametro("periodoInicial", pagamentosBaixadosAutomaticamenteRelatorioHelper.getPeriodoInicial());
			relatorio.addParametro("periodoFinal", pagamentosBaixadosAutomaticamenteRelatorioHelper.getPeriodoFinal());
			relatorio.addParametro("gerenciaRegional", pagamentosBaixadosAutomaticamenteRelatorioHelper.getGerenciaRegional());
			relatorio.addParametro("unidadeNegocio", pagamentosBaixadosAutomaticamenteRelatorioHelper.getUnidadeNegocio());
			relatorio.addParametro("localidade", pagamentosBaixadosAutomaticamenteRelatorioHelper.getLocalidade());
			relatorio.addParametro("setorComercial", pagamentosBaixadosAutomaticamenteRelatorioHelper.getSetorComercial());
			relatorio.addParametro("baixaAutomatica", pagamentosBaixadosAutomaticamenteRelatorioHelper.getBaixaAutomatica());
			relatorio.addParametro("faixaInicial", pagamentosBaixadosAutomaticamenteRelatorioHelper.getFaixaInicial());
			relatorio.addParametro("faixaFinal", pagamentosBaixadosAutomaticamenteRelatorioHelper.getFaixaFinal());
			relatorio.addParametro("indicadorGerenciaRegional", indicadorGerenciaRegional);
			relatorio.addParametro("indicadorUnidadeNegocio", indicadorUnidadeNegocio);
			relatorio.addParametro("indicadorLocalidade", indicadorLocalidade);
			relatorio.addParametro("indicadorSetorComercial", indicadorSetorComercial);
			relatorio.addParametro("helper", pagamentosBaixadosAutomaticamenteRelatorioHelper);
			
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
		}else if(tipo != null && tipo.equals("sintetico")){
			relatorio = new RelatorioPagamentoBaixaAutomaticoSintetico(
							(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorio.addParametro("periodoInicial", pagamentosBaixadosAutomaticamenteRelatorioHelper.getPeriodoInicial());
			relatorio.addParametro("periodoFinal", pagamentosBaixadosAutomaticamenteRelatorioHelper.getPeriodoFinal());
			relatorio.addParametro("gerenciaRegional", pagamentosBaixadosAutomaticamenteRelatorioHelper.getGerenciaRegional());
			relatorio.addParametro("unidadeNegocio", pagamentosBaixadosAutomaticamenteRelatorioHelper.getUnidadeNegocio());
			relatorio.addParametro("localidade", pagamentosBaixadosAutomaticamenteRelatorioHelper.getLocalidade());
			relatorio.addParametro("setorComercial", pagamentosBaixadosAutomaticamenteRelatorioHelper.getSetorComercial());
			relatorio.addParametro("baixaAutomatica", pagamentosBaixadosAutomaticamenteRelatorioHelper.getBaixaAutomatica());
			relatorio.addParametro("faixaInicial", pagamentosBaixadosAutomaticamenteRelatorioHelper.getFaixaInicial());
			relatorio.addParametro("faixaFinal", pagamentosBaixadosAutomaticamenteRelatorioHelper.getFaixaFinal());
			relatorio.addParametro("indicadorGerenciaRegional", indicadorGerenciaRegional);
			relatorio.addParametro("indicadorUnidadeNegocio", indicadorUnidadeNegocio);
			relatorio.addParametro("indicadorLocalidade", indicadorLocalidade);
			relatorio.addParametro("indicadorSetorComercial", indicadorSetorComercial);
			relatorio.addParametro("helper", pagamentosBaixadosAutomaticamenteRelatorioHelper);
			
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
		}
		
		retorno = processarExibicaoRelatorio(
				relatorio, tipoGeracaoRelatorio, httpServletRequest,
				httpServletResponse, actionMapping);
		
		return retorno;		
		
	}
}
