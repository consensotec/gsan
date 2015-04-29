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
package gsan.gui.atendimentopublico.registroatendimento;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 21/08/2006
 */
public class ConsultarProgramacaoAbastecimentoManutencaoAction extends
		GcomAction {
	/**
	 * Este caso de uso permite a programa�ao de abastecimento e manutencao de
	 * uma determinada �rea de bairro
	 * 
	 * [UC0440] Consultar Programa��o de Abastecimento e Manuten��o
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 21/08/2006
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
				.findForward("exibirResultadoConsultarProgramacaoAbastecimentoManutencaoAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarProgramacaoAbastecimentoManutencaoActionForm consultarProgramacaoAbastecimentoManutencaoActionForm = (ConsultarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		String idMunicipio = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getMunicipio();

		sessao.setAttribute("idMunicipio", idMunicipio);

		String idBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getBairro();

		sessao.setAttribute("idBairro", idBairro);

		String areaBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getAreaBairro();

		sessao.setAttribute("areaBairro", areaBairro);

		String mesAnoReferencia = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getMesAnoReferencia();

		Collection colecaoProgramacaoAbastecimento = fachada
				.consultarProgramacaoAbastecimento(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		// Seleciona todas as programa��es de abastecimento de acordo da �rea de
		// bairro e o m�s e o ano de referencia informado. Ordenando de forma
		// crescente por Data de Inicio de Abastecimento e Hora de Inicio de
		// abastecimento
		Collection colecaoProgramacaoManutencao = fachada
				.consultarProgramacaoManutencao(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		if ((colecaoProgramacaoAbastecimento == null || colecaoProgramacaoAbastecimento
				.isEmpty())
				&& ((colecaoProgramacaoManutencao == null || colecaoProgramacaoManutencao
						.isEmpty()))) {
			throw new ActionServletException(
					"atencao.abastecimento_manutencao_sem_registro");

		}

		// retorna ano e mes

		String ano = null;

		String mes = null;

		if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			sessao.setAttribute("ano", ano);
			sessao.setAttribute("mes", mes);

		} else {

			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);
			sessao.setAttribute("ano", ano);
			sessao.setAttribute("mes", mes);
		}

		return retorno;
	}
}
