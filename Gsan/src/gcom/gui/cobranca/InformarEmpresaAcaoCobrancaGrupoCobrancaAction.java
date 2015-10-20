package gcom.gui.cobranca;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoGrupoContrato;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

public class InformarEmpresaAcaoCobrancaGrupoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		HttpSession session = httpServletRequest.getSession();
		
		Fachada fachada = Fachada.getInstancia();
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper = 
				(Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper>) session.getAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper");
		
		if(colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper==null){
			throw new ActionServletException("atencao.selecione.grupo","");
		}
		
		Collection<CobrancaAcao> colecaoCobrancaAcao =  (Collection<CobrancaAcao>) session.getAttribute("colecaoCobrancaAcao");
		
		verificarExistenciaAcaoCobrancaObrigatoriaNaoInformada(colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper, colecaoCobrancaAcao);
		
		InformarEmpresaAcaoCobrancaGrupoCobrancaHelper informarEmpresaAcaoCobrancaGrupoCobrancaHelper = null;
		CobrancaAcaoGrupoContrato cobrancaAcaoGrupoContrato = null;
		Iterator iterator = colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.iterator();
		while(iterator.hasNext()){
			informarEmpresaAcaoCobrancaGrupoCobrancaHelper = (InformarEmpresaAcaoCobrancaGrupoCobrancaHelper)iterator.next();
			cobrancaAcaoGrupoContrato = informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato();
			
			if(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getIndicadorRemovido().compareTo(ConstantesSistema.NAO)==0){
				cobrancaAcaoGrupoContrato.setUltimaAlteracao(new Date());
				if(cobrancaAcaoGrupoContrato.getId()==null){
					fachada.inserir(cobrancaAcaoGrupoContrato);
				}else{
					fachada.atualizar(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato());
				}
				
			}else if(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getIndicadorRemovido().compareTo(ConstantesSistema.SIM)==0
					&& informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato().getId()!=null){
				fachada.remover(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato());
			}
		}
		
		montarPaginaSucesso(httpServletRequest, "Informar Empresa por Ação de Cobrança e por Grupo de Cobrança atualizada com sucesso", "", "");
		
		return retorno;
	}
	
	public void verificarExistenciaAcaoCobrancaObrigatoriaNaoInformada(Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper,
			Collection<CobrancaAcao> colecaoCobrancaAcao){
			
		Iterator iteratorCorbrancaAcao = colecaoCobrancaAcao.iterator();
		CobrancaAcao cobrancaAcao = null;
		
		Iterator iteratorHelper = null;
		InformarEmpresaAcaoCobrancaGrupoCobrancaHelper informarEmpresaAcaoCobrancaGrupoCobrancaHelper = null;
		
		boolean naoAchouCobrancaAcaoObrigatoria = false;
		int totalNaoAchou = 0;
		
		int totalObrigatorio = verificarQuantidadeObrigatoriosColecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper(colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper);
		
		while(iteratorCorbrancaAcao.hasNext()){
			
			cobrancaAcao = (CobrancaAcao) iteratorCorbrancaAcao.next();
			
			if(cobrancaAcao.getIndicadorObrigatoriedade().compareTo(ConstantesSistema.SIM)==0){
				naoAchouCobrancaAcaoObrigatoria = false;
				totalNaoAchou = 0;
				
				if(totalObrigatorio==0){
					throw new ActionServletException("atencao.cobranca.acao.obrigatoria",cobrancaAcao.getDescricaoCobrancaAcao());
				}
				
				iteratorHelper = colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.iterator();
				while(iteratorHelper.hasNext()){
					informarEmpresaAcaoCobrancaGrupoCobrancaHelper = (InformarEmpresaAcaoCobrancaGrupoCobrancaHelper)iteratorHelper.next();
					
					if(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getIndicadorRemovido().compareTo(ConstantesSistema.NAO)==0
							&& (informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato().getCobrancaAcao().getId().compareTo(cobrancaAcao.getId())==0)){
						break;
					}else{
						totalNaoAchou++;
						if(totalNaoAchou==colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.size()){
							naoAchouCobrancaAcaoObrigatoria = true;
							break;
						}
					}
				}
				
				if(naoAchouCobrancaAcaoObrigatoria==true){
					throw new ActionServletException("atencao.cobranca.acao.obrigatoria",cobrancaAcao.getDescricaoCobrancaAcao());
				}
			}
		}
	}
	
	public int verificarQuantidadeObrigatoriosColecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper(Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper){
		
		Iterator iterator = colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.iterator();
		InformarEmpresaAcaoCobrancaGrupoCobrancaHelper informarEmpresaAcaoCobrancaGrupoCobrancaHelper = null;
		int totalObrigatorio = 0;
		
		while(iterator.hasNext()){
			informarEmpresaAcaoCobrancaGrupoCobrancaHelper = (InformarEmpresaAcaoCobrancaGrupoCobrancaHelper) iterator.next();
			if(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getIndicadorRemovido().compareTo(ConstantesSistema.NAO)==0
					&& informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato().getCobrancaAcao().
					getIndicadorObrigatoriedade().compareTo(ConstantesSistema.SIM)==0){
				totalObrigatorio++;
			}
		}
		return totalObrigatorio;
	}
}
