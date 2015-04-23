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
package gsan.gui.atendimentopublico.ordemservico;

import java.util.Collection;
import java.util.Iterator;

import gsan.atendimentopublico.bean.OrdemServicoArquivoTextoHelper;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1228] Consultar Ordens de Serviço do Arquivo Texto
 * 
 * @author Mariana Victor
 * @date 22/09/2011
 * 
 */
public class ExibirConsultarOrdemServicoDoArquivoTextoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarOrdemServicoDoArquivoTexto");
		
		ConsultarOrdemServicoDoArquivoTextoActionForm form = 
			(ConsultarOrdemServicoDoArquivoTextoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Integer idArquivoTexto = null;
		
		if (httpServletRequest.getParameter("arquivoTexto") != null 
				&& !httpServletRequest.getParameter("arquivoTexto").toString().trim().equals("")) {
			
			idArquivoTexto = new Integer(httpServletRequest.getParameter("arquivoTexto"));
			sessao.setAttribute("idArquivoTexto", idArquivoTexto);
			
		} else if (sessao.getAttribute("idArquivoTexto") != null 
				&& !sessao.getAttribute("idArquivoTexto").toString().trim().equals("")) {
			
			idArquivoTexto = new Integer(sessao.getAttribute("idArquivoTexto").toString());
			
		} else {
			
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", 
				"Arquivo Texto");
			
		}
		
		// 1. O sistema exibe as informações do arquivo texto;
		this.carregarCampos(form, idArquivoTexto, sessao);
		
		return retorno;
	}
	
	public void carregarCampos(ConsultarOrdemServicoDoArquivoTextoActionForm form, 
			Integer idArquivoTexto, HttpSession sessao){
		Fachada fachada = Fachada.getInstancia();
		
		// 1. O sistema exibe as informações do arquivo texto
		Object[] dadosArquivoTxt = fachada.
			pesquisarDadosArquivoTextoOSVisita(idArquivoTexto);

		if (dadosArquivoTxt[0] != null) {
			String comando = (String) dadosArquivoTxt[0];
			form.setDescricaoComando(comando.toString());
		}
		
		// 1.1.	Código e Descrição da Localidade
		if (dadosArquivoTxt[1] != null) {
			Integer idLocalidade = (Integer) dadosArquivoTxt[1];
			form.setIdLocalidade(idLocalidade.toString());
		}
		if (dadosArquivoTxt[2] != null) {
			String nomeLocalidade = (String) dadosArquivoTxt[2];
			form.setDescricaoLocalidade(nomeLocalidade.toString());
		}
		
		// 1.2. Código do Setor Comercial Inicial 
		if (dadosArquivoTxt[3] != null) {
			Integer codigoSetorInicial = (Integer) dadosArquivoTxt[3];
			form.setDescricaoSetorComercialInicial(codigoSetorInicial.toString());
		}
		
		// 1.3. Código do Setor Comercial Final 
		if (dadosArquivoTxt[4] != null) {
			Integer codigoSetorFinal = (Integer) dadosArquivoTxt[4];
			form.setDescricaoSetorComercialFinal(codigoSetorFinal.toString());
		}
		
		// 1.4.	Código da Quadra Inicial
		if (dadosArquivoTxt[5] != null) {
			Integer codigoQuadraInicial = (Integer) dadosArquivoTxt[5];
			form.setNumeroQuadraInicial(codigoQuadraInicial.toString());
		}
		
		// 1.5.	Código da Quadra Final 
		if (dadosArquivoTxt[6] != null) {
			Integer codigoQuadraFinal = (Integer) dadosArquivoTxt[6];
			form.setNumeroQuadraFinal(codigoQuadraFinal.toString());
		}
		
		// 1.6.	Agente Comercial 
		if (dadosArquivoTxt[7] != null) { 
			String agenteComercial = (String) dadosArquivoTxt[7];
			form.setAgenteComercial(agenteComercial);
		}
		
		
		// 2. O sistema deverá exibir a lista das ordens de serviço associadas ao arquivo texto 
		Collection<OrdemServicoArquivoTextoHelper> colecaoOrdemServicoArquivoTextoHelper = fachada.
			pesquisarDadosOrdensServicoArquivoTextoVisitaCampo(idArquivoTexto);
		
		sessao.setAttribute("colecaoOrdemServicoArquivoTextoHelper", colecaoOrdemServicoArquivoTextoHelper);
		
		// 3.1.	Caso todas as ordens de serviço estejam "Pendentes", 
		//   o sistema deverá disponibilizar o botão de "Atualizar Ordem de Serviço". 
		if (colecaoOrdemServicoArquivoTextoHelper != null 
				&& !colecaoOrdemServicoArquivoTextoHelper.isEmpty()) {
			Iterator iterator = colecaoOrdemServicoArquivoTextoHelper.iterator();
			boolean ordemServicoPendente = false;
			
			while(iterator.hasNext()) {
				OrdemServicoArquivoTextoHelper helper = (OrdemServicoArquivoTextoHelper) iterator.next();
				
				if (helper.getCodigoSituacao().trim().equalsIgnoreCase("1")) {
					ordemServicoPendente = true;
					break;
				}
			}
			
			if (ordemServicoPendente) {
				sessao.setAttribute("ordemServicoPendente", true);
			} else {
				sessao.removeAttribute("ordemServicoPendente");
			}
		} else {
			sessao.removeAttribute("ordemServicoPendente");
		}
	}
}


