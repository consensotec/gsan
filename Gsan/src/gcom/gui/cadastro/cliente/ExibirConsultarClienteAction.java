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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.bean.ConsultarClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pr�-processamento da p�gina de consultar de cliente
 * [UC0474] Consultar Im�vel
 * 
 * @author Rafael Santos
 * @date 12/09/2006
 */
public class ExibirConsultarClienteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirConsultarCliente");
		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();
		
        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
		

		ConsultarClienteActionForm consultarClienteActionForm  = (ConsultarClienteActionForm) actionForm;

		String codigoCliente = null;
		
		String desabilitarPesquisaCliente = httpServletRequest.getParameter("desabilitarPesquisaCliente");
		
		if(httpServletRequest.getParameter("pesquisarHistorico") != null && httpServletRequest.getParameter("pesquisarHistorico").equals("OK")){
			pesquisarHistoricoCliente(httpServletRequest, consultarClienteActionForm);
		}
		
		if(desabilitarPesquisaCliente != null && !desabilitarPesquisaCliente.equals("")){
			httpServletRequest.setAttribute("desabilitarPesquisaCliente",desabilitarPesquisaCliente);
		}
		
		if(httpServletRequest.getParameter("tipoConsulta") != null && !httpServletRequest.getParameter("tipoConsulta").equals("")){
			codigoCliente = httpServletRequest.getParameter("idCampoEnviarDados");	
		}
		
		String limparForm = httpServletRequest.getParameter("limparForm");
		if(limparForm != null && !limparForm.equals("")){
			sessao.removeAttribute("colecaoTelefones");
			sessao.removeAttribute("colecaoClienteEnderecosHelper");
            sessao.removeAttribute("colecaoClienteFone");

			consultarClienteActionForm.setCodigoCliente(null);
			consultarClienteActionForm.setNomeCliente(null);	
			consultarClienteActionForm.setNomeAbreviado(null);
			consultarClienteActionForm.setDataVencimentoContas(null);
			consultarClienteActionForm.setIndicaorExecucao(null);
			consultarClienteActionForm.setTipoCliente(null);
			consultarClienteActionForm.setEmail(null);
			consultarClienteActionForm.setCpfCliente(null);
			consultarClienteActionForm.setRgCliente(null);
			consultarClienteActionForm.setDataEmissaoRGCliente(null);
			consultarClienteActionForm.setOrgaoEmissorRGCliente(null);
			consultarClienteActionForm.setDataNascimentoCliente(null);
			consultarClienteActionForm.setSexoCliente(null);
			consultarClienteActionForm.setProfissaoCliente(null);
			consultarClienteActionForm.setCnpjCliente(null);
			consultarClienteActionForm.setRamoAtividadeCliente(null);
			
		}
		
		if(!(httpServletRequest.getParameter("tipoConsulta") != null && !httpServletRequest.getParameter("tipoConsulta").equals(""))){
			codigoCliente = consultarClienteActionForm.getCodigoCliente();
		}
		
		if(codigoCliente != null && !codigoCliente.equals("")){
			 Cliente cliente = fachada.consultarCliente(new Integer(codigoCliente.trim()));
			 
			 if(cliente != null){

                httpServletRequest.setAttribute(
                        "codigoClienteNaoEncontrado", null);
                
                //nome do cliente
                consultarClienteActionForm.setCodigoCliente(codigoCliente);
                consultarClienteActionForm.setNomeCliente(cliente.getNome());

                //nome do cliente abreviado
                consultarClienteActionForm.setNomeAbreviado(cliente.getNomeAbreviado());
                
                //dia vencimento contas
                if(cliente.getDataVencimento() != null){
                	consultarClienteActionForm.setDataVencimentoContas(cliente.getDataVencimento().toString());
                }

                //tipo do cliente
                if(cliente.getClienteTipo() != null){
                	consultarClienteActionForm.setTipoCliente(cliente.getClienteTipo().getDescricao());
                }

                //indicador de cobranca
                if(cliente.getIndicadorAcaoCobranca() != null){
                	if(cliente.getIndicadorAcaoCobranca().shortValue() == 1){
                		consultarClienteActionForm.setIndicaorExecucao("SIM");	
                	}else{
                		consultarClienteActionForm.setIndicaorExecucao("N�O");
                	}
                }
                
                //e-mail
                consultarClienteActionForm.setEmail(cliente.getEmail());
                
                //tipo de pessoa
                if(cliente.getClienteTipo() != null && cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null){
                	//pessoa fisica
                	if(cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)){
                		httpServletRequest.setAttribute("indicadorTipoCliente",ClienteTipo.INDICADOR_PESSOA_FISICA.shortValue());
                		
                		//cpf
                		consultarClienteActionForm.setCpfCliente(cliente.getCpfFormatado());
                		
                		//rg
                		if(cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getDescricaoAbreviada() != null && cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getSigla()!= null){
                			consultarClienteActionForm.setRgCliente(Util.formatarRGApresentacao(cliente.getRg(),cliente.getOrgaoExpedidorRg().getDescricaoAbreviada(),cliente.getUnidadeFederacao().getSigla()));
                		} else {
                			consultarClienteActionForm.setRgCliente(null);
                		}
                		
                		//datade emissao
                		if(cliente.getDataEmissaoRg() != null){
                			consultarClienteActionForm.setDataEmissaoRGCliente(Util.formatarData(cliente.getDataEmissaoRg()));
                		} else {
                			consultarClienteActionForm.setDataEmissaoRGCliente(null);
                		}
                			
                		//orgao expedidor
                		if(cliente.getOrgaoExpedidorRg() != null && cliente.getOrgaoExpedidorRg().getDescricaoAbreviada() != null && cliente.getUnidadeFederacao() != null && cliente.getUnidadeFederacao().getSigla()!= null){
                			consultarClienteActionForm.setOrgaoEmissorRGCliente(cliente.getOrgaoExpedidorRg().getDescricaoAbreviada()+"/"+cliente.getUnidadeFederacao().getSigla());
                		} else {
                			consultarClienteActionForm.setOrgaoEmissorRGCliente(null);
                		}
                		
                		//data nascimento
                		consultarClienteActionForm.setDataNascimentoCliente(Util.formatarData(cliente.getDataNascimento()));
                		
                		//sexo
                		if(cliente.getPessoaSexo() != null){
                			consultarClienteActionForm.setSexoCliente(cliente.getPessoaSexo().getDescricao());
                		}
                		
                		//profissao
                		if(cliente.getProfissao() != null){
                			consultarClienteActionForm.setProfissaoCliente(cliente.getProfissao().getDescricao());
                		}
                	//pessoa juridica	
                	}else{
                		httpServletRequest.setAttribute("indicadorTipoCliente",ClienteTipo.INDICADOR_PESSOA_JURIDICA.shortValue());
                		
                		//cnpj
                		consultarClienteActionForm.setCnpjCliente(cliente.getCnpjFormatado());
                		
                		//ramo atividade
                		if(cliente.getRamoAtividade() != null){
                			consultarClienteActionForm.setRamoAtividadeCliente(cliente.getRamoAtividade().getDescricao());
                		}
                		
                	}
                }
                
                //cole��o dos telefones
                Collection colecaoTelefones = fachada.pesquisarClienteFone(new Integer(codigoCliente.trim()));
                sessao.setAttribute("colecaoTelefones",colecaoTelefones);
                
                
                Collection colecaoEndereco = fachada.pesquisarEnderecoCliente(new Integer(codigoCliente.trim()));
                
                Collection colecaoEnderecoCliente = fachada.pesquisarClientesEnderecosAbreviado(new Integer(codigoCliente.trim()));
                
                
                
                Collection colecaoClienteEnderecosHelper = new ArrayList();
                
                //
                if(colecaoEndereco != null && !colecaoEndereco.isEmpty() ){
                	
                	ConsultarClienteHelper consultarClienteHelper = null;
                	
                	Iterator iterator = colecaoEndereco.iterator();
                	Iterator iteratorColecaoEnderecoCliente = colecaoEnderecoCliente.iterator();
                	
                	while (iterator.hasNext() || iteratorColecaoEnderecoCliente.hasNext() ) {
						ClienteEndereco clienteEndereco = (ClienteEndereco) iterator.next();
						
						String endereco = (String) iteratorColecaoEnderecoCliente.next();
						
						consultarClienteHelper = new ConsultarClienteHelper();
						//endereco tipo 
						if(clienteEndereco.getEnderecoTipo() != null){
							consultarClienteHelper.setTipoEndereco(clienteEndereco.getEnderecoTipo().getDescricao());
						}
						
						//indicador de endere�o
						if(clienteEndereco.getIndicadorEnderecoCorrespondencia().equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)){
							consultarClienteHelper.setIndicadorEndereco("SIM");	
						}else{
							consultarClienteHelper.setIndicadorEndereco("N�O");
						}
					
						consultarClienteHelper.setEnderecoClliente(endereco);
						
						colecaoClienteEnderecosHelper.add(consultarClienteHelper);
					}
                	
                }

                sessao.setAttribute("colecaoClienteEnderecosHelper",colecaoClienteEnderecosHelper);
                

                Collection colecaoClienteFone = fachada.pesquisarClienteFone(new Integer(codigoCliente.trim()));
                sessao.setAttribute("colecaoClienteFone",colecaoClienteFone);
			 }else{
                httpServletRequest.setAttribute(
                        "codigoClienteNaoEncontrado", "true");
                
                consultarClienteActionForm.setNomeCliente("CLIENTE INEXISTENTE");

			 }
		}
		

		return retorno;
	}
	
	private void pesquisarHistoricoCliente(HttpServletRequest httpServletRequest, ConsultarClienteActionForm consultarClienteActionForm) {
		Integer idUsuarioAcao = null;
		String[] idOperacoes = null;
		String idUsuario = null;
		Date dataInicial = null;
		Date dataFinal = null;
		Date horaInicial = null;
		Date horaFinal = null;
		String unidadeNegocio = null;

		Hashtable<String, String> argumentos = new Hashtable<String, String>();
		argumentos.put("clie_id", consultarClienteActionForm.getCodigoCliente());

		Collection colecaoAlteracaoCliente = Fachada.getInstancia().pesquisarUsuarioAlteracaoDasOperacoesEfetuadas(idUsuarioAcao,
						idOperacoes, idUsuario, dataInicial, dataFinal,
						horaInicial, horaFinal, argumentos, null, null,
						unidadeNegocio, true);

		httpServletRequest.setAttribute("colecaoAlteracaoCliente", colecaoAlteracaoCliente);
	}
}