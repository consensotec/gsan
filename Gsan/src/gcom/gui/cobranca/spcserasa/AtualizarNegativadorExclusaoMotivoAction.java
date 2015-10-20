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
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter  Negativador Exclus�o Motivo
 * 
 * @author Yara Taciane 
 * @created 04/01/2008
 */
public class AtualizarNegativadorExclusaoMotivoAction extends GcomAction {
	/**
	 * @author Yara Taciane
	 * @date 04/01/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm = (AtualizarNegativadorExclusaoMotivoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
			
		// ------------ REGISTRAR TRANSA��O ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSA��O ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a

		NegativadorExclusaoMotivo negativadorExclusaoMotivo = (NegativadorExclusaoMotivo) sessao.getAttribute("negativadorExclusaoMotivo");

		// Pegando os dados do Formul�rio	
		String descricaoExclusaoMotivo = atualizarNegativadorExclusaoMotivoActionForm.getDescricaoExclusaoMotivo();
		String indicadorUso = atualizarNegativadorExclusaoMotivoActionForm.getIndicadorUso();
		String idCobrancaDebitoSituacao = atualizarNegativadorExclusaoMotivoActionForm.getIdCobrancaDebitoSituacao();
		
		Long time = Long.parseLong(atualizarNegativadorExclusaoMotivoActionForm.getTime()); 
		
		// Seta os campos para serem atualizados				
		
		if (descricaoExclusaoMotivo!= null	&& !descricaoExclusaoMotivo.equals("")) {

			negativadorExclusaoMotivo.setDescricaoExclusaoMotivo(descricaoExclusaoMotivo);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Descri��o Exclus�o Motivo");
		}	
		
		if (idCobrancaDebitoSituacao!= null	&& !idCobrancaDebitoSituacao.equals("-1")) {

			CobrancaDebitoSituacao cobrancaDebitoSituacao = new CobrancaDebitoSituacao();
			cobrancaDebitoSituacao.setId(new Integer(idCobrancaDebitoSituacao));
			negativadorExclusaoMotivo.setCobrancaDebitoSituacao(cobrancaDebitoSituacao);

		} else {
			throw new ActionServletException("atencao.required", null,
					"Cobran�a D�bito Situa��o");
		}		
		
		if (indicadorUso!= null	&& !indicadorUso.equals("")) {		
		  negativadorExclusaoMotivo.setIndicadorUso(Short.parseShort(indicadorUso));		
		}else{
			throw new ActionServletException("atencao.required", null,
			"indicador de Uso");
		}
		
		//Check para atualiza��o realizada por outro usu�rio 
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo(); 
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID, negativadorExclusaoMotivo.getId()));
		
		Collection collNegativadorExclusaoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class.getName());
		
		NegativadorExclusaoMotivo negativExclusaoMotivo = (NegativadorExclusaoMotivo)collNegativadorExclusaoMotivo.iterator().next();

		if (negativExclusaoMotivo.getUltimaAlteracao().getTime() != time){
			throw new ActionServletException("atencao.registro_remocao_nao_existente");
		}
		// ------------ REGISTRAR TRANSA��O ----------------
		negativadorExclusaoMotivo.setOperacaoEfetuada(operacaoEfetuada);
		negativadorExclusaoMotivo.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(negativadorExclusaoMotivo);
		// ------------ REGISTRAR TRANSA��O ----------------

			
		negativadorExclusaoMotivo.setUltimaAlteracao(new Date());
		
		// Atualiza o negativadorContrato 
		fachada.atualizar(negativadorExclusaoMotivo);
		
		montarPaginaSucesso(httpServletRequest, "negativador Exclusao Motivo de c�digo "
				+ negativadorExclusaoMotivo.getId() + " atualizado com sucesso.",
				"Realizar outra Manuten��o de Negativador Exclusao Motivo",
				"exibirFiltrarNegativadorExclusaoMotivoAction.do?desfazer=S");
	
		return retorno;
	}
    
	
    
   
}
 