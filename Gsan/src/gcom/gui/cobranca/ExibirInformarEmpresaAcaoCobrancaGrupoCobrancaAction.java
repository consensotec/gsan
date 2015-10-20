package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoGrupoContrato;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirInformarEmpresaAcaoCobrancaGrupoCobrancaAction");
		InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm informarEmpresaAcaoCobrancaGrupoCobrancaActionForm = 
				(InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm) actionForm;
		HttpSession session = httpServletRequest.getSession();
		
		String parametroPesquisa = httpServletRequest.getParameter("pesquisa");
		
		if(httpServletRequest.getParameter("menu")!=null){
			
			session.removeAttribute("colecaoCobrancaGrupo");
			session.removeAttribute("colecaoEmpresa");
			session.removeAttribute("colecaoContrato");
			session.removeAttribute("colecaoCobrancaAcao");
			session.removeAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper");
			
			pesquisarCobrancaGrupo(session);
			pesquisarEmpresaCobrancaServico(session);
			pesquisarCobrancaAcao(session);
		}
		
		if(parametroPesquisa!=null){
			if(parametroPesquisa.compareTo("contrato")==0){
				pesquisarContratoEmpresaServicoPorEmpresa(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdEmpresa(), session);
			}else if(parametroPesquisa.compareTo("selecionar")==0){
				montarInformarEmpresaAcaoCobrancaGrupoCobranca(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm, session);
			}else if(parametroPesquisa.compareTo("adicionar")==0){
				adicionarInformarEmpresaAcaoCobrancaGrupoCobranca(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm, session);
			}else if(parametroPesquisa.compareTo("remover")==0){
				removerInformarEmpresaAcaoCobrancaGrupoCobranca(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm, session);
			}else if(parametroPesquisa.compareTo("limpar")==0){
				session.removeAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper");
			}
		}
		
		return retorno;
	}
	
	public void pesquisarCobrancaGrupo(HttpSession session){
		
		FiltroCobrancaGrupo  filtroCobrancaGrupo =  new FiltroCobrancaGrupo();
		
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
			FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.SIM));
		
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
			FiltroCobrancaGrupo.INDICADOR_EXECUCAO_AUTOMATICA, ConstantesSistema.SIM));
		
		filtroCobrancaGrupo.adicionarParametro(new ParametroNulo(FiltroCobrancaGrupo.CONTRATO_EMPRESA_SERVICO));
		
		filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);
		
		Collection colecaoCobrancaGrupo = 
				Fachada.getInstancia().pesquisar(filtroCobrancaGrupo,
					CobrancaGrupo.class.getName());
		
		session.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);
	}
	
	public Collection<Empresa> pesquisarEmpresaCobrancaServico(HttpSession session){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		
		Collection<Empresa> colecaoEmpresa = 
				Fachada.getInstancia().obterEmpresasCobranca(ConstantesSistema.SIM, simpleDateFormat.format(new Date()));
		
		session.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		return colecaoEmpresa;
	}
	
	public Collection<ContratoEmpresaServico> pesquisarContratoEmpresaServicoPorEmpresa(Integer idEmpresa,HttpSession session){
	
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
		
		filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.EMPRESA_ID, idEmpresa));
		
		Collection<ContratoEmpresaServico> colecaoContrato = Fachada.getInstancia().pesquisar(
			filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());
		
		session.setAttribute("colecaoContrato", colecaoContrato);
	
		return colecaoContrato;
	}
	
	public Collection<CobrancaAcao> pesquisarCobrancaAcao(HttpSession session){
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection<CobrancaAcao> colecaoCobrancaAcao = Fachada.getInstancia().
				pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		session.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
		
		return colecaoCobrancaAcao;
	}

	public void montarInformarEmpresaAcaoCobrancaGrupoCobranca(InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm 
			informarEmpresaAcaoCobrancaGrupoCobrancaActionForm,HttpSession session){
		
		Collection<CobrancaAcaoGrupoContrato> colecaoInformarEmpresaAcaoCobranca = Fachada.getInstancia().
				obterCobrancaAcaoGrupoContrato(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdGrupo(),
						informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdEmpresa(),
							informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdContrato(),
								informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdCobrancaAcao());
		
		Iterator iterator = colecaoInformarEmpresaAcaoCobranca.iterator();
		
		CobrancaAcaoGrupoContrato cobrancaAcaoGrupoContrato = null;
		InformarEmpresaAcaoCobrancaGrupoCobrancaHelper  informarEmpresaAcaoCobrancaGrupoCobrancaHelper = null;
		
		ArrayList<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper = 
				new ArrayList<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper>();
		while(iterator.hasNext()){
			
			cobrancaAcaoGrupoContrato = (CobrancaAcaoGrupoContrato) iterator.next();
			
			informarEmpresaAcaoCobrancaGrupoCobrancaHelper = new InformarEmpresaAcaoCobrancaGrupoCobrancaHelper();
			informarEmpresaAcaoCobrancaGrupoCobrancaHelper.setCobrancaAcaoGrupoContrato(cobrancaAcaoGrupoContrato);
			informarEmpresaAcaoCobrancaGrupoCobrancaHelper.setIndicadorRemovido(ConstantesSistema.NAO);
			
			colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.add(informarEmpresaAcaoCobrancaGrupoCobrancaHelper);
		}
		
		session.setAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper", colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper);
		
	}
	
	public void removerInformarEmpresaAcaoCobrancaGrupoCobranca(InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm 
			informarEmpresaAcaoCobrancaGrupoCobrancaActionForm,HttpSession session){
		
		ArrayList<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper = (ArrayList<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper>) 
				session.getAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper");
		
		String[] idsRegistrosRemocao = informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdRegistrosRemocao();
		String idsRemocaoPosicaoString = "";
		String[] idsRemocaoPosicao = null;
		
		if(idsRegistrosRemocao == null || idsRegistrosRemocao.length==0){
			 throw new ActionServletException("atencao.registros.nao_selecionados");
        }else{
        	Iterator iterator = null;
        	InformarEmpresaAcaoCobrancaGrupoCobrancaHelper informarEmpresaAcaoCobrancaGrupoCobrancaHelper = null;
        	for(int posicaoRemocao = 0;idsRegistrosRemocao.length>posicaoRemocao;posicaoRemocao++){
        		iterator = colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.iterator();
        		for(int posicao = 0;iterator.hasNext();posicao++){
        			informarEmpresaAcaoCobrancaGrupoCobrancaHelper = (InformarEmpresaAcaoCobrancaGrupoCobrancaHelper) iterator.next();
            		if(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getIndicadorRemovido().compareTo(ConstantesSistema.NAO)==0
            				&& informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato().getCobrancaAcao().getId().
            				compareTo(Integer.valueOf(idsRegistrosRemocao[posicaoRemocao]))==0){
            			idsRemocaoPosicaoString+=posicao+";";
            		}
        		}
        	}
        	
        	idsRemocaoPosicao = idsRemocaoPosicaoString.split(";");
        	for(int posicao = 0;idsRemocaoPosicao.length>posicao;posicao++){
        		colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.get(Integer.valueOf(idsRemocaoPosicao[posicao])).
        		setIndicadorRemovido(ConstantesSistema.SIM);
        	}
        }
		
		session.setAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper", colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper);
	}
	
	public void adicionarInformarEmpresaAcaoCobrancaGrupoCobranca(InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm 
			informarEmpresaAcaoCobrancaGrupoCobrancaActionForm,HttpSession session){
		
		Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper = (Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper>) 
				session.getAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper");
		
		verificarExistenciaCobrancaAcao(colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper, informarEmpresaAcaoCobrancaGrupoCobrancaActionForm);
		
		Iterator iterator = null;
		
		Collection<CobrancaGrupo> colecaoCobrancaGrupo = (Collection<CobrancaGrupo>) 
				session.getAttribute("colecaoCobrancaGrupo");
		CobrancaGrupo cobrancaGrupo = null;
		iterator = colecaoCobrancaGrupo.iterator();
		while(iterator.hasNext()){
			cobrancaGrupo = (CobrancaGrupo) iterator.next();
			if(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdGrupo().compareTo(cobrancaGrupo.getId())==0)break;
		}
		
		Collection<Empresa> colecaoEmpresa = (Collection<Empresa>) 
				session.getAttribute("colecaoEmpresa");
		Empresa empresa = null;
		iterator = colecaoEmpresa.iterator();
		while(iterator.hasNext()){
			empresa = (Empresa) iterator.next();
			if(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdEmpresa().compareTo(empresa.getId())==0)break;
		}
		
		Collection<ContratoEmpresaServico> colecaoContrato = (Collection<ContratoEmpresaServico>) 
				session.getAttribute("colecaoContrato");
		ContratoEmpresaServico contratoEmpresaServico = null;
		iterator = colecaoContrato.iterator();
		while(iterator.hasNext()){
			contratoEmpresaServico = (ContratoEmpresaServico) iterator.next();
			if(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdContrato().compareTo(contratoEmpresaServico.getId())==0)break;
		}
		
		Collection<CobrancaAcao> colecaoCobrancaAcao = (Collection<CobrancaAcao>) 
				session.getAttribute("colecaoCobrancaAcao");
		CobrancaAcao cobrancaAcao = null;
		iterator = colecaoCobrancaAcao.iterator();
		while(iterator.hasNext()){
			cobrancaAcao = (CobrancaAcao)iterator.next();
			if(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdCobrancaAcao().compareTo(cobrancaAcao.getId())==0)break;
		}
		
		CobrancaAcaoGrupoContrato cobrancaAcaoGrupoContrato = new CobrancaAcaoGrupoContrato();
		cobrancaAcaoGrupoContrato.setCobrancaGrupo(cobrancaGrupo);
		contratoEmpresaServico.setEmpresa(empresa);
		cobrancaAcaoGrupoContrato.setContratoEmpresaServico(contratoEmpresaServico);
		cobrancaAcaoGrupoContrato.setCobrancaAcao(cobrancaAcao);
		
		InformarEmpresaAcaoCobrancaGrupoCobrancaHelper informarEmpresaAcaoCobrancaGrupoCobrancaHelper = new InformarEmpresaAcaoCobrancaGrupoCobrancaHelper();
		informarEmpresaAcaoCobrancaGrupoCobrancaHelper.setCobrancaAcaoGrupoContrato(cobrancaAcaoGrupoContrato);
		informarEmpresaAcaoCobrancaGrupoCobrancaHelper.setIndicadorRemovido(ConstantesSistema.NAO);
		
		colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.add(informarEmpresaAcaoCobrancaGrupoCobrancaHelper);
		
		session.setAttribute("colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper", colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper);
	}
	
	public void verificarExistenciaCobrancaAcao (Collection<InformarEmpresaAcaoCobrancaGrupoCobrancaHelper> colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper,
			InformarEmpresaAcaoCobrancaGrupoCobrancaActionForm informarEmpresaAcaoCobrancaGrupoCobrancaActionForm){
		
		InformarEmpresaAcaoCobrancaGrupoCobrancaHelper informarEmpresaAcaoCobrancaGrupoCobrancaHelper = null;
		
		Iterator iterator = colecaoInformarEmpresaAcaoCobrancaGrupoCobrancaHelper.iterator();
		while(iterator.hasNext()){
			informarEmpresaAcaoCobrancaGrupoCobrancaHelper = (InformarEmpresaAcaoCobrancaGrupoCobrancaHelper) iterator.next();
			if(informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getIndicadorRemovido().compareTo(ConstantesSistema.NAO)==0
					&& informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato().getCobrancaAcao().getId().
					compareTo(informarEmpresaAcaoCobrancaGrupoCobrancaActionForm.getIdCobrancaAcao())==0){
				throw new ActionServletException("atencao.verificar.existencia.cobranca.acao",informarEmpresaAcaoCobrancaGrupoCobrancaHelper.getCobrancaAcaoGrupoContrato().getCobrancaAcao().getDescricaoCobrancaAcao());
			}
		}
	}
}
