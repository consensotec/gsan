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
package gsan.gui.batch.relatorio;

import gsan.batch.RelatorioGerado;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.relatorio.FiltroRelatorioGerado;
import gsan.relatorio.RelatorioVazioException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.IoUtil;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe resps�vel por montar a apresenta��o dos relat�rios armazenados em
 * batch
 * 
 * 
 * @author Rodrigo Silveira
 * @date 26/10/2006
 */
public class ExibirRelatorioBatchAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		int idFuncionalidadeIniciada = converterStringToInt(httpServletRequest
				.getParameter("idFuncionalidadeIniciada"));

		FiltroRelatorioGerado filtroRelatorioGerado = new FiltroRelatorioGerado();
		filtroRelatorioGerado.adicionarParametro(new ParametroSimples(
				FiltroRelatorioGerado.FUNCIONALIDADE_INICIADA_ID,
				idFuncionalidadeIniciada));
		filtroRelatorioGerado
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeIniciada");
		
		
		RelatorioGerado relatorioGerado = (RelatorioGerado) Util
				.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(
						filtroRelatorioGerado, RelatorioGerado.class.getName()));
		
		if(relatorioGerado == null){
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		OutputStream out = null;
		try {

			TarefaRelatorio tarefaRelatorio = (TarefaRelatorio) IoUtil
					.transformarBytesParaObjeto(relatorioGerado
							.getFuncionalidadeIniciada().getTarefaBatch());

			// httpServletResponse.addHeader("Content-Disposition","attachment;
			// filename=relatorio");

			String mimeType = null;
			switch ((Integer) tarefaRelatorio
					.getParametro("tipoFormatoRelatorio")) {
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
			}

			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			// out.write((byte[])
			// Util.retonarObjetoDeColecao(relatorioRetorno.values()));
			out.write(relatorioGerado.getArquivoRelatorio());
			out.flush();
			out.close();
		} catch (IOException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
		} catch (ClassNotFoundException ex) {
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
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}