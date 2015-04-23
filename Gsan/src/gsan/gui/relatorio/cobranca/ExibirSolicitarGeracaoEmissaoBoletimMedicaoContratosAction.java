package gsan.gui.relatorio.cobranca;

import java.util.Collection;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.ContratoEmpresaServico;
import gsan.micromedicao.FiltroContratoEmpresaServico;
import gsan.relatorio.cobranca.BoletimMedicaoContratoDadosHelper;
import gsan.relatorio.cobranca.BoletimMedicaoContratoHelper;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
 * 
 * @author Mariana Victor
 * @date 21/11/2011
 * */
public class ExibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirSolicitarGeracaoEmissaoBoletimMedicaoContratos");
		
		GerarEmitirBoletimMedicaoContratosForm form = 
			(GerarEmitirBoletimMedicaoContratosForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
	
		Fachada fachada = Fachada.getInstancia();

        if (httpServletRequest.getParameter("menu") != null
        		&& httpServletRequest.getParameter("menu").trim().equalsIgnoreCase("sim")) {
        	// 1.1.	Operação (obrigatório) (exibir com a opção "Gerar Boletim" selecionado e permitir 
        	//  que o usuário selecione entre "Gerar Boletim" e "Emitir Relatório").
        	form.setFormaGeracao("1");
        	form.setTipoOperacao("1");
        	form.setIdEmpresa("-1");
        	form.setIdsBoletim(null);
        	form.setMesAnoReferencia("");
        	
        	// 1.2.	Empresa (obrigatório) o sistema deverá popular 
        	//  o "combo-box" com a descrição das empresas ativas contratadas para cobrança 
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        	filtroEmpresa.adicionarParametro(new ParametroSimples(
        		FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
        	filtroEmpresa.adicionarParametro(new ParametroSimples(
        		FiltroEmpresa.INDICADOR_EMPRESA_CONTRATADA_COBRANCA, ConstantesSistema.SIM));
        	Collection<Empresa> colecaoEmpresa = fachada.pesquisar(
        		filtroEmpresa, Empresa.class.getName());
        	
        	if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
        		sessao.setAttribute("colecaoEmpresas", colecaoEmpresa);
        	} else {
        		sessao.removeAttribute("colecaoEmpresas");
        	}

        }
        
        if (httpServletRequest.getParameter("selecionouEmpresa") != null
        		&& httpServletRequest.getParameter("selecionouEmpresa").trim().equalsIgnoreCase("sim")){
        	
        	if (form.getIdEmpresa() != null
        		&& !form.getIdEmpresa().trim().equals("")) {

        		sessao.setAttribute("informouEmpresa", true);
        		
	        	// 1.3.	Contrato (obrigatório) (só habilitar após a seleção da Empresa no item acima) o sistema deverá 
	        	//  popular o "combo-box" com os contratos associados à empresa informada 
	        	FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
	        	filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(
	        		FiltroContratoEmpresaServico.EMPRESA_ID, form.getIdEmpresa()));
	        	Collection<ContratoEmpresaServico> colecaoContratoEmpresaServico = fachada.pesquisar(
	        		filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());
	        	
	        	if (colecaoContratoEmpresaServico != null && !colecaoContratoEmpresaServico.isEmpty()) {
	        		sessao.setAttribute("colecaoContrato", colecaoContratoEmpresaServico);
	        	} else {
	        		sessao.removeAttribute("colecaoContrato");
	        	}
	        	
        	} else {
        		sessao.removeAttribute("informouEmpresa");
        		sessao.removeAttribute("colecaoContrato");
        	}
        	
        }

		// Tipo da Operação igual a Gerar Boletim
		if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("1")) {
			
			  if (httpServletRequest.getParameter("selecionarBoletim") != null
		        		&& httpServletRequest.getParameter("selecionarBoletim").trim().equalsIgnoreCase("sim")){
				  
				  this.validarForm(form);
				  Integer anoMes = null;
				  
				  if (form.getMesAnoReferencia() != null && !form.getMesAnoReferencia().trim().equals("")) {
					anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
				  }
				  
				  Collection<ContratoEmpresaServico> colecaoContratoEmpresaServico =
						  (Collection<ContratoEmpresaServico>) sessao.getAttribute("colecaoContrato");
				  String descricaoContrato = this.obterDescricaoContratoServico(form.getIdContrato(), colecaoContratoEmpresaServico);
				  
				  Collection<BoletimMedicaoContratoHelper> colecaoBoletimMedicaoContratoHelper = 
						  fachada.pesquisarBoletimMedicaoContrato(
							  new Integer(form.getIdContrato()), descricaoContrato, 
							  anoMes);
				  
				  if (colecaoBoletimMedicaoContratoHelper != null 
						  && !colecaoBoletimMedicaoContratoHelper.isEmpty()) {
					  sessao.setAttribute("colecaoBoletimMedicaoContratoHelper", colecaoBoletimMedicaoContratoHelper);
				  } else {
					  sessao.removeAttribute("colecaoBoletimMedicaoContratoHelper");
				  }
				  
			  }
		} else if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("2")) {
			// Tipo da Operação igual a Emitir Boletim

			  if (httpServletRequest.getParameter("selecionarBoletimContrato") != null
		        		&& httpServletRequest.getParameter("selecionarBoletimContrato").trim().equalsIgnoreCase("sim")){
				  
				  this.validarForm(form);
				  
				  Collection<BoletimMedicaoContratoDadosHelper> colecaoBoletimMedicaoContratoDadosHelper = 
						  fachada.pesquisarBoletimMedicaoContratoEmitir(
							  new Integer(form.getIdContrato()),
							  Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia()));
				  
				  if (colecaoBoletimMedicaoContratoDadosHelper != null 
						  && !colecaoBoletimMedicaoContratoDadosHelper.isEmpty()) {
					  sessao.setAttribute("colecaoBoletimMedicaoContratoDadosHelper", colecaoBoletimMedicaoContratoDadosHelper);
				  } else {
					  sessao.removeAttribute("colecaoBoletimMedicaoContratoDadosHelper");
				  }
				  
			  }
		}

		return retorno;
	}
	
	/**
	 * [UC1250] - Solicitar Geração/Emissão Boletim de Medição de Contratos
	 * pesquisa na lista de contratos que foi fornecida utilizando o id parametro,
	 * e retorna a descrição do contrato selecionado.
	 * 
	 * @author Bruno Sá Barreto
	 * @date 15/01/2015
	 *
	 * @param idContrato
	 */
	private String obterDescricaoContratoServico(String idContrato, Collection<ContratoEmpresaServico> colecaoContratoEmpresaServico) {
		String result = "";
		for(ContratoEmpresaServico contrato : colecaoContratoEmpresaServico){
			if(idContrato.equals(contrato.getId().toString()))result = contrato.getDescricaoContrato();
			break;
		}
		return result;
	}

	private void validarForm(GerarEmitirBoletimMedicaoContratosForm form) {
		
		if (form.getIdEmpresa() == null
				|| form.getIdEmpresa().trim().equals("")
				|| form.getIdEmpresa().trim().equals("-1")) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Empresa");
		}
		
		if (form.getIdContrato() == null
				|| form.getIdContrato().trim().equals("")
				|| form.getIdContrato().trim().equals("-1")) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Contrato");
		}
		
		if (form.getTipoOperacao() != null
				&& !form.getTipoOperacao().equals("1")
				&& (form.getMesAnoReferencia() == null
				|| form.getMesAnoReferencia().trim().equals(""))) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Mês/Ano do Ciclo de Cobrança");
		}
		
		// [FS0001] - Validar mês/ano do boletim.
		if (form.getTipoOperacao() != null
				&& !form.getTipoOperacao().equals("1")
				&& !Util.validarMesAno(form.getMesAnoReferencia())) {
			
			throw new ActionServletException("atencao.mesAno", "Mês/Ano informado");
		}
	}

}
