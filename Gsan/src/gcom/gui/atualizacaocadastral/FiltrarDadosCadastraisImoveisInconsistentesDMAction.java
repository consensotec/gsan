package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.RetornoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.bean.DadosMovimentoAtualizacaoCadastralDMHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Jonathan Marcos
 * @since 24/10/2014
 */
public class FiltrarDadosCadastraisImoveisInconsistentesDMAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		FiltrarDadosCadastraisImoveisInconsistentesDMActionForm filtrarDadosCadastraisImoveisInconsistentesDMActionForm = 
				(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm) actionForm;
		
		String objetoConsultaFiltro = httpServletRequest.getParameter("objetoConsultaFiltro");
		if(objetoConsultaFiltro!=null){
			processarFiltro(filtrarDadosCadastraisImoveisInconsistentesDMActionForm, 
					httpServletRequest, objetoConsultaFiltro);
		}
		
		return retorno(actionMapping, objetoConsultaFiltro);
	}
	
	/**
	 * Método reponsável por<br>
	 * pesquisar os dados do movimento
	 * @author Jonathan Marcos
	 * @since 24/10/2014
	 * @param httpServletRequest
	 * @param sessao
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosMovimento(HttpServletRequest httpServletRequest, 
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
	
		Date periodoInicial = null;
		if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial() != null 
				&& !filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial().equals("")){
			periodoInicial = Util.converteStringParaDate(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial());
		}
		
		Date periodoFinal = null;
		if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal() != null 
				&& !filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal().equals("")){
			periodoFinal = Util.converteStringParaDate(
					filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal());
		}
		
		//adicionando validação de idlocalidade e codigosetorcomercial
		//Adicionado por: Bruno Barreto, 06/01/2015
		if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade()!=null 
				&& !"".equals(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade())){
			Localidade localidade = this.pesquisarLocalidade(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade());
			if(localidade==null){
				throw new ActionServletException("atencao.localidade.inexistente");
			}
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDescricaoLocalidade(localidade.getDescricao());
		}
		
		if(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial()!=null 
				&& !"".equals(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial())){
			SetorComercial setorComercial = this.pesquisarSetorComercial(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial());
			if(setorComercial==null){
				throw new ActionServletException("atencao.setor_comercial.inexistente");
			}
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDescricaoSetorComercial(setorComercial.getDescricao());
		}
		
		if(periodoInicial != null && periodoFinal == null){
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Movimento Final");
		}else if(periodoFinal != null && periodoInicial == null){
			throw new ActionServletException("atencao.campo.informado",
					"Periodo de Movimento Inicial");
		}else if (periodoFinal != null && Util.compararData(periodoFinal, new Date()) > 0) {
			throw new ActionServletException("atencao.data_final_menor_data_atual",
					"Periodo de Movimento Final");
		}else if ( periodoInicial != null && periodoFinal != null && Util.compararData(periodoInicial, periodoFinal) > 0 ) {
			String[] mensagem = new String[2];
			mensagem[0] = "Periodo de Movimento Inicial";
			mensagem[1] = "Periodo de Movimento Final";
			throw new ActionServletException("atencao.intervalo_final_menor_igual_inicial",null, mensagem);
		}
			
		//este método verifica se ficou armazenado algum "lixo" no actionForm entre as ações de limpeza do mesmo
		this.limparDadosQuadrasESetorComercial(filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosMovimentoAtualizacaoCadastralHelper = Fachada.getInstancia().
				pesquisarDadosMovimentoAtualizacaoCadastralDMHelper(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade(),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial(),
						transformarArrayIntegerStringComVirgulas(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados()), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdCadastrador(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdTipoInconsistencia(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa());
	
		if(colecaoDadosMovimentoAtualizacaoCadastralHelper != null && !colecaoDadosMovimentoAtualizacaoCadastralHelper.isEmpty()){
			httpServletRequest.setAttribute("colecaoDadosMovimentoAtualizacaoCadastralHelper", colecaoDadosMovimentoAtualizacaoCadastralHelper);
			DadosMovimentoAtualizacaoCadastralDMHelper helperTotal = null;
			Iterator<DadosMovimentoAtualizacaoCadastralDMHelper> iteratorHelperTotal = colecaoDadosMovimentoAtualizacaoCadastralHelper.iterator();
			while( iteratorHelperTotal.hasNext() ) {
				helperTotal = (DadosMovimentoAtualizacaoCadastralDMHelper) iteratorHelperTotal.next();
			}
			httpServletRequest.setAttribute("total", helperTotal.getTotal());
			httpServletRequest.setAttribute("totalPendentes", helperTotal.getTotalPendente());
			httpServletRequest.setAttribute("totalPendentesInscritos", helperTotal.getTotalPendenteInscricao());
			httpServletRequest.setAttribute("telaRetorno", httpServletRequest.getParameter("objetoConsultaFiltro").toString());
		} else {
			throw new ActionServletException("atencao.nao_existe_imovel_para_filtro_informado");
		}
	}

	/**
	 * Método reponsável por<br>
	 * pesquisar os dados do imóvel
	 * @author Jonathan Marcos
	 * @since 24/10/2014
	 * @param httpServletRequest
	 * @param sessao
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosImovel(HttpServletRequest httpServletRequest, 
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
	
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralDMHelper(null,
						null,
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdImovel(),
						null, 
						null, 
						null,
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null);
		if(colecaoDadosImoveisHelper != null && !colecaoDadosImoveisHelper.isEmpty()){
			httpServletRequest.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
			httpServletRequest.setAttribute("telaRetorno", "filtro");
			httpServletRequest.setAttribute("objetoConsultaFiltro", "imovel");
		}else{
			throw new ActionServletException("atencao.nao_existe_dados_movimento_imovel");
		}
		filtrarDadosCadastraisImoveisInconsistentesDMActionForm.limparMovimento();
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar dados do cliente
	 * @author Jonathan Marcos
	 * @since 24/10/2014
	 * @param request
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosCliente(HttpServletRequest httpServletRequest, 
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
		
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralDMHelper(null,
						null,
						null,
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getCodigoCliente(), 
						null, 
						null,
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null, 
						null);
		
		if(colecaoDadosImoveisHelper != null && !colecaoDadosImoveisHelper.isEmpty()){
			httpServletRequest.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
			httpServletRequest.setAttribute("telaRetorno", "filtro");
			httpServletRequest.setAttribute("objetoConsultaFiltro", "cliente");
		} else {
			throw new ActionServletException("atencao.nao_existe_dados_movimento_cliente");
		}
		filtrarDadosCadastraisImoveisInconsistentesDMActionForm.limparMovimento();
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar dados  documento
	 * @author Jonathan Marcos
	 * @since 24/10/2014
	 * @param request
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosDocumento(HttpServletRequest httpServletRequest, 
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
		if(!Util.validacaoCPF(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getNumeroDocumento()) 
				&& !Util.validacaoCNPJ(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getNumeroDocumento()) ) {
			throw new ActionServletException("atencao.documento_invalido");
		} else {
			
			Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
					pesquisarDadosImovelAtualizacaoCadastralDMHelper(null,
							null,
							null,
							null, 
							filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getNumeroDocumento(), 
							null,
							null, 
							null, 
							null, 
							null, 
							null, 
							null, 
							null, 
							null, 
							null);
			
			if(colecaoDadosImoveisHelper != null && !colecaoDadosImoveisHelper.isEmpty()){
				httpServletRequest.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
				httpServletRequest.setAttribute("telaRetorno", "filtro");
				httpServletRequest.setAttribute("objetoConsultaFiltro", "documento");
			} else {
				throw new ActionServletException("atencao.nao_existe_dados_movimento_documento");
			}
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.limparMovimento();
		}
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar dados do imovel<br>
	 * total
	 * @author Jonathan Marcos
	 * @since 27/10/2014
	 * @param httpServletRequest
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosImovelTotal(HttpServletRequest httpServletRequest, 
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
		
		filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdRegistro(null);
		
		if(httpServletRequest.getParameter("id") != null && !httpServletRequest.getParameter("id").equals("") ) {
			String[] idEHData = httpServletRequest.getParameter("id").toString().split("[|]") ;
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdParametroTabelaAtualizacaoCadastral(idEHData[0]);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDataRecebimento(idEHData[1]);
		}
		
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralDMHelper(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdParametroTabelaAtualizacaoCadastral(),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getDataRecebimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdImovel(),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getCodigoCliente(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getNumeroDocumento(), 
						null,
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial(), 
						transformarArrayIntegerStringComVirgulas(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados()), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdCadastrador(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdTipoInconsistencia(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa());
		
		httpServletRequest.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		httpServletRequest.setAttribute("telaRetorno", httpServletRequest.getParameter("objetoConsultaFiltroAnterior"));
		
		httpServletRequest.setAttribute("objetoConsultaFiltro", "total");
		httpServletRequest.setAttribute("id", httpServletRequest.getParameter("id"));
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar dados do imovel<br>
	 * pendente
	 * @author Jonathan Marcos
	 * @since 27/10/2014
	 * @param httpServletRequest
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosImovelPendente(HttpServletRequest httpServletRequest,
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
		
		filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdRegistro(null);
		
		if(httpServletRequest.getParameter("id") != null && !httpServletRequest.getParameter("id").equals("")) {
			String[] idEHData = httpServletRequest.getParameter("id").toString().split("[|]") ;
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdParametroTabelaAtualizacaoCadastral(idEHData[0]);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDataRecebimento(idEHData[1]);
		}
		
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralDMHelper(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdParametroTabelaAtualizacaoCadastral(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getDataRecebimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdImovel(),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getCodigoCliente(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getNumeroDocumento(), 
						String.valueOf(RetornoAtualizacaoCadastralDM.SITUACAO_PENDENTE),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial(), 
						transformarArrayIntegerStringComVirgulas(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados()), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdCadastrador(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdTipoInconsistencia(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa() );
		
		httpServletRequest.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		httpServletRequest.setAttribute("telaRetorno", httpServletRequest.getParameter("objetoConsultaFiltroAnterior"));
		
		httpServletRequest.setAttribute("objetoConsultaFiltro", "pendente");
		httpServletRequest.setAttribute("id", httpServletRequest.getParameter("id"));
	}
	
	/**
	 * Método responsável por<br>
	 * pesquisar dados do imovel<br>
	 * pendente Inscrição
	 * @author Jonathan Marcos
	 * @since 27/10/2014
	 * @param httpServletRequest
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void pesquisarDadosImovelPendenteInscricao(HttpServletRequest httpServletRequest,
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
				filtrarDadosCadastraisImoveisInconsistentesDMActionForm) {
		if(httpServletRequest.getParameter("id") != null && !httpServletRequest.getParameter("id").equals("")) {
			String[] idEHData = httpServletRequest.getParameter("id").toString().split("[|]") ;
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setIdParametroTabelaAtualizacaoCadastral(idEHData[0]);
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm.setDataRecebimento(idEHData[1]);
		}
		
		Collection<DadosMovimentoAtualizacaoCadastralDMHelper> colecaoDadosImoveisHelper = Fachada.getInstancia().
				pesquisarDadosImovelAtualizacaoCadastralDMHelper(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdParametroTabelaAtualizacaoCadastral(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getDataRecebimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdImovel(),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getCodigoCliente(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getNumeroDocumento(), 
						String.valueOf(RetornoAtualizacaoCadastralDM.PENDENTE_POR_INSCRICAO),
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdLocalidade(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdSetorComercial(), 
						transformarArrayIntegerStringComVirgulas(filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdQuadraSelecionados()), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdCadastrador(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoInicial(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getPeriodoMovimentoFinal(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIndicadorSituacaoMovimento(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdTipoInconsistencia(), 
						filtrarDadosCadastraisImoveisInconsistentesDMActionForm.getIdEmpresa());
		
		httpServletRequest.setAttribute("colecaoDadosImoveisHelper", colecaoDadosImoveisHelper);
		httpServletRequest.setAttribute("telaRetorno", httpServletRequest.getParameter("objetoConsultaFiltroAnterior"));
		
		httpServletRequest.setAttribute("objetoConsultaFiltro", "pendenteInscricao");
		httpServletRequest.setAttribute("id", httpServletRequest.getParameter("id"));
	}
	
	/**
	 * Método responsável por<br>
	 * processar o filtro
	 * @author Jonathan Marcos
	 * @since 24/10/2014
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 * @param httpServletRequest
	 * @param objetoConsultaFiltro
	 */
	private void processarFiltro(FiltrarDadosCadastraisImoveisInconsistentesDMActionForm 
			filtrarDadosCadastraisImoveisInconsistentesDMActionForm,HttpServletRequest httpServletRequest,
				String objetoConsultaFiltro){
		if(objetoConsultaFiltro.compareTo("movimento")==0){
			pesquisarDadosMovimento(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsultaFiltro.compareTo("imovel")==0){
			pesquisarDadosImovel(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsultaFiltro.compareTo("cliente")==0){
			pesquisarDadosCliente(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsultaFiltro.compareTo("documento")==0){
			pesquisarDadosDocumento(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsultaFiltro.compareTo("total")==0){
			pesquisarDadosImovelTotal(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsultaFiltro.compareTo("pendente")==0){
			pesquisarDadosImovelPendente(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}else if(objetoConsultaFiltro.compareTo("pendenteInscricao")==0){
			pesquisarDadosImovelPendenteInscricao(httpServletRequest, filtrarDadosCadastraisImoveisInconsistentesDMActionForm);
		}
	}
	
	/**
	 * Método responsável por<br>
	 * verificar o retorno
	 * @author Jonathan Marcos
	 * @since 27/10/2014
	 * @param actionMapping
	 * @param objetoConsulta
	 * @return ActionForward
	 */
	private ActionForward retorno(ActionMapping actionMapping,String objetoConsulta){
		ActionForward actionForward = null;
		if(objetoConsulta!=null){
			if(objetoConsulta.compareTo("movimento")==0){
				actionForward = actionMapping.
						findForward("filtrarDadosCadastraisImoveisInconsistentesMovimentoDM");
			}else{
				actionForward = actionMapping.
						findForward("filtrarDadosCadastraisImoveisInconsistentesImovelDM");
			}
		}
		return actionForward;
	}
	
	/**
	 * Método responsável por<br>
	 * transformar  um array de Integer<br>
	 * em String com virgulas
	 * @author Jonathan Marcos
	 * @since 24/10/2014
	 * @param arrayInteger
	 * @return String
	 */
	private String transformarArrayIntegerStringComVirgulas(Integer[] arrayInteger){
		String dadosTransformados = null;
		if(arrayInteger!=null && arrayInteger.length!=0){
			dadosTransformados = "";
			for(int posicao = 0;posicao<arrayInteger.length;posicao++){
				dadosTransformados+=arrayInteger[posicao].toString();
				if(posicao!=arrayInteger.length-1){
					dadosTransformados+=",";
				}
			}
		}
		return dadosTransformados;
	}
	
	/**
	 * [UC1297] - Atualizar Dados Cadastrais para imóveis Inconstentes
	 * [FS0005] - Verificar Existencia do Setor Comercial
	 *
	 * @author Bruno Sá Barreto
	 * @date 06/01/2015
	 *
	 * @param idSetorComercial
	 * @return SetorComercial
	 */
	private SetorComercial pesquisarSetorComercial(String idSetorComercial) {
		SetorComercial result = null;
		FiltroSetorComercial filtro = new FiltroSetorComercial();
		filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
		Collection<?> colecao = Fachada.getInstancia().pesquisar(filtro, SetorComercial.class.getName());
		if (!Util.isVazioOrNulo(colecao)) {
			result = (gcom.cadastro.localidade.SetorComercial) Util.retonarObjetoDeColecao(colecao);
		}
		return result;
	}

	/**
	 * [UC1297] - Atualizar Dados Cadastrais para imóveis Inconstentes
	 * [FS0004] - Verificar Existencia da Localidade
	 *
	 * @author Bruno Sá Barreto
	 * @date 06/01/2015
	 *
	 * @param idLocalidade
	 * @return Localidade
	 */
	private Localidade pesquisarLocalidade(String idLocalidade) {
		Localidade result = null;
		FiltroLocalidade filtro = new FiltroLocalidade();
		filtro.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
		Collection<?> colecao = Fachada.getInstancia().pesquisar(filtro, Localidade.class.getName());
		if (!Util.isVazioOrNulo(colecao)) {
			result = (gcom.cadastro.localidade.Localidade) Util.retonarObjetoDeColecao(colecao);
		}
		return result;
	}
	

	/**
	 * Este método verifica se ficou armazenado algum "lixo"
	 * no actionForm entre as ações de limpeza do mesmo
	 * [UC1297] - Atualizar Dados Cadastrais para imóveis Inconstentes
	 * [FS0004] - Verificar Existencia da Localidade
	 
	 * @author Bruno Sá Barreto
	 * @date 13/01/2015
	 *
	 * @param filtrarDadosCadastraisImoveisInconsistentesDMActionForm
	 */
	private void limparDadosQuadrasESetorComercial(
			FiltrarDadosCadastraisImoveisInconsistentesDMActionForm form) {
		if("".equals(form.getIdLocalidade())){
			form.setIdSetorComercial("");
			form.setDescricaoSetorComercial("");
			form.setDescricaoLocalidade("");
			form.setIdQuadraSelecionados(null);
			form.setIdQuadra(null);
		}
	}
}
