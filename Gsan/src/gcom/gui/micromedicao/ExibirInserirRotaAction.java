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
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.leitura.FiltroLeituraTipo;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pr�-processamento da p�gina de inserir rota
 * 
 * @author Vivianne Sousa
 * @created 21/03/2006
 */
public class ExibirInserirRotaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclus�o de uma nova rota
	 * 
	 * [UC0038] Inserir Rota
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 21/03/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {


        ActionForward retorno = actionMapping.findForward("inserirRota");
        InserirRotaActionForm inserirRotaActionForm = (InserirRotaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
       
        
        //Pesquisando grupo de cobran�a
        FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
        filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO_ABREVIADA);
        Collection<CobrancaGrupo> collectionCobrancaGrupo = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
        //httpServletRequest.setAttribute("collectionCobrancaGrupo", collectionCobrancaGrupo);
        sessao.setAttribute("collectionCobrancaGrupo", collectionCobrancaGrupo);
        //Fim de pesquisando grupo de cobran�a
        
        //Pesquisando grupo de faturamento
        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
        filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA);
        Collection<FaturamentoGrupo> collectionFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
        //httpServletRequest.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
        sessao.setAttribute("collectionFaturamentoGrupo", collectionFaturamentoGrupo);
        //Fim de pesquisando grupo de faturamento
        
        //Pesquisando tipo de leitura 
        FiltroLeituraTipo filtroLeituraTipo = new FiltroLeituraTipo();
        filtroLeituraTipo.setCampoOrderBy(FiltroLeituraTipo.DESCRICAO);
        filtroLeituraTipo.adicionarParametro(new ParametroSimples(FiltroLeituraTipo.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
        Collection<LeituraTipo> collectionLeituraTipo = fachada.pesquisar(filtroLeituraTipo, LeituraTipo.class.getName());
        //httpServletRequest.setAttribute("collectionLeituraTipo", collectionLeituraTipo);
        sessao.setAttribute("collectionLeituraTipo", collectionLeituraTipo);
        //Fim de pesquisando tipo de leitura
        
        //Pesquisando empresa leitur�stica
        FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
        Collection<Empresa> collectionEmpresa = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
        //httpServletRequest.setAttribute("collectionEmpresa", collectionEmpresa);
        sessao.setAttribute("collectionEmpresa", collectionEmpresa);
        //Fim de pesquisando empresa leitur�stica
        
    	//c�digo p verificar se j� foram adicionadas todos os crit�rios de cobran�a da rota
        //se ainda falta adicionar HABILITA o bot�o adicionar
        //sen�o DESABILITA o bot�o adicionar
    	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
    	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.INDICADOR_USO,ConstantesSistema.SIM));
        
        Collection<CobrancaAcao> collectionCobrancaAcao = new ArrayList();
        
                      
        // crit�rio de cobran�a
        Collection<RotaAcaoCriterio> collectionRotaAcaoCriterio = 
        		(Collection<RotaAcaoCriterio>)sessao.getAttribute("collectionRotaAcaoCriterio");

        String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			criteriosCobrancaRotaInicial (collectionRotaAcaoCriterio,fachada,sessao,httpServletRequest);
			
			collectionCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
	        sessao.setAttribute("collectionCobrancaAcao", collectionCobrancaAcao);
	        
	        inserirRotaActionForm.setIndicadorAjusteConsumo( ConstantesSistema.NAO+"" );
            inserirRotaActionForm.setIndicadorRotaAlternativa( ConstantesSistema.NAO+"");
            inserirRotaActionForm.setIndicadorTransmissaoOffline(ConstantesSistema.NAO+"");
            inserirRotaActionForm.setIndicadorSequencialLeitura(ConstantesSistema.NAO+"");
            inserirRotaActionForm.setIndicadorArmazenarCoordenadas(ConstantesSistema.NAO+"");
			
		}else if ((collectionRotaAcaoCriterio != null && collectionRotaAcaoCriterio.size()!= 0)
        		&& (sessao.getAttribute("UseCase") != null &&
        				sessao.getAttribute("UseCase").equals("INSERIRROTA"))) {
      	
			collectionCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
			
        	collectionRotaAcaoCriterio = (Collection) sessao
                    .getAttribute("collectionRotaAcaoCriterio");
            //manda para a sess�o a cole��o de collectionRotaAcaoCriterio
            sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
	        
	        if (collectionRotaAcaoCriterio.size() != collectionCobrancaAcao.size()){
	        	httpServletRequest.setAttribute("adicionar","habilitado");
	  		}else{
				httpServletRequest.setAttribute("adicionar","desabilitado");
			}
        }/* else {
        	criteriosCobrancaRotaInicial (collectionRotaAcaoCriterio,fachada,sessao,httpServletRequest);       
        }*/


        // crit�rio de cobran�a
        	
        

        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------
        	
        	inserirRotaActionForm.setIdLocalidade("");
        	inserirRotaActionForm.setLocalidadeDescricao("");
        	inserirRotaActionForm.setCodigoSetorComercial("");
        	inserirRotaActionForm.setSetorComercialDescricao("");
        	inserirRotaActionForm.setCodigoRota("");
        	inserirRotaActionForm.setCobrancaGrupo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setFaturamentoGrupo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setLeituraTipo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setEmpresaLeituristica("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setEmpresaCobranca("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setEmpresaEntregaContas("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setCobrancaCriterio("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	//inserirRotaActionForm.setDataAjusteLeitura("");
        	//inserirRotaActionForm.setIndicadorAjusteConsumo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setIndicadorFiscalizarCortado("");
        	inserirRotaActionForm.setIndicadorFiscalizarSuprimido("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setIndicadorGerarFalsaFaixa("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setPercentualGeracaoFaixaFalsa("");
        	inserirRotaActionForm.setIndicadorGerarFiscalizacao("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
        	inserirRotaActionForm.setPercentualGeracaoFiscalizacao("");
            inserirRotaActionForm.setIdLeiturista("");
            inserirRotaActionForm.setNomeLeiturista("");
            inserirRotaActionForm.setNumeroLimiteImoveis("");
            inserirRotaActionForm.setIndicadorAjusteConsumo( ConstantesSistema.NAO+"" );
            inserirRotaActionForm.setIndicadorRotaAlternativa( ConstantesSistema.NAO+"");
            inserirRotaActionForm.setIndicadorTransmissaoOffline(ConstantesSistema.NAO+"");
            inserirRotaActionForm.setIndicadorSequencialLeitura(ConstantesSistema.NAO+"");
            inserirRotaActionForm.setIndicadorArmazenarCoordenadas(ConstantesSistema.NAO+"");

        	criteriosCobrancaRotaInicial (collectionRotaAcaoCriterio,fachada,sessao,httpServletRequest);
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
       			httpServletRequest,fachada);
        
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
       			httpServletRequest,fachada);
        
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
	       			httpServletRequest,fachada);       			
		}        
        
       //-------Fim de parte que trata do c�digo quando o usu�rio tecla enter
                          
        
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
		// DEFINI��O QUE IR� AUXILIAR O RETORNO DOS POPUPS
		sessao.setAttribute("UseCase", "INSERIRROTA");
        
       return retorno;
    }
    
    private void verificaExistenciaCodLocalidade(String idDigitadoEnterLocalidade, 
    											InserirRotaActionForm inserirRotaActionForm,
    											HttpServletRequest httpServletRequest,
    											Fachada fachada) {
    	
        //Verifica se o c�digo da Localidade foi digitado
        if (idDigitadoEnterLocalidade != null
				&& !idDigitadoEnterLocalidade.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterLocalidade) > 0) {
	        
	        //Recupera a localidade informada pelo usu�rio
	        Localidade localidadeEncontrada = fachada.pesquisarLocalidadeDigitada(new Integer(idDigitadoEnterLocalidade));
	        
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
													HttpServletRequest httpServletRequest,
													Fachada fachada) {
    	
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

    		Collection<SetorComercial> setorComerciais = fachada.pesquisar(
    				filtroSetorComercial, SetorComercial.class.getName());
    		
    		
    		if (setorComerciais != null && !setorComerciais.isEmpty()) {
				//o setor comercial foi encontrado
				SetorComercial setorComercialEncontrado = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
				inserirRotaActionForm.setCodigoSetorComercial( "" +  (setorComercialEncontrado.getCodigo()));
				inserirRotaActionForm.setSetorComercialDescricao(setorComercialEncontrado.getDescricao());
				httpServletRequest.setAttribute("idSetorComercialNaoEncontrada","true");
				httpServletRequest.setAttribute("nomeCampo","codigoRota");
				

				// Retorna o maior c�digo de Rota existente para o setor comercial pesquisado
				int codigo = fachada.pesquisarMaximoCodigoRota(new Integer(setorComercialEncontrado.getId()));
				
				// Acrescenta 1 no valor do c�digo para setar o primeiro c�digo vazio para o usu�rio
				codigo = codigo + 1;
				
				inserirRotaActionForm.setCodigoRota("" + codigo);
				
				httpServletRequest.setAttribute("nomeCampo", "codigoRota");
				
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
	
    private void criteriosCobrancaRotaInicial (Collection collectionRotaAcaoCriterio,
    												Fachada fachada,
    												HttpSession sessao,
    												HttpServletRequest httpServletRequest){    	
    	
    	collectionRotaAcaoCriterio = new ArrayList();
    	
    	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
    	filtroCobrancaAcao.adicionarParametro(new ParametroNaoNulo(FiltroCobrancaAcao.COBRANCAO_CRITERIO));
    	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.INDICADOR_USO,ConstantesSistema.SIM));

        Collection<CobrancaGrupo> collectionCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
        
        Iterator iterator = collectionCobrancaAcao.iterator();

		while (iterator.hasNext()) {
			
			CobrancaAcao cobrancaAcao = (CobrancaAcao) iterator.next();
			
			RotaAcaoCriterio rotaAcaoCriterioNova = new RotaAcaoCriterio();
			rotaAcaoCriterioNova.setCobrancaAcao(cobrancaAcao);
			
			//Filtro p pesquisar o crit�rio cobran�a padr�o para cada a��o de cobran�a
			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID,cobrancaAcao.getCobrancaCriterio().getId()));
        	
        	Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada.pesquisar(filtroCobrancaCriterio, CobrancaCriterio.class.getName());
        	
        	CobrancaCriterio cobrancaCriterio = (CobrancaCriterio)Util.retonarObjetoDeColecao(collectionCobrancaCriterio);
        	rotaAcaoCriterioNova.setCobrancaCriterio(cobrancaCriterio);
        	
        	collectionRotaAcaoCriterio.add(rotaAcaoCriterioNova);
    	
		}
		
        //manda para a sess�o a cole��o de collectionRotaAcaoCriterio
        sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
        httpServletRequest.setAttribute("adicionar","desabilitado");
    }
    
    private void verificaExistenciaCodLeiturista(String idDigitadoEnterLeiturista, 
			InserirRotaActionForm inserirRotaActionForm,
			HttpServletRequest httpServletRequest,
			Fachada fachada) {
		
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

			Collection leituristaEncontrado = fachada.pesquisar(filtroLeiturista,
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
			} else {
				//o leiturista n�o foi encontrado
				inserirRotaActionForm.setIdLeiturista("");
				inserirRotaActionForm.setNomeLeiturista("LEITURISTA INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idLeiturista");
			}
		}		
    }   
}
 