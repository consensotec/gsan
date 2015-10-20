package gcom.gui.faturamento.contratodemanda;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.contratodemanda.ContratoDemandaImovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1230] Atualizar Contrato de Demanda Condomínios Residenciais
 * 
 * @author Diogo Peixoto
 * @since 26/09/2011
 *
 */
public class AtualizarContratoDemandaCondominiosResidenciaisAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward retorno = mapping.findForward("telaSucesso");
		AtualizarContratoDemandaCondominiosResidenciaisActionForm form = (AtualizarContratoDemandaCondominiosResidenciaisActionForm) formulario;
		HttpSession sessao = request.getSession();
		ContratoDemandaImovel contrato = (ContratoDemandaImovel) sessao.getAttribute("contrato");
		
		/*
		 * UC1230 - FS0004
		 * Verifica se o imóvel não é um condominio ou se o imóvel 
		 * está associado a algum condomínio.
		 */
		Imovel imov = this.getFachada().pesquisarImovel(new Integer(form.getIdImovel()));		
		
		if(imov != null){
			if(imov.getIndicadorImovelCondominio() == 1){
				throw new ActionServletException("atencao.contrato_demanda_imovel_condominio");
			}
			if(imov.getImovelCondominio() != null){
				throw new ActionServletException("atencao.contrato_demanda_imovel_vinculado_condominio");
			}
		}
			
		if(contrato.getSituacao().getDescricao().equalsIgnoreCase("Suspenso") && form.getSituacaoContrato().equals("1")){
			contrato.setDataSuspensaoContrato(null);
			contrato.setDescricaoObservacaoSuspensao(null);
		}
	
		
		this.getFachada().atualizarContratoDemandaCondominiosResidenciais(contrato, form.getNumeroContrato(), form.getIdImovel(), 
			form.getDataInicio(), form.getDataFim(), form.getIdCliente(), form.getTipoRelacaoCliente(), form.getDemandaMinima(), 
			form.getPercentualDesconto(), form.getSituacaoContrato(), form.getIdMotivoEncerramento(), form.getObservacaoSuspensao(), 
			form.getObservacaoEncerramento(), form.getDataEncerramento(), this.getUsuarioLogado(request));
		
		montarPaginaSucesso(request, "Contrato de Demanda Condomínios Residenciais "
                + contrato.getNumeroContrato() + "  atualizado com sucesso.",
                "Realizar outra Manutenção de Contrato de Demanda Condomínios Residenciais",
                "exibirFiltrarContratoDemandaCondominiosResidenciaisAction.do?desfazer=sim");
		
		return retorno;
	}
}
