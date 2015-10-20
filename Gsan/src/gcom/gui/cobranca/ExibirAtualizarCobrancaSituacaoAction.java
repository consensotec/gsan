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


import gcom.cadastro.cliente.FiltroProfissao;
import gcom.cadastro.cliente.FiltroRamoAtividade;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroContaMotivoRevisao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




/**
 * 
 * @author Arthur Carvalho
 * @date 05/09/2008
 */
public class ExibirAtualizarCobrancaSituacaoAction extends GcomAction {

	/**
	 * M�todo responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("cobrancaSituacaoAtualizar");

		AtualizarCobrancaSituacaoActionForm atualizarCobrancaSituacaoActionForm = (AtualizarCobrancaSituacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoPesquisa = null;
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((CobrancaSituacao) sessao.getAttribute("cobrancaSituacao")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		CobrancaSituacao cobrancaSituacao = new CobrancaSituacao();
		
		
		if (id != null && !id.trim().equals("")) {

			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(FiltroCobrancaSituacao.ID, id));
			
			filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
			
			Collection colecaoCobrancaSituacao = fachada.pesquisar(
					filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			
			if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
				cobrancaSituacao = (CobrancaSituacao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
			}

			
			if (id != null && !id.trim().equals("")) {

				atualizarCobrancaSituacaoActionForm
					.setCodigo(cobrancaSituacao
						.getId().toString());

				atualizarCobrancaSituacaoActionForm
					.setDescricao(cobrancaSituacao
						.getDescricao());
				
				if (cobrancaSituacao.getContaMotivoRevisao() != null){
					
					atualizarCobrancaSituacaoActionForm
					.setContaMotivoRevisao(cobrancaSituacao
							.getContaMotivoRevisao().getId().toString());
				} 
				
				if (cobrancaSituacao.getProfissao() != null){
					
					atualizarCobrancaSituacaoActionForm
					.setProfissao(cobrancaSituacao
							.getProfissao().getId().toString());
				} 
				
				if (cobrancaSituacao.getRamoAtividade() != null){
					
					atualizarCobrancaSituacaoActionForm
					.setRamoAtividade(cobrancaSituacao
							.getRamoAtividade().getId().toString());
				} 
				
				atualizarCobrancaSituacaoActionForm
					.setIndicadorExigenciaAdvogado(cobrancaSituacao
							.getIndicadorExigenciaAdvogado());
				
				atualizarCobrancaSituacaoActionForm
					.setIndicadorUso(cobrancaSituacao
						.getIndicadorUso());
				
				if (cobrancaSituacao.getIndicadorBloqueioParcelamento() != null){
				
					atualizarCobrancaSituacaoActionForm
						.setIndicadorBloqueioParcelamento(cobrancaSituacao
							.getIndicadorBloqueioParcelamento());
				}
				if (cobrancaSituacao.getIndicadorBloqueioRetirada() != null){
					
					atualizarCobrancaSituacaoActionForm
						.setIndicadorBloqueioRetirada(cobrancaSituacao
							.getIndicadorBloqueioRetirada());
				}
				
				if (cobrancaSituacao.getIndicadorBloqueioInclusao() != null){
					
					atualizarCobrancaSituacaoActionForm
						.setIndicadorBloqueioInclusao(cobrancaSituacao
							.getIndicadorBloqueioInclusao());
				}
				if(cobrancaSituacao.getIndicadorSelecaoApenasComPermissao() != null){
					atualizarCobrancaSituacaoActionForm
						.setIndicadorSelecaoApenasComPermissao(
								cobrancaSituacao.getIndicadorSelecaoApenasComPermissao());
				}
				
				if(cobrancaSituacao.getIndicadorPrescricaoImoveisParticulares() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorPrescricaoImoveisParticulares(
							cobrancaSituacao.getIndicadorPrescricaoImoveisParticulares());
					
				}
				
				if(cobrancaSituacao.getIndicadorBloqueioCertidaoDebNegativado() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorBloqueioCertidaoDebNegativado(
							cobrancaSituacao.getIndicadorBloqueioCertidaoDebNegativado().toString());
				}
				
				if(cobrancaSituacao.getIndicadorNaoIncluirImoveisEmCobrancaResultado() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorNaoIncluirImoveisEmCobrancaResultado(
							cobrancaSituacao.getIndicadorNaoIncluirImoveisEmCobrancaResultado());
					
				}
				
				if(cobrancaSituacao.getIndicadorCancelarImovelNegativacao() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorCancelarImovelNegativacao(
							cobrancaSituacao.getIndicadorCancelarImovelNegativacao());
					
				}
				
				/**
				 * MA20140610677 - Alterar vencimentos para contas negativadas
				 * @author Diogo Luiz
				 * @date 23/06/2014
				 * RM11230 - [UC0858] - Manter Situa��o de Cobran�a		
				 */
				if(cobrancaSituacao.getIndicadorAlterarVencimentoConta() != null){
					atualizarCobrancaSituacaoActionForm.setIndicadorAlterarVencimentoConta(
						cobrancaSituacao.getIndicadorAlterarVencimentoConta());
				}
				
