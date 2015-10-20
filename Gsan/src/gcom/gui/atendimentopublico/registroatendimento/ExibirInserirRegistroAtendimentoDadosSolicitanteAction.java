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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os par�metros para realiza��o
 * da inser��o de um R.A (Aba n� 03 - Dados do solicitante) 
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class ExibirInserirRegistroAtendimentoDadosSolicitanteAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirRegistroAtendimentoDadosSolicitante");
	
        InserirRegistroAtendimentoActionForm form = 
    	(InserirRegistroAtendimentoActionForm) actionForm;

    	HttpSession sessao = httpServletRequest.getSession(false);

    	Fachada fachada = Fachada.getInstancia();
    	
    	httpServletRequest.setAttribute("nomeCampo", "idCliente");
    	
    	sessao.removeAttribute("gis");	
		
//    	this.verificarInformacoesRASimplificada(form, sessao, fachada);
    		
    	/*
    	 * Pesquisas realizadas a partir do ENTER
    	 * ===========================================================================================================
    	 */
    		
    	String pesquisarCliente = httpServletRequest
    	.getParameter("pesquisarCliente");

    	if (pesquisarCliente != null
    			&& !pesquisarCliente.equalsIgnoreCase("")) {
    			
    		//[FS0027] - Verificar informa��o do im�vel
    		Cliente cliente = fachada.verificarInformacaoImovel(Util.converterStringParaInteger(form.getIdCliente()),
    		Util.converterStringParaInteger(form.getIdImovel()), false);
    		
    		if (cliente == null) {
    		
    			form.setIdCliente("");
    			form.setNomeCliente("Cliente inexistente");
    		
    			httpServletRequest.setAttribute("corCliente", "exception");
    			httpServletRequest.setAttribute("nomeCampo", "idCliente");
    		
    		} else {
    				
    			form.setIdCliente(cliente.getId().toString());
    			form.setNomeCliente(cliente.getNome());
    				
    			Collection colecaoEnderecos = fachada.pesquisarEnderecosClienteAbreviado(cliente.getId());
    				
    			if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()){
    					
    				Iterator endCorrespondencia = colecaoEnderecos.iterator();
    				ClienteEndereco endereco = null;
    					
    				while(endCorrespondencia.hasNext()){
    						
    					endereco = (ClienteEndereco) endCorrespondencia.next();
    						
    					if (endereco.getIndicadorEnderecoCorrespondencia() != null &&
    						endereco.getIndicadorEnderecoCorrespondencia()
    						.equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)){
    							
    						form.setClienteEnderecoSelected(endereco.getId().toString());
    						break;
    					}
    				}
    					
    				sessao.setAttribute("colecaoEnderecosAbaSolicitante", colecaoEnderecos);
    				sessao.setAttribute("enderecoPertenceCliente", "OK");
    			}
    				
    			Collection colecaoFones = fachada.pesquisarClienteFone(cliente.getId());
    				
    			if (colecaoFones != null && !colecaoFones.isEmpty()){
    					
    				Iterator fonePrincipal = colecaoFones.iterator();
    				ClienteFone fone = null;
    					
    				while(fonePrincipal.hasNext()){
    						
    					fone = (ClienteFone) fonePrincipal.next();
    						
    					if (fone.getIndicadorTelefonePadrao() != null &&
    						fone.getIndicadorTelefonePadrao()
    						.equals(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL)){
    							
    						form.setClienteFoneSelected(fone.getId().toString());
    						break;
    					}
    				}
    					
    				sessao.setAttribute("colecaoFonesAbaSolicitante", colecaoFones);
    			}
    		
    			this.limparUnidadeSolicitante(sessao);
    			this.limparNomeSolicitante(sessao);
    				
    			sessao.setAttribute("desabilitarDadosSolicitanteUnidade", "OK");
    			sessao.setAttribute("desabilitarDadosSolicitanteFuncionario", "OK");
    			sessao.setAttribute("desabilitarDadosSolicitanteNome", "OK");
    			sessao.setAttribute("habilitarAlteracaoEnderecoSolicitante", "NAO");
    				
    		}
    	}
    		
    		
    		
    	String pesquisarUnidade = httpServletRequest.getParameter("pesquisarUnidade");

    	if (pesquisarUnidade != null && !pesquisarUnidade.equalsIgnoreCase("")) {
    		
    		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
    		
    		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
    		FiltroUnidadeOrganizacional.ID, form.getIdUnidadeSolicitante()));
    		
    		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
    		FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
    		
    		Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
    		UnidadeOrganizacional.class.getName());
    		
    		if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
    		
    			form.setIdUnidadeSolicitante("");
    			form.setDescricaoUnidadeSolicitante("Unidade Solicitante inexistente");
    		
    			httpServletRequest.setAttribute("corUnidadeSolicitante", "exception");
    			httpServletRequest.setAttribute("nomeCampo", "idUnidadeSolicitante");
    		
    		} else {
    			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
    					.retonarObjetoDeColecao(colecaoUnidade);
    			
    			sessao.setAttribute("enderecoPertenceCliente", "OK");
    				
    			form.setIdUnidadeSolicitante(unidade.getId().toString());
    			form.setDescricaoUnidadeSolicitante(unidade.getDescricao());
    				
    			this.limparCliente(sessao);
    			this.limparNomeSolicitante(sessao);
    				
    			httpServletRequest.setAttribute("nomeCampo", "idFuncionarioResponsavel");
    			
    			sessao.setAttribute("desabilitarDadosSolicitanteCliente", "OK");
    			sessao.setAttribute("desabilitarDadosSolicitanteNome", "OK");
    			sessao.setAttribute("habilitarAlteracaoEnderecoSolicitante", "NAO");
    			sessao.setAttribute("habilitarAlteracaoEnderecoAbaSolicitante", "NAO");
    			
    			
    				
    		}
    	}
    		
    		
    	String pesquisarFuncionario = httpServletRequest.getParameter("pesquisarFuncionario");

    	if (pesquisarFuncionario != null && !pesquisarFuncionario.equalsIgnoreCase("")) {
    		
    		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
    		
    		filtroFuncionario.adicionarParametro(new ParametroSimples(
    		FiltroFuncionario.ID, form.getIdFuncionarioResponsavel()));
    			
    		filtroFuncionario.adicionarParametro(new ParametroSimples(
    		FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, form.getIdUnidadeSolicitante()));
    		
    		Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario,
    		Funcionario.class.getName());
    		
    		if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {
    		
    			form.setIdFuncionarioResponsavel("");
    			form.setNomeFuncionarioResponsavel("Funcion�rio inexistente");
    		
    			httpServletRequest.setAttribute("corFuncionario", "exception");
    			httpServletRequest.setAttribute("nomeCampo", "idUnidadeSolicitanteInformar");
    		
    		} else {
    			Funcionario funcionario = (Funcionario) Util
    					.retonarObjetoDeColecao(colecaoFuncionario);
    		
    			form.setIdFuncionarioResponsavel(funcionario.getId().toString());
    			form.setNomeFuncionarioResponsavel(funcionario.getNome());
    				
    			this.limparCliente(sessao);
    			this.limparNomeSolicitante(sessao);
    				
    			sessao.setAttribute("desabilitarDadosSolicitanteCliente", "OK");
    			sessao.setAttribute("desabilitarDadosSolicitanteNome", "OK");
    			sessao.setAttribute("habilitarAlteracaoEnderecoSolicitante", "SIM");
    		}
    	}
    		
    	/*
    	 * Fim das pesquisas realizadas pelo ENTER
    	 * ===========================================================================================================
    	 * ===========================================================================================================
    	 */
    		
    	String informadoNomeSolicitante = httpServletRequest
    	.getParameter("informadoNomeSolicitante");
    		
    	if (informadoNomeSolicitante != null
    			&& !informadoNomeSolicitante.equalsIgnoreCase("")){
    			
    		this.limparCliente(sessao);
    		this.limparUnidadeSolicitante(sessao);
    			
    		sessao.setAttribute("desabilitarDadosSolicitanteCliente", "OK");
    		sessao.setAttribute("desabilitarDadosSolicitanteUnidade", "OK");
    		sessao.setAttribute("desabilitarDadosSolicitanteFuncionario", "OK");
    		sessao.setAttribute("habilitarAlteracaoEnderecoSolicitante", "SIM");
    			
    		httpServletRequest.setAttribute("nomeCampo", "nomeSolicitante");
    	}
    		
    		
    	String limparCliente = httpServletRequest
    	.getParameter("limparClienteSolicitante");
    		
    	if (limparCliente != null
    			&& !limparCliente.equalsIgnoreCase("")){
    			
    		this.limparCliente(sessao);
    		form.setIdCliente("");
    		form.setNomeCliente("");
    		httpServletRequest.setAttribute("nomeCampo", "idCliente");
    	}
    		
    		
    	String limparUnidadeSolicitante = httpServletRequest
    	.getParameter("limparUnidadeSolicitante");
    		
    	if (limparUnidadeSolicitante != null
    			&& !limparUnidadeSolicitante.equalsIgnoreCase("")){
    			
    		this.limparUnidadeSolicitante(sessao);
    			
    		httpServletRequest.setAttribute("nomeCampo", "idCliente");
    	}
    		
    	String limparNomeSolicitante = httpServletRequest
    	.getParameter("limparNomeSolicitante");
    		
    	if (limparNomeSolicitante != null
    			&& !limparNomeSolicitante.equalsIgnoreCase("")){
    		
    		this.limparNomeSolicitante(sessao);
    			
    		httpServletRequest.setAttribute("nomeCampo", "idCliente");
    	}
    	
    	
    	//Remover Telefone
    	String removerFone = httpServletRequest
    	.getParameter("removerFone");
    	
    	if (removerFone != null && !removerFone.equalsIgnoreCase("")){
    		
    		long objetoRemocao = (Long.valueOf(httpServletRequest.getParameter("removerFone"))).longValue();
    		Collection colecaoFones = (Collection) sessao.getAttribute("colecaoFonesAbaSolicitante");
    		Iterator iteratorColecaoFones = colecaoFones.iterator();
    		ClienteFone clienteFone = null;
    		
    		while (iteratorColecaoFones.hasNext()){
    			clienteFone = (ClienteFone) iteratorColecaoFones.next();
    			
    			if (obterTimestampIdObjeto(clienteFone) == objetoRemocao){
    				colecaoFones.remove(clienteFone);
    				break;
    			}
    		}
    		
    	}
    	
    	
    	/*
		 * Adicionar Fone
		 */
		String adicionarFone = httpServletRequest
				.getParameter("telaRetorno");

		if (adicionarFone != null
				&& !adicionarFone.trim().equalsIgnoreCase("")) {
			
			retorno = actionMapping
			.findForward("informarFone");
		}
    	
    	
    	/*
		 * Removendo endere�o
		 */
		String removerEndereco = httpServletRequest.getParameter("removerEndereco");

		if (removerEndereco != null
				&& !removerEndereco.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoEnderecosAbaSolicitante") != null) {

				Collection enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecosAbaSolicitante");

				if (!enderecos.isEmpty()) {
					enderecos.remove(enderecos.iterator().next());
				}
			}
		}
		
		
		/*
		 * Adicionar endere�o
		 */
		String adicionarEndereco = httpServletRequest
				.getParameter("tipoPesquisaEndereco");

		if (adicionarEndereco != null
				&& !adicionarEndereco.trim().equalsIgnoreCase("")) {
			
			retorno = actionMapping.findForward("informarEndereco");
			httpServletRequest.setAttribute("mostrarPerimetro", "sim");
		}
    	
    	
    	//Selecionar Fone com indicador padr�o
    	String foneIndicadorPadrao = httpServletRequest.getParameter("clienteFoneSelected");
    	
    	this.selecionarFoneComIndicadorPadrao(foneIndicadorPadrao, sessao);
    	
    	
    	
    	//Disponibilizando os dados do cliente usu�rio do im�vel
    	String retornoEndereco = httpServletRequest.getParameter("retornoEndereco");
    	String retornoFone = httpServletRequest.getParameter("retornoFone");
    	
    	if (form.getIdImovel() != null && !form.getIdImovel().equalsIgnoreCase("") &&
    		(pesquisarCliente == null || pesquisarCliente.equalsIgnoreCase("")) &&
    		(pesquisarUnidade == null || pesquisarUnidade.equalsIgnoreCase("")) &&
    		(pesquisarFuncionario == null || pesquisarFuncionario.equalsIgnoreCase("")) &&
    		(informadoNomeSolicitante == null || informadoNomeSolicitante.equalsIgnoreCase("")) &&
    		(limparCliente == null || limparCliente.equalsIgnoreCase("")) &&
    		(limparUnidadeSolicitante == null || limparUnidadeSolicitante.equalsIgnoreCase("")) &&
    		(limparNomeSolicitante == null || limparNomeSolicitante.equalsIgnoreCase("")) &&
    		(removerFone == null || removerFone.equalsIgnoreCase("")) &&
    		(removerEndereco == null || removerEndereco.equalsIgnoreCase("")) &&
    		(retornoEndereco == null || retornoEndereco.equalsIgnoreCase("")) &&
    		(adicionarEndereco == null || adicionarEndereco.equalsIgnoreCase("")) &&
    		(adicionarFone == null || adicionarFone.equalsIgnoreCase("")) &&
    		(retornoFone == null || retornoFone.equalsIgnoreCase("")) &&
//    		(form.getIdCliente() == null || form.getIdCliente().equalsIgnoreCase("")) &&
    		(form.getIdUnidadeSolicitante() == null || form.getIdUnidadeSolicitante().equalsIgnoreCase("")) &&
    		(form.getIdFuncionarioResponsavel() == null || form.getIdUnidadeSolicitante().equalsIgnoreCase("")) &&
    		(form.getNomeSolicitante() == null || form.getNomeSolicitante().equalsIgnoreCase(""))){
    	
    		Cliente clienteUsuarioImovel = fachada.pesquisarClienteUsuarioImovel(
    		Util.converterStringParaInteger(form.getIdImovel()));
    		
    		if (clienteUsuarioImovel != null && 
    			( //(form.getIdCliente() == null || form.getIdCliente().equalsIgnoreCase("")) &&
    			  (form.getIdUnidadeSolicitante() == null || form.getIdUnidadeSolicitante().equalsIgnoreCase("")) &&
    			  (form.getIdFuncionarioResponsavel() == null || form.getIdFuncionarioResponsavel()
    			   .equalsIgnoreCase("")) &&
    			   (form.getNomeSolicitante() == null || form.getNomeSolicitante()
    	    		.equalsIgnoreCase(""))) || form.getIdImovel().equalsIgnoreCase(form.getIdImovelAssociacaoCliente())){
    			
    			form.setIdCliente(clienteUsuarioImovel.getId().toString());
    			form.setNomeCliente(clienteUsuarioImovel.getNome());
    			
    			form.setIdImovelAssociacaoCliente(form.getIdImovel());
    			
    			Collection colecaoEnderecos = fachada.pesquisarEnderecosClienteAbreviado(clienteUsuarioImovel.getId());
				
    			if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()){
    					
    				Iterator endCorrespondencia = colecaoEnderecos.iterator();
    				ClienteEndereco endereco = null;
    					
    				while(endCorrespondencia.hasNext()){
    						
    					endereco = (ClienteEndereco) endCorrespondencia.next();
    						
    					if (endereco.getIndicadorEnderecoCorrespondencia() != null &&
    						endereco.getIndicadorEnderecoCorrespondencia()
    						.equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)){
    							
    						form.setClienteEnderecoSelected(endereco.getId().toString());
    						break;
    					}
    				}
    					
    				sessao.setAttribute("colecaoEnderecosAbaSolicitante", colecaoEnderecos);
    				sessao.setAttribute("enderecoPertenceCliente", "OK");
    			}
    				
    			Collection colecaoFones = fachada.pesquisarClienteFone(clienteUsuarioImovel.getId());
    				
    			if (colecaoFones != null && !colecaoFones.isEmpty()){
    					
    				Iterator fonePrincipal = colecaoFones.iterator();
    				ClienteFone fone = null;
    					
    				while(fonePrincipal.hasNext()){
    						
    					fone = (ClienteFone) fonePrincipal.next();
    						
    					if (fone.getIndicadorTelefonePadrao() != null &&
    						fone.getIndicadorTelefonePadrao()
    						.equals(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL)){
    							
    						form.setClienteFoneSelected(fone.getId().toString());
    						break;
    					}
    				}
    					
    				sessao.setAttribute("colecaoFonesAbaSolicitante", colecaoFones);
    			}
    		
    			this.limparUnidadeSolicitante(sessao);
    			this.limparNomeSolicitante(sessao);
    				
    			sessao.setAttribute("desabilitarDadosSolicitanteUnidade", "OK");
    			sessao.setAttribute("desabilitarDadosSolicitanteFuncionario", "OK");
    			sessao.setAttribute("desabilitarDadosSolicitanteNome", "OK");
    			sessao.setAttribute("habilitarAlteracaoEnderecoSolicitante", "NAO");

    		}
    	}
    	
    	
    	if (form.getIdImovel() == null || form.getIdImovel().equalsIgnoreCase("") &&
        	(pesquisarCliente == null || pesquisarCliente.equalsIgnoreCase("")) &&
        	(pesquisarUnidade == null || pesquisarUnidade.equalsIgnoreCase("")) &&
        	(pesquisarFuncionario == null || pesquisarFuncionario.equalsIgnoreCase("")) &&
        	(informadoNomeSolicitante == null || informadoNomeSolicitante.equalsIgnoreCase("")) &&
        	(limparCliente == null || limparCliente.equalsIgnoreCase("")) &&
        	(limparUnidadeSolicitante == null || limparUnidadeSolicitante.equalsIgnoreCase("")) &&
        	(limparNomeSolicitante == null || limparNomeSolicitante.equalsIgnoreCase("")) &&
        	(removerFone == null || removerFone.equalsIgnoreCase("")) &&
        	(removerEndereco == null || removerEndereco.equalsIgnoreCase("")) &&
        	(retornoEndereco == null || retornoEndereco.equalsIgnoreCase("")) &&
        	(retornoFone == null || retornoFone.equalsIgnoreCase("")) &&
        	(adicionarEndereco == null || adicionarEndereco.equalsIgnoreCase("")) &&
    		(adicionarFone == null || adicionarFone.equalsIgnoreCase("")) &&
        	sessao.getAttribute("colecaoEnderecos") != null &&
        	(sessao.getAttribute("colecaoEnderecosAbaSolicitante") == null ||
        	(sessao.getAttribute("colecaoEnderecosAbaSolicitante") != null && 
        	((Collection) sessao.getAttribute("colecaoEnderecosAbaSolicitante")).isEmpty()))){
    		
    		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
    		
    		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
    		
    		ClienteEndereco clienteEndereco = new ClienteEndereco();
    		
    		clienteEndereco.setComplemento(imovel.getComplementoEndereco());
    		clienteEndereco.setEnderecoReferencia(imovel.getEnderecoReferencia());
    		clienteEndereco.setLogradouroBairro(imovel.getLogradouroBairro());
    		clienteEndereco.setLogradouroCep(imovel.getLogradouroCep());
    		clienteEndereco.setNumero(imovel.getNumeroImovel());
    		clienteEndereco.setPerimetroInicial(imovel.getPerimetroInicial());
    		clienteEndereco.setPerimetroFinal(imovel.getPerimetroFinal());
    		clienteEndereco.setUltimaAlteracao(new Date());
    		
    		Collection colecaoEnderecosAbaSolicitante = new ArrayList();
    		colecaoEnderecosAbaSolicitante.add(clienteEndereco);
    		
    		sessao.setAttribute("colecaoEnderecosAbaSolicitante", colecaoEnderecosAbaSolicitante);
    		
    	}
    	
    	//[SB0011] 1.5 Verifica Indicador de Envio de email para ServicoTipo
    	if(form != null && form.getEspecificacao() != null && !form.getEspecificacao().equals("")){
    		
    		int idEspecificacao = Integer.parseInt(form.getEspecificacao());
    		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
    		filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
    		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
    		
    		List<SolicitacaoTipoEspecificacao> listaSolicitacaoTipoEspecificacao = new ArrayList<SolicitacaoTipoEspecificacao>(fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName()));
    		if(listaSolicitacaoTipoEspecificacao != null && !listaSolicitacaoTipoEspecificacao.isEmpty()){
    			SolicitacaoTipoEspecificacao especificacao = listaSolicitacaoTipoEspecificacao.get(0);
    			
    			if(especificacao.getServicoTipo()!=null && especificacao.getServicoTipo().getIndicadorEnvioPesquisaSatisfacao().intValue() == 1){
    				sessao.setAttribute("habilitarCampoSatisfacaoEmail", true);
    			}else{
    				sessao.setAttribute("habilitarCampoSatisfacaoEmail", false);
    			}
    		}
    	}
    	return retorno;
    }

