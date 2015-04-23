package gsan.gui.micromedicao;

import java.util.Collection;

import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarRotaLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
//		 Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InformarRotaLeituristaActionForm form = (InformarRotaLeituristaActionForm) actionForm;
		
		Fachada f = Fachada.getInstancia();
		
		Collection<DadosRota> colDadosRotas = (Collection<DadosRota>) sessao.getAttribute("colecaoRotas");
		
		if(form.getLeitursitaID()!= null && !form.getLeitursitaID().equals("") && 
				!form.getLeitursitaID().equals("-1") && form.getRotas()!=null && 
				form.getRotas().length>0){
						
			Integer[] idsRotas = new Integer[form.getRotas().length];
			for(int i =0; i< form.getRotas().length; i++){
				idsRotas[i] = new Integer(form.getRotas()[i]);
			}
			
			if(!Util.isVazioOrNulo(colDadosRotas)){
				for(DadosRota rota : colDadosRotas){
					boolean achou = true;
					for(int i = 0; i < form.getRotas().length; i++){
						if(idsRotas[i].equals(rota.getId())){
							achou = true;
							break;
						}else{
							achou = false;
						}
					}
					if(!achou){
						FiltroRota filtroRota = new FiltroRota();
						filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rota.getId()));
						filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LEITURISTA_ID, form.getLeitursitaID()));
						Collection<Rota> colRota = f.pesquisar(filtroRota, Rota.class.getName());
						if(!Util.isVazioOrNulo(colRota)){
							Rota novaRota = (Rota) Util.retonarObjetoDeColecao(colRota);
							novaRota.setLeiturista(null);
							f.atualizar(novaRota);
						}
					}
				}
			}
			
			//Chamar Metodo da Fachada para atualizar 
			f.atualizarRelacaoRotaLeiturista(new Integer(form.getLeitursitaID()), idsRotas);
			
			montarPaginaSucesso(httpServletRequest, "Relação Rota X Leiturista Atualizada com sucesso.",
					"Realizar outra atualização de Rota X Leiturista",
					"exibirInformarRotaLeituristaAction.do?menu=sim");
			
		}else{
			//Fornecer os Leituristas e as rotas
			for(DadosRota rota : colDadosRotas){
				FiltroRota filtroRota = new FiltroRota();
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, rota.getId()));
				filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LEITURISTA_ID, form.getLeitursitaID()));
				Collection<Rota> colRota = f.pesquisar(filtroRota, Rota.class.getName());
				if(!Util.isVazioOrNulo(colRota)){
					Rota novaRota = (Rota) Util.retonarObjetoDeColecao(colRota);
					novaRota.setLeiturista(null);
					f.atualizar(novaRota);
				}
			}
			
			montarPaginaSucesso(httpServletRequest, "Relação Rota X Leiturista Atualizada com sucesso.",
					"Realizar outra atualização de Rota X Leiturista",
					"exibirInformarRotaLeituristaAction.do?menu=sim");
		}
		
		return retorno;
	}
	
}
