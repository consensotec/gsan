package gcom.gui.cadastro.imovel;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1202]Exibir a Página de transferir imóveis para outro Logradouro.
 * 
 * @author Davi Menezes
 * @date 03/08/2011
 */
public class ExibirTransferirImovelLogradouroAction extends GcomAction {
	
	private static final int LOGRADOURO_ORIGEM = 1;
	
	private static final int LOGRADOURO_DESTINO = 2;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("transferirImovel");
		
		boolean idLogradouroNaoEncontrado = true;
		
		HttpSession session = httpServletRequest.getSession(false);
		
		Collection<Bairro> colecaoBairros = null;
		
		Collection<Cep> colecaoCep = null;
		
		int qtdBairros = 0, qtdCep = 0;
		
		TransferirImovelLogradouroActionForm form = (TransferirImovelLogradouroActionForm) actionForm;
		
		if(httpServletRequest.getParameter("menu") != null){
			form.setTipoTransferencia("1");
		}else	
		//limpa as colecoes de bairro e cep
		if(httpServletRequest.getParameter("limparLograOrigem") != null){
			session.setAttribute("colecaoBairrosOrigem", colecaoBairros);	
			session.setAttribute("colecaoCepOrigem", colecaoCep);
			session.setAttribute("colecaoImovelClienteParaTransferirHelper", null);
			form.setIdsRegistro(null);
		}else if(httpServletRequest.getParameter("limparLograDestino") != null){
			session.setAttribute("quantidadeBairros", qtdBairros);		
			session.setAttribute("quantidadeCep", qtdCep);		
			session.setAttribute("colecaoBairros", colecaoBairros);		
			session.setAttribute("colecaoCep", colecaoCep);
		}else if(httpServletRequest.getParameter("desfazer") != null){
			session.setAttribute("colecaoBairrosOrigem", colecaoBairros);	
			session.setAttribute("colecaoCepOrigem", colecaoCep);
			session.setAttribute("quantidadeBairros", qtdBairros);		
			session.setAttribute("quantidadeCep", qtdCep);		
			session.setAttribute("colecaoBairros", colecaoBairros);		
			session.setAttribute("colecaoCep", colecaoCep);
			session.setAttribute("colecaoImovelClienteParaTransferirHelper", null);
			form.setTipoTransferencia("1");
			form.setIdsRegistro(null);
		}
	
		//Pesquisar o Logradouro Origem e traz as colecoes de bairro e cep
		if(Util.verificarNaoVazio(form.getLogradouroImovelOrigemFiltro())){
			idLogradouroNaoEncontrado = this.pesquisarLogradouro(LOGRADOURO_ORIGEM, form, httpServletRequest);
			
			if(!idLogradouroNaoEncontrado){
				colecaoBairros = pesquisarBairro(LOGRADOURO_ORIGEM, form, session);
				colecaoCep = pesquisarCep(LOGRADOURO_ORIGEM, form, session);
			}
			
			session.setAttribute("colecaoBairrosOrigem", colecaoBairros);	
			session.setAttribute("colecaoCepOrigem", colecaoCep);
		}else{
			session.setAttribute("colecaoBairrosOrigem", null);	
			session.setAttribute("colecaoCepOrigem", null);
		}
		
		//Pesquisar o Logradouro Destino e traz as colecoes de bairro e cep
		if(Util.verificarNaoVazio(form.getLogradouroImovelDestinoFiltro())){
			idLogradouroNaoEncontrado = this.pesquisarLogradouro(LOGRADOURO_DESTINO, form, httpServletRequest);
			
			if(!idLogradouroNaoEncontrado){
				colecaoBairros = pesquisarBairro(LOGRADOURO_DESTINO, form, session);
				colecaoCep = pesquisarCep(LOGRADOURO_DESTINO, form, session);
				
				qtdBairros = colecaoBairros.size();
				qtdCep = colecaoCep.size();
				
				if(qtdBairros == 1){
					form.setBairroImovelFiltro(colecaoBairros.iterator().next().getId().toString());
				}
				
				if(qtdCep == 1){
					form.setCepImovelFiltro(colecaoCep.iterator().next().getCepId().toString());
				}
				
			}
			
			session.setAttribute("quantidadeBairros", qtdBairros);		
			session.setAttribute("quantidadeCep", qtdCep);		
			session.setAttribute("colecaoBairros", colecaoBairros);		
			session.setAttribute("colecaoCep", colecaoCep);
		}else{
			session.setAttribute("quantidadeBairros", null);		
			session.setAttribute("quantidadeCep", null);		
			session.setAttribute("colecaoBairros", null);		
			session.setAttribute("colecaoCep", null);
		}
		
