/*
* Copyright (C) 2007-2007 the GSAN ? Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place ? Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN ? Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gcom.gui.seguranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.ConsultaCadastroCdlInformacoesArmazenadasActionForm;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 *
 * @author Rodrigo Cabral
 * @date 08/11/2010
 */

public class ExibirConsultaCadastroCdlInformacoesArmazenadasAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultaCadastroCdlInformacoesArmazenadas");				
		
		ConsultaCadastroCdlInformacoesArmazenadasActionForm form = (ConsultaCadastroCdlInformacoesArmazenadasActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = null;
		
		form.setLoginUsuario("");
		form.setCodigoCliente("");
		form.setCpfCliente("");
		form.setCnpjCliente("");
		form.setNomeCliente("");
		form.setLogradouroCliente("");
		form.setNumeroImovelCliente("");
		form.setBairroCliente("");
		form.setComplementoEnderecoCliente("");
		form.setCidadeCliente("");
		form.setCepCliente("");
		form.setCodigoDdd("");
		form.setTelefoneCliente("");
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			codigo = httpServletRequest.getParameter("idRegistroAtualizacao");
		} else {
			codigo = ""+((ConsultaCdl)sessao.getAttribute("consultaCadastroCdlInformacoesArmazenadas")).getId();
		}
		
		if (httpServletRequest.getParameter("menu") != null) {
			sessao.setAttribute("menu", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("menu");
		}
		
		if (codigo == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				codigo = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				codigo = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		ConsultaCdl consultaCdl = null;
		
		if (codigo != null && !codigo.trim().equals("") && Integer.parseInt(codigo) > 0) {
		
			FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();
			
			filtroConsultaCadastroCdl.adicionarParametro(new ParametroSimples(FiltroConsultaCadastroCdl.ID, codigo));
			
			Collection colecaoConsultaCdl = fachada.pesquisar
				(filtroConsultaCadastroCdl,ConsultaCdl.class.getName());
			
			if(colecaoConsultaCdl != null && !colecaoConsultaCdl.isEmpty()){
				
				consultaCdl = (ConsultaCdl)Util.retonarObjetoDeColecao(colecaoConsultaCdl);
				
				if (consultaCdl.getLoginUsuario() != null){
					form.setLoginUsuario(consultaCdl.getLoginUsuario());
				}
				
				if (consultaCdl.getCodigoCliente() != null){
					form.setCodigoCliente(consultaCdl.getCodigoCliente().getId().toString());
				}
				
				if (consultaCdl.getCpfCliente() != null){
					form.setCpfCliente(Util.formatarCpf(consultaCdl.getCpfCliente()));
					
					if (consultaCdl.getCodigoDddResidencial() != null){
						form.setCodigoDdd(consultaCdl.getCodigoDddResidencial());
					}
				
					if (consultaCdl.getTelefoneResidencialCliente() != null){
						form.setTelefoneCliente(consultaCdl.getTelefoneResidencialCliente());
					}
				}
				
				if (consultaCdl.getCnpjCliente() != null){
					form.setCnpjCliente(Util.formatarCnpj(consultaCdl.getCnpjCliente()));
					
					if (consultaCdl.getCodigoDddComercial() != null){
						form.setCodigoDdd(consultaCdl.getCodigoDddComercial());
					}
				
					if (consultaCdl.getTelefoneComercialCliente() != null){
						form.setTelefoneCliente(consultaCdl.getTelefoneComercialCliente());
					}
				}
				
				if (consultaCdl.getNomeCliente() != null){
					form.setNomeCliente(consultaCdl.getNomeCliente());
				}
				
				if (consultaCdl.getLogradouroCliente() != null){
					form.setLogradouroCliente(consultaCdl.getLogradouroCliente());
				}
				
				if (consultaCdl.getNumeroImovelCliente() != null){
					form.setNumeroImovelCliente(consultaCdl.getNumeroImovelCliente().toString());
				}
				
				if (consultaCdl.getBairroCliente() != null){
					form.setBairroCliente(consultaCdl.getBairroCliente());
				}
				
				if (consultaCdl.getComplementoEnderecoCliente() != null){
					form.setComplementoEnderecoCliente(consultaCdl.getComplementoEnderecoCliente());
				}
				
				if (consultaCdl.getCidadeCliente() != null){
					form.setCidadeCliente(consultaCdl.getCidadeCliente());
				}
				
				if (consultaCdl.getCepCliente() != null){
					form.setCepCliente(Util.formatarCEP( consultaCdl.getCepCliente().toString()));
				}
				
			}
				
			
			
			sessao.setAttribute("consultaCadastroCdlInformacoesArmazenadas", consultaCdl);

			if (sessao.getAttribute("colecaoConsultaCdl") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarConsultaCadastroCdlAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarConsultaCadastroCdlAction.do");
			}
			
		}

		return retorno;
	
	}

}