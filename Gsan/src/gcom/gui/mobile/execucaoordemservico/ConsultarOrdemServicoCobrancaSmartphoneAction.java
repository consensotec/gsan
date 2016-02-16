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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gcom.atendimentopublico.bean.AcoesParaCorrecaoAnormalidadesEncontradasHelper;
import gcom.atendimentopublico.bean.ProcessarAtualizacaoOSArquivoTextoHelper;
import gcom.atendimentopublico.ordemservico.ArquivoTextoRetornoVisitaCampo;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoRetornoVisitaCampo;
import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ConsultarOrdemServicoCobrancaSmartphoneAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ConsultarOrdemServicoCobrancaSmartphoneActionForm form = (ConsultarOrdemServicoCobrancaSmartphoneActionForm) actionForm;
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] idsOrdensServico = form.getIdsRegistros();
		
		if (idsOrdensServico != null && idsOrdensServico.length > 0) {
			Collection<ProcessarAtualizacaoOSArquivoTextoHelper> colecaoHelper = new ArrayList<ProcessarAtualizacaoOSArquivoTextoHelper>();
			
			boolean osPendente = false;
			for (int i = 0; i < idsOrdensServico.length; i++) {
				ProcessarAtualizacaoOSArquivoTextoHelper helper = new ProcessarAtualizacaoOSArquivoTextoHelper();
				Integer idOS = new Integer(idsOrdensServico[i]);
				
				if (!fachada.verificarOSEncerrada(idOS)) {
					osPendente = true;
				}
				
				Collection<AcoesParaCorrecaoAnormalidadesEncontradasHelper> colecaoAcoesCorrecaoAnormalidadesEncontradasHelper = null;
				Object[] dadosOS = fachada.pesquisarDadosOSVisitaCampo(idOS);
			    
				if (dadosOS[2] != null && ((Short)dadosOS[2]).compareTo(ConstantesSistema.NAO) == 0) {
	        		Integer idArquivoTextoRetornoVisitaCampo = (Integer) dadosOS[0];
	        		colecaoAcoesCorrecaoAnormalidadesEncontradasHelper = 
	        				fachada.pesquisarAcoesParaCorrecaoAnormalidadesEncontradas(idArquivoTextoRetornoVisitaCampo);
				}
				
				helper.setIdOrdemServico(idOS);
				String[] idsAcoes = null;
				if (colecaoAcoesCorrecaoAnormalidadesEncontradasHelper != null && !colecaoAcoesCorrecaoAnormalidadesEncontradasHelper.isEmpty()) {
					Iterator<AcoesParaCorrecaoAnormalidadesEncontradasHelper> iterator = colecaoAcoesCorrecaoAnormalidadesEncontradasHelper.iterator();
					idsAcoes = new String[colecaoAcoesCorrecaoAnormalidadesEncontradasHelper.size()];
					int j = 0;
					while(iterator.hasNext()) {
						AcoesParaCorrecaoAnormalidadesEncontradasHelper helperAcoes = (AcoesParaCorrecaoAnormalidadesEncontradasHelper) iterator.next();
						idsAcoes[j] = helperAcoes.getIdServicoTipo();
						j++;
					}
				}
				helper.setColecaoAcoesCorrecaoAnormalidadesEncontradasHelper(colecaoAcoesCorrecaoAnormalidadesEncontradasHelper);
				helper.setIdsAcoes(idsAcoes);
				
		        Integer idArquivoTexto = new Integer(sessao.getAttribute("idArquivoTexto").toString());
		        
		        FiltroArquivoTextoRetornoVisitaCampo filtro = new FiltroArquivoTextoRetornoVisitaCampo();
		        filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRetornoVisitaCampo.ID_ARQUIVO_TEXTO, idArquivoTexto));
		        filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoRetornoVisitaCampo.ID_ORDEM_SERVICO, idOS));
		        Collection<ArquivoTextoRetornoVisitaCampo> colecao = fachada.pesquisar(filtro, ArquivoTextoRetornoVisitaCampo.class.getName());
		        ArquivoTextoRetornoVisitaCampo arq = (ArquivoTextoRetornoVisitaCampo) Util.retonarObjetoDeColecao(colecao);
		        
				helper.setMatricula((Integer)dadosOS[1]);
				helper.setOrdemServicoConferida("");
				helper.setIdArquivoTexto(arq.getId());
				if (dadosOS[3] != null) {
					helper.setAnormalidadeEncontrada((Integer)dadosOS[3]);
				}
				
				colecaoHelper.add(helper);
			}
			
			if (!osPendente) {
				throw new ActionServletException("atencao.selecione.alguma.ordem_servico.pendente");
			}
			
			parametros.put("colecaoProcessarAtualizacaoOSArquivoTextoHelper", colecaoHelper);
			parametros.put("usuarioLogado", usuarioLogado);
			
		} else {
			throw new ActionServletException("atencao.selecione.alguma.ordem_servico.pendente");
		}

		fachada.inserirProcessoIniciadoParametrosLivres(parametros, Processo.PROCESSAR_ATUAL_ORDENS_SERVICO_ARQ_TXT, usuarioLogado);
		
        montarPaginaSucesso(httpServletRequest, "A atualização da(s) Ordem(ns) de Serviço(s) foi enviada para processamento.",
        		"Consultar Arquivo Texto das Ordens de Serviço de Visita", "exibirConsultarArquivoTextoOSVisitaAction.do?menu=sim");

		return retorno;
	}

}