				//Motivo de revisao da conta
					FiltroContaMotivoRevisao filtroContaMotivoRevisao = new FiltroContaMotivoRevisao();
		        
		        	filtroContaMotivoRevisao.setCampoOrderBy(FiltroContaMotivoRevisao.ID);
		        
		        	filtroContaMotivoRevisao.adicionarParametro(new ParametroSimples(
		        			FiltroContaMotivoRevisao.INDICADOR_USO,
		        			ConstantesSistema.INDICADOR_USO_ATIVO));
				
		        	//Retorna motivo de revisao da conta
		        	colecaoPesquisa = this.getFachada().pesquisar(filtroContaMotivoRevisao,
		        			ContaMotivoRevisao.class.getName());
		        
	        	if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		            	//Nenhum registro na tabela foi encontrado
		            	throw new ActionServletException(
		                    	"atencao.pesquisa.nenhum_registro_tabela", null,
		            			"Situa��o de Cobran�a");
		        	} else {
		            	httpServletRequest.setAttribute("colecaoContaMotivoRevisao", colecaoPesquisa);
		        	}
				
		        
				}
			
			//Profissao
	        FiltroProfissao filtroProfissao = new FiltroProfissao();
	        
	        filtroProfissao.setCampoOrderBy(FiltroProfissao.ID);
	        
	        filtroProfissao.adicionarParametro(new ParametroSimples(
	        		FiltroProfissao.INDICADOR_USO,
	        		ConstantesSistema.INDICADOR_USO_ATIVO));
	       
	        Collection colecaoProfissao = null;
	        //Retorna Profissao
	        colecaoProfissao = this.getFachada().pesquisar(filtroProfissao,
	        		Profissao.class.getName());
	        
	        if (colecaoProfissao == null || colecaoProfissao.isEmpty()) {
	            //Nenhum registro na tabela foi encontrado
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Profissao");
	        } else {
	            httpServletRequest.setAttribute("colecaoProfissao", colecaoProfissao);
	        }
	        
	        //Ramo Atividade
	        FiltroRamoAtividade filtroRamoAtividade = new FiltroRamoAtividade();
	        
	        filtroRamoAtividade.setCampoOrderBy(FiltroRamoAtividade.ID);
	        
	        filtroRamoAtividade.adicionarParametro(new ParametroSimples(
	        		FiltroRamoAtividade.INDICADOR_USO,
	        		ConstantesSistema.INDICADOR_USO_ATIVO));
	       
	        Collection colecaoRamoAtividade = null;
	        //Retorna Ramo de Atividade
	        colecaoRamoAtividade = this.getFachada().pesquisar(filtroRamoAtividade,
	        		RamoAtividade.class.getName());
	        
	        if (colecaoRamoAtividade == null || colecaoRamoAtividade.isEmpty()) {
	            //Nenhum registro na tabela foi encontrado
	            throw new ActionServletException(
	                    "atencao.pesquisa.nenhum_registro_tabela", null,
	                    "Ramo Atvidade");
	        } else {
	            httpServletRequest.setAttribute("colecaoRamoAtividade", colecaoRamoAtividade);
	        }
			
			sessao.setAttribute("atualizarCobrancaSituacao", cobrancaSituacao);

			if (sessao.getAttribute("colecaoCobrancaSituacao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarCobrancaSituacaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarCobrancaSituacaoAction.do");
			}

		}
		

		return retorno;
	}
	
	
}