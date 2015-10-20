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
* Yara Taciane de Souza
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

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoHelper;
import gcom.atendimentopublico.ordemservico.bean.OSPavimentoRetornoHelper;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter o Ordem Processo de Repavimenta��o
 * @author Yara Taciane de Souza
 * @created 03/06/2008
 * 
 * * @alterado por Arthur Carvalho 
 * * @data:28-04-2010
 */

public class ExibirManterOrdemProcessoRepavimentacaoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterOrdemProcessoRepavimentacao");
		HttpSession sessao = httpServletRequest.getSession(false);		
		Fachada fachada = Fachada.getInstancia();  
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissao = verificaUnidadeUsuario(usuario, fachada);		
		
		FiltrarOrdemProcessoRepavimentacaoActionForm form = (FiltrarOrdemProcessoRepavimentacaoActionForm) actionForm;
		
		//Recebe os Parametros do filtro atraves do Helper e seta os valores no FORM
		OSPavimentoHelper osPavimentoHelper = null;
		if (sessao.getAttribute("osPavimentoHelper") != null) {
			
			osPavimentoHelper =  (OSPavimentoHelper) sessao.getAttribute("osPavimentoHelper");
			
			adicionaOsParametrosNoForm( osPavimentoHelper , form , fachada );
						
		}
		
		if(osPavimentoHelper.getSituacaoRetorno() == 4){
			form.setEscolhaRelatorio("1");
		}else if ( form.getEscolhaRelatorio() == null || form.getEscolhaRelatorio().equals("") ) {
			form.setEscolhaRelatorio("2");
		}
		
		if(form.getManterPaginaAux() == null ){
			
   		 	form.setManterPaginaAux("0");
			httpServletRequest.setAttribute("manterPaginaAux", form.getManterPaginaAux() );
		}
		
		 Collection collOrdemServicoPavimento = null;		    
	     if (httpServletRequest.getParameter("retornaDoPopup") != null) {
	        	collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
	        			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		 }else{
				if (sessao.getAttribute("collOrdemServicoPavimentoHelper") != null) {
					collOrdemServicoPavimento =  (Collection) sessao.getAttribute("collOrdemServicoPavimentoHelper");
				}				
		 }
	     
	     //Conta a quantidade de registros selecionados.
	     Integer ordemServicoPavimentoCount = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
	     
	     if ( ordemServicoPavimentoCount == null || ordemServicoPavimentoCount.equals("") || 
	    		 ordemServicoPavimentoCount.equals(" ") || ordemServicoPavimentoCount.intValue() <= 0 ) {
	    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );
	     }
	     
	     form.setOrdensServicoSelecionadas(ordemServicoPavimentoCount.toString());
	     
	     retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	     
	     
	     //pesquisa na colecao para atualizacao na tela
	     Integer pagina = null;
	     Integer paginaAtualizada = null ;
	     if ( httpServletRequest.getAttribute("numeroPaginasPesquisa") != null && 
	    		 (!httpServletRequest.getAttribute("numeroPaginasPesquisa").toString().equals("0") || 
	    				 form.getManterPaginaAux() != null ) ) {
	    	
	    	 //Primeira vez que entra na pagina
	    	 if ( sessao.getAttribute("numeroPagina") != null ) {
	    		 //Controla pagina para que nao retorne para pagina incial.
	    		 String paginaAtual = (String) sessao.getAttribute("numeroPagina"); 
	    		 form.setManterPaginaAux(  paginaAtual );
	    		 pagina = (Integer) Util.converterStringParaInteger( form.getManterPaginaAux() );
		    	
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao( osPavimentoHelper, pagina );
		    	 
	    		 Integer numeroDaPagina = pagina.intValue() +1;
		    	 httpServletRequest.setAttribute("page.offset", numeroDaPagina.toString() );
		    	 httpServletRequest.setAttribute("numeroPaginasPesquisa", pagina.toString() );
		    	 
		    	 retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    	 
	    	 } else {
				 //Esse fluxo serve para ter o controle da pagina��o quando a pagina for atualizada atraves do popup.
	    		 paginaAtualizada = (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") + 1;
	    		 form.setManterPaginaAux(  paginaAtualizada.toString() );
	    		 
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
		    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    		 
	    		 //Fluxo responsavel caso as OS sejam pesquisada com a situa��o PENDENTE
	    		 if ( osPavimentoHelper.getSituacaoRetorno().toString().equals("1") &&
	    				 httpServletRequest.getParameter("retornaDoPopup") != null ) {
	    			 
	    			 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
	    					 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    			 
	    			 sessao.setAttribute("totalRegistros", ordemServicoPavimentoCount);

	    		 } else {
	    			 
	    			 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
			    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    		 }
	    		 
	    		 
	    	 }
	    	 
	    	 
	     } else {
	    	 //Pega o numero da pagina atraves do aux criado para guardar o numero da pagina.
	    	 //segunda vez em diante que abre a pagina
	    	 if (form.getManterPaginaAux() != null ) {
		    
	    		 pagina = (Integer) Util.converterStringParaInteger(form.getManterPaginaAux());
		    	 
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper, pagina);
		    	 
		    	 Integer pag = pagina.intValue() +1;
		    	 httpServletRequest.setAttribute("page.offset", pag.toString() );
		    	 
		    	 retorno = this.controlarPaginacao(httpServletRequest, retorno, ordemServicoPavimentoCount );
	    	
	    	 } else {
	    		
	    		 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
		    	 			(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
	    	 }
	     }
	     String[] idsRegistrosChecados = form.getIdRegistro(); 
	     
         //Verifica se o usuario clicou no botao de Concordancia das demandas.
         if ( httpServletRequest.getParameter("acao") != null && 
         	  httpServletRequest.getParameter("acao").equals("concordanciaDemandas")){
        	
        	 //verifica a permissao do usuario.
        	 if (temPermissao) {
        		 
        		 Iterator iteratorCollOrdemServicoPavimento = collOrdemServicoPavimento.iterator();
	        	 OSPavimentoRetornoHelper osPavimentoRetornoHelper  = new OSPavimentoRetornoHelper();
	        	 while( iteratorCollOrdemServicoPavimento.hasNext() ) {
	        		 
	        		 osPavimentoRetornoHelper = (OSPavimentoRetornoHelper) iteratorCollOrdemServicoPavimento.next();
	        		 
	        		 for ( int i=0 ; i < idsRegistrosChecados.length ; i++ ) { 
	        			 //verifica se o dado exibido � igual 
	        			 if(osPavimentoRetornoHelper.getOrdemServico().getId().toString().equals(idsRegistrosChecados[i])
	        					 && osPavimentoRetornoHelper.getOrdemServicoPavimento().getMotivoRejeicao() == null){
	        				 
	        				 osPavimentoHelper.setPavimentoRuaRetorno( osPavimentoHelper.getPavimentoRua() );
	        				 osPavimentoHelper.setPavimentoCalcadaRetorno( osPavimentoHelper.getPavimentoCalcada() );
	        				 
	        				 //Pesquisar a Ordem Servico para atualizacao na base
	        				 FiltroOrdemServicoPavimento filtroOrdemServicoPavimento = new FiltroOrdemServicoPavimento();
	        				 filtroOrdemServicoPavimento.adicionarParametro( new ParametroSimples(
	        						 FiltroOrdemServicoPavimento.ORDEM_SERVICO_ID, idsRegistrosChecados[i]));
	        				 
	        				 Collection colecaoOrdemServicoPavimento = fachada.pesquisar(filtroOrdemServicoPavimento,
	        						 OrdemServicoPavimento.class.getName());
	        				 
	        				 OrdemServicoPavimento ordemServicoPavimento = (OrdemServicoPavimento) 
	        				 		Util.retonarObjetoDeColecao(colecaoOrdemServicoPavimento);
	        				 //ordemServicoPavimento.setAreaPavimentoCalcadaRetorno(osPavimentoHelper.getAreaPavimentoCalcada());
	        				 FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
	        				 filtroPavimentoRua.adicionarParametro( new ParametroSimples( FiltroPavimentoRua.ID,
	        						 osPavimentoRetornoHelper.getOrdemServicoPavimento().getPavimentoRua().getId() ) );
	        				 
	        				 Collection colecaoPavimentoRuaRetorno = fachada.pesquisar(filtroPavimentoRua, 
	        						 PavimentoRua.class.getName());
	        				 
	        				 PavimentoRua pavimentoRuaRetorno = (PavimentoRua) 
	        				 	Util.retonarObjetoDeColecao(colecaoPavimentoRuaRetorno);
	        				 
	        				 //Caso tenha sido registrado o retorno para um servi�o ele n�o mais poder� ser alterado;
	        				 if (ordemServicoPavimento.getPavimentoRuaRetorno() == null || 
	        						 ordemServicoPavimento.getPavimentoRua().equals("")) {
		        				 
	        					 ordemServicoPavimento.setPavimentoRuaRetorno(pavimentoRuaRetorno);
		        				 ordemServicoPavimento.setAreaPavimentoRuaRetorno(
		        						 osPavimentoRetornoHelper.getOrdemServicoPavimento()
		        						 	.getAreaPavimentoRua());
		        				 ordemServicoPavimento.setDataExecucao(new Date());
		        				 ordemServicoPavimento.setIndicadorAceite(ConstantesSistema.SIM);
		        				 ordemServicoPavimento.setDataAceite(ordemServicoPavimento.getDataExecucao());
		        				 
		        				 if ( ordemServicoPavimento.getDescricaoMotivoAceite() != null &&
		        						 !ordemServicoPavimento.getDescricaoMotivoAceite().equals("") ) {
		        					 
		        					 String descricaoJaCadastrada = ordemServicoPavimento.getDescricaoMotivoAceite();
		        					 ordemServicoPavimento.setDescricaoMotivoAceite( descricaoJaCadastrada + " ACEITE AUTOMATICO");
		        					 
		        				 } else {
		        					 
		        					 ordemServicoPavimento.setDescricaoMotivoAceite("ACEITE AUTOMATICO");
		        				 }
		        				 //Usuario admin.
		        				 ordemServicoPavimento.setUsuarioAceite(Usuario.USUARIO_BATCH);
		        				 //ordemServicoPavimento.set
		        				 
		        				 fachada.atualizar(ordemServicoPavimento);
		        				 
		        				 
		        			     //pesquisa na colecao para atualizacao na tela
		        				 if ( pagina != null ) {
			        			     
		        					 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
		        							 pagina);
		        					 
		        					 if ( osPavimentoHelper.getSituacaoRetorno().toString() != "1" && 
		        							 osPavimentoHelper.getSituacaoRetorno().toString() != "2" ) {
			        					 
		        						 Integer atualizaPaginacao = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
			        				     
			        				     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			        				    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			        				    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );
			        				     } else {
			        				    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
				        				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
			        				     }
			        				     
		        					 }
		        				     
		        				 } else {
			        			     
		        					 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
			        						 (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));
		        					 
		        					 if ( !osPavimentoHelper.getSituacaoRetorno().toString().equals("1")  && 
		        							 !osPavimentoHelper.getSituacaoRetorno().toString().equals("2")  ) {
			        					
		        						 Integer atualizaPaginacao = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
			        				     
			        				     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			        				    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			        				    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );

			        				     } 
		        					 }
		        					 //situacao pendente
		        				     if ( osPavimentoHelper.getSituacaoRetorno().toString().equals("1") ) {
		        				    	 
		        				    	 Integer atualizaPaginacao = fachada.pesquisarOrdemProcessoRepavimentacaoCount(osPavimentoHelper);
			        				     
			        				     if ( atualizaPaginacao == null || atualizaPaginacao.equals("") || 
			        				    		 atualizaPaginacao.equals(" ") || atualizaPaginacao.intValue() <= 0 ) {
			        				    	 throw new ActionServletException("atencao.informarOutroFiltro", "exibirFiltrarOrdemProcessoRepavimentacaoAction.do?limpar=S", null, new String[]{} );

			        				     } 
				        				
			        				     if ( idsRegistrosChecados.length == 10 ) {
			        				    	 
			        				    	 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
					        						 (Integer) 0 );
			        				    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
			        				    	 sessao.setAttribute("page.offset", (Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa") + 1 );
				        				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
				        				 
			        				     } else if (collOrdemServicoPavimento.size() == 0) { 
		        				     	
			        				    	 collOrdemServicoPavimento = fachada.pesquisarOrdemProcessoRepavimentacao(osPavimentoHelper,
					        						 (Integer) 0 );
			        				    	 sessao.setAttribute("totalRegistros", atualizaPaginacao);
			        				    	 sessao.setAttribute("page.offset", 1);
				        				     retorno = this.controlarPaginacao(httpServletRequest, retorno, atualizaPaginacao );
				        				     
		        				     	 }else {
				        					
				        					 sessao.setAttribute("totalRegistros", atualizaPaginacao);
				        				 }
		        				     }
		        				 }

	        				 } else {
	        					 throw new ActionServletException("atencao.registro_nao_pode_ser_atualizado", 
	        							 "exibirManterOrdemProcessoRepavimentacaoAction.do", null , new String[] {});
	        				 }
		        		 }
	        		 }
	        	 }
        	 } else {
        		 throw new ActionServletException("atencao.nao_possui_permissao_para_atualizar");
        	 }
         }
         
		// httpServletRequest.setAttribute("collOrdemServicoPavimento", collOrdemServicoPavimento);
		 sessao.setAttribute("collOrdemServicoPavimento", collOrdemServicoPavimento);
		 sessao.removeAttribute("numeroPagina");
		 sessao.removeAttribute("desabilitaBotaoAlterar");
		 sessao.removeAttribute("colecaoMotivoRejeicao");
			
         return retorno;
        
    }
    
	
	/**
	 * Verifica se usuario em Permissao para atualizar o 
	 * retorno das ordens de Servi�o atraves do bot�o confirmar demandas.
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 26/04/2010
	 * @param usuario
	 * @param fachada
	 * @return boolean
	 */
    private boolean verificaUnidadeUsuario( Usuario usuario, Fachada fachada) {
    	
    	boolean temPermissao = false;
    	
    	Collection colecaoUnidadesResponsaveis = null;
    	FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
    	
		if ( usuario != null && usuario.getUnidadeOrganizacional() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo().getId() != null &&
				(usuario.getUnidadeOrganizacional().getUnidadeTipo().getId().intValue() == 
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()) ) { 
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, usuario.getUnidadeOrganizacional().getId()));
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));
	
			colecaoUnidadesResponsaveis = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if ( colecaoUnidadesResponsaveis != null && !colecaoUnidadesResponsaveis.isEmpty() ) {
				temPermissao = true;
			}
	    
		}
		return temPermissao;
    }
    
    /**
     * @author Arthur Carvalho
     * 
     * @date 04/05/2010 
     * 
     * @param osPavimentoHelper
     * @param form
     * @param fachada
     */
    private void adicionaOsParametrosNoForm( OSPavimentoHelper osPavimentoHelper, 
    		FiltrarOrdemProcessoRepavimentacaoActionForm form , Fachada fachada) {
    	
    	if(osPavimentoHelper.getIdUnidadeResponsavel() != null){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID,osPavimentoHelper.getIdUnidadeResponsavel()));			
			
			Collection colecaoUnidadesResponsaveis = fachada.pesquisar(
						filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadesResponsaveis);		
			
			if(unidadeOrganizacional != null){
				form.setDescricaoUnidadeResponsavel(unidadeOrganizacional.getDescricao());
			}				
			
		}
		
		if(osPavimentoHelper.getSituacaoRetorno() != null){				
			String situacao = "";				
			if(osPavimentoHelper.getSituacaoRetorno().toString().equals("1")){
				situacao = "PENDENTES";
			}else if(osPavimentoHelper.getSituacaoRetorno().toString().equals("2")){
				situacao = "CONCLU�DAS";
			}else if(osPavimentoHelper.getSituacaoRetorno().toString().equals("4")){
				situacao = "REJEITADAS";
			// 3
			}else{
				situacao = "TODAS";
			}
		  form.setSituacaoRetornoDescricao(situacao);					
		}
		
		if(osPavimentoHelper.getPeriodoEncerramentoOsInicial() != null && osPavimentoHelper.getPeriodoEncerramentoOsInicial()!= null){
			form.setPeriodoEncerramentoOsInicial(osPavimentoHelper.getPeriodoEncerramentoOsInicial());
			form.setPeriodoEncerramentoOsFinal(osPavimentoHelper.getPeriodoEncerramentoOsFinal());
		}else{
			form.setPeriodoEncerramentoOsInicial(null);
			form.setPeriodoEncerramentoOsFinal(null);
		}
		
		if(osPavimentoHelper.getIdMunicipio() != null){
			
			FiltroMunicipio filtro = new FiltroMunicipio();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroMunicipio.ID,
					osPavimentoHelper.getIdMunicipio()));			
			
			Collection colecaoMunicipio = 
				this.getFachada().pesquisar(
					filtro, 
					Municipio.class.getName());
			
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);		
			
			if(municipio != null){
				form.setIdMunicipio(municipio.getId().toString());
				form.setMunicipioDescricao(municipio.getNome());
			}
		}
		
		if(osPavimentoHelper.getIdBairro() != null){
			
			FiltroBairro filtro = new FiltroBairro();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroBairro.ID,
					osPavimentoHelper.getIdBairro()));			
			
			Collection colecao = 
				this.getFachada().pesquisar(
					filtro, 
					Bairro.class.getName());
			
			Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecao);		
			
			if(bairro != null){
				form.setIdBairro(bairro.getId().toString());
				form.setBairroDescricao(bairro.getNome());
			}
			
		}

		if(osPavimentoHelper.getIdLogradouro() != null){
			FiltroLogradouro filtro = new FiltroLogradouro();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroLogradouro.ID,
					osPavimentoHelper.getIdLogradouro()));			
			
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
			
			Collection colecao = 
				this.getFachada().pesquisar(
					filtro, 
					Logradouro.class.getName());
			
			Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecao);		
			
			if(logradouro != null){
				form.setIdLogradouro(logradouro.getId().toString());
				form.setLogradouroDescricao(logradouro.getDescricaoFormatada());
			}
			

		}

		
    }
  

}