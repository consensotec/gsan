package gsan.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.cadastro.localidade.FiltroLocalidade;
import gsan.cadastro.localidade.FiltroQuadra;
import gsan.cadastro.localidade.FiltroSetorComercial;
import gsan.cadastro.localidade.Localidade;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.SetorComercial;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.atendimentopublico.ordemservico.RelatorioQuantImovOSSeletiva;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.filtro.ParametroSimples;

public class GerarRelatorioQuantImovOSSeletivaAction extends
ExibidorProcessamentoTarefaRelatorio{
	
	/**
	 * Action que gera o relatório de documentos não aceitos
	 * 
	 * [UC 1223] Gerar Relatório Quantitativo dos Imóves das ordes Seletivas
	 * 
	 * @author Raimundo Martins
	 * @date 15/09/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	private HttpSession sessao = null;
	
	private GerarArquivoVisitaCampoActionForm form = null;

	private TarefaRelatorio relatorio = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;		
		form = (GerarArquivoVisitaCampoActionForm) actionForm;
		sessao = httpServletRequest.getSession(false);
		relatorio = new RelatorioQuantImovOSSeletiva((Usuario) sessao.getAttribute("usuarioLogado"));
		
		relatorio.addParametro("comando", form.getComando());
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		String local = form.getLocalidade();
		String setIni = form.getCodigoSetorComercialOrigem();
		String setFin = form.getCodigoSetorComercialDestino();
		String qdrIni = form.getCodigoQuadraInicial();
		String qdrFin = form.getCodigoQuadraFinal();
				
		
		if(local !=null && !local.trim().equals("")){
			existeLocalidade();		
		}		
		if(setIni !=null && setFin !=null && !setIni.trim().equals("") && !setFin.trim().equals("")){
			existeSetorComercial();			
		}
		if(qdrIni !=null && qdrFin !=null && !qdrIni.trim().equals("") && !qdrFin.trim().equals("")){
			existeQuadra();			
		}
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorio.addParametro("localidade", local);
		relatorio.addParametro("setorInicial", setIni);
		relatorio.addParametro("setorFinal", setFin);
		relatorio.addParametro("quadraInicial", qdrIni);
		relatorio.addParametro("quadraFinal", qdrFin);		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, Integer.parseInt(tipoRelatorio), httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;
	}
	
	private Boolean existeLocalidade(){
		String localidadeCod = form.getLocalidade();

		if (localidadeCod != null && !localidadeCod.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeCod));
	
			Collection<Localidade> colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
	
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				return true;
			}
			else{								
				throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade");
			}
		}
		else{
			throw new ActionServletException("atencao.pesquisa_inexistente", "Localidade");
		}
	}
	
	private Boolean existeSetorComercial(){
		
		boolean retornoOrigem = false;
		boolean retornoDestino = false;
		String codigoSetorComercialOrigem;
		String codigoSetorComercialDestino;
		
		codigoSetorComercialOrigem = form.getCodigoSetorComercialOrigem(); 
		codigoSetorComercialDestino = form.getCodigoSetorComercialDestino();
		
		
		if (codigoSetorComercialOrigem != null && !codigoSetorComercialOrigem.trim().equals("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(form.getLocalidade())));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorComercialOrigem));
		
			Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {				
				retornoOrigem = true;
			}
			else{				
				throw new ActionServletException("atencao.pesquisa_inexistente", "Setor Comercial Inicial");
			}
			
		}
		
		if (codigoSetorComercialDestino != null && !codigoSetorComercialDestino.trim().equals("")) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, Integer.parseInt(form.getLocalidade())));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,codigoSetorComercialDestino));
		
			Collection<SetorComercial> colecaoSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {				
				retornoDestino = true;
			}
			else{				
				throw new ActionServletException("atencao.pesquisa_inexistente", "Setor Comercial Final");	
			}
			
		}
		
		if(retornoOrigem && retornoDestino){
			return true;
		}
		else{
			return false;
		}
	}
	
	private Boolean existeQuadra(){
		String quadraIni = form.getCodigoQuadraInicial();
		String quadraFin = form.getCodigoQuadraFinal();
		
		Boolean retornoIni = false;
		Boolean retornoFin = false;
		FiltroQuadra filtroQuadraInicial = new FiltroQuadra();
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getLocalidade()));
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialOrigem()));
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraIni));
		Collection<Quadra> colecaoQuadraInicial = Fachada.getInstancia().pesquisar(filtroQuadraInicial, Quadra.class.getName());
		
		FiltroQuadra filtroQuadraFinal = new FiltroQuadra();
		filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getLocalidade()));
		filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialDestino()));
		filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraFin));
		Collection<Quadra> colecaoQuadraFinal = Fachada.getInstancia().pesquisar(filtroQuadraFinal, Quadra.class.getName());
		
		if(colecaoQuadraInicial !=null && !colecaoQuadraInicial.isEmpty()){
			retornoIni = true;
		}
		else{			
			throw new ActionServletException("atencao.pesquisa_inexistente", "Quadra Inicial");
		}
		if(colecaoQuadraFinal !=null && !colecaoQuadraFinal.isEmpty()){
			retornoFin = true;
		}
		else{			
			throw new ActionServletException("atencao.pesquisa_inexistente", "Quadra Final");
		}
		if(retornoIni && retornoFin){
			return true;
		}else{
			return false;
		}
	}

}
