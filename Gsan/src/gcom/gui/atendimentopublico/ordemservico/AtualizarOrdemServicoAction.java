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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descri��o da Classe>>
 * 
 * @author lms
 * @date 22/08/2006
 */
public class AtualizarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarOrdemServicoActionForm form = (AtualizarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		OrdemServico ordemServico = form.setFormValues(form.getOrdemServico());

		// pesquisa do enter servi�o tipo
		String idServicoTipo = form.getIdServicoTipo();
		String descricaoServicoTipo = form.getDescricaoServicoTipo();
		
		if (idServicoTipo != null
				&& !idServicoTipo.equals("")
				&& (descricaoServicoTipo == null || descricaoServicoTipo
						.equals(""))) {
			Integer idServicoTipoInteger = Util.converterStringParaInteger(form
					.getIdServicoTipo());

			ServicoTipo servicoTipo = fachada
					.pesquisarSevicoTipo(idServicoTipoInteger);

			if (servicoTipo != null && !servicoTipo.equals("")) {
				ordemServico.setServicoTipo(servicoTipo);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Tipo Servi�o");
			}
		}
		
		if(ordemServico.getObservacao() != null && 
			!ordemServico.getObservacao().equals("") && 
			ordemServico.getObservacao().length() > 200){
					
			String[] msg = new String[2];
			msg[0]="Observa��o";
			msg[1]="200";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}					

		// pesquisa do enter Ordem de Servi�o Refer�ncia
		String idOSReferencia = form.getIdOrdemServicoReferencia();
		String descricaoOsReferencia = form
				.getDescricaoOrdemServicoReferencia();
		if (idOSReferencia != null
				&& !idOSReferencia.equals("")
				&& (descricaoOsReferencia == null || descricaoOsReferencia
						.equals(""))) {
			Integer idOrdemServicoReferencia = Util
					.converterStringParaInteger(form
							.getIdOrdemServicoReferencia());

			OrdemServico os = fachada
					.pesquisarOrdemServico(idOrdemServicoReferencia);

			if (os != null && !os.equals("")) {
				ordemServico.setOsReferencia(os);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Ordem Servi�o Refer�ncia");

			}
		}

		// pesquisa do enter Servi�o Tipo Refer�ncia
		String idServicoTipoReferencia = form.getIdServicoTipoReferencia();
		String descricaoServicoTipoReferencia = form
				.getDescricaoServicoTipoReferencia();
		if (idServicoTipoReferencia != null
				&& !idServicoTipoReferencia.equals("")
				&& (descricaoServicoTipoReferencia == null || descricaoServicoTipoReferencia
						.equals(""))) {

			Integer idTipoServicoReferenciaInteger = Util
					.converterStringParaInteger(form
							.getIdServicoTipoReferencia());

			ServicoTipo st = fachada
					.pesquisarSevicoTipo(idTipoServicoReferenciaInteger);
			if (st != null && !st.equals("")) {
				ordemServico.setServicoTipoReferencia(st);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Servi�o Tipo Refer�ncia");
			}
		}

		String veioAcompanhamentoRoteiro = (String) sessao
				.getAttribute("veioAcompanhamentoRoteiro");		
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			if (ordemServico.getServicoTipo() != null &&
					ordemServico.getServicoTipo().getIndicadorProgramacaoAutomatica() != ConstantesSistema.SIM.shortValue()){
				throw new ActionServletException("atencao.servico_tipo.nao_compativel.atualizar_os");
			}
		}
		
		// gera a ordem de servi�o
		fachada.atualizarOrdemServico(ordemServico, usuarioLogado);

		if (sessao.getAttribute("ehPopup") == null
				|| sessao.getAttribute("ehPopup").equals("")) {
			// Exibe a p�gina de sucesso
			String mensagem = "";
			if(ordemServico.getRegistroAtendimento() != null && ordemServico.getRegistroAtendimento().getId() != null ){
				mensagem = "Ordem de Servi�o "
					+ ordemServico.getId()
					+ " para o registro de Atendimento n�mero "
					+ ordemServico.getRegistroAtendimento().getId()
					+ " atualizada com sucesso.";
			}else if (ordemServico.getCobrancaDocumento() != null && ordemServico.getCobrancaDocumento().getId() != null ){
				mensagem = "Ordem de Servi�o "
					+ ordemServico.getId()
					+ " para o Documento de Cobran�a n�mero "
					+ ordemServico.getCobrancaDocumento().getId()
					+ " atualizada com sucesso.";
			} else {
				mensagem = "Ordem de Servi�o "
					+ ordemServico.getId()
					+ " atualizada com sucesso.";
			}
			
			if (sessao.getAttribute("importarMovimentoACQUAGIS") != null &&
					sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
				
				montarPaginaSucesso(httpServletRequest,  mensagem,
						"Voltar",
						sessao.getAttribute("caminhoRetornoOS").toString());
				
			}else {
				montarPaginaSucesso(httpServletRequest,  mensagem, "Realizar outra Manuten��o de Ordem de Servi�o",
						"exibirFiltrarOrdemServicoAction.do?menu=sim",
						"exibirConsultarDadosOrdemServicoAction.do?menu=sim&numeroOS=" + ordemServico.getId(), 
						"Voltar");
			}
			
		} else {
			// volta para tela e limpa o popup
			httpServletRequest.setAttribute("fecharPopup", "SIM");
			retorno = actionMapping.findForward("exibirAtualizarOrdemServico");
		}
		
		String dataProgramacao = (String) sessao.getAttribute("dataProgramacaoAtualizar");
		String chaveArquivo = (String) sessao.getAttribute("chaveArquivoAtualizar");
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			fachada.atualizarOSProgramacaoAcompServico(Util.converterStringParaInteger(chaveArquivo), Util.converteStringParaDate(dataProgramacao), ordemServico.getId(),
					ordemServico.getServicoTipo().getId());
		}
		
		sessao.removeAttribute("dataProgramacaoAtualizar");
		sessao.removeAttribute("chaveArquivoAtualizar");
		
		sessao.removeAttribute("ehPopup");
		sessao.removeAttribute("servicoTipo");
		sessao.removeAttribute("colecaoServicoTipoPrioridade");
		sessao.removeAttribute("colecaoServicosTipo");
		
		return retorno;
	}
}