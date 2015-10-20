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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroArrecadadorContrato;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.bean.RegistroHelperCodigo0;
import gcom.arrecadacao.bean.RegistroHelperCodigoA;
import gcom.arrecadacao.bean.RegistroHelperCodigoF;
import gcom.arrecadacao.bean.RegistroHelperCodigoG;
import gcom.arrecadacao.bean.RegistroHelperCodigoZ;
import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 * Action respons�vel para dar um onLoad no arquivo e importar os ceps.
 * 
 * @author S�vio Luiz
 * @created 24 de Agosto de 2005
 */
public class RegistrarMovimentoArrecadadoresAction extends GcomAction {

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		try {

			Integer idArrecadadorContrato = null;

			String idTipoMovimento = null;
			
			String nomeArrecadador = null;
			
			Short codigoAgente = null;
			
			BigDecimal valorMovimentoArrecadador = new BigDecimal("0.0");
			
			BigDecimal valorTotalMovimentoArrecadador = new BigDecimal("0.0");
			
			RegistroHelperCodigoA registroHelperCodigoA = null;
			
			RegistroHelperCodigo0 registroHelperCodigo0 = null;
			
			RegistroHelperCodigoF helperF;
			
			RegistroHelperCodigoG helperG;
			
			RegistroHelperCodigoZ helperZ;
			
			Fachada fachada = Fachada.getInstancia();

			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(httpServletRequest);

			FileItem item = null;

			// cria uma string builder que recupera o txt e joga para o
			// controlador para o processamento
			StringBuilder stringBuilderTxt = new StringBuilder();

			// cria um contador para saber quantas linhas ter� o txt
			int quantidadeRegistros = 0;
			
			// pega uma lista de itens do form
			Iterator iter = items.iterator();
			while (iter.hasNext()) {

				item = (FileItem) iter.next();

				if (item.getFieldName().equals("codigoAgente")) {
					codigoAgente = new Short(item.getString());
				}
				// No combo com o codigo do covenio o value � o id do Arrecadador pois dois arrecadadores 
				//podem tem o mesmo codigo do agente (CAEMA). 
				if (item.getFieldName().equals("codigoConvenio")) {
					idArrecadadorContrato = new Integer(item.getString());
				}

				if (item.getFieldName().equals("idTipoMovimento")) {
					idTipoMovimento = item.getString();
				}
				
			
				

				// verifica se n�o � diretorio
				if (!item.isFormField()) {

					// coloca o nome do item para maiusculo
					String nomeItem = item.getName().toUpperCase();
					
					// compara o final do nome do arquivo � .DEB se for debito autom�tico
					//ou .ARR se for c�digo de barras
					if ((idTipoMovimento != null && 
						idTipoMovimento.equals("DEBITO AUTOMATICO") && nomeItem.endsWith(".DEB")) || 
						(idTipoMovimento != null && 
                        idTipoMovimento.equals("CODIGO DE BARRAS") && nomeItem.endsWith(".ARR")) || 
                        (idTipoMovimento != null && nomeItem.endsWith(".REM")) || 
                        (idTipoMovimento != null && nomeItem.endsWith(".RET")) ||
                        (idTipoMovimento != null && nomeItem.endsWith(".FEB"))) {

						// abre o arquivo
						InputStreamReader reader = new InputStreamReader(item.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);

						// StringBuffer linha = new StringBuffer();
						// cria uma variavel do tipo boolean
						boolean eof = false;
						boolean primeiraLinha = true;
						// enquanto a variavel for false
						while (!eof) {

							// pega a linha do arquivo
							String linhaLida = buffer.readLine();
							
							// se for a ultima linha do arquivo
							if (linhaLida != null && linhaLida.length() > 0) {
								
								String codigoRegistro = linhaLida.substring(0, 1).toUpperCase();
								Character codigoRegistroCharacter = codigoRegistro.charAt(0);
								
								if (((int) codigoRegistroCharacter) >= 65 && ((int) codigoRegistroCharacter) <= 90) {

									// completa a string para o tamanho de 150 caso necessario.
									String linhaCompleta = null;
									if (linhaLida.length() != 150) {
										linhaCompleta = Util.completaString(linhaLida, 150);
									} else {
										linhaCompleta = linhaLida;
									}
									
									//[SB0001] - Validar Arquivo de Movimento de Arrecadador
				                    if (primeiraLinha) {
				                    	
				                    	/*
				                    	 * Colocado por Raphael Rossiter em 10/11/2008 Analista: Eduardo Borges
				                    	 * [SB0001] - Validar Arquivo de Movimento de Arrecadador
				                    	 */
				                    	FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

				            			filtroArrecadador.adicionarParametro(
				            				new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, codigoAgente));

				            			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

				            			Collection arrecadadorEncontrado = 
				            				this.getFachada().pesquisar(filtroArrecadador, Arrecadador.class.getName());
				            			
				            			if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {
				            				nomeArrecadador = ((Arrecadador) ((List) arrecadadorEncontrado).get(0)).getCliente().getNome();
				            			}
				            			
				            			FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
				            			
				            			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
				            					FiltroArrecadadorContrato.ID,idArrecadadorContrato));
				            			filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
				        						FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
				            			
				            			filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
				            			Collection colecaoArrecadadorContrato = Fachada.getInstancia().pesquisar(
				            					filtroArrecadadorContrato,ArrecadadorContrato.class.getName());
				            			
				            			ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato)Util.retonarObjetoDeColecao(colecaoArrecadadorContrato);
				            			
				            			
				            			registroHelperCodigoA = Fachada.getInstancia().validarArquivoMovimentoArrecadador(codigoRegistro, linhaCompleta, 
				                    	             codigoAgente, nomeArrecadador, idTipoMovimento, arrecadadorContrato, null, null, null, 
				                    	             arrecadadorContrato.getArrecadador().getId());
				                    	
				                    	primeiraLinha = false;
				                    }
									
				                    stringBuilderTxt.append(linhaCompleta);
									stringBuilderTxt.append("\n");
									quantidadeRegistros = quantidadeRegistros + 1;
									
									
									//[SB0001] - Validar Arquivo de Movimento de Arrecadador
									//Item 12 - Verificar se o valor  dos registros do arquivo � inv�lido									
									SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
									
									if(sistemaParametro.getIndicadorValorMovimentoArrecadador() == 1){
										if(codigoRegistro.equals("F")){
											
											helperF = (RegistroHelperCodigoF) fachada.distribuirdadosRegistroMovimentoArrecadador(linhaCompleta, null);
											valorMovimentoArrecadador = Util.somaBigDecimal(valorMovimentoArrecadador, new BigDecimal(helperF.getValorDebito()));
											
										}else if(codigoRegistro.equals("G")){
											
											helperG = (RegistroHelperCodigoG) fachada.distribuirdadosRegistroMovimentoArrecadador(linhaCompleta, null);
											valorMovimentoArrecadador = Util.somaBigDecimal(valorMovimentoArrecadador, new BigDecimal(helperG.getValorRecebido()));
											
										}else if(codigoRegistro.equals("Z")){
											
											helperZ = (RegistroHelperCodigoZ) fachada.distribuirdadosRegistroMovimentoArrecadador(linhaCompleta, null);
											valorTotalMovimentoArrecadador = new BigDecimal(helperZ.getValorTotalRegistrosArquivo());
											if(valorTotalMovimentoArrecadador.compareTo(valorMovimentoArrecadador)!= 0){
												throw new ActionServletException("atencao.valor_registros_arquivo_invalido");
											}
											
										}
									}									
									
								} 
								else {
									break;
								}
							} 
							else {
								break;
							}

						}

