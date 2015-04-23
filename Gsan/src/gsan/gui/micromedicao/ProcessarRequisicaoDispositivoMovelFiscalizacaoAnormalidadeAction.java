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
* Davi Menezes de Oliveira
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
 * [UC1231] -  Acompanhamento de Fiscalização de Anormalidade
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
	
	//Tipos de Requisição
	private static final short BAIXAR_ARQUIVO = 1;
	private static final short ATUALIZAR_MOVIMENTO = 2;
	private static final short FINALIZAR_ROTEIRO = 3;
	private static final short RECEBER_FOTO = 5;
	private static final short ATUALIZAR_SITUACAO = 10;
	
	
	/**
	 * Método Execute do Action
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
		
		//Verificamos qual foi a ação solicitada pelo dispositivo móvel
		try{
			in = request.getInputStream();
			out = response.getOutputStream();
			din = new DataInputStream(in);
			
			//O primeiro byte da requisição possue sempre o tipo da requisição
			// feita pelo dispositivo móvel
			int acaoSolicitada = 0;
			if(din != null){
				acaoSolicitada = din.readByte();
			}
				
			switch(acaoSolicitada){
				//No caso de download de arquivo, é sempre passado o imei do celular
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
			
		//Caso aconteça qualquer problema, retornamos para o
		//dispositivo movel o caracter de erro no processamento da requisição
		} catch(Exception e){
			response.setContentLength(1);
			try{
				out.write(RESPOSTA_ERRO);
				out.flush();
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. RETORNOU " + RESPOSTA_ERRO + " PARA O CELULAR /n/n/n");
				e.printStackTrace();
			} catch(IOException ioe){
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. NÃO ENVIOU RESPOSTA PARA O CELULAR /n/n/n");
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
			System.out.println("ERROR: Ao atualizar o arquivo de fiscalização de anormalidade");
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
	 * [UC1220] - Gerar Arquivo de Fiscalização de Anormalidade
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
				
				System.out.println("INICIO: Baixando arquivo texto fiscalizacação ID: "+arquivoTextoVisitaCampo.getId());
				
				//Parametro que identifica que o tipo de arquivo da rota está sendo enviado
				String parametroArquivoBaixarRota = "";
				
				//1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo rota
				response.setContentLength(1 + parametroArquivoBaixarRota.getBytes().length + arq.length);
				
				out.write(RESPOSTA_OK);
				out.write(parametroArquivoBaixarRota.getBytes());
				out.write(arq);
				
				out.flush();
				
				System.out.println("FIM: Baixando arquivo texto fiscalizacação ID: "+arquivoTextoVisitaCampo.getId());
			}
			
			else{
				System.out.println("ERROR: Não existe arquivo de Fiscalização de anormalidade.");
				response.setContentLength(1);
				
				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao baixar arquivo mobile de fiscalização de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	private void receberFoto(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
		try {
			System.out.println("INICIO: Recebendo foto fiscalizacao anormalidade ");
			//Lemos o número da OS para qual essa foto pertence
			int numeroOS = din.readInt();
			
			//Verificamos qual é o tipo de foto
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
			System.out.println("ERROR: Ao receber a foto de fiscalização de anormalidade");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	

}
