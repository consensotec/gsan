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

import gsan.atendimentopublico.ordemservico.Equipe;
import gsan.atendimentopublico.ordemservico.FiltroEquipe;
import gsan.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarDadosEquipeAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarDadosEquipe");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		ConsultarDadosEquipeActionForm 
			consultarDadosEquipeActionForm = (ConsultarDadosEquipeActionForm) actionForm;
		
		if(httpServletRequest.getParameter("ehPopup") != null){
			sessao.setAttribute("ehPopup","true");	
		}
		
		Integer idEquipe = null;
		Equipe equipe = null;
		ObterDadosEquipe obterDadosEquipe = null;
		
		//Caso venha da tela de ordem_servico_roteiro_acompanhamento.jsp
		//O id não eh informada ,o id esta na sessao 
		if(httpServletRequest.getParameter("idEquipe") == null){

			String chave = httpServletRequest.getParameter("chave");
			
			HashMap mapEquipe = 
				(HashMap) sessao.getAttribute("mapEquipe");
			
			equipe = (Equipe) mapEquipe.get(chave);
			obterDadosEquipe = Fachada.getInstancia().obterDadosEquipe(equipe.getId());
		
		} else {
			
			idEquipe = new Integer(httpServletRequest.getParameter("idEquipe"));
			obterDadosEquipe = Fachada.getInstancia().obterDadosEquipe(idEquipe);
			equipe = obterDadosEquipe.getEquipe();
		}
		
		FiltroEquipe filtroEquipe = new FiltroEquipe();
		
		filtroEquipe.adicionarParametro(new ParametroSimples (FiltroEquipe.ID, equipe.getId()));
		
		Collection<Equipe> dadosEquipe = (Collection) Fachada.getInstancia().pesquisar(filtroEquipe, Equipe.class.getName());
		
		Equipe equipeFiltro = (Equipe) Util.retonarObjetoDeColecao(dadosEquipe);
		
		consultarDadosEquipeActionForm.setIdEquipe(""+equipe.getId());
		consultarDadosEquipeActionForm.setNomeEquipe(equipe.getNome());
		consultarDadosEquipeActionForm.setPlacaVeiculo(equipe.getPlacaVeiculo());
		
		//Para exibir a carga horária em horas e não em minutos - Raphael Rossiter em 13/02/2007
		consultarDadosEquipeActionForm.setCargaTrabalhoDia(""+ (equipe.getCargaTrabalho() / 60));
		
		consultarDadosEquipeActionForm.setCodigoDdd(equipeFiltro.getCodigoDdd().toString());
		consultarDadosEquipeActionForm.setNumeroTelefone(equipeFiltro.getNumeroTelefone().toString());
		consultarDadosEquipeActionForm.setNumeroImei(equipeFiltro.getNumeroImei().toString());
		
		consultarDadosEquipeActionForm.setUnidadeOrganizacionalId(""+equipe.getUnidadeOrganizacional().getId());
		consultarDadosEquipeActionForm.setUnidadeOrganizacionalDescricao(equipe.getUnidadeOrganizacional().getDescricao());
		consultarDadosEquipeActionForm.setTipoPerfilServicoId(""+equipe.getServicoPerfilTipo().getId());
		consultarDadosEquipeActionForm.setTipoPerfilServicoDescricao(equipe.getServicoPerfilTipo().getDescricao());
		consultarDadosEquipeActionForm.setEquipeComponentes(obterDadosEquipe.getColecaoEquipeComponentes());
		
		return retorno;
	}
	
}