						// fecha o arquivo
						buffer.close();
						reader.close();
						item.getInputStream().close();

					}else if(idTipoMovimento != null 
                             && idTipoMovimento.equals("FICHA DE COMPENSACAO") 
                             && nomeItem.endsWith(".BOL")){
                     
                        // abre o arquivo
                        InputStreamReader reader = new InputStreamReader(item.getInputStream());
                        BufferedReader buffer = new BufferedReader(reader);

                        // StringBuffer linha = new StringBuffer();
                        // cria uma variavel do tipo boolean
                        boolean eof = false;
                        boolean primeiraLinha = true;
                        // enquanto a variavel for false
                        while (!eof) {

                            // pega a linha do arquivo
                            String linhaLida = buffer.readLine();
                            
                            // se for a ultima linha do arquivo
                            if (linhaLida != null && linhaLida.length() > 0) {
                            		
                            	String codigoRegistro = linhaLida.substring(0, 1).toUpperCase();
                            	
//                              Integer codigoRegistro = new Integer(linhaLida.substring(7, 8));
//                                if (((int) codigoRegistro) >= 0 && ((int) codigoRegistro) <= 9) {

                                    // completa a string para o tamanho de 240 caso necessario.
                                    String linhaCompleta = null;
                                    if (linhaLida.length() != 240) {
                                        linhaCompleta = Util.completaString(linhaLida, 240);
                                    } else {
                                        linhaCompleta = linhaLida;
                                    }
                                    
                                    //[SF0011] - Validar Arquivo de Movimento de Arrecadador da Ficha de Compensa��o
				                    if (primeiraLinha) {
				                    	
				                    	/*
				                    	 * Colocado por Raphael Rossiter em 10/11/2008 Analista: Eduardo Borges
				                    	 * [SF0011] - Validar Arquivo de Movimento de Arrecadador da Ficha de Compensa��o
				                    	 */
				                    	FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

				            			filtroArrecadador.adicionarParametro(
				            				new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, codigoAgente));

				            			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

				            			Collection arrecadadorEncontrado = 
				            				this.getFachada().pesquisar(filtroArrecadador, Arrecadador.class.getName());
				            			
				            			if (arrecadadorEncontrado != null && !arrecadadorEncontrado.isEmpty()) {
				            				nomeArrecadador = ((Arrecadador) ((List) arrecadadorEncontrado).get(0)).getCliente().getNome();
				            			}
				            			
				            			FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
				            			
				            			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
				            					FiltroArrecadadorContrato.ID,idArrecadadorContrato));
				            			filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
				        						FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
				            			
				            			filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
				            			Collection colecaoArrecadadorContrato = Fachada.getInstancia().pesquisar(
				            					filtroArrecadadorContrato,ArrecadadorContrato.class.getName());
				            			
				            			ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato)Util.retonarObjetoDeColecao(colecaoArrecadadorContrato);
				            			
				            			registroHelperCodigo0 = Fachada.getInstancia().validarArquivoMovimentoArrecadadorFichaCompensacao(codigoRegistro, linhaCompleta, 
				                    			codigoAgente, nomeArrecadador, idTipoMovimento, arrecadadorContrato, null, arrecadadorContrato.getArrecadador().getId());
				                    	
				                    	primeiraLinha = false;
				                    }
				                    
                                    stringBuilderTxt.append(linhaCompleta);
                                    stringBuilderTxt.append("\n");
                                    quantidadeRegistros = quantidadeRegistros + 1;
