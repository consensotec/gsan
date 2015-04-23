/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.gui.StatusWizard;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.ZipUtil;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da atualização de um R.A (Aba nº 04 - Anexos) 
 *
 * @author Raphael Rossiter
 * @date 27/07/2009
 */
public class ExibirAtualizarRegistroAtendimentoAnexosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("atualizarRegistroAtendimentoAnexos");
        
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
        String adicionar = httpServletRequest.getParameter("adicionar");
		String remover = httpServletRequest.getParameter("remover");
		String visualizar = httpServletRequest.getParameter("visualizar");
		
		//ADICIONANDO UM ARQUIVO
		if (adicionar != null && !adicionar.equals("")){
			
			Object[] parametrosFormulario = null;
	        FileItem arquivoInformado = null;
	        String observacaoAnexo = null;
			
			//ARQUIVO
			try{
				
				parametrosFormulario = recebendoObjetos(httpServletRequest);
			
			}
			catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}
			
			arquivoInformado = (FileItem) parametrosFormulario[0];
			observacaoAnexo = (String) parametrosFormulario[1];
			
			//VALIDAÇÃO DO ARQUIVO
			fachada.validarRegistroAtendimentoAnexos(arquivoInformado);
			
			RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
			arquivoInformado, observacaoAnexo, fachada);
			
			//INSERINDO O ARQUIVO NA COLEÇÃO DE VISUALIZAÇÃO
			Collection colecaoRegistroAtendimentoAnexo = null;
			
			if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") != null){
				
				colecaoRegistroAtendimentoAnexo = (Collection) 
				sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
				
				colecaoRegistroAtendimentoAnexo.add(registroAtendimentoanexo);
				
			}
			else{
				
				colecaoRegistroAtendimentoAnexo = new ArrayList();
				colecaoRegistroAtendimentoAnexo.add(registroAtendimentoanexo);
				
				sessao.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);
			}
		}
		
		//REMOVENDO UM ARQUIVO
		this.removerArquivo(remover, sessao);
		
		//OBTENDO ARQUIVO PARA VISUALIZAÇÃO
		RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(visualizar, sessao);
		
		//PREPARANDO VISUALIZAÇÃO DO ARQUIVO
		if (registroAtendimentoAnexo != null){
			
			try {
				
				ServletOutputStream out = null;
				
				String mimeType = ConstantesSistema.CONTENT_TYPE_GENERICO;
				String nomeArquivo = "Anexo";
				
				byte[] arquivoOriginal = registroAtendimentoAnexo.getImagemDocumento();
				
				if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOC)){
					mimeType = ConstantesSistema.CONTENT_TYPE_MSWORD;
					nomeArquivo = nomeArquivo+".doc";
				}else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_DOCX)){
					mimeType = ConstantesSistema.CONTENT_TYPE_DOCX;
					nomeArquivo = nomeArquivo+".docx";
				}else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_PDF)){
					mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
					nomeArquivo = nomeArquivo+".pdf";
				}else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)){
					mimeType = ConstantesSistema.CONTENT_TYPE_JPEG;
					nomeArquivo = nomeArquivo+".jpg";
				}
				else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_XLS)){
					mimeType = ConstantesSistema.CONTENT_TYPE_XLS;
					nomeArquivo = nomeArquivo+".xls";
				}
				else if (registroAtendimentoAnexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_XLSX)){
					mimeType = ConstantesSistema.CONTENT_TYPE_XLSX;
					nomeArquivo = nomeArquivo+".xlsx";
				}
				
				if (registroAtendimentoAnexo.getIndicadorCompactado().equals(ConstantesSistema.SIM)){
					
					ByteArrayOutputStream baosArquivo = new ByteArrayOutputStream();
					
					File arquivoCompactado = new File("arquivoCompactado.zip"); //Criamos um nome para o arquivo  
				    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoCompactado)); //Criamos o arquivo  
				    bos.write(registroAtendimentoAnexo.getImagemDocumento()); //Gravamos os bytes lá
				    
					 // Cria o input do arquivo ZIP
			        ZipInputStream zinstream = new ZipInputStream(new FileInputStream(arquivoCompactado));
		 
		            // Pega a proxima entrada do arquivo
		            ZipEntry zentry = zinstream.getNextEntry();
		 
		            byte[] buffer = new byte[1024];
		            
		            // Enquanto existir entradas no ZIP
				    while (zentry != null) {
				        // Pega o nome da entrada
		                String entryName = zentry.getName();
		 
		                // Cria o output do arquivo , Sera extraido onde esta rodando a classe
		                FileOutputStream outstream = new FileOutputStream(entryName);
		                int n;
		 
		                // Escreve no arquivo
		                while ((n = zinstream.read(buffer)) > -1) {
		                    baosArquivo.write(buffer, 0, n);
		                }
		                
		                // Fecha arquivo
		                outstream.close();
		                File arquivoDescompactado = new File(entryName);
		                arquivoDescompactado.delete();
		 
		                // Fecha entrada e tenta pegar a proxima
				        zinstream.closeEntry();
				        zentry = zinstream.getNextEntry();
				    }
				    	 
			        // Fecha o zip como um todo
			        zinstream.close();
			        bos.close();
			        arquivoCompactado.delete();
			        
			        arquivoOriginal = null;
			        arquivoOriginal = baosArquivo.toByteArray();
			        baosArquivo.close();
				}
				
				httpServletResponse.setContentType(mimeType);
				httpServletResponse.setHeader("Content-disposition","attachment; filename=" +nomeArquivo );
				
				out = httpServletResponse.getOutputStream();
				
				out.write(arquivoOriginal);
				out.flush();
				out.close();
			} 
			catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
			}
		}
		
		//CARREGANDO OS ANEXOS QUE ESTÃO CADASTRADOS NA BASE
		if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") == null) {
			
			StatusWizard statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");
			
			FiltroRegistroAtendimentoAnexo filtroRegistroAtendimentoAnexo = new FiltroRegistroAtendimentoAnexo();
			
			filtroRegistroAtendimentoAnexo.adicionarParametro(new ParametroSimples(
			FiltroRegistroAtendimentoAnexo.REGISTRO_ATENDIMENTO_ID,
			statusWizard.getId()));

			Collection colecaoRegistroAtendimentoAnexo = fachada.pesquisar(filtroRegistroAtendimentoAnexo,
			RegistroAtendimentoAnexo.class.getName());
			
			sessao.setAttribute("colecaoRegistroAtendimentoAnexo", colecaoRegistroAtendimentoAnexo);
		}
		
		httpServletRequest.setAttribute("nomeCampo", "arquivoAnexo");
        
        return retorno;
	}
	
	/**
	 * Removendo um arquivo da coleção
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private void removerArquivo(String identificacao, HttpSession sessao){
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					colecaoRegistroAtendimentoAnexo.remove(anexoColecao);
					break;
				}
			}
		}
	}
	
	/**
	 * Realizando o upload do arquivo informado
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param HttpServletRequest
	 */
	private Object[] recebendoObjetos(HttpServletRequest httpServletRequest) throws FileUploadException {
		
		Object[] parametrosFormulario = new Object[2]; 
		
		DiskFileUpload upload = new DiskFileUpload();
		
		List itens = upload.parseRequest(httpServletRequest);
		FileItem fileItem = null;
		
		if (itens != null) {
			
			Iterator iter = itens.iterator();
			
			while (iter.hasNext()) {
				
				fileItem = (FileItem) iter.next();
				
				if (fileItem.getFieldName().equals("arquivoAnexo")) {
					
					parametrosFormulario[0] = fileItem;
				}
				
				if (fileItem.getFieldName().equals("observacaoAnexo")) {
					
					parametrosFormulario[1] = fileItem.getString();
				}
			}
		}
		
		return parametrosFormulario;
	}
	
	/**
	 * Gerando o objeto que referencia o arquivo que será anexado ao RA
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param FileItem
	 * @param String
	 */
	private RegistroAtendimentoAnexo gerarRegistroAtendimentoAnexo(FileItem arquivoAnexo, 
			String observacaoAnexo, Fachada fachada){
		
		RegistroAtendimentoAnexo anexo = new RegistroAtendimentoAnexo();
		
		//EXTENSÃO
		anexo.setNomeExtensaoDocumento(Util.obterExtensaoDoArquivo(arquivoAnexo));
		
		//ARQUIVO EM BYTES
		if (!anexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)){
			
			try {
				
				if (!anexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_XLS)){
					
					//COMPACTAR
					File arquivoOriginal = new File("original." + Util.obterExtensaoDoArquivo(arquivoAnexo)); //Criamos um nome para o arquivo  
				    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoOriginal)); //Criamos o arquivo  
				    bos.write(arquivoAnexo.get()); //Gravamos os bytes lá
					
			        File arquivoCompactado = new File("originalCompactado.zip");
		
			        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(arquivoCompactado));
		            ZipUtil.adicionarArquivo(zos, arquivoOriginal);
		
		            zos.close();
		
		            ByteArrayOutputStream baosArquivoZip = new ByteArrayOutputStream();
		            FileInputStream inputStream = new FileInputStream(arquivoCompactado);
		
		            // Escrevemos aos poucos
		            int INPUT_BUFFER_SIZE = 1024;
		            byte[] temp = new byte[INPUT_BUFFER_SIZE];
		            int numBytesRead = 0;
		
		            while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
		                baosArquivoZip.write(temp, 0, numBytesRead);
		            }
		
		            anexo.setImagemDocumento(baosArquivoZip.toByteArray());
		
		            // Fechamos o inputStream
		            inputStream.close();
		            baosArquivoZip.close();
		            bos.close();
		
		            inputStream = null;
		            arquivoCompactado.delete();
		            arquivoOriginal.delete();
		            
		            //INDICADOR COMPACTADO
		            anexo.setIndicadorCompactado(ConstantesSistema.SIM);
				}
				else{
					
					anexo.setImagemDocumento(arquivoAnexo.get());
					
					//INDICADOR COMPACTADO
		            anexo.setIndicadorCompactado(ConstantesSistema.NAO);
				}
	            
			} catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
	        }
        }
		else{
			
			try {
				
				SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
				
				//REDIMENSIONAR
				File arquivoOriginal = new File("original." + Util.obterExtensaoDoArquivo(arquivoAnexo)); //Criamos um nome para o arquivo  
			    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoOriginal)); //Criamos o arquivo  
			    bos.write(arquivoAnexo.get()); //Gravamos os bytes lá
				
			    File arquivoRedimensionado = arquivoOriginal; 
			    
			    if (sistemaParametro.getImagemResolucaoAltura() != null){
			    	
			    	arquivoRedimensionado = Util.resizeImg(arquivoOriginal, sistemaParametro.getImagemResolucaoAltura(), sistemaParametro.getImagemResolucaoLargura());
			    }
			    
			    ByteArrayOutputStream baosArquivoRedimensionado = new ByteArrayOutputStream();
	            FileInputStream inputStream = new FileInputStream(arquivoRedimensionado);
	
	            // Escrevemos aos poucos
	            int INPUT_BUFFER_SIZE = 1024;
	            byte[] temp = new byte[INPUT_BUFFER_SIZE];
	            int numBytesRead = 0;
	
	            while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
	            	baosArquivoRedimensionado.write(temp, 0, numBytesRead);
	            }
			    
			    anexo.setImagemDocumento(baosArquivoRedimensionado.toByteArray());
			    
			    inputStream.close();
			    baosArquivoRedimensionado.close();
			    bos.close();
			    
			    inputStream = null;
			    arquivoOriginal.delete();
			    arquivoRedimensionado.delete();
	            
			} catch (IOException e) {
				throw new ActionServletException("erro.sistema", e);
	        }
			
			//INDICADOR COMPACTADO
			anexo.setIndicadorCompactado(ConstantesSistema.NAO);
		}
		
		//OBSERVAÇÃO
		if (observacaoAnexo != null && !observacaoAnexo.equals("")){
			
			anexo.setDescricaoDocumento(observacaoAnexo.trim());
		}
		
		//ÚLTIMA ALTERAÇÃO
		anexo.setUltimaAlteracao(new Date());
		
		return anexo;
		
	}
	
	/**
	 * Removendo um arquivo da coleção
	 * 
	 * @author Raphael Rossiter
	 * @date 30/07/2009
	 * 
	 * @param String
	 * @param HttpSession
	 */
	private RegistroAtendimentoAnexo obterArquivoParaVisualizacao(String identificacao, HttpSession sessao){
		
		RegistroAtendimentoAnexo registroAtendimentoAnexo = null;
		
		if (identificacao != null && !identificacao.equals("")){
			
			Collection colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");
			
			Iterator it = colecaoRegistroAtendimentoAnexo.iterator();
			RegistroAtendimentoAnexo anexoColecao = null;
			
			while (it.hasNext()){
				
				anexoColecao = (RegistroAtendimentoAnexo) it.next();
				
				if (obterTimestampIdObjeto(anexoColecao) == Long.parseLong(identificacao)){
					registroAtendimentoAnexo = anexoColecao;
					break;
				}
			}
		}
		
		return registroAtendimentoAnexo;
	}
}
