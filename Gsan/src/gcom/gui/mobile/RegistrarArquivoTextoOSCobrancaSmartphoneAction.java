/*
 * Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 *
 * This file is part of GSAN, an integrated service management system for Sanitation
 *
 * GSAN is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * GSAN is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
 * Copyright (C) <2007> 
 * Adriano Britto Siqueira
 * Alexandre Santos Cabral
 * Ana Carolina Alves Breda
 * Ana Maria Andrade Cavalcante
 * Aryed Lins de Ara�jo
 * Bruno Leonardo Rodrigues Barros
 * Carlos Elmano Rodrigues Ferreira
 * Cl�udio de Andrade Lira
 * Denys Guimar�es Guenes Tavares
 * Eduardo Breckenfeld da Rosa Borges
 * Fab�ola Gomes de Ara�jo
 * Fl�vio Leonardo Cavalcanti Cordeiro
 * Francisco do Nascimento J�nior
 * Homero Sampaio Cavalcanti
 * Ivan S�rgio da Silva J�nior
 * Jos� Edmar de Siqueira
 * Jos� Thiago Ten�rio Lopes
 * K�ssia Regina Silvestre de Albuquerque
 * Leonardo Luiz Vieira da Silva
 * M�rcio Roberto Batista da Silva
 * Maria de F�tima Sampaio Leite
 * Micaela Maria Coelho de Ara�jo
 * Nelson Mendon�a de Carvalho
 * Newton Morais e Silva
 * Pedro Alexandre Santos da Silva Filho
 * Rafael Corr�a Lima e Silva
 * Rafael Francisco Pinto
 * Rafael Koury Monteiro
 * Rafael Palermo de Ara�jo
 * Raphael Veras Rossiter
 * Roberto Sobreira Barbalho
 * Rodrigo Avellar Silveira
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 *
 * Este programa � software livre; voc� pode redistribu�-lo e/ou
 * modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
 * publicada pela Free Software Foundation; vers�o 2 da
 * Licen�a.
 * Este programa � distribu�do na expectativa de ser �til, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 * COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 * PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 * detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
 * junto com este programa; se n�o, escreva para Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package gcom.gui.mobile;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * Action exibe a tela que ir� processar o arquivo de retorno vindo de campo
 * 
 * @author Bruno Barros
 * @created 20/09/2011
 */
public class RegistrarArquivoTextoOSCobrancaSmartphoneAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		RegistrarArquivoTextoOSCobrancaSmartphoneActionForm form =
				(RegistrarArquivoTextoOSCobrancaSmartphoneActionForm) actionForm;
		FormFile formFile = form.getFormFile();

		if (!formFile.getFileName().toLowerCase().endsWith(".zip")) {
			throw new ActionServletException("atencao.arquivo_zip_invalido");
		}

		File pasta = new File(ConstantesSistema.DIRETORIO_GSANEOS + "-" + new Date().getTime());

		try {
			File zipFile = salvarArquivo(pasta, formFile);
			descompactarArquivo(pasta, zipFile);
			BufferedReader buffer = getArquivoTexto(zipFile);

			// processa o arquivo texto
			Fachada.getInstancia().atualizarMovimentacaoExecucaoOS(buffer, pasta);

			montarPaginaSucesso(request, "Arquivo registrado com sucesso!", "Voltar",
					"exibirRegistrarArquivoTextoOSCobrancaSmartphoneAction.do?menu=sim");
		} catch (IOException e) {
			throw new ActionServletException("atencao.erro_descompactar_arquivo");
		} catch (Exception e) {
			throw new ActionServletException("atencao.erro_processar_arquivo_texto", e);
		} finally {
			FileUtils.deleteQuietly(pasta);
		}

		return retorno;
	}

	private File salvarArquivo(File pasta, FormFile formFile) throws IOException {
		pasta.mkdirs();

		File file = new File(pasta, formFile.getFileName());
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		InputStream inputStream = formFile.getInputStream();

		byte[] buffer = new byte[inputStream.available()];
		int len;

		while ((len = inputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, len);
		}

		fileOutputStream.flush();
		fileOutputStream.close();
		
		return file;
	}

	private void descompactarArquivo(File pasta, File zipFile) throws IOException {
		byte[] buffer = new byte[1024];

		ZipInputStream zinstream = new ZipInputStream(new FileInputStream(zipFile));
		ZipEntry entry = zinstream.getNextEntry();
		while (entry != null) {
			FileOutputStream outstream = new FileOutputStream(new File(pasta, entry.getName()));
			int n;
			while ((n = zinstream.read(buffer)) > -1) {
				outstream.write(buffer, 0, n);
			}
			outstream.close();
			zinstream.closeEntry();
			entry = zinstream.getNextEntry();
		}

		zinstream.close();
	}

	private BufferedReader getArquivoTexto(File zipFile) throws FileNotFoundException {
		String path = zipFile.getAbsolutePath();
		path = FilenameUtils.removeExtension(path);
		path = path.concat(".txt");

		InputStreamReader reader = new InputStreamReader(new FileInputStream(path));
		BufferedReader buffer = new BufferedReader(reader);
		
		return buffer;
	}
}
