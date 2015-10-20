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
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para a pr�-exibi��o da p�gina de Manter Cliente
 * 
 * @author rodrigo
 */
public class ExibirManterClienteAction extends GcomAction {

	/**
	 * < <Descri��o do m�todo>>
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

		ActionForward retorno = actionMapping.findForward("manterCliente");

		Fachada fachada = Fachada.getInstancia();

		Collection<Cliente> clientes = null;

		//Mudar isso quando implementar a parte de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Parte da verifica��o do filtro
		FiltroCliente filtroCliente = null;
		
		String codigo  = (String) sessao.getAttribute("codigo");
		String cpf  = (String) sessao.getAttribute("cpf");
		String rg  = (String) sessao.getAttribute("rg");
		String cnpj  = (String) sessao.getAttribute("cnpj");
		String nome  = (String) sessao.getAttribute("nome");
		String nomeMae  = (String) sessao.getAttribute("nomeMae");		
		String cep  = (String) sessao.getAttribute("cep");
		String idMunicipio  = (String) sessao.getAttribute("idMunicipio");
		String codigoBairro  = (String) sessao.getAttribute("codigoBairro");
		String idLogradouro  = (String) sessao.getAttribute("idLogradouro");
		String indicadorUso  = (String) sessao.getAttribute("indicadorUso");
		String tipoPesquisa  = (String) sessao.getAttribute("tipoPesquisa");
		String tipoPesquisaNomeMae  = (String) sessao.getAttribute("tipoPesquisaNomeMae");
		String idEsferaPoder  = (String) sessao.getAttribute("idEsferaPoder");
		
		
		
		// Verifica se o filtro foi informado pela p�gina de filtragem de
		// cliente
		if (sessao.getAttribute("filtroCliente") != null ) {
			filtroCliente = (FiltroCliente) sessao
					.getAttribute("filtroCliente");
		} else {
			// Caso o exibirManterCliente n�o tenha passado por algum esquema de
			// filtro, a quantidade de registros � verificada para avaliar a necessidade
			// de filtragem
			filtroCliente = new FiltroCliente();
			
			retorno = actionMapping.findForward("filtrarCliente");
				
			// c�digo para checar ou nao o Atualizar
	        String primeiraVez = httpServletRequest.getParameter("menu");
			if (primeiraVez != null && !primeiraVez.equals("")) {
				//pesquisarActionForm.reset();
				//pesquisarActionForm.set("indicadorUso", "");
				sessao.setAttribute("indicadorAtualizar","1");
			}

			if (httpServletRequest.getParameter("desfazer") != null
	                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
		        //Limpando o formulario
				//pesquisarActionForm.reset();
		        sessao.setAttribute("indicadorAtualizar","1");
	        }
	        
			sessao.removeAttribute("voltar");
			sessao.removeAttribute("idRegistroAtualizacao");	
		}

		// A pesquisa de clientes s� ser� feita se o forward estiver direcionado
		// para a p�gina de manterEmpresa
		if (retorno.getName().equalsIgnoreCase("manterCliente")) {
			
			sessao.removeAttribute("atualizar");
			
			// Seta a ordena��o desejada do filtro
			filtroCliente.setCampoOrderBy(FiltroCliente.NOME);

			filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			/*filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.ORGAO_EXPEDIDOR_RG);*/
			filtroCliente
				.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.UNIDADE_FEDERACAO);

			// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
			//Integer totalRegistros = fachada
				///	.pesquisarClienteDadosClienteEnderecoCount(filtroCliente);
			Integer totalRegistros = (Integer) fachada.filtrarQuantidadeCliente(codigo,
					cpf,
					rg,
					cnpj,
					nome,
					nomeMae,		
					cep,
					idMunicipio,
					codigoBairro,
					idLogradouro,
					indicadorUso,
					tipoPesquisa,
					tipoPesquisaNomeMae, null,
					idEsferaPoder);

			// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
			// da pesquisa que est� no request
			//clientes = fachada
				//	.pesquisarClienteDadosClienteEndereco(filtroCliente, (Integer) httpServletRequest
					//		.getAttribute("numeroPaginasPesquisa"));
			clientes = fachada.filtrarCliente(
					codigo,
					cpf,
					rg,
					cnpj,
					nome,
					nomeMae,		
					cep,
					idMunicipio,
					codigoBairro,
					idLogradouro,
					indicadorUso,
					tipoPesquisa,
					tipoPesquisaNomeMae,
					null, idEsferaPoder,
					(Integer) httpServletRequest
							.getAttribute("numeroPaginasPesquisa"));
		    
			if (clientes == null || clientes.isEmpty()) {
				// Nenhum cliente cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			
			if (clientes.size()== 1 && httpServletRequest.getAttribute("atualizar") != null
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))){
				// caso o resultado do filtro s� retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema n�o exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarCliente");
				Cliente cliente = (Cliente)clientes.iterator().next();
				httpServletRequest
                	.setAttribute("idRegistroAtualizacao", cliente.getId().toString());
				sessao
                	.setAttribute("atualizar","atualizar");
			}else{
				// A cole��o fica na sess�o devido ao esquema de pagina��o
				sessao.setAttribute("clientes", clientes);
			}
		}

		return retorno;
	}
}