//	private void verificarInformacoesRASimplificada(InserirRegistroAtendimentoActionForm form, HttpSession sessao,
//			Fachada fachada) {
//		if (sessao.getAttribute("clienteRASimplificado") != null
//				&& !sessao.getAttribute("clienteRASimplificado").toString().trim().equals("")){
//
//    		Cliente cliente = (Cliente) sessao.getAttribute("clienteRASimplificado");
//    		
//			form.setIdCliente(cliente.getId().toString());
//			form.setNomeCliente(cliente.getNome());
//				
//			Collection colecaoEnderecos = fachada.pesquisarEnderecosClienteAbreviado(cliente.getId());
//				
//			if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()){
//					
//				Iterator endCorrespondencia = colecaoEnderecos.iterator();
//				ClienteEndereco endereco = null;
//					
//				while(endCorrespondencia.hasNext()){
//						
//					endereco = (ClienteEndereco) endCorrespondencia.next();
//						
//					if (endereco.getIndicadorEnderecoCorrespondencia() != null &&
//						endereco.getIndicadorEnderecoCorrespondencia()
//						.equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)){
//							
//						form.setClienteEnderecoSelected(endereco.getId().toString());
//						break;
//					}
//				}
//					
//				sessao.setAttribute("colecaoEnderecosAbaSolicitante", colecaoEnderecos);
//				sessao.setAttribute("enderecoPertenceCliente", "OK");
//			}
//				
//			Collection colecaoFones = fachada.pesquisarClienteFone(cliente.getId());
//				
//			if (colecaoFones != null && !colecaoFones.isEmpty()){
//					
//				Iterator fonePrincipal = colecaoFones.iterator();
//				ClienteFone fone = null;
//					
//				while(fonePrincipal.hasNext()){
//						
//					fone = (ClienteFone) fonePrincipal.next();
//						
//					if (fone.getIndicadorTelefonePadrao() != null &&
//						fone.getIndicadorTelefonePadrao()
//						.equals(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL)){
//							
//						form.setClienteFoneSelected(fone.getId().toString());
//						break;
//					}
//				}
//					
//				sessao.setAttribute("colecaoFonesAbaSolicitante", colecaoFones);
//			}
//			
//			sessao.removeAttribute("idClienteRASimplificado");
//		} 
//    	
//    	if (sessao.getAttribute("nomeSolicitante") != null
//				&& !sessao.getAttribute("nomeSolicitante").toString().trim().equals("")){
//
//			form.setNomeSolicitante(
//				sessao.getAttribute("nomeSolicitante").toString().trim());
//			sessao.removeAttribute("nomeSolicitante");
//			
//		}
//	}
    	
    	
    private void limparCliente(HttpSession sessao){
    		
    		if (sessao.getAttribute("enderecoPertenceCliente") != null){
    			sessao.removeAttribute("colecaoEnderecosAbaSolicitante");
    			sessao.removeAttribute("colecaoFonesAbaSolicitante");
    		}
    		
    		sessao.removeAttribute("enderecoPertenceCliente");
    		sessao.removeAttribute("desabilitarDadosSolicitanteUnidade");
    		sessao.removeAttribute("desabilitarDadosSolicitanteFuncionario");
    		sessao.removeAttribute("desabilitarDadosSolicitanteNome");
    		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");
    	
    }
    	
    	
    private void limparUnidadeSolicitante(HttpSession sessao){
    		
    		sessao.removeAttribute("desabilitarDadosSolicitanteCliente");
    		sessao.removeAttribute("desabilitarDadosSolicitanteNome");
    		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");
    		sessao.removeAttribute("habilitarAlteracaoEnderecoAbaSolicitante");
    	
    }
    	
    	
    private void limparNomeSolicitante(HttpSession sessao){
    		
    		sessao.removeAttribute("desabilitarDadosSolicitanteCliente");
    		sessao.removeAttribute("desabilitarDadosSolicitanteUnidade");
    		sessao.removeAttribute("desabilitarDadosSolicitanteFuncionario");
    		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");
    	
    }
    
    
    private void selecionarFoneComIndicadorPadrao(String objetoFoneIndicadorPadrao, HttpSession sessao){
    	
    	if (objetoFoneIndicadorPadrao != null && !objetoFoneIndicadorPadrao.equalsIgnoreCase("")){
    		
    		long objetoPadrao = (Long.valueOf(objetoFoneIndicadorPadrao)).longValue();
    		
    		if (sessao.getAttribute("colecaoFonesAbaSolicitante") != null){
    			
    			Collection colecaoFones = (Collection) sessao.getAttribute("colecaoFonesAbaSolicitante");
        		Iterator iteratorColecaoFones = colecaoFones.iterator();
        		ClienteFone clienteFone = null;
        		
        		while (iteratorColecaoFones.hasNext()){
        			clienteFone = (ClienteFone) iteratorColecaoFones.next();
        			
        			if (obterTimestampIdObjeto(clienteFone) == objetoPadrao){
        				clienteFone.setIndicadorTelefonePadrao(new Short("1"));
        			}
        			else{
        				clienteFone.setIndicadorTelefonePadrao(null);
        			}
        		}
    		}
    	}
    }

}
