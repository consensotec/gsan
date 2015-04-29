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
package gsan.gui.atendimentopublico.ligacaoagua;

import gsan.atendimentopublico.bean.IntegracaoComercialHelper;
import gsan.atendimentopublico.ligacaoagua.CorteTipo;
import gsan.atendimentopublico.ligacaoagua.LigacaoAgua;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.MotivoCorte;
import gsan.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
*  Action que define o processamento da p�gina de efetuar corte de liga��o de �gua
*
* @author Leandro Cavalcanti 
* @date	  12/07/2006
* 
* Refeito
* @author Leonardo Regis
* @date 27/09/2006
*/
public class EfetuarCorteLigacaoAguaAction extends GcomAction {

	/**
	 * Efetuar Corte Liga��o �gua
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		EfetuarCorteLigacaoAguaActionForm corteLigacaoAguaActionForm = 
			(EfetuarCorteLigacaoAguaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
			
		if (corteLigacaoAguaActionForm.getIdOrdemServico() != null && !corteLigacaoAguaActionForm.getIdOrdemServico().trim().equals("")) {

			OrdemServico ordemServico = new OrdemServico();
			Imovel imovel = new Imovel();
			LigacaoAgua ligacaoAgua = new LigacaoAgua();
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = null;
			if (sessao.getAttribute("ordemServico") != null) {
				ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			}
			
			// Imovel
			//Comentado por Raphael Rossiter em 28/02/2007
			//imovel = ordemServico.getRegistroAtendimento().getImovel();
			imovel = ordemServico.getImovel();
			
			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao.setId(LigacaoAguaSituacao.CORTADO);
			imovel.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			imovel.setUltimaAlteracao(new Date());
			// Liga��o �gua
			ligacaoAgua.setId(imovel.getId());
			Date dataCorte = null;
			if (corteLigacaoAguaActionForm.getDataCorte() != null && !corteLigacaoAguaActionForm.getDataCorte().trim().equals("")) {
				dataCorte = Util.converteStringParaDate(corteLigacaoAguaActionForm.getDataCorte());
			} else {
				throw new ActionServletException("atencao.required", null," Data do Corte");
			}
			ligacaoAgua.setDataCorte(dataCorte);
			if (corteLigacaoAguaActionForm.getNumSeloCorte()!= null && !corteLigacaoAguaActionForm.getNumSeloCorte().trim().equals("")){
				ligacaoAgua.setNumeroSeloCorte(new Integer(corteLigacaoAguaActionForm.getNumSeloCorte()));
			}else{
				ligacaoAgua.setNumeroSeloCorte(null);
			}
			CorteTipo corteTipo = new CorteTipo();
			corteTipo.setId(new Integer(corteLigacaoAguaActionForm.getTipoCorte()));
			ligacaoAgua.setCorteTipo(corteTipo);
			MotivoCorte motivoCorte = new MotivoCorte();
			motivoCorte.setId(new Integer(corteLigacaoAguaActionForm.getMotivoCorte()));
			ligacaoAgua.setMotivoCorte(motivoCorte);
			ligacaoAgua.setUltimaAlteracao(new Date());

			// Hidrometro Instala��o Hist�rico
			if (imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null) {
				hidrometroInstalacaoHistorico = imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
				//Validar N�mero de Leitura do Corte / N�mero do Selo de Corte
				if (corteLigacaoAguaActionForm.getNumLeituraCorte()!= null && !corteLigacaoAguaActionForm.getNumLeituraCorte().trim().equals("")){
					hidrometroInstalacaoHistorico.setNumeroLeituraCorte(new Integer(corteLigacaoAguaActionForm.getNumLeituraCorte()));
				}else{
					hidrometroInstalacaoHistorico.setNumeroLeituraCorte(null);
				}
				hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
			}
			ligacaoAgua.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			imovel.setLigacaoAgua(ligacaoAgua);

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));
			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
			if(corteLigacaoAguaActionForm.getMotivoNaoCobranca() != null &&	!corteLigacaoAguaActionForm.getMotivoNaoCobranca().equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(corteLigacaoAguaActionForm.getMotivoNaoCobranca()));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
			
			BigDecimal valorAtual = new BigDecimal(0);
			if (corteLigacaoAguaActionForm.getValorDebito() != null) {
			    String valorDebito = corteLigacaoAguaActionForm
			     	.getValorDebito().toString().replace(".", "");
			    
			    valorDebito = valorDebito.replace(",", ".");
			    
			    valorAtual = new BigDecimal(valorDebito);

			    ordemServico.setValorAtual(valorAtual);
			}
			
			if(corteLigacaoAguaActionForm.getPercentualCobranca() != null && 
					!corteLigacaoAguaActionForm.getPercentualCobranca().trim().equals("")) {
				ordemServico.setPercentualCobranca(new BigDecimal(corteLigacaoAguaActionForm.getPercentualCobranca()));	
			}
			ordemServico.setUltimaAlteracao(new Date());
			
			// Preenche Helper
			DadosEfetuacaoCorteLigacaoAguaHelper dadosHelper = new DadosEfetuacaoCorteLigacaoAguaHelper();
			dadosHelper.setImovel(imovel);
			dadosHelper.setLigacaoAgua(ligacaoAgua);
			dadosHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
			dadosHelper.setOrdemServico(ordemServico);
			if(corteLigacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("true")){
				dadosHelper.setVeioEncerrarOS(true);
			} else {
				dadosHelper.setVeioEncerrarOS(false);
			}
			if (corteLigacaoAguaActionForm.getQuantidadeParcelas() != null &&
					!corteLigacaoAguaActionForm.getQuantidadeParcelas().trim().equals("")) {
				dadosHelper.setQtdeParcelas(new Integer(corteLigacaoAguaActionForm.getQuantidadeParcelas()).intValue());
			} else {
				dadosHelper.setQtdeParcelas(0);
			}
			
			integracaoComercialHelper.setDadosEfetuacaoCorteLigacaoAguaHelper(dadosHelper);
			integracaoComercialHelper.setOrdemServico(ordemServico);
			integracaoComercialHelper.setUsuarioLogado(usuario);
			//efetuar Corte Liga��o de �gua
			
			if(corteLigacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
				integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
				
				fachada.efetuarCorteLigacaoAgua(integracaoComercialHelper);
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

				montarPaginaSucesso(httpServletRequest,"Corte de Liga��o de �gua do im�vel "+imovel.getId()+" efetuada com Sucesso",
					"Efetuar outra Corte de Liga��o de �gua",
					"exibirEfetuarCorteLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarCorteLigacaoAguaAction.do?idOrdemServico"+ordemServico.getId(),
					"Atualiza��o Corte Liga��o de �gua efetuada");
			}
			
			return retorno;
		} else {
			throw new ActionServletException(
					"atencao.required", null,"Ordem de Servi�o");
		}
	}

}
