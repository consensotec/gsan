package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.SituacaoTransmissaoAtualizacaoCadastralDM;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC 1392] Consultar Roteiro Dispositivo Móvel Atualização Cadastral
 * 
 * @author André Miranda
 * @since 20/10/2014
 */
public class TransmitirAtualizacaoCadastralArquivoTextoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		String idArquivo = httpServletRequest.getParameter("idArquivo");
		String idSituacaoArquivo = httpServletRequest.getParameter("idSituacaoArquivo");

		if(idArquivo == null || idArquivo.isEmpty()) {
			return retorno;
		}

		FiltroArquivoTextoAtualizacaoCadastralDM filtro = new FiltroArquivoTextoAtualizacaoCadastralDM();
		filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastralDM.ID, idArquivo));
		filtro.adicionarCaminhoParaCarregamentoEntidade("parametroTabelaAtualizacaoCadastralDM");
		filtro.adicionarCaminhoParaCarregamentoEntidade("parametroTabelaAtualizacaoCadastralDM.localidade");

		Collection<?> colArquivo = getFachada().pesquisar(filtro, ArquivoTextoAtualizacaoCadastralDM.class.getName());
		if(Util.isVazioOrNulo(colArquivo)){
			throw new ActionServletException("atencao.arquivo_ja_baixado");
		}

		ArquivoTextoAtualizacaoCadastralDM arquivoTexto = (ArquivoTextoAtualizacaoCadastralDM) Util.retonarObjetoDeColecao(colArquivo);

		if(arquivoTexto.getArquivoTexto() == null || arquivoTexto.getArquivoTexto().length <= 0) {
			throw new ActionServletException("atencao.arquivo_ja_baixado");
		}

		try {
			if (idSituacaoArquivo != null && !idSituacaoArquivo.isEmpty()) {
				Integer idSituacao = Integer.parseInt(idSituacaoArquivo);

				if (!idSituacao.equals(SituacaoTransmissaoAtualizacaoCadastralDM.LIBERADO)) {
					throw new ActionServletException("atencao.arquivo_ja_baixado");
				}

				byte[] arquivo = getFachada().montarArquivoZipComMapasQuadras(arquivoTexto);
				
				httpServletResponse.setContentType("application/octet-stream");
				httpServletResponse.setContentLength(arquivo.length);
				httpServletResponse.setHeader("Content-Disposition","attachment;filename="+arquivoTexto.getDescricaoArquivo().replace("txt", "zip"));
			     
				ServletOutputStream out = httpServletResponse.getOutputStream();
				out.write(arquivo);
				out.flush();
				out.close();

				//Atualizar a situacao do arquivo para EM_CAMPO
				arquivoTexto.setSituacaoTransmissao(
						new SituacaoTransmissaoAtualizacaoCadastralDM(SituacaoTransmissaoAtualizacaoCadastralDM.EM_CAMPO));								
				getFachada().atualizar(arquivoTexto);
			}
		} catch (IOException e) {
			reportarErros(httpServletRequest, "erro.sistema");
		}

		return retorno;
	}
}
