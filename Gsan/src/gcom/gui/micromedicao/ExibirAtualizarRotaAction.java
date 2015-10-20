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
package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.FiltroRotaAcaoCriterio;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.FiltroLeituraTipo;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir atulizar a rota
 * @author Vivianne Sousa
 * @created 03/04/2006
 */

public class ExibirAtualizarRotaAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	    	
    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("atualizarRota");
        InserirRotaActionForm inserirRotaActionForm = (InserirRotaActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);
     
        
        //Pesquisando grupo de cobran�a
        FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
        filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO_ABREVIADA);
        
        Collection<CobrancaGrupo> collectionCobrancaGrupo = 
        	this.getFachada().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
        
        sessao.setAttribute("collectionCobrancaGrupo", collectionCobrancaGrupo);
        //Fim de pesquisando grupo de cobran�a
        
        //Pesquisando grupo de faturamento
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);

        Collection<FaturamentoGrupo> collectionFaturamentoGrupo = 
        	this.getFachada().pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

        sessao.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
        //Fim de pesquisando grupo de faturamento
        
        //Pesquisando tipo de leitura 
        FiltroLeituraTipo filtroLeituraTipo = new FiltroLeituraTipo();
        filtroLeituraTipo.adicionarParametro(
        	new ParametroSimples(FiltroLeituraTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroLeituraTipo.setCampoOrderBy(FiltroLeituraTipo.DESCRICAO);
        
        Collection<LeituraTipo> collectionLeituraTipo = 
        	this.getFachada().pesquisar(filtroLeituraTipo, LeituraTipo.class.getName());

        sessao.setAttribute("collectionLeituraTipo", collectionLeituraTipo);
        //Fim de pesquisando tipo de leitura
        
        //Pesquisando empresa leitur�stica
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

        Collection<Empresa> collectionEmpresa = 
        	this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

        sessao.setAttribute("collectionEmpresa", collectionEmpresa);
        //Fim de pesquisando empresa leitur�stica
       
        
        
        // crit�rio de cobran�a
        Collection<RotaAcaoCriterio> collectionRotaAcaoCriterio = 
        	(Collection<RotaAcaoCriterio>)sessao.getAttribute("collectionRotaAcaoCriterio");

        if ((collectionRotaAcaoCriterio != null && collectionRotaAcaoCriterio.size()!= 0) && 
        	(sessao.getAttribute("UseCase") != null && sessao.getAttribute("UseCase").equals("ATUALIZARROTA"))) {
     	        	
        	//c�digo p verificar se j� foram adicionadas todos os crit�rios de cobran�a da rota
            //se ainda falta adicionar HABILITA o bot�o adicionar
            //sen�o DESABILITA o bot�o adicionar
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(
        		new ParametroNaoNulo(FiltroCobrancaAcao.COBRANCAO_CRITERIO));
        	filtroCobrancaAcao.adicionarParametro(
        		new ParametroSimples(FiltroCobrancaCriterio.INDICADOR_USO,ConstantesSistema.SIM));
        	
        	Collection<CobrancaAcao> collectionAcaoCobranca = 
        		this.getFachada().pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
	        
	        if (collectionRotaAcaoCriterio.size() < collectionAcaoCobranca.size()){
	        	httpServletRequest.setAttribute("adicionar","habilitado");
	  		}else{
				httpServletRequest.setAttribute("adicionar","desabilitado");
			}
        	
        } 
        // crit�rio de cobran�a
       
        String idRota = null;
		if ( httpServletRequest.getParameter("reloadPage") == null ||  
				httpServletRequest.getParameter("reloadPage").equals("")){
			//Recupera o id do Rota que vai ser atualizado            
            if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
            	idRota = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
            	//Definindo a volta do bot�o Voltar p Filtrar Rota
    	    	sessao.setAttribute("voltar", "filtrar");
    	    	sessao.setAttribute("idRegistroAtualizacao",idRota);
            }else if(httpServletRequest.getParameter("idRegistroAtualizacao") == null){
            	idRota = (String)sessao.getAttribute("idRegistroAtualizacao");
            	//Definindo a volta do bot�o Voltar p Filtrar Rota
    	    	sessao.setAttribute("voltar", "filtrar");
            }else if (httpServletRequest.getParameter("idRegistroAtualizacao")!= null) {
            	idRota = httpServletRequest.getParameter("idRegistroAtualizacao");
            	//Definindo a volta do bot�o Voltar p Manter Rota
            	sessao.setAttribute("voltar", "manter");
            	sessao.setAttribute("idRegistroAtualizacao",idRota);
            	sessao.setAttribute("indicadorAtualizar","1");
            }
            
           exibirRota(idRota, inserirRotaActionForm, sessao, collectionRotaAcaoCriterio, httpServletRequest);

		}else {
			idRota = (String)sessao.getAttribute("idRegistroAtualizacao");
		}
        

		 //-------Parte que trata do c�digo quando o usu�rio tecla enter     
        String idDigitadoEnterLocalidade = inserirRotaActionForm.getIdLocalidade();
    	if (idDigitadoEnterLocalidade != null &&
    			!idDigitadoEnterLocalidade.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterLocalidade)){
			//Localidade n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade ");		
		}
        verificaExistenciaCodLocalidade(idDigitadoEnterLocalidade,inserirRotaActionForm,
       			httpServletRequest);
           
        
        String idDigitadoEnterSetorComercial = inserirRotaActionForm.getCodigoSetorComercial();
    	if(idDigitadoEnterSetorComercial != null &&
    			!idDigitadoEnterSetorComercial.equalsIgnoreCase("")&&
    			Util.validarValorNaoNumerico(idDigitadoEnterSetorComercial)){
			//Setor Comercial n�o num�rico.
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Setor Comercial ");		
		}
        verificaExistenciaCodSetorComercial(idDigitadoEnterLocalidade,idDigitadoEnterSetorComercial,inserirRotaActionForm,
       			httpServletRequest);
        
        String idDigitadoEnterLeiturista = inserirRotaActionForm.getIdLeiturista();
        
        if (idDigitadoEnterLeiturista != null &&
                !idDigitadoEnterLeiturista.equalsIgnoreCase("")&&
                Util.validarValorNaoNumerico(idDigitadoEnterLeiturista)){
            //Leiturista n�o num�rico.
            httpServletRequest.setAttribute("nomeCampo","idLeiturista");
            throw new ActionServletException("atencao.nao.numerico",
                    null,"Leiturista ");        
        } else {
            verificaExistenciaCodLeiturista(idDigitadoEnterLeiturista, inserirRotaActionForm,
                    httpServletRequest);                
        }        
       //-------Fim de parte que trata do c�digo quando o usu�rio tecla enter
                       
        
        
        
        
        
        if (httpServletRequest.getParameter("desfazer") != null
        && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
    	
	    	//-------------- bt DESFAZER ---------------
	    	sessao.removeAttribute("collectionRotaAcaoCriterio");
	    	collectionRotaAcaoCriterio = null;
	    	exibirRota(idRota, inserirRotaActionForm,sessao,collectionRotaAcaoCriterio,httpServletRequest);
    
        }		
       
       sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		// DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
       sessao.setAttribute("UseCase", "ATUALIZARROTA");
       
       return retorno;  
    }

    private void exibirRota(String idRota, 
			InserirRotaActionForm inserirRotaActionForm,
			HttpSession sessao,
			Collection collectionRotaAcaoCriterio,
			HttpServletRequest httpServletRequest) {

  	    //Cria a vari�vel que vai armazenar a rota para ser atualizada
	        Rota rota = null;
	        
	    	//Cria o filtro de rota e seta o id da rota para ser atualizado no filtro
			//e indica quais objetos devem ser retornados pela pesquisa
	        FiltroRota filtroRota = new FiltroRota();
	        filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, idRota));
	        
	        filtroRota.adicionarCaminhoParaCarregamentoEntidade("setorComercial.localidade");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("cobrancaGrupo");
			filtroRota.adicionarCaminhoParaCarregamentoEntidade("empresa");
            filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista.funcionario");
            filtroRota.adicionarCaminhoParaCarregamentoEntidade("leiturista.cliente");
			
			Collection<Rota> collectionRota = 
				this.getFachada().pesquisar(filtroRota, Rota.class.getName()) ;	
			
				
			//Caso a pesquisa tenha retornado a rota
			if (collectionRota != null	&& !collectionRota.isEmpty()){
				
				//Recupera da cole��o a rota que vai ser atualizada
				rota = (Rota) Util.retonarObjetoDeColecao(collectionRota);
	
				//Seta no form os dados de rota
	        	inserirRotaActionForm.setIdLocalidade("" + rota.getSetorComercial().getLocalidade().getId());
	        	inserirRotaActionForm.setLocalidadeDescricao("" + rota.getSetorComercial().getLocalidade().getDescricao());
	        	inserirRotaActionForm.setCodigoSetorComercial("" + rota.getSetorComercial().getCodigo());
	        	inserirRotaActionForm.setSetorComercialDescricao("" + rota.getSetorComercial().getDescricao());
	        	inserirRotaActionForm.setCodigoRota("" + rota.getCodigo()); 
	        	inserirRotaActionForm.setCobrancaGrupo("" + rota.getCobrancaGrupo().getId());
	        	inserirRotaActionForm.setFaturamentoGrupo("" + rota.getFaturamentoGrupo().getId());
	        	inserirRotaActionForm.setLeituraTipo("" + rota.getLeituraTipo().getId());
	        	inserirRotaActionForm.setIndicadorFiscalizarCortado("" + rota.getIndicadorFiscalizarCortado());
	        	inserirRotaActionForm.setIndicadorFiscalizarSuprimido("" + rota.getIndicadorFiscalizarSuprimido());
	        	inserirRotaActionForm.setIndicadorTransmissaoOffline("" + rota.getIndicadorTransmissaoOffline());
	        	inserirRotaActionForm.setIndicadorSequencialLeitura("" + rota.getIndicadorSequencialLeitura());
	        	inserirRotaActionForm.setIndicadorGerarFalsaFaixa("" + rota.getIndicadorGerarFalsaFaixa());
                inserirRotaActionForm.setIndicadorAjusteConsumo( "" + rota.getIndicadorAjusteConsumo() );
                inserirRotaActionForm.setIndicadorRotaAlternativa(""+rota.getIndicadorRotaAlternativa().shortValue());
                        
                
                if(rota.getNumeroDiasConsumoAjuste() != null && !rota.getNumeroDiasConsumoAjuste().equals("")){
                    inserirRotaActionForm.setNumeroDiasConsumoAjuste( "" + rota.getNumeroDiasConsumoAjuste() );
                }else{
                    inserirRotaActionForm.setNumeroDiasConsumoAjuste( "" );
                }
                
                
                if ( rota.getLeiturista() != null ){
                    inserirRotaActionForm.setIdLeiturista( "" + rota.getLeiturista().getId() );
                    
                    if ( rota.getLeiturista().getFuncionario() != null ){
                        inserirRotaActionForm.setNomeLeiturista( "" + rota.getLeiturista().getFuncionario().getNome() );
                    } else {
                        inserirRotaActionForm.setNomeLeiturista( "" + rota.getLeiturista().getCliente().getNome() );
                    }
                }else{
                	 inserirRotaActionForm.setIdLeiturista("");
                	 inserirRotaActionForm.setNomeLeiturista("");
                }
                if ( rota.getEmpresa() != null ) {
		        	inserirRotaActionForm.setEmpresaLeituristica("" + rota.getEmpresa().getId());
	        	}
	        	if ( rota.getEmpresaCobranca() != null ) {
	        		inserirRotaActionForm.setEmpresaCobranca("" + rota.getEmpresaCobranca().getId());
	        	}
	        	if ( rota.getEmpresaEntregaContas() != null )  {
	        		inserirRotaActionForm.setEmpresaEntregaContas("" + rota.getEmpresaEntregaContas().getId());
	        	} else{
	        		inserirRotaActionForm.setEmpresaEntregaContas("");
	        	}
			    
	   	    	if (rota.getPercentualGeracaoFaixaFalsa() != null){
		        	inserirRotaActionForm.setPercentualGeracaoFaixaFalsa("" + 
		        			rota.getPercentualGeracaoFaixaFalsa().toString().replace(".", ",")); 		
	        	}else{
	        		inserirRotaActionForm.setPercentualGeracaoFaixaFalsa("");
	        	}
	        	
	        	inserirRotaActionForm.setIndicadorGerarFiscalizacao("" + rota.getIndicadorGerarFiscalizacao());
	        	if (rota.getPercentualGeracaoFiscalizacao() != null){
	        		inserirRotaActionForm.setPercentualGeracaoFiscalizacao("" + 
	        				rota.getPercentualGeracaoFiscalizacao().toString().replace(".", ","));
	        	}else{
	        		inserirRotaActionForm.setPercentualGeracaoFiscalizacao("");
	        	}
	        	
	        	if ((rota.getIndicadorUso() != null) && (!rota.getIndicadorUso().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO))){
	        		inserirRotaActionForm.setIndicadorUso("" + rota.getIndicadorUso());
	        	}
	        	
	        	if(rota.getIndicadorRotaAlternativa() != null){
	        		inserirRotaActionForm.setIndicadorRotaAlternativa(""+rota.getIndicadorRotaAlternativa().shortValue());
	        	}
	        	
	        	if(rota.getNumeroLimiteImoveis() != null){
	        		inserirRotaActionForm.setNumeroLimiteImoveis(""+ rota.getNumeroLimiteImoveis());
	        	}else{
	        		inserirRotaActionForm.setNumeroLimiteImoveis("");
	        	}
	        	
	        	if(rota.getIndicadorCoordenadas() != null){
	        		inserirRotaActionForm.setIndicadorArmazenarCoordenadas(String.valueOf(rota.getIndicadorCoordenadas()));
	        	}else{
	        		inserirRotaActionForm.setIndicadorArmazenarCoordenadas(ConstantesSistema.NAO.toString());
	        	}
	        	
	    	    inserirRotaActionForm.setUltimaAlteracao(Util.formatarDataComHora(rota.getUltimaAlteracao()));   

		    	    
		    	//carregar grid Crit�rios de Cobran�a da Rota   
	    	    if (collectionRotaAcaoCriterio == null ||
	    	    		collectionRotaAcaoCriterio.isEmpty()){
	    	    	 FiltroRotaAcaoCriterio filtroRotaAcaoCriterio = new FiltroRotaAcaoCriterio();
	 				filtroRotaAcaoCriterio.adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
	 				filtroRotaAcaoCriterio.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
	 				filtroRotaAcaoCriterio.adicionarParametro(new ParametroSimples
	 						(FiltroRotaAcaoCriterio.ROTA_ID,rota.getId()));
	 				collectionRotaAcaoCriterio = (Collection<RotaAcaoCriterio>)
	 					this.getFachada().pesquisar(filtroRotaAcaoCriterio,RotaAcaoCriterio.class.getName());
	 				
	 				sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
	    	    }
					    	   
	    	    //c�digo p verificar se j� foram adicionadas todos os crit�rios de cobran�a da rota
 	            //se ainda falta adicionar HABILITA o bot�o adicionar
 	            //sen�o DESABILITA o bot�o adicionar
 				FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
 				filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
 		        Collection<CobrancaGrupo> collectionAcaoCobranca = 
 		        	this.getFachada().pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
 		        
 		        sessao.setAttribute("collectionAcaoCobranca", collectionAcaoCobranca);
		        if (collectionRotaAcaoCriterio.size() < collectionAcaoCobranca.size()){
		        	httpServletRequest.setAttribute("adicionar","habilitado");
		        }else{
		        	httpServletRequest.setAttribute("adicionar","desabilitado");
				}

			}
    }

    
    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
			InserirRotaActionForm inserirRotaActionForm,
			HttpServletRequest httpServletRequest) {

	//Verifica se o c�digo da Localidade foi digitado
	if (idDigitadoEnterLocalidade != null
	&& !idDigitadoEnterLocalidade.trim().equals("")
	&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
	
		//Recupera a localidade informada pelo usu�rio
		Localidade localidadeEncontrada = 
			this.getFachada().pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
		
		//Caso a localidade informada pelo usu�rio esteja cadastrada no sistema
		//Seta os dados da localidade no form
		//Caso contr�rio seta as informa��es da localidade para vazio 
		//e indica ao usu�rio que a localidade n�o existe 
		
		if (localidadeEncontrada != null) {
			//a localidade foi encontrada
			inserirRotaActionForm.setIdLocalidade("" + (localidadeEncontrada.getId()));
			inserirRotaActionForm
			.setLocalidadeDescricao(localidadeEncontrada.getDescricao());
			httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
			"true");
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
		} else {
			//a localidade n�o foi encontrada
			inserirRotaActionForm.setIdLocalidade("");
			httpServletRequest.setAttribute("idLocalidadeNaoEncontrada",
			"exception");
			inserirRotaActionForm
			.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
		
		}
	}

}


    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
				String idDigitadoEnterSetorComercial, 
				InserirRotaActionForm inserirRotaActionForm,
				HttpServletRequest httpServletRequest) {

	//Verifica se o c�digo do Setor Comercial foi digitado
	if ((idDigitadoEnterSetorComercial != null && !idDigitadoEnterSetorComercial.toString()
	.trim().equalsIgnoreCase(""))
	&& (idDigitadoEnterLocalidade != null && !idDigitadoEnterLocalidade.toString().trim()
	.equalsIgnoreCase(""))) {
	
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idDigitadoEnterLocalidade != null
		&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
		
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.ID_LOCALIDADE, new Integer(
			idDigitadoEnterLocalidade)));
			}
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
			idDigitadoEnterSetorComercial)));
			
			Collection<SetorComercial> setorComerciais = this.getFachada().pesquisar(
			filtroSetorComercial, SetorComercial.class.getName());
			
		
			if (setorComerciais != null && !setorComerciais.isEmpty()) {
				//o setor comercial foi encontrado
				SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
				inserirRotaActionForm.setCodigoSetorComercial( "" +  (setorComercialEncontrado.getCodigo()));
				inserirRotaActionForm.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
				httpServletRequest.setAttribute("idSetorComercialNaoEncontrada","true");
				//httpServletRequest.setAttribute("nomeCampo","cobrancaGrupo");
				httpServletRequest.setAttribute("nomeCampo","idLocalidade");
				
			} else {
				//o setor comercial n�o foi encontrado
				inserirRotaActionForm.setCodigoSetorComercial("");
				httpServletRequest.setAttribute("idSetorComercialNaoEncontrada",
				"exception");
				inserirRotaActionForm
				.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
			
			}
		
		}
    }
    
    private void verificaExistenciaCodLeiturista(String idDigitadoEnterLeiturista, 
            InserirRotaActionForm inserirRotaActionForm,
            HttpServletRequest httpServletRequest) {
        
        //Verifica se o c�digo do Leiturista foi digitado
        if (idDigitadoEnterLeiturista != null
            && !idDigitadoEnterLeiturista.trim().equals("")
            && Integer.parseInt(idDigitadoEnterLeiturista) > 0) {
            
            //Recupera o leiturista informado pelo usu�rio
            FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
            filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
            filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.ID, idDigitadoEnterLeiturista));
            filtroLeiturista.adicionarParametro(new ParametroSimples(
                    FiltroLeiturista.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection leituristaEncontrado = this.getFachada().pesquisar(filtroLeiturista,
                    Leiturista.class.getName());
            
            //Caso o leiturista informado pelo usu�rio esteja cadastrado no sistema
            //Seta os dados do leiturista no form
            //Caso contr�rio seta as informa��es de leiturista para vazio 
            //e indica ao usu�rio que o leiturista n�o existe 
            
            if (leituristaEncontrado != null && leituristaEncontrado.size() > 0) {
                //leiturista foi encontrado
                Leiturista leiturista = (Leiturista) ((List) leituristaEncontrado).get(0); 
                inserirRotaActionForm.setIdLeiturista("" + 
                    leiturista.getId());
                if (leiturista.getFuncionario() != null){
                    inserirRotaActionForm.setNomeLeiturista(leiturista.getFuncionario().getNome());                 
                } else if (leiturista.getCliente() != null){
                    inserirRotaActionForm.setNomeLeiturista(leiturista.getCliente().getNome());
                }
                httpServletRequest.setAttribute("idLeituristaEncontrado","true");
                httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
                httpServletRequest.setAttribute( "idLeituristaEncontrado", "" );
            } else {
                //o leiturista n�o foi encontrado
                inserirRotaActionForm.setIdLeiturista("");
                inserirRotaActionForm.setNomeLeiturista("LEITURISTA INEXISTENTE");
                httpServletRequest.removeAttribute("idLeituristaEncontrado");
                httpServletRequest.setAttribute("nomeCampo","idLeiturista");
            }
        }       
    }   
    
}