package gsan.gui.relatorio.cobranca;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1250] Solicitar Geração/Emissão Boletim de Medição de Contratos
 * 
 * @author Mariana Victor
 * @date 21/11/2011
 * */
public class SolicitarGeracaoEmissaoBoletimMedicaoContratosAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Form
		GerarEmitirBoletimMedicaoContratosForm form = (GerarEmitirBoletimMedicaoContratosForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		this.validarForm(form);

		fachada.gerarBoletimMedicaoContrato(form.getIdsBoletim(), 
			new Integer(form.getIdContrato()), form.getMesAnoReferencia());
		
		montarPaginaSucesso(httpServletRequest, "Boletim de Contrato gerado com sucesso.",
				"Solicitar Geração/Emissão dos Boletins de Contratos",
				"exibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction.do?menu=sim");
		
			
		return retorno;
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
				&& form.getTipoOperacao().equals("1")
				&& (form.getIdsBoletim() == null
						|| form.getIdsBoletim().length ==  0)) {

			throw new ActionServletException(
					"atencao.selecione.boletim_medicao_cobranca");
			
		}
		
	}

}
