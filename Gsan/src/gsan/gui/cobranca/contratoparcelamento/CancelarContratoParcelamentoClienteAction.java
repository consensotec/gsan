package gsan.gui.cobranca.contratoparcelamento;

import gsan.cobranca.contratoparcelamento.ContratoParcelamento;
import gsan.cobranca.contratoparcelamento.FiltroContratoParcelamento;
import gsan.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
 * 
 * @author Rômulo Aurélio
 * @date 12/05/2011
 */
public class CancelarContratoParcelamentoClienteAction extends GcomAction {
	

		public ActionForward execute(ActionMapping actionMapping,
				ActionForm actionForm, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("voltarExibirCancelarContratoParcelamentoClienteAction");

			HttpSession sessao = httpServletRequest.getSession(false);

			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			Fachada fachada = Fachada.getInstancia();

			CancelarContratoParcelamentoClienteActionForm form = (CancelarContratoParcelamentoClienteActionForm) actionForm;


			ContratoParcelamento contratoParcelamento = (ContratoParcelamento) sessao.getAttribute("contratoParcelamento");
			
			FiltroContratoParcelamento filtroContratoParcelamento = new FiltroContratoParcelamento();
			filtroContratoParcelamento.adicionarParametro(new ParametroSimples(
				FiltroContratoParcelamento.ID, contratoParcelamento.getId()));
			Collection<ContratoParcelamento> colecaoContrato = fachada.pesquisar(
				filtroContratoParcelamento, ContratoParcelamento.class.getName());
			ContratoParcelamento contrato = (ContratoParcelamento) Util.retonarObjetoDeColecao(colecaoContrato);
			
			String dataCancelamentoForm = form.getDataCancelamento();
			Date dataCancelamento = Util.converteStringParaDate(dataCancelamentoForm);
			

			// [FS0005]-Validar data superior a data corrente
			if(dataCancelamento.after(new Date())){
				throw new ActionServletException("atencao.data_menor_que_atual", null , dataCancelamentoForm);
			}
			
			//[FS0001] - Verificar preenchimento dos campos
			
			if(form.getDataCancelamento() == null || form.getDataCancelamento().equalsIgnoreCase("")){
				
				throw new ActionServletException("atencao.required", null, "Data Cancelamento");
				
			}else{
				contrato.setDataCancelamento(dataCancelamento);
			}
			
			if(form.getIdMotivoCancelamento() == null || form.getIdMotivoCancelamento().equalsIgnoreCase("-1")){
				throw new ActionServletException("atencao.required", null, "Motivo Cancelamento");
			}else{
				ParcelamentoMotivoDesfazer pacMotivoDesfazer = new ParcelamentoMotivoDesfazer();
				pacMotivoDesfazer.setId(new Integer(form.getIdMotivoCancelamento()));
				contrato.setMotivoDesfazer(pacMotivoDesfazer);
			}
			
			
			sessao.removeAttribute("fecharPopupCancelarContrato");
			
			fachada.cancelarContratoParcelamentoCliente(contrato, usuarioLogado);

			sessao.setAttribute("fecharPopupCancelarContrato", true);

			return retorno;
		}
}
