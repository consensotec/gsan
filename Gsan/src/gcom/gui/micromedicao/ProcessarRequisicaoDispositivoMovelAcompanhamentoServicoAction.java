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

package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * 
 * [UCXXX] -  
 * 
 *
 * @author Bruno Barros
 * @date 15/06/2011
 *
 */
public class ProcessarRequisicaoDispositivoMovelAcompanhamentoServicoAction extends GcomAction {
	
	// Constantes da classe	
	// Tipos de Respota
	private static final byte RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';
	
	// Tipos de Requisi��o
	private static final short BAIXAR_ARQUIVO = 1;
	private static final short ATUALIZAR_ARQUIVO = 2;
	private static final short BAIXAR_MENSAGEM = 3;
	private static final short ENCERRAR_OS = 4;
	private static final short RECEBER_FOTO = 5;
	private static final short MUDAR_SITUACAO_ORDEM_SERVICO = 6;
	private static final short ENCERRAR_ARQUIVO_RETORNO = 7;
	private static final short RECEBER_COORDENADA = 9;
	private static final short PING = 8;
	private static final short SINAL_ROTA_INICIALIZADA = 10;
	//private static final short UPLOAD_RETORNO_ARQUIVO = 7;
	
	// Fachada
	Fachada fachada = Fachada.getInstancia();
	
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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response){

		InputStream in = null;
		OutputStream out = null;
		DataInputStream din = null;
		
		// Verificamos qual foi a a��o solicitada pelo dispositivo movel
		try {
			in = request.getInputStream();
			out = response.getOutputStream();
			din = new DataInputStream(in);
			
			// O primeiro byte da requisi��o possue sempre o tipo da requisi��o
			// feita pelo dispositivo m�vel
			int acaoSolicitada = din.readByte();
			long imei = din.readLong();
			switch ( acaoSolicitada ) {
			// Baixar o arquivo ?			
			case BAIXAR_ARQUIVO:
				// No caso de baixas de arquivo, � sempre passado
				// o imei do celular
				this.baixarArquivo(imei,response,out);
				break;
			case ATUALIZAR_ARQUIVO:
				// No caso de baixas de arquivo, � sempre passado
				// o imei do celular
				this.atualizarArquivo(imei,response,out);
				break;
			case BAIXAR_MENSAGEM:
				this.baixarMensagem(imei,response,out);
				break;				
			case RECEBER_FOTO:
				this.receberFoto(din, response, out);
				break;
			case MUDAR_SITUACAO_ORDEM_SERVICO:
				atualizarSituacaoProgramacaoOrdemServico(din, response, out);
				break;
			case ENCERRAR_OS:
				encerrarOS(din, response, out);
				break;
			case ENCERRAR_ARQUIVO_RETORNO:
				atualizarSituacaoArquivo(imei,SituacaoTransmissaoLeitura.EM_CAMPO.shortValue(),SituacaoTransmissaoLeitura.TRANSMITIDO.shortValue(),response,out);
				break;
			case PING:
				out.write( RESPOSTA_OK );
				out.flush();
				break;
			case SINAL_ROTA_INICIALIZADA:
				atualizarSituacaoArquivo(imei,SituacaoTransmissaoLeitura.LIBERADO.shortValue(),SituacaoTransmissaoLeitura.EM_CAMPO.shortValue(),response,out);			
				break;
			case RECEBER_COORDENADA:
				this.receberCoordenada(imei,din,response,out);
				break;
			default:
				break;
			}
			
		// Caso aconte�a qualquer problema, retornamos para o
		// dispositivo mobel o caracter de erro no processamento
		// da requisi��o
		} catch (Exception e) {						
			response.setContentLength( 1 );			
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
				
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. RETORNOU " + RESPOSTA_ERRO + " PARA O CELULAR /n/n/n");
				
				e.printStackTrace();
			} catch (IOException e1) {
				System.out.println("ERRO: NO PROCESSAMENTO DO GSAN. N�O ENVIOU RESPOSTA PARA O CELULAR /n/n/n");
				e1.printStackTrace();
				
				throw new ActionServletException( e1.getMessage() );
			}
		}finally{
			if (in != null) {
				try {
					in.close();					
				} catch (IOException e) {
					e.printStackTrace();					
					throw new ActionServletException( e.getMessage() );					
				}
			}
			
			if(out != null){
				try {					
					out.close();
				} catch (IOException e) {
					e.printStackTrace();					
					throw new ActionServletException( e.getMessage() );				
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
	 * @author Bruno Barros
	 * @date 15/06/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void baixarArquivo( long imei, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Arquivo retornado
			byte[] arq = fachada.baixarArquivoTextoAcompanhamentoServico( imei );
			
			// Nome do arquivo
			//String nomeArquivo = (String) retorno[1];
			
			if(arq != null && arq.length > 0){
				System.out.println("Inicio : Baixando arquivo Mobile");

				// Parametro que identifica que o tipo de arquivo da rota est� sendo enviado
				String parametroArquivoBaixarRota = "";				
				
				// 1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength( 1+parametroArquivoBaixarRota.getBytes().length+arq.length );

				out.write( RESPOSTA_OK );
				out.write( parametroArquivoBaixarRota.getBytes() );
				out.write( arq );
				
				out.flush();

				System.out.println("Fim: Baixando arquivo Mobile");	        
			}else{
				System.out.println("N�o existem arquivos liberados para o imei " + imei);

				response.setContentLength( 1 );

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch( Exception e ){
			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
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
	 * @author Bruno Barros
	 * @date 15/06/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void atualizarArquivo( long imei, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Arquivo retornado
			byte[] arq = fachada.atualizarArquivoTextoAcompanhamentoServico( imei );
			
			// Nome do arquivo
			//String nomeArquivo = (String) retorno[1];
			
			if(arq != null && arq.length > 0){
				System.out.println("Inicio : Atualizando Arquivo Mobile");

				// Parametro que identifica que o tipo de arquivo da rota est� sendo enviado
				String parametroArquivoBaixarRota = "";				
				
				// 1 do tipo de resposta ok + parametro Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength( 1+parametroArquivoBaixarRota.getBytes().length+arq.length );

				out.write( RESPOSTA_OK );
				out.write( parametroArquivoBaixarRota.getBytes() );
				out.write( arq );
				
				out.flush();

				System.out.println("Fim: Atualizando Arquivo Mobile");	        
			}else{
				System.out.println("N�o existe atualiza��o");
				response.setContentLength( 1 );

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
			
		} catch( Exception e ){
			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
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
	 * @author Th�lio Ara�jo
	 * @date 08/0/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void baixarMensagem( long imei, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Mensagem Retornada
			String mensagemRetorno = fachada.retornaMensagemAcompanhamentoArquivosRoteiroImei( imei );
			
			if(mensagemRetorno != null && !mensagemRetorno.equals(null)){
				System.out.println("Inicio : Baixando Mensagem");

				String mensagem = "13|"+mensagemRetorno+"|";
				
				byte[] bytesMensagem = mensagem.getBytes();
				
				response.setContentLength( 1+bytesMensagem.length );

				out.write( RESPOSTA_OK );
				out.write( bytesMensagem );
				
				out.flush();

				System.out.println("Fim: Baixando Mensagem");	        
			}else{
				System.out.println("N�o h� mensagens para baixar...");

				response.setContentLength( 1 );

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
		} catch( Exception e ){
			System.out.println("Erro ao Baixar Mensagem");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * 
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * M�todo que insere o array de bytes vindo do celular
	 * e o insere no banco
	 *  
	 * @param data
	 * @param response
	 * @param out
	 * 
	 * @param fileLength 
	 * @throws IOException
	 */
	public void receberFoto( DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{		
		// Lemos o n�mero da OS para qual essa foto pertence
		int numeroOS = data.readInt();
		
		// Verificamos qual o tipo da foto, se inicio, meio ou fim
		int tipoFoto = data.readInt();
		
		long fileSize = data.readLong();

		byte[] bytesFoto = new byte[(int)fileSize]; 
		int read = 0;
		int numRead = 0;
		while (read < bytesFoto.length && (numRead=data.read(bytesFoto, read, bytesFoto.length-read)) >= 0) {
			read = read + numRead;
		}
		
		// inserimos na base
		Fachada.getInstancia().inserirFotoOrdemServico( numeroOS, tipoFoto, bytesFoto );		
	}
	
	/**
	 * 
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * Altera a situa��o de uma ordem de servi�o no GSAN
	 * 
	 * 
	 * @param data
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void atualizarSituacaoProgramacaoOrdemServico( 
			DataInputStream data, 
			HttpServletResponse response, 
			OutputStream out ) throws IOException{		
		try{		
			// Lemos o n�mero da OS para qual essa foto pertence
			int numeroOS = data.readInt();
		
			// Verificamos qual o tipo da foto, se inicio, meio ou fim
			short situacao = data.readShort();
		
			Fachada.getInstancia().atualizarSituacaoProgramacaoOrdemServico( numeroOS , situacao);
		} catch( Exception e ){
			System.out.println("Erro ao Baixar Mensagem");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
		
	}
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * M�todo que insere o array de bytes vindo do celular
	 * e o insere no banco
	 *  
	 * @param data
	 * @param response
	 * @param out
	 * 
	 * @param fileLength 
	 * @throws IOException
	 */
	public void encerrarOS( DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{		
		try{
		
			long fileSize = data.readLong();
	
			byte[] bytesArquivo = new byte[(int)fileSize]; 
			int read = 0;
			int numRead = 0;
			while (read < bytesArquivo.length && (numRead=data.read(bytesArquivo, read, bytesArquivo.length-read)) >= 0) {
				read = read + numRead;
			}
			
			String arquivo = new String(bytesArquivo);
			
			BufferedReader buffer = new BufferedReader(new StringReader(arquivo));
			
			// inserimos na base
			Fachada.getInstancia().retornoAtualizarOrdemServicoAcompanhamentoServico( buffer );
			
			response.setContentLength( 1 );

			out.write( RESPOSTA_OK );

			out.flush();

		}catch( Exception e ){
			System.out.println("Erro ao efetuar opera��o.");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * [UC-1225] Incluir dados acompanhamento servico 
	 * 
	 * M�todo que recebe a coordenada da equipe
	 *  
	 * @author Th�lio Ara�jo
	 * @param data
	 * 
	 * @param response
	 * @param out
	 * 
	 * @param fileLength 
	 * @throws IOException
	 */
	public void receberCoordenada(long imei, DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{		
		try{
			long fileSize = data.readLong();
			
			byte[] bytesArquivo = new byte[(int)fileSize]; 
			int read = 0;
			int numRead = 0;
			while (read < bytesArquivo.length && (numRead=data.read(bytesArquivo, read, bytesArquivo.length-read)) >= 0) {
				read = read + numRead;
			}
			
			String arquivo = new String(bytesArquivo);
			
			Fachada.getInstancia().inserirCoordenadaPercursoEquipe(imei,arquivo);
			
			response.setContentLength( 1 );

			out.write( RESPOSTA_OK );

			out.flush();
		}catch( Exception e ){
			System.out.println("Erro ao efetuar opera��o.");
			e.printStackTrace();
			response.setContentLength( 1 );
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
	 * @author Bruno Barros
	 * @date 15/06/2011
	 *
	 * @param imei
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	public void atualizarSituacaoArquivo( long imei, short situacaoAnterior,short situacaoAtual, HttpServletResponse response, OutputStream out ) throws IOException{		
		
		try{
			// Arquivo retornado
			fachada.atualizarSituacaoArquivoTextoAcompanhamentoServico( imei,situacaoAnterior,situacaoAtual );
			
			response.setContentLength( 1 );

			out.write( RESPOSTA_OK );

			out.flush();

				
		} catch( Exception e ){
			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}
}
