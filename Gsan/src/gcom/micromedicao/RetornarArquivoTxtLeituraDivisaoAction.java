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
package gcom.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.IoUtil;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
  Descri��o da classe
 * 
 * @author Hugo Leonardo
 * @date 05/06/2010
 */
public class RetornarArquivoTxtLeituraDivisaoAction extends GcomAction {

 /**
  * Este caso de uso permite Retornar um Arquivo Txt Leitura Divis�o
  * 
  * UC1027 - Exibir Consultar Arquivo Texto Leitura Divis�o.
  *  
  * @author Hugo Leonardo
  * @created 04/06/2010
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
		
		String idArquivoTxtDivisao = httpServletRequest
		    .getParameter("idRegistroAtualizacao");
		
		FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = 
			new FiltroArquivoTextoRoteiroEmpresaDivisao();
		filtroArquivoTextoRoteiroEmpresaDivisao.adicionarParametro(new ParametroSimples( 
				FiltroArquivoTextoRoteiroEmpresaDivisao.ID, idArquivoTxtDivisao));
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoArquivoTxtLeituraDivisao = fachada.pesquisar(filtroArquivoTextoRoteiroEmpresaDivisao,
			ArquivoTextoRoteiroEmpresaDivisao.class.getName());
		
		ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = (ArquivoTextoRoteiroEmpresaDivisao) 
			colecaoArquivoTxtLeituraDivisao.iterator().next();
		  
		if(arquivoTextoRoteiroEmpresaDivisao.getSituacaoTransmissaoLeitura().getId()
				.equals(SituacaoTransmissaoLeitura.LIBERADO)){
	   
			try {
				   
				boolean ehgz = arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo().toUpperCase().endsWith(".GZ");
			
				if(!ehgz){
						
					File temporario = File.createTempFile("temporario", ".txt"); 
				    FileOutputStream outputStream = new FileOutputStream(temporario);
					    
				    try {
						outputStream.write((
						  (StringBuilder) IoUtil.transformarBytesParaObjeto(
						    arquivoTextoRoteiroEmpresaDivisao.getArquivoTexto())).toString().getBytes());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
				    outputStream.close();
				 
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
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename="+arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo());
					ServletOutputStream out;
		
					out = httpServletResponse.getOutputStream();
					out.write(arquivo.toByteArray());
					out.flush();
					out.close();
					
				}else{   
					    
					httpServletResponse.setContentType("application/zip");
				    httpServletResponse.addHeader("Content-Disposition",
				      "attachment; filename="+ arquivoTextoRoteiroEmpresaDivisao.getNomeArquivo() );
				    ServletOutputStream out;
				 
				    out = httpServletResponse.getOutputStream();
				    out.write( arquivoTextoRoteiroEmpresaDivisao.getArquivoTexto() );
				    out.flush();
				    out.close();
				}
			    
			    //Atualizar Situacao do Arquivo Divis�o
				fachada.atualizarArquivoTextoDivisaoEnviado(arquivoTextoRoteiroEmpresaDivisao.getId(),SituacaoTransmissaoLeitura.EM_CAMPO);
			   
			} catch (IOException e) {
				reportarErros(httpServletRequest, "erro.sistema");
			} 
		}else{
			throw new ActionServletException("atencao.arquivo_divisao_nao_pode_baixar");
		}
		
		return retorno;
	}

}