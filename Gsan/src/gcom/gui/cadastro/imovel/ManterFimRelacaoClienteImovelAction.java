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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 15 de Agosto de 2005
 */
public class ManterFimRelacaoClienteImovelAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da A��o
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarEconomiaPopup");
		// Cria a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		FimRelacaoClienteImovelActionForm fimRelacaoClienteImovelActionForm = (FimRelacaoClienteImovelActionForm) actionForm;

		// Obt�m a fachada
		//Fachada fachada = Fachada.getInstancia();

		Collection colecaoClientesImoveisEconomiaFimRelacao = (Collection) sessao
				.getAttribute("colecaoClientesImoveisEconomiaFimRelacao");
		// verifica se tem algum cliente imovel economia que precisa ser
		// atualizado com a data de termino da
		// rela��o e o motivo.
		if (colecaoClientesImoveisEconomiaFimRelacao != null
				&& !colecaoClientesImoveisEconomiaFimRelacao.isEmpty()) {
			Iterator clienteImovelEconomiaIterator = colecaoClientesImoveisEconomiaFimRelacao
					.iterator();
			String idMotivoFimRelacao = fimRelacaoClienteImovelActionForm
					.getIdMotivo();

			ClienteImovelFimRelacaoMotivo clienteImovelFimRelacao = new ClienteImovelFimRelacaoMotivo();
			// seta o id do motivo do fim da rela��o
			clienteImovelFimRelacao.setId(new Integer(idMotivoFimRelacao));

			String dataFimRelacaoForm = fimRelacaoClienteImovelActionForm
					.getDataTerminoRelacao();

			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

			Date dataFimRelacao = null;
			try {
				dataFimRelacao = dataFormato.parse(dataFimRelacaoForm);
			} catch (ParseException ex) {
				dataFimRelacao = null;
			}

			Date dataCorrente = null;
			Calendar a = Calendar.getInstance();
			a.set(Calendar.SECOND, 0);
			a.set(Calendar.MILLISECOND, 0);
			a.set(Calendar.HOUR, 0);
			a.set(Calendar.MINUTE, 0);
			dataCorrente = a.getTime();

			if (dataFimRelacao.after(dataCorrente)) {
				throw new ActionServletException(
						"atencao.data_fim_relacao_cliente_imovel");
			}

			while (clienteImovelEconomiaIterator.hasNext()) {
				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
						.next();
				if (dataFimRelacao.before(clienteImovelEconomia
						.getDataInicioRelacao())) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");

				}

			}

			// caso a data n�o seja menor que a atual ent�o
			// seta a data final no cliente imovel
			clienteImovelEconomiaIterator = colecaoClientesImoveisEconomiaFimRelacao
					.iterator();

			while (clienteImovelEconomiaIterator.hasNext()) {
				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) clienteImovelEconomiaIterator
						.next();
				clienteImovelEconomia
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacao);
				clienteImovelEconomia.setDataFimRelacao(dataFimRelacao);

			}

		}

		return retorno;
	}
}
