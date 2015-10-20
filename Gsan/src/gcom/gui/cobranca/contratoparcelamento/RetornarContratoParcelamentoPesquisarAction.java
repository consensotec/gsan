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
package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoCliente;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarContratoParcelamentoPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornoPesquisa");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarContratoParcelamentoActionForm form = (PesquisarContratoParcelamentoActionForm) actionForm;
		
		String numeroContrato = form.getNumeroContrato();
		String dataContrato = form.getDataContrato();
		String loginUsuario = form.getLoginUsuario();
		String clienteAutocomplete = form.getAutocompleteCliente();
		String indicadorSituacao = form.getIndicadorSituacao();
		
		FiltroContratoParcelamentoCliente filtro = new FiltroContratoParcelamentoCliente();	
		filtro.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.ID_CLIENTE_SUPERIOR));
		filtro.adicionarParametro(new ParametroNulo(FiltroContratoParcelamentoCliente.DATA_CANCELAMENTO));
		
		boolean peloMenosUm = false;
		
		if(numeroContrato != null && !numeroContrato.equals("")){
			peloMenosUm = true;
			filtro.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoCliente.NUMERO_CONTRATO, numeroContrato));
		}
		
		if(dataContrato != null && !dataContrato.equals("")){
			peloMenosUm = true;
			Date data = Util.converteStringParaDate(dataContrato); 
			filtro.adicionarParametro(new ParametroSimples(
					FiltroContratoParcelamentoCliente.DATA_CONTRATO, data));
		}
		
		if(indicadorSituacao != null && !indicadorSituacao.equals("")){
			peloMenosUm = true;
			
			//Encerrados
			if(indicadorSituacao.equals("2")){
				filtro.adicionarParametro(new ParametroSimplesDiferenteDe(
						FiltroContratoParcelamentoCliente.PARCEL_SITUACAO_ID, ParcelamentoSituacao.NORMAL + ""));
			//N�o Encerrados
			}else if(indicadorSituacao.equals("1")){
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.PARCEL_SITUACAO_ID, indicadorSituacao));
			}
		}
		
		if (loginUsuario != null && !loginUsuario.trim().equals("")){
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, form.getLoginUsuario()));
			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				peloMenosUm = true;
				filtro.adicionarParametro(new ParametroSimples(
						FiltroContratoParcelamentoCliente.USUARIO_RESPONSAVEL_ID, usuario.getId()));
			}
		}else{
			sessao.removeAttribute("usuarioResponsavel");
		}
		
		if (clienteAutocomplete != null && !"".equals(clienteAutocomplete)
				&& clienteAutocomplete.contains("-")){
			int id = Integer.parseInt(clienteAutocomplete.split(" - ")[0].trim());
			peloMenosUm = true;
			filtro.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoCliente.ID_CLIENTE, id+""));
		}else{
			sessao.removeAttribute("cliente");
		}
		
		if (!peloMenosUm){
			throw new ActionServletException(
				"atencao.filtro.nenhum_parametro_informado", null, "contratoParcelamento");
		}
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoCliente.CONTRATO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoParcelamentoCliente.CLIENTE);
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtro, ContratoParcelamentoCliente.class.getName());
		Collection collContratoParcelamento = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Valida��es 
		if (collContratoParcelamento == null || collContratoParcelamento.isEmpty()) {
			throw new ActionServletException(
				"atencao.pesquisa.nenhumresultado", null, "contratoParcelamento");
		} else {
			sessao.setAttribute("collContratoParcelamento", collContratoParcelamento);

		}		
		
		String popup = (String) sessao.getAttribute("popup");
		if (popup != null && popup.equals("2")) {
			sessao.setAttribute("popup", popup);
		} else {
			sessao.removeAttribute("popup");
		}
		
		return retorno;
	}

}
