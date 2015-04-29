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
package gsan.gui.util.tabelaauxiliar.faixa;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.tabelaauxiliar.faixa.TabelaAuxiliarFaixaReal;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author R�mulo Aur�lio
 * 
 */
public class AtualizarTabelaAuxiliarFaixaRealAction extends GcomAction {
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

		// Pega o action form
		TabelaAuxiliarFaixaRealActionForm form = (TabelaAuxiliarFaixaRealActionForm) actionForm;

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera o ponto de coleta da sess�o
		TabelaAuxiliarFaixaReal tabelaAuxiliarFaixaReal = (TabelaAuxiliarFaixaReal) sessao
				.getAttribute("tabelaAuxiliarFaixaReal");

		// Atualiza a tabela auxiliar com os dados inseridos pelo usu�rio
		// A data de �ltima altera��o n�o � alterada, pois ser� usada na
		// verifica��o de atualiza��o

		BigDecimal volumeMaiorFaixa = null;
		
		if (!form.getVolumeMaiorFaixa().equalsIgnoreCase("")) {
			
			volumeMaiorFaixa = new BigDecimal(0);

			String valorAux = form.getVolumeMaiorFaixa().toString().replace(
					".", "");

			valorAux = valorAux.replace(",", ".");

			volumeMaiorFaixa = new BigDecimal(valorAux);

			tabelaAuxiliarFaixaReal.setVolumeMaiorFaixa(volumeMaiorFaixa);
		}
		if (!form.getVolumeMenorFaixa().equalsIgnoreCase("")) {
			BigDecimal volumeMenorFaixa = new BigDecimal(0);

			String valorAux1 = form.getVolumeMenorFaixa().toString().replace(
					".", "");

			valorAux1 = valorAux1.replace(",", ".");

			volumeMenorFaixa = new BigDecimal(valorAux1);

			tabelaAuxiliarFaixaReal.setVolumeMenorFaixa(volumeMenorFaixa);
			
			
			if (volumeMaiorFaixa != null					
					&& volumeMenorFaixa != null) {
				if (volumeMenorFaixa.compareTo(volumeMaiorFaixa) > 0) {
					throw new ActionServletException("atencao.volume_menor_faixa_superior_maior_faixa");
				}
			}
			
			
		}
		if (form.getIndicadorUso() != null) {
			tabelaAuxiliarFaixaReal.setIndicadorUso(new Short(form
					.getIndicadorUso()));
		}

		// Atualiza os dados
		fachada.atualizarTabelaAuxiliar(tabelaAuxiliarFaixaReal);

		// Monta a p�gina de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo")) + " "
							+ tabelaAuxiliarFaixaReal.getId().toString()
							+ " atualizado(a) com sucesso.",
					"Realizar outra manuten��o de "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarFaixaRealFiltrar")));

		}

		// Remove os objetos da sess�o
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarFaixaRealManter");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarFaixaReal");
		sessao.removeAttribute("tamMaxCampoDescricao");
		sessao.removeAttribute("tamMaxCampoDescricaoAbreviada");

		// devolve o mapeamento de retorno
		return retorno;
	}

}
