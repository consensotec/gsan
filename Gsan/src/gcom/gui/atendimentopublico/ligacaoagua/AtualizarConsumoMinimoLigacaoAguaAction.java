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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0463] Atualizar Consumo M�nimo da Liga��o de �gua
 * 
 * Apresenta��o da atualiza��o de consumo m�nimo de liga��o de �gua
 * 
 * @author Leonardo Regis
 * @date 30/08/2006
 */
public class AtualizarConsumoMinimoLigacaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarConsumoMinimoLigacaoAguaActionForm atualizarConsumoMinimoLigacaoAguaActionForm = (AtualizarConsumoMinimoLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Imovel
		Imovel imovel = new Imovel();
		imovel.setId(new Integer(atualizarConsumoMinimoLigacaoAguaActionForm
				.getMatriculaImovel()));
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(new Integer(
				atualizarConsumoMinimoLigacaoAguaActionForm
						.getConsumoTarifaId()));
		imovel.setConsumoTarifa(consumoTarifa);
		// Liga��o �gua
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(imovel.getId());
		
		if (atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado() != null && !atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado().trim().equals("")) {
			ligacaoAgua.setNumeroConsumoMinimoAgua(new Integer(
				atualizarConsumoMinimoLigacaoAguaActionForm
						.getConsumoMinimoFixado()));
		}
		
		ligacaoAgua
				.setUltimaAlteracao(atualizarConsumoMinimoLigacaoAguaActionForm
						.getDataConcorrencia());
		imovel.setLigacaoAgua(ligacaoAgua);
		// [FS0004] Validar Consumo M�nimo
		if (atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado() != null && !atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado().trim().equals("")) {
			fachada.validarConsumoMinimoLigacaoAgua(imovel);
		}

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if (atualizarConsumoMinimoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			// Efetuando Atualiza��o volume m�nimo da Liga��o de �gua
			fachada.atualizarConsumoMinimoLigacaoAgua(integracaoComercialHelper);

		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper",
					integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			// Monta a p�gina de sucesso
			montarPaginaSucesso(
					httpServletRequest,
					"Atualiza��o do Consumo M�nimo da Liga��o de �gua "
							+ ligacaoAgua.getId() + " efetuada com Sucesso",
					"Atualizar o Consumo M�nimo de outra Liga��o de �gua",
					"exibirAtualizarConsumoMinimoLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarConsumoMinimoLigacaoAguaAction.do?idOrdemServico="
							+ atualizarConsumoMinimoLigacaoAguaActionForm
									.getIdOrdemServico(),
					"Atualizar o Consumo M�nimo da Liga��o de �gua alterada");
		}

		return retorno;
	}
}