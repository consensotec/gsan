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
package gcom.gui.util;

import gcom.fachada.Fachada;
import gcom.seguranca.acesso.Abrangencia;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroFuncionalidadeCategoria;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Filtro responsável pela segurança do sistema verificando se o usuário que está 
 * requisitando a funcionalidade ou operação, tem acesso ou algum tipo de restrição 
 *
 * @author Pedro Alexandre
 * @date 20/07/2006
 */
public class FiltroSegurancaAcesso extends HttpServlet implements Filter {
	//Variável que aramzena as configurações iniciais do filtro
	private FilterConfig filterConfig;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metódo responsável por setaas configurações inicias necessárias
	 *
	 * @author Pedro Alexandre
	 * @date 20/07/2006
	 *
	 * @param filterConfig
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	
	/**
	 * Metódo responsável por verificar se o usuário tem acesso a funcionalidade ou operação
	 *
	 * @author Pedro Alexandre
	 * @date 20/07/2006
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
		throws ServletException, IOException {
		
		try {
			
			
			//Faz um cast no request para recuperar a sessão do usuário
			HttpServletRequest requestPagina = (HttpServletRequest) request;
		
			//Recupera a sessão do usuário logado
			HttpSession sessao = requestPagina.getSession(false);

			//Recupera o usuário que está logado da sessão
			Usuario usuarioLogado = null;
			
			if(sessao != null){
				usuarioLogado= (Usuario) sessao.getAttribute("usuarioLogado");
			}

			Abrangencia abrangencia = 
        		(Abrangencia)request.getAttribute(Abrangencia.ABRANGENCIA);
            
			//Recupera a coleção de grupos que o usuário logado pertence
            Collection<Grupo> colecaoGruposUsuario = null;
            
            if(sessao != null){
            	colecaoGruposUsuario = (Collection) 
            		sessao.getAttribute("colecaoGruposUsuario");
            }
            	
			
			//Recupera a url do request
			String enderecoURL = requestPagina.getServletPath();
			
            
			/*
			 * Caso a url seja de um processo de abas 
			 * recupera a url pelo parametro do wizard adicionando o ".do" no final
			 */
			if(enderecoURL.contains("Wizard")){
				enderecoURL = requestPagina.getParameter("action") + ".do";
			}
			
			//Alterado por Sávio Luiz.
			//Data:29/02/2008
			//verifica se o endereço da funcionalidade existe a palavra tabela auxiliar
			//se existir então acrescenta o parametros ao caminho para 
			//que ele seja único.
			if(enderecoURL.contains("TabelaAuxiliar")){
				if(requestPagina.getParameter("tela") != null && 
					!requestPagina.getParameter("tela").equals("")){
					
					enderecoURL = 
						enderecoURL+ "?tela="+requestPagina.getParameter("tela") ;
				}
			}
			
			if(requestPagina.getParameter("gerarRelatorio") != null && 
					!requestPagina.getParameter("gerarRelatorio").equals("")){
					
				enderecoURL = 
					enderecoURL+ "?gerarRelatorio="+requestPagina.getParameter("gerarRelatorio") ;
			}
			
			//Caso seja um acesso ao olap, a url é a mesma para todos
			//por isso pega o id da funcionalidade direto
			Integer idFuncionalidade = null;
			if(enderecoURL.contains("selecaoOLAPAction")){
				String id = requestPagina.getParameter("id");
				if(id != null && !id.equals("")){
					idFuncionalidade = new Integer(requestPagina.getParameter("id"));	
				}
			}

			if(enderecoURL.contains("informarDadosGeracaoRelatorioConsultaAction")){
				String id = requestPagina.getParameter("id");
				if(id != null && !id.equals("")){
					idFuncionalidade = new Integer(requestPagina.getParameter("id"));	
				}
			}
			
			
			
			
			Fachada fachada = Fachada.getInstancia();
			
			//Verifica se a url requisitada pelo usuário é uma operação ou uma funcionalidade
			String tipoURL = fachada.verificarTipoURL(enderecoURL);
			
