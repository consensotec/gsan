package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.mobile.execucaoordemservico.ArquivoTextoOSCobranca;
import gcom.mobile.execucaoordemservico.FiltroArquivoTextoOSCobranca;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio,Jean Varela
 * @date 04/10/2010,01/12/2015
 */


public class TransmitirArquivoTxtOSCobrancaSmartphoneOffLineAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
								 HttpServletResponse response) {
		                                                                            
		ActionForward retorno = actionMapping.findForward("telaSucessoBaixarArquivoTxtAction");
	
		
		ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form = (ConsultarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;

		String imei = request.getParameter("imei");
		String idSituacaoArquivo = request.getParameter("idSituacaoArquivo");		
		String identificadorArquivo = request.getParameter("idArquivo");

		
		if (Util.verificarNaoVazio(imei) && idSituacaoArquivo != null &&
			!idSituacaoArquivo.isEmpty() && identificadorArquivo != null && !identificadorArquivo.isEmpty()) {
			Integer idSituacao = Integer.parseInt(idSituacaoArquivo);
			Integer idArquivo = Integer.parseInt(identificadorArquivo);
			
			if (!idSituacao.equals(SituacaoTransmissaoLeitura.LIBERADO)) {
				throw new ActionServletException("atencao.arquivo_ja_baixado");
			}
			
			byte[] arquivo = buscaArquivo(imei,idArquivo);
			
			try {

				ArquivoTextoOSCobranca arquivoTextoOSCobranca = pesquisaArquivoTexto(imei,idArquivo);
			
				verificarLeiturista(arquivoTextoOSCobranca, form);

				response.addHeader("Content-Disposition", "attachment; filename=" + getNomeArquivo(arquivoTextoOSCobranca.getId()));
				response.addHeader("Content-Length", arquivo.length + "");
				response.setContentType("text/plain");
				OutputStream output = response.getOutputStream();

				output.write(arquivo);
				output.flush();
				output.close();
				
				atualizaSituacao(arquivoTextoOSCobranca);
				request.setAttribute("situacao", SituacaoTransmissaoLeitura.EM_CAMPO);
			} catch (IOException e) {
				reportarErros(request, "erro.sistema");
			}
		}
		return retorno;
	}
	

	
	private void verificarLeiturista(ArquivoTextoOSCobranca arquivoTextoOSCobranca, ConsultarArquivoTextoOSCobrancaSmartphoneActionForm form) {
		String idTipoOS = form.getIdTipoOS();
		
		//2. Caso o Tipo da Ordem de Serviço corresponda a "O.S. DE MICROMEDICAO"
		if(idTipoOS != null && (Integer.valueOf(idTipoOS)).equals(OrdemServico.ORDEM_SERVICO_MICROMEDICAO)){
			
			FiltroHidrometro filtroHidrometro = new FiltroHidrometro();
			filtroHidrometro.adicionarParametro(new ParametroSimples(
										FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_LEITURISTA_ID, 
										arquivoTextoOSCobranca.getLeiturista().getId()));
			filtroHidrometro.adicionarParametro(new ParametroSimples(
										FiltroHidrometro.HIDROMETRO_LOCAL_ARMAZENAGEM_IC_USO,
										ConstantesSistema.SIM));

			Collection<Hidrometro> colecaoHidrometros = Fachada.getInstancia().pesquisar(filtroHidrometro,
																			   Hidrometro.class.getName());

			// 1. Caso não exista registro na tabela MICROMEDICAO.HIDROMETRO
			if (colecaoHidrometros == null || colecaoHidrometros.isEmpty()) {

				// 1. exibir a mensagem
				// "Não existem Hidrômetros informados para o Agente Comercial selecionado."
				throw new ActionServletException("atencao.arquivo_txt_nao_ex_hidrometros_agente");
			} else {
				ParametroSistema parametro = getFachada().pesquisarParametroSistema(
											 ParametroSistema.NUMERO_LIMITE_OS_COBRANCA);
				
				String limite = parametro.getValorParametro();
				if (colecaoHidrometros.size() > Integer.valueOf(limite)) {
					throw new ActionServletException("atencao.arquivo_txt_existem_mais_hidr_limite", limite);

				}
			}
		}
	}
	
	private ArquivoTextoOSCobranca pesquisaArquivoTexto(String imei,Integer idArquivo){
		ArquivoTextoOSCobranca arquivoTextoOSCobranca = new ArquivoTextoOSCobranca();
		FiltroArquivoTextoOSCobranca filtro = new FiltroArquivoTextoOSCobranca();
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoOSCobranca.IMEI_LEITURISTA, imei));
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoOSCobranca.ID, idArquivo));
		filtro.adicionarCaminhoParaCarregamentoEntidade("leiturista");

		arquivoTextoOSCobranca = (ArquivoTextoOSCobranca) Util.retonarObjetoDeColecao(getFachada().
											pesquisar(filtro, ArquivoTextoOSCobranca.class.getName()));

		return arquivoTextoOSCobranca;
	}
	
	private void atualizaSituacao(ArquivoTextoOSCobranca arquivoTextoOSCobranca){
		// Atualiza o arquivo em campo
		SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.EM_CAMPO);
		arquivoTextoOSCobranca.setSituacao(situacao);
		arquivoTextoOSCobranca.setDataEnvio(new Date());
		getFachada().atualizar(arquivoTextoOSCobranca);
	}
	
	private String getNomeArquivo(Integer id){
		return  id + "-" + Util.formatarDataAAAAMMDD(new Date()) + ".txt";
	}
	
	private byte[] buscaArquivo(String imei,Integer idArquivo){
		byte[] arquivo = getFachada().baixarArquivoTextoExecucaoOrdemServico(Long.parseLong(imei), idArquivo);
		
		if (arquivo == null || arquivo.length <= 0){
			throw new ActionServletException("atencao.arquivo_ja_baixado");
		}
		
		return arquivo;
	}
	
}
