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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0652] Manter Comando de Negativa��o<br>
 * [SB0004] Exibir Atualizar Comando de Negativa��o por Guia de Pagamento<br>
 * 
 * Exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da atualiza��o de um Comando de Negativa��o por Guia de Pagamento<br>
 * Aba n� 02 - Dados Cliente
 *
 * @author Andr� Miranda
 * @date 17/03/2015
 */
public class ExibirAtualizarComandoNegativacaoPorGuiaPagamentoDadosClienteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
        ActionForward retorno = actionMapping.findForward("atualizarComandoNegativacaoPorGuiaPagamentoDadosCliente");
    	HttpSession sessao = request.getSession(false);
        AtualizarComandoNegativacaoPorCriterioActionForm form = (AtualizarComandoNegativacaoPorCriterioActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
  	  
        // Refer�ncia do D�bito Final
		if (!Util.verificarNaoVazio(form.getReferenciaFinal())) {
			String anoMesArrecadacao = Util.formatarAnoMesParaMesAno(getSistemaParametro().getAnoMesArrecadacao());
			form.setReferenciaFinal(anoMesArrecadacao);
		}

		// Data do Vencimento Final
		if (!Util.verificarNaoVazio(form.getDataVencimentoFinal())) {
			//Per�odo de vencimento do d�bito	
			Integer numeroDiasVencimentoCobranca = new Integer(getSistemaParametro().getNumeroDiasVencimentoCobranca());			
			Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);
			Date dataVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dataVencimentoFinal, -5);
			form.setDataVencimentoInicial(Util.formatarData(dataVencimentoInicial));
			form.setDataVencimentoFinal(Util.formatarData(dataVencimentoFinal));
  	    }

		// Pesquisa Cliente
		String idCliente = form.getIdCliente();
		if (Util.verificarNaoVazio(idCliente)) {
			FiltroCliente filtro = new FiltroCliente();
			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			Collection<?> colecaoCliente = fachada.pesquisar(filtro, Cliente.class.getName());

			if (Util.isVazioOrNulo(colecaoCliente)) {
				request.setAttribute("funcionalidadeEncontrada", "exception");
				form.setIdCliente(null);
				form.setDescricaoCliente("Cliente Inexistente");
			} else {
				request.setAttribute("funcionalidadeEncontrada", "valor");

				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				form.setIdCliente(cliente.getId().toString());
				form.setDescricaoCliente(cliente.getNome());

				form.setTipoCliente(null);
			}
		} else {
			form.setDescricaoCliente(null);
		}

        // Pesquisar Tipo do Cliente
		if (sessao.getAttribute("colecaoTipoCliente") == null) {
			Collection colecaoTipoCliente = fachada.pesquisar(new FiltroClienteTipo(), ClienteTipo.class.getName());
			sessao.setAttribute("colecaoTipoCliente", colecaoTipoCliente);
		}

		// Data Corrente
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();
		request.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));

    	return retorno;
    }
}
