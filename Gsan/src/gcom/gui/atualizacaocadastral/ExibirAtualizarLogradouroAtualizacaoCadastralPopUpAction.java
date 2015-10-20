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

package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.LogradouroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroBairroAtlzCadDM;
import gcom.atualizacaocadastral.LogradouroCepAtlzCadDM;
import gcom.atualizacaocadastral.bean.AtualizacaoCadastralLogradouroHelper;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.endereco.bean.AtualizarLogradouroBairroHelper;
import gcom.cadastro.endereco.bean.AtualizarLogradouroCepHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para a pre-exibicao da pagina de Atualizar LogradouroAtlzCad Atualizacao Cadastral
 * 
 * @author Anderson Cabral
 * @date   13/03/2013
 */
public class ExibirAtualizarLogradouroAtualizacaoCadastralPopUpAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirAtualizarLogradouroAtualizacaoCadastralPopUpAction");

		AtualizarLogradouroAtualizacaoCadastralPopUpActionForm logradouroAtlzCadActionForm = (AtualizarLogradouroAtualizacaoCadastralPopUpActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		logradouroAtlzCadActionForm.setColecaoBairro("");
		logradouroAtlzCadActionForm.setColecaoCep("");
		
		String idLogradouroAtlzCad = httpServletRequest.getParameter("idLogradouro");
		
		// Parte da pesquisa da lupa que é rotornada do logradouro_pesquisar.jsp
		// para ser redirecionado para a pesquisa que foi chamado
		if (httpServletRequest.getParameter("chamarPesquisa") != null) {
			retorno = actionMapping.findForward(httpServletRequest
					.getParameter("chamarPesquisa"));
			if (httpServletRequest.getParameter("chamarPesquisa").equals("exibirPesquisarBairro") || 
					httpServletRequest.getParameter("chamarPesquisa").equals("exibirPesquisarCep")) {
				
				httpServletRequest.setAttribute("idMunicipio",
						logradouroAtlzCadActionForm.getIdMunicipio());
				httpServletRequest.setAttribute("idMunicipioDefinido",
						logradouroAtlzCadActionForm.getIdMunicipio());		
				httpServletRequest.setAttribute("tipoPesquisaEndereco", "logradouro");
				httpServletRequest.setAttribute("operacao", "2");
			}
			return retorno;
		}
		
		
		/********RECUPERA  DADOS DOS POPUPs**********/
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			//Municipio
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {
				logradouroAtlzCadActionForm.setIdMunicipio(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				logradouroAtlzCadActionForm.setNomeMunicipio(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));
			}//Cep
			else if(httpServletRequest.getParameter("tipoConsulta").equals(
					"cep")){
				logradouroAtlzCadActionForm.setCodigoCEP(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				logradouroAtlzCadActionForm.setDescricaoCEP(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));
			}//Bairro
			else if(httpServletRequest.getParameter("tipoConsulta").equals(
					"bairro")){
				logradouroAtlzCadActionForm.setCodigoBairro(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				logradouroAtlzCadActionForm.setNomeBairro(httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));				
			}
		}
		
		/********FIM RECUPERA  DADOS DOS POPUPs**********/
		
		if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
	         
	         Collection colecaoBairros = (List) sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
	         
	         if (!colecaoBairros.isEmpty()){
	        	 logradouroAtlzCadActionForm.setColecaoBairro("CARREGADO");
	         }
	         else{
	        	 logradouroAtlzCadActionForm.setColecaoBairro("");
	         }
		}
	        
	    if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
	         
	         Collection colecaoCep = (List) sessao.getAttribute("colecaoCepSelecionadosUsuario");
	         
	         if (!colecaoCep.isEmpty()){
	        	 logradouroAtlzCadActionForm.setColecaoCep("CARREGADO");
	         }else{
	        	 logradouroAtlzCadActionForm.setColecaoCep("");
	         }
	    }
	    
		String codigoLogradouroAtlzCad = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (codigoLogradouroAtlzCad == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null){
				codigoLogradouroAtlzCad = httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
		}
		
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}		

		// Parte que trata do codigo quando o usuario tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) logradouroAtlzCadActionForm.getIdMunicipio();
		String codigoDigitadoEnterBairro = (String) logradouroAtlzCadActionForm.getCodigoBairro();
		
		/** Removendo toda a colecao de bairro da sessao */
        String removerColecaoBairro = httpServletRequest.getParameter("removerColecaoBairro");        
        if (removerColecaoBairro != null && !removerColecaoBairro.equals("")){
        	
        	sessao.removeAttribute("colecaoBairrosSelecionadosUsuario");
        	logradouroAtlzCadActionForm.setColecaoBairro("");
        }
        
        /** Removendo toda a colecao de cep da sessao */
        String removerColecaoCep = httpServletRequest.getParameter("removerColecaoCep");
        
        if (removerColecaoCep != null && !removerColecaoCep.equals("")){       	
        	sessao.removeAttribute("colecaoCepSelecionadosUsuario");
        	logradouroAtlzCadActionForm.setColecaoCep("");
        }
		
		//Verifica se o codigo foi digitado
        if (idDigitadoEnterMunicipio != null
                && !idDigitadoEnterMunicipio.trim().equals("")
                && !Util.validarValorNaoNumerico(idDigitadoEnterMunicipio)) {
            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idDigitadoEnterMunicipio));
            filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

            if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
                //O municipio foi encontrado
                logradouroAtlzCadActionForm.setIdMunicipio(((Municipio) ((List) municipioEncontrado).get(0)).getId().toString());
                logradouroAtlzCadActionForm.setNomeMunicipio(((Municipio) ((List) municipioEncontrado).get(0)).getNome());
                
                httpServletRequest.setAttribute("nomeCampo", "codigoBairro");
                httpServletRequest.setAttribute("idMunicipioNaoEncontrado","true");
                
                Municipio municipio = (Municipio) ((List) municipioEncontrado).get(0);
                
                if (!fachada.verificarMunicipioComCepPorLogradouro(municipio)){
                	
                	httpServletRequest.setAttribute("cepUnico", "OK");               	
                	Cep cep = fachada.obterCepUnicoMunicipio(municipio);
                	
                	if (cep != null){
                		cep.setIdCepAtualizacaoCadastral(cep.getCepId().toString());
                		Collection colecaoCepSelecionadosUsuario = new ArrayList();
                    	colecaoCepSelecionadosUsuario.add(cep);
                    	
                    	sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
                	}
                }

            } else {            	
                logradouroAtlzCadActionForm.setIdMunicipio("");
                httpServletRequest.setAttribute("nomeCampo", "idMunicipio");
                httpServletRequest.setAttribute("idMunicipioNaoEncontrado","exception");
                logradouroAtlzCadActionForm.setNomeMunicipio("Município inexistente");
            }

        }

        //Verifica se o código foi digitado
        if (codigoDigitadoEnterBairro != null
                && !codigoDigitadoEnterBairro.trim().equals("")
                && !Util.validarValorNaoNumerico(codigoDigitadoEnterBairro)) {
            FiltroBairro filtroBairro = new FiltroBairro();

            filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
            
            filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
            filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
            
            // verifica se o bairro pesquisado é de um municipio existente
            if (idDigitadoEnterMunicipio != null
                    && !idDigitadoEnterMunicipio.trim().equals("")
                    && Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

                filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
            }

            Collection bairroEncontrado = fachada.pesquisar(filtroBairro, Bairro.class.getName());

            if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
                
            	//O bairro foi encontrado
                Bairro objetoBairroEncontrado = (Bairro) Util.retonarObjetoDeColecao(bairroEncontrado);
            	
            	logradouroAtlzCadActionForm.setCodigoBairro(String.valueOf(objetoBairroEncontrado.getCodigo()));
                logradouroAtlzCadActionForm.setNomeBairro(objetoBairroEncontrado.getNome());
                
                httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarBairro");
                httpServletRequest.setAttribute("idBairroNaoEncontrado", "true");
                
                /*
                 * Adicionado o novo BAIRRO na colecao
                 */
                String adicionarBairroColecao = httpServletRequest.getParameter("adicionarBairroColecao");
                
                if (adicionarBairroColecao != null && !adicionarBairroColecao.equals("")){
                	
                	logradouroAtlzCadActionForm.setCodigoBairro("");
                    logradouroAtlzCadActionForm.setNomeBairro("");
                    
                	List colecaoBairrosSelecionadosUsuario = new ArrayList();                	
                    if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
                    	
                    	colecaoBairrosSelecionadosUsuario = (List) sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
                    	
                    	if (!colecaoBairrosSelecionadosUsuario.contains((Bairro) ((List) bairroEncontrado).get(0))){
                    		colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
                    		logradouroAtlzCadActionForm.setColecaoBairro("CARREGADO");
                    	}
                    	else{
                    		throw new ActionServletException("atencao.objeto_ja_selecionado", null, "Bairro");
                    	}
                    }
                    else{
                    	colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
                    	sessao.setAttribute("colecaoBairrosSelecionadosUsuario", colecaoBairrosSelecionadosUsuario);
                    	logradouroAtlzCadActionForm.setColecaoBairro("CARREGADO");
                    }
                }
                
            } else {
                logradouroAtlzCadActionForm.setCodigoBairro("");
                httpServletRequest.setAttribute("nomeCampo",
                "codigoBairro");
                httpServletRequest.setAttribute("idBairroNaoEncontrado",
                        "exception");
                logradouroAtlzCadActionForm.setNomeBairro("Bairro inexistente");
            }

        }
        //fim da parte da pesquisa do enter
        
        
        // Inicio da parte que verifica se vem da pagina de
		// atualizacao_cadastral_novos_logradouros_incluir.jsp
		if (codigoLogradouroAtlzCad != null && !codigoLogradouroAtlzCad.equals("")) {
			
			sessao.removeAttribute("colecaoBairrosSelecionadosUsuario");
			sessao.removeAttribute("colecaoCepSelecionadosUsuario");
			
			logradouroAtlzCadActionForm.setCodigoBairro("");
			logradouroAtlzCadActionForm.setCodigoCEP("");
			logradouroAtlzCadActionForm.setDescricaoCEP("");
			logradouroAtlzCadActionForm.setNomeBairro("");
			
			ArrayList<AtualizacaoCadastralLogradouroHelper> colecaoLogradouroHelper = (ArrayList<AtualizacaoCadastralLogradouroHelper>) sessao.getAttribute("colecaoLogradouroHelper");

			LogradouroAtlzCadDM  logradouroAtlzCad = pesquisarLogradouroAtlzCad(colecaoLogradouroHelper, codigoLogradouroAtlzCad);

			if (logradouroAtlzCad != null) {

				logradouroAtlzCadActionForm.setCodigoLogradouro(codigoLogradouroAtlzCad);				
				logradouroAtlzCadActionForm.setNome(formatarResultado(logradouroAtlzCad.getNome()));				
				logradouroAtlzCadActionForm.setNomePopular(formatarResultado(logradouroAtlzCad.getNomePopular()));				
				logradouroAtlzCadActionForm.setNomeLoteamento(formatarResultado(logradouroAtlzCad.getNomeLoteamento()));
				
				if(logradouroAtlzCad.getLogradouro() != null && logradouroAtlzCad.getLogradouro().getId() != null){	
					logradouroAtlzCadActionForm.setIndicadorDesabilitarBotao("1");
				}else{
					logradouroAtlzCadActionForm.setIndicadorDesabilitarBotao("2");
				}

				logradouroAtlzCadActionForm.setIdTipo(new Integer(formatarResultado(""+ logradouroAtlzCad.getLogradouroTipo().getId())));
				FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo(FiltroLogradouroTipo.DESCRICAO);
				filtroLogradouroTipo.setConsultaSemLimites(true);
				filtroLogradouroTipo.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.INDICADORUSO,	ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradourosTipo = fachada.pesquisar(filtroLogradouroTipo, LogradouroTipo.class.getName());

				if (logradourosTipo == null || logradourosTipo.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null,
							"logradouro tipo");
				} else {
					sessao.setAttribute("logradouroTipos", logradourosTipo);
				}

				logradouroAtlzCadActionForm.setIdMunicipio(formatarResultado(logradouroAtlzCad.getMunicipio().getId().toString()));
				logradouroAtlzCadActionForm.setNomeMunicipio(formatarResultado(logradouroAtlzCad.getMunicipio().getNome()));

//				logradouroAtlzCadActionForm.setIndicadorUso(formatarResultado(""+ logradouroAtlzCad.getIndicadorUso()));

				if (logradouroAtlzCad.getLogradouroTitulo() != null
						&& !logradouroAtlzCad.getLogradouroTitulo().equals("")) {

					logradouroAtlzCadActionForm
							.setIdTitulo(new Integer(
									formatarResultado(""
											+ (logradouroAtlzCad.getLogradouroTitulo()
													.getId()))));
				}else{
					logradouroAtlzCadActionForm.setIdTitulo(null);
				}

				// cria a coleção de titulos
				FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo(FiltroLogradouroTitulo.DESCRICAO);

				filtroLogradouroTitulo.setConsultaSemLimites(true);
				filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(FiltroLogradouroTipo.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroTitulos = fachada.pesquisar(filtroLogradouroTitulo, LogradouroTitulo.class.getName());

				if (logradouroTitulos == null || logradouroTitulos.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null,
							"logradouro título");
				} else {
					sessao.setAttribute("logradouroTitulos", logradouroTitulos);
				}

				/*
				 * Carrega uma coleção com os bairros que estão relacionados com o logradouroAtlzCad selecionado
				 */
				
				ArrayList<LogradouroBairroAtlzCadDM> logradouroBairrosAtlzCad = pesquisarLogradouroBairroAtlzCad(colecaoLogradouroHelper, logradouroAtlzCad.getId().toString());

				/*
				 * Carregando uma coleção com os bairros que foram selecinados a partir da relação 
				 * existente entre o próprio bairro e o logradouroAtlzCad escolhido 
				 */
				if (logradouroBairrosAtlzCad != null && !logradouroBairrosAtlzCad.isEmpty()) {
					
					LogradouroBairroAtlzCadDM logradouroAtlzCadBairro = null;
					Collection colecaoBairrosSelecionadosUsuario = new ArrayList();
					Iterator iteratorLogradouroBairroAtlzCads = logradouroBairrosAtlzCad.iterator();
					
					while (iteratorLogradouroBairroAtlzCads.hasNext()){
						logradouroAtlzCadBairro = (LogradouroBairroAtlzCadDM) iteratorLogradouroBairroAtlzCads.next();
						colecaoBairrosSelecionadosUsuario.add(logradouroAtlzCadBairro.getBairro());
					}
					
					logradouroAtlzCadActionForm.setColecaoBairro("CARREGADO");
					sessao.setAttribute("colecaoBairrosSelecionadosUsuario", colecaoBairrosSelecionadosUsuario);
				}
				
				
				/*
				 * Carrega uma coleção com os CEPs que estão relacionados com o logradouroAtlzCad selecionado
				 */
				ArrayList<LogradouroCepAtlzCadDM> logradouroAtlzCadCeps = pesquisarLogradouroCepAtlzCad(colecaoLogradouroHelper, logradouroAtlzCad.getId().toString());
				
				/*
				 * Carregando uma coleção com os CEPs que foram selecinados a partir da relação 
				 * existente entre o próprio CEO e o logradouroAtlzCad escolhido 
				 */
				if (logradouroAtlzCadCeps != null && !logradouroAtlzCadCeps.isEmpty()) {
					
					LogradouroCepAtlzCadDM logradouroAtlzCadCep = null;
					Collection colecaoCepSelecionadosUsuario = new ArrayList();
					Iterator iteratorlogradouroAtlzCadCeps = logradouroAtlzCadCeps.iterator();
					
					//Verifica se o municipio tem cep unico
					if (fachada.verificarMunicipioComCepPorLogradouro(logradouroAtlzCad.getMunicipio())){
						while (iteratorlogradouroAtlzCadCeps.hasNext()){
							logradouroAtlzCadCep = (LogradouroCepAtlzCadDM) iteratorlogradouroAtlzCadCeps.next();
							
							//convertendo o cepAtlzCad para cep
							Cep cep = new Cep();
							cep.setIdCepAtualizacaoCadastral(logradouroAtlzCadCep.getCepAtlzCad().getId());
							cep.setCodigo(logradouroAtlzCadCep.getCepAtlzCad().getCodigoCep());
							cep.setMunicipio(logradouroAtlzCad.getMunicipio().getNome());
							cep.setSigla(logradouroAtlzCad.getMunicipio().getUnidadeFederacao().getDescricao());						
							cep.setLogradouro(logradouroAtlzCad.getNome());
							
							colecaoCepSelecionadosUsuario.add(cep);
						}
					}else{
						httpServletRequest.setAttribute("cepUnico", "OK"); 
						
	                	Cep cep = fachada.obterCepUnicoMunicipio(logradouroAtlzCad.getMunicipio());
	                	
	                	
	                	if (cep != null){
	                		cep.setIdCepAtualizacaoCadastral(cep.getCepId().toString());
	                		colecaoCepSelecionadosUsuario.add(cep);
	                	}
					}
					
					sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
					logradouroAtlzCadActionForm.setColecaoCep("CARREGADO");
				}
				
				sessao.setAttribute("logradouro", logradouroAtlzCad);

			}
		}
		
		
		/*
         * Removendo o bairro selecionado da sessão
         */
        String idBairro = httpServletRequest.getParameter("idBairro");
        
        if (Util.verificarNaoVazio(idBairro) &&
        	sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null && !"true".equals(sessao.getAttribute("idBairroFiltro"))){
        	
        	Collection colecaoBairrosSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoBairrosSelecionadosUsuario");

        	if(colecaoBairrosSelecionadosUsuario.size()==1){
        		throw new ActionServletException("atencao.campo_bairro_obrigatorio");
        	}
        	
            Iterator colecaoBairrosSelecionadosUsuarioIterator;

            Bairro bairroInserir;

            colecaoBairrosSelecionadosUsuarioIterator = colecaoBairrosSelecionadosUsuario
            .iterator();

            while (colecaoBairrosSelecionadosUsuarioIterator.hasNext()) {

            	bairroInserir = (Bairro) colecaoBairrosSelecionadosUsuarioIterator
                .next();

                if (bairroInserir.getId().equals(new Integer(idBairro))) {

                	//Alteracao feita por Tiago Moreno - verifica se pode ser excluido o Logradouro Bairro
                	FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
                	filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, idBairro));
                	filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, idLogradouroAtlzCad));
                	Collection colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());

                	if (colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()){
                		
                		LogradouroBairro logradouroBairro = (LogradouroBairro) colecaoLogradouroBairro.iterator().next();
                		
                		int id = logradouroBairro.getId();
                		httpServletRequest.removeAttribute("idBairro");
                		fachada.verificaObjetoRemocao(id, LogradouroBairro.class.getName(), null, null);
                	}
                	//Fim da Alteracao - Tiago Moreno - 08/08/2006
                	
                	//Remove atualizacao do bairro: Raphael Rossiter
                	//==================================================================================================
                	Collection colecaoAtualizarLogradouroBairroHelper = (Collection)
            		sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper");
                	
                	if (colecaoAtualizarLogradouroBairroHelper != null &&
                		!colecaoAtualizarLogradouroBairroHelper.isEmpty()){
                	
                		Iterator iterator = colecaoAtualizarLogradouroBairroHelper.iterator();
                    	AtualizarLogradouroBairroHelper atualizarLogradouroBairroHelper = null;
                    	
                    	while (iterator.hasNext()){
                    		
                    		atualizarLogradouroBairroHelper = (AtualizarLogradouroBairroHelper) iterator.next();
                    		
                    		if (atualizarLogradouroBairroHelper.getBairro().getId()
                    			.equals(bairroInserir.getId())){
                    			
                    			colecaoAtualizarLogradouroBairroHelper.remove(atualizarLogradouroBairroHelper);
                    			break;
                    		}
                    	}
                	}
                	//==================================================================================================
                	
                	colecaoBairrosSelecionadosUsuario.remove(bairroInserir);
                	break;
                }
            }
            
            if (colecaoBairrosSelecionadosUsuario.isEmpty()){
            	logradouroAtlzCadActionForm.setColecaoBairro("");
            }
        }
        
        
        /*
         * Removendo o CEP selecionado da sessão
         */
        String idCepAtualizacaoCadastral = httpServletRequest.getParameter("idCepAtualizacaoCadastral");
        
        if (idCepAtualizacaoCadastral != null && !idCepAtualizacaoCadastral.equals("") &&
        	sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
        	
        	Collection colecaoCepSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoCepSelecionadosUsuario");

        	if(colecaoCepSelecionadosUsuario.size()==1){
        		throw new ActionServletException("atencao.campo_cep_obrigatorio");
        	}
        	
            Iterator colecaoCepSelecionadosUsuarioIterator;

            Cep cepInserir;

            colecaoCepSelecionadosUsuarioIterator = colecaoCepSelecionadosUsuario
            .iterator();

            while (colecaoCepSelecionadosUsuarioIterator.hasNext()) {

            	cepInserir = (Cep) colecaoCepSelecionadosUsuarioIterator
                .next();

                if (cepInserir.getIdCepAtualizacaoCadastral().equals(idCepAtualizacaoCadastral)) {
                	//Alteracao feita por Tiago Moreno - verifica se pode ser excluido o Logradouro Bairro
                	FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
                	filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, idCepAtualizacaoCadastral));
                	filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, idLogradouroAtlzCad));
                	Collection colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());

                	if (colecaoLogradouroCep != null && !colecaoLogradouroCep.isEmpty()){
                		
                		LogradouroCep logradouroCep = (LogradouroCep) colecaoLogradouroCep.iterator().next();
                		
                		int id = logradouroCep.getId();
                		
                		fachada.verificaObjetoRemocao(id, LogradouroCep.class.getName(), null, null);
                	}
                	//Fim da Alteracao - Tiago Moreno - 08/08/2006
                	
                	//Remove atualizacao do cep: Raphael Rossiter
                	//==================================================================================================
                	Collection colecaoAtualizarLogradouroCepHelper = (Collection)
            		sessao.getAttribute("colecaoAtualizarLogradouroCepHelper");
                	
                	if (colecaoAtualizarLogradouroCepHelper != null &&
                		!colecaoAtualizarLogradouroCepHelper.isEmpty()){
                	
                		Iterator iterator = colecaoAtualizarLogradouroCepHelper.iterator();
                    	AtualizarLogradouroCepHelper atualizarLogradouroCepHelper = null;
                    	
                    	while (iterator.hasNext()){
                    		
                    		atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper) iterator.next();
                    		
                    		if (atualizarLogradouroCepHelper.getCep().getCepId()
                    			.equals(cepInserir.getCepId())){
                    			
                    			colecaoAtualizarLogradouroCepHelper.remove(atualizarLogradouroCepHelper);
                    			break;
                    		}
                    	}
                	}
                	//==================================================================================================
	
                	colecaoCepSelecionadosUsuario.remove(cepInserir);
                	break;
                }
            }
            
            if (colecaoCepSelecionadosUsuario.isEmpty()){
            	logradouroAtlzCadActionForm.setColecaoCep("");
            }
        }
        
        
        /*
        * Adicionando um cep na coleção a partir do nome do município e do código do cep 
        */
        String codigoDigitadoEnterCep = (String) logradouroAtlzCadActionForm.getCodigoCEP();
        
        if (codigoDigitadoEnterCep != null && 
        	!codigoDigitadoEnterCep.trim().equals("") && 
        	!Util.validarValorNaoNumerico(codigoDigitadoEnterCep)) {
        	
            FiltroCep filtroCep = new FiltroCep();

            filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.CODIGO, codigoDigitadoEnterCep));
            
            filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
            
            if (logradouroAtlzCadActionForm.getNomeMunicipio() != null
                && !logradouroAtlzCadActionForm.getNomeMunicipio().trim().equals("")) {

            	filtroCep.adicionarParametro(new ComparacaoTexto(
                FiltroCep.MUNICIPIO, logradouroAtlzCadActionForm.getNomeMunicipio()));
            	
            }

            Collection cepEncontrado = fachada.pesquisar(filtroCep,
            Cep.class.getName());

            if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
                
            	Cep objetoCepEncontrado = (Cep) Util.retonarObjetoDeColecao(cepEncontrado);
            	
            	objetoCepEncontrado.setIdCepAtualizacaoCadastral(objetoCepEncontrado.getCepId().toString());
            	logradouroAtlzCadActionForm.setCodigoCEP(String.valueOf(objetoCepEncontrado.getCodigo()));
                logradouroAtlzCadActionForm.setDescricaoCEP(objetoCepEncontrado.getDescricaoLogradouroFormatada());
                
                httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarCep");

                httpServletRequest.setAttribute("idCEPNaoEncontrado", "true");
                
                /*
                 * Adicionado o novo CEP na coleção
                 */
                String adicionarCepColecao = httpServletRequest.getParameter("adicionarCepColecao");
                
                if (adicionarCepColecao != null && !adicionarCepColecao.equals("")){
                	
                	logradouroAtlzCadActionForm.setCodigoCEP("");
                    logradouroAtlzCadActionForm.setDescricaoCEP("");
                    
                	List colecaoCepSelecionadosUsuario = new ArrayList();
	                if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
	                	
	                	colecaoCepSelecionadosUsuario = (List) sessao.getAttribute("colecaoCepSelecionadosUsuario");
	                	
	                	if (!colecaoCepSelecionadosUsuario.contains((Cep) ((List) cepEncontrado).get(0))){
//	                		colecaoCepSelecionadosUsuario.add((Cep) ((List) cepEncontrado).get(0));
	                		
	                		//[UC1442] Inserir Novos Logradouros Atualizacao Cadastral
	                		//[FS0002] Verificar CEP Associado a Logradouro
	                		boolean cepJaAssociadoLogradouro = fachada.verificaSeExisteAssociacaoCepLogradouro(objetoCepEncontrado.getCodigo());
	                		//O CEP informado já está associado a um logradouro.
	                		if(cepJaAssociadoLogradouro) throw new ActionServletException(
                                    "atencao.cep_ja_associado_a_logradouro", null, "Cep");
	                			
	                		colecaoCepSelecionadosUsuario.add(objetoCepEncontrado);
	                		logradouroAtlzCadActionForm.setColecaoCep("CARREGADO");
	                		
	                	}
	                	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");
                    	}
	                }
	                else{
//	                	colecaoCepSelecionadosUsuario.add((Cep) ((List) cepEncontrado).get(0));
	                	colecaoCepSelecionadosUsuario.add(objetoCepEncontrado);
	                	sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
	                	logradouroAtlzCadActionForm.setColecaoCep("CARREGADO");
	                }
                }

            } else {
                logradouroAtlzCadActionForm.setCodigoCEP("");
                httpServletRequest.setAttribute("nomeCampo",
                "codigoCEP");
                httpServletRequest.setAttribute("idCEPNaoEncontrado",
                        "exception");
                logradouroAtlzCadActionForm.setDescricaoCEP("CEP inexistente");

            }

        }
        
        
        
        /*
         * Alteração de Bairro (Logradouro_Bairro)
         */
        String idBairroAtual = httpServletRequest.getParameter("idBairroAtual");
        
        if (idBairroAtual != null && !idBairroAtual.equalsIgnoreCase("")){
        	
        	String idBairroNovo = httpServletRequest.getParameter("idBairroNovo");
        	Logradouro logradouro = (Logradouro) sessao.getAttribute("logradouro");
        	Collection colecaoAtualizarLogradouroBairroHelper = null;
        	List colecaoBairrosSelecionadosUsuario = null;
        	Bairro bairroNovo = null;
        	Bairro bairroAtual = null;
        	AtualizarLogradouroBairroHelper atualizarLogradouroBairroHelper = null;
        	LogradouroBairro logradouroBairro = null;
        	boolean bairroJaAtualizado = false;
        	
        	//Carregando o novo bairro
        	FiltroBairro filtroBairro = new FiltroBairro();
    		
    		filtroBairro.adicionarParametro(new ParametroSimples(
            FiltroBairro.ID, idBairroNovo));
    		
    		Collection colecaoBairro = fachada.pesquisar(filtroBairro, 
    		Bairro.class.getName());
    		
    		bairroNovo = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
        	
    		
        	if (sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper") != null){
        		
        		colecaoBairrosSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
        		colecaoAtualizarLogradouroBairroHelper = (Collection)
        		sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper");
        		
        		Iterator iteratorBairros = colecaoBairrosSelecionadosUsuario.iterator();
        		Iterator iteratorAtualizacaoBairros = colecaoAtualizarLogradouroBairroHelper.iterator();
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo bairro. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarBairro = true;
        		
        		while (iteratorBairros.hasNext()){
        			
        			bairroAtual = (Bairro) iteratorBairros.next();
        			
        			if (bairroAtual.getId().equals(new Integer(idBairroAtual))){
        				
        				if (colecaoBairrosSelecionadosUsuario.contains(bairroNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarBairroTipoBairroNaoInformado(bairroAtual)){
        						adicionarBairro = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Bairro");
        					}*/
        					
        					adicionarBairro = false;
        		
        				}
        				
        				bairroJaAtualizado = false;
        				while (iteratorAtualizacaoBairros.hasNext()){
        				
        					atualizarLogradouroBairroHelper = (AtualizarLogradouroBairroHelper)
        					iteratorAtualizacaoBairros.next();
        					
        					//Bairro já atualizado
        					if (atualizarLogradouroBairroHelper.getBairro().getId()
        						.equals(bairroAtual.getId())){
        						
        						atualizarLogradouroBairroHelper.setBairro(bairroNovo);
        						bairroJaAtualizado = true;
        						break;
        					}
        				}
        				
        				//Caso o bairro ainda não tenha sido atualizado
        				if (!bairroJaAtualizado){
        					
        					FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
        	        		
        	        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
        	                FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
        	        		
        	        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
        	        		FiltroLogradouroBairro.ID_BAIRRO, idBairroAtual));
        	        		
        	        		Collection colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, 
        	        		LogradouroBairro.class.getName());
        	        		
        	        		logradouroBairro = (LogradouroBairro)
        	        		Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
        	        		
        	        		atualizarLogradouroBairroHelper = new AtualizarLogradouroBairroHelper();
        	        		
        	        		atualizarLogradouroBairroHelper.setLogradouroBairro(logradouroBairro);
        	        		atualizarLogradouroBairroHelper.setBairro(bairroNovo);
        	        		
        	        		colecaoAtualizarLogradouroBairroHelper.add(atualizarLogradouroBairroHelper);
        				}
        				
        				colecaoBairrosSelecionadosUsuario.remove(bairroAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo bairro.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarBairro){
        					colecaoBairrosSelecionadosUsuario.add(bairroNovo);
        				}
        				
        				break;
        			}
        		}
        		
        	}
        	else{
        		
        		//Gerando coleção de apoio
        		colecaoAtualizarLogradouroBairroHelper = new ArrayList();
        		
        		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
        		
        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
                FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
        		
        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
        		FiltroLogradouroBairro.ID_BAIRRO, idBairroAtual));
        		
        		Collection colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, 
        		LogradouroBairro.class.getName());
        		
        		//Verifica se o usuário está alterando um bairro que ele acabou de informar
        		if (colecaoLogradouroBairro != null &&
        			!colecaoLogradouroBairro.isEmpty()){
        		
        			logradouroBairro = (LogradouroBairro)
            		Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
            		
            		atualizarLogradouroBairroHelper = new AtualizarLogradouroBairroHelper();
            		
            		atualizarLogradouroBairroHelper.setLogradouroBairro(logradouroBairro);
            		atualizarLogradouroBairroHelper.setBairro(bairroNovo);
            		
            		colecaoAtualizarLogradouroBairroHelper.add(atualizarLogradouroBairroHelper);
            		
            		sessao.setAttribute("colecaoAtualizarLogradouroBairroHelper", 
            		colecaoAtualizarLogradouroBairroHelper);
        		}
        		
        		//Atualização da tabela de exibição
        		colecaoBairrosSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
        		
        		Iterator iterator = colecaoBairrosSelecionadosUsuario.iterator();
        		bairroAtual = null;
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo bairro. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarBairro = true;
        		
        		while (iterator.hasNext()){
        			
        			bairroAtual = (Bairro) iterator.next();
        			
        			if (bairroAtual.getId().equals(new Integer(idBairroAtual))){
        				
        				if (colecaoBairrosSelecionadosUsuario.contains(bairroNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarBairroTipoBairroNaoInformado(bairroAtual)){
        						adicionarBairro = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Bairro");
        					}*/
        					
        					adicionarBairro = false;
        					
        				}
        				
        				colecaoBairrosSelecionadosUsuario.remove(bairroAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo bairro.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarBairro){
        					colecaoBairrosSelecionadosUsuario.add(bairroNovo);
        				}
        				
        				break;
        			}
        		}
        	}
        	
        	//Organizar a coleção
            Collections.sort((List) colecaoBairrosSelecionadosUsuario, new Comparator() {
                public int compare(Object a, Object b) {
                    String nomeBairro1 = ((Bairro) a).getNome();
                    String nomeBairro2 = ((Bairro) b).getNome();

                    return nomeBairro1.compareTo(nomeBairro2);

                }
            });
        }
        
        
        
        /*
         * Alteração de Cep (Logradouro_Cep)
         */
        String cdCepAtual = httpServletRequest.getParameter("cdCepAtual");
        
        if (cdCepAtual != null && !cdCepAtual.equalsIgnoreCase("")){
        	
        	String cdCepNovo = httpServletRequest.getParameter("cdCepNovo");
        	Logradouro logradouro = (Logradouro) sessao.getAttribute("logradouro");
        	Collection colecaoAtualizarLogradouroCepHelper = null;
        	List colecaoCepSelecionadosUsuario = null;
        	Cep cepNovo = null;
        	Cep cepAtual = null;
        	AtualizarLogradouroCepHelper atualizarLogradouroCepHelper = null;
        	LogradouroCep logradouroCep = null;
        	boolean cepJaAtualizado = false;
        	
        	//Carregando o novo cep
        	FiltroCep filtroCep = new FiltroCep();
    		
        	filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.CODIGO, cdCepNovo));
    		
    		Collection colecaoCep = fachada.pesquisar(filtroCep, 
    		Cep.class.getName());
    		
    		cepNovo = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
        	
    		
        	if (sessao.getAttribute("colecaoAtualizarLogradouroCepHelper") != null){
        		
        		colecaoCepSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoCepSelecionadosUsuario");
        		colecaoAtualizarLogradouroCepHelper = (Collection)
        		sessao.getAttribute("colecaoAtualizarLogradouroCepHelper");
        		
        		Iterator iteratorCeps = colecaoCepSelecionadosUsuario.iterator();
        		Iterator iteratorAtualizacaoCeps = colecaoAtualizarLogradouroCepHelper.iterator();
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo cep. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarCep = true;
        		
        		while (iteratorCeps.hasNext()){
        			
        			cepAtual = (Cep) iteratorCeps.next();
        			
        			if (cepAtual.getCodigo().equals(new Integer(cdCepAtual))){
        				
        				if (colecaoCepSelecionadosUsuario.contains(cepNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarCepInicialMunicipio(cepAtual)){
        						adicionarCep = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Cep");
        					}*/
        					
        					adicionarCep = false;
        					
        				}
        				
        				cepJaAtualizado = false;
        				while (iteratorAtualizacaoCeps.hasNext()){
        				
        					atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper)
        					iteratorAtualizacaoCeps.next();
        					
        					//Cep já atualizado
        					if (atualizarLogradouroCepHelper.getCep().getCodigo()
        						.equals(cepAtual.getCodigo())){
        						
        						atualizarLogradouroCepHelper.setCep(cepNovo);
        						cepJaAtualizado = true;
        						break;
        					}
        				}
        				
        				//Caso o cep ainda não tenha sido atualizado
        				if (!cepJaAtualizado){
        					
        					FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
        	        		
        					filtroLogradouroCep.adicionarParametro(new ParametroSimples(
        					FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
        	        		
        					filtroLogradouroCep.adicionarParametro(new ParametroSimples(
        	        		FiltroLogradouroCep.CODIGO_CEP, cdCepAtual));
        	        		
        	        		Collection colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, 
        	        		LogradouroCep.class.getName());
        	        		
        	        		logradouroCep = (LogradouroCep)
        	        		Util.retonarObjetoDeColecao(colecaoLogradouroCep);
        	        		
        	        		atualizarLogradouroCepHelper = new AtualizarLogradouroCepHelper();
        	        		
        	        		atualizarLogradouroCepHelper.setLogradouroCep(logradouroCep);
        	        		atualizarLogradouroCepHelper.setCep(cepNovo);
        	        		
        	        		colecaoAtualizarLogradouroCepHelper.add(atualizarLogradouroCepHelper);
        				}
        				
        				colecaoCepSelecionadosUsuario.remove(cepAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo cep.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarCep){
        					colecaoCepSelecionadosUsuario.add(cepNovo);
        				}
        				
        				break;
        			}
        		}
        		
        	}
        	else{
        		
        		//Gerando coleção de apoio
        		colecaoAtualizarLogradouroCepHelper = new ArrayList();
        		
        		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
        		
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
        		
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(
        		FiltroLogradouroCep.CODIGO_CEP, cdCepAtual));
        		
        		Collection colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, 
        		LogradouroCep.class.getName());
        		
        		//Verifica se o usuário está alterando um cep que ele acabou de informar
        		if (colecaoLogradouroCep != null &&
        			!colecaoLogradouroCep.isEmpty()){
        		
        			logradouroCep = (LogradouroCep)
	        		Util.retonarObjetoDeColecao(colecaoLogradouroCep);
	        		
	        		atualizarLogradouroCepHelper = new AtualizarLogradouroCepHelper();
	        		
	        		atualizarLogradouroCepHelper.setLogradouroCep(logradouroCep);
	        		atualizarLogradouroCepHelper.setCep(cepNovo);
            		
	        		colecaoAtualizarLogradouroCepHelper.add(atualizarLogradouroCepHelper);
            		
            		sessao.setAttribute("colecaoAtualizarLogradouroCepHelper", 
            		colecaoAtualizarLogradouroCepHelper);
        		}
        		
        		//Atualização da tabela de exibição
        		colecaoCepSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoCepSelecionadosUsuario");
        		
        		Iterator iterator = colecaoCepSelecionadosUsuario.iterator();
        		cepAtual = null;
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo cep. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarCep = true;
        		
        		while (iterator.hasNext()){
        			
        			cepAtual = (Cep) iterator.next();
        			
        			if (cepAtual.getCodigo().equals(new Integer(cdCepAtual))){
        				
        				if (colecaoCepSelecionadosUsuario.contains(cepNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarCepInicialMunicipio(cepAtual)){
        						adicionarCep = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Cep");
        					}*/
        					
        					adicionarCep = false;
        				}
        				
        				colecaoCepSelecionadosUsuario.remove(cepAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo cep.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarCep){
        					colecaoCepSelecionadosUsuario.add(cepNovo);
        				}
        				
        				break;
        			}
        		}
        	}
        	
        	//Organizar a coleção
            Collections.sort((List) colecaoCepSelecionadosUsuario, new Comparator() {
                public int compare(Object a, Object b) {
                    String logradouroCep1 = ((Cep) a).getDescricaoLogradouroFormatada();
                    String logradouroCep2 = ((Cep) b).getDescricaoLogradouroFormatada();

                    return logradouroCep1.compareTo(logradouroCep2);

                }
            });
        }
        
        // Permissão especial
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        if (!fachada.verificarPermissaoAtualizarLogradouroBairro(usuarioLogado)) {
        	httpServletRequest.setAttribute("bloquearLinkBairro", "OK");
        }
        
        if (httpServletRequest.getParameter("fechar") != null){
        	httpServletRequest.setAttribute("fechar","ok");
        }
   
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}
	
	/**
	 * Retorna os LogradouroAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private LogradouroAtlzCadDM pesquisarLogradouroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		LogradouroAtlzCadDM logradouro = null;
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouro = logradouroHelper.getLogradouroAtlzCad();
			}
		}
		
		return logradouro;		
	}
	
	/**
	 * Retorna os LogradouroCepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<LogradouroBairroAtlzCadDM> pesquisarLogradouroBairroAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<LogradouroBairroAtlzCadDM> logradouroBairroAtlzCad = null;
		
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroBairroAtlzCad = logradouroHelper.getColecaoLogardouroBairroAtlzCad();;
			}
		}		
		return logradouroBairroAtlzCad;		
	}
	
	/**
	 * Retorna os LogradouroCepAtlzCad (da colecao que esta na sessao) do logradouro informado
	 * @author Anderson Cabral
	 * @since 13/03/2013
	 * **/
	private ArrayList<LogradouroCepAtlzCadDM> pesquisarLogradouroCepAtlzCad(ArrayList<AtualizacaoCadastralLogradouroHelper>colecaoLogradouroHelper, String id){
		ArrayList<LogradouroCepAtlzCadDM> logradouroCepAtlzCad = null;
		
		for(AtualizacaoCadastralLogradouroHelper logradouroHelper : colecaoLogradouroHelper){
			if(logradouroHelper.getLogradouroAtlzCad().getId().toString().equals(id)){
				logradouroCepAtlzCad = logradouroHelper.getColecaoLogradouroCepAtlzCad();;
			}
		}		
		return logradouroCepAtlzCad;		
	}	
}
