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
package gsan.gui.relatorio.micromedicao;

import gsan.cadastro.localidade.Localidade;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.micromedicao.GerarDadosLeituraActionForm;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.micromedicao.GerarDadosLeituraHelper;
import gsan.relatorio.micromedicao.RelatorioGerarDadosLeitura;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;

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
public class GerarRelatorioDadosLeituraAction extends
		ExibidorProcessamentoTarefaRelatorio {

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
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		GerarDadosLeituraActionForm form = (GerarDadosLeituraActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		GerarDadosLeituraHelper gerarDadosLeituraHelper = new GerarDadosLeituraHelper();
		
		if (form.getIdGrupoFaturamento() != null && !form.getIdGrupoFaturamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			gerarDadosLeituraHelper.setIdGrupoFaturamento(new Integer(form.getIdGrupoFaturamento()));
		}
		
		if (form.getIdRota() != null && !form.getIdRota().equals("")) {
			gerarDadosLeituraHelper.setIdRota(new Integer(form.getIdRota()));
			gerarDadosLeituraHelper.setCodigoRota(new Integer(form.getCodigoRota()));
		}
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeInicial()));
		
			if (localidade != null) {
				gerarDadosLeituraHelper.setIdLocalidadeInicial(localidade.getId());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade Inicial");
			}
			
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().trim().equals("")) {
			
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(form.getIdLocalidadeFinal()));
			
			if (localidade != null) {
				gerarDadosLeituraHelper.setIdLocalidadeFinal(localidade.getId());
			} else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade Final");
			}
			
		}
		
		gerarDadosLeituraHelper.setIndicadorOrdenacao(new Short(form.getIndicadorOrdenacao()));
		
		// Fim da parte que vai mandar os parametros para o relat�rio
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioGerarDadosLeitura relatorioGerarDadosLeitura = new RelatorioGerarDadosLeitura((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioGerarDadosLeitura.addParametro("gerarDadosLeituraHelper",
				gerarDadosLeituraHelper);
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioGerarDadosLeitura.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioGerarDadosLeitura, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}
