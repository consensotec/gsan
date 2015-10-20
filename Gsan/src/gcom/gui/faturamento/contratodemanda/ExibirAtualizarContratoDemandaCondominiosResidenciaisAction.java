package gcom.gui.faturamento.contratodemanda;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.contratodemanda.ContratoDemandaImovel;
import gcom.faturamento.contratodemanda.ContratoDemandaMotivoEncerramento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1230] - Atualizar Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 26/09/2011
 *
 */
public class ExibirAtualizarContratoDemandaCondominiosResidenciaisAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward retorno = mapping.findForward("exibirAtualizarContratoDemandaCondominiosResidenciaisAction");
		AtualizarContratoDemandaCondominiosResidenciaisActionForm form = (AtualizarContratoDemandaCondominiosResidenciaisActionForm) formulario;
		String numeroContratoAttr = (String)request.getAttribute("idContrato");
		String numeroContratoPara = request.getParameter("idContrato");
		String desfazer = request.getParameter("desfazer");
		HttpSession sessao = request.getSession();
		
		/*
		 * Caso o número do contrato seja diferente de null, isto é, caso tenha
		 * vindo do manter contrato de demanda.
		 */
		if(Util.verificarNaoVazio(numeroContratoAttr)){
			ContratoDemandaImovel contrato = this.getFachada().pesquisarContratoDemandaImovel(numeroContratoAttr);
			if(contrato != null){
				sessao.setAttribute("contrato", contrato);
				this.carregarDadosDoContratoForm(form, contrato);
			}
		}else if(Util.verificarNaoVazio(numeroContratoPara)){
			ContratoDemandaImovel contrato = this.getFachada().pesquisarContratoDemandaImovel(numeroContratoPara);
			if(contrato != null){
				sessao.setAttribute("contrato", contrato);
				this.carregarDadosDoContratoForm(form, contrato);
			}
		}else if(Util.verificarNaoVazio(desfazer) && desfazer.equalsIgnoreCase("sim")){
			this.carregarDadosDoContratoForm(form, (ContratoDemandaImovel) sessao.getAttribute("contrato"));
		}else{
			if(Util.verificarIdNaoVazio(form.getIdImovel())){
				ContratoDemandaImovel contrato = (ContratoDemandaImovel)sessao.getAttribute("contrato");
				if(!contrato.getImovel().getId().toString().equals(form.getIdImovel())){
					Imovel imovel = this.getFachada().pesquisarImovel(Integer.valueOf(form.getIdImovel()));
					if(imovel != null){	
						if(this.getFachada().validarImovelContratoDemandaCondominios(form.getIdImovel())){
							form.setInscricaoImovel(imovel.getInscricaoFormatada());
						}
					}else{
						form.setInscricaoImovel("IMOVEL INEXISTENTE");
						request.setAttribute("codigoImovelNaoEncontrado", true);
					}
				}
			}
			
			if(Util.verificarIdNaoVazio(form.getIdCliente())){
				this.pesquisarCliente(form.getIdCliente(), form, request);
			}
		}
		
		this.pesquisarMotivosEncerramento(form, request);
		
		return retorno;
	}
	
	/**
	 * [UC1230] - Atualizar Contrato de Demanda Condomínios Residenciais
	 * 
	 * Método que vai carregar os dados do contrato no formulário
	 * para ser exibido na tela.
	 * 
	 * @author Diogo Peixoto
	 * @since 26/09/2011
	 * 
	 * @param form
	 * @param contrato
	 */
	private void carregarDadosDoContratoForm(AtualizarContratoDemandaCondominiosResidenciaisActionForm form, ContratoDemandaImovel contrato){
		form.setNumeroContrato(contrato.getNumeroContrato());
		form.setIdImovel(contrato.getImovel().getId().toString());
		form.setInscricaoImovel(contrato.getImovel().getInscricaoFormatada());
		form.setDataInicio(Util.formatarData(contrato.getDataInicioContrato()));
		form.setDataFim(Util.formatarData(contrato.getDataFimContrato()));
		form.setIdCliente(contrato.getCliente().getId().toString());
		form.setNomeCliente(contrato.getCliente().getNome());
		form.setDemandaMinima(contrato.getDemandaMinimaContratada().toString());
		form.setPercentualDesconto(contrato.getFaixaConsumo().getPercentualDesconto().toString()+" %");
		form.setDataEncerramento(Util.formatarData(contrato.getDataEncerramentoContrato()));
		form.setObservacaoEncerramento(contrato.getDescricaoObservacaoEncerramento());
		form.setObservacaoSuspensao(contrato.getDescricaoObservacaoSuspensao());
		form.setSituacaoContrato(contrato.getSituacao().getId().toString());
		form.setTipoRelacaoCliente(contrato.getRelacaoCliente().toString());
		if(contrato.getMotivoEcenrramento() == null){
			form.setIdMotivoEncerramento("-1");
		}else{
			form.setIdMotivoEncerramento(contrato.getMotivoEcenrramento().getId().toString());
		}
	}
	
	/**
	 * [UC1230] - Atualizar Contrato de Demanda Condomínios Residenciais
	 * 
	 * Método que recupera todas os motivos de encerremantos do contrato
	 * de demanda que estão em situação ativa.
	 * 
	 * @param form
	 * @param request
	 */
	private void pesquisarMotivosEncerramento(AtualizarContratoDemandaCondominiosResidenciaisActionForm form, HttpServletRequest request){
		List<ContratoDemandaMotivoEncerramento> motivos = this.getFachada().pesquisarContratoDemandaMotivosEncerramentoAtivo();
		if(!Util.isVazioOrNulo(motivos)){
			request.setAttribute("colecaoMotivo", motivos);
		}
	}
	
	/**
	 * [UC1226] Inserir Contrato de Demanda Condomínios Residenciais
	 * [FS0004] – Verificar Cliente
	 * 
	 * @author Diogo Peixoto
	 * @since 20/09/2011
	 * 
	 * @param idCliente
	 * @param form
	 */
	private void pesquisarCliente(String idCliente, AtualizarContratoDemandaCondominiosResidenciaisActionForm form, HttpServletRequest request){
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));

		List<Cliente> clienteEncontrado = (List<Cliente>) this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		if(!Util.isVazioOrNulo(clienteEncontrado)){
			Cliente cliente = clienteEncontrado.get(0);
			//[FS0006] – Validar Cliente
			if(cliente.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)){
				throw new ActionServletException("atencao.contrato_demanda_cliente_inativo");
			}else if(cliente.getCpf() == null){
				throw new ActionServletException("atencao.contrato_demanda_cliente_sem_cpf");
			}else{
				form.setIdCliente(clienteEncontrado.get(0).getId().toString());
				form.setNomeCliente(clienteEncontrado.get(0).getNome());
			}
		}else{
			request.setAttribute("codigoClienteNaoEncontrado", true);
			form.setNomeCliente("CLIENTE INEXISTENTE");
		}
	}
}