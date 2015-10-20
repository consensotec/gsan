/**
 * 
 */
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
package gcom.gui.faturamento.debito;

import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.FiltroDebitoTipoVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;
import java.math.BigDecimal;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Josenildo Neves	
 * @date 22/02/2010
 */
public class FiltrarDebitoTipoVigenciaAction extends GcomAction {

	/**
	 * [UC0984] Filtrar tipo de d�bito vig�ncia
	 * 
	 * Este caso de uso cria um filtro que ser� usado na pesquisa do Debito Tipo Vigencia
	 * 
	 * @author Josenildo Neves - Hugo Leonardo
	 * @date 22/02/2010 - 20/04/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterDebitoTipoVigenciaAction");

		FiltrarDebitoTipoVigenciaActionForm filtrarDebitoTipoVigenciaActionForm = (FiltrarDebitoTipoVigenciaActionForm) actionForm;

		FiltroDebitoTipoVigencia filtroDebitoTipoVigencia = new FiltroDebitoTipoVigencia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;
		
		String debitoTipo = filtrarDebitoTipoVigenciaActionForm
				.getDebitoTipo();

		String valorDebitoInicial = filtrarDebitoTipoVigenciaActionForm.getValorDebitoInicial();

		String valorDebitoFinal = filtrarDebitoTipoVigenciaActionForm.getValorDebitoFinal();

		// Verifica se o campo debitoTipo foi informado

		if (debitoTipo != null && !debitoTipo.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroDebitoTipoVigencia.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipoVigencia.DEBITO_TIPO_ID, debitoTipo));
			
			filtroDebitoTipoVigencia.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");

		}

		// Verifica se o campo valorDebitoInicial foi informado

		if (valorDebitoInicial != null && !valorDebitoInicial.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
		}

		// Verifica se o campo valorDebitoFinal foi informado

		if (valorDebitoFinal != null && !valorDebitoFinal.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
		} 

		if(valorDebitoInicial != null && !valorDebitoInicial.trim().equalsIgnoreCase("")
					&& valorDebitoFinal != null && !valorDebitoFinal.trim().equalsIgnoreCase("")){
			
			BigDecimal valorInicial = Util.formatarMoedaRealparaBigDecimal(valorDebitoInicial);
	
			BigDecimal valorFinal = Util.formatarMoedaRealparaBigDecimal(valorDebitoFinal);
	
			Integer resultado = valorInicial.compareTo(valorFinal);
	
			if (resultado == 1) {
				throw new ActionServletException(
						"atencao.valor_debito_final_menor_valor_debito_inicial");
			}
		
			if( valorInicial != null && valorFinal != null ) {
				filtroDebitoTipoVigencia.adicionarParametro(new Intervalo(
						FiltroDebitoTipoVigencia.VALOR, valorInicial, valorFinal));
			}
		
		}

		filtroDebitoTipoVigencia.setCampoOrderBy(FiltroDebitoTipoVigencia.DEBITO_TIPO_ID);

		Collection <DebitoTipoVigencia> colecaoDebitoTipoVigencia = fachada.pesquisar(filtroDebitoTipoVigencia, DebitoTipoVigencia.class.getName());		
		
		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar est� marcado e em caso afirmativo
		// manda pelo um request uma vari�vel para o
		// ExibirManterDebitoTipoVigenciaAction para nele verificar se ir�
		// para o
		// atualizar ou para o manter
		if (filtrarDebitoTipoVigenciaActionForm.getAtualizar() != null
				&& filtrarDebitoTipoVigenciaActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarDebitoTipoVigenciaActionForm.getAtualizar());

		}

		// Pesquisa sem registros
		if (colecaoDebitoTipoVigencia == null || colecaoDebitoTipoVigencia.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Debito Tipo Vigencia");
		} else {
			DebitoTipoVigencia debitoTipoVigencia = new DebitoTipoVigencia();
			debitoTipoVigencia = (DebitoTipoVigencia) Util.retonarObjetoDeColecao(colecaoDebitoTipoVigencia);
			String idRegistroAtualizar = debitoTipoVigencia.getId().toString();

			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
			httpServletRequest.setAttribute("colecaoDebitoTipoVigencia", colecaoDebitoTipoVigencia);
		
		}
		
		sessao.setAttribute("filtroDebitoTipoVigencia", filtroDebitoTipoVigencia);

		httpServletRequest.setAttribute("filtroDebitoTipoVigencia", filtroDebitoTipoVigencia);

		return retorno;

	}
}
