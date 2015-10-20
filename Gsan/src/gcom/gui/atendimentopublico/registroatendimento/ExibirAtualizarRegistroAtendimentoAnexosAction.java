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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.ParametroSimples;

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
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da atualiza��o de um R.A (Aba n� 04 - Anexos) 
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
			
			//VALIDA��O DO ARQUIVO
			fachada.validarRegistroAtendimentoAnexos(arquivoInformado);
			
			RegistroAtendimentoAnexo registroAtendimentoanexo = this.gerarRegistroAtendimentoAnexo(
			arquivoInformado, observacaoAnexo, fachada);
			
			//INSERINDO O ARQUIVO NA COLE��O DE VISUALIZA��O
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
		
		//OBTENDO ARQUIVO PARA VISUALIZA��O
		RegistroAtendimentoAnexo registroAtendimentoAnexo = this.obterArquivoParaVisualizacao(visualizar, sessao);
		
		//PREPARANDO VISUALIZA��O DO ARQUIVO
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
				    bos.write(registroAtendimentoAnexo.getImagemDocumento()); //Gravamos os bytes l�
				    
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
		
		//CARREGANDO OS ANEXOS QUE EST�O CADASTRADOS NA BASE
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
	 * Removendo um arquivo da cole��o
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
	 * Gerando o objeto que referencia o arquivo que ser� anexado ao RA
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
		
		//EXTENS�O
		anexo.setNomeExtensaoDocumento(Util.obterExtensaoDoArquivo(arquivoAnexo));
		
		//ARQUIVO EM BYTES
		if (!anexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_JPG)){
			
			try {
				
				if (!anexo.getNomeExtensaoDocumento().equals(ConstantesSistema.EXTENSAO_XLS)){
					
					//COMPACTAR
					File arquivoOriginal = new File("original." + Util.obterExtensaoDoArquivo(arquivoAnexo)); //Criamos um nome para o arquivo  
				    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoOriginal)); //Criamos o arquivo  
				    bos.write(arquivoAnexo.get()); //Gravamos os bytes l�
					
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
			    bos.write(arquivoAnexo.get()); //Gravamos os bytes l�
				
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
		
		//OBSERVA��O
		if (observacaoAnexo != null && !observacaoAnexo.equals("")){
			
			anexo.setDescricaoDocumento(observacaoAnexo.trim());
		}
		
		//�LTIMA ALTERA��O
		anexo.setUltimaAlteracao(new Date());
		
		return anexo;
		
	}
	
	/**
	 * Removendo um arquivo da cole��o
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
