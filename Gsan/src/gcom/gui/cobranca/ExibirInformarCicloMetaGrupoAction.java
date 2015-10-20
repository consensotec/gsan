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
package gcom.gui.cobranca;

import gcom.cobranca.CicloMeta;
import gcom.cobranca.CicloMetaGrupo;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCicloMeta;
import gcom.cobranca.FiltroCicloMetaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.InformarCicloMetaGrupoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00] Informar metas do ciclo
 * 
 * @author Francisco do Nascimento
 * 
 */

public class ExibirInformarCicloMetaGrupoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("informarCicloMetaGrupo");

		CicloMetaGrupoActionForm cicloMetaForm = (CicloMetaGrupoActionForm) actionForm;

		CicloMeta cicloMeta = null;
		
		Fachada fachada = Fachada.getInstancia();

//		 Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		String voltaFiltro = httpServletRequest.getParameter("voltar");
		if (voltaFiltro != null && !voltaFiltro.trim().equals("")) {
			sessao.removeAttribute("idCicloMeta");
			sessao.removeAttribute("cicloMeta");
			sessao.removeAttribute("helpers");
			cicloMetaForm.setAnoMesCobranca(null);
			cicloMetaForm.setIdCobrancaAcao(null);
		}

		if (httpServletRequest.getParameter("reloadPage") == null) {

			String idCicloMeta = "";

			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idCicloMeta = httpServletRequest
						.getParameter("idRegistroAtualizacao");

				sessao.setAttribute("idCicloMeta",
						idCicloMeta);
			} else {
				if (sessao.getAttribute("idRegistroAtualizacao") != null) {
					idCicloMeta = (String) sessao
							.getAttribute("idRegistroAtualizacao");
				} else {
					idCicloMeta = (String) sessao
							.getAttribute("idCicloMeta");
				}
			}

			if ((idCicloMeta == null || idCicloMeta.equals("")) && 
					cicloMetaForm.getAnoMesCobranca() != null){
				FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					cicloMetaForm.getIdCobrancaAcao()));
				filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
						Util.formatarMesAnoComBarraParaAnoMes(cicloMetaForm.getAnoMesCobranca())));
				filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);

				Collection colecaoCicloMetas = fachada.pesquisar(
						filtroCiclo, CicloMeta.class.getName());
				
				cicloMeta = (CicloMeta) Util.retonarObjetoDeColecao(colecaoCicloMetas);
				
				if (cicloMeta != null){
					/* 4.3 Caso ambos estejam informados e apenas o valor seja exclu�do 
					( atualizar o CLMT_VLLIMITE com zero na tabela CICLO_META com 
					CLMT_AMREFERENCIA = a refer�ncia Informada e CBAC_ID =  Id da 
					A��o de Cobran�a selecionada.) */
					if (httpServletRequest.getParameter("alterarApenasValorLimite") != null 
							&& !httpServletRequest.getParameter("alterarApenasValorLimite").equals("")){
						cicloMeta.setUltimaAlteracao(new Date());
						cicloMeta.setValorLimite(new BigDecimal(0));
						if (!cicloMetaForm.getMetaTotal().equals("")
								&& !cicloMetaForm.getMetaTotal().equals("0")){
							
							FiltroCicloMetaGrupo filtroCicloMetaGrupo = new FiltroCicloMetaGrupo();
							filtroCicloMetaGrupo.adicionarParametro(new ParametroSimples(FiltroCicloMetaGrupo.CICLO_META_ID, 
								cicloMetaForm.getIdCicloMeta()));
							Collection colecaoCicloMetaGrupo = fachada.pesquisar(
									filtroCicloMetaGrupo, CicloMetaGrupo.class.getName());
									
							Collection<InformarCicloMetaGrupoHelper> helpersAux = (Collection<InformarCicloMetaGrupoHelper>) sessao.getAttribute("helpers");
							Collection<InformarCicloMetaGrupoHelper> helpersLocalidade = new ArrayList<InformarCicloMetaGrupoHelper>();
							
							if (helpersAux != null){
								if (cicloMeta.getId().intValue() > 0 
										&& colecaoCicloMetaGrupo != null && !colecaoCicloMetaGrupo.isEmpty()){
									
									for (Iterator iter = helpersAux.iterator(); iter.hasNext();) {
										
										InformarCicloMetaGrupoHelper itemGerencia = (InformarCicloMetaGrupoHelper) iter.next();
										
										for (Iterator iter2 = itemGerencia.getSubItens().values().iterator(); iter2.hasNext();) {
											
											InformarCicloMetaGrupoHelper itemUneg = (InformarCicloMetaGrupoHelper) iter2.next();
											
											for (Iterator iter3 = itemUneg.getSubItens().values().iterator(); iter3
													.hasNext();) {
												
												InformarCicloMetaGrupoHelper itemLoc = (InformarCicloMetaGrupoHelper) iter3.next();
												  
												String nomeItem = itemGerencia.getTipoItem() + itemGerencia.getIdItem() + 
													itemUneg.getTipoItem() + itemUneg.getIdItem() +
													itemLoc.getTipoItem() + itemLoc.getIdItem(); 
												
												itemLoc.setMetaAtual(Integer.parseInt(
														httpServletRequest.getParameter(nomeItem)));
												
											}
											
											helpersLocalidade.addAll(itemUneg.getSubItens().values());
										}
										
									}
									
									fachada.atualizar(cicloMeta);
									fachada.atualizarDistribuicaoMetasCicloGrupoLocalidade(cicloMeta, helpersLocalidade);
								}else{
									cicloMeta.setMetaTotal(new Integer(cicloMetaForm.getMetaTotal()));
									fachada.distribuirMetasCiclo(cicloMeta);
								}
							}else{
								fachada.atualizar(cicloMeta);
							}
							
						}else{
							fachada.remover(cicloMeta);
						}
						
						//limpa a sess�o
						if (sessao.getAttribute("helpers") != null){
							sessao.removeAttribute("helpers");
						}
						
						if (sessao.getAttribute("cicloMeta") != null){
							sessao.removeAttribute("cicloMeta");
						}
						
						if (sessao.getAttribute("colecaoCobrancaAcao") != null){
							sessao.removeAttribute("colecaoCobrancaAcao");
						}
						
						//Seta o retorno
				        retorno = actionMapping.findForward("telaSucesso");
						
						//p�gina de sucesso
						montarPaginaSucesso(httpServletRequest, "Metas/Valor Limite da A��o de Cobran�a " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
								+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
								+ " atualizadas(o) com sucesso. ",
								"Informar Metas de outra A��o de Cobran�a",
								"exibirInformarCicloMetaGrupoAction.do?menu=sim" );
						
						return retorno;
					}
					
					/* 4.1.	Caso s� o valor estivesse informado e for exclu�do 
					 * (excluir a linha da tabela CICLO_META com CLMT_AMREFERENCIA = a 
					 * refer�ncia Informada e CBAC_ID =  Id da A��o de Cobran�a selecionada.) */
					if (httpServletRequest.getParameter("excluirCicloMeta") != null 
							&& !httpServletRequest.getParameter("excluirCicloMeta").equals("")){
						
						fachada.remover(cicloMeta);
						
						//limpa a sess�o
						if (sessao.getAttribute("helpers") != null){
							sessao.removeAttribute("helpers");
						}
						
						if (sessao.getAttribute("cicloMeta") != null){
							sessao.removeAttribute("cicloMeta");
						}
						
						if (sessao.getAttribute("colecaoCobrancaAcao") != null){
							sessao.removeAttribute("colecaoCobrancaAcao");
						}
						
						//Seta o retorno
				        retorno = actionMapping.findForward("telaSucesso");
						
						//p�gina de sucesso
						montarPaginaSucesso(httpServletRequest, "Metas/Valor Limite da A��o de Cobran�a " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
								+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
								+ " atualizadas(o) com sucesso. ",
								"Informar Metas de outra A��o de Cobran�a",
								"exibirInformarCicloMetaGrupoAction.do?menu=sim" );
						return retorno;
					}
					
					/* 4.4.	Caso ambos estejam informados e apenas a Meta seja exclu�da 
					 * (atualizar o CLMT_QTMETA com zero na tabela CICLO_META com CLMT_AMREFERENCIA 
					 * = a refer�ncia Informada e CBAC_ID =  Id da A��o de Cobran�a selecionada 
					 * e Excluir na tabela CICLO_META_GRUPO as linhas com o CLMT_ID da tabela 
					 * CICLO_META com CLMT_AMREFERENCIA = a refer�ncia Informada e CBAC_ID =  
					 * Id da A��o de Cobran�a selecionada)*/
					if (httpServletRequest.getParameter("alterarMetaExcluirCicloMetaGrupo") != null 
							&& !httpServletRequest.getParameter("alterarMetaExcluirCicloMetaGrupo").equals("")){
						cicloMeta.setUltimaAlteracao(new Date());
						cicloMeta.setMetaTotal(0);
						fachada.atualizar(cicloMeta);
						fachada.removerCicloMetaGrupo(cicloMeta.getId());
						
						//limpa a sess�o
						if (sessao.getAttribute("helpers") != null){
							sessao.removeAttribute("helpers");
						}
						
						if (sessao.getAttribute("cicloMeta") != null){
							sessao.removeAttribute("cicloMeta");
						}
						
						if (sessao.getAttribute("colecaoCobrancaAcao") != null){
							sessao.removeAttribute("colecaoCobrancaAcao");
						}
						
						//Seta o retorno
				        retorno = actionMapping.findForward("telaSucesso");
						
						//p�gina de sucesso
						montarPaginaSucesso(httpServletRequest, "Metas/Valor Limite da A��o de Cobran�a " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
								+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
								+ " atualizadas(o) com sucesso. ",
								"Informar Metas de outra A��o de Cobran�a",
								"exibirInformarCicloMetaGrupoAction.do?menu=sim" );
						return retorno;
					}
					
					/* 4.5.	Caso ambos estejam informados e ambos sejam exclu�dos((Excluir na 
					 * tabela CICLO_META_GRUPO as linhas com o CLMT_ID da tabela CICLO_META com 
					 * CLMT_AMREFERENCIA = a refer�ncia Informada e CBAC_ID =  Id da A��o de Cobran�a
					 *  selecionada e excluir a linha da tabela CICLO_META com CLMT_AMREFERENCIA = a 
					 *  refer�ncia Informada e CBAC_ID = Id da A��o de Cobran�a selecionada.)*/
					if (httpServletRequest.getParameter("excluirTudo") != null 
							&& !httpServletRequest.getParameter("excluirTudo").equals("")){
						fachada.removerCicloMetaGrupo(cicloMeta.getId());
						fachada.remover(cicloMeta);
						
						//limpa a sess�o
						if (sessao.getAttribute("helpers") != null){
							sessao.removeAttribute("helpers");
						}
						
						if (sessao.getAttribute("cicloMeta") != null){
							sessao.removeAttribute("cicloMeta");
						}
						
						if (sessao.getAttribute("colecaoCobrancaAcao") != null){
							sessao.removeAttribute("colecaoCobrancaAcao");
						}
						
						//Seta o retorno
				        retorno = actionMapping.findForward("telaSucesso");
						
						//pagina de sucesso
						montarPaginaSucesso(httpServletRequest, "Metas/Valor Limite da A��o de Cobran�a " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
								+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
								+ " atualizadas(o) com sucesso. ",
								"Informar Metas de outra A��o de Cobran�a",
								"exibirInformarCicloMetaGrupoAction.do?menu=sim" );
						return retorno;
					}
					
					/* 4.2.	Caso apenas a meta estivesse gerada e for exclu�da (Excluir na 
					 * tabela CICLO_META_GRUPO as linhas com o CLMT_ID da tabela CICLO_META 
					 * com CLMT_AMREFERENCIA = a refer�ncia Informada e CBAC_ID =  Id da A��o 
					 * de Cobran�a selecionada e excluir a linha da tabela CICLO_META com 
					 * CLMT_AMREFERENCIA = a refer�ncia Informada e CBAC_ID =  Id da A��o 
					 * de Cobran�a selecionada.)*/
					if (httpServletRequest.getParameter("excluirCicloMetaCicloMetaGrupo") != null 
							&& !httpServletRequest.getParameter("excluirCicloMetaCicloMetaGrupo").equals("")){
						fachada.removerCicloMetaGrupo(cicloMeta.getId());
						fachada.remover(cicloMeta);
						
						//limpa a sess�o
						if (sessao.getAttribute("helpers") != null){
							sessao.removeAttribute("helpers");
						}
						
						if (sessao.getAttribute("cicloMeta") != null){
							sessao.removeAttribute("cicloMeta");
						}
						
						if (sessao.getAttribute("colecaoCobrancaAcao") != null){
							sessao.removeAttribute("colecaoCobrancaAcao");
						}
						
						//Seta o retorno
				        retorno = actionMapping.findForward("telaSucesso");
						
						//pagina de sucesso
						montarPaginaSucesso(httpServletRequest, "Metas/Valor Limite da A��o de Cobran�a " + cicloMeta.getCobrancaAcao().getDescricaoCobrancaAcao() 
								+ " do Ciclo " + Util.formatarAnoMesParaMesAno(cicloMeta.getAnoMesReferencia())  				 
								+ " atualizadas(o) com sucesso. ",
								"Informar Metas de outra A��o de Cobran�a",
								"exibirInformarCicloMetaGrupoAction.do?menu=sim" );
						return retorno;
					}
					
					
					idCicloMeta = cicloMeta.getId() + "";
					
					cicloMetaForm.setMetaTotal(cicloMeta.getMetaTotal() + "");
					cicloMetaForm.setValorLimite(Util.formataBigDecimal(cicloMeta.getValorLimite(), 2, true));
					int metaTotalCalculada = 0;
					int metaTotalAjustada = 0;
					
					if (cicloMeta.getMetaTotal() > 0){
						TreeMap<String, InformarCicloMetaGrupoHelper> helpers = fachada.consultarColecaoCicloMetaGrupo(cicloMeta);
	
						// percorrer os helpers de gerencia para calcular o valor total das metas
						for (Iterator iter = helpers.values().iterator(); iter.hasNext();) {
							InformarCicloMetaGrupoHelper helperGerencia = (InformarCicloMetaGrupoHelper) iter.next();			
							metaTotalCalculada += helperGerencia.getMetaOriginal();
							metaTotalAjustada += helperGerencia.getMetaAtual();
						}					
						sessao.setAttribute("helpers", helpers.values());
					}
					
					httpServletRequest.setAttribute("metaTotalCalculada", new Integer(metaTotalCalculada));
					httpServletRequest.setAttribute("metaTotalAjustada", new Integer(metaTotalAjustada));
					
					if (cicloMeta.getValorLimite() != null){
						httpServletRequest.setAttribute("valorLimiteEmissao", cicloMeta.getValorLimite());
					}else{
						httpServletRequest.setAttribute("valorLimiteEmissao", 0);
					}
					
					sessao.setAttribute("cicloMeta", cicloMeta);
					
				} else {
					idCicloMeta = "-1";

					cicloMetaForm.setMetaTotal("");
					cicloMetaForm.setValorLimite("");
					
					sessao.removeAttribute("helpers");
					sessao.removeAttribute("cicloMeta");
				}
			}else{
				idCicloMeta = "";

				cicloMetaForm.setMetaTotal("");
				cicloMetaForm.setValorLimite("");
				
				sessao.removeAttribute("helpers");
				sessao.removeAttribute("cicloMeta");
				
			}
			
			cicloMetaForm.setIdCicloMeta(idCicloMeta);
			

			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, 
				ConstantesSistema.SIM));
//			filtroCobrancaAcao.adicionarParametro(new ParametroSimples("usaMetas", 
//					ConstantesSistema.SIM));
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);

			Collection colecaoCobrancaAcao = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);

		}

		return retorno;

	}

}