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
package gsan.gui.cobranca;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroCliente;
import gsan.cadastro.cliente.FiltroClienteRelacaoTipo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 10 de Janeiro de 2006
 */
public class ExibirConsultarDebitoAction extends GcomAction {
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
        
    	// Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("consultarDebito");

        // Mudar isso quando tiver esquema de seguran�a
        HttpSession sessao = httpServletRequest.getSession(false);
        
        ConsultarDebitoActionForm consultarDebitoActionForm = (ConsultarDebitoActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        
        if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").equals("")) {
        	consultarDebitoActionForm.setResponsavel("1");
        }
        
//        if (httpServletRequest.getParameter("voltar") != null && !httpServletRequest.getParameter("voltar").equals("")) {
//        	consultarDebitoActionForm.setTipoRelacao("-1");
//        }
        
        if (httpServletRequest.getParameter("voltar") != null && !httpServletRequest.getParameter("voltar").equals("")) {
        	
        	if (sessao.getAttribute("tipoPesquisa") != null && sessao.getAttribute("tipoPesquisa").equals("cliente")) {
        		consultarDebitoActionForm.setCodigoClienteSuperior(null);
        		consultarDebitoActionForm.setCodigoClienteSuperiorClone(null);
        	} else if (sessao.getAttribute("tipoPesquisa") != null && sessao.getAttribute("tipoPesquisa").equals("clienteSuperior")) {
        		consultarDebitoActionForm.setCodigoCliente(null);
        		consultarDebitoActionForm.setCodigoClienteClone(null);
        		//consultarDebitoActionForm.setTipoRelacao("-1");
        	} else {
        		consultarDebitoActionForm.setCodigoClienteSuperior(null);
        		consultarDebitoActionForm.setCodigoClienteSuperiorClone(null);
        		consultarDebitoActionForm.setCodigoCliente(null);
        		consultarDebitoActionForm.setCodigoClienteClone(null);
        		//consultarDebitoActionForm.setTipoRelacao("-1");        		
        	}
        	
        	sessao.removeAttribute("tipoPesquisa");
        	
//        	if (consultarDebitoActionForm.getCodigoClienteSuperior() != null && !consultarDebitoActionForm.getCodigoClienteSuperior().trim().equals("")) {
//        		//consultarDebitoActionForm.setTipoRelacao("-1");
//        	}
        }
        
        if (!fachada.verificarPermissaoConsultarDebitosIndicadoNaContaOuTodos(usuario)) {
			sessao.setAttribute("semPermissao", true);
		}
        
        // Remove as cole��es e os valores da sess�o
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("valorConta");
		sessao.removeAttribute("valorAcrescimo");
		sessao.removeAttribute("valorAgua");
		sessao.removeAttribute("valorEsgoto");
		sessao.removeAttribute("valorDebito");
		sessao.removeAttribute("valorCredito");
		sessao.removeAttribute("valorContaAcrescimo");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("valorDebitoACobrar");
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("valorCreditoARealizar");
		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("valorGuiaPagamento");
		sessao.removeAttribute("valorTotalSemAcrescimo");
		sessao.removeAttribute("valorTotalComAcrescimo");
		sessao.removeAttribute("colecaoDebitoCliente");
		//sessao.removeAttribute("tipoRelacao");
		sessao.removeAttribute("valorImposto");
        
        String idDigitadoEnterCliente = (String) consultarDebitoActionForm.getCodigoCliente();
        String idDigitadoEnterImovel = (String) consultarDebitoActionForm.getCodigoImovel();
        String idDigitadoEnterClienteSuperior = (String) consultarDebitoActionForm.getCodigoClienteSuperior();
      
