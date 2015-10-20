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
package gcom.gui.atendimentopublico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
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
 * @date 19/01/2007
 */
public class EnviarDadosBancosAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("enviarDadosBancoBrasilAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("banco").equalsIgnoreCase(
				"bancoBrasil")) {

			retorno = actionMapping.findForward("enviarDadosBancoBrasilAction");
		}
		
		if(httpServletRequest.getParameter("banco").equalsIgnoreCase(
				"Bradesco")) {

			retorno = actionMapping.findForward("enviarDadosBradescoAction");
		}

		// Geracao do codigo de Barra da conta

		Collection idsContaEP = new ArrayList();
		if (sessao.getAttribute("idConta") != null
				&& !sessao.getAttribute("idConta").equals("")) {
			idsContaEP.add(new Integer("" + sessao.getAttribute("idConta")));
		}else if(httpServletRequest.getParameter("idConta") != null
				&& !httpServletRequest.getParameter("idConta").equals("")) {
			idsContaEP.add(new Integer("" + httpServletRequest.getParameter("idConta")));
		}
		Collection<EmitirContaHelper> colecaoEmitirContaHelper = fachada
				.emitir2ViaContas(idsContaEP, false,ConstantesSistema.NAO);

		EmitirContaHelper emitirContaHelper = colecaoEmitirContaHelper
				.iterator().next();

		String representacaoNumericaCodBarraFormatada = emitirContaHelper
				.getRepresentacaoNumericaCodBarraFormatada();

		// Cortar a representacaoNumericaCodBarraFormatada separando de 12 em
		// 12, mas tirando os espacos e tra�os

		String cAux1 = representacaoNumericaCodBarraFormatada.substring(0, 11)
				+ representacaoNumericaCodBarraFormatada.substring(12, 13);

		String cAux2 = representacaoNumericaCodBarraFormatada.substring(14, 25)
				+ representacaoNumericaCodBarraFormatada.substring(26, 27);

		String cAux3 = representacaoNumericaCodBarraFormatada.substring(28, 39)
				+ representacaoNumericaCodBarraFormatada.substring(40, 41);

		String cAux4 = representacaoNumericaCodBarraFormatada.substring(42, 53)
				+ representacaoNumericaCodBarraFormatada.substring(54, 55);
		//		
		// String cAux2 =
		// String cAux3 =
		// String cAux4 =
		// codigo de barras passado pela 2. via de conta
		String codigo = cAux1 + cAux2 + cAux3 + cAux4;
		
		httpServletRequest.setAttribute("codigo",codigo);

		// valor passado
		String valor = (String) emitirContaHelper.getValorContaString();

		httpServletRequest.setAttribute("valor", valor);

		// primeira parte co codigo de barras
		String cb1 = cAux1;

		httpServletRequest.setAttribute("cb1", cb1);

		// segunda parte
		String cb2 = cAux2;
		httpServletRequest.setAttribute("cb2", cb2);
		// terceira
		String cb3 = cAux3;
		httpServletRequest.setAttribute("cb3", cb3);
		// quarta
		String cb4 = cAux4;
		httpServletRequest.setAttribute("cb4", cb4);

		// codigo de barras para exibir para o cliente

		String cb = "Codigo de barras#" + codigo;

		httpServletRequest.setAttribute("cb", cb);

		Date data = new Date();

		String dt = Util.formatarData(data);

		httpServletRequest.setAttribute("dt", dt);

		String dia = dt.substring(0, 2);
		String mes = dt.substring(3, 5);
		String ano = dt.substring(6, 10);

		String dtPagamento = dia + mes + ano;

		httpServletRequest.setAttribute("dtPagamento", dtPagamento);

		String dtEd = "Data do pagamento#" + dt;

		httpServletRequest.setAttribute("dted", dtEd);

		// $dt = date("dmY"); // data para passar para o banco
		// $dted = "Data do pagamento#".date("d/m/Y");

		// Date dt = date("dmY");

		// BigDecimal valorConta = new BigDecimal(0);

		String valorAux = valor.toString();

		String vlrSemPonto = valorAux.replace(".", "");

		String vlrSemVirgula = vlrSemPonto.replace(",", "");

		String vlr = "0000000" + vlrSemVirgula;

		httpServletRequest.setAttribute("vlr", vlr);

		// valorConta = new BigDecimal(valorAux);

		String vlRed = "Valor#R$ " + valor;

		httpServletRequest.setAttribute("vlred", vlRed);

		/*
		 * C�digo php Original
		 * 
		 * $codigo = $_GET['codigobarra']; // codigo de barras passado pela 2.
		 * via de conta $valor = $_GET['valor']; // valor passado
		 * 
		 * $cb1 = substr($codigo, 0, 12); // primeira parte co codigo de barras
		 * $cb2 = substr($codigo, 12, 12); // segunda parte $cb3 =
		 * substr($codigo, 24, 12); // terceira $cb4 = substr($codigo, 36, 12); //
		 * quarta
		 * 
		 * $cb = "Codigo de barras#".$codigo; // codigo de barras para exibir
		 * para o cliente
		 * 
		 * $dt = date("dmY"); // data para passar para o banco $dted = "Data do
		 * pagamento#".date("d/m/Y");
		 * 
		 * $vlrsemponto = str_replace(".", "", $valor); $vlrsemvirgula =
		 * str_replace(",", "", $vlrsemponto); $vlr = str_pad($vlrsemvirgula,
		 * 11, "0", STR_PAD_LEFT); $vlred = "Valor#R$ ".$valor;
		 */
		
		
		/*
		 * Colocado por Raphael Rossiter em 16/05/2007
		 */
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("nomeEmpresa", sistemaParametro.getNomeEmpresa().trim());

		return retorno;

	}
}
