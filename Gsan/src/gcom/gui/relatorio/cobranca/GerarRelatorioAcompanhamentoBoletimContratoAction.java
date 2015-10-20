package gcom.gui.relatorio.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.CobrancaBoletimContrato;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.FiltroCobrancaBoletimContrato;
import gcom.cobranca.FiltroCobrancaBoletimMedicao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.relatorio.atendimentopublico.FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoBoletimMedicaoContrato;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
 * 
 * [SB0002] - Emitir Boletim de Contrato
 * 
 * @author Mariana Victor
 *
 * @date 22/11/2011
 */
public class GerarRelatorioAcompanhamentoBoletimContratoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		GerarEmitirBoletimMedicaoContratosForm form = (GerarEmitirBoletimMedicaoContratosForm) actionForm;
		
		String nomeRelatorio = ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO_CONTRATO;
		RelatorioAcompanhamentoBoletimMedicaoContrato relatorio = new RelatorioAcompanhamentoBoletimMedicaoContrato(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);

		FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper helper = new FiltrarRelatorioAcompanhamentoBoletimMedicaoHelper();
		
		String idEmpresa = form.getIdEmpresa();
		if(Util.verificarNaoVazio(idEmpresa)){
			helper.setIdEmpresa(Integer.valueOf(idEmpresa));
		}
		
		String idContrato = form.getIdContrato();
		if(Util.verificarNaoVazio(idContrato)){
			helper.setIdContratoEmpresaServico(Integer.valueOf(idContrato));
		}
		
		String mesAno = form.getMesAnoReferencia();
		if(Util.verificarNaoVazio(mesAno)){
			try{
				helper.setMesAnoReferencia(form.getMesAnoReferencia());
			}catch (NumberFormatException e) {
				throw new ActionServletException("atencao.mes_ano_referencia_invalido");
			}	
		}

		String idBoletimContrato = form.getIdBoletimContrato();
		if(Util.verificarNaoVazio(idBoletimContrato)){
			helper.setIdBoletimContrato(Integer.valueOf(idBoletimContrato));
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
		Collection<Empresa> empresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
		
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.ID, form.getIdContrato()));
		Collection<ContratoEmpresaServico> contratosEmpresaServico = Fachada.getInstancia().pesquisar(
				filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());
		ContratoEmpresaServico contratoEmpresaServico = (ContratoEmpresaServico)Util.retonarObjetoDeColecao(contratosEmpresaServico);

		FiltroCobrancaBoletimContrato filtroCobrancaBoletimContrato = new FiltroCobrancaBoletimContrato();
		filtroCobrancaBoletimContrato.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimContrato.ID, form.getIdBoletimContrato()));
		Collection<CobrancaBoletimContrato> colecaoCobrancaBoletimContrato = Fachada.getInstancia().pesquisar(
				filtroCobrancaBoletimContrato, CobrancaBoletimContrato.class.getName());
		CobrancaBoletimContrato cobrancaBoletimContrato = (CobrancaBoletimContrato)
				Util.retonarObjetoDeColecao(colecaoCobrancaBoletimContrato);
		
		FiltroCobrancaBoletimMedicao filtroCobrancaBoletimMedicao = new FiltroCobrancaBoletimMedicao();
		filtroCobrancaBoletimMedicao.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.COBRANCA_BOLETIM_CONTRATO_ID, cobrancaBoletimContrato.getId()));
		filtroCobrancaBoletimMedicao.adicionarParametro(new ParametroNaoNulo(FiltroCobrancaBoletimMedicao.COBRANCA_GRUPO_ID));
		filtroCobrancaBoletimMedicao.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		Collection<CobrancaBoletimMedicao> colecaoCobrancaBoletimMedicao = Fachada.getInstancia().pesquisar(
			filtroCobrancaBoletimMedicao, CobrancaBoletimMedicao.class.getName());
		
		String gruposIncluidos = "";
		if(colecaoCobrancaBoletimMedicao != null && !colecaoCobrancaBoletimMedicao.isEmpty()){
			for (CobrancaBoletimMedicao cobrancaBoletimMedicao : colecaoCobrancaBoletimMedicao){
				gruposIncluidos = gruposIncluidos + "  "+ cobrancaBoletimMedicao.getCobrancaGrupo().getDescricaoAbreviada() +"-" + Util.formatarAnoMesParaMesAno(cobrancaBoletimMedicao.getAnoMesReferencia()) ;
			}
		}
		
		httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
		relatorio.addParametro("tipoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("nomeEmpresa", empresa.getDescricao());
		relatorio.addParametro("numeroContrato", contratoEmpresaServico.getDescricaoContrato());
		relatorio.addParametro("descricaoContrato", cobrancaBoletimContrato.getDescricaoBoletimContrato());
		relatorio.addParametro("dataReferencia", form.getMesAnoReferencia());
		relatorio.addParametro("pcTaxaSucesso", Util.formatarBigDecimalComPonto(contratoEmpresaServico.getPercentualTaxaSucesso()) + '%');
		relatorio.addParametro("gruposIncluidos", gruposIncluidos);
		relatorio.addParametro("filtrarRelatorioAcompanhamentoBoletimMedicaoHelper", helper);

		try {
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		return retorno;
	}

}
