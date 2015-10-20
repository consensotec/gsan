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
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o processamento da p�gina de efetuar corte de liga��o de
 * �gua
 * 
 * @author Thiago Ten�rio
 * @date 12/07/2006
 */

public class EfetuarCorteAdministrativoLigacaoAguaAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = getSessao(httpServletRequest);
		EfetuarCorteAdministrativoLigacaoAguaActionForm corteAdministrativoLigacaoAguaActionForm = (EfetuarCorteAdministrativoLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String tipoCorteId = corteAdministrativoLigacaoAguaActionForm
				.getTipoCorte();

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");
		
		//Comentado por Raphael Rossiter em 28/02/2007
		//Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel = ordemServico.getImovel();

		// String idServicoMotivoNaoCobranca =
		// corteAdministrativoLigacaoAguaActionForm
		// .getMotivoNaoCobranca();
		// String valorPercentual = corteAdministrativoLigacaoAguaActionForm
		// .getPercentualCobranca();

		/*---------------------  In�cio Dados do Corte da Liga��o de �gua  ------------------------*/
		// validar Data Corte
		Date dataCorteAdministrativo = null;
		if (corteAdministrativoLigacaoAguaActionForm.getDataCorte() != null
				&& corteAdministrativoLigacaoAguaActionForm.getDataCorte() != "") {

			dataCorteAdministrativo = Util
					.converteStringParaDate(corteAdministrativoLigacaoAguaActionForm
							.getDataCorte());
		} else {
			throw new ActionServletException("atencao.required", null,
					" Data do Corte");
		}
		// Validar Tipo do Corte
		CorteTipo corteTipo = new CorteTipo();
		corteTipo.setId(new Integer(tipoCorteId));

		LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

		ligacaoAgua.setDataCorteAdministrativo(dataCorteAdministrativo);
		ligacaoAgua.setCorteTipo(corteTipo);
		ligacaoAgua.setUltimaAlteracao(new Date());

		ordemServico.setIndicadorComercialAtualizado(new Short("1"));
		ordemServico.setUltimaAlteracao(new Date());
		ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
		if (corteAdministrativoLigacaoAguaActionForm.getMotivoNaoCobranca() != null
				&& !corteAdministrativoLigacaoAguaActionForm
						.getMotivoNaoCobranca().equals("-1")) {
			servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
			servicoNaoCobrancaMotivo.setId(new Integer(
					corteAdministrativoLigacaoAguaActionForm
							.getMotivoNaoCobranca()));
		}
		ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

		BigDecimal valorAtual = new BigDecimal(0);
		if (corteAdministrativoLigacaoAguaActionForm.getValorDebito() != null) {
			String valorDebito = corteAdministrativoLigacaoAguaActionForm
					.getValorDebito().toString().replace(".", "");

			valorDebito = valorDebito.replace(",", ".");

			valorAtual = new BigDecimal(valorDebito);

			ordemServico.setValorAtual(valorAtual);
		}

		if (corteAdministrativoLigacaoAguaActionForm.getPercentualCobranca() != null
				&& !corteAdministrativoLigacaoAguaActionForm
						.getPercentualCobranca().equals("")) {
			ordemServico.setPercentualCobranca(new BigDecimal(
					corteAdministrativoLigacaoAguaActionForm
							.getPercentualCobranca()));
		}
//		ordemServico
//				.setUltimaAlteracao(corteAdministrativoLigacaoAguaActionForm
//						.getDataConcorrencia());

		// Preenche Helper
		DadosEfetuacaoCorteLigacaoAguaHelper dadosHelper = new DadosEfetuacaoCorteLigacaoAguaHelper();
		dadosHelper.setLigacaoAgua(ligacaoAgua);
		dadosHelper.setOrdemServico(ordemServico);
		if (corteAdministrativoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("true")) {
			dadosHelper.setVeioEncerrarOS(true);
		} else {
			dadosHelper.setVeioEncerrarOS(false);
		}
		if (corteAdministrativoLigacaoAguaActionForm.getQuantidadeParcelas() != null
				&& !corteAdministrativoLigacaoAguaActionForm
						.getQuantidadeParcelas().equals("")) {
			dadosHelper.setQtdeParcelas(new Integer(
					corteAdministrativoLigacaoAguaActionForm
							.getQuantidadeParcelas()).intValue());
		} else {
			dadosHelper.setQtdeParcelas(0);
		}
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setDadosEfetuacaoCorteLigacaoAguaHelper(dadosHelper);
		if(corteAdministrativoLigacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarCorteAdministrativoLigacaoAgua(dadosHelper, usuario);
		}else{
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
			
			if(sessao.getAttribute("semMenu") == null){
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			}else{
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		
		
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){

		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Corte Administrativo da Liga��o de �gua do im�vel "
						+ imovel.getId() + " efetuada com Sucesso ",
				"Efetuar outro Corte Administrativo da Liga��o de �gua",
				"exibirEfetuarCorteAdministrativoLigacaoAguaAction.do?reset=true");
		}

		return retorno;
	}
}