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

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.gui.GcomAction;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction extends GcomAction {	
	
	/**
	 * Constantes de Classe.
	 */
	private static final char RESPOSTA_ERRO = '#';	
	private static final byte RESPOSTA_OK = '*';
	
	
	private static final int CONFIRMAR_ARQUIVO_RECEBIDO = 3;
	private static final int PACOTE_BAIXAR_ARQUIVO = 0;
	private static final int ATUALIZAR_MOVIMENTO = 1;
	public static final int FINALIZAR_LEITURA = 2;
	public static final int FINALIZAR_LEITURA_INCOMPLETA = 6;
    private static final int BAIXAR_NOVA_VERSAO_JAD = 4;
    private static final int BAIXAR_NOVA_VERSAO_JAR = 5;
    public static final int FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO = 7;
    public static final int RECEBER_FOTO = 8;

	/**
	 * M�todo Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		try {
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);			
						
			int pacote = din.readByte();		
				
			System.out.println("Solicitacao Mobile : " + pacote);
			switch(pacote){
				case PACOTE_BAIXAR_ARQUIVO:
					//Baixar Arquivo
					this.baixarArquivo(din,response,out);
					break;
				case ATUALIZAR_MOVIMENTO:
					//Atualizar Movimento
					this.atualizarMovimentacao(din,response,out);
					break;
				case FINALIZAR_LEITURA:
					//Finalizar Movimento
					this.finalizarMovimentacao(din,response,out, 2);
					break;
				case FINALIZAR_LEITURA_INCOMPLETA:
					//Finalizar Movimento
					this.finalizarMovimentacao(din,response,out,2);
					break;
				case CONFIRMAR_ARQUIVO_RECEBIDO:
					//Finalizar Movimento
					this.confirmacaoArquivoRecebido(din,response,out);
					break;
                case BAIXAR_NOVA_VERSAO_JAD:
                    // Carrega o arquivo jad do banco
                    this.baixarNovaVersaoJad( din, response, out );
                    break;
                case BAIXAR_NOVA_VERSAO_JAR:
                    // Carrega o arquivo jar do banco                    
                    this.baixarNovaVersaoJar( din, response, out );
                    break;                      
                case FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO:
					this.finalizarMovimentacao(din,response,out,2);
					break;
                case RECEBER_FOTO:
                	this.receberFoto(din, response, out);
                	break;
           }
				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			response.setContentLength( 1 );			
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
			} catch (IOException e1) {
				System.out.println("Erro: " + e.getMessage());
			}			
		}finally{
			if (in != null) {
				try {
					in.close();					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(out != null){
				try {					
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
		
	}
	
	/**
	 * [UC0811] Processar Requisi��es do Dispositivo M�vel Impressao Simultanea.
	 * 
	 * M�todo que baixa o arquivo de entrada do servidor.
	 * 
	 * @author Bruno Barros
	 * @date 13/09/2009
	 *  
	 * @param data
	 * @param response
	 * @throws IOException
	 */
	public void baixarArquivo(DataInputStream data, HttpServletResponse response, OutputStream out ) throws IOException{
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();
		System.out.println("imei: " + imei);
		
//******************************************************************	
// 08/01/2009		
// COMENTADO PARA ENTRAR S� NA PROX. VERS�O		 
//		String versao = data.readUTF();
//		System.out.println("versao: " + versao);
//		if(versao != null && versao.equals("v0.1")){
//*************************************************************		
		
		try{
			Object[] retorno =fachada.baixarArquivoTextoParaLeituristaImpressaoSimulanea(imei,ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			byte[] arq = (byte[]) retorno[0];
			String nomeArquivo = (String) retorno[1];
			
			if(arq != null){
				System.out.println("Inicio : Baixando arquivo Mobile");
				
				String tipoArquivo = "";
				
				if  ( nomeArquivo.endsWith( ".txt" ) ){
					tipoArquivo = "tipoArquivo=T&";	
				} else {
					tipoArquivo = "tipoArquivo=G&";
				}
				
				// Parametro que identifica que o tipo de arquivo da rota est� sendo enviado
				String parametroArquivoBaixarRota = "arquivoRoteiro=";				
				
				// 1 do tipo de resposta ok + tipo do arquivo + parametro Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength( 1+tipoArquivo.getBytes().length+parametroArquivoBaixarRota.getBytes().length+arq.length );

				out.write(RESPOSTA_OK);
				out.write( tipoArquivo.getBytes() );
				out.write( parametroArquivoBaixarRota.getBytes() );
				out.write( arq );
				
				out.flush();

				System.out.println("Fim: Baixando arquivo Mobile");	        
			}else{
				System.out.println("Erro ao Baixar arquivo Mobile");
				response.setContentLength( 1 );
				//OutputStream out = response.getOutputStream();
				out.write(RESPOSTA_ERRO);
				out.flush();
		     //   out.close();
			}
			
		} catch( Exception e ){

			System.out.println("Erro ao Baixar arquivo Mobile");
			response.setContentLength( 1 );
			out.write(RESPOSTA_ERRO);
			out.flush();

			}
					
//******************************************************************			
//			else { 
//			System.out.println("Erro vers�o Mobile");
//			response.setContentLength(RESPOSTA_ERRO_VERSAO);
//			OutputStream out = response.getOutputStream();
//			out.write(RESPOSTA_ERRO_VERSAO);
//			out.flush();
//	        out.close();
//		}
//******************************************************************			
	}
	
	/**
	 * [UC0811] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo que atualiza as movimenta��es dos leituristas.
	 *
	 * @author Bruno Barros
	 * @date 10/11/2009
	 * 
	 * @throws IOException 
	 *
	 */
	public void atualizarMovimentacao(DataInputStream data , HttpServletResponse response,OutputStream out) throws IOException{
		InputStreamReader reader = new InputStreamReader( data );
        BufferedReader buffer = new BufferedReader(reader);
        	
		try{
			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = Fachada.getInstancia().atualizarFaturamentoMovimentoCelular( buffer, false, false, 2 );
			// caso tenha gerado relat�rio de inconsist�ncia,
			// ent�o retorna que o im�vel n�o foi processado
			if ( retorno.getRelatorioConsistenciaProcessamento() != null ){                
			    System.out.println("Erro ao atualizar faturamento movimento celular");
			    response.setContentLength( 1 );			
			    out.write(RESPOSTA_ERRO);
			    out.flush();
			} else if ( retorno.getMensagemComunicacaoServidorCelular() != null ){                    
			    System.out.println("Valida��o encontrada. Retornando para o celular.");
			    
			    response.setContentLength( 1 + retorno.getMensagemComunicacaoServidorCelular().length() );
			    
			    if ( retorno.getIndicadorSucessoAtualizacao() ){
			    	out.write(RESPOSTA_OK);	
			    } else {
			    	out.write(RESPOSTA_ERRO);
			    }
			    
                out.write( retorno.getMensagemComunicacaoServidorCelular().getBytes() );
			    out.flush();			    
			} else {
			    response.setContentLength( 1 );         
			    out.write(RESPOSTA_OK);
			    out.flush();
			    System.out.println("Fim: atualizar faturamento movimento celular");                    
			}
        } catch( Exception e ){
			e.printStackTrace();
			System.out.println("Erro ao atualizar faturamento movimento celular");
			response.setContentLength( 1 );			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	
	/**
	 * [UC0631] Processar Requisi��es do Dispositivo M�vel.
	 * 
	 * M�todo que finaliza as movimenta��es.
	 *
	 * @author Bruno Barros
	 * @date 10/11/2009
	 * 
	 * @throws IOException 
	 *
	 */
	public void finalizarMovimentacao(DataInputStream data, HttpServletResponse response,OutputStream out, int indicadorAndroid) throws IOException{
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();
		Integer idRota =  0;
		
		InputStreamReader reader = new InputStreamReader( data );
        BufferedReader buffer = new BufferedReader(reader);

		System.out.println("Finalizando arquivo Mobile imei=" +imei );
		
		try{
			String registro0 = buffer.readLine();
			
			int indcFinalizacao = Integer.parseInt(registro0.substring(1,2));
			Integer codRota = Integer.parseInt(registro0.substring(8,15));
			Integer setorComercial = Integer.parseInt(registro0.substring(5,8));
			Integer localidade = Integer.parseInt(registro0.substring(2,5));
			
			idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota,setorComercial,localidade);
			
			// Caso n�o encotremos essa rota, pesquisamos 
			// assumindo que o imovel possue rota alternativa
			if ( idRota == null ){
				String primeiroRegistro = buffer.readLine();
				Integer matricula = Integer.parseInt( primeiroRegistro.substring( 1, 10 ) );
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "rotaAlternativa.setorComercial" );
				filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID, matricula ) );				
				Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar( filtroImovel, Imovel.class.getName() );
				Imovel imo = (Imovel) Util.retonarObjetoDeColecao( colImovel );
				
				localidade = imo.getLocalidade().getId();
				setorComercial = imo.getRotaAlternativa().getSetorComercial().getCodigo();
				codRota = imo.getRotaAlternativa().getCodigo().intValue();
				
				idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota,setorComercial,localidade);
				
				// Remontamos o buffer
				String linha;
				StringBuffer arquivo = new StringBuffer();
				arquivo.append( primeiroRegistro + "\n" );

				while( ( linha = buffer.readLine() ) != null ){					
					arquivo.append(linha + "\n");					
				}				
				
				InputStream is = new ByteArrayInputStream( arquivo.toString().getBytes() );        	
        		InputStreamReader readerRetorno = new InputStreamReader( is );    		
        		buffer = new BufferedReader(readerRetorno);
			}
			
			// Caso o tipo de finaliza��o seja de arquivo com im�veis faltando, pesquisamos quais ja chegaram
			if ( indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO ){
				buffer = fachada.removerImoveisJaProcessadosBufferImpressaoSimultanea( idRota, buffer, ConstantesSistema.NAO );
			}
			
			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = null;
			
            if ( buffer != null  ) {  
            	 retorno = fachada.atualizarFaturamentoMovimentoCelular( buffer,false, true, indicadorAndroid );
            }

			//caso n�o tenha gerado relat�rio de inconsist�ncia,
			//ent�o retorna que o im�vel n�o foi processado
			if( retorno != null && ( retorno.getRelatorioConsistenciaProcessamento() != null || retorno.getMensagemComunicacaoServidorCelular() !=null ) ){
				System.out.println("Erro ao atualizar faturamento movimento celular");
				response.setContentLength( 1 );			
				out.write(RESPOSTA_ERRO);
				out.flush();
			} else {          
				
				// Verificamos se a quantidade de im�veis que chegaram � mesma
                // gerada no arquivo de ida
                Integer anoMesFaturamento = fachada.retornaAnoMesFaturamentoGrupoDaRota( idRota );
                
                //cria uma cole��o de situa��es de transmiss�es para verificar se � para finalizar o arquivo princial 
                //(Caso todos arquivos divididos estejam com a situa��o de Finalizado ou Finalizado Incompleto ent�o finaliza 
                // o arquivo principal para finalizado ou finalizado imcompleto).
                Integer[] idsSituacaoTransmissao = new Integer[1];
                idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
                
				//Atualiza a situa��o do arquivo texto de em campo para finalizado
				//Altera��o feita por S�vio Luiz 
				//Data:05/04/2010
				if(indcFinalizacao == FINALIZAR_LEITURA || indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO ){
	                
	                Integer diferenca = fachada.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa( idRota,  anoMesFaturamento );
	                
	                	
	                if ( diferenca != 0 && !fachada.isRotaDividida( idRota, anoMesFaturamento ) ){
	                    System.out.println("Valida��o encontrada. Retornando para o celular.");
	                    
	                    String mensagem = "mensagem=A quantidade de im�veis enviados n�o corresponde ao esperado";
	                    response.setContentLength( 1 + mensagem.getBytes().length );        
	                    out.write(RESPOSTA_ERRO);
	                    out.write( mensagem. getBytes() );
	                    out.flush();
	                }else{
	                	// Se o arquivo n�o for dividido, ent�o atualiza o arquivo pela rota
	                	if(!fachada.isRotaDividida( idRota, anoMesFaturamento )){
	                	  fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
							SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
	                	}else{
	                		//caso exista arquivo dividido, ent�o atualiza o arquivo dividido pelo imei
	                		fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
	    							SituacaoTransmissaoLeitura.TRANSMITIDO);
	                		//verifica se todas as rotas divididas est�o com a situa��o de FINALIZADO
	                		if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
	                			// atualiza o arquivo principal para a situa��o de finalizado 
	                			fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
	         							SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
	                		}else{
	                			// verifica se todas as rotas divididas est�o com a situa��o de FINALIZADO ou finalizado imcompleto.
	                			idsSituacaoTransmissao = new Integer[2];
	                			idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
	                			idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
	                			if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
		                			// atualiza o arquivo principal para a situa��o de finalizado 
		                			fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
		         							SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO, anoMesFaturamento);
	                			}
	                		}
	                		
	                	}
	    				response.setContentLength( 1 );			
	    				out.write(RESPOSTA_OK);
	    				out.flush();
	    				System.out.println("Fim: atualizar faturamento movimento celular");
	                }
				} else if(indcFinalizacao == FINALIZAR_LEITURA_INCOMPLETA){
					
					if(!fachada.isRotaDividida( idRota, anoMesFaturamento )){
					  fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
							SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO, anoMesFaturamento);
					}else{
						//caso exista arquivo dividido, ent�o atualiza o arquivo dividido pelo imei
                		fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
    							SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
                		//verifica se todas as rotas divididas est�o com a situa��o de FINALIZADO
                		if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
                			// atualiza o arquivo principal para a situa��o de finalizado 
                			fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
         							SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
                		}else{
                			// verifica se todas as rotas divididas est�o com a situa��o de FINALIZADO ou finalizado imcompleto.
                			idsSituacaoTransmissao = new Integer[2];
                			idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
                			idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
                			if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
	                			// atualiza o arquivo principal para a situa��o de finalizado 
	                			fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
	         							SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO, anoMesFaturamento);
                			}
                		}
                	}
					response.setContentLength( 1 );			
					out.write(RESPOSTA_OK);
					out.flush();
					System.out.println("Fim: atualizar faturamento movimento celular");
				}
				
                
			}
        } catch( Exception e ){
			e.printStackTrace();
			System.out.println("Erro ao atualizar faturamento movimento celular");
			response.setContentLength( 1 );			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	
	/**
	 * 
	 * M�todo de Comfirma��o do Recebimento do Arquivo
	 * 
	 * @since 23/05/08
	 * @param data
	 * @throws IOException
	 */
	public void confirmacaoArquivoRecebido(DataInputStream data, HttpServletResponse response,OutputStream out) throws IOException{
		Fachada fachada = Fachada.getInstancia();
		long imei = data.readLong();		
	
		System.out.println("Confirmando Recebimento do arquivo Mobile imei = "+imei);
		
		try{
			
			//Atualiza a situa��o do arquivo texto de "em campo" para "finalizado e n�o transmitido"
			//Altera��o feita por S�vio Luiz 
			//Data:05/04/2010
			fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
					SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO);
			
			//Atualiza a situa��o do arquivo texto de "liberado" para "em campo"
			//Altera��o feita por S�vio Luiz 
			//Data:05/04/2010
			fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.LIBERADO,
					SituacaoTransmissaoLeitura.EM_CAMPO);
			
			//Atualiza a situa��o do arquivo texto de "dispon�vel" para "liberado"
			//Altera��o feita por S�vio Luiz 
			//Data:05/04/2010
			if ( fachada.liberaProximoArquivoImpressaoSimultaneaOnLine() ){
				fachada.atualizarArquivoTextoMenorSequencialLeitura(imei,SituacaoTransmissaoLeitura.DISPONIVEL,
						SituacaoTransmissaoLeitura.LIBERADO,ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
			}
	        
	        response.setContentLength( 1 );			
			out.write(RESPOSTA_OK);
			out.flush();
			System.out.println("Fim: atualizar faturamento movimento celular");
		} catch( Exception e ){
			e.printStackTrace();
			System.out.println("Erro ao atualizar faturamento movimento celular");
			response.setContentLength( 1 );			
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	 /**
     * [UC0811] Processar Requisi��es do Dispositivo M�vel Impressao Simultanea.
     * 
     * M�todo que baixa a nova vers�o do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @param response
     * @throws IOException
     */
	public void  baixarNovaVersaoJad(DataInputStream data, HttpServletResponse response,OutputStream out) throws IOException{
        Fachada fachada = Fachada.getInstancia();
        
        try{
            byte[] arq = fachada.baixarNovaVersaoJad(  );

            if(arq != null){
            	String parametroTipoArquivo = "jad="; 
            	
                System.out.println("Inicio : Baixando NOVA VERS�O, ARQUIVO JAD Mobile");
                response.setContentLength( arq.length+parametroTipoArquivo.getBytes().length + 1 );

                out.write(RESPOSTA_OK);
                out.write( parametroTipoArquivo.getBytes() );                
                
                out.write(arq);
                out.flush();

                System.out.println("Fim: Baixando NOVA VERS�O, ARQUIVO JAD Mobile");         
            }else{
                System.out.println("Erro ao Baixar arquivo Mobile");
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
     * [UC0811] Processar Requisi��es do Dispositivo M�vel Impressao Simultanea.
     * 
     * M�todo que baixa a nova vers�o do mobile para o celular
     * 
     * @author Bruno Barros
     * @date 08/06/2010
     *  
     * @param 
     * @param response
     * @throws IOException
     */
    public void  baixarNovaVersaoJar(DataInputStream data, HttpServletResponse response,OutputStream out) throws IOException{
        Fachada fachada = Fachada.getInstancia();
        
        try{
            byte[] arq = fachada.baixarNovaVersaoJar( );

            if(arq != null){
            	
            	String parametroTipoArquivo = "jar="; 
            	
                System.out.println("Inicio : Baixando NOVA VERS�O, ARQUIVO JAR Mobile");
                response.setContentLength( arq.length+parametroTipoArquivo.getBytes().length + 1 );

                out.write(RESPOSTA_OK);
                out.write( parametroTipoArquivo.getBytes() );
                
                out.write(arq);
                out.flush();

                System.out.println("Fim: Baixando NOVA VERS�O, ARQUIVO JAR Mobile");         
            }else{
                System.out.println("Erro ao Baixar arquivo Mobile");
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
     * 
     * @author Davi Menezes
     * @date 17/07/2012
     * 
     * @parameters @param din
     * @parameters @param response
     * @parameters @param out
     * 
     * @throws IOException
     */
    private void receberFoto(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException {
    	try{
	    	System.out.println("INICIO: Recebendo foto anormalidade leitura ");
	    	
	    	//Lemos o n�mero do im�vel a que essa foto pertence
	    	int numeroImovel = din.readInt();
	    	
	    	//Lemos o n�mero da leitura de anormalidade
	    	int idAnormalidade = din.readInt();
	    	
	    	int idFotoTipoLeituraConsumoAnormalidade = din.readInt();
	    	
	    	//Lemos o Ano/M�s Referencia
	    	int anoMesReferencia = din.readInt();
	    	
	    	//foto tipo
	    	int tipoFoto = din.readInt();

	    	//Lemos o Ano/M�s Referencia
	    	int tipoMedicao = din.readInt();
	    	
	    	long fileSize = din.readLong();
			
			byte[] bytesFoto = new byte[(int)fileSize];
			int read = 0;
			int numRead = 0;
			while(read < bytesFoto.length && (numRead=din.read(bytesFoto, read, bytesFoto.length-read))>= 0){
				read = read + numRead;
			}
			
			//inserimos na base
			System.out.println("INICIO: Recebendo foto anormalidade leitura IM�VEL: "+numeroImovel);
			
			File arquivoOriginal  = new File("foto.jpg");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( new FileOutputStream(arquivoOriginal));
            bufferedOutputStream.write(bytesFoto);
         
            File arquivoRedimensionado = arquivoOriginal;
            SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
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
   
            bytesFoto = baosArquivoRedimensionado.toByteArray();
            
			getFachada().inserirFotoMovimentoRoteiroEmpresa(numeroImovel, idAnormalidade, idFotoTipoLeituraConsumoAnormalidade,anoMesReferencia,tipoFoto,tipoMedicao, bytesFoto);
			
			arquivoOriginal.delete();
			arquivoRedimensionado.delete();
			System.out.println("FIM: Recebendo foto anormalidade leitura IM�VEL: "+numeroImovel);
			
			response.setContentLength(1+read+bytesFoto.length);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Recebendo foto anormalidade leitura ");
			
    	} catch (Exception e) {
			System.out.println("ERROR: Ao receber a foto de anormalidade de leitura ");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
		
	}	 
	
}