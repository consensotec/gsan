package gcom.gui.mobile.execucaoordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.mobile.execucaoordemservico.bean.ArquivoTxtOSCobrancaSmartphoneHelper;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1498] - Consultar Arquivo Texto de Ordens de Serviço para Smartphone
 * (Novo)
 * 
 * @author Jean Varela
 * @date 16/11/2015
 */
public class ConsultarArquivoTextoOSCobrancaSmartphoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultarArquivoTextoOSCobrancaSmartphone");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;

		validaCamposObrigatorios(form);

		Integer idEmpresa = form.getIdEmpresa();
		Integer idTipoServico = form.getIdServicoTipo();
		Integer referenciaCronograma = null;
		Integer[] idsLocalidade = null;
		Integer idComandoEventual = null;
		Integer idAgenteComercial = null;
		Integer idCobrancaGrupo = null;
		Integer idSituacaoArquivo = null;
		
		if (form.getTipoFiltro() == 1){
			if (Util.verificarNaoVazio(form.getMesAnoCronograma())) {
				referenciaCronograma = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoCronograma());
			}
			
			if (form.getIdsLocalidade() != null && (form.getIdsLocalidade().length > 1 ||
				(form.getIdsLocalidade().length == 1 && form.getIdsLocalidade()[0] != -1))){
				idsLocalidade = form.getIdsLocalidade();
			}
			
			idCobrancaGrupo = form.getIdGrupoCobranca();
		}else{			
			
			if ( form.getIdComando() != null &&  form.getIdComando() != -1){
				idComandoEventual = form.getIdComando();
			}

		}

		if (form.getIdAgenteComercial() != null && form.getIdAgenteComercial() != -1){
			idAgenteComercial = form.getIdAgenteComercial(); 
		}	
		
		if (form.getSituacaoTextoLeitura() != null && Integer.valueOf(form.getSituacaoTextoLeitura()) != -1){
			idSituacaoArquivo = Integer.valueOf(form.getSituacaoTextoLeitura());
		}
		
		Collection<ArquivoTxtOSCobrancaSmartphoneHelper> colecaoArquivoTextoOSCobrancaSmartphone = fachada
				.buscarColecaoArquivoTextoOSCobrancaSmartphone(idEmpresa, idTipoServico, referenciaCronograma,
						idCobrancaGrupo, idsLocalidade, idComandoEventual,idAgenteComercial,idSituacaoArquivo);

		if (colecaoArquivoTextoOSCobrancaSmartphone.size() > 0) {
			sessao.setAttribute("colecaoArquivoTextoOSCobrancaSmartphone", colecaoArquivoTextoOSCobrancaSmartphone);
			sessao.setAttribute("achou", "1");
		} else {
			throw new ActionServletException("atencao.arquivo_texto_os_cobranca_inexistente");
		}

		if (httpServletRequest.getAttribute("baixarArquivo") != null) {
			httpServletRequest.setAttribute("baixarArquivo", true);
		}

		return retorno;
	}

	public void validaCamposObrigatorios(ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form) {

		if (form.getIdEmpresa() == null || form.getIdEmpresa() == -1) {
			throw new ActionServletException("atencao.campo.informado", null, "Empresa ");
		}

		if (form.getIdGrupoCobranca() != null && form.getIdGrupoCobranca() != -1) {
			if (form.getMesAnoCronograma() == null || form.getMesAnoCronograma().trim().equals("")) {
				throw new ActionServletException("atencao.campo.informado", null, "Mês/Ano do Cronograma:");
			}

		}
	}
}
