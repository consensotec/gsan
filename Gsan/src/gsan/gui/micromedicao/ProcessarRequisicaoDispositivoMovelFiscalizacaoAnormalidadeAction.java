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
* Davi Menezes de Oliveira
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

package gsan.gui.micromedicao;

import gsan.atendimentopublico.ordemservico.ArquivoTextoVisitaCampo;
import gsan.atendimentopublico.ordemservico.FiltroArquivoTextoVisitaCampo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1231] -  Acompanhamento de Fiscaliza��o de Anormalidade
 *
 * @author Davi Menezes
 * @date 05/09/2011
 *
 */

public class ProcessarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction extends GcomAction {
	
	//Constantes de Classe
	//Tipos de Resposta
	private static final char RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';
	
	//Tipos de Requisi��o
	private static final short BAIXAR_ARQUIVO = 1;
	private static final short ATUALIZAR_MOVIMENTO = 2;
	private static final short FINALIZAR_ROTEIRO = 3;
	private static final short RECEBER_FOTO = 5;
	private static final short ATUALIZAR_SITUACAO = 10;
	
	
	/**
	 * M�todo Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException 
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		
		InputStream in = null;
		OutputStream out = null;
		DataInputStream din = null;
		
		//Verificamos qual foi a a��o solicitada pelo dispositivo m�vel
		try{
			in = request.getInputStream();
			out = response.getOutputStream();
			din = new DataInputStream(in);
			
			//O primeiro byte da requisi��o possue sempre o tipo da requisi��o
			// feita pelo dispositivo m�vel
			int acaoSolicitada = 0;
			if(din != null){
				acaoSolicitada = din.readByte();
			}
				
			switch(acaoSolicitada){
				//No caso de download de arquivo, � sempre passado o imei do celular
				case BAIXAR_ARQUIVO:
					//Baixar Arquivo
					long imei = din.readLong();
					this.baixarArquivo(imei, response, out);
					break;
				case ATUALIZAR_MOVIMENTO:
					//Atualizar Movimento
					this.atualizarMovimentacao(din,response,out);
					break;
				case FINALIZAR_ROTEIRO:
					//Finalizar Leitura
					this.finalizarRoteiro(din,response,out);
					break;
				case RECEBER_FOTO:
					//Receber Foto
					this.receberFoto(din, response, out);
					break;
				case ATUALIZAR_SITUACAO:
					//Atualizar Situacao do Arquivo
					long idArquivoTextoVisitaCampo = din.readLong();
					this.atualizarSituacaoArquivo(response, out,idArquivoTextoVisitaCampo);
					break;
					
				default:
					break;
			}
			
		//Caso aconte�a qualquer problema, retornamos para o
		//dispositivo movel o caracter de erro no processamento da requisi��o
		} catch(Exception e){
			response.setContentLength(1);
			try{
				out.write(RESPOSTA_ERRO);
				out.flush();
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. RETORNOU " + RESPOSTA_ERRO + " PARA O CELULAR /n/n/n");
				e.printStackTrace();
			} catch(IOException ioe){
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. N�O ENVIOU RESPOSTA PARA O CELULAR /n/n/n");
				ioe.printStackTrace();
				throw new ActionServletException(ioe.getMessage());
			}
		} finally{
			if(in != null){
				try{
					in.close();
				} catch (IOException e){
					e.printStackTrace();
					throw new ActionServletException(e.getMessage());
				}
			}
			if(out != null){
				try{
					out.close();
				} catch(IOException e){
					e.printStackTrace();
					throw new ActionServletException(e.getMessage());
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * [UCXXX] - 
	 * 
	 * SF/FS - 
	 *
	 * @author Davi Menezes
	 * @date 15/09/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	
	private void finalizarRoteiro(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(din);
		BufferedReader buffer = new BufferedReader(reader);
		
		try{
			System.out.println("INICIO: Finalizando roteiro fiscalizacao ");
			getFachada().finalizarLeituraFiscalizacaoAnormalidade(buffer);
			
			out.write(RESPOSTA_OK);
			out.flush();
			System.out.println("FIM: Finalizando roteiro fiscalizacao ");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao finalizar roteiro fiscalizacao");
			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * [UCXXX] - 
	 * 
	 * SF/FS - 
	 *
	 * @author Davi Menezes
	 * @date 15/09/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	private void atualizarMovimentacao(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		InputStreamReader reader = new InputStreamReader(din, "UTF-8");
		BufferedReader buffer = new BufferedReader(reader);
		
		try{
			System.out.println("INICIO: Atualizando movimentacao da fiscalizacao ");
			
			getFachada().atualizarMovimentacaoFiscalizacaoAnormalidade(buffer);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Atualizando movimentacao da fiscalizacao ");
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao atualizar o arquivo de fiscaliza��o de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * Atualiza o arquivo texto para situacao "EM CAMPO" 
	 *
	 * @author Rafael Pinto
	 * @date 27/10/2011
	 *
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void atualizarSituacaoArquivo(HttpServletResponse response, 
		OutputStream out, long idArquivoTextoVisitaCampo) throws IOException {
		try {
			//Atualiza o arquivo em campo
			System.out.println("INICIO: Atualizando a situacao do arquivo ID: "+idArquivoTextoVisitaCampo);
			
			FiltroArquivoTextoVisitaCampo filtro = new FiltroArquivoTextoVisitaCampo();
			filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoVisitaCampo.ID, idArquivoTextoVisitaCampo));
			
			Collection<ArquivoTextoVisitaCampo> colArquivoTexto = 
					this.getFachada().pesquisar(filtro, ArquivoTextoVisitaCampo.class.getName());
			
			ArquivoTextoVisitaCampo arquivoTextoVisitaCampo = (ArquivoTextoVisitaCampo) Util.retonarObjetoDeColecao(colArquivoTexto);
			
			if(arquivoTextoVisitaCampo != null){
				SituacaoTransmissaoLeitura situacao = new SituacaoTransmissaoLeitura(SituacaoTransmissaoLeitura.EM_CAMPO);
				arquivoTextoVisitaCampo.setSituacaoTransmissaoLeitura(situacao);
				arquivoTextoVisitaCampo.setDataEnvioArquivo(new Date());
				
				this.getFachada().atualizar(arquivoTextoVisitaCampo);
				
			}
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Atualizando a situacao do arquivo ID: "+idArquivoTextoVisitaCampo);
			
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR: Ao atualizar situacao do arquivo em campo ID:"+idArquivoTextoVisitaCampo);
			response.setContentLength(1);
			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}



	}
	
	/**
	 * [UC1220] - Gerar Arquivo de Fiscaliza��o de Anormalidade
	 *
	 * @author Davi Menezes
	 * @date 05/09/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	private void baixarArquivo(long imei, HttpServletResponse response, OutputStream out) throws IOException  {
		try{
			//Arquivo retornado
			Object[] retorno = getFachada().baixarArquivoTextoFiscalizacaoAnormalidade(imei); 
			
			if(retorno != null && retorno.length > 0){
				
				ArquivoTextoVisitaCampo arquivoTextoVisitaCampo = (ArquivoTextoVisitaCampo) retorno[0];				
				byte[] arq = (byte[]) retorno[1];
				
				System.out.println("INICIO: Baixando arquivo texto fiscalizaca��o ID: "+arquivoTextoVisitaCampo.getId());
				
				//Parametro que identifica que o tipo de arquivo da rota est� sendo enviado
				String parametroArquivoBaixarRota = "";
				
				//1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo rota
				response.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arq.length);
				
				out.write(RESPOSTA_OK);
				out.write(parametroArquivoBaixarRota.getBytes());
				out.write(arq);
				
				out.flush();
				
				System.out.println("FIM: Baixando arquivo texto fiscalizaca��o ID: "+arquivoTextoVisitaCampo.getId());
			}
			
			else{
				System.out.println("ERROR: N�o existe arquivo de Fiscaliza��o de anormalidade.");
				response.setContentLength(1);
				
				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao baixar arquivo mobile de fiscaliza��o de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	private void receberFoto(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			System.out.println("INICIO: Recebendo foto fiscalizacao anormalidade ");
			//Lemos o n�mero da OS para qual essa foto pertence
			int numeroOS = din.readInt();
			
			//Verificamos qual � o tipo de foto
			int tipoFoto = din.readInt();
			
			long fileSize = din.readLong();
			
			byte[] bytesFoto = new byte[(int)fileSize];
			int read = 0;
			int numRead = 0;
			while(read < bytesFoto.length && (numRead=din.read(bytesFoto, read, bytesFoto.length-read))>= 0){
				read = read + numRead;
			}
			
			//inserimos na base
			getFachada().inserirFotoOrdemServico(numeroOS, tipoFoto, bytesFoto);
			
			response.setContentLength(1+read+bytesFoto.length);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Recebendo foto fiscalizacao anormalidade ");
			
		} catch (Exception e) {
			System.out.println("ERROR: Ao receber a foto de fiscaliza��o de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	

}
