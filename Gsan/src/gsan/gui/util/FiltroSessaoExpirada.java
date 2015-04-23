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
package gsan.gui.util;

import java.io.IOException;

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

/**
 * Filtro responsável por verificar se a sessão do usuário expirou o tempo
 * 
 * @author Pedro Alexandre
 * @date 05/07/2006
 */
public class FiltroSessaoExpirada extends HttpServlet implements Filter {
	// Variável que vai armazenar a configuração do filtro
	private FilterConfig filterConfig;

	private static final long serialVersionUID = 1L;

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 * @param filterConfig
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 05/07/2006
	 * 
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) 
		throws ServletException, IOException {
		
		try {
			// Faz um cast no request para recuperar a sessão do usuário
			HttpServletRequest requestPagina = ((HttpServletRequest) request);
			HttpSession sessao = requestPagina.getSession(false);

			// Recupera a url do request
			String enderecoURL = requestPagina.getServletPath();
			


			// Caso a sessão esteja nula(expirou) redireciona o usuário para a
			// página de sessão expirada
			// Caso contrário chama o próximo filtro do web.xml se existir
			if (sessao == null && 
				(!enderecoURL.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction") && 
				!enderecoURL.contains("emitirSegundaViaContaInternetAcessoGeralAction") && 
				!enderecoURL.contains("enviarDadosBancosAcessoGeralAction") && 
				!enderecoURL.contains("exibirSelecionarBancoAction") && 
				!enderecoURL.contains("enviarDadosBancosAction") && 
				!enderecoURL.contains("exibirSelecionarBancoAcessoGeralAction") && 
				!enderecoURL.contains("exibirLogTelaInicialAction")&& 
				!enderecoURL.contains("exibirLogTelaFinalAction")&& 
				!enderecoURL.contains("processarRequisicaoDipositivoMovelAction")&& 
				!enderecoURL.contains("processarRequisicaoTelemetriaAction")&& 
				!enderecoURL.contains("efetuarLoginAction")&& 
				!enderecoURL.contains("processarRequisicaoGisAction")&& 
				!enderecoURL.contains("processarCoordenadasGisAction")&& 
				!enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAction")&& 
                !enderecoURL.contains("processarRequisicaoDispositivoMovelAcompanhamentoServicoAction")&& 
				!enderecoURL.contains("exibirInserirCadastroEmailClienteAction")&& 
				!enderecoURL.contains("inserirCadastroEmailClienteAction")&& 
				!enderecoURL.contains("gerarRelatorio2ViaContaAction")&& 
				!enderecoURL.contains("exibirInserirCadastroContaBraileAction")&& 
				!enderecoURL.contains("inserirCadastroContaBraileAction")&& 
				!enderecoURL.contains("exibirQuestionarioSatisfacaoAction")&& 
				!enderecoURL.contains("questionarioSatisfacaoAction")&& 
				!enderecoURL.contains("processarRequisicaoDispositivoMovelFiscalizacaoAnormalidadeAction") &&  
				!enderecoURL.contains("processarRequisicaoDipositivoMovelImpressaoSimultaneaAndroidAction") &&
				!enderecoURL.contains("processarRequisicaoDispositivoMovelAtualizacaoCadastralAction") &&
				verificaUrlLojaVirtualCaema(enderecoURL) ) &&
				verificaUrlLojaVirtualCaer(enderecoURL) && 
				verificaUrlLojaVirtualCaern(enderecoURL) ){
							
				RequestDispatcher rd = filterConfig.getServletContext()
						.getRequestDispatcher("/jsp/util/sessao_expirada.jsp");
				rd.forward(request, response);
			} else {
				if (enderecoURL
						.contains("exibirEmitirSegundaViaContaInternetAcessoGeralAction")) {
					// Cria uma sessao temporaria para o usuario que entra no
					// EmitirSegundaViaContaInternet sem logar no sistema
					sessao = requestPagina.getSession(true);

				}
				filterChain.doFilter(request, response);
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
		boolean naoEhUrlLoja = false;
		
		if(	!enderecoURL.contains("exibirServicosPortalCaemaAction")&& 
			!enderecoURL.contains("inserirCadastroEmailClientePortalCaemaAction")&& 
			!enderecoURL.contains("exibirInserirSolicitacaoServicosPortalCaemaAction")&& 
			!enderecoURL.contains("inserirSolicitacaoServicosPortalCaemaAction")  && 
			!enderecoURL.contains("exibirInserirCadastroEmailClientePortalCaemaAction")&& 
			!enderecoURL.contains("emitirSegundaViaContaPortalCaemaAction") && 
			!enderecoURL.contains("exibirCanaisAtendimentoCaemaAction") 	&& 
			!enderecoURL.contains("exibirInformacoesPortalCaemaAction")   && 
			!enderecoURL.contains("exibirInformacoesNegociacaoDebitosPortalCaemaAction")&& 
			!enderecoURL.contains("exibirPortalInformacoesCaemaAction") && 
			!enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaemaAction")&& 
			!enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalCaemaAction") && 
			!enderecoURL.contains("exibirConsultarPagamentoFaturaPortalCaemaAction") &&
			!enderecoURL.contains("exibirConsultarTabelaServicosPortalCaemaAction") &&
			!enderecoURL.contains("exibirConsultarRegulamentoServicosPortalCaemaAction") &&
			!enderecoURL.contains("exibirConsultarImovelPagamentosPortalCaemaAction") &&
			!enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCaemaAction") &&
			!enderecoURL.contains("exibirInformarVencimentoAlternativoPortalAction") &&
			!enderecoURL.contains("inserirDiaVencimentoAlternativoAction") &&
			!enderecoURL.contains("gerarCertidaoNegativaAction") &&
			!enderecoURL.contains("exibirValidarCertidaoNegativaDebitoPortalCaemaAction") &&
			!enderecoURL.contains("validarCertidaoNegativaDebitoPortalCaemaAction") ){

			naoEhUrlLoja = true;
		}
		
		return naoEhUrlLoja;
		
	}
	
	/**
	 * Verifica se a URL informada eh da loja virtual da Caer
	 * 
	 * @author Davi Menezes
	 * @date 04/09/2012
	 */
	private boolean verificaUrlLojaVirtualCaer(String enderecoURL){
		//Caso seja url´s da loja virtual
		boolean naoEhUrlLoja = false;
		
		if(	!enderecoURL.contains("exibirServicosPortalCaerAction") &&
			!enderecoURL.contains("exibirCanaisAtendimentoCaerAction") && 
			!enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaerAction") &&
			!enderecoURL.contains("exibirInserirCadastroEmailClientePortalCaerAction") &&
			!enderecoURL.contains("inserirCadastroEmailClientePortalCaerAction") && 
			!enderecoURL.contains("exibirInserirSolicitacaoServicosPortalCaerAction") && 
			!enderecoURL.contains("inserirSolicitacaoServicosPortalCaerAction")  &&
			!enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaerAction") &&
			!enderecoURL.contains("emitirSegundaViaContaPortalCaerAction") && 
			!enderecoURL.contains("exibirInformacoesPortalCaerAction") && 
			!enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalCaerAction") &&
			!enderecoURL.contains("exibirConsultarPagamentoFaturaPortalCaerAction") &&
			!enderecoURL.contains("exibirConsultarTabelaServicosPortalCaerAction") &&
			!enderecoURL.contains("exibirConsultarImovelPagamentosPortalCaerAction") &&
			!enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCaerAction") && 
			!enderecoURL.contains("exibirAcompanhamentoRAPortalCaerAction") && 
			!enderecoURL.contains("exibirInformarVencimentoAlternativoPortalCaerAction") &&
			!enderecoURL.contains("exibirValidarCertidaoNegativaDebitoPortalCaerAction") &&
			!enderecoURL.contains("validarCertidaoNegativaDebitoPortalCaerAction") &&
			!enderecoURL.contains("exibirEfetuarParcelamentoDebitosPortalCaerAction") &&
			!enderecoURL.contains("efetuarParcelamentoDebitosPortalCaerAction") &&
			!enderecoURL.contains("gerarRelatorioDocumentosParcelamentoPortalCaerAction")) {
			
			naoEhUrlLoja = true;
		}
		
		return naoEhUrlLoja;
	}
	
	/**
	 * Verifica se a URL informada eh da loja virtual da Caernn
	 * 
	 * @author Rafael Pinto
	 * @date 15/07/2013
	 */
	private boolean verificaUrlLojaVirtualCaern(String enderecoURL){
		//Caso seja url´s da loja virtual
		boolean naoEhUrlLoja = false;
		
		if(	!enderecoURL.contains("exibirServicosPortalCaernAction") &&
			!enderecoURL.contains("exibirCanaisAtendimentoCaernnAction") && 
			!enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaernAction") &&
			!enderecoURL.contains("exibirInserirCadastroEmailClientePortalCaernAction") &&
			!enderecoURL.contains("inserirCadastroEmailClientePortalCaernAction") && 
			!enderecoURL.contains("exibirInserirSolicitacaoServicosPortalCaernAction") && 
			!enderecoURL.contains("inserirSolicitacaoServicosPortalCaernAction")  &&
			!enderecoURL.contains("exibirLojasAtendimentoPresencialPortalCaernAction") &&
			!enderecoURL.contains("emitirSegundaViaContaPortalCaernAction") && 
			!enderecoURL.contains("exibirInformacoesPortalCaernAction") && 
			!enderecoURL.contains("exibirConsultarEstruturaTarifariaPortalCaernAction") &&
			!enderecoURL.contains("exibirConsultarPagamentoFaturaPortalCaernAction") &&
			!enderecoURL.contains("exibirConsultarTabelaServicosPortalCaernAction") &&
			!enderecoURL.contains("exibirConsultarImovelPagamentosPortalCaernAction") &&
			!enderecoURL.contains("exibirConsultarConsumoHistoricoAguaPortalCaernAction") && 
			!enderecoURL.contains("exibirAcompanhamentoRAPortalCaernAction") && 
			!enderecoURL.contains("exibirInformarVencimentoAlternativoPortalCaernAction") &&
			!enderecoURL.contains("exibirValidarCertidaoNegativaDebitoPortalCaernAction") &&
			!enderecoURL.contains("validarCertidaoNegativaDebitoPortalCaernAction") ) {
			
			naoEhUrlLoja = true;
		}
		
		return naoEhUrlLoja;
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