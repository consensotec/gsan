package gsan.gui.seguranca.parametrosistema;

import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.FiltroModulo;
import gsan.seguranca.acesso.Modulo;
import gsan.seguranca.parametrosistema.FiltroParametroSistema;
import gsan.seguranca.parametrosistema.ParametroSistema;
import gsan.seguranca.parametrosistema.ParametroTipo;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarParametroSistemaAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

	
		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("parametroSistemaAtualizar");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarParametroSistemaActionForm form = (AtualizarParametroSistemaActionForm) actionForm;

		String idRegistroAtualizar = httpServletRequest.getParameter("idRegistroAtualizar");
		
		ParametroSistema parametroSistema = null;

		String primeiraVez = httpServletRequest.getParameter("primeiraVez");
		
		if(Util.verificarNaoVazio(primeiraVez)){
			FiltroParametroSistema filtro = new FiltroParametroSistema();
			filtro.adicionarParametro(new ParametroSimples(FiltroParametroSistema.ID, idRegistroAtualizar));
			filtro.adicionarCaminhoParaCarregamentoEntidade("parametroTipo");
			filtro.adicionarCaminhoParaCarregamentoEntidade("modulo");
			Collection colecaoParametroSistema = fachada.pesquisar(filtro, ParametroSistema.class.getName());
			
			if (colecaoParametroSistema != null && !colecaoParametroSistema.isEmpty()) {
				parametroSistema = (ParametroSistema) Util.retonarObjetoDeColecao(colecaoParametroSistema);
			}
			sessao.setAttribute("parametroSistema", parametroSistema);
		}else{
			parametroSistema = (ParametroSistema) sessao.getAttribute("parametroSistema");
		}
		
		Integer id = parametroSistema.getId();
		if (id != null) {
			form.setId(String.valueOf(id));
		} else {
			form.setId("");
		}
		
		String nome = parametroSistema.getNome();
		if (nome != null) {
			form.setNome(String.valueOf(nome));
		} else {
			form.setNome("");
		}
		
		ParametroTipo tipoParametro = parametroSistema.getParametroTipo();
		if (tipoParametro != null) {
			
			 form.setCodigoTipo(String.valueOf(tipoParametro.getId()));
			 form.setDescricaoTipo(tipoParametro.getId() +"- "+tipoParametro.getDescricao());
			 
		} else {
			form.setCodigoTipo("");
			form.setDescricaoTipo("");
		}

		String descricao = parametroSistema.getDescricao();
		if (descricao != null) {
			form.setDescricao(String.valueOf(descricao));
		} else {
			form.setDescricao("");
		}
	
		String codigoConstante = parametroSistema.getCodigoConstante();
		if (codigoConstante != null) {
			form.setCodigoConstante(codigoConstante);
		} else {
			form.setCodigoConstante("");
		}
		
		String valorParametro = parametroSistema.getValorParametro();
		if (valorParametro != null) {	
			
			if(parametroSistema.getParametroTipo().getId().equals(ParametroTipo.REFERENCIA)){
				String referencia = parametroSistema.getValorParametro();				
				//Formta o valor no formato (MM/YYYY)
				String valorparametroReferencia = formatarParametroReferencia(referencia);		
				
				form.setValorParametro(String.valueOf(valorparametroReferencia));	
				
			}else{
				form.setValorParametro(String.valueOf(valorParametro));	
			}
			
		} else {
			form.setValorParametro("");
		}
				
		Short indicadorParametroRestrito = parametroSistema.getIndicadorParametroRestrito();
		if (indicadorParametroRestrito != null) {
			form.setIndicadorParametroRestrito(String.valueOf(indicadorParametroRestrito));
		} else {
			form.setIndicadorParametroRestrito("");
		}
		
		Modulo modulo = new Modulo();
		if(parametroSistema.getModulo() != null){
			modulo.setId(parametroSistema.getModulo().getId());
			
			FiltroModulo filtro = new FiltroModulo();
			filtro.adicionarParametro(new ParametroSimples(FiltroModulo.ID, modulo.getId()));					
			Collection colecaoModulo = fachada.pesquisar(filtro, Modulo.class.getName());
			
			if (colecaoModulo != null && !colecaoModulo.isEmpty()) {
				modulo = (Modulo) Util.retonarObjetoDeColecao(colecaoModulo);
			}
			if (modulo.getId() != null) {
				form.setModuloId(String.valueOf(modulo.getId()));
				form.setDescricaoModulo(modulo.getId() + "- " + modulo.getDescricaoModulo());
				
			} else {
				form.setModuloId("");
				form.setDescricaoModulo("");
			}
		}else{
			form.setModuloId("");
			form.setDescricaoModulo("");		
		}
		
		
		Short indicadorUso = parametroSistema.getIndicadorUso();
		if (indicadorUso != null) {
			form.setIndicadorUso(String.valueOf(indicadorUso));
		} else {
			form.setIndicadorUso("");
		}		
		
		sessao.removeAttribute("parametroRestrito");
		sessao.removeAttribute("parametroRestritoAtivado");
		
		if(!indicadorParametroRestrito.equals(ConstantesSistema.NAO)){	
			sessao.setAttribute("parametroRestritoAtivado",ConstantesSistema.NAO);				
		}else{
			sessao.setAttribute("parametroRestrito",ConstantesSistema.SIM);			
		}
		
		limparSessao(sessao);
		
		sessao.setAttribute("parametroSistema", parametroSistema);
		
		if(tipoParametro.getId().equals(ParametroTipo.NUMERO_INTEIRO)){
			sessao.setAttribute("parametroTipoInteiro", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.INDICADOR)){			
			sessao.setAttribute("parametroTipoIndicador", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.VALOR)){
			sessao.setAttribute("parametroTipoValor", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.URL)){
			sessao.setAttribute("parametroTipoURL", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.TEXTO)){
			sessao.setAttribute("parametroTipoTexto", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.IP)){
			sessao.setAttribute("parametroTipoIP", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.METODO)){
			sessao.setAttribute("parametroTipoMetodo", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.PERCENTUAL)){
			sessao.setAttribute("parametroTipoPercentual", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.DATA)){
			sessao.setAttribute("parametroTipoData", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.DATA_HORA)){
			sessao.setAttribute("parametroTipoDataHora", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.LISTA_VALORES)){
			sessao.setAttribute("parametroTipoLista", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.COORDENADA)){
			sessao.setAttribute("parametroTipoCoordenada", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.ID)){
			sessao.setAttribute("parametroTipoID", tipoParametro.getId());
		}else if(tipoParametro.getId().equals(ParametroTipo.REFERENCIA)){
			sessao.setAttribute("parametroTipoReferencia", tipoParametro.getId());
		}
		
		return retorno;
		
		}
	
	
	private void limparSessao(HttpSession sessao){
		
		sessao.removeAttribute("parametroTipoInteiro");
		sessao.removeAttribute("parametroTipoIndicador");
		sessao.removeAttribute("parametroTipoValor");
		sessao.removeAttribute("parametroTipoURL");
		sessao.removeAttribute("parametroTipoTexto");
		sessao.removeAttribute("parametroTipoIP");
		sessao.removeAttribute("parametroTipoMetodo");
		sessao.removeAttribute("parametroTipoPercentual");
		sessao.removeAttribute("parametroTipoData");
		sessao.removeAttribute("parametroTipoDataHora");
		sessao.removeAttribute("parametroTipoLista");
		sessao.removeAttribute("parametroTipoCoordenada");
		sessao.removeAttribute("parametroTipoID");
		sessao.removeAttribute("parametroTipoReferencia");
		
	}
	
	//Metodo para validar Ano/Mês referência (MM/YYYY)
	private String formatarParametroReferencia(String valorParametro){
				
		String retorno ="";
				
		if(!valorParametro.equals("")){
			
			String referencia = valorParametro;	
			String anoReferencia = referencia.substring(0, 4);
			String mesReferencia = referencia.substring(4,6);
			
					
			retorno =   mesReferencia + "/"+ anoReferencia;
					
		}
		
		return retorno;
				
	}
	
}