        // verifica se o codigo do cliente foi digitado
        if (idDigitadoEnterCliente != null
                && !idDigitadoEnterCliente.trim().equals("")
                && Integer.parseInt(idDigitadoEnterCliente) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<Cliente> clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List<Cliente>) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List<Cliente>) clienteEncontrado)
											.get(0)).getId());
				}

				consultarDebitoActionForm
						.setCodigoCliente(((Cliente) ((List<Cliente>) clienteEncontrado)
								.get(0)).getId().toString());
				consultarDebitoActionForm
						.setCodigoClienteClone(((Cliente) ((List<Cliente>) clienteEncontrado)
							.get(0)).getId().toString());
				consultarDebitoActionForm
						.setNomeCliente(((Cliente) ((List<Cliente>) clienteEncontrado)
								.get(0)).getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "tipoRelacao");

			} else {
				httpServletRequest.setAttribute("corCliente","exception");
               	consultarDebitoActionForm
               			.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
               	
               	consultarDebitoActionForm.setCodigoCliente("");
               	
               	httpServletRequest.setAttribute("nomeCampo", "codigoCliente");

			}
        }
        
        // verifica se o codigo do cliente superior foi digitado
        if (idDigitadoEnterClienteSuperior != null
                && !idDigitadoEnterClienteSuperior.trim().equals("")
                && Integer.parseInt(idDigitadoEnterClienteSuperior) > 0) {
            
        	FiltroCliente filtroCliente = new FiltroCliente();
			
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterClienteSuperior));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection<Cliente> clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O Cliente foi encontrado
				if (((Cliente) ((List<Cliente>) clienteEncontrado).get(0))
						.getIndicadorUso().equals(
								ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.cliente.inativo",
							null, ""
									+ ((Cliente) ((List<Cliente>) clienteEncontrado)
											.get(0)).getId());
				}

				consultarDebitoActionForm
						.setCodigoClienteSuperior(((Cliente) ((List<Cliente>) clienteEncontrado)
								.get(0)).getId().toString());
				consultarDebitoActionForm
						.setCodigoClienteSuperiorClone(((Cliente) ((List<Cliente>) clienteEncontrado)
							.get(0)).getId().toString());
				consultarDebitoActionForm
						.setNomeClienteSuperior(((Cliente) ((List<Cliente>) clienteEncontrado)
								.get(0)).getNome());
				
				httpServletRequest.setAttribute("nomeCampo", "referenciaInicial");

			} else {
				httpServletRequest.setAttribute("corClienteSuperior","exception");
               	consultarDebitoActionForm
               			.setNomeClienteSuperior(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
               	
               	
               	consultarDebitoActionForm.setCodigoClienteSuperior("");
               	
               	httpServletRequest.setAttribute("nomeCampo", "codigoClienteSuperior");

			}
        }
        
        if (idDigitadoEnterImovel != null && !idDigitadoEnterImovel.trim().equals("")) {
			// Pesquisa o imovel na base
			String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(idDigitadoEnterImovel));
        	
			if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {
				
				// O imovel foi encontrado
				consultarDebitoActionForm.setCodigoImovel(idDigitadoEnterImovel);
				consultarDebitoActionForm.setCodigoImovelClone(idDigitadoEnterImovel);
				consultarDebitoActionForm.setInscricaoImovel(imovelEncontrado);
				httpServletRequest.setAttribute("nomeCampo", "referenciaInicial");
			} else {
					httpServletRequest.setAttribute("corImovel","exception");
               		consultarDebitoActionForm
               			.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
               		
               		consultarDebitoActionForm.setCodigoImovel("");
               		
               		httpServletRequest.setAttribute("nomeCampo", "codigoImovel");
			}
        }

        FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
        
        filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(FiltroClienteRelacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName() );
		
		if (collectionClienteRelacaoTipo != null && !collectionClienteRelacaoTipo.isEmpty()) 
		{
			httpServletRequest.setAttribute("collectionClienteRelacaoTipo", collectionClienteRelacaoTipo);
		}
		else
		{
	        throw new ActionServletException(
	        		"atencao.collectionClienteRelacaoTipo_inexistente", null, "id");
		}
		return retorno;
    }
}
