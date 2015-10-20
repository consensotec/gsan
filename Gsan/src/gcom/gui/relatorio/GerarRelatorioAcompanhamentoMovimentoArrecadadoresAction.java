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
package gcom.gui.relatorio;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action respons�vel pela exibi��o do relat�rio de bairro manter
 * 
 * @author S�vio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioAcompanhamentoMovimentoArrecadadoresAction extends ExibidorProcessamentoTarefaRelatorio {

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
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AcompanharMovimentoArrecadadoresActionForm acompanharMovimentoArrecadadoresActionForm = (AcompanharMovimentoArrecadadoresActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Inicio da parte que vai mandar os parametros para o relat�rio
		String mesAnoReferencia = acompanharMovimentoArrecadadoresActionForm.getMesAnoReferencia();

		String idArrecadador = acompanharMovimentoArrecadadoresActionForm.getIdArrecadador();

		Arrecadador arrecadador = null;

		if (idArrecadador != null && !idArrecadador.equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.ID, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection colecaoArrecadadores = fachada.pesquisar(filtroArrecadador,
					Arrecadador.class.getName());

			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				// O arrecadador foi encontrado
				arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadadores);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Arrecadador");
			}

		} else {
			arrecadador = new Arrecadador();
		}

		String idArrecadacaoForma = acompanharMovimentoArrecadadoresActionForm.getIdFormaArrecadacao();
		
		ArrecadacaoForma arrecadacaoForma = null;

		if (idArrecadacaoForma != null
				&& !idArrecadacaoForma.equals("")
				&& !idArrecadacaoForma.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
					FiltroArrecadacaoForma.CODIGO, idArrecadacaoForma));

			Collection colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,
					ArrecadacaoForma.class.getName());

			if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()) {
				// O arrecadador foi encontrado
				arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Forma de Arrecada��o");
			}

		} else {
			arrecadacaoForma = new ArrecadacaoForma();
		}

		fachada.gerarResumoAcompanhamentoMovimentoArrecadadores((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), mesAnoReferencia, arrecadador, arrecadacaoForma);
		
		// Fim da parte que vai mandar os parametros para o relat�rio
//		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
//		RelatorioAcompanhamentoMovimentoArrecadadores relatorioAcompanhamentoMovimentoArrecadadores = new RelatorioAcompanhamentoMovimentoArrecadadores((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
//		relatorioAcompanhamentoMovimentoArrecadadores.addParametro("mesAnoReferencia", mesAnoReferencia);
//		relatorioAcompanhamentoMovimentoArrecadadores.addParametro("arrecadador", arrecadador);
//		relatorioAcompanhamentoMovimentoArrecadadores.addParametro("arrecadacaoForma", arrecadacaoForma);
//		
//		if (tipoRelatorio  == null) {
//			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
//		}
//
//		relatorioAcompanhamentoMovimentoArrecadadores.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
//		
//		retorno = processarExibicaoRelatorio(relatorioAcompanhamentoMovimentoArrecadadores, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		
		// montando p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Movimento Arrecadadores Enviado para Processamento", "Voltar",
				"/exibirAcompanharMovimentoArrecadadoresAction.do");

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