//                                } else {
//                                    break;
//                                }
                            } else {
                                break;
                            }

                        }

                        // fecha o arquivo
                        buffer.close();
                        reader.close();
                        item.getInputStream().close();
                        
                    }else {
						if(idTipoMovimento != null && idTipoMovimento.equals("DEBITO AUTOMATICO")){
							throw new ActionServletException("atencao.tipo_importacao.nao_deb");
						}else if(idTipoMovimento != null && idTipoMovimento.equals("CODIGO DE BARRAS")){
							throw new ActionServletException("atencao.tipo_importacao.nao_arr");
						}else if(idTipoMovimento != null && idTipoMovimento.equals("FICHA DE COMPENSACAO")){
                            throw new ActionServletException("atencao.tipo_importacao.nao_ficha");
                        }
                        
					}

				}
			}

			if (quantidadeRegistros != 0) {
				
				FiltroArrecadadorContrato filtroArrecadadorContrato = new FiltroArrecadadorContrato();
    			
    			filtroArrecadadorContrato.adicionarParametro(new ParametroSimples(
    					FiltroArrecadadorContrato.ID,idArrecadadorContrato));
    			filtroArrecadadorContrato.adicionarParametro(new ParametroNulo(
						FiltroArrecadadorContrato.DATA_CONTRATO_ENCERRAMENTO));
    			
    			filtroArrecadadorContrato.adicionarCaminhoParaCarregamentoEntidade("arrecadador");
    			Collection colecaoArrecadadorContrato = Fachada.getInstancia().pesquisar(
    					filtroArrecadadorContrato,ArrecadadorContrato.class.getName());
    			
    			ArrecadadorContrato arrecadadorContrato = (ArrecadadorContrato)Util.retonarObjetoDeColecao(colecaoArrecadadorContrato);
				
    			//Complemento do processo
    			String complementoProcesso = "";
    			
    			
    			
    			if(registroHelperCodigoA != null){
    				
    				FiltroBanco filtroBanco = new FiltroBanco();
    				filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, Integer.parseInt(registroHelperCodigoA.getCodigoBanco())));
    				Banco banco = (Banco)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroBanco,Banco.class.getName()));
    				
    				complementoProcesso = banco.getDescricaoAbreviada()
    									  +"/"+
    									  registroHelperCodigoA.getNumeroSequencialArquivo()
    									  +"/"+
    									  idTipoMovimento;
    			}
    			
    			else if(registroHelperCodigo0 != null){
    				FiltroBanco filtroBanco = new FiltroBanco();
    				filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID, Integer.parseInt(registroHelperCodigo0.getCodigoBancoCompensacao())));
    				Banco banco = (Banco)Util.retonarObjetoDeColecao(fachada.pesquisar(filtroBanco,Banco.class.getName()));
    				
    				complementoProcesso = banco.getDescricaoAbreviada()
    									  +"/"+
    									  registroHelperCodigo0.getNumeroSequencialArquivo()
    									  +"/"+
    									  idTipoMovimento;
    			}
    			
    			
    			Map parametros = new HashMap();
    			parametros.put("stringBuilderTxt", stringBuilderTxt);
    			parametros.put("codigoAgente", codigoAgente);
    			parametros.put("nomeArrecadador", nomeArrecadador);
    			parametros.put("idTipoMovimento", idTipoMovimento);
    			parametros.put("quantidadeRegistros", quantidadeRegistros);
    			parametros.put("idArrecadador", arrecadadorContrato.getArrecadador().getId());
    			parametros.put("arrecadadorContrato", arrecadadorContrato);
    			parametros.put("complementoProcesso", complementoProcesso);
    			
    			
    			fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
    	          		Processo.REGISTRAR_MOVIMENTO_ARRECADADORES, usuarioLogado);
    			
    			
				/*this.getFachada().registrarMovimentoArrecadadores(stringBuilderTxt,
					codigoAgente, nomeArrecadador,	idTipoMovimento,
					quantidadeRegistros, getUsuarioLogado(httpServletRequest), arrecadadorContrato.getArrecadador().getId(),
					arrecadadorContrato);*/
				
			} else {
				throw new ActionServletException("atencao.importacao.nao_concluida");
			}

		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");

		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}

		// montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Movimento Arrecadadores Enviado para Processamento", 
			"Voltar",
			"/exibirRegistrarMovimentoArredadadoresAction.do");

		return retorno;
	}
}
