package gsan.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gsan.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.gui.StatusWizard;
import gsan.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel por configurar todo o processo de informar acerto documentos n�o aceitos
 * [UC1214] Informar Acerto Documentos N�o Aceitos
 * 
 * @author 	Mariana Victor
 * @created	18/08/2011
 */
public class ExibirConsultarDadosOrdemServicoVisitaAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto actionmapping
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoVisita");

        ConsultarDadosOrdemServicoVisitaActionForm form = (ConsultarDadosOrdemServicoVisitaActionForm) actionForm;

        //Obt�m a inst�ncia da sess�o
        HttpSession sessao = httpServletRequest.getSession(false);

        String idOrdemServico = "", matricula = "";
        
        if (httpServletRequest.getParameter("desfazer") != null
        		&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("true")) {

        	idOrdemServico = form.getOrdemServico();
        	matricula = form.getMatricula();
        }
        
        this.carregarCampos(sessao, httpServletRequest, form, idOrdemServico, matricula);
        
        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "consultarDadosOrdemServicoVisitaWizardAction", "consultarDadosOrdemServicoVisitaAction",
                null,null);
        
        //monta a primeira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "AbaAnormalidadeA.gif", "AbaAnormalidadeD.gif",
                        "exibirConsultarDadosOrdemServicoVisitaAnormalidadeAction",
                        "consultarDadosOrdemServicoVisitaAnormalidadeAction"));
        
        //monta a segunda aba do processo,se for leitura do c�digo de barra por caneta
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "AbaFotosA.gif", "AbaFotosD.gif",
                        "exibirConsultarDadosOrdemServicoVisitaFotosAction",
                        "consultarDadosOrdemServicoVisitaFotosAction"));

        statusWizard.setCaminhoActionDesfazer("exibirConsultarDadosOrdemServicoVisitaAction.do");
        statusWizard.setNomeBotaoConcluir("Atualizar OS");
        statusWizard.setCaminhoActionVoltarFiltro("exibirConsultarOrdemServicoDoArquivoTextoAction");
        
        if (sessao.getAttribute("ordemServicoEncerrada") != null 
        		&& !sessao.getAttribute("ordemServicoEncerrada")
        			.toString().trim().equals("")){
        	statusWizard.setBotaoConcluirDesabilitado("sim");
        } else {
        	statusWizard.setBotaoConcluirDesabilitado("");
        }
        
        //manda o statusWizard para a sess�o
        sessao.setAttribute("statusWizard", statusWizard);

        //retorna o mapeamento contido na vari�vel retorno
        return retorno;
    }

    public void carregarCampos( HttpSession sessao, HttpServletRequest httpServletRequest, 
    		ConsultarDadosOrdemServicoVisitaActionForm form, String idOrdemServico, String idImovelConsultado) {

    	Fachada fachada = Fachada.getInstancia();
    	
    	Integer idOS = null;
    	
        // 1. Ordem de Servi�o 
        if (httpServletRequest.getParameter("ordemServico") != null
        		&& !httpServletRequest.getParameter("ordemServico").equals("")) {

        	idOS = new Integer(httpServletRequest.getParameter("ordemServico"));
        	form.setOrdemServico(idOS.toString());
        } else if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

        	idOS = new Integer(idOrdemServico);
        	form.setOrdemServico(idOrdemServico);
        }
        
        // 2. Matr�cula
        if (httpServletRequest.getParameter("matricula") != null
        		&& !httpServletRequest.getParameter("matricula").equals("")) {
        	
        	Integer idImovel = new Integer(httpServletRequest.getParameter("matricula"));
        	form.setMatricula(idImovel.toString());
        	
        } else if (idImovelConsultado != null && !idImovelConsultado.trim().equals("")) {

        	form.setMatricula(idImovelConsultado);
        }
        
        Object[] dadosOS = fachada.pesquisarDadosAnormalidadePavimentoOSVisitaCampo(idOS);
        
        if (dadosOS != null) {
        	// 3. Anormalidade Registrada
        	if (dadosOS[0] != null) {
        		form.setAnormalidadeRegistrada((String) dadosOS[0]); 
        	} else {
        		form.setAnormalidadeRegistrada("");
        	}
        	// 4. Anormalidade Encontrada 
        	if (dadosOS[1] != null) {
        		form.setAnormalidadeEncontrada((String) dadosOS[1]); 
        	} else {
        		form.setAnormalidadeEncontrada("");
        	}
        	// 5. Tipo de Pavimento de Cal�ada 
        	if (dadosOS[2] != null) {
        		form.setTipoPavimentoCalcada((String) dadosOS[2]); 
        	} else {
        		form.setTipoPavimentoCalcada("");
        	}
        	// 6. Tipo de Pavimento de Rua
        	if (dadosOS[3] != null) {
        		form.setTipoPavimentoRua((String) dadosOS[3]); 
        	} else {
        		form.setTipoPavimentoRua("");
        	}
        	
        	form.setOrdemServicoConferida("");
        	
        	// 7. A��es para Corre��o da Anormalidade:
        	// 7.1.	Caso existam a��es retornadas 
        	if (dadosOS[5] != null
        			&& ((Short)dadosOS[5]).compareTo(ConstantesSistema.NAO) == 0) {
        		sessao.setAttribute("existeAcaoRetornada", true);
        		
        		Integer idArquivoTextoRetornoVisitaCampo = (Integer) dadosOS[4];
        		
        		// 7.1.1. O sistema dever� exibir todas as a��es para corre��o das anormalidades encontradas 
        		Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> colecaoAcoesCorrecaoAnormalidadesEncontradasHelper = 
        				fachada.pesquisarAcoesParaCorrecaoAnormalidadesEncontradas(idArquivoTextoRetornoVisitaCampo);
        		
        		if (colecaoAcoesCorrecaoAnormalidadesEncontradasHelper != null 
        				&& !colecaoAcoesCorrecaoAnormalidadesEncontradasHelper.isEmpty()) {
        			
        			sessao.setAttribute("colecaoAcoesCorrecaoAnormalidadesEncontradasHelper", 
        				colecaoAcoesCorrecaoAnormalidadesEncontradasHelper);
        			
        		} else {
        			
        			sessao.removeAttribute("colecaoAcoesCorrecaoAnormalidadesEncontradasHelper");
        			
        		}
        				
        	} else {
        		// 7.2.	Caso contr�rio, exibir a tabela vazia.
        		// 8. Caso a tabela n�o esteja vazia, o usu�rio poder� gerar as 
        		//  ordens de servi�o informadas em campo:
        		sessao.removeAttribute("existeAcaoRetornada");
        	}
        	

        	if (dadosOS[6] != null
        			&& ((Short)dadosOS[6]).compareTo(ConstantesSistema.NAO) == 0) {
        		sessao.setAttribute("ordemServicoEncerrada", true);
        	} else {
        		sessao.removeAttribute("ordemServicoEncerrada");
        	}
        	
        } else {
    		form.setAnormalidadeRegistrada("");
    		form.setAnormalidadeEncontrada("");
    		form.setTipoPavimentoCalcada("");
    		form.setTipoPavimentoRua("");
    		form.setOrdemServicoConferida("");
			sessao.removeAttribute("colecaoAcoesCorrecaoAnormalidadesEncontradasHelper");
    		sessao.removeAttribute("existeAcaoRetornada");
    		sessao.removeAttribute("ordemServicoEncerrada");
        }
        
    }
}
