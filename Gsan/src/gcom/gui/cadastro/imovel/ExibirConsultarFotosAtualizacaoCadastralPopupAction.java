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

import gcom.atualizacaocadastral.ImovelAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.ImovelFotoAtualizacaoCadastralDM;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ConsultarFotosAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Popup para exibi��o das fotos da atualiza��o cadastral.
 * 
 * @author Andr� Miranda
 * @since 19/06/2015
 */
public class ExibirConsultarFotosAtualizacaoCadastralPopupAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request,	HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirConsultarFotosAtualizacaoCadastralPopup");
		ConsultarImovelActionForm form = (ConsultarImovelActionForm) actionForm;

		Imovel imovel = getFachada().pesquisarImovel(Integer.valueOf(form.getIdImovel()));
		if(imovel == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Im�vel");
		}

		List<ImovelAtualizacaoCadastralDM> imoveisAtlzCad = getFachada().pesquisarImovelComFotoAtualizacaoCadastralDM(imovel.getId());
		if(Util.isVazioOrNulo(imoveisAtlzCad)) {
			throw new ActionServletException("atencao.naocadastrado", null, "Foto de Atualiza��o Cadastral");
		}

		ConsultarFotosAtualizacaoCadastralHelper helper;
		List<ConsultarFotosAtualizacaoCadastralHelper> helpers = new ArrayList<ConsultarFotosAtualizacaoCadastralHelper>();

		for (ImovelAtualizacaoCadastralDM imovelAtlzCad : imoveisAtlzCad) {
			helper = new ConsultarFotosAtualizacaoCadastralHelper();
			helper.setCadastrador(consultarCadastrador(imovelAtlzCad));
			helper.setDataVisita(Util.formatarDataComHora(imovelAtlzCad.getDataVisita()));

			List<ImovelFotoAtualizacaoCadastralDM> fotos =
					Fachada.getInstancia().pesquisarImovelFotoAtualizacaoCadastralDM(imovelAtlzCad.getId(), null);
			helper.setFotos(fotos);
			helpers.add(helper);
		}

		request.setAttribute("helpers", helpers);

		return retorno;
	}

	private String consultarCadastrador(ImovelAtualizacaoCadastralDM imovel) {
		FiltroUsuario filtro = new FiltroUsuario();
		filtro.adicionarParametro( new ParametroSimples(FiltroUsuario.LOGIN,imovel.getLogin(), ConectorOr.CONECTOR_OR, 2));
		filtro.adicionarParametro( new ParametroSimples(FiltroUsuario.CPF,imovel.getLogin()));

		Collection<Usuario> colecaoUsuario = getFachada().pesquisar(filtro, Usuario.class.getName());
		if (!Util.isVazioOrNulo(colecaoUsuario)) {
			Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			return usuario.getNomeUsuario();
		}

		return imovel.getLogin();
	}
}
