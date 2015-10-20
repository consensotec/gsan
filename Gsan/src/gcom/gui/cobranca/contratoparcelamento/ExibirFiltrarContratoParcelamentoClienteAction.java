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



import gcom.cadastro.cliente.Cliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarContratoParcelamentoClienteAction extends GcomAction {
	
	
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

		ActionForward retorno = actionMapping.findForward("exibirFiltrarContratoParcelamentoClienteAction");
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltrarContratoParcelamentoClienteActionForm filtrarContratoParcelamentoActionForm = (FiltrarContratoParcelamentoClienteActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		
		
		//Motivos de cancelamento do contrato
		 FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
		 filtroParcelamentoMotivoDesfazer.adicionarParametro(new ParametroSimples(
		 FiltroParcelamentoMotivoDesfazer.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		 filtroParcelamentoMotivoDesfazer.setCampoOrderBy(FiltroParcelamentoMotivoDesfazer.DESCRICAO);

		 
		 Collection colecaoParcelamentoMotivoDesfazer = fachada.pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName());
		 if(colecaoParcelamentoMotivoDesfazer.size() > 0)
			 sessao.setAttribute("colecaoContratoMotivoCancelamento",colecaoParcelamentoMotivoDesfazer);
		 
		 if (filtrarContratoParcelamentoActionForm.getLoginUsuario() != null 
				 && !filtrarContratoParcelamentoActionForm.getLoginUsuario().trim().equals("")){
			 
		 	FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, 
					filtrarContratoParcelamentoActionForm.getLoginUsuario()));
			Collection colecaoUsuario = fachada.pesquisar(
					filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				
				filtrarContratoParcelamentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
				filtrarContratoParcelamentoActionForm.setLoginUsuario(usuario.getLogin());
				
				sessao.setAttribute("usuarioEncontrado","true");
			} else {

				sessao.removeAttribute("usuarioEncontrado");
				filtrarContratoParcelamentoActionForm.setLoginUsuario("");
				filtrarContratoParcelamentoActionForm.setNomeUsuario("Usu�rio Inexistente");
				
			}
			
		}
		 
		 //Recupera campo AutoCompleteCliente
		 if (filtrarContratoParcelamentoActionForm.getClienteAutocomplete() != null && !"".equals(filtrarContratoParcelamentoActionForm.getClienteAutocomplete())
					&& filtrarContratoParcelamentoActionForm.getClienteAutocomplete().contains("-")){
				int id = Integer.parseInt(filtrarContratoParcelamentoActionForm.getClienteAutocomplete().split(" - ")[0].trim());
				Cliente cliente = fachada.pesquisarClienteDigitado(id);
				filtrarContratoParcelamentoActionForm.setIdClienteContrato(id+"");
				filtrarContratoParcelamentoActionForm.setContratoClienteDescricaoFiltro(cliente.getNome());
				filtrarContratoParcelamentoActionForm.setClienteAutocompleteCNPJ(cliente.getCnpjFormatado());
		}
		
		 //Validar n�mero do contrato anterior pesquisado
		 String pesquisarContratoAnterior = httpServletRequest.getParameter("pesquisarContratoAnterior");
		 
		 if(pesquisarContratoAnterior != null && !pesquisarContratoAnterior.equals("")){
			 
			 FiltroContratoParcelamento filtroContratoParcelamento = new FiltroContratoParcelamento();
			 filtroContratoParcelamento.adicionarParametro(new ComparacaoTexto(FiltroContratoParcelamento.NUMERO,filtrarContratoParcelamentoActionForm.getIdContratoAnterior()));
			 
			 ArrayList<ContratoParcelamento> lista = new ArrayList(fachada.pesquisar(filtroContratoParcelamento,ContratoParcelamento.class.getName()));
			 
			 if(lista == null || lista.size() == 0){
				 throw new ActionServletException(
					"atencao.numero.contrato.nao.existe");
			 }
			 
		 }
		 
		 
		 String limparForm = httpServletRequest.getParameter("limparForm");
		 
		 if(limparForm != null && !limparForm.equals("")){
			 filtrarContratoParcelamentoActionForm.reset(actionMapping,httpServletRequest);
		 }
		 

		 		
		
		
		
		return retorno;
	}

}
