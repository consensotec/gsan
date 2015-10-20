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
package gcom.gui.relatorio.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.geografico.RelatorioManterBairro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioBairroManterAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroBairro filtroBairro = (FiltroBairro) sessao.getAttribute("filtroBairro");



		// Inicio da parte que vai mandar os parametros para o relat�rio

		Bairro bairroParametros = new Bairro();

		String idMunicipio = (String) pesquisarActionForm.get("idMunicipio");

		Municipio municipio = null;

		if (idMunicipio != null && !idMunicipio.equals("")) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipios != null && !municipios.isEmpty()) {
				// O municipio foi encontrado
				Iterator municipioIterator = municipios.iterator();

				municipio = (Municipio) municipioIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Munic�pio");
			}

		} else {
			municipio = new Municipio();
		}

		int codigoBairro = 0;

		String codigoBairroPesquisar = (String) pesquisarActionForm
				.get("codigoBairro");

		if (codigoBairroPesquisar != null && !codigoBairroPesquisar.equals("")) {
			codigoBairro = Integer.parseInt(codigoBairroPesquisar);
		}

		Short indicadorDeUso = null;

		if (pesquisarActionForm.get("indicadorUso") != null
				&& !pesquisarActionForm.get("indicadorUso").equals("")) {

			indicadorDeUso = new Short(""
					+ pesquisarActionForm.get("indicadorUso"));
		}

		// seta os parametros que ser�o mostrados no relat�rio
		bairroParametros.setMunicipio(municipio);
		bairroParametros.setCodigo(codigoBairro);
		bairroParametros.setNome("" + pesquisarActionForm.get("nomeBairro"));
		bairroParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relat�rio
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioManterBairro relatorio = new RelatorioManterBairro((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorio.addParametro("filtroBairro", filtroBairro);
		relatorio.addParametro("bairroParametros",bairroParametros);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

//		
//
//		Map<Integer, byte[]> relatorioRetorno = null;
//
//		OutputStream out = null;
//
//		try {
//
//			// cria uma inst�ncia da classe do relat�rio
//
//			// chama o met�do de gerar relat�rio passando o c�digo da analise
//			// como par�metro
//			relatorioRetorno = (Map<Integer, byte[]>) relatorioManterBairro.executar();
//
//			if (retorno == null) {
//
//				// httpServletResponse.addHeader("Content-Disposition",
//				// "attachment; filename=relatorio");
//
//				// prepara a resposta para o popup
//				String mimeType = null;
//				switch ((Integer) Util.retonarObjetoDeColecao(relatorioRetorno
//						.keySet())) {
//				case TarefaRelatorio.TIPO_PDF:
//					httpServletResponse.addHeader("Content-Disposition",
//							"attachment; filename=relatorio.pdf");
//
//					mimeType = "application/pdf";
//					break;
//
//				case TarefaRelatorio.TIPO_RTF:
//					httpServletResponse.addHeader("Content-Disposition",
//							"attachment; filename=relatorio.rtf");
//
//					mimeType = "application/rtf";
//					break;
//				case TarefaRelatorio.TIPO_XLS:
//					httpServletResponse.addHeader("Content-Disposition",
//							"attachment; filename=relatorio.xls");
//
//					mimeType = "application/vnd.ms-excel";
//					break;
//				case TarefaRelatorio.TIPO_HTML:
//					httpServletResponse.addHeader("Content-Disposition",
//							"attachment; filename=relatorio.zip");
//
//					mimeType = "application/zip";
//					break;
//
//				}
//
//				httpServletResponse.setContentType(mimeType);
//				out = httpServletResponse.getOutputStream();
//				out.write((byte[]) Util.retonarObjetoDeColecao(relatorioRetorno
//						.values()));
//				out.flush();
//				out.close();
//
//			}
//		} catch (IOException ex) {
//			// manda o erro para a p�gina no request atual
//			reportarErros(httpServletRequest, "erro.sistema");
//
//			// seta o mapeamento de retorno para a tela de erro de popup
//			retorno = actionMapping.findForward("telaErroPopup");
//
//		} catch (SistemaException ex) {
//			// manda o erro para a p�gina no request atual
//			reportarErros(httpServletRequest, "erro.sistema");
//
//			// seta o mapeamento de retorno para a tela de erro de popup
//			retorno = actionMapping.findForward("telaErroPopup");
//
//		} catch (RelatorioVazioException ex1) {
//			// manda o erro para a p�gina no request atual
//			reportarErros(httpServletRequest, "erro.relatorio.vazio");
//
//			// seta o mapeamento de retorno para a tela de aten��o de popup
//			retorno = actionMapping.findForward("telaAtencaoPopup");
//		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
