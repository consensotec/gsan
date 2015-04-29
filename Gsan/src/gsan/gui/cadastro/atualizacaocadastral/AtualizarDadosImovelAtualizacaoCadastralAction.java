/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gui.cadastro.atualizacaocadastral;

import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarDadosImovelAtualizacaoCadastralAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

        //Seta o retorno
    	ActionForward retorno = 
			actionMapping.findForward("exibirAtualizarDadosImovelAtualizacaoCadastralPopup");
        
    	// Obtém o action form
		ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm atualizacaoCadastralActionForm =
			(ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		String idImovelAtualizacaoCadastral = atualizacaoCadastralActionForm.getIdImovelAtualizacaoCadastral();
		
		String idImovel = (String) sessao.getAttribute("imovel");
		String idCliente = (String) sessao.getAttribute("cliente");		
		
		/*
		 * [FS0007] - Verificar permissão especial para cliente relacionado ao programa Viva Água
		 * @author Diogo Luiz
		 * @date 23/09/2014 
		 */	
		if(fachada.verificarClienteImovelVivaAguaPermissao(new Integer(idImovel), usuario)){
			throw new ActionServletException("atencao.usuario_sem_permissao_viva_agua");
		}
		
		/*
		 * [FS0006] – Verificar permissão especial para alteração de cliente negativado.
		 * @author Diogo Luiz
		 * @date 23/09/2014 
		 */	
		if(fachada.verificarSeClienteEstaNegativado(new Integer(idCliente), usuario)){
			throw new ActionServletException("atencao.usuario_nao_possui_permissao_alterar_cliente_negativado", null, idCliente);
		}		
		
		if (!atualizacaoCadastralActionForm.getIdRegistrosAutorizados().equals("")) {
			String registrosAutorizados = atualizacaoCadastralActionForm.getIdRegistrosAutorizados();
			
			String[] listaIdRegistrosSim = registrosAutorizados.split(",");
			// Varre a lista para recuperar os ids SIM selecionados
			if(listaIdRegistrosSim != null && !listaIdRegistrosSim.equals("")){
			
				// Realiza a alteracao dos registros autorizados
				fachada.atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(listaIdRegistrosSim, ConstantesSistema.SIM,
						usuario,idImovelAtualizacaoCadastral);
				
			}
		}
			
		if (!atualizacaoCadastralActionForm.getIdRegistrosNaoAutorizados().equals("")) {
			String registrosNaoAutorizados = atualizacaoCadastralActionForm.getIdRegistrosNaoAutorizados();
			
			String[] listaIdRegistrosNao = registrosNaoAutorizados.split(",");
			// Varre a lista para recuperar os ids SIM selecionados
			if(listaIdRegistrosNao != null && !listaIdRegistrosNao.equals("")){
				// Realiza a alteracao dos registros autorizados
				fachada.atualizarIndicadorAutorizacaoColunaAtualizacaoCadastral(listaIdRegistrosNao, ConstantesSistema.NAO,
						usuario,idImovelAtualizacaoCadastral);
				
			}
		}
		
		httpServletRequest.setAttribute("reload", true);
        
		if(idImovelAtualizacaoCadastral != null && !idImovelAtualizacaoCadastral.equals("")){
			String mensagem = "Imóvel inserido com sucesso";
			httpServletRequest.removeAttribute("reload");
			retorno = actionMapping.findForward("telaSucessoPopup");
			
			montarPaginaSucesso(httpServletRequest,	mensagem, "", "");
		}
		
        return retorno;
    }
    
}
