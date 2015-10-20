package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.FiltroSituacaoTransmissaoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.SituacaoTransmissaoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralArquivoTextoHelper;
import gcom.atualizacaocadastral.bean.ConsultarRoteiroDispositivoMovelHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1392] - Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author Davi Menezes
 * @date 26/11/2012
 *
 */
public class ConsultarRoteiroDispositivoMovelAction extends GcomAction {

	/**
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRoteiroDispositivoMovel");
		
		//Form
		ConsultarRoteiroDispositivoMovelActionForm form = (ConsultarRoteiroDispositivoMovelActionForm) actionForm;
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Helper
		ConsultarRoteiroDispositivoMovelHelper helper = new ConsultarRoteiroDispositivoMovelHelper();
		
		//Remover Coleção da Sessão
		sessao.removeAttribute("colecaoArquivoTextoRoteiroDispositivoMovel");
		
		//Validar Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			this.validarLocalidade(form, fachada);
			helper.setIdLocalidade(Integer.parseInt(form.getIdLocalidade()));
			
			//Validar Setor Comercial
			if(form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
				this.validarSetorComercial(form, fachada);
				helper.setCodigoSetorComercial(Integer.parseInt(form.getCodigoSetorComercial()));
				
				//Validar Quadra
				if(form.getNumeroQuadra() != null && !form.getNumeroQuadra().equals("")){
					this.validarQuadra(form, fachada);
					helper.setNumeroQuadra(Integer.parseInt(form.getNumeroQuadra()));
				}
			}else{
				if(form.getNumeroQuadra() != null && !form.getNumeroQuadra().equals("")){
					throw new ActionServletException("atencao.setor_comercial_nao_informado");
				}
			}
		}
		
		//Validar Cadastrador
		if(form.getCadastrador() != null && !form.getCadastrador().equals("") && !form.getCadastrador().equals("-1")){
			helper.setIdCadastrador(Integer.parseInt(form.getCadastrador()));
			//setar cadastrador selecionado no request
			httpServletRequest.setAttribute("cadastradorSelecionado", helper.getIdCadastrador());
		}
		
		//Validar Situação Arquivo
		if(form.getSituacaoArquivo() != null && !form.getSituacaoArquivo().equals("") && !form.getSituacaoArquivo().equals("-1")){
			helper.setSituacaoArquivoTexto(Integer.parseInt(form.getSituacaoArquivo()));
		}
		
		//Pesquisar os arquivos
		Collection<AtualizacaoCadastralArquivoTextoHelper> colecaoArquivoTextoRoteiroDispositivoMovel = 
				fachada.pesquisarArquivoRoteiroAtualizacaoCadastral(helper);
		
		if(!Util.isVazioOrNulo(colecaoArquivoTextoRoteiroDispositivoMovel)){
			//Achou registros de Arquivos
			sessao.setAttribute("achou", "1");
			
			sessao.setAttribute("colecaoArquivoTextoRoteiroDispositivoMovel", colecaoArquivoTextoRoteiroDispositivoMovel);
			
		}else{
			throw new ActionServletException("atencao.nenhum_arquivo_txt_atualizacao_cadastral");
		}

		
		//Pesquisar Cadastrador
		this.pesquisarCadastrador(fachada, httpServletRequest);
		
		//Pesquisar Situação Transmissão
		this.pesquisarSituacaoTransmissao(fachada, httpServletRequest);
		
		return retorno;
	}
	
	/**
	 * Validar Localidade
	 * 
	 * @author Davi Menezes
	 * @date 27/11/2012
	 * 
	 */
	private void validarLocalidade(ConsultarRoteiroDispositivoMovelActionForm form, Fachada fachada){
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if(Util.isVazioOrNulo(colLocalidade)){
			throw new ActionServletException("atencao.localidade.inexistente");
		}else{
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colLocalidade);
			form.setDescricaoLocalidade(localidade.getDescricao());
		}
	}
	
	/**
	 * Validar Setor Comercial
	 * 
	 * @author Davi Menezes
	 * @date 27/11/2012
	 */
	private void validarSetorComercial(ConsultarRoteiroDispositivoMovelActionForm form, Fachada fachada){
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercial()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		if(Util.isVazioOrNulo(colSetorComercial)){
			throw new ActionServletException("atencao.setor_comercial.inexistente");
		}else{
			SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colSetorComercial);
			form.setDescricaoSetorComercial(setorComercial.getDescricao());
		}
	}
	
	/**
	 * Validar Quadra
	 * 
	 * @author Davi Menezes
	 * @date 27/11/2012
	 */
	private void validarQuadra(ConsultarRoteiroDispositivoMovelActionForm form, Fachada fachada){
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getNumeroQuadra()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, form.getCodigoSetorComercial()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, form.getIdLocalidade()));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
		if(Util.isVazioOrNulo(colQuadra)){
			throw new ActionServletException("atencao.quadra.inexistente");
		}else{
			Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colQuadra);
			form.setDescricaoQuadra(quadra.getDescricao());
		}
	}
	
	/**
	 * Pesquisar Cadastrador
	 * 
	 * @author Davi Menezes
	 * @date 26/11/2012
	 * 
	 */
	private void pesquisarCadastrador(Fachada fachada, HttpServletRequest request){
		HttpSession sessao = request.getSession(false);
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista(FiltroLeiturista.USUARIO_NOME);
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ATUALIZACAO_CADASTRAL, ConstantesSistema.SIM));
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
		
		Collection<?> colLeiturista = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
		if(!Util.isVazioOrNulo(colLeiturista)){
			request.setAttribute("colecaoLeiturista", colLeiturista);
			sessao.setAttribute("colecaoLeiturista", colLeiturista);
		}else{
			request.removeAttribute("colecaoLeiturista");
			sessao.removeAttribute("colecaoLeiturista");
		}
	}
	
	/**
	 * Pesquisar Situacao Transmissao
	 * 
	 * @author André Miranda
	 * @date 03/09/2014
	 * 
	 */
	private void pesquisarSituacaoTransmissao(Fachada fachada, HttpServletRequest request){
		FiltroSituacaoTransmissaoAtualizacaoCadastralDM filtroSituacao = new FiltroSituacaoTransmissaoAtualizacaoCadastralDM(FiltroSituacaoTransmissaoAtualizacaoCadastralDM.ID);
		filtroSituacao.adicionarParametro(new ParametroSimples(FiltroSituacaoTransmissaoAtualizacaoCadastralDM.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<?> colSituacaoTransmissao = fachada.pesquisar(filtroSituacao, SituacaoTransmissaoAtualizacaoCadastralDM.class.getName());
		if(!Util.isVazioOrNulo(colSituacaoTransmissao)){
			request.setAttribute("colecaoSituacaoTransmissao", colSituacaoTransmissao);
		}else{
			request.removeAttribute("colecaoSituacaoTransmissao");
		}
	}
	
}
