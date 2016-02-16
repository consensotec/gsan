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
package gcom.gui.mobile.execucaoordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.mobile.execucaoordemservico.bean.DadosDebitoOSCobrancaSmartphoneHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EncerrarOrdemServicoIndividualCobrancaSmartphoneAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm form = (ConsultarDadosOrdemServicoCobrancaSmartphoneActionForm) actionForm;
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Integer idArquivoTexto = new Integer(form.getIdArquivo());
		Integer idOS = new Integer(form.getOrdemServico());
		
		boolean osPendente = false;
		if (!fachada.verificarOSEncerrada(idOS)) {
			osPendente = true;
		}
		if (!osPendente) {
			throw new ActionServletException("atencao.selecione.alguma.ordem_servico.pendente");
		}
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(idOS);
//		if (sessao.getAttribute("execucaoOSFiscalizacao") != null){
//			retorno = actionMapping.findForward("fiscalizacao");
//			sessao.setAttribute("veioEncerrarSmartphone", true);
//			sessao.setAttribute("idOS", idOS);
//			sessao.setAttribute("idArquivo", idArquivoTexto);
//		}else{
			
			if (ordemServico.getServicoTipo().getDebitoTipo() != null) {
				
				DadosDebitoOSCobrancaSmartphoneHelper helperDebito = null;
				
				if (form.getMotivoNaoCobranca() != null && !form.getMotivoNaoCobranca().equals("-1")) {
					
					helperDebito = new DadosDebitoOSCobrancaSmartphoneHelper();
					
					ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
					servicoNaoCobrancaMotivo.setId(new Integer(form.getMotivoNaoCobranca()));
					
					helperDebito.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
				}
				else if (form.getPercentualCobranca() != null) {
		        	
					helperDebito = new DadosDebitoOSCobrancaSmartphoneHelper();
					
					helperDebito.setPercentualCobranca(Util.formatarMoedaRealparaBigDecimal(form.getPercentualCobranca()));
					helperDebito.setQuantidadeParcelas(Integer.valueOf(form.getQuantidadeParcelas()));
					helperDebito.setValorDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorDebito()));
		        }
				
				fachada.efetuarExecucaoOSCobrancaSmartphone(idArquivoTexto, idOS, helperDebito, usuarioLogado);
				
				// Atualiza o cliente caso necessário.
//				fachada.atualizarClienteAPartirDoDispositivoMovelCobrancaSmartphone( idArquivoTexto, idOS, usuarioLogado );
				
				montarPaginaSucesso(httpServletRequest, "Ordem de serviço encerrada com sucesso.", "Encerrar outra OS", "exibirConsultarOrdemServicoCobrancaSmartphoneAction.do?arquivoTexto=" + idArquivoTexto + "&idTipoOrdemServico=1");
			}
			else{
				
				fachada.efetuarExecucaoOSCobrancaSmartphone(idArquivoTexto, idOS, null, usuarioLogado);
				
				montarPaginaSucesso(httpServletRequest, "Ordem de serviço encerrada com sucesso.", "Encerrar outra OS", "exibirConsultarOrdemServicoCobrancaSmartphoneAction.do?arquivoTexto=" + idArquivoTexto + "&idTipoOrdemServico=1");
			}
//		}
		
		return retorno;
	}
}
