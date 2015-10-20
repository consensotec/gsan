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
package gcom.gui.faturamento;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioErrosMovimentosContaPreFaturadas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
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
 * [UC0820] Atualizar Faturamento do Movimento do Celular
 * 
 * @author Bruno Barros
 * @date 10/06/2009
 */

public class AtualizarFaturamentoMovimentoCelularAction extends ExibidorProcessamentoTarefaRelatorio {
	
	private int soAndroid = 1;
    private int  soJ2ME = 2;
    
    //QUANTIDADE DE PIPES DA PRIMEIRA LINHA
    //private int quantidadeRegistroPrimeiraLinha = 6;

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        try {
            DiskFileUpload upload = new DiskFileUpload();
            
            int indcFinalizacao = 0;
            Integer codRota = null;
    		Integer setorComercial = null;
    		Integer localidade = null;
    		boolean temRegistroTipo0 = false;
    		Integer matricula = null;
            
            // Parse the request
            List itensForm = upload.parseRequest(httpServletRequest);
            Iterator iteItensForm = itensForm.iterator();
            
            byte[] byteRelatorio = null;
            Integer idRota = 0;
            boolean indicadorSucessoAtualizacao = false;
            
            Fachada fachada = Fachada.getInstancia();            
            
            int so = soJ2ME;
            
            while ( iteItensForm.hasNext() ){
                
                FileItem item = ( FileItem )iteItensForm.next();
                
                // Caso não seja um field do formulario
                // é o arquivo
                if ( !item.isFormField() ){
                    // Lemos 
                    InputStreamReader reader = new InputStreamReader(item.getInputStream());
                    InputStreamReader inputSemRegistroZero = new InputStreamReader(item.getInputStream());
                    BufferedReader buffer = new BufferedReader(reader);
                    BufferedReader bufferSemRegistroZero = new BufferedReader(inputSemRegistroZero);
                    
                    String registro0 = buffer.readLine();
                    
                    System.out.println("Linha arquivo String: " + registro0);
                    
                    //Quebra por PIPE
                    String [] primeiraLinha = quebrarPorPipe(registro0);
                    
                    //Verificar se o arquivo é J2ME ou ANDROID
                    if(primeiraLinha != null && primeiraLinha.length > 1){
                    	//ARQUIVO ANDROID
                    	so = soAndroid;
                    }
                    
                    String[] arrayRegistro0 = parseResgistroTipo0(registro0, so);
                                       
                    if(Integer.parseInt(arrayRegistro0[0]) == 0){
                    	bufferSemRegistroZero = null;
                    	inputSemRegistroZero = null;
                    	temRegistroTipo0 = true;
                    	
                    																																																										
                    	if(so == soJ2ME){
                    		indcFinalizacao = Integer.parseInt(arrayRegistro0[1]);
                    		codRota = Integer.parseInt(arrayRegistro0[2]);
                    		setorComercial = Integer.parseInt(arrayRegistro0[3]);
                    		localidade = Integer.parseInt(arrayRegistro0[4]);
                    		
                    		idRota = fachada.obterIdRotaPorSetorComercialELocalidade(codRota,setorComercial,localidade);
                    		
                    	}else if(so == soAndroid){
                    		indcFinalizacao = Integer.parseInt(arrayRegistro0[1]);
                    		codRota = Integer.parseInt(arrayRegistro0[4]);
                    		setorComercial = Integer.parseInt(arrayRegistro0[3]);
                    		localidade = Integer.parseInt(arrayRegistro0[2]);
                    		matricula = Integer.parseInt(arrayRegistro0[5]);
                    		
                    		idRota = fachada.obterIdRotaDeMovimentoRotEmpresaPorImovel(matricula);
                    	}
                    	
                    	
                		
                		
                		// Caso não encotremos essa rota, pesquisamos 
            			// assumindo que o imovel possue rota alternativa
            			if ( idRota == null ){
            				String primeiroRegistro = null;
            				
            				
            				
            				
            				//Obter Matricula J2ME
            				if(so == soJ2ME){
            					primeiroRegistro = buffer.readLine();
            					matricula = Integer.parseInt( primeiroRegistro.substring( 1, 10 ) );
            				}
            				
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
            				if(so == soJ2ME){
            					arquivo.append( primeiroRegistro + "\n" );
            				}

            				while( ( linha = buffer.readLine() ) != null ){					
            					arquivo.append(linha + "\n");					
            				}				
            				
            				InputStream is = new ByteArrayInputStream( arquivo.toString().getBytes() );        	
                    		InputStreamReader readerRetorno = new InputStreamReader( is );    		
                    		buffer = new BufferedReader(readerRetorno);
            			}                		
                		
            			// Caso o tipo de finalização seja de arquivo com imóveis faltando, pesquisamos quais ja chegaram
            			if ( indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO ){        				
            				
            				short indicadorAndroid = ConstantesSistema.SIM;
            				
            				if(so==soJ2ME){
            					indicadorAndroid = ConstantesSistema.NAO;
            				}
            				buffer = fachada.removerImoveisJaProcessadosBufferImpressaoSimultanea( idRota, buffer,indicadorAndroid);
            				
            			}
                    } else {          
                    	buffer = bufferSemRegistroZero;
                    }
            		
        			
        			RetornoAtualizarFaturamentoMovimentoCelularHelper helper = null;
        			
                    if ( buffer != null) {  
                    	 helper = fachada.atualizarFaturamentoMovimentoCelular( buffer, true, true, so );
                         byteRelatorio = helper.getRelatorioConsistenciaProcessamento();                    
                         indicadorSucessoAtualizacao = helper.getIndicadorSucessoAtualizacao();
                    }        			
                    
                    break;
                }                
            }
            
            if ( byteRelatorio != null ){            	
            	RelatorioErrosMovimentosContaPreFaturadas relatorio = 
            		new RelatorioErrosMovimentosContaPreFaturadas( 
            				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );
            	
            	relatorio.setRelatorio( byteRelatorio );
            	
            	httpServletRequest.setAttribute("telaSucessoRelatorio",true);
            	
				retorno = processarExibicaoRelatorio(relatorio,
						TarefaRelatorio.TIPO_PDF + "", httpServletRequest, httpServletResponse,	actionMapping);  
            }else{    	
            	// montando página de sucesso
        		montarPaginaSucesso(httpServletRequest,
        			"Faturamento Movimento Celular Atualizado com sucesso", 
        			"Voltar",
        			"/exibirAtualizarFaturamentoMovimentoCelularAction.do");
            }
            
			if(indicadorSucessoAtualizacao){
				
				Integer anoMesFaturamento = fachada.retornaAnoMesFaturamentoGrupoDaRota( idRota );
				
				if ( temRegistroTipo0 ){				
					if( indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA ||
						indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_ARQUIVO_IMOVEIS_FALTANDO	){

						if( !fachada.isRotaDividida(idRota, anoMesFaturamento)){
							//Verifica se a quantidade de imóveis bateu
							 Integer diferenca = fachada.pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa( idRota,  anoMesFaturamento );
				                
				                if ( diferenca != null && diferenca != 0 && !fachada.isRotaDividida( idRota, anoMesFaturamento ) ){
				                	 throw new ActionServletException("atencao.qtd_imoveis_errada");   
				                    
				                }else{
				                	Fachada.getInstancia().atualizarArquivoTextoEnviadoPorRotaIgnora(idRota,SituacaoTransmissaoLeitura.TRANSMITIDO, anoMesFaturamento);
				                }
						
						}

					} else if(indcFinalizacao == ProcessarRequisicaoDipositivoMovelImpressaoSimultaneaAction.FINALIZAR_LEITURA_INCOMPLETA){
						Fachada.getInstancia().atualizarArquivoTextoEnviadoPorRota(idRota, SituacaoTransmissaoLeitura.EM_CAMPO,
								SituacaoTransmissaoLeitura.FINALIZADO_INCOMPLETO, anoMesFaturamento);
					}
				}
			}
            
            return retorno;            
            
          
        } catch (FileUploadException e) {
            throw new ActionServletException("erro.atualizacao.nao_concluida", e);        
        } catch (IOException e) {
            throw new ActionServletException("erro.atualizacao.nao_concluida", e);        
        }catch (ErroRepositorioException e) {
        	throw new ActionServletException("erro.atualizacao.nao_concluida", e);
		} catch (ControladorException e) {
        	throw new ActionServletException("erro.atualizacao.nao_concluida", e);
		}
       
    }
    
    /**
     * [UC0820] - Atualizar Faturamento do Movimento Celular
     * 
     * [FS0001] - Verificar Existencia do arquivo de faturamento do movimento
     * celular
     * 
     * @author bruno
     * @date 11/06/2009
     *
     * @param arquivo
     */
   /* private Boolean verificarExistenciaArquivoFaturamentoCelular( List itens ){        
        return itens != null;
    }*/
    
    /**
     * [UC0820] - Atualizar Faturamento do Movimento Celular
     * 
     * [FS0002] - Verificar Existencia de Dados no Arquivo
     * 
     * @author bruno
     * @date 11/06/2009
     *
     * @param arquivo
     */
    /*private Boolean verificarExistenciaDadosArquivoFaturamentoCelular( List itens ){
        return itens.size() > 0;
    }*/
    
    private String[] quebrarPorPipe(String registro) {
		// |(PIPE) é um carecter especial. logo usamos (\\|) para fazer o split
		String[] b = registro.split("\\|"); 	
		return b;
	}
    
    private String[] parseResgistroTipo0(String registro0, int indicadorSO){
    	if (indicadorSO == soJ2ME){
    		return parseRegistroTipo0Substring(registro0);
    	} else {
    		return quebrarPorPipe(registro0);
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
    
}