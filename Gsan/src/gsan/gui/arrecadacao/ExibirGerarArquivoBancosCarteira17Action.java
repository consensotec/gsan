package gsan.gui.arrecadacao;
import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.banco.FiltroBanco;
import gsan.fachada.Fachada;
import gsan.faturamento.FaturamentoGrupo;
import gsan.faturamento.FiltroFaturamentoGrupo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesIn;

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
 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
 * [UC1574] - Solicitar Geracao de Arquivo Carteira 17 
 * 
 * @author Diogo Luiz
 * @Date 07/11/2013
 *
 */
public class ExibirGerarArquivoBancosCarteira17Action extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("gerarArquivoBancosCarteira17");

		GerarArquivoBancosCarteira17ActionForm form = (GerarArquivoBancosCarteira17ActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();	
		
		Collection colecaoPesquisa = null;
		
		Collection colecaoIdsFaturamentoGrupo = null;
		
		Collection colecaoFaturamentoGrupo = null;
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = null;
		
		String bancoID = form.getBancoID();		
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {   		

    		FiltroBanco filtroBanco = new FiltroBanco();

    		filtroBanco.adicionarParametro(new ParametroSimples(
                        FiltroBanco.ID, bancoID));

    		filtroBanco.adicionarParametro(new ParametroSimples(
                		FiltroBanco.INDICADOR_USO,
                        ConstantesSistema.INDICADOR_USO_ATIVO));
       
            colecaoPesquisa = fachada.pesquisar(filtroBanco,
                    Banco.class.getName());

            if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {		            		                	
               
            	form.setBancoID("");
            	form.setBancoDescricao("Banco inexistente.");
                httpServletRequest.setAttribute("corBanco", "exception");	                    
                
                httpServletRequest.setAttribute("nomeCampo", "bancoID");
                
            } else {
                Banco objetoBanco = (Banco) Util
                        .retonarObjetoDeColecao(colecaoPesquisa);
                form.setBancoID(String.valueOf(objetoBanco.getId()));
                form.setBancoDescricao(objetoBanco.getDescricao());
                httpServletRequest.setAttribute("corBanco", "valor");
                
                httpServletRequest.setAttribute("nomeCampo", "bancoID");
            }	              
		}			

		if (httpServletRequest.getParameter("criaColecaoBanco") != null
				&& !httpServletRequest.getParameter("criaColecaoBanco").equals("")) {

			boolean mesAnoValido = Util.validarMesAno(form.getMesAnoFaturamento());
			if (!mesAnoValido) {
				throw new ActionServletException("atencao.ano_mes.invalido");
			}
			int ano = Integer.parseInt(form.getMesAnoFaturamento().substring(3, 7));
			if (ano < 2013) {
				throw new ActionServletException("atencao.movimento.maior.2013");
			}	
			
			if(form.getBancoID() == null || form.getBancoID().equals("")){
				throw new ActionServletException("atencao.agencia.sem_banco");
			}
			
			Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(
			form.getMesAnoFaturamento());				
			
			//Grupos Selecionado
			String dadosFaturamentoGrupo = httpServletRequest.getParameter("criaColecaoBanco");					
			
			//Gerando Colecao de Grupos
			colecaoIdsFaturamentoGrupo = 
					this.obterGruposSelecionados(dadosFaturamentoGrupo, anoMesReferencia);			
			
			Collection debitosAutomaticoBancosCarteira17 = (Collection) fachada.pesquisaDebitoAutomaticoCarteira17(
				colecaoIdsFaturamentoGrupo, anoMesReferencia, bancoID);			

			if (debitosAutomaticoBancosCarteira17 != null
					&& !debitosAutomaticoBancosCarteira17.isEmpty()) {
				sessao.setAttribute("debitosAutomaticoBancosCarteira17", debitosAutomaticoBancosCarteira17);
			} else {
				throw new ActionServletException("atencao.nao.movimento.carteira17");
			}	
			
		} else {
			sessao.removeAttribute("debitosAutomaticoBancosCarteira17");
		}
		
		if (httpServletRequest.getParameter("limpaColecao") != null
				&& !httpServletRequest.getParameter("limpaColecao").equals("")) {
			sessao.removeAttribute("debitosAutomaticoBancosCarteira17");
		}

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			
			form.reset(actionMapping, httpServletRequest);
			form.setMesAnoFaturamento("");
			
			filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
					FiltroFaturamentoGrupo.DESCRICAO);

			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
					FiltroFaturamentoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			colecaoFaturamentoGrupo = fachada.pesquisar(
					filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

			sessao.setAttribute("colecaoFaturamentoGrupo",
					colecaoFaturamentoGrupo);			
		}
		
		if( httpServletRequest.getParameter("criaColecaoBanco") != null){
			
			filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
				FiltroFaturamentoGrupo.DESCRICAO);
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimplesIn(
				FiltroFaturamentoGrupo.ID, colecaoIdsFaturamentoGrupo));

			Collection colecaoFaturamentoGrupoSelecionados = 
					fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			
			sessao.setAttribute("colecaoFaturamentoGrupoSelecionados",	
				colecaoFaturamentoGrupoSelecionados);	
			
			Iterator it = colecaoFaturamentoGrupoSelecionados.iterator();	
			
			filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
				FiltroFaturamentoGrupo.DESCRICAO);

		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoFaturamentoGrupoRemovido = fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			
			while(it.hasNext()){
				
				FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
				faturamentoGrupo = (FaturamentoGrupo) it.next();
				
				if(colecaoFaturamentoGrupoRemovido.contains(faturamentoGrupo)){			
					
					colecaoFaturamentoGrupoRemovido.remove(faturamentoGrupo);
					
				}								
			}
			
			sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupoRemovido);			
			
		}else{
			sessao.removeAttribute("colecaoFaturamentoGrupoSelecionados");
		}

		return retorno;
	}		
	
	private Collection obterGruposSelecionados(String faturamentoGrupoStringBuffer, 
			Integer anoMesReferencia){
		
		Collection retorno = null;
		
		if (faturamentoGrupoStringBuffer != null && 
				!faturamentoGrupoStringBuffer.equalsIgnoreCase("")){
			
			retorno = new ArrayList();
			
			String[] arrayFaturamentoGrupo = faturamentoGrupoStringBuffer.split(":");
			String[] arrayGrupoEReferencia = null;
			
			Integer idFaturamentoGrupo = null;
			Integer anoMesFaturamentoGrupo = null;
			
			for (int x=0; x < arrayFaturamentoGrupo.length; x++){
				
				arrayGrupoEReferencia = arrayFaturamentoGrupo[x].split(";");
				
				idFaturamentoGrupo = new Integer(arrayGrupoEReferencia[0]); 
				anoMesFaturamentoGrupo = Util.converterStringParaInteger(arrayGrupoEReferencia[1].trim());				
			
				if (anoMesReferencia > anoMesFaturamentoGrupo) {
					throw new ActionServletException(
							"atencao.faturamento.posterior.faturamento.grupo");
				}
				
				retorno.add(idFaturamentoGrupo);
			}
		}		
		return retorno;
	}	
}