		return retorno;
	}
	
	/**
	 * Pesquisar pelo logradouro
	 * @param Logradouro origem ou destino
	 * @param TransferirImovelLogradouroActionForm
	 * @param httpServletRequest
	 */
	public boolean pesquisarLogradouro(Integer atributoToSet, TransferirImovelLogradouroActionForm form, HttpServletRequest httpServletRequest){
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
		
		if(atributoToSet == LOGRADOURO_ORIGEM) {
			if(!Util.validarStringNumerica(form.getLogradouroImovelOrigemFiltro())){
				throw new ActionServletException("atencao.inteiroNegativoZeroPositivo", "Logradouro Origem");
			}
			
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, Integer.parseInt(form.getLogradouroImovelOrigemFiltro())));
		} else if(atributoToSet == LOGRADOURO_DESTINO){
			if(!Util.validarStringNumerica(form.getLogradouroImovelDestinoFiltro())){
				throw new ActionServletException("atencao.inteiroNegativoZeroPositivo", "Logradouro Destino");
			}
			
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, Integer.parseInt(form.getLogradouroImovelDestinoFiltro())));
		}
		// filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection logradouroEncontrado = this.getFachada().pesquisar(filtroLogradouro, Logradouro.class.getName());

		Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(logradouroEncontrado);

		httpServletRequest.removeAttribute("idLogradouroNaoEncontrado");
			
		if(logradouro != null){
			String logradouroFormatado = "";

			if(logradouro.getLogradouroTipo() != null){
				logradouroFormatado = logradouro.getLogradouroTipo().getDescricaoAbreviada();	
			}
			
			if(logradouro.getLogradouroTitulo() != null){
				logradouroFormatado = logradouroFormatado + " " + logradouro.getLogradouroTitulo().getDescricaoAbreviada(); 
			}
				
			logradouroFormatado = logradouroFormatado + " " + logradouro.getNome();
			
			
			if(atributoToSet == LOGRADOURO_ORIGEM) {
				form.setLogradouroImovelOrigemFiltro(String.valueOf(logradouro.getId()));
				form.setDescricaoLogradouroImovelOrigemFiltro(logradouroFormatado);
			} else if(atributoToSet == LOGRADOURO_DESTINO) {
				form.setLogradouroImovelDestinoFiltro(String.valueOf(logradouro.getId()));
				form.setDescricaoLogradouroImovelDestinoFiltro(logradouroFormatado);
			}
			
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "false");
			return false;
		} else {
			
			if(atributoToSet == LOGRADOURO_ORIGEM) {
				form.setLogradouroImovelOrigemFiltro("");
				form.setDescricaoLogradouroImovelOrigemFiltro("Logradouro Inexistente");
			} else if(atributoToSet == LOGRADOURO_DESTINO) {
				form.setLogradouroImovelDestinoFiltro("");
				form.setDescricaoLogradouroImovelDestinoFiltro("Logradouro Inexistente");
			}
			
			// exception
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "true");
			return true;
		}
	}
	
	public Collection<Bairro> pesquisarBairro(Integer atributoToSet, TransferirImovelLogradouroActionForm form, HttpSession session){
		Collection<Bairro> colecaoBairros = null;
		
		FiltroLogradouroBairro filtroBairro = new FiltroLogradouroBairro();
		filtroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
		
		if(atributoToSet == LOGRADOURO_ORIGEM) {
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, form.getLogradouroImovelOrigemFiltro()));
		}else{
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, form.getLogradouroImovelDestinoFiltro()));
		}
		
		Collection colecaoLogradouroBairro = getFachada().pesquisar(filtroBairro,LogradouroBairro.class.getName());		
		Iterator iteratorBairro = colecaoLogradouroBairro.iterator();
		
		colecaoBairros = new ArrayList<Bairro>();
		
		while(iteratorBairro.hasNext()) {
			LogradouroBairro logradouroBairro = (LogradouroBairro) iteratorBairro.next();
			colecaoBairros.add(logradouroBairro.getBairro());
		}
		
		return colecaoBairros;
	}
	
	public Collection<Cep> pesquisarCep(Integer atributoToSet, TransferirImovelLogradouroActionForm form, HttpSession session){
		Collection<Cep> colecaoCep = null;		
		
		FiltroLogradouroCep filtroCep = new FiltroLogradouroCep();
		filtroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
				
		if(atributoToSet == LOGRADOURO_ORIGEM) {
			filtroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, form.getLogradouroImovelOrigemFiltro()));
		}else{
			filtroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, form.getLogradouroImovelDestinoFiltro()));
		}
		
		Collection colecaoLogradouroCep = getFachada().pesquisar(filtroCep,LogradouroCep.class.getName());
		
		Iterator iteratorCep = colecaoLogradouroCep.iterator();
		
		colecaoCep = new ArrayList<Cep>();
		
		while(iteratorCep.hasNext()) {
			LogradouroCep logradouroCep = (LogradouroCep) iteratorCep.next();
			colecaoCep.add(logradouroCep.getCep());
		}
		
		return colecaoCep;
	}
}
