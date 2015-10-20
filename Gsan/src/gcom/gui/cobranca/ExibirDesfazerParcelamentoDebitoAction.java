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
package gcom.gui.cobranca;

import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Desfazer Parcelamento D�bito
 * 
 * @author Fernanda Karla
 * @since 11/01/2006
 */
public class ExibirDesfazerParcelamentoDebitoAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = this.getUsuarioLogado(httpServletRequest);
		String codigo = httpServletRequest.getParameter("codigoParcelamento");
		String motivo = httpServletRequest.getParameter("motivo");
		String confirmado = httpServletRequest.getParameter("confirmado");
		
		FiltroParcelamentoPagamentoCartaoCredito filtroParcelamentoPagamentoCartaoCredito = new FiltroParcelamentoPagamentoCartaoCredito();
		filtroParcelamentoPagamentoCartaoCredito.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID_PARCELAMENTO, codigo));
       	Collection<ParcelamentoPagamentoCartaoCredito> collectionParcelamentoPagamentoCartaoCredito = fachada.pesquisar(filtroParcelamentoPagamentoCartaoCredito, ParcelamentoPagamentoCartaoCredito.class.getName());
       	
		if(collectionParcelamentoPagamentoCartaoCredito != null && collectionParcelamentoPagamentoCartaoCredito.size() > 0){
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, "161"));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));			
	       	Collection<UsuarioPermissaoEspecial> collectionUsuarioPermissaoEspecial = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			
	       	if(collectionUsuarioPermissaoEspecial !=null && collectionUsuarioPermissaoEspecial.size() > 0){
	       		if(confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					httpServletRequest.setAttribute("parcelamentoCartao", "Confirmar");
					httpServletRequest.setAttribute("confirmacaoNormal", "confirmacaoNormal");
					httpServletRequest.setAttribute("codigoParcelamento", codigo);
					httpServletRequest.setAttribute("motivo", motivo);
					return montarPaginaConfirmacao("atencao.parcelamento_confirmado_cartao_credito", httpServletRequest, actionMapping);
				}
	       	}else{
				throw new ActionServletException("atencao.usuario_sem_permissao","exibirConsultarParcelamentoDebitoAction.do?codigoImovel=221036&codigoParcelamento=11511","");
			}
		}
		
		this.getFachada().desfazerParcelamentosDebito(motivo, new Integer(codigo),usuario);

		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Parcelamento de D�bitos desfeito com sucesso.",
			"Realizar outra manuten��o de Parcelamento de D�bitos",
			"exibirConsultarListaParcelamentoDebitoAction.do?menu=sim");
		
		return retorno;
	}
}
