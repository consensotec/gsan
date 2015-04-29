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
package gsan.gui.micromedicao;

import gsan.cadastro.localidade.FiltroQuadra;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.FiltroRota;
import gsan.micromedicao.Rota;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que define o pr�-processamento da p�gina de pesquisa de Rota
 * 
 * @author Rafael Santos
 * @since 23/08/2006
 */
public class ExibirPesquisarRotaAction extends GcomAction {

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

		ActionForward retorno = actionMapping.findForward("pesquisarRota");
        
        DynaValidatorActionForm form = (DynaValidatorActionForm) actionForm;

		
		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		//DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		String codigoSetorComercial = null;
		String idLocalidade = null;

		if (httpServletRequest.getParameter("destino") != null
				&& !httpServletRequest.getParameter("destino").trim()
						.equalsIgnoreCase("")) {

			sessao.setAttribute("destino",(String) httpServletRequest
					.getParameter("destino"));
		}

		
		if (httpServletRequest.getParameter("codigoSetorComercial") != null
				&& !httpServletRequest.getParameter("codigoSetorComercial").trim()
						.equalsIgnoreCase("")) {

			codigoSetorComercial = (String) httpServletRequest
					.getParameter("codigoSetorComercial");
            
            form.set( "codigoSetorComercial", codigoSetorComercial );
		}

		if (httpServletRequest.getParameter("idLocalidade") != null
				&& !httpServletRequest.getParameter("idLocalidade").trim()
						.equalsIgnoreCase("")) {

			idLocalidade = (String) httpServletRequest
					.getParameter("idLocalidade");
            
            form.set( "idLocalidade", idLocalidade );
        }
        
        codigoSetorComercial = ( codigoSetorComercial == null ? (String) form.get( "codigoSetorComercial" ) : codigoSetorComercial );
        idLocalidade = ( idLocalidade == null ? (String) form.get( "idLocalidade" ) : idLocalidade );

		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");

        /** 
         * carregamento do filtro comentado por pedro alexandre dia :22/01/2007
         */
		/*filtroRota
				.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL_ID);*/
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.LOCALIDADE_ID, idLocalidade));
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));

		filtroRota.setCampoOrderBy(FiltroRota.CODIGO_ROTA);

		if (httpServletRequest.getParameter("indicadorUso") != null
				&& !httpServletRequest.getParameter("indicadorUso").trim()
						.equalsIgnoreCase("")) {
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
		}
		
		if(httpServletRequest.getParameter("indicadorRotaAlternativa") != null && !httpServletRequest.getParameter("indicadorRotaAlternativa").trim().equalsIgnoreCase("") ){
		  filtroRota.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADOR_ROTA_ALTERNATIVA,ConstantesSistema.NAO));		  
		}

		Collection rotas = null;
		// Aciona o controle de pagina��o para que sejam pesquisados apenas
		// os registros que aparecem na p�gina
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroRota, Rota.class.getName());
		rotas = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");	

		if (rotas == null || rotas.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "rota");
		}

		sessao.setAttribute("rotas", rotas);
		
		return retorno;
	}

}
