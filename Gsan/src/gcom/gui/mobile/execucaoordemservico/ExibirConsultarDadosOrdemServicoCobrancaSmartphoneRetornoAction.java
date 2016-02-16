package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.mobile.execucaoordemservico.ExecucaoOSOrdemServico;
import gcom.mobile.execucaoordemservico.FiltroExecucaoOSOrdemServico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
 * 
 * SB0001 ? Consultar/Atualizar Dados da Ordem de Serviço
 * 
 * 1ª Aba - "Anormalidade"
 * 
 * @author Mariana Victor
 * @date 23/09/2011
 */
public class ExibirConsultarDadosOrdemServicoCobrancaSmartphoneRetornoAction extends GcomAction {

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
    	
        //seta o mapeamento de retorno para a página da primeira aba
        ActionForward retorno = actionMapping.findForward("consultarDadosOrdemServicoCobrancaSmartphoneRetorno");
        
        ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form = (ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm) actionForm;
        HttpSession sessao = this.getSessao(httpServletRequest);
        
        this.gerarDebio(Integer.valueOf(form.getOrdemServico()), form, httpServletRequest, sessao);
        		
        return retorno;
    }
    
    public void gerarDebio(Integer idOS, ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form, HttpServletRequest httpServletRequest,
    		HttpSession sessao){
    	
    	//GERAÇÃO DO DÉBITO
    	FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();
		filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroServicoNaoCobrancaMotivo.DESCRICAO);

		Collection colecaoServicoNaoCobrancaMotivo = this.getFachada().pesquisar( filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());
		if (colecaoServicoNaoCobrancaMotivo != null && !colecaoServicoNaoCobrancaMotivo.isEmpty()) {
			sessao.setAttribute("colecaoMotivoNaoCobranca",	colecaoServicoNaoCobrancaMotivo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo Não Cobrança");
		}
    	
    	Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    	Fachada fachada = Fachada.getInstancia();
    	
    	OrdemServico ordemServico = fachada.recuperaOSPorId(idOS);
    	
    	// Preencher dados da Geração
		// Tipo Débito
		if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
			form.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId()+"");
			form.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao()+"");
		}else{
			form.setIdTipoDebito("");
			form.setDescricaoTipoDebito("");			
		}
		
		// 5. Tipo de Servico
        FiltroExecucaoOSOrdemServico filtro = new FiltroExecucaoOSOrdemServico();
        filtro.adicionarCaminhoParaCarregamentoEntidade( "servicoTipo" );
        filtro.adicionarCaminhoParaCarregamentoEntidade( "atendimentoMotivoEncerramento" );
        filtro.adicionarParametro( new ParametroSimples( FiltroExecucaoOSOrdemServico.ID_ORDEM_SERVICO, ordemServico.getId() ) );
        ExecucaoOSOrdemServico os = ( ExecucaoOSOrdemServico )Util.retonarObjetoDeColecao( fachada.pesquisar(filtro, ExecucaoOSOrdemServico.class.getName() ) );
        
		if (os.getAtendimentoMotivoEncerramento().getIndicadorExecucao() == ConstantesSistema.SIM.shortValue()) {
        	form.setIcExibirDebitos("1");
        }else{
			form.setIcExibirDebitos("");
		}
		
		//[FS0013] - Alteração de Valor
		this.permitirAlteracaoValor(ordemServico.getServicoTipo(), form);
		
		String calculaValores = httpServletRequest.getParameter("calculaValores");
		
		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = new Integer(1);
		
		if(form.getQuantidadeParcelas() == null || form.getQuantidadeParcelas().equals(""))
			form.setQuantidadeParcelas(qtdeParcelas.toString());
		
		if(calculaValores != null && calculaValores.equals("S"))
			calcularValorPrestacao(form, ordemServico, valorDebito, sistemaParametro);
		else{
		
			valorDebito = 
				this.getFachada().obterValorDebito(ordemServico.getServicoTipo().getId(), 
					ordemServico.getImovel().getId(), new Short(LigacaoTipo.LIGACAO_AGUA+""));
			
			if (valorDebito != null) {
				String valorDebitoComVirgula = valorDebito+"";
				form.setValorDebito(valorDebitoComVirgula.replace(".",","));
			} else {
				form.setValorDebito("0,00");
			}
			
			calcularValorPrestacao(form, ordemServico, valorDebito, sistemaParametro);
		}
		
		form.setQtdeMaxParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento()+"");
		if(ordemServico.getServicoNaoCobrancaMotivo() != null){
			form.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
		}
		if(ordemServico.getServicoNaoCobrancaMotivo() != null){
			form.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
		}
		// Verificar permissão especial
		boolean temPermissaoMotivoNaoCobranca = this.getFachada().verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
		
		if (temPermissaoMotivoNaoCobranca) {
			httpServletRequest.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
//			form.setPercentualCobranca("100");
		}
    }
    
    private void calcularValorPrestacao(
    		ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form, OrdemServico ordemServico,
			BigDecimal valorDebito, SistemaParametro sistemaParametro) {
		Integer qtdeParcelas;
		{
			
			//[UC0186] - Calcular Prestação
			BigDecimal  taxaJurosFinanciamento = null; 
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());
			
			if(ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && 
				qtdeParcelas.intValue() != 1){
				
				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			}else{
				taxaJurosFinanciamento = new BigDecimal(0);
			}
			
			BigDecimal valorPrestacao = null;
			if(taxaJurosFinanciamento != null){
				
				valorDebito = new BigDecimal(form.getValorDebito().replace(",","."));
				
				String percentualCobranca = form.getPercentualCobranca();
				
				if(percentualCobranca != null && percentualCobranca.equals("70")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				}else if (percentualCobranca != null && percentualCobranca.equals("50")){
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}
				
				valorPrestacao =
					this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento,
						qtdeParcelas, 
						valorDebito, 
						new BigDecimal("0.00"));
				
				valorPrestacao.setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			
			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao,2,true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}						
			
		}
	}
    
    /*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter
	 * data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form){
		
		if (servicoTipo.getIndicadorPermiteAlterarValor() == 
			ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()){
			
			form.setAlteracaoValor("OK");
		}
		else{
			form.setAlteracaoValor("");
		}
		
	}
    
}
