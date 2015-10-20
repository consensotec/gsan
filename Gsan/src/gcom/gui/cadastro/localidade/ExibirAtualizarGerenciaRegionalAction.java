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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Thiago Ten�rio
 * @date 30/10/2006
 */
public class ExibirAtualizarGerenciaRegionalAction extends GcomAction {
	/**
	 * [UC00] Atualizar Ger�ncia Regional
	 * 
	 * Este caso de uso permite alterar uma valor de uma Ger�ncia Regional
	 * 
	 * @author Thiago Ten�rio, Ivan S�rgio
	 * @date 02/10/2007, 11/06/2007
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarGerenciaRegional");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarGerenciaRegionalActionForm atualizarGerenciaRegionalActionForm = 
			(AtualizarGerenciaRegionalActionForm) actionForm;

		String removerEndereco = (String) httpServletRequest.getParameter("removerEndereco");
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		// Remove o endereco informado.
		if (removerEndereco != null
				&& !removerEndereco.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoEnderecos") != null) {
				Collection enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
				if (!enderecos.isEmpty()) {
					enderecos.remove(enderecos.iterator().next());
				}
			}
		} else {
			if (httpServletRequest.getParameter("menu") != null) {
				atualizarGerenciaRegionalActionForm.setGerenciaRegionalID("");
				atualizarGerenciaRegionalActionForm.setNome("");
				atualizarGerenciaRegionalActionForm.setNomeAbreviado("");
			}

			Fachada fachada = Fachada.getInstancia();
			
			String idGerenciaRegional = null;
		
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
					&& !httpServletRequest.getParameter("idRegistroAtualizacao")
							.equals("")) {
				sessao.removeAttribute("gerenciaRegional");
				sessao.removeAttribute("colecaoGerenciaRegionalTela");
			}

			// Verifica se veio do filtrar ou do manter
			if (httpServletRequest.getParameter("manter") != null) {
				sessao.setAttribute("manter", true);
			} else if (httpServletRequest.getParameter("filtrar") != null) {
				sessao.removeAttribute("manter");
			}
		
			// Usado para o botao Voltar
			if (sessao.getAttribute("gerenciaRegional") != null) {
	   			//Definindo a volta do bot�o Voltar p Filtrar Localidade
	        	sessao.setAttribute("voltar", "manter");
	        }else {
	        	sessao.setAttribute("voltar", "filtrar");
	        }
		
			if(objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")){
				switch (Integer.parseInt(objetoConsulta)) {
		            
					//Gerente da Localidade
	            	case 1:
		            	
		            	this.pesquisarCliente(atualizarGerenciaRegionalActionForm);
		            	break;
	
		            default:
		                break;
		        }
			} 
					
				
					
			
			// Verifica se o servicoCobrancaValor j� est� na sess�o, em caso
			// afirmativo
			// significa que o usu�rio j� entrou na tela e apenas selecionou algum
			// item que deu um reload na tela e em caso negativo significa que ele
			// est� entrando pela primeira vez
			if (sessao.getAttribute("colecaoGerenciaRegionalTela") == null) {
				if (sessao.getAttribute("gerenciaRegional") != null) {
					GerenciaRegional gerenciaRegional = (GerenciaRegional) sessao.getAttribute("gerenciaRegional");
	
					sessao.setAttribute("idGerenciaRegional", gerenciaRegional.getId().toString());
					sessao.setAttribute("gerenciaRegional", gerenciaRegional);
					
					atualizarGerenciaRegionalActionForm.setGerenciaRegionalID(gerenciaRegional.getId().toString());
					atualizarGerenciaRegionalActionForm.setNome(gerenciaRegional.getNome());
					
					atualizarGerenciaRegionalActionForm.setCnpjGerenciaRegional(gerenciaRegional.getCnpjGerenciaRegional());
					
					atualizarGerenciaRegionalActionForm.setNomeAbreviado(gerenciaRegional.getNomeAbreviado());
					atualizarGerenciaRegionalActionForm.setTelefone(gerenciaRegional.getFone());
					atualizarGerenciaRegionalActionForm.setRamal(gerenciaRegional.getRamalFone());
					atualizarGerenciaRegionalActionForm.setFax(gerenciaRegional.getFax());
					atualizarGerenciaRegionalActionForm.setEmail(gerenciaRegional.getEmail());
					
					if(gerenciaRegional.getCliente() != null 
							&& !gerenciaRegional.getCliente().equals("")){
						atualizarGerenciaRegionalActionForm.setIdCliente(
								gerenciaRegional.getCliente().getId().toString());
						atualizarGerenciaRegionalActionForm.setNomeCliente(
								gerenciaRegional.getCliente().getNome());
					}	
					
					if(gerenciaRegional.getIndicadorUso() != null) {
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}else {
						// Seta como Ativo
						Short indicadorUso = 1;
						gerenciaRegional.setIndicadorUso(indicadorUso);
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}
					
					Collection colecaoEnderecos = null;
	
					if (gerenciaRegional.getEnderecoFormatado() != null) {
						colecaoEnderecos = new ArrayList();
						Localidade endereco = new Localidade();
						
						endereco.setLogradouroCep(gerenciaRegional.getLogradouroCep());
						endereco.setLogradouroBairro(gerenciaRegional.getLogradouroBairro());
						endereco.setComplementoEndereco(gerenciaRegional.getComplementoEndereco());
						endereco.setNumeroImovel(gerenciaRegional.getNumeroImovel());
						endereco.setEnderecoReferencia(gerenciaRegional.getEnderecoReferencia());
						
						colecaoEnderecos.add(endereco);
						sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
					}
	
					
					sessao.setAttribute("gerenciaRegionalAtualizar", gerenciaRegional);
					sessao.removeAttribute("gerenciaRegional");

				} else if((httpServletRequest.getParameter("desfazer") != null
					  && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) || 
					  httpServletRequest.getParameter("idRegistroAtualizacao") != null) {

					GerenciaRegional gerenciaRegional = null;
	
					idGerenciaRegional = null;
	
					if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
							|| httpServletRequest.getParameter(
									"idRegistroAtualizacao").equals("")) {
						gerenciaRegional = (GerenciaRegional) sessao
								.getAttribute("gerenciaRegional");
					} else {
						idGerenciaRegional = (String) httpServletRequest
								.getParameter("idRegistroAtualizacao");
						sessao.setAttribute("idRegistroAtualizacao",
								idGerenciaRegional);
					}
					
					if (idGerenciaRegional == null) {
						gerenciaRegional = (GerenciaRegional) sessao.getAttribute("gerenciaRegionalAtualizar");
						idGerenciaRegional = gerenciaRegional.getId().toString();
					}
	
					if (idGerenciaRegional != null) {
	
	

						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("cliente");
						
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID,
										idGerenciaRegional));
	
						Collection<GerenciaRegional> colecaoGerenciaRegional = fachada
								.pesquisar(filtroGerenciaRegional,
										GerenciaRegional.class.getName());
	
						if (colecaoGerenciaRegional == null
								|| colecaoGerenciaRegional.isEmpty()) {
							throw new ActionServletException(
									"atencao.atualizacao.timestamp");
						}
						httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
						gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional.iterator().next();
					}
	
					atualizarGerenciaRegionalActionForm.setGerenciaRegionalID(gerenciaRegional.getId().toString());
					atualizarGerenciaRegionalActionForm.setNome(gerenciaRegional.getNome());
					atualizarGerenciaRegionalActionForm.setNomeAbreviado(gerenciaRegional.getNomeAbreviado());
					atualizarGerenciaRegionalActionForm.setTelefone(gerenciaRegional.getFone());
					atualizarGerenciaRegionalActionForm.setRamal(gerenciaRegional.getRamalFone());
					atualizarGerenciaRegionalActionForm.setFax(gerenciaRegional.getFax());
					atualizarGerenciaRegionalActionForm.setEmail(gerenciaRegional.getEmail());
					
					if(gerenciaRegional.getCliente() != null){
						atualizarGerenciaRegionalActionForm.setIdCliente(
								gerenciaRegional.getCliente().getId().toString());
						atualizarGerenciaRegionalActionForm.setNomeCliente(
								gerenciaRegional.getCliente().getNome());
					}
					
					if(gerenciaRegional.getIndicadorUso() != null) {
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}else {
						// Seta como Ativo
						Short indicadorUso = 1;
						gerenciaRegional.setIndicadorUso(indicadorUso);
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}
	
					sessao.setAttribute("gerenciaRegionalAtualizar", gerenciaRegional);
					
					if (gerenciaRegional.getEnderecoFormatado() != null) {
						Collection colecaoEnderecos = new ArrayList();
						Localidade endereco = new Localidade();
						
						endereco.setLogradouroCep(gerenciaRegional.getLogradouroCep());
						endereco.setLogradouroBairro(gerenciaRegional.getLogradouroBairro());
						endereco.setComplementoEndereco(gerenciaRegional.getComplementoEndereco());
						endereco.setNumeroImovel(gerenciaRegional.getNumeroImovel());
						endereco.setEnderecoReferencia(gerenciaRegional.getEnderecoReferencia());
						
						colecaoEnderecos.add(endereco);
						sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
					}

				}
			}
		}

		httpServletRequest.setAttribute("colecaoGerenciaRegionalTela", sessao
				.getAttribute("colecaoGerenciaRegionalTipoValorTela"));
		
        //Codigo Cliente
		if(atualizarGerenciaRegionalActionForm.getIdCliente() != null && 
			!atualizarGerenciaRegionalActionForm.getIdCliente().equals("") && 
			atualizarGerenciaRegionalActionForm.getNomeCliente() != null && 
			!atualizarGerenciaRegionalActionForm.getNomeCliente().equals("")){
							
			httpServletRequest.setAttribute("gerenteLocalidadeEncontrado","true");
		}	
		
		return retorno;
	}
	
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(AtualizarGerenciaRegionalActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getIdCliente())));

		// Pesquisa de acordo com os par�metros informados no filtro
		Collection colecaoCliente = 
			this.getFachada().pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a cole��o
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obt�m o objeto da cole��o pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setIdCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
			

		} else {
			form.setIdCliente("");
			form.setNomeCliente("Cliente inexistente");
		}
	}
}
