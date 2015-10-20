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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
 * Action respons�vel para dar um onLoad no arquivo e importar o arquivo.
 * 
 * @author Yara Taciane
 * @created 09/01/2008
 */
public class RegistrarNegativadorMovimentoRetornoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/*
		 * Passos do Processo
		 * 
		 * 1 - Chama m�todo para Validar Arquivo .
		 * 2 - Retorna Tela de Informa��es do Arquivo para o usu�rio. 
		 * 3 - Insere Processo Batch.
		 * 4 - Chama Registrar Movimento Retorno
		 * 5 - Busca o Arquivo na pasta bin do servidor. 
		 * 6 - Chama Registrar Movimento Retorno e SubFluxos
		 * 
		 */

		ActionForward retorno = actionMapping
				.findForward("registrarNegativadorMovimentoRetornoResumo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		Negativador negativador = null;
		String numeroSequencialArquivo = "";
		int countRegistro = 0;

		try {

			String nomeItem = "";
			String idNegativador = null;
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
				if (item.getFieldName().equals("idNegativador")) {
					idNegativador = item.getString();
					
					FiltroNegativador fn = new FiltroNegativador();
					fn.adicionarParametro(new ParametroSimples(FiltroNegativador.ID,
							idNegativador));
					fn.adicionarCaminhoParaCarregamentoEntidade("cliente");

					Collection collNegativador = fachada.pesquisar(fn,
							Negativador.class.getName());
					// Recupera da cole��o o Negativador que vai ser atualizado
					negativador = (Negativador) Util
							.retonarObjetoDeColecao(collNegativador);
				}
				// verifica se n�o � diretorio
				if (!item.isFormField()) {
					// coloca o nome do item para maiusculo
					//nomeItem = item.getName().toUpperCase();					
					//---------------------------------------------------------------
					
					Date data = new Date();
					String AAAAMMDD = Util.formatarDataAAAAMMDD(data);
					//String HHMM = Util.formatarDataHHMM(data);
					String formatodatahora = AAAAMMDD;
					nomeItem = "REG_SPC_SERASA" + formatodatahora;	
					
					
					//---------------------------------------------------------------				

					if (Util.verificarNaoVazio(idNegativador)) {
						// abre, l� e escreve o arquivo
						InputStreamReader reader = new InputStreamReader(item
								.getInputStream());
						BufferedReader buffer = new BufferedReader(reader);

						File file = new File(nomeItem);
						FileOutputStream fout = new FileOutputStream(file);
						PrintWriter pw = new PrintWriter(fout);

						// cria uma variavel do tipo boolean
						boolean eof = false;
						// enquanto a variavel for false
						while (!eof) {
							// pega a linha do arquivo
							String linhaLida = buffer.readLine();

							if (linhaLida != null) {
								pw.println(linhaLida);
							}
							
							//FS0011 - Verificar erro de Header/Trailler no arquivo
							if(negativador.getId().equals(Negativador.NEGATIVADOR_SPC)){
								//Verifica se a linha � do header ou trailler
								if ( linhaLida != null && linhaLida.length() >= 334 && 
										( linhaLida.substring(0,2).equals("00") || linhaLida.substring(0,2).equals("99")) ) {
									
									String verificaCodigoDeRetorno = linhaLida.substring(324, 334);
									if ( verificaCodigoDeRetorno != null && !verificaCodigoDeRetorno.equals("0000000000") ) {
									
										throw new ActionServletException("atencao.headerOuTraillerComProblema");
									}
								}
							}else if (negativador.getId().equals(Negativador.NEGATIVADOR_SERASA)) {
								//Verifica se a linha � do header ou trailler
								if ( linhaLida != null && linhaLida.length() >= 593 && 
										(linhaLida.substring(0,1).equals("0") || linhaLida.substring(0,1).equals("9")) ) {
									
									String verificaCodigoDeRetorno = linhaLida.substring(533, 593);
									if ( verificaCodigoDeRetorno != null && !verificaCodigoDeRetorno.trim().equals("") ) {
									
										throw new ActionServletException("atencao.headerOuTraillerComProblema");
									}
								}
							}
							
							
							
							// conta a quantidade de linhas do arquivo
							if (linhaLida != null && linhaLida.length() > 0) {
								stringBuilderTxt.append(linhaLida);
								stringBuilderTxt.append("\n");
								quantidadeRegistros = quantidadeRegistros + 1;
							} else {
								break;
							}
						}
						// fecha o arquivo
						buffer.close();
						reader.close();
						item.getInputStream().close();
						pw.flush();
						pw.close();

					}else{
						throw new ActionServletException("atencao.negativador.nao.selecionado");
					}
				}
			}

			if ("".equals(nomeItem)) {
				throw new ActionServletException(
						"atencao.arquivo_negativador_movimento_retorno_inexistente");
			}


			if (quantidadeRegistros != 0) {

				// 1 - Chama m�todo para Validar Arquivo .
				Object[] retornoValidadacao = fachada
						.validarArquivoMovimentoRetorno(stringBuilderTxt,
								negativador);

				Collection collRegistrosLidos = (Collection) retornoValidadacao[0];
				NegativadorMovimento negativadorMovimento = (NegativadorMovimento) retornoValidadacao[1];

				System.out.println("negativadorMovimento"
						+ negativadorMovimento.getId());

				Iterator it = collRegistrosLidos.iterator();
				while (it.hasNext()) {
					String registro = (String) it.next();
					countRegistro = countRegistro + 1;
					if (countRegistro == 1) {
						if (Negativador.NEGATIVADOR_SPC.intValue() == negativador
								.getId().intValue()) {
							// H.04
							numeroSequencialArquivo = registro
									.substring(18, 25);
						} else if (Negativador.NEGATIVADOR_SERASA.intValue() == negativador
								.getId().intValue()) {
							// H.09
							numeroSequencialArquivo = registro.substring(120,
									125);
						}
					}
				}
			} else {
				throw new ActionServletException("atencao.arquivo_sem_dados",
						null, nomeItem);
			}
		} catch (IOException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (NumberFormatException ex) {
			throw new ActionServletException("erro.importacao.nao_concluida");
		} catch (FileUploadException e) {
			throw new ActionServletException("erro.sistema", e);
		}

		// 2 - Retorna Tela de Informa��es do Arquivo para o usu�rio.
		sessao.setAttribute("nomeNegativador", negativador.getCliente()
				.getNome());
		sessao.setAttribute("dataProcessamento", Util.formatarData(new Date()));
		sessao.setAttribute("horaProcessamento", Util
				.formatarHoraSemData(new Date()));
		sessao.setAttribute("numeroSequencialArquivo", numeroSequencialArquivo);
		sessao.setAttribute("numeroRegistros", countRegistro);

//		// 3 - Insere Processo Batch.
		Usuario usuario = this.getUsuarioLogado(httpServletRequest);
		
		fachada.inserirProcessoRegistrarNegativadorMovimentoRetorno(usuario);
		
	//	Collection coll = fachada.registrarNegativadorMovimentoRetorno(0,null);
		

		return retorno;
	}

}
