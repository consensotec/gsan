package gcom.gui.faturamento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.FiltroParametrosMSGSMSEmail;
import gcom.cadastro.ParametrosMSGSMSEmail;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ColunasTextoSMSEmail;
import gcom.cobranca.FiltroColunasTextoSMSEmail;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

public class ExibirParametrizarMensagemFaturamentoSMSEmailAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirParametrizarMensagemFaturamentoSMSEmail");
		HttpSession session = httpServletRequest.getSession();
		
		ParametrizarMensagemFaturamentoSMSEmailActionForm form = (ParametrizarMensagemFaturamentoSMSEmailActionForm) actionForm;
		
		FiltroParametrosMSGSMSEmail filtroParametrosMSGSMSEmail = new FiltroParametrosMSGSMSEmail();
		filtroParametrosMSGSMSEmail.adicionarParametro(new ParametroNulo(FiltroParametrosMSGSMSEmail.DATA_RETIRADA));
		
		@SuppressWarnings("unchecked")
		ParametrosMSGSMSEmail parametrosMSGSMSEmail = (ParametrosMSGSMSEmail) 
				Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroParametrosMSGSMSEmail, ParametrosMSGSMSEmail.class.getName()));
		
		httpServletRequest.getSession().setAttribute("parametrosMSGSMSEmail", parametrosMSGSMSEmail);
		
		if(httpServletRequest.getParameter("menu")!=null){
			pesquisarCategoria(session);
			pesquisarColunasTextoSMSEmail(session);
			pesquisarParametrosMSGSMSEmail(httpServletRequest,parametrosMSGSMSEmail, form);
		}
		
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	private void pesquisarCategoria(HttpSession session){
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,ConstantesSistema.SIM));
		Collection colecaoCategoria = Fachada.getInstancia().
				pesquisar(filtroCategoria, Categoria.class.getName());
		
		session.setAttribute("colecaoCategoria", colecaoCategoria);
	}
	
	@SuppressWarnings("rawtypes")
	private void  pesquisarColunasTextoSMSEmail(HttpSession session){
		
		FiltroColunasTextoSMSEmail filtroColunasTextoSMSEmail = new FiltroColunasTextoSMSEmail();
		filtroColunasTextoSMSEmail.adicionarParametro(new ParametroSimples(
				FiltroColunasTextoSMSEmail.INDICADOR_USO, ConstantesSistema.SIM));
		filtroColunasTextoSMSEmail.adicionarParametro(new ParametroSimples(
				FiltroColunasTextoSMSEmail.INDICADOR_FATURAMENTO, ConstantesSistema.SIM));
		
		Collection colecaoColunasTextoSMSEmail = Fachada.getInstancia().pesquisar(
				filtroColunasTextoSMSEmail, ColunasTextoSMSEmail.class.getName());
		
		session.setAttribute("colecaoColunasTextoSMSEmail", colecaoColunasTextoSMSEmail);
		
		int quantidadeColecaoColunasTextoSMSEmail = 0;
		if(colecaoColunasTextoSMSEmail!=null
				&& colecaoColunasTextoSMSEmail.size()!=0){
			quantidadeColecaoColunasTextoSMSEmail = colecaoColunasTextoSMSEmail.size();
		}
		session.setAttribute("quantidadeColecaoColunasTextoSMSEmail", quantidadeColecaoColunasTextoSMSEmail);
	}
	
	/**
	 * @author Jonathan Marcos
	 * @date 14/07/2014
	 * @param httpServletRequest
	 * [OBSERVACOES]
	 * 		- [IT0001] Pesquisar ultima mensagem cadastrada
	 */
	private void pesquisarParametrosMSGSMSEmail(HttpServletRequest httpServletRequest,ParametrosMSGSMSEmail parametrosMSGSMSEmail,
			ParametrizarMensagemFaturamentoSMSEmailActionForm form){
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		String[] categorias = new String[4];
		
		form.setAnoMesFaturamento(Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento()));
		form.setValorMinimoConta(Util.formatarMoedaReal(parametrosMSGSMSEmail.getValorMinimoConta()));
		form.setQuantidadeDiasAntesVencimento(Util.converterObjetoParaString(parametrosMSGSMSEmail.getQuantidadeDiasAntesVencimento()));
		form.setQuantidadeTentativaEnvio(Util.converterObjetoParaString(parametrosMSGSMSEmail.getQuantidadeTentativasEnvio()));
		form.setDescricaoTextoSMS(Util.converterObjetoParaString(parametrosMSGSMSEmail.getDescricaoTextoSMS()));
		form.setDescricaoTextoEmail(Util.converterObjetoParaString(parametrosMSGSMSEmail.getDescricaoTextoEmail()));
		
		form.setIndicadorComercial(Util.converterObjetoParaString(parametrosMSGSMSEmail.getIndicadorComercial()));
		if(parametrosMSGSMSEmail.getIndicadorComercial() != null && parametrosMSGSMSEmail.getIndicadorComercial().equals(ConstantesSistema.SIM)){
			this.inserirArray(categorias, Categoria.COMERCIAL_INT+"");
		}
		
		form.setIndicadorIndustrial(Util.converterObjetoParaString(parametrosMSGSMSEmail.getIndicadorIndustrial()));
		if(parametrosMSGSMSEmail.getIndicadorIndustrial() != null && parametrosMSGSMSEmail.getIndicadorIndustrial().equals(ConstantesSistema.SIM)){
			this.inserirArray(categorias, Categoria.INDUSTRIAL_INT+"");
		}
		
		form.setIndicadorPublico(Util.converterObjetoParaString(parametrosMSGSMSEmail.getIndicadorPublico()));
		if(parametrosMSGSMSEmail.getIndicadorPublico() != null && parametrosMSGSMSEmail.getIndicadorPublico().equals(ConstantesSistema.SIM)){
			this.inserirArray(categorias, Categoria.PUBLICO_INT+"");
		}
		
		form.setIndicadorResidencial(Util.converterObjetoParaString(parametrosMSGSMSEmail.getIndicadorResidencial()));
		if(parametrosMSGSMSEmail.getIndicadorResidencial() != null && parametrosMSGSMSEmail.getIndicadorResidencial().equals(ConstantesSistema.SIM)){
			this.inserirArray(categorias, Categoria.RESIDENCIAL_INT+"");
		}
		
		form.setCategorias(categorias);
		form.setMaximoSMSSP(Util.converterObjetoParaString(sistemaParametro.getTamanhoMaximoMensagemSms()));	
		
	}
	
	private void inserirArray(String[] categorias,String valor){
		for(int i = 0; i < categorias.length; i++){
			if(categorias[i] == null){
				categorias[i] = valor;
				break;
			}
		}
	}
}
