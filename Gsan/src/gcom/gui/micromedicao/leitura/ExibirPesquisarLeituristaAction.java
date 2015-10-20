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
package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Action que define o pr�-processamento da p�gina de pesquisar leiturista 
 *
 * @author Francisco Nascimento
 * @date 03/08/2007
 */
public class ExibirPesquisarLeituristaAction extends GcomAction {
	
	/**
	 * [UC0628] Pesquisar Leiturista
	 *
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno para a tela de pesquisar leiturista
		ActionForward retorno = actionMapping.findForward("pesquisarLeiturista");
		
		//Cria uma inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarLeituristaActionForm form = (PesquisarLeituristaActionForm) actionForm;
		
        //Pesquisando empresas
        if (sessao.getAttribute("colecaoEmpresa") == null){
        	
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa(FiltroEsferaPoder.DESCRICAO);
        	filtroEmpresa.setConsultaSemLimites(true);
        	
        	filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        	
        	Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa, 
        			Empresa.class.getName());
        	
        	if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()){
        		throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "EMPRESA");
        	}
        	
        	sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
        }
        //Fim de pesquisando empresas
		   
		// -------Parte que trata do c�digo quando o usu�rio tecla enter
		// caso seja o id do cliente
		String idDigitadoEnterCliente = form.getIdCliente();
		String idDigitadoFuncionario = form.getIdFuncionario();
        
		// Verifica se o c�digo foi digitado
		if (idDigitadoEnterCliente != null
				&& !idDigitadoEnterCliente.trim().equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idDigitadoEnterCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				// O cliente foi encontrado
				form.setIdCliente("" + ((Cliente) ((List) clienteEncontrado).get(0))
					.getId());
				form.setNomeCliente(((Cliente) ((List) clienteEncontrado).get(0))
					.getNome());
				// idClienteEncontrado no jsp, ser� verificada a presen�a deste campo para
				// identificar se algum objeto foi encontrado
				httpServletRequest.setAttribute("idClienteEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo","idCliente");

			} else {

				form.setIdCliente("");
				//httpServletRequest.setAttribute("idClienteNaoEncontrado",
					//	"exception");
				form.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","DDDTelefone");

			}

		}

		// Verifica se o c�digo foi digitado
		if (idDigitadoFuncionario != null
				&& !idDigitadoFuncionario.trim().equals("")) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idDigitadoFuncionario));

			Collection funcionarioEncontrado = fachada.pesquisar(filtroFuncionario,
					Funcionario.class.getName());

			if (funcionarioEncontrado != null && !funcionarioEncontrado.isEmpty()) {
				// O cliente foi encontrado
				form.setIdFuncionario("" + ((Funcionario) ((List) funcionarioEncontrado).get(0))
					.getId());
				form.setNomeFuncionario(((Funcionario) ((List) funcionarioEncontrado).get(0))
					.getNome());
				// idFuncionarioEncontrado no jsp, ser� verificada a presen�a deste campo para
				// identificar se algum objeto foi encontrado
				httpServletRequest.setAttribute("idFuncionarioEncontrado",
						"true");
				httpServletRequest.setAttribute("nomeCampo","idCliente");

			} else {

				form.setIdFuncionario("");
				form.setNomeFuncionario("FUNCION�RIO INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo","idFuncionario");

			}

		}

		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			// Verifica se o tipo da consulta de leiturista � de cliente
			// se for os parametros de enviarDadosParametros ser�o mandados para
			// a pagina leiturista_pesquisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta") != null){ 
				 if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {
					 form.setIdCliente(httpServletRequest
							 .getParameter("idCampoEnviarDados"));
					 form.setNomeCliente(httpServletRequest
							 .getParameter("descricaoCampoEnviarDados"));
					httpServletRequest.setAttribute("idClienteEncontrado",
						"true");
					 
				 } else if (httpServletRequest.getParameter("tipoConsulta").equals(
					"funcionario")) {
					 form.setIdFuncionario(httpServletRequest
						.getParameter("idCampoEnviarDados"));
					 form.setNomeFuncionario(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
					httpServletRequest.setAttribute("idFuncionarioEncontrado",
						"true");
					 
				 }
			}
		} else {
	        if (httpServletRequest.getParameter("objetoConsulta") == null
					|| httpServletRequest.getParameter("objetoConsulta")
							.equals("")) {

				form.setEmpresa("");
				form.setIdFuncionario("");
				form.setNomeFuncionario("");
				form.setIdCliente("");
				form.setNomeCliente("");
				form.setDDDTelefone("");
				form.setNumeroTelefone("");
				sessao.removeAttribute("caminhoRetornoTelaPesquisa");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaFuncionario");

			}		
		}
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaLeiturista") != null) {
        	
			sessao.setAttribute(
				"caminhoRetornoTelaPesquisaLeiturista",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaLeiturista"));
		}
		
        //Retorna o mapeamento contido na vari�vel retorno
		return retorno;
	}
}
