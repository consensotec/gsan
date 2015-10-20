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

import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoRetornoHelper;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.usuario.Usuario;

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
public class AtualizarImovelEnderecoAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("gis");
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

		Fachada fachada = Fachada.getInstancia();
		
		if (httpServletRequest.getParameter("confirmado") != null && 
			httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			sessao.setAttribute("confirmou", "sim");
		}
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		ImovelAbaEnderecoHelper helper = new ImovelAbaEnderecoHelper();
		helper.setImovelEnderecos(colecaoEnderecos);
		helper.setSetorComercial((SetorComercial) sessao.getAttribute("setorComercial"));
		helper.setUsuarioLogado(usuario);
		helper.setIdFuncionalidade(Funcionalidade.MANTER_IMOVEL);
		
		if (sessao.getAttribute("idQuadraInicial") != null && !sessao.getAttribute("idQuadraInicial").equals("")) {
			
			helper.setIdQuadraAnterior( (Integer) sessao.getAttribute("idQuadraInicial"));
		}
		
		ImovelAbaEnderecoRetornoHelper resultado = fachada.validarImovelAbaEndereco(helper);
		
		if (resultado.isMunicipioEnderecoDiferenteMunicipioSetorComercial() && sessao.getAttribute("confirmou") == null) {
			retorno = montarPaginaConfirmacaoWizard(
				"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial",
				httpServletRequest, actionMapping, "");
		}

//		// Alteracao Solicitada por Ana Breda, Data:23/02/2006
//		// Alterado por R�mulo Aur�lio
//
//		// Cria cole��o
//
//		Collection setorComerciais = (Collection) sessao
//				.getAttribute("setorComerciais");
//
//		// Cria cole��o
//		Collection colecaoEnderecos = (Collection) sessao
//				.getAttribute("colecaoEnderecos");

//		Imovel imovelEnderecos = (Imovel) colecaoEnderecos.iterator().next();
//
//		System.out
//				.println("imovel.getLogradouroBairro().getLogradouro().getMunicipio().getId().toString()="
//						+ imovelEnderecos.getLogradouroBairro().getLogradouro()
//								.getMunicipio().getId().intValue());
//		System.out
//				.println("( ((SetorComercial) ((List) setorComerciais).get(0)).getMunicipio().getId()="
//						+ (((SetorComercial) ((List) setorComerciais).get(0))
//								.getMunicipio().getId().intValue()));
//		System.out
//				.println("boolean="
//						+ (!(imovelEnderecos.getLogradouroBairro()
//								.getLogradouro().getMunicipio().getId()
//								.intValue() == (((SetorComercial) ((List) setorComerciais)
//								.get(0)).getMunicipio().getId().intValue()))));
//
//		if (imovelEnderecos.getLogradouroBairro() != null
//				&& imovelEnderecos.getLogradouroBairro().getLogradouro() != null
//				&& imovelEnderecos.getLogradouroBairro().getLogradouro()
//						.getMunicipio() != null
//				&& (!(imovelEnderecos.getLogradouroBairro().getLogradouro()
//						.getMunicipio().getId().intValue() == (((SetorComercial) ((List) setorComerciais)
//						.get(0)).getMunicipio().getId().intValue())))) {
//
//			Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
//
//			if (!fachada
//					.verificarPermissaoAtualizarImovelMunicipioLogradouroDiferenteSetor(usuario)) {
//				throw new ActionServletException(
//						"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial");
//			} else {
//				if (sessao.getAttribute("confirmou") == null) {
//					// Comentado por S�vio Luiz. Data:18/12/2007.
//					// Alterado para mudar o destino passando por atributo por
//					// algum action.
//					
//					// httpServletRequest.setAttribute("destino", actionMapping
//					// .findForward("gerenciadorProcesso"));
//					retorno = montarPaginaConfirmacaoWizard(
//							"atencao.usuario.sem.permissao.inserir_logradouro_municipio_diferente_setor_comercial",
//							httpServletRequest, actionMapping, "");
//				}
//			}
//
//		}
//
//		if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
//			throw new ActionServletException(
//					"atencao.imovel_endereco.nao_cadastrado", null);
//		} else {
//			if (colecaoEnderecos.size() > 1) {
//				throw new ActionServletException(
//						"atencao.imovel_endereco.mais_de_um", null);
//			}
//		}

		return retorno;
	}

}
