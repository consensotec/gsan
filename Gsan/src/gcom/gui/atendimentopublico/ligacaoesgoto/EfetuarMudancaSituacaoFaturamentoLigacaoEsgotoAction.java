/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.FiltroSupressaoMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o processamento da página de efetuar corte de ligação de
 * água
 * 
 * @author Leandro Cavalcanti
 * @date 12/07/2006
 */

public class EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm mudancaFaturamentoLigacaoAguaActionForm = 
				(EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String ordemServicoId = mudancaFaturamentoLigacaoAguaActionForm
				.getIdOrdemServico();
		
		if(sessao.getAttribute("colecaoMotivo") != null && 
				mudancaFaturamentoLigacaoAguaActionForm.getMotivoCorteSupressao() == null){
			 Collection colecaoMotivoCorteSupressao = (Collection) sessao.getAttribute("colecaoMotivo");
			 
			 String motivoCorteSupressao =  (String) Util.retonarObjetoDeColecao(colecaoMotivoCorteSupressao);
			 mudancaFaturamentoLigacaoAguaActionForm.setMotivoCorteSupressao(motivoCorteSupressao);
		}
		// String volumeMinimoFixado =
		// mudancaFaturamentoLigacaoAguaActionForm.getVolumeMinimoFixado();
		// String novaSituacaoEsgoto =
		// mudancaFaturamentoLigacaoAguaActionForm.getNovaSitLigacaoEsgoto();
		// String tipoServico =
		// mudancaFaturamentoLigacaoAguaActionForm.getTipoServico();
		String idServicoMotivoNaoCobranca = mudancaFaturamentoLigacaoAguaActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = mudancaFaturamentoLigacaoAguaActionForm
				.getPercentualCobranca();		

		OrdemServico ordemServico = null;
		// Validar Ordem de Serviço
		if (ordemServicoId != null && !ordemServicoId.equals("")) {
			ordemServico = (OrdemServico) sessao.getAttribute("ordemServico");
			if (ordemServico == null) {
				throw new ActionServletException(
						"atencao.ordem_servico_inexistente", null,
						"ORDEM DE SERVIÇO INEXISTENTE");

			}else{
				if((ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO)
						|| ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO)) 
						&& mudancaFaturamentoLigacaoAguaActionForm.getMotivoCorteSupressao() == null){
					throw new ActionServletException(
						"atencao.informe_campo_obrigatorio", null,
						"Motivo Corte Supressao");
				}
			}
		}	 
		 
		 
		/*---------------------  Início Dados do Corte da Ligação de Água  ------------------------*/

		// Validar Data da Mudança recebida do encerramento da Ordem de Serviço.
		// Date dataMudanca = null;
		// dataMudanca =
		// Util.converteStringParaDate(mudancaFaturamentoLigacaoAguaActionForm
		// .getDataMudanca());


		if (ordemServico != null
				&& mudancaFaturamentoLigacaoAguaActionForm.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(new Short("1"));

			if (idServicoMotivoNaoCobranca != null) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						mudancaFaturamentoLigacaoAguaActionForm
								.getPercentualCobranca()));
			}

			BigDecimal valorAtual = new BigDecimal(0);
			if (mudancaFaturamentoLigacaoAguaActionForm.getValorDebito() != null 
					&& !mudancaFaturamentoLigacaoAguaActionForm.getValorDebito().equals("")) {
				String valorDebito = mudancaFaturamentoLigacaoAguaActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}	
		}

		String qtdParcelas = mudancaFaturamentoLigacaoAguaActionForm
				.getQuantidadeParcelas();

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();		
		
		LigacaoEsgoto ligacaoEsgoto = new LigacaoEsgoto();
		ligacaoEsgoto = (LigacaoEsgoto) sessao.getAttribute("ligacaoEsgoto");			
		
		if(ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_DESATIVACAO_LIGACAO_ESGOTO) &&
				mudancaFaturamentoLigacaoAguaActionForm.getMotivoCorteSupressao() != null){
			
			MotivoCorte motivoCorte = new MotivoCorte();
			FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();
			filtroMotivoCorte.adicionarParametro(new ParametroSimples(FiltroMotivoCorte.ID, 
				mudancaFaturamentoLigacaoAguaActionForm.getMotivoCorteSupressao()));
			Collection colecaoMotivoCorte = fachada.pesquisar(filtroMotivoCorte, 
				MotivoCorte.class.getName());
			
			if(!Util.isVazioOrNulo(colecaoMotivoCorte)){
				motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);				
				ligacaoEsgoto.setMotivoCorte(motivoCorte);
				if (ordemServico.getDataEncerramento() != null && !ordemServico.getDataEncerramento().equals("")){
					ligacaoEsgoto.setDataCorteLigacaoEsgoto(ordemServico.getDataEncerramento());
				}else{
					ligacaoEsgoto.setDataCorteLigacaoEsgoto(Util.converteStringParaDate(mudancaFaturamentoLigacaoAguaActionForm.getDataMudanca()));
				}
			}	
			
		}else if(ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_TAMPONAMENTO_LIGACAO_ESGOTO) &&
				mudancaFaturamentoLigacaoAguaActionForm.getMotivoCorteSupressao() != null){

			SupressaoMotivo supressaoMotivo = new SupressaoMotivo();
			FiltroSupressaoMotivo filtroSupressaoMotivo = new FiltroSupressaoMotivo();
			filtroSupressaoMotivo.adicionarParametro(new ParametroSimples(FiltroSupressaoMotivo.ID, 
				mudancaFaturamentoLigacaoAguaActionForm.getMotivoCorteSupressao()));
			Collection colecaoMotivoSupressao = fachada.pesquisar(filtroSupressaoMotivo, 
				SupressaoMotivo.class.getName());
			if(!Util.isVazioOrNulo(colecaoMotivoSupressao)){
				supressaoMotivo = (SupressaoMotivo) Util.retonarObjetoDeColecao(colecaoMotivoSupressao);
				ligacaoEsgoto.setSupressaoMotivo(supressaoMotivo);
				if (ordemServico.getDataEncerramento() != null && !ordemServico.getDataEncerramento().equals("")){
					ligacaoEsgoto.setDataSupressaoLigacaoEsgoto(ordemServico.getDataEncerramento());
				}else{
					ligacaoEsgoto.setDataSupressaoLigacaoEsgoto(Util.converteStringParaDate(mudancaFaturamentoLigacaoAguaActionForm.getDataMudanca()));
				}
				
			}
		}
		
		integracaoComercialHelper.setLigacaoEsgoto(ligacaoEsgoto);		
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		integracaoComercialHelper.setUsuarioLogado(usuario);		

		if (mudancaFaturamentoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada
					.efetuarMudancaSituacaoFaturamentoLiagacaoEsgoto(integracaoComercialHelper);
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

		/*
		 * if(ordemServico.getServicoTipo().getDebitoTipo() != null &&
		 * (ordemServico.getServicoNaoCobrancaMotivo() == null ||
		 * ordemServico.getServicoNaoCobrancaMotivo().getId() ==
		 * ConstantesSistema.NUMERO_NAO_INFORMADO)){
		 * fachada.gerarDebitoOrdemServico(ordemServico.getId(),
		 * ordemServico.getServicoTipo().getDebitoTipo().getId(),
		 * Util.calcularValorDebitoComPorcentagem(valorAtual,
		 * mudancaFaturamentoLigacaoAguaActionForm.getPercentualCobranca()) ,
		 * new
		 * Integer(mudancaFaturamentoLigacaoAguaActionForm.getQuantidadeParcelas()).intValue()); }
		 */

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			// Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Mudança de Faturamento da Ligação de Esgoto"
							+ " efetuada com Sucesso", "",
					"exibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction.do");
		}

		return retorno;
	}
}
