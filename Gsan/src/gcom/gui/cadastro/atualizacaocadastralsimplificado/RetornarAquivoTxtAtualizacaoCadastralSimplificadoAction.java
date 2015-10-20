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
package gcom.gui.cadastro.atualizacaocadastralsimplificado;

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Retornar o arquivo texto a partir do BD.
 * [UC0969] Importar arquivo de atualiza��o cadastral simplificado
 * 
 * 
 * @author Samuel Valerio
 * 
 * @date 21/10/2009
 * 
 * 
 */
public class RetornarAquivoTxtAtualizacaoCadastralSimplificadoAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = null;

		String idArquivoTxt = httpServletRequest.getParameter("id");

		if (idArquivoTxt == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_informado");

		try {
			Integer.parseInt(idArquivoTxt);
		} catch (NumberFormatException nfe) {
			throw new ActionServletException(
					"atencao.arquivo_texto_id_deve_ser_numerico", null, idArquivoTxt);
		}

		Fachada fachada = Fachada.getInstancia();
		
		FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario filtro = new FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario();
		filtro.adicionarParametro(new ParametroSimples(FiltroAtualizacaoCadastralSimplificadoSimplificadoBinario.ARQUIVO_ID, idArquivoTxt));
		filtro.adicionarCaminhoParaCarregamentoEntidade("arquivo");
		
		AtualizacaoCadastralSimplificadoBinario binario = (AtualizacaoCadastralSimplificadoBinario) Util
				.retonarObjetoDeColecao(fachada.pesquisar(filtro,
						AtualizacaoCadastralSimplificadoBinario.class.getName()));
		
		if (binario == null)
			throw new ActionServletException(
					"atencao.arquivo_texto_nao_encontrado_para_o_id_informado", null, idArquivoTxt);

		try {
			File temporario = File.createTempFile("temporario", ".txt");
	
			FileOutputStream outputStream = new FileOutputStream(temporario);
			outputStream.write(((StringBuffer) IoUtil
					.transformarBytesParaObjeto(binario.getBinario())).toString()
					.getBytes());
			outputStream.close();
	
			FileInputStream inputStream = new FileInputStream(temporario);
	
			int INPUT_BUFFER_SIZE = 1024;
			byte[] temp = new byte[INPUT_BUFFER_SIZE];
			int numBytesRead = 0;
	
			ByteArrayOutputStream arquivoStream = new ByteArrayOutputStream();
	
			while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
				arquivoStream.write(temp, 0, numBytesRead);
			}
	
			inputStream.close();
			inputStream = null;

			arquivoStream.close();
			httpServletResponse.setContentType("text/plain");
			httpServletResponse.addHeader("Content-Disposition",
					"attachment; filename=" + binario.getArquivo().getNome());
			ServletOutputStream out;

			out = httpServletResponse.getOutputStream();
			out.write(arquivoStream.toByteArray());
			out.flush();
			out.close();

			temporario.delete();
		} catch (IOException e) {
			reportarErros(httpServletRequest, "erro.sistema");

		} catch (ClassNotFoundException e) {
			reportarErros(httpServletRequest, "erro.sistema");
		}

		return retorno;
	}

}
