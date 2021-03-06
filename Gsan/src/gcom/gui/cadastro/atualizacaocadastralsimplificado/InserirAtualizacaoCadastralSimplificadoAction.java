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

import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.IoUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * L� o arquivo a partir dos par�metros da requisi��o e insere no BD.
 * [UC0969] Importar arquivo de atualiza��o cadastral simplificado
 * 
 * 
 * @author Samuel Valerio
 * 
 * @date 21/10/2009
 * 
 * 
 */
public class InserirAtualizacaoCadastralSimplificadoAction extends GcomAction {
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		DiskFileUpload upload = new DiskFileUpload();
		
		Integer idArquivo = null;
		String nomeArquivo = null;
		
		try {
			List items = upload.parseRequest(httpServletRequest);
			
			if (items != null) {
				FileItem item = null;
				String comentario = null;
				
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					item = (FileItem) iter.next();
					
					if (item.getFieldName().equals("comentario")) {
						comentario = item.getString().toUpperCase();
					}
					
					// Caso n�o seja um field do formulario
	                // � o arquivo
	                if ( !item.isFormField() ){
	                	InputStreamReader reader;
						try {
							reader = new InputStreamReader(item.getInputStream());
							BufferedReader buffer = new BufferedReader(reader);
							
							Collection<AtualizacaoCadastralSimplificadoLinha> linhas = new HashSet<AtualizacaoCadastralSimplificadoLinha>();
							
							final AtualizacaoCadastralSimplificado arquivo = new AtualizacaoCadastralSimplificado();
							
							String linha;
							int numeroLinha = 0;
							StringBuffer conteudoDoArquivo = new StringBuffer();
							while ((linha = buffer.readLine()) != null) {
								final AtualizacaoCadastralSimplificadoLinha linhaObj = new AtualizacaoCadastralSimplificadoLinha(linha.split("#"), ++numeroLinha);
								linhaObj.setArquivo(arquivo);
								linhas.add(linhaObj);
								conteudoDoArquivo.append(linha);
								conteudoDoArquivo.append(System.getProperty("line.separator"));
							}
							
							arquivo.setComentario(comentario);
							arquivo.setUsuario((Usuario) httpServletRequest.getSession(false).getAttribute("usuarioLogado"));
							AtualizacaoCadastralSimplificadoBinario arquivoBinario = new AtualizacaoCadastralSimplificadoBinario();
							arquivoBinario.setBinario(IoUtil.transformarObjetoParaBytes(conteudoDoArquivo));
							arquivo.setNome(item.getName());
							idArquivo = Fachada.getInstancia().inserirArquivoAtualizacaoCadastralSimplificado(arquivo, arquivoBinario, linhas);
							
							// colocando o nome do arquivo em uma vari�vel para exib�-lo na p�gina de sucesso
							nomeArquivo = arquivo.getNome();
							
						} catch (NumberFormatException erroLayoutException) {
							throw erroLayoutException;	
						} catch (IOException e) {
							throw new ActionServletException("erro.inserir_arquivo_atualizacao_cadastral_simplificado.envio");
						}
	                    
	                }
					
				}
			}
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.inserir_arquivo_atualizacao_cadastral_simplificado.envio");
		}
		
		montarPaginaSucesso(httpServletRequest, "O arquivo " + nomeArquivo

				+ " foi importado com sucesso.", "Visualizar detalhes da importa��o",

				"exibirDetalhesAtualizacaoCadastralSimplificadoAction.do?id="+idArquivo);

		return retorno;
	}
}
