package gcom.gui.portal.caern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.portal.caern.ConsultarPagamentoFaturaPortalCaernHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesNotIn;

/**
 * 
 * [RM5976][UC1532] Consultar Arrecadadores.
 * 
 * @author Maxwell Moreira 
 * @since 22/07/2013
 *
 */

public class ExibirConsultarPagamentoFaturaPortalCaernAction extends GcomAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarPagamentoFaturaPortalCaernAction");
		request.setAttribute("voltarInformacoes", true);
	
		Collection<Integer> colecao =  new ArrayList<Integer>();
		colecao.add(new Integer(0));

		FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
		filtroArrecadadorContrato.adicionarParametro( new ParametroNaoNulo(FiltroArrecadadorContrato.NUMERO_SEQUENCIAL_ARQUIVO_RETORNO_DEBITO_AUTOMATICO));
		filtroArrecadadorContrato.adicionarParametro( new ParametroSimplesNotIn(
				FiltroArrecadadorContrato.NUMERO_SEQUENCIAL_ARQUIVO_RETORNO_DEBITO_AUTOMATICO, colecao));
		
		filtroArrecadadorContrato.adicionarParametro( new ParametroSimples(
			FiltroArrecadadorContrato.ARRECADADOR_INDICADOR_USO, ConstantesSistema.SIM));
	
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade(FiltroArrecadadorContrato.ARRECADADOR);
		filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade(FiltroArrecadadorContrato.ARRECADADOR_CLIENTE);
		filtroArrecadadorContrato.setCampoOrderBy(FiltroArrecadadorContrato.ARRECADADOR_CODIGO_AGENTE);

		Collection<ArrecadadorContrato> colecaoArrecadadorContrato =  this.getFachada().pesquisar(filtroArrecadadorContrato , 
				ArrecadadorContrato.class.getName());
		
		if (colecaoArrecadadorContrato != null && !colecaoArrecadadorContrato.isEmpty() ) {
			
			Collection<ConsultarPagamentoFaturaPortalCaernHelper> colecaoHelper =   new ArrayList<ConsultarPagamentoFaturaPortalCaernHelper>();
			Iterator iteratorColecao =  colecaoArrecadadorContrato.iterator();
			
			ConsultarPagamentoFaturaPortalCaernHelper helper  = null;
			while(iteratorColecao.hasNext()) {
				
				ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato) iteratorColecao.next();
				
				helper  = new ConsultarPagamentoFaturaPortalCaernHelper();
				
				helper.setCodigo(arrecadadorContrato.getArrecadador().getCodigoAgente().toString());
				helper.setNome(arrecadadorContrato.getArrecadador().getCliente().getNome());
				
				if(!colecaoHelper.contains(helper)){
					colecaoHelper.add(helper);	
				}
			}
			
			request.setAttribute("helperPagamentoFatura", colecaoHelper);
		}
		
		String ip = request.getRemoteAddr();
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.ONDE_PAGAR_FATURA, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);

		
		return retorno;
	}

}
