package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

public class GerarArquivoTextoVisitaCampo extends GcomAction{
	
	/**
	 * Action que gera o arquivo texto para as Os Visita
	 * 
	 * [UC 1220] Gerar Arquivo Texto para as Os de Visita
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
	
	private Fachada fachada = Fachada.getInstancia();	
	private GerarArquivoVisitaCampoActionForm form = null;
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		form = (GerarArquivoVisitaCampoActionForm) actionForm;
		
		/**
		 * [UC1220] Gerar Arquivo Texto para as Os de Visita
		 * [FS0004] Verificar preenchimento campos obrigatórios
		 * 
		 * OBS: [FS0004] é Junção das [FS0001] e [FS0002]
		 **/ 
		if(!existeLocalidade() && !existeSetorComercial()){
			throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
		}
		else if(!existeLocalidade()){
			throw new ActionServletException("atencao.localidade.inexistente");
		}
		else if(!existeSetorComercial()){
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}
		else{
			/*verifica se a quadra foi informada*/
			if(form.getCodigoQuadraInicial() !=null &&
			   !form.getCodigoQuadraInicial().trim().equals("") &&
			   form.getCodigoQuadraFinal() !=null &&
			   !form.getCodigoQuadraFinal().trim().equals("")){				
				if(existeQuadra()){
					if(existeArquivo() == false){
						inserirDados(httpServletRequest);
					}
				}
				
			}
			else{
				if(existeArquivo() == false){
					inserirDados(httpServletRequest);
				}				
			}
			
		}
		return retorno;
	}
	
	/**
	 * [UC1220] Gerar Arquivo Texto para as Os de Visita
	 * [FS0001] Verificar existência da localidade 
	 * */
	private Boolean existeLocalidade(){
		String localidadeCod = form.getLocalidade();

		if (localidadeCod != null && !localidadeCod.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, localidadeCod));
	
			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
	
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
	
	/**
	 * 
	 * [UC1220] Gerar Arquivo Texto para as Os de Visita
	 * [FS0002] Verificar existência do setor 
	 * 
	 * Recebe um inteiro que identifica
	 * se o setor comercial é inicial 
	 * ou final
	 * tipo = 1 (Origem) 
	 * */ 
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
		
			Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
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
		
			Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
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
	
	/**
	 * 
	 * [UC1220] Gerar Arquivo Texto para as Os de Visita
	 * [FS0003] Verificar existência da quadra 
	 * */
	private Boolean existeQuadra(){
		String quadraIni = form.getCodigoQuadraInicial();
		String quadraFin = form.getCodigoQuadraFinal();
		
		Boolean retornoIni = false;
		Boolean retornoFin = false;
		FiltroQuadra filtroQuadraInicial = new FiltroQuadra();
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getLocalidade()));
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialOrigem()));
		filtroQuadraInicial.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraIni));
		Collection<Quadra> colecaoQuadraInicial = fachada.pesquisar(filtroQuadraInicial, Quadra.class.getName());
		
		FiltroQuadra filtroQuadraFinal = new FiltroQuadra();
		filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getLocalidade()));
		filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercialDestino()));
		filtroQuadraFinal.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, quadraFin));
		Collection<Quadra> colecaoQuadraFinal = fachada.pesquisar(filtroQuadraFinal, Quadra.class.getName());
		
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
	
	private void inserirDados(HttpServletRequest httpServletRequest){
		Boolean inserido = fachada.inserirDadosGeracaoArquivoTextoOSVisitaCampo(form.getComando(), form.getLocalidade(), 
			form.getCodigoSetorComercialOrigem(), form.getCodigoSetorComercialDestino(), form.getCodigoQuadraInicial(), 
			form.getCodigoQuadraFinal());			
	 
		if(inserido){
			montarPaginaSucesso(httpServletRequest, "Arquivo Texto inserido com sucesso",
				"Inserir Outro Arquivo Texto Para o Comando Selecionado",
				"exibirGerarArquivosVisitaCampoAction.do?comando="+form.getComando(),
				"exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim",
				"Consultar Comando de OS Seletiva de Inspeção Anormalidade"				
				);
		}
		else{
			throw new ActionServletException("Arquivo texto não inserido");
		}
	}
	
	private Boolean existeArquivo(){
		Boolean existe = fachada.verificarExistenciaArquivoTextoVisitaCampo(form.getComando(), form.getLocalidade(), 
			form.getCodigoSetorComercialOrigem(), form.getCodigoSetorComercialDestino(), form.getCodigoQuadraInicial(), 
			form.getCodigoQuadraFinal());
		if(existe){
			throw new ActionServletException("atencao.existe_arquivo_texto_com_esses_parametros");
		}
		else{
			return false;
		}
	}

}
