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
package gcom.gui.batch;

import gcom.batch.FiltroProcesso;
import gcom.batch.FiltroProcessoSituacao;
import gcom.batch.Processo;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de filtrar processo iniciado
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class ExibirFiltrarProcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarProcesso");

		// Pesquisa as situa��es do processos para o select da p�gina
		this.pesquisarProcessoSituacao(httpServletRequest);

		this.resetForm((FiltrarProcessoActionForm) actionForm);
		
		// Pesquisa o Processo digitado
		this.pesquisarProcessoDigitado(httpServletRequest,
				(FiltrarProcessoActionForm) actionForm);
		
		this.pesquisarUsuarioDigitado(httpServletRequest,
				(FiltrarProcessoActionForm) actionForm);

		return retorno;
	}

	/**
	 * Pesquisa todas as Situa��es do Processo para popular o select da p�gina
	 * de inserir processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/07/2006
	 * 
	 * @param httpServletRequest
	 */
	private void pesquisarProcessoSituacao(HttpServletRequest httpServletRequest) {
		FiltroProcessoSituacao filtroProcessoSituacao = new FiltroProcessoSituacao();
		Collection<ProcessoSituacao> colecao = Fachada.getInstancia()
				.pesquisar(filtroProcessoSituacao,
						ProcessoSituacao.class.getName());
		httpServletRequest.setAttribute("colecaoProcessoSituacao", colecao);

	}

	/**
	 * Pesquisa todas as Situa��es do Processo para popular o select da p�gina
	 * de inserir processo
	 * 
	 * @author Rodrigo Silveira
	 * @date 15/03/2007
	 * 
	 * @param httpServletRequest
	 */

	private void resetForm(FiltrarProcessoActionForm form) {

		String idDigitadoEnterProcesso = (String) form.getIdProcesso();

		if (idDigitadoEnterProcesso == null
				|| idDigitadoEnterProcesso.trim().equals("")) {
			form.resetFormCustom();
		}

	}

	/**
	 * Fun��o que pesquisa um Processo iniciado precedente digitado
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/07/2006
	 * 
	 * @param httpServletRequest
	 * @param actionForm
	 */
	private void pesquisarProcessoDigitado(
			HttpServletRequest httpServletRequest,
			FiltrarProcessoActionForm actionForm) {

		String idDigitadoEnterProcesso = (String) actionForm.getIdProcesso();

		actionForm.setDescricaoProcesso("");
		actionForm.setIdProcesso("");

		// Verifica se o c�digo foi digitado
		if (idDigitadoEnterProcesso != null
				&& !idDigitadoEnterProcesso.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterProcesso) > 0
				&& !idDigitadoEnterProcesso.trim().equals(
						actionForm.getIdProcesso())) {
			Fachada fachada = Fachada.getInstancia();

			// Este trecho pesquisa o processo digitado e altera o form para
			// refletir o resultado da busca na p�gina de filtrar processo

			FiltroProcesso filtroProcesso = new FiltroProcesso();

			filtroProcesso.adicionarParametro(new ParametroSimples(
					FiltroProcesso.ID, idDigitadoEnterProcesso));

			Collection processos = fachada.pesquisar(filtroProcesso,
					Processo.class.getName());

			if (processos != null && !processos.isEmpty()) {
				// O processo foi encontrado
				actionForm.setIdProcesso(((Processo) ((List) processos).get(0))
						.getId().toString());
				actionForm.setDescricaoProcesso(((Processo) ((List) processos)
						.get(0)).getDescricaoProcesso());

			} else {
				actionForm.setIdProcesso("");
				httpServletRequest.setAttribute("idProcessoNaoEncontrado",
						"true");
				actionForm.setDescricaoProcesso("Processo inexistente");

			}
		}

	}
	
	//CRC-1466 Fl�vio Leonardo 17/03/09
	private void pesquisarUsuarioDigitado(HttpServletRequest httpServletRequest,
			FiltrarProcessoActionForm actionForm){
		
		String idDigitadoEnterUsuario = (String) actionForm.getUsuarioId();

		actionForm.setUsuarioDescricao("");
		actionForm.setUsuarioId("");
		
		if(idDigitadoEnterUsuario != null
		&& !idDigitadoEnterUsuario.trim().equals("")
		&& !idDigitadoEnterUsuario.trim().equals(
				actionForm.getIdProcesso())) {
			
			Fachada fachada = Fachada.getInstancia();
		
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idDigitadoEnterUsuario));
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(!colecaoUsuario.isEmpty()){
				Usuario usuario = (Usuario)Util.retonarObjetoDeColecao(colecaoUsuario);
				
				actionForm.setUsuarioId(usuario.getId().toString());
				actionForm.setUsuarioDescricao(usuario.getNomeUsuario());
			}else{
				
				httpServletRequest.setAttribute("idUsuarioNaoEncontrado",
						"true");
				actionForm.setUsuarioDescricao("Usu�rio inexistente");
				actionForm.setUsuarioId("");
			}
			
		}
		
	}

}
