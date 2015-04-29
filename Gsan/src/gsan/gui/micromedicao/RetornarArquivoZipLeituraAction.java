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
 * R�mulo Aur�lio de Melo Souza Filho
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
package gsan.gui.micromedicao;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.ArquivoTextoRoteiroEmpresa;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.tarefa.TarefaException;
import gsan.util.IoUtil;
import gsan.util.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarArquivoZipLeituraAction extends GcomAction {

	/**
	 * Este caso de uso permite Retornar um Arquivo Txt Leitura
	 * 
	 * [UC0629] Retornar Arquivo Txt Leitura
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 13/10/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		ConsultarArquivoTextoLeituraActionForm form = (ConsultarArquivoTextoLeituraActionForm) actionForm;

		String[] idsRegistrosRemocao = form.getIdsRegistros();

		Fachada fachada = Fachada.getInstancia();
	

		Collection colecaoArquivoTextoRoteiroEmpresa = fachada
				.pesquisarArquivosTextoRoteiroEmpresaParaArquivoZip(idsRegistrosRemocao);

		String nomeArquivo = "Rol_Grupo_";
		String nomeArquvioZip = nomeArquivo + form.getGrupoFaturamentoID()
				+ ".zip";

		ZipOutputStream zos = null;

		File compactadoTipo = new File(nomeArquvioZip);

		if (colecaoArquivoTextoRoteiroEmpresa != null
				&& !colecaoArquivoTextoRoteiroEmpresa.isEmpty()) {

			try {
				
				zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Iterator colecaoArquivoTextoRoteiroEmpresaIterator = (Iterator) colecaoArquivoTextoRoteiroEmpresa
					.iterator();
			try {

				while (colecaoArquivoTextoRoteiroEmpresaIterator.hasNext()) {

					ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa = (ArquivoTextoRoteiroEmpresa) colecaoArquivoTextoRoteiroEmpresaIterator
							.next();

					File arquivoTxts = new File(arquivoTextoRoteiroEmpresa
							.getNomeArquivo());

					FileOutputStream out = new FileOutputStream(arquivoTxts
							.getAbsolutePath());

					out
							.write(((StringBuilder) IoUtil
									.transformarBytesParaObjeto(arquivoTextoRoteiroEmpresa
											.getArquivoTexto()))
											.toString().getBytes());

					out.close();

					ZipUtil.adicionarArquivo(zos, arquivoTxts);

					arquivoTxts.delete();
					
					zos.closeEntry();
					
					// Atualizar Situacao do Arquivo
					fachada.atualizarArquivoTextoEnviado(arquivoTextoRoteiroEmpresa.getId(),SituacaoTransmissaoLeitura.EM_CAMPO);

				}

				zos.flush();
				
				zos.close();
				

				httpServletResponse.setContentType("application/zip");
				httpServletResponse.addHeader("Content-Disposition",
						"attachment; filename=" + nomeArquvioZip);
				
				File arquivozip = new File(nomeArquvioZip);
				
				ServletOutputStream sos = httpServletResponse.getOutputStream();

				sos.write(IoUtil.getBytesFromFile(arquivozip));  
								  
				sos.flush();
				 
				sos.close();
				
												
			} catch (Exception e) {
				e.printStackTrace();
				//throw new TarefaException(
				//	"Erro ao gravar relat�rio no diretorio", e);
				throw new ActionServletException("atencao.erro_relatorio_diretorio", e);
			}
		}

		try {

			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao fechar o zip do relatorio", e);
		}

		return retorno;

	}

}
