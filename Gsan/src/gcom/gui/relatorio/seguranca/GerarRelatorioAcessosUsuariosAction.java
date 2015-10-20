/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.relatorio.seguranca;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.RelatorioAcessosPorUsuarios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1040] Gerar Relat�rio de Acessos por Usu�rio
 * 
 * @author Hugo Leonardo
 *
 * @date 12/07/2010
 */

public class GerarRelatorioAcessosUsuariosAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioAcessosUsuariosActionForm form = (GerarRelatorioAcessosUsuariosActionForm) actionForm;
		
		FiltrarRelatorioAcessosUsuariosHelper helper = new FiltrarRelatorioAcessosUsuariosHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Unidade Organizacional
		if (form.getIdsUnidadeOrganizacional() != null && !form.getIdsUnidadeOrganizacional().equals("-1") ){
			//&& form.getIdsUnidadeOrganizacional().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsUnidadeOrganizacional();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setUnidadeOrganizacional(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Grupo Acesso
		if(form.getIdsGrupoAcesso() != null && !form.getIdsGrupoAcesso().equals("-1") ){
				//&& form.getIdsGrupoAcesso().length > 0){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsGrupoAcesso();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			
			if(colecao.size() > 0){
				helper.setGrupoAcesso(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Modulo
		if ( form.getModulo() != null && 
				!form.getModulo().equals("-1")) {
			
			helper.setModulo(form.getModulo());
			peloMenosUmParametroInformado = true;
		}
		
		// situa��o usu�rio
		if (form.getIdsSituacaoUsuario() != null && !form.getIdsSituacaoUsuario().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsSituacaoUsuario();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setSituacaoUsuario(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// funcionalidade
		if ( form.getIdFuncionalidade() != null && 
				!form.getIdFuncionalidade().equals("")){
			
			helper.setFuncionalidade(form.getIdFuncionalidade());
			peloMenosUmParametroInformado = true;
		}
		
		// Operacao
		if ( form.getOperacao() != null && 
				!form.getOperacao().equals("-1")) {
			
			helper.setOperacao(form.getOperacao());
			peloMenosUmParametroInformado = true;
		}
		
		// Usuario Tipo
		if(form.getIdsUsuarioTipo() != null && !form.getIdsUsuarioTipo().equals("-1") ){
				//&& form.getIdsUsuarioTipo().length > 0){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsUsuarioTipo();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setUsuarioTipo(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
	
		// Usuario
		if(form.getIdUsuario() != null && !form.getIdUsuario().equals("")){
			helper.setUsuario(form.getIdUsuario());
			peloMenosUmParametroInformado = true;
		}
		
		// Permiss�o Especial
		if (form.getIdsPermissaoEspecial() != null && !form.getIdsPermissaoEspecial().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsPermissaoEspecial();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setPermissaoEspecial(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		 
		TarefaRelatorio relatorio = new RelatorioAcessosPorUsuarios((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarRelatorioAcessosUsuariosHelper", helper);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}