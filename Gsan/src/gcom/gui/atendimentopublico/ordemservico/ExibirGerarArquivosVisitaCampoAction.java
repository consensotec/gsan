package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC1220] Gerar Arquivo Texto para as Ordens de Serviço de Visita
 * 
 * @author Raimundo Martins
 * @since 09/09/2011
 */

public class ExibirGerarArquivosVisitaCampoAction extends GcomAction {

	private Fachada fachada = Fachada.getInstancia();
	// private HttpSession sessao = null;
	private GerarArquivoVisitaCampoActionForm form = null;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarArquivoTextoVisitaCampo");
		// sessao = httpServletRequest.getSession(false);
		form = (GerarArquivoVisitaCampoActionForm) actionForm;
		form.setComando(httpServletRequest.getParameter("comando"));
		ComandoOrdemSeletiva  comandoOrdemSeletiva = fachada.pesquisarComandoOSSeletiva(new Integer(form.getComando()));
		form.setComandoDesc(comandoOrdemSeletiva.getDescricaoComando());
						
		String tipoPesquisa = httpServletRequest.getParameter("tipoPesquisa");

		if (tipoPesquisa != null && tipoPesquisa.equals("localidade")) {
			if (!existeLocalidade()) {
				httpServletRequest.setAttribute("localidadeInexistente", true);
			}
		} 
		else if (tipoPesquisa != null && tipoPesquisa.equals("setorComercial")) {
			int tipoSetor = Integer.parseInt(httpServletRequest.getParameter("tipoSetor"));
			if (!existeSetorComercial(tipoSetor)) {
				if(tipoSetor == 1){
					httpServletRequest.setAttribute("setorComercialOrigemInexistente", true);
					httpServletRequest.setAttribute("setorComercialDestinoInexistente", true);
				}
				else{
					httpServletRequest.setAttribute("setorComercialDestinoInexistente", true);
				}
			}
		}
		else if (tipoPesquisa != null && tipoPesquisa.equals("quadra")) {
			int tipoQuadra = Integer.parseInt(httpServletRequest.getParameter("tipoQuadra"));
			if (existeQuadra(tipoQuadra) == false) {
				if(tipoQuadra == 1){
					form.setCodigoQuadraInicial("");					
					form.setCodigoQuadraFinal("");					
					httpServletRequest.setAttribute("quadraInicialInexistente", true);
					httpServletRequest.setAttribute("quadraFinalInexistente", true);
					
				}
				else{
					form.setCodigoQuadraFinal("");
					httpServletRequest.setAttribute("quadraFinalInexistente", true);
				}
			}
		}
		else if(tipoPesquisa !=null && tipoPesquisa.equals("consultarQdt")){					
			Integer qtd = fachada.consultarOrdemServicoSeletiva(form.getComando(), form.getLocalidade(), 
				form.getCodigoSetorComercialOrigem(), form.getCodigoSetorComercialDestino(), form.getCodigoQuadraInicial(), 
				form.getCodigoQuadraFinal()).size();			
			
			form.setQtdOsSeletiva(qtd.toString());
			
			
		}
		else {
			form.setLocalidade("");
			form.setNomeLocalidade("");
			form.setIdSetorComercialOrigem("");
			form.setCodigoSetorComercialOrigem("");
			form.setDescricaoSetorComercialOrigem("");
			form.setIdSetorComercialDestino("");
			form.setCodigoSetorComercialDestino("");
			form.setDescricaoSetorComercialDestino("");
			form.setCodigoQuadraFinal("");
			form.setCodigoQuadraInicial("");
			form.setQtdOsSeletiva("");
		}
		
		return retorno;
	}

	private Boolean existeLocalidade() {
		String localidadeCod = form.getLocalidade();

		if (localidadeCod != null && !localidadeCod.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeCod));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setLocalidade(localidade.getId().toString());
				form.setNomeLocalidade(localidade.getDescricao());

				return true;
			} else {
				form.setLocalidade("");
				form.setNomeLocalidade("LOCALIDADE INEXISTENTE");
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * Recebe um inteiro que identifica se o setor comercial é inicial ou final
	 * tipo = 1 (Origem)
	 */
	private Boolean existeSetorComercial(int tipo) {

		String codigoSetorComercial;
		if (tipo == 1) {
			codigoSetorComercial = form.getCodigoSetorComercialOrigem();
		} else {
			codigoSetorComercial = form.getCodigoSetorComercialDestino();
		}

		if (codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(form.getLocalidade())));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorComercial));

			Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
				if (tipo == 1) {
					form.setIdSetorComercialOrigem(setorComercial.getId().toString());
					form.setCodigoSetorComercialOrigem(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialOrigem(setorComercial.getDescricao());
					form.setIdSetorComercialDestino(""+ setorComercial.getId());
					form.setCodigoSetorComercialDestino(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
				} else {
					form.setIdSetorComercialDestino(""+ setorComercial.getId());
					form.setCodigoSetorComercialDestino(""+ setorComercial.getCodigo());
					form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
				}
				return true;
			} else {
				if (tipo == 1) {
					form.setCodigoSetorComercialOrigem("");
					form.setDescricaoSetorComercialOrigem("SETOR COMERCIAL INEXISTENTE");
					form.setCodigoSetorComercialDestino("");
					form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");
				} else {
					form.setCodigoSetorComercialDestino("");
					form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");
				}
				return false;
			}
		} else {
			return false;
		}

	}
	
	private Boolean existeQuadra(int tipo) {
		String quadra;
		if(tipo == 1){
			quadra = form.getCodigoQuadraInicial();
		}
		else{
			quadra = form.getCodigoQuadraFinal();
		}
		
		if(quadra !=null && !quadra.trim().equals("")){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getLocalidade()));
			if(tipo == 1){
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialOrigem()));
			}
			else{
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialDestino()));
			}
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadra));
			Collection<Quadra> colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
			
			if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
				if(tipo == 1){
					form.setCodigoQuadraInicial(quadra);
				}else{
					form.setCodigoQuadraFinal(quadra);
				}
				return true;
			}
		}
		return false;
	}
	
}
