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
package gsan.gui.cobranca;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.batch.Processo;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1233] - Encerrar Ordem de Servico de Visita de Cobrança
 * 
 * @author Hugo Azevedo
 * @date 23/09/2011
 */
public class EncerrarOrdensVisitaCobrancaAction extends GcomAction {
 
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		EncerrarOrdensVisitaCobrancaActionForm form = (EncerrarOrdensVisitaCobrancaActionForm) actionForm;
		
		Collection<OrdemServico> colecaoOrdemServico = new ArrayList<OrdemServico>();
		
		String idGrupo = form.getIdGrupo();
		String mesAno = form.getMesano();
		
		//Indicador de confirmação
		String confirmado = httpServletRequest.getParameter("confirmado");
		
		//Retirando a barra do mes/ano
		mesAno = Util.formatarMesAnoParaAnoMesSemBarra(mesAno);
		
		if( confirmado == null	|| !confirmado.trim().equalsIgnoreCase("ok")){
			//Busca das Ordens de Servico de Visita de Cobranca
			colecaoOrdemServico = fachada.obterColecaoOrdemServicoVisitaCobranca(idGrupo,mesAno);
			
			
			if(colecaoOrdemServico.size() > 0){
	
				httpServletRequest.setAttribute("caminhoActionConclusao",
						"/gsan/encerrarOrdensVisitaCobrancaAction.do");
				
				sessao.setAttribute("colecaoOrdemServico", colecaoOrdemServico);
				
				//Tela de confirmação
				return montarPaginaConfirmacao(
						"atencao.encerrar_os_visita_cobranca.confirmar",
						httpServletRequest, actionMapping,colecaoOrdemServico.size()+"");
			}
			else{
				throw new ActionServletException("atencao.nenhuma_os_encontrada");
			}
		}
	
		colecaoOrdemServico = (Collection<OrdemServico>)sessao.getAttribute("colecaoOrdemServico");
		sessao.removeAttribute("colecaoOrdemServico");
		
		Map parametros = new HashMap();
		parametros.put("colecaoOrdemServico", colecaoOrdemServico);
	
		
		fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
          		Processo.ENCERRAR_OS_ORDEM_VISITA, usuarioLogado);
		
		
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Encerramento do Comando de Cobrança por Empresa Enviado para Processamento", 
			"Voltar",
			"exibirEncerrarOrdensVisitaCobrancaAction.do?menu=sim");
		
		return retorno;
	}
}