			setCaminhoMenu(sessao, idFuncionalidade, enderecoURL, tipoURL);
			
			//Caso o usuário esteja logado e não tenha clicado no link de logoff
			if(usuarioLogado != null  && 
				!enderecoURL.contains("Logoff") && 
				!enderecoURL.contains("Login") && 
				!enderecoURL.contains("telaPrincipal") && 
//				!enderecoURL.contains("cancelar") && 
				!enderecoURL.contains("executarBatch") && 
				!enderecoURL.toLowerCase().contains("pesquisar") && 
				!enderecoURL.toLowerCase().contains("relatorio") && 
				!enderecoURL.contains("efetuarAlteracaoSenhaAction") && 
				!enderecoURL.contains("carregarParametrosAction") && 
				!enderecoURL.contains("exibirInformarMelhoriasGcomAction") && 
				!enderecoURL.contains("informarMelhoriasGcomAction") && 
				!enderecoURL.contains("exibirEfetuarAlteracaoSenhaSimplificadaAction") && 
				!enderecoURL.contains("efetuarAlteracaoSenhaSimplificadaAction") && 
				!enderecoURL.contains("exibirConsultarSistemaAlteracaoHistoricoAction") && 
				!enderecoURL.contains("exibirSistemaHistAlteracaoDetalharPopupAction") && 
				!enderecoURL.contains("exibirConsultarDadosPagamentoAction") && 
				!enderecoURL.contains("exibirConsultarSituacaoEspecialFaturamentoPopupAction") && 
				!enderecoURL.contains("exibirConsultarSituacaoEspecialCobrancaPopupAction") && 
				!enderecoURL.contains("processarRequisicaoDipositivoMovelAction") && 
				!enderecoURL.contains("processarRequisicaoTelemetriaAction") &&
				!enderecoURL.contains("retornarDadosImovelTelemetriaAction") &&
				!enderecoURL.contains("processarRequisicaoGisAction") &&
				!enderecoURL.contains("processarCoordenadasGisAction") && 
				!enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelAtualizacaoCadastralAction") &&
				!enderecoURL.contains("contasAtrasoWebService")
				 ){
                
				//Caso o tipo da url não esteja nulo
				if(tipoURL != null){
					
					//Caso o usuário tenha solicitado uma funcionalidade 
					if(tipoURL.equalsIgnoreCase("funcionalidade")){
												
						/*
						 * Caso usuário não tenha acesso a funcionalidade
						 * exibe a tela de acesso negado para funcionalidade
						 * Caso contrário chama o próximo filtro na fila
						 */ 
						if(!fachada.verificarAcessoPermitidoFuncionalidade(usuarioLogado,
							enderecoURL, colecaoGruposUsuario,idFuncionalidade)){
							
							RequestDispatcher rd = 
								filterConfig.getServletContext().
									getRequestDispatcher("/jsp/util/acesso_negado_funcionalidade.jsp");
							
							request.setAttribute("URL", enderecoURL);
							rd.forward(request,response);
						}else{
							doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
						}
						
						//Caso o usuário tenha solicitado uma operação 
					}else if(tipoURL.equalsIgnoreCase("operacao")){
						
						
						/*
						 * Caso o usuário não tenha acesso a operação
						 * exibe a tela de acesso negado para operação
						 * Caso contrário chama o próximo filtro na fila
						 */
						if(!fachada.verificarAcessoPermitidoOperacao(usuarioLogado,
							enderecoURL, colecaoGruposUsuario)){
							
							RequestDispatcher rd = 
								filterConfig.getServletContext().
									getRequestDispatcher("/jsp/util/acesso_negado_operacao.jsp");
							
							request.setAttribute("URL", enderecoURL);
							rd.forward(request,response);
							
						}else{
                            
							if(abrangencia != null){
                                
								if(!fachada.verificarAcessoAbrangencia(abrangencia)){
                                    RequestDispatcher rd = 
                                    	filterConfig.getServletContext().
                                    		getRequestDispatcher("/jsp/util/acesso_negado_abrangencia.jsp");
                                    rd.forward(request,response);
                                }else{
                                	doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
                                }
                            }else{
                            	doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
                            }
						}
					}
				}else{
				
					RequestDispatcher rd = 
						filterConfig.getServletContext().
							getRequestDispatcher("/jsp/util/acesso_negado_funcionalidade.jsp");
					request.setAttribute("URL", enderecoURL);
					rd.forward(request,response);	
				}
				
				
			// Lista de todas as funcionalidades que podem ser acessadas sem que exista um usuario logado na sessao
			} else if (enderecoURL.contains("Logoff") || 
				enderecoURL.contains("Login") || 
				enderecoURL.contains("telaPrincipal") || 
				enderecoURL.contains("executarBatch") || 
				enderecoURL.toLowerCase().contains("pesquisar") || 
				enderecoURL.toLowerCase().contains("relatorio") || 
				enderecoURL.contains("efetuarAlteracaoSenhaAction") || 
				enderecoURL.contains("carregarParametrosAction") || 
				enderecoURL.contains("exibirInformarMelhoriasGcomAction") || 
				enderecoURL.contains("informarMelhoriasGcomAction") || 
				enderecoURL.contains("exibirEfetuarAlteracaoSenhaSimplificadaAction") || 
				enderecoURL.contains("efetuarAlteracaoSenhaSimplificadaAction") || 
				enderecoURL.contains("exibirConsultarSistemaAlteracaoHistoricoAction") || 
				enderecoURL.contains("exibirSistemaHistAlteracaoDetalharPopupAction") ||
				enderecoURL.contains("exibirLembrarSenhaAction") || 
				enderecoURL.contains("lembrarSenhaAction") || 
				enderecoURL.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction") || 
				enderecoURL.contains("emitirSegundaViaContaInternetAcessoGeralAction") || 
				enderecoURL.contains("enviarDadosBancosAcessoGeralAction") || 
				enderecoURL.contains("exibirSelecionarBancoAcessoGeralAction") || 
				enderecoURL.contains("exibirSelecionarBancoAction") || 
				enderecoURL.contains("enviarDadosBancosAction") || 
				enderecoURL.contains("exibirLogTelaInicialAction") || 
				enderecoURL.contains("exibirLogTelaFinalAction") || 
				enderecoURL.contains("exibirConsultarDadosPagamentoAction") || 
				enderecoURL.contains("processarRequisicaoDipositivoMovelAction") || 
				enderecoURL.contains("exibirConsultarSituacaoEspecialFaturamentoPopupAction") || 
				enderecoURL.contains("exibirConsultarSituacaoEspecialCobrancaPopupAction") || 
				enderecoURL.contains("processarRequisicaoTelemetriaAction")||
				enderecoURL.contains("retornarDadosImovelTelemetriaAction")||
				enderecoURL.contains("processarRequisicaoGisAction") ||
				enderecoURL.contains("processarCoordenadasGisAction") ||
				enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction" ) ||
				enderecoURL.contains("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction") ||
				enderecoURL.contains("exibirEmitir2viaDeclaracaoAnualQuitacaoDebitosAction") ||
				enderecoURL.contains("emitir2viaDeclaracaoAnualQuitacaoDebitosAction") ||
				enderecoURL.contains("exibirInserirCadastroEmailClienteAction") ||
				enderecoURL.contains("inserirCadastroEmailClienteAction") || 
				enderecoURL.contains("gerarRelatorio2ViaContaAction") || 
				enderecoURL.contains("exibirInserirCadastroContaBraileAction") ||  
				enderecoURL.contains("inserirCadastroContaBraileAction") || 
				enderecoURL.contains("exibirQuestionarioSatisfacaoAction") || 
				enderecoURL.contains("questionarioSatisfacaoAction") ||
				enderecoURL.contains("processarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction")||
				enderecoURL.contains("exibirInserirClientePortalAction")||
				enderecoURL.contains("inserirClientePortalAction")  ||
				enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAndroidAction" )||
				enderecoURL.contains("processarRequisicaoDispositivoMovelAtualizacaoCadastralAction") ||
				enderecoURL.contains("contasAtrasoWebService") ||
				verificaUrlLojaVirtualCaema(enderecoURL) ||
				verificaUrlLojaVirtualCaer(enderecoURL) ||
				verificaUrlLojaVirtualCaern(enderecoURL)
				){
				
				doFilter(request, response, filterChain, usuarioLogado, enderecoURL);
				
			} else {

				RequestDispatcher rd = filterConfig.getServletContext()
						.getRequestDispatcher(
								"/jsp/util/acesso_negado_funcionalidade.jsp");
				
				request.setAttribute("URL", enderecoURL);
				rd.forward(request, response);

			}
		} catch (ServletException sx) {
			throw sx;
		} catch (IOException iox) {
			throw iox;
		}
	}
	
	/**
	 * Verifa se a url informada eh da loja virtual da Caema
	 * 
	 * @author Rafael Pinto
	 * @date 19/01/2012
	 * 
	 */
	private boolean verificaUrlLojaVirtualCaema(String enderecoURL){
		
		//Caso seja url´s da loja virtual
		boolean ehUrlLoja = false;
		
		if(	enderecoURL.contains("exibirServicosPortalCaemaAction") ||
			enderecoURL.contains("inserirCadastroEmailClientePortalCaemaAction") || 
			enderecoURL.contains("exibirInserirSolicitacaoServicosPortalCaemaAction") || 
			enderecoURL.contains("inserirSolicitacaoServicosPortalCaemaAction") || 
			enderecoURL.contains("exibirInserirCadastroEmailClientePortalCaemaAction") || 
			enderecoURL.contains("emitirSegundaViaContaPortalCaemaAction") ||
			enderecoURL.contains("exibirCanaisAtendimentoCaemaAction")|| 				 
			enderecoURL.contains("exibirInformacoesPortalCaemaAction") ||   
			enderecoURL.contains("exibirInformacoesTarifaSocialPortalCaemaAction") ||   
			enderecoURL.contains("exibirInformacoesNegociacaoDebitosPortalCaemaAction") ||   
			enderecoURL.contains("exibirPortalInformacoesCaemaAction") ||
			enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaemaAction") ||
			enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalCaemaAction") ||
			enderecoURL.contains("exibirConsultarPagamentoFaturaPortalCaemaAction") ||
			enderecoURL.contains("exibirConsultarTabelaServicosPortalCaemaAction") ||
			enderecoURL.contains("exibirConsultarRegulamentoServicosPortalCaemaAction")||
			enderecoURL.contains("exibirConsultarImovelPagamentosPortalCaemaAction")||
			enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCaemaAction") ||
			enderecoURL.contains("exibirInformarVencimentoAlternativoPortalAction") ||
			enderecoURL.contains("exibirAcompanhamentoRAPortalCaemaAction") ||
			enderecoURL.contains("inserirDiaVencimentoAlternativoAction") ||
			enderecoURL.contains("gerarCertidaoNegativaAction") ||
			enderecoURL.contains("exibirValidarCertidaoNegativaDebitoPortalCaemaAction") ||		
			enderecoURL.contains("exibirConsultarTramitePortalCaemaAction") ||	
			enderecoURL.contains("validarCertidaoNegativaDebitoPortalCaemaAction")){

			ehUrlLoja = true;
		}
		
		return ehUrlLoja;
		
	}

	/**
	 * Verifica se a url informada eh da loja virtual da Caer
	 * 
	 * @author Davi Menezes
	 * @date 04/09/2012
	 */
	private boolean verificaUrlLojaVirtualCaer(String enderecoURL){
		//Caso seja url´s da loja virtual da Caer
		boolean ehUrlLoja = false;
		
		if( enderecoURL.contains("exibirServicosPortalCaerAction") || 
			enderecoURL.contains("exibirInserirCadastroEmailClientePortalCaerAction") ||
			enderecoURL.contains("inserirCadastroEmailClientePortalCaerAction") || 
			enderecoURL.contains("exibirInserirSolicitacaoServicosPortalCaerAction") || 
			enderecoURL.contains("inserirSolicitacaoServicosPortalCaerAction") || 
			enderecoURL.contains("exibirCanaisAtendimentoCaerAction") || 
			enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaerAction") ||
			enderecoURL.contains("emitirSegundaViaContaPortalCaerAction") || 
			enderecoURL.contains("exibirInformacoesPortalCaerAction") || 
			enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalCaerAction") ||
			enderecoURL.contains("exibirConsultarPagamentoFaturaPortalCaerAction") ||
			enderecoURL.contains("exibirConsultarTabelaServicosPortalCaerAction") ||
			enderecoURL.contains("exibirAcompanhamentoRAPortalCaerAction") ||
			enderecoURL.contains("exibirConsultarImovelPagamentosPortalCaerAction") ||
			enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCaerAction") ||
			enderecoURL.contains("exibirInformarVencimentoAlternativoPortalCaerAction") || 
			enderecoURL.contains("exibirValidarCertidaoNegativaDebitoPortalCaerAction") || 
			enderecoURL.contains("validarCertidaoNegativaDebitoPortalCaerAction") ||
			enderecoURL.contains("exibirEfetuarParcelamentoDebitosPortalCaerAction") ||
			enderecoURL.contains("efetuarParcelamentoDebitosPortalCaerAction") ||
			enderecoURL.contains("gerarRelatorioDocumentosParcelamentoPortalCaerAction") ||
			enderecoURL.contains("exibirInserirCadastroContaBraillePortalCaerAction") ||
			enderecoURL.contains("inserirCadastroContaBraillePortalCaerAction")){
			
			ehUrlLoja = true;
		}
			
		return ehUrlLoja;
	}
	
	/**
	 * Verifica se a url informada eh da loja virtual da Caern
	 * 
	 * @author Rafael Pinto
	 * @date 15/07/2012
	 */
	private boolean verificaUrlLojaVirtualCaern(String enderecoURL){
		//Caso seja url´s da loja virtual da Caern
		boolean ehUrlLoja = false;
		
		if( enderecoURL.contains("exibirServicosPortalCaernAction") || 
			enderecoURL.contains("exibirInserirCadastroEmailClientePortalCaernAction") ||
			enderecoURL.contains("inserirCadastroEmailClientePortalCaernAction") || 
			enderecoURL.contains("exibirInserirSolicitacaoServicosPortalCaernAction") || 
			enderecoURL.contains("inserirSolicitacaoServicosPortalCaernAction") || 
			enderecoURL.contains("exibirCanaisAtendimentoCaernAction") || 
			enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaernAction") ||
			enderecoURL.contains("emitirSegundaViaContaPortalCaernAction") || 
			enderecoURL.contains("exibirInformacoesPortalCaernAction") || 
			enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalCaernAction") ||
			enderecoURL.contains("exibirConsultarPagamentoFaturaPortalCaernAction") ||
			enderecoURL.contains("exibirConsultarTabelaServicosPortalCaernAction") ||
			enderecoURL.contains("exibirAcompanhamentoRAPortalCaernAction") ||
			enderecoURL.contains("exibirConsultarImovelPagamentosPortalCaernAction") ||
			enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCaernAction") ||
			enderecoURL.contains("exibirInformarVencimentoAlternativoPortalCaernAction") || 
			enderecoURL.contains("exibirValidarCertidaoNegativaDebitoPortalCaernAction") || 
			enderecoURL.contains("validarCertidaoNegativaDebitoPortalCaernAction") ||
			enderecoURL.contains("exibirEfetuarParcelamentoDebitosPortalCaernAction") ||
			enderecoURL.contains("efetuarParcelamentoDebitosPortalCaernAction") ||
			enderecoURL.contains("gerarRelatorioDocumentosParcelamentoPortalCaernAction")||
			enderecoURL.contains("emitirContratoAdesaoAction")||
			enderecoURL.contains("exibirInserirCadastroContaBraillePortalCaernAction") ||
			enderecoURL.contains("inserirCadastroContaBraillePortalCaernAction")) {
			
			ehUrlLoja = true;
		}
			
		return ehUrlLoja;
	}
	
	private void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain, Usuario usuarioLogado, String enderecoURL) throws IOException, ServletException {
		long tempoInicial =System.currentTimeMillis();
		
		filterChain.doFilter(request, response);
		
		long tempoFinal = System.currentTimeMillis() - tempoInicial;
				
		Logger log = Logger.getLogger("GSAN_TEMPO");
		
		if (usuarioLogado != null) { 
			log.debug(usuarioLogado.getNomeUsuario()+":"+ enderecoURL+": " + tempoFinal + "ms");
		} else {
			log.debug(enderecoURL+": " + tempoFinal + "ms");
		}
	}


	private void setCaminhoMenu(HttpSession sessao, Integer idFuncionalidade, String enderecoURL, String tipoURL) {
		String caminhoMenuFuncionalidade = "";
		String caminhoHelpFuncionalidade = ConstantesSistema.URL_HELP;

		Fachada fachada = Fachada.getInstancia();

		//Ajuste que monta o caminho correto da funcionalidade Manter Vinculos de Imoveis para Rateio de Consumo.
		//Pois o mesmo re-utiliza classes da funcionalidade Manter Imovel, ocorrendo conflito no momento que carrega o caminho do menu.
		if ( sessao != null && sessao.getAttribute("caminhoMenuFuncionalidade") != null && 
				sessao.getAttribute("caminhoMenuFuncionalidade").equals("Gsan -> Micromedicao -> Medicao Individualizada -> Manter Vinculos de Imoveis para Rateio de Consumo") ) {
		
			caminhoMenuFuncionalidade = "Gsan -> Micromedicao -> Medicao Individualizada -> Manter Vinculos de Imoveis para Rateio de Consumo";
		
		} else if (tipoURL != null) {
			
			Funcionalidade funcionalidade = recuperarFuncionalidadePelaURL(enderecoURL, tipoURL, fachada);
			
			if (idFuncionalidade != null) {
				FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
				filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("modulo");
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
				
				Collection colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade, Funcionalidade.class.getName());
				funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			} else {
				funcionalidade = recuperarFuncionalidadePelaURL(enderecoURL, tipoURL, fachada);
			}

			//CARREGANDO A URL DO HELP POR FUNCIONALIDADE
			if (funcionalidade != null) {
				String url = funcionalidade.getCaminhoUrlHelp();
			
				if (!Util.verificarNaoVazio(url) && funcionalidade.getModulo() != null) {
					url = funcionalidade.getModulo().getCaminhoUrlHelp();
				}
			
				if (Util.verificarNaoVazio(url)) {
					caminhoHelpFuncionalidade += "?" + url.trim();
				}
			}

			if (funcionalidade != null && funcionalidade.getFuncionalidadeCategoria() != null) {
				FuncionalidadeCategoria funcionalidadeCategoria = funcionalidade.getFuncionalidadeCategoria();

				caminhoMenuFuncionalidade = funcionalidadeCategoria
						.getNome() + " -> " + funcionalidade.getDescricao();

				while (funcionalidadeCategoria
						.getFuncionalidadeCategoriaSuperior() != null) {

					FiltroFuncionalidadeCategoria filtroFuncionalidadeCategoria = new FiltroFuncionalidadeCategoria();
					filtroFuncionalidadeCategoria
							.adicionarParametro(new ParametroSimples(
									FiltroFuncionalidadeCategoria.ID,
									funcionalidadeCategoria
											.getFuncionalidadeCategoriaSuperior()
											.getId()));

					Collection colecaoFuncionalidadeCategoria = fachada
							.pesquisar(filtroFuncionalidadeCategoria,
									FuncionalidadeCategoria.class.getName());

					funcionalidadeCategoria = (FuncionalidadeCategoria) Util
							.retonarObjetoDeColecao(colecaoFuncionalidadeCategoria);

					caminhoMenuFuncionalidade = funcionalidadeCategoria
							.getNome()
							+ " -> " + caminhoMenuFuncionalidade;
				}
			}

		}
		
		if(enderecoURL != null) { 				
			if(enderecoURL.contains("informarParametrosSistemaDadosGeraisEmpresaAction") ||
					 enderecoURL.contains("informarParametrosSistemaFaturamentoTarifaSocialAction") || 
				 	 enderecoURL.contains("informarParametrosSistemaArrecadacaoFinanceiroAction") ||
					 enderecoURL.contains("informarParametrosSistemaAtendimentoPublicoSegurancaAction") ||
					 enderecoURL.contains("informarParametrosSistemaMicromedicaoCobrancaAction")){
				caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Sistema Parametro -> Informa Parametros do Sistema";
			}else if(enderecoURL.contains("exibirRetificarContaAction")){
				caminhoMenuFuncionalidade = "Gsan -> Faturamento -> Conta -> Manter Conta";
			}else if(enderecoURL.contains("exibirInserirClienteNomeTipoAction") ||
					 enderecoURL.contains("inserirClienteNomeTipoAction") ||
					 enderecoURL.contains("inserirClientePessoaAction") ||
					 enderecoURL.contains("exibirInserirClientePessoaAction") ||
					 enderecoURL.contains("exibirInserirClienteEnderecoAction") ||
					 enderecoURL.contains("inserirClienteEnderecoAction")){
				caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Cliente -> Inserir Cliente";
			}else if(enderecoURL.contains("exibirAtualizarClienteNomeTipoAction") || 
					 enderecoURL.contains("atualizarClienteNomeTipoAction") || 
					 enderecoURL.contains("atualizarClientePessoaAction") || 
					 enderecoURL.contains("exibirAtualizarClientePessoaAction") || 
					 enderecoURL.contains("atualizarClienteEnderecoAction") || 
					 enderecoURL.contains("exibirAtualizarClienteEnderecoAction")){
				caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Cliente -> Manter Cliente";
			}else if(enderecoURL.contains("exibirInserirComandoNegativacaoPorCriterioAction") ||
					 enderecoURL.contains("exibirInserirComandoNegativacaoMatriculaImovelAction") ||
					 enderecoURL.contains("exibirInserirComandoNegativacaoDadosGeraisAction") || 
					 enderecoURL.contains("exibirInserirComandoNegativacaoLocalizacaoAction") ||
					 enderecoURL.contains("exibirInserirComandoNegativacaoDadosImovelAction") ||
					 enderecoURL.contains("exibirInserirComandoNegativacaoDadosDebitoAction") ||
					 enderecoURL.contains("inserirComandoNegativacaoDadosGeraisAction") ||
					 enderecoURL.contains("inserirComandoNegativacaoDadosDebitoAction") ||
					 enderecoURL.contains("inserirComandoNegativacaoDadosImovelAction") ||
					 enderecoURL.contains("inserirComandoNegativacaoLocalizacaoAction")){
				caminhoMenuFuncionalidade = "Gsan -> Cobranca -> Negativacao -> Inserir Comando de Negativacao";
			}else if(enderecoURL.contains("exibirInserirComandoNegativacaoPorGuiaPagamentoAction") ||
					 enderecoURL.contains("exibirInserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction") || 
					 enderecoURL.contains("exibirInserirComandoNegativacaoPorGuiaPagamentoDadosClienteAction") ||
					 enderecoURL.contains("inserirComandoNegativacaoPorGuiaPagamentoDadosGeraisAction") ||
					 enderecoURL.contains("inserirComandoNegativacaoPorGuiaPagamentoDadosClienteAction")) {
				caminhoMenuFuncionalidade = "Gsan -> Cobranca -> Negativacao -> Inserir Comando de Negativacao por Guia de Pagamento";
			}else if(enderecoURL.contains("exibirFiltrarLogradouroActio")||
					 enderecoURL.contains("filtrarLogradouroAction")){
				caminhoMenuFuncionalidade = "Gsan -> Cadastro -> Endereco -> Logradouro -> Manter Logradouro";
			}else if(enderecoURL.contains("exibirEfetuarLigacaoEsgotoAction")){
				caminhoMenuFuncionalidade = "Gsan -> Atendimento ao Publico -> Ligacao de Esgoto -> Efetuar Ligacao de Esgoto";
			}
		}

		if (sessao != null) {		
		   sessao.setAttribute("caminhoMenuFuncionalidade", caminhoMenuFuncionalidade);
		   sessao.setAttribute("caminhoHelpFuncionalidade", caminhoHelpFuncionalidade);
		}
	}

	private Funcionalidade recuperarFuncionalidadePelaURL(String enderecoURL, String tipoURL, Fachada fachada) {
		
		Funcionalidade retorno = null;
		
		if (enderecoURL.startsWith("/")) {
			enderecoURL = enderecoURL.substring(1);
		}
		
		if (tipoURL.equalsIgnoreCase("funcionalidade")) {
			// Cria o filtro para pesquisar a funcionalidade com a url informada
			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
			filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("modulo");
			
			String parametro = null;
			
			if (enderecoURL.contains("?")) {
				parametro = enderecoURL.substring(enderecoURL.indexOf("?") + 1, enderecoURL.length());
				enderecoURL = enderecoURL.substring(0, enderecoURL.indexOf("?"));
			}
			
			if (parametro != null) {
				filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
						FiltroFuncionalidade.CAMINHO_URL, enderecoURL));
				filtroFuncionalidade.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroFuncionalidade.CAMINHO_URL, parametro));
			} else {
				filtroFuncionalidade.adicionarParametro(new ParametroSimples(
						FiltroFuncionalidade.CAMINHO_URL, enderecoURL));
			}
			
			// Pesquisa a funcionalidade com a url informada
			Collection colecaoFuncionalidade = fachada.pesquisar(
					filtroFuncionalidade, Funcionalidade.class.getName());
			
			if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {
				retorno = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
			} else {
				filtroFuncionalidade = new FiltroFuncionalidade();
				filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("funcionalidadeCategoria");
				filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade("modulo");
				
				filtroFuncionalidade.adicionarParametro(new ComparacaoTexto(
						FiltroFuncionalidade.CAMINHO_URL, enderecoURL));
				
				colecaoFuncionalidade = fachada.pesquisar(
						filtroFuncionalidade, Funcionalidade.class.getName());
				
				if (colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()) {
					retorno = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
				}
			}
			
		} else {
			// Cria o filtro para pesquisar a operação da url informada
			// e carrega a funcionalidade da operação
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			// filtroOperacao.adicionarParametro(new
			// ParametroSimples(FiltroOperacao.CAMINHO_URL,urlOperacao));
			if (enderecoURL.startsWith("/")) {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(
						FiltroOperacao.CAMINHO_URL, enderecoURL
								.substring(1)));
			} else {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(
						FiltroOperacao.CAMINHO_URL, enderecoURL));
			}

			filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade.funcionalidadeCategoria");
			filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade.modulo");

			// Pesquisa a operação no sistema com a url informada
			Collection colecaoOperacao = fachada.pesquisar(filtroOperacao,
					Operacao.class.getName());

			if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {

				Operacao operacao = (Operacao) Util
						.retonarObjetoDeColecao(colecaoOperacao);

				retorno = operacao.getFuncionalidade();
				
			}
		}
		return retorno;
	}

	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 *
	 */
	public void destroy() {
	}
}
