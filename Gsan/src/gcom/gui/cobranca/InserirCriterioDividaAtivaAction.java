package gcom.gui.cobranca;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

/**
 * [UC 1587] - Gerar Dívida Ativa
 * 
 * @author Davi Menezes
 * @date 13/02/2014
 *
 */
public class InserirCriterioDividaAtivaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Formulário
		InserirCriterioDividaAtivaActionForm form = (InserirCriterioDividaAtivaActionForm) actionForm;
		
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		if(form.getVencimentoConta() == null || form.getVencimentoConta().equals("")){
			throw new ActionServletException("atencao.required", null, "Data Máxima Vencimento da Conta");
		}else{
			if(!Util.verificaSeDataValida(form.getVencimentoConta(), "dd/MM/yyyy")){
				throw new ActionServletException("atencao.data_maxima_vencimento_conta_invalida");
			}
		}
		
		if(form.getValorInicial() != null && !form.getValorInicial().equals("") &&
				(form.getValorFinal() == null || form.getValorFinal().equals(""))){
			throw new ActionServletException("atencao.informe_campo_final", null, "Valor");
		}
		
		if((form.getValorInicial() == null || form.getValorInicial().equals("")) && 
				form.getValorFinal() != null && !form.getValorFinal().equals("")){
			throw new ActionServletException("atencao.informe_campo_inicial", null, "Valor");
		}
		
		Date vencimentoConta = Util.converteStringParaDate(form.getVencimentoConta());
		
		BigDecimal valorInicial = null;
		if(form.getValorInicial() != null && !form.getValorInicial().equals("")){
			valorInicial = Util.formatarMoedaRealparaBigDecimal(form.getValorInicial());
		}
		
		BigDecimal valorFinal = null;
		if(form.getValorFinal() != null && !form.getValorFinal().equals("")){
			valorFinal = Util.formatarMoedaRealparaBigDecimal(form.getValorFinal());
		}
		
		Collection<Integer> colecaoEsferaPoder = new ArrayList<Integer>();
		if(form.getEsferaPoder() != null && form.getEsferaPoder().length > 0){
			for(int i = 0; i < form.getEsferaPoder().length; i++){
				if(!form.getEsferaPoder()[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecaoEsferaPoder.add(Integer.parseInt(form.getEsferaPoder()[i]));
				}
			}
		}
		
		Collection<Integer> colecaoClienteTipo = new ArrayList<Integer>();
		if(form.getTipoCliente() != null && form.getTipoCliente().length > 0){
			for(int i = 0; i < form.getTipoCliente().length; i++){
				if(!form.getTipoCliente()[i].equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecaoClienteTipo.add(Integer.parseInt(form.getTipoCliente()[i]));
				}
			}
		}
		
		fachada.inserirCriterioDividaAtiva(vencimentoConta, valorInicial, valorFinal, 
				usuarioLogado, colecaoEsferaPoder, colecaoClienteTipo);
		
		montarPaginaSucesso(httpServletRequest, "Criterio de Divida Ativa inserido com sucesso", 
			"Inserir novo criterio de divida ativa", "exibirInserirCriterioDividaAtivaAction.do?menu=sim");
		
		return retorno;
	}

}
