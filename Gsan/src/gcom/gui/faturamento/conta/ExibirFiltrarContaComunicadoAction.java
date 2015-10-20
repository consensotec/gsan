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
package gcom.gui.faturamento.conta;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirFiltrarContaComunicadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarContaComunicado");

		Fachada fachada = Fachada.getInstancia();
		
		FiltrarContaComunicadoActionForm filtrarContaComunicadoActionForm = (FiltrarContaComunicadoActionForm) actionForm;
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("menu")!= null && httpServletRequest.getParameter("menu").equals("sim")){
			filtrarContaComunicadoActionForm.setAbrangencia("todos");
			filtrarContaComunicadoActionForm.setTipoPesquisaTitulo(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
		}
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		
		sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		
		
		String idLocalidade = (String) httpServletRequest.getParameter("localidade");
		
		String idGerenciaRegional = (String) httpServletRequest.getParameter("gerenciaRegional");
		String acao = (String)httpServletRequest.getParameter("acao");
		
		if(acao != null && !acao.equals("") && acao.equalsIgnoreCase("limparColecoes")){
			this.limparCamposAoSelecionarGrupoFaturamento( sessao,  filtrarContaComunicadoActionForm,  httpServletRequest);
			httpServletRequest.setAttribute("acaoRetorno", "grupoFaturamento");
		}
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			if (idGerenciaRegional != null && !idGerenciaRegional.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, idGerenciaRegional));
				filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			}
			
			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				filtrarContaComunicadoActionForm.setLocalidadeDescricao(localidade.getDescricao());
				
				Collection colecaoSetorComercial = this.carregarSetorComercial(sessao, actionForm, httpServletRequest, localidade, fachada);
				
				httpServletRequest.setAttribute("colecaoSetorComercialPorLocalidade",colecaoSetorComercial);
				
				String[] idSetor = filtrarContaComunicadoActionForm.getSetorComercial();
				
				if(idSetor!= null && idSetor.length == 1 && !idSetor[0].isEmpty() && !idSetor[0].equals("-1")){
					
					Collection colecaoRota = this.carregarRota(sessao, actionForm, httpServletRequest, idSetor[0], fachada);
					httpServletRequest.setAttribute("colecaoRotaPorSetor", colecaoRota);
					
					String[] idRota = filtrarContaComunicadoActionForm.getRota();
					if(idRota != null && idRota.length == 1 && !idRota[0].isEmpty() && !idRota[0].equals("-1")){
						Collection colecaoQuadra = this.carregarQuadra(sessao, actionForm, httpServletRequest, idRota[0], fachada);
						httpServletRequest.setAttribute("colecaoQuadraPorRota", colecaoQuadra);
					}else{
						httpServletRequest.removeAttribute("colecaoQuadraPorRota");
					}
					
				}else{
					httpServletRequest.removeAttribute("colecaoQuadraPorRota");
					httpServletRequest.removeAttribute("colecaoRotaPorSetor");
				}
				
			} else {
				
				filtrarContaComunicadoActionForm.setLocalidade("");
				filtrarContaComunicadoActionForm.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
				
				httpServletRequest.removeAttribute("colecaoSetorComercialPorLocalidade");
				httpServletRequest.removeAttribute("colecaoQuadraPorRota");
				httpServletRequest.removeAttribute("colecaoRotaPorSetor");
			}
		
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		return retorno;

	}
	
	public Collection carregarSetorComercial(HttpSession sessao, ActionForm actionForm, HttpServletRequest httpServletRequest, Localidade localidade, Fachada fachada){
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial(FiltroSetorComercial.DESCRICAO);
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		return colecaoSetorComercial;
		
	}
	
	public Collection carregarRota(HttpSession sessao, ActionForm actionForm, HttpServletRequest httpServletRequest, String idSetor, Fachada fachada){
		//Rota
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.setCampoOrderBy(FiltroRota.CODIGO_ROTA);
		filtroRota.adicionarParametro( new ParametroSimples( FiltroRota.SETOR_COMERCIAL_ID, idSetor));
		filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
		
		return colecaoRota;
	}
	
	public Collection carregarQuadra(HttpSession sessao, ActionForm actionForm, HttpServletRequest httpServletRequest, String idRota, Fachada fachada){
		//Rota
		FiltroQuadra filtroQuadra = new FiltroQuadra();
		filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
		filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.ROTA_ID, idRota));
		filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
		
		return colecaoQuadra;
	}
	
	public void limparCamposAoSelecionarGrupoFaturamento(HttpSession sessao, FiltrarContaComunicadoActionForm form, HttpServletRequest httpServletRequest){
		form.setSetorComercial(null);
		form.setRota(null);
		form.setQuadra(null);
		httpServletRequest.removeAttribute("colecaoQuadraPorRota");
		httpServletRequest.removeAttribute("colecaoRotaPorSetor");
		httpServletRequest.removeAttribute("colecaoSetorComercialPorLocalidade");
	}

}
