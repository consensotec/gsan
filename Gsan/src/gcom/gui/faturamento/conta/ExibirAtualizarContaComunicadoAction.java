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
import gcom.faturamento.conta.ContaComunicado;
import gcom.faturamento.conta.ContaComunicadoFaturamentoGrupo;
import gcom.faturamento.conta.ContaComunicadoQuadra;
import gcom.faturamento.conta.ContaComunicadoRota;
import gcom.faturamento.conta.ContaComunicadoSetor;
import gcom.faturamento.conta.FiltroContaComunicado;
import gcom.faturamento.conta.FiltroContaComunicadoFaturamentoGrupo;
import gcom.faturamento.conta.FiltroContaComunicadoQuadra;
import gcom.faturamento.conta.FiltroContaComunicadoRota;
import gcom.faturamento.conta.FiltroContaComunicadoSetor;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.parametrosistema.FiltroParametroSistema;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

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
public class ExibirAtualizarContaComunicadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarContaComunicado");

		Fachada fachada = Fachada.getInstancia();
				
		AtualizarContaComunicadoActionForm form = (AtualizarContaComunicadoActionForm) actionForm;
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroParametroSistema filtroParametroSistema = new FiltroParametroSistema();
		ParametroSistema parametroSistemaLin = null;
		
		filtroParametroSistema.limparListaParametros();
		filtroParametroSistema.adicionarParametro(new ParametroSimples(FiltroParametroSistema.CODIGO_CONSTANTE, ParametroSistema.COMUNICADO_LINHAS_QTD_MAX));
		Collection<ParametroSistema> colecaoParametroSistema = fachada.pesquisar(filtroParametroSistema, ParametroSistema.class.getName());
		Integer maxLinha = 0;
		if(!colecaoParametroSistema.isEmpty()){
			parametroSistemaLin = colecaoParametroSistema.iterator().next();
			maxLinha = new Integer(parametroSistemaLin.getValorParametro());
			sessao.setAttribute("maxLinha", ""+maxLinha);
		}else{
			throw new ActionServletException("atencao.parametro.sistema.nulo", null, "Quantidade maxima de linhas do comunicado");
		}
		
		
		ContaComunicado contaComunicado = null;

		if(httpServletRequest.getParameter("idRegistroAtualizacao") != null && !httpServletRequest
				.getParameter("idRegistroAtualizacao").equals("")){
				form.setIdComunicado((String)httpServletRequest.getParameter("idRegistroAtualizacao"));
				form.setReferencia(null);
				form.setTitulo(null);
				form.setComunicado(null);
				form.setGrupoFaturamento(null);
				form.setGerenciaRegional(null);
				form.setLocalidade(null);
				form.setLocalidadeDescricao(null);
				form.setSetorComercial(null);
				form.setSetorComercialDescricao(null);
				form.setRota(null);
				form.setQuadra(null);
				form.setIcUso(null);
				
				//-------Carrega Comunicado com dados da base
				FiltroContaComunicado filtroComunicado = new FiltroContaComunicado();
				filtroComunicado.adicionarParametro(new ParametroSimples(FiltroContaComunicado.ID, form.getIdComunicado()));
				filtroComunicado.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicado.GERENCIA_REGIONAL);
				filtroComunicado.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicado.LOCALIDADE);
				Collection<ContaComunicado> colecaoContaComunicado = fachada.pesquisar(filtroComunicado, ContaComunicado.class.getName());
				
				String idContaComunicado = httpServletRequest
						.getParameter("idRegistroAtualizacao");
				
				form.setIdComunicado(idContaComunicado);
			
			if(colecaoContaComunicado != null && !colecaoContaComunicado.isEmpty()){
				
				contaComunicado = colecaoContaComunicado.iterator().next();
				form.setIcUso(contaComunicado.getIndicadorUso().toString());
				form.setReferencia(Util.formatarAnoMesParaMesAno(contaComunicado.getAnoMesReferencia()));
				form.setTitulo(contaComunicado.getTitulo());
				form.setComunicado(contaComunicado.getComunicado());
				if(contaComunicado.getGerenciaRegional() != null){
					form.setGerenciaRegional(contaComunicado.getGerenciaRegional().getId().toString());
				}
				if(contaComunicado.getLocalidade() != null){
					form.setLocalidade(contaComunicado.getLocalidade().getId().toString());
					form.setLocalidadeDescricao(contaComunicado.getLocalidade().getDescricao());
				}
				
	
				//----------Verifica se o Comunicado tem subclasses verificando se a localidade e o setor estao nulos em ContaComunicado
				if(contaComunicado.getLocalidade() == null && contaComunicado.getGerenciaRegional() == null){
					
					//-----------CARREGA COMANDO POR QUADRAS
					FiltroContaComunicadoQuadra filtroComQuadra = new FiltroContaComunicadoQuadra();
					filtroComQuadra.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoQuadra.GERENCIA_REGIONAL);
					filtroComQuadra.adicionarParametro(new ParametroSimples(FiltroContaComunicadoQuadra.CONTA_COMUNICADO_ID, contaComunicado.getId()));
					Collection<ContaComunicadoQuadra> colComQuadra = fachada.pesquisar(filtroComQuadra, ContaComunicadoQuadra.class.getName());
					
					String[] arrayQuadras = null;
					String[] arrayRotas = null;
					String[] arraySetores = null;
					String[] arrayGrupos = null;
					int i = 0;
					
					if(colComQuadra != null && !colComQuadra.isEmpty()){
						arrayQuadras = new String[colComQuadra.size()];
						arraySetores = new String[1];
						arrayRotas = new String[1];
						ContaComunicadoQuadra comQuadra = null;
						Iterator<ContaComunicadoQuadra> itComQuadra = colComQuadra.iterator();
						
						while(itComQuadra.hasNext()){
							comQuadra = itComQuadra.next();
							if(i==0){
								arrayRotas[0] = comQuadra.getQuadra().getRota().getId().toString();
								arraySetores[0] = comQuadra.getQuadra().getSetorComercial().getId().toString();
								form.setLocalidade(comQuadra.getQuadra().getSetorComercial().getLocalidade().getId().toString());
							}
							arrayQuadras[i] = comQuadra.getQuadra().getId().toString();
							i++;
						}
						form.setQuadra(arrayQuadras);
						form.setRota(arrayRotas);
						form.setSetorComercial(arraySetores);
						
					}else {
						//-----------CARREGA COMANDO POR ROTA
						FiltroContaComunicadoRota filtroComRota = new FiltroContaComunicadoRota();
						filtroComRota.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoRota.GERENCIA_REGIONAL);
						filtroComRota.adicionarParametro(new ParametroSimples(FiltroContaComunicadoRota.CONTA_COMUNICADO_ID, contaComunicado.getId()));
						Collection<ContaComunicadoRota> colComRota = fachada.pesquisar(filtroComRota, ContaComunicadoRota.class.getName());
						if(colComRota != null && !colComRota.isEmpty()){
							arrayRotas = new String[colComRota.size()];
							arraySetores = new String[1];
							ContaComunicadoRota comRota = null;
							Iterator<ContaComunicadoRota> itComRota = colComRota.iterator();
							while (itComRota.hasNext()) {
								comRota = itComRota.next();
								if(i==0){
									arraySetores[0] = comRota.getRota().getSetorComercial().getId().toString();
									form.setLocalidade(comRota.getRota().getSetorComercial().getLocalidade().getId().toString());
								}
								arrayRotas[i] = comRota.getRota().getId().toString();
								i++;
							}
							form.setRota(arrayRotas);
							form.setSetorComercial(arraySetores);
							
						}else{
							//----------------CARREGA POR SETOR COMERCIAL
							FiltroContaComunicadoSetor filtroConSetor = new FiltroContaComunicadoSetor();
							filtroConSetor.adicionarCaminhoParaCarregamentoEntidade(FiltroContaComunicadoSetor.LOCALIDADE);
							filtroConSetor.adicionarParametro(new ParametroSimples(FiltroContaComunicadoSetor.CONTA_COMUNICADO_ID, contaComunicado.getId()));
							Collection colComSetor = fachada.pesquisar(filtroConSetor, ContaComunicadoSetor.class.getName());
							if(colComSetor != null && !colComSetor.isEmpty()){
								Iterator<ContaComunicadoSetor> itComSetor = colComSetor.iterator();
								arraySetores = new String[colComSetor.size()];
								ContaComunicadoSetor comSetor = null;
								while (itComSetor.hasNext()) {
									comSetor = itComSetor.next();
									if(i==0){
										form.setLocalidade(comSetor.getSetorComercial().getLocalidade().getId().toString());
									}
									arraySetores[i] = comSetor.getSetorComercial().getId().toString();
									i++;
								}
								form.setSetorComercial(arraySetores);
							}else{
								FiltroContaComunicadoFaturamentoGrupo filtroContaComunicadoFaturamentoGrupo = new FiltroContaComunicadoFaturamentoGrupo();
								filtroContaComunicadoFaturamentoGrupo.adicionarCaminhoParaCarregamentoEntidade(filtroContaComunicadoFaturamentoGrupo.FATURAMENTO_GRUPO);
								filtroContaComunicadoFaturamentoGrupo.adicionarParametro(new ParametroSimples(filtroContaComunicadoFaturamentoGrupo.CONTA_COMUNICADO_ID, contaComunicado.getId()));
								Collection colComGrupo = fachada.pesquisar(filtroContaComunicadoFaturamentoGrupo, ContaComunicadoFaturamentoGrupo.class.getName());
								Iterator<ContaComunicadoFaturamentoGrupo> itComGrupo = colComGrupo.iterator();
								arrayGrupos = new String[colComGrupo.size()];
								ContaComunicadoFaturamentoGrupo comGrupo = null;
								while (itComGrupo.hasNext()) {
									comGrupo = itComGrupo.next();
									arrayGrupos[i] = comGrupo.getFaturamentoGrupo().getId().toString();
									i++;
								}
								form.setGrupoFaturamento(arrayGrupos);
							}
							
						}
		
					}
				}
			}
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
		
		String idLocalidade = form.getLocalidade();
		
		String idGerenciaRegional = form.getGerenciaRegional();
		String acao = (String)httpServletRequest.getParameter("acao");
		
		if(acao != null && !acao.equals("") && acao.equalsIgnoreCase("limparColecoes")){
			this.limparCamposAoSelecionarGrupoFaturamento( sessao,  form,  httpServletRequest);
			httpServletRequest.setAttribute("acaoRetorno", "grupoFaturamento");
		}
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			if (idGerenciaRegional != null && !"".equalsIgnoreCase(idGerenciaRegional)){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, idGerenciaRegional));
				filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			}
			
			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				form.setLocalidadeDescricao(localidade.getDescricao());
				
				Collection colecaoSetorComercial = this.carregarSetorComercial(sessao, actionForm, httpServletRequest, localidade, fachada);
				
				httpServletRequest.setAttribute("colecaoSetorComercialPorLocalidade",colecaoSetorComercial);
				
				String[] idSetor = form.getSetorComercial();
				
				if(idSetor!= null && idSetor.length == 1 && !idSetor[0].isEmpty() && !idSetor[0].equals("-1")){
					
					Collection colecaoRota = this.carregarRota(sessao, actionForm, httpServletRequest, idSetor[0], fachada);
					httpServletRequest.setAttribute("colecaoRotaPorSetor", colecaoRota);
					
					String[] idRota = form.getRota();
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
				
				form.setLocalidade("");
				form.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
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
	
	public void limparCamposAoSelecionarGrupoFaturamento(HttpSession sessao, AtualizarContaComunicadoActionForm form, HttpServletRequest httpServletRequest){
		form.setSetorComercial(null);
		form.setRota(null);
		form.setQuadra(null);
		httpServletRequest.removeAttribute("colecaoQuadraPorRota");
		httpServletRequest.removeAttribute("colecaoRotaPorSetor");
		httpServletRequest.removeAttribute("colecaoSetorComercialPorLocalidade");
	}

}
