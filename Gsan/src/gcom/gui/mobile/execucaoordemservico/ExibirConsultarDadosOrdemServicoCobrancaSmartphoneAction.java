package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.mobile.execucaoordemservico.ExecucaoOSCorte;
import gcom.mobile.execucaoordemservico.ExecucaoOSFiscalizacao;
import gcom.mobile.execucaoordemservico.ExecucaoOSOrdemServico;
import gcom.mobile.execucaoordemservico.ExecucaoOSSituacoesEncontradas;
import gcom.mobile.execucaoordemservico.FiltroExecucaoOSCorte;
import gcom.mobile.execucaoordemservico.FiltroExecucaoOSFiscalizacao;
import gcom.mobile.execucaoordemservico.FiltroExecucaoOSOrdemServico;
import gcom.mobile.execucaoordemservico.FiltroExecucaoOSSituacoesEncontradas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarDadosOrdemServicoCobrancaSmartphoneAction extends GcomAction {

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
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoCobrancaSmartphone");
        //Obtém a instância da sessão        
        HttpSession sessao = this.getSessao(httpServletRequest);
        ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form = 
        	(ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm) actionForm;

        String idOrdemServico = "", matricula = "", idArquivo = "";
        
        if (httpServletRequest.getParameter("desfazer") != null && 
        	httpServletRequest.getParameter("desfazer").equalsIgnoreCase("true")) {
        	idOrdemServico = form.getOrdemServico();
        	matricula = form.getMatricula();
        	idArquivo = form.getIdArquivo();
        }
        
        this.carregarCampos(sessao,httpServletRequest,form,idOrdemServico,matricula,idArquivo);
        
        //Monta o Status do Wizard
        StatusWizard statusWizard = 
    		new StatusWizard(
    		"consultarDadosOrdemServicoCobrancaSmartphoneWizardAction", 
    		"encerrarOrdemServicoIndividualCobrancaSmartphoneAction",
    		null,
    		null);
        
        statusWizard.inserirNumeroPaginaCaminho(
        	statusWizard.new StatusWizardItem(
        			1, 
        			"abaOSA.gif", 
        			"abaOSB.gif",
                    "exibirConsultarDadosOrdemServicoCobrancaSmartphoneRetornoAction",
                    "consultarDadosOrdemServicoCobrancaSmartphoneRetornoAction"));
        
        //monta a segunda aba do processo,de foto
        statusWizard.inserirNumeroPaginaCaminho(
        	statusWizard.new StatusWizardItem(
        		2, 
        		"AbaFotosA.gif", 
        		"AbaFotosD.gif",
                "exibirConsultarDadosOrdemServicoCobrancaSmartphoneFotosAction",
                "consultarDadosOrdemServicoCobrancaSmartphoneFotosAction"));

        statusWizard.setNomeBotaoConcluir("Encerrar OS");
        
    	statusWizard.setCaminhoActionVoltarFiltro("exibirConsultarOrdemServicoCobrancaSmartphoneAction");
        
        if (sessao.getAttribute("ordemServicoEncerrada") != null && 
        	!sessao.getAttribute("ordemServicoEncerrada").toString().trim().equals("")){
        	
        	statusWizard.setBotaoConcluirDesabilitado("sim");
        } else {
        	statusWizard.setBotaoConcluirDesabilitado("");
        }
        
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);

        //retorna o mapeamento contido na variável retorno
        return retorno;
    }

    public void carregarCampos( HttpSession sessao, HttpServletRequest httpServletRequest, 
    		ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form, String idOrdemServico, String idImovelConsultado, String idArquivoConsultado) {

    	Fachada fachada = Fachada.getInstancia();
    	
    	Integer idOS = null;
    	
//		Usuario usuario = (Usuario)sessao.getAttribute("usuarioLogado");
//		form.setEmpresa( usuario.getEmpresa().getId()+"" );
//		form.setDescricaoEmpresa( usuario.getEmpresa().getDescricao() );
		
		// 2. Tipo da Ordem de Servico					
		form.setIdTipoOrdemServico(1+"");
		form.setDescricaoTipoOrdemServico(OrdemServico.DESCRICAO_ORDEM_SERVICO_COBRANCA);
    	
        // 3. Ordem de Serviço 
        if (httpServletRequest.getParameter("ordemServico") != null
        	&& !httpServletRequest.getParameter("ordemServico").equals("")) {

        	idOS = new Integer(httpServletRequest.getParameter("ordemServico"));
        	form.setOrdemServico(idOS.toString());
        } else if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

        	idOS = new Integer(idOrdemServico);
        	form.setOrdemServico(idOrdemServico);
        }
        
        // 4. Matrícula
        if (httpServletRequest.getParameter("matricula") != null
        	&& !httpServletRequest.getParameter("matricula").equals("")) {
        	
        	Integer idImovel = new Integer(httpServletRequest.getParameter("matricula"));
        	form.setMatricula(idImovel.toString());
        	
        } else if (idImovelConsultado != null && !idImovelConsultado.trim().equals("")) {

        	form.setMatricula(idImovelConsultado);
        }
        
        // 5. Tipo de Servico
        FiltroExecucaoOSOrdemServico filtro = new FiltroExecucaoOSOrdemServico();
        filtro.adicionarCaminhoParaCarregamentoEntidade( "servicoTipo" );
        filtro.adicionarCaminhoParaCarregamentoEntidade( "atendimentoMotivoEncerramento" );
        filtro.adicionarCaminhoParaCarregamentoEntidade( "arquivoTextoOSCobranca.parametrosArquivoTextoOSCobranca.empresa");
        filtro.adicionarParametro( new ParametroSimples( FiltroExecucaoOSOrdemServico.ID_ORDEM_SERVICO, idOS ) );
        ExecucaoOSOrdemServico os = ( ExecucaoOSOrdemServico )Util.retonarObjetoDeColecao( fachada.pesquisar(filtro, ExecucaoOSOrdemServico.class.getName() ) );
        
        form.setDescricaoServicoTipo( os.getServicoTipo().getDescricao() );
        
        // 6 Tipo de Encerramento
        form.setDescricaoMotivoEncerramento( os.getAtendimentoMotivoEncerramento().getDescricao() );
        
        /*if (os.getAtendimentoMotivoEncerramento().getIndicadorExecucao() == ConstantesSistema.SIM.shortValue()) {
        	httpServletRequest.setAttribute("exibirDebitos", "1");
        }*/
        
        // 7 Data da Execucao
        form.setDataExecucao( Util.formatarData( os.getDataExecucao() ) );
        
        // 8 Hora da Execução
        form.setHoraExecucao( Util.formatarHoraSemData( os.getDataExecucao() ) );
        
        // 9 Parecer
        form.setParecer( os.getParecerEncerramento() );
        
        // 1. Empresa
        if(os.getArquivoTextoOSCobranca().getParametrosArquivoTextoOSCobranca().getEmpresa() != null){
        	form.setEmpresa( os.getArquivoTextoOSCobranca().getParametrosArquivoTextoOSCobranca().getEmpresa().getId()+"" );
        	form.setDescricaoEmpresa( os.getArquivoTextoOSCobranca().getParametrosArquivoTextoOSCobranca().getEmpresa().getDescricao() );
		}
        
        // 10. id do Arquivo
        Integer idArquivo = null;
        if ( httpServletRequest.getParameter("idArquivo") != null && 
        	!httpServletRequest.getParameter("idArquivo").equals("")) {
        	
        	idArquivo = new Integer( httpServletRequest.getParameter("idArquivo"));
        	form.setIdArquivo(idArquivo.toString());
        	
        } else if ( idArquivoConsultado != null && !idImovelConsultado.trim().equals( "" ) ){
        	form.setIdArquivo(idArquivoConsultado);
        }
        
        if (httpServletRequest.getParameter("calcularValores") == null) {
        	form.setPercentualCobranca("100");
        	form.setQuantidadeParcelas("1");
        }
        
        sessao.removeAttribute("execucaoOSCorte");
        sessao.removeAttribute("execucaoOSSupressao");
        sessao.removeAttribute("execucaoOSFiscalizacao");
        sessao.removeAttribute("colecaoSituacoesEncontradas");
        sessao.removeAttribute("execucaoOSSubstituicao");
        sessao.removeAttribute("execucaoOSRemocao");
        sessao.removeAttribute("execucaoOSCaixaProtecao");
        
        // Adicionamos agora um objeto especifico para cada tipo de OS
        FiltroExecucaoOSCorte filtroCorte = new FiltroExecucaoOSCorte();
        filtroCorte.adicionarParametro( new ParametroSimples( FiltroExecucaoOSCorte.ID_ORDEM_SERVICO, idOS ) );
        filtroCorte.adicionarParametro( new ParametroSimples( FiltroExecucaoOSCorte.ID_ARQUIVO, idArquivo ) );
        filtroCorte.adicionarCaminhoParaCarregamentoEntidade( "motivoCorte" );
        filtroCorte.adicionarCaminhoParaCarregamentoEntidade( "corteTipo" );
        ExecucaoOSCorte osCorte = ( ExecucaoOSCorte )Util.retonarObjetoDeColecao( fachada.pesquisar(filtroCorte, ExecucaoOSCorte.class.getName() ) );
        
        if ( osCorte != null ){
        	form.setDescricaoMotivoCorte( osCorte.getMotivoCorte().getDescricao() );
        	form.setDescricaoTipoCorte( osCorte.getCorteTipo().getDescricao() );
        	form.setLeituraCorte( osCorte.getLeituraCorte()+"" );
        	if(osCorte.getNumeroSelo() != null){
        		form.setNumeroSelo(osCorte.getNumeroSelo().toString());	
        	}else{
        		form.setNumeroSelo("");
        	}
        	
        	String tipoExecucao = null;
        	if(osCorte.getCodigoPavimento() != null){
        		if(osCorte.getCodigoPavimento().equals(ConstantesSistema.SIM)){
        			tipoExecucao = "RAMAL COM PAVIMENTO";
        		}else if(osCorte.getCodigoPavimento().equals(ConstantesSistema.NAO)){
        			tipoExecucao = "RAMAL SEM PAVIMENTO";
        		}else{
        			tipoExecucao = "NICHO";
        		}
        	}
        	form.setTipoExecucao(tipoExecucao);
        	String tipoPavimento = null;
        	if(osCorte.getCodigotipoPavimento() != null){
        		if(osCorte.getCodigotipoPavimento().equals(ConstantesSistema.SIM)){
        			tipoPavimento = "ASFÁLTICO";
        		}else{
        			tipoPavimento = "PARALELO/PEDRA TOSCA";
        		}
        	}
        	form.setTipoPavimento(tipoPavimento);
        	String existeCalcada = null;
        	if(osCorte.getIndicadorExisteCalcada() != null){
        		if(osCorte.getIndicadorExisteCalcada().equals(ConstantesSistema.SIM)){
        			existeCalcada = "SIM";
        		}else{
        			existeCalcada = "NÃO";
        		}
        	}
        	form.setComCalcada(existeCalcada);
        	sessao.setAttribute("execucaoOSCorte", osCorte);
    		
        } else {

	        // Adicionamos o objeto específico de os de fiscalização
	        FiltroExecucaoOSFiscalizacao filtroFiscalizacao = new FiltroExecucaoOSFiscalizacao();
	        filtroFiscalizacao.adicionarParametro( new ParametroSimples( FiltroExecucaoOSFiscalizacao.ID_ORDEM_SERVICO, idOS ) );
	        filtroFiscalizacao.adicionarParametro( new ParametroSimples( FiltroExecucaoOSFiscalizacao.ID_ARQUIVO, idArquivo ) );
	        filtroFiscalizacao.adicionarCaminhoParaCarregamentoEntidade( "documentoTipo" );
	        ExecucaoOSFiscalizacao osFiscalizacao = ( ExecucaoOSFiscalizacao )Util.retonarObjetoDeColecao( fachada.pesquisar(filtroFiscalizacao, ExecucaoOSFiscalizacao.class.getName() ) );
	        
	        if ( osFiscalizacao != null ){
	        	// Adicionamos o documento entregue
	        	if(osFiscalizacao.getDocumentoTipo() != null){
	        		form.setDescricaoDocumentoEntregue(osFiscalizacao.getDocumentoTipo().getDescricaoDocumentoTipo()); 
	        	}
	        	sessao.setAttribute("execucaoOSFiscalizacao", osFiscalizacao);
		        FiltroExecucaoOSSituacoesEncontradas filtroSituacoesEncontradas = new FiltroExecucaoOSSituacoesEncontradas();
		        filtroSituacoesEncontradas.adicionarParametro( new ParametroSimples( FiltroExecucaoOSSituacoesEncontradas.ID_ORDEM_SERVICO, idOS ) );
		        filtroSituacoesEncontradas.adicionarCaminhoParaCarregamentoEntidade("fiscalizacaoSituacao");
		        Collection<?> colSituacoesEncontroladas = (Collection<?>) fachada.pesquisar(filtroSituacoesEncontradas, ExecucaoOSSituacoesEncontradas.class.getName() );
		        
		        if ( colSituacoesEncontroladas != null && colSituacoesEncontroladas.size() > 0 ){
		        	sessao.setAttribute("colecaoSituacoesEncontradas", colSituacoesEncontroladas);
		        }		        	
	        } 
        }
    }
}
