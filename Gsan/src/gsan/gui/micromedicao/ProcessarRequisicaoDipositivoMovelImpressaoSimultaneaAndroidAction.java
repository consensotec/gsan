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
package gsan.gui.micromedicao;

import gsan.cadastro.SistemaAndroid;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gsan.gui.GcomAction;
import gsan.micromedicao.ServicoTipoCelular;
import gsan.micromedicao.SituacaoTransmissaoLeitura;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

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
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAndroidAction extends GcomAction {	
	
	/**
	 * Constantes de Classe.
	 */
	private static final char RESPOSTA_ERRO = '#';	
	private static final byte RESPOSTA_OK = '*';
	
	
	private static final int CONFIRMAR_ARQUIVO_RECEBIDO = 10;
	private static final int ATUALIZAR_MOVIMENTO = 1;
	public static final int FINALIZAR_LEITURA = 2;
	public static final int FINALIZAR_LEITURA_INCOMPLETA = 6;
    public static final int FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO = 7;
    private static final int RECEBER_FOTO = 8;
    private static final int PING = 15;
    private static final int BAIXAR_NOVA_VERSAO_APK = 11;
    private static final int VERIFICAR_VERSAO_ANDROID = 12;
    private static final int BAIXAR_ROTA = 13;
    
	/**
	 * Método Execute do Action
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
				case ATUALIZAR_MOVIMENTO:
					//Atualizar Movimento
					this.atualizarMovimentacao(din,response,out);
					break;
				case FINALIZAR_LEITURA:
					//Finalizar Movimento
					this.finalizarMovimentacao(din,response,out, 1);
					break;
				case FINALIZAR_LEITURA_INCOMPLETA:
					//Finalizar Movimento
					this.finalizarMovimentacao(din,response,out, 1);
					break;
				case CONFIRMAR_ARQUIVO_RECEBIDO:
					//Confirmar recebimento
					this.confirmacaoArquivoRecebido(din,response,out);
					break;
                case FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO:
					this.finalizarMovimentacao(din,response,out, 1);
					break;
                case RECEBER_FOTO:
//                	Recebe as fotos de Anormalidade
                	this.receberFoto(din,response,out);
                	break;
                case PING:
    				out.write( RESPOSTA_OK );
    				out.flush();
    				break;
                case BAIXAR_NOVA_VERSAO_APK:
					this.baixarNovaVersaoIscAndroid(din, response, out);
					break;	
                case VERIFICAR_VERSAO_ANDROID:
    				this.verificarVersaoIscAndroid(din,response,out);
    				break;
                case BAIXAR_ROTA:
					//Baixar Arquivo
					this.baixarArquivo(din,response,out);
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
	 * [UC0811] Processar Requisições do Dispositivo Móvel Impressao Simultanea.
	 * 
	 * Método que baixa o arquivo de entrada do servidor.
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
// COMENTADO PARA ENTRAR SÓ NA PROX. VERSÃO		 
//		String versao = data.readUTF();
//		System.out.println("versao: " + versao);
//		if(versao != null && versao.equals("v0.1")){
//*************************************************************		
		
		try{
			Object[] retorno =fachada.baixarArquivoTextoParaLeituristaImpressaoSimulaneaAndroid(imei);
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
				
				// Parametro que identifica que o tipo de arquivo da rota está sendo enviado
				String parametroArquivoBaixarRota = "arquivoRoteiro=";				
				
				// 1 do tipo de resposta ok + tipo do arquivo + parametro Arquivo baixar rota + tamanho do arquivo da rota
				response.setContentLength( 1+tipoArquivo.getBytes().length+parametroArquivoBaixarRota.getBytes().length+arq.length );
				
				System.out.println("Lenght arquivo ---> "+ 1+tipoArquivo.getBytes().length+parametroArquivoBaixarRota.getBytes().length+arq.length);

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
//			System.out.println("Erro versão Mobile");
//			response.setContentLength(RESPOSTA_ERRO_VERSAO);
//			OutputStream out = response.getOutputStream();
//			out.write(RESPOSTA_ERRO_VERSAO);
//			out.flush();
//	        out.close();
//		}
//******************************************************************			
	}
	
	/**
	 * [UC0811] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Método que atualiza as movimentações dos leituristas.
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
//        String s = "mensagem=voltou";
//	    response.setContentLength( 1 + s.length() );
//    	out.write(RESPOSTA_OK);		
//	    out.write(s.getBytes());
//		out.flush();
		try{
			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = Fachada.getInstancia().atualizarFaturamentoMovimentoCelular( buffer, false, false, 1);
			// caso tenha gerado relatório de inconsistência,
			// então retorna que o imóvel não foi processado
			if ( retorno.getRelatorioConsistenciaProcessamento() != null ){                
			    System.out.println("Erro ao atualizar faturamento movimento celular");
			    response.setContentLength( 1 );			
			    out.write(RESPOSTA_ERRO);
			    out.flush();
			} else if ( retorno.getMensagemComunicacaoServidorCelular() != null ){                    
			    System.out.println("Validação encontrada. Retornando para o celular.");
			    
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
	 * [UC0631] Processar Requisições do Dispositivo Móvel.
	 * 
	 * Método que finaliza as movimentações.
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
			
			ArrayList<String> arrayRegistro0 = Util.split(registro0);
            
            int indcFinalizacao = Integer.parseInt(arrayRegistro0.get(1));
	       	 Integer localidade = Integer.parseInt(arrayRegistro0.get(2));
	    	 Integer setorComercial = Integer.parseInt(arrayRegistro0.get(3));
        	 Integer codRota = Integer.parseInt(arrayRegistro0.get(4));
        	 Integer idPrimeiroImovel = Integer.parseInt(arrayRegistro0.get(5));

			idRota = fachada.obterIdRotaDeMovimentoRotEmpresaPorImovel(idPrimeiroImovel);
			
			// Caso não encotremos essa rota, pesquisamos 
			// assumindo que o imovel possue rota alternativa
			if ( idRota == null ){
//				String primeiroRegistro = buffer.readLine();
//				ArrayList<String> arrayRegistro1 = Util.split(primeiroRegistro);
//				String matricula =arrayRegistro1.get(0);
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "rotaAlternativa.setorComercial" );
				filtroImovel.adicionarParametro( new ParametroSimples( FiltroImovel.ID, idPrimeiroImovel ) );				
				Collection<Imovel> colImovel = Fachada.getInstancia().pesquisar( filtroImovel, Imovel.class.getName() );
				Imovel imo = (Imovel) Util.retonarObjetoDeColecao( colImovel );
				
				localidade = imo.getLocalidade().getId();
				setorComercial = imo.getRotaAlternativa().getSetorComercial().getCodigo();
				codRota = imo.getRotaAlternativa().getCodigo().intValue();
				
				idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota,setorComercial,localidade);
				
				// Remontamos o buffer
				String linha;
				StringBuffer arquivo = new StringBuffer();
//				arquivo.append( primeiroRegistro + "\n" );

				while( ( linha = buffer.readLine() ) != null ){					
					arquivo.append(linha + "\n");					
				}				
				
				InputStream is = new ByteArrayInputStream( arquivo.toString().getBytes() );        	
        		InputStreamReader readerRetorno = new InputStreamReader( is );    		
        		buffer = new BufferedReader(readerRetorno);
			}
			
			// Caso o tipo de finalização seja de arquivo com imóveis faltando, pesquisamos quais ja chegaram
			if ( indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO ){
				buffer = fachada.removerImoveisJaProcessadosBufferImpressaoSimultanea( idRota, buffer, ConstantesSistema.SIM );
			}
			
			RetornoAtualizarFaturamentoMovimentoCelularHelper retorno = null;
			
            if ( buffer != null  ) {  
            	 retorno = fachada.atualizarFaturamentoMovimentoCelular( buffer,false, true, indicadorAndroid);
            }

			//caso não tenha gerado relatório de inconsistência,
			//então retorna que o imóvel não foi processado
            if( retorno != null && ( retorno.getRelatorioConsistenciaProcessamento() != null || retorno.getMensagemComunicacaoServidorCelular() !=null ) ){
				System.out.println("Erro ao atualizar faturamento movimento celular");
				response.setContentLength( 1 );			
				out.write(RESPOSTA_ERRO);
				out.flush();
			} else {            
				
				// Verificamos se a quantidade de imóveis que chegaram é mesma
                // gerada no arquivo de ida
                Integer anoMesFaturamento = fachada.retornaAnoMesFaturamentoGrupoDaRota( idRota );
                
                //cria uma coleção de situações de transmissões para verificar se é para finalizar o arquivo princial 
                //(Caso todos arquivos divididos estejam com a situação de Finalizado ou Finalizado Incompleto então finaliza 
                // o arquivo principal para finalizado ou finalizado imcompleto).
                Integer[] idsSituacaoTransmissao = new Integer[1];
                idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
                
				//Atualiza a situação do arquivo texto de em campo para finalizado
				//Alteração feita por Sávio Luiz 
				//Data:05/04/2010
				if(indcFinalizacao == FINALIZAR_LEITURA || indcFinalizacao == FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO ){
	                
	                Integer diferenca = fachada.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa( idRota,  anoMesFaturamento );
	                
	                if ( diferenca != 0 && !fachada.isRotaDividida( idRota, anoMesFaturamento ) ){
	                    System.out.println("Validação encontrada. Retornando para o celular.");
	                    
	                    String mensagem = "mensagem=A quantidade de imóveis enviados não corresponde ao esperado";
	                    response.setContentLength( 1 + mensagem.getBytes().length );        
	                    out.write(RESPOSTA_ERRO);
	                    out.write( mensagem. getBytes() );
	                    out.flush();
	                }else{
	                	// Se o arquivo não for dividido, então atualiza o arquivo pela rota
	                	if(!fachada.isRotaDividida( idRota, anoMesFaturamento )){
	                	  fachada.atualizarArquivoTextoEnviadoPorRotaIgnora(idRota, SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
	                	}else{
	                		//caso exista arquivo dividido, então atualiza o arquivo dividido pelo imei
	                		fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
	    							SituacaoTransmissaoLeitura.TRANSMITIDO);
	                		//verifica se todas as rotas divididas estão com a situação de FINALIZADO
	                		if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
	                			// atualiza o arquivo principal para a situação de finalizado 
	                			fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
	         							SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
	                		}else{
	                			// verifica se todas as rotas divididas estão com a situação de FINALIZADO ou finalizado imcompleto.
	                			idsSituacaoTransmissao = new Integer[2];
	                			idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
	                			idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
	                			if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
		                			// atualiza o arquivo principal para a situação de finalizado 
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
					  fachada.atualizarArquivoTextoEnviadoPorRotaIgnora(idRota,	SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO, anoMesFaturamento);
					}else{
						//caso exista arquivo dividido, então atualiza o arquivo dividido pelo imei
                		fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
    							SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO);
                		//verifica se todas as rotas divididas estão com a situação de FINALIZADO
                		if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
                			// atualiza o arquivo principal para a situação de finalizado 
                			fachada.atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
         							SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
                		}else{
                			// verifica se todas as rotas divididas estão com a situação de FINALIZADO ou finalizado imcompleto.
                			idsSituacaoTransmissao = new Integer[2];
                			idsSituacaoTransmissao[0] = SituacaoTransmissaoLeitura.TRANSMITIDO;
                			idsSituacaoTransmissao[1] = SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO;
                			if(!fachada.verificarExistenciaArquivosDivididosSituacaoDiferente(idRota, anoMesFaturamento,idsSituacaoTransmissao)){
	                			// atualiza o arquivo principal para a situação de finalizado 
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
	 * Método de Comfirmação do Recebimento do Arquivo
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
			
			//Atualiza a situação do arquivo texto de "em campo" para "finalizado e não transmitido"
			//Alteração feita por Sávio Luiz 
			//Data:05/04/2010
			fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.EM_CAMPO,
					SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO);
			
			//Atualiza a situação do arquivo texto de "liberado" para "em campo"
			//Alteração feita por Sávio Luiz 
			//Data:05/04/2010
			fachada.atualizarArquivoTextoEnviado(imei, SituacaoTransmissaoLeitura.LIBERADO,
					SituacaoTransmissaoLeitura.EM_CAMPO);
			
			//Atualiza a situação do arquivo texto de "disponível" para "liberado"
			//Alteração feita por Sávio Luiz 
			//Data:05/04/2010
			if ( fachada.liberaProximoArquivoImpressaoSimultaneaOnLine() ){
				fachada.atualizarArquivoTextoMenorSequencialLeitura(imei,SituacaoTransmissaoLeitura.DISPONIVEL,
						SituacaoTransmissaoLeitura.LIBERADO,ServicoTipoCelular.IMPRESSAO_SIMULTANEA);
				
				fachada.atualizarArquivoTextoMenorSequencialLeitura(imei,SituacaoTransmissaoLeitura.DISPONIVEL,
						SituacaoTransmissaoLeitura.LIBERADO,ServicoTipoCelular.LEITURA);
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
	
	 
	
	private String[] parseResgistroTipo0(String registro0, int indicadorSO){
    	if (indicadorSO==2){
    		return parseRegistroTipo0Substring(registro0);
    	} else {
    		return parseRegistroTipo0Pipe(registro0);
    	}
    }
    
	private String[] parseRegistroTipo0Substring(String registro0) {
		String[] retorno = new String[5];
		
		retorno[0] = registro0.substring(0,1);
		retorno[1] = registro0.substring(1,2);
		retorno[2] = registro0.substring(8,15);
		retorno[3] = registro0.substring(5,8);
		retorno[4] = registro0.substring(2,5);		
		
		return retorno;
	}
    
	private String[] parseRegistroTipo0Pipe(String registro0) {
		return registro0.split("\\|");
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
	    	
	    	//Lemos o número do imóvel a que essa foto pertence
	    	int numeroImovel = din.readInt();
	    	
	    	//Lemos o número da leitura de anormalidade
	    	int idLeituraAnormalidade = din.readInt();
	    	
	    	//Lemos o Ano/Mês Referencia
	    	int anoMesReferencia = din.readInt();
	    	
	    	//foto tipo
	    	int tipoFoto = din.readInt();

	    	//Lemos o Ano/Mês Referencia
	    	int tipoMedicao = din.readInt();
	    	
	    	long fileSize = din.readLong();
			
			byte[] bytesFoto = new byte[(int)fileSize];
			int read = 0;
			int numRead = 0;
			while(read < bytesFoto.length && (numRead=din.read(bytesFoto, read, bytesFoto.length-read))>= 0){
				read = read + numRead;
			}
			
			//inserimos na base
			System.out.println("INICIO: Recebendo foto anormalidade leitura IMÓVEL: "+numeroImovel);
			
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
            
			getFachada().inserirFotoMovimentoRoteiroEmpresa(numeroImovel, idLeituraAnormalidade, anoMesReferencia,tipoFoto,tipoMedicao, bytesFoto);
		
			arquivoOriginal.delete();
			arquivoRedimensionado.delete();
			
			System.out.println("FIM: Recebendo foto anormalidade leitura IMÓVEL: "+numeroImovel);
			
			response.setContentLength(1+read+bytesFoto.length);
			
			out.write(RESPOSTA_OK);
			out.flush();
			
			System.out.println("FIM: Recebendo foto anormalidade leitura ");
			
    	} catch (Exception e) {
    		e.printStackTrace();
			System.out.println("ERROR: Ao receber a foto de anormalidade de leitura ");
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

    /**
     * [UC1302] RM3982
     * 
     * Método que baixa a nova versão do mobile para o android
     * 
     * @author Carlos Chaves
     * @date 29/08/2012
     *  
     * @param 
     * @param response
     * @throws IOException
     */
    private void baixarNovaVersaoIscAndroid(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException{
		Fachada fachada = Fachada.getInstancia();
        
        try{
        	Object[] obj = fachada.baixarNovaVersaoApk( SistemaAndroid.IMPRESSAO_SIMULTANEA );
        	if(obj != null){
        		
        		byte[] arq = (byte[]) obj[0];
            	
                System.out.println("Inicio : Baixando NOVA VERSÃO, ARQUIVO APK Android ISC");
                response.setContentLength( arq.length );

                //out.write(RESPOSTA_OK);
                //out.write( parametroTipoArquivo.getBytes() );                
                
                out.write(arq);
                out.flush();

                System.out.println("Fim: Baixando NOVA VERSÃO, ARQUIVO APK Android ISC");         
            }else{
                System.out.println("Erro ao Baixar arquivo Android ISC");
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
    
	private void verificarVersaoIscAndroid(DataInputStream din, HttpServletResponse response, OutputStream out) throws IOException{
		Fachada fachada = Fachada.getInstancia();
        
        try{
        	Object[] obj = fachada.baixarNovaVersaoApk( SistemaAndroid.IMPRESSAO_SIMULTANEA );
        	
        	if(obj != null){
        		
        		String versao = (String)obj[1].toString();
        		
        		System.out.println("Inicio : Verificar Versao,  Android ISC");
              //  response.setContentLength( versao.length());

                out.write(RESPOSTA_OK);
               
        		out.write(versao.getBytes());
                out.flush();

                System.out.println("Fim: Verificar Versao, ARQUIVO APK Android ISC");  
        	}else{
        		System.out.println("Erro ao Baixar arquivo Android ISC");
                response.setContentLength( 1 );

                out.write(RESPOSTA_ERRO);
                out.flush();
        	}
        } catch( Exception e ){

            System.out.println("Erro ao Baixar valor da VERSÃO Android ISC");
            response.setContentLength( 1 );
            out.write(RESPOSTA_ERRO);
            out.flush();

         }
	}
    
    
		
}