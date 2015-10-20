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
package gcom.relatorio;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.SistemaException;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe resps�vel por montar a apresenta��o do Relatorio Processado
 * 
 * 
 * @author Thiago Toscano
 * @date 25/05/2006
 */
public class ExibidorProcessamentoTarefaRelatorio extends GcomAction {

	public ActionForward processarExibicaoRelatorio(
			TarefaRelatorio tarefaRelatorio, int tipoRelatorio,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ActionMapping actionMapping) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		String valorConfirmacao = httpServletRequest.getParameter("confirmado");
		
		if (valorConfirmacao == null || valorConfirmacao.equals("")) {
			int quantidadeRegistroGerado = tarefaRelatorio.calcularTotalRegistrosRelatorio();

			String nomeClasseRelatorio = tarefaRelatorio.getClass().getSimpleName();

			int quantidadeMaximaOnLineRelatorio = ConstantesExecucaoRelatorios.get(nomeClasseRelatorio);

			// se a quantidade a ser processada for maior que a permitida
			if (quantidadeMaximaOnLineRelatorio == ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA
					|| quantidadeRegistroGerado > quantidadeMaximaOnLineRelatorio) {
				
				httpServletRequest
					.setAttribute("caminhoActionConclusao", httpServletRequest.getContextPath().toString() + httpServletRequest.getServletPath().toString());
				
				httpServletRequest.setAttribute("tipoRelatorio", ""+tipoRelatorio);
				
				
				//Fazer l�gica de controle
				return montarPaginaConfirmacao("atencao.numero.registro.excedeu.limite.online", httpServletRequest, actionMapping);
				
			}else if(httpServletRequest.getAttribute("telaSucessoRelatorio") != null 
					&& quantidadeRegistroGerado <= quantidadeMaximaOnLineRelatorio){
				
				 sessao.setAttribute("tipoRelatorio", ""+tipoRelatorio);
				 
					// manda o erro para a p�gina no request atual
					reportarErros(httpServletRequest, "erro.sistema");

					// seta o mapeamento de retorno para a tela de erro de popup
					retorno = actionMapping.findForward("telaErroPopup");

				 RelatorioProcessado relatorioProcessado = null;	
					
				 try {
					 relatorioProcessado = GerenciadorExecucaoTarefaRelatorio.analisarExecucao(tarefaRelatorio, tipoRelatorio);
				 } catch (ControladorException ex) {
					 ActionServletException exception = new ActionServletException(ex.getMessage());
					 exception.setUrlBotaoVoltar("telaPrincipal.do");
					 throw exception;
				 }
				 
				 
				 sessao.setAttribute("relatorioProcessado", relatorioProcessado);
				 
				 httpServletRequest.setAttribute("telaSucessoRelatorio", true);
				 
				 montarPaginaSucesso(httpServletRequest, "Relat�rio Gerado com Sucesso.", "", "", "", "");
				 
				 retorno = actionMapping.findForward("telaSucesso");
			}		
			else {
				retorno = processarRelatorio(tarefaRelatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping, retorno);
			}
			
		} else {
			retorno = processarRelatorio(tarefaRelatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping, retorno);
		}

		return retorno;
		
		
	}

	protected ActionForward processarRelatorio(TarefaRelatorio tarefaRelatorio, int tipoRelatorio, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ActionMapping actionMapping, ActionForward retorno) {
		
		try {
			RelatorioProcessado relatorioProcessado = GerenciadorExecucaoTarefaRelatorio
					.analisarExecucao(tarefaRelatorio, tipoRelatorio);

			if (relatorioProcessado == null) {

				retorno = actionMapping.findForward("telaApresentacaoBatch");

			} else {
				OutputStream out = null;

				// httpServletResponse.addHeader("Content-Disposition","attachment;
				// filename=relatorio");

				String mimeType = null;
				switch (tipoRelatorio) {
				case TarefaRelatorio.TIPO_PDF:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.pdf");
					mimeType = "application/pdf";
					break;

				case TarefaRelatorio.TIPO_RTF:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.rtf");

					mimeType = "application/rtf";
					break;
				case TarefaRelatorio.TIPO_XLS:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.xls");

					mimeType = "application/vnd.ms-excel";
					break;
				case TarefaRelatorio.TIPO_HTML:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.zip");

					mimeType = "application/zip";
					break;
				case TarefaRelatorio.TIPO_TXT:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.txt");

					mimeType = "text/plain";
					break;
				case TarefaRelatorio.TIPO_CSV:
					httpServletResponse.addHeader("Content-Disposition",
							"attachment; filename=relatorio.csv");

					mimeType = "application/vnd.ms-excel";
					break;
				}

				httpServletResponse.setContentType(mimeType);
				out = httpServletResponse.getOutputStream();
				// out.write((byte[])
				// Util.retonarObjetoDeColecao(relatorioRetorno.values()));
				out.write(relatorioProcessado.getDados());
				out.flush();
				out.close();

			}
			
			
		} catch (ControladorException ex) {
			 ActionServletException exception = new ActionServletException(ex.getMessage());
			 exception.setUrlBotaoVoltar("telaPrincipal.do");
			 throw exception;
			
		} catch (IOException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

	public ActionForward processarExibicaoRelatorio(TarefaRelatorio tarefaRelatorio, String tipoRelatorio,
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, ActionMapping actionMapping) {

		int tipoIntRelatorio = TarefaRelatorio.TIPO_PDF;
		
		try {
			tipoIntRelatorio = Integer.parseInt(tipoRelatorio);

			switch (tipoIntRelatorio) {
			case TarefaRelatorio.TIPO_HTML:
				break;
			case TarefaRelatorio.TIPO_RTF:
				break;
			case TarefaRelatorio.TIPO_XLS:
				break;
			case TarefaRelatorio.TIPO_PDF:
				break;
			case TarefaRelatorio.TIPO_TXT:
				break;
			case TarefaRelatorio.TIPO_CSV:
				break;
			default:
				tipoIntRelatorio = TarefaRelatorio.TIPO_PDF;
				break;
			}

		} catch (Exception e) {
		}

		return processarExibicaoRelatorio(tarefaRelatorio, tipoIntRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
	}
}