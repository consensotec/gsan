/**
 * 
 */
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 04/08/2006
 */
public class InserirTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um tipo de servi�o de refer�ncia.
	 * 
	 * [UC0436] Inserir Tipo de Servi�o de Refer�ncia
	 * 
	 * 
	 * @author R�mulo Aur�lio
	 * @date 04/08/2006
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

		//ActionForward retorno = actionMapping.findForward("tipoServicoReferenciaInserirFechar");
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirTipoServicoReferenciaActionForm inserirTipoServicoReferenciaActionForm = (InserirTipoServicoReferenciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();

		String descricao = inserirTipoServicoReferenciaActionForm
				.getDescricao();

		String abreviatura = inserirTipoServicoReferenciaActionForm
				.getAbreviatura();

		String indicadorExistenciaOsReferencia = inserirTipoServicoReferenciaActionForm
				.getIndicadorExistenciaOsReferencia();

		String situacaoOSAntes = null;

		if (indicadorExistenciaOsReferencia != null
				&& indicadorExistenciaOsReferencia
						.equals(ServicoTipoReferencia.INDICADOR_EXISTENCIA_OS_REFERENCIA_ATIVO
								.toString())) {
			if (inserirTipoServicoReferenciaActionForm.getSituacaoOSAntes() == null
					|| inserirTipoServicoReferenciaActionForm
							.getSituacaoOSAntes()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException(
						"atencao.indicador_os_incompativel_os_antes");
			}

			if (inserirTipoServicoReferenciaActionForm.getSituacaoOSApos() == null
					|| inserirTipoServicoReferenciaActionForm
							.getSituacaoOSApos()
							.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException(
						"atencao.indicador_os_incompativel_os_apos");

			}
		}
		if ( inserirTipoServicoReferenciaActionForm.getSituacaoOSAntes() != null ) {
			if (!inserirTipoServicoReferenciaActionForm.getSituacaoOSAntes()
					.toString().equals(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
	
				situacaoOSAntes =  inserirTipoServicoReferenciaActionForm
						.getSituacaoOSAntes() ;
	
				servicoTipoReferencia.setSituacaoOsReferenciaAntes(new Integer(
						situacaoOSAntes));
	
			}
		} else {
			situacaoOSAntes = "";
		}
		
		String situacaoOSApos = null;

		if ( inserirTipoServicoReferenciaActionForm.getSituacaoOSApos() != null ) {
			if (!inserirTipoServicoReferenciaActionForm.getSituacaoOSApos()
					.toString().equals(
							new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO)
									.toString())) {
				situacaoOSApos =  inserirTipoServicoReferenciaActionForm
						.getSituacaoOSApos() ;
	
				servicoTipoReferencia.setSituacaoOsReferenciaApos(new Integer(
						situacaoOSApos));
			}
		} else { 
			situacaoOSApos = "";
		}
		String tipoServico = inserirTipoServicoReferenciaActionForm
				.getTipoServico();

		// Monta o Objeto Servico Tipo Referencia

		servicoTipoReferencia.setDescricao(descricao);

		servicoTipoReferencia.setDescricaoAbreviada(abreviatura);

		servicoTipoReferencia.setIndicadorExistenciaOsReferencia(new Short(
				indicadorExistenciaOsReferencia));
		
		
		if (tipoServico != null
				&& !tipoServico.equals("")) {

			ServicoTipo servicoTipo = new ServicoTipo();

			servicoTipo.setId(new Integer(tipoServico));

			servicoTipoReferencia.setServicoTipo(servicoTipo);

		}
		
		if(inserirTipoServicoReferenciaActionForm.getIndicadorDiagnostico() != null
				&& !inserirTipoServicoReferenciaActionForm.getIndicadorDiagnostico().trim().equals("")){
			servicoTipoReferencia.setIndicadorDiagnostico(new Short(inserirTipoServicoReferenciaActionForm.getIndicadorDiagnostico()));
		}
		
		if(inserirTipoServicoReferenciaActionForm.getIndicadorFiscalizacao() != null
				&& !inserirTipoServicoReferenciaActionForm.getIndicadorFiscalizacao().trim().equals("")){
			servicoTipoReferencia.setIndicadorFiscalizacao(new Short(inserirTipoServicoReferenciaActionForm.getIndicadorFiscalizacao()));
		}

		servicoTipoReferencia.setIndicadorUso(new Short(
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		if(sessao.getAttribute("semMenu") != null){
			fachada.validarTipoServicoReferenciaParaInsercao(servicoTipoReferencia);
			sessao.setAttribute("servicoTipoReferencia", servicoTipoReferencia);
			httpServletRequest.setAttribute("fecharPopup","S");
		}else{
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			fachada.inserirTipoServicoReferencia(servicoTipoReferencia, usuarioLogado);
			
			montarPaginaSucesso(httpServletRequest,
					"Tipo de Servi�o de Refer�ncia " + descricao + " inserido com sucesso.",
					"Inserir outro Tipo de Servi�o de Refer�ncia",
					"exibirInserirTipoServicoReferenciaAction.do?menu=sim");
			
			sessao.removeAttribute("InserirTipoServicoReferenciaActionForm");
		}

		return retorno;
	}
}
