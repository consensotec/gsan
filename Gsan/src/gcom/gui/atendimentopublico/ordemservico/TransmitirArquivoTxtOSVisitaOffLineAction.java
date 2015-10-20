package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/10/2010
 */

public class TransmitirArquivoTxtOSVisitaOffLineAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		String imei = httpServletRequest.getParameter("imei");

		if (imei != null && !imei.equals("")) {

			Object[] dados = getFachada().baixarArquivoTextoFiscalizacaoAnormalidade(new Long(imei));
			File temporario;

			if (dados != null && dados.length > 0) {
				try {
					temporario = File.createTempFile("temporario", ".txt");

					FileOutputStream out = new FileOutputStream(temporario);

					ArquivoTextoVisitaCampo arquivoTextoVisitaCampo = (ArquivoTextoVisitaCampo) dados[0];

					if (arquivoTextoVisitaCampo.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO)) {

						byte[] arq = (byte[]) dados[1];

						// Parametro que identifica que o tipo de arquivo da
						// rota
						// está sendo enviado
						String parametroArquivoBaixarRota = "";

						// 1 do tipo de resposta ok + parametro Arquivo baixar
						// rota
						// + tamanho do arquivo rota
						httpServletResponse.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arq.length);

						out.write(parametroArquivoBaixarRota.getBytes());
						out.write(arq);

						out.flush();

						FileInputStream inputStream = new FileInputStream(temporario);

						int INPUT_BUFFER_SIZE = 1024;
						byte[] temp = new byte[INPUT_BUFFER_SIZE];
						int numBytesRead = 0;

						ByteArrayOutputStream arquivo = new ByteArrayOutputStream();

						while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
							arquivo.write(temp, 0, numBytesRead);
						}

						inputStream.close();
						inputStream = null;

						arquivo.close();

						httpServletResponse.setContentType("text/plain");
						httpServletResponse.addHeader("Content-Disposition", "attachment; filename="
								+ arquivoTextoVisitaCampo.getId() + "-" + Util.formatarDataAAAAMMDD(new Date()));

						ServletOutputStream servletOutputStream;

						servletOutputStream = httpServletResponse.getOutputStream();
						servletOutputStream.write(arquivo.toByteArray());
						servletOutputStream.flush();
						servletOutputStream.close();

						// Atualiza o arquivo em campo
						SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.EM_CAMPO);
						arquivoTextoVisitaCampo.setSituacaoTransmissaoLeitura(situacao);
						arquivoTextoVisitaCampo.setDataEnvioArquivo(new Date());

						this.getFachada().atualizar(arquivoTextoVisitaCampo);

					} else {
						throw new ActionServletException("atencao.arquivo_ja_baixado");
					}

				} catch (IOException e) {
					reportarErros(httpServletRequest, "erro.sistema");
				}

			} else {
				throw new ActionServletException("atencao.arquivo_ja_baixado");
			}
		}
		return retorno;
	}